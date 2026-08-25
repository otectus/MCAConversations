package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.personality.Personalities;
import net.conczin.mca.network.Network;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.entity.VillagerLike;
import net.conczin.mca.entity.ai.Memories;
import net.conczin.mca.entity.ai.relationship.AgeState;
import net.conczin.mca.entity.ai.relationship.EntityRelationship;
import net.conczin.mca.entity.interaction.Constraint;
import net.conczin.mca.network.s2c.InteractionDialogueQuestionResponse;
import net.conczin.mca.resources.Dialogues;
import net.conczin.mca.resources.data.dialogue.Answer;
import net.conczin.mca.resources.data.dialogue.Question;
import net.conczin.mca.server.world.data.FamilyTree;
import net.conczin.mca.server.world.data.FamilyTreeNode;
import net.conczin.mca.server.world.data.PlayerSaveData;
import net.conczin.mca.server.world.data.Village;
import net.conczin.mca.server.world.data.VillageManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
 * <p><b>Why {@code net.conczin.mca.*}?</b> That is simply where MCA's classes live. The 1.20.1 line
 * shipped a Forgix-merged "Universal" jar that relocated each loader's copy under a loader-named
 * root ({@code forge.net.mca.*}); the 1.21.1 line drops that entirely and publishes a per-loader
 * artifact — {@code net.conczin.mca:mca-neoforge} — with the classes at their real package.
 *
 * <p>Every method here fails safe: instanceof guards, {@code try/catch (Throwable)}, DEBUG log,
 * documented safe default. MCA API drift must never crash a server.
 */
public final class McaCompat {

    private McaCompat() {
    }

    /** True for an MCA human villager (not the zombie variant). */
    public static boolean isMcaVillager(Entity entity) {
        return entity instanceof VillagerEntityMCA;
    }

    /** The villager's raw MCA name string (for gossip snapshot caching). Safe default: empty. */
    public static Optional<String> getVillagerName(Entity entity) {
        if (entity instanceof VillagerEntityMCA mca) {
            try {
                return Optional.ofNullable(mca.getDisplayName()).map(c -> c.getString());
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
    public static Optional<net.minecraft.network.chat.Component> getProfessionText(Entity villager) {
        if (villager instanceof VillagerLike<?> v) {
            try {
                return Optional.ofNullable(v.getProfessionText());
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getProfessionText failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }

    /** The player's relationship hearts with this villager. Safe default: 0. */
    public static int getHearts(ServerPlayer player, Entity villager) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                Memories memories = mca.getVillagerBrain().getMemoriesForPlayer(player);
                return memories == null ? 0 : memories.getHearts();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getHearts failed; defaulting 0", t);
            }
        }
        return 0;
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
        if (delta == 0 || player == null || !(villager instanceof VillagerEntityMCA mca)) {
            return 0;
        }
        try {
            int before = getHearts(player, villager);
            mca.getVillagerBrain().rewardHearts(player, delta);
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
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                mca.getLongTermMemory().remember(id, durationTicks);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA remember({}) failed; ignoring", id, t);
            }
        }
    }

    /** Writes a permanent (never-expiring) LongTermMemory entry. <b>Server side only.</b> */
    public static void rememberForever(Entity villager, String id) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                mca.getLongTermMemory().remember(id);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA rememberForever({}) failed; ignoring", id, t);
            }
        }
    }

    /** True when an unexpired LongTermMemory entry exists. Safe default: false. */
    public static boolean hasMemory(Entity villager, String id) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return mca.getLongTermMemory().getMemory(id) > 0;
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA hasMemory({}) failed; defaulting false", id, t);
            }
        }
        return false;
    }

    /** Ticks remaining until the memory expires (0 = missing/expired). Safe default: empty. */
    public static OptionalLong getMemoryRemaining(Entity villager, String id) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return OptionalLong.of(mca.getLongTermMemory().getMemory(id));
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getMemoryRemaining({}) failed; defaulting empty", id, t);
            }
        }
        return OptionalLong.empty();
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
        Optional<MutableComponent> line = getDialogueLine(villager, player, phrase, args);
        if (line.isEmpty()) {
            return;
        }
        try {
            // 1.21.1 argument order is (Component, boolean), the reverse of the 1.20.1 packet's
            // (boolean, Component). silent=false keeps MCA's own delivery sound.
            Network.sendToPlayer(new InteractionDialogueQuestionResponse(line.get(), false), player);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA sayInDialogue({}) failed; falling back to chat", phrase, t);
            try {
                player.sendSystemMessage(line.get());
            } catch (Throwable t2) {
                McaConversations.LOGGER.debug("Chat fallback failed too; dropping line", t2);
            }
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
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return Optional.ofNullable(mca.getTranslatable(player, "dialogue." + phrase, args));
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getDialogueLine({}) failed; defaulting empty", phrase, t);
            }
        }
        return Optional.empty();
    }

    // ------------------------------------------------------------------
    // Relationships / family
    // ------------------------------------------------------------------

    /** The villager's spouse UUID, empty when unmarried. Safe default: empty. */
    public static Optional<UUID> getPartnerUuid(Entity villager) {
        try {
            return EntityRelationship.of(villager).flatMap(EntityRelationship::getPartnerUUID);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA getPartnerUuid failed; defaulting empty", t);
            return Optional.empty();
        }
    }

    /** The villager's spouse display name. Safe default: empty. */
    public static Optional<String> getSpouseName(Entity villager) {
        try {
            return EntityRelationship.of(villager)
                    .flatMap(EntityRelationship::getPartnerName)
                    .map(c -> c.getString());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA getSpouseName failed; defaulting empty", t);
            return Optional.empty();
        }
    }

    /** Resolves a (possibly unloaded) villager's name from MCA's family tree. Safe default: empty. */
    public static Optional<String> familyTreeName(ServerLevel level, UUID villagerUuid) {
        try {
            FamilyTree tree = FamilyTree.get(level);
            return tree.getOrEmpty(villagerUuid).map(FamilyTreeNode::getName).filter(n -> !n.isBlank());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA familyTreeName failed; defaulting empty", t);
            return Optional.empty();
        }
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
        try {
            PlayerSaveData data = PlayerSaveData.get(player);
            String chosen = data.getEntityData().getString("villagerName");
            if (chosen == null || chosen.isBlank()) {
                return; // never chosen a name -> keep MCA's username fallback
            }
            FamilyTreeNode node = data.getFamilyEntry(); // get-or-create (defaults to username)
            if (!chosen.equals(node.getName())) {
                node.setName(chosen); // setName() -> markDirty() -> persists
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA syncPlayerFamilyName failed; ignoring", t);
        }
    }

    /**
     * The villager's MCA personality as its bare lowercase id (e.g. {@code odd}, {@code upbeat}) —
     * the same token MCA uses as the personality prefix on dialogue lang keys. Safe default: empty.
     *
     * <p>Reads the stable registry id rather than {@code toString()}. The 1.20.1 build had to go
     * through {@code toString()} because it targeted both MCA 7.6 (where {@code Personality} was an
     * enum) and 7.7 (where it became a registry-backed class); this line targets 7.7 on 1.21.1 only,
     * where {@code getPersonalityId()} is the documented stable accessor.
     * {@link Personalities#normalize} still strips the {@code mca:} namespace and handles a
     * third-party personality registered under some other namespace.
     */
    public static Optional<String> getPersonality(Entity villager) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return Optional.ofNullable(mca.getVillagerBrain().getPersonalityId())
                        .map(Object::toString)
                        .map(Personalities::normalize)
                        .filter(s -> !s.isEmpty());
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getPersonality failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }

    /**
     * The villager's current MCA mood name (e.g. {@code sad}, {@code overjoyed}) — the same values
     * the native {@code mood} dialogue condition matches. Safe default: empty.
     */
    public static Optional<String> getMoodName(Entity villager) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return Optional.ofNullable(mca.getVillagerBrain().getMood()).map(m -> m.getName());
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getMoodName failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }

    /** True when the villager's MCA age state is ADULT. Safe default: false (fail closed for gating). */
    public static boolean isAdult(Entity villager) {
        if (villager instanceof VillagerLike<?> v) {
            try {
                return v.getAgeState() == AgeState.ADULT;
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA isAdult failed; defaulting false", t);
            }
        }
        return false;
    }

    /** True when the villager is married (to anyone — player or villager). Safe default: false. */
    public static boolean isMarried(Entity villager) {
        try {
            return EntityRelationship.of(villager).map(EntityRelationship::isMarried).orElse(false);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA isMarried failed; defaulting false", t);
            return false;
        }
    }

    /** True when the villager is married to exactly this player. Safe default: false. */
    public static boolean isMarriedToPlayer(Entity villager, UUID playerUuid) {
        try {
            return EntityRelationship.of(villager).map(r -> r.isMarriedTo(playerUuid)).orElse(false);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA isMarriedToPlayer failed; defaulting false", t);
            return false;
        }
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
        if (villager instanceof VillagerLike<?> v) {
            try {
                return v.getAgeState() == AgeState.BABY;
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA isBaby failed; defaulting false", t);
            }
        }
        return false;
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
        if (villager instanceof VillagerLike<?> v) {
            try {
                AgeState state = v.getAgeState();
                return state == null
                        ? Optional.empty()
                        : Optional.of(state.name().toLowerCase(java.util.Locale.ROOT));
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getAgeGroup failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }

    // ------------------------------------------------------------------
    // Villages
    // ------------------------------------------------------------------

    /** The id of the villager's home village. Safe default: empty. */
    public static OptionalInt getHomeVillageId(Entity villager) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                Optional<Village> village = mca.getResidency().getHomeVillage();
                return village.map(v -> OptionalInt.of(v.getId())).orElseGet(OptionalInt::empty);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getHomeVillageId failed; defaulting empty", t);
            }
        }
        return OptionalInt.empty();
    }

    /** The villager's home village name. Safe default: empty. */
    public static Optional<String> getHomeVillageName(Entity villager) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return mca.getResidency().getHomeVillage().map(Village::getName);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA getHomeVillageName failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }

    /** The nearest MCA village id within {@code radius} of {@code pos}. Safe default: empty. */
    public static OptionalInt findNearestVillageId(ServerLevel level, BlockPos pos, int radius) {
        try {
            return VillageManager.get(level).findNearestVillage(pos, radius)
                    .map(v -> OptionalInt.of(v.getId())).orElseGet(OptionalInt::empty);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA findNearestVillageId failed; defaulting empty", t);
            return OptionalInt.empty();
        }
    }

    /** The currently-loaded resident villager entities of a village. Safe default: empty list. */
    public static List<Entity> loadedVillageResidents(ServerLevel level, int villageId) {
        try {
            return VillageManager.get(level).getOrEmpty(villageId)
                    .map(v -> new ArrayList<Entity>(v.getResidents(level)))
                    .orElseGet(ArrayList::new);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA loadedVillageResidents failed; defaulting empty", t);
            return new ArrayList<>();
        }
    }

    /**
     * The full resident UUID set of a village — <b>independent of chunk loading</b>, so unloaded
     * residents still count. This is what arrival/departure diffing reads, so an unloaded villager is
     * never mistaken for one who left. Safe default: empty set.
     */
    public static Set<UUID> villageResidentUuids(ServerLevel level, int villageId) {
        try {
            return VillageManager.get(level).getOrEmpty(villageId)
                    .map(v -> v.getResidentsUUIDs().collect(java.util.stream.Collectors.toCollection(HashSet::new)))
                    .orElseGet(HashSet::new);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA villageResidentUuids failed; defaulting empty", t);
            return new HashSet<>();
        }
    }

    /** UUID→name for the full residency set (also names unloaded residents). Safe default: empty map. */
    public static Map<UUID, String> villageResidentNames(ServerLevel level, int villageId) {
        try {
            return VillageManager.get(level).getOrEmpty(villageId)
                    .map(v -> new HashMap<>(v.getResidentNames()))
                    .orElseGet(HashMap::new);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("MCA villageResidentNames failed; defaulting empty", t);
            return new HashMap<>();
        }
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
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                Dialogues.getInstance().selectAnswer(mca, player, questionId, answerName);
                return true;
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA selectAnswer({}, {}) failed; ignoring", questionId, answerName, t);
            }
        }
        return false;
    }

    /**
     * Enforces an answer's {@code constraints} the way the GUI does at render time (MCA's
     * {@code selectAnswer} trusts the click and does <em>not</em> re-validate), so chat mode cannot
     * trigger e.g. spouse-only content for a non-spouse. Resolves the {@link Question}/{@link Answer},
     * computes the villager+player's satisfied {@link Constraint} set, and checks the answer accepts it.
     * <b>Fails closed:</b> an unknown binding or any MCA read failure returns false (ineligible), so a
     * compat break degrades to "answer unavailable", never "constraints bypassed".
     */
    public static boolean checkConstraints(Entity villager, ServerPlayer player, String questionId, String answerName) {
        if (villager instanceof VillagerLike<?> v) {
            try {
                Question question = Dialogues.getInstance().getQuestion(questionId);
                if (question == null) {
                    return false;
                }
                Answer answer = question.getAnswer(answerName);
                if (answer == null) {
                    return false;
                }
                Set<Constraint> satisfied = Constraint.allMatching(v, player);
                return answer.isValidForConstraint(satisfied);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA checkConstraints({}, {}) failed; defaulting ineligible",
                        questionId, answerName, t);
            }
        }
        return false;
    }

    /**
     * The UUID of the player currently in a GUI interaction (open screen) with this villager, so chat
     * mode can defer with a busy line instead of driving the engine concurrently. MCA's
     * {@code getInteractingPlayer()} is already filtered to players with an open screen. Fail-open:
     * empty on any failure. <b>Server side only.</b>
     */
    public static Optional<UUID> isInteractingWith(Entity villager) {
        if (villager instanceof VillagerEntityMCA mca) {
            try {
                return mca.getInteractions().getInteractingPlayer()
                        .map(net.minecraft.world.entity.player.Player::getUUID);
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("MCA isInteractingWith failed; defaulting empty", t);
            }
        }
        return Optional.empty();
    }
}
