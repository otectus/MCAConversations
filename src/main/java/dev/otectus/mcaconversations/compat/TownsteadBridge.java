package dev.otectus.mcaconversations.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The seam to the optional Townstead integration (Townstead spec 5.1), built to the discipline
 * {@link ReputationBridge} and {@link QuestsBridge} already use in this mod.
 *
 * <p><b>Only {@code java.*} and {@code net.minecraft.*} types, plus Conversations' own
 * {@code Townstead*View} records, may appear in this interface.</b> The real implementation lives
 * under {@code compat.townstead} and is reached by name from {@link TownsteadCompat} only after
 * {@code ModList} confirms Townstead is present. Nothing here, nor anything reachable from here
 * without that check, may name a {@code com.aetherianartificer.townstead} type;
 * {@code NoTownsteadStaticLinkTest} enforces it.
 *
 * <p><b>Every method is total.</b> Reads return an empty view and writes return {@code false} rather
 * than throwing, because these are called from dialogue condition scoring, per-tick chat scans and
 * the heart-application path, where an exception would take a conversation, or a tick, with it. A
 * capability that fails to bind disables its own features and nothing else.
 *
 * <p>There is one deliberate difference from the sibling mods' bridges: the fallback is a real
 * {@link NoopTownsteadBridge} rather than a {@code null} facade, so query code never has to branch
 * around a missing root object (spec 5.2).
 */
public interface TownsteadBridge {

    /** How much of Townstead bound. {@link TownsteadStatus#ABSENT} when the mod is not installed. */
    TownsteadStatus status();

    /** The capabilities that bound. Empty when Townstead is absent. */
    Set<TownsteadCapability> capabilities();

    /** Townstead's declared mod version, or an empty string when it is not installed. */
    String detectedVersion();

    /**
     * Which MCA package root the installed Townstead was compiled against, when that could be
     * determined. Reported for diagnostics only; no code path ever branches on it.
     */
    Optional<String> variant();

    /**
     * Members that were expected but did not bind, named for a bug report, each with the reason it
     * failed. Empty when everything bound and when Townstead is absent: an absent mod is not a
     * partial binding.
     */
    default List<String> unresolvedMembers() {
        return List.of();
    }

    // ---------------------------------------------------------------------------------- reads

    /** Everything Townstead knows about this villager, or {@link TownsteadVillagerView#EMPTY}. */
    TownsteadVillagerView villager(Entity entity);

    /** The server's Townstead calendar, or {@link TownsteadCalendarView#EMPTY}. */
    TownsteadCalendarView calendar(MinecraftServer server);

    /** The registered building at a position, or {@link TownsteadBuildingView#EMPTY}. */
    TownsteadBuildingView buildingAt(ServerLevel level, BlockPos pos);

    /** A root definition by id, or {@link TownsteadRootView#EMPTY}. */
    TownsteadRootView root(ResourceLocation id);

    /**
     * A personality definition by its Townstead id, resolving the custom display name and the MCA
     * base voice behind it. {@link TownsteadPersonalityView#EMPTY} for an unknown id.
     */
    TownsteadPersonalityView personality(String personalityId);

    /**
     * Spirit for one MCA village. Keyed by village id rather than by a resident entity because the
     * gossip sweep needs the spirit of a village whose residents may all be unloaded, and because
     * {@code McaCompat} already resolves villages by id.
     */
    TownsteadSpiritView spiritForVillage(ServerLevel level, int villageId);

    /**
     * Townstead's full resolved context-tag set for this villager (spec 8.3). Reused rather than
     * reimplemented: a partial duplicate would drift from the vocabulary packs are written against.
     *
     * <p>Note these tags are <b>not player-scoped</b>. Townstead derives the player-relationship and
     * held-item tags from its own scan, so two players talking to one villager see the same set.
     */
    Set<String> contextTags(Entity villager);

    /** The skills this villager has learned, as namespaced id strings. Never {@code null}. */
    Set<String> learnedSkills(Entity villager);

    boolean hasSkill(Entity villager, String skillId);

    /** True when Townstead recognises this spirit id, so content can be linted against the real mod. */
    boolean isKnownSpirit(String spiritId);

    /**
     * True while Townstead is playing a reaction on this villager. Chat attention must not fight an
     * animation lock, so this is the first thing the attention policy asks.
     */
    boolean isReactionLocked(Entity villager, long gameTime);

    // --------------------------------------------------------------------------------- writes
    //
    // The only three things Conversations ever tells Townstead. None of them mutates needs,
    // schedules, professions, skills, roots, genes, the calendar, buildings or village spirit:
    // Townstead stays authoritative for every one of those (spec 1).

    /**
     * Play an authored, heart-neutral reaction for a conversation outcome. Fired with Townstead's
     * CONTEXT trigger source so its sleep, reaction-lock, cooldown, chance and movement gates all
     * stay active, and with the given tags in the reaction context because Townstead matches a
     * reaction's required_tags against exactly that set.
     *
     * @return true when Townstead actually played something; false when it declined, which is
     *         ordinary and must never disturb the conversation
     */
    boolean fireReaction(ServerLevel level, Entity villager, ServerPlayer player,
                         ResourceLocation reaction, Set<String> tags);

    /**
     * Tell Townstead the <b>measured</b> MCA heart delta after Conversations applied affection, so
     * its heart_increased and heart_decreased context tags are true. Never the authored, scaled or
     * granted figure: MCA doubles a negative for a sensitive villager, so only the measured number is
     * what the player actually received.
     */
    void markHeartChange(Entity villager, int measuredDelta, long gameTime);

    /** Mark a typed-chat conversation open, so Townstead emits its dialogue context tags. */
    void dialogueOpen(Entity villager, ServerPlayer player, long gameTime);

    /** Mark a typed-chat conversation closed. Idempotent; safe to call for an unopened villager. */
    void dialogueClose(Entity villager, ServerPlayer player, long gameTime);

    // --------------------------------------------------------------------------- convenience

    default boolean has(TownsteadCapability capability) {
        return capabilities().contains(capability);
    }

    /** True when Townstead is installed and at least its core facade bound. */
    default boolean isAvailable() {
        return status() == TownsteadStatus.FULL || status() == TownsteadStatus.PARTIAL;
    }

    /**
     * The live bridge. Never {@code null}: an absent Townstead is {@link NoopTownsteadBridge}, whose
     * every answer is the neutral one.
     */
    final class Holder {
        private static volatile TownsteadBridge instance = NoopTownsteadBridge.INSTANCE;

        private Holder() {
        }

        public static TownsteadBridge get() {
            return instance;
        }

        /** Set once from {@link TownsteadCompat#init()}; last writer wins. */
        public static void set(TownsteadBridge bridge) {
            instance = bridge == null ? NoopTownsteadBridge.INSTANCE : bridge;
        }
    }
}
