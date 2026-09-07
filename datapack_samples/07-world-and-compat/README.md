# 07 — world and compat (`sample_almanac`)

One new starter, **"Anything worth marking down?"**, merged into the mod's `conversations.cat.events`
page. It is a villager's almanac: the crown, your standing, an errand, the village's gossip, the
festival, the sky, the season — whichever of those the world can actually supply today.

The point of the pack is not the content. It is that **every one of those branches can be missing**,
and the answer still says something a person would say. Four optional mods, six config switches and
one indifferent Tuesday all have to produce a sentence.

---

## What it demonstrates

- `conversations_weather` (`clear` / `rain` / `storm`), `conversations_season`
  (`spring` / `summer` / `autumn` / `winter`) and `conversations_holiday`
  (`spring_bloom` / `midsummer` / `harvest_festival` / `midwinter` / `none`) as three separate
  branches with three separate `conversations_disabled` sinks.
- `conversations_gossip` as the **condition** paired with `conversations_gossip_say` as the
  **action** — the same query written twice, which is the only way the two agree.
- `conversations_context` reading `capital.*` fields, with an explicit `unknown` policy on every
  single condition.
- `conversations_quest_available` / `_active` / `_ready` / `_completed` — this pack uses `_ready` and
  `_active`.
- `conversations_reputation` (tier bands) and `conversations_reputation_incident` (open incidents),
  used both as a positive gate and as a negative sink.
- Two `conversation_scenes` entries using the `standing_remark` and `court_remark` purposes.
- `conversations_say` `vars` with `capital_name`, `sovereign_title`, `village_name`, `holiday`,
  `weather` and `season`, and exactly which `%N$s` each one lands on.
- The rule that **no optional mod appears in `mods.toml`**, and why a condition naming an absent mod
  is a silent zero rather than a crash.

---

## Install

A pack folder can hold both halves, but Minecraft only ever serves `data/` from a world's datapack
folder and `assets/` from a resource pack folder. **Copy this folder into both places:**

```
saves/<world>/datapacks/07-world-and-compat/     -> supplies data/sample_almanac/...
resourcepacks/07-world-and-compat/               -> supplies assets/sample_almanac/lang/en_us.json
```

Then in game:

```
/reload                     — picks up data/ (dialogue, catalog, beats, scenes, chat intents)
F3+T (or the resource pack screen)   — picks up assets/ (the lines and the button labels)
```

If you install only the datapack half, every line renders as a raw key
(`dialogue.almanac.plain`) — that is the symptom of a missing resource pack half, not a broken
dialogue file.

`pack_format` is **15**, which is 1.20.1 for both pack kinds.

---

## Try it

Talk to any adult or child villager → **Chat** (or the Conversations button, depending on
`hubEntryMode`) → **Events** → **"Anything worth marking down?"**.

Useful proof, in rising order of cost:

```
/conversations context snapshot
```
Prints every context field and its value for the villager you are looking at. If `capital.present`
reads *unavailable* rather than `false`, the court branch is being skipped by its `"unknown": "fail"`
policy — which is the intended behaviour without MCA Capitals, and is a different fact from
`capital.present = false`.

```
/conversations context capabilities
```
Which context fields this install can answer at all. The 26 `capital.*` fields appear here only with
MCA Capitals installed and `[capitals] enabled = true`.

```
/conversations gossip list
```
Shows the village events waiting to be told. If this is empty, the gossip branch cannot score, and
you will get one of the other branches. `/conversations gossip clear` resets the "already told"
marks so you can re-test.

```
/conversations scene plan
/conversations scene candidates almanac
```
Why a scene was or was not chosen for this villager right now — this is how you check whether
`sample_almanac.standing.tier_changed` or `sample_almanac.court.title_changed` was eligible.

```
/conversations chat debug anything worth marking down
/conversations chat debug-ask conversations.cat.events almanac
```
The first scores your sentence against every loaded intent (yours and the mod's) and prints the
winner. The second drives the villager you are looking at straight to that question/answer pair,
which is the fastest way to reach a branch you cannot get the world to produce.

To force particular branches: `/weather thunder` for the storm line, `/time set` past a festival day
for the holiday line, and a village event (a birth, a marriage, a villager moving in) for the gossip
line.

---

## File-by-file walkthrough

### `pack.mcmeta`
`pack_format: 15`, a description, nothing else. The same file serves as the datapack and the resource
pack manifest.

### `data/sample_almanac/dialogues/conversations.cat.events.json`

The one file in this pack whose **basename is not ours**. MCA loads any
`data/*/dialogues/<name>.json` and keys it by the *basename*, so this file's single `almanac` answer
is appended to the mod's own `conversations.cat.events` answers. The shipped file carries no
top-level flags (`auto`, `silent`), so this one carries none either: MCA keeps the flags of whichever
same-named file loads last, and that order is undefined.

`"constraints": "!toddler,!baby"` hides the button on the very young. Constraints are AND-only and
`!` negates, so this reads "not a toddler and not a baby".

Ten results, **ideal first, fallback last**, because MCA subtracts weights as it walks the list and
falls off the end onto the *last* result when nothing scored positive. In order:

| # | Branch | Positive gate | Sinks | Route |
|---|---|---|---|---|
| 1 | court remark | `capital.title_changed` (900) + `capital.present` (200) | `capitals`, `capital_topics`, `branching`, `topics` | `almanac.court.respond` |
| 2 | standing, high | `conversations_reputation {min_tier: honored}` (700) | an open `crime` incident, `branching`, `topics` | `almanac.standing.respond` |
| 3 | standing, wary | open `crime` incident (700) + `max_tier: wary` (200) | `branching`, `topics` | `almanac.standing.wary.respond` |
| 4 | quest | `conversations_quest_ready` (600) + `_active` (200) | `quests`, `branching`, `topics` | `almanac.quest.respond` |
| 5 | gossip | `conversations_gossip` (500) | `gossip`, `branching`, `topics` | `almanac.gossip.respond` |
| 6 | festival | any of the four `conversations_holiday` buckets (300 each) | cooldown memory, `holidays`, `branching`, `topics` | `almanac.world.respond` |
| 7 | weather | `storm` 250 / `rain` 180 / `clear` 60 | cooldown memory, `world`, `branching`, `topics` | `almanac.world.respond` |
| 8 | season | any of the four seasons (120 each) | cooldown memory, `seasons`, `branching`, `topics` | `almanac.world.respond` |
| 9 | plain branching | `conversations_enabled: branching` (40) | `topics` | `almanac.plain.respond` |
| 10 | legacy one-liner | `baseChance: 1`, no conditions | none | back to `conversations.cat.events` |

Things worth reading closely:

- **Every gated result carries `{"chance": -2000, "conversations_disabled": "<feature>"}`.** The
  feature ids used here are `capitals`, `capital_topics`, `quests`, `gossip`, `world` (weather),
  `seasons`, `holidays`, `branching` and `topics`. A sink of −2000 is large enough that no
  combination of positive conditions can climb back out of it.
- **The weather, season and festival branches are three results, not one.** They are gated by three
  different config switches (`enableWeatherLines`, `enableSeasonLines`, `enableHolidayLines`), and one
  result cannot carry three independent kill switches without dying when any single one is off.
- **The festival branch outscores the season branch** (300 vs 120) rather than sinking it. Selection
  is weighted-random over positive totals, not highest-wins, so a festival day still lands on the
  season line about a quarter of the time. That is a deliberate choice here — both lines are true on
  that day. If you want a hard preference, sink the season result on all four holiday buckets, which
  is what the mod's own `season` starter does.
- **The two reputation branches are mutually exclusive by construction.** The high branch sinks
  −2000 on an open `crime` incident that this villager knows about; the wary branch scores 700 on
  exactly that. They cannot both be positive.
- **The cooldown memory** `sample_almanac.cooldown.almanac` is written by every branch with
  `"time": 36000` (1.5 MC days) and is read as a −1000 sink by the three world branches only. Asking
  twice in a day therefore stops producing weather chat and starts producing the plain line. The id
  carries the pack's namespace: the mod owns `mcaconversations.*` and this pack owns
  `sample_almanac.*`.
- **Result 10 uses MCA's own `say`, not `conversations_say`, and no session action at all.** It is
  the one result that must work when this mod's features are all switched off, so it uses nothing but
  base MCA vocabulary. Note the key order: `next` first, then `say`. Actions run in JSON key order
  and `next` overwrites the screen's speech slot, so a `say` written above `next` is never read.

### `data/sample_almanac/conversation_catalog/sample_almanac.json`

One topic, `almanac`. `conversations_session {"op": "begin"}` requires a catalogued topic id, so this
is where the id is declared — once, where a typo cannot hide. `depth: standard` sets the
per-conversation heart budget (+4/−5) for anything reached from this topic;
`required_stance_families` lists the kinds of thing a player can say back, and **must include
`exit`** — a node with no graceful way out is a content bug.

### `data/sample_almanac/dialogues/almanac.*.respond.json` (seven pages)

Every page has the same shape: two answers that do something and one `leave` that costs nothing.
Actions are ordered **state → `next` → `say`** in all of them:

```
conversations_session (turn)  ->  conversations_affection_apply  ->
conversations_disposition_apply  ->  next  ->  conversations_say
```

`conversations_affection_apply` appears on four buttons only (`congratulate`, `thank_them`,
`own_it`, `offer_help`), always with a stable `decision` id and an explicit `policy`. It is the only
route to a heart change in branching content; the disposition applies move the internal vector, which
is never a number the player sees.

The `leave` answer on every page ends the session (`{"op": "end"}`); the other two record a turn and
leave it open. All three, `leave` included, set `next` to `conversations.cat.events`, the topic's
`return_question`, so every button lands the player back on the events page. Their beats are
terminal, which is what makes the exchange two turns long rather than four.

Why **seven** pages and not one: MCA decides which buttons to show from the *answer's* own
constraints and never looks at which line led there. So "How you are spoken of" and "What is being
said" have to be different questions — otherwise "They are not wrong" would appear under a line
praising you. The wary opener therefore gets `almanac.standing.wary.respond` of its own. A rebuff
never routes into a page that assumes the stance landed.

The three world openers (weather, season, festival) **do** share `almanac.world.respond`, because
their two buttons — "Feels that way to me too" and "What will you do about it?" — are true of all
three lines. A player label may only reference detail present in *every* variant of *every* line that
can open the page.

### `data/sample_almanac/conversation_beats/sample_almanac.json`

The semantic contract. Eight opener beats (one per branch that names a beat) and one terminal beat
per reply, plus a `replies` block keyed `"<question>/<answer>"`.

- `responds_to` names the beats a reply may answer. The build's job is to catch a button that can
  appear under a line it does not answer.
- The **gossip page's replies use `responds_to: ["*"]`**. The gossip line is chosen at runtime by
  `conversations_gossip_say` out of whichever village event is untold, so there is no single beat to
  name. That is exactly what the wildcard contract is for; the cost is that its two replies must be
  true of a wedding, a birth and a newcomer alike, which is why they are "Who told you?" and "I will
  keep that to myself."
- The gossip starter is also the one branch whose `conversations_session` has **no `beat`** — there
  is nothing to name yet. `beat` is required for `turn`, optional for `begin`.

### `data/sample_almanac/conversation_scenes/sample_almanac.json`

Two unprompted routes — a scene is *not* dialogue. It names a question and an opening beat that
already exist in this pack, and everything else in it is the rule for when that route is right.

| Scene | Purpose | Interruption cost | Needs |
|---|---|---|---|
| `sample_almanac.court.title_changed` | `court_remark` | 3 | **MCA Capitals**; the purpose only exists with it loaded |
| `sample_almanac.standing.tier_changed` | `standing_remark` | 3 | MCA: Reputation to ever fire |

Interruptions are one per villager per day unless the player starts the conversation, and each
purpose spends part of that budget — `court_remark` and `standing_remark` cost 3 apiece, against
`low_stakes` at 8 and `resume` at 2. Two remarks in a day is the practical ceiling, and that is the
point: a villager who comments on your reputation every morning is a notification, not a person.

Every condition carries an explicit `unknown` policy, and both scenes use `"fail"`. The four
policies are not interchangeable:

- `fail` — ineligible when the field cannot be read. *"I cannot tell, so I will not say it."*
- `neutral` — contributes nothing, and is the only policy that **matches** while unknown.
- `fallback` — degrade to the scene's declared `fallback` route.
- `error` — fail loudly. Authoring only; never ship it.

Without MCA Capitals, `capital.present` is *unreadable*, not `false`. With `"unknown": "fail"` the
court scene simply never becomes a candidate. Written with `"unknown": "neutral"` it would become
eligible on every villager in a world with no capitals at all, and would then speak a line about a
court that does not exist.

Scene ids are also the one catalog whose overrides are deterministic — the scene loader sorts its
inputs by `ResourceLocation` before folding them. `chat_intents`, `conversation_catalog` and
`conversation_beats` fold in whatever order the resource manager hands over, so redefining one of
*those* ids from another pack is a race. Ours are all newly named under the pack's prefix, so nothing
here relies on winning that race.

### `data/sample_almanac/chat_intents/sample_almanac.json`

One intent per non-exit button, bound to its exact `question` + `answer`, plus one for the starter
itself. `leave` gets none on purpose: chat mode's own "never mind" is the way out.

`context` scopes an intent to a page that is actually open, so "Who told you?" only competes while
the gossip page is showing. Keywords are authored as surface words — the loader stems and
synonym-canonicalises them with the same normaliser the player's typed message goes through.
`requiresAny` is the evidence floor: without at least one of those words present, the intent does not
score at all, which is what stops "I will keep that to myself" from winning on the word "that".

Answer-level `constraints` from the dialogue file (`!toddler,!baby` on the starter) are enforced
automatically at match time. Do not repeat them in the intent.

### `assets/sample_almanac/lang/en_us.json`

Button labels (`dialogue.<question>.<answer>`), page headers (`dialogue.<question>`) and villager
lines (`dialogue.<phrase>`), every say pool with three variants `/1 /2 /3`.

Translation keys are a **single global map across every asset namespace**, so this file does not need
to be `assets/mca_dialogue/lang/en_us.json` to work — and, by the same token, a key you define that
someone else also defines is a silent collision. Everything here is under `almanac.*` for that
reason.

---

## Template variables, and which `%N$s` each one is

MCA prepends the player's name as **`%1$s`** in every dialogue line, always. Your `vars` fill
`%2$s`, `%3$s`, … **in the order you list them**, and `slots` (unused in this pack) come after the
vars.

| Say key | `vars` | `%2$s` | `%3$s` |
|---|---|---|---|
| `almanac.court.title_changed` | `["capital_name", "sovereign_title"]` | the capital's name | "King" / "Queen" |
| `almanac.standing.honored` | `["village_name"]` | the village's name | — |
| `almanac.standing.wary` | `["village_name"]` | the village's name | — |
| `almanac.holiday` | `["holiday"]` | "the harvest festival", "midwinter", … | — |
| `almanac.weather` | `["weather"]` | "the rain", "the storm", "the clear sky" | — |
| `almanac.season` | `["season"]` | "spring", "the height of summer", … | — |

Note the shape of the lines these fill. `holiday`, `weather` and `season` all resolve to phrases that
carry their own article, so the sentence must not supply one: "It is %2$s", never "It is the %2$s".
The same discipline applies to slot tokens.

Gossip lines are different — they are not `conversations_say` and take no `vars`.
`conversations_gossip_say` fills them from the event: **`%2$s` is subject A** (for a marriage, one of
the couple; for a birth, the baby; for an arrival, the newcomer) and **`%3$s` is subject B or the
event detail**, which is empty for births and arrivals. That is why
`dialogue.almanac.gossip.birth/*` never mentions `%3$s`: a line that referenced it would render a
blank.

An unresolvable var falls back to neutral text ("someone", "the village"), so a line never breaks —
it just stops being specific. That is also what `enableTemplates = false` does to all of them.

---

## What a player actually sees

This is the table the pack exists to make true. In each case, the starter still produces a sentence
and a way back to the Events page.

| Situation | What happens |
|---|---|
| **No optional mods at all** (plain MCA + Conversations) | Results 1–4 score 0 — the capital fields are unreadable and the quest/reputation conditions return 0. The player gets gossip, festival, weather, season or the plain line, whichever the world can supply. Nothing is missing from the menu and nothing logs an error. |
| MCA Capitals absent | No court branch, and `sample_almanac.court.title_changed` is never a scene candidate. |
| MCA Capitals present, `[capitals] enabled = false` | Same as absent, via the `conversations_disabled: capitals` sink instead of via the unreadable field. |
| MCA Capitals present, `topicsEnabled = false` | The court branch is sunk by `capital_topics`; capital *news* still reaches the gossip branch if `newsEnabled` is on. |
| MCA: Reputation absent | Both standing branches score 0. There is no `[reputation]` config block to turn off — the integration is gated purely on the mod being installed. |
| MCA: Quests absent, or `enableQuests = false` | The quest branch scores 0 either way. |
| `enableGossip = false` | The gossip branch is sunk. Nothing is detected or told, so no event could have matched anyway. |
| `enableWeatherLines = false` | The weather branch is sunk (`world`) and the `weather` var would render neutrally. |
| `enableSeasonLines = false` | The season branch is sunk (`seasons`). Serene Seasons, if installed, is what the season would have come from. |
| `enableHolidayLines = false` | The festival branch is sunk (`holidays`); ordinary days were never gated on it. |
| `enableBranching = false` | Results 1–9 are all sunk. Result 10, the legacy one-liner, fires: one line, back to the Events page, no tree. |
| `enableTopics = false` | Same — result 10 again. |
| `enableTemplates = false` | Branches still fire; `%2$s` renders as "the village", "someone" and so on. |
| **Every switch off at once** | Result 10. One line, one hop back to the category. That is the whole degradation story. |

### Why no optional mod appears in `mods.toml`

This pack ships no `mods.toml` at all — datapacks do not have one — but the rule it inherits from the
mod is worth stating, because it is what makes the table above possible. **A declared dependency,
even `mandatory = false`, is a claim about a mod that is not needed to load.** Conversations
registers `conversations_quest_*`, `conversations_reputation*` and the `capital.*` context fields
*unconditionally*, whether or not the supplying mod is present, and they score `0` when it is not.
Registered-and-zero is what lets the authored fallback fire. The alternative — leaving the key
unregistered on an install without the mod — would make this very file an unknown-key error on a
plain MCA setup.

So: a condition naming an absent mod simply never matches. It is not a crash, not a warning, and not
something to guard with a dependency declaration.

---

## The rules this pack is obeying

1. **Fallback last.** MCA walks the results subtracting weights and runs off the end when nothing is
   positive, landing on the final element. Result 10 is the safety net; written first it would be a
   coin flip.
2. **Selection is weighted-random over positive totals, not highest-wins.** Every branch that must
   not fire is *sunk* to a negative total, not merely outscored.
3. **Actions run in JSON key order and `say` must come after `next`.** `next` writes the destination
   prompt into the one speech slot; a `say` above it is overwritten and never read.
4. **Every `conversations_*` result carries a `conversations_disabled` sink**, or the answer has a
   plain-MCA alternative. This answer has both.
5. **Hearts move only through `conversations_affection_apply`,** with a stable `decision` id and an
   explicit `policy`. Openers grant nothing at all; a cooldown memory is not a reward.
6. **Every node offers 2–5 answers and a consequence-free way out.**
7. **Button labels are what the player says.** Never "Persuade", never "+2 Warmth", never a number.
8. **Every `conversations_context` and scene condition states an `unknown` policy.** "Not raining"
   and "nothing could tell me" are different facts.
9. **Ids are namespaced** — `sample_almanac.*` for memories and scenes, `almanac.*` for questions,
   beats and lang keys. The mod owns `mcaconversations.*`.
10. **No answers added to MCA's own `auto` questions** (`root`, `chat`, `rumors`).

---

## How to adapt it

- **Change the trigger set, not the shape.** Delete any of results 1–8 and the rest still work; the
  only load-bearing element is result 10.
- **Add a branch** by copying result 7 (the simplest gated one), changing the condition, the
  `branch`, the `beat` and the say key, and adding the beat plus three lang variants. Put it
  *above* result 10.
- **Make the world branches stricter** by adding the four `conversations_holiday` sinks to the season
  result, as the mod's own season starter does.
- **Point the scenes at your own routes** by editing `route.question` and `route.opening_beat`. Both
  must already exist and be contracted; a scene never introduces dialogue of its own.
- **Widen chat matching without touching intents** by shipping a synonyms-only file:
  `{"synonyms": {"almanac": ["diary", "ledger"]}}`. Synonym blocks merge across every pack before any
  intent parses, first writer wins per alias.

## What this pack deliberately does not do

- **No `conversations_reputation_signal`.** The action exists and records an authored outcome as a
  public deed, but it must name an **incident definition supplied by MCA: Reputation** — it never
  accepts a raw score delta, which is precisely what stops a datapack farming standing. Guessing an
  incident id here would have taught the wrong lesson; look the ids up in your MCA: Reputation build.
- **No `conversations_quest_open`.** Same reason: it names a quest, and this pack has none.
- **No episodes, threads, commitments, identity tokens or culture.** Those are packs 04, 05 and 06.
- **No `min_variants`, no personality overlays.** Pack 03 covers overlays and the prefixed-key rule.
- **It does not redefine any shipped id.** Everything is new and namespaced, so installing this pack
  cannot change how the mod's own content behaves.
- **It has not been run in a production MCA instance.** It is authored against the shipped schemas,
  the documented engine rules and the mod's own content as it stands; treat the "Try it" section as
  the test you run, not as a test that has been run.
