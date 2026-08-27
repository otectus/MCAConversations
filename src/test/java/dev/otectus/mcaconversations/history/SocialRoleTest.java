package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The observed-role rules (spec §16.2).
 *
 * <p>The point of every assertion here is the plan's refusal of the "random rival": a villager may
 * only hold a role towards somebody for a reason they could give, and a role that has no natural end
 * has to be endable, or a village accumulates relationships nobody can account for.
 */
class SocialRoleTest {

    private static final UUID TOMAS = UUID.fromString("00000000-0000-0000-0000-00000000a001");
    private static final UUID MARA = UUID.fromString("00000000-0000-0000-0000-00000000a002");

    @Test
    @DisplayName("a role with no cause is refused rather than stored")
    void causelessRolesAreRefused() {
        VillagerHistory history = new VillagerHistory();

        assertFalse(history.putRole(SocialRoleRecord.observed(TOMAS, SocialRole.AVOIDED, "", 4L)),
                "\"I avoid him\" with no reason is the drama the plan rules out");
        assertTrue(history.roles().isEmpty());
    }

    @Test
    @DisplayName("a structural role has no expiry and an episodic one does")
    void lifetimesFollowTheKindOfRelationship() {
        SocialRoleRecord mentor = SocialRoleRecord.observed(
                MARA, SocialRole.MENTOR, "episode.taught_me_the_trade", 10L);
        SocialRoleRecord fire = SocialRoleRecord.observed(
                TOMAS, SocialRole.SHARED_EVENT, "event.the_fire", 10L);

        assertTrue(mentor.expiresDay().isEmpty(), "who taught you does not lapse on a timer");
        assertEquals(OptionalLong.of(10L + SocialRole.SHARED_EVENT.defaultLifetimeDays()),
                fire.expiresDay(), "being at the same fire stops being worth mentioning");
    }

    @Test
    @DisplayName("a role that never expires can still be withdrawn")
    void structuralRolesAreEndable() {
        VillagerHistory history = new VillagerHistory();
        history.putRole(SocialRoleRecord.observed(
                MARA, SocialRole.APPRENTICE, "episode.i_taught_her", 3L));

        assertTrue(history.withdrawRole(MARA, SocialRole.APPRENTICE));
        assertTrue(history.rolesOf(MARA).isEmpty(),
                "otherwise she is introduced as the apprentice a decade after she left");
    }

    @Test
    @DisplayName("seeing a role again restarts its clock and keeps how long it has been true")
    void renewalKeepsTheOriginDate() {
        SocialRoleRecord first = SocialRoleRecord.observed(
                TOMAS, SocialRole.CUSTOMER, "episode.bought_again", 5L);
        SocialRoleRecord again = first.renewed("episode.bought_again", 40L);

        assertEquals(5L, again.createdDay(), "\"for years now\" has to rest on the first day");
        assertEquals(35L, again.daysHeld(40L));
        assertEquals(OptionalLong.of(40L + SocialRole.CUSTOMER.defaultLifetimeDays()),
                again.expiresDay(), "and the lapse is measured from the last time it was true");
    }

    @Test
    @DisplayName("an expired role is pruned and a structural one is not")
    void pruningRespectsThePolicy() {
        VillagerHistory history = new VillagerHistory();
        history.putRole(SocialRoleRecord.observed(TOMAS, SocialRole.SHARED_EVENT, "event.the_fire", 1L));
        history.putRole(SocialRoleRecord.observed(MARA, SocialRole.MENTOR, "episode.taught_me", 1L));

        history.prune(1L + SocialRole.SHARED_EVENT.defaultLifetimeDays() + 1L);

        assertTrue(history.rolesOf(TOMAS).isEmpty());
        assertEquals(1, history.rolesOf(MARA).size());
    }

    @Test
    @DisplayName("the mirror of a role is offered and never applied on its own")
    void mirrorsAreNotAutomatic() {
        VillagerHistory history = new VillagerHistory();
        history.putRole(SocialRoleRecord.observed(MARA, SocialRole.MENTOR, "episode.taught_me", 2L));

        assertEquals(Optional.of(SocialRole.APPRENTICE), SocialRole.MENTOR.mirror());
        assertTrue(history.rolesOfKind(SocialRole.APPRENTICE).isEmpty(),
                "one side's account of a relationship is not evidence of the other side's");
    }

    @Test
    @DisplayName("roles survive a save and a load")
    void roundTrips() {
        SocialRoleRecord original = SocialRoleRecord.observed(
                TOMAS, SocialRole.SUPPLY_DEPENDENCY, "episode.short_of_hides", 12L);
        CompoundTag saved = original.save();

        assertEquals(Optional.of(original), SocialRoleRecord.load(saved));
    }

    @Test
    @DisplayName("a row naming a role this build does not know reads as empty")
    void unknownRolesAreDropped() {
        CompoundTag row = new CompoundTag();
        row.putUUID("target", TOMAS);
        row.putString("role", "arch_nemesis");
        row.putString("cause", "episode.something");

        assertTrue(SocialRoleRecord.load(row).isEmpty());
    }

    @Test
    @DisplayName("the condition asks whether the role is held, and for how long")
    void conditionReadsHeldRoles() {
        VillagerHistory history = new VillagerHistory();
        history.putRole(SocialRoleRecord.observed(MARA, SocialRole.MENTOR, "episode.taught_me", 10L));
        Optional<VillagerHistory> held = Optional.of(history);

        JsonObject json = new JsonObject();
        json.addProperty("role", "mentor");
        json.addProperty("min_days", 30);
        HistoryQuery.Role query = HistoryQuery.Role.fromJson(json);

        assertTrue(query.isValid());
        assertFalse(query.matches(held, 20L), "twelve days is not \"for years\"");
        assertTrue(query.matches(held, 50L));
        assertFalse(query.matches(Optional.empty(), 50L));
    }

    @Test
    @DisplayName("the action refuses to record a role it cannot account for")
    void directiveNeedsACause() {
        JsonObject json = new JsonObject();
        json.addProperty("role", "avoided");
        json.addProperty("target", "person");
        assertFalse(HistoryDirective.Role.fromJson(json).isValid());

        json.addProperty("cause", "episode.the_argument");
        assertTrue(HistoryDirective.Role.fromJson(json).isValid());
    }

    @Test
    @DisplayName("a withdrawal needs no cause, because it is the end of one")
    void withdrawalNeedsNoCause() {
        JsonObject json = new JsonObject();
        json.addProperty("role", "apprentice");
        json.addProperty("target", "person");
        json.addProperty("withdraw", true);

        assertTrue(HistoryDirective.Role.fromJson(json).isValid());
    }

    @Test
    @DisplayName("the cap gives up an expiring role before a structural one")
    void capEvictsTheLeastStructural() {
        VillagerHistory history = new VillagerHistory();
        int cap = HistoryCaps.rolesPerVillager();
        history.putRole(SocialRoleRecord.observed(MARA, SocialRole.MENTOR, "episode.taught_me", 1L));
        for (int i = 0; i < cap; i++) {
            UUID other = UUID.fromString(String.format("00000000-0000-0000-0000-0000000b%04d", i));
            history.putRole(SocialRoleRecord.observed(
                    other, SocialRole.SHARED_EVENT, "event.number_" + i, 2L + i));
        }

        assertEquals(cap, history.roles().size(), "the cap holds");
        assertEquals(1, history.rolesOf(MARA).size(),
                "and a mentorship is not evicted to make room for having been at the same fire");
    }
}
