package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The provenance invariants, which are the ones that keep a villager honest (spec §16.3).
 *
 * <p>Every assertion here is a sentence a villager must not be able to say. Between them they cover
 * the two failure modes §2.4 names — stating hearsay as observation, and producing specificity with
 * no source behind it — plus the privacy rule §4.5 turns into a relationship mechanic.
 */
class ProvenanceTest {

    private static final UUID TELLER = UUID.fromString("00000000-0000-0000-0000-0000000000a1");
    private static final UUID PLAYER = UUID.fromString("00000000-0000-0000-0000-0000000000b2");

    @Test
    @DisplayName("a rumour cannot be held as an observation, however it was authored")
    void confidenceIsClampedToWhatTheSourceCanBear() {
        Provenance rumour = new Provenance(KnowledgeSource.UNKNOWN_RUMOR, Optional.empty(),
                Confidence.WITNESSED, PrivacyLevel.ORDINARY, null, Distortion.NONE, 0);

        assertEquals(Confidence.UNCERTAIN, rumour.confidence(),
                "an unattributed rumour claiming to be witnessed must be lowered, not believed");
        assertTrue(rumour.needsHedging(), "and it must be spoken with a hedge");
    }

    @Test
    @DisplayName("a chain the villager cannot produce is not a chain")
    void toldByWithoutATellerBecomesAnUnattributedRumour() {
        Provenance orphan = new Provenance(KnowledgeSource.TOLD_BY, Optional.empty(),
                Confidence.LIKELY, PrivacyLevel.ORDINARY, null, Distortion.NONE, 0);

        assertEquals(KnowledgeSource.UNKNOWN_RUMOR, orphan.source());
        assertTrue(orphan.toldBy().isEmpty());
    }

    @Test
    @DisplayName("a teller on a first-hand source is dropped rather than kept as decoration")
    void firstHandSourcesCarryNoTeller() {
        Provenance seen = new Provenance(KnowledgeSource.WITNESSED, Optional.of(TELLER),
                Confidence.WITNESSED, PrivacyLevel.ORDINARY, null, Distortion.NONE, 0);

        assertTrue(seen.toldBy().isEmpty(), "nobody told them; they were there");
    }

    @Test
    @DisplayName("permission may be stricter than privacy implies, and never freer")
    void permissionOnlyNarrows() {
        Provenance confidential = new Provenance(KnowledgeSource.PARTICIPANT, Optional.empty(),
                Confidence.WITNESSED, PrivacyLevel.CONFIDENTIAL, SharePermission.MAY_NAME,
                Distortion.NONE, 0);
        assertEquals(SharePermission.MAY_NOT_SHARE, confidential.share(),
                "a confidence cannot be laundered by relabelling what may be done with it");

        Provenance ordinary = new Provenance(KnowledgeSource.WITNESSED, Optional.empty(),
                Confidence.WITNESSED, PrivacyLevel.ORDINARY, SharePermission.MAY_NOT_SHARE,
                Distortion.NONE, 0);
        assertEquals(SharePermission.MAY_NOT_SHARE, ordinary.share(),
                "but an author may embargo something ordinary");
    }

    @Test
    @DisplayName("a discreet fact may be described and its people may not be named")
    void discreetFactsAreAnonymous() {
        Provenance discreet = Provenance.witnessed(PrivacyLevel.DISCREET);

        assertTrue(discreet.maySpeak());
        assertFalse(discreet.mayName());
    }

    @Test
    @DisplayName("one hop costs confidence, re-sources the fact, and invents nothing")
    void propagationWeakensWithoutDistorting() {
        Provenance seen = Provenance.witnessed(PrivacyLevel.ORDINARY);
        Provenance heard = seen.afterHop(TELLER);

        assertEquals(KnowledgeSource.TOLD_BY, heard.source());
        assertEquals(Optional.of(TELLER), heard.toldBy());
        assertEquals(Confidence.LIKELY, heard.confidence());
        assertEquals(Distortion.NONE, heard.distortion(),
                "propagation may not invent detail; a distortion is authored");
        assertEquals(PrivacyLevel.ORDINARY, heard.privacy(),
                "privacy travels with the fact rather than wearing off");
    }

    @Test
    @DisplayName("a hop with nobody to name lands as an unattributed rumour")
    void propagationWithoutATellerIsAnonymous() {
        Provenance heard = Provenance.witnessed(PrivacyLevel.PUBLIC).afterHop(null);

        assertEquals(KnowledgeSource.UNKNOWN_RUMOR, heard.source());
        assertTrue(heard.needsHedging());
    }

    @Test
    @DisplayName("a confidence stays a confidence after it has travelled")
    void privacySurvivesPropagation() {
        Provenance told = Provenance.told(TELLER, PrivacyLevel.CONFIDENTIAL);

        assertEquals(SharePermission.MAY_NOT_SHARE, told.share());
        assertTrue(told.isBreachable());
        assertEquals(SharePermission.MAY_NOT_SHARE, told.afterHop(TELLER).share(),
                "repeating it does not make it repeatable");
    }

    @Test
    @DisplayName("what the player says about themselves is believed and never called observed")
    void selfReportIsNeverObservation() {
        Provenance claim = Provenance.selfReported(PLAYER, PrivacyLevel.DISCREET);

        assertEquals(Confidence.SELF_REPORTED, claim.confidence());
        assertFalse(claim.confidence().isObserved());
        assertEquals(Optional.of(PLAYER), claim.toldBy());
    }

    @Test
    @DisplayName("provenance survives a save and a load unchanged")
    void roundTrips() {
        Provenance original = new Provenance(KnowledgeSource.COWORKER, Optional.empty(),
                Confidence.LIKELY, PrivacyLevel.DISCREET, SharePermission.MAY_NOT_SHARE,
                Distortion.OMITTED_DETAIL, 0);

        assertEquals(original, Provenance.load(original.save()));
    }

    @Test
    @DisplayName("a save written before provenance existed keeps the footing it recorded")
    void legacyRowsMigrate() {
        Provenance migrated = Provenance.fromLegacy("participant", "confidential", "witnessed");

        assertEquals(KnowledgeSource.PARTICIPANT, migrated.source());
        assertEquals(PrivacyLevel.CONFIDENTIAL, migrated.privacy());
        assertEquals(Confidence.WITNESSED, migrated.confidence());
        assertEquals(SharePermission.MAY_NOT_SHARE, migrated.share(),
                "those saves had nowhere to put a permission, so it is derived");
    }

    @Test
    @DisplayName("an unreadable legacy source reads as witnessed, which is what that code assumed")
    void unknownLegacySourceFallsBack() {
        assertEquals(KnowledgeSource.WITNESSED, Provenance.fromLegacy("", "", "").source());
    }

    @Test
    @DisplayName("an episode written before provenance loads with its old fields intact")
    void episodesMigrate() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-0000000000c3");
        UUID owner = UUID.fromString("00000000-0000-0000-0000-0000000000d4");
        CompoundTag legacy = new CompoundTag();
        legacy.putUUID("id", id);
        legacy.putString("kind", "work.damaged_volume");
        legacy.putString("subject", "work.librarian.damaged_volume");
        legacy.putString("state", EpisodeState.ACTIVE.key());
        legacy.putUUID("owner", owner);
        legacy.putString("source", "participant");
        legacy.putString("privacy", "discreet");
        legacy.putString("confidence", "witnessed");
        legacy.putInt("salience", 40);
        legacy.putLong("created", 10L);
        legacy.putLong("updated", 12L);

        EpisodeRecord loaded = EpisodeRecord.load(legacy).orElseThrow();

        assertEquals(KnowledgeSource.PARTICIPANT, loaded.source());
        assertEquals(PrivacyLevel.DISCREET, loaded.privacy());
        assertEquals(Confidence.WITNESSED, loaded.confidence());
        assertEquals(loaded, EpisodeRecord.load(loaded.save()).orElseThrow(),
                "and it round-trips in the new shape from then on");
    }

    @Test
    @DisplayName("an episode retold is the same episode, held one step less firmly")
    void episodeHops() {
        EpisodeRecord mine = EpisodeRecord.opened(
                UUID.fromString("00000000-0000-0000-0000-0000000000e5"),
                "village.wall", "village.repair", EpisodeState.ACTIVE,
                UUID.fromString("00000000-0000-0000-0000-0000000000f6"),
                java.util.Map.of(), PrivacyLevel.PUBLIC, 30, 5L);

        EpisodeRecord theirs = mine.asToldBy(TELLER);

        assertEquals(mine.id(), theirs.id(), "the event id survives the chain");
        assertEquals(KnowledgeSource.TOLD_BY, theirs.source());
        assertEquals(Confidence.LIKELY, theirs.confidence());
        assertSame(mine, mine.withProvenance(mine.provenance()),
                "and re-setting the same provenance is a no-op");
    }
}
