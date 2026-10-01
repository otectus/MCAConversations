# MCA: Conversations — audit, 2026-09-30

Branch `feature/social-1.8.0` at `ebef667` plus the working-tree changes listed in §7. Forge 1.20.1,
Forge 47.4.10 (dev) / 47.4.23 (runtime checks), Java 17, MCA Reborn `[7.6,8)`.

## 1. Summary

No Critical findings. **Four Major and six Minor findings**; seven are fixed, each with a regression test
where it could be expressed without a running game. The largest issue is not fixed because it is a
policy decision:

- **Fixed:** chat-side attention holds pinned a villager to a player who had walked off, teleported or
  changed dimension (F2). The social audit misclassified all 404 of this mod's own scenes on real Forge
  servers (F1). MCA: Quests `talk_about` counted repeat clicks as conversations (F4). Plus four Minor
  items: mixin warning noise, an unthrottled packet, untranslated player text, and a stale client
  handle across server switches (F5–F8).
- **Not fixed, needs a decision:** at server start, one malformed entry in *any* datapack refuses the
  whole content load. Nothing earlier exists to retain, so the server runs with none of this mod's
  ~1,057 dialogue questions (F3). The log now says so plainly, and names the pack.

This was also the first time the 1.8.0 branch ran on real dedicated servers. It starts, reloads,
survives a villager spawn and death, and stops cleanly on MCA 7.6.20, 7.6.26 and 7.7.1-beta.2, both
alone and beside Townstead, Quests, Reputation, Crime and Serene Seasons. No conversation has been
played in a client.

## 2. What the mod does (my understanding, from code, data, lang and config)

The brief left the description blank, so this is inferred. Every fix below is consistent with it.

- **Starting a conversation.** Right-clicking an MCA villager opens MCA's own `InteractScreen`, and
  MCA's `main` question carries a *Conversations* answer (`hubEntryMode`: `ADDITIVE` adds its own button,
  `REPLACE` reroutes MCA's *Chat* answer to it, `HIDDEN` removes the entry). That answer leads into this mod's category and topic questions. Alternatively, **chat mode**:
  typing in the vanilla chat box near villagers, matched against intents (`chat_intents`), with the
  reply delivered in chat (`ChatModeDispatcher`, `NetworkHandlerMixin`'s delivery redirect).
  Greet-on-approach and villager initiatives can also open an exchange.
- **How dialogue is defined.** Data-driven. MCA dialogue JSON under `data/mcaconversations/dialogues/`
  is merged into MCA's own dialogue tree by MCA's `Dialogues` reload listener. This mod's own catalogs
  (topics, scenes, beats, intents, templates, identity tokens, culture, professions, Townstead
  holidays) load through a single transactional listener, `ContentReloadCoordinator` (HIGH), with a
  completion listener (LOWEST). `DialoguesMixin` keeps or strips this mod's owned questions according
  to the verdict. Most of the corpus is generated at build time from `src/content/` and committed. It
  is not produced at runtime, and **nothing is AI-generated and there is no network I/O**.
- **What gates lines and options.** MCA's own conditions, plus this mod's `conversations_*` vocabulary
  registered into MCA's registries (47 names across conditions and actions in the two registrars). The
  conditions cover: hearts bands, personality, mood, memory and cooldowns, gossip,
  weather, season, holiday, disposition, dice checks, progress, kingdom and civic gates, Townstead,
  and Quests/Reputation/Crime state. On top of those, `TopicGate` (age allow-list, kingdom gates,
  Townstead) filters the answer list in `QuestionMixin`. Every gate is evaluated on the server.
- **What choices can do.** MCA actions (say, next, remember, hearts) and this mod's own: capped
  affection, arc/milestone progress, topic session begin and end, dispositions, cooldown records
  (which drive Quests' `talk_about`), quest accept and topic unlock, reputation signals and Townstead
  reactions. There are no item or command effects of this mod's own.
- **Sessions.** Server-side and keyed by player UUID (`ConversationSessions`). A graphical discussion
  gets a `ConversationHandle` minted at the first dialogue packet MCA sends (`InteractionBoundary`),
  indexed by player and by villager (`ConversationPresence`). It is leased by client heartbeats, judged
  every tick for distance, dimension, death and sleep, and ended only through
  `ConversationLifecycle.terminate`. Handoff moves a villager to a second player as `TAKEN_OVER`.
- **Reaching the client.** Offers go out as `ChoiceOfferS2C` (handle, revision, answer ids). Replies
  come back as `ChoiceSelectC2S` (handle, revision, index) and are re-validated by
  `ChoiceSelectionService`: handle, revision, index, frontend, distance, MCA's interacting player,
  constraints and age gate, one-shot claim. The card itself is MCA's screen, restyled by
  `InteractScreenChoiceMixin` (and the Townstead screen mixins).
- **Persistence.** Overworld `SavedData` (history, progress, dispositions, gossip, identity, culture,
  court) and two player capabilities (gift memory, chat-mode opt-in), copied on `PlayerEvent.Clone`.
- **Integrations.** MCA is resolved reflectively (`McaBinding`, four package roots). Quests,
  Reputation and Crime go through vendored compile-only API jars behind name-loaded adapters. Townstead,
  Capitals, Ultima Kingdoms and Serene Seasons are reflective. MCA is the only mandatory dependency.

## 3. Method

1. Oriented from `MODMAP.md`, `CLAUDE.md`, `docs/ROADMAP.md` and `docs/RELOAD-TRANSACTION-BOUNDARY.md`.
   Settled decisions and known issues were not re-litigated. `check_mod.py` found 0/0/0. The baseline
   `build` passed with 1,729 tests.
2. Checked the pinned warning that the source can lag the released jar. `build/libs/mcaconversations-1.8.0.jar`
   (Sep 28 13:27) predates the last commit (Sep 28 18:52), and Gradle reported it up to date against the
   source, so there was no hidden release to recover.
3. Read every hop of the pipeline (§4) and the mixins, network, lifecycle, attention, chat, commands,
   persistence and integration code. Ran mechanical scans across all 451 main classes: client classes
   reachable from common code, event buses, empty catches, identity comparisons, config reads, and lang
   keys against all 23 lang namespaces.
4. **Booted real dedicated servers.** ForgeGradle's dev runtime cannot load MCA (`docs/ROADMAP.md` §2),
   so I booted the packaged Forge 47.4.23 server Ultima Kingdoms keeps, driven by `/tmp/mcac-boot/boot.py`
   (modelled on Ultima's `integration_runtime.py --startup-only`). Each boot starts, runs `/reload` and
   console commands, optionally summons and kills MCA villagers, and stops. These boots found F1 and F3,
   and they verify every fix that has a runtime face.

## 4. Pipeline trace

| Hop | Where | Verdict |
|---|---|---|
| Interaction → session open | MCA `interactAt` → `NetworkHandlerMixin` (first `InteractionDialogueResponse`) → `InteractionBoundary.beginGui` → `ConversationLifecycle.begin` (danger lockout, rate limit, handoff) | Sound |
| Condition check | MCA `Question.getValidAnswers` + `QuestionMixin` (`TopicGate`, hub button) on the server; MCA returns a mutable `LinkedList` on all six fleet builds (bytecode checked) | Sound |
| Line / option selection | MCA dialogue engine; the owned lookup boundary (`ContentReloadCoordinator.lookup`) | Sound once content is committed. **Broken for the whole server session when the first load is refused (F3)** |
| Client display | `ChoiceOfferS2C` → `ClientChoiceMessages` → `InteractScreenChoiceMixin`; refmap maps `Font.split`, `ChatScreen.keyPressed`, `ItemStack.split` to SRG | Sound. Visual layout not observed (no client run) |
| Player choice | Numbered card → `ChoiceSelectC2S`; MCA click → `InteractionDialogueMessageMixin` → the same service | Sound |
| Server validation | `ChoiceSelectionService.submitPinned`: handle authority, revision, index, generation, frontend, distance policy, MCA interacting player, constraints, age gate, one-shot claim | Sound, and rejects stale, spoofed and out-of-range submissions |
| Effect | `McaCompat.selectAnswer` inside `ConversationOutcomes`; the registrar's actions | Sound, except **Quests `talk_about` double-counted (F4)** |
| Persistence | `SavedData` mutators all `setDirty`; capabilities serialised and cloned | Sound |
| Sync | S2C offer, clear, opened, closed; C2S presence heartbeat | Sound; stale handle quoted across a server switch (F8, cosmetic) |
| Close | Lifecycle tick (disconnect, death, spectator, other dimension, unload, sleep, lease, distance); logout, death, villager death; client close; native close guarded against stale senders; handoff | Sound for GUI. **Chat-side holds outlived their player's departure (F2)** |

Categories from the brief with no finding:
- 1 (hard errors): no exception from this mod in any boot.
- 2 (sides): no client class reachable from common code; S2C handlers go through an installed sink.
- 3 (silent failures): buses correct; all 28 empty catches are commented fail-soft points on corrupt
  NBT, optional bindings or cosmetic UI; every `SavedData` mutator marks dirty; `Clone` copies both
  capabilities.
- 5 (server authority).
- 6 (identity): UUIDs throughout; the three object-reference comparisons are same-call scopes and cache
  keys.
- 9 (AI and network): none present.
- 10 (version traps): compiles against 1.20.1; name-based reflection only targets other mods' own
  classes.
- 12 (config): all 145 config values are read.

## 5. Findings

| # | Severity | Location | Issue | Fixed |
|---|---|---|---|---|
| F1 | Major | `conversation/ContentStaging.java:323` (`bundledPack`) | This mod's own pack not recognised on real Forge servers: 404 bundled scenes listed by `/conversations social audit`, plus a `social_contract_absent` note every reload | Y |
| F2 | Major | `chat/ConversationMovementController.java:153` (`judge`) | Chat, greeting, initiative and typing holds ignored reach and dimension: the villager stayed pinned for up to `chatModeAttentionTicks` (≤ 72,000) after the player left | Y |
| F3 | Major | `conversation/ContentReloadCoordinator.java` (refusal policy) | One refused entry in any pack at server start leaves the mod with no dialogue for the whole session | N (clear ERROR added; policy open, Q1) |
| F4 | Major | `compat/mca/ConversationsMcaRegistrar.java:156` (`recordOne`) | Quests `talk_about` advanced on every cooldown write, including "again" re-entries: one topic clicked N times finishes N heart-to-hearts | Y |
| F5 | Minor | `mcaconversations.mixins.json`; `mixin/NetworkHandlerMixin.java:35` | 27 `WARN [mixin/] Error loading class` per server start for MCA's absent package root (by the same mechanism, Townstead targets on a client without Townstead — not observed); the Javadoc claimed DEBUG | Y |
| F6 | Minor | `chat/VillagerAttention.java:145` (`playerTyping`) | Typing pings not rate-limited on the server; each is an entity query on the server thread | Y |
| F7 | Minor | `command/ConversationsCommand.java:95,107` | Player-level `/conversations chat on\|off\|status` replies hardcoded in English | Y |
| F8 | Minor | `client/dialogue/ClientChoiceMessages.java:99,110` | `refFor`/`currentRef` skip connection resync; the heartbeat quotes the previous server's session after a server switch (the server ignores it) | Y |
| F9 | Minor | `compat/quests/TalkAboutObjective.java:45`, `UnlockTopicReward.java:37` | `mcaconversations.topic.<id>` keys do not exist, so quest cards show raw ids (`ask_parent`) | N (content, Q3) |
| F10 | Minor | `META-INF/mods.toml`; `CHANGELOG.md:11`; `compat/CrimeBridge.java:65` | The changelog advertises "MCA: Crime `[0,)`", but `mods.toml` declares no `mcacrime` entry and `CrimeBridge` says it deliberately declares nothing | N (Q2) |

### F1 — the bundled pack is not recognised on Forge (Major, fixed)

- **Root cause:** `bundledPack` accepted only `mod:mcaconversations` / `mod/mcaconversations`.
  `Resource.sourcePackId()` returns the `PackResources` id, and Forge 47.4.23's
  `ResourcePackLoader.createPackForMod` builds `new PathPackResources(mf.getFile().getFileName(), …)`
  (read from the sources jar). The id is therefore the jar's file name; `mod:<id>` is only the
  repository entry. The unit test encoded the wrong assumption for Forge. NeoForge 21.1 does use
  `mod/<id>`, so the port is unaffected.
- **Evidence:** the server log printed `mcaconversations-1.8.0.jar (404): topic.age_and_life.the_later_years, …`
  on MCA 7.7.1-beta.2, 7.6.20 and 7.6.26.
- **Fix:** `bundledPack(pack, ownFileName)` also accepts this mod's own file name, resolved in
  `ContentSources.ownPackFileName` through `ModList.getModFileById(...).getFile().getFileName()`,
  guarded so unit tests see `null`. The two loader-named forms are kept.
- **Test:** `SceneSocialAuditTest.bundledScenesUnderTheJarFileNameAreNotReported`.
- **Runtime:** after the fix the audit prints "Every loaded scene declares … or is this mod's own" on
  all three boots.

### F2 — chat-side holds outlive their player (Major, fixed)

- **Root cause:** `ConversationMovementController.judge` treated any connected player as usable. GUI
  holds are safe because `ConversationsEvents.judgeDiscussion` checks dimension and distance before
  `holdStill`. Chat, greeting, initiative and typing holds have no lifecycle. `VillagerAttention.tick`
  renewed `HOLD` (erase walk target, stop navigation, look at the player) every tick until
  `untilTick`, and `AttentionLedger.Hold.ownsMovement()` is true for `CONVERSATION` holds.
- **Evidence:** code path only. Nothing in the chat branch compares levels or distance, whereas the
  project's own rule for the same exchange, `EngagementPolicy.evaluate`, refuses the reply
  (`ChoiceSelectionService.resolveVillager`) and drops the queued line (`ChatDelivery.deliver`). The
  stability spec lists dimension changes and unload among the endings every path must honour
  (`docs/…Stability…md:799`).
- **Fix:** for `chat=true`, the player is usable only while `EngagementPolicy.evaluate(player,
  villager, chatModeAddressedRadius²).ok()`. Otherwise the stance is `DROP` and the hold is released
  the same tick. There is no new timer and no new radius.
- **Test:** `ConversationMovementControllerTest.chatHoldOutOfReachIsDropped`. Not observed in-game.

### F3 — a refused first load leaves no dialogue (Major, not fixed)

- **Root cause:** since the reload transaction, any `REFUSED` problem rejects the whole attempt
  (`RELOAD-TRANSACTION-BOUNDARY.md`, "What changed against §1"), and retention restores the previous
  committed bundle. At server start there is none: generation 0 is "explicitly unavailable" (limitation
  1 in that document). So `DialoguesMixin` strips every newly parsed owned key and reinstates nothing.
  The lookup boundary then passes through to MCA's map, where the owned questions are gone.
- **Evidence:** with one broken third-party pack (one truncated JSON file and one scene with an invalid
  `shape`), both the 7.7.1-beta.2 and the 7.6.20 servers logged `Content reload attempt 1: rejected …
  0 retained question(s) … removed 1057 newly parsed owned key(s)`, and `/reload` repeated it. Running
  `datapack disable "file/brokenpack"` recovered (`committed … 1057 retained question(s)`).
- **What changed:** the operator previously saw only per-problem ERRORs and an INFO summary that reads
  like a routine refusal. `summarise` now adds one ERROR when a rejection leaves nothing committed:
  "MCA: Conversations has no dialogue content … until the problem(s) above are fixed or pack
  file/brokenpack removed, then /reload" (`BootstrapRefusalMessageTest`, observed on both servers). The
  policy itself is unchanged: see Q1.
- **Not observed:** what MCA does when a player clicks through to a stripped question. The spec
  records a warning on 7.7.x and an NPE inside a server task on 7.6.20 for the related mid-reload
  window.

### F4 — `talk_about` counted clicks (Major, fixed)

- **Root cause:** `signalQuestTopic` runs for every `conversations_record` of `mcaconversations.cooldown.<topic>`.
  Topic starters record the cooldown when they *begin*, and each topic's "again" result, which MCA
  picks *because* the cooldown memory is still live (e.g. `conversations.family.json`, `checkin_child`,
  chance 1000 on the memory), records it again. The method's own contract said "exactly once per
  completed topic conversation".
- **Fix:** `recordOne` reads `McaCompat.hasMemory` before writing. Only a cooldown written while none
  was running signals (`countsAsTopicConversation`).
- **Test:** `TopicConversationSignalTest`.
- **Impact today:** no quest in MCAQuests, MCAReputation, MCACrime or Ultima Kingdoms uses
  `mcaconversations:talk_about`, so only third-party quest packs were exposed.

### F5–F8 (Minor, fixed)

- **F5:** `@Pseudo` stops an absent target from being an error, not from being looked up.
  `MixinInfo.readDeclaredTargets` asks the config plugin *before* `ClassInfo.forName`, which is the call
  that warns (Mixin 0.8.5 bytecode checked). MCA: Quests' plugin-gated MCA mixins produced no warnings
  in the same boot. New `compat/MixinTargetPlugin` answers "no" only when the owning mod's jar
  (`LoadingModList`, no class loading) lacks the class. Warnings went from 27 to 0 on both roots, and
  7/7 mixins still apply (debug log `Mixing … into forge.net[.conczin].mca…`). Test:
  `MixinTargetPluginTest`.
- **F6:** a floor of 5 ticks between acted-on `typing=true` pings per player. A "stopped typing" ping
  does not reset it, so alternating the two cannot bypass it. The client sends one a second. Test:
  `TypingPingFloorTest`.
- **F7:** seven `commands.mcaconversations.chat.*` keys in `en_us` and `pt_br`. The lang files were
  already sorted and stay sorted; `verifyGeneratedConversationContent` passes. Operator-only
  diagnostics (`gossip`, `social`, `compat`, `history`, …) stay English by design.
- **F8:** `synchronizeConnection()` is called at the top of `refFor` and `currentRef`, as the class
  already does in `state()`, `opened` and `closed`.

## 6. Observations outside this mod

- With the full companion set (MCA 7.6.26, Townstead 0.7.6, Patchouli, Quests 1.7.0, Reputation 0.6.1,
  Crime 0.7.5, Serene Seasons), the server logs six `RuntimeDistCleaner ERROR: Attempted to load class
  net/minecraft/client/Minecraft for invalid dist DEDICATED_SERVER` during auto-subscription. They
  never appear with Conversations + MCA alone, nor with Quests or Crime alone beside it. They come from
  the combination, not from this mod, and I did not attribute them further.
- `ImmersiveSmithing/build/packtest/release-1.1.0/mods/mcacrime-0.7.5.jar` (Sep 23) is a different
  build from today's `MCACrime/build/libs/mcacrime-0.7.5.jar`: it lacks `api/CrimeDialogueHooks` and
  `CrimeDialogueResolver`. Against it, Conversations logs `Failed to register MCA: Crime integration`
  with a stack trace and runs without Crime, as designed. Two different binaries share one version
  number.

## 7. Changes by file

| File | Change |
|---|---|
| `src/main/java/.../conversation/ContentStaging.java` | F1: `bundledPack(pack, ownFileName)`, testable `unauditedScenes` overload |
| `src/main/java/.../conversation/ContentSources.java` | F1: `ownPackFileName()`, the loader-specific half (moved here when mirroring, so `ContentStaging` stays identical on both loaders) |
| `src/main/java/.../chat/ConversationMovementController.java` | F2: chat holds judged by `EngagementPolicy` at `chatModeAddressedRadius` |
| `src/main/java/.../compat/mca/ConversationsMcaRegistrar.java` | F4: `isTopicCooldown`, `countsAsTopicConversation`; `recordOne` reads before it writes; corrected Javadoc |
| `src/main/java/.../conversation/ContentReloadCoordinator.java` | F3: no-content ERROR and `refusedPacks` |
| `src/main/java/.../compat/MixinTargetPlugin.java` (new) | F5 |
| `src/main/resources/mcaconversations.mixins.json` | F5: `"plugin"` |
| `src/main/java/.../mixin/NetworkHandlerMixin.java` | F5: corrected Javadoc claim about `@Pseudo` |
| `src/main/java/.../chat/VillagerAttention.java` | F6: `TYPING_PING_FLOOR_TICKS`, `acceptTypingPing`, cleared in `clearPlayer`/`reset` |
| `src/main/java/.../command/ConversationsCommand.java` | F7: translatable replies |
| `src/main/resources/assets/mcaconversations/lang/en_us.json`, `pt_br.json` | F7: 7 keys each |
| `src/main/java/.../client/dialogue/ClientChoiceMessages.java` | F8 |
| `src/test/java/.../conversation/SceneSocialAuditTest.java` | F1 regression test |
| `src/test/java/.../chat/ConversationMovementControllerTest.java` | F2 regression test |
| `src/test/java/.../compat/mca/TopicConversationSignalTest.java` (new) | F4 |
| `src/test/java/.../conversation/BootstrapRefusalMessageTest.java` (new) | F3 message |
| `src/test/java/.../compat/MixinTargetPluginTest.java` (new) | F5 |
| `src/test/java/.../chat/TypingPingFloorTest.java` (new) | F6 |
| `MODMAP.md` | Regenerated; Current focus, Decisions (4), Known issues (3) |
| `CHANGELOG.md` | `### Fixed — audit (2026-09-30)` under 1.8.0 |
| `docs/ROADMAP.md` | §2 wording, §3.7 limitation, §4 open decision 5, §5 check table |

No registry id, NBT key, config key, dialogue or node id, or packet id was renamed. The network
protocol is unchanged. There is no `src/generated/` and no datagen, so `runData` does not apply.

## 8. Verification

Every Gradle command ran through `/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh`.

| Check | Result | Evidence |
|---|---|---|
| Baseline `build` | PASS; 1,729 tests, 0 failures, 6 skipped | `/tmp/gradle-MCAConversations-build-20260930-192722.log` |
| Final `build verifyGeneratedConversationContent verifyVoiceOverlays` (all three tasks executed, none up to date) | PASS; `test` 1,741 / 0 failures / 6 skipped (the Capitals and Townstead real-jar probes); drift gates 2 and 5 tests, 0 failures | `/tmp/gradle-MCAConversations-build-20260930-195226.log` |
| `check_mod.py MCAConversations` | 0 errors, 0 warnings, 0 notes | console |
| Jar | `build/libs/mcaconversations-1.8.0.jar`, sha256 `117d7c5a…fa331d11`, carries the plugin | — |
| Dedicated server, final jar, MCA 7.7.1-beta.2 | Start, `/reload`, commands, summon ×2, kill, stop exit 0; 0 WARN/ERROR from this mod; 7/7 mixins; 0 absent-root warnings | `/tmp/mcac-boot/final-771b2/logs/{latest,debug}.log` |
| Same, MCA 7.6.20 + Architectury 9.2.14 | Same result | `/tmp/mcac-boot/final-7620/logs/` |
| Same, MCA 7.6.26 + Townstead 0.7.6 + Patchouli + Quests 1.7.0 + Reputation 0.6.1 + Crime 0.7.5 (2026-09-30) + Serene Seasons | Same result; all five integrations registered; Townstead 14 capabilities | `/tmp/mcac-boot/final-full/logs/` |
| Broken third-party pack at startup (7.7.1-beta.2, 7.6.20) | Refused; new ERROR names `file/brokenpack`; `datapack disable` recovers to generation 1 | `/tmp/mcac-boot/broken2`, `broken3` |
| Release parity vs the NeoForge port, before mirroring | FAIL: 45 differences, 26 already present at `ebef667`, 19 from this audit | `tools/verify_release_parity.py`, run against a `HEAD` worktree and the working tree |
| Release parity after mirroring (same day, §11) | PASS from both copies: 2,028 identical, 191 reviewed adaptations | `tools/verify_release_parity.py` in each repository |
| `runClient` / production client | **Not run** | — |

## 9. Open questions

1. **First-load refusal policy (F3).** Keep all-or-nothing at start-up (today: the server runs without
   this mod's dialogue until an operator notices), or, when nothing has ever been committed, publish
   this mod's own content and quarantine only the packs that carried a refusal? The second option is
   a change to the reload transaction's design, so I did not make it.
2. **MCA: Crime declaration (F10).** Either add
   `[[dependencies]] modId="mcacrime" mandatory=false versionRange="[0,)" ordering="AFTER"` (consistent
   with family rule 5 and the changelog header; no ordering cycle, since Crime declares nothing about
   this mod and Reputation already orders both after it), or correct the changelog header to say Crime
   is undeclared. `CrimeBridge.java:65` currently says the latter was deliberate.
3. **Quest topic names (F9).** Add `mcaconversations.topic.<id>` display names (about 40 topics, two
   locales)? That is new player-facing text, so I left it. The existing `dialogue.chatmode.topic.*`
   strings are first-person ("my dreams") and do not fit a quest card.
4. **`talk_about` semantics (F4).** I made it count at most one conversation per topic per villager per
   cooldown window, which matches "heart-to-heart(s)" and "exactly once". If you meant every topic
   start to count, revert `countsAsTopicConversation` to `isTopicCooldown`.
5. ~~**NeoForge mirror.**~~ Done the same day; see §11.
6. **MCAQuests / MCAReputation.** No change is needed in either for these findings. For MCA: Crime (not
   in the brief's list): publish the 2026-09-30 `0.7.5` under a new version number, so the Sep 23 jar
   that lacks the dialogue API cannot be mistaken for it.

## 10. Not verified, and an in-game checklist

**Not verified:**
- Anything a client does: card layout at GUI scales, Escape, the history and settings overlays,
  Townstead's screen, numbered replies, the close and taken-over sentences.
- Every conversation actually played end to end.
- F2's effect on a real villager.
- What MCA does when a stripped question is clicked (F3).
- The NeoForge port in a client, and in a production (non-dev) server; its dev `runServer` is covered
  in §11.

**Checklist.** Singleplayer (integrated) *and* a dedicated server, MCA 7.6.x and 7.7.1:

1. Open a villager, go *Conversations → a topic*, answer to the end. Lines display, hearts change
   once, the card closes cleanly on the last answer and on Escape.
2. **Interrupted:** mid-topic, walk 10 blocks (the reply is refused with an out-of-range sentence),
   then 25 blocks (the window closes). Repeat with: the villager attacked by a zombie, the villager
   killed, `/tp` into the Nether, logging out, dying, the villager going to bed, `/reload` with the card
   open. Each ends once, and the villager walks away afterwards.
3. **Chat mode (F2):** say "hello" to a villager, then walk 30 blocks away, `/tp` away, or change
   dimension. The villager resumes its schedule immediately, not after 30 s.
4. **Two players at once:** A talks to a villager and B right-clicks it. B takes over, A sees "speaking
   with someone else", and A's late click does nothing to B's conversation. Then both use chat mode near
   the same villager: each gets only their own numbered choices, and the confidant greeting reaches
   only its player.
5. **Broken pack (F3):** drop a pack with a malformed `conversation_scenes` file into a new world. The
   log carries the "has no dialogue content" ERROR naming it. `/datapack disable` it, and topics return
   without a restart.
6. **Quests (F4):** with a quest that has `mcaconversations:talk_about` count 2, clicking one topic
   twice in a minute advances it once; the same topic after its cooldown lapses advances it again.
7. `/conversations chat status` in `pt_br` shows Portuguese.
8. `/conversations social audit` on a server with a datapack scene lacking `social` lists only that
   pack.

## 11. Follow-up the same day: NeoForge parity and deployment

At your request the fixes were mirrored to `1.21.1 Ports/MCAConversations_1.21.1` and both lines deployed.

- **Mirrored verbatim** (each was byte-identical between the loaders before the audit): the six changed
  main classes, both lang files, and the five changed or new tests other than the plugin test.
- **Adapted:**
  - `compat/MixinTargetPlugin`: `net.neoforged.fml.loading`, and only the port's one MCA root, because
    `NeoForgePortLintTest` forbids the Forgix root.
  - `MixinTargetPluginTest`: NeoForge's unit JVM loads MCA, so the port also checks the lookup against
    the real MCA jar.
  - `ContentSources.ownPackFileName`: `net.neoforged.fml.ModList`.
  - `ConversationsCommand`: data attachments.
  - `NetworkHandlerMixin`: the same doc correction, in the port's single-target wording.
  - `mixins.json`: the `plugin` line.
- **Forge-side change made to allow this:** `ownPackFileName` moved from `ContentStaging` into
  `ContentSources`, which was already a reviewed adaptation, so `ContentStaging` stays identical on both
  loaders. The Forge plugin's table now lists all four `McaBinding` roots.
- **Not applicable to NeoForge:** F1 (the port's packs are named `mod/<id>`, which was already
  recognised) and the MCA half of F5 (one MCA root, so no absent root to warn about).
- **Parity:** 26 differences pre-dated this audit. They were the MCA: Crime integration and the
  2026-09-27/28 family-pass edits on both sides, which had never been pinned. All were reviewed as
  loader adaptations, with no logic drift: a normalised diff left only package, attachment, payload,
  SavedData-API, test-path and 1.21.1 personality-roster differences. The manifest was re-pinned
  (14 updated, 6 added) and is identical in both repositories. Result from both copies: 2,028 identical
  files, 191 reviewed adaptations, verified.
- **Builds:**
  - Forge: `build verifyGeneratedConversationContent verifyVoiceOverlays` PASS, 1,742 tests, 0
    failures, 6 skipped. `mcaconversations-1.8.0.jar`, sha256 `0ceeaf40…`.
  - NeoForge: the same tasks plus `verifyJarContents` PASS, 1,786 tests, 0 failures, 6 skipped.
    `mcaconversations-neoforge-1.8.0+1.21.1.jar`, sha256 `589aa9d7…`.
- **Runtime:**
  - The final Forge jar on real servers (MCA 7.7.1-beta.2; MCA 7.6.20 + Architectury): 7/7 mixins,
    0 warnings or exceptions, content committed, social audit clean.
  - NeoForge `runServer` with MCA loaded as a real mod, driven over RCON through the same commands:
    8/8 mixins, 0 exceptions, content committed at start and after `/reload`.
- **Changelogs:** both carry a 2026-09-30 audit section. The NeoForge header's stale Townstead range
  (`[0.7.5,0.8)`) is corrected to the declared `[0.7.5,0.9)`.

