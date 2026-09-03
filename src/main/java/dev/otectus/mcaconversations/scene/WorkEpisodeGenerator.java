package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.EpisodeTemplate;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.NarrativeCatalogLoader;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.ModList;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Gives a villager something to actually be working on (spec §12.2).
 *
 * <p>Without this, an episode could only exist if a scene created one — and no scene could be
 * selected, because every scene that speaks about a situation requires one to already exist. This is
 * the bootstrap: on the first work interaction, if the villager has no live work episode, one is
 * opened from the templates their profession declares.
 *
 * <p>Four rules keep it from becoming a random-event generator:
 *
 * <ol>
 *   <li><b>Resume before create.</b> A villager with a live work episode gets that one back. Their
 *       working life is continuous, not re-rolled each morning.</li>
 *   <li><b>Templates decide what is possible.</b> Only kinds whose {@code professions} include this
 *       trade, and whose optional mods are present, are candidates. A librarian never acquires a
 *       farmer's blight.</li>
 *   <li><b>The choice is seeded, not random.</b> Villager plus day, so the same villager asked twice
 *       in one day is working on the same thing — and two librarians in one village are not.</li>
 *   <li><b>Nothing is claimed about the world.</b> The episode's payload is authored tokens from the
 *       template's own pools; it never asserts that materials were consumed or produced (spec §17.2).</li>
 * </ol>
 */
public final class WorkEpisodeGenerator {

    private WorkEpisodeGenerator() {
    }

    /**
     * Ensures this villager has a live work episode, opening one if they do not.
     *
     * @return the episode now in force, or empty when this profession has no templates
     */
    public static Optional<EpisodeRecord> ensure(Entity villager, String professionId, long today) {
        if (!History.episodesEnabled() || villager == null) {
            return Optional.empty();
        }
        try {
            List<EpisodeTemplate> candidates = candidatesFor(professionId);
            if (candidates.isEmpty()) {
                return Optional.empty();
            }
            // Resume before create: a live episode of any eligible kind is this villager's current work.
            for (EpisodeTemplate template : candidates) {
                Optional<EpisodeRecord> live = History.liveEpisode(villager, template.kind(), today);
                if (live.isPresent()) {
                    return live;
                }
            }
            EpisodeTemplate chosen = candidates.get(
                    (int) (seed(villager, today) % candidates.size()));
            return History.openEpisode(villager, chosen.kind(), Map.of(), today);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("work episode generation failed for '{}'; ignoring",
                    professionId, t);
            return Optional.empty();
        }
    }

    /**
     * Work episode kinds this profession may have, in a stable order.
     *
     * <p>Sorted by kind so the seeded index means the same thing on every server, and so adding a new
     * template later shifts the choice predictably rather than reshuffling every villager.
     */
    static List<EpisodeTemplate> candidatesFor(String professionId) {
        if (professionId == null || professionId.isBlank()) {
            return List.of();
        }
        String normalized = professionId.trim().toLowerCase(Locale.ROOT);
        List<EpisodeTemplate> out = new ArrayList<>();
        for (EpisodeTemplate template : NarrativeCatalogLoader.active().episodes()) {
            // A template with no declared professions is a shared affordance, not a work episode:
            // handing every trade every generic situation is how thirty-seven professions become one.
            if (template.professions().contains(normalized)
                    && template.isAvailable(normalized, WorkEpisodeGenerator::modPresent)) {
                out.add(template);
            }
        }
        out.sort(Comparator.comparing(EpisodeTemplate::kind));
        return List.copyOf(out);
    }

    /** Villager and day: the same villager asked twice today is working on the same thing. */
    private static long seed(Entity villager, long today) {
        long hash = 0xcbf29ce484222325L;
        for (byte b : (villager.getUUID() + "/" + today).getBytes(StandardCharsets.UTF_8)) {
            hash ^= (b & 0xffL);
            hash *= 0x100000001b3L;
        }
        return hash & Long.MAX_VALUE;
    }

    private static boolean modPresent(String modId) {
        if (modId == null || modId.isBlank()) {
            return true;
        }
        try {
            return ModList.get() != null && ModList.get().isLoaded(modId.trim().toLowerCase(Locale.ROOT));
        } catch (Throwable t) {
            return false;
        }
    }
}
