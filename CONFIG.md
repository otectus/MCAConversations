# MCA: Conversations — Configuration

Three files, generated on first run:

| File | Scope | Holds |
|---|---|---|
| `config/mcaconversations-common.toml` | per installation, not synchronised | feature switches and debug flags |
| `<world>/serverconfig/mcaconversations-server.toml` | per world, **synchronised to every client** | gameplay values: hearing distance, the heart economy, disposition movement, villager initiative, history caps |
| `config/mcaconversations-client.toml` | per player, client only | presentation |

### Moved in 1.5.0

The values in the third column used to live in `mcaconversations-common.toml`. A common file is
loaded on both sides of a connection and synchronised on neither, so a client and a server could
disagree about how far a villager hears, how many hearts a day a conversation may pay, or how often
a villager may speak first — none of which is a decision a client gets to make. They now live in a
server file, which Forge stores per world and sends to every client on connect.

**If you had customised any of them**, copy your value into the new
`<world>/serverconfig/mcaconversations-server.toml`. The old entries are ignored and can be deleted;
nothing breaks if you leave them there. Everything you did not customise keeps the same default, so
an untouched install behaves identically. Where a section below spans two files, a note under its
heading says which of its options went where.

## `[display]` (client)

These options affect only the local player's dialogue presentation. Defaults provide the complete
1.5.1 experience without configuration.

| Option | Default | Meaning |
|---|---|---|
| `dialogueMenuStyle` | `RESPONSIVE` | `RESPONSIVE` uses the full MCA: Conversations responsive card with animations and a live speaker portrait. `MINIMAL` uses the responsive menu with simpler graphics and no portrait. `MCA_ORIGINAL` leaves the dialogue UI entirely to MCA Reborn |
| `numberedResponses` | `true` | Legacy compatibility switch from 1.4.x/1.5.1. When `false`, always uses `MCA_ORIGINAL` regardless of `dialogueMenuStyle`. New installations should normally leave this `true` |
| `numericResponseShortcuts` | `true` | Allow number keys (1–9) to select visible dialogue choices. Disabled automatically when the dialogue style is `MCA_ORIGINAL` |
| `chatNumericShortcuts` | `true` | Let unmodified digits select a pending chat response when the chat input is empty |
| `showResponseControlHints` | `true` | Show keyboard and paging hints below the response list (for `RESPONSIVE` and `MINIMAL` only) |
| `motionMode` | `FULL` | Conversation motion: `FULL` uses short state-driven animation, `REDUCED` uses fades only, `OFF` disables every dialogue animation instantly (including question reveal). `OFF` is the canonical way to disable all animation |
| `uiSoundVolume` | `0.65` | Volume multiplier for response focus, page turn, and confirmation sounds. Set to `0.0` to disable all UI sounds |
| `speakerNameAccent` | `true` | Render the speaking villager's name in bold and accent color when that makes the speaker unambiguous (`RESPONSIVE` and `MINIMAL` only) |
| `showSpeakerPortrait` | `true` | Show the speaking villager's portrait in the dialogue card header where the style supports it. The `MINIMAL` style intentionally omits the portrait; `RESPONSIVE` shows it when this is enabled and space allows; `MCA_ORIGINAL` follows MCA's own behavior |
| `questionRevealMode` | `OFF` | How the villager's line appears: `OFF` shows it at once; `FAST` reveals it over a few ticks (ignored when `motionMode` is `OFF` and in `MCA_ORIGINAL` mode). Any input completes the reveal instantly |

#### Support: getting the exact MCA Reborn dialogue interface

To use MCA Reborn's native dialogue menu instead of the Conversations card:

```toml
dialogueMenuStyle = "MCA_ORIGINAL"
```

Existing users with `numberedResponses = false` from 1.5.1 automatically get this behavior.

#### Support: keeping the new interface but removing animations

To use either `RESPONSIVE` or `MINIMAL` without any animation:

```toml
motionMode = "OFF"
```

#### Interaction between `numberedResponses` and `dialogueMenuStyle`

When `numberedResponses = false`, the effective dialogue style is always `MCA_ORIGINAL`, even if `dialogueMenuStyle` is set to `RESPONSIVE` or `MINIMAL`. This preserves compatibility for users who explicitly disabled numbered responses in 1.5.1 to restore MCA's native interface.

#### Ignored settings by style

**When using `MCA_ORIGINAL`:**

The following options are ignored because MCA Reborn owns the dialogue presentation and input:
- `motionMode`
- `showResponseControlHints`
- `showSpeakerPortrait`
- `questionRevealMode`
- `speakerNameAccent` (applied only by the Conversations renderer)
- `numericResponseShortcuts` (GUI numeric shortcuts)

Chat numeric shortcuts remain controlled by `chatNumericShortcuts`.

**When using `MINIMAL`:**

The live villager portrait is intentionally omitted regardless of `showSpeakerPortrait`. When `motionMode = FULL`, `MINIMAL` uses a restrained motion profile with no row cascade, no focus pop-out or lift, and no selection press movement, while entrance and page transitions keep a short 2-pixel slide. Other motion modes behave identically in both styles.

#### Resource pack behavior

**`RESPONSIVE`** draws the card body as a flat, translucent dark backing that resource packs do not change. Number badges and page buttons are drawn from the vanilla `widgets.png` texture and follow resource packs that retexture it. The selection frames and scrollbar are flat colours that resource packs do not change.

**`MINIMAL`** uses flat primitives and is mostly independent of GUI textures. It still honors the active font, language, and text styling from resource packs.

**`MCA_ORIGINAL`** displays whatever MCA Reborn and the active resource pack normally render for dialogue.

## `[features]`

| Option | Default | Effect when disabled |
|---|---|---|
| `enableTopics` | `true` | Every converted topic falls back to its legacy one-line result and returns to its category — the branching trees are not entered at all. Distinct from `enableBranching`, which keeps the topics but disables the trees behind them |
| `enableStates` | `true` | conversation states (grateful, grieving, elated, annoyed, smitten, proud) are neither recorded nor referenced |
| `enableTemplates` | `true` | templated lines render with neutral fallbacks ("someone", "the village") |
| `enableGossip` | `true` | no events are detected or told; the "news" topic answers "quiet week" |
| `enableQuests` | `true` | MCA: Quests integration is inert: the four `conversations_quest_*` conditions score 0, quest lines aren't voiced, and finished quests seed no gossip. Only ever active when the `mcaquests` mod is installed. Note that `conversations_quest_available` is always about the villager in front of you, so `scope` does not apply to it |
| `enableWeatherLines` | `true` | weather-aware lines are off; the `conversations_weather` condition and the `world` feature flag score as disabled |
| `enableBranching` | `true` | converted topics fall back to their 1.0.0 one-line result and return to the category, payout and all. Nothing is ever left as an empty page: each starter carries an explicit legacy fallback |

### `hubEntryMode` (default `ADDITIVE`)

How the Conversations hub is reached from MCA's villager **interaction screen**. Replaces the old
boolean `replaceChatWithConversations`, which had no setting for "both work".

| Mode | MCA's Chat answer | Conversations button |
|---|---|---|
| `ADDITIVE` *(default)* | unchanged | visible, as its own entry |
| `REPLACE` | opens the Conversations hub | hidden, so there is only one way in |
| `HIDDEN` | unchanged | hidden |

This is **not** chat mode. `enableChatMode` is about talking to villagers in normal chat with
natural language; `hubEntryMode` only decides which button opens the hub inside MCA's GUI. They are
independent — chat mode works in all three modes.

**No mode affects MCA's own AI chat**, which is triggered by naming a villager in normal chat and
never routes through the dialogue system this mod hooks.

**Upgrading:** the old key is gone and existing configs land on `ADDITIVE`, a superset of both
previous settings. Set `hubEntryMode = "REPLACE"` (old `true`) or `"HIDDEN"` (old `false`) to
reproduce the old behaviour exactly.

Feature toggles act through the `conversations_enabled`/`conversations_disabled` dialogue conditions and
server-side early-outs — content degrades to fallback lines rather than disappearing mid-tree.

## `[dynamic]` — living histories

> `maxInitiativesPerVillagerPlayerDay`, `initiativeCooldownTicks` and `dynamicTopicSlots` live in the **server** file; the switches and `debugDirector` live in the **common** file.

Every switch here has an off state that reproduces the static conversation exactly. The layer is
additive: with `enabled = false` the complete hand-authored corpus is selected by the same static
routers it always was, and
nothing new is read, written or generated.

| Option | Default | Effect |
|---|---|---|
| `enabled` | `true` | Master switch. Off means no scene is ever planned, every `conversations_scene` condition scores 0, and every dynamic route takes its sink. |
| `identityEnabled` | `true` | Two interests, two values, a comfort, an aversion and three styles per villager, generated once from the world seed and their UUID and never rerolled. Off means no profile is generated or persisted and selection is identity-neutral. |
| `episodesEnabled` | `true` | Villagers carry concrete situations between conversations with states that change. Off means only evergreen scenes are selected and no promise is ever created. |
| `socialOpinionsEnabled` | `true` | Bounded, *caused* social knowledge: opinions of named neighbours and the observed roles they hold — coworker, mentor, customer, someone avoided. Never a resident-by-resident graph: an edge needs a family tie, shared work or an observed event. |
| `villageCultureEnabled` | `true` | Shared village tokens residents can agree or disagree about. |
| `maxInitiativesPerVillagerPlayerDay` | `1` | How often one villager may open a *decision page* unprompted, per player per day. A passing hello is not counted against it; an emergency and a genuine change in something you already know about are not rationed by it either, though both still wait out `initiativeCooldownTicks`. `0` disables villager initiative entirely. |
| `initiativeCooldownTicks` | `300` | Real-time floor between two unprompted lines from the same villager to the same player, whatever the daily budget still allows (300 = 15 s). This is the backstop that stops a villager talking at somebody standing next to them; the daily budget is what keeps the day quiet. Range 20–24000. |
| `dynamicTopicSlots` | `3` | Context-specific entries above the six fixed hub categories. `0` keeps the six fixed categories alone. |
| `debugDirector` | `false` | Log why each scene was chosen. Verbose; for authoring. |

## `[history]`

> `enabled` lives in the **common** file; every cap below it lives in the **server** file.

| Option | Default | Effect |
|---|---|---|
| `enabled` | `true` | Persist episodes, threads, promises, claims and opinions to `data/mcaconversations_history.dat`. Off means nothing new is written and the existing arcs, milestones, affection budgets and disposition vectors are untouched. |
| `episodeRetentionDays` | `32` | Days a resolved episode stays callable before it is compressed and pruned. |
| `activeEpisodeCap` | `6` | Simultaneous live episodes per villager. Over the cap the least salient is *abandoned* — a state a scene can speak from — never silently deleted. |
| `resolvedEpisodeCap` | `24` | Resolved episodes kept as remembered history. |
| `openThreadCapPerPair` | `8` | Storage bound, not a menu size: only the highest-priority item in each category is ever offered. |
| `commitmentCapPerPair` | `8` | An outstanding promise is never evicted to make room. |
| `playerClaimCapPerPair` | `16` | Things the player has told one villager about themselves. |
| `socialEdgeCapPerVillager` | `16` | Opinions one villager may hold about named neighbours, and separately the observed roles they may hold. |
| `topicRecencyCapPerPair` | `32` | Entries per repetition-suppression level. |

Every cap is clamped to a hard ceiling the store enforces whatever the file says, so a mis-set value
can make the mod remember *less* but never make a save grow without bound. The configured and hard
numbers are both printed in `reports/memory-schema.md`.

## `[group]`

| Option | Default | Effect |
|---|---|---|
| `enabled` | `false` | Allow a second and third villager to join with a contracted interjection. Off by default: group scenes are chat-only for now, and every interjection must answer the beat just spoken and rest on a footing its speaker actually has. Five shapes exist and nothing else is allowed — corroborate something public, differ about a preference, add a detail from the same trade, remember a family event differently, or say that a thing is not yours to tell. |
| `maxSpeakers` | `3` | Hard cap including the lead villager. |

## `[gift]`

| Option | Default | Range | Meaning |
|---|---|---|---|
| `giftMemoryPerPlayerCap` | 16 | 1–256 | most recent gifts remembered per player (one per villager, oldest dropped) |
| `gratitudeWindowTicks` | 24000 | 1200–168000 | how long a villager stays "grateful" after an accepted gift |

## `[states]`

Conversation states are short-lived moods an event leaves on a villager that colour its dialogue for a
while. Each value is a duration in game ticks (24000 = 1 MC day); all require `enableStates`. Dialogue
gates on them with a plain `{"memory": {"id": "mcaconversations.state.<name>"}}` condition.

| Option | Default | Range | Meaning |
|---|---|---|---|
| `stateGriefWindowTicks` | 48000 | 1200–168000 | residents stay "grieving" after a death in their village |
| `stateElatedWindowTicks` | 24000 | 1200–168000 | residents stay "elated" after a birth or marriage in their village |
| `stateAnnoyedWindowTicks` | 12000 | 1200–168000 | a villager stays "annoyed" at a player who struck it |
| `stateSmittenWindowTicks` | 24000 | 1200–168000 | a villager stays "smitten" after a gift given while very fond |
| `stateProudWindowTicks` | 24000 | 1200–168000 | a villager stays "proud" of a player who completed a quest for it (needs MCA: Quests) |
| `stateSmittenMinHearts` | 100 | 1–1000 | minimum hearts at gift time for the gift to also make the villager "smitten" |

## `[world]`

| Option | Default | Meaning |
|---|---|---|
| `enableWeatherLines` | `true` | enable weather-aware lines: the `weather` template variable and the `conversations_weather` dialogue condition (buckets: `clear`, `rain`, `storm`) |
| `enableSeasonLines` | `true` | enable season-aware lines: the `season` template variable and the `conversations_season` condition (buckets: `spring`, `summer`, `autumn`, `winter`). Read from **Serene Seasons** when installed, else derived from the world day |
| `enableHolidayLines` | `true` | enable festival-day lines: the `holiday` template variable and the `conversations_holiday` condition (buckets: `spring_bloom`, `midsummer`, `harvest_festival`, `midwinter`, `none`). Always calendar-based |
| `seasonYearLengthDays` | 96 | length of a year in MC days for the calendar season/holiday dates (four equal quarters from spring at day 0); range 4–4096. Default 96 matches Serene Seasons' default 24-day seasons |

## `[gossip]`

| Option | Default | Range | Meaning |
|---|---|---|---|
| `gossipScanIntervalTicks` | 600 | 100–24000 | ticks between village relationship scans (600 = 30 s) |
| `gossipRetentionDays` | 7 | 1–64 | MC days an event stays tellable |
| `maxEventsPerVillage` | 32 | 4–256 | retained events per village (oldest dropped) |
| `detectMarriage` / `detectDivorce` / `detectDeath` / `detectBirth` | `true` | | per-event-type detection toggles |
| `detectArrival` / `detectDeparture` | `true` | | notice villagers moving into / away from a village (residency-set diffing) |

Scan cost: one nearest village per online player per interval, deduplicated. Relationship detection reads
loaded residents only; arrival/departure reads the village's stored residency set (load-independent) and
diffs it against a persisted snapshot — both negligible on `/forge tps`.

## `[rpg]`

> The three `enable*` toggles and `debugRpg` live in the **common** file; the four `disposition*` numbers live in the **server** file.

The 0.7.0+ RPG layer: an internal per-(villager, player) **disposition vector** (trust, respect,
warmth, attraction, tension, familiarity) that gates and voices dialogue, and **dialogue checks**
with crit/success/partial/rebuff outcomes. Hearts remain MCA's only visible relationship number —
the vector never grants hearts. Every toggle degrades to a documented simpler behavior; everything
off is exactly the 0.6.0 experience. See DATAPACK.md → *The disposition vector & dialogue checks*.

| Option | Default | Range | Meaning / off-state |
|---|---|---|---|
| `enableDispositions` | `true` | | master toggle for the vector. Off: no vector state is read or written, disposition-gated results never match (their authored fallbacks fire), checks run on a hearts-only formula |
| `enableChecks` | `true` | | master toggle for dialogue checks. Off: checked stances resolve through their plain fallback result (0.6.0-style single outcome) |
| `enableCheckTiers` | `true` | | four-tier outcomes. Off: binary — crit collapses into success, partial into rebuff |
| `dispositionGainMultiplier` | 1.0 | 0.0–4.0 | scale on all disposition gains and losses (0 freezes the vector) |
| `dispositionDecayMultiplier` | 1.0 | 0.0–4.0 | scale on drift back toward the personality baseline (0 = never drifts) |
| `dispositionDailyAxisCap` | 8 | 1–50 | per-axis, per-MC-day cap on total disposition movement (anti-farming) |
| `dispositionStaleDays` | 0 | 0–365 | prune records untouched this many MC days (0 = only prune on villager death) |
| `debugRpg` | `false` | | INFO-level logs for disposition reads/writes, check inputs, tier selection |

## `[conversation]`

> Everything here except `debugBranching` lives in the **server** file.

The branching-conversation economy. Hearts move on what you **say back** — never on asking,
navigating, or leaving. Every conversation-sourced heart change passes through a guarded ledger:
the authored delta is scaled by the multiplier, clamped by the depth class's per-conversation
budget, clamped again by the per-day budget, diminished on repeat, and applied at most once per
transaction. These caps stay active even with the disposition vector switched off.

| Option | Default | Meaning |
|---|---|---|
| `conversationHeartMultiplier` | `1.0` | Scale on every conversation heart change, both directions. `0.0` makes conversation heart-neutral while the trees, vector and arcs still run |
| `conversationDailyPositiveCap` | `8` | Per-villager, per-player, per-MC-day ceiling on hearts **gained** from conversation |
| `conversationDailyNegativeCap` | `10` | Per-day floor on hearts **lost**, as a positive number. Counted separately, so antagonising a villager never manufactures room to earn more back |
| `strongerNegativeOutcomes` | `false` | Double authored negatives before the caps, for players who want dismissiveness to bite harder |
| `conversationSessionTimeoutTicks` | `1200` | How long a conversation survives without activity before its per-conversation budget resets (1200 = 60 s). Sessions never persist across a restart; arcs, milestones and daily budgets do |
| `debugBranching` | `false` | Verbose logging: topic and node transitions, decision ids, check inputs and tier, requested vs applied hearts, vector deltas, arc and milestone moves |

Per-conversation budgets come from the topic's depth class rather than a config knob, so an
operator has four numbers to reason about instead of forty:

| Depth class | Gain per conversation | Loss per conversation |
|---|---:|---:|
| Quick (weather, food, the day) | +2 | −3 |
| Standard (work, village, news) | +4 | −5 |
| Deep / Relationship (fears, secrets, family) | +8 | −10 |
| Service (a work offer) | +2 | −2 |

One honest wrinkle: MCA itself doubles a **negative** heart change for a `SENSITIVE` villager,
inside its own reward path and therefore after these caps. The budget bounds what this mod grants;
MCA's personality rule can still amplify a granted loss.

## `[chat]`

> `chatModeRadius`, `chatModeAddressedRadius`, `chatModeStickinessTicks`, `chatModeGreetChance` and `chatModeAttentionTicks` live in the **server** file — they decide whether a villager answers at all. The rest lives in the **common** file.

Chat mode (since 0.8.0): talk to villagers by typing in the vanilla chat box; they answer in chat through
the same dialogue engine as the GUI. **On by default.** Per-player opt-out: `/conversations chat off`.
Ops can inspect matching live with `/conversations chat debug <message>`.

| Option | Default | Range | Meaning |
|---|---|---|---|
| `enableChatMode` | `true` | | master switch — off restores the exact pre-chat-mode experience |
| `chatModeDefaultOn` | `true` | | players are opted in before ever running `/conversations chat on` |
| `chatModeRadius` | `12.0` | 1–64 | ambient hearing radius (blocks) for unaddressed messages |
| `chatModeAddressedRadius` | `24.0` | 1–96 | radius when the villager is named or sticky ("calling out") |
| `chatModeStickinessTicks` | `600` | 0–72000 | how long the last conversation partner stays the default target |
| `chatModeLookConeDegrees` | `25.0` | 0–90 | half-angle of the look-at targeting cone (0 disables) |
| `chatModeMaxResponders` | `2` | 1–5 | max villagers answering one ambient message |
| `chatModeMinScore` | `0.55` | 0–1 | match-confidence threshold for addressed messages |
| `chatModeAmbientMinScore` | `0.75` | 0–1 | stricter threshold for ambient messages (raise on chatty servers) |
| `chatModeReplyDelayTicks` | `15` | 0–100 | base humanized reply delay (scaled by line length) |
| `chatModeCooldownTicks` | `40` | 0–1200 | per-player floor between processed messages (anti-spam) |
| `chatModePublicReplies` | `true` | | villager replies are shown to other players within the addressed radius (everyone in range reads the same wording — the line's variant is chosen once, server-side) |
| `chatModeShowHeartChanges` | `true` | | subtle `(+2 ♥)` feedback, once per exchange, speaker-only |
| `chatModeMessageFormat` | `<%1$s> %2$s` | | chat line template (`%1$s` villager name, `%2$s` line) |
| `chatModeMuteTicks` | `6000` | 200–72000 | duration of a "stop talking" mute, per villager↔player pairing |
| `chatModeInsultDetection` | `true` | | obvious insults get an in-character rebuke + ANNOYED (never censors) |
| `chatModeLocalChat` | `false` | | **EXPERIMENTAL** — proximity/RP chat: opted-in players' chat becomes radius-local unsigned text and global chat effectively goes away for them (still logged to console). Default flipped to `false` in 0.8.1 — safe for solo/RP packs; on public servers understand the chat-signing and moderation implications first |
| `chatModeGreetOnApproach` | `true` | | villagers may greet a player entering the radius (once per villager/player/day) |
| `chatModeGreetChance` | `0.35` | 0–1 | daily greet chance, scaled by personality (outgoing ×1.5, reserved ×0.5); deterministic per day |
| `chatModeTypingAttention` | `true` | | nearby villagers stop and face a player whose chat box is open |
| `chatModeAttentionTicks` | `600` | 0–72000 | how long a conversation partner stays put facing you after the last exchange (0 disables) |

## `[townstead]`

The optional [Townstead](https://www.curseforge.com/minecraft/mc-mods/townstead) integration. **With
Townstead not installed, none of this does anything and nothing changes**: every Townstead dialogue
condition scores `0`, every Townstead template variable falls back to its neutral wording, no
Townstead class is ever loaded, and every existing seeded check resolves exactly as it does today.

Supported Townstead range: `[0.7.5,0.8)`, gated on `0.7.6`. Conversations reads Townstead state and
never writes it — needs, schedules, professions, skills, roots, genes, the calendar, buildings and
village spirit all stay Townstead's to own. The three things Conversations does tell Townstead are a
measured heart change, a typed-chat conversation opening and closing, and an authored, heart-neutral
reaction.

Run `/conversations compat townstead status` in game to see what actually bound.

| Option | Default | Range | Meaning |
|---|---|---|---|
| `enabled` | `true` | | master switch. Off, Conversations behaves exactly as though Townstead were absent |
| `contentEnabled` | `true` | | offer the Townstead conversation topics (wellbeing, daily rhythm, work and mastery, age and life, roots, home and place, community identity, calendar) |
| `contextConditionsEnabled` | `true` | | let the `conversations_townstead*` dialogue conditions read Townstead state. Off, they score `0` and authored fallback branches fire instead |
| `contextCheckFitEnabled` | `true` | | let an authored `townstead_fit` block colour a dialogue check. Off, the term is exactly `0` |
| `reactionsEnabled` | `true` | | fire Townstead reactions on conversation outcomes. Every bundled reaction is heart-neutral. **Townstead can only play a reaction through Emotecraft**, so without that mod this degrades to no reaction rather than to an error |
| `emotionEffectsEnabled` | `true` | | supply Conversations emotion tags inside Townstead's RPG dialogue typewriter. Client side only; never leaks markup into chat mode, system chat, TTS or base MCA UI |
| `scheduleRespectEnabled` | `true` | | let a villager's Townstead shift affect greetings, ambient replies, deep-topic availability and how firmly chat mode holds their attention. Off, a working villager is interrupted exactly as before |
| `typedChatDialogueTrackingEnabled` | `true` | | tell Townstead when a typed-chat conversation opens and closes, so its `in_dialogue_with_player` and `dialogue_just_ended` tags are true for chat mode as well as the RPG screen |
| `giftNeedObservationEnabled` | `true` | | after an accepted gift, re-read the villager's needs a tick later and only then let gratitude lines claim it helped. Conversations never fills a need itself |
| `gossipEnabled` | `true` | | let the existing village gossip sweep also notice Townstead changes: need crises and recoveries, profession progress, newly learned skills, life-stage and birthday milestones, buildings appearing and disappearing, and village spirit shifting |
| `customPersonalityProfilesEnabled` | `true` | | match a Townstead custom personality to its exact interiority profile before falling back to the MCA personality it is based on |
| `calendarSource` | `AUTO` | `AUTO`, `TOWNSTEAD`, `SERENE_SEASONS`, `BUILTIN` | which mod decides the narrative date and season. `AUTO` prefers Townstead when healthy, then Serene Seasons, then the built-in calendar. Exactly one source ever answers, so two installed calendars cannot contradict each other in one conversation |
| `useLegacyHolidayFallbackWithTownstead` | `false` | | when Townstead owns the calendar and no `townstead_holidays` mapping matches today, fall back to the built-in fixed festival cycle. Off by default because that cycle is keyed to Conversations' own year length and would land on unrelated dates in a Townstead calendar |
| `maxCheckFit` | `8` | 0–14 | hard clamp on the `townstead_fit` dialogue-check term, in points. Kept below the 15-point tier margin so Townstead state can colour a borderline exchange without deciding one |
| `contextCacheTicks` | `20` | 1–100 | how long a Townstead context read is reused by the chat scans. Dialogue evaluation always caches for exactly one tick regardless, because MCA scores many candidate results for a single click |
| `needCrisisCooldownDays` | `2` | 0–60 | days before the same villager can produce another need-crisis rumour |
| `buildingRemovalConfirmScans` | `2` | 1–10 | how many consecutive sweeps must agree a known building is gone before that becomes news. Guards against a reload transient reading as a demolition |
| `debug` | `false` | | verbose logging for Townstead binding, context reads and reactions |

## `[debug]`

| Option | Default | Meaning |
|---|---|---|
| `debugLogging` | `false` | INFO-level logs for gift recording, gossip detection, and gossip telling |
