# Changelog

All notable changes to this project will be documented in this file. Format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/); versions follow SemVer.

Compatibility: Minecraft 1.20.1 · Forge 47.x · requires MCA Reborn `[7.6,8)` + Architectury API `[9.2,10)`.

## [0.1.0] - 2026-07-06

### Added
- Real Talk conversation hub merged into MCA's villager Talk menu (`main` question), with 8 topics
  across three trust tiers plus gossip, spouse, and family branches (10 dialogue JSON files).
- Per-player conversation memory built on MCA's LongTermMemory: first-time / asked-recently /
  revisit-later responses per topic, permanent topic flags, `opened_up`/`confided` unlock flags.
- Custom dialogue conditions registered with MCA: `realtalk_enabled`, `realtalk_disabled`,
  `realtalk_gossip`.
- Custom dialogue actions registered with MCA: `realtalk_record` (multi-memory writes),
  `realtalk_say` (templated lines: villager/spouse/village names, last gift item, time of day),
  `realtalk_gossip_say`.
- Village gossip subsystem: marriage/divorce/birth detection by periodic village scan
  (relationship-snapshot diffing), death detection by event; village-scoped, name-cached,
  per-listener once-only delivery; persisted in `mcarealtalk_gossip.dat`.
- Gift gratitude: a server-side mixin on MCA's `BreedableRelationship.acceptGift` records accepted
  gifts to a player capability and a per-player `grateful` villager memory (1 day by default).
- `checkin` greeting answer merged into MCA's `greet` question (memory-aware, once per half day).
- ~40 new `/N` line variants appended to MCA's most-heard dialogue pools (`main`, `greet.success`,
  `greet.fail`, `chat.success`, `story.success`, `shake_hand.success`).
- Personality-flavored overrides for gloomy and flirty villagers.
- `/realtalk gossip list|clear` admin command (permission level 2).
- Config toggles per feature plus gossip/gift tunables (see CONFIG.md).
- Content lint test suite: dialogue JSON vocabulary, memory-id namespacing, lang-key coverage,
  variant-sequence integrity.

### In-world verification checklist (production-style instance)
1. Boot log shows `Registered dialogue conditions realtalk_enabled/...` and no `Dialogue ... not
   properly formatted` warnings.
2. Villager Talk menu shows "Real talk..." and MCA's own buttons still work.
3. Fears topic: deflects below 25 hearts; `.first` line at 25+; immediate re-ask gives `.again`;
   after `/time add 48000` and re-ask gives `.revisit`.
4. "Us"/"Family" buttons hidden for strangers, visible for spouse/family.
5. Give an accepted gift → within a day, spouse "Are you happy?" references the item by name.
6. Marry two villagers (`/mca` admin) → within ~30s a third villager's "Anything happen around
   here lately?" names the couple, exactly once per player, surviving relog.
7. Kill a villager → death gossip names them after the entity is gone.
8. Toggle each `[features]` config off → related lines degrade to fallbacks, no errors.
9. `/forge tps` stays clean near a large village with scanning enabled.
