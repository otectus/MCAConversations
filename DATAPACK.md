# MCA: Real Talk — Datapack & Dialogue JSON Reference

Real Talk's dialogue is ordinary MCA Reborn dialogue JSON, loaded by MCA itself from
`data/<any-namespace>/dialogues/<question>.json`. Datapack authors can extend or override it, and
can use this mod's custom conditions/actions in their own packs. Everything below is verified
against MCA Reborn 7.6.23.

## How MCA loads dialogue (the parts that matter)

- Every `data/*/dialogues/<name>.json` containing an `"answers"` array is loaded; the **question
  name is the file basename** (namespace-independent, so new questions should carry a unique
  prefix — this mod uses `realtalk.*`).
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

Real Talk's per-topic grammar combines them: a permanent `mcarealtalk.topic.<t>` flag ("ever
asked"), an expiring `mcarealtalk.cooldown.<t>` flag ("asked recently"), and results for
first-time / asked-again / revisit built from has/lacks gates plus negative sinks.

**Memory id namespace** (all ids this mod writes): `mcarealtalk.topic.*`, `mcarealtalk.cooldown.*`,
`mcarealtalk.state.*` (`grateful`), `mcarealtalk.unlock.*` (`opened_up`, `confided`),
`mcarealtalk.greet.today`, `mcarealtalk.gossip.<eventUuid>`. Third-party packs building on these
flags may read them freely; write your own ids under your own prefix.

## Custom conditions (usable in any dialogue/gift JSON once this mod is installed)

| Key | Value | Meaning |
|---|---|---|
| `realtalk_enabled` | `"topics" \| "states" \| "templates" \| "gossip"` | 1 when that config feature is on, else 0 |
| `realtalk_disabled` | same | inverse — pair with a large negative `chance` as a kill-switch |
| `realtalk_gossip` | `{"types": ["marriage","divorce","death","birth"]?, "max_age": <ticks>?}` | 1 when the villager's home village has an event matching the filter that this villager hasn't told this player (defaults: all types, 72000 ticks) |

## Custom actions

| Key | Value | Effect |
|---|---|---|
| `realtalk_record` | one `{"id", "var"?, "time"?}` or an array of them | extra `remember` writes (JSON keys can't repeat, so use this when a result needs several) |
| `realtalk_say` | `{"phrase": "<key>", "vars": ["villager_name", ...]?}` | says `dialogue.<phrase>` in the dialogue screen with template args |
| `realtalk_gossip_say` | `{"types"?, "max_age"?, "phrase_prefix"?}` | tells the next untold event (same query rules as the condition) using `dialogue.<prefix>.<type>` (default prefix `realtalk.gossip`), then marks it told for this villager+player |

### Template variables (`realtalk_say` / gossip lines)

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

Gossip lines receive `%2$s` = subject A's name, `%3$s` = subject B's name (empty for
single-subject events like deaths).

## Conventions for content that degrades gracefully

- Every result that uses a `realtalk_*` action should carry a
  `{"chance": -2000, "realtalk_disabled": "<feature>"}` sink (or an alternative plain-MCA result
  must exist on the same answer) so disabling a feature falls back instead of going silent.
- Don't add answers to MCA's `auto` questions (`root`, `chat`, `rumors`) — auto questions pick a
  random answer, so merging in a new one changes MCA's own behavior odds.
- Extension files must mirror the original question's top-level flags (see `main.json`).
