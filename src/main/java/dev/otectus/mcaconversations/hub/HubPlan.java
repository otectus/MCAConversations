package dev.otectus.mcaconversations.hub;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The dynamic entries this hub is showing, this time (spec §14.2).
 *
 * <p>Three at most, and the cap is the interesting part. A hub that surfaced everything relevant
 * would be a list of everything the villager has going on, which is a character sheet rather than a
 * conversation — and §14.3 is explicit that identity is discovered by talking, not displayed. Three
 * entries above six categories is a suggestion; thirty would be a menu.
 *
 * <p>The plan also refuses to offer the same topic twice. Two entries opening the same conversation
 * under different words is the kind of menu that makes a player think the two buttons differ.
 */
public record HubPlan(List<HubSlot> slots) {

    public static final int MAX_SLOTS = 3;

    public static final HubPlan EMPTY = new HubPlan(List.of());

    public HubPlan {
        List<HubSlot> kept = new ArrayList<>(MAX_SLOTS);
        Set<String> topics = new LinkedHashSet<>();
        Set<HubSlot.Kind> kinds = new LinkedHashSet<>();
        if (slots != null) {
            for (HubSlot slot : slots) {
                if (slot == null || !slot.isWellFormed() || kept.size() >= MAX_SLOTS) {
                    continue;
                }
                // One entry per kind and one per topic: the three slots answer three different
                // questions, and offering the same conversation twice makes them look different.
                if (!kinds.add(slot.kind()) || !topics.add(slot.topic())) {
                    continue;
                }
                kept.add(slot);
            }
        }
        slots = List.copyOf(kept);
    }

    public boolean isEmpty() {
        return slots.isEmpty();
    }

    /** The answer names this hub should show, in slot order. */
    public List<String> answerNames() {
        List<String> names = new ArrayList<>(slots.size());
        for (HubSlot slot : slots) {
            names.add(slot.answerName());
        }
        return List.copyOf(names);
    }

    /** The slot behind one dynamic answer name, when this hub is showing it. */
    public Optional<HubSlot> forAnswer(String answerName) {
        if (answerName == null) {
            return Optional.empty();
        }
        String needle = answerName.trim().toLowerCase(java.util.Locale.ROOT);
        for (HubSlot slot : slots) {
            if (slot.answerName().equals(needle)) {
                return Optional.of(slot);
            }
        }
        return Optional.empty();
    }

    /** True when this hub is offering {@code answerName}; the visibility rule, in one call. */
    public boolean offers(String answerName) {
        return forAnswer(answerName).isPresent();
    }
}
