package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * How much more of this subject the villager is willing to have (spec §5.2).
 *
 * <p>This is the field that makes "so that's how the days go" stop appearing after "then we've
 * nothing to discuss. Good day." A response page may only be shared by beats that agree here:
 * pressing on is a reasonable button after {@link #INVITES_FOLLOWUP} and a rude one after
 * {@link #CLOSES_SUBJECT}, and no amount of result-level conditions can hide a button that MCA has
 * already decided to show (spec §3.1).
 */
public enum Openness {

    /** The line ends on a hook. Asking more is the natural thing to do. */
    INVITES_FOLLOWUP("invites_followup", 3),
    /** Complete in itself, but a further question would not be an intrusion. */
    PERMITS_FOLLOWUP("permits_followup", 2),
    /** They answered, but narrowly. Only a careful, specific question is welcome. */
    GUARDED("guarded", 1),
    /** This subject is finished. Another question about it is a boundary push. */
    CLOSES_SUBJECT("closes_subject", 0),
    /** The conversation itself is over. Only leaving follows. */
    ENDS_CONVERSATION("ends_conversation", -1);

    private final String key;
    private final int room;

    Openness(String key, int room) {
        this.key = key;
        this.room = room;
    }

    public String key() {
        return key;
    }

    /** How much further questioning this line supports; larger is more open. Comparison only. */
    public int room() {
        return room;
    }

    /** True when a further question about the same subject is welcome. */
    public boolean allowsProbing() {
        return room >= 2;
    }

    /** True when the subject is shut and only repair, respect or leaving may follow. */
    public boolean isClosed() {
        return room <= 0;
    }

    public static Optional<Openness> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Openness openness : values()) {
            if (openness.key.equals(normalized)) {
                return Optional.of(openness);
            }
        }
        return Optional.empty();
    }
}
