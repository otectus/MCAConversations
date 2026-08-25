package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversationsConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

/**
 * Per-player chat-mode opt-in, stored as a NeoForge data attachment (mirrors the gift-memory
 * attachment) so it survives relog and death. The feature being enabled in config only makes chat mode <em>available</em>;
 * each player chooses for themselves with {@code /conversations chat on|off}. Until a player chooses,
 * their state is the {@code chatModeDefaultOn} config default.
 */
public final class ChatModePlayerState implements INBTSerializable<CompoundTag> {

    private boolean explicit = false;
    private boolean enabled = false;

    /** Whether chat mode is active for this player (their explicit choice, else the config default). */
    public boolean isEnabled() {
        return explicit ? enabled : McaConversationsConfig.COMMON.chatModeDefaultOn.get();
    }

    /** Records the player's explicit on/off choice. */
    public void setEnabled(boolean value) {
        this.explicit = true;
        this.enabled = value;
    }

    public void copyFrom(ChatModePlayerState other) {
        this.explicit = other.explicit;
        this.enabled = other.enabled;
    }

    /** True when the player has never made an explicit choice. */
    public boolean isExplicit() {
        return explicit;
    }

    /**
     * The stored choice, ignoring the config default.
     *
     * <p>{@link #isEnabled()} falls back to {@code chatModeDefaultOn} when no choice was made, which
     * is the right answer for gameplay but the wrong one for logging or migration reporting.
     */
    public boolean storedChoice() {
        return enabled;
    }

    /** Reads the raw capability/attachment compound. Used by {@code ForgeCapsMigration}. */
    public static ChatModePlayerState fromNbt(CompoundTag tag) {
        ChatModePlayerState state = new ChatModePlayerState();
        state.load(tag);
        return state;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return save();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        load(tag);
    }

    CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("explicit", explicit);
        tag.putBoolean("enabled", enabled);
        return tag;
    }

    void load(CompoundTag tag) {
        this.explicit = tag.getBoolean("explicit");
        this.enabled = tag.getBoolean("enabled");
    }
}
