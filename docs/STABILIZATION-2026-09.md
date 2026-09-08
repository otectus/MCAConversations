# Stabilization and narrative review — September 2026

Applies to Forge 1.20.1 and NeoForge 1.21.1. The Forge starting point was current `main`, `db723ac7430a22b5d2664f966f59754f99d65728`; the existing NeoForge port started at `78558e8fddf301f31c05e6d9ef720d1ca687e947`. Local untracked design documents were preserved. The existing port directory is `MCAConversations_1.21.1`.

## Runtime corrections

- GUI mouse, numbered choices and contextual free-text replies consume the same current, server-validated offer. A missing, consumed, stale, wrong-target or wrong-frontend offer cannot run actions. Range, life, visibility, ownership and chat settings are checked before execution.
- Offer revisions survive session recreation; a new client connection resets the client watermark. Unanswered GUI pages retain their semantic context and budget. Reopening an active topic preserves its spent budget and valid scene plan; explicitly ending an exchange permits a fresh one.
- Delayed chat keeps per-player order and only the final current line presents choices. Invalid speakers and departed players cannot receive stale dialogue. Nonpublic disclosures are kept out of public broadcasts. Group interjections require the relevant subject/episode and a licensed response; kinship and profession alone do not establish knowledge.
- Server shutdown and player death clear transient sessions and pending deliveries. Malformed Reputation filters fail closed instead of matching unrelated standing/incidents, and its query facade is published only after hook registration succeeds.
- At the pair-store limit, replacing a villager's last record now retains the new record in the store and save. Invalid capacities and maximum timestamps cannot trap eviction in a loop.
- The first disposition write begins at the villager's personality baseline. Clamped axis changes report and charge only actual movement, preserving the daily budget.
- Repeatable affection decisions no longer consume the lifetime milestone ledger. A full replay ledger refuses an untrackable payment instead of allowing repeatable `once` rewards; this reports `HISTORY_CAPACITY` in diagnostics.
- A quest promise observes the exact promised quest's completion/failure event. Player actions cannot settle promises made by a villager. Partial Quests registration leaves no query facade behind. `event_observed` remains a reserved, unavailable resolver until a target-matching observer exists; it cannot falsely accuse a player of breaking a promise.
- History pruning reports state transitions as mutations so abandoned episodes and lapsed threads survive save/reload. Resolved threads stay resolved. Failed history insertions no longer report successful writes.
- Rumor delivery consults receiver history, includes recent resolved events, skips known stories without starving other news, and refuses future events. Same-day resolutions and explicit corrections can travel once; stale hearsay cannot overwrite firsthand or terminal knowledge. Day-only records do not invent an order for same-day reversible state/payload changes. Anonymous retellings remove entity-reference payload slots, and retelling preserves authored distortion until an explicit correction. Malformed repeated gossip subjects no longer crash deduplication.
- Capitals availability is tracked per capability. Missing diplomacy/heir/allegiance access is unknown, not peace/no heir/no allegiance. Named sovereign/consort/house-word guards distinguish known people and text from vacancies.
- Court polling no longer overwrites resolved titles with inferred ranks. Naming an heir does not announce a birth; abdication does not necessarily announce a coronation. Title-change timing uses a consistent game-time clock.
- Forge resource expansion writes UTF-8, including the Java-properties-safe escaped description. As of this release, `compileJava` compiles the optional MCA: Quests / MCA: Reputation integrations against compile-only API jars vendored under `libs/api/` and hash-pinned by `gradle/sibling-apis.properties` (version, provider commit, SHA-256 per provider), checked by the `verifySiblingApis` task before every compile. `-PmcaQuestsApiPath`/`-PmcaReputationApiPath` still override the vendored jar with a local jar or class directory for iterating against a provider working tree, skipping the hash check for that provider. These remain compile-only; nothing from either provider is packaged.

## This release's finding disposition

This document covers the pass that produced `main` at `db723ac`/1.6.0, continuing through
`df0b5c8`/1.6.1. This release is a second,
later pass that fixes the correctness findings of a subsequent audit
(`docs/MCAConversations-Conversation-Systems-Research-and-Implementation-Plan.md`, §7). This table
maps each finding to the slice commit that addressed it and is grounded in the eight slice commit
messages for this release and the current `CHANGELOG.md` entry.

| Finding | Status | Slice | Main classes | Covering tests |
| --- | --- | --- | --- | --- |
| F01 — clean CI cannot compile optional integrations | Fixed | slice 1 `d1bbbc4` | `build.gradle` (`verifySiblingApis`, `siblingApi`), `gradle/sibling-apis.properties`, `libs/api/*.jar` | `SiblingApiManifestTest` |
| F02 — nine answers gated on unrecognized `!child` | Fixed | slice 2 `3999742` | `FeatureId`, `conversation/AgeGroup`, `conversation/TopicAgeGate`, `mixin/QuestionMixin`, `mixin/InteractionDialogueMessageMixin`, `chat/GatePreview`, `hub/DynamicHub`, the three `conversations.cat.*.json` category files | `NativeConstraintTokens`/`ConstraintVocabularyProbeTest`, `ContentLintTest` |
| F03 — unknown feature id treated as enabled | Fixed | slice 2 `3999742` | `FeatureId`, `McaConversationsConfig`, `compat/mca/ConversationsMcaRegistrar` | `FeatureOffLintTest` |
| F04 — named chat executes outside its radius | Fixed | slice 3 `e078b50` | `chat/VillagerFinder`, `chat/ChatDelivery`, `chat/ChatModeDispatcher`, `conversation/EngagementPolicy`, `conversation/ChoiceSelectionService` | `VillagerFinderTest`, `EngagementPolicyTest` |
| F05 — chat work queued before cancellation is known | Fixed | slice 3 `e078b50` | `chat/AcceptedChat`, `event/ConversationsEvents` | `AcceptedChatSnapshotTest` |
| F06 — Capitals caches can outlive their world | Fixed | slice 4 `6018815` | `compat/ServerEpoch`, `compat/CacheLifetime`, `compat/capitals/ReflectiveCapitalsBridge`, `compat/CapitalsBridge`, `event/ConversationsEvents` | `CapitalsCacheLifetimeTest` |
| F07 — newer-schema history is not preserved losslessly | Fixed | slice 5 `9499e5a` | `history/ConversationHistoryStore`, `history/ConversationHistorySavedData` | `FutureSchemaTest` |
| F08 — history decoding bypasses declared collection limits | Fixed | slice 5 `9499e5a` | `history/HistoryCaps`, `history/VillagerHistory`, `history/PairHistory`, `history/ConversationHistoryBackupSavedData` | `ConversationHistoryStoreTest` |
| F09 — eviction is insertion-order, not activity-order | Fixed | slice 5 `9499e5a` | `history/ConversationHistoryStore#setLiveSessionPredicate`, `history/VillagerHistory#isProtected`, `conversation/ConversationSessions#hasSessionWith` | `EvictionOrderTest` |
| F10 — some source prose violates identity/grammar assumptions | **Deferred to 1.7.x** (WP12, editorial repair) | — | — | — |
| F11 — selection caps can suppress continuity before scoring | **Deferred to 1.7.x** (WP09, selection) | — | — | — |
| F12 — reload/override semantics need one coherent generation | Fixed, critical portion only | slice 6 `908c66a` | `conversation/ContentGeneration`, `conversation/ContentGenerationListener`, `conversation/ConversationCatalogLoader`, `conversation/ConversationGuard`, `conversation/ConversationSession`, `conversation/ChoiceSelectionService` | `ReloadResilienceTest`, `ContentGenerationTest`, `GuiOfferLifetimeTest` |
| F13 — fingerprint/slot-rendering edge cases | Fixed | slice 7 `1240c4b` | `context/ContextValue`, `history/NarrativeValue`, `scene/SlotBinder`, `scene/SlotType`, `template/SlotRenderer`, `template/ConversationsSay` | `ContextFingerprintTest`, `SlotBinderTest`, `SlotRendererTest` |
| F14 — CI and docs overstate or omit current behavior | Fixed | slice 1 `d1bbbc4` (CI runs the drift gates by name) plus this documentation pass | `README.md`, `MODMAP.md`, `DATAPACK.md`, `CONFIG.md` | — |

WP08's critical portion (every conversation close names its reason and tears down through one path)
landed in slice 8 `8120ca4`: `conversation/CloseReason` (new), `conversation/ConversationSessions`,
`chat/ChatModeScheduler`, `chat/ChatModeSession`, `chat/ChatDelivery`, `chat/VillagerAttention`,
`scene/ConversationPlanner`; covered by `SessionCloseReasonTest`, `ChatModeSchedulerTest` and
`ChatDeliveryTest`. The rest of WP08 (utterance ids, transcript, per-recipient disclosure
bookkeeping, durable checkpoints, resume after restart, the automatic-node budget) and WP09–WP15 are
deferred to 1.7.x; WP16 (release closure) is `docs/RELEASE-1.6.3-LEDGER.md` itself, with only its
production checks outstanding. See `docs/RELEASE-1.6.3-LEDGER.md` (this release's ledger) for the full deferred list and the production
checks this fix set still needs.

## Content and evidence

The source editorial pass covers ordinary topic scenes and their player replies, multi-turn follow-ups, and Capitals scenes. Both locales are authored together. Portuguese chat normalization uses the client language to distinguish the preposition `no` from the negation `não`; exact offered sentences preserve the same choice as GUI input. Generated fixtures exercise both languages. Additional substantive voice pools and group replies were edited, including the existing village overlay so it cannot undo the corrected base story. State-sensitive lines use executable context gates; hypothetical taxes and building proposals do not claim a real tax rate, levy or construction project. See [Capitals capability evidence](MCA_CAPITALS_SUPPORT.md) for artifact versions. `build/reports/conversations/narrative-inventory.json` enumerates every data file, dialogue result, source scene, nested reply, locale and voice key; its Markdown companion describes coverage. The adjacency and `narrative-claim-review.tsv` reports support further semantic review. The pass does not certify every legacy/profession sentence as independently human-reviewed.

Structural validation enumerates runtime graphs, contracts, translations, placeholders, intents, conditions and callbacks. Those checks can prove structural properties and detect contradictions represented in data. They do not prove that every possible generated conversation sounds natural; the bilingual adjacency and trace reports are retained for editorial review.

## Reproduction

Use the checked-in wrapper and the correct Java toolchain: Forge uses Java 17, NeoForge uses Java 21. Run `build verifyGeneratedConversationContent verifyVoiceOverlays`. Generator changes require `generateConversationContent generateVoiceOverlays` first. Do not run the generators and validation concurrently in one checkout.

As of this release, a default build needs no sibling checkout at all: `compileJava` resolves the optional MCA: Quests / MCA: Reputation integrations from the compile-only API jars vendored under `libs/api/` and pinned by `gradle/sibling-apis.properties`, and `verifySiblingApis` fails the build the moment a vendored jar's SHA-256 stops matching that manifest. To iterate against a provider working tree instead, append `-PmcaQuestsApiPath=<classes-directory-or-compatible-api-jar>` and/or `-PmcaReputationApiPath=<classes-directory-or-compatible-api-jar>`; an override is announced in the build log and skips the hash check for that provider only. To refresh the vendored jar for a new provider release: build the provider (`./gradlew build` in that repo), copy `build/libs/<modid>-<version>-api.jar` into `libs/api/`, and update that provider's `version`, `commit` and `sha256` keys in `gradle/sibling-apis.properties` (see the manifest's own header comment). The artifact is never bundled into Conversations.

Real integration probes use `capitalsProbeTest -PcapitalsJar=<matching-jar>` and `townsteadProbeTest -PtownsteadLegacyJar=<matching-jar>` (or `townsteadModernJar`). Supplying no jar skips the corresponding real-artifact tests; it is not evidence that the integration works.

Townstead 0.7.6 artifact evidence: [Forge/NeoForge file list](https://www.curseforge.com/minecraft/mc-mods/townstead/files/all) and [NeoForge file 8611587](https://www.curseforge.com/minecraft/mc-mods/townstead/files/8611587). Dependencies and binary probes are verified locally; no mod is bundled or published by this pass.

## Completed validation

Final commands passed on both loaders: `build verifyGeneratedConversationContent verifyVoiceOverlays`. Both content generators also passed independently and produced identical shared output across 1,302 files. NeoForge's `verifyJarContents` passed with 1,000 dialogue files, 46 locale files, and no shaded dependencies. Both Git whitespace checks passed, and both jar ZIPs passed integrity checks.

| Check | Forge 1.20.1 | NeoForge 1.21.1 |
| --- | ---: | ---: |
| Main test cases | 1,140 | 1,181 |
| Failures/errors | 0 | 0 |
| Optional-artifact cases skipped in main suite | 6 | 6 |
| Generated-content verification cases | 1 passed | 1 passed |
| Voice verification cases | 5 passed | 5 passed |
| Dedicated Capitals probe cases | 2 passed | 2 passed |
| Dedicated Townstead probe cases | 6 passed | 6 passed |

The skipped main-suite cases require explicit jar paths; all six ran successfully in the dedicated real-artifact probe tasks. MCA reflection probes covered Forge 7.6.20, 7.7.0-beta.2 and 7.7.1-alpha.2, and NeoForge 7.7.33 and 7.7.36-beta.3. The NeoForge test launcher uses Java 21; Forge compilation/runtime tests use Java 17. Unit-test bootstraps and reflection probes do not replace the production checks below.

Release review artifacts are beside each jar in `build/libs/reports/`, including `narrative-inventory.md`, `narrative-inventory.json`, bilingual adjacency reports, and the heuristic claim-review queue. The inventory contains 1,000 questions, 3,327 answers and 4,562 result rows. Generated content includes 373 scenes, 1,111 beats, 1,209 contracted replies and 445 pages; 55 scenes are Capitals-gated, including 13 added in this pass.

## Production verification still needed

- Run actual production clients and dedicated servers with Forge MCA 7.6/7.7 and NeoForge MCA 7.7. Check MCA's remapped mixins, joining/rejoining, death, two players answering the same villager, stale/duplicate packets, and GUI/chat transitions.
- Exercise all three dialogue styles, paging, keyboard and screen-reader interaction, long translated choices, slow reading, screen reopen and switching servers.
- In real Capitals worlds, appoint/replace heirs, change sovereigns, court roles and diplomatic relations, advance chronicles, rename people/houses, and test unloaded residents. Check callbacks in both languages and verify privacy and news recency. Repeat with Capitals disabled and absent.
- With Quests/Reputation/Townstead/Serene Seasons present individually and together, exercise real event order, gift acceptance, completed/failed promises, world save/reload and resource reload. Binary reflection probes do not execute full mod behavior.
- Listen through the bilingual adjacency reports in play, particularly high-frequency pools and voices, child/teen/adult separation, trust/privacy gates and context changes mid-exchange.
