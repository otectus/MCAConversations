# 1.7.0 Forge / NeoForge parity

The NeoForge port now contains the 1.7.0 gameplay, content, configuration, networking contracts,
client utilities and regression fixes from the Forge working tree. The history/presentation overlap
fix is present in both renderers. Changes remain uncommitted in both repositories.

## Scope and reconciliation

- All 98 authored topic, profession and voice files match.
- All 1,209 shared main resources match, including every dialogue, locale, generated catalog and
  the protocol resource. Loader metadata, mixin configuration and pack formats remain version-specific.
- The parity audit covers every main Java source, test source, main resource, authored content file,
  and the data/language bodies of all ten tutorial packs. It currently verifies 1,859 identical files
  and 162 explicitly recorded platform adaptations.
- The port retains its NeoForge attachments and ForgeCaps migration, Java 21 SavedData APIs, typed
  payload registration, MCA record/method signatures, scrolling and portrait APIs, and GUI sprites.
  Both response-card styles now share the flat backing and viewport ownership behavior.
- The port's terminal-reply recovery and bystander eligibility fixes were brought into Forge too.
  Recovery now occurs before queued chat options capture their revision, preserving those options.
- Forge gift tracking now uses the accepted transfer point already used by NeoForge. MCA rejects
  inventory-full, disliked and saturated gifts inside `acceptGift`; tracking now runs only before
  `split(1)` and records one item. Binary probes verified that transfer on every configured MCA jar.
- Tutorial packs retain NeoForge's separate data/resource-pack packaging and version-appropriate MCA
  personality names. These are explicit adaptations, not missing content.
- Both releases report mod version 1.7.0 and require choice protocol 3. NeoForge's artifact/mod version
  additionally includes `+1.21.1` to distinguish downloads.

## Verification

Both projects passed:

```sh
./gradlew build verifyGeneratedConversationContent verifyVoiceOverlays --console=plain
```

| Check | Forge 1.20.1 | NeoForge 1.21.1 |
| --- | --- | --- |
| Main tests passed | 1,476 | 1,517 |
| Optional integration probes skipped | 6 | 6 |
| Generated conversation check | Passed | Passed |
| Voice checks | 5 passed | 5 passed |
| MCA manifest, reload and gift probes | 7.6.20, 7.7.0-beta.2, 7.7.1-alpha.2 | 7.7.33, 7.7.36-beta.3 |
| Jar integrity | Passed | Passed |
| NeoForge jar contents | N/A | 1,008 dialogues, 46 locale files; no shaded dependencies |

NeoForge tests booted FML with MCA and the new mod version under Java 21. The utility rendering
regression executes the card draw path and verifies that open utilities do not draw hidden answers,
including at partial opacity, and that Back restores the existing response list. The accepted-gift
probe also checks the compiled mixin's injection point; the Forge build generated the corresponding
SRG mapping for `ItemStack.split`.

## Repeatable parity check

Run from either repository:

```sh
python3 tools/verify_release_parity.py --forge /path/to/Forge/project --neoforge /path/to/NeoForge/project
```

Shared files must match. The [adaptation manifest](parity-1.7.0-adaptations.json) names and hashes every
allowed difference, so an edited adapter, missing file, new unmatched file or changed content fails
the audit. The hashes record this reviewed release; changes to an adapter require another review.
This check establishes source/content agreement and detects drift; it does not prove gameplay by itself.

## Runtime limits

No graphical playthrough or integrated/dedicated-server world smoke test was performed. The six
skipped tests on each loader require opt-in Townstead/Capitals artifacts; optional integrations were
compiled against their pinned APIs and their ordinary unit tests passed, but those real-artifact
probes were not re-run. Platform-specific integration behavior still needs the normal release smoke tests.
