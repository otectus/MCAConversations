package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.locale.LineVoice;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.personality.Personalities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;

/**
 * The single point of contact with Minecraft Comes Alive: Reborn (together with the
 * {@code compat.mca} adapter package, which implements MCA functional interfaces).
 *
 * <p><b>No MCA type appears anywhere in this file.</b> Every MCA class and member is reached by name
 * at runtime through {@link McaHandles}, which probes for MCA's package root rather than assuming
 * one. That is not gold-plating: MCA repackaged mid-version-line. It ships a Forgix-merged
 * "Universal" jar, so its Forge classes sit under a loader-named root — {@code forge.net.mca.*}
 * through 7.7.0-beta.2, and {@code forge.net.conczin.mca.*} from 7.7.1-alpha.1, when the base package
 * was renamed. The root cannot be inferred from the version number, only probed.
 *
 * <p>This file used to {@code import forge.net.mca.*}, and on the renamed build that made every
 * method here throw {@code NoClassDefFoundError} while the JVM resolved the types in its own
 * signatures — before any {@code catch} could run. {@code McaBridge} caught it once at registration
 * and disabled the whole mod, which is why chat with villagers went silent.
 *
 * <p>Every method still fails safe: type guards, {@code try/catch (Throwable)}, DEBUG log, documented
 * safe default. MCA API drift must never crash a server.
 */
public final class McaCompat {

    private McaCompat() {
    }

    /** True for an MCA human villager (not the zombie variant). */
    public static boolean isMcaVillager(Entity entity) {
        return McaHandles.isVillager(entity);
    }

    /** The villager's raw MCA name string (for gossip snapshot caching). Safe default: empty. */
    public static Optional<String> getVillagerName(Entity entity) {
        if (isMcaVillager(entity)) {
            try {
                // Vanilla dispatch on a vanilla receiver: MCA overrides getDisplayName to return the
                // villager's given name, and reobfJar rewrites this call site, so it must not be
                // resolved by name the way MCA's own methods are.
                return Optional.ofNullable(entity.getDisplayName()).map(Component::getString);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getVillagerName failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }

    /**
     * The villager's localized profession text (e.g. "Farmer", "Guard"). Returned as a Component
     * so translation resolves client-side in the player's locale. Safe default: empty.
     */
    public static Optional<Component> getProfessionText(Entity villager) {
        try {
            return Optional.ofNullable(McaHandles.professionText(villager));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA getProfessionText failed; defaulting empty", t);
            return Optional.empty();
        }
    }

    /** The player's relationship hearts with this villager. Safe default: 0. */
    public static int getHearts(ServerPlayer player, Entity villager) {
        return McaHandles.hearts(villager, player);
    }

    /**
     * Moves hearts through MCA's own reward path — the same call its native {@code positive}/
     * {@code negative} dialogue actions make — so the heart particle, the interaction-fatigue bump,
     * the {@code HEARTS_CRITERION} advancement trigger and the mood change all still happen.
     *
     * <p>Returns the <b>measured</b> change ({@code after − before}), not the requested one. MCA
     * doubles a negative delta inside {@code rewardHearts} for a {@code SENSITIVE} villager, so the
     * requested number is not always what the player receives, and the honest figure is the one to
     * log and to show as chat heart feedback.
     *
     * <p>Note that MCA's native actions call {@code modifyMoodValue} once themselves <em>and</em>
     * again inside {@code rewardHearts}; this path moves mood once, which is the intended behaviour
     * for guarded conversation outcomes. Safe default: 0 (nothing moved). <b>Server side only.</b>
     */
    public static int rewardHearts(Entity villager, ServerPlayer player, int delta) {
        if (delta == 0 || player == null || !isMcaVillager(villager)) {
            return 0;
        }
        try {
            int before = getHearts(player, villager);
            McaHandles.rewardHearts(villager, player, delta);
            return getHearts(player, villager) - before;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA rewardHearts({}) failed; no hearts moved", delta, t);
            return 0;
        }
    }

    // ------------------------------------------------------------------
    // World state (weather; season/holiday later)
    // ------------------------------------------------------------------

    /** True when it is raining in the entity's level. Safe default: false. */
    public static boolean isRaining(Entity entity) {
        if (entity != null) {
            try {
                return entity.level().isRaining();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("isRaining failed; defaulting false", t);
            }
        }
        return false;
    }

    /** True when it is thundering (a storm) in the entity's level. Safe default: false. */
    public static boolean isThundering(Entity entity) {
        if (entity != null) {
            try {
                return entity.level().isThundering();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("isThundering failed; defaulting false", t);
            }
        }
        return false;
    }

    /** The entity level's current day count ({@code dayTime / 24000}). Safe default: 0. */
    public static long getWorldDay(Entity entity) {
        if (entity != null) {
            try {
                return entity.level().getDayTime() / 24000L;
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("getWorldDay failed; defaulting 0", t);
            }
        }
        return 0L;
    }

    // ------------------------------------------------------------------
    // LongTermMemory (dialogue memory) primitives
    // ------------------------------------------------------------------

    /**
     * Writes a LongTermMemory entry expiring {@code durationTicks} from now (MCA stores expiry
     * time; its JSON {@code memory} condition reads time-remaining). <b>Server side only.</b>
     */
    public static void remember(Entity villager, String id, long durationTicks) {
        McaHandles.remember(villager, id, durationTicks);
    }

    /** Writes a permanent (never-expiring) LongTermMemory entry. <b>Server side only.</b> */
    public static void rememberForever(Entity villager, String id) {
        McaHandles.rememberForever(villager, id);
    }

    /** True when an unexpired LongTermMemory entry exists. Safe default: false. */
    public static boolean hasMemory(Entity villager, String id) {
        return McaHandles.memoryTicks(villager, id).orElse(0L) > 0L;
    }

    /** Ticks remaining until the memory expires (0 = missing/expired). Safe default: empty. */
    public static OptionalLong getMemoryRemaining(Entity villager, String id) {
        return McaHandles.memoryTicks(villager, id);
    }

    // ------------------------------------------------------------------
    // Dialogue line delivery
    // ------------------------------------------------------------------

    /**
     * Shows a line from the villager inside the open interact/dialogue screen, exactly like MCA's
     * own {@code say} dialogue action: {@code getTranslatable} handles the spouse-aware player name
     * (always {@code %1$s}), dialogue-type key fallback, and (client-side) random {@code /N}
     * variants; extra args land at {@code %2$s}+.
     *
     * <p>{@code phrase} is the key <em>without</em> the {@code dialogue.} prefix. Fallback when
     * MCA's network path fails: plain system chat message. <b>Server side only.</b>
     */
    public static void sayInDialogue(Entity villager, ServerPlayer player, String phrase, Object... args) {
        Optional<MutableComponent> built = getDialogueLine(villager, player, phrase, args);
        if (built.isEmpty()) {
            return;
        }
        // Name the pooled variant here, on the server, before the packet leaves. MCA would otherwise
        // resolve the pool on the client with a fresh random draw and no memory of the last sentence,
        // which is what makes a pool of three read like a pool of one. See LineVoice.
        Optional<Component> line = Optional.of(LineVoice.pinned(built.get(), villager, player));
        if (McaHandles.sendDialogueLine(player, line.get())) {
            return;
        }
        McaConversations.LOGGER.debug("MCA sayInDialogue({}) failed; falling back to chat", phrase);
        try {
            player.sendSystemMessage(line.get());
        } catch (Throwable t2) {
            McaConversations.LOGGER.debug("Chat fallback failed too; dropping line", t2);
        }
    }

    /**
     * Builds a villager-voiced dialogue line via MCA's {@code getTranslatable} — the same path
     * {@link #sayInDialogue} uses, minus delivery. {@code getTranslatable} resolves the spouse-aware
     * player name ({@code %1$s}), the per-personality/age lang overlay markers, and (client-side) random
     * {@code /N} variants; extra args land at {@code %2$s}+. {@code phrase} is the key <em>without</em>
     * the {@code dialogue.} prefix. Safe default: empty (caller falls back). <b>Server side only.</b>
     */
    public static Optional<MutableComponent> getDialogueLine(Entity villager, ServerPlayer player,
                                                             String phrase, Object... args) {
        try {
            return Optional.ofNullable(McaHandles.translatable(villager, player, "dialogue." + phrase, args));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA getDialogueLine({}) failed; defaulting empty", phrase, t);
            return Optional.empty();
        }
    }

    // ------------------------------------------------------------------
    // Relationships / family
    // ------------------------------------------------------------------

    /** The villager's spouse UUID, empty when unmarried. Safe default: empty. */
    public static Optional<UUID> getPartnerUuid(Entity villager) {
        return McaHandles.partnerUuid(villager);
    }

    /** The villager's spouse display name. Safe default: empty. */
    public static Optional<String> getSpouseName(Entity villager) {
        return McaHandles.partnerName(villager);
    }

    /** Resolves a (possibly unloaded) villager's name from MCA's family tree. Safe default: empty. */
    public static Optional<String> familyTreeName(ServerLevel level, UUID villagerUuid) {
        return McaHandles.familyTreeName(level, villagerUuid);
    }

    /**
     * Copies the player's MCA-editor name (stored server-side as the {@code "villagerName"} tag in their
     * {@code PlayerSaveData} entity data) into their MCA family-tree node. MCA's {@code getTranslatable}
     * resolves the player-name slot ({@code %1$s}) from that node, so this makes every villager — ours
     * and MCA's own — address the player by their chosen MCA name rather than the vanilla username.
     * <b>No-op</b> when the player hasn't chosen a name (leaves MCA's username fallback intact).
     * <b>Server side only.</b>
     */
    public static void syncPlayerFamilyName(ServerPlayer player) {
        McaHandles.syncPlayerFamilyName(player);
    }

    /**
     * The villager's MCA personality as its bare lowercase id (e.g. {@code odd}, {@code upbeat}) —
     * the same token MCA uses as the personality prefix on dialogue lang keys. Safe default: empty.
     *
     * <p><b>Version-agnostic on purpose.</b> MCA 7.6 declares {@code Personality} as an enum and 7.7
     * as a registry-backed class, so neither {@code name()} (gone in 7.7) nor {@code getPersonalityId()}
     * (absent in 7.6) can be called from a single binary. {@code toString()} exists in both — inherited
     * from {@code Enum} on 7.6 ({@code "ODD"}) and overridden to the namespaced id on 7.7
     * ({@code "mca:odd"}) — and {@link Personalities#normalize} reduces both to {@code "odd"}. Reading
     * it as an opaque {@link Object} means the binding needs no entry for the type at all.
     */
    public static Optional<String> getPersonality(Entity villager) {
        try {
            return Optional.ofNullable(McaHandles.personalityString(villager))
                    .map(Personalities::normalize)
                    .filter(s -> !s.isEmpty());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA getPersonality failed; defaulting empty", t);
            return Optional.empty();
        }
    }

    /**
     * The villager's current MCA mood name (e.g. {@code sad}, {@code overjoyed}) — the same values
     * the native {@code mood} dialogue condition matches. Safe default: empty.
     */
    public static Optional<String> getMoodName(Entity villager) {
        return Optional.ofNullable(McaHandles.moodName(villager));
    }

    /** True when the villager's MCA age state is ADULT. Safe default: false (fail closed for gating). */
    public static boolean isAdult(Entity villager) {
        return "adult".equals(McaHandles.ageStateName(villager));
    }

    /** True when the villager is married (to anyone — player or villager). Safe default: false. */
    public static boolean isMarried(Entity villager) {
        return McaHandles.isMarried(villager);
    }

    /** True when the villager is married to exactly this player. Safe default: false. */
    public static boolean isMarriedToPlayer(Entity villager, UUID playerUuid) {
        return McaHandles.isMarriedTo(villager, playerUuid);
    }

    /**
     * The structural romance gate for the Attraction axis and all romantic content: the villager
     * must be an adult, and either married to this player or not married at all. <b>Fails closed</b> —
     * any MCA read failure means not eligible; a compat break must never open romance toward a
     * child, teen, or someone else's spouse.
     */
    public static boolean isRomanceEligible(Entity villager, ServerPlayer player) {
        if (villager == null || player == null || !isAdult(villager)) {
            return false;
        }
        return isMarriedToPlayer(villager, player.getUUID()) || !isMarried(villager);
    }

    /** True when the villager's MCA age state is BABY (used for birth detection). Safe default: false. */
    public static boolean isBaby(Entity villager) {
        return "baby".equals(McaHandles.ageStateName(villager));
    }

    /**
     * The villager's MCA age state as a lowercase name ({@code baby}, {@code toddler},
     * {@code child}, {@code teen}, {@code adult}) — the same vocabulary the native
     * {@code age_group} dialogue condition matches. Safe default: empty.
     *
     * <p>Chat mode uses this to pick an age-appropriate voice: babies babble rather than answer,
     * and toddlers get their own shorter variants of the chat-mode replies (see {@code AgeVoice}).
     */
    public static Optional<String> getAgeGroup(Entity villager) {
        return Optional.ofNullable(McaHandles.ageStateName(villager));
    }

    // ------------------------------------------------------------------
    // Villages
    // ------------------------------------------------------------------

    /** The id of the villager's home village. Safe default: empty. */
    public static OptionalInt getHomeVillageId(Entity villager) {
        return McaHandles.homeVillageId(villager);
    }

    /** The villager's home village name. Safe default: empty. */
    public static Optional<String> getHomeVillageName(Entity villager) {
        return McaHandles.homeVillageName(villager);
    }

    /**
     * MCA's own {@code Village} object for an id, handed back as an opaque {@link Object}.
     *
     * <p>The one MCA read whose <em>type</em> a caller must not see. Townstead's village-spirit
     * aggregator takes an MCA {@code Village}, and the Townstead binding reaches it through a
     * method handle whose parameters are all {@code Object} precisely so that no MCA package name
     * enters that binding. Returning the real type here would put it straight back.
     *
     * <p>Nothing outside a guarded compatibility package should call this. Safe default: empty.
     */
    public static Optional<Object> villageHandle(ServerLevel level, int villageId) {
        return McaHandles.villageHandle(level, villageId);
    }

    /** The nearest MCA village id within {@code radius} of {@code pos}. Safe default: empty. */
    public static OptionalInt findNearestVillageId(ServerLevel level, BlockPos pos, int radius) {
        return McaHandles.nearestVillageId(level, pos, radius);
    }

    /** The currently-loaded resident villager entities of a village. Safe default: empty list. */
    public static List<Entity> loadedVillageResidents(ServerLevel level, int villageId) {
        return McaHandles.loadedResidents(level, villageId);
    }

    /**
     * The full resident UUID set of a village — <b>independent of chunk loading</b>, so unloaded
     * residents still count. This is what arrival/departure diffing reads, so an unloaded villager is
     * never mistaken for one who left. Safe default: empty set.
     */
    public static Set<UUID> villageResidentUuids(ServerLevel level, int villageId) {
        return McaHandles.residentUuids(level, villageId);
    }

    /** UUID→name for the full residency set (also names unloaded residents). Safe default: empty map. */
    public static Map<UUID, String> villageResidentNames(ServerLevel level, int villageId) {
        return McaHandles.residentNames(level, villageId);
    }

    // ------------------------------------------------------------------
    // Chat mode: driving the dialogue engine as a GUI button click would
    // ------------------------------------------------------------------

    /**
     * Drives MCA's dialogue engine exactly as a GUI button click would: runs the full weighted Result
     * selection for {@code answerName} under {@code questionId} and triggers the chosen Result's actions
     * (say/next, hearts, remember, and every {@code conversations_*} custom action). Delivery of the
     * resulting {@code say}/{@code next} GUI packets is redirected to chat by {@code NetworkHandlerMixin}
     * while a {@code ChatModeSession} redirect scope is open. Returns false on any failure. <b>Server side
     * only.</b>
     */
    public static boolean selectAnswer(Entity villager, ServerPlayer player, String questionId, String answerName) {
        return McaHandles.selectAnswer(villager, player, questionId, answerName);
    }

    /**
     * Enforces an answer's {@code constraints} the way the GUI does at render time (MCA's
     * {@code selectAnswer} trusts the click and does <em>not</em> re-validate), so chat mode cannot
     * trigger e.g. spouse-only content for a non-spouse. Resolves the question and answer, computes the
     * villager+player's satisfied constraint set, and checks the answer accepts it.
     * <b>Fails closed:</b> an unknown binding or any MCA read failure returns false (ineligible), so a
     * compat break degrades to "answer unavailable", never "constraints bypassed".
     */
    public static boolean checkConstraints(Entity villager, ServerPlayer player, String questionId, String answerName) {
        return McaHandles.checkConstraints(villager, player, questionId, answerName);
    }

    /**
     * The UUID of the player currently in a GUI interaction (open screen) with this villager, so chat
     * mode can defer with a busy line instead of driving the engine concurrently. MCA's
     * {@code getInteractingPlayer()} is already filtered to players with an open screen. Fail-open:
     * empty on any failure. <b>Server side only.</b>
     */
    public static Optional<UUID> isInteractingWith(Entity villager) {
        return McaHandles.interactingPlayer(villager);
    }

    // ------------------------------------------------------------------
    // Living-histories context capabilities (spec §7.3)
    //
    // Added as one group because they answer one question the mod could not previously ask: what does
    // this villager's working and family life actually look like right now. Every one returns an
    // Optional or an empty collection on a miss, so ConversationContextSnapshot can record the field
    // as UNKNOWN rather than as a default that later reads like an observation.
    // ------------------------------------------------------------------

    /**
     * The villager's exact profession registry id, {@code "minecraft:farmer"}.
     *
     * <p>The single most valuable of the new reads. Until now the mod inferred a profession from
     * {@code getProfessionText}, which is a localized display string — so it could tell farmers from
     * librarians only by matching translated prose, and never noticed a profession <em>change</em>.
     * Safe default: empty.
     */
    public static Optional<String> getProfessionId(Entity villager) {
        return McaHandles.professionId(villager);
    }

    /** The MCA chore the player assigned, lowercased ({@code none}, {@code harvest}…). Empty on a miss. */
    public static Optional<String> getCurrentChore(Entity villager) {
        return McaHandles.currentChore(villager);
    }

    /** True while MCA's brain reports panic — the strongest suppressor of ordinary initiative. */
    public static boolean isPanicking(Entity villager) {
        return McaHandles.isPanicking(villager);
    }

    /** True while MCA's brain reports grief. */
    public static boolean isGrieving(Entity villager) {
        return McaHandles.isGrieving(villager);
    }

    /** The villager's assigned workplace block. Safe default: empty. */
    public static Optional<BlockPos> getWorkplace(Entity villager) {
        return McaHandles.workplace(villager);
    }

    /** The villager's assigned home block, dimension dropped. Safe default: empty. */
    public static Optional<BlockPos> getHomePos(Entity villager) {
        return McaHandles.homePos(villager);
    }

    /** Registry ids of the villager's MCA traits, lowercased. Safe default: empty set. */
    public static Set<String> getTraitIds(Entity villager) {
        return McaHandles.traitIds(villager);
    }

    /**
     * Which of {@code itemTags} the villager is carrying at least one of.
     *
     * <p>Presence only, never a count: "I have the iron" is an observation a villager can make, and
     * "we are down to nine iron" is an economy claim this mod does not get to invent (spec §12.2).
     */
    public static Set<String> getCarriedTags(Entity villager, java.util.Collection<String> itemTags) {
        return McaHandles.inventoryTags(villager, itemTags);
    }

    /** True when MCA's family tree records this UUID as deceased. Safe default: false. */
    public static boolean isDeceased(ServerLevel level, UUID villagerUuid) {
        return McaHandles.isDeceased(level, villagerUuid);
    }

    /** A villager's partner from the family tree, whether or not they are loaded. */
    public static Optional<UUID> getPartnerFromTree(ServerLevel level, UUID villagerUuid) {
        return McaHandles.partnerOf(level, villagerUuid);
    }

    /** Father then mother, absent relations skipped. Safe default: empty list. */
    public static List<UUID> getParents(ServerLevel level, UUID villagerUuid) {
        return McaHandles.parentsOf(level, villagerUuid);
    }

    public static Set<UUID> getSiblings(ServerLevel level, UUID villagerUuid) {
        return McaHandles.siblingsOf(level, villagerUuid);
    }

    public static Set<UUID> getChildren(ServerLevel level, UUID villagerUuid) {
        return McaHandles.childrenOf(level, villagerUuid);
    }

    /** A family member's profession id, so "my sister the mason" is read rather than guessed. */
    public static Optional<String> getFamilyTreeProfessionId(ServerLevel level, UUID villagerUuid) {
        return McaHandles.familyTreeProfessionId(level, villagerUuid);
    }

    /** The village's current population. Safe default: empty. */
    public static OptionalInt getVillagePopulation(ServerLevel level, int villageId) {
        return McaHandles.villagePopulation(level, villageId);
    }

    /**
     * MCA's building type token at a position ({@code library}, {@code smithy}, {@code house}…).
     *
     * <p>This is how a scene learns it is being told inside a library without any block-by-block
     * simulation of its own (spec §7.3). Safe default: empty.
     */
    public static Optional<String> getBuildingTypeAt(ServerLevel level, int villageId, BlockPos pos) {
        return McaHandles.buildingTypeAt(level, villageId, pos);
    }
}
