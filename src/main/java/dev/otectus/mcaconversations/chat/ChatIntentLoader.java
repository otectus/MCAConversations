package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;

/**
 * The published view of {@code data/<namespace>/chat_intents/*.json} (spec §7).
 *
 * <p>This used to be the mod's first {@link net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener},
 * publishing its own index the moment it had one. The parsing now lives in
 * {@code ContentStaging.chatIntents} and the publication belongs to {@code ContentReloadCoordinator},
 * so the intent index changes at the same instant as the catalog it binds to rather than at its own.
 * What is left here is the name the rest of the mod already reads through.
 */
public final class ChatIntentLoader {

    private ChatIntentLoader() {
    }

    /** The intent index of the bundle this operation is running against; empty before the first load. */
    public static IntentIndex active() {
        return ContentOperation.bundle().intents();
    }

    /** Test seam: publish an index without a resource reload. */
    public static void setActiveForTesting(IntentIndex index) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withIntents(index == null ? IntentIndex.build(java.util.List.of(), SynonymTable.EMPTY) : index));
    }
}
