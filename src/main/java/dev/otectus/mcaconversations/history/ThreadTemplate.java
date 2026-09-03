package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The authored shape of a resumable subject between one villager and one player (spec §8.4, §11.5).
 *
 * <p>The single most important field is {@link #resumeScenes}: coming back to a subject is an
 * <em>authored</em> moment, not a restored screen. A thread that lapses without a resume scene has
 * nowhere honest to go, so the parser refuses one.
 *
 * @param id             the template id, which is also the thread key for a pair
 * @param topic          the hub topic it appears under
 * @param subject        the conversational subject, finer than the topic
 * @param resumeScenes   scenes eligible to pick this thread up again, in preference order
 * @param cooldownDays   days after a scene before this thread may be raised again
 * @param expiresAfterDays days of silence after which it lapses; empty means it never does
 * @param privacy        how freely the subject may be named, including in a hub label
 * @param episodeKind    the episode kind this thread binds to, when it binds to one
 * @param lapseScene     the scene played when a lapsed thread is finally mentioned
 */
public record ThreadTemplate(String id,
                             String topic,
                             String subject,
                             java.util.List<String> resumeScenes,
                             long cooldownDays,
                             OptionalLong expiresAfterDays,
                             PrivacyLevel privacy,
                             String episodeKind,
                             String lapseScene) {

    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    public ThreadTemplate {
        id = normalize(id);
        topic = normalize(topic);
        subject = normalize(subject);
        episodeKind = normalize(episodeKind);
        lapseScene = normalize(lapseScene);
        resumeScenes = java.util.List.copyOf(resumeScenes);
        privacy = privacy == null ? PrivacyLevel.defaultLevel() : privacy;
        cooldownDays = Math.max(0L, cooldownDays);
        expiresAfterDays = expiresAfterDays == null ? OptionalLong.empty() : expiresAfterDays;
    }

    /** True when this thread binds to a live episode rather than standing on its own. */
    public boolean needsEpisode() {
        return !episodeKind.isEmpty();
    }

    /** A fresh thread record from this template. */
    public SharedThreadRecord open(Optional<java.util.UUID> episodeId, long today) {
        SharedThreadRecord record = SharedThreadRecord.opened(id, topic, subject, episodeId, privacy, today);
        return record.withSchedule(today,
                expiresAfterDays.isPresent()
                        ? OptionalLong.of(today + expiresAfterDays.getAsLong())
                        : OptionalLong.empty());
    }

    public static ThreadTemplate fromJson(String id, JsonObject json) {
        String normalized = normalize(id);
        if (!ID.matcher(normalized).matches()) {
            throw new IllegalArgumentException("thread template '" + id + "' must match " + ID.pattern());
        }
        String topic = require(json, "topic", normalized);
        String subject = require(json, "subject", normalized);

        Set<String> resumeScenes = new LinkedHashSet<>(EpisodeTemplate.strings(json, "resume_scenes"));
        if (resumeScenes.isEmpty()) {
            throw new IllegalArgumentException("thread template '" + normalized
                    + "' must declare at least one resume_scene: a subject with no authored way back is a "
                    + "thread that can only ever be dropped");
        }

        return new ThreadTemplate(normalized, topic, subject,
                java.util.List.copyOf(resumeScenes),
                json.has("cooldown_days") ? json.get("cooldown_days").getAsLong() : 1L,
                json.has("expires_after_days")
                        ? OptionalLong.of(json.get("expires_after_days").getAsLong())
                        : OptionalLong.empty(),
                json.has("privacy")
                        ? PrivacyLevel.byKey(json.get("privacy").getAsString())
                                .orElse(PrivacyLevel.defaultLevel())
                        : PrivacyLevel.defaultLevel(),
                json.has("episode_kind") ? json.get("episode_kind").getAsString() : "",
                json.has("lapse_scene") ? json.get("lapse_scene").getAsString() : "");
    }

    private static String require(JsonObject json, String field, String id) {
        if (json == null || !json.has(field) || !json.get(field).isJsonPrimitive()) {
            throw new IllegalArgumentException("thread template '" + id + "' requires a \"" + field + "\"");
        }
        String value = json.get(field).getAsString().trim().toLowerCase(Locale.ROOT);
        if (!ID.matcher(value).matches()) {
            throw new IllegalArgumentException("thread template '" + id + "' " + field + " '" + value
                    + "' must match " + ID.pattern());
        }
        return value;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
