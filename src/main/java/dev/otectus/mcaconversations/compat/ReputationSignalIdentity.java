package dev.otectus.mcaconversations.compat;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Locale;

/**
 * The operation identity one authored conversation deed is delivered under (spec §30.6, Reputation
 * §11.2 and §16.2).
 *
 * <p>Pure, and on the always-loaded side of {@link ReputationBridge}'s gate, so the rule that decides
 * whether two clicks are the same deed can be unit-tested without a server, a world or MCA:
 * Reputation installed.
 *
 * <p><b>What changed in 1.7.2 and why.</b> Until now the key was
 * {@code conversation:<villager>:<player>:<decision>}. Two things were wrong with it. The villager
 * made the same apology payable again by walking up to a different resident, and the decision alone
 * made a second, genuinely unrelated incident unaddressable — one apology stage is an identity for
 * one grievance, not for every grievance a village holds. So the villager leaves the key and the
 * <em>incident being amended</em> enters it. Reputation's receipt identity already scopes the key by
 * producer namespace, player and community, so neither belongs here either.
 *
 * <p>A reused identity with a different payload is a conflict rather than a new award, which is why
 * over-long keys are compacted with a digest instead of truncated: two different identities must never
 * collapse into one key that Reputation would then answer for both.
 */
public final class ReputationSignalIdentity {

    /** The producer namespace every Conversations delivery is filed under. */
    public static final String NAMESPACE = "mcaconversations";

    /** Reputation's own bound on an operation key (its dedupe-key bound, because it is one). */
    public static final int MAX_OPERATION_KEY_LENGTH = 256;

    /** The prefix a compacted key carries, so a digest is never mistaken for an authored decision. */
    public static final String DIGEST_PREFIX = "conversation:sha256:";

    private ReputationSignalIdentity() {
    }

    /**
     * The operation key for one authored decision, optionally bound to the incident it amends.
     *
     * @param decisionId       the authored decision; the apology <em>stage</em>, not the button click
     * @param boundIncidentId  the incident this deed answers, or null when it answers none
     * @return a stable, bounded key; never null and never blank for a usable decision
     * @throws IllegalArgumentException when the decision is missing, which is a pack error
     */
    public static String operationKey(String decisionId, String boundIncidentId) {
        String decision = decisionId == null ? "" : decisionId.trim().toLowerCase(Locale.ROOT);
        if (decision.isEmpty()) {
            throw new IllegalArgumentException("a reputation signal needs a decision id");
        }
        String bound = boundIncidentId == null ? "" : boundIncidentId.trim().toLowerCase(Locale.ROOT);
        String key = bound.isEmpty()
                ? "conversation:" + decision
                : "conversation:" + decision + ":" + bound;
        return key.length() <= MAX_OPERATION_KEY_LENGTH ? key : compact(key);
    }

    /**
     * A stable digest of an over-long key.
     *
     * <p>Deterministic across restarts and JVMs — SHA-256 of the UTF-8 bytes, hex — because a receipt
     * written yesterday has to be found again today. A truncation would be neither: two decisions that
     * shared a long prefix would silently become one operation.
     */
    private static String compact(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(DIGEST_PREFIX);
            for (byte b : hash) {
                hex.append(Character.forDigit((b >> 4) & 0xF, 16));
                hex.append(Character.forDigit(b & 0xF, 16));
            }
            return hex.toString();
        } catch (Exception e) {
            // Every JRE ships SHA-256; if this ever happens, a bounded prefix plus the key's length
            // and hash is still a stable identity rather than a colliding truncation.
            return DIGEST_PREFIX + key.length() + ":" + Integer.toHexString(key.hashCode());
        }
    }

    /** Whether a key was compacted, which a diagnostic line may want to say out loud. */
    public static boolean isCompacted(String key) {
        return key != null && key.startsWith(DIGEST_PREFIX);
    }
}
