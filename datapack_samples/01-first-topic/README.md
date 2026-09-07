# 01 — first topic (`sample_sleep`)

The smallest complete MCA: Conversations topic that is still *correct*: a new chit-chat starter,
**"Did you sleep?"**, merged into the mod's own chit-chat category page, with four branches, two
response pages, a shared close page, a catalog row, semantic contracts, chat intents and a full
lang file.

Nothing in this pack is clever. Everything in it is here because leaving it out produces content
that loads, looks fine in the screen, and behaves wrongly — usually in a way you only notice weeks
later. The walkthrough below says which line is guarding against which failure.

---

## What it demonstrates

- **Same-basename merge.** `data/sample_sleep/dialogues/conversations.cat.chitchat.json` adds one
  answer to a question the mod already ships, without touching the mod's file.
- **Result ordering**, and why the fallback is the *last* element of `results` rather than the first.
- **Action key ordering** — state actions, then `next`, then `say`.
- **Cooldown memory** with `remember`/`conversations_record` and the has/lacks condition idioms.
- The **`quick` depth class** and the per-conversation heart budget it implies.
- **`conversations_affection_apply`** with a stable `decision` id and an explicit `policy` — the only
  route to a heart change in branching content.
- **`conversations_disposition_apply`** for the parts of a relationship that are not hearts.
- A **personality split** (`conversations_personality`) so that one button is not right for everyone.
- **Beat contracts** (`conversation_beats/`) tying each say pool to the page it opens and each button
  to the beats it is allowed to answer.
- **One chat intent per non-exit answer**, bound to its exact question + answer.
- **Graceful degradation**: a plain-MCA result that still works when the branching feature is off.

---

## Install

A pack folder that adds dialogue has **two halves**, and Minecraft will only ever serve one of them
from a given location:

- `data/` is only read from a **data pack** — `saves/<world>/datapacks/` (or `world/datapacks/` on a
  server, or a global pack folder).
- `assets/` is only read from a **resource pack** — `resourcepacks/`, enabled in Options → Resource
  Packs.

On 1.21.1 the two halves need two `pack.mcmeta` files, because a data pack declares
`pack_format` **48** and a resource pack declares **34** — one number cannot serve both roles the
way 15 did on 1.20.1. So this pack ships pre-split, and you install two folders:

```
01-first-topic/datapack/      -> saves/<world>/datapacks/01-first-topic/
01-first-topic/resourcepack/  -> resourcepacks/01-first-topic/
```

Install only the data half and the conversation runs correctly with every line rendering as a raw
translation key (`dialogue.sample_sleep.ordinary/2`). Install only the resource half and nothing
happens at all.

The file paths named throughout this README — `data/sample_sleep/…` and `assets/sample_sleep/…` —
are the paths *inside an installed pack*. In this folder they sit under `datapack/` and
`resourcepack/` respectively.

Reload after editing:

- `/reload` — reloads `data/`. This picks up dialogue, the catalog, the beats and the chat intents.
- **F3 + T** — reloads resource packs, which is what picks up `en_us.json`.

`/reload` alone will not change a single word on screen; F3+T alone will not change a single route.

---

## Try it

1. Walk up to an adult villager and open the MCA interaction screen.
2. Open the hub — **"Let's talk properly"** on MCA's main menu by default, or MCA's **Chat** button
   if you set `hubEntryMode = REPLACE` — then **Just making conversation.** The chit-chat page now
   has a **"Did you sleep?"** button that the mod did not ship. That is the same-basename merge
   working.
3. Press it. You get one of three ordinary-night lines, and a page with three replies.
4. Choose **"Did you dream about anything?"**, then **"I've not been sleeping much either."** That is
   the two-decision path a `quick` topic owes.
5. Press **"Did you sleep?"** again immediately. You now get the *asked-recently* branch, because the
   first pass wrote an expiring memory. Nothing about your click changed; the world state did.

Prove it with the debug commands, looking at the same villager:

```
/conversations chat debug did you sleep well last night
```
scores your message against every loaded intent. `sample_sleep.starter` should be at the top. Try
`goodnight` on its own and watch the anti-keyword push it down.

```
/conversations chat debug-ask conversations.cat.chitchat sleep
/conversations chat debug-ask sleep.respond dream
/conversations chat debug-ask sleep.close share
```
drives the villager you are looking at through the whole path without clicking anything, which is
the fastest way to check that a route you just edited still connects.

```
/conversations context snapshot
```
prints `speaker.mood` and `speaker.age` among everything else — the two fields that decide which
branch of the starter you are about to get. If you cannot reproduce the rough-night branch, this is
where you find out that the villager's mood is `fine`.

```
/conversations profile inspect
```
prints the villager's personality, which is what decides whether **"Good. A night's rest is worth
having."** lands warmly or flatly.

---

## File-by-file walkthrough

### `pack.mcmeta`

```json
datapack/pack.mcmeta      {"pack": {"pack_format": 48, "description": "..."}}
resourcepack/pack.mcmeta  {"pack": {"pack_format": 34, "description": "..."}}
```

Two files, because on 1.21.1 the data-pack format is **48** and the resource-pack format is **34**.
Neither has a `filter` or an `overlays` section: this pack only adds, it never hides anything the
mod ships.

---

### `data/sample_sleep/dialogues/conversations.cat.chitchat.json`

The filename is the whole trick. MCA loads every `data/*/dialogues/*.json` that contains an
`"answers"` array and keys it by the **file basename**, ignoring the namespace. Two files called
`conversations.cat.chitchat.json` in two namespaces do not conflict — their `answers` arrays are
**merged**. So this file contains exactly one answer, ours, and the mod keeps all of its own.

The file has **no top-level flags**, deliberately. MCA keeps the flags of whichever same-named file
loads last, and that order is undefined. The mod's own `conversations.cat.chitchat.json` has no
`auto` and no `silent`, so ours must not introduce any either; adding `"silent": true` here would
have a one-in-two chance of silencing the mod's whole chit-chat page.

```json
{"name": "sleep", "constraints": "!baby", "results": [ ... ]}
```

`constraints` is a comma-separated token list, AND-only, with `!` for negation. A baby has no
business answering a question about their night, and MCA hides the button rather than us having to
sink it in every result. Note that constraints are also enforced automatically at chat-intent match
time, which is why the intent file does not repeat this gate.

The answer name `sleep` gives the button label key `dialogue.conversations.cat.chitchat.sleep`. It
must not collide with any **question** name, because a question `q` and an answer `a` on question `q`
share the key shape `dialogue.q.a`. There is no question called `conversations.cat.chitchat.sleep`,
so we are safe. (This is also why our pages are named `sleep.respond` and not `sleep` plus an answer
called `respond`.)

#### The five results, in order

MCA scores every result — `baseChance` plus, for each entry of `conditions`, `chance × conditionValue`
— then picks one by **weighted random over the positive totals**. It is not highest-wins. The
practical consequence is that a checked answer must guarantee that exactly **one** result has a
positive total in any given world state, which is what the negative sinks below are for.

**1. Asked recently.** `baseChance: 0`, plus `{"chance": 1000, "memory": {...}}`. The `memory`
condition returns 1 while an unexpired memory exists and 0 otherwise, so this result is worth 1000
when the player asked in the last 12000 ticks and worth 0 the rest of the time. Zero is not positive,
so it never competes with the ordinary branch.

**2. Toddler or child.** Two `{"chance": 100, "age_group": ...}` entries. Multiple keys inside one
`{...}` are ANDed, so a villager cannot be both — separate entries are how you write an OR. The
`{"chance": -1000, "memory": ...}` entry is the sink that stops this branch from beating the
asked-recently branch on a repeat visit.

**3. Rough night.** Scored on `mood`, sunk on the two young age groups (an unhappy child gets the
child line, not the adult one) and on the cooldown. `mood` is string-compared inside MCA and fails
*soft*: a typo here never matches instead of crashing. `age_group` and `current_chore` are the
opposite — they are parsed strictly, and a bad value throws out of the datapack reload and takes the
world load with it. Every value used here is from the documented valid set.

**4. Ordinary night — the branching fallback.** `baseChance: 1`, with a `-2000` or `-1000` sink for
every state the earlier results claim. That is the whole pattern: the specific branches score up, the
general branch scores 1 and sinks itself out of every situation a specific branch handles.

**5. Plain MCA — the real last resort, and it must be last.**

```json
"conditions": [
  {"chance": -2000, "conversations_enabled": "branching"},
  {"chance": -1000, "memory": {...}}
],
"actions": {"positive": 2, "conversations_record": {...}, "next": "conversations.cat.chitchat", ...}
```

Results 1–4 all sink `-2000` on `conversations_disabled: "branching"`. If the pack stopped at result
4, then a player who turned branching off in the config would get a starter whose every result scored
negative, and MCA — which picks the **last** result when nothing is positive — would hand them result
4 anyway: a `conversations_session` begin, a route into a response page, and a conversation the
runtime is no longer driving. Result 5 exists so that the last element of the array is the one that
is safe to fall into. It uses MCA's native `positive` heart action, hops straight back to the
category page, and re-uses the ordinary say pool.

There is a second, sharper case. With branching **off and** the cooldown **active**, result 5's own
`-1000` cooldown sink makes even *it* negative — so every result scores ≤ 0 and MCA runs off the end
of the loop and takes the last one. Result 5. Which is exactly what you want, and is only true
because it is written last. Swap results 4 and 5 and this pack silently becomes broken in one
specific config, which is the least debuggable kind of broken.

#### Action ordering, inside every result

```json
"actions": {
  "conversations_session": {...},
  "conversations_record": {...},
  "next": "sleep.respond",
  "conversations_say": {"phrase": "sample_sleep.ordinary"}
}
```

Actions run in **JSON key order**, and the interact screen holds exactly one speech slot. `next`
writes the destination question's own prompt into that slot. A `say` written *before* `next` is
therefore overwritten and the player never reads it — the line is spoken into a slot that is about to
be replaced. The order is always: state actions (`conversations_session`,
`conversations_progress_apply`, `conversations_affection_apply`, `conversations_disposition_apply`,
`conversations_record`, `remember`) → `next` → `say`.

Only eight of those keys are MCA's own. Its complete native action set on 1.21.1 is `next`, `say`,
`remember`, `quit`, `negative`, `positive`, `command` and `time` — `time` being the one this
version adds. Everything else you will see in an `actions` block, every `conversations_*` key,
comes from this mod's registrar.

The corollary is that the `dialogue.<question>` prompt in the lang file is a **fallback**, shown only
when a result arrives carrying no line of its own. Ours all carry one, so `dialogue.sleep.respond`
is almost never seen — but it still has to read acceptably, because "almost never" is not never.

`conversations_session` with `{"op": "begin", "topic": "sleep", "budget": "quick", "branch": "...",
"beat": "..."}` frames the exchange. It grants nothing at all: the opener is bookkeeping. `topic`
must name a row in the catalog, `budget` names a depth class, `branch` records which way the opener
went so sibling branches can share a page, and `beat` names the semantic contract for the line about
to be spoken.

`conversations_record` is the multi-write form of `remember`; here it writes one expiring memory,
because JSON keys cannot repeat and later results in this pack would otherwise need two `remember`s.
`"var": "player"` suffixes the id with the player's UUID, so the cooldown is per player. `12000`
ticks is half a Minecraft day.

The memory id is `sample_sleep.cooldown.sleep` — the pack's own namespace. The mod owns
`mcaconversations.*` and you may read those flags freely, but you must not write into them.

---

### `data/sample_sleep/dialogues/sleep.respond.json`

Three answers: `dream` (curiosity), `glad` (encouragement), `leave` (exit). Two to five answers per
node, and at least one consequence-free way out, is the floor.

This page is opened by **three** different beats — the ordinary opener, the asked-recently opener,
and the child opener. That is a constraint on the wording of the buttons, not just on the routing.
MCA decides which buttons to show from the *answer's* own constraints; it never looks at which
villager line led there. So a button here must make sense after all three lines, and — because the
client picks the say variant and the server never learns which one — after **every variant** of each
of those three pools. "Did you dream about anything?" is safe after all nine possible lines because
all nine assert the same single fact: the villager slept and has just reported on it. That fact is
written down as `sleep:reported` in the beat file, and the reply contract for each button that leans
on it — `dream` and `glad` — declares `requires_facts: ["sleep:reported"]`, so a lint can check the
claim instead of a human remembering it. The three `leave` replies declare no `requires_facts` at
all: an exit presupposes nothing, which is what makes it safe on any page.

`dream` carries a `conversations_disposition_apply` and no hearts. Curiosity that costs the villager
nothing should move `familiarity` and `trust`, not affection. Per-axis deltas are clamped to ±10 at
parse; a per-day cap and same-day diminishing returns are applied on top at runtime.

`glad` has **two results**, and this is the pack's answer to "there is no universally correct button":

```json
{"baseChance": 0, "conditions": [{"chance": 100, "conversations_personality": ["gloomy","anxious","crabby"]}], ...}
{"baseChance": 1, "conditions": [{"chance": -2000, "conversations_personality": [...]}], ...}
```

For a gloomy, anxious or crabby villager the first result wins (100 against a negative), routes to a
different beat, says a flatter pool and grants no hearts. For everyone else the first scores 0 and
the second scores 1. Exactly one positive result in every state, in both directions.

Use **`conversations_personality`**, never MCA's native `personality` condition. The native parser
throws on a personality id the running MCA build does not know, and MCA 7.7 renamed four of them —
so any single authored value is a crash on one MCA version or the other, and that crash aborts the
datapack reload and the world load. `conversations_personality` never throws and resolves the legacy
ids to their successors.

`glad`'s second result is where the pack's first heart change lives:

```json
"conversations_affection_apply": {"decision": "sleep.ordinary.glad", "delta": 1,
                                  "budget": "quick", "policy": "daily_repeat"}
```

`decision` is a **stable id**. It keys anti-farming, the debug output and the tests, so it must never
be reused for a semantically different choice. `budget` names the depth class whose per-conversation
cap applies — `quick` is +2/−3 — and `policy` is stated explicitly rather than left to the
`daily_repeat` default, so that a later reader can see the intent rather than infer it. Hearts move
through this action and no other. Native `positive`/`negative` in branching content bypasses every
guard, which is why the only `positive` in this pack is on the non-branching fallback result.

`leave` ends the session and returns to the category page. No hearts, no dispositions, no memory: a
door you can always walk through without paying is the point.

---

### `data/sample_sleep/dialogues/sleep.rough.respond.json`

The rough-night branch needs a page of its own precisely *because* MCA cannot condition a button on
the line that led to it. "That sounds like a long night." would be a non-sequitur after the ordinary
opener, so the two openers open different questions. That is the whole reason beat contracts exist,
and it is the mistake a naive port makes first: one respond page, one set of buttons, and a quarter
of the exchanges reading like a bad transcript.

Four answers: `empathize` (empathy, +1), `ask` (curiosity, dispositions only), `brush_off`
(dismissal, −1), `leave` (exit).

`brush_off` is the pack's negative path. It is what makes the topic honest — at least one plausible
path must be able to lose affection, or the button set is decoration. Note where it goes:

```json
"conversations_session": {"op": "end"},
"conversations_affection_apply": {"decision": "sleep.rough.brush_off", "delta": -1, ...},
"conversations_disposition_apply": {"topic": "sleep.rough.brush_off", "deltas": {"warmth": -3, "tension": 4}},
"next": "conversations.cat.chitchat",
```

It ends the session and returns to the category page. It does **not** route into `sleep.close`,
because `sleep.close` offers "I've not been sleeping much either." and "Get a proper night tonight."
— two buttons that assume the stance landed and the exchange is companionable. A rebuff that arrives
at a page written for a landed stance is one of the most common and most jarring authoring bugs; the
rule is that a rebuff never routes into a landed close. The crossed boundary changes the
relationship (−1 heart, −3 warmth, +4 tension) without removing access to the villager: the topic is
askable again after the cooldown.

That does mean the brush-off path contains one decision rather than the two a `quick` topic asks for
on its normal paths. It is a rupture ending, deliberately short. If you want the fuller shape, add a
`sleep.rough.repair` page and route `brush_off` there — a page offering an apology, a doubling-down,
and a door — which is what the mod's own `day` topic does.

Note also that `ask` grants no hearts. A conversation in which every button pays is a conversation
where the buttons do not matter.

---

### `data/sample_sleep/dialogues/sleep.close.json`

One shared close page for five inbound beats: `dream`, `glad`, `glad.flat`, `rough.empathize` and
`rough.ask`. Sharing it is only legal because all five leave the conversation in the same place —
the villager has just said a little more about their night and is neither closing the subject nor
asking for anything. All five are declared `"openness": "permits_followup"` in the beat file for
exactly that reason. A page may not be opened both by a line that invites more and a line that closes
the subject; if one of these beats were `closes_subject`, it would need its own page.

Three answers again, one of them free. `share` and `wish` each grant +1 with distinct decision ids.
`wish` uses `"policy": "once_per_day"` rather than `daily_repeat` because "get a proper night
tonight" is a thing you say once a day and not a thing whose value halves on the second saying;
`share` uses `daily_repeat` because volunteering something about yourself is repeatable but should
diminish.

Add up a normal path: ordinary opener (0) → `glad` (+1) → `share` (+1) = +2, which is exactly the
`quick` class's per-conversation cap. The rough path is the same: `empathize` (+1) → `share` (+1).
There is no path in this pack that can exceed its budget, which is what the runtime would have to
clamp if there were.

---

### `data/sample_sleep/conversation_catalog/sleep.json`

The catalog is not a second dialogue engine. MCA's JSON stays authoritative; this row is the
machine-readable claim that the topic exists, so that lints can check it and so arc, milestone and
depth declarations live in exactly one place a typo cannot slip past.

- `entry` names the question and answer the topic is reached by — `conversations.cat.chitchat` /
  `sleep`, matching the merged file exactly.
- `depth: "quick"` sets the default budget for any `conversations_affection_apply` that omits one,
  and declares that normal paths owe two decisions and the topic owes two subject families. This pack
  ships four subjects (`sleep.ordinary`, `sleep.rough`, `sleep.young`, `sleep.again`).
- `return_question` is where exits land.
- `ages` must agree with what the dialogue actually reaches. Ours lists all four non-baby groups
  because the starter is `!baby` and has a child branch.
- `required_stance_families` must include **`exit`**. A topic with no graceful way out fails lint,
  and more to the point fails the player.
- `chat_required: true` asserts that every non-exit answer has a chat intent — which is the claim the
  intent file below has to make true.

The top-level `_comment` is safe: every loader in this mod reads one named sub-object (`topics`,
`beats`, `intents`, ...) and ignores everything else at the root. Do **not** put a `_comment` inside
an individual topic or beat entry, where a strict sub-parser may see it.

---

### `data/sample_sleep/conversation_beats/sleep.json`

Nine beats and ten replies. A **beat** is one say pool plus the question it opens, treated as a
single authored unit; a **reply** is one button, keyed `"<question>/<answer>"`.

Fields worth dwelling on:

- `subject` is *what the villager is talking about*, not which page the line lives on. It is what the
  session stores and what the depth measurement counts, so naming every beat after its page makes a
  topic look like one conversation in several costumes. Ours are `sleep.ordinary`, `sleep.rough`,
  `sleep.young`, `sleep.again`.
- `facts` are written `type:value` and must be true of **every variant** of the pool, because the
  client picks the variant and the server never learns which. `sleep:poor` sits only on the rough
  beats; `sleep:reported` sits on all of them. The reply contracts then declare `requires_facts`, and
  that is what makes "What kept you up?" checkably illegal on the ordinary page.
- `npc_act` says what the line *does*: `report` for the plain openers, `complain` for the rough one,
  `qualify` for the asked-again line and the flat personality variant, `disclose` for the dream
  answer, `explain` for the rough follow-up, `accept` for the two lines that take the player's stance
  well.
- `polarity` colours what is appropriate afterwards. `acute` — grief, fear, fresh harm — forbids
  humour and flirtation however playful the villager is. Nothing here is acute; the rough opener is
  `negative`, which is enough to forbid `humor` in its `forbidden_stances`.
- `openness` is what lets five beats share `sleep.close`, discussed above.
- `outcome` appears only on the beats where the villager is *reacting to a player line*
  (`engaged`, `appreciated`, `qualified`). The four openers have none, because nothing preceded them.
- `allowed_stances` / `forbidden_stances` must include `exit`. Every page needs a door, stated twice:
  once as an actual answer, once as a contract.

An unknown beat id in a `conversations_session` action is logged and ignored rather than thrown, so
misspelling one here loses your breadcrumbs, not the player's conversation. That is convenient and it
is also why nothing tells you at runtime; the beat file is the thing that lets a build tell you.

---

### `data/sample_sleep/chat_intents/sleep.json`

Eight intents — one per non-exit answer, bound to its exact `question` + `answer` pair. An intent
drives the engine exactly as a click on that button would, so binding to the wrong pair produces a
villager who answers a question you did not ask.

The `synonyms` block is worth understanding separately from the intents. Synonym blocks from **every
pack and namespace** are merged in a first pass, before any intent parses, and first writer wins per
alias. So `{"sleep": ["slept", "asleep", "sleeping", "kip"]}` broadens the vocabulary of every pack
installed, not just this one — a pack that ships only synonyms and no intents is a valid and useful
thing. Because keywords and phrases go through the same stemmer and synonym canonicalizer that the
player's typed message does, the keyword list only needs the canonical form: write `sleep`, match
`slept`.

Each intent gets:

- `keywords` with weights in `(0, 10]`. Distinctness across intents matters more than weight: two
  intents sharing a keyword set are two intents that will fight, and neither will win reliably.
- `requiresAny` — evidence the message must actually contain. Without it a high-weight keyword can
  drag in messages that merely rhyme with the topic.
- `phrases` — several natural paraphrases, not one canonical wording. These are the difference
  between an intent that matches how you would phrase it and one that matches how you wrote the
  button.
- `antiKeywords` on the starter: `goodnight` is a farewell, not a question about last night's sleep.
- `context` on the seven non-starter intents, naming the sub-question they belong to. A context-scoped
  intent only scores while the player's session has that question open, with a scoring bonus and a
  relaxed threshold. That is what lets `sample_sleep.close.share` get away with words as ordinary as
  "neither" and "either": inside `sleep.close` there is very little else those could mean.

The dialogue file's `constraints` are enforced automatically at match time, so the starter intent
does not repeat `!baby`.

Note that intent **ids** merge last-wins across packs. Redefining an id from another pack is possible
but the order the resource manager hands intent files over is not defined, so it is only reliable if
your pack sits above the mod in load order. This pack redefines nothing.

---

### `assets/sample_sleep/lang/en_us.json`

Fifty-nine keys, in three groups.

**Button labels** — `dialogue.<question>.<answer>`. A label is **what the player says**, in the
player's voice. Never "Persuade", never "+2 Warmth", never a success chance, and never
personality-flavoured — the villager has a personality, the player's own words do not. Labels are
also the one thing exempt from the repetition caps, because a label reading the same on every page is
an affordance rather than a tic.

A label may only reference detail that appears in **every** variant of the line it answers. "Did you
dream about anything?" is safe; "Which of them woke you?" would not be, because only one of the three
rough variants mentions being woken.

**Question prompts** — `dialogue.<question>`, with no answer suffix. These are fallbacks, shown only
when a result arrives with no line. They still need to read acceptably alone.

**Villager lines** — `dialogue.<phrase>`, where `<phrase>` is the `conversations_say` value *without*
the `dialogue.` prefix. Every pool here has three variants, `/1 /2 /3`. Three is the floor the mod
holds itself to; two is allowed only for precision-targeted pools and for keys ending `.child`,
`.teen`, `.crit`, `.success`, `.partial`, `.rebuff` or `.guard`. A **single-line pool carries no
suffix at all** — `dialogue.foo`, not `dialogue.foo/1`.

Translation keys are a single global map across every asset namespace, which cuts both ways: you do
**not** need to override `assets/mca_dialogue/lang/en_us.json` to add `dialogue.*` keys, and a key
you define that someone else also defines is a silent collision with no warning. Prefixing every say
key with `sample_sleep.` is the whole defence.

`%1$s` is the player's (spouse-aware) name and MCA prepends it to every line; template variables from
`conversations_say`'s `vars` array fill `%2$s` onwards. This pack uses no `vars`, so `%1$s` is the
only placeholder in it.

On the writing itself: the corpus this pack is imitating is plain and specific. No whimsy, no
catchphrase, no line reused between pools. Two habits are invisible while writing and unmistakable
while playing — a marked word creeping to the front of everything, and one goodbye for everybody —
so the three exit pools in this pack (`sample_sleep.leave`, `sample_sleep.rough.leave` and
`sample_sleep.close.leave`) are three genuinely different exits, and none of them is "Right
you are."

---

## The rules this pack is obeying

**Rule 1 — actions run in JSON key order, and `say` must come after `next`.** Obeyed in every result
of every file: `conversations_session` / `conversations_affection_apply` /
`conversations_disposition_apply` / `conversations_record`, then `next`, then `conversations_say`.
Written the other way round, `next` overwrites the single speech slot with the destination
question's prompt and the villager's line is never seen. The bug looks like "my line does not
appear", and the JSON looks fine.

**Rule 2 — when every result scores ≤ 0, MCA takes the LAST one.** Obeyed by the ordering of the five
results on the `sleep` answer, and by the two results on `glad`. The plain-MCA fallback is last on
purpose; with branching disabled *and* the cooldown active, every result is negative and MCA runs off
the end of the selection loop into that final element. Put it first and that same state hands the
player a branching route the runtime is not driving.

**Rule 3 — an `auto` question must have exactly one answer.** Obeyed by omission: this pack adds no
answer to any `auto` question. MCA's `root`, `chat` and `rumors` are auto, and `Actions.next` calls
`getRandomAnswer()` on them — so a second answer turns the node into a coin flip. Because answers
merge across data packs, a third-party pack can introduce that coin flip without touching the file
that declares the question. Do not merge into an auto question.

Two more the pack holds itself to:

**Weighted-random, not highest-wins.** Every answer with more than one result guarantees exactly one
positive total in every state, via negative sinks. `{"chance": 1, "conversations_progress": {"is":
"none"}}` sitting beside a result worth 100 is not a fallback; it is a 1-in-101 chance of the wrong
line.

**Never MCA's native `personality` condition.** It throws on an unknown id and takes the world load
with it. Use `conversations_personality`.

---

## How to adapt it

Change these first, in this order:

1. **The namespace and the prefixes.** Rename `sample_sleep` everywhere: the two folder names, the
   memory id `sample_sleep.cooldown.sleep`, and the `sample_sleep.` say-key prefix. Keep them
   consistent — a pack that owns one prefix cannot collide with anything.
2. **The topic id and the question names.** `sleep`, `sleep.respond`, `sleep.rough.respond`,
   `sleep.close`. Question names are global by basename, so pick something nobody else will pick.
   Update the catalog `entry`, every `next`, every beat `response_question`, every reply key, and
   every intent `question`.
3. **The say pools.** Three variants each, all asserting the same facts. This is most of the work and
   all of the quality.
4. **The branch conditions.** `mood` and `age_group` are the obvious axes; `conversations_season`,
   `conversations_weather` and `conversations_relationship` are the next ones to reach for.
5. **The intents.** Keep one per non-exit answer and keep the keyword sets distinct from each other
   and from the mod's own.

If you want a **deeper** topic, change `depth` to `standard` in the catalog and add nodes: standard
owes ten subject families, three disclosure levels and state-sensitive revisits, and it raises the
budget to +4/−5. Do not simply raise the deltas; the budget is per conversation, not per button.

---

## What this pack deliberately does not do

- **No checks.** Nothing here uses `conversations_check`, so no button has a tiered outcome and no
  roll is made. The `glad` split is a personality *condition*, which is deterministic, not a check.
- **No durable state.** No arcs, no milestones, no exclusive choices, no promises. The only thing
  written down is an expiring cooldown, which is bookkeeping rather than narrative. State that
  nothing reads back is state that should not be stored, and a two-decision chit-chat topic has
  nothing worth remembering next week.
- **No scene, no episode, no identity token.** The dynamic layer is not touched, so this pack behaves
  identically with `dynamic.enabled` on or off.
- **No optional-mod compat.** No weather, season, holiday, gossip, quest, reputation or capitals
  conditions.
- **No `pt_br`.** The mod ships English and Portuguese in the same change with matching keys and
  placeholders; a sample pack ships English only, and says so rather than shipping a half-translated
  second locale.
- **No personality overlay namespaces.** `assets/mca_dialogue_<personality>/lang/` would let a gloomy
  villager say the ordinary line differently, and its keys must be prefixed with the personality id
  (`gloomy.dialogue.sample_sleep.ordinary`). An *un*prefixed key in an overlay namespace is not an
  overlay — it is a global collision, and whichever resource pack loads last becomes every villager's
  voice.
- **No removal of anything.** This pack only adds. There is no `"remove": true` flag in any schema
  here, and merged answers cannot be un-merged.
