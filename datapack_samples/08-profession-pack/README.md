# 08 — profession pack (`sample_chandler`)

Work-talk for a trade that some **other** mod supplies: a chandler, a candle-maker.

> ## Read this first
>
> The profession id in this pack is **`examplemod:chandler`**, and `examplemod` is a placeholder.
> **Nothing in this pack matches anything until you replace `examplemod` with the real mod id** that
> registers the profession — in `data/sample_chandler/profession_profiles/examplemod.json` (both the
> filename and the key inside it) and in the `"profession": "examplemod:chandler"` condition in
> `data/sample_chandler/dialogues/conversations.work.json`.
>
> Until then the pack loads cleanly, logs nothing, and every villager falls through to the generic
> line. That is the correct behaviour for a profession from an absent mod, and it is the same
> behaviour you get if you install this pack without the mod at all. **A profession condition naming
> a mod that is not installed simply never matches. It is not an error and it never crashes.**

---

## What it demonstrates

- The **one-mod-per-file split**: `profession_profiles/examplemod.json`,
  `conversation_beats/work_examplemod.json`, `chat_intents/profession_examplemod.json`.
- A `profession_profiles` entry with a real `archetype`, an `owner`, ten `subjects` and three
  `callback_types`.
- Merging a profession-scored result into the mod's `conversations.work` `auto` router — including
  mirroring its top-level flags, and what merging into an `auto` question actually costs.
- Routing the trade to **its own page**, `chandler.respond`, rather than into another trade's page.
- The generic templated fallback (`conversations.work.generic` with the `profession_name` var) that
  every unheard-of trade gets, and why that is the deliberate coherent default.
- A personality-scored `challenge` stance with a landed result and a stung result, and a followup
  page that is safe to reach from either.

---

## Install

Both halves, as always — Minecraft serves `data/` only from a world's datapack folder and `assets/`
only from a resource pack folder:

```
08-profession-pack/datapack/     -> saves/<world>/datapacks/08-profession-pack/
08-profession-pack/resourcepack/ -> resourcepacks/08-profession-pack/
```

```
/reload      — dialogue, profiles, beats, intents
F3+T         — the lines and the button labels
```

`pack_format` is **48** in `datapack/pack.mcmeta` and **34** in `resourcepack/pack.mcmeta`. On
1.21.1 the data and resource formats have diverged, so one manifest can no longer serve both halves
— hence two folders. Paths written `data/…` or `assets/…` below are paths inside an installed
pack.

---

## Try it

You need a villager whose profession is `examplemod:chandler` (after the rename, a villager of the
real trade). Then: **"Let's talk properly" → Work → "What do you do?"** — or **Chat → Work** if
you set `hubEntryMode = REPLACE`.

```
/conversations context snapshot
```
Look at `work.profession_id` and `work.archetype`. If `profession_id` does not read exactly what your
`"profession"` condition says, that is the whole bug — mod profession ids are `namespace:path` and
the namespace is the *mod id*, not the mod's display name.

```
/conversations chat debug-ask chandler.respond ask_hard
```
Drives the villager you are looking at straight to that button, so you can test the page without
getting the router to route. This is also the fastest way to see whether your lang keys resolved.

```
/conversations chat debug what is the hard part
```
Scores that sentence against every intent. Expect `work.chandler.ask_hard` to win **while the
chandler page is open** — its `context` field means it barely competes otherwise.

Change the villager's personality (or find another chandler) to see both halves of the `challenge`
stance: `extroverted`, `crabby`, `greedy`, `relaxed`, `peaceful` and `upbeat` take it on the chin,
everyone else is stung by it.

---

## File-by-file walkthrough

### `data/sample_chandler/profession_profiles/examplemod.json`

The machine-readable claim about what the trade *is*. It is not dialogue and it does not select
anything on its own; it is what lets scenes, callbacks and lint reason about a profession they have
never seen a line for.

```
"archetype": "craft"        one of cultivation, food, craft, knowledge, defense,
                            exploration, occult, untraded
"owner": "examplemod"       the mod that supplies the profession, or "base"
"display_fallback": "chandler"
"subjects": [10 of them]    at least six is the floor
"callback_types": [3]       at least two is the floor
"season_affinity": true     candles are a winter trade
```

The two floors are not arbitrary. **Six subjects** is the point below which a trade becomes one line
and a shrug — there is nothing to come back to. **Two callback types** is the point below which a
trade remembers nothing, and a villager who remembers nothing cannot have a second conversation with
you. This profile declares `order_placed`, `shortage_named` and `player_help`.

`archetype` exists so trades can share *routing, condition shapes and callback plumbing*. It must
never be read as permission to share sentences: a mason and a woodworker both make things that
outlive them, and that is worth a shared schema, not a shared line. `craft` here buys the chandler
nothing a hand-written page would not, and costs nothing either.

`owner` is what keeps an optional trade from looking like a broken base one. A profile with
`"owner": "examplemod"` that never matches is a mod you do not have installed; a base profile that
never matches is a bug.

### Why one file per mod

`profession_profiles/examplemod.json`, `conversation_beats/work_examplemod.json` and
`chat_intents/profession_examplemod.json` are all named after the **supplying mod**, not after the
trade and not after this pack. The loaders merge every file in a directory, so at runtime the
filename changes precisely nothing.

It changes everything the day `examplemod` renames `chandler` to `candlemaker`, drops the profession,
or you stop supporting that mod. With the split, that is `git rm` on three files and one edit in the
router. Without it — one `professions.json` holding six mods' trades — it is a careful surgical edit
across three shared files, in which the easy mistake is deleting one line too many and silently
breaking a different mod's trade. The mod itself ships its optional trades this way (Ars Nouveau,
Farmer's Delight, Ice and Fire, More Villagers, Vampirism, Werewolves) and its build enforces it.

The mod additionally splits terminal beats into `conversation_beats/terminal_work_<owner>.json`.
This pack keeps its two terminal beats in the one beats file, because there are two of them; the
split is worth making at the point where the file stops fitting on a screen.

**No optional mod goes in `mods.toml`.** A datapack has no `mods.toml` to put it in, but if you are
shipping this inside a jar: do not declare `examplemod`, not even `mandatory = false`. A declared
dependency is a claim about a mod that is not needed to load. The `profession` condition is MCA's
own, it takes a string, and a string naming an absent mod matches nothing.

### `data/sample_chandler/dialogues/conversations.work.json`

The extension of the mod's profession router. Three things about this file matter more than its
contents.

**1. The basename must match exactly.** `conversations.work.json`, no prefix. MCA keys questions by
basename and merges the `answers` arrays of every file that shares one. That is the extension
mechanism.

**2. The top-level flags must mirror the original.** The shipped file is:

```json
{ "auto": true, "silent": true, "answers": [ ... ] }
```

so this one is too. MCA keeps the flags of whichever same-named file loads **last**, and that order
is undefined. An extension that omits `"auto": true` will, half the time, turn the router into a
visible question with buttons.

**3. Merging into an `auto` question costs something, and you should know what.** `auto` questions
are answered by `getRandomAnswer()`, and merging appends a *second answer* to the list. So on a
chandler, MCA now flips a coin between the mod's router answer and ours. Both are complete: the
mod's answer knows about forty professions and ends in the generic fallback, and ours knows about the
chandler and ends in **the same generic fallback**. Whichever way the coin lands, the villager says
something true. What you lose is that half of a chandler's work-talk comes out as the generic line
instead of the chandler line.

If you would rather not pay that: ship your result *inside* a replacement of
`data/mcaconversations/dialogues/conversations.work.json` at that exact path, which replaces the mod's
file wholesale (the loader takes the top-most resource per id, not a stack). That is a much larger
commitment — you now own every profession result in the mod, forever, including the ones added in the
next release. The merge is the cheaper trade for a sample, and the mod's own documentation names it
as the supported route.

The answer itself has two results:

| # | Gate | Route | Says |
|---|---|---|---|
| 1 | `{"chance": 100, "profession": "examplemod:chandler"}` | `chandler.respond` | `chandler.work.identity` |
| 2 | `baseChance: 1`, no conditions | `conversations.topic.work.pride.respond` | `conversations.work.generic`, `vars: ["profession_name"]` |

Note the answer has **no `name`**, matching the shipped file: an `auto` question's single answer is
never labelled or clicked.

**Result 2 is the fallback and it goes last**, for the usual reason — when every result of an answer
scores ≤ 0, MCA takes the last one. It is also why this pack cannot "decline" to answer for a
non-chandler: an answer always produces a result. So the fallback is the generic templated line,
routed to the mod's own generic page and beat.

**Why the generic fallback is the right default and not a cop-out.** The line is *"Being a %2$s
isn't glamorous, but it's mine, and I'm better at it than most."* — the `profession_name` var renders
any mod's profession, client-side localised, so a trade nobody has ever written a line for still gets
a sentence that is true of it, in the player's language. The alternative for an unknown trade is
silence or a base-profession line that is simply wrong about them. A shrug that fits is better
content than a specific claim that does not.

**Route to your own page.** Result 1 goes to `chandler.respond`, not into
`conversations.topic.work.farmer.respond` or any other trade's page. Merging into someone else's page
would hand the chandler a set of buttons written about a different job, and MCA — which picks buttons
from the answer's own constraints, never from the line that led there — has no way to stop it.

### `data/sample_chandler/dialogues/chandler.respond.json`

Four answers: `ask_hard` (curiosity), `value` (encouragement), `challenge` (challenge) and `leave`
(exit). Actions everywhere in **state → `next` → `say`** order:

```
conversations_session (turn)  ->  conversations_affection_apply  ->
conversations_disposition_apply  ->  next  ->  conversations_say
```

`challenge` is the only answer with two results, split on `conversations_personality`:

- **landed** (`extroverted`, `crabby`, `greedy`, `relaxed`, `peaceful`, `upbeat`): +1 heart,
  respect +4, and a line that concedes the point.
- **stung** (everyone else, `baseChance: 1` with a −2000 sink on the same personality list): −1
  heart, tension +4.

Exactly one of the two is positive in every state, which is the property a multi-result answer must
have — selection is weighted-random over positive totals, so "the other one probably won't win" is
not a design. Use **`conversations_personality`, never MCA's native `personality` condition**: the
native one throws on an id the running MCA does not know and takes the whole datapack reload — and
the world load — down with it. This one never throws and resolves legacy ids to their successors, so
one authored list works across MCA versions. Every id in the list is one a 1.21.1 villager can roll:
MCA no longer registers `confident`, `peppy` or `athletic`, so naming those would give you a branch
nobody ever reaches.

`leave` ends the session and returns to `conversations.cat.profession`, which is where work-talk
came from. It grants nothing.

### `data/sample_chandler/dialogues/chandler.followup.json`

Three answers: `ask_more`, `thanks`, `leave`. All four beats of the previous page route here,
including the stung one, so its two buttons have to be true after a rebuff as well as after praise:
"Is there anything you refuse to make?" and "I had not thought of it that way" both read as
de-escalation. This is the same shape the mod uses for its own optional trades. The rule being
obeyed is narrow but strict: **a rebuff must not route into a page that assumes the stance landed** —
a button like "Glad you agree" here would break it.

`thanks` uses `"policy": "once_per_day"` rather than `daily_repeat`, because it is a small ceremonial
line and repeating it should not pay twice.

### `data/sample_chandler/conversation_beats/work_examplemod.json`

Seven beats and seven reply contracts. A beat is one `say` pool paired with the `next` question it
opens, treated as a single authored unit — MCA cannot see which line led to a page, so the contract
is what makes "these buttons belong under that line" checkable.

- `facts: ["work:chandler"]` on the opener, `requires_facts` on the replies: the buttons on
  `chandler.respond` presuppose the villager has said what they do. A reply may not presuppose a
  referent every inbound beat does not introduce.
- `allowed_stances` / `forbidden_stances` are what the page may and may not contain. The opener
  forbids `empathy` and `practical_help` — nobody needs consoling about being a chandler — and
  forbids `flirtation`, `boundary_push` and `dismissal` everywhere.
- The two terminal beats (`work.chandler.more`, `work.chandler.thanks`) have
  `response_question: "conversations.cat.profession"` and `allowed_stances: ["exit"]`. That is what
  makes the exchange four turns and then over, rather than an endless tree.
- `outcome` on the six beats that answer a player line (`engaged`, `appreciated`, `qualified`,
  `resisted`) is what a callback later reads back through `conversations_exchange` — it records what
  was *decided* about a subject, not merely that the subject came up. The opener
  `work.chandler.identity` carries none, correctly: nothing preceded it, so nothing was decided.

### `data/sample_chandler/chat_intents/profession_examplemod.json`

One intent per non-exit button — five of them — each bound to its exact question and answer, each
`context`-scoped to its own page, each with distinct keywords and a `requiresAny` evidence floor.
`leave` gets none; "never mind" is chat mode's own exit.

The keywords are deliberately about *the thing the player is saying*, not about candles: `hard`,
`difficult`, `tricky` for the first, `smoke`, `stink`, `cheap` for the challenge. Keywords shared
across two of your own intents make them fight each other, and keywords borrowed from the trade's
vocabulary make them fight every other trade's page.

Intent **ids** merge last-wins across datapacks, so `work.chandler.ask_hard` is a name you are
claiming globally. Prefix accordingly if you ship more than one profession.

### `assets/sample_chandler/lang/en_us.json`

Page headers, button labels, and each say pool with **two** variants rather than three. That is the
mod's own floor for precision-targeted pools: every shipped `conversations.work.prof.<trade>.*` pool
is exactly two, because a line this specific to one trade and one button is heard rarely, and two
good variants beat three with a filler. General-purpose pools still want three.

The generic fallback needs no lang key from this pack — `dialogue.conversations.work.generic` already
exists in the mod.

---

## The rules this pack is obeying

1. **Fallback last**, because MCA takes the final result when nothing scores positive.
2. **`say` after `next`**, because `next` overwrites the one speech slot.
3. **An `auto` question has exactly one answer** — the mod's does, ours does, and the section above
   is honest about what the merge does to that.
4. **Extension files mirror the original's top-level flags.**
5. **Never MCA's native `personality` condition.**
6. **Hearts only through `conversations_affection_apply`**, each with a stable `decision` id and an
   explicit `policy`. The opener grants nothing at all.
7. **Exactly one positive result in every state** on the multi-result `challenge` answer.
8. **Every node has a consequence-free exit.**
9. **Button labels are what the player says.**
10. **One mod's content in files named after that mod**, so removing support is deleting files.
11. **Every non-exit button has a chat intent bound to its exact question and answer.**

---

## How to adapt it

1. **Rename `examplemod` everywhere.** Three filenames' worth and two ids: the profile key, the
   `owner`, and the `"profession"` condition. Check the result with
   `/conversations context snapshot` against `work.profession_id`.
2. **Then rename the trade** if it is not a chandler: `chandler.respond` / `chandler.followup`, the
   beat ids, the `chandler.work.*` say keys and the intent ids. Question names must carry a unique
   prefix — MCA keys them by basename across every namespace, so `respond.json` would collide with
   the world.
3. **Add a second profession from the same mod** by adding a second key to the *same* profile file, a
   second set of beats to the *same* beats file, and a second result to the router — and a second
   page. Same mod, same files.
4. **Add a subject** by extending `subjects`, adding a beat and a page for it, and adding a result to
   `chandler.respond`. Ten subjects is a comfortable trade; six is the floor.
5. **Do not** raise `delta` past what the depth class allows. `standard` is +4/−5 per conversation;
   the runtime caps it regardless, and content that assumes otherwise silently does nothing.

## What this pack deliberately does not do

- **It does not touch another trade's page**, or any shipped page except by naming the mod's own
  generic route in its fallback.
- **It does not replace `data/mcaconversations/dialogues/conversations.work.json`.** The blunt
  same-path override is described above and is pack 09's subject; the cost is owning every profession
  result the mod ever ships.
- **No scenes, episodes or callbacks.** `callback_types` is declared in the profile, but nothing here
  reads it back — the `conversations_exchange` callback idiom is pack 04's, and profession scenes
  are pack 05's.
- **No `terminal_work_examplemod.json`.** With two terminal beats the split is not yet earning its
  keep; the mod's own trades do split them and you should when yours grow.
- **It has not been run in a production MCA instance.** It is authored against the shipped schemas,
  the documented engine rules, and the mod's own optional-profession content as it stands.
