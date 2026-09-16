# 1.7.0 review — 2026-09-14

Reviewed the pending 1.7.0 changes against commit `8089f92` (1.6.3), including runtime code,
authored content, generated resources, tests and release documentation. Corrections remain in
the working tree alongside the existing release changes.

## Corrected findings

| Area | Defect and correction |
| --- | --- |
| Reload scheduling | The coordinator queued its completion check before Minecraft could schedule MCA's apply, rejecting a valid reload. A separate completion listener now observes the transaction after MCA. The fixture now uses Minecraft's actual `SimpleReloadInstance` ordering; the old implementation failed that regression. |
| Refused choices | Clearing an offer also removed ownership of its explanation card. The lapse now retains its frontend, stays visible and receives input. Back to topics requests a fresh, server-validated menu using a one-use revision ticket; close invokes MCA's normal screen teardown. A failed recovery announces its updated reason. |
| Submission failures | Native MCA packets for owned questions bypassed the common executor's pinned content and failure cleanup. They now use the same executor as numbered replies. Unrelated old packets cannot dismiss a new question, and chat execution failure clears any successor offer before closing its session. |
| Episode follow-ups | The new advice follow-ups required completed episode states, but selection and continuation only retrieved live episodes. Selection now uses the pair's exact retained episode, continuation validates the required state, and episode actions retain that binding. Finished threads are preserved instead of reopened, and another player cannot inherit the advice callback. |
| Resume binding | A resume scene that does not itself open a thread could lose the episode that caused its admission. The director now carries the validated continuation's episode into scene evaluation. |
| Authored timing | Repeat cooldowns did not enforce the initial wait described by the three advice follow-ups. Explicit days-since-last-talk conditions now enforce those waits. Generated dialogue and voice resources were refreshed. |
| Card geometry and controls | A long first answer could change frame density, and hidden hints could change footer allocation across offers. Density now depends on the window and font, with a stable footer. Utility hit targets respect clipping, hidden answers do not gain pointer focus, and held confirmation cannot skip reset confirmation. |
| History | Reopening the same villager reused screen-local delivery identities and dropped later lines. Each screen now has a distinct conversation identity. Chat shortcut submissions record their translated answer label instead of an internal answer identifier. |
| Content validation | An empty dialogue directory bypassed reference checks. Missing executable questions now reject the staged bundle, including when the entire directory is absent. Test fixtures now provide the dialogue resources their catalogs reference. |
| Diagnostics and documentation | Cross-section failures now retain the winning resource and pack. Corrected stale presentation version comments, obsolete reload claims, completed-episode continuation wording, and history scope documentation. |

## Validation

Final command:

```sh
./gradlew build verifyGeneratedConversationContent verifyVoiceOverlays --console=plain
```

Result: **BUILD SUCCESSFUL**. The main suite passed **1,467 tests**, with **6 optional Townstead
and Capitals probes skipped** because their opt-in jar properties were not supplied. The generated
conversation check and all **5 voice-overlay checks** passed. `git diff --check` passed.

Regression coverage includes actual reload listener ordering, rejected reload retention, absent
dialogue resources, diagnostic attribution, lapse ownership and recovery, stale and replayed return
tickets, return-packet encoding, fixed geometry, and the shipped advice family's transitions through
active, succeeded and remembered states. The binding manifest probes cover MCA 7.6.20,
7.7.0-beta.2 and 7.7.1-alpha.2.

Build output: `build/libs/mcaconversations-1.7.0.jar`.

## Verification limits

No graphical playthrough or integrated/dedicated-server smoke test was run. Unit fixtures use
opaque executable markers where MCA requires a transformed game runtime. The MCA manifest
probes establish binding compatibility, not full in-game behavior; the remaining runtime evidence
is described in [the reload boundary document](RELOAD-TRANSACTION-BOUNDARY.md).

## Follow-up: overlapping utility panels

A user screenshot exposed a rendering defect missed by the initial review: the response list was
still drawn underneath the history and presentation panels. Their translucent backgrounds could
not conceal the underlying answer text and number badges. The renderer now draws either the utility
or the responses in that viewport. Closing the utility restores the same prepared response list.

`DialogueUtilityRenderingTest` executes the card's draw path with a text recorder and transparent
skin. It reproduced the bug for both utilities before the correction, then passed with the fix.
It covers both style profiles, focused and locked responses, partial opacity, question visibility,
balanced clipping and restoration on Back. The follow-up build passed 1,469 tests, with the same
6 optional integration probes skipped. This is draw-call verification, not an in-game screenshot test.
