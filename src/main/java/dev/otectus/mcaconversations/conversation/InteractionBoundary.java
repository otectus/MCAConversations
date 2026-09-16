package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ConversationMovementController;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.network.ConversationRef;
import dev.otectus.mcaconversations.network.ConversationsNetwork;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

import java.util.Optional;
import java.util.UUID;

/**
 * Where a graphical discussion is accepted (spec §4.1).
 *
 * <p>A GUI offer used to be recorded with a {@code null} villager, because the packet that carries
 * it names only the player. Everything downstream then had to guess: the client guessed from
 * whichever screen was open, and the server guessed from whatever UUID the client sent back. This
 * class removes the guess by asking the only authority there is — MCA's own record of who is
 * interacting with whom — and minting the handle from that.
 *
 * <p><b>Authoritative, never client-supplied.</b> The villager is resolved from
 * {@link McaCompat#isInteractingWith(Entity)}, which is MCA's own interacting-player reference and
 * is set when MCA accepts the interaction and opens the screen. A UUID from a packet is a claim; this
 * is the server's own answer.
 *
 * <p><b>Cheap after the first offer.</b> The scan below runs only while the player has no registered
 * discussion. Once one exists, the villager comes out of the presence index and is confirmed with a
 * single entity lookup, so a long conversation costs one map read per offer rather than an area
 * query.
 */
public final class InteractionBoundary {

    /**
     * How far around the player to look for the villager MCA says they are interacting with.
     *
     * <p>Deliberately generous and deliberately not a policy: it is the radius of a <em>search</em>,
     * not a rule about how far apart a conversation may be held. The distance that decides whether an
     * exchange may continue lives with the engagement checks, and widening this constant can never
     * make a far-away conversation legal — only make a legal one findable.
     */
    private static final double SEARCH_RADIUS = 24.0D;

    private InteractionBoundary() {
    }

    /**
     * Accepts (or confirms) the graphical discussion this player is in, and names its villager.
     *
     * <p>Called from the outgoing dialogue-packet hook, which is the earliest server-side moment this
     * mod can see that MCA has accepted an interaction <em>and</em> is about to put something on the
     * player's screen. Minting here rather than at the raw right-click is deliberate: an interaction
     * another mod cancels, an item consumes, or MCA answers with a trade screen never produces a
     * dialogue packet, and so never mints a conversation that would immediately have to be torn down.
     *
     * @return the handle now registered for this player, or empty when MCA reports no interaction
     */
    public static Optional<ConversationHandle> beginGui(ServerPlayer player) {
        if (player == null || player.hasDisconnected()) {
            return Optional.empty();
        }
        try {
            Entity villager = resolveInteracting(player).orElse(null);
            if (villager == null) {
                return Optional.empty();
            }
            ConversationHandle before = ConversationPresence.ofPlayer(player.getUUID()).orElse(null);
            Optional<ConversationHandle> accepted = ConversationLifecycle.begin(player.getUUID(),
                    villager.getUUID(), dimensionOf(player), ConversationSession.Frontend.GUI,
                    player.level().getGameTime());
            accepted.filter(handle -> !handle.equals(before))
                    .ifPresent(handle -> ConversationsNetwork.sendOpened(player,
                            ConversationRef.of(handle), handle.frontend()));
            // Spec §5.2 step 1: stop where you are the moment the discussion is accepted, rather than
            // taking one more step while waiting for the next lifecycle tick to notice.
            accepted.ifPresent(handle -> ConversationMovementController.onAccepted(handle, villager,
                    player, player.level().getGameTime()));
            return accepted;
        } catch (Throwable t) {
            // The conversation still works without a handle; it simply keeps the pre-1.7.1 behaviour
            // for this offer. Never let identity bookkeeping break dialogue delivery.
            McaConversations.LOGGER.debug("could not establish the interaction boundary", t);
            return Optional.empty();
        }
    }

    /** The plain registry name of the level this player is in, for example {@code minecraft:overworld}. */
    public static String dimensionOf(ServerPlayer player) {
        return player.level().dimension().location().toString();
    }

    /**
     * The villager MCA says is interacting with this player, confirmed against MCA every time.
     *
     * <p>The registered discussion is a hint, not proof: MCA can end its own interaction without
     * telling anybody — a trade screen, a chore assignment, its own close request — and a handle that
     * outlived that must not keep naming a villager who has moved on.
     */
    public static Optional<Entity> resolveInteracting(ServerPlayer player) {
        UUID known = ConversationPresence.ofPlayer(player.getUUID())
                .map(ConversationHandle::villagerId).orElse(null);
        if (known != null) {
            Entity remembered = player.serverLevel().getEntity(known);
            if (owns(remembered, player)) {
                return Optional.of(remembered);
            }
        }
        AABB box = player.getBoundingBox().inflate(SEARCH_RADIUS);
        for (Entity candidate : player.level().getEntities(player, box, McaCompat::isMcaVillager)) {
            if (owns(candidate, player)) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }

    private static boolean owns(Entity villager, ServerPlayer player) {
        return villager != null && villager.isAlive()
                && McaCompat.isInteractingWith(villager).filter(player.getUUID()::equals).isPresent();
    }
}
