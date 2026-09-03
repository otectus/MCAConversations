package dev.otectus.mcaconversations.village;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * The culture tokens a datapack has offered, indexed by family (spec §17.3).
 *
 * <p>Immutable and rebuilt wholesale on reload, like every other catalog here. The one thing it does
 * beyond lookup is keep each family's list in a stable order: a village's culture is generated from a
 * seed, so if the order of the candidates changed between two loads of the same world the village
 * would quietly become a different village. Sorting by id is what makes the generation reproducible
 * across a datapack that merely got reordered.
 */
public final class VillageCultureCatalog {

    public static final VillageCultureCatalog EMPTY =
            new VillageCultureCatalog(Map.of(), Map.of());

    private final Map<String, CultureToken> byId;
    private final Map<CultureFamily, List<CultureToken>> byFamily;
    private final Map<String, String> aliases;

    public VillageCultureCatalog(Map<String, CultureToken> tokens, Map<String, String> aliases) {
        Map<String, CultureToken> ids = new LinkedHashMap<>();
        Map<CultureFamily, List<CultureToken>> families = new EnumMap<>(CultureFamily.class);
        if (tokens != null) {
            for (CultureToken token : tokens.values()) {
                if (token == null || !token.isWellFormed()) {
                    continue;
                }
                ids.put(token.id(), token);
                families.computeIfAbsent(token.family(), key -> new ArrayList<>()).add(token);
            }
        }
        for (List<CultureToken> list : families.values()) {
            list.sort(Comparator.comparing(CultureToken::id));
        }
        Map<CultureFamily, List<CultureToken>> frozen = new EnumMap<>(CultureFamily.class);
        families.forEach((family, list) -> frozen.put(family, List.copyOf(list)));
        this.byId = Map.copyOf(ids);
        this.byFamily = Collections.unmodifiableMap(frozen);
        this.aliases = aliases == null ? Map.of() : Map.copyOf(aliases);
    }

    public boolean isEmpty() {
        return byId.isEmpty();
    }

    public int size() {
        return byId.size();
    }

    /** A token by id, following the alias table so a renamed token keeps its villages. */
    public Optional<CultureToken> token(String id) {
        if (id == null) {
            return Optional.empty();
        }
        String key = id.trim().toLowerCase(java.util.Locale.ROOT);
        CultureToken direct = byId.get(key);
        if (direct != null) {
            return Optional.of(direct);
        }
        String aliased = aliases.get(key);
        return aliased == null ? Optional.empty() : Optional.ofNullable(byId.get(aliased));
    }

    /** Candidates for one family, in a stable order, filtered to what this install can offer. */
    public List<CultureToken> candidates(CultureFamily family, Predicate<String> modPresent) {
        List<CultureToken> all = byFamily.getOrDefault(family, List.of());
        if (all.isEmpty()) {
            return List.of();
        }
        List<CultureToken> available = new ArrayList<>(all.size());
        for (CultureToken token : all) {
            if (isAvailable(token, modPresent)) {
                available.add(token);
            }
        }
        return List.copyOf(available);
    }

    /** True when every family has at least one candidate, so a whole culture can be generated. */
    public boolean isComplete(Predicate<String> modPresent) {
        for (CultureFamily family : CultureFamily.values()) {
            if (candidates(family, modPresent).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAvailable(CultureToken token, Predicate<String> modPresent) {
        for (String integration : token.integrations()) {
            if (modPresent == null || !modPresent.test(integration)) {
                return false;
            }
        }
        return true;
    }
}
