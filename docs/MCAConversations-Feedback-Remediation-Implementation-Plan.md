# MCA: Conversations — feedback remediation implementation plan

Prepared 2026-10-17 for the coding agent that will execute it. It answers the four reports in the
2026-10-17 handoff (text mismatch, repeated opener speech, child/parent perspective, server tick lag)
with one investigation each, one design, ordered work packages, a content inventory, validation
steps, a release plan and the open questions that only the reporters can close.

Everything below was derived from the checkout and from the real dependency jars. **No Minecraft
client was run, no audio was played and no profiler was attached while preparing this plan.** Where a
claim rests on bytecode it names the jar; where it rests on source it names `file:line`. Where the
plan could not establish something, it says so and leaves a runtime gate.

Paths are relative to `src/main/java/dev/otectus/mcaconversations/` unless they start with `src/`,
`docs/`, `tools/` or `/`.

---

## 0. Baseline and evidence sources

### 0.1 Checkout

| Item | Observed |
|---|---|
| Branch / HEAD | `feature/social-1.8.0` at `db6fe57`, which is also `origin/main`. Local `main` lags at `1a9a6a5` (1.7.3). |
| Working tree | Dirty: the MCA: Kingdoms rename (`compat/KingdomsIdentity`, `mods.toml` `mcakingdoms` entry, regenerated `scene_generated_mcakingdoms.json`, `NoKingdomsStaticLinkTest`). Plan against HEAD plus this tree; do not revert it. |
| Build facts | `gradle.properties`: MC 1.20.1, Forge 47.4.10, Java 17, `mod_version=1.8.0`, `network_protocol=4`, probe fleet 7.6.20 / 7.6.26 / 7.7.0-beta.2 / 7.7.1-alpha.2 / 7.7.1-beta.1 / 7.7.1-beta.2. |
| `CHANGELOG.md` | 1.8.0 is **unreleased**. The screen/chat agreement fix (`VillagerMessageMixin`) shipped in **1.2.0 (2026-08-19)** (`CHANGELOG.md:3116`). Every jar in `build/libs/` from 1.5.2 upward carries the mixin class. |
| NeoForge port | `/home/otectus/Projects/1.21.1 Ports/MCAConversations_1.21.1` at `18bb011`. Its `mcaconversations.mixins.json` has **no** `VillagerMessageMixin` (see §1.1 and §6). |
| Built jars | `build/libs/mcaconversations-1.8.0.jar` sha256 `87684114…` (Oct 1, post-rename). The 2026-09-30 audit jar was `0ceeaf40…`. |

### 0.2 Dependency artifacts inspected (bytecode, via `javap`)

| Artifact | Path | Used for |
|---|---|---|
| MCA 7.6.26+1.20.1 (the version installed in both local test instances) | `~/.gradle/caches/modules-2/files-2.1/maven.modrinth/minecraft-comes-alive-reborn/7.6.26+1.20.1/ee59…/` | speech, translation, constraints, family tree, village, interact screen, dialogue engine |
| MCA 7.7.1-beta.2+1.20.1 (newest fleet member, `forge.net.conczin.mca` root) | `…/7.7.1-beta.2+1.20.1/f935…/` | same classes, to confirm nothing drifted |
| MCA 7.6.20, 7.7.0-beta.2, 7.7.1-alpha.2, 7.7.1-beta.1 | same cache | present; class inventories checked, not disassembled |
| MCA NeoForge 7.7.33+1.21.1 | `~/.gradle/caches/modules-2/files-2.1/net.conczin.mca/mca-neoforge/7.7.33+1.21.1/` | port parity note only |
| Townstead 0.7.6+1.20.1 (the version installed locally) | `/home/otectus/Projects/AlexsButchery/build/packtest/phase4/mods/townstead-0.7.6+1.20.1.jar` | RPG screen, literal TTS, its MCA mixins |

No decompiler is installed; all upstream facts are from `javap -c -p` and are listed in Appendix A
with the exact method they came from. Townstead 0.7.7 jars are not present on this machine
(`docs/ROADMAP.md` §5 records probe runs against them on 2026-09-22; the files are gone).

### 0.3 Local reproduction environments

| Environment | What it is | Relevance |
|---|---|---|
| CurseForge instance **MCA Addons** (`/home/otectus/Documents/curseforge/minecraft/Instances/MCA Addons`) | Forge 47.4.23, MCA 7.6.26, Townstead 0.7.6, Conversations 1.8.0 (`0ceeaf40…`, audit build), Quests 1.7.1, Reputation 0.6.1, Crime 0.7.5, `en_us`, client style `MINIMAL`, motion `REDUCED`, reveal `OFF`. `config/mca.json`: `useMCAVoices=true`, `enableOnlineTTS=false`. **No resource packs** (`resourcePacks:[]`), so no MCA voice pack: this instance cannot play NPC speech at all today. Linux narrator (`flite`) failed to load. | Smallest client for Workstreams A/B/C; needs the MCA Voices pack or online TTS to exercise B. |
| CurseForge instance **Ultima** | ~330 mods incl. MCA 7.6.26, Townstead 0.7.6, Conversations 1.8.0 (`87684114…`), Kingdoms 0.1.1, Capitals (`lukis-grand-capitals-1.1.3`, `mcacapitals` absent), Recruits, Alex's Mobs, Lycanites, Serene Seasons. Many saves. | Closest local analogue of a “heavy pack” for Workstream D. |
| Packaged Forge 47.4.23 dedicated server | `/home/otectus/Projects/MCAKingdoms/build/forge-server/` (used by `MCAKingdoms/tools/test/integration_runtime.py` and `production_runtime.py`, and by the 2026-09-30 audit boots) | Controlled server benchmarks and two-client checks. |
| Profilers | `spark-1.10.53-forge.jar` and `observable-4.4.2.jar` in instance **MC Eternal 2** `mods/` | Copy into disposable server/client `mods/` for Workstream D. |

No mod matching “Social Expansion” exists in any local instance; see §7.

---

## 1. Disposition of each report

Summary first, detail after.

| # | Report | Classification | Confirmed cause vs. hypothesis | Already addressed in source? |
|---|---|---|---|---|
| 1 | Interface text ≠ chat log; replies match the hidden text | **Not reproduced in current source on the local screen path**; **reproduced by construction in every artifact older than 1.2.0**; **suspected with source evidence** on two paths current code does not cover (network-delivered `VillagerMessage`; NeoForge port) | Cause of the historical defect is confirmed (three component instances, three pool draws). Current source keeps one instance for GUI, chat and TTS on the local path. | Partly: `VillagerMessageMixin` (1.2.0) covers the local path only; the stash is `transient`, so network-delivered messages still re-parse, and the port has no mixin. |
| 2 | Speech replays an opening greeting | **Confirmed mechanism in bytecode of MCA 7.6.26 and 7.7.1-beta.2**; runtime reproduction still owed (no voice pack / online TTS locally) | Confirmed: an explicit `/N` key never updates MCA’s `lastResolvedKey`, so the component is associated with whatever pooled key was resolved last and MCA speaks that. | No. `VillagerMessageMixin` makes the association *consistent*, not correct. |
| 3 | Children speak like parents | **Confirmed content defect**; predicate direction verified correct | The two family topics are chimeras: `checkin_child` (NPC is the player’s child) carries a parent-voice response page; `ask_parent` (NPC is the player’s parent) carries child-voice sub-branches. | No. `RelationshipRoles`, `McaContextSource`, `GreetingPolicy` and MCA’s `kids`/`parent` constraints are all directionally consistent; only authored pages are wrong. |
| 4 | Severe tick lag attributed to villagers | **Unconfirmed attribution**; Conversations does no force-loading and no per-villager-per-tick work; **one concrete coincident 600-tick burst and three store-wide prunes identified** | Hypothesis with source evidence; needs measurement. | Lifecycle hold fix (audit F2) is in source and untested in game. |

### 1.1 Report 1 — the interface shows different NPC text from the chat log

**How a line reaches the three surfaces today (MCA 7.6.26, Forge).** The server sends an
`InteractionDialogueQuestionResponse(silent, text)` whose text is a JSON string
(Appendix A, A-7). On the client, `ClientInteractionManagerImpl.handleDialogueQuestionResponse`
calls `InteractScreen.setLastPhrase(text, silent)`, which, when not silent, calls
`villager.sendChatMessage(text, player)` and splits the *returned* component into
`dialogQuestionText` (A-6). `Messenger.sendChatMessage` on the client builds a `VillagerMessage(name,
message, uuid)` and hands it straight to `ClientInteractionManagerImpl.handleVillagerMessage`, which
adds `getMessage()` to the chat HUD and passes `getContent()` to `SpeechManager.onChatMessage`
(A-6). `VillagerMessage` stores `prefix` and `message` as JSON strings (A-7), so without our mixin
each getter parses a fresh component and MCA’s client-side pool draw (`PooledTranslationStorage.get`,
a bare `nextInt`, A-3) runs once per instance: three instances, three sentences. That is the defect
the reporter describes, and it is exactly what `mixin/client/VillagerMessageMixin.java:76-105` closes
by serving the constructor’s own instances back from both getters.

**Why the current source does not reproduce it on the local path.** The Conversations card renders
`exactQuestion`, which `InteractScreenChoiceMixin.java:81-98` captures at MCA’s `Font.split` call as
`component.copy()`; a copy shares the `TranslatableContents` instance and therefore the cached
decomposition, so the card, MCA’s own panel, the chat line and the speech lookup all read one draw.
`DialoguePresentationBuilder.java:90-94` and `SpeakerTextStyler.java:18-60` restyle by visiting the
same component and never re-create a translatable from its key. The delivered history
(`ClientDialogueHistory.java:52-54`) records that same component. Server-pinned lines
(`locale/LineVoice.java:106-125`) are concrete `/N` keys and cannot differ at all.

**Where it can still happen, from source evidence.**

1. **Artifacts older than 1.2.0.** Any reporter on a pre-August build sees the three-draw bug on
   every pooled line. Shipped jars 1.5.2, 1.6.3, 1.7.3 and 1.8.0 all contain the mixin class.
2. **Network-delivered `VillagerMessage`.** The stash is `transient` and `null` over the wire, so
   MCA’s own server-sent villager chatter still parses twice (chat and speech can disagree). No
   Conversations line travels this way today (`ChatDelivery` uses `sendSystemMessage`;
   `McaCompat.sayInDialogue` uses the question packet), so this is upstream-only until WP-B3.
3. **NeoForge port.** It has no `VillagerMessageMixin`. MCA NeoForge 7.7.33 keeps `Component`
   fields in `VillagerMessage` (A-7b), so the parse bug does not exist there, but the speech
   association defect (§1.2) does.
4. **Resource or language reload mid-conversation** re-decomposes unpinned pooled components;
   chat keeps the old wrapped lines while the panel re-splits. Rare; covered by the agreement test.
5. **Townstead’s RPG screen** does not echo in-screen lines to chat at all: its
   `ClientHandlerImplMixin` cancels MCA’s three handlers while `RpgDialogueScreen` is open (A-9), so
   a reporter seeing a chat log *and* a different interface text was on MCA’s `InteractScreen`, not
   on Townstead’s.

**What the current code does not guarantee** and what Workstream A therefore adds: nothing ties
the answer offer to the line it belongs to. `InteractScreenChoiceMixin.java:300-315` accepts an
offer when its question id and answer ids equal MCA’s screen fields; the text shown is simply the
last `setLastPhrase`. Multi-line turns, silent menu prompts, the terminal re-offer
(`ChoiceSelectionService.java:191-220`), `TopicExhaustion.onPageOffered` (a line sent from *inside*
the offer hook, `mixin/NetworkHandlerMixin.java:94-101`) and a late packet after a handoff all rely
on wire order alone. That is adequate when nothing goes wrong and unverifiable when something does,
which is why the reporter could not tell us which line the options belonged to.

### 1.2 Report 2 — NPC speech repeatedly plays an opening greeting

**Confirmed mechanism (A-1, A-2, A-4, identical in 7.6.26 and 7.7.1-beta.2).**

1. `MixinTranslationStorage.mca$onGet` (HEAD of `ClientLanguage.getOrDefault`, SRG `m_118919_`)
   computes `key' = DialogueType.applyFallback(key)` and asks the pool for `key'`. **Only when the
   pool answers** (the key is a pool base) does it set `CommonSpeechManager.INSTANCE.lastResolvedKey`
   to the concrete key it drew. An explicit `…/N` key is not a pool base
   (`PooledTranslationStorage` indexes bases with the trailing number stripped, A-3), so the pool
   answers `null` and `lastResolvedKey` keeps its previous value.
2. `MixinTranslatableText.mca$updateTranslations` (TAIL of `TranslatableContents.decompose`, SRG
   `m_237524_`) does `translations.put(this, lastResolvedKey)` for **every** translatable that
   decomposes while `lastResolvedKey != null`.
3. `SpeechManager.onChatMessage(component, uuid)` looks the component’s contents up in that map
   (`LimitedLinkedHashMap`, 100 entries, keyed by `TranslatableContents.equals`, i.e. by key and
   args) and calls `speak(mappedKey, uuid, translatable=true)`; a `LiteralContents` is spoken as
   literal text; anything else is silent.
4. `speak`: with `enableOnlineTTS`, it resolves the mapped key through `Language` and sends the text
   to the configured backend; without it, it plays `mca_voices:<mappedkey>.<gender>.<tone>` if such
   a sound exists (the MCA Voices resource pack), else nothing.

Every line Conversations pins (`LineVoice.pin`, all `conversations_say` output, the hub/category
prompts that are single keys) therefore decomposes under a **stale** `lastResolvedKey`: the last
pooled key any component on that client resolved. In an MCA interaction that is typically MCA’s own
`dialogue.main/N` greeting pool (“Why, hello there!”, seven variants in MCA’s
`assets/mca_dialogue/lang/en_us.json`, the kind of line a voice pack records) or our own pooled hub prompt
`dialogue.conversations/N` (`src/main/resources/assets/mca_dialogue/lang/en_us.json:14698-14702`,
unrecorded). With a voice pack the stale greeting recording plays; with online TTS the stale key’s
*text* is spoken. Both read as “it keeps saying the opener”. `VillagerMessageMixin` does not change
this: it makes all three surfaces share the instance that carries the stale association.

**What this is not.** It is not the accessibility narrator (`client/dialogue/DialogueChoiceNarrator`
speaks focused answers through `Minecraft.getNarrator()`, a separate system that was unavailable on
the local machine because `flite` is missing). It is not Townstead’s screen: there,
`RpgDialogueScreen.setLastPhrase` → `speakDisplayedText` → `TownsteadLiteralTts.speak(text)` (A-9),
which speaks the *displayed* text and only for `enableOnlineTTS` with model `realtime`, `player2` or
`elevenlabs`; MCA’s `SpeechManager` is bypassed while that screen is open.

**Runtime status.** Not yet reproduced here: neither local instance has the MCA Voices pack or
online TTS enabled. WP0 contains the reproduction recipe and the diagnostic that captures the mapped
key at `onChatMessage` time so the recipe produces evidence rather than impressions.

### 1.3 Report 3 — conversations with children use parent language

**Predicates are right (A-5, both jars).** `Constraint.KIDS` (`"kids"`) is
`Relationship.IS_PARENT.asConstraint()`, and `IS_PARENT(villager, partner)` is
`villager.getFamilyEntry().isParent(partnerUuid)`: the *player is one of the villager’s parents*.
`Constraint.PARENT` (`"parent"`) is `IS_KID.asConstraint()`, and `IS_KID` looks the *player’s* node
up and tests `node.isParent(villagerUuid)`: the *villager is one of the player’s parents*.
`FamilyTreeNode.isParent(id)` is `streamParents().anyMatch(id)`. This matches
`conversation/RelationshipRoles.java:12-17` (`parent` = player is the villager’s parent, `child` =
player is the villager’s child), `Relationships.rolesOf` (`conversation/Relationships.java:53-72`,
UUID membership in the villager’s parents/children/siblings), `McaContextSource` (`player.is_parent`
/ `player.is_child`, `context/McaContextSource.java:196-204`) and `GreetingPolicy.family`
(`chat/GreetingPolicy.java:121-130`: player-is-parent → child-voice hail “You’re back! I was hoping
you’d come by.”; player-is-child → parent-voice hail “There you are! Come here, you.”).

**The content is wrong.** `src/main/resources/data/mcaconversations/dialogues/conversations.family.json`:

- `checkin_child` (constraint `kids`, so the NPC is the player’s child) opens in a child’s voice
  (`family.child.doing/little/teen/again`: “Pretty good! I beat the miller’s kid at races twice.”).
  Its response page `conversations.topic.checkin_child.respond` then offers the player
  “Tell me how they really are.” / “You’re doing right by them.” / “Children are all the same.” and
  the *child* answers as a **parent talking about their own children**: `family.child.listen`
  (“Better than last month. Worse than I’d like. That’s children.”), `family.child.praise` (“That’s
  the thing nobody says to a parent. Thank you, %1$s.”), `family.child.dismiss` (“Mine aren’t. Mine
  are theirs.”); the follow-up page continues in that voice (`family.child.guide`,
  `family.child.play_along`, `family.child.scold` → `conversations.topic.family.scolded.close`,
  “I’ll raise mine my way”), as do the repair beats (`checkin_child.rebuffed.*`, “People are, about
  other people’s children”). Only `ask_promise` → `checkin_child.promise.*` is written for a child.
- `ask_parent` (constraint `parent`, so the NPC is the player’s parent) opens correctly
  (`family.parent.proud/worried/again`) and its response page is correct, but two sub-branches are
  written for a **child**: `ask_parent.pride.*` (“I mended the fence myself. Nobody asked me to and
  nobody’s noticed but you.”, “I can read the long words now”) and `ask_parent.event.*` (“You all
  went to the thing and I stayed with the neighbours. Nobody said why.”, “I’m not a baby.”).
- Neither topic’s deeper pages branch by age, although the catalog admits `toddler`, `child`,
  `teen` and `adult` for both (`conversation_catalog/topics.json:19-24, 69-74`), so a toddler who
  passes the starter can reach “I mended the fence myself”.
- The chat intent `family.checkin_child` (`chat_intents/relationships.json:113-131`) matches “how
  are the kids”, which is a question for a parent, and routes it into the child-constrained topic;
  the topic label `dialogue.chatmode.topic.checkin_child` is “the kids”.

This is a swap and a re-home, not a predicate change. The parent-voice pages are good material for a
topic the corpus does not have (asking a villager who *has* children about them); the child-voice
`pride`/`event` branches are the missing depth for `checkin_child`. §2.3 and WP-C2 lay it out.

`AgeSeparationTest` (structure by age) and `ConstraintVocabularyProbeTest` (tokens exist) remain
true and were never evidence about perspective; WP-C1 adds the perspective lint that would have
caught this.

### 1.4 Report 4 — server tick lag attributed to villagers

**What Conversations does per tick (`event/ConversationsEvents.java:360-417`).**
`ChatModeScheduler.drain` (deadline queue), `VillagerAttention.tick` (sweep of a small hold map),
`tickDiscussions` (one player lookup, one entity lookup and one `ConversationMovementController.judge`
per live *GUI* discussion), and nothing else. There is no `LivingTickEvent`, no entity-join hook and
no per-villager work: the only code Conversations injects into villager AI is
`InteractTaskMovementMixin.followPlayer`, which runs only while a villager has an interacting player
and does one map read. Nothing calls `getChunk`, adds a ticket or forces a load:
`judgeDiscussion` resolves villagers with `ServerLevel.getEntity(UUID)` (loaded entities only), and
MCA’s `Village.getResidents(ServerLevel)` maps resident UUIDs through the same lookup (A-8), so
`McaHandles.loadedResidents` is loaded-only as the handoff asked us to verify. Observable attributes
by *entity tick*; Conversations’ server-tick-event work would not appear under a villager at all,
while MCA’s brain, Townstead’s `VillagerServerTickMixin` (fatigue and dispatcher per villager per
tick, A-9) and other AI mods would.

**What is periodic and coincident.** Every `gossipScanIntervalTicks` (default 600) the handler runs,
on the *same tick*: `GossipDetectors.scan` (nearest village per online player, all loaded residents
of each distinct village, relationship diff, residency diff, Townstead snapshot per loaded resident,
village buildings), `DispositionSavedData.prune` and `ProgressSavedData.prune` (every record of
every villager, `disposition/DispositionStore.java:115-132`, `progress/ProgressStore.java:128-145`),
`History.prune` (every stored villager history, `history/ConversationHistoryStore.java:235-247`,
each walking its episodes, opinions and threads), `RumourPropagation.sweep` (an AABB query per
player, up to three speakers, a full sort of each speaker’s episodes) and `ConversationSessions.sweep`.
`CourtNewsPoller.tick` runs every `capitalNewsPollSeconds*20` = 600 ticks, so it lands on the same
tick; `GreetOnApproach.scan` and `TownsteadDialogueTracking.sweep` run every 40 ticks, so one in
fifteen of theirs lands there too. The prunes scale with **total stored history, loaded or not**,
and the history prune is day-granular yet runs 40 times per in-game day. That is the kind of periodic
spike a 100-tick Observable window may average away and a spark timeline shows plainly. It is a
hypothesis until measured; §3 WP-D4 measures it.

**Per-conversation per-tick cost worth a look.** `ConversationMovementController.judge`
(`chat/ConversationMovementController.java:153-172`) reads `TownsteadChatPolicy.Facts.townstead`
each tick for each held villager, and `Townstead.snapshot` caches for one tick
(`compat/Townstead.java:29`), so every live GUI discussion costs a reflective Townstead read per
tick. Bounded by concurrent conversations, cheap per call, but it is the one steady per-tick item.

**What the audit left untested.** The September hold fix (`judge` with `chat=true`, audit F2) has a
unit test and no in-game observation. WP-D4 includes it.

---

## 2. Chosen design

Principle from the handoff: one identified utterance and one authoritative answer offer per step,
explicit directional family facts, measured and bounded background work. Extend what exists.

### 2.1 The utterance contract

**Identity.** An utterance is identified by `(conversation identity, utteranceSeq)`.

- `conversation identity` is the existing `ConversationHandle.sessionId` for a managed discussion
  (`conversation/ConversationHandle.java`), and for chat-mode exchanges without a handle the session’s
  new `sessionNonce` (a UUID minted when the `ConversationSession` object is created). Nothing new is
  persisted.
- `utteranceSeq` is a per-session monotonic counter, distinct from the offer revision
  (`ConversationSession.offerRevision`, global) and from question ids. One turn may emit several
  utterances; a reused question produces a new utterance.

**What the server records per utterance** (new record `Utterance` on `ConversationSession`, bounded
ring of the last 8): `seq`, `kind` (`NPC_LINE`, `SILENT_PROMPT`, `FEEDBACK`), `frontend`, the
component’s key with MCA markers stripped, the chosen variant (`/N` or 0), the content generation,
`silentFlag`, and the offer revision that later referenced it (filled by `setOffer`).

**Carrier.** The identity travels *inside the component* so that every surface that receives the
component can read it and no second packet has to be correlated by order:

- The tag is an **extra, unused translatable argument**: an empty literal component whose `Style`
  insertion is `mcaconversations:utt/<identity>/<seq>/<flags>`. Unused args are never rendered,
  never inserted into chat on shift-click, survive `Component.Serializer.toJson/fromJson` (MCA’s
  packets serialise components as JSON, A-7), and `TranslatableContents.equals` includes args, so
  **two intentional identical sentences are two different map keys** in MCA’s speech association.
- Literal lines (Capitals chronicle text, the chat fallback, Townstead species-voice lines) are wrapped as
  `Component.translatable("%s", literal, tag)`, which renders identically.
- A small common helper `conversation/UtteranceTag` (write/read, pure, unit-tested) is used by the
  server and the client. Reading tolerates absence: an untagged component is “unknown utterance”.

**Where tags are applied (server).**

1. Conversations’ own lines: `McaCompat.sayInDialogue` after `LineVoice.pin`
   (`compat/McaCompat.java:193-211`) and `ChatDelivery.villagerSays` after `voice()`
   (`chat/ChatDelivery.java:72-96`).
2. Everything MCA sends to the screen for a *managed* discussion: in `NetworkHandlerMixin`
   (`mixin/NetworkHandlerMixin.java:68-76`), when `isQuestionResponse(message)` and the player has a
   current handle, rebuild the packet with the tagged component through the existing
   `QUESTION_RESPONSE_NEW` + `NETWORK_SEND_TO_PLAYER` handles and cancel the original. This covers
   MCA-authored page prompts (e.g. `dialogue.conversations.family`, silent or not) and lines from
   MCA’s native `say`. Unmanaged MCA interactions (no handle) are left byte-identical.
3. Chat-mode `QuickReplies` option blocks and heart feedback are `FEEDBACK` utterances: tagged so a
   client can dedupe and so they are never spoken.

**Offer linkage (protocol 5).** `ChoiceOfferS2C` gains `long utteranceSeq`: the seq of the last
utterance the session delivered *before the offer was sent*, or `-1` when none exists. It must be
read at send time, after `TopicExhaustion.onPageOffered` has had its chance to speak
(`mixin/NetworkHandlerMixin.java:88-109` currently records the offer before that call; the seq is
taken when `ChoiceOfferS2C.from` is built, which is after). `reofferAfterTerminal`
(`conversation/ChoiceSelectionService.java:191-220`) re-offers with the current seq, which is the
terminal line the client is already showing. `network_protocol` becomes 5 in `gradle.properties`;
`NetworkProtocol.FALLBACK` follows.

**Client ledger.** A new client-only `client/dialogue/ClientUtteranceLedger` keeps the last 16
delivered utterances per connection: identity, seq, kind, the resolved display component (the
instance the screen drew), the plain speech text derived once from it, and which surfaces have seen
it (screen, chat, speech). Recording points:

- `InteractScreenChoiceMixin.mcaconversations$captureDisplayedQuestion`
  (`mixin/client/InteractScreenChoiceMixin.java:81-98`): read the tag from the captured component;
  keep the local `questionRevision` as a fallback for untagged lines.
- `VillagerMessageMixin` (both getters) for chat and speech.
- The chat HUD for typed chat (optional WP-B3; a HEAD, non-cancelling hook on vanilla
  `ChatListener.handleSystemMessage`).

`ClientDialogueHistory.spokenIdentity` becomes the utterance identity when a tag is present, so one
line that reaches the card and the chat is one history entry by construction rather than by local
revision.

**Agreement rule (the one behavioural change players see).** The card is actionable only when the
offer’s `utteranceSeq` equals the ledger’s latest displayed seq for this conversation, or the offer
carries `-1`, or the displayed line is untagged (legacy or third-party packet). When the offer is
ahead of the display (its line has not arrived), the answers render but are not clickable or
keyboard-selectable for up to a 40-tick grace; the renderer shows the existing “selecting…” footer.
When the grace lapses the card becomes actionable anyway and a bounded DEBUG line names the
identities (fail-open: a correlation bug must never freeze a conversation). When the display is ahead
of the offer (a later line arrived, e.g. after a handoff) the old offer is treated as superseded
exactly as a `SUPERSEDED` clear would be. Paging, resizing, utilities, focus and the typewriter never
change the ledger (they do not call any recording point).

**Resolution stability.** The client resolves a line once per utterance per locale: the ledger stores
the display component; speech text is `component.getString()` with MCA’s emotion markup stripped
by the same rule Townstead uses (`client/townstead/ConversationsEmotionTags.strip`). Repeated
`copy()`s share contents and never reroll. Pool draws for *unpinned* MCA keys still happen once per
instance; the ledger holds that instance.

### 2.2 Speech ownership

**Single owner.** MCA’s `SpeechManager` stays the only thing that plays NPC speech on MCA’s screen
and for MCA-delivered messages; Townstead’s literal TTS stays the owner while `RpgDialogueScreen` is
open (it cancels MCA’s handler itself). Conversations plays nothing and introduces no backend.

**The fix is the association, not the player.** At the message boundary, before MCA reads its map,
Conversations writes the *correct* key for utterances it owns:

- In `VillagerMessageMixin`: at `getMessage()` HEAD set `CommonSpeechManager.INSTANCE.lastResolvedKey`
  to the exact resolved key of our line (so MCA’s own TAIL association is right when the chat HUD
  decomposes it), and at `getContent()` HEAD `translations.put(contents, exactKey)` defensively
  (so the speech lookup is right even if something decomposed the component earlier).
- “Exact resolved key” = what `DialogueType.applyFallback(rawKey)` returns on this client at this
  moment (public static, reached reflectively through a new client-side binding on the existing
  `McaBinding` root), i.e. the personality/gender/type variant the screen actually rendered. For
  owned keys this is deterministic: the one random branch in `applyFallback` (`#P`, profession) only
  matters when a profession-prefixed key exists, and none exists for owned namespaces (a resource lint
  in WP-B1 asserts this). If `Language.has(resolved)` is false (a resource pack removed the variant),
  fall back to the marker-stripped key and log once per key.
- Scope: components carrying our tag, or whose stripped key starts with `dialogue.conversations.`
  or `dialogue.chatmode.` (our extensions of MCA’s `dialogue.main/8..12` remain MCA’s pool and keep
  MCA’s own association). MCA’s own single-key lines have the same upstream defect; the plan leaves
  them to upstream and includes a reproducible report in §7.

**What each backend then does, and why that is correct.** With the MCA Voices pack (offline),
`speak(ourKey, true)` looks for `mca_voices:<ourkey>.<gender>.<tone>`, finds nothing and stays
silent: no recording exists for Conversations lines and none is invented. With online TTS, the
backend receives the text of the exact key that was displayed. Variant pinning (`LineVoice`) and
anti-repetition are untouched; the voice pack guard in `MCAClientMixin` is untouched.

**Queue, dedupe and cache.** MCA already drops a new line for a speaker whose previous sound is still
playing (A-4) and never queues. Conversations adds no queue. The ledger’s `seenBy(SPEECH)` flag is
what the optional typed-chat route (WP-B3) uses for exactly-once, keyed by utterance identity, never
by text. Audio cache identity is upstream (`AudioCache.getHash(text)` plus voice parameters); nothing
here changes the bytes a backend receives except that they are now the right sentence. Cancellation on
partner change, disconnect or unload is upstream’s (entity-bound sound instances stop with the entity);
the ledger is cleared with the connection (`ClientChoiceMessages.synchronizeConnection`).

**Typed chat (decision).** Chat-mode lines are plain system messages and are not spoken by anyone
today (`chat/ChatDelivery.java:135`); Townstead only captures them into its box while its screen is
open (A-9). Adding speech there is new behaviour, not a regression fix. WP-B3 specifies it as an
opt-in client toggle that calls `SpeechManager.INSTANCE.onChatMessage(taggedLine, villagerUuid)` from
the chat hook after the association repair, deduped by utterance, default **off**. The owner decides
whether it ships with this remediation.

### 2.3 Directional family rules

**Facts.** Keep MCA’s UUID family graph as the only source: `Relationships.rolesOf` and the `kids`
/ `parent` constraints already read it. The matrix from the handoff holds with the verified
semantics:

| NPC speaking to the player | MCA constraint that passes | `RelationshipRoles` | Route |
|---|---|---|---|
| Player’s young child | `kids` | `parent=true` | `checkin_child`, child-voice, age-branched |
| Player’s adult child | `kids` | `parent=true` | `checkin_child`, adult-child voice |
| Player’s parent | `parent` | `child=true` | `ask_parent`, parent-voice |
| Unrelated child or teen | neither | none | general age-appropriate topics only |
| Sibling, spouse, unrelated adult | `family`/`spouse`/none | `sibling`/`spouse`/none | only what those roles gate |
| Unreadable tree | none (predicates fail soft) | `NONE` | neutral wording; no family route |

**Metadata, narrow and explicit.** Add one optional field in two places and one lint:

- `conversation_catalog/topics.json` topic rows: `"player_role": "parent" | "child" | "sibling" |
  "spouse"`, meaning *what the player is to the speaker* for this topic. `TopicEntry` parses it;
  `TopicGate.allows` enforces it beside the age allow-list on every entry path (GUI answer list,
  direct packet, numbered reply, chat, dynamic hub) using `Relationships.rolesOf`. This is a
  server-side belt beside MCA’s constraint, not a replacement; its real value is that the catalog
  now *states* the perspective.
- `conversation_beats` context: `"player_role"` with the same vocabulary, next to the existing
  `ages` and `relationships` (`conversation/BeatContract.java:113-137`).
- `FamilyPerspectiveTest` (new, beside `AgeSeparationTest`): walks every catalog topic that declares a
  `player_role` through every `next` page reachable from its starters and asserts that every beat
  whose `say` key is reached declares the same `player_role`, and that no `say` key is shared between
  two different roles. It also emits the corpus report (§4) as a test artifact.

**Content.** Re-home rather than rewrite: §4 maps every affected key. The parent-voice pages become a
new topic for villagers who have children (`their_children`, gated by a new
`conversations_family` condition `{"has": "children"}` evaluated through `McaCompat.getChildren`);
the child-voice `pride` and `event` branches move under `checkin_child`; the handful of lines that
only make sense in one direction are rewritten; the deeper `checkin_child` pages gain `age_group`
conditioned results so toddler, child, teen and adult do not share a sentence. Portuguese and the
six voice families follow through `src/content/voices` and `generateVoiceOverlays`.

### 2.4 Performance

Lifecycle first (nothing changes in `ConversationLifecycle`, `ConversationPresence`, the presence
lease or the hold rules); no tickets ever; then make the maintenance proportional and staggered:

- One **phase table** in `ConversationsEvents`: gossip at `tick % interval == 0`, rumours at
  `+interval/4`, disposition and progress prunes at `+interval/2` and at most once per in-game hour,
  history prune at `+3*interval/4` and only when the in-game day changed, session sweep at
  `+interval/8`, court poll offset by 7 ticks from the gossip phase, greet scan and Townstead sweep
  spread across players by `playerUuid.hashCode() % 40`.
- **Bounded, resumable batches** for the three store prunes (a cursor over villagers, N per call,
  default 64) and for `GossipDetectors.scanVillage` (residents per sweep, default 128, resuming next
  sweep). Items examined are bounded, not only outputs.
- **Counters** (`debug/PerfCounters`, lock-free longs): per section of the tick handler, max and
  total nanoseconds over the last 1,200 ticks, and the live counts the handoff asks for (sessions,
  holds, queued replies, history store size, residents examined, candidates examined). Exposed by
  `/conversations perf status|reset`, operator-only.
- The per-tick Townstead read in `judge` is served from a `townsteadContextCacheTicks` snapshot
  instead of a one-tick one, unless the villager is in a reaction lock (verified by counter).
- A measurement campaign (WP-D4) with spark and Observable on identical disposable worlds, before and
  after, with budgets set per the named hardware before final validation.

### 2.5 Explicitly not changed

No dialogue engine rewrite; no new TTS provider or audio asset; no change to `ChoiceSelectionService`
authority or to `HandleAuthority`; no access transformers; no MCA import in `src/`; no save schema
change; no override of `MCAClient.useExpandedPersonalityTranslations` beyond the existing locale
widening; no global villager tick throttling; Townstead keeps its camera, typewriter, menu grouping
and input; `numberedResponses=false` (`MCA_ORIGINAL`) keeps MCA’s own panel.

---

## 3. Ordered work packages

Order matters: instrumentation and reproduction first, text identity before speech, content after
the perspective lint exists, performance measurement before and after its changes. Each package
lists what it touches, what it depends on, the intended behaviour, risks and the tests that accept
it. “Gradle” means through `/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh <dir> <task>`.

### WP0 — Baseline, reproduction harness and diagnostics (start immediately)

Files: `command/ConversationsCommand.java` (new `client` and `perf` subtrees), new
`client/dialogue/dev/ClientDiagnostics.java` (client-only), new `debug/PerfCounters.java`,
`docs/` (this plan’s validation log), `MCAKingdoms/tools/test/production_runtime.py` is *read*, not
modified (copy its launch sequence into a Conversations-local
`tools/test/conversations_server.py` that only boots the packaged server with a given mod list).

Behaviour:

1. `/conversations client diagnose` (client command, physical client only): prints MCA root,
   whether `VillagerMessageMixin`, `InteractScreenChoiceMixin` and `MCAClientMixin` applied (a static
   “applied” latch set by each injector, like `ChatModeSession.markRedirectInstalled`), the dialogue
   style, Townstead screen presence, MCA `enableOnlineTTS`/model, voice-pack presence
   (`packId contains "MCAVoices"`), and the last 8 ledger entries with identity, seq, kind,
   resolved-key hash, and which surfaces saw them. Text is never printed unless
   `-Dmcaconversations.diag.text=true` is set.
2. `/conversations client speech-trace on|off`: while on, log at INFO (client) one line per
   `onChatMessage` our mixin observes: utterance identity, our computed exact key, MCA’s
   `lastResolvedKey` at that moment, and the key MCA’s map held for the component *before* repair.
   This is the evidence line for Report 2.
3. `/conversations perf status|reset` (operator): the counters of §2.4, plus loaded chunk ticket
   counts for the overworld (read-only, from `ServerChunkCache`) so “no Conversations tickets” is
   observable.
4. `tools/test/conversations_server.py`: boots the packaged Forge 47.4.23 server from
   `/home/otectus/Projects/MCAKingdoms/build/forge-server/libraries` with an explicit `--mod` list, a
   seeded flat world copied from `--seed-world`, `view-distance`/`simulation-distance` arguments, and
   writes `artifacts.sha256`. It is the vehicle for every server check below.

Depends on: nothing. Risks: none functional; keep the latches `volatile` and the commands
operator-gated. Acceptance: unit tests for the counter arithmetic; a dedicated-server boot with the
new commands registered; a client run of `/conversations client diagnose` in **MCA Addons**.

### WP-A1 — Utterance identity on the server

Files: new `conversation/UtteranceTag.java`, new `conversation/Utterance.java`,
`conversation/ConversationSession.java` (seq, ring, `sessionNonce`, `lastUtteranceSeq()`,
`recordUtterance`), `compat/McaCompat.java:193-211` (`sayInDialogue` tags after pin),
`chat/ChatDelivery.java:72-157` (tag the villager line and the options/feedback blocks),
`mixin/NetworkHandlerMixin.java:68-76` (re-tag MCA question packets for managed discussions),
`compat/mca/McaHandles.java` (helper `retagQuestionResponse(message, component)` using the existing
`QUESTION_RESPONSE_NEW`/`QUESTION_RESPONSE_SILENT` handles), `locale/LineVoice.java:106-125`
(preserve existing args, append nothing itself).

Behaviour: every NPC line, silent prompt and feedback block that leaves the server for a Conversations
frontend carries a tag; the session remembers the last 8. Unmanaged MCA packets are untouched. A tag
is applied at most once (re-tagging an already tagged component is a no-op).

Depends on: WP0 (for the diagnostic that shows tags). Risks: a datapack line whose format string
uses `%N$s` beyond its real args would already be broken; the tag only appends. Townstead’s
`getTranslatable` overwrite returns MCA-shaped translatables for keys that have no species variant,
which is every Conversations key unless a pack adds species lines, and a server-formatted *literal*
when a species variant exists (A-9); literals take the `%s` wrapper tag, are deterministic (no client
draw) and are spoken by MCA as literal text, so neither case needs special handling. Acceptance: `UtteranceTagTest` (round trip through `Component.Serializer`, equality
semantics, literal wrapping renders identically), `ConversationSessionUtteranceTest` (seq monotonic,
ring bounded, re-offer references the current seq), `NetworkHandlerMixinRetagTest` through the
existing pinned-hop fixtures, `MixinTargetProbeTest` unchanged.

### WP-A2 — Protocol 5: the offer names its utterance

Files: `network/ChoiceOfferS2C.java` (field, codec, `from(ref, offer, seq)`),
`conversation/ConversationSession.java` (`ChoiceOffer.utteranceSeq`), `conversation/ConversationSessions.java:70-77`,
`mixin/NetworkHandlerMixin.java:88-115` (take the seq after `onPageOffered`),
`conversation/ChoiceSelectionService.java:191-220`, `gradle.properties` (`network_protocol=5`),
`network/NetworkProtocol.java` (`FALLBACK = "5"`), `tools/parity/parity-1.8.0-adaptations.json`
(re-pin), NeoForge mirror of the same files.

Behaviour: the offer carries the seq of the line it belongs to or `-1`. Nothing else on the wire
changes. Clients and servers on protocol 4 are refused at handshake, as the channel already does.

Depends on: WP-A1. Risks: the protocol bump forces simultaneous client/server update (already the
posture since 1.7.1, `docs/ROADMAP.md` §1). Acceptance: codec round-trip test; `ChoiceOfferS2CTest`
bounds unchanged; a two-sided packaged check (WP5 in §5) that a protocol-4 client is refused with the
channel message.

### WP-A3 — Client ledger and the agreement rule

Files: new `client/dialogue/ClientUtteranceLedger.java`,
`mixin/client/InteractScreenChoiceMixin.java:75-98, 300-333` (record from the captured component;
`active()` consults the agreement rule; `select` refuses while waiting),
`client/dialogue/ClientChoiceState.java:26-32, 55-67` (offer carries `utteranceSeq`; a `waiting`
flag), `client/dialogue/ClientChoiceMessages.java:345-358` (accept passes the seq; `synchronizeConnection`
clears the ledger), `client/dialogue/DialogueChoiceRenderer.java:91-215` (render the waiting state
through the existing footer; no selection while waiting; `ModelKey` keys on utterance seq instead of
the local `questionRevision` when a tag exists), `client/dialogue/ClientDialogueHistory.java:39-58`
(identity from the tag), `mixin/client/VillagerMessageMixin.java` (record `CHAT`/`SPEECH` sightings).

Behaviour: §2.1 agreement rule, fail-open after 40 ticks with one DEBUG line per discussion.
Keyboard digits, Enter, Space and clicks are consumed but do nothing while waiting; the narrator
announces “waiting for the villager” once (new key `gui.mcaconversations.responses.waiting`, both
locales). The history drawer shows one entry per utterance however many surfaces drew it.

Depends on: WP-A2. Risks: Townstead’s screen bypasses `InteractScreenChoiceMixin`, so no agreement
gating applies there by design (Townstead owns its panel; our badges and digit routing are unchanged).
Acceptance: `ClientUtteranceLedgerTest` (bounded, per connection, dedupe by identity),
`InteractScreenAgreementTest` driving the pure `active()` decision with offers ahead/behind/equal and
the grace lapse, `DeliveredHistoryTest` for one-entry-per-utterance, and the manual matrix in §5.

### WP-A4 — One instance on the network path too

Files: `mixin/client/VillagerMessageMixin.java` (shadow the target’s own `message`/`prefix` String
fields, parse once into the stash when it arrives `null`, serve the same instances from both getters),
`src/test/java/.../MixinTargetProbeTest.java:109-111` (shadowed fields are checked strictly by the
existing reflection walk).

Behaviour: MCA’s server-sent villager chatter also reads one sentence in chat and speech. Covers
the one remaining two-draw path on Forge.

Depends on: none (can land with WP-B1). Risks: `@Pseudo` shadow rules: `prefix` and `message` are
declared on `VillagerMessage` itself in 7.6.20 through 7.7.1-beta.2 (A-7), so the strict probe passes;
if a future MCA renames them the probe fails the build rather than the game. Acceptance:
`MixinTargetProbeTest` across the fleet; the packaged two-client check that a server-sent chatter line
matches between chat and `speech-trace`.

### WP-B1 — Speech association repair

Files: `mixin/client/VillagerMessageMixin.java` (two HEAD injections call the repair),
new `client/speech/SpeechAssociation.java` (client-only; decides scope, computes the exact key,
writes `lastResolvedKey` and the map), `compat/mca/McaBinding.java` + `McaHandles.java` (new members:
`CommonSpeechManager.INSTANCE` static getter, `lastResolvedKey` field get/set, `translations` field
get, `DialogueType.applyFallback` static; all on the probed root, all replayed by
`McaBindingProbeTest`), `locale/LineVoice.java:252-265` (`stripMarkers` is reused),
new `src/test/java/.../content/NoProfessionOverlayLintTest.java` (no key in any
`assets/mca_dialogue*/lang/*.json` starts with `<vanilla profession id>.dialogue.conversations` or
`.dialogue.chatmode`).

Behaviour: §2.2. The repair runs only for owned or tagged utterances, fails open (any throwable →
MCA’s behaviour unchanged, one DEBUG line), and is observable through `speech-trace`.

Depends on: WP0 (trace), independent of WP-A. Risks: (1) MCA’s `translations` map is 100 entries;
if something decomposes 100 other translatables between `getMessage()` and `onChatMessage()` the
association could be evicted — impossible inside one `handleVillagerMessage` call. (2) Setting
`lastResolvedKey` is a global write; it affects only later associations, which only villager messages
read. (3) `applyFallback` is public static in every fleet jar (A-2); the binding probe turns a rename
into a build failure. Acceptance: `SpeechAssociationTest` with a fake `Language` and fake map
(stale key replaced; untagged MCA key untouched; missing variant falls back; two identical sentences
with different tags keep distinct entries); the real-client recipe in §5 step B with the trace line
showing `mapped-before` ≠ `mapped-after` and audible silence (voice pack) or the right sentence (online
TTS).

### WP-B2 — Speech acceptance evidence and the NeoForge note

Files: `docs/ROADMAP.md` §2 (new rows), `AUDIT.md` is not edited. In the NeoForge port: the same
`SpeechAssociation` (its MCA keeps `Component` fields, so no parse unification is needed, but the
stale association is identical in `net.conczin.mca.entity.CommonSpeechManager`; verify in the 7.7.33
jar the same way before mirroring).

Behaviour: none at runtime. Depends on: WP-B1. Acceptance: the §5 matrix rows for speech recorded with
trace output attached.

### WP-B3 — Typed-chat speech (decision required; default off)

Files: new `mixin/client/ChatListenerSpeechMixin.java` (HEAD on vanilla
`ChatListener.handleSystemMessage(Component, boolean)`, non-cancelling), `client/speech/SpeechAssociation`
(reuse), `McaConversationsConfig` client toggle `chatModeSpeech` (default `false`),
`client/dialogue/ClientUtteranceLedger` (`seenBy(SPEECH)` dedupe).

Behaviour: when on, a tagged chat-mode NPC line (never feedback or option blocks) is handed once to
`SpeechManager.INSTANCE.onChatMessage(line, villagerUuid)` after repair, so it follows MCA’s mode
exactly (silent with a voice pack, spoken online). Off, nothing changes. Townstead’s chat capture
still runs first when its screen is open; if it cancels the message our hook sees nothing.

Depends on: WP-A1, WP-B1. Risks: a bystander client receives the same system line and would also
speak it at the villager’s position; that is correct spatial behaviour but doubles backend requests on
online TTS. Decision: ship behind the toggle or defer. Acceptance: ledger dedupe test; manual check
that a greeting is spoken once per client and never twice on reopen.

### WP-C1 — Perspective metadata, gate and lint

Files: `conversation/TopicEntry.java` (`playerRole()` optional), `conversation/TopicGate.java`
(enforce beside `allowsAge`, through a `Predicate<RelationshipRoles>` seam for tests),
`conversation/BeatContract.java:113-137` (`player_role` in context), new
`src/test/java/.../content/FamilyPerspectiveTest.java`, `DATAPACK.md` (document the field),
`src/test/java/.../conversation/TopicGateTest.java` (role cases).

Behaviour: a topic row may state what the player is to the speaker; the gate refuses the entry when
MCA’s tree says otherwise; the lint fails the build when a reachable beat disagrees with its topic or
when a `say` key is shared across roles. Existing content without the field is unaffected.

Depends on: nothing. Risks: the lint must walk `next` pages exactly as `AgeSeparationTest` and
`ConversationGraphLintTest` already do (reuse `DialogueGraph`); the first run will fail on the
current corpus, which is the point. Acceptance: the lint red on HEAD content, green after WP-C2.

### WP-C2 — Content: split the chimera and finish each direction

Hand-authored files (not generator-owned; `ContentCompiler` owns only `conversations.scene.*`,
`scene_*.json` and the five narrative-template directories): `dialogues/conversations.family.json`,
`dialogues/conversations.topic.checkin_child.*.json`, `dialogues/conversations.topic.ask_parent.*.json`,
`dialogues/conversations.topic.family.scolded.close.json`, new
`dialogues/conversations.topic.their_children.*.json`, `conversation_beats/interior_checkin_child.json`,
`interior_ask_parent.json`, `checkin_child_close.json`, `family_more.json`,
`repair_*_rebuffed_followup.json`, `terminal_family.json`, `interior_family.json`,
`conversation_catalog/topics.json`, `assets/mca_dialogue/lang/en_us.json` and `pt_br.json`
(the dialogue lang namespace is hand-maintained; `LocaleParityTest` enforces both locales),
`src/content/voices/*.json` (the 21 `family.child.*`/`family.parent.*` overlay variants are
generated from here; run `generateVoiceOverlays` and commit the output; `verifyVoiceOverlays` gates),
new condition in `compat/mca/ConversationsMcaRegistrar.java` (`conversations_family: {"has": "children"}`).

The mapping is in §4. Rules while doing it: keep every existing topic id, memory id
(`mcaconversations.cooldown.<topic>`), milestone and save key; new keys only for moved sentences
whose old key would now lie about its speaker; keep overlay coverage above
`SignatureOverlayLintTest.WEIGHTED_COVERAGE_FLOOR` by moving overlay variants with their base keys;
age-branch the deeper `checkin_child` pages with `age_group` conditions the way
`conversations.family.json:45-132` already does for the starters.

Depends on: WP-C1 (so the lint proves the result). Risks: this is new player-facing text in two
locales (estimate in §4); Portuguese needs a native reading (`docs/ROADMAP.md` §3.5 WP12 caveat).
Acceptance: `FamilyPerspectiveTest`, `AgeSeparationTest` (now with the deeper pages),
`LocaleParityTest`, `OverlayLintTest`, `SignatureOverlayLintTest`, `ChatIntentLintTest`,
`verifyGeneratedConversationContent`, `verifyVoiceOverlays`, and the in-game matrix in §5.

### WP-C3 — Chat intents, labels, templates

Files: `chat_intents/relationships.json:113-150` (split: “how are the kids / your children” →
`their_children`; “how are you holding up” → `checkin_child`), `assets/mca_dialogue/lang/*.json`
(`dialogue.chatmode.topic.checkin_child` → “how you’re holding up”, new `…their_children` → “the
kids”), `chat/AgeVoice.java:48-51` (no change; verify the toddler borrow table still maps
`hail.family.child` to the parent-greeting pool only as the documented damaged-tree guard),
`GreetingPolicy` (no change; add a unit test that `family(NONE)` is unreachable from `SocialPolicy`
bands, or make it return a neutral pool if it is reachable).

Depends on: WP-C2. Acceptance: `ChatIntentLintTest`, `ChatCoverageTest`, `GreetingVoiceLintTest`;
typed “how are the kids” at a childless adult yields the gated-topic miss, at a parent opens
`their_children`, at the player’s own child opens nothing from that phrase.

### WP-D1 — Counters and the perf command

Files: `debug/PerfCounters.java` (from WP0), `event/ConversationsEvents.java:360-417` (section
timers), `chat/VillagerAttention.java`, `chat/ChatModeScheduler.java`, `gossip/GossipDetectors.java`,
`history/RumourPropagation.java`, the three store prunes (count items examined),
`command/ConversationsCommand.java`.

Behaviour: zero-allocation `nanoTime` sections; the command prints p50/p95/max per section over the
window, live counts, and ticket counts. Off-path cost is two `nanoTime` calls per section per tick.

Depends on: WP0. Acceptance: unit test of the ring statistics; a server boot where `perf status`
prints after 1,200 ticks.

### WP-D2 — Phase table and bounded batches

Files: `event/ConversationsEvents.java:383-417, 545-605` (phase table; `pruneLivingHistory` only when
`today` changed; prunes in batches), new `history/PruneCursor` (or a generic
`util/ResumableSweep`), `history/ConversationHistoryStore.java:235-247`,
`disposition/DispositionStore.java:115-132`, `progress/ProgressStore.java:128-145`,
`gossip/GossipDetectors.java:103-161` (resident batch with a per-village cursor),
`court/CourtNewsPoller` call site (offset), `chat/GreetOnApproach.java:68-81` and the
`TownsteadDialogueTracking.sweep` call (per-player phase), `CONFIG.md` (document that
`gossipScanIntervalTicks` still sets the period and that maintenance is spread across it).

Behaviour: the same work, spread over the interval, each piece bounded per tick and resumable; no new
config key unless the batch sizes prove to need tuning (then one `maintenanceBatchSize`, bounded
16–1024). Durable history is never discarded by the change; expiry rules are unchanged.

Depends on: WP-D1 (to show the before/after). Risks: a resumable cursor over a mutating map must
tolerate removals (iterate a snapshot of keys per batch). Acceptance: unit tests that a full prune
pass over N villagers completes within ceil(N/batch) calls and removes exactly what the unbatched
version removes; the WP-D4 before/after spark captures show the 600-tick spike replaced by smaller,
offset ones, with no new spike.

### WP-D3 — Per-conversation per-tick reads

Files: `chat/ConversationMovementController.java:153-172`, `chat/TownsteadChatPolicy.java:63-65`,
`compat/Townstead.java:145-170` (a second TTL for hold judgement equal to `townsteadContextCacheTicks`,
with immediate invalidation when `reactionLocked` or `collapsed` flips, which the existing snapshot
already exposes).

Behaviour: a held villager costs one Townstead read per `townsteadContextCacheTicks` instead of one per
tick; danger (`hurt`, `PANIC`) is still read live every tick because it comes from vanilla state.

Depends on: WP-D1 (counter proves the saving). Risks: a Townstead reaction lock that starts between
reads could be fought for up to 20 ticks; keep the lock flags live if the counter shows the read is
cheap. Acceptance: `ConversationMovementControllerTest` additions; counter delta on a server with
Townstead and 5 held villagers.

### WP-D4 — Measurement campaign (before WP-D2 lands, and after)

No source files. Procedure in §5 part D. Deliverable: a `docs/PERF-BASELINE-<date>.md` with the
environment manifest, the matrix, spark viewer links or exported `.sparkprofile` files, Observable
screenshots, TPS/MSPT distributions, and the budget decisions. The WP is complete when the identified
regression (if the baseline shows one) is addressed by WP-D2/D3 and the after-profile shows no moved
spike; or when the baseline shows Conversations is not the cost, in which case the deliverable is the
scoped upstream report in §7.

---

## 4. Content inventory

Ownership: all files below are hand-authored (`MODMAP.md`, “Content pipeline”). The only generated
material touched is the personality overlays, regenerated from `src/content/voices/*.json` by
`generateVoiceOverlays`.

### 4.1 Routes and keys to move or rewrite

| Today | Speaker as written | Should belong to | Action |
|---|---|---|---|
| `checkin_child` starters: `family.child.doing/1-3`, `.little/1-3`, `.teen/1-2`, `.again/1-3` | player’s child | `checkin_child` | keep |
| `topic.checkin_child.respond` answers `listen`, `praise`, `dismiss` + NPC `family.child.listen/1-3`, `.praise/1-3`, `.dismiss/1-3` | a parent about their own kids | new `their_children` | move keys and answers; rename page to `topic.their_children.respond` |
| `topic.checkin_child.followup` answers `guide`, `play_along`, `scold`, `leave` + `family.child.guide/1-3`, `.play_along/1-3`, `.scold/1-3`, `.leave/1-3` | parent | `their_children` | move |
| `topic.family.scolded.close` + `family.scolded.apologize/leave/soften/1-3`, beats `family.child.scolded`, `family.scolded.*` (`checkin_child_close.json`) | parent | `their_children` | move; keep subject `family.child` renamed to `their_children` |
| `checkin_child.rebuffed.*` (`repair_checkin_child_rebuffed_followup.json`, `topic.checkin_child.rebuffed.followup`) | parent (“other people’s children”) | `their_children` | move; `dismiss` is the entry |
| `topic.checkin_child.promise` + `checkin_child.promise.*` | child | `checkin_child` | keep |
| `topic.ask_parent.pride` + `ask_parent.pride/1-3`, `.said/1-3`, `.show/1-3`, `.leave/1-3`, beats `ask_parent.pride*` | child | `checkin_child` | move to `topic.checkin_child.pride`; answer label “Is there something you’re proud of?” fits a parent asking a child |
| `topic.ask_parent.event` + `ask_parent.event/1-3`, `.plain/1-3`, `.together/1-3`, `.leave/1-3`, beats `ask_parent.event*`, answer `followup.ask_event` (“Is this about what happened at home?”) | child | `checkin_child` | move to `topic.checkin_child.event`; offer it from the new `checkin_child.respond` |
| `ask_parent` starters `family.parent.proud/worried/again`, respond `answer_honestly`/`reassure`/`snap`/`leave`, followup `ask_worries`/`thank_them`/`deflect`/`leave`, `ask_parent.rebuffed.*` | parent | `ask_parent` | keep |
| `interior_family.json` beats `family.child.guide.to.family`, `family.child.play_along.to.family` | parent | `their_children` | retarget beat ids with the moved pages |
| `terminal_family.json` `family.child.doing.terminal`, `family.child.leave.terminal` | child / parent | `checkin_child` / `their_children` | split |

### 4.2 New text required (estimate)

| Topic | Need | Count (en + pt) |
|---|---|---|
| `checkin_child.respond` | three child-voice NPC replies to the player checking in (“Tell me how you really are.” / “You’re doing well.” / a dismissive option) × 3 variants, plus answer labels | 9 lines + 3 labels, ×2 locales |
| `checkin_child` age branches | toddler and teen variants for `pride`/`event` entry points or age-conditioned exclusions | 6–12 lines, ×2 |
| `their_children` | starter answer label, opener(s) for a villager with children (“How are the kids?”), gate text when unavailable | 4–6 lines, ×2 |
| Overlays | the six voice families for every new or moved key, via `src/content/voices` | generated |

### 4.3 Locale and overlay coverage

- `en_us` and `pt_br` must stay key-identical (`LocaleParityTest`).
- 21 overlay variants for `family.child.*`/`family.parent.*` exist per personality namespace (e.g.
  `assets/mca_dialogue_friendly/lang/en_us.json`); they move with their base keys through
  `src/content/voices/*.json` and `specials/*.json`, regenerated by `generateVoiceOverlays`.
- `SignatureOverlayLintTest.WEIGHTED_COVERAGE_FLOOR` (18 %) must still pass after the move; budget
  overlay variants for the new lines.
- Third-party datapacks that override `conversations.topic.checkin_child.respond` keep working for
  the keys that remain; document the moved page ids in `DATAPACK.md` and `CHANGELOG.md`.

### 4.4 Chat intents and labels

`family.checkin_child` and `family.ask_parent` in `chat_intents/relationships.json`; new
`family.their_children`; labels `dialogue.chatmode.topic.*`; `checkin_child.hurt.*` intents move
with the scolded page.

---

## 5. Validation instructions

Record every run with the environment manifest from the handoff §3 (loader, client/server jars and
sha256, MCA version and root, add-ons, language, packs and order, style, entry mode, chat mode, voice
backend). Classify each symptom as reproduced now / reproduced only in an older artifact / suspected /
not reproduced under stated conditions.

### Part A — build gates (run on every slice)

```
/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh /home/otectus/Projects/MCAConversations compileJava
/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh /home/otectus/Projects/MCAConversations check
/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh /home/otectus/Projects/MCAConversations build verifyGeneratedConversationContent verifyVoiceOverlays
python3 /home/otectus/Projects/.mcmod-tools/check_mod.py MCAConversations
python3 tools/verify_release_parity.py --forge . --neoforge "/home/otectus/Projects/1.21.1 Ports/MCAConversations_1.21.1"
```

`check` already replays `McaBindingProbeTest`, `MixinTargetProbeTest` and
`ConstraintVocabularyProbeTest` against the six fleet jars; the new binding members (WP-B1) and
shadowed fields (WP-A4) are therefore verified against every supported MCA by the build. Townstead
probes need `-PtownsteadModernJar=… -PtownsteadLegacyJar=…`; only 0.7.6 is on this machine.

### Part B — client reproduction and acceptance (real production client; not a unit test)

Use **MCA Addons** (MCA 7.6.26, Townstead 0.7.6, `MINIMAL`). Replace `mods/mcaconversations-1.8.0.jar`
with the build under test. Create a disposable superflat world, `/gamemode creative`, summon an MCA
villager, make them the player’s child or parent with MCA’s villager editor (family tree), and a
second unrelated villager.

B1. Text agreement (Report 1). With `/conversations client speech-trace on`, open the villager →
Conversations → a topic; answer three times; for each turn record: card text, chat log line, history
drawer entry, `diagnose` ledger row. Repeat in `RESPONSIVE`, `MCA_ORIGINAL` and with
`numberedResponses=false`, then with Townstead’s *Talk* button (RPG screen). Expected: one sentence
on every surface that shows it; the card is never actionable while its line has not been shown;
the H drawer has one entry per utterance. Run once more after `/reload` and after switching the
language to `pt_br` mid-conversation.

B2. Speech (Report 2). Needs one of: the MCA Voices resource pack installed and enabled, or
`enableOnlineTTS=true` with a working backend (the owner decides which; neither is set up locally).
Sequence per the handoff: pooled greeting A (open the screen, let `dialogue.main/N` resolve) →
explicit Conversations line B → another explicit line C, with the hub prompt and answer labels
resolving in between. Capture the `speech-trace` lines. Expected before WP-B1: `mapped-before` is
the greeting or hub key for B and C; after WP-B1: `mapped-after` is B’s and C’s own key, the voice
pack plays nothing for them, online TTS speaks B then C, and a repeated legitimate line later speaks
again. Rapidly switch villagers and confirm no stale opener plays. Accessibility: toggle the Minecraft
narrator on a machine where `flite` loads and confirm `DialogueChoiceNarrator` still announces focus.

B3. Family (Report 3). Four villagers: the player’s toddler, the player’s teen, the player’s adult
child, the player’s parent; plus an unrelated child and an adult who has children. For each, open
`Family` and read every reachable page to its end, in GUI and typed chat, in both locales. Expected
per the §2.3 matrix; `their_children` appears only for the parent villager; no child line reaches a
parent and no parent line reaches a child; age-up the teen with the editor and re-run.

B4. Lifecycle (unchanged behaviour, must not regress): walk 10 then 25 blocks mid-topic; `/tp` to the
Nether; attack the villager; `/kill` it; log out; die; let it go to bed; `/reload` with the card open;
two real clients taking the villager over. Expected as `AUDIT.md` §10 lists.

### Part C — dedicated server, two clients

Boot with `tools/test/conversations_server.py --mod <mca> --mod <conversations> [--mod townstead …]`.
Repeat B1–B3 with two clients where relevant (takeover, bystander chat), and confirm a protocol-4
client is refused by the channel after WP-A2.

### Part D — performance campaign (spark + Observable, identical disposable worlds)

Prepare once: a flat world seeded with villages of 20, 60 and 150 MCA villagers (use
`/mca` spawn commands or a prebuilt save copied per run), simulation distance 8, 2 players (one
idle near the village, one 400 blocks away), warm-up 5 minutes, run 10 minutes, repeat twice.

Matrix (separate copies of the same save, never the original):

1. MCA + Architectury only.
2. MCA + Conversations.
3. MCA + Townstead; MCA + Quests; MCA + Reputation; MCA + Crime; MCA + Capitals; MCA + Kingdoms.
4. MCA + Conversations + Townstead; MCA + Conversations + Quests + Capitals + the reporter’s
   “Social Expansion” once identified.
5. The full **Ultima** mod list with and without Conversations.

Per run: `spark profiler start --timeout 600 --thread "Server thread"` (then `spark profiler stop`,
keep the viewer link or `--save-to-file`), `spark tps` and `spark tickmonitor --threshold 50` logs,
Observable `/observable run 60` at minute 6 and again at a 600-tick boundary, `/conversations perf
status` at the end, `/forge tps`, loaded chunks and tickets (`/forge chunkload` or spark’s world
view), and the counters from WP-D1. Actions during the run: no conversation; one GUI conversation
held open 3 minutes; ten open/close cycles; typing in chat near the village; a chat exchange then
walking 50 blocks; teleport away and back; logout and return; `/time set` across a day boundary.

Report: TPS and MSPT p50/p95/p99 and the max tick, with spark’s call-tree attribution of anything
under `dev.otectus.mcaconversations` versus `net.mca`/`forge.net.mca`, Townstead and others; the
600-tick spike before and after WP-D2; counts of sessions/holds/queue after each departure action
(must return to zero); ticket owners (must never include Conversations). Set the numeric budgets
after the first baseline on the named hardware (this machine: CachyOS, Linux 7.2.9, JDK 17.0.20)
and state them in `docs/PERF-BASELINE-<date>.md` before final validation. Do not claim a universal
improvement.

### Part E — what only real clients, audio or profilers can establish

B1, B2, B3 (in-game), B4, C and D above. Unit tests, binding probes and a dedicated-server boot are
evidence of wiring, not of what a player sees or hears; mark them so in the roadmap.

---

## 6. Release plan

- **Version and protocol.** Ship as 1.8.0 (unreleased) if the owner wants one release, or 1.8.1 if
  1.8.0 goes out first; either way `network_protocol=5`, so client and server update together (the
  existing posture since 1.7.1). State it in the changelog header.
- **Parity.** Mirror every change to `1.21.1 Ports/MCAConversations_1.21.1` (branch
  `neoforge/1.21.1`): WP-A1..A3, WP-B1 (adapted to the single `net.conczin.mca` root; `VillagerMessage`
  there holds `Component` fields, so the association repair hooks the same getters without the parse
  unification), WP-C and WP-D verbatim. Re-pin `tools/parity/parity-1.8.0-adaptations.json` (or a
  new release manifest) and run `verify_release_parity.py` from both copies.
- **Config.** New keys: client `chatModeSpeech` (default off, WP-B3 only), optional
  `maintenanceBatchSize` (WP-D2 only if needed). No key is renamed or removed; `CONFIG.md` and
  `MODMAP.md` (hand section) updated with the work, per the project’s changelog-with-the-work habit.
- **Upgrade compatibility.** No save schema change; `PairHistory`, progress, disposition and gossip
  formats are untouched. Moved dialogue page ids are listed in `CHANGELOG.md` and `DATAPACK.md` for
  pack authors; cooldown memory ids are kept so a world mid-cooldown behaves the same.
- **Changelog.** Truthful: “the dialogue card waits for the line it answers”, “villager speech no
  longer replays the previous pooled line for Conversations’ sentences; lines without a recording in a
  voice pack are silent as before”, “the two family topics were partly written from the wrong side;
  they are now three topics”, “maintenance is spread across the gossip interval and bounded per tick”,
  and, separately, what was *not* verified if a client gate is skipped.
- **Rollback.** Nothing persistent changes, so rolling back is reinstalling the previous jar on both
  sides; a world that saw the new family topics keeps harmless cooldown memories.
- **Documentation.** `docs/ROADMAP.md` §2 gains the rows for B1–B4, C and D with their run status;
  `AUDIT.md` is left as the September record.

---

## 7. Remaining uncertainties and useful reporter evidence

Prioritised; planning continued without them.

1. **The reporters’ exact jars** (client and server `mcaconversations-*.jar` sha256, MCA version
   and root, Townstead version). Decides whether Report 1 is the pre-1.2.0 artifact or a path this
   plan has not seen.
2. **Voice backend** for Report 2: MCA Voices pack (which version) or `enableOnlineTTS` and which
   `onlineTTSModel`; a 30-second recording with `speech-trace on` after WP0 ships.
3. **Which interface** for Report 1: a screenshot or video showing the card style (badge numbers and
   the `H`/settings corner buttons identify Conversations’ card; Townstead’s box has the typewriter
   frame and name tab).
4. **The “Social Expansion” mod**: project page, mod id, version. Nothing by that name exists in any
   local instance; do not substitute Townstead for it.
5. **A spark profile** (`spark profiler --timeout 300`) and the Observable export from the affected
   server, plus simulation distance, player count and the mod list. Until then Part D runs on the
   local analogue.
6. **Upstream report** to prepare (not blocking): MCA’s `MixinTranslatableText` associates any
   translatable with the last pooled key; explicit `/N` keys and single keys inherit stale
   associations; `speak()` speaks raw `%1$s` placeholders from `Language.getOrDefault` under online
   TTS. Attach the bytecode facts in Appendix A and the trace lines from B2.
7. **Product decisions** the owner must take: ship typed-chat speech (WP-B3) or defer; new family
   text volume (§4.2) and a Portuguese reader; whether `their_children` is a 1.8.0 feature or a
   separate release.

---

## Appendix A — Evidence log

Bytecode facts come from `javap -c -p` on the extracted classes; the line references into the
disassembly are not reproduced, the method is named instead. “7.6.26” means
`minecraft-comes-alive-reborn-7.6.26+1.20.1.jar`, root `forge.net.mca`; “7.7.1-beta.2” means
`minecraft-comes-alive-reborn-7.7.1-beta.2+1.20.1.jar`, root `forge.net.conczin.mca`. Where only one
is named, the other was checked and is equivalent.

- **A-1** `mixin.client.MixinTranslationStorage.mca$onGet(String key, String fallback, cir)`
  (7.6.26, 7.7.1-beta.2): `key' = DialogueType.applyFallback(key)`; `tuple = pool.get(key')`;
  if `tuple != null` → `CommonSpeechManager.INSTANCE.lastResolvedKey = tuple.getA()` and the return
  value is the pooled text; else if `key' != key` → return `super.getOrDefault(key')`; else fall
  through. Injected at HEAD of `ClientLanguage.m_118919_` (`getOrDefault`). `lastResolvedKey` is
  **not** touched on the null path.
- **A-2** `mixin.MixinTranslatableText.mca$updateTranslations(CallbackInfo)` (both):
  `if (lastResolvedKey != null) translations.put((ComponentContents) this, lastResolvedKey)`;
  injected at TAIL of `TranslatableContents.m_237524_` (`decompose`). `entity.CommonSpeechManager`
  has `public String lastResolvedKey` and `public final LimitedLinkedHashMap<ComponentContents,
  String> translations` of capacity 100. `DialogueType.applyFallback(String)` is `public static`.
- **A-3** `util.localization.PooledTranslationStorage` (7.6.26): bases are keys matching
  `/[0-9]+$` with the suffix stripped; `get(key)` returns a `Tuple(chosenKey, text)` only when the
  key is a base with options, choosing with `PoolUtil.pickOne(..., rand)`; `contains` likewise.
- **A-4** `client.tts.SpeechManager.onChatMessage(Component, UUID)` (both): `contents =
  component.getContents()`; if `translations.containsKey(contents)` → `speak(translations.get(contents),
  uuid, true)`; else if `contents instanceof LiteralContents` → `speak(text, uuid, false)`.
  `speak`: returns if the speaker’s previous sound is still playing; finds the speaker by scanning
  client entities for the UUID; returns if `isSpeechImpaired()` or `isToYoungToSpeak()`; with
  `Config.enableOnlineTTS`: translatable → `Language.has(key) ? Language.getOrDefault(key) : warn and
  return`, then realtime / player2 / elevenlabs / online by `onlineTTSModel`; else translatable →
  `mca_voices:<key lowercased>.<gender>.<min(9, tone*10)>` played if the sound manager knows it
  (7.7.1-beta.2 factors this into `tryPlayVoicePackSound`).
- **A-5** `entity.interaction.Constraint` static initialiser (both): `FAMILY("family",
  Relationship.IS_FAMILY.asConstraint())`, `KIDS("kids", Relationship.IS_PARENT.asConstraint())`,
  `PARENT("parent", Relationship.IS_KID.asConstraint())`, `ORPHAN("orphan", IS_ORPHAN…)`;
  age constants compare `getAgeState()` with `BABY/TODDLER/TEEN/ADULT`.
  `entity.ai.Relationship`: `IS_PARENT = (villager, uuid) -> villager.getRelationships()
  .getFamilyEntry().isParent(uuid)`; `IS_KID = (villager, uuid) -> FamilyTree.get(world)
  .getOrEmpty(uuid).filter(node -> node.isParent(villager.getUUID())).isPresent()`;
  `IS_RELATIVE` → `getFamilyEntry().isRelative(uuid)` (`getAllRelatives(9)`); `IS_ORPHAN` →
  `getParents().allMatch(deceased)`. `server.world.data.FamilyTreeNode.isParent(UUID)` =
  `streamParents().anyMatch(id::equals)`.
- **A-6** `client.gui.InteractScreen` (7.6.26): `setDialogue(String, List)` only stores
  `dialogQuestionId`/`dialogAnswers`; `setLastPhrase(MutableComponent, boolean silent)`: `silent ?
  villager.transformMessage(text) : villager.sendChatMessage(text, player)`, then
  `dialogQuestionText = font.split(result, 160)`. `network.ClientInteractionManagerImpl`:
  `handleDialogueResponse` → `screen.setDialogue(question, answers)`; `handleDialogueQuestionResponse`
  → `screen.setLastPhrase(getQuestionText(), silent)`; `handleVillagerMessage` →
  `ChatListener.handleSystemMessage(msg.getMessage(), false)` then
  `SpeechManager.INSTANCE.onChatMessage(msg.getContent(), msg.getUuid())`. `entity.ai.Messenger
  .sendChatMessage(MutableComponent, Entity)`: builds `new VillagerMessage(name, message, uuid)`;
  server receiver → `NetworkHandler.sendToPlayer`; otherwise → `handleVillagerMessage` directly.
- **A-7** `network.s2c.VillagerMessage` (Forge, 7.6.20…7.7.1-beta.2): fields `private final String
  prefix`, `private final String message`, `private final UUID uuid`; constructor
  `(MutableComponent, MutableComponent, UUID)`; `getMessage()`/`getContent()` parse JSON.
  `InteractionDialogueQuestionResponse(boolean, Component)` stores `Component.Serializer.toJson`;
  `getQuestionText()` parses it. `InteractionDialogueResponse` has `question` and `answers`.
  **A-7b** NeoForge `mca-neoforge-7.7.33+1.21.1`: `VillagerMessage` holds `Component prefix/message`.
- **A-8** `server.world.data.Village.getResidents(ServerLevel)` (7.6.26):
  `getResidentsUUIDs().map(level::getEntity).filter(VillagerEntityMCA.class::isInstance)…`;
  `VillageManager.findNearestVillage(BlockPos, int)` streams all villages through a radius predicate
  and takes the minimum. `resources.Dialogues.selectAnswer` scores results with `Result.getChances`,
  picks one, sends `AnalysisResults`, then `Actions.trigger`.
- **A-9** Townstead 0.7.6: `mixin.InteractScreenMixin.townstead$interceptTalkButton` opens
  `RpgDialogueScreen` on `gui.button.talk`; `mixin.ClientHandlerImplMixin` redirects
  `handleDialogueResponse` → `setDialogue`, `handleDialogueQuestionResponse` → `setLastPhrase(text,
  silent)`, and `handleVillagerMessage` → `setFinalPhrase(getContent())` for the screen’s villager,
  cancelling MCA’s handlers; `RpgDialogueScreen.setLastPhrase` keeps the previous text for a silent
  main-menu prompt, otherwise `DialogueBox.setText` and, when not silent, `speakDisplayedText` →
  `TownsteadLiteralTts.speak(component.getString(), villager)`, which speaks only when
  `enableOnlineTTS` and model ∈ {realtime, player2, elevenlabs}; `mixin.ChatListenerMixin` →
  `tryExtractExternalVillagerLine` (a `§`-prefixed `"<name>: "` system line) → `setIncomingChatLine`
  and cancel; `mixin.MessengerVoiceMixin` overwrites `getTranslatable` → `SpeciesVoice.line`, which
  picks a species variant server-side and returns a **literal** component built with `String.format`
  (server locale; `LineVoice.pin` leaves it untouched; MCA speaks it as literal text) or `null`, in
  which case `McaVoiceFallback.build` returns MCA’s marker-prefixed translatable;
  `mixin.VillagerServerTickMixin` runs `FatigueVillagerTicker.preAiStep` and
  `VillagerServerTickDispatcher.tick` per villager tick; `TownsteadLiteralTts` is present in 0.7.6.
- **A-10** Source facts relied on above: `mixin/client/VillagerMessageMixin.java:70-105`;
  `mixin/client/InteractScreenChoiceMixin.java:75-98, 277-333`; `mixin/NetworkHandlerMixin.java:61-127`;
  `locale/LineVoice.java:106-125`; `locale/VariantPools.java:84-91`; `compat/McaCompat.java:193-228`;
  `compat/mca/McaHandles.java:196-210, 400-446, 673-685, 741-748`; `compat/mca/McaBinding.java:208-211,
  348-364`; `conversation/ConversationSession.java:48-54, 193-248`; `conversation/ConversationSessions.java:70-77,
  254-266`; `conversation/ChoiceSelectionService.java:97-220`; `network/ChoiceOfferS2C.java:21-67`;
  `network/ConversationsNetwork.java:57-88`; `client/dialogue/ClientChoiceMessages.java:151-159, 345-358`;
  `client/dialogue/DialogueChoiceRenderer.java:91-215`; `client/dialogue/DialoguePresentationBuilder.java:81-124`;
  `client/dialogue/SpeakerTextStyler.java:18-60`; `client/dialogue/ClientDialogueHistory.java:39-58`;
  `chat/ChatDelivery.java:72-157, 211-214`; `chat/ChatModeSession.java:228-239`;
  `event/ConversationsEvents.java:360-605`; `chat/VillagerAttention.java:145-154, 236-287`;
  `chat/ConversationMovementController.java:153-172, 248-271`; `chat/GreetOnApproach.java:68-155`;
  `chat/VillagerFinder.java:42-68`; `chat/ChatModeScheduler.java:92-166`; `gossip/GossipDetectors.java:79-161`;
  `gossip/TownsteadGossip.java:26-63`; `history/RumourPropagation.java:206-256`;
  `history/ConversationHistoryStore.java:235-247`; `history/VillagerHistory.java:409-434`;
  `disposition/DispositionStore.java:115-132`; `progress/ProgressStore.java:128-145`;
  `court/CourtNewsPoller.java:230-276`; `compat/Townstead.java:29, 145-170`;
  `chat/TownsteadChatPolicy.java:63-65`; `conversation/RelationshipRoles.java:12-17`;
  `conversation/Relationships.java:53-72`; `context/McaContextSource.java:196-204`;
  `chat/GreetingPolicy.java:121-130`; `chat/AgeVoice.java:48-51`; `conversation/TopicGate.java`;
  `conversation/TopicEntry.java:92, 196`; `conversation/BeatContract.java:113-137`;
  `src/test/java/.../MixinTargetProbeTest.java:109-111`; `src/test/java/.../content/AgeSeparationTest.java`;
  `src/test/java/.../compat/ConstraintVocabularyProbeTest.java`.

## Appendix B — Commands used to establish the evidence (reproducible)

```
# fleet jars
ls ~/.gradle/caches/modules-2/files-2.1/maven.modrinth/minecraft-comes-alive-reborn/
# disassemble a class from a jar
unzip -o -q <jar> 'forge/net/mca/client/tts/SpeechManager.class' -d /tmp/mca && javap -c -p /tmp/mca/forge/net/mca/client/tts/SpeechManager.class
# which predicate backs a constraint
javap -c -p .../Constraint.class | grep -n -B3 -A6 'String kids$'
# injection target of MCA's translatable mixin
javap -v .../MixinTranslatableText.class | grep -E 'Utf8 +(m_[0-9]+_|TAIL)'
# page prompt classification used in §1.1
python3 -I - <<'EOF'
import json,glob,os
lang=json.load(open('src/main/resources/assets/mca_dialogue/lang/en_us.json'))
for f in glob.glob('src/main/resources/data/mcaconversations/dialogues/*.json'):
    p=os.path.basename(f)[:-5]; k='dialogue.'+p
    print(p, 'single' if k in lang else 'pooled' if k+'/1' in lang else 'none')
EOF
```
