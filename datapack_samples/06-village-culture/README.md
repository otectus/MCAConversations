# 06 — Village culture (`sample_commons`, prefix `commons`)

What a village is like, and what one resident makes of it.

This pack gives a village six culture tokens — a tradition, a value, a work concern, a landmark, a
festival and a debate — and then writes two scenes about the *same* festival: one for the resident
who is glad of it and one for the resident who quietly is not. It also covers the two things that
sit next to culture in the data model: **observed social roles and opinions** (written from a reply,
about a neighbour the JSON never names) and **rumour provenance** (how a villager knows a thing, and
what she may do with it).

---

## What it demonstrates

- `village_culture/` with **all six families** filled, and why a partial culture is not an option.
- `endorsed_by` / `questioned_by` naming **identity** token ids, and the load-time refusal of a
  token listed in both.
- The seeded, one-token-per-family draw, and what a villager with no home village matches.
- `conversations_culture` in both forms — `token`, and `family` + `stance` — and what giving both
  means.
- The same gate written from the scene side, as a `village.culture` context condition with an
  explicit `unknown` policy.
- `conversations_opinion` and `conversations_role`: both need a `cause`, both take their target from
  a bound `person` slot rather than from JSON, roles are directional with no automatic mirror, and
  `withdraw: true` is the one form that needs no cause.
- Rumour provenance on an episode — `knowledge`, `privacy`, `share`, `distortion` — plus the fact
  that confidence is *derived*, and that `distortion` is the one field the runtime never sets.
- A `rumoured` / `reported` beat frame naming a source referent, and the `epistemic_move` /
  `privacy_move` reply fields.

## Install

Two halves, two folders — `data/` is only served from a world's `datapacks/`, `assets/` only from
`resourcepacks/`:

```
06-village-culture/datapack/      -> <instance>/saves/<world>/datapacks/06-village-culture/
06-village-culture/resourcepack/  -> <instance>/resourcepacks/06-village-culture/
```

On 1.21.1 a data pack declares `pack_format` **48** and a resource pack declares **34**, so
one `pack.mcmeta` can no longer serve both roles the way 15 did on 1.20.1. This sample therefore
ships pre-split, and paths written `data/…` or `assets/…` below are paths inside an installed
pack.

Then `/reload` for the data half and F3 + T for the assets half. Raw `dialogue.commons.…` keys on
screen mean you installed one half only.

Culture is **generated once per village and persisted**. Installing this pack does not re-culture a
village that already has one; you want a village that has never been visited by a running instance
of the mod, or a fresh world.

## Try it

Stand in front of an adult villager who lives in a village (not a wanderer — see below).

1. `/conversations context snapshot` — look at `village.culture`. It lists the six tokens the
   village drew. If this pack's tokens are not among them, the village drew somebody else's; make a
   new world or raise this pack's weights.
2. `/conversations profile inspect` — the resident's identity. Whether they *endorse* or *question*
   the shearing supper is decided by whether their profile holds `hospitality` / `crowded_table` /
   `commons_village_meetings` (endorse) or `crowds` / `commons_being_owed` (question). Most
   residents hold neither and **ignore** it, which is not a failure state.
3. `/conversations scene candidates village` — you should see `commons.supper.endorsed` **or**
   `commons.supper.questioned` for a given villager, essentially never both, because the identity
   gates are opposites.
4. `/conversations scene plan` — the frozen plan the dialogue route will match.
5. Talk → **Village** → *"What is this village like?"*.
6. On the borrowed-tool page, press *"They're good for it."*, then
   `/conversations history inspect` — there should now be a `reliability` opinion and a
   `supply_dependency` role, both pointing at the neighbour the scene bound into the `neighbour`
   slot, and both carrying the cause `commons.borrowed_tool.player_vouched`.
7. Check the other villager. The role is **directional**: the neighbour does not automatically hold
   a `customer` role back toward the speaker, and nothing in this pack creates one.
8. On the mill page, press *"Where did you hear that?"* — the villager hedges, because her
   `knowledge` is `told_by` and confidence is derived from the source, not declared.
9. `/conversations chat debug where did you hear that` — `commons.mill.source` should win.

---

## File-by-file walkthrough

### `data/sample_commons/village_culture/sample_commons.json`

Six tokens, one in each family: `tradition`, `value`, `work_concern`, `landmark`, `festival`,
`debate`.

**A village is all six families or none.** The draw takes exactly one token per family, so a pack
that fills five families and skips one hands every scene binding that sixth family a hole to handle.
The mod's honest answer to a family it cannot fill is that this install has no village culture at
all — which is why this pack ships all six even though only three of them are spoken about in its
dialogue. The other three (`commons_ask_before_you_take`, `commons_the_bell_post`,
`commons_whether_the_commons_stays_common`) exist so the village is *complete*, and so other packs'
scenes that gate on those families have something to find.

**The draw is seeded on the world seed and the village id, and nothing else** — not the day, not the
population, not who is asking. Two players walking in from opposite directions find the same place;
a server restart does not rewrite what the village believes about itself; and a pack that later adds
ten festivals leaves existing villages exactly as they were.

`weight` competes within a family. These sit at 9–12, next to the shipped tokens' 8–12, so they are
plausible without crowding the mod's own out.

`aliases` renames a token without re-culturing anybody: `commons_the_supper` resolves forward to
`commons_the_shearing_supper`.

#### `endorsed_by` and `questioned_by`

Both lists name **identity token ids**, never culture ids. That is what stops a culture from being a
hive mind: the festival belongs to the village, and whether a given resident is glad of it comes
from who that resident already is. A villager who values `hospitality` endorses the supper; one
averse to `crowds` has a reservation about it; **most residents ignore it**, and indifference is the
common case, not a bug.

Some of the ids here are the mod's shipped identity tokens (`hospitality`, `crowds`, `fairness`,
`orderly_tools`, `collaborative`, `solitary`, `privacy`, `improvising`, `independence`, `duty`,
`teaching`, `pragmatic`, `local_history`, `tradition`, `ambition`, `followed_work`,
`crowded_table`); three are this pack's own (`commons_common_ground`, `commons_being_owed`,
`commons_village_meetings`), defined in `identity_tokens/sample_commons.json`. Either is fine; an id
that resolves to nothing simply never matches.

**A token listed in both sets is refused at load**, because the tie would be settled by iteration
order — i.e. by which list the code happened to read first, which is not a design decision anybody
made. Check your lists by hand: `commons_being_owed` appears in `questioned_by` on two tokens
(`commons_the_borrowed_tool` and `commons_the_shearing_supper`) and in `endorsed_by` on none.

The stance keys are `endorse`, `question`, `ignore`.

### `data/sample_commons/identity_tokens/sample_commons.json`

Three tokens whose only job in this pack is to be named by the culture file — one `value`, one
`aversion`, one `interest`. `commons_common_ground` `conflicts` with `commons_being_owed`: the
exclusion is symmetric and declaring it once is enough, and it means no villager can both believe in
holding things in common and hate being under an obligation.

Identity generation is seeded on the world seed and the villager UUID only, so installing this pack
gives no existing villager a new value.

### `data/sample_commons/episode_templates/sample_commons.json`

Two episodes, written to have deliberately different provenance.

| | `commons.borrowed_tool` | `commons.mill_hearsay` |
|---|---|---|
| `knowledge` | `participant` — she lent it | `told_by` — somebody told her |
| `privacy` | `ordinary` | `discreet` |
| `share` | `may_name` | `may_describe_anonymously` |
| `distortion` | `none` | `omitted_detail` |
| derived confidence | certain | at best "likely" |

- **Confidence is derived, not declared.** There is no `confidence` field. The source is what a
  footing can honestly rest on: a `public_notice` supports certainty, `told_by` supports "likely",
  and an `unknown_rumor` cannot be held more firmly than "uncertain" no matter what a pack writes.
  Asking for a firmer confidence is not an error — the value is lowered and the line hedges, which
  is why `dialogue.commons.scene.mill.hearsay/*` is written with "for what that is worth" and "I
  would not repeat it as fact" already in it.
- **`share` may be narrower than `privacy` implies and never wider**, so a confidence cannot be
  laundered by relabelling what may be done with it. `may_describe_anonymously` on the mill episode
  is what makes the *names* drop out of any copy that reaches another villager — dropped from the
  copy, not merely hidden, because a name kept in a record and merely not spoken is one authoring
  mistake away from being spoken. `share` never stops the person a thing happened to from speaking
  about their own life.
- **`distortion` is the one field nothing in the runtime ever sets.** Propagation weakens confidence
  and adds hedging; it does not invent detail. `omitted_detail` here is an *authored* fact about
  this story: she is repeating a version with something missing from it. That is what lets two
  villagers disagree about what a public event *meant* without either contradicting the event log
  about what it *was*.
- Propagation itself is all refusals: `may_not_share` never moves; three hops from the person it
  happened to and it stops; confidence falls a step per hop; salience falls 15 per hop and below 10
  nobody brings it up; privacy travels with the fact; what a player said about themselves never
  travels unless it is `public`. Nothing here has to be configured — it is what the fields above
  already mean.
- `commons.borrowed_tool` has a `neighbour` slot with **no `slot_options`**. Person slots are not
  drawn from an authored pool: they are bound at runtime and stored as a UUID, then re-resolved at
  render time — which is why a neighbour who has died or moved renders as the neutral fallback
  instead of being named as though still present. The slot *type* is declared on the scene, not
  here.

### `data/sample_commons/thread_templates/sample_commons.json`

One thread so the borrowed tool can be picked back up days later. `resume_scenes` is required and
names `commons.borrowed_tool.settled` — the scene written for having been away. (This file is not in
the design brief's file list for pack 06; it is here because a scene that binds an episode to a
thread needs the thread to exist.)

### `data/sample_commons/conversation_scenes/sample_commons.json`

Six scenes, all `purpose: "topic:village"` — which is what makes the `fallback` legal, since a
fallback target must share this scene's purpose **and** topic. All five specific scenes fall back to
`commons.village.evergreen`, which declares no fallback and terminates the chain.

The culture gate is written on the scene side as a context condition:

```json
{"field": "village.culture", "has": "commons_the_shearing_supper", "unknown": "fail"}
```

`unknown` is required thinking on every context condition. `fail` is right here: if nothing can tell
us what this village keeps, a scene *about* what it keeps is not eligible. `neutral` would let the
scene fire for a village that has no culture at all — which is exactly the bug the "all six families
or none" rule exists to prevent. (`fallback` degrades to the declared fallback; `error` is for
authoring and must never ship.)

The two supper scenes are identical except for their `context.identity` block: `value` any of
`hospitality` / `commons_common_ground` for the endorsed one, `aversion` any of `crowds` /
`commons_being_owed` for the questioned one. They share `base_priority` 26 and both cap at one
mention per seven days, so a villager who somehow qualified for both would not get the supper twice.
Their `shape`s differ (`celebrate` vs `debate`) so repetition suppression treats them as different
conversations.

`commons.borrowed_tool.active` declares the person slot's **type**:

```json
"required_slots": {"neighbour": "person", "tool": "localized_token"}
```

That declaration is what the opinion and role directives on the respond page read their target from.

`commons.mill.hearsay` declares `teller` as a second `person` slot: the villager who told her. That
is not decoration — see the beat frame below.

### `data/sample_commons/dialogues/conversations.cat.village.json`

The basename matches MCA's file, so this file's `answers` array is **merged** into the shipped
`conversations.cat.village` page; it contains only the pack's own answer, `commons`.

Seven results, ideal-first, plain fallback last (when every result scores ≤ 0, MCA takes the last
one). Each of the six scene routes carries the canonical triple — `+900` on the plan naming this
scene, **`−5000` on the plan not naming it**, `−2000` on the dynamic layer being off — plus sinks on
`branching` and `topics`, because every one of them opens a session and speaks through
`conversations_say`. The negated sink is load-bearing: selection is weighted-random over positive
totals, so without it a route could win while the director had planned something else, and speak a
line whose `%2$s` and `%3$s` were never bound.

The **last** result is the plain-MCA alternative: no `conversations_*` action, just
`"next": "commons.plain.respond"`, sunk whenever `dynamic` is enabled.

#### `conversations_culture`, both ways

Two of the routes carry an extra, *positive* culture condition. These are scores, not gates — the
scene's own context condition is the gate:

```json
{"chance": 60, "conversations_culture": {"token": "commons_the_shearing_supper"}}
```

`token` asks whether this village holds a particular token **at all**. That is the gate a line
naming the split oak, or the shearing supper, needs.

```json
{"chance": 60, "conversations_culture": {"token": "commons_the_shearing_supper",
                                         "family": "festival", "stance": "question"}}
```

`family` with `stance` asks what **this resident** makes of whatever their village holds in that
family. Given both, **both must hold** — the village keeps the supper *and* this villager has a
reservation about it. Writing them as two separate conditions would let a page fire for a villager
who questions a festival their village does not actually keep, since with a bare `family` + `stance`
the "festival" in question is whichever one that village drew.

Stances are `endorse`, `question`, `ignore`; `ignore` is the default and by far the commonest.

**A villager with no home village matches nothing, before negation.** A wanderer has no culture, so
`{"not": true}` on a culture condition does **not** fire for them either — "does not endorse" would
be asserting something about a village that is not theirs. If you want a route for wanderers, gate
it on `place.village_id` being unknown with an explicit `unknown` policy, not on a negated culture
condition.

### `data/sample_commons/dialogues/commons.scene.borrowed_tool.active.respond.json`

The page where durable social state is written. `vouch_for_them`:

```json
"conversations_opinion": {
  "axis": "reliability", "target": "neighbour", "delta": 2,
  "cause": "commons.borrowed_tool.player_vouched",
  "privacy": "discreet", "expires_days": 60
},
"conversations_role": {
  "role": "supply_dependency", "target": "neighbour",
  "cause": "commons.borrowed_tool.player_vouched"
}
```

Four things to notice:

1. **`target` is a slot name, not a villager.** It names the `neighbour` slot on the frozen plan, and
   the directive resolves it to whoever the runtime bound there. A directive naming a UUID could not
   have been authored: that villager did not exist when the pack was written.
2. **`cause` is required on both.** An opinion with no cause could only ever produce "I don't like
   them", which is generic drama; a role nobody can account for is a random rival. Use a stable
   string you can grep for.
3. **The axes are closed**: `reliability`, `warmth`, `respect`, `trust`, `fairness`, `skill`. The
   roles are closed too: `coworker`, `supply_dependency`, `customer`, `beneficiary`, `mentor`,
   `apprentice`, `trusted_neighbour`, `recurring_disagreement`, `cared_for`, `avoided`,
   `shared_event`.
4. **Roles are directional and the mirror is never created automatically.** That she counts the
   neighbour a supply dependency is not evidence that the neighbour counts her a customer, and
   asserting it from one side's account would be inventing the other side. If you want both, write
   both, from two scenes, on two villagers.

Lifetimes belong to the *kind* of relationship rather than to whichever code path noticed it —
`supply_dependency`, `coworker`, `mentor`, `apprentice` and `cared_for` last until withdrawn;
`trusted_neighbour` 90 days; `customer` and `avoided` 60; `beneficiary` 45;
`recurring_disagreement` 30; `shared_event` 21. Seeing a role again refreshes its lapse date and
keeps its original day, which is what lets a line say "for years now" and be right.

### `data/sample_commons/dialogues/commons.scene.borrowed_tool.settled.respond.json`

The other half, and the one form of the directive that takes no `cause`:

```json
"conversations_role": {"role": "supply_dependency", "target": "neighbour", "withdraw": true}
```

A role that lasts until it is withdrawn has to be endable, or a villager accumulates every
relationship they have ever had. `withdraw: true` is the end of one, so there is nothing to account
for — and it is the **only** form that does not need a cause. The same answer advances the episode
to `remembered` and resolves the thread, so the subject stops being live without being forgotten.

### `data/sample_commons/dialogues/commons.scene.mill.hearsay.respond.json`

Two ways to answer a rumour, and they are different *kinds* of answer:

- `ask_where_they_heard_it` carries `"epistemic_move": "ask_source"` in its reply contract. The
  epistemic moves are `believe`, `doubt`, `ask_source`, `suspend_judgment`, `correct`, `withhold`.
- `keep_it_between_us` carries `"privacy_move": "keep_private"` — the privacy moves are
  `keep_private`, `permit_sharing`, `ask_permission`, `publicize`. Player permission has exactly one
  representation, and it is the privacy level: a player saying "you can tell people" makes the fact
  public. A flag beside the privacy level would allow the contradiction of a confidential fact that
  may nonetheless be repeated.

Neither answer rewrites the episode's provenance from JSON. Setting an account straight is
`conversations_episode` with `{"op": "correct"}`, which changes the *footing* — the villager now
holds it as certain and any authored distortion is cleared — and never the source, because being
told the truth does not mean they were there after all. This pack does not use it: nobody in it is
in a position to correct the mill story.

### `data/sample_commons/conversation_beats/sample_commons.json`

Six opening beats, ten turn beats, eighteen replies.

The rumour beats are the interesting ones. `commons.mill.hearsay.open` has
`"epistemic": "rumoured"`, and **a `reported` or `rumoured` frame must name a source referent** —
here `"referents": {"teller": "slot:teller", "what": "slot:what"}`. That rule is what stops a rumour
being spoken as an observation: the frame cannot be written at all without pointing at where the
story came from, which is also the slot the "where did you hear that" answer reads.

`openness: "guarded"` on that beat says the villager is not inviting the subject to be opened
further, and the allowed stances (`curiosity`, `restraint`, `exit`) match: there is no
`practical_help` answer to a rumour.

Everywhere else the contract rules are the ordinary ones: every non-exit reply either answers an
obligation the inbound beat declared or performs a topic move (`bridge`, `boundary`, `reciprocate`,
`exit`); every referent a reply uses is introduced by every beat that can open its page (which is
why the shared `commons.followup` page's replies use none); and a beat's tense never contradicts the
episode states it plays in — `commons.borrowed_tool.settled.closed` is `past` and plays only in
`succeeded`.

### `data/sample_commons/chat_intents/sample_commons.json`

Thirteen intents, one per non-exit answer, each bound to its exact question+answer. Ids are prefixed
`commons.` because intent ids merge **last-wins** across datapacks and, for `chat_intents`, the
order is whatever the resource manager hands over — so redefining an id from another pack is not
deterministic unless your pack also outranks it.

### `assets/sample_commons/lang/en_us.json`

- `dialogue.<question>` prompts, `dialogue.<question>.<answer>` buttons (what the **player** says),
  `dialogue.<phrase>/1 /2 /3` for the villager's say pools.
- **`mcaconversations.culture.<id>`** for all six culture tokens, written as phrases that can be
  dropped into a sentence: *"the shearing supper"*, *"whether the commons stays common"*. Use that
  prefix even for your own tokens — the shipped resolver looks under `mcaconversations.culture.*`,
  so a token keyed under your own namespace prefix would render as a raw key.
- **`mcaconversations.slot.<token>`** for the six slot tokens, each a complete noun phrase carrying
  its own article, with the surrounding sentence written so nothing agrees with it:
  `"I lent %3$s to %2$s a fortnight back"` works whatever comes back; `"The %3$s is back"` would
  not.
  Note that `commons_the_good_blade` is singular on purpose — a plural token like "the good shears"
  would force the verb in every sentence using it to agree, which is exactly the trap the phrasing
  rule exists to avoid.

The supper lines name the festival **in prose** rather than through a slot, because slots bind from
an episode and these two scenes have no episode. The `mcaconversations.culture.*` keys are still
required: they are what the runtime renders when *it* names a token, and they are checked per locale.

---

## The rules this pack is obeying

1. **All six culture families, or none.** A partial culture is not a supported state.
2. **`endorsed_by` / `questioned_by` name identity ids**, and no id appears in both.
3. **Culture is seeded per world+village and persisted**; it is not re-rolled by installing content.
4. **A villager with no village matches no culture condition, before negation.**
5. **`conversations_opinion` and `conversations_role` need a `cause`** and take their target from a
   bound `person` slot — except `withdraw: true`, which needs no cause because it ends one.
6. **Roles are directional. No mirror is ever created.**
7. **Confidence is derived from `knowledge`; `distortion` is authored and never set by the runtime.**
8. **A `rumoured` or `reported` frame names a source referent.**
9. **The negated `conversations_scene` sink is on every dynamic route**, and every
   `conversations_*` result also has a `conversations_disabled` sink and a plain-MCA alternative.
10. **Fallback result last; actions ordered state → `next` → `say`.**
11. **Every page has a consequence-free exit**; every non-exit answer has a chat intent.
12. **No hearts move.** Dispositions do. Hearts are `conversations_affection_apply` only.

## How to adapt it

- **Change the six tokens first.** They are the pack's whole personality, and the ids are referenced
  in exactly three places each: the culture file, the scene context conditions, and the lang file.
- **Then the endorse/question lists.** This is where a village stops being a mood board and becomes
  a place people disagree about. Pick identity tokens that genuinely cut both ways; a token with an
  empty `questioned_by` produces a village everybody approves of, which reads as propaganda.
- **The `person` slots.** If you do not want a neighbour named, drop the `neighbour` slot from the
  scene and the episode, and drop the opinion and role directives with it — a directive whose target
  slot is unbound has nothing to write about.
- **The rumour episode.** Change `knowledge` before you change the lines: the hedging in the lines
  is there because the source is `told_by`, and a `witnessed` episode with hedged lines reads as a
  villager being evasive about something she saw.
- **`base_priority` and the caps** last. They are the least interesting knob and the most tempting.

## What this pack deliberately does not do

- **No `commitment_templates`.** Nobody promises anything; the borrowed tool comes back because the
  episode advances, not because an observer judged a promise kept. Promises are pack 04.
- **No `conversations_claim`.** Nothing the player says about themselves is stored. A claim requires
  a `source` naming the `question/answer` that introduced it, and free-form typed text may *select*
  a claim but may never *become* one.
- **No `{"op": "correct"}` on an episode.** Correcting an account is a scene in its own right and
  needs somebody who was actually there.
- **No `event_observed` resolver anywhere.** It exists in the enum and has no observer installed; it
  would never judge anything.
- **No new topic or category.** It reuses the shipped `village` topic, which is what
  `conversations_session` `begin` requires.
- **It does not redefine or remove any shipped id.** Modifying shipped content is pack 09; removing
  it is pack 10.

## Honest limits

Written against the shipped schemas and the documented engine rules, and checked for JSON validity —
not run in a production MCA instance. Two specific caveats worth knowing before you copy the
pattern:

- **No scene the mod itself ships binds a `person` slot.** The slot type, the storage-as-UUID and
  the render-time re-resolution are all documented, and `conversations_opinion` / `conversations_role`
  read their target from a bound slot by construction — but this pack is the first content to lean
  on it, so check `/conversations history inspect` for a resolved target rather than assuming.
- **Culture generation needs a village the mod has actually cultured.** If `village.culture` is
  empty in `/conversations context snapshot`, nothing in this pack except
  `commons.village.evergreen` can fire, and that is the "no culture at all" state behaving exactly
  as designed.
