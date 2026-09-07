# 02 — a new hub category (`sample_pastimes`)

Pack 01 added one starter to a category the mod already ships. This pack adds a **whole category**:
a new **Pastimes** button on the `conversations` hub, a category page of its own with two starters
(`music`, `games`) and a `back`, an entry on MCA's own main menu so the category is reachable even
when the hub button is hidden, and the topics behind both starters.

Adding a category is four small pieces of plumbing and one large piece of writing. The plumbing is
what this README is about, because every part of it has a failure mode that produces a pack which
loads cleanly and is wrong.

---

## What it demonstrates

- **A hub answer is pure navigation.** Exactly one result, whose only action is `next`.
- **A category page must have a `back`**, and starters return to their own page, not the hub.
- **`constraints` for age gating**, and the rule that a category's gate must be implied by *every*
  starter it fronts.
- **Answer names must not collide with question names**, because they share a lang-key shape.
- **`hubEntryMode`**, and why the `main.json` extension has to carry `"silent": true`.
- The **uncategorized fallback** — what happens to a pack written against the pre-0.3.0 flat hub, and
  how to migrate it.
- Two `quick` topics with an asked-recently branch, a shared close page, dispositions, guarded
  affection and a personality split.
- One chat intent per non-exit answer, and an honest note about the one answer that gets none.

---

## Install

Two halves, as always. `data/` is only read from `saves/<world>/datapacks/`; `assets/` is only read
from `resourcepacks/`. Copy this folder into both. `pack_format` 15 is correct for 1.20.1 in both
roles.

```
saves/<world>/datapacks/02-new-category/
resourcepacks/02-new-category/
```

`/reload` picks up `data/`. **F3 + T** picks up `assets/`. You need both after most edits — a new
button appears on `/reload` and is labelled `dialogue.pastimes.cat.music` until you press F3+T.

This pack and pack 01 use different namespaces and different question names, so they can be
installed together.

---

## Try it

1. Open a villager's MCA interaction screen. The main menu now has **"Ask about pastimes"** next to
   MCA's own entries — that is `main.json`.
2. Go the other way instead: **"Let's talk properly"**, the mod's hub, which by default sits on the
   same main menu as the entry above (under `hubEntryMode = REPLACE` it is MCA's **Chat** button that
   opens it). The hub's category list now ends with **"How do you pass the time?"** — that is
   `conversations.json`. MCA appends merged answers, so a third-party category lands after the six
   shipped ones.
3. Either route lands on the same page, `pastimes.cat`, with **music**, **games** and a **back**.
4. Take `games`, then **"I'd back myself against you."** A confident, playful, upbeat or peppy
   villager takes the bait; anyone else deflects it. Then close on **"I do the same when I get the
   chance."**
5. Find a **toddler** and open the hub. The Pastimes button is still there and the page now shows
   only **games** and **back** — `music` is gated `!toddler,!baby`, `games` is only `!baby`.

Debug commands, on the villager you are looking at:

```
/conversations chat debug what do you do for fun
/conversations chat debug do you play games
```
scores the message against every loaded intent; the first should top out on `sample_pastimes.hub`,
the second on `sample_pastimes.cat.games`. Note the second only gets its context bonus once the
`pastimes.cat` page is actually open.

```
/conversations chat debug-ask pastimes.cat games
/conversations chat debug-ask pastimes.games.respond challenge_them
/conversations chat debug-ask pastimes.close share
```
walks the whole route without clicking, which is how you check a rename did not break a `next`.

```
/conversations profile inspect
```
prints the personality that decides which of `challenge_them`'s two results you just got.

```
/conversations context snapshot
```
prints `speaker.age`, which is what the `constraints` on the two starters are really gating on.

---

## File-by-file walkthrough

### `pack.mcmeta`

`pack_format` 15, a description, nothing else. Same file serves as both a data pack and a resource
pack manifest.

---

### `data/sample_pastimes/dialogues/conversations.json`

```json
{"answers": [{"name": "pastimes", "constraints": "!baby",
  "results": [{"baseChance": 1, "actions": {"next": "pastimes.cat"}}]}]}
```

Basename `conversations.json`, so this merges into the mod's hub question. One answer, one result,
**one action**. That is the whole rule for a hub button: hub answers are pure navigation. No `say`,
no hearts, no memory, no session. Side effects belong on the starters inside the category page,
because the hub is a menu and a menu that charges you for browsing is a bad menu.

No top-level flags. The mod's own `conversations.json` has none — it is a normal question with eight
answers — and MCA keeps the flags of whichever same-named file loads last, in an undefined order.
Adding a flag here would be a coin flip applied to the mod's entire hub.

The answer name `pastimes` gives the label key `dialogue.conversations.pastimes`. Check it against
the question list before committing to it: an answer `a` on question `q` labels from `dialogue.q.a`,
which is the *same key* a question literally named `q.a` would use for its own prompt. The mod hit
this and it is why the hub's relationships button is not called `family` — `conversations.family`
exists as a follow-up question, so an answer named `family` on `conversations` would have fought it
for `dialogue.conversations.family`. There is no question called `conversations.pastimes`, so we are
clear.

`constraints: "!baby"` hides the button from babies. The gating rule for a category button is
specific: constraints are AND-only (`containsAll`), so a category's gate must be a token implied by
**every** starter it fronts. Our starters are `!toddler,!baby` (music) and `!baby` (games); the token
they share is `!baby`, so that is what the hub button gets. Gate the hub button `!toddler` and a
toddler loses access to `games`, which is the one thing on the page written for them.

The corresponding rule going the other way: a category must never be reachable *and* empty. Because
`games` is only `!baby`, every villager who can see the Pastimes button has at least one starter
behind it. When your starters have genuinely heterogeneous gates and no shared token, leave the
category ungated and handle the emptiness with result-level deflects instead — that is what the mod
does for chit-chat, village and personal.

---

### `data/sample_pastimes/dialogues/main.json`

```json
{"silent": true, "answers": [{"name": "pastimes", "constraints": "!baby",
  "results": [{"baseChance": 1, "actions": {"next": "pastimes.cat"}}]}]}
```

This is the second way in, and the `"silent": true` is not decoration. MCA's `main` question is
silent; extension files merge their `answers` but the **top-level flags come from whichever file
loads last**, and the load order is undefined. Ship this without `silent` and you have a coin flip:
half your world loads, MCA's whole main menu starts speaking a line it was never meant to speak. An
extension file must mirror the original question's flags, always. The mod's own `main.json` carries
`"silent": true` for exactly this reason.

The mod's `main.json` also sets `"priority": 5` on its answer, to place it among MCA's own entries.
This pack sets no priority and takes the default position. A third-party pack outranking the mod's
own entry on MCA's menu is rude, and priority wars between packs are unwinnable.

**Why have this at all**, when the hub button already exists? Because of `hubEntryMode`. The mod has
three modes:

- `ADDITIVE` (the default since 1.0.0): MCA's Chat button is left alone and the mod merges its own
  `conversations` answer into `main`. Both doors exist.
- `REPLACE`: every `next: "chat"` hop — MCA's, and any third-party pack's — is redirected to the
  `conversations` hub, and the separate menu button is hidden. Answers merged into question `chat`
  become unreachable while this is on.
- `HIDDEN`: the menu button is removed and MCA's Chat is untouched.

Under `HIDDEN` the hub is not on the main menu, so a category that only exists as a hub answer is
one config toggle away from being unreachable. The `main.json` entry is the insurance. It also means
this pack does not care which mode the player chose, which is the property you want from a pack you
are not going to support forever.

Two things this pack does **not** do: it does not merge into question `chat` (unreachable under
`REPLACE`), and it does not touch MCA's `root` question (which is `auto` — see the rules section).

---

### `data/sample_pastimes/dialogues/pastimes.cat.json`

The category page: `music`, `games`, `back`.

The question name is `pastimes.cat`, carrying this pack's own prefix. The mod's category pages are
`conversations.cat.<id>`, and you *could* name yours that way — but question names are global by
basename and namespace-independent, so `conversations.cat.pastimes` is a name in the mod's own space.
Take your own. The only time a basename should match the mod's or MCA's is when you deliberately mean
to extend that exact question, as `conversations.json` and `main.json` above do.

**`back` is mandatory.** Every category page needs an answer that hops to `conversations` with no
side effects. A page you can only leave by closing the screen is a bug, and it is the single most
common omission when adding a category. Ours is one result, one `next`, no actions of any kind — and
no chat intent, because the way out is not a thing you should have to phrase correctly.

Each starter carries **three results**, in this order:

1. **Asked recently.** `baseChance: 0` plus `{"chance": 1000, "memory": {...}}` on the pack's own
   cooldown id. The `memory` condition is 1 while the memory is live and 0 once it has expired, so
   this result is worth 1000 or worth nothing — never a small positive number competing with the
   ordinary branch. This branch grants nothing and writes no new cooldown; it routes into the same
   respond page with a different opening line.
2. **Ordinary.** `baseChance: 1`, sunk `-1000` on the same memory. Writes the 12000-tick cooldown,
   begins the session, routes into the respond page.
3. **Plain MCA, and last.** Sunk `-2000` on `conversations_enabled: "branching"` and `-1000` on the
   cooldown, with MCA's native `positive: 2`, a return straight to `pastimes.cat` and no session at
   all.

Results 1 and 2 both sink `-2000` on `conversations_disabled: "branching"`. Result 3 is what a player
with branching turned off actually gets, and it has to be **last**, because MCA's selection loop
takes the final element when nothing has a positive score. With branching off and the cooldown live,
that is precisely the state: result 3 sinks itself on the cooldown too, everything is negative, and
MCA runs off the end into result 3 anyway. Written first, it would have been result 1 — a
branching route with no runtime driving it — that the player fell into.

Action order in every result is state → `next` → `say`. `conversations_session` and
`conversations_record` first, then `next`, then `conversations_say`. Actions run in JSON key order,
the interact screen has one speech slot, and `next` writes the destination question's prompt into it
— so a `say` authored before `next` is overwritten and simply never read.

Note the answer names against the question list one more time: `music` and `games` label from
`dialogue.pastimes.cat.music` and `dialogue.pastimes.cat.games`. Our respond pages are named
`pastimes.music.respond` and `pastimes.games.respond`, *not* `pastimes.cat.music` — if they were, the
page's own prompt key and the button's label key would be the same string and one would silently
become the other.

---

### `data/sample_pastimes/dialogues/pastimes.music.respond.json`

Three answers: `ask_more` (curiosity, dispositions only), `praise` (encouragement, +1 heart), `leave`
(exit, free).

The page is opened by two beats — the ordinary opener and the asked-recently line — so both buttons
have to work after either, and after all three variants of each. MCA never looks at which villager
line led to a page; it decides which buttons to show from the answer's own constraints alone. So the
constraint is on the *writing*: every variant of both openers asserts the same single fact, that
there is music in this village and the speaker has a view on it, and that is written down as
`pastimes:music_here` in the beat file with `requires_facts` on both replies.

That fact is also what makes "Who plays around here?" safe. A label may only reference detail present
in **every** variant of the line it answers. "Where did you learn the fiddle?" would have been a
non-sequitur two times in three.

`praise` carries the only guarded heart change on this page:

```json
"conversations_affection_apply": {"decision": "pastimes.music.praise", "delta": 1,
                                  "budget": "quick", "policy": "daily_repeat"}
```

`decision` is a stable id that keys anti-farming, debug output and tests, and must never be reused
for a semantically different choice. `budget` names the depth class whose per-conversation cap
applies (`quick` = +2/−3). `policy` is written out rather than relying on the `daily_repeat` default.
This action is the **only** route to a heart change in branching content — the native
`positive`/`negative` actions bypass the duplicate-transaction refusal, the replay policy and both
budgets, which is why the only `positive` in this pack is on the non-branching fallback result.

`ask_more` grants no hearts, only `familiarity` and `trust`. A page where every button pays is a page
where the buttons do not matter.

---

### `data/sample_pastimes/dialogues/pastimes.games.respond.json`

Same shape, with one addition: `challenge_them` has **two results**, split on personality.

```json
{"baseChance": 0, "conditions": [{"chance": 100,
   "conversations_personality": ["confident","playful","upbeat","peppy"]}], ...}
{"baseChance": 1, "conditions": [{"chance": -2000,
   "conversations_personality": ["confident","playful","upbeat","peppy"]}], ...}
```

For those four personalities the first result scores 100 and the second goes deeply negative; for
everyone else the first scores 0 (not positive) and the second scores 1. Exactly one positive total
in every state, which is what selection requires — MCA picks by **weighted random over the positive
totals**, not by taking the highest. A "fallback" worth 1 sitting beside a result worth 100 is not a
fallback; it is a 1-in-101 chance of the wrong line.

This is the pack's answer to *there is no universally correct button*. Teasing a confident villager
lands and pays +1; teasing a quiet one deflects, pays nothing, and adds a point of `tension`. Neither
outcome is a punishment and neither is free.

Use **`conversations_personality`**, never MCA's native `personality` condition. The native parser
throws on an id the running MCA build does not know, MCA 7.7 renamed four personalities, and the
throw aborts the datapack reload *and* the world load. `conversations_personality` never throws and
resolves legacy ids (`witty`→`upbeat`, `shy`→`introverted`, `lazy`→`relaxed`, `grumpy`→`crabby`) to
their successors, so one authored value works on both MCA versions.

---

### `data/sample_pastimes/dialogues/pastimes.close.json`

One close page shared by both topics, opened by five beats: `music.ask_more`, `music.praise`,
`games.ask_more`, `games.challenge` and `games.challenge.flat`.

Sharing is legal because all five leave the conversation in the same posture — the villager has just
said a bit more, is not closing the subject, and is not asking for anything. All five are declared
`"openness": "permits_followup"`. A page may not be opened by both a line that invites more and a
line that closes the subject; if one of these beats were `closes_subject` it would need a page of its
own, and lint would split them for you.

Note where `challenge.flat` routes. The deflection is a soft `qualified`, not a rebuff — the villager
declines the contest but stays in the conversation — so arriving at a companionable close page is
honest. Had it been an actual rebuff, routing it here would be the classic authoring bug: a rebuff
must never land on a page whose buttons assume the stance landed. Look at "I do the same when I get
the chance." and ask whether it reads as a non-sequitur after the line before it; if it does, you
need a rebuff-aware close instead.

Three answers, one free. `agree` and `share` each pay +1 under distinct decision ids and
`"policy": "once_per_day"` — these are things you say once in a day, not things whose value halves on
the second saying.

Add up a normal path: opener (0) → `praise` (+1) → `share` (+1) = +2, exactly the `quick` cap. Two
decisions, which is what `quick` owes on a normal path. No path in this pack can exceed its budget.

---

### `data/sample_pastimes/conversation_catalog/pastimes.json`

Two rows, `pastimes.music` and `pastimes.games`. Topic ids carry the pack's prefix, because the
catalog folds every file from every namespace into **one** map keyed by the id declared inside the
JSON, and the last one seen wins, silently. A pack that calls its topic `music` will one day be
installed alongside another pack that also calls its topic `music`, and one of them will simply stop
existing with no error anywhere.

Per row:

- `entry` names the question and answer the topic is reached by, and must match the dialogue exactly.
- `depth: "quick"` sets the default affection budget and declares that normal paths owe two decisions
  and the topic owes two subject families. Between them the two topics ship four distinct subjects
  (`pastimes.music`, `pastimes.music.repeat`, `pastimes.games`, `pastimes.games.repeat`); the nine
  beats share those four, the reaction beats reusing the two main ones.
- `return_question` is `pastimes.cat`, the topic's own category page — **not** the hub. Starters
  return to the page they were pressed on; only `back` goes up a level.
- `ages` must agree with what the dialogue can actually reach. `pastimes.music` lists
  `child`/`teen`/`adult` to match its `!toddler,!baby` constraint; `pastimes.games` lists all four.
- `required_stance_families` must include **`exit`**. A topic with no graceful way out fails lint and
  fails the player.
- `chat_required: true` asserts that every non-exit answer has an intent — a claim the intent file
  has to make true.

The top-level `_comment` survives because every loader here reads one named sub-object (`topics`,
`beats`, `intents`, ...) and ignores the rest of the root. Do not put a `_comment` *inside* an entry,
where a stricter sub-parser may reject it.

---

### `data/sample_pastimes/conversation_beats/pastimes.json`

Nine beats, nine replies. A beat is one say pool plus the question it opens; a reply is one button,
keyed `"<question>/<answer>"`.

- `subject` is what the villager is *talking about*, not which page the line sits on. It is what the
  session stores and what the depth measurement counts, so naming beats after their pages makes one
  conversation in several costumes. The repeat lines get `pastimes.music.repeat` /
  `pastimes.games.repeat` because "I already told you" is genuinely a different subject from the
  music itself.
- `facts` are `type:value` and must be true of **every** variant of the pool, because the client
  picks the variant and the server never learns which one. A claim only one variant makes is a claim
  no reply may answer.
- `npc_act`: `report` for the openers, `qualify` for the repeat lines and the deflected challenge,
  `explain` for the two follow-ups, `accept` for the two lines that take the stance well.
- `polarity` colours what is appropriate next. Nothing here is `acute` — grief, fear and fresh harm —
  where humour and flirtation are never appropriate however playful the villager is. That matters
  here because `challenge_them` is a `humor` stance, and it would have to be forbidden on an acute
  beat.
- `openness` is what lets five beats share one close page.
- `outcome` appears only where the beat is the villager reacting to a player line. The four openers
  have none.
- `allowed_stances` includes `exit` on every beat; `forbidden_stances` never does, because that would
  forbid the door. Every page needs one.

An unknown beat id named in a `conversations_session` action is logged and ignored rather than
thrown, so a typo here costs you the breadcrumb trail, not the player's conversation — which is
convenient and is also why nothing tells you at runtime.

---

### `data/sample_pastimes/chat_intents/pastimes.json`

Nine intents: the hub button, both starters, both `ask_more`s, `praise`, `challenge_them`, and both
paying answers on the close page. Every non-exit answer in the pack except one.

**The exception, stated honestly:** there is no intent bound to `main`/`pastimes`. It is the same
navigation as `conversations`/`pastimes` and would want the same words, and two intents competing
over one keyword set is a collision — neither wins reliably, and a distinctness check is exactly what
would flag it. One intent, on the hub answer, is the right number.

`back` gets no intent either. The way out of a menu should not require phrasing.

The `synonyms` block merges across **every** pack and namespace in a first pass, before any intent
parses, first writer wins per alias. `{"hobby": ["pastime", "leisure", "amusement"]}` therefore
widens the vocabulary of every installed pack, not just this one, and a pack that ships only synonyms
and no intents is a valid thing to build. Because keywords and phrases go through the same stemmer
and canonicalizer as the player's typed message, you author the canonical surface word and match all
of its aliases: write `hobby`, match `pastime`.

Each intent carries `keywords` (weights in `(0, 10]`), `requiresAny` (evidence the message must
actually contain), and several `phrases` that are real paraphrases rather than one canonical wording.
The keyword sets are less distinct than they look: `sample_pastimes.close.share` uses `same` /
`chance` / `myself` and pack 01's `sample_sleep.close.share` uses `neither` / `either` / `myself`, so
both carry `myself` at 1.2, in `keywords` and in `requiresAny`. What stops the two from fighting when
both packs are installed is not vocabulary but scope: they are scoped to different `context`
questions, so only one of them is ever in the running on a given page.

Every intent below the hub sets `context` to the sub-question it belongs to. A context-scoped intent
only scores while the player's session has that question open, with a scoring bonus and a relaxed
threshold — which is what lets `sample_pastimes.games.ask_more` get away with a word as ordinary as
"often". Outside `pastimes.games.respond` that word means nothing in particular; inside it, there is
very little else it could mean.

Answer-level `constraints` are enforced automatically at match time, so the two starter intents do
not repeat their age gates.

Intent **ids** merge last-wins across packs, so redefining another pack's id is possible — but the
order the resource manager hands the files over is undefined, so it is only reliable if your pack
sits above the mod in load order. This pack redefines nothing.

---

### `assets/sample_pastimes/lang/en_us.json`

Sixty keys, in three groups.

**Button labels**, `dialogue.<question>.<answer>` — what the **player** says, in the player's voice.
Never "Persuade", never "+2 Warmth", never a success chance, never personality-flavoured. Note the
two entry labels are deliberately different: `dialogue.conversations.pastimes` reads "How do you pass
the time?" because on the hub you are picking a subject to raise, while `dialogue.main.pastimes`
reads "Ask about pastimes" because on MCA's menu you are picking a mode. Same destination, different
sentence.

**Question prompts**, `dialogue.<question>` with no answer suffix — including
`dialogue.pastimes.cat`, the category page header. These are fallbacks, displayed only when a result
arrives with no line of its own, but they still need to read acceptably alone.

**Villager lines**, `dialogue.<phrase>`, where `<phrase>` is the `conversations_say` value without the
`dialogue.` prefix. Three variants each — `/1 /2 /3`. Three is the floor; two is allowed only for
precision-targeted pools and keys ending `.child`, `.teen`, `.crit`, `.success`, `.partial`,
`.rebuff` or `.guard`. A single-line pool takes **no suffix at all**.

Translation keys are one global map across every asset namespace. That is why you never need to
override `assets/mca_dialogue/lang/en_us.json` to add `dialogue.*` keys — and why a key you define
that someone else also defines is a silent collision. Prefixing every say key with
`sample_pastimes.` is the whole defence.

`%1$s` is the player's spouse-aware name, prepended by MCA to every line. `vars` on
`conversations_say` would fill `%2$s` onwards; this pack declares none.

The writing follows the shipped corpus: plain, specific, no catchphrase, no line reused between
pools. The five exit and close pools are five different endings — a marked word creeping to the
front of everything, and one goodbye for everybody, are the two tics that no per-line review
catches.

---

## The rules this pack is obeying

**Rule 1 — actions run in JSON key order, and `say` must come after `next`.** Obeyed in every result
with actions: `conversations_session` / `conversations_affection_apply` /
`conversations_disposition_apply` / `conversations_record` first, then `next`, then
`conversations_say`. The interact screen holds one speech slot and `next` writes the destination
prompt into it, so a `say` authored earlier is silently overwritten. The symptom is "my line never
appears" and the JSON looks perfectly fine.

**Rule 2 — when every result scores ≤ 0, MCA takes the LAST one.** Obeyed by the ordering of the
three results on each starter, and by the two results on `challenge_them`. The plain-MCA result is
last on both starters on purpose: with branching disabled and the cooldown live, every result is
negative and the selection loop falls off the end into the final element. That has to be the
side-effect-free one.

**Rule 3 — an `auto` question must have exactly one answer.** This is the rule this pack is most at
risk of breaking, because it is the pack that merges into MCA's own questions. `main` is **not**
auto, which is why merging an answer into it is legal. MCA's `root`, `chat` and `rumors` **are**
auto: `Actions.next` calls `getRandomAnswer()` on an auto question, so a second answer turns the node
into a coin flip. Since answers merge across data packs, a third-party pack can introduce that coin
flip into a question it never opened. This pack adds nothing to any auto question — and that is also
why the second entry point is on `main` rather than on `chat`.

Two more the pack holds itself to:

**Weighted-random, not highest-wins.** Every multi-result answer guarantees exactly one positive
total in every state via negative sinks.

**Never MCA's native `personality` condition.** It throws on an id the running build does not know
and takes the world load with it. `conversations_personality` instead, always.

---

## The uncategorized-fallback migration path

Before v0.3.0 the hub listed starters directly and third-party packs merged their starters straight
into question `conversations`. That still works: a merged answer on `conversations` appears on the
hub **after** the category buttons, because MCA appends merged answers, and its `next:
"conversations"` return lands back on the hub one level up. Nothing broke, and nothing needs to be
migrated in a hurry.

But such a starter sits at the top level next to six category buttons, which reads oddly, and it
returns the player to the hub rather than to a page of related subjects. Opting in is a small edit:

1. Change the file's basename from `conversations.json` to `conversations.cat.<id>.json` for whichever
   shipped category fits — or, as here, add a category of your own.
2. Change your starter's `next` targets so they return to that category page rather than to
   `conversations`.
3. Move the label key from `dialogue.conversations.<answer>` to
   `dialogue.conversations.cat.<id>.<answer>`.
4. Update `entry` and `return_question` in your catalog row, and the `question` field of your intents.

If you add your own category rather than joining a shipped one, you also need the hub answer, the
category page with its `back`, and two new lang keys: the hub button label and the page header. That
is the four-piece plumbing this pack is made of.

---

## How to adapt it

1. **Namespace and prefixes.** Rename `sample_pastimes` in both folder names, in the cooldown ids
   `sample_pastimes.cooldown.*`, and in the `sample_pastimes.` say-key prefix.
2. **The category id.** `pastimes.cat` is the page question; `pastimes` is the answer name on both
   `conversations` and `main`. Check the new answer name against every question name in every
   installed pack before committing to it.
3. **The starters.** Copy the three-result shape and change the branch conditions. `mood`,
   `age_group`, `conversations_season`, `conversations_weather` and `conversations_relationship` are
   the obvious axes. Keep the plain-MCA result last.
4. **The say pools.** Three variants each, all asserting the same facts. Most of the work, all of the
   quality.
5. **`ages` and `constraints` together.** If you change one, change the other and the catalog row, or
   you will ship a starter the catalog claims a toddler can reach and the dialogue hides from them.

To add a **third** starter to this category: one answer in `pastimes.cat.json`, one respond page (or
reuse `pastimes.close`), one catalog row, its beats and replies, its intents, and the label key
`dialogue.pastimes.cat.<answer>`. Nothing else changes — the hub button, `main.json` and the `back`
are all already there.

---

## What this pack deliberately does not do

- **No negative path.** Every button here is neutral or positive, which for a topic about hobbies is
  defensible but is not the general rule: a converted topic should have at least one plausible path
  that loses affection. Pack 01's `brush_off` shows that shape.
- **No checks and no tiers.** Nothing uses `conversations_check`, so no outcome is rolled. The
  `challenge_them` split is a deterministic personality condition, not a check.
- **No durable state.** No arcs, no milestones, no exclusive choices, no promises. The only thing
  written down is an expiring cooldown. State nothing reads back is state that should not be stored.
- **No dynamic layer.** No scenes, episodes, threads or identity tokens, so the pack behaves the same
  with `dynamic.enabled` on or off.
- **No optional-mod compat.** No weather, season, holiday, gossip, quest, reputation or capitals
  conditions — and correspondingly nothing that would belong in a `mods.toml`.
- **No `pt_br`.** English only, said plainly rather than shipped half-translated.
- **No personality overlays.** `assets/mca_dialogue_<personality>/lang/` would let a gloomy villager
  deliver the games opener differently, and every key in such a namespace **must** be prefixed with
  the personality id (`gloomy.dialogue.sample_pastimes.games.open`). An unprefixed key there is not
  an overlay at all — it is a global collision, and whichever resource pack loads last quietly
  becomes every villager's voice.
- **No removal.** This pack only adds. It does not hide MCA's Chat button, does not change
  `hubEntryMode`, and does not replace any file the mod ships. There is no `"remove": true` flag in
  any schema here, and an answer another pack merged in cannot be un-merged.
