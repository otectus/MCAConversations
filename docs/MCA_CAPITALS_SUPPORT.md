# MCA Capitals: implemented support and evidence

This integration is a read-only soft dependency. Conversations does not grant titles,
submit petitions, change allegiance, impose taxes, issue decrees, or alter diplomacy.
Conversations performs no such game-state mutations. Dialogue distinguishes an
opinion or a proposed arrangement from an action that actually happened.

## Releases inspected

The stabilization pass inspected these released jars with `javap` and the existing
reflective binding manifest, rather than assuming the public repository matched a release:

| Target | Artifact | Published file |
| --- | --- | --- |
| Minecraft 1.20.1 Forge | `mcacapitals-1.3.6.jar` | [CurseForge file 8795147](https://www.curseforge.com/minecraft/mc-mods/mca-capitals/files/8795147) |
| Minecraft 1.21.1 NeoForge | `mcacapitals-1.3.5.jar` | [CurseForge file 8794248](https://www.curseforge.com/minecraft/mc-mods/mca-capitals/files/8794248) |

SHA-256 values of the inspected downloads, in the same order:

```text
0f4a4b51f3cd31a23c3e1ca73617c077c4aff0511d9f7adf49fccb226e752bb1
10ad0456d86d99fce36005cc504d395d893e295f4f164efb85cc455edd160e3c
```

The [public source repository](https://github.com/MajesttyX/mca_capitals_addon) at
`89101881ea3ed683f03b8d4d3c8e5d21f0522908` still identifies itself as 1.1.0. It is
useful background, but was not treated as evidence for the newer binary API.
These are inspected compatibility targets, not a claim that no newer release exists.

## Available state and its limits

The released `CapitalRecord` exposes the sovereign, consort, heir, court officers,
player sovereign identity, capital state, royal household and guard membership,
disgrace, mourning, and chronicle entries. The bridge also resolves titles and ranks,
house identity and words, Crown standing, declared player allegiance, and diplomatic
relations. All imported values are flattened into Conversations-owned records; no
Capitals class is statically linked into the mod.

Five additional context facts use those already bound APIs:

| Context field | Meaning |
| --- | --- |
| `capital.sovereign_named` | A villager or player sovereign identity is present. |
| `capital.sovereign_female` | Capitals' sovereign gender flag; only used together with sovereign presence. |
| `capital.consort_named` | A consort identity is present. |
| `capital.house_present` | The speaker has a resolved house name; an unavailable lookup is not a negative answer. |
| `capital.house_words_present` | The speaker has nonblank house words, so a motto may actually be quoted. |

`capital.crown_standing` describes the **speaker**, not the player. Content no longer
accuses or praises the player using the villager's standing. Player allegiance and
player sovereignty remain separate, supported facts.

No tax rate, treasury ledger, active general law text, petition queue, public-works
schedule, unrest level, ruler approval score, or private family discussion is exposed
by this bridge. Conversations therefore discusses taxation, civic priorities,
petitions, lawmaking, and dissent as views, questions, or proposals. It never reports
a fabricated levy, completed building, riot, secret agreement, or petition result.
Role-specific law conversations use the actual Master of Laws office. Conversations
does not equate holding that office with a particular justice mechanic being unlocked.

## Narrative coverage

All 42 previously authored Capitals scenes were reviewed and rewritten across crown,
court, realm, house, village, news, rumors, and standing. The rewrite removes invented
relatives, bereavements, witnesses, trips, guard shifts, precise timelines, tithe
changes, royal scandals, promised benefits, and unsupported claims about what the
whole village knows or feels.

Thirteen added scenes have dedicated continuation pages, producing 55 Capital-gated
scenes in total. New subjects include petitions and service, understandable laws,
decrees and family privacy, fair hearings, hypothetical tax accountability, inclusive
ceremonies, farmer and mason development priorities, teenage ambitions, loyalty with
disagreement, candid private dissent, disagreements with allies, and reacting to peace.
The branches offer questions, practical alternatives, disagreement, and withdrawal.

Every authored exchange has `en`/`pt` parity for `en_us`/`pt_br` output, including
player replies and chat phrases. Narrow contextual pools explicitly declare
`min_variants: 1`; their scene cooldowns are at least two days. The 13 deeper additions
normally use a four-day cooldown, while private dissent uses seven days and one
mention per week. The normal compiler floor remains three for other pools. These
overrides avoid adding repetitive paraphrases merely to meet a line count.

Private political worries and disgrace require trusted relationship bands and
confidential delivery; private dissent also requires at least 14 days of acquaintance.
No branch promises to pass a private remark on to the court.

## Corrected state and memory behavior

- Partial bindings mark fields from unavailable capabilities as unavailable, preserving
  the distinction between unknown diplomacy and peace, or unknown succession and no heir.
- Court-news polling requires readable court and chronicle capabilities. Unavailable
  diplomacy preserves the previous relation snapshot instead of forgetting a war and
  announcing it again when the capability returns.
- House-absence dialogue requires an explicit successful house read. It cannot fall
  through when the HOUSES capability is missing.
- Queen, consort, and motto lines have the specific presence gates their claims require.
- The court poller no longer overwrites resolved titles with guessed office titles.
  Capitals has distinct titles such as `high_sovereign`, `crown_heir`, and
  `heir_apparent`; guessing from office holders caused repeated false title changes.
- Only successful resolved-title reads update title memory. Their age uses game time,
  matching the rest of the history clock and resisting `/time set` discontinuities.
- Naming, replacing, or removing an heir produces court news, not an invented birth.
  Abdication alone likewise does not prove a coronation. Translation-key classification
  matches word boundaries so a reward does not become war news.
- Change-of-ruler and peace reaction scenes require a recent untold gossip event.
  A long-established active capital no longer announces a fresh coronation on its own.

Existing court NBT remains compatible: these corrections do not add or reinterpret
persistent fields. Already stored erroneous gossip is not rewritten retroactively.

## Validation and production checks

Automated coverage includes `CapitalContextSourceTest`, `CourtNewsPollerTest`,
`ChronicleEventMapperTest`, `CapitalsNarrativeContractTest`, and
`NoCapitalsStaticLinkTest`. `capitalsProbeTest -PcapitalsJar=<release jar>` exercises
the real manifest in a separate classloader, with the configured MCA probe dependency.
Run it separately for each platform's release jar in the corresponding checkout.

Before publishing, verify these in a real client and dedicated server:

1. Without Capitals, or with its Conversations toggle disabled, all Capital-gated
   scenes disappear in GUI, numbered chat, free text, and groups.
2. With Capitals, compare speaker/sovereign/heir/house/office names and player allegiance
   against the actual court UI, including absent heirs, consorts, and house words.
3. Change a primary title while another court office is also held. Confirm one recent
   title reaction, no oscillation on later polls, and correct behavior after saving.
4. Name an adult heir, replace that heir, change sovereign, and end a war. Confirm the
   event type and gated follow-up without invented births or ceremony details.
5. Exercise trusted private dissent with bystanders and a group conversation. Confirm
   confidentiality gates and semantically equivalent English and Portuguese replies.
6. Check a partial or unsupported binding: only supported facts should remain available,
   with diagnostics identifying the failed members and no false negative-state claims.

Build/probe outcomes belong in the release verification report; the presence of this
checklist does not claim an interactive production-world test was performed.
