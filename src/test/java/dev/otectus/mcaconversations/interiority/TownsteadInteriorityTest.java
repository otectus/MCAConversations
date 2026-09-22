package dev.otectus.mcaconversations.interiority;

import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.compat.TownsteadPersonalityView;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.personality.Personalities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Custom Townstead personalities keep their namespace, and fall back through their MCA base (spec §10). */
class TownsteadInteriorityTest {

    private ConversationContentBundle before;

    private static InteriorityProfile profile(String key, int warmth) {
        return InteriorityProfile.fromJson(key, JsonParser.parseString(
                "{\"baselines\": {\"warmth\": " + warmth + "}}").getAsJsonObject());
    }

    @BeforeEach
    void publish() {
        before = ContentReloadCoordinator.committed();
        Interiority.setProfilesForTesting(Map.of(
                "upbeat", profile("upbeat", 6),
                "mypack:reserved_scholar", profile("mypack:reserved_scholar", -4),
                "otherpack:reserved_scholar", profile("otherpack:reserved_scholar", 2)));
    }

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    @Test
    @DisplayName("MCA ids reduce to the bare voice; custom ids keep their namespace; junk is neutral")
    void profileKeys() {
        assertEquals("upbeat", Personalities.profileKey("witty"));
        assertEquals("upbeat", Personalities.profileKey("mca:WITTY"));
        assertEquals("upbeat", Personalities.profileKey("minecraft:upbeat"));
        assertEquals("mypack:reserved_scholar", Personalities.profileKey("MyPack:Reserved_Scholar"));
        assertEquals("", Personalities.profileKey("not a key!"));
        assertEquals("", Personalities.profileKey("a:b:c"));
        assertEquals("", Personalities.profileKey(null));
        assertTrue(Personalities.isCustomKey("mypack:reserved_scholar"));
        assertFalse(Personalities.isCustomKey("upbeat"));
    }

    @Test
    @DisplayName("two packs' reserved_scholar are two profiles, not one")
    void namespacesDoNotCollide() {
        assertEquals(-4, Interiority.profile("mypack:reserved_scholar").baseline(DispositionAxis.WARMTH));
        assertEquals(2, Interiority.profile("otherpack:reserved_scholar").baseline(DispositionAxis.WARMTH));
    }

    @Test
    @DisplayName("an exact custom profile first, then the custom definition's MCA base, then nothing")
    void lookupOrder() {
        TownsteadPersonalityView authored = new TownsteadPersonalityView("mypack:reserved_scholar", true,
                "upbeat", null, null);
        assertEquals(Optional.of(-4), Interiority.townsteadProfile(authored)
                .map(p -> p.baseline(DispositionAxis.WARMTH)));

        TownsteadPersonalityView unauthored = new TownsteadPersonalityView("mypack:quiet_smith", true,
                "upbeat", null, null);
        assertEquals(Optional.of(6), Interiority.townsteadProfile(unauthored)
                .map(p -> p.baseline(DispositionAxis.WARMTH)), "the base voice supplies the profile");

        TownsteadPersonalityView orphan = new TownsteadPersonalityView("mypack:quiet_smith", true, "", null, null);
        assertTrue(Interiority.townsteadProfile(orphan).isEmpty(), "MCA's own reading answers next");
        assertTrue(Interiority.townsteadProfile(TownsteadPersonalityView.EMPTY).isEmpty());
    }
}
