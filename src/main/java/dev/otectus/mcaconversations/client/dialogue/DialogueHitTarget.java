package dev.otectus.mcaconversations.client.dialogue;

/** Typed result for stable card hit testing; controls never masquerade as answer indices. */
public sealed interface DialogueHitTarget {
    record Choice(int absoluteIndex) implements DialogueHitTarget {}
    record PreviousPage() implements DialogueHitTarget {}
    record NextPage() implements DialogueHitTarget {}
    record None() implements DialogueHitTarget {}
}
