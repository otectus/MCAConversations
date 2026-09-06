package dev.otectus.mcaconversations.compat;

import java.util.Optional;
import java.util.UUID;

/**
 * One capital, flattened into types this mod owns.
 *
 * <p>Deliberately carries no Capitals object of any kind, not even opaquely: a view outlives the
 * read that produced it (the news poller keeps last poll's snapshot), and holding a live
 * {@code CapitalRecord} would both pin Capitals state and make the view unusable the moment the mod
 * is absent. Everything here is a JDK type, so it is safe to store, diff and log.
 *
 * <p>{@code state} is the Capitals capital-state enum lower-cased ({@code pending}, {@code founded},
 * {@code active}), or {@code unknown} when it could not be read.
 */
public record CapitalCourtView(UUID capitalId,
                               int villageId,
                               String dimensionId,
                               String name,
                               String state,
                               Optional<UUID> sovereign,
                               boolean sovereignFemale,
                               Optional<UUID> consort,
                               Optional<UUID> heir,
                               Optional<UUID> dowager,
                               Optional<UUID> hand,
                               Optional<UUID> commander,
                               Optional<UUID> herald,
                               Optional<UUID> grandMaester,
                               Optional<UUID> masterOfLaws,
                               boolean mourning,
                               boolean playerSovereign,
                               Optional<UUID> playerSovereignId,
                               String playerSovereignName,
                               int chronicleSize) {

    /** True when a successor is already named, which is what "the succession is settled" means. */
    public boolean heirNamed() {
        return heir.isPresent();
    }
}
