package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.CrimeBridge;
import dev.otectus.mcaconversations.compat.McaCompat;

import java.util.List;
import java.util.Optional;

/**
 * The player's live legal standing under MCA: Crime, as this villager would know it (1.8.0).
 *
 * <p>Everything here comes through {@link CrimeBridge}, so no Crime type is named and an install
 * without the mod sees exactly one difference: this source's capability line. Every field it declares
 * reads {@code UNAVAILABLE} then, which is what makes a crime-gated scene unselectable rather than
 * selectable-on-a-false.
 *
 * <p><b>Coarse, and only what a villager could know.</b> A warrant is public (the posters are up), a
 * band is how the village treats the player, and custody is visible. Heat itself is a number nobody in
 * the village reads, so it is not a field; {@code conversations_crime_heat} exists for authored
 * thresholds. Whether the speaker is the law is here because a guard's lines and a farmer's differ.
 */
public final class CrimeContextSource implements ConversationContextSource {

    public static final String ID = "crime";

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.CRIME_WANTED,
            ContextKeys.CRIME_BAND,
            ContextKeys.CRIME_JAILED,
            ContextKeys.CRIME_SPEAKER_IS_LAW);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<ContextKey<?>> declares() {
        return DECLARES;
    }

    @Override
    public boolean isAvailable(ContextRequest request) {
        return request.isComplete()
                && McaCompat.isMcaVillager(request.villager())
                && CrimeBridge.isAvailable();
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        if (!request.isComplete() || !McaCompat.isMcaVillager(request.villager())) {
            markUnavailable(builder, "no MCA villager and player pair");
            return;
        }
        CrimeBridge.CrimeQueries q = CrimeBridge.queries();
        if (q == null) {
            markUnavailable(builder, "MCA: Crime is not installed");
            return;
        }
        try {
            builder.put(ContextKeys.CRIME_WANTED, q.isWanted(request.player()));
            Optional<String> band = q.band(request.player());
            if (band.isPresent()) {
                builder.put(ContextKeys.CRIME_BAND, band.get());
            } else {
                builder.unknown(ContextKeys.CRIME_BAND);
            }
            builder.put(ContextKeys.CRIME_JAILED, q.isJailed(request.player()));
            builder.put(ContextKeys.CRIME_SPEAKER_IS_LAW, CrimeBridge.isLaw(request.villager()));
            builder.reportCapability(ContextCapabilities.Status.READY, "");
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("crime context unavailable; those fields go dark", t);
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "crime read failed");
        }
    }

    private static void markUnavailable(ContextSnapshotBuilder builder, String reason) {
        builder.allUnavailable(DECLARES);
        builder.reportCapability(ContextCapabilities.Status.ABSENT, reason);
    }
}
