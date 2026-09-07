# 03 — Checks and dispositions

Namespace `sample_courage`, question prefix `courage`, topic id `courage`.

A **deep** topic: the villager admits there is something they keep not doing, and the player's
answer is put through a **dialogue check**. This is the pack that teaches the one shape you cannot
get wrong, because getting it wrong is invisible — MCA picks a result by *weighted random over
positive totals*, not highest-wins, so a checked answer that leaves two results positive quietly
plays the wrong one some fraction of the time and looks like flaky writing.

---

## What it demonstrates

- The **canonical checked-stance shape**: a cost-free guard result below the gate, one result per
  tier (`crit` / `success` / `partial` / `rebuff`) sharing one `id`, `axis` and `difficulty`, and a
  plain fallback **last** that fires only while checks are switched off.
- The two sinks every tier result carries: `-2000 conversations_disabled: "checks"` and a
  `-1000` sink that kills it below the gate.
- Gating on **`conversations_relationship` bands**, never on a heart number.
- `conversations_disposition_apply` moving `trust` / `respect` / `warmth` / `tension` /
  `familiarity` as the *consequence* of a stance, while hearts move only through
  `conversations_affection_apply`.
- A rebuff routing to a **rebuff-aware close page** (`courage.close.rebuffed`) instead of one that
  assumes the stance landed.
- An **interiority** file: per-personality resting baselines and stance bias.
- Two **personality overlay** lang files showing the prefixed-key rule
  (`gloomy.dialogue.…`, `confident.dialogue.…`).
- Beat + reply contracts, and one chat intent per non-exit button.

---

## Install

A pack folder like this one has **two halves**, and Minecraft loads them from two different places:

| Half | What is in it | Where it goes |
|---|---|---|
| data | `data/sample_courage/**` | `saves/<world>/datapacks/03-checks-and-dispositions/` |
| assets | `assets/sample_courage/**`, `assets/mca_dialogue_*/**` | `resourcepacks/03-checks-and-dispositions/` |

Copy the **whole folder to both places**. Minecraft only serves `data/` out of a datapack and only
serves `assets/` out of a resource pack; the extra directory in each copy is ignored. (Inside a mod
jar the split does not exist — one jar carries both.)

Then:

```
/reload                     # picks up data/: dialogues, beats, catalog, intents, interiority
F3+T  (or reselect the pack) # picks up assets/: the lang files
```

`pack.mcmeta` declares `"pack_format": 15`, which is 1.20.1 for **both** pack kinds.

Lang files **merge**: `assets/sample_courage/lang/en_us.json` adds `dialogue.*` keys to the same
global map `assets/mca_dialogue/lang/en_us.json` fills. You never need to edit the mod's own lang
file to add lines. The flip side is that a key two packs both define is a silent collision — hence
the `courage.` prefix on every key here.

---

## Try it

1. Give yourself a villager to talk to and check where you stand:
   ```
   /conversations context snapshot
   ```
   Look at `player.relationship_band`. Below `friend` you will get the **guard** reply; at `friend`
   or above the check runs.
2. Open the topic: talk to the villager → **"Let's talk properly"** (MCA's **Chat** button instead,
   under `hubEntryMode = REPLACE`) → *Personal* → “Is there something you keep putting off?”
3. Press **“You could do it. I'd not let you go at it alone.”** and read what comes back.
4. Drive the same button from chat mode and watch the intent score:
   ```
   /conversations chat debug you could do it, you would not be alone
   /conversations chat debug-ask courage.respond steady
   ```
5. Prove the roll is **seeded**, not rolled per click: close the dialogue screen, reopen it, take
   the same stance again. You get the same tier. Sleep or wait out a half-day time bucket and it can
   change. That is deliberate — a rebuff cannot be re-rolled into a crit by clicking away.
6. Turn the machinery off, one switch at a time, in `config/mcaconversations-*.toml`, and confirm the
   answer still produces exactly one line:
   - `enableCheckTiers = false` → crit collapses into success, partial into rebuff.
   - `enableChecks = false` → the plain fallback fires.
   - `enableDispositions = false` → the check runs on hearts alone; the relationship gate is
     unaffected, because relationship bands are not part of the disposition vector.

---

## File-by-file walkthrough

### `pack.mcmeta`
`{"pack": {"pack_format": 15, "description": "…"}}`. Nothing else. Format 15 is 1.20.1.

### `data/sample_courage/dialogues/conversations.cat.personal.json` — the starter
The basename is **exactly** the mod's category page, because that is the extension mechanism: MCA
loads every `data/*/dialogues/<name>.json` and **merges the `answers` arrays** of files sharing a
basename. This file contains one answer, `courage`, and nothing else — it does not restate the
mod's own twelve answers (the eleven topic starters plus `back`) and must not, or it would fight
them.

Category pages carry **no top-level flags**, so this extension carries none either. (For a question
that does have flags — `main.json` has `"silent": true` — an extension file has to mirror them,
because MCA keeps the flags of whichever same-named file loads last and that order is undefined.)

`"constraints": "!baby,!toddler"` hides the button for the ages that have no lines here.
Constraints are a comma-separated, AND-only token list; `!` negates. That is narrower than the
catalog row, which lists `"ages": ["teen", "adult"]`: babies and toddlers are kept out, but children
are still offered the button, because there is no `child` token to write. The closing section of this
README is about that gap.

Three results, ordered ideal-first and **fallback last**:

| # | Fires when | Opener |
|---|---|---|
| 1 | the cooldown memory is live | `courage.open.again` |
| 2 | the band is below `friend` | `courage.open.wary` |
| 3 | otherwise (fallback, last) | `courage.open.first` |

Each earlier condition is repeated as a negative sink on the later results, so **exactly one** has
positive weight:

- cooldown live: `1000` / `0−1000` / `1−1000` → only #1.
- below the gate, no cooldown: `0` / `100` / `1−1000` → only #2.
- neither: `0` / `0` / `1` → only #3.

All three carry `-2000 conversations_disabled: "branching"` and `"topics"`. When either feature is
off every result scores ≤ 0 and MCA falls through to the **last** one, which is the plain opener —
the safety net is the last array element, which is exactly why the fallback lives there.

Action key order inside each result is **state → `next` → `say`**:

```
conversations_session (begin) → conversations_record → next → conversations_say
```

`next` writes the destination question's prompt into the screen's single speech slot, so a `say`
authored before it is overwritten and the player never reads it.

The opener **grants nothing** — no hearts, no disposition, no progress. A cooldown memory
(`sample_courage.cooldown.courage`, player-scoped, 48000 ticks) is bookkeeping, not a reward.
Memory ids are prefixed with the pack namespace; the mod owns `mcaconversations.*`.

All three openers route to the same page, so all three lines say **the same thing in different
moods**: there is something they keep not doing, and it is not for lack of time or ability. A
button on `courage.respond` may only lean on detail present in *every* variant of *every* line that
can open the page, and this is how you make that true cheaply.

### `data/sample_courage/dialogues/courage.respond.json` — the checked page

Four answers: one checked stance, two plain ones, one exit.

#### `steady` — the canonical checked stance

Six results, in this order:

1. **The guard.** `baseChance 0`, one condition:
   `{"chance": 100, "conversations_relationship": {"bands": ["stranger","acquaintance","tense","hostile"]}}`.
   It routes back to `courage.respond` and says `courage.steady.guard`. It has **no cost and no
   reward at all** — no hearts, no disposition. That is the point: if the guard paid out or charged,
   a player below the gate could farm it, and if it dead-ended the villager would go silent.
2. **`crit`** → `courage.close.landed`, +3 hearts, trust/respect/familiarity up.
3. **`success`** → `courage.close.landed`, +2 hearts.
4. **`partial`** → `courage.close.landed`, +1 heart, familiarity only.
5. **`rebuff`** → **`courage.close.rebuffed`**, −1 heart, tension +5 and trust −2.
6. **The plain fallback, last.** `baseChance 3`, sunk by
   `{"chance": -2000, "conversations_enabled": "checks"}`.

Results 2–5 each carry three conditions:

```json
{"chance":  1000, "conversations_check": {"id": "courage.steady", "tier": "...",
                                          "axis": "trust", "difficulty": 45,
                                          "stance": "encouragement"}},
{"chance": -1000, "conversations_relationship": {"bands": ["stranger","acquaintance","tense","hostile"]}},
{"chance": -2000, "conversations_disabled": "checks"}
```

All four tiers share `id`, `axis`, `difficulty` and `stance` — the resolver computes **one** score
per click and reads off which band it lands in, so the four results are four faces of one roll, not
four independent rolls. `stance: "encouragement"` is what lets an interiority profile make this kind
of remark land better on an `athletic` villager and worse on an `anxious` one; the bias is capped at
±12, which is less than one tier margin, so personality colours the outcome and never decides it.
(`arc` is the other optional field — naming an arc makes the seeded roll change when the arc stage
moves. This pack has no arc; pack 04 does.)

#### Proof: exactly one result has positive weight, in every state

Weight = `baseChance` + the sum of `chance × conditionValue` for every condition that matches. MCA
subtracts clamped weights walking the array and, if nothing is positive, **takes the last element**.
Below, G = guard, T = the tier result the resolver selected, T′ = the other three tiers, F = the
plain fallback.

| Relationship | `enableChecks` | G | T | T′ | F | Positive |
|---|---|---|---|---|---|---|
| ≥ `friend` | on | 0 | **1000** | 0 | 3 − 2000 = −1997 | T only |
| ≥ `friend` | off | 0 | 0 − 2000 | 0 − 2000 | **3** | F only |
| < `friend` | on | **100** | 1000 − 1000 = 0 | 0 − 1000 | 3 − 2000 − 1000 | G only |
| < `friend` | off | **100** | −3000 | −3000 | 3 − 1000 = −997 | G only |

Two more axes, neither of which disturbs the table:

- **`enableCheckTiers = false`** collapses crit→success and partial→rebuff. The resolver still
  selects exactly one tier key, so exactly one of the four tier conditions is 1. Row 1 is unchanged;
  it is simply a different T.
- **`enableDispositions = false`** makes `conversations_disposition` never match and makes the check
  score from hearts alone. It does **not** touch `conversations_relationship`, which reads MCA's
  hearts, marriage and rupture state directly. That is the whole reason this pack gates on bands: a
  gate written as `conversations_disposition {"axis": "trust", "max": 34}` silently *disappears*
  when dispositions are switched off, and the guard reply stops ever firing.

The band list `["stranger","acquaintance","tense","hostile"]` is the exact complement of
`{"at_least": "friend"}` — `friend`, `confidant` and `partner` are the bands at or above the floor,
and `family` parses but never resolves in this build, so nothing can leak between the two sets. Do
not gate on `family` alone anywhere.

Note the guard's condition and the tier results' sink are **the same query**, written once as a
positive and once as a negative. Keep them in step by hand; there is no shared definition.

#### The other three answers

- `ask_what_stops_you` (`curiosity`) and `admit_i_wouldnt` (`self_disclosure`) are plain: one
  result each, +1 heart with `policy: "once_per_day"`, a small disposition move, then
  `courage.close.landed`. They are the proof that a checked stance is a tool, not a tax — most
  buttons should not be checked.
- `leave` (`exit`) ends the session and returns to `conversations.cat.personal`. It grants and costs
  nothing. Every node here has one.

Hearts move **only** through `conversations_affection_apply`, never MCA's native `positive`. Every
one of them names a stable `decision` id and an explicit `policy`. All four tiers plus the plain
fallback share the decision id `courage.steady`, because they are one choice with different
outcomes; anti-farming should treat a repeat of that choice as a repeat. `budget: "deep"` names the
depth class whose per-conversation cap applies (+8 / −10).

Deepest path total: `steady` crit (+3) → `hold_you_to_it` (+2) = **+5**, inside the deep budget.
Worst path: `steady` rebuff (−1), and the rebuffed close charges nothing.

### `data/sample_courage/dialogues/courage.close.landed.json`
Three answers — `hold_you_to_it` (+2), `no_pressure` (+1), `leave` (free). Every answer ends the
session (`conversations_session: {"op": "end"}`) before `next`, because session ops are state
actions.

The lines here are written to survive **crit, success, partial and the checks-off fallback**, all
four of which arrive at this page. Nothing on it says "thank you for agreeing" — a partial did not
agree.

### `data/sample_courage/dialogues/courage.close.rebuffed.json`
The rebuff's own page. Three answers: `apologize` (`candor`), `drop_it` (`restraint`), `leave`.
None of them moves hearts; two of them lower `tension` slightly, because backing off is how you
repair a push.

This file exists for one rule: **a rebuff must never route into a close node whose answers assume
the stance landed.** Sending `courage.steady.rebuff` to `courage.close.landed` would offer the
player "Then I'll ask you about it next week" thirty seconds after being told to leave it alone.
The mod's own lint (`rebuffTiersDoNotRouteToLandedCloseNodes`) exists because that shipped once.

Note also that the crossed boundary **changes** the relationship — tension up, trust down — and
never removes access to the villager. Every route out of this page is open.

### `data/sample_courage/conversation_catalog/sample_courage.json`
One topic row. `depth: "deep"` is what the affection budget reads. `ages` matches the answer
constraints. `required_stance_families` lists the kinds of thing the player can say here and
**must** include `exit`; a topic without it is refused at load.

The catalog is not a second dialogue engine — MCA's JSON stays authoritative. It exists so ids are
declared in exactly one place and so lint can tell that a claimed topic really became a
conversation.

### `data/sample_courage/conversation_beats/sample_courage.json`
Beats and replies. A **beat** is one say pool paired with the question it opens, treated as one
authored unit; a **reply** is one button, keyed `question/answer`.

Why it matters: MCA decides which buttons to show from the **answer's** own constraints and never
looks at which villager line led there. If two lines need different replies, no condition can stop
the wrong button appearing — the lines have to open different questions. That is exactly why
`courage.steady.rebuff` opens `courage.close.rebuffed` and the other tiers open
`courage.close.landed`; the beat contracts are how a build can check it.

- `facts: ["courage:unattempted"]` is shared by every variant of every opener, and
  `requires_facts` on `steady` and `ask_what_stops_you` is what those buttons take for granted.
  A claim only one variant makes is a claim no button may answer.
- `allowed_stances` on every beat inbound to a page is the set that page's buttons must satisfy, and
  must include `exit`.
- `openness` on the rebuff beat is `guarded` and its `npc_act` is `set_boundary` with
  `outcome: "rebuffed"` — a rupture may only open apology, clarification, respect for the boundary,
  or the door, which is precisely the three answers on the rebuffed page.

**Every beat here is inbound to one of this pack's own pages.** Two beats may not contract the same
`say → next` route, and two beats inbound to the same page have to agree about what that page may
offer. Both are easy to violate accidentally by contracting a route into a mod-owned page like
`conversations.cat.personal`, so the terminal lines that return there carry no beat in this sample.
(A structural collision does not crash: `BeatContractLoader` keeps the previous catalog and logs at
ERROR — which means your new beats silently do not exist. Check the log.)

### `data/sample_courage/interiority/sample_courage.json`
Per-personality resting `baselines` for the disposition axes and `stance_bias` — how well a kind of
thing the player says lands on them. Baselines clamp to ±15, bias to ±12.

**This replaces the shipped profile for each personality it names.** Interiority profiles fold into
one map keyed by personality id and the last file read wins, and for these catalogs the order the
resource manager hands files over is not defined. Do not count on winning unless your pack sits
above the mod's; and when you do redefine one, restate the whole profile, because you are replacing
it, not patching it. This sample deliberately touches only `anxious` and `athletic`.

`anxious` gets `encouragement: -5` and `restraint: +7`; `athletic` gets `encouragement: +9`. So the
`steady` button is the harder read on an anxious villager and the easy one on an athletic villager —
which is the checklist item "there is no universally correct button", implemented in the one place
it belongs.

### `data/sample_courage/chat_intents/sample_courage.json`
One intent per non-exit button, each with `question` + `answer` naming the exact pair, and each
`context`-scoped to the page it lives on so it only competes while that page is open. Keywords are
authored as surface words — the loader stems and synonym-canonicalises them with the same normaliser
the player's typed message goes through.

Exits are deliberately unbound: leaving is a dispatcher `system` intent (`farewell`), not a topic
one, and MCA enforces answer-level `constraints` at match time so there is no need to restate them
here.

The `synonyms` block merges across every pack and namespace before any intent parses, so a pack that
only broadens vocabulary needs no intents at all. First writer wins per alias.

### `assets/sample_courage/lang/en_us.json`
- `dialogue.<question>` — the page prompt. It is only a **fallback**, shown when a result arrives
  with no line of its own, but write it so it reads acceptably alone anyway.
- `dialogue.<question>.<answer>` — the button label.
- `dialogue.<phrase>` — the villager line named by `conversations_say`. The `phrase` field is the
  key **without** the `dialogue.` prefix.
- Variants are `/1 /2 /3`. Three is the floor; two is allowed for the precision-targeted pools,
  which here are everything ending `.crit`, `.success`, `.partial`, `.rebuff` and `.guard`. A
  single-line pool takes **no** `/1` suffix at all.

`%1$s` is the player's name, inserted by MCA. Template `vars` would fill `%2$s` onward; this pack
uses none.

Button labels are **what the player says**. Never "Persuade", never "+2 Warmth", never a success
chance. Below the gate the guard reply is the honest lower-stakes outcome, which is why the label
can stay warm at any relationship level without lying.

### `assets/mca_dialogue_gloomy/lang/en_us.json` and `assets/mca_dialogue_confident/lang/en_us.json`
Personality overlays. MCA looks for `<personality>.dialogue.<key>` before `dialogue.<key>`, so an
overlay is a complete replacement line for one personality.

**The keys must carry the personality prefix.** `gloomy.dialogue.courage.steady.rebuff/1` is an
overlay. `dialogue.courage.steady.rebuff/1` sitting in the same file is *not* — it is a second
global definition of the base key, i.e. a silent collision with your own pack.

Two more rules: an overlay key must have a base line to override, and an overlay pool must carry the
**same number of variants** as the base pool. A base pool of three with an overlay of two lets MCA
ask for an index the personality never wrote, and the villager falls back mid-conversation. That is
why the `gloomy` overlays here are 2 lines (base `.guard` and `.rebuff` are 2) and the `confident`
opener overlay is 3 (base `courage.open.first` is 3).

Writing overlays per **voice family** rather than per personality is the mod's own practice: six
approaches to saying a hard thing (`plainspoken`, `bright`, `warm`, `quiet`, `tender`, `settled`)
beats sixteen paraphrases of one sentence. This pack shows two namespaces because two is enough to
show the rule.

---

## The rules this pack is obeying

1. **Actions run in JSON key order, and `say` must come after `next`.** Every result here is
   state → `next` → `say`.
2. **When every result scores ≤ 0, MCA takes the LAST one.** Every fallback is the last array
   element. Never the first.
3. **Selection is weighted-random over positive totals.** Hence the state table above. A checked
   answer with two positive results is a bug you will only see as "the villager sometimes says the
   wrong thing".
4. Hearts move only through `conversations_affection_apply`, with a stable `decision` and an
   explicit `policy`, inside the depth class budget.
5. The opener grants nothing; cooldown memory is bookkeeping.
6. Every node offers 2–5 answers and at least one consequence-free way out.
7. A rebuff never routes into a landed close.
8. Every non-exit answer has a chat intent bound to its exact question+answer.
9. Never MCA's native `personality` condition — it throws on an id the running MCA does not know and
   takes the whole datapack reload, and the world load, with it. Use `conversations_personality`.
   (This pack expresses personality through interiority instead, which is finer-grained again.)

---

## How to adapt it

Change these, roughly in this order:

1. **The `difficulty`** on all four tier conditions (45 here) — the single biggest lever. Raise it
   and the stance is a long shot; lower it and it is nearly free. Change all four together or the
   answer stops being one roll.
2. **The `axis`** (`trust` here). `warmth` for comfort, `respect` for candour and competence,
   `trust` for anything that costs the villager something to say. `attraction` is romance-gated and
   structurally unreachable on an ineligible target — never gate a required route on it.
3. **The gate band.** `{"at_least": "friend"}` in the guard's complement. Move it to
   `confidant` for something genuinely private, or drop the guard entirely for a stance that is
   fair to offer a stranger — but then delete the `-1000` sinks too, or the tiers stay dead.
4. **The interiority profiles.** Nothing else in the pack changes how personality reads.
5. The lines. All of them. The mechanics survive a total rewrite of the prose.

To add a **second** checked stance to the page, copy the `steady` answer whole, change the check
`id` and the say keys, and re-run the state table above on the copy. Two stances may not share a
check `id` unless they are literally the same choice.

---

## What this pack deliberately does not do

- **It offers two decisions on a normal path where `deep` asks for three.** Adding the third means a
  middle node between `courage.respond` and the close pages — a page where the villager names what
  going at it would actually cost, and the player answers that instead. It is left out to keep the
  checked answer the only complicated thing in the pack. If you build on this, add it; the catalog
  row already claims `deep`.
- No arc, no milestone, no exclusive choice, no promise — those are pack 04. The `arc` field on
  `conversations_check` is therefore unused here, and it is the field that makes a seeded roll move
  on when the relationship genuinely moves on.
- No scenes, episodes, threads or identity tokens — pack 05.
- No `pt_br`. Shipped content lands both locales in one change with matching keys and placeholders.
- No terminal beat contracts for the lines that return to `conversations.cat.personal`, to keep
  every beat here inbound to this pack's own pages.
- It does not redefine any mod-owned dialogue answer, intent id or scene. The only shipped file it
  touches is `conversations.cat.personal`, and it touches it purely additively by merging one
  answer.
- These files are authored against the shipped schemas and the documented engine rules. They have
  not been played through in a production MCA instance.

---

## A gap this pack cannot close: there is no `child` constraint

This topic is catalogued for `teen` and `adult`, and its starter is gated
`"constraints": "!baby,!toddler"`. That is as close as MCA lets a datapack get, because
**MCA's constraint registry has no `child` token**.

The registry holds `baby`, `toddler`, `teen`, `adult` (plus `family`, `spouse`, `kids`, the rank
tokens and so on), each with a `!` form — and nothing for children. `Constraint.fromStringList`
looks each comma-separated token up and **silently drops anything it does not recognise**, with no
log line. So a tempting `"!baby,!toddler,!child"` evaluates as `"!baby,!toddler"` and reads as a
gate that is not there. (Verified in `Constraint.class` in both the 1.20.1 Forge and 1.21.1 NeoForge
MCA jars. The mod's own shipped content has the same gap in several answers.)

The practical consequence: **children are offered this button.** Since constraints are the only
answer-level gate, you cannot fix that at the button. If you need children excluded, the gate has to
move into the results — add `{"chance": -1000, "age_group": "child"}` to the adult routes and author
a child-appropriate result for them to land on instead. Note that sinking *every* result does not
work: when all results score ≤ 0 MCA picks the last one, so a child would simply receive whatever
your fallback says. There has to be a result written for them.

This sample takes the honest option: the dead token is gone, the limitation is written down, and the
lines are worded so a younger villager reading them is not jarring.
