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

## New in 1.5.1

### 🪟 The conversation card is painted with Minecraft's own menus

The panel and its recessed list body are the options-screen dirt; the number badges and page buttons
are cut from the vanilla button strip. Rows are drawn as vanilla list entries — shadowed white text
at rest, vanilla's two-tone frame when focused — and an answer too long to fit gets a vanilla
scrollbar. A resource pack that reskins vanilla menus reskins this card with it, so there is no
second palette to keep in step with the rest of the game.

This replaces 1.5.0's three appearance options (`enhancedConversationVisuals`, `visualStyle` and
`panelOpacity`). Forge quietly drops those keys from an existing client config on first load; nothing
is required of you. There is one look now, and it is the game's. The speaker portrait, the optional
line reveal and the motion settings are untouched.

---

## New in 1.5.0

### 👤 Talk to someone, not to a menu

The villager you are speaking to sits framed in the conversation card, so the line you are reading
has someone attached to it. If you like your dialogue to arrive as speech rather than all at once,
there is an optional reveal for the villager's line. Both are client settings you own; the portrait
steps aside by itself on narrow or short screens rather than eating the reading width.

### 🫱 They bring things up themselves

Walk past someone who is owed something and they say so. A promise that has come due — named, not
hinted at — a rupture between you that was never acknowledged, a thread you left open and that is
ready to pick up, or their own situation having changed since the last time *you* spoke to them.
It replaces the hello rather than adding to it: one line, no screen opens, nothing waits on you. The
player who was here yesterday and the one who has been away a season are each told what is news to
them.

It stays bounded — one unprompted opening per villager per player per day, nothing at all from
somebody asleep, fighting, panicking or busy with another player, and *"stop talking"* honoured
before anything else is considered. One config value switches it off entirely.

### 🤞 Promises the running game watches

Promise a villager some iron and the promise settles when the gift actually arrives, on MCA's own
accepted-gift path. Everything else is judged the next time the two of you meet, not at midnight on
a day you were logged out — and turning up a little late is late, not broken, for three days. A
promise this install cannot observe is left outstanding rather than guessed at either way.

### 🧠 They remember what you decided, not just that you spoke

The stance behind the reply you chose is now recorded per subject, for up to sixteen subjects per
villager. A later line can say *you told me to save the ink* instead of *as I was saying*. Change
your mind and they remember the mind you ended up with.

### 🔁 They stop repeating themselves

Almost every villager line here is a small pool of alternatives, and the game used to draw from it at
random with no memory — so three sentences did not read as three. The server now picks from the ones
it has not used yet for this villager, this player and this line. Seeded, so closing and reopening
the screen is not a reroll, and everyone who overhears a line hears the same one.

### 🏘️ A village that happens to you

A villager can be carrying the village itself — a wedding, a death, somebody arriving or leaving — as
a running thing that starts, goes on across visits, and eventually settles. It opens only when the
gossip log says this villager really has news you have not heard, and the line names the actual
event. Ask what it changed for them and they answer; press for what they mean and they say the harder
version, or tell them to take their time and they ease off.

Conversations can also run a third turn now, so a repair does not end the moment you apologise.

---

## What it does

### 💬 It's a conversation, not a vending machine
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

Every topic in the hub works this way, from the weather and what's for supper up through fears,
regrets, secrets, and the topics you only get with a spouse or your own children. Nothing pays you
hearts just for asking, and one config toggle puts it all back the old way.

### 🕯️ They hold you to what you said
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
Chat? One config setting restores it, or keeps both side by side.)

The hub is organized into six tidy categories instead of one endless list:

- **Chit-Chat** — how their day went, the food, the weather, the season, and how their days run
- **Profession** — how they feel about their work, and what they could use a hand with
- **Village** — the place, the people, the rumors, your standing, and the neighbours
- **Events** — news, what they've noticed, and what the two of you have been through
- **Personal** — their life story, hopes, dreams, fears, feelings, regrets, secrets, interests,
  values, where they came from, and what they make of you
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
Every villager quietly keeps track of more than a number: how much they **trust** you, whether
they **respect** you, how **warm** they feel around you, any recent **tension** between you, and how
long you've simply **known** each other. None of it shows as a score — hearts stay MCA's one visible
measure — but all of it shows in what they're willing to say. Push too hard too early and doors stay
politely shut; be someone's rock for a week and watch what opens. Left alone, feelings drift back
toward that villager's personality resting state — grudges soften, and banked goodwill from one
great day doesn't last forever either. It's per-player, farming-proof, and persists with the world.

### 🎲 Conversations with real stakes
The deepest stances resolve like a tabletop check with four ways to land. A **crit** opens the
villager up further than you asked; a **success** lands as intended; a **partial** half-lands; a
**rebuff** misfires — in character, with a graceful way out, and sometimes a little lingering tension.
The outcome comes from your relationship, your hearts, their mood — and a dash of luck that is
**seeded**: closing and reopening the dialogue never re-rolls a bad answer. Come back tomorrow and
try again.

### 🧑‍🌾 Personalized to each villager
Nobody sounds like a template:

- **Two farmers are not one farmer.** Every villager carries a handful of stable anchors — what they
  are interested in, what they value, a comfort, an aversion, and how they work, socialise and open
  up. They're drawn once from the world seed and the villager's own id and never rerolled, so two
  players meet the same person.
- **Situations that are actually going on.** A librarian has *a specific damaged book*: hers, with a
  specific kind of damage, and it stays hers until it's dealt with. Come back and it has dried, or it
  hasn't. Two librarians in one village worry about two different volumes.
- **Profession-aware work talk** with hand-written packs for every vanilla trade *and* the
  professions added by popular mods — MCA's own roles, More Villagers, Ars Nouveau, Chef's Delight,
  Ice and Fire, Vampirism, Werewolves: 37 packs in all. Any *other* mod's profession still gets a
  graceful self-personalizing line.
- **Food talk that respects MCA traits** — vegetarians, the lactose intolerant, and more answer
  differently.
- **Kids and teens answer in their own voice** — never with adult follow-ups.
- **Every MCA personality has its own voice.** All 16 of MCA 7.7's, plus the 7.6 spellings kept as
  aliases so a villager keeps its voice across an MCA upgrade — 21 personality overlays in all, each
  with two or three variants of the lines you hear most. The gloomy villager, the peppy one and the
  greedy one answer the same question in genuinely different words.

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

### 🗣️ Village gossip that travels
Villagers notice **marriages, divorces, births, deaths, arrivals, and departures** happening around
them and share the news — with names — exactly once per player. Ask *"Anything happen around here
lately?"* in Events, or *"Any rumors going around?"* in Village, and see what's going around. Every
personality breaks the news in its own voice.

News also passes from one villager to the next. It loses confidence with every telling and never
gains detail, and something you said about yourself doesn't travel unless you made it public. Because
the event keeps its identity all the way down the chain, setting the record straight later corrects
the actual event rather than one villager's copy of it.

### 💍 Spouse & family talk
Married partners and family get their own branches — *Are you happy? Remember when we met? What
about our future? Is anything weighing on you?*

---

## Requirements

| | |
|---|---|
| **Minecraft** | 1.20.1 |
| **Mod loader** | Forge 47.x |
| **Required** | [MCA Reborn](https://www.curseforge.com/minecraft/mc-mods/minecraft-comes-alive-reborn) `[7.6,8)` — built against 7.7.0-beta.2, verified on 7.6.20. **Not** 7.7.0-beta.1, which is broken upstream with or without this mod |
| **Only if MCA needs it** | [Architectury API](https://www.curseforge.com/minecraft/mc-mods/architectury-api) — required by MCA 7.6, dropped by MCA 7.7. This mod never asks for it |
| **Optional** | **MCA: Quests** `[0.8,)` — quest-aware conversation lines |
| **Optional** | **MCA: Reputation** `[0.2,)` — public standing, and villagers telling each other what you've done |
| **Optional** | **Townstead** `[0.7.5,0.8)` — its schedules, buildings and village culture, inside its own RPG screen |
| **Optional** | **Serene Seasons** — villagers track the real season (calendar fallback otherwise) |

**Languages:** English and Brazilian Portuguese, both complete — menus, dialogue, every personality
voice and the whole chat-mode vocabulary. 36,397 key/value entries per locale, across 23 namespaces.

> This is an **add-on** — MCA Reborn must be installed for it to do anything. Every optional mod above
> is a soft dependency: install any, all, or none of them.

---

## Configuration

Three files, generated on first run:

| File | Scope | Holds |
|---|---|---|
| `config/mcaconversations-common.toml` | per installation | feature switches and debug flags |
| `<world>/serverconfig/mcaconversations-server.toml` | per world, synchronised to every client | gameplay values: hearing distance, the heart economy, disposition movement and decay, how often a villager may speak first, history caps |
| `config/mcaconversations-client.toml` | per player, client only | presentation: the portrait, the line reveal, motion, sound, numbered responses |

You can turn the whole Chat replacement off and get vanilla MCA Chat back, or keep both. Topics,
moods, templated lines, weather and season lines, and village gossip switch on and off
independently, as do the relationship vector, dialogue checks and four-tier outcomes — each falls
back to a simpler documented behavior, all the way down to the classic pre-RPG experience. Turn
branching off entirely and topics go back to their old one-line answers, payout and all. If you'd
rather the heart economy were gentler or harsher, there's a global multiplier, separate daily caps
for hearts gained and lost, and a stronger-negatives switch.

Disabled features degrade to graceful fallback lines rather than breaking mid-conversation. Full
details are in the project's `CONFIG.md`.

> **Upgrading from before 1.5.0?** The gameplay values moved out of the common file into the new
> per-world server file. Anything you customised needs copying across; anything you didn't keeps the
> same default.

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
