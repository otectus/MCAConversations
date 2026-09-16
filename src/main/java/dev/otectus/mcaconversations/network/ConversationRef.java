package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationHandle;
import net.minecraft.network.FriendlyByteBuf;

import java.util.UUID;

/**
 * The wire projection of a {@link ConversationHandle}: which discussion a packet belongs to.
 *
 * <p>Protocol 3 had revisions but no identity, so a packet could only ever say "the offer numbered
 * 41" — and the server had no way to tell the player's current discussion from the one they closed a
 * second ago. Every packet on protocol 4 carries one of these instead, and the server decides what
 * to do with it through {@code HandleAuthority}: a straggler naming a retired session is refused,
 * never obeyed.
 *
 * <p>Only two of the handle's six components travel. The session UUID alone already identifies a
 * discussion uniquely — it is minted fresh per accepted interaction — and the villager rides along
 * because it is what the client's screen is actually looking at, which lets a mismatch be caught
 * before anything is resolved. The epoch, dimension and frontend stay server-side: the server holds
 * the full handle and compares it itself. The player UUID is deliberately <em>never</em> on the wire
 * — the sender comes from the network context, never from the packet (spec §7).
 *
 * <p>Both components are optional, and {@link #NONE} is the honest value for a packet that belongs
 * to no managed discussion: chat mode's ambient exchanges, and anything from a frontend that never
 * acquired a handle. An unidentified packet is not rejected; it simply falls through to the checks
 * that governed it before handles existed.
 *
 * @param sessionId  the discussion's identity, or null when the sender names none
 * @param villagerId the villager that discussion owns, or null when the sender names none
 */
public record ConversationRef(UUID sessionId, UUID villagerId) {

    /** A packet that belongs to no managed discussion. Two bytes on the wire. */
    public static final ConversationRef NONE = new ConversationRef(null, null);

    /** The wire form of a live handle, or {@link #NONE} when there is no handle. */
    public static ConversationRef of(ConversationHandle handle) {
        return handle == null ? NONE : new ConversationRef(handle.sessionId(), handle.villagerId());
    }

    /** A ref that names only the villager — what a frontend has before the server answers with a handle. */
    public static ConversationRef ofVillager(UUID villagerId) {
        return villagerId == null ? NONE : new ConversationRef(null, villagerId);
    }

    /** True when this ref names a discussion, as opposed to merely naming a villager or nothing. */
    public boolean identified() {
        return sessionId != null;
    }

    /** True when this ref names exactly the discussion {@code handle} is. */
    public boolean matches(ConversationHandle handle) {
        return handle != null && handle.sessionId().equals(sessionId)
                && (villagerId == null || handle.villagerId().equals(villagerId));
    }

    /** Same ref, with the villager filled in when the sender left it out. */
    public ConversationRef withVillager(UUID fallback) {
        return villagerId != null || fallback == null ? this : new ConversationRef(sessionId, fallback);
    }

    /**
     * {@code present?uuid} per component, so the common unidentified case costs two bytes and a
     * missing villager is distinguishable from a zero UUID.
     */
    public static void write(FriendlyByteBuf buffer, ConversationRef ref) {
        ConversationRef safe = ref == null ? NONE : ref;
        writeOptionalUuid(buffer, safe.sessionId());
        writeOptionalUuid(buffer, safe.villagerId());
    }

    public static ConversationRef read(FriendlyByteBuf buffer) {
        UUID session = readOptionalUuid(buffer);
        UUID villager = readOptionalUuid(buffer);
        return session == null && villager == null ? NONE : new ConversationRef(session, villager);
    }

    static void writeOptionalUuid(FriendlyByteBuf buffer, UUID id) {
        buffer.writeBoolean(id != null);
        if (id != null) {
            buffer.writeUUID(id);
        }
    }

    static UUID readOptionalUuid(FriendlyByteBuf buffer) {
        return buffer.readBoolean() ? buffer.readUUID() : null;
    }

    @Override
    public String toString() {
        return "ref[" + brief(sessionId) + "->" + brief(villagerId) + ']';
    }

    private static String brief(UUID id) {
        return id == null ? "-" : id.toString().substring(0, 8);
    }
}
