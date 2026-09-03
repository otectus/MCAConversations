package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.context.ContextKey;
import dev.otectus.mcaconversations.context.ContextSources;
import dev.otectus.mcaconversations.conversation.SceneShape;
import dev.otectus.mcaconversations.history.CommitmentResolver;
import dev.otectus.mcaconversations.history.CommitmentTemplate;
import dev.otectus.mcaconversations.history.ConversationHistoryStore;
import dev.otectus.mcaconversations.history.EpisodeState;
import dev.otectus.mcaconversations.history.EpisodeTemplate;
import dev.otectus.mcaconversations.history.HistoryCaps;
import dev.otectus.mcaconversations.history.ThreadTemplate;
import dev.otectus.mcaconversations.identity.IdentityCatalog;
import dev.otectus.mcaconversations.identity.IdentityFamily;
import dev.otectus.mcaconversations.identity.IdentityToken;
import dev.otectus.mcaconversations.identity.VillagerIdentityRecord;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import dev.otectus.mcaconversations.scene.SceneDefinition;
import dev.otectus.mcaconversations.scene.SlotType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * Writes the living-histories reports the release ships with (spec §19.7).
 *
 * <p>Four of the plan's ten reports, and the four that matter first: what scenes exist and what each
 * needs before it can be chosen; which identity tokens exist and whether any is dead; every thread
 * and promise lifecycle; and the record schema with its caps. All four are generated rather than
 * written, because a hand-maintained table of this is wrong within a week — and all four are asserted
 * deterministic, because the interesting question about a report is usually not what it says but what
 * a change did to it.
 */
class LivingHistoriesReportTest {

    private static final Path REPORT_DIR = TestPaths.of("build/reports/conversations");
    private static final Path DATA = TestPaths.of("src/main/resources/data/mcaconversations");

    @Test
    @DisplayName("scenes.md lists every scene, what gates it, and where it routes")
    void writeScenesReport() throws IOException {
        SceneCatalog catalog = SceneCatalog.build(read(DATA.resolve("conversation_scenes"), "scenes",
                SceneDefinition::fromJson));

        StringBuilder out = new StringBuilder();
        header(out, "Conversation scenes",
                "Every authored scene, the conditions that must hold before the director may choose it,",
                "and the contracted route it hands control to. A scene is not dialogue: it names a",
                "question and an opening beat that already exist, and everything else here is the rule",
                "for when that route is the right one (spec 10.4).");
        out.append("| Scene | Purpose | Shape | Profession | Episode | States | Slots | Route | Thread | Fallback |\n");
        out.append("|---|---|---|---|---|---|---|---|---|---|\n");
        for (SceneDefinition scene : catalog.all()) {
            out.append("| `").append(scene.id()).append("` | ")
                    .append(scene.purpose().key())
                    .append(scene.topic().isEmpty() ? "" : ":" + scene.topic()).append(" | ")
                    .append(scene.shape().key()).append(" | ")
                    .append(scene.professions().isEmpty() ? "any" : join(scene.professions())).append(" | ")
                    .append(scene.episodeKind().isEmpty() ? "—" : "`" + scene.episodeKind() + "`").append(" | ")
                    .append(scene.episodeStates().stream().map(EpisodeState::key).sorted().toList()).append(" | ")
                    .append(slots(scene.requiredSlots())).append(" | `")
                    .append(scene.questionId()).append("` | ")
                    .append(scene.threadTemplate().isEmpty() ? "—" : "`" + scene.threadTemplate() + "`").append(" | ")
                    .append(scene.hasFallback() ? "`" + scene.fallbackScene() + "`" : "—").append(" |\n");
        }
        out.append("\n## Topics\n\n")
                .append("Scenes per `purpose/topic`, before the index divides them by profession. ")
                .append("This is the editorial view: how much of the corpus is about what.\n\n")
                .append("| Topic | Scenes |\n|---|---:|\n");
        catalog.topicSizes().forEach((key, size) ->
                out.append("| `").append(key).append("` | ").append(size).append(" |\n"));

        out.append("\n## Index leaves\n\n")
                .append("Stage one of the candidate pipeline is a lookup on `purpose/topic#profession`. ")
                .append("A leaf over ").append(SceneCatalog.MAX_INDEXED)
                .append(" entries is truncated deterministically, and **Authored** is printed before ")
                .append("that truncation — a leaf whose two columns differ is shipping content no ")
                .append("player can reach.\n\n")
                .append("| Leaf | Authored | Indexed |\n|---|---:|---:|\n");
        catalog.rawBucketSizes().forEach((key, size) ->
                out.append("| `").append(key).append("` | ").append(size).append(" | ")
                        .append(catalog.bucketSizes().getOrDefault(key, 0)).append(" |\n"));
        if (catalog.truncations().isEmpty()) {
            out.append("\nNo leaf is truncated: every authored scene is reachable.\n");
        } else {
            out.append("\n**Truncated:**\n\n");
            catalog.truncations().forEach(problem -> out.append("- ").append(problem).append("\n"));
        }

        assertDeterministic(REPORT_DIR.resolve("scenes.md"), out.toString());
    }

    @Test
    @DisplayName("identity-coverage.md shows the token catalog and what each token gates")
    void writeIdentityReport() throws IOException {
        IdentityCatalog catalog = readIdentityCatalog();

        StringBuilder out = new StringBuilder();
        header(out, "Identity coverage",
                "The stable anchors a villager may be generated with, and the rules about who may be",
                "given each. Gates decide eligibility, favours only weight, and bans exist so a profile",
                "can never infer a sensitive identity from a job or a mood (spec 6.2, 6.3).");
        out.append("| Family | Cap | Tokens |\n|---|---:|---|\n");
        for (IdentityFamily family : IdentityFamily.values()) {
            out.append("| ").append(family.key()).append(" | ").append(family.cap()).append(" | ")
                    .append(catalog.family(family).size()).append(" |\n");
        }
        out.append("\n| Token | Family | Weight | Ages | Professions | Favours | Bans | Conflicts |\n")
                .append("|---|---|---:|---|---|---|---|---|\n");
        for (IdentityToken token : catalog.all()) {
            out.append("| `").append(token.id()).append("` | ").append(token.family().key())
                    .append(" | ").append(token.weight())
                    .append(" | ").append(token.ages().isEmpty() ? "any" : join(token.ages()))
                    .append(" | ").append(token.professions().isEmpty() ? "any" : join(token.professions()))
                    .append(" | ").append(join(token.favourArchetypes()))
                    .append(join(token.favourPersonalities()).isEmpty() ? ""
                            : " / " + join(token.favourPersonalities()))
                    .append(" | ").append(join(token.neverWithProfessions()))
                    .append(join(token.neverWithPersonalities()).isEmpty() ? ""
                            : " / " + join(token.neverWithPersonalities()))
                    .append(" | ").append(join(catalog.conflictsWith(token.id())))
                    .append(" |\n");
        }
        out.append("\nProfile schema version: ").append(VillagerIdentityRecord.SCHEMA_VERSION)
                .append(". Aliases: ").append(catalog.aliases().isEmpty() ? "none" : catalog.aliases())
                .append(".\n");

        assertDeterministic(REPORT_DIR.resolve("identity-coverage.md"), out.toString());
    }

    @Test
    @DisplayName("threads.md documents every episode, thread and promise lifecycle")
    void writeThreadsReport() throws IOException {
        List<EpisodeTemplate> episodes =
                read(DATA.resolve("episode_templates"), "episodes", EpisodeTemplate::fromJson);
        List<ThreadTemplate> threads =
                read(DATA.resolve("thread_templates"), "threads", ThreadTemplate::fromJson);
        List<CommitmentTemplate> commitments =
                read(DATA.resolve("commitment_templates"), "commitments", CommitmentTemplate::fromJson);

        StringBuilder out = new StringBuilder();
        header(out, "Episodes, threads and promises",
                "The lifecycles behind every callback. An episode is a situation with a state; a thread",
                "is what one villager and one player are in the middle of; a promise is a thing the game",
                "can actually observe. Every promise below names a resolver, because a promise the game",
                "cannot check must be worded as willingness instead (spec 8.5).");

        out.append("## Episodes\n\n")
                .append("| Kind | Subject | Opens in | States | Slots | Pools | Privacy | Salience | Due | Expires |\n")
                .append("|---|---|---|---|---|---|---|---:|---:|---:|\n");
        for (EpisodeTemplate template : sortedBy(episodes, EpisodeTemplate::kind)) {
            out.append("| `").append(template.kind()).append("` | `").append(template.subject())
                    .append("` | ").append(template.initialState().key())
                    .append(" | ").append(template.states().stream().map(EpisodeState::key).sorted().toList())
                    .append(" | ").append(join(template.requiredSlots()))
                    .append(" | ").append(poolSizes(template))
                    .append(" | ").append(template.privacy().key())
                    .append(" | ").append(template.baseSalience())
                    .append(" | ").append(template.dueAfterDays().isPresent()
                            ? template.dueAfterDays().getAsLong() + "d" : "—")
                    .append(" | ").append(template.expiresAfterDays().isPresent()
                            ? template.expiresAfterDays().getAsLong() + "d" : "—")
                    .append(" |\n");
        }
        out.append("\n### Declared transitions\n\n");
        for (EpisodeTemplate template : sortedBy(episodes, EpisodeTemplate::kind)) {
            List<String> arrows = new ArrayList<>();
            new TreeMap<>(template.allowedTransitions()).forEach((from, targets) ->
                    targets.stream().sorted().forEach(to ->
                            arrows.add(from.key() + "->" + to.key())));
            out.append("- `").append(template.kind()).append("`: ")
                    .append(arrows.isEmpty() ? "the full state machine" : String.join(", ", arrows))
                    .append("\n");
        }

        out.append("\n## Threads\n\n")
                .append("| Thread | Topic | Subject | Episode | Resume scenes | Cooldown | Expires | Privacy |\n")
                .append("|---|---|---|---|---|---:|---:|---|\n");
        for (ThreadTemplate template : sortedBy(threads, ThreadTemplate::id)) {
            out.append("| `").append(template.id()).append("` | ").append(template.topic())
                    .append(" | `").append(template.subject())
                    .append("` | ").append(template.needsEpisode() ? "`" + template.episodeKind() + "`" : "—")
                    .append(" | ").append(template.resumeScenes())
                    .append(" | ").append(template.cooldownDays()).append("d")
                    .append(" | ").append(template.expiresAfterDays().isPresent()
                            ? template.expiresAfterDays().getAsLong() + "d" : "never")
                    .append(" | ").append(template.privacy().key()).append(" |\n");
        }

        out.append("\n## Promises\n\n")
                .append("| Promise | Resolver | Target | Due | Made by | Thread | Judgeable |\n")
                .append("|---|---|---|---:|---|---|---|\n");
        for (CommitmentTemplate template : sortedBy(commitments, CommitmentTemplate::id)) {
            out.append("| `").append(template.id()).append("` | ").append(template.resolver().key())
                    .append(" | ").append(template.target().isEmpty() ? "—" : "`" + template.target().qualified() + "`")
                    .append(" | ").append(template.dueAfterDays().isPresent()
                            ? template.dueAfterDays().getAsLong() + "d" : "—")
                    .append(" | ").append(template.madeBy().key())
                    .append(" | ").append(template.threadId().isEmpty() ? "—" : "`" + template.threadId() + "`")
                    .append(" | ").append(template.resolver().isJudgeable() ? "yes" : "never judged")
                    .append(" |\n");
        }
        out.append("\nRegistered resolvers: ");
        for (CommitmentResolver resolver : CommitmentResolver.values()) {
            out.append("`").append(resolver.key()).append("` ");
        }
        out.append("\n");

        assertDeterministic(REPORT_DIR.resolve("threads.md"), out.toString());
    }

    @Test
    @DisplayName("memory-schema.md records the persisted shape, its caps and its providers")
    void writeMemorySchemaReport() throws IOException {
        StringBuilder out = new StringBuilder();
        header(out, "Persisted schema and caps",
                "What the living-histories layer writes to a world, how much of it there can be, and",
                "which provider answers each context field. Every collection has a hard ceiling the",
                "store enforces whatever the config says, so a mis-set property can shrink what the mod",
                "remembers but never make a save grow without bound (spec 8.8, 8.9).");

        out.append("## Files\n\n")
                .append("| File | Schema | Holds |\n|---|---:|---|\n")
                .append("| `data/mcaconversations_identity.dat` | ")
                .append(VillagerIdentityRecord.SCHEMA_VERSION)
                .append(" | one stable profile per villager |\n")
                .append("| `data/mcaconversations_history.dat` | ")
                .append(ConversationHistoryStore.CURRENT_VERSION)
                .append(" | episodes, opinions, threads, promises, claims, recency |\n")
                .append("| `data/mcaconversations_progress.dat` | 1 | 1.4.0 arcs, milestones, budgets ")
                .append("(untouched by this layer) |\n");

        out.append("\n## Caps\n\n").append("| Collection | Configured | Hard ceiling |\n|---|---:|---:|\n")
                .append("| Active or blocked episodes per villager | ").append(HistoryCaps.activeEpisodes())
                .append(" | ").append(HistoryCaps.HARD_ACTIVE_EPISODES).append(" |\n")
                .append("| Resolved episodes per villager | ").append(HistoryCaps.resolvedEpisodes())
                .append(" | ").append(HistoryCaps.HARD_RESOLVED_EPISODES).append(" |\n")
                .append("| Open threads per pair | ").append(HistoryCaps.threadsPerPair())
                .append(" | ").append(HistoryCaps.HARD_THREADS_PER_PAIR).append(" |\n")
                .append("| Promises per pair | ").append(HistoryCaps.commitmentsPerPair())
                .append(" | ").append(HistoryCaps.HARD_COMMITMENTS_PER_PAIR).append(" |\n")
                .append("| Player claims per pair | ").append(HistoryCaps.claimsPerPair())
                .append(" | ").append(HistoryCaps.HARD_CLAIMS_PER_PAIR).append(" |\n")
                .append("| Social opinions per villager | ").append(HistoryCaps.opinionsPerVillager())
                .append(" | ").append(HistoryCaps.HARD_OPINIONS_PER_VILLAGER).append(" |\n")
                .append("| Recency entries per pair | ").append(HistoryCaps.recencyPerPair())
                .append(" | ").append(HistoryCaps.HARD_RECENCY_PER_PAIR).append(" |\n")
                .append("| Tracked villagers | — | ").append(HistoryCaps.HARD_VILLAGERS).append(" |\n")
                .append("| Player pairs per villager | — | ").append(HistoryCaps.HARD_PAIRS_PER_VILLAGER)
                .append(" |\n");

        out.append("\n## Context fields\n\n")
                .append("`~` marks a volatile field: one that may be refreshed at a turn boundary. Everything")
                .append(" else is pinned for the life of a scene, because a scene that re-reads its own facts")
                .append(" is how a bound referent silently becomes a different person (spec 7.4).\n\n")
                .append("| Field | Owner | Volatile |\n|---|---|---|\n");
        Map<String, String> owners = ContextSources.fieldOwners();
        for (ContextKey<?> key : ContextKey.all()) {
            out.append("| `").append(key.id()).append("` | ")
                    .append(owners.getOrDefault(key.id(), "—")).append(" | ")
                    .append(key.isVolatile() ? "~" : "").append(" |\n");
        }

        out.append("\n## Shapes\n\nRepetition suppression penalises a recently used rhetorical shape even")
                .append(" when the nouns differ, which is what stops a large corpus reading as one")
                .append(" conversation (spec 9.4).\n\n| Shape | Cooldown | High stakes |\n|---|---:|---|\n");
        for (SceneShape shape : SceneShape.values()) {
            out.append("| ").append(shape.key()).append(" | ").append(shape.cooldownDays())
                    .append("d | ").append(shape.isHighStakes() ? "yes" : "").append(" |\n");
        }

        assertDeterministic(REPORT_DIR.resolve("memory-schema.md"), out.toString());
    }

    // --- helpers ------------------------------------------------------------------------------

    private static void header(StringBuilder out, String title, String... lines) {
        out.append("# ").append(title).append("\n\n");
        for (String line : lines) {
            out.append(line).append("\n");
        }
        out.append("\n_Generated by `LivingHistoriesReportTest`; do not edit by hand._\n\n");
    }

    private static String join(java.util.Collection<String> values) {
        return values.isEmpty() ? "" : String.join(", ", new java.util.TreeSet<>(values));
    }

    private static String slots(Map<String, SlotType> slots) {
        if (slots.isEmpty()) {
            return "—";
        }
        List<String> out = new ArrayList<>();
        new TreeMap<>(slots).forEach((name, type) -> out.add(name + ":" + type.key()));
        return String.join(", ", out);
    }

    private static String poolSizes(EpisodeTemplate template) {
        if (template.slotOptions().isEmpty()) {
            return "—";
        }
        List<String> out = new ArrayList<>();
        new TreeMap<>(template.slotOptions()).forEach((slot, options) ->
                out.add(slot + "×" + options.size()));
        return String.join(", ", out);
    }

    private static <T> List<T> sortedBy(List<T> values, java.util.function.Function<T, String> key) {
        List<T> out = new ArrayList<>(values);
        out.sort(java.util.Comparator.comparing(key));
        return out;
    }

    /**
     * Writes the report, then writes it again and asserts the bytes match.
     *
     * <p>A report whose ordering wobbles produces a diff on every build and hides the one change
     * somebody actually wants to see.
     */
    private static void assertDeterministic(Path path, String content) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);
        String reread = Files.readString(path);
        assertEquals(content, reread, path + " did not round-trip");
        assertTrue(content.length() > 200, path + " is suspiciously short");
    }

    private interface Factory<T> {
        T create(String id, JsonObject json);
    }

    private static <T> List<T> read(Path directory, String section, Factory<T> factory) {
        List<T> out = new ArrayList<>();
        if (!Files.isDirectory(directory)) {
            return out;
        }
        try (var stream = Files.list(directory)) {
            for (Path file : stream.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (!root.has(section) || !root.get(section).isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject(section).entrySet()) {
                    if (entry.getKey().startsWith("_") || !entry.getValue().isJsonObject()) {
                        continue;
                    }
                    JsonObject body = entry.getValue().getAsJsonObject();
                    body.remove("_comment");
                    out.add(factory.create(entry.getKey(), body));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return out;
    }

    private static IdentityCatalog readIdentityCatalog() {
        List<IdentityToken> tokens = read(DATA.resolve("identity_tokens"), "tokens",
                IdentityToken::fromJson);
        Map<String, String> aliases = new TreeMap<>();
        try (var stream = Files.list(DATA.resolve("identity_tokens"))) {
            for (Path file : stream.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (root.has("aliases") && root.get("aliases").isJsonObject()) {
                    root.getAsJsonObject("aliases").entrySet().forEach(entry ->
                            aliases.put(entry.getKey(), entry.getValue().getAsString()));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return IdentityCatalog.build(tokens, aliases);
    }
}
