package dev.otectus.mcaconversations.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.chat.GreetingPolicy;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.RelationshipRoles;
import dev.otectus.mcaconversations.conversation.Relationships;
import dev.otectus.mcaconversations.conversation.SocialAttitude;
import dev.otectus.mcaconversations.conversation.SocialContact;
import dev.otectus.mcaconversations.conversation.SocialFacts;
import dev.otectus.mcaconversations.conversation.SocialPolicy;
import dev.otectus.mcaconversations.conversation.SocialThresholds;
import dev.otectus.mcaconversations.history.History;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

import java.util.OptionalInt;

/**
 * {@code /conversations social inspect}: what the social model derived for the nearest villager and
 * the player running it, and why (Stability spec §14.1). {@code /conversations social audit}: the
 * scenes other packs added without saying what they assume (§9.3).
 *
 * <p>Read-only and level 2. Ordinary play never shows any of this — hearts stay the one number a
 * player sees — so this is where an operator answers "why did a stranger greet me like an old
 * friend?" without guessing.
 */
public final class SocialCommand {

    private static final double LOOK_RADIUS = 12.0;

    private SocialCommand() {
    }

    public static LiteralArgumentBuilder<CommandSourceStack> subtree() {
        return Commands.literal("social")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("inspect").executes(ctx -> inspect(ctx.getSource())))
                .then(Commands.literal("audit").executes(ctx -> audit(ctx.getSource())));
    }

    /** How many scene ids {@link #audit} names per pack before it just counts the rest. */
    private static final int AUDIT_IDS_SHOWN = 8;

    /**
     * {@code /conversations social audit}: scenes from other packs that declare nothing about what they
     * assume of the pair (Stability spec §9.3). A report, not a verdict — such a scene may be perfectly
     * safe; nobody has said so.
     */
    private static int audit(CommandSourceStack source) {
        java.util.SortedMap<String, java.util.List<String>> unaudited =
                dev.otectus.mcaconversations.scene.SceneCatalogLoader.active().unauditedByPack();
        if (unaudited.isEmpty()) {
            say(source, "Every loaded scene declares what it assumes about the player (a `social` block), "
                    + "or is this mod's own.");
            return Command.SINGLE_SUCCESS;
        }
        int total = unaudited.values().stream().mapToInt(java.util.List::size).sum();
        say(source, total + " scene(s) from " + unaudited.size() + " pack(s) declare no `social` block. They are "
                + "eligible as written; whether they suit a stranger is not checked. DATAPACK.md, "
                + "\"Declaring what a scene assumes\", shows how to add one.");
        unaudited.forEach((pack, ids) -> {
            String shown = String.join(", ", ids.subList(0, Math.min(AUDIT_IDS_SHOWN, ids.size())));
            say(source, "  " + pack + " (" + ids.size() + "): " + shown
                    + (ids.size() > AUDIT_IDS_SHOWN ? ", and " + (ids.size() - AUDIT_IDS_SHOWN) + " more" : ""));
        });
        return Command.SINGLE_SUCCESS;
    }

    private static int inspect(CommandSourceStack source) {
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("Run this as a player, near a villager."));
            return 0;
        }
        Entity villager = nearest(player);
        if (villager == null) {
            source.sendFailure(Component.literal("Stand near an MCA villager."));
            return 0;
        }
        SocialThresholds thresholds = McaConversationsConfig.socialThresholds();
        boolean aware = McaConversationsConfig.relationshipAwareDialogue();
        SocialFacts facts = Relationships.facts(villager, player);
        RelationshipBand band = SocialPolicy.band(facts, thresholds, aware);
        SocialContact contact = SocialPolicy.contact(facts);
        SocialAttitude attitude = SocialPolicy.attitude(band, contact, facts.hearts());
        RelationshipRoles roles = facts.roles();

        say(source, "--- " + McaCompat.getVillagerName(villager).orElse("villager") + " and "
                + player.getGameProfile().getName() + " ---");
        say(source, "  facts     hearts " + facts.hearts()
                + ", contact days " + orDash(facts.contactDays())
                + ", familiarity " + orDash(facts.familiarity())
                + ", trust margin " + orDash(facts.trustMargin())
                + (facts.rupture() ? ", UNREPAIRED RUPTURE" : "")
                + (facts.legacyRecognized() ? ", recognised by the legacy import" : ""));
        say(source, "  roles     " + (roles.spouse() ? "spouse " : "") + (roles.parent() ? "parent " : "")
                + (roles.child() ? "child " : "") + (roles.sibling() ? "sibling " : "")
                + (roles.equals(RelationshipRoles.NONE) ? "none" : ""));
        say(source, "  derived   band " + band.key() + ", contact " + contact.key() + ", attitude " + attitude.key()
                + (aware ? "" : " (relationshipAwareDialogue off: hearts-only bands)"));
        say(source, "  greeting  pool " + ChatModeDispatcher.greetingPool(villager, player, facts)
                + ", frequency x" + String.format("%.2f", GreetingPolicy.frequency(band, contact)));
        say(source, "  farewell  pool " + GreetingPolicy.farewell(band, contact, roles));
        say(source, "  ladder    acquaintance: familiarity " + thresholds.acquaintanceFamiliarity() + ", "
                + thresholds.acquaintanceDays() + " days; friend: " + thresholds.friendHearts() + " hearts, familiarity "
                + thresholds.friendFamiliarity() + ", " + thresholds.friendDays() + " days; confidant: "
                + thresholds.confidantHearts() + " hearts, familiarity " + thresholds.confidantFamiliarity() + ", "
                + thresholds.confidantDays() + " days, trust margin " + thresholds.confidantTrustMargin());
        say(source, "  world     " + (History.legacyImportWorld(source.getServer())
                ? "began before this mod tracked relationships: first exchanges may import an existing one"
                : "began with relationship tracking: every relationship is lived"));
        return Command.SINGLE_SUCCESS;
    }

    private static Entity nearest(ServerPlayer player) {
        AABB box = player.getBoundingBox().inflate(LOOK_RADIUS);
        Entity best = null;
        double bestDistance = Double.MAX_VALUE;
        for (Entity entity : player.level().getEntities(player, box, McaCompat::isMcaVillager)) {
            double distance = entity.distanceToSqr(player);
            if (distance < bestDistance) {
                bestDistance = distance;
                best = entity;
            }
        }
        return best;
    }

    private static String orDash(OptionalInt value) {
        return value.isPresent() ? Integer.toString(value.getAsInt()) : "-";
    }

    private static void say(CommandSourceStack source, String line) {
        source.sendSuccess(() -> Component.literal(line), false);
    }
}
