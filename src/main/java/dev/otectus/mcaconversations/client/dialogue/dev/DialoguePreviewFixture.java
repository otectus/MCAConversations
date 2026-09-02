package dev.otectus.mcaconversations.client.dialogue.dev;

import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.stream.IntStream;

/**
 * One synthetic conversation used to exercise the response card in a development client.
 *
 * <p>These deliberately cover the shapes real content produces only rarely -- an answer longer than
 * the safe area, a page boundary landing exactly on the ninth shortcut, a script whose glyph
 * advances differ from Latin -- because those are the cases that break silently and are tedious to
 * reproduce by walking a villager through a dialogue tree.
 *
 * <p>The text is literal rather than translated: preview strings must not ship in the language
 * files, and {@code DialoguePresentationBuilder.withAnswerText} exists so they do not have to.
 */
record DialoguePreviewFixture(String name, Component speaker, Component question, boolean silent,
                              List<Component> answers) {

    DialoguePreviewFixture {
        answers = List.copyOf(answers);
    }

    private static Component text(String value) {
        return Component.literal(value);
    }

    private static List<Component> numbered(int count, String body) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> text(i + ". " + body))
                .map(Component.class::cast)
                .toList();
    }

    static final List<DialoguePreviewFixture> ALL = List.of(
            new DialoguePreviewFixture("Short question, three answers",
                    text("Stephan"),
                    text("Stephan: Morning. Sleep well?"), false,
                    List.of(text("Well enough, thanks."),
                            text("Not really. Long night."),
                            text("Why do you ask?"))),

            new DialoguePreviewFixture("One answer only",
                    text("Rose"),
                    text("Rose: I've been meaning to say something."), false,
                    List.of(text("Go on."))),

            new DialoguePreviewFixture("Nine answers, exactly one page",
                    text("Alina"),
                    text("Alina: There's a lot going on. Where do you want to start?"), false,
                    numbered(9, "Tell me about that.")),

            new DialoguePreviewFixture("Eighteen answers, forced paging",
                    text("Alina"),
                    text("Alina: There's a lot going on. Where do you want to start?"), false,
                    numbered(18, "Tell me about that.")),

            new DialoguePreviewFixture("Long question, multi-line answers",
                    text("Brother Amos"),
                    text("Brother Amos: I have thought about your question since yesterday, and I "
                            + "am still not certain I have an answer that would satisfy either of "
                            + "us. Ask me again, and I will try to be honest rather than kind."),
                    false,
                    List.of(text("I would rather you were honest, even if it is not what I hoped "
                                    + "to hear from you this morning."),
                            text("Take your time. I am not going anywhere, and neither is the "
                                    + "question."),
                            text("Then let us leave it. Some things do not need settling today."))),

            new DialoguePreviewFixture("Answer taller than the safe area",
                    text("The Archivist"),
                    text("The Archivist: You asked what the records say."), false,
                    List.of(text("The ledger runs to four hundred entries, and every one of them "
                                    + "was copied twice: once by the scribe who took the original "
                                    + "dictation, and once by whoever inherited the desk after he "
                                    + "died. The second copy is the one that survives. It differs "
                                    + "from the first in eleven places, and nobody now living can "
                                    + "say which of the two was closer to what was actually said "
                                    + "on the day, or whether either of them was close at all."),
                            text("Just give me the short version."))),

            new DialoguePreviewFixture("Silent prompt, no speaker",
                    text("Rose"),
                    text("The room is quiet. Rose is waiting for you to say something."), true,
                    List.of(text("Say hello."), text("Leave quietly."))),

            new DialoguePreviewFixture("Non-Latin script",
                    text("村人"),
                    text("村人: おはようございます。今日はどちらへ行かれますか。"), false,
                    List.of(text("市場へ行くところです。"),
                            text("まだ決めていません。"),
                            text("あなたはどうしますか。"))),

            new DialoguePreviewFixture("Very long speaker name",
                    text("Bartholomew Fitzwilliam the Younger"),
                    text("Bartholomew Fitzwilliam the Younger: You may call me Bart."), false,
                    List.of(text("Bart it is."), text("I'll use the full name, thank you.")))
    );
}
