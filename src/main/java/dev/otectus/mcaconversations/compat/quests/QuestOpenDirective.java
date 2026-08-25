package dev.otectus.mcaconversations.compat.quests;

import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;

/**
 * Parsed args for the {@code conversations_quest_open} dialogue action:
 * {@code {"mode": "menu"}} (default) opens this villager's Quests menu; {@code {"mode": "accept",
 * "quest": "ns:path"}} accepts a specific quest directly.
 *
 * <p>No {@code dev.otectus.mcaquests.*} imports — safe to reference from the MCA-importing registrar.
 * Bad JSON throws so {@code SafeParse.orNull} degrades the action to a no-op.
 */
public record QuestOpenDirective(Mode mode, Optional<String> quest) {

    public enum Mode { MENU, ACCEPT }

    public static QuestOpenDirective fromJson(JsonObject json) {
        Mode mode = Mode.MENU;
        if (json.has("mode")) {
            String raw = json.get("mode").getAsString().toLowerCase(Locale.ROOT);
            mode = switch (raw) {
                case "menu" -> Mode.MENU;
                case "accept" -> Mode.ACCEPT;
                default -> throw new IllegalArgumentException("conversations_quest_open: unknown mode '" + raw + "'");
            };
        }
        Optional<String> quest = json.has("quest")
                ? Optional.of(json.get("quest").getAsString()) : Optional.empty();
        if (mode == Mode.ACCEPT && quest.isEmpty()) {
            throw new IllegalArgumentException("conversations_quest_open: mode 'accept' requires a 'quest' id");
        }
        return new QuestOpenDirective(mode, quest);
    }
}
