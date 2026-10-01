# Roadmap and open work

The one place that says what is unfinished in MCA: Conversations, what has been verified and how,
and what has not. It replaces the per-release ledgers (`RELEASE-*-LEDGER.md`,
`STABILIZATION-2026-09.md`) and carries forward every item they left open.

**Keeping it true.** Every change that closes, opens or verifies something updates this file in the
same commit, alongside `CHANGELOG.md`. An item leaves this file when it is done *and* its check has
been run; a check that was not run is written down as not run, never implied.

Last reconciled against the code: 2026-09-22, version 1.8.0 on `feature/social-1.8.0`.

---

## 1. Release state

| Version | Forge commit | NeoForge commit | Published |
|---|---|---|---|
| 1.7.0 | `ef92593` | `958a0bb` | Pushed; not tagged |
| 1.7.1 | `375fbba` | `5b67c5b` | Pushed; not tagged, not uploaded |
| 1.7.2 | `e3c0415` | `6186b22` | Pushed; not tagged, not uploaded |
| 1.7.3 | `d979cc4` + `19f59cd` | `3002bed` | Pushed to `main` / `neoforge/1.21.1`; not tagged, not uploaded |
| 1.8.0 | `feature/social-1.8.0`, pushed to `main` | `feature/social-1.8.0`, pushed to `neoforge/1.21.1` | Pushed 2026-09-30; not tagged, not uploaded |

Protocol 4 since 1.7.1: a 1.7.0 client and a 1.7.1+ server refuse each other, so 1.7.1–1.7.3 publish
**on both loaders together**. A build of the 1.7.3 source (without its three review fixes) circulates
inside Ultima Kingdoms' integration packs labelled 1.7.2 (sha256 `6d0714…`); replace it with 1.7.3.

Fabric / Minecraft 26.2 (`/home/otectus/Projects/Fabric/MCAConversations_26.2`) is at 1.6.4 and lacks
1.7.0–1.7.3. It is outside the current scope and listed here only so nobody assumes parity.

---

## 2. Runtime acceptance not yet performed

No release since 1.0.0's startup matrix has been exercised in a production-mapped client. Dedicated-server
*startup* of 1.8.0 was observed on 2026-09-30 (§5); no conversation has been played on one. MCA does not
load under a ForgeGradle dev runtime, so `runClient` is not evidence. Every row below is **not run**. A usable harness exists in the sibling Ultima Kingdoms repository
(`tools/test/integration_runtime.py`, which already launches a production-style Forge client and
server with this mod); adopting it is the first step of the campaign.

Run each row in the four presentations (Responsive, Minimal, MCA Original, Townstead's screen),
integrated and dedicated, on both loaders.

### 1.8.0 — social behaviour
- A new player meets a villager: a neutral, name-free greeting; walking past friends yields warm
  greetings more often and never more than one every ten seconds.
- A relationship built over separate days climbs stranger → acquaintance → friend; many hearts in one
  day do not.
- An upgraded world: existing friends and family keep their bands; true strangers stay strangers.
- A parent, child or sibling reads as family, including in the middle of a quarrel.
- A volunteered greeting does not stop the villager; answering it does.
- Switching chat mode off in a config reload closes live chat conversations; GUI ones continue.
- A stranger is never named: greeting, farewell, hub prompt, a typed "how have you been" (stranger
  check-in). A spouse, a relative and — with MCA: Reputation — a well-regarded stranger each get
  their own greeting in the villager's own personality; a relative's depends on whether you are
  their parent, child or sibling, and your child never uses your name; a toddler's is its own.
- Greetings follow the band: an acquaintance uses your name without warmth, a friend is glad, a
  confidant's greeting reaches you and not a second player standing nearby, a villager you have
  quarrelled with is guarded and does not wave, a hostile one is curt.
- Goodbyes follow suit: a guarded villager's is careful and a hostile one's curt, neither waves or uses
  your name; your child's never uses your first name.
- An MCA world with hearts already earned, adding Conversations for the first time: an old friend keeps
  their band. A brand-new world imports nothing.
- A test datapack adding a scene with no `social` block: it plays as before, and
  `/conversations social audit` lists it under its pack.
- Opening the chat box turns at most three heads; a sleeping or fleeing villager does not turn.
- A villager who falls asleep mid-discussion closes it as `SPEAKER_UNAVAILABLE`.

### 1.8.0 — Townstead (Townstead 0.7.6 and 0.7.7, with and without Emotecraft)
- `/conversations compat townstead status` reports `full` with 14 capabilities; `snapshot` matches
  the villager's Townstead card; `explain` names each Townstead condition on a known answer.
- *Life here* is listed only with Townstead; each topic opens by click and by typing; the hungry,
  worn-out, mid-shift, trade-level, building, spirit, date and festival scenes appear when their
  state holds, and the funnel opener otherwise.
- A reply that lands, amuses or stings plays one reaction with Emotecraft, none without, and never
  changes hearts; Townstead's `heart_increased` tag appears after a measured heart change.
- Typed chat sets and clears Townstead's `in_dialogue_with_player`; a farewell, a mute, a new partner
  and a lapsed window all clear it.
- A villager at work defers a deep typed topic with "give me a moment"; a collapsed one greets nobody
  and is not stopped by chat attention; one mid-reaction is left alone.
- Food given to a hungry villager is remembered as relief a tick later; a gift that changes nothing
  is not.
- Gossip: a crisis, a collapse and recovery, a promotion, a new skill, a birthday, a new and a
  demolished building (after two sweeps), a village spirit change — each once, none on first sight.
- A Townstead calendar drives the season and the mapped festivals; an unmapped day is no festival.
- The typewriter plays the sidecar's `<sleepy>` lines in Townstead's screen and nowhere else.
- Removing Townstead from a world that had it: the world loads, *Life here* disappears, gossip
  already told stays readable, custom personalities fall back; re-adding it resumes.

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

### 3.1 Social behaviour — delivered in 1.8.0; what remains

`MCAConversations_Conversation_Stability_and_Social_Behavior_Implementation.md` §1.2, §8–§11,
work packages WP7–WP9.

**Delivered in 1.8.0:** real family roles and ruptures in the band (`conversation/Relationships`,
`SocialPolicy`); the §8.4 ladder with contact days, familiarity and trust, configurable under
`[social]`; meaningful-contact accounting on `PairHistory` (no schema bump); the §11.2 one-time
legacy import; `social.*` context fields and the three role fields; §9.2's greeting families —
stranger, respected stranger (MCA: Reputation respect bias, courtesy only), recognised, acquaintance,
friend, confidant, partner, family (one pool per role: the player as parent, child or sibling),
guarded and hostile — each its own pool, with toddler variants; stranger and recognised greetings
voiced by all six personality families; acquaintance, friend, confidant, partner, family,
respected-stranger and guarded greetings voiced by every personality (`GreetingVoiceLintTest`); the
confidant's greeting delivered to the player alone; guarded and hostile greetings name-free and
without a wave; the pre-1.8.0 `chatmode.hail` chosen by no band and kept name-free; stranger
farewells, and guarded, hostile and child-to-parent farewells voiced by every personality (name-free,
no wave for the first two); the legacy import extended to worlds that had no history file until they
were a day old (an MCA world adding this mod, or history switched off); relationship-scaled greeting frequency and the per-player ambient cooldown; volunteered
greetings no longer hold the villager; typing attention capped at three
awake, non-fleeing villagers (§10.2); the stranger check-in; name-free hub prompts, deflections and
greet-pool extensions in every personality; `SocialContract` scene metadata (§9.3) with compiler
validation; the checked surface manifest (§9.4, `src/test/resources/social_surface_manifest.json`);
`/conversations social inspect` (§14.1); `/conversations social audit` and a `social_contract_absent`
reload note for other packs' scenes without a `social` block (§9.3's diagnostics and migration
guidance); `CloseReason.FEATURE_DISABLED` and `SPEAKER_UNAVAILABLE` in use, `INVALID_OFFER` documented
as never closing by design.

**Not planned, by design:**
- Other packs' scenes without `social` metadata are reported, not proven safe (§9.3): the audit names
  them, and nothing reads their prose to guess. Native MCA and other add-ons' lines are not owned.

### 3.2 Townstead — delivered in 1.8.0; what remains

`MCAConversations-Townstead-1.20.1-Compatibility-Implementation-Spec.md`, folded into 1.8.0 on
2026-09-22. Spec §6 (variant jars) is superseded by the single reflective jar and is not planned.

| Spec | Requirement | State |
|---|---|---|
| §8 | `conversations_townstead_available`, `_townstead`, `_tags`, `_spirit`, `_skill`, `_react` | Delivered (`compat/TownsteadConditions`, registrar) |
| §9 | `townstead_fit` check term | Delivered. Structural gates: chat defers deep topics for working, tired, collapsed or desperate villagers (`chat/TownsteadChatPolicy`); scenes gate on `townstead.*` fields. The dialogue screen is not gated by shift |
| §10 | Custom personality → interiority profile, then MCA base | Delivered |
| §11 | 23 template variables; calendar precedence; `townstead_holidays/` | Delivered, with mappings for Townstead's four profiles |
| §12 | Outcome coordinator; measured heart notification; reactions; typed-chat dialogue tracking | Delivered (`conversation/ConversationOutcomes`, 13 reactions) |
| §13–§14 | Schedule/need-aware greetings, attention and responders; gift need observation | Delivered |
| §15–§16 | Gossip events and save schema; "Life here" with eight topics; existing-topic variants | Delivered (variants in `day`, `season`, `life`) |
| §17.1 | `hubEntryMode` matrix in Townstead's screen, scripted | Not run |
| §17.3 | Emotion-tag sidecar and guarded client mixin | Delivered; not seen in a live client |
| §20 | `/conversations compat townstead status\|probe\|snapshot\|explain`, `compat namespace` | Delivered |
| §21, §24–§25 | Removal/re-add in a running world, verification matrix, release criteria | Not run (see §2) |

Reactions use Emotecraft's built-in emotes (`waving`, `clap`, `here`, `point`, `palm`, `crying`),
which Townstead's backend knows; how each reads on a villager has not been seen. Emotecraft is
documented as needed for reactions and is not declared in `mods.toml`.

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

- A refused first content load leaves the mod with **no dialogue**: one malformed entry in any pack
  refuses the whole attempt and nothing earlier exists to retain, until the pack is fixed and `/reload`
  is run. An ERROR now says so (2026-09-30); the policy is open decision 5 (§4).
- Chronicle text quoted in Capitals gossip renders in the **server's** locale (since 1.6.0).
- The `event_observed` commitment resolver is reserved and unavailable: no generic event observer
  exists. Either build one or remove it from the vocabulary.
- `flirtation` and `attraction` — and the three orientation traits — are scaffolded with no content.
- Optional per the specs, not planned: measured relationship-delta feedback on the card (visual spec
  §21); removing the legacy `numberedResponses` switch (1.5.2 spec §39).

### 3.8 Documentation debt

None recorded. `docs/conversation-templates/` is no longer committed; `generate-templates.py` produces
a current set on demand (§4, decision 1).

---

## 4. Open decisions

1. ~~Regenerate `docs/conversation-templates/` per release (about 23 MB of churn) or drop it and keep
   only `generate-templates.py`~~ — decided 2026-09-22: dropped. The generated files are ignored and
   produced on demand; the generator and its `README.md` stay.
2. ~~Townstead as its own 1.9.0~~ — decided 2026-09-22: folded into 1.8.0. Emotecraft is documented,
   not declared in `mods.toml`.
3. Design the romance vertical or remove the `flirtation` / `attraction` scaffolding.
4. ~~Accept the one-time legacy relationship import~~ — accepted 2026-09-22 and shipped in 1.8.0.
5. At a refused *first* load, keep rejecting everything (today), or publish this mod's own content and
   quarantine only the packs that carried a refusal. See `AUDIT.md`, open question 1.

---

## 5. Checks run, per release

Every command ran through `/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh`. "Tests" are totals
from `build/test-results/test/*.xml`; the six skipped cases are always the Capitals and Townstead
real-jar probes, which need jar paths that were not supplied.

### 1.8.0 — 2026-09-30 audit

| Check | Forge | NeoForge |
|---|---|---|
| `build verifyGeneratedConversationContent verifyVoiceOverlays` | PASS, `/tmp/gradle-MCAConversations-build-20260930-201530.log`; 1,742 tests, 0 failures, 6 skipped; drift gates 2 and 5 tests, 0 failures | PASS with `verifyJarContents`, `/tmp/gradle-MCAConversations_1.21.1-build-20260930-201605.log`; 1,786 tests, 0 failures, 6 skipped; drift gates 2 and 5 |
| Parity, `tools/verify_release_parity.py` from both copies | 2,028 identical, 191 reviewed adaptations, verified | same |
| Jar | `mcaconversations-1.8.0.jar`, sha256 `0ceeaf40…`, protocol 4 | `mcaconversations-neoforge-1.8.0+1.21.1.jar`, sha256 `589aa9d7…`, protocol 4 |
| `check_mod.py` | 0 errors, 0 warnings, 0 notes | not applicable |
| Dedicated server | Packaged Forge 47.4.23 (`/tmp/mcac-boot/boot.py`): starts, `/reload`, console commands, villager summon and kill, stops; 0 WARN/ERROR from this mod; 7/7 mixins applied. MCA 7.7.1-beta.2; MCA 7.6.20 + Architectury (final jar, `rel-*`); MCA 7.6.26 with Townstead 0.7.6, Quests 1.7.0, Reputation 0.6.1, Crime 0.7.5 (2026-09-30 build), Serene Seasons | `runServer` (MCA loads as a real mod here), driven over RCON: same commands; 0 WARN/ERROR from this mod, 0 exceptions; 8/8 mixins applied; MCA root `net.conczin.mca.`, 92 members |
| Broken third-party pack at startup | Load refused; ERROR names the pack; `datapack disable` recovers (generation 1, 1,057 questions) | not run |
| In-game conversation | **not run** | **not run** |

### 1.8.0 — 2026-09-22

| Check | Forge | NeoForge |
|---|---|---|
| `build verifyGeneratedConversationContent verifyVoiceOverlays` | PASS, `/tmp/gradle-MCAConversations-build-20260922-214414.log` | PASS, `/tmp/gradle-MCAConversations_1.21.1-build-20260922-214447.log` (with `verifyJarContents`) |
| `:test` | executed; 1,709 tests, 0 failures, 6 skipped | executed; 1,752 tests, 0 failures, 6 skipped |
| `townsteadProbeTest` against real jars | PASS on 0.7.7 modern (`-193435.log`), 0.7.7 legacy (`-193444.log`) and 0.7.6 (`-193452.log`); each 6 tests, 0 skipped, root `forge.net.conczin.mca`, 14 server capabilities | PASS on the 1.21.1 build (`/tmp/gradle-MCAConversations_1.21.1-townsteadProbeTest-20260922-194113.log`); 6 tests, 0 skipped, root `net.conczin.mca`, 14 server capabilities |
| Jar | `mcaconversations-1.8.0.jar`, protocol 4 | `mcaconversations-neoforge-1.8.0+1.21.1.jar`, protocol 4 |
| `check_mod.py` | 0 errors, 0 warnings, 0 notes | not applicable |
| Parity, from both copies | 2,017 identical, 185 reviewed adaptations, verified | same |
| In-game | **not run** | **not run** |

The Forge probe logs are `/tmp/gradle-MCAConversations-townsteadProbeTest-20260922-<time>.log`.

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
