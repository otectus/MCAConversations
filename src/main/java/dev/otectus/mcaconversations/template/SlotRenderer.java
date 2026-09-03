package dev.otectus.mcaconversations.template;

import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.history.NarrativeValue;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;

import java.util.Optional;
import java.util.UUID;

/**
 * Turns a bound scene slot into a {@link Component} the locale line can drop into a sentence
 * (spec §18.5).
 *
 * <p>The rule the whole design rests on: <b>a slot supplies a noun phrase, and the surrounding
 * grammar lives in the locale line.</b> Nothing here concatenates translated fragments, and nothing
 * builds a mini-grammar. Every token resolves to one complete lang key, so Brazilian Portuguese can
 * write "o livro de registros" with its own article and gender rather than being handed an English
 * bare noun to inflect.
 *
 * <p>The corollary, and it constrains the writing rather than the code: a line that uses a slot must
 * not put an agreeing adjective next to it. "The %2$s is damp" cannot be translated correctly for
 * every possible slot; "%2$s came back from the damp" can, because nothing in the sentence agrees
 * with the noun.
 *
 * <p>Nothing here can throw. An unrenderable slot becomes a neutral fallback — a line must always
 * render, and a blank in the middle of a sentence is worse than a vaguer word.
 */
public final class SlotRenderer {

    /** The lang-key namespace every token slot renders through. */
    public static final String TOKEN_PREFIX = "mcaconversations.slot.";

    /** The neutral phrase used when a slot cannot be rendered at all. */
    public static final String FALLBACK_KEY = "mcaconversations.fallback.something";

    private SlotRenderer() {
    }

    /**
     * Renders one bound value.
     *
     * @param level the level, needed to resolve a person slot's current name
     */
    public static Component render(NarrativeValue value, ServerLevel level) {
        if (value == null || value.isEmpty()) {
            return Component.translatable(FALLBACK_KEY);
        }
        try {
            return switch (value.kind()) {
                case TOKEN, ENUM_TOKEN -> Component.translatable(TOKEN_PREFIX + value.raw());
                case REGISTRY_ID -> registryName(value.raw());
                case UUID_REF -> personName(value, level);
                case BAND -> Component.translatable(TOKEN_PREFIX + "band."
                        + bandToken(value.asBand().orElse(0)));
                case DAY -> Component.literal(Long.toString(value.asDay().orElse(0L)));
                case FLAG -> Component.translatable(TOKEN_PREFIX + (value.asFlag() ? "yes" : "no"));
            };
        } catch (Throwable t) {
            return Component.translatable(FALLBACK_KEY);
        }
    }

    /**
     * A registry id as the game's own display name.
     *
     * <p>Items only, and deliberately: an item has a translated name every locale already ships, which
     * is the one case where borrowing a name is better than authoring one. Anything else falls back
     * rather than printing a raw id into a sentence.
     */
    private static Component registryName(String id) {
        ResourceLocation location = ResourceLocation.tryParse(id);
        if (location == null) {
            return Component.translatable(FALLBACK_KEY);
        }
        Optional<Item> item = BuiltInRegistries.ITEM.getOptional(location);
        return item.<Component>map(value -> Component.translatable(value.getDescriptionId()))
                .orElseGet(() -> Component.translatable(TOKEN_PREFIX + location.getPath()));
    }

    /**
     * A person slot as their current name.
     *
     * <p>Read fresh from MCA's family tree at render time rather than cached, so a name that changed
     * is spoken correctly — and a person who has died or vanished renders as the neutral fallback
     * rather than as a ghost the villager keeps naming (spec §2.4).
     */
    private static Component personName(NarrativeValue value, ServerLevel level) {
        Optional<UUID> uuid = value.asUuid();
        if (uuid.isEmpty() || level == null || McaCompat.isDeceased(level, uuid.get())) {
            return Component.translatable(FALLBACK_KEY);
        }
        return McaCompat.familyTreeName(level, uuid.get())
                .filter(name -> !name.isBlank())
                .<Component>map(Component::literal)
                .orElseGet(() -> Component.translatable(FALLBACK_KEY));
    }

    /**
     * A coarse band, never a number.
     *
     * <p>"A few" and "most" are things a villager says; "seven" is an economic claim the mod has no
     * business making unless something actually counted (spec §12.2).
     */
    private static String bandToken(int value) {
        if (value <= 0) {
            return "none";
        }
        if (value == 1) {
            return "one";
        }
        if (value <= 3) {
            return "a_few";
        }
        return value <= 8 ? "several" : "many";
    }
}
