package dev.otectus.mcaconversations.client.dialogue.dev;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.ClientUtteranceLedger;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.conversation.UtteranceTag;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code /conversations client diagnose} and {@code /conversations client speech-trace on|off}: what
 * this client's dialogue and speech path looks like, for a bug report (feedback remediation WP0).
 *
 * <p>A client command, registered on the physical client only, so it lives here rather than in the
 * common {@code ConversationsCommand} tree; Forge's client handler passes every other
 * {@code /conversations} subcommand on to the server. Operator diagnostics, so the wording is literal
 * English like the server's own. Dialogue text is never printed unless the JVM was started with
 * {@code -Dmcaconversations.diag.text=true}; keys are reported as hashes.
 */
@Mod.EventBusSubscriber(modid = McaConversations.MOD_ID, value = Dist.CLIENT)
public final class ClientDiagnosticsCommand {

    private ClientDiagnosticsCommand() {
    }

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("conversations")
                .then(Commands.literal("client")
                        .then(Commands.literal("diagnose").executes(ClientDiagnosticsCommand::diagnose))
                        .then(Commands.literal("speech-trace")
                                .then(Commands.literal("on").executes(ctx -> trace(ctx, true)))
                                .then(Commands.literal("off").executes(ctx -> trace(ctx, false))))));
    }

    private static int trace(CommandContext<CommandSourceStack> ctx, boolean on) {
        ClientDiagnostics.speechTrace(on);
        say("speech-trace " + (on ? "on: one INFO line per speech hand-off in latest.log" : "off"));
        return Command.SINGLE_SUCCESS;
    }

    private static int diagnose(CommandContext<CommandSourceStack> ctx) {
        for (String line : report()) {
            say(line);
        }
        return Command.SINGLE_SUCCESS;
    }

    /** The report, line by line. Also written to the log so it can be attached as text. */
    static List<String> report() {
        List<String> lines = new ArrayList<>();
        lines.add("MCA: Conversations client diagnose");
        lines.add("  mca root=" + McaHandles.resolution().root() + " status=" + McaHandles.resolution().status()
                + " speech-binding=" + McaHandles.Speech.resolution().status()
                + (McaHandles.Speech.resolution().unresolvedRequired().isEmpty() ? ""
                        : " missing=" + McaHandles.Speech.resolution().unresolvedRequired()));
        StringBuilder hooks = new StringBuilder("  hooks fired:");
        for (ClientDiagnostics.Hook hook : ClientDiagnostics.Hook.values()) {
            hooks.append(' ').append(hook.mixin()).append('=').append(ClientDiagnostics.hasFired(hook));
        }
        lines.add(hooks.toString());
        Minecraft minecraft = Minecraft.getInstance();
        boolean voicePack = false;
        try {
            voicePack = minecraft.getResourceManager().listPacks()
                    .anyMatch(pack -> pack.packId().contains("MCAVoices"));
        } catch (Throwable ignored) {
            // A diagnostic, not a requirement.
        }
        String screen = minecraft.screen == null ? "none" : minecraft.screen.getClass().getSimpleName();
        lines.add("  style=" + ClientChoiceController.dialogueMenuStyle() + " screen=" + screen
                + " townstead=" + ModList.get().isLoaded("townstead")
                + " onlineTTS=" + McaHandles.onlineTtsEnabled() + " model=" + McaHandles.Speech.onlineTtsModel()
                + " voicePack=" + voicePack + " chatModeSpeech=" + McaConversationsConfig.chatModeSpeech()
                + " speechTrace=" + ClientDiagnostics.speechTrace());
        List<ClientUtteranceLedger.Entry> recent = ClientUtteranceLedger.get().recent(8);
        lines.add("  ledger: " + ClientUtteranceLedger.get().size() + " held, last displayed="
                + ClientUtteranceLedger.get().lastDisplayed().map(Object::toString).orElse("none"));
        boolean text = ClientDiagnostics.textAllowed();
        for (ClientUtteranceLedger.Entry entry : recent) {
            UtteranceTag.Id id = entry.id();
            lines.add("    " + id.identity().toString().substring(0, 8) + "/" + id.seq() + " " + id.kind()
                    + " key#" + keyHash(entry.display()) + " seen=" + entry.seenBy()
                    + (text ? " text=\"" + entry.speechText() + "\"" : ""));
        }
        lines.forEach(line -> McaConversations.LOGGER.info("[diagnose] {}", line));
        return lines;
    }

    /** A stable hash of the line's lang key, so a report can match keys without carrying text. */
    private static String keyHash(Component display) {
        Component carrier = UtteranceTag.locate(display).map(UtteranceTag.Located::carrier).orElse(display);
        if (carrier != null && UtteranceTag.speechCarrier(carrier).getContents() instanceof TranslatableContents tc) {
            return Integer.toHexString(tc.getKey().hashCode());
        }
        return "literal";
    }

    private static void say(String line) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.displayClientMessage(Component.literal(line), false);
        }
    }
}
