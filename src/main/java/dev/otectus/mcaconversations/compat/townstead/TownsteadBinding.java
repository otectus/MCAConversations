package dev.otectus.mcaconversations.compat.townstead;

import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadStatus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Resolves Townstead at <em>runtime</em>, by name, and reports what bound as
 * {@link TownsteadCapability capabilities} rather than as one all-or-nothing switch.
 *
 * <h2>Why nothing is bound by parameter type</h2>
 *
 * <p>Townstead is compiled against MCA, so several of its own method descriptors name MCA types:
 * {@code TownsteadAPI.villager(VillagerEntityMCA)},
 * {@code ContextResolver.tagsFor(.., VillagerEntityMCA)},
 * {@code VillageSpiritAggregator.totalsFor(Village)}, and {@code PersonalityResolver.baseOf(..)},
 * which <em>returns</em> an MCA personality. Naming any of those would hard-link this mod to one MCA
 * package layout through a third mod.
 *
 * <p>So methods are matched on owner, name, arity and staticness, and every handle is adapted to an
 * erased shape whose parameters are all {@link Object} and whose reference returns are {@link Object}
 * too. The MCA value simply passes through as a reference nothing here ever names.
 *
 * <h2>The four members that need an exact lookup</h2>
 *
 * <p>Owner plus name plus arity plus staticness is unique for almost everything Townstead exposes,
 * but not for everything Conversations reads. {@code DialogueStateTracker.onOpen},
 * {@code ReactionDispatcher.fire}, {@code LearnedSkills.learned} and {@code LearnedSkills.has} each
 * have two overloads that collide under that key, and picking the wrong one would mean sending a
 * UUID where a player belongs, or handing the dispatcher a reaction object where an id belongs.
 *
 * <p>Those four are disambiguated explicitly, and safely: every type used to tell the overloads apart
 * is a JDK or Minecraft class that is always present and already in this mod's constant pool, so
 * naming it costs nothing. For {@code fire} even that is avoided, by comparing the third parameter
 * type's <em>name</em> as a string.
 *
 * <h2>Capabilities, not a boolean</h2>
 *
 * <p>Each {@link Member} belongs to one capability, or to none at all for the handful of core members
 * without which there is no facade to speak of. A capability is bound only when every member it
 * declares bound, so one moved internal method in a Townstead point release disables exactly the
 * feature that needed it.
 *
 * <h2>The contract</h2>
 *
 * <p><b>Resolution never throws and never returns null.</b> An unresolved member becomes a constant
 * stub returning its type's default, so call sites in {@link TownsteadHandles} need no guards. That
 * matters more than convenience: enumerating a class's methods forces the JVM to resolve their
 * descriptors, so a Townstead built against a different MCA layout than the installed one throws
 * {@code NoClassDefFoundError} out of {@code getMethods()} itself. Caught per owner, and recorded
 * with the throwable's own class name, that mismatch reads as "nothing bound, and here is why"
 * rather than as an indistinguishable "method renamed" -- and never as a crash.
 *
 * @see TownsteadHandles for the resolved handles themselves
 */
public final class TownsteadBinding {

    /**
     * Townstead's package root, stored <em>dotted</em>, never in internal slash form. That is what
     * lets {@code NoTownsteadStaticLinkTest} byte-scan for slash-form references and treat any hit as
     * a regression, with no exemption for this file.
     */
    private static final String PACKAGE = "com.aetherianartificer.townstead.";

    /** The class whose presence identifies an installed, API-bearing Townstead. */
    private static final String PROBE_CLASS = "api.TownsteadAPI";

    /**
     * A method whose first parameter is an MCA villager. Its parameter type's <em>runtime</em> name
     * tells us which MCA package layout this Townstead was compiled against, read reflectively as a
     * string so it never becomes linkage. Diagnostics only: no code path ever branches on it.
     */
    private static final String VARIANT_PROBE_METHOD = "villager";

    /**
     * The parameter type that picks {@code ReactionDispatcher.fire(.., ResourceLocation, ..)} out of
     * its two same-arity overloads. Compared as a <em>string</em> against the runtime parameter name,
     * so the alternative overload's Townstead-owned type is never named here.
     */
    private static final String FIRE_BY_ID_PARAMETER = "net.minecraft.resources.ResourceLocation";

    private enum Kind { CLASS, VIRTUAL, STATIC, CTOR }

    /**
     * One thing Conversations needs from Townstead, named relative to {@link #PACKAGE}.
     * Identity-compared, so {@link TownsteadHandles} refers to members by constant rather than by a
     * string that could typo.
     */
    public static final class Member {

        private final Kind kind;
        private final String ownerRelative;
        private final String name;
        private final Class<?> returnType;
        private final int arity;
        @Nullable
        private final Class<?>[] exactParameters;
        @Nullable
        private final TownsteadCapability capability;

        private Member(Kind kind, String ownerRelative, String name, Class<?> returnType, int arity,
                       @Nullable Class<?>[] exactParameters, @Nullable TownsteadCapability capability) {
            this.kind = kind;
            this.ownerRelative = ownerRelative;
            this.name = name;
            this.returnType = returnType;
            this.arity = arity;
            this.exactParameters = exactParameters;
            this.capability = capability;
        }

        /** The capability this member belongs to, or {@code null} for a core facade member. */
        @Nullable
        public TownsteadCapability capability() {
            return capability;
        }

        @Override
        public String toString() {
            return switch (kind) {
                case CLASS -> ownerRelative;
                case CTOR -> ownerRelative + "#<init>/" + arity;
                default -> ownerRelative + "#" + name + "/" + arity;
            };
        }

        /**
         * The erased handle shape. Every parameter is {@link Object} (including the receiver for a
         * virtual) and {@code asType} does the boxing, so callers pass plain references and an MCA
         * argument crosses without ever being named.
         *
         * <p>Only a <em>primitive</em> return keeps its faithful type, so an unresolved member stubs
         * to a real {@code 0} or {@code false}. Every reference return is erased to {@link Object},
         * because the stub for one is {@code null} whatever type it claims; pretending otherwise
         * would put an array or element type into a signature that has to be null-checked anyway.
         * {@link TownsteadHandles} null-checks every reference result.
         */
        private MethodType erasedType() {
            int params = switch (kind) {
                case VIRTUAL -> arity + 1; // receiver first
                case STATIC, CTOR -> arity;
                case CLASS -> 0;
            };
            return MethodType.methodType(returnType, Collections.nCopies(params, Object.class));
        }
    }

    private static Member statik(String ownerRelative, String name, Class<?> ret, int arity,
                                 @Nullable TownsteadCapability capability) {
        return new Member(Kind.STATIC, ownerRelative, name, ret, arity, null, capability);
    }

    /**
     * A static method matched by its exact parameter types rather than by arity alone, for the
     * Townstead members whose overloads collide. See the class javadoc.
     */
    private static Member exact(String ownerRelative, String name, Class<?> ret,
                                @Nullable TownsteadCapability capability, Class<?>... parameters) {
        return new Member(Kind.STATIC, ownerRelative, name, ret, parameters.length, parameters, capability);
    }

    /** A zero-argument accessor. Every Townstead snapshot is a record, so this covers most of them. */
    private static Member get(String ownerRelative, String name, Class<?> ret,
                              TownsteadCapability capability) {
        return new Member(Kind.VIRTUAL, ownerRelative, name, ret, 0, null, capability);
    }

    /** A constructor, bound by arity. */
    private static Member ctor(String ownerRelative, int arity, TownsteadCapability capability) {
        return new Member(Kind.CTOR, ownerRelative, "<init>", Object.class, arity, null, capability);
    }

    /** A class whose mere presence, and whose enum constants, are what we need. */
    private static Member type(String ownerRelative, TownsteadCapability capability) {
        return new Member(Kind.CLASS, ownerRelative, "", Object.class, 0, null, capability);
    }

    // ---------------------------------------------------------------------------------------------
    // The manifest: every Townstead class and member Conversations reads or calls.
    //
    // Verified member-by-member against townstead-0.7.6+1.20.1.jar by TownsteadBindingProbeTest.
    // Only four entries need an exact parameter list; the rest are unique by owner, name, arity and
    // staticness in that jar, which is what keeps MCA relocated types out of our constant pool.
    //
    // LearnedSkills.learn, forceLearn, forget and forceForget are deliberately absent. Townstead owns
    // profession progression, and a member that is never bound cannot be called by accident.
    // ---------------------------------------------------------------------------------------------

    private static final String O_API = "api.TownsteadAPI";
    private static final String O_VILLAGER = "api.TownsteadVillagerSnapshot";
    private static final String O_NEEDS = "api.TownsteadNeedsSnapshot";
    private static final String O_SCHEDULE = "api.TownsteadScheduleSnapshot";
    private static final String O_CALENDAR = "api.TownsteadCalendarSnapshot";
    private static final String O_BUILDING = "api.TownsteadBuildingSnapshot";
    private static final String O_ROOT = "api.TownsteadRootSnapshot";
    private static final String O_LIFE_STAGE = "api.TownsteadLifeStageSnapshot";
    private static final String O_CONTEXT = "reaction.trigger.event.ContextResolver";
    private static final String O_SKILLS = "profession.skill.LearnedSkills";
    private static final String O_SPIRIT_AGG = "spirit.VillageSpiritAggregator";
    private static final String O_SPIRIT_TOTALS = "spirit.SpiritTotals";
    private static final String O_SPIRIT_READOUT = "spirit.SpiritReadout";
    private static final String O_SPIRIT_REGISTRY = "spirit.SpiritRegistry";
    private static final String O_PERSONALITY = "root.personality.PersonalityResolver";
    private static final String O_PERSONALITY_DEF = "root.personality.PersonalityDef";
    private static final String O_DISPATCHER = "reaction.ReactionDispatcher";
    private static final String O_REACTION_CONTEXT = "reaction.ReactionContext";
    private static final String O_TRIGGER_SOURCE = "reaction.ReactionContext$TriggerSource";
    private static final String O_BACKENDS = "reaction.backend.ReactionBackends";
    private static final String O_LOCK = "reaction.ReactionLockTracker";
    private static final String O_SOCIAL = "reaction.trigger.event.SocialInteractionTracker";
    private static final String O_DIALOGUE = "reaction.trigger.event.DialogueStateTracker";

    private static final TownsteadCapability CAP_VILLAGER = TownsteadCapability.READ_VILLAGER;
    private static final TownsteadCapability CAP_NEEDS = TownsteadCapability.READ_NEEDS;
    private static final TownsteadCapability CAP_SCHEDULE = TownsteadCapability.READ_SCHEDULE;
    private static final TownsteadCapability CAP_CALENDAR = TownsteadCapability.READ_CALENDAR;
    private static final TownsteadCapability CAP_BUILDING = TownsteadCapability.READ_BUILDING;
    private static final TownsteadCapability CAP_ROOT = TownsteadCapability.READ_ROOT;
    private static final TownsteadCapability CAP_PERSONALITY = TownsteadCapability.READ_PERSONALITY;
    private static final TownsteadCapability CAP_SKILLS = TownsteadCapability.READ_SKILLS;
    private static final TownsteadCapability CAP_SPIRIT = TownsteadCapability.READ_SPIRIT;
    private static final TownsteadCapability CAP_TAGS = TownsteadCapability.READ_CONTEXT_TAGS;
    private static final TownsteadCapability CAP_REACT = TownsteadCapability.FIRE_REACTION;
    private static final TownsteadCapability CAP_HEARTS = TownsteadCapability.MARK_HEART_CHANGE;
    private static final TownsteadCapability CAP_DIALOGUE = TownsteadCapability.TRACK_DIALOGUE;
    private static final TownsteadCapability CAP_LOCK = TownsteadCapability.REACTION_LOCK;

    // READ_VILLAGER. entity(Entity) is the safe entry point: its parameter descriptor is vanilla-only,
    // unlike the villager(VillagerEntityMCA) overload beside it, which must never be bound.
    public static final Member API_ENTITY = statik(O_API, "entity", Object.class, 1, CAP_VILLAGER);
    public static final Member V_UUID = get(O_VILLAGER, "uuid", Object.class, CAP_VILLAGER);
    public static final Member V_NAME = get(O_VILLAGER, "name", Object.class, CAP_VILLAGER);
    public static final Member V_ENTITY_TYPE = get(O_VILLAGER, "entityType", Object.class, CAP_VILLAGER);
    public static final Member V_ROOT_ID = get(O_VILLAGER, "rootId", Object.class, CAP_VILLAGER);
    public static final Member V_LIFE_STAGE = get(O_VILLAGER, "lifeStage", Object.class, CAP_VILLAGER);
    public static final Member V_AGE_DAYS = get(O_VILLAGER, "biologicalAgeDays", long.class, CAP_VILLAGER);
    public static final Member V_AGE_YEARS = get(O_VILLAGER, "apparentAgeYears", int.class, CAP_VILLAGER);
    public static final Member V_IMMORTAL = get(O_VILLAGER, "immortal", boolean.class, CAP_VILLAGER);
    public static final Member V_AGELESS = get(O_VILLAGER, "ageless", boolean.class, CAP_VILLAGER);
    public static final Member V_SENIOR = get(O_VILLAGER, "senior", boolean.class, CAP_VILLAGER);
    public static final Member V_FERTILITY = get(O_VILLAGER, "fertility", float.class, CAP_VILLAGER);
    public static final Member V_PERSONALITY_ID =
            get(O_VILLAGER, "personalityId", Object.class, CAP_VILLAGER);
    public static final Member V_PROFESSION_ID =
            get(O_VILLAGER, "professionId", Object.class, CAP_VILLAGER);
    public static final Member V_PROFESSION_LEVEL =
            get(O_VILLAGER, "professionLevel", int.class, CAP_VILLAGER);
    public static final Member V_PROFESSION_XP = get(O_VILLAGER, "professionXp", int.class, CAP_VILLAGER);
    public static final Member V_CARRIED = get(O_VILLAGER, "carriedVariants", Object.class, CAP_VILLAGER);
    public static final Member V_ALLELES = get(O_VILLAGER, "expressedAlleles", Object.class, CAP_VILLAGER);
    public static final Member V_HERITAGE = get(O_VILLAGER, "heritage", Object.class, CAP_VILLAGER);

    // READ_NEEDS.
    public static final Member V_NEEDS = get(O_VILLAGER, "needs", Object.class, CAP_NEEDS);
    public static final Member N_HUNGER = get(O_NEEDS, "hunger", int.class, CAP_NEEDS);
    public static final Member N_SATURATION = get(O_NEEDS, "saturation", float.class, CAP_NEEDS);
    public static final Member N_HUNGER_EXHAUSTION =
            get(O_NEEDS, "hungerExhaustion", float.class, CAP_NEEDS);
    public static final Member N_THIRST = get(O_NEEDS, "thirst", int.class, CAP_NEEDS);
    public static final Member N_QUENCHED = get(O_NEEDS, "quenched", int.class, CAP_NEEDS);
    public static final Member N_THIRST_EXHAUSTION =
            get(O_NEEDS, "thirstExhaustion", float.class, CAP_NEEDS);
    public static final Member N_FATIGUE = get(O_NEEDS, "fatigue", int.class, CAP_NEEDS);
    public static final Member N_COLLAPSED = get(O_NEEDS, "collapsed", boolean.class, CAP_NEEDS);
    public static final Member N_GATED = get(O_NEEDS, "gated", boolean.class, CAP_NEEDS);

    // READ_SCHEDULE.
    public static final Member V_SCHEDULE = get(O_VILLAGER, "schedule", Object.class, CAP_SCHEDULE);
    public static final Member S_MODE = get(O_SCHEDULE, "mode", Object.class, CAP_SCHEDULE);
    public static final Member S_TEMPLATE_ID = get(O_SCHEDULE, "templateId", Object.class, CAP_SCHEDULE);
    public static final Member S_CUSTOM_SHIFTS =
            get(O_SCHEDULE, "customShifts", boolean.class, CAP_SCHEDULE);
    public static final Member S_NON_DEFAULT_SHIFTS =
            get(O_SCHEDULE, "nonDefaultCustomShifts", boolean.class, CAP_SCHEDULE);
    public static final Member S_TICK_HOUR = get(O_SCHEDULE, "currentTickHour", int.class, CAP_SCHEDULE);
    public static final Member S_DISPLAY_HOUR =
            get(O_SCHEDULE, "currentDisplayHour", int.class, CAP_SCHEDULE);
    public static final Member S_SHIFT_ORDINAL =
            get(O_SCHEDULE, "currentShiftOrdinal", int.class, CAP_SCHEDULE);
    public static final Member S_CURRENT_ACTIVITY =
            get(O_SCHEDULE, "currentActivity", Object.class, CAP_SCHEDULE);
    public static final Member S_PLANNED_ACTIVITY =
            get(O_SCHEDULE, "plannedActivity", Object.class, CAP_SCHEDULE);
    public static final Member S_CURRENT_TEMPLATE =
            get(O_SCHEDULE, "currentTemplateId", Object.class, CAP_SCHEDULE);
    public static final Member S_SHIFTS = get(O_SCHEDULE, "shifts", Object.class, CAP_SCHEDULE);
    public static final Member S_WEEKDAY_TEMPLATES =
            get(O_SCHEDULE, "weekDayTemplates", Object.class, CAP_SCHEDULE);

    // READ_CALENDAR.
    public static final Member API_CALENDAR = statik(O_API, "calendar", Object.class, 1, CAP_CALENDAR);
    public static final Member K_PROFILE_ID = get(O_CALENDAR, "profileId", Object.class, CAP_CALENDAR);
    public static final Member K_WORLD_DAY = get(O_CALENDAR, "worldDay", long.class, CAP_CALENDAR);
    public static final Member K_EPOCH_OFFSET = get(O_CALENDAR, "epochYearOffset", int.class, CAP_CALENDAR);
    public static final Member K_TIME_MODE = get(O_CALENDAR, "timeMode", Object.class, CAP_CALENDAR);
    public static final Member K_YEAR = get(O_CALENDAR, "year", int.class, CAP_CALENDAR);
    public static final Member K_MONTH = get(O_CALENDAR, "month", int.class, CAP_CALENDAR);
    public static final Member K_DAY = get(O_CALENDAR, "day", int.class, CAP_CALENDAR);
    public static final Member K_DAY_OF_YEAR = get(O_CALENDAR, "dayOfYear", int.class, CAP_CALENDAR);
    public static final Member K_DAY_OF_WEEK = get(O_CALENDAR, "dayOfWeek", int.class, CAP_CALENDAR);
    public static final Member K_SEASON = get(O_CALENDAR, "season", Object.class, CAP_CALENDAR);

    // READ_BUILDING.
    public static final Member API_BUILDING_AT = statik(O_API, "buildingAt", Object.class, 2, CAP_BUILDING);
    public static final Member B_ID = get(O_BUILDING, "id", int.class, CAP_BUILDING);
    public static final Member B_VILLAGE_ID = get(O_BUILDING, "villageId", int.class, CAP_BUILDING);
    public static final Member B_TYPE = get(O_BUILDING, "type", Object.class, CAP_BUILDING);
    public static final Member B_SIZE = get(O_BUILDING, "size", int.class, CAP_BUILDING);
    public static final Member B_CENTER_X = get(O_BUILDING, "centerX", int.class, CAP_BUILDING);
    public static final Member B_CENTER_Y = get(O_BUILDING, "centerY", int.class, CAP_BUILDING);
    public static final Member B_CENTER_Z = get(O_BUILDING, "centerZ", int.class, CAP_BUILDING);
    public static final Member B_MIN_X = get(O_BUILDING, "minX", int.class, CAP_BUILDING);
    public static final Member B_MIN_Y = get(O_BUILDING, "minY", int.class, CAP_BUILDING);
    public static final Member B_MIN_Z = get(O_BUILDING, "minZ", int.class, CAP_BUILDING);
    public static final Member B_MAX_X = get(O_BUILDING, "maxX", int.class, CAP_BUILDING);
    public static final Member B_MAX_Y = get(O_BUILDING, "maxY", int.class, CAP_BUILDING);
    public static final Member B_MAX_Z = get(O_BUILDING, "maxZ", int.class, CAP_BUILDING);

    // READ_ROOT.
    public static final Member API_ORIGIN = statik(O_API, "origin", Object.class, 1, CAP_ROOT);
    public static final Member R_ID = get(O_ROOT, "id", Object.class, CAP_ROOT);
    public static final Member R_DISPLAY_NAME = get(O_ROOT, "displayName", Object.class, CAP_ROOT);
    public static final Member R_SPECIES = get(O_ROOT, "species", Object.class, CAP_ROOT);
    public static final Member R_ANCESTRY = get(O_ROOT, "ancestry", Object.class, CAP_ROOT);
    public static final Member R_LINEAGE = get(O_ROOT, "lineage", Object.class, CAP_ROOT);
    public static final Member R_EFFECTIVE_SPECIES =
            get(O_ROOT, "effectiveSpecies", Object.class, CAP_ROOT);
    public static final Member R_DEFAULT_GENES = get(O_ROOT, "defaultGenes", Object.class, CAP_ROOT);
    public static final Member R_LIFE_STAGES = get(O_ROOT, "lifeStages", Object.class, CAP_ROOT);
    public static final Member LS_ID = get(O_LIFE_STAGE, "id", Object.class, CAP_ROOT);
    public static final Member LS_LABEL = get(O_LIFE_STAGE, "label", Object.class, CAP_ROOT);
    public static final Member LS_DAYS = get(O_LIFE_STAGE, "days", int.class, CAP_ROOT);
    public static final Member LS_SCALE = get(O_LIFE_STAGE, "scale", float.class, CAP_ROOT);
    public static final Member LS_PRESENTS_AS = get(O_LIFE_STAGE, "presentsAs", Object.class, CAP_ROOT);
    public static final Member LS_NARRATIVE_START =
            get(O_LIFE_STAGE, "narrativeStart", float.class, CAP_ROOT);
    public static final Member LS_NARRATIVE_END = get(O_LIFE_STAGE, "narrativeEnd", float.class, CAP_ROOT);

    // READ_PERSONALITY. baseOf returns an MCA Personality, so its erased return is Object and the
    // facade reads it through Enum.name() rather than casting to a type it must not mention.
    public static final Member P_DEF = statik(O_PERSONALITY, "def", Object.class, 1, CAP_PERSONALITY);
    public static final Member P_BASE_OF = statik(O_PERSONALITY, "baseOf", Object.class, 1, CAP_PERSONALITY);
    public static final Member PD_ID = get(O_PERSONALITY_DEF, "id", Object.class, CAP_PERSONALITY);
    public static final Member PD_BASE = get(O_PERSONALITY_DEF, "base", Object.class, CAP_PERSONALITY);
    public static final Member PD_DISPLAY_NAME =
            get(O_PERSONALITY_DEF, "displayName", Object.class, CAP_PERSONALITY);
    public static final Member PD_DESCRIPTION =
            get(O_PERSONALITY_DEF, "description", Object.class, CAP_PERSONALITY);

    // READ_SKILLS. Both members collide with a UUID-keyed overload of the same arity, so both are
    // pinned by their exact parameter types. Only the reading members exist here, by design.
    public static final Member SK_LEARNED =
            exact(O_SKILLS, "learned", Object.class, CAP_SKILLS, LivingEntity.class);
    public static final Member SK_HAS =
            exact(O_SKILLS, "has", boolean.class, CAP_SKILLS, LivingEntity.class, ResourceLocation.class);

    // READ_SPIRIT. totalsFor takes an MCA Village, supplied by McaCompat.villageHandle as an Object.
    public static final Member SPIRIT_TOTALS_FOR =
            statik(O_SPIRIT_AGG, "totalsFor", Object.class, 1, CAP_SPIRIT);
    public static final Member SPIRIT_READOUT_FOR =
            statik(O_SPIRIT_AGG, "readoutFor", Object.class, 1, CAP_SPIRIT);
    public static final Member SPIRIT_TIER_FOR =
            statik(O_SPIRIT_AGG, "tierForSpirit", int.class, 1, CAP_SPIRIT);
    public static final Member ST_PER_SPIRIT = get(O_SPIRIT_TOTALS, "perSpirit", Object.class, CAP_SPIRIT);
    public static final Member ST_TOTAL = get(O_SPIRIT_TOTALS, "total", int.class, CAP_SPIRIT);
    public static final Member ST_CONTRIBUTING =
            get(O_SPIRIT_TOTALS, "contributingBuildings", int.class, CAP_SPIRIT);
    public static final Member SR_CLASSIFICATION =
            get(O_SPIRIT_READOUT, "classification", Object.class, CAP_SPIRIT);
    public static final Member SR_TIER_INDEX = get(O_SPIRIT_READOUT, "tierIndex", int.class, CAP_SPIRIT);
    public static final Member SR_PRIMARY = get(O_SPIRIT_READOUT, "primarySpiritId", Object.class, CAP_SPIRIT);
    public static final Member SR_SECONDARY =
            get(O_SPIRIT_READOUT, "secondarySpiritId", Object.class, CAP_SPIRIT);
    public static final Member SR_COMPONENT = get(O_SPIRIT_READOUT, "asComponent", Object.class, CAP_SPIRIT);
    public static final Member SPIRIT_CONTAINS =
            statik(O_SPIRIT_REGISTRY, "contains", boolean.class, 1, CAP_SPIRIT);

    // READ_CONTEXT_TAGS. tagsFor takes an MCA villager; unique by arity, so no exact list is needed.
    public static final Member CONTEXT_TAGS_FOR = statik(O_CONTEXT, "tagsFor", Object.class, 2, CAP_TAGS);

    // FIRE_REACTION. fire has two arity-4 static overloads and is pinned by a string comparison of
    // its third parameter type. ReactionBackends.all is bound too, because Townstead can only play a
    // reaction through a registered backend and an empty registry means every reaction is inert.
    public static final Member REACT_FIRE = statik(O_DISPATCHER, "fire", boolean.class, 4, CAP_REACT);
    public static final Member REACT_CONTEXT_CTOR = ctor(O_REACTION_CONTEXT, 5, CAP_REACT);
    public static final Member REACT_TRIGGER_SOURCE = type(O_TRIGGER_SOURCE, CAP_REACT);
    public static final Member REACT_BACKENDS_ALL = statik(O_BACKENDS, "all", Object.class, 0, CAP_REACT);

    // REACTION_LOCK, MARK_HEART_CHANGE, TRACK_DIALOGUE. onOpen collides with a UUID-keyed overload of
    // the same arity; onClose does not, because its other overload takes two arguments.
    public static final Member LOCK_IS_LOCKED = statik(O_LOCK, "isLocked", boolean.class, 2, CAP_LOCK);
    public static final Member SOCIAL_MARK_HEARTS =
            statik(O_SOCIAL, "markHeartChange", void.class, 3, CAP_HEARTS);
    public static final Member DIALOGUE_ON_OPEN = exact(O_DIALOGUE, "onOpen", void.class, CAP_DIALOGUE,
            LivingEntity.class, ServerPlayer.class, long.class);
    public static final Member DIALOGUE_ON_CLOSE =
            statik(O_DIALOGUE, "onClose", void.class, 3, CAP_DIALOGUE);

    /** Every member, in declaration order. The single source of truth for what this mod touches. */
    public static final List<Member> MANIFEST = List.of(
            API_ENTITY, V_UUID, V_NAME, V_ENTITY_TYPE, V_ROOT_ID, V_LIFE_STAGE, V_AGE_DAYS, V_AGE_YEARS,
            V_IMMORTAL, V_AGELESS, V_SENIOR, V_FERTILITY, V_PERSONALITY_ID, V_PROFESSION_ID,
            V_PROFESSION_LEVEL, V_PROFESSION_XP, V_CARRIED, V_ALLELES, V_HERITAGE,
            V_NEEDS, N_HUNGER, N_SATURATION, N_HUNGER_EXHAUSTION, N_THIRST, N_QUENCHED,
            N_THIRST_EXHAUSTION, N_FATIGUE, N_COLLAPSED, N_GATED,
            V_SCHEDULE, S_MODE, S_TEMPLATE_ID, S_CUSTOM_SHIFTS, S_NON_DEFAULT_SHIFTS, S_TICK_HOUR,
            S_DISPLAY_HOUR, S_SHIFT_ORDINAL, S_CURRENT_ACTIVITY, S_PLANNED_ACTIVITY, S_CURRENT_TEMPLATE,
            S_SHIFTS, S_WEEKDAY_TEMPLATES,
            API_CALENDAR, K_PROFILE_ID, K_WORLD_DAY, K_EPOCH_OFFSET, K_TIME_MODE, K_YEAR, K_MONTH,
            K_DAY, K_DAY_OF_YEAR, K_DAY_OF_WEEK, K_SEASON,
            API_BUILDING_AT, B_ID, B_VILLAGE_ID, B_TYPE, B_SIZE, B_CENTER_X, B_CENTER_Y, B_CENTER_Z,
            B_MIN_X, B_MIN_Y, B_MIN_Z, B_MAX_X, B_MAX_Y, B_MAX_Z,
            API_ORIGIN, R_ID, R_DISPLAY_NAME, R_SPECIES, R_ANCESTRY, R_LINEAGE, R_EFFECTIVE_SPECIES,
            R_DEFAULT_GENES, R_LIFE_STAGES, LS_ID, LS_LABEL, LS_DAYS, LS_SCALE, LS_PRESENTS_AS,
            LS_NARRATIVE_START, LS_NARRATIVE_END,
            P_DEF, P_BASE_OF, PD_ID, PD_BASE, PD_DISPLAY_NAME, PD_DESCRIPTION,
            SK_LEARNED, SK_HAS,
            SPIRIT_TOTALS_FOR, SPIRIT_READOUT_FOR, SPIRIT_TIER_FOR, ST_PER_SPIRIT, ST_TOTAL,
            ST_CONTRIBUTING, SR_CLASSIFICATION, SR_TIER_INDEX, SR_PRIMARY, SR_SECONDARY, SR_COMPONENT,
            SPIRIT_CONTAINS,
            CONTEXT_TAGS_FOR,
            REACT_FIRE, REACT_CONTEXT_CTOR, REACT_TRIGGER_SOURCE, REACT_BACKENDS_ALL,
            LOCK_IS_LOCKED, SOCIAL_MARK_HEARTS, DIALOGUE_ON_OPEN, DIALOGUE_ON_CLOSE);

    /**
     * The capabilities this manifest covers. Status is measured against these rather than against
     * every {@link TownsteadCapability} constant, so a capability whose members have not been declared
     * here cannot be mistaken for one that bound. {@code RPG_EMOTION_TAGS} is deliberately outside it:
     * that one is a client mixin, not a method handle, and it reports its own presence.
     */
    public static final Set<TownsteadCapability> DECLARED_CAPABILITIES = declaredCapabilities();

    private static Set<TownsteadCapability> declaredCapabilities() {
        EnumSet<TownsteadCapability> declared = EnumSet.noneOf(TownsteadCapability.class);
        for (Member member : MANIFEST) {
            if (member.capability != null) {
                declared.add(member.capability);
            }
        }
        return Collections.unmodifiableSet(declared);
    }

    // ---------------------------------------------------------------------------------------------
    // Resolution
    // ---------------------------------------------------------------------------------------------

    /** The outcome of resolving {@link #MANIFEST} against one classloader. Immutable. */
    public static final class Resolution {

        private final TownsteadStatus status;
        private final Set<TownsteadCapability> capabilities;
        @Nullable
        private final String variant;
        private final Map<Member, MethodHandle> resolved;
        private final Map<Member, Object> constants;
        private final List<String> unresolved;

        private Resolution(TownsteadStatus status, Set<TownsteadCapability> capabilities,
                           @Nullable String variant, Map<Member, MethodHandle> resolved,
                           Map<Member, Object> constants, List<String> unresolved) {
            this.status = status;
            this.capabilities = capabilities;
            this.variant = variant;
            this.resolved = resolved;
            this.constants = constants;
            this.unresolved = List.copyOf(unresolved);
        }

        public TownsteadStatus status() {
            return status;
        }

        /** The capabilities whose every declared member bound. */
        public Set<TownsteadCapability> capabilities() {
            return capabilities;
        }

        /**
         * The MCA package root the installed Townstead was compiled against, read reflectively from a
         * method's parameter type at bind time. Diagnostics only.
         */
        @Nullable
        public String variant() {
            return variant;
        }

        /**
         * Members that did not bind, each with the reason, for the status command and the one WARN at
         * startup. A whole-owner failure carries the throwable's class name, so an MCA layout mismatch
         * is distinguishable from a renamed method.
         */
        public List<String> unresolved() {
            return unresolved;
        }

        /**
         * The handle for a member. <b>Never null</b>: an unresolved member yields a constant stub of
         * the same erased type returning that type's default, so call sites need no guard of their own.
         */
        public MethodHandle handle(Member member) {
            MethodHandle handle = resolved.get(member);
            return handle != null ? handle : MethodHandles.empty(member.erasedType());
        }

        /**
         * The resolved value of a {@link Kind#CLASS} member, which for an enum owner is the constant
         * this mod needs from it. {@code null} when that class did not resolve.
         */
        @Nullable
        public Object constant(Member member) {
            return constants.get(member);
        }

        public boolean has(Member member) {
            return resolved.containsKey(member) || constants.containsKey(member);
        }

        public boolean has(TownsteadCapability capability) {
            return capabilities.contains(capability);
        }
    }

    /**
     * A resolution in which nothing bound, used when Townstead is not installed and as the last-ditch
     * value if resolution itself somehow fails. Every handle it hands out is still a working stub.
     */
    public static Resolution absent() {
        return new Resolution(TownsteadStatus.ABSENT, Set.of(), null, Map.of(), Map.of(), List.of());
    }

    /**
     * Resolves the whole manifest against {@code loader}. Never throws: every failure is recorded and
     * turned into a stub. That is load-bearing rather than tidy. Enumerating a class's methods forces
     * the JVM to resolve their parameter descriptors, so a Townstead compiled against a different MCA
     * layout than the installed one throws {@code NoClassDefFoundError} out of {@code getMethods()}.
     * Caught here, that becomes "nothing bound, status INCOMPATIBLE" plus one actionable log line.
     */
    public static Resolution resolveAgainst(ClassLoader loader) {
        if (loadOrNull(loader, PACKAGE + PROBE_CLASS) == null) {
            return absent();
        }

        Map<Member, MethodHandle> resolved = new IdentityHashMap<>();
        Map<Member, Object> constants = new IdentityHashMap<>();
        List<String> unresolved = new ArrayList<>();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Map<String, OwnerMethods> methodCache = new HashMap<>();

        for (Member member : MANIFEST) {
            OwnerMethods owner = methodsOf(loader, methodCache, member.ownerRelative);
            if (member.kind == Kind.CLASS) {
                Object constant = triggerSourceConstant(loader, member);
                if (constant == null) {
                    unresolved.add(member + " (" + owner.failure() + ")");
                } else {
                    constants.put(member, constant);
                }
                continue;
            }
            MethodHandle handle = null;
            try {
                handle = member.kind == Kind.CTOR
                        ? bindConstructor(lookup, loader, member)
                        : bindMethod(lookup, owner.methods(), member);
            } catch (Throwable ignored) {
                // Recorded below as an ordinary miss; see the javadoc for why this must not escape.
            }
            if (handle == null) {
                unresolved.add(member + " (" + owner.failure() + ")");
            } else {
                resolved.put(member, handle);
            }
        }

        EnumSet<TownsteadCapability> bound = EnumSet.copyOf(DECLARED_CAPABILITIES);
        for (Member member : MANIFEST) {
            if (member.capability != null && !resolved.containsKey(member)
                    && !constants.containsKey(member)) {
                bound.remove(member.capability);
            }
        }

        TownsteadStatus status;
        if (bound.isEmpty()) {
            status = TownsteadStatus.INCOMPATIBLE;
        } else if (bound.size() == DECLARED_CAPABILITIES.size()) {
            status = TownsteadStatus.FULL;
        } else {
            status = TownsteadStatus.PARTIAL;
        }

        OwnerMethods api = methodCache.get(O_API);
        return new Resolution(status, Collections.unmodifiableSet(bound),
                probeVariant(api == null ? null : api.methods()), resolved, constants, unresolved);
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
                // Almost always a relocated MCA type in one of this class's descriptors. Recording the
                // throwable's own name is what makes that diagnosable from a bug report.
                return OwnerMethods.failed(t.getClass().getSimpleName());
            }
        });
    }

    /**
     * The {@code CONTEXT} constant of Townstead's trigger-source enum, read through
     * {@link Class#getEnumConstants()} and matched on {@link Enum#name()} so the enum type itself is
     * never named here. Using {@code CONTEXT} rather than {@code COMMAND} is what keeps Townstead's
     * sleep, lock, cooldown, chance and movement gates in force for everything Conversations fires.
     */
    @Nullable
    private static Object triggerSourceConstant(ClassLoader loader, Member member) {
        Class<?> owner = loadOrNull(loader, PACKAGE + member.ownerRelative);
        if (owner == null) {
            return null;
        }
        try {
            Object[] constants = owner.getEnumConstants();
            if (constants == null) {
                return null;
            }
            return Arrays.stream(constants)
                    .filter(Enum.class::isInstance)
                    .filter(c -> "CONTEXT".equals(((Enum<?>) c).name()))
                    .findFirst()
                    .orElse(null);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Which MCA package root Townstead was built against, taken from the runtime name of a parameter
     * type rather than from anything we compile against. Returns for example {@code forge.net.mca}.
     * {@code null} when it cannot be determined, which is not an error: nothing branches on this.
     */
    @Nullable
    private static String probeVariant(@Nullable Method[] apiMethods) {
        if (apiMethods == null) {
            return null;
        }
        for (Method candidate : apiMethods) {
            if (!candidate.getName().equals(VARIANT_PROBE_METHOD) || candidate.getParameterCount() != 1) {
                continue;
            }
            try {
                String parameter = candidate.getParameterTypes()[0].getName();
                int entity = parameter.indexOf(".entity.");
                return entity > 0 ? parameter.substring(0, entity) : parameter;
            } catch (Throwable t) {
                return null;
            }
        }
        return null;
    }

    /**
     * {@code initialize = false} is deliberate: a probe must not run a Townstead class's static
     * initialiser, which would touch MCA and register content before Forge is ready for it.
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
     * Finds a method by name, arity and staticness, or by an exact parameter list for the four members
     * whose overloads collide. Ordinary members are never matched by parameter type, which would mean
     * naming MCA's relocated classes and reintroducing the linkage this layer exists to avoid.
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
            if (member.exactParameters != null
                    && !Arrays.equals(candidate.getParameterTypes(), member.exactParameters)) {
                continue;
            }
            if (member == REACT_FIRE && !firesById(candidate)) {
                continue;
            }
            try {
                candidate.setAccessible(true);
                // asFixedArity() before asType(), for the reason McaBinding#erase spells out: erasing a
                // varargs collector's trailing Object[] to Object turns asType into a re-collect, which
                // would hand the callee our argument array wrapped in another array. Townstead declares
                // varargs members (getTranslatable, aliases, context, applyToBase); none is bound today,
                // and this is what keeps binding one from being a silent corruption.
                return lookup.unreflect(candidate).asFixedArity().asType(member.erasedType());
            } catch (Throwable t) {
                return null;
            }
        }
        return null;
    }

    /**
     * Picks the {@code fire} overload that takes a reaction <em>id</em> rather than a resolved
     * Townstead reaction object. Compared by parameter type <em>name</em>, so the other overload's
     * Townstead-owned type never has to be mentioned to rule it out.
     */
    private static boolean firesById(Method candidate) {
        try {
            return FIRE_BY_ID_PARAMETER.equals(candidate.getParameterTypes()[2].getName());
        } catch (Throwable t) {
            return false;
        }
    }

    @Nullable
    private static MethodHandle bindConstructor(MethodHandles.Lookup lookup, ClassLoader loader,
                                                Member member) {
        Class<?> owner = loadOrNull(loader, PACKAGE + member.ownerRelative);
        if (owner == null) {
            return null;
        }
        try {
            for (Constructor<?> candidate : owner.getConstructors()) {
                if (candidate.getParameterCount() == member.arity) {
                    candidate.setAccessible(true);
                    return lookup.unreflectConstructor(candidate).asFixedArity()
                            .asType(member.erasedType());
                }
            }
        } catch (Throwable t) {
            return null;
        }
        return null;
    }

    private TownsteadBinding() {
    }
}
