package dev.otectus.mcaconversations.identity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

/**
 * Every identity token the running build knows about, plus the alias table that keeps existing
 * profiles valid when one is renamed (spec §6.4, §22.3).
 *
 * <p>Built once per datapack reload and then read-only, so generation and every profile condition see
 * a consistent world. Tokens are indexed by family in a stable order because generation walks that
 * order with a seeded RNG: a map iteration whose order varied between runs would make the same seed
 * produce different villagers, which is precisely what {@code IdentityProfileDeterminismTest} exists
 * to forbid.
 *
 * <p>Conflicts are stored <b>symmetrically</b>. A datapack declaring {@code solitary conflicts
 * collaborative} does not also have to declare the reverse, and cannot get it half-right.
 */
public final class IdentityCatalog {

    public static final IdentityCatalog EMPTY = new IdentityCatalog(List.of(), Map.of());

    private final Map<String, IdentityToken> byQualifiedId;
    private final Map<IdentityFamily, List<IdentityToken>> byFamily;
    private final Map<String, Set<String>> conflicts;
    private final Map<String, String> aliases;

    private IdentityCatalog(Collection<IdentityToken> tokens, Map<String, String> aliases) {
        Map<String, IdentityToken> ids = new TreeMap<>();
        Map<IdentityFamily, List<IdentityToken>> families = new EnumMap<>(IdentityFamily.class);
        for (IdentityToken token : tokens) {
            ids.put(token.qualifiedId(), token);
        }
        // Sorted by qualified id so the iteration order a seeded pick walks is identical on every run.
        for (IdentityToken token : ids.values()) {
            families.computeIfAbsent(token.family(), key -> new ArrayList<>()).add(token);
        }
        Map<IdentityFamily, List<IdentityToken>> frozen = new EnumMap<>(IdentityFamily.class);
        families.forEach((family, list) -> frozen.put(family, List.copyOf(list)));

        Map<String, Set<String>> symmetric = new TreeMap<>();
        for (IdentityToken token : ids.values()) {
            for (String other : token.conflicts()) {
                symmetric.computeIfAbsent(token.id(), key -> new java.util.TreeSet<>()).add(other);
                symmetric.computeIfAbsent(other, key -> new java.util.TreeSet<>()).add(token.id());
            }
        }
        Map<String, Set<String>> frozenConflicts = new TreeMap<>();
        symmetric.forEach((id, others) -> frozenConflicts.put(id, Set.copyOf(others)));

        this.byQualifiedId = Map.copyOf(ids);
        this.byFamily = Map.copyOf(frozen);
        this.conflicts = Map.copyOf(frozenConflicts);
        this.aliases = Map.copyOf(aliases);
    }

    public static IdentityCatalog build(Collection<IdentityToken> tokens, Map<String, String> aliases) {
        Map<String, String> seen = new LinkedHashMap<>();
        for (IdentityToken token : tokens) {
            if (seen.put(token.qualifiedId(), token.id()) != null) {
                throw new IllegalArgumentException("duplicate identity token '" + token.qualifiedId() + "'");
            }
        }
        Map<String, String> normalizedAliases = new TreeMap<>();
        aliases.forEach((from, to) -> normalizedAliases.put(
                from.trim().toLowerCase(Locale.ROOT), to.trim().toLowerCase(Locale.ROOT)));
        return new IdentityCatalog(tokens, normalizedAliases);
    }

    public boolean isEmpty() {
        return byQualifiedId.isEmpty();
    }

    public int size() {
        return byQualifiedId.size();
    }

    /** Every token of one family, in stable id order. */
    public List<IdentityToken> family(IdentityFamily family) {
        return byFamily.getOrDefault(family, List.of());
    }

    /** Every token, in qualified-id order — the order generation and the reports both walk. */
    public List<IdentityToken> all() {
        List<IdentityToken> out = new ArrayList<>(byQualifiedId.values());
        out.sort(java.util.Comparator.comparing(IdentityToken::qualifiedId));
        return List.copyOf(out);
    }

    /** Looks a token up by {@code family:id}, following the alias table first. */
    public Optional<IdentityToken> token(IdentityFamily family, String id) {
        if (family == null || id == null) {
            return Optional.empty();
        }
        String resolved = resolveAlias(id.trim().toLowerCase(Locale.ROOT));
        return Optional.ofNullable(byQualifiedId.get(family.key() + ":" + resolved));
    }

    /**
     * Follows the alias chain to a current token id.
     *
     * <p>Bounded to eight hops so a datapack that writes a cycle produces a stuck-but-harmless id
     * rather than hanging the reload thread.
     */
    public String resolveAlias(String id) {
        String current = id;
        for (int hop = 0; hop < 8; hop++) {
            String next = aliases.get(current);
            if (next == null || next.equals(current)) {
                return current;
            }
            current = next;
        }
        return current;
    }

    /** True when these two token ids may not be held by the same villager. */
    public boolean conflict(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        return conflicts.getOrDefault(a, Set.of()).contains(b);
    }

    /** Everything that conflicts with {@code id}, both directions merged. */
    public Set<String> conflictsWith(String id) {
        return conflicts.getOrDefault(id, Set.of());
    }

    public Map<String, String> aliases() {
        return aliases;
    }

    /** True when the catalog can produce a complete profile — every single-token family is populated. */
    public boolean isComplete() {
        for (IdentityFamily family : IdentityFamily.values()) {
            if (family(family).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
