# MCA: Conversations

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Forge](https://img.shields.io/badge/Forge-47.x-orange)
![Requires](https://img.shields.io/badge/Requires-MCA%20Reborn%207.6%20%E2%80%93%207.7-blue)
![License](https://img.shields.io/badge/License-GPL--3.0-lightgrey)
![Status](https://img.shields.io/badge/Status-alpha-red)

Deeper, less repetitive villager conversations for **Minecraft Comes Alive: Reborn**.

## Features (1.0.0)

- **Chat mode (new)** — talk to villagers by just *typing*. `Hey Coralia! How's your day going?` in
  the vanilla chat box gets an answer in chat, in her voice, through the **same dialogue engine** as
  the GUI — identical heart gates, cooldowns, moods, dialogue checks, memories, and gossip. No
  AI/LLM: matching is deterministic, datapack-driven (keywords + phrases with typo tolerance,
  synonyms, negation awareness), and unit-tested. Address villagers by name, by looking at them, or
  just keep talking — your conversation partner stays "sticky" for follow-ups. Multi-turn depth
  works too: open up their fears, then answer *"You could face it — I'd stand with you."*
- **A living village (new)** — villagers *may* greet you as you pass (a personality-weighted daily
  coin flip — the peppy farmer usually says hi, the introverted librarian rarely; villagers who dislike you
  brush you off instead). Open the chat box and nearby villagers stop and turn to you expectantly;
  your conversation partner stays put, facing you, until a while after the conversation lapses —
  and still flees danger. Say *"bye"* or *"stop talking"* and they respect it, per villager.
  Shout a question in the square and the villagers it applies to answer, staggered. Optional
  radius-local chat (on by default, EXPERIMENTAL) keeps conversations neighborhood-scale.
- **A relationship deeper than hearts** — every villager quietly tracks how much they *trust*
  and *respect* you, how *warm* they feel around you, recent *tension*, and how long you've known
  each other. Hearts stay MCA's one visible number — the vector never shows and never grants
  hearts; it decides which replies open up and how they land. It drifts back toward the villager's
  personality baseline over days, is capped against farming, and is per-player.
- **Dialogue checks** — the deepest stances resolve like a CRPG check with four outcomes:
  a *crit* opens the villager further than asked, a *success* lands, a *partial* half-lands, a
  *rebuff* misfires in character (and always exits gracefully). Outcomes come from the relationship,
  hearts, mood, and a **seeded** roll — closing and re-opening the screen never re-rolls; coming
  back tomorrow might. Piloted on the fears topic: try *"You could face it. I'd stand with you."*
- **Chat, replaced** — MCA's own "Chat" button now opens the Conversations hub: how their day
  *actually* went, whether they like their work, food, the neighbors, their life story, dreams,
  fears, feelings, regrets, and secrets. (Reachable via `hubEntryMode`; see CONFIG.md.)
- **Category menu** — the hub opens on six clean categories (Chit-Chat, Profession, Village,
  Events, Personal, Relationships) instead of one long list; pick one to see its starters, with
  "Something else." to step back. Categories with nothing to offer are hidden — Relationships
  only appears for spouses and family.
- **Personalized per villager** — work talk is profession-aware (hand-written lines for all
  vanilla trades plus every profession mod in the pack: MCA, More Villagers, Ars Nouveau, Chef's
  Delight, Ice and Fire, Vampirism, Werewolves — and a self-personalizing generic line for any
  other mod's professions); food talk respects MCA traits (vegetarian, lactose intolerance, ...);
  children and teens answer in their own voice. **Every personality has a flavored overlay** — all 16 of MCA 7.7's, plus MCA 7.6's
  `athletic` and the four 7.6 spellings (`witty`/`shy`/`lazy`/`grumpy`) kept as aliases of their
  7.7 successors, so a villager keeps its voice across an MCA upgrade. 21 overlay namespaces in all. Each covers the
  high-traffic lines (greetings, check-ins, day/work/village, personal openers, deflects, gossip and
  the whole chat-mode vocabulary), each with 2–3 variants — a crabby villager and a peppy one
  answer the same question in visibly different words

## Requirements

Minecraft 1.20.1 · Forge 47.x · requires **MCA Reborn `[7.6,8)`**.

Built and tested against **MCA 7.7.0-beta.2**; verified to still start and run on **MCA 7.6.20**.

> **Architectury is no longer declared as a dependency of this mod.** MCA 7.6 requires it and asks
> for it itself; MCA 7.7 dropped it. Conversations has never referenced Architectury, and declaring
> it mandatory only blocked people upgrading to 7.7 who had removed it.

> **MCA 7.7.0-beta.1 does not work — with or without this mod.** That build ships a truncated
> `forge-mca.refmap.json`, so MCA's own `MixinLivingEntity` fails to apply in a production runtime.
> Reproduced with MCA alone. Use **7.7.0-beta.2** or newer.

### Languages

**English (`en_us`)** and **Brazilian Portuguese (`pt_br`)** — both complete: UI strings, the full
base dialogue pool, every personality overlay, the age voices and the whole chat-mode vocabulary
(2,453 translated strings). MCA gates per-personality dialogue to `en_us`/`ru_ru`; a narrow
client-only hook widens that gate to the locales this mod ships complete overlays for, while
preserving MCA's voice-pack and online-TTS restrictions untouched.

Optional: **MCA: Quests** (quest-aware lines) and **Serene Seasons** (real seasons; calendar fallback
otherwise) — both soft dependencies; the mod works fully without them.

## How it works

MCA's dialogue system loads datapack JSON from any namespace and merges same-named questions, so
most of Conversations is data: `data/mcaconversations/dialogues/*.json` adds new questions and extends MCA's
`main`/`greet`. The Java side registers custom dialogue conditions/actions
(`conversations_gossip`, `conversations_disposition`, `conversations_check`, `conversations_say`, ...) into
MCA's public registries — no runtime patching of MCA except three small soft-fail mixins (the Chat→hub
redirect, a gift observer, and chat mode's dialogue-packet-to-chat redirect). Chat mode's matcher is a
second *frontend* to the same engine: free text resolves to the exact `(question, answer)` a GUI click
would send, so parity is structural, not re-implemented; its intents live in
`data/<any-namespace>/chat_intents/*.json` and are fully datapack-extensible (including synonym packs).
The only client-side code is a one-byte "chat box open" ping so villagers can turn toward a typing
player. The relationship vector lives in its own versioned world save data and
**never touches hearts**: MCA's hearts remain the only authoritative, visible relationship number,
and every heart change still flows through MCA's own dialogue actions. See
[DATAPACK.md](DATAPACK.md) for the full JSON vocabulary (datapack authors can build on it, including
their own checked stances) and [CONFIG.md](CONFIG.md) for configuration.

## Status

Alpha. Pure logic (gossip log, diffing, templates, content lint) is unit-tested; **MCA-touching
behavior can only be verified in a production-style instance** — MCA Reborn does not load under a
ForgeGradle dev runtime (its bundled mixins only resolve against SRG names), so `runClient` is not
a valid test of MCA integration. See the in-world checklist in [CHANGELOG.md](CHANGELOG.md).

## License

GPL-3.0-only, matching MCA Reborn, whose internals this mod links against.
