package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import forge.net.mca.cobalt.network.NetworkHandler;
import forge.net.mca.entity.VillagerEntityMCA;
import forge.net.mca.entity.VillagerLike;
import forge.net.mca.entity.ai.Memories;
import forge.net.mca.entity.ai.relationship.AgeState;
import forge.net.mca.entity.ai.relationship.EntityRelationship;
import forge.net.mca.network.s2c.InteractionDialogueQuestionResponse;
import forge.net.mca.server.world.data.FamilyTree;
import forge.net.mca.server.world.data.FamilyTreeNode;
import forge.net.mca.server.world.data.Village;
import forge.net.mca.server.world.data.VillageManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * The single point of contact with Minecraft Comes Alive: Reborn (together with the
 * {@code compat.mca} adapter package, which implements MCA functional interfaces).
 *
 * <p><b>Why {@code forge.net.mca.*} and not {@code net.mca.*}?</b> MCA Reborn ships a Forgix-merged
 * "Universal" jar (Forge + Fabric + Quilt). Forgix relocates each loader's classes under a
 * loader-named root package, so the Forge classes are physically {@code forge.net.mca.*} in both
 * the production jar and our dev-remapped (deobf) jar.
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
            NetworkHandler.sendToPlayer(new InteractionDialogueQuestionResponse(false, line.get()), player);
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
}
