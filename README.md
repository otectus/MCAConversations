# MCA: Conversations

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Forge](https://img.shields.io/badge/Forge-47.x-orange)
![Requires](https://img.shields.io/badge/Requires-MCA%20Reborn%207.6%20%E2%80%93%207.7-blue)
![License](https://img.shields.io/badge/License-GPL--3.0-lightgrey)
![Status](https://img.shields.io/badge/Status-alpha-red)

Deeper, less repetitive villager conversations for **Minecraft Comes Alive: Reborn**.

## Features (1.4.3)

- **Conversations finally have room to breathe** — the base MCA interaction screen now uses a
  responsive choice card instead of the fixed 170-pixel popup. Questions and answers form a clear
  vertical hierarchy, long responses wrap, each full row is clickable, and menus page at nine
  responses rather than overlapping the villager or falling off the screen.
- **Use the input that is comfortable** — click a response, press its top-row or Numpad number, move
  with Up/Down and confirm with Enter or Space, or use Home/End and Page Up/Page Down on larger menus.
  Focus has a visible border, scrolling belongs to the open conversation instead of changing the
  hotbar, and the first input locks the offer so a double-click or held key cannot apply it twice.
- **Chat choices are complete and readable** — quick replies are one per line and no longer stop at
  five; the bundled six- and eight-response menus show every response. Bare multi-digit replies such
  as `10` remain supported, while ordinary sentences containing numbers remain ordinary speech.
  With an empty chat box open, `1`–`9` can select directly without broadcasting the digit.
- **The number is presentation; the offer is authority** — protocol 2 synchronizes a revisioned,
  bounded projection of MCA's exact constraint-filtered answer order. The client sends only a
  revision and absolute index; the server re-resolves the answer, villager, distance, interaction,
  expiry, and constraints, then consumes it before running MCA's existing answer path.
- **Townstead keeps owning its RPG screen** — when Townstead is installed, its existing wrapped,
  scrollable hub/submenu/Back model receives visible number badges and digit activation. Its camera,
  typewriter, mouse, arrows, Enter behavior, and native selection routine remain intact.

## Features (1.4.1)

> **Since 1.4.1, a villager is somebody in particular.**

- **Two farmers stop being one farmer (new)** — every villager now carries a small set of stable
  anchors: two things they are interested in, two things they value, a comfort, an aversion, and a
  way of working, of being sociable, and of opening up. They are generated once from the world seed
  and the villager's own id, and never rerolled — not on a restart, not when they move, not when a
  different player talks to them. Two players meet the same person.
- **Situations that are actually going on (new)** — a librarian has *a specific damaged book*: hers,
  with a specific kind of damage, and it stays hers until it is dealt with. Ask about her work and
  she tells you what is wrong with it. Come back and it has dried, or it has not. Two librarians in
  one village are worrying about two different volumes.
- **Promises the game can check (new)** — "I can bring you wool" is only ever offered when something
  in the running game can actually watch for wool arriving. If nothing can, the button says what it
  honestly means instead. No villager will ever thank you for something you did not do, or accuse
  you of breaking a promise nothing was watching.
- **She does not always agree with you (new)** — advise the librarian to save the ink and let the
  pages warp, and whether she takes it depends on what she values. When she pushes back she names
  the reason, and you can hold your ground or hand the judgement back to the person who actually
  knows books.
- **You cannot shop for a different subject (new)** — closing the screen and reopening it gets you
  the same conversation. What she raises is decided once, from the world and your shared history,
  and reopening a window is not a reroll.
- **Seven more things to ask about (new)** — what she enjoys, what she will not do, how her days
  run, where she came from, which corner of the village is hers, what she makes of you, and what the
  two of you have been through. That last one is adult-only and needs time on the clock, because
  there has to be a history before there is a topic.
- **A village that is somewhere in particular (new)** — every village draws six things once and
  keeps them: a tradition, a public value, a shared work worry, a landmark, a festival, and an
  argument currently going on. Residents do not all agree about them — whether somebody is glad of
  the frost supper depends on who they already are — and two players walking in from opposite
  directions find the same place.
- **News that travels, and can be corrected (new)** — a thing one villager knows can reach the
  villager next to them. It loses confidence with every telling and never gains detail, a confidence
  does not become ordinary by being repeated, and what you said about yourself never travels unless
  you made it public. Because the event keeps its identity through the whole chain, setting the
  record straight later corrects the actual event rather than one villager's copy of it.
- **Villagers who know when not to talk to you (new)** — what a villager may start on its own is
  budgeted and gated: at most one greeting per villager per day, nothing at all from somebody asleep,
  fighting, panicking or busy with another player, and a "stop talking" that is honoured before
  anything else is even considered. Greetings are the whole of what a villager currently opens
  unprompted; the budget and the gate for fuller unsolicited conversations are built and idle, and
  the content that would use them is 1.5.0 work.
- **Somebody else can join in (new, off by default)** — in chat mode, a second villager may
  corroborate something public, differ about a preference, add a detail only their trade would know,
  remember a family event differently, or tell the first one that it is not theirs to tell. Three
  speakers, fixed turn order, and every line has to answer the one before it.

## Features (1.1.0)

> Asking a question is not what earns affection — answering one is.

- **Your reply is what shapes the relationship** — asking a villager how their day went used
  to hand you hearts for the click. It doesn't any more. Now they *answer*, and **you** choose what
  to say back, and that is what lands. Sit with someone's bad day and offer to take something off
  their hands, and you've earned something. Tell them everyone has bad days and then double down
  when they bristle, and you've lost it — and apologising settles the air without buying the hearts
  back, because a slight isn't undone by saying sorry. **All 28 catalogued topics are converted** —
  from the weather and what's for supper up through fears, regrets, secrets, and the spouse and
  family topics — and no topic pays hearts for being clicked any more.
- **Conversations that remember (new)** — tell a villager you'll stand with them and they hold you
  to it a week later, by name. Get them to crack all the way open about what frightens them and
  they answer differently ever after, because they know you remember. Press them after they've said
  no and that is permanent: the topic opens warily from then on, and the only way back is an honest
  apology that doesn't erase it.
- **The same conversation by typing (new)** — every one of those choices is reachable in chat, in
  your own words. When a villager puts a decision to you, the options come with it, numbered, so you
  can answer `2` if you'd rather pick than phrase. While you're mid-decision, an idle "how's the
  weather" can't be mistaken for your answer.

## Features (1.0.0)

- **Chat mode** — talk to villagers by just *typing*. `Hey Coralia! How's your day going?` in
  the vanilla chat box gets an answer in chat, in her voice, through the **same dialogue engine** as
  the GUI — identical heart gates, cooldowns, moods, dialogue checks, memories, and gossip. No
  AI/LLM: matching is deterministic, datapack-driven (keywords + phrases with typo tolerance,
  synonyms, negation awareness), and unit-tested. Address villagers by name, by looking at them, or
  just keep talking — your conversation partner stays "sticky" for follow-ups. Multi-turn depth
  works too: open up their fears, then answer *"You could face it — I'd stand with you."*
- **A living village** — villagers *may* greet you as you pass (a personality-weighted daily
  coin flip — the peppy farmer usually says hi, the introverted librarian rarely; villagers who dislike you
  brush you off instead). Open the chat box and nearby villagers stop and turn to you expectantly;
  your conversation partner stays put, facing you, until a while after the conversation lapses —
  and still flees danger. Say *"bye"* or *"stop talking"* and they respect it, per villager.
  Shout a question in the square and the villagers it applies to answer, staggered. Optional
  radius-local chat (`chatModeLocalChat`, off by default, EXPERIMENTAL) keeps conversations
  neighborhood-scale.
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
  high-traffic lines (greetings, check-ins, day/work/village, the topic openers, deflects, gossip and
  the personality-voiced chat-mode deflections), each with 2–3 variants — a crabby villager and a
  peppy one answer the same question in visibly different words. **New in 1.2.0:** the overlays reach
  the first *reply* as well as the opener, so accepting sympathy, accepting help, being promised
  support and being asked again days later all sound like the villager saying them — not like one
  narrator speaking for everyone after the first sentence

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
(36,255 key/value entries per locale across 23 namespaces, of which 12,961 are the base
`mca_dialogue` pool). MCA gates per-personality dialogue to `en_us`/`ru_ru`; a narrow
client-only hook widens that gate to the locales this mod ships complete overlays for, while
preserving MCA's voice-pack and online-TTS restrictions untouched.

Optional: **MCA: Quests** (quest-aware lines), **MCA: Reputation** (public standing, and villagers
telling each other what you have done), **Townstead** `[0.7.5,0.8)` (its schedules, buildings and
village culture) and **Serene Seasons** (real seasons; calendar fallback otherwise) — all soft
dependencies; the mod works fully without any of them.

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
