package dev.otectus.mcaconversations.identity;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Turns a seed and a few observed facts into a stable villager profile (spec §6.2).
 *
 * <h2>The seed</h2>
 *
 * <p>Derived from the world seed, the villager UUID and the schema version — and deliberately from
 * nothing else. Not the day, not the position, not the villager's name, and above all not the player,
 * because two players talking to the same villager must meet the same person.
 *
 * <h2>The RNG</h2>
 *
 * <p>SplitMix64, written out here rather than taken from {@code java.util.Random}. Two reasons: it is
 * a pure function of the seed and the draw index, so the tenth draw can be reproduced without
 * replaying the first nine; and it does not depend on a JDK class whose behaviour is specified for
 * {@code nextInt} but not for every derived method a future refactor might reach for. A profile that
 * silently changed under a JDK upgrade would be the worst possible bug in this file.
 *
 * <h2>What the generator will not do</h2>
 *
 * <p>It never infers a sensitive identity from a job or a personality. Bans live on the tokens and are
 * enforced here, so "cleric therefore devout" and "sensitive therefore fragile" cannot be written even
 * by a datapack that wants them (spec §6.2). It also never rerolls: {@link #generate} is called once,
 * the result is persisted, and later balance changes leave existing villagers alone.
 */
public final class VillagerIdentityGenerator {

    private VillagerIdentityGenerator() {
    }

    /** Ages that get no work style, because they have no work to have a style about. */
    private static final Set<String> NON_WORKING_AGES = Set.of("baby", "child");

    /**
     * The stable seed for one villager.
     *
     * @param worldSeed  the level's seed, so two worlds do not generate identical villages of people
     * @param villager   the villager UUID, which MCA keeps stable across unload and rename
     */
    public static long seedFor(long worldSeed, UUID villager) {
        long hash = 0xcbf29ce484222325L ^ worldSeed;
        hash = mix(hash, villager == null ? "" : villager.toString());
        hash = mix(hash, "identity/v" + VillagerIdentityRecord.SCHEMA_VERSION);
        return hash;
    }

    /**
     * Generates a profile.
     *
     * @param catalog     the token catalog; an empty or incomplete one yields empty
     * @param seed        from {@link #seedFor}
     * @param age         MCA age group, gating age-inappropriate tokens
     * @param professionId exact profession registry id, or null when unknown
     * @param archetype   work archetype key, used only to favour, never to gate
     * @param personality canonical personality key, used only to favour and to ban
     * @return the profile, or empty when no complete profile could be built
     */
    public static Optional<VillagerIdentityRecord> generate(IdentityCatalog catalog,
                                                            long seed,
                                                            String age,
                                                            String professionId,
                                                            String archetype,
                                                            String personality) {
        if (catalog == null || catalog.isEmpty()) {
            return Optional.empty();
        }
        String normalizedAge = normalize(age);
        String normalizedProfession = normalize(professionId);
        String normalizedArchetype = normalize(archetype);
        String normalizedPersonality = normalize(personality);

        // One monotonically advancing draw index across all families, so adding a family later cannot
        // change the tokens an earlier family would have picked for an already-generated villager.
        Draws draws = new Draws(seed);
        Set<String> taken = new LinkedHashSet<>();

        Set<String> interests = pickMany(catalog, IdentityFamily.INTEREST, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        Set<String> values = pickMany(catalog, IdentityFamily.VALUE, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        String comfort = pickOne(catalog, IdentityFamily.COMFORT, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        String aversion = pickOne(catalog, IdentityFamily.AVERSION, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        String workStyle = NON_WORKING_AGES.contains(normalizedAge)
                ? ""
                : pickOne(catalog, IdentityFamily.WORK_STYLE, draws, taken,
                        normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        String socialStyle = pickOne(catalog, IdentityFamily.SOCIAL_STYLE, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        String disclosureStyle = pickOne(catalog, IdentityFamily.DISCLOSURE_STYLE, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);
        String originMotif = pickOne(catalog, IdentityFamily.ORIGIN_MOTIF, draws, taken,
                normalizedAge, normalizedProfession, normalizedArchetype, normalizedPersonality);

        if (interests.isEmpty() && values.isEmpty() && comfort.isEmpty()) {
            // Nothing eligible at all: report no profile rather than a hollow one that reads as
            // "this villager cares about nothing".
            return Optional.empty();
        }
        return Optional.of(new VillagerIdentityRecord(
                VillagerIdentityRecord.SCHEMA_VERSION, seed, interests, values, comfort, aversion,
                workStyle, socialStyle, disclosureStyle, originMotif,
                Optional.empty(), Optional.empty(), 0L));
    }

    private static Set<String> pickMany(IdentityCatalog catalog, IdentityFamily family, Draws draws,
                                        Set<String> taken, String age, String profession,
                                        String archetype, String personality) {
        Set<String> picked = new LinkedHashSet<>();
        for (int slot = 0; slot < family.cap(); slot++) {
            String token = pickOne(catalog, family, draws, taken, age, profession, archetype, personality);
            if (token.isEmpty()) {
                break;
            }
            picked.add(token);
        }
        return picked;
    }

    /**
     * Weighted seeded choice from the eligible, non-conflicting tokens of one family.
     *
     * <p>The draw is consumed <em>whether or not</em> anything was eligible. That keeps the stream
     * position a function of the family list alone: a villager whose comfort family happened to be
     * empty must not thereby shift every later family's choice.
     */
    private static String pickOne(IdentityCatalog catalog, IdentityFamily family, Draws draws,
                                  Set<String> taken, String age, String profession,
                                  String archetype, String personality) {
        List<IdentityToken> eligible = new ArrayList<>();
        long total = 0L;
        for (IdentityToken token : catalog.family(family)) {
            if (taken.contains(token.id()) || conflictsWithTaken(catalog, taken, token.id())) {
                continue;
            }
            if (!token.isEligible(age, profession, archetype, personality)) {
                continue;
            }
            eligible.add(token);
            total += token.weightFor(archetype, personality);
        }
        long roll = draws.next();
        if (eligible.isEmpty() || total <= 0L) {
            return "";
        }
        long target = Math.floorMod(roll, total);
        for (IdentityToken token : eligible) {
            target -= token.weightFor(archetype, personality);
            if (target < 0L) {
                taken.add(token.id());
                return token.id();
            }
        }
        // Unreachable while total is the sum of the same weights, but a rounding surprise must not
        // return null into a record that has already promised non-null strings.
        IdentityToken last = eligible.get(eligible.size() - 1);
        taken.add(last.id());
        return last.id();
    }

    private static boolean conflictsWithTaken(IdentityCatalog catalog, Set<String> taken, String candidate) {
        for (String held : taken) {
            if (catalog.conflict(held, candidate)) {
                return true;
            }
        }
        return false;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private static long mix(long hash, String text) {
        for (byte b : text.getBytes(StandardCharsets.UTF_8)) {
            hash ^= (b & 0xffL);
            hash *= 0x100000001b3L;
        }
        return hash;
    }

    /** SplitMix64 over a seed and an advancing counter: pure, portable, and trivially reproducible. */
    private static final class Draws {
        private final long seed;
        private int index;

        Draws(long seed) {
            this.seed = seed;
        }

        long next() {
            long z = seed + (0x9e3779b97f4a7c15L * ++index);
            z = (z ^ (z >>> 30)) * 0xbf58476d1ce4e5b9L;
            z = (z ^ (z >>> 27)) * 0x94d049bb133111ebL;
            return (z ^ (z >>> 31)) & Long.MAX_VALUE;
        }
    }
}
