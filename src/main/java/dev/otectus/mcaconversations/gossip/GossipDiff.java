package dev.otectus.mcaconversations.gossip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * The pure diffing rules that turn "current villager states" vs "last scan's snapshots" into
 * gossip events. Extracted from the detector so the rules are unit-testable without MC.
 */
public final class GossipDiff {

    private GossipDiff() {
    }

    /**
     * One villager's state as observed by the current scan. {@code partner} empty = unmarried.
     */
    public record Observation(UUID uuid, String name, Optional<UUID> partner, boolean isBaby) {
    }

    /** A derived event, minus the bookkeeping (id/village/time) the caller adds. */
    public record Derived(GossipEventType type, UUID aUuid, String aName, Optional<UUID> bUuid, String bName) {
    }

    /**
     * Diffs the current observations of one village against the previous snapshots.
     *
     * <p>Rules:
     * <ul>
     *   <li><b>Marriage</b>: partner went empty→X. Emitted once per couple — only by the
     *       lexicographically smaller UUID when both partners are observed this scan.</li>
     *   <li><b>Divorce</b>: partner went X→empty, unless X is in {@code recentlyDead} (the death
     *       event covers it — a widow is not a divorcee).</li>
     *   <li><b>Birth</b>: a villager never seen before (no snapshot) that is currently a baby.
     *       Existing snapshots (even {@code wasBaby}) never re-emit.</li>
     * </ul>
     */
    public static List<Derived> diff(List<Observation> current,
                                     Map<UUID, RelationshipSnapshot> snapshots,
                                     Set<UUID> recentlyDead,
                                     boolean detectMarriage, boolean detectDivorce, boolean detectBirth) {
        List<Derived> out = new ArrayList<>();
        Map<UUID, Observation> byId = new java.util.HashMap<>();
        for (Observation o : current) {
            byId.put(o.uuid(), o);
        }

        for (Observation o : current) {
            RelationshipSnapshot prev = snapshots.get(o.uuid());

            if (detectBirth && prev == null && o.isBaby()) {
                out.add(new Derived(GossipEventType.BIRTH, o.uuid(), o.name(), Optional.empty(), ""));
            }
            // note: births feed diffResidency's exclusion set so a newborn isn't also an "arrival".

            Optional<UUID> prevPartner = prev == null ? Optional.empty() : prev.partner();

            if (detectMarriage && o.partner().isPresent() && prevPartner.isEmpty()) {
                UUID partner = o.partner().get();
                Observation partnerObs = byId.get(partner);
                // Both observed: only the smaller UUID emits (dedup); partner unobserved: emit here.
                boolean emit = partnerObs == null || o.uuid().compareTo(partner) < 0;
                if (emit) {
                    String partnerName = partnerObs != null ? partnerObs.name()
                            : Optional.ofNullable(snapshots.get(partner)).map(RelationshipSnapshot::name).orElse("");
                    out.add(new Derived(GossipEventType.MARRIAGE, o.uuid(), o.name(), Optional.of(partner), partnerName));
                }
            }

            if (detectDivorce && o.partner().isEmpty() && prevPartner.isPresent()) {
                UUID exPartner = prevPartner.get();
                if (!recentlyDead.contains(exPartner)) {
                    String exName = Optional.ofNullable(byId.get(exPartner)).map(Observation::name)
                            .or(() -> Optional.ofNullable(snapshots.get(exPartner)).map(RelationshipSnapshot::name))
                            .orElse("");
                    out.add(new Derived(GossipEventType.DIVORCE, o.uuid(), o.name(), Optional.of(exPartner), exName));
                }
            }
        }
        return out;
    }

    /**
     * Diffs a village's current resident UUID set against the previous scan's set into
     * {@link GossipEventType#ARRIVAL}/{@link GossipEventType#DEPARTURE} events. Names are looked up in
     * {@code names} (uuid→name), defaulting to {@code ""} when unknown.
     *
     * <ul>
     *   <li><b>Arrival</b>: present now, absent before — excluding {@code births} (a newborn is a BIRTH,
     *       reported by {@link #diff}, not an arrival).</li>
     *   <li><b>Departure</b>: present before, absent now — excluding {@code recentlyDead} (a death is
     *       reported as DEATH; a widow-maker is not a mover).</li>
     * </ul>
     *
     * <p>Unlike {@link #diff}, this reads the load-independent residency set, so an unloaded resident is
     * never mistaken for a departure. The caller must only invoke it once a {@code prior} set exists —
     * the first sighting of a village seeds residency and emits nothing, so discovery never floods
     * arrivals.
     */
    public static List<Derived> diffResidency(Set<UUID> current, Set<UUID> prior,
                                              Map<UUID, String> names,
                                              Set<UUID> recentlyDead, Set<UUID> births,
                                              boolean detectArrival, boolean detectDeparture) {
        List<Derived> out = new ArrayList<>();
        if (detectArrival) {
            for (UUID u : current) {
                if (!prior.contains(u) && !births.contains(u)) {
                    out.add(new Derived(GossipEventType.ARRIVAL, u, names.getOrDefault(u, ""), Optional.empty(), ""));
                }
            }
        }
        if (detectDeparture) {
            for (UUID u : prior) {
                if (!current.contains(u) && !recentlyDead.contains(u)) {
                    out.add(new Derived(GossipEventType.DEPARTURE, u, names.getOrDefault(u, ""), Optional.empty(), ""));
                }
            }
        }
        return out;
    }
}
