# Changelog

All notable changes to this project will be documented in this file. Format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/); versions follow SemVer.

Compatibility: Minecraft 1.20.1 · Forge 47.x · requires MCA Reborn `[7.6,8)`.
Built against MCA 7.7.0-beta.2; verified on 7.6.20. Architectury is no longer declared (MCA 7.6
asks for it itself; MCA 7.7 dropped it). Optional: MCA: Quests, Serene Seasons.

## [1.1.0] - unreleased

**Branching conversations, phase one.** Asking a villager a question no longer pays you for the
click. They answer; you choose what to say back; your reply is what moves hearts. Two topics are
converted in this release — **the day** and **fears** — chosen deliberately as the shallowest and
the deepest, to prove the grammar before the remaining twenty-six follow.

> ### Live verification has NOT been done for this release
>
> This release ships on unit tests, lint and a successful reobfuscated build. The production
> checklist — a real Forge instance with MCA, fresh and upgraded worlds, a dedicated server, two
> concurrent players, all three hub entry modes, relog and restart persistence, MCA: Quests and
> Serene Seasons present and absent, every config off-state, and deliberate farming and
> duplicate-packet attempts — **has not been run**. ForgeGradle's `runClient` is not a substitute,
> because MCA's own mixins misbehave in that runtime. Treat 1.1.0 as untested in play.

### Topics converted so far

| Topic | Depth | Branches |
|---|---|---|
| **The day** | quick | rough / good / ordinary, plus age-appropriate trees and a repair route |
| **Fears** | deep | checked stances, a three-stage arc, a revelation, a boundary scar and its repair |
| **Check-in** | quick | rough / good, from MCA's own greeting menu |
| **Food** | quick | a dietary-trait branch that is never the butt of a rewarded joke, plus ordinary taste talk |
| **Weather** | quick | a storm is concern; a fine morning is small talk. They are not the same conversation |
| **Season** | quick | the turning year, and the four festival days with a once-a-day invitation |
| **Work** | standard | the forty profession lines now *open* a conversation instead of ending one |
| **Work offer** | service | terms and motivation first; the quest screen opens only once you have said yes |
| **Village** | standard | resident pride, honest criticism, and having nowhere to call home |
| **The people** | standard | four personality-flavoured openings; pushing for gossip after discomfort costs |
| **Rumours** | standard | who told you, is it reliable, and who you will tell |
| **News** | standard | the event *type* picks the branch: a death and a wedding are not one conversation |
| **Noticed** | standard | grieving, annoyed at you, elated, or steady — four different answers |

Thirteen starters still pay out on the click and are tracked in the lint's migration ledger.

### The economy moved

- **Opener rewards are gone from the converted topics.** Asking "how's your day?" was worth +2 or
  +3 on the click; it is now worth nothing. What the day is worth is decided by what you say next,
  and the ceiling for a whole mundane conversation is +2.
- **Losses are real and cannot be refunded.** Brushing off someone's bad day costs a heart; doubling
  down when they object costs two more. Apologising afterwards settles the *tension* — the invisible
  relationship vector — and grants no hearts at all. You cannot pay your way out of having been
  dismissive.
- **Every conversation heart change is guarded.** In order: duplicate-transaction refusal → replay
  policy (full, then half, then nothing for the same decision the same day; milestone outcomes fire
  once ever) → per-conversation budget by depth class → per-villager per-player daily budget → MCA's
  own `rewardHearts`. Positive and negative budgets are separate, so antagonising a villager never
  creates room to earn more back.
- **No universally correct button.** A joke on a bad day lands for a playful or upbeat villager,
  falls flat on a gloomy, sensitive or anxious one, and is politely received by everyone else — no
  dice involved, just who they are. Same for unsolicited advice while they're working.

### Conversations that remember

- **A three-stage arc for fears**, advancing at most one stage per conversation and continuing on
  later days: they name it, you work out what would help, and later you ask how it went.
- **A one-shot revelation.** Getting someone to open all the way about what frightens them fires
  once, ever, and a later conversation says something different because of it.
- **A boundary that can be crossed.** Pressing after a refusal — not the first attempt, the one
  *after* being told no — sets a permanent scar. The topic opens warily from then on, the warm route
  closes, and an honest apology reopens a guarded one without erasing what happened.
- **A mutually exclusive promise.** Pledging to stand with them, or honestly saying you can't, are
  both remembered, are read back differently later, and the first one taken decides it for good.

### Chat mode reaches all of it

- 57 new context-scoped intents with 171 tested utterances, covering every substantive answer in
  both trees.
- **Numbered quick-replies:** a villager who has put a decision to you lists the choices, and `2`
  picks the second one. It is the same `selectAnswer` call the GUI button makes.
- **Live-decision filtering:** while a decision is open, matching scores the choices on the table
  plus the ways out. A weak global topic match can no longer masquerade as your answer; interrupting
  requires an explicit subject change that clears an absolute floor and beats the best contextual
  reading by a margin.

### New for datapacks

`conversations_session`, `conversations_affection_apply`, `conversations_progress_apply` and the
`conversations_progress` condition; a conversation catalog under `conversation_catalog/`; a
per-personality interiority registry under `interiority/`; and `stance` / `arc` fields on
`conversations_check`. Full reference in `DATAPACK.md`, including three MCA engine rules that were
never written down before and that decide how a result must be authored.

### Fixed / completed

- **`Dispositions.baseline` returned zero for every personality.** It now reads real resting values
  from the interiority registry, so a crabby villager genuinely starts colder than a friendly one.
- **Dialogue checks used a personality fit of zero and an arc stage of zero.** Both are real now: a
  check can name a stance family, and the villager's personality moves the outcome by less than one
  tier margin; a check can name an arc, so the seeded roll changes when the relationship does.
- **MCA's GUI submission path validates nothing** — no distance, open-screen, constraint or replay
  check, byte-identical in 7.6.20 and 7.7.0-beta.2. A narrow soft-failing mixin now rejects, *for
  this mod's questions only*, an answer that was never offered, a duplicate submission in the same
  tick, and any attempt to drive a villager that MCA says is mid-conversation with someone else.
- **The progress ledger is migrated field-by-field, never discarded.** Unlike the disposition store,
  a world opened once with a newer build and rolled back keeps its arcs, milestones and promises.

### Known gaps

- Thirteen of the twenty-six original topics still pay out on the click: life story, dreams, hopes,
  regrets and secrets, feelings, and every spouse and family starter. The count is tracked as a
  migration ledger inside `ConversationGraphLintTest`, which fails if a topic is converted without
  removing its row, or if a new rewarded starter appears without being listed.
- Interiority profiles carry resting baselines and stance bias only. Wants, boundaries and secret
  pools arrive with the topics that read them — storing state nothing reads is how save files rot.
- Converted results no longer populate MCA's analysis tooltip. That tooltip explains *lottery
  chances*, and a deterministic authored branch has none; fabricating one would be both misleading
  and a mechanics leak. The villager's words are the feedback.

## [1.0.0] - 2026-08-13

**MCA Reborn 7.7 support**, on top of everything 0.9.0 shipped. 0.9.0 does not start on MCA 7.7 at
all; this release fixes that and extends the personality system to 7.7's roster.

### MCA versions

Built and pinned against **MCA `7.7.0-beta.2+1.20.1`**, and verified to still start and run on
**7.6.20**. Every MCA signature this mod consumes is byte-identical across those builds; the one
real drift (`Personality` enum → registry-backed class) is handled without reflection.

**MCA 7.7.0-beta.1 is broken on its own** and this release does not change that: beta.1 ships a
truncated `forge-mca.refmap.json` (19 mixin classes vs beta.2's 31), so MCA's own `MixinLivingEntity`
cannot resolve `isImmobile()Z` in a production runtime and startup dies. Reproduced with MCA as the
only mod installed. Use beta.2 or newer.

### Fixed

- **0.9.0 could not start on MCA 7.7: `JsonSyntaxException: Unknown personality 'witty'`.** MCA 7.7
  renamed four personalities and turned a fifth into a trait, and its native `personality` dialogue
  condition parses values with `orElseThrow` inside `Dialogues.apply`, which has no error
  containment — so 11 conditions in the shipped datapack aborted the whole reload and the world
  would not load. All 49 uses of the native condition are replaced with a new parse-safe
  **`conversations_personality`**, which never throws and matches through the canonical roster, so
  one authored id works on both MCA versions.
- **Forge refused to load on MCA 7.7 without Architectury.** `mods.toml` declared Architectury
  **mandatory** while this mod has zero references to it. MCA 7.7 dropped the dependency, so anyone
  who removed it was blocked by *us*. The declaration is gone; MCA asks for whatever MCA needs.
- **`getPersonality` used `Personality.name()`**, which exists only on the 7.6 enum. It now reads
  `Personality.toString()` — the one accessor the 7.6 enum (`"ODD"`) and the 7.7 registry class
  (`"mca:odd"`) share — and normalises both to `odd`.

### Added

- **The four personalities new in MCA 7.7 — `playful`, `extroverted`, `anxious`, `peaceful` — now
  have written voices**, at 0.9.0's full 80-key shape (conversation lines *and* the 25 chat-mode
  lines). Upstream ships no overlay for any of them, so without this they would speak only the
  generic pool. Each is written to stay distinct from its nearest neighbour (playful vs peppy,
  extroverted vs upbeat, anxious vs introverted, peaceful vs relaxed).
- **`conversations_personality`** dialogue condition (string or array), parse-safe and alias-aware.
- **Client-only locale hook** (`MCAClientMixin`) for future locales: MCA gates per-personality
  dialogue to `en_us`/`ru_ru`, and this widens **only** the language check, only for locales that
  ship complete overlays, re-testing and preserving MCA's voice-pack and online-TTS restrictions.
  Declared in the mixin config's `client` section so a dedicated server never loads a client class.

### Added — Brazilian Portuguese (`pt_br`), complete

**2,453 strings**, covering every shipped key: the 41 UI/fallback/analysis strings, all 724 base
dialogue lines, and all 21 personality overlays — including the age voices (baby babble, toddler,
child, teen) and the full chat-mode vocabulary, so a Portuguese player gets natural-language chat
in Portuguese, not just menus.

Enforced by `LocaleParityTest`: identical key sets to `en_us`, identical placeholder signatures per
key, contiguous `/N` variant runs, no bare `%s`, and every locale declared complete in
`OverlayLocales` must actually ship every overlay. A missing or mistyped key fails the build rather
than rendering a raw translation key mid-conversation.

The `dialogue.chatmode.topic.*` labels are written as noun phrases that read correctly after a
preposition ("Pergunta sobre **o meu trabalho**"), because they are substituted into other lines
rather than shown on their own.

**MCA gates per-personality dialogue to `en_us`/`ru_ru`**, so the overlays would never have been
read. `MCAClientMixin` widens **only** the language check, and only for locales that ship complete
overlays, re-testing and preserving MCA's voice-pack and online-TTS restrictions verbatim. It is
declared in the mixin config's `client` section, so a dedicated server never loads a client class —
verified in the production run.

### Changed — personality migration

| MCA 7.6 | MCA 7.7 | Conversations |
|---|---|---|
| `witty` | `upbeat` | rewritten voice; `witty.dialogue.*` kept as a 7.6 alias |
| `shy` | `introverted` | rewritten voice; `shy.dialogue.*` kept as a 7.6 alias |
| `lazy` | `relaxed` | rewritten voice; `lazy.dialogue.*` kept as a 7.6 alias |
| `grumpy` | `crabby` | rewritten voice; `grumpy.dialogue.*` kept as a 7.6 alias |
| `athletic` | *(now the `mca:athletic` trait)* | kept as a **7.6-only** overlay, not offered as a 7.7 personality |

The four renamed voices were **rewritten, not copied**: Upbeat is genuinely positive rather than
Witty's dry deflection; Introverted is reserved and articulate rather than Shy's uniform stammer;
Relaxed is unhurried-but-competent rather than Lazy's incapability; Crabby is irritable with range —
weary, blunt, and noticeably softer with someone it likes — rather than Grumpy's flat hostility.
Legacy alias namespaces carry the same text under the old prefix, so a world does not change voice
when the server upgrades.

### Changed — hub entry

`replaceChatWithConversations` (boolean) is replaced by **`hubEntryMode`**:

| Mode | MCA's Chat answer | Conversations button |
|---|---|---|
| `ADDITIVE` *(new default)* | unchanged | visible |
| `REPLACE` *(the 0.2.0–0.9.x behaviour)* | opens the Conversations hub | hidden (no duplicate entry) |
| `HIDDEN` | unchanged | hidden |

Named `hubEntryMode` rather than `chatMode` to stay clearly distinct from 0.9.0's **chat mode**
(`enableChatMode`, talking to villagers in normal chat) — the two are unrelated and independent.

Additive mode needs **no mixin**: the button is a datapack answer merged into MCA's `main` question
through the merge MCA already performs for same-named questions. A narrow `Question.getValidAnswers`
injection hides that answer in the other two modes, since MCA filters answers by constraints only.

**MCA's own AI chat is untouched in every mode**, as it always was: it is driven by
`MixinServerPlayNetworkHandler.handleChat` and never routes through the dialogue system.

*Migration:* existing configs land on `ADDITIVE`, a superset of both old settings. Set
`hubEntryMode = "REPLACE"` to restore the old routing exactly, or `"HIDDEN"` for MCA-only menus.

### Repository note

0.9.0 shipped from a state that never reached git: `origin/feature/chat-mode` held the 0.8.0
chat-mode source, while the released 0.9.0 jar contained a further delta (10 code files and 37
resource files). That delta was recovered by decompiling the jar and diffing it against a build of
the branch, and is now in source — verified by rebuilding and decompiling again, which reproduces
0.9.0's classes exactly and packages byte-identical `assets/` and `data/`.

### Tests

319 tests, all passing (0.9.0's source baseline had 297). New: `PersonalitiesTest` (roster, alias
resolution, parse-safety), `HubEntryModeTest` (behaviour matrix + injected `main.json` shape). The
overlay lint now enforces the personality-prefix rule and cross-namespace collision-freedom, and
draws its roster from the shared `Personalities` table so content and code cannot disagree.

### Verified on a production Forge 1.20.1 dedicated server (not `runClient`)

| Build | MCA | Architectury | Result |
|---|---|---|---|
| 0.9.0 | 7.7.0-beta.2 | yes | **crash** — `Unknown personality 'witty'`, never starts |
| 1.0.0 | 7.7.0-beta.2 | no | **starts** |
| 1.0.0 | 7.6.20 | yes | **starts** |

Confirmed on 1.0.0: dialogue conditions/actions register, chat mode reports its configuration at
startup, the datapack reload completes with no warnings, and `MCAClientMixin` is never loaded
server-side.

### Known limitations

- Client-side behaviour (the rendered button, per-personality line selection) is verified by lint
  and by MCA's own resolution rules, not by an automated in-game client run — MCA does not load
  under a ForgeGradle dev runtime.
- MCA 7.7 is itself in beta; the pin will move as upstream stabilises.

## [0.8.0] - 2026-07-15

**Chat mode** — a second frontend to the whole dialogue engine, **on by default**: talk to villagers
by typing in the vanilla chat box (`Agnes, how's your day?`) and they answer in chat, in their own
voice, with the identical heart gates, cooldowns, dispositions, checks, and gossip as the GUI. No
AI/LLM — deterministic, datapack-driven matching (`chat_intents/`, see DATAPACK.md).

### Added
- **Free-text matching engine**: keyword/IDF + phrase scoring with typo tolerance, synonyms,
  negation awareness, and per-answer constraint gating; ~50 shipped intents over greeting,
  chit-chat, profession, village, events, personal, relationship, and stance follow-up content.
- **Natural targeting**: name address (`Agnes, …`) > conversation stickiness (multi-turn follow-ups
  without re-addressing) > look-at > nearest; ambient questions may draw multiple staggered
  responders (`chatModeMaxResponders`).
- **Conversation depth**: open sub-questions (fears/dreams/feelings/us/family) keep context, so
  "You could face it — I'd stand with you." lands as the stance it is.
- **Social layer**: greeting/farewell/"stop talking"/"never mind" controls, graduated in-character
  confusion with topic hints, insult rebukes (ANNOYED + tension, never censors), personality-voiced
  deflections with grumpy/peppy/friendly overlays.
- **Proximity greetings** (`chatModeGreetOnApproach` + `chatModeGreetChance`, default on / 0.35):
  villagers *may* greet you on radius entry with an actual hello (`chatmode.hail` pools; a cold
  brush-off if they dislike you) — a personality-weighted, per-day deterministic coin flip, once per
  villager per player per day, on a budget separate from the GUI's ask-how-you've-been cooldown.
- **Villager attention** (`chatModeTypingAttention` + `chatModeAttentionTicks`, default on / 30 s):
  open the chat box and nearby villagers stop and turn to you (a one-byte client→server ping — the
  mod's first and only client code/packet); a conversation partner stays put facing you until the
  timer lapses after the last exchange. Villagers in danger are never pinned; "bye"/"stop talking"
  release them immediately.
- **Bare-name calls**: `Nataliya?` (typo-tolerant) gets a "Yes?" acknowledgment — the villager turns
  and waits. "Hey <Name>!" greeting-prefixed vocatives address that villager.
- **Heart feedback** (`chatModeShowHeartChanges`, default on): subtle `(+2 ♥)` suffix, shown once
  per exchange, speaker-only.
- **Local chat** (`chatModeLocalChat`, default on, EXPERIMENTAL): opted-in players' chat is
  radius-local unsigned text (still logged to the server console); set false to restore global
  signed chat.
- `/conversations chat on|off|status` (everyone) and op tools `chat debug-ask` / `chat debug <msg>`
  (live scoring introspection).
- Config `[chat]` section (radii, thresholds, delays, mute, format, …; see CONFIG.md); per-player
  opt-in capability; `chat_intents` datapack format incl. third-party synonym packs.

### Fixed
- MCA's "Last interaction analysis" panel showed raw keys (e.g. `analysis.time_min`) for conditions
  MCA ships no label for — added labels for `time_min`/`time_max` ("Time of Day"), `is_pregnant`,
  `rank`, and all 12 `conversations_*` custom conditions.
- Quests integration compiles against MCA: Quests 0.9.x (`QuestDefinition.title` API change).
- In-world test findings: "Hey <Name>!" vocatives and bare typo'd names now resolve; "what are you
  doing / up to", "what's up", "what do you do (for a living)", and "how is everyone doing" now
  match their topics; the topic-hint sentence no longer leaks a raw `greet` key; multi-line answers
  no longer repeat the heart suffix per line; "stop talking" mutes only that villager (not the whole
  village); unmatched chatter near a sticky villager stays silent instead of drawing confused lines
  unless the message actually engages them (question form / second person).

## [0.7.1] - 2026-07-11

A small correctness fix: villagers now address the player by the name they chose in the MCA
character editor instead of their Minecraft username.

### Fixed
- **Villagers use the player's MCA name, not their username.** MCA resolves the spoken player name
  (`%1$s`) from the player's family-tree node, falling back to the account username when that node
  name is blank — which it was, because the chosen name is stored separately (the `villagerName`
  entity-data tag the MCA editor writes). On login we now copy that chosen name into the family-tree
  node (`McaCompat.syncPlayerFamilyName`, called from a new `PlayerLoggedInEvent` handler), so
  `getTranslatable` resolves it correctly for **every** villager line — this mod's dialogue *and*
  MCA's own. No-op for players who never set a name (their username still shows); the write persists
  via MCA's own `FamilyTreeNode.setName` and is overworld-global, so it holds across dimensions and
  relogs. Fixes player names in `conversations_say`, gossip lines, and quest-voice lines alike.

## [0.7.0] - 2026-07-11

The first **RPG-layer** release (1.0.0 track): villagers now carry an internal, per-player
**disposition vector** — Trust, Respect, Warmth, Attraction, Tension, Familiarity — and the deepest
stances resolve through **dialogue checks** with crit/success/partial/rebuff outcomes, piloted on the
fears topic. Hearts remain MCA's only visible relationship economy: the vector never shows as a
number and never grants hearts — it decides which replies open and how they land. Everything
degrades cleanly: all RPG toggles off is exactly the 0.6.0 experience.

### Added
- **Disposition vector.** Six bounded axes per (villager, player), persisted in versioned world data
  (`data/mcaconversations_dispositions.dat`), server-authoritative, pruned on villager death (and
  optionally by age). Axes decay toward a personality baseline with per-axis half-lives — Tension
  fades in ~2 days, Trust lingers ~7; Familiarity never decays. No per-tick processing: decay is
  computed lazily on read. Pre-0.7.0 worlds migrate implicitly (first read = baseline).
- **Farming guards on every vector write.** Per-axis per-day movement cap
  (`dispositionDailyAxisCap`), and repeating the same stance the same day yields full → half →
  quarter → nothing — for losses too, so Tension can't be rage-farmed. Authored deltas are capped at
  ±10 at parse time.
- **Dialogue checks with success tiers.** New `conversations_check` condition: four results per
  stance (crit/success/partial/rebuff) selected deterministically from the disposition axis, hearts
  (capped ±25 — checks refine MCA's economy, never fight it), MCA mood, conversation states, and a
  **seeded roll** (villager + player + check id + half-day time bucket, SplitMix64) — re-opening the
  screen can never re-roll a rebuff into a crit; coming back later legitimately can.
- **New dialogue vocabulary.** Conditions `conversations_disposition` (gate on a decayed axis range)
  and `conversations_check`; action `conversations_disposition_apply` (guarded vector deltas). All
  SafeParse-contained: malformed JSON degrades to never-match/no-op, never a crash.
- **Fears pilot content.** The fears follow-up now offers four stances: *comfort* (warmth check),
  *"You could face it. I'd stand with you."* (trust-gated challenge check), *"Tell me the rest of
  it."* (higher-trust press check), and the existing *share*. Below-gate stances get an in-character,
  **cost-free** guard reply (no rebuff-farming below threshold); rebuffs misfire in character, raise
  Tension, and always exit gracefully. 24 new base lines + 2 stance labels.
- **`[rpg]` config section** — `enableDispositions`, `enableChecks`, `enableCheckTiers`,
  gain/decay multipliers, daily axis cap, stale-days pruning, `debugRpg` logging. Each documented
  with its off-state fallback in CONFIG.md.
- **Age/romance structural gating.** The Attraction axis is layered shut for non-eligible targets
  (children/teens/married-to-someone-else): the read path, the write path, the check assembler, and
  the condition adapter each gate on a **fail-closed** eligibility read (any MCA API failure means
  not eligible).

### Validation
- New unit suites: disposition math (clamp/half-life/convergence), NBT round-trip + versioning
  (missing/future version → empty store, malformed entries skipped), farming guards, seed
  determinism/spread, resolver tier bands and disabled-state formulas, parser rejection paths.
- New content lints: parser-validated disposition/check args; every check id defines **all four
  tiers** with consistent axis/difficulty plus a checks-disabled fallback; tier results never
  dead-end; and `checkedAnswerStatesResolveToExactlyOneResult` — a full state-space simulation
  proving **exactly one result of a checked answer has positive weight in every reachable state**
  (MCA's result selection is weighted-random, verified from `Dialogues.selectAnswer` bytecode, so
  this is the invariant that makes checks deterministic).

### Notes
- MCA's dialogue-response packets are handled on the server main thread (verified from the Forge
  `NetworkHandlerImpl` bytecode: `enqueueWork`) — consequence application is single-threaded.
- Verified from the 7.6.20 jar: `EntityRelationship.isMarriedTo(UUID)` = partner match + married
  state, and MCA's mood names are exactly the seven the lint pins.

### In-world verification checklist (production instance — MCA does not load in the dev runtime)
1. Boot: log shows `conversations_disposition/conversations_check` and
   `conversations_disposition_apply` registered; world creation succeeds.
2. Fears page (25+ hearts, adult villager): all four stances + back render without clipping.
3. Below-gate: with a fresh villager, *challenge*/*press* give the guard reply, cost nothing,
   and stay on the fears page.
4. Re-open scumming: force a rebuff (`debugRpg` shows the tier), close and re-open the dialogue
   within the same half-day — identical tier every time; after a sleep/next half-day it may differ.
5. Farming: repeat *comfort* through the cooldown window across a day — `debugRpg` shows applied
   deltas diminishing full → half → quarter → 0 and the daily cap truncating.
6. Two players build **independent** vectors with the same villager (`debugRpg` read logs).
7. Relog + server restart: vector values persist (`data/mcaconversations_dispositions.dat`).
8. Pre-0.7.0 world: first conversation works, reads baselines, no errors.
9. Kill the villager: its disposition records are dropped from the saved data.
10. Toggles: `enableChecks=false` → stances give the single fallback line; `enableCheckTiers=false`
    → only success/rebuff appear in the debug log; `enableDispositions=false` → guard stances never
    block and checks still resolve (hearts-only); all off → 0.6.0 behavior; `/forge tps` unchanged.

## [0.6.0] - 2026-07-07

The **seasons & deeper-gossip** release: villagers now speak to the time of year and festival days, notice
neighbours moving in and out of the village, and report every kind of news in their own personality's voice.
Two new personal/village topics round it out. Everything is additive — existing saves and datapacks are
unaffected, and every new system degrades cleanly when its feature is off or MCA state is unavailable.

### Added
- **Seasons & holidays.** New `conversations_season` (`{"is": "spring"|"summer"|"autumn"|"winter"}`) and
  `conversations_holiday` (`{"is": "spring_bloom"|"midsummer"|"harvest_festival"|"midwinter"|"none"}`)
  dialogue conditions, plus `season` and `holiday` template variables. Seasons come from **Serene Seasons**
  when it's installed and fall back to a calendar season derived from the world day otherwise; holidays are
  always calendar-based. Tunable under `[world]` (`enableSeasonLines`, `enableHolidayLines`,
  `seasonYearLengthDays`, default 96 to match Serene Seasons).
- **A "How's the season treating you?" topic** under Chit-Chat that remarks on the current festival if one
  is running, otherwise the season.
- **Arrival & departure gossip.** Villagers now notice neighbours **moving into** and **leaving** the
  village — two new `GossipEventType`s (`arrival`, `departure`) detected by diffing the village's full,
  load-independent residency set against a persisted snapshot. A death is never mistaken for a departure,
  a newborn never for an arrival, and a village's first sighting only seeds the set (no false flood).
  Toggles: `[gossip]` `detectArrival` / `detectDeparture`.
- **Gossip in every personality's voice.** All 13 personality overlays now flavour the six village-gossip
  lines (marriage, divorce, death, birth, arrival, departure) — the gloomy villager, the greedy one and the
  peppy one break the same news very differently — each with a variant. Base gossip pools raised to three
  variants apiece.
- **Two new topics.** A personal **"What are you hoping for?"** (opens at 25+ hearts and feeds the existing
  regrets/secrets confidence chain) and a village **"Any rumors going around?"** that surfaces the gossip
  pool from the Village menu.

### Notes
- Serene Seasons is a **soft, reflection-only** dependency: it is not on the compile classpath and is reached
  purely by reflection after a `ModList` check, so an MCA-only install loads and falls back to calendar
  seasons with zero Serene Seasons classes touched.
- Content/unit lints extended in lockstep: `ContentLintTest` pins the season/holiday conditions and their
  value vocabularies; `OverlayLintTest` now requires all 13 overlays to cover the six gossip keys. New tests
  cover the holiday calendar, the season-from-day math, the Serene Seasons bridge seam, the arrival/departure
  residency diff, and the gossip-type round-trip.
- As with prior releases, MCA + Quests don't load under the dev `runClient`, so the MCA-touching behaviour
  (season/weather reads, residency diffing, the new topics in the live UI) is verified by the build/lint/unit
  suite; in-world confirmation is done in a production instance.

## [0.5.0] - 2026-07-07

The **anti-repetition** release: every personality now sounds like itself across the topics you hit most,
and two new event-driven systems give villagers something fresh to react to. Existing saves and datapacks
are unaffected — all of it is additive and degrades cleanly when a feature is off or MCA state is unavailable.

### Added
- **Personality voices, everywhere that matters.** All 13 personality overlays now cover the **core-20
  highest-traffic topics** (greeting, check-in, day, work, village, neighbours, food, the personal openers,
  the deflects, gossip, and "are you happy with us"), each with **2–3 `/N` variants**. Previously overlays
  flavoured only 15 topics with a single line apiece, so most villagers said the identical base line and
  repeated it verbatim on a re-ask. Now a grumpy farmer and a peppy one answer the same question in
  genuinely different voices, and asking twice rarely returns the same words.
- **Conversation states (moods).** A gift, a completed quest, a punch, or a death/birth/marriage in the
  village now leaves a villager in a short-lived mood — `grateful`, `smitten`, `proud`, `annoyed`,
  `grieving`, or `elated` — written as an expiring `mcaconversations.state.<name>` memory. Dialogue gates
  on it with a plain MCA `memory` condition (no new datapack vocabulary); durations are tunable under the
  new `[states]` config group. Generalises the old single `grateful` state. Requires `enableStates`.
- **Weather-aware lines.** A new `conversations_weather` dialogue condition (`{"is": "clear"|"rain"|"storm"}`)
  and a `weather` template variable let villagers speak to the current sky. Gated by the new `[world]`
  config group (`enableWeatherLines`) and the `world` feature flag; storm outranks rain outranks clear.
- **Two built-in topics** surface the new systems in normal play: a **weather** starter under Chit-Chat
  (villagers remark on the current sky) and a **"How have you been, in yourself?"** starter under Events
  that reacts to a villager's current mood — condolences while `grieving`, shared joy while `elated`.

### Fixed
- **CONFIG.md** now documents the `features.enableQuests` toggle shipped in 0.4.0 (previously undocumented),
  alongside the new `[states]` / `[world]` groups.

### Notes
- Content lints extended in lockstep: `OverlayLintTest` now requires all 13 overlays to cover the core-20
  key set (with variant integrity), and `ContentLintTest` pins the `conversations_weather` condition, the
  `world` feature, and the weather value vocabulary. New unit tests cover the state enum/rules and the
  weather bucketing/query.
- Long-tail per-personality topic coverage, seasonal/holiday lines, and deeper gossip are planned for
  follow-up releases.

## [0.4.0] - 2026-07-07

### Added
- **Optional MCA: Quests integration** (only active when the `mcaquests` mod is installed; Conversations
  still loads and works fully standalone). All Quests-touching code sits behind a new
  `compat.QuestsBridge` classloading gate — the exact sibling of the MCA gate — so an MCA-only install
  never loads a `mcaquests` class. Config toggle: `features.enableQuests` (and the
  `conversations_enabled: "quests"` dialogue feature flag).
  - **Conversational awareness.** Four new dialogue conditions — `conversations_quest_available`,
    `conversations_quest_active`, `conversations_quest_ready`, `conversations_quest_completed` (value
    `{ "scope": "this"|"any", "min": N }`) — let villagers react to your quest state. They score 0 when
    Quests is absent.
  - **Drive quests from conversation.** New `conversations_quest_open` action (`{ "mode": "menu" }` or
    `{ "mode": "accept", "quest": "ns:path" }`) plus an "Anything you need doing?" answer on the
    Profession page that opens the villager's Quests menu when they have an offer (and says so gracefully
    when they don't). The Quests mod's own button stays the primary quest UI.
  - **Quests ripple through the village.** Completing a quest writes a permanent
    `mcaconversations.quest.done.*` memory on the giver and seeds a new `QUEST` gossip event other villagers
    tell; failing one writes `mcaconversations.quest.failed.*`.
  - **Personality-voiced quest lines.** A resolver registered with Quests renders quest offer/accept/
    in-progress/ready/complete/failed lines in the villager's Conversations voice (base pool
    `dialogue.conversations.quest.*`; per-personality overlays can be added later — falls back to base, and to
    Quests' own static text when Conversations is off).
  - **Conversations-based quest content.** Registers a `mcaconversations:talk_about` objective (completes when the
    player has the matching Conversations conversation) and a `mcaconversations:unlock_topic` reward (writes a Real
    Talk unlock memory) into the Quests add-on API.
- New content lints pin the four quest condition keys, the `conversations_quest_open` action, the `quests`
  feature, and the `conversations_quest_*` object args; new unit tests cover `QuestsBridge`, the quest parsers,
  the quest memory ids, and the `QUEST` gossip round-trip.

### Notes
- MCA: Quests-side additions ship in that repo (generic add-on seams: `api.QuestDialogueHooks` /
  `QuestDialogueResolver`, `api.ExternalSignalObjective`, `QuestManager.notifyExternalObjective` /
  `eligibleOffers(player, villager)`). Conversations's optional dependency degrades gracefully against a Quests
  build that lacks them.
- Not yet verified in a production instance (MCA + Quests don't load under dev `runClient`); see the
  in-world checklist below.

## [0.3.0] - 2026-07-06

### Changed
- **The hub is now a category menu.** Opening Conversations shows six category buttons — Chit-Chat
  (day, food), Profession (work), Village (village, people), Events (news), Personal (life,
  dreams, fears, feelings, regrets, secret), Relationships (us, family) — instead of the flat
  15-starter list. Each category is its own dialogue question (`conversations.cat.<id>`); starters
  moved into them **verbatim** (conditions, heart deltas, cooldown memory ids, follow-up routing
  all byte-identical — in-flight cooldowns in existing worlds are honored), with only their
  return hop retargeted to the category page. Every page has a "Something else." back answer;
  the hub keeps "Never mind." to exit.
- **Empty categories are hidden**: the Relationships button carries `constraints: "family"`
  (MCA's `family` includes the spouse), so strangers never see it. The other categories are
  never empty — their gating is result-level deflection, exactly as before.
- Third-party answers merged into question `conversations` still work: they surface on the hub after
  the category buttons (uncategorized fallback). Packs can target `conversations.cat.<id>` to join a
  category. See DATAPACK.md's new "The category hub" section.

### Added
- Category lang keys: hub button labels (`dialogue.conversations.<id>`), page headers
  (`dialogue.conversations.cat.<id>`), and per-page back labels; starter button labels moved to
  `dialogue.conversations.cat.<id>.<starter>` with their old text.
- Three content lints: hub answers must stay side-effect-free navigation hops, every
  `conversations.*` question must be reachable from the hub, and answer label keys may not collide
  with question header keys (the pre-0.3.0 hub relied on exactly that double duty).

## [0.2.1] - 2026-07-06

### Fixed
- **Raw translation key shown as the hub header when entering via Chat**
  (`#Gmale.#EPEPPY.#TTEEN.dialogue.chat`): MCA's `next` action builds the header prompt from the
  raw next string, so the Chat→Conversations redirect displays key `dialogue.chat` — which no lang file
  provided (MCA never shows it; vanilla `chat` is an auto question). Added a `dialogue.chat`
  entry-prompt pool (5 variants) plus a personality-flavored entry line in all 13 overlays, and
  lint coverage so the key set can't regress.

## [0.2.0] - 2026-07-06

### Changed
- **MCA's "Chat" button now opens the Conversations hub** (the separate "Conversations..." button is
  gone). Implemented as a soft-fail mixin on MCA's single dialogue routing point
  (`Dialogues.getQuestion`): only the exact `chat` hop is redirected; `chat.topic`/`chat.fail`,
  root/first-meeting, hire, rumors, and story flows are untouched (verified: MCA's `main.json` is
  the only referrer of `next: "chat"`). Config `replaceChatWithConversations` (default true); when off,
  Chat behaves vanilla and the hub is unreachable. MCA's old casual chat line pool is dropped.
- Work topic moved to a dedicated auto question (`conversations.work`) with per-profession responses.

### Added
- **Per-profession work talk for the whole runecraft modpack** (scanned 419 jars): hand-written
  lines for all 13 vanilla trades + nitwit + jobless, MCA's 6 registered professions
  (guard/archer/adventurer/mercenary/cultist/outlaw), all 8 More Villagers professions, Ars
  Nouveau's shady wizard, Chef's Delight chef+cook, Ice and Fire scribe, Vampirism's three, and
  Werewolves' expert — 37 professions × 2 variants. Unknown/future professions get a
  self-personalizing generic line via the new `profession_name` template variable (localized
  client-side).
- **New topics**: food (with trait-flavored replies — vegetarian, lactose intolerance, coeliac,
  diabetes, and a sirben easter egg), neighbors/people (personality-bucketed opinions), and
  secret (tier 3, unlocked by having confided — the payoff for the `confided` flag).
- **Age-appropriate answers**: child and teen villagers answer day/dreams/fears in their own
  voice (and never trigger adult follow-ups or unlock flags).
- **Personality overlays for all 13 personalities** (was 2): athletic, confident, friendly,
  witty, shy, sensitive, greedy, odd, lazy, grumpy, peppy join gloomy and flirty — 14
  high-traffic lines each.
- **Anti-repetition depth**: every say line now has a pool of ≥3 variants (≥2 for
  profession/trait/age precision lines); deflect and hub-prompt pools deepened.
- Lint gates: pinned profession roster + ResourceLocation wellformedness, trait vocabulary,
  dead-lang-key detection, variant-pool floor, overlay coverage floor, mixin-config guard.

### Removed
- `dialogue.main.conversations` button and our `chat.success/20-25` pool extensions (dead after the
  Chat replacement).

## [0.1.0] - 2026-07-06

### Fixed
- **World-creation crash** (`No enum constant forge.net.mca.entity.ai.Chore.CHOPPING`): the day
  topic used invalid `current_chore` values (`chopping`/`harvesting`/`fishing`); MCA's `Chore` enum
  is `NONE, PROSPECT, HARVEST, CHOP, HUNT, FISH` and MCA parses these at datapack load with no
  error containment, so the bad values aborted the resource reload while creating a new world.
  Corrected to `chop`/`harvest`/`fish`.
- Hardened all `conversations_*` condition/action parsers: malformed JSON (in this mod or any datapack
  using our keys) now logs an ERROR and degrades to a no-op instead of crashing the reload the
  same way.
- Content lint now validates condition *values* (chore/mood/personality/age_group/rank/constraints
  vocabularies pinned from the MCA 7.6.26 jar), not just condition keys — the gap that let the
  crash ship.

### Added
- Conversations conversation hub merged into MCA's villager Talk menu (`main` question), with 8 topics
  across three trust tiers plus gossip, spouse, and family branches (10 dialogue JSON files).
- Per-player conversation memory built on MCA's LongTermMemory: first-time / asked-recently /
  revisit-later responses per topic, permanent topic flags, `opened_up`/`confided` unlock flags.
- Custom dialogue conditions registered with MCA: `conversations_enabled`, `conversations_disabled`,
  `conversations_gossip`.
- Custom dialogue actions registered with MCA: `conversations_record` (multi-memory writes),
  `conversations_say` (templated lines: villager/spouse/village names, last gift item, time of day),
  `conversations_gossip_say`.
- Village gossip subsystem: marriage/divorce/birth detection by periodic village scan
  (relationship-snapshot diffing), death detection by event; village-scoped, name-cached,
  per-listener once-only delivery; persisted in `mcaconversations_gossip.dat`.
- Gift gratitude: a server-side mixin on MCA's `BreedableRelationship.acceptGift` records accepted
  gifts to a player capability and a per-player `grateful` villager memory (1 day by default).
- `checkin` greeting answer merged into MCA's `greet` question (memory-aware, once per half day).
- ~40 new `/N` line variants appended to MCA's most-heard dialogue pools (`main`, `greet.success`,
  `greet.fail`, `chat.success`, `story.success`, `shake_hand.success`).
- Personality-flavored overrides for gloomy and flirty villagers.
- `/conversations gossip list|clear` admin command (permission level 2).
- Config toggles per feature plus gossip/gift tunables (see CONFIG.md).
- Content lint test suite: dialogue JSON vocabulary, memory-id namespacing, lang-key coverage,
  variant-sequence integrity.

### In-world verification checklist (production-style instance)
1. Boot log shows `Registered dialogue conditions conversations_enabled/...` and no `Dialogue ... not
   properly formatted` warnings.
2. Villager Talk menu shows "Conversations..." and MCA's own buttons still work.
3. Fears topic: deflects below 25 hearts; `.first` line at 25+; immediate re-ask gives `.again`;
   after `/time add 48000` and re-ask gives `.revisit`.
4. "Us"/"Family" buttons hidden for strangers, visible for spouse/family.
5. Give an accepted gift → within a day, spouse "Are you happy?" references the item by name.
6. Marry two villagers (`/mca` admin) → within ~30s a third villager's "Anything happen around
   here lately?" names the couple, exactly once per player, surviving relog.
7. Kill a villager → death gossip names them after the entity is gone.
8. Toggle each `[features]` config off → related lines degrade to fallbacks, no errors.
9. `/forge tps` stays clean near a large village with scanning enabled.
