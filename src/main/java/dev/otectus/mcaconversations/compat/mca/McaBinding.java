package dev.otectus.mcaconversations.compat.mca;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.server.level.ServerLevel;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * Resolves Minecraft Comes Alive: Reborn at <em>runtime</em>, by name, so one MCA: Conversations jar works
 * across MCA's package-root migrations instead of hard-linking one of them.
 *
 * <h2>Why this exists</h2>
 *
 * <p>MCA repackaged mid-line. Through 7.6.20 it shipped a Forgix-merged jar whose Forge classes live
 * at {@code forge.net.mca.*}; a later 7.7 build dropped the merge and renamed the base package to
 * {@code net.conczin.mca.*}. Because {@code McaCompat} used to {@code import forge.net.mca.*}, the
 * very first MCA reference on a renamed build threw
 * {@code NoClassDefFoundError: forge/net/mca/entity/VillagerEntityMCA} — from an
 * {@code EntityInteract} handler, so a dedicated server died the instant any player right-clicked any
 * entity.
 *
 * <p><b>The root cannot be inferred from the version number</b>: 7.7.0-beta.2 still ships
 * {@code forge.net.mca} while later 7.7 builds do not. So this is a class probe, never a version
 * comparison. Class-relative names are identical across every layout seen, so the whole difference is
 * the one prefix in {@link #CANDIDATE_ROOTS}.
 *
 * <h2>The contract</h2>
 *
 * <p><b>Resolution never throws and never returns null.</b> An unresolved member becomes a
 * <em>constant stub</em>: a {@link MethodHandle} of the identical erased type that returns the type's
 * default ({@code null}/{@code 0}/{@code false}/nothing). That is what makes per-member degradation
 * free — callers need no null checks, and a member MCA removed simply reads as "absent" rather than
 * exploding. Whole-class failures degrade the same way, via {@link Resolution#cls} returning null and
 * every dependent member falling back to a stub.
 *
 * <p>Members are declared in {@link #MANIFEST} as {@link Member} constants, which are the only keys
 * {@link McaHandles} uses — so the manifest is the single source of truth for what this mod needs
 * from MCA, and {@code McaBindingProbeTest} can replay it against any MCA jar in a throwaway
 * {@link ClassLoader} without loading a single MCA class into the test JVM.
 *
 * @see McaHandles for the resolved handles themselves
 */
public final class McaBinding {

    /**
     * Package roots to probe, in order. Each ends with a dot and is stored <em>dotted</em>, never in
     * internal slash form — that is what lets {@code NoMcaStaticLinkTest} byte-scan compiled classes
     * for slash-form MCA references and treat any hit as a regression.
     *
     * <p>One entry on 1.21.1: MCA ships a per-loader jar here rather than a Forgix-merged one, so the
     * {@code forge.} relocations the 1.20.1 line had to probe for do not exist and the renamed base
     * package is the only layout. The probe itself is kept whole — a future root is one array entry,
     * not a rewrite, which is the whole reason nothing below names a package literally.
     */
    private static final String[] CANDIDATE_ROOTS = {
            "net.conczin.mca.", // per-loader jar, renamed base package
    };

    /** The class whose presence identifies a root. Every layout has it at this relative name. */
    private static final String PROBE_CLASS = "entity.VillagerEntityMCA";

    public enum Status {
        /** No MCA on the classloader at all. */
        ABSENT,
        /** MCA is loaded but no candidate root matched — an unknown future layout. */
        UNBINDABLE,
        /** Root found, but at least one required member did not resolve. */
        PARTIAL,
        /** Everything required resolved. */
        BOUND
    }

    // ---------------------------------------------------------------------------------------------
    // Member descriptors
    // ---------------------------------------------------------------------------------------------

    private enum Kind { CLASS, VIRTUAL, STATIC, GETTER, CONSTRUCTOR }

    /**
     * One thing this mod needs from MCA, named relative to the package root. Identity-compared, so
     * {@link McaHandles} refers to members by constant rather than by a string that could typo.
     */
    public static final class Member {
        private final Kind kind;
        private final String ownerRelative;
        private final String name;
        private final Class<?> returnType;
        private final int arity;
        private final Class<?> firstParamHint;
        private final boolean required;

        private Member(Kind kind, String ownerRelative, String name, Class<?> returnType, int arity,
                       Class<?> firstParamHint, boolean required) {
            this.kind = kind;
            this.ownerRelative = ownerRelative;
            this.name = name;
            this.returnType = returnType;
            this.arity = arity;
            this.firstParamHint = firstParamHint;
            this.required = required;
        }

        /** {@code true} when a miss should fail the build rather than merely degrade a feature. */
        public boolean required() {
            return required;
        }

        @Override
        public String toString() {
            return switch (kind) {
                case CLASS -> ownerRelative;
                case GETTER -> ownerRelative + "." + name;
                default -> ownerRelative + "#" + name + "/" + arity;
            };
        }

        /**
         * The erased handle shape. Every parameter is {@link Object} (including the receiver for a
         * virtual) and {@code asType} does the boxing, so callers pass plain references; only the
         * return type is kept faithful, so a primitive stub can be a real {@code 0}/{@code false}.
         */
        private MethodType erasedType() {
            int params = switch (kind) {
                case VIRTUAL, GETTER -> arity + 1; // receiver first
                case STATIC, CONSTRUCTOR -> arity;
                case CLASS -> 0;
            };
            return MethodType.methodType(returnType, Collections.nCopies(params, Object.class));
        }
    }

    private static Member cls(String ownerRelative) {
        return new Member(Kind.CLASS, ownerRelative, "<class>", void.class, 0, null, true);
    }

    private static Member virtual(String ownerRelative, String name, Class<?> ret, int arity) {
        return new Member(Kind.VIRTUAL, ownerRelative, name, ret, arity, null, true);
    }

    private static Member virtual(String ownerRelative, String name, Class<?> ret, int arity, Class<?> hint) {
        return new Member(Kind.VIRTUAL, ownerRelative, name, ret, arity, hint, true);
    }

    /** As {@link #virtual}, but a miss is recorded and tolerated instead of failing the probe test. */
    private static Member optionalVirtual(String ownerRelative, String name, Class<?> ret, int arity) {
        return new Member(Kind.VIRTUAL, ownerRelative, name, ret, arity, null, false);
    }

    private static Member statik(String ownerRelative, String name, Class<?> ret, int arity) {
        return new Member(Kind.STATIC, ownerRelative, name, ret, arity, null, true);
    }

    private static Member getter(String ownerRelative, String field) {
        return new Member(Kind.GETTER, ownerRelative, field, Object.class, 0, null, true);
    }

    /** As {@link #getter}, but a miss is recorded and tolerated instead of failing the probe test. */
    private static Member optionalGetter(String ownerRelative, String field) {
        return new Member(Kind.GETTER, ownerRelative, field, Object.class, 0, null, false);
    }

    /**
     * A constructor, resolved by arity. Conversations is the only mod in the suite that has to
     * <em>build</em> an MCA object rather than only read from one: chat delivery hands MCA's own
     * dialogue packet back to its network layer.
     */
    private static Member constructor(String ownerRelative, int arity) {
        return new Member(Kind.CONSTRUCTOR, ownerRelative, "<init>", Object.class, arity, null, true);
    }

    // ---------------------------------------------------------------------------------------------
    // The manifest - every MCA class and member MCA: Conversations depends on.
    //
    // Verified present and signature-identical in both 1.21.1 builds of the probe fleet;
    // McaBindingProbeTest replays every one of them on every build. Three entries are ambiguous by
    // name alone and are called out below.
    //
    // Deliberately absent: Personality. MCA 7.6 declares it as an enum and 7.7 as a registry-backed
    // class, so neither name() nor getPersonalityId() exists in both -- toString() does, and
    // Personalities.normalize reduces "ODD" and "mca:odd" alike to "odd". Reading it as an opaque
    // Object and calling toString() needs no manifest entry at all.
    // ---------------------------------------------------------------------------------------------

    private static final String C_VILLAGER = "entity.VillagerEntityMCA";
    private static final String C_VILLAGER_LIKE = "entity.VillagerLike";
    private static final String C_BRAIN = "entity.ai.brain.VillagerBrain";
    private static final String C_MEMORIES = "entity.ai.Memories";
    private static final String C_MOOD = "entity.ai.Mood";
    private static final String C_MEMORY = "entity.ai.LongTermMemory";
    private static final String C_RESIDENCY = "entity.ai.Residency";
    private static final String C_RELATIONSHIP = "entity.ai.relationship.EntityRelationship";
    private static final String C_COMMAND_HANDLER = "entity.interaction.EntityCommandHandler";
    private static final String C_CONSTRAINT = "entity.interaction.Constraint";
    private static final String C_CONFIG = "Config";
    private static final String C_NETWORK = "network.Network";
    private static final String C_QUESTION_RESPONSE = "network.s2c.InteractionDialogueQuestionResponse";
    private static final String C_DIALOGUE_RESPONSE = "network.s2c.InteractionDialogueResponse";
    private static final String C_ANALYSIS_RESULTS = "network.s2c.AnalysisResults";
    private static final String C_DIALOGUES = "resources.Dialogues";
    private static final String C_QUESTION = "resources.data.dialogue.Question";
    private static final String C_ANSWER = "resources.data.dialogue.Answer";
    private static final String C_ACTIONS = "resources.data.dialogue.Actions";
    private static final String C_ACTIONS_FACTORY = "resources.data.dialogue.Actions$Factory";
    private static final String C_ACTIONS_ACTION = "resources.data.dialogue.Actions$Action";
    private static final String C_GIFT_PREDICATE = "entity.interaction.gifts.GiftPredicate";
    private static final String C_GIFT_FACTORY = "entity.interaction.gifts.GiftPredicate$Factory";
    private static final String C_GIFT_CONDITION = "entity.interaction.gifts.GiftPredicate$Condition";
    private static final String C_FAMILY_TREE = "server.world.data.FamilyTree";
    private static final String C_FAMILY_NODE = "server.world.data.FamilyTreeNode";
    private static final String C_PLAYER_SAVE = "server.world.data.PlayerSaveData";
    private static final String C_VILLAGE = "server.world.data.Village";
    private static final String C_VILLAGE_MANAGER = "server.world.data.VillageManager";
    private static final String C_BUILDING = "server.world.data.Building";
    private static final String C_TRAITS = "entity.ai.Traits";
    private static final String C_TRAIT = "entity.ai.Traits$Trait";

    // Classes ---------------------------------------------------------------------------------------
    public static final Member VILLAGER_CLASS = cls(C_VILLAGER);
    public static final Member VILLAGER_LIKE_CLASS = cls(C_VILLAGER_LIKE);
    public static final Member QUESTION_RESPONSE_CLASS = cls(C_QUESTION_RESPONSE);
    public static final Member DIALOGUE_RESPONSE_CLASS = cls(C_DIALOGUE_RESPONSE);
    public static final Member ANALYSIS_RESULTS_CLASS = cls(C_ANALYSIS_RESULTS);

    // The four SAM interfaces our dialogue conditions and actions are handed back to MCA as. Declared
    // here so the Class comes from the probed root: writing either name as a literal at the proxy site
    // would put an MCA package straight back into our constant pool and fail NoMcaStaticLinkTest.
    public static final Member ACTIONS_FACTORY_CLASS = cls(C_ACTIONS_FACTORY);
    public static final Member ACTIONS_ACTION_CLASS = cls(C_ACTIONS_ACTION);
    public static final Member GIFT_FACTORY_CLASS = cls(C_GIFT_FACTORY);
    public static final Member GIFT_CONDITION_CLASS = cls(C_GIFT_CONDITION);

    // VillagerEntityMCA -----------------------------------------------------------------------------
    public static final Member GET_VILLAGER_BRAIN = virtual(C_VILLAGER, "getVillagerBrain", Object.class, 0);
    public static final Member GET_LONG_TERM_MEMORY = virtual(C_VILLAGER, "getLongTermMemory", Object.class, 0);
    public static final Member GET_RESIDENCY = virtual(C_VILLAGER, "getResidency", Object.class, 0);
    /**
     * AMBIGUOUS BY NAME: a covariant override leaves two arity-0 {@code getInteractions} entries, the
     * real {@code VillagerCommandHandler} one and an {@code EntityCommandHandler} bridge.
     * {@code bindMethod} skips bridges, which is what makes this deterministic.
     */
    public static final Member GET_INTERACTIONS = virtual(C_VILLAGER, "getInteractions", Object.class, 0);
    /**
     * Inherited from the {@code Messenger} interface, and VARARGS: {@code (Player, String, Object...)}
     * is arity 3 with an {@code Object[]} tail. {@link #erase} pins the handle to fixed arity, so the
     * call site passes the argument array itself as the third argument rather than spreading it.
     */
    public static final Member GET_TRANSLATABLE = virtual(C_VILLAGER, "getTranslatable", Object.class, 3);

    // VillagerLike ----------------------------------------------------------------------------------
    public static final Member GET_PROFESSION_TEXT = virtual(C_VILLAGER_LIKE, "getProfessionText", Object.class, 0);
    public static final Member GET_AGE_STATE = virtual(C_VILLAGER_LIKE, "getAgeState", Object.class, 0);

    // Brain / hearts / mood -------------------------------------------------------------------------
    // getMemoriesForPlayer takes net.minecraft.world.entity.player.Player, not ServerPlayer; the erased
    // Object parameter shape accepts either, so no hint is needed.
    public static final Member GET_MEMORIES_FOR_PLAYER = virtual(C_BRAIN, "getMemoriesForPlayer", Object.class, 1);
    public static final Member REWARD_HEARTS = virtual(C_BRAIN, "rewardHearts", void.class, 2);
    public static final Member GET_PERSONALITY = virtual(C_BRAIN, "getPersonality", Object.class, 0);
    public static final Member GET_MOOD = virtual(C_BRAIN, "getMood", Object.class, 0);
    public static final Member GET_HEARTS = virtual(C_MEMORIES, "getHearts", int.class, 0);
    public static final Member MOOD_GET_NAME = virtual(C_MOOD, "getName", Object.class, 0);

    // LongTermMemory --------------------------------------------------------------------------------
    // AMBIGUOUS BY NAME: remember(String) and remember(String, long) are separated by arity.
    public static final Member MEMORY_REMEMBER_FOREVER = virtual(C_MEMORY, "remember", void.class, 1);
    public static final Member MEMORY_REMEMBER_FOR = virtual(C_MEMORY, "remember", void.class, 2);
    public static final Member MEMORY_GET = virtual(C_MEMORY, "getMemory", long.class, 1);
    public static final Member MEMORY_PARSE_ID = statik(C_MEMORY, "parseId", Object.class, 2);

    // Residency / Village / VillageManager ------------------------------------------------------------
    public static final Member GET_HOME_VILLAGE = virtual(C_RESIDENCY, "getHomeVillage", Object.class, 0);
    public static final Member VILLAGE_GET_ID = virtual(C_VILLAGE, "getId", int.class, 0);
    public static final Member VILLAGE_GET_NAME = virtual(C_VILLAGE, "getName", Object.class, 0);
    public static final Member VILLAGE_RESIDENT_UUIDS = virtual(C_VILLAGE, "getResidentsUUIDs", Object.class, 0);
    public static final Member VILLAGE_RESIDENT_NAMES = virtual(C_VILLAGE, "getResidentNames", Object.class, 0);
    /**
     * AMBIGUOUS BY NAME <em>and</em> arity: {@code getResidents(ServerLevel)} returns the loaded
     * entities and {@code getResidents(int)} returns names. Only the parameter hint separates them, and
     * it may name a Minecraft type precisely because that is not an MCA package.
     */
    public static final Member VILLAGE_GET_RESIDENTS =
            virtual(C_VILLAGE, "getResidents", Object.class, 1, ServerLevel.class);
    public static final Member VILLAGE_MANAGER_GET = statik(C_VILLAGE_MANAGER, "get", Object.class, 1);
    public static final Member VILLAGE_MANAGER_GET_OR_EMPTY = virtual(C_VILLAGE_MANAGER, "getOrEmpty", Object.class, 1);
    // Arity 2 picks findNearestVillage(BlockPos, int) over findNearestVillage(Entity).
    public static final Member FIND_NEAREST_VILLAGE = virtual(C_VILLAGE_MANAGER, "findNearestVillage", Object.class, 2);

    // Relationship / family tree -----------------------------------------------------------------------
    public static final Member RELATIONSHIP_OF = statik(C_RELATIONSHIP, "of", Object.class, 1);
    public static final Member GET_PARTNER_UUID = virtual(C_RELATIONSHIP, "getPartnerUUID", Object.class, 0);
    public static final Member GET_PARTNER_NAME = virtual(C_RELATIONSHIP, "getPartnerName", Object.class, 0);
    public static final Member IS_MARRIED = virtual(C_RELATIONSHIP, "isMarried", boolean.class, 0);
    public static final Member IS_MARRIED_TO = virtual(C_RELATIONSHIP, "isMarriedTo", boolean.class, 1);
    /**
     * The villager's world and identity, as seen from any relationship handle.
     *
     * <p>Both are declared abstract on {@code EntityRelationship} and implemented once on
     * {@code Relationship}, so neither is declared on a concrete subclass such as
     * {@code BreedableRelationship}. That is why they are bound here rather than shadowed: a
     * {@code @Pseudo} mixin can only shadow members declared <em>directly</em> on its target, because
     * Mixin has no guaranteed view of a pseudo target's supertypes. Shadowing them from
     * {@code BreedableRelationshipMixin} threw {@code InvalidMixinException} during mixin apply and
     * took the game down on startup. On the manifest they are covered by {@code McaBindingProbeTest}
     * instead, which is what makes the next rename a build failure.
     */
    public static final Member GET_RELATIONSHIP_WORLD = virtual(C_RELATIONSHIP, "getWorld", Object.class, 0);
    public static final Member GET_RELATIONSHIP_UUID = virtual(C_RELATIONSHIP, "getUUID", Object.class, 0);
    public static final Member FAMILY_TREE_GET = statik(C_FAMILY_TREE, "get", Object.class, 1);
    public static final Member FAMILY_TREE_GET_OR_EMPTY = virtual(C_FAMILY_TREE, "getOrEmpty", Object.class, 1);
    public static final Member NODE_GET_NAME = virtual(C_FAMILY_NODE, "getName", Object.class, 0);
    public static final Member NODE_SET_NAME = virtual(C_FAMILY_NODE, "setName", void.class, 1);
    // Arity 1 picks get(ServerPlayer) over get(ServerLevel, UUID).
    public static final Member PLAYER_SAVE_GET = statik(C_PLAYER_SAVE, "get", Object.class, 1);
    public static final Member PLAYER_SAVE_ENTITY_DATA = virtual(C_PLAYER_SAVE, "getEntityData", Object.class, 0);
    public static final Member PLAYER_SAVE_FAMILY_ENTRY = virtual(C_PLAYER_SAVE, "getFamilyEntry", Object.class, 0);

    // GUI interaction ---------------------------------------------------------------------------------
    public static final Member GET_INTERACTING_PLAYER =
            virtual(C_COMMAND_HANDLER, "getInteractingPlayer", Object.class, 0);

    // The dialogue engine, driven as a GUI click would -------------------------------------------------
    public static final Member DIALOGUES_GET_INSTANCE = statik(C_DIALOGUES, "getInstance", Object.class, 0);
    public static final Member DIALOGUES_SELECT_ANSWER = virtual(C_DIALOGUES, "selectAnswer", void.class, 4);
    public static final Member DIALOGUES_GET_QUESTION = virtual(C_DIALOGUES, "getQuestion", Object.class, 1);
    public static final Member QUESTION_GET_ANSWER = virtual(C_QUESTION, "getAnswer", Object.class, 1);
    public static final Member ANSWER_VALID_FOR_CONSTRAINT =
            virtual(C_ANSWER, "isValidForConstraint", boolean.class, 1);
    public static final Member CONSTRAINT_ALL_MATCHING = statik(C_CONSTRAINT, "allMatching", Object.class, 2);

    // Chat-mode delivery -------------------------------------------------------------------------------
    public static final Member NETWORK_SEND_TO_PLAYER = statik(C_NETWORK, "sendToPlayer", void.class, 2);
    public static final Member QUESTION_RESPONSE_NEW = constructor(C_QUESTION_RESPONSE, 2);
    // A record on 1.21.1, so the accessor carries the component name rather than a get- prefix.
    public static final Member QUESTION_RESPONSE_TEXT =
            virtual(C_QUESTION_RESPONSE, "questionText", Object.class, 0);
    public static final Member QUESTION_RESPONSE_SILENT = getter(C_QUESTION_RESPONSE, "silent");
    public static final Member DIALOGUE_RESPONSE_QUESTION = getter(C_DIALOGUE_RESPONSE, "question");
    public static final Member DIALOGUE_RESPONSE_ANSWERS = getter(C_DIALOGUE_RESPONSE, "answers");

    // MCA's config, for the one client-side guard the locale hook must preserve verbatim ---------------
    public static final Member CONFIG_GET_INSTANCE = statik(C_CONFIG, "getInstance", Object.class, 0);
    public static final Member CONFIG_ONLINE_TTS = getter(C_CONFIG, "enableOnlineTTS");

    // Registering our own dialogue conditions and actions with MCA --------------------------------------
    public static final Member ACTIONS_REGISTER = statik(C_ACTIONS, "register", void.class, 3);
    public static final Member GIFT_REGISTER = statik(C_GIFT_PREDICATE, "register", void.class, 3);

    // Living-histories context capabilities (spec §7.3) ------------------------------------------------
    //
    // One capability group, added together because they answer one question the mod could not ask
    // before: what is this villager's working life actually like right now. Members verified present
    // and identically named in 7.6.20, 7.7.0-beta.2 and 7.7.1-alpha.2 are declared REQUIRED — a
    // rename should fail McaBindingProbeTest rather than quietly turn every living-work scene into an
    // evergreen one. The two members that genuinely differ across those builds (the trait id and the
    // villager inventory) are declared OPTIONAL IN PAIRS and documented at their declaration; a
    // required member that only exists on 7.7 fails the probe on 7.6.20 and strands the whole group.
    // At runtime an unbound member is still only a stub, and McaContextSource reports the group
    // DEGRADED rather than asserting a fact it never read.

    /** The exact profession registry id, which {@code getProfessionText} could only ever approximate. */
    public static final Member GET_PROFESSION_ID = virtual(C_VILLAGER, "getProfessionId", Object.class, 0);
    /**
     * OPTIONAL PAIR, and a second real MCA drift rather than a defensive one. 7.7 exposes
     * {@code getInventory()}; 7.6.20 has no accessor at all, only a {@code private final
     * UpdatableInventory inventory} field. Both are declared optional and {@code McaHandles
     * .inventoryTags} tries the method first, so one jar reads carried items on every supported
     * build and a future third spelling degrades to "carrying nothing" rather than failing the probe.
     *
     * <p>{@code SimpleContainer} — read for coarse tag presence only, never for counts (spec §12.2).
     */
    public static final Member GET_VILLAGER_INVENTORY =
            optionalVirtual(C_VILLAGER, "getInventory", Object.class, 0);
    public static final Member VILLAGER_INVENTORY_FIELD = optionalGetter(C_VILLAGER, "inventory");
    public static final Member GET_TRAITS = virtual(C_VILLAGER, "getTraits", Object.class, 0);
    public static final Member TRAITS_GET_TRAITS = virtual(C_TRAITS, "getTraits", Object.class, 0);
    /**
     * OPTIONAL PAIR, and a real MCA drift rather than a defensive one. 7.6.20 has
     * {@code Trait#id()} returning a {@code String}; 7.7 renamed it to {@code getId()} returning a
     * {@code ResourceLocation}. Both are declared optional and {@code McaHandles.traitIds} tries the
     * modern name first, so one jar reads traits correctly on every supported build and a future
     * third spelling degrades to "no traits" rather than failing the probe.
     */
    public static final Member TRAIT_GET_ID = optionalVirtual(C_TRAIT, "getId", Object.class, 0);
    public static final Member TRAIT_ID_LEGACY = optionalVirtual(C_TRAIT, "id", Object.class, 0);

    /** MCA's assigned chore — the difference between "working" and "working on what you asked". */
    public static final Member BRAIN_GET_CURRENT_JOB = virtual(C_BRAIN, "getCurrentJob", Object.class, 0);
    /** Panic and grief are the two states that must silence ordinary initiative (spec §11.2). */
    public static final Member BRAIN_IS_PANICKING = virtual(C_BRAIN, "isPanicking", boolean.class, 0);
    public static final Member BRAIN_SHOULD_GRIEVE = virtual(C_BRAIN, "shouldGrieve", boolean.class, 0);

    /** {@code BlockPos} of the assigned workplace; compared against the villager's own position. */
    public static final Member RESIDENCY_GET_WORKPLACE = virtual(C_RESIDENCY, "getWorkplace", Object.class, 0);
    /** {@code Optional<GlobalPos>} of the assigned home. */
    public static final Member RESIDENCY_GET_HOME = virtual(C_RESIDENCY, "getHome", Object.class, 0);

    // FamilyTreeNode: the authoritative social graph, so this mod never persists a second one (§16.1).
    public static final Member NODE_IS_DECEASED = virtual(C_FAMILY_NODE, "isDeceased", boolean.class, 0);
    public static final Member NODE_PARTNER = virtual(C_FAMILY_NODE, "partner", Object.class, 0);
    public static final Member NODE_FATHER = virtual(C_FAMILY_NODE, "father", Object.class, 0);
    public static final Member NODE_MOTHER = virtual(C_FAMILY_NODE, "mother", Object.class, 0);
    /** {@code Set<UUID>}; the arity-0 {@code siblings()} rather than the streaming overloads. */
    public static final Member NODE_SIBLINGS = virtual(C_FAMILY_NODE, "siblings", Object.class, 0);
    /** {@code Set<UUID>}. AMBIGUOUS-ADJACENT: {@code getChildren()} returns a Stream; names differ. */
    public static final Member NODE_CHILDREN = virtual(C_FAMILY_NODE, "children", Object.class, 0);
    public static final Member NODE_PROFESSION_ID = virtual(C_FAMILY_NODE, "getProfessionId", Object.class, 0);

    public static final Member VILLAGE_GET_POPULATION = virtual(C_VILLAGE, "getPopulation", int.class, 0);
    /** {@code Optional<Building>} for a position — how a scene learns it is being told in a library. */
    public static final Member VILLAGE_BUILDING_AT = virtual(C_VILLAGE, "getBuildingAt", Object.class, 1);
    public static final Member BUILDING_GET_TYPE = virtual(C_BUILDING, "getType", Object.class, 0);

    /** Every member above, in declaration order. The single source of truth for what MCA must provide. */
    public static final List<Member> MANIFEST = List.of(
            VILLAGER_CLASS, VILLAGER_LIKE_CLASS, QUESTION_RESPONSE_CLASS, DIALOGUE_RESPONSE_CLASS,
            ANALYSIS_RESULTS_CLASS, ACTIONS_FACTORY_CLASS, ACTIONS_ACTION_CLASS, GIFT_FACTORY_CLASS,
            GIFT_CONDITION_CLASS,
            GET_VILLAGER_BRAIN, GET_LONG_TERM_MEMORY, GET_RESIDENCY, GET_INTERACTIONS, GET_TRANSLATABLE,
            GET_PROFESSION_TEXT, GET_AGE_STATE,
            GET_MEMORIES_FOR_PLAYER, REWARD_HEARTS, GET_PERSONALITY, GET_MOOD, GET_HEARTS, MOOD_GET_NAME,
            MEMORY_REMEMBER_FOREVER, MEMORY_REMEMBER_FOR, MEMORY_GET, MEMORY_PARSE_ID,
            GET_HOME_VILLAGE, VILLAGE_GET_ID, VILLAGE_GET_NAME, VILLAGE_RESIDENT_UUIDS,
            VILLAGE_RESIDENT_NAMES, VILLAGE_GET_RESIDENTS,
            VILLAGE_MANAGER_GET, VILLAGE_MANAGER_GET_OR_EMPTY, FIND_NEAREST_VILLAGE,
            RELATIONSHIP_OF, GET_PARTNER_UUID, GET_PARTNER_NAME, IS_MARRIED, IS_MARRIED_TO,
            GET_RELATIONSHIP_WORLD, GET_RELATIONSHIP_UUID,
            FAMILY_TREE_GET, FAMILY_TREE_GET_OR_EMPTY, NODE_GET_NAME, NODE_SET_NAME,
            PLAYER_SAVE_GET, PLAYER_SAVE_ENTITY_DATA, PLAYER_SAVE_FAMILY_ENTRY,
            GET_INTERACTING_PLAYER,
            DIALOGUES_GET_INSTANCE, DIALOGUES_SELECT_ANSWER, DIALOGUES_GET_QUESTION, QUESTION_GET_ANSWER,
            ANSWER_VALID_FOR_CONSTRAINT, CONSTRAINT_ALL_MATCHING,
            NETWORK_SEND_TO_PLAYER, QUESTION_RESPONSE_NEW, QUESTION_RESPONSE_TEXT, QUESTION_RESPONSE_SILENT,
            DIALOGUE_RESPONSE_QUESTION, DIALOGUE_RESPONSE_ANSWERS,
            CONFIG_GET_INSTANCE, CONFIG_ONLINE_TTS,
            ACTIONS_REGISTER, GIFT_REGISTER,
            GET_PROFESSION_ID, GET_VILLAGER_INVENTORY, VILLAGER_INVENTORY_FIELD,
            GET_TRAITS, TRAITS_GET_TRAITS, TRAIT_GET_ID,
            TRAIT_ID_LEGACY,
            BRAIN_GET_CURRENT_JOB, BRAIN_IS_PANICKING, BRAIN_SHOULD_GRIEVE,
            RESIDENCY_GET_WORKPLACE, RESIDENCY_GET_HOME,
            NODE_IS_DECEASED, NODE_PARTNER, NODE_FATHER, NODE_MOTHER, NODE_SIBLINGS, NODE_CHILDREN,
            NODE_PROFESSION_ID,
            VILLAGE_GET_POPULATION, VILLAGE_BUILDING_AT, BUILDING_GET_TYPE);

    // ---------------------------------------------------------------------------------------------
    // Resolution
    // ---------------------------------------------------------------------------------------------

    /**
     * The outcome of resolving {@link #MANIFEST} against one {@link ClassLoader}. Immutable once
     * built; {@link McaHandles} keeps one for the game's lifetime and the probe test builds a
     * throwaway one per MCA jar.
     */
    public static final class Resolution {

        private final Status status;
        private final String root;
        private final Map<Member, Object> resolved;
        private final List<String> unresolvedRequired;
        private final List<String> unresolvedOptional;

        private Resolution(Status status, String root, Map<Member, Object> resolved,
                           List<String> unresolvedRequired, List<String> unresolvedOptional) {
            this.status = status;
            this.root = root;
            this.resolved = resolved;
            this.unresolvedRequired = List.copyOf(unresolvedRequired);
            this.unresolvedOptional = List.copyOf(unresolvedOptional);
        }

        public Status status() {
            return status;
        }

        /** The matched package root (dotted, trailing dot), or {@code null} when nothing matched. */
        public String root() {
            return root;
        }

        public List<String> unresolvedRequired() {
            return unresolvedRequired;
        }

        public List<String> unresolvedOptional() {
            return unresolvedOptional;
        }

        /** The resolved class for a {@code CLASS} member, or {@code null} when it did not resolve. */
        public Class<?> cls(Member member) {
            Object value = resolved.get(member);
            return value instanceof Class<?> c ? c : null;
        }

        /**
         * The handle for a method/field member. <b>Never null</b> — an unresolved member yields a
         * constant stub of the same erased type returning that type's default, so call sites need no
         * guard of their own.
         */
        public MethodHandle handle(Member member) {
            Object value = resolved.get(member);
            return value instanceof MethodHandle h ? h : MethodHandles.empty(member.erasedType());
        }

        /**
         * True when this member actually bound. Only worth asking for an {@code optional} member whose
         * absence selects a different code path — everything else can just call through the stub.
         */
        public boolean has(Member member) {
            return resolved.get(member) instanceof MethodHandle;
        }

        /**
         * An enum constant on a resolved MCA enum class, or {@code null}. Reads (age state, mood,
         * personality, relationship state) go through {@code Enum#name} instead and need no binding;
         * this is only for the handful of places that must pass a real MCA enum <em>value</em> back in.
         */
        public Object enumConstant(Member enumClass, String constant) {
            Class<?> type = cls(enumClass);
            if (type == null || !type.isEnum()) {
                return null;
            }
            for (Object candidate : type.getEnumConstants()) {
                if (candidate instanceof Enum<?> e && e.name().equals(constant)) {
                    return candidate;
                }
            }
            return null;
        }
    }

    /**
     * A resolution in which nothing is bound. Used as the last-ditch value when even
     * {@link #resolveAgainst} fails, so {@link McaHandles} always has a non-null {@code Resolution}
     * and every handle it hands out is a working stub.
     */
    public static Resolution absent() {
        return new Resolution(Status.ABSENT, null, Map.of(), List.of(), List.of());
    }

    /**
     * Resolves the whole manifest against {@code loader}. Never throws: any failure is recorded and
     * turned into a stub, because this runs from a {@code <clinit>} whose escape would reintroduce
     * exactly the {@code NoClassDefFoundError} cascade this class exists to remove.
     */
    public static Resolution resolveAgainst(ClassLoader loader) {
        Map<Member, Object> resolved = new IdentityHashMap<>();
        List<String> missingRequired = new ArrayList<>();
        List<String> missingOptional = new ArrayList<>();

        String root = probeRoot(loader);
        if (root == null) {
            return new Resolution(mcaOnClasspath(loader) ? Status.UNBINDABLE : Status.ABSENT,
                    null, resolved, missingRequired, missingOptional);
        }

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Map<String, Class<?>> classes = new java.util.HashMap<>();
        for (Member member : MANIFEST) {
            try {
                Class<?> owner = classes.computeIfAbsent(member.ownerRelative,
                        relative -> loadOrNull(loader, root + relative));
                if (owner == null) {
                    record(member, missingRequired, missingOptional);
                    continue;
                }
                Object value = switch (member.kind) {
                    case CLASS -> owner;
                    case GETTER -> bindGetter(lookup, owner, member);
                    case CONSTRUCTOR -> bindConstructor(lookup, owner, member);
                    default -> bindMethod(lookup, owner, member);
                };
                if (value == null) {
                    record(member, missingRequired, missingOptional);
                } else {
                    resolved.put(member, value);
                }
            } catch (Throwable t) {
                record(member, missingRequired, missingOptional);
            }
        }

        Status status = missingRequired.isEmpty() ? Status.BOUND : Status.PARTIAL;
        return new Resolution(status, root, resolved, missingRequired, missingOptional);
    }

    private static void record(Member member, List<String> required, List<String> optional) {
        (member.required ? required : optional).add(member.toString());
    }

    /** The first candidate root whose probe class loads, or {@code null}. */
    private static String probeRoot(ClassLoader loader) {
        for (String root : CANDIDATE_ROOTS) {
            if (loadOrNull(loader, root + PROBE_CLASS) != null) {
                return root;
            }
        }
        return null;
    }

    /**
     * MCA-specific resources at a jar root, used only to tell "MCA absent" (fine, and the expected
     * state in unit tests) apart from "MCA present in a layout we do not know" (worth an ERROR).
     * Several, because MCA has renamed these too: {@code mca.png} in 7.6, {@code mca.classtweaker} from
     * 7.7. Only the diagnostic differs — behaviour is identical either way.
     */
    private static final String[] MCA_MARKER_RESOURCES = {
            "mca.png", "mca.classtweaker", "mca.mixins.json", "forge-mca.mixin.json", "fabric-mca.mixin.json"};

    /** True when MCA looks installed even though no candidate root matched. */
    private static boolean mcaOnClasspath(ClassLoader loader) {
        if (loader == null) {
            return false;
        }
        for (String marker : MCA_MARKER_RESOURCES) {
            if (loader.getResource(marker) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@code initialize = false} is deliberate: {@code VillagerEntityMCA}'s static initialiser builds
     * MCA's tracked-data parameter set, and a mere probe must not force that.
     */
    private static Class<?> loadOrNull(ClassLoader loader, String name) {
        try {
            return Class.forName(name, false, loader);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Erases a resolved handle to {@code erasedType} — every parameter {@link Object}, so callers pass
     * plain references and never name an MCA type.
     *
     * <p>The {@code asFixedArity()} is load-bearing, not tidiness. {@link MethodHandles.Lookup} hands
     * back a <em>varargs collector</em> for a varargs method, and {@link MethodHandle#asType} on a
     * collector passes the trailing array straight through only when the new trailing parameter is
     * assignable to the array type. Erasure guarantees it is not — {@code Object[]} is not assignable
     * from {@code Object} — so {@code asType} silently builds a one-element collector instead, wrapping
     * whatever array the call site passes inside a fresh one-element array. On MCA's
     * {@code getTranslatable(Player, String, Object...)} that put the argument array itself into the
     * first substitution slot, so every voiced line carrying a value rendered it as
     * {@code [Ljava.lang.Object;@1a2b3c}. Pinning to fixed arity first makes the erased trailing
     * {@code Object} a plain cast back to {@code Object[]}, which is what every {@link McaHandles} call
     * site assumes. It is a no-op for the non-varargs members, which is all the rest of them.
     */
    static MethodHandle erase(MethodHandle resolved, MethodType erasedType) {
        return resolved.asFixedArity().asType(erasedType);
    }

    /**
     * Finds a method by name, arity, and staticness — never by exact parameter types, which would
     * mean naming MCA types. Every member in the manifest is unique under that key in both known MCA
     * layouts, except {@code Village#getResidents}, whose two one-argument overloads are separated by
     * {@link Member#firstParamHint}.
     */
    private static MethodHandle bindMethod(MethodHandles.Lookup lookup, Class<?> owner, Member member) {
        Method match = null;
        for (Method candidate : owner.getMethods()) {
            // Bridges are skipped, not merely deprioritised. A covariant override leaves two arity-0
            // entries with the same name -- VillagerEntityMCA#getInteractions is the live example,
            // declaring both the real VillagerCommandHandler return and an EntityCommandHandler
            // bridge -- and getMethods() has no defined order, so binding whichever came first would
            // be a coin flip that a passing probe test could not distinguish.
            if (candidate.isBridge()
                    || !candidate.getName().equals(member.name)
                    || candidate.getParameterCount() != member.arity
                    || Modifier.isStatic(candidate.getModifiers()) != (member.kind == Kind.STATIC)) {
                continue;
            }
            if (member.firstParamHint != null
                    && (member.arity == 0 || !candidate.getParameterTypes()[0].equals(member.firstParamHint))) {
                continue;
            }
            match = candidate;
            break;
        }
        if (match == null) {
            return null;
        }
        try {
            match.setAccessible(true);
            return erase(lookup.unreflect(match), member.erasedType());
        } catch (Throwable t) {
            return null;
        }
    }

    /** Finds a constructor by arity alone, for the same reason {@link #bindMethod} avoids param types. */
    private static MethodHandle bindConstructor(MethodHandles.Lookup lookup, Class<?> owner, Member member) {
        for (java.lang.reflect.Constructor<?> candidate : owner.getConstructors()) {
            if (candidate.getParameterCount() != member.arity) {
                continue;
            }
            try {
                candidate.setAccessible(true);
                return erase(lookup.unreflectConstructor(candidate), member.erasedType());
            } catch (Throwable t) {
                return null;
            }
        }
        return null;
    }

    private static MethodHandle bindGetter(MethodHandles.Lookup lookup, Class<?> owner, Member member) {
        try {
            Field field = findField(owner, member.name);
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            return erase(lookup.unreflectGetter(field), member.erasedType());
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * The named field, public or not, from {@code owner} or any superclass.
     *
     * <p>{@link Class#getField} alone only sees public fields, which is why MCA 7.6.20's
     * {@code private final UpdatableInventory inventory} could not be reached by a getter member at
     * all. Walking declared fields up the hierarchy means a field MCA never exposed is still a
     * binding target, which is the difference between degrading a capability and losing it.
     */
    private static Field findField(Class<?> owner, String name) {
        try {
            return owner.getField(name);
        } catch (NoSuchFieldException ignored) {
            // Not public, or not present at all - fall through to the declared-field walk.
        }
        for (Class<?> c = owner; c != null && c != Object.class; c = c.getSuperclass()) {
            try {
                return c.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
                // Keep walking; a miss at every level is a genuine absence.
            }
        }
        return null;
    }

    // ---------------------------------------------------------------------------------------------
    // Production surface
    // ---------------------------------------------------------------------------------------------

    private static boolean logged;

    private McaBinding() {
    }

    /**
     * Logs the binding outcome exactly once, from common setup — after Forge has constructed every
     * mod, so the classloader is authoritative. Deliberately one line per state rather than a warning
     * per failed call: a partially-bound MCA would otherwise flood the log during an eligibility pass.
     */
    public static synchronized void init() {
        if (logged) {
            return;
        }
        logged = true;
        Resolution resolution = McaHandles.resolution();
        switch (resolution.status()) {
            case BOUND -> McaConversations.LOGGER.info(
                    "[MCA: Conversations] Bound to Minecraft Comes Alive at '{}' ({} members).",
                    resolution.root(), MANIFEST.size());
            case PARTIAL -> McaConversations.LOGGER.warn(
                    "[MCA: Conversations] Bound to Minecraft Comes Alive at '{}', but {} required member(s) did not "
                            + "resolve: {}. The features that need them are disabled; everything else works. "
                            + "Please report this with your MCA version.",
                    resolution.root(), resolution.unresolvedRequired().size(), resolution.unresolvedRequired());
            case UNBINDABLE -> McaConversations.LOGGER.error(
                    "[MCA: Conversations] Minecraft Comes Alive is installed but none of the known package roots {} "
                            + "matched, so every Conversations feature is disabled: villagers will not answer in "
                            + "chat and the conversation topics will not appear. Your server will NOT crash. "
                            + "Please report this with your MCA version.", String.join(", ", CANDIDATE_ROOTS));
            case ABSENT -> McaConversations.LOGGER.info(
                    "[MCA: Conversations] Minecraft Comes Alive was not found on the classpath; MCA-backed features "
                            + "are inactive.");
        }
        if (!resolution.unresolvedOptional().isEmpty()) {
            McaConversations.LOGGER.info("[MCA: Conversations] Optional MCA members absent in this version (expected on "
                    + "newer builds; a fallback is used): {}", resolution.unresolvedOptional());
        }
    }

    /** A one-line human-readable summary, for {@code /conversations debug mca}. */
    public static String describe() {
        Resolution resolution = McaHandles.resolution();
        return "status=" + resolution.status()
                + " root=" + (resolution.root() == null ? "<none>" : resolution.root())
                + " members=" + MANIFEST.size()
                + " missingRequired=" + resolution.unresolvedRequired()
                + " missingOptional=" + resolution.unresolvedOptional();
    }
}
