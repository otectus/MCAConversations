# MCA: Conversations — Branching Conversation Rework

## Instruction set for planning and implementation

This document is the implementation brief for turning every substantive conversation option shipped by MCA: Conversations into a meaningful, multi-turn, multiple-choice exchange. It is written for a coding agent working in the current repository.

The intended result is not an endless dialogue tree and not a custom AI system. It is a bounded, authored conversation graph in which:

- selecting a topic gets a contextual answer from the villager;
- the player then chooses what to say in response;
- later choices react to both the villager's answer and the player's earlier stance;
- those choices can gain, lose, or leave unchanged MCA affection hearts;
- personality, mood, age, relationship state, memories, and past choices affect how a stance lands;
- mundane subjects receive short but real trees, while intimate subjects receive deeper trees and persistent arcs;
- the interaction GUI and free-text chat mode continue to drive the same underlying dialogue results.

Treat this document as a requirements and execution contract. Before coding, turn its phases into small implementation tasks with explicit tests and acceptance criteria. Do not claim a phase complete merely because its JSON parses or its unit tests pass; the production-runtime checks in this document are part of completion.

---

## 1. Product outcome and interpretation of “every option”

### 1.1 Required outcome

Every substantive topic starter owned by this mod must enter a conversation graph instead of immediately returning to its category after one villager line. This includes the intentionally mundane subjects: check-ins, the day, food, weather, seasons, work, village life, neighbors, local news, and gossip.

A topic tree must contain at least:

1. a contextual villager opener;
2. a first player response node with multiple credible stances;
3. a villager reaction to that stance;
4. at least one further player decision or follow-through for an ordinary adult topic;
5. a deliberate resolution or exit.

Deep topics must go further and may continue across separate conversations through persistent arc state.

This rule applies to every branch-enabled opener result, not only the ideal first-time result. A cooldown reply, low-heart deflection, missing-gossip fallback, no-quest fallback, or unavailable optional context must still lead to an appropriate response choice. It may use a shorter boundary/fallback exchange, but it may not silently restore the current one-click behavior. Babies and the explicit branching-disabled compatibility path are the only blanket exceptions.

### 1.2 What is and is not a conversation option

The completeness rule applies to all substantive choices shipped in the Conversations hub and its topic pages. It does not mean every terminal response must recursively open another tree forever.

The following are exempt from starting another tree and must remain side-effect-free:

- category navigation;
- Back, Something else, Never mind, Leave, and equivalent exits;
- a final resolution that clearly ends a bounded exchange;
- baby babble;
- the actual handoff into an external quest screen, after the player has explicitly accepted the offer.

Do not expand unrelated native MCA interactions such as Story, Joke, Gift, or other upstream main-menu mechanics unless a later task explicitly adds them to scope. Preserve the current hub integration modes.

### 1.3 Quality bar

The rework fails the product goal if it merely adds one generic “That is nice / That is bad” prompt after every line. Choices must be believable things a player character might actually say, and choices within a node must differ in stance, not only in wording.

Good stance families include empathy, curiosity, candor, encouragement, practical help, humor, respectful disagreement, self-disclosure, restraint, confrontation, and dismissal. Not every node needs every family. The villager must react to the meaning of the choice.

There must not be a universally correct button. A warm answer may suit one personality while direct practical advice suits another. A polite exit is always safe and neutral.

---

## 2. Current-state findings that must guide the implementation

The repository is a Forge 1.20.1 add-on for MCA Reborn 7.7.0 beta 2, currently versioned 1.0.0. Its runtime already has most of the primitives needed for this rework. Reuse them.

### 2.1 Existing flow

The current flow is:

```text
MCA interaction screen
  -> main/Chat or Conversations button
  -> conversations category hub
  -> conversations.cat.<category> topic answer
  -> MCA selects one weighted Result
  -> say + positive/negative + memory/action + next
  -> usually back to the same category
```

Relevant implementation points:

- `src/main/resources/data/mcaconversations/dialogues/*.json` contains the MCA dialogue graph.
- `src/main/java/dev/otectus/mcaconversations/mixin/DialoguesMixin.java` redirects MCA Chat to the hub when configured.
- `src/main/java/dev/otectus/mcaconversations/mixin/QuestionMixin.java` controls the additive hub button.
- `src/main/java/dev/otectus/mcaconversations/compat/McaCompat.java` is the compatibility boundary for driving MCA and reading hearts, mood, age, memories, and relationship state.
- `src/main/java/dev/otectus/mcaconversations/compat/mca/ConversationsMcaRegistrar.java` registers custom dialogue conditions and actions.
- `src/main/java/dev/otectus/mcaconversations/chat/ChatModeDispatcher.java` maps typed text to the same `(question, answer)` pairs used by GUI clicks.
- `src/main/java/dev/otectus/mcaconversations/chat/ChatModeSession.java` captures the next question and valid answers emitted by MCA for chat follow-ups.

Keep MCA's dialogue JSON and `Dialogues.selectAnswer` as the authoritative conversation engine. Do not build a parallel dialogue runtime or a custom screen unless inspection proves an unavoidable MCA limitation.

### 2.2 Measured content shape

At the time of this analysis, the shipped dialogue content contains:

- 15 dialogue question files;
- 57 answers and 216 results;
- 162 results with a native positive-heart action;
- one result with a negative-heart action;
- nine results applying disposition changes;
- 12 check-tier results, representing three four-tier checked stances;
- 108 results writing conversation memory.

Most topic clicks therefore reward the player before the player has expressed any response. The current data is overwhelmingly positive and offers almost no opportunity to lose affection through conversation.

Only `conversations.dreams`, `conversations.fears`, and `conversations.feelings` are true response-stance pages. Dreams and feelings have two substantive stances each. Fears has four and is the only shipped topic currently using disposition-aware, four-tier dialogue checks. The other pages mostly return straight to a category after one line.

The chat-intent pack currently has 39 intents, of which only eight are context-scoped follow-up stances. Every added GUI choice will therefore require matching chat-mode work.

### 2.3 Existing RPG primitives are incomplete, not absent

Reuse and finish these systems:

- `DispositionAxis`, `DispositionStore`, `Dispositions`, and `FarmingGuard` already provide a bounded per-villager/per-player vector, lazy decay, daily movement caps, and repeat diminishing.
- `CheckResolver`, `CheckSeed`, and `CheckContextFactory` already provide deterministic crit/success/partial/rebuff checks.
- `ConversationState`, LongTermMemory helpers, templates, gossip, and quest conditions already provide contextual inputs.

However, do not mistake comments and design documents for implemented behavior:

- `Dispositions.baseline(...)` currently returns zero for every personality.
- `CheckContextFactory` currently supplies a personality fit of zero.
- `CheckContextFactory` currently supplies arc stage zero.
- there is no loaded interiority profile system;
- there is no general ordered-arc or milestone implementation;
- native `positive` and `negative` actions are not protected by this mod's disposition farming guard.

`MCA-Conversations-1.0.0-RPG-Expansion.md` is useful design input, but the coding agent must verify each claimed feature against source before relying on it.

### 2.4 Constraints already verified by the project

- MCA question files merge by basename across datapacks.
- The file basename is the question ID.
- Answer labels use `dialogue.<question>.<answer>`; question prompts use `dialogue.<question>`.
- Result conditions adjust a weighted score. A positive score means a result can enter MCA's result lottery; it is not a priority system.
- Negative condition sinks are used to make results mutually exclusive.
- MCA's `selectAnswer` trusts the supplied question and answer and does not re-check answer constraints. Chat mode currently compensates with `McaCompat.checkConstraints`; GUI/network submissions need separate consideration before high-value consequences are introduced.
- Production behavior cannot be certified from ForgeGradle `runClient`, because MCA's own mixins do not work correctly in that development runtime. Keep the existing production-style test discipline.

---

## 3. Non-negotiable design rules

### 3.1 Preserve one visible relationship economy

MCA hearts remain the authoritative and player-visible affection value. The disposition vector remains internal and modulatory. Do not introduce a second visible “relationship score.”

Use the vector to decide what opens and how a stance lands. Use guarded MCA heart changes for the visible affection consequence.

### 3.2 Move affection from asking to responding

Topic openers, navigation, returning, abandoning, and merely hearing a disclosure grant no hearts.

Affection changes belong on substantive player decisions after the villager has spoken. This is the core behavioral migration:

```text
Current: ask “How is your day?” -> automatic +2/+3
Target:  ask “How is your day?” -> 0
         hear the answer
         choose a response
         response lands -> -N, 0, or +N
```

Opening a conversation is not itself kindness. How the player listens, jokes, challenges, dismisses, or follows through is what shapes affection.

### 3.3 Consequences must be authored, bounded, and explainable

Every consequence-bearing choice must declare:

- its stable decision ID;
- its stance tags;
- its possible check tier or deterministic outcome;
- its requested heart delta;
- its disposition deltas;
- any short-term memory, milestone, boundary, promise, or arc transition;
- its next node or terminal return;
- its replay policy and farming budget group.

No hidden heuristic may invent arbitrary heart changes at runtime. Personality and state may change how the authored stance resolves, but the possible outcomes remain explicit in data and lintable.

### 3.4 Choice labels are dialogue, not mechanics

Buttons must contain what the player says. Do not label them “Good response,” “Bad response,” “Persuade,” “Warmth +2,” or with success odds.

The player should infer the result from the villager's words, expression, follow-up availability, and optional heart feedback already supported by chat mode.

### 3.5 Every node has a graceful exit

Every choice node must have a neutral exit or subject-change answer. It applies no hearts, vector movement, milestone, or reward. No gate or changed world state may leave a page with zero valid answers.

Heart, trust, age, memory, or feature gates must produce authored conversation, not dead ends. For example, a villager who refuses a personal question can let the player respect the boundary, apologize, ask a safer question, or press and risk a loss. A missing quest can lead to brief ordinary help-talk. “Nothing happened” is still a conversational state.

### 3.6 No behavioral randomness among simultaneously valid results

Localization pools may vary wording. Behavioral outcomes must be deterministic for a given state. For any consequence-bearing answer and any modeled state, exactly one result may have positive weight.

If a check is used, author all four tiers and an explicit checks-disabled fallback. Do not leave two tier results eligible and allow MCA's weighted lottery to decide the player's consequence.

### 3.7 Both frontends are one feature

A GUI-only branch or a chat-only branch is incomplete. Every substantive answer in a live decision node must be reachable through:

- its MCA answer button; and
- a context-scoped chat intent with tested paraphrases.

Both paths must call the exact same `Dialogues.selectAnswer` result and therefore produce identical hearts, disposition, memories, checks, and next nodes.

---

## 4. Target conversation grammar

### 4.1 Node roles

Use a small, consistent graph vocabulary:

| Role | Purpose | Allowed side effects |
|---|---|---|
| Category | Navigation to topics | `next` only |
| Opener | Select contextual villager line and start topic | `say`, `next`, topic/session start, first-seen/cooldown memory; no affection |
| Decision | Present 2–5 things the player can say | Side effects occur in the selected result, not in the prompt |
| Reaction | Villager reacts; may route to a follow-up decision | `say`, guarded affection, disposition, progress/memory, `next` |
| Resolution | Close the exchange or seed a later callback | Same as Reaction, then a deliberate return |
| Callback opener | Reference prior choices or resume an arc | `say`, `next`; normally no affection for merely resuming |
| Exit | Leave or change subject | `next` or `quit` only |

MCA combines the reaction actions and the next-question transition in one result, so “Reaction” is usually a result on a Decision answer rather than a separate silent page.

### 4.2 Depth classes

Record every topic in a machine-readable conversation catalog and assign one of these depth classes:

| Class | Subjects | Required shape | Target duration | Heart stakes |
|---|---|---|---|---|
| Quick | weather, season, food, routine check-in | opener + 2 player decisions + resolution | 30–75 seconds | mostly -2..+2 total |
| Standard | work, village, neighbors, news, general life | opener + 2–3 decisions + resolution/callback | 60–120 seconds | mostly -4..+4 total |
| Deep | dreams, fears, hopes, regrets, secrets | opener + 3–5 decisions and cross-session arc | 2–5 minutes, split when appropriate | guarded one-time outcomes up to roughly ±8 |
| Relationship | feelings, spouse, parent/child/family | opener + 3–5 decisions, relationship-specific paths | 2–5 minutes | similar to Deep; strict age/romance gates |
| Service | work offer / quest handoff | context + terms/motivation + accept/decline | 30–90 seconds before external UI | little or no immediate affection |

Quick does not mean disposable. Even weather must give the player a chance to relate: offer practical concern in a storm, joke about wet boots, discuss the crops, dismiss the villager's worry, or leave.

Toddlers may use a reduced Quick grammar with one follow-up decision. Babies remain babble-only. Children and teens need age-appropriate trees rather than adult lines with a different opener.

### 4.3 Branching without combinatorial explosion

Use a graph, not a fully duplicated binary tree:

- branch where the meaning or consequence genuinely differs;
- converge branches after the villager has acknowledged the earlier stance;
- use path-specific node IDs only when a later choice must know the exact earlier path;
- store durable facts only when they will be referenced later;
- use localization variants for wording variety, not duplicate nodes;
- cap an ordinary topic at five player decisions;
- move major arc continuation to another in-game day rather than adding an unbounded same-session chain.

Do not create a generic response node whose choices mean different things depending on invisible state. If the choice text or stakes differ, create a distinct node.

### 4.4 ID convention

Keep current category answer IDs stable for datapack and chat compatibility. Add namespaced question IDs under a predictable hierarchy:

```text
conversations.topic.<topic>.respond
conversations.topic.<topic>.followup
conversations.topic.<topic>.followup.<branch>
conversations.topic.<topic>.resolve.<branch>
conversations.arc.<topic>.<stage>.respond
```

Stable decision IDs are separate from question IDs:

```text
<topic>.<stage>.<stance>
day.rough.empathize
weather.storm.offer_help
work.dissatisfied.challenge
secret.first.promise_keep
```

Never reuse a decision ID for a semantically different choice. These IDs key anti-farming, debug output, callbacks, and tests.

### 4.5 Conversation catalog

Add a small, data-only catalog under this mod's namespace, for example:

`src/main/resources/data/mcaconversations/conversation_catalog/topics.json`

The runtime does not need to replace MCA with this catalog. Its primary job is to define completeness and let lint reason about the graph. Each topic entry should declare at minimum:

```json
{
  "id": "day",
  "entry": {"question": "conversations.cat.chitchat", "answer": "day"},
  "depth": "quick",
  "return_question": "conversations.cat.chitchat",
  "ages": ["toddler", "child", "teen", "adult"],
  "required_stance_families": ["empathy", "curiosity", "exit"],
  "chat_required": true
}
```

The catalog becomes the authoritative list behind “every shipped topic has a tree.” A topic may not be added to a category without adding it to the catalog and satisfying its graph lint.

---

## 5. Affection and disposition design

### 5.1 Outcome scale

Use this as the default scale; tune it against real MCA progression before freezing values:

| Choice/outcome | Typical hearts | Typical vector effect |
|---|---:|---|
| Navigation, opener, neutral exit | 0 | none |
| Respectful but ordinary response | 0..+1 | +1 in a relevant axis at most |
| Well-matched mundane stance | +1..+2 | +1..+3 Warmth/Respect/Trust |
| Mild mismatch or tactless joke | 0..-1 | small Tension or loss of Warmth |
| Clear dismissal or insult | -1..-3 | Tension +2..+5 |
| Deep success | +2..+4 | +2..+6 relevant axes; may advance an arc |
| Deep crit / one-time milestone | +4..+6 | durable progress or revelation; one time only |
| Deep partial | -1..+1 | small movement; possible second chance |
| Deep rebuff | -2..-5 | Tension and/or trust loss; graceful exit |
| Deliberate boundary violation | up to -8 once | durable scar/milestone; never repeat-farmable |

Do not award every polite answer. A conversation in which the player listens without changing hearts can still be successful because it advances Familiarity, records a callback, or simply reveals character.

### 5.2 Per-conversation and per-day budgets

Implement and test both:

- a per-conversation absolute positive and negative budget, based on depth class;
- a per-villager/per-player daily budget for conversation-sourced heart movement.

Suggested initial caps, subject to a production balance pass:

| Class | Positive cap per conversation | Negative cap per conversation |
|---|---:|---:|
| Quick | +2 | -3 |
| Standard | +4 | -5 |
| Deep / Relationship | +8 | -10 |
| Service | +2 | -2 |

Use separate positive and negative counters so a player cannot manufacture extra positive capacity by first antagonizing the villager. Repeating the same decision on the same day should diminish full -> half -> zero, with integer rounding toward zero. Milestone effects fire once ever.

### 5.3 Add a guarded affection action

Do not scale this rework by continuing to place raw native `positive`/`negative` actions on every result. Introduce a parse-safe custom action such as:

```json
"conversations_affection_apply": {
  "decision": "day.rough.empathize",
  "delta": 2,
  "budget": "quick",
  "policy": "daily_repeat"
}
```

The exact schema may change after inspecting MCA 7.6 and 7.7 heart mutation behavior, but the action must:

- call MCA's own heart mutation mechanism through `McaCompat`;
- remain server-authoritative;
- apply the configured multiplier and caps;
- diminish or suppress repeated decisions;
- be idempotent for a duplicated packet/transaction;
- clamp authored deltas to a safe range at parse time;
- report the actual applied delta to debug logging and chat-mode heart feedback;
- no-op safely when context is malformed;
- preserve the interaction screen's relationship feedback if MCA exposes a supported path;
- never derive affection from the hidden disposition value automatically.

If inspection proves a custom action cannot preserve MCA's required analysis/feedback behavior, use a guarded custom condition plus native `positive`/`negative` as the fallback design. In that design, prove action ordering and duplicate-packet safety before content migration. Do not silently accept unguarded native heart writes.

Add a dedicated, bounded `ConversationProgressStore` or equivalently focused ledger rather than coupling affection guards to `enableDispositions`. Affection anti-farming must remain active even when the vector feature is disabled. Persist only the minimum required counters and decision records; prune on the same low-frequency cadence already used for dispositions.

### 5.4 Disposition effects

Keep the existing six axes and their current meaning. Finish the incomplete inputs:

- load non-zero per-personality resting baselines;
- resolve stance/personality fit into `CheckContextFactory`;
- supply real arc stage to check seeding;
- preserve the romance eligibility guard on Attraction at read, condition, check, and write layers;
- keep Familiarity near-monotonic and time-earned;
- make Tension decay and make durable scars explicit milestones rather than enormous Tension values.

Every stance should list which axes it is intended to touch. Use a common stance vocabulary so personality fit can be data-driven:

```text
empathy, curiosity, candor, encouragement, practical_help,
humor, respectful_disagreement, self_disclosure, restraint,
challenge, flirtation, dismissal, boundary_push
```

### 5.5 Personality fit and villager interiority

Add a reloadable data registry for personality/interiority definitions rather than hard-coding a switch statement. A definition should be able to provide:

- resting baselines by axis;
- preference or aversion values for stance tags;
- 1–3 wants or conversational drives;
- boundary categories;
- optional arc or secret pools;
- aliases compatible with `Personalities.LEGACY_ALIASES`.

Use deterministic, bounded selection for any per-villager traits. A given villager must not change wants after a reload merely because collection order changed. If profiles are selected per villager rather than solely per personality, persist the selected stable IDs or derive them from UUID plus a versioned salt and define the migration behavior.

Personality fit modifies a check within a modest bound; it must not make one stance automatically correct for all villagers of a personality. Current mood, relationship history, and the specific topic still matter.

---

## 6. Narrative memory, boundaries, promises, and arcs

### 6.1 State taxonomy

Use the cheapest state that satisfies the narrative requirement:

| State kind | Example | Storage |
|---|---|---|
| Current path | player chose humor on turn one | graph path/session only |
| Short-term | villager asked for space for two days | expiring player-scoped LongTermMemory |
| Permanent milestone | player was trusted with a secret | player-scoped namespaced flag |
| Exclusive choice | promised discretion vs refused promise | enumerated mutually exclusive flags |
| Ordered arc | fear stage 0 -> disclosure -> plan -> follow-through | bounded stage in progress data, or rigorously linted one-way stage flags |
| Relationship vector | Trust, Respect, Warmth, etc. | existing disposition SavedData |

Do not persist ordinary branch path text or localization. Persist stable IDs and stages only.

### 6.2 Required progress API

Provide parse-safe data hooks sufficient for authored arcs. A minimal vocabulary is:

- condition: current arc stage/range;
- condition: has/lacks milestone or exclusive choice;
- action: advance, hold, or regress an arc within declared bounds;
- action: set a one-shot milestone;
- action: set one member of an exclusive choice group;
- action: set an expiring boundary/cooldown memory.

Names must be namespaced, player-scoped where appropriate, and declared in a registry/catalog so lint can reject typos. A malformed progress action must no-op rather than break datapack reload.

If MCA LongTermMemory can express a requirement cleanly, prefer it. Use custom progress storage for ordered numeric stages, exclusivity, transaction idempotency, and counters that LongTermMemory cannot represent safely.

### 6.3 Arc rules

- Deep and Relationship topics must have a defined cross-session arc, even if the first release only authors two or three stages.
- An arc may advance at most one major stage per completed conversation unless a crit explicitly declares otherwise.
- Major stages should generally require a later time bucket or day before continuation.
- A rebuff may hold or regress an arc, but must never permanently remove all conversation access.
- A crossed boundary may close a warm route while leaving a guarded repair route.
- Mutually exclusive choices must be visible later through changed dialogue, not only through hidden state.
- Every stored milestone needs at least one callback line elsewhere, or it is dead state and should not be stored.

### 6.4 Gossip needs conversation context

The existing `conversations_gossip_say` tells and consumes an event but does not provide enough context for a nuanced player follow-up. Extend it or add a companion action so the next decision can know at least:

- event ID;
- event type;
- named subject(s), if still resolvable;
- whether it is happy, sad, neutral, or sensitive news;
- whether it has already been marked told.

This context may be short-lived session state. It must not retain entity objects, must tolerate an unloaded/dead subject, and must not tell the same event twice because the player backed out. Laughing about a roof chicken and laughing about a death cannot share the same consequence table.

---

## 7. Session integrity and frontend parity

### 7.1 One session model for GUI and chat

Create a small transient server-side session registry, keyed primarily by player UUID, containing:

- villager UUID;
- topic ID and depth/budget class;
- current offered question ID;
- currently offered answer IDs;
- start and last-activity game time;
- current graph branch or short-lived context;
- heart movement already applied in this conversation;
- transaction IDs already applied;
- optional last gossip context;
- frontend (`GUI` or `CHAT`) for diagnostics only.

Expire sessions after inactivity, and clear them on logout, villager death, explicit exit, lost interaction, or target change. Do not persist this transient state across restart. Durable effects live in LongTermMemory/progress/disposition storage.

Integrate rather than duplicate `ChatModeSession`; either generalize its conversation portion or compose it with the new registry. Preserve chat-specific mute, stickiness, miss, and delivery state.

### 7.2 Validate offered choices before consequences

Before enabling meaningful rewards and penalties, inspect both supported MCA versions and identify the narrowest stable hook that observes a GUI dialogue response and its answer submission. For questions owned by `mcaconversations`:

- record the question and valid answer list offered to the player;
- reject or neutralize a submitted answer that was not offered for that villager/session;
- reject a stale or duplicated consequence transaction;
- re-check hard age/relationship constraints server-side;
- fail safely without breaking native MCA dialogue if the hook is unavailable.

Do not globally alter native MCA answer semantics. Scope validation to this mod's questions/actions. Keep any new mixin soft-fail (`require = 0`) and document the compatibility fallback.

If robust GUI choice validation is not possible without a fragile invasive mixin, the guarded affection/progress actions must still independently enforce idempotency, limits, age/romance safety, and active topic context. Record that residual limitation honestly.

### 7.3 Chat-mode decision behavior

Current chat matching always includes global topic intents alongside context-scoped intents. For deeper trees, prevent accidental topic jumps from masquerading as an answer.

When a live decision question exists:

- score system controls such as farewell and decline;
- score only context intents bound to the current question and currently offered answers by default;
- permit an explicit subject change only through strong, separately tested wording;
- do not let a weak global topic match beat a valid contextual response;
- use `Session.currentAnswers`, which is already captured, rather than checking only that an answer exists somewhere in the datapack;
- preserve “never mind,” “something else,” and “goodbye” as neutral exits.

Every choice intent must provide multiple natural paraphrases and enough distinguishing evidence. Expand the addressed-utterance suite and negative player-chatter corpus as the intent count grows.

### 7.4 Chat feedback

Continue using the existing villager voice and delayed delivery path. Heart feedback must show the actual guarded delta, not the requested authored delta. A multi-line result shows the feedback once.

Do not expose raw disposition numbers, check scores, or probabilities to ordinary players. Those belong only in opt-in debug logs/commands.

---

## 8. Content plan: every shipped topic

The following is the minimum content matrix. The coding agent may improve wording and branch details, but may not omit a row without explicitly changing scope and documenting why.

### 8.1 Greeting and mundane conversation

| Topic | Opener context | Required player stances | Required follow-through/consequence |
|---|---|---|---|
| Check-in (`greet/checkin`) | current mood/state, recent check-in, age | listen, celebrate, offer support, give space, exit | respond to good vs rough answer; a dismissive response may lose affection; route back to main only after resolution |
| Day | mood, chore, time, age, recent ask | empathize, ask what happened, celebrate, offer practical help, tease/dismiss, exit | rough/good/busy branches must differ; support may build Trust/Warmth, dismissing a rough day may lose hearts |
| Food | trait/diet disclosure, age, first/revisit | share preference, ask recipe, respect a restriction, playful disagreement, mock/dismiss, exit | never make a medical/diet trait the butt of a rewarded joke; remember a shared favorite or promised food callback sparingly |
| Weather | clear/rain/storm, profession relevance, fear state | discuss crops/work, offer shelter/help, enjoy it, joke, dismiss concern, exit | storm concern and welcome rain require different reactions; small stakes, real ± affection possible |
| Season/holiday | season, holiday, profession, prior holiday talk | share a tradition, invite/accept, complain honestly, ask what they enjoy, dismiss, exit | holiday invitation or tradition may create a short callback; no repeatable holiday heart farm |

### 8.2 Work, village, and events

| Topic | Opener context | Required player stances | Required follow-through/consequence |
|---|---|---|---|
| Work | profession-specific line, likes/hates work, chore, personality | praise craft, ask why it matters, offer practical idea, challenge dissatisfaction, belittle, exit | professional pride favors Respect; listening to burnout favors Trust; route through a decision page after the auto profession line |
| Work offer / quest | quest availability/status, relationship | ask what is needed, ask terms, volunteer, decline respectfully, refuse rudely | open the quest UI only after explicit acceptance; opening/declining is not a free heart reward; quest completion owns the major payoff |
| Village/home | has village/home, village name, resident status | affirm home, ask what should improve, offer help, criticize constructively, insult the place, exit | distinguish resident pride, dissatisfaction, and homelessness; store only promises that later systems can honor |
| Neighbors/people | personality, relationship, recent gossip, age | agree, defend neighbors, ask for an example, empathize with overwhelm, encourage reconciliation, gossip, exit | respectful disagreement can build Respect; pushing gossip after discomfort raises Tension |
| News | actual event or no-news fallback | compassion, celebration, curiosity, skepticism, refuse gossip, cruel amusement, exit | outcome must be event-type-aware; death/divorce is sensitive, birth/marriage celebratory, arrival/departure mixed |
| Rumors | actual rumor context, trust, personality | listen, ask source, challenge reliability, promise discretion, spread/encourage, exit | discretion can build Trust; exploiting sensitive gossip can lose it; do not invent a promise that has no callback |
| “How have you been, in yourself?” / noticed | grieving/elated/annoyed/proud/default state | validate, encourage, offer help, give space, dismiss, exit | short-term state drives reply; an empathetic response should be meaningfully different from generic check-in |

### 8.3 Personal topics

| Topic | Required shape | Required durable content |
|---|---|---|
| Life story | ask which part matters -> respond with empathy/curiosity/comparison/judgment -> follow one thread | Familiarity-gated later chapter; callback to what the villager chose to reveal |
| Dreams | preserve encourage and ask-more; add practical help, gentle realism, skepticism, exit; follow up on what action they might take | dream arc with a later progress callback; promises to help must be tracked or not offered |
| Fears | preserve comfort/challenge/press/share; add a second decision after non-terminal outcomes and a repair route after rebuff | real arc stage in check seed; boundaries; revelation milestone; pushing after a refusal can create a scar |
| Hopes | listen, encourage, ask first step, share a hope, question realistically, mock/dismiss, exit | later “still hoping” callback and optional action stage |
| Feelings about player | mutual, uncertain, candid concern, ask needs, set boundary, exit; strict relationship/age paths | adult romance eligibility for attraction; platonic/family alternatives; no romantic content for children/teens |
| Regrets | listen without absolving, offer forgiveness, ask about repair, challenge, pry, exit | staged disclosure and possible repair intention; do not reward forced forgiveness as universally correct |
| Secret | accept confidence, decline responsibility, promise discretion, ask why they chose the player, press for more, betray/trivialize, exit | one-shot revelation; explicit exclusive promise state; later callback that proves the game remembered |

### 8.4 Spouse and family topics

| Topic | Required player stances | Required consequence/callback |
|---|---|---|
| Are you happy with us? | listen, affirm, ask what could improve, become defensive, dismiss | relationship needs branch; not every concern is solved by reassurance; later opener reflects unresolved issue |
| Remember when we met? | share own memory, tease warmly, correct gently, ask what stood out, brush off | one shared-memory flag or variant; avoid repeated heart payout |
| Our future | align, negotiate a difference, ask priorities, avoid commitment, reject harshly | persistent preference or discussion stage; never promise world-changing features the mod cannot observe |
| Is anything weighing on you? | validate, help solve, ask for detail, give space, dismiss | short-term worry memory and later check-back; support must suit whether the villager wanted listening or action |
| Check in with child | listen, praise, guide, play along, dismiss | age-appropriate Warmth/Trust effects; never reuse adult romance/check language |
| Parent asks player | answer honestly, reassure, ask what worries them, deflect, snap | family Trust/Respect path and later callback |
| Family memories | laugh/share, ask more, add own memory, question detail gently, brush off | bounded family-story progression; template names must fail safely when relatives are absent |

### 8.5 Choice distribution requirements

Across the content set, ensure all of the following are true:

- every topic has a neutral exit;
- every topic has at least one plausible path that can gain affection;
- every topic has at least one plausible path that can lose affection, though it need not be cartoonishly rude;
- at least one reasonable disagreement path can be neutral or positive for personalities that value candor;
- not every positive path is empathy, and not every negative path is directness;
- deep topics use Tier 4 durable shifts and selected Tier 5 milestones;
- mundane topics normally remain in consequence tiers 1–3 and do not set permanent flags without a concrete callback.

---

## 9. Authoring pattern

The exact JSON must be validated against MCA, but each topic should follow this conceptual pattern.

### 9.1 Starter/opener

```json
{
  "name": "day",
  "results": [
    {
      "baseChance": 0,
      "conditions": [
        {"chance": 100, "mood": "sad"},
        {"chance": -2000, "age_group": "toddler"},
        {"chance": -2000, "conversations_disabled": "branching"}
      ],
      "actions": {
        "say": "conversations.day.rough",
        "next": "conversations.topic.day.rough.respond",
        "conversations_session": {"op": "begin", "topic": "day", "budget": "quick"},
        "conversations_record": {"id": "mcaconversations.cooldown.day", "var": "player", "time": 12000}
      }
    },
    {
      "baseChance": 1,
      "conditions": [
        {"chance": -2000, "conversations_enabled": "branching"}
      ],
      "actions": {
        "say": "conversations.day.rough",
        "next": "conversations.cat.chitchat"
      }
    }
  ]
}
```

Important properties:

- the opener has no affection action;
- its result routes to a context-specific response node;
- a branching-disabled fallback preserves the old shallow behavior;
- first/repeat/revisit memories select the opener but do not skip the response tree; a repeat can route to a shorter “apologize / explain / change subject” node rather than granting another normal payoff.

### 9.2 Player decision

```json
{
  "answers": [
    {
      "name": "empathize",
      "results": [
        {
          "baseChance": 0,
          "conditions": [
            {"chance": 1000, "conversations_check": {
              "id": "day.rough.empathize", "tier": "success",
              "axis": "warmth", "difficulty": 20
            }},
            {"chance": -2000, "conversations_disabled": "checks"}
          ],
          "actions": {
            "say": "conversations.day.rough.empathize.success",
            "next": "conversations.topic.day.rough.followup",
            "conversations_affection_apply": {
              "decision": "day.rough.empathize.success", "delta": 1,
              "budget": "quick", "policy": "daily_repeat"
            },
            "conversations_disposition_apply": {
              "topic": "day.rough.empathize", "deltas": {"warmth": 2, "trust": 1}
            }
          }
        }
      ]
    },
    {
      "name": "leave",
      "results": [
        {"baseChance": 1, "actions": {"next": "conversations.cat.chitchat"}}
      ]
    }
  ]
}
```

The real checked answer must define crit, success, partial, rebuff, and disabled fallback results. The abbreviated sample does not waive that existing lint requirement.

### 9.3 Do not put all choices behind checks

Use checks when uncertainty is narratively meaningful: challenge, flirtation, pressing, difficult candor, repair, or a major request. Ordinary listening, exiting, asking a simple follow-up, and honoring a clear boundary should usually be deterministic.

The goal is character responsiveness, not turning every line into a dice roll.

---

## 10. Localization and voice

### 10.1 Required locales

English (`en_us`) and Brazilian Portuguese (`pt_br`) remain release-blocking and must have exact key and placeholder parity. Do not land English-only trees with the intent to translate later unless the feature is explicitly hidden from release builds.

### 10.2 Line families

For each node author:

- one question prompt;
- one player button label per answer;
- villager reaction lines for every reachable outcome;
- callback/resume lines for stored state;
- age-specific lines where the node is reachable by toddlers, children, or teens;
- generic fallbacks for optional world, quest, relationship, family, or template data.

Use `/1`, `/2`, etc. variants for frequently repeated villager reactions. Do not use variants to conceal different mechanical consequences under one key.

### 10.3 Personality overlays

Do not multiply every new line by all overlay namespaces indiscriminately. Prioritize personality flavor where players hear it most:

- topic openers;
- high-frequency mundane reactions;
- check crit/rebuff lines;
- milestone and callback lines;
- category/evolving openers.

All content still needs a strong base voice. Overlay parity must continue to honor canonical 7.7 personalities, legacy aliases, and the legacy-only athletic overlay through `Personalities.overlayPrefixes()`.

Player answer text represents the player, not the villager; do not personality-flavor the player's words.

### 10.4 UI constraints

- Keep 2–5 visible answers per node, with 3–4 preferred.
- Test the smallest supported GUI scale.
- Keep button wording concise while preserving actual speech.
- Keep villager lines readable without a custom scrolling screen.
- If five choices clip in upstream MCA UI, reduce or split the node; do not introduce a custom screen only to retain excess choices.

---

## 11. Configuration and compatibility

### 11.1 Feature toggle

Add a `branching` feature gate, defaulting to enabled once the feature is production-ready. When disabled:

- existing topic buttons remain;
- each starter uses a plain legacy-style one-line result and returns to its category;
- no branch session, arc, guarded affection, or new chat context is required;
- native MCA Chat/hub fallback behavior remains intact.

Do not accomplish the off-state by removing answers and leaving empty pages. Use explicit result fallbacks and lint them.

### 11.2 Economy settings

Add only settings operators can meaningfully reason about:

- global conversation-heart multiplier;
- per-day positive and negative caps;
- optional stronger-negative-outcomes toggle if desired;
- session timeout;
- branch debug logging.

Prefer depth-class budgets in data/code defaults rather than exposing dozens of per-topic knobs.

### 11.3 Save migration

- Preserve existing `mcaconversations.topic.*`, `cooldown.*`, unlock, state, gossip, and quest memories.
- Treat old topic/cooldown memories as opener history, not completed arc stages.
- Version every new SavedData schema.
- Migrate known old versions field-by-field; do not copy the current `DispositionStore` behavior of dropping all non-current versions for future schemas that hold milestones or affection guards.
- Ignore malformed UUIDs, unknown flag IDs, out-of-range stages, and future fields safely.
- Clamp counters and stages on load.
- Back up or use atomic replacement for any generated content rewrite.

### 11.4 Datapack compatibility

- Keep category question IDs and starter answer names stable.
- Give all new questions `conversations.*` IDs to reduce collisions.
- Continue allowing third-party packs to merge additional answers.
- Document the new custom conditions/actions and catalog conventions in `DATAPACK.md`.
- A third-party answer without a catalog entry may remain supported but is outside the “full tree guaranteed” lint unless it opts into catalog metadata.

---

## 12. Implementation sequence

Each vertical slice must include Java/data, English and Portuguese localization, chat intents, lint, unit tests, and production verification. Do not postpone parity work until all dialogue writing is finished.

### Phase 0 — Evidence and executable baseline

Before modifying behavior:

1. Record current test/build status and current content counts.
2. Inspect the exact MCA 7.6 and 7.7 methods for:
   - question/answer selection;
   - valid-answer generation;
   - network submission handling;
   - heart mutation and analysis feedback;
   - action execution order;
   - dialogue response packets.
3. Confirm which narrow hook can track GUI offered choices and submissions.
4. Produce a small generated graph report of current questions, answers, next edges, heart actions, and unreachable nodes.
5. Reconcile this document, `DATAPACK.md`, `chat-mode-spec.md`, and `MCA-Conversations-1.0.0-RPG-Expansion.md` against code. Record discrepancies rather than implementing from prose blindly.

Stop and report if a required MCA hook cannot be made soft-fail across supported versions.

### Phase 1 — Foundations

Implement before broad content conversion:

1. conversation catalog schema and loader for lint;
2. graph analysis/lint framework;
3. transient shared conversation session registry;
4. guarded affection action and persistent bounded ledger;
5. server-side idempotency and best available offered-choice validation;
6. progress conditions/actions for arc stage and milestones;
7. interiority/personality-fit data registry;
8. real `Dispositions.baseline`, `personalityFit`, and `arcStage` inputs;
9. `branching` feature fallback;
10. debug output for topic, node, decision, check inputs/tier, requested/applied hearts, vector deltas, and progress transition.

No large content migration should begin until a unit-tested vertical slice proves these foundations.

### Phase 2 — Pilot vertical slices

Convert two deliberately different topics:

- Day: mundane, mood/chore-aware, short, repeatable, small stakes.
- Fears: deep, already checked, disposition-aware, boundary and arc capable.

These two slices must prove:

- opener rewards moved downstream;
- GUI and chat traverse identical nodes;
- at least two player decisions occur;
- positive, neutral, and negative outcomes all work;
- daily/session caps and duplicate suppression work;
- disabled branching/checks/dispositions degrade cleanly;
- en_us/pt_br and overlay behavior remain correct;
- live production runtime works.

Revise the authoring pattern based on this pilot before multiplying content.

### Phase 3 — Mundane completeness

Convert check-in, food, weather, and season/holiday. This phase is a hard product milestone: no mundane starter may remain a one-click response.

Use mostly deterministic reactions and low-stakes consequences. Add graph lint proving every Quick topic meets its minimum decision depth.

### Phase 4 — Work, village, events, and gossip

Convert work, work offer, village, neighbors, news, rumors, and noticed/state check-in. Add event-type-aware gossip session context and explicit quest acceptance before UI handoff.

### Phase 5 — Personal arcs

Convert life, dreams, hopes, regrets, and secrets; deepen fears. Add callbacks, ordered stages, exclusive promises, and boundary consequences. Complete deep-topic reachability simulations before enabling by default.

### Phase 6 — Feelings, spouse, and family

Convert feelings and every `conversations.us` and `conversations.family` starter. Run exhaustive age, spouse, family, pregnancy/children, and romance-eligibility matrices. Attraction must remain structurally unreachable for children, teens, and ineligible adults.

### Phase 7 — Content and balance pass

1. Walk every path in both languages.
2. Review stance variety and remove “obvious correct button” patterns.
3. Compare heart gain per in-game day with native MCA progression.
4. Verify personality fit produces difference without caricature.
5. Expand high-value overlay pools.
6. Profile graph reload, session maps, and SavedData growth.
7. Update README, CONFIG, DATAPACK, CHANGELOG, and release notes.

---

## 13. Required automated validation

### 13.1 Graph lint

Extend `ContentLintTest` or add a dedicated `ConversationGraphLintTest` that fails on:

- a catalog entry whose starter does not exist;
- a substantive starter that is missing from the catalog;
- an opener that does not reach a decision node when branching is enabled;
- a Quick topic with fewer than two player decisions on any normal adult path;
- a Standard/Deep/Relationship topic below its declared minimum depth;
- a path exceeding the maximum depth without an explicit cross-session boundary;
- a decision node with fewer than two valid substantive/exit answers or more than five total answers;
- a node with no neutral exit;
- a missing `next` target;
- an orphan `conversations.topic.*` or `conversations.arc.*` node;
- an unintended cycle;
- any reward-bearing cycle;
- a terminal path that does not return/quit deliberately;
- affection, disposition, or progress effects on navigation or opener nodes;
- raw native heart actions inside branching content after migration;
- a consequence-bearing answer without a stable decision ID and replay policy;
- a path whose possible heart total exceeds its class budget;
- a topic without at least one positive-capable and one negative-capable path;
- a milestone with no callback reader;
- a callback with no possible writer;
- an exclusive group with zero or multiple simultaneously set paths;
- an arc transition outside declared stage bounds;
- a romantic/Attraction path reachable by an invalid age or relationship state.

### 13.2 Result-state lint

Generalize the existing checked-answer state-space simulation so every deterministic consequence answer resolves to exactly one behavioral result for every modeled combination of:

- feature toggles;
- check tier;
- relevant disposition range;
- required/forbidden memory;
- arc stage;
- exclusive choice/milestone;
- age/romance eligibility;
- world/gossip context where modeled.

If the state space becomes too large to simulate, simplify the answer. Do not waive determinism.

### 13.3 Chat parity lint

For every non-exit answer on a catalog-reachable decision node:

- require at least one context-scoped intent bound to the exact question/answer;
- require the context to name the exact current question;
- verify the answer can appear in captured `currentAnswers` under some valid state;
- require a minimum phrase/keyword evidence floor;
- require test utterances in en_us and a documented decision about pt_br input matching support;
- verify the intent is inert outside its context;
- verify system decline/farewell remains available;
- verify common player-to-player chatter does not trigger it ambiently.

### 13.4 Localization lint

Keep existing key/placeholder parity and add:

- every reachable prompt, answer, result, fallback, and callback resolves in both base locales;
- no dead generated keys;
- no missing or gapped variant sequences;
- configurable maximum button-label and prompt lengths, with an allowlist for reviewed exceptions;
- player answer labels do not accidentally depend on personality overlay keys;
- every templated family/friend/gossip variable has a neutral fallback.

### 13.5 Unit tests

Add focused pure tests for:

- affection delta parsing, clamp, multiplier, positive/negative budgets, repeat diminishing, day rollover, and idempotency;
- progress store version migration, bounds, exclusivity, prune, malformed input, and round trip;
- session begin/update/end/timeout, target switch, duplicate transaction, and answer validation;
- personality/interiority loader, alias resolution, deterministic selection, and bounded check fit;
- check input assembly using actual personality fit and arc stage;
- gossip context lifecycle and missing subject fallback;
- branching feature fallbacks;
- chat filtering by current question and currently offered answers.

### 13.6 Representative path simulations

At minimum simulate these end to end:

1. rough day -> empathize -> offer help -> small positive outcome;
2. rough day -> mock -> villager objects -> player apologizes -> partial repair, not net farming;
3. storm -> dismiss concern -> small loss -> repeat same day produces no additional exploit;
4. food trait disclosure -> respectful curiosity -> positive; mock trait -> negative and safe line;
5. work dissatisfaction -> practical challenge fits one personality but rebuffs another under deterministic checks;
6. death gossip -> compassion vs cruel amusement produce different results;
7. fear below Trust gate -> guarded reply and safe exit;
8. fear crit -> revelation milestone once -> later callback;
9. push after fear refusal -> boundary scar -> repair route remains;
10. secret -> promise discretion -> later callback; alternate refusal path cannot also set promise;
11. spouse future disagreement -> respectful negotiation remains viable;
12. child/teen traversal -> no Attraction read, check, write, or romantic line;
13. GUI and chat perform the same decision and produce identical state deltas;
14. duplicated/stale packet -> no duplicate hearts/vector/progress;
15. every RPG/branch toggle combination -> live fallback and no empty node.

---

## 14. Production verification

Unit tests are necessary but insufficient. Build the reobfuscated jar and test in a production-style Forge instance with MCA.

Verify:

- fresh world and upgraded pre-rework world;
- single player and dedicated server;
- two players independently conversing with the same villager;
- GUI entry in ADDITIVE, REPLACE, and HIDDEN hub modes;
- free-text chat entry, contextual follow-ups, subject change, farewell, and timeout;
- villager busy with another player's GUI;
- reload while no session is active and reload during/after a session;
- relog/restart persistence for arcs, milestones, affection guards, and disposition;
- session state correctly does not persist;
- small GUI scale and long translated strings;
- toddlers, children, teens, adults, spouses, family members, and unrelated villagers;
- every supported personality, including legacy aliases where practical;
- no-news gossip fallback and each event type;
- MCA: Quests absent and present;
- Serene Seasons absent and present;
- each new configuration off-state;
- rapid clicking, repeated topic farming, re-open check scumming, and duplicate message attempts;
- server tick and save-data growth with a representative populated village.

Record exact jar versions and evidence. Do not report integration success based on `runClient`.

---

## 15. Documentation deliverables

Update in the same release:

- `README.md`: explain that the player's response, not asking the question, shapes hearts; show mundane and deep examples.
- `CONFIG.md`: branching, economy, timeout, and fallback behavior.
- `DATAPACK.md`: catalog schema, node conventions, affection/session/progress actions and conditions, full checked example, chat parity requirements.
- `chat-mode-spec.md`: live decision filtering, offered-answer validation, subject changes, and session lifecycle.
- `CHANGELOG.md`: migration and balance changes, including removal of automatic opener affection.

Also add a concise content-authoring checklist so future topics cannot regress into one-click responses.

---

## 16. Definition of done

The rework is complete only when all of the following are true:

- every substantive topic listed in Section 8 is in the catalog and enters a bounded choice graph;
- every normal adult mundane topic contains at least two player decisions after its opener;
- deep and relationship topics have persistent, perceivable continuation;
- asking a topic never grants hearts by itself;
- player choices can produce positive, neutral, and negative heart outcomes;
- consequences are guarded, capped, repeat-resistant, and packet-idempotent;
- personality fit, actual arc stage, mood, hearts, and disposition can influence checked outcomes;
- no player-facing disposition number or check probability is introduced;
- GUI and chat reach the same result actions and are both fully covered;
- all English and Brazilian Portuguese content is present and lint-clean;
- age, family, spouse, and romance safety matrices pass;
- navigation and exits are always safe and effect-free;
- all automated tests pass;
- every representative production path has recorded verification;
- fallback behavior remains playable when branching, checks, dispositions, gossip, quests, world context, or templates are disabled.

---

## 17. Coding-agent working rules

1. Inspect before editing, especially MCA bytecode/API behavior and existing dirty worktree changes.
2. Preserve the existing data-driven engine and compatibility boundary.
3. Implement one vertical slice at a time; do not mass-generate dialogue before the pilot proves the grammar.
4. Add the guard and lint in the same change as every new reward surface.
5. Add chat intent and both locale entries in the same change as every new GUI answer.
6. Keep mixins narrow, soft-failing, and scoped to this mod's questions.
7. Treat state and graph IDs as stable serialized API once released.
8. Prefer bounded maps, lazy time calculations, reload-time compilation, and event-driven writes; add no per-villager per-tick conversation work.
9. Never delete or overwrite existing user content or translations mechanically without a reviewed migration/diff.
10. At the end of each phase, report changed files, graph/content counts, tests run, production checks completed, known gaps, and the next safe phase.

If any requirement conflicts with verified MCA behavior, stop at that phase boundary, document the exact conflict and evidence, and propose the smallest compatible alternative. Do not silently weaken affection safety, frontend parity, or the “every topic becomes a real conversation” requirement.
