# MCA: Conversations 1.5.1 — 1.21.1 NeoForge acceptance checklist

Manual smoke-test procedure for the 1.21.1 NeoForge port. Build under test: `mcaconversations-neoforge-1.5.1+1.21.1.jar` against MCA `net.conczin.mca:mca-neoforge:7.7.33+1.21.1` or `7.7.36-beta.3+1.21.1` (SHA-256 `de4763d3…c436fc3`, see [PORT-1.21.1-EVIDENCE.md](PORT-1.21.1-EVIDENCE.md)).

All steps assume one clean world with MCA and Conversations, no other mods, in singleplayer or on a dedicated server with the player as op (level 4).

---

## Already verified automatically

Re-runnable with `./gradlew clean test build`.

| Check | How | Coverage |
|---|---|---|
| 991 tests pass, real Minecraft on the classpath | `./gradlew test` | All units, integration, content, compat |
| All 920 dialogue files, 110 beats, 26 chat-intent files parse and all keys resolve | Content lints in `build` | 173→920 dialogues verified |
| `en_us` / `pt_br` parity across 23 asset namespaces | `LocaleParityTest` | No `gui.mcaconversations.*` or `chat.mcaconversations.*` raw keys visible |
| 14-personality roster matches resolved MCA; legacy overlays ship | `PersonalitiesTest` | `friendly`, `flirty`, `playful`, `gloomy`, `sensitive`, `greedy`, `odd`, `crabby`, `extroverted`, `introverted`, `relaxed`, `anxious`, `peaceful`, `upbeat` (no `confident`, `peppy`, `athletic` in MCA's `CANONICAL`) |
| 1.20.1 `ForgeCaps` player data imports correctly | `ForgeCapsMigrationTest` | Gift and chat-mode migration over fresh, corrupt, and repeat-pass fixtures |
| Attachment NBT keys unchanged from Forge capability era | `AttachmentNbtRoundTripTest` | Round-trip fidelity |
| SavedData filenames unchanged; gossip round-trips save/load | `SavedDataContractTest` | `mcaconversations_{dispositions,gossip,progress,history,identity,culture}` |
| Networking payloads round-trip with correct byte layout | `TypingStatus`, `ChoiceOffer`, `ChoiceClear`, `ChoiceSelect` payload tests | Protocol version `"2"`, exact byte codecs |
| No Forge-era API, no `mods.toml`, no SRG, no refmap | `NeoForgePortLintTest` | Pure NeoForge 21.1 APIs; mojmap; no DistExecutor |
| Jar contains correct metadata and content, nothing shaded, Java 21 | `verifyJarContents` | 920 dialogues, 110 beats, 26 chat-intents, correct manifest |
| Dedicated server starts with MCA, all 6 common mixins apply | `./gradlew runServer` | No startup errors; every MCA mixin is `require = 0` (SOURCE's graceful-degradation policy, `defaultRequire: 0`), so check the log for Mixin apply warnings rather than a crash |

---

## Manual smoke tests (human at keyboard)

### 1. Startup and binding

- **Setup:** Start the game with MCA 7.7.33+1.21.1 or 7.7.36-beta.3+1.21.1, single-player or dedicated server.
- **Step 1a:** Open the world and wait for FML to finish loading.
  - **Observe:** No `[ERROR]` or `[FATAL]` in the log. No `mcaconversations` mixin apply errors.
  - **Evidence:** Screenshot of the log view showing clean startup. Check console for `[ERROR] (...McaConversations...)`.
- **Step 1b:** Run `/conversations chat debug`.
  - **Observe:** Output shows binding root resolved: `ROOT: net.conczin.mca.` (not `ABSENT` or `UNBINDABLE`).
  - **Observe:** `UNBOUND: []` (empty list; all required members found).
  - **Evidence:** Screenshot of command output.

### 2. Hub routing and numbered choice card

- **Setup:** Stand near an MCA villager. Open the villager's interact screen.
- **Step 2a:** Verify hub routing mode matches `hubEntryMode` config.
  - **If `ADDITIVE`:** Both **Chat** button and a separate **Conversations** entry appear in the menu.
  - **If `REPLACE`:** Only **Chat** appears, and clicking it opens the Conversations hub directly.
  - **If `HIDDEN`:** Only **Chat** appears, and its behavior is vanilla (no Conversations intervention).
  - **Evidence:** Screenshot of the interact menu.
- **Step 2b:** Click **Chat** (or **Conversations** if separate) to open the dialogue.
- **Step 2c:** Verify numbered choice card.
  - **Observe:** If the villager has a 2–9 choice response, numbered badges (1–9) appear on the visible choices.
  - **Observe:** Pressing numeric keys 1–9 selects the corresponding choice; pressing Up/Down/Page-Up/Page-Down navigates; pressing Enter selects the focused choice.
  - **Observe:** Clicking a choice with the mouse works.
  - **Observe:** Mouse scroll changes the page.
  - **Evidence:** Screenshot of a numbered dialogue with badges visible.

### 3. Choice synchronization (dedicated server)

- **Setup:** On a dedicated server with the player as op.
- **Step 3a:** Stand near a villager. Open their interact screen and open a dialogue.
- **Step 3b:** On the interact screen, press a numeric key (e.g., `2`) to select a choice.
  - **Observe:** The choice is sent to the server and processed. The screen updates or closes as MCA's normal flow continues.
  - **Evidence:** No visible desync; the villager responds as if you had clicked.
- **Step 3c:** Intentionally interrupt (e.g., walk away mid-dialogue, or close the screen without submitting).
- **Step 3d:** Re-open the interact screen immediately.
  - **Observe:** The stale choice offer is cleared; the dialogue reverts to the prior state.
- **Step 3e:** Relog (disconnect and reconnect to the server).
  - **Observe:** The offer is gone on reconnect. No ghost choices remain.
  - **Evidence:** Screenshot showing cleared state after relog.

### 4. Chat mode and typing attention

- **Setup:** Ensure `McaConversationsConfig.COMMON.enableChatMode` is `true`.
- **Step 4a:** Run `/conversations chat on`.
  - **Observe:** Output confirms chat mode is now enabled for you.
  - **Evidence:** Screenshot of the command output.
- **Step 4b:** Type a message in chat while standing near a villager.
  - **Observe:** The villager turns to look at you (VillagerAttention fires).
  - **Observe:** A typing indicator (e.g., `... typing ...` if configured) may appear near the villager or in chat.
  - **Observe:** When you stop typing (finish the message or wait), the villager looks away.
- **Step 4c:** The villager may respond in chat. If `group.enabled` is `false` (default), they respond only to you. If `true`, they may respond to the whole group.
  - **Evidence:** Screenshot showing the villager's attention state and chat response.
- **Step 4d:** Run `/conversations chat off`.
  - **Observe:** Chat mode is disabled. Typing no longer triggers villager attention.

### 5. Gift acceptance and memory

All six substeps are required. **Only the last two (5e and 5f) should produce gratitude or recorded gift memory.**

- **Setup:** Have a well-liked gift in your inventory (e.g., flowers, apples, emeralds, or a configured best-response item).
- **Step 5a:** Give the villager a gift they dislike.
  - **Observe:** They refuse with a response line (e.g., "That's not my thing.").
  - **Evidence:** Screenshot of refusal. Check world/data: `mcaconversations_progress.dat` should NOT have a new `last_gift_item` entry for this villager.
- **Step 5b:** Fill the villager's inventory completely (give them 27 stacks of cobblestone or similar).
- **Step 5c:** Give them another gift.
  - **Observe:** They refuse (inventory full).
  - **Evidence:** Screenshot of refusal. Verify `mcaconversations_progress.dat` is unchanged.
- **Step 5d:** Give the same well-liked gift multiple times until their response saturates (e.g., hearts stop increasing, or a satiation message appears).
  - **Observe:** On the saturated attempt, they refuse (e.g., "I'm satisfied for now.").
  - **Evidence:** Screenshot of satiation refusal. Verify `mcaconversations_progress.dat` does NOT record the saturated attempt.
- **Step 5e:** Give the villager a well-liked gift (after hearts have recovered or in a new interaction).
  - **Observe:** They accept with gratitude (e.g., "Thank you! That's wonderful!").
  - **Observe:** They play a surprised/happy sound.
  - **Observe:** Exactly one item is consumed from the stack.
  - **Evidence:** Screenshot of acceptance and sound. Save the world and inspect `mcaconversations_progress.dat` in `world/data/`: it should contain a `last_gift_item` entry for this villager matching the gift you gave.
- **Step 5f:** Give a best-response gift (if one is configured and in your inventory).
  - **Observe:** They accept with emphatic gratitude (e.g., "Oh, this is perfect!").
  - **Observe:** They play a delighted sound (different from 5e).
  - **Observe:** Exactly one item is consumed.
  - **Evidence:** Screenshot. Verify `mcaconversations_progress.dat` records the new item (may replace the prior gift, depending on config).

### 6. Gift memory persistence

- **Setup:** Completed step 5e or 5f (at least one gift accepted and recorded).
- **Step 6a:** Save the world (`/save-all` on dedicated server, Esc → Save and Quit on single-player).
- **Step 6b:** Relog (rejoin the world).
  - **Observe:** The villager still remembers the gift. The gratitude line or flavor text in their dialogue may reference it (e.g., "I still treasure that gift.").
  - **Evidence:** Screenshot of the dialogue line. Verify `mcaconversations_progress.dat` still contains the recorded gift.
- **Step 6c:** On single-player, die and respawn (or on dedicated server, the player dies and respawns).
  - **Observe:** Gift memory is preserved; no duplication or loss.
  - **Evidence:** Screenshot of the villager's continued remembrance.

### 7. Gossip (village news)

- **Setup:** Run multiple interactions: marriages, births, deaths, arrivals, departures (if configured).
- **Step 7a:** Run `/conversations gossip list`.
  - **Observe:** Gossip events are displayed, e.g., `[village 1] marriage: Alice & Bob (5 ticks ago)`.
  - **Observe:** The list persists across relogs (unless cleared).
  - **Evidence:** Screenshot of the gossip log.
- **Step 7b:** Verify retention: observe a villager's dialogue for gossip callouts (e.g., "Did you hear about Alice?").
  - **Evidence:** Screenshot of a gossip-aware dialogue line.

### 8. Living histories: profiles, history, scenes

- **Setup:** Interact with MCA villagers to build up history (gifts, rejections, conversations, marriages, etc.).
- **Step 8a:** Stand near a villager and run `/conversations profile inspect`.
  - **Observe:** Output shows the villager's profile: seed, schema version, completeness.
  - **Evidence:** Screenshot showing the profile data.
- **Step 8b:** Run `/conversations history inspect`.
  - **Observe:** Output shows episodes, threads, commitments, and social opinions involving you and the villager.
  - **Evidence:** Screenshot.
- **Step 8c:** Run `/conversations scene plan`.
  - **Observe:** Output shows the next planned scene: topic, purpose, beat, and why it was chosen.
  - **Evidence:** Screenshot.
- **Step 8d:** Run `/conversations context snapshot`.
  - **Observe:** Output shows contextual information: profession, workplace, home, personality, mood, etc.
  - **Evidence:** Screenshot.
- **Step 8e:** Run `/conversations profile tokens`.
  - **Observe:** Output lists available identity tokens (if enabled).
  - **Evidence:** Screenshot.

### 9. Persistence of living-history data

- **Setup:** Completed step 8 (profile, history, scenes recorded).
- **Step 9a:** Save and relog.
  - **Observe:** Profile and history are identical before and after the reload.
  - **Evidence:** Screenshot of `profile inspect` and `history inspect` before and after.
- **Step 9b:** Verify the six SavedData files exist in `world/data/`:
  - `mcaconversations_dispositions.dat`
  - `mcaconversations_gossip.dat`
  - `mcaconversations_progress.dat`
  - `mcaconversations_history.dat`
  - `mcaconversations_identity.dat`
  - `mcaconversations_culture.dat`
  - **Evidence:** File browser screenshot showing all six.

### 10. VillagerMessage invariant

- **Setup:** Enable chat mode (`/conversations chat on`). Interact with a villager in dialogue.
- **Step 10a:** The villager sends a response line. Observe the same text in two places:
  - In the interact panel (the dialogue screen).
  - In the chat window (if the reply is public/group-enabled).
- **Step 10b:** The text must be **identical** in both locations (same formatting, same line breaks, same colors).
  - **Observe:** No truncation, no re-parsing, no random re-draw of the same pooled line.
  - **Evidence:** Screenshot showing the interact panel and chat window side-by-side with the same villager response.

### 11. Townstead integration (if present)

- **Setup:** Have Townstead 0.7.6 NeoForge 1.21.1 installed.
- **Step 11a:** Find a Townstead RPG dialogue (if one is encountered in normal play, or via developer tools).
- **Step 11b:** Verify numbered choice card.
  - **Observe:** Choices in Townstead's dialogue screen have numbered badges (1–9) if the mod is active.
  - **Observe:** Numeric keys, arrow keys, paging, and mouse clicks all work (same as step 2).
  - **Evidence:** Screenshot of a numbered Townstead dialogue.
- **Step 11c:** Verify no breakage without Townstead.
  - **Uninstall** Townstead and restart the game.
  - **Observe:** Conversations still works; all tests pass; no errors in the log.
  - **Evidence:** Screenshot of the test output showing no failures.
- **Step 11d:** Re-install Townstead.

### 12. Townstead absence

- **Setup:** Uninstall Townstead (or if not installed, no setup needed).
- **Step 12a:** Run a full game session (interact with villagers, accept gifts, chat).
  - **Observe:** Nothing breaks. Conversations still works.
  - **Evidence:** Screenshot of a clean log and successful interactions.

### 13. Dedicated server + `/reload`

- **Setup:** On a dedicated server with both MCA and Conversations.
- **Step 13a:** Run `/reload`.
  - **Observe:** All 920 dialogues parse without error.
  - **Observe:** No `mcaconversations.*` raw keys (`"gui.mcaconversations.*"` or `"chat.mcaconversations.*"`) appear in chat.
  - **Observe:** No `[ERROR]` or `[WARN]` related to Conversations in the log.
  - **Evidence:** Screenshot of the log showing a clean reload.
- **Step 13b:** Interact with a villager post-reload.
  - **Observe:** Dialogue works normally.
  - **Evidence:** Screenshot of a post-reload interaction.

### 14. Client-side locale guards

- **Setup:** Have `en_us` or `pt_br` selected in the launcher.
- **Step 14a (en_us):** Generic dialogue lines and personality-specific lines both appear.
  - **Observe:** Personality overlays work (e.g., flirty NPCs have flirtier lines).
  - **Evidence:** Screenshot showing personality-differentiated dialogue.
- **Step 14b (pt_br):** Repeat 14a in Portuguese.
  - **Observe:** Both generic and personality-specific Portuguese lines appear.
  - **Evidence:** Screenshot showing Portuguese personality overlays.
- **Step 14c:** If MCAVoices is installed, verify suppression.
  - **Observe:** Per-personality dialogue is suppressed (only generic lines appear).
  - **Evidence:** Screenshot comparing with and without MCAVoices.
- **Step 14d:** If MCA's online TTS is enabled, verify suppression.
  - **Observe:** Per-personality dialogue is suppressed.
  - **Evidence:** Screenshot.

### 15. Config (server vs client)

- **Setup:** Run on a dedicated server. Have a client and server config file pair (one on the server, one on the client).
- **Step 15a:** Modify a server-only key in the server's `mcaconversations-server.toml` (e.g., `chat.chatModeGreetChance` to `1.0`, or `chat.chatModeAddressedRadius`). Restart the server without restarting the client.
  - **Observe:** The change takes effect. Dialogue behavior changes.
  - **Evidence:** Screenshot showing the changed behavior.
- **Step 15b:** Modify a client-only key in the client's `mcaconversations-client.toml` (e.g., `display.uiSoundVolume` to `0.0`, or `display.numberedResponses` to `false`). Restart the client without restarting the server.
  - **Observe:** The change takes effect on the client; server behavior is unaffected.
  - **Evidence:** Screenshot of the changed UI.
- **Step 15c:** Verify defaults: `group.enabled` is `false` by default (no group interjections unless explicitly enabled).
  - **Observe:** By default, villagers respond only to the player, not to the whole group.
  - **Evidence:** Screenshot showing the configured default (e.g., log line during startup or config inspection).

---

## Unverified (beyond scope of this checklist)

- **MCA: Quests and MCA: Reputation** integration. No 1.21.1 NeoForge release exists yet; the adapters compile and degrade to documented no-mod behaviour.
- **Serene Seasons** integration. The bridge is reflection-only; integration tests cover absent and unexpected API paths, but the present-and-compatible path needs the real 1.21.1 NeoForge jar (not released yet as of 2026-09-03).
