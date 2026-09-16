package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonElement;

/**
 * One effective JSON document, with its provenance attached before it was parsed.
 *
 * <p>Effective means the resource manager's pack stack has already chosen the winner for this
 * resource id: an identical-resource override hides the lower pack's file entirely, so a malformed
 * file underneath a valid override is never seen and never rejects the bundle. Distinct resources
 * that merge into one id — two files declaring the same topic, for instance — remain two staged
 * resources, and their collision is a diagnostic with both origins.
 */
public record StagedResource(ResourceOrigin origin, JsonElement json) {

    public net.minecraft.resources.ResourceLocation resource() {
        return origin.resource();
    }
}
