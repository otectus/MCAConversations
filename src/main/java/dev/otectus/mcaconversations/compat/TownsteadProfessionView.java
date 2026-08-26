package dev.otectus.mcaconversations.compat;

import java.util.Set;

/**
 * A villager's Townstead trade and what they have learned of it (Townstead spec 5.2).
 *
 * <p>{@link #level()} is Townstead's own {@code professionLevel}. The snapshot has no field spelled
 * "tier", and inventing one here would hand pack authors a number Townstead never promised.
 *
 * <p>{@link #skills()} is read-only in the strongest sense: only Townstead's {@code learned} and
 * {@code has} members are bound at all, so there is no path from a conversation to teaching or
 * un-teaching a skill even by mistake.
 */
public record TownsteadProfessionView(
        String professionId,
        int level,
        int xp,
        Set<String> skills) {

    public static final TownsteadProfessionView EMPTY =
            new TownsteadProfessionView("", 0, 0, Set.of());

    public TownsteadProfessionView {
        skills = Set.copyOf(skills);
    }

    /** True when this villager has a Townstead profession at all (players and the jobless do not). */
    public boolean employed() {
        return !professionId.isEmpty();
    }

    /** True when the villager has learned the named skill. Ids are namespaced and matched exactly. */
    public boolean hasSkill(String skillId) {
        return skillId != null && skills.contains(skillId);
    }
}
