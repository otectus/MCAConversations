# MCA: Conversations — Configuration

File: `config/mcaconversations-common.toml` (generated on first run).

## Client-side display settings

File: `config/mcaconversations-client.toml` (generated on first run).

These are local player preferences: how the dialogue interface looks, feels, and responds. They are never sent to the server, and changing them does not affect your world or other players.

## `[display]` (client)

These options affect only the local player's dialogue presentation.

Since 1.7.0 the defaults are `dialogueMenuStyle = MINIMAL` and `motionMode = REDUCED`: the same
numbered menu, drawn flat, with motion limited to a fade when it opens and closes. The richer
presentation is unchanged and one setting away (`dialogueMenuStyle = "RESPONSIVE"`,
`motionMode = "FULL"`).

**Upgrading changes nothing you have already set.** NeoForge writes every option into
`mcaconversations-client.toml` the first time it saves the file, so an existing installation states
these keys explicitly and keeps its own values — a stored `RESPONSIVE` or `FULL` is treated as a
choice, never as a leftover default. Only a key that is absent from the file (a new installation, or
one you have trimmed by hand) picks up the new default.

| Option | Default | Meaning |
|---|---|---|
| `dialogueMenuStyle` | `MINIMAL` (`RESPONSIVE` before 1.7.0) | `MINIMAL` uses the same numbered menu drawn as flat rectangles: one panel, a hairline above the answers, a plain numeral, a stationary focus mark in the row's gutter, and no portrait or badge artwork. `RESPONSIVE` uses the full card with animations and a live speaker portrait. `MCA_ORIGINAL` leaves the dialogue UI entirely to MCA Reborn |
| `numberedResponses` | `true` | Legacy compatibility switch from 1.4.x/1.5.1. When `false`, always uses `MCA_ORIGINAL` regardless of `dialogueMenuStyle`. New installations should normally leave this `true` |
| `numericResponseShortcuts` | `true` | Allow number keys (1–9) to select visible dialogue choices. Disabled automatically when the dialogue style is `MCA_ORIGINAL` |
| `chatNumericShortcuts` | `true` | Let unmodified digits select a pending chat response when the chat input is empty |
| `showResponseControlHints` | `true` | Show keyboard and paging hints below the response list (for `RESPONSIVE` and `MINIMAL` only) |
| `motionMode` | `REDUCED` (`FULL` before 1.7.0) | Conversation motion: `REDUCED` fades the card in when it opens and out when it closes and changes everything else immediately, `FULL` uses short state-driven animation, `OFF` disables every dialogue animation instantly (including question reveal). `OFF` is the canonical way to disable all animation |
| `uiSoundVolume` | `0.65` | Volume multiplier for response focus, page turn, and confirmation sounds. Set to `0.0` to disable all UI sounds |
| `speakerNameAccent` | `true` | Render the speaking villager's name in bold and accent color when that makes the speaker unambiguous (`RESPONSIVE` and `MINIMAL` only) |
| `showSpeakerPortrait` | `true` | Show the speaking villager's portrait in the dialogue card header where the style supports it. The `MINIMAL` style intentionally omits the portrait; `RESPONSIVE` shows it when this is enabled and space allows; `MCA_ORIGINAL` follows MCA's own behavior |
| `questionRevealMode` | `OFF` | How the villager's line appears: `OFF` shows it at once; `FAST` reveals it over a few ticks (ignored when `motionMode` is `OFF` and in `MCA_ORIGINAL` mode). Any input completes the reveal instantly |
| `deliveredHistoryEntries` | `64` | How many delivered lines and sent responses the card's history drawer keeps in memory. The drawer is collapsed by default, holds only what this client actually received, is cleared on disconnect and world change, and is never written to disk or exported. `0` disables the drawer |

#### The two card utilities

The response card carries two small overlays, opened from the buttons at the left of the footer strip
or with `H` and `P`. Both are drawn inside the response viewport, so the panel, the villager's line
and the footer stay exactly where they were; neither can submit a response, and while one is open no
key or click reaches the answer list at all. `Back`, `Esc`, `Backspace` and `Tab` close the overlay
and return the keyboard to the region it came from.

- **`H` — recent lines.** Lines delivered to the dialogue screen and synchronized responses sent
  during this connection: the speaker and their line, and your own response with its truthful status (*sent*, *accepted*, or *refused* with the
  reason the server gave). One utterance is one entry however many surfaces it appeared on. Bounded
  by `deliveredHistoryEntries` and cleared when the connection ends.
- **`P` — presentation.** `dialogueMenuStyle`, `motionMode`, `questionRevealMode`, `uiSoundVolume`
  and `showResponseControlHints`, changed with `Left`/`Right` and written straight to
  `mcaconversations-client.toml`. When `numberedResponses = false` the style row states both the
  configured style and the `MCA_ORIGINAL` it is being overridden to. `Reset to recommended` lists
  what it would change and applies it only when chosen a second time. With Townstead installed the
  pane says so: Townstead draws its own dialogue screen and these settings do not change it.

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

Focus never moves anything under `MINIMAL`: the focused row is a fill plus a two-pixel mark in its
own left gutter, the whole row is the click target, and the numeral and answer text keep the same
coordinates focused or not. Confirming a choice adds a white outline, so the two states differ by
more than a shade of grey.

#### What each motion mode animates

Scope: these are the effects MCA: Conversations draws on its own card. Where another mod owns the
dialogue screen — Townstead in particular — its screen, camera work and typewriter are its own and
are not governed by `motionMode`.

| Event | `FULL` | `REDUCED` | `OFF` |
|---|---|---|---|
| Opening the menu | short entrance (a fade, plus a few pixels of movement under `RESPONSIVE`) | one brief fade | immediate |
| A new question in the same conversation | text and answers replaced in place; under `RESPONSIVE` the rows still cascade in | immediate, panel does not move or re-fade | immediate |
| Moving focus | short eased transition | immediate | immediate |
| Selecting an answer | press and settle under `RESPONSIVE`; a colour change under `MINIMAL` | immediate | immediate |
| Expanding a clipped answer | immediate | immediate | immediate |
| Turning the answer page | short slide | immediate | immediate |
| Revealing the villager's line | `questionRevealMode`, off by default | `questionRevealMode`, off by default | never |
| Closing the menu | short fade out | the same fade out | immediate |

The entrance belongs to the menu, not to each question: a pause between two turns of a conversation
is the server thinking, and the card waits rather than closing and opening again.

#### Resource pack behavior

**`RESPONSIVE`** draws with the vanilla menu background texture and the vanilla button sprites, so a resource pack that retextures those changes the card.

**`MINIMAL`** uses flat primitives and is mostly independent of GUI textures. It still honors the active font, language, and text styling from resource packs.

**`MCA_ORIGINAL`** displays whatever MCA Reborn and the active resource pack normally render for dialogue.

---

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

Content and these options both name features through one closed registry, `FeatureId`. A
`conversations_enabled`/`conversations_disabled` condition (or a direct
`McaConversationsConfig.isFeatureEnabled(String)` call) that names an id `FeatureId` does not
recognise — a typo, or a feature this build has since renamed — is treated as **disabled**, logged
once at WARN, and never as enabled: content that names an unknown feature id is invalidated rather
than switched on.

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
diffs it against a persisted snapshot — both negligible on `/neoforge tps`.

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

### How long a discussion lasts (1.7.1)

These are about the discussion itself rather than the hearts it pays: how far apart you may stand,
and how the server tells a player who is reading from a client that has stopped being there. All of
them live in the **server** file. The *continued* distance is not MCA's reach: opening a
conversation still needs an ordinary interaction, so raising it lets you walk further while talking
and never lets anybody start talking from further away. Chat mode keeps its own hearing radii in
`[chat]`; they are a separate question and are unaffected by these.

| Option | Default | Range | Meaning |
|---|---|---|---|
| `continueDistance` | `16.0` | 1.0-64.0 | How far apart you may be and go on talking, in three dimensions. Standing exactly this far apart is still in range. Was a fixed 8 blocks before 1.7.1 |
| `immediateCloseDistance` | `24.0` | 1.0-128.0 | Past this the conversation ends at once, with no grace. A value below `continueDistance` is raised to it, which simply removes the grace band |
| `distanceGraceTicks` | `20` | 0-1200 | How long you may stand between the two distances before the conversation ends (20 = 1 s). The window stays readable, but no answer or topic change may run from out there, and walking back inside clears the timer. `0` ends it the moment you separate |
| `guiLeaseTicks` | `100` | 0-24000 | How long a dialogue window may go without reporting itself before the villager is released (100 = 5 s). This is what frees a villager from a crashed client or a window that vanished; an open window renews it continuously, so reading for minutes never expires it. `0` switches the lease off |
| `holdVillagerDuringInteraction` | `true` |  | Hold a villager still and facing you while you are talking. Off gives up only the movement hold; every lifecycle rule still applies |
| `attackReopenDelayTicks` | `100` | 0-24000 | How long after an attack before that villager will talk again (100 = 5 s). Ongoing danger keeps refusing regardless |
| `attackedBehavior` | `NATIVE_COMBAT` |  | What an attacked villager is free to do once the conversation has ended. `NATIVE_COMBAT` leaves MCA's own reaction alone, so a guard is still a guard; `RETREAT` asks even a combat profession to break away first |
| `interruptOnImmediateDanger` | `true` |  | End the conversation when the villager is in immediate danger, so they can flee rather than standing in a fight to finish a sentence |

The heartbeat cadence the lease is renewed on is an internal constant (20 ticks), deliberately not a
setting: it is an implementation detail of the window, and the lease above is the number that
decides anything.

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

## `[capitals]`

The optional [MCA: Capitals](https://www.curseforge.com/minecraft/mc-mods/mca-capitals) monarchy integration (requires MCA Capitals 1.3+ (tested against 1.3.6)). **With MCA Capitals not installed, none of this does anything and nothing changes**: every capital context field is unavailable, every capital topic self-hides, all capital news is not seeded, and no Capitals class is ever loaded. Conversations reads capital names, rosters, chronicles and court states and never writes them — all court actions stay Capitals' to own. The integration is purely read-only: capital data is queried on server-side to seed gossip and fill context, and court news is polled on its own cadence (via `capitalNewsPollSeconds`, hooked in event handlers).

Run `/conversations context capabilities` in game to see the live context fields this villager has access to.

| Option | Default | Range | Meaning |
|---|---|---|---|
| `enabled` | `true` | | master switch. Off, Conversations behaves exactly as though Capitals were absent |
| `topicsEnabled` | `true` | | offer the Capitals conversation topics (the crown, the court, houses, the realm) |
| `newsEnabled` | `true` | | turn new chronicle entries and court changes into village gossip, so a coronation or a declaration of war is something villagers bring up on their own |
| `newsPollSeconds` | `30` | 5–600 | how often the chronicle is checked for new entries, in seconds. Independent of the gossip sweep's own cadence; a capital's chronicle changes far less often than a village does |
| `newsMaxPerPoll` | `3` | 1–20 | how many chronicle entries one poll may turn into gossip. A capital that generated a burst of events while nobody was near should not flood the village with all of them |
| `diplomacyTalkEnabled` | `true` | | let villagers speak about wars, truces and alliances between capitals. Off, the `at_war` and `allied` context fields stay unavailable and only domestic court talk remains |
| `roleRemarkEnabled` | `true` | | let a villager whose title just changed remark on it when approached, once |
| `roleRemarkDays` | `7` | 1–60 | how many days a title change stays fresh enough to be worth remarking on |
| `contextCacheTicks` | `200` | 0–6000 | how long a resolved villager-to-capital lookup is reused, in ticks. 0 re-resolves every read, which is correct but scans the capital registry far more often |
| `debug` | `false` | | verbose logging for the Capitals binding and context reads |

## `[debug]`

| Option | Default | Meaning |
|---|---|---|
| `debugLogging` | `false` | INFO-level logs for gift recording, gossip detection, and gossip telling |


### Session and replay behavior

An unanswered GUI page retains its response offer, context, and spent heart budget while it remains active. The chat inactivity timeout continues to apply to chat offers. Reopening the same active topic does not reset the per-conversation budget. Reconnecting starts a new transient session; durable daily and one-shot guards remain in saved data.

Optional API build overrides (`mcaQuestsApiPath`, `mcaReputationApiPath`) are Gradle properties, not in-game configuration options. See [validation and production checks](docs/STABILIZATION-2026-09.md).
