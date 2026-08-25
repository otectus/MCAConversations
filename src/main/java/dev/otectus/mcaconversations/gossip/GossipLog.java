package dev.otectus.mcaconversations.gossip;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * The in-memory gossip event store. Pure logic (no MC/MCA types beyond nothing at all) so every
 * rule here is unit-tested: per-village cap, same-subjects dedup, retention pruning, and the
 * deterministic newest-first query shared by the {@code conversations_gossip} condition and the
 * {@code conversations_gossip_say} action (they must agree on which event is "next").
 */
public final class GossipLog {

    private final List<GossipEvent> events = new ArrayList<>();

    /** Immutable view, insertion order. */
    public List<GossipEvent> events() {
        return List.copyOf(events);
    }

    public int size() {
        return events.size();
    }

    /**
     * Adds an event unless an event of the same type with the same subject set already exists in
     * the village (any age — retention pruning is what re-opens the door). Enforces the per-village
     * cap by dropping the oldest first. Returns true when added.
     */
    public boolean add(GossipEvent event, int maxPerVillage) {
        boolean duplicate = events.stream().anyMatch(e ->
                e.type() == event.type()
                        && e.villageId() == event.villageId()
                        && sameSubjects(e, event));
        if (duplicate) {
            return false;
        }
        events.add(event);
        List<GossipEvent> village = events.stream()
                .filter(e -> e.villageId() == event.villageId())
                .sorted(Comparator.comparingLong(GossipEvent::created))
                .toList();
        for (int i = 0; i < village.size() - maxPerVillage; i++) {
            events.remove(village.get(i));
        }
        return true;
    }

    private static boolean sameSubjects(GossipEvent a, GossipEvent b) {
        Set<UUID> sa = subjectSet(a);
        Set<UUID> sb = subjectSet(b);
        return sa.equals(sb);
    }

    private static Set<UUID> subjectSet(GossipEvent e) {
        return e.bUuid().map(bu -> Set.of(e.aUuid(), bu)).orElseGet(() -> Set.of(e.aUuid()));
    }

    /** Drops events older than {@code retentionTicks}. Returns how many were removed. */
    public int pruneOlderThan(long now, long retentionTicks) {
        int before = events.size();
        events.removeIf(e -> now - e.created() > retentionTicks);
        return before - events.size();
    }

    /**
     * The next event a villager would tell: newest first, in the given village, of an allowed type,
     * within {@code maxAgeTicks}, not involving the teller, and not flagged told. Deterministic so
     * the gossip condition and the gossip say action always pick the same event.
     *
     * @param types       allowed types; empty set = all types
     * @param alreadyTold per-(teller,listener) told-check, backed by villager LongTermMemory
     */
    public Optional<GossipEvent> query(int villageId, Set<GossipEventType> types, long now, long maxAgeTicks,
                                       UUID tellerUuid, Predicate<GossipEvent> alreadyTold) {
        return events.stream()
                .filter(e -> e.villageId() == villageId)
                .filter(e -> types.isEmpty() || types.contains(e.type()))
                .filter(e -> now - e.created() <= maxAgeTicks)
                .filter(e -> !e.involves(tellerUuid))
                .filter(e -> !alreadyTold.test(e))
                .max(Comparator.comparingLong(GossipEvent::created)
                        .thenComparing(e -> e.id().toString()));
    }

    public void clear() {
        events.clear();
    }

    /** Restores from persisted order (used by SavedData load). */
    public void addRaw(GossipEvent event) {
        events.add(event);
    }
}
