# MCA: Conversations — Datapack & Dialogue JSON Reference

Conversations's dialogue is ordinary MCA Reborn dialogue JSON, loaded by MCA itself from
`data/<any-namespace>/dialogues/<question>.json`. Datapack authors can extend or override it, and
can use this mod's custom conditions/actions in their own packs. Everything below is verified
against MCA Reborn 7.6.23.

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
    mayor, monarch` · `personality`: `athletic, confident, friendly, flirty, witty, shy, gloomy,
    sensitive, greedy, odd, lazy, grumpy, peppy`
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
`mcaconversations.state.*` (`grateful`), `mcaconversations.unlock.*` (`opened_up`, `confided`),
`mcaconversations.greet.today`, `mcaconversations.gossip.<eventUuid>`. Third-party packs building on these
flags may read them freely; write your own ids under your own prefix.

## Custom conditions (usable in any dialogue/gift JSON once this mod is installed)

| Key | Value | Meaning |
|---|---|---|
| `conversations_enabled` | `"topics" \| "states" \| "templates" \| "gossip"` | 1 when that config feature is on, else 0 |
| `conversations_disabled` | same | inverse — pair with a large negative `chance` as a kill-switch |
| `conversations_gossip` | `{"types": ["marriage","divorce","death","birth"]?, "max_age": <ticks>?}` | 1 when the villager's home village has an event matching the filter that this villager hasn't told this player (defaults: all types, 72000 ticks) |

## Custom actions

| Key | Value | Effect |
|---|---|---|
| `conversations_record` | one `{"id", "var"?, "time"?}` or an array of them | extra `remember` writes (JSON keys can't repeat, so use this when a result needs several) |
| `conversations_say` | `{"phrase": "<key>", "vars": ["villager_name", ...]?}` | says `dialogue.<phrase>` in the dialogue screen with template args |
| `conversations_gossip_say` | `{"types"?, "max_age"?, "phrase_prefix"?}` | tells the next untold event (same query rules as the condition) using `dialogue.<prefix>.<type>` (default prefix `conversations.gossip`), then marks it told for this villager+player |

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

Gossip lines receive `%2$s` = subject A's name, `%3$s` = subject B's name (empty for
single-subject events like deaths).

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

## The Chat redirect (v0.2.0)

While `replaceChatWithConversations` is on (default), **every** `next: "chat"` hop — MCA's Chat button,
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

To restore a separate menu button instead (v0.1.0 style), set the config to `false` and ship a
datapack file `data/<yourpack>/dialogues/main.json`:

```json
{ "silent": true, "answers": [ { "name": "conversations",
    "results": [ { "baseChance": 1, "actions": { "next": "conversations" } } ] } ] }
```

(plus a lang entry `dialogue.main.conversations` for the button label).

## Extending profession work-talk

`data/mcaconversations/dialogues/conversations.work.json` is an `auto` question whose results are scored per
profession — third-party packs can merge additional profession results into it (same-basename
merge) with `{"chance": 100, "profession": "yourmod:yourprofession"}` and their own say keys.
Conditions naming professions from uninstalled mods never match and never crash. Professions with
no hand-written result fall through to the generic templated line (`profession_name` var).

## Conventions for content that degrades gracefully

- Every result that uses a `conversations_*` action should carry a
  `{"chance": -2000, "conversations_disabled": "<feature>"}` sink (or an alternative plain-MCA result
  must exist on the same answer) so disabling a feature falls back instead of going silent.
- Don't add answers to MCA's `auto` questions (`root`, `chat`, `rumors`) — auto questions pick a
  random answer, so merging in a new one changes MCA's own behavior odds.
- Extension files must mirror the original question's top-level flags (see `main.json`).
