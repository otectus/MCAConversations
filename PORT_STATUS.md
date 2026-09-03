# PORT_STATUS.md — 1.21.1 NeoForge parity port

Resume-from-here file. If you are a fresh session: read this, then `PARITY.md`, then the approved plan
(`C:\Users\crims\.claude\plans\task-port-mca-lexical-thacker.md` if it still exists). SOURCE
`C:\Projects\MCAConversations` @ `1ee78b7` (v1.5.1) is read-only truth. TARGET was cut from SOURCE
`89edad2` (v1.2.1); the port is a re-sync of five SOURCE releases plus the loader migration.

## Final state

**Build green: 991 tests, 0 failures. All slices landed (B0–B13). Jar:** `build/libs/mcaconversations-neoforge-<mod_version>+<minecraft_version>.jar` (versions come from `gradle.properties`). **Reports:** `build/libs/reports/` contains adjacency, adjacency.pt_br, coverage, identity-coverage, memory-schema, scenes, threads, uncontracted-routes.

F4 landed: the three hand-registered `Test` tasks (`townsteadProbeTest`, `verifyGeneratedConversationContent`, `verifyVoiceOverlays`) now inherit the ModDevGradle `test` task's fork options (working dir `build/minecraft-junit`, JVM args, arg-file providers, system properties) via `ext.inheritUnitTestWiring` and depend on the plugin's prepare tasks, because NeoForge's JUnit launcher on the test classpath needs `mainargs.txt`. Results: `townsteadProbeTest` = TownsteadBindingProbeTest 5 + TownsteadUiMixinProbeTest 1, all pass with both the 1.21.1 and 1.20.1 jars supplied (`Townstead bound; MCA root = net.conczin.mca`); `verifyGeneratedConversationContent` (ContentCompilerTest 1) and `verifyVoiceOverlays` (VoiceFamilyCompilerTest 5) pass.

**To resume** (next session): read this file's history section, `PARITY.md`, and the two `docs/` files; build with `./gradlew build`; run the Townstead probe with `-PtownsteadModernJar=<path to townstead-0.7.6+1.21.1.jar>`; then walk `docs/ACCEPTANCE-1.21.1.md` in-game before tagging a release. No open code items remain.

### History of completed runs

**Slice 1 — reflective MCA binding**. B1a landed (McaBinding/McaHandles, MCA → `runtimeOnly`,
`testRuntimeClasspath` excludes MCA, `McaBindingProbeTest` with a port-only classpath guard,
`NoMcaStaticLinkTest`). B1b landed (McaCompat/McaBridge to HEAD, ConversationsMcaRegistrar decoupled
onto `McaHandles.registerCondition/registerAction`, 6 mixins → `@Pseudo` string targets,
`McaNetworkMixin` → `NetworkHandlerMixin`, zero `net.conczin.mca` imports under src/main). Main session
fixed `McaHandles.sendDialogueLine` to construct the 1.21.1 record in `(Component, boolean)` order
(Deviation 18). **G1 gate PASSED** (clean, compileJava, test, townsteadProbeTest smoke, build →
`mcaconversations-neoforge-1.5.1+1.21.1.jar`) after fix-up F1 (Deviation 19) and three test fixes
(probe isolation compares paths not URL strings; `NoMcaStaticLinkTest` resolves `build/classes` via
`support.TestPaths`; `ReputationIntegrationTest` scans for `McaHandles.registerCondition/registerAction`).
`mca_version_range` widened to `[7.7.13,8)` (Deviation 11); probed on 7.7.33 and 7.7.36-beta.3.
Pending human check: `runClient` #1 (binding root resolved, 0 unbound members) — folded into the final
smoke checklist rather than blocking the run order.

**Slice 2 — config, three specs**: B2 landed (`McaConversationsConfig` = SOURCE HEAD with the
`ModConfigSpec` rename only; COMMON/SERVER/CLIENT registered in SOURCE order; `season/CalendarSource`
added; 17 caller sites in `chat/ChatModeDispatcher`, `chat/GreetOnApproach`, `chat/VillagerAttention`,
`conversation/ConversationSessions`, `disposition/Dispositions`, `event/ConversationsEvents` moved to
the server accessors `McaConversationsConfig.<field>()` — their owning runs will find that hunk already
applied). Deferred → B9: `McaConversations.java` SOURCE `:65-70` `TownsteadCompat::init` enqueueWork.
**G2 gate PASSED** (`build`, jar produced) after the main session fixed five more moved-field callers
that used a local `cfg`/`config` variable (`chat/ChatDelivery.java:66`, `progress/Affection.java:54-57`);
a grep for `.<serverField>.get()` across src confirms no others remain.

**Slice 3 — data layer**: B3a landed (identity 10, village 10, profession 4, `locale/LineVoice`,
`personality/VoiceFamily`, 3 tests; McaCompat's `LineVoice.pinned` hunk restored; `compileJava` PASS).
Loader directories: `identity_tokens`, `village_culture`, `profession_profiles`. SOURCE registers these
loaders in `event/ConversationsEvents.java:350,354,356` → B5e. B3b landed (history/ files 1–18 incl.
`ConversationHistorySavedData` "mcaconversations_history"; only SavedData + `BuiltInRegistries.ITEM`
renames differ). `history/HistoryQuery` imports `conversation.OutcomeFamily` and `conversation.SceneShape`,
so B3c pulls those two leaf types forward from B5c1. B3c landed (history/ 19–35, 9 history tests,
`conversation/OutcomeFamily` + `SceneShape` pulled forward from B5c1 — B5c1 must skip them,
port-only `SavedDataContractTest` extended to 6 names). **G3c gate PASSED** (`compileJava`,
`compileTestJava`; six SavedData names present). B3d landed (context/ 18 + 2 tests, all
verbatim; `conversation/RelationshipBand` + `Relationships` pulled forward — B5c1 must skip them; no
context hooks exist in SOURCE's ConversationsEvents). One compile-breaker remains:
`context/HistoryContextSource.java:137` needs `GossipConditionLogic.nextUntoldEventType`, which B5a
owns, so B5a ran next. B5a landed (`template/SlotRenderer` new; `SayDirective`, `TemplateEngine`,
`GossipConditionLogic` (+`nextUntoldEventType`), `GiftTracker` (+`CommitmentObserver.onGiftAccepted`),
`Affection` patched; state/check/season/disposition already at HEAD). Risk 11 closed: SOURCE main has no
`ItemStack` NBT access anywhere. Deferred → B5c2 (after B5b): SOURCE `template/ConversationsSay.java:14,18-44`
(`resolveSlots` + 3-arg `buildArgs`; needs `scene.ConversationPlan` and `ConversationSession.plan()`).
G3d/G5a `build` gate: compile green, 7 tests failed on missing data files and repository-relative
paths (`ContextKeyOwnershipTest`, `VillagerIdentityGeneratorTest`, `VillageCultureTest`, and the port
lint `NeoForgePortLintTest.testsResolveRepositoryPathsThroughTestPaths`). Fix-up F2 landed (16 files) and the gate then PASSED (692 tests) after one more `VillageCultureTest`
path went through `TestPaths`: pulled
`data/mcaconversations/{identity_tokens,village_culture,conversation_scenes,episode_templates,
thread_templates,commitment_templates,profession_profiles}` and `assets/mcaconversations/lang/{en_us,pt_br}.json`
forward from slice 11 as byte-identical copies, and routes the new tests' repository paths through
`support.TestPaths` (the one permitted difference from SOURCE test files). Standing rule for every
later run that ports tests: repository paths go through `TestPaths.of(...)`, never `Path.of("src/...")`. SOURCE hooks for B5e:
`ConversationsEvents.java:166,207-216` `History.forget`, `:262,305-310` `History.prune`, `:324`
`RumourPropagation.sweep`, `:357` `NarrativeCatalogLoader.listeners()`.

Deferred hunks recorded by B1b (apply in the owning run):
- → B4: SOURCE `mixin/NetworkHandlerMixin.java:63-80` frontend-aware offer branch (`ChoiceOffer`,
  `ChoiceOfferS2C`, `ChoiceClearS2C`, `sendOffer/clearOffer/warnOversizedOffer`, `ChatModeSession.activeVillagerId`);
  TARGET keeps `recordOffer(...)` + `swallowDialogue(...)` until then.
- → B5a (locale): SOURCE `compat/McaCompat.java:4,197-200` `LineVoice.pinned(...)` (marked `// DEFERRED` in place).
- → B6: SOURCE `compat/mca/ConversationsMcaRegistrar.java:144-148` (`LivingHistoriesRegistrar.register()`),
  `:255-273` (`conversations_relationship` condition, needs `conversation/RelationshipQuery`+`Relationships`),
  `:627-690` (`SessionDirective.Op.TURN`/beat/`ConversationPlanner`); SOURCE
  `mixin/InteractionDialogueMessageMixin.java:88-90` (`scene.ConversationPlanner.onAnswerSubmitted`);
  `compat/McaBridge.java` LivingHistoriesRegistrar wiring.
- → B9: `TownsteadBindingProbeTest` / `TownsteadUiMixinProbeTest` must build their per-jar
  `URLClassLoader` with parent `new McaHidingClassLoader(testClassLoader)` (test-support class added by
  F1 in `src/test/java/.../compat/`), and must keep the MCA probe jar in their own `URL[]` because
  Townstead's API signatures reference MCA types.
- → B13: `McaBindingProbeTest.resolutionWithoutMcaIsAbsentAndDoesNotThrow` javadoc still says the rest
  of the suite runs without MCA; no longer true (prose only).
- Notes: `BreedableRelationshipMixin` uses `require = 0` (SOURCE and `defaultRequire: 0`; TARGET had 1) and
  keeps `stack.copyWithCount(1)` at the INVOKE-before-split point (Deviation 1). `MixinsJsonLintTest`
  now asserts `NetworkHandlerMixin` (the evidence doc heading was corrected in B13).
Facts from B1a: `find_api.py` indexes NeoForge classes (Risk 4 closed); `rewardHearts` and
`shouldGrieve` are on `VillagerBrain`, `getTranslatable` is a `Messenger` default visible via
`getMethods()`, `Mood.getName` and `Residency.getHomeVillage` exist as SOURCE binds them. Slice 0 gate passed (`BUILD SUCCESSFUL`, jar
`mcaconversations-neoforge-1.5.1+1.21.1.jar`).

Execution mode: each run in the approved plan's "Builder handoff briefs" (22 runs B1a…B13) is dispatched
to a `builder` agent with a self-contained brief; a `verifier` runs the gate; this file and `PARITY.md`
are updated by the main session after each gate. Run order is by compile dependency: B1a, B1b, B2, B3a,
B3b, B3c, B3d, B5a, B5b, B5c1, B5c2, B4, B5d, B7, B5e, then B6 ∥ B8, then B9 ∥ B11, B12a, B12b, B13.

Verified facts feeding the briefs (javap against the real 1.21.1 jars): SOURCE's 85-member MCA manifest
resolves 83/88 rows identically under `net.conczin.mca.`; only `cobalt.network.NetworkHandler` →
`network.Network` and `getQuestionText()` → `questionText()` change, `Traits$Trait#id` is an expected
optional miss, five rows need their declaring class confirmed in B1a. Townstead 0.7.6 NeoForge: every
`TownsteadBinding` member (112 `static final Member` declarations) and both client mixin targets resolve; only `TownsteadUiMixinProbeTest:45`
must accept `keyPressed` instead of `m_7933_`.

## Just finished

- Phase 0 audit: three scouts inventoried SOURCE, TARGET, and the resource/test trees; results are
  folded into `PARITY.md`.
- Dependencies verified for 1.21.1 NeoForge: MCA `net.conczin.mca:mca-neoforge` `7.7.33+1.21.1`
  (latest stable) and `7.7.36-beta.3+1.21.1` (latest); MCA: Quests 1.1.0 and MCA: Reputation 0.2.0
  as built siblings under `C:\Projects\1.21.1 Ports`; Townstead 0.7.6 NeoForge 1.21.1 downloaded from
  CurseForge (file id 8611587) to the session scratchpad,
  `townstead/townstead-0.7.6+1.21.1.jar`, 5,301,544 bytes, SHA-256
  `d8fe667d340e54dc7fd46bca762f8b5a0964a2bcce8a640dce3cd0370ebdbffe`. The 1.20.1 jar for the legacy
  probe is at `C:\Users\crims\curseforge\minecraft\Instances\RealCraft\mods\townstead-0.7.6+1.20.1.jar`.
- `gradle.properties`: `mod_version=1.5.1`; `mca_probe_versions=7.7.33+1.21.1,7.7.36-beta.3+1.21.1`.
- `neoforge.mods.toml`: optional `mcaquests [0.8,)` and `townstead [0.7.5,0.8)` added next to the
  optional `mcareputation [0.2,)`.
- `build.gradle`: `mcaProbe${i}` configurations, `test.doFirst` sets `mcaconversations.probe.jars`,
  `townsteadProbeTest` task (not wired into build; `failOnNoMatchingTests=false` until slice 9).

## Next

1. Slice 0 gate: `gradlew-quiet.ps1 -Task build`. Confirm `7.7.33+1.21.1` resolves from the Conczin Maven.
2. Slice 1 — reflective MCA binding (`McaBinding`/`McaHandles`, string-target mixins, MCA → `runtimeOnly`,
   `configurations.testRuntimeClasspath { exclude group: 'net.conczin.mca' }`, `McaBindingProbeTest`,
   `NoMcaStaticLinkTest`), then one `runClient`, then widen `mca_version_range`.
3. Slices 2–13 per the plan: config (3 specs) → data layer → networking → core engine → registrars →
   commands → client UI → Townstead → Quests/Reputation/Seasons → resources + authoring pipeline →
   tests → docs + smoke checklist.

## Known blockers

- None blocking. Watch items: ModDevGradle `unitTest` may keep MCA on the test classpath after the
  `runtimeOnly` switch (Risk 1); the hand-registered `townsteadProbeTest` may need the MDG JVM wiring
  that `test` gets; `sourceSets.main.java.exclude(...)` fallback blocks in `build.gradle:109-127` drop
  compat sources when sibling projects are unbuilt — new compat classes must join those lists.

## Deliberate reorderings vs the plan

- The 10 stale TARGET-only dialogue files are deleted in slice 11 (resource sync), not slice 0: the
  `verifyJarContents` dialogue-count floor (173) would trip before the sync raises it.
- `configurations.testRuntimeClasspath { exclude group: 'net.conczin.mca' }` lands in slice 1 with the
  `runtimeOnly` switch, not slice 0, because current TARGET tests still link MCA types.
- Networking (slice 4) runs after the conversation runs (B5c1/B5c2): `ChoiceSelectC2S` ↔
  `ChoiceSelectionService` ↔ `ConversationSession.Frontend` are mutually dependent.
- The `event/ConversationsEvents.java` patch moves from slice 3 to run B5e: its 8 reload listeners
  reference loaders in `scene/`, `hub/`, `conversation/`. Commands (slice 7) run before B5e for the same
  reason. Slice 10 folds into B6 (compat/quests, /reputation, /seasons unchanged since 89edad2).

## Deviations from SOURCE (accumulating; each needs an acceptance note)

| # | Deviation | Why / migration |
|---|---|---|
| 1 | `BreedableRelationshipMixin` injects BEFORE the sole `ItemStack.split(I)` inside `acceptGift`, not at HEAD | MCA 1.21.1 does the reject/inventory-full checks inside `acceptGift`; this position records exactly the accepted gifts. Evidence: `docs/PORT-1.21.1-EVIDENCE.md`. |
| 2 | `client.VillagerMessageMixin` deleted | 1.21.1 `VillagerMessage(Component prefix, Component message, UUID)` is a record; SOURCE's mixin only worked around a JSON re-parse / repeated-random-draw bug that no longer exists. Acceptance: smoke item 8. |
| 3 | `NetworkHandlerMixin` targets `net.conczin.mca.network.Network.sendToPlayer(HandleablePayload, ServerPlayer)`; `InteractionDialogueMessageMixin` targets `handleServer(ServerPlayer)` | MCA removed `cobalt.network.NetworkHandler` and `receive`. |
| 4 | Single mixin/binding package root `net.conczin.mca` | 1.21.1 MCA publishes per-loader jars; no `forge.` relocation. |
| 5 | SimpleChannel `mcaconversations:main` ids 0–3 → payload types `typing_status`, `choice_offer`, `choice_clear`, `choice_select`; registrar version `"2"` | NeoForge payload API; per-payload byte layout preserved. |
| 6 | Forge capabilities → NeoForge attachments (same ids) + `PlayerLegacyDataMixin` migration | Already in TARGET with fixtures. |
| 7 | `ForgeConfigSpec` → `ModConfigSpec`, three specs registered via `ModContainer` | Keys identical. |
| 8 | `mod_version=1.5.1` (user decision; reverses the earlier 2.0.0 line). Jar `mcaconversations-neoforge-1.5.1+1.21.1.jar` | `gradle.properties` only. |
| 9 | `mcaconversations.mixins.json`: `compatibilityLevel JAVA_21`, no refmap | mojmap runtime. |
| 10 | `pack.mcmeta` pack_format 34 + supported_formats | 1.21.1. |
| 11 | MCA range `[7.6,8)` → `[7.7.13,8)` (pending slice-1 probe) | first published 1.21.1 MCA is 7.7.13. |
| 12 | `InteractScreenChoiceMixin`: no SRG `m_*` aliases; `mouseScrolled` has 4 params | mojmap; 1.20.2+ signature. |
| 13 | `DistExecutor` → `FMLEnvironment.dist` guards with lambdas | NeoForge removed DistExecutor. |
| 14 | `TickEvent.*` → `*TickEvent.Post` | already in TARGET. |
| 15 | ForgeGradle/MixinGradle → ModDevGradle; probe configurations without `fg.deobf` | build system. |
| 16 | **Withdrawn.** `Personality` keeps SOURCE's `toString()` path only; no `getPersonalityId()` member is added | 1:1 rule: manifest members are added only when a SOURCE member cannot bind. |
| 17 | `confident`/`peppy`/`athletic` personalities are legacy-only on 1.21.1 MCA; their lang overlays still ship | see `docs/PORT-1.21.1-EVIDENCE.md` roster. |
| 19 | MCA stays on the test runtime classpath (no `testRuntimeClasspath` exclude); `McaBindingProbeTest` isolates each probe jar behind a parent classloader that hides `net.conczin.mca.*` | ModDevGradle's `unitTest` boots FML, which enforces the mandatory `mca` dependency; SOURCE's ForgeGradle tests never booted FML. `mca` remains mandatory in `neoforge.mods.toml` as in SOURCE. Closes Risk 1. |
| 21 | Client UI on 1.21.1 GUI sprites: `DialogueCardSkin` draws the button nine-slice with `GuiGraphics.blitSprite("widget/button" / "widget/button_highlighted" / "widget/button_disabled")` instead of blitting `AbstractWidget.WIDGETS_LOCATION` (removed in 1.21); `DIRT = Screen.MENU_BACKGROUND`; `DialogueChoiceRenderer` calls the rect-based `InventoryScreen.renderEntityInInventoryFollowsAngle(x1,y1,x2,y2,scale,0.0625F,…)`; `DialogueCardPreviewScreen` drops its manual `renderBackground` (Screen.render already calls the 4-arg form) and takes 4-double `mouseScrolled`; `InteractScreenChoiceMixin` passes `Component` (not `MutableComponent`) for `setLastPhrase` and 4-double `mouseScrolled`. Method names, geometry constants, `BUTTON_V_*` offsets and `patches()` preserved. | Vanilla client API changes between 1.20.1 and 1.21.1; behaviour and layout unchanged. Acceptance: smoke item 2. |
| 20 | Loader-descriptor tests adapted: `content/OptionalProfessionIsolationTest` reads `META-INF/neoforge.mods.toml`, matches `type="required"` instead of `mandatory=true`, and expects `neoforge` where SOURCE expects `forge`; `content/MixinsJsonLintTest` asserts `NetworkHandlerMixin` and no `VillagerMessageMixin`; `compat/ReputationIntegrationTest` scans `McaHandles.registerCondition/registerAction` and does not reject the `[0.2,)` range; `MixinTargetProbeTest` omits `VillagerMessageMixin` and expects `handleServer` (not `receive`) for `InteractionDialogueMessageMixin`; `content/ContentLintTest` validates `conversations_personality` values against `Personalities.overlayPrefixes()` (canonical + aliases + retained legacy `athletic`/`confident`/`peppy`) because 1.21.1 MCA's `CANONICAL` roster lacks `confident`/`peppy` (Deviation 17) while SOURCE content still names them | The assertions' intent is unchanged; only the loader's file name, TOML grammar, mixin roster, and MCA personality roster differ. |
| 18 | `McaHandles.sendDialogueLine` constructs `InteractionDialogueQuestionResponse` as `(Component, boolean)`; SOURCE passes `(boolean, Component)` | 1.21.1 MCA turned the packet into a record with that component order; the arity-bound handle cannot express it, so the call site carries the swap. |

## Verification recipe (run at each gate; full detail in the plan)

- Build: `C:\Projects\.mcmod-tools\gradlew-quiet.ps1 -Project "C:\Projects\1.21.1 Ports\MCAConversations_1.21.1" -Task build`
- Identity diffs vs SOURCE: condition/action ids (`register(Condition|Action)\("…"`), SavedData names
  (`"mcaconversations_*"`), attachment/payload ids, ordered config `push/define*` tokens, lang keys per
  file (python set diff), resource tree SHA-256 map excluding `META-INF/*`, `pack.mcmeta`,
  `mcaconversations.mixins.json`; mixins.json delta must be exactly `+PlayerLegacyDataMixin`,
  `-VillagerMessageMixin`.
- Raw lang keys: `rg -oN '"(gui\.|chat\.)?mcaconversations\.[a-z0-9_.]+"' src/main/java` checked against
  `en_us.json`; content lint tests run in `build`.
