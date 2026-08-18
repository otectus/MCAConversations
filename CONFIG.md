# MCA: Conversations — Configuration

File: `config/mcaconversations-common.toml` (generated on first run).

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
| `chatModePublicReplies` | `true` | | villager replies are shown to other players within the addressed radius |
| `chatModeShowHeartChanges` | `true` | | subtle `(+2 ♥)` feedback, once per exchange, speaker-only |
| `chatModeMessageFormat` | `<%1$s> %2$s` | | chat line template (`%1$s` villager name, `%2$s` line) |
| `chatModeMuteTicks` | `6000` | 200–72000 | duration of a "stop talking" mute, per villager↔player pairing |
| `chatModeInsultDetection` | `true` | | obvious insults get an in-character rebuke + ANNOYED (never censors) |
| `chatModeLocalChat` | `false` | | **EXPERIMENTAL** — proximity/RP chat: opted-in players' chat becomes radius-local unsigned text and global chat effectively goes away for them (still logged to console). Default flipped to `false` in 0.8.1 — safe for solo/RP packs; on public servers understand the chat-signing and moderation implications first |
| `chatModeGreetOnApproach` | `true` | | villagers may greet a player entering the radius (once per villager/player/day) |
| `chatModeGreetChance` | `0.35` | 0–1 | daily greet chance, scaled by personality (outgoing ×1.5, reserved ×0.5); deterministic per day |
| `chatModeTypingAttention` | `true` | | nearby villagers stop and face a player whose chat box is open |
| `chatModeAttentionTicks` | `600` | 0–72000 | how long a conversation partner stays put facing you after the last exchange (0 disables) |

## `[debug]`

| Option | Default | Meaning |
|---|---|---|
| `debugLogging` | `false` | INFO-level logs for gift recording, gossip detection, and gossip telling |
