package dev.otectus.mcaconversations.gift;

import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import dev.otectus.mcaconversations.state.LastGift;
import dev.otectus.mcaconversations.support.TestPaths;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The Forge-written player files {@link ForgeCapsMigration} has to be able to read, and the
 * generator that produced the committed copies under {@code src/test/resources/fixtures}.
 *
 * <h2>Where the shape comes from</h2>
 *
 * These are synthesised from the Forge serialisation path rather than captured from a play session.
 * Read out of the 1.20.1 tree at commit {@code 89edad2}:
 *
 * <ul>
 *   <li>{@code event/ConversationsEvents.onAttachCapabilities} attached two providers under
 *       {@code ConversationsCapabilities.ID} and {@code CHAT_MODE_ID}, which are the same two
 *       {@code ResourceLocation}s this port still uses for its attachments;</li>
 *   <li>{@code gift/GiftMemoryProvider} and {@code chat/ChatModePlayerStateProvider} implemented
 *       {@code INBTSerializable<CompoundTag>} and their {@code serializeNBT()} did nothing but
 *       delegate to {@code GiftMemoryData}/{@code ChatModePlayerState}, whose serialisation is
 *       byte-for-byte the same code this port carries;</li>
 *   <li>Forge's {@code CapabilityDispatcher} collected every attached serialisable provider into
 *       one root-level {@code ForgeCaps} compound, keyed by the id the capability was added under.</li>
 * </ul>
 *
 * <p>So the only part of the envelope not taken from this repository's own code is the
 * {@code ForgeCaps} wrapper itself, which is stable, long-documented Forge behaviour.
 *
 * <h2>Regenerating</h2>
 *
 * <pre>
 * ./gradlew test --tests "*ForgeCapsMigrationTest*" -Dmcaconversations.regenerateFixtures=true
 * </pre>
 *
 * The committed {@code .dat} files are what the tests actually read. Generating them in memory
 * instead would make the tests pass in lockstep with any future change to
 * {@code GiftMemoryData.save} — which is precisely the change that would break a real migrating
 * world, so the bytes are frozen on disk on purpose.
 */
final class ForgeCapsFixtures {

    /** Forge's capability dispatcher wrote every attached capability under this root-level key. */
    static final String FORGE_CAPS = "ForgeCaps";

    static final String GIFT_KEY = "mcaconversations:gift_memory";
    static final String CHAT_KEY = "mcaconversations:chat_mode";

    /** Fixed synthetic villager ids, so a fixture is reproducible and carries no real player data. */
    static final UUID ANNA = UUID.fromString("11111111-2222-3333-4444-555555555555");
    static final UUID BRAM = UUID.fromString("66666666-7777-8888-9999-aaaaaaaaaaaa");

    static final LastGift ANNAS_GIFT = new LastGift("minecraft:golden_apple", 1, 120_500L);
    static final LastGift BRAMS_GIFT = new LastGift("minecraft:emerald", 12, 98_000L);

    private static final Path FIXTURE_DIR = TestPaths.of("src/test/resources/fixtures");

    private ForgeCapsFixtures() {
    }

    /** Every fixture, by file name, in the shape a Forge player file would have on disk. */
    static Map<String, CompoundTag> all() {
        Map<String, CompoundTag> fixtures = new LinkedHashMap<>();
        fixtures.put("forge-player-gift-only.dat", player(giftMemory(), null, false));
        fixtures.put("forge-player-chat-only.dat", player(null, chatMode(), false));
        fixtures.put("forge-player-both.dat", player(giftMemory(), chatMode(), true));
        fixtures.put("forge-player-malformed.dat", malformedPlayer());
        fixtures.put("fabric-player-no-forgecaps.dat", player(null, null, false));
        return fixtures;
    }

    static CompoundTag read(String name) throws IOException {
        try (InputStream in = ForgeCapsFixtures.class.getResourceAsStream("/fixtures/" + name)) {
            if (in == null) {
                throw new IOException("missing fixture /fixtures/" + name
                        + "; regenerate with -Dmcaconversations.regenerateFixtures=true");
            }
            return NbtIo.readCompressed(in, NbtAccounter.unlimitedHeap());
        }
    }

    static void writeAll() throws IOException {
        Files.createDirectories(FIXTURE_DIR);
        for (Map.Entry<String, CompoundTag> fixture : all().entrySet()) {
            try (OutputStream out = Files.newOutputStream(FIXTURE_DIR.resolve(fixture.getKey()))) {
                // Gzip-compressed NBT, the same encoding a real player .dat uses.
                NbtIo.writeCompressed(fixture.getValue(), out);
            }
        }
    }

    // --- The shapes ---------------------------------------------------------

    /** The {@code GiftMemoryData} tag: villager-UUID string keys to LastGift compounds. */
    private static CompoundTag giftMemory() {
        GiftMemoryData data = new GiftMemoryData();
        data.recordGift(ANNA, ANNAS_GIFT, 16);
        data.recordGift(BRAM, BRAMS_GIFT, 16);
        return data.save();
    }

    /**
     * The {@code ChatModePlayerState} tag for a player who explicitly opted <em>out</em>.
     *
     * <p>Deliberately the opt-out rather than the opt-in: on a server whose {@code chatModeDefaultOn}
     * is true, losing this record silently opts the player back in, which is the failure a migration
     * bug would actually produce and the one a fixture should pin.
     */
    private static CompoundTag chatMode() {
        ChatModePlayerState state = new ChatModePlayerState();
        state.setEnabled(false);
        return state.save();
    }

    /**
     * A player file whose {@code ForgeCaps} is present but useless in three different ways at once:
     * gift memory is a string rather than a compound, chat mode is a compound full of the wrong
     * keys, and a third mod's capability sits alongside them. Nothing here may throw, and the
     * unrelated entry must be ignored rather than misread.
     */
    private static CompoundTag malformedPlayer() {
        CompoundTag caps = new CompoundTag();
        caps.putString(GIFT_KEY, "this was never a compound");
        CompoundTag nonsense = new CompoundTag();
        nonsense.putString("explicit", "yes");
        nonsense.putInt("unexpected", 7);
        caps.put(CHAT_KEY, nonsense);
        CompoundTag foreign = new CompoundTag();
        foreign.putInt("mana", 40);
        caps.put("someothermod:mana", foreign);

        CompoundTag player = vanillaPlayer();
        player.put(FORGE_CAPS, caps);
        return player;
    }

    private static CompoundTag player(CompoundTag gift, CompoundTag chat, boolean withForeignCapability) {
        CompoundTag player = vanillaPlayer();
        if (gift == null && chat == null && !withForeignCapability) {
            return player;      // A NeoForge-native or vanilla file: no ForgeCaps at all.
        }
        CompoundTag caps = new CompoundTag();
        if (gift != null) {
            caps.put(GIFT_KEY, gift);
        }
        if (chat != null) {
            caps.put(CHAT_KEY, chat);
        }
        if (withForeignCapability) {
            // Any real Forge world has other mods' capabilities in here too.
            CompoundTag foreign = new CompoundTag();
            foreign.putBoolean("someFlag", true);
            caps.put("someothermod:state", foreign);
        }
        player.put(FORGE_CAPS, caps);
        return player;
    }

    /**
     * Just enough of a real player file to be honest about what surrounds {@code ForgeCaps}. The
     * migration reads none of it, and a fixture that contained only our own key would not
     * demonstrate that.
     */
    private static CompoundTag vanillaPlayer() {
        CompoundTag player = new CompoundTag();
        player.putInt("DataVersion", 3465);           // 1.20.1
        player.putFloat("Health", 20.0f);
        player.putInt("foodLevel", 20);
        player.putInt("XpLevel", 7);
        player.putInt("playerGameType", 0);
        player.put("Inventory", new ListTag());
        player.put("EnderItems", new ListTag());
        player.putIntArray("UUID", new int[]{0x1a2b3c4d, 0x5e6f7a8b, 0x1c2d3e4f, 0x5a6b7c8d});
        return player;
    }
}
