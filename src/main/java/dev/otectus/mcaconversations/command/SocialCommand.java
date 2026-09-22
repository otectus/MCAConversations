package dev.otectus.mcaconversations.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.otectus.mcaconversations.McaConversationsConfig;
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
 * the player running it, and why (Stability spec §14.1).
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
                .then(Commands.literal("inspect").executes(ctx -> inspect(ctx.getSource())));
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
        say(source, "  greeting  pool " + GreetingPolicy.pool(band, contact)
                + ", frequency x" + String.format("%.2f", GreetingPolicy.frequency(band, contact)));
        say(source, "  ladder    acquaintance: familiarity " + thresholds.acquaintanceFamiliarity() + ", "
                + thresholds.acquaintanceDays() + " days; friend: " + thresholds.friendHearts() + " hearts, familiarity "
                + thresholds.friendFamiliarity() + ", " + thresholds.friendDays() + " days; confidant: "
                + thresholds.confidantHearts() + " hearts, familiarity " + thresholds.confidantFamiliarity() + ", "
                + thresholds.confidantDays() + " days, trust margin " + thresholds.confidantTrustMargin());
        say(source, "  world     " + (History.legacyImportWorld(source.getServer())
                ? "predates 1.8.0: first exchanges may import an existing relationship"
                : "began under 1.8.0: every relationship is lived"));
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
