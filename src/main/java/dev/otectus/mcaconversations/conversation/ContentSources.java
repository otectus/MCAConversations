package dev.otectus.mcaconversations.conversation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Reads one datapack directory into staged, attributed documents.
 *
 * <p>This replaces the scanning {@link net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener}
 * inherited for the eleven owned sections. That inherited scan drops a file whose JSON will not parse
 * before any loader sees it, and it hands {@code apply} a bare map with no pack id — the two things
 * the boundary note (§6) named as unobtainable. Reading the manager directly makes a malformed file a
 * recorded, attributed problem instead of a silent absence, and supplies the pack the winning
 * resource came from.
 *
 * <p>Only the <em>effective</em> resource for each id is parsed: an identical-resource override from a
 * higher pack hides the lower file completely, so a malformed file underneath a valid override never
 * rejects anything. That a lower file was hidden is recorded informationally, not as a failure.
 */
public final class ContentSources {

    private static final Gson GSON = new Gson();
    private static final String SUFFIX = ".json";

    private ContentSources() {
    }

    /**
     * Every effective {@code .json} document under {@code data/<ns>/<directory>}, ordered by
     * {@link ResourceLocation#compareTo}, with each document's pack and physical path attached.
     *
     * <p>Ordering is established here rather than per loader: four of the eleven sections used to
     * iterate the incoming map directly, so "last id wins" meant whatever the pack stack happened to
     * produce. It now means the same thing for all eleven, on every machine.
     */
    public static Staged read(ResourceManager manager, ContentSection section) {
        List<StagedResource> documents = new ArrayList<>();
        List<ContentProblem> problems = new ArrayList<>();
        if (manager == null) {
            return new Staged(List.of(), List.of());
        }
        String directory = section.directory();
        String prefix = directory + "/";
        Map<ResourceLocation, Resource> effective = new TreeMap<>(ResourceLocation::compareTo);
        try {
            effective.putAll(manager.listResources(directory, path -> path.getPath().endsWith(SUFFIX)));
        } catch (Throwable t) {
            problems.add(ContentProblem.of(section, ResourceOrigin.unknownPack(null), "", "",
                    ContentSeverity.REFUSED, "directory_unreadable",
                    "could not list " + directory + ": " + t));
            return new Staged(List.of(), problems);
        }
        Map<ResourceLocation, List<Resource>> stacks;
        try {
            stacks = manager.listResourceStacks(directory, path -> path.getPath().endsWith(SUFFIX));
        } catch (Throwable t) {
            stacks = Map.of();
        }

        for (Map.Entry<ResourceLocation, Resource> entry : effective.entrySet()) {
            ResourceLocation file = entry.getKey();
            Resource resource = entry.getValue();
            ResourceLocation id = idOf(file, prefix);
            ResourceOrigin origin = new ResourceOrigin(id, resource.sourcePackId(), file.getPath());
            List<Resource> stack = stacks.get(file);
            if (stack != null && stack.size() > 1) {
                // A normal pack override. Informational: the hidden file is not parsed at all, so
                // whatever is wrong with it cannot reject this reload.
                problems.add(ContentProblem.of(section, origin, "", "", ContentSeverity.INFO,
                        "resource_overridden",
                        (stack.size() - 1) + " lower-priority copy/copies of " + file + " are hidden by pack "
                                + resource.sourcePackId()));
            }
            try (BufferedReader reader = resource.openAsReader()) {
                JsonElement json = GsonHelper.fromJson(GSON, reader, JsonElement.class);
                if (json == null || json.isJsonNull()) {
                    problems.add(ContentProblem.of(section, origin, "", "", ContentSeverity.REFUSED,
                            "resource_empty", "the file parsed to nothing"));
                    continue;
                }
                documents.add(new StagedResource(origin, json));
            } catch (JsonParseException e) {
                problems.add(ContentProblem.ofSyntax(section, origin, "", ContentSeverity.REFUSED,
                        "resource_malformed", e));
            } catch (Throwable t) {
                problems.add(ContentProblem.of(section, origin, "", "", ContentSeverity.REFUSED,
                        "resource_unreadable", String.valueOf(t)));
            }
        }
        return new Staged(documents, problems);
    }

    /** {@code <ns>:<directory>/a/b.json} becomes {@code <ns>:a/b}, the id the loaders reported before. */
    static ResourceLocation idOf(ResourceLocation file, String prefix) {
        String path = file.getPath();
        if (path.startsWith(prefix)) {
            path = path.substring(prefix.length());
        }
        if (path.endsWith(SUFFIX)) {
            path = path.substring(0, path.length() - SUFFIX.length());
        }
        return new ResourceLocation(file.getNamespace(), path);
    }

    /** One directory's effective documents plus everything that went wrong reading them. */
    public record Staged(List<StagedResource> documents, List<ContentProblem> problems) {

        public Staged {
            documents = documents == null ? List.of() : List.copyOf(documents);
            problems = problems == null ? List.of() : List.copyOf(problems);
        }

        /** True when a file in this directory could not be read or parsed at all. */
        public boolean fatal() {
            return problems.stream().anyMatch(p -> p.severity().fatal());
        }
    }
}
