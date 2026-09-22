package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.compat.Townstead;
import dev.otectus.mcaconversations.compat.TownsteadSnapshot;
import dev.otectus.mcaconversations.compat.TownsteadVillagerView;

import java.util.List;

/**
 * What Townstead says about the speaking villager, as ordinary context fields (Townstead spec §7,
 * §16), so a topic pack's scenes can gate on a shift, a need or the village's spirit the same way
 * they gate on the weather.
 *
 * <p>Every field is UNAVAILABLE without Townstead, with the integration switched off, or with its
 * content switched off. A part Townstead could not supply for this villager — no calendar bound, not
 * standing in a building, no spirit for the village — is UNKNOWN rather than a false answer.
 * Deliberately absent: genes, carried variants, fertility and heritage fractions, which ordinary
 * content has no business reading (spec §15.2).
 */
public final class TownsteadContextSource implements ConversationContextSource {
    public static final String ID = "townstead";

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.TOWNSTEAD_PRESENT, ContextKeys.TOWNSTEAD_ACTIVITY, ContextKeys.TOWNSTEAD_NEED,
            ContextKeys.TOWNSTEAD_NEED_CRISIS, ContextKeys.TOWNSTEAD_HUNGER, ContextKeys.TOWNSTEAD_THIRST,
            ContextKeys.TOWNSTEAD_FATIGUE, ContextKeys.TOWNSTEAD_LIFE_STAGE, ContextKeys.TOWNSTEAD_AGE,
            ContextKeys.TOWNSTEAD_PROFESSION_LEVEL, ContextKeys.TOWNSTEAD_SKILLS, ContextKeys.TOWNSTEAD_BUILDING,
            ContextKeys.TOWNSTEAD_SPECIES, ContextKeys.TOWNSTEAD_SPIRIT_TIER, ContextKeys.TOWNSTEAD_SPIRIT,
            ContextKeys.TOWNSTEAD_SPIRIT_KIND, ContextKeys.TOWNSTEAD_MONTH, ContextKeys.TOWNSTEAD_WEEKDAY);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<ContextKey<?>> declares() {
        return DECLARES;
    }

    @Override
    public boolean isAvailable(ContextRequest request) {
        return request.villager() != null && Townstead.contentEnabled();
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        if (!isAvailable(request)) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.ABSENT, "Townstead not active");
            return;
        }
        contribute(builder, Townstead.snapshot(request.villager()));
        builder.reportCapability(ContextCapabilities.Status.READY, "");
    }

    /** The pure half, so the mapping is testable with a hand-built snapshot. */
    static void contribute(ContextSnapshotBuilder builder, TownsteadSnapshot snapshot) {
        TownsteadVillagerView villager = snapshot.villager();
        boolean hasVillager = snapshot.live() && !villager.isEmpty();
        builder.put(ContextKeys.TOWNSTEAD_PRESENT, hasVillager);
        if (hasVillager) {
            builder.put(ContextKeys.TOWNSTEAD_ACTIVITY, villager.schedule().currentActivity());
            builder.put(ContextKeys.TOWNSTEAD_NEED, villager.needs().primaryNeed());
            builder.put(ContextKeys.TOWNSTEAD_NEED_CRISIS, villager.needs().inCrisis());
            builder.put(ContextKeys.TOWNSTEAD_HUNGER, villager.needs().hungerBucket());
            builder.put(ContextKeys.TOWNSTEAD_THIRST, villager.needs().thirstBucket());
            builder.put(ContextKeys.TOWNSTEAD_FATIGUE, villager.needs().fatigueBucket());
            putOrUnknown(builder, ContextKeys.TOWNSTEAD_LIFE_STAGE, villager.life().lifeStage());
            builder.put(ContextKeys.TOWNSTEAD_AGE, villager.life().ageDescription());
            if (villager.profession().employed()) {
                builder.put(ContextKeys.TOWNSTEAD_PROFESSION_LEVEL, villager.profession().level());
            } else {
                builder.unknown(ContextKeys.TOWNSTEAD_PROFESSION_LEVEL);
            }
            builder.put(ContextKeys.TOWNSTEAD_SKILLS, villager.profession().skills());
            builder.put(ContextKeys.TOWNSTEAD_BUILDING,
                    snapshot.building().present() ? snapshot.building().family() : "");
        } else {
            for (ContextKey<?> key : List.of(ContextKeys.TOWNSTEAD_ACTIVITY, ContextKeys.TOWNSTEAD_NEED,
                    ContextKeys.TOWNSTEAD_NEED_CRISIS, ContextKeys.TOWNSTEAD_HUNGER, ContextKeys.TOWNSTEAD_THIRST,
                    ContextKeys.TOWNSTEAD_FATIGUE, ContextKeys.TOWNSTEAD_LIFE_STAGE, ContextKeys.TOWNSTEAD_AGE,
                    ContextKeys.TOWNSTEAD_PROFESSION_LEVEL, ContextKeys.TOWNSTEAD_SKILLS,
                    ContextKeys.TOWNSTEAD_BUILDING)) {
                builder.unknown(key);
            }
        }
        putOrUnknown(builder, ContextKeys.TOWNSTEAD_SPECIES, snapshot.origin().effectiveSpecies());
        if (snapshot.spirit().isEmpty()) {
            builder.unknown(ContextKeys.TOWNSTEAD_SPIRIT_TIER);
            builder.unknown(ContextKeys.TOWNSTEAD_SPIRIT);
            builder.unknown(ContextKeys.TOWNSTEAD_SPIRIT_KIND);
        } else {
            builder.put(ContextKeys.TOWNSTEAD_SPIRIT_TIER, snapshot.spirit().tier());
            putOrUnknown(builder, ContextKeys.TOWNSTEAD_SPIRIT, snapshot.spirit().primaryId());
            putOrUnknown(builder, ContextKeys.TOWNSTEAD_SPIRIT_KIND, snapshot.spirit().classification());
        }
        if (snapshot.calendar().isEmpty()) {
            builder.unknown(ContextKeys.TOWNSTEAD_MONTH);
            builder.unknown(ContextKeys.TOWNSTEAD_WEEKDAY);
        } else {
            builder.put(ContextKeys.TOWNSTEAD_MONTH, snapshot.calendar().month());
            builder.put(ContextKeys.TOWNSTEAD_WEEKDAY, snapshot.calendar().dayOfWeek());
        }
    }

    private static void putOrUnknown(ContextSnapshotBuilder builder, ContextKey<String> key, String value) {
        if (value == null || value.isBlank()) {
            builder.unknown(key);
        } else {
            builder.put(key, value.toLowerCase(java.util.Locale.ROOT));
        }
    }
}
