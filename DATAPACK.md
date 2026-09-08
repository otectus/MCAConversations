# MCA: Conversations — Datapack & Dialogue JSON Reference

Conversations's dialogue is ordinary MCA Reborn dialogue JSON, loaded by MCA itself from
`data/<any-namespace>/dialogues/<question>.json`. Datapack authors can extend or override it, and
can use this mod's custom conditions/actions in their own packs. Everything below is verified
against the packaged MCA Reborn jars this port compiles against and probes (`mca_version` and
`mca_probe_versions` in `gradle.properties`).

## How MCA loads dialogue (the parts that matter)

- Every `data/*/dialogues/<name>.json` containing an `"answers"` array is loaded; the **question
  name is the file basename** (namespace-independent, so new questions should carry a unique
  prefix — this mod uses `conversations.*`).
- If two packs define the same question name, their **answers lists merge** (both sets of answers
  kept). Top-level flags (`auto`, `silent`) come from whichever file loads last — extension files
  must mirror the original's flags (our `main.json` carries `"silent": true` to match MCA's).
- Answer buttons label from lang key `dialogue.<question>.<answer>`; question prompts from
  `dialogue.<question>`; said lines from `dialogue.<say-key>`, all supporting random `/1`, `/2`, …
  variants (client-side pooling) and per-personality overlay namespaces
  (`assets/mca_dialogue_<personality>/lang/`).
- **Personality overlay keys MUST be prefixed with the personality id**, e.g.
  `odd.dialogue.conversations`, never `dialogue.conversations`. MCA's client resolver
  (`DialogueType.applyFallback` → `getPrefixedPhrase`) only ever looks up
  `<personality>.[<dialogueType>.]<key>`, so an unprefixed key is never treated as a personality
  line. Worse, Minecraft translation keys are **global across asset namespaces**: unprefixed keys in
  several overlays collide, and whichever resource pack loads last silently becomes every villager's
  voice. The base fallback pool in `assets/mca_dialogue/lang/` stays unprefixed — that is what the
  prefixed keys fall back to.
- Per-personality dialogue is gated client-side by `MCAClient.useExpandedPersonalityTranslations()`:
  off when an `MCAVoices` pack is active, when online TTS is enabled, or when the language is
  outside MCA's list. This mod widens the **language** part only, for locales it ships complete
  overlays for (`en_us`, `pt_br`).
- Within a result, each entry of `"conditions"` contributes `chance × conditionValue` to the
  result's score on top of `baseChance`; unknown condition keys are skipped with a log warning.
  Multiple condition keys inside one `{...}` entry are ANDed.
- **Negative sinks are the reliable gate**: a condition like
  `{"chance": -1000, "hearts_max": 24}` kills a result outright when hearts ≤ 24.
- **MCA parses condition values strictly at load, with NO error containment.** An invalid enum
  value (e.g. a bad `current_chore`) throws out of `Dialogues.apply` and **crashes the game during
  world creation / any datapack reload**. Valid values (the MCA build this port compiles against —
  `mca_version` in `gradle.properties`):
  - `current_chore`: `none, prospect, harvest, chop, hunt, fish`
  - `mood`: `depressed, sad, unhappy, passive, fine, happy, overjoyed` (string-compared — a bad
    value silently never matches rather than crashing)
  - `age_group`: `baby, toddler, child, teen, adult` · `rank`: `outlaw, peasant, merchant, noble,
    mayor, monarch`
  - **`personality`: do not use MCA's native condition.** MCA has renamed personalities more than
    once — four in 7.7, and `confident`/`peppy` dropped again for 1.21.1 — and its native parser
    throws on an id the running MCA does not know, which aborts the datapack reload and the world
    load with it. Any single value is therefore a crash on one MCA version or another. Use this
    mod's parse-safe **`conversations_personality`** instead: it never throws, and it resolves
    legacy ids to their successors so one authored value keeps working across an upgrade. Accepts a
    string or an array.
    Rollable on MCA 1.21.1 — the only ids a freshly generated villager can have: `friendly,
    flirty, playful, gloomy, sensitive, greedy, odd, crabby, extroverted, introverted, relaxed,
    anxious, peaceful, upbeat`.
    Legacy ids that still resolve: `witty`→`upbeat`, `shy`→`introverted`, `lazy`→`relaxed`,
    `grumpy`→`crabby`. And `athletic`, `confident` and `peppy` match only themselves — MCA no
    longer registers any of the three, so no new villager is one, but an upgraded save or a
    third-party pack can still present one and this mod still ships their voices. Authoring one is
    valid; it simply never matches on a stock install.
  This mod's own `conversations_*` keys are **parse-safe**: malformed JSON logs an ERROR and the entry
  degrades to a no-op action / never-matching condition instead of crashing the reload.

### Three engine rules that decide how you author a result (verified against the MCA builds in `gradle.properties`'s `mca_version` and `mca_probe_versions`)

These are not obvious from the JSON, and getting any of them wrong produces content that looks fine
and behaves strangely. All three are enforced by `ConversationGraphLintTest`.

1. **Actions run in JSON key order, and `say` must come after `next`.** The interact screen holds one
   speech slot and `setLastPhrase` *replaces* it. The `next` action writes the destination question's
   prompt into that slot; a `say` authored **before** `next` is therefore overwritten and the player
   never reads it. The order to write is: state actions (`conversations_session`,
   `conversations_progress_apply`, `conversations_affection_apply`, `conversations_disposition_apply`,
   `conversations_record`, `remember`) → `next` → `say`. A corollary: a question's own
   `dialogue.<question>` prompt is a **fallback**, shown only when a result arrives with no line of
   its own.

2. **When every result of an answer scores ≤ 0, MCA picks the LAST one.** `Dialogues.selectAnswer`
   walks the results subtracting each clamped weight and breaks when the running total goes negative;
   if nothing has positive weight the loop simply runs off the end. So the final element of a
   `results` array is the implicit safety net — put the fallback there, never first.

3. **An `auto` question must have exactly one answer.** `Actions.next` calls `getRandomAnswer()` on an
   auto question, and answers *merge across datapacks*, so a second answer turns the node into a coin
   flip that a third-party pack can introduce without touching your file.

### Answer `constraints` (MCA's native vocabulary)

An answer's own `constraints` array is MCA's native gate, separate from this mod's result-level
conditions above — it decides whether the answer is offered at all, evaluated by MCA itself before
any `conversations_*` condition sees it. **There is no `child` token.** Nine shipped answers used to
carry `!child` to keep a topic off small children; MCA's real `Constraint` registry has no `child`
entry and silently drops a token it does not recognise, so that exclusion never took effect. The base
ids (each usable plain or negated with `!`, checked in as `NativeConstraintTokens.BASE` and
cross-checked against real MCA jars by `ConstraintVocabularyProbeTest`) are: `family`, `relative`,
`baby`, `toddler`, `teen`, `adult`, `spouse`, `engaged`, `promised`, `kids`, `parent`, `cleric`,
`adventurer`, `mercenary`, `outlawed`, `trader`, `peasant`, `noble`, `mayor`, `monarch`, `orphan`,
`following`, `staying`, `village_has_space`, `has_village`, `hit_by`, `riding`. `relative` and
`riding` arrived in MCA 7.7 and are present on every build this port's declared range (`mca_version_range`
in `gradle.properties`) can run against. `ContentLintTest` fails the build if any shipped constraint token is not one of
these, plain or negated — author age exclusion through the conversation catalog's `ages` allow-list
below, not through a constraint.

## MCA's LongTermMemory (what `memory`/`remember` really do)

MCA stores an **expiry game-time** per memory id, per villager:

- `"remember": {"id": X, "var": "player"}` → remembered ~forever.
- `"remember": {"id": X, "var": "player", "time": 48000}` → expires 48000 ticks (2 MC days) later.
- `"var": "player"` suffixes the id with `.<playerUuid>` (per-player scoping).
- The `memory` **condition** computes `clamp(ticksRemaining / dividend + add, 0, max)`
  (defaults `1 / 0 / 1`), which yields two clean idioms:

| Idiom | JSON | Value |
|---|---|---|
| **has** (unexpired memory exists) | `{"memory": {"id": X, "var": "player"}}` | 1 |
| **lacks** (missing or expired) | `{"memory": {"id": X, "var": "player", "dividend": -1.0, "add": 1.0}}` | 1 |

Conversations's per-topic grammar combines them: a permanent `mcaconversations.topic.<t>` flag ("ever
asked"), an expiring `mcaconversations.cooldown.<t>` flag ("asked recently"), and results for
first-time / asked-again / revisit built from has/lacks gates plus negative sinks.

**Memory id namespace** (all ids this mod writes): `mcaconversations.topic.*`, `mcaconversations.cooldown.*`,
`mcaconversations.state.*` (`grateful`, `smitten`, `proud`, `annoyed` — player-scoped; `grieving`, `elated`
— ambient/unscoped), `mcaconversations.unlock.*` (`opened_up`, `confided`), `mcaconversations.greet.today`,
`mcaconversations.gossip.<eventUuid>`, `mcaconversations.quest.done.*` / `mcaconversations.quest.failed.*`.
Third-party packs building on these flags may read them freely; write your own ids under your own prefix.

**Conversation states** are short-lived moods left by an event, gated with a plain `memory` condition —
`{"memory": {"id": "mcaconversations.state.grieving"}}` (ambient) or `{"memory": {"id":
"mcaconversations.state.proud", "var": "player"}}` (player-scoped). They are set by: gifts (`grateful`,
`smitten`), player strikes (`annoyed`), quest complete/fail (`proud`/`annoyed`, needs MCA: Quests), and
village death/birth/marriage (`grieving`/`elated`). Durations are configurable; requires `enableStates`.

### Capital context fields (MCA Capitals integration)

Available only when MCA Capitals is installed and the `[capitals]` master switch is on. Usable as condition fields in `conversations_context`: `{"field": "capital.name", "is": "..."}` etc.

| Field | Type | Values | Meaning |
|---|---|---|---|
| `capital.present` | boolean | — | Whether this villager belongs to a capital at all |
| `capital.name` | string | capital village name | The capital's name (its MCA village name) |
| `capital.state` | string | `pending`, `founded`, `active`, `unknown` | The capital's establishment state |
| `capital.title` | string | 22 lowercase title ids | This villager's court title, or none if they have no office |
| `capital.title_rank` | integer | 0–21 | Numeric rank of the title (for breadth comparisons) |
| `capital.office` | string | `none`, `master_of_laws`, `ambassador` | Special court offices held at this capital |
| `capital.crown_standing` | string | `friend`, `enemy`, `neutral`, `unknown` | This villager's standing with the crown |
| `capital.royal_household` | boolean | — | Whether this villager is part of the royal family |
| `capital.royal_guard` | boolean | — | Whether this villager is or was a royal guard |
| `capital.disgraced` | boolean | — | Whether this villager has been disgraced |
| `capital.house` | string | house name | The noble house this villager belongs to (if any) |
| `capital.house_tier` | string | `noble`, `great`, `royal` | The tier of their house |
| `capital.at_war` | boolean | — | Whether the capital is currently at war |
| `capital.allied` | boolean | — | Whether the capital has active alliances (only when diplomacy talk enabled) |
| `capital.mourning` | boolean | — | Whether the capital is mourning a death in the royal line |
| `capital.sovereign_is_player` | boolean | — | Whether any player holds the throne (not necessarily this one) |
| `capital.player_is_sovereign` | boolean | — | Whether the player in this conversation is the reigning sovereign |
| `capital.player_allegiance` | string | `same`, `foreign`, `none` | This villager's allegiance relative to the player's capital |
| `capital.heir_named` | boolean | — | Whether a clear heir has been designated |
| `capital.title_changed` | boolean | — | Whether this villager's title just changed (within `roleRemarkDays`) |
| `capital.previous_title` | string | former title id | The title they held before this one (when changed) |

## Custom conditions (usable in any dialogue/gift JSON once this mod is installed)

| Key | Value | Meaning |
|---|---|---|
| `conversations_enabled` | one of the closed `FeatureId` ids — `topics`, `states`, `templates`, `gossip`, `quests`, `world`, `seasons`, `holidays`, `dispositions`, `checks`, `branching`, `chat`, `townstead`, `capitals`, `capital_topics`, `capital_news`, `capital_diplomacy`, `dynamic`, `identity`, `episodes`, `history`, `social_opinions`, `village_culture`, `group` | 1 when that config feature is on, else 0. Feature ids resolve through one closed registry (`FeatureId`); it currently defines no aliases, only these 24 canonical ids. **An id `FeatureId` does not know is an invalid reference, not a feature**: both `conversations_enabled` and `conversations_disabled` score 0 on it, and it is logged once at WARN (`McaConversationsConfig.warnUnknownFeature`) rather than silently defaulting to enabled |
| `conversations_disabled` | same | inverse — pair with a large negative `chance` as a kill-switch. Same unknown-id behavior as `conversations_enabled`: it also scores 0, never 1, so a typo cannot make a sink permanently unable to fire |
| `conversations_gossip` | `{"types": ["marriage","divorce","death","birth","arrival","departure","quest","coronation","royal_marriage","royal_birth","royal_death","appointment","disgrace","war","peace","alliance","capital_founded","court_news"]?, "max_age": <ticks>?}` | 1 when the villager's home village has an event matching the filter that this villager hasn't told this player (defaults: all types, 72000 ticks) |
| `conversations_weather` | `{"is": "clear" \| "rain" \| "storm"}` | 1 when the current sky in the villager's level matches (storm outranks rain outranks clear); 0 when `enableWeatherLines` is off |
| `conversations_season` | `{"is": "spring" \| "summer" \| "autumn" \| "winter"}` | 1 when the current season matches — read from Serene Seasons if installed, else the calendar season from the world day; 0 when `enableSeasonLines` is off |
| `conversations_holiday` | `{"is": "spring_bloom" \| "midsummer" \| "harvest_festival" \| "midwinter" \| "none"}` | 1 when the current calendar festival matches (`none` = an ordinary day); 0 when `enableHolidayLines` is off |
| `conversations_personality` | `"odd"` or `["odd","playful"]` | 1 when the villager's personality is one of these. **Use this instead of MCA's native `personality`** — that one throws on an id the running MCA does not know and takes the datapack reload (and world load) down with it. This one never throws, and resolves 7.6 ids to their 7.7 successors so one authored value works on both MCA versions |
| `conversations_disposition` | `{"axis": "trust" \| "respect" \| "warmth" \| "attraction" \| "tension" \| "familiarity", "min"?, "max"?}` | 1 while the decayed disposition axis lies in the inclusive range (bounds default to the axis limits). **Never matches** when `enableDispositions` is off (author a fallback result) or on `attraction` for a romance-ineligible target |
| `conversations_session` | `{"topic"?: "day", "branch"?: "rough"}` | 1 while the live session is inside that topic and/or branch. Lets sibling branches share one node instead of duplicating the branch into the node name |
| `conversations_budget` | `{"axis": "positive"\|"negative"\|"repeats", "min"?, "max"?, "decision"?}` | 1 while today's affection ledger for this villager and player is in range. `repeats` counts one decision and requires `decision`; the other two are daily totals and must not name one |
| `conversations_check` | `{"id": "<topic.stance>", "tier": "crit" \| "success" \| "partial" \| "rebuff", "axis", "difficulty": 0–100, "stance"?, "arc"?}` | 1 when the seeded check resolver lands on this result's declared tier — see *Dialogue checks* below. All tier results of a stance share id/axis/difficulty/stance/arc. Optional `stance` names a stance family so the villager's interiority profile can make that kind of remark land better or worse on them; optional `arc` names the ordered progression the check belongs to, so the seeded roll changes when the relationship genuinely moves on |
| `conversations_progress` | `{"arc","min"?,"max"?}` / `{"milestone","has"?}` / `{"exclusive","is"}` | 1 when the durable ledger agrees: arc stage in range, milestone set (or deliberately absent with `"has": false`), or this side of an exclusive choice taken (`"is": "none"` for undecided). Exactly one of the three keys |

## Custom actions

| Key | Value | Effect |
|---|---|---|
| `conversations_record` | one `{"id", "var"?, "time"?}` or an array of them | extra `remember` writes (JSON keys can't repeat, so use this when a result needs several) |
| `conversations_say` | `{"phrase": "<key>", "vars": ["villager_name", ...]?}` | says `dialogue.<phrase>` in the dialogue screen with template args |
| `conversations_gossip_say` | `{"types"?, "max_age"?, "phrase_prefix"?}` | tells the next untold event (same query rules as the condition) using `dialogue.<prefix>.<type>` (default prefix `conversations.gossip`), then marks it told for this villager+player |
| `conversations_disposition_apply` | `{"topic": "<topic.stance>", "deltas": {"<axis>": ±N}}` | moves disposition axes through the farming guards (per-axis \|delta\| ≤ 10 at parse; per-day cap and same-day repeat diminishing at apply). No-op when `enableDispositions` is off; `attraction` deltas are dropped for romance-ineligible targets |
| `conversations_session` | `{"op": "begin"\|"branch"\|"end", "topic"?, "budget"?, "branch"?}` | frames a topic on the shared conversation session. `begin` resets the per-conversation heart budget (depth class from the catalog unless `budget` overrides it); `branch` records which way an opener went; `end` closes the topic. Carries no reward of its own |
| `conversations_affection_apply` | `{"decision": "<topic.stage.stance>", "delta": ±1..8, "budget"?, "policy"?}` | **the only way branching content may move hearts.** See below |
| `conversations_progress_apply` | one object or an array of `{"arc",…}` / `{"milestone"}` / `{"exclusive","member"}` | moves durable narrative state. See below |

### Branching conversations (v1.1.0)

A converted topic stops being one click that pays out and becomes a short authored exchange: the
villager answers, **you** choose what to say back, and your reply is what moves hearts. Three pieces
of vocabulary do the work, and the runtime guards them so authored content cannot create an exploit
by accident.

**`conversations_affection_apply` — the only guarded route to a heart change.**

```json
"conversations_affection_apply": {
  "decision": "day.rough.empathize",
  "delta": 1,
  "budget": "quick",
  "policy": "daily_repeat"
}
```

- `decision` is a **stable id** that keys anti-farming, debug output and tests. Never reuse one for a
  semantically different choice; lint rejects the same id on two unrelated answers.
- `delta` is clamped to ±8 at parse.
- `budget` is the depth class whose per-conversation cap applies — `quick` (+2/−3), `standard`
  (+4/−5), `deep`/`relationship` (+8/−10), `service` (+2/−2). Omit it and the live session's class
  (from the catalog) is used.
- `policy` is `daily_repeat` (full → half → nothing for a repeat of the same decision the same day),
  `once_per_day`, or `once` (a milestone outcome, once ever). It defaults to `daily_repeat` so a pack
  that omits it still behaves safely; this mod's own lint requires it explicitly.

Every application runs: duplicate-transaction refusal → replay policy → per-conversation budget →
per-day budget → MCA's own `rewardHearts`. Note that MCA itself doubles a **negative** delta for a
`SENSITIVE` villager inside `rewardHearts`, after our caps — the budget bounds what the mod grants,
and MCA's personality rule may still amplify a granted loss.

**`conversations_progress_apply` — durable narrative state.** One object, or an array when a result
needs several (JSON keys cannot repeat):

```json
"conversations_progress_apply": [
  {"arc": "fears", "op": "advance", "to": 1},
  {"milestone": "fears.revelation"}
]
```

- `arc` with `op` `advance` / `regress` / `hold`. **An advance moves at most one stage per call**, and
  never past the `max_stage` the catalog declares — the runtime enforces both, not just lint.
- `milestone` fires exactly once, ever, for this villager and player.
- `exclusive` + `member` records one side of a mutually exclusive choice; the first side taken decides
  the group for good.

Expiring boundary and cooldown state needs no new action — `conversations_record` with a `time`
already does it.

**The conversation catalog** (`data/<namespace>/conversation_catalog/*.json`) is the machine-readable
claim that a topic exists. It is not a second dialogue engine; MCA's JSON stays authoritative. It
exists so lint can check that every shipped topic really became a conversation, and so arc, milestone
and exclusive ids are declared in exactly one place a typo cannot slip past. Files load through the
same `SimpleJsonResourceReloadListener` pattern as the chat-intent loader (`ConversationCatalogLoader`):
on a reload, every file across every pack is merged in a fixed **sorted** order (by resource location),
so which file wins a colliding topic id is deterministic across machines and reloads, never dependent
on pack-stack iteration order; a colliding id is reported once at WARN naming every declaring file and
the one that won:

```json
{"topics": {"day": {
  "entry": {"question": "conversations.cat.chitchat", "answer": "day"},
  "depth": "quick",
  "return_question": "conversations.cat.chitchat",
  "ages": ["toddler", "child", "teen", "adult"],
  "required_stance_families": ["empathy", "curiosity", "practical_help", "dismissal", "exit"],
  "chat_required": true
}}}
```

`ages` is an allow-list drawn from `AgeGroup`'s four **authorable** groups — `toddler`, `child`,
`teen`, `adult` (`AgeGroup.parse` rejects anything else, including `baby`: MCA's own babies never
hold a conversation at all, so a topic cannot be authored for them). An unreadable age (MCA absent, an
unassigned age state, a future MCA age this port does not know) never satisfies the allow-list, even
an allow-list that names every authorable group. `TopicAgeGate` is applied on every entry path a
catalogued topic can be reached from except one: the MCA dialogue screen's answer list
(`mixin/QuestionMixin`, which removes an under-age answer before it is ever clickable — MCA's own
result conditions run too late to hide a menu entry), dialogue-screen submissions
(`mixin/InteractionDialogueMessageMixin`), the numbered-choice packet
(`conversation/ChoiceSelectionService`) and free-text chat (`chat/GatePreview`) all call
`TopicAgeGate.allows`. The fifth path, dynamic hub routing (`hub/DynamicHub`), applies
`TopicEntry.allowsAge` directly rather than going through `TopicAgeGate`, dropping an under-age slot
before it is ever built. A topic with no catalog row is not gated by any of this — it is left exactly
as MCA's own dialogue JSON offers it.

Stance families are the shared vocabulary for *what kind of thing the player just said*: `empathy`,
`curiosity`, `candor`, `encouragement`, `practical_help`, `humor`, `respectful_disagreement`,
`self_disclosure`, `restraint`, `challenge`, `flirtation`, `dismissal`, `boundary_push`, `exit`.
Every topic must require `exit` — a node with no graceful way out fails lint.

**Interiority** (`data/<namespace>/interiority/*.json`) gives each personality resting disposition
baselines and a bias for or against each stance family. Baselines clamp to ±15 and stance bias to
±12, which is less than one check-tier margin: personality colours an outcome, it never decides one.
Profiles are per personality, so nothing is rolled or stored per villager.

```json
{"profiles": {"friendly": {
  "baselines":   {"warmth": 8, "trust": 4},
  "stance_bias": {"empathy": 8, "practical_help": 5, "dismissal": -10}
}}}
```

### Template variables (`conversations_say` / gossip lines)

MCA prepends the player's (spouse-aware) name as **`%1$s`** in every dialogue line; template vars
fill `%2$s`, `%3$s`, … in the order listed. Unresolvable vars fall back to neutral text
("someone", "the village", ...) — a line never breaks.

| Var | Resolves to |
|---|---|
| `villager_name` | the speaking villager's name |
| `spouse_name` | their spouse's name |
| `village_name` | their home village's name |
| `last_gift_item` | the display name of the last gift this player gave this villager |
| `time_of_day` | "this morning" / "today" / "this evening" / "tonight" |
| `profession_name` | the villager's localized profession display name (any mod's professions, client-side localized) |
| `weather` | "the clear sky" / "the rain" / "the storm" (current sky in the villager's level) |
| `season` | "spring" / "the height of summer" / "autumn" / "the depths of winter" (Serene Seasons if installed, else calendar) |
| `holiday` | "the spring bloom" / "midsummer" / "the harvest festival" / "midwinter" / "an ordinary day" |
| `capital_name` | the capital's name (the MCA village name of the village it is seated at) |
| `sovereign_name` | the reigning sovereign's name |
| `sovereign_title` | "King" or "Queen", by the sovereign's gender |
| `heir_name` | the named heir's name, or a neutral fallback when the succession is unsettled |
| `house_name` | the speaker's house name |
| `house_words` | the speaker's house words |
| `villager_title` | the speaker's own court title, localized (e.g. "Hand of the King"), or a neutral fallback when they have no office |
| `rival_capital_name` | a capital this one is at war with |
| `ally_capital_name` | a capital this one is allied with |

Gossip lines receive `%2$s` = subject A's name (e.g., a capital's village name), `%3$s` = subject B's name or event detail. For capital events: %3$s is the rendered chronicle line for court news; the new sovereign's or heir's name for coronations and royal births; for royal deaths the departed sovereign's name (or a chronicle line when the event came from the chronicle rather than the mourning-flag diff); a rival capital's name for wars and alliances. Empty only for deaths, births, arrivals, and departures (single-villager events not involving capitals).

### Scene purposes

**Purposes** (each costs interruption time; interruptions are one per villager per day unless the player initiates):
- `topic:<id>` — a topic unprompted scene (general purpose)
- `greeting` — proximity greeting when the player enters range
- `state_change` — villager moved to a new state (moods, relationships)
- `due_commitment` — a promise just came due
- `acute` — sudden grief, fear, or injury
- `shared_event` — a village event the villager wants to tell the player (cost: 4)
- `opinion_request` — villager asks the player for their thoughts on something
- `repair` — after a rupture or conflict
- `standing_remark` (MCA: Reputation) — the player's standing with the village just changed tiers (cost: 3)
- `court_remark` (MCA: Capitals) — this villager's court title just changed (cost: 3; only with MCA Capitals loaded)
- `low_stakes` — a comfort, an origin motif, an easy conversation (cost: 8)
- `resume` — picking a subject back up after time has passed (cost: 2)

### vars_used

`vars_used` is an optional, informational field on scenes, purely for documentation. It is an array of template variable names (e.g., `capital_name`, `season`, `profession_name`) that the scene's dialogue expects. Valid values are all vars listed in the *Template variables* section above, plus capital variables (`capital_name`, `sovereign_name`, `sovereign_title`, `heir_name`, `house_name`, `house_words`, `villager_title`, `rival_capital_name`, `ally_capital_name`). No enforcement: purely informational for content authors.

## The disposition vector & dialogue checks (v0.7.0)

### The vector

Each (villager, player) pair carries six bounded, internal axes, persisted in
`data/mcaconversations_dispositions.dat` (versioned; a pre-0.7.0 world simply reads baselines until
the first write). **Hearts remain MCA's sole authoritative, visible relationship economy** — the
vector never shows as a number, never grants hearts, and only decides which results open and how
lines are voiced. Heart changes stay MCA-native `positive`/`negative` fields with the usual
one-time/cooldown guards.

| Axis | Range | Decay half-life | Built / spent by |
|---|---|---|---|
| `trust` | −100..100 | ~7 MC days | confiding, honored commitments; lost fast by betrayal |
| `respect` | −100..100 | ~5 days | candor, competence, facing things |
| `warmth` | −100..100 | ~4 days | kindness, company, comfort |
| `attraction` | −100..100 | ~5 days | **romance-gated**: adults who are unmarried or married to this player; structurally unreachable otherwise |
| `tension` | 0..100 | ~2 days | recent friction; fades so one bad talk isn't a scar |
| `familiarity` | 0..100 | never | shared history; slow, time-earned, never decays |

All axes except `familiarity` drift back toward the personality baseline (exponential, computed
lazily — no tick cost). Writes are farming-guarded: per-axis daily movement cap
(`dispositionDailyAxisCap`), and repeating the same `topic` the same day yields full → half →
quarter → nothing (losses included — tension can't be rage-farmed).

### Checks

A **checked stance** is one answer whose results each declare a tier via `conversations_check`.
The resolver computes, deterministically per click:

```
score = axis value (decayed)            // hearts/2 capped ±50 instead, when dispositions are off
      + hearts/4 (capped ±25)           // hearts always matter — checks refine MCA's economy
      + mood adjust (depressed −12 … overjoyed +6; grieving −12, annoyed −8, grateful +4, smitten +6)
      + seeded roll (−10..+10)
tier  = crit at difficulty+15 · success at difficulty · partial at difficulty−15 · rebuff below
```

The roll is **seeded** from villager UUID + player UUID + check id + arc stage + a half-day time
bucket — re-opening the screen can never re-roll a rebuff into a crit; coming back later can.
With `enableCheckTiers` off, crit collapses into success and partial into rebuff. With
`enableChecks` off, no tier matches and the stance's authored fallback result fires.

### The canonical checked-stance shape

MCA's result selection is **weighted-random over positive totals, not highest-wins** — so a checked
answer must guarantee that *exactly one result has positive weight in every state* (the
`checkedAnswerStatesResolveToExactlyOneResult` lint proves this for every shipped answer, simulating
all toggle/tier/gate combinations). The shape that achieves it:

```jsonc
{ "name": "challenge", "results": [
  { // guard: fires alone below the gate, cost-free (no rebuff-farming below threshold)
    "baseChance": 0,
    "conditions": [ { "chance": 100, "conversations_disposition": { "axis": "trust", "max": 34 } } ],
    "actions": { "next": "conversations.fears", "say": "conversations.fears.challenge.guard" } },
  { // one result per tier: crit / success / partial / rebuff
    "baseChance": 0,
    "conditions": [
      { "chance": 1000, "conversations_check": { "id": "fears.challenge", "tier": "crit", "axis": "trust", "difficulty": 45 } },
      { "chance": -1000, "conversations_disposition": { "axis": "trust", "max": 34 } },  // dead below the gate
      { "chance": -2000, "conversations_disabled": "checks" } ],                          // dead when checks off
    "actions": { "next": "conversations.cat.personal", "say": "conversations.fears.challenge.crit",
      "positive": 6,
      "conversations_disposition_apply": { "topic": "fears.challenge", "deltas": { "respect": 6, "trust": 3 } } } },
  { // plain fallback: fires only when the check subsystem is disabled (0.6.0-style single outcome)
    "baseChance": 3,
    "conditions": [
      { "chance": -2000, "conversations_enabled": "checks" },
      { "chance": -1000, "conversations_disposition": { "axis": "trust", "max": 34 } } ],
    "actions": { "next": "conversations.cat.personal", "say": "conversations.fears.challenge.success", "positive": 4 } }
] }
```

Rules the lint enforces: every check id defines **all four tiers** with identical axis/difficulty;
every checked answer has the checks-disabled fallback; tier results always keep a live `next` and a
say (rebuffs exit gracefully, never dead-end); guard replies are cost-free; disposition ranges are
parser-valid and inside axis bounds; one disposition range per axis per answer.

Button labels stay **in-character words** ("You could face it. I'd stand with you."), written honest
at any relationship level — below the gate the villager's guard reply is the honest, lower-stakes
outcome. Never label a stance mechanically ("Persuade"), never show a number.

### What turns off when

| Config | Off-state behavior |
|---|---|
| `enableDispositions` | vector reads return baselines; `conversations_disposition` never matches (fallbacks fire); applies are no-ops; checks run on hearts alone |
| `enableChecks` | no tier ever matches; each checked stance's plain fallback result fires |
| `enableCheckTiers` | binary: crit→success, partial→rebuff |
| all three off | exactly the 0.6.0 experience |

## The category hub (v0.3.0)

The `conversations` hub no longer lists starters directly — it shows **category buttons**, each a
side-effect-free hop into a category page (`conversations.cat.<id>` question) that holds the actual
starters. Structure:

```
conversations (hub)                        — 6 category buttons + "Never mind." (→ main)
├── conversations.cat.chitchat             — day, food
├── conversations.cat.profession           — work            (→ conversations.work auto question)
├── conversations.cat.village              — village, people
├── conversations.cat.events               — news
├── conversations.cat.personal             — life, dreams, fears, feelings, regrets, secret
│                                       (→ conversations.dreams / .fears / .feelings follow-ups)
└── conversations.cat.relationships        — us, family      (→ conversations.us / .family follow-ups)
```

Rules the content lints enforce (`ContentLintTest`):

- **Hub answers are pure navigation**: exactly one result whose only action is
  `{"next": "conversations.cat.<id>"}` (`back` → `main`). Side effects (say/hearts/memories) belong on
  starters inside the pages.
- **Every category page has a `back` answer** hopping to `conversations` (label
  `dialogue.conversations.cat.<id>.back`); starters' results return to their own category page
  (`next: "conversations.cat.<id>"`), not the hub.
- **Empty categories hide via answer-level `constraints`** on the hub button. Constraints are
  AND-only (`containsAll`), so a category's gate must be a token implied by *every* starter it
  fronts — `relationships` uses `"family"` (MCA's `family` includes the spouse, covering both the
  spouse-gated `us` and the `family,!spouse` starter). When starters have heterogeneous gates,
  leave the category ungated and rely on per-result deflects (this mod's existing idiom —
  chit-chat/village/personal pages are never empty, their gating is result-level).
- **No answer name may collide with a question name**: answer `a` of question `q` labels from
  `dialogue.q.a`, the same key question `q.a` would use as its header. (That's why the hub button
  for relationships isn't named `family` — `conversations.family` exists as a follow-up.)

**Adding a category** = one hub answer + one `conversations.cat.<id>.json` + two lang keys
(`dialogue.conversations.<id>` button label, `dialogue.conversations.cat.<id>` page header) + a back answer
and its label. **Adding a starter to a category** = merge an answer into `conversations.cat.<id>`
(same-basename merge, no top-level flags on category files) with a
`dialogue.conversations.cat.<id>.<answer>` label.

**Uncategorized fallback:** third-party answers merged into question `conversations` still work — they
appear on the hub *after* the category buttons (MCA appends merged answers). Their
`next: "conversations"` returns land on the hub, one level up. This is the intended migration path for
packs written against the pre-0.3.0 flat hub; opt into a category by merging into
`conversations.cat.<id>` instead.

## Hub entry (`hubEntryMode`)

While `hubEntryMode = REPLACE`, **every** `next: "chat"` hop — MCA's Chat button,
or any third-party datapack's — resolves to the `conversations` hub instead (the redirect intercepts the
exact question name `chat` at MCA's `Dialogues.getQuestion`; `chat.topic`/`chat.fail` and all other
names pass through). Datapack answers merged into question `chat` are unreachable while the toggle
is on. If the `conversations` question is missing (e.g. a datapack removed it), Chat falls back to
vanilla MCA behavior automatically.

Header note: MCA builds the dialogue-screen header from the raw `next` string, so entering the hub
via Chat displays lang key `dialogue.chat` (we provide its pool; personality overlays each carry a
flavored entry line). Returning to the hub from a category page (`next: "conversations"`) uses
`dialogue.conversations`, and each category page's header is `dialogue.conversations.cat.<id>`. Datapacks can
override any of these pools to re-text entry vs. return vs. per-category headers independently.

Since 1.0.0 the **default is `ADDITIVE`**, not `REPLACE`: MCA's Chat answer is left alone and this
mod ships its own `data/mcaconversations/dialogues/main.json`, which merges a `conversations` answer
into MCA's `main` question. Both entries exist and neither hides the other. `REPLACE` restores the
0.2.0–0.9.x routing described above (and hides the separate button, so there is only one way in);
`HIDDEN` removes the button without touching MCA's Chat.

None of the three modes affects MCA's own **AI chat**, which is driven by naming a villager in
normal chat (`ServerGamePacketListenerImpl.handleChat`) and never routes through `Dialogues`.

A third-party pack can add its own menu entry the same way:

```json
{ "silent": true, "answers": [ { "name": "conversations",
    "results": [ { "baseChance": 1, "actions": { "next": "conversations" } } ] } ] }
```

(plus a lang entry `dialogue.main.conversations` for the button label). Note the `"silent": true`:
MCA keeps the top-level flags of whichever same-named file loads last, and that order is undefined,
so an extension file must mirror the original question's flags.

## Extending profession work-talk

`data/mcaconversations/dialogues/conversations.work.json` is an `auto` question whose results are scored per
profession — third-party packs can merge additional profession results into it (same-basename
merge) with `{"chance": 100, "profession": "yourmod:yourprofession"}` and their own say keys.
Conditions naming professions from uninstalled mods never match and never crash. Professions with
no hand-written result fall through to the generic templated line (`profession_name` var).

## Chat-mode intents (`chat_intents/`, chat-mode feature)

When `enableChatMode` is on, free-typed chat is matched to dialogue answers by intents loaded from
`data/<any-namespace>/chat_intents/*.json` — a real reload listener, so `/reload` picks up changes and
third-party datapacks can add or override intents without touching this mod's files.

**File shape** (both blocks optional — a synonyms-only file is a valid "synonym pack"):

```json
{
  "synonyms": {
    "work": ["job", "trade", "profession"]
  },
  "intents": {
    "profession.work": {
      "question": "conversations.cat.profession",
      "answer": "work",
      "keywords": { "work": 1.5, "craft": 0.8 },
      "requiresAny": ["work"],
      "phrases": ["what do you do", "your job"],
      "antiKeywords": ["overwork"],
      "context": null,
      "category": "topics"
    },
    "chatmode.greeting": { "system": "greet", "keywords": { "hello": 1.5 } }
  }
}
```

Rules the loader enforces (one malformed intent is skipped with a log line; the reload never fails):

- **Exactly one** of `question`+`answer` (drives the engine like a GUI click) or `system`
  (`greet` / `farewell` / `mute` / `drop` / `insult` — dispatcher behaviors).
- ≥ 1 keyword or phrase; keyword weights in `(0, 10]`.
- `synonyms` blocks **merge across all files and namespaces** (pass 1, before any intent parses), so a
  pack that only broadens vocabulary — e.g. `{"synonyms": {"rumor": ["tea", "goss"]}}` — needs no
  intents of its own. First writer wins per alias; conflicts are logged and lint-checked.
- Intent **ids** merge last-wins across datapacks (same as MCA's dialogue merge), so a pack can
  re-keyword a shipped intent by redefining its id.
- `context` scopes an intent to an open sub-question (`conversations.fears` etc.): it only scores
  while the player's session has that question open, with a scoring bonus and threshold relief.
- Keywords and phrases are authored as surface words; the loader stems and synonym-canonicalizes them
  with the same normalizer the player's message goes through — write `"fears"`, match `"afraid"`.
- Answer-level `constraints` in the dialogue file (e.g. `spouse`) are enforced automatically at match
  time; do not duplicate them in the intent.

Build-time lint (`ChatIntentLintTest`) verifies shipped intents bind to real dialogue answers, carry
enough evidence, and don't collide.

## Content-authoring checklist (a topic is not converted until all of this is true)

Work through this before opening a PR that converts a topic. Most of it is lint-enforced; the items
that are not are the ones worth being honest with yourself about.

**Shape**

- [ ] The topic has a row in `conversation_catalog/topics.json`, with a depth class, the ages it is
      reachable by, and `exit` among its required stance families.
- [ ] The opener routes into a `conversations.topic.<topic>.*` node and grants **nothing** — no
      hearts, no vector, no progress. First-seen and cooldown memories are fine; those are bookkeeping.
- [ ] Every normal adult path offers at least the decisions its depth class requires (Quick 2,
      Standard 2, Deep and Relationship 3), and no path exceeds five.
- [ ] Every node offers 2–5 answers and at least one consequence-free way out.
- [ ] Every *non-ideal* opener result — cooldown, low hearts, missing context, no quest — also leads
      to a choice. A shorter, warier exchange is fine; silently returning to the menu is not.
- [ ] Toddlers babble or get the reduced grammar; children and teens get age-appropriate lines, not
      adult lines behind a different opener.

**Consequence**

- [ ] Hearts move only through `conversations_affection_apply`, never native `positive`/`negative`.
- [ ] Every affection action declares a stable `decision` id and an explicit `policy`.
- [ ] No single path can exceed its depth class's budget in either direction.
- [ ] At least one plausible path can gain affection and at least one can lose it.
- [ ] There is no universally correct button: at least one stance's outcome depends on personality,
      mood or relationship rather than being right for everyone.
- [ ] Anything durable (arc, milestone, exclusive choice) is declared in the catalog **and** read
      back somewhere. State nothing reads is state that should not be stored.
- [ ] An arc advances at most one stage per conversation.
- [ ] A crossed boundary changes the relationship; it never removes all access to the villager.

**Both frontends**

- [ ] Every non-exit answer has a context-scoped chat intent bound to its exact question, with
      several natural paraphrases.
- [ ] At least three test utterances per stance in `IntentMatcherTest`, and they pass in context.
- [ ] The intent's keyword set is distinct from every other intent's.

**Words**

- [ ] `en_us` and `pt_br` land in the same change, with matching keys and placeholders.
- [ ] Every `say` key has its variant pool. The floor `ContentLintTest.sayKeyPoolsMeetTheVariantFloor`
      actually enforces is **3 lines**, relaxed to **2** for the precision-targeted pools:
      `conversations.work.prof.*`, `conversations.food.trait.*`, and any key ending `.child`,
      `.teen`, `.crit`, `.success`, `.partial`, `.rebuff` or `.guard`. The single exception is
      `conversations.food.trait.sirben`, where one line is the joke.
- [ ] Button labels are what the **player says** — never "Persuade", never "+2 Warmth", never a
      success chance — and are never personality-flavoured.
- [ ] A player label and its reply pool reference only detail that appears in **every** variant
      of the line they answer. "Well, the cat clearly won." answered a rough-day opener whose
      other two variants were a sticking door and a dropped egg, so it was a non-sequitur two
      times in three. If the pools disagree, write to what they share.
- [ ] Each result authors its actions in the order: state → `next` → `say`.
- [ ] A rebuff tier never routes into a close node whose answers assume the stance landed.
      `fears.open.comfort.rebuff` used to arrive at a page offering "Thank you for trusting me
      with that." Author a rebuff-aware close instead; `rebuffTiersDoNotRouteToLandedCloseNodes`
      enforces it.
- [ ] Every member of an `exclusive_groups` entry is read back by some `conversations_progress`
      condition, and so is having taken neither side. The idiom is one result per member plus a
      last result that sinks `-2000` on *all* of them - not an explicit `{"is": "none"}` scoring
      1 beside a member scoring 100, which leaves MCA a 1-in-101 chance of the wrong line.
- [ ] The node's own prompt reads acceptably on its own, even though it is only a fallback.

**Then**

- [ ] `./gradlew test` is green, including the migration ledger — delete the topic's row from
      `LEGACY_REWARDED_STARTERS`, because the debt is paid.
- [ ] Add the topic's interesting paths to `TopicPathSimulationTest`. Its coverage half walks every
      catalogued topic automatically; the hand-written scenarios are for beats worth naming.

## Conventions for content that degrades gracefully

- Every result that uses a `conversations_*` action should carry a
  `{"chance": -2000, "conversations_disabled": "<feature>"}` sink (or an alternative plain-MCA result
  must exist on the same answer) so disabling a feature falls back instead of going silent.
- Don't add answers to MCA's `auto` questions (`root`, `chat`, `rumors`) — auto questions pick a
  random answer, so merging in a new one changes MCA's own behavior odds.
- Extension files must mirror the original question's top-level flags (see `main.json`).


### Stabilization behavior

See [the stabilization report](docs/STABILIZATION-2026-09.md) for response-offer validation, bounded reward accounting, promise observers, language authoring, and the production verification matrix. A full replay ledger returns `HISTORY_CAPACITY` and grants no affection; daily decisions do not consume lifetime one-shot entries.

Authored topic pools normally require three bilingual variants. An explicit `min_variants` of 1 or 2 is reserved for deliberately infrequent scenes and their precise reactions; source lint rejects it on high-frequency scenes. The compiler carries this policy into beat metadata for independent resource validation. It does not change runtime selection weights or bypass cooldowns.
