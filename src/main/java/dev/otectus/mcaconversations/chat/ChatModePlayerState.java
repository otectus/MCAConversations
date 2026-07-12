package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversationsConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Per-player chat-mode opt-in, stored as a Forge capability (mirrors the gift-memory capability) so it
 * survives relog and death. The feature being enabled in config only makes chat mode <em>available</em>;
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

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("explicit", explicit);
        tag.putBoolean("enabled", enabled);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.explicit = tag.getBoolean("explicit");
        this.enabled = tag.getBoolean("enabled");
    }
}
