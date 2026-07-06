# MCA: Real Talk

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Forge](https://img.shields.io/badge/Forge-47.x-orange)
![Requires](https://img.shields.io/badge/Requires-MCA%20Reborn%207.6.x-blue)
![License](https://img.shields.io/badge/License-GPL--3.0-lightgrey)
![Status](https://img.shields.io/badge/Status-alpha-red)

Deeper, less repetitive villager conversations for **Minecraft Comes Alive: Reborn**.

## Features (0.1.0)

- **Real Talk menu** — a new "Real talk..." entry in MCA's villager Talk menu opens a hub of
  personal conversation topics: how their day *actually* went, whether they like their work, their
  life story, dreams, fears, feelings, and regrets.
- **Heart-gated depth** — casual topics are open to anyone; personal topics (life, dreams, fears)
  need 25+ hearts; intimate topics (feelings, regrets) need 60+ hearts *and* a prior heart-to-heart
  — unless you're married to them. Below the gate, villagers deflect in character.
- **Conversation memory** — villagers remember (per player, persisted in entity NBT via MCA's own
  long-term memory) what you've asked. Ask again too soon and they'll call it out; come back much
  later and they'll *revisit* the topic ("I've been chewing on what you asked me...").
- **Gift gratitude** — an accepted gift leaves the villager grateful for a day; married partners
  will thank you for the specific item ("Still smiling about that poppy, if I'm honest").
- **Personalized lines** — templated dialogue can reference the villager's name, spouse, home
  village, your last gift, and the time of day.
- **Village gossip** — villagers notice marriages, divorces, births, and deaths in their village
  and share the news (with names) exactly once per listener. Ask "Anything happen around here
  lately?" in the Real Talk menu.
- **Spouse & family talk** — dedicated branches for married partners (Are you happy? Remember when
  we met? What about our future? Is anything weighing on you?) and family members.
- **Anti-repetition polish** — dozens of new line variants merged into MCA's own most-heard
  dialogue pools (main menu prompts, greetings, chat, stories, handshakes), plus flavored overrides
  for gloomy and flirty personalities.

## Requirements

Minecraft 1.20.1 · Forge 47.x · requires **MCA Reborn `[7.6,8)`** + **Architectury API `[9.2,10)`**.

## How it works

MCA's dialogue system loads datapack JSON from any namespace and merges same-named questions, so
most of Real Talk is data: `data/mcarealtalk/dialogues/*.json` adds new questions and extends MCA's
`main`/`greet`. The Java side registers custom dialogue conditions/actions
(`realtalk_gossip`, `realtalk_say`, `realtalk_record`, ...) into MCA's public registries — no
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
