# 04 — Arcs and promises

Namespace `sample_ledger`, question prefix `ledger`, topic id `ledger`.

A **standard** topic about a debt the villager is owed. Everything in it is *durable*: what you
talked them into is still true tomorrow, the promise you made can be kept or broken by something you
actually do in the world, and a later conversation reads the decision back.

This is the pack about state that outlives a conversation — and about the discipline that goes with
it. **State nothing reads is state that should not be stored**, so every durable thing declared here
is also read somewhere in these three dialogue files — with one deliberate exception, the cooldown
memory the opener writes, which is called out where it appears.

---

## What it demonstrates

- `conversations_progress_apply` writing an **arc** stage, a **milestone** and one side of an
  **exclusive group**, and `conversations_progress` reading all three back — including
  `{"has": false}` for a milestone and `{"is": "none"}` for an undecided group.
- The **correct exclusive-group idiom**: one result per member, plus a **last** result that sinks
  `-2000` on all of them. Not a `{"is": "none"}` result scoring 1 next to a member scoring 100.
- An arc that advances **at most one stage per call**, enforced by the runtime and not only by lint.
- Two `commitment_templates`: one the running game can genuinely observe (`gift_tag_received` on
  `minecraft:emerald`) and one that can only be `manual_neutral` — and why `event_observed` must
  never be used.
- `conversations_commitment` as both a condition (state / due) and an action (`make` / `resolve`).
- `conversations_exchange` reading back **what was decided**, with all three discriminators.
- Hearts through `conversations_affection_apply` only, with stable decision ids, explicit policies
  and the `standard` depth budget.
- One chat intent per non-exit button.

---

## Install

Two halves, two folders:

| Half | In this folder | Where it goes |
|---|---|---|
| `data/sample_ledger/**` | `datapack/` | `saves/<world>/datapacks/04-arcs-and-promises/` |
| `assets/sample_ledger/**` | `resourcepack/` | `resourcepacks/04-arcs-and-promises/` |

Copy each half to its own place. Minecraft serves only `data/` from a datapack and only `assets/`
from a resource pack, and on 1.21.1 the two want different `pack_format` numbers — **48** for data,
**34** for resources — so each half carries its own `pack.mcmeta`. Paths written `data/…` or
`assets/…` below are paths inside an installed pack.

```
/reload                      # dialogues, catalog, beats, commitments, intents
F3+T (or reselect the pack)  # the lang file
```

Lang files merge across namespaces, so `assets/sample_ledger/lang/en_us.json` adds its `dialogue.*`
keys without touching `assets/mca_dialogue/lang/en_us.json`. The corollary is that a key two packs
both define collides silently, which is why every key here is prefixed `ledger.`.

---

## Try it

1. Talk to a villager → **"Let's talk properly"** (MCA's **Chat** button instead, under
   `hubEntryMode = REPLACE`) → *Village* → “Is somebody in the village behind with you?”
2. Press **“Who is it?”**. That fires the milestone `ledger.acknowledged`, once ever, for this
   villager and player. Press it again — the answer changes, because the second result reads the
   milestone back.
3. Press **“Some debts cost more to chase than they're worth.”** (or “Ask them for it. Plainly.”).
   That takes one side of the exclusive group and advances the arc to stage 1.
4. Inspect what was written:
   ```
   /conversations history inspect      # promises, opinions, roles, threads, claims
   /conversations context snapshot     # narrative.due_commitments and the rest
   ```
5. Leave, come back, and open the topic again. The starter now opens on `ledger.again.open` because
   the arc has moved. Press **“How did that leave things?”** — it reads the exclusive group and
   tells you which side you took.
6. Make the promise: on `ledger.respond`, press **“I'll cover it myself.”**. Now
   `ledger.cover_the_debt` is `pending`, due in three days.
   - Return within three days: the starter opens on `ledger.promise.open` and
     **“Was there something outstanding?”** reads the promise back.
   - **Give the villager an emerald.** The gift observer resolves the promise `kept` — no dialogue
     required, and that is exactly the point.
   - Or let three days pass without one, and it goes `overdue` and then `broken`.
7. Ask the callback question: **“We settled this once, didn't we?”**. It fires only if a decision
   filed under subject `ledger.debt` with stance `practical_help` and outcome `accepted` exists
   within 14 days — i.e. only if you actually offered to cover it.
8. From chat mode:
   ```
   /conversations chat debug i will cover it myself
   /conversations chat debug-ask ledger.respond offer_to_cover
   ```

---

## File-by-file walkthrough

### `datapack/pack.mcmeta` and `resourcepack/pack.mcmeta`
`{"pack": {"pack_format": 48, "description": "…"}}` and `{"pack": {"pack_format": 34,
"description": "…"}}` — the 1.21.1 data and resource formats.

### `data/sample_ledger/dialogues/conversations.cat.village.json` — the starter
Basename matches the mod's category page exactly, so MCA **merges** this file's `answers` array into
the mod's. It contains one answer, `ledger`. Category pages carry no top-level flags, so this
extension carries none. `"constraints": "!baby,!toddler"` is as close as a constraint can get to the
catalog's `ages` (`teen`, `adult`): babies and toddlers are kept out, but children are still offered
the button, because there is no `child` token to write — see the closing section.

Three results, ideal first, fallback last:

| # | Fires when | Opens |
|---|---|---|
| 1 | `ledger.cover_the_debt` is `pending` or `overdue` | `ledger.followup`, saying `ledger.promise.open` |
| 2 | arc `ledger` is at stage ≥ 1 | `ledger.followup`, saying `ledger.again.open` |
| 3 | otherwise (last) | `ledger.respond`, saying `ledger.open` |

Exactly one is positive in every combination, because each earlier condition reappears as a `-1000`
sink on the later results:

| promise outstanding | arc ≥ 1 | #1 | #2 | #3 |
|---|---|---|---|---|
| yes | yes | **1000** | 1000−1000 = 0 | 1−1000−1000 |
| yes | no | **1000** | 0−1000 | 1−1000 |
| no | yes | 0 | **1000** | 1−1000 |
| no | no | 0 | 0 | **1** |

All three carry `-2000 conversations_disabled: "branching"` and `"topics"`. With either off, every
result is ≤ 0 and MCA takes the last element — the plain opener. That is the safety net working as
designed, and the reason a fallback is never written first.

Note what result #1 is really doing: `conversations_commitment` reads the state the promise **is in
today**, computed on read. A deadline that passed while the server was down reads correctly without
anything having been written. You never have to tick promises.

Actions are ordered state → `next` → `say` in every result. `next` writes the destination question's
prompt into the screen's single speech slot, so a `say` written before it is overwritten and never
seen.

The opener grants nothing. The cooldown memory `sample_ledger.cooldown.ledger` is the one piece of
write-only state in the pack: it is recorded here and read nowhere, because the opener branches on
the promise and the arc instead. Packs 01 and 03 show the reading side of the same action.

### `data/sample_ledger/dialogues/ledger.respond.json`
Five answers — the maximum a node should offer.

#### `ask_who` — the milestone, written and read in one answer
Two results:

```json
{"chance":  100, "conversations_progress": {"milestone": "ledger.acknowledged", "has": false}}   // result 1
{"chance": -2000, "conversations_progress": {"milestone": "ledger.acknowledged", "has": false}}  // result 2 (last)
```

Result 1 sets the milestone (`conversations_progress_apply: {"milestone": "ledger.acknowledged"}`,
which fires exactly once ever for this pair) and tells the player who. Result 2, `baseChance 1`, is
the "you already asked" line. Weights: unset → `100` / `1 − 2000`; set → `0` / `1`. One positive
either way.

`{"has": false}` is how you ask "deliberately absent". It is not the same as the id being unknown,
and it is why a milestone can gate a first-time line without a second memory flag beside it.

Both results route back to `ledger.respond`, which is what makes this the *first* of the two
decisions the `standard` depth class asks for on a normal path.

#### `urge_forgive` — one choice, two outcomes, and no universally correct button
Two results discriminated by `conversations_personality`:

- `greedy` / `crabby` / `extroverted` → **−1 heart**, tension +4, respect −2, and the line
  `ledger.forgive.resents`. They do it, and they resent being talked into it.
- anyone else (last, `baseChance 1`, sunk `-1000` on the same personality set) → **+2 hearts**,
  warmth +3, respect +2, `ledger.forgive.agrees`.

Both results write the *same* durable state, because the choice was the same choice:

```json
"conversations_progress_apply": [
  {"exclusive": "ledger.settlement", "member": "forgive"},
  {"arc": "ledger", "op": "advance", "to": 1}
]
```

An array, because a result may need several progress writes and JSON keys cannot repeat.

Use `conversations_personality`, never MCA's native `personality` condition — that one throws on an
id the running MCA does not know and takes the datapack reload, and the world load, down with it.
`conversations_personality` never throws and resolves legacy ids to their successors. All three ids
named here are ones a 1.21.1 villager can roll; `confident`, `peppy` and `athletic` are no longer
registered, so a result gated only on those would never be reached.

#### `urge_collect`
Takes the other side of the group, advances the arc, and has the **villager** promise something:
`conversations_commitment: {"op": "make", "id": "ledger.speak_to_them"}`. +1 heart.

#### `offer_to_cover`
`conversations_commitment: {"op": "make", "id": "ledger.cover_the_debt"}` — the promise the game can
check — and +2 hearts with `policy: "once"`, because offering to settle a neighbour's debt is a
milestone outcome, not a daily nicety.

This is also the answer `conversations_exchange` reads back later. The decision is filed from what
actually happened: the **stance of the button** (`practical_help`, from the reply contract) and the
**outcome family of the beat the villager moved to** (`accepted`, on
`ledger.cover.accepted.to.ledger.followup`), under that beat's **subject** (`ledger.debt`). None of
those three come from anything a result asserts about itself.

#### `leave`
Ends the session, returns to `conversations.cat.village`, costs nothing.

#### Arcs advance one stage at a time
`{"arc": "ledger", "op": "advance", "to": 1}` asks to land on stage 1. If you asked for `"to": 2`
from stage 0 you would still land on 1: the runtime clamps an advance to `current + 1` and to the
catalog's `max_stage`, so an arc cannot be skipped even by a datapack that tries. `regress` and
`hold` are the other two ops; `hold` moves nothing and exists to document that the author meant it.

#### The budget
`standard` is +4 / −5 per conversation, ≥ 2 decisions. The best single path is
`ask_who` (0) → `urge_forgive` non-grudging (+2) → `ask_how_it_went` (+1) = **+3**. The worst is
−1. Every affection action names its `budget` explicitly rather than leaning on the session's class.

### `data/sample_ledger/dialogues/ledger.followup.json`
Four answers.

#### `ask_how_it_went` — the exclusive-group idiom, done correctly
Three results:

```json
{"baseChance": 0, "conditions": [{"chance": 100, "conversations_progress": {"exclusive": "ledger.settlement", "is": "forgive"}}], …}
{"baseChance": 0, "conditions": [{"chance": 100, "conversations_progress": {"exclusive": "ledger.settlement", "is": "collect"}}], …}
{"baseChance": 1, "conditions": [
    {"chance": -2000, "conversations_progress": {"exclusive": "ledger.settlement", "is": "forgive"}},
    {"chance": -2000, "conversations_progress": {"exclusive": "ledger.settlement", "is": "collect"}}], …}
```

One result per member, plus a **last** result that sinks `-2000` on all of them. Weights are
`100 / 0 / −1999`, `0 / 100 / −1999`, or `0 / 0 / 1`. Exactly one positive, always.

**Why not the obvious version.** It is tempting to write the "neither side taken" case as an
explicit fourth condition scoring 1:

```json
{"baseChance": 0, "conditions": [{"chance": 1, "conversations_progress": {"exclusive": "ledger.settlement", "is": "none"}}]}
```

…sitting beside a member result scoring 100. When no side has been taken the member results are 0
and this one is 1, which looks right. But selection is **weighted random over positive totals, not
highest-wins**, so the moment the two are ever positive together — which happens the instant you add
a third condition, or someone merges another result into the answer — you have a 1-in-101 chance of
the wrong line. Two positive results in a state where you meant one is always a bug; it is just a
bug that shows up once every hundred plays and reads as flaky writing. The `-2000`-on-all-members
form cannot get there: the "neither" result is arithmetically dead the moment any member is set.

`{"is": "none"}` is still a legitimate query — use it where "undecided" is a *positive* thing you
want to say, and give it the same sink treatment.

The first side taken decides the group for good; a later `member` write on a decided group does not
flip it.

#### `remind_promise` — reading promises back, and resolving one
Three results, same discipline:

1. `{"id": "ledger.cover_the_debt", "state": ["pending", "overdue"]}` → the villager mentions
   *your* promise. It does **not** resolve anything: the gift observer does that.
2. `{"id": "ledger.speak_to_them", "state": ["pending"]}`, sunk `-1000` on condition 1 → the
   villager mentions *their own* promise and the result resolves it:
   `{"op": "resolve", "id": "ledger.speak_to_them", "outcome": "noted"}`.
   `noted` is the terminal state of a neutral promise — acknowledged and deliberately never judged
   kept or broken.
3. Last, `baseChance 1`, sunk `-2000` on both — nothing outstanding.

Note that this answer's button shows on the page regardless. MCA's conditions score **results**, not
answers; answers are filtered only by their own `constraints`. Any answer whose results are all
conditional therefore needs an unconditional last result, or it dead-ends.

#### `mention_last_time` — `conversations_exchange`
```json
{"chance": 100, "conversations_exchange": {
   "subject": "ledger.debt", "stance": "practical_help",
   "outcome": "accepted", "within_days": 14}}
```

`conversations_recent` answers *how long since this came up*. `conversations_exchange` answers *and
what did we settle on* — the difference between "as I was saying" and "you told me to save the ink".

The three discriminators **intersect**:

- **`subject`** — what was being talked about, taken from the beat the villager moved to. The right
  subject with the wrong stance does not match.
- **`stance`** — the `StanceFamily` of the button the player pressed, from the reply contract.
- **`outcome`** — the `OutcomeFamily` of the beat the villager moved to in response.

Naming **none** of the three is refused at parse time: a condition matching any decision at all
would fire for every player who has ever finished a conversation. Naming one asks broadly
(`{"stance": "respectful_disagreement"}` — did they push back about anything lately); naming all
three asks exactly, which is what this pack does. A misspelt stance or outcome is *invalid*, not
ignored — a typo withholds the branch rather than widening it.

`within_days` defaults to 30. One decision is kept per subject, per villager, per player, up to 16
subjects; re-deciding a subject overwrites it, because the villager remembers the mind you ended up
with, not every mind you passed through.

The second result is the unconditional last one, sunk `-2000` on the same query.

#### `leave`
Consequence-free, as on every page.

### `data/sample_ledger/conversation_catalog/sample_ledger.json`
The one place `arc`, `milestones` and `exclusive_groups` are declared:

```json
"arc": {"id": "ledger", "max_stage": 2},
"milestones": ["ledger.acknowledged"],
"exclusive_groups": {"ledger.settlement": ["forgive", "collect"]}
```

`max_stage` bounds every advance. A group needs at least two members. Declaring ids here and nowhere
else means a typo in a dialogue file is a condition that never matches rather than a second, silent
ledger — and it is what lets lint check that every member is read back somewhere, and that having
taken *neither* side is read back too.

`depth: "standard"`, `return_question: "conversations.cat.village"`, and `required_stance_families`
must include `exit`.

### `data/sample_ledger/commitment_templates/sample_ledger.json`
Two promises, and the difference between them is the lesson.

```json
"ledger.cover_the_debt": {
  "resolver": "gift_tag_received",
  "target": "registry_id:minecraft:emerald",
  "due_after_days": 3,
  "made_by": "player"
}
```

`gift_tag_received` is observed by the running game: the player gives the villager an emerald and the
promise settles `kept`, with no dialogue involved. **A promise must name a resolver, and a judging
resolver must name a target** — that is enforced at parse, not just by lint. A template that
promises help without saying what counts as help is refused at load rather than shipped as a promise
nobody can keep.

The available resolvers are `gift_tag_received`, `quest_state` (MCA: Quests completion/failure),
`visit_after_day` (which requires `due_after_days`), `conversation_choice` and `manual_neutral`.
Gift, visit and quest observers settle **player-made** promises only.

```json
"ledger.speak_to_them": {"resolver": "manual_neutral", "made_by": "villager"}
```

**When to use `manual_neutral`.** When nothing in the running game can observe the thing. A villager
going to knock on a neighbour's door is not an event any observer sees, so judging it kept or broken
would be a coin flip dressed as bookkeeping. `manual_neutral` records it as something that was said
and never judges it; the dialogue side resolves it to `noted` when it comes up again. The alternative
is to word the button as willingness rather than promise — "I'll see what I can find" — and store
nothing at all. Both are honest. Inventing an observer is not.

**Why `event_observed` must never be used.** It exists in the resolver enum and it is reserved, but
**no generic event observer is installed**. A promise on it never judges kept or broken and never
progresses; it sits pending until it goes overdue and stays there. It looks like a working resolver
and behaves like a leak. Use `manual_neutral` when you mean "remembered, never judged", and one of
the four real observers when you mean "checked".

`due_after_days` is optional except for `visit_after_day`. A template may also name a `thread` when
the promise belongs to one; neither promise here does, because this pack has no threads.

### `data/sample_ledger/conversation_beats/sample_ledger.json`
Beats and replies. A beat is one say pool plus the question it opens; a reply is one button, keyed
`question/answer`.

MCA decides which buttons to show from the **answer's** own constraints and never looks at which
line led there. So lines needing different replies must open different questions, and the beat
contracts are what make that checkable.

The important one here is `ledger.cover.accepted.to.ledger.followup`: its `subject` is `ledger.debt`
and its `outcome` is `accepted`, and those two fields plus the `practical_help` stance on
`ledger.respond/offer_to_cover` are the whole of what `conversations_exchange` reads back later. If
you rename the subject, the callback stops firing — silently, because a decision filed under the old
subject simply does not match the new query.

`facts: ["ledger:unpaid"]` is on every beat and shared by every variant of every pool, because MCA
picks the variant on the client and the server never learns which one; a claim only one variant
makes is a claim no button may answer. `requires_facts` on the reply contracts is what those buttons
take for granted.

Every beat is inbound to one of this pack's own pages. Two beats may not contract the same
`say → next` route, and beats inbound to a shared page have to agree about which stances that page
may offer — both are easy to break by contracting a route into a mod-owned page, so the terminal
lines returning to `conversations.cat.village` carry no beat here. A structural collision does not
crash: the loader keeps the previous catalog and logs at ERROR, which means your new beats silently
do not exist.

An unknown beat id named from a result is logged and ignored rather than thrown — a pack that names
a beat it forgot to ship loses its breadcrumbs, not the player's conversation.

### `data/sample_ledger/chat_intents/sample_ledger.json`
One intent per non-exit button, bound to its exact `question` + `answer`, `context`-scoped to the
page it lives on. Keywords are surface words; the loader stems and synonym-canonicalises them with
the same normaliser the player's message goes through. The `synonyms` block merges across every pack
and namespace before any intent parses (first writer wins per alias), so a pure vocabulary pack needs
no intents of its own.

Intent **ids** merge last-wins across datapacks, which is how you re-keyword a shipped intent — but
the order for `chat_intents` is whatever the resource manager hands over, so do not rely on
redefining someone else's id unless your pack's priority is above theirs. Everything here uses its
own `ledger.*` ids and overrides nothing.

Exits are unbound on purpose: leaving is a dispatcher `system` intent (`farewell`), not a topic one.

### `assets/sample_ledger/lang/en_us.json`
`dialogue.<question>` is the page prompt (a fallback, shown only when a result arrives with no line
of its own — write it to read acceptably alone anyway). `dialogue.<question>.<answer>` is the button
label. `dialogue.<phrase>` is the villager line, where `phrase` is the key **without** the
`dialogue.` prefix.

Every say pool here has three variants (`/1 /2 /3`); the two-variant relaxation is for tier and
guard pools, which this pack has none of. A single-line pool takes no `/1` suffix at all.

`%1$s` is the player's name, prepended by MCA. Template `vars` would fill `%2$s` onward; this pack
uses none — deliberately, because `villager_name` and friends are easy and the durable-state
machinery is what the pack is for.

Button labels are **what the player says**: "I'll cover it myself.", not "Make promise" and not
"+2 Hearts". And a label may only reference detail present in **every** variant of the line it
answers, which is why the openers all agree that somebody owes the villager and hasn't paid, and
differ only in mood.

---

## The rules this pack is obeying

1. State actions → `next` → `say`, in JSON key order, in every result.
2. The fallback is the **last** array element, because MCA takes the last one when everything scores
   ≤ 0.
3. Selection is weighted-random over positive totals — hence a sink for every earlier condition, and
   the exclusive-group idiom above.
4. Hearts only through `conversations_affection_apply`, always with a stable `decision` and an
   explicit `policy`, inside the `standard` budget.
5. An arc advances at most one stage per call.
6. Everything durable is declared in the catalog **and** read back in dialogue.
7. Every member of the exclusive group is read back, and so is having taken neither side.
8. Every node offers 2–5 answers and a consequence-free way out.
9. A promise names a resolver the running game can observe, or it is `manual_neutral`.
10. Every non-exit answer has a chat intent bound to its exact question+answer.
11. The opener grants nothing.

---

## How to adapt it

1. **The promise target.** `registry_id:minecraft:emerald` → whatever your topic is actually about.
   Keep it something a player can plausibly hand over, and keep `due_after_days` generous; the
   observer is the only thing that can settle it, and an unmeetable promise is worse than none.
2. **The exclusive members.** `forgive` / `collect` are the two ways this story can go. Add a third
   if there is genuinely a third; the group takes any number ≥ 2, and the last-result sink must then
   sink on all of them.
3. **`max_stage`.** Raise it and add opener results reading `{"arc": "ledger", "min": 2}`, and give
   the higher stages their own pages. Every stage you add is a stage that must be reachable one
   advance at a time and readable somewhere.
4. **The exchange query.** Change `subject`, `stance` or `outcome` and you must change the beat and
   reply contract that produce them, or the callback quietly never fires.
5. The lines. All of them.

---

## What this pack deliberately does not do

- **No checks and no disposition gating** — that is pack 03. The two are orthogonal: a checked
  stance could sit on `ledger.respond` perfectly well, and would need the full six-result shape.
- **No threads and no episodes.** A promise may name a `thread` it belongs to; neither of these two
  does, because thread templates and their `resume_scenes` are pack 05's subject. The commitment
  layer works without them.
- **No `conversations_claim`, `conversations_opinion` or `conversations_role`.** Opinions need a
  `cause` and resolve their target from a bound `person` slot on a frozen plan, which means a scene,
  which means pack 05/06.
- **It does not use `event_observed`**, and neither should you. See above.
- **It does not redefine any mod-owned id.** For `chat_intents`, `conversation_catalog` and
  `conversation_beats` the last-wins order is undefined, so overriding somebody else's id is not
  something a pack can rely on without raising its own priority. Pack 09 covers the overrides that
  *are* deterministic.
- No `pt_br`. Shipped content lands both locales in one change with matching keys and placeholders.
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
gate that is not there. (Verified in `Constraint.class` in `mca-neoforge-7.7.33+1.21.1.jar`, where
the registry is token-for-token what the 1.20.1 Forge jar shipped. The mod's own shipped content has
the same gap in several answers.)

The practical consequence: **children are offered this button.** Since constraints are the only
answer-level gate, you cannot fix that at the button. If you need children excluded, the gate has to
move into the results — add `{"chance": -1000, "age_group": "child"}` to the adult routes and author
a child-appropriate result for them to land on instead. Note that sinking *every* result does not
work: when all results score ≤ 0 MCA picks the last one, so a child would simply receive whatever
your fallback says. There has to be a result written for them.

This sample takes the honest option: the dead token is gone, the limitation is written down, and the
lines are worded so a younger villager reading them is not jarring.
