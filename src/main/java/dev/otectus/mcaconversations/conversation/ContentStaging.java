package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.chat.IntentBinding;
import dev.otectus.mcaconversations.chat.IntentIndex;
import dev.otectus.mcaconversations.chat.SynonymTable;
import dev.otectus.mcaconversations.history.CommitmentTemplate;
import dev.otectus.mcaconversations.history.EpisodeTemplate;
import dev.otectus.mcaconversations.history.NarrativeCatalog;
import dev.otectus.mcaconversations.history.ThreadTemplate;
import dev.otectus.mcaconversations.identity.IdentityCatalog;
import dev.otectus.mcaconversations.identity.IdentityToken;
import dev.otectus.mcaconversations.interiority.InteriorityProfile;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.profession.ProfessionProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfiles;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import dev.otectus.mcaconversations.scene.SceneDefinition;
import dev.otectus.mcaconversations.village.CultureToken;
import dev.otectus.mcaconversations.village.VillageCultureCatalog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

/**
 * The eleven section parsers, extracted from the eleven loaders that used to own them and made pure.
 *
 * <p>Nothing here writes a live field. Each method takes the effective documents of one directory and
 * returns a {@link StagingResult}: the built section, or a refusal, plus every attributed problem.
 * The coordinator is the only thing that may turn an accepted set of these into live content.
 *
 * <p><b>The failure policy changes here, deliberately.</b> The old loaders dropped a malformed entry
 * and published the rest, so one bad entry in one pack silently replaced a working catalog with a
 * partial one. Staging records a malformed entry as {@link ContentSeverity#REFUSED} and the attempt
 * is rejected as a whole, which is what makes "the previous bundle stays in force" true rather than
 * true per loader. A skipped-but-harmless fact — a file with none of this section's keys in it, a
 * hidden pack override — stays informational.
 */
public final class ContentStaging {

    private ContentStaging() {
    }

    // --- Chat intents -------------------------------------------------------------------------

    /** {@code chat_intents}: synonym classes merge globally, then intents, last id wins. */
    public static StagingResult<IntentIndex> chatIntents(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        SynonymTable.Builder synonyms = SynonymTable.builder();
        for (StagedResource document : documents) {
            JsonObject root = objectRoot(ContentSection.CHAT_INTENTS, document, problems);
            if (root == null || !isObject(root, "synonyms")) {
                continue;
            }
            for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("synonyms").entrySet()) {
                if (!e.getValue().isJsonArray()) {
                    problems.add(ContentProblem.of(ContentSection.CHAT_INTENTS, document.origin(),
                            "/synonyms/" + e.getKey(), e.getKey(), ContentSeverity.REFUSED,
                            "synonym_class_not_an_array", "a synonym class must be an array of strings"));
                    continue;
                }
                List<String> aliases = new ArrayList<>();
                try {
                    e.getValue().getAsJsonArray().forEach(a -> aliases.add(a.getAsString()));
                } catch (Throwable t) {
                    problems.add(ContentProblem.of(ContentSection.CHAT_INTENTS, document.origin(),
                            "/synonyms/" + e.getKey(), e.getKey(), ContentSeverity.REFUSED,
                            "synonym_alias_not_a_string", String.valueOf(t)));
                    continue;
                }
                synonyms.addClass(e.getKey(), aliases);
            }
        }
        Map<String, IntentBinding> byId = new LinkedHashMap<>();
        section(ContentSection.CHAT_INTENTS, documents, "intents", problems, (entry, ctx) -> {
            IntentBinding binding = IntentBinding.fromJson(entry.id(), entry.json());
            if (binding != null) {
                byId.put(entry.id(), binding);
            }
            return null;
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return build(ContentSection.CHAT_INTENTS, problems,
                () -> IntentIndex.build(new ArrayList<>(byId.values()), synonyms.build()));
    }

    // --- Conversation catalog ------------------------------------------------------------------

    /**
     * {@code conversation_catalog}. A topic id is meant to be declared in exactly one place, so two
     * distinct resources claiming one id stays what it was — the sorted-last file wins — but the
     * collision now names every contributing resource instead of only the winner.
     */
    public static StagingResult<ConversationCatalog> conversationCatalog(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, TopicEntry> byId = new LinkedHashMap<>();
        Map<String, List<ResourceOrigin>> declaredIn = new LinkedHashMap<>();
        section(ContentSection.CONVERSATION_CATALOG, documents, "topics", problems, (entry, ctx) -> {
            TopicEntry topic = TopicEntry.fromJson(entry.id(), entry.json());
            if (topic != null) {
                declaredIn.computeIfAbsent(entry.id(), ignored -> new ArrayList<>()).add(entry.origin());
                byId.put(entry.id(), topic);
            }
            return null;
        });
        declaredIn.forEach((id, origins) -> {
            if (origins.size() > 1) {
                problems.add(ContentProblem.of(ContentSection.CONVERSATION_CATALOG,
                                origins.get(origins.size() - 1), "/topics/" + id, id, ContentSeverity.INFO,
                                "topic_declared_twice",
                                "declared in " + origins.size() + " resources; the sorted-last one wins")
                        .withContributors(origins));
            }
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return build(ContentSection.CONVERSATION_CATALOG, problems,
                () -> ConversationCatalog.build(new ArrayList<>(byId.values())));
    }

    // --- Beats and replies ---------------------------------------------------------------------

    /** {@code conversation_beats}: both sections of the directory stage together, as one thing. */
    public static StagingResult<BeatCatalog> beatContracts(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, BeatContract> beats = new LinkedHashMap<>();
        Map<String, ReplyContract> replies = new LinkedHashMap<>();
        section(ContentSection.CONVERSATION_BEATS, documents, "beats", problems, (entry, ctx) -> {
            BeatContract beat = BeatContract.fromJson(entry.id(), entry.json());
            if (beat != null) {
                beats.put(entry.id(), beat);
            }
            return null;
        });
        section(ContentSection.CONVERSATION_BEATS, documents, "replies", problems, (entry, ctx) -> {
            ReplyContract reply = ReplyContract.fromJson(entry.id(), entry.json());
            if (reply != null) {
                replies.put(entry.id(), reply);
            }
            return null;
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return build(ContentSection.CONVERSATION_BEATS, problems,
                () -> BeatCatalog.build(new ArrayList<>(beats.values()), new ArrayList<>(replies.values())));
    }

    // --- Profession profiles -------------------------------------------------------------------

    /** {@code profession_profiles}. A profile for an absent mod stays valid: its id simply never matches. */
    public static StagingResult<ProfessionProfiles> professionProfiles(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, ProfessionProfile> byId = new LinkedHashMap<>();
        section(ContentSection.PROFESSION_PROFILES, documents, "profiles", problems, (entry, ctx) -> {
            ProfessionProfile profile = ProfessionProfile.fromJson(entry.id(), entry.json());
            if (profile != null) {
                byId.put(entry.id(), profile);
            }
            return null;
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return build(ContentSection.PROFESSION_PROFILES, problems,
                () -> ProfessionProfiles.build(new ArrayList<>(byId.values())));
    }

    // --- Interiority ---------------------------------------------------------------------------

    /** {@code interiority}: keys are canonicalised, so 7.6 and 7.7 personality spellings meet. */
    public static StagingResult<Map<String, InteriorityProfile>> interiority(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, InteriorityProfile> loaded = new LinkedHashMap<>();
        Map<String, String> canonicalSources = new LinkedHashMap<>();
        section(ContentSection.INTERIORITY, documents, "profiles", problems, (entry, ctx) -> {
            String canonical = Personalities.canonical(entry.id());
            String previous = canonicalSources.put(canonical, entry.id());
            if (previous != null && !previous.equals(entry.id())) {
                problems.add(ContentProblem.of(ContentSection.INTERIORITY, entry.origin(),
                        "/profiles/" + entry.id(), entry.id(), ContentSeverity.REFUSED,
                        "personality_canonical_collision",
                        "'" + entry.id() + "' and '" + previous + "' both canonicalise to '" + canonical + "'"));
                return null;
            }
            InteriorityProfile profile = InteriorityProfile.fromJson(canonical, entry.json());
            if (profile != null) {
                loaded.put(canonical, profile);
            }
            return null;
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return StagingResult.accepted(Map.copyOf(loaded), problems);
    }

    // --- Identity tokens -----------------------------------------------------------------------

    /** {@code identity_tokens}: tokens keyed by qualified id, plus the forward-resolving alias table. */
    public static StagingResult<IdentityCatalog> identityTokens(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, IdentityToken> byQualifiedId = new LinkedHashMap<>();
        Map<String, String> aliases = new TreeMap<>();
        section(ContentSection.IDENTITY_TOKENS, documents, "tokens", problems, (entry, ctx) -> {
            IdentityToken token = IdentityToken.fromJson(entry.id(), entry.json());
            if (token != null) {
                byQualifiedId.put(token.qualifiedId(), token);
            }
            return null;
        });
        strings(ContentSection.IDENTITY_TOKENS, documents, "aliases", problems, aliases, false);
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return build(ContentSection.IDENTITY_TOKENS, problems,
                () -> IdentityCatalog.build(new ArrayList<>(byQualifiedId.values()), aliases));
    }

    // --- Scenes --------------------------------------------------------------------------------

    /**
     * {@code conversation_scenes}. An index overflow used to be an error logged <em>after</em> the
     * catalog had already been published: a scene silently lost its place. It is a refusal now, so
     * the previous catalog stays in force instead.
     */
    public static StagingResult<SceneCatalog> scenes(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, SceneDefinition> byId = new LinkedHashMap<>();
        Map<String, ResourceOrigin> origins = new LinkedHashMap<>();
        section(ContentSection.CONVERSATION_SCENES, documents, "scenes", problems, (entry, ctx) -> {
            SceneDefinition scene = SceneDefinition.fromJson(entry.id(), entry.json());
            if (scene != null) {
                byId.put(scene.id(), scene);
                origins.put(scene.id(), entry.origin());
            }
            return null;
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        StagingResult<SceneCatalog> built = build(ContentSection.CONVERSATION_SCENES, problems,
                () -> SceneCatalog.build(new ArrayList<>(byId.values())));
        if (!built.accepted()) {
            return built;
        }
        SceneCatalog catalog = built.value();
        List<ContentProblem> all = new ArrayList<>(built.problems());
        catalog.truncations().forEach(problem -> all.add(ContentProblem.of(ContentSection.CONVERSATION_SCENES,
                ResourceOrigin.unknownPack(null), "/scenes", "", ContentSeverity.REFUSED,
                "scene_index_overflow", problem)));
        catalog.danglingReferences().forEach(problem -> all.add(ContentProblem.of(ContentSection.CONVERSATION_SCENES,
                ResourceOrigin.unknownPack(null), "/scenes", "", ContentSeverity.INFO,
                "scene_reference_unresolved", problem)));
        if (fatal(all)) {
            return StagingResult.refused(all);
        }
        return StagingResult.accepted(catalog, all);
    }

    // --- Village culture -----------------------------------------------------------------------

    /** {@code village_culture}: ids are trimmed and lowercased, because a village stores the string. */
    public static StagingResult<VillageCultureCatalog> villageCulture(List<StagedResource> documents) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, CultureToken> tokens = new LinkedHashMap<>();
        Map<String, String> aliases = new TreeMap<>();
        section(ContentSection.VILLAGE_CULTURE, documents, "tokens", problems, (entry, ctx) -> {
            String id = entry.id().trim().toLowerCase(Locale.ROOT);
            CultureToken token = CultureToken.fromJson(id, entry.json());
            if (token == null || !token.isWellFormed()) {
                problems.add(ContentProblem.of(ContentSection.VILLAGE_CULTURE, entry.origin(),
                        "/tokens/" + entry.id(), entry.id(), ContentSeverity.REFUSED,
                        "culture_token_malformed", "the token is not well formed"));
                return null;
            }
            tokens.put(token.id(), token);
            return null;
        });
        strings(ContentSection.VILLAGE_CULTURE, documents, "aliases", problems, aliases, true);
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        return build(ContentSection.VILLAGE_CULTURE, problems,
                () -> new VillageCultureCatalog(tokens, aliases));
    }

    // --- Narrative templates -------------------------------------------------------------------

    /**
     * The three narrative directories, staged together into one catalog.
     *
     * <p>They used to be three listeners sharing one process-static staging area, each calling
     * {@code publish()} over whatever the other two happened to contain — so a reload could publish
     * three times, and a failure in the last one left the first two's contents live. Here the three
     * are sections of one attempt and build once.
     */
    public static StagingResult<NarrativeCatalog> narrative(List<StagedResource> episodeDocuments,
                                                            List<StagedResource> threadDocuments,
                                                            List<StagedResource> commitmentDocuments) {
        List<ContentProblem> problems = new ArrayList<>();
        Map<String, EpisodeTemplate> episodes = new LinkedHashMap<>();
        Map<String, ThreadTemplate> threads = new LinkedHashMap<>();
        Map<String, CommitmentTemplate> commitments = new LinkedHashMap<>();
        section(ContentSection.EPISODE_TEMPLATES, episodeDocuments, "episodes", problems, (entry, ctx) -> {
            EpisodeTemplate template = EpisodeTemplate.fromJson(entry.id(), entry.json());
            if (template != null) {
                episodes.put(template.kind(), template);
            }
            return null;
        });
        section(ContentSection.THREAD_TEMPLATES, threadDocuments, "threads", problems, (entry, ctx) -> {
            ThreadTemplate template = ThreadTemplate.fromJson(entry.id(), entry.json());
            if (template != null) {
                threads.put(template.id(), template);
            }
            return null;
        });
        section(ContentSection.COMMITMENT_TEMPLATES, commitmentDocuments, "commitments", problems, (entry, ctx) -> {
            CommitmentTemplate template = CommitmentTemplate.fromJson(entry.id(), entry.json());
            if (template != null) {
                commitments.put(template.id(), template);
            }
            return null;
        });
        if (fatal(problems)) {
            return StagingResult.refused(problems);
        }
        StagingResult<NarrativeCatalog> built = build(ContentSection.EPISODE_TEMPLATES, problems,
                () -> NarrativeCatalog.build(new ArrayList<>(episodes.values()),
                        new ArrayList<>(threads.values()), new ArrayList<>(commitments.values())));
        if (!built.accepted()) {
            return built;
        }
        List<ContentProblem> all = new ArrayList<>(built.problems());
        built.value().danglingReferences().forEach(problem ->
                all.add(ContentProblem.of(ContentSection.EPISODE_TEMPLATES, ResourceOrigin.unknownPack(null),
                        "", "", ContentSeverity.INFO, "narrative_reference_unresolved", problem)));
        return StagingResult.accepted(built.value(), all);
    }

    // --- Shared shapes -------------------------------------------------------------------------

    /** One {@code id -> object} entry of one section of one document. */
    record Entry(ResourceOrigin origin, String id, JsonObject json) {
    }

    private static void section(ContentSection section, List<StagedResource> documents, String key,
                                List<ContentProblem> problems,
                                BiFunction<Entry, ContentSection, Void> reader) {
        for (StagedResource document : documents) {
            JsonObject root = objectRoot(section, document, problems);
            if (root == null) {
                continue;
            }
            if (!root.has(key)) {
                // A file of this directory that carries none of this section is not a problem: the
                // beat directory legitimately holds files with only replies in them.
                continue;
            }
            if (!root.get(key).isJsonObject()) {
                problems.add(ContentProblem.of(section, document.origin(), "/" + key, "",
                        ContentSeverity.REFUSED, "section_wrong_shape",
                        "'" + key + "' must be an object of id -> entry"));
                continue;
            }
            for (Map.Entry<String, JsonElement> e : root.getAsJsonObject(key).entrySet()) {
                String id = e.getKey();
                if (!e.getValue().isJsonObject()) {
                    problems.add(ContentProblem.of(section, document.origin(), "/" + key + "/" + id, id,
                            ContentSeverity.REFUSED, "entry_not_an_object",
                            "entry '" + id + "' is not an object"));
                    continue;
                }
                try {
                    reader.apply(new Entry(document.origin(), id, e.getValue().getAsJsonObject()), section);
                } catch (Throwable t) {
                    problems.add(ContentProblem.ofSyntax(section, document.origin(), id,
                            ContentSeverity.REFUSED, "entry_malformed", t)
                            .withContributors(List.of(document.origin())));
                }
            }
        }
    }

    private static void strings(ContentSection section, List<StagedResource> documents, String key,
                                List<ContentProblem> problems, Map<String, String> into, boolean normalise) {
        for (StagedResource document : documents) {
            JsonObject root = objectRoot(section, document, problems);
            if (root == null || !root.has(key)) {
                continue;
            }
            if (!root.get(key).isJsonObject()) {
                problems.add(ContentProblem.of(section, document.origin(), "/" + key, "",
                        ContentSeverity.REFUSED, "section_wrong_shape",
                        "'" + key + "' must be an object of string -> string"));
                continue;
            }
            for (Map.Entry<String, JsonElement> e : root.getAsJsonObject(key).entrySet()) {
                if (!e.getValue().isJsonPrimitive()) {
                    problems.add(ContentProblem.of(section, document.origin(), "/" + key + "/" + e.getKey(),
                            e.getKey(), ContentSeverity.REFUSED, "alias_not_a_string",
                            "alias '" + e.getKey() + "' must name a string"));
                    continue;
                }
                String from = normalise ? e.getKey().trim().toLowerCase(Locale.ROOT) : e.getKey();
                String to = normalise ? e.getValue().getAsString().trim().toLowerCase(Locale.ROOT)
                        : e.getValue().getAsString();
                into.put(from, to);
            }
        }
    }

    private static JsonObject objectRoot(ContentSection section, StagedResource document,
                                         List<ContentProblem> problems) {
        JsonElement json = document.json();
        if (json == null || !json.isJsonObject()) {
            problems.add(ContentProblem.of(section, document.origin(), "", "", ContentSeverity.REFUSED,
                    "root_not_an_object", "the file's root must be a JSON object"));
            return null;
        }
        return json.getAsJsonObject();
    }

    private static boolean isObject(JsonObject root, String key) {
        return root.has(key) && root.get(key).isJsonObject();
    }

    private static boolean fatal(List<ContentProblem> problems) {
        return problems.stream().anyMatch(p -> p.severity().fatal());
    }

    private static <T> StagingResult<T> build(ContentSection section, List<ContentProblem> problems,
                                              java.util.function.Supplier<T> builder) {
        try {
            T value = builder.get();
            if (value == null) {
                List<ContentProblem> all = new ArrayList<>(problems);
                all.add(ContentProblem.of(section, ResourceOrigin.unknownPack(null), "", "",
                        ContentSeverity.REFUSED, "section_build_empty", "the section builder produced nothing"));
                return StagingResult.refused(all);
            }
            return StagingResult.accepted(value, problems);
        } catch (Throwable t) {
            List<ContentProblem> all = new ArrayList<>(problems);
            all.add(ContentProblem.of(section, ResourceOrigin.unknownPack(null), "", "",
                    ContentSeverity.REFUSED, "section_build_refused", String.valueOf(t)));
            return StagingResult.refused(all);
        }
    }
}
