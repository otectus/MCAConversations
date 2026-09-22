# Roadmap and open work

The one place that says what is unfinished in MCA: Conversations, what has been verified and how,
and what has not. It replaces the per-release ledgers (`RELEASE-*-LEDGER.md`,
`STABILIZATION-2026-09.md`) and carries forward every item they left open.

**Keeping it true.** Every change that closes, opens or verifies something updates this file in the
same commit, alongside `CHANGELOG.md`. An item leaves this file when it is done *and* its check has
been run; a check that was not run is written down as not run, never implied.

Last reconciled against the code: 2026-09-22, Forge `19f59cd` / NeoForge `3002bed`, version 1.7.3.

---

## 1. Release state

| Version | Forge commit | NeoForge commit | Published |
|---|---|---|---|
| 1.7.0 | `ef92593` | `958a0bb` | `origin/main` is here; not tagged |
| 1.7.1 | `375fbba` | `5b67c5b` | No — local `main` only |
| 1.7.2 | `e3c0415` | `6186b22` | No |
| 1.7.3 | `d979cc4` + `19f59cd` | `3002bed` | No |

Protocol 4 since 1.7.1: a 1.7.0 client and a 1.7.1+ server refuse each other, so 1.7.1–1.7.3 publish
**on both loaders together**. A build of the 1.7.3 source (without its three review fixes) circulates
inside Ultima Kingdoms' integration packs labelled 1.7.2 (sha256 `6d0714…`); replace it with 1.7.3.

Fabric / Minecraft 26.2 (`/home/otectus/Projects/Fabric/MCAConversations_26.2`) is at 1.6.4 and lacks
1.7.0–1.7.3. It is outside the current scope and listed here only so nobody assumes parity.

---

## 2. Runtime acceptance not yet performed

No release since 1.0.0's startup matrix has been exercised in a production-mapped client or a
dedicated server. MCA does not load under a ForgeGradle dev runtime, so `runClient` is not evidence.
Every row below is **not run**. A usable harness exists in the sibling Ultima Kingdoms repository
(`tools/test/integration_runtime.py`, which already launches a production-style Forge client and
server with this mod); adopting it is the first step of the campaign.

Run each row in the four presentations (Responsive, Minimal, MCA Original, Townstead's screen),
integrated and dedicated, on both loaders.

### 1.7.3 — kingdom gates and guild contacts
- A `kingdom_gate` topic is absent for a villager outside the kingdom in the GUI, a crafted packet,
  a numbered reply, typed chat and the dynamic hub; present inside it.
- `guild_contact` appears only for an appointed contact; an introduction and a commissions request
  relay Ultima's reason to the requester alone; no destination reaches chat or bystanders.
- Ultima absent: no gated topic, no error, `mcaconversations.civic.unavailable` is translated.

### 1.7.2 — MCA: Reputation 0.6.0
- A villager raises `known_for_courage` / `known_for_violence` only when they know the deed.
- An apology is refused on a second click and when repeated to a second resident; a superseding
  apology folds its precursor into one figure.
- The facet-aware check term moves a borderline TRUST check.
- Launch against MCA: Reputation 0.4.1 and 0.5.0 (the capability negotiation was written for them and
  never exercised).

### 1.7.1 — conversation stability
- The villager stops walking and faces the player for the whole discussion; three minutes of reading
  keep the hold.
- A schedule boundary crossed mid-conversation does not take the villager away.
- A zombie attack closes the window and the villager flees; the 100-tick lockout does not make a
  legitimate re-interact feel broken; hold versus `InteractTask.stop()` behaves.
- The 8–16 block band stays usable while walking; beyond 24 blocks the conversation closes at once.
- Two real clients: the second takes the villager over, the first sees "speaking with someone else",
  no stale packet from the first reaches the new discussion.

### 1.7.0 — refinement and reload transaction
- `/reload` with an answer card open, with a deliberately broken pack, on MCA 7.6.20, 7.7.0-beta.2
  and 7.7.1-alpha.2 (the retention hook is `require = 0`; `RELOAD-TRANSACTION-BOUNDARY.md` §5, §7).
- All three menu styles × three motion modes, including an upgraded client TOML that still reads
  `RESPONSIVE` / `FULL`; the `H` and `P` overlays at several GUI scales, keyboard-only.

### 1.6.3 — audit findings F01–F14
- F02: child, teen and adult villagers against the nine formerly `!child`-gated topics, all styles
  and chat.
- F04: a named villager at three-quarters radius on both horizontal axes gets no hearts, no reply.
- F05: a chat mod that cancels or rewrites messages, `chatModeLocalChat` on and off.
- F06: same JVM, world A, stop, open an older copy of a save with a different court.
- F07/F08: hand-edit a save's history version to 3 (original tag byte-identical afterwards); an
  oversized history file loads within bounds.
- F09: fill the history store, save/reload, the same villager is evicted each time.
- F12: two clients, GUI↔chat transitions, save/reload, `/reload`, each optional mod absent / disabled /
  present / failing.
- F13: a village with a non-ASCII or punctuated name.
- Close paths: logout, player or villager death, timeout, server stop, out-of-range, dimension change.

### Integrations
- MCA Capitals six-point checklist (`MCA_CAPITALS_SUPPORT.md`, "Validation and production checks").
- Quests / Reputation / Townstead / Serene Seasons individually and together: event order, gift
  acceptance, promise completion and failure, save/reload, resource reload.
- The real-artifact probes `capitalsProbeTest` and `townsteadProbeTest` last ran green at 1.6.1;
  every 1.7.x build reports their six cases **skipped**. Re-run with real jar paths.

---

## 3. Open work

### 3.1 Social behaviour — planned 1.8.0

`MCAConversations_Conversation_Stability_and_Social_Behavior_Implementation.md` §1.2, §8–§11,
work packages WP7–WP9. Deferred on 2026-09-15 when 1.7.1 took the stability half. None of it exists:

- `conversation/Relationships#bandOf` passes `false, false` for family and unresolved conflict; its
  comment about missing family bindings is stale (helpers exist in `compat/McaCompat`).
- No social context resolver, dialogue policy or contact policy; no meaningful-contact record in
  `history/PairHistory`; no `[social]` configuration and no `chat.ambientPlayerCooldownTicks`.
- Greeting pools are `chatmode.hail`, `hail_cold` and their toddler variants only, split on negative
  hearts: a zero-heart stranger draws the friendly pool. `GreetOnApproach` weights by personality
  only.
- No configuration-reload revalidation (no `ModConfigEvent` handler), so `CloseReason`
  `FEATURE_DISABLED`, `SPEAKER_UNAVAILABLE` and `INVALID_OFFER` are still reserved and unused.
- Legacy relationship migration for upgraded worlds (§11.2) is undesigned in code.

### 3.2 Townstead — planned 1.9.0

`MCAConversations-Townstead-1.20.1-Compatibility-Implementation-Spec.md`. What exists: the reflective
binding (14 capabilities, `compat/townstead/*`), its probes, the two client mixins that number
Townstead's RPG choices, a `season/CalendarSource` enum nothing reads, and the `[townstead]` config
section. Seventeen of its eighteen keys have no reader; only `enabled` is read. Spec §6 (variant jars) is superseded by the
single reflective jar and is not planned.

| Spec | Requirement | State |
|---|---|---|
| §8 | `conversations_townstead_available`, `_townstead`, `_tags`, `_spirit`, `_skill`, `_react` | Not registered |
| §9 | `townstead_fit` check term; structural gates (collapsed, sleeping, raid, work shift) | Not built |
| §10 | Custom personality → interiority profile, then MCA base | Not built |
| §11 | 23 `townstead_*` template variables; calendar precedence; `townstead_holidays/` | Not built |
| §12 | Outcome coordinator; measured heart notification; heart-neutral reactions; typed-chat dialogue tracking | Not built — every bridge query has no caller |
| §13–§14 | Schedule/need-aware greetings, attention and responders; gift need observation | Not built |
| §15–§16 | Gossip events and save schema; "Life here" category with eight topics; existing-topic variants | Not built |
| §17.3 | Emotion-tag sidecar and guarded client mixin | Not built |
| §20 | `/conversations compat townstead status\|probe\|snapshot\|explain` | Not built (promised in 1.3.0's notes) |
| §21, §24–§25 | Removal/re-add safety, verification matrix, release criteria | Not run |

Emotecraft is required for any reaction to play; without it reactions degrade to none.

### 3.3 MCA: Reputation 0.6.0 follow-ups (recorded divergences of 1.7.2)

- No template values for recognition tier or dominant traits (`REPUTATION_*` has tier, score,
  village, recent deed and title only).
- Two of the six standing variants Reputation §16.2 asks for ship (`known_for_courage`,
  `known_for_violence`); neutral-stranger, recognized-stranger, reliable and mixed are missing.
- `storyRevision` is not carried into the gossip told-memory.

### 3.4 Ultima Kingdoms follow-ups

- The gate infrastructure has one authored use (`guild_contact`); no topic ships a `kingdom_gate`.
- Ultima's own roadmap (`UltimaKingdoms/docs/Ultima-Factions-Integration-Audit-and-Roadmap.md`, S02)
  proposes a faction context query — public allegiance, standing, institution role, treaty status,
  news this speaker knows — through the ordinary context pipeline.
- Ultima Kingdoms has no NeoForge 1.21.1 build; the port's bridge is inert.

### 3.5 Conversation-systems plan residue

`MCAConversations-Conversation-Systems-Research-and-Implementation-Plan.md` §20.

- **WP08** — utterance ids, per-recipient disclosure bookkeeping, durable checkpoints,
  resume-after-restart, the automatic-node transition budget (§9.2–§9.4). Only the close-reason and
  teardown portion shipped.
- **WP10** — a durable, exportable transcript (the `H` drawer is deliberately not one); an
  unavailable-choice presentation policy of hide / locked / contextual refusal (only the availability
  sentence shipped); an addressed-only chat mode; grapheme-safe reveal fixtures.
- **WP11** — schemas for every authoring and runtime format (only `kingdom_gate` has one), source maps,
  `/conversations validate|explain|simulate|trace|capabilities`, the §17.3 report extensions.
- **WP12 / F10** — Portuguese first-person gender agreement: about 240 `Obrigada`/`Obrigado` in 57
  profession sources plus `cansada`, `sozinha`, `pronta` and similar; `src/content/topics/` and
  `src/content/voices/` never swept. Needs a native reader; lint can only find candidates.
- **WP13** — 8 of the 48 everyday scenes; pilots B (boundary respected), C (harmless disagreement) and
  D (court change) unauthored; `shared.advice` is the only non-work episode family; commitment
  resolvers are almost all item delivery.
- **WP14** — the integration capability matrix and exact-artifact production scenarios.
- **WP15** — group conversation still builds a new `GroupConversationSession` per interjection
  (`chat/group/GroupDirector`), so its three-speaker cap cannot hold across an exchange and footing is
  not bound to the active plan; spectators, gestures and audio, Crime and Runic Skills adapters.
- **§16.3** MCA: Quests clarify / decline / debrief / changed objective / social reward, and **§16.5**
  twelve paired Capitals follow-up scenes — no changelog evidence of either; confirm before starting.
- **§19.2** No performance baseline has been measured.

### 3.6 Content targets (from `build/libs/reports/coverage.md`)

| Target | Source | Measured at 1.7.3 |
|---|---|---|
| Raw overlay coverage ≥ 30 % | Living Histories §24.5 (Coherence §16 says 25 %) | 12.6 % of say pools |
| Salience-weighted overlay coverage ≥ 90 % | Living Histories §24.5 | 23.8 % (lint floor 18 %); substantive tier 1.8 %, filler 0.7 % |
| ≥ 3 resumable state changes per standard/deep/relationship topic | Living Histories §24.4 | `ask_parent`, `checkin_child`, `firstmet`, `happy`, `worries` report 0 callbacks |
| 48 new ordinary-life scenes | Systems plan §12.5 | 8 |

`SignatureOverlayLintTest.WEIGHTED_COVERAGE_FLOOR` (18 %) is the binding constraint on any unvoiced
content: every new scene must budget voice-family variants with it.

### 3.7 Known limitations and small bugs

- Chronicle text quoted in Capitals gossip renders in the **server's** locale (since 1.6.0).
- The `event_observed` commitment resolver is reserved and unavailable: no generic event observer
  exists. Either build one or remove it from the vocabulary.
- `flirtation` and `attraction` — and the three orientation traits — are scaffolded with no content.
- Optional per the specs, not planned: measured relationship-delta feedback on the card (visual spec
  §21); removing the legacy `numberedResponses` switch (1.5.2 spec §39).

### 3.8 Documentation debt

- `README.md`'s feature list stops at 1.7.0; `CURSEFORGE.md` stops at 1.5.1.
- `docs/conversation-templates/` was generated from the 1.5.1 corpus and lacks the Capitals topics
  and `guild_contact`.

---

## 4. Open decisions

1. Regenerate `docs/conversation-templates/` per release (about 23 MB of churn) or drop it and keep
   only `generate-templates.py`.
2. Townstead as its own 1.9.0, and whether Emotecraft is named as an optional dependency.
3. Design the romance vertical or remove the `flirtation` / `attraction` scaffolding.
4. Accept the one-time legacy relationship import for upgraded worlds (Stability spec §11.2).

---

## 5. Checks run, per release

Every command ran through `/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh`. "Tests" are totals
from `build/test-results/test/*.xml`; the six skipped cases are always the Capitals and Townstead
real-jar probes, which need jar paths that were not supplied.

### 1.7.3 — 2026-09-22

| Check | Forge | NeoForge |
|---|---|---|
| `build verifyGeneratedConversationContent verifyVoiceOverlays` | PASS, `/tmp/gradle-MCAConversations-build-20260922-164228.log` | PASS, `/tmp/gradle-MCAConversations_1.21.1-build-20260922-164537.log` (with `verifyJarContents`) |
| `:test` | executed; 1,606 tests, 0 failures, 6 skipped | executed; 1,649 tests, 0 failures, 6 skipped |
| Jar | `mcaconversations-1.7.3.jar`, sha256 `8fea8b4e…`, protocol 4, no provider class bundled | `mcaconversations-neoforge-1.7.3+1.21.1.jar`, sha256 `79354d81…`, protocol 4 |
| `check_mod.py` | 0 errors, 0 warnings, 0 notes | not applicable (1.20.1 tool) |
| Parity, `tools/verify_release_parity.py` from both copies | 1,899 identical, 178 reviewed adaptations, verified | same |
| In-game | **not run** | **not run** |

### 1.7.2 — 2026-09-16

Forge `build` PASS (`/tmp/gradle-MCAConversations-build-20260916-151454.log`), 1,589 tests, 0
failures, 6 skipped; `check_mod.py` 0/0/0. NeoForge mirror built and verified in its own repository
(`docs/RELEASE-1.7.2-LEDGER.md` there). In-game: **not run**.

### 1.7.1 — 2026-09-16

Both loaders `build verifyGeneratedConversationContent verifyVoiceOverlays` PASS; `:test` was
up-to-date in that build and reused the slice 6 results (Forge 1,568, NeoForge 1,611, 0 failures, 6
skipped each). Parity 1,879 identical, 172 reviewed. In-game: **not run**.
