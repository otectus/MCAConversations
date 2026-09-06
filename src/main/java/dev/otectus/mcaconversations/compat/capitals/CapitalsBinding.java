package dev.otectus.mcaconversations.compat.capitals;

import dev.otectus.mcaconversations.compat.CapitalsCapability;
import dev.otectus.mcaconversations.compat.CapitalsStatus;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Resolves MCA Capitals at <em>runtime</em>, by name, and reports what bound as
 * {@link CapitalsCapability capabilities} rather than as one all-or-nothing switch. The shape is
 * {@code TownsteadBinding}'s, for the same reasons.
 *
 * <h2>Why nothing is bound by parameter type</h2>
 *
 * <p>Capitals is compiled against MCA, and several of its own classes carry MCA types in their
 * descriptors. Naming any parameter type would hard-link this mod to one MCA package layout through
 * a third mod, so methods are matched on owner, name, arity and staticness, and every handle is
 * adapted to an erased shape whose parameters are all {@link Object}. A Capitals value passes
 * through as a reference nothing here ever names.
 *
 * <h2>Capabilities, not a boolean</h2>
 *
 * <p>Each {@link Member} belongs to exactly one capability, and a capability is bound only when
 * every member it declares bound. So one moved method in a Capitals point release disables exactly
 * the feature that needed it. {@link CapitalsCapability#CORE} is the exception in effect rather than
 * in kind: without it there is no record to read anything else from, so losing it is
 * {@link CapitalsStatus#INCOMPATIBLE}.
 *
 * <h2>The contract</h2>
 *
 * <p><b>Resolution never throws and never returns null.</b> An unresolved member becomes a constant
 * stub returning its type's default, so call sites in {@link CapitalsHandles} need no guards.
 * Enumerating a class's methods forces the JVM to resolve their descriptors, so a Capitals built
 * against a different MCA layout than the installed one can throw {@code NoClassDefFoundError} out
 * of {@code getMethods()} itself. Caught per owner, and recorded with the throwable's own class
 * name, that mismatch reads as "this capability is unbound, and here is why" rather than as a crash.
 *
 * @see CapitalsHandles for the resolved handles themselves
 */
public final class CapitalsBinding {

    /**
     * Capitals' package root, stored <em>dotted</em>, never in internal slash form. That is what lets
     * {@code NoCapitalsStaticLinkTest} byte-scan for slash-form references and treat any hit as a
     * regression, with no exemption for this file.
     */
    private static final String PACKAGE = "com.majesttyx.mcacapitals.";

    /** The class whose presence identifies an installed Capitals. */
    private static final String PROBE_CLASS = "capital.CapitalManager";

    private enum Kind { VIRTUAL, STATIC }

    /**
     * One thing Conversations needs from Capitals, named relative to {@link #PACKAGE}.
     * Identity-compared, so {@link CapitalsHandles} refers to members by constant rather than by a
     * string that could typo.
     */
    public static final class Member {

        private final Kind kind;
        private final String ownerRelative;
        private final String name;
        private final Class<?> returnType;
        private final int arity;
        private final CapitalsCapability capability;

        private Member(Kind kind, String ownerRelative, String name, Class<?> returnType, int arity,
                       CapitalsCapability capability) {
            this.kind = kind;
            this.ownerRelative = ownerRelative;
            this.name = name;
            this.returnType = returnType;
            this.arity = arity;
            this.capability = capability;
        }

        /** The capability this member belongs to. */
        public CapitalsCapability capability() {
            return capability;
        }

        @Override
        public String toString() {
            return ownerRelative + "#" + name + "/" + arity;
        }

        /**
         * The erased handle shape. Every parameter is {@link Object} (including the receiver for a
         * virtual) and {@code asType} does the boxing, so callers pass plain references and a
         * Capitals argument crosses without ever being named.
         *
         * <p>Only a <em>primitive</em> return keeps its faithful type, so an unresolved member stubs
         * to a real {@code 0} or {@code false}. Every reference return is erased to {@link Object},
         * because the stub for one is {@code null} whatever type it claims.
         */
        private MethodType erasedType() {
            int params = kind == Kind.VIRTUAL ? arity + 1 : arity;
            return MethodType.methodType(returnType, Collections.nCopies(params, Object.class));
        }
    }

    private static Member statik(String ownerRelative, String name, Class<?> ret, int arity,
                                 CapitalsCapability capability) {
        return new Member(Kind.STATIC, ownerRelative, name, ret, arity, capability);
    }

    private static Member virtual(String ownerRelative, String name, Class<?> ret, int arity,
                                  CapitalsCapability capability) {
        return new Member(Kind.VIRTUAL, ownerRelative, name, ret, arity, capability);
    }

    // ---------------------------------------------------------------------------------------------
    // The manifest: every Capitals class and member Conversations reads.
    //
    // Verified member-by-member against mcacapitals-1.3.6.jar by CapitalsBindingProbeTest. Every
    // entry is unique by owner, name, arity and staticness in that jar, which is what keeps
    // parameter types -- and so MCA's relocated classes -- out of our constant pool. Capitals never
    // gets written to: this mod reads a court, it does not hold one.
    // ---------------------------------------------------------------------------------------------

    private static final String O_MANAGER = "capital.CapitalManager";
    private static final String O_RECORD = "capital.CapitalRecord";
    private static final String O_TITLES = "capital.CapitalTitleResolver";
    private static final String O_TITLE_ID = "capital.CapitalTitleResolver$ResolvedTitleId";
    private static final String O_NOBLES = "noble.NobleManager";
    private static final String O_STANDING = "capital.CapitalCrownStandingService";
    private static final String O_HOUSE_DATA = "data.CapitalHouseDataAccess";
    private static final String O_HOUSE = "house.CapitalHouseRecord";
    private static final String O_IDENTITY_SERVICE = "identity.VillagerIdentityService";
    private static final String O_IDENTITY = "identity.VillagerIdentityData";
    private static final String O_DIPLOMACY = "data.CapitalDiplomacyDataAccess";
    private static final String O_RELATION_KEY = "data.CapitalRelationKey";
    private static final String O_RELATION = "data.CapitalRelationRecord";
    private static final String O_CHRONICLE = "capital.CapitalChronicleService";
    private static final String O_CHRONICLE_ENTRY = "capital.CapitalChronicleEntry";
    private static final String O_ALLEGIANCE = "capital.PlayerCapitalAllegianceService";
    private static final String O_NAMES = "capital.CapitalNameService";
    private static final String O_MCA_BRIDGE = "util.MCAIntegrationBridge";

    private static final CapitalsCapability CORE = CapitalsCapability.CORE;
    private static final CapitalsCapability COURT = CapitalsCapability.COURT;
    private static final CapitalsCapability TITLES = CapitalsCapability.TITLES;
    private static final CapitalsCapability STANDING = CapitalsCapability.STANDING;
    private static final CapitalsCapability HOUSES = CapitalsCapability.HOUSES;
    private static final CapitalsCapability DIPLOMACY = CapitalsCapability.DIPLOMACY;
    private static final CapitalsCapability CHRONICLE = CapitalsCapability.CHRONICLE;
    private static final CapitalsCapability ALLEGIANCE = CapitalsCapability.ALLEGIANCE;
    private static final CapitalsCapability NAMES = CapitalsCapability.NAMES;

    // CORE: the registry and the identity of a record. Nothing else can be read without these.
    public static final Member CAPITAL_FOR_VILLAGE =
            statik(O_MANAGER, "getCapitalForVillage", Object.class, 2, CORE);
    public static final Member ALL_CAPITAL_RECORDS =
            statik(O_MANAGER, "getAllCapitalRecords", Object.class, 0, CORE);
    public static final Member CAPITAL_LEVEL =
            statik(O_MANAGER, "getCapitalLevel", Object.class, 2, CORE);
    public static final Member R_CAPITAL_ID = virtual(O_RECORD, "getCapitalId", Object.class, 0, CORE);
    public static final Member R_VILLAGE_ID = virtual(O_RECORD, "getVillageId", Object.class, 0, CORE);
    public static final Member R_DIMENSION_ID =
            virtual(O_RECORD, "getVillageDimensionId", Object.class, 0, CORE);
    public static final Member R_STATE = virtual(O_RECORD, "getState", Object.class, 0, CORE);
    public static final Member R_CHRONICLE_ENTRIES =
            virtual(O_RECORD, "getChronicleEntries", Object.class, 0, CORE);

    // COURT: who sits where. Every uuid here is nullable in Capitals and crosses as an Optional.
    public static final Member R_SOVEREIGN = virtual(O_RECORD, "getSovereign", Object.class, 0, COURT);
    public static final Member R_CONSORT = virtual(O_RECORD, "getConsort", Object.class, 0, COURT);
    public static final Member R_HEIR = virtual(O_RECORD, "getHeir", Object.class, 0, COURT);
    public static final Member R_DOWAGER = virtual(O_RECORD, "getDowager", Object.class, 0, COURT);
    public static final Member R_HAND = virtual(O_RECORD, "getHand", Object.class, 0, COURT);
    public static final Member R_COMMANDER = virtual(O_RECORD, "getCommander", Object.class, 0, COURT);
    public static final Member R_HERALD = virtual(O_RECORD, "getHerald", Object.class, 0, COURT);
    public static final Member R_GRAND_MAESTER =
            virtual(O_RECORD, "getGrandMaester", Object.class, 0, COURT);
    public static final Member R_MASTER_OF_LAWS =
            virtual(O_RECORD, "getMasterOfLaws", Object.class, 0, COURT);
    public static final Member R_SOVEREIGN_FEMALE =
            virtual(O_RECORD, "isSovereignFemale", boolean.class, 0, COURT);
    public static final Member R_MOURNING =
            virtual(O_RECORD, "isMourningActive", boolean.class, 0, COURT);
    public static final Member R_PLAYER_SOVEREIGN =
            virtual(O_RECORD, "isPlayerSovereign", boolean.class, 0, COURT);
    public static final Member R_PLAYER_SOVEREIGN_ID =
            virtual(O_RECORD, "getPlayerSovereignId", Object.class, 0, COURT);
    public static final Member R_PLAYER_SOVEREIGN_NAME =
            virtual(O_RECORD, "getPlayerSovereignName", Object.class, 0, COURT);
    public static final Member R_IS_HOUSEHOLD =
            virtual(O_RECORD, "isRoyalHouseholdMember", boolean.class, 1, COURT);
    public static final Member R_IS_ROYAL_GUARD =
            virtual(O_RECORD, "isRoyalGuard", boolean.class, 1, COURT);
    public static final Member R_IS_DISGRACED =
            virtual(O_RECORD, "isDisgracedRoyalGuard", boolean.class, 1, COURT);

    // TITLES. NobleManager.getTitle is the fallback when the resolver cannot answer for an entity
    // Capitals does not consider a resident, and is why a bare noble still has a title to speak of.
    public static final Member FIND_CAPITAL_FOR_ENTITY =
            statik(O_TITLES, "findCapitalForEntity", Object.class, 2, TITLES);
    public static final Member RESOLVED_TITLE_ID =
            statik(O_TITLES, "getResolvedTitleIdForEntity", Object.class, 2, TITLES);
    public static final Member DISPLAY_TITLE =
            statik(O_TITLES, "getDisplayTitleForEntity", Object.class, 2, TITLES);
    public static final Member COURT_OFFICE =
            statik(O_TITLES, "getCourtOfficeLineIdForEntity", Object.class, 2, TITLES);
    public static final Member TITLE_RANK = virtual(O_TITLE_ID, "rankValue", int.class, 0, TITLES);
    public static final Member NOBLE_TITLE = statik(O_NOBLES, "getTitle", Object.class, 1, TITLES);

    // STANDING.
    public static final Member CROWN_STANDING =
            statik(O_STANDING, "getStanding", Object.class, 3, STANDING);

    // HOUSES. findHouseForMember answers for any uuid; the identity record needs a loaded entity and
    // is what carries house words and the current surname.
    public static final Member HOUSE_FOR_MEMBER =
            statik(O_HOUSE_DATA, "findHouseForMember", Object.class, 3, HOUSES);
    public static final Member H_NAME = virtual(O_HOUSE, "getHouseName", Object.class, 0, HOUSES);
    public static final Member H_TIER = virtual(O_HOUSE, "getTier", Object.class, 0, HOUSES);
    public static final Member IDENTITY_OF =
            statik(O_IDENTITY_SERVICE, "getIdentity", Object.class, 1, HOUSES);
    public static final Member I_HOUSE_NAME = virtual(O_IDENTITY, "houseName", Object.class, 0, HOUSES);
    public static final Member I_HOUSE_WORDS =
            virtual(O_IDENTITY, "houseWords", Object.class, 0, HOUSES);
    public static final Member I_SURNAME =
            virtual(O_IDENTITY, "currentSurname", Object.class, 0, HOUSES);

    // DIPLOMACY.
    public static final Member RELATIONS_SNAPSHOT =
            statik(O_DIPLOMACY, "getRelationshipsSnapshot", Object.class, 1, DIPLOMACY);
    public static final Member AMBASSADOR =
            statik(O_DIPLOMACY, "getAmbassador", Object.class, 2, DIPLOMACY);
    public static final Member K_FIRST = virtual(O_RELATION_KEY, "first", Object.class, 0, DIPLOMACY);
    public static final Member K_SECOND = virtual(O_RELATION_KEY, "second", Object.class, 0, DIPLOMACY);
    public static final Member REL_STATE =
            virtual(O_RELATION, "getDiplomaticState", Object.class, 0, DIPLOMACY);
    public static final Member REL_BAND = virtual(O_RELATION, "getBand", Object.class, 0, DIPLOMACY);
    public static final Member REL_SCORE = virtual(O_RELATION, "getScore", int.class, 0, DIPLOMACY);

    // CHRONICLE. decodeSemanticEntry returns null for the old plain-text entries, which is why
    // renderStoredEntry is bound beside it: it renders both forms.
    public static final Member DECODE_ENTRY =
            statik(O_CHRONICLE, "decodeSemanticEntry", Object.class, 1, CHRONICLE);
    public static final Member RENDER_STORED_ENTRY =
            statik(O_CHRONICLE, "renderStoredEntry", Object.class, 1, CHRONICLE);
    public static final Member E_DAY = virtual(O_CHRONICLE_ENTRY, "day", long.class, 0, CHRONICLE);
    public static final Member E_TYPE = virtual(O_CHRONICLE_ENTRY, "type", Object.class, 0, CHRONICLE);
    public static final Member E_TRANSLATION_KEY =
            virtual(O_CHRONICLE_ENTRY, "translationKey", Object.class, 0, CHRONICLE);
    public static final Member E_RENDER =
            virtual(O_CHRONICLE_ENTRY, "render", Object.class, 0, CHRONICLE);

    // ALLEGIANCE.
    public static final Member DECLARED_CAPITAL_ID =
            statik(O_ALLEGIANCE, "getDeclaredCapitalId", Object.class, 2, ALLEGIANCE);

    // NAMES. getVillageName is bound only as a fallback: a capital's name is the MCA village name,
    // and this mod reads that through its own MCA binding first.
    public static final Member RESOLVE_DISPLAY_NAME =
            statik(O_NAMES, "resolveDisplayName", Object.class, 3, NAMES);
    public static final Member FAMILY_NODE_NAME =
            statik(O_MCA_BRIDGE, "getFamilyNodeName", Object.class, 2, NAMES);
    public static final Member VILLAGE_NAME =
            statik(O_MCA_BRIDGE, "getVillageName", Object.class, 2, NAMES);

    /** Every member, in declaration order. The single source of truth for what this mod touches. */
    public static final List<Member> MANIFEST = List.of(
            CAPITAL_FOR_VILLAGE, ALL_CAPITAL_RECORDS, CAPITAL_LEVEL, R_CAPITAL_ID, R_VILLAGE_ID,
            R_DIMENSION_ID, R_STATE, R_CHRONICLE_ENTRIES,
            R_SOVEREIGN, R_CONSORT, R_HEIR, R_DOWAGER, R_HAND, R_COMMANDER, R_HERALD, R_GRAND_MAESTER,
            R_MASTER_OF_LAWS, R_SOVEREIGN_FEMALE, R_MOURNING, R_PLAYER_SOVEREIGN, R_PLAYER_SOVEREIGN_ID,
            R_PLAYER_SOVEREIGN_NAME, R_IS_HOUSEHOLD, R_IS_ROYAL_GUARD, R_IS_DISGRACED,
            FIND_CAPITAL_FOR_ENTITY, RESOLVED_TITLE_ID, DISPLAY_TITLE, COURT_OFFICE, TITLE_RANK,
            NOBLE_TITLE,
            CROWN_STANDING,
            HOUSE_FOR_MEMBER, H_NAME, H_TIER, IDENTITY_OF, I_HOUSE_NAME, I_HOUSE_WORDS, I_SURNAME,
            RELATIONS_SNAPSHOT, AMBASSADOR, K_FIRST, K_SECOND, REL_STATE, REL_BAND, REL_SCORE,
            DECODE_ENTRY, RENDER_STORED_ENTRY, E_DAY, E_TYPE, E_TRANSLATION_KEY, E_RENDER,
            DECLARED_CAPITAL_ID,
            RESOLVE_DISPLAY_NAME, FAMILY_NODE_NAME, VILLAGE_NAME);

    /**
     * The capabilities this manifest covers. Status is measured against these rather than against
     * every {@link CapitalsCapability} constant, so a capability whose members have not been declared
     * here cannot be mistaken for one that bound.
     */
    public static final Set<CapitalsCapability> DECLARED_CAPABILITIES = declaredCapabilities();

    private static Set<CapitalsCapability> declaredCapabilities() {
        EnumSet<CapitalsCapability> declared = EnumSet.noneOf(CapitalsCapability.class);
        for (Member member : MANIFEST) {
            declared.add(member.capability);
        }
        return Collections.unmodifiableSet(declared);
    }

    // ---------------------------------------------------------------------------------------------
    // Resolution
    // ---------------------------------------------------------------------------------------------

    /** The outcome of resolving {@link #MANIFEST} against one classloader. Immutable. */
    public static final class Resolution {

        private final CapitalsStatus status;
        private final Set<CapitalsCapability> capabilities;
        private final Map<Member, MethodHandle> resolved;
        private final List<String> unresolved;

        private Resolution(CapitalsStatus status, Set<CapitalsCapability> capabilities,
                           Map<Member, MethodHandle> resolved, List<String> unresolved) {
            this.status = status;
            this.capabilities = capabilities;
            this.resolved = resolved;
            this.unresolved = List.copyOf(unresolved);
        }

        public CapitalsStatus status() {
            return status;
        }

        /** The capabilities whose every declared member bound. */
        public Set<CapitalsCapability> capabilities() {
            return capabilities;
        }

        /**
         * Members that did not bind, each with the reason, for the one WARN at startup. A whole-owner
         * failure carries the throwable's class name, so an MCA layout mismatch is distinguishable
         * from a renamed method.
         */
        public List<String> unresolved() {
            return unresolved;
        }

        /**
         * The handle for a member. <b>Never null</b>: an unresolved member yields a constant stub of
         * the same erased type returning that type's default, so call sites need no guard of their
         * own.
         */
        public MethodHandle handle(Member member) {
            MethodHandle handle = resolved.get(member);
            return handle != null ? handle : MethodHandles.empty(member.erasedType());
        }

        public boolean has(Member member) {
            return resolved.containsKey(member);
        }

        public boolean has(CapitalsCapability capability) {
            return capabilities.contains(capability);
        }
    }

    /**
     * A resolution in which nothing bound, used when Capitals is not installed and as the last-ditch
     * value if resolution itself somehow fails. Every handle it hands out is still a working stub.
     */
    public static Resolution absent() {
        return new Resolution(CapitalsStatus.ABSENT, Set.of(), Map.of(), List.of());
    }

    /**
     * Resolves the whole manifest against {@code loader}. Never throws: every failure is recorded and
     * turned into a stub.
     */
    public static Resolution resolveAgainst(ClassLoader loader) {
        if (loadOrNull(loader, PACKAGE + PROBE_CLASS) == null) {
            return absent();
        }

        Map<Member, MethodHandle> resolved = new IdentityHashMap<>();
        List<String> unresolved = new ArrayList<>();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Map<String, OwnerMethods> methodCache = new HashMap<>();

        for (Member member : MANIFEST) {
            OwnerMethods owner = methodsOf(loader, methodCache, member.ownerRelative);
            MethodHandle handle = null;
            try {
                handle = bindMethod(lookup, owner.methods(), member);
            } catch (Throwable ignored) {
                // Recorded below as an ordinary miss; see the javadoc for why this must not escape.
            }
            if (handle == null) {
                unresolved.add(member + " (" + owner.failure() + ")");
            } else {
                resolved.put(member, handle);
            }
        }

        EnumSet<CapitalsCapability> bound = EnumSet.copyOf(DECLARED_CAPABILITIES);
        for (Member member : MANIFEST) {
            if (!resolved.containsKey(member)) {
                bound.remove(member.capability);
            }
        }

        CapitalsStatus status;
        if (!bound.contains(CapitalsCapability.CORE)) {
            status = CapitalsStatus.INCOMPATIBLE;
        } else if (bound.size() == DECLARED_CAPABILITIES.size()) {
            status = CapitalsStatus.FULL;
        } else {
            status = CapitalsStatus.PARTIAL;
        }

        return new Resolution(status, Collections.unmodifiableSet(bound), resolved, unresolved);
    }

    /**
     * Every public method of one owner, plus why it produced none if it produced none. Cached because
     * a miss here is a whole-class failure that should be reported identically for each of that
     * class's members, and because {@code getMethods()} is the expensive part of binding.
     */
    private record OwnerMethods(Method[] methods, @Nullable String failure) {

        static OwnerMethods of(Method[] methods) {
            return new OwnerMethods(methods, null);
        }

        static OwnerMethods failed(String failure) {
            return new OwnerMethods(new Method[0], failure);
        }

        @Override
        public String failure() {
            return failure == null ? "no matching member" : failure;
        }
    }

    private static OwnerMethods methodsOf(ClassLoader loader, Map<String, OwnerMethods> cache,
                                          String ownerRelative) {
        return cache.computeIfAbsent(ownerRelative, relative -> {
            Class<?> owner = loadOrNull(loader, PACKAGE + relative);
            if (owner == null) {
                return OwnerMethods.failed("class not found");
            }
            try {
                return OwnerMethods.of(owner.getMethods());
            } catch (Throwable t) {
                // Almost always an MCA type in one of this class's descriptors that the installed MCA
                // does not have. Recording the throwable's own name makes that diagnosable.
                return OwnerMethods.failed(t.getClass().getSimpleName());
            }
        });
    }

    /**
     * {@code initialize = false} is deliberate: a probe must not run a Capitals class's static
     * initialiser, which would touch MCA before Forge is ready for it.
     */
    @Nullable
    private static Class<?> loadOrNull(ClassLoader loader, String name) {
        try {
            return Class.forName(name, false, loader);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Finds a method by name, arity and staticness. Never by parameter type, which would mean naming
     * MCA's relocated classes and reintroducing the linkage this layer exists to avoid.
     */
    @Nullable
    private static MethodHandle bindMethod(MethodHandles.Lookup lookup, Method[] candidates,
                                           Member member) {
        for (Method candidate : candidates) {
            if (!candidate.getName().equals(member.name)
                    || candidate.getParameterCount() != member.arity
                    || Modifier.isStatic(candidate.getModifiers()) != (member.kind == Kind.STATIC)) {
                continue;
            }
            try {
                candidate.setAccessible(true);
                // asFixedArity() before asType(), for the reason McaBinding#erase spells out: erasing
                // a varargs collector's trailing Object[] to Object turns asType into a re-collect,
                // which would hand the callee our argument array wrapped in another array. Capitals
                // declares varargs members on the chronicle service; none is bound today, and this is
                // what keeps binding one from being a silent corruption.
                return lookup.unreflect(candidate).asFixedArity().asType(member.erasedType());
            } catch (Throwable t) {
                return null;
            }
        }
        return null;
    }

    private CapitalsBinding() {
    }
}
