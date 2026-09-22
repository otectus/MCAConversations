package dev.otectus.mcaconversations.compat;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Everything Townstead can say about one villager, read at most once per evaluation (Townstead spec
 * §7.1).
 *
 * <p>MCA scores many candidate results for a single click, and a scene evaluation reads several
 * conditions; each would otherwise cross the reflective bridge again. A snapshot memoises every part
 * the first time it is asked for and never twice, so an evaluation that only asks about needs never
 * pays for the village spirit. Each part is the matching view's {@code EMPTY} when Townstead is
 * absent, switched off, or did not bind the capability that part needs — never {@code null}.
 */
public final class TownsteadSnapshot {

    public static final TownsteadSnapshot EMPTY = new TownsteadSnapshot(false,
            () -> TownsteadVillagerView.EMPTY, () -> TownsteadCalendarView.EMPTY,
            () -> TownsteadBuildingView.EMPTY, () -> TownsteadRootView.EMPTY,
            () -> TownsteadSpiritView.EMPTY, Set::of);

    private final boolean live;
    private final Memo<TownsteadVillagerView> villager;
    private final Memo<TownsteadCalendarView> calendar;
    private final Memo<TownsteadBuildingView> building;
    private final Memo<TownsteadRootView> origin;
    private final Memo<TownsteadSpiritView> spirit;
    private final Memo<Set<String>> tags;

    public TownsteadSnapshot(boolean live,
                             Supplier<TownsteadVillagerView> villager,
                             Supplier<TownsteadCalendarView> calendar,
                             Supplier<TownsteadBuildingView> building,
                             Supplier<TownsteadRootView> origin,
                             Supplier<TownsteadSpiritView> spirit,
                             Supplier<Set<String>> tags) {
        this.live = live;
        this.villager = new Memo<>(villager, TownsteadVillagerView.EMPTY);
        this.calendar = new Memo<>(calendar, TownsteadCalendarView.EMPTY);
        this.building = new Memo<>(building, TownsteadBuildingView.EMPTY);
        this.origin = new Memo<>(origin, TownsteadRootView.EMPTY);
        this.spirit = new Memo<>(spirit, TownsteadSpiritView.EMPTY);
        this.tags = new Memo<>(tags, Set.of());
    }

    /** A snapshot whose parts are all already known — the test seam and the eager path. */
    public static TownsteadSnapshot of(TownsteadVillagerView villager, TownsteadCalendarView calendar,
                                       TownsteadBuildingView building, TownsteadRootView origin,
                                       TownsteadSpiritView spirit, Set<String> tags) {
        return new TownsteadSnapshot(true, () -> villager, () -> calendar, () -> building,
                () -> origin, () -> spirit, () -> tags);
    }

    /** False for {@link #EMPTY}: Townstead absent, switched off, or no villager to ask about. */
    public boolean live() {
        return live;
    }

    public TownsteadVillagerView villager() {
        return villager.get();
    }

    public TownsteadCalendarView calendar() {
        return calendar.get();
    }

    /** The registered building the villager is standing in; {@code present() == false} outside one. */
    public TownsteadBuildingView building() {
        return building.get();
    }

    /** The villager's root (species, ancestry, lineage). */
    public TownsteadRootView origin() {
        return origin.get();
    }

    /** The villager's home village spirit. */
    public TownsteadSpiritView spirit() {
        return spirit.get();
    }

    /** Townstead's full resolved context-tag set for the villager. */
    public Set<String> tags() {
        return tags.get();
    }

    /** Memoises one part; a supplier that throws or answers null yields the part's empty value. */
    private static final class Memo<T> {
        private final Supplier<T> supplier;
        private final T empty;
        private T value;
        private boolean done;

        Memo(Supplier<T> supplier, T empty) {
            this.supplier = supplier;
            this.empty = empty;
        }

        synchronized T get() {
            if (!done) {
                done = true;
                try {
                    T read = supplier.get();
                    value = read == null ? empty : read;
                } catch (Throwable t) {
                    value = empty;
                }
            }
            return value;
        }
    }
}
