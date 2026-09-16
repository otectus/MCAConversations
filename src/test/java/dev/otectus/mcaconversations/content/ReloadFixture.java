package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentReloadAttempt;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;
import dev.otectus.mcaconversations.conversation.ConversationGuard;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.util.Unit;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An in-memory datapack and a synchronous drive of one whole reload, including the MCA apply this
 * mod publishes inside.
 *
 * <p>The unit JVM cannot run MCA's parse path — its jars are SRG-named against an official-mapped
 * Minecraft, so anything of MCA's that touches a Minecraft member throws
 * {@code NoSuchMethodError} (boundary note §5). What this fixture therefore drives is the shape of
 * the boundary rather than MCA's parser: the map is filled with opaque marker objects under the same
 * keys MCA's key rule produces, and the head/tail callbacks are invoked exactly where the mixin
 * invokes them. {@code McaDialogueReloadProbeTest} is what pins the real members against real jars.
 */
final class ReloadFixture {

    private final Map<ResourceLocation, List<Entry>> files = new LinkedHashMap<>();

    private record Entry(String pack, String body) {
    }

    /** Explicit executable counterpart for fixtures that reference the chitchat topic. */
    ReloadFixture withChitchat() {
        return with("dialogues", "mcaconversations", "conversations.cat.chitchat",
                "{\"answers\":[{\"name\":\"day\",\"results\":[{\"actions\":{\"next\":\"main\"}}]}]}");
    }

    /** Adds {@code data/<ns>/<directory>/<name>.json}, on top of any copy already there. */
    ReloadFixture with(String directory, String namespace, String name, String body) {
        return with(directory, namespace, name, body, "fixture");
    }

    /** The same, naming the pack it comes from, so an override can be told from a merge. */
    ReloadFixture with(String directory, String namespace, String name, String body, String pack) {
        ResourceLocation key = ResourceLocation.fromNamespaceAndPath(namespace, directory + "/" + name + ".json");
        files.computeIfAbsent(key, ignored -> new ArrayList<>()).add(new Entry(pack, body));
        return this;
    }

    /** Every shipped resource of this mod, read from the repository the way the lint tests read it. */
    static ReloadFixture shipped() throws java.io.IOException {
        ReloadFixture fixture = new ReloadFixture();
        java.nio.file.Path data = dev.otectus.mcaconversations.support.TestPaths.of("src/main/resources/data");
        try (Stream<java.nio.file.Path> namespaces = java.nio.file.Files.list(data)) {
            for (java.nio.file.Path namespace : namespaces.toList()) {
                try (Stream<java.nio.file.Path> walked = java.nio.file.Files.walk(namespace)) {
                    for (java.nio.file.Path file : walked.toList()) {
                        if (!file.toString().endsWith(".json")) {
                            continue;
                        }
                        String relative = namespace.relativize(file).toString().replace('\\', '/');
                        int slash = relative.indexOf('/');
                        if (slash < 0) {
                            continue;
                        }
                        String directory = relative.substring(0, slash);
                        String name = relative.substring(slash + 1, relative.length() - ".json".length());
                        fixture.with(directory, namespace.getFileName().toString(), name,
                                java.nio.file.Files.readString(file), "mcaconversations");
                    }
                }
            }
        }
        return fixture;
    }

    ResourceManager manager() {
        Map<ResourceLocation, List<Resource>> built = new LinkedHashMap<>();
        files.forEach((key, stack) -> {
            List<Resource> resources = new ArrayList<>();
            stack.forEach(entry -> resources.add(new Resource(new StubPack(entry.pack()),
                    () -> stream(entry.body()))));
            built.put(key, resources);
        });
        return new StubManager(built);
    }

    /** Drives this fixture as one whole reload with no pre-existing MCA questions. */
    Outcome run() throws Exception {
        return reload(manager(), Map.of(), Set.of());
    }

    /**
     * Drives one reload the way the game does: prepare on a background executor, the barrier, the
     * coordinator's apply, then MCA's apply head and tail with the parsed map.
     *
     * @param withhold names to omit from the map MCA hands back, simulating content that did not
     *                 parse into an executable question
     */
    Outcome reload(ResourceManager manager, Map<String, Object> existingQuestions,
                   Set<String> withhold) throws Exception {
        ContentReloadCoordinator.setMcaExpectedForTesting(true);
        try {
            // Use Minecraft's real preparation barrier and listener-future chain. Calling MCA's
            // callbacks manually between queue drains hid tasks queued before MCA could apply.
            ApplyQueue apply = new ApplyQueue();
            ContentReloadCoordinator listener = ContentReloadCoordinator.begin();
            ContentReloadAttempt attempt = ContentReloadCoordinator.pending();
            Object instance = new Object();
            Map<String, Object> live = new LinkedHashMap<>(existingQuestions);
            ResourceManagerReloadListener mca = ignored -> {
                ContentReloadCoordinator.onDialoguesApplyHead(instance);
                // MCA's apply clears its map in place and refills it from the new pack.
                live.clear();
                for (ResourceLocation key : files.keySet()) {
                    if (!key.getPath().startsWith("dialogues/")) {
                        continue;
                    }
                    String name = key.getPath().substring(key.getPath().lastIndexOf('/') + 1);
                    name = name.substring(0, name.length() - ".json".length());
                    if (!withhold.contains(name)) {
                        live.put(name, new Parsed(name));
                    }
                }
                ContentReloadCoordinator.onDialoguesApplyTail(instance, live);
            };
            var reload = SimpleReloadInstance.of(manager,
                    List.of(listener, mca, ContentReloadCoordinator.completionListener()), Runnable::run,
                    apply, CompletableFuture.completedFuture(Unit.INSTANCE));
            apply.drain();
            reload.done().join();
            return new Outcome(attempt, live, ContentReloadCoordinator.committed());
        } finally {
            ContentReloadCoordinator.clearMcaExpectedForTesting();
        }
    }

    /** A reload with no MCA apply at all: the hook missing, or MCA's listener reshaped. */
    Outcome reloadWithoutMcaApply(ResourceManager manager) {
        ContentReloadCoordinator.setMcaExpectedForTesting(true);
        try {
            ApplyQueue apply = new ApplyQueue();
            ContentReloadCoordinator listener = ContentReloadCoordinator.begin();
            ContentReloadAttempt attempt = ContentReloadCoordinator.pending();
            var reload = SimpleReloadInstance.of(manager,
                    List.of(listener, ContentReloadCoordinator.completionListener()), Runnable::run,
                    apply, CompletableFuture.completedFuture(Unit.INSTANCE));
            apply.drain();
            reload.done().join();
            return new Outcome(attempt, Map.of(), ContentReloadCoordinator.committed());
        } finally {
            ContentReloadCoordinator.clearMcaExpectedForTesting();
        }
    }

    /** The server thread's task queue, drained on demand rather than inline. */
    private static final class ApplyQueue implements java.util.concurrent.Executor {

        private final java.util.ArrayDeque<Runnable> queued = new java.util.ArrayDeque<>();

        @Override
        public void execute(Runnable command) {
            queued.add(command);
        }

        void drain() {
            while (!queued.isEmpty()) {
                queued.poll().run();
            }
        }
    }

    /** One driven reload: the attempt, the map MCA was left holding, and the bundle now in force. */
    record Outcome(ContentReloadAttempt attempt, Map<String, Object> liveQuestions,
                   ConversationContentBundle committed) {

        boolean committedNow() {
            return attempt.verdict() == ContentReloadAttempt.Verdict.COMMITTED;
        }

        List<String> ownedKeysLeftInMca() {
            return liveQuestions.keySet().stream().filter(ConversationGuard::isOurQuestion).sorted().toList();
        }
    }

    /** A stand-in for one of MCA's parsed {@code Question} objects: opaque, identity-comparable. */
    record Parsed(String name) {
    }

    static PreparableReloadListener.PreparationBarrier barrier() {
        return new PreparableReloadListener.PreparationBarrier() {
            @Override
            public <T> CompletableFuture<T> wait(T value) {
                return CompletableFuture.completedFuture(value);
            }
        };
    }

    // --- Stubs ---------------------------------------------------------------------------------

    private static InputStream stream(String body) {
        return new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
    }

    private record StubPack(String id) implements PackResources {

        @Override
        public IoSupplier<InputStream> getRootResource(String... path) {
            return null;
        }

        @Override
        public IoSupplier<InputStream> getResource(PackType type, ResourceLocation location) {
            return null;
        }

        @Override
        public void listResources(PackType type, String namespace, String path, ResourceOutput out) {
        }

        @Override
        public Set<String> getNamespaces(PackType type) {
            return Set.of();
        }

        @Override
        public <T> T getMetadataSection(MetadataSectionSerializer<T> serializer) {
            return null;
        }

        @Override
        public net.minecraft.server.packs.PackLocationInfo location() {
            return new net.minecraft.server.packs.PackLocationInfo(id,
                    net.minecraft.network.chat.Component.literal(id),
                    net.minecraft.server.packs.repository.PackSource.DEFAULT, java.util.Optional.empty());
        }

        @Override
        public String packId() {
            return id;
        }

        @Override
        public void close() {
        }
    }

    private record StubManager(Map<ResourceLocation, List<Resource>> stacks) implements ResourceManager {

        @Override
        public Set<String> getNamespaces() {
            return Set.of();
        }

        @Override
        public List<Resource> getResourceStack(ResourceLocation location) {
            return stacks.getOrDefault(location, List.of());
        }

        @Override
        public Map<ResourceLocation, Resource> listResources(String path, Predicate<ResourceLocation> filter) {
            Map<ResourceLocation, Resource> out = new LinkedHashMap<>();
            listResourceStacks(path, filter).forEach((key, stack) -> out.put(key, stack.get(stack.size() - 1)));
            return out;
        }

        @Override
        public Map<ResourceLocation, List<Resource>> listResourceStacks(String path,
                                                                       Predicate<ResourceLocation> filter) {
            Map<ResourceLocation, List<Resource>> out = new LinkedHashMap<>();
            stacks.forEach((key, stack) -> {
                if (key.getPath().startsWith(path + "/") && filter.test(key)) {
                    out.put(key, stack);
                }
            });
            return out;
        }

        @Override
        public Stream<PackResources> listPacks() {
            return Stream.of();
        }

        @Override
        public Optional<Resource> getResource(ResourceLocation location) {
            List<Resource> stack = stacks.get(location);
            return stack == null || stack.isEmpty() ? Optional.empty()
                    : Optional.of(stack.get(stack.size() - 1));
        }
    }
}
