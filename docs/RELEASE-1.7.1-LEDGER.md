# Release ledger — 1.7.1 (Forge 1.20.1 and NeoForge 1.21.1)

Acceptance ledger for the conversation-stability half of the September 2026 conversation stability and
social behavior specification
(`docs/MCAConversations_Conversation_Stability_and_Social_Behavior_Implementation.md`), landed in both
repositories. **No in-game run was performed for this release.** The release
verification in "Release checks" below was run at slice 6 HEAD on both loaders on 2026-09-16;
everything else here is a static read of the commits and their source. Nothing in this ledger
substitutes for the runtime acceptance listed as outstanding.

The release is seven slices. Slices 1–6 are the implementation and are committed in both
repositories; **slice 7 is this documentation and release pass** — these three documents, the
regenerated adaptation manifest and the release checks below — and its commit is the one that introduces
this ledger (`git log --oneline -- docs/RELEASE-1.7.1-LEDGER.md`). The slice table therefore has six rows, not seven.

Every per-slice row records that the builder ran `compileJava` and `test` in each repository. That is
**as reported by the implementing agent; no log was retained.** The JUnit results quoted under "Release
checks" are from the last of those runs, at slice 6, which the release build then reused.

"Delivered" means the defect the slice describes is corrected in source and covered by unit tests that
passed in the builder's slice 6 test runs, which the release build then reused unchanged (see "Release
checks"). It does not mean the corresponding client-launch check was performed — none were.

## Baseline

Forge `main` was at `ef92593` and the NeoForge port's `neoforge/1.21.1` at `958a0bb`, both at
`mod_version=1.7.0` and `network_protocol=3`. Both now read 1.7.1 and protocol 4; the values live only
in each `gradle.properties`. A 1.7.1 client and a 1.7.0 server do not pair, by design (see §
"User decisions" below).

Each slice landed as one Forge commit and one port commit, in that order, before the next slice
began. Commit hashes are from `git log --oneline` in each repository.

## Slices

| Slice | Forge | NeoForge port | Delivers |
| --- | --- | --- | --- |
| 1 | `3f86a6d` | `005c317` | A closed screen's out-of-range lapse no longer captures the next villager |
| 2 | `ad63c38` | `c5c073d` | One handle per accepted discussion; idempotent teardown |
| 3 | `9f40eac` | `da7fc65` | Choice channel protocol 4; every lifecycle packet carries the discussion's identity |
| 4 | `5d87661` | `ca65dd2` | 16-block continued distance with grace, and a presence lease for GUI discussions |
| 5 | `687e86e` | `2eb0abe` | Villagers stand still and face the player; an attack ends the discussion for good |
| 6 | `0ad7df0` | `c52d4d5` | Another player's accepted interaction takes the villager over cleanly |

### Slice 1 — stale-lapse fix
**Delivered.** GUI choice offers and the explanations they leave behind now name the owning villager;
the interaction screen clears both unconditionally on close and reconciles a screen that is replaced
rather than closed; a lapse is shown only for the villager whose screen is open.
Classes: `client/dialogue/ClientChoiceState`, `ClientChoiceMessages`,
`mixin/client/InteractScreenChoiceMixin`, `client/dialogue/dev/DialogueCardPreviewScreen`.
Tests added: `ClientChoiceLapseCarryoverTest` (the regression the spec mandates), with
`ClientChoiceLapseTest`, `ClientChoiceStateTest`, `DialogueChoicePresenterTest`,
`DialogueChoiceVisualStateTest` and `DialogueStyleHotSwitchTest` extended.
Per-slice checks: `compileJava` and `test` in each repository (see the preamble).

### Slice 2 — handle identity and idempotent teardown
**Delivered.** Adds `ConversationHandle`, `ConversationPresence` and the `ConversationLifecycle`
coordinator. Closing a session releases villager attention only when the closer owns the hold,
teardown stages are individually guarded so the indexes are always released, and `CloseReason` carries
explicit wire ids with six new named endings.
Classes: `conversation/ConversationHandle`, `ConversationLifecycle`, `ConversationPresence`,
`ConversationSession`, `ConversationSessions`, `CloseReason`, `chat/AttentionLedger`,
`chat/VillagerAttention`, `chat/ChatModeSession`, `chat/ChatModeDispatcher`, `event/ConversationsEvents`.
Tests added: `ConversationHandleIsolationTest`; `AttentionLedgerTest`, `ConversationSessionTest` and
`SessionCloseReasonTest` extended.
Per-slice checks: `compileJava` and `test` in each repository (see the preamble).

### Slice 3 — protocol 4 and wire identity
**Delivered.** Offers, clears, selections and returns carry a `ConversationRef` (session and
villager); four new packets open, close, heartbeat and client-close a discussion by handle; the server
begins a managed handle at the authoritative MCA interaction and refuses obsolete packets without
disturbing the live card. `McaInteractionCloseMixin` stops another player's tokenless MCA close from
ending a managed discussion. `network_protocol` moved to 4 in both repositories.
Classes: the eight `network/` packets plus `ConversationRef`, `ConversationLifecycleBridge`,
`ConversationsNetwork`, `NetworkProtocol`, `ChoicePacketSink`; `conversation/HandleAuthority`,
`InteractionBoundary`, `ChoiceOutcome`, `ChoiceSelectionService`; `mixin/McaInteractionCloseMixin`,
`mixin/NetworkHandlerMixin`, `mixin/client/InteractScreenChoiceMixin`; `chat/ChatModeScheduler`;
`client/ConversationPresenceTracker`, `client/dialogue/ClientChoiceController`,
`client/dialogue/ClientChoiceMessages`.
Tests added: `ChoiceSelectCodecTest`, `ConversationLifecycleCodecTest` and
`ConversationPacketOrderingTest` (the six ordering cases from the spec).
Extended: `ChoiceOfferCodecTest`, `ChoiceClearCodecTest`, `ChoiceReturnCodecTest`, the port-only
`Choice{Offer,Clear,Select}PayloadTest` byte-order pins, and `MixinTargetProbeTest` with the new
mixin's injection point.
Per-slice checks: `compileJava` and `test` in each repository (see the preamble).

### Slice 4 — presence lease and distance policy
**Delivered.** `ConversationDistancePolicy` (continue 16, immediate close 24, 20-tick grace, inclusive
boundaries) replaces the fixed 8-block GUI check. The server tick judges every live GUI handle from
the presence index: lease expiry, distance, disconnect, dimension change and unloaded villagers each
end the discussion through the lifecycle. Adds the eight `[conversation]` server settings, documented
in `CONFIG.md`.
Classes: `conversation/ConversationDistancePolicy`, `ChoiceSelectionService`, `ConversationLifecycle`,
`ConversationPresence`, `ConversationSessions`; `McaConversationsConfig`; `event/ConversationsEvents`;
`network/ConversationPresenceC2S`; `client/ConversationPresenceTracker`,
`client/dialogue/ClientChoiceMessages`, `mixin/client/InteractScreenChoiceMixin`.
Tests added: `ConversationDistancePolicyTest`, `ConversationPresenceLeaseTest`; `ConfigSpecTest` and
`ConversationPacketOrderingTest` extended.
Per-slice checks: `compileJava` and `test` in each repository (see the preamble).

### Slice 5 — stationary hold, facing and danger
**Delivered.** `ConversationMovementController` books and applies the stationary hold for every managed
GUI discussion — walk target erased, look target on the owner, navigation stopped; never invulnerability,
gravity or teleport-back — independent of chat mode. `InteractTaskMovementMixin` cancels MCA's
`InteractTask#followPlayer` while a hold owns the villager. Damage and blocked hits interrupt through
`ConversationDanger` regardless of `enableStates`, deduplicated per incident, with a per-villager
re-open lockout; hurt or panic revokes the handle instead of pausing it. Binds
`EntityCommandHandler#stopInteracting` as teardown stage 6, conditional on MCA's interacting player
still being the handle's owner.
Classes: `chat/ConversationMovementController`, `chat/VillagerAttention`, `chat/AttentionLedger`;
`conversation/ConversationDanger`, `DangerLockout`, `InteractionBoundary`, `ConversationLifecycle`;
`compat/NativeInteractionClose`, `compat/McaCompat`, `compat/mca/McaBinding`, `McaHandles`;
`mixin/InteractTaskMovementMixin`; `event/ConversationsEvents`.
Tests added: `ConversationMovementControllerTest`, `ConversationDangerTest`; `AttentionLedgerTest` and
`MixinTargetProbeTest` extended.
Per-slice checks: `compileJava` and `test` in each repository (see the preamble). No unit test can show that a Brain stopped
walking, however; that is a runtime check and it is outstanding.

### Slice 6 — multiplayer handoff
**Delivered.** `ConversationLifecycle.handoff` is a serialized transfer: the movement hold moves to the
successor before the predecessor's teardown, so the villager is never unheld, stays put and turns to
the new player. The first player's window closes with a dedicated "speaking with someone else" message
(`gui.mcaconversations.responses.taken_over` in `en_us` and `pt_br`), and every stale packet from the
first player is refused. Adds `OpenRateLimiter` (three accepted opens per pair per 20 ticks) and
refuses a takeover during a danger lockout.
Classes: `conversation/ConversationLifecycle`, `conversation/OpenRateLimiter`,
`chat/ConversationMovementController`, `client/dialogue/ClientChoiceMessages`, both language files.
Tests added: `ClientChoiceCloseMessageTest`; `ConversationMovementControllerTest`,
`ConversationDangerTest`, `ConversationHandleIsolationTest`, `ConversationPacketOrderingTest`,
`ConversationSessionTest` and `SessionCloseReasonTest` extended.
Per-slice checks: `compileJava` and `test` in each repository (see the preamble).

## User decisions taken for this release (2026-09-15)

1. **Release scoping:** the conversation-stability workstream ships as 1.7.1; the social-behavior
   workstream is deferred to 1.8.0. This ledger covers the stability half only.
2. **Attack behavior for guard and combat professions:** the default is `NATIVE_COMBAT`, with
   `RETREAT` as the config option — the inverse of the spec, whose §5.3 and §11.4 configuration table
   make `RETREAT` the default and `NATIVE_COMBAT` the alternative. `NATIVE_COMBAT` leaves MCA's own
   reaction alone, so a guard is still a guard.
3. **Protocol bump accepted:** protocol 4 means 1.7.1 clients and servers do not pair with 1.7.0, and
   both loaders' jars must publish together.

## Documented divergences from the spec text

Two behaviors ship deliberately differently from the spec's wording. Both were decided for this
release.

1. **An out-of-16 answer gets a grace band, not an immediate close.** The spec reads as an immediate
   close once the pair separates. The shipped policy is the 16/24/grace rule: sixteen blocks to
   continue, a 20-tick grace out to twenty-four in which the card stays readable and nothing may be
   *done*, and an immediate close past twenty-four. The reason is that a single step across a boundary
   should not destroy a conversation the player is reading; the refusal of *effects* out of range is
   unchanged and immediate.
2. **`ConversationCloseC2S` carries no client-asserted reason.** A client may say that its window has
   closed; it may not say why, because a reason from the client is a claim the server would otherwise
   have to trust and record. The server names the ending itself from what it can observe.

## Deferred to 1.8.0

The entire social-behavior workstream — spec §1.2, work packages WP7–WP9 — is deferred:

- **WP7:** real family and conflict evidence in `conversation/Relationships` (which still passes
  `false, false` to `RelationshipBand.of`), the outdated "family not bound" javadoc there, a new
  `SocialContextResolver` feeding the context keys, and additive optional contact metadata in
  `history/PairHistory` with **no** history store version bump.
- **WP8:** hard social eligibility ahead of selection, extending `scene/SceneEligibility`'s band
  filter, and social metadata through the authoring compiler.
- **WP9:** the corpus itself — edits under `src/content/{topics,professions,voices}` regenerated
  through `generateConversationContent` and `generateVoiceOverlays`, greeting frequency in
  `chat/GreetOnApproach`, and the negative-versus-nonnegative hail split in `chat/ChatModeDispatcher`.
- **Release:** the 1.8.0 release and parity pass.

Also outstanding: legacy relationship migration for existing high-heart pairs — the spec's
`social.legacyRelationshipMigration` setting (§11.4) — accepted as 1.8.0 scope.

## Runtime acceptance not yet performed

None of the following has been run. They cannot be unit-tested, and this release ships without them.
Each must be exercised on a real client, across the four dialogue presentations (Responsive, Minimal,
MCA Original and Townstead) and on both an integrated and a dedicated server:

1. The villager stops walking and faces the player for the whole discussion.
2. Three minutes of reading without losing the hold or the villager.
3. A schedule boundary crossed while talking does not take the villager away.
4. A zombie attack closes the window and the villager escapes, rather than standing to finish a
   sentence.
5. The 8–16 block band stays usable while walking, and beyond 24 blocks the conversation closes at
   once.
6. Two real clients for the handoff: the second player takes the villager over, the first player's
   window closes with the "speaking with someone else" message, and no stale packet from the first
   client reaches the new discussion.

The related danger-lockout risk (a 100-tick lockout making a legitimate re-interact feel broken) and
the hold-versus-`InteractTask.stop()` interaction are both judgements that need the runtime matrix,
not a unit test.

## Release checks

Run at slice 6 HEAD (`0ad7df0` / `c52d4d5`) on 2026-09-16, once per repository, through the quiet
wrapper:

```sh
/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh <repo> build verifyGeneratedConversationContent verifyVoiceOverlays
```

Both builds succeeded.

| | Forge 1.20.1 | NeoForge 1.21.1 |
| --- | --- | --- |
| Result | BUILD SUCCESSFUL | BUILD SUCCESSFUL |
| Log | `/tmp/gradle-MCAConversations-build-20260916-015410.log` | `/tmp/gradle-MCAConversations_1.21.1-build-20260916-015419.log` |
| Release-relevant tasks that executed (the logs list further build plumbing tasks) | `jar`, `conversationsReports`, `verifyGeneratedConversationContent`, `verifyVoiceOverlays` | the same, plus `verifyJarContents` |
| `:test` in this build | **UP-TO-DATE** — not executed | **UP-TO-DATE** — not executed |
| Jar | `build/libs/mcaconversations-1.7.1.jar`, 4,999,982 bytes | `build/libs/mcaconversations-neoforge-1.7.1+1.21.1.jar`, 4,958,032 bytes |
| Declared version | `mods.toml` 1.7.1 | `neoforge.mods.toml` 1.7.1+1.21.1 |
| `mcaconversations-network.properties` | `protocol=4` | `protocol=4` |
| Both new mixin classes in the jar and its `mixins.json` | Yes | Yes |

### Where the test numbers come from

**The release build did not run the tests.** Gradle reported `:test UP-TO-DATE` on both loaders,
meaning no test input had changed since the previous run and the existing results were reused. The
JUnit results below are therefore the **builder's slice 6 test runs**, against the same committed
sources the release build packaged:

| | Forge 1.20.1 | NeoForge 1.21.1 |
| --- | --- | --- |
| Results written | 2026-09-16 01:48:24 | 2026-09-16 01:51:03 |
| Test classes (XML files in `build/test-results/test/`) | 207 | 215 |
| Tests | 1,568 — 0 failures, 0 errors, 6 skipped | 1,611 — 0 failures, 0 errors, 6 skipped |
| `MixinTargetProbeTest`, `McaBindingProbeTest`, `NoMcaStaticLinkTest` | 0 skipped | 0 skipped |

The release build ran at 01:54 and reused both sets. This is a legitimate reuse — the inputs were
identical — but it is not a fresh execution, and nothing here should be read as "the suite ran again
at release time". A publishing run that wants a fresh execution should pass `--rerun-tasks` or run
`cleanTest test`.

The six skipped cases on each loader are the optional-artifact Capitals and Townstead probes, which
require jar paths that were not supplied. They are recorded as skipped, not as passed.

`.mcmod-tools/check_mod.py` reported **0 errors, 0 warnings, 0 notes** on the Forge repository. It is a
Forge 1.20.1 tool; run against the port it reports 60 expected platform mismatches and finds no missing
model, language key or texture, so it is **not applicable** to the port rather than failing there.

Release parity, run from both copies of the script:

```sh
python3 tools/verify_release_parity.py --forge /home/otectus/Projects/MCAConversations \
    --neoforge "/home/otectus/Projects/1.21.1 Ports/MCAConversations_1.21.1"
```

```
{"identical": 1879, "reviewed_adaptations": 172}
Release parity verified: shared files match and every platform difference is accounted for.
```

The two MCA members 1.7.1 newly depends on — `InteractTask#followPlayer` and
`EntityCommandHandler#stopInteracting` — were confirmed present on all five configured jars by the
slice 5 builder with `javap -p`, and the probe was shown to be load-bearing by a deliberate negative
run: renaming the injection point failed `MixinTargetProbeTest` on every jar in both repositories, and
was then reverted.

`MODMAP.md` was regenerated in the Forge repository with `.mcmod-tools/modmap.py`; everything below its
`AUTO:END` marker is unchanged. The port carries no `MODMAP.md`.

**Still not run:** no in-game session of any kind. The runtime acceptance matrix above remains
outstanding, and a green build is not a claim that any of it was exercised.
