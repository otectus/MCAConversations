package dev.otectus.mcaconversations.client.dialogue;

/** Typed result for stable card hit testing; controls never masquerade as answer indices. */
public sealed interface DialogueHitTarget {
    record Choice(int absoluteIndex) implements DialogueHitTarget {}
    record PreviousPage() implements DialogueHitTarget {}
    record NextPage() implements DialogueHitTarget {}
    /** The question's own reading region. The wheel belongs to it while the pointer is inside. */
    record Question() implements DialogueHitTarget {}
    /** The response viewport, but not on a row: still the list's wheel, not the page's. */
    record Responses() implements DialogueHitTarget {}
    record None() implements DialogueHitTarget {}
}
