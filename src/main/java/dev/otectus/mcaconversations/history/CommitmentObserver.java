package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * The half of a promise that was missing: somebody actually watching.
 *
 * <p>Until 1.5.0 a promise could be made and recorded and then nothing in the running game ever looked
 * at it again. {@link CommitmentResolver} declared six ways a promise could be observed,
 * {@link History#promise} refused to create one whose resolver was unavailable, and
 * {@link History#settle} existed — but the only caller was the {@code conversations_commitment}
 * dialogue action, which means a promise was only ever settled by the player walking back and pressing
 * the button that said they had done it. "I can bring you wool" was, in practice, an honour system.
 *
 * <p>This class closes that. It is deliberately not a scheduled job over every stored promise:
 *
 * <ul>
 *   <li><b>A gift is an event</b>, so it is observed as one, on the existing accepted-gift path.</li>
 *   <li><b>Everything else is observed when the two of them next meet.</b> That is cheaper — no sweep
 *       over a save file, and the common case is a map lookup that finds nothing — but it is also the
 *       more truthful model. A villager does not form a view about a promise at midnight on the day it
 *       came due; they form it when they see you again. It also means a promise cannot be marked
 *       broken behind a player's back while they are logged out.</li>
 * </ul>
 *
 * <p>Nothing here ever judges a promise nothing watched. A {@code MANUAL_NEUTRAL} promise is settled as
 * {@link CommitmentRecord.State#NOTED} by the record itself whatever is asked, and a resolver this
 * install cannot observe is left outstanding rather than guessed at.
 */
public final class CommitmentObserver {

    /**
     * Days past due before an unmet promise is read as broken.
     *
     * <p>Not zero. A promise that came due while the player was asleep, or an hour into a mining trip,
     * has not been broken — it has been slightly late, which is a thing people forgive and villagers
     * should too. Three days is long enough that only genuine neglect reaches it.
     */
    public static final long BROKEN_AFTER_GRACE_DAYS = 3L;

    private CommitmentObserver() {
    }

    /**
     * Settles any promise this gift satisfies.
     *
     * <p>Called from the accepted-gift path, which already runs for every gift MCA accepts, so a
     * promised delivery is recognised at the moment it happens rather than the next time the subject
     * comes up.
     */
    public static List<CommitmentRecord> onGiftAccepted(Entity villager, ServerPlayer player,
                                                        ItemStack stack, long today) {
        if (villager == null || player == null || stack == null || stack.isEmpty()) {
            return List.of();
        }
        try {
            List<CommitmentRecord> settled = new ArrayList<>();
            for (CommitmentRecord commitment : outstanding(villager, player)) {
                if (commitment.resolver() == CommitmentResolver.GIFT_TAG_RECEIVED
                        && satisfies(commitment.target(), stack)) {
                    History.settle(villager, player, commitment.id(),
                                    CommitmentRecord.State.KEPT, today)
                            .ifPresent(settled::add);
                }
            }
            return List.copyOf(settled);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("gift commitment observation failed; nothing settled", t);
            return List.of();
        }
    }

    /**
     * Settles what meeting again settles, and forms the judgement that being met again allows.
     *
     * <p>Called when this player and this villager actually speak. Two things can happen:
     *
     * <ul>
     *   <li>a {@code visit_after_day} promise is kept by this very visit, if the day has come;</li>
     *   <li>a judgeable promise long past its day, that this visit has not kept, is read as broken.</li>
     * </ul>
     *
     * <p>Order matters between those two: the visit is credited before the deadline is judged, so
     * arriving on the last possible day keeps the promise instead of breaking it.
     *
     * @return every promise whose state changed, so a caller can speak to it
     */
    public static List<CommitmentRecord> onMet(Entity villager, ServerPlayer player, long today) {
        if (villager == null || player == null) {
            return List.of();
        }
        try {
            List<CommitmentRecord> changed = new ArrayList<>();
            for (CommitmentRecord commitment : outstanding(villager, player)) {
                CommitmentRecord.State outcome = outcomeOnMeeting(commitment, today);
                if (outcome != null) {
                    History.settle(villager, player, commitment.id(), outcome, today)
                            .ifPresent(changed::add);
                }
            }
            return List.copyOf(changed);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("commitment observation on meeting failed; nothing settled", t);
            return List.of();
        }
    }

    /**
     * What meeting today does to one outstanding promise, or null when it does nothing.
     *
     * <p>Pure, so the rule can be read and tested without a world.
     */
    static CommitmentRecord.State outcomeOnMeeting(CommitmentRecord commitment, long today) {
        if (commitment == null || !commitment.isOutstanding()) {
            return null;
        }
        if (!commitment.resolver().isAvailable()) {
            // The install lost the mod that could watch this. Leaving it outstanding is the only
            // honest option: it was neither kept nor broken, it stopped being observable.
            return null;
        }
        if (commitment.resolver() == CommitmentResolver.VISIT_AFTER_DAY && commitment.isDue(today)) {
            return CommitmentRecord.State.KEPT;
        }
        if (!commitment.resolver().isJudgeable() || commitment.dueDay().isEmpty()) {
            return null;
        }
        return today > commitment.dueDay().getAsLong() + BROKEN_AFTER_GRACE_DAYS
                ? CommitmentRecord.State.BROKEN
                : null;
    }

    /**
     * Whether this stack is the thing that was promised.
     *
     * <p>The resolver is named for tags and the shipped corpus all names concrete items, so both are
     * accepted: an exact item id first, then the same id read as an item tag. A datapack promising
     * {@code registry_id:forge:ingots/iron} therefore works without a second resolver.
     */
    static boolean satisfies(NarrativeValue target, ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        ResourceLocation wanted = promisedId(target);
        if (wanted == null) {
            return false;
        }
        if (wanted.equals(ForgeRegistries.ITEMS.getKey(stack.getItem()))) {
            return true;
        }
        try {
            return stack.is(TagKey.create(Registries.ITEM, wanted));
        } catch (Throwable t) {
            // An id that is not a live tag is simply not a match; a promise is never settled by
            // guessing, and a malformed target must not take the gift path down with it.
            McaConversations.LOGGER.debug("commitment target '{}' is neither an item nor a tag",
                    target.raw(), t);
            return false;
        }
    }

    /**
     * The item id a promise names, or null when it does not name one.
     *
     * <p>Pure, and separated out because it is the half of {@link #satisfies} that decides whether a
     * promise is even about an item — a token, a day, an empty value or a malformed id all mean "this
     * gift cannot settle this promise", and that is worth being able to check without a live registry.
     */
    static ResourceLocation promisedId(NarrativeValue target) {
        if (target == null || target.isEmpty() || target.kind() != NarrativeValue.Kind.REGISTRY_ID) {
            return null;
        }
        return ResourceLocation.tryParse(target.raw());
    }

    /** Outstanding promises on this pair, or nothing at all when history is off or unreadable. */
    private static List<CommitmentRecord> outstanding(Entity villager, ServerPlayer player) {
        return History.pair(villager, player)
                .map(PairHistory::commitments)
                .orElse(List.of())
                .stream()
                .filter(CommitmentRecord::isOutstanding)
                .toList();
    }
}
