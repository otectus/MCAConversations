package dev.otectus.mcaconversations.compat;

import java.util.List;

/**
 * A Townstead root, the species/ancestry/lineage a villager descends from (Townstead spec 5.2).
 *
 * <p>{@link #effectiveSpecies()} is the resolved species after inheritance, which is what content
 * should gate on; {@link #species()} is what this root declares directly and may be empty for a root
 * that inherits one.
 */
public record TownsteadRootView(
        String id,
        String displayName,
        String species,
        String ancestry,
        String lineage,
        String effectiveSpecies,
        List<String> defaultGenes,
        List<TownsteadLifeStageView> lifeStages) {

    public static final TownsteadRootView EMPTY =
            new TownsteadRootView("", "", "", "", "", "", List.of(), List.of());

    public TownsteadRootView {
        defaultGenes = List.copyOf(defaultGenes);
        lifeStages = List.copyOf(lifeStages);
    }

    /** True when Townstead gave us nothing about this root. */
    public boolean isEmpty() {
        return id.isEmpty();
    }
}
