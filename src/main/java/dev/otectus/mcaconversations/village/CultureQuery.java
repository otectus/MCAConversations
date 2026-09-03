package dev.otectus.mcaconversations.village;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

/**
 * {@code conversations_culture: {token?, family?, stance?, not?}} (spec §17.3).
 *
 * <p>Two questions in one condition, because content asks them together. {@code token} asks whether
 * this village holds a particular token at all — the gate on a line that names the split oak.
 * {@code family} with {@code stance} asks what <em>this</em> resident makes of whatever their village
 * holds in that family, which is the gate on a line that argues with it.
 *
 * <p>Given both, both must hold: the village keeps the first frost supper <em>and</em> this villager
 * has a reservation about it. That combination is the one a scene usually wants, and writing it as
 * two conditions would let a page fire for a villager who questions a festival their village does not
 * actually keep.
 *
 * <p>A villager with no home village matches nothing, before negation. A wanderer has no culture, and
 * saying they "do not endorse" one would be asserting something about a village that is not theirs.
 */
public record CultureQuery(String token, CultureFamily family, CultureStance stance, boolean negate) {

    public static final CultureQuery INVALID = new CultureQuery("", null, null, false);

    public CultureQuery {
        token = token == null ? "" : token.trim().toLowerCase(java.util.Locale.ROOT);
    }

    public boolean isValid() {
        return !token.isEmpty() || family != null;
    }

    public static CultureQuery fromJson(JsonObject json) {
        if (json == null) {
            return INVALID;
        }
        return new CultureQuery(
                json.has("token") ? json.get("token").getAsString() : "",
                json.has("family")
                        ? CultureFamily.byKey(json.get("family").getAsString()).orElse(null) : null,
                json.has("stance")
                        ? CultureStance.byKey(json.get("stance").getAsString()).orElse(null) : null,
                json.has("not") && json.get("not").getAsBoolean());
    }

    /** True when this villager's village, and this villager, answer the question as asked. */
    public boolean matches(Entity villager) {
        if (!isValid()) {
            return false;
        }
        boolean matched = evaluate(villager);
        return negate != matched;
    }

    private boolean evaluate(Entity villager) {
        Optional<VillageCultureRecord> culture = VillageCulture.of(villager);
        if (culture.isEmpty()) {
            return false;
        }
        if (!token.isEmpty() && !culture.get().tokenIds().contains(token)) {
            return false;
        }
        if (family == null) {
            return true;
        }
        if (culture.get().token(family).isEmpty()) {
            return false;
        }
        // No stance asked means "this village has something in that family", which is the gate a
        // line naming the landmark needs without caring what the speaker makes of it.
        return stance == null || VillageCulture.stanceOf(villager, family) == stance;
    }
}
