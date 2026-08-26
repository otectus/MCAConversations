package dev.otectus.mcaconversations.compat;

import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * A villager's personality as Townstead sees it (Townstead spec 5.2, 10).
 *
 * <p>{@link #id()} keeps its namespace. That is the whole point of this record: two datapacks may
 * each define a {@code reserved_scholar}, and collapsing them to a bare path, which is what
 * {@code Personalities.normalize} does for MCA's own roster, would give both the same interiority
 * profile and the same voice.
 *
 * <p>{@link #baseId()} is the MCA personality a custom definition declares itself a variety of,
 * which is what supplies the spoken voice when a pack has not authored an exact profile.
 */
public record TownsteadPersonalityView(
        String id,
        boolean custom,
        String baseId,
        @Nullable Component displayName,
        @Nullable Component description) {

    public static final TownsteadPersonalityView EMPTY =
            new TownsteadPersonalityView("", false, "", null, null);

    public Optional<Component> displayNameOpt() {
        return Optional.ofNullable(displayName);
    }

    public Optional<Component> descriptionOpt() {
        return Optional.ofNullable(description);
    }

    /** True when Townstead gave us nothing to say about this villager's personality. */
    public boolean isEmpty() {
        return id.isEmpty();
    }
}
