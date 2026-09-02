package dev.otectus.mcaconversations.context;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Every context field this mod can supply, and the only vocabulary a {@code conversations_context}
 * condition may name (spec §7.1).
 *
 * <p>The list is deliberately closed and mostly coarse. Almost every value is a <b>token</b> — a
 * short lowercase string from a small band — rather than a raw number or a block position, for two
 * reasons. Bands are stable enough to author against ("night", "storm", "injured") where exact values
 * are not, and a band cannot smuggle a precise world fact into prose that never observed it
 * (spec §17.2, §4.2).
 *
 * <p>Fields are grouped by the domain that owns them. A field belongs to exactly one provider; two
 * providers writing the same key is a bug the builder rejects, which is what stops the same click
 * seeing two different answers to one question (spec §7.4).
 */
public final class ContextKeys {

    private ContextKeys() {
    }

    // --- Speaker -------------------------------------------------------------------------------
    public static final ContextKey<UUID> SPEAKER_UUID = ContextKey.of("speaker.uuid", UUID.class);
    public static final ContextKey<String> SPEAKER_NAME = ContextKey.of("speaker.name", String.class);
    /** {@code baby}, {@code child}, {@code teen}, {@code adult} — MCA's own age groups. */
    public static final ContextKey<String> SPEAKER_AGE = ContextKey.of("speaker.age", String.class);
    public static final ContextKey<String> SPEAKER_PERSONALITY = ContextKey.of("speaker.personality", String.class);
    public static final ContextKey<String> SPEAKER_VOICE_FAMILY = ContextKey.of("speaker.voice_family", String.class);
    /** {@code hale}, {@code hurt}, {@code grave} — never a hit-point number. */
    public static final ContextKey<String> SPEAKER_HEALTH_BAND = ContextKey.volatileOf("speaker.health_band", String.class);
    /** MCA's mood name, lowercased. Acute, so volatile. */
    public static final ContextKey<String> SPEAKER_MOOD = ContextKey.volatileOf("speaker.mood", String.class);
    public static final ContextKey<Boolean> SPEAKER_MARRIED = ContextKey.of("speaker.married", Boolean.class);

    // --- Work ----------------------------------------------------------------------------------
    /** The exact registry id, {@code minecraft:farmer}. The single most useful new MCA read. */
    public static final ContextKey<String> WORK_PROFESSION_ID = ContextKey.of("work.profession_id", String.class);
    public static final ContextKey<String> WORK_PROFESSION_NAME = ContextKey.of("work.profession_name", String.class);
    /** The {@code WorkArchetype} key from the shipped profile. */
    public static final ContextKey<String> WORK_ARCHETYPE = ContextKey.of("work.archetype", String.class);
    /** Coarse brain activity: {@code work}, {@code meet}, {@code rest}, {@code idle}, {@code panic}. */
    public static final ContextKey<String> WORK_ACTIVITY = ContextKey.volatileOf("work.activity", String.class);
    /** MCA's assigned chore, lowercased, or unknown when none is set. */
    public static final ContextKey<String> WORK_CHORE = ContextKey.volatileOf("work.chore", String.class);
    /** True while the villager is standing at their own workplace. */
    public static final ContextKey<Boolean> WORK_AT_WORKSITE = ContextKey.volatileOf("work.at_worksite", Boolean.class);
    /** Coarse item tags the villager is carrying, for "is that a new hammer?" — never a count. */
    public static final ContextKey<Set<String>> WORK_MATERIAL_TAGS = ContextKey.generic("work.material_tags", Set.class);
    /** The profession the villager held before this one, when a change was actually observed. */
    public static final ContextKey<String> WORK_FORMER_PROFESSION = ContextKey.of("work.former_profession", String.class);
    public static final ContextKey<Long> WORK_PROFESSION_CHANGED_DAY = ContextKey.of("work.profession_changed_day", Long.class);

    // --- Place ---------------------------------------------------------------------------------
    public static final ContextKey<String> PLACE_DIMENSION = ContextKey.of("place.dimension", String.class);
    /** A coarse biome family: {@code temperate}, {@code cold}, {@code arid}, {@code aquatic}, {@code nether}… */
    public static final ContextKey<String> PLACE_BIOME_FAMILY = ContextKey.of("place.biome_family", String.class);
    public static final ContextKey<Integer> PLACE_VILLAGE_ID = ContextKey.of("place.village_id", Integer.class);
    public static final ContextKey<String> PLACE_VILLAGE_NAME = ContextKey.of("place.village_name", String.class);
    /** The semantic location tokens of spec §17.4 — never coordinates. */
    public static final ContextKey<String> PLACE_LOCATION = ContextKey.volatileOf("place.location", String.class);
    /** Whether the speaker is under cover, which is what makes a rain line honest. */
    public static final ContextKey<Boolean> PLACE_SHELTERED = ContextKey.volatileOf("place.sheltered", Boolean.class);
    /** {@code away} when the speaker is outside their own home village. */
    public static final ContextKey<Boolean> PLACE_AWAY_FROM_HOME = ContextKey.volatileOf("place.away_from_home", Boolean.class);

    // --- Time ----------------------------------------------------------------------------------
    public static final ContextKey<Long> TIME_DAY = ContextKey.of("time.day", Long.class);
    /** {@code dawn}, {@code morning}, {@code midday}, {@code afternoon}, {@code dusk}, {@code night}. */
    public static final ContextKey<String> TIME_BAND = ContextKey.volatileOf("time.band", String.class);
    public static final ContextKey<String> TIME_SEASON = ContextKey.of("time.season", String.class);
    public static final ContextKey<String> TIME_HOLIDAY = ContextKey.of("time.holiday", String.class);
    /** Days since this pair last spoke; unknown on a first meeting rather than zero. */
    public static final ContextKey<Long> TIME_DAYS_SINCE_LAST_TALK = ContextKey.of("time.days_since_last_talk", Long.class);
    public static final ContextKey<Long> TIME_DAYS_SINCE_FIRST_MET = ContextKey.of("time.days_since_first_met", Long.class);
    /** {@code none}, {@code brief}, {@code long}, {@code very_long} — the absence band (spec §13). */
    public static final ContextKey<String> TIME_ABSENCE_BAND = ContextKey.of("time.absence_band", String.class);

    // --- Weather -------------------------------------------------------------------------------
    /** {@code clear}, {@code rain}, {@code storm}. */
    public static final ContextKey<String> WEATHER_STATE = ContextKey.volatileOf("weather.state", String.class);
    /** True when this profession's profile declares a weather affinity, so a weather line is on-topic. */
    public static final ContextKey<Boolean> WEATHER_RELEVANT = ContextKey.of("weather.relevant", Boolean.class);

    // --- Player --------------------------------------------------------------------------------
    public static final ContextKey<UUID> PLAYER_UUID = ContextKey.of("player.uuid", UUID.class);
    public static final ContextKey<String> PLAYER_NAME = ContextKey.of("player.name", String.class);
    public static final ContextKey<String> PLAYER_HEALTH_BAND = ContextKey.volatileOf("player.health_band", String.class);
    /** Coarse tags for what the player is visibly holding. Drives "Is that iron?", never a promise. */
    public static final ContextKey<Set<String>> PLAYER_HELD_TAGS = ContextKey.volatileGeneric("player.held_tags", Set.class);
    public static final ContextKey<Integer> PLAYER_HEARTS = ContextKey.of("player.hearts", Integer.class);
    /** The {@code RelationshipBand} key. */
    public static final ContextKey<String> PLAYER_RELATIONSHIP_BAND = ContextKey.of("player.relationship_band", String.class);
    public static final ContextKey<Boolean> PLAYER_IS_SPOUSE = ContextKey.of("player.is_spouse", Boolean.class);
    public static final ContextKey<Boolean> PLAYER_IS_FAMILY = ContextKey.of("player.is_family", Boolean.class);

    // --- Social --------------------------------------------------------------------------------
    /** Names of living family members the speaker may safely refer to, resolved from MCA's tree. */
    public static final ContextKey<List<String>> SOCIAL_FAMILY_NAMES = ContextKey.generic("social.family_names", List.class);
    /** Nearby villagers who could plausibly hear or join, capped small (spec §11.6). */
    public static final ContextKey<List<String>> SOCIAL_NEARBY = ContextKey.volatileGeneric("social.nearby", List.class);
    public static final ContextKey<Integer> SOCIAL_VILLAGE_POPULATION = ContextKey.of("social.village_population", Integer.class);

    // --- Narrative (this mod's own stores) -----------------------------------------------------
    /** Ids of episodes the speaker currently owns in a non-terminal state. */
    public static final ContextKey<List<String>> NARRATIVE_ACTIVE_EPISODES = ContextKey.generic("narrative.active_episodes", List.class);
    /** Thread template ids ready to resume for this pair. */
    public static final ContextKey<List<String>> NARRATIVE_READY_THREADS = ContextKey.generic("narrative.ready_threads", List.class);
    /** Commitment template ids whose due day has passed. */
    public static final ContextKey<List<String>> NARRATIVE_DUE_COMMITMENTS = ContextKey.generic("narrative.due_commitments", List.class);
    /** True while an unrepaired rupture stands between this pair. */
    public static final ContextKey<Boolean> NARRATIVE_RUPTURE = ContextKey.of("narrative.rupture", Boolean.class);
    /** Subjects discussed recently, newest first, for the four-level recency check (spec §9.4). */
    public static final ContextKey<List<String>> NARRATIVE_RECENT_SUBJECTS = ContextKey.generic("narrative.recent_subjects", List.class);

    // --- Identity (this mod's own stable profile) ----------------------------------------------
    public static final ContextKey<Set<String>> IDENTITY_INTERESTS = ContextKey.generic("identity.interests", Set.class);
    public static final ContextKey<Set<String>> IDENTITY_VALUES = ContextKey.generic("identity.values", Set.class);
    public static final ContextKey<String> IDENTITY_COMFORT = ContextKey.of("identity.comfort", String.class);
    public static final ContextKey<String> IDENTITY_AVERSION = ContextKey.of("identity.aversion", String.class);
    public static final ContextKey<String> IDENTITY_WORK_STYLE = ContextKey.of("identity.work_style", String.class);
    public static final ContextKey<String> IDENTITY_SOCIAL_STYLE = ContextKey.of("identity.social_style", String.class);
    public static final ContextKey<String> IDENTITY_DISCLOSURE_STYLE = ContextKey.of("identity.disclosure_style", String.class);
    public static final ContextKey<String> IDENTITY_ORIGIN_MOTIF = ContextKey.of("identity.origin_motif", String.class);
    public static final ContextKey<String> IDENTITY_FORMATIVE_EVENT = ContextKey.of("identity.formative_event", String.class);

    // --- Village -------------------------------------------------------------------------------
    /** Village culture tokens shared by residents, when a culture record exists (spec §17.3). */
    public static final ContextKey<Set<String>> VILLAGE_CULTURE = ContextKey.generic("village.culture", Set.class);
    /** The most recent untold village event type for this speaker, from the gossip log. */
    public static final ContextKey<String> VILLAGE_RECENT_EVENT = ContextKey.of("village.recent_event", String.class);

    /**
     * Forces this class to initialise.
     *
     * <p>{@link ContextKey#byId} and {@link ContextKey#all} both call it, because a lookup that ran
     * before any key constant had been touched would consult an empty registry and report a perfectly
     * valid field name as unknown.
     */
    static void touch() {
        // Body intentionally empty: invoking any static method of a class initialises it (JLS 12.4.1),
        // so the call itself is the whole mechanism.
    }
}
