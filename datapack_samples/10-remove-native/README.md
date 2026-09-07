# 10 — remove-native

**Namespace:** `sample_trim` **Prefix:** `trim`

Taking shipped content *away*. This is the hardest of the ten samples, because the datapack system
has no subtraction operator: there is no `"remove": true` flag in any of the eleven schemas, and no
way to delete a key from a catalog. Everything below is either a config switch, or a replacement of
a whole file with a smaller version of itself.

The four techniques are ordered **safest first, most brittle last**. Work down the list and stop at
the first one that does what you need. Most people should stop at technique 1.

> **This pack is destructive by design.** Installing it removes the Events hub category, every
> dynamic scene, and two chat intents, from the world you install it in. It is teaching material.
> Read it before you enable it.

---

## What it demonstrates

1. **Config first** — the honest answer for most removals. Real option names, the file each one
   lives in, and what "off" actually does. Ships no files.
2. **Emptying a whole catalog** — `data/mcaconversations/conversation_scenes/generated.json`
   replaced with `{"scenes": {}}`.
3. **Dropping a hub category** — `data/mcaconversations/dialogues/conversations.json` replaced with
   a complete copy that has the `events` answer removed.
4. **Neutralising a chat intent** — both forms. `data/mcaconversations/chat_intents/chitchat.json`
   replaced with a complete copy minus `chitchat.food` (deterministic), and
   `data/sample_trim/chat_intents/trim_neutralise.json` redefining `chitchat.season` with an
   unreachable keyword set (not deterministic).
5. **What you cannot do** — the last section, and the most important one.

---

## Install

Copy `10-remove-native/datapack/` into `saves/<world>/datapacks/10-remove-native/`, then `/reload`.

There is **no `assets/` half**, and therefore no `resourcepack/` folder. This pack removes things;
it adds no lines and needs no lang keys. (Every other sample in this set ships two halves, split
into `datapack/` and `resourcepack/` because on 1.21.1 a data pack declares `pack_format` **48** and
a resource pack declares **34**, so one manifest cannot serve both. This one needs only the data
half, and that is the exception.)

`pack_format` is **48**, the 1.21.1 data pack format. Paths written `data/…` below are paths inside
an installed pack; in this folder they sit under `datapack/`.

A datapack in the world folder already outranks the mod's built-in data, so the three replacement
files win without any ordering work on your part. The *fourth* technique's non-deterministic half
does not — see technique 4.

**To undo any of this**, delete the pack and `/reload`. Nothing here writes to the world save.

---

## Technique 1 — config first

**Ships nothing. Reversible. Per install. Never fights a mod update.** If the thing you want gone
has a switch, use the switch.

A datapack override is permanent for as long as the pack is installed, invisible to the player, and
silently stops receiving mod updates for whatever it replaced. A config option is a line in a text
file that a player can flip back.

Three files, generated on first run:

| File | Scope |
|---|---|
| `config/mcaconversations-common.toml` | per installation, not synchronised — feature switches, debug flags |
| `<world>/serverconfig/mcaconversations-server.toml` | per world, **synchronised to every client** — gameplay values |
| `config/mcaconversations-client.toml` | per player, client only — presentation |

### `[features]` (common)

| Option | Set it to `false` when you want... |
|---|---|
| `enableTopics` | every converted topic gone. Each falls back to its legacy one-line result and returns to its category; the branching trees are not entered at all. |
| `enableBranching` | the topics kept but the trees behind them gone. Each starter carries an explicit legacy fallback, so nothing is ever left as an empty page. **Distinct from `enableTopics`** — that is the distinction to get right. |
| `enableStates` | conversation states (grateful, grieving, elated, annoyed, smitten, proud) neither recorded nor referenced. |
| `enableTemplates` | templated lines rendered with neutral fallbacks ("someone", "the village"). |
| `enableGossip` | no events detected or told; the "news" topic answers "quiet week". |
| `enableQuests` | MCA: Quests integration inert — the four `conversations_quest_*` conditions score 0, quest lines are not voiced, finished quests seed no gossip. |

`hubEntryMode` is a string, not a boolean: `ADDITIVE` (default — MCA's Chat answer unchanged, the
Conversations button visible as its own entry), `REPLACE` (MCA's Chat answer opens the Conversations
hub, the separate button hidden), `HIDDEN` (Chat unchanged, the Conversations button hidden). If
what you want is "no visible Conversations entry point", that is `HIDDEN`, and it is a config line,
not a datapack.

### `[world]` (common)

`enableWeatherLines`, `enableSeasonLines`, `enableHolidayLines` — each turns off both a template
variable and its matching `conversations_*` condition (`weather` / `season` / `holiday`). Content
degrades to authored fallback lines rather than disappearing mid-tree. `enableWeatherLines` is also
what the `world` feature flag reads, so `conversations_disabled: "world"` scores as disabled with it
off; it is defined here under `[world]` and nowhere else.

### `[rpg]` (`enable*` in common, the `disposition*` numbers in server)

| Option | Off means |
|---|---|
| `enableDispositions` | no vector state read or written; disposition-gated results never match and their authored fallbacks fire; checks run on a hearts-only formula. |
| `enableChecks` | checked stances resolve through their plain fallback result — the 0.6.0-style single outcome. |
| `enableCheckTiers` | binary outcomes: crit collapses into success, partial into rebuff. |

### `[dynamic]` — living histories (switches in common)

| Option | Off means |
|---|---|
| `enabled` | **the master switch.** No scene is ever planned, every `conversations_scene` condition scores 0, and every dynamic route takes its sink. |
| `identityEnabled` | no identity profile generated or persisted; selection is identity-neutral. |
| `episodesEnabled` | only evergreen scenes selected; no promise ever created. |
| `socialOpinionsEnabled` | no opinions of named neighbours, no observed roles. |
| `villageCultureEnabled` | no shared village tokens. |
| `dynamicTopicSlots` (server) | set to `0` to keep the six fixed hub categories alone, with no context-specific entries above them. |
| `maxInitiativesPerVillagerPlayerDay` (server) | set to `0` to disable villager initiative entirely. |

### Elsewhere

- `[history] enabled = false` — nothing new persisted to `data/mcaconversations_history.dat`.
- `[chat] enableChatMode = false` — the exact pre-chat-mode experience; every chat intent is inert
  without needing to be edited at all. **If what you want is "no free-typed chat", stop here and do
  not do technique 4.**
- `[group] enabled` — already `false` by default.
- `[gossip] detectMarriage` / `detectDivorce` / `detectDeath` / `detectBirth` / `detectArrival` /
  `detectDeparture` — per-event-type detection toggles, for removing one kind of news rather than
  all of it.
- `[townstead] enabled` / `[capitals] enabled` — off means Conversations behaves exactly as though
  that mod were absent.

**How to check it worked.** Set the option, restart (common config is read at startup), and use the
debug commands: `/conversations scene plan` should report no plan with `dynamic.enabled = false`;
`/conversations gossip list` should be empty with `enableGossip = false`;
`/conversations chat debug <anything>` should match nothing with `enableChatMode = false`. Feature
toggles act through the `conversations_enabled` / `conversations_disabled` dialogue conditions and
server-side early-outs, so content degrades to fallback lines rather than vanishing mid-tree — if
you see a blank page, that is a bug, not the switch working.

---

## Technique 2 — empty a whole catalog

**File:** `data/mcaconversations/conversation_scenes/generated.json`

```json
{"scenes": {}}
```

That is the entire mechanism. `SceneCatalogLoader` extends `SimpleJsonResourceReloadListener`, whose
`prepare` resolves **one resource per exact `ResourceLocation`, top-most pack wins**. Ship the mod's
path and the mod's copy is never opened. The loader then reads the `scenes` object, finds nothing,
and publishes an empty catalog.

The same shape works for any of the eleven catalogs — swap the directory and the sub-object name:
`conversation_catalog` (`topics`), `conversation_beats` (`beats` / `replies`), `conversation_scenes`
(`scenes`), `episode_templates` (`episodes`), `thread_templates` (`threads`),
`commitment_templates` (`commitments`), `identity_tokens` (`tokens`), `village_culture`,
`interiority` (`profiles`), `profession_profiles`, `chat_intents` (`intents` / `synonyms`).

**What breaks.** Every shipped dynamic route now takes its sink. A route into a scene looks like
this:

```json
{"chance": 900,  "conversations_scene": {"is": "topic.weather.the_long_dry"}},
{"chance": -5000, "conversations_scene": {"is": "topic.weather.the_long_dry", "not": true}},
{"chance": -2000, "conversations_disabled": "dynamic"}
```

With no scenes in the catalog, no plan is ever frozen, the positive condition never fires and the
negated one always does, so the result scores `-5000` and the answer falls through to its ordinary
line. **That is exactly why those sinks exist** — and it is why this technique does not leave the
player staring at an empty page.

**Do not do this.** `dynamic.enabled = false` in `[dynamic]` produces the identical outcome, and it
is cheaper, reversible, per-install, and does not stop you receiving the next 200 scenes the mod
ships. This file is here to show you the mechanism for a catalog that has *no* config switch — and
to be honest that for this particular catalog, the config switch exists and is better.

**How to check it worked.** `/reload`, then `/conversations scene candidates work` while looking at
a villager: no candidates. `/conversations scene plan`: no plan. Talk to a villager about the
weather and you get the ordinary hand-authored line, not a scene.

**Interaction with pack 09.** If `09-modify-native` is also installed, it ships
`data/sample_retune/conversation_scenes/sample_retune.json`, which *redefines*
`work.librarian.current_task.evergreen`. That is a different path, so this file does not suppress
it: emptying `generated.json` removes 373 scenes and pack 09 puts one of them back. Emptying a
catalog only silences the file you replaced, never the whole directory.

---

## Technique 3 — drop a hub category or a starter

**File:** `data/mcaconversations/dialogues/conversations.json`

MCA's `Dialogues` also extends `SimpleJsonResourceReloadListener` and also does not override
`prepare`, so the same rule applies: **same exact path replaces**. The cross-file *merge* — the one
that makes technique 1 of pack 09 work — is a separate step keyed on the **basename only**, and it
runs on whatever survived that first resolution. So a file at
`data/<yourns>/dialogues/conversations.json` would be *merged* and could only add; a file at
`data/mcaconversations/dialogues/conversations.json` *replaces*, and can subtract.

This is the supported way to remove a hub button, a category starter, or any shipped answer.

The shipped hub has eight answers: `chitchat`, `profession`, `village`, `events`, `personal`,
`relationships`, `babble`, `back`. This copy has seven. `events` is gone; the other seven are
restated **exactly** as the mod ships them, in the same order, with the same `constraints` strings
and the same `next` targets.

**Complete, not truncated.** Anything you leave out is gone. Drop `back` by accident and the player
has no way out of the hub. Drop `babble` and a baby villager's only answer disappears. This is why
the file below is a full copy with one deletion, and not a hand-written stub.

**What breaks.**

- `conversations.cat.events` still exists as a question. It is simply unreachable from the hub. Any
  other route into it — a chat intent, a dynamic hub slot, another pack — still works.
- Two shipped chat intents, `events.news` and `events.noticed` in
  `data/mcaconversations/chat_intents/events.json`, bind to `conversations.cat.events`. They are
  now bound to a page the player cannot open through the hub. Chat mode drives the engine directly
  rather than clicking the hub, so they may still fire. If you want the topic genuinely gone, remove
  those intents too (technique 4).
- **This file is now yours to maintain.** If a future version adds a seventh category, or changes
  the `constraints` on `relationships`, or fixes a bug in `babble`, none of it reaches you. Nothing
  logs a warning. Re-diff this file against the mod's copy after every update.

**Removing one starter instead of a whole category** is the same technique applied to the category
page: copy `data/mcaconversations/dialogues/conversations.cat.chitchat.json`, delete the one answer,
ship it at that path. Be aware of the size of what you are adopting — that file is about 70 KB of
carefully weighted results, and you now own all of it. Consider whether `enableTopics = false` or a
`conversations_disabled` sink gets you close enough first.

**How to check it worked.** `/reload`, talk to an adult villager, open **Conversations**: five
category buttons and "Never mind", with no Events. Then check you did not break the rest — open each
remaining category and come back out through its `back` answer.

---

## Technique 4 — neutralise a chat intent

You cannot delete a key. `ChatIntentLoader` folds every file into one map keyed by intent id and
there is no removal syntax. There are exactly two options.

### 4a. Replace the file at its exact path — deterministic

**File:** `data/mcaconversations/chat_intents/chitchat.json`

A complete copy of the shipped file with `chitchat.food` removed. Six intents remain
(`greeting.checkin`, `chitchat.day`, `chitchat.weather`, `chitchat.season`,
`checkin.toddler.delight`, `checkin.toddler.ask`) and the `synonyms` block is restated unchanged.

Guaranteed to work, for the usual reason: top-most pack wins per exact `ResourceLocation`.

**What breaks.** The `food` *answer* still exists on the chit-chat page and the player can still
click it. Only the chat-mode route to it is gone — typing "what do you like to eat" no longer drives
the villager to it. And the shipped `synonyms` in this file are now yours: if the mod adds a synonym
class here, you will not get it.

### 4b. Redefine the id with an unreachable keyword set — *not* deterministic

**File:** `data/sample_trim/chat_intents/trim_neutralise.json`

```json
"chitchat.season": {
  "question": "conversations.cat.chitchat",
  "answer": "season",
  "keywords": {"zzqxtrimsentinelalpha": 0.01},
  "requiresAll": ["zzqxtrimsentinelalpha", "zzqxtrimsentinelbeta"],
  "category": "topics"
}
```

The parser refuses an intent with zero keywords *and* zero phrases, and every keyword weight must be
inside `(0, 10]`, so an intent cannot be emptied — only made unreachable. `requiresAll` demands
every listed token be present in the player's message; two nonsense sentinels, one of which is not
even a keyword, can never both be present in anything a player types.

**Why this is the brittle one.** `ChatIntentLoader.apply` iterates `files.entrySet()`, and `files`
is the `java.util.HashMap` that vanilla's `SimpleJsonResourceReloadListener.prepare` builds. Last
`put` wins, and the order is **hash order — not pack order, not alphabetical**. It is stable for a
fixed set of installed files, so it will look reliable while you test it, and it can flip the moment
any other pack adds or removes a `chat_intents` file. `conversation_catalog` and
`conversation_beats` share this problem; `conversation_scenes` does not, because
`SceneCatalogLoader` explicitly sorts by `ResourceLocation` first.

Use 4b only when you cannot use 4a — for instance when you want to neutralise one intent out of a
file whose other contents you do not want to adopt, and you can live with it occasionally losing.
**Where determinism matters, replace the path.**

**How to check it worked.**

```
/conversations chat debug what do you like to eat
/conversations chat debug what season is it
```

The first should no longer score `chitchat.food` at all (4a). The second should no longer score
`chitchat.season` (4b) — and if it still does, 4b lost the race on your install, which is the
failure mode, not a mistake in the file.

---

## What you cannot do

Checked in source and in MCA's bytecode, not assumed.

**There is no `"remove": true` flag.** Not in `conversation_catalog`, `conversation_beats`,
`conversation_scenes`, `episode_templates`, `thread_templates`, `commitment_templates`,
`identity_tokens`, `village_culture`, `interiority`, `profession_profiles` or `chat_intents`, and
not in MCA's dialogue schema either. Every one of those loaders reads one named sub-object and folds
it into a map; none of them has any notion of a negative entry. If you have seen such a flag in
another mod's datapack format, it is not here.

**You cannot un-merge another pack's answers.** MCA's `Question.merge` is
`answers.addAll(other.getAnswers())`. If pack A merges an answer into
`conversations.cat.chitchat`, your pack cannot take it out — replacing
`data/mcaconversations/dialogues/conversations.cat.chitchat.json` removes the *mod's* answers,
because that is the path you replaced, and pack A's file is at a different path and is merged in
afterwards regardless. The only lever over another pack's content is to not install it, or to
persuade its author.

**Never leave an `auto` question with an empty `answers` array.** MCA calls `getRandomAnswer()` on
an `auto` question, which is `answers.get(random.nextInt(answers.size()))`, and `nextInt(0)` throws
`IllegalArgumentException: bound must be positive`. MCA's own `auto` questions include `root`,
`chat` and `rumors`. Emptying one is a crash, not a removal.

**An empty `answers` array on a *normal* question is safe, and is still not a removal.** The
question registers, nothing throws, and the player gets a dialogue screen with zero response cards
and no way forward. That is a dead end, not a deletion. If you want a page gone, remove the answers
that route *into* it — not the answers *on* it.

**Removing a `next` target does not remove the answer that points at it.** A `next` naming a
question that no longer exists is a route into nothing. Work backwards from the entry point.

**A hard gate is not a removal either, but it is often what you actually want.**
`cooldown_days` and `max_mentions_per_7_days` on a scene are hard gates rather than scores;
a `{"chance": -2000, "conversations_disabled": "<feature>"}` sink reliably kills a result; a
`constraints` token keeps an answer off the screen for whole classes of villager. All three are
reversible, and none of them adopts a file.

---

## The rules this pack is obeying

- **Any replacement file is complete and correct.** The hub file restates seven answers with their
  original order and constraints; the intents file restates six intents and the synonyms block. Both
  were diffed against the mod's copies to confirm that exactly one entry was dropped from each.
- **Fallback result last**, and **actions ordered state -> `next` -> `say`** — inherited unchanged
  from the mod's own file, because the replacement is a copy.
- **`constraints` strings are copied verbatim.** They are AND-only, split on commas, and unrecognised
  tokens are silently dropped by `Constraint.fromStringList`. Retyping one from memory is a good way
  to turn a gate into a no-op without any error appearing anywhere. (There is no `child` token in
  MCA's registry, for example. `!child` does nothing.)
- **Top-level `_comment` only.** Every loader reads one named sub-object and ignores the rest of the
  root object, which is why a root-level `_comment` survives. Do not put one inside an individual
  entry unless you have checked that entry's parser.
- **No answer is left dangling.** `back` and `babble` are still on the hub.

---

## How to adapt it

- **Start by deleting files from this pack.** Each of the four is independent. Keep the one you want.
- **Empty a different catalog** by copying `generated.json`'s shape and changing the directory and
  the sub-object name — `{"topics": {}}` for `conversation_catalog`, `{"profiles": {}}` for
  `interiority`, and so on. Check what the mod's own file in that directory is called: you must
  match the **filename** as well as the directory.
- **Drop a different hub answer** by editing the copy of `conversations.json` — but re-copy it from
  the mod's current jar first, so you are subtracting from today's version rather than this sample's
  snapshot.
- **Drop a starter instead of a category** by taking the same approach to
  `conversations.cat.<category>.json`, and read the size warning in technique 3 before you commit.
- **Neutralise a different intent** by changing the id in `trim_neutralise.json`, or by copying the
  relevant `chat_intents` file and deleting the entry. Prefer the second.

---

## What this pack deliberately does not do

- **It does not empty an `auto` question**, because that crashes. See *What you cannot do*.
- **It does not ship an empty `answers` array anywhere**, because a screen with no response cards is
  a dead end rather than a removal.
- **It does not replace `conversations.cat.chitchat.json`**, even though that is the way to drop a
  single starter. Adopting 70 KB of shipped content to delete one answer is a decision that deserves
  to be made deliberately, not copied from a sample.
- **It does not touch `interiority`, `conversation_catalog` or `conversation_beats`.** Emptying the
  beat catalog in particular is unusually unforgiving: `BeatContractLoader` rejects the whole reload
  and keeps the previous catalog, logging at ERROR, when the beat catalog collides structurally.
- **It changes no config.** Technique 1 is documentation, not files — a datapack cannot write your
  config, and it should not want to.
- **It adds nothing.** No lang keys, no `assets/` half, no new answers. Pack 09 is the additive half
  of this pair.
- **It is not tested in a running game.** These files are authored against the shipped schemas, the
  mod's source, and the loader bytecode in `mca-neoforge-7.7.33+1.21.1.jar` (package root
  `net.conczin.mca`), and the two replacement files were diffed programmatically against the
  originals. They have not been run in a production instance.
