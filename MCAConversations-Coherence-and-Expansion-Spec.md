# MCA: Conversations — Coherence-First Conversation Overhaul and Expansion Specification

**Target repository:** [`otectus/MCAConversations`](https://github.com/otectus/MCAConversations)  
**Reviewed revision:** [`16b5ab95ed3c1dae3d8023a53e1b518a367de089`](https://github.com/otectus/MCAConversations/commit/16b5ab95ed3c1dae3d8023a53e1b518a367de089) (`main`, 2026-08-26)  
**MCA Reborn reference:** [`Luke100000/minecraft-comes-alive`, branch `1.20.1`](https://github.com/Luke100000/minecraft-comes-alive/tree/1.20.1), reviewed at [`4d824551b30654e5792e19e84f3933e3e3d90ea2`](https://github.com/Luke100000/minecraft-comes-alive/commit/4d824551b30654e5792e19e84f3933e3e3d90ea2)  
**Platform:** Minecraft 1.20.1, Forge 47.x, MCA Reborn 7.6/7.7 compatibility line  
**Current mod version:** `1.3.0-alpha.1`  
**Recommended release scope:** a major content and coherence update; use `1.4.0` only if save/data compatibility remains fully additive, otherwise treat it as the foundation of `2.0.0`  
**Audience:** a coding agent implementing the overhaul, followed by a human dialogue editor performing semantic acceptance review

---

## 1. Required outcome

Rebuild the authored conversation experience around one non-negotiable rule:

> Every player reply must be a direct, intelligible response to the exact meaning of the villager's preceding statement, and every villager reaction must be a direct, intelligible response to the exact meaning and tone of the player's selected reply.

The update must also make villagers feel like particular people living particular lives, rather than generic line dispensers with a profession noun or personality accent occasionally inserted. Profession, age, personality, mood, relationship, prior conversations, family role, village state, world context, recent events, and supported optional integrations should affect what a villager chooses to discuss, what they reveal, how they phrase it, which replies are reasonable, and how they remember the exchange.

This is not a request to add more random strings to the existing pools. It is a request to replace generic semantic funnels with authored, context-specific conversation beats and to make incoherent paths structurally impossible.

The implementation is complete only when:

1. no current or newly authored path presents a reply that presupposes something the villager did not say;
2. no hostile, rejecting, grieving, guarded, joyful, or intimate outcome is routed into a generic follow-up that assumes a different emotional state;
3. all supported professions have genuine multi-turn work conversations, not merely a unique opening sentence;
4. high-value conversations remember specific facts and player stances and later call them back accurately;
5. GUI and typed chat mode expose the same semantic choices;
6. all of this remains server-authoritative, datapack-friendly, bounded in cost, and compatible with the supported MCA package/API variants.

---

## 2. Executive diagnosis

The repository already contains much more machinery than the player-facing problem suggests. It has branching topics, conversation sessions, guarded affection, disposition axes, checks, narrative progress, memories, gossip, templates, age routing, personality conditions and overlays, chat intents, optional integrations, and extensive structural tests. A wholesale engine rewrite would discard good work and create unnecessary compatibility risk.

The actual defect is that the system models **graph structure and consequences**, but not the **meaning of an utterance**. Many semantically different NPC lines flow into the same response question. MCA then displays the same fixed reply buttons regardless of which line won. Later, multiple radically different NPC reactions flow into the same follow-up question. The graph is reachable, localized, balanced, and mechanically valid while the conversation itself makes no sense.

### 2.1 Current corpus snapshot

The reviewed revision contains:

| Measure | Current value |
|---|---:|
| Catalogued topics | 28 |
| Dialogue question files | 173 |
| Authored answers | 663 |
| Result branches | 1,057 |
| Referenced NPC `say` pools | 905 |
| Base English NPC variants referenced by those pools | 2,611 |
| Base `mca_dialogue` English keys | 3,679 |
| Chat intents | 399 |
| Personality/legacy overlay namespaces | 21 |
| Unique referenced `say` pools with any personality overlay | 27 of 905 (about **3.0%**) |
| Explicitly rostered professions | 37 |

These numbers matter because they show that the problem is not a simple shortage of strings. The mod already has a large corpus. The next update must improve semantic topology, specificity, callback state, and editorial discipline before simply increasing volume.

### 2.2 What is already strong and should be retained

Retain and build on:

- MCA JSON as the authoritative dialogue engine;
- the category hub and the current 28-topic catalog;
- `ConversationSession`, guarded packet submission, per-conversation budgets, and idempotency;
- `conversations_affection_apply`, disposition axes, checks, and progress ledgers;
- parse-safe personality handling across MCA 7.6 and 7.7;
- age-specific branches and the current distinction between quick, standard, deep, relationship, and service conversations;
- chat-mode intent loading and current constraint enforcement;
- locale parity, placeholder validation, pool-size checks, graph reachability, path-budget simulation, and optional-mod soft failure;
- the runtime-resolved MCA compatibility layer and its probe suite.

The architecture needs one new conceptual layer—a semantic contract for each turn—and a large content migration onto that layer.

---

## 3. Why the current conversations fail

### 3.1 MCA exposes answers by question, not by the result that led there

MCA's `Question#getValidAnswers` filters answers using answer-level `constraints`. It does **not** use result-level conditions to decide which buttons to show. Result conditions are evaluated only after the player selects an answer, to choose the resulting villager reaction.

That has a decisive authoring consequence:

> If two NPC statements require different reasonable player replies, they must not route to the same response question merely because they share a broad topic.

Adding more `mood`, `profession`, `personality`, or memory conditions to the results under a shared answer cannot make an inappropriate button disappear. The reliable fix is to route each semantic beat—or a genuinely equivalent family of beats—to a response question whose entire answer set fits every inbound statement.

### 3.2 Work dialogue is a semantic funnel

The current adult work router contains 40 possible opener results:

- 37 exact profession results;
- a work-hating state line;
- a generic templated profession line;
- a general work-liking line.

Every one routes to `conversations.topic.work.respond`, which always offers:

- “You're good at what you do.”
- “Why does it matter to you?”
- “You don't sound like you enjoy it.”
- “You'd say that whether it was true or not.”
- “I'll let you work.”

That reply set cannot be correct for all 40 meanings. Most profession lines are proud, comic, matter-of-fact, ethically reflective, or descriptive. For example, a farmer can say that weeds “negotiate hardest,” an armorer can explain that every dent represents a funeral that did not happen, and a florist can describe bouquets as apologies, courtships, and funerals. “You don't sound like you enjoy it” is not a coherent inference from many of those statements.

The second turn is worse. Praise, curiosity, and challenge can produce eight very different reaction pools, all of which route to `conversations.topic.work.followup`. That node always offers:

- “Have you tried it the other way?”
- “It sounds like it's wearing you down.”
- “Anyone could do that job.”
- “So you're the reason it all still works.”
- “I'll let you work.”

After the villager accepts a compliment with “Thank you,” the player can inexplicably suggest doing an unspecified process “the other way.” After the villager rejects the player's inference with “I never said that. You decided it for me,” the player can ignore the rebuke and diagnose burnout. The graph has two decisions, but the second decision is not grounded in the exchange.

Profession customization therefore currently stops at the opening line. The conversation immediately collapses back into a profession-neutral tree.

### 3.3 Other confirmed semantic funnels

The work tree is the clearest case, not the only one.

| Shared node | Inbound meanings currently mixed together | Example of resulting mismatch | Required correction |
|---|---|---|---|
| `conversations.topic.noticed.followup` | apology accepted, explanation requested, apology rejected, elation shared, elation deflated, ordinary wellbeing, grief validated, grief dismissed | After “Then we've nothing to discuss. Good day,” the player is still offered “Tell me what would help.” After a joyful line, the same help/grief-shaped options appear. | Split into annoyed-repair, annoyed-boundary, elated, fine/opening-up, grieving-supported, and grieving-hostile continuations. |
| `conversations.topic.standing.respond` | repeated query, unresolved incident, bad reputation, good reputation, neutral/unknown reputation | A well-regarded player is offered “How could I make things right?” even though nothing is wrong. | Route to `standing.good`, `standing.neutral`, `standing.bad`, `standing.incident`, and `standing.repeat` response nodes. |
| `conversations.topic.checkin.good.followup` | gratitude, concern about the injured player, ordinary happiness, flirtation, holiday enjoyment, and the NPC being hurt by a deflating reply | After “You could have let me have the week,” the next buttons include “Whatever you're doing, keep at it” and “I'll let you enjoy it.” | Split positive continuation, concern-for-player, romantic continuation, holiday continuation, and repair-after-deflation. |
| `conversations.topic.food.normal.followup` | agreement about plain food, recipe explanation, seasonal availability, playful disagreement, and irritated disagreement | “I'll bring you some” or “Where do you get it?” can appear when no particular food was established. | Split preference, recipe, sourcing, and disagreement continuations; require a concrete food fact before offering to bring or source it. |
| `conversations.topic.people.followup` | agreement, accepted correction, rejected correction, temporary irritation, and a request for a specific example | The player can push for “who's the worst?” immediately after the NPC admits they were unfair, or joke after being told they are too new to understand. | Separate gossip invitation, repair/softening, defensive boundary, and concrete-example branches. |
| `conversations.topic.life.followup` | thoughtful disclosure, low mood, empathy accepted, judgment rebuked, and honest silence appreciated | After “I told you that in confidence, not for marking,” the player can immediately say “Tell me more” or make a mayor joke. | Route judgment rebuffs to apology/back-off/repair, not the disclosure continuation. |
| `conversations.topic.news.followup` | celebratory, tragic, skeptical, amused, and sour reactions | “Spread it” and “How are they?” are not universally suitable, especially when no identifiable subject exists. | Carry event type and named/unnamed subject facts into distinct response nodes. |
| generic `*.close` nodes | warmth, boundaries, rejection, humor, confession, and hostility | “Thank you for trusting me” or “Tell me what that means” may follow an outcome that explicitly closed the subject. | Make close nodes contract-specific: warm close, neutral close, repair close, boundary close, and hard exit. |

These nodes should be treated as the first remediation set, but the coding agent must not assume the table is exhaustive. Generate and inspect every inbound-line → response-buttons adjacency in the full graph.

### 3.4 Variant pools can hide probabilistic non-sequiturs

MCA resolves `/1`, `/2`, and later variants client-side for GUI dialogue. The server generally knows the base key but not which exact variant a client displayed. Consequently, every variant under one base `say` key must make the same claims, carry the same emotional posture, and support the same replies.

The current lint contains a small hand-curated noun check that catches cases such as a reply mentioning a cat when only one rough-day variant mentioned a cat. That is useful but incomplete. It does not detect:

- one variant asking a question while another makes a declaration;
- one variant expressing pride while another expresses burnout;
- one variant naming a concrete person while another does not;
- one variant inviting advice while another asks only to be heard;
- one variant closing a boundary while another invites follow-up;
- one variant describing a current task while another describes a lifelong philosophy.

All members of a pool must share a declared semantic contract. Different props and anecdotes are welcome only when subsequent choices respond to the common meaning rather than to a variant-specific prop.

### 3.5 Personality is present mechanically but sparse in the actual conversation surface

The mod supports the 16 canonical MCA 7.7 personalities, four MCA 7.6 aliases, and the legacy-only athletic voice. It also uses personality in checks and selection conditions. However, only about 27 of 905 referenced NPC `say` pools have any personality overlay in the current English corpus.

Personality therefore often changes the probability or consequence of a player's stance without changing:

- what subject the villager chooses;
- which aspect of that subject matters to them;
- how readily they disclose;
- whether they ask for advice, sympathy, space, or practical help;
- how they narrate their work and place in the village;
- which fact they remember and revisit.

The overhaul should use personality primarily to influence **topic selection, disclosure, interpretation, and conversational goals**, with voice overlays as a secondary surface treatment.

### 3.6 Structural tests cannot prove dialogue sense

The current test suite correctly validates many important invariants: known conditions/actions, valid vocabularies, localization, placeholder arity, pool floors, profession roster coverage, graph reachability, answer uniqueness, path depth, heart budgets, gated checks, progress, and several hand-authored scenarios.

What it does not yet possess is a general representation of:

- what the villager just communicated;
- what facts a reply presupposes;
- whether a reply acknowledges or contradicts the NPC's stance;
- whether an outcome invites or rejects further discussion;
- whether the next node's buttons remain valid after every possible result tier.

That missing representation is the core technical deliverable of this update.

---

## 4. Product requirements

### 4.1 Non-negotiable requirements

1. **Exact adjacency:** every visible player line must answer the immediately preceding NPC line.
2. **Exact reaction:** every NPC result must respond to the selected player line, including its tone.
3. **Exact continuation:** every next node must fit the result tier that entered it.
4. **Profession depth:** every supported adult profession must receive a real work pack with multiple subjects, problems, beliefs, stories, and callbacks.
5. **Personal relevance:** context changes subject matter, not only phrasing or score.
6. **Persistent specificity:** important disclosures and player promises are stored under stable semantic ids and referenced accurately later.
7. **Age integrity:** toddlers, children, teens, and adults never share a line or reply merely because it is convenient when their conceptual understanding differs.
8. **Relationship integrity:** stranger, friend, spouse, parent, child, and hostile/tense relationships do not receive interchangeable intimacy.
9. **Consequence honesty:** the visible wording of a choice accurately signals warmth, curiosity, humor, challenge, dismissal, pressure, flirtation, or exit.
10. **No false affordances:** do not offer to solve, fetch, repair, escort, or undertake a job unless the branch either performs a real supported action or clearly frames it as conversational roleplay without promising gameplay.
11. **Datapack extensibility:** third-party authors must be able to add profession packs and semantic beats without Java patches.
12. **No runtime AI requirement:** all shipped dialogue remains deterministic, local, server-authoritative authored content; no network calls or generated text at runtime.

### 4.2 Explicit non-goals

- Do not fork or replace MCA's entire dialogue engine.
- Do not make every line unique to every combination of personality × profession × mood × age; use layered specificity to avoid an impossible combinatorial corpus.
- Do not turn conversations into an affection min-max quiz with one obvious “correct” response.
- Do not add per-villager per-tick scanning or large unbounded histories.
- Do not rewrite stable reward, progress, gossip, reputation, or compatibility systems unless required by the semantic-turn model.
- Do not use an LLM to generate dialogue at runtime.

---

## 5. The semantic turn contract

### 5.1 Definition

A **conversation beat** is the smallest unit that has one stable meaning from the player's perspective. It consists of:

- the NPC speech pool;
- the context under which that pool can be selected;
- the NPC's speech act and emotional posture;
- facts established by the line;
- the response question opened after the line;
- the complete set of player stances that are sensible in response;
- any durable fact or callback identity associated with the beat.

A **reply contract** describes what one player answer means, what it presupposes, and which outcome families it may legally enter.

An **outcome contract** describes whether the NPC accepts, appreciates, questions, resists, rebuffs, closes, or escalates the selected stance, and which next response contracts remain valid.

### 5.2 Required beat fields

Add datapack-loaded metadata under `data/<namespace>/conversation_beats/*.json`. This metadata supplements MCA dialogue JSON; it does not replace it.

Each beat should declare at minimum:

| Field | Purpose |
|---|---|
| `id` | Stable semantic id, independent of wording. |
| `topic` | Catalog topic id. |
| `say` | Base NPC speech key. |
| `response_question` | Exact MCA question opened by that result. |
| `npc_act` | Controlled vocabulary such as `report`, `disclose`, `complain`, `celebrate`, `ask`, `invite`, `refuse`, `set_boundary`, `reminisce`, `request_help`, `offer_work`. |
| `subject` | Controlled subject id such as `work.farmer.crop_health`. |
| `polarity` | `positive`, `neutral`, `mixed`, `negative`, or `acute`. |
| `openness` | `invites_followup`, `permits_followup`, `guarded`, `closes_subject`, or `ends_conversation`. |
| `facts` | Facts every variant establishes. |
| `allowed_stances` | Stance families that make sense after every variant. |
| `forbidden_stances` | Stances that must never be offered at this beat. |
| `context` | Author-declared profession, age, relationship, state, integration, or world requirements. |
| `callback` | Optional durable fact, expiry, and future beat ids. |

Use enums or validated ids wherever possible. Free-form tags without a controlled vocabulary will drift into spelling variants and stop being useful.

### 5.3 Required reply fields

Each named answer under a contracted response question must declare:

| Field | Purpose |
|---|---|
| `question` + `answer` | Exact MCA binding. |
| `stance` | One `StanceFamily`. |
| `responds_to` | Beat ids or a contract-family id. |
| `requires_facts` | Facts the player's line assumes to be true. |
| `introduces_facts` | Facts established by the player's reply, such as an offer or promise. |
| `tone` | `gentle`, `plain`, `playful`, `blunt`, `hostile`, `intimate`, etc. |
| `outcomes` | Allowed outcome contract families. |
| `exit` | Whether this is a consequence-free exit. |

### 5.4 Required outcome fields

Each result selected under a reply must be associated with an outcome family:

- `accepted`;
- `appreciated`;
- `engaged`;
- `qualified`;
- `misunderstood`;
- `resisted`;
- `rebuffed`;
- `hurt`;
- `boundary_closed`;
- `conversation_ended`.

The next question must accept that family. A `rebuffed`, `hurt`, or `boundary_closed` result must not route into a node that assumes warmth, trust, disclosure, or willingness to continue. It should route to one of:

- apology/repair;
- clarify intent;
- respect the boundary;
- change subject;
- leave.

### 5.5 Invariants the build must enforce

Add lint that fails when any of the following is true:

1. a `say` + `next` pair referenced by shipped branching content lacks a beat contract;
2. a response question has multiple inbound beats whose contracts are not explicitly compatible;
3. an answer's `requires_facts` are not present in every compatible inbound beat;
4. an answer's stance is absent from the inbound beat's `allowed_stances` or present in `forbidden_stances`;
5. one base speech pool contains variants assigned to different acts, polarity, openness, or fact sets;
6. an outcome routes to a next node that does not accept its outcome family;
7. a boundary-closing outcome offers pressure, intimacy, humor at the NPC's expense, or continued probing without an intervening repair;
8. a hostile answer has neutral or friendly wording, or a friendly answer carries a hostile consequence without a clearly authored misunderstanding/check outcome;
9. a reply mentions an entity, item, place, event, task, injury, relationship, or promise not established by the current beat or prior session facts;
10. a callback references a fact that its originating path never wrote;
11. a callback can run for a different villager, profession, or relationship than the originating fact permits;
12. a response node is reachable from both `invites_followup` and `closes_subject` without explicitly partitioning its offered answers.

### 5.6 Human review remains mandatory

No tag system can prove that prose sounds natural. Semantic contracts make whole classes of non-sequitur structurally detectable, but a human must still read every adjacency. The build should generate a review artifact containing, for every possible turn:

1. selection context;
2. all NPC variants;
3. response prompt;
4. every player button;
5. every possible NPC outcome pool;
6. every next node's buttons;
7. durable state and consequences.

The release must not be accepted until an editor has read the generated adjacency report and every representative full transcript.

---

## 6. Target technical design

### 6.1 Keep MCA JSON authoritative

Continue using `data/*/dialogues/*.json`, MCA questions, answer constraints, result scoring, `next`, and `say`. Do not create a second runtime conversation engine.

The new beat catalog should serve four bounded purposes:

1. load-time validation;
2. server-side session/debug context;
3. chat-mode semantic binding;
4. build-time transcript generation and lint.

The actual visible route must still be represented in MCA dialogue JSON so datapacks can merge or override it.

### 6.2 Couple every line to its correct response node

Treat `say` and `next` as one authored semantic pair. Every result that speaks must specify a response question designed for that speech contract.

For example, replace this pattern:

```json
{
  "conditions": [{"chance": 100, "profession": "minecraft:farmer"}],
  "actions": {
    "next": "conversations.topic.work.respond",
    "say": "conversations.work.prof.farmer"
  }
}
```

with a profession/beat-specific route:

```json
{
  "conditions": [
    {"chance": 100, "profession": "minecraft:farmer"},
    {"chance": 100, "conversations_weather": {"is": "clear"}},
    {"chance": -2000, "conversations_disabled": "world"}
  ],
  "actions": {
    "conversations_session": {
      "op": "branch",
      "branch": "work.farmer.dry_fields"
    },
    "next": "conversations.topic.work.farmer.dry_fields.respond",
    "say": "conversations.work.farmer.dry_fields"
  }
}
```

The exact conditions above are illustrative. The implementation must author mutually intelligible fallback results for disabled features and overlapping contexts.

### 6.3 Split by meaning, not by every sentence

Do not create one response question per variant if every variant has the same meaning. Reuse is safe when all inbound beats share:

- subject;
- NPC act;
- polarity;
- openness;
- required facts;
- allowed reply stances.

Reuse is unsafe merely because the lines share a topic label such as `work`, `people`, `life`, or `noticed`.

### 6.4 Extend the live session with semantic turn state

Add bounded fields to `ConversationSession`:

- `currentBeatId`;
- `currentContractFamily`;
- `lastNpcAct`;
- `lastOutcomeFamily`;
- `lastPlayerStance`;
- `turnFacts` as a small immutable set;
- `contextSnapshotId` or the small snapshot itself;
- a short ring of recent beat ids for anti-repetition and debug output.

Do not persist the entire session. Persist only facts explicitly declared as callbacks through the existing progress/memory systems or a new bounded semantic-memory record.

Extend `conversations_session` with a backward-compatible `turn`/`beat` field, or add a narrow `conversations_turn` bookkeeping action. It should set semantic state without granting rewards or delivering speech.

### 6.5 Specificity and randomness rules

Randomness may choose among:

- semantically equivalent line variants;
- several eligible subjects whose individual response nodes are all correct;
- equal-priority evergreen beats not recently used.

Randomness must never choose among incompatible meanings and then show a shared reply page.

Use explicit priority tiers:

1. unresolved callback or promise;
2. acute recent state/event;
3. exact profession + current chore/problem;
4. exact relationship/family role;
5. exact village/reputation/quest context;
6. season/weather/time context;
7. evergreen profession or topic beat;
8. generic fallback.

Where two native MCA results may both score positively, either:

- ensure both route to their own compatible nodes and accept that either subject may be chosen; or
- make the lower-priority result explicitly ineligible while the higher-priority context holds.

Do not rely on “100 is much larger than 2” as a guarantee. MCA uses weighted selection, so a low-weight generic branch can still win.

### 6.6 Context snapshot

Build a `ConversationContextSnapshot` once when a topic begins or a beat is selected. It may contain:

- villager UUID and age group;
- exact profession id and display name;
- personality id and traits;
- mood and health bucket;
- current chore;
- rank;
- spouse/partner/family relation to the player;
- heart band and disposition bands;
- home village id/name and selected village facts;
- weather, time of day, season, holiday, dimension, and optionally biome;
- recent conversation memories and progress;
- last gift;
- recent gossip/event;
- quest and reputation summaries when integrations are present;
- Townstead summaries when present.

Resolve this on demand. Do not poll it every tick. Cache only for the short conversation session and invalidate naturally on topic end/timeout.

Any new MCA access must go through `McaCompat`/`McaBinding`/`McaHandles`; do not reintroduce compile-time MCA class references. Extend the binding probe manifest and run it against all configured MCA probe versions.

### 6.7 Modular content source and generated runtime resources

The base English file is already nearly 400 KB, and the proposed work packs alone will add thousands of entries. Hand-editing one monolithic lang JSON will become unsafe.

Recommended source layout:

```text
src/content/
  topics/<topic>/dialogues/*.json
  professions/<namespace>/<profession>/profile.json
  professions/<namespace>/<profession>/dialogues/*.json
  beats/<topic>/*.json
  locales/en_us/<topic-or-pack>.json
  locales/pt_br/<topic-or-pack>.json
  overlays/<personality>/<locale>/<topic-or-pack>.json
```

Add a deterministic Gradle generation task that:

1. merges fragments;
2. rejects duplicate keys unless an explicit override manifest authorizes them;
3. validates placeholder parity;
4. emits the runtime layout under generated resources;
5. produces a key ownership report;
6. runs before tests and `processResources`;
7. never rewrites human-authored source fragments.

Keep the final jar layout compatible with MCA's global translation lookup and current personality-prefix rules.

### 6.8 Datapack extension model

Add `profession_profiles` and `conversation_beats` as reload listeners using last-id-wins semantics consistent with the current catalog/intents.

Third-party packs should be able to add:

- an exact profession id;
- its domain/archetype;
- supported work subjects;
- context-gated beats;
- response nodes;
- semantic contracts;
- chat vocabulary;
- localization.

Malformed metadata must log and skip the affected entry without aborting world load. Native MCA condition values remain crash-prone, so shipped/generated JSON must continue to receive strict build-time vocabulary validation.

### 6.9 Chat-mode parity without thousands of duplicated intent definitions

The existing matcher binds an intent to one exact question and answer. Profession expansion could otherwise require hundreds of near-identical intent entries.

Preserve exact intents for highly specific lines, but add a semantic stance layer:

- every contracted answer declares a stance and optional subject verbs/nouns;
- while a question is open, the matcher considers only answers actually offered in the session;
- shared phrases such as “how?”, “why?”, “can I help?”, “that sounds hard,” “I'm sorry,” and “leave it” map to stance families;
- profession profiles contribute domain synonyms such as crops/harvest, armor/plate, books/manuscripts, patrol/watch, and nets/catch;
- exact question intents outrank generic stance intents;
- numbered/keyword quick replies remain a final fallback.

Add collision tests proving that an utterance cannot accidentally select an answer that was not offered or a semantically different stance in the same node.

### 6.10 Performance constraints

- Compile beat/profile metadata at reload into immutable maps.
- Index by topic, profession id, contract family, and context tags.
- Do not scan the full corpus on a click.
- Bound recent beat history and persistent callbacks.
- Perform no per-villager per-tick work.
- Reuse existing lazy decay/time calculations.
- Add a reload benchmark and a selection microbenchmark with the full expanded corpus.

---

## 7. Complete work-conversation redesign

### 7.1 New work flow

Replace the single universal work funnel with:

1. **profession resolution** — adult profession, unemployed, nitwit, or young-person chores/aspirations;
2. **subject selection** — current task, craft, difficulty, village value, work relationships, aspiration, or callback;
3. **profession-specific opener beat**;
4. **beat-specific response page**;
5. **outcome-specific follow-up** when appropriate;
6. **profession-specific close or callback write**.

The profession hub can remain simple, but work conversations should expose multiple natural entry intentions over time:

- “How is work today?”
- “What are you working on?”
- “How did you learn the trade?”
- “What's the hardest part?”
- “What does the village need from you?”
- “Is there anything I can actually help with?”

Not every button must appear at once. Use context, cooldowns, relationship, and prior discovery to rotate subjects while preserving a graceful exit.

### 7.2 Standard profession profile

Every supported profession profile must define:

- exact registry id;
- display fallback;
- domain/archetype;
- worksite or environmental affinities where available;
- core duties;
- craft vocabulary;
- resources/materials;
- common problems;
- village beneficiaries/dependencies;
- ethical tensions;
- learning/mastery subjects;
- aspiration subjects;
- seasonal/weather hooks;
- valid help/quest hooks;
- at least two callback fact types;
- compatibility ownership (`base`, `MCA`, or optional mod).

### 7.3 Minimum content per profession

For each of the 37 currently rostered ids, author at minimum:

- 6 distinct opener beat pools:
  - current task;
  - technique/mastery;
  - difficulty/risk;
  - village purpose;
  - personal history/learning;
  - aspiration or dissatisfaction;
- at least 3 context-gated alternatives across weather, season, mood, chore, relationship, village state, or recent event;
- at least 2 callback beats referring to a fact learned or promise made in an earlier conversation;
- one positive/proud path, one strained/negative path, and one mixed/ethical path;
- 4–5 coherent player options at each main response page, including an exit;
- outcome-specific follow-ups for accepted, neutral, resisted, and hurt/rebuffed stances;
- chat-mode coverage;
- base English and Portuguese localization;
- personality-aware selection or signature voice coverage at the high-salience beats.

This is a floor, not a quota to fill with paraphrases. Each pool's variants must approach the subject from different anecdotes or images while preserving one semantic contract.

### 7.4 Profession content matrix

| Profession id | Required bespoke subjects and hooks |
|---|---|
| `minecraft:farmer` | crop choice; soil and irrigation; planting/harvest pressure; pests; weather and season; tool condition; feeding the village; trade prices; inherited land; fear of a failed harvest; pride in a good field; player help that only appears when actionable. |
| `minecraft:fisherman` | current catch; dawn/night conditions; river/ocean differences; nets, rods, boats, and repairs; storms; fish scarcity; competition with wildlife; supplying food; a dangerous catch; patience and solitude; remembered fishing spots. |
| `minecraft:shepherd` | flock personalities; lambing; shearing; pasture quality; wolves/predators; weather exposure; wool demand; sick animals; naming animals; inherited husbandry knowledge; conflict between attachment and livelihood. |
| `minecraft:fletcher` | feather and shaft supply; balance and straightness; guard/hunter orders; rushed batches; testing arrows; workstation precision; pride in invisible craftsmanship; broken arrows returning; ethics of who receives weapons. |
| `minecraft:librarian` | acquiring books; cataloguing; damaged or missing volumes; literacy and teaching; preserving local history; dangerous/strange texts; requests from villagers; quiet versus loneliness; a book connected to the player or village. |
| `minecraft:cartographer` | blank map regions; surveying; landmark accuracy; dangerous routes; stories from travelers; maps that saved or misled someone; village expansion; weather and visibility; desire to travel; the difference between drawing and knowing a place. |
| `minecraft:cleric` | brewing/healing work; illness and injury; listening to grief; community ritual without importing real-world doctrine; scarce ingredients; moral confidentiality; overwork from caring for others; village crises; limits of what can be healed. |
| `minecraft:armorer` | fitting armor; repeated damage patterns; repairs; metal/fuel shortage; guard safety; balancing weight and protection; a returned suit whose wearer survived; rushed orders; responsibility for failures; player equipment observations when reliable. |
| `minecraft:weaponsmith` | balance, temper, edge, and material; commissions; guard readiness; dangerous customers; repair versus replacement; ethics of making weapons; pride in a named blade; shortages; fear of seeing one's work used badly. |
| `minecraft:toolsmith` | wear patterns; tools for farmers/miners/builders; repair queues; ergonomic/design improvements; material supply; unglamorous village dependence; a tool that outlived its maker; frustration with misuse; prototype ideas. |
| `minecraft:butcher` | feeding the village; preservation; feast demand; shortages; waste; cleanliness and speed; emotional/ethical unease; arguments with villagers; inherited recipes; winter preparation; distinguishing the work from cruelty. |
| `minecraft:leatherworker` | curing/tanning; dyes; waterproofing; hide quality; smell and public complaints; armor/book/tool dependencies; custom orders; reducing waste; weather effects; a piece that aged well; apprenticeship knowledge. |
| `minecraft:mason` | foundations; cracks and repairs; quarry supply; structural risk; walls, homes, and monuments; storm/frost damage; village growth; reading stone; work that outlives people; current building-specific dialogue when a known village building exists. |
| `minecraft:nitwit` | unofficial errands; observation of village life; stigma around having no formal trade; unexpected competence; freedom from schedules; humor that does not reduce the villager to a joke; desire—or refusal—to find a calling; helping neighbors quietly. |
| `minecraft:none` | unemployment or between trades; apprenticeships; failed attempts; economic worry; indecision; freedom; current chores; admiration/envy of another trade; asking the player about travel; a future profession callback if the villager later changes job. |
| `mca:guard` | patrol route; shift fatigue; recent threats; quiet-shift philosophy; equipment; village weak points; morale; protecting named residents; player reputation; unresolved incidents; asking for practical help only when a real integration can supply it. |
| `mca:archer` | watchtower duty; sightlines; ammunition; weather/wind; recent sightings; roofs and village observation; training; restraint before firing; night watch; rivalry/cooperation with guards and fletchers. |
| `mca:adventurer` | last journey; next destination; ruins; companions lost or remembered; supplies; maps; risk appetite; village restlessness; rare discoveries; exaggeration versus honesty; player achievements only when reliably known. |
| `mca:mercenary` | current/previous contract; payment; loyalty; professional boundaries; dangerous employers; moral lines; reputation; wounds; weather and travel; why they stay in the village; tension between coin and belonging. |
| `mca:cultist` | rituals; omens; secret meetings; recruitment; supplies; doubts; community suspicion; comic evasions balanced with sincere belief; a boundary around forbidden knowledge; consequences for pressing too hard. |
| `mca:outlaw` | wanted status; safe routes; law and grudges; fences/supplies; survival; trust; why they became an outlaw; village sympathy or hostility; player reputation; fear of betrayal; possible redemption without trivializing wrongdoing. |
| `morevillagers:enderian` | End research; pearl behavior; gaze safety; teleportation; artifacts; unsettling observations; isolation; scientific obsession; supplies; a finding that changes between conversations. |
| `morevillagers:engineer` | redstone prototypes; failures; automation; material shortages; maintenance; village skepticism; accidental explosions; iterative testing; practical benefits; a prototype callback that succeeds, fails, or changes. |
| `morevillagers:florist` | seasonal blooms; bees; rare flowers; bouquets for courtship/apology/funerals; dye supply; cultivation; weather; named village occasions; aesthetics versus livelihood; a requested arrangement callback. |
| `morevillagers:hunter` | tracks; animal populations; weather; safety; ethics; feeding the village; hides/meat; predator sightings; forest knowledge; a wounded or elusive quarry; restraint and conservation. |
| `morevillagers:miner` | current seam; supports and cave safety; torches; dust/fatigue; ore demand; dangerous sounds; tool quality; depth; cave-ins; sharing finds; fear/pride associated with the dark. |
| `morevillagers:netherian` | portal condition; Nether routes; heat; bartering; hostile mobs; landmarks; fire resistance supplies; failed expedition; mapping; risk normalization; concern that surface villagers do not understand the work. |
| `morevillagers:oceanographer` | currents; ruins; guardians; specimens; diving gear; breathing limits; maps; storms; sea myths versus observation; a recovered artifact; wet/dry humor grounded in actual work. |
| `morevillagers:woodworker` | tree species and grain; seasoning wood; structural beams; furniture/orders; forest supply; sustainable cutting; tools; weather damage; a difficult piece; emotional meaning of building homes. |
| `ars_nouveau:shady_wizard` | magical wares; components; questionable sourcing; failed spells; clients; warranties/refunds; law and secrecy; genuine expertise beneath the sales pitch; an item or solution callback; boundaries around dangerous magic. |
| `chefsdelight:delightchef` | menu planning; kitchen leadership; technique; ingredient quality; feasts; demanding patrons; presentation; pressure; signature dish; mentoring cooks; a meal/event callback. |
| `chefsdelight:delightcook` | daily meals; nutrition; shortages; leftovers; village preferences; kitchen pace; feeding children/workers; practical recipes; being overlooked beside a chef; a dish the player tried or supplied. |
| `iceandfire:scribe` | manuscripts; copying and translation; ink/paper; preservation; dangerous or living texts; dragon-related records; commissions; accuracy; secrecy; a recovered passage callback. |
| `vampirism:hunter_expert` | tracking vampires; training; tools and garlic; recent signs; preparation; civilian safety; moral judgment; exhaustion; teaching novices; a threat callback with careful optional-mod gating. |
| `vampirism:priest` | holy water and protective rites within the mod's fiction; afflicted villagers; mercy versus fear; supplies; counseling; suspicion; night preparation; limits of protection; a named incident callback. |
| `vampirism:vampire_expert` | blood-arts knowledge; nocturnal work; client secrecy; appetite/control ethics; research; daylight constraints; social suspicion; dangerous advice boundaries; a case-study callback. |
| `werewolves:werewolf_expert` | moon phases; preparation; treatment/training; containment; stigma; tracking signs; family/community impact; calendar scheduling; an upcoming/full-moon callback; balancing safety and personhood. |

Optional profession packs must be data-only and harmless when their owning mod is absent. Exact profession conditions from an absent registry must continue to score zero rather than creating hard dependencies.

### 7.5 Profession archetypes may reuse mechanics, never generic prose

Group professions for reusable routing, condition, and consequence logic:

- cultivation/care;
- food/service;
- craft/production;
- knowledge/research;
- defense/security;
- exploration/extraction;
- occult/illicit.

Archetypes may share schemas, stance patterns, check difficulties, and callback mechanics. They must not all share NPC lines such as “work is hard” or reply pages such as “you are good / why / you seem tired.” Reuse structure, not lived detail.

### 7.6 Worked coherence examples

#### Farmer: drought beat

NPC:

> “The wheat's yellowing too early. One more dry afternoon and half that field will be chaff.”

Valid replies:

- “Which part of the field is worst?” — curiosity; requires `field_stressed`.
- “I can help carry water if that would matter.” — practical help; requires `drought` and an actionable or explicitly conversational help route.
- “You've brought in bad harvests before. What worked then?” — encouragement + memory, only if prior experience is established.
- “Then plant something hardier next season.” — blunt advice; may be accepted or rejected based on expertise/respect.
- “I'll let you get back to it.” — exit.

Invalid replies that must never appear:

- “You don't sound like you enjoy farming.”
- “Who taught you?”
- “So you're the reason the village still works.”
- “I'll bring you some,” with no referent.

#### Armorer: repeated damage beat

NPC:

> “Three breastplates came back with the same split below the shoulder. Either my pattern is wrong or something out there has learned where to strike.”

Valid replies:

- ask to inspect the damaged plates;
- ask whether the same patrol returned them;
- reassure without dismissing the risk;
- challenge the design only with sufficiently blunt/respected context;
- advise warning the guards;
- exit.

This beat establishes `repeated_damage`, `possible_design_flaw`, and `possible_common_threat`. Later callbacks can truthfully report which explanation proved correct.

#### Librarian: damaged volume beat

NPC:

> “Someone returned the village births ledger with six pages wet through. I can save the names or the dates, not both.”

Valid replies must address preservation, responsibility, the lost information, or practical help. Generic praise for being a librarian is no longer sufficient.

#### Guard: boundary after player blame

If the player blames the guard for a recent attack and the guard rebuffs it, the next page must offer apology, clarification, calm disagreement, or exit. It must not route to a friendly patrol-story page or ask whether the guard is “wearing down.”

### 7.7 Children, teens, nitwits, and unemployed villagers

Do not treat “work” as an adult profession question for every age/state.

- **Toddlers:** chores as play, imitation, simple concrete tasks; no career discourse.
- **Children:** helping family, learning, small responsibilities, what they imagine becoming.
- **Teens:** apprenticeships, unwanted chores, pressure to choose a trade, early competence, envy or uncertainty.
- **Unemployed adults:** job-seeking, between trades, care work, informal labor, choice, shame, freedom, or transition.
- **Nitwits:** a full human interiority; informal contribution, observation, humor, frustration with labels, and possible contentment. Avoid making every branch ridicule them.

If a villager changes profession, a callback should recognize the transition: what they hoped for, what they chose, and how the new work compares.

### 7.8 Work offers and quests

`work_offer` must be profession-aware even when MCA: Quests is absent, but it must not falsely promise a playable task.

Use three tiers:

1. **Conversational need:** the NPC discusses what needs doing; no promise of an objective.
2. **Actionable lightweight interaction:** only where the mod implements a real immediate action or verifiable condition.
3. **Quest-backed offer:** when MCA: Quests exposes an eligible offer, bind the conversation to the exact giver/quest, explain need and terms, and open the quest UI only after explicit acceptance.

Profession-specific offer subjects should include appropriate materials, risks, beneficiaries, and urgency. “Nothing needs doing” should still be tailored: a farmer between harvests, a librarian caught up on repairs, and a guard during a quiet week should not say the same thing.

---

## 8. Topic-by-topic expansion matrix

The goal is not to make every topic equally long. It is to ensure that each has enough semantic branches to reflect the villager and the moment.

| Topic | Required expansion and correction |
|---|---|
| `day` | Base the answer on current chore, work/non-work, time, health, mood, weather, recent event, and relationship. Split “rough” into inconvenience, fatigue, conflict, fear, grief, and physical danger where known. Do not route all rough days to the same help/joke page. |
| `checkin` | Distinguish genuine wellbeing, guarded “fine,” injury, exhaustion, joy, flirtation, grief, anger at the player, late-night wakefulness, pregnancy, and event aftermath. Carry the state into every follow-up. |
| `food` | Add profession, culture/household, season, trait, family, scarcity, feast, cooking ability, favorite/disliked food, and remembered gift/meal. Only offer recipes, sourcing, or “I'll bring some” when a concrete item/dish exists. |
| `weather` | Tie reactions to profession and actual consequence: crops, fishing, patrols, masonry, flowers, travel, hunting, Nether/ocean work. Separate enjoyment, danger, inconvenience, and practical urgency. |
| `season` | Add profession workload, holidays, family traditions, memories, village preparation, food, travel, and mood. Holiday invitation/decline must refer to the correct holiday and relationship. |
| `work` | Replace the universal funnel with the complete profession system in §7. |
| `work_offer` | Profession- and quest-aware need/terms/acceptance; no generic or false jobs. Remember accepted, declined, rudely refused, completed, and failed offers accurately. |
| `village` | Discuss named buildings, missing services, safety, population, leadership, recent construction/damage, season, belonging, and the villager's profession/family stake. Distinguish praise, criticism, homelessness, newcomer status, and desire to leave. |
| `people` | Move from generic neighbor judgments to named or role-grounded relationships where privacy permits. Separate affection, conflict, loneliness, envy, reconciliation, gossip invitation, and a firm refusal to gossip. |
| `neighbour` | Use actual loaded resident names/family relations when safely resolved. Store the named subject for the whole exchange. Never offer “defend them” or “who is worst?” without a concrete person and compatible disclosure posture. |
| `rumors` | Carry gossip event type, subject, source confidence, privacy level, and whether the villager is willing to spread it. Distinguish harmless chatter from death, divorce, crime, or a private secret. |
| `standing` | Split positive, neutral, negative, unresolved-incident, unknown, and repeat branches. Offer amends only when something requires amends; offer curiosity about praise when standing is good. Integrate exact known deeds when Reputation is installed. |
| `news` | Carry event type and named/unnamed subjects. Separate good news, bad news, mixed change, help received, danger, and no news. Replies about spreading, secrecy, celebration, or sympathy must match the event. |
| `noticed` | Split every emotional/state family and every player-caused conflict outcome. Remove the universal follow-up. Grief, joy, anger, guardedness, injury, pregnancy, pride, fear, and ordinary wellbeing need different goals. |
| `life` | Add personal-history archetypes selected from age, profession, family, village tenure, hardship, migration, and relationship. Store the disclosed chapter so later questions refer to the exact part, not “that story.” Route judgment rebuffs into repair. |
| `dreams` | Distinguish career, travel, family, craft mastery, safety, status, home, adventure, and impossible/fanciful dreams. Follow-ups should ask about the stated dream's obstacles or first step. |
| `fears` | Carry the feared object/situation, disclosure level, trigger, and desired response. Practical planning is valid for some fears; quiet presence is valid for others. Never reuse a plan/pledge page after the NPC declined disclosure. |
| `hopes` | Distinguish near-term wish, long-term aspiration, hope for another person, village hope, relationship hope, and guarded superstition. Help must address the named hope. |
| `regrets` | Carry the domain of regret, responsibility, reparability, harmed party, and willingness to act. Do not invite forgiveness, confession, or practical repair when the preceding line rejected those frames. |
| `secret` | Preserve the existing disclosure boundary fix. Add secret categories and explicit confidentiality severity. Store whether the player heard it, promised discretion, declined, pressured, or betrayed it. Never surface the content in a path that did not receive it. |
| `feelings` | Separate platonic, romantic, familial, wary, resentful, grateful, and uncertain feelings. Relationship eligibility and family roles must dominate generic heart thresholds. Follow-ups must retain the expressed feeling. |
| `happy` | For spouse/partner contexts, distinguish stable happiness, gratitude, hidden dissatisfaction, acute conflict, and guarded reassurance. “How can we improve?” must follow a branch that actually indicates room for improvement. |
| `firstmet` | Store/reconstruct the actual relationship context available to the mod: location or event only if known; otherwise frame memories as impressions. Let different personalities remember different aspects without inventing facts. |
| `future` | Separate children, home, travel, work, village, safety, and relationship commitment. Do not offer “I want the same” until the NPC has stated a concrete future. |
| `worries` | Carry the worry's subject and whether the NPC wants solutions, reassurance, information, or simply to be heard. Player dismissal should route to hurt/repair or exit, not the normal continuation. |
| `checkin_child` | Respect directionality: parent checking on child versus player checking on their child. Use age, recent events, school/work chores, family state, and prior promises. Avoid adult emotional vocabulary for toddlers. |
| `ask_parent` | Let a child/descendant ask about a concrete concern, pride, expectation, or family event. The player's answer must address the question actually asked. |
| `memories` | Ground memories in family role, village tenure, profession, holiday, home, or known prior event. Store a memory subject for the turn so “that's not how it went” can lead to clarify/joke/repair rather than a generic family close. |

### 8.1 Depth targets

- Quick topics: at least 2 meaningful player decisions on normal paths and at least 8 distinct opener contexts across the topic.
- Standard topics: 2–3 meaningful decisions, at least 10 semantic beat families, and at least 3 callbacks/state-sensitive revisits.
- Deep topics: 3–5 meaningful decisions, several disclosure levels, explicit boundaries, at least 4 distinct subject archetypes, and durable callbacks.
- Relationship topics: 3–5 decisions with family/romance/relationship eligibility and disposition-aware outcomes; at least 4 relationship-state entry families.
- Service topics: enough dialogue to establish need, terms, acceptance/refusal, and outcome without awarding affection for opening a menu.

---

## 9. Personalization model

### 9.1 Layered personalization, not combinatorial duplication

Compose specificity in this order:

1. **semantic subject:** profession/event/family/history determines what is being discussed;
2. **relationship permission:** determines what may be disclosed and how directly the player may respond;
3. **current state:** mood, health, chore, weather, season, and recent event determine why it matters now;
4. **personality goal:** determines what the NPC wants from the exchange and how they interpret a stance;
5. **voice:** determines phrasing, rhythm, humor, guardedness, and imagery;
6. **variant:** supplies replay variety without changing the contract.

Do not try to author a separate full tree for all possible combinations. Author semantically specific beats, then use targeted overlays and outcome conditions where they add real distinction.

### 9.2 Personality design matrix

The canonical personality should affect subject preference, disclosure, and interpretation, not just adjectives.

| Canonical personality | Conversational tendencies to encode |
|---|---|
| `confident` | volunteers opinions; emphasizes mastery and plans; accepts direct challenge more readily; dislikes pity; may overstate certainty. |
| `peppy` | favors present/future, activity, celebration, and quick pivots; responds well to encouragement; may minimize fatigue until directly noticed. |
| `friendly` | asks reciprocal questions; discusses neighbors/family; welcomes practical help; gives generous interpretations; discloses relational details sooner. |
| `flirty` | notices the player and shared time; may turn safe topics playful when romance-eligible; must never inject flirtation into grief, hard boundaries, children, or ineligible relationships. |
| `playful` | uses jokes, dares, and imaginative comparisons; welcomes humor when tension is low; can become serious when the beat requires it. |
| `gloomy` | notices loss, risk, impermanence, and disappointment; may still value quiet beauty; resists shallow optimism but can accept honest solidarity. |
| `sensitive` | notices emotional subtext and criticism; discloses hurt; values careful empathy; blunt/dismissive stances land harder; do not portray as uniformly fragile. |
| `greedy` | foregrounds value, ownership, trade, status, scarcity, and fair compensation; respects clear terms; generosity should be meaningful rather than absent. |
| `odd` | makes unusual associations and notices overlooked details; may choose eccentric subjects; replies must still be logically answerable rather than random nonsense. |
| `crabby` | complains, values brevity and competence, resists intrusion, appreciates useful candor; warmth should surface indirectly. |
| `extroverted` | discusses people/events, asks questions, narrates publicly, seeks engagement; may overshare or spread news unless a privacy boundary exists. |
| `introverted` | favors craft, observation, small circles, and quiet; disclosure thresholds are higher; accepts space and precise questions more readily than broad probing. |
| `relaxed` | de-emphasizes urgency/status, values routine and comfort, may resist over-planning; calm must not erase real danger or grief. |
| `anxious` | anticipates failure and seeks preparation/reassurance/information; practical help may land well when it does not dismiss the fear. |
| `peaceful` | favors repair, community stability, restraint, and nonviolent solutions; reacts strongly to needless escalation; can still be firm. |
| `upbeat` | reframes difficulty toward possibility, enjoys stories and humor, encourages others; must not invalidate grief or serious harm. |

Legacy handling remains:

- `witty` → `upbeat`;
- `shy` → `introverted`;
- `lazy` → `relaxed`;
- `grumpy` → `crabby`;
- `athletic` remains a legacy-only 7.6 voice/behavior surface and a 7.7 trait where available.

### 9.3 Personality coverage target

Do not attempt a 16-personality rewrite of every utility line. Require:

- personality-aware subject/goal selection for every standard, deep, and relationship topic;
- personality-aware outcome selection for every non-trivial challenge, joke, pressure, flirtation, and dismissal;
- authored overlay coverage for 100% of designated **signature beats**:
  - profession identity/history;
  - deep-topic disclosures;
  - spouse/family commitments;
  - boundary/rebuff lines;
  - callbacks to important promises;
- at least 25% of all referenced `say` pools with meaningful personality overlay coverage by the end of the expansion, up from about 3%, while prioritizing salience over raw percentage.

### 9.4 Relationship and disposition bands

Use both MCA hearts/relationship and this mod's disposition axes. Define author-facing bands:

- stranger;
- familiar acquaintance;
- trusted friend;
- close confidant;
- spouse/partner;
- parent/child/family;
- tense;
- openly hostile.

Map these to thresholds in one documented source of truth. Avoid scattering magic numbers through dialogue JSON.

Relationship should affect:

- which topics appear;
- how a question is phrased;
- what facts may be disclosed;
- whether teasing is affectionate or insulting;
- whether direct advice is welcome;
- whether a rebuff is mild, wounded, or final;
- which callbacks are remembered and surfaced.

### 9.5 Mood and acute state

Mood should not merely swap “happy” for “sad.” It should change conversational purpose:

- a happy craftsperson may celebrate a result;
- a sad craftsperson may describe why routine is keeping them moving;
- an angry NPC may want accountability rather than comfort;
- an injured NPC or an NPC reacting to the player's injury needs a state-specific response page;
- grief must override playful/flirty surface behavior;
- pregnancy, health, and recent danger require age/relationship-appropriate handling.

### 9.6 World and village context

Use only context that produces a meaningful conversational difference:

- current chore;
- time of day;
- weather/storm;
- season/holiday;
- home village and known buildings;
- current dimension/biome when reliable;
- recent arrival/departure/birth/death/marriage/divorce/quest event;
- village reputation incident/deed;
- named loaded resident/family relation;
- optional Townstead need/schedule/spirit/profession state.

Avoid decorative placeholders that merely insert `%2$s` into an otherwise generic line.

### 9.7 Specific memory and callbacks

Replace vague state such as “talked about work” with bounded semantic facts where the content justifies it.

Examples:

- `work.farmer.concern=drought`;
- `work.armorer.issue=repeated_shoulder_split`;
- `dream.goal=travel_end`;
- `fear.subject=losing_child`;
- `player.promise=help_irrigate`;
- `player.stance=challenged_design`;
- `secret.heard=<secret-id>`;
- `relationship.future=children`;
- `news.subject=<event-id>`.

Each persistent fact needs:

- owner villager/player pair;
- stable type and bounded value vocabulary;
- created game time;
- optional expiry;
- optional resolved state;
- callbacks that consume or revisit it;
- a storage cap and eviction policy for non-critical facts.

Never store localized prose. Store ids and render fresh localized lines.

### 9.8 Optional integrations

Integrations may add more specific facts and actions, but base conversations must remain complete without them.

- **MCA: Quests:** exact giver/offer/status/completion and explicit acceptance.
- **MCA: Reputation:** exact tier, known deed, unresolved incident, village identity, and amends logic.
- **Townstead:** needs, schedule, work role, spirit, life stage, building, and current activity; never hard-link classes.
- **Serene Seasons:** actual season and harvest pressure.
- **Profession-owning mods:** data packs tied to exact profession ids.

Every integration branch needs a feature-disabled/absent fallback that remains coherent and does not imply unavailable functionality.

---

## 10. Dialogue writing standard

### 10.1 The adjacency test

For every player button, complete this sentence:

> “The villager just said **X**, therefore the player can reasonably say **Y**.”

If “therefore” cannot be defended for every line variant, split the beat or rewrite the reply.

For every result, complete:

> “The player just said **Y** in tone **T**, therefore this villager can reasonably answer **Z** and move to page **N**.”

### 10.2 Presupposition test

A reply may only refer to:

- facts stated by every variant of the current beat;
- facts already established in the current session;
- durable facts the branch explicitly queried;
- universally visible immediate context that the line makes relevant.

Pronouns must have unambiguous referents. “It,” “they,” “that,” “the other way,” “some,” and “there” are not harmless generic words; each requires an established referent.

### 10.3 Emotional continuity test

Track the emotional state of both speakers after every result.

- Acceptance may continue the subject.
- A qualified response may invite clarification.
- A misunderstanding may invite clarification or apology.
- Hurt requires repair, space, or escalation.
- A hard boundary requires respect or explicit violation with honest consequences.
- Grief does not become banter merely because the villager is playful.
- Joy does not become a help request without a stated problem.
- A player-caused conflict does not vanish when navigating to a shared follow-up.

### 10.4 Choice quality

At most five buttons. A strong node usually contains:

- one curious/clarifying response;
- one supportive/practical response where appropriate;
- one personality-dependent or candid alternative;
- one genuinely negative or boundary-testing option where appropriate;
- one graceful exit.

Do not force the same stance roster onto every beat. A funeral disclosure does not need a joke. A proud craft explanation does not need a burnout diagnosis. A closed boundary does not need more probing merely to reach five buttons.

### 10.5 Voice rules

- Keep the grounded, pre-modern/Minecraft-adjacent register already used well in the best profession lines.
- Avoid modern therapy jargon, corporate jargon, and generic motivational copy.
- Avoid making every villager say “Aye,” “Nobody,” “Right you are,” or begin with ellipses.
- Distinguish professions through concrete materials, tools, schedules, customers, risks, and dependencies.
- Distinguish personalities through attention and conversational goal, not caricature catchphrases.
- Use humor as a response to something real in the beat, never as randomness disguised as personality.
- Do not make children sound like miniature adults or toddlers like quirky poets.
- Do not make nitwits, gloomy villagers, anxious villagers, or occult professions one-note jokes.

### 10.6 Variant rules

Variants should differ in image, anecdote, or cadence while preserving:

- the same subject;
- the same relevant facts;
- the same polarity;
- the same degree of openness;
- the same temporal status;
- the same named/unnamed entities;
- the same actionable affordances.

If one variant contains a detail worth responding to specifically, give it a separate base key and response contract.

### 10.7 Consequence rules

- No hidden reversal between wording and mechanical effect.
- Personality can change how a stance lands, but a good-faith question must not secretly be authored as betrayal.
- A hostile line can have a hostile consequence; label it honestly.
- An affectionate/flirty line must be absent when the relationship is ineligible.
- Do not award hearts for selecting a topic, opening a screen, or accepting a quest UI.
- Durable promises, betrayals, and disclosures must be idempotent and callback-capable.

---

## 11. Proposed metadata examples

### 11.1 Beat contract

```json
{
  "beats": {
    "work.minecraft_farmer.crop_stress.dry": {
      "topic": "work",
      "say": "conversations.work.farmer.crop_stress.dry",
      "response_question": "conversations.topic.work.farmer.crop_stress.dry.respond",
      "npc_act": "disclose_problem",
      "subject": "work.farmer.crop_health",
      "polarity": "negative",
      "openness": "invites_followup",
      "facts": [
        "work:farmer",
        "crop:stressed",
        "cause:dry_weather"
      ],
      "allowed_stances": [
        "curiosity",
        "practical_help",
        "encouragement",
        "respectful_disagreement",
        "exit"
      ],
      "forbidden_stances": [
        "flirtation"
      ],
      "context": {
        "profession": "minecraft:farmer",
        "weather": "clear",
        "ages": ["adult"]
      },
      "callback": {
        "fact": "work.farmer.crop_stress",
        "value": "dry",
        "expires_after": 168000
      }
    }
  }
}
```

### 11.2 Reply contract

```json
{
  "replies": {
    "conversations.topic.work.farmer.crop_stress.dry.respond/offer_water": {
      "stance": "practical_help",
      "responds_to": ["work.minecraft_farmer.crop_stress.dry"],
      "requires_facts": ["crop:stressed", "cause:dry_weather"],
      "introduces_facts": ["player:offered_water_help"],
      "tone": "plain",
      "outcomes": ["accepted", "qualified", "resisted"],
      "exit": false
    }
  }
}
```

### 11.3 Profession profile

```json
{
  "profiles": {
    "minecraft:farmer": {
      "archetype": "cultivation",
      "subjects": [
        "crop_health",
        "soil",
        "harvest",
        "tools",
        "village_food",
        "learning",
        "future"
      ],
      "materials": ["seed", "wheat", "beetroot", "water", "compost"],
      "risks": ["drought", "storm", "pests", "failed_harvest"],
      "season_affinity": true,
      "weather_affinity": true,
      "callback_types": ["crop_problem", "harvest_result", "player_help"]
    }
  }
}
```

The exact schema may change during implementation, but it must preserve the invariants and remain parse-safe.

---

## 12. File-level implementation plan

### 12.1 New or extended Java areas

Recommended classes/packages:

```text
conversation/
  BeatContract.java
  BeatContractLoader.java
  ReplyContract.java
  OutcomeFamily.java
  NpcSpeechAct.java
  ConversationContextSnapshot.java
  SemanticFact.java

profession/
  ProfessionProfile.java
  ProfessionProfileLoader.java
  ProfessionProfiles.java

memory/
  SemanticMemoryRecord.java
  SemanticMemoryStore.java
  SemanticMemorySavedData.java

debug/
  ConversationTrace.java
  ConversationTraceExporter.java
```

Modify:

- `ConversationSession` and `ConversationSessions` for bounded turn semantics;
- `SessionDirective` and its registrar action for `beat`/turn bookkeeping;
- `ConversationCatalog`/`TopicEntry` only if topic metadata needs declared beat/profile requirements;
- `McaCompat`, `McaBinding`, and `McaHandles` for exact profession id, traits, chore, rank, family role, or other newly consumed MCA facts;
- `IntentBinding`, `IntentIndex`, `IntentMatcher`, and `QuickReplies` for semantic stance binding;
- debug commands to display current topic, beat, facts, context, offered replies, and why a branch won;
- content loaders to keep the prior active snapshot on reload failure.

### 12.2 Data migration targets

Immediate dialogue files to replace/split include:

- `conversations.work.json`;
- `conversations.topic.work.respond.json`;
- `conversations.topic.work.followup.json`;
- `conversations.topic.noticed.followup.json`;
- `conversations.topic.standing.respond.json`;
- `conversations.topic.checkin.good.followup.json`;
- `conversations.topic.food.normal.followup.json`;
- `conversations.topic.people.followup.json`;
- `conversations.topic.life.followup.json`;
- other shared response/close nodes identified by the generated adjacency audit.

Keep `conversations.work.legacy.json` only as the explicit branching-disabled compatibility fallback. It must not become the normal path for supported professions.

### 12.3 Documentation

Update:

- `DATAPACK.md` with beat/profile schemas, semantic invariants, and extension examples;
- `README.md` with accurate personalization claims;
- `CONFIG.md` for any new content toggles and fallbacks;
- changelog with migration and datapack compatibility notes;
- a profession-pack authoring guide;
- a dialogue editorial checklist.

---

## 13. Test and validation plan

### 13.1 New build-time tests

Add at least:

1. **`BeatContractLintTest`**
   - every shipped branching `say` + `next` has one contract;
   - every response question's inbound contracts are compatible;
   - facts, stances, openness, and outcomes validate.

2. **`ReplyPresuppositionLintTest`**
   - every required fact is supplied on every inbound route;
   - pronoun/referent-sensitive replies carry explicit fact tags;
   - actionable replies require an actionable contract.

3. **`OutcomeRoutingLintTest`**
   - accepted/neutral/resisted/rebuffed/hurt/boundary outcomes route only to compatible nodes;
   - no hard boundary leads to ordinary disclosure continuation.

4. **`ProfessionCoverageTest`**
   - all 37 current ids have profiles and the minimum beat/category/callback coverage;
   - optional ids do not create required dependencies;
   - generic fallback exists for unknown third-party professions.

5. **`VariantSemanticParityTest`**
   - all variants under a base key share one contract;
   - entity naming/actionability markers agree;
   - generated metadata and locale pools have identical runs.

6. **`ConversationTraceGenerationTest`**
   - exports every adjacency and fails on uncontracted routes;
   - output is deterministic for review and diffs.

7. **`ChatSemanticParityTest`**
   - every non-exit GUI answer is reachable through exact or stance-based chat input;
   - only offered answers may be selected;
   - context collisions resolve deterministically.

8. **`SemanticMemoryRoundTripTest`**
   - bounded NBT save/load;
   - expiry, eviction, villager/player ownership, and migration;
   - no localized text persisted.

9. **`McaContextBindingProbeTest`** extensions
   - every newly used MCA handle resolves against all configured MCA probe versions and both package roots;
   - static-link scan remains clean.

10. **`ContentGenerationTest`**
    - source fragments merge deterministically;
    - duplicate ownership, locale parity, placeholder parity, and generated output cleanliness.

### 13.2 Transcript scenario tests

Add named scenarios for at least:

- every profession's six required work subjects;
- proud profession line → praise/curiosity/challenge paths;
- burnout line → listen/help/dismiss paths;
- profession problem → specific callback after expiry interval;
- good standing never offering amends;
- bad/unresolved standing offering specific amends/deed questions;
- anger at player → apology accepted/rejected and boundary respected/violated;
- grief → empathy, space, dismissal, and hard exit;
- joy → celebration without generic help language;
- secret declined versus heard versus betrayed;
- concrete future plan → agreement/disagreement that names the plan;
- child/teen/adult work routing;
- profession change callback;
- optional mod present/absent/disabled paths;
- all four check outcomes entering compatible next nodes;
- duplicate packets and repeated choices remaining idempotent.

### 13.3 Pairwise context testing

Do not attempt the full Cartesian product. Use pairwise or covering-array cases across:

- age group;
- profession class;
- canonical personality;
- mood band;
- relationship band;
- tension/trust band;
- weather/season;
- callback present/absent;
- optional integration present/absent/disabled.

Every supported profession must still receive at least one direct test independent of the pairwise suite.

### 13.4 Human editorial passes

Perform four separate passes:

1. **Logic pass:** facts, referents, adjacency, and continuity.
2. **Character pass:** personality, age, profession, relationship, and emotional goal.
3. **Voice pass:** natural prose, repetition, register, and Minecraft fit.
4. **Gameplay pass:** button clarity, pacing, consequence honesty, cooldowns, and no false affordances.

A coding agent may generate the review report and highlight risks. It must not self-certify “perfect sense” without a human reading the transcripts.

### 13.5 Runtime verification matrix

Run production-style Forge clients/servers with at least:

- MCA 7.6.20;
- MCA 7.7.0-beta.2;
- MCA 7.7.1-alpha.2 or the current supported renamed-package build;
- current pinned Forge and a current 47.4.x runtime used by releases;
- dedicated server + client;
- GUI and chat frontends;
- English and Portuguese;
- expanded personality translations on/off;
- optional integrations absent and present in their supported combinations;
- `/reload` after joining;
- upgraded save with existing conversation progress.

Do not treat ForgeGradle `runClient` alone as production certification; retain the repository's existing package/mixin probe discipline.

---

## 14. Migration and compatibility

### 14.1 Existing saves

- Live sessions are transient and require no migration.
- Preserve existing arc, milestone, exclusive-choice, affection, disposition, gossip, gift, and cooldown ids.
- Where an old broad memory maps cleanly to a new fact, read it as a low-specificity legacy fact; do not invent details.
- New semantic memories need a data version and bounded migration path.
- Never delete player progress because the new content has more detailed branches.

### 14.2 Existing datapacks

- Preserve old question ids as redirect/facade nodes where practical.
- Document which universal nodes are deprecated.
- Continue merging third-party answers under MCA rules.
- A third-party result that routes to an uncontracted old node should remain playable, but debug/lint should identify it as legacy/unverified rather than crashing.
- Provide a compatibility contract family for unknown third-party profession lines and a safe generic response set that makes no unsupported assumptions.

### 14.3 Configuration

Existing feature toggles must continue to degrade coherently. If semantic metadata, profiles, or generated content fail to load:

- keep the previous successful snapshot;
- fall back to a minimal safe response node;
- never show reply buttons that rely on missing facts;
- never abort world load for this mod's custom metadata.

---

## 15. Recommended implementation sequence

### Phase 0 — Freeze evidence and generate the adjacency audit

- Record reviewed commit and corpus metrics.
- Build an exporter over all current dialogue JSON and locale variants.
- Produce the complete current adjacency report.
- Mark every confirmed non-sequitur and semantic fan-in hotspot.
- Add regression scenarios for the concrete examples in §3 before changing content.

**Exit criterion:** the team can inspect every possible current turn without manually searching 173 JSON files.

### Phase 1 — Semantic metadata and lint, no behavior change

- Implement controlled vocabularies, beat/reply/outcome contracts, loaders, and tests.
- Contract the existing corpus incrementally.
- Allow an explicit temporary `legacy_unverified` marker only during migration; report its count and forbid new uses.
- Extend session/debug data.

**Exit criterion:** every current route is either contracted or explicitly listed as migration debt; new uncontracted content fails.

### Phase 2 — Global coherence repair

- Split all incompatible shared response and follow-up nodes.
- Fix work, noticed, standing, check-in, food, people, life, news, and close-node hotspots first.
- Route every check outcome to a compatible continuation.
- Remove reply presuppositions unsupported by all variants.

**Exit criterion:** zero `legacy_unverified` routes and zero contract violations across all 28 current topics.

### Phase 3 — Profession framework and base fallback

- Add profession profiles, exact profession id context, modular source generation, and coverage lint.
- Create archetype mechanics and unknown-profession fallback.
- Implement adult/young/unemployed/nitwit routing.
- Keep current profession lines as migrated identity beats where they meet the contract.

**Exit criterion:** the new work flow functions for known and unknown professions without generic semantic funnels.

### Phase 4 — Vanilla and MCA profession packs

- Implement all `minecraft:*` and `mca:*` profiles first.
- Land each profession as a vertical slice: dialogue, contracts, locale pairs, chat, callbacks, tests.
- Do not merge a profession with only an opener.

**Exit criterion:** every base-game/MCA profession meets §7.3.

### Phase 5 — Optional profession packs

- Implement More Villagers, Ars Nouveau, Chef's Delight, Ice and Fire, Vampirism, and Werewolves profiles.
- Verify all owning mods absent/present.
- Keep separate data ownership for easy maintenance.

**Exit criterion:** all 37 current roster ids meet §7.3 and no optional hard dependency exists.

### Phase 6 — Personality, relationship, and age expansion

- Apply personality subject/goal selection.
- Expand signature overlays.
- Add relationship-band variants and family-role correctness.
- Complete toddler/child/teen conceptual separation.

**Exit criterion:** signature coverage and personality targets pass; no invalid romance/family/age surface remains.

### Phase 7 — Full topic expansion

- Work through the matrix in §8 in vertical slices.
- Add specific subject memories and callbacks.
- Prioritize noticed/state, standing/news/gossip, deep topics, then relationship/family.

**Exit criterion:** every topic meets its depth and context targets.

### Phase 8 — Chat mode, localization, and editorial release pass

- Complete semantic chat matching.
- Finish Portuguese parity and review placeholders.
- Run generated transcript review in both locales.
- Remove repeated verbal tics and paraphrase-only pools.
- Run performance and full runtime matrices.

**Exit criterion:** all automated and human acceptance gates pass.

### Phase 9 — Documentation and release hardening

- Update authoring docs and examples.
- Publish deprecation/migration notes.
- Include a content coverage report in the release artifacts.
- Keep debug trace commands available for future bug reports.

---

## 16. Quantitative acceptance criteria

The release must meet all of the following:

### Coherence

- 100% of shipped branching `say` + `next` pairs have beat contracts.
- 100% of named response answers have reply contracts.
- 0 response questions receive incompatible inbound contract families.
- 0 reply labels require facts absent from any compatible inbound beat.
- 0 `rebuffed`, `hurt`, or `boundary_closed` outcomes route into a warm/intimate/ordinary follow-up without repair.
- 0 variant pools disagree on contract facts, act, polarity, or openness.
- 0 unresolved pronoun/referent warnings in the generated editorial report.

### Profession content

- 37/37 currently rostered professions have profiles.
- At least 6 distinct work opener beat pools per profession.
- At least 2 profession-specific callbacks per profession.
- At least 3 context-gated profession beats per profession.
- Every profession has proud, strained, and mixed/ethical material.
- No known profession normally routes through the universal current `conversations.topic.work.respond` or `conversations.topic.work.followup` funnels.
- Unknown third-party professions receive a coherent generic fallback that uses the display profession but makes no detailed claim.

### Personalization

- All 16 canonical personalities have subject/goal/interiority behavior.
- All legacy aliases continue to resolve.
- 100% of designated signature beats have personality-aware surface or selection.
- At least 25% of referenced `say` pools have meaningful personality overlay coverage, with no paraphrase-only padding.
- All topic entries and downstream nodes enforce their declared age groups.
- All romantic content verifies eligibility and all family content verifies direction/role.

### Content depth

- Every quick topic has at least 8 opener contexts and 2 decisions on normal paths.
- Every standard topic has at least 10 beat families and 3 state/callback revisits.
- Every deep topic has at least 4 subject archetypes, explicit boundary paths, and durable callbacks.
- Every relationship topic has at least 4 relationship-state entry families.
- Every service/quest path establishes need and terms before acceptance.

### Frontends, locales, and safety

- Every non-exit GUI answer is selectable through chat mode.
- English/Portuguese key and placeholder parity is exact.
- All existing structural, reward, path, compatibility, and NBT tests remain green.
- All configured MCA binding probes remain green.
- No per-tick conversation work is introduced.
- Datapack reload failure retains the previous good semantic/profile snapshot.
- Optional integrations remain optional.

---

## 17. Coding-agent working rules

1. Inspect the current branch before editing; do not implement against an older content-review document as if it still described `main`.
2. Add regression tests before fixing each confirmed incoherent path.
3. Never “fix” a mismatch by merely making the reply vaguer if a specific branch would be more engaging.
4. Never add line volume without adding or confirming its semantic contract.
5. Preserve action order: state/bookkeeping → `next` → `say`/`conversations_say`.
6. Preserve result fallback ordering and feature-disabled alternatives.
7. Keep auto questions to one answer.
8. Use `conversations_personality`, never MCA's crash-prone native personality condition across the supported version range.
9. Route all new MCA facts through the runtime binding layer and extend every compatibility probe.
10. Keep hearts behind `conversations_affection_apply` in branching content.
11. Store semantic ids, never localized prose.
12. Keep histories bounded and selection reload-compiled.
13. Treat all 37 profession packs as vertical slices; do not postpone chat, locale, callback, or test work.
14. Generate and review transcripts continuously, not only after all writing is complete.
15. Do not claim semantic completion from green tests alone; obtain human adjacency review.

---

## Appendix A — First nodes to split

Start the coherence migration with this queue:

1. `conversations.topic.work.respond`
2. `conversations.topic.work.followup`
3. `conversations.topic.noticed.followup`
4. `conversations.topic.standing.respond`
5. `conversations.topic.checkin.good.followup`
6. `conversations.topic.checkin.rough.followup`
7. `conversations.topic.food.normal.followup`
8. `conversations.topic.people.respond`
9. `conversations.topic.people.followup`
10. `conversations.topic.village.followup`
11. `conversations.topic.news.followup`
12. `conversations.topic.life.followup`
13. `conversations.topic.dreams.followup`
14. `conversations.topic.hopes.followup`
15. `conversations.topic.regrets.sit_with_it`
16. shared topic/family/us close nodes whose inbound outcomes differ in openness or polarity

For each, enumerate all inbound `say` pools, all variants, all available buttons, all possible outcomes, and all next pages. Split on semantic contract boundaries, not on filename convenience.

---

## Appendix B — Rough content scale

A genuinely massive but controlled expansion will likely add, before personality overlays:

| Content area | Approximate English additions |
|---|---:|
| 37 profession packs | 2,000–3,000 strings/variants |
| Work callbacks and profession transitions | 300–500 |
| Coherence splits for existing topics | 600–1,000 |
| Non-work topic expansion | 1,500–2,500 |
| Relationship/family/deep callbacks | 600–1,000 |
| Chat vocabulary/intents or generated bindings | 600–1,200 entries |
| Signature personality overlays | 2,000–4,000 overlay entries across personalities |

These are planning ranges, not targets to pad. A smaller set of specific, coherent branches is preferable to a larger set of generic paraphrases. Implement in vertical slices so every merged increment is shippable.

---

## Appendix C — Reviewed source anchors

Primary MCA: Conversations sources:

- [Dialogue/data authoring rules (`DATAPACK.md`)](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/DATAPACK.md)
- [Topic catalog](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/resources/data/mcaconversations/conversation_catalog/topics.json)
- [Current profession router](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/resources/data/mcaconversations/dialogues/conversations.work.json)
- [Current work response node](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/resources/data/mcaconversations/dialogues/conversations.topic.work.respond.json)
- [Current work follow-up node](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/resources/data/mcaconversations/dialogues/conversations.topic.work.followup.json)
- [Base English dialogue corpus](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/resources/assets/mca_dialogue/lang/en_us.json)
- [Conversation session](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/java/dev/otectus/mcaconversations/conversation/ConversationSession.java)
- [Personality compatibility roster](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/java/dev/otectus/mcaconversations/personality/Personalities.java)
- [Variant-pool behavior](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/main/java/dev/otectus/mcaconversations/locale/VariantPools.java)
- [Content lint suite](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/test/java/dev/otectus/mcaconversations/content/ContentLintTest.java)
- [Conversation graph lint suite](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/test/java/dev/otectus/mcaconversations/content/ConversationGraphLintTest.java)
- [Path simulation suite](https://github.com/otectus/MCAConversations/blob/16b5ab95ed3c1dae3d8023a53e1b518a367de089/src/test/java/dev/otectus/mcaconversations/content/TopicPathSimulationTest.java)

MCA Reborn engine references:

- [Question/answer exposure](https://github.com/Luke100000/minecraft-comes-alive/blob/4d824551b30654e5792e19e84f3933e3e3d90ea2/common/src/main/java/net/mca/resources/data/dialogue/Question.java)
- [Result selection](https://github.com/Luke100000/minecraft-comes-alive/blob/4d824551b30654e5792e19e84f3933e3e3d90ea2/common/src/main/java/net/mca/resources/data/dialogue/Result.java)
- [`next`/`say` action behavior](https://github.com/Luke100000/minecraft-comes-alive/blob/4d824551b30654e5792e19e84f3933e3e3d90ea2/common/src/main/java/net/mca/resources/data/dialogue/Actions.java)
- [Dialogue loader/selector](https://github.com/Luke100000/minecraft-comes-alive/blob/4d824551b30654e5792e19e84f3933e3e3d90ea2/common/src/main/java/net/mca/resources/Dialogues.java)
- [MCA professions](https://github.com/Luke100000/minecraft-comes-alive/blob/4d824551b30654e5792e19e84f3933e3e3d90ea2/common/src/main/java/net/mca/ProfessionsMCA.java)
- [MCA 1.20.1 base dialogue data](https://github.com/Luke100000/minecraft-comes-alive/tree/4d824551b30654e5792e19e84f3933e3e3d90ea2/common/src/main/resources/data/mca/dialogues)

---

## Final implementation principle

The mod should never again decide that two lines may share replies merely because both are “about work,” “about life,” “about people,” or “about feelings.” Conversation is coherent when replies attach to claims, emotions, goals, and boundaries—not category names. Build that truth into the data model, migrate every current branch to it, and then expand boldly.
