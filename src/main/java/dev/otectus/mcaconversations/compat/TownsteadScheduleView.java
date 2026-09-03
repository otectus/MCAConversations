package dev.otectus.mcaconversations.compat;

import java.util.List;

/**
 * A villager's Townstead shift schedule (Townstead spec 5.2).
 *
 * <p>{@link #currentActivity()} is what the villager's brain is <em>actually</em> doing and
 * {@link #plannedActivity()} is what their shift table says they should be; the two diverge when
 * something has interrupted them. Both are lowercase {@code work} / {@code meet} / {@code rest} /
 * {@code idle}.
 */
public record TownsteadScheduleView(
        String mode,
        String templateId,
        boolean customShifts,
        boolean nonDefaultCustomShifts,
        int currentTickHour,
        int currentDisplayHour,
        int currentShiftOrdinal,
        String currentActivity,
        String plannedActivity,
        String currentTemplateId,
        List<Integer> shifts,
        List<String> weekDayTemplates) {

    /** The schedule a villager has when Townstead is absent: idle, so nothing is ever deferred. */
    public static final TownsteadScheduleView EMPTY = new TownsteadScheduleView(
            "", "", false, false, 0, 0, 0, "idle", "idle", "", List.of(), List.of());

    public TownsteadScheduleView {
        shifts = List.copyOf(shifts);
        weekDayTemplates = List.copyOf(weekDayTemplates);
    }

    /** True when the villager is doing what their schedule planned for this hour. */
    public boolean onSchedule() {
        return currentActivity.equals(plannedActivity);
    }

    /** True while the villager is on shift, the one state that defers a deep topic. */
    public boolean working() {
        return "work".equals(currentActivity);
    }

    /** True when the villager is off shift and sociable. */
    public boolean meeting() {
        return "meet".equals(currentActivity);
    }

    /** True while the villager is resting. Not the same as sleeping, which MCA owns. */
    public boolean resting() {
        return "rest".equals(currentActivity);
    }
}
