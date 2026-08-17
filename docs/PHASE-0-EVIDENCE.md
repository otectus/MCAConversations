# Branching Conversations — Phase 0 evidence baseline

Companion to `BRANCHING-CONVERSATIONS-IMPLEMENTATION-PLAN.md` §12 "Phase 0 — Evidence and executable
baseline". Everything below was measured or read out of source/bytecode on **2026-08-13** against the
tree at `feature/branching-conversations` (branched from `release/1.0.0`, commit `78de2eb`).

Nothing in this document is taken from the prose of the four design documents. Where a document and
the code disagree, §5 records the discrepancy and the code wins.

> **Status (updated 2026-08-17).** This is the *pre-work* snapshot and is deliberately preserved as
> one. All conversion phases of the plan have since landed — the dialogue directory now holds
> converted trees for check-in, food, weather, season, work, village, people, rumors, news, noticed,
> life, dreams, hopes, regrets, secrets, feelings, the `us.*` topics, and family — and the lint's
> migration ledger (`ConversationGraphLintTest.LEGACY_REWARDED_STARTERS`) is **empty**: no starter
> pays out on the click any more. §7 remains true and unchanged: **no live production verification
> has been performed.** (This file and its companion plan describe MCAConversations work; they
> originally sat in MCAReputation's `gradle/` directory by accident and were moved here.)

---

## 1. Executable baseline

| Item | Value |
|---|---|
| Source version (`gradle.properties`) | `1.0.0` |
| Newest built artifact | `build/libs/mcaconversations-1.0.0.jar` (same day, matches source) |
| Older artifacts present | `mcaconversations-0.9.0.jar` (repo root), `~/Downloads/mcaconversations-0.9.0.jar`, `build/libs/mcaconversations-0.8.0.jar` |
| Working tree at start | clean apart from the untracked plan document |
| Unit tests | **319 tests, 48 classes, 0 failures, 0 skipped** |
| MCA compile target | `7.7.0-beta.2+1.20.1`, runtime range `[7.6,8)` |

### 1.1 Build invocation on Windows

`gradle.properties` pins `org.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64`, a Linux path.
On this Windows checkout `./gradlew test` aborts with *"Java home supplied is invalid"* before any
task runs. By project decision the tracked file is **left alone**; every Gradle invocation in this
workstream passes an override instead:

```sh
./gradlew test -Dorg.gradle.java.home="C:/Program Files/Eclipse Adoptium/jdk-17.0.20.8-hotspot"
```

`./gradlew --version` succeeds without the override (it never forks a build JVM), so a green
`--version` is not evidence that the build works.

---

## 2. Current content shape (measured, not quoted)

15 dialogue question files, **56** named answers (the plan says 57), 216 results.

| Question | auto | answers | results | +hearts | −hearts | disp | check | memory | max + |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `conversations` |  | 8 | 8 | 0 | 0 | 0 | 0 | 0 | +0 |
| `conversations.cat.chitchat` |  | 5 | 33 | 30 | 0 | 0 | 0 | 16 | +3 |
| `conversations.cat.events` |  | 3 | 6 | 4 | 0 | 0 | 0 | 0 | +2 |
| `conversations.cat.personal` |  | 8 | 43 | 28 | 0 | 0 | 0 | 28 | +8 |
| `conversations.cat.profession` |  | 3 | 7 | 2 | 0 | 0 | 0 | 2 | +2 |
| `conversations.cat.relationships` |  | 3 | 3 | 0 | 0 | 0 | 0 | 0 | +0 |
| `conversations.cat.village` |  | 4 | 19 | 14 | 0 | 0 | 0 | 12 | +3 |
| `conversations.dreams` |  | 3 | 4 | 3 | 0 | 0 | 0 | 0 | +5 |
| `conversations.family` |  | 4 | 9 | 8 | 0 | 0 | 0 | 2 | +4 |
| `conversations.fears` |  | 5 | 20 | 13 | 0 | 9 | 12 | 0 | +6 |
| `conversations.feelings` |  | 3 | 5 | 3 | 1 | 0 | 0 | 0 | +8 |
| `conversations.us` |  | 5 | 12 | 11 | 0 | 0 | 0 | 3 | +6 |
| `conversations.work` | yes | 1 | 40 | 40 | 0 | 0 | 0 | 40 | +4 |
| `greet` |  | 1 | 6 | 6 | 0 | 0 | 0 | 5 | +4 |
| `main` |  | 1 | 1 | 0 | 0 | 0 | 0 | 0 | +0 |

Totals: 162 results with a native `positive`, **1** with `negative`, 9 `conversations_disposition_apply`,
12 `conversations_check` tier results (three four-tier stances, all in `fears`), 108
`conversations_record` writes.

Graph analysis:

- **Orphan questions** (no incoming `next`): `greet` only — by design, it merges by basename into
  MCA's own `greet`.
- **Dangling `next` targets**: none.
- **Self-loop edges** (an answer on a page whose result returns to that same page): **118**.
- **Results that grant hearts and return straight to a category page: 131 of 216.** This single
  number is the rework's target: 61% of all results pay the player for clicking a topic and then
  hand them back the menu.

Localization: 724 base `mca_dialogue` keys, 21 personality overlay namespaces at ~80 keys each, 41
`mcaconversations` UI keys — **2,453 strings per locale**, 4,906 across `en_us` + `pt_br`.

Chat intents: 39 total across 8 files; **8** are context-scoped (all in `depth.json`, bound to
`conversations.fears`, `conversations.dreams`, `conversations.feelings`).

---

## 3. MCA API inspection (plan §12 Phase 0 item 2)

Both supported jars were decompiled and compared: `minecraft-comes-alive-reborn-7.6.20+1.20.1` and
`minecraft-comes-alive-reborn-7.7.0-beta.2+1.20.1`. **Every signature this rework depends on is
identical across the two.** The only behavioural drift is that 7.7's `Dialogues.selectAnswer` adds
null-guards plus a warning log where 7.6 would `NullPointerException` on an unknown question/answer.

### 3.1 Answer selection and the result lottery

`Dialogues.selectAnswer(VillagerEntityMCA, ServerPlayer, String questionId, String answerId)`:

1. resolves the question and answer **by name, with no constraint re-check** — confirming the plan's
   §2.4 note. The GUI's constraint filtering happens only when the answer list is *rendered*.
2. sums `max(0, IntAnalysis.getTotal())` over every result, rolls `random.nextInt(total)`, then walks
   the list subtracting each result's clamped total until the running value goes negative.
3. sends `AnalysisResults` **only if the chosen result's actions contain `positive` or `negative`**.
4. triggers the chosen result's actions.

Two consequences that are not documented anywhere in this repository and that lint must encode:

> **Zero-weight fallback rule.** When every result of an answer scores ≤ 0, the loop never breaks and
> `chosen` ends at `results.size() - 1`. **MCA falls back to the *last* result in the array, not the
> first.** Every authored "nothing matched" fallback must therefore be the final element.

> **Non-positive results are unreachable while any sibling scores > 0.** `max(0, total)` means a
> result with a zero or negative score consumes no probability mass, so negative condition sinks are
> a reliable exclusion mechanism — which is what the existing content already relies on.

### 3.2 Action execution order, and why `say` must come *after* `next`

`Actions.fromJson` iterates `json.entrySet()` on a Gson `JsonObject`, which is a `LinkedTreeMap` —
**actions execute in authored JSON key order**. If a result has no `next` key, MCA appends an
implicit `quit` action at the end.

That interacts with the interaction screen in a way that decides how every line must be authored.
`InteractScreen` holds exactly **one** speech slot, and `setLastPhrase` *replaces* it rather than
appending. Both the `say` action and the `next` action write to that slot: `next` resolves
`dialogue.<destination>` and sends it as the destination page's prompt. So the last one authored
wins, and the correct order for a result is:

```text
state first  → conversations_session / _progress_apply / _affection_apply / _disposition_apply / _record
then  next   → sets the destination page, and writes the destination's prompt into the speech slot
then  say    → OVERWRITES that slot with the villager's actual reaction
```

Authoring `say` before `next` makes the villager's reaction invisible — the destination's prompt
lands on top of it. The pre-existing 1.0.0 content already happens to author `next` first, which is
why it reads correctly; the rule was simply never written down. A consequence worth stating plainly:
**a question's own prompt is a fallback**, seen only when a result arrives carrying no line of its
own. Prompts still have to read well, but the villager's real voice in a branching tree is always
the `say` of the result that got them there.

### 3.3 Heart mutation

```java
// Actions.java (identical in 7.6.20 and 7.7.0-beta.2)
register("positive", GsonHelper::getAsInt, hearts -> (villager, player) -> {
    villager.getVillagerBrain().modifyMoodValue(hearts);
    villager.getVillagerBrain().rewardHearts(player, hearts);
});
```

```java
// VillagerBrain.rewardHearts
if (hearts > 0)  level.broadcastEntityEvent(entity, (byte) 16);
else {           level.broadcastEntityEvent(entity, (byte) 15);
                 if (getPersonality() == Personality.SENSITIVE) hearts *= 2; }
memory.modInteractionFatigue(1);
memory.modHearts(hearts);
CriterionMCA.HEARTS_CRITERION.trigger(player, memory.getHearts(), hearts, "interaction");
getVillagerBrain().modifyMoodValue(hearts);
```

Findings that shape the guarded affection action (plan §5.3):

- `rewardHearts` is the correct call: it carries the particle event, interaction fatigue, the MCA
  advancement criterion, and the mood change. A new `McaCompat.rewardHearts` wrapper is all that is
  needed; no reflection, no reimplementation of MCA's economy.
- **A `SENSITIVE` villager silently doubles a negative delta inside `rewardHearts`.** The applied
  delta must therefore be measured as `heartsAfter − heartsBefore`, never assumed equal to the
  requested delta. `ChatModeDispatcher.driveStaggered` already uses exactly this before/after
  measurement for chat heart feedback, so the pattern is established in this codebase.
- Native `positive` calls `modifyMoodValue(n)` **and** `rewardHearts(n)`, which calls it again — so
  native results move mood twice per heart. A guarded action calling only `rewardHearts` moves mood
  once. This is a deliberate, documented behaviour change, not an oversight.

### 3.4 The interaction screen's heart-impact strip

`AnalysisResults` is sent only for results carrying native `positive`/`negative`, so migrating
content off those actions would silently delete the feedback strip from the interaction screen.

`forge.net.mca.network.s2c.AnalysisResults` and `forge.net.mca.resources.data.analysis.ChanceAnalysis`
are both public and constructible, so the guarded action can build and send an equivalent packet
itself. **Plan §5.3's fallback design ("guarded condition plus native positive/negative") is not
required** — the custom action can preserve the feedback.

### 3.5 GUI submission handling — the security hole

```java
// InteractionDialogueMessage.receive(ServerPlayer) — byte-identical in 7.6.20 and 7.7.0-beta.2
Entity v = player.serverLevel().getEntity(this.villagerUUID);
if (v instanceof VillagerEntityMCA villager) {
    Dialogues.getInstance().selectAnswer(villager, player, this.question, this.answer);
}
```

There is **no** distance check, no open-screen check, no "is this villager interacting with you"
check, no constraint check, and no replay protection. A crafted or duplicated client packet can
drive any question/answer pair against any MCA villager anywhere in the sender's level. Today that
is worth at most a few farmed hearts; with authored milestones and arc transitions it becomes a
correctness problem.

**Chosen hook (plan §12 Phase 0 item 3):** a `require = 0`, `remap = false` mixin at the HEAD of
`InteractionDialogueMessage.receive`, cancellable, that validates **only** questions owned by this
mod (`conversations*`) and passes every native MCA question straight through. Same class, same
descriptor on both MCA versions. If the injection ever fails to apply, the guarded actions still
enforce idempotency, caps and age/romance safety independently, and validation degrades to off.

### 3.6 Offered-choice recording needs no new hook

`InteractionDialogueResponse` already carries `(question, List<String> validAnswers)` — the
constraint-filtered set, built server-side by `Question.getValidAnswers`. This mod's existing
`NetworkHandlerMixin` already intercepts that packet on the way out; it currently records the pair
into `ChatModeSession` only while a chat redirect scope is open and discards it otherwise.

Extending that same interception to record the offered set into the shared session registry for GUI
players costs **zero new mixins**.

Related: `ChatModeSession.Session.currentAnswers` is written by that mixin and **read nowhere in the
codebase** — confirming plan §7.3's finding that chat matching does not yet filter by the answers
actually on offer.

### 3.7 `auto` questions

`Actions.next` resolves the target question and, if `isAuto()`, immediately recurses into
`selectAnswer(villager, player, target, target.getRandomAnswer().getName())` — sending **no**
`InteractionDialogueResponse` for the auto node. `conversations.work` already uses this shape
(`auto: true, silent: true`, one unnamed answer, 40 profession-conditioned results) and it is the
right shape for a contextual opener.

Risk to lint: `getRandomAnswer()` is a genuine `Random.nextInt` over the answer list, and
`Dialogues.loadDialogue` **merges answers by basename across datapacks**. An `auto` question is only
deterministic while it has exactly one answer, so lint must assert that for every `auto` question
this mod owns.

---

## 4. Verified state of the RPG primitives (plan §2.3)

Every claim in plan §2.3 is confirmed true against source:

| Claim | Evidence |
|---|---|
| `Dispositions.baseline(...)` returns zero for every personality | `Dispositions.java:32-34` — literal `return 0` |
| `CheckContextFactory` supplies personality fit of zero | `CheckContextFactory.java:43` — `int personalityFit = 0` |
| `CheckContextFactory` supplies arc stage zero | `CheckContextFactory.java:51` — `int arcStage = 0` |
| No loaded interiority profile system | no such package, class or data directory exists |
| No general ordered-arc or milestone implementation | `MemoryIds` has `unlock(name)` flags only; nothing ordered, nothing exclusive, no counters |
| Native `positive`/`negative` are not protected by the farming guard | `FarmingGuard` is reachable only from `DispositionStore.apply`; the 162 native heart actions bypass it entirely |

One further gap, which plan §11.3 already anticipates: `DispositionStore.load` discards the entire
store whenever `tag.getInt("version") != CURRENT_VERSION`. Acceptable for a decaying vector that
rebuilds itself; **unacceptable** for an affection ledger or a milestone store, which must migrate
field-by-field.

---

## 5. Document reconciliation (plan §12 Phase 0 item 5)

| Document | Status | Discrepancies found |
|---|---|---|
| `DATAPACK.md` | Accurate | Custom condition/action tables match `ConversationsMcaRegistrar` exactly. Missing: the zero-weight last-result fallback rule (§3.1), the JSON key-order execution rule (§3.2), and the `auto`-question single-answer requirement (§3.7). All three are load-bearing for authors and must be added. |
| `chat-mode-spec.md` | Accurate but superseded | §6.7 and §9 state as design that "global intents remain live simultaneously" with context intents. Plan §7.3 deliberately reverses this for live decision nodes. The spec needs an amendment, not a correction. `currentAnswers` is documented as captured and is captured — it is simply never consumed. |
| `MCA-Conversations-1.0.0-RPG-Expansion.md` | Design input only | Its "completion criteria" describe the vector, checks, interiority, arcs, milestones and callbacks as shipped in 1.0.0. Only the vector and the checks are real. Interiority, arcs, milestones, callbacks, evolving openers and exclusive branches do **not** exist in code. Treat the whole document as a wish list, exactly as plan §2.3 warns. |
| `BRANCHING-CONVERSATIONS-IMPLEMENTATION-PLAN.md` | Accurate | One measurement drift: 56 named answers, not 57. §5.3's suggested fallback design is unnecessary (see §3.4). |
| `CONFIG.md` | Accurate | No `branching` key yet; to be added with the flag. |

---

## 6. Decisions taken for 1.1.0

Recorded here because several of them freeze into serialized API on release.

| Decision | Choice |
|---|---|
| Scope | Plan phases 0–2 only: foundations plus the **Day** and **Fears** pilot trees. Phases 3–7 follow in later releases. |
| Version / branch | `1.1.0` on `feature/branching-conversations`. |
| Chat parity | Context-scoped intents for every substantive answer **plus** numbered/keyword quick-replies on decision nodes. |
| Localization | `en_us` and `pt_br` authored in the same change as every new node. Parity lint stays green throughout. |
| Heart economy | Deliberately slower than 1.0.0. Mundane topics settle at 0..+1; real gains concentrate in deep topics and one-time milestones. |
| Interiority granularity | **Per personality only.** No per-villager selection, no UUID salt, nothing per-villager to persist or migrate. The schema leaves room to add per-villager selection later without breaking saves. |
| Build config | `gradle.properties` left untouched; the JDK path is overridden per invocation. |
| Production verification | **Not performed for 1.1.0.** See §7. |

---

## 7. Known gap: production verification

Plan §14 requires the reobfuscated jar to be exercised in a production-style Forge instance —
fresh and upgraded worlds, dedicated server, two concurrent players, all three hub entry modes,
relog and restart persistence, MCA: Quests and Serene Seasons present and absent, every config
off-state, and farming/duplicate-packet attempts. Plan §2.4 additionally forbids substituting
ForgeGradle `runClient`, because MCA's own mixins misbehave in that runtime.

**None of this was performed for 1.1.0.** By explicit project decision the release ships on unit
tests, lint and a successful reobfuscated build alone, and the gap is stated plainly in
`CHANGELOG.md` and `README.md` rather than papered over. No claim of live verification appears
anywhere in this release's documentation or commit messages.
