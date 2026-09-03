package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.identity.Identity;
import dev.otectus.mcaconversations.identity.VillagerIdentityRecord;

import java.util.List;
import java.util.Optional;

/**
 * Who this villager is underneath, as context rather than as a gate.
 *
 * <h2>Why this exists, given {@code conversations_profile} already works</h2>
 *
 * <p>A scene can already require an identity token through its {@code context.identity} block, which
 * parses to a {@code ProfileQuery}. That is a hard gate, and it is the right tool for "this scene is
 * only for somebody who values precision". It is the wrong tool for everything else: a
 * {@code ProfileQuery} cannot be combined with a numeric range, cannot be negated against an unknown,
 * and — the practical problem — is not visible to the conversation trace, so an author debugging why
 * a scene lost cannot see what the villager's anchors actually were.
 *
 * <p>These nine fields were declared for exactly that and never written, so
 * {@code identity.interests} and its eight siblings have read {@code UNAVAILABLE} on every install
 * since they were added. So have {@code work.former_profession} and
 * {@code work.profession_changed_day}, even though the identity record has carried both since 1.4.1.
 *
 * <h2>Absent is not neutral</h2>
 *
 * <p>Identity generation is a config switch, and a villager only acquires anchors when something asks
 * for them. With the feature off, or before a villager has ever been profiled, every field here is
 * {@code UNAVAILABLE} rather than empty — because "this villager has no interests" and "nobody has
 * asked who this villager is" are different claims, and only one of them is ever true.
 */
public final class IdentityContextSource implements ConversationContextSource {

    public static final String ID = "identity";

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.IDENTITY_INTERESTS, ContextKeys.IDENTITY_VALUES,
            ContextKeys.IDENTITY_COMFORT, ContextKeys.IDENTITY_AVERSION,
            ContextKeys.IDENTITY_WORK_STYLE, ContextKeys.IDENTITY_SOCIAL_STYLE,
            ContextKeys.IDENTITY_DISCLOSURE_STYLE, ContextKeys.IDENTITY_ORIGIN_MOTIF,
            ContextKeys.IDENTITY_FORMATIVE_EVENT,
            ContextKeys.WORK_FORMER_PROFESSION, ContextKeys.WORK_PROFESSION_CHANGED_DAY);

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
        return Identity.enabled() && request.villager() != null;
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        if (request.villager() == null) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.ABSENT, "no villager");
            return;
        }
        if (request.volatileOnly()) {
            // Every anchor here is generated once and never rerolled — that is the whole point of
            // them — so there is nothing a refresh could learn.
            builder.reportCapability(ContextCapabilities.Status.READY, "");
            return;
        }
        try {
            Optional<VillagerIdentityRecord> profile = Identity.of(request.villager());
            if (profile.isEmpty()) {
                builder.allUnavailable(DECLARES);
                builder.reportCapability(ContextCapabilities.Status.DEGRADED, "villager has no profile");
                return;
            }
            VillagerIdentityRecord record = profile.get();
            builder.put(ContextKeys.IDENTITY_INTERESTS, record.interests());
            builder.put(ContextKeys.IDENTITY_VALUES, record.values());
            builder.put(ContextKeys.IDENTITY_COMFORT, record.comfort());
            builder.put(ContextKeys.IDENTITY_AVERSION, record.aversion());
            builder.put(ContextKeys.IDENTITY_WORK_STYLE, record.workStyle());
            builder.put(ContextKeys.IDENTITY_SOCIAL_STYLE, record.socialStyle());
            builder.put(ContextKeys.IDENTITY_DISCLOSURE_STYLE, record.disclosureStyle());
            builder.put(ContextKeys.IDENTITY_ORIGIN_MOTIF, record.originMotif());
            builder.put(ContextKeys.IDENTITY_FORMATIVE_EVENT, record.formativeEvent());

            // A trade nobody has ever seen this villager change is UNKNOWN, not "none": the record
            // only fills these when a transition was actually observed.
            builder.put(ContextKeys.WORK_FORMER_PROFESSION, record.formerProfession());
            if (record.formerProfession().isPresent()) {
                builder.put(ContextKeys.WORK_PROFESSION_CHANGED_DAY, record.lastLifeChangeDay());
            } else {
                builder.unknown(ContextKeys.WORK_PROFESSION_CHANGED_DAY);
            }
            builder.reportCapability(ContextCapabilities.Status.READY, "");
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("identity context unavailable; those fields go dark", t);
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "identity read failed");
        }
    }
}
