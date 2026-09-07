# 09 — modify-native

**Namespace:** `sample_retune` **Prefix:** `retune`

Changing content that MCA: Conversations already ships, *without removing any of it*. Five
techniques, one file each, and the price of each written down next to it. Pack **10-remove-native**
is the other half of this pair: it is about subtraction, this one is about adjustment.

Read this one before you write any override of your own. Two of the five techniques here are
**not deterministic**, and the README says exactly which two and why.

---

## What it demonstrates

1. **Adding to a shipped question by same-basename merge** — `dialogues/conversations.cat.chitchat.json`
   adds a new `moon` answer to the mod's chit-chat page, and uses `priority` to decide where the
   button lands. Additive, safe, nothing is overridden.
2. **Re-keywording a shipped chat intent** — `chat_intents/retune_rekeyword.json` redefines the
   shipped `chitchat.weather` intent id; `chat_intents/retune_synonyms.json` broadens vocabulary
   with a file that declares no intents at all.
3. **Redefining an interiority profile** — `interiority/sample_retune.json` gives `gloomy` a taste
   for humour, so one personality answers one stance family differently.
4. **Re-tuning a shipped scene** — `conversation_scenes/sample_retune.json` redefines
   `work.librarian.current_task.evergreen`. Scenes are the one catalog where redefining an id is
   deterministic.
5. **Overriding a shipped `dialogue.*` lang pool** — `assets/mca_dialogue/lang/en_us.json` rewrites
   one button label and one three-variant say pool. The least invasive change in the whole document.

And one anti-technique, documented but deliberately **not shipped**: replacing a whole mod file at
its exact path. See *The blunt instrument*, below.

---

## Install

This pack has a `data/` half and an `assets/` half, and Minecraft will not serve them from the same
place. Copy the **whole folder** into both:

- `saves/<world>/datapacks/09-modify-native/` — Minecraft reads only `data/` from here.
- `resourcepacks/09-modify-native/` — Minecraft reads only `assets/` from here, and you must
  **enable it in Options -> Resource Packs**.

Reload:

- `/reload` for everything under `data/` (dialogues, chat intents, interiority, scenes).
- `F3+T`, or toggling the pack in the resource pack screen, for everything under `assets/`.

`pack_format` is **15** — correct for 1.20.1 as both a data pack and a resource pack.

### Pack ordering matters here

Techniques 2 and 3 depend on which file the game reads *last*, and technique 5 depends on which
resource pack sits *above* the mod. In the resource pack screen, drag this pack to the **top** of
the enabled list; packs higher in that list win. Data packs are ordered by `/datapack list` and
`/datapack enable <name> first|last` — a datapack in the world folder already outranks the mod's
built-in data, which is what makes same-path replacement work at all.

---

## Try it

**Technique 1 — the added answer**

```
/reload
```

Talk to any adult villager -> **Conversations** -> **Chit-chat**. "Do you ever watch the moon?" is
the **first** button on the page, above "How is your day?". That position is the `priority` field
doing its job (see below). Pick it, answer, and you land back on the chit-chat page.

With chat mode on, this also works typed:

```
/conversations chat debug do you watch the moon
```

should score `retune.chitchat.moon` highest.

**Technique 2 — the re-keyworded intent**

```
/conversations chat debug is the wind getting up
```

The shipped `chitchat.weather` intent has no `wind` keyword; this pack's redefinition does. If
`chitchat.weather` wins that message, your redefinition is the one that loaded. If it does not,
*your redefinition lost the race* — that is the failure mode, and it is described below.

```
/conversations chat debug the wheather today
```

tests the synonyms-only file: `wheather` canonicalises to `weather` before any intent is scored.

**Technique 3 — the interiority profile**

Find a `gloomy` villager (`/conversations profile inspect` on the nearest one prints the
personality) and take a checked stance in the `humor` family. Against the shipped profile a joke
costs a gloomy villager 6 points of check score; against this one it gains 4. That is a 10-point
swing, and still **less than one tier margin** — it colours the outcome, it does not decide it.

```
/conversations context snapshot
```

prints `speaker.personality` if you would rather not guess.

**Technique 4 — the retuned scene**

```
/conversations scene candidates work
```

looking at a librarian. `work.librarian.current_task.evergreen` should now be gated by
`cooldown_days 2` and `max_mentions_per_7_days 2` rather than the shipped `cooldown_days 0`, and it
should stop being offered on consecutive days.

```
/conversations scene plan
```

prints the frozen plan, including the scene's route.

**Technique 5 — the lang override**

Open Chit-chat: the weather button now reads "What is the sky doing today?". Ask it in the rain and
the villager's opening line comes from this pack's three-variant pool, not the mod's. No `/reload`
needed — `F3+T` is enough.

---

## File-by-file walkthrough

### `pack.mcmeta`

`{"pack": {"pack_format": 15, "description": "..."}}`. Nothing else. The same file serves as both the
data pack and the resource pack manifest.

### `data/sample_retune/dialogues/conversations.cat.chitchat.json` — technique 1

The basename is `conversations.cat.chitchat`, which is exactly the mod's chit-chat category page.
That is deliberate, and it is the one place in this pack where matching a shipped name is *safe*:

> MCA's `Dialogues.loadDialogue` keys questions by `path.substring(path.lastIndexOf('/') + 1)` — the
> **basename only, namespace discarded**. Two files with the same basename in different namespaces
> are merged with `Question.merge`, which is `answers.addAll(other.getAnswers())`. Then the combined
> list is sorted by `Answer.getPriority()` **ascending**.

So this file contributes one extra answer and touches nothing the mod wrote.

**Fields**

- `"name": "moon"` — the answer id. It must not collide with a name the shipped page already uses
  (`day`, `food`, `weather`, `season`, `routine`, `back`). See *the same-name trap* below for what
  happens if it does.
- `"priority": -1` — every shipped answer on this page omits `priority`, and `Answer.fromJson`
  defaults it to `0`. `-1` therefore sorts this answer **before all of them**. Leave `priority` out
  and you get `0`, which ties with every shipped answer; `List.sort` is stable, so a tie preserves
  merge order and your answer lands *after* `back` — under "Never mind", which looks broken.
  **The cost:** `priority` is a coarse instrument. Because the shipped page gives every answer the
  same `0`, you can land before all of them or after all of them, and there is no way to slot in
  between `routine` and `back` without taking ownership of the whole file.
- `"constraints": "!baby,!toddler"` — one string, split on commas, AND-only. Note there is **no
  `child` token** in MCA's `Constraint.REGISTRY`; unrecognised tokens are silently dropped, so
  `!child` is not a gate, it is a no-op. (The mod's own content has nine answers with `!child` in
  them. Do not copy that.)

**Results, in order** — MCA scores every result and picks a **weighted-random** winner among the
positive totals, and if every total is `<= 0` it takes the **last** one. Both facts shape this list:

1. Night line. `baseChance 0`, `+900` on `time_min: 13000`. At night this outweighs the ordinary
   result 900:1. It is a weight, not an exclusion — that is the mod's own idiom on the shipped
   `weather` answer, and it means the ordinary line still shows up about one night in nine hundred.
2. Ordinary line. `baseChance 1`, no positive condition. This is the day-and-most-of-the-time route.
3. Legacy fallback, **last**. `baseChance 1`, sunk `-2000` on `conversations_enabled: "topics"`. It
   is negative whenever topics are on, and the only positive result when they are off. That is the
   complementary-pair idiom: results 1 and 2 sink on `conversations_disabled: "topics"`, result 3
   sinks on `conversations_enabled: "topics"`, so exactly one branch is ever live.

Actions are ordered `next` -> `conversations_say` in every result. `say` after `next` is an engine
rule, not a style preference.

#### The same-name trap

You cannot add *results* to a shipped answer. The merge is at the **answer** level, and this is
worth spelling out because it is the single most tempting wrong idea in this document:

- `Question.merge` is a plain `addAll`. Nothing dedupes by answer name.
- The client builds one button per entry in that list, so an answer named `weather` merged onto a
  page that already has one renders **two identical buttons**.
- `Question.getAnswer(name)` walks the list and returns the **first** match. After the priority sort
  that is whichever of the two has the lower `priority` — and on a tie, whichever file the resource
  manager happened to merge first.

So shipping an answer named `weather` does not extend the shipped `weather` answer. It either does
nothing visible except add a duplicate button, or it silently hijacks the shipped name and leaves a
dead second button behind, and which of the two you get is not something you chose. **Give your
answers your own names.**

### `data/sample_retune/dialogues/retune.moon.respond.json` — technique 1's landing page

A plain `{"answers": [...]}` page. Three answers, which is inside the 2–5 rule, and `leave` is the
consequence-free way out. Each answer is the same complementary pair as above: a `conversations_say`
result sunk when topics are off, and a plain result sunk when they are on.

Nothing here grants hearts, opens a session, or writes disposition. That is on purpose — see
*What this pack deliberately does not do*.

### `data/sample_retune/chat_intents/retune_rekeyword.json` — technique 2

Declares the id `chitchat.weather`, which `data/mcaconversations/chat_intents/chitchat.json` also
declares, with a wider keyword set (`wind`, `fog`, `cloud`, `forecast`) and two more phrases.

`ChatIntentLoader.apply` folds every `chat_intents` file into one map keyed by id and does
`byId.put(id, binding)` — **last writer wins, silently, no warning**.

**The cost, stated plainly.** The loader iterates `files.entrySet()`, and `files` comes from
vanilla's `SimpleJsonResourceReloadListener.prepare`, which is a `java.util.HashMap`. **The
iteration order is hash order — not pack order, not alphabetical order.** It is stable for a given
set of installed files, so it will look reliable while you test, and it can flip the moment any
other pack adds or removes a `chat_intents` file. `conversation_catalog` and `conversation_beats`
have exactly the same problem.

**The deterministic version** is to replace the mod's file at its exact path:
`data/mcaconversations/chat_intents/chitchat.json`. Resources resolve one per `ResourceLocation`
with the top-most pack winning, so your copy is read and the mod's is not read at all. That is
guaranteed — and it makes every one of the intents in that file yours to maintain, forever. Redefine
the id when you can live with the uncertainty; replace the path when you cannot.

**A redefinition must still satisfy the parser.** `IntentBinding.fromJson` throws (and the intent is
skipped, with the rest of the reload continuing) on: a blank id; not exactly one of
`question`+`answer` or `system`; zero keywords *and* zero phrases; a keyword weight outside
`(0, 10]`; a blank entry in any string array. A redefinition that throws does not "fall back to the
shipped one" — the shipped one was already overwritten in the map, or it was not, depending on
order. Do not rely on failure.

Also note: answer-level `constraints` from the dialogue file are enforced automatically at match
time. Do not restate them in the intent.

### `data/sample_retune/chat_intents/retune_synonyms.json` — technique 2, second half

No `intents` block at all, and it is still a valid file. Synonym classes are gathered in **pass 1**,
before any intent parses, from every `chat_intents` file in every namespace. So three lines of JSON
broaden the vocabulary of every shipped intent at once.

**The cost:** first writer wins per alias, and "first" is that same HashMap order. An alias another
pack already claimed is logged and dropped. Synonyms are also global — teaching the matcher that
`squall` means `storm` affects every intent that mentions storms, not only yours.

This is the cheapest and safest thing in this entire pack. If all you want is for players to be
understood when they phrase something differently, stop here.

### `data/sample_retune/chat_intents/retune_moon.json` — supporting technique 1

Every non-exit answer wants a chat intent bound to its exact question and answer name, or chat mode
cannot reach it. Three intents: one for the new chit-chat starter, two `context`-scoped to
`retune.moon.respond` so they only score while that page is open. Exit answers get no intent.

### `data/sample_retune/interiority/sample_retune.json` — technique 3

Redefines the `gloomy` profile: `humor` moves from `-6` to `+4` and `restraint` from `+6` to `+3`.
Everything else is the shipped profile, restated character for character.

**Restated on purpose.** `Interiority.apply` does `loaded.put(personality, profile)` with the whole
parsed profile. A redefinition **replaces**; it does not merge. Any axis you leave out is not
inherited from the shipped profile — it is simply absent, which the check maths reads as `0`.

**The clamps, and why they are small.** Baselines clamp to `±15`, stance bias to `±12`. Both are
less than one check-tier margin by design: personality colours an outcome, it never decides one. A
`±12` bias cannot turn a rebuff into a crit; it can turn a marginal partial into a marginal success.
If you find yourself wanting `+30`, what you actually want is a different `difficulty` on the check,
or a different `conversations_relationship` gate — not a bigger bias.

**The cost:** `interiority` is a `SimpleJsonResourceReloadListener` like the intents, so this is the
**same non-deterministic last-wins race** described above. The deterministic alternative is to
replace `data/mcaconversations/interiority/personalities.json` — all seventeen profiles, yours
forever.

There is also a design cost the mod holds itself to and you inherit: no personality may be punished
by all ordinary choices, `sensitive` is not fragile, `crabby` is not hostile, and every personality
keeps at least one warm route and one honest one. Handing `gloomy` a `-12` on `empathy` *and*
`humor` *and* `encouragement` would leave a player with no kind thing to say that works.

### `data/sample_retune/conversation_scenes/sample_retune.json` — technique 4

Redefines `work.librarian.current_task.evergreen`, a real scene from
`src/main/resources/data/mcaconversations/conversation_scenes/generated.json`. The shipped version
is a `base_priority` 6 filler with `cooldown_days: 0`; this one adds `cooldown_days: 2`,
`max_mentions_per_7_days: 2` and an `identity_values` lean toward `precision`, so a librarian
mentions what they are working on less often and it means more when they do.

**This one is deterministic**, and it is the only override in this pack that is:

> `SceneCatalogLoader` does `ordered.sort(ResourceLocation::compareTo)` before folding its files.
> Not the vanilla HashMap order — an explicit sort.

Last one in sort order wins, so **your namespace must sort after `mcaconversations`**.
`sample_retune` starts with `s`, `mcaconversations` with `m`, and `s > m`, so this file is read
second and this definition is the one that survives. A pack in a namespace called `awesome_scenes`
would sort *before* the mod and lose silently. If your namespace sorts early, either rename it or
fall back to replacing `generated.json` at its exact path — a 373-scene file you would then own.

**Re-tuning, not re-pointing.** This redefinition keeps `route.question` and `route.opening_beat`
pointing at the mod's existing page and beat, and you should think hard before changing them:

- `route` is what the director freezes into the plan, and it is what `/conversations scene plan`
  prints.
- A player who opens a topic reaches the scene through a **dialogue result carrying a
  `conversations_scene` condition**, and that result brings its *own* `next` and its own `beat`,
  written in the dialogue file. Repointing the scene's `route` does not rewrite those shipped
  dialogue results.
- If you do point `route` somewhere new, both the question and the opening beat must already exist
  and be contracted, or the plan names something unreachable.

**Everything is restated** for the same reason as the interiority profile: the loader puts a whole
scene definition. Fields you drop are gone, not inherited.

**Loader rules a redefinition still has to satisfy**: `purpose` and `shape` come from closed
vocabularies; a `fallback` must exist, must not be the scene itself, must not close a loop, and must
share this scene's purpose **and** topic; a chain longer than four hops is walked for four and then
abandoned to the static route; a single profession leaf is bounded at 128 scenes. And
`max_mentions_per_7_days` and `cooldown_days` are hard gates, not scores — raising them removes the
scene from consideration outright rather than making it less likely.

### `assets/sample_retune/lang/en_us.json` — this pack's own lines

Every key this pack invents. Note it lives under **this pack's own namespace**: translation keys are
a single global map merged from every namespace, so you never need to write into `mca_dialogue` to
*add* a key. You only need to for technique 5, which *replaces* one.

Say pools get three variants (`/1 /2 /3`); the mod's floor, relaxed to two only for tier pools and
keys ending `.child`, `.teen`, `.crit`, `.success`, `.partial`, `.rebuff`, `.guard`. A single-line
pool has **no** `/1` suffix at all.

Button labels are what the *player* says — "I watch it most nights myself.", not "Agree" and
certainly not "+2 Warmth". And a label may only reference detail present in **every** variant of the
line it answers: both the night pool and the day pool talk about the moon in general terms, so
"I watch it most nights myself." is safe against all six.

`%1$s` is the player's (spouse-aware) name, which MCA prepends. Template vars fill `%2$s` onward, in
the order they are listed in `vars`, and slots come after the vars.

### `assets/mca_dialogue/lang/en_us.json` — technique 5

Two keys: the chit-chat weather **button label** and the three-variant **rain say pool**. The mod
ships roughly 13,800 keys in this file. **You do not need to reproduce them.**

That is the whole point, and it is the one place where an `assets/` override behaves *better* than a
`data/` override:

> `ClientLanguage.loadFrom` calls `getResourceStack` per namespace and applies every pack's
> `lang/en_us.json` in order, lowest to highest, putting each key into one map. Keys you do not
> mention keep the mod's value; keys you do mention take yours.

Language files **stack and merge per key**. Data files **replace whole**. Every other technique in
this document is subject to the second rule; this one is the exception, which is exactly why it is
the least invasive way to change what a villager says.

**Why write into `mca_dialogue` and not `sample_retune`?** Because the merge is per namespace, and
the key already exists in `mca_dialogue`. Redefining `dialogue.conversations.weather.rain/1` from
your own namespace *also* writes to the same global map, but which namespace is applied last is
`resourceManager.getNamespaces()` order — a `Set`, with no ordering promise. Within one namespace,
pack order decides, and pack order is something you control from the resource pack screen. Use the
namespace that already owns the key.

**The cost:** it is a text override and nothing more. It cannot change which line is chosen, only
what that line says. And you have taken on the maintenance of any key you name: if a mod update
rewords the button and you have overridden it, players see your old wording forever with no warning.
Keep the override list short and re-read it after every update.

**Personality overlays** work the same way but have one extra rule: keys in
`assets/mca_dialogue_<personality>/lang/` **must be prefixed with the personality id** —
`odd.dialogue.conversations.foo`. An unprefixed key in an overlay namespace is not an overlay, it is
a silent global collision.

---

## The blunt instrument, and why this pack does not use it

Every one of the eleven mod catalogs, and MCA's dialogue loader too, resolves **one resource per
exact `ResourceLocation`, top-most pack wins**. So the guaranteed way to change anything is to ship
the mod's own path from your pack:

```
data/mcaconversations/chat_intents/chitchat.json
data/mcaconversations/interiority/personalities.json
data/mcaconversations/conversation_scenes/generated.json
data/mcaconversations/dialogues/conversations.cat.chitchat.json
```

It always works. It is never ambiguous. Pack 10 uses it deliberately, because for *removal* it is
the only mechanism there is.

For *modification* you almost never want it, for two reasons:

1. **You inherit the whole file.** Not the line you changed — every line in it.
   `conversations.cat.chitchat.json` is about 70 KB. `generated.json` holds 373 scenes.
   `personalities.json` holds all seventeen profiles. Ship one and you have adopted all of it.
2. **A mod update stops reaching you, silently.** New topics, fixed conditions, new scenes, corrected
   lines: all of it goes into the mod's copy of the file, and the mod's copy is no longer read.
   Nothing logs a warning. Nothing tells the player. Your pack will keep working and quietly get
   further and further out of date.

Same-basename merging (technique 1) has neither problem: the mod's file is still read, still
updated, and still merged with yours.

---

## The rules this pack is obeying

Written naively, this pack would break most of these.

- **Fallback result last.** MCA takes the last result when every total is `<= 0`. A fallback anywhere
  else is not a fallback.
- **Weighted-random, not highest-wins.** Two positive results both fire sometimes. A result you want
  to be exclusive needs the other one *sunk*, not merely outweighed. The 900:1 night weighting here
  is a deliberate lean, not a gate, and this README says so rather than pretending otherwise.
- **Action order is state -> `next` -> `say`.** `say` after `next`, always.
- **Every `conversations_*` result carries a `conversations_disabled` sink, or a plain alternative
  exists on the same answer.** Both, here: the complementary `conversations_disabled` /
  `conversations_enabled` pair.
- **2–5 answers per node, and at least one consequence-free way out.** `retune.moon.respond` has
  three, and `leave` costs nothing.
- **Answer names must not collide with question names, nor with shipped answer names on the same
  page.**
- **Button labels are what the player says.**
- **A label may only reference detail present in every variant of the line it answers.**
- **Three variants per say pool.**
- **Never MCA's native `personality` condition** — it throws on an unknown id and takes the whole
  reload down with it, world load included. `conversations_personality` is the safe one. This pack
  uses neither, but technique 3 is about personality and the temptation is right there.
- **Do not add answers to MCA's `auto` questions** (`root`, `chat`, `rumors`). An `auto` question
  picks a random answer immediately with `getRandomAnswer()` and never shows a screen.
- **A top-level `_comment` is safe** in these catalog files, because every loader reads one named
  sub-object and ignores the rest of the root. Commentary *inside* an entry is not safe unless you
  have checked that entry's parser. Per-entry notes belong in this README.

---

## How to adapt it

- **Change the added topic first.** In `conversations.cat.chitchat.json`, rename `moon` to your own
  id, repoint `next` at your own respond page, and rename every `dialogue.retune.moon.*` key. Then
  merge into a different category page by renaming the file: `conversations.cat.village.json`,
  `conversations.cat.personal.json`, `conversations.cat.events.json`,
  `conversations.cat.profession.json`, `conversations.cat.relationships.json`.
- **Change the button position** with `priority`. Negative to sort above the shipped answers,
  omitted or positive to sort below them.
- **Re-keyword a different intent** by copying `retune_rekeyword.json` and changing the id. Run
  `/conversations chat debug <message>` before and after so you can see what moved.
- **Retune a different scene** by copying the block in `sample_retune.json` and changing the id to
  any of the 373 in `generated.json` — then restate *all* of that scene's fields, not only the ones
  you are changing.
- **Retune a different personality** the same way, from
  `data/mcaconversations/interiority/personalities.json`.
- **Override more lang keys** by adding them to `assets/mca_dialogue/lang/en_us.json`. Cheapest
  change here by a wide margin, and the one to reach for first.

---

## What this pack deliberately does not do

- **It never removes anything.** No shipped answer disappears, no intent is neutralised, no catalog
  is emptied. That is pack 10.
- **It adds no topic to `conversation_catalog`, no beats, no session.** The `moon` exchange is a
  plain two-page chat: no `conversations_session`, no budget, no arc. A topic with a real branching
  tree is pack 01's job, and repeating it here would bury the point.
- **It moves no hearts and writes no disposition.** Hearts change only through
  `conversations_affection_apply` in branching content, with a stable `decision` id and an explicit
  `policy`, and none of that is what this pack is about.
- **It ships no replacement of a mod file at its exact path**, even though that is the only
  guaranteed form of techniques 2 and 3. The cost is documented above, and the honest answer is that
  you should reach for it only when the non-determinism actually bites you.
- **It changes no scene `route`.** Re-tuning `selection` is safe; re-pointing `route` obliges you to
  own a question and a contracted beat, and does not affect the shipped player-initiated dialogue
  routes at all.
- **It is not tested in a running game.** These files are authored against the shipped schemas, the
  mod's source, and MCA 7.7.0-beta.2's loader bytecode. They have not been run in a production
  instance.
