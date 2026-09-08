# Release 1.6.3 acceptance ledger

This is the acceptance record for the "Slice A" pass described in
`docs/MCAConversations-Conversation-Systems-Research-and-Implementation-Plan.md` (§20.1): findings
F01–F09, F12–F14, and the critical portion of WP08. F10, F11 and the rest of WP08 through WP16 are
deferred to 1.7.x (§e).

**No in-game run was performed in this session.** Every figure below comes from source inspection,
unit/lint suites, real-jar binding probes, and file hashes — not from a running client or server. See
§21.3 of the plan for what "production validation" means and why a ForgeGradle dev runtime does not
substitute for it.

## a) Baseline recorded before work began

| | Forge (this repo) | NeoForge port (`MCAConversations_1.21.1`) |
| --- | --- | --- |
| HEAD | `df0b5c82d8e0758068ca22b58d759c881c150db3` | `e9d097367ec1722b05aa3201c6427c62135fd125` |
| Version | 1.6.1 | 1.6.1 |
| Previous release jar | `mcaconversations-1.6.1.jar` | `mcaconversations-neoforge-1.6.1+1.21.1.jar` |
| SHA-256 | `e60186b14aa29301f03c6864202465556a6031ea057826d079aab759768cd2f2` | `6d59f0e37ae413ea7f86b7c395779828cdc66c865a676c0f272feef446406bb3` |

Both jar hashes above were recomputed against the `build/libs/` copies present in each checkout and
match exactly.

At this baseline, `build.gradle` resolved the optional MCA: Quests / MCA: Reputation integrations from
`${projectDir}/../MCAQuests/build/classes/java/main` and `${projectDir}/../MCAReputation/build/classes/java/main`
by default (`git show df0b5c8:build.gradle`, lines 156 and 168) — sibling checkout class directories
that a clean clone or CI does not have. A clean-checkout `compileJava` therefore failed to compile
`compat.quests`/`compat.reputation`, which reference those classes directly. This is why F01 is P0:
every other finding is reachable only from a tree that can already compile.

## b) Provider artifacts

From `gradle/sibling-apis.properties`:

| Provider | Version | Commit | Jar | SHA-256 |
| --- | --- | --- | --- | --- |
| MCA: Quests | 1.6.4 | `af372b6951191eac2b87b35e3108af3a7f64edfe` | `libs/api/mcaquests-1.6.4-api.jar` | `f4033ea8d02e5220ce793f4c13851239a22294fcd866f5eb6af076462fe3f2dd` |
| MCA: Reputation | 0.4.1 | `edb8ebdafdbe612105e0528e41bd943356e67b42` | `libs/api/mcareputation-0.4.1-api.jar` | `e559e7920d56bee37ed93f0c895c2ef29c6e0addef748ac2c49352aa11ce7ebe` |

Both jars in `libs/api/` were rehashed in this session and match the manifest exactly, and
`verifySiblingApis` checks this same hash before every `compileJava`.

## c) Findings F01–F14

Status, the slice commit, the main classes touched, and the covering unit tests. Classes and tests
are drawn from the eight slice commit messages, `git show --stat` on each, and the `## [1.6.3]`
`CHANGELOG.md` bullets. The full table (with reasoning quoted from the audit) also appears in
`docs/STABILIZATION-2026-09.md` under "This release's finding disposition"; it is repeated here with the
outstanding production check for each finding.

| Finding | Status | Slice | Main classes | Tests | Production check still required |
| --- | --- | --- | --- | --- | --- |
| F01 — clean CI cannot compile optional integrations | Fixed | `d1bbbc4` | `build.gradle` (`verifySiblingApis`, `siblingApi`), `gradle/sibling-apis.properties`, `libs/api/*.jar` | `SiblingApiManifestTest` | A production-mapped client and dedicated server on both loaders with supported MCA jars and both MCA package roots on Forge |
| F02 — nine answers gated on unrecognized `!child` | Fixed | `3999742` | `FeatureId`, `conversation/AgeGroup`, `conversation/TopicAgeGate`, `mixin/QuestionMixin`, `mixin/InteractionDialogueMessageMixin`, `chat/GatePreview`, `hub/DynamicHub`, the three `conversations.cat.*.json` category files | `NativeConstraintTokens`/`ConstraintVocabularyProbeTest`, `ContentLintTest` | A child, teen and adult villager exercising each of the nine topics in all three menu styles and in chat |
| F03 — unknown feature id treated as enabled | Fixed | `3999742` | `FeatureId`, `McaConversationsConfig`, `compat/mca/ConversationsMcaRegistrar` | `FeatureOffLintTest` | Covered by F02's in-game pass; no separate in-game check identified |
| F04 — named chat executes outside its radius | Fixed | `e078b50` | `chat/VillagerFinder`, `chat/ChatDelivery`, `chat/ChatModeDispatcher`, `conversation/EngagementPolicy`, `conversation/ChoiceSelectionService` | `VillagerFinderTest`, `EngagementPolicyTest` | A named villager at three-quarters of the configured radius on both horizontal axes gets no hearts and no reply |
| F05 — chat work queued before cancellation is known | Fixed | `e078b50` | `chat/AcceptedChat`, `event/ConversationsEvents` | `AcceptedChatSnapshotTest` | A chat mod that cancels or rewrites messages, with `chatModeLocalChat` both on and off |
| F06 — Capitals caches can outlive their world | Fixed | `6018815` | `compat/ServerEpoch`, `compat/CacheLifetime`, `compat/capitals/ReflectiveCapitalsBridge`, `compat/CapitalsBridge`, `event/ConversationsEvents` | `CapitalsCacheLifetimeTest` | Same JVM: open world A, stop, then open an earlier copy of a save with the same resident and a different court |
| F07 — newer-schema history is not preserved losslessly | Fixed | `9499e5a` | `history/ConversationHistoryStore`, `history/ConversationHistorySavedData` | `FutureSchemaTest` | Hand-edit a save's history version to 3, play, save, and confirm the original tag is byte-identical afterward |
| F08 — history decoding bypasses declared collection limits | Fixed | `9499e5a` | `history/HistoryCaps`, `history/VillagerHistory`, `history/PairHistory`, `history/ConversationHistoryBackupSavedData` | `ConversationHistoryStoreTest` | Same as F07's save-fixture check, observed against an oversized file |
| F09 — eviction is insertion-order, not activity-order | Fixed | `9499e5a` | `history/ConversationHistoryStore#setLiveSessionPredicate`, `history/VillagerHistory#isProtected`, `conversation/ConversationSessions#hasSessionWith` | `EvictionOrderTest` | Fill the store in a real world, save/reload, and confirm the same villager is evicted each time |
| F10 — some source prose violates identity/grammar assumptions | **Deferred to 1.7.x** (WP12) | — | — | — | — |
| F11 — selection caps can suppress continuity before scoring | **Deferred to 1.7.x** (WP09) | — | — | — | — |
| F12 — reload/override semantics need one coherent generation | Fixed, critical portion only | `908c66a` | `conversation/ContentGeneration`, `conversation/ContentGenerationListener`, `conversation/ConversationCatalogLoader`, `conversation/ConversationGuard`, `conversation/ConversationSession`, `conversation/ChoiceSelectionService` | `ReloadResilienceTest`, `ContentGenerationTest`, `GuiOfferLifetimeTest` | Two clients, GUI↔chat transitions, save/reload, `/reload`, and each optional integration absent/disabled/present/failing, then the combined pack |
| F13 — fingerprint/slot-rendering edge cases | Fixed | `1240c4b` | `context/ContextValue`, `history/NarrativeValue`, `scene/SlotBinder`, `scene/SlotType`, `template/SlotRenderer`, `template/ConversationsSay` | `ContextFingerprintTest`, `SlotBinderTest`, `SlotRendererTest` | A village with a non-ASCII or punctuated name, observed in a running world |
| F14 — CI and docs overstate or omit current behavior | Fixed | `d1bbbc4` (CI runs the drift gates by name) plus this documentation pass | `README.md`, `MODMAP.md`, `DATAPACK.md`, `CONFIG.md`, `docs/STABILIZATION-2026-09.md` | — | — |

WP08's critical portion (every conversation close names its reason and tears down through one path)
landed in slice 8, `8120ca4`: `conversation/CloseReason` (new), `conversation/ConversationSessions`,
`chat/ChatModeScheduler`, `chat/ChatModeSession`, `chat/ChatDelivery`, `chat/VillagerAttention`,
`scene/ConversationPlanner`; covered by `SessionCloseReasonTest`, `ChatModeSchedulerTest` and
`ChatDeliveryTest`. Its outstanding production check is the same as F12's: multiple close paths
(logout, player or villager death, timeout, server stop, and an out-of-range, dimension-changed or
player-gone engagement abort, all of which now tear down through `ConversationSessions#close`)
exercised in a real, running world. A reload's stale-offer refusal is separate: it only ends the
topic, not the session (`conversation/ConversationGuard.java`, `conversation/ChoiceSelectionService.java`
call `ConversationSessions#endTopic` with `CloseReason.CONTENT_RELOADED`).

## d) Final artifacts of this session

| | Forge | NeoForge port |
| --- | --- | --- |
| Jar | `build/libs/mcaconversations-1.6.3.jar` | `build/libs/mcaconversations-neoforge-1.6.3+1.21.1.jar` |
| SHA-256 | `d98fd95fc449d7669585f5c890633b1f8e18211c8f8132a9ac2a2ba1d1e10604` | `8bd6d0032593a4bb58aea83ce6356ab5a68a11a9d9f0eff6f8c809cd780b5e3b` |

Both hashes were recomputed against the jars in each repo's `build/libs/` after the final build of
this session, which followed the engagement-abort change, and match.

Unit suites at the last verifier run (aggregated from `build/test-results/test/*.xml`):

| | Forge | Port |
| --- | --- | --- |
| Tests | 1258 | 1299 |
| Failures/errors | 0 | 0 |
| Skipped | 6 | 6 |

The 6 skips on each side are the jar-property-gated probe tests, gated behind `-PcapitalsJar` /
`-PtownsteadModernJar` / `-PtownsteadLegacyJar` and not run without them: on Forge,
`CapitalsBindingProbeTest#manifestResolvesAgainstTheRealCapitalsJar`,
`TownsteadBindingProbeTest#manifestResolvesAgainstTheRealTownsteadJar`,
`TownsteadBindingProbeTest#collidingOverloadsResolveToTheIntendedOne`,
`TownsteadBindingProbeTest#theNeedScalesMatchTownstead`,
`TownsteadBindingProbeTest#theEntryPointTakesAVanillaEntity`, and
`TownsteadUiMixinProbeTest#supportedTownsteadJarStillExposesTheDecoratedChoiceSurface`
(`TownsteadBindingProbeTest#resolutionWithoutTownsteadIsAbsentAndDoesNotThrow` is not among the
skips — it runs, and exercises the absent-jar path). These are recorded as **skipped, not passed**;
skipping is not evidence the integration works, only that it was not exercised against a real jar in
this run.

`src/content/**` and `src/main/resources/data/mcaconversations/**` are byte-identical between the two
repositories apart from line endings (`diff -rq --strip-trailing-cr`, no differences reported).

## e) Deferred to 1.7.x

- **F10** (WP12, editorial repair) — source prose that violates its own identity/grammar assumptions
  (e.g. the outlaw profession's ungendered pack using a gendered self-description).
- **F11** (WP09, selection) — the director's 32-candidate base-priority admission cutoff can exclude a
  candidate before continuity scoring ever sees it.
- **The rest of WP08** — utterance ids, a transcript, per-recipient disclosure bookkeeping, durable
  checkpoints, resume-after-restart, and the automatic-node budget. Only the close-reason/teardown
  portion landed this release (§c, `8120ca4`).
- **WP10** — player-facing transcript, availability reasons, narrator/input/layout parity work beyond
  what already exists in `client.dialogue`.
- **WP11** — authoring-tool improvements (schemas, source maps, simulator, reports, samples) beyond
  the existing `ContentCompiler`/`VoiceFamilyCompiler` pipeline.
- **WP13** — the everyday-expansion scene set (48 proposed scenes and pilot micro-arcs).
- **WP14** — the Quests/Reputation/Capitals/Townstead/Seasons integration capability matrix and
  exact-artifact production scenarios called for in §21.3.
- **WP15** — optional expansions (NPC groups, spectators, gestures/audio, new Crime/Skills adapters).
- **F12's staging coordinator** — this release publishes one monotonic content generation after every
  reload and refuses a stale offer against it (`ContentGenerationListener`), but does not stage and
  cross-validate related catalogs before publishing, or roll back a partially-failed reload as one
  unit — the coordinator that §7's F12 implementation note describes.
- **F01's reflective-binding alternative** — this release solves F01 by vendoring compile-only API
  jars for MCA: Quests and MCA: Reputation (`libs/api/`, hash-pinned). It does not extend
  `compat.mca`'s name-based reflective-binding pattern (no compile-time dependency at all, matching
  how this mod binds MCA itself) to the two optional add-ons; that alternative was not attempted this
  release.
