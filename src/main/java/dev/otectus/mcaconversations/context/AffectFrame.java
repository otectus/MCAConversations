package dev.otectus.mcaconversations.context;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * A villager's current feeling <em>and the reason for it</em> (spec §15.3).
 *
 * <p>MCA already has a mood. What it does not have is a cause, and a cause is what turns an emotion
 * from a wording filter into a selection input: "anxious" tells the director to soften a line,
 * "anxious <b>because the east gate is still weak</b>" tells it which subject to raise. Without the
 * cause, an acute state can only ever decorate whatever scene a timer happened to pick.
 *
 * <p>Never rolled freely. Every frame is derived from an observed state or a live episode, so an
 * absent cause means an absent frame — not a random one (spec §15.3, §17.2).
 *
 * @param primary   the dominant feeling token; see {@link #PRIMARY_TOKENS}
 * @param secondary an optional single complicating feeling — mixed emotion is two tokens, never a
 *                  numeric psychology model
 * @param intensity {@code mild}, {@code moderate} or {@code strong}; controls openness and initiative
 * @param cause     what produced it, as {@code episode:<id>}, {@code event:<type>} or {@code state:<token>}
 * @param target    who or what it is about — a villager name, {@code village}, {@code player}, {@code self}
 * @param sinceDay  the game day the frame was established, so "still" and "again" can be checked
 */
public record AffectFrame(String primary,
                          Optional<String> secondary,
                          String intensity,
                          Optional<String> cause,
                          Optional<String> target,
                          long sinceDay) {

    /** The closed vocabulary of feelings a frame may carry. */
    public static final Set<String> PRIMARY_TOKENS = Set.of(
            "steady", "anxious", "grieving", "elated", "annoyed", "smitten", "proud", "worn",
            "hopeful", "ashamed", "lonely", "relieved", "wary", "restless");

    public static final Set<String> INTENSITIES = Set.of("mild", "moderate", "strong");

    /** The frame used when nothing acute could be established: honest, unremarkable, uncaused. */
    public static final AffectFrame STEADY =
            new AffectFrame("steady", Optional.empty(), "mild", Optional.empty(), Optional.empty(), 0L);

    public AffectFrame {
        primary = normalize(primary);
        intensity = INTENSITIES.contains(normalize(intensity)) ? normalize(intensity) : "moderate";
        if (!PRIMARY_TOKENS.contains(primary)) {
            primary = "steady";
        }
        secondary = secondary == null ? Optional.empty() : secondary.map(AffectFrame::normalize)
                .filter(PRIMARY_TOKENS::contains)
                .filter(token -> !token.equals("steady"));
        cause = cause == null ? Optional.empty() : cause;
        target = target == null ? Optional.empty() : target;
    }

    /** True when this is anything other than ordinary composure. */
    public boolean isAcute() {
        return !"steady".equals(primary);
    }

    /**
     * True when this feeling forbids inherited humour (spec §20.6).
     *
     * <p>Checked by the scene lints rather than by prose: a peppy villager's joke pool must become
     * ineligible under grief because the <em>state</em> says so, not because an author remembered.
     */
    public boolean forbidsInheritedHumour() {
        return switch (primary) {
            case "grieving", "ashamed", "annoyed", "lonely" -> true;
            default -> "strong".equals(intensity);
        };
    }

    /** How a trace and a fingerprint print this frame. Stable, and free of any translated prose. */
    @Override
    public String toString() {
        return primary
                + secondary.map(s -> "+" + s).orElse("")
                + "/" + intensity
                + cause.map(c -> "(" + c + ")").orElse("");
    }

    private static String normalize(String token) {
        return token == null ? "" : token.trim().toLowerCase(Locale.ROOT);
    }
}
