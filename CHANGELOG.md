# Changelog

All notable changes to this project will be documented in this file. Format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/); versions follow SemVer.

Compatibility: Minecraft 1.20.1 · Forge 47.x · requires MCA Reborn `[7.6,8)` + Architectury API `[9.2,10)`.

## [0.4.0] - 2026-07-07

### Added
- **Optional MCA: Quests integration** (only active when the `mcaquests` mod is installed; Conversations
  still loads and works fully standalone). All Quests-touching code sits behind a new
  `compat.QuestsBridge` classloading gate — the exact sibling of the MCA gate — so an MCA-only install
  never loads a `mcaquests` class. Config toggle: `features.enableQuests` (and the
  `conversations_enabled: "quests"` dialogue feature flag).
  - **Conversational awareness.** Four new dialogue conditions — `conversations_quest_available`,
    `conversations_quest_active`, `conversations_quest_ready`, `conversations_quest_completed` (value
    `{ "scope": "this"|"any", "min": N }`) — let villagers react to your quest state. They score 0 when
    Quests is absent.
  - **Drive quests from conversation.** New `conversations_quest_open` action (`{ "mode": "menu" }` or
    `{ "mode": "accept", "quest": "ns:path" }`) plus an "Anything you need doing?" answer on the
    Profession page that opens the villager's Quests menu when they have an offer (and says so gracefully
    when they don't). The Quests mod's own button stays the primary quest UI.
  - **Quests ripple through the village.** Completing a quest writes a permanent
    `mcaconversations.quest.done.*` memory on the giver and seeds a new `QUEST` gossip event other villagers
    tell; failing one writes `mcaconversations.quest.failed.*`.
  - **Personality-voiced quest lines.** A resolver registered with Quests renders quest offer/accept/
    in-progress/ready/complete/failed lines in the villager's Conversations voice (base pool
    `dialogue.conversations.quest.*`; per-personality overlays can be added later — falls back to base, and to
    Quests' own static text when Conversations is off).
  - **Conversations-based quest content.** Registers a `mcaconversations:talk_about` objective (completes when the
    player has the matching Conversations conversation) and a `mcaconversations:unlock_topic` reward (writes a Real
    Talk unlock memory) into the Quests add-on API.
- New content lints pin the four quest condition keys, the `conversations_quest_open` action, the `quests`
  feature, and the `conversations_quest_*` object args; new unit tests cover `QuestsBridge`, the quest parsers,
  the quest memory ids, and the `QUEST` gossip round-trip.

### Notes
- MCA: Quests-side additions ship in that repo (generic add-on seams: `api.QuestDialogueHooks` /
  `QuestDialogueResolver`, `api.ExternalSignalObjective`, `QuestManager.notifyExternalObjective` /
  `eligibleOffers(player, villager)`). Conversations's optional dependency degrades gracefully against a Quests
  build that lacks them.
- Not yet verified in a production instance (MCA + Quests don't load under dev `runClient`); see the
  in-world checklist below.

## [0.3.0] - 2026-07-06

### Changed
- **The hub is now a category menu.** Opening Conversations shows six category buttons — Chit-Chat
  (day, food), Profession (work), Village (village, people), Events (news), Personal (life,
  dreams, fears, feelings, regrets, secret), Relationships (us, family) — instead of the flat
  15-starter list. Each category is its own dialogue question (`conversations.cat.<id>`); starters
  moved into them **verbatim** (conditions, heart deltas, cooldown memory ids, follow-up routing
  all byte-identical — in-flight cooldowns in existing worlds are honored), with only their
  return hop retargeted to the category page. Every page has a "Something else." back answer;
  the hub keeps "Never mind." to exit.
- **Empty categories are hidden**: the Relationships button carries `constraints: "family"`
  (MCA's `family` includes the spouse), so strangers never see it. The other categories are
  never empty — their gating is result-level deflection, exactly as before.
- Third-party answers merged into question `conversations` still work: they surface on the hub after
  the category buttons (uncategorized fallback). Packs can target `conversations.cat.<id>` to join a
  category. See DATAPACK.md's new "The category hub" section.

### Added
- Category lang keys: hub button labels (`dialogue.conversations.<id>`), page headers
  (`dialogue.conversations.cat.<id>`), and per-page back labels; starter button labels moved to
  `dialogue.conversations.cat.<id>.<starter>` with their old text.
- Three content lints: hub answers must stay side-effect-free navigation hops, every
  `conversations.*` question must be reachable from the hub, and answer label keys may not collide
  with question header keys (the pre-0.3.0 hub relied on exactly that double duty).

## [0.2.1] - 2026-07-06

### Fixed
- **Raw translation key shown as the hub header when entering via Chat**
  (`#Gmale.#EPEPPY.#TTEEN.dialogue.chat`): MCA's `next` action builds the header prompt from the
  raw next string, so the Chat→Conversations redirect displays key `dialogue.chat` — which no lang file
  provided (MCA never shows it; vanilla `chat` is an auto question). Added a `dialogue.chat`
  entry-prompt pool (5 variants) plus a personality-flavored entry line in all 13 overlays, and
  lint coverage so the key set can't regress.

## [0.2.0] - 2026-07-06

### Changed
- **MCA's "Chat" button now opens the Conversations hub** (the separate "Conversations..." button is
  gone). Implemented as a soft-fail mixin on MCA's single dialogue routing point
  (`Dialogues.getQuestion`): only the exact `chat` hop is redirected; `chat.topic`/`chat.fail`,
  root/first-meeting, hire, rumors, and story flows are untouched (verified: MCA's `main.json` is
  the only referrer of `next: "chat"`). Config `replaceChatWithConversations` (default true); when off,
  Chat behaves vanilla and the hub is unreachable. MCA's old casual chat line pool is dropped.
- Work topic moved to a dedicated auto question (`conversations.work`) with per-profession responses.

### Added
- **Per-profession work talk for the whole runecraft modpack** (scanned 419 jars): hand-written
  lines for all 13 vanilla trades + nitwit + jobless, MCA's 6 registered professions
  (guard/archer/adventurer/mercenary/cultist/outlaw), all 8 More Villagers professions, Ars
  Nouveau's shady wizard, Chef's Delight chef+cook, Ice and Fire scribe, Vampirism's three, and
  Werewolves' expert — 37 professions × 2 variants. Unknown/future professions get a
  self-personalizing generic line via the new `profession_name` template variable (localized
  client-side).
- **New topics**: food (with trait-flavored replies — vegetarian, lactose intolerance, coeliac,
  diabetes, and a sirben easter egg), neighbors/people (personality-bucketed opinions), and
  secret (tier 3, unlocked by having confided — the payoff for the `confided` flag).
- **Age-appropriate answers**: child and teen villagers answer day/dreams/fears in their own
  voice (and never trigger adult follow-ups or unlock flags).
- **Personality overlays for all 13 personalities** (was 2): athletic, confident, friendly,
  witty, shy, sensitive, greedy, odd, lazy, grumpy, peppy join gloomy and flirty — 14
  high-traffic lines each.
- **Anti-repetition depth**: every say line now has a pool of ≥3 variants (≥2 for
  profession/trait/age precision lines); deflect and hub-prompt pools deepened.
- Lint gates: pinned profession roster + ResourceLocation wellformedness, trait vocabulary,
  dead-lang-key detection, variant-pool floor, overlay coverage floor, mixin-config guard.

### Removed
- `dialogue.main.conversations` button and our `chat.success/20-25` pool extensions (dead after the
  Chat replacement).

## [0.1.0] - 2026-07-06

### Fixed
- **World-creation crash** (`No enum constant forge.net.mca.entity.ai.Chore.CHOPPING`): the day
  topic used invalid `current_chore` values (`chopping`/`harvesting`/`fishing`); MCA's `Chore` enum
  is `NONE, PROSPECT, HARVEST, CHOP, HUNT, FISH` and MCA parses these at datapack load with no
  error containment, so the bad values aborted the resource reload while creating a new world.
  Corrected to `chop`/`harvest`/`fish`.
- Hardened all `conversations_*` condition/action parsers: malformed JSON (in this mod or any datapack
  using our keys) now logs an ERROR and degrades to a no-op instead of crashing the reload the
  same way.
- Content lint now validates condition *values* (chore/mood/personality/age_group/rank/constraints
  vocabularies pinned from the MCA 7.6.26 jar), not just condition keys — the gap that let the
  crash ship.

### Added
- Conversations conversation hub merged into MCA's villager Talk menu (`main` question), with 8 topics
  across three trust tiers plus gossip, spouse, and family branches (10 dialogue JSON files).
- Per-player conversation memory built on MCA's LongTermMemory: first-time / asked-recently /
  revisit-later responses per topic, permanent topic flags, `opened_up`/`confided` unlock flags.
- Custom dialogue conditions registered with MCA: `conversations_enabled`, `conversations_disabled`,
  `conversations_gossip`.
- Custom dialogue actions registered with MCA: `conversations_record` (multi-memory writes),
  `conversations_say` (templated lines: villager/spouse/village names, last gift item, time of day),
  `conversations_gossip_say`.
- Village gossip subsystem: marriage/divorce/birth detection by periodic village scan
  (relationship-snapshot diffing), death detection by event; village-scoped, name-cached,
  per-listener once-only delivery; persisted in `mcaconversations_gossip.dat`.
- Gift gratitude: a server-side mixin on MCA's `BreedableRelationship.acceptGift` records accepted
  gifts to a player capability and a per-player `grateful` villager memory (1 day by default).
- `checkin` greeting answer merged into MCA's `greet` question (memory-aware, once per half day).
- ~40 new `/N` line variants appended to MCA's most-heard dialogue pools (`main`, `greet.success`,
  `greet.fail`, `chat.success`, `story.success`, `shake_hand.success`).
- Personality-flavored overrides for gloomy and flirty villagers.
- `/conversations gossip list|clear` admin command (permission level 2).
- Config toggles per feature plus gossip/gift tunables (see CONFIG.md).
- Content lint test suite: dialogue JSON vocabulary, memory-id namespacing, lang-key coverage,
  variant-sequence integrity.

### In-world verification checklist (production-style instance)
1. Boot log shows `Registered dialogue conditions conversations_enabled/...` and no `Dialogue ... not
   properly formatted` warnings.
2. Villager Talk menu shows "Conversations..." and MCA's own buttons still work.
3. Fears topic: deflects below 25 hearts; `.first` line at 25+; immediate re-ask gives `.again`;
   after `/time add 48000` and re-ask gives `.revisit`.
4. "Us"/"Family" buttons hidden for strangers, visible for spouse/family.
5. Give an accepted gift → within a day, spouse "Are you happy?" references the item by name.
6. Marry two villagers (`/mca` admin) → within ~30s a third villager's "Anything happen around
   here lately?" names the couple, exactly once per player, surviving relog.
7. Kill a villager → death gossip names them after the entity is gone.
8. Toggle each `[features]` config off → related lines degrade to fallbacks, no errors.
9. `/forge tps` stays clean near a large village with scanning enabled.
