# Changelog

All notable changes to this project will be documented in this file. Format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/); versions follow SemVer.

Compatibility: Minecraft 1.20.1 · Forge 47.x · requires MCA Reborn `[7.6,8)` + Architectury API `[9.2,10)`.
Optional: Serene Seasons (soft, reflection-only — used for real seasons when present).

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
- **Proximity greetings** (`chatModeGreetOnApproach`, default on): villagers greet you as you
  arrive, sharing the GUI's daily greet cooldown.
- **Heart feedback** (`chatModeShowHeartChanges`, default on): subtle `(+2 ♥)` suffix, speaker-only.
- **Local chat** (`chatModeLocalChat`, default on, EXPERIMENTAL): opted-in players' chat is
  radius-local unsigned text; set false to restore global signed chat.
- `/conversations chat on|off|status` (everyone) and op tools `chat debug-ask` / `chat debug <msg>`
  (live scoring introspection).
- Config `[chat]` section (radii, thresholds, delays, mute, format, …); per-player opt-in
  capability; `chat_intents` datapack format incl. third-party synonym packs.

### Fixed
- MCA's "Last interaction analysis" panel showed raw keys (e.g. `analysis.time_min`) for conditions
  MCA ships no label for — added labels for `time_min`/`time_max` ("Time of Day"), `is_pregnant`,
  `rank`, and all 12 `conversations_*` custom conditions.
- Quests integration compiles against MCA: Quests 0.9.x (`QuestDefinition.title` API change).

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
