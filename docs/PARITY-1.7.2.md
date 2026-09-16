# 1.7.2 Forge / NeoForge parity

1.7.2 is a single-slice release: the adoption of **MCA: Reputation 0.6.0**, landed on Forge as one
commit (`a76a3f0` on `feature/reputation-0.6.0`) and mirrored here as one commit on
`neoforge/1.21.1`. Unlike 1.7.1, which was mirrored slice by slice because the work sat on the
platform seams, this release is almost entirely shared Java and shared content: the integration lives
behind `compat.reputation`, and nothing it touches is networking, config, registry or mixin code.

## Scope and reconciliation

- The parity audit covers every main Java source, test source, main resource, authored content file,
  and the data/language bodies of all ten tutorial packs. It now verifies **1,883 identical files**
  and the **same 172 explicitly recorded platform adaptations** as 1.7.1 — four more identical files
  than 1.7.1 (the two new generated scene dialogues, `compat/ReputationSignalIdentity.java` and
  `context/ReputationContextSource.java`), and no new adaptation.
- Seven of those 172 were re-hashed: the five files carrying this release's shared edit that already
  diverged at 1.7.1, plus two lint tests whose vocabulary grew. Every one differs for the reason
  already recorded against it in [`parity-1.7.2-adaptations.json`](parity-1.7.2-adaptations.json) —
  NeoForge dependency APIs on the main sources, the NeoForge test bootstrap on the tests. Nothing was
  added to the manifest and nothing was dropped from it.
- Both releases report mod version 1.7.2 and require choice protocol **4**, unchanged from 1.7.1; the
  values live only in each `gradle.properties`, and the parity check compares them rather than
  restating them. No payload shape changed, so this is not a client/server compatibility break.
- The Forge repository's `MODMAP.md` remains the package inventory for both trees; the port carries
  none of its own, per [`PARITY-1.7.1.md`](PARITY-1.7.1.md).

## What is shared, exactly

Four files are new in this release and **all four are byte-identical across the two trees**:

| New file | Role |
| --- | --- |
| `compat/ReputationSignalIdentity.java` | The pure operation-key computation for a conversation deed |
| `context/ReputationContextSource.java` | Writes the three `standing.speaker_*` context fields |
| `dialogues/conversations.scene.standing.known_for_courage.respond.json` | Generated scene output |
| `dialogues/conversations.scene.standing.known_for_violence.respond.json` | Generated scene output |

So are the files this release changed that did **not** already diverge: `compat/ReputationQueryJson`
(the `profile` and `signal` parsers), `compat/mca/ConversationsMcaRegistrar` (registering
`conversations_reputation_profile`), `context/ContextKeys`, `context/ContextSources`, the authored
`src/content/topics/standing.json`, and every generated artefact it compiles to — the dialogue
catalogs, the scene, beat and chat-intent files, and **both** locales of
`assets/mca_dialogue/lang/`. The generated side was produced here by the
`generateConversationContent` task, never hand-edited, and came out byte-identical to the Forge
output including the `pt_br` keys, which the bilingual authored content emits alongside `en_us`.

## Adaptations re-expressed for this release

| File | 1.7.2 difference |
| --- | --- |
| `compat/ReputationBridge.java` | `net.neoforged.fml.ModList`. The nine capability strings, the three new record types and the SPI additions are shared source |
| `compat/reputation/ConversationsReputationCompat.java` | `net.neoforged.neoforge.common.NeoForge` and `NeoForge.EVENT_BUS.register(...)`; `ResourceLocation.fromNamespaceAndPath` for the `SOURCE` id; the API gate reads **2**, this line's Reputation API generation, where Forge reads 1; `@EventBusSubscriber` wording in the comment |
| `compat/reputation/ConversationsReputationEvents.java` | `net.neoforged.bus.api.SubscribeEvent` and the same annotation/bus wording. `onProfileChanged` itself is shared source |
| `compat/ReputationIntegrationTest.java` | `TestPaths.of` for repository paths, `neoforge.mods.toml` with `type="optional"`, and the source-scanned assertions that police `NeoForge.EVENT_BUS` and `@EventBusSubscriber` rather than `MinecraftForge.EVENT_BUS` and `@Mod.` — including the new profile-listener test, which polices the NeoForge annotation name |
| `content/ContentLintTest.java` | `TestPaths.of` and this port's overlay personality roster; the 1.7.2 change is the one new condition key |
| `content/TopicPathSimulationTest.java` | `TestPaths.of`; the 1.7.2 change is the simulated villager answering the new condition "no" |
| `context/ContextKeyOwnershipTest.java` | `TestPaths.of`; the 1.7.2 change is the seventh context source |

The API version gate is the only *behavioural* difference in the pair, and it is not new: the
NeoForge generation of the MCA: Reputation API declares version 2 because its public event types
extend NeoForge's event base, so a build compiled against v1 cannot link. 0.6.0 does not move it on
either loader, so neither handshake changed in 1.7.2.

Both optional-compat handlers remain **manually registered** from
`ConversationsReputationCompat#register()` on both loaders. An `@EventBusSubscriber` would put a
Reputation event type on the classpath of an install that has no Reputation; the port's annotation
name differs from Forge's, but the rule and the test that enforces it are the same.

## The vendored API jar is per-loader

This is the one artefact that cannot be shared, and it is not compared by the parity tool (which
audits sources, resources and authored content, not `libs/`):

| | Forge 1.20.1 | NeoForge 1.21.1 |
| --- | --- | --- |
| Jar | `libs/api/mcareputation-0.6.0-api.jar` | `libs/api/mcareputation-0.6.0-api.jar` |
| Provider commit | `367a8a0fd46f5a59b48bc9e373c4cb8eda10ce9c` | `fe717a56258f5c0dabda426ffa13d8758b603b42` (provisional — see the ledger) |
| SHA-256 | `d17bb8c279f91da8702de76861c8eb9212eeca62351924d0ce9c714854672d6e` | `4379d6d8a3a5828d0e57018ac952cf6c1d62440f0e646e370d93943fb37e901f` |
| Classes | 69 | 68 |
| Declared API version | 1 | 2 |

Both are pinned by their own `gradle/sibling-apis.properties` and checked by `verifySiblingApis`
before anything compiles. The vendored MCA: Quests jar stays at 1.6.4 on both sides, as it did in the
Forge 1.7.2 manifest.

## Repeatable parity check

Run from either repository:

```sh
python3 tools/verify_release_parity.py --forge /path/to/Forge/project --neoforge /path/to/NeoForge/project
```

Run for this document from the Forge copy of the script, with this repository's manifest named
explicitly:

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

This repository's own copy of the script prints the same line with
`--manifest docs/parity-1.7.2-adaptations.json` and with no `--manifest` at all, since 1.7.2 is now
the newest manifest here. **The Forge copy still needs its own 1.7.2 manifest for the `--manifest`-less
form**: with none, it selects the newest manifest in the repository the script lives in, which there
is still 1.7.1, and fails on the seven files re-expressed above. Copying this manifest into
`/home/otectus/Projects/MCAConversations/docs/` is all that is required; it was not done from the port,
which leaves the Forge checkout untouched.

The [adaptation manifest](parity-1.7.2-adaptations.json) names and hashes every allowed difference and
records the reviewed release and protocol, so an edited adapter, a missing file, a new unmatched file
or changed content fails the audit.

This check establishes source and content agreement and detects drift. **It does not prove gameplay.**

## Verification

The port was built on 2026-09-16 with `compileJava`, `generateConversationContent`,
`verifyGeneratedConversationContent`, `verifyVoiceOverlays`, `check` and `build`, all passing;
`check` executed `:test` (not up-to-date) and reported **1,632 tests, 0 failures, 0 errors, 6 skipped**
optional-artifact probes, against Forge's 1,589 — the same 21 new cases on each side, on top of each
loader's own 1.7.1 total (1,611 here, 1,568 there). `build` ran `verifySiblingApis`, `jar`,
`conversationsReports` and `verifyJarContents`, and produced
`build/libs/mcaconversations-neoforge-1.7.2+1.21.1.jar` declaring version `1.7.2+1.21.1` and
`protocol=4`, with no `mcareputation` class in it. Commands, log paths and jar facts are in
[`RELEASE-1.7.2-LEDGER.md`](RELEASE-1.7.2-LEDGER.md) § Release checks.

**No in-game session was run on either loader.** The runtime acceptance for this release — a villager
raising one of the two new scenes, an apology refused on a second click and to a second resident, a
superseding apology folding its precursor, and the facet-aware check term moving a borderline TRUST
check — has **not** been performed; it is listed as outstanding in both ledgers.
