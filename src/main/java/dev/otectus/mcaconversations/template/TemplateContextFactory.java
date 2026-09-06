package dev.otectus.mcaconversations.template;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.CapitalCourtView;
import dev.otectus.mcaconversations.compat.CapitalRelationView;
import dev.otectus.mcaconversations.compat.CapitalStandingView;
import dev.otectus.mcaconversations.compat.CapitalsBridge;
import dev.otectus.mcaconversations.compat.CapitalsCompat;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.context.CapitalContextSource;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.season.SeasonContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
                // MCA: Capitals variables. Resolved together because they all need the same court,
                // and every one of them is left unset when Capitals is absent, the villager belongs
                // to no capital, or the office being named is vacant.
                case CAPITAL_NAME, SOVEREIGN_NAME, SOVEREIGN_TITLE, HEIR_NAME, HOUSE_NAME,
                        HOUSE_WORDS, VILLAGER_TITLE, RIVAL_CAPITAL_NAME, ALLY_CAPITAL_NAME ->
                        capitalVariable(context, var, villager);
            }
        }
        return context;
    }

    /**
     * One capital variable, resolved through {@link CapitalsBridge}.
     *
     * <p>Wrapped in a catch because this runs while a line is being built: a variable that cannot be
     * resolved must degrade to its fallback text, never take the line with it.
     */
    private static void capitalVariable(TemplateContext context, TemplateVariable var,
                                        Entity villager) {
        if (villager == null || !CapitalsCompat.isActive()
                || !(villager.level() instanceof ServerLevel level)) {
            return;
        }
        try {
            CapitalsBridge bridge = CapitalsBridge.Holder.get();
            Optional<CapitalCourtView> resolved = CapitalContextSource.courtOf(bridge, level, villager);
            if (resolved.isEmpty()) {
                return;
            }
            CapitalCourtView court = resolved.get();
            switch (var) {
                case CAPITAL_NAME -> literal(context, var, court.name());
                case SOVEREIGN_NAME -> court.sovereign()
                        .ifPresent(id -> literal(context, var, bridge.displayName(level, court, id)));
                case SOVEREIGN_TITLE -> {
                    // By the sovereign's gender, not the speaker's — a queen is a queen to everyone.
                    if (court.sovereign().isPresent() || court.playerSovereign()) {
                        context.with(var, Component.translatable("mcaconversations.capital.title."
                                + (court.sovereignFemale() ? "queen" : "king")));
                    }
                }
                case HEIR_NAME -> court.heir()
                        .ifPresent(id -> literal(context, var, bridge.displayName(level, court, id)));
                case HOUSE_NAME -> literal(context, var,
                        standing(bridge, level, court, villager).houseName());
                case HOUSE_WORDS -> literal(context, var,
                        standing(bridge, level, court, villager).houseWords());
                case VILLAGER_TITLE -> {
                    String titleId = standing(bridge, level, court, villager).titleId();
                    if (!titleId.isBlank() && !"none".equals(titleId)) {
                        context.with(var,
                                Component.translatable("mcaconversations.capital.title." + titleId));
                    }
                }
                case RIVAL_CAPITAL_NAME -> firstRelation(bridge, level, court,
                        CapitalRelationView::atWar).ifPresent(name -> literal(context, var, name));
                case ALLY_CAPITAL_NAME -> firstRelation(bridge, level, court,
                        CapitalRelationView::allied).ifPresent(name -> literal(context, var, name));
                default -> {
                    // Unreachable: the caller only routes the nine capital variables here.
                }
            }
        } catch (Throwable t) {
            dev.otectus.mcaconversations.McaConversations.LOGGER.debug(
                    "capital template variable {} failed; using the fallback", var.jsonName(), t);
        }
    }

    private static CapitalStandingView standing(CapitalsBridge bridge, ServerLevel level,
                                                CapitalCourtView court, Entity villager) {
        return bridge.standingOf(level, court, villager.getUUID(), villager);
    }

    /** The first capital this one stands in the given relation to, by name. */
    private static Optional<String> firstRelation(CapitalsBridge bridge, ServerLevel level,
                                                  CapitalCourtView court,
                                                  Predicate<CapitalRelationView> matches) {
        return bridge.relationsOf(level, court).stream()
                .filter(matches)
                .map(CapitalRelationView::otherName)
                .filter(name -> !name.isBlank())
                .findFirst();
    }

    /** Sets a literal value, or leaves the variable unset so its fallback text is used. */
    private static void literal(TemplateContext context, TemplateVariable var, String value) {
        if (value != null && !value.isBlank()) {
            context.with(var, Component.literal(value));
        }
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
