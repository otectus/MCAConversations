package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.gossip.TownsteadObservations.Resident;
import dev.otectus.mcaconversations.gossip.TownsteadObservations.Village;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

/**
 * Turns what the sweep sees of Townstead into gossip (Townstead spec §15.2, §15.4). Pure: every
 * decision is a function of the previous observation and the current one.
 *
 * <p>The rules that keep it quiet: a first observation only seeds; a resident or village that could
 * not be read is unknown, not changed; a need crisis has hysteresis — entered at emergency, left only
 * once every need is comfortable — and a cooldown; a recovery is news only after a crisis was; a
 * building is gone only after several sweeps agree; and each sweep tells at most a couple of new
 * skills or buildings per subject.
 */
public final class TownsteadGossipDiff {

    /** Most newly learned skills one resident produces in one sweep. */
    public static final int MAX_SKILLS_PER_SCAN = 2;
    /** Most building events one village produces in one sweep. */
    public static final int MAX_BUILDINGS_PER_SCAN = 2;
    /** {@link Resident#lastCrisisDay()} for a crisis never reported. */
    public static final long NEVER = -1L;

    private TownsteadGossipDiff() {
    }

    /** One event to record. {@code attributes} may carry {@code a_key}/{@code b_key} translations. */
    public record Derived(GossipEventType type, UUID aUuid, String aName, String bName,
                          Map<String, String> attributes) {
    }

    /**
     * A loaded resident as Townstead reports them now.
     *
     * @param stable       every need comfortable ({@code primaryNeed == none}), the only way out of a crisis
     * @param calendarYear the Townstead year, 0 with no calendar
     */
    public record ResidentNow(UUID uuid, String name, boolean inCrisis, boolean collapsed, boolean stable,
                              String profession, int tier, Set<String> skills, String stage, int age,
                              int calendarYear) {
    }

    public record ResidentStep(List<Derived> events, Resident next) {
    }

    public static ResidentStep resident(ResidentNow now, Resident prior, long today, long observed,
                                        int crisisCooldownDays) {
        boolean crisisNow = now.inCrisis() || now.collapsed();
        if (prior == null) {
            // Seeding: somebody first seen mid-crisis is not news, and their recovery will not be either.
            return new ResidentStep(List.of(), new Resident(crisisNow, now.collapsed(), false, NEVER,
                    now.profession(), now.tier(), now.skills(), now.stage(), now.age(), 0, observed));
        }
        List<Derived> events = new ArrayList<>();
        boolean told = prior.crisisTold();
        long lastCrisisDay = prior.lastCrisisDay();

        if (now.collapsed() && !prior.collapsed()) {
            events.add(person(GossipEventType.COLLAPSE, now, Map.of()));
            told = true;
        }
        boolean crisis = prior.crisis();
        if (!crisis && crisisNow) {
            crisis = true;
            boolean cooled = lastCrisisDay == NEVER || today - lastCrisisDay >= crisisCooldownDays;
            if (!now.collapsed() && cooled) {
                events.add(person(GossipEventType.NEED_CRISIS, now, Map.of()));
                told = true;
                lastCrisisDay = today;
            }
        } else if (crisis && !now.collapsed() && now.stable()) {
            crisis = false;
        }
        if ((prior.crisis() || prior.collapsed()) && !crisis && !now.collapsed() && told) {
            events.add(person(GossipEventType.RECOVERY, now, Map.of()));
            told = false;
        }

        boolean sameTrade = !now.profession().isEmpty() && now.profession().equals(prior.profession());
        if (sameTrade && prior.tier() > 0 && now.tier() > prior.tier()) {
            events.add(person(GossipEventType.PROFESSION_TIER_UP, now, Map.of(
                    "profession", now.profession(), "tier", Integer.toString(now.tier()))));
        }
        if (sameTrade) {
            Set<String> learned = new TreeSet<>(now.skills());
            learned.removeAll(prior.skills());
            int reported = 0;
            for (String skill : learned) {
                if (reported++ >= MAX_SKILLS_PER_SCAN) {
                    break;
                }
                events.add(person(GossipEventType.SKILL_LEARNED, now, Map.of("skill", skill)));
            }
        }

        boolean stageChanged = !prior.stage().isEmpty() && !now.stage().isEmpty() && !now.stage().equals(prior.stage());
        if (stageChanged) {
            events.add(person(GossipEventType.LIFE_STAGE_CHANGED, now, Map.of("stage", now.stage(), "from", prior.stage())));
        }
        int birthdayYear = prior.birthdayYear();
        boolean aYearOlder = prior.age() > 0 && now.age() == prior.age() + 1;
        boolean newYear = now.calendarYear() == 0 || now.calendarYear() != prior.birthdayYear();
        if (aYearOlder && !stageChanged && newYear) {
            events.add(person(GossipEventType.BIRTHDAY, now, Map.of("age", Integer.toString(now.age()))));
            birthdayYear = now.calendarYear();
        }
        return new ResidentStep(events, new Resident(crisis, now.collapsed(), told, lastCrisisDay, now.profession(),
                now.tier(), now.skills(), now.stage(), now.age(), birthdayYear, observed));
    }

    /**
     * A village as it can be read now.
     *
     * @param buildings    registered building id to type, or null when MCA's village could not be read
     * @param spiritKnown  Townstead reported this village's spirit
     */
    public record VillageNow(Map<Integer, String> buildings, boolean spiritKnown, int spiritTier,
                             String classification, String primary, String secondary) {
    }

    public record VillageStep(List<Derived> events, Village next) {
    }

    public static VillageStep village(int villageId, VillageNow now, Village prior, int confirmScans, long observed) {
        if (prior == null) {
            return new VillageStep(List.of(), new Village(now.buildings() == null ? Map.of() : now.buildings(), Map.of(),
                    now.spiritKnown() ? now.spiritTier() : 0,
                    now.spiritKnown() ? now.classification() : "",
                    now.spiritKnown() ? now.primary() : "",
                    now.spiritKnown() ? now.secondary() : "", observed));
        }
        List<Derived> events = new ArrayList<>();
        Map<Integer, String> buildings = new TreeMap<>(prior.buildings());
        Map<Integer, Integer> missing = new TreeMap<>(prior.missing());
        if (now.buildings() != null) {
            int budget = MAX_BUILDINGS_PER_SCAN;
            for (Map.Entry<Integer, String> entry : now.buildings().entrySet()) {
                missing.remove(entry.getKey());
                String before = buildings.put(entry.getKey(), entry.getValue());
                if (before == null && budget-- > 0) {
                    events.add(building(GossipEventType.BUILDING_REGISTERED, villageId, entry.getKey(), entry.getValue()));
                }
            }
            for (Map.Entry<Integer, String> entry : prior.buildings().entrySet()) {
                if (now.buildings().containsKey(entry.getKey())) {
                    continue;
                }
                int absent = missing.getOrDefault(entry.getKey(), 0) + 1;
                if (absent < Math.max(1, confirmScans)) {
                    missing.put(entry.getKey(), absent);
                    continue;
                }
                missing.remove(entry.getKey());
                buildings.remove(entry.getKey());
                if (budget-- > 0) {
                    events.add(building(GossipEventType.BUILDING_REMOVED, villageId, entry.getKey(), entry.getValue()));
                }
            }
        }

        int tier = prior.spiritTier();
        String classification = prior.classification();
        String primary = prior.primary();
        String secondary = prior.secondary();
        if (now.spiritKnown()) {
            boolean seeded = prior.spiritTier() > 0 || !prior.classification().isEmpty() || !prior.primary().isEmpty();
            boolean structural = now.spiritTier() != tier || !now.classification().equals(classification)
                    || !now.primary().equals(primary) || !now.secondary().equals(secondary);
            if (seeded && structural && !now.primary().isEmpty()) {
                events.add(spirit(villageId, now));
            }
            tier = now.spiritTier();
            classification = now.classification();
            primary = now.primary();
            secondary = now.secondary();
        }
        return new VillageStep(events, new Village(buildings, missing, tier, classification, primary, secondary, observed));
    }

    private static Derived person(GossipEventType type, ResidentNow now, Map<String, String> attributes) {
        return new Derived(type, now.uuid(), now.name(), "", attributes);
    }

    private static Derived building(GossipEventType type, int villageId, int id, String buildingType) {
        return new Derived(type, synthetic("building:" + villageId + ":" + id), humanize(familyOf(buildingType)), "",
                Map.of("building", buildingType, "a_key", "buildingType." + buildingType));
    }

    private static Derived spirit(int villageId, VillageNow now) {
        String path = now.primary().substring(now.primary().lastIndexOf(':') + 1);
        return new Derived(GossipEventType.SPIRIT_IDENTITY_CHANGED,
                synthetic("spirit:" + villageId + ":" + now.primary() + ":" + now.spiritTier()), humanize(path), "",
                Map.of("spirit", now.primary(), "tier", Integer.toString(now.spiritTier()),
                        "classification", now.classification(), "a_key", "townstead.spirit." + path));
    }

    /** A stable subject id for a thing that is not a villager, so it can never be mistaken for one. */
    static UUID synthetic(String what) {
        return UUID.nameUUIDFromBytes(("mcaconversations:townstead:" + what).getBytes(StandardCharsets.UTF_8));
    }

    /** {@code dock_l2} → {@code dock}. */
    static String familyOf(String type) {
        int marker = type.lastIndexOf("_l");
        if (marker > 0 && marker + 2 < type.length() && type.substring(marker + 2).chars().allMatch(Character::isDigit)) {
            return type.substring(0, marker);
        }
        return type;
    }

    static String humanize(String id) {
        return id.substring(id.lastIndexOf(':') + 1).replace('_', ' ').trim();
    }
}
