package dev.otectus.mcaconversations.court;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.CapitalChronicleEventView;
import dev.otectus.mcaconversations.compat.CapitalCourtView;
import dev.otectus.mcaconversations.compat.CapitalRelationView;
import dev.otectus.mcaconversations.compat.CapitalsBridge;
import dev.otectus.mcaconversations.compat.CapitalsCompat;
import dev.otectus.mcaconversations.gossip.GossipEvent;
import dev.otectus.mcaconversations.gossip.GossipEventType;
import dev.otectus.mcaconversations.gossip.GossipSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Turns a capital's chronicle into something a villager will actually say.
 *
 * <p>MCA Capitals writes its history to a chronicle and nowhere else: there is no event, no callback
 * and no hook to subscribe to, so the only way to learn that a king died is to notice that the list
 * got longer. That is what this does, on its own slow cadence — a chronicle changes far less often
 * than a village's marriages do, so it deliberately does not ride the gossip sweep.
 *
 * <h2>Two sources, one deduplicated result</h2>
 *
 * <p>The chronicle is the richer source (it carries the herald's own wording) but not the more
 * reliable one, because Capitals does not chronicle everything it records. So a second pass diffs the
 * court against the snapshot from last poll: a changed sovereign, a newly named heir, mourning
 * beginning, a relation entering or leaving war. Where both passes see the same thing — the usual
 * case for a coronation — the chronicle's version wins and the diff stays quiet, which is what the
 * per-poll {@code emitted} set is for.
 *
 * <h2>Why the work is split</h2>
 *
 * <p>{@link #poll} takes a reading and a memory and produces gossip; it holds no server, no level and
 * no Capitals type, so every rule above is testable with a list and a fake source. {@link #tick} is
 * the glue that fetches the reading from the bridge and writes the result to world storage, and it
 * swallows everything: this runs on the server tick, and a hiccup inside an optional mod must cost a
 * poll, never the tick.
 */
public final class CourtNewsPoller {

    /** Fixed title ids for the offices a court view names, used for the change memory. */
    private static final String TITLE_SOVEREIGN = "sovereign";
    private static final String TITLE_CONSORT = "sovereign_consort";
    private static final String TITLE_HEIR = "heir_apparent";
    private static final String TITLE_HAND = "hand";
    private static final String TITLE_COMMANDER = "lord_commander";
    private static final String TITLE_HERALD = "court_herald";
    private static final String TITLE_GRAND_MAESTER = "grand_maester";
    private static final String TITLE_MASTER_OF_LAWS = "master_of_laws";

    private CourtNewsPoller() {
    }

    /**
     * Everything one poll needs to read about a capital, behind three calls it can be given fakes for.
     *
     * <p>The chronicle is fetched rather than passed because the range to fetch depends on the stored
     * cursor, which only {@link #poll} knows.
     */
    public interface CourtSource {

        List<CapitalChronicleEventView> chronicleSince(CapitalCourtView court, int fromIndex, int max);

        List<CapitalRelationView> relationsOf(CapitalCourtView court);

        /** A name for a villager who may not be loaded; never {@code null}, empty when unknown. */
        String displayName(CapitalCourtView court, UUID entity);
    }

    /**
     * What one poll did.
     *
     * @param emitted       how many gossip events were produced
     * @param memoryChanged whether the court memory needs saving
     */
    public record PollResult(int emitted, boolean memoryChanged) {
    }

    // --- The rule ------------------------------------------------------------------------------

    /**
     * Reads every court once and hands each piece of news to {@code sink}.
     *
     * @param courts     the capitals to read, in any order
     * @param source     where chronicle entries, relations and names come from
     * @param memory     the cursors and snapshots from last poll; updated in place
     * @param now        game time, stored on each event
     * @param maxPerPoll how many chronicle entries one capital may contribute this poll
     */
    public static PollResult poll(List<CapitalCourtView> courts, CourtSource source,
                                  CourtRoleMemory memory, long now, int maxPerPoll,
                                  Consumer<GossipEvent> sink) {
        if (courts == null || courts.isEmpty() || source == null || memory == null) {
            return new PollResult(0, false);
        }
        int emitted = 0;
        boolean changed = false;
        long today = now / 24_000L;
        for (CapitalCourtView court : courts) {
            if (court == null || court.capitalId() == null) {
                continue;
            }
            int seenBefore = memory.chronicleSeen(court.capitalId());
            boolean firstSight = seenBefore < 0;
            Set<GossipEventType> told = EnumSet.noneOf(GossipEventType.class);

            ChronicleCursor.Range range =
                    ChronicleCursor.advance(seenBefore, court.chronicleSize(), maxPerPoll);
            if (!range.isEmpty()) {
                for (CapitalChronicleEventView entry
                        : source.chronicleSince(court, range.from(), range.count())) {
                    if (entry == null) {
                        continue;
                    }
                    GossipEventType type =
                            ChronicleEventMapper.map(entry.type(), entry.translationKey());
                    told.add(type);
                    sink.accept(event(type, court, now, court.sovereign(), entry.text()));
                    emitted++;
                }
            }
            changed |= memory.setChronicleSeen(court.capitalId(), range.newCursor());

            // Read once: the diff and the snapshot it becomes must agree, and Capitals should be
            // asked for a capital's relations exactly once per poll.
            List<CapitalRelationView> relations = source.relationsOf(court);
            emitted += diff(court, source, memory, now, firstSight, told, relations, sink);
            changed |= memory.setSnapshot(court.capitalId(), snapshotOf(court, relations));
            changed |= observeOffices(court, memory, today);
        }
        return new PollResult(emitted, changed);
    }

    /**
     * The second pass: what changed about the court itself since last poll.
     *
     * <p>A first sighting emits nothing at all. Every capital on a server that has just installed the
     * integration has a sovereign, an heir and a war or two, and none of it is news.
     */
    private static int diff(CapitalCourtView court, CourtSource source, CourtRoleMemory memory,
                            long now, boolean firstSight, Set<GossipEventType> told,
                            List<CapitalRelationView> relations, Consumer<GossipEvent> sink) {
        if (firstSight) {
            return 0;
        }
        CourtRoleMemory.CapitalSnapshot before = memory.snapshot(court.capitalId());
        int emitted = 0;

        if (court.sovereign().isPresent() && !court.sovereign().equals(before.sovereign())
                && told.add(GossipEventType.CORONATION)) {
            sink.accept(event(GossipEventType.CORONATION, court, now, court.sovereign(),
                    source.displayName(court, court.sovereign().get())));
            emitted++;
        }
        if (court.heir().isPresent() && before.heir().isEmpty()
                && told.add(GossipEventType.ROYAL_BIRTH)) {
            sink.accept(event(GossipEventType.ROYAL_BIRTH, court, now, court.heir(),
                    source.displayName(court, court.heir().get())));
            emitted++;
        }
        if (court.mourning() && !before.mourning() && told.add(GossipEventType.ROYAL_DEATH)) {
            String who = before.sovereign().map(uuid -> source.displayName(court, uuid)).orElse("");
            sink.accept(event(GossipEventType.ROYAL_DEATH, court, now, before.sovereign(), who));
            emitted++;
        }

        for (CapitalRelationView relation : relations) {
            if (relation == null || relation.otherCapitalId() == null) {
                continue;
            }
            String was = before.relations().getOrDefault(relation.otherCapitalId(), "");
            boolean wasWar = "war".equals(was);
            if (relation.atWar() && !wasWar && told.add(GossipEventType.WAR)) {
                sink.accept(event(GossipEventType.WAR, court, now, court.sovereign(),
                        relation.otherName()));
                emitted++;
            } else if (!relation.atWar() && wasWar && told.add(GossipEventType.PEACE)) {
                sink.accept(event(GossipEventType.PEACE, court, now, court.sovereign(),
                        relation.otherName()));
                emitted++;
            }
            if (relation.allied() && !"alliance".equals(was) && told.add(GossipEventType.ALLIANCE)) {
                sink.accept(event(GossipEventType.ALLIANCE, court, now, court.sovereign(),
                        relation.otherName()));
                emitted++;
            }
        }
        return emitted;
    }

    /**
     * Remembers who holds each office, so "I was only just given it" has something to stand on.
     *
     * <p>Eight named holders from the view the poll already has — never a resident scan, which on a
     * large capital would be the most expensive thing this mod does and would learn nothing extra.
     */
    private static boolean observeOffices(CapitalCourtView court, CourtRoleMemory memory, long today) {
        boolean changed = false;
        changed |= observe(memory, court.sovereign(), TITLE_SOVEREIGN, today);
        changed |= observe(memory, court.consort(), TITLE_CONSORT, today);
        changed |= observe(memory, court.heir(), TITLE_HEIR, today);
        changed |= observe(memory, court.hand(), TITLE_HAND, today);
        changed |= observe(memory, court.commander(), TITLE_COMMANDER, today);
        changed |= observe(memory, court.herald(), TITLE_HERALD, today);
        changed |= observe(memory, court.grandMaester(), TITLE_GRAND_MAESTER, today);
        changed |= observe(memory, court.masterOfLaws(), TITLE_MASTER_OF_LAWS, today);
        return changed;
    }

    private static boolean observe(CourtRoleMemory memory, Optional<UUID> holder, String titleId,
                                   long today) {
        return holder.isPresent() && memory.observe(holder.get(), titleId, today);
    }

    private static CourtRoleMemory.CapitalSnapshot snapshotOf(CapitalCourtView court,
                                                              List<CapitalRelationView> relations) {
        Map<UUID, String> states = new LinkedHashMap<>();
        for (CapitalRelationView relation : relations) {
            if (relation != null && relation.otherCapitalId() != null) {
                states.put(relation.otherCapitalId(), relation.state());
            }
        }
        return new CourtRoleMemory.CapitalSnapshot(court.sovereign(), court.heir(), court.state(),
                court.mourning(), states);
    }

    /**
     * One piece of court news. The capital itself is the subject: gossip is village-scoped, and what
     * the village is talking about is the crown, not whichever courtier the chronicle happened to
     * name.
     */
    private static GossipEvent event(GossipEventType type, CapitalCourtView court, long now,
                                     Optional<UUID> secondary, String text) {
        return new GossipEvent(UUID.randomUUID(), type, court.villageId(), now,
                court.capitalId(), court.name(), secondary, text == null ? "" : text);
    }

    // --- The glue ------------------------------------------------------------------------------

    /** One poll of every capital on the server. Never throws; a failed poll is retried next time. */
    public static void tick(MinecraftServer server) {
        if (server == null || !CapitalsCompat.isActive() || !newsEnabled() || !gossipEnabled()) {
            return;
        }
        try {
            CapitalsBridge bridge = CapitalsBridge.Holder.get();
            List<CapitalCourtView> courts = bridge.allCourts(server);
            if (courts.isEmpty()) {
                return;
            }
            // Levels are resolved once, up front: a capital whose dimension is not loaded is skipped
            // entirely rather than half-read, and the source below never has to ask again.
            Map<UUID, ServerLevel> levels = new LinkedHashMap<>();
            List<CapitalCourtView> readable = new ArrayList<>(courts.size());
            for (CapitalCourtView court : courts) {
                Optional<ServerLevel> level = bridge.levelOf(server, court);
                if (level.isPresent()) {
                    levels.put(court.capitalId(), level.get());
                    readable.add(court);
                }
            }
            if (readable.isEmpty()) {
                return;
            }

            CourtMemorySavedData storage = CourtMemorySavedData.get(server);
            GossipSavedData gossip = GossipSavedData.get(server);
            int maxPerVillage = McaConversationsConfig.COMMON.maxEventsPerVillage.get();
            long now = server.overworld().getGameTime();

            PollResult result = poll(readable, new BridgeSource(bridge, levels), storage.memory(), now,
                    McaConversationsConfig.COMMON.capitalNewsMaxPerPoll.get(),
                    event -> gossip.addEvent(event, maxPerVillage));
            if (result.memoryChanged()) {
                storage.markChanged();
            }
            if (result.emitted() > 0 && McaConversationsConfig.COMMON.capitalsDebug.get()) {
                McaConversations.LOGGER.info("Court news: {} event(s) from {} capital(s)",
                        result.emitted(), readable.size());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("court news poll failed; skipping this poll", t);
        }
    }

    /** The production reading: the bridge, plus the level each capital was resolved in. */
    private record BridgeSource(CapitalsBridge bridge, Map<UUID, ServerLevel> levels)
            implements CourtSource {

        @Override
        public List<CapitalChronicleEventView> chronicleSince(CapitalCourtView court, int fromIndex,
                                                              int max) {
            ServerLevel level = levels.get(court.capitalId());
            return level == null ? List.of() : bridge.chronicleSince(level, court, fromIndex, max);
        }

        @Override
        public List<CapitalRelationView> relationsOf(CapitalCourtView court) {
            ServerLevel level = levels.get(court.capitalId());
            return level == null ? List.of() : bridge.relationsOf(level, court);
        }

        @Override
        public String displayName(CapitalCourtView court, UUID entity) {
            ServerLevel level = levels.get(court.capitalId());
            return level == null ? "" : bridge.displayName(level, court, entity);
        }
    }

    /** Config is not loaded in unit tests or very early startup; treat that as enabled. */
    private static boolean newsEnabled() {
        try {
            return McaConversationsConfig.isFeatureEnabled("capital_news");
        } catch (Throwable t) {
            return true;
        }
    }

    private static boolean gossipEnabled() {
        try {
            return McaConversationsConfig.COMMON.enableGossip.get();
        } catch (Throwable t) {
            return true;
        }
    }
}
