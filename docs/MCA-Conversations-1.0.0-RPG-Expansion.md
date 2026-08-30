# MCA: Conversations 1.0.0 — RPG Depth Expansion Layer

> **How to use this document.** This is an *additive* layer for the existing "Branching Conversation Rework" prompt. It does not replace that prompt; it deepens it. Sections are numbered to slot alongside the originals (e.g. §4a follows §4). Where a section says **REPLACES** or **AMENDS**, apply it to the corresponding original section. Everything here inherits the original prompt's disciplines: inspection-first Phase 0 with no code written, evidence-gating from MCA's actual source, the honesty rule (deterministic checks before anything is called complete), non-destructive atomic writes, minimal mixins, no AI/remote/heavy runtime generation, and graceful fallback to native MCA Chat.
>
> **Design mandate for this release:** conversations should feel like a *roleplaying* system, not a well-engineered branching flowchart. Choices must be ample, legible as *different believable stances*, and consequential in ways the player can feel across a single conversation **and** across the whole save. The villager should feel like a person with interiority — wants, sensitivities, history, and a relationship arc — not a dispenser of scored responses.

---

## §0a. Explicit design tension to resolve during Phase 0

Before implementing, reconcile three forces that genuinely pull against each other, and record the reconciliation in the design doc:

1. **RPG depth** wants many relationship dimensions, gated content, and long-arc irreversibility.
2. **MCA's economy** is deliberately a single scalar (hearts) with a modest interaction budget. Fighting it produces balance chaos and breaks parity with the rest of MCA.
3. **Anti-farming + lint + "don't over-engineer"** want few reward surfaces, bounded state, and provable reachability.

The resolution this release adopts (unless inspection proves it unworkable):

- **Hearts remain the single authoritative, MCA-visible relationship economy.** Nothing below replaces hearts or duplicates them as a second visible score.
- **A new bounded disposition vector is *internal* and *modulatory*.** It decides *which branches open* and *how lines are voiced and scored* — it does **not** appear as a competing number, and it is not farmable for MCA-facing progression.
- **Every added reward surface gets a farming guard and a lint rule in the same change.** No reward surface ships without both.

If Claude Code cannot satisfy all three, it must stop at the STOP gate and surface the specific conflict rather than silently picking one.

---

## §4a. Relationship model: from scalar to vector (AMENDS §4 and §5)

Introduce a per-villager, per-player **disposition vector** that sits *beside* MCA hearts and modulates conversation content. Hearts stay authoritative and MCA-visible; the vector is internal, bounded, versioned, and server-authoritative.

Suggested axes (finalize against MCA's mood/heart semantics before committing):

- **Trust** — belief that the player has their back; gates confiding, secrets, vulnerability. Slow to build, faster to lose, decays only slightly.
- **Respect** — regard for the player's competence and principles; gates being taken seriously, advice being accepted, certain proud/guarded personalities opening up. Built by candor, competence, honoring commitments.
- **Warmth** — enjoyment of the player's company (platonic); gates playfulness, casual disclosure, invitations. Fluid, decays gently toward a personality baseline.
- **Attraction** — **romance-gated; adults only; spouse or romance-eligible only.** Never present for children/teens; never modifiable by inappropriate branches. Distinct from Warmth.
- **Tension** — recent friction/wariness; opens guarded, curt, or defensive branches; **decays over time** so a single bad conversation is not permanent unless it also sets a milestone (see §5a). Prevents the player from farming "make-up" content by first antagonizing.
- **Familiarity** — accumulated shared history; near-monotonic; drives callbacks, skipped exposition, and unlocks that should feel *earned by time*, not by grinding a single topic.

**Coupling rule (critical):** the vector *modulates access and voicing*; **hearts** remain the thing that visibly changes and the thing anti-farming must guard most tightly. Where a branch should also move hearts, that heart change follows the original prompt's consequence scale (§5) and its farming guards — the vector does not create a second, unguarded progression channel. Document precisely how vector state and hearts interact (e.g. high Trust *unlocks* a branch whose *heart* payoff is still one-time and capped).

**Persistence requirements** (extend the original §5 profile requirements): keyed by player UUID; bounded per axis with hard clamps; versioned with a migration path; tolerant of missing/malformed legacy data; pruned for stale/absent villagers; no per-tick processing; no unnecessary networking; server-authoritative only. Provide a compact, documented serialization and a unit-tested clamp/migrate/prune path.

**Baseline & drift:** each personality defines a resting baseline per axis, and axes drift toward baseline over time (except Familiarity and milestone-locked state). This prevents both permanent grudges from one slip and permanently banked goodwill from a single good day.

---

## §4b. Dialogue checks with success tiers (RPG skill-check analogue)

Gate the most rewarding lines behind **checks**, the way a CRPG gates a persuade/insight option — but derived from *relationship state*, not invented player stats.

**Check inputs (deterministic + bounded variety):**

- current disposition vector and hearts,
- personality fit for the chosen stance,
- current MCA mood and short-lived conversation state (grieving, elated, annoyed, …),
- relevant history flags (past choices, prior disclosures, honored/broken promises),
- a small **seeded** roll for variance (seeded from stable inputs so it is not save-scummable by re-opening; see §6a).

**Outcome tiers** (replace binary pass/fail):

- **Crit** — the villager opens further than usual; may unlock a milestone or a new branch.
- **Success** — the intended good outcome.
- **Partial** — the attempt half-lands; tone shifts, small or no heart change, sometimes a second chance.
- **Rebuff** — the attempt misfires *in character*; may raise Tension, but **must not** be farmable and must always have a graceful exit.

**Rules:**

- Every check **must** define behavior for every tier, including a safe fallback when optional context is disabled.
- A check gated on state the player cannot yet reach must degrade gracefully (the option is hidden or shown as a visibly different, lower-stakes stance) — never a dead button, never a trap.
- Checks express *character*, not a correct answer: the same stance can crit with one personality/mood and rebuff with another. Do not surface numeric thresholds or odds on buttons (consistent with original §9). Optional subtle tone hinting only if configurable.

---

## §4c. Villager interiority: wants, boundaries, secrets, arcs (NEW)

This is the core of "choices that matter." Give villagers persistent interiority so responses land *consistently* and *irreversibly*, not just variably.

Author, per villager archetype/personality (as data, not Java), a small interiority record:

- **Wants** — 1–3 persistent drives (to be respected, to be needed, to be left alone, to prove themselves, to be understood). Choices that serve a want build the relevant axis faster and unlock warmth; choices that trample it cost more than the raw number suggests.
- **Boundaries** — topics or approaches that, if pushed after a refusal, set a **durable** consequence (see §5a milestone tier). Boundaries are what make "press after a refusal" genuinely risky rather than a soft −2.
- **Secrets / revelations** — content that unlocks *only* at a threshold (e.g. Trust + Familiarity), fires **once**, sets a permanent flag, and is referenced thereafter. This is the payoff that makes long relationships feel deep.
- **Arcs** — for the deepest topics (grief, fear, regret, a strained family tie), a small ordered progression that advances across *separate conversations* over time, not within one sitting. Each stage gates the next; skipping is impossible; regression is possible if the player mishandles a stage.

Keep interiority *bounded and enumerated* so lint can validate it and callbacks can key off it. Do not free-form store narrative text that the system then can't reason about — store flags/enums rich enough to drive the callback and unlock lines authored in localization.

---

## §5a. Consequence taxonomy — make "matter" mean something (REPLACES the loose parts of §5)

Define five explicit consequence tiers and require the content to *use the upper tiers* on deep topics, not just the safe lowest one:

1. **Ephemeral** — tone/voicing only; no state change. (Fine for navigation and small talk color.)
2. **Session** — affects only the current conversation's remaining branches.
3. **Short-term memory** — a decaying flag / Tension bump lasting in-game days; referenced by near-future openers; then fades. (Farming-guarded.)
4. **Durable relationship shift** — a lasting vector change and/or a one-time, capped heart change following §5's scale and guards.
5. **Milestone / irreversible** — a permanent flag: a secret learned, a boundary crossed and scarred, a promise made, a branch mutually exclusive with another. **Gates future content. Generally cannot be undone.** Exactly the tier that makes choices feel weighty.

**Requirements:**

- Deep topics (life story, dreams, fears, regrets, secrets, spouse/family arcs) must carry at least one Tier-4 and, where narratively earned, Tier-5 stake.
- Provide **mutually exclusive branches** where appropriate: choosing to comfort vs. to challenge, to keep a confidence vs. to bring it up later, should *close off* the other path for that arc. Lint must confirm both paths are individually reachable and terminating, and that the exclusivity flag is set once.
- Milestone flags are unique, namespaced, player-scoped, one-shot, and enumerated for lint.
- Irreversibility is a *feature*, but every irreversible path must still be *escapable in the moment* (a graceful exit) and must never soft-lock the villager out of all future interaction — it changes the relationship, it doesn't brick it.

---

## §5b. Reactivity, callbacks, and relationship arcs across sessions (AMENDS §4/§5)

Choices matter most when the game *remembers out loud*. Require:

- **Content callbacks** — later conversations reference specific prior choices/disclosures by their enumerated flags ("You told me about your brother once…", "You didn't laugh, when everyone else did — I noticed"). Author these as localization keyed to the stored flags.
- **Evolving openers** — the category hub starter and the villager's opening line shift with the relationship arc and Familiarity: strangers get guarded/generic openings; deep relationships get intimate, history-aware ones; a recently mishandled relationship opens warier.
- **Shifting topic availability** — as Trust/Familiarity grow, new topics/branches appear and shallow exposition is skipped; if Tension is high or a boundary was scarred, some warm branches are temporarily or permanently withheld.
- **Arc continuity** — the §4c arcs visibly progress across sessions; the player can perceive "we're further along than last week."

All callback/arc content must have a generic fallback so a missing optional system never produces a dangling reference.

---

## §6a. Anti-farming under richer rewards (AMENDS §6 — treat as strengthened, not optional)

More reward surfaces = more exploit surface. In addition to everything in original §6:

- **Vector gains diminish and cap** per period; repeated identical stances yield progressively less; there is a per-day / per-topic ceiling on how far a single conversation can move any axis.
- **Milestones fire exactly once**, ever, and their payoff is one-time.
- **Negative content is equally unfarmable** — the player cannot rage-bait a villager to grind Tension/rebuff content, then reset. Rebuffs and Tension are rate-limited and decay; make-up content is not repeatable on demand.
- **Seeded rolls, not fresh RNG** — dialogue-check variance is seeded from stable state (villager UUID + player UUID + topic + arc stage + coarse time bucket) so re-opening a conversation does not re-roll a rebuff into a crit. Document the seeding precisely; unit-test that identical inputs yield identical outcomes.
- **No reward from navigation, back, re-open, or abandon** (as original §6), extended to cover vector *and* milestone state.
- **Idempotent consequence application** — each decision applies its consequence at most once even under packet duplication or rapid clicking.

---

## §9a. UI legibility for stance-based choices (AMENDS §9)

Within MCA's existing dialogue interface:

- The player must always be able to tell a **stance** (something the character *says*) from **navigation** (Back / Leave / "Something else"). Never label stances mechanically ("Persuade", "Flirt", "Good response") — write the actual words, as original §7/§9 require.
- A stance that is *gated* and currently unmet should either be hidden or shown as a *different, honest, lower-stakes* stance — never a greyed trap and never a visibly numbered lock.
- Success-tier outcomes must read naturally in the villager's reply; the player infers how it landed from the *dialogue*, not from a number.
- 3–5 stances must fit without clipping at small GUI scales; paginate long lines; ensure the conversation can never trap if a gated stance becomes unavailable mid-flow.

---

## §10a. Configuration additions (AMENDS §10)

Add, with defaults set to deliver the full intended RPG experience:

- master toggle for the disposition vector (off → fall back to hearts + LongTermMemory only, content still playable);
- toggle for dialogue-check success tiers (off → simplified success/fail);
- toggle for milestone/irreversible consequences (off → downgrade Tier-5 to Tier-4 durable, so nothing becomes unplayable);
- vector gain/decay rate multipliers and per-period caps;
- toggle for stronger negative/rebuff outcomes;
- global relationship-effect multiplier / reward cap (as original §10);
- debug logging for vector state, check inputs/tiers, seed derivation, and milestone firing.

When any RPG subsystem is disabled or its content is missing, degrade to the next-simpler documented behavior and never to a broken Chat.

---

## §11a. Validation and tests for the RPG systems (EXTENDS §11)

Add to `ContentLintTest` / dedicated graph & state tests:

- **Threshold reachability** — every branch gated on a disposition/hearts/flag threshold is reachable via some legitimate play path; no branch requires an impossible or contradictory state.
- **Check completeness** — every dialogue check defines all four tiers (or the configured subset) *and* a disabled-context fallback; no tier routes to a dead end or a missing localization key.
- **Milestone integrity** — milestone flags are unique, namespaced, player-scoped, one-shot; mutually exclusive branches are each independently reachable and terminating; exclusivity is set exactly once.
- **Irreversibility safety** — no irreversible path can soft-lock a villager out of *all* future interaction; every such path has an in-the-moment graceful exit.
- **Farming guards present** — every reward surface (heart, vector, milestone) carries its guard; seeded-roll determinism holds (same inputs → same tier).
- **Vector bounds** — clamp/migrate/prune logic keeps every axis within bounds across legacy/malformed input; versioning migrates cleanly.
- **Romance/age gating** — Attraction and any romantic branch are unreachable for children/teens and for non-eligible villagers, structurally, under all paths.
- **Callback safety** — every callback/arc line has a generic fallback; no enumerated-flag reference can dangle when optional systems are off.
- **Baseline drift** — axes provably drift toward personality baseline over time except Familiarity/milestone-locked state.

Add unit tests for: the vector store (clamp/migrate/prune/version), the check resolver (input assembly, seeding determinism, tier selection), milestone one-shot enforcement, farming-guard idempotency, and arc-stage progression/regression.

Add representative path simulations (extend original §11's list):

- confide attempt below Trust threshold → safe deflection, no rebuff-farm;
- confide attempt at threshold → crit → secret unlocked → milestone set once → referenced by a later opener;
- boundary pushed after refusal → durable scar → warmth branch withheld thereafter;
- antagonize → attempt make-up repeatedly → confirm make-up content is rate-limited and Tension decays rather than farms;
- re-open a rebuffed conversation → identical seeded outcome (no save-scum);
- disable vector subsystem → same topic still completes via hearts/LTM fallback.

---

## §12a. Production verification additions (EXTENDS §12)

Because MCA mixins don't behave in ForgeGradle's dev runtime (respect this — do **not** claim integration verified from `runClient`), the production checklist must additionally cover:

- vector state persisting across relog and server restart; migrating from a pre-1.0 save with no vector data;
- two players building *independent* disposition vectors with the same villager;
- milestone flags surviving reconnect and being referenced correctly afterward;
- an arc advancing across several separate sessions over multiple in-game days, including a mishandled-stage regression;
- seeded-roll determinism observed live (re-opening yields the same tier);
- age/romance gating holding for child, teen, adult, and spouse targets in a running instance;
- farming attempts (repeat stance, rage-bait, rapid click, packet dupe) yielding no excess hearts/vector/milestone gain;
- every config toggle's disabled-state fallback confirmed playable in a live world.

---

## §13a. Documentation additions (EXTENDS §13)

Beyond the original §13 deliverables, document:

- **The relationship model** — what each disposition axis means, how it's built/lost, how it decays to baseline, and precisely how it couples to hearts (with the "modulates access/voicing, hearts stay authoritative" rule stated plainly).
- **The check system** — inputs, tiers, seeding, and how authors gate a stance and author each tier's outcome.
- **Interiority authoring** — how to give a villager wants/boundaries/secrets/arcs *as data*, and how those map to consequence tiers and callbacks.
- **The consequence taxonomy** — the five tiers, when to use each, and the requirement that deep topics reach Tier 4–5.
- A worked **datapack example** extending the original's example to show: a gated stance with all outcome tiers, a milestone flag, a later opener that references it, a mutually-exclusive branch pair, and the disabled-subsystem fallback — all without editing Java.

---

## §8a. Personality × RPG-systems authoring guide (EXTENDS §8)

Extend the personality authoring guide so each of the 13 personalities specifies, *as guidance for content authors*:

- resting **baseline** per disposition axis;
- which **wants** dominate and which **boundaries** are sharpest;
- which stances tend to **crit** vs **rebuff** for them (as *tendencies*, never guarantees);
- how they voice each **success tier** (a proud personality's "partial" reads differently from a shy one's);
- how their arcs on deep topics differ in pacing and what regresses them.

Hold the original §8 guardrails: no personality is punished by all ordinary choices; "sensitive" ≠ fragile; "grumpy" ≠ hostile; "shy" can become direct at high Trust; negative traits still allow warmth and depth; positive personalities still have boundaries and bad moods. Personality modulates *probability and voicing*, never full determinism.

---

## Updated completion criteria (SUPERSEDES the original list for 1.0.0)

The rework is complete only when, in addition to the original criteria:

- a bounded, versioned, server-authoritative **disposition vector** modulates content without duplicating or replacing hearts;
- the deepest topics use **dialogue checks with success tiers** and carry **Tier-4/Tier-5 consequences**, including at least some **mutually exclusive** and **irreversible** branches;
- villagers have **wants, boundaries, secrets, and multi-session arcs** authored as data;
- later conversations **reference prior choices out loud** and openers **evolve with the relationship arc**;
- every reward surface (heart / vector / milestone) has a **farming guard and a lint rule**, and dialogue-check variance is **seeded and deterministic**;
- **age/romance gating** is structurally enforced on all paths;
- disabling any RPG subsystem degrades to a documented, still-playable fallback;
- all extended lint, unit tests, and the production build pass, with the RPG-specific production checklist enumerated for live verification.

At the end, additionally report: the disposition-model design as built (axes, coupling, decay); the check/seed design; the consequence-tier coverage per topic (which topics reach Tier 4/5); the farming-guard ↔ reward-surface mapping; and any RPG scope deliberately deferred past 1.0.0 with rationale.
