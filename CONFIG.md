# MCA: Conversations — Configuration

File: `config/mcaconversations-common.toml` (generated on first run).

## `[features]`

| Option | Default | Effect when disabled |
|---|---|---|
| `replaceChatWithConversations` | `true` | MCA's Chat button behaves like vanilla MCA; the Conversations hub becomes unreachable (no separate button exists since v0.2.0 — see DATAPACK.md to restore one via datapack) |
| `enableTopics` | `true` | Conversations topic branches deflect to fallback lines |
| `enableStates` | `true` | conversation states (grateful, grieving, elated, annoyed, smitten, proud) are neither recorded nor referenced |
| `enableTemplates` | `true` | templated lines render with neutral fallbacks ("someone", "the village") |
| `enableGossip` | `true` | no events are detected or told; the "news" topic answers "quiet week" |
| `enableQuests` | `true` | MCA: Quests integration is inert (quest-aware conditions score 0, quest lines aren't voiced) — only ever active when the `mcaquests` mod is installed |
| `enableWeatherLines` | `true` | weather-aware lines are off; the `conversations_weather` condition and the `world` feature flag score as disabled |

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

## `[debug]`

| Option | Default | Meaning |
|---|---|---|
| `debugLogging` | `false` | INFO-level logs for gift recording, gossip detection, and gossip telling |
