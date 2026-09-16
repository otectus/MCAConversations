package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.IntentIndex;
import dev.otectus.mcaconversations.chat.SynonymTable;
import dev.otectus.mcaconversations.history.NarrativeCatalog;
import dev.otectus.mcaconversations.identity.IdentityCatalog;
import dev.otectus.mcaconversations.interiority.InteriorityProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfiles;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import dev.otectus.mcaconversations.village.VillageCultureCatalog;

import java.util.List;
import java.util.Map;

/**
 * One coherent body of loaded content, published as a single reference assignment.
 *
 * <p>Before this existed, eleven listeners each published their own section at their own moment, so
 * "the content in force" was eleven independently-versioned things and the generation counter was a
 * marker that a reload had happened rather than evidence the content behind it agreed with itself. A
 * bundle is the whole set or none of it.
 *
 * <p><b>Owned executable questions are held strongly.</b> {@link #ownedQuestions()} are MCA's own
 * parsed {@code Question} objects, kept as {@link Object} because no MCA type may be named in this
 * source tree. They are the objects an unanswered offer will execute against, so a rejected reload
 * that let them be collected would destroy exactly what it was supposed to preserve. They are opaque
 * and externally mutable: MCA hands their answer lists out live. This mod never merges into them, and
 * never claims they are deeply immutable — only that the bundle keeps the same objects reachable.
 *
 * <p>Everything else here is immutable. {@link #generation()} is {@code 0} for the bootstrap bundle,
 * which means "no reload has succeeded yet"; the first successful publication is {@code 1}.
 */
public record ConversationContentBundle(long generation,
                                        boolean available,
                                        long attempt,
                                        IntentIndex intents,
                                        ConversationCatalog topics,
                                        BeatCatalog beats,
                                        ProfessionProfiles professions,
                                        Map<String, InteriorityProfile> interiority,
                                        IdentityCatalog identity,
                                        SceneCatalog scenes,
                                        VillageCultureCatalog culture,
                                        NarrativeCatalog narrative,
                                        DialogueResourceIndex dialogues,
                                        Map<String, Object> ownedQuestions,
                                        boolean ownedQuestionsKnown,
                                        List<ContentProblem> problems) {

    /**
     * The bundle in force before any reload has succeeded: generation {@code 0} and explicitly
     * unavailable. {@link #ownedQuestionsKnown()} is false, so the lookup boundary passes every
     * question straight through to MCA rather than answering "absent" for a map it has never seen.
     */
    public static final ConversationContentBundle UNAVAILABLE = new ConversationContentBundle(
            0L, false, 0L,
            IntentIndex.build(List.of(), SynonymTable.EMPTY),
            ConversationCatalog.EMPTY,
            BeatCatalog.EMPTY,
            ProfessionProfiles.EMPTY,
            Map.of(),
            IdentityCatalog.EMPTY,
            SceneCatalog.EMPTY,
            VillageCultureCatalog.EMPTY,
            NarrativeCatalog.EMPTY,
            DialogueResourceIndex.empty(),
            Map.of(),
            false,
            List.of());

    public ConversationContentBundle {
        interiority = interiority == null ? Map.of() : Map.copyOf(interiority);
        ownedQuestions = ownedQuestions == null ? Map.of() : Map.copyOf(ownedQuestions);
        problems = problems == null ? List.of() : List.copyOf(problems);
    }

    /** The executable question MCA parsed for this name under this bundle, or null when absent. */
    public Object ownedQuestion(String name) {
        return name == null ? null : ownedQuestions.get(name);
    }

    /** How many owned executable questions this bundle is holding, for the terminal summary. */
    public int retainedQuestionCount() {
        return ownedQuestions.size();
    }

    // --- Section replacement, for the test seams the loaders still expose ----------------------

    public ConversationContentBundle withIntents(IntentIndex value) {
        return new ConversationContentBundle(generation, available, attempt, value, topics, beats,
                professions, interiority, identity, scenes, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withTopics(ConversationCatalog value) {
        return new ConversationContentBundle(generation, available, attempt, intents, value, beats,
                professions, interiority, identity, scenes, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withBeats(BeatCatalog value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, value,
                professions, interiority, identity, scenes, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withProfessions(ProfessionProfiles value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, beats,
                value, interiority, identity, scenes, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withInteriority(Map<String, InteriorityProfile> value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, beats,
                professions, value, identity, scenes, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withIdentity(IdentityCatalog value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, beats,
                professions, interiority, value, scenes, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withScenes(SceneCatalog value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, beats,
                professions, interiority, identity, value, culture, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withCulture(VillageCultureCatalog value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, beats,
                professions, interiority, identity, scenes, value, narrative, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    public ConversationContentBundle withNarrative(NarrativeCatalog value) {
        return new ConversationContentBundle(generation, available, attempt, intents, topics, beats,
                professions, interiority, identity, scenes, culture, value, dialogues,
                ownedQuestions, ownedQuestionsKnown, problems);
    }

    /** The same content published as the next generation, with the executable table MCA just parsed. */
    public ConversationContentBundle published(long publishedGeneration, long publishedAttempt,
                                               Map<String, Object> owned, boolean ownedKnown,
                                               List<ContentProblem> attemptProblems) {
        return new ConversationContentBundle(publishedGeneration, true, publishedAttempt, intents, topics,
                beats, professions, interiority, identity, scenes, culture, narrative, dialogues,
                owned, ownedKnown, attemptProblems);
    }
}
