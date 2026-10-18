package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.Locale;
import java.util.Optional;

/**
 * Something the speaker's own family must include: {@code {"has": "children"}} (feedback remediation
 * WP-C2). Read from MCA's family tree by UUID through {@link McaCompat}, so a villager's children are
 * the ones MCA recorded, loaded or not.
 *
 * <p>One shape in two places: the {@code conversations_family} dialogue condition, and a catalog row's
 * {@code family} field, which {@link TopicGate} enforces on every entry path so a topic about the
 * speaker's children is never offered to — or typed at — a villager who has none. Fails closed: a
 * family tree that cannot be read has nobody in it.
 */
public record FamilyRequirement(Relation has) {

    /** Who the speaker must have. */
    public enum Relation {
        CHILDREN("children"), PARENTS("parents"), SIBLINGS("siblings");

        private final String key;

        Relation(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        static Optional<Relation> byKey(String key) {
            String wanted = key == null ? "" : key.trim().toLowerCase(Locale.ROOT);
            for (Relation relation : values()) {
                if (relation.key.equals(wanted)) {
                    return Optional.of(relation);
                }
            }
            return Optional.empty();
        }
    }

    public FamilyRequirement {
        if (has == null) {
            throw new IllegalArgumentException("a family requirement names who the speaker must have");
        }
    }

    /** {@code {"has": "children"}}. Strict: anything else is refused, never read as satisfied. */
    public static FamilyRequirement fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("family requirement must be an object like {\"has\": \"children\"}");
        }
        JsonObject json = element.getAsJsonObject();
        if (!json.has("has") || !json.get("has").isJsonPrimitive() || json.size() != 1) {
            throw new IllegalArgumentException("family requirement takes exactly one field, \"has\"");
        }
        String has = json.get("has").getAsString();
        return new FamilyRequirement(Relation.byKey(has).orElseThrow(() -> new IllegalArgumentException(
                "family requirement \"has\" must be one of children, parents, siblings (got '" + has + "')")));
    }

    /** Pure: whether a family with these counts satisfies the requirement. */
    public boolean satisfiedBy(int children, int parents, int siblings) {
        return switch (has) {
            case CHILDREN -> children > 0;
            case PARENTS -> parents > 0;
            case SIBLINGS -> siblings > 0;
        };
    }

    /** Whether this villager's recorded family satisfies the requirement. False when unreadable. */
    public boolean test(Entity villager) {
        if (villager == null || !(villager.level() instanceof ServerLevel level)) {
            return false;
        }
        try {
            return switch (has) {
                case CHILDREN -> !McaCompat.getChildren(level, villager.getUUID()).isEmpty();
                case PARENTS -> !McaCompat.getParents(level, villager.getUUID()).isEmpty();
                case SIBLINGS -> !McaCompat.getSiblings(level, villager.getUUID()).isEmpty();
            };
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("family requirement unreadable; treating it as unmet", t);
            return false;
        }
    }
}
