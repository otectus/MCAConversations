# MCA: Conversations

**Deeper, less repetitive villager conversations for Minecraft Comes Alive: Reborn.**

Your MCA villagers already have families, jobs, and hearts. *MCA: Conversations* gives them
something to **say** about all of it — how their day actually went, whether they like their work,
the neighbors, their fears, their regrets, and the gossip going around the village. Fewer canned
one-liners, more the sense that you're talking to someone who remembers you.

It's a lightweight add-on: no AI, no text generation, no servers phoned home. Just a lot of
hand-written dialogue wired into MCA's own conversation system, gated by your relationship and
what you've talked about before.

---

## What it does

### 💬 The Chat button, reimagined
MCA's existing **"Chat"** button now opens a clean **Conversations hub** instead of a random
one-liner. Pick a category and dig in — or click *"Never mind."* to step back out. (Prefer vanilla
Chat? One config toggle restores it.)

The hub is organized into six tidy categories instead of one endless list:

- **Chit-Chat** — how their day went, how they feel about the food
- **Profession** — how they feel about their work
- **Village** — the place and the people in it
- **Events** — news and gossip going around
- **Personal** — their life story, dreams, fears, feelings, regrets, and secrets
- **Relationships** — dedicated talk for spouses and family (hidden from strangers)

Empty categories simply don't show up.

### 🧠 They remember what you asked
Villagers keep a per-player memory of your conversations (persisted with the villager, using MCA's
own long-term memory). Ask the same thing too soon and they'll gently call it out. Come back much
later and they'll *revisit* it on their own — *"I've been chewing on what you asked me…"*

### ❤️ Conversations that earn their depth
Not everyone spills their guts to a stranger.

- **Casual topics** (their day, work, food, the neighbors) are open to anyone.
- **Personal topics** (life story, dreams, fears) open up at **25+ hearts**.
- **Intimate topics** (feelings, regrets) need **60+ hearts** *and* a prior heart-to-heart — unless
  you're married to them.
- **Secrets** only come out once they've confided in you before.

Below the bar, villagers deflect — in character, not with an error.

### 🧑‍🌾 Personalized to each villager
Nobody sounds like a template:

- **Profession-aware work talk** with hand-written lines for every vanilla trade *and* the
  professions added by popular mods — MCA's own roles, More Villagers, Ars Nouveau, Chef's Delight,
  Ice and Fire, Vampirism, Werewolves. Any *other* mod's profession still gets a graceful
  self-personalizing line.
- **Food talk that respects MCA traits** — vegetarians, the lactose intolerant, and more answer
  differently.
- **Kids and teens answer in their own voice** — never with adult follow-ups.
- **All 13 MCA personalities** get flavored line overlays, so the gloomy villager and the flirty one
  don't read from the same script.

### 🎁 Gifts they're grateful for
Give a villager a gift they like and they'll stay grateful for about a day. Married partners will
even thank you for the *specific item* — *"Still smiling about that poppy, if I'm honest."*

### 🗣️ Village gossip
Villagers notice **marriages, divorces, births, and deaths** happening around them and share the
news — with names — exactly once per player. Ask *"Anything happen around here lately?"* and see
what's going around.

### 💍 Spouse & family talk
Married partners and family get their own branches — *Are you happy? Remember when we met? What
about our future? Is anything weighing on you?*

---

## Requirements

| | |
|---|---|
| **Minecraft** | 1.20.1 |
| **Mod loader** | Forge 47.x |
| **Required** | [MCA Reborn](https://www.curseforge.com/minecraft/mc-mods/minecraft-comes-alive-reborn) `7.6.x` |
| **Required** | [Architectury API](https://www.curseforge.com/minecraft/mc-mods/architectury-api) `9.2.x` (MCA's own dependency) |
| **Optional** | MCA: Quests — unlocks quest-aware conversation lines when installed |

> This is an **add-on** — MCA Reborn must be installed for it to do anything.

---

## Optional: MCA: Quests integration

If you also run **MCA: Quests**, villagers become quest-aware in conversation: they'll mention work
that needs doing, react to quests you have active or ready to turn in, and the whole village hears
about a finished quest as fresh gossip. Everything degrades cleanly if Quests isn't installed —
*MCA: Conversations* works fully on its own.

---

## Configuration

Everything is toggleable in `config/mcaconversations-common.toml` (generated on first run):

- Turn the whole Chat replacement off and get vanilla MCA Chat back.
- Enable/disable topics, gift gratitude, templated lines, and village gossip independently.
- Tune gossip scan frequency, event retention, and how long gratitude lasts.

Disabled features degrade to graceful fallback lines rather than breaking mid-conversation.

---

## For datapack & modpack authors

Almost all of the dialogue is **data**, not code. MCA merges same-named questions across namespaces,
so packs can add topics, extend the hub, or plug into a category without touching Java. The Java side
only registers a handful of custom dialogue conditions and actions into MCA's public registries. See
the project's `DATAPACK.md` for the full JSON vocabulary.

---

## Status & license

Alpha — actively developed; feedback and bug reports welcome. Licensed **GPL-3.0-only**, matching
MCA Reborn.
