package dev.otectus.mcaconversations.debug;

import dev.otectus.mcaconversations.conversation.BeatContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * One turn of a conversation, assembled so a human can read it (spec §5.6).
 *
 * <p>No tag system can prove that prose sounds natural. Contracts make whole classes of non-sequitur
 * structurally detectable, but the last check is always a person reading the exchange — and until
 * now that meant opening 173 JSON files and a 400 KB lang file side by side. A trace puts one turn's
 * entire adjacency in one place: what could have been said, what the player may answer, what the
 * villager may answer back, and where each of those lands.
 */
public record ConversationTrace(String questionId,
                                String prompt,
                                List<Inbound> inbound,
                                List<Button> buttons) {

    /** A villager line that can open this question. */
    public record Inbound(String sayKey,
                          List<String> variants,
                          String selectionContext,
                          Optional<BeatContract> contract,
                          String fromRoute) {

        /** True when nothing declares what this line means — migration debt, and reported as such. */
        public boolean isUncontracted() {
            return contract.isEmpty();
        }
    }

    /** A player button, with everything the villager may say back to it. */
    public record Button(String name,
                         String label,
                         String stance,
                         String tone,
                         boolean contracted,
                         List<Reaction> reactions) {
    }

    /** One villager reaction to a button, and the page it opens. */
    public record Reaction(String sayKey,
                           List<String> variants,
                           String selectionContext,
                           String next,
                           List<String> nextButtons,
                           List<String> consequences,
                           Optional<BeatContract> contract) {
    }

    /** Every distinct meaning that can open this page, for spotting semantic fan-in at a glance. */
    public Map<String, List<String>> inboundContractFamilies() {
        Map<String, List<String>> byFamily = new TreeMap<>();
        for (Inbound line : inbound) {
            String family = line.contract()
                    .map(c -> c.npcAct().key() + "/" + c.polarity().key() + "/" + c.openness().key())
                    .orElse("(uncontracted)");
            byFamily.computeIfAbsent(family, k -> new ArrayList<>()).add(line.sayKey());
        }
        return byFamily;
    }

    /** True when this page is entered by lines whose declared meanings disagree (spec §5.5 rule 2). */
    public boolean hasSemanticFanIn() {
        return inboundContractFamilies().size() > 1;
    }

    public long uncontractedInboundCount() {
        return inbound.stream().filter(Inbound::isUncontracted).count();
    }
}
