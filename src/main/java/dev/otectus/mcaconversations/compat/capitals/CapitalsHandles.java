package dev.otectus.mcaconversations.compat.capitals;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.CapitalsCapability;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The typed facade over {@link CapitalsBinding}'s resolved handles: Capitals objects go in, JDK
 * values come out, and <b>no Capitals value ever escapes this class except as an opaque
 * {@link Object}</b> that only {@link ReflectiveCapitalsBridge} passes straight back in.
 *
 * <p>Every read swallows {@link Throwable} and answers the type's neutral value, because an unbound
 * member is a stub whose invocation is legal but meaningless, and because a read is never worth a
 * crash. Failures are logged <b>once per member</b>, not once per call: a moved method would
 * otherwise produce a stack trace per villager per tick.
 *
 * <p>Enums are converted with {@link Enum#name()} lowercased and components with
 * {@link Component#getString()}, so neither a Capitals enum constant nor a mutable component can
 * reach a view record and become linkage or a stale reference.
 *
 * @see CapitalsBinding for the manifest these handles come from
 */
final class CapitalsHandles {

    private static final CapitalsBinding.Resolution R = resolveQuietly();

    /** Members that have already reported a failure, so a broken one logs once and not per tick. */
    private static final Set<String> REPORTED = ConcurrentHashMap.newKeySet();

    private CapitalsHandles() {
    }

    private static CapitalsBinding.Resolution resolveQuietly() {
        try {
            return CapitalsBinding.resolveAgainst(CapitalsHandles.class.getClassLoader());
        } catch (Throwable t) {
            return CapitalsBinding.absent();
        }
    }

    /** The live resolution, for the one line logged at startup. */
    static CapitalsBinding.Resolution resolution() {
        return R;
    }

    static boolean has(CapitalsCapability capability) {
        return R.has(capability);
    }

    // --- handles ---------------------------------------------------------------------------------

    private static final MethodHandle H_CAPITAL_FOR_VILLAGE =
            R.handle(CapitalsBinding.CAPITAL_FOR_VILLAGE);
    private static final MethodHandle H_ALL_RECORDS = R.handle(CapitalsBinding.ALL_CAPITAL_RECORDS);
    private static final MethodHandle H_CAPITAL_LEVEL = R.handle(CapitalsBinding.CAPITAL_LEVEL);
    private static final MethodHandle H_CAPITAL_ID = R.handle(CapitalsBinding.R_CAPITAL_ID);
    private static final MethodHandle H_VILLAGE_ID = R.handle(CapitalsBinding.R_VILLAGE_ID);
    private static final MethodHandle H_DIMENSION_ID = R.handle(CapitalsBinding.R_DIMENSION_ID);
    private static final MethodHandle H_STATE = R.handle(CapitalsBinding.R_STATE);
    private static final MethodHandle H_CHRONICLE_ENTRIES =
            R.handle(CapitalsBinding.R_CHRONICLE_ENTRIES);

    private static final MethodHandle H_SOVEREIGN = R.handle(CapitalsBinding.R_SOVEREIGN);
    private static final MethodHandle H_CONSORT = R.handle(CapitalsBinding.R_CONSORT);
    private static final MethodHandle H_HEIR = R.handle(CapitalsBinding.R_HEIR);
    private static final MethodHandle H_DOWAGER = R.handle(CapitalsBinding.R_DOWAGER);
    private static final MethodHandle H_HAND = R.handle(CapitalsBinding.R_HAND);
    private static final MethodHandle H_COMMANDER = R.handle(CapitalsBinding.R_COMMANDER);
    private static final MethodHandle H_HERALD = R.handle(CapitalsBinding.R_HERALD);
    private static final MethodHandle H_GRAND_MAESTER = R.handle(CapitalsBinding.R_GRAND_MAESTER);
    private static final MethodHandle H_MASTER_OF_LAWS = R.handle(CapitalsBinding.R_MASTER_OF_LAWS);
    private static final MethodHandle H_SOVEREIGN_FEMALE = R.handle(CapitalsBinding.R_SOVEREIGN_FEMALE);
    private static final MethodHandle H_MOURNING = R.handle(CapitalsBinding.R_MOURNING);
    private static final MethodHandle H_PLAYER_SOVEREIGN = R.handle(CapitalsBinding.R_PLAYER_SOVEREIGN);
    private static final MethodHandle H_PLAYER_SOVEREIGN_ID =
            R.handle(CapitalsBinding.R_PLAYER_SOVEREIGN_ID);
    private static final MethodHandle H_PLAYER_SOVEREIGN_NAME =
            R.handle(CapitalsBinding.R_PLAYER_SOVEREIGN_NAME);
    private static final MethodHandle H_IS_HOUSEHOLD = R.handle(CapitalsBinding.R_IS_HOUSEHOLD);
    private static final MethodHandle H_IS_ROYAL_GUARD = R.handle(CapitalsBinding.R_IS_ROYAL_GUARD);
    private static final MethodHandle H_IS_DISGRACED = R.handle(CapitalsBinding.R_IS_DISGRACED);

    private static final MethodHandle H_FIND_CAPITAL = R.handle(CapitalsBinding.FIND_CAPITAL_FOR_ENTITY);
    private static final MethodHandle H_TITLE_ID = R.handle(CapitalsBinding.RESOLVED_TITLE_ID);
    private static final MethodHandle H_DISPLAY_TITLE = R.handle(CapitalsBinding.DISPLAY_TITLE);
    private static final MethodHandle H_COURT_OFFICE = R.handle(CapitalsBinding.COURT_OFFICE);
    private static final MethodHandle H_TITLE_RANK = R.handle(CapitalsBinding.TITLE_RANK);
    private static final MethodHandle H_NOBLE_TITLE = R.handle(CapitalsBinding.NOBLE_TITLE);

    private static final MethodHandle H_CROWN_STANDING = R.handle(CapitalsBinding.CROWN_STANDING);

    private static final MethodHandle H_HOUSE_FOR_MEMBER = R.handle(CapitalsBinding.HOUSE_FOR_MEMBER);
    private static final MethodHandle H_HOUSE_NAME = R.handle(CapitalsBinding.H_NAME);
    private static final MethodHandle H_HOUSE_TIER = R.handle(CapitalsBinding.H_TIER);
    private static final MethodHandle H_IDENTITY_OF = R.handle(CapitalsBinding.IDENTITY_OF);
    private static final MethodHandle H_IDENTITY_HOUSE = R.handle(CapitalsBinding.I_HOUSE_NAME);
    private static final MethodHandle H_IDENTITY_WORDS = R.handle(CapitalsBinding.I_HOUSE_WORDS);
    private static final MethodHandle H_IDENTITY_SURNAME = R.handle(CapitalsBinding.I_SURNAME);

    private static final MethodHandle H_RELATIONS = R.handle(CapitalsBinding.RELATIONS_SNAPSHOT);
    private static final MethodHandle H_AMBASSADOR = R.handle(CapitalsBinding.AMBASSADOR);
    private static final MethodHandle H_KEY_FIRST = R.handle(CapitalsBinding.K_FIRST);
    private static final MethodHandle H_KEY_SECOND = R.handle(CapitalsBinding.K_SECOND);
    private static final MethodHandle H_REL_STATE = R.handle(CapitalsBinding.REL_STATE);
    private static final MethodHandle H_REL_BAND = R.handle(CapitalsBinding.REL_BAND);
    private static final MethodHandle H_REL_SCORE = R.handle(CapitalsBinding.REL_SCORE);

    private static final MethodHandle H_DECODE_ENTRY = R.handle(CapitalsBinding.DECODE_ENTRY);
    private static final MethodHandle H_RENDER_STORED = R.handle(CapitalsBinding.RENDER_STORED_ENTRY);
    private static final MethodHandle H_ENTRY_DAY = R.handle(CapitalsBinding.E_DAY);
    private static final MethodHandle H_ENTRY_TYPE = R.handle(CapitalsBinding.E_TYPE);
    private static final MethodHandle H_ENTRY_KEY = R.handle(CapitalsBinding.E_TRANSLATION_KEY);
    private static final MethodHandle H_ENTRY_RENDER = R.handle(CapitalsBinding.E_RENDER);

    private static final MethodHandle H_DECLARED_CAPITAL = R.handle(CapitalsBinding.DECLARED_CAPITAL_ID);

    private static final MethodHandle H_RESOLVE_NAME = R.handle(CapitalsBinding.RESOLVE_DISPLAY_NAME);
    private static final MethodHandle H_FAMILY_NAME = R.handle(CapitalsBinding.FAMILY_NODE_NAME);
    private static final MethodHandle H_VILLAGE_NAME = R.handle(CapitalsBinding.VILLAGE_NAME);

    // --- core ------------------------------------------------------------------------------------

    /** The capital record seated at an MCA village, opaque. {@code null} when there is none. */
    @Nullable
    static Object capitalForVillage(Object level, int villageId) {
        return has(CapitalsCapability.CORE)
                ? ref("getCapitalForVillage", H_CAPITAL_FOR_VILLAGE, level, villageId) : null;
    }

    /** Every capital record the server holds, opaque, in registry order. */
    static List<Object> allCapitalRecords() {
        if (!has(CapitalsCapability.CORE)) {
            return List.of();
        }
        Object records = ref("getAllCapitalRecords", H_ALL_RECORDS);
        if (!(records instanceof Iterable<?> items)) {
            return List.of();
        }
        List<Object> out = new ArrayList<>();
        for (Object record : items) {
            if (record != null) {
                out.add(record);
            }
        }
        return out;
    }

    /** The level a capital's village sits in, as a raw object for the caller to cast. */
    @Nullable
    static Object capitalLevel(Object server, Object record) {
        return has(CapitalsCapability.CORE)
                ? ref("getCapitalLevel", H_CAPITAL_LEVEL, server, record) : null;
    }

    static Optional<UUID> capitalId(Object record) {
        return asUuid(ref("getCapitalId", H_CAPITAL_ID, record));
    }

    static int villageId(Object record) {
        Object value = ref("getVillageId", H_VILLAGE_ID, record);
        return value instanceof Number number ? number.intValue() : -1;
    }

    static String dimensionId(Object record) {
        return asString(ref("getVillageDimensionId", H_DIMENSION_ID, record));
    }

    /** The capital state, lower-cased. {@code unknown} rather than empty, so content can gate on it. */
    static String state(Object record) {
        String state = asEnumName(ref("getState", H_STATE, record));
        return state.isEmpty() ? "unknown" : state;
    }

    /** The raw, encoded chronicle entries of one record, oldest first. */
    static List<String> chronicleEntries(Object record) {
        return asStringList(ref("getChronicleEntries", H_CHRONICLE_ENTRIES, record));
    }

    // --- court -----------------------------------------------------------------------------------

    static Optional<UUID> sovereign(Object record) {
        return courtSeat("getSovereign", H_SOVEREIGN, record);
    }

    static Optional<UUID> consort(Object record) {
        return courtSeat("getConsort", H_CONSORT, record);
    }

    static Optional<UUID> heir(Object record) {
        return courtSeat("getHeir", H_HEIR, record);
    }

    static Optional<UUID> dowager(Object record) {
        return courtSeat("getDowager", H_DOWAGER, record);
    }

    static Optional<UUID> hand(Object record) {
        return courtSeat("getHand", H_HAND, record);
    }

    static Optional<UUID> commander(Object record) {
        return courtSeat("getCommander", H_COMMANDER, record);
    }

    static Optional<UUID> herald(Object record) {
        return courtSeat("getHerald", H_HERALD, record);
    }

    static Optional<UUID> grandMaester(Object record) {
        return courtSeat("getGrandMaester", H_GRAND_MAESTER, record);
    }

    static Optional<UUID> masterOfLaws(Object record) {
        return courtSeat("getMasterOfLaws", H_MASTER_OF_LAWS, record);
    }

    static Optional<UUID> playerSovereignId(Object record) {
        return courtSeat("getPlayerSovereignId", H_PLAYER_SOVEREIGN_ID, record);
    }

    static boolean sovereignFemale(Object record) {
        return flag("isSovereignFemale", H_SOVEREIGN_FEMALE, record);
    }

    static boolean mourning(Object record) {
        return flag("isMourningActive", H_MOURNING, record);
    }

    static boolean playerSovereign(Object record) {
        return flag("isPlayerSovereign", H_PLAYER_SOVEREIGN, record);
    }

    static String playerSovereignName(Object record) {
        return has(CapitalsCapability.COURT)
                ? asString(ref("getPlayerSovereignName", H_PLAYER_SOVEREIGN_NAME, record)) : "";
    }

    static boolean royalHousehold(Object record, UUID villager) {
        return flag("isRoyalHouseholdMember", H_IS_HOUSEHOLD, record, villager);
    }

    static boolean royalGuard(Object record, UUID villager) {
        return flag("isRoyalGuard", H_IS_ROYAL_GUARD, record, villager);
    }

    static boolean disgraced(Object record, UUID villager) {
        return flag("isDisgracedRoyalGuard", H_IS_DISGRACED, record, villager);
    }

    private static Optional<UUID> courtSeat(String site, MethodHandle handle, Object record) {
        return has(CapitalsCapability.COURT) ? asUuid(ref(site, handle, record)) : Optional.empty();
    }

    // --- titles ----------------------------------------------------------------------------------

    /** The capital record Capitals resolves this villager into, opaque. */
    @Nullable
    static Object findCapitalForEntity(Object level, UUID villager) {
        return has(CapitalsCapability.TITLES)
                ? ref("findCapitalForEntity", H_FIND_CAPITAL, level, villager) : null;
    }

    /**
     * The resolved title enum constant, opaque, because the rank has to be read off the same object.
     */
    @Nullable
    static Object resolvedTitle(Object level, UUID villager) {
        return has(CapitalsCapability.TITLES)
                ? ref("getResolvedTitleIdForEntity", H_TITLE_ID, level, villager) : null;
    }

    /** A resolved title as its lower-case id, or {@code none} when there is no title at all. */
    static String titleId(@Nullable Object title) {
        String name = asEnumName(title);
        return name.isEmpty() ? "none" : name;
    }

    static int titleRank(@Nullable Object title) {
        return title == null ? 0 : number("rankValue", H_TITLE_RANK, title);
    }

    static String displayTitle(Object level, UUID villager) {
        return has(CapitalsCapability.TITLES)
                ? asString(ref("getDisplayTitleForEntity", H_DISPLAY_TITLE, level, villager)) : "";
    }

    /** The secondary court office, lower-cased, or {@code none}. */
    static String courtOffice(Object level, UUID villager) {
        if (!has(CapitalsCapability.TITLES)) {
            return "none";
        }
        String office = asEnumName(ref("getCourtOfficeLineIdForEntity", H_COURT_OFFICE, level, villager));
        return office.isEmpty() ? "none" : office;
    }

    /**
     * The plain noble title, lower-cased. Only consulted when the resolver answered nothing: a noble
     * outside any capital still holds a title, and this is the only place it is recorded.
     */
    static String nobleTitle(UUID villager) {
        return has(CapitalsCapability.TITLES)
                ? asEnumName(ref("getTitle", H_NOBLE_TITLE, villager)) : "";
    }

    // --- standing --------------------------------------------------------------------------------

    /**
     * Standing with the Crown as {@code friend}, {@code enemy} or {@code neutral}; {@code unknown}
     * only when the standing service did not bind, which is what tells content to say nothing rather
     * than to say "neither".
     */
    static String crownStanding(Object level, Object record, UUID villager) {
        if (!has(CapitalsCapability.STANDING)) {
            return "unknown";
        }
        String standing = asEnumName(ref("getStanding", H_CROWN_STANDING, level, record, villager));
        if (standing.startsWith("friend")) {
            return "friend";
        }
        return standing.startsWith("enemy") ? "enemy" : "neutral";
    }

    // --- houses ----------------------------------------------------------------------------------

    /** The house record this villager belongs to, opaque, or {@code null}. */
    @Nullable
    static Object houseForMember(Object level, UUID capitalId, UUID villager) {
        return has(CapitalsCapability.HOUSES)
                ? ref("findHouseForMember", H_HOUSE_FOR_MEMBER, level, capitalId, villager) : null;
    }

    static String houseName(@Nullable Object house) {
        return house == null ? "" : asString(ref("getHouseName", H_HOUSE_NAME, house));
    }

    static String houseTier(@Nullable Object house) {
        return house == null ? "" : asEnumName(ref("getTier", H_HOUSE_TIER, house));
    }

    /** The identity record of a <em>loaded</em> entity, opaque, or {@code null}. */
    @Nullable
    static Object identityOf(@Nullable Object loadedEntity) {
        return loadedEntity == null || !has(CapitalsCapability.HOUSES)
                ? null : ref("getIdentity", H_IDENTITY_OF, loadedEntity);
    }

    static String identityHouseName(@Nullable Object identity) {
        return identity == null ? "" : asString(ref("houseName", H_IDENTITY_HOUSE, identity));
    }

    static String identityHouseWords(@Nullable Object identity) {
        return identity == null ? "" : asString(ref("houseWords", H_IDENTITY_WORDS, identity));
    }

    static String identitySurname(@Nullable Object identity) {
        return identity == null ? "" : asString(ref("currentSurname", H_IDENTITY_SURNAME, identity));
    }

    // --- diplomacy -------------------------------------------------------------------------------

    /** Every relation on this level, keyed by the opaque relation key. */
    static Map<Object, Object> relationsSnapshot(Object level) {
        if (!has(CapitalsCapability.DIPLOMACY)) {
            return Map.of();
        }
        Object snapshot = ref("getRelationshipsSnapshot", H_RELATIONS, level);
        if (!(snapshot instanceof Map<?, ?> map)) {
            return Map.of();
        }
        Map<Object, Object> out = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                out.put(entry.getKey(), entry.getValue());
            }
        }
        return out;
    }

    static Optional<UUID> ambassador(Object level, UUID capitalId) {
        return has(CapitalsCapability.DIPLOMACY)
                ? asUuid(ref("getAmbassador", H_AMBASSADOR, level, capitalId)) : Optional.empty();
    }

    static Optional<UUID> relationFirst(Object key) {
        return asUuid(ref("first", H_KEY_FIRST, key));
    }

    static Optional<UUID> relationSecond(Object key) {
        return asUuid(ref("second", H_KEY_SECOND, key));
    }

    static String relationState(Object relation) {
        return asEnumName(ref("getDiplomaticState", H_REL_STATE, relation));
    }

    static String relationBand(Object relation) {
        return asEnumName(ref("getBand", H_REL_BAND, relation));
    }

    static int relationScore(Object relation) {
        return number("getScore", H_REL_SCORE, relation);
    }

    // --- chronicle -------------------------------------------------------------------------------

    /** The decoded entry, opaque, or {@code null} for a legacy plain-text entry. */
    @Nullable
    static Object decodeEntry(String stored) {
        return has(CapitalsCapability.CHRONICLE)
                ? ref("decodeSemanticEntry", H_DECODE_ENTRY, stored) : null;
    }

    /** A stored entry rendered to text in the server locale. Works for both entry forms. */
    static String renderStoredEntry(String stored) {
        return has(CapitalsCapability.CHRONICLE)
                ? asString(ref("renderStoredEntry", H_RENDER_STORED, stored)) : "";
    }

    static long entryDay(Object entry) {
        Object value = ref("day", H_ENTRY_DAY, entry);
        return value instanceof Number number ? number.longValue() : 0L;
    }

    /** The coarse event type, lower-cased, or {@code legacy} when there is none. */
    static String entryType(@Nullable Object entry) {
        String type = entry == null ? "" : asEnumName(ref("type", H_ENTRY_TYPE, entry));
        return type.isEmpty() ? "legacy" : type;
    }

    static String entryTranslationKey(Object entry) {
        return asString(ref("translationKey", H_ENTRY_KEY, entry));
    }

    static String entryText(Object entry) {
        return asString(ref("render", H_ENTRY_RENDER, entry));
    }

    // --- allegiance and names --------------------------------------------------------------------

    static Optional<UUID> declaredCapitalId(Object level, UUID player) {
        return has(CapitalsCapability.ALLEGIANCE)
                ? asUuid(ref("getDeclaredCapitalId", H_DECLARED_CAPITAL, level, player))
                : Optional.empty();
    }

    static String resolveDisplayName(Object level, Object record, UUID entity) {
        return has(CapitalsCapability.NAMES)
                ? asString(ref("resolveDisplayName", H_RESOLVE_NAME, level, record, entity)) : "";
    }

    static String familyNodeName(Object level, UUID entity) {
        return has(CapitalsCapability.NAMES)
                ? asString(ref("getFamilyNodeName", H_FAMILY_NAME, level, entity)) : "";
    }

    /** Capitals' own view of an MCA village name. Only a fallback; ours is asked first. */
    static String villageName(Object level, int villageId) {
        return has(CapitalsCapability.NAMES)
                ? asString(ref("getVillageName", H_VILLAGE_NAME, level, villageId)) : "";
    }

    // --- invocation ------------------------------------------------------------------------------

    /**
     * Every call goes through here. {@code invokeWithArguments} rather than a fixed-arity
     * {@code invoke} because these run per conversation and per news poll, not per villager per tick,
     * and one total call site is worth more here than the constant-folding a dozen overloads would
     * buy.
     */
    @Nullable
    private static Object ref(String site, MethodHandle handle, Object... arguments) {
        for (Object argument : arguments) {
            if (argument == null) {
                // A bound handle would throw on the cast asType inserted for the receiver, and a null
                // argument is never a question worth asking Capitals anyway.
                return null;
            }
        }
        try {
            return handle.invokeWithArguments(arguments);
        } catch (Throwable t) {
            report(site, t);
            return null;
        }
    }

    private static boolean flag(String site, MethodHandle handle, Object... arguments) {
        return ref(site, handle, arguments) instanceof Boolean value && value;
    }

    private static int number(String site, MethodHandle handle, Object... arguments) {
        return ref(site, handle, arguments) instanceof Number value ? value.intValue() : 0;
    }

    // --- conversions -----------------------------------------------------------------------------

    /**
     * Any Capitals or Minecraft value as a plain string. A {@link Component} is flattened with
     * {@code getString()} so nothing mutable or locale-bound escapes; a null becomes the empty
     * string rather than the text "null".
     */
    private static String asString(@Nullable Object value) {
        if (value == null) {
            return "";
        }
        return value instanceof Component text ? text.getString() : value.toString();
    }

    /** An enum constant as its lower-case name, so the enum type itself never crosses the boundary. */
    private static String asEnumName(@Nullable Object value) {
        return value instanceof Enum<?> constant ? constant.name().toLowerCase(Locale.ROOT) : "";
    }

    /** A nullable Capitals uuid. Absent rather than a zero uuid: "nobody" is not entity 0. */
    private static Optional<UUID> asUuid(@Nullable Object value) {
        return value instanceof UUID id ? Optional.of(id) : Optional.empty();
    }

    private static List<String> asStringList(@Nullable Object value) {
        if (!(value instanceof Iterable<?> items)) {
            return List.of();
        }
        List<String> out = new ArrayList<>();
        for (Object entry : items) {
            out.add(asString(entry));
        }
        return List.copyOf(out);
    }

    /**
     * One line per failing member, ever. A member that has moved would otherwise log a stack trace
     * per villager per poll, which is how a degraded integration turns into an unusable server.
     */
    private static void report(String site, Throwable t) {
        if (REPORTED.add(site)) {
            McaConversations.LOGGER.warn("MCA Capitals read '{}' failed; falling back to the neutral "
                    + "value for the rest of this session. Please report this with your MCA Capitals "
                    + "version.", site, t);
        }
    }
}
