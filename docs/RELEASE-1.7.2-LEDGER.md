# Release ledger — 1.7.2 (Forge 1.20.1; NeoForge 1.21.1 mirror pending)

Acceptance ledger for adopting **MCA: Reputation 0.6.0** in MCA: Conversations, against section 16.2
of `MCAReputation/docs/MCA_Reputation_0.6.0_Phase_1_Implementation_Plan.md` and the audit findings in
its section 3.1. **No in-game run was performed for this release.** Everything below is either a
command that was actually run in this repository on 2026-09-16, with its log path, or a static read of
the source. Nothing here substitutes for the runtime acceptance listed as outstanding.

The NeoForge 1.21.1 mirror is **not done**: `origin/neoforge/1.21.1` still vendors
`mcareputation-0.4.1-api.jar` and still reads `mod_version=1.6.3`. It is a separate piece of work,
recorded as pending below, and `docs/parity-1.7.1-adaptations.json` / `docs/PARITY-1.7.1.md` were
deliberately **not** touched — a parity manifest that claims 1.7.2 before the port exists would be
false.

## Baseline

| | Value |
| --- | --- |
| Branch | `feature/reputation-0.6.0`, off `main` at `375fbba` (1.7.1) |
| Version | `mod_version` 1.7.1 → **1.7.2**, in `gradle.properties` only |
| Network protocol | **4**, unchanged — no packet format changed, so a 1.7.1 client still pairs with a 1.7.2 server |
| MCA: Reputation API jar | `libs/api/mcareputation-0.4.1-api.jar` → **`libs/api/mcareputation-0.6.0-api.jar`** |
| Provider commit | `otectus/MCAReputation` `367a8a0fd46f5a59b48bc9e373c4cb8eda10ce9c` (0.6.0, branch `feature/0.6.0-profiles`) |
| Provider jar SHA-256 | `d17bb8c279f91da8702de76861c8eb9212eeca62351924d0ce9c714854672d6e` (79 entries, 69 classes) |
| Declared Reputation API version | still **1**, so the `getApiVersion() != 1` handshake is unchanged |
| `mods.toml` dependency | `mcareputation`, `mandatory=false`, `versionRange="[0.2,)"`, `ordering="AFTER"` — deliberately **not** narrowed |

The vendored jar was copied from the provider's own `build/libs/`, the 0.4.1 jar was deleted, and
`gradle/sibling-apis.properties` was updated with the version, commit, path and hash above.
`verifySiblingApis` ran ahead of every `compileJava` below and passed, which is the check that the jar
on disk is the one this ledger names.

## What was delivered

### 1. Typed delivery and a deed identity that cannot be farmed (§3.1, §16.2)

`compat/reputation/ConversationsReputationCompat#recordSignal` no longer calls `record(...)`. It
builds one `IncidentDelivery` and takes one of four paths, in order of what the installed build
advertises: `deliverProfiled(ProfiledDelivery.superseding(...))`, `recordSuperseding(...)`,
`deliver(...)`, or plain `record(...)` on a pre-0.4.1 build. Typed outcomes are read honestly:
`APPLIED`, `ACCEPTED_NO_PUBLIC_INCIDENT` and `DUPLICATE` are terminal-accepted and reported as
recorded; every `REFUSED_*` is false, with the retryable ones named at debug.

The identity changed, which was the audit finding. 1.7.1 keyed by
`conversation:<villager>:<player>:<decision>`; the villager made the same apology payable again to a
different resident, and the decision alone made a second unrelated grievance unaddressable. The key is
now computed by the new pure class `compat/ReputationSignalIdentity`:
`conversation:<decision>` or `conversation:<decision>:<boundIncidentId>`, lower-cased, bounded to 256
characters and **digested rather than truncated** past that. The player and community are already part
of Reputation's receipt identity (`namespace + player + community + operationKey`), so neither is
repeated in the key. The request's own `dedupeKey` is set to the same string, as Reputation §11.2
requires of a keyed delivery.

Binding and supersession are separate and both authored:

- `"binds": "known_incident"` selects the exact eligible grievance through
  `selectIncidents(..., SpeakerContext)` — a **real** speaker context, so the speaker-less overload's
  fail-closed behaviour is never relied on — filtered to `ACTIVE`, known to the speaker, and actually
  negative. With nothing eligible known, **nothing is recorded**.
- `"supersedes": "<earlier decision>"` finds the precursor through `findReceipt(...)` on the earlier
  decision's own operation key (read-only, no scan, no newest-deed guess) and delivers with a
  `SupersedeSpec`, so a fuller apology after a partial one totals one figure.

The villager is also added as a real `witness(...)` on the request: a deed the speaker was present for
may honestly be recorded as witnessed, which is not the same as labelling a private exchange public.

The shipped amends content now exercises it:
`data/mcaconversations/dialogues/conversations.topic.standing.incident.respond.json` carries
`"binds": "known_incident"` on its `mcareputation:public_apology` signal. That page is only reachable
behind an active, crime-tagged incident known to the speaker, so the binding always has a precursor
to find there.

### 2. One opinion term, the resolved one, and capability negotiation (§13.2, §14.4, §16.2)

`ReputationBridge#publicStandingFit` still reads **exactly one** term — `opinionBias` or `checkBias`,
chosen, never summed — and 0.6.0's `getOpinionBias` is now itself the facet-aware answer, so no facet
bias was added beside it. The ±8 clamp is unchanged, on both sides.

The 1.7.1 `getMethod("getOpinionBias", …)` probe is gone. The adapter reads
`McaReputationApi.capabilities(server)` into a snapshot (weak server reference, 100-tick TTL, cleared
on world stop through the new `ReputationQueries#clearServerState` hook) and gates on capability
strings. The always-loaded bridge spells those nine strings out itself — it may not name a Reputation
type — and `ConversationsReputationCompat#featureStringsAgree()` checks each against
`ReputationCapabilities.FEATURE_*` at registration, disabling the integration on a rename rather than
reading every optional operation as permanently absent. A build too old to have `capabilities` is
absorbed by one `catch (Throwable)`: no features, which is the pre-0.4.1 behaviour.

The five 0.6.0 profile strings are treated exactly as Reputation documents them — advertised only
while the feature is live — so an unsupported scope answers *unavailable* before the query runs, and
never reads as a negative answer about the player.

### 3. Gossip (§16.2)

`gossipCandidate`/`ExternalGossipCandidate` are **kept** as the source of the candidate list, and the
reason is in Reputation's own API contract: 0.6.0 leaves both untouched precisely so an adapter keeps
its baseline behaviour, and the `gossipStory` surface answers for the villager's newest story only —
there is no filtered *list* to move to. What was adopted is the half that matters for §16.2's
requirement: where `FEATURE_GOSSIP_STORY` is live, `gossipStory` is consulted and an incident it
reports as a **correction** (superseded or disproven) is dropped from the candidate list, so a
resolved story is never retold in its old accusatory form. `GossipCandidate`'s record shape is
unchanged, so the per-teller told-memory keeps keying on the incident id.

### 4. Profile-aware dialogue (§16.2)

Two consumers, both registered unconditionally so a suite-authored pack still loads on an
MCA-only install:

- **`conversations_reputation_profile`** — parsed by the new `ReputationQueryJson#profile`, scored
  through `ReputationBridge#matchesProfile`, answered by
  `matchesSpeakerProfile(server, player, Entity villager, ProfileQuery)` for `"scope": "speaker"`
  (the default) and `matchesProfile(...)` for `"scope": "community"`. The bridge's answer is
  three-valued (`MATCH` / `NO_MATCH` / `UNAVAILABLE`); the condition scores 1 only on `MATCH`, so an
  unanswerable question runs the authored fallback. A missing or unresolvable speaker is
  `UNAVAILABLE` and is **never** re-asked of the community.
- **Three context fields** — `standing.speaker_knows_player`,
  `standing.speaker_recognition_tier`, `standing.speaker_known_for` — written by the new
  `context/ReputationContextSource` from `getVillagerProfileDetailed`, in the shape the existing
  Capitals source established. Unavailable without the mod or the profile layer, so a gated scene
  hides itself.

Content slice, authored in `src/content/topics/standing.json` and regenerated:

| Scene | Gate | What the villager does |
| --- | --- | --- |
| `known_for_courage` | `standing.speaker_knows_player` true **and** `standing.speaker_known_for` has `mcareputation:bravery` | Repeats what reached them about the player standing their ground, as hearsay they will not inflate |
| `known_for_violence` | `standing.speaker_knows_player` true **and** `standing.speaker_known_for` has `mcareputation:violence` | Says to the player's face that they know of harm done, and that they do not know the whole of it |

Both are `epistemic: reported`, so the compiler attaches the anonymous-source referent MCA:
Conversations' §10.3 frame rule requires; neither grants warmth, hearts or familiarity. Two further
uses of the dialogue condition ship in the hand-authored hub
(`dialogues/conversations.cat.village.json`): a `+40` weight on the praise route for a speaker who
knows the player for bravery, and the same on the wary route for violence.

### 5. Events (§15)

`onTierChanged` is unchanged. `onProfileChanged(ReputationProfileChangedEvent)` was added **because
there is a consumer**: the check term is memoized for 20 ticks in `ReputationBridge`, and since 0.6.0
the number behind it is the villager's facet-aware opinion, so recognition or a facet moving changes it
while the score, tier and standing revision all stand still. The handler only invalidates
(`invalidateStandingCache(playerId)`); nothing is said out loud, and a quiet background change never
becomes a remark. Both handlers remain in the guarded package and are registered by hand from
`ConversationsReputationCompat#register()` — no `@Mod.EventBusSubscriber`, asserted by test.

### 6. Files changed

| Area | Files |
| --- | --- |
| Build and pin | `gradle.properties` (version only), `gradle/sibling-apis.properties`, `libs/api/mcareputation-0.6.0-api.jar` added, `libs/api/mcareputation-0.4.1-api.jar` deleted |
| Always-loaded bridge | `compat/ReputationBridge.java`, `compat/ReputationQueryJson.java`, `compat/ReputationSignalIdentity.java` (new) |
| Guarded adapter | `compat/reputation/ConversationsReputationCompat.java`, `compat/reputation/ConversationsReputationEvents.java` |
| Registration | `compat/mca/ConversationsMcaRegistrar.java` |
| Context | `context/ContextKeys.java`, `context/ContextSources.java`, `context/ReputationContextSource.java` (new) |
| Content sources | `src/content/topics/standing.json` |
| Generated content (committed) | `dialogues/conversations.scene.standing.known_for_courage.respond.json` and `…known_for_violence.respond.json` (new), `dialogues/conversations.cat.village.json`, `conversation_scenes/generated.json`, `conversation_beats/scene_generated.json`, `chat_intents/scene_generated.json`, `assets/mca_dialogue/lang/{en_us,pt_br}.json`, `src/test/resources/generated_matcher_fixtures.tsv` |
| Hand-authored content | `dialogues/conversations.topic.standing.incident.respond.json` (the `binds` key) |
| Tests | `compat/ReputationIntegrationTest.java`, `content/ContentLintTest.java`, `content/TopicPathSimulationTest.java`, `context/ContextKeyOwnershipTest.java` |
| Docs | `CHANGELOG.md`, `DATAPACK.md`, `README.md`, `MODMAP.md` (regenerated), this ledger |

## Release checks

Every command below was run on 2026-09-16 through the quiet wrapper,
`/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh /home/otectus/Projects/MCAConversations <task>`,
in this order. The logs are the wrapper's own and are listed as written.

| Command | Result | Log |
| --- | --- | --- |
| `compileJava` (before any source change, against the swapped 0.6.0 jar) | PASS | `/tmp/gradle-MCAConversations-compileJava-20260916-144922.log` |
| `compileTestJava` | PASS | `/tmp/gradle-MCAConversations-compileTestJava-20260916-150314.log` |
| `generateConversationContent` (final run) | PASS | `/tmp/gradle-MCAConversations-generateConversationContent-20260916-150813.log` |
| `generateVoiceOverlays` | PASS — no overlay change | `/tmp/gradle-MCAConversations-generateVoiceOverlays-20260916-150345.log` |
| `check` | PASS | `/tmp/gradle-MCAConversations-check-20260916-150902.log`, re-run after a last debug-log edit: `/tmp/gradle-MCAConversations-check-20260916-151419.log` |
| `verifyGeneratedConversationContent` | PASS | `/tmp/gradle-MCAConversations-verifyGeneratedConversationContent-20260916-150928.log` |
| `verifyVoiceOverlays` | PASS | `/tmp/gradle-MCAConversations-verifyVoiceOverlays-20260916-150936.log` |
| `build` | PASS | `/tmp/gradle-MCAConversations-build-20260916-151138.log`, re-run as above: `/tmp/gradle-MCAConversations-build-20260916-151454.log` |

`check` reported `:test` executed, not up-to-date, in both runs. JUnit results from
`build/test-results/test/*.xml`, identical across them:

| | Value |
| --- | --- |
| Test classes (XML files) | 207 |
| Tests | **1,589 — 0 failures, 0 errors, 6 skipped** |

1.7.1's number was 1,568, so 21 cases are new. The six skipped are the same optional-artifact Capitals
and Townstead probes as 1.7.1, which need jar paths that were not supplied; they are skipped, not
passed.

`build` produced `build/libs/mcaconversations-1.7.2.jar`, 5,031,679 bytes (the first run, before the
last debug-log edit, produced 5,031,581). Verified from the jar:
`mods.toml` declares `version="1.7.2"`, `mcaconversations-network.properties` declares `protocol=4`,
the optional `mcareputation` dependency is still `mandatory=false` / `[0.2,)` / `AFTER`, and the jar
contains **no** `mcareputation` class — the API jar is compile-only and was not bundled.
`build` also ran `conversationsReports`, and `:test` was `UP-TO-DATE` in that invocation, reusing the
`check` results above.

`python3 /home/otectus/Projects/.mcmod-tools/check_mod.py MCAConversations` (run from
`/home/otectus/Projects`, which is where it resolves a project name): **0 errors, 0 warnings, 0
notes**. This matters for this release because the content slice added lang keys in both maintained
locales.

`MODMAP.md` was regenerated with `python3 /home/otectus/Projects/.mcmod-tools/modmap.py
MCAConversations`; everything below its `AUTO:END` marker is unchanged (the generator's stray extra
blank line under the marker was removed).

### New and changed tests

Added to `ReputationIntegrationTest`: profile-condition parsing (every field, the unscoped default,
the empty query, fourteen malformed shapes, the sixteen-facet bound); the three-valued profile answer
and its capability gating (absent mod, profileless build, per-scope capability, throwing backend, and
that only `MATCH` scores); the bounded speaker-profile view; signal-action parsing (every field,
defaults, seven malformed shapes); the operation identity (no villager in the key, the same stage is
one operation, two grievances stay separable, case-insensitivity, digest-not-truncate, a missing
decision is a pack error); source-scanned assertions that the adapter delivers, supersedes through
`ProfiledDelivery`/`recordSuperseding`, finds its precursor by receipt, supplies a `SpeakerContext`
and a witness, has no 1.7.1 villager-keyed dedupe key and no reflective opinion probe; that exactly
one standing term is read; and that the profile listener exists, invalidates, and says nothing.

Changed because the vocabulary grew: `ContentLintTest` (the new condition key),
`TopicPathSimulationTest` (the simulated villager answers the new condition "no"),
`ContextKeyOwnershipTest` (seven context sources, not six).

## Not run, and not claimed

1. **No in-game session of any kind**, on either an integrated or a dedicated server. In particular:
   a villager actually raising `known_for_courage` or `known_for_violence`; an apology delivered
   against a live MCA: Reputation 0.6.0 and refused on the second click; the same apology refused to a
   second resident; a superseding apology folding its precursor; and the facet-aware check term
   moving a borderline TRUST check. Each of those is the point of the release and none of them can be
   unit-tested here, because the suite deliberately has no MCA: Reputation on its runtime classpath.
2. **No run against a second MCA: Reputation build.** The capability negotiation is written to degrade
   on an older one and the fallbacks are unit-asserted at the bridge, but only 0.6.0's API jar was
   compiled against and nothing was launched against 0.4.1 or 0.5.0.
3. **NeoForge 1.21.1 mirror: pending.** The port still vendors the 0.4.1 API jar and reads 1.6.3. Its
   own adoption commit, its `mod_version` bump and a 1.7.2 parity manifest
   (`docs/PARITY-1.7.2.md`, `docs/parity-1.7.2-adaptations.json`,
   `tools/verify_release_parity.py --forge --neoforge`) are all outstanding. Nothing in
   `docs/PARITY-1.7.1.md` or `docs/parity-1.7.1-adaptations.json` was touched.
4. **Nothing was pushed**, and no tag or release was created.

## Deliberate divergences from the spec text

1. **The old gossip candidate format stays.** §16.2 asks to "adopt `gossipStory`/semantic revisions";
   0.6.0's own API contract says `gossipCandidate` and `ExternalGossipCandidate` are untouched so an
   adapter keeps its baseline, and the story surface answers for one newest story rather than a
   filtered list. `gossipStory` is adopted for the part that changes behaviour — corrections are not
   retold — and the candidate list still comes from the older call. Carrying `storyRevision` into the
   told-memory would change that memory's shape and is not done here.
2. **No template values for recognition or dominant traits yet.** §16.2 also asks for bounded template
   values. The same facts are exposed as three context fields and through the new condition, which is
   what the content slice needs; adding `REPUTATION_*` template variables (and their fallback keys in
   both locales) is left for a later release.
3. **The content slice is two variants, not six.** §16.2 lists neutral-stranger, recognized-stranger,
   reliable, brave, notorious and mixed variants. Two facet-gated scenes plus two authored weights on
   existing routes ship here; the recognition-tier field is in place and documented for the rest.
