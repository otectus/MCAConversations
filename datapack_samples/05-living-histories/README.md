# 05 — Living histories (`sample_beekeeper`, prefix `bees`)

A farmer who keeps bees. One hive has gone wrong; it may come back; and whatever happens, there is
always something to say about the row of hives. That is four scenes, one episode, one thread, four
identity tokens, and exactly one new button on MCA's profession category.

This is the pack that shows the whole dynamic layer end to end: **identity → episode → scene →
route → line**, plus the two things that are easiest to get wrong (the negated `conversations_scene`
sink, and slot phrasing that survives translation).

---

## What it demonstrates

- `identity_tokens/` — new anchors a villager can be generated with, and the fact that adding them
  never rerolls a villager who already exists.
- `episode_templates/` — a situation with its own state machine, two slot pools, and full
  provenance (`knowledge` / `privacy` / `share` / `distortion`).
- `thread_templates/` — the pair-level record, and the **required** `resume_scenes`.
- `conversation_scenes/` — four scenes, a real two-hop `fallback` chain, `cooldown_days` and
  `max_mentions_per_7_days` as hard gates, `identity_values` as a scoring thumb, and the optional
  documentation fields `slots_used` / `vars_used`.
- The dialogue route that **reads the frozen plan** (`conversations_scene`) and never re-selects,
  including the load-bearing negated sink.
- `conversations_say` with `vars` **and** `slots` together, and exactly which positional argument
  each one lands on.
- `conversations_episode` (`advance`) and `conversations_thread` (`open`) written from a reply.
- Slot lang keys under `mcaconversations.slot.*`, written as complete noun phrases.
- One chat intent per non-exit answer, bound to its exact question+answer.

## Install

A sample that adds dialogue has **two halves**, and Minecraft serves them from two different places:

- `data/` is only read from `saves/<world>/datapacks/` (a **data pack**).
- `assets/` is only read from `resourcepacks/` (a **resource pack**).

So install the two halves separately:

```
05-living-histories/datapack/      -> <instance>/saves/<world>/datapacks/05-living-histories/
05-living-histories/resourcepack/  -> <instance>/resourcepacks/05-living-histories/
```

On 1.21.1 a data pack declares `pack_format` **48** and a resource pack declares **34**, so
one `pack.mcmeta` can no longer serve both roles the way 15 did on 1.20.1. This sample therefore
ships pre-split, and paths written `data/…` or `assets/…` below are paths inside an installed
pack.

Enable the resource pack in Options → Resource Packs, then in game:

```
/reload                 (reloads the data half: scenes, episodes, threads, beats, intents, dialogue)
F3 + T                  (reloads the assets half: the lines)
```

Without the assets half you will see raw keys like `dialogue.bees.scene.wet_hive.blocked` — that is
the symptom of installing one half only, not a bug in the pack.

Nothing here needs the pack to be higher priority than the mod: every id is new, and the one file
that shares a name with a mod file (`dialogues/conversations.cat.profession.json`) is **merged** by
MCA rather than replacing it.

## Try it

Stand in front of an adult **farmer** (see "How to adapt it" for why farmer).

1. `/conversations profile inspect` — the villager's identity profile. If `interest` contains
   `bees_hives`, `animals` or `gardening`, this pack's scenes are eligible for them. Profiles are
   seeded on world seed + villager UUID, so a villager who does not have it will never have it;
   find another farmer rather than waiting.
2. `/conversations context snapshot` — check `work.profession_id` reads `minecraft:farmer`. That is
   the field `bees.wet_hive.blocked` gates on with `"unknown": "fail"`.
3. `/conversations scene candidates work` — every scene the director considered for topic `work`,
   with why each was kept or dropped. Look for `bees.hives.evergreen` at minimum; it has no gates
   beyond profession and age.
4. `/conversations scene plan` — the **frozen** plan for this villager right now. Whatever id it
   names is the id the dialogue route will match with `conversations_scene`.
5. Talk to them → **Work** → *"How are the bees?"*.
6. `/conversations history inspect` — after pressing *"Let me give you a hand with it"*, the episode
   `bees.wet_hive` should read `active` and the thread `bees.wet_hive` should be open.
7. Come back a few days later. When the episode reaches `succeeded`, the thread's `resume_scenes`
   entry (`bees.wet_hive.recovered`) is what the player gets — a line written for having been away,
   not the button page they left open.
8. `/conversations chat debug how are the bees` — scores your typed message against every intent;
   `bees.starter` should win.
9. `/conversations chat debug-ask bees.scene.wet_hive.blocked.respond offer_to_lift_it` — drives the
   villager you are looking at straight to that (question, answer) pair, which is how you test a
   page without waiting for its scene to be planned.

---

## File-by-file walkthrough

### `datapack/pack.mcmeta` and `resourcepack/pack.mcmeta`

`pack_format` **48** for the data half and **34** for the resource half. On 1.21.1 the two formats
have diverged, so the pack carries two manifests instead of one shared file.

### `data/sample_beekeeper/identity_tokens/sample_beekeeper.json`

Four tokens, one each in `interest`, `value`, `comfort`, `aversion`.

- `family` decides which cap the token competes under: `interest` (2), `value` (2), `comfort` (1),
  `aversion` (1), `work_style` (1), `social_style` (1), `disclosure_style` (1), `origin_motif` (1).
- `ages` is a **gate**: non-empty means *only these*. `bees_hives` is teen/adult, because a keeping
  interest that a toddler could be generated with would produce lines no child should say.
- `favour_archetypes` / `favour_personalities` are a **weight** (+8), never a rail. A cultivation
  villager is *likelier* to be interested in hives; nothing guarantees it, and that is deliberate —
  a profile inferred from a job is not a person.
- `conflicts` is a **symmetric exclusion**: `bees_patience` conflicts with the shipped value
  `ambition`, so one villager can never hold both. Declaring it on one side is enough.
- `aliases` maps `bees_skeps` → `bees_hives`. That is how you rename a token later without
  rerolling anybody: villagers keep the string they were generated with and the catalog resolves it
  forward.

**A profile is generated once**, from the world seed and the villager UUID only — not the day, not
their position, not who is asking. Installing this pack does not give existing villagers new
interests. Rebalancing the weights later leaves them alone too.

### `data/sample_beekeeper/episode_templates/sample_beekeeper.json`

One kind, `bees.wet_hive`.

- `initial_state: "blocked"` and a `states` list that **narrows** the shared machine. A template may
  narrow and never widen: writing `succeeded->active` in `transitions` is refused at load, because a
  legal-looking table that contradicts the state machine would let a resolved thing become
  unresolved through data.
- `required_slots` names the two things every line about this episode may refer to;
  `slot_options` are the pools they are drawn from. **The pick is seeded on world + villager +
  kind, not on the day.** The hive she is worrying about is hers and stays hers until it resolves.
  A daily roll would be combinatorial variety pretending to be a life.
- Provenance, all four fields written out:
  - `knowledge: "participant"` — it is her own hive; she is not repeating anything.
  - `privacy: "ordinary"` — sayable, not a confidence.
  - `share: "may_describe_anonymously"` — narrower than `ordinary` would imply, which is allowed;
    `share` may be narrower than the privacy level and never wider.
  - `distortion: "none"` — and see pack 06: this is the one field the runtime never sets.
  - **Confidence is derived, not declared.** There is no `confidence` field to write. A
    `participant` account supports certainty; an `unknown_rumor` cannot be held more firmly than
    "uncertain" no matter what a pack asks for.
- `due_after_days: 4` and `expires_after_days: 20` are the episode's own clock.

### `data/sample_beekeeper/thread_templates/sample_beekeeper.json`

The pair-level record: what *you two* are in the middle of, as opposed to what is happening to her.

`resume_scenes` is **required and the parser refuses a thread without it**. Coming back to a subject
is an authored moment, not a restored screen: on return the player gets `bees.wet_hive.recovered`,
which is written for having been away. A thread with no way back could only ever be dropped, so the
loader will not accept one.

`cooldown_days: 1` keeps the thread from being resumed twice in a day. Statuses (`open`,
`waiting_on_world`, `waiting_on_player`, `ready_to_resume`, `resolved`, `lapsed`, `ruptured`) are set
by the runtime and by `conversations_thread`, not declared here.

### `data/sample_beekeeper/conversation_scenes/sample_beekeeper.json`

Four scenes. **A scene is not dialogue.** It names a `question` and an `opening_beat` that already
exist and are already contracted; everything else in the entry is the rule for *when that route is
the right one*.

| Scene | `base_priority` | Gate | `fallback` |
|---|---|---|---|
| `bees.wet_hive.blocked` | 30 | episode `blocked`, both slots bound, farmer, an interest token | `bees.hives.season_ahead` |
| `bees.wet_hive.recovered` | 34 | episode `succeeded`, `hive` bound | `bees.hives.season_ahead` |
| `bees.hives.season_ahead` | 22 | an interest token | `bees.hives.evergreen` |
| `bees.hives.evergreen` | 12 | farmer, adult | *(none — the floor)* |

- `purpose: "topic:work"` on all four. Purposes are a closed set (`topic:<id>`, `greeting`,
  `state_change`, `due_commitment`, `acute`, `shared_event`, `opinion_request`, `repair`,
  `standing_remark`, `court_remark`, `low_stakes`, `resume`), and each costs interruption time.
- `shape` drives repetition suppression: two scenes that share no ids at all can still be the same
  conversation, and the shape is what lets the director notice. The four here are deliberately
  different (`problem_solve`, `reminisce`, `plan`, `observe`).
- `context.conditions` uses `work.profession_id` with `"unknown": "fail"`. **Every context condition
  must state an `unknown` policy** — `fail` (ineligible when the field cannot be read), `neutral`
  (matches when unknown), `fallback` (degrade to the declared fallback), or `error` (authoring only,
  never ship it). "Not a farmer" and "nothing could tell me what they are" are different facts.
- `context.identity` asks the profile a question directly. An unprofiled villager is always a
  non-match, so an identity gate can only ever *add* a route, never remove one.
- `selection.identity_values` is a **score**, not a gate: a villager who values `bees_patience` or
  `duty` gets a nudge toward this scene over another one they are equally eligible for.
- `selection.cooldown_days` and `selection.max_mentions_per_7_days` are **hard gates, not scores**.
  They cannot be outvoted by a high `base_priority`. `max_mentions_per_7_days` counts across the
  seven day labels `today-6 … today`, so a cap of 2 behaves as written.
- `slots_used` and `vars_used` are purely informational — nothing enforces them. They document which
  slots this scene's lines actually read, which is how you notice a slot that is bound and never
  spoken.

#### The fallback chain, and the four rules it obeys

`bees.wet_hive.blocked` → `bees.hives.season_ahead` → `bees.hives.evergreen`. Two hops, and it is
real: when the hive episode has moved on, or a slot has nothing to bind, or the cap for the week is
spent, the director walks the chain **nearest hop first** and re-checks the *full* eligibility stack
at every hop. A degrade is never a way past a gate.

The loader enforces four rules, and this chain satisfies all of them:

1. **The target must exist and must not be the scene itself.** `bees.hives.season_ahead` and
   `bees.hives.evergreen` are both defined in this file.
2. **The chain must not close a loop.** `evergreen` declares no `fallback`, which is what terminates
   it. If `evergreen` fell back to `blocked` the loader would refuse the pack.
3. **The target must share this scene's purpose *and* topic.** All four are `topic:work`. A work
   scene degrades to a more general work scene, never to one about the weather. This is the rule
   that most often bites: it is tempting to fall back from a work problem to a comfortable
   small-talk scene, and the loader will not have it.
4. **At most four hops**, then the chain is abandoned to the static route — which is the honest
   answer once the scene has little to do with what the player asked.

### `data/sample_beekeeper/dialogues/conversations.cat.profession.json`

The basename matches MCA's file exactly, so its `answers` array is **merged** with the mod's. This
file therefore contains only the pack's own answer (`bees`) — it does not restate `work`,
`work_offer` or `back`, and it must not, because restating them would define them twice.

The answer has five results, **ideal-first and fallback-last**, because when every result of an
answer scores ≤ 0 MCA picks the **last** one.

Each of the four scene routes is the canonical triple:

```json
{"chance":  900,  "conversations_scene": {"is": "bees.wet_hive.blocked"}},
{"chance": -5000, "conversations_scene": {"is": "bees.wet_hive.blocked", "not": true}},
{"chance": -2000, "conversations_disabled": "dynamic"}
```

**The negated condition is load-bearing.** Selection is *weighted-random over positive totals*, not
highest-wins. Without the −5000 sink, the blocked route would still carry whatever positive weight
it had and could win in a state where the director planned something else entirely — and then the
villager speaks `dialogue.bees.scene.wet_hive.blocked`, whose `%3$s` and `%4$s` were **never bound**,
because no plan for that scene exists. What the player sees is a line with a hole in it, or the raw
argument, depending on the locale. The sink is what makes "this scene is not the plan" cost more
than any positive score can pay.

The `-2000 conversations_disabled: "dynamic"` line is the same idea for a different failure: with
the dynamic layer switched off there is no frozen plan at all, so every scene route must lose.
`branching` and `topics` are sunk too, because these routes also open a session and speak through
`conversations_say`.

The **last** result is the plain-MCA alternative: no `conversations_*` action anywhere, just
`"next": "bees.plain.respond"`. It carries `{"chance": -2000, "conversations_enabled": "dynamic"}`
so it loses whenever the dynamic layer is on, and wins by the last-result rule when everything else
has been sunk. That is this pack's answer to the house rule "every `conversations_*` result carries
a `conversations_disabled` sink, **or** the answer has a plain-MCA alternative" — it has both.

Action order inside every result is **state → `next` → `say`**:
`conversations_session` (and `conversations_record`) → `next` → `conversations_say`. MCA applies
actions in key order and a `say` before its `next` speaks into the wrong page.

#### `vars` and `slots` in one directive

The blocked route is the demonstration:

```json
"conversations_say": {
  "phrase": "bees.scene.wet_hive.blocked",
  "vars": ["season"],
  "slots": ["hive", "trouble"]
}
```

MCA prepends the **player's name** as `%1$s` always. Then `vars` fill in declaration order, then
`slots` fill in declaration order, after the vars. So:

| Arg | Comes from | Example |
|---|---|---|
| `%1$s` | MCA, automatically | the player's name |
| `%2$s` | `vars[0]` = `season` | `summer` |
| `%3$s` | `slots[0]` = `hive` | `the far hive` |
| `%4$s` | `slots[1]` = `trouble` | `wax moth in the comb` |

and the line reads `"Middle of %2$s, and %3$s has %4$s."` Reorder the `slots` array and every
translation of that line silently changes meaning, which is why the order is declared once, in the
route, and never re-derived.

`phrase` is required and non-blank, and is the lang key **without** the `dialogue.` prefix. Each
name in `vars` must be a known template variable or the directive throws at parse; `slots` are
lowercased and not validated at parse time, so a typo in a slot name is a silent empty argument —
check them with `/conversations scene plan`.

### The respond pages

`bees.scene.wet_hive.blocked.respond.json`, `…recovered.respond.json`,
`bees.scene.hives.season_ahead.respond.json`, `bees.scene.hives.evergreen.respond.json`,
`bees.followup.json`.

Every question name carries the pack prefix `bees.` — the only file allowed to use a
`conversations.*` name is the merge extension above, and only because its basename must match.

Each non-exit answer has two results: the branching one (sunk when `branching` is disabled) and a
plain one (sunk when `branching` is enabled). Exactly one is positive in every state, which is what
the weighted-random selector requires; an answer with two positive results is a coin flip you did
not intend to write.

On the blocked page, `offer_to_lift_it` is where durable state is written:

```json
"conversations_episode": {"op": "advance", "kind": "bees.wet_hive", "state": "active"},
"conversations_thread":  {"op": "open", "template": "bees.wet_hive"}
```

Both instantiate an **authored template** — a result cannot invent an episode kind or a thread from
JSON, which is what keeps runtime state and authored content in step. `advance` moves `blocked` →
`active`, a transition the template declares; an undeclared transition is refused.

Every page ends in a consequence-free `leave` that ends the session and returns to
`conversations.cat.profession`, and every page offers 2–5 answers.

### `data/sample_beekeeper/conversation_beats/sample_beekeeper.json`

The semantic contract. Four opening beats (one per scene's `opening_beat`), seven turn beats, and
the replies that connect them.

- A beat names its `say` pool and its `response_question`, so the beat and the dialogue page agree
  about which page comes next. The scene points at the beat; the beat points at the page; the page's
  answers point at the next beats.
- `allowed_stances` / `forbidden_stances` say what a player may take toward this moment. A hive that
  has gone wrong permits `practical_help`, `curiosity` and `exit`, and forbids `flirtation`,
  `dismissal` and `boundary_push` — the moment is not an occasion for them.
- `frame` (contracts v2) is where the build gets its teeth. `obligations` declares what the beat
  asks of the player (`decide`, `clarify`), and every non-exit reply must either **answer one of
  those obligations** or perform a declared **topic move** (`bridge`, `boundary`, `reciprocate`,
  `exit`). `bees.scene.hives.evergreen.respond/ask_after_them` does both, because the evergreen beat
  only asks for `acknowledge`.
- `referents` and `uses_referents` are the other half: every referent a reply presupposes must be
  introduced by **every** beat that can open its page. The followup page is shared by all seven turn
  beats, so no reply on it may use a referent — and none does.
- `frame.temporal` must not contradict `episode_states`. `bees.wet_hive.recovered.explained` is
  `past` and plays only in `succeeded`; writing it as `current` would be a "still" on a finished
  thing.
- `epistemic` is `observed` on eight of the eleven beats, because she is the participant in what they
  describe. The three `bees.hives.season_ahead.*` beats are `inferred`: they are `future` `plan`
  beats about how the season will go, which she is reading off her own hives rather than reporting as
  something already seen. Neither value needs a source; a `reported` or `rumoured` frame **must**
  name a source referent — see pack 06.

### `data/sample_beekeeper/chat_intents/sample_beekeeper.json`

Ten intents: one per non-exit answer in this pack, each bound to the exact `question` + `answer`
pair it presses. Chat-mode users type sentences, and an answer with no intent is unreachable by
typing even though it is on screen.

`requiresAny` is the cheap filter (a message with none of those words cannot match at all);
`keywords` are the weights; `phrases` support a `*` wildcard; `antiKeywords` subtract. The
`synonyms` block is pack-wide and folds `skep`/`hives` into `hive`.

Intent **ids** merge last-wins across datapacks, and for `chat_intents` the order is whatever the
resource manager hands over — so redefining another pack's id is not deterministic unless your pack
also outranks it. Every id here is prefixed `bees.`, so the question does not arise.

### `assets/sample_beekeeper/lang/en_us.json`

Lang keys are one global map merged across every namespace, so `dialogue.*` keys live perfectly well
under this pack's own namespace; there is no need to override `assets/mca_dialogue/`. The flip side
is that a key you define which somebody else also defines is a **silent** collision.

Key shapes:

- `dialogue.<question>` — the page prompt.
- `dialogue.<question>.<answer>` — the button, which is **what the player says**.
- `dialogue.<phrase>` — the villager's line, `/1 /2 /3` for a three-variant pool. A single-line pool
  takes no suffix at all.

Three variants everywhere, which is the floor the mod holds itself to.

Button labels only reference detail present in **every** variant of the line they answer. *"What
went wrong?"* is safe because all three blocked variants state that something is wrong; *"What's the
plan?"* is safe because all three season variants mention planning. A label like *"Move it off the
wet ground"* would not be, because only one variant mentions the ground.

#### Slot tokens: the phrasing rule, with a good and a bad example

Every slot token needs `mcaconversations.slot.<token>` in every locale, and it must be a **complete
noun phrase carrying its own article**:

```json
"mcaconversations.slot.bees_the_far_hive": "the far hive",
"mcaconversations.slot.bees_a_queen_that_stopped_laying": "a queen that stopped laying"
```

**Good** — the sentence around it agrees with nothing:

> `"Middle of %2$s, and %3$s has %4$s."` → "Middle of summer, and the far hive has wax moth in the
> comb."

**Bad** — do not write this:

> `"The %3$s has gone %4$s."` with tokens `far_hive` / `damp`

That English sentence supplies the article, forces the token to be a bare noun, and forces the
adjective to agree with it. In German the article has to match the noun's gender; in French the
adjective has to match number and gender; in Japanese the whole construction is wrong. The token
carries its own article precisely so the translator can rewrite the *phrase* without touching the
*sentence*, and the sentence is written so it does not care what came back.

The same rule is what lets a `person` slot render as a neutral fallback when the neighbour has died
or moved: nothing in the surrounding sentence agreed with their name.

---

## The rules this pack is obeying

1. **Result order is ideal-first, fallback-last** — when every result scores ≤ 0, MCA takes the last.
2. **Action order is state → `next` → `say`.**
3. **Selection is weighted-random over positive totals.** Every answer here has exactly one result
   with positive weight in any given state.
4. **The negated `conversations_scene` sink is not optional.** Without it a dynamic route can win on
   base chance and speak a line whose slots were never bound.
5. **`cooldown_days` and `max_mentions_per_7_days` are hard gates.** No priority outvotes them.
6. **A thread declares `resume_scenes` or it is refused.**
7. **A fallback shares purpose and topic, does not loop, and is at most four hops.**
8. **Every `conversations_*` result carries a `conversations_disabled` sink** or the answer has a
   plain-MCA alternative. Here, both.
9. **Every node has a consequence-free exit**, and openers grant nothing but a cooldown memory.
10. **Every non-exit answer has a chat intent** bound to its exact question+answer.
11. **Button labels are what the player says** — never "Persuade", never a number, never a mechanic.
12. **Hearts move only through `conversations_affection_apply`**, which is why this pack moves
    dispositions and nothing else: an offer of help earns respect, not a heart.

## How to adapt it

**Change first: the profession.** Every scene here names `minecraft:farmer`, because a sample that
does not fire teaches nothing and a vanilla profession is guaranteed to exist. There is no vanilla
beekeeper; the beekeeping is the *subject*, not the job. If you have a mod that adds one, replace
`minecraft:farmer` in three places — `episode_templates` (`professions`), the four scenes
(`profile.profession`), and the beats (`context.profession`) — with its real id, and remember that a
profession from an uninstalled mod simply never matches and never crashes.

Then, in rough order of how much they change:

- **The slot pools.** Add options to `slot_options` and a `mcaconversations.slot.*` key for each.
  Existing villagers keep the option they were seeded with; new ones can draw the new entries.
- **`base_priority`.** The four scenes are spaced 34 / 30 / 22 / 12 so the recovery outranks the
  problem, and the evergreen only wins when nothing else is eligible. Move these before touching
  cooldowns.
- **`identity_values`.** Cheap and low-risk: it nudges, it never gates.
- **The lines.** Three variants per pool is the floor. Keep the slot arguments where they are.
- **`cooldown_days` / `max_mentions_per_7_days`.** Raise these to make the villager quieter about a
  subject; they cannot be worked around from the dialogue side, which is the point.

## What this pack deliberately does not do

- **No `commitment_templates`.** Nobody promises anything here; the offer to help is written as
  willingness and settled by the episode advancing, not by an observer judging a promise kept.
  Promises are pack 04.
- **No `conversations_affection_apply`.** No hearts move. Dispositions do.
- **No new topic in `conversation_catalog/`.** It reuses the shipped `work` topic, which is what the
  `conversations_session` `begin` op requires (`topic` must name a catalogued topic id). Adding a
  category or a topic row is packs 01 and 02.
- **No `person` slot.** The `person` slot type exists and pack 06 uses one; nothing here needs a
  neighbour named.
- **No `conversations_exchange` callback.** This pack remembers *state* (the episode, the thread),
  not *decisions*. Reading back what the player decided is pack 04.
- **No interiority or personality overlay.** Pack 03.
- **It does not redefine or remove anything the mod ships.** Every id is new and the one shared
  basename is a merge. Modifying shipped content is pack 09; removing it is pack 10.

## Honest limits

These files are written against the shipped schemas and the documented engine rules and were checked
for JSON validity, but they have not been run in a production MCA instance. Two places are worth
checking first if something does not fire:

- the scene ids in `/conversations scene candidates work`, which will tell you whether the gate or
  the priority is what dropped a scene;
- `/conversations scene plan` immediately before pressing the button, which is the plan the route
  will be matching against.
