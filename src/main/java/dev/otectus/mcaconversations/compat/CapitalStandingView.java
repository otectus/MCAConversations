package dev.otectus.mcaconversations.compat;

/**
 * Where one villager stands inside one capital: their title, their office, their standing with the
 * Crown, and the house they belong to.
 *
 * <p>Every string is lower-case and neutral rather than null. {@code titleId} is {@code none} for a
 * villager with no title at all, {@code office} is {@code none} for one holding no secondary court
 * office, and {@code crownStanding} is {@code unknown} only when the standing service did not bind —
 * a villager the Crown has no opinion of is {@code neutral}.
 */
public record CapitalStandingView(String titleId,
                                  int titleRank,
                                  String displayTitle,
                                  String office,
                                  String crownStanding,
                                  boolean royalHousehold,
                                  boolean royalGuard,
                                  boolean disgraced,
                                  String houseName,
                                  String houseTier,
                                  String houseWords,
                                  String surname) {

    private static final CapitalStandingView NONE = new CapitalStandingView(
            "none", 0, "", "none", "unknown", false, false, false, "", "", "", "");

    /** The standing of a villager in no capital, and the answer whenever a read fails. */
    public static CapitalStandingView none() {
        return NONE;
    }

    /** True when this villager holds a house of any tier. */
    public boolean hasHouse() {
        return !houseName.isEmpty();
    }
}
