package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.VillagerFinder.Ranked;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The AABB the level is queried with is a cube, so a villager may be up to {@code sqrt(3)·radius}
 * away and still be returned by it. Ranking is what turns that cube back into the sphere the config
 * radius describes; these tests pin that boundary and the ordering that follows it.
 */
class VillagerFinderTest {

    private static final double R = 16.0D;
    private static final double R_SQR = R * R;
    private static final double EPS = 0.01D;

    private static Ranked at(String name, double dx, double dy, double dz) {
        return new Ranked(UUID.nameUUIDFromBytes((name + dx + dy + dz).getBytes()), name,
                dx * dx + dy * dy + dz * dz, 0.0D);
    }

    @Test
    void cubeCornerIsOutsideTheSphere() {
        // (0.75R, 0, 0.75R) sits inside the inflated AABB but 1.06·R away from the player.
        Ranked corner = at("Agnes", 0.75 * R, 0.0, 0.75 * R);
        assertTrue(corner.distSqr() > R_SQR, "fixture must be a genuine cube-corner case");
        assertEquals(List.of(), VillagerFinder.rank(List.of(corner), R_SQR, 16));
    }

    @Test
    void justInsideTheRadiusIsKept() {
        Ranked near = at("Agnes", R - EPS, 0.0, 0.0);
        assertEquals(List.of(near), VillagerFinder.rank(List.of(near), R_SQR, 16));
    }

    @Test
    void justOutsideTheRadiusIsDropped() {
        Ranked far = at("Agnes", R + EPS, 0.0, 0.0);
        assertEquals(List.of(), VillagerFinder.rank(List.of(far), R_SQR, 16));
    }

    @Test
    void exactlyOnTheRadiusIsKept() {
        Ranked on = new Ranked(UUID.randomUUID(), "Agnes", R_SQR, 0.0D);
        assertEquals(List.of(on), VillagerFinder.rank(List.of(on), R_SQR, 16));
    }

    @Test
    void verticalSeparationCountsTowardTheRadius() {
        // A villager directly overhead is as far away as one directly ahead; height is not free.
        Ranked above = at("Agnes", 0.0, R + EPS, 0.0);
        assertEquals(List.of(), VillagerFinder.rank(List.of(above), R_SQR, 16));
    }

    @Test
    void nearestFirst() {
        Ranked near = at("Agnes", 2.0, 0.0, 0.0);
        Ranked mid = at("Bo", 5.0, 0.0, 0.0);
        Ranked far = at("Cyn", 9.0, 0.0, 0.0);
        assertEquals(List.of(near, mid, far), VillagerFinder.rank(List.of(far, near, mid), R_SQR, 16));
    }

    @Test
    void capAppliesAfterTheSphereFilter() {
        // 4 out-of-range villagers ahead of 3 in-range ones: the cap must not be spent on the ones
        // the filter is about to reject.
        List<Ranked> gathered = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            gathered.add(at("Far" + i, R + 1.0 + i, 0.0, 0.0));
        }
        gathered.add(at("Near0", 1.0, 0.0, 0.0));
        gathered.add(at("Near1", 2.0, 0.0, 0.0));
        gathered.add(at("Near2", 3.0, 0.0, 0.0));

        List<Ranked> ranked = VillagerFinder.rank(gathered, R_SQR, 2);
        assertEquals(2, ranked.size());
        assertEquals(List.of("Near0", "Near1"), ranked.stream().map(Ranked::name).toList());
    }

    @Test
    void capIsHonored() {
        List<Ranked> gathered = new ArrayList<>();
        for (int i = 0; i < VillagerFinder.MAX_CANDIDATES + 5; i++) {
            gathered.add(at("V" + i, 1.0 + i * 0.1, 0.0, 0.0));
        }
        assertEquals(VillagerFinder.MAX_CANDIDATES,
                VillagerFinder.rank(gathered, R_SQR, VillagerFinder.MAX_CANDIDATES).size());
    }

    @Test
    void twoSameNamedVillagersAtTheSameDistanceRankDeterministically() {
        // The level hands entities back in whatever order it stores them; the ranking may not.
        Ranked a = new Ranked(UUID.fromString("00000000-0000-0000-0000-0000000000aa"), "Agnes", 9.0D, 0.0D);
        Ranked b = new Ranked(UUID.fromString("00000000-0000-0000-0000-0000000000bb"), "Agnes", 9.0D, 0.0D);
        assertEquals(VillagerFinder.rank(List.of(a, b), R_SQR, 16),
                VillagerFinder.rank(List.of(b, a), R_SQR, 16));
        assertEquals(List.of(a, b), VillagerFinder.rank(List.of(b, a), R_SQR, 16));
    }
}
