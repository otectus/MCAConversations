# MCA: Conversations — Content Review & Expansion Spec

**Target repo:** `otectus/MCAConversations` (Minecraft 1.20.1 / Forge 47.x / MCA Reborn `[7.6,8)`)
**Reviewed at:** commit `c13ae58` ("Phase 5: the personal topics"), version line 1.1.0
**Audience:** a coding agent implementing an incremental content release (proposed **1.2.0**)
**Nature of the change:** almost entirely **data** — dialogue JSON, lang JSON, chat intents, catalog rows,
plus a small number of lint/test additions and six narrowly-scoped Java changes.

---

## 0. How to use this document

Every work item has a stable id (`A1`, `B3`, `C7`, …), a **Why**, a **Change**, a **Files**, and an
**Acceptance** clause. Ids are referenced from the sequencing plan in §7. Nothing here asks you to
redesign the engine: the architecture is sound and the invariants it already enforces all hold.

Three ground rules inherited from `DATAPACK.md` that this spec never overrides:

1. Hearts move **only** through `conversations_affection_apply`, never native `positive`/`negative`,
   in any `conversations.topic.*` / `conversations.arc.*` node.
2. Every node offers 2–5 answers and at least one consequence-free exit.
3. `en_us` and `pt_br` land in the same commit, with identical keys and identical placeholder sets.

Work through `DATAPACK.md` §"Content-authoring checklist" for every new topic. Where this spec
disagrees with a doc, §1 says which one is wrong.

---

## 1. Ground truth: the docs are behind the code

Fix these first; they are cheap and they stop the next contributor from mis-scoping.

| Id | Where | Says | Reality |
|---|---|---|---|
| **A1a** | `README.md:20` | "Two topics are converted in 1.1.0 (the day, and fears)" | **18** topics are converted: `day, fears, checkin, food, weather, season, work, work_offer, village, people, rumors, news, noticed, life, dreams, hopes, regrets, secret` |
| **A1b** | `conversation_catalog/topics.json:8` (`_comment`) | "1.1.0 converts two pilot topics" | same as above |
| **A1c** | `CHANGELOG.md:116` | "Six of the twenty-six original topics still pay out" | **10 answers across 3 areas** (`feelings`, `us`, `family`) still pay on the click; original topic count reconstructs to 21, not 26 |
| **A1d** | `README.md:94` | "2,453 translated strings" | pt_br footprint is **4,243** across 23 namespaces (base file alone is 2,514) |
| **A1e** | `DATAPACK.md:519` | "Every `say` key has its variant pool (3 lines; 2 for check tiers)" | The enforced floor also allows 2 for `conversations.work.prof.*`, `conversations.food.trait.*`, `*.child`, `*.teen`, `*.guard`, and 1 for `food.trait.sirben`. **The doc is wrong, not the content.** Rewrite the bullet to match `ContentLintTest.sayKeyPoolsMeetTheVariantFloor` |
| **A1f** | `DATAPACK.md` `conversations_gossip` table | lists 6 gossip types | `GossipEventType` has **7** — `quest` is missing from the table (its lang pool ships and is reachable from `rumors`) |
| **A1g** | `README.md` overlay claim | "each covers … the whole chat-mode vocabulary" | 12 of 68 `dialogue.chatmode.*` bases are overlaid, and **0 of the 468 `conversations.topic.*` branching lines**. Reword to "the personality-voiced deflection lines and the topic openers" |
| **A1h** | `CONFIG.md` `enableTopics` | "topic branches deflect to fallback lines" | The flag has **no effect at all** — see `F1` |
| **A1i** | `CONFIG.md` `enableQuests` | "quest-aware conditions score 0" | `questScore` never consults it — see `F2` |

**Acceptance (A1):** `grep -rn "two pilot topics\|Two topics are converted" .` returns nothing; the
DATAPACK variant-floor bullet matches the test; `quest` appears in the gossip type table.

---

## 2. Part A — Defects

Ordered by player-visible severity.

### A2 — `secret` tells the secret before you agree to hear it, then never tells it

**Why.** `conversations.cat.personal` says `conversations.secret.first` on the opener, and that line
**is the whole secret**:

> `dialogue.conversations.secret.first` = *"Alright. You've earned one. The 'famous' pie I bring to festivals? The trader sells it to me. Every year."*

The player is then offered `topic.secret.respond.accept` = *"I'll hear it."*, whose reply is
*"...Alright. Then here it is, and I'll not be able to unsay it."* — and the branch jumps to
`topic.secret.followup` (*"So now you're carrying it too."*) **without ever delivering anything**.
`decline` (*"Don't tell me something you'll regret."*) routes to the same followup, so a player who
refused is then offered `topic.secret.followup.promise` = *"It stays with me. You have my word."*

**Change.** Split the opener from the payload.

1. Rewrite the three `conversations.secret.first` variants into a *pre-disclosure* beat — the villager
   signals they have something, no content: e.g. *"There's a thing I've never said out loud in this
   village. …Do you want it, or shall we talk about the weather?"*
2. Move the current three secret payloads to a new key pool
   `conversations.secret.respond.accept` (replacing the current contentless line), and author
   matching payload pools for `conversations.secret.respond.ask_why_me`.
3. Give `decline` its own `next`: a new node `conversations.topic.secret.declined` whose answers are
   `offer_later` / `change_subject` / `leave`, none of which can promise to keep a secret the player
   never heard. `decline` must not advance the `secret` arc or set `secret.entrusted` (it currently
   does not — keep it that way) and must not reach `topic.secret.followup`.

**Files.** `dialogues/conversations.topic.secret.respond.json`,
new `dialogues/conversations.topic.secret.declined.json`,
`assets/mca_dialogue/lang/{en_us,pt_br}.json`,
`chat_intents/personal_arcs.json` (three new intents for the new node),
`conversation_catalog/topics.json` (no schema change; `secret` already declares `restraint`).

**Acceptance.** A path simulation asserts that `decline` never reaches `conversations.topic.secret.followup`
and never sets milestone `secret.entrusted`; `accept` says a key whose variants each contain the
secret's content.

---

### A3 — `arc.secret.resume.respond.hint_publicly` punishes a label that reads as asking permission

**Why.** Label: *"Shall I mention it to the others?"* → reply: *"…Say that again and I'll know exactly
what you are."* / *"That is the one thing. The ONE thing I asked."* A player asking a polite question
is branded a traitor. This is the sharpest label/consequence mismatch in the file.

**Change.** Either (preferred) **relabel to an intent, not a question** —
*"I've been thinking of letting it slip."* — or split into two answers:

- `ask_who_knows` = *"Does anyone else know?"* → informational, small `familiarity`, no penalty.
- `float_telling` = *"I nearly told someone. I didn't."* → tension +, trust −, honest confession beat
  with a repair path.

Keep one genuinely hostile option, but label it as hostile.

**Files.** `dialogues/conversations.arc.secret.resume.respond.json`, lang pairs, `chat_intents/personal_arcs.json`.

**Acceptance.** No answer label in the mod phrases a betrayal as a request for permission. Add a
lint note (see `E7`).

---

### A4 — `day.rough.followup.lighten` references a cat that exists in 1 of 3 opener variants

**Why.** `topic.day.rough.followup.lighten` = *"Well, the cat clearly won."* The cat only appears in
the **base** `conversations.day.rough` variant. `/1` is a sticking door, `/2` is a dropped egg. The
payoff triples down: `day.rough.lighten.landed` = *"Ha! The cat won."*, `/1` = *"…since the stew hit
the floor."*, `.polite` = *"Heh. The cat did win, to be fair."* — a non-sequitur ~67% of the time.

Same class of bug: `topic.weather.young.respond.play_along` = *"Big sky sheep, definitely."* matches
only `weather.toddler/1`.

**Change.** Make labels and payoffs **variant-agnostic**. Rewrite `lighten` to
*"It sounds like the day was against you personally."* and rewrite the three `landed`/`polite`/`flat`
pools to refer to "the day"/"all of it" rather than a specific mishap. Same for `play_along`
(*"That does sound serious."*).

**Rule to add to `DATAPACK.md` §Words:** *a player label and its reply pool may only reference detail
that appears in **every** variant of the line they answer.*

**Files.** lang pairs only (`dialogue.conversations.topic.day.rough.followup.lighten`,
`dialogue.conversations.day.rough.lighten.*`, `…weather.young.respond.play_along`, `…weather.play_along.*`).

**Acceptance.** `E8` lint (below) passes.

---

### A5 — Four adult-only topics are reachable by children and teens

**Why.** `TopicEntry.ages` / `reachableBy` (`src/main/java/.../conversation/TopicEntry.java:68-76,138`)
has **no caller anywhere**. Four catalog rows declare `ages: ["adult"]` but their openers are gated
only `!toddler,!baby`:

| Topic | Opener |
|---|---|
| `regrets` | `dialogues/conversations.cat.personal.json:1920` |
| `secret` | `dialogues/conversations.cat.personal.json:2159` |
| `rumors` | `dialogues/conversations.cat.village.json:771` |
| `work_offer` | `dialogues/conversations.cat.profession.json:164` |

So a child can be asked to keep an adult's secret.

**Change.** Two parts.

1. **Data:** add `"constraints": "adult"` to those four opener answers (the token is already in use at
   `conversations.cat.relationships.json:5`).
2. **Lint (`E1`):** assert catalog `ages` matches the opener's age gating for every topic. This is the
   real fix — otherwise it drifts again.

**Acceptance.** `E1` passes; a child villager's Personal category shows neither `regrets` nor `secret`.

---

### A6 — Toddlers are routed into child/teen dialogue in 8 of 10 topics

**Why.** Only `day` and `fears` ship a dedicated toddler node. For **life, dreams, hopes, village,
food, weather, season, people**, `age_group: toddler` routes to `topic.<x>.young.respond`, which is
written for a ten-year-old and shared with teens. The result:

> Toddler: *"I'm little! I do puddles and snacks and naps. It's a full day."*
> Player: *"Go on, tell me properly."*
> Toddler: *"Really? Right — nobody ever asks for the long version."*

`weather` and `season` are worse: they have **no** child or teen entry at all, so `young.respond`
exists solely for toddlers and is written for the wrong age.

**Change.** Add `conversations.topic.<t>.toddler.respond.json` for **life, dreams, hopes, village,
food, weather, season, people** (8 new nodes), modelled on
`conversations.topic.day.toddler.respond.json`. Each: 3 answers + `leave`, tiny deltas
(`warmth ±1..2`, `familiarity +1`), `budget` = the topic's class, `policy: "daily_repeat"`, no arc
advance, no milestone.

The toddler register is already excellent where it exists — copy its rules, don't copy its lines:
concrete nouns, superlatives, present tense, magical causation, self-interrupting.
(`fears.toddler/2` = *"When Mama hides her face behind her hands. Where does she GO?"* is the target.)

**Files.** 8 new dialogue files; 8 opener re-routes in `conversations.cat.{personal,village,chitchat}.json`;
~120 new lang keys × 2 locales; 24 new chat intents (3 per node) in the matching `chat_intents/*.json`;
`IntentMatcherTest` utterances.

**Acceptance.** No `age_group: toddler` result routes to a `*.young.*` node anywhere; `E2` lint.

---

### A7 — `news` and `noticed` declare `child`/`teen` and ship no age content

**Why.** Both catalog rows list `child, teen`; neither opener has an age branch and there is no
`*.young.respond`. Children get adult lines about deaths and divorces.

**Change.** Either add `conversations.topic.{news,noticed}.young.respond.json` (preferred — a child
reporting village news in a child's terms is good content), or narrow both catalog rows to
`["adult"]` and gate the openers. Recommend the former for `news`, the latter for `noticed`
(reading an adult's mood is an adult move).

**Acceptance.** `E1` passes for both topics.

---

### A8 — The rebuff tier exits into gratitude

**Why.** `fears.open.comfort.rebuff` = *"Don't. I didn't hand you that so you could pat it on the
head."* and `fears.open.challenge.rebuff` = *"Don't make my fear a test of your character."* Both
route to `conversations.topic.fears.open.close`, whose three answers are:

- `thank` = *"Thank you for trusting me with that."*
- `means` = *"That took something to say."*
- `leave` = *"I'll let you be."*

Two of three are gratitude for opening up, immediately after being told off. Tonally the worst
collision in the file.

**Change.** Add `conversations.topic.fears.open.close.rebuffed.json` — same shape, rebuff-aware
answers: `apologize` (*"Fair. That came out wrong."* → settles `tension`, no hearts),
`accept` (*"Understood. I'll shut up."* → small `respect`), `leave`. Point all four rebuff results at
it. Do the same for any future checked stance.

**Rule to add:** *a rebuff tier must never route to a close node whose answers assume the stance landed.*

**Files.** new `dialogues/conversations.topic.fears.open.close.rebuffed.json`;
`dialogues/conversations.fears.json` (the 3 rebuff results' `next`);
lang pairs; 2 intents in `chat_intents/fears.json`.

---

### A9 — `topic.fears.scarred.respond.apologize` hands the knife back

**Why.** Apologising for having pushed routes to `topic.fears.guarded.respond`, whose answers include
`press` = *"Come on. Out with it."* — the exact button that caused the scar.

**Change.** Route `scarred.apologize` to a new `conversations.topic.fears.repaired.json` with
`ask_safer` / `respect` / `leave` only — no `press`. This mirrors the existing
`conversations.topic.day.rough.repair.json` pattern already in the repo.

---

### A10 — `press` crit and success promise disclosure the tree does not have

**Why.** `fears.open.press.crit` = *"…Alright. The rest of it, the true shape — and it stays between
us."* → routes to `topic.fears.open.followup`, whose node text is *"So now you know."* The player was
told they'd be told, and weren't. `success` has the same problem (*"There's more, yes. Give me a
breath. …There."*).

**Change.** Add a **crit-only** disclosure node `conversations.topic.fears.open.disclosed.json`
reached from `press.crit` and `comfort.crit`, containing the *actual* extra content (an authored
second layer of the fear, personality-overlaid — see `C1`), then falling through to
`topic.fears.open.followup`. A crit that opens the villager "further than asked" must yield text the
success tier does not have; otherwise the tier system is invisible.

---

### A11 — `success` and the checks-disabled `plain` fallback are the same line

**Why.** `fears.open.comfort.success` = *"…Thank you. Most people change the subject."*
`…comfort.plain/1` = *"Most people go quiet when I say it. You didn't."* Players who toggle
`enableChecks` off will not notice.

Compounding it: `ContentLintTest`'s variant-floor exemption keys off the **name suffix**
(`key.endsWith(".success")`), so `conversations.fears.{challenge,press}.success` — which are the
*checks-disabled plain fallbacks*, seen by every player with checks off — get the relaxed 2-line
floor purely by naming coincidence.

**Change.**
1. Rename the plain-fallback say keys from `…{challenge,press}.success` to `…{challenge,press}.plain`
   for consistency with `comfort.plain`, and fill each to 3 variants.
2. Rewrite `success` pools so they are the "landed, but no extra disclosure" beat, distinct in image
   from `plain`.
3. Fix the lint to test **the result's `conversations_check` condition**, not the key's spelling (`E3`).

---

### A12 — Three exclusive-choice members are permanently recorded and never read

**Why.** Catalog declares four exclusive groups; only `secret.promise` reads both members plus `none`.

| Group | Members | Read back |
|---|---|---|
| `fears.support` | `pledged`, `stepped_back` | `pledged` only (`arc.fears.followthrough.respond.json:72`) |
| `dreams.support` | `pledged`, `honest` | `pledged` only (`arc.dreams.resume.respond.json:12`) |
| `regrets.stance` | `forgave`, `listened` | `forgave` only (`arc.regrets.resume.respond.json:12`) |
| `secret.promise` | `kept`, `declined` | both + `none` ✅ |

So *"I can't promise that. But I'm listening."* reads back exactly like never having had the
conversation. That directly undercuts `CHANGELOG.md:44`'s headline claim.

**Change.** For each of the three groups, add a second gated result (and lang pool) on the same
answer that reads `{"exclusive": "<group>", "is": "<other member>"}`, plus keep the existing
`"is": "none"` / negative-sink fallback. Copy the `secret.promise` shape verbatim — it is the model.

The `stepped_back` / `honest` / `listened` voice should be **warm, not punitive**: the villager
respects the honesty and remembers it differently, not worse.

**Acceptance.** `E4` lint: every declared exclusive member is read by at least one condition.

---

### A13 — `quest` gossip is seeded, localized, and unreachable from `news`

**Why.** `GossipEventType.QUEST` is seeded at `compat/quests/ConversationsQuestsEvents.java:91`, has
3 en_us + 3 pt_br variants at `mca_dialogue/lang/en_us.json:174-176`, and is tellable only via
`rumors` (whose query is `{}` = all types). `conversations.cat.events.json`'s `news` answer enumerates
only the six village types across three branches, and its "none" sink covers only those — so a
village whose sole untold event is a quest hears *"Quiet week, honestly."*

**Change.** Add a fourth `news` branch: `{"types": ["quest"]}` → `branch: "helped"` →
`conversations.topic.news.helped.respond.json`, and add the matching `-2000` sink to the `none` result.

**Files.** `dialogues/conversations.cat.events.json`, new respond node, lang pairs, 3 intents.

---

### A14 — Label collisions that make adjacent buttons indistinguishable

**Why.** Three different buttons in three adjacent `life` nodes are all *"Thank you for telling me."*
with three different consequences (`topic.life.respond.leave` ends the session,
`topic.life.followup.leave` ends the session, `topic.life.close.thank` applies a `trust` delta).
Similarly `topic.people.{respond,followup}.leave` are both *"I'll not stir it."*

**Change.** Within a single topic, no two answer labels may be byte-identical unless they are the
same consequence. Rewrite the collisions. Cross-topic reuse of a bare exit line (*"Right you are."*)
is fine and should stay — that is voice, not confusion.

**Acceptance.** `E5` lint: per-question-tree label uniqueness.

---

### A15 — `chatmode.clarify` speaks the design vocabulary aloud

**Why.** `dialogue.chatmode.clarify` = *"Do you mean %2$s, or %3$s?"* filled from
`dialogue.chatmode.topic.*`, several of which are stance descriptions:
`comfort` = "offering comfort", `challenge` = "urging me to face it", `press` = "hearing the rest",
`unsure` = "being unsure". Rendered: *"Do you mean offering comfort, or hearing the rest?"* — the
villager narrating your conversational stances back at you.

**Change.** Rewrite the ~15 stance-shaped `chatmode.topic.*` values as **quoted fragments of what the
player would say**, e.g. `comfort` → "that it sounds hard", `press` → "that you want the rest",
`challenge` → "that I could face it". Topic nouns (`weather`, `work`) are already fine.

---

## 3. Part B — De-cloning the deep topics (highest content value)

This is the single largest quality problem and the biggest opportunity in the release.

### The measurement

`life`, `dreams`, `hopes`, `regrets`, `secret` share **three entire sub-trees, byte-identical, five
ways**. Verified by direct comparison of `dialogue.conversations.<topic>.<branch>.*`:

| Branch | Keys per topic | Distinct strings across all 5 topics |
|---|---|---|
| `*.guarded.*` (`ask_safer`, `leave`, `press`, `respect` × 3 variants) | 12 | **12** (i.e. 100% identical) |
| `*.again.*` (`apologize`, `leave`, `press` × 3) | 9 | **9** |
| `*.close.*` (`leave`, `say_means`, `thank` × 3) | 9 | **9** |

Byte-identical share of each topic's total lines: **life 46%, hopes 44%, regrets 40%, dreams 38%,
secret 37%.** For player labels: **life 71%, dreams 71%, hopes 68%.**

The refusal to tell you a *secret* and the refusal to talk about your *hopes for the harvest* are
currently the same sentence:

> `conversations.{dreams,hopes,life,regrets,secret}.guarded.press` = *"I could. I'm choosing not to. There's a difference."*

Structurally it is worse: every node in the mod is `[warm, curious, hostile, leave]`, all deep topics
are `respond → followup → close`, and 106 of 106 nodes offer 3–4 answers. After two topics a player
can predict which button is which by position.

### B1 — Fork the 30 shared deep-topic keys five ways

**Change.** Author topic-specific variants for all 30 shared keys × 5 topics = **150 keys × 2 locales
= 300 strings**. Each must be recognisably about *that* topic's subject matter:

| Key | `secret` should sound like | `hopes` should sound like |
|---|---|---|
| `guarded.press` | a refusal to be pried at | a reluctance to jinx it |
| `guarded.respect` | relief that you didn't push | a quiet "ask me after harvest" |
| `close.say_means` | the weight of being trusted | the embarrassment of having wished aloud |
| `again.press` | "I already handed you the one" | "it hasn't changed since this morning" |

**Files.** `assets/mca_dialogue/lang/{en_us,pt_br}.json`. No structural change.

**Acceptance.** `E6` lint: no `dialogue.conversations.<topicA>.*` string is byte-identical to a
`dialogue.conversations.<topicB>.*` string where A≠B and both are in the deep set — with an
allow-list for bare exit lines under 5 words.

### B2 — Retire the "Nobody's ever ___" reward machine

**Why.** 84 lines contain "Nobody"; 21 contain "Most people"; the same reward beat —
*"…You'd actually [X]? Nobody's ever [Y]."* — fires at **23 distinct sites**, one for essentially
every kind act in the game:

```
fears.open.pledge              "...Nobody's said that before."
dreams.followup.pledge_help    "...Then it's the first time it's been more than mine."
hopes.resume.offer_help        "...You'd actually help."
village.followup.offer_help    "...You'd actually put a hand to it? Most people just have opinions."
food.trait.offer_alternative   "...You'd actually do that? Nobody plans around me. Nobody."
checkin.rough.offer_help       "...Nobody's offered in a month."
work.followup.hear_burnout     "...Nobody's said that to me."
regrets.followup.forgive       "...Nobody's said that."
noticed.grieving.validate      "...Aye. I am allowed, aren't I. Nobody's said that."
secret.resume.check_in         "...You asked after ME, not the secret. Nobody does that."
news.followup.ask_how_they_are "...Me? Nobody's asked me."
hopes.followup.ask_first_step  "...Nobody's asked me to break it down."
```

Each is a good line. Collectively they establish that every villager has been ignored by everyone
forever until the player arrived — implausible, self-flattering, and it devalues each moment.

**Change.** Keep the beat at **no more than 6 sites**, reserved for the deepest disclosures
(`fears.open.pledge`, `secret.resume.check_in`, `regrets.followup.forgive`, and three of your
choosing). Rewrite the other ~17 into **other** kinds of acknowledgement, at least four distinct
shapes:

- *practical* — "Right. Tuesday, then. Bring the good axe."
- *deflecting* — "Don't make a thing of it. …But aye."
- *surprised-then-brisk* — "Huh. Alright. Where were we."
- *reciprocal* — "Then you get one back, next time you're the one having the day."

**Acceptance.** `E9` lint: at most 6 `dialogue.conversations.*` values contain the substring
`Nobody's` / `Nobody has`; at most 10 contain `Most people`.

### B3 — Break the `[warm, curious, hostile, leave]` monoculture

**Why.** Every one of 106 nodes has the same three-plus-exit shape, and the catalog reinforces it —
14 of 18 topics require `dismissal`, 13 require `curiosity`.

**Change.** Introduce two new node shapes and use each on at least four topics:

1. **The five-answer fork** (allowed: cap is 5). On `life.followup`, `work.followup`,
   `village.followup`, `people.followup`: add a *humour* stance and a *self_disclosure* stance
   alongside the existing three. Both already exist in the vocabulary and are badly underused
   (`humor` is biased by 9 of 17 personalities and required by **1** topic).
2. **The two-answer beat.** For high-tension moments (`fears.pressed`, `noticed.grieving`,
   `regrets.close`), a node with only *stay* and *go* is stronger than a menu. Two answers is legal
   and currently used zero times.

Update the affected catalog rows' `required_stance_families` accordingly (add `humor` to `day`,
`checkin`, `season`, `people`; add `self_disclosure` to `life`, `regrets`, `work`).

### B4 — Give the deep topics divergent *shapes*, not just divergent words

**Change.** Stop `respond → followup → close` being universal. Suggested per-topic shapes:

| Topic | Shape |
|---|---|
| `life` | `respond → followup → close` (keep — it is the chronological one) |
| `dreams` | `respond → followup → close`, but `followup` forks on whether the player was encouraging or honest, into two *different* close nodes |
| `hopes` | `respond → close` (short), with the depth coming from the **arc** rather than the tree |
| `regrets` | `respond → sit_with_it → close`, where the middle node is the two-answer beat from `B3` |
| `secret` | `respond → declined \| followup → close` (per `A2`) |

### B5 — Fix the worst variant pools

**Why.** Several 3-variant pools are one line and its editor's pass — same image, reworded. Worst:

- `conversations.regrets.again` — base and `/2` are both the "stones" metaphor.
- `conversations.fears.open.comfort.partial` — base and `/1` are both "kind but useless".
- `conversations.work.respond.challenge.polite` — all three are "it's fine, not love".
- `conversations.fears.scarred.give_space` — three ways to say "fine".

**The overlays are systematically worse.** Measured base↔`/1` string similarity averages 0.37–0.40
across personalities; e.g. `greedy.dialogue.conversations.gossip.none` base and `/1` are 0.81
similar (*"Nothing worth trading. A quiet week — bad for gossip, worse for business. Even the
chickens are withholding."* / *"No news worth trading. Bad week for gossip, worse for business. Even
the hens are holding out on me."*).

**Change.** Rewrite so each variant takes a **different angle**, not a different wording. The repo
already contains the standard to hit — copy the method from these:

- `conversations.rumors.none` — three different *reasons* nothing is happening, one of which
  characterises the speaker (*"And I'd have heard it, believe me. These walls are thin."*).
- `conversations.village.young.dismiss` — *"It's NOT dull." / "You haven't seen the well." / "…It's a bit dull."* — three emotional positions, including a capitulation.
- `conversations.noticed.fine.dig` — deflect / caught-out / postpone.

**Acceptance.** `E10` lint (advisory, warn-only): flag any variant pair inside a pool with
normalized similarity > 0.65.

---

## 4. Part C — Expansions using hooks that already exist

Every item here is **data only** unless marked ⚙ (needs the small Java in Part F). These are ranked
by player-visible value per line of JSON.

### C1 — Personality overlays for the branching layer ⭐ biggest single win

**Why.** Overlays cover **27 of 1,198** `dialogue.conversations.*` base keys (2.3%) — all of them
topic *openers*. The personality speaks the first sentence and then hands the conversation to one
generic narrator for the next six exchanges. All **468** `conversations.topic.*` lines — the entire
1.1.0 branching body, i.e. most of what an engaged player reads — have zero overlay in any of the 21
namespaces.

The overlays are the best-differentiated writing in the project and should not stay a one-line
trick. Compare on `fears.first`:

> gloomy — *"That the best days already happened and I wasn't paying attention."*
> peppy — *"That one morning the cheer won't come, and everyone finds out how hard I row to keep it. …ANYWAY! Also spiders!"*
> greedy — *"Honestly? Dying with full pockets and an empty table. What was I saving it all FOR, then? …Forget the second part."*
> lazy — *"Waking up one day in a hurry and never getting out of it."*

**Change.** Extend the overlay surface by **one tier**: the *first reply* of every converted topic
(the `respond` node's say keys), not the whole tree. Concretely, per personality namespace add:

- `conversations.day.{good,rough,ordinary}.respond.*` — the reaction to your first reply
- `conversations.{life,dreams,hopes,fears,secret,regrets}.respond.listen`
- `conversations.checkin.rough.offer_help`
- `conversations.work.followup.hear_burnout`
- the six `*.revisit` lines (currently un-overlaid and among the 30 most-referenced keys)

Budget: ~20 keys × 21 namespaces × 2 variants × 2 locales ≈ **1,680 strings**. That is the largest
line item in the release; scope it to a subset of namespaces per PR if needed, but ship whole
namespaces at a time so `OverlayLintTest` stays green.

**Also fix the three single-personality keys:** `regrets.first` (gloomy only),
`us.firstmet.memory` (flirty only), `us.worries.open` (gloomy only) — 59 of 861 overlay cells empty.
Either fill them for all 21 or remove them from the overlay set.

### C2 — Mood in reply nodes ⭐

**Why.** 45 `mood` conditions exist; **all 45 are on openers or legacy questions**. Not one of the
106 branching reply nodes reads mood. `passive` and `fine` are never branched on at all.

**Change.** Add a mood-varying result to the *reply* of at least eight topics — the moment where the
player has chosen what to say and the answer's temperature should matter most:

```jsonc
// e.g. in conversations.topic.day.rough.followup.json, answer "offer_help"
{ "baseChance": 0,
  "conditions": [ { "chance": 100, "mood": "depressed" } ],
  "actions": { "next": "conversations.cat.chitchat",
    "say": "conversations.day.rough.offer_help.low",
    "conversations_affection_apply": { "decision": "day.rough.offer_help", "delta": 1, "budget": "quick", "policy": "daily_repeat" },
    "conversations_disposition_apply": { "topic": "day.rough.offer_help", "deltas": { "warmth": 4, "trust": 2 } } } }
```

Write the `passive`/`fine` branch too — "they're just having a normal day" is unwritten content.

### C3 — Time of day ⭐ cheapest win in the mod

**Why.** MCA's native `time_min` / `time_max` conditions have **zero uses**. The `time_of_day`
template variable is fully implemented (`TemplateContextFactory.java:97`), localized in both locales
with four buckets, and referenced by **zero** lines. No villager anywhere says anything different at
dawn than at midnight.

**Change.**
1. Add `time_min`/`time_max` branches to `greet.json`, `conversations.topic.checkin.*`, and
   `conversations.topic.day.*` openers — a dawn greeting, a late-night one.
2. Use `conversations_say` with `time_of_day` in at least six lines.
3. Add a "you're up late" beat to `checkin` with its own small `warmth`/`familiarity` delta.

### C4 — Make `tension` and `familiarity` readable ⭐

**Why.** `tension` has **105 writes and 0 reads**. `familiarity` has **95 writes and 0 reads** and
never decays. `attraction` has 0 of both. **No `min` bound is used anywhere in the entire content
set** — all 12 disposition gates are `max` guards. There is not one "you have earned this" threshold
in the mod.

**Change.** Three gates, each of which makes an existing invisible mechanic visible:

```jsonc
// 1. Tension is showing — put on any topic opener
{ "chance": 100, "conversations_disposition": { "axis": "tension", "min": 30 } }
// -> a shorter, cooler version of the opener. This is the missing half of the apology mechanic:
//    the CHANGELOG says apologising settles the air, but nothing reads whether the air was unsettled.

// 2. Long acquaintance — familiarity never decays, so this is real shared history
{ "chance": 100, "conversations_disposition": { "axis": "familiarity", "min": 45 } }
// -> `life`, `us`, `village` openers that reference how long you have known each other.

// 3. Earned depth — the first positive threshold in the mod
{ "chance": 100, "conversations_disposition": { "axis": "trust", "min": 55 } }
// -> an extra answer on `secret.followup` / `fears.open.followup` that only appears at high trust.
```

Every one needs its `{"chance": -2000, "conversations_disabled": "dispositions"}` sink — currently
**zero** content sinks on `"dispositions"`, so a third-party pack copying the documented idiom finds
it unexercised.

### C5 — Checks beyond `fears` ⭐

**Why.** `conversations_check` is used by **6 check ids, all in one topic**. `enableChecks` and
`enableCheckTiers` therefore affect a single topic's live content. Only 3 of 14 stance families
(`empathy`, `challenge`, `boundary_push`) are ever named by a check, so **11 of 14 families' tuned
interiority bias is computed by nothing at runtime**.

**Change.** Add one checked stance to each of five topics, chosen to activate the neglected families:

| Topic | Stance | Family | Axis | Difficulty |
|---|---|---|---|---|
| `regrets` | `forgive` | `empathy` | `trust` | 40 |
| `work` | `hear_burnout` | `curiosity` | `respect` | 35 |
| `village` | `offer_help` | `practical_help` | `respect` | 30 |
| `people` | `disagree` | `respectful_disagreement` | `respect` | 45 |
| `day`/`weather` | `lighten` / `joke` | `humor` | `warmth` | 25 |

The `humor` one matters most: `CHANGELOG.md` headlines *"a joke on a bad day lands for a playful or
upbeat villager, falls flat on a gloomy, sensitive or anxious one"*, 9 of 17 personalities carry a
humour bias — and exactly **one** topic requires the family.

Use the canonical checked-stance shape from `DATAPACK.md:289` verbatim: guard result + four tier
results + checks-disabled plain fallback, all four tiers sharing id/axis/difficulty.

### C6 — `curiosity` interiority coverage ⚙-adjacent

**Why.** `curiosity` is required by **13 of 18 topics** — more than any family except `exit` and
`dismissal` — and biased by only **4 of 17** personality profiles. Asking a villager a follow-up
question currently lands identically on `crabby` and `friendly`.

Baseline coverage is also lopsided: `warmth` 13/17, `trust` 6/17, `tension` 4/17, **`respect` 3/17**,
`attraction` 1/17, `familiarity` 0/17 — yet content writes `respect` 91 times.

**Change.** In `data/mcaconversations/interiority/personalities.json`:
- add a `curiosity` bias to at least 12 more profiles (clamp ±12);
- add a `respect` baseline to at least 10 more profiles (clamp ±15);
- add `candor` bias to more than 3 profiles once `C7` gives it content.

### C7 — Give `candor` and `flirtation` content, or drop them

**Why.** Both are in `StanceFamily` and both carry interiority bias, and **no catalog row requires
either**. `flirtation` is the stance twin of the dead `attraction` axis and the three unused
orientation traits (`bisexual`, `homosexual`, `asexual`) — the entire romance vertical is scaffolded
and unwritten. `candor` has no such excuse.

**Change.**
- **`candor`:** add it to `noticed`, `people` and `work` catalog rows and author a blunt-honesty
  stance on each (*"You're not fine and we both know it."*). Cheap, high value, no romance design
  needed.
- **`flirtation` + `attraction`:** deliberately **out of scope for 1.2.0** unless you convert `us`
  (see `D1`). Note it in `CHANGELOG` as known-unbuilt rather than leaving it looking like an
  oversight.

### C8 — Weather, season and holiday beyond the two openers

**Why.** All 36 world-condition uses are in **one file** on **two answers**
(`conversations.cat.chitchat.json`). Nothing else in the mod is world-aware: a farmer at harvest, a
fisherman in a storm and a grieving villager at midwinter get identical text. `clear` and `none` are
implemented, lang-backed and **never authored** — content synthesises "ordinary" from negative sinks
instead.

**Change.**
1. Author the positive `{"is": "clear"}` and `{"is": "none"}` branches.
2. Add season/weather results to `work` (harvest, storm-bound fishing), `village` (a hard winter),
   `food` (what is in season) and `checkin`.
3. Use the `weather` / `season` / `holiday` template vars — currently **0 uses each** despite full
   implementation and localization.

⚙ Needs `F3` (feature keys for `seasons`/`holidays`) so the new content can carry a proper
degradation sink.

### C9 — Wire up the gift layer

**Why.** The whole gift-memory subsystem is reachable from **one spouse-gated result**
(`conversations.us.json:25`). `SMITTEN` is written, has two config knobs, feeds the check score at
+6 — and is gated on by **zero** dialogue results. `PROUD` (quest completion) is written,
config-tunable, and read by **nothing at all**, not even the check resolver. `last_gift_item` has 1
use. `LastGift.count` and `gameTime` are persisted and unreachable.

**Change.**
1. Add a `mcaconversations.state.grateful` branch to `greet.json` and `checkin` for **non-spouses**
   using `last_gift_item`.
2. Add a `smitten` branch to `greet` and to `feelings` once converted.
3. Add `PROUD` to `MoodModifiers` (⚙ `F4`) and author a post-quest beat on `work_offer` and `checkin`
   that reads `mcaconversations.quest.done.<ns>.<path>` — **zero** dialogue files read those memories
   today, so a villager who remembers exactly which quest you finished says nothing about it.

### C10 — Read the session `branch` instead of duplicating it into node names ⚙

**Why.** `conversations_session` carries an optional `branch`; **114 shipped results set it**
(`rough`, `good`, `sad`, `glad`, `grieving`, `storm`, `trait`, `young`, …) and
`ConversationSession.branch()` has **exactly one caller — its own setter**. Nothing reads it. Content
compensates by duplicating the branch into the node name (`conversations.topic.day.rough.respond`),
which is a large part of why node count is 106 and why sibling branches drift apart.
`op: "branch"` itself has **0 uses**.

**Change.** ⚙ `F5` registers a `conversations_session` **condition**
(`{"branch": "rough"}` / `{"topic": "day"}`). Then collapse at least one family of near-identical
nodes — the five `*.again.respond` nodes, or `day.{good,ordinary,rough}.followup` — into one shared
node that branches on session state. This is the structural half of `B1`.

### C11 — Expose the daily budget so the villager can voice it ⚙

**Why.** `ProgressRecord.positiveToday` / `negativeToday` / `repeatsToday` / `everApplied` are
tracked per (villager, player) and exposed by **no condition**. Today the budget silently clamps the
number; nothing lets the villager say *"we've talked enough for one day."*

**Change.** ⚙ `F6` registers `conversations_budget`
(`{"axis": "positive"|"negative"|"repeats", "min"?, "max"?, "decision"?}`). Then author a
budget-aware close on `day`, `checkin` and `fears`: at cap, the villager warmly winds the
conversation down instead of the player quietly getting nothing.

### C12 — MCA-native conditions at zero uses

Seven native conditions are available, safe (unlike native `personality`, which crashes world load
on unknown ids and is correctly avoided), and used **zero** times. Each is a self-contained content
seam:

| Condition | Content it unlocks |
|---|---|
| `rank` (`outlaw, peasant, merchant, noble, mayor, monarch`) | A mayor and a peasant currently give identical `village` and `people` answers |
| `has_item` / `item` / `tag` | React to what the player is holding — a drawn sword, a bouquet, a pickaxe |
| `min_health` | "You look hurt." Zero such lines exist |
| `emeralds` | Obvious fit for `greedy`, which has 5 stance biases and no economic content |
| `is_married` | Married-to-someone-else is entirely unauthored; only `constraints: "spouse"` (this player) is used |
| `village_has_building` | The closest thing MCA offers to a village-stats gate — natural for `village` |
| `biome` | A desert village and a taiga village discuss weather identically |
| `advancement` | The natural gate for late-game topics |

Also unused: `current_chore: "none"`, `age_group: "adult"` (always implicit), and every
`hearts_min` above 0 — **there is no positive hearts gate anywhere in the content set**.

Pick **four** for 1.2.0. Recommended: `rank` on `village`/`people`, `has_item` on `greet`,
`min_health` on `checkin`, `village_has_building` on `village`.

---

## 5. Part D — New topics and trees

### D1 — Convert `us` and `family` (the ten remaining legacy starters) ⭐

**Why.** These are the last 10 rows in `LEGACY_REWARDED_STARTERS`
(`src/test/java/.../content/ConversationGraphLintTest.java:356-366`) and they pay hearts on the click
— up to **+8** for `conversations.feelings/same`. They are also **flat menus**: every answer's `next`
returns to the same node, so:

> `us.worries` = *"Is anything weighing on you?"* → *"There is something. I've been rehearsing how to say it for a week. Sit with me."* → **no follow-up exists.**

The mod gives a two-level branching tree to *the weather* and a dead end to a spouse admitting
they've rehearsed telling you something for a week. **This is the largest missing beat in the
project.**

Ironically the legacy prose here is the best voice in the repo — 0% `...`-openers versus 11–15% in
`regrets`/`secret`, and lines like `us.future.together/1` = *"I want us old and insufferable on a
porch somewhere, judging everyone's fences."* Preserve the voice; add the tree.

**Change.** Add catalog rows and full branching trees for:

| Topic | Depth | Ages | Stance families | Durable |
|---|---|---|---|---|
| `us` | relationship | adult (spouse) | `empathy, self_disclosure, candor, flirtation, exit` | arc `us` max 3; ms `us.reassured`; excl `us.worry` [`faced`, `deferred`] |
| `family` | standard | child, teen, adult | `empathy, curiosity, encouragement, dismissal, exit` | ms `family.confided` |
| `feelings` | deep | teen, adult | `empathy, candor, self_disclosure, restraint, exit` | arc `feelings` max 2 |

`us` is the natural home for `attraction`, `flirtation` and the three orientation traits (`C7`), and
for `spouse_name` (currently **0** uses even inside the two spouse-gated files) and `is_pregnant`.

**Acceptance.** `LEGACY_REWARDED_STARTERS` is **empty**; delete the ledger test's exemption list or
assert emptiness. No native `positive`/`negative` outside `conversations.work.legacy.json` and the
three `LEGACY_OFFSTATE_QUESTIONS`.

### D2 — Ask about a *named* person

**Why.** `people` and `rumors` cover the neighbours in the abstract. You can never ask a villager
about a specific person — despite `gossip.*` already templating `%2$s` with real villager names, and
`McaCompat.villageResidentNames:518` already resolving the roster.

**Change.** New topic `neighbour` (standard, adult/teen), entered from `conversations.cat.village`.
Uses `conversations_gossip_say` with a narrowed `types` filter and a new `phrase_prefix` — currently
**0 uses** of that parameter. Stances: `curiosity`, `candor`, `restraint`, `dismissal`, `exit`.

### D3 — The topics the trees never let you take

These moves have no button anywhere in 470 labels. Each is small and each closes a real gap:

| Missing move | Where it belongs | Note |
|---|---|---|
| **"I don't know what to say."** | every deep `respond` node | Every node forces warm / curious / cruel / leave. Honest inarticulacy is the most natural human response to a confession and is unrepresentable. Closest is `fears.open.followup.step_back`, which is still composed and competent. Family: `restraint` |
| **Humour in the deep topics** | `fears`, `regrets`, `secret`, `life`, `hopes` | `day.rough.lighten` and `weather.storm.joke` are the liveliest branches in the game; the deep topics have **no** humour stance at all. Deflecting with a joke is the commonest real response to something heavy |
| **Reciprocal disclosure** | `regrets`, `secret`, `life` | Exists only for `fears` (`share` = *"I'm afraid of that too"* → *"Two cowards make one brave pair"*). `deflect.secret` **explicitly invites it** — *"Secrets are traded, not given. Trust me with something of yours first"* — and there is no way to do it |
| **Deferral** | every node | Exactly one exit exists: `leave`. There is no "come find me tonight" / "tell me when you're ready" — despite `guarded.respect` saying *"Ask me again in a season and we'll see."* The player cannot act on the invitation just made. Pair with `C3` (time of day) |
| **Confidentiality logistics** | `secret` | After promising, you cannot ask "does anyone else know?", "what if it gets out?", "should I tell X?". The only later move is `hint_publicly` = instant betrayal. No middle exists (see `A3`) |
| **A physical beat** | anywhere | The entire mod is talking heads. `weather.storm.offer_shelter` is the single gesture in 470 labels, and it moves no one. At minimum, gestures that route to a different reply pool |

### D4 — Broken promises ⭐

**Why.** `fears.support: pledged` and `dreams.support: pledged` are permanent flags, and the arc
lines reward honouring them (`fears.followthrough.recall.pledged` = *"you turned up, which is the
rarer half of a promise"*). **There is no branch for pledging and then not turning up** — no
reproach, no disappointment, no decay. The one durable commitment the system tracks can only ever be
honoured. A pledge that costs nothing means nothing.

**Change.** Record the pledge with a `conversations_record` carrying a `time`, then in
`conversations.arc.fears.followthrough.respond.json` add a branch gated on
`{"memory": "mcaconversations.pledge.fears", "max_age": <N days>}` being **absent/expired**:
the villager notices you did not come back. Small `trust` loss, `tension` gain, no heart loss on the
first lapse; a repair path via apology. Mirror for `dreams.support`.

This also gives `A12`'s `stepped_back` / `honest` members something to contrast against — the honest
refusal now reads *better* than a broken pledge, which is the whole point of the exclusive.

### D5 — Fill the depth floor on arc-resume paths

**Why.** Seven ordinary adult paths offer **1 decision** where their deep class requires 3. The
shipped lint only measures the *deepest* branch, so these pass silently:

`arc.{life,dreams,hopes,regrets,secret}.resume.respond`, `arc.fears.plan.respond`,
`arc.fears.followthrough.respond` — verified terminal: every answer routes straight back to
`conversations.cat.personal`.

These are the **normal day-2 experience** of an arc, not a cooldown deflect. Returning to a villager
you have opened up with is currently thinner than meeting them for the first time.

**Change.** Give each resume node a second tier (`→ *.resume.followup`) with 3 answers plus exit.
Fix the lint to measure the **minimum** over normal adult branches, not the maximum (`E2`).

### D6 — Give `work_offer`, `rumors` and `noticed` a state-dependent outcome

**Why.** These three have **zero** multi-result answers: every button has exactly one authored
outcome for every villager in every state. The checklist rule *"there is no universally correct
button"* is not met. (`noticed` varies at the opener across four moods, but never at the choice.)

**Change.** One personality-gated or mood-gated result per topic minimum. `noticed` is the easy one:
`validate` should land differently on a `crabby` villager than on a `sensitive` one.

Related: the branching-off state for these three is a **stub**, not the 1.0.0 experience — their
`{"conversations_disabled": "branching"}` fallbacks say "there is nothing" unconditionally and grant
nothing, and the off-state `work_offer` never opens the quest screen even when a quest is available.
This contradicts `DATAPACK.md:333` ("all three off = exactly the 0.6.0 experience"). Fix the three
fallbacks to route to real content.

---

## 6. Part E — Lint and test hardening

These are what stop the findings above from re-appearing. All go in
`src/test/java/dev/otectus/mcaconversations/content/`.

| Id | Test | Assertion |
|---|---|---|
| **E1** | `ConversationGraphLintTest.catalogAgesMatchOpenerGating` | For every catalog topic, the opener answer's `constraints` / `age_group` conditions permit exactly the declared `ages`. Closes `A5`, `A7`. Also: make something actually call `TopicEntry.reachableBy` — today it has zero callers |
| **E2** | `ConversationGraphLintTest.topicsMeetTheirDepthFloorOnEveryNormalAdultPath` | Change `max` → `min` over normal adult branches (excluding cooldown/guarded/low-heart deflects). Closes `D5` |
| **E3** | `ContentLintTest.sayKeyPoolsMeetTheVariantFloor` | Determine the check-tier exemption from the **result's `conversations_check` condition**, not `key.endsWith(".success")`. Closes `A11` |
| **E4** | `ConversationGraphLintTest.everyExclusiveMemberIsReadBack` | Every member declared in `exclusive_groups` is named by at least one `conversations_progress` condition. Closes `A12` |
| **E5** | `ContentLintTest.answerLabelsAreUniqueWithinATopic` | No two answers in the same topic's node family share a byte-identical label unless they share a consequence. Closes `A14` |
| **E6** | `ContentLintTest.deepTopicsDoNotShareLines` | No `dialogue.conversations.<A>.*` value is byte-identical to a `dialogue.conversations.<B>.*` value for distinct A, B in {life, dreams, hopes, regrets, secret, fears}, except an allow-list of bare exits (< 5 words). Closes `B1` |
| **E7** | `ContentLintTest.answerLabelsDoNotPhraseBetrayalAsAQuestion` | Advisory: any answer whose result applies `trust ≤ -4` must not have a label ending in `?`. Closes `A3` |
| **E8** | `ContentLintTest.labelsDoNotReferenceSingleVariantDetail` | For each answer, the set of content nouns in its label and reply pool must appear in **every** variant of the line it answers. Practically: maintain a small hand-curated map of "specific detail" nouns per pool. Closes `A4` |
| **E9** | `ContentLintTest.rewardBeatIsNotOverused` | ≤ 6 values contain `Nobody's`/`Nobody has`; ≤ 10 contain `Most people`. Closes `B2` |
| **E10** | `ContentLintTest.variantPoolsAreNotParaphrases` | **Warn-only** (log, don't fail): flag pool members with normalized Levenshtein similarity > 0.65, including inside overlay files. Closes `B5` |
| **E11** | `ConversationGraphLintTest.rebuffTiersDoNotRouteToLandedCloseNodes` | A result whose `conversations_check.tier` is `rebuff` must not `next` into a node any of whose answers applies positive `trust`/`warmth`. Closes `A8` |
| **E12** | `PilotPathSimulationTest` → rename `TopicPathSimulationTest` | Currently covers **2 of 18** converted topics (`day`, `fears` only — grep for the other 16 returns zero hits). Add at least the happy path, the hostile path and the arc-resume path for all 18, plus `us`/`family`/`feelings` from `D1` |
| **E13** | `ChatIntentLintTest.antiKeywordsDisambiguateCoLiveIntents` | `antiKeywords` is used by **3 of 330** intents. Require it on any pair of co-live intents with keyword Jaccard ≥ 0.30. Concretely: globally-live `personal.regrets` (`regret`/`sorry`/`wrong`) shadows all five context-scoped `*.apologize` stances, and `chatmode.greeting` collides with `chitchat.day` on `morning`/`afternoon`/`evening` |

**Note on what is already clean** — do not "fix" these, they pass:
say-key ↔ lang parity (0 broken, 0 orphans), en_us/pt_br parity (0 diffs, 0 placeholder mismatches
across 4,243 keys × 2), intent binding (0 unbound, 0 bad contexts, 325 distinct question/answer
pairs), non-exit answer intent coverage (**270 of 270**), node shape (3–4 answers everywhere, always
an exit), guarded affection (0 native heart grants in branching nodes), progress read/write balance
at the id level, and the legacy ledger's own accuracy.

---

## 7. Part F — The six small Java changes

Keep these minimal and separable from the content PRs.

| Id | File | Change |
|---|---|---|
| **F1** | `McaConversationsConfig.java:34-48` | `enableTopics` currently has **no effect** — its only read is the `"topics"` feature key, and no dialogue JSON uses that key. Either wire it (author `conversations_disabled: "topics"` sinks on every converted opener) or remove the flag and the CONFIG.md entry. Recommend wiring |
| **F2** | `ConversationsMcaRegistrar.java:504-527` | `questScore` never consults `enableQuests`, so `conversations_quest_available` still matches with the flag off — contradicting CONFIG.md. Add the check. Also: `AVAILABLE` ignores `scope` entirely (`:517` calls `hasEligibleOffer` with no `thisOnly`), so `scope: "any"` is silently meaningless there |
| **F3** | `McaConversationsConfig.isFeatureEnabled` | Add `"seasons"` → `enableSeasonLines` and `"holidays"` → `enableHolidayLines` cases. Today `"world"` maps to `enableWeatherLines` alone and the default branch returns `true`, so `conversations_disabled: "seasons"` scores 0 forever and content cannot degrade gracefully. Required by `C8` |
| **F4** | `check/MoodModifiers.java:29-45` | `PROUD` is written (`ConversationsQuestsEvents.java:65`), config-tunable, and read by **nothing** — not even the check resolver, which reads only GRIEVING/ANNOYED/GRATEFUL/SMITTEN. Add `PROUD +4`. Required by `C9` |
| **F5** | `ConversationsMcaRegistrar` | Register a `conversations_session` **condition** — `{"topic"?, "branch"?}` — reading `ConversationSession.topic()/branch()`. 114 results write `branch` today and nothing reads it. Required by `C10` |
| **F6** | `ConversationsMcaRegistrar` + `ProgressRecord` | Register `conversations_budget` — `{"axis": "positive"\|"negative"\|"repeats", "min"?, "max"?, "decision"?}` — over `positiveToday` / `negativeToday` / `repeatsToday`. Required by `C11` |

Optional, larger, not required for 1.2.0: expose `LastGift.count`, and surface Serene Seasons
sub-seasons (`SeasonsBridge.SeasonQueries.seasonBucket:45` returns only the four top-level buckets).

---

## 8. Sequencing

Six PRs, each independently shippable and green.

**PR 1 — Truth and defects (small, do first).**
`A1` docs · `A2` secret · `A3` hint_publicly · `A4` cat · `A5` age gating · `A14` label collisions ·
`A15` clarify vocabulary · lints `E1`, `E5`.

**PR 2 — Tiers and repair.**
`A8` rebuff close · `A9` scarred repair · `A10` crit disclosure · `A11` plain/success ·
`A12` exclusive members · `D4` broken promises · lints `E3`, `E4`, `E11`.

**PR 3 — Ages and depth.**
`A6` 8 toddler nodes · `A7` news/noticed · `D5` arc-resume depth · `D6` state-dependent outcomes and
the three stub off-states · lints `E2`, `E12`.

**PR 4 — De-cloning (the big content PR).**
`B1` fork 150 keys · `B2` reward-beat rewrite · `B3` node shapes · `B4` divergent tree shapes ·
`B5` variant pools · lints `E6`, `E8`, `E9`, `E10`.

**PR 5 — Systems wiring.**
`F1`–`F6` · `C2` mood in replies · `C3` time of day · `C4` tension/familiarity/trust gates ·
`C5` checks in five topics · `C6` interiority · `C8` world flavour · `C9` gifts and quests ·
`C10` session branch · `C11` budget · `C12` four native conditions · `A13` quest gossip · lint `E13`.

**PR 6 — New content.**
`D1` convert `us`/`family`/`feelings` (empties the ledger) · `D2` named neighbours · `D3` missing
moves · `C1` overlay tier two · `C7` candor.

**Rough content volume for the release**

| Item | New/changed strings (× 2 locales) |
|---|---|
| `B1` deep-topic forking | 300 |
| `C1` overlay tier two | ~1,680 |
| `A6` toddler nodes | ~240 |
| `D1` us/family/feelings trees | ~700 |
| `C2`–`C12` system branches | ~500 |
| `B2`/`B5` rewrites | ~250 |
| **Total** | **≈ 3,700 strings**, roughly **+45%** on the current 4,243-key pt_br footprint |

That matches the brief: incremental in structure, large in conversation data.

---

## 9. Appendix — conventions to follow

**Node naming.** `conversations.topic.<topic>[.<branch>].<tier>` where tier ∈
`respond, followup, close`; arc nodes `conversations.arc.<arc>.<stage>.respond`.
Age nodes: `.toddler.respond`, `.young.respond` (child+teen).

**Say keys.** `conversations.<topic>[.<branch>].<answer>[.<outcome>]`. Outcomes:
`crit, success, partial, rebuff` for check tiers; `plain` for the checks-disabled fallback;
`guard` for below-gate replies; `landed, polite, flat` for the three-temperature pattern.

**Action ordering inside `actions`** (checklist-mandated): state → `next` → `say`.
That is: `conversations_progress_apply`, `conversations_record`, `conversations_affection_apply`,
`conversations_disposition_apply`, `conversations_session`, then `next`, then `say`.

**Every new affection result** declares a stable `decision` id prefixed with its topic, an explicit
`policy`, and a `budget` matching the catalog depth class.

**Every new conditional result** carries its `{"chance": -2000, "conversations_disabled": "<feature>"}`
sink, or a plain-MCA sibling on the same answer.

**Chat intents.** One per non-exit answer, `context` = the exact question id, ≥ 3 natural phrases,
keyword set distinct from every other co-live intent, `antiKeywords` where Jaccard ≥ 0.30. Add ≥ 3
utterances per stance to `IntentMatcherTest`.

**Voice guardrails.** The prose currently contains **zero** modern-therapy register — no "hold
space", "process that", "boundaries", "unpack", "safe space". That is unusual and worth protecting;
consider a lint on the vocabulary list. Also watch the existing tics, measured across 1,871 villager
lines: `...`-openers 8.2%, "Aye" 7.3%, "Nobody" 4.5%, "Right you are." 41 occurrences (24 of them
byte-identical, including twice as a *player* label). New content should not add to these.

**The standard to hit** is already in the repo — `work.prof.*`, `food.trait.*`, the toddler lines and
the personality overlays are markedly better than the deep-topic bodies. Examples worth reading
before writing anything:

> `work.prof.shepherd/1` — *"Shearing season is one long argument I have with forty woolly lawyers."*
> `work.prof.cultist` — *"We are a BOOK CLUB. A perfectly ordinary book club. The chanting is... enthusiasm for literature."*
> `food.trait.coeliac` — *"Bread hates me. Actual bread. In a village that's half bakery."*
> `fears.toddler/2` — *"When Mama hides her face behind her hands. Where does she GO?"*
> `season.holiday.midwinter` — *"Midwinter's night — the year's darkest. We light every candle we've got and dare the dark to outlast us."*
> `us.future.together/1` — *"I want us old and insufferable on a porch somewhere, judging everyone's fences."*
