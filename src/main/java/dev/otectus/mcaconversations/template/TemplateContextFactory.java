package dev.otectus.mcaconversations.template;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.season.SeasonContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

/**
 * Resolves template variables server-side. When the templates feature is disabled every variable
 * stays unresolved and {@link TemplateEngine} substitutes the per-variable fallback text — a
 * content bug can degrade the line but never break it.
 */
public final class TemplateContextFactory {

    private TemplateContextFactory() {
    }

    public static TemplateContext build(List<TemplateVariable> vars, Entity villager, ServerPlayer player) {
        TemplateContext context = new TemplateContext();
        if (!McaConversationsConfig.COMMON.enableTemplates.get()) {
            return context;
        }
        for (TemplateVariable var : vars) {
            switch (var) {
                case VILLAGER_NAME -> McaCompat.getVillagerName(villager)
                        .ifPresent(name -> context.with(var, Component.literal(name)));
                case SPOUSE_NAME -> McaCompat.getSpouseName(villager)
                        .ifPresent(name -> context.with(var, Component.literal(name)));
                case VILLAGE_NAME -> McaCompat.getHomeVillageName(villager)
                        .ifPresent(name -> context.with(var, Component.literal(name)));
                case LAST_GIFT_ITEM -> ConversationsCapabilities.get(player)
                        .flatMap(data -> data.lastGiftTo(villager.getUUID()))
                        .ifPresent(gift -> {
                            ResourceLocation id = ResourceLocation.tryParse(gift.itemId());
                            Item item = id == null ? null : ForgeRegistries.ITEMS.getValue(id);
                            if (item != null) {
                                context.with(var, Component.translatable(item.getDescriptionId()));
                            }
                        });
                case TIME_OF_DAY -> context.with(var,
                        Component.translatable(timeOfDayKey(player.serverLevel().getDayTime() % 24000L)));
                case PROFESSION_NAME -> McaCompat.getProfessionText(villager)
                        .ifPresent(text -> context.with(var, text));
                case WEATHER -> context.with(var, Component.translatable("mcaconversations.weather."
                        + WorldContext.weatherBucket(McaCompat.isRaining(villager), McaCompat.isThundering(villager))));
                case SEASON -> context.with(var,
                        Component.translatable("mcaconversations.season." + SeasonContext.seasonBucket(villager)));
                case HOLIDAY -> context.with(var,
                        Component.translatable("mcaconversations.holiday." + SeasonContext.holidayBucket(villager)));
            }
        }
        return context;
    }

    /** Day-tick bucket → lang key ({@code assets/mcaconversations/lang}). 0 = dawn, 6000 = noon, 13000 = nightfall. */
    static String timeOfDayKey(long dayTicks) {
        String bucket;
        if (dayTicks < 3000L) {
            bucket = "morning";
        } else if (dayTicks < 9000L) {
            bucket = "day";
        } else if (dayTicks < 13000L) {
            bucket = "evening";
        } else if (dayTicks < 23000L) {
            bucket = "night";
        } else {
            bucket = "morning";
        }
        return "mcaconversations.time_of_day." + bucket;
    }
}
