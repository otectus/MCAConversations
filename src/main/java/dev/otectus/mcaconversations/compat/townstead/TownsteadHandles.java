package dev.otectus.mcaconversations.compat.townstead;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.TownsteadBuildingView;
import dev.otectus.mcaconversations.compat.TownsteadCalendarView;
import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadLifeStageView;
import dev.otectus.mcaconversations.compat.TownsteadLifeView;
import dev.otectus.mcaconversations.compat.TownsteadNeedsView;
import dev.otectus.mcaconversations.compat.TownsteadPersonalityView;
import dev.otectus.mcaconversations.compat.TownsteadProfessionView;
import dev.otectus.mcaconversations.compat.TownsteadRootView;
import dev.otectus.mcaconversations.compat.TownsteadScheduleView;
import dev.otectus.mcaconversations.compat.TownsteadSpiritView;
import dev.otectus.mcaconversations.compat.TownsteadVillagerView;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The typed facade over {@link TownsteadBinding}'s resolved handles: Townstead objects go in,
 * Conversations' own view records come out, and <b>no Townstead value ever escapes this class</b>.
 *
 * <p>Handles are {@code private static final} so HotSpot can constant-fold them at the call site. A
 * per-call map lookup would forfeit that, and several of these run inside dialogue condition scoring,
 * which MCA repeats for every candidate result of every click.
 *
 * <p>Every read swallows {@link Throwable} and returns the type's empty view, because an unbound
 * member is a stub whose invocation is legal but meaningless, and because a read is never worth a
 * crash. Repeated failures are logged once per site rather than once per call: a broken member would
 * otherwise produce a stack trace per villager per tick.
 *
 * <p>Enums are converted with {@link Enum#name()} lowercased rather than returned as-is, so neither a
 * Townstead enum constant nor MCA's personality enum can leak into a view record and become linkage.
 *
 * @see TownsteadBinding for the manifest these handles come from
 */
final class TownsteadHandles {

    private static final TownsteadBinding.Resolution R = resolveQuietly();

    /** Sites that have already reported a failure, so a broken member logs once and not per tick. */
    private static final Set<String> REPORTED = ConcurrentHashMap.newKeySet();

    private TownsteadHandles() {
    }

    private static TownsteadBinding.Resolution resolveQuietly() {
        try {
            return TownsteadBinding.resolveAgainst(TownsteadHandles.class.getClassLoader());
        } catch (Throwable t) {
            return TownsteadBinding.absent();
        }
    }

    /** The live resolution, for the status command and the one line logged at startup. */
    static TownsteadBinding.Resolution resolution() {
        return R;
    }

    static boolean has(TownsteadCapability capability) {
        return R.has(capability);
    }

    // --- handles ---------------------------------------------------------------------------------

    private static final MethodHandle H_ENTITY = R.handle(TownsteadBinding.API_ENTITY);
    private static final MethodHandle H_CALENDAR = R.handle(TownsteadBinding.API_CALENDAR);
    private static final MethodHandle H_BUILDING_AT = R.handle(TownsteadBinding.API_BUILDING_AT);
    private static final MethodHandle H_ORIGIN = R.handle(TownsteadBinding.API_ORIGIN);

    private static final MethodHandle H_V_UUID = R.handle(TownsteadBinding.V_UUID);
    private static final MethodHandle H_V_NAME = R.handle(TownsteadBinding.V_NAME);
    private static final MethodHandle H_V_ENTITY_TYPE = R.handle(TownsteadBinding.V_ENTITY_TYPE);
    private static final MethodHandle H_V_ROOT_ID = R.handle(TownsteadBinding.V_ROOT_ID);
    private static final MethodHandle H_V_LIFE_STAGE = R.handle(TownsteadBinding.V_LIFE_STAGE);
    private static final MethodHandle H_V_AGE_DAYS = R.handle(TownsteadBinding.V_AGE_DAYS);
    private static final MethodHandle H_V_AGE_YEARS = R.handle(TownsteadBinding.V_AGE_YEARS);
    private static final MethodHandle H_V_IMMORTAL = R.handle(TownsteadBinding.V_IMMORTAL);
    private static final MethodHandle H_V_AGELESS = R.handle(TownsteadBinding.V_AGELESS);
    private static final MethodHandle H_V_SENIOR = R.handle(TownsteadBinding.V_SENIOR);
    private static final MethodHandle H_V_FERTILITY = R.handle(TownsteadBinding.V_FERTILITY);
    private static final MethodHandle H_V_PERSONALITY_ID = R.handle(TownsteadBinding.V_PERSONALITY_ID);
    private static final MethodHandle H_V_PROFESSION_ID = R.handle(TownsteadBinding.V_PROFESSION_ID);
    private static final MethodHandle H_V_PROFESSION_LEVEL = R.handle(TownsteadBinding.V_PROFESSION_LEVEL);
    private static final MethodHandle H_V_PROFESSION_XP = R.handle(TownsteadBinding.V_PROFESSION_XP);
    private static final MethodHandle H_V_CARRIED = R.handle(TownsteadBinding.V_CARRIED);
    private static final MethodHandle H_V_ALLELES = R.handle(TownsteadBinding.V_ALLELES);
    private static final MethodHandle H_V_HERITAGE = R.handle(TownsteadBinding.V_HERITAGE);
    private static final MethodHandle H_V_NEEDS = R.handle(TownsteadBinding.V_NEEDS);
    private static final MethodHandle H_V_SCHEDULE = R.handle(TownsteadBinding.V_SCHEDULE);

    private static final MethodHandle H_N_HUNGER = R.handle(TownsteadBinding.N_HUNGER);
    private static final MethodHandle H_N_SATURATION = R.handle(TownsteadBinding.N_SATURATION);
    private static final MethodHandle H_N_HUNGER_EXH = R.handle(TownsteadBinding.N_HUNGER_EXHAUSTION);
    private static final MethodHandle H_N_THIRST = R.handle(TownsteadBinding.N_THIRST);
    private static final MethodHandle H_N_QUENCHED = R.handle(TownsteadBinding.N_QUENCHED);
    private static final MethodHandle H_N_THIRST_EXH = R.handle(TownsteadBinding.N_THIRST_EXHAUSTION);
    private static final MethodHandle H_N_FATIGUE = R.handle(TownsteadBinding.N_FATIGUE);
    private static final MethodHandle H_N_COLLAPSED = R.handle(TownsteadBinding.N_COLLAPSED);
    private static final MethodHandle H_N_GATED = R.handle(TownsteadBinding.N_GATED);

    private static final MethodHandle H_S_MODE = R.handle(TownsteadBinding.S_MODE);
    private static final MethodHandle H_S_TEMPLATE_ID = R.handle(TownsteadBinding.S_TEMPLATE_ID);
    private static final MethodHandle H_S_CUSTOM_SHIFTS = R.handle(TownsteadBinding.S_CUSTOM_SHIFTS);
    private static final MethodHandle H_S_NON_DEFAULT = R.handle(TownsteadBinding.S_NON_DEFAULT_SHIFTS);
    private static final MethodHandle H_S_TICK_HOUR = R.handle(TownsteadBinding.S_TICK_HOUR);
    private static final MethodHandle H_S_DISPLAY_HOUR = R.handle(TownsteadBinding.S_DISPLAY_HOUR);
    private static final MethodHandle H_S_SHIFT_ORDINAL = R.handle(TownsteadBinding.S_SHIFT_ORDINAL);
    private static final MethodHandle H_S_CURRENT_ACTIVITY = R.handle(TownsteadBinding.S_CURRENT_ACTIVITY);
    private static final MethodHandle H_S_PLANNED_ACTIVITY = R.handle(TownsteadBinding.S_PLANNED_ACTIVITY);
    private static final MethodHandle H_S_CURRENT_TEMPLATE = R.handle(TownsteadBinding.S_CURRENT_TEMPLATE);
    private static final MethodHandle H_S_SHIFTS = R.handle(TownsteadBinding.S_SHIFTS);
    private static final MethodHandle H_S_WEEKDAYS = R.handle(TownsteadBinding.S_WEEKDAY_TEMPLATES);

    private static final MethodHandle H_K_PROFILE_ID = R.handle(TownsteadBinding.K_PROFILE_ID);
    private static final MethodHandle H_K_WORLD_DAY = R.handle(TownsteadBinding.K_WORLD_DAY);
    private static final MethodHandle H_K_EPOCH_OFFSET = R.handle(TownsteadBinding.K_EPOCH_OFFSET);
    private static final MethodHandle H_K_TIME_MODE = R.handle(TownsteadBinding.K_TIME_MODE);
    private static final MethodHandle H_K_YEAR = R.handle(TownsteadBinding.K_YEAR);
    private static final MethodHandle H_K_MONTH = R.handle(TownsteadBinding.K_MONTH);
    private static final MethodHandle H_K_DAY = R.handle(TownsteadBinding.K_DAY);
    private static final MethodHandle H_K_DAY_OF_YEAR = R.handle(TownsteadBinding.K_DAY_OF_YEAR);
    private static final MethodHandle H_K_DAY_OF_WEEK = R.handle(TownsteadBinding.K_DAY_OF_WEEK);
    private static final MethodHandle H_K_SEASON = R.handle(TownsteadBinding.K_SEASON);

    private static final MethodHandle H_B_ID = R.handle(TownsteadBinding.B_ID);
    private static final MethodHandle H_B_VILLAGE_ID = R.handle(TownsteadBinding.B_VILLAGE_ID);
    private static final MethodHandle H_B_TYPE = R.handle(TownsteadBinding.B_TYPE);
    private static final MethodHandle H_B_SIZE = R.handle(TownsteadBinding.B_SIZE);
    private static final MethodHandle H_B_CENTER_X = R.handle(TownsteadBinding.B_CENTER_X);
    private static final MethodHandle H_B_CENTER_Y = R.handle(TownsteadBinding.B_CENTER_Y);
    private static final MethodHandle H_B_CENTER_Z = R.handle(TownsteadBinding.B_CENTER_Z);
    private static final MethodHandle H_B_MIN_X = R.handle(TownsteadBinding.B_MIN_X);
    private static final MethodHandle H_B_MIN_Y = R.handle(TownsteadBinding.B_MIN_Y);
    private static final MethodHandle H_B_MIN_Z = R.handle(TownsteadBinding.B_MIN_Z);
    private static final MethodHandle H_B_MAX_X = R.handle(TownsteadBinding.B_MAX_X);
    private static final MethodHandle H_B_MAX_Y = R.handle(TownsteadBinding.B_MAX_Y);
    private static final MethodHandle H_B_MAX_Z = R.handle(TownsteadBinding.B_MAX_Z);

    private static final MethodHandle H_R_ID = R.handle(TownsteadBinding.R_ID);
    private static final MethodHandle H_R_DISPLAY_NAME = R.handle(TownsteadBinding.R_DISPLAY_NAME);
    private static final MethodHandle H_R_SPECIES = R.handle(TownsteadBinding.R_SPECIES);
    private static final MethodHandle H_R_ANCESTRY = R.handle(TownsteadBinding.R_ANCESTRY);
    private static final MethodHandle H_R_LINEAGE = R.handle(TownsteadBinding.R_LINEAGE);
    private static final MethodHandle H_R_EFFECTIVE_SPECIES = R.handle(TownsteadBinding.R_EFFECTIVE_SPECIES);
    private static final MethodHandle H_R_DEFAULT_GENES = R.handle(TownsteadBinding.R_DEFAULT_GENES);
    private static final MethodHandle H_R_LIFE_STAGES = R.handle(TownsteadBinding.R_LIFE_STAGES);
    private static final MethodHandle H_LS_ID = R.handle(TownsteadBinding.LS_ID);
    private static final MethodHandle H_LS_LABEL = R.handle(TownsteadBinding.LS_LABEL);
    private static final MethodHandle H_LS_DAYS = R.handle(TownsteadBinding.LS_DAYS);
    private static final MethodHandle H_LS_SCALE = R.handle(TownsteadBinding.LS_SCALE);
    private static final MethodHandle H_LS_PRESENTS_AS = R.handle(TownsteadBinding.LS_PRESENTS_AS);
    private static final MethodHandle H_LS_NARRATIVE_START = R.handle(TownsteadBinding.LS_NARRATIVE_START);
    private static final MethodHandle H_LS_NARRATIVE_END = R.handle(TownsteadBinding.LS_NARRATIVE_END);

    private static final MethodHandle H_P_DEF = R.handle(TownsteadBinding.P_DEF);
    private static final MethodHandle H_P_BASE_OF = R.handle(TownsteadBinding.P_BASE_OF);
    private static final MethodHandle H_PD_ID = R.handle(TownsteadBinding.PD_ID);
    private static final MethodHandle H_PD_BASE = R.handle(TownsteadBinding.PD_BASE);
    private static final MethodHandle H_PD_DISPLAY_NAME = R.handle(TownsteadBinding.PD_DISPLAY_NAME);
    private static final MethodHandle H_PD_DESCRIPTION = R.handle(TownsteadBinding.PD_DESCRIPTION);

    private static final MethodHandle H_SK_LEARNED = R.handle(TownsteadBinding.SK_LEARNED);
    private static final MethodHandle H_SK_HAS = R.handle(TownsteadBinding.SK_HAS);

    private static final MethodHandle H_SPIRIT_TOTALS = R.handle(TownsteadBinding.SPIRIT_TOTALS_FOR);
    private static final MethodHandle H_SPIRIT_READOUT = R.handle(TownsteadBinding.SPIRIT_READOUT_FOR);
    private static final MethodHandle H_SPIRIT_TIER = R.handle(TownsteadBinding.SPIRIT_TIER_FOR);
    private static final MethodHandle H_ST_PER_SPIRIT = R.handle(TownsteadBinding.ST_PER_SPIRIT);
    private static final MethodHandle H_ST_TOTAL = R.handle(TownsteadBinding.ST_TOTAL);
    private static final MethodHandle H_ST_CONTRIBUTING = R.handle(TownsteadBinding.ST_CONTRIBUTING);
    private static final MethodHandle H_SR_CLASSIFICATION = R.handle(TownsteadBinding.SR_CLASSIFICATION);
    private static final MethodHandle H_SR_TIER_INDEX = R.handle(TownsteadBinding.SR_TIER_INDEX);
    private static final MethodHandle H_SR_PRIMARY = R.handle(TownsteadBinding.SR_PRIMARY);
    private static final MethodHandle H_SR_SECONDARY = R.handle(TownsteadBinding.SR_SECONDARY);
    private static final MethodHandle H_SR_COMPONENT = R.handle(TownsteadBinding.SR_COMPONENT);
    private static final MethodHandle H_SPIRIT_CONTAINS = R.handle(TownsteadBinding.SPIRIT_CONTAINS);

    private static final MethodHandle H_CONTEXT_TAGS = R.handle(TownsteadBinding.CONTEXT_TAGS_FOR);

    private static final MethodHandle H_REACT_FIRE = R.handle(TownsteadBinding.REACT_FIRE);
    private static final MethodHandle H_REACT_CTX = R.handle(TownsteadBinding.REACT_CONTEXT_CTOR);
    private static final MethodHandle H_BACKENDS_ALL = R.handle(TownsteadBinding.REACT_BACKENDS_ALL);
    private static final Object TRIGGER_CONTEXT = R.constant(TownsteadBinding.REACT_TRIGGER_SOURCE);

    private static final MethodHandle H_LOCK_IS_LOCKED = R.handle(TownsteadBinding.LOCK_IS_LOCKED);
    private static final MethodHandle H_MARK_HEARTS = R.handle(TownsteadBinding.SOCIAL_MARK_HEARTS);
    private static final MethodHandle H_DIALOGUE_OPEN = R.handle(TownsteadBinding.DIALOGUE_ON_OPEN);
    private static final MethodHandle H_DIALOGUE_CLOSE = R.handle(TownsteadBinding.DIALOGUE_ON_CLOSE);

    // --- reads -----------------------------------------------------------------------------------

    static TownsteadVillagerView villager(@Nullable Entity entity) {
        if (entity == null || !has(TownsteadCapability.READ_VILLAGER)) {
            return TownsteadVillagerView.EMPTY;
        }
        try {
            Object snapshot = H_ENTITY.invoke(entity);
            if (snapshot == null) {
                return TownsteadVillagerView.EMPTY;
            }
            return new TownsteadVillagerView(
                    uuid(str(H_V_UUID.invoke(snapshot))),
                    str(H_V_NAME.invoke(snapshot)),
                    str(H_V_ENTITY_TYPE.invoke(snapshot)),
                    life(snapshot),
                    profession(entity, snapshot),
                    personalityOf(str(H_V_PERSONALITY_ID.invoke(snapshot))),
                    schedule(H_V_SCHEDULE.invoke(snapshot)),
                    needs(H_V_NEEDS.invoke(snapshot)),
                    stringMap(H_V_CARRIED.invoke(snapshot)),
                    stringList(H_V_ALLELES.invoke(snapshot)),
                    floatMap(H_V_HERITAGE.invoke(snapshot)));
        } catch (Throwable t) {
            report("villager", t);
            return TownsteadVillagerView.EMPTY;
        }
    }

    private static TownsteadLifeView life(Object snapshot) throws Throwable {
        return new TownsteadLifeView(
                str(H_V_ROOT_ID.invoke(snapshot)),
                str(H_V_LIFE_STAGE.invoke(snapshot)),
                (long) H_V_AGE_DAYS.invoke(snapshot),
                (int) H_V_AGE_YEARS.invoke(snapshot),
                (boolean) H_V_IMMORTAL.invoke(snapshot),
                (boolean) H_V_AGELESS.invoke(snapshot),
                (boolean) H_V_SENIOR.invoke(snapshot),
                (float) H_V_FERTILITY.invoke(snapshot) > 0f);
    }

    private static TownsteadProfessionView profession(Entity entity, Object snapshot) throws Throwable {
        return new TownsteadProfessionView(
                str(H_V_PROFESSION_ID.invoke(snapshot)),
                (int) H_V_PROFESSION_LEVEL.invoke(snapshot),
                (int) H_V_PROFESSION_XP.invoke(snapshot),
                learnedSkills(entity));
    }

    private static TownsteadNeedsView needs(@Nullable Object needs) {
        if (needs == null || !has(TownsteadCapability.READ_NEEDS)) {
            return TownsteadNeedsView.EMPTY;
        }
        try {
            return new TownsteadNeedsView(
                    (int) H_N_HUNGER.invoke(needs),
                    (float) H_N_SATURATION.invoke(needs),
                    (float) H_N_HUNGER_EXH.invoke(needs),
                    (int) H_N_THIRST.invoke(needs),
                    (int) H_N_QUENCHED.invoke(needs),
                    (float) H_N_THIRST_EXH.invoke(needs),
                    (int) H_N_FATIGUE.invoke(needs),
                    (boolean) H_N_COLLAPSED.invoke(needs),
                    (boolean) H_N_GATED.invoke(needs));
        } catch (Throwable t) {
            report("needs", t);
            return TownsteadNeedsView.EMPTY;
        }
    }

    private static TownsteadScheduleView schedule(@Nullable Object schedule) {
        if (schedule == null || !has(TownsteadCapability.READ_SCHEDULE)) {
            return TownsteadScheduleView.EMPTY;
        }
        try {
            return new TownsteadScheduleView(
                    lower(str(H_S_MODE.invoke(schedule))),
                    str(H_S_TEMPLATE_ID.invoke(schedule)),
                    (boolean) H_S_CUSTOM_SHIFTS.invoke(schedule),
                    (boolean) H_S_NON_DEFAULT.invoke(schedule),
                    (int) H_S_TICK_HOUR.invoke(schedule),
                    (int) H_S_DISPLAY_HOUR.invoke(schedule),
                    (int) H_S_SHIFT_ORDINAL.invoke(schedule),
                    lower(str(H_S_CURRENT_ACTIVITY.invoke(schedule))),
                    lower(str(H_S_PLANNED_ACTIVITY.invoke(schedule))),
                    str(H_S_CURRENT_TEMPLATE.invoke(schedule)),
                    intList(H_S_SHIFTS.invoke(schedule)),
                    stringList(H_S_WEEKDAYS.invoke(schedule)));
        } catch (Throwable t) {
            report("schedule", t);
            return TownsteadScheduleView.EMPTY;
        }
    }

    static TownsteadCalendarView calendar(@Nullable MinecraftServer server) {
        if (server == null || !has(TownsteadCapability.READ_CALENDAR)) {
            return TownsteadCalendarView.EMPTY;
        }
        try {
            Object snapshot = H_CALENDAR.invoke(server);
            if (snapshot == null) {
                return TownsteadCalendarView.EMPTY;
            }
            return new TownsteadCalendarView(
                    str(H_K_PROFILE_ID.invoke(snapshot)),
                    (long) H_K_WORLD_DAY.invoke(snapshot),
                    (int) H_K_EPOCH_OFFSET.invoke(snapshot),
                    lower(str(H_K_TIME_MODE.invoke(snapshot))),
                    (int) H_K_YEAR.invoke(snapshot),
                    (int) H_K_MONTH.invoke(snapshot),
                    (int) H_K_DAY.invoke(snapshot),
                    (int) H_K_DAY_OF_YEAR.invoke(snapshot),
                    (int) H_K_DAY_OF_WEEK.invoke(snapshot),
                    lower(str(H_K_SEASON.invoke(snapshot))));
        } catch (Throwable t) {
            report("calendar", t);
            return TownsteadCalendarView.EMPTY;
        }
    }

    static TownsteadBuildingView buildingAt(@Nullable ServerLevel level, @Nullable BlockPos pos) {
        if (level == null || pos == null || !has(TownsteadCapability.READ_BUILDING)) {
            return TownsteadBuildingView.EMPTY;
        }
        try {
            Object snapshot = H_BUILDING_AT.invoke(level, pos);
            if (snapshot == null) {
                return TownsteadBuildingView.EMPTY;
            }
            return new TownsteadBuildingView(
                    true,
                    (int) H_B_ID.invoke(snapshot),
                    (int) H_B_VILLAGE_ID.invoke(snapshot),
                    str(H_B_TYPE.invoke(snapshot)),
                    (int) H_B_SIZE.invoke(snapshot),
                    (int) H_B_CENTER_X.invoke(snapshot),
                    (int) H_B_CENTER_Y.invoke(snapshot),
                    (int) H_B_CENTER_Z.invoke(snapshot),
                    (int) H_B_MIN_X.invoke(snapshot),
                    (int) H_B_MIN_Y.invoke(snapshot),
                    (int) H_B_MIN_Z.invoke(snapshot),
                    (int) H_B_MAX_X.invoke(snapshot),
                    (int) H_B_MAX_Y.invoke(snapshot),
                    (int) H_B_MAX_Z.invoke(snapshot));
        } catch (Throwable t) {
            report("buildingAt", t);
            return TownsteadBuildingView.EMPTY;
        }
    }

    static TownsteadRootView root(@Nullable ResourceLocation id) {
        if (id == null || !has(TownsteadCapability.READ_ROOT)) {
            return TownsteadRootView.EMPTY;
        }
        try {
            Object snapshot = H_ORIGIN.invoke(id);
            if (snapshot == null) {
                return TownsteadRootView.EMPTY;
            }
            return new TownsteadRootView(
                    str(H_R_ID.invoke(snapshot)),
                    str(H_R_DISPLAY_NAME.invoke(snapshot)),
                    str(H_R_SPECIES.invoke(snapshot)),
                    str(H_R_ANCESTRY.invoke(snapshot)),
                    str(H_R_LINEAGE.invoke(snapshot)),
                    str(H_R_EFFECTIVE_SPECIES.invoke(snapshot)),
                    stringList(H_R_DEFAULT_GENES.invoke(snapshot)),
                    lifeStages(H_R_LIFE_STAGES.invoke(snapshot)));
        } catch (Throwable t) {
            report("root", t);
            return TownsteadRootView.EMPTY;
        }
    }

    private static List<TownsteadLifeStageView> lifeStages(@Nullable Object raw) {
        if (!(raw instanceof List<?> list)) {
            return List.of();
        }
        List<TownsteadLifeStageView> stages = new ArrayList<>(list.size());
        for (Object stage : list) {
            if (stage == null) {
                continue;
            }
            try {
                stages.add(new TownsteadLifeStageView(
                        str(H_LS_ID.invoke(stage)),
                        str(H_LS_LABEL.invoke(stage)),
                        (int) H_LS_DAYS.invoke(stage),
                        (float) H_LS_SCALE.invoke(stage),
                        lower(str(H_LS_PRESENTS_AS.invoke(stage))),
                        (float) H_LS_NARRATIVE_START.invoke(stage),
                        (float) H_LS_NARRATIVE_END.invoke(stage)));
            } catch (Throwable t) {
                report("lifeStage", t);
            }
        }
        return List.copyOf(stages);
    }

    /**
     * Resolves a Townstead personality id to its definition, and to the MCA voice behind it.
     *
     * <p>A custom definition answers {@code def}; a plain MCA personality does not, and is reported as
     * its own base. {@code baseOf} returns an MCA personality enum, which crosses as {@link Object}
     * and is read through {@link Enum#name()} so its type is never named.
     */
    static TownsteadPersonalityView personalityOf(String personalityId) {
        if (personalityId == null || personalityId.isEmpty()
                || !has(TownsteadCapability.READ_PERSONALITY)) {
            return TownsteadPersonalityView.EMPTY;
        }
        try {
            Object def = H_P_DEF.invoke(personalityId);
            if (def == null) {
                return new TownsteadPersonalityView(lower(personalityId), false,
                        enumName(H_P_BASE_OF.invoke(personalityId)), null, null);
            }
            String base = str(H_PD_BASE.invoke(def));
            if (base.isEmpty()) {
                base = enumName(H_P_BASE_OF.invoke(personalityId));
            }
            String id = str(H_PD_ID.invoke(def));
            return new TownsteadPersonalityView(
                    lower(id.isEmpty() ? personalityId : id),
                    true,
                    lower(base),
                    component(H_PD_DISPLAY_NAME.invoke(def)),
                    component(H_PD_DESCRIPTION.invoke(def)));
        } catch (Throwable t) {
            report("personality", t);
            return TownsteadPersonalityView.EMPTY;
        }
    }

    /**
     * Spirit for one village. The MCA {@code Village} arrives from {@code McaCompat} as an opaque
     * {@link Object} and is handed straight to Townstead through an erased handle, so neither side's
     * type is ever named here.
     */
    static TownsteadSpiritView spirit(@Nullable ServerLevel level, int villageId) {
        if (level == null || villageId < 0 || !has(TownsteadCapability.READ_SPIRIT)) {
            return TownsteadSpiritView.EMPTY;
        }
        Object village = McaCompat.villageHandle(level, villageId).orElse(null);
        if (village == null) {
            return TownsteadSpiritView.EMPTY;
        }
        try {
            Object totals = H_SPIRIT_TOTALS.invoke(village);
            if (totals == null) {
                return TownsteadSpiritView.EMPTY;
            }
            Object readout = H_SPIRIT_READOUT.invoke(totals);
            Map<String, Integer> perSpirit = intMap(H_ST_PER_SPIRIT.invoke(totals));
            int total = (int) H_ST_TOTAL.invoke(totals);
            int contributing = (int) H_ST_CONTRIBUTING.invoke(totals);
            if (readout == null) {
                return new TownsteadSpiritView(villageId, perSpirit, total, contributing,
                        0, "", "", "", null);
            }
            // The readout's own tier, never a tier recomputed from copied thresholds: a retune
            // upstream must not silently change what an authored line means.
            return new TownsteadSpiritView(
                    villageId,
                    perSpirit,
                    total,
                    contributing,
                    (int) H_SR_TIER_INDEX.invoke(readout),
                    enumName(H_SR_CLASSIFICATION.invoke(readout)),
                    str(H_SR_PRIMARY.invoke(readout)),
                    str(H_SR_SECONDARY.invoke(readout)),
                    component(H_SR_COMPONENT.invoke(readout)));
        } catch (Throwable t) {
            report("spirit", t);
            return TownsteadSpiritView.EMPTY;
        }
    }

    /**
     * Townstead's tier for a raw point total, asked of its aggregator rather than computed from
     * copied thresholds. Needed by the spirit query for a named spirit that is not the primary one,
     * where the readout's own tier does not apply.
     */
    static int tierForPoints(int points) {
        if (!has(TownsteadCapability.READ_SPIRIT)) {
            return 0;
        }
        try {
            return (int) H_SPIRIT_TIER.invoke(points);
        } catch (Throwable t) {
            report("tierForPoints", t);
            return 0;
        }
    }

    static boolean isKnownSpirit(@Nullable String spiritId) {
        if (spiritId == null || spiritId.isEmpty() || !has(TownsteadCapability.READ_SPIRIT)) {
            return false;
        }
        try {
            return (boolean) H_SPIRIT_CONTAINS.invoke(spiritId);
        } catch (Throwable t) {
            report("isKnownSpirit", t);
            return false;
        }
    }

    static Set<String> contextTags(@Nullable Entity villager) {
        if (villager == null || !has(TownsteadCapability.READ_CONTEXT_TAGS)
                || !(villager.level() instanceof ServerLevel level)) {
            return Set.of();
        }
        try {
            return stringSet(H_CONTEXT_TAGS.invoke(level, villager));
        } catch (Throwable t) {
            report("contextTags", t);
            return Set.of();
        }
    }

    static Set<String> learnedSkills(@Nullable Entity villager) {
        if (villager == null || !has(TownsteadCapability.READ_SKILLS)) {
            return Set.of();
        }
        try {
            return stringSet(H_SK_LEARNED.invoke(villager));
        } catch (Throwable t) {
            report("learnedSkills", t);
            return Set.of();
        }
    }

    static boolean hasSkill(@Nullable Entity villager, @Nullable String skillId) {
        if (villager == null || skillId == null || !has(TownsteadCapability.READ_SKILLS)) {
            return false;
        }
        ResourceLocation id = ResourceLocation.tryParse(skillId);
        if (id == null) {
            return false;
        }
        try {
            return (boolean) H_SK_HAS.invoke(villager, id);
        } catch (Throwable t) {
            report("hasSkill", t);
            return false;
        }
    }

    static boolean isReactionLocked(@Nullable Entity villager, long gameTime) {
        if (villager == null || !has(TownsteadCapability.REACTION_LOCK)) {
            return false;
        }
        try {
            return (boolean) H_LOCK_IS_LOCKED.invoke(villager, gameTime);
        } catch (Throwable t) {
            report("isReactionLocked", t);
            return false;
        }
    }

    // --- writes ----------------------------------------------------------------------------------

    /**
     * How many reaction backends Townstead currently has registered. Zero means every reaction is
     * inert whatever else bound: Townstead can only play one through a backend, and it ships exactly
     * one, for Emotecraft. Reported by the status command so an absent Emotecraft reads as a missing
     * prerequisite rather than as a Conversations bug.
     */
    static int reactionBackendCount() {
        if (!has(TownsteadCapability.FIRE_REACTION)) {
            return 0;
        }
        try {
            return H_BACKENDS_ALL.invoke() instanceof Map<?, ?> backends ? backends.size() : 0;
        } catch (Throwable t) {
            report("reactionBackends", t);
            return 0;
        }
    }

    static boolean fireReaction(@Nullable ServerLevel level, @Nullable Entity villager,
                                @Nullable ServerPlayer player, @Nullable ResourceLocation reaction,
                                Set<String> tags) {
        if (level == null || villager == null || reaction == null || TRIGGER_CONTEXT == null
                || !has(TownsteadCapability.FIRE_REACTION) || reactionBackendCount() == 0) {
            return false;
        }
        try {
            // TriggerSource.CONTEXT, not COMMAND: Townstead's sleep, lock, cooldown, chance and
            // movement gates all stay in force. Depth 0 so nothing we fire can start a mirror chain.
            Object context = H_REACT_CTX.invoke(TRIGGER_CONTEXT, player, villager.blockPosition(),
                    Set.copyOf(tags), 0);
            return (boolean) H_REACT_FIRE.invoke(level, villager, reaction, context);
        } catch (Throwable t) {
            report("fireReaction", t);
            return false;
        }
    }

    static void markHeartChange(@Nullable Entity villager, int measuredDelta, long gameTime) {
        if (villager == null || measuredDelta == 0 || !has(TownsteadCapability.MARK_HEART_CHANGE)) {
            return;
        }
        try {
            H_MARK_HEARTS.invoke(villager, measuredDelta, gameTime);
        } catch (Throwable t) {
            report("markHeartChange", t);
        }
    }

    static void dialogueOpen(@Nullable Entity villager, @Nullable ServerPlayer player, long gameTime) {
        if (villager == null || player == null || !has(TownsteadCapability.TRACK_DIALOGUE)) {
            return;
        }
        try {
            H_DIALOGUE_OPEN.invoke(villager, player, gameTime);
        } catch (Throwable t) {
            report("dialogueOpen", t);
        }
    }

    static void dialogueClose(@Nullable Entity villager, @Nullable ServerPlayer player, long gameTime) {
        if (villager == null || player == null || !has(TownsteadCapability.TRACK_DIALOGUE)) {
            return;
        }
        try {
            H_DIALOGUE_CLOSE.invoke(villager, player, gameTime);
        } catch (Throwable t) {
            report("dialogueClose", t);
        }
    }

    // --- conversions -----------------------------------------------------------------------------

    /**
     * Any Townstead or Minecraft value as a plain string. Resource locations and records both answer
     * {@code toString()} usefully, and a null becomes the empty string rather than the text "null".
     */
    private static String str(@Nullable Object value) {
        return value == null ? "" : value.toString();
    }

    private static String lower(String value) {
        return value.toLowerCase(Locale.ROOT);
    }

    /** An enum constant as its lowercase name, so the enum type itself never crosses the boundary. */
    private static String enumName(@Nullable Object value) {
        return value instanceof Enum<?> constant ? lower(constant.name()) : str(value);
    }

    @Nullable
    private static Component component(@Nullable Object value) {
        return value instanceof Component text ? text : null;
    }

    private static UUID uuid(String raw) {
        try {
            return raw.isEmpty() ? new UUID(0L, 0L) : UUID.fromString(raw);
        } catch (IllegalArgumentException e) {
            return new UUID(0L, 0L);
        }
    }

    private static List<String> stringList(@Nullable Object value) {
        if (!(value instanceof List<?> list)) {
            return List.of();
        }
        List<String> out = new ArrayList<>(list.size());
        for (Object entry : list) {
            out.add(str(entry));
        }
        return List.copyOf(out);
    }

    private static List<Integer> intList(@Nullable Object value) {
        if (!(value instanceof List<?> list)) {
            return List.of();
        }
        List<Integer> out = new ArrayList<>(list.size());
        for (Object entry : list) {
            out.add(entry instanceof Number number ? number.intValue() : 0);
        }
        return List.copyOf(out);
    }

    private static Set<String> stringSet(@Nullable Object value) {
        if (!(value instanceof Iterable<?> items)) {
            return Set.of();
        }
        Set<String> out = new LinkedHashSet<>();
        for (Object entry : items) {
            String text = str(entry);
            if (!text.isEmpty()) {
                out.add(text);
            }
        }
        return Set.copyOf(out);
    }

    private static Map<String, String> stringMap(@Nullable Object value) {
        if (!(value instanceof Map<?, ?> map)) {
            return Map.of();
        }
        Map<String, String> out = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            out.put(str(entry.getKey()), str(entry.getValue()));
        }
        return Map.copyOf(out);
    }

    private static Map<String, Float> floatMap(@Nullable Object value) {
        if (!(value instanceof Map<?, ?> map)) {
            return Map.of();
        }
        Map<String, Float> out = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Number number) {
                out.put(str(entry.getKey()), number.floatValue());
            }
        }
        return Map.copyOf(out);
    }

    private static Map<String, Integer> intMap(@Nullable Object value) {
        if (!(value instanceof Map<?, ?> map)) {
            return Map.of();
        }
        Map<String, Integer> out = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Number number) {
                out.put(str(entry.getKey()), number.intValue());
            }
        }
        return Map.copyOf(out);
    }

    /**
     * One line per failing site, ever. A member that has moved or a Townstead build that disagrees
     * with this manifest would otherwise log a stack trace per villager per tick, which is how a
     * degraded integration turns into an unusable server (spec 4.4).
     */
    private static void report(String site, Throwable t) {
        if (REPORTED.add(site)) {
            McaConversations.LOGGER.warn("Townstead read '{}' failed; falling back to the neutral value "
                    + "for the rest of this session. Please report this with your Townstead version.", site, t);
        }
    }
}
