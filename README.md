# MCA: Conversations

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Forge](https://img.shields.io/badge/Forge-47.x-orange)
![Requires](https://img.shields.io/badge/Requires-MCA%20Reborn%207.6.x-blue)
![License](https://img.shields.io/badge/License-GPL--3.0-lightgrey)
![Status](https://img.shields.io/badge/Status-alpha-red)

Deeper, less repetitive villager conversations for **Minecraft Comes Alive: Reborn**.

## Features (0.6.0)

- **Chat, replaced** — MCA's own "Chat" button now opens the Conversations hub: how their day
  *actually* went, whether they like their work, food, the neighbors, their life story, dreams,
  fears, feelings, regrets, and secrets. (Toggleable via `replaceChatWithConversations`.)
- **Category menu** — the hub opens on six clean categories (Chit-Chat, Profession, Village,
  Events, Personal, Relationships) instead of one long list; pick one to see its starters, with
  "Something else." to step back. Categories with nothing to offer are hidden — Relationships
  only appears for spouses and family.
- **Personalized per villager** — work talk is profession-aware (hand-written lines for all
  vanilla trades plus every profession mod in the pack: MCA, More Villagers, Ars Nouveau, Chef's
  Delight, Ice and Fire, Vampirism, Werewolves — and a self-personalizing generic line for any
  other mod's professions); food talk respects MCA traits (vegetarian, lactose intolerance, ...);
  children and teens answer in their own voice. **All 13 personalities now have flavored overlays
  across the 20 most-heard topics** (greeting, check-in, day, work, village, neighbors, food, the
  personal openers, deflects, and more), each with 2–3 variants — a grumpy villager and a peppy one
  answer the same question in genuinely different voices, and asking twice rarely repeats verbatim.
- **Heart-gated depth** — casual topics are open to anyone; personal topics (life, dreams, fears)
  need 25+ hearts; intimate topics (feelings, regrets) need 60+ hearts *and* a prior heart-to-heart
  — unless you're married to them. Secrets require having confided something first. Below the
  gate, villagers deflect in character.
- **Conversation memory** — villagers remember (per player, persisted in entity NBT via MCA's own
  long-term memory) what you've asked. Ask again too soon and they'll call it out; come back much
  later and they'll *revisit* the topic ("I've been chewing on what you asked me...").
- **Conversation moods** — an event leaves a villager in a passing mood that colors their dialogue:
  *grateful* or *smitten* after a gift, *proud* or *annoyed* after a quest, *annoyed* if you strike
  them, and village-wide *grieving* or *elated* after a death, birth, or marriage nearby.
- **Weather, season & holiday lines** — villagers remark on the current sky (clear, rain, storm),
  the time of year (spring/summer/autumn/winter), and calendar festivals (spring bloom, midsummer,
  harvest festival, midwinter). Seasons read from **Serene Seasons** when installed, else from the
  world calendar; the `weather`/`season`/`holiday` template variables drop any of it into a line.
- **Gift gratitude** — an accepted gift leaves the villager grateful for a day; married partners
  will thank you for the specific item ("Still smiling about that poppy, if I'm honest").
- **Personalized lines** — templated dialogue can reference the villager's name, spouse, home
  village, your last gift, the time of day, and the season.
- **Village gossip** — villagers notice marriages, divorces, births, deaths, and now **arrivals and
  departures** (neighbors moving in or leaving) in their village, and share the news (with names)
  exactly once per listener — **each in their own personality's voice**. Ask "Anything happen around
  here lately?" in Events or "Any rumors going around?" in Village.
- **Spouse & family talk** — dedicated branches for married partners (Are you happy? Remember when
  we met? What about our future? Is anything weighing on you?) and family members.
- **Anti-repetition polish** — dozens of new line variants merged into MCA's own most-heard
  dialogue pools (main menu prompts, greetings, chat, stories, handshakes), on top of the full
  per-personality overlay coverage above.

## Requirements

Minecraft 1.20.1 · Forge 47.x · requires **MCA Reborn `[7.6,8)`** + **Architectury API `[9.2,10)`**.
Optional: **MCA: Quests** (quest-aware lines) and **Serene Seasons** (real seasons; calendar fallback
otherwise) — both are soft dependencies and the mod works fully without them.

## How it works

MCA's dialogue system loads datapack JSON from any namespace and merges same-named questions, so
most of Conversations is data: `data/mcaconversations/dialogues/*.json` adds new questions and extends MCA's
`main`/`greet`. The Java side registers custom dialogue conditions/actions
(`conversations_gossip`, `conversations_say`, `conversations_record`, ...) into MCA's public registries — no
runtime patching of MCA except one small server-side mixin that observes accepted gifts. See
[DATAPACK.md](DATAPACK.md) for the full JSON vocabulary (datapack authors can build on it) and
[CONFIG.md](CONFIG.md) for configuration.

## Status

Alpha. Pure logic (gossip log, diffing, templates, content lint) is unit-tested; **MCA-touching
behavior can only be verified in a production-style instance** — MCA Reborn does not load under a
ForgeGradle dev runtime (its bundled mixins only resolve against SRG names), so `runClient` is not
a valid test of MCA integration. See the in-world checklist in [CHANGELOG.md](CHANGELOG.md).

## License

GPL-3.0-only, matching MCA Reborn, whose internals this mod links against.
