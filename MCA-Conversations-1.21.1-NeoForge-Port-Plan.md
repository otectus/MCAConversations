# MCA: Conversations — Minecraft 1.21.1 NeoForge Port Plan

## Document status

This is an implementation plan, not a speculative checklist. It is written for a coding agent that will perform the complete port, preserve existing worlds and content, validate every MCA-internal hook, and produce a releasable NeoForge artifact.

The source baseline inspected for this plan is:

- MCA: Conversations repository: https://github.com/otectus/MCAConversations
- Source commit: 89edad20fa01b62a8e3e765c8bbf1a5b6df5e8a1, dated 2026-08-25
- Current mod version: 1.2.1
- Current platform: Minecraft 1.20.1, Forge 47.4.10, Java 17
- Current MCA compile target: 7.7.0-beta.2+1.20.1
- Current source inventory: 128 main Java files, 65 test Java files, and 235 JSON resources
- Content inventory: 173 MCA dialogue JSON files, 13 chat-intent JSON files, one conversation catalog, one interiority profile, and 46 language files across 23 asset namespaces

The target baseline is:

| Component | Target |
|---|---|
| Minecraft | 1.21.1 only |
| Loader | NeoForge 21.1.234 |
| Java | 21 |
| Build plugin | ModDevGradle 2.0.141 |
| Gradle wrapper | 9.6.1 |
| Mappings | Parchment 2024.11.17 for Minecraft 1.21.1 |
| MCA Reborn | 7.7.36+1.21.1, NeoForge artifact |
| MCA Maven coordinate | net.conczin.mca:mca-neoforge:7.7.36+1.21.1 |
| Mixin runtime | 0.8.7 as supplied by NeoForge 21.1 |
| Recommended Conversations release line | 2.0.0 or another new, non-colliding loader/version line |

MCA 7.7.36 is the current published 1.21.1 NeoForge release at the time of this plan. The published jar is the authoritative binary contract. The upstream 1.21.1 source branch is supporting evidence, not a substitute for checking the exact resolved jar.

Primary references:

- MCA source, 1.21.1 branch: https://github.com/Luke100000/minecraft-comes-alive/tree/1.21.1
- MCA 7.7.36 NeoForge release: https://www.curseforge.com/minecraft/mc-mods/minecraft-comes-alive-reborn/files/8658484
- NeoForge 1.21.1 payload networking: https://docs.neoforged.net/docs/1.21.1/networking/payload/
- NeoForge 1.21.1 data attachments: https://docs.neoforged.net/docs/1.21.1/datastorage/attachments/
- NeoForge 1.21.1 SavedData: https://docs.neoforged.net/docs/1.21.1/datastorage/saveddata/
- NeoForge 1.21.1 configuration: https://docs.neoforged.net/docs/1.21.1/misc/config/

## 1. Required outcome

The finished port must:

1. Build reproducibly with Java 21 and NeoForge 21.1.x without ForgeGradle, Mixingradle, Architectury, sibling class-directory assumptions, or any 1.20.1 Forge runtime dependency.
2. Load on a dedicated server and a client with MCA Reborn 7.7.36 for NeoForge.
3. Preserve all mod IDs, resource IDs, dialogue IDs, translation keys, config keys, SavedData filenames, and existing NBT schemas unless this plan explicitly identifies a migration.
4. Preserve all core features: dialogue hub modes, topics, branching, guarded affection, progress, dispositions, templates, gift memory, conversation states, gossip, weather/seasons/holidays, chat mode, typing attention, commands, locale overlays, and optional suite integration.
5. Migrate existing 1.20.1 per-player Forge capability data into NeoForge attachments on first load.
6. Preserve the three world-global SavedData files without renaming or resetting them.
7. Retarget every MCA mixin against the exact 7.7.36 bytecode and remove hooks that are no longer necessary.
8. Pass the full existing unit/content test suite plus new port, migration, payload, mixin, jar, client, and dedicated-server checks.
9. Produce a jar containing only MCA: Conversations code and resources. MCA, NeoForge, optional suite mods, and Architectury must not be shaded into it.

The port is not complete if it merely compiles. It is complete only after the runtime and upgrade matrices in this document pass.

## 2. Non-negotiable invariants

Keep these values stable:

- Mod ID: mcaconversations
- Attachment IDs after migration: mcaconversations:gift_memory and mcaconversations:chat_mode
- SavedData filenames:
  - mcaconversations_dispositions
  - mcaconversations_gossip
  - mcaconversations_progress
- Dialogue resource directory: data/mcaconversations/dialogues
- Existing question, answer, action, condition, memory, progress, disposition, and translation identifiers
- Config property names and their defaults
- Default signed-chat behavior: normal chat is not canceled or rewritten unless the existing experimental local-chat option is enabled

Do not make the build pass by deleting a feature, suppressing a source set, weakening a test, catching linkage errors around all startup code, or widening a dependency range that has not been tested.

## 3. High-confidence API findings

### 3.1 MCA is no longer a Forgix-relocated universal jar

Every source import under forge.net.mca must become net.conczin.mca. MCA 1.21.1 publishes a loader-specific NeoForge jar named mca-neoforge. Remove the old comments and compatibility assumptions about a forge-prefixed relocated package.

### 3.2 MCA networking was replaced

The old classes forge.net.mca.cobalt.network.NetworkHandler and Message do not exist in the target. MCA now uses net.conczin.mca.network.Network and HandleablePayload.

Relevant target signatures are:

~~~java
Network.sendToPlayer(HandleablePayload payload, ServerPlayer player)

record InteractionDialogueQuestionResponse(Component questionText, boolean silent)
record InteractionDialogueResponse(String question, List<String> answers)
record AnalysisResults(Analysis analysis)
record VillagerMessage(Component prefix, Component message, UUID uuid)

InteractionDialogueMessage.handleServer(ServerPlayer player)
~~~

Consequences:

- Rewrite the current NetworkHandlerMixin to target Network.sendToPlayer.
- Replace field/getter access with record accessors such as questionText(), silent(), question(), and answers().
- Retarget InteractionDialogueMessageMixin from receive to handleServer.
- Change McaCompat.sayInDialogue to construct InteractionDialogueQuestionResponse in Component-then-boolean order.

### 3.3 The VillagerMessage workaround is obsolete

Delete client/VillagerMessageMixin.java and remove it from mcaconversations.mixins.json. In MCA 1.21.1, VillagerMessage carries Component objects directly. MCA’s ClientHandlerImpl copies prefix(), appends message(), and passes the same message() component to speech. The old JSON reparse and multiple-random-draw bug no longer exists, and the old constructors/getters targeted by the mixin are gone.

### 3.4 The gift hook must move to the actual accepted branch

The current BreedableRelationshipMixin injects at the head of acceptGift and therefore records inventory-full, failed, or desaturated/rejected gifts as accepted. MCA 1.21.1 performs all of those checks inside acceptGift and consumes the item only in the final success branch.

Retarget the hook to the sole ItemStack.split(1) invocation inside acceptGift, immediately before the split. Pass GiftTracker a copy with count one. This records exactly the item MCA is about to accept.

Use an exact descriptor and a required injection during development:

~~~java
@Inject(
    method = "acceptGift",
    at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/world/item/ItemStack;split(I)Lnet/minecraft/world/item/ItemStack;",
        shift = At.Shift.BEFORE,
        remap = true
    ),
    require = 1,
    remap = false
)
~~~

If the release policy deliberately keeps fail-soft mixins, lower require only after the startup audit can report a missing critical hook. Do not silently ship a gift hook that never applies.

### 3.5 Question.getValidAnswers changed shape

The target signature is:

~~~java
List<String> getValidAnswers(ServerPlayer player, VillagerEntityMCA villager)
~~~

QuestionMixin must include both method arguments before CallbackInfoReturnable. Continue modifying only the returned main-question list and only the conversations answer.

### 3.6 Player capabilities should become attachments

NeoForge 1.21.1 data attachments are the correct replacement for the two Forge player capabilities. Serializable entity attachments can opt into copyOnDeath, eliminating the provider, LazyOptional, attach, invalidation, and clone lifecycle code.

### 3.7 SavedData signatures changed

Each SavedData loader and saver now receives HolderLookup.Provider, and computeIfAbsent takes a SavedData.Factory plus the filename. The three existing filenames and store payloads must remain unchanged.

### 3.8 Event changes are not only package renames

Important target mappings:

| Forge 1.20.1 | NeoForge 1.21.1 |
|---|---|
| net.minecraftforge.eventbus.api | net.neoforged.bus.api |
| net.minecraftforge.fml.common.Mod | net.neoforged.fml.common.Mod |
| Mod.EventBusSubscriber | standalone EventBusSubscriber |
| MinecraftForge.EVENT_BUS | NeoForge.EVENT_BUS |
| TickEvent.ServerTickEvent with END phase | ServerTickEvent.Post |
| TickEvent.ClientTickEvent with END phase | ClientTickEvent.Post |
| LivingHurtEvent | LivingIncomingDamageEvent |
| ForgeConfigSpec | ModConfigSpec |
| ForgeRegistries.ITEMS | BuiltInRegistries.ITEM |
| SimpleChannel | CustomPacketPayload and PayloadRegistrar |
| Forge capabilities | NeoForge data attachments |

ServerChatEvent, AddReloadListenerEvent, RegisterCommandsEvent, PlayerEvent login/logout events, LivingDeathEvent, and ServerStartedEvent still exist under net.neoforged.neoforge.event packages. ServerChatEvent still supplies getPlayer(), getRawText(), getMessage(), and cancellation.

## 4. Execution strategy

Create a dedicated branch, recommended name port/1.21.1-neoforge. Do not rewrite the 1.20.1 branch in place. The implementation should be a sequence of reviewable commits in the phase order below.

Each phase has an exit gate. Do not start the next phase while its predecessor has unexplained compile errors, failing tests, missing evidence, or a runtime regression.

Recommended commit sequence:

1. chore(port): capture 1.20.1 baseline and binary evidence
2. build(neoforge): move to Java 21 and ModDevGradle
3. refactor(neoforge): migrate loader APIs, events, config, and registries
4. refactor(storage): replace capabilities and port SavedData
5. refactor(network): add NeoForge payload networking
6. refactor(mca): bind to MCA 7.7.36 NeoForge APIs
7. fix(mixins): retarget and audit MCA hooks
8. compat: restore NeoForge suite integrations
9. test: add upgrade, runtime, content, and artifact gates
10. docs/release: document the NeoForge line and produce the release candidate

## 5. Phase 0 — Baseline, artifacts, and binary evidence

### Tasks

1. Check out source commit 89edad20fa01b62a8e3e765c8bbf1a5b6df5e8a1 and create the port branch.
2. With Java 17 and the current sibling builds available, run:

~~~bash
./gradlew clean test build
~~~

3. Record the exact number of tests discovered and passed. Preserve all generated reports as baseline evidence.
4. Create two disposable 1.20.1 worlds:
   - a minimal world with one player and one MCA villager;
   - a populated fixture with gifts, chat-mode choice, disposition values, gossip, and progress.
5. In the populated fixture, deliberately create:
   - gift memory for at least two villagers;
   - an explicit chat-mode off or on choice that differs from the config default;
   - non-empty mcaconversations_dispositions.dat;
   - non-empty mcaconversations_gossip.dat;
   - non-empty mcaconversations_progress.dat.
6. Copy the complete worlds before opening them in 1.21.1. Never test an upgrade on the only copy.
7. Resolve the target MCA dependency and record its exact file hash.
8. Inspect the resolved 7.7.36 jar with jar and javap or ASM. Record exact class names, method descriptors, access flags, and relevant instructions for:
   - net.conczin.mca.network.Network.sendToPlayer;
   - net.conczin.mca.network.c2s.InteractionDialogueMessage.handleServer;
   - net.conczin.mca.resources.Dialogues.getQuestion and selectAnswer;
   - net.conczin.mca.resources.data.dialogue.Question.getValidAnswers;
   - net.conczin.mca.entity.ai.BreedableRelationship.acceptGift;
   - net.conczin.mca.MCAClient.useExpandedPersonalityTranslations;
   - the packet records named in section 3.2.
9. Save this evidence in docs/PORT-1.21.1-EVIDENCE.md. Include the MCA jar SHA-256. The resolved jar, not a moving branch, is the final authority for mixin descriptors.

### Exit gate

- The 1.20.1 baseline build is green or every pre-existing failure is documented.
- Both upgrade worlds are backed up.
- The exact MCA target jar and all mixin target descriptors are recorded.
- The agent can explain every planned mixin target from the resolved binary.

## 6. Phase 1 — Replace the build and metadata

### 6.1 gradle.properties

Replace the Forge properties with:

~~~properties
minecraft_version=1.21.1
minecraft_version_range=[1.21.1,1.21.2)
java_version=21

moddev_version=2.0.141
neoforge_version=21.1.234
neoforge_version_range=[21.1.234,21.2)
loader_version_range=[4,)

parchment_minecraft=1.21.1
parchment_version=2024.11.17

mca_version=7.7.36+1.21.1
mca_version_range=[7.7.36,7.7.37)
~~~

Use a new release version, recommended 2.0.0, so Forge and NeoForge artifacts cannot collide. Remove forge_version, mapping_channel, mapping_version, and architectury_version.

The initial narrow MCA runtime range is intentional: this mod mixes into MCA internals. Widen it only after running the full compatibility matrix against each additional MCA release.

### 6.2 Gradle wrapper and settings

- Update the wrapper to Gradle 9.6.1, matching the inspected MCA 1.21.1 build.
- Update the Foojay resolver to 1.0.0.
- Remove the MinecraftForge plugin repository.
- Keep gradlePluginPortal and Maven Central.
- Keep rootProject.name unchanged.
- Enable configuration cache only after all custom verification tasks prove compatible.

### 6.3 build.gradle

Replace ForgeGradle and Mixingradle with net.neoforged.moddev version 2.0.141.

Required structure:

~~~groovy
plugins {
    id "java"
    id "eclipse"
    id "idea"
    id "net.neoforged.moddev" version "2.0.141"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

neoForge {
    version = "21.1.234"
    parchment {
        minecraftVersion = "1.21.1"
        mappingsVersion = "2024.11.17"
    }
    runs {
        client { client() }
        server { server() }
        gameTestServer { type = "gameTestServer" }
        data { data() }
    }
    mods {
        mcaconversations {
            sourceSet sourceSets.main
        }
    }
    unitTest {
        enable()
        testedMod = mods.mcaconversations
    }
}
~~~

Repositories:

- Add https://maven.conczin.net/Artifacts for official MCA artifacts.
- Keep the Sponge repository for Mixin compile/annotation-processor artifacts.
- Add the Modrinth Maven only as a documented fallback if the exact Conczin artifact cannot be resolved.
- Scope custom repositories to their groups where practical.

Dependencies:

~~~groovy
implementation "net.conczin.mca:mca-neoforge:" + mca_version
compileOnly "org.spongepowered:mixin:0.8.7"
annotationProcessor "org.spongepowered:mixin:0.8.7:processor"
~~~

Keep the existing JUnit dependencies initially. Upgrade JUnit separately only if required by Gradle 9.6. Do not combine a test-framework migration with the loader port without a reason.

Remove:

- the buildscript block for Mixingradle;
- net.minecraftforge.gradle;
- apply plugin: org.spongepowered.mixin;
- minecraft mappings and Forge run configuration;
- fg.deobf;
- the minecraft dependency configuration;
- Architectury repository and dependency;
- reobfJar finalization;
- MixinGradle’s mixin block and SRG refmap-remapping system properties;
- raw ../MCAQuests/build/classes and ../MCAReputation/build/classes dependencies.

Retain resource expansion, generated-resource directories if actually used, UTF-8 compilation, manifest metadata, JUnit Platform, and jar-content verification.

Add:

- options.release = 21 for JavaCompile;
- a reproducible archive configuration;
- dependency locking or an equivalent exact dependency report;
- a verifyJarContents task described in phase 9.

### 6.4 Metadata

Delete src/main/resources/META-INF/mods.toml and create META-INF/neoforge.mods.toml.

Use type = "required" or type = "optional"; mandatory is the old Forge spelling.

Initial dependency policy:

~~~toml
modLoader="javafml"
loaderVersion="[4,)"
license="GPL-3.0-only"

[[mixins]]
config="mcaconversations.mixins.json"

[[mods]]
modId="mcaconversations"
version="2.0.0"
displayName="MCA: Conversations"
authors="otectus"
description='''Deeper, less repetitive villager conversations for Minecraft Comes Alive: Reborn.'''

[[dependencies.mcaconversations]]
modId="neoforge"
type="required"
versionRange="[21.1.234,21.2)"
ordering="NONE"
side="BOTH"

[[dependencies.mcaconversations]]
modId="minecraft"
type="required"
versionRange="[1.21.1,1.21.2)"
ordering="NONE"
side="BOTH"

[[dependencies.mcaconversations]]
modId="mca"
type="required"
versionRange="[7.7.36,7.7.37)"
ordering="AFTER"
side="BOTH"
~~~

Add optional MCA: Quests and MCA: Reputation entries only after their NeoForge versions and ranges are known. Do not retain the old permissive Forge ranges.

Update pack.mcmeta from resource pack format 15 to the Minecraft 1.21.1 resource format 34. Then explicitly validate both client resources and server data reloads because Minecraft 1.21.1’s data-pack format is 48.

### Exit gate

- Gradle resolves NeoForge and the exact MCA artifact under Java 21.
- No ForgeGradle, Mixingradle, Architectury, fg.deobf, or reobfJar configuration remains.
- processResources produces neoforge.mods.toml with resolved values.
- A compile attempt reaches Java source errors rather than build-tool configuration errors.

## 7. Phase 2 — Loader APIs, entrypoint, config, events, registries

### 7.1 McaConversations.java

Use constructor injection:

~~~java
public McaConversations(IEventBus modBus, ModContainer container)
~~~

Inside the constructor:

1. Register COMMON_SPEC and CLIENT_SPEC with ModContainer.registerConfig.
2. Register ConversationsAttachments on modBus.
3. Add the FMLCommonSetupEvent listener.
4. Add ConversationsNetwork.register as a RegisterPayloadHandlersEvent listener.
5. Keep all MCA and optional-compat registration work in common setup enqueueWork.

Remove ModLoadingContext and FMLJavaModLoadingContext.

### 7.2 McaConversationsConfig.java

- Replace ForgeConfigSpec with net.neoforged.neoforge.common.ModConfigSpec.
- Preserve every config section, property name, type, range, default, and comment.
- Preserve COMMON and CLIENT file identities.
- Add a regression test that loads representative old TOML values and confirms the same getters resolve them.

### 7.3 Event subscribers

Change McaConversations event classes to:

~~~java
@EventBusSubscriber(modid = McaConversations.MOD_ID)
~~~

Use net.neoforged.bus.api.SubscribeEvent and EventPriority.

In ConversationsEvents.java:

- Delete onAttachCapabilities and onClone after attachments are complete.
- Keep login/logout cleanup and player-name synchronization.
- Keep ServerChatEvent handling and the safe server.execute hop. Treat the callback as async-capable even when a particular server invokes it on its main thread.
- Replace LivingHurtEvent with LivingIncomingDamageEvent and continue reading getSource().getEntity().
- Replace TickEvent.ServerTickEvent and phase checks with ServerTickEvent.Post.
- Keep AddReloadListenerEvent, RegisterCommandsEvent, LivingDeathEvent, PlayerEvent login/logout, and ServerStartedEvent under NeoForge packages.
- Ensure no client type is imported into the common subscriber.

In ChatTypingTracker.java:

- Use net.neoforged.api.distmarker.Dist.
- Use standalone EventBusSubscriber with value = Dist.CLIENT.
- Change the handler parameter to ClientTickEvent.Post and delete the old phase condition.
- Replace SimpleChannel.sendToServer with PacketDistributor.sendToServer.

In ConversationsQuestsCompat.java:

- Replace MinecraftForge.EVENT_BUS with NeoForge.EVENT_BUS.
- Use NeoForge event annotations in ConversationsQuestsEvents.

### 7.4 Registries and resource locations

Replace every two-argument ResourceLocation constructor with ResourceLocation.fromNamespaceAndPath. Retain ResourceLocation.tryParse for untrusted or optional strings.

Known constructor sites:

- ConversationsNetwork
- the former ConversationsCapabilities registry IDs
- ConversationsQuestsCompat
- ConversationsReputationCompat

Replace:

- ForgeRegistries.ITEMS.getKey with BuiltInRegistries.ITEM.getKey in GiftTracker;
- ForgeRegistries.ITEMS.getValue with BuiltInRegistries.ITEM.getOptional in TemplateContextFactory.

Do not turn an unknown gift item ID into AIR. Leave the template variable unresolved so its existing fallback text is used.

### 7.5 Mechanical completion sweep

Run:

~~~bash
rg -n "net\.minecraftforge|forge\.net\.mca|ForgeRegistries|SimpleChannel|LazyOptional|CapabilityManager|new ResourceLocation" src
~~~

At the end of the port, the only intentional old-Forge reference should be the literal NBT key ForgeCaps in the one-time migration code and historical documentation/tests that explicitly exercise migration.

### Exit gate

- Config, event, registry, and entrypoint source compiles.
- No phase-based old tick handler remains.
- A dedicated-server classloading inspection shows no net.minecraft.client reference from common classes.

## 8. Phase 3 — Player attachments and persistence migration

### 8.1 Replace the capability registry

Rename ConversationsCapabilities.java to ConversationsAttachments.java and implement:

~~~java
private static final DeferredRegister<AttachmentType<?>> TYPES =
    DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, McaConversations.MOD_ID);

public static final Supplier<AttachmentType<GiftMemoryData>> GIFT_MEMORY =
    TYPES.register("gift_memory", () ->
        AttachmentType.serializable(GiftMemoryData::new).copyOnDeath().build());

public static final Supplier<AttachmentType<ChatModePlayerState>> CHAT_MODE =
    TYPES.register("chat_mode", () ->
        AttachmentType.serializable(ChatModePlayerState::new).copyOnDeath().build());
~~~

Expose direct non-null accessors:

~~~java
public static GiftMemoryData giftMemory(Player player)
public static ChatModePlayerState chatMode(Player player)
~~~

Update every caller to stop treating these values as optional. getData creates the default attachment lazily.

Delete:

- gift/GiftMemoryProvider.java
- chat/ChatModePlayerStateProvider.java
- all LazyOptional invalidation code
- AttachCapabilitiesEvent handling
- PlayerEvent.Clone handling for these values

copyOnDeath preserves both attachments across ordinary death. NeoForge already preserves serializable attachments when returning from the End; do not duplicate them with a clone handler.

No client sync is required: gift memory and chat-mode state are consumed and mutated on the logical server.

### 8.2 Update INBTSerializable

Keep GiftMemoryData and ChatModePlayerState implementing NeoForge’s INBTSerializable, but update both methods:

~~~java
CompoundTag serializeNBT(HolderLookup.Provider provider)
void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag)
~~~

Their internal tag schemas must remain byte-for-byte compatible:

- gift memory remains keyed by villager UUID strings;
- chat mode retains explicit and enabled booleans.

### 8.3 One-time migration from ForgeCaps

Forge 1.20.1 stored the providers under the root player tag:

~~~text
ForgeCaps
  mcaconversations:gift_memory
  mcaconversations:chat_mode
~~~

NeoForge 1.21.1 stores attachments under neoforge:attachments and will not automatically import ForgeCaps.

Add a common PlayerLegacyDataMixin targeting Player.readAdditionalSaveData at TAIL. On the logical server:

1. If the root has no compound ForgeCaps, return.
2. Read the two old namespaced entries.
3. For each entry, migrate only when the corresponding new attachment is not already present.
4. Construct the new data object and deserialize the old compound using the player’s registry provider.
5. Set the attachment on the player.
6. Log one concise INFO line per migrated player, without dumping NBT.
7. Never erase or overwrite a valid new attachment.

The next normal save writes neoforge:attachments and no longer reproduces ForgeCaps, making the migration naturally one-time.

Put the parsing and precedence rules in a normal helper so they can be unit-tested without invoking Mixin.

### 8.4 Port world-global SavedData

Update:

- disposition/DispositionSavedData.java
- gossip/GossipSavedData.java
- progress/ProgressSavedData.java

For each class:

1. Add a create supplier for an empty instance.
2. Change load to accept CompoundTag and HolderLookup.Provider.
3. Change save to accept CompoundTag and HolderLookup.Provider.
4. Use:

~~~java
storage.computeIfAbsent(
    new SavedData.Factory<>(SavedType::create, SavedType::load),
    DATA_NAME
)
~~~

5. Keep the DATA_NAME constant exactly unchanged.
6. Keep the existing store serialization format exactly unchanged.
7. Keep every setDirty call on mutation.
8. Continue anchoring global data to server.overworld().

### 8.5 Tests

Add:

- GiftMemoryAttachmentNbtTest
- ChatModeAttachmentNbtTest
- LegacyForgeCapsMigrationTest
- AttachmentDeathCopyGameTest
- SavedData121RoundTripTest for all three files
- ExistingWorldUpgradeGameTest using copied fixture data

Migration tests must cover:

- old data only;
- new data only;
- both old and new data, where new wins;
- missing or malformed old values;
- death respawn;
- End return;
- relog and server restart.

### Exit gate

- No capability/provider/LazyOptional code remains.
- Old player data migrates once and survives a second restart.
- All three existing world data files load without reset and save under the same names.

## 9. Phase 4 — Replace Conversations networking

Conversations currently owns one C2S message: the typing-state ping. Rewrite ConversationsNetwork.java around CustomPacketPayload.

### Payload

~~~java
public record TypingStatusC2S(boolean typing) implements CustomPacketPayload {
    public static final Type<TypingStatusC2S> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(
            McaConversations.MOD_ID, "typing_status"
        ));

    public static final StreamCodec<ByteBuf, TypingStatusC2S> STREAM_CODEC =
        StreamCodec.composite(
            ByteBufCodecs.BOOL,
            TypingStatusC2S::typing,
            TypingStatusC2S::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
~~~

### Registration

Register on RegisterPayloadHandlersEvent:

~~~java
PayloadRegistrar registrar = event.registrar("2");
registrar.playToServer(
    TypingStatusC2S.TYPE,
    TypingStatusC2S.STREAM_CODEC,
    ConversationsNetwork::handleTyping
);
~~~

NeoForge invokes payload handlers on the main thread by default. In the handler:

- obtain the player from IPayloadContext;
- require a ServerPlayer;
- call ChatModeDispatcher.onTypingStatus;
- do not accept a UUID, position, radius, or target from the client;
- keep all feature, opt-in, liveness, and spectator checks on the server.

Use PacketDistributor.sendToServer from ChatTypingTracker.

Because Conversations is required on both sides, use strict protocol compatibility. A 1.20 client cannot connect to a 1.21 server anyway, so no legacy packet decoder is needed.

Add a codec round-trip test and a game test that sends true, refreshes the attention timeout, sends false, and verifies cleanup. Also verify that a disconnect expires attention even if the close packet is lost.

### Exit gate

- No net.minecraftforge.network or SimpleChannel code remains.
- The payload is registered exactly once on the mod bus.
- A real client can join a dedicated server and typing attention works.
- A malformed or excessive client sequence cannot select another player or villager and cannot bypass server config.

## 10. Phase 5 — Port the MCA bridge and adapter

### 10.1 Namespace migration

In McaCompat, McaBridge, ConversationsMcaRegistrar, and every mixin, replace forge.net.mca with net.conczin.mca.

Update comments to describe the loader-specific NeoForge artifact. Keep McaBridge as a classloading and compatibility boundary even though metadata requires MCA; it still provides a controlled failure path for an unexpected binary mismatch.

### 10.2 McaCompat changes

Replace old network imports with:

~~~java
import net.conczin.mca.network.Network;
import net.conczin.mca.network.s2c.InteractionDialogueQuestionResponse;
~~~

Change sayInDialogue to:

~~~java
Network.sendToPlayer(
    new InteractionDialogueQuestionResponse(line.get(), false),
    player
);
~~~

Keep the chat fallback.

Compile-check every wrapper against the exact MCA jar, especially:

- VillagerLike.getProfessionText and getAgeState;
- villager brain memories, rewardHearts, mood, and personality;
- LongTermMemory;
- EntityRelationship;
- FamilyTree and PlayerSaveData;
- Residency, Village, and VillageManager;
- Dialogues, Question, Answer, Constraint, and interactions.

Do not replace safe wrapper defaults with unguarded direct calls. Do remove comments claiming that one binary supports MCA 7.6 and 7.7; the new line targets MCA 7.7.36 on Minecraft 1.21.1.

### 10.3 Personality model

The inspected 7.7.36-era target registers these 14 rollable built-ins:

- friendly
- flirty
- playful
- gloomy
- sensitive
- greedy
- odd
- crabby
- extroverted
- introverted
- relaxed
- anxious
- peaceful
- upbeat

Update Personalities.CANONICAL to this exact roster. confident and peppy were present in the earlier 1.20.1 7.7 beta but are not registered in the target. Move them to legacy-only alongside athletic if their overlay resources are retained. Retain the old witty, shy, lazy, and grumpy aliases only as content compatibility data; do not count them as target rollable personalities.

Prefer the target’s stable personality ResourceLocation, such as getPersonalityId().getPath(), over a cross-version Object.toString workaround. Continue normalizing third-party namespaced personality IDs safely.

Update personality and overlay lint tests so they distinguish:

- required target overlays;
- retained legacy overlays;
- optional third-party personalities.

### 10.4 MCA dialogue registration

GiftPredicate.register and Actions.register retain the needed signatures under net.conczin.mca. Port imports and fully qualified parameter types in ConversationsMcaRegistrar.

Keep registration on common setup enqueueWork, before server data packs parse dialogue JSON.

Add a duplicate-registration guard so an unusual lifecycle or test bootstrap cannot install the same keys twice. Log the final registered vocabulary once.

### Exit gate

- All direct MCA calls are confined to compat, compat/mca, mixins, and explicitly documented optional adapters.
- Every McaCompat method compiles against and is smoke-tested with MCA 7.7.36.
- A /reload parses all custom conditions and actions without an unknown-key error.

## 11. Phase 6 — Retarget and harden mixins

### 11.1 Mixin configuration

Update mcaconversations.mixins.json:

- compatibilityLevel: JAVA_21
- minVersion: 0.8.7
- retain required: true
- retain the generated refmap and verify it is actually present in the built jar
- keep defaultRequire at zero only for intentionally fail-soft, non-critical injections

Expected target list:

Common:

1. BreedableRelationshipMixin
2. DialoguesMixin
3. InteractionDialogueMessageMixin
4. McaNetworkMixin, renamed from NetworkHandlerMixin
5. QuestionMixin
6. PlayerLegacyDataMixin

Client:

1. client.MCAClientMixin

Delete client.VillagerMessageMixin.

### 11.2 BreedableRelationshipMixin

- Target net.conczin.mca.entity.ai.BreedableRelationship.
- Keep MCA method remap disabled.
- Inject immediately before the accepted branch’s ItemStack.split(1).
- Use Relationship.getWorld and getUUID to resolve the villager.
- Pass stack.copyWithCount(1) to GiftTracker.
- Test inventory-full, unknown gift, base failure, saturation failure, accepted normal, and accepted best responses.

Only the two accepted cases may create gratitude, smitten state, or gift memory.

### 11.3 DialoguesMixin

- Change imports to net.conczin.mca.
- Verify the exact private questions field and getQuestion descriptor in the resolved jar.
- Preserve exact behavior: redirect only the name chat, only in REPLACE mode, and only when the conversations hub exists.
- Test ADDITIVE, REPLACE, HIDDEN, and a datapack that removes the hub.

### 11.4 InteractionDialogueMessageMixin

- Target net.conczin.mca.network.c2s.InteractionDialogueMessage.
- Inject into handleServer(ServerPlayer) at HEAD.
- Avoid shadowing record fields. Cast this to InteractionDialogueMessage and use villagerUUID(), question(), and answer().
- Preserve the narrow ConversationGuard scope.
- Test valid GUI clicks, valid chat-mode drives, stale offers, replayed packets, out-of-range villagers, another player’s active interaction, and native MCA questions.

### 11.5 McaNetworkMixin

Target:

~~~java
net.conczin.mca.network.Network.sendToPlayer(
    HandleablePayload payload,
    ServerPlayer player
)
~~~

At HEAD:

- mark the redirect hook installed;
- for InteractionDialogueQuestionResponse, use questionText() and silent();
- for InteractionDialogueResponse, record question() and answers(), then swallow only under the existing redirect scope;
- for AnalysisResults, swallow only under the existing chat-mode analysis scope;
- pass every other payload through unchanged.

Use an explicit method descriptor so an overload cannot be captured later.

Add an observable startup/self-test state:

- critical hook applied;
- optional hook applied;
- hook missing.

The existing /conversations chat debug-ask command should report a missing redirect hook plainly.

### 11.6 QuestionMixin

Use the full target callback shape:

~~~java
private void hideHubButton(
    ServerPlayer player,
    VillagerEntityMCA villager,
    CallbackInfoReturnable<List<String>> cir
)
~~~

Continue removing only conversations from main when the configured hub mode hides the separate button.

### 11.7 MCAClientMixin

- Change Config and MCAClient imports to net.conczin.mca.
- Retain the two upstream safety guards: online TTS disabled and no MCA voice pack active.
- Allow only locales for which Conversations ships a complete overlay, currently en_us and pt_br.
- Update stale comments: the inspected target’s native method allows en_us, not the older documented en_us/ru_ru combination.
- Test client startup in en_us and pt_br, with and without MCAVoices, and with online TTS enabled.

### 11.8 Refmap and production validation

Development names are not sufficient. For every mixin:

1. Confirm the annotation processor creates mcaconversations.refmap.json.
2. Inspect the refmap and jar contents.
3. Start a production-style client and server using the built jar, not only IDE classes.
4. Treat every Mixin apply warning, target-not-found message, or zero matched instruction as a release blocker.

### Exit gate

- All seven intended mixins apply in a production-style runtime.
- No obsolete mixin class remains in source, JSON, tests, or jar.
- Critical hooks have positive runtime evidence, not merely require = 0.

## 12. Phase 7 — Optional integrations and suite build order

MCA: Quests and MCA: Reputation are currently Forge 1.20.1 projects. Their class files cannot be placed on a NeoForge 1.21.1 compile or runtime classpath. A complete Conversations suite port therefore has a cross-repository prerequisite.

### Required order

1. Port MCA: Reputation to Minecraft 1.21.1 NeoForge.
2. Port MCA: Quests to Minecraft 1.21.1 NeoForge, compiling against the ported Reputation API if needed.
3. Publish or otherwise expose reproducible compile-only artifacts for both.
4. Port Conversations adapters against those exact artifacts.

### Build contract

Replace sibling build/classes paths with versioned dependencies. Preferred design:

- each sibling publishes a small API artifact or a normal deobfuscated development variant;
- Conversations uses compileOnly for the API;
- optional integration test runs add the real mod with runtimeOnly;
- no sibling class is shaded into Conversations;
- the release workflow resolves exact versions and fails loudly if the APIs are absent.

Do not make a normal build silently omit compat classes. If a core-only developer profile is needed, make it explicit and ensure release and CI use the full profile.

### Quests adapter

Port:

- compat/QuestsBridge.java
- compat/quests/ConversationsQuestsCompat.java
- compat/quests/ConversationsQuestsEvents.java
- QuestConditionQuery.java
- QuestOpenDirective.java
- QuestVoiceResolver.java
- TalkAboutObjective.java
- UnlockTopicReward.java

Specific changes:

- NeoForge ModList and event bus packages;
- ResourceLocation factories;
- org.jetbrains.annotations.Nullable instead of javax.annotation.Nullable where needed;
- target Quests attachment/storage APIs instead of its old Forge capabilities;
- codecs and registries updated for Minecraft 1.21.1;
- event classes compiled against the ported Quests API;
- no direct dependency on Quests implementation internals unless that is an explicitly versioned public contract.

Refactor ConversationsQuestsCompat to use McaQuestsApi and QuestDialogueHooks for all operations that can be exposed there. If current concrete types such as QuestManager, QuestDefinitions, PlayerQuestData, QuestCapabilities, or ActiveQuest are still required, add the necessary public query methods to MCA: Quests rather than binding Conversations to internal storage.

### Reputation adapter

Port:

- compat/ReputationBridge.java
- compat/ReputationQueryJson.java
- compat/reputation/ConversationsReputationCompat.java

Use the ported public API for snapshots, queries, incidents, external gossip, and signals. Move any required CommunityKey or internal enum conversion behind MCA: Reputation’s API rather than importing its implementation packages.

### Metadata

After the sibling release versions are fixed, add optional dependency entries with:

- type = "optional"
- ordering = "AFTER"
- side = "BOTH"
- a range that begins at the first 1.21.1 NeoForge-compatible release

Never retain [0.8,) or [0.2,) if those ranges include Forge-only jars.

### Serene Seasons

The Seasons bridge is reflection-only. Update ModList imports, then test against the actual 1.21.1 NeoForge Serene Seasons jar:

- present and compatible;
- absent;
- present with an unexpected API, which must fall back to the calendar model without crashing.

### Exit gate

- Core Conversations builds and runs with both sibling mods absent.
- Full Conversations builds reproducibly against versioned sibling APIs.
- Runtime matrix passes with Quests only, Reputation only, both, and neither.
- No optional mod class loads before its ModList gate.

## 13. Phase 8 — Content, resources, and tests

### 13.1 Preserve content first

Do not mass-regenerate or rewrite the 173 dialogue files during the loader port. MCA 1.21.1 still loads dialogue JSON from the dialogues folder and retains the custom GiftPredicate and Actions extension seams.

Keep all question IDs, answer IDs, action names, condition names, translation keys, priorities, and merge behavior stable.

### 13.2 Validate target content assumptions

Update tests for:

- the 14 target personality overlays;
- retained legacy overlays as non-required compatibility assets;
- en_us and pt_br parity;
- all 173 dialogue files;
- all 13 chat-intent files;
- the catalog and interiority profile;
- the changed mixin list;
- pack metadata;
- no stale target class names.

Retain existing content lints:

- ChatIntentLintTest
- ContentLintTest
- ConversationGraphLintTest
- LocaleParityTest
- MixinsJsonLintTest
- OverlayLintTest
- TopicPathSimulationTest

Update expectations, not the safety rules they enforce.

### 13.3 Add port-specific static tests

Add NeoForgePortLintTest that fails on:

- imports beginning net.minecraftforge;
- imports beginning forge.net.mca;
- new ResourceLocation constructors;
- ForgeRegistries;
- SimpleChannel;
- LazyOptional or capability-provider types;
- META-INF/mods.toml;
- VillagerMessageMixin in source or mixin JSON;
- Java 17 compatibility in mixin JSON.

Permit the exact string ForgeCaps only in the migration helper/test.

### 13.4 Add runtime-oriented tests

Where a vanilla-only unit test is inadequate, use ModDevGradle unit-test support or NeoForge GameTests.

Add coverage for:

- attachment registration and death copying;
- legacy player data migration;
- SavedData load/save;
- payload codec and handler;
- all custom dialogue predicates and actions after /reload;
- hub routing modes;
- exact gift acceptance semantics;
- packet submission guard;
- chat-mode redirect;
- locale overlay gate;
- dedicated-server side isolation.

### Exit gate

- All 65 existing test classes pass after intentional expectation updates.
- New port tests pass.
- Resource counts do not unexpectedly shrink.
- /reload completes without malformed, duplicate, or unknown dialogue keys.

## 14. Phase 9 — Artifact, CI, and runtime verification

### 14.1 Jar verification

Create verifyJarContents and make build depend on it.

Require:

- META-INF/neoforge.mods.toml exists;
- META-INF/mods.toml does not exist;
- mcaconversations.mixins.json exists;
- mcaconversations.refmap.json exists if referenced;
- all expected data and asset namespaces exist;
- no class begins with:
  - net/conczin/mca/
  - forge/net/mca/
  - net/minecraftforge/
  - dev/architectury/
  - dev/otectus/mcaquests/
  - dev/otectus/mcareputation/
- no nested MCA, NeoForge, Quests, or Reputation jar is present;
- Implementation-Version matches the release;
- class-file major version is Java 21.

### 14.2 CI

Add a GitHub Actions workflow with:

1. Ubuntu, Temurin 21:

~~~bash
./gradlew --no-daemon clean test build
~~~

2. Windows, Temurin 21:

~~~text
gradlew.bat --no-daemon test build
~~~

3. A headless GameTest or bounded dedicated-server smoke job.
4. A full-suite job resolving the exact ported Quests and Reputation artifacts.
5. Artifact upload for test reports, logs, and the candidate jar.

Cache Gradle safely. Do not cache run worlds between jobs.

### 14.3 Core runtime matrix

Run the built jar, not IDE output.

| Scenario | Required result |
|---|---|
| Dedicated server: NeoForge + MCA + Conversations | Starts cleanly; no client-class error |
| Client: NeoForge + MCA + Conversations | Main menu and world load cleanly |
| Client joins dedicated server | Strict payload registration succeeds |
| MCA absent | Loader reports required dependency clearly |
| Quests/Reputation absent | Core features remain fully usable |
| /reload | All Conversations content reloads |
| en_us | Generic and personality lines resolve |
| pt_br | Generic and personality overlays resolve |
| MCAVoices active | MCAClientMixin preserves voice-pack guard |
| Online TTS enabled | MCAClientMixin preserves TTS guard |

### 14.4 Feature acceptance script

In one clean world, verify:

1. ADDITIVE shows MCA Chat and a separate Conversations button.
2. REPLACE routes MCA Chat to the Conversations hub and hides the duplicate.
3. HIDDEN removes the separate entry and leaves MCA Chat unchanged.
4. A complete branching topic applies the selected response, progress, disposition, and at most the permitted heart change.
5. Replaying the same GUI packet cannot repeat a guarded outcome.
6. A rejected gift creates no memory or gratitude.
7. Inventory-full and saturated gifts create no memory or gratitude.
8. An accepted gift records exactly one item, persists through relog, and survives death.
9. Templates resolve villager, spouse, village, profession, time, weather, season, holiday, and last gift.
10. Gossip records and tells death, marriage, birth, arrival, and departure as configured.
11. Chat mode opt-in/out persists.
12. Named, sticky, look-at, ambient, quick-reply, busy, baby/toddler, mute, cooldown, and confusion paths behave as before.
13. Default chat remains vanilla signed chat.
14. Experimental local chat is radius-limited and explicitly unsigned as documented.
15. Typing opens attention, refreshes it, closes it, and expires after packet loss/disconnect.
16. Public and private villager reply modes reach the correct players.
17. The debug-ask command reports the redirect hook active.

### 14.5 Upgrade matrix

Open only copies of the phase-0 fixtures.

Verify:

- Minecraft and MCA complete their own world upgrades without MCA: Conversations errors;
- all three SavedData files load with original record counts;
- old gift memory appears under the new attachment;
- old chat-mode choice remains explicit and unchanged;
- the next save writes neoforge:attachments;
- a second restart does not re-import or duplicate data;
- death and End return preserve attachments once;
- content, personalities, and family names remain usable.

MCA’s 7.7.36 changelog warns about internal personality/trait persistence changes relative to older 7.7 builds. Backups are mandatory, and the test report must distinguish MCA’s data conversion from Conversations’ own migration.

### Exit gate

- Every matrix row has a recorded pass.
- Logs contain no Mixin target failure, unknown payload, missing dialogue type, data-fix exception, client class on server, or hidden linkage error.
- The candidate jar passes verifyJarContents.

## 15. Phase 10 — Documentation and release

Update:

- README.md
- CONFIG.md
- CHANGELOG.md
- any installation or compatibility sections under docs

Document:

- Minecraft 1.21.1 and NeoForge only;
- Java 21;
- exact initial MCA compatibility;
- removal of Architectury;
- upgrade backup requirement;
- automatic ForgeCaps-to-attachment migration;
- unchanged SavedData filenames;
- optional suite versions;
- known incompatibility with Forge 1.20.1 jars;
- the new artifact/version naming convention.

Publish separate loader/game-version artifacts. Never replace an existing 1.20.1 Forge file with a 1.21.1 NeoForge jar carrying the same ambiguous filename.

Recommended release artifact:

~~~text
mcaconversations-neoforge-2.0.0+1.21.1.jar
~~~

Tag only the commit whose CI artifacts passed the full runtime and upgrade matrices.

## 16. File-by-file change manifest

### Delete

- src/main/java/dev/otectus/mcaconversations/gift/GiftMemoryProvider.java
- src/main/java/dev/otectus/mcaconversations/chat/ChatModePlayerStateProvider.java
- src/main/java/dev/otectus/mcaconversations/mixin/client/VillagerMessageMixin.java
- src/main/resources/META-INF/mods.toml

### Rename or replace

- gift/ConversationsCapabilities.java → gift/ConversationsAttachments.java
- mixin/NetworkHandlerMixin.java → mixin/McaNetworkMixin.java
- META-INF/mods.toml → META-INF/neoforge.mods.toml

### Add

- mixin/PlayerLegacyDataMixin.java
- a pure legacy ForgeCaps migration helper
- NeoForgePortLintTest
- attachment and legacy migration tests
- payload codec/handler tests
- SavedData 1.21.1 round-trip tests
- mixin/runtime audit tests
- jar-content verification
- GitHub Actions workflow
- docs/PORT-1.21.1-EVIDENCE.md

### Major edits

| File or area | Change |
|---|---|
| build.gradle | ModDevGradle, Java 21, MCA NeoForge dependency, test/runtime setup |
| gradle.properties | target versions and ranges |
| settings.gradle | modern plugin/toolchain setup |
| Gradle wrapper | 9.6.1 |
| McaConversations.java | injected mod bus/container, attachments and payload registration |
| McaConversationsConfig.java | ModConfigSpec |
| ConversationsEvents.java | NeoForge events; remove capability lifecycle |
| ChatTypingTracker.java | client post-tick event and PacketDistributor |
| ConversationsNetwork.java | CustomPacketPayload |
| GiftMemoryData.java | NeoForge INBTSerializable signature |
| ChatModePlayerState.java | NeoForge INBTSerializable signature |
| GiftTracker.java | BuiltInRegistries and one-item accepted stack |
| TemplateContextFactory.java | BuiltInRegistries optional lookup and direct attachment access |
| three SavedData classes | HolderLookup.Provider and Factory API |
| McaBridge and optional bridges | NeoForge ModList |
| McaCompat.java | net.conczin imports and MCA payload API |
| ConversationsMcaRegistrar.java | net.conczin imports |
| all five retained MCA mixins | exact 7.7.36 targets/descriptors |
| MCAClientMixin.java | namespace and updated target comments |
| Personalities.java | exact 14-personality target roster |
| mcaconversations.mixins.json | Java 21, Mixin 0.8.7, new list |
| pack.mcmeta | resource format 34 |
| content tests | target roster, mixins, metadata |

Every other Java file should be changed only when compilation, a target API change, or a test proves it necessary. Avoid formatting or content churn that obscures the port.

## 17. Risk register

| Risk | Severity | Mitigation |
|---|---:|---|
| MCA 7.7.36 published binary differs from moving source branch | High | Hash and inspect the exact resolved jar; use exact descriptors |
| Fail-soft mixin silently removes a feature | High | Critical require during development, startup audit, production-style test |
| ForgeCaps data is ignored by NeoForge | High | Explicit Player NBT migration and copied-world upgrade tests |
| Gift hook records rejected gifts | High | Inject only at successful split invocation |
| Optional siblings remain Forge-only | High | Port in dependency order; compile against versioned NeoForge APIs |
| SavedData reset due to filename/factory changes | High | Keep names/schema, golden NBT fixtures, restart checks |
| Client class loads on dedicated server | High | client-only subscriber/mixin plus headless smoke job |
| Signed chat semantics regress | High | Preserve default non-canceling path; explicit local-chat tests |
| Personality roster changed in late MCA 7.7 | Medium | Target 14-name roster and separate legacy assets |
| Resource metadata accepts assets but rejects data | Medium | pack format update plus real client and /reload validation |
| Dependency accidentally shaded | Medium | verifyJarContents and archive inspection |
| Broad MCA version range admits incompatible internals | Medium | Start narrow; widen only after matrix testing |
| Resource rewrite introduces translation/dialogue drift | Medium | Preserve resources; run existing lint/parity/graph tests |

## 18. Coding-agent operating rules

The implementing agent must:

1. Work phase by phase and stop at each exit gate.
2. Keep the project buildable at each committed phase, or state clearly when a short-lived mechanical migration commit cannot compile.
3. Use the exact resolved 7.7.36 jar as the mixin authority.
4. Preserve IDs and persistence formats by default.
5. Add a regression test before fixing any port-discovered behavioral bug.
6. Never remove content merely because a loader/API test is difficult.
7. Never use catch Throwable as a substitute for a correct compile-time integration. Existing fail-safe boundaries may remain, but core startup must fail visibly when a required hook is incompatible.
8. Never add Architectury. MCA’s 1.21.1 NeoForge artifact does not require it.
9. Never compile NeoForge code against Forge sibling class files.
10. Review the final diff for unrelated formatting and generated-file churn.

## 19. Final definition of done

The port is done only when all statements below are true:

- Java 21, ModDevGradle, NeoForge, Minecraft, and MCA pins match the target table.
- clean test build passes on Linux and Windows.
- The existing 65 test classes and all new port tests pass.
- A production-style dedicated server and client start with the built jar.
- Client/server payload negotiation and typing attention work.
- All intended mixins apply to the exact MCA 7.7.36 runtime.
- All core feature acceptance steps pass.
- All four optional-mod combinations pass.
- The two copied 1.20.1 worlds upgrade without Conversations data loss.
- The built jar contains the required metadata/resources and no shaded dependency classes.
- No unintended net.minecraftforge, forge.net.mca, ForgeRegistries, SimpleChannel, LazyOptional, or old capability code remains.
- README, config documentation, changelog, dependency ranges, and release filename accurately describe the NeoForge line.
- The release candidate is produced by CI from the tagged commit that passed these gates.

At that point, and not before, publish the 1.21.1 NeoForge release.
