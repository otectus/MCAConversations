package dev.otectus.mcaconversations.compat;

import com.google.gson.JsonElement;
import net.minecraft.world.entity.Entity;

import java.util.List;

/**
 * The five Townstead dialogue conditions (Townstead spec §8), parsed and tested in one place so the
 * MCA registrar and {@code /conversations compat townstead explain} can never disagree about what a
 * condition means.
 *
 * <p>Every test is {@code false} unless Townstead is live and its context conditions are switched on,
 * which is what makes a Townstead branch fall through to its authored fallback on an install
 * without it.
 */
public final class TownsteadConditions {

    public static final String AVAILABLE = "conversations_townstead_available";
    public static final String QUERY = "conversations_townstead";
    public static final String TAGS = "conversations_townstead_tags";
    public static final String SPIRIT = "conversations_townstead_spirit";
    public static final String SKILL = "conversations_townstead_skill";

    public static final List<String> TYPES = List.of(AVAILABLE, QUERY, TAGS, SPIRIT, SKILL);

    private TownsteadConditions() {
    }

    /**
     * Parses one condition body. Throws {@link IllegalArgumentException} for a malformed body or an
     * unknown type, which the registrar turns into a refused entry.
     */
    public static Object parse(String type, JsonElement json) {
        return switch (type) {
            case AVAILABLE -> TownsteadAvailableQuery.fromJson(json);
            case QUERY -> TownsteadQuery.fromJson(json);
            case TAGS -> TownsteadTagQuery.fromJson(json);
            case SPIRIT -> TownsteadSpiritQuery.fromJson(json);
            case SKILL -> TownsteadSkillQuery.fromJson(json);
            default -> throw new IllegalArgumentException("not a Townstead condition: " + type);
        };
    }

    /** True when a parsed condition holds for this villager. {@code false} for a null query. */
    public static boolean test(Object query, Entity villager) {
        if (query == null || !Townstead.conditionsEnabled()) {
            return false;
        }
        if (query instanceof TownsteadAvailableQuery available) {
            return available.matches(Townstead.bridge().capabilities());
        }
        TownsteadSnapshot snapshot = Townstead.snapshot(villager);
        if (query instanceof TownsteadQuery field) {
            return field.matches(snapshot);
        }
        if (query instanceof TownsteadTagQuery tags) {
            return tags.matches(snapshot.tags());
        }
        if (query instanceof TownsteadSpiritQuery spirit) {
            return spirit.matches(snapshot.spirit());
        }
        if (query instanceof TownsteadSkillQuery skill) {
            return skill.matches(snapshot.villager().profession().skills());
        }
        return false;
    }
}
