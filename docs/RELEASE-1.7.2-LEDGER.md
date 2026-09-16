# Release ledger — 1.7.2 (NeoForge 1.21.1)

Acceptance ledger for the NeoForge 1.21.1 mirror of the Forge 1.7.2 adoption of **MCA: Reputation
0.6.0**. The Forge commit this mirrors is `a76a3f0` on `feature/reputation-0.6.0` in
`/home/otectus/Projects/MCAConversations`; its own ledger is `docs/RELEASE-1.7.2-LEDGER.md` there and
records the design, which is not restated here. **No in-game run was performed for this release.**
Everything below is either a command actually run against this repository on 2026-09-16, with its log
path, or a static read of the source.

This is the piece the Forge ledger listed as pending ("NeoForge 1.21.1 mirror: pending"). That
statement is still in the Forge tree and was deliberately **not** edited from here.

## Baseline

| | Value |
| --- | --- |
| Branch | `neoforge/1.21.1`, on top of `5b67c5b` (1.7.1 slice 7) |
| Version | `mod_version` 1.7.1 → **1.7.2**, in `gradle.properties` only |
| Network protocol | **4**, unchanged — no payload shape changed, so a 1.7.1 client still pairs with a 1.7.2 server |
| MCA: Reputation API jar | `libs/api/mcareputation-0.4.1-api.jar` → **`libs/api/mcareputation-0.6.0-api.jar`** |
| Provider commit | `otectus/MCAReputation` `fc8575918fefa580d09f635be5a6fc744893bfac` (0.6.0, branch `neoforge/1.21.1`, merge-base `b70f320`) |
| Provider jar SHA-256 | `3b84ffe2cb224e980db8207c5088588642b27dafd0e3a7a719c11e9c1c3c0681` (126,733 bytes, 78 entries, 68 classes) |
| Declared Reputation API version | still **2** — the NeoForge generation of that API — so the `getApiVersion() != 2` handshake is unchanged |
| `neoforge.mods.toml` dependency | `mcareputation`, `type="optional"`, `versionRange="[0.2,)"`, `ordering="AFTER"` — deliberately **not** narrowed |

The API jar is the NeoForge 1.21.1 build, not the Forge one: the two jars carry the same public
surface but are compiled for their own loader, and this line's API generation is 2 where Forge's is 1.
The 0.4.1 jar was deleted, `gradle/sibling-apis.properties` was updated with the version, minecraft,
loader, commit, path and hash above, and `verifySiblingApis` ran ahead of every `compileJava` below
without `-PmcaReputationApiPath` and passed — which is the check that the jar on disk is the one this
ledger names.

The pin is final. An earlier revision of this ledger recorded the pre-rebase commit
`fe717a5` as provisional, because MCA: Reputation's 0.6.0 NeoForge `api/**` was still being rebased
onto `b70f320` (0.5.0 on NeoForge). That rebase has landed at `fc85759`, the API jar was rebuilt from
it, and the commit and SHA-256 above are that build: 126,733 bytes, byte-different from the pre-rebase
jar but with an **identical entry list** (78 entries, 68 classes, same class names), so no source
change followed from the refresh. The refresh was landed as its own commit on top of the adoption
commit, and `compileJava`, `check` and `build` were re-run against it.

## What was delivered

The behaviour is the Forge 1.7.2 behaviour; see that ledger's sections 1–5 for the design. What this
commit establishes is that the same behaviour is the same source on this loader.

| Area | Mirroring |
| --- | --- |
| Typed delivery and deed identity (`recordSignal`, `ReputationSignalIdentity`) | Same source. `ReputationSignalIdentity` is byte-identical to Forge's |
| Capability negotiation (`capabilities(server)`, nine `FEATURE_*` strings, `clearServerState`) | Same source; `ReputationBridge` differs only in its `ModList` import |
| Gossip correction suppression (`gossipStory`) | Same source |
| Profile-aware dialogue (`conversations_reputation_profile`, `ReputationQueryJson#profile`) | `ReputationQueryJson` and `ConversationsMcaRegistrar` are byte-identical to Forge's |
| Context fields (`standing.speaker_knows_player`, `…_recognition_tier`, `…_known_for`) | `ContextKeys`, `ContextSources` and the new `ReputationContextSource` are byte-identical to Forge's |
| Events (`onProfileChanged`) | Same source; the annotation comment and the `SubscribeEvent` import are NeoForge's |
| Content (`src/content/topics/standing.json`, two scenes, two authored route weights) | Authored file byte-identical to Forge's; every generated output regenerated here and byte-identical to Forge's, `pt_br` included |

### Loader differences, and only these

Seven files differ from Forge, and all seven already differed at 1.7.1 for the reasons recorded in
`docs/parity-1.7.1-adaptations.json`. They took the shared 1.7.2 edit and kept their divergence:

| File | Difference |
| --- | --- |
| `compat/ReputationBridge.java` | `net.neoforged.fml.ModList` |
| `compat/reputation/ConversationsReputationCompat.java` | `net.neoforged.neoforge.common.NeoForge`, `NeoForge.EVENT_BUS.register(...)`, the `@EventBusSubscriber` wording, `ResourceLocation.fromNamespaceAndPath` for the `SOURCE` id, and the API gate on **2** rather than 1 |
| `compat/reputation/ConversationsReputationEvents.java` | `net.neoforged.bus.api.SubscribeEvent` and the `NeoForge.EVENT_BUS` / `@EventBusSubscriber` wording |
| `compat/ReputationIntegrationTest.java` | `TestPaths.of` for every repository path (ModDevGradle runs the suite from `build/minecraft-junit`), `neoforge.mods.toml` with `type="optional"`, and the two source-scanned assertions that police `NeoForge.EVENT_BUS` and `@EventBusSubscriber` instead of `MinecraftForge.EVENT_BUS` and `@Mod.` |
| `content/ContentLintTest.java` | `TestPaths.of`, and the overlay personality roster this port lints against |
| `content/TopicPathSimulationTest.java` | `TestPaths.of` |
| `context/ContextKeyOwnershipTest.java` | `TestPaths.of` |

The optional-compat classes stay **manually registered** from
`ConversationsReputationCompat#register()`. No `@EventBusSubscriber` was added: the annotation would
put `dev.otectus.mcareputation.*` on the classpath of an install that has no Reputation, which is the
standalone case. `ReputationIntegrationTest` asserts both halves of that.

### Files changed

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
| Docs | `CHANGELOG.md`, `DATAPACK.md`, `README.md`, `docs/PARITY-1.7.2.md`, `docs/parity-1.7.2-adaptations.json`, this ledger |

There is no `MODMAP.md` on this line, by the convention `docs/PARITY-1.7.1.md` records: the Forge
repository's regenerated inventory covers both trees, since the package layout is shared.

## Release checks

Every command below was run on 2026-09-16 through the quiet wrapper,

```sh
/home/otectus/Projects/.mcmod-tools/gradlew-quiet.sh \
    "/home/otectus/Projects/1.21.1 Ports/MCAConversations_1.21.1" <task>
```

in this order. The logs are the wrapper's own and are listed as written.

| Command | Result | Log |
| --- | --- | --- |
| `compileJava` (after the jar swap and every source change) | PASS | `/tmp/gradle-MCAConversations_1.21.1-compileJava-20260916-162757.log` |
| `generateConversationContent` | PASS — reproduced the committed output exactly | `/tmp/gradle-MCAConversations_1.21.1-generateConversationContent-20260916-162817.log` |
| `verifyGeneratedConversationContent` | PASS | `/tmp/gradle-MCAConversations_1.21.1-verifyGeneratedConversationContent-20260916-162853.log` |
| `verifyVoiceOverlays` | PASS | `/tmp/gradle-MCAConversations_1.21.1-verifyVoiceOverlays-20260916-162919.log` |
| `check` | PASS — `:test` executed, not up-to-date | `/tmp/gradle-MCAConversations_1.21.1-check-20260916-162948.log` |
| `build` | PASS — `verifySiblingApis`, `jar`, `conversationsReports`, `verifyJarContents` executed; `:test` UP-TO-DATE, reusing the `check` results | `/tmp/gradle-MCAConversations_1.21.1-build-20260916-163057.log` |

`generateVoiceOverlays` was **not** run: no voice family changed in this release (the Forge run of it
reported no overlay change), and `verifyVoiceOverlays` passes on the committed overlays.

JUnit results from `build/test-results/test/*.xml`:

| | Value |
| --- | --- |
| Test classes (XML files) | 215 |
| Tests | **1,632 — 0 failures, 0 errors, 6 skipped** |

1.7.1's number on this loader was 1,611, so 21 cases are new — the same 21 the Forge tree gained
(1,568 → 1,589). The six skipped are the same optional-artifact Capitals and Townstead probes as
1.7.1, which need jar paths that were not supplied; they are skipped, not passed.

`build` produced `build/libs/mcaconversations-neoforge-1.7.2+1.21.1.jar`, 4,989,702 bytes. Verified
from the jar: `neoforge.mods.toml` declares `version="1.7.2+1.21.1"`,
`mcaconversations-network.properties` declares `protocol=4`, the optional `mcareputation` dependency
is still `type="optional"` / `[0.2,)` / `AFTER`, the two new scene dialogues are present, and the jar
contains **no** `mcareputation` class — the API jar is compile-only and was not bundled.

### Re-run after the API jar refresh

The vendored jar was refreshed to the post-rebase build (`fc85759`) in a second commit on top of the
adoption commit, and the same wrapper was used again on 2026-09-16:

| Command | Result | Log |
| --- | --- | --- |
| `compileJava` | PASS — `verifySiblingApis` executed against the new hash with no override; `compileJava` itself UP-TO-DATE, because the refreshed jar has an identical ABI | `/tmp/gradle-MCAConversations_1.21.1-compileJava-20260916-165324.log` |
| `check --rerun-tasks -x verifyGeneratedConversationContent` | PASS — `:test` executed fresh | `/tmp/gradle-MCAConversations_1.21.1-check-20260916-165416.log` |
| `build` | PASS — `verifySiblingApis` and `verifyJarContents` executed; `jar` UP-TO-DATE | `/tmp/gradle-MCAConversations_1.21.1-build-20260916-165529.log` |

A plain `check` right after the refresh reported `:test UP-TO-DATE` and was therefore re-run with
`--rerun-tasks`: Gradle's compile-classpath normalisation ignores a jar change that does not move the
ABI, which is exactly what this refresh was, so nothing downstream looked stale. The forced run
reported the same **1,632 tests — 0 failures, 0 errors, 6 skipped**. `jar` stayed UP-TO-DATE and the
release jar is byte-identical to the adoption commit's (4,989,702 bytes): the API jar is compile-only
and never packaged, so a new one cannot change the artifact.

`.mcmod-tools/check_mod.py` is a Forge 1.20.1 tool. Run against this port it reports **60 errors, 0
warnings, 0 notes**, every one of them the expected platform mismatch (NeoForge imports,
`ModConfigSpec`, `ResourceLocation.fromNamespaceAndPath`, Java 21, `pack_format` 34, and the absence
of `META-INF/mods.toml`), and it finds **no missing model, language key or texture**. It is therefore
**not applicable** here rather than failing here — the same conclusion the 1.7.1 ledger recorded. The
content slice's new lang keys in both maintained locales are covered instead by this tree's own
`check`: `LocaleParityTest`, `OverlayLintTest` and `ContentLintTest` all passed.

## Release parity

The manifest for this release is `docs/parity-1.7.2-adaptations.json`: 172 reviewed adaptations, the
same set as 1.7.1, with the seven re-expressed files above re-hashed. No adaptation was added and none
was dropped. Run from the **Forge** copy of the script, with the manifest named explicitly:

```sh
python3 /home/otectus/Projects/MCAConversations/tools/verify_release_parity.py \
    --forge /home/otectus/Projects/MCAConversations \
    --neoforge "/home/otectus/Projects/1.21.1 Ports/MCAConversations_1.21.1" \
    --manifest "/home/otectus/Projects/1.21.1 Ports/MCAConversations_1.21.1/docs/parity-1.7.2-adaptations.json"
```

```
{"identical": 1883, "reviewed_adaptations": 172}
Release parity verified: shared files match and every platform difference is accounted for.
```

The same output comes from this repository's own copy of the script, and from either copy with no
`--manifest` at all: the script picks the newest `docs/parity-*-adaptations.json` in the repository it
itself lives in, and both repositories now carry a byte-identical 1.7.2 manifest — this one with the
adoption commit, the Forge one as `e3c0415`. Until `e3c0415` existed the Forge copy selected 1.7.1 and
failed on the seven files re-expressed here, which is why the explicit `--manifest` form above is the
one recorded; it is correct either way. Nothing in the Forge checkout was edited from here.

## Not run, and not claimed

1. **No in-game session of any kind**, integrated or dedicated. In particular: a villager actually
   raising `known_for_courage` or `known_for_violence`; an apology delivered against a live MCA:
   Reputation 0.6.0 on NeoForge and refused on the second click; the same apology refused to a second
   resident; a superseding apology folding its precursor; and the facet-aware check term moving a
   borderline TRUST check. None of those can be unit-tested here, because the suite deliberately has
   no MCA: Reputation on its runtime classpath.
2. **No run against a second MCA: Reputation build.** Only the 0.6.0 NeoForge API jar was compiled
   against; nothing was launched against 0.4.1 or 0.5.0 on this loader.
3. **The provider pin is final** (`fc85759`), refreshed after the MCA: Reputation NeoForge rebase
   landed. Nothing about it is outstanding.
4. **Nothing was pushed**, and no tag or release was created.

## Deliberate divergences from the spec text

The three the Forge ledger records are inherited unchanged, because the source is shared: the old
gossip candidate format stays; no template values for recognition or dominant traits yet; and the
content slice is two variants, not six.
