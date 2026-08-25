package dev.otectus.mcaconversations.template;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.gift.ConversationsAttachments;
import dev.otectus.mcaconversations.season.SeasonContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;

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
                case LAST_GIFT_ITEM -> ConversationsAttachments.giftMemory(player)
                        .lastGiftTo(villager.getUUID())
                        .ifPresent(gift -> {
                            ResourceLocation id = ResourceLocation.tryParse(gift.itemId());
                            // getOptional, not getValue: an item id from a mod that has since been
                            // removed must leave the variable unresolved so the template falls back
                            // to its existing text, never resolve to AIR.
                            if (id == null) {
                                return;
                            }
                            BuiltInRegistries.ITEM.getOptional(id).ifPresent(item ->
                                    context.with(var, Component.translatable(item.getDescriptionId())));
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
                // MCA: Reputation variables (spec 30.7). Each is left unset when the mod is absent or
                // nothing resolves, so TemplateEngine substitutes the variable's neutral fallback and
                // the line still reads as a sentence.
                case REPUTATION_TIER -> {
                    String tier = dev.otectus.mcaconversations.compat.ReputationBridge
                            .tierId(player, villager);
                    if (!tier.isEmpty()) {
                        context.with(var, Component.translatable("mcareputation.tier." + tier));
                    }
                }
                case REPUTATION_SCORE -> {
                    if (dev.otectus.mcaconversations.compat.ReputationBridge.isAvailable()) {
                        context.with(var, Component.literal(Integer.toString(
                                dev.otectus.mcaconversations.compat.ReputationBridge.score(player, villager))));
                    }
                }
                case REPUTATION_VILLAGE -> McaCompat.getHomeVillageName(villager)
                        .ifPresent(name -> context.with(var, Component.literal(name)));
                case REPUTATION_RECENT_DEED -> {
                    var queries = dev.otectus.mcaconversations.compat.ReputationBridge.queries();
                    if (queries != null && dev.otectus.mcaconversations.compat.ReputationBridge.isAvailable()) {
                        try {
                            queries.recentKnownDeed(player, villager)
                                    .ifPresent(deed -> context.with(var, deed));
                        } catch (Throwable t) {
                            dev.otectus.mcaconversations.McaConversations.LOGGER.debug(
                                    "reputation_recent_deed failed; using the fallback", t);
                        }
                    }
                }
                case REPUTATION_TITLE -> {
                    // Titles are shown by the standing screen and the Journal; a dialogue line wanting
                    // one asks for the tier's name, which is the badge a villager would actually use.
                    String tier = dev.otectus.mcaconversations.compat.ReputationBridge
                            .tierId(player, villager);
                    if (!tier.isEmpty()) {
                        context.with(var, Component.translatable("mcareputation.tier." + tier));
                    }
                }
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
