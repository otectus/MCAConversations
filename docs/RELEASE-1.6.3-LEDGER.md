# Release ledger — 1.6.3 (NeoForge 1.21.1)

Acceptance ledger for Slice A of the September 2026 conversation-systems audit
(`docs/MCAConversations-Conversation-Systems-Research-and-Implementation-Plan.md`, Forge repository
only), keyed to findings F01–F14 as the plan requires. **No in-game run was performed in this
session.** Every entry below is either a build/test artifact produced by this session's Gradle runs
or a static read of the source and the plan document; nothing here substitutes for the production
checks it lists as still required.

## Baseline

Before this release, port `HEAD` was `e9d097367ec1722b05aa3201c6427c62135fd125` ("1.6.1: stabilization,
bilingual narrative pass, and datapack samples"), at `mod_version=1.6.1`. The jar built from that
commit, `mcaconversations-neoforge-1.6.1+1.21.1.jar`, has SHA-256
`6d59f0e37ae413ea7f86b7c395779828cdc66c865a676c0f272feef446406bb3`.

Before this release, a clean checkout of that commit could not compile the optional integrations:
`build.gradle` at `0f0feb1~1` defaulted `mcaQuestsApiPath`/`mcaReputationApiPath` to sibling
class-output directories (`../MCAQuests_1.21.1/build/classes/java/main`,
`../MCAReputation_1.21.1/build/classes/java/main`) and, when either was absent, logged a warning and
silently excluded the affected compat sources (`sourceSets.main.java.exclude(...)` over
`compat/quests/ConversationsQuestsCompat.java`, `ConversationsQuestsEvents.java`,
`QuestVoiceResolver.java`, `TalkAboutObjective.java`, `UnlockTopicReward.java`, and all of
`compat/reputation/**`). CI checks out only this repository, so its build always took the exclude
branch and always shipped without either integration — quietly, since the build still went green.
This is the reason F01 is P0.

## Provider artifacts (`gradle/sibling-apis.properties`)

| Provider | Version | Commit | SHA-256 |
| --- | --- | --- | --- |
| MCA: Quests | 1.6.4 | `6b7ef4662f77397b361fea8bfb71f3539294a807` | `208a2a995248f3a607221fada95c52388e58e997d6c94e984c77cfe29908fc13` |
| MCA: Reputation | 0.4.1 | `b5c09ca46ac81ce6e5fda3008aa8314401eebd08` | `5af80d54f20a931a5e8e48a8569617e59ee6f9a7ec9853912f1a540267570d27` |

Both jars are vendored under `libs/api/` (`mcaquests-1.6.4-api.jar`, `mcareputation-0.4.1-api.jar`). A
missing default jar fails at configuration time, in `siblingApi()` (`build.gradle` ~129-137), naming
the expected path; `verifySiblingApis` then hashes only the pinned (non-overridden) jar against the
recorded SHA-256 before `compileJava`, and skips the hash check entirely when a `-PmcaQuestsApiPath` /
`-PmcaReputationApiPath` override is in effect.

## Findings F01–F14

Status and slice commits below come from `git log --oneline -9` on this branch and `git show --stat`
on each slice; production checks come from the plan document's own Acceptance sections (or the
narrower scope given for this release). "Fixed" means the static/unit-level defect the finding
describes is corrected and covered by a test; it does not mean the production check was run.

### F01 — clean CI cannot compile optional integrations
**Fixed**, slice 1 (`0f0feb1`). Classes/tests: `build.gradle`, `gradle/sibling-apis.properties`,
`libs/api/mcaquests-1.6.4-api.jar`, `libs/api/mcareputation-0.4.1-api.jar`; `SiblingApiManifestTest`.
Production check still required: an empty workspace clone builds both loader targets from a clean
checkout with no sibling repository present, and the resulting jar contains no shaded
MCA/Quests/Reputation classes (`verifyJarContents` already checks this at build time; a real clean-clone
run has not been performed this session).

### F02 — nine answer gates use an unrecognized `!child` constraint
**Fixed**, slice 2 (`807fef7`). Classes/tests:
`data/mcaconversations/dialogues/conversations.cat.{chitchat,personal,village}.json`,
`conversation/TopicAgeGate`, `conversation/AgeGroup`, `mixin/QuestionMixin`; `ContentLintTest`,
`FeatureIdTest`. Production check still required: child/teen/adult villagers on each of the nine
affected topics, in all three menu styles and in chat.

### F03 — an unknown feature ID is treated as enabled
**Fixed**, slice 2 (`807fef7`), same commit as F02. Classes/tests: `FeatureId`,
`McaConversationsConfig`, `compat/mca/ConversationsMcaRegistrar`; `FeatureIdTest`. Production check
still required: production-mapped client and dedicated server, misspelling every shipped feature id
in a datapack and confirming no typo activates content or produces a misleading fallback.

### F04 — named chat can execute outside its configured radius
**Fixed**, slice 3 (`d238eab`). Classes/tests: `chat/VillagerFinder`, `chat/ChatDelivery`,
`chat/ChatModeDispatcher`; `VillagerFinderTest`, `EngagementPolicyTest`. Production check still
required: a named villager at three quarters of the radius on both horizontal axes gets no hearts and
no reply.

### F05 — chat work is queued before downstream cancellation is known
**Fixed**, slice 3 (`d238eab`), same commit as F04. Classes/tests: `chat/AcceptedChat`,
`conversation/EngagementPolicy`, `event/ConversationsEvents`; `AcceptedChatSnapshotTest`,
`EngagementPolicyTest`. Production check still required: a chat mod that cancels or rewrites
messages, tested with `chatModeLocalChat` both on and off.

### F06 — Capitals caches can outlive the world that populated them
**Fixed**, slice 4 (`beda442`). Classes/tests: `compat/CacheLifetime`, `compat/ServerEpoch`,
`compat/capitals/ReflectiveCapitalsBridge`; `CapitalsCacheLifetimeTest`. Production check still
required: within one JVM, open world A, visit a capital, stop, then open an earlier copy of the save
with the same resident and a different court, and confirm the first conversation after reopening uses
the new world's data.

### F07 — newer-schema preservation is not implemented losslessly
**Fixed**, slice 5 (`d5bfde0`). Classes/tests: `history/ConversationHistorySavedData`,
`history/ConversationHistoryStore`; `FutureSchemaTest`. Production check still required: hand-edit a
save's history schema version to 3, play, save, and confirm the original file is written back byte
for byte (degraded-read-only, not silently downgraded).

### F08 — history decoding bypasses declared collection limits
**Fixed**, slice 5 (`d5bfde0`), same commit as F07. Classes/tests: `history/HistoryCaps`,
`history/VillagerHistory#enforceLoadedCaps`, `history/PairHistory#enforceLoadedCaps`;
`ConversationHistoryStoreTest`. Production check still required: load an oversized same-schema save
for every capped collection and confirm each loads within its declared bound and retains the
intended records.

### F09 — world history eviction is insertion-order, not activity-order
**Fixed**, slice 5 (`d5bfde0`), same commit as F07/F08. Classes/tests:
`history/ConversationHistoryStore#evictionCandidate`; `EvictionOrderTest`,
`LastActivityDerivationTest`. Production check still required: fill the store, refresh an old
villager, add another record, and confirm the evicted villager is the genuinely inactive one, on both
the first pass and after a save/reload with the same activity state.

### F10 — some source prose violates its own identity and grammar assumptions
**Deferred to 1.7.x** (WP12, editorial repair). No slice commit in 1.6.3.

### F11 — selection caps can suppress continuity before it is scored
**Deferred to 1.7.x** (WP09, selection). No slice commit in 1.6.3.

### F12 — reload and override semantics need one coherent generation
**Fixed** (the targeted risk this release scoped: stale offers against replaced content), slice 6
(`13fecae`). Classes/tests: `conversation/ContentGeneration`, `conversation/ContentGenerationListener`,
`conversation/ConversationCatalogLoader`, `conversation/ConversationGuard`;
`ContentGenerationTest`, `ReloadResilienceTest`,
`GuiOfferLifetimeTest`. F12's staging coordinator — validating and publishing multiple addon catalogs
as one cross-referenced generation, rather than each catalog publishing independently and only
offers being generation-stamped — is **not** built; see Deferred below. Production check still
required: two clients, exercising GUI↔chat transitions, save/reload and `/reload` around a datapack
change, confirming an active exchange either survives a compatible reload or closes with a clear
reason, and that a renamed or deleted route cannot execute via an old offer.

### F13 — fingerprints and runtime slot rendering have extension edge cases
**Fixed**, slice 7 (`79850b3`). Classes/tests: `context/ContextValue`, `history/NarrativeValue`,
`scene/SlotBinder`, `scene/SlotType`, `template/SlotRenderer`; `ContextFingerprintTest`,
`SlotBinderTest`, `SlotRendererTest`. Production check still required: confirm in play that a village
name with punctuation or non-ASCII characters renders literally rather than as a raw translation key,
and that an unknown/missing village name still falls back to the existing generic text.

### F14 — CI and public documentation overstate or omit current behavior
**Fixed.** The CI half (both content-drift gates run by name) landed in slice 1 (`0f0feb1`,
`.github/workflows/build.yml`). The documentation half — the mixin inventory, the client-code capability
summary and the `InitiativePlanner`-aware initiative description in `README.md`, plus the
`DATAPACK.md`/`CONFIG.md` updates for F02/F03's new vocabulary — is this documentation pass, not a
numbered commit. Production check still required: none specific to F14; its acceptance is the
`doc-checker` audit of this pass against the source it cites.

## WP08 (critical part)

Every conversation close now names its reason and runs one teardown for session, attention lease and
queued lines — including the engagement-abort path in `chat/ChatModeDispatcher` that fires when the
pair drifts out of range, changes dimension, or the player is gone before `selectAnswer` runs, which
now closes the session in full rather than only ending the topic — landed in slice 8 (`56c5805`).
Classes/tests: `conversation/CloseReason`, `conversation/ConversationSessions`,
`chat/ChatModeDispatcher`, `chat/ChatModeScheduler`, `chat/ChatModeSession`, `chat/VillagerAttention`,
`scene/ConversationPlanner`; `SessionCloseReasonTest`, `ChatModeSchedulerTest`, `ChatDeliveryTest`.

## Final artifact of this session

`build/libs/mcaconversations-neoforge-1.6.3+1.21.1.jar`, SHA-256:
`8bd6d0032593a4bb58aea83ce6356ab5a68a11a9d9f0eff6f8c809cd780b5e3b` (final build of this session,
after the engagement-abort change).

Unit suite at the last verifier run: **1,299 tests, 0 failures, 6 skipped**
(`build/test-results/test/*.xml`, summed across all report files). The six skipped cases all require
an explicit real-jar path that was not supplied this run, and are recorded as skipped, not passed:

- `CapitalsBindingProbeTest.manifestResolvesAgainstTheRealCapitalsJar()`
- `TownsteadBindingProbeTest.manifestResolvesAgainstTheRealTownsteadJar()`
- `TownsteadBindingProbeTest.collidingOverloadsResolveToTheIntendedOne()`
- `TownsteadBindingProbeTest.theNeedScalesMatchTownstead()`
- `TownsteadBindingProbeTest.theEntryPointTakesAVanillaEntity()`
- `TownsteadUiMixinProbeTest.supportedTownsteadJarStillExposesTheDecoratedChoiceSurface()`

`src/content/**` and `data/mcaconversations/**` were diffed against the Forge repository
(`C:\Projects\MCAConversations`) with line-ending differences ignored (`diff -rq --strip-trailing-cr`);
both trees reported byte-identical.

## Deferred to 1.7.x

- WP16 (release closure) is **not** deferred: this ledger is its document, and only the production
  checks it lists as still required remain outstanding.
- F10 (editorial repair, WP12) and F11 (selection admission, WP09).
- The rest of WP08: utterance ids, transcript, per-recipient disclosure bookkeeping, durable
  checkpoints, resume after restart, and the automatic-node budget. Only the close-reason/teardown
  part landed this release.
- WP10 (player experience), WP11 (authoring tools), WP13 (everyday expansion), WP14 (integrations),
  WP15 (optional expansions).
- F12's staging coordinator: catalogs still publish independently rather than as one validated,
  cross-referenced generation; this release's fix is stale-offer refusal against whichever
  generation is current, not multi-catalog staging.
- F01's reflective-binding alternative: the shipped fix is vendored, hash-checked compile-only API
  jars; a fully reflective binding for the optional Quests/Reputation integrations (avoiding a
  compile-time API dependency altogether) was not built.
