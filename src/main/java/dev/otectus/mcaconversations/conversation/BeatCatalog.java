package dev.otectus.mcaconversations.conversation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

/**
 * The compiled, immutable index of every declared beat and reply (spec §6.10).
 *
 * <p>Built once per datapack reload and then read-only, because the questions the runtime asks of it
 * are asked on a button click and must not walk the corpus: "what does this {@code say}+{@code next}
 * pair mean", "which beats can open this question", "what does this button claim to be".
 *
 * <p>The {@link #inbound(String)} index is the one that does the real work. It is the answer to the
 * question that no amount of MCA result conditions can answer — <em>given every route into this page,
 * is every button on it sensible?</em> — because MCA filters buttons by answer constraints alone and
 * never by which result led there (spec §3.1).
 */
public final class BeatCatalog {

    public static final BeatCatalog EMPTY = new BeatCatalog(List.of(), List.of());

    private final Map<String, BeatContract> byId;
    private final Map<String, BeatContract> byRoute;
    private final Map<String, List<BeatContract>> inbound;
    private final Map<String, List<BeatContract>> bySay;
    private final Map<String, ReplyContract> replies;
    private final Map<String, List<ReplyContract>> repliesByQuestion;

    private BeatCatalog(Collection<BeatContract> beats, Collection<ReplyContract> replyContracts) {
        Map<String, BeatContract> ids = new TreeMap<>();
        Map<String, BeatContract> routes = new TreeMap<>();
        Map<String, List<BeatContract>> in = new TreeMap<>();
        Map<String, List<BeatContract>> says = new TreeMap<>();
        for (BeatContract beat : beats) {
            ids.put(beat.id(), beat);
            routes.put(route(beat.say(), beat.responseQuestion()), beat);
            in.computeIfAbsent(beat.responseQuestion(), k -> new ArrayList<>()).add(beat);
            says.computeIfAbsent(beat.say(), k -> new ArrayList<>()).add(beat);
        }
        Map<String, ReplyContract> byKey = new TreeMap<>();
        Map<String, List<ReplyContract>> byQuestion = new TreeMap<>();
        for (ReplyContract reply : replyContracts) {
            byKey.put(reply.key(), reply);
            byQuestion.computeIfAbsent(reply.question(), k -> new ArrayList<>()).add(reply);
        }

        this.byId = Map.copyOf(ids);
        this.byRoute = Map.copyOf(routes);
        this.inbound = freeze(in);
        this.bySay = freeze(says);
        this.replies = Map.copyOf(byKey);
        this.repliesByQuestion = freeze(byQuestion);
    }

    private static <T> Map<String, List<T>> freeze(Map<String, List<T>> source) {
        Map<String, List<T>> out = new LinkedHashMap<>();
        source.forEach((key, value) -> out.put(key, List.copyOf(value)));
        return Map.copyOf(out);
    }

    /**
     * Builds a catalog, rejecting the two duplications that would make lookups ambiguous: two beats
     * claiming one id, and two beats claiming one {@code say}+{@code next} route.
     */
    public static BeatCatalog build(Collection<BeatContract> beats, Collection<ReplyContract> replies) {
        Map<String, String> seenIds = new LinkedHashMap<>();
        Map<String, String> seenRoutes = new LinkedHashMap<>();
        for (BeatContract beat : beats) {
            String clashingId = seenIds.put(beat.id(), beat.id());
            if (clashingId != null) {
                throw new IllegalArgumentException("duplicate beat id '" + beat.id() + "'");
            }
            String routeKey = route(beat.say(), beat.responseQuestion());
            String owner = seenRoutes.put(routeKey, beat.id());
            if (owner != null) {
                throw new IllegalArgumentException("beats '" + owner + "' and '" + beat.id()
                        + "' both contract the route " + routeKey);
            }
        }
        Map<String, String> seenReplies = new LinkedHashMap<>();
        for (ReplyContract reply : replies) {
            if (seenReplies.put(reply.key(), reply.key()) != null) {
                throw new IllegalArgumentException("duplicate reply contract '" + reply.key() + "'");
            }
        }
        return new BeatCatalog(beats, replies);
    }

    /** The key a {@code say}+{@code next} pair is filed under. */
    public static String route(String say, String next) {
        return say + " -> " + next;
    }

    public Optional<BeatContract> beat(String id) {
        return Optional.ofNullable(byId.get(id));
    }

    /** The beat contracted for an authored result's {@code say}+{@code next} pair, if any. */
    public Optional<BeatContract> forRoute(String say, String next) {
        return Optional.ofNullable(byRoute.get(route(say, next)));
    }

    /** Every beat that can open {@code question}. The set every button on that page must satisfy. */
    public List<BeatContract> inbound(String question) {
        return inbound.getOrDefault(question, List.of());
    }

    /** Every beat sharing one base speech pool — they must agree on the whole contract (spec §3.4). */
    public List<BeatContract> forSay(String say) {
        return bySay.getOrDefault(say, List.of());
    }

    public Optional<ReplyContract> reply(String question, String answer) {
        return Optional.ofNullable(replies.get(question + "/" + answer));
    }

    public List<ReplyContract> repliesFor(String question) {
        return repliesByQuestion.getOrDefault(question, List.of());
    }

    public Collection<BeatContract> beats() {
        return byId.values();
    }

    public Collection<ReplyContract> replies() {
        return replies.values();
    }

    /** Every question that at least one beat opens. */
    public Set<String> contractedQuestions() {
        return inbound.keySet();
    }

    public int size() {
        return byId.size();
    }

    public boolean isEmpty() {
        return byId.isEmpty() && replies.isEmpty();
    }
}
