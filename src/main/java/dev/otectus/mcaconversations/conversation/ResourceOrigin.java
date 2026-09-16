package dev.otectus.mcaconversations.conversation;

import net.minecraft.resources.ResourceLocation;

/**
 * Where one staged JSON document actually came from: its resource id, the pack that won it, and the
 * physical path inside that pack.
 *
 * <p>The pack id is the field the old inherited {@code prepare} could not supply (boundary note §6),
 * so it is recorded explicitly as {@link #UNKNOWN_PACK} rather than guessed when a caller genuinely
 * does not have it — a consumer can then tell "not available on this path" from "no problem here".
 */
public record ResourceOrigin(ResourceLocation resource, String pack, String path) {

    public static final String UNKNOWN_PACK = "<unknown pack>";

    public ResourceOrigin {
        pack = pack == null || pack.isBlank() ? UNKNOWN_PACK : pack;
        path = path == null || path.isBlank() ? (resource == null ? "<unknown>" : resource.toString()) : path;
    }

    /** An origin for a document whose pack is not knowable on the path that produced it. */
    public static ResourceOrigin unknownPack(ResourceLocation resource) {
        return new ResourceOrigin(resource, UNKNOWN_PACK, null);
    }

    @Override
    public String toString() {
        return resource + " (pack " + pack + ")";
    }
}
