package dev.otectus.mcaconversations.compat.mca;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * The typed facade over {@link McaBinding}: MCA's API expressed entirely in vanilla and JDK types.
 *
 * <p>Every field here is {@code static final} and assigned once in {@code <clinit>}, which is what
 * keeps this fast — HotSpot constant-folds a {@code static final} {@link Class} or
 * {@link MethodHandle}, so {@link #isVillager} folds to the same check {@code instanceof} would emit
 * and a bound handle inlines through to MCA's method. That matters: {@code VillagerFinder} calls
 * {@link #isVillager} on every entity near a talking player, and the dialogue conditions registered
 * through {@link #registerCondition} are evaluated once per candidate result per interaction.
 *
 * <p><b>Nothing in this class can throw.</b> Unbound members are constant stubs (see
 * {@link McaBinding.Resolution#handle}) and every accessor additionally swallows {@link Throwable}
 * and returns its documented empty value.
 *
 * <p>Keep {@link McaBinding}'s core (root probe, {@code Member}, {@code Resolution}, stub synthesis)
 * identical to the copies in MCA: Quests and MCA: Crime. Only the manifest and this facade differ per
 * mod — and only this one needs {@link #registerCondition}/{@link #registerAction}, because
 * Conversations is the only mod in the suite that hands implementations <em>back</em> to MCA.
 */
public final class McaHandles {

    private static final McaBinding.Resolution R = resolveQuietly();
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    private McaHandles() {
    }

    private static McaBinding.Resolution resolveQuietly() {
        try {
            return McaBinding.resolveAgainst(McaHandles.class.getClassLoader());
        } catch (Throwable t) {
            return McaBinding.absent();
        }
    }

    /** The live resolution, for logging and {@code /conversations debug mca}. */
    public static McaBinding.Resolution resolution() {
        return R;
    }

    /** True when MCA bound well enough to be useful. */
    public static boolean available() {
        return VILLAGER != null;
    }

    // --- classes -----------------------------------------------------------------------------------
    private static final Class<?> VILLAGER = R.cls(McaBinding.VILLAGER_CLASS);
    private static final Class<?> VILLAGER_LIKE = R.cls(McaBinding.VILLAGER_LIKE_CLASS);
    private static final Class<?> QUESTION_RESPONSE = R.cls(McaBinding.QUESTION_RESPONSE_CLASS);
    private static final Class<?> DIALOGUE_RESPONSE = R.cls(McaBinding.DIALOGUE_RESPONSE_CLASS);
    private static final Class<?> ANALYSIS_RESULTS = R.cls(McaBinding.ANALYSIS_RESULTS_CLASS);
    private static final Class<?> ACTIONS_FACTORY = R.cls(McaBinding.ACTIONS_FACTORY_CLASS);
    private static final Class<?> ACTIONS_ACTION = R.cls(McaBinding.ACTIONS_ACTION_CLASS);
    private static final Class<?> GIFT_FACTORY = R.cls(McaBinding.GIFT_FACTORY_CLASS);
    private static final Class<?> GIFT_CONDITION = R.cls(McaBinding.GIFT_CONDITION_CLASS);

    // --- handles -----------------------------------------------------------------------------------
    private static final MethodHandle H_BRAIN = R.handle(McaBinding.GET_VILLAGER_BRAIN);
    private static final MethodHandle H_MEMORY = R.handle(McaBinding.GET_LONG_TERM_MEMORY);
    private static final MethodHandle H_RESIDENCY = R.handle(McaBinding.GET_RESIDENCY);
    private static final MethodHandle H_INTERACTIONS = R.handle(McaBinding.GET_INTERACTIONS);
    private static final MethodHandle H_TRANSLATABLE = R.handle(McaBinding.GET_TRANSLATABLE);
    private static final MethodHandle H_PROFESSION_TEXT = R.handle(McaBinding.GET_PROFESSION_TEXT);
    private static final MethodHandle H_AGE_STATE = R.handle(McaBinding.GET_AGE_STATE);
    private static final MethodHandle H_MEMORIES_FOR = R.handle(McaBinding.GET_MEMORIES_FOR_PLAYER);
    private static final MethodHandle H_REWARD_HEARTS = R.handle(McaBinding.REWARD_HEARTS);
    private static final MethodHandle H_PERSONALITY = R.handle(McaBinding.GET_PERSONALITY);
    private static final MethodHandle H_MOOD = R.handle(McaBinding.GET_MOOD);
    private static final MethodHandle H_HEARTS = R.handle(McaBinding.GET_HEARTS);
    private static final MethodHandle H_MOOD_NAME = R.handle(McaBinding.MOOD_GET_NAME);
    private static final MethodHandle H_REMEMBER_FOREVER = R.handle(McaBinding.MEMORY_REMEMBER_FOREVER);
    private static final MethodHandle H_REMEMBER_FOR = R.handle(McaBinding.MEMORY_REMEMBER_FOR);
    private static final MethodHandle H_MEMORY_GET = R.handle(McaBinding.MEMORY_GET);
    private static final MethodHandle H_PARSE_ID = R.handle(McaBinding.MEMORY_PARSE_ID);
    private static final MethodHandle H_HOME_VILLAGE = R.handle(McaBinding.GET_HOME_VILLAGE);
    private static final MethodHandle H_VILLAGE_ID = R.handle(McaBinding.VILLAGE_GET_ID);
    private static final MethodHandle H_VILLAGE_NAME = R.handle(McaBinding.VILLAGE_GET_NAME);
    private static final MethodHandle H_RESIDENT_UUIDS = R.handle(McaBinding.VILLAGE_RESIDENT_UUIDS);
    private static final MethodHandle H_RESIDENT_NAMES = R.handle(McaBinding.VILLAGE_RESIDENT_NAMES);
    private static final MethodHandle H_GET_RESIDENTS = R.handle(McaBinding.VILLAGE_GET_RESIDENTS);
    private static final MethodHandle H_MANAGER_GET = R.handle(McaBinding.VILLAGE_MANAGER_GET);
    private static final MethodHandle H_MANAGER_GET_OR_EMPTY = R.handle(McaBinding.VILLAGE_MANAGER_GET_OR_EMPTY);
    private static final MethodHandle H_NEAREST_VILLAGE = R.handle(McaBinding.FIND_NEAREST_VILLAGE);
    private static final MethodHandle H_RELATIONSHIP_OF = R.handle(McaBinding.RELATIONSHIP_OF);
    private static final MethodHandle H_PARTNER_UUID = R.handle(McaBinding.GET_PARTNER_UUID);
    private static final MethodHandle H_RELATIONSHIP_WORLD = R.handle(McaBinding.GET_RELATIONSHIP_WORLD);
    private static final MethodHandle H_RELATIONSHIP_UUID = R.handle(McaBinding.GET_RELATIONSHIP_UUID);
    private static final MethodHandle H_PARTNER_NAME = R.handle(McaBinding.GET_PARTNER_NAME);
    private static final MethodHandle H_IS_MARRIED = R.handle(McaBinding.IS_MARRIED);
    private static final MethodHandle H_IS_MARRIED_TO = R.handle(McaBinding.IS_MARRIED_TO);
    private static final MethodHandle H_FAMILY_TREE_GET = R.handle(McaBinding.FAMILY_TREE_GET);
    private static final MethodHandle H_TREE_GET_OR_EMPTY = R.handle(McaBinding.FAMILY_TREE_GET_OR_EMPTY);
    private static final MethodHandle H_NODE_NAME = R.handle(McaBinding.NODE_GET_NAME);
    private static final MethodHandle H_NODE_SET_NAME = R.handle(McaBinding.NODE_SET_NAME);
    private static final MethodHandle H_SAVE_GET = R.handle(McaBinding.PLAYER_SAVE_GET);
    private static final MethodHandle H_SAVE_ENTITY_DATA = R.handle(McaBinding.PLAYER_SAVE_ENTITY_DATA);
    private static final MethodHandle H_SAVE_FAMILY_ENTRY = R.handle(McaBinding.PLAYER_SAVE_FAMILY_ENTRY);
    private static final MethodHandle H_INTERACTING_PLAYER = R.handle(McaBinding.GET_INTERACTING_PLAYER);
    private static final MethodHandle H_DIALOGUES = R.handle(McaBinding.DIALOGUES_GET_INSTANCE);
    private static final MethodHandle H_SELECT_ANSWER = R.handle(McaBinding.DIALOGUES_SELECT_ANSWER);
    private static final MethodHandle H_GET_QUESTION = R.handle(McaBinding.DIALOGUES_GET_QUESTION);
    private static final MethodHandle H_GET_ANSWER = R.handle(McaBinding.QUESTION_GET_ANSWER);
    private static final MethodHandle H_VALID_FOR_CONSTRAINT = R.handle(McaBinding.ANSWER_VALID_FOR_CONSTRAINT);
    private static final MethodHandle H_ALL_MATCHING = R.handle(McaBinding.CONSTRAINT_ALL_MATCHING);
    private static final MethodHandle H_SEND_TO_PLAYER = R.handle(McaBinding.NETWORK_SEND_TO_PLAYER);
    private static final MethodHandle H_NEW_QUESTION_RESPONSE = R.handle(McaBinding.QUESTION_RESPONSE_NEW);
    private static final MethodHandle H_QUESTION_TEXT = R.handle(McaBinding.QUESTION_RESPONSE_TEXT);
    private static final MethodHandle H_QUESTION_SILENT = R.handle(McaBinding.QUESTION_RESPONSE_SILENT);
    private static final MethodHandle H_RESPONSE_QUESTION = R.handle(McaBinding.DIALOGUE_RESPONSE_QUESTION);
    private static final MethodHandle H_RESPONSE_ANSWERS = R.handle(McaBinding.DIALOGUE_RESPONSE_ANSWERS);
    private static final MethodHandle H_CONFIG_INSTANCE = R.handle(McaBinding.CONFIG_GET_INSTANCE);
    private static final MethodHandle H_ONLINE_TTS = R.handle(McaBinding.CONFIG_ONLINE_TTS);
    private static final MethodHandle H_ACTIONS_REGISTER = R.handle(McaBinding.ACTIONS_REGISTER);
    private static final MethodHandle H_GIFT_REGISTER = R.handle(McaBinding.GIFT_REGISTER);

    // Living-histories context capabilities (spec §7.3).
    private static final MethodHandle H_PROFESSION_ID = R.handle(McaBinding.GET_PROFESSION_ID);
    private static final MethodHandle H_VILLAGER_INVENTORY = R.handle(McaBinding.GET_VILLAGER_INVENTORY);
    private static final MethodHandle H_GET_TRAITS = R.handle(McaBinding.GET_TRAITS);
    private static final MethodHandle H_TRAITS_SET = R.handle(McaBinding.TRAITS_GET_TRAITS);
    private static final MethodHandle H_TRAIT_ID = R.handle(McaBinding.TRAIT_GET_ID);
    private static final MethodHandle H_TRAIT_ID_LEGACY = R.handle(McaBinding.TRAIT_ID_LEGACY);
    private static final MethodHandle H_CURRENT_JOB = R.handle(McaBinding.BRAIN_GET_CURRENT_JOB);
    private static final MethodHandle H_IS_PANICKING = R.handle(McaBinding.BRAIN_IS_PANICKING);
    private static final MethodHandle H_SHOULD_GRIEVE = R.handle(McaBinding.BRAIN_SHOULD_GRIEVE);
    private static final MethodHandle H_WORKPLACE = R.handle(McaBinding.RESIDENCY_GET_WORKPLACE);
    private static final MethodHandle H_HOME_POS = R.handle(McaBinding.RESIDENCY_GET_HOME);
    private static final MethodHandle H_NODE_DECEASED = R.handle(McaBinding.NODE_IS_DECEASED);
    private static final MethodHandle H_NODE_PARTNER = R.handle(McaBinding.NODE_PARTNER);
    private static final MethodHandle H_NODE_FATHER = R.handle(McaBinding.NODE_FATHER);
    private static final MethodHandle H_NODE_MOTHER = R.handle(McaBinding.NODE_MOTHER);
    private static final MethodHandle H_NODE_SIBLINGS = R.handle(McaBinding.NODE_SIBLINGS);
    private static final MethodHandle H_NODE_CHILDREN = R.handle(McaBinding.NODE_CHILDREN);
    private static final MethodHandle H_NODE_PROFESSION_ID = R.handle(McaBinding.NODE_PROFESSION_ID);
    private static final MethodHandle H_VILLAGE_POPULATION = R.handle(McaBinding.VILLAGE_GET_POPULATION);
    private static final MethodHandle H_BUILDING_AT = R.handle(McaBinding.VILLAGE_BUILDING_AT);
    private static final MethodHandle H_BUILDING_TYPE = R.handle(McaBinding.BUILDING_GET_TYPE);

    // ==============================================================================================
    // Type tests
    // ==============================================================================================

    /** True for an MCA human villager (not the zombie variant). Cannot throw when MCA is absent. */
    public static boolean isVillager(Object entity) {
        Class<?> type = VILLAGER;
        return type != null && type.isInstance(entity);
    }

    /** True for anything implementing MCA's {@code VillagerLike}. */
    public static boolean isVillagerLike(Object entity) {
        Class<?> type = VILLAGER_LIKE;
        return type != null && type.isInstance(entity);
    }

    /** True for MCA's single-line dialogue packet — the one chat mode converts into a chat message. */
    public static boolean isQuestionResponse(Object message) {
        Class<?> type = QUESTION_RESPONSE;
        return type != null && type.isInstance(message);
    }

    /** True for MCA's question-with-answers packet, which chat mode records and swallows. */
    public static boolean isDialogueResponse(Object message) {
        Class<?> type = DIALOGUE_RESPONSE;
        return type != null && type.isInstance(message);
    }

    /** True for MCA's analysis packet, which has no chat equivalent and is dropped. */
    public static boolean isAnalysisResults(Object message) {
        Class<?> type = ANALYSIS_RESULTS;
        return type != null && type.isInstance(message);
    }

    // ==============================================================================================
    // Villager reads
    // ==============================================================================================

    /** The villager's localized profession text. */
    public static Component professionText(Object villager) {
        return isVillagerLike(villager) && ref(H_PROFESSION_TEXT, villager) instanceof Component c ? c : null;
    }

    /**
     * Lowercased {@code name()} of the villager's MCA age state ({@code baby} … {@code adult}), or
     * null. MCA enums never leave this class as MCA types.
     */
    public static String ageStateName(Object villager) {
        return isVillagerLike(villager) ? enumName(ref(H_AGE_STATE, villager)) : null;
    }

    /**
     * The raw {@code toString()} of MCA's personality object, or null. Deliberately unparsed here:
     * 7.6 yields {@code "ODD"} and 7.7 {@code "mca:odd"}, and {@code Personalities.normalize} owns
     * that difference.
     */
    public static String personalityString(Object villager) {
        Object personality = isVillager(villager) ? ref(H_PERSONALITY, brain(villager)) : null;
        return personality == null ? null : personality.toString();
    }

    /** The villager's current MCA mood name (e.g. {@code sad}), or null. */
    public static String moodName(Object villager) {
        Object mood = isVillager(villager) ? ref(H_MOOD, brain(villager)) : null;
        return ref(H_MOOD_NAME, mood) instanceof String s ? s : null;
    }

    /** The player's relationship hearts with this villager. Safe default: 0. */
    public static int hearts(Object villager, Player player) {
        if (!isVillager(villager) || player == null) {
            return 0;
        }
        Object memories = ref(H_MEMORIES_FOR, brain(villager), player);
        if (memories == null) {
            return 0;
        }
        try {
            return (int) H_HEARTS.invoke(memories);
        } catch (Throwable t) {
            return 0;
        }
    }

    /** Moves hearts through MCA's own reward path (particle, fatigue, advancement, mood all fire). */
    public static void rewardHearts(Object villager, ServerPlayer player, int delta) {
        Object brain = isVillager(villager) ? brain(villager) : null;
        if (brain == null || player == null) {
            return;
        }
        try {
            H_REWARD_HEARTS.invoke(brain, player, delta);
        } catch (Throwable ignored) {
            // Hearts are progression, never correctness; a failed payout must not kill the tick.
        }
    }

    /** The UUID of the player currently in a GUI interaction with this villager. Fail-open: empty. */
    public static Optional<UUID> interactingPlayer(Object villager) {
        Object handler = isVillager(villager) ? ref(H_INTERACTIONS, villager) : null;
        Object present = unwrap(ref(H_INTERACTING_PLAYER, handler));
        return present instanceof Player p ? Optional.ofNullable(p.getUUID()) : Optional.empty();
    }

    private static Object brain(Object villager) {
        return ref(H_BRAIN, villager);
    }

    // ==============================================================================================
    // LongTermMemory
    // ==============================================================================================

    /** Writes a memory entry expiring {@code durationTicks} from now. */
    public static void remember(Object villager, String id, long durationTicks) {
        Object memory = isVillager(villager) ? ref(H_MEMORY, villager) : null;
        if (memory == null || id == null) {
            return;
        }
        try {
            H_REMEMBER_FOR.invoke(memory, id, durationTicks);
        } catch (Throwable ignored) {
            // A dropped memory costs a repeated line, never a crash.
        }
    }

    /** Writes a permanent (never-expiring) memory entry. */
    public static void rememberForever(Object villager, String id) {
        Object memory = isVillager(villager) ? ref(H_MEMORY, villager) : null;
        if (memory == null || id == null) {
            return;
        }
        try {
            H_REMEMBER_FOREVER.invoke(memory, id);
        } catch (Throwable ignored) {
            // As above.
        }
    }

    /** Ticks remaining on a memory (0 = missing/expired), or empty when unreadable. */
    public static OptionalLong memoryTicks(Object villager, String id) {
        Object memory = isVillager(villager) ? ref(H_MEMORY, villager) : null;
        if (memory == null || id == null) {
            return OptionalLong.empty();
        }
        try {
            return OptionalLong.of((long) H_MEMORY_GET.invoke(memory, id));
        } catch (Throwable t) {
            return OptionalLong.empty();
        }
    }

    /** MCA's own memory-id parsing, so our {@code var} scoping matches its native {@code remember}. */
    public static String parseMemoryId(JsonObject json, ServerPlayer player) {
        try {
            return H_PARSE_ID.invoke(json, player) instanceof String s ? s : null;
        } catch (Throwable t) {
            return null;
        }
    }

    // ==============================================================================================
    // Dialogue lines and delivery
    // ==============================================================================================

    /**
     * MCA's {@code getTranslatable}: spouse-aware player name at {@code %1$s}, the personality/age lang
     * overlay markers, and (client-side) random {@code /N} variants.
     *
     * <p>{@code getTranslatable} is varargs, so the bound handle's third parameter is the
     * {@code Object[]} itself: {@code McaBinding.erase} pins the handle to fixed arity, which is what
     * makes the array pass straight through rather than be wrapped in a second array.
     */
    public static MutableComponent translatable(Object villager, ServerPlayer player, String key, Object[] args) {
        if (!isVillager(villager)) {
            return null;
        }
        try {
            Object line = H_TRANSLATABLE.invoke(villager, player, key, args == null ? new Object[0] : args);
            return line instanceof MutableComponent c ? c : null;
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Builds MCA's single-line dialogue packet and sends it through MCA's own network handler, so the
     * line lands in the open interact screen exactly as its native {@code say} action would.
     * Returns false when either half is unavailable, so the caller can fall back to plain chat.
     */
    public static boolean sendDialogueLine(ServerPlayer player, Component line) {
        if (player == null || line == null) {
            return false;
        }
        try {
            Object packet = H_NEW_QUESTION_RESPONSE.invoke(false, line);
            if (packet == null) {
                return false;
            }
            H_SEND_TO_PLAYER.invoke(packet, player);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /** The rendered line carried by MCA's single-line dialogue packet. */
    public static MutableComponent questionText(Object message) {
        return ref(H_QUESTION_TEXT, message) instanceof MutableComponent c ? c : null;
    }

    /** Whether MCA marked this dialogue packet silent (no TTS / no chime). */
    public static boolean isSilentQuestion(Object message) {
        try {
            return message != null && H_QUESTION_SILENT.invoke(message) instanceof Boolean b && b;
        } catch (Throwable t) {
            return false;
        }
    }

    /** The question id carried by MCA's question-with-answers packet. */
    public static String responseQuestion(Object message) {
        return ref(H_RESPONSE_QUESTION, message) instanceof String s ? s : null;
    }

    /** The constraint-filtered answer names MCA offered for that question. */
    @SuppressWarnings("unchecked")
    public static List<String> responseAnswers(Object message) {
        Object answers = ref(H_RESPONSE_ANSWERS, message);
        if (answers instanceof List<?> list) {
            try {
                return new ArrayList<>((List<String>) list);
            } catch (Throwable t) {
                return List.of();
            }
        }
        return List.of();
    }

    // ==============================================================================================
    // The dialogue engine
    // ==============================================================================================

    /** Drives MCA's engine exactly as a GUI button click would. Returns false on any failure. */
    public static boolean selectAnswer(Object villager, ServerPlayer player, String questionId, String answerName) {
        if (!isVillager(villager) || player == null) {
            return false;
        }
        Object dialogues = dialogues();
        if (dialogues == null) {
            return false;
        }
        try {
            H_SELECT_ANSWER.invoke(dialogues, villager, player, questionId, answerName);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * Re-runs the constraint check the GUI does at render time, because MCA's {@code selectAnswer}
     * trusts the click and does not re-validate. <b>Fails closed</b>: any unreadable step is
     * "ineligible", never "allowed".
     */
    public static boolean checkConstraints(Object villager, ServerPlayer player, String questionId,
                                           String answerName) {
        if (!isVillagerLike(villager) || player == null) {
            return false;
        }
        Object dialogues = dialogues();
        Object question = ref(H_GET_QUESTION, dialogues, questionId);
        Object answer = ref(H_GET_ANSWER, question, answerName);
        if (answer == null) {
            return false;
        }
        try {
            Object satisfied = H_ALL_MATCHING.invoke(villager, player);
            return satisfied != null && (boolean) H_VALID_FOR_CONSTRAINT.invoke(answer, satisfied);
        } catch (Throwable t) {
            return false;
        }
    }

    private static Object dialogues() {
        try {
            return H_DIALOGUES.invoke();
        } catch (Throwable t) {
            return null;
        }
    }

    // ==============================================================================================
    // Relationships and the family tree
    // ==============================================================================================

    /**
     * The villager behind a relationship handle, resolved through the relationship's own world.
     *
     * <p>Takes the relationship itself — an {@code EntityRelationship}, in practice the
     * {@code BreedableRelationship} a mixin is injecting into — not the villager, which is the thing
     * being looked up. Both accessors are inherited rather than declared on any concrete relationship
     * class, which is exactly why they are reached here rather than by {@code @Shadow}; see
     * {@link McaBinding#GET_RELATIONSHIP_WORLD}.
     *
     * <p>Safe default: empty, for an unbound member, a null world, or an entity that has since been
     * unloaded.
     */
    public static Optional<Entity> relationshipVillager(Object relationship) {
        if (relationship == null) {
            return Optional.empty();
        }
        try {
            if (!(ref(H_RELATIONSHIP_WORLD, relationship) instanceof ServerLevel level)) {
                return Optional.empty();
            }
            if (!(ref(H_RELATIONSHIP_UUID, relationship) instanceof UUID uuid)) {
                return Optional.empty();
            }
            return Optional.ofNullable(level.getEntity(uuid));
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    public static Optional<UUID> partnerUuid(Object entity) {
        Object relationship = relationshipOf(entity);
        return unwrap(ref(H_PARTNER_UUID, relationship)) instanceof UUID id ? Optional.of(id) : Optional.empty();
    }

    public static Optional<String> partnerName(Object entity) {
        Object relationship = relationshipOf(entity);
        return unwrap(ref(H_PARTNER_NAME, relationship)) instanceof Component c
                ? Optional.ofNullable(c.getString())
                : Optional.empty();
    }

    public static boolean isMarried(Object entity) {
        return boolOf(H_IS_MARRIED, relationshipOf(entity));
    }

    public static boolean isMarriedTo(Object entity, UUID playerUuid) {
        Object relationship = relationshipOf(entity);
        if (relationship == null || playerUuid == null) {
            return false;
        }
        try {
            return (boolean) H_IS_MARRIED_TO.invoke(relationship, playerUuid);
        } catch (Throwable t) {
            return false;
        }
    }

    /** Resolves a possibly-unloaded villager's name from MCA's family tree. */
    public static Optional<String> familyTreeName(ServerLevel level, UUID villagerUuid) {
        if (level == null || villagerUuid == null) {
            return Optional.empty();
        }
        Object tree = staticRef(H_FAMILY_TREE_GET, level);
        Object node = unwrap(ref(H_TREE_GET_OR_EMPTY, tree, villagerUuid));
        return ref(H_NODE_NAME, node) instanceof String s && !s.isBlank() ? Optional.of(s) : Optional.empty();
    }

    /**
     * Copies the player's chosen MCA name into their family-tree node, so MCA's {@code getTranslatable}
     * addresses them by it. No-op when they never chose one.
     */
    public static void syncPlayerFamilyName(ServerPlayer player) {
        if (player == null) {
            return;
        }
        Object data = staticRef(H_SAVE_GET, player);
        if (data == null) {
            return;
        }
        if (!(ref(H_SAVE_ENTITY_DATA, data) instanceof CompoundTag tag)) {
            return;
        }
        String chosen = tag.getString("villagerName");
        if (chosen == null || chosen.isBlank()) {
            return; // never chosen a name -> keep MCA's username fallback
        }
        Object node = ref(H_SAVE_FAMILY_ENTRY, data);
        if (node == null || chosen.equals(ref(H_NODE_NAME, node))) {
            return;
        }
        try {
            H_NODE_SET_NAME.invoke(node, chosen); // setName() -> markDirty() -> persists
        } catch (Throwable ignored) {
            // Cosmetic; the vanilla username remains a correct fallback.
        }
    }

    private static Object relationshipOf(Object entity) {
        if (entity == null) {
            return null;
        }
        try {
            return unwrap(H_RELATIONSHIP_OF.invoke(entity));
        } catch (Throwable t) {
            return null;
        }
    }

    // ==============================================================================================
    // Villages
    // ==============================================================================================

    public static OptionalInt homeVillageId(Object villager) {
        Object village = homeVillage(villager);
        return village == null ? OptionalInt.empty() : villageId(village);
    }

    public static Optional<String> homeVillageName(Object villager) {
        return ref(H_VILLAGE_NAME, homeVillage(villager)) instanceof String s ? Optional.of(s) : Optional.empty();
    }

    /** MCA's own {@code Village} object, handed back opaquely for the Townstead binding. */
    public static Optional<Object> villageHandle(ServerLevel level, int villageId) {
        return Optional.ofNullable(village(level, villageId));
    }

    public static OptionalInt nearestVillageId(ServerLevel level, BlockPos pos, int radius) {
        Object manager = staticRef(H_MANAGER_GET, level);
        if (manager == null || pos == null) {
            return OptionalInt.empty();
        }
        try {
            Object village = unwrap(H_NEAREST_VILLAGE.invoke(manager, pos, radius));
            return village == null ? OptionalInt.empty() : villageId(village);
        } catch (Throwable t) {
            return OptionalInt.empty();
        }
    }

    public static List<Entity> loadedResidents(ServerLevel level, int villageId) {
        Object residents = ref(H_GET_RESIDENTS, village(level, villageId), level);
        List<Entity> out = new ArrayList<>();
        if (residents instanceof List<?> list) {
            for (Object o : list) {
                if (o instanceof Entity e) {
                    out.add(e);
                }
            }
        }
        return out;
    }

    public static Set<UUID> residentUuids(ServerLevel level, int villageId) {
        Object stream = ref(H_RESIDENT_UUIDS, village(level, villageId));
        Set<UUID> out = new HashSet<>();
        if (stream instanceof Stream<?> s) {
            try {
                s.forEach(e -> {
                    if (e instanceof UUID id) {
                        out.add(id);
                    }
                });
            } catch (Throwable ignored) {
                return out;
            }
        }
        return out;
    }

    public static Map<UUID, String> residentNames(ServerLevel level, int villageId) {
        Object names = ref(H_RESIDENT_NAMES, village(level, villageId));
        Map<UUID, String> out = new HashMap<>();
        if (names instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> e : map.entrySet()) {
                if (e.getKey() instanceof UUID id && e.getValue() instanceof String name) {
                    out.put(id, name);
                }
            }
        }
        return out;
    }

    private static Object homeVillage(Object villager) {
        Object residency = isVillager(villager) ? ref(H_RESIDENCY, villager) : null;
        return unwrap(ref(H_HOME_VILLAGE, residency));
    }

    private static Object village(ServerLevel level, int villageId) {
        Object manager = staticRef(H_MANAGER_GET, level);
        return unwrap(ref(H_MANAGER_GET_OR_EMPTY, manager, villageId));
    }

    private static OptionalInt villageId(Object village) {
        try {
            return OptionalInt.of((int) H_VILLAGE_ID.invoke(village));
        } catch (Throwable t) {
            // Distinguished from a real id of 0: an unreadable village is "no village", not village 0.
            return OptionalInt.empty();
        }
    }

    /**
     * MCA's own online-TTS switch. Read so the locale hook can re-test MCA's guard verbatim rather
     * than widening it: MCA's voice packs record one audio file per base key and cannot follow a
     * personality-specific key, so overriding that check would silently break voiced dialogue.
     * Defaults to {@code true} (i.e. "leave MCA's answer alone") when unreadable.
     */
    public static boolean onlineTtsEnabled() {
        try {
            Object config = H_CONFIG_INSTANCE.invoke();
            return config == null || !(H_ONLINE_TTS.invoke(config) instanceof Boolean b) || b;
        } catch (Throwable t) {
            return true;
        }
    }

    // ==============================================================================================
    // Registering our own conditions and actions with MCA
    // ==============================================================================================

    /** One dialogue condition body. Takes the villager as a vanilla {@link Entity}, never an MCA type. */
    @FunctionalInterface
    public interface ConditionBody {
        float test(Entity villager, ItemStack stack, ServerPlayer player);
    }

    /** One dialogue action body. */
    @FunctionalInterface
    public interface ActionBody {
        void trigger(Entity villager, ServerPlayer player);
    }

    /**
     * Registers a dialogue condition under MCA's gift-predicate registry, which its dialogue JSON
     * shares.
     *
     * <p>MCA's {@code Factory}/{@code Condition} are single-abstract-method interfaces, so
     * {@link MethodHandleProxies#asInterfaceInstance} can supply them: it returns an object that
     * genuinely implements the interface, which means {@code Condition.and(..)} — a {@code default}
     * method MCA calls when a result combines conditions — is inherited rather than something we would
     * have to reimplement. A bare {@link java.lang.reflect.Proxy} would route {@code and} to the
     * invocation handler and silently break combined conditions.
     *
     * <p>The parser argument is a plain JDK {@link BiFunction} and passes through untouched.
     */
    @SuppressWarnings("unchecked")
    public static <T> void registerCondition(String key, BiFunction<JsonElement, String, T> parser,
                                             Function<T, ConditionBody> factory) {
        // The cast is safe by construction: MCA only ever calls parse(..) with what our own parser
        // returned, and the erased Factory.parse signature is the reason it arrives as Object.
        register(H_GIFT_REGISTER, GIFT_FACTORY, GIFT_CONDITION, key, parser,
                parsed -> conditionInstance(factory.apply((T) parsed)));
    }

    /** Registers a dialogue action. Same proxy mechanism as {@link #registerCondition}. */
    @SuppressWarnings("unchecked")
    public static <T> void registerAction(String key, BiFunction<JsonElement, String, T> parser,
                                          Function<T, ActionBody> factory) {
        register(H_ACTIONS_REGISTER, ACTIONS_FACTORY, ACTIONS_ACTION, key, parser,
                parsed -> actionInstance(factory.apply((T) parsed)));
    }

    @SuppressWarnings("unchecked")
    private static <T> void register(MethodHandle registrar, Class<?> factoryType, Class<?> bodyType,
                                     String key, BiFunction<JsonElement, String, T> parser,
                                     Function<Object, Object> bodyFactory) {
        if (factoryType == null || bodyType == null) {
            return; // MCA absent or reshaped; the feature simply never appears
        }
        try {
            // Function.apply erases to (Object)Object; asType narrows the return to MCA's own
            // interface so the value handed back through parse(..) passes MCA's checkcast.
            MethodHandle apply = LOOKUP
                    .findVirtual(Function.class, "apply", MethodType.methodType(Object.class, Object.class))
                    .bindTo(bodyFactory)
                    .asType(MethodType.methodType(bodyType, Object.class));
            Object factoryProxy = MethodHandleProxies.asInterfaceInstance(factoryType, apply);
            registrar.invoke(key, parser, factoryProxy);
        } catch (Throwable t) {
            // A condition MCA never learned about scores 0 and an action never fires: the dialogue
            // still loads, which is the whole point of not throwing out of registration.
            dev.otectus.mcaconversations.McaConversations.LOGGER.debug(
                    "MCA registration of '{}' failed; that binding will be inert", key, t);
        }
    }

    private static Object conditionInstance(ConditionBody body) {
        return conditionInstance(GIFT_CONDITION, VILLAGER, body);
    }

    private static Object actionInstance(ActionBody body) {
        return actionInstance(ACTIONS_ACTION, VILLAGER, body);
    }

    /**
     * Wraps one of our condition bodies as MCA's {@code GiftPredicate.Condition}.
     *
     * <p>Package-private and parameterised on the two MCA classes rather than reading the static
     * fields, so {@code McaProxyBindingTest} can drive this exact code against a real MCA jar opened
     * in a throwaway class loader. This is the one genuinely novel step in the binding — everything
     * else only ever calls <em>into</em> MCA — so it is worth being able to test rather than only
     * observe in game.
     */
    static Object conditionInstance(Class<?> conditionType, Class<?> villagerType, ConditionBody body) {
        if (body == null || conditionType == null || villagerType == null) {
            return null;
        }
        try {
            return MethodHandleProxies.asInterfaceInstance(conditionType, conditionHandle(villagerType, body));
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * The handle {@link #conditionInstance} hands to {@code MethodHandleProxies}. Split out so
     * {@code McaProxyBindingTest} can assert its type is <em>exactly</em> MCA's
     * {@code Condition.test} signature: that equality is the whole contract
     * {@link MethodHandleProxies#asInterfaceInstance} enforces, and getting it wrong is the one way
     * registration fails silently.
     */
    static MethodHandle conditionHandle(Class<?> villagerType, ConditionBody body) throws Throwable {
        return LOOKUP
                .findVirtual(ConditionBody.class, "test",
                        MethodType.methodType(float.class, Entity.class, ItemStack.class, ServerPlayer.class))
                .bindTo(body)
                // MCA's Condition.test takes VillagerEntityMCA; widening it to Entity is what lets
                // the body be written against vanilla types.
                .asType(MethodType.methodType(float.class, villagerType, ItemStack.class, ServerPlayer.class));
    }

    /** Wraps one of our action bodies as MCA's {@code Actions.Action}. See the condition twin above. */
    static Object actionInstance(Class<?> actionType, Class<?> villagerType, ActionBody body) {
        if (body == null || actionType == null || villagerType == null) {
            return null;
        }
        try {
            return MethodHandleProxies.asInterfaceInstance(actionType, actionHandle(villagerType, body));
        } catch (Throwable t) {
            return null;
        }
    }

    /** The action twin of {@link #conditionHandle}. */
    static MethodHandle actionHandle(Class<?> villagerType, ActionBody body) throws Throwable {
        return LOOKUP
                .findVirtual(ActionBody.class, "trigger",
                        MethodType.methodType(void.class, Entity.class, ServerPlayer.class))
                .bindTo(body)
                .asType(MethodType.methodType(void.class, villagerType, ServerPlayer.class));
    }

    // ==============================================================================================
    // Living-histories context capabilities (spec §7.3)
    //
    // Every accessor here answers one field of ConversationContextSnapshot. They return Optional or an
    // empty collection on any miss, so the context source can report the field UNKNOWN rather than
    // inventing a default — the whole point of the capability group (spec §10.7).
    // ==============================================================================================

    /** The exact profession registry id, {@code "minecraft:farmer"}. Empty when unbound or unset. */
    public static Optional<String> professionId(Object villager) {
        if (!isVillager(villager)) {
            return Optional.empty();
        }
        Object id = ref(H_PROFESSION_ID, villager);
        return id == null ? Optional.empty() : Optional.of(id.toString());
    }

    /** MCA's assigned chore, lowercased ({@code none}, {@code harvest}, {@code fish}…). */
    public static Optional<String> currentChore(Object villager) {
        String name = isVillager(villager) ? enumName(ref(H_CURRENT_JOB, brain(villager))) : null;
        return name == null ? Optional.empty() : Optional.of(name);
    }

    /** True while MCA's brain says the villager is panicking — ordinary initiative must stay quiet. */
    public static boolean isPanicking(Object villager) {
        return isVillager(villager) && boolOf(H_IS_PANICKING, brain(villager));
    }

    /** True while MCA's brain says the villager is grieving. */
    public static boolean isGrieving(Object villager) {
        return isVillager(villager) && boolOf(H_SHOULD_GRIEVE, brain(villager));
    }

    /** The assigned workplace block, if MCA has one for this villager. */
    public static Optional<BlockPos> workplace(Object villager) {
        Object residency = isVillager(villager) ? ref(H_RESIDENCY, villager) : null;
        // getWorkplace() returns a bare BlockPos, not an Optional: unwrapping it would always be null.
        return ref(H_WORKPLACE, residency) instanceof BlockPos pos ? Optional.of(pos) : Optional.empty();
    }

    /** The assigned home block, dropping the dimension of MCA's {@code GlobalPos}. */
    public static Optional<BlockPos> homePos(Object villager) {
        Object residency = isVillager(villager) ? ref(H_RESIDENCY, villager) : null;
        Object global = unwrap(ref(H_HOME_POS, residency));
        if (global instanceof net.minecraft.core.GlobalPos gp) {
            return Optional.of(gp.pos());
        }
        return Optional.empty();
    }

    /**
     * Registry ids of the villager's MCA traits.
     *
     * <p>Read through {@code Traits#getTraits} and {@code Trait#getId} rather than {@code hasTrait},
     * because {@code hasTrait} has two arity-1 overloads and would need a parameter hint for something
     * the set already answers.
     */
    public static Set<String> traitIds(Object villager) {
        Object traits = isVillagerLike(villager) ? ref(H_GET_TRAITS, villager) : null;
        Object set = ref(H_TRAITS_SET, traits);
        if (!(set instanceof Iterable<?> items)) {
            return Set.of();
        }
        Set<String> out = new HashSet<>();
        for (Object trait : items) {
            // 7.7's getId() first, then 7.6's id(). An unbound member is a stub returning null, so the
            // fallback costs one null check rather than a version test.
            Object id = ref(H_TRAIT_ID, trait);
            if (id == null) {
                id = ref(H_TRAIT_ID_LEGACY, trait);
            }
            if (id != null) {
                out.add(id.toString().toLowerCase(Locale.ROOT));
            }
        }
        return Set.copyOf(out);
    }

    /**
     * Which of {@code probes} the villager is carrying at least one of.
     *
     * <p>Presence only. A count would be an economy claim, and the plan is explicit that a villager
     * may say "I have iron" and may not say "the village consumed twelve iron" (spec §12.2).
     */
    public static Set<String> inventoryTags(Object villager, Collection<String> probes) {
        if (!isVillager(villager) || probes == null || probes.isEmpty()) {
            return Set.of();
        }
        Object inventory = ref(H_VILLAGER_INVENTORY, villager);
        if (!(inventory instanceof net.minecraft.world.Container container)) {
            return Set.of();
        }
        Set<String> found = new HashSet<>();
        try {
            for (int slot = 0; slot < container.getContainerSize(); slot++) {
                ItemStack stack = container.getItem(slot);
                if (stack == null || stack.isEmpty()) {
                    continue;
                }
                for (String probe : probes) {
                    if (found.contains(probe)) {
                        continue;
                    }
                    net.minecraft.resources.ResourceLocation id =
                            net.minecraft.resources.ResourceLocation.tryParse(probe);
                    if (id != null && stack.is(net.minecraft.tags.TagKey.create(
                            net.minecraft.core.registries.Registries.ITEM, id))) {
                        found.add(probe);
                    }
                }
            }
        } catch (Throwable t) {
            return Set.copyOf(found);
        }
        return Set.copyOf(found);
    }

    // --- Family tree, read as the authoritative social graph (spec §16.1) -------------------------

    /** True when MCA's family tree records this villager as dead — the referent-drift guard. */
    public static boolean isDeceased(ServerLevel level, UUID villagerUuid) {
        return boolOf(H_NODE_DECEASED, familyNode(level, villagerUuid));
    }

    public static Optional<UUID> partnerOf(ServerLevel level, UUID villagerUuid) {
        return nodeUuid(H_NODE_PARTNER, familyNode(level, villagerUuid));
    }

    /** Father then mother, in that order, skipping absent and MCA's zero-UUID placeholder. */
    public static List<UUID> parentsOf(ServerLevel level, UUID villagerUuid) {
        Object node = familyNode(level, villagerUuid);
        List<UUID> out = new ArrayList<>();
        nodeUuid(H_NODE_FATHER, node).ifPresent(out::add);
        nodeUuid(H_NODE_MOTHER, node).ifPresent(out::add);
        return List.copyOf(out);
    }

    public static Set<UUID> siblingsOf(ServerLevel level, UUID villagerUuid) {
        return nodeUuidSet(H_NODE_SIBLINGS, familyNode(level, villagerUuid));
    }

    public static Set<UUID> childrenOf(ServerLevel level, UUID villagerUuid) {
        return nodeUuidSet(H_NODE_CHILDREN, familyNode(level, villagerUuid));
    }

    /** A family member's profession id, so "my sister the mason" is a fact rather than a guess. */
    public static Optional<String> familyTreeProfessionId(ServerLevel level, UUID villagerUuid) {
        Object id = ref(H_NODE_PROFESSION_ID, familyNode(level, villagerUuid));
        return id == null ? Optional.empty() : Optional.of(id.toString());
    }

    // --- Village and buildings --------------------------------------------------------------------

    public static OptionalInt villagePopulation(ServerLevel level, int villageId) {
        Object village = village(level, villageId);
        if (village == null) {
            return OptionalInt.empty();
        }
        try {
            return OptionalInt.of((int) H_VILLAGE_POPULATION.invoke(village));
        } catch (Throwable t) {
            return OptionalInt.empty();
        }
    }

    /** MCA's building type token at {@code pos} ({@code library}, {@code smithy}, {@code house}…). */
    public static Optional<String> buildingTypeAt(ServerLevel level, int villageId, BlockPos pos) {
        if (pos == null) {
            return Optional.empty();
        }
        Object building = unwrap(ref(H_BUILDING_AT, village(level, villageId), pos));
        return ref(H_BUILDING_TYPE, building) instanceof String type && !type.isBlank()
                ? Optional.of(type.toLowerCase(Locale.ROOT))
                : Optional.empty();
    }

    private static Object familyNode(ServerLevel level, UUID villagerUuid) {
        if (level == null || villagerUuid == null) {
            return null;
        }
        Object tree = staticRef(H_FAMILY_TREE_GET, level);
        return unwrap(ref(H_TREE_GET_OR_EMPTY, tree, villagerUuid));
    }

    /** MCA writes an all-zero UUID where a relation is absent; that is "nobody", not a resident. */
    private static Optional<UUID> nodeUuid(MethodHandle handle, Object node) {
        Object value = ref(handle, node);
        if (value instanceof UUID uuid
                && (uuid.getMostSignificantBits() != 0L || uuid.getLeastSignificantBits() != 0L)) {
            return Optional.of(uuid);
        }
        return Optional.empty();
    }

    private static Set<UUID> nodeUuidSet(MethodHandle handle, Object node) {
        Object value = ref(handle, node);
        if (!(value instanceof Iterable<?> items)) {
            return Set.of();
        }
        Set<UUID> out = new HashSet<>();
        for (Object item : items) {
            if (item instanceof UUID uuid
                    && (uuid.getMostSignificantBits() != 0L || uuid.getLeastSignificantBits() != 0L)) {
                out.add(uuid);
            }
        }
        return Set.copyOf(out);
    }

    // ==============================================================================================
    // Shared containment
    // ==============================================================================================

    private static Object ref(MethodHandle handle, Object receiver) {
        // The receiver null-check is not redundant with the stub contract: a *bound* handle would
        // throw NullPointerException on the receiver cast that asType inserted.
        if (receiver == null) {
            return null;
        }
        try {
            return handle.invoke(receiver);
        } catch (Throwable t) {
            return null;
        }
    }

    private static Object ref(MethodHandle handle, Object receiver, Object a) {
        if (receiver == null) {
            return null;
        }
        try {
            return handle.invoke(receiver, a);
        } catch (Throwable t) {
            return null;
        }
    }

    /** A static member: no receiver slot, so null arguments are the caller's business, not ours. */
    private static Object staticRef(MethodHandle handle, Object a) {
        if (a == null) {
            return null;
        }
        try {
            return handle.invoke(a);
        } catch (Throwable t) {
            return null;
        }
    }

    private static boolean boolOf(MethodHandle handle, Object receiver) {
        if (receiver == null) {
            return false;
        }
        try {
            return (boolean) handle.invoke(receiver);
        } catch (Throwable t) {
            return false;
        }
    }

    private static Object unwrap(Object maybeOptional) {
        return maybeOptional instanceof Optional<?> opt ? opt.orElse(null) : null;
    }

    /** Lowercased {@code name()} of an MCA enum value, or null — how every enum read leaves this class. */
    private static String enumName(Object value) {
        return value instanceof Enum<?> e ? e.name().toLowerCase(Locale.ROOT) : null;
    }
}
