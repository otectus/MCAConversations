package dev.otectus.mcaconversations.compat;

import java.util.UUID;

/**
 * One capital's relation to another, from the first capital's point of view.
 *
 * <p>{@code state} is the Capitals diplomatic-state enum lower-cased ({@code peace},
 * {@code non_aggression_pact}, {@code alliance}, {@code truce}, {@code war}) and {@code band} is the
 * relationship band lower-cased. Both pass through rather than being re-bucketed here, so Capitals
 * stays authoritative over its own vocabulary.
 */
public record CapitalRelationView(UUID otherCapitalId, String otherName, String state, String band,
                                  int score) {

    public boolean atWar() {
        return "war".equals(state);
    }

    public boolean allied() {
        return "alliance".equals(state);
    }
}
