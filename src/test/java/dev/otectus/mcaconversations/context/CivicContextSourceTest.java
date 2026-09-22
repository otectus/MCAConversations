package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.compat.CivicBridge;
import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CivicContextSourceTest {
    @Test
    void contactSnapshotCarriesOnlyRequesterScopedPublicFacts() {
        ContextSnapshotBuilder builder = new ContextSnapshotBuilder();
        builder.beginSource(CivicContextSource.ID);
        CivicContextSource.contribute(builder, new CivicBridge.Contact(
                UUID.fromString("fcd0b19b-77fb-4aa2-aaf4-a83272b071b8"),
                new ResourceLocation("ultima_kingdoms", "lamplighters"),
                "civic.organization.lamplighters", "guild_contact", true, false, true,
                List.of("civic.introduction.rank"), List.of(), 17L, 4L));
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CIVIC_CONTACT));
        assertEquals(Optional.of("ultima_kingdoms:lamplighters"),
                snapshot.value(ContextKeys.CIVIC_ORGANIZATION));
        assertEquals(Optional.of("civic.organization.lamplighters"),
                snapshot.value(ContextKeys.CIVIC_ORGANIZATION_NAME_KEY));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CIVIC_INTRODUCTION_QUALIFIED));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CIVIC_COMMISSION_QUALIFIED));
        assertEquals(Optional.of(List.of("civic.introduction.rank")),
                snapshot.value(ContextKeys.CIVIC_INTRODUCTION_REASONS));
        assertEquals(Optional.of(17L), snapshot.value(ContextKeys.CIVIC_STATE_REVISION));
        assertEquals(Optional.of(4L), snapshot.value(ContextKeys.CIVIC_POLICY_REVISION));
    }
}
