package dev.otectus.mcaconversations.hub;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Availability sentences say something a person could say, and never assume a pronoun. */
class TopicAvailabilityTest {

    private static String key(Component component) {
        return component.getContents() instanceof TranslatableContents contents
                ? contents.getKey() : component.getString();
    }

    @Test
    void anAvailableTopicExplainsNothing() {
        assertFalse(TopicAvailability.AVAILABLE.explainable());
        assertNull(TopicAvailability.AVAILABLE.sentence(Component.literal("Anna")));
    }

    @Test
    void anUnnamedVillagerIsDescribedRatherThanGendered() {
        assertEquals("mcaconversations.availability.busy.unnamed",
                key(TopicAvailability.BUSY.sentence(null)));
        assertEquals("mcaconversations.availability.not_ready.unnamed",
                key(TopicAvailability.NOT_READY.sentence(null)));
    }

    @Test
    void aNamedVillagerIsNamed() {
        Component sentence = TopicAvailability.BUSY.sentence(Component.literal("Anna"));
        assertEquals("mcaconversations.availability.busy.named", key(sentence));
        assertEquals(1, ((TranslatableContents) sentence.getContents()).getArgs().length);
    }

    @Test
    void whatTheTwoOfThemDidNeedsNeitherNameNorPronoun() {
        assertEquals("mcaconversations.availability.discussed_today",
                key(TopicAvailability.DISCUSSED_TODAY.sentence(Component.literal("Anna"))));
    }

    @Test
    void everyShowableSentenceIsDeclaredForTheLocaleCheck() {
        assertEquals(5, TopicAvailability.allLangKeys().size());
    }
}
