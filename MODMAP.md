# MCA: Conversations

Machine-generated map of this mod. Read this first when picking the project up.

## Build

| | |
|---|---|
| Minecraft | 1.20.1 |
| Forge | 47.4.10 |
| Java | 17 |
| Gradle | 8.8 |
| ForgeGradle | [6.0,6.2) |
| Mappings | official 1.20.1 |
| Parchment plugin | no |
| Mixins | yes |
| Datagen wired into resources | yes |

## Source layout

```
dev.otectus.mcaconversations                         3 files
dev.otectus.mcaconversations.chat                    22 files
dev.otectus.mcaconversations.chat.group              5 files
dev.otectus.mcaconversations.check                   7 files
dev.otectus.mcaconversations.client                  2 files
dev.otectus.mcaconversations.client.dialogue         22 files
dev.otectus.mcaconversations.client.dialogue.dev     3 files
dev.otectus.mcaconversations.client.townstead        2 files
dev.otectus.mcaconversations.command                 2 files
dev.otectus.mcaconversations.compat                  31 files
dev.otectus.mcaconversations.compat.capitals         3 files
dev.otectus.mcaconversations.compat.mca              4 files
dev.otectus.mcaconversations.compat.quests           7 files
dev.otectus.mcaconversations.compat.reputation       2 files
dev.otectus.mcaconversations.compat.seasons          1 file
dev.otectus.mcaconversations.compat.townstead        3 files
dev.otectus.mcaconversations.context                 19 files
dev.otectus.mcaconversations.conversation            33 files
dev.otectus.mcaconversations.court                   5 files
dev.otectus.mcaconversations.debug                   3 files
dev.otectus.mcaconversations.disposition             9 files
dev.otectus.mcaconversations.event                   1 file
dev.otectus.mcaconversations.gift                    4 files
dev.otectus.mcaconversations.gossip                  11 files
dev.otectus.mcaconversations.history                 35 files
dev.otectus.mcaconversations.hub                     5 files
dev.otectus.mcaconversations.identity                10 files
dev.otectus.mcaconversations.interiority             2 files
dev.otectus.mcaconversations.locale                  3 files
dev.otectus.mcaconversations.mixin                   5 files
dev.otectus.mcaconversations.mixin.client            6 files
dev.otectus.mcaconversations.network                 4 files
dev.otectus.mcaconversations.personality             3 files
dev.otectus.mcaconversations.profession              4 files
dev.otectus.mcaconversations.progress                13 files
dev.otectus.mcaconversations.scene                   19 files
dev.otectus.mcaconversations.season                  3 files
dev.otectus.mcaconversations.state                   5 files
dev.otectus.mcaconversations.template                8 files
dev.otectus.mcaconversations.util                    1 file
dev.otectus.mcaconversations.village                 10 files
dev.otectus.mcaconversations.world                   1 file
```

## Registered content (0 entries)

_No DeferredRegister entries found._

## Data generation

_No datagen providers detected — assets and data JSON are hand-written._

## Resources on disk

| kind | count |
|---|---|
| blockstates | 0 |
| block models | 0 |
| item models | 0 |
| block textures | 0 |
| item textures | 0 |
| recipes | 0 |
| block loot tables | 0 |
| block tags | 0 |
| item tags | 0 |
| biome modifiers | 0 |
| lang files | en_us.json, pt_br.json |

Run `check_mod.py` for a full consistency check (missing models, lang keys, textures).

<!-- MODMAP:AUTO:END — everything below is hand-maintained and preserved -->

## Current focus

_What you are working on right now. One or two lines._

## Roadmap

- [ ] …

## Decisions

**Capitals binding:** MCA Capitals is bound reflectively via `compat/capitals/CapitalsBinding.java`, with no direct imports of its classes. The capital name is the MCA village's name; the event source is chronicle diffs and court snapshots polled together in `court/CourtNewsPoller.java`. The probe jar location is supplied as a command-line property `-PcapitalsJar=<path>` (read via `project.hasProperty` in `build.gradle` ~:262 by the `capitalsProbeTest` task); no probe-version list is stored in `gradle.properties` (binding versions are hard-coded in the binding itself, as they are with MCA).

## Content pipeline

`src/content/{topics,professions,voices}` are the hand-authored sources for the branching/dynamic
corpus. Two Gradle tasks compile them into committed runtime resources (`build.gradle`):

- `generateConversationContent` runs `authoring.ContentCompiler` (`src/content` → `src/main/resources`),
  owning `conversations.scene.*` dialogues, `scene_*.json` contracts/intents and the five
  narrative-template directories.
- `generateVoiceOverlays` runs `authoring.VoiceFamilyCompiler` (`src/content/voices` →
  `src/main/resources/assets`), expanding the six authored voices into every personality overlay
  namespace in both locales.

Neither is wired into `processResources`; the compiler lives in the test source set and the generated
output is committed. `verifyGeneratedConversationContent` and `verifyVoiceOverlays` are the drift
gates — separate `Test` tasks that recompile into a scratch tree and byte-compare it back. `check`
does not depend on either (see `build.gradle`), so CI invokes them by name alongside `build`.

`data/mcaconversations/dialogues/conversations.cat.*.json` (the six category starter files) and
`conversations.json` are **not** part of this pipeline — they are hand-authored MCA question banks
that the generators never read or write (confirmed against `ContentCompiler`'s own ownership doc
comment and by grepping `src/test/java/.../authoring/` for `conversations.cat`, which finds no
generator reference to those files, only test fixtures that use the ids).

## Known issues

_Bugs you know about but have not fixed, with the symptom and any lead._
