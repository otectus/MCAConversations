package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Meaningful-contact accounting on a pair, and the upgraded-world flag (Stability spec §8.5, §11.2). */
class SocialContactRecordTest {

    @Test
    void oneCreditPerDayAndNothingForAnEarlierClock() {
        PairHistory pair = new PairHistory();
        assertTrue(pair.recordContact(10, false));
        assertFalse(pair.recordContact(10, false), "the same day credits once");
        assertEquals(1, pair.contactDays());
        assertTrue(pair.recordContact(11, false));
        assertFalse(pair.recordContact(5, false), "a clock that reads earlier credits nothing");
        assertEquals(2, pair.contactDays());
    }

    @Test
    void theLegacyDecisionIsTakenOnceAtInitialisation() {
        PairHistory legacy = new PairHistory();
        legacy.recordContact(1, true);
        legacy.recordContact(2, false);
        assertTrue(legacy.legacyContact());

        PairHistory fresh = new PairHistory();
        fresh.recordContact(1, false);
        fresh.recordContact(2, true);
        assertFalse(fresh.legacyContact(), "later hearts never make a pair retroactively legacy");
    }

    @Test
    void contactSurvivesSaveAndLoadAndKeepsThePairNonEmpty() {
        PairHistory pair = new PairHistory();
        assertTrue(pair.isEmpty());
        pair.recordContact(40, true);
        pair.recordContact(41, true);
        assertFalse(pair.isEmpty());

        PairHistory loaded = PairHistory.load(pair.save());
        assertTrue(loaded.contactInitialized());
        assertEquals(2, loaded.contactDays());
        assertTrue(loaded.legacyContact());
        assertFalse(loaded.recordContact(41, false), "the last credited day is persisted too");
    }

    @Test
    void aPairSavedBeforeTheSocialModelHasNoContactRecord() {
        PairHistory old = PairHistory.load(new CompoundTag());
        assertFalse(old.contactInitialized());
        assertEquals(0, old.contactDays());
        assertFalse(new PairHistory().save().contains("contact"), "absent until the first credit");
    }

    @Test
    void aCorruptedDayCountIsBounded() {
        CompoundTag tag = new CompoundTag();
        CompoundTag contact = new CompoundTag();
        contact.putInt("days", Integer.MAX_VALUE);
        tag.put("contact", contact);
        assertEquals(PairHistory.MAX_CONTACT_DAYS, PairHistory.load(tag).contactDays());
        contact.putInt("days", -5);
        assertEquals(0, PairHistory.load(tag).contactDays());
    }

    @Test
    void onlyAWorldThatPredatesTheSocialModelMayImportLegacyRelationships() {
        assertFalse(new ConversationHistoryStore().legacyImportWorld(), "a new store is a new world");

        CompoundTag preSocial = new CompoundTag();
        preSocial.putInt("version", ConversationHistoryStore.CURRENT_VERSION);
        ConversationHistoryStore upgraded = ConversationHistoryStore.load(preSocial);
        assertTrue(upgraded.legacyImportWorld(), "a file written before 1.8.0 is an upgraded world");

        CompoundTag saved = upgraded.save(new CompoundTag());
        assertTrue(ConversationHistoryStore.load(saved).legacyImportWorld(), "and stays one after saving");

        CompoundTag fresh = new ConversationHistoryStore().save(new CompoundTag());
        assertFalse(ConversationHistoryStore.load(fresh).legacyImportWorld());
    }
}
