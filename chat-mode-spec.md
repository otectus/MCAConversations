# MCA: Conversations — Chat-Only Mode Specification

**Feature:** `chatMode` — an optional mode where players talk to villagers through the vanilla Minecraft chat box and villagers answer in chat, with no interaction GUI.
**Target:** MCAConversations 0.8.x · Minecraft 1.20.1 · Forge 47.x · MCA Reborn `[7.6,8)`
**Status:** Implemented and shipped in **0.8.0** (all four phases). This document is the design of
record; see the addendum below for where the implementation deliberately evolved past it.
**Hard constraint:** No AI/LLM/ML of any kind. All matching is deterministic, rule-based, data-driven, unit-testable, and runs server-side.

> ### Post-spec addendum (as shipped in 0.8.0)
> - **Defaults**: all chat/proximity flags default **on** (user decision; §10's table keeps the
>   design-time dark-launch values).
> - **Greetings**: the system `greet` no longer drives `greet/checkin` (those lines answer *"How
>   have you been, really?"* and read out of context) — greetings use dedicated `chatmode.hail` /
>   `hail_cold` lang pools, hearts-aware. Proactive greet-on-approach is a per-(villager, player,
>   day) deterministic roll (`chatModeGreetChance` × personality weight) with its own daily memory.
> - **Attention** (not in the original spec): villagers stop and face a player whose chat box is
>   open, and a conversation partner stays put until `chatModeAttentionTicks` lapses. The typing
>   signal required the mod's **one client→server packet** — a deliberate deviation from §1's "no
>   new client code or packets" non-goal (the mod is required on both sides regardless).
> - **Confusion gating**: unmatched messages captured via stickiness/look-at only draw the confused
>   ladder when the message engages the villager (question form or second person) — §5 tier-2's
>   pronoun cue, promoted to a filter, so player-to-player chat is never interrupted.
> - **Mute**: per villager↔player pairing (§11 as written), enforced for ambient and proactive
>   greetings too.
> - **Vocatives**: greeting-prefixed forms (`Hey Agnes! …`) and bare typo'd names (`Anges?`) resolve
>   as addresses; a bare name gets an attentive "Yes?" acknowledgment.

---

## 0. Table of contents

1. [Vision & scope](#1-vision--scope)
2. [Prior art & research findings](#2-prior-art--research-findings)
3. [Architecture overview](#3-architecture-overview)
4. [Chat interception (Forge 1.20.1)](#4-chat-interception-forge-1201)
5. [Villager targeting & addressing](#5-villager-targeting--addressing)
6. [The matching engine (non-AI NLU)](#6-the-matching-engine-non-ai-nlu)
7. [Intent data format (datapack JSON)](#7-intent-data-format-datapack-json)
8. [Dialogue execution & delivery](#8-dialogue-execution--delivery)
9. [Conversation session state](#9-conversation-session-state)
10. [Configuration](#10-configuration)
11. [Failure, deflection & anti-spam behavior](#11-failure-deflection--anti-spam-behavior)
12. [Multiplayer & broadcast semantics](#12-multiplayer--broadcast-semantics)
13. [New classes & file map](#13-new-classes--file-map)
14. [Testing requirements](#14-testing-requirements)
15. [Implementation phases](#15-implementation-phases)
16. [Risks & mitigations](#16-risks--mitigations)

---

## 1. Vision & scope

Today, all Conversations content is reached through MCA's interact screen: the player right-clicks a villager, clicks **Chat**, and navigates a category hub (`conversations` → `conversations.cat.*`) with buttons. Responses render inside the dialogue screen.

**Chat mode** makes the same content reachable by simply typing. The player writes a normal chat message — `Agnes, how was your day?` — and the villager answers **in the chat box**, in their own voice (personality overlay, name, templated variables), applying the *same* heart gates, cooldown memories, dispositions, moods, dialogue checks, and gossip state as the GUI. Chat mode is a **second frontend to the existing dialogue engine**, not a second dialogue system.

### Decisions already made (user-confirmed)

- **Targeting:** all three mechanisms combined — name addressing (`Agnes, …`), look-at priority, and conversation stickiness (the villager you last talked to keeps responding for a window). Unaddressed messages fall back to nearest eligible villager.
- **Gating parity:** full parity with the GUI. Heart gates, deflections, dialogue checks, memory writes, disposition changes all run identically. Chat mode is a real alternate frontend, never read-only flavor.
- **Distance:** villagers respond within an adequate but configurable radius.
- **Multiple responders:** if a message is "remotely applicable" to several villagers and not addressed to one, more than one may respond, bounded by config (see §12).

### Non-goals

- No natural-language *generation*. Villagers only ever say lines that exist in the lang packs (base + 13 personality overlays).
- No client-side mod requirement beyond what already exists. Chat mode must work for vanilla-client players on a modded server? **No** — this is a Forge mod on both sides already (MCA requires the client mod). But chat mode itself must not add any *new* client code or packets (see §8).
- No modification of MCA's chat button flow (`replaceChatWithConversations` GUI path is untouched; both frontends coexist).

---

## 2. Prior art & research findings

*(Research base for the design decisions in §5–§6 and §11. A coding agent does not need to re-read the sources; the actionable conclusions are inlined. Provenance: §2.1–§2.2 were adversarially verified against primary sources (source code and official specs, 3-0 verifier votes per claim, fetched 2026-07-12); §2.4 was verified directly against Forge 47 / MCA Reborn 1.20.1 source during spec authoring; §2.3 and §2.5 are design lineage — well-known systems whose specifics were **not** re-verified, so the spec treats them as inspiration and encodes the actual behavior normatively in §6/§11 with tests, not citations.)*

### 2.1 Minecraft ecosystem: exactly one mature non-AI precedent

**Citizens/Denizen chat triggers** (Bukkit/Spigot) are the only mature free-text chat→NPC response system in the ecosystem, and their mechanics — verified from `ChatTrigger.java` (dev branch) and official docs — are the closest existing relative of this feature:

- **Addressing is purely spatial.** Denizen finds the *closest* chat-trigger-enabled NPC within a hard 25-block search, then rejects unless the player is inside that NPC's per-trigger configured radius (`npc.getTriggerTrait().getRadius(name) < distance`). Two optional server-config checks tighten it: `player.hasLineOfSight(npc)` and `isFacingEntity(player, npc, 45°)`. Radius and re-trigger cooldown are per-NPC, per-trigger knobs. Their docs explicitly warn that a large chat radius "can be easily accidentally triggered by unrelated chatter."
- **Matching is deliberately primitive**: case-insensitive substring containment (`messageLow.contains(keywordLow)`) on a keyword wrapped in `/slashes/`, pipe alternatives (`/hi|hello|hey/`), a `regex:` prefix, and a `/*/` catch-all; first-match-wins in listed order, so authors put the catch-all last as the deflection branch. (A claimed `strict:` exact-match prefix failed verification — do not assume it exists.)
- **On match, the Bukkit chat event is canceled** so the message never reaches global chat, with a per-trigger `show as normal chat: true` override and an `event.setMessage` rewrite hook.
- **No-match fallback is explicit and three-way configurable**: failed keyword match (default: suppress + display the player's line as local "overheard" NPC-directed speech within a 4-block range), NPC on cooldown/engaged (default: fall through to global chat), no chat trigger at all (default: global chat).

What chat mode **adopts** from Denizen: per-feature radius + cooldown knobs; look-at as an addressing signal (§5 tier 3; Denizen's facing tolerance is 45° — ours defaults tighter at 25° because it is one signal among four, not the sole gate); catch-all-last as explicit deflection content (§11); the accidental-trigger warning, encoded as the stricter ambient threshold (§6.1 tenet 1, `chatModeAmbientMinScore`). What it **rejects**: substring containment as the matching primitive (`"rain"` would match "training" — untenable against ~45 intents; Denizen tolerates it because each NPC carries a handful of author-tuned keywords), closest-NPC-only addressing (no name vocatives, no stickiness — poor fit for a village of named villagers), and cancel-the-event chat suppression (§2.4: mechanically poisonous under 1.19+ signed chat, and philosophically wrong for this mod — the player's words are theirs and stay in chat).

**Negative results, verified 3-0:** the popular "talking villager" mods are *not* prior art for matching. Villager Talk (Fabric, client-side, ~800 phrases) and Talking Villagers (Spigot) both make villagers **proactively** speak on proximity/game-state triggers (approach, weather, profession, raids, bells) and never parse typed chat; every villager mod that *does* respond to typed chat (VillagerGPT, Speaking Villagers, Villager AI) is LLM-based and excluded by this spec's hard constraint. They remain useful as secondary references for delivery feel: randomized per-villager reply delays to avoid overlap (→ §8.4 humanized delay, §12 staggering) and profession/context-conditioned phrase pools (which MCAConversations already has, far deeper).

**MCA Reborn's own legacy "Chat" interaction** (the thing `replaceChatWithConversations` reroutes) is button-driven, not free-text — MCA has never parsed chat box text; this mode is genuinely new surface for the MCA family.

### 2.2 Classic pattern-matching engines: three convergent lessons

Forty years of non-ML chatbots (ELIZA lineage → AIML 2.0 → RiveScript → ChatScript) converge on three patterns, each verified against the primary spec/manual and each visible in §6:

1. **Normalize, then match — every engine, no exceptions.** AIML 2.0's preprocessor removes punctuation, expands contractions, corrects a few common misspellings, collapses whitespace, and matches case-invariantly in a `[a-zA-Z0-9]*` token space. RiveScript lowercases, strips punctuation, and applies author-defined `! sub` substitutions (`! sub what's = what is`) *before* trigger lookup. ChatScript goes furthest: plurals→singular, verbs→infinitive, pronouns→subject form, text numbers→digits — and **matches both the original and canonical token streams simultaneously** when the pattern word is canonical (`(dog)` matches "dogs"; `(be *1 correct)` matches was/are/is), so authors never enumerate inflections. → §6.2 is exactly this pipeline; stemming both the message *and* the keyword tables at load (§7.2) reproduces ChatScript's author-writes-natural-words property.
2. **Specificity beats scores for *selection*; scores exist for *routing*.** AIML resolves ambiguity with a fixed per-node priority (`$word > # > _ > word > <set> > ^ > *`) and non-greedy wildcards — zero scoring machinery. RiveScript auto-sorts triggers most-specific-first by typed wildcard (`_` letters-only > `#` number > `*` any). ChatScript is two-level: **topics are routed by keyword score** ("number and length of matched keywords"), then rules inside the winning topic run in declared order, first-rule-that-outputs wins — Wilcox explicitly rejects numeric salience for authoring ("salience is… not a good way to author control"). → Chat mode's hybrid (§6.3) is ChatScript's shape, adapted: IDF-weighted keyword evidence *routes* (like topic scoring — with one extra job these engines never had: deciding **whether to answer at all**, since a villager must usually stay silent; that's the confidence threshold), while phrase patterns and system-intent precedence supply the deterministic most-specific-first layer (§6.4, §6.6). The margin rule replaces AIML's graph priority for near-ties.
3. **Meaning via structure: synonym sets, context predicates, structural negation.** ChatScript's `~concept` sets (`concept: ~meat (bacon ham beef …)`, nestable) are the canonical "match sets of words instead of words ≈ match meaning" device → §7's `synonyms` classes are precisely this, datapack-authorable. ChatScript's verified negation idiom — `( ![ not never rarely ] I * ~ingest * ~meat )` so "I never eat meat" can't fire the meat-eater reply — is the structural pattern behind §6.2's negation window. AIML's `<that>` (match against the bot's last utterance) and `topic` predicate (scope categories to the active subject, both joined into the match path) are the direct ancestors of §6.7's `currentQuestion` context scoping — chat mode's version is stronger because the *server* knows the actual open question id rather than re-matching the bot's last text. RiveScript's typed wildcards inform the deliberately tiny §6.4 grammar (one gap construct + synonym-class slots; ChatScript's full operator zoo — ranged `*~n`, optional `{}`, unordered `<< >>` — is consciously *not* imported: ~45 intents don't need it, and every operator is authoring surface that datapack writers can misuse).

### 2.3 MUD / interactive-fiction lineage (inspiration-grade, not verified)

The research pass's MUD/IF claims did not survive adversarial verification, so no specific mechanism is cited here as fact. The *shape* this spec borrows from that tradition — keyword-triggered NPC responses on `ask <npc> about <topic>` (DikuMUD-family mob triggers, TADS 3 / Inform 7 topic systems) and Emily Short's conversation-design essays — is: a finite, discoverable **topic inventory** per NPC; **graceful, in-character deflection** when a topic isn't recognized, escalating to explicit topic *suggestion* rather than repeated identical failure; and greeting/farewell as first-class conversational states. These appear in this spec as: the topic inventory being literally enumerable (§7.2's coverage requirement over the dialogue answers), the graduated confused→hint→shrug ladder (§11), and greeting/farewell system intents (§7). A coding agent should treat §11's behavior table as normative on its own terms — it does not depend on any external system being accurately described.

### 2.4 Forge 1.20.1 chat mechanics (verified directly against source during spec authoring)

- **`ServerChatEvent` fires off-thread.** Forge 47's `ForgeHooks.getServerChatSubmittedDecorator()` wraps event firing in `CompletableFuture.supplyAsync(…, Util.backgroundExecutor())` — the handler runs on a background executor, not the server thread. Every design in §4 (capture `(player, raw)` → `server.execute` hop) follows from this one verified fact.
- **Canceling/mutating signed player chat is hazardous.** Since 1.19.1, player messages are cryptographically signed and chained; canceling or rewriting them server-side yields client "message validation" warnings/kicks on strict setups and breaks report chains (this is why Denizen's Bukkit-era cancel model doesn't transfer). Chat mode's default path therefore **never** cancels or mutates the player's message — villagers respond *around* normal chat. The opt-in `chatModeLocalChat` documents its unsigned-rebroadcast trade-off explicitly (§4).
- **Villager speech as system messages is proven in-repo.** `McaCompat.sayInDialogue`'s fallback already sends villager-voiced Components via `ServerPlayer.sendSystemMessage` — system messages are unsigned by design, involve no signing machinery, and render client-side lang correctly. §8.3 simply promotes the existing fallback to the primary delivery.
- **MCA API surfaces** used by §6.6/§8/§9 (`Dialogues.selectAnswer` semantics and its lack of constraint re-validation, `Constraint.allMatching` + `Answer.isValidForConstraint`, both dialogue S2C packets' public fields, `EntityCommandHandler.stopInteracting()`'s null-safe no-op, `getInteractingPlayer()`'s open-screen filter) were each read from MCA 1.20.1 source; exact signatures are inlined at their point of use.

### 2.5 Quantitative matching techniques (normative design, tuned by tests)

No fuzzy-matching/scoring claims survived the research pass's verification bar, so §6.3–§6.5 are specified as **this spec's own normative algorithm** — assembled from the verified engine patterns above (keyword routing scored by count/length/specificity, structural negation, normalize-both-sides) plus standard, uncontroversial string-matching machinery (Damerau-Levenshtein edit bounds, Jaro-Winkler for short names, IDF specificity weighting) chosen for implementability without dependencies. The concrete constants (thresholds `0.55`/`0.75`, boost values, edit-distance bounds, window sizes) are **starting values, not researched truths**: the §14 table-driven matcher corpus (≥ 100 positive cases + the anti-case set) is the tuning instrument, and the config exposes the two thresholds precisely because live servers will want to trade precision against recall. Treat any future disagreement between this section's constants and the test corpus as the corpus winning.

### 2.6 Finding → decision map

| Verified finding | Spec decision |
|---|---|
| Denizen: per-NPC radius + cooldown knobs; "large radius → accidental triggers" warning | `chatModeRadius`/`chatModeAddressedRadius`/`chatModeCooldownTicks` (§10); strict ambient threshold (§6.1) |
| Denizen: line-of-sight / 45° facing options; closest-NPC addressing | Look-at cone as one of four addressing tiers (§5); name vocatives + stickiness added *beyond* prior art |
| Denizen: catch-all-last deflection branch; three-way no-match config | Graduated deflection ladder, addressed-only (§11); ambient no-match = silence (§6.1 tenet 4) |
| Denizen: cancel-event suppression (Bukkit) | **Rejected** for default path — signed-chat hazard (§2.4); opt-in `chatModeLocalChat` only |
| Denizen: substring containment matching | **Rejected** — token/stem matching with anchors instead (§6.3) |
| AIML/RiveScript/ChatScript: normalize-then-match; `! sub` substitutions | §6.2 pipeline; contraction + synonym tables applied to both sides (§7.2) |
| ChatScript: `~concept` synonym sets | §7 `synonyms` classes in datapack JSON |
| ChatScript: topic keyword scoring ("number and length") + positional rules; anti-salience stance | Hybrid §6.3: scores route + threshold silence; patterns/system-precedence/margin rule keep selection deterministic |
| AIML: `<that>`/`topic` context in the match path | §6.7 `currentQuestion` scoping with server-known question ids |
| ChatScript: `![ not never … ]` negation guard | §6.2 negation window; negation-aware anti-keywords (§6.3) |
| AIML: non-greedy wildcards | §6.4 per-gap cap (4) + total window cap (12) |
| Villager Talk / Talking Villagers: proactive-only, randomized delays | Confirms greenfield; §8.4 humanized delay, §12 stagger |

---

## 3. Architecture overview

```
ServerChatEvent (Forge game bus, HIGH priority — fires on a BACKGROUND thread, §4)
        │  capture (player, raw text) only
        ▼
server.execute(...)  — main-thread hop
        │
        ▼
ChatModeDispatcher.handle(ServerPlayer, String rawMessage)   [server thread from here on]
        │  1. feature gate (enableChatMode && McaBridge.isAvailable())
        │  2. VillagerFinder.candidates(player, radius)  → List<TargetCandidate>
        │  3. Addressing.resolve(message, candidates, session) → target(s) + stripped message
        │  4. Normalizer.normalize(stripped)             → NormalizedMessage (tokens)
        │  5. IntentMatcher.match(tokens, IntentIndex, target, session) → ScoredIntent…
        │  6. GatePreview (constraints/feature flags) — pick best *eligible* intent
        │  7. ChatModeSession.open(player, villager)      → delivery redirect scope
        │  8. Dialogues.getInstance().selectAnswer(villager, player, questionId, answerName)
        │         └─ MCA engine: Result scoring → actions (say/next/positive/remember/
        │            conversations_say/conversations_record/…) — UNCHANGED
        │  9. NetworkHandlerMixin: InteractionDialogue*Response packets intercepted while
        │         session open → converted to chat Components → player.sendSystemMessage
        │ 10. session bookkeeping (stickiness, cooldowns, current question for follow-ups)
        ▼
   Villager line appears in vanilla chat:  <Agnes> Nothing broke and nobody borrowed
                                           anything. A shameful shortage of things to
                                           complain about.
```

Everything after the hop runs **server-side on the server thread** (`selectAnswer` is synchronous). No new packets, no client classes — matching the mod's existing architecture (the mod currently defines zero packets and reuses `ServerPlayer.sendSystemMessage` as its proven chat fallback in `McaCompat.sayInDialogue`).

### Two integration strategies considered

**A. Parallel dispatcher (rejected as primary).** Map keywords → lang phrase keys directly, re-implement gate checks via `McaCompat` (`getHearts`, `hasMemory`, `Dispositions.axis`, `CheckContextFactory`+`CheckResolver`), resolve lines with `McaCompat.getDialogueLine`, send with `sendSystemMessage`. *Pros:* zero new mixins, everything pure/testable. *Cons:* duplicates MCA's Result-scoring semantics (baseChance + matching condition chances, weighted random) which live in `data/*/dialogues/*.json`; every future dialogue file would need its logic mirrored twice; drift between GUI and chat answers is guaranteed over time.

**B. Engine reuse with delivery redirection (RECOMMENDED).** Reuse MCA's public `Dialogues.getInstance().selectAnswer(VillagerEntityMCA, ServerPlayer, questionId, answerName)` — the exact method MCA's own GUI button handler calls. It performs the full weighted Result selection (`Result.getChances(villager, player)` sums `baseChance` + matching condition chances, then `villager.getRandom().nextInt(total)`) and triggers the chosen `Actions` — which is where `say`, `next`, `positive`/`negative` hearts, `remember`, and all of this mod's registered custom actions (`conversations_say`, `conversations_record`, `conversations_disposition_apply`, `conversations_gossip_say`, `conversations_quest_open`) execute. Full parity is *automatic* because it is literally the same code path. The only difference: MCA's `say`/`next` deliver text via `NetworkHandler.sendToPlayer(new InteractionDialogueQuestionResponse(...))` / `InteractionDialogueResponse` — GUI packets that a client without an open interact screen ignores. Chat mode therefore wraps the `selectAnswer` call in a **delivery-redirect scope**: a soft-fail mixin on `forge.net.mca.cobalt.network.NetworkHandler.sendToPlayer` checks a server-thread scope object; while active for that player, dialogue-response packets are converted to chat lines instead of being sent (§8). This is one more small, `require = 0`, config-gated mixin — consistent with the mod's two existing soft-fail mixins (`DialoguesMixin`, `BreedableRelationshipMixin`) and its "no runtime patching of MCA except small soft-fail mixins" philosophy.

The matching engine (§6) then has one job: convert free text into the same `(questionId, answerName)` pair a button click would have produced. Everything downstream is untouched MCA + existing Conversations code.

---

## 4. Chat interception (Forge 1.20.1)

**Hook:** `ServerChatEvent` on the Forge game bus, subscribed in `event/ConversationsEvents.java` alongside the existing handlers, following the established early-out pattern:

```java
@SubscribeEvent(priority = EventPriority.HIGH)
public static void onServerChat(ServerChatEvent event) {
    if (!McaBridge.isAvailable()) return;
    if (!McaConversationsConfig.COMMON.enableChatMode.get()) return;
    ChatModeDispatcher.onChat(event); // captures (player, raw text) and hops threads — see below
}
```

**⚠ Threading (verified against Forge 47 / 1.20.1 source):** `ServerChatEvent` is fired from Forge's `ChatDecorator` inside `CompletableFuture.supplyAsync(..., Util.backgroundExecutor())` — i.e. on a **background thread, not the server main thread**. The handler must therefore do *no* level/entity access in-line. Contract:

```java
public static void onChat(ServerChatEvent event) {
    ServerPlayer player = event.getPlayer();
    String raw = event.getRawText();                    // plain String — safe to capture
    player.getServer().execute(() ->                    // hop to main thread
        ChatModeDispatcher.handle(player, raw));        // §3 pipeline runs here
}
```

Inside `handle`, first re-validate `player.hasDisconnected() == false` and `player.isAlive()`. All entity queries, session mutation, `selectAnswer`, and delivery happen on the main thread; the villager's reply arriving a tick after the player's message is indistinguishable from the humanized delay (§8.4) anyway. Event *cancellation/mutation* would have to happen synchronously on the background thread — one more reason the default path never cancels (see `chatModeLocalChat` note below).

Rules, informed by 1.19+ chat-signing constraints (§2.4):

- **Never cancel or mutate the event by default.** The player's message goes to global chat as normal; villagers *respond* to it. Canceling signed player chat in 1.20.1 triggers the "message could not be validated" / red-signing client warnings and breaks chat-report chains; mutating `event.setMessage` on signed messages causes the same class of problems. Read-only interception sidesteps the entire chat-signing minefield.
- An optional `chatModeLocalChat` config (default **off**, marked experimental) turns player chat into proximity speech: cancel the event and re-broadcast the player's line only to players within `chatModeRadius`. Because cancellation must be decided synchronously on the background thread (above), the decision **cannot depend on entity queries** — so the semantic is "all chat becomes local" (like dedicated proximity-chat mods), not "villager-directed chat becomes local": `event.setCanceled(true)` unconditionally, then a main-thread task broadcasts `<name> message` to players within radius. Document in the config comment that this downgrades player messages to unsigned system messages (disables client-side chat reporting/filtering for them) — acceptable because it is opt-in and server-wide. **Phase 4, not MVP** (§15).
- Ignore: messages starting with `/` (commands never reach ServerChatEvent anyway, but guard defensively), empty/whitespace, messages from spectators, messages from players who haven't opted in (`/conversations chat off`), and messages to villagers under an active mute (§11, "stop talking").
- The handler must be **cheap on the hot path**: bail before any entity query if the message can't possibly be for a villager? No — any message *can* be (proximity broadcast). The cheap pre-check is the AABB entity query itself, which at radius ≤ 16 on one player-centered box per chat message is negligible (chat messages are low-frequency events; this is not a tick handler).
- All downstream work is wrapped `try/catch (Throwable)` with a rate-limited warn log, per the codebase's universal fail-safe convention: a chat-mode bug must never eat or delay player chat.

**Opt-in per player (server-level feature, player-level choice):** chat mode being enabled in config makes it *available*; each player can toggle it for themselves with `/conversations chat on|off|status` (extends the existing `ConversationsCommand`). Per-player opt-in state is stored in the player's persistent NBT (`getPersistentData()` is not persisted across death — use a small `SavedData` map or the existing capability pattern (`GiftMemoryProvider` precedent) — implement as `ChatModePlayerState` attached alongside gift memory). Default for new players: `chatModeDefaultOn` config (default `false`).

---

## 5. Villager targeting & addressing

New utility `chat/VillagerFinder.java` (there is **no existing proximity utility** in the codebase — `WorldQuery` is a weather/season condition record, not spatial):

```java
static List<VillagerCandidate> candidates(ServerPlayer player, double radius) {
    return player.serverLevel().getEntitiesOfClass(
            LivingEntity.class,                       // resolved via McaCompat.isMcaVillager
            player.getBoundingBox().inflate(radius),
            e -> McaCompat.isMcaVillager(e) && e.isAlive() && !isSleeping(e))
        .stream()
        .map(e -> new VillagerCandidate(e, e.distanceToSqr(player),
                                        lookDot(player, e),            // §5 tier-3 look-at cosine
                                        McaCompat.getVillagerName(e)))
        .sorted(Comparator.comparingDouble(VillagerCandidate::distSqr))
        .limit(16)
        .toList();
}
```

*(Keep MCA types out of `chat/` — go through `McaCompat` for `isMcaVillager`/`getVillagerName`; accept `LivingEntity`/`Entity` in chat-package signatures, mirroring how the rest of the codebase isolates MCA behind `compat/`.)*

### Target resolution (`chat/Addressing.java`, pure & unit-testable)

Given the raw message, the candidate list (with names), and the player's session, resolve in priority order:

1. **Name address (explicit, highest).** Leading vocative: `^\s*(name)[,:;!.\s]` or trailing `,\s*(name)[.!?]*$`. Name matching is case-insensitive against each candidate's MCA name, first token of the name, and a fuzzy match (Jaro-Winkler ≥ 0.90 on the leading token — tolerates `Agnes`→`Anges`). If a name matches, that villager is the sole target **even if farther than others**, provided within `chatModeAddressedRadius` (larger than the ambient radius; you can call out to someone across the square). The matched vocative is **stripped** from the message before intent matching. If the named villager is not in range → soft failure line (§11).
2. **Reply pronoun + stickiness.** If the message contains second-person address with no name (`you`, `your`) *or* the player has an **active session** (§9) with a villager still in range whose `lastExchange` is within `chatModeStickinessTicks`, the sticky villager is the target. This is what makes multi-turn exchanges (`fears` → `"You could face it. I'd stand with you."`) work without re-addressing.
3. **Look-at.** Ray-cast: among candidates, the villager within `chatModeLookConeDegrees` (default 25°) of the player's view vector and within radius, nearest angular deviation first. (Compute via normalized `dot(lookVec, (villagerEyePos - playerEyePos).normalize())`; no block-raycast needed — talking through a window is fine and cheaper.)
4. **Nearest / broadcast.** No name, no sticky session, nobody in the look cone: the message is **ambient**. Every candidate scores it against their own intent index; responders are selected by relevance (§12). This is the "shout in the town square and whoever it applies to answers" mode — capped by `chatModeMaxResponders`.

Precedence conflicts resolve strictly 1 > 2 > 3 > 4. A name address always breaks stickiness (and re-sticks to the newly named villager on success).

---

## 6. The matching engine (non-AI NLU)

The matcher converts a normalized player message into the best `(questionId, answerName)` binding, or *no match*. It is a hybrid of the three families that survived four decades of non-ML chatbots (§2): **pattern/wildcard matching** (ELIZA/AIML/RiveScript) for high-precision phrasings, **weighted keyword scoring with IDF-style specificity** (retrieval-lite) for recall on free paraphrase, and **topic-scoped context** (MUD/IF `ask X about Y`, AIML `<that>/<topic>`) for multi-turn flow. All stages are pure Java over plain data (✦, §13) — deterministic given (message, index, session snapshot).

### 6.1 Design tenets

1. **Precision over recall for ambient messages, recall over precision for addressed ones.** A villager wrongly butting into player banter is worse than a villager missing a mumbled question; a directly-addressed villager failing to answer is worse than occasionally guessing the topic. Two thresholds (§10) encode this.
2. **Data over code.** Keywords, phrases, synonyms, anti-keywords live in datapack JSON (§7). Java knows *how* to score, never *what* a topic sounds like. Modpacks can extend matching without touching the mod.
3. **Deterministic & explainable.** No randomness in matching (randomness stays where it already lives — MCA's Result selection). Every decision reproducible in `/conversations chat debug`, which prints per-intent score breakdowns.
4. **Fail closed.** When unsure, prefer the in-character confused line (§11) over a wrong topic. Wrong answers teach players the system is broken; confused lines teach them how to phrase.

### 6.2 Normalization pipeline (`chat/Normalizer.java` ✦)

Ordered stages, each trivially unit-testable:

1. **Fold**: lowercase via `Locale.ROOT`; Unicode NFKD + strip combining marks (`café` → `cafe`); map curly quotes/dashes to ASCII.
2. **De-noise**: strip emoji/symbols; collapse ≥ 3 repeated letters to 2 (`heyyyy` → `heyy` — then dictionary pass below still hits `hey`); trim.
3. **Contractions**: table-driven expansion before punctuation strip (`how's` → `how is`, `whats` → `what is`, `dont` / `don't` → `do not`, `im` → `i am`, `hows` → `how is`, …). Table lives in code (small, closed set), ~40 entries.
4. **Tokenize**: split on non-alphanumerics (apostrophes already consumed); drop empty tokens; cap at 32 tokens (longer messages truncate — nobody addresses a villager in 33+ words, and it bounds worst-case cost).
5. **Negation tagging**: negator set `{not, no, never, none, nobody, nothing, hardly, without}` (post-expansion, `don't` is already `do not`). A negator marks the following window of up to 3 content tokens as `negated=true`, window broken by punctuation-derived clause boundaries (tracked pre-strip), coordinating conjunctions (`but, and, or`), or another negator.
6. **Stopword flagging** (not removal): `{the, a, an, i, me, my, we, us, it, its, is, are, was, be, been, of, to, in, on, at, for, with, and, or, so, well, um, uh, please, hey, ok, okay, just, really, very}` — flagged so scoring ignores them as *content*, but pattern matching (6.4) can still see them positionally. Question words (`how, what, why, when, where, who, do, does, did, can, could, would, will, tell, about`) are **kept as features** — they carry intent shape.
7. **Stem** (light, suffix-only — a deliberate subset of Porter): strip `'s`; `-ies`→`y`; `-es`/`-s` (guard: length ≥ 4, not `-ss`); `-ing` (guard: length ≥ 6, restore silent-e per small exception list: `having`→`have`); `-ed` (length ≥ 5). Applied to both message tokens and index keywords at load, so both sides stem identically. No dictionary, no lemmatization — mismatches are absorbed by fuzzy matching, not by heavier stemming.
8. **Synonym canonicalization**: the index's alias table (§7, `synonyms`) maps stems to a canonical stem at load time and at query time (`job|trade|profession|occupation` → `work`; `frightened|scared|afraid` → `fear`; `hi|hiya|heya|greetings|hello|hey|yo` → `hello`). One level deep, applied once — no recursive rewriting.

Output: `NormalizedMessage { List<Token> tokens }`, `Token { String stem; boolean stop; boolean negated; int pos }` plus derived `Set<String> contentStems`, `Set<String> bigrams` (consecutive non-stop stem pairs), `boolean interrogative` (leading question word or trailing `?` pre-strip).

### 6.3 Intent scoring (`chat/IntentMatcher.java` ✦)

At index load, compute per-stem **specificity** across all intents, IDF-style: `idf(t) = 1 + ln(N / df(t))` where `N` = intent count and `df(t)` = number of intents whose keyword set contains `t`. A stem like `village` (appearing in several village intents) contributes less than `regret` (unique). Each intent's **norm** is the sum of its top-6 keyword `weight·idf` values (capping the norm keeps 20-keyword intents from being unreachable).

For each intent `I` against message `M`:

```
kw     = Σ over matched keywords k of  w(k) · idf(k) · matchQuality(k)
         where matchQuality = 1.0 exact-stem, 0.8 fuzzy (6.5), 0 if token negated
base   = min(1, kw / norm(I))                            // keyword evidence, 0..1
phrase = +0.35 per matched phrase pattern (6.4), cap +0.50
bigram = +0.05 per matched intent bigram, cap +0.10
anti   = −0.30 per matched anti-keyword (negation-aware: a negated anti-keyword doesn't fire)
ctx    = +0.25 if I is scoped to session.currentQuestion (6.7)
cover  = −0.25 · max(0, unmatchedContentRatio − 0.6)     // long messages mostly about
                                                          // something else don't match
score  = clamp01(base + phrase + bigram + anti + ctx + cover)
```

Guards evaluated before scoring (cheap rejects): `requiresAll` stems all present; `requiresAny` at least one present (most intents set `requiresAny` to their 2–3 anchor stems — this is the primary false-positive firewall and makes scoring O(intents-with-an-anchor-hit) via an inverted index `stem → intents`, not O(all intents)).

**Coverage term rationale:** `unmatchedContentRatio` = fraction of non-stop message stems that matched nothing in `I` (keywords, phrases, or synonyms). `"how is your day"` → 0 unmatched → no penalty. `"anyone know where the nether fortress is btw how was your day"` → high unmatched → penalized below ambient threshold; an *addressed* villager may still answer (lower threshold), which is the right call for a rambling but direct question.

### 6.4 Phrase patterns

Each intent may declare ordered patterns in a deliberately tiny wildcard grammar (an AIML/RiveScript subset — one construct, no priorities to reason about):

```
"how * day"        →  stems must appear in order; * = 0–4 arbitrary tokens between
"do you like *job" →  *job = any stem in the 'job' synonym class
"tell me about your family"
```

Matching is a greedy ordered-subsequence scan over the full token list (stop tokens included, so `how is your day` matches `how * day`) with per-gap cap 4 and total-window cap 12. Patterns are compiled at load to stem arrays; no regex at query time. Patterns exist for precision (they boost, never gate) — an intent with only keywords still works.

### 6.5 Fuzzy token matching

Per-token typo absorption, applied only when an exact stem lookup misses and the token has length ≥ 4: a message stem `m` fuzzy-matches keyword stem `k` if `damerauLevenshtein(m, k) ≤ 1` for length 4–6, `≤ 2` for length ≥ 7, with first letter required equal (classic spell-check heuristic: initials rarely mistyped; also keeps `work`≠`fork`). Implement Damerau-Levenshtein with early-exit banding (O(len·maxDist)); candidates come from the inverted index bucketed by (first letter, length ± edit bound), so fuzzing is a handful of comparisons per token, not index-wide. Fuzzy hits score `matchQuality = 0.8` and never satisfy `requiresAll`/`requiresAny` guards on stems shorter than 5 (anchors must be nearly right). Villager-name fuzzing in §5 uses Jaro-Winkler ≥ 0.90 instead (names are short, transposition-prone, prefix-weighted); both metrics live in one `chat/Fuzzy.java` ✦ utility.

### 6.6 Eligibility preview & selection (`chat/GatePreview.java` + dispatcher logic)

MCA's `selectAnswer` **does not re-validate answer `constraints`** — in the GUI, constraints filter which buttons render (`InteractionDialogueResponse`'s constructor computes the applicable answers); the server trusts the click. Chat mode must therefore enforce constraints itself or it would let anyone trigger spouse-only content. For each candidate intent, `GatePreview.eligible(villager, player, binding)`:

1. **Constraints** (hard): `McaCompat.checkConstraints(villager, player, questionId, answerName)` — verified MCA 1.20.1 API: resolve the `Question` via `Dialogues.getInstance().getQuestion(id)`, find the `Answer` by name, compute `Set<Constraint> satisfied = Constraint.allMatching(villager, player)` (`net.mca.entity.interaction.Constraint`; negations like `!spouse` are their own enum constants, so no extra handling), and return `answer.isValidForConstraint(satisfied)` (a `containsAll` check). Failing → intent ineligible; matching proceeds to the next-ranked intent (a non-spouse asking "are you happy with us?" falls through to, e.g., `feelings` or the confused line, rather than reaching spouse content).
2. **Feature flags** (hard): the binding's category flag (`isFeatureEnabled("topics")` etc.) and `enableChatMode` recheck.
3. **Heart/memory/check gates are NOT previewed** (soft): these live inside Result conditions, and the engine already resolves them to in-character deflections with full parity (§8.1). Previewing them would double-implement the exact logic §3 chose to avoid.

Selection over the ranked eligible list:

- Addressed/sticky/look-at target: accept top intent if `score ≥ chatModeMinScore`.
- Ambient: accept if `score ≥ chatModeAmbientMinScore`.
- **Margin rule**: if the top two eligible intents bind to *different questions* and `top1 − top2 < 0.10`, treat as ambiguous → clarification line (§11) naming both topics (addressed targets only; ambient ambiguity = silence). If they bind to the same question (e.g. two chit-chat intents), take top1 — the cost of being wrong is trivial.
- Greeting/farewell short-circuit: messages of ≤ 3 content tokens are first checked against the small-utterance table (`hello`, `bye`, `thanks`, `yes`, `no` classes) before full scoring; this keeps one-word messages away from the coverage math and gives `hi Agnes` a guaranteed greet.
- **System-intent precedence**: `_system.json` intents (§7) score like any other but win ties and near-ties (margin < 0.10) against topic intents — a decline or mute request must never be misread as topic interest.

### 6.7 Context scoping (multi-turn)

Intents may declare `"context": "conversations.fears"` — they only enter scoring when `session.currentQuestion` equals that id (and score the `ctx` bonus). This is how CRPG-style stances work in chat: after `fears` opens (`next: "conversations.fears"` recorded by the redirect mixin), the scoped intents for `comfort` / `challenge` / `press` / `share` become live, e.g. *"You could face it. I'd stand with you."* → `press`. Context intents are checked with threshold `chatModeMinScore − 0.10` (the conversation frame justifies looser reading, mirroring AIML's `<that>`-scoped categories and IF's current-topic disambiguation). Global intents remain live simultaneously — a subject change wins if it outscores the scoped set, and clears `currentQuestion` (§9). Category-hub questions (`conversations`, `conversations.cat.*`) are **never** recorded as context: they are menus, and chat mode's entire premise is that menus dissolve into free address.

### 6.8 Worked examples (normative)

| Player types | Resolution |
|---|---|
| `Agnes, how's your day going?` | Vocative `agnes` → tier-1 target. Normalize → `how is your day go` (+interrogative). `chitchat.day`: anchor `day` hit, pattern `how * day` +0.35, base ≈ 0.9 → far over threshold → `selectAnswer(V, P, "conversations.cat.chitchat", "day")`. Engine applies cooldown/mood/child Results exactly as GUI. |
| `do you actually like your job?` (looking at the smith) | Tier-3 look-at. Synonym `job→work`; `profession.work` anchors hit; pattern `like *job` +0.35 → `("conversations.cat.profession","work")` → auto-question picks the profession-flavored line. |
| `heard any rumors lately??` | Sticky target if present else nearest/ambient. `village.rumors` anchors (`rumor` stem via `-s` strip) → `("conversations.cat.village","rumors")` → `conversations_gossip` condition + `conversations_gossip_say` run engine-side; if no untold gossip, the engine's own "quiet lately" Result answers. |
| `what are you afraid of, Ilsa?` | Trailing vocative. Synonyms `afraid→fear`. Heart gate **not** previewed; below 25 hearts the engine deflects in character — parity. Else `next: conversations.fears` records context; stance intents go live. |
| `you could face it. i'd stand with you` (10 s later) | Sticky + context `conversations.fears`; scoped intent `fears.press` pattern `stand with you` → check runs engine-side (seeded — no chat-vs-GUI reroll exploit, same `CheckSeed` bucketing). |
| `anyone selling emeralds?` | Ambient tier-4. `selling/emeralds` match no intent anchors; every score ≪ ambient threshold → **silence** (asserted by anti-case tests). |
| `nice house lol` | Ambient; `house` may weakly brush `village.home` keywords but fails `requiresAny` anchors + coverage → silence. |
| `I don't want to talk about the weather` | `weather` anchor hits but token is negation-tagged → contributes 0; the `chatmode.decline` system intent's phrase `do not want to talk about` (§7) matches and system intents outrank topic intents → if addressed, the acknowledged-decline line `chatmode.dropped`; if ambient, silence. |

### 6.9 Complexity & performance budget

Per message: normalization O(n tokens) with n ≤ 32; anchor lookup via inverted index touches only intents sharing ≥ 1 stem (typically < 10 of ~45); each scored intent is set-intersection over ≤ 32 stems plus ≤ 4 pattern scans. Everything allocates small short-lived objects; zero reflection anywhere (all intercepted MCA packet fields are public — §8.2). Budget < 0.5 ms per chat message on commodity hardware; a JMH-style micro-benchmark is not required, but the debug command prints elapsed µs for tuning.

---

## 7. Intent data format (datapack JSON)

**Location:** `data/<namespace>/chat_intents/*.json`, loaded by `ChatIntentLoader` (a `SimpleJsonResourceReloadListener` registered via `AddReloadListenerEvent`), merged across namespaces exactly like MCA merges `dialogues/` — so modpacks and other addons can add or override intents by shipping a datapack. File name = intent group; intents keyed by id. Every entry parses through `SafeParse`: a malformed intent logs one warning and is skipped; the reload never fails.

### 7.1 Schema

```jsonc
// data/mcaconversations/chat_intents/chitchat.json
{
  "synonyms": {                       // group-local synonym classes (merged globally at load;
    "work": ["job", "trade", "profession", "occupation", "craft"],
    "hello": ["hi", "hiya", "heya", "hey", "greetings", "yo"]
  },
  "intents": {
    "chitchat.day": {
      "question": "conversations.cat.chitchat",   // MUST exist as a dialogue question id
      "answer": "day",                            // MUST exist in that question's answers[]
      "keywords": {                               // stem: weight (1.0 default; anchors get 1.5)
        "day": 1.5, "today": 1.2, "going": 1.0, "morning": 0.8, "doing": 0.8
      },
      "requiresAny": ["day", "today", "doing"],   // anchor firewall (§6.3)
      "phrases": ["how * day", "how are you doing", "how is it going"],  // stemmed at load (§7.2)
      "antiKeywords": ["birthday"],               // "is it your birthday today" ≠ day topic
      "bigrams": ["your day"],
      "category": "chitchat"                      // ties into isFeatureEnabled("topics") etc.
    },
    "chitchat.weather": {
      "question": "conversations.cat.chitchat",
      "answer": "weather",
      "keywords": { "weather": 1.5, "rain": 1.2, "storm": 1.2, "sunny": 1.0, "sky": 0.8, "cold": 0.6, "hot": 0.6 },
      "requiresAny": ["weather", "rain", "storm", "sunny", "sky"],
      "phrases": ["nice weather", "lovely day out", "some storm"]
    }
  }
}
```

```jsonc
// data/mcaconversations/chat_intents/personal.json (excerpt)
{
  "intents": {
    "personal.fears": {
      "question": "conversations.cat.personal",
      "answer": "fears",
      "keywords": { "fear": 1.5, "afraid": 1.5, "scare": 1.2, "worry": 0.8, "nightmare": 0.8 },
      "requiresAny": ["fear", "afraid", "scare"],
      "phrases": ["what are you afraid of", "what scares you", "your fears"]
    },
    "fears.press": {
      "context": "conversations.fears",           // scoped: only live mid-conversation (§6.7)
      "question": "conversations.fears",
      "answer": "press",
      "keywords": { "face": 1.2, "stand": 1.2, "with": 0.4, "brave": 1.0, "overcome": 1.2, "beside": 1.0 },
      "requiresAny": ["face", "stand", "overcome", "brave"],
      "phrases": ["stand with you", "face it", "i would stand"]
    },
    "fears.share": {
      "context": "conversations.fears",
      "question": "conversations.fears",
      "answer": "share",
      "keywords": { "too": 0.6, "same": 1.0, "also": 0.8, "me": 0.4, "understand": 1.0 },
      "phrases": ["i am afraid too", "me too", "i understand"]
    }
  }
}
```

```jsonc
// data/mcaconversations/chat_intents/_system.json — reserved ids, no question binding
{
  "intents": {
    "chatmode.greeting":  { "system": "greet",    "keywords": { "hello": 1.5 },
                            "phrases": ["good morning", "good evening"] },
    "chatmode.farewell":  { "system": "farewell", "keywords": { "bye": 1.5, "goodbye": 1.5, "farewell": 1.2 },
                            "phrases": ["see you", "got to go", "take care"] },
    "chatmode.silence":   { "system": "mute",     "phrases": ["stop talking", "be quiet", "leave me alone",
                                                              "do not talk to me"] },
    "chatmode.decline":   { "system": "drop",     "phrases": ["do not want to talk about",
                                                              "never mind", "forget it", "drop it"] }
  }
}
```

### 7.2 Rules

- `question`/`answer` bindings are **validated by `ChatIntentLintTest`** against the shipped `dialogues/*.json` at build time and by `ChatIntentLoader` at reload (unknown binding → intent skipped + one log line). `greet` binds to MCA's own `greet.json` (extended by this mod) — `chatmode.greeting` maps to `("greet","checkin")` via the `system: "greet"` handler so greeting cooldown parity holds.
- `system` intents route to dispatcher behaviors instead of `selectAnswer`: `greet` (drive greet question), `farewell`/`mute`/`drop` (§11). `system` and `question` are mutually exclusive.
- Keywords/phrases are **stemmed at load** with the same Normalizer, so authors write natural words (`scares`, `going`) and matching stays consistent.
- Synonym classes merge globally; a duplicate synonym mapping to two different canonicals is a lint failure (first-wins at runtime with a warning).
- Intent ids namespaced by file convention (`<group>.<name>`); overriding an intent = shipping the same id in a higher-priority datapack (standard datapack ordering; last reload wins, mirroring MCA dialogue merge semantics).
- **Coverage requirement for launch content:** one intent per reachable answer in §"Topic inventory" (the `conversations.cat.*` answers, sub-question stances for `fears`/`dreams`/`feelings`/`us`/`family`, plus greet) — ~40 intents. `back` answers get **no** intents (menu navigation is meaningless in chat).

---

## 8. Dialogue execution & delivery

### 8.1 Driving the engine

Once the matcher produces a winning `IntentBinding { questionId, answerName }` for target villager V and player P:

```java
try (ChatModeSession.Scope scope = ChatModeSession.open(player, villager)) {
    McaCompat.selectAnswer(villager, player, binding.questionId(), binding.answerName());
}
```

`McaCompat.selectAnswer` is a new compat wrapper (all MCA types stay behind `compat/`):

```java
/** Drives MCA's dialogue engine exactly as a GUI button click would. False on any failure. */
public static boolean selectAnswer(Entity villager, ServerPlayer player,
                                   String questionId, String answerName) {
    try {
        forge.net.mca.resources.Dialogues.getInstance()
            .selectAnswer((VillagerEntityMCA) villager, player, questionId, answerName);
        return true;
    } catch (Throwable t) { warnOnce("selectAnswer", t); return false; }
}
```

This executes, unmodified: Result scoring (`baseChance` + matching condition `chance` sums, weighted random via `villager.getRandom()`), then the chosen Result's `Actions.trigger(villager, player)` — hearts (`positive`/`negative`), `remember`, `say`, `next`, and every custom action this mod registered (`conversations_say` templating, `conversations_record` cooldowns, `conversations_disposition_apply`, `conversations_gossip_say`, `conversations_quest_open`). **Gating parity is therefore structural, not re-implemented**: the same `hearts_max`/`memory`/`conversations_check`/`conversations_disposition` conditions weight the same Results. A player below 25 hearts asking about `life` gets the same in-character deflection Result in chat that they would get in the GUI, because MCA picks it with the same math.

### 8.2 Delivery redirection (the one new mixin)

MCA's `say`/`next` actions deliver text as S2C GUI packets: `NetworkHandler.sendToPlayer(new InteractionDialogueQuestionResponse(...))` (the villager's line) and `InteractionDialogueResponse` (the next question prompt + buttons). With no interact screen open these are useless to the client. Add **`mixin/NetworkHandlerMixin`** (`@Mixin(targets = "forge.net.mca.cobalt.network.NetworkHandler", remap = false)`, `require = 0`, injected at `HEAD` of `sendToPlayer` with cancellation):

- If `ChatModeSession.activeFor(player)` is false → no-op (GUI flow byte-identical to today).
- If active and packet is `InteractionDialogueQuestionResponse` → take its text via the **public** `getQuestionText()` (the class exposes `public final String questionText` — a JSON-serialized `Text` — plus `public final boolean silent`; despite the field name, MCA's `say` action ships the villager's *line* in this packet). Hand the `MutableComponent` to `ChatDelivery.villagerSays(villager, player, component)`, **cancel** the send. Respect `silent`.
- If active and packet is `InteractionDialogueResponse` (question prompt/buttons; **public fields** `String question`, `List<String> answers` — the answer list is already constraint-filtered by the packet's constructor) → record both on the session (`session.currentQuestion = question; session.currentAnswers = answers`) for follow-up matching (§9), **cancel** — chat mode never renders menus. The captured `currentAnswers` doubles as a context-intent validity filter: a scoped intent whose `answer` isn't in the list can't fire.
- If active and packet is `AnalysisResults` (the heart-impact analysis strip shown in the GUI) → cancel silently (chat shows outcomes through the villager's words, as CRPGs do in prose).
- Any other packet type → pass through untouched (gift responses, village packets, etc. must not be affected).
- The scope is opened and closed on the **server thread only** (the dispatcher always runs inside `server.execute` — §4 — and `selectAnswer` is synchronous); implement as a plain static field with a `server.isSameThread()` assertion — no concurrency machinery needed. `Scope` is `AutoCloseable`; `close()` always runs (try-with-resources) so a throwing action can never leave delivery redirected.
- If the mixin failed to apply (`require = 0` — MCA reshaped internals), `ChatModeSession.redirectionAvailable()` returns false and chat mode falls back to **Strategy A-lite**: resolve deflection-free simple topics via `McaCompat.getDialogueLine` + `sendSystemMessage` only, and log one warning that full engine parity is unavailable. (Keeps the mod alive across MCA drift, per house style.)

### 8.3 Chat formatting (`chat/ChatDelivery.java`)

Villager lines are delivered with `player.sendSystemMessage(component)` — the exact call already proven chat-safe by `McaCompat.sayInDialogue`'s fallback path. Formatting:

```
<Agnes> Nothing broke and nobody borrowed anything. A shameful shortage of things to complain about.
```

- Assembled via `chatModeMessageFormat` (§10): `%1$s` = villager name from `McaCompat.getVillagerName` in a distinct color (`ChatFormatting.YELLOW`), `%2$s` = the resolved line Component appended (never string-flattened). One template string covers both `<Agnes> line` and `Agnes: line` styles.
- The `Component` from MCA is already fully resolved: personality overlay (`mca_dialogue_grumpy` etc.), player's chosen MCA name in `%1$s`, template args (`%2$s+`) from `conversations_say`. **Never** re-resolve or flatten to a raw string; forward the Component so client-side lang and styling survive.
- Emotional/system beats that the GUI conveys visually (a rebuffed check, a mood shift) are *not* annotated in chat by default; `chatModeShowHeartChanges` (default off) may append a subtle suffix like ` (+2 ♥)` in gray for players who want feedback.
- **Bystanders:** by default only the speaking player receives the response (whisper model — matches GUI privacy). With `chatModePublicReplies = true`, the line is also sent to other players within `chatModeRadius` of the *villager* (roleplay-server mode), via the same `sendSystemMessage` loop. Villager replies are system messages, so no chat-signing implications either way.

### 8.4 Response latency

Instant, single-line replies feel robotic and can flood chat when multiple villagers respond. Delay each delivery by a small humanized tick count: `chatModeReplyDelayTicks` base (default 15) + 1 tick per 4 characters of the resolved line, capped at 60. Implementation: enqueue `(deliverAtTick, Runnable)` into a `ChatModeScheduler` drained from the existing `onServerTick` handler (`Phase.END`, alongside the gossip scan). The *engine* (selectAnswer, state writes) runs immediately at match time — only the **delivery** is deferred, so ordering with subsequent player messages stays coherent; multiple responders get staggered delays (§12).

---

## 9. Conversation session state

`chat/ChatModeSession.java` — one lightweight session per player (server-side map `UUID player → Session`, cleaned on logout/death and by TTL sweep in the tick handler):

```java
final class Session {
    UUID villagerId;          // sticky target
    long lastExchangeGameTime;// stickiness window anchor
    String currentQuestion;   // set by redirected InteractionDialogueResponse (e.g. "conversations.fears")
    List<String> currentAnswers; // constraint-filtered answers captured from the same packet
    int consecutiveMisses;    // for graduated fallback (§11)
    long mutedUntilGameTime;  // "stop talking" / villager walked away
}
```

Semantics:

- **Stickiness**: after a successful exchange with villager V, V remains the default target for `chatModeStickinessTicks` (default 600 = 30 s), refreshed on every exchange. Broken by: naming another villager, V dying/unloading, player or V leaving `chatModeAddressedRadius`, or explicit farewell (`bye`, `see you`, `farewell` → intent `chatmode.farewell`, which also triggers a goodbye line via `greet`-style content).
- **`currentQuestion` (conversation depth)**: when a Result's `next` points to a sub-question (`conversations.fears`, `conversations.dreams`, `conversations.feelings`, `conversations.us`, `conversations.family`), the redirect mixin records it. While set (and sticky target unchanged), the matcher (§6) considers that sub-question's answers **first and at a scoring bonus** — so after the villager opens up about fears, `"You could face it — I'd stand with you."` matches the `press`/`challenge` stance rather than being misread as small talk. If nothing in the sub-question matches, matching falls through to the global index (the player changed the subject), and `currentQuestion` clears. It also clears on stickiness expiry, and when a Result `next`s back to a category/hub question (categories are menus; meaningless in chat).
- Sessions are transient (not persisted). Long-term consequences (cooldowns, unlocks, hearts, dispositions) already persist through MCA memories and this mod's SavedData — a relog mid-conversation loses only the sticky pointer, matching how a GUI screen close behaves.
- **Busy/interaction guard**: if the villager is currently in a GUI interaction with *another* player, chat mode responds with a busy deflection (`chatmode.busy` line: *"Give me a moment — I'm in the middle of something."*) rather than driving the engine concurrently. Verified API: `EntityCommandHandler.getInteractingPlayer()` returns an `Optional` already filtered to players with an open screen — wrap as `McaCompat.isInteractingWith(villager) → Optional<UUID>`, fail-open (empty on any Throwable).

---

## 10. Configuration

All options in `McaConversationsConfig.Common`, new `b.push("chat") … b.pop()` section, following house conventions (camelCase, `enable*` feature flags, `*Ticks` durations, every value `.comment(...)`ed). Also add `case "chat" -> COMMON.enableChatMode.get();` to `isFeatureEnabled` so datapack content can gate on `conversations_enabled: "chat"`.

> **Defaults changed in 0.8.0 (post-spec, user decision):** `enableChatMode`, `chatModeDefaultOn`,
> `chatModePublicReplies`, `chatModeShowHeartChanges`, `chatModeLocalChat`, and
> `chatModeGreetOnApproach` now default **`true`** — chat mode ships on. The table below preserves
> the original design-time (dark-launch) defaults.

| Option | Type / default | Meaning |
|---|---|---|
| `enableChatMode` | bool `false` | Master switch. Off = zero behavior change, no chat listener work. |
| `chatModeDefaultOn` | bool `false` | Whether players are opted in before running `/conversations chat on`. |
| `chatModeRadius` | double `12.0` (1–64) | Ambient hearing radius (blocks) for unaddressed messages. |
| `chatModeAddressedRadius` | double `24.0` (1–96) | Radius when the villager is named or sticky ("calling out"). |
| `chatModeStickinessTicks` | int `600` (0–72000) | How long the last conversation partner stays the default target. |
| `chatModeLookConeDegrees` | double `25.0` (0–90) | Half-angle of the look-at targeting cone. 0 disables look-at. |
| `chatModeMaxResponders` | int `2` (1–5) | Max villagers that may answer one ambient message. |
| `chatModeMinScore` | double `0.55` (0–1) | Confidence threshold for addressed messages (§6). |
| `chatModeAmbientMinScore` | double `0.75` (0–1) | Stricter threshold for ambient/broadcast messages ("remotely applicable" still needs to be clearly *some* topic — eavesdropping villagers shouldn't misfire on player-to-player chat). |
| `chatModeReplyDelayTicks` | int `15` (0–100) | Base humanized reply delay. |
| `chatModeCooldownTicks` | int `40` (0–1200) | Per-player global floor between processed messages (anti-spam; §11). |
| `chatModePublicReplies` | bool `false` | Villager replies visible to nearby players, not just the speaker. |
| `chatModeShowHeartChanges` | bool `false` | Append subtle `(+2 ♥)`-style feedback to lines. |
| `chatModeMessageFormat` | string `"<%1$s> %2$s"` | Chat line template: `%1$s` = villager name (colored), `%2$s` = the line. Servers preferring roleplay style set e.g. `"%1$s: %2$s"`. |
| `chatModeMuteTicks` | int `6000` (200–72000) | Duration of a "stop talking" mute per villager→player pairing (§11). |
| `chatModeInsultDetection` | bool `true` | Map obvious insults to an in-character rebuke + ANNOYED state (§11). |
| `chatModeLocalChat` | bool `false` | EXPERIMENTAL: cancel + rebroadcast player chat only within radius (unsigned; see §4). Phase 4. |
| `chatModeGreetOnApproach` | bool `false` | Villagers proactively greet an opted-in player entering the radius (rate-limited by the existing `greet.today` cooldown memory). Phase 4. |

Client spec remains empty — there is deliberately no client-side surface.

---

## 11. Failure, deflection & anti-spam behavior

Non-AI matchers *will* miss; the difference between charming and broken is what happens next. Chat mode's failure behavior follows the MUD/IF playbook (§2.3): stay in character, teach the vocabulary, and never stack repeated identical failures.

**Graduated confusion (addressed messages only).** Tracked per session via `consecutiveMisses`:

1. First miss: personality-voiced confusion — `dialogue.chatmode.confused` (+ `/1 /2` variants; overlays give the grumpy smith *"Speak plainly or let me work."* and the peppy farmer *"Ooh, you lost me! Say it another way?"*).
2. Second consecutive miss: confusion **plus a menu-in-prose hint** assembled from the villager's currently *eligible* categories: `dialogue.chatmode.hint` with `%2$s` = localized topic list, e.g. *"You could ask about my work, the village, or how my day's been."* (Assemble from GatePreview-eligible category names; this is the chat-mode equivalent of the GUI's buttons and the IF convention of `topics`/`talk to` prompting.)
3. Third+: villager disengages softly — `dialogue.chatmode.shrug` (*"…"* / a personality mumble), and the session mutes matching for `chatModeCooldownTicks · 4` so a flailing player doesn't farm confusion lines. Counter resets on any successful match or after the stickiness window lapses.

**Ambiguity** (margin rule, §6.6): `dialogue.chatmode.clarify` with the two topic names (*"My work, or the village? Which do you mean?"*). The clarification sets `session.currentQuestion` to a synthetic `chatmode.clarify` context whose two scoped intents are the two candidates' anchor sets plus ordinals/`first|second|former|latter` — a one-word reply resolves it. Does not increment `consecutiveMisses`.

**Explicit player controls** (system intents, §7): farewell ends stickiness with a goodbye line; `chatmode.silence` (*"stop talking"*) mutes that villager→player pairing for `chatModeMuteTicks` (new config, default 6000 = 5 min) with one acknowledgment line; `/conversations chat off` kills the feature per-player. Muted state responds to nothing — not even name address — until expiry.

**Decline/negation** (`chatmode.decline`, `"I don't want to talk about X"`): acknowledged with `dialogue.chatmode.dropped` (*"Fair enough."*), clears `currentQuestion`, never counts as a miss.

**Anti-spam floors:**
- Per player: at most one processed message per `chatModeCooldownTicks` (drop silently below floor — the *player* is flooding, not failing).
- Per villager: one ambient response per window (§12); addressed responses share the same floor.
- Repetition guard: an identical normalized message to the same villager within the stickiness window re-sends **nothing new** — the engine's own cooldown Results (`conversations.day.again` etc.) already handle "asked again too soon" in character for matched topics; for unmatched repeats, stay silent rather than repeat the confused line.

**Hostility (optional, Phase 3, `chatModeInsultDetection` default on):** a small `chatmode.insult` intent (curated keyword list, no slur-list maintenance ambitions — obvious game-register insults only: `stupid, idiot, ugly, hate you, shut up`) maps to an in-character rebuke line plus existing systems: `StateTracker.apply(villager, player, ConversationState.ANNOYED)` and a small `conversations_disposition_apply`-equivalent tension bump via `Dispositions.apply`. Parity with the mod's "strike a villager → ANNOYED" behavior, and it makes the social simulation feel alive without moderating anyone's chat (the message itself still goes to global chat untouched — this mod never censors).

**Hard-failure lines** (all with overlays): `chatmode.too_far` (named villager out of `chatModeAddressedRadius` but in render distance… skip — if they're out of radius they simply don't hear; **no line**, silence is correct), `chatmode.busy` (§9), `chatmode.sleeping` — actually: sleeping villagers don't respond at all (filtered in §5); no line. Keep the failure-line surface minimal: **confused / hint / shrug / clarify / dropped / busy / muted-ack**.

---

## 12. Multiplayer & broadcast semantics

**Addressed / sticky / look-at messages** target exactly one villager; exactly one may respond.

**Ambient messages** (targeting tier 4): each candidate villager scores the message independently against the intent index *with their own eligibility* (a childless villager can't match spouse content; a villager whose `rumors` cooldown is hot scores gossip lower). Selection:

1. Drop candidates below `chatModeAmbientMinScore` after gate preview (§6.6).
2. Sort by `score, then -distance`; take top `chatModeMaxResponders`.
3. Stagger deliveries: first responder at base delay, each subsequent +20–35 ticks (deterministic jitter from villager UUID, not `Math.random`-per-call — keep replay/test determinism).
4. Only the **first** responder becomes the sticky target (closest thing to "who answered you").
5. Villagers who *heard* but didn't respond are unaffected — no state writes occur for non-responders (gate preview is read-only; the engine only runs for actual responders).

**Player-to-player conversation protection:** ambient matching must not hijack conversations between players. Suppress ambient responses when the message contains another *online player's* name as vocative (same vocative regex as §5 tier 1 — check player names before villager names), when the message is a reply chain (starts with `@`), or when two-plus players within the radius are actively chatting (heuristic: another player sent a message in the last 200 ticks and the current message matches no intent above the ambient threshold anyway — the threshold does most of the work; document that servers with heavy town-square chatter should raise `chatModeAmbientMinScore` or set `chatModeMaxResponders` to 1).

**Rate limiting per villager:** a villager responds to at most one ambient message per `chatModeCooldownTicks` window (tracked on the session-map side, per villager UUID), so five players shouting simultaneously don't make one grumpy villager answer five times in one tick.

---

## 13. New classes & file map

All new Java under `dev.otectus.mcaconversations.chat` unless noted. **Pure classes (no Minecraft/MCA imports) are marked ✦ — they must stay pure so they can be JUnit-tested without bootstrap**, mirroring the `template/`/`state/`/`check/` precedent.

| File | Role |
|---|---|
| `chat/ChatModeDispatcher.java` | Orchestrates the §3 pipeline; the only class that sees `ServerChatEvent` data. |
| `chat/VillagerFinder.java` | AABB proximity query + candidate assembly (via `McaCompat`). |
| `chat/Addressing.java` ✦ | Vocative extraction, name matching (exact/prefix/fuzzy), target-tier resolution. Operates on plain candidate records `(uuid, name, distSqr, lookDot)`. |
| `chat/Normalizer.java` ✦ | Lowercase/fold, punctuation strip, tokenization, stopword handling, light stemming, synonym canonicalization, negation tagging (§6.2). |
| `chat/IntentIndex.java` ✦ | Loaded intent table; per-intent keyword sets, patterns, weights; lookup structures. |
| `chat/IntentMatcher.java` ✦ | Scoring (§6.3–6.5): overlap scoring, phrase/pattern boosts, context bonus, threshold + margin logic. Returns ranked `ScoredIntent`s. |
| `chat/IntentBinding.java` ✦ | Record: intent id → `(questionId, answerName)` + metadata (gate preview hints, category). |
| `chat/ChatIntentLoader.java` | `SimpleJsonResourceReloadListener` for `data/*/chat_intents/*.json` (`AddReloadListenerEvent`), builds `IntentIndex`; every entry `SafeParse`-guarded so a bad datapack degrades to skipping that intent, never a crash. |
| `chat/Fuzzy.java` ✦ | Damerau-Levenshtein (banded) + Jaro-Winkler; nothing else. |
| `chat/ChatModeSession.java` | Session map, stickiness, `currentQuestion`/`currentAnswers`, redirect scope, mute/cooldown bookkeeping. |
| `chat/ChatDelivery.java` | Name-prefixed Component assembly, `sendSystemMessage` fan-out, delayed-delivery queue. |
| `chat/GatePreview.java` | Read-only eligibility pre-check via `McaCompat` (answer constraints + feature flags; heart/memory gates deliberately **not** previewed — §6.6) so the dispatcher picks the best *eligible* intent. |
| `mixin/NetworkHandlerMixin.java` | Delivery redirection (§8.2). Add to `mcaconversations.mixins.json`. `require = 0`. |
| `compat/McaCompat.java` (extend) | `selectAnswer(...)` (§8.1), `checkConstraints(...)` (§6.6), `isInteractingWith(...)` (§9), `getVillagerName(...)` for candidates. All `try/catch(Throwable)` + safe default, per house style. |
| `event/ConversationsEvents.java` (extend) | `onServerChat` subscriber; tick-drain for `ChatDelivery`; reload listener registration. |
| `command/ConversationsCommand.java` (extend) | `/conversations chat on\|off\|status`, `/conversations chat debug <message>` (op-only: prints tokens, candidate scores, chosen intent — indispensable for tuning keyword tables). |
| `McaConversationsConfig.java` (extend) | §10 options + `isFeatureEnabled("chat")`. |
| `data/mcaconversations/chat_intents/*.json` | Intent definitions (§7), one file per category mirroring the dialogue files. |
| `assets/mca_dialogue/lang/en_us.json` (extend) | New chat-mode lines (the full §11 surface, each with `/1 /2` variants): `dialogue.chatmode.confused`, `.hint`, `.shrug`, `.clarify`, `.dropped`, `.busy`, `.muted`, `.farewell`, `.insult_rebuke`; plus personality overlays for at least `grumpy`, `peppy`, `friendly` at launch. |

**Dependency rule:** `chat/` may import `compat/` and `state/`/`template/` records, never `forge.net.mca.*` directly. ✦-classes may import nothing from Minecraft at all.

---

## 14. Testing requirements

JUnit 5, same conventions as the existing 28 test classes (plain `@Test`, Gson `JsonParser` for fixtures, no Minecraft bootstrap for pure classes).

**Unit tests (pure ✦ classes):**
- `NormalizerTest` — case folding, punctuation, contractions (`how's` → `how is`), stemming edge cases, negation tagging (`I don't like the rain` must not match a "love the weather" pattern positively), Unicode (player types `café`).
- `AddressingTest` — leading/trailing vocatives, fuzzy name hits and near-misses (`Agness,` matches Agnes; `Agnes'` possessive does; `Agent` does not), player-name vs villager-name precedence, tier precedence (named beats sticky beats look-at beats nearest), stripped-message correctness.
- `IntentMatcherTest` — the heart of the suite. Table-driven: ≥ 100 utterance → expected-intent cases covering every shipped intent, paraphrases, misspellings within fuzz tolerance, ambiguous inputs (assert margin logic picks none / asks), below-threshold small talk (assert no match), context-boosted follow-ups (`currentQuestion` set), negation, multi-intent sentences (assert highest-specificity wins). Also *anti*-cases: common player-to-player chatter (`anyone selling emeralds`, `brb`, `lol nice one`, `where are you`) must score below the ambient threshold against **every** intent.
- `IntentIndexTest` — JSON parsing, defaulting, malformed-entry skip (SafeParse contract), duplicate keyword conflict detection.
- `SessionRulesTest` — stickiness window math, `currentQuestion` set/clear rules, mute/cooldown arithmetic (pure functions over injected game-time longs, mirroring `StateRulesTest`).

**Content lint tests (extend `content/`):**
- `ChatIntentLintTest` — walks every `chat_intents/*.json` and asserts: every `question` id exists as a dialogue file (or MCA-known id like `greet`); every `answer` name exists in that question's `answers[]`; every referenced deflection/system phrase resolves in `assets/mca_dialogue/lang/en_us.json`; every intent has ≥ 3 keywords or ≥ 1 phrase pattern; no two intents share an identical keyword set; all memory ids are `mcaconversations.`-namespaced. *A keyword-table typo must fail CI, not a play session* — same philosophy as `ContentLintTest`.
- Extend `OverlayLintTest` to cover new `chatmode.*` phrase keys in overlays that claim them.
- `MixinsJsonLintTest` — assert `NetworkHandlerMixin` is registered.

**Coverage bar:** every ✦ class ≥ 90% line coverage; matcher table must include at least one utterance per shipped intent per category.

**Manual test script (production-style instance — dev `runClient` can't load MCA's mixins, per build.gradle note):** documented checklist in the PR: GUI flow unchanged with feature off; on: greeting, day, work, gossip, heart-gated deflect below 25 hearts, fears check flow (multi-turn stance in chat), farewell, two-villager ambient response, stop-talking mute, villager busy with another player's GUI, relog mid-conversation, MCA-absent (bridge unavailable) no-op.

---

## 15. Implementation phases

**Phase 1 — Skeleton & plumbing (no matching yet).**
Config section + feature flag; `/conversations chat on|off|status`; `ServerChatEvent` subscriber; `VillagerFinder`; `ChatModeSession` + redirect scope; `NetworkHandlerMixin`; `McaCompat.selectAnswer`. Milestone: a hardcoded debug command `/conversations chat debug-ask <question> <answer>` drives the engine at the nearest villager and the reply appears in chat with full personality voicing, GUI untouched. *This de-risks the entire §8 strategy before any NLU work.*

**Phase 2 — Matching engine MVP.**
`Normalizer`, `IntentIndex`, `IntentMatcher`, `ChatIntentLoader`, `GatePreview`; intent JSON for greeting + Chit-Chat + Profession + Village + Events; addressed-and-nearest targeting only (tiers 1 & 4 minus broadcast: single nearest responder); confused/deflection lines; unit + lint tests green. Milestone: the §1 example works end-to-end.

**Phase 3 — Conversation depth & social polish.**
Stickiness + `currentQuestion` follow-ups (fears/dreams/feelings/us/family sub-answers); look-at targeting; ambient multi-responder broadcast with staggering; farewell/mute; insult detection (`chatModeInsultDetection`, §11); reply-delay humanization; `chatModePublicReplies`; personality overlays for `chatmode.*` lines; Personal/Relationships intent files.

**Phase 4 — Optional extras (each independently shippable).**
`chatModeGreetOnApproach`; `chatModeLocalChat` (signed-chat caveats documented); `chatModeShowHeartChanges`; datapack-defined synonym packs for modpack authors; `/conversations chat debug <msg>` scoring introspection if not done earlier.

Each phase ends with: all tests green, `ContentLint`-family green, manual checklist run, and **feature-off regression check** (byte-identical behavior with `enableChatMode=false`).

---

## 16. Risks & mitigations

| Risk | Impact | Mitigation |
|---|---|---|
| `ServerChatEvent` fires on a background thread (Forge 1.20.1 ChatDecorator) | Off-thread entity access → crashes/corruption | Mandatory `server.execute` hop in §4; dispatcher asserts `server.isSameThread()`; handler body touches only `(player ref, raw String)`. |
| MCA reshapes `Dialogues.selectAnswer` / packet classes in a 7.6.x patch | Chat mode dead | All access via `McaCompat` `try/catch(Throwable)` + `require=0` mixin; graceful degradation path in §8.2; version-range pin `[7.6,8)` already in mods.toml. |
| `selectAnswer` assumes an active GUI interaction somewhere in `Actions` | Rare NPE/log spam | **Largely retired by source verification**: `quit` → `EntityCommandHandler.stopInteracting()` is a confirmed no-op when `interactingPlayer == null` (instanceof-guarded). Phase 1 debug command still smoke-tests every action type reachable from shipped content. |
| Redirect scope leaks (exception mid-trigger) | GUI packets swallowed for later real GUI use | Scope is try-with-resources + assert-server-thread; mixin checks player identity, not a global flag. |
| False positives on ambient chat (villager butts into player banter) | Annoyance, immersion break | Strict `chatModeAmbientMinScore`, player-name vocative suppression, per-villager rate limit, anti-case test table (§14), `chatModeMaxResponders` default 2. |
| False negatives frustrate players ("it ignored me") | Feature feels broken | Addressed messages get the *lower* threshold + graduated confused-lines that teach phrasing (§11); `/conversations chat debug` for server operators; keyword tables are datapack-editable so packs can extend synonyms without Java. |
| Chat-signing violations (1.19+) if player chat is canceled/mutated | Client warnings, kick on strict servers | Default path never cancels/mutates player chat; villager lines are system messages (unsigned by design); `chatModeLocalChat` opt-in documents the trade-off. |
| Performance: entity query + scoring per chat message | Server tick cost | Chat messages are rare events; AABB query ≤ 16 candidates; index lookups are token-set intersections over ~40 intents; zero per-tick work except the tiny delivery queue drain. Budget: < 0.5 ms per message; the debug command prints elapsed µs (§6.9). |
| Villager name collisions (two "Agnes" in range) | Wrong villager answers | Tie-break: nearest + look-at dot product; if still ambiguous and both in the look cone, respond from nearest and have the line's sticky session disambiguate follow-ups. |
| Non-English content packs | Matcher is English-keyword based | Intent keywords live in datapack JSON, not code — translations ship as datapack overrides; normalizer is locale-agnostic (fold + tokenize); document as a modpack-author extension point. |

---

## Appendix A — Research sources

Primary sources behind §2 (all fetched and verified 2026-07-12):

**Minecraft prior art (§2.1):** Denizen `ChatTrigger.java` (https://github.com/DenizenScript/Denizen/blob/dev/plugin/src/main/java/com/denizenscript/denizen/scripts/triggers/core/ChatTrigger.java) and `Settings.java` (same repo, `utilities/Settings.java`); Citizens wiki Denizen triggers (https://wiki.citizensnpcs.co/Denizen/Triggers); Denizen chat-trigger guide (https://guide.denizenscript.com/guides/npcs/chat-trigger.html) and meta docs (https://meta.denizenscript.com/Docs/Languages/chat%20triggers, https://meta.denizenscript.com/Docs/Commands/trigger); Villager Talk (https://modrinth.com/mod/villagertalk1); Talking Villagers (https://www.spigotmc.org/resources/talking-villagers-freemium.132639/).

**Classic engines (§2.2):** AIML 2.0 Working Draft (https://github.com/AIML-Foundation/AIML-2.0-Spec/blob/master/aiml.md); Pandorabots core concepts (https://www.pandorabots.com/docs/core-concepts/); ChatScript Basic User Manual and Advanced Topic Manual (https://github.com/ChatScript/ChatScript/blob/master/WIKI/ChatScript-Basic-User-Manual.md, …/ChatScript-Advanced-Topic-Manual.md); Wilcox, *Pattern Matching for Natural Language Applications* (https://github.com/ChatScript/ChatScript/blob/master/WIKI/PAPERS/Paper-Pattern-Matching-for-Natural-Language-Applications.md); RiveScript tutorial and Working Draft (https://www.rivescript.com/docs/tutorial, https://www.rivescript.com/wd/RiveScript).

**Forge / MCA mechanics (§2.4, verified during authoring):** Forge 1.20.1 `ForgeHooks.java` (https://github.com/MinecraftForge/MinecraftForge/blob/1.20.1/src/main/java/net/minecraftforge/common/ForgeHooks.java — `getServerChatSubmittedDecorator`); MCA Reborn 1.20.1 sources (https://github.com/Luke100000/minecraft-comes-alive, branch `1.20.1`): `resources/Dialogues.java`, `resources/data/dialogue/Actions.java`, `resources/data/dialogue/Answer.java`, `entity/interaction/Constraint.java`, `entity/interaction/EntityCommandHandler.java`, `network/s2c/InteractionDialogueQuestionResponse.java`, `network/s2c/InteractionDialogueResponse.java`.

Research method: 104-agent deep-research harness (5 search angles → 22 sources fetched → 105 claims extracted → top 25 adversarially verified with 3 independent refutation votes each → 24 confirmed, 1 refuted). The refuted claim (Denizen `strict:` prefix) is excluded above; areas that produced no surviving claims (§2.3, §2.5) are explicitly marked inspiration-grade/normative in the body.

---

*End of specification.*
