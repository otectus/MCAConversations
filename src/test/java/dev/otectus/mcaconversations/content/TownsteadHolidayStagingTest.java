package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** {@code townstead_holidays} is staged and published with the rest of the bundle, or not at all. */
class TownsteadHolidayStagingTest {

    @Test
    @DisplayName("a mapping publishes with the bundle; a malformed one refuses the whole reload")
    void stagesAtomically() throws Exception {
        ReloadFixture.Outcome good = new ReloadFixture().withChitchat()
                .with("townstead_holidays", "mypack", "moons", """
                        {"holidays": {"harvest": {"profile": "mypack:moons", "month": 9, "day": 3,
                         "holiday": "harvest_festival"}}}""")
                .run();
        assertTrue(good.committedNow(), () -> String.valueOf(good.attempt().problems()));
        ConversationContentBundle published = ContentReloadCoordinator.committed();
        assertEquals(Optional.of("harvest_festival"),
                published.townsteadHolidays().holidayFor("mypack:moons", 9, 3, 67));

        ReloadFixture.Outcome broken = new ReloadFixture().withChitchat()
                .with("townstead_holidays", "mypack", "moons", """
                        {"holidays": {"harvest": {"profile": "mypack:moons", "month": 9, "day": 3,
                         "holiday": "boxing_day"}}}""")
                .run();
        assertFalse(broken.committedNow());
        assertSame(published, ContentReloadCoordinator.committed(), "the previous mapping stays in force");
    }

    @Test
    @DisplayName("custom interiority ids under different namespaces stage as two profiles")
    void namespacedInteriorityStages() throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture().withChitchat()
                .with("interiority", "mypack", "custom", """
                        {"profiles": {"mypack:reserved_scholar": {}, "otherpack:reserved_scholar": {},
                         "friendly": {}}}""")
                .run();
        assertTrue(outcome.committedNow(), () -> String.valueOf(outcome.attempt().problems()));
        assertTrue(ContentReloadCoordinator.committed().interiority().containsKey("mypack:reserved_scholar"));
        assertTrue(ContentReloadCoordinator.committed().interiority().containsKey("otherpack:reserved_scholar"));
    }
}
