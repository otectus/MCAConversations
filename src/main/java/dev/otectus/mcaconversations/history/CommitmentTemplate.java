package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.regex.Pattern;

/**
 * The authored shape of a promise (spec §8.5, §12.6).
 *
 * <p>The parser is where "never write a promise the game cannot resolve" becomes structural: a
 * template must name a registered {@link CommitmentResolver}, and every resolver except
 * {@code manual_neutral} must also name the {@link #target} it will be checked against. A template
 * that promises iron without saying which tag counts as iron is rejected at load rather than shipped
 * as a promise nobody can keep.
 *
 * @param id          the template id, used as the commitment key for a pair
 * @param resolver    how it will be observed
 * @param target      what satisfies it, as a typed value — an item tag, a quest id, a reply id
 * @param dueAfterDays days from the promise until it is due; empty for an open-ended one
 * @param madeBy      who is promising
 * @param threadId    the thread this promise belongs to, when it belongs to one
 */
public record CommitmentTemplate(String id,
                                 CommitmentResolver resolver,
                                 NarrativeValue target,
                                 OptionalLong dueAfterDays,
                                 CommitmentRecord.Party madeBy,
                                 String threadId) {

    public static final Pattern ID = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    public CommitmentTemplate {
        id = normalize(id);
        threadId = normalize(threadId);
        resolver = resolver == null ? CommitmentResolver.MANUAL_NEUTRAL : resolver;
        target = target == null ? NarrativeValue.EMPTY : target;
        madeBy = madeBy == null ? CommitmentRecord.Party.PLAYER : madeBy;
        dueAfterDays = dueAfterDays == null ? OptionalLong.empty() : dueAfterDays;
    }

    /** True when this promise can be observed on the running install right now. */
    public boolean isObservable() {
        return resolver.isAvailable();
    }

    /** A fresh commitment record from this template. */
    public CommitmentRecord make(long today, Optional<java.util.UUID> episodeId) {
        return CommitmentRecord.made(id, resolver, target, madeBy, today,
                dueAfterDays.isPresent() ? OptionalLong.of(today + dueAfterDays.getAsLong())
                        : OptionalLong.empty(),
                episodeId);
    }

    public static CommitmentTemplate fromJson(String id, JsonObject json) {
        String normalized = normalize(id);
        if (!ID.matcher(normalized).matches()) {
            throw new IllegalArgumentException("commitment template '" + id + "' must match " + ID.pattern());
        }
        if (json == null || !json.has("resolver")) {
            throw new IllegalArgumentException("commitment template '" + normalized
                    + "' must name a resolver; a promise with no observer has to be worded as willingness "
                    + "instead, or declared manual_neutral");
        }
        CommitmentResolver resolver = CommitmentResolver.byKey(json.get("resolver").getAsString())
                .orElseThrow(() -> new IllegalArgumentException("commitment template '" + normalized
                        + "' resolver '" + json.get("resolver").getAsString() + "' is not registered"));

        NarrativeValue target = json.has("target")
                ? NarrativeValue.parse(json.get("target").getAsString())
                : NarrativeValue.EMPTY;
        if (resolver.isJudgeable() && target.isEmpty() && resolver != CommitmentResolver.VISIT_AFTER_DAY) {
            throw new IllegalArgumentException("commitment template '" + normalized + "' uses resolver '"
                    + resolver.key() + "' but names no target, so nothing could ever satisfy it");
        }

        OptionalLong dueAfterDays = json.has("due_after_days")
                ? OptionalLong.of(json.get("due_after_days").getAsLong())
                : OptionalLong.empty();
        if (resolver == CommitmentResolver.VISIT_AFTER_DAY && dueAfterDays.isEmpty()) {
            throw new IllegalArgumentException("commitment template '" + normalized
                    + "' resolves by a return visit but names no due_after_days");
        }

        CommitmentRecord.Party madeBy = json.has("made_by")
                && "villager".equalsIgnoreCase(json.get("made_by").getAsString().trim())
                ? CommitmentRecord.Party.VILLAGER
                : CommitmentRecord.Party.PLAYER;

        return new CommitmentTemplate(normalized, resolver, target, dueAfterDays, madeBy,
                json.has("thread") ? json.get("thread").getAsString() : "");
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
