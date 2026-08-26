# MCA: Conversations — Datapack & Dialogue JSON Reference

Conversations's dialogue is ordinary MCA Reborn dialogue JSON, loaded by MCA itself from
`data/<any-namespace>/dialogues/<question>.json`. Datapack authors can extend or override it, and
can use this mod's custom conditions/actions in their own packs. Everything below is verified
against the packaged MCA Reborn jars — 7.7.0-beta.2 (the build this mod is compiled against)
and 7.6.20 (still supported at runtime).

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
  world creation / any datapack reload**. Valid values (MCA 7.6.26):
  - `current_chore`: `none, prospect, harvest, chop, hunt, fish`
  - `mood`: `depressed, sad, unhappy, passive, fine, happy, overjoyed` (string-compared — a bad
    value silently never matches rather than crashing)
  - `age_group`: `baby, toddler, child, teen, adult` · `rank`: `outlaw, peasant, merchant, noble,
    mayor, monarch`
  - **`personality`: do not use MCA's native condition.** MCA 7.7 renamed four personalities and
    turned `athletic` into a trait, and the native parser throws on an id the running MCA does not
    know — which aborts the datapack reload and the world load. Any single value is therefore a
    crash on one MCA version or the other. Use this mod's parse-safe **`conversations_personality`**
    instead: it never throws, and it resolves legacy ids to their successors so one authored value
    works on both. Accepts a string or an array.
    Canonical (MCA 7.7): `confident, peppy, friendly, flirty, playful, gloomy, sensitive, greedy,
    odd, crabby, extroverted, introverted, relaxed, anxious, peaceful, upbeat`.
    Legacy ids that still resolve: `witty`→`upbeat`, `shy`→`introverted`, `lazy`→`relaxed`,
    `grumpy`→`crabby`; `athletic` matches only itself (MCA 7.6 worlds).
  This mod's own `conversations_*` keys are **parse-safe**: malformed JSON logs an ERROR and the entry
  degrades to a no-op action / never-matching condition instead of crashing the reload.

### Three engine rules that decide how you author a result (verified in 7.6.20 and 7.7.0-beta.2)

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

## Custom conditions (usable in any dialogue/gift JSON once this mod is installed)

| Key | Value | Meaning |
|---|---|---|
| `conversations_enabled` | `"topics" \| "states" \| "templates" \| "gossip" \| "quests" \| "world" \| "dispositions" \| "checks" \| "chat"` | 1 when that config feature is on, else 0 |
| `conversations_disabled` | same | inverse — pair with a large negative `chance` as a kill-switch |
| `conversations_gossip` | `{"types": ["marriage","divorce","death","birth","arrival","departure","quest"]?, "max_age": <ticks>?}` | 1 when the villager's home village has an event matching the filter that this villager hasn't told this player (defaults: all types, 72000 ticks) |
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
and exclusive ids are declared in exactly one place a typo cannot slip past:

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

Gossip lines receive `%2$s` = subject A's name, `%3$s` = subject B's name (empty for
single-subject events like deaths, births, arrivals, and departures).

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

## Semantic contracts (`conversation_beats/`)

MCA decides which buttons to show from the **answer's** own constraints. It never looks at which
villager line led there. So if two lines need different replies, no condition anywhere can stop the
wrong button appearing — the lines have to open different questions. A beat contract is how that
becomes something the build can check.

A **beat** is one `say` pool paired with the `next` question it opens, treated as a single authored
unit:

```json
{
  "beats": {
    "work.armorer.identity": {
      "topic": "work",
      "say": "conversations.work.prof.armorer",
      "response_question": "conversations.topic.work.armorer.respond",
      "npc_act": "explain",
      "subject": "work.armorer.identity",
      "polarity": "positive",
      "openness": "permits_followup",
      "facts": ["work:armorer"],
      "allowed_stances": ["curiosity", "encouragement", "challenge", "exit"],
      "forbidden_stances": ["flirtation", "dismissal", "empathy", "practical_help"],
      "context": {"ages": ["adult"], "profession": "minecraft:armorer"},
      "callback": {"fact": "work.armorer.issue", "value": "repeated_split", "expires_after": 168000}
    }
  }
}
```

- `npc_act` — what the line *does*: `report`, `explain`, `disclose`, `disclose_problem`, `complain`,
  `celebrate`, `ask`, `invite`, `reminisce`, `request_help`, `offer_work`, `accept`, `qualify`,
  `resist`, `deflect`, and the four ruptures `refuse`, `hurt`, `set_boundary`, `dismiss`.
- `polarity` — `positive`, `neutral`, `mixed`, `negative`, `acute`. `acute` is grief, fear and fresh
  harm: humour and flirtation are never appropriate there, however playful the villager is.
- `openness` — `invites_followup`, `permits_followup`, `guarded`, `closes_subject`,
  `ends_conversation`. A page may not be opened both by a line inviting more and a line closing the
  subject; lint splits them.
- `facts` — written `type:value`, and shared by **every variant** of the pool. MCA picks the variant
  on the client and the server never learns which one, so a claim only one variant makes is a claim
  no reply may answer.
- `allowed_stances` / `forbidden_stances` — which player stances make sense afterwards. Must include
  `exit`: every page needs a door.
- `outcome` — present when the beat is the villager *reacting* to a player line: `accepted`,
  `appreciated`, `engaged`, `qualified`, `misunderstood`, `resisted`, and the ruptures `rebuffed`,
  `hurt`, `boundary_closed`, `conversation_ended`. A rupture may only open apology, clarification,
  respect for the boundary, or the door.

A **reply** is one button, bound to its exact `question/answer`:

```json
{
  "replies": {
    "conversations.topic.work.armorer.respond/value": {
      "stance": "encouragement",
      "responds_to": ["work.armorer.identity"],
      "requires_facts": ["work:armorer"],
      "introduces_facts": [],
      "tone": "plain",
      "outcomes": ["appreciated"],
      "exit": false
    }
  }
}
```

`responds_to` accepts exact beat ids, `subject:work.armorer.*` patterns, or `*`. `requires_facts` is
what the wording takes for granted — it is why "I'll bring you some" cannot be offered on a route
where nothing established a *some*. `tone` is how the button sounds (`gentle`, `plain`, `playful`,
`blunt`, `hostile`, `intimate`) as distinct from what it wants, so a gentle wording cannot hide a
hostile consequence.

Results name their beat through the session action, which grants and speaks nothing:

```json
"conversations_session": {"op": "turn", "beat": "work.armorer.identity"}
```

An unknown beat id is logged and ignored rather than thrown, so a pack that names a beat it forgot
to ship loses its breadcrumbs, not the player's conversation.

## Extending profession work-talk

`data/mcaconversations/dialogues/conversations.work.json` is an `auto` question whose results are scored per
profession — third-party packs can merge additional profession results into it (same-basename
merge) with `{"chance": 100, "profession": "yourmod:yourprofession"}` and their own say keys.
Conditions naming professions from uninstalled mods never match and never crash. Professions with
no hand-written result fall through to the generic templated line (`profession_name` var), which is
the deliberate coherent fallback for a trade this mod has never heard of.

**A profession this mod ships routes to its own page** — `conversations.topic.work.<path>.respond` —
and a pack adding a profession should do the same rather than merging into another trade's page.
Declare what the trade is in `data/<namespace>/profession_profiles/*.json`:

```json
{
  "profiles": {
    "yourmod:chandler": {
      "archetype": "craft",
      "owner": "yourmod",
      "display_fallback": "chandler",
      "subjects": ["current_task", "craft", "risk", "village_value", "learning", "aspiration",
                   "tallow", "wicks"],
      "materials": ["tallow", "wick", "mould"],
      "risks": ["fire", "shortage"],
      "beneficiaries": ["every_household"],
      "callback_types": ["order_placed", "player_help"],
      "season_affinity": false,
      "weather_affinity": false
    }
  }
}
```

- `archetype` — `cultivation`, `food`, `craft`, `knowledge`, `defense`, `exploration`, `occult`,
  `untraded`. Archetypes exist so trades can share routing, condition shapes and callback plumbing.
  They may never share villager lines: a mason and a woodworker both build things that outlive them,
  and that is worth a shared schema, not a shared sentence.
- `subjects` — at least six, because a trade with fewer becomes one line and a shrug.
- `callback_types` — at least two, because a trade that remembers nothing cannot have a second
  conversation.
- `owner` — the mod that supplies the profession, or `base`. An optional profession whose mod is
  absent simply never matches; it must not look like a missing base profession.

**Keep one mod's professions in files named after that mod.** The loaders merge every file in a
directory, so which file a profile, beat or intent sits in changes nothing at runtime — and changes
everything when the owning mod renames a profession. This mod ships its optional trades that way and
the build enforces it: `profession_profiles/<owner>.json`, `conversation_beats/work_<owner>.json`,
`conversation_beats/terminal_work_<owner>.json` and `chat_intents/profession_<owner>.json` hold that
owner's professions and nothing else, while the dialogue pages are already one file each and named
after the trade. A pack adding professions from someone else's mod should follow the same split, so
that removing one mod's support means deleting files rather than editing shared ones.

No optional profession may appear in `mods.toml`. A declared dependency — even `mandatory=false` —
is a claim about a mod that is not needed to load, and a profession condition naming an absent mod
simply never matches.

## Relationship bands (`conversations_relationship`)

Dialogue asks how close two people are by name, never by heart number. The thresholds live in
`RelationshipBand` and nowhere else, so changing what "confidant" means is one edit.

```json
{"chance": 100, "conversations_relationship": {"at_least": "friend"}}
{"chance": 100, "conversations_relationship": ["stranger", "tense", "hostile"]}
{"chance": 100, "conversations_relationship": {"at_least": "friend", "not": ["partner"]}}
```

- Bands: `stranger` (0), `acquaintance` (25), `friend` (60), `confidant` (80) on the warmth line;
  `partner` (married to this player) and `family` (a role, not a score); `tense` and `hostile` for a
  relationship that has gone wrong regardless of what preceded it.
- `at_least` counts `partner` and `family` as at least `confidant` — a spouse or a relative may hear
  anything a confidant may. The ruptured bands are never "at least" anything.
- A rupture outranks warmth and a marriage outranks a heart total, in that order.
- It fails soft: an unreadable relationship resolves to `stranger`, the band that discloses least.
- `family` parses but this build never resolves it — MCA's parent/child relations are not among the
  members the compat layer binds. Do not gate content on it alone.

## Personality overlays and voice families

MCA resolves a villager's line by looking for `<personality>.dialogue.<key>` before
`dialogue.<key>`, so an overlay is a complete replacement line for one personality. This mod ships
one overlay namespace per personality — `assets/mca_dialogue_<personality>/lang/` — covering the
MCA 7.7 roster, the 7.6 spellings that were renamed, and `athletic`.

**Overlays are authored per voice family, not per personality.** Six families —
`plainspoken`, `bright`, `warm`, `quiet`, `tender`, `settled` — group the sixteen personalities by
*how* they say a hard thing rather than by temperament adjectives, and one authored line is expanded
into every member namespace. `VoiceFamily` is the source of truth for the mapping and the build
fails if a personality belongs to none or to two. Writing sixteen versions of one sentence produces
sixteen paraphrases, which the paraphrase lint rejects and a player notices; six approaches are a
real distinction.

Where a pool already has per-personality lines, those are finer-grained than a family line and are
kept: the build tops such pools up rather than overwriting them.

**Signature beats must be covered in full.** `SignatureBeat` derives, from the beat contracts, the
lines where personality has to be audible: a trade's identity and craft openers, any beat whose
speech act or outcome is a rupture, deep-topic disclosures, invitations, and callbacks. Every one of
those pools must exist in every personality namespace at the base pool's full variant count — an
overlay with fewer variants lets MCA ask for an index the personality never wrote, and the villager
falls back mid-pool. A signature pool with no coverage fails the build unless it is listed in
`src/test/resources/signature_overlay_debt.txt`.

Two rules that catch the common mistakes: an overlay key must have a base line to override, and a
single-line base pool is written without a `/1` suffix — so its overlay must be written bare too.

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
- [ ] Every beat's `subject` names what the villager is talking about, and the topic reaches the
      subject-family count its depth class asks for — or its shortfall shrinks in
      `topic_depth_debt.txt`.
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
- [ ] `ChatCoverageTest` is green: no reply button without an intent, no intent without a button.

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
- [ ] Nothing in the new lines pushes a marked word past 2% of the corpus or an exact sentence
      past twelve copies. If a farewell already exists twelve times, write a different one.

**Then**

- [ ] `./gradlew test` is green, including the migration ledger — delete the topic's row from
      `LEGACY_REWARDED_STARTERS`, because the debt is paid.
- [ ] Add the topic's interesting paths to `TopicPathSimulationTest`. Its coverage half walks every
      catalogued topic automatically; the hand-written scenarios are for beats worth naming.

## Subjects, and the depth a topic owes

A beat's `subject` is **what the villager is actually talking about**, not which page the line lives
on. It is the field the session stores for the whole exchange, the one the trace exporter prints, and
the one `TopicDepthTest` counts — so a topic whose every beat says `village.home` is measured as one
conversation in several costumes, however many beats it has.

Name it after the content. The village topic carries `village.praise`, `village.criticism`,
`village.fault`, `village.help`, `village.insult`, `village.belonging`, `village.season`,
`village.home`, `village.repeat`, `village.no_home`, `village.toddler` and `village.young`, because
those are twelve different things to talk about. If two beats would honestly carry the same subject,
they should — the count is a measurement, not a target to game.

Each depth class owes a number of subject families, from spec §8.1:

| Depth class | Subject families | Disclosure levels | Also |
|---|---:|---:|---|
| `quick` | 2 | 2 | at least 2 decisions on normal paths |
| `standard` | 10 | 3 | state-sensitive revisits |
| `deep` | 4 | 4 | explicit boundaries and a durable callback |
| `relationship` | 4 | 2 | relationship-state entry families |
| `service` | 2 | 2 | need and terms established before acceptance |

A topic below its floor must be named in `src/test/resources/topic_depth_debt.txt` with the number
it is short by. That ledger is a ceiling, not a note: falling further behind fails the build, and so
does reaching the target while still listed. It can only shrink. It is currently empty.

## Wildcard reply contracts (`responds_to: ["*"]`)

Most reply contracts name the beats they answer. The gossip-driven pages cannot: their opener is not
a `say` key at all but `conversations_gossip_say`, which chooses a line by event type at runtime, so
there is no single inbound beat to name. Those buttons declare `"responds_to": ["*"]` — legal after
any line that opens their page — which for a page whose opener is chosen at runtime is the truth
rather than a shrug.

Use it only for that case. Everywhere else, naming the inbound beats is what makes the lint able to
tell you that a button no longer answers the line above it.

## Verbal tics

Two habits are invisible while writing and unmistakable while playing, and no paraphrase check can
see either, because each individual line is fine:

- **A catchphrase.** A marked word creeping to the front of everything. "Aye" once opened 3.7% of the
  English corpus. `VerbalTicLintTest` caps any word that is not an ordinary sentence opener at **2%**
  of a locale's villager lines, with a per-locale list of what counts as ordinary — Portuguese gets a
  Portuguese list, not a translated English one.
- **One goodbye for everybody.** "Right you are." once stood fifty times. No exact sentence of two
  words or more may stand more than **twelve** times.

Both caps skip button labels, and the lint asks the dialogue files which keys are labels rather than
guessing from the key's shape. A label reading the same on every page is an affordance; two answers
with different consequences sharing one is the actual bug, and `answerLabelsAreUniqueWithinATopic`
catches that.

## Generated reports

`./gradlew build` leaves four documents in `build/libs/reports/`, all generated and all asserted
deterministic so they can be diffed against the previous release:

| File | What it is |
|---|---|
| `adjacency.md` | Every question node, every line that can open it, every button, every reaction and every onward page. This is the reading material for the §13.4 human review. |
| `adjacency.pt_br.md` | The same document rendered through the Portuguese lang file. Key parity is satisfied by a key existing; this is how you find out whether the Portuguese conversation reads. |
| `coverage.md` | The counts: contracts, per-topic depth against each topic's own target, personality overlay coverage, chat intents, profession profiles, locale key totals. |
| `uncontracted-routes.txt` | Every `say → next` route with no beat contract. Currently empty, and the ledger that keeps it that way is `src/test/resources/legacy_unverified_routes.txt`. |

## Debug commands for bug reports

The tracing tools stay in the shipped jar. A conversation bug is almost always "the wrong line
followed the right one", and that is very hard to report without them.

| Command | Permission | What it does |
|---|---|---|
| `/conversations chat debug <message>` | op | Scores `<message>` against every live intent and prints the ranking, the winning binding, and why anything close lost. This is how you find out whether a phrase failed to match or matched the wrong button. |
| `/conversations chat debug-ask <question> <answer>` | op | Drives the villager you are looking at straight to a `(question, answer)` pair and surfaces the reply in chat. Reproduces a specific adjacency without playing to it. |
| `/conversations chat status` | any | Whether chat mode is on for the server and for you. |
| `/conversations gossip list` / `clear` | op | The gossip log the news and rumours topics read from. |
| `/conversations compat townstead status` | op | What the Townstead binding actually resolved. |

When reporting a bad adjacency, the useful pair is the node id and the button name from
`build/libs/reports/adjacency.md` plus the output of `debug-ask` on them.

## Migration notes for pack authors

Nothing here breaks a third-party pack that only uses MCA's own dialogue vocabulary. These are the
conventions this mod's own content now follows, and which its lints will hold a contributed topic to.

- **Heart numbers are gone from branching content.** `hearts`, `hearts_min` and `hearts_max` still
  work — they are MCA's conditions, not ours — but our content asks `conversations_relationship` for
  a *band* instead: `stranger`, `acquaintance`, `friend`, `confidant`, `partner`, `family`, `tense`,
  `hostile`. The band thresholds were chosen so the old gates survive exactly: `hearts_max: 24` is
  `stranger`, `hearts_max: 59` is at-most-`acquaintance`, `hearts_min: 0` is
  at-least-`acquaintance`. `RelationshipBandLintTest` fails a raw heart number in our dialogue files,
  and `migrationPreservedBehaviour` walks −100…120 hearts asserting the equivalence.
- **Never use MCA's native `personality` condition.** It throws on an unknown id and takes the whole
  datapack reload down with it, and the id set changed between supported MCA versions. Use
  `conversations_personality`, which accepts a string or an array and scores zero on an id it does
  not know. `contentNeverUsesMcasCrashProneNativePersonalityCondition` enforces this.
- **Optional-mod professions live in that mod's own file.** A profession from Ars Nouveau, Farmer's
  Delight, Ice and Fire, More Villagers, Vampirism or Werewolves is authored in `work_<owner>.json`,
  and no optional mod appears in `mods.toml`. `OptionalProfessionIsolationTest` fails a mixed file,
  so an install without that mod loads exactly the content it can use.
- **Beat `subject` ids were renamed** in the 1.2.0 depth pass, from per-topic blanket names to what
  each beat is about. Only reply contracts using `subject:` patterns in `responds_to` read them, and
  the corpus uses exactly one such pattern; if your pack matches on a subject id, check it against
  the current names.
- **Every reply button needs a chat intent.** `ChatCoverageTest` fails a non-exit answer with no
  intent, and an intent naming a button that no longer exists. Menus (`conversations`,
  `conversations.cat.*`, `conversations.family`, `conversations.us`) and `back` are exempt: chat mode
  jumps straight to a question-and-answer pair rather than walking the menus, and "never mind" is the
  way out.

## Conventions for content that degrades gracefully

- Every result that uses a `conversations_*` action should carry a
  `{"chance": -2000, "conversations_disabled": "<feature>"}` sink (or an alternative plain-MCA result
  must exist on the same answer) so disabling a feature falls back instead of going silent.
- Don't add answers to MCA's `auto` questions (`root`, `chat`, `rumors`) — auto questions pick a
  random answer, so merging in a new one changes MCA's own behavior odds.
- Extension files must mirror the original question's top-level flags (see `main.json`).
