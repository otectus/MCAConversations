# Changelog

All notable changes to this project will be documented in this file. Format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/); versions follow SemVer.

Compatibility: Minecraft 1.20.1 · Forge 47.x · requires MCA Reborn `[7.6,8)`.
Built against MCA 7.7.0-beta.2; verified on 7.6.20. Architectury is no longer declared (MCA 7.6
asks for it itself; MCA 7.7 dropped it). Optional: MCA: Quests, MCA: Reputation,
Serene Seasons, Townstead `[0.7.5,0.8)`.

## [1.4.3] - August 30th, 2026

The conversation UI and input overhaul. The fixed MCA response popup could not hold the larger menus
already shipped by Conversations, and chat's five-choice cap made later valid answers invisible.
This release makes the exact server-owned offer readable and selectable across the base MCA screen,
typed chat, and Townstead's optional RPG screen without changing answer order or dialogue outcomes.

### Added

- A responsive base-MCA choice card with a distinct question header, left-aligned wrapped answers,
  content-sized full-row hitboxes, visible hover/keyboard focus, and an opaque-enough background for
  readability over the world. Menus page at nine visible responses and show a page indicator.
- Top-row and Numpad `1`–`9`, Up/Down, Home/End, Enter/Space, Page Up/Page Down, mouse, and wheel
  controls. The wheel is consumed while choices own focus, preventing MCA's old hotbar-slot change.
- Immediate selection locking so double-clicks and held keys cannot submit one offer twice.
- An empty-ChatScreen digit shortcut. It closes chat and selects without broadcasting the digit;
  non-empty input, modified digits, and in-world hotbar keys are never intercepted.
- Optional Townstead number badges and digit selection that delegate to Townstead's visible entry,
  submenu, Back, and native selection behavior. The adapter is `@Pseudo`, statically unlinked, and
  verified against the supported 0.7.6 jar; Townstead remains optional.
- Four client display controls: `numberedResponses`, `numericResponseShortcuts`,
  `chatNumericShortcuts`, and `showResponseControlHints`.
- English and Portuguese-Brazilian UI, control, expiry, hover, page, selection, and narration keys.

### Changed

- Chat quick replies are now a vertical block, retain translated components and MCA's offered order,
  and show all choices up to the protocol safety ceiling. The old five-response truncation is gone;
  strict typed multi-digit choices such as `10` work without treating sentences containing numbers
  as selections.
- The network protocol is now **2**. Multiplayer clients and servers must both use 1.4.3. Offers are
  immutable, monotonically revisioned, capped at 64 synchronized answer ids, and never truncated.
  The client selection packet contains only revision, absolute index, and optional villager UUID.
- Numeric selections are revalidated server-side for offer revision, one-shot consumption, index,
  expiry, frontend, villager identity, distance, active interaction, and current constraints before
  the existing MCA `selectAnswer` path runs. Ordinary mouse packets for Conversations-owned questions
  now consume that same offer revision and re-check constraints as well.
- The dual-root MCA Mixin probe now covers the base interaction screen's constructor, production SRG
  render/input/close methods, and dialogue fields. A separate optional probe covers Townstead's exact
  decorated UI members.

### Compatibility

- No MCA or Townstead class is a compile-time dependency. Both known MCA Forge package roots remain
  supported, the base UI falls back to MCA's original mouse screen if synchronization or a hook is
  unavailable, and offers above 64 choices keep the original UI rather than showing misleading
  partial numbering.

## [1.4.2] - August 28th, 2026

Two defects that made the mod quieter than it should have been on MCA 7.6.20. No content was added
and no behaviour changed for anyone who was not already losing something.

### Fixed

- **Eighteen authored affection grants were being discarded at every world load.** They declared
  `"budget": "generous"`, which is not one of the five `DepthClass` keys (`quick`, `standard`, `deep`,
  `relationship`, `service`), so `AffectionApply` threw at parse and `SafeParse` contained each one
  into a no-op — exactly as designed, but the design assumed the content was right. The affected
  beats are the delta-3 `once` payoffs at the end of nine reflective topics (dreams, fears, future,
  happy, hopes, memories, regrets, secret, worries) and nine profession arcs; every one of them now
  declares `deep`. `standard` would have been wrong: those payoffs sit alongside delta-2 `standard`
  beats that would clamp them inside a single session, and `relationship` carries age and romance
  gates that a work conversation must not inherit.

- **`VillagerEntityMCA#getInventory` was declared REQUIRED and does not exist on MCA 7.6.20.** That
  build has no accessor at all, only a `private final UpdatableInventory inventory` field, so every
  7.6.20 start logged an unresolved required member and the living-histories group ran without any
  reading of what a villager is carrying. It is now an optional pair — the 7.7 method first, the
  7.6.20 field second — matching how the `Trait#getId`/`Trait#id` drift is already handled. Two
  supporting changes make that possible: `bindGetter` now finds declared fields anywhere in the
  class hierarchy instead of only public ones, and the capability-group comment no longer claims
  that every member below it was verified identical across all three probed MCA builds.

## [1.4.1] - August 28th, 2026

The conversation overhaul. Villagers have always had plenty to say; the problem was that the game
kept letting you answer a sentence they never spoke. This release rebuilds the conversation around
one rule — every reply answers the line before it — and then expands what there is to talk about.

**This entry is the whole of the 1.4 line.** The coherence work and the living-histories layer built
on top of it were briefly separated into a 1.4.0 heading and an unreleased successor, which produced
a changelog that claimed both that the two were one release and that turning the second off
"reproduces 1.4.0 exactly". They are one release, and this is it. Nothing was dropped in the merge;
what follows is every change since 1.3.0, with the correctness fixes below applied on top.

**What that comes to.** 3,339 beat contracts and 3,025 reply contracts across 917 question nodes and
3,079 buttons; 12,961 lang keys in each of English and Portuguese; 37 profession profiles; 2,154 chat
intents. Forty-one topics carry contracted beats. No route in the corpus is uncontracted, every topic
meets the subject-family breadth its own depth class asks for, and all three debt ledgers are empty.
The full numbers ship with the release in `reports/coverage.md`, generated by the build.

**One target is short of the spec, and it is now measured properly.** Two specs state it and they do
not agree: the coherence spec asks in §9.3 and §15.5 for meaningful personality overlays on at least
25% of referenced say pools raw, and the living-histories spec raises that to 30% while keeping the
salience-weighted target at 90%. The later number governs, so the target this release is measured
against is **30% raw and 90% weighted**. It has **12.8% raw** (418 of 3,258) and **24.9%
salience-weighted**.

The raw percentage fell during the release while the overlays themselves did not: the corpus grew by
the living-history scenes and the new topic funnels, which ship with one say pool each and no
personality variants yet, so the denominator moved and the numerator stayed put. The weighted figure
is the more honest one and the harder one to move — it prices a signature line at eight farewells —
and its per-tier breakdown says exactly where the work is: the signature tier is at 100%, all 394
pools in all twenty-one namespaces, and the 2,319 substantive pools are at 0.9%. Both numbers have a
ratchet that fails the build if they fall, and the overlay ledger is empty rather than being used to
excuse the difference.

### Fixed — half of the generated work conversations could never be chosen

**Eighteen professions had dynamic work scenes and could not reach a single one of them.** The scene
index filed everything under `purpose/topic` and kept the first 128 entries of a bucket in id order.
That bound is a real one — it is what stops a datapack costing frame time — but the shipped corpus
files **256** scenes under `topic:work`, so exactly half of them were discarded before a single
eligibility check ran. The boundary fell inside the leatherworker's pack, mid-arc: `stubborn_hide`
kept its blocked state and lost its resolution. Everything alphabetically after it went with it —
shady wizard, scribe, mercenary, outlaw, librarian, mason, nitwit, unemployed, shepherd, toolsmith,
weaponsmith, miner, netherian, oceanographer, woodworker, priest, vampire expert, werewolf expert.
The librarian's damaged-volume arc that the README uses as its worked example was among the
unreachable.

The index now files a scene under `purpose/topic#profession`, once per profession it names, and a
lookup merges this villager's leaf with the profession-agnostic one. This is not a widened bound: a
scene naming a profession was *already* rejected outright for every other one, so the leaf returns
exactly what the old bucket returned after filtering — without the truncation. The largest leaf in
the shipped corpus now holds seven scenes rather than 256.

Three things make sure it stays fixed:

- **Truncation is no longer silent.** The catalog records every leaf that lost scenes and the first
  id it dropped, the loader logs it at error, and the reports print authored and indexed counts side
  by side. The old lint read the *truncated* buckets — the one piece of evidence a truncation
  destroys — and so could never have failed.
- **Reachability is asserted directly.** Every shipped scene must come back from a lookup for the
  leaf it is filed under, and every profession that owns scenes must be able to reach all of them.
- **The scored cap stopped being an editorial decision.** Candidates now reach the 32-candidate
  scoring bound in authored-priority order rather than alphabetically, so a scene can no longer be
  dropped for its name while a less important one survives.

### Fixed — weekly mention caps above one never fired

`max_mentions_per_7_days` was derived from the scene's last-seen day alone, so the count it produced
was 0 or 1 and nothing else. A cap is reached when the count is *at least* the cap, so a cap of 2 or
3 was unreachable: **134 of the 316 shipped scenes** had no weekly cap at all, and only their
separate cooldown held them back.

Each scene now carries a small ring of seven daily bins beside its last-seen day — two bits per day,
saturating at three, anchored on that day and shifted forward as days pass. Fourteen bits per scene;
the record stays the handful of small numbers it was. The window is exactly the seven day labels
`today-6 … today`, because "not more than seven days ago" spans eight. Saves written before this
release load unchanged: a stored last-seen day with no ring counts as the one mention it is evidence
of, and counting proceeds correctly from there. A server clock moved backwards under-counts for a few
days rather than locking a scene out of its own cap.

### Fixed — `fallback` was schema a pack author could rely on and runtime could not honour

The 316 shipped scenes make **219** `fallback` declarations, each naming the less specific scene to
degrade to when the preferred one cannot be told. The loader validated them and the director never
followed one: a scene that failed late binding simply dropped out, and the conversation fell all the
way back to the static route.

The director now walks the chain — nearest hop first, at most four hops, cycle-guarded, and re-gated
at every hop against the identical eligibility stack the preferred scene had to pass. A degrade is
never a way around a gate: if the general work scene is inside its own cooldown, it is no more
selectable than the specific one that led to it. Reached only after the preferred scene fails, so a
fallback never competes with the scene that declared it, and named in the selection trace as
`degraded to` so it is visible in `/conversations explain`.

Load-time validation is stricter to match, because the field is now load-bearing: a fallback must
exist, must not name itself, must not close a loop, and must share the purpose and topic of the scene
that names it. A fallback that changed the subject would answer a question about work with a line
about the weather and give the player no way to tell that is what happened.

### Added — the committed generated content is verified against its authoring sources

`build.gradle` has claimed since the content compiler landed that `ContentCompilerTest` regenerates
and re-asserts the committed generated files on every test run. No such test existed. The generated
corpus — scenes, episode/thread/commitment templates, beat and reply contracts, chat intents, both
lang files — could drift from `src/content` in either direction and nothing would notice.

It exists now, and it is non-mutating by construction: the whole output tree is copied to a scratch
directory, the compiler runs against the copy, and the copy is compared back byte for byte. Stale,
missing and left-over files are all reported by path. A failing check leaves the working tree exactly
as it found it; regenerating stays an explicit author command.

```
./gradlew verifyGeneratedConversationContent   # check only, also part of `check`
./gradlew generateConversationContent          # regenerate, for the author
```

### Added — continuous integration

The project had no CI. Everything above is checked by `./gradlew check` rather than by compiling —
unreachable scenes, replies that answer nothing, locale key parity, content drift — and none of it
ran anywhere but on a maintainer's machine. `.github/workflows/build.yml` now runs the whole suite on
JDK 17 for every push and pull request, builds the reobfuscated jar, and keeps the jar, the
conversation reports and (on failure) the test report as artifacts.

The suite is **807 tests**, up from 682.

### Fixed — documentation that described a different mod

Several claims had drifted from the code they describe, and a reader had no way to tell which was
right:

- the README said radius-local chat was **on** by default; `chatModeLocalChat` has defaulted to
  `false` since 0.8.1, which is what `CONFIG.md` and the code both say;
- the README listed two optional integrations. There are four: **MCA: Quests**, **MCA: Reputation**,
  **Townstead** `[0.7.5,0.8)` and **Serene Seasons**, all of them already in the metadata and config;
- the README counted **5,524** translated strings per locale. The shipped assets carry **36,255**
  key/value entries per locale across the 23 namespaces, of which 12,961 are the base `mca_dialogue`
  pool. Both numbers are now stated, so the metric is unambiguous;
- the README promised "one unprompted conversation per villager per day". Greetings are the whole of
  what a villager currently opens on its own; the budget and gate for fuller unsolicited
  conversations exist and are idle, and the copy now says so rather than describing 1.5.0;
- two specs disagree about the personality-overlay target — the coherence spec asks for 25% raw, the
  living-histories spec for 30%. The later number governs and is now named as the one this release is
  measured against;
- `DATAPACK.md` documents `fallback`, `max_mentions_per_7_days` and profession-based scene indexing
  as the load-bearing rules they became this release;
- `gradle/` held a byte-identical copy of `docs/BRANCHING-CONVERSATIONS-IMPLEMENTATION-PLAN.md` and a
  stale copy of `docs/PHASE-0-EVIDENCE.md`, both left behind by a move out of another repository.
  Removed; `docs/` is the one canonical location.

### Added — conversations now declare what they mean

**The old graph knew its own shape and nothing about its own sense.** It could prove a topic was
reachable, localized, balanced and inside its heart budget while a farmer said the weeds negotiate
hardest and the game offered "You don't sound like you enjoy it." That happened because MCA decides
which buttons to show from the *answer's* constraints alone — it never looks at which villager line
led there — so forty professions' opening lines all fed one page of five fixed replies, and no
condition on any result could have hidden a button that did not fit.

The fix is a layer the mod did not have: every villager line now declares what it means.

- **A beat** is a `say` pool and the `next` question it opens, treated as one authored unit. It
  declares its speech act, subject, polarity, how open the villager is to being asked more, the facts
  every one of its variants establishes, and which player stances make sense after it.
- **A reply** is one button, bound to its exact `question/answer`. It declares its stance, its tone,
  the facts its wording takes for granted, and the facts it introduces — so "I'll bring you some" can
  be refused on any route where nothing established a *some*.
- **An outcome** says how the villager took it. A rebuff, a hurt or a closed boundary may only open
  apology, clarification, respect or the door — never a page written for a villager enjoying the
  conversation.

All of it is datapack-loaded from `data/<namespace>/conversation_beats/*.json` and merged across
namespaces like the topic catalog, so a profession pack ships its meaning alongside its dialogue with
no Java patch. Malformed metadata logs and is skipped; a failed reload keeps the previous catalog.

- **`conversations_session` gained a `turn` op.** It names the beat a result is playing, so the live
  session knows what was just said and what it established. It grants nothing and speaks nothing.
- **The live session now carries semantic turn state** — current beat and subject, the villager's last
  act, how the last player line landed, the facts established so far, and a short ring of recent
  beats. Bounded and transient, as before; anything that must outlive the conversation is still
  written explicitly.

### Changed — "Tell me what would help" no longer follows "Get away from me"

**The `noticed` branch is the first funnel to be rebuilt.** Thirteen villager lines used to converge
on a single page whose four buttons were *Tell me what would help*, *I'll be around*, *Let's talk
about something else* and *I'll let you be*. Those buttons arrived after the villager shared good
news, after they told you your apology was not wanted, after they flatly declined to elaborate, and
after they told you to get away from them. Every one of those pairings is a non-sequitur, and no
condition on any result could have removed a button MCA had already decided to show.

It is now twelve continuation pages, one per meaning:

- an accepted apology, an invitation to explain yourself, and a rebuff that closes the subject;
- shared elation, and elation you just deflated;
- a steady week acknowledged, a small trouble admitted but not named, a "fine" defended, and the
  moment a villager finally says they are not fine;
- grief validated, grief met with quiet, and grief dismissed.

Each page offers only what its inbound line supports. After a rebuff you may apologise, ask what you
actually did, back off or leave — nothing else. After grief is dismissed there are three buttons and
none of them is an offer of help. "Tell me what would help" survives in exactly one place: the page
you reach by telling a villager, to their face, that they are not fine and you both know it.

Along the way, one variant of the brush-off used to end with "Good day." while its two siblings only
grumbled — a farewell in a pool whose other lines invited a reply. All three now close the subject,
so the page they open is right for whichever one a player reads.

**Chat mode moved with it.** The three intents bound to the old page are replaced by thirty-five
bound to the new ones, so every new button is reachable by typing as well as clicking.

### Changed — good standing no longer asks how to make amends

**Five different verdicts shared one page**, so every player who asked what the village thought of
them was offered *How could I make things right?* — including the ones nothing was wrong with. The
villager then had to answer "there's nothing hanging over you that words would fix", which is the
system apologising for a button it should not have shown.

Standing is now five conversations:

- **well thought of** — ask what they've heard, ask what keeps it that way, or deflect the
  compliment; the follow-up thanks them or promises to keep deserving it;
- **not yet placed** — ask what would give them a reason, ask what *they* make of you, or say that
  being nobody in particular suits you fine;
- **poorly thought of** — ask what they've heard, ask what would mend it, or say it isn't who you
  are;
- **an unresolved incident** — the only place the public apology lives, and the only place it can be
  offered, because it is the only branch where something is actually outstanding;
- **asked already today** — press, apologise for asking twice, or leave.

Every one of them names the reputation tier or the recent deed it is actually talking about, and the
"what have they heard" line is now written three times over, because good news and bad news do not
sound the same in a villager's mouth.

### Changed — a check-in now answers the state it found

**"How have you been?" had two follow-up pages between it and everything a villager could be.** The
good-news page received gratitude for a gift, ordinary contentment, a festival, open flirtation, a
villager alarmed at the blood on the player, and a villager whose good week the player had just
belittled — and offered all six *Whatever you're doing, keep at it*, *Careful, you'll ruin your
reputation*, and *Things have been alright with me too*.

Six new pages take the states that never belonged there:

- **the festival** — ask what they do for it, wish them the day, or admit you have been eating since
  dawn as well;
- **smitten** — take up the offer, return the warmth, or gently keep things to the weather;
- **the player is bleeding** — the villager wants you sat down, and the buttons are about your
  injury, not their week;
- **the good week you dented** — apologise, give it back to them, or explain what you meant; nothing
  else;
- **awake at the wrong hour** — a page about being unable to sleep rather than about a hard stretch
  generally;
- **the brush-off** — after "thank you for the vote of confidence", you may apologise, ask again
  properly, or back off.

The two pages that remain keep every button they had, because once the wrong lines stop arriving at
them the buttons are right — which is the whole point of splitting by meaning rather than by topic.

### Changed — every trade now has its own conversation

**This is the change the rest of the release was built to make possible.** Thirty-seven professions
shared one page of five replies. A farmer said the weeds negotiate hardest, an armorer said every
dent is a funeral that did not happen, a florist said a bouquet is somebody's apology — and all
three were answered with *You're good at what you do*, *Why does it matter to you?*, *You don't sound
like you enjoy it*, *You'd say that whether it was true or not*, and *I'll let you work*. Then all
eight possible reactions to those met one further page offering *Have you tried it the other way?*
and *It sounds like it's wearing you down*, whichever way the conversation had gone.

**Every one of the thirty-seven now opens a page written for it**, with a follow-up of its own:

- the questions are about the trade. The fisherman is asked what the heron actually takes; the
  armorer, what keeps him up; the librarian, what the worst thing that happens to a book is; the
  werewolf expert, what an unprepared month looks like.
- the compliment names what the village actually gets. *Nobody's died in armour you made.* *The
  children eat properly because of you.* *People live inside your work.*
- the challenge is one that trade would really hear. *It's sticks and feathers.* *Stacking rocks
  isn't a craft.* *It's killing, dressed up in tracking.* Whether it lands as respect or as an
  insult still turns on personality, as it did before.
- the follow-up asks the second question that only makes sense after the first: whether the dents
  tell the armorer anything, which book the librarian would save first, whether the woodworker can
  really name the tree.

Two pages remain shared, and only for the villagers they are actually about: one for a villager who
likes the work — which is also the coherent fallback for a profession from a mod this one has never
heard of — and one for a villager who does not, where "it sounds like it's wearing you down" finally
sits under a line about hating the job rather than under a line about loving it.

### Added — six things to ask every trade about

**One page per trade is not a conversation; it is a better first line.** Having given each of the
thirty-seven professions its own opening page, the obvious next question was what happens when a
player asks about the same trade twice — and the answer was the same page again.

Every trade now has six subjects instead of one:

- **the work in hand** — what is actually on the bench, in the pot, down the drift, on the shore
  today, and what would go wrong if it were left;
- **the craft** — who taught it, what took years, and what still cannot be written down;
- **the risk** — the part of the trade that costs something, which for most of them is not danger
  but a decision somebody has to make alone;
- **what the village gets** — the ledger the villager keeps privately and has never had read back
  to them;
- **where it goes** — the thing they would build, learn, hand on or walk away to, and what is in
  the way of it.

That is a hundred and eighty-five new pages on top of the thirty-seven identity pages, each with
three replies of its own — a question about the trade, an offer of hands or an acknowledgement, and
a second question that only makes sense once the first has been answered. Every one of them is
written for its trade: the shepherd is asked which of three counts they trust, the fletcher what is
wrong with a bent feather, the cartographer what a dotted line means, the miner how they know four
props are enough, the scribe how a first-hand account differs from a well-told second-hand one. The
villager who never had a trade and the one the village decided was simple get all six as well —
being unemployed is a subject, not an absence of one.

Every page carries beat contracts and reply contracts like the rest of the corpus, so the lint holds
them to the same rule: no button appears under a line it does not answer. All of it is reachable by
typing as well as clicking, and all of it is written in both locales.

### Added — professions are described, not just named

`data/<namespace>/profession_profiles/*.json` declares what a trade actually involves: its
archetype, the subjects it can be got talking about, its materials, risks, who in the village depends
on it, and which facts about it are worth remembering between conversations. All thirty-seven ship
with one, and the roster is derived from the work router itself, so a profession added without a
profile fails the build and a profile for a profession nothing routes to fails it too.

A trade from a mod this one has never seen gets a generic profile built from its display name —
enough to hold a conversation, and carrying no invented details about a craft nobody here knows.

### Changed — an optional mod's professions live in that mod's files

**Six of the thirty-seven trades come from mods this one has never required**, and until now
everything they said was mixed into the same three files as the farmer's. That is invisible at
runtime — the loaders merge a directory — and it is the difference between deleting a file and
picking a hundred entries out of a shared one when Vampirism renames a profession.

Each owning mod now has files of its own: `work_<owner>.json` and `terminal_work_<owner>.json` for
its beats and replies, `profession_<owner>.json` for its chat intents, and the profile file it
already had. The dialogue pages were always one file each, named after the trade. Between them, the
whole of what this mod says about More Villagers, Ars Nouveau, Chef's Delight, Ice and Fire,
Vampirism and Werewolves can be listed — and removed — without opening a shared file.

The new lint holds that in place and checks the thing underneath it: no profession-owning mod appears
in `mods.toml` at all, mandatory or optional; only Forge, Minecraft and MCA are ever mandatory; each
owner's files contain that owner's professions and nothing else; and every chat intent bound to an
optional trade's page is scoped to that page, so an absent mod cannot cost a player a wrong match on
a typed line. The roster is read from the shipped work router rather than kept by hand, so a seventh
optional mod is covered the day it is added.

### Added — personality is audible where it matters

**Sixteen personalities, and one narrator.** MCA rolls a personality onto every villager and this mod
has always shipped a lang overlay per personality — but the overlays stopped at the topic openers.
A villager said one sentence in their own voice and then handed the next six exchanges to a single
narrator. Confident and gloomy closed a subject against you in identical words; a crabby farmer and
a tender one described their trade with the same sentence.

Three things changed.

**Voice families.** Spec §9.1 is explicit that personalization composes in layers and that a separate
tree per combination is the wrong shape — sixteen paraphrases of one sentence is exactly what the
paraphrase lint exists to reject. What actually differs between a confident villager and a crabby one
telling you the thing they do not tell people is not the sentiment, it is the *approach*: whether
they state it flat, joke first, turn it outward, understate it, feel its weight, or take their time.
There are six of those in MCA's roster, not sixteen. `VoiceFamily` is the documented map from every
personality — 7.7 ids, the 7.6 spellings, and `athletic` — to one of them, and the build enforces
that the six families and the overlay roster are the same set.

**Signature beats.** §9.3 designates the lines where personality must be audible, and
`SignatureBeat` derives that set from the beat contracts instead of a hand-kept list: a beat is a
rebuff because its speech act is a rupture, not because somebody ticked a box. Five tiers — the line
that opens a trade and the line about how it was learned; refusals, hurt, boundaries and dismissals;
deep-topic disclosures; commitments; and callbacks. Two hundred and twenty-one pools, and a new one
authored next month arrives already designated.

**All of them are written.** Every signature pool now carries authored lines in all twenty-one
personality namespaces, at full variant count, in both locales — a little over twenty thousand lines,
authored once per family and expanded by the build. A gloomy shepherd and a crabby one no longer
close a subject the same way. Ask a librarian what they do and the answer depends on who is asking
and who is answering.

The lint holds it: a signature pool with no coverage fails the build unless it is named in
`src/test/resources/signature_overlay_debt.txt` with a reason, and a line named there fails once it
has been written — so the ledger can only shrink. It currently holds nothing. Overlay coverage across
the whole corpus went from 1% of say pools to 5.8%, and the floor may only be raised; §9.3's 25% is
the target the next releases are aimed at, "prioritizing salience over raw percentage" as the spec
asks.

Along the way, one personality overlay for the burnout reply had been written without a variant
suffix while its base pool has three variants, so MCA could never have drawn it. It is now a proper
three-variant pool in every namespace.

### Changed — which subject a villager opens with depends on who they are

Every trade has six subjects, and the router weighted all six equally: a gloomy villager was exactly
as likely to open on what the village gets out of their work as a friendly one, and a confident one
as likely to open on what it costs them. Six equal weights is not personalization; it is a coin toss
with six sides.

Each trade now carries a second router result per subject, conditioned on the voice family that
subject belongs to. The bright voice reaches for what is happening today, the quiet one for the
craft, the tender one for the risk, the warm one for who the work is for, the settled one for the
long view, and the plainspoken one simply says what the trade is.

**The unconditioned result stays.** That is the part the new lint cares about most: a preference is a
thumb on the scale, never a rail. A confident villager who can never once mention the risk is a worse
villager than one who mentions it slightly less often, and the build fails if a subject ever becomes
reachable only by the personalities that prefer it.

### Changed — "has this person earned it?" is asked by name, not by number

Forty results were gated on `hearts_max: 24`, `hearts_max: 59` and `hearts_min: 0`. Nobody reading
those could say what they meant, and nobody changing them could find every copy — which is exactly
the shape spec §9.4 asks the corpus to stop using.

`RelationshipBand` is now the single documented source of truth — stranger, acquaintance, friend,
confidant, partner, family, tense, hostile — and a new `conversations_relationship` condition lets
dialogue name one:

```json
{"conversations_relationship": {"at_least": "friend"}}
{"conversations_relationship": ["stranger", "tense", "hostile"]}
```

All forty gates were migrated, and a test walks every heart total from −100 to 120 to prove the
bands that replaced them cover exactly the same players — including negative totals, which is why
the two `hearts_max` forms name the ruptured bands explicitly. The `friend` threshold sits at sixty
rather than a rounder fifty because 24 and 59 were already the corpus's own boundaries: the bands
describe the content that is written rather than asking it to move.

The resolver puts a rupture above warmth and a marriage above a heart total — ninety-five hearts with
something unresolved between you is not a friendship, and a spouse is a spouse at five. It fails
soft: an unreadable relationship is a stranger, because a compat break must make villagers reticent
rather than make them confide in someone they have never met. The `family` band is in the vocabulary
and deliberately never returned, since MCA's parent/child relations are not among the members this
mod binds; the lint keeps shipped content from gating on it until that binding exists.

### Added — teenagers get their own news

Every topic told the three ages apart except one. A ten-year-old and a sixteen-year-old were routed
to the same news page and drew from the same gossip pool — a pool written in a child's voice, in
which a wedding is "there was cake, I had two bits".

Teenagers now have a branch of their own: eight gossip pools in a sixteen-year-old's voice, a page,
four replies and their chat intents. The difference is not vocabulary. The child reports that a baby
was born; the teenager reports that a baby was born and that nobody has asked how the mother is, and
that they will be the one minding it every second evening. Telling them straight is worth something
to them, and being told they are too young to repeat a thing lands as the rebuff it is — with
personality overlays in all twenty-one namespaces, because a rebuff is a signature beat.

A new lint holds the whole separation in place: within one topic, no two age groups may open the
same page or draw from the same pool.

### Added — the arcs are a promise, and both halves are now declared

Eight topics already came back days later: tell a villager a fear and they raise it again, and what
they say depends on what you did about it. That worked. It was never *declared* — the beat contract
has carried a `callback` field since the metadata layer landed and not one beat used it. So nothing
checked that the line which advances an arc and the line which resumes it are talking about the same
thing; the two halves could drift apart and every lint would still pass, because each half is
individually well-formed.

Ninety beats now declare the callback they make, read from the shipped results rather than a
hand-kept list, and a new lint joins the halves: a line that advances an arc must declare it, a
declaration must name lines that can actually consume it, and an arc must be startable and
resumable — with more than one way in and more than one way back, or it is a scripted sequel rather
than something the player did.

**It found a half-built feature immediately.** Twelve results advanced the `us` and `family` arcs —
every serious choice in the relationship and family topics — and neither arc had a single page that
read the progress back. You negotiated a future, or were told a worry and gave the villager space,
and they never raised it again. Both arcs now return: three pages each, with what the villager says
turning on whether you held to what you said, let it drift, remembered, or admit you forgot.

Because a callback is a signature beat, all ninety carry personality overlays in every namespace.
Overlay coverage is now 7.7% of say pools, up from 1% at the start of the release.

### Added — the plan a villager told you about now comes back

Every one of the thirty-seven professions would tell you, once, the single thing they wanted. The
armourer's suit that nobody ever needs. The priest's list that has never been written down. The
outlaw's trade at any wage, from anyone willing to be seen teaching him. And the next conversation
began as though you had never asked, because the work topic had no callbacks at all — the one thing
spec section 7.3 asks each profession for by name.

Encouraging that plan is now a promise the mod keeps. The profession hub opens a resume page when
the arc is set, and the line that opens it is that profession's own: thirty-seven of them, each about
the plan that profession actually voiced, in the three shapes a plan takes between conversations. It
moved, it did not, or it turned into something else. You can ask what the next step is, ask what
would move it, or say you have heard this before — and the last of those is answered honestly.

All thirty-seven of the lines that make the promise are written in every personality namespace, so
the ledger of unwritten signature overlays is still empty.

### Added — seven standard topics that never remembered anything

Section 8.1 asks every standard topic for at least three callbacks or state-sensitive revisits.
Seven had none: village, people, neighbour, rumours, standing, news and noticed. You could offer to
put a hand to a fault in the wall, take an errand quietly to somebody's door, agree not to spread a
thing that was probably untrue, or promise to look in on a villager who was hurt — and none of it
survived the conversation it happened in.

Each of the seven now has an arc, advanced by the two places in that topic where the player actually
undertakes something. The village asks when would suit. The neighbour tells you they mentioned
somebody had been, and didn't say who. The one who was hurt says you came back, or says plainly that
you did not. Rumours tells you whether the thing you asked to be kept has been kept — and, once,
that it got out and not through them, which is a thing they can't prove and you can choose to
believe.

Fourteen advancing lines and three disclosures, all written in every personality namespace.

### Added — the release now ships its own coverage report

Phase 9 asks for a content coverage report in the release artifacts, and a hand-maintained coverage
table is wrong within a week. This one is generated and asserted deterministic, so the interesting
question -- "what did this change do to it" -- is a diff.

`./gradlew build` now leaves four documents beside the jar in `build/libs/reports/`: the adjacency
report in English and Portuguese, the coverage table, and the (empty) list of uncontracted routes.
The coverage table counts contracts, every topic's depth against the target its own depth class
sets, personality overlay coverage, chat intents, profession profiles and locale key totals.

### Added — authoring documentation for everything the depth pass introduced

`DATAPACK.md` gained sections on what a beat's `subject` is for and the depth each class owes, the
wildcard reply contract the gossip pages need and why nothing else should use it, the two verbal-tic
caps and what they exclude, the generated reports, and the debug commands worth reaching for in a bug
report. The authoring checklist gained the three items the new lints enforce.

It also gained migration notes: heart numbers replaced by relationship bands with the exact
thresholds that preserve the old gates, why MCA's native personality condition must never be used,
where an optional mod's professions live, that beat subject ids were renamed, and that every reply
button now needs a chat intent.

### Added — every button can now be said out loud

Spec section 16 asks that every non-exit GUI answer be selectable through chat mode. Eighty-nine
were not. They were the pages the coherence migration left alone: you could press "What's keeping
you up?" at two in the morning, and typing it did nothing at all.

All eighty-nine have chat intents now, and every phrase they add is asserted in the matcher test, so
"selectable" is a fact and not a claim. A new lint keeps it that way in both directions — a reply
button with no intent fails the build, and an intent naming a button that no longer exists fails
too. Menus and ways out are exempt by the mod's own existing rule: chat jumps straight to a
question-and-answer pair, so the menu path is never walked, and "never mind" is the way out.

Two shipped intents needed a decisive word once the corpus grew by ninety entries — inverse document
frequency shifts under everything — and both were fixed by giving the in-context intent the word its
own phrase turns on, rather than by weakening a topic entry a player types cold.

### Fixed — twenty-nine reply buttons that had no contract

The gossip-driven pages — neighbour, the five news branches, and rumours — were the ones the
coherence migration could not reach, because their opener is not a `say` key at all: it is chosen by
event type at runtime, so there is no single inbound line to name. They now answer the wildcard,
which for a gossip page is the truth, and section 16's "100% of named response answers have reply
contracts" is met.

While contracting them, a quieter bug surfaced: a hundred and thirty-six results played a line whose
beat contract existed and never recorded it, because the result carried no beat id. Everything that
reads the session afterwards — facts, callbacks, the stored subject — saw nothing happen. They are
stamped now.

### Changed — the mod stopped saying "Aye"

Nine thousand lines written to one brief drift towards each other, and two habits had set in that no
paraphrase check can see, because each line on its own is perfectly good. "Aye" opened 3.7% of the
English corpus. "Right you are." stood fifty times, "I'll let you get on." thirty-seven, and between
eight sentences they were doing every goodbye in the mod.

They are thinned out, and two caps now hold the line: a word that is not an ordinary sentence opener
may begin at most one line in fifty, and no exact sentence may stand more than twelve times. Both
run over Portuguese as well as English, with a Portuguese list of ordinary openers rather than an
English one translated, and both exclude button labels — a label reading the same on every page is
an affordance, not a tic, and the lint asks the dialogue files which keys are labels rather than
guessing from the key's shape.

Four Portuguese lines had collapsed several distinct English goodbyes into one apiece; each minority
reading has its own words back.

### Added — the review artifact now reads in Portuguese too

Key and placeholder parity was already exact, and says nothing about whether the Portuguese
conversation *reads*: parity is satisfied by a key that exists. The adjacency report — every node,
every line that can open it, every button and every reaction — now renders through the Portuguese
lang file as well, to `build/reports/conversations/adjacency.pt_br.md`, and fails on the two things a
reviewer cannot work around: a pool with no Portuguese lines, and a button with no Portuguese
wording.

### Added — a broken datapack no longer takes the conversation with it

Beat contracts are keyed twice, by id and by the route they contract, and two packs that both claim
one route are a real conflict the catalog refuses to build. The loader has always kept the last good
catalog rather than emptying itself, and nothing proved it. Now something does — including the other
half, which matters just as much: a pack that removes every beat has removed every beat, and that is
a choice, not a failure.

### Changed — `origin` moved to Personal, and two topics stayed where they were

Section 14.1 files `origin`, `player` and `shared_history` under particular categories. `origin` has
moved to Personal to match, because where somebody is from is a fact about them rather than about
the place they live now.

`player` and `shared_history` have deliberately not moved to Relationships. That category is gated on
`family` in the shipped hub, so a topic placed there would be invisible to every villager who is not
related to you — which is the opposite of what both topics are for. "What do you make of me?" is a
question for anybody, and a shared history is precisely how a stranger stops being one. They stay
under Personal and Events, where the people who need them can reach them.

### Added — a coverage number that cannot be gamed

Section 16 sets two personality-overlay targets, and the plan says outright that only one of them can
be faked: "a raw percentage may not be met by overlaying terminal small-talk filler." A raw count
treats a farewell and a confession as one pool each, so a corpus could reach its number by writing
twenty-one voices for "see you later" and none for the line that matters.

Every say pool now carries a voice tier read off what its beat already declares, so a line cannot be
promoted by relabelling it: a **signature** line (the ones §9.3 designates) is priced at eight, a
**substantive** one — a line that leaves the subject open, which is the body of a conversation — at
four, and everything guarded, closing or terminal at one. Salience-weighted coverage is the ratio of
those weights, and it ships in `reports/coverage.md` beside the raw figure, with the per-tier
breakdown that shows where the gap actually is.

This release stands at **24.9% salience-weighted** against a 90% target, and the breakdown says
exactly why: the signature tier is at 100%, and the 2,319 substantive pools are at 0.9%. Both numbers
now have a ratchet that fails the build if they fall.

### Added — a typed claim can always be pointed back at a click

Storing what a player said about themselves is the one place this mod keeps a record whose origin is a
person rather than the world, and the entire safety argument for doing it is that the record can be
traced to a button. The machinery enforced that; nothing checked the content, and no content used it.

Both are fixed. Funnel replies can now carry a claim, the first two ship on the `player` topic, and
three lints hold the invariant for everything written after: a recorded claim names the button it is
attached to and no other, it carries a value the store can actually hold, and nothing waits on a claim
no button can record — a condition on a claim nothing writes is a branch that can never be taken.

### Added — the feature switches are checked, not just documented

Every switch in this mod is supposed to have an off state that reproduces the previous release
exactly, and that promise lives in the content: a generated route sinks itself when its feature is
disabled and the hand-written route underneath stays available. A route that forgot one of its sinks
would not produce an error — it would produce a villager answering a question nobody can turn off, and
only somebody playing with the layer disabled would ever find it.

All 286 generated routes are now checked to stand down for `branching` and `topics`, and every
preselected-scene route for `dynamic` as well. Separately, every button carrying a generated route is
checked to keep a route that survives the whole layer being switched off.

The sign turned out to matter more than the presence: `conversations_disabled` is true *while* a
feature is off, so a negative chance on it is a route removing itself from the off state and a
positive one is a route that exists *because* of it. The first version of this lint read both as
sinks and called every correct fallback a hole.

### Added — a dedicated server is not allowed to reach for a client class

A client class on a server path works perfectly in single-player and takes the mod down the first time
somebody runs a server. That is now a build failure: no source outside the two declared client
packages may import `net.minecraft.client`, no mixin in the common block may name a client type, and
every mixin source must be declared in exactly one of the two blocks — one declared in neither does
nothing at all, silently.

### Added — a hub that knows what this villager has going on

The menu was the same six categories for every villager in the world, whatever was happening to them.
It now surfaces up to three contextual entries above those categories, and each answers a different
question about the person in front of you:

1. **Where were we?** — the highest-priority thread the two of you are already in the middle of.
2. **What's on your mind?** — the villager's most salient live situation that you have *not* been
   told about.
3. **Can I ask about…** — the most salient situation you *have* been told about, offered again.

The privacy rule is structural rather than something content has to remember. A dynamic label may
never be more specific than a domain — your work, the village, your family, your day, or simply
"something" — so there is no wording available to it that could name a person or expose a confidence.
The middle entry is the strictest case: by construction the player has no way of knowing what it is,
so its label says nothing at all, and there is exactly one such entry rather than one per domain,
because five of them could be read for a hint. Fears, regrets and a secret all reach the player as the
same word.

Two further refusals: a situation recorded `speaker_only` is never advertised at all — a menu entry is
a standing offer and a confidence is not — and the same topic is never offered twice under two labels,
which is the menu that makes a player think the two buttons differ.

This ships in chat mode, where the mod renders its own text and can both label and route freely.
Saying one of the offers opens that topic's real conversation, exactly as pressing its button would;
anything that merely resembles an offer falls through to ordinary matching rather than being swallowed.
The GUI keeps its six fixed categories this release. `dynamicTopicSlots = 0` reproduces the fixed six-category hub
exactly, and that knob is now actually read — it had been in the config and consulted by nothing.

### Added — a second villager can join in, on five terms and no others

Group conversation is the feature every game of this kind gets wrong in the same way: a bystander who
may say anything ends up saying nothing in particular, and the result is a chorus of villagers
agreeing with each other. So this first slice is chat-only, off by default, and closed.

Five shapes exist and nothing else is allowed: corroborating or qualifying something public, differing
about a low-stakes preference, a coworker adding a detail only the trade would know, a family member
remembering the same event differently, and somebody saying "that isn't yours to tell."

Each shape names the footings an interjection may rest on, and that is what does the work:

- Corroboration needs having been there, having been told by somebody who was, or the thing having
  been announced. A villager with no connection to an event cannot confirm it.
- A trade detail needs the trade — compared exactly, not by archetype. A fisherman and a farmer are
  both outdoors, which is not the same as knowing which batch to use.
- A different memory needs the family tree, read from MCA rather than kept here.
- A boundary needs standing. Objecting on behalf of a thing you know nothing about is an
  interruption, not a boundary.
- Only friendly disagreement is open to anybody, because a preference needs no source — which is
  exactly why it is confined to things nothing turns on.

Every interjection also has to answer the beat that was just spoken. A line that answers some other
beat is a line that merely happened to be said next, which is the thing the plan rules out by name.

Three speakers including the lead, and the cap is not a tuning knob upward: a fourth line arrives
while the player is still reading the second. Turn order is fixed rather than simultaneous, and a
boundary goes first — hearing "that isn't yours to tell" after two people have already added detail
to the thing is worse than not hearing it at all. Nobody speaks twice, and while somebody is spending
a confidence nobody makes small talk over it.

Fifteen lines ship, three per shape, in both locales. Turn it on with `group.enabled`.

### Added — news that travels, and can be corrected

A thing one villager knows can now reach the villager standing next to them, and every rule about it
is a refusal rather than a feature.

The event id survives every hop. That is the whole point: a story that has been round the village
three times is still *the same event*, so a later correction addresses it rather than one villager's
copy of it. `conversations_episode` gained `{"op": "correct"}` for exactly that — it changes the
footing, not the event, and never the source, because being told the truth does not mean they were
there after all.

What a story loses on the way: one step of confidence per hop, fifteen salience per hop, and after
three hops it stops entirely. What it does not lose is its privacy — a confidence does not become
ordinary by being repeated — and what it never gains is detail. Propagation weakens an account; it
does not invent one.

- Anything the holder may not share does not move at all.
- Where a fact may be described but its people may not be named, the participants are **dropped from
  the copy**, not carried and hidden. A name kept in a record is one authoring mistake away from
  being spoken.
- What a player said about themselves never travels unless the player made it public. Permission has
  exactly one representation, and it is the privacy level itself: a flag beside it would allow the
  contradiction of a confidential fact that may nonetheless be repeated.

It runs on the existing low-frequency sweep, among villagers near an online player, and moves at most
four stories per pass across the whole server. The bound on *where* is not only about cost — a rumour
spreading through empty chunks would be a simulation nobody could have witnessed, and the first a
player heard of it would be a villager referring to something that had never happened in front of
anyone.

Provenance gained a hop counter for this, and the knowledge sources now carry two footings rather
than one: what a source gives by default, and the firmest it could honestly support. Collapsing those
into a single number meant either believing every rumour or never being able to be told the truth.

### Fixed — the daily limit on villagers starting conversations was never enforced

`maxInitiativesPerVillagerPlayerDay` has been in the config since the living-histories layer landed
and nothing read it. The anti-spam policy existed in three places that each knew part of it — the
director charged a score penalty for interrupting somebody working, the greeter checked mutes, the cap
was a number in a file — which is a policy with holes in it.

Every rule now lives in one gate that every initiative path asks, and the order of its checks is
itself part of the policy:

1. **A mute outranks everything.** A player who has said "stop talking" is not owed an exception for a
   villager who happens to have news. If the mute cannot be read, it is treated as present — silence
   is the only safe direction to fail in for a rule that exists to honour that request.
2. **What the villager is in the middle of comes next**, before any budget is spent, because an
   interruption that should not happen at all should not cost anybody their daily allowance. Somebody
   asleep, fighting, panicking, or in another player's interaction screen is unavailable for
   everything except an acute scene. Somebody grieving or part-way through a chore is unavailable for
   small talk and an opinion request, and can still be told a promise has come due.
3. **A fifteen-second real-time cooldown**, which the two purposes that bypass the daily cap still
   have to wait out — "may bypass the daily cap" must not mean "may happen twice in the same breath".
4. **Then the daily budget**, spent only by an initiative that opens a decision page. A villager
   calling hello across the square is a passing line, not a page, and does not use up the day's one
   chance to raise something that needs an answer.

Proactive greeting now goes through the gate rather than its own partial copy of these rules, so a
villager mid-chore or asleep no longer hails anybody. And when several things are outstanding at once,
exactly one is surfaced: the ranking is by how much interrupting somebody for it costs, which turns
out to be the same order the plan lists the initiative classes in.

### Added — villages that are somewhere in particular

Every village in the game was the same village with different buildings. There was nothing a resident
could refer to that a resident of the next settlement could not have said just as well.

A village now has six things, drawn once and kept: a tradition, a public value, a shared work worry,
a landmark, a festival, and an argument currently going on. The draw is seeded on the world seed and
the village id and nothing else — not the day, not the population, not who is asking — so two players
walking in from opposite directions find the same place, and a restart does not quietly rewrite what
the village believes about itself. Twenty-four tokens ship, four per family, in both locales.

The point is not the tokens; it is that residents do not all agree about them. A culture token names
the identity tokens whose holders are for it and whose holders have a reservation, so the festival
reaches each villager through who that villager already is. Somebody who values hospitality endorses
the frost supper. Somebody averse to crowds has something to say about it. Most residents ignore it,
which is the honest majority position and not a failure state — and a villager with no identity
profile holds no view at all rather than being given one.

- A village is all six families or none. A partial culture would hand every scene binding a landmark
  a hole to handle.
- On a merge the surviving village keeps its own culture and records that it took the other one in,
  so the absorbed id keeps resolving for residents who came across. Blending the two would leave
  everybody living somewhere that had never existed.
- A wanderer has no culture, and that reads as *unknown* rather than as an empty set. An empty set
  would say "this village believes nothing", which is never true.
- Renaming a token without stranding the villages that drew it is what the alias table is for.

Datapacks gained `data/<namespace>/village_culture/` and the `conversations_culture` condition, which
asks either what the village keeps or what this resident makes of it — or both at once, so a page
cannot fire for a villager who questions a festival their village does not actually hold.

Townstead installs get six more, in their own file and each declaring the mod that owns it: an
apprentice year, a rule that nobody here grows old alone, a worry that the trades are being learned
less well than they were, the first house, the naming day, and an argument about whether the town
should keep growing. They are things only Townstead makes true, and on a plain install the catalog
filters them out before a village can draw one.

### Added — who people are to each other, beyond the family tree

MCA already knows who is whose parent, child, sibling or partner, and this mod does not keep a second
copy of that — a duplicate family graph is a graph that goes wrong. What nothing recorded was the
rest of a village: who works beside whom, who taught whom their trade, whose work depends on whose,
who is being looked after, and who somebody takes the long way round to avoid.

Eleven observed roles now exist, each with a cause and a lifetime that belongs to the kind of
relationship rather than to whichever code path noticed it. A mentorship, a supply dependency and
somebody being cared for last until they are explicitly withdrawn. Having been at the same fire lasts
three weeks. The same argument twice lasts a month, so a bad afternoon does not become a permanent
feud.

- A role with no cause is refused at the door, exactly as an opinion is. "I avoid him" with nothing
  behind it is the drama the plan rules out by name.
- Seeing a role again refreshes when it lapses and keeps the day it began, which is what lets a line
  say "for years now" and be right. `conversations_role` with `min_days` is the condition that
  checks it.
- Roles are directional, and the mirror is never created automatically. That a smith counts the
  farmer a supplier is not evidence the farmer counts the smith a customer.
- A role that never expires can still be withdrawn, or a villager introduces somebody as their
  apprentice a decade after they left.
- When the per-villager cap is reached, the edge given up is the one with least to say: an expiring
  role before a structural one. A mentorship is not evicted to make room for a shared bonfire.

Datapacks gained the `conversations_role` condition and action. Both share the existing social-edge
cap and the `socialOpinionsEnabled` switch, because they are one layer to a server owner: how much a
village remembers about itself.

### Added — a villager now knows *how* they know

An episode used to carry a free-text `source` next to a privacy level and a confidence, and nothing
tied the three together. That allowed a record which said a villager was *certain* about a thing they
had heard as an *unknown rumour* — specificity with no source behind it, which is the exact failure
the plan rules out.

The four fields section 16.3 asks for are now one bundle with its invariants enforced where the
record is built:

- **Knowledge source** — witnessed, participant, family, coworker, told by a named person, public
  notice, or unattributed rumour. Confidence is clamped to the firmest footing the source can bear,
  so a rumour cannot be spoken as an observation however a datapack marked it.
- **Share permission** — may name, may describe anonymously, may not share. It may be narrower than
  the privacy level implies and never wider, so a confidence cannot be laundered by relabelling what
  may be done with it. It governs passing a thing on; it never stops the person a thing happened to
  from speaking about their own life.
- **Distortion** — authored only, and the runtime never sets it. Propagation costs confidence and
  adds hedging; it does not invent detail. That is what lets two villagers read the same public event
  differently without either contradicting the log about what actually happened.

`told_by` with nobody named degrades to an unattributed rumour rather than being kept as a chain the
villager could not produce, and a teller attached to a first-hand source is dropped rather than
carried as decoration. Retelling an episode preserves its event id, so a later correction can address
the same event rather than a copy of it.

Datapacks gained `knowledge`, `share` and `distortion` on episode templates. Saves from earlier
releases are read as they were written: the three loose fields become a well-formed bundle with
permission derived from the privacy level already on the record.

### Added — seven new things to ask a villager about

The catalog had twenty-eight topics and no way to ask a villager what she enjoys, what she will not
do, where she came from, what she makes of you, how her days actually run, which corner of the
village she is fond of, or what the two of you have been through. Section 8 names all seven. They now
exist, in both locales, and each one is built the same way the shipped topics are rather than as a
single line with three replies bolted to it.

- **interests** — what she does that is hers, and what it would take to be shown it.
- **values** — the rule she holds to and where she got it, plus what holding to it cost her this
  season and the one she was raised on and has since put down.
- **player** — the only topic where you are the subject. Her first reading of you, her current one,
  the afternoon that changed it, and what she says about you when you are not there. Asking for the
  unflattering half is a real button and she answers it.
- **routine** — the shape of a day and the quarter of an hour in it she will move anything to
  protect, with the two ends nobody sees: the first hour, and the hour after the work is done.
- **origin** — where she is from, what she carried here, the season that still smells like the old
  place, and whether this one has become home yet. She has an answer to that and she is not sure it
  is the right one.
- **place** — the corner of the village she would defend in an argument, and the doorway she has
  walked around rather than through for four years.
- **shared_history** — the record she keeps of the two of you, the entry you have certainly
  forgotten, the thing neither of you mentions, and the date nobody marks. It is adult-only and both
  scenes want time on the clock, because there has to be a history before there is a topic.

Each one ships as three tiers on a single button, and only ever one of them is live, so the
consequence of pressing it is never a lottery. A context-gated **scene** wins when its conditions
hold — the hour, the season, how long you have known each other, how close you are. Below that a
two-page **funnel** opens the subject and then goes one step further into it, with a brush-off on
each page that ends the conversation rather than pretending the subject is still open. Below that,
for anyone playing with branching switched off, the plain legacy line, so nothing about switching the
feature off costs a player an answer.

### Added — twenty-nine subjects the topics were missing, and the ledger is empty

The depth lint arrived with fourteen topics on its ledger. It is now empty: every topic in the
catalog meets the subject-family breadth its own depth class asks for.

Part of that was correcting metadata rather than writing content. Whole topics carried a single
blanket subject — `village.home` covered praise, criticism, a named fault, an offer of help and an
insult alike — so the lint was measuring a label instead of the conversation. Naming each beat after
what the villager is actually talking about closed `news`, `people` and `village` on its own, and it
means the trace exporter and the session's stored subject now say something true.

The rest is new:

- **standing** gained the three reputation states section 8 names and the topic did not have. There
  is no tally in most villages, and now saying so opens a conversation instead of ending one: the
  villager will tell you how *they* read you, who would know if anyone, and what they think of being
  measured at all. Above that, being *spoken well of* — by a particular person, out of your hearing —
  is its own branch, because it is not the same as a good score. And a village that is genuinely
  split about you is not a village with no opinion: that has a branch too, where you can ask which
  half the speaker is in and what the other half is still holding on to.
- **noticed** gained six. Section 8 lists grief, joy, anger, guardedness, injury, pregnancy, pride,
  fear and ordinary wellbeing; four existed. A villager on four hearts said they were fine, because
  "fine" was the fallback and nothing looked at their health. So did one part-way through an
  infection, one who was pregnant, one who had just watched you finish something difficult for them
  — the mod already records that as pride and nothing read it — and one who was simply worn through.
  Guardedness was the largest gap: a villager who barely knows you got the same warm page as your
  spouse. It is now a disclosure level with a real cost to pressing.
- **neighbour** and **rumors** each gained a third level. Both ran two decisions deep and stopped.
  You can now ask after the person rather than the story — their family, whether the two of them get
  on, whether they need anything — and whether the villager should be telling you any of it, which
  is the disclosure-posture check section 8 asks for, made into a button rather than a rule the
  content has to remember. A rumour can be questioned in the same way: is it serious, do you believe
  it, is any of it about me, and is this old news everyone but me already has.
- The seven **relationship topics** gained twelve between them, each one a thing section 8 names by
  hand and none of them had: a child's pride and a child's question about a family event; a promise
  you made and have not kept; what they made of you at the start and the version of that afternoon
  you remember differently; where the two of you would actually live; what they are grateful for and
  the thing they have not said; whether they want to be listened to rather than helped, and whether
  what they need is simply to know something.

### Added — every topic is now held to the depth it claims

The graph lint already counted *decisions* along a branch. That is the easy half of spec §8.1. The
other half is breadth: a standard topic needs semantic beat families rather than one subject in four
costumes, a deep topic needs several disclosure levels, and nothing was counting either.

A new lint measures every catalog topic against the floors its own depth class declares — subject
families, disclosure levels, and whether a deep topic remembers anything between conversations — and
`src/test/resources/topic_depth_debt.txt` records, per topic, exactly how many subject families each
one is still short by. The ledger is a ceiling: a topic that falls further behind fails the build,
and a topic that reaches its target and is still listed fails too, so it can only shrink. Fourteen
topics are on it today, and the number beside each is now a fact rather than an impression.

### Added — a rumour can be private

Spec §8 asks the rumours topic to carry a privacy level and to "distinguish harmless chatter from
death, divorce, crime, or a private secret". It did not: a death and a wedding arrived on the same
page with the same three buttons, and every line in the topic was equally open. There was no version
of the conversation in which a villager declined to pass something on.

A death or a divorce — or any news at all, told to somebody who has not yet earned the full version
— now opens a discreet branch instead. The villager gives the fact and no more; you can ask what
kind of thing it is, say you will not ask further, or press. Pressing does not yield the story: it
closes the subject, because that is what a boundary is. Ask after the people rather than the story
and you get the one answer nobody else has asked for this week.

That gives the topic the guarded and closed-subject levels it had never had, and a seventh set of
gossip lines written in a discreet register, covering every event type the gossip system can report.

### Changed — four more funnels, split by meaning

**Food.** Seven lines shared one page, and the first button on it was *I'll bring you some* — offered
after "Must everything be a debate?", after a recipe had been explained, and after nothing in
particular had been named. That last one was not a routing fault but a pool fault: one variant of
the opener answered with a dish and one answered with a shrug, so no reply could point at anything.
Both openers now name something, and the page is four: a preference shared, a method explained, an
argument welcomed, and an argument that was not. Mocking a villager's dietary condition also stops
leading to *There's something I have too* — it leads to a page with two buttons, one of which is an
apology.

**Neighbours.** A villager who had just said *I love them. All of them. ESPECIALLY the loud ones*
was offered "They're not so bad, most of them". There are now three openers — fond, mixed and
sour — and four continuations: shared grievance, an invitation to gossip on terms, a villager who
took the correction, and a villager who told you that you have lived here five minutes. "Go on —
who's the worst?" survives only on the gossip page, where it is met with the refusal it deserves.

**The village.** *Tell me and I'll help fix it* was offered after the villager listed what needs
mending, and equally after they said nothing springs to mind, after they said they would not swap
the place, and after the player called their home a miserable little place. Four pages now: a named
fault, a settled year, a compliment accepted, and an insult that gets an apology page and nothing
else.

**News.** Eleven lines shared four buttons written for a confidence. Good news, a death, an
ambiguous departure, a rebuke for souring somebody's good week and a rebuke for laughing at a
funeral all arrived at *I'll not repeat any of it* and *Everyone should hear about this*. Now: good
news you can pass on gladly, news you soured, news nobody knows the half of — the only page where
spreading it is still an option, and still a mistake — a death, where the buttons are silence, a
question about the villager rather than the story, and an offer to the family, and one page for
having laughed, which offers an apology and the door.

### Changed — a rebuke no longer opens the page for a confidence

The deep topics each had one outcome where the villager tells the player they have got it wrong, and
all of them then opened the page written for a disclosure that had landed well:

- **Life.** "I told you that in confidence, not for marking" was followed by *Tell me more about
  that part*.
- **Dreams.** "I didn't need it measured out" was followed by *I'll help you get there*.
- **Hopes.** "Too much. Right. I'll keep the next one to myself" was followed by *What would the
  first step be?*
- **Regrets.** "You want the detail more than the person" was followed by *I'm still here*.

Each now has a page with three buttons: an apology, a clarification, and leaving.

The same thing was true of the shared close nodes, which greeted every arrival with *Thank you for
telling me* and *That mattered, what you said*. Right for almost everything that reached them — and
wrong, every time, for the partner who has just heard "never" about a future together, the friend
told they brought their own feelings up, the parent told their child needs a firmer hand, and the
villager whose secret was called nothing much. Four repair closes now take those, and only those.

### Changed — every villager line in the mod now declares what it means

The migration ledger began at **985 routes** — every authored `say`+`next` pair in the corpus, none
of them carrying any statement of what the line meant, so nothing could be checked. **It is now
empty.** All 1,520 speaking routes are contracted, and all 1,046 buttons declare their stance.

Getting there was three sweeps, in increasing order of care:

- **Terminal lines** (428 of them) — the villager's last word before the menu. These open no reply
  page, so their contract is short and completely determinate; the act comes from the answer they
  react to and the polarity from the consequence the result actually applies.
- **Interior pages** (329 lines, 504 buttons) — the ones with a reply page after them. Two tables do
  the work, and neither is derived from the content it checks: one says what each button *is*, read
  off its wording; the other says what each kind of villager line leaves room for, read off its
  speech act. Because the second knows nothing about which buttons a page happens to offer, the
  comparison is a real check rather than a restatement.
- **What the check then found.** Eight more pages where a rupture shared a page with lines that had
  gone well — the same defect as the ones already fixed by hand, found systematically instead of by
  reading. A villager who has just said "I'll stop asking, then", "Mine are theirs", "Sorry for
  having an hour that didn't hurt" or "I'll not mention it again" now gets a page with an apology on
  it rather than the buttons written for a conversation that was going fine.

Two rules were corrected in the process, both because the lint was stricter than the design:

- Every decision node is *supposed* to offer a blunt or boundary-testing option, and the villager's
  rebuff is the honest consequence of taking it. The first draft forbade those, which made the lint
  disagree with §10.4. What is forbidden now is only what the spec forbids: warmth after a rupture,
  sympathy after a celebration, humour on an acute line, and flirtation anywhere it has not been
  authored.
- Spec §5.5 bans a page opened by both an invitation and a closed subject *without partitioning its
  answers*. The partition that matters turned out not to be structural: a page offering only "press
  anyway", "back off" and "leave" is right after a partial answer and right after a refusal, and
  splitting it would have produced two identical pages. The rule now asks whether the closing line
  leaves room for every button, which is the question §5.5 was actually asking.

### Added — the whole conversation, as one readable document

`build/reports/conversations/adjacency.md` is generated by the build. Every question node appears
once, with every line that can open it, every button it offers, every reaction, and the page each
reaction leads to. It is deterministic so it can be diffed: the question "what did this change do to
the conversation" now has an answer that does not involve opening 173 JSON files beside a 400 KB
language file.

The report is also where semantic fan-in becomes visible. It counts, per node, how many *different
meanings* can arrive there — which is how the work follow-up's eight incompatible inbound lines stop
being a thing you have to notice by playing.

### Added — lint that fails the build on a non-sequitur

`BeatContractLintTest` refuses content that cannot be checked, and content that can be checked and is
wrong: a button whose stance the inbound line forbids, a button presupposing a fact no inbound line
establishes, a page opened both by a line inviting more and a line closing the subject, a contracted
page with no way out, and a boundary the graph walks straight through.

Migration is incremental and honest about it. `src/test/resources/legacy_unverified_routes.txt`
lists the 985 routes that predate this system and have no declared meaning yet. **That ledger may
only shrink** — a new uncontracted route fails the build outright, and a listed route that has since
been contracted or deleted also fails, so the number cannot quietly stop meaning anything.

### Added — villagers who accumulate a life

The rest of this release makes every reply answer the line before it. This half answers the question that
leaves behind: two farmers with the same personality still had much the same conversational life.
They chose different variants, but neither had accumulated a specific working week, a stable
preference, a remembered disagreement, or a reason to raise one subject instead of another.

Five layers fix that, and the content that proves them runs across every trade in the mod. It adds
capability as well as corpus: with `dynamic.enabled = false` the mod selects exactly what it selected
before, and the hand-written conversation underneath it is untouched.

**The rule the whole layer serves:** variation must come from who this villager is, what has
happened, what is happening now, and what the two speakers remember — not from a larger random
synonym bag.

### Added — the world, read once

Before this release, each dialogue condition queried MCA for itself. Within a single player click one
condition could see the villager at their workplace and the next could see them halfway home, and a
line could be selected on facts that were no longer true by the time it was spoken.

- **`ConversationContextSnapshot`** captures the world once when a topic opens and hands the same
  frozen answers to every selector, condition, template and trace. Pinned fields never change for the
  life of a scene; a small marked set of volatile ones — the hour, the weather, what the player is
  holding — refresh at a turn boundary.
- **Every field carries *why* it has the value it has.** `KNOWN`, `UNKNOWN` and `UNAVAILABLE` are
  three different answers, because "she is not pregnant" and "nothing here can tell me whether she is
  pregnant" are different facts and only one of them is safe to speak. Every optional read must
  declare what an unknown answer means: `fail`, `neutral`, `fallback` or `error`.
- **Providers are isolated adapters.** One field has exactly one owner, enforced at build time; a
  provider that throws costs its own fields and reports a capability failure, never the conversation.

### Added — thirteen new reads from MCA

MCA has always had these answers and this mod never asked for most of them. Profession was inferred
from a *translated display string*, which could tell a farmer from a librarian and could never notice
a profession change.

`getProfessionId`, the assigned chore, panic and grief state, workplace and home positions, MCA
traits, coarse inventory tags, the family tree's parents, siblings, children, partner, deceased flag
and profession, village population, and the building type at a position. All verified present and
identically named across 7.6.20, 7.7.0-beta.2 and 7.7.1-alpha.2, and all reached by name at runtime —
no MCA type entered the codebase.

`Traits$Trait` was the one genuine drift: 7.6 has `id()` returning a `String`, 7.7 has `getId()`
returning a `ResourceLocation`. Both are declared optional and tried in order, so one jar reads traits
correctly on every supported build.

### Added — villagers who are somebody

- **A stable profile per villager**: two interests, two values, a comfort, an aversion, and a work,
  social and disclosure style, drawn from a datapack catalog of 58 tokens.
- **Generated once, from the world seed and the villager's UUID only.** Not the day, not their
  position, not their name, and above all not the player asking — two players meet the same person.
  Restarting, renaming, relocating, rebalancing the weights, or a different player talking to them all
  leave it untouched.
- **Anchors are identifiers, never prose.** The save file contains no English, so a translation change
  cannot rewrite anybody's personality.
- **A profile can never infer a sensitive identity from a job or a mood.** A cleric is not
  automatically devout in a particular way, an outlaw is not automatically cruel, a "sensitive"
  villager is not fragile, and a nitwit is not incompetent. Bans live on the tokens as data and are
  asserted over the shipped catalog rather than left as an editorial note.
- Renaming a token uses an alias table, which rewrites existing profiles forward rather than rerolling
  anybody.

Forty same-profession, same-personality villagers produce at least twenty distinct profiles; across
ten thousand seeds no ordinary token takes more than 45% of its family; every shipped token is
reachable. All three are tests, not claims.

### Added — situations that change, promises that can be checked

A new store beside the existing ones — `data/mcaconversations_history.dat`, deliberately its own file
so a schema change to one cannot risk the other.

- **Episodes**: a concrete situation with a lifecycle. `planned → active ⇄ blocked → succeeded /
  failed / abandoned → remembered`. A resolved project can never become unresolved again: the machine
  refuses it at runtime and a template that tries to declare it is refused at load. This is what makes
  "is it still stuck?" answerable and "the west frame finally held" safe to say.
- **Threads**: what one villager and one player are in the middle of, and what each is waiting for.
  `waiting_on_world` and `waiting_on_player` are separate states, because "the book dried, mostly" and
  "you said you would bring something" are different things to say.
- **Promises the game can actually observe.** A commitment must name a registered resolver, and a
  judging resolver must name a target. If nothing in the running game can watch it, the button has to
  be worded as willingness instead — or declared `manual_neutral`, which is remembered as something
  that was said and never judged kept or broken. A promise made under an optional mod that is later
  removed becomes `unobservable`, never `broken`.
- **Player claims with provenance.** Only an authored reply can create one, and a later contradiction
  keeps both values and opens a clarification rather than silently overwriting the past or accusing
  anybody of lying. Free-form typed text may *select* a claim; it may never *become* one.
- **Social opinions that are directional and caused.** An edge needs a family tie, shared work, an
  observed event or an authored consequence — never a resident-by-resident product, and never an
  unexplained dislike.
- **Bounded and pruned deterministically.** Every collection has a configured cap and a hard ceiling
  the store enforces regardless, so a mis-set property can make the mod remember less but never make a
  save grow without bound. Over the live-episode cap the least salient is *abandoned* — a state a
  scene can honestly speak from — rather than silently deleted. An open promise, an unrepaired rupture
  and a thread somebody is waiting on are never pruned. A worst case of 200 villagers with 20 active
  player pairs each and every collection full is asserted against a byte budget, not merely logged.

### Added — a director that can explain itself

- Candidate pipeline in the plan's order: index lookup, hard eligibility, slot binding, continuity
  priority, transparent scoring, four-level repetition suppression, deterministic choice, freeze.
- **Hard gates are gates, never large negative numbers.** A candidate that fails one does not reach
  scoring at all, so no bonus anywhere can put an ineligible scene on screen.
- **Repetition is tracked at four levels** — exact scene, subject, *rhetorical shape*, and topic. The
  shape level is the one that matters at scale: two scenes can share no ids at all and still be the
  same conversation with different nouns.
- **Reroll resistance.** The seed comes from the world, the villager, the player, the day, the purpose
  and the fingerprint of the eligible set — never a clock or a random source. Closing and reopening the
  screen, switching between the GUI and chat, or changing language all reuse the frozen plan. A player
  who does not like the subject cannot shop for a different one.
- **Every decision is explainable.** `/conversations scene plan` prints the candidate counts, every
  non-zero score term for the finalists, each rejected candidate with the *first decisive* reason, the
  bound slots and where each value came from, and the seed.

### Added — semantic contracts v2

Optional fields on beats and replies. Everything authored before this release behaves exactly as it did.

A beat may declare its predicate, tense, epistemic footing, privacy, the obligations it makes
relevant, the referents it introduces, the episode states in which it tells the truth, and its
rhetorical shape. A reply may declare which obligation it fulfils, which referents its wording
presupposes, the claim it records, the promise it makes, and its epistemic, privacy and temporal moves.

That lets the build catch four things v1 could not see:

1. a non-exit reply that neither fulfils an obligation nor performs a declared topic move;
2. a page whose villager asks a direct question and whose buttons all talk about something else;
3. a reply presupposing a referent that some inbound line never introduced;
4. a tense that contradicts the episode state it plays in — no "still" on a finished thing.

A `reported` or `rumoured` frame must name a source, so a rumour cannot be spoken as an observation.

### Added — a living situation for every trade in the mod

Thirty-seven profession packs, one for each trade the mod knows, written from the six subjects §12.4
gives that trade. Each carries three situations with a state that changes: a farmer's failing crop, a
guard's weak point in the wall, a mason's rushed foundation, a scribe's error running through four
generations of copies. A librarian has a specific damaged book — hers, chosen from authored pools by a
seed made of the world and her UUID, and the same book until it is resolved. Two librarians in one village are worrying about two different volumes while sharing one
authored page.

Every situation plays across its own lifecycle — blocked, active, succeeded, failed, remembered — with
a remind-me route for when you have been away, and an evergreen fallback that claims nothing about any
object and is therefore true whatever the world turns out to be.

Every exchange has a real decision in it. You can ask what pressing the pages would cost, offer to
bring wool — a promise the existing gift path can actually observe — advise her to save the ink, or
disagree with her outright, which is content rather than a wasted turn. Thirty-five of the situations
carry a promise with a registered resolver behind it, so an offer to fetch iron, feathers, timber,
paper or bread is a thing the world can later check you did.

**She does not always agree with you.** Whether she takes your advice or argues the point depends on
her stable values, and the disagreement names the proposition rather than being generic. Conceding
after an explanation and conceding after a disagreement are two different pages, because arriving with
an offer in the middle of an argument changes the subject rather than answering it.

Every line is authored in English and Brazilian Portuguese together. Slots supply complete noun
phrases carrying their own articles, and the sentences around them are written so that nothing agrees
with the noun — which is what makes one template correct for six volumes in both languages.

Every button is pressable and typable, and the compiler writes a matcher fixture for each one as it
emits it, so the two cannot drift apart.

**Topics got the same treatment, gated on the world instead of on an episode.** A profession scene is
selected by a situation that has a state; a topic has no such object, so these are selected by the
context and the profile — the hour, the season, the weather, how long you have been away, how well the
two of you know each other, and what she values. The weather topic only has the wet-work scene while it
is actually raining. Feelings only has the flat-stretch scene for somebody she has known a fortnight.
Fears only reaches its honest scene after dark, and only for a confidant.

### Added — datapack surface

Four new directories — `identity_tokens/`, `episode_templates/`, `thread_templates/`,
`commitment_templates/` — plus `conversation_scenes/`, and nine new conditions and five new actions.
The vocabulary is deliberately small and orthogonal: a pack that wants a new anchor adds a token, and
one that wants a new kind of situation adds a template. There is no condition per interest and no
action per episode kind.

Every action instantiates an authored template rather than a shape, so a result cannot invent an
episode kind or an unregistered promise from JSON.

`conversations_say` gained a `slots` list, which fills the positional args after the vars.

### Added — configuration and operator commands

`[dynamic]`, `[history]` and `[group]`. Every switch has an off state that reproduces the static
conversation exactly,
and `dynamic.enabled = false` silences the whole layer without touching seven other flags.

Eight new `/conversations` subcommands for inspecting generated state, which ordinary play
deliberately never shows: the profile, the history, the plan and why it won, a dry-run candidate list,
the context snapshot, and the compat capability status. One narrow `history forget confirm` drops a
single villager's profile and history and says exactly what it dropped; there is deliberately no
wipe-everything command.

### Added — tests and reports

42 new tests, taking the suite from 640 to 682, all green. They cover profile determinism and
distribution, anti-stereotype constraints, alias migration, NBT round trips for every record family,
malformed-row containment, forward schema tolerance, cap enforcement, the episode state machine, the
seeded slot pools, and nine coherence lints over the shipped scenes.

Four new generated reports beside the existing four: `scenes.md`, `identity-coverage.md`, `threads.md`
and `memory-schema.md`, all deterministic and diffable.

Three lints caught real bugs in this release's own content and are worth naming, because they are the
reason the layer is trustworthy: a chat intent whose authored phrase could not pass its own
`requiresAny` gate; a page where the villager left a decision open and none of the buttons decided
anything; and a `practical_help` reply offered after a line that does not allow it.

### Changed

- `SceneCatalog`, `IdentityCatalog` and `NarrativeCatalog` return their contents in sorted order.
  `Map.copyOf` makes no ordering promise, and a report or lint walking one directly would have
  produced a different ordering on a different JVM run.
- `Confidence.weakened()` skips `SELF_REPORTED`: a story that has travelled one hop must not come out
  as "the player told me about themselves".

### Compatibility

Saves from 1.3.0 load unchanged. The new stores are separate files that simply do not exist yet; the
existing progress ledger, disposition vectors, MCA memories, arcs, milestones and affection budgets
are read and written exactly as before. A world opened once under a later build and rolled back is
read with the current reader rather than discarded.

No fabricated first meeting is backfilled for an old pair. A villager who has known you for months but
has no event record says so honestly rather than inventing a memory.

## [1.3.0] - August 25th, 2026

Two things. MCA renamed its own package and took this mod down with it, which is now fixed in a way
that will survive the next rename as well as this one. And Townstead support begins — the boundary
between the two mods only, with nothing player-facing yet.

### Fixed — MCA 7.7.1 moved its package, and villagers stopped talking

**On MCA 7.7.1-alpha.1, chat mode went silent and the rest of the mod went with it.** MCA renamed its
base package from `net.mca` to `net.conczin.mca` while keeping the Forgix merge, so the Forge root
moved from `forge.net.mca.*` to `forge.net.conczin.mca.*`. Every MCA reference here was a
compile-time import, so all seven mixins failed to apply and the MCA bridge threw as it registered —
which left it reporting itself unavailable, and that flag is the gate every chat message passes
through. Nothing crashed; villagers simply stopped answering.

The root cannot be guessed from the version number — `7.7.0-beta.2` is still `forge.net.mca` — so
guessing was never an option. **MCA is now resolved by name at runtime instead**, the same discipline
the Townstead layer below uses:

- The package root is probed once at startup, and a manifest of every MCA member this mod touches is
  resolved to method handles. Anything unresolved becomes a constant stub returning its type's
  default, so no call site needs a guard of its own.
- **Every public signature in `McaCompat` is unchanged**, so the rest of the mod did not move. Dialogue
  conditions and actions are registered through `MethodHandleProxies` over interfaces resolved from
  the probed root — deliberately not a plain `Proxy`, because MCA calls a default method on its own
  condition interface when a result combines conditions, and that has to stay inherited.
- All seven mixins now declare **both** package roots and take MCA-typed injector parameters loosely,
  so they apply whichever root is installed.
- **MCA has left the compile classpath entirely.** That is what turns "do not name an MCA type" from
  advice into something the build enforces.

Four guards now run on every build against MCA `7.6.20`, `7.7.0-beta.2` and `7.7.1-alpha.2`: the
binding probe, a static-link scan, a mixin-target probe (injections are declared optional, so a
renamed injection point would otherwise fail silently), and a pseudo-shadow probe — a `@Pseudo` mixin
may only shadow members declared on the target itself, and getting that wrong shipped as a startup
crash once already.

The declared MCA range is unchanged at `[7.6,8)`; it now actually holds across it.

### Added — the optional Townstead boundary

**There is nothing new to hear in game yet.** No villager says anything they did not say before,
because no dialogue condition, template variable or topic reads Townstead state until the next slice.
What this establishes is the part that has to be right before any of that is safe to write.

- **[Townstead](https://www.curseforge.com/minecraft/mc-mods/townstead) is now an optional
  dependency**, range `[0.7.5,0.8)`, gated on `0.7.6`. With Townstead absent nothing changes at all:
  no Townstead class is resolved, no log line is written, and every existing seeded dialogue check
  resolves exactly as it did before. That is the normal case for most installs and it is deliberately
  silent.
- **One jar, not two.** Townstead is itself compiled against MCA, so its own method descriptors name
  MCA types — and, as above, MCA has shipped under more than one root. The obvious answer is a
  Conversations jar per layout that everyone has to match up correctly. Instead every Townstead member
  is resolved by name and arity, and every call crosses through a handle whose arguments are all
  `Object`, so an MCA value passes through as a reference this mod never names. There is no artifact
  to match and nothing to install wrongly.
- **What bound is reported as capabilities, not as a yes or no** — fourteen of them, covering villager
  state, needs, schedule, calendar, buildings, roots, personalities, skills, village spirit, context
  tags, reactions, heart tracking, dialogue tracking and the reaction lock. A capability counts as
  bound only when every member it needs bound, so if a future Townstead moves one internal method,
  exactly the feature that needed it switches off and names the member it wanted. Nothing else is
  affected and nothing throws.
- **A `[townstead]` config section**, documented in `CONFIG.md`. Every option is present and all but
  `enabled` are inert in this alpha; they are declared now so the file does not churn again as each
  following slice lands.

### Notes for testing

- With Townstead installed, expect exactly one line at startup naming its version, the MCA root it was
  built against, and `14 capabilities bound`. A `PARTIAL` or `INCOMPATIBLE` line is the interesting
  one: it lists the members that did not resolve, and why.
- `/conversations compat townstead status` does not exist yet; it arrives with the next slice.
- Reactions, when they arrive, will need **Emotecraft**. Townstead can only play a reaction through a
  registered animation backend and ships exactly one, so without that mod a reaction is inert however
  well everything else binds. Conversations treats that as "no reaction" rather than as an error.

### Internal

- Townstead's need scales are not one scale: hunger runs 0–100 while thirst and fatigue run 0–20.
  The semantic bands (`starving`, `famished`, `hungry`, …) reuse Townstead's own state vocabulary and
  its own published thresholds rather than inventing either, and a probe pins all thirteen constants
  against the real jar, so a retune upstream fails a test instead of quietly changing what a line means.
- `./gradlew townsteadProbeTest -PtownsteadLegacyJar=<path>` resolves the whole Townstead manifest
  against a supplied jar in its own JVM. Since no class names a Townstead type, the compiler cannot
  report a renamed member; this is what does. Skipped when no jar is supplied.
- Two standing tripwires for Townstead, matching the MCA ones: no compiled class may reference a
  Townstead type, and nothing outside the guarded `compat/townstead` package may reference that
  package. Both scan the constant pool of every compiled class, and neither needs an exemption list.

## [1.2.1] - August 25th, 2026

A content pass over every shipped conversation, in both locales, plus the one binding defect that was
corrupting every value those conversations substitute. Apart from that defect nothing here changes a
system; it fixes lines that reached the screen wrong and exchanges that did not cohere once read end
to end.

### Fixed — every substituted value, in every voiced line

- **Villagers spoke the argument array instead of the value it held.** Any line that substitutes
  something — a quest title, a name, a season, a gift, a village — drew its first argument as
  `[Ljava.lang.Object;@7fd2575c`: *"Otectus, thank you. [Ljava.lang.Object;@7fd2575c has been weighing
  on me."* MCA's `getTranslatable(Player, String, Object...)` is varargs, so `Lookup#unreflect` returns
  a **varargs collector**, and `asType` to the binding's erased all-`Object` shape does not pass the
  trailing array through: `Object[]` is not assignable from `Object`, so `asType` silently builds a
  *one-element* collector that wraps our argument array inside another array. MCA received exactly one
  argument — the array — and printed its identity at `%2$s`. `McaBinding` now pins every resolved
  handle to `asFixedArity()` before erasing it, which is the whole fix here and a no-op for the other
  65 members.
- **The blast radius was every templated line the mod has ever shipped**, because `conversations_say`
  and `conversations_gossip` both deliver through this handle: 509 English lines name `%2$s` across the
  base pool and the 21 personality overlays, and each has a `pt_br` twin. It was never version- or
  layout-specific — every MCA build and both package roots resolve the same varargs method. The 163
  lines naming `%3$s` failed differently and more visibly: with only one argument present Minecraft
  aborts the substitution and draws the untouched template, `%3$s` and all. Lines with no substitutions
  were unaffected in appearance, having no slot to show the stray argument in.
- **Only the Forge build was ever wrong.** The Fabric source calls `getTranslatable` directly, where
  javac spreads the array into the varargs slot correctly; nothing there changes.
- **`TownsteadBinding` is pinned the same way**, at both its method and constructor sites. Townstead
  declares varargs members of its own (`getTranslatable`, `aliases`, `context`, `applyToBase`) and
  binds none of them today, so this changes nothing now and stops the first one that is bound from
  being silently corrupted instead.
- **A test now holds it.** `McaBindingErasureTest` binds a local varargs method through
  `McaBinding.erase` and asserts the tail arrives as the argument array itself. Dropping the
  `asFixedArity()` again binds cleanly, throws nothing and logs nothing — the probe tests would still
  pass, because the manifest still resolves — so without this test the next reader has only the
  comment to go on.

### Fixed — lines that rendered as their own source code

- **Four topic openers showed the player a raw format string.** A plain `say` hands MCA exactly one
  argument — the spouse-aware player name at `%1$s` — but `conversations.village.home`,
  `conversations.us.happy.grateful`, `conversations.us.firstmet.memory` and
  `conversations.family.memories.share` each named `%2$s`. Minecraft catches that at render and
  substitutes the untouched template, so *"What's it like living here?"* answered with a literal
  *"%2$s? It's home."* — and because all 21 personality overlays override `village.home`, every
  villager in the game was broken on it. The three that wanted a real value are now
  `conversations_say` with the variable they were written for; the family story, which wanted a
  relative's name and no template variable supplies one, names one of the villager's own instead.
- **Six pooled families wrote their template variable at `%1$s`.** The declared var was never read,
  so the resolved value was discarded and the player's own name landed in the noun slot: *"Why? In
  **Steve** you don't ask why."*, *"that **Steve** you brought me is still doing its work."*, *"In
  **Steve**? Whatever's ripe…"* The season, weather, holiday and last-gift hooks never once reached
  the screen. Moved to `%2$s` in both locales.
- **One result spoke twice.** `topic.checkin.good.respond#ask_more` carried both a
  `conversations_say` and a `say`; both push a finished line, so the client kept whichever landed
  last and the holiday line — variable and all — was resolved and thrown away.

### Fixed — branches that could not be reached

- **Eight topics paid hearts for being asked a second time.** Ten results either listed a condition
  twice (doubling its weight) or paired `+1000` with `-1000` on their own cooldown memory (cancelling
  it), which sank the repeat branch below the branching-disabled fallback. MCA picks the last result
  when everything scores zero or less, so re-asking dropped into the legacy path: the first-time line
  again, no session, and 2–4 hearts every time. Simulating all 28 topics at their cooldown now finds
  none that reward.
- **Five topics had no repeat branch at all.** `us.happy`, `us.future`, `family.checkin_child`,
  `family.ask_parent` and `season` are gated by a cooldown that nothing was written to catch. Each
  now has the "you already asked me" beat its twenty siblings have.

### Changed — content that read as the same line twice

- **The five deep topics shipped one refusal between them.** `life`, `dreams`, `hopes`, `regrets` and
  `secret` all answered a guarded villager with *"Some things stay mine for now."* over the identical
  four buttons, even though the replies behind those buttons were already topic-specific. Each is now
  written to the reply that follows it — a half-built thing, a hope you jinx by naming, a stone put
  down on purpose. The three young-villager screens (`life`, `dreams`, `hopes`) were cloned the same
  way and are likewise distinct.
- **69 second variants were rewordings of the first.** Reviving the dead base lines in 1.2.0 made both
  halves of every overlay pool visible for the first time, which exposed the pairs whose second line
  restated the first in different words — concentrated in `gloomy`, `greedy`, `peppy`, `sensitive` and
  `odd`. Rewritten as separate beats.
- **A crit read exactly like a success.** `day.lighten` shared its best-outcome line with its ordinary
  one, so the check system's top result was indistinguishable from a pass.
- **Villagers describing themselves as the wrong gender.** The `flirty` overlay called itself "a girl"
  five times and the player a "Handsome nuisance"; the base pack reported the player as "she" and
  spoke of "the man doing his job" in three places; the Portuguese carried "o homem" and "uma moça" in
  the same lines. Overlays apply to villagers and players of any gender.
- **House style is now consistent across both locales**: em dash throughout (82 bare hyphens
  replaced), ASCII ellipsis throughout (134 unicode ones replaced), and one missing comma.

### Changed — Brazilian Portuguese

`pt_br` is re-synchronised with every line above: 141 strings retranslated, the five new repeat pools
authored, and the gendered self-references fixed independently of the English. Key sets and
placeholder signatures remain identical across locales in all 23 namespaces.

### Added — five lints for the classes above

`ContentLintTest` now fails the build on: a line naming an argument its call site does not pass; a
declared template var no variant reads; a result that sets the speech slot twice; a condition listed
twice; and a condition both boosted and sunk. `LangKeys.linesOf` gives them one shared rule for
"every line MCA can actually draw for this key".

## [1.2.0] - August 19th, 2026

### Fixed — one line, two different sentences

- **A villager's reply no longer differs between the dialogue screen and the chat log.** MCA picks
  which `/N` variant of a pooled line to speak on the *client*, at random, once per `Component`
  instance — and it renders every interaction-screen line from three separate instances: the chat
  copy and the text-to-speech copy are each re-parsed out of the packet's JSON, while the panel keeps
  the original. Three parses, three independent draws, so the screen could read *"Straight to the
  terms."* while chat read *"Whatever's fair."* for the same click, and a voice pack could speak a
  third line again. The message now serves back the components it was built with, so all three read
  the same sentence. (It also stopped MCA's profession branch, which flips a coin per resolution,
  from putting the two surfaces in different *pools* rather than merely different variants.)
- **3,518 authored sentences that could never be shown are back in rotation.** MCA's pool builder
  indexes only the `/N` keys and always draws from them once any exist, so a plain base sentence left
  beside a `/1` is dead content — MCA's own lang has no such key anywhere, and ours had 3,518 of them.
  Every pooled family has been renumbered so its base line is simply its first variant. 1,678 of
  those families had exactly one live line where two were written, including 34 of the 38 pooled lines
  in *every* personality overlay: a crabby villager now has both of their answers instead of one. No
  sentence was edited, added or removed — the keys were renumbered around them.
- **The lint that was supposed to guarantee variety was counting a line nobody sees.** The pool floor
  added one for the plain base key, so a family with a floor of "three lines" was shipping two. It now
  counts only what MCA can draw, and a plain key beside a pool is a build failure rather than the
  house style. Coverage lints learned the same rule: a key exists if it is plain *or* pooled.
- **Two players standing together hear the same words.** A chat-mode reply is sent to the speaker and
  to every bystander in range as separate messages, and each client used to roll its own variant.
  The variant is now chosen once on the server and sent as a concrete key, read straight from the lang
  files inside the mod's own jar — so it works on a dedicated server, where `assets/` is never mounted,
  and there is no generated index that could drift. The index deliberately excludes the five pools that
  extend MCA's own (`dialogue.main` and friends), where we ship only half the lines, and never names a
  variant past the end of a villager's personality overlay, which would silently drop them to the
  generic voice.
- **The humanised reply delay no longer scales with the length of a lang key.** On a dedicated server
  the line cannot be resolved, so `getString()` returned the raw key and the "typing time" tracked its
  spelling. The delay now uses the chosen variant's real length, falling back to the corpus median
  when there is nothing to measure.

### Added — the ledger speaks (MCA: Reputation, optional)

The 1.1.0 bridge supplied normalized gossip candidates but nothing rendered them, and the standing
topic was designed but not authored. Both are real now; without MCA: Reputation installed, nothing
below exists and Conversations is unchanged.

- **External gossip is actually told.** The gossip logic merges two sources — the native village log
  and the incidents this villager knows about the listener's deeds — into one normalized shape, and
  the newest story wins deterministically, so the `conversations_gossip` condition and the say
  action can never disagree. External stories render through Conversations' own dialogue voices
  (`dialogue.mcareputation.gossip.*`, en_us and pt_br) with up to four arguments, and use the same
  once-per-teller `LongTermMemory` flag native gossip always has.
- **Duplicate quest gossip is suppressed.** With Reputation active, completing a quest no longer
  seeds the generic `QUEST` gossip event — Reputation's named quest incident is the canonical story,
  and one deed should not be told twice in two voices. Memories and state still apply either way.
- **The standing topic.** A Village-category question — *"What do people think of me around
  here?"* — reachable from the GUI and from chat. The answer branches on your actual standing
  (well-regarded, neutral, poorly regarded, or an unresolved matter this villager knows about) and
  speaks your tier; you can press for the deed people mention, and when something genuinely hangs
  over you, an amends path lets you apologise in public — recorded through
  `conversations_reputation_signal` as `mcareputation:public_apology`, once per decision, never
  resolving the original deed by itself. Taking the answer with grace or snapping at it matters;
  children and teens deflect; without Reputation the villager honestly shrugs. Catalogued, linted,
  chat-intent-covered, and localized in both languages.

### Added — every topic is a real conversation now

- **The last ten legacy starters are converted.** `us`, `family` and `feelings` were flat menus that
  paid up to +8 hearts for a single click and had no follow-up at all — a spouse could say *"I've
  been rehearsing how to say it for a week"* and the conversation simply ended. They are now
  branching trees, split into `happy`, `firstmet`, `future`, `worries`, `memories`, `checkin_child`,
  `ask_parent` and `feelings`, with an arc on `feelings`. The migration ledger in
  `ConversationGraphLintTest` is **empty**: no topic anywhere pays hearts for being asked.

### Fixed — things the trees promised and did not deliver

- **A secret is no longer told before you agree to hear it, and is actually told once you do.** The
  opener used to *be* the secret; agreeing to hear it replied *"here it is"* and then delivered
  nothing. The opener is now a pre-disclosure beat with no content in it, both answers that accept
  carry the payload, and **declining has its own ending** — you can offer to hear it another time or
  change the subject, and you are never asked to promise to keep a secret you were never told.
  Declining does not advance the arc or set the `secret.entrusted` milestone.
- **Asking a villager whether anyone else knows their secret is no longer treated as betrayal.**
  *"Shall I mention it to the others?"* read as asking permission and cost 3 hearts and 8 trust. The
  hostile button now says what it means — *"I've been thinking of letting it slip."* — and the
  question it was impersonating is a real, safe answer of its own.
- **Jokes and payoffs no longer name things that only happened in one version of the story.** *"Well,
  the cat clearly won."* answered a bad-day opener that mentioned a cat one time in three; the other
  two were a sticking door and a dropped egg. Same for the toddler weather branch, where playing
  along meant agreeing about sky sheep the child had not necessarily mentioned. Both are now written
  to what every variant shares, and `DATAPACK.md` carries the rule.
- **Children can no longer be asked to keep an adult's secret.** `regrets`, `secret`, `rumors` and
  `work_offer` declared themselves adult-only in the catalog and were gated only against toddlers
  and babies. New lint `catalogAgesMatchOpenerGating` walks inbound routes — so a topic gated by its
  category page is not asked for a redundant gate — and fails if a catalog age and the button that
  offers it ever disagree again.
- **Three buttons in `life` that read *"Thank you for telling me."* did three different things**, one
  of which paid a heart. New lint `answerLabelsAreUniqueWithinATopic` allows identical labels only
  where the consequence is identical, so reusing a bare exit line across topics stays legal — that is
  voice — while a disguised choice is not.
- **The villager no longer narrates your conversational stances back at you.** When chat mode had to
  ask which of two things you meant, it rendered them from the design vocabulary: *"Do you mean
  offering comfort, or hearing the rest?"* Those now read as fragments of what you would have said.

### Added — a promise you can actually break

- **Pledging to stand with someone and then not coming back is now a thing that happened.** Both
  durable commitments the system tracks — `fears.support` and `dreams.support` — could previously only
  ever be honoured; the arc lines rewarded turning up and there was no branch for the other case. Making
  the pledge now also stamps a dated memory, and when the villager next raises it and that stamp has
  lapsed, they say so. **No hearts are lost on a first lapse** — the cost is trust and tension — and
  `fears` gets a repair node where you can own it, offer to be there now, or decline to make excuses.
- **A crit now tells you something a success does not.** Pressing or comforting well enough to crit
  promised *"the rest of it, the true shape"* and then routed to a page reading *"So now you know"*,
  which contained nothing new. Both crits now reach an authored second layer of the fear — how long
  it has been carried, and what it costs — before the conversation continues. The tier system is
  visible in the text rather than only in the ledger.

### Fixed — tiers that contradicted themselves

- **Being told off no longer leads to a page thanking you for the trust.** *"I didn't hand you that
  so you could pat it on the head"* routed to a close node whose two substantive answers were
  *"Thank you for trusting me with that"* and *"That took something to say"*. Rebuffs now reach a
  rebuff-aware close — apologise, accept the boundary, or leave. New lint
  `rebuffTiersDoNotRouteToLandedCloseNodes` fails any rebuff that lands on a node granting trust or
  warmth.
- **Apologising for having pushed no longer hands back the button that caused the scar.** It routed
  to the guarded node, which offers *"Come on, you can tell me."* It now reaches a repair node with
  no boundary push on it at all, asserted by the path simulation.
- **Turning checks off is no longer invisible.** In the off-state `fears` question the checks-disabled
  fallback shared its lang key with the success tier, so both said the same sentence — and the key,
  by being spelled `.success`, also collected the relaxed two-line variant floor meant for check
  tiers. The fallbacks now have their own three-line pools, and
  `sayKeyPoolsMeetTheVariantFloor` derives the relaxed floor from the result's `conversations_check`
  condition rather than from how the key is spelled.
- **"I can't promise that, but I'm listening" is now remembered as itself.** Three of the four
  exclusive groups recorded a second member that no condition ever read, so the honest refusal read
  back exactly like never having had the conversation. Each group now branches on both members and on
  having taken neither, and the fourth — the one that was already right — had a latent 1-in-101
  chance of speaking the wrong line, which is fixed too. New lint `everyExclusiveMemberIsReadBack`.

### Added — ages, and the day after

- **Toddlers have their own voice in nine more topics.** `food`, `life`, `dreams`, `hopes`,
  `feelings`, `village` and `checkin` routed three-year-olds into replies written for a ten-year-old,
  so a toddler who said *"I'm little! I do puddles and snacks and naps"* could be answered with *"Go
  on, tell me properly"* and reply *"Right — nobody ever asks for the long version."* Each now has a
  real toddler node. `weather` and `season` had the opposite problem — their "young" nodes were
  reached by toddlers and nobody else and were already written that way — so those are renamed to
  match what they are.
- **Children can report the village news, in a child's words.** `news` declared `child` and `teen`
  and shipped no age content at all, so children delivered adult lines about deaths and divorces.
  There is now a child's telling of every event type, rendered through
  `conversations_gossip_say`'s `phrase_prefix` — a parameter with zero uses until now, and one the
  lint had no opinion about; `gossipTypeLinesExistForEveryPrefixInUse` now requires any prefix in use
  to cover every type it can be asked to tell. `noticed` went the other way and is adult-only:
  reading an adult's mood and naming it is an adult move.
- **The second day of an arc is a real conversation.** Eight arc-resume nodes were terminal — every
  answer went straight back to the category — so returning to a villager you had opened up with was
  thinner than meeting them for the first time. Each substantive answer now continues into a second
  tier and then into the topic's existing close, while brush-offs and exits still end where they did.

### Fixed — a topic nobody could reach, and three that had no wrong answer

- **The ordinary day was unreachable.** The catch-all branch for a villager in no particular mood
  doing no particular chore — much the commonest state in the game — was authored with
  `baseChance: 0` and nothing but negative sinks, so it could never score. MCA's zero-weight rule then
  handed the click to the *last* result, which is the branching-disabled legacy line. Most villagers,
  most of the time, were getting the 1.0.0 experience with branching switched on. New lint
  `everyResultCanActuallyBeChosen` fails any result MCA could never pick.
- **`work_offer`, `rumors` and `noticed` had no wrong button**: every answer had exactly one authored
  outcome for every villager in every state. Each now varies — validating a grieving villager lands
  differently on a crabby one than a sensitive one, challenging a rumour lands differently on someone
  who trades in them, and asking what the job pays is a different conversation with a greedy villager.
- **Their branching-off state is the old experience again, not a stub.** All three answered with one
  unconditional line, and `work_offer` never opened the quest screen even when a quest was waiting —
  contradicting the documented promise that all three toggles off is exactly the 0.6.0 experience.
- **The depth floor is measured on every normal adult branch**, not just the deepest one
  (`topicsMeetTheirDepthFloorOnEveryNormalAdultPath`). That is what let the eight arc paths above ship
  at one decision while their topics claimed to be Deep. Age branches, cooldowns, below-gate deflects
  and "there is no news" branches are excluded — those are meant to be short.
- **The path simulator covers every topic**, not two of twenty-seven
  (`TopicPathSimulationTest`, renamed from `PilotPathSimulationTest`). It walks each catalogued topic
  from its opener back to the category through the real progress store, checking that asking never
  pays, that every state resolves to exactly one result rather than a lottery, that the walk
  terminates, and that the total stays inside the topic's budget. It also refuses to pass vacuously:
  a topic whose opener does not reach a branching node is reported rather than skipped.

### Changed — the deep topics stop sounding like each other

- **`life`, `dreams`, `hopes`, `regrets` and `secret` no longer share their words.** Three entire
  sub-trees were byte-identical across all five, so the refusal to tell you a secret and the
  reluctance to discuss your hopes for the harvest were the same sentence — *"I could. I'm choosing
  not to. There's a difference."* Every guarded, again and close pool is now written to its own
  subject: `secret` refuses to be pried at, `hopes` is superstitious about jinxing it, `dreams` will
  not be shown a house with no roof on it, `regrets` will not lift a stone it put down on purpose.
  New lint `deepTopicsDoNotShareLines` — exits stay shared on purpose, because a reused parting line
  is voice, and the exemption keys off the answer being an exit rather than a word count.
- **The "nobody has ever done that for me" beat is rationed to six sites.** It fired at 25, one for
  essentially every kind act in the game, which collectively established that every villager had been
  ignored by everyone forever until the player arrived. The other nineteen now acknowledge kindness
  four other ways — practical (*"Tuesday, and bring the good axe"*), deflecting (*"Don't say it in
  front of the others"*), surprised-then-brisk (*"Huh. Right — where were we"*), and reciprocal
  (*"And you? You get to answer that too"*). `rewardBeatIsNotOverused` matches the beat rather than
  the word, so a line like *"Nobody's said 'settled' yet"* — a fact about the village — is not caught.
- **Two node shapes that did not exist before.** Every one of 140-odd nodes was
  `[warm, curious, hostile, leave]`, so after two topics the buttons were predictable by position.
  `life`, `work`, `village` and `people` now offer a fifth answer — a joke — and the five deep closes
  offer to trade rather than only to thank, which is also the reciprocal disclosure the trees never
  allowed even though `deflect.secret` explicitly invites it. `regrets` gains a two-answer beat in the
  middle of its tree, where the only moves are to stay with it or to give the room; at the heaviest
  moment in the topic a menu was the wrong shape.
- **`dreams` forks.** Encouraging someone and being honest with them now lead to two different
  closes rather than the same one.
- **Variant pools that were one line and its editor's pass** are rewritten to take different angles
  rather than different wordings — `regrets.again` had the same "stones" metaphor twice,
  `work.respond.challenge.polite` said "it's fine, not love" three ways, and the `greedy` and `odd`
  overlays each had a near-duplicate pair. `variantPoolsAreNotParaphrases` logs anything above 0.65
  similarity for a human to judge and fails above 0.80, where it is not a judgement call any more.
- **`labelsDoNotReferenceSingleVariantDetail`** locks in the earlier fix and carries a curated map of
  which props belong to which variant, so the next pool with differing detail is caught rather than
  discovered in play.

### Added — the systems that were built and never used

Six small engine changes, each of which existed to make content possible that had never been written.

- **`enableTopics` does something.** It had no effect whatsoever: the flag was read only through a
  feature key no shipped dialogue named. All 150 branching results across the 27 openers now sink on
  it, so turning it off falls every topic back to its legacy one-line result — what `CONFIG.md` has
  claimed since 0.6.0. Asserted by a simulation over every catalogued topic.
- **`enableQuests` switches the quest conditions off.** `questScore` never consulted it, so quest
  branches kept matching for players who had turned the integration off.
- **`conversations_disabled: "seasons"` and `"holidays"` can fire.** Both fell through
  `isFeatureEnabled`'s default and scored as permanently enabled, so season- and festival-aware
  content had no way to degrade.
- **`PROUD` is read by something.** The state a villager is left in by the player finishing a quest
  for it was written, given its own config window, and consulted by nothing — not even the check
  resolver, which knew the other four. It is worth +4, between gratitude and infatuation.
- **`conversations_session` is readable.** 114 results had been writing a `branch` into the session
  since 1.1.0 and its only reader was its own setter, so content duplicated the branch into node
  names instead. The five deep topics' "we were just here" nodes — identical but for one lang key
  each — are now **one** node that asks the session which topic is open.
- **`conversations_budget` exposes the daily ledger.** `positiveToday`, `negativeToday` and
  `repeatsToday` were tracked per villager and player and readable by nothing, so the cap clamped
  kindness to zero in silence. At the cap the villager now turns the offer down warmly.

### Added — content for the seams that had none

- **Villagers say something different at midnight.** `time_min`/`time_max` had zero uses and the
  `time_of_day` template variable zero references, so nobody in the mod spoke differently at dawn
  than at noon. Being about at a strange hour is now its own check-in branch, and asking what is
  keeping them up continues into the rough-day follow-up.
- **The disposition vector is finally audible.** `tension` had 105 writes and no reads, `familiarity`
  95 and none, and no gate anywhere used a `min` bound — so there was not one "you have earned this"
  threshold in the mod. Three now exist: a cooler reply while the air is still unsettled (the missing
  half of the apology mechanic), a warmer one from someone who has known you a long time, and an
  extra beat at high trust. All carry the `dispositions`-disabled sink the documentation described
  and no content had ever used.
- **Checks reach five more topics.** `conversations_check` lived entirely in `fears`, so eleven of
  fourteen stance families' tuned personality bias was computed by nothing at runtime. `regrets`,
  `work`, `village`, `people` and `day` each gain a checked stance. The `day` one converts a
  hand-rolled two-list personality gate into a real humour check — which is what finally makes the
  headline claim about a joke landing for a playful villager and falling flat on a gloomy one true
  rather than decorative.
- **Personality profiles cover what the content uses.** `curiosity` is required by 13 topics and was
  biased by 4 of 17 profiles; it is now on 15. `candor` goes from 3 to 11, and a resting `respect`
  baseline from 3 to 13 against content that writes the axis 91 times.
- **Replies read mood.** All 54 mood conditions were on openers; not one reply node read mood, which
  is the moment it matters most. Eight now do — including `passive` and `fine`, which nothing in the
  mod had ever branched on, so "they are just having an ordinary day" was unwritten.
- **Four MCA-native conditions that had zero uses.** A mayor now answers differently about the
  village than a peasant, an outlaw differently about the neighbours, a villager notices you are
  bleeding, and a village with a smith says so.
- **The world reaches beyond two answers.** All 36 world-condition uses sat in one file, so a farmer
  at harvest and a fisherman in a storm said identical things. `work`, `village`, `food`, `checkin`,
  `weather` and `season` are now world-aware, and the `clear` and `none` values — implemented,
  localized and never once authored — finally have lines.
- **The gift and quest layers reach ordinary conversation.** Gratitude names the gift, a smitten
  villager will talk about anything you like, and a villager you finished a quest for is almost too
  embarrassed to ask for another.
- **Quest gossip is tellable.** `GossipEventType.QUEST` was seeded, localized in both languages, and
  reachable only through `rumors`, so a village whose one untold event was a quest said "quiet week".

### Added — new content

- **The personalities speak past the first sentence.** Overlays covered 27 of 1,442
  `dialogue.conversations.*` keys and every one was a topic *opener*, so a villager said one line in
  their own voice and handed the next six exchanges to a single narrator. All 21 namespaces now also
  voice the first **reply** in six registers — accepting sympathy on a bad day, accepting an offer of
  help, being seen as a person rather than a pair of hands, being promised support, being given room
  to hope, and being asked again days later. Two lints hold it: every namespace must cover the set,
  and no two personalities may ship the same sentence for the same key.
- **You can ask about someone in particular.** `people` and `rumors` covered the neighbours in the
  abstract; there was no way to ask about a *person*, despite gossip having always templated real
  villager names. The new `neighbour` topic tells the same events as a considered opinion of somebody
  the villager has known for years — with its own voice, through `conversations_gossip_say`'s
  `phrase_prefix`. You can ask what they are really like, defend them, tell the villager it is not
  theirs to tell, or push for more and be turned down for it.
- **"I don't know what to say."** Every deep node forced warm, curious, cruel or leave, so honest
  inarticulacy — the most natural response to a confession — was unrepresentable in 593 labels.
  `life`, `regrets`, `fears` and `dreams` now let you say nothing useful and stay anyway, and are
  warmer for it than the composed answer would have been.
- **Blunt honesty is a stance.** `candor` was in the vocabulary, carried personality bias, and no
  topic required it. `noticed`, `people` and `work` now do: *"You're not fine and we both know it."*
- **Deferral and reciprocal disclosure**, the two other moves the trees never allowed: you can ask a
  villager to tell you when they are ready, and you can trade a story instead of only receiving one —
  which `deflect.secret` had been explicitly inviting (*"Secrets are traded, not given"*) with no way
  to accept.

### Known unbuilt

- **`flirtation` and `attraction` remain scaffolded and unwritten**, along with the three orientation
  traits. Both are in the stance and axis vocabularies, both carry interiority bias, and no content
  requires either. This is a deliberate scope decision rather than an oversight: the romance vertical
  needs a design pass of its own, and half-writing it would be worse than leaving it clearly empty.

### Fixed — documentation that had drifted from the code

- `README.md`, `CURSEFORGE.md` and the catalog's own comment all still said two pilot topics were
  converted; it is twenty-seven. The translated-string count is re-measured (4,724 per locale across
  23 namespaces), and the overlay claim no longer implies the personality voices reach the branching
  bodies — they cover the openers and deflections only.
- `DATAPACK.md`'s variant-floor bullet described a rule the lint does not enforce; it now matches
  `sayKeyPoolsMeetTheVariantFloor` exactly. Its `conversations_gossip` type list was missing `quest`.
- `CONFIG.md` claimed `enableTopics` deflects topic branches (it has no effect at all) and that
  `enableQuests` makes quest conditions score 0 (`questScore` never consults it). Both rows now say
  what the code does; both flags are wired up later in this release.

## [1.1.0] - August 17th, 2026

### Added — MCA: Reputation integration (optional)

**Villagers now take your public standing into account, and can tell each other what you have done —
when [MCA: Reputation](https://github.com/otectus/MCAReputation) is installed. Without it, nothing
about Conversations changes at all.**

That last clause is the important one and it is asserted by tests, not merely intended: with the mod
absent the standing term is exactly `0`, so every seeded check resolves to the tier it always did.

- **Public standing colours trust and respect checks.** `CheckInputs` gains a `publicStandingFit` term,
  read from the player's current tier and hard-clamped to ±8 on both sides of the bridge. The resolver's
  tier margin is 15, so standing can tip a borderline outcome and can never carry a check on its own.
  Warmth, attraction, tension, and familiarity receive nothing — those are private interpersonal state
  between one villager and one player, and what the village at large thinks has no business there.
  Reputation never writes a disposition axis and never grants hearts.
- **Two dialogue conditions**, `conversations_reputation` (score, tier range, title) and
  `conversations_reputation_incident` (type, status, tags, age, and whether *this speaker* actually
  knows about it). Both are registered **unconditionally**, because dialogue JSON naming an
  unregistered key is an error — a pack written for the full suite has to load on an MCA-only install,
  where they score `0` and your authored fallback branch fires.
- **One dialogue action**, `conversations_reputation_signal`. It names an *incident definition*, never a
  raw score delta: how much a public apology is worth is decided by the datapack, and the dedupe key —
  villager, player, decision id — makes a second click a no-op. Small talk, navigation, and asking the
  opener cannot reach it. Repeated clicking cannot farm standing.
- **The external gossip seam.** Reputation supplies incidents this villager knows as normalized
  candidates carrying a phrase key and up to four arguments; the "already told" flag stays exactly
  where it has always lived, in MCA's `LongTermMemory` under
  `mcaconversations.gossip.<eventUuid>.<playerUuid>`. Built-in gossip is untouched. (Rendering the
  candidates and merging the two sources landed in 1.2.0.)
- **Five template variables** for `conversations_say`: `reputation_tier`, `reputation_score`,
  `reputation_village`, `reputation_recent_deed`, `reputation_title`. Each has a neutral localized
  fallback in both `en_us` and `pt_br`, so a line using one never breaks and never reads as an error.

### Changed

- `gradle.properties` no longer hardcodes an absolute Linux JDK path, which made the build fail on any
  other machine. Set `JAVA_HOME` to a JDK 17 instead.

### Compatibility

- **MCA: Reputation is entirely optional**, gated by the same `ReputationBridge` discipline as
  `QuestsBridge`: no `mcareputation` import outside `compat/reputation`, the implementation reached by
  name after a `ModList` check, and every failure contained to one ERROR. A test walks the source tree
  and fails the build if either rule is broken.
- Existing dispositions, gossip data, progress, `LongTermMemory` flags, and quest memories are
  untouched. `mcaconversations_gossip.dat` loads unchanged.
- 450 automated tests pass, including all 432 that existed before this work, and both locales keep
  full parity.

### Earlier in 1.1.0



**Branching conversations, phase one.** Asking a villager a question no longer pays you for the
click. They answer; you choose what to say back; your reply is what moves hearts. Two topics are
converted in this release — **the day** and **fears** — chosen deliberately as the shallowest and
the deepest, to prove the grammar before the remaining twenty-six follow.

> ### Live verification has NOT been done for this release
>
> This release ships on unit tests, lint and a successful reobfuscated build. The production
> checklist — a real Forge instance with MCA, fresh and upgraded worlds, a dedicated server, two
> concurrent players, all three hub entry modes, relog and restart persistence, MCA: Quests and
> Serene Seasons present and absent, every config off-state, and deliberate farming and
> duplicate-packet attempts — **has not been run**. ForgeGradle's `runClient` is not a substitute,
> because MCA's own mixins misbehave in that runtime. Treat 1.1.0 as untested in play.

### Topics converted so far

| Topic | Depth | Branches |
|---|---|---|
| **The day** | quick | rough / good / ordinary, plus age-appropriate trees and a repair route |
| **Fears** | deep | checked stances, a three-stage arc, a revelation, a boundary scar and its repair |
| **Check-in** | quick | rough / good, from MCA's own greeting menu |
| **Food** | quick | a dietary-trait branch that is never the butt of a rewarded joke, plus ordinary taste talk |
| **Weather** | quick | a storm is concern; a fine morning is small talk. They are not the same conversation |
| **Season** | quick | the turning year, and the four festival days with a once-a-day invitation |
| **Work** | standard | the forty profession lines now *open* a conversation instead of ending one |
| **Work offer** | service | terms and motivation first; the quest screen opens only once you have said yes |
| **Village** | standard | resident pride, honest criticism, and having nowhere to call home |
| **The people** | standard | four personality-flavoured openings; pushing for gossip after discomfort costs |
| **Rumours** | standard | who told you, is it reliable, and who you will tell |
| **News** | standard | the event *type* picks the branch: a death and a wedding are not one conversation |
| **Noticed** | standard | grieving, annoyed at you, elated, or steady — four different answers |
| **Life story** | deep | a chapter they chose to reveal, and a later day that asks how it ended |
| **Dreams** | deep | promise to help, or say honestly that you can't — the two are exclusive |
| **Hopes** | deep | smaller than a dream; naming the first step is what makes it real |
| **Regrets** | deep | absolution and honest company are both valid, and mutually exclusive |
| **Secrets** | deep | a one-shot confidence, a promise you make or refuse, and a callback that proves it held |

The lint's migration ledger is now empty: no starter pays out on the click any more.

### The economy moved

- **Opener rewards are gone from the converted topics.** Asking "how's your day?" was worth +2 or
  +3 on the click; it is now worth nothing. What the day is worth is decided by what you say next,
  and the ceiling for a whole mundane conversation is +2.
- **Losses are real and cannot be refunded.** Brushing off someone's bad day costs a heart; doubling
  down when they object costs two more. Apologising afterwards settles the *tension* — the invisible
  relationship vector — and grants no hearts at all. You cannot pay your way out of having been
  dismissive.
- **Every conversation heart change is guarded.** In order: duplicate-transaction refusal → replay
  policy (full, then half, then nothing for the same decision the same day; milestone outcomes fire
  once ever) → per-conversation budget by depth class → per-villager per-player daily budget → MCA's
  own `rewardHearts`. Positive and negative budgets are separate, so antagonising a villager never
  creates room to earn more back.
- **No universally correct button.** A joke on a bad day lands for a playful or upbeat villager,
  falls flat on a gloomy, sensitive or anxious one, and is politely received by everyone else — no
  dice involved, just who they are. Same for unsolicited advice while they're working.

### Conversations that remember

- **A three-stage arc for fears**, advancing at most one stage per conversation and continuing on
  later days: they name it, you work out what would help, and later you ask how it went.
- **A one-shot revelation.** Getting someone to open all the way about what frightens them fires
  once, ever, and a later conversation says something different because of it.
- **A boundary that can be crossed.** Pressing after a refusal — not the first attempt, the one
  *after* being told no — sets a permanent scar. The topic opens warily from then on, the warm route
  closes, and an honest apology reopens a guarded one without erasing what happened.
- **A mutually exclusive promise.** Pledging to stand with them, or honestly saying you can't, are
  both remembered, are read back differently later, and the first one taken decides it for good.

### Chat mode reaches all of it

- 57 new context-scoped intents with 171 tested utterances, covering every substantive answer in
  both trees.
- **Numbered quick-replies:** a villager who has put a decision to you lists the choices, and `2`
  picks the second one. It is the same `selectAnswer` call the GUI button makes.
- **Live-decision filtering:** while a decision is open, matching scores the choices on the table
  plus the ways out. A weak global topic match can no longer masquerade as your answer; interrupting
  requires an explicit subject change that clears an absolute floor and beats the best contextual
  reading by a margin.

### New for datapacks

`conversations_session`, `conversations_affection_apply`, `conversations_progress_apply` and the
`conversations_progress` condition; a conversation catalog under `conversation_catalog/`; a
per-personality interiority registry under `interiority/`; and `stance` / `arc` fields on
`conversations_check`. Full reference in `DATAPACK.md`, including three MCA engine rules that were
never written down before and that decide how a result must be authored.

### Fixed / completed

- **`Dispositions.baseline` returned zero for every personality.** It now reads real resting values
  from the interiority registry, so a crabby villager genuinely starts colder than a friendly one.
- **Dialogue checks used a personality fit of zero and an arc stage of zero.** Both are real now: a
  check can name a stance family, and the villager's personality moves the outcome by less than one
  tier margin; a check can name an arc, so the seeded roll changes when the relationship does.
- **MCA's GUI submission path validates nothing** — no distance, open-screen, constraint or replay
  check, byte-identical in 7.6.20 and 7.7.0-beta.2. A narrow soft-failing mixin now rejects, *for
  this mod's questions only*, an answer that was never offered, a duplicate submission in the same
  tick, and any attempt to drive a villager that MCA says is mid-conversation with someone else.
- **The progress ledger is migrated field-by-field, never discarded.** Unlike the disposition store,
  a world opened once with a newer build and rolled back keeps its arcs, milestones and promises.

### Known gaps

- Ten answers across three areas still pay out on the click: `feelings`, and every spouse (`us`) and
  `family` starter — that is ten of the twenty-one topics the 0.6.0 hub actually shipped. The debt is
  tracked as a migration ledger inside `ConversationGraphLintTest`, which fails if a topic is
  converted without removing its row, or if a new rewarded starter appears without being listed.
- Interiority profiles carry resting baselines and stance bias only. Wants, boundaries and secret
  pools arrive with the topics that read them — storing state nothing reads is how save files rot.
- Converted results no longer populate MCA's analysis tooltip. That tooltip explains *lottery
  chances*, and a deterministic authored branch has none; fabricating one would be both misleading
  and a mechanics leak. The villager's words are the feedback.

## [1.0.0] - 2026-08-13

**MCA Reborn 7.7 support**, on top of everything 0.9.0 shipped. 0.9.0 does not start on MCA 7.7 at
all; this release fixes that and extends the personality system to 7.7's roster.

### MCA versions

Built and pinned against **MCA `7.7.0-beta.2+1.20.1`**, and verified to still start and run on
**7.6.20**. Every MCA signature this mod consumes is byte-identical across those builds; the one
real drift (`Personality` enum → registry-backed class) is handled without reflection.

**MCA 7.7.0-beta.1 is broken on its own** and this release does not change that: beta.1 ships a
truncated `forge-mca.refmap.json` (19 mixin classes vs beta.2's 31), so MCA's own `MixinLivingEntity`
cannot resolve `isImmobile()Z` in a production runtime and startup dies. Reproduced with MCA as the
only mod installed. Use beta.2 or newer.

### Fixed

- **0.9.0 could not start on MCA 7.7: `JsonSyntaxException: Unknown personality 'witty'`.** MCA 7.7
  renamed four personalities and turned a fifth into a trait, and its native `personality` dialogue
  condition parses values with `orElseThrow` inside `Dialogues.apply`, which has no error
  containment — so 11 conditions in the shipped datapack aborted the whole reload and the world
  would not load. All 49 uses of the native condition are replaced with a new parse-safe
  **`conversations_personality`**, which never throws and matches through the canonical roster, so
  one authored id works on both MCA versions.
- **Forge refused to load on MCA 7.7 without Architectury.** `mods.toml` declared Architectury
  **mandatory** while this mod has zero references to it. MCA 7.7 dropped the dependency, so anyone
  who removed it was blocked by *us*. The declaration is gone; MCA asks for whatever MCA needs.
- **`getPersonality` used `Personality.name()`**, which exists only on the 7.6 enum. It now reads
  `Personality.toString()` — the one accessor the 7.6 enum (`"ODD"`) and the 7.7 registry class
  (`"mca:odd"`) share — and normalises both to `odd`.

### Added

- **The four personalities new in MCA 7.7 — `playful`, `extroverted`, `anxious`, `peaceful` — now
  have written voices**, at 0.9.0's full 80-key shape (conversation lines *and* the 25 chat-mode
  lines). Upstream ships no overlay for any of them, so without this they would speak only the
  generic pool. Each is written to stay distinct from its nearest neighbour (playful vs peppy,
  extroverted vs upbeat, anxious vs introverted, peaceful vs relaxed).
- **`conversations_personality`** dialogue condition (string or array), parse-safe and alias-aware.
- **Client-only locale hook** (`MCAClientMixin`) for future locales: MCA gates per-personality
  dialogue to `en_us`/`ru_ru`, and this widens **only** the language check, only for locales that
  ship complete overlays, re-testing and preserving MCA's voice-pack and online-TTS restrictions.
  Declared in the mixin config's `client` section so a dedicated server never loads a client class.

### Added — Brazilian Portuguese (`pt_br`), complete

**2,453 strings**, covering every shipped key: the 41 UI/fallback/analysis strings, all 724 base
dialogue lines, and all 21 personality overlays — including the age voices (baby babble, toddler,
child, teen) and the full chat-mode vocabulary, so a Portuguese player gets natural-language chat
in Portuguese, not just menus.

Enforced by `LocaleParityTest`: identical key sets to `en_us`, identical placeholder signatures per
key, contiguous `/N` variant runs, no bare `%s`, and every locale declared complete in
`OverlayLocales` must actually ship every overlay. A missing or mistyped key fails the build rather
than rendering a raw translation key mid-conversation.

The `dialogue.chatmode.topic.*` labels are written as noun phrases that read correctly after a
preposition ("Pergunta sobre **o meu trabalho**"), because they are substituted into other lines
rather than shown on their own.

**MCA gates per-personality dialogue to `en_us`/`ru_ru`**, so the overlays would never have been
read. `MCAClientMixin` widens **only** the language check, and only for locales that ship complete
overlays, re-testing and preserving MCA's voice-pack and online-TTS restrictions verbatim. It is
declared in the mixin config's `client` section, so a dedicated server never loads a client class —
verified in the production run.

### Changed — personality migration

| MCA 7.6 | MCA 7.7 | Conversations |
|---|---|---|
| `witty` | `upbeat` | rewritten voice; `witty.dialogue.*` kept as a 7.6 alias |
| `shy` | `introverted` | rewritten voice; `shy.dialogue.*` kept as a 7.6 alias |
| `lazy` | `relaxed` | rewritten voice; `lazy.dialogue.*` kept as a 7.6 alias |
| `grumpy` | `crabby` | rewritten voice; `grumpy.dialogue.*` kept as a 7.6 alias |
| `athletic` | *(now the `mca:athletic` trait)* | kept as a **7.6-only** overlay, not offered as a 7.7 personality |

The four renamed voices were **rewritten, not copied**: Upbeat is genuinely positive rather than
Witty's dry deflection; Introverted is reserved and articulate rather than Shy's uniform stammer;
Relaxed is unhurried-but-competent rather than Lazy's incapability; Crabby is irritable with range —
weary, blunt, and noticeably softer with someone it likes — rather than Grumpy's flat hostility.
Legacy alias namespaces carry the same text under the old prefix, so a world does not change voice
when the server upgrades.

### Changed — hub entry

`replaceChatWithConversations` (boolean) is replaced by **`hubEntryMode`**:

| Mode | MCA's Chat answer | Conversations button |
|---|---|---|
| `ADDITIVE` *(new default)* | unchanged | visible |
| `REPLACE` *(the 0.2.0–0.9.x behaviour)* | opens the Conversations hub | hidden (no duplicate entry) |
| `HIDDEN` | unchanged | hidden |

Named `hubEntryMode` rather than `chatMode` to stay clearly distinct from 0.9.0's **chat mode**
(`enableChatMode`, talking to villagers in normal chat) — the two are unrelated and independent.

Additive mode needs **no mixin**: the button is a datapack answer merged into MCA's `main` question
through the merge MCA already performs for same-named questions. A narrow `Question.getValidAnswers`
injection hides that answer in the other two modes, since MCA filters answers by constraints only.

**MCA's own AI chat is untouched in every mode**, as it always was: it is driven by
`MixinServerPlayNetworkHandler.handleChat` and never routes through the dialogue system.

*Migration:* existing configs land on `ADDITIVE`, a superset of both old settings. Set
`hubEntryMode = "REPLACE"` to restore the old routing exactly, or `"HIDDEN"` for MCA-only menus.

### Repository note

0.9.0 shipped from a state that never reached git: `origin/feature/chat-mode` held the 0.8.0
chat-mode source, while the released 0.9.0 jar contained a further delta (10 code files and 37
resource files). That delta was recovered by decompiling the jar and diffing it against a build of
the branch, and is now in source — verified by rebuilding and decompiling again, which reproduces
0.9.0's classes exactly and packages byte-identical `assets/` and `data/`.

### Tests

319 tests, all passing (0.9.0's source baseline had 297). New: `PersonalitiesTest` (roster, alias
resolution, parse-safety), `HubEntryModeTest` (behaviour matrix + injected `main.json` shape). The
overlay lint now enforces the personality-prefix rule and cross-namespace collision-freedom, and
draws its roster from the shared `Personalities` table so content and code cannot disagree.

### Verified on a production Forge 1.20.1 dedicated server (not `runClient`)

| Build | MCA | Architectury | Result |
|---|---|---|---|
| 0.9.0 | 7.7.0-beta.2 | yes | **crash** — `Unknown personality 'witty'`, never starts |
| 1.0.0 | 7.7.0-beta.2 | no | **starts** |
| 1.0.0 | 7.6.20 | yes | **starts** |

Confirmed on 1.0.0: dialogue conditions/actions register, chat mode reports its configuration at
startup, the datapack reload completes with no warnings, and `MCAClientMixin` is never loaded
server-side.

### Known limitations

- Client-side behaviour (the rendered button, per-personality line selection) is verified by lint
  and by MCA's own resolution rules, not by an automated in-game client run — MCA does not load
  under a ForgeGradle dev runtime.
- MCA 7.7 is itself in beta; the pin will move as upstream stabilises.

## [0.8.0] - 2026-07-15

**Chat mode** — a second frontend to the whole dialogue engine, **on by default**: talk to villagers
by typing in the vanilla chat box (`Agnes, how's your day?`) and they answer in chat, in their own
voice, with the identical heart gates, cooldowns, dispositions, checks, and gossip as the GUI. No
AI/LLM — deterministic, datapack-driven matching (`chat_intents/`, see DATAPACK.md).

### Added
- **Free-text matching engine**: keyword/IDF + phrase scoring with typo tolerance, synonyms,
  negation awareness, and per-answer constraint gating; ~50 shipped intents over greeting,
  chit-chat, profession, village, events, personal, relationship, and stance follow-up content.
- **Natural targeting**: name address (`Agnes, …`) > conversation stickiness (multi-turn follow-ups
  without re-addressing) > look-at > nearest; ambient questions may draw multiple staggered
  responders (`chatModeMaxResponders`).
- **Conversation depth**: open sub-questions (fears/dreams/feelings/us/family) keep context, so
  "You could face it — I'd stand with you." lands as the stance it is.
- **Social layer**: greeting/farewell/"stop talking"/"never mind" controls, graduated in-character
  confusion with topic hints, insult rebukes (ANNOYED + tension, never censors), personality-voiced
  deflections with grumpy/peppy/friendly overlays.
- **Proximity greetings** (`chatModeGreetOnApproach` + `chatModeGreetChance`, default on / 0.35):
  villagers *may* greet you on radius entry with an actual hello (`chatmode.hail` pools; a cold
  brush-off if they dislike you) — a personality-weighted, per-day deterministic coin flip, once per
  villager per player per day, on a budget separate from the GUI's ask-how-you've-been cooldown.
- **Villager attention** (`chatModeTypingAttention` + `chatModeAttentionTicks`, default on / 30 s):
  open the chat box and nearby villagers stop and turn to you (a one-byte client→server ping — the
  mod's first and only client code/packet); a conversation partner stays put facing you until the
  timer lapses after the last exchange. Villagers in danger are never pinned; "bye"/"stop talking"
  release them immediately.
- **Bare-name calls**: `Nataliya?` (typo-tolerant) gets a "Yes?" acknowledgment — the villager turns
  and waits. "Hey <Name>!" greeting-prefixed vocatives address that villager.
- **Heart feedback** (`chatModeShowHeartChanges`, default on): subtle `(+2 ♥)` suffix, shown once
  per exchange, speaker-only.
- **Local chat** (`chatModeLocalChat`, default on, EXPERIMENTAL): opted-in players' chat is
  radius-local unsigned text (still logged to the server console); set false to restore global
  signed chat.
- `/conversations chat on|off|status` (everyone) and op tools `chat debug-ask` / `chat debug <msg>`
  (live scoring introspection).
- Config `[chat]` section (radii, thresholds, delays, mute, format, …; see CONFIG.md); per-player
  opt-in capability; `chat_intents` datapack format incl. third-party synonym packs.

### Fixed
- MCA's "Last interaction analysis" panel showed raw keys (e.g. `analysis.time_min`) for conditions
  MCA ships no label for — added labels for `time_min`/`time_max` ("Time of Day"), `is_pregnant`,
  `rank`, and all 12 `conversations_*` custom conditions.
- Quests integration compiles against MCA: Quests 0.9.x (`QuestDefinition.title` API change).
- In-world test findings: "Hey <Name>!" vocatives and bare typo'd names now resolve; "what are you
  doing / up to", "what's up", "what do you do (for a living)", and "how is everyone doing" now
  match their topics; the topic-hint sentence no longer leaks a raw `greet` key; multi-line answers
  no longer repeat the heart suffix per line; "stop talking" mutes only that villager (not the whole
  village); unmatched chatter near a sticky villager stays silent instead of drawing confused lines
  unless the message actually engages them (question form / second person).

## [0.7.1] - 2026-07-11

A small correctness fix: villagers now address the player by the name they chose in the MCA
character editor instead of their Minecraft username.

### Fixed
- **Villagers use the player's MCA name, not their username.** MCA resolves the spoken player name
  (`%1$s`) from the player's family-tree node, falling back to the account username when that node
  name is blank — which it was, because the chosen name is stored separately (the `villagerName`
  entity-data tag the MCA editor writes). On login we now copy that chosen name into the family-tree
  node (`McaCompat.syncPlayerFamilyName`, called from a new `PlayerLoggedInEvent` handler), so
  `getTranslatable` resolves it correctly for **every** villager line — this mod's dialogue *and*
  MCA's own. No-op for players who never set a name (their username still shows); the write persists
  via MCA's own `FamilyTreeNode.setName` and is overworld-global, so it holds across dimensions and
  relogs. Fixes player names in `conversations_say`, gossip lines, and quest-voice lines alike.

## [0.7.0] - 2026-07-11

The first **RPG-layer** release (1.0.0 track): villagers now carry an internal, per-player
**disposition vector** — Trust, Respect, Warmth, Attraction, Tension, Familiarity — and the deepest
stances resolve through **dialogue checks** with crit/success/partial/rebuff outcomes, piloted on the
fears topic. Hearts remain MCA's only visible relationship economy: the vector never shows as a
number and never grants hearts — it decides which replies open and how they land. Everything
degrades cleanly: all RPG toggles off is exactly the 0.6.0 experience.

### Added
- **Disposition vector.** Six bounded axes per (villager, player), persisted in versioned world data
  (`data/mcaconversations_dispositions.dat`), server-authoritative, pruned on villager death (and
  optionally by age). Axes decay toward a personality baseline with per-axis half-lives — Tension
  fades in ~2 days, Trust lingers ~7; Familiarity never decays. No per-tick processing: decay is
  computed lazily on read. Pre-0.7.0 worlds migrate implicitly (first read = baseline).
- **Farming guards on every vector write.** Per-axis per-day movement cap
  (`dispositionDailyAxisCap`), and repeating the same stance the same day yields full → half →
  quarter → nothing — for losses too, so Tension can't be rage-farmed. Authored deltas are capped at
  ±10 at parse time.
- **Dialogue checks with success tiers.** New `conversations_check` condition: four results per
  stance (crit/success/partial/rebuff) selected deterministically from the disposition axis, hearts
  (capped ±25 — checks refine MCA's economy, never fight it), MCA mood, conversation states, and a
  **seeded roll** (villager + player + check id + half-day time bucket, SplitMix64) — re-opening the
  screen can never re-roll a rebuff into a crit; coming back later legitimately can.
- **New dialogue vocabulary.** Conditions `conversations_disposition` (gate on a decayed axis range)
  and `conversations_check`; action `conversations_disposition_apply` (guarded vector deltas). All
  SafeParse-contained: malformed JSON degrades to never-match/no-op, never a crash.
- **Fears pilot content.** The fears follow-up now offers four stances: *comfort* (warmth check),
  *"You could face it. I'd stand with you."* (trust-gated challenge check), *"Tell me the rest of
  it."* (higher-trust press check), and the existing *share*. Below-gate stances get an in-character,
  **cost-free** guard reply (no rebuff-farming below threshold); rebuffs misfire in character, raise
  Tension, and always exit gracefully. 24 new base lines + 2 stance labels.
- **`[rpg]` config section** — `enableDispositions`, `enableChecks`, `enableCheckTiers`,
  gain/decay multipliers, daily axis cap, stale-days pruning, `debugRpg` logging. Each documented
  with its off-state fallback in CONFIG.md.
- **Age/romance structural gating.** The Attraction axis is layered shut for non-eligible targets
  (children/teens/married-to-someone-else): the read path, the write path, the check assembler, and
  the condition adapter each gate on a **fail-closed** eligibility read (any MCA API failure means
  not eligible).

### Validation
- New unit suites: disposition math (clamp/half-life/convergence), NBT round-trip + versioning
  (missing/future version → empty store, malformed entries skipped), farming guards, seed
  determinism/spread, resolver tier bands and disabled-state formulas, parser rejection paths.
- New content lints: parser-validated disposition/check args; every check id defines **all four
  tiers** with consistent axis/difficulty plus a checks-disabled fallback; tier results never
  dead-end; and `checkedAnswerStatesResolveToExactlyOneResult` — a full state-space simulation
  proving **exactly one result of a checked answer has positive weight in every reachable state**
  (MCA's result selection is weighted-random, verified from `Dialogues.selectAnswer` bytecode, so
  this is the invariant that makes checks deterministic).

### Notes
- MCA's dialogue-response packets are handled on the server main thread (verified from the Forge
  `NetworkHandlerImpl` bytecode: `enqueueWork`) — consequence application is single-threaded.
- Verified from the 7.6.20 jar: `EntityRelationship.isMarriedTo(UUID)` = partner match + married
  state, and MCA's mood names are exactly the seven the lint pins.

### In-world verification checklist (production instance — MCA does not load in the dev runtime)
1. Boot: log shows `conversations_disposition/conversations_check` and
   `conversations_disposition_apply` registered; world creation succeeds.
2. Fears page (25+ hearts, adult villager): all four stances + back render without clipping.
3. Below-gate: with a fresh villager, *challenge*/*press* give the guard reply, cost nothing,
   and stay on the fears page.
4. Re-open scumming: force a rebuff (`debugRpg` shows the tier), close and re-open the dialogue
   within the same half-day — identical tier every time; after a sleep/next half-day it may differ.
5. Farming: repeat *comfort* through the cooldown window across a day — `debugRpg` shows applied
   deltas diminishing full → half → quarter → 0 and the daily cap truncating.
6. Two players build **independent** vectors with the same villager (`debugRpg` read logs).
7. Relog + server restart: vector values persist (`data/mcaconversations_dispositions.dat`).
8. Pre-0.7.0 world: first conversation works, reads baselines, no errors.
9. Kill the villager: its disposition records are dropped from the saved data.
10. Toggles: `enableChecks=false` → stances give the single fallback line; `enableCheckTiers=false`
    → only success/rebuff appear in the debug log; `enableDispositions=false` → guard stances never
    block and checks still resolve (hearts-only); all off → 0.6.0 behavior; `/forge tps` unchanged.

## [0.6.0] - 2026-07-07

The **seasons & deeper-gossip** release: villagers now speak to the time of year and festival days, notice
neighbours moving in and out of the village, and report every kind of news in their own personality's voice.
Two new personal/village topics round it out. Everything is additive — existing saves and datapacks are
unaffected, and every new system degrades cleanly when its feature is off or MCA state is unavailable.

### Added
- **Seasons & holidays.** New `conversations_season` (`{"is": "spring"|"summer"|"autumn"|"winter"}`) and
  `conversations_holiday` (`{"is": "spring_bloom"|"midsummer"|"harvest_festival"|"midwinter"|"none"}`)
  dialogue conditions, plus `season` and `holiday` template variables. Seasons come from **Serene Seasons**
  when it's installed and fall back to a calendar season derived from the world day otherwise; holidays are
  always calendar-based. Tunable under `[world]` (`enableSeasonLines`, `enableHolidayLines`,
  `seasonYearLengthDays`, default 96 to match Serene Seasons).
- **A "How's the season treating you?" topic** under Chit-Chat that remarks on the current festival if one
  is running, otherwise the season.
- **Arrival & departure gossip.** Villagers now notice neighbours **moving into** and **leaving** the
  village — two new `GossipEventType`s (`arrival`, `departure`) detected by diffing the village's full,
  load-independent residency set against a persisted snapshot. A death is never mistaken for a departure,
  a newborn never for an arrival, and a village's first sighting only seeds the set (no false flood).
  Toggles: `[gossip]` `detectArrival` / `detectDeparture`.
- **Gossip in every personality's voice.** All 13 personality overlays now flavour the six village-gossip
  lines (marriage, divorce, death, birth, arrival, departure) — the gloomy villager, the greedy one and the
  peppy one break the same news very differently — each with a variant. Base gossip pools raised to three
  variants apiece.
- **Two new topics.** A personal **"What are you hoping for?"** (opens at 25+ hearts and feeds the existing
  regrets/secrets confidence chain) and a village **"Any rumors going around?"** that surfaces the gossip
  pool from the Village menu.

### Notes
- Serene Seasons is a **soft, reflection-only** dependency: it is not on the compile classpath and is reached
  purely by reflection after a `ModList` check, so an MCA-only install loads and falls back to calendar
  seasons with zero Serene Seasons classes touched.
- Content/unit lints extended in lockstep: `ContentLintTest` pins the season/holiday conditions and their
  value vocabularies; `OverlayLintTest` now requires all 13 overlays to cover the six gossip keys. New tests
  cover the holiday calendar, the season-from-day math, the Serene Seasons bridge seam, the arrival/departure
  residency diff, and the gossip-type round-trip.
- As with prior releases, MCA + Quests don't load under the dev `runClient`, so the MCA-touching behaviour
  (season/weather reads, residency diffing, the new topics in the live UI) is verified by the build/lint/unit
  suite; in-world confirmation is done in a production instance.

## [0.5.0] - 2026-07-07

The **anti-repetition** release: every personality now sounds like itself across the topics you hit most,
and two new event-driven systems give villagers something fresh to react to. Existing saves and datapacks
are unaffected — all of it is additive and degrades cleanly when a feature is off or MCA state is unavailable.

### Added
- **Personality voices, everywhere that matters.** All 13 personality overlays now cover the **core-20
  highest-traffic topics** (greeting, check-in, day, work, village, neighbours, food, the personal openers,
  the deflects, gossip, and "are you happy with us"), each with **2–3 `/N` variants**. Previously overlays
  flavoured only 15 topics with a single line apiece, so most villagers said the identical base line and
  repeated it verbatim on a re-ask. Now a grumpy farmer and a peppy one answer the same question in
  genuinely different voices, and asking twice rarely returns the same words.
- **Conversation states (moods).** A gift, a completed quest, a punch, or a death/birth/marriage in the
  village now leaves a villager in a short-lived mood — `grateful`, `smitten`, `proud`, `annoyed`,
  `grieving`, or `elated` — written as an expiring `mcaconversations.state.<name>` memory. Dialogue gates
  on it with a plain MCA `memory` condition (no new datapack vocabulary); durations are tunable under the
  new `[states]` config group. Generalises the old single `grateful` state. Requires `enableStates`.
- **Weather-aware lines.** A new `conversations_weather` dialogue condition (`{"is": "clear"|"rain"|"storm"}`)
  and a `weather` template variable let villagers speak to the current sky. Gated by the new `[world]`
  config group (`enableWeatherLines`) and the `world` feature flag; storm outranks rain outranks clear.
- **Two built-in topics** surface the new systems in normal play: a **weather** starter under Chit-Chat
  (villagers remark on the current sky) and a **"How have you been, in yourself?"** starter under Events
  that reacts to a villager's current mood — condolences while `grieving`, shared joy while `elated`.

### Fixed
- **CONFIG.md** now documents the `features.enableQuests` toggle shipped in 0.4.0 (previously undocumented),
  alongside the new `[states]` / `[world]` groups.

### Notes
- Content lints extended in lockstep: `OverlayLintTest` now requires all 13 overlays to cover the core-20
  key set (with variant integrity), and `ContentLintTest` pins the `conversations_weather` condition, the
  `world` feature, and the weather value vocabulary. New unit tests cover the state enum/rules and the
  weather bucketing/query.
- Long-tail per-personality topic coverage, seasonal/holiday lines, and deeper gossip are planned for
  follow-up releases.

## [0.4.0] - 2026-07-07

### Added
- **Optional MCA: Quests integration** (only active when the `mcaquests` mod is installed; Conversations
  still loads and works fully standalone). All Quests-touching code sits behind a new
  `compat.QuestsBridge` classloading gate — the exact sibling of the MCA gate — so an MCA-only install
  never loads a `mcaquests` class. Config toggle: `features.enableQuests` (and the
  `conversations_enabled: "quests"` dialogue feature flag).
  - **Conversational awareness.** Four new dialogue conditions — `conversations_quest_available`,
    `conversations_quest_active`, `conversations_quest_ready`, `conversations_quest_completed` (value
    `{ "scope": "this"|"any", "min": N }`) — let villagers react to your quest state. They score 0 when
    Quests is absent.
  - **Drive quests from conversation.** New `conversations_quest_open` action (`{ "mode": "menu" }` or
    `{ "mode": "accept", "quest": "ns:path" }`) plus an "Anything you need doing?" answer on the
    Profession page that opens the villager's Quests menu when they have an offer (and says so gracefully
    when they don't). The Quests mod's own button stays the primary quest UI.
  - **Quests ripple through the village.** Completing a quest writes a permanent
    `mcaconversations.quest.done.*` memory on the giver and seeds a new `QUEST` gossip event other villagers
    tell; failing one writes `mcaconversations.quest.failed.*`.
  - **Personality-voiced quest lines.** A resolver registered with Quests renders quest offer/accept/
    in-progress/ready/complete/failed lines in the villager's Conversations voice (base pool
    `dialogue.conversations.quest.*`; per-personality overlays can be added later — falls back to base, and to
    Quests' own static text when Conversations is off).
  - **Conversations-based quest content.** Registers a `mcaconversations:talk_about` objective (completes when the
    player has the matching Conversations conversation) and a `mcaconversations:unlock_topic` reward (writes a Real
    Talk unlock memory) into the Quests add-on API.
- New content lints pin the four quest condition keys, the `conversations_quest_open` action, the `quests`
  feature, and the `conversations_quest_*` object args; new unit tests cover `QuestsBridge`, the quest parsers,
  the quest memory ids, and the `QUEST` gossip round-trip.

### Notes
- MCA: Quests-side additions ship in that repo (generic add-on seams: `api.QuestDialogueHooks` /
  `QuestDialogueResolver`, `api.ExternalSignalObjective`, `QuestManager.notifyExternalObjective` /
  `eligibleOffers(player, villager)`). Conversations's optional dependency degrades gracefully against a Quests
  build that lacks them.
- Not yet verified in a production instance (MCA + Quests don't load under dev `runClient`); see the
  in-world checklist below.

## [0.3.0] - 2026-07-06

### Changed
- **The hub is now a category menu.** Opening Conversations shows six category buttons — Chit-Chat
  (day, food), Profession (work), Village (village, people), Events (news), Personal (life,
  dreams, fears, feelings, regrets, secret), Relationships (us, family) — instead of the flat
  15-starter list. Each category is its own dialogue question (`conversations.cat.<id>`); starters
  moved into them **verbatim** (conditions, heart deltas, cooldown memory ids, follow-up routing
  all byte-identical — in-flight cooldowns in existing worlds are honored), with only their
  return hop retargeted to the category page. Every page has a "Something else." back answer;
  the hub keeps "Never mind." to exit.
- **Empty categories are hidden**: the Relationships button carries `constraints: "family"`
  (MCA's `family` includes the spouse), so strangers never see it. The other categories are
  never empty — their gating is result-level deflection, exactly as before.
- Third-party answers merged into question `conversations` still work: they surface on the hub after
  the category buttons (uncategorized fallback). Packs can target `conversations.cat.<id>` to join a
  category. See DATAPACK.md's new "The category hub" section.

### Added
- Category lang keys: hub button labels (`dialogue.conversations.<id>`), page headers
  (`dialogue.conversations.cat.<id>`), and per-page back labels; starter button labels moved to
  `dialogue.conversations.cat.<id>.<starter>` with their old text.
- Three content lints: hub answers must stay side-effect-free navigation hops, every
  `conversations.*` question must be reachable from the hub, and answer label keys may not collide
  with question header keys (the pre-0.3.0 hub relied on exactly that double duty).

## [0.2.1] - 2026-07-06

### Fixed
- **Raw translation key shown as the hub header when entering via Chat**
  (`#Gmale.#EPEPPY.#TTEEN.dialogue.chat`): MCA's `next` action builds the header prompt from the
  raw next string, so the Chat→Conversations redirect displays key `dialogue.chat` — which no lang file
  provided (MCA never shows it; vanilla `chat` is an auto question). Added a `dialogue.chat`
  entry-prompt pool (5 variants) plus a personality-flavored entry line in all 13 overlays, and
  lint coverage so the key set can't regress.

## [0.2.0] - 2026-07-06

### Changed
- **MCA's "Chat" button now opens the Conversations hub** (the separate "Conversations..." button is
  gone). Implemented as a soft-fail mixin on MCA's single dialogue routing point
  (`Dialogues.getQuestion`): only the exact `chat` hop is redirected; `chat.topic`/`chat.fail`,
  root/first-meeting, hire, rumors, and story flows are untouched (verified: MCA's `main.json` is
  the only referrer of `next: "chat"`). Config `replaceChatWithConversations` (default true); when off,
  Chat behaves vanilla and the hub is unreachable. MCA's old casual chat line pool is dropped.
- Work topic moved to a dedicated auto question (`conversations.work`) with per-profession responses.

### Added
- **Per-profession work talk for the whole runecraft modpack** (scanned 419 jars): hand-written
  lines for all 13 vanilla trades + nitwit + jobless, MCA's 6 registered professions
  (guard/archer/adventurer/mercenary/cultist/outlaw), all 8 More Villagers professions, Ars
  Nouveau's shady wizard, Chef's Delight chef+cook, Ice and Fire scribe, Vampirism's three, and
  Werewolves' expert — 37 professions × 2 variants. Unknown/future professions get a
  self-personalizing generic line via the new `profession_name` template variable (localized
  client-side).
- **New topics**: food (with trait-flavored replies — vegetarian, lactose intolerance, coeliac,
  diabetes, and a sirben easter egg), neighbors/people (personality-bucketed opinions), and
  secret (tier 3, unlocked by having confided — the payoff for the `confided` flag).
- **Age-appropriate answers**: child and teen villagers answer day/dreams/fears in their own
  voice (and never trigger adult follow-ups or unlock flags).
- **Personality overlays for all 13 personalities** (was 2): athletic, confident, friendly,
  witty, shy, sensitive, greedy, odd, lazy, grumpy, peppy join gloomy and flirty — 14
  high-traffic lines each.
- **Anti-repetition depth**: every say line now has a pool of ≥3 variants (≥2 for
  profession/trait/age precision lines); deflect and hub-prompt pools deepened.
- Lint gates: pinned profession roster + ResourceLocation wellformedness, trait vocabulary,
  dead-lang-key detection, variant-pool floor, overlay coverage floor, mixin-config guard.

### Removed
- `dialogue.main.conversations` button and our `chat.success/20-25` pool extensions (dead after the
  Chat replacement).

## [0.1.0] - 2026-07-06

### Fixed
- **World-creation crash** (`No enum constant forge.net.mca.entity.ai.Chore.CHOPPING`): the day
  topic used invalid `current_chore` values (`chopping`/`harvesting`/`fishing`); MCA's `Chore` enum
  is `NONE, PROSPECT, HARVEST, CHOP, HUNT, FISH` and MCA parses these at datapack load with no
  error containment, so the bad values aborted the resource reload while creating a new world.
  Corrected to `chop`/`harvest`/`fish`.
- Hardened all `conversations_*` condition/action parsers: malformed JSON (in this mod or any datapack
  using our keys) now logs an ERROR and degrades to a no-op instead of crashing the reload the
  same way.
- Content lint now validates condition *values* (chore/mood/personality/age_group/rank/constraints
  vocabularies pinned from the MCA 7.6.26 jar), not just condition keys — the gap that let the
  crash ship.

### Added
- Conversations conversation hub merged into MCA's villager Talk menu (`main` question), with 8 topics
  across three trust tiers plus gossip, spouse, and family branches (10 dialogue JSON files).
- Per-player conversation memory built on MCA's LongTermMemory: first-time / asked-recently /
  revisit-later responses per topic, permanent topic flags, `opened_up`/`confided` unlock flags.
- Custom dialogue conditions registered with MCA: `conversations_enabled`, `conversations_disabled`,
  `conversations_gossip`.
- Custom dialogue actions registered with MCA: `conversations_record` (multi-memory writes),
  `conversations_say` (templated lines: villager/spouse/village names, last gift item, time of day),
  `conversations_gossip_say`.
- Village gossip subsystem: marriage/divorce/birth detection by periodic village scan
  (relationship-snapshot diffing), death detection by event; village-scoped, name-cached,
  per-listener once-only delivery; persisted in `mcaconversations_gossip.dat`.
- Gift gratitude: a server-side mixin on MCA's `BreedableRelationship.acceptGift` records accepted
  gifts to a player capability and a per-player `grateful` villager memory (1 day by default).
- `checkin` greeting answer merged into MCA's `greet` question (memory-aware, once per half day).
- ~40 new `/N` line variants appended to MCA's most-heard dialogue pools (`main`, `greet.success`,
  `greet.fail`, `chat.success`, `story.success`, `shake_hand.success`).
- Personality-flavored overrides for gloomy and flirty villagers.
- `/conversations gossip list|clear` admin command (permission level 2).
- Config toggles per feature plus gossip/gift tunables (see CONFIG.md).
- Content lint test suite: dialogue JSON vocabulary, memory-id namespacing, lang-key coverage,
  variant-sequence integrity.

### In-world verification checklist (production-style instance)
1. Boot log shows `Registered dialogue conditions conversations_enabled/...` and no `Dialogue ... not
   properly formatted` warnings.
2. Villager Talk menu shows "Conversations..." and MCA's own buttons still work.
3. Fears topic: deflects below 25 hearts; `.first` line at 25+; immediate re-ask gives `.again`;
   after `/time add 48000` and re-ask gives `.revisit`.
4. "Us"/"Family" buttons hidden for strangers, visible for spouse/family.
5. Give an accepted gift → within a day, spouse "Are you happy?" references the item by name.
6. Marry two villagers (`/mca` admin) → within ~30s a third villager's "Anything happen around
   here lately?" names the couple, exactly once per player, surviving relog.
7. Kill a villager → death gossip names them after the entity is gone.
8. Toggle each `[features]` config off → related lines degrade to fallbacks, no errors.
9. `/forge tps` stays clean near a large village with scanning enabled.
