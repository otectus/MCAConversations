package dev.otectus.mcaconversations.compat;

/**
 * A villager's Townstead needs, normalised into Conversations' own vocabulary (Townstead spec 5.2).
 *
 * <p>Field-for-field Townstead's public {@code TownsteadNeedsSnapshot}, plus the three semantic
 * buckets the spec asks for. Dialogue should say "you look exhausted", never "your fatigue is 18", so
 * a line is authored against a band and stays correct if the numbers move underneath it.
 *
 * <h2>The three scales are not the same scale</h2>
 *
 * <p>Worth knowing before writing content against this. Townstead runs hunger over <b>0 to 100</b>
 * and thirst and fatigue over <b>0 to 20</b>, so a raw comparison between them is meaningless and a
 * threshold copied from one to another is a bug. {@link #primaryNeed()} exists so content never has
 * to make that comparison itself.
 *
 * <h2>Where the band names and boundaries come from</h2>
 *
 * <p>Both are Townstead's, not ours. The names are its own {@code HungerState}, {@code ThirstState}
 * and {@code FatigueState} vocabularies lowercased, and every boundary is one of its published
 * threshold constants. Reusing them rather than inventing a parallel set is the same rule the spec
 * applies to context tags: a partial duplicate would drift from what the other mod means.
 * {@code TownsteadBindingProbeTest} pins every constant below against the real jar, so a retune
 * upstream fails a test instead of quietly rewriting what a bundled wellbeing line claims.
 *
 * <p>One gap is deliberate: Townstead distinguishes {@code RESTED} from {@code ALERT}, but publishes
 * no constant for the boundary between them, so {@link #fatigueBucket()} reports {@code rested} for
 * both rather than guessing where it falls.
 *
 * <p>Note also that {@link #fatigue()} runs the opposite way to the other two, higher being
 * <em>more</em> tired, which is why {@link #energy()} exists for lines that read better rising.
 */
public record TownsteadNeedsView(
        int hunger,
        float saturation,
        float hungerExhaustion,
        int thirst,
        int quenched,
        float thirstExhaustion,
        int fatigue,
        boolean collapsed,
        boolean gated) {

    /** Townstead {@code HungerData.MAX_HUNGER}. */
    public static final int MAX_HUNGER = 100;
    /** Townstead {@code ThirstData.MAX_THIRST}. */
    public static final int MAX_THIRST = 20;
    /** Townstead {@code FatigueData.MAX_FATIGUE}, and so the zero point for {@link #energy()}. */
    public static final int MAX_FATIGUE = 20;

    /** Townstead {@code HungerData.EMERGENCY_THRESHOLD}. */
    public static final int HUNGER_EMERGENCY = 25;
    /** Townstead {@code HungerData.ADEQUATE_THRESHOLD}. */
    public static final int HUNGER_ADEQUATE = 50;
    /** Townstead {@code HungerData.DINNER_THRESHOLD}. */
    public static final int HUNGER_DINNER = 60;
    /** Townstead {@code HungerData.BREAKFAST_THRESHOLD}. */
    public static final int HUNGER_BREAKFAST = 80;

    /** Townstead {@code ThirstData.EMERGENCY_THRESHOLD}. */
    public static final int THIRST_EMERGENCY = 4;
    /** Townstead {@code ThirstData.ADEQUATE_THRESHOLD}. */
    public static final int THIRST_ADEQUATE = 12;
    /** Townstead {@code ThirstData.BREAKFAST_THRESHOLD}. */
    public static final int THIRST_BREAKFAST = 16;
    /** Townstead {@code ThirstData.SATIETY_THRESHOLD}. */
    public static final int THIRST_SATIETY = 18;

    /** Townstead {@code FatigueData.TIRED_THRESHOLD}. */
    public static final int FATIGUE_TIRED = 8;
    /** Townstead {@code FatigueData.DROWSY_THRESHOLD}. */
    public static final int FATIGUE_DROWSY = 12;
    /** Townstead {@code FatigueData.EXHAUSTED_THRESHOLD}. */
    public static final int FATIGUE_EXHAUSTED = 16;

    /** The needs a villager has when Townstead is absent or the read failed: nothing is wrong. */
    public static final TownsteadNeedsView EMPTY = new TownsteadNeedsView(
            MAX_HUNGER, 0f, 0f, MAX_THIRST, MAX_THIRST, 0f, 0, false, true);

    /** Rested-ness on a rising scale: {@link #MAX_FATIGUE} when fully rested, {@code 0} when spent. */
    public int energy() {
        return MAX_FATIGUE - fatigue;
    }

    /** True when thirst is actually being simulated: Townstead gates it behind a thirst mod. */
    public boolean thirstActive() {
        return !gated;
    }

    /** {@code starving} / {@code famished} / {@code hungry} / {@code adequate} / {@code well_fed}. */
    public String hungerBucket() {
        if (hunger <= HUNGER_EMERGENCY) {
            return "starving";
        }
        if (hunger < HUNGER_ADEQUATE) {
            return "famished";
        }
        if (hunger < HUNGER_DINNER) {
            return "hungry";
        }
        return hunger < HUNGER_BREAKFAST ? "adequate" : "well_fed";
    }

    /**
     * {@code dehydrated} / {@code parched} / {@code thirsty} / {@code hydrated} / {@code quenched},
     * and always {@code quenched} when Townstead is not simulating thirst at all. Content must not
     * pity a villager for a need that does not exist on this install.
     */
    public String thirstBucket() {
        if (!thirstActive()) {
            return "quenched";
        }
        if (thirst <= THIRST_EMERGENCY) {
            return "dehydrated";
        }
        if (thirst < THIRST_ADEQUATE) {
            return "parched";
        }
        if (thirst < THIRST_BREAKFAST) {
            return "thirsty";
        }
        return thirst < THIRST_SATIETY ? "hydrated" : "quenched";
    }

    /**
     * {@code exhausted} / {@code drowsy} / {@code tired} / {@code rested}. See the class javadoc for
     * why Townstead's {@code ALERT} is folded into {@code rested}.
     */
    public String fatigueBucket() {
        if (fatigue >= FATIGUE_EXHAUSTED) {
            return "exhausted";
        }
        if (fatigue >= FATIGUE_DROWSY) {
            return "drowsy";
        }
        return fatigue >= FATIGUE_TIRED ? "tired" : "rested";
    }

    /**
     * The one need worth speaking about first, or {@code none} when nothing is pressing.
     *
     * <p>Compared as fractions of each need's own ceiling, because the three scales differ: comparing
     * the raw numbers would make a villager on 30 hunger out of 100 look better off than one on 15
     * thirst out of 20. Collapse outranks everything, and hunger breaks ties because it is the need a
     * player can most obviously do something about.
     */
    public String primaryNeed() {
        if (collapsed) {
            return "collapsed";
        }
        float hungerShare = (float) hunger / MAX_HUNGER;
        float thirstShare = thirstActive() ? (float) thirst / MAX_THIRST : 1f;
        float energyShare = (float) energy() / MAX_FATIGUE;
        float lowest = Math.min(hungerShare, Math.min(thirstShare, energyShare));
        if (lowest >= 0.5f) {
            return "none";
        }
        if (hungerShare == lowest) {
            return "hunger";
        }
        return thirstShare == lowest ? "thirst" : "fatigue";
    }

    /** True when any need has reached a band content should treat as an emergency. */
    public boolean inCrisis() {
        return collapsed
                || hunger <= HUNGER_EMERGENCY
                || fatigue >= FATIGUE_EXHAUSTED
                || (thirstActive() && thirst <= THIRST_EMERGENCY);
    }
}
