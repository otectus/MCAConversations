# MCA: Conversations 2.0.0 — acceptance checklist

What the automated suite already proves, and what still needs a human at the keyboard before this
is tagged.

Build under test: `mcaconversations-neoforge-2.0.0+1.21.1.jar`
against MCA `net.conczin.mca:mca-neoforge:7.7.36-beta.3+1.21.1`
(SHA-256 `de4763d3…c436fc3`, see [PORT-1.21.1-EVIDENCE.md](PORT-1.21.1-EVIDENCE.md)).

---

## Already verified automatically

Re-runnable with `./gradlew clean test build`.

| Check | How |
|---|---|
| 537 tests across 68 classes pass, with real Minecraft on the classpath | `./gradlew test` |
| All 173 dialogue files, 13 chat-intent files, the catalog and the interiority profile parse, and every referenced translation key exists in both locales | the seven content lints |
| `en_us` / `pt_br` parity across all 23 asset namespaces | `LocaleParityTest` |
| The 14-personality roster matches the resolved MCA jar; the three retained legacy voices still ship | `PersonalitiesTest`, `OverlayLintTest` |
| 1.20.1 `ForgeCaps` player data imports correctly from frozen `.dat` fixtures — gift-only, chat-only, both, malformed, absent, new-data-wins, and a repeat pass | `ForgeCapsMigrationTest` |
| Attachment NBT keys are unchanged from the capability era | `AttachmentNbtRoundTripTest` |
| The three SavedData filenames are unchanged, and gossip survives save/load through the new API | `SavedDataContractTest` |
| The typing payload round-trips and carries exactly one byte | `TypingStatusPayloadTest` |
| No Forge-era API, no `mods.toml`, no refmap, no `ForgeCaps` outside the migration | `NeoForgePortLintTest` |
| The jar contains the right metadata and content, nothing shaded, all classes Java 21 | `verifyJarContents` |
| A dedicated server starts with MCA loaded and **all six common mixins apply**, including the `require = 1` gift hook | `./gradlew runServer` |

The gift hook deserves a note: it is declared `require = 1` against the single
`ItemStack.split(1)` invocation inside `acceptGift`. If that instruction were not found, Mixin
would abort startup rather than no-op. A clean server boot is therefore positive proof the hook is
attached at the right instruction — not merely that it failed quietly.

---

## Needs a human — feature acceptance

One clean world, MCA + Conversations, no other mods. Each step is a behaviour a unit test cannot
reach.

### Hub routing (`hubEntryMode`)

1. `ADDITIVE` — the villager menu shows MCA's **Chat** *and* a separate **Conversations** entry.
2. `REPLACE` — **Chat** opens the Conversations hub directly, and the separate entry is gone.
3. `HIDDEN` — the separate entry is gone and **Chat** behaves exactly as it does without this mod.

### Conversation mechanics

4. Walk a complete branching topic end to end. The chosen answer applies its response, its progress
   and its disposition change, and at most the permitted heart change.
5. Re-send the same GUI packet for a guarded outcome (relog mid-conversation, or double-click).
   The outcome must not apply twice.

### Gifts — the regression this port fixes

Do all six. Only the last two may produce gratitude or a remembered gift.

6. Give a gift the villager refuses → **no** gratitude, **no** `last_gift_item`.
7. Fill the villager's inventory, then give a gift → refused, and again nothing recorded.
8. Give the same well-liked gift repeatedly until the response saturates → the saturated refusal
   records nothing.
9. Give an accepted gift → gratitude appears, exactly **one** item is consumed from the stack.
10. Give a best-response gift → gratitude, the surprised sound, one item consumed.
11. Relog, then die and respawn. The remembered gift survives both.

### Content and world

12. Templates resolve: villager name, spouse, village, profession, time of day, weather, season,
    holiday, and last gift.
13. Gossip records and re-tells death, marriage, birth, arrival and departure as configured.
14. `/conversations chat on|off` persists across relog.
15. Named, sticky, look-at, ambient, quick-reply, busy, baby/toddler, mute, cooldown and confusion
    paths behave as they did on 1.20.1.
16. **Default chat is untouched vanilla signed chat.** Nothing is cancelled or rewritten unless the
    experimental local-chat option is on; with it on, chat is radius-limited and explicitly
    unsigned.
17. Typing in chat turns nearby villagers toward you; closing chat releases them; and the hold
    expires on its own after a disconnect (kill the client rather than closing chat cleanly).
18. Public and private villager reply modes reach the right players.
19. `/conversations chat debug-ask` reports the redirect hook as **active**.

### Client-side gates

20. `en_us` — generic and per-personality lines both resolve.
21. `pt_br` — generic and per-personality overlays both resolve.
22. With **MCAVoices** installed — per-personality dialogue is suppressed (the voice-pack guard is
    preserved).
23. With MCA's **online TTS** enabled — likewise suppressed.

---

## Needs a human — world upgrade

**Work on copies.** Never open the only copy of a world you care about.

Build two 1.20.1 fixture worlds on the Forge 1.2.1 build first:

- a minimal one: one player, one MCA villager;
- a populated one: gift memory for at least two villagers, an explicit chat-mode choice that
  *differs* from `chatModeDefaultOn`, and non-empty `mcaconversations_dispositions.dat`,
  `mcaconversations_gossip.dat` and `mcaconversations_progress.dat`.

Then, on copies, under 1.21.1 + NeoForge + MCA 7.7.36-beta.3 + this build:

| # | Check |
|---|---|
| 1 | Minecraft and MCA complete their own upgrades with no Conversations error in the log |
| 2 | All three `.dat` files load with their original record counts — not reset to empty |
| 3 | Old gift memory appears under the new attachment (one INFO line per migrated player) |
| 4 | The explicit chat-mode choice is still explicit and still the same value |
| 5 | After one save, the player file contains `neoforge:attachments` and no `ForgeCaps` |
| 6 | A second restart neither re-imports nor duplicates anything |
| 7 | Death and an End return each preserve both attachments exactly once |
| 8 | Content, personalities and family names are all still usable |

Report MCA's own personality/trait data conversion separately from Conversations' migration. MCA
7.7.36 changes how it persists those relative to older 7.7 builds, and conflating the two would
misattribute whichever one actually moved something.

---

## Not covered here

- **MCA: Quests and MCA: Reputation integrations.** Neither sibling has a 1.21.1 NeoForge release,
  so there is nothing to test them against. The adapters are still in the tree; Quests compiles
  against the ported sibling when its classes are present, and Reputation is excluded from the
  build until a port exists. Both degrade to their documented no-mod behaviour.
- **Serene Seasons.** The bridge is reflection-only and its unit tests cover the absent and
  unexpected-API paths, but the present-and-compatible path needs the real 1.21.1 NeoForge jar.
