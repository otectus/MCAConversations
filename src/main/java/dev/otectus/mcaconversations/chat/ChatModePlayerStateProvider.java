package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Attach-time provider for {@link ChatModePlayerState} (mirrors {@code GiftMemoryProvider}). */
public final class ChatModePlayerStateProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    private final ChatModePlayerState data = new ChatModePlayerState();
    private final LazyOptional<ChatModePlayerState> optional = LazyOptional.of(() -> data);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ConversationsCapabilities.CHAT_MODE ? optional.cast() : LazyOptional.empty();
    }

    public void invalidate() {
        optional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        return data.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        data.deserializeNBT(tag);
    }
}
