package dev.otectus.mcaconversations.compat;

/**
 * Where a villager stands in a Townstead life cycle (Townstead spec 5.2).
 *
 * <p>Deliberately carries a {@link #fertilityPresent()} <em>flag</em> rather than Townstead's raw
 * fertility figure. Spec 15.2 forbids shipping fertility as gossip and 20 makes it admin-only, and a
 * value that must never be spoken has no business in the record every template variable reads.
 *
 * <p>{@link #ageless()} and {@link #immortal()} are narrative flavour only. They are never a proxy
 * for MCA adulthood: every existing romance and age gate keeps running on MCA's own age state, and
 * Townstead data may only ever make those gates stricter.
 */
public record TownsteadLifeView(
        String rootId,
        String lifeStage,
        long biologicalAgeDays,
        int apparentAgeYears,
        boolean immortal,
        boolean ageless,
        boolean senior,
        boolean fertilityPresent) {

    public static final TownsteadLifeView EMPTY =
            new TownsteadLifeView("", "", 0L, 0, false, false, false, false);

    /**
     * A coarse narrative band for a line that wants to speak about a stage without naming a datapack
     * id: {@code child}, {@code young}, {@code adult}, {@code senior} or {@code ageless}.
     */
    public String ageDescription() {
        if (ageless || immortal) {
            return "ageless";
        }
        if (senior) {
            return "senior";
        }
        if (apparentAgeYears <= 0) {
            return "adult";
        }
        if (apparentAgeYears < 16) {
            return "child";
        }
        return apparentAgeYears < 30 ? "young" : "adult";
    }
}
