package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.compat.CivicBridge;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Optional;

/** Requester/speaker civic facts supplied only by Ultima's public reflective API. */
public final class CivicContextSource implements ConversationContextSource {
    public static final String ID = "civic";
    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.CIVIC_CONTACT, ContextKeys.CIVIC_ORGANIZATION,
            ContextKeys.CIVIC_ORGANIZATION_NAME_KEY, ContextKeys.CIVIC_ROLE,
            ContextKeys.CIVIC_SERVICES_AVAILABLE, ContextKeys.CIVIC_INTRODUCTION_QUALIFIED,
            ContextKeys.CIVIC_COMMISSION_QUALIFIED, ContextKeys.CIVIC_INTRODUCTION_REASONS,
            ContextKeys.CIVIC_COMMISSION_REASONS, ContextKeys.CIVIC_STATE_REVISION,
            ContextKeys.CIVIC_POLICY_REVISION);

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
        return CivicBridge.isAvailable() && request.player() != null && request.villager() != null;
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        if (!isAvailable(request)) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.ABSENT, "Ultima civic API unavailable");
            return;
        }
        Optional<CivicBridge.Contact> context = CivicBridge.speakerContext(request.player(), request.villager());
        if (context.isEmpty()) {
            builder.put(ContextKeys.CIVIC_CONTACT, false);
            for (ContextKey<?> key : DECLARES) {
                if (key != ContextKeys.CIVIC_CONTACT) builder.unknown(key);
            }
            builder.reportCapability(ContextCapabilities.Status.READY, "speaker is not a civic contact");
            return;
        }
        contribute(builder, context.get());
        builder.reportCapability(ContextCapabilities.Status.READY, "");
    }

    static void contribute(ContextSnapshotBuilder builder, CivicBridge.Contact context) {
        builder.put(ContextKeys.CIVIC_CONTACT, true);
        builder.put(ContextKeys.CIVIC_ORGANIZATION, context.organization().toString());
        builder.put(ContextKeys.CIVIC_ORGANIZATION_NAME_KEY, context.nameKey());
        builder.put(ContextKeys.CIVIC_ROLE, context.role());
        builder.put(ContextKeys.CIVIC_SERVICES_AVAILABLE, context.servicesAvailable());
        builder.put(ContextKeys.CIVIC_INTRODUCTION_QUALIFIED, context.introductionQualified());
        builder.put(ContextKeys.CIVIC_COMMISSION_QUALIFIED, context.commissionQualified());
        builder.put(ContextKeys.CIVIC_INTRODUCTION_REASONS, context.reasons());
        builder.put(ContextKeys.CIVIC_COMMISSION_REASONS, context.commissionReasons());
        builder.put(ContextKeys.CIVIC_STATE_REVISION, context.stateRevision());
        builder.put(ContextKeys.CIVIC_POLICY_REVISION, context.policyRevision());
    }
}
