package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.village.VillageCulture;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Set;

/**
 * This mod's own village store, as a context source (spec §7.2, §17.3).
 *
 * <p>Separate from {@link McaContextSource} even though it needs MCA's village id, because the two
 * fail for different reasons and a report that lumped them together would be misleading. MCA being
 * absent costs you the whole village; the culture feature being switched off costs you six tokens and
 * nothing else.
 *
 * <p>A villager with no home village writes {@code unknown} rather than an empty set. The difference
 * matters to a scene: an empty set says "this village believes nothing", which is never true, while
 * unknown says "this person has no village", which is exactly what a wanderer is.
 */
public final class VillageContextSource implements ConversationContextSource {

    private static final String ID = "village";

    private static final List<ContextKey<?>> DECLARES = List.of(ContextKeys.VILLAGE_CULTURE);

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
        return VillageCulture.enabled() && request.villager() != null;
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        if (request.volatileOnly()) {
            // What a village believes about itself does not change inside one conversation.
            return;
        }
        Entity villager = request.villager();
        if (villager == null || !VillageCulture.enabled()) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.ABSENT,
                    villager == null ? "no villager" : "village culture disabled");
            return;
        }
        try {
            Set<String> tokens = VillageCulture.tokensOf(villager);
            if (tokens.isEmpty()) {
                builder.unknown(ContextKeys.VILLAGE_CULTURE);
                builder.reportCapability(ContextCapabilities.Status.DEGRADED,
                        "no home village, or no culture could be generated");
                return;
            }
            builder.put(ContextKeys.VILLAGE_CULTURE, tokens);
            builder.reportCapability(ContextCapabilities.Status.READY, "");
        } catch (Throwable t) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "village culture read failed");
        }
    }
}
