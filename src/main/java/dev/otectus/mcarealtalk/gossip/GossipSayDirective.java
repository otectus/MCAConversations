package dev.otectus.mcarealtalk.gossip;

import com.google.gson.JsonObject;

/**
 * Parsed form of the {@code realtalk_gossip_say} action JSON — the same query the paired
 * {@code realtalk_gossip} condition uses (they must match so both pick the same event), plus an
 * optional phrase prefix. The told line's key is {@code dialogue.<prefix>.<type>}, args:
 * {@code %1$s} player name (MCA convention), {@code %2$s} subject A, {@code %3$s} subject B.
 */
public record GossipSayDirective(GossipQuery query, String phrasePrefix) {

    public static final String DEFAULT_PREFIX = "realtalk.gossip";

    public static GossipSayDirective fromJson(JsonObject json) {
        GossipQuery query = GossipQuery.fromJson(json);
        String prefix = json != null && json.has("phrase_prefix")
                ? json.get("phrase_prefix").getAsString()
                : DEFAULT_PREFIX;
        return new GossipSayDirective(query, prefix);
    }
}
