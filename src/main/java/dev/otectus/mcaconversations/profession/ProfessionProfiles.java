package dev.otectus.mcaconversations.profession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

/**
 * The compiled index of every declared profession profile (spec §6.10, §7.2).
 *
 * <p>Built once per reload and read on a click, so every lookup here is a map hit. The interesting
 * method is {@link #forId(String, String)}: it always answers. A profession this mod has never heard
 * of — a third-party trade from a mod installed after this one shipped — gets a generic profile
 * built from its display name, which is enough for a coherent conversation that makes no detailed
 * claims (spec §14.2). The alternative, silence or a crash, is worse than a villager who will only
 * say general things about being a chandler.
 */
public final class ProfessionProfiles {

    public static final ProfessionProfiles EMPTY = new ProfessionProfiles(List.of());

    private final Map<String, ProfessionProfile> byId;
    private final Map<WorkArchetype, List<ProfessionProfile>> byArchetype;

    private ProfessionProfiles(Collection<ProfessionProfile> profiles) {
        Map<String, ProfessionProfile> ids = new TreeMap<>();
        Map<WorkArchetype, List<ProfessionProfile>> archetypes = new LinkedHashMap<>();
        for (ProfessionProfile profile : profiles) {
            ids.put(profile.id(), profile);
            archetypes.computeIfAbsent(profile.archetype(), k -> new ArrayList<>()).add(profile);
        }
        Map<WorkArchetype, List<ProfessionProfile>> frozen = new LinkedHashMap<>();
        archetypes.forEach((key, value) -> frozen.put(key, List.copyOf(value)));
        this.byId = Map.copyOf(ids);
        this.byArchetype = Map.copyOf(frozen);
    }

    public static ProfessionProfiles build(Collection<ProfessionProfile> profiles) {
        Map<String, String> seen = new LinkedHashMap<>();
        for (ProfessionProfile profile : profiles) {
            if (seen.put(profile.id(), profile.id()) != null) {
                throw new IllegalArgumentException("duplicate profession profile '" + profile.id() + "'");
            }
        }
        return new ProfessionProfiles(profiles);
    }

    /** The declared profile for {@code id}, if this mod or a datapack ships one. */
    public Optional<ProfessionProfile> declared(String id) {
        // Map.copyOf rejects a null key outright, and a villager with no profession is an ordinary
        // thing to be asked about — answer it rather than throwing on the dialogue thread.
        return id == null ? Optional.empty() : Optional.ofNullable(byId.get(id));
    }

    /**
     * A profile for {@code id}, always. Falls back to a generic craft profile carrying only the
     * display name, so an unknown trade is discussed without inventing details about it.
     */
    public ProfessionProfile forId(String id, String displayFallback) {
        ProfessionProfile declared = declared(id).orElse(null);
        if (declared != null) {
            return declared;
        }
        return ProfessionProfile.generic(id == null ? "unknown:unknown" : id,
                displayFallback == null || displayFallback.isBlank() ? "villager" : displayFallback);
    }

    public List<ProfessionProfile> ofArchetype(WorkArchetype archetype) {
        return byArchetype.getOrDefault(archetype, List.of());
    }

    public Collection<ProfessionProfile> all() {
        return byId.values();
    }

    public Set<String> ids() {
        return byId.keySet();
    }

    /** Profiles that need no optional mod — the set that must always be complete. */
    public List<ProfessionProfile> base() {
        return byId.values().stream().filter(ProfessionProfile::isBase).toList();
    }

    public int size() {
        return byId.size();
    }

    public boolean isEmpty() {
        return byId.isEmpty();
    }
}
