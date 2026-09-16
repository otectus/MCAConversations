# 1.7.1 Forge / NeoForge parity

1.7.1 is the conversation-stability half of the September 2026 conversation stability and social
behavior specification
([`MCAConversations_Conversation_Stability_and_Social_Behavior_Implementation.md`](MCAConversations_Conversation_Stability_and_Social_Behavior_Implementation.md));
the social half is deferred to 1.8.0. Unlike 1.7.0, which was reconciled by a parity
pass at the end, 1.7.1 was mirrored **slice by slice**: each slice landed as one commit in the Forge
repository and one in the NeoForge port before the next slice began, because the work sits exactly
where the two trees diverge — networking, events, config API and a mixin whose MCA target has a
different method name and class shape per platform.

## Scope and reconciliation

- The parity audit covers every main Java source, test source, main resource, authored content file,
  and the data/language bodies of all ten tutorial packs. It now verifies **1,879 identical files**
  and **172 explicitly recorded platform adaptations** (162 of them inherited from 1.7.0).
- All 98 authored topic, profession and voice files under `src/content` match, and both trees carry
  1,212 main resources. The generated dialogue catalogs, locales and the protocol resource are
  byte-identical; loader metadata, mixin configuration and pack formats remain version-specific.
- Every new 1.7.1 behavior is shared Java: the conversation handle, presence index and lifecycle
  coordinator, the distance policy, the movement controller, the danger and lockout rules, the open
  rate limiter and the close-reason vocabulary are the same files in both repositories. The
  adaptations below are confined to the platform seams those shared classes are reached through.
- Both releases report mod version 1.7.1 and require choice protocol 4; the values live only in each
  `gradle.properties`, and the parity check compares them rather than restating them.
- The Forge repository's regenerated `MODMAP.md` is the package inventory for both trees, since the
  package layout is shared; the port carries no `MODMAP.md` of its own.

## Adaptations by slice

Ten files are new adaptations in 1.7.1; the rest are files that already diverged in 1.7.0 and
received the same shared edit on each side. Every one is listed with its reason in
[`parity-1.7.1-adaptations.json`](parity-1.7.1-adaptations.json).

### Slice 1 — stale-lapse fix
Mirrored verbatim apart from two files that already diverge for unrelated reasons:
`client/dialogue/dev/DialogueCardPreviewScreen` and `mixin/client/InteractScreenChoiceMixin`.

### Slice 2 — handle identity and idempotent teardown
No new adaptation. `chat/ChatModeDispatcher`, `chat/ChatModeSession`, `conversation/ConversationSessions`,
`event/ConversationsEvents` and `ConversationSessionTest` already diverged before 1.7.1 and took the
same shared edit on each side; their manifest hashes were refreshed.

### Slice 3 — protocol 4 and wire identity
The largest group, and the reason slice-by-slice mirroring was chosen.

- `network/ChoiceOfferS2C`, `ChoiceClearS2C`, `ChoiceSelectC2S`, `ChoiceReturnC2S` — the port
  declares a typed `CustomPacketPayload` with a `StreamCodec` and `encode(buffer, message)` argument
  order, where Forge keeps the static `encode`/`decode` pair.
- New `network/ConversationOpenedS2C`, `ConversationClosedS2C`, `ConversationPresenceC2S`,
  `ConversationCloseC2S` — typed payloads with the stable type ids `conversation_opened`,
  `conversation_closed`, `conversation_presence` and `conversation_close`; the port has no
  `handle(Supplier<NetworkEvent.Context>)`.
- `network/ConversationsNetwork` — registration through a `PayloadRegistrar` on
  `RegisterPayloadHandlersEvent`, `IPayloadContext` handlers, and `PacketDistributor.sendToPlayer`.
- New `mixin/McaInteractionCloseMixin` — MCA 1.21.1 ships `InteractionCloseRequest` as a **record**
  with `handleServer(ServerPlayer)` under the single `net.conczin.mca` root; Forge has a class with
  `receive(ServerPlayer)` and names both package roots. This guard cannot be one shared file.
- `mixin/NetworkHandlerMixin` — the port targets `net.conczin.mca.network.Network` only.
- New `client/ConversationPresenceTracker` — NeoForge `@EventBusSubscriber`, `ClientTickEvent.Post`
  and `PacketDistributor.sendToServer`.
- `client/dialogue/ClientChoiceController` — sends through `PacketDistributor.sendToServer`.
- `MixinTargetProbeTest` — probes `handleServer` rather than `receive`.
- Port-only `Choice{Offer,Clear,Select}PayloadTest` — byte-order pins, updated for the two-byte
  conversation-handle prefix. These tests exist on the port only and have no Forge counterpart.
- The codec round-trip tests differ in the call they make (`STREAM_CODEC` versus the static pair);
  what they assert is the same.

### Slice 4 — presence lease and distance policy
- `McaConversationsConfig` and `ConfigSpecTest` — the eight new `[conversation]` server settings are
  declared on `ModConfigSpec`.
- `event/ConversationsEvents` — the lifecycle tick runs on `ServerTickEvent.Post`.
- `ConversationSessions`, `mixin/client/InteractScreenChoiceMixin`, `client/ConversationPresenceTracker`
  and `network/ConversationPresenceC2S` took the same edit in files that already diverge.

### Slice 5 — stationary hold, facing and danger
- New `mixin/InteractTaskMovementMixin` — the port names the single
  `net.conczin.mca.entity.ai.brain.tasks.InteractTask` root; Forge names both.
- `event/ConversationsEvents` — `LivingIncomingDamageEvent` and `LivingDamageEvent.Pre` in place of
  Forge's `LivingAttackEvent` and `LivingHurtEvent`. The deduplication rule is the same.
- `compat/mca/McaBinding` and `McaHandles` — the same `STOP_INTERACTING` binding, added to files that
  already diverge.

### Slice 6 — multiplayer handoff
One adaptation: the new `ClientChoiceCloseMessageTest` resolves the language directory through
`TestPaths.of` rather than `Path.of`, which `NeoForgePortLintTest` requires because ModDevGradle runs
the suite from `build/minecraft-junit`.

### Mixin configuration
`mcaconversations.mixins.json` lists the two new common mixins on both sides, alongside the port's
own `PlayerLegacyDataMixin` and without `client.VillagerMessageMixin`.

## MCA jar fleets and the two new probed members

The fleets are the ones in each `gradle.properties` (`mca_probe_versions`):

| Platform | Probed MCA jars |
| --- | --- |
| Forge 1.20.1 | 7.6.20, 7.7.0-beta.2, 7.7.1-alpha.2 |
| NeoForge 1.21.1 | 7.7.33, 7.7.36-beta.3 |

1.7.1 adds two MCA members to the coverage surface:

| Member | Used by | Recorded result |
| --- | --- | --- |
| `InteractTask#followPlayer(VillagerEntityMCA)` (private) | `InteractTaskMovementMixin`, covered by `MixinTargetProbeTest` | Present on all five jars |
| `EntityCommandHandler#stopInteracting()` | `McaBinding.STOP_INTERACTING`, covered by `McaBindingProbeTest` | Present on all five jars |

Both were confirmed during the slice 5 implementation with `javap -p` against all five jars, as
reported by the implementing agent; no artifact of that survey was retained. The probe was shown to be
load-bearing by a deliberate negative run: renaming the injection point failed `MixinTargetProbeTest`
on every jar in both repositories, and was then reverted. `followPlayer` was chosen precisely because it is the one name stable across
all five jars: `InteractTask`'s Behavior lifecycle methods carry legacy names on 7.6.20 and official
names on the other four, so mixing into them would silently no-op on one side of the fleet. The
port's fleet has no 7.6-era jar, so a member proven required there is not proof it exists on Forge,
and vice versa — which is why both probe suites run per platform.

## Protocol 4: the two jars are one deliverable

The choice channel demands exact protocol equality, so a 1.7.0 client is refused by a 1.7.1 server
and the reverse. `network_protocol=4` moved in both repositories in slice 3 and the parity check
compares the two values, but nothing at runtime pairs a Forge jar with a NeoForge one — the
requirement is that **both loaders' 1.7.1 jars publish together**, so that no player can end up with
1.7.1 on one side of a connection and 1.7.0 on the other.

## Repeatable parity check

Run from either repository:

```sh
python3 tools/verify_release_parity.py --forge /path/to/Forge/project --neoforge /path/to/NeoForge/project
```

Run for this document, from both copies of the script, with identical output:

```
{"identical": 1879, "reviewed_adaptations": 172}
Release parity verified: shared files match and every platform difference is accounted for.
```

The [adaptation manifest](parity-1.7.1-adaptations.json) names and hashes every allowed difference and
records the reviewed release and protocol, so an edited adapter, a missing file, a new unmatched file
or changed content fails the audit. The script now reads the expected release and protocol from the
manifest rather than restating them, and takes `--manifest` to audit an older release. The hashes
record this reviewed release; changing an adapter requires another review.

This check establishes source and content agreement and detects drift. **It does not prove gameplay.**

## Verification

Both repositories were built at slice 6 HEAD on 2026-09-16 with
`build verifyGeneratedConversationContent verifyVoiceOverlays`, and both builds succeeded. `jar`,
`conversationsReports`, the two content-drift verify tasks and — on the port — `verifyJarContents`
executed and passed. Both jars declare version 1.7.1 and `protocol=4`, and both new mixin classes are
present in each jar and listed in its `mixins.json`.

**That build did not run the unit suites:** Gradle reported `:test UP-TO-DATE` on both loaders and
reused the results of the builder's slice 6 runs, which were produced from the same committed sources
(Forge results written 01:48:24, port 01:51:03; the release build ran at 01:54). Those reused results
are Forge 1,568 tests and NeoForge 1,611 tests, 0 failures and 0 errors on each, 6 skipped
optional-artifact probes per loader, with `MixinTargetProbeTest`, `McaBindingProbeTest` and
`NoMcaStaticLinkTest` contributing no skips. The full evidence, including log paths and jar sizes, is
in [`RELEASE-1.7.1-LEDGER.md`](RELEASE-1.7.1-LEDGER.md) § Release checks.

The parity audit quoted above was run for this document.

**No in-game session was run.** The runtime acceptance matrix for this release — a villager staying put
and facing the player, minutes of reading, a schedule boundary crossed mid-conversation, a zombie attack
closing the window, the 8–16 block band and the 24-block cut-off, and two real clients for handoff,
across four presentations on integrated and dedicated servers — has **not** been performed; it is listed
as outstanding in the ledger.
