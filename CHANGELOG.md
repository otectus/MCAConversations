# Changelog

All notable changes to this project will be documented in this file. Format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/); versions follow SemVer.

Compatibility: Minecraft 1.20.1 · Forge 47.x · requires MCA Reborn `[7.6,8)`.
Built against MCA 7.7.0-beta.2; verified on 7.6.20. Architectury is no longer declared (MCA 7.6
asks for it itself; MCA 7.7 dropped it). Optional: MCA: Quests, Serene Seasons.

## [1.2.1] - unreleased

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

## [1.2.0] - unreleased

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

## [1.1.0] - unreleased

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
