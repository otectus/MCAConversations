package dev.otectus.mcaconversations.compat;

/**
 * One entry of a capital's chronicle, decoded.
 *
 * <p>{@code index} is the entry's <em>absolute</em> position in the capital's chronicle list, which
 * is what the news poller stores as its cursor; a view without it could not be resumed after a
 * restart. {@code type} is the coarse Capitals event-type enum lower-cased, or {@code legacy} for an
 * old plain-text entry that carries no semantic type at all.
 *
 * <p>{@code text} is rendered in the <em>server</em> locale. Capitals renders through a
 * {@code Component}, and the poller stores the result rather than the key, because a gossip event
 * has one text field and no argument list to re-render from.
 */
public record CapitalChronicleEventView(int index, long day, String type, String translationKey,
                                        String text) {
}
