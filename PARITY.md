# PARITY.md — MCA: Conversations 1.20.1 Forge (v1.5.1) → 1.21.1 NeoForge

**Status:** Complete. Build green (991 tests). Identity diffs clean. 2026-09-03.

SOURCE `C:\Projects\MCAConversations` @ `1ee78b7` (v1.5.1) is the spec. This checklist has one box per
SOURCE entry, grouped by category in dependency order. Legend: `[x]` matches SOURCE HEAD; `[ ]` missing (not applicable to this port). "0 byte-diff" means the resource tree-hash compare in
PORT_STATUS.md § Verification is empty for that directory. Paths are relative to
`src/main/java/dev/otectus/mcaconversations/` unless they start with `src/`, `docs/`, or are root files.

## A. Registries — none in SOURCE
- [x] No DeferredRegister content in either repo (pure logic/data/mixin mod). N/A.

## B. Data: attachments, SavedData, reload listeners
- [x] attachment `mcaconversations:gift_memory` — SOURCE `gift/ConversationsCapabilities.java:27` → TARGET `gift/ConversationsAttachments.java:39`
- [x] attachment `mcaconversations:chat_mode` — SOURCE `:29` → TARGET `:45`
- [x] SavedData `mcaconversations_dispositions` — `disposition/DispositionSavedData.java` (reconcile post-1.2.1 diff)
- [x] SavedData `mcaconversations_gossip` — `gossip/GossipSavedData.java`
- [x] SavedData `mcaconversations_progress` — `progress/ProgressSavedData.java`
- [x] SavedData `mcaconversations_history` — `history/ConversationHistorySavedData.java` (Factory/HolderLookup pattern)
- [x] SavedData `mcaconversations_identity` — `identity/VillagerIdentitySavedData.java` (Factory/HolderLookup pattern)
- [x] SavedData `mcaconversations_culture` — `village/VillageCultureSavedData.java` (Factory/HolderLookup pattern)
- [x] `AddReloadListenerEvent` loaders: all 8 in SOURCE order (ChatIntentLoader, ConversationCatalogLoader, BeatContractLoader, ProfessionProfileLoader, Interiority, IdentityCatalogLoader, SceneCatalogLoader, VillageCultureCatalogLoader) + `NarrativeCatalogLoader.listeners()`; handler body character-identical to SOURCE
- [x] `compat/TownsteadCapability` (plain enum, 15 constants in SOURCE order)

## C. Config (`McaConversationsConfig.java`, 111 define calls, three specs)
- [x] COMMON spec sections `features`, `gift`, `states`, `world`, `gossip`, `rpg`, `conversation`, `chat`
- [x] COMMON new sections `townstead`, `dynamic`, `history`, `group`, `debug`
- [x] SERVER spec (`chat`, `conversation`, `rpg`, `dynamic`, `history`) registered as `ModConfig.Type.SERVER`
- [x] CLIENT spec (`display`)
- [x] Acceptance met: ordered `push/define*` token stream identical to SOURCE (130 tokens, 111 defines, no differences); default config file names; `season/CalendarSource` added

## D. Networking (SOURCE channel `mcaconversations:main` v"2" → NeoForge payload registrar `versioned("2")`)
- [x] `TypingStatusC2S(boolean)` → payload `mcaconversations:typing_status`
- [x] `ChoiceOfferS2C(long revision, Frontend frontend, String questionId, List<String> answerIds)` → `mcaconversations:choice_offer`; wire order preserved (varLong, enum, utf(256), varInt count ≤64, utf(256)[]); `MAX_CHOICES=64`, `MAX_ID_LENGTH=256`; byte-exact port-only test
- [x] `ChoiceClearS2C(long revision, Reason reason)` → `mcaconversations:choice_clear`; `Reason {NONE, CONSUMED, EXPIRED}`
- [x] `ChoiceSelectC2S(long revision, int absoluteIndex, UUID villagerId)` → `mcaconversations:choice_select`
- [x] protocol version `"2"` (`registrar.versioned("2")`)
- [x] `sendOffer` / `clearOffer` / `warnOversizedOffer` in `ConversationsNetwork` (SOURCE names/signatures; client handlers behind `FMLEnvironment.dist.isClient()` lambdas)

## E. Client / GUI
- [x] `client/ChatTypingTracker`
- [x] `client/ClientUiResourceGeneration` (CLIENT, MOD bus, `RegisterClientReloadListenersEvent`)
- [x] `client/dialogue/*` (19 files) — 17 verbatim; `DialogueCardSkin` (widget strip → GUI sprites, `MENU_BACKGROUND`) and `DialogueChoiceRenderer` (rect-based `renderEntityInInventoryFollowsAngle`) carry Deviation 18
- [x] `client/dialogue/dev/*` (3) — `DialogueCardPreviewScreen`: `ModConfigSpec.ConfigValue`, manual `renderBackground` dropped (Screen.render calls it), 4-double `mouseScrolled`; `DialoguePreviewCommand`: NeoForge `RegisterClientCommandsEvent`
- [x] `client/townstead/*` (2, verbatim; reflective)

## F. Mixins (`src/main/resources/mcaconversations.mixins.json`, `defaultRequire: 0`)
- [x] `BreedableRelationshipMixin` → `net.conczin.mca.entity.ai.BreedableRelationship.acceptGift`, `@Pseudo` string target, BEFORE the sole `ItemStack.split(I)` (Deviation 1), `require = 0`
- [x] `DialoguesMixin` → `resources.Dialogues.getQuestion(String)` HEAD, string target
- [x] `InteractionDialogueMessageMixin` → `network.c2s.InteractionDialogueMessage.handleServer(ServerPlayer)`, string target, body at SOURCE HEAD
- [x] `NetworkHandlerMixin` → `network.Network.sendToPlayer(HandleablePayload, ServerPlayer)`, string target, SOURCE name, body at SOURCE HEAD (frontend-aware offer branch)
- [x] `QuestionMixin` → `Question.getValidAnswers(ServerPlayer, VillagerEntityMCA)` RETURN, string target
- [x] client `MCAClientMixin` → `MCAClient.useExpandedPersonalityTranslations()Z`, string target
- [x] client `ChatScreenChoiceMixin` → vanilla `ChatScreen.keyPressed(III)Z` (verbatim)
- [x] client `InteractScreenChoiceMixin` → `net.conczin.mca.client.gui.InteractScreen` string target; SRG aliases dropped; `setLastPhrase(Component, boolean)`; `mouseScrolled(DDDD)`; `@ModifyArg` on `Font.split` kept (Deviation 12)
- [x] client `TownsteadChoicePanelMixin` → `com.aetherianartificer.townstead.client.gui.dialogue.ChoicePanel` (setVisible/tick/render TAIL; `entryHeights` is `@Shadow @Final` on 1.21.1)
- [x] client `TownsteadRpgDialogueScreenMixin` → `...RpgDialogueScreen.keyPressed(III)Z` HEAD (SRG alias dropped)
- [x] client `VillagerMessageMixin` — deleted by design (PORT_STATUS Deviation 2)
- [x] `PlayerLegacyDataMixin` — port-only, keep

## G. Integrations
- [x] `compat/mca/ConversationsMcaRegistrar` — 19 conditions + 9 actions, byte-identical to SOURCE HEAD
- [x] `compat/mca/LivingHistoriesRegistrar` — 12 conditions + 6 actions, byte-identical; id multiset SOURCE = TARGET (31 conditions, 15 actions)
- [x] `compat/mca/McaBinding`, `compat/mca/McaHandles` (reflective manifest; 88 `Member` constants unchanged; single root `net.conczin.mca.`, `C_NETWORK` → `network.Network`, `getQuestionText` → `questionText`; all five owner rows stay on SOURCE's owner class)
- [x] `compat/McaBridge`, `compat/McaCompat` — at SOURCE HEAD (McaBridge differs only by the `net.neoforged.fml.ModList` import)
- [x] `compat/quests/*` (7), `compat/reputation/*` (1), `compat/seasons/*` (1) + `QuestsBridge`/`ReputationBridge`/`SeasonsBridge` — unchanged in SOURCE since 89edad2; both sibling class dirs exist so the `compileOnly` branches are taken
- [x] Townstead: `compat/Townstead*.java` (16) + `compat/townstead/*` (3; 112 manifest `Member`s unchanged, all resolve against 0.7.6 NeoForge), `TownsteadCompat::init` last in `onCommonSetup`
- [x] `neoforge.mods.toml` optional deps: `mcaquests [0.8,)`, `mcareputation [0.2,)`, `townstead [0.7.5,0.8)`; required `neoforge`, `minecraft`, `mca [7.7.13,8)`

## H. Commands
- [x] `/conversations gossip list|clear`, `/conversations chat on|off|status|debug-ask|debug` (`command/ConversationsCommand.java` at HEAD; only the attachment read differs)
- [x] `/conversations profile inspect|tokens`, `history inspect|forget confirm`, `scene plan|candidates`, `context snapshot|capabilities` (`command/LivingHistoriesCommand.java` verbatim, perm 2; SOURCE mounts these subtrees under `conversations` from `ConversationsCommand.register`; 24 literals identical)
- [x] client dev preview command (`RegisterClientCommandsEvent`, `client/dialogue/dev/DialoguePreviewCommand`)

## I. Core engine packages (SOURCE files → TARGET files)
- [x] `chat` 21 (at SOURCE HEAD; `ChatModeDispatcher.chatModeEnabled` reads the NeoForge attachment; loader dir `chat_intents`; 208 = 208 literals)
- [x] `chat.group` 5 (verbatim)
- [x] `check` 7 (verified 0 hunks vs SOURCE HEAD); [x] `state` 5 (0 hunks)
- [x] `context` 18 (verbatim; 95 key literals match)
- [x] `conversation` 33 (all at SOURCE HEAD, 0 hunks; `Frontend {GUI, CHAT}` order verified; `BeatContractLoader` dir `conversation_beats`; 11 new enums' constant order verified)
- [x] `debug` 3 (verbatim)
- [x] `disposition` 9, `gift` 4, `gossip` 11, `progress` 13, `event` 1 (at HEAD; only SavedData/attachment/registry/event-class shapes differ); [x] `world` 1 (0 hunks)
- [x] `history` 35 (byte-identical except SavedData signature and `BuiltInRegistries.ITEM`; loader dirs `episode_templates`, `thread_templates`, `commitment_templates`)
- [x] `hub` 5 (verbatim)
- [x] `identity` 10 (byte-identical to SOURCE except the SavedData signature)
- [x] `interiority` 2, `locale` 3 (`LineVoice` added), `personality` 3 (`VoiceFamily` added), `season` 3 (`CalendarSource` added), `state` 5 (at HEAD)
- [x] `profession` 4
- [x] `scene` 19 (verbatim except two `ModList` imports; loader dir `conversation_scenes`)
- [x] `template` 8 (at SOURCE HEAD; `TemplateContextFactory` differs only by `BuiltInRegistries`)
- [x] `village` 10 (loader dir `village_culture`)
- [x] root: `McaConversationsConfig` at HEAD (rename only); `McaConversations` at HEAD (NeoForge bootstrap shape; `onCommonSetup` order McaBridge → QuestsBridge → ReputationBridge → SeasonsBridge → TownsteadCompat)

## J. Datagen
- [x] None in SOURCE or TARGET. N/A.

## K. Assets & data (acceptance: 0 byte-diff vs SOURCE for every listed directory)
- [x] `data/mcaconversations/dialogues/` — 920, byte-identical (10 stale TARGET-only files deleted)
- [x] `data/mcaconversations/conversation_beats/` — 110, byte-identical
- [x] `data/mcaconversations/chat_intents/` — 26, byte-identical
- [x] `data/mcaconversations/profession_profiles/` — 7 (byte-identical, pulled forward in F2)
- [x] `data/mcaconversations/village_culture/` — 2 (`base.json`, `townstead.json`; byte-identical)
- [x] `data/mcaconversations/{thread_templates,episode_templates,conversation_scenes,commitment_templates}/generated.json`, `identity_tokens/base.json` (byte-identical)
- [x] `data/mcaconversations/interiority/`, `conversation_catalog/` — byte-identical
- [x] `assets/mcaconversations/lang/en_us.json`, `pt_br.json` — 549 keys each, byte-identical to SOURCE
- [x] `assets/mca_dialogue*/lang/*` — 22 namespaces × 2 files, byte-identical; `assets/mcaconversations/textures/gui/` copied
- [x] Tree-hash acceptance met on 2026-09-03: every file under `src/main/resources` hash-equals SOURCE except `META-INF/neoforge.mods.toml`, `pack.mcmeta`, `mcaconversations.mixins.json` (1,121 = 1,121 files)
- [x] `src/content/**` (94 files, sha-equal) + `authoring/*` (7 test-source files; no-arg repository defaults via `TestPaths`, explicit JavaExec args unchanged) + Gradle tasks `verifyGeneratedConversationContent`, `generateConversationContent`, `verifyVoiceOverlays`, `generateVoiceOverlays`, `conversationsReports` (`build` depends on it); `verifyJarContents` floors raised to live counts (dialogues 920, beats 110, chat_intents 26, …)
- [x] Deleted stale TARGET-only dialogues (SOURCE renamed them): `conversations.topic.{food.normal,news,noticed,people,standing,village,work}.followup.json`, `conversations.topic.{people,standing,work}.respond.json`
- [x] `pack.mcmeta` — pack_format 34 + supported_formats (keep TARGET)
- [x] `mcaconversations.mixins.json` — JAVA_21 / no refmap / `defaultRequire: 0`; `mixins` = SOURCE 5 + `PlayerLegacyDataMixin`; `client` = SOURCE 6 − `VillagerMessageMixin` (delta exactly `+PlayerLegacyDataMixin`, `-VillagerMessageMixin`)

## L. Tests
- [x] All 137 SOURCE tests present in TARGET (the 4 Townstead tests land in B9)
- [x] 41 shared tests differ from SOURCE HEAD; every hunk is the `TestPaths` repository-path rule (33 files) or a documented 1.21.1 fact (7 files: `ConfigSpecTest`, `ReloadResilienceTest`, `OptionalProfessionIsolationTest`, `ContentLintTest`, `MixinsJsonLintTest`, `McaBindingProbeTest`, `PersonalitiesTest`; plus `MixinTargetProbeTest`) — zero unexplained divergences
- [x] `src/test/resources/{generated_matcher_fixtures.tsv, legacy_unverified_routes.txt, signature_overlay_debt.txt, topic_depth_debt.txt}` sha-equal
- [x] Port-only tests kept: `content/NeoForgePortLintTest`, `content/SavedDataContractTest`, `gift/AttachmentNbtRoundTripTest`, `gift/ForgeCapsFixtures`, `gift/ForgeCapsMigrationTest`, `network/{TypingStatus,ChoiceOffer,ChoiceClear,ChoiceSelect}PayloadTest`, `support/TestPaths`, `support/MixinClassLoader`, `compat/McaHidingClassLoader` + `src/test/resources/fixtures/*.dat`

## M. Port-only additions (keep; no SOURCE counterpart)
- [x] `gift/ConversationsAttachments` (attachment registry), `mixin/PlayerLegacyDataMixin` (Forge-caps → attachment migration)
- [x] `docs/PORT-1.21.1-EVIDENCE.md`, `docs/ACCEPTANCE-1.21.1.md`
- [x] `verifyJarContents` Gradle task (thresholds to be raised in the resource-sync slice), `filteringCharset='UTF-8'`, sibling-classes `compileOnly` blocks for mcaquests/mcareputation
