> This is the shared Forge design/review record. NeoForge-specific verification is recorded in
> [1.7.0 parity evidence](PARITY-1.7.0.md).

# The reload transaction boundary

What one datapack reload actually does to this mod's content and to MCA's dialogue engine, what a
coordinator could hook, and what no coordinator built on the supported MCA artifacts can cover.

Sections 1–6 are the investigation record as written, describing the behaviour **before** the
coordinator existed. §4.1's recommended strategy has since been built; §7 at the end of this document
records what shipped, what changed against §1 and §4.2, and which limitations remain.

Every add-on claim cites `file:line` against the working tree. Every MCA claim names the class and
member it was read from. MCA claims come from decompiled bytecode of the three jars in
`mca_probe_versions` (`gradle.properties:33`), and the ones marked **pinned** are additionally
asserted by `src/test/java/dev/otectus/mcaconversations/compat/McaDialogueReloadProbeTest.java`
against all three jars on every `test` run. Claims not marked pinned are decompilation readings.

---

## 1. What this mod owns

### 1.1 The catalogs it loads

All eleven are `SimpleJsonResourceReloadListener`s registered from one
`AddReloadListenerEvent` handler, `event/ConversationsEvents.java:413`, in this order. The class is
`@Mod.EventBusSubscriber(modid = ...)` with no priority given (`:53`), i.e. `EventPriority.NORMAL` on
the FORGE bus. The listener list, its directories, and its lack of overlap are pinned by
`ReloadTransactionBaselineTest#noOwnedLoaderReadsTheDialogueDirectory`.

| # | Listener | Directory (`data/<ns>/…`) | On a bad *file* | On a bad *entry* | On a build/collision refusal |
|---|---|---|---|---|---|
| 1 | `chat/ChatIntentLoader.java` | `chat_intents` | skipped | skipped, rest published (`:81`) | publishes `active` (`:95`); whole-apply throw retains previous (`:99`) |
| 2 | `conversation/ConversationCatalogLoader.java` | `conversation_catalog` | skipped (`:63`) | skipped, rest published (`:73`, `:80`) | publishes (`:102`); whole-apply throw retains previous (`:106`) |
| 3 | `conversation/BeatContractLoader.java` | `conversation_beats` | skipped | skipped, rest staged (`:99`, `:117`) | **retains previous** on route collision (`:81`), publishes otherwise (`:84`); throw retains (`:88`) |
| 4 | `profession/ProfessionProfileLoader.java` | `profession_profiles` | skipped | skipped (`:74`) | **retains previous** on collision (`:89`), else publishes (`:93`); throw retains (`:97`) |
| 5 | `interiority/Interiority.java` | `interiority` | skipped | skipped (`:99`) | no build step; publishes what parsed (`:111`); throw retains (`:115`) |
| 6 | `identity/IdentityCatalogLoader.java` | `identity_tokens` | skipped | skipped (`:110`, `:129`) | **retains previous** on collision (`:85`), else publishes (`:89`); throw retains (`:98`) |
| 7 | `scene/SceneCatalogLoader.java` | `conversation_scenes` | skipped | skipped (`:67`) | **retains previous** on collision (`:83`), else publishes (`:86`); throw retains (`:97`) |
| 8 | `village/VillageCultureCatalogLoader.java` | `village_culture` | skipped | skipped | publishes (`:85`); throw retains (`:88`) |
| 9–11 | `history/NarrativeCatalogLoader.java#listeners()` (`:57`) — `Episodes`, `Threads`, `Commitments` | `episode_templates`, `thread_templates`, `commitment_templates` | skipped (`:82`) | skipped (`:92`) | the three share one staging area and publish together from `publish()` (`:116`); **retains previous** on collision (`:112`); throw retains (`:102`) |
| 12 | `conversation/ContentGenerationListener.java` | — (`ResourceManagerReloadListener`) | — | — | loads nothing; advances the content generation (`:21`) |

Two distinct failure shapes, therefore:

- **Retain**: an exception escaping `apply`, or a refused build (route/id collision). The previous
  catalog stays in force in full.
- **Skip-and-publish**: a malformed *entry*. The entry is dropped, everything else in the pack is
  published, and the previous catalog is gone. `SafeParse.orNull` (`util/SafeParse.java:23`) is what
  turns a per-entry parse failure into a skip.

These are per-listener decisions with no shared verdict. `ReloadTransactionBaselineTest#oneReloadProducesTwoDifferentVerdicts`
pins the consequence: one pack can leave the beat catalog at its old contents while the conversation
catalog publishes a partial new one, in the same reload.

`ContentGenerationListener` is registered last so it runs last (vanilla applies listeners in list
order, §3.2), and it advances the generation unconditionally — pinned by
`ReloadTransactionBaselineTest#generationAdvancesRegardlessOfTheVerdicts`. A generation is therefore
a *marker that a reload happened*, not evidence that the content behind it is coherent.

### 1.2 The dialogue data it ships but does not load

`src/main/resources/data/mcaconversations/dialogues/` holds **1000** JSON files. No listener of this
mod reads that directory; MCA's `Dialogues` listener does, and the namespace is discarded when it
does — `loadDialogue` keys each file by the path segment after the last `/`, so
`mcaconversations:dialogues/x.json` and `mca:dialogues/x.json` collide on `"x"` and are merged
(`Question.merge` appends the earlier-loaded answers to the later one). *(Decompilation reading; the
parse path could not be driven in the unit JVM — §5.)*

The consequence for a transaction: **half of this mod's shipped content is applied by another mod's
listener, with its own verdict, in the same reload.** A pack that changes a dialogue action and
breaks a Conversations catalog entry gets the changed action regardless of what the catalog loader
decided (`ReloadTransactionBaselineTest#theChangedDialogueIsNotConsumedByAnyOwnedLoader`,
`#noOwnedLoaderReadsTheDialogueDirectory`).

### 1.3 Add-on code that depends on a question name existing in MCA's live map

| Site | Dependency |
|---|---|
| `mixin/DialoguesMixin.java:54` | injects at `getQuestion` HEAD; reads `questions.get("conversations")` from the shadowed map (`:52`, `:63`). A missing hub falls through to vanilla chat (`:67`) |
| `compat/mca/McaHandles.java:426` | `selectAnswer(villager, player, questionId, answerName)` — resolves the name inside MCA at call time; any throw becomes `false` (`:428`) |
| `compat/mca/McaHandles.java:444` | `checkConstraints` — `getQuestion(questionId)` then `Question.getAnswer(answerName)`; a missing name fails closed |
| `conversation/ChoiceSelectionService.java:74` | executes an offer by its stored `questionId`, through `checkConstraints` (`:76`) and then `selectAnswer` (`:98`) |
| `chat/ChatModeDispatcher.java:724`, `:1011` | chat-mode answers drive the same `selectAnswer` |
| `network/ChoiceOfferS2C.java:16` | an offer travels to the client as a question id plus answer ids; the client returns them as strings |
| `conversation/ConversationGuard.java:22` | ownership is decided by name prefix (`conversations`, `conversations.*`) |
| `conversation/ConversationGuard.java:81` | a submission whose offer generation differs from `ContentGeneration.current()` is refused as `CONTENT_RELOADED` |

Nothing here holds an MCA `Question` object across a reload. Every dependency is a **string name
resolved against whatever map is live at the moment of the click.**

---

## 2. MCA's side, per probe version

`<root>` is `forge.net.mca.` (7.6.20, 7.7.0-beta.2) or `forge.net.conczin.mca.` (7.7.1-alpha.2).
`McaBinding.java:65–70` lists both plus the two un-merged roots.

| Member | Shape | Pinned |
|---|---|---|
| `<root>resources.Dialogues` | `extends SimpleJsonResourceReloadListener("dialogues")`, public no-arg constructor | yes |
| `Dialogues.INSTANCE` | `private static Dialogues`, assigned **in the constructor** | yes (static, type) |
| `Dialogues.questions` | `private final Map<String, Question>` (`HashMap`), no accessor | yes (type, private+final, non-static) |
| `Dialogues.getInstance()` | `public static Dialogues` | yes |
| `Dialogues.apply(Map, ResourceManager, ProfilerFiller)` | **protected**, `void`; `questions.clear()` then `data.forEach(this::loadDialogue)` | yes (descriptor, protected); clear-in-place also demonstrated |
| `Dialogues.getQuestion(String)` | `public Question` | yes |
| `Dialogues.selectAnswer(VillagerEntityMCA, ServerPlayer, String, String)` | `public void` | yes |
| `Question.getName()`, `Question.getAnswer(String)`, `Answer.getName()` | public | yes |
| `Question(String, List, boolean, boolean)` | public constructor, touches no Minecraft type | yes (used by the fixture) |
| `Dialogues.prepare` | not overridden; the inherited scan of `data/*/dialogues/**.json` | decompilation |
| `MCAForge.onAddReloadListener` | `MinecraftForge.EVENT_BUS.addListener(...)` in the mod constructor → `EventPriority.NORMAL`; `event.addListener(new Dialogues())` on **every** event | decompilation |
| `selectAnswer` with an unknown name | 7.6.20: **NPE**, no null check. 7.7.0-beta.2 / 7.7.1-alpha.2: warn and return | decompilation |

Behavioural facts demonstrated by the fixture on all three jars:

1. Constructing a `Dialogues` repoints `INSTANCE` at it, and its map starts empty.
2. `apply` clears the map **in place** — same map object before and after, contents gone.
3. A `Question` put into a *new* instance's map is returned, by identity, from that instance's
   `getQuestion`. This is the whole mechanical basis of any retention strategy.

Model-object facts (decompilation): `Question`/`Answer`/`Result` fields are `final` but their lists
are handed out live and are mutated (`Question.merge`, `fromJson`'s `baseConditions` fold); a
`Question` never references another `Question`; the `next` action captures only a **string id** and
resolves it through `Dialogues.getInstance().getQuestion(id)` at trigger time. So a retained old
`Question` keeps its own answers and actions, but its `next` hops land in the *new* map.

---

## 3. The Forge preparation/apply barrier, as it behaves

### 3.1 Listener collection

`ReloadableServerResources.loadResources` builds `List.of(tagManager, lootData, recipes,
functionLibrary, advancements)` and appends `ForgeEventFactory.onResourceReload(...)`, which posts one
`AddReloadListenerEvent` and returns the listeners in the order handlers added them. Same-priority
handlers run in registration order; a higher priority always runs first (eventbus `ListenerList`
concatenates per-priority lists).

### 3.2 The barrier

`SimpleReloadInstance` chains each listener's apply onto the previous one, and every apply waits on
*all* preparations. So:

- every `prepare` runs concurrently on the background pool, before any `apply`;
- `apply`s then run **one at a time, in list order, on the server thread** (vanilla five first, then
  event listeners in handler-registration order).

There is no all-or-nothing window: each apply publishes as it completes, and the server task queue
that runs them is the same queue that runs packet handlers (`NetworkHandlerImpl` uses
`ctx.enqueueWork`). **A player's click can be processed between any two applies.**

### 3.3 Handler ordering between `mca` and `mcaconversations` is a startup race

FML's CONSTRUCT stage is a `ParallelTransition`: mod constructors and `@Mod.EventBusSubscriber`
injection run on a `ForkJoinPool`. `mods.toml`'s `ordering="AFTER"` orders *loading*, not that
parallel construction. MCA registers its handler from `MCAForge.<init>`; this mod's handler is an
auto-subscribed static method. Both are `NORMAL`. Which one lands first in the NORMAL list is decided
by that race at startup and then fixed for the process.

Consequence: today, whether `Dialogues.apply` runs **before or after** this mod's eleven applies is
not determined. Explicit `EventPriority` is the only lever that fixes it.

### 3.4 The window in which MCA's map is empty

`INSTANCE` is repointed to an **empty** instance at event time — before any `prepare`. From that
moment until `Dialogues.apply` completes, `getInstance().getQuestion(x)` is `null` for every `x`. Any
click landing in that window warns (7.7.x) or throws NPE inside the `enqueueWork` task (7.6.20).

---

## 4. Strategy comparison against this mod's hard constraints

Constraints: no MCA import anywhere in `src/` (main or test); no access transformers; no MixinExtras;
mixins are ordinary `@Pseudo`, `remap = false`, `require = 0`, listing both Forge roots, as
`mixin/DialoguesMixin.java:39–43` does; all reflective access through `compat/mca/McaBinding.java`.

| Strategy (trace's A–G) | Verdict |
|---|---|
| **A** — capture the outgoing instance from an `AddReloadListenerEvent` handler at `HIGHEST` | Viable. Needs no new binding member: `Dialogues.getInstance()` is public and already a manifest member (`McaBinding.java:335`). Capture point is well defined because MCA repoints `INSTANCE` inside its own handler |
| **B** — capture in a mixin on `Dialogues.<init>` HEAD | Viable and ordering-independent, but needs `@Shadow private static INSTANCE` on a `@Pseudo` target. A is simpler and uses a public accessor; keep B as a fallback if A's ordering ever proves unreliable |
| **C** — reinsert at `@Inject(method = "apply", at = @At("TAIL"))` on `Dialogues` | Viable. `apply` is protected, which mixin can inject into (the manifest cannot express it — §5). Uses the map `DialoguesMixin` already shadows. An exception thrown here fails the whole reload future, so the hook must be totally guarded like `DialoguesMixin:68` |
| **D** — reinsert from an add-on listener registered by a `LOWEST`-priority second handler | Viable but strictly worse than C: it leaves a gap between `Dialogues.apply` and the reinsertion in which the retained names are missing, and it forces this mod's other applies to run after MCA's |
| **E** — copy the old entries in at `apply` HEAD | Rejected. `loadDialogue` calls `q.merge(existing)` on a key collision, producing a question with both the old and the new answers — neither "old" nor "new" |
| **F** — reflect on `questions` instead of `@Shadow` | Unnecessary for C (the shadow is already there) and not expressible in the manifest for `INSTANCE`. Keep as the diagnostic path only |
| **G** — replace or suppress MCA's own `Dialogues` registration | Rejected. Requires mixing into `MCAForge.onAddReloadListener`, whose body differs per version (8 vs 11 listeners); highest coupling, and it takes ownership of another mod's listener set |

### 4.1 Recommended strategy

**A + C, with explicit `EventPriority.HIGH` on this mod's existing `AddReloadListenerEvent` handler.**

1. `event/ConversationsEvents.java:413` gains `@SubscribeEvent(priority = EventPriority.HIGH)`. This
   mod's eleven listeners are then always earlier in the reload list than MCA's, so **all eleven
   applies — and therefore the coordinator's verdict — complete before `Dialogues.apply` runs.** This
   removes the §3.3 race, which is required whether or not retention is adopted.
2. In the same handler, before MCA's handler has constructed anything, capture the outgoing instance
   via the existing `DIALOGUES_GET_INSTANCE` handle and hold it in a weak, server-scoped slot together
   with the generation it belongs to.
3. The coordinator (stage 5) stages every owned catalog, cross-validates, and records one verdict:
   accepted, or rejected with reasons. This happens in `ContentGenerationListener`'s slot — still the
   last of this mod's listeners, still before `Dialogues.apply` under (1).
4. A new `@Inject(method = "apply", at = @At("TAIL"), require = 0)` in `mixin/DialoguesMixin.java`
   reads that verdict. On **rejected**, it copies, from the captured outgoing map into the shadowed
   new map, exactly the entries whose keys satisfy `ConversationGuard.isOurQuestion`
   (`conversation/ConversationGuard.java:22`) — with `put`, overwriting the newly parsed entries of
   the same name, because the point is to keep the bundle the old metadata was validated against. On
   **accepted**, it does nothing. Everything inside a `try`/`catch(Throwable)` that logs and returns.
5. The generation advances only after step 4, so an offer minted before the reload is refused by
   `ConversationGuard.java:81` exactly when the content behind it actually changed.

Why this and not the alternatives: it needs no new binding member, no access transformer, no MCA
import, no change to MCA's listener set, and it is the only ordering in which this mod's verdict is
knowable at the one moment MCA's map can still be corrected.

### 4.2 Documented limitations — cases this cannot cover

1. **The first reload after boot.** `INSTANCE` is `null` until MCA constructs its first `Dialogues`,
   so there is no outgoing map to retain from. The first reload is publish-only.
2. **The gap between `INSTANCE` repointing and `apply` TAIL.** From MCA's handler until its apply
   finishes, the live map is empty or partial. A click in that window sees `null` for every question,
   including retained ones: warning on 7.7.x, **NPE inside the `enqueueWork` task on 7.6.20** (where
   that NPE surfaces was not traced). Retention cannot shrink this window; only refusing input can.
3. **Clicks between applies.** Applies and packet tasks share one queue (§3.2), so a click can land
   between this mod's applies and MCA's. Rollback is therefore "the next lookup sees the old bundle",
   never "nothing observed the intermediate state".
4. **Retained questions are not sealed.** A retained `Question`'s `next` ids resolve against the new
   map at trigger time, so an old question can hop into a new one. Retention preserves entry points
   and answer lists, not a closed subgraph.
5. **Other mods' data.** Nothing here rolls back MCA's own questions, or any third mod's catalogs.
   The verdict covers this mod's owned names only.
6. **`merge` semantics are bypassed for retained names.** A pack that intentionally extends a
   `conversations.*` question through the namespace-collision merge loses that extension for as long
   as the bundle is rejected.
7. **Client state.** A client holding a rendered card keeps showing it; recovery is the existing
   generation refusal (`ConversationGuard.java:81`, `ChoiceSelectionService.java:74`), not a rollback.
8. **Mixin failure is silent by design.** `require = 0` means a reshaped `apply` disables retention
   with no error. `McaDialogueReloadProbeTest` is the compensating check, and it only covers the jars
   in `mca_probe_versions`.

---

## 5. Fixtures, and what they cannot reach

- `src/test/java/dev/otectus/mcaconversations/compat/McaDialogueReloadProbeTest.java` — per jar, in its
  own `URLClassLoader`, asserts every member in §2's "pinned" column and demonstrates the three
  behavioural facts.
- `src/test/java/dev/otectus/mcaconversations/content/ReloadTransactionBaselineTest.java` — drives the
  real loader classes with a fixture pack (one changed dialogue action, one valid and one invalid
  catalog entry, one colliding beat pair) and pins today's mixed outcome as the regression baseline.
- `src/test/java/dev/otectus/mcaconversations/content/ReloadResilienceTest.java` — the pre-existing
  single-loader retention rules.

**Why `McaBinding`'s manifest was not extended.** The members the strategy touches cannot be expressed
in it, and the ones that can are already there:

- `Dialogues.INSTANCE` is static; a `getter` member erases to an instance getter, because
  `Member#erasedType` prepends a receiver for `GETTER` (`McaBinding.java:137`).
- `Dialogues.apply` is protected; `bindMethod` scans `owner.getMethods()` (`McaBinding.java:674`), so
  it cannot see it.
- `Dialogues.questions` is reached through Mixin's `@Shadow`, not through a handle.
- `getInstance`, `getQuestion`, `selectAnswer` and `Question.getAnswer` are already required manifest
  members (`McaBinding.java:335–338`).

Adding a static-getter or declared-method kind is a change to production binding semantics and is not
part of an investigation stage. The dedicated probe test covers the same ground with no production
change, and asserts the protected/static modifiers themselves, so the note above fails loudly if MCA
ever makes these members ordinary.

**What could not be demonstrated.** MCA's jars are SRG-named while the dev/test Minecraft is
official-mapped, so any MCA method that calls a Minecraft member throws `NoSuchMethodError` in the
unit JVM. `Dialogues.loadDialogue` calls `ResourceLocation.m_135815_()` on its first line, so the
JSON→`Question` parse path — and with it the namespace-dropping key rule and the `merge` behaviour on
a key collision — cannot be driven from the unit suite. The fixture therefore drives `apply` with an
empty map and builds `Question`s through MCA's public constructor, which touches no Minecraft type.
Those two rules remain decompilation readings; confirming them needs a running game or a remapped
classpath, and neither was done here.

---

## 6. Structured diagnostics specification (for stage 5)

Today a rejected entry logs free text (`util/SafeParse.java:27`) and identifies the pack only by the
`ResourceLocation` in each loader's warn line. The coordinator should emit one record per problem:

| Field | Source | Notes |
|---|---|---|
| `generation` | `ContentGeneration.current()` + 1 | the generation the reload is attempting |
| `listener` | loader class simple name | which of the eleven |
| `directory` | the listener's `directory` | as inventoried in §1.1 |
| `resource` | the `ResourceLocation` key from the prepared map | namespace = pack namespace |
| `pack` | the pack id | only obtainable from the `ResourceManager`; `prepare` has it, `apply`'s `Map` does not. If the loaders keep using the inherited `prepare`, this field must be recorded as unknown rather than guessed |
| `path` | JSON pointer to the offending member (`/topics/day.broken/entry`) | buildable by threading the key chain through the parse, which `SafeParse.orNull` does not do today |
| `line`, `column` | from Gson's `JsonSyntaxException`/`MalformedJsonException` when present | absent for semantic failures; record as unknown, do not fabricate |
| `entry` | the id being parsed | already available at every skip site |
| `severity` | `skipped` \| `retained` \| `refused` | the §1.1 distinction, made explicit |
| `reason` | stable machine token plus message | not the exception's `toString()` alone |

A reload emits, in addition, one summary record: generation, verdict (`accepted`/`rejected`), the
per-listener severity, and — when retention ran — the count of question names carried over and the
name of the version-specific hook that carried them.

The pack-id and line/column fields are the two the current architecture cannot supply everywhere;
both are specified as explicitly-unknown rather than optional, so a consumer can tell "not available
on this path" from "no problem here".

---

## 7. Implemented

The A + C strategy of §4.1 shipped. What §1 describes is now history, and is kept because it is the
behaviour the transaction fixtures are measured against.

**What shipped.**

- One content reader, `conversation/ContentReloadCoordinator`, registered at `EventPriority.HIGH` from
  `event/ConversationsEvents#onAddReloadListeners`. The eleven `SimpleJsonResourceReloadListener`s are
  gone; the classes that carried them are views of the published bundle, and the parsing they did
  lives in `conversation/ContentStaging`. `ContentGenerationListener` is gone with them, and
  `ContentGeneration.advance()` with it: the generation is a field of the committed bundle.
- A completion observer registered at `EventPriority.LOWEST` waits for MCA's apply future before
  checking whether the transaction finished. Queueing that check inside the coordinator's own apply
  runs too early: Minecraft schedules the next listener only after the preceding apply completes.
  The observer also finalizes a failed apply, retaining the previous committed bundle.
- `conversation/ContentSources` reads each owned directory from the `ResourceManager` directly rather
  than through the inherited scan, which is what makes the pack id of §6 obtainable and what turns a
  file whose JSON will not parse into an attributed refusal instead of a file that was never there.
  Only the effective resource for each id is opened, so a malformed file hidden under a valid
  override cannot reject anything.
- `conversation/DialogueResourceIndex` models the effective `dialogues/**` across namespaces using
  MCA's own key, merge and priority rules. It never calls `getQuestion`. It is what lets an owned
  `next` into a question nothing declares be refused before MCA applies, which §1.2 said was
  impossible for this mod to judge.
- `conversation/ContentValidation` cross-checks the staged sections against that index.
- `mixin/DialoguesMixin` gained an `apply` HEAD hook (binding the attempt to the exact incoming
  instance), an `apply` TAIL hook (the publication point), and an owned-question lookup boundary on
  `getQuestion`. The decision the boundary applies lives in `ContentReloadCoordinator#lookup` so it
  can be tested without MCA on the classpath.
- `conversation/ContentOperation` pins one bundle for the duration of one planning or execution
  operation, so the nested reads of a submission, a chat exchange, a hub build or a command all see
  one body of content. Deferred chat deliveries carry the bundle they were queued under.

**What changed against §1.** The "skip-and-publish" failure shape is gone. A malformed entry is
`REFUSED`, and the attempt is rejected as a whole, so "retain" is now the only failure shape and it
covers the whole bundle rather than one listener's catalog. `ReloadTransactionBaselineTest` and
`ReloadResilienceTest` were transformed accordingly.

**Limitations that remain, from §4.2.** 1 (the first load has no outgoing map to retain from — the
bootstrap bundle is generation `0` and explicitly unavailable), 2 (the window between `INSTANCE`
being repointed and `apply` finishing is narrowed but not closed: the lookup boundary answers owned
names from the committed bundle throughout it, so the NPE-on-7.6.20 case applies to MCA's own names
rather than to ours), 3 (applies and packet tasks still share one queue), 4 (a retained question's
route into an external name still lands in live content — those exits are now enumerated in the
bundle rather than merely acknowledged), 5, 6 and 7 are unchanged, and 8 is unchanged and now
matters more: `require = 0` on the apply hooks means a reshaped `Dialogues.apply` disables
publication rather than only retention. That is deliberate — no tail means no publication — but it
makes `McaDialogueReloadProbeTest` load-bearing rather than advisory.

**One deviation from §4.1.** When MCA is absent altogether the coordinator publishes at its own apply
with an empty executable table, because the eleven owned sections are this mod's own data and every
feature that would execute a question is already gated on MCA being present. Waiting for a tail that
can never come would leave the mod permanently without content.

**Not demonstrated.** §5 still applies: MCA's parse path cannot be driven in the unit JVM, so the
fixtures use Minecraft's real `SimpleReloadInstance` listener ordering and drive the head and tail
callbacks, map and key rule with opaque marker objects rather than MCA's own `Question`s. A
regression test reproduces the premature-observation failure under that scheduler. The transformed-runtime evidence for
`getQuestion`, `selectAnswer`, automatic `next` hops and the apply hooks against all three probe
artifacts has not been produced, and neither have the dedicated- and integrated-server observations.
