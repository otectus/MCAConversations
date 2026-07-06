# MCA: Real Talk — Configuration

File: `config/mcarealtalk-common.toml` (generated on first run).

## `[features]`

| Option | Default | Effect when disabled |
|---|---|---|
| `replaceChatWithRealTalk` | `true` | MCA's Chat button behaves like vanilla MCA; the Real Talk hub becomes unreachable (no separate button exists since v0.2.0 — see DATAPACK.md to restore one via datapack) |
| `enableTopics` | `true` | Real Talk topic branches deflect to fallback lines |
| `enableStates` | `true` | gratitude after gifts is neither recorded nor referenced |
| `enableTemplates` | `true` | templated lines render with neutral fallbacks ("someone", "the village") |
| `enableGossip` | `true` | no events are detected or told; the "news" topic answers "quiet week" |

Feature toggles act through the `realtalk_enabled`/`realtalk_disabled` dialogue conditions and
server-side early-outs — content degrades to fallback lines rather than disappearing mid-tree.

## `[gift]`

| Option | Default | Range | Meaning |
|---|---|---|---|
| `giftMemoryPerPlayerCap` | 16 | 1–256 | most recent gifts remembered per player (one per villager, oldest dropped) |
| `gratitudeWindowTicks` | 24000 | 1200–168000 | how long a villager stays "grateful" after an accepted gift |

## `[gossip]`

| Option | Default | Range | Meaning |
|---|---|---|---|
| `gossipScanIntervalTicks` | 600 | 100–24000 | ticks between village relationship scans (600 = 30 s) |
| `gossipRetentionDays` | 7 | 1–64 | MC days an event stays tellable |
| `maxEventsPerVillage` | 32 | 4–256 | retained events per village (oldest dropped) |
| `detectMarriage` / `detectDivorce` / `detectDeath` / `detectBirth` | `true` | | per-event-type detection toggles |

Scan cost: one nearest village per online player per interval, deduplicated, primitive reads over
loaded residents only — negligible on `/forge tps`.

## `[debug]`

| Option | Default | Meaning |
|---|---|---|
| `debugLogging` | `false` | INFO-level logs for gift recording, gossip detection, and gossip telling |
