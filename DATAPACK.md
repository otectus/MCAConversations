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
| `conversations_gossip` | `{"types": ["marriage","divorce","death","birth","arrival","departure"]?, "max_age": <ticks>?}` | 1 when the villager's home village has an event matching the filter that this villager hasn't told this player (defaults: all types, 72000 ticks) |
| `conversations_weather` | `{"is": "clear" \| "rain" \| "storm"}` | 1 when the current sky in the villager's level matches (storm outranks rain outranks clear); 0 when `enableWeatherLines` is off |
| `conversations_season` | `{"is": "spring" \| "summer" \| "autumn" \| "winter"}` | 1 when the current season matches — read from Serene Seasons if installed, else the calendar season from the world day; 0 when `enableSeasonLines` is off |
| `conversations_holiday` | `{"is": "spring_bloom" \| "midsummer" \| "harvest_festival" \| "midwinter" \| "none"}` | 1 when the current calendar festival matches (`none` = an ordinary day); 0 when `enableHolidayLines` is off |
| `conversations_personality` | `"odd"` or `["odd","playful"]` | 1 when the villager's personality is one of these. **Use this instead of MCA's native `personality`** — that one throws on an id the running MCA does not know and takes the datapack reload (and world load) down with it. This one never throws, and resolves 7.6 ids to their 7.7 successors so one authored value works on both MCA versions |
| `conversations_disposition` | `{"axis": "trust" \| "respect" \| "warmth" \| "attraction" \| "tension" \| "familiarity", "min"?, "max"?}` | 1 while the decayed disposition axis lies in the inclusive range (bounds default to the axis limits). **Never matches** when `enableDispositions` is off (author a fallback result) or on `attraction` for a romance-ineligible target |
| `conversations_check` | `{"id": "<topic.stance>", "tier": "crit" \| "success" \| "partial" \| "rebuff", "axis", "difficulty": 0–100}` | 1 when the seeded check resolver lands on this result's declared tier — see *Dialogue checks* below. All four tier results of a stance share id/axis/difficulty |

## Custom actions

| Key | Value | Effect |
|---|---|---|
| `conversations_record` | one `{"id", "var"?, "time"?}` or an array of them | extra `remember` writes (JSON keys can't repeat, so use this when a result needs several) |
| `conversations_say` | `{"phrase": "<key>", "vars": ["villager_name", ...]?}` | says `dialogue.<phrase>` in the dialogue screen with template args |
| `conversations_gossip_say` | `{"types"?, "max_age"?, "phrase_prefix"?}` | tells the next untold event (same query rules as the condition) using `dialogue.<prefix>.<type>` (default prefix `conversations.gossip`), then marks it told for this villager+player |
| `conversations_disposition_apply` | `{"topic": "<topic.stance>", "deltas": {"<axis>": ±N}}` | moves disposition axes through the farming guards (per-axis \|delta\| ≤ 10 at parse; per-day cap and same-day repeat diminishing at apply). No-op when `enableDispositions` is off; `attraction` deltas are dropped for romance-ineligible targets |

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

## Conventions for content that degrades gracefully

- Every result that uses a `conversations_*` action should carry a
  `{"chance": -2000, "conversations_disabled": "<feature>"}` sink (or an alternative plain-MCA result
  must exist on the same answer) so disabling a feature falls back instead of going silent.
- Don't add answers to MCA's `auto` questions (`root`, `chat`, `rumors`) — auto questions pick a
  random answer, so merging in a new one changes MCA's own behavior odds.
- Extension files must mirror the original question's top-level flags (see `main.json`).
