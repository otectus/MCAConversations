package dev.otectus.mcaconversations.client.townstead;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.client.ClientUiResourceGeneration;
import dev.otectus.mcaconversations.compat.Townstead;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Conversations' emotion tags for Townstead's RPG typewriter (Townstead spec §17.3). Client only.
 *
 * <p>Townstead's {@code EmotionTagOverrides} scans a fixed list of MCA namespaces for lang values
 * carrying tags like {@code <sleepy>…</sleepy>}. Conversations' lines live elsewhere, and raw tags in
 * ordinary lang values would leak into chat mode, system chat, text-to-speech and MCA's own screen.
 * So the tagged versions live in a sidecar, {@code assets/mcaconversations/townstead_emotions/<locale>.json},
 * which only this index reads and only the guarded mixin on Townstead's class consults.
 *
 * <p>Lookup by key is exact. Lookup by resolved text answers only when exactly one sidecar line
 * strips to that text; a collision answers nothing rather than guessing. The index is rebuilt when
 * the language or the resource packs change.
 */
public final class ConversationsEmotionTags {

    /** Townstead's own strip rule, so "the same text" means the same thing on both sides. */
    private static final Pattern STRIP = Pattern.compile("<\\w+>(.*?)</\\w+>", Pattern.DOTALL);

    /** Key to tagged line, and plain text to tagged line where the plain text is unambiguous. */
    record Index(Map<String, String> byKey, Map<String, String> byPlain) {
        static final Index EMPTY = new Index(Map.of(), Map.of());
    }

    private static Index index = Index.EMPTY;
    private static String indexedLocale = "";
    private static int indexedGeneration = -1;

    private ConversationsEmotionTags() {
    }

    /** The tagged line for a translation key, or null. */
    public static String byKey(String key) {
        return key == null ? null : current().byKey().get(key);
    }

    /** The tagged line whose plain text is exactly this, or null (none, or more than one). */
    public static String byResolvedText(String plain) {
        return plain == null ? null : current().byPlain().get(plain);
    }

    private static Index current() {
        if (!Townstead.emotionEffectsEnabled()) {
            return Index.EMPTY;
        }
        try {
            Minecraft minecraft = Minecraft.getInstance();
            String locale = minecraft.getLanguageManager().getSelected();
            int generation = ClientUiResourceGeneration.current();
            if (!locale.equals(indexedLocale) || generation != indexedGeneration) {
                index = load(minecraft, locale);
                indexedLocale = locale;
                indexedGeneration = generation;
            }
            return index;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead emotion sidecar unavailable; clean text only", t);
            return Index.EMPTY;
        }
    }

    private static Index load(Minecraft minecraft, String locale) {
        Map<String, String> tagged = new HashMap<>();
        // English underneath, the selected locale over it: a missing translation falls back like lang does.
        for (String code : "en_us".equals(locale) ? List.of("en_us") : List.of("en_us", locale)) {
            ResourceLocation file = ResourceLocation.tryParse(McaConversations.MOD_ID + ":townstead_emotions/" + code + ".json");
            if (file == null) {
                continue;
            }
            for (Resource resource : minecraft.getResourceManager().getResourceStack(file)) {
                try (Reader reader = resource.openAsReader()) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                        if (!entry.getKey().startsWith("_") && entry.getValue().isJsonPrimitive()) {
                            tagged.put(entry.getKey(), entry.getValue().getAsString());
                        }
                    }
                } catch (Exception e) {
                    McaConversations.LOGGER.debug("unreadable Townstead emotion sidecar {}", file, e);
                }
            }
        }
        return build(tagged);
    }

    /** Pure: the two lookups for a set of tagged lines. */
    static Index build(Map<String, String> tagged) {
        Map<String, String> byPlain = new HashMap<>();
        Set<String> collided = new HashSet<>();
        for (String line : tagged.values()) {
            String plain = strip(line);
            if (plain.equals(line)) {
                continue;
            }
            String previous = byPlain.putIfAbsent(plain, line);
            if (previous != null && !previous.equals(line)) {
                collided.add(plain);
            }
        }
        collided.forEach(byPlain::remove);
        return new Index(Map.copyOf(tagged), Map.copyOf(byPlain));
    }

    /** The visible text of a tagged line. */
    static String strip(String tagged) {
        String result = tagged;
        while (STRIP.matcher(result).find()) {
            result = STRIP.matcher(result).replaceAll("$1");
        }
        return result;
    }
}
