package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * What one villager and one player are in the middle of discussing (spec §8.4).
 *
 * <p>Small on purpose. A thread stores the frame — which subject, bound to which episode, waiting on
 * what, resumable when — and nothing about the dialogue itself. That separation is what makes
 * {@link #resumeScene} an authored choice rather than a saved screen: on return the player gets a line
 * written for coming back after four days, not the stale button page they left open (spec §11.5).
 *
 * @param templateId       the authored thread template this instantiates
 * @param topic            the hub topic it belongs under
 * @param subject          the conversational subject, finer than the topic
 * @param episodeId        the episode it is about, when it is about one
 * @param status           what it is waiting for
 * @param lastScene        the last scene actually played on this thread
 * @param lastOutcome      how that scene ended, in the existing {@code OutcomeFamily} vocabulary
 * @param obligation       what is outstanding, as {@code commitment:<id>} or {@code answer:<frame>}
 * @param playerStance     the player's last stance on this subject, so a resume can honour it
 * @param privacy          how freely the subject may be named — including in a menu label
 * @param nextEligibleDay  the earliest day it may be raised again
 * @param expiresDay       when it lapses if nothing happens
 * @param resumeCount      how many times it has already been picked up; damps repeated resumes
 * @param lastMentionedDay the day it was last spoken about
 */
public record SharedThreadRecord(String templateId,
                                 String topic,
                                 String subject,
                                 Optional<UUID> episodeId,
                                 ThreadStatus status,
                                 String lastScene,
                                 String lastOutcome,
                                 String obligation,
                                 String playerStance,
                                 PrivacyLevel privacy,
                                 long nextEligibleDay,
                                 OptionalLong expiresDay,
                                 int resumeCount,
                                 long lastMentionedDay) {

    /** Beyond this many resumes a thread is repeating itself and should resolve or lapse. */
    public static final int MAX_RESUMES = 6;

    private static final String KEY_TEMPLATE = "template";
    private static final String KEY_TOPIC = "topic";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_EPISODE = "episode";
    private static final String KEY_STATUS = "status";
    private static final String KEY_LAST_SCENE = "last_scene";
    private static final String KEY_LAST_OUTCOME = "last_outcome";
    private static final String KEY_OBLIGATION = "obligation";
    private static final String KEY_STANCE = "stance";
    private static final String KEY_PRIVACY = "privacy";
    private static final String KEY_NEXT_DAY = "next_day";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_RESUMES = "resumes";
    private static final String KEY_MENTIONED = "mentioned";

    public SharedThreadRecord {
        templateId = normalize(templateId);
        topic = normalize(topic);
        subject = normalize(subject);
        lastScene = normalize(lastScene);
        lastOutcome = normalize(lastOutcome);
        obligation = normalize(obligation);
        playerStance = normalize(playerStance);
        status = status == null ? ThreadStatus.OPEN : status;
        privacy = privacy == null ? PrivacyLevel.defaultLevel() : privacy;
        episodeId = episodeId == null ? Optional.empty() : episodeId;
        expiresDay = expiresDay == null ? OptionalLong.empty() : expiresDay;
        resumeCount = Math.max(0, resumeCount);
    }

    /** A thread opened by a scene that has just been played. */
    public static SharedThreadRecord opened(String templateId, String topic, String subject,
                                            Optional<UUID> episodeId, PrivacyLevel privacy, long day) {
        return new SharedThreadRecord(templateId, topic, subject, episodeId, ThreadStatus.OPEN,
                "", "", "", "", privacy, day, OptionalLong.empty(), 0, day);
    }

    /** The key this thread is filed under for a villager/player pair. */
    public String key() {
        return templateId;
    }

    /** True when the thread may be offered today. */
    public boolean isReady(long today) {
        return status.isResumable() && today >= nextEligibleDay && !hasLapsed(today)
                && resumeCount < MAX_RESUMES;
    }

    public boolean hasLapsed(long today) {
        return expiresDay.isPresent() && today > expiresDay.getAsLong();
    }

    /** True when something is outstanding that the villager may legitimately raise. */
    public boolean hasObligation() {
        return !obligation.isEmpty();
    }

    /** The commitment id this thread is waiting on, when its obligation is a promise. */
    public Optional<String> outstandingCommitment() {
        return obligation.startsWith("commitment:")
                ? Optional.of(obligation.substring("commitment:".length()))
                : Optional.empty();
    }

    public long daysSinceMentioned(long today) {
        return Math.max(0L, today - lastMentionedDay);
    }

    // --- Transitions ------------------------------------------------------------------------------

    /**
     * Moves the thread to a new status.
     *
     * <p>A closed thread never reopens through this method. Reopening a resolved subject is a new
     * thread with its own template, because "we are talking about this again" and "we never stopped"
     * are different things to a player and want different opening lines.
     */
    public SharedThreadRecord withStatus(ThreadStatus next, long day) {
        if (next == null || next == status || status.isClosed()) {
            return this;
        }
        return new SharedThreadRecord(templateId, topic, subject, episodeId, next, lastScene,
                lastOutcome, obligation, playerStance, privacy, nextEligibleDay, expiresDay,
                resumeCount, day);
    }

    /** Records that a scene was played on this thread, bumping the resume counter. */
    public SharedThreadRecord played(String scene, String outcome, String stance,
                                     long day, long cooldownDays) {
        return new SharedThreadRecord(templateId, topic, subject, episodeId, status,
                scene, outcome, obligation, stance.isBlank() ? playerStance : stance, privacy,
                day + Math.max(0L, cooldownDays), expiresDay, resumeCount + 1, day);
    }

    /** Sets or clears the outstanding obligation. An empty string clears it. */
    public SharedThreadRecord withObligation(String newObligation, long day) {
        return new SharedThreadRecord(templateId, topic, subject, episodeId, status, lastScene,
                lastOutcome, newObligation, playerStance, privacy, nextEligibleDay, expiresDay,
                resumeCount, day);
    }

    public SharedThreadRecord withEpisode(UUID newEpisodeId) {
        return new SharedThreadRecord(templateId, topic, subject, Optional.ofNullable(newEpisodeId),
                status, lastScene, lastOutcome, obligation, playerStance, privacy, nextEligibleDay,
                expiresDay, resumeCount, lastMentionedDay);
    }

    public SharedThreadRecord withSchedule(long newNextEligibleDay, OptionalLong newExpiry) {
        return new SharedThreadRecord(templateId, topic, subject, episodeId, status, lastScene,
                lastOutcome, obligation, playerStance, privacy, newNextEligibleDay,
                newExpiry == null ? expiresDay : newExpiry, resumeCount, lastMentionedDay);
    }

    // --- Persistence -------------------------------------------------------------------------------

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString(KEY_TEMPLATE, templateId);
        tag.putString(KEY_TOPIC, topic);
        tag.putString(KEY_SUBJECT, subject);
        episodeId.ifPresent(id -> tag.putUUID(KEY_EPISODE, id));
        tag.putString(KEY_STATUS, status.key());
        putIfPresent(tag, KEY_LAST_SCENE, lastScene);
        putIfPresent(tag, KEY_LAST_OUTCOME, lastOutcome);
        putIfPresent(tag, KEY_OBLIGATION, obligation);
        putIfPresent(tag, KEY_STANCE, playerStance);
        tag.putString(KEY_PRIVACY, privacy.key());
        tag.putLong(KEY_NEXT_DAY, nextEligibleDay);
        expiresDay.ifPresent(day -> tag.putLong(KEY_EXPIRES, day));
        tag.putInt(KEY_RESUMES, resumeCount);
        tag.putLong(KEY_MENTIONED, lastMentionedDay);
        return tag;
    }

    public static Optional<SharedThreadRecord> load(CompoundTag tag) {
        if (tag == null) {
            return Optional.empty();
        }
        String template = tag.getString(KEY_TEMPLATE);
        if (template.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(new SharedThreadRecord(template, tag.getString(KEY_TOPIC),
                tag.getString(KEY_SUBJECT),
                tag.hasUUID(KEY_EPISODE) ? Optional.of(tag.getUUID(KEY_EPISODE)) : Optional.empty(),
                ThreadStatus.byKey(tag.getString(KEY_STATUS)).orElse(ThreadStatus.OPEN),
                tag.getString(KEY_LAST_SCENE), tag.getString(KEY_LAST_OUTCOME),
                tag.getString(KEY_OBLIGATION), tag.getString(KEY_STANCE),
                PrivacyLevel.byKey(tag.getString(KEY_PRIVACY)).orElse(PrivacyLevel.defaultLevel()),
                tag.getLong(KEY_NEXT_DAY),
                tag.contains(KEY_EXPIRES) ? OptionalLong.of(tag.getLong(KEY_EXPIRES)) : OptionalLong.empty(),
                tag.getInt(KEY_RESUMES), tag.getLong(KEY_MENTIONED)));
    }

    private static void putIfPresent(CompoundTag tag, String key, String value) {
        if (value != null && !value.isEmpty()) {
            tag.putString(key, value);
        }
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
