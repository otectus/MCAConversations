# MCA: Conversations — sample datapacks

Ten worked examples of what a datapack can do to this mod, from adding a single new question to
taking shipped content back out again. Each folder is a complete, installable pack with a long
`README.md` explaining not just what each file says but **why it is written that way** — most of the
mistakes available here produce content that loads cleanly and behaves strangely, so the reasoning
is the part worth reading.

These are teaching material first and playable content second. They are authored against the shipped
schemas, the engine rules in [`../DATAPACK.md`](../DATAPACK.md), and the MCA Reborn jar itself
(`mca-neoforge-7.7.33+1.21.1.jar`, package root `net.conczin.mca`, read directly for the loader and
constraint behaviour). **They have not been played in a production MCA instance** — MCA does not
load under the mod's dev runtime, so nothing here has been verified in-game. Treat the JSON as
correct-by-construction and the READMEs as the authority on intent.

This is the **1.21.1 NeoForge** set. It is the same ten packs as the 1.20.1 Forge set, teaching the
same things in the same order, with two version-correct differences: every pack is **split into a
`datapack/` half and a `resourcepack/` half** (why, immediately below), and no example gates on
`confident`, `peppy` or `athletic`, because MCA on 1.21.1 no longer registers those three.

---

## A pack that adds dialogue is not only a datapack

This trips up everybody once. A conversation has two halves that Minecraft loads from two different
places:

| Half | Lives in | Loaded from | Contains |
|---|---|---|---|
| **Data** | `data/…` | `saves/<world>/datapacks/` | questions, answers, scenes, beats, intents, catalogs |
| **Assets** | `assets/…` | `resourcepacks/` | every `dialogue.*` line, every button label, slot and culture nouns |

Every villager line and every response-card label is a **client translation key**. A folder sitting
only in `datapacks/` will load its logic and then show you `dialogue.sample_sleep.rough` as literal
text on screen.

So each sample folder here contains both halves — but on 1.21.1 they cannot be the *same* folder.
Each sample is shipped pre-split, and you install the two halves into two places:

```
01-first-topic/datapack/      → saves/<your world>/datapacks/01-first-topic/
01-first-topic/resourcepack/  → resourcepacks/01-first-topic/
```

Then enable the resource pack in Options → Resource Packs, and either restart the world or run:

```
/reload
```

Shipping to other people, you have three honest options: tell them to install both folders, ship the
two halves as separate downloads, or wrap the whole thing in a small mod jar (a mod's own resources
are a single pack that serves both trees, which is how MCA: Conversations itself does it).

### Why this set is split and the 1.20.1 set is not

`pack.mcmeta` carries exactly one `pack_format`, and that one number has to be right for the role
the folder is being loaded in. On Minecraft 1.20.1 the two roles happened to share a number:

| Minecraft | resource pack format | data pack format |
|---|---|---|
| 1.20.1 | 15 | 15 |
| **1.21.1** | **34** | **48** |

Because 15 meant both things on 1.20.1, one folder holding `data/` *and* `assets/` could legally be
copied into `datapacks/` and into `resourcepacks/` and be accepted in both roles — which is exactly
what the 1.20.1 sample set tells you to do. On 1.21.1 the two numbers have diverged, so a single
`pack.mcmeta` can no longer satisfy both: whichever number you write, the other half loads with a
format-mismatch warning and, on a strict client, is refused. Hence `datapack/` (format **48**) and
`resourcepack/` (format **34**) as separate folders with separate `pack.mcmeta` files.

Nothing inside `data/` or `assets/` changed because of this — it is purely where the two
`pack.mcmeta` files live. Pack 10 ships no `assets/` at all, so it has no `resourcepack/` folder.

---

## The ten packs

| # | Pack | Namespace | What it is for |
|---|---|---|---|
| 01 | [`01-first-topic`](01-first-topic/) | `sample_sleep` | The smallest complete topic: one new question, end to end. **Start here.** |
| 02 | [`02-new-category`](02-new-category/) | `sample_pastimes` | A whole new hub category, and your own entry on MCA's menu. |
| 03 | [`03-checks-and-dispositions`](03-checks-and-dispositions/) | `sample_courage` | A checked stance that can crit, land, glance off or be rebuffed. |
| 04 | [`04-arcs-and-promises`](04-arcs-and-promises/) | `sample_ledger` | Durable state: arcs, milestones, an either/or you can't take back, and a promise the game checks. |
| 05 | [`05-living-histories`](05-living-histories/) | `sample_beekeeper` | The dynamic layer: identity, episodes, threads, scenes, slots. |
| 06 | [`06-village-culture`](06-village-culture/) | `sample_commons` | What a village keeps, what a resident makes of it, and who owes whom what. |
| 07 | [`07-world-and-compat`](07-world-and-compat/) | `sample_almanac` | Weather, seasons, gossip, and other mods — with everything degrading gracefully. |
| 08 | [`08-profession-pack`](08-profession-pack/) | `sample_chandler` | Work-talk for a trade another mod supplies. |
| 09 | [`09-modify-native`](09-modify-native/) | `sample_retune` | Five ways to change what the mod already ships, and what each costs. |
| 10 | [`10-remove-native`](10-remove-native/) | `sample_trim` | Taking content back out — safest technique first, and what is simply not possible. |

Every pack uses a distinct namespace, so all ten can be installed at once without colliding.

### Suggested reading order

**01 → 02** are the foundations: how a question is reached, how answers merge, how a category is
built. Read both before anything else, because packs 03–08 assume that vocabulary.

**03 → 04** are about consequence: how an answer's outcome stops being fixed, and how a conversation
leaves something behind that a later conversation can read.

**05 → 06** are the dynamic layer: content chosen because of who this villager is and what is
currently happening to them, rather than because of which button you pressed.

**07 → 08** are integration: reacting to the world and to other mods without depending on them.

**09 → 10** are for when you want to change the mod rather than extend it. They are last on purpose —
both rely on override semantics that the earlier packs never need, and both hand you maintenance
you did not have before.

---

## What each pack demonstrates

| Capability | Pack |
|---|---|
| Merging an answer into an existing question | 01, 02, 04, 07, 08 |
| Result ordering, and why the fallback goes last | 01 (all) |
| Action key ordering (`state → next → say`) | 01 (all) |
| Cooldown and first-seen memories | 01, 04 |
| `conversations_affection_apply` and depth budgets | 01, 03, 04 |
| Hub categories, `back` answers, `constraints` gating | 02 |
| Extending MCA's `main` menu | 02 |
| `conversations_check` — the four tiers and the canonical shape | 03 |
| `conversations_disposition` / `conversations_disposition_apply` | 03 |
| `conversations_relationship` bands instead of heart numbers | 03 |
| Interiority profiles and personality voice overlays | 03, 09 |
| `conversations_progress` arcs, milestones, exclusive groups | 04 |
| `conversations_commitment` — promises the game can observe | 04 |
| `conversations_exchange` — reading back what was decided | 04 |
| `identity_tokens`, `episode_templates`, `thread_templates` | 05 |
| `conversation_scenes`, frozen plans, fallback chains | 05, 06, 07, 09 |
| `conversations_say` with `vars` and `slots` | 05, 07 |
| `village_culture` and `conversations_culture` | 06 |
| `conversations_opinion` and `conversations_role` | 06 |
| Rumour provenance: `knowledge` / `privacy` / `share` | 06 |
| Weather, season and holiday conditions | 07 |
| `conversations_gossip` and `conversations_gossip_say` | 07 |
| `conversations_context` and the `capital.*` fields | 07 |
| Quests and Reputation compat, and graceful degradation | 07 |
| `profession_profiles` and third-party trades | 08 |
| Chat-mode intents and synonym packs | 01–08 (every pack), 09, 10 |
| Overriding a shipped file at its exact path | 09, 10 |
| Redefining a shipped id | 09, 10 |
| Config toggles as the first-choice removal tool | 10 |

---

## The five rules that cause the most trouble

Learn these before writing anything of your own. All five produce content that loads without
complaint and misbehaves at runtime.

1. **`say` must come after `next`.** The dialogue screen has one speech slot and `next` writes the
   destination question's prompt into it. A `say` authored before `next` is overwritten and the
   player never reads it. Order every result's actions as state → `next` → `say`.

2. **The last result is the fallback, not the first.** When every result of an answer scores ≤ 0,
   MCA picks the *last* one. Selection is weighted-random over positive totals, not highest-wins, so
   a "default" result written first with a small positive weight will also win a slice of every other
   case.

3. **Negative sinks are how you gate.** There is no "if". A result is killed by giving it a large
   negative contribution: `{"chance": -2000, "conversations_disabled": "checks"}`.

4. **The same file path replaces; the same file *basename* merges.** Two files called
   `conversations.cat.chitchat.json` in different namespaces have their answers concatenated. One
   file at `data/mcaconversations/dialogues/conversations.cat.chitchat.json` shipped from a
   higher-priority pack replaces the mod's copy outright. That distinction is the whole of packs 09
   and 10.

5. **Never use MCA's native `personality` condition.** It throws on an id the running MCA does not
   know, and that aborts the datapack reload and the world load with it. Use
   `conversations_personality`, which never throws and resolves the older ids forward. The roster
   has now moved twice: MCA renamed four personalities in 7.7, and on 1.21.1 it stopped registering
   `confident`, `peppy` and `athletic` altogether. Those three still *resolve* — authoring one is
   valid and the mod still ships their voices — but no freshly generated villager can be one, so a
   gate that names only them never fires on a stock install. The ids a villager can actually roll on
   1.21.1 are `friendly`, `flirty`, `playful`, `gloomy`, `sensitive`, `greedy`, `odd`, `crabby`,
   `extroverted`, `introverted`, `relaxed`, `anxious`, `peaceful` and `upbeat`; every example in
   these ten packs gates on one of those.

Two more that are less famous and just as quiet, both being cases where a **typo becomes a silently
disabled gate rather than an error**:

**Unrecognised `constraints` tokens are dropped without a word.** MCA's registry has `baby`,
`toddler`, `teen`, `adult` — and no `child`. A `!child` in a constraints string does nothing at all,
and nothing is logged. (Verified in `Constraint.class` in `mca-neoforge-7.7.33+1.21.1.jar`, where
the registry is token-for-token what the 1.20.1 Forge jar shipped.)

**An unrecognised feature id scores as *enabled*.** `isFeatureEnabled` ends in `default -> true`, so
a misspelt `{"chance": -2000, "conversations_disabled": "gossipp"}` sink never fires, and a misspelt
`conversations_enabled` gate always fires. The complete set is closed — check a spelling against it
before trusting a sink:

`topics`, `states`, `templates`, `gossip`, `quests`, `world`, `seasons`, `holidays`, `dispositions`,
`checks`, `branching`, `chat`, `townstead`, `capitals`, `capital_topics`, `capital_news`,
`capital_diplomacy`, `dynamic`, `identity`, `episodes`, `history`, `social_opinions`,
`village_culture`, `group`.

Note that `world` covers **weather only** — seasons and festivals have their own ids — and that
there is no feature id for check-tier collapsing, which is config-only.

---

## Proving it worked

Every pack's README ends with in-game steps. These are the commands they use — all op-only except
`chat status`:

| Command | What it tells you |
|---|---|
| `/reload` | Re-reads every datapack. Watch the log for parse errors. |
| `/conversations chat debug <message>` | Scores a typed message against every intent and shows why the winner won. |
| `/conversations chat debug-ask <question> <answer>` | Drives the villager you are looking at straight to one button, without playing to it. |
| `/conversations scene plan` | Why the scene you are in was chosen: every score term, and the first reason each finalist was dropped. |
| `/conversations scene candidates <topic>` | The same as a dry run, without storing a plan. |
| `/conversations profile inspect` | The nearest villager's identity tokens and the seed they came from. |
| `/conversations profile tokens` | The whole identity catalog, by family — your tokens should appear here. |
| `/conversations history inspect` | Episodes, threads, promises, claims, opinions and roles this villager holds. |
| `/conversations context snapshot` | Every context field and its current value. |
| `/conversations gossip list` | The event log the news and rumour topics read from. |

If a pack seems to do nothing, work down this list in order: does `/reload` log a parse error; does
`profile tokens` / `scene candidates` show your content loaded at all; does `debug-ask` reach the
button directly; and is the relevant feature switched on in the config
([`../CONFIG.md`](../CONFIG.md))?

---

## Where the reference material is

- [`../DATAPACK.md`](../DATAPACK.md) — the full JSON vocabulary. The authority; these samples are
  worked examples of it.
- [`../CONFIG.md`](../CONFIG.md) — every config option, and what each one's off-state does.
- `build/libs/reports/` after a `./gradlew build` — eight generated documents including
  `adjacency.md` (every node, line, button and onward page) and `scenes.md` (every scene, what gates
  it, where it routes). When you need to know what the mod already does somewhere, read those rather
  than the JSON.
