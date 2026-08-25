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

### 💬 It's a conversation, not a vending machine *(new in 1.1.0)*
Asking a villager how their day went used to hand you hearts for the click. Not any more. Now they
**answer** — and you choose what to say back, and *that* is what counts.

Find someone having a rotten day and you can sit with it, ask what happened, offer to take
something off their hands — or tell them everyone has bad days and watch their face close. Apologise
afterwards and the air clears, but the hearts don't come back: being dismissive isn't undone by
saying sorry.

There's no correct button. Crack a joke on a bad day and the playful blacksmith laughs; the gloomy
one asks you not to. Offer the busy farmer a faster way to do her job and she'll either thank you
for being blunt or add it to the list of things she's apparently doing wrong — depending entirely on
who she is.

All 28 topics are converted — from the weather and what's for supper up through fears, regrets,
secrets, and the topics you only get with a spouse or your own children. Nothing pays you hearts
just for asking any more, and one config toggle puts it all back the old way.

### 🕯️ They hold you to what you said *(new in 1.1.0)*
Get a villager to tell you what really frightens them and it isn't a one-off scene. Days later
they'll bring it up themselves, ask you what you think would help, and later still tell you how it's
going. Promise you'll stand with them and they remember the promise — by name, out loud, a week
on. Say honestly that you can't promise that, and they remember *that* instead, and respect it.

Push them after they've told you no, though, and it's permanent. The topic opens warily from then
on, the warm route closes, and the only way back is an apology that doesn't pretend it never
happened.

### 🗣️ Just talk to them
Skip the menus entirely: type **`Hey Coralia! How's your day going?`** in normal chat and Coralia
answers — in chat, in her own voice, through the exact same dialogue engine as the GUI (same heart
gates, same memories, same moods, same gossip). No AI and no text generation: matching is
old-fashioned, deterministic keyword-and-phrase scoring with typo tolerance, and every line is
hand-written.

- **Call them by name** (`Hey Hanna!`, even typo'd), **look at them**, or just keep talking — your
  conversation partner sticks with you for follow-ups.
- **The village feels alive**: some villagers greet you as you pass (the peppy ones more than the
  introverted ones — and villagers who dislike you brush you off). Open the chat box and nearby villagers
  stop and turn to you; whoever you're talking with stays put, facing you, until a little while
  after the conversation ends. Shout *"How's everyone doing?"* in the square and whoever it applies
  to answers.
- **They respect your boundaries**: say *"bye"* to end it, *"stop talking"* to mute that villager
  for a while, *"never mind"* to change the subject. Insult one and they'll take it personally.
- Multi-turn depth works in chat too: get a villager to open up about their fears, then answer
  *"You could face it — I'd stand with you."*
- When a villager puts a real choice to you, the options come with their reply, numbered — answer in
  your own words, or just type `2`. While you're mid-decision an idle *"nice weather"* won't be
  mistaken for your answer.
- Everything is a config toggle (whole feature, greetings, attention, local chat, thresholds), it's
  per-player opt-out (`/conversations chat off`), and modpacks can add their own topics and synonyms
  via datapack.

### 💬 The Chat button, reimagined
MCA's existing **"Chat"** button now opens a clean **Conversations hub** instead of a random
one-liner. Pick a category and dig in — or click *"Never mind."* to step back out. (Prefer vanilla
Chat? One config toggle restores it.)

The hub is organized into six tidy categories instead of one endless list:

- **Chit-Chat** — how their day went, the food, the weather, and the season
- **Profession** — how they feel about their work
- **Village** — the place, the people, and the rumors going around
- **Events** — news and gossip going around
- **Personal** — their life story, hopes, dreams, fears, feelings, regrets, and secrets
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

### 🤝 A relationship deeper than hearts
Every villager now quietly keeps track of more than a number: how much they **trust** you, whether
they **respect** you, how **warm** they feel around you, any recent **tension** between you, and how
long you've simply **known** each other. None of it shows as a score — hearts stay MCA's one visible
measure — but all of it shows in what they're willing to say. Push too hard too early and doors stay
politely shut; be someone's rock for a week and watch what opens. Left alone, feelings drift back
toward what that villager's personality resting state is — grudges soften, and banked goodwill from
one great day doesn't last forever either. It's per-player, farming-proof, and persists with the world.

### 🎲 Conversations with real stakes
The deepest stances resolve like a tabletop check with four ways to land. A **crit** opens the
villager up further than you asked; a **success** lands as intended; a **partial** half-lands; a
**rebuff** misfires — in character, with a graceful way out, and sometimes a little lingering tension.
The outcome comes from your relationship, your hearts, their mood — and a dash of luck that is
**seeded**: closing and reopening the dialogue never re-rolls a bad answer. Come back tomorrow and
try again. First stances to use it, on the fears topic: *"You could face it. I'd stand with you."*
and *"Tell me the rest of it."*

### 🧑‍🌾 Personalized to each villager
Nobody sounds like a template:

- **Profession-aware work talk** with hand-written lines for every vanilla trade *and* the
  professions added by popular mods — MCA's own roles, More Villagers, Ars Nouveau, Chef's Delight,
  Ice and Fire, Vampirism, Werewolves. Any *other* mod's profession still gets a graceful
  self-personalizing line.
- **Food talk that respects MCA traits** — vegetarians, the lactose intolerant, and more answer
  differently.
- **Kids and teens answer in their own voice** — never with adult follow-ups.
- **All 13 MCA personalities** now get flavored overlays across the **20 most-heard topics**, each
  with two or three variants — the gloomy villager, the peppy one, and the greedy one answer the same
  question in genuinely different voices, and asking twice rarely gives you the same words back.

### 🎭 Passing moods
Something just happened, and it shows. A villager turns **grateful** or **smitten** after a gift,
**proud** or **annoyed** after a quest, **annoyed** if you hit them — and a whole village goes
**grieving** after a death or **elated** after a birth or wedding nearby. The mood colors their lines
for a while, then fades.

### 🌦️ They notice the weather, the season, and the day
Villagers remark on the sky above them — a clear day, the rain, a rolling storm — so a chat in a
thunderstorm doesn't sound like a chat in the sun. They also speak to the **time of year** and to
**festival days** — the spring bloom, midsummer, the harvest festival, midwinter. Run **Serene Seasons**
and the season tracks it exactly; without it, the season is read from the world calendar.

### 🚪 They notice who comes and goes
Beyond marriages and deaths, villagers now notice neighbours **moving into** the village and **leaving**
it for good — fresh faces and empty doorways both become news worth passing on.

### 🎁 Gifts they're grateful for
Give a villager a gift they like and they'll stay grateful for about a day. Married partners will
even thank you for the *specific item* — *"Still smiling about that poppy, if I'm honest."*

### 🗣️ Village gossip
Villagers notice **marriages, divorces, births, deaths, arrivals, and departures** happening around
them and share the news — with names — exactly once per player. Ask *"Anything happen around here
lately?"* in Events, or *"Any rumors going around?"* in Village, and see what's going around. And now
**every personality breaks the news in its own voice** — the gloomy one, the greedy one and the peppy
one report the same wedding very differently.

### 💍 Spouse & family talk
Married partners and family get their own branches — *Are you happy? Remember when we met? What
about our future? Is anything weighing on you?*

---

## Requirements

**Languages:** English and Brazilian Portuguese, both complete — menus, dialogue, every personality voice and the whole chat-mode vocabulary (2,453 translated strings).

| | |
|---|---|
| **Minecraft** | 1.20.1 |
| **Mod loader** | Forge 47.x |
| **Required** | [MCA Reborn](https://www.curseforge.com/minecraft/mc-mods/minecraft-comes-alive-reborn) `7.6` – `7.7` (built against 7.7.0-beta.2; **not** 7.7.0-beta.1, which is broken upstream) |
| **Only if MCA needs it** | [Architectury API](https://www.curseforge.com/minecraft/mc-mods/architectury-api) — required by MCA 7.6, dropped by MCA 7.7. This mod never asks for it |
| **Optional** | MCA: Quests — unlocks quest-aware conversation lines when installed |
| **Optional** | Serene Seasons — villagers track the real season when installed (calendar fallback otherwise) |

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
- Enable/disable topics, conversation moods, templated lines, weather/season/holiday lines, and village gossip independently.
- Toggle the relationship vector, dialogue checks, and four-tier outcomes separately — each falls
  back to a simpler documented behavior, all the way down to the classic pre-RPG experience.
- Turn branching conversations off entirely and converted topics go back to their old one-line
  answers, payout and all. If you'd rather the new economy were gentler or harsher, there's a global
  multiplier, separate daily caps for hearts gained and lost, and a stronger-negatives switch.
- Tune gossip scan frequency, event retention, how long gratitude lasts, each mood's duration, the calendar year length, which village events (marriage, birth, arrival, departure, …) get noticed, and the relationship system's gain/decay rates and daily anti-farming caps.

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
