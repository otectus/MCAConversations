# Rewriting the conversations — how these templates work

These files are a fill-in copy of **every conversation MCA: Conversations ships**: every question
node, every button the player can press, every line a villager can say back, every per-personality
version of those lines, and every rule the build enforces on them. Nothing is summarised away.

Start at [00-INDEX.md](00-INDEX.md).

---

## 1. How a conversation is actually built here

A conversation is a small graph of **question nodes**. One node is one screen: the villager has said
something, and the player is looking at two to five buttons.

```
villager's line  ──►  question node  ──►  player presses a button
                                             │
                                             ├─ the button picks one OUTCOME
                                             │     (weighted by conditions)
                                             │
                                             ├─ the outcome moves state
                                             │     hearts / disposition / arc / memories
                                             │
                                             ├─ the outcome opens the NEXT node
                                             └─ the outcome SAYS a line
                                                   ── which is what the player reads
                                                      on that next screen
```

Four kinds of text exist, and every template block tells you which one you are looking at:

| `WHO` says | What it is | Key shape |
|---|---|---|
| **VILLAGER — the node's own prompt** | A **fallback**. Shown only when the player arrives with no line of their own. Write it so it reads cold. | `dialogue.<question>` |
| **PLAYER — button** | The words on the button. In the player's voice. | `dialogue.<question>.<answer>` |
| **VILLAGER — the reply** | What the player actually reads after pressing. This is the bulk of the corpus. | `dialogue.<say key>` |
| **Slots / world words** | Noun phrases dropped into a generated line. | `mcaconversations.slot.*` — see [99-slots-and-interface.md](99-slots-and-interface.md) |

Because `say` overwrites the destination node's prompt, **the reply is what the player reads and the
prompt is the safety net.** Rewriting a node prompt changes far less than rewriting a reply.

---

## 2. How to fill a template in

Every editable pool looks like this:

```text
POOL   dialogue key: dialogue.conversations.day.rough.empathize
WHO    VILLAGER — what the player reads after pressing "That sounds like a lot to carry."
       spoken on: conversations.topic.day.rough.respond, button `empathize`
       leaves the player on: conversations.topic.day.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.empathize.to.day.rough`: the villager accepts. Subject `day.rough`, ...
```

```text
  dialogue.conversations.day.rough.empathize/1   [76 chars]
    en  It is, a bit. Nice of you to notice instead of telling me it could be worse.
    >>  ............................................
    pt  É, um pouco. Bom você notar em vez de dizer que podia ser pior.
    >>  ............................................
```

- `en` / `pt` are the **current** shipped lines. Do not edit them — they are your reference.
- `>>` is **your replacement** for the line directly above it. Type over the dots.
- **A blank `>>` means "keep what is there."** You never have to fill in a line you are happy with.
- The `[N chars]` figure is the current length. The dialogue screen wraps, but staying within about
  ±30% of the original keeps the layout you already have.
- `pt` is Brazilian Portuguese. If you rewrite the English and leave the Portuguese, the build still
  passes — parity is checked on *keys and placeholders*, not on meaning — but the two locales will
  drift apart. If you are not writing Portuguese, leave every `pt` `>>` blank rather than guessing.

Fill-in blocks appear once per file. If a pool is spoken in more than one place inside the same file
you get a pointer back to the block instead of a second copy, so you cannot accidentally write two
different replacements for one key.

### Where the text actually lives when you are done

| Block | File to paste into |
|---|---|
| `dialogue.…` en | `src/main/resources/assets/mca_dialogue/lang/en_us.json` |
| `dialogue.…` pt | `src/main/resources/assets/mca_dialogue/lang/pt_br.json` |
| `<personality>.dialogue.…` en | `src/main/resources/assets/mca_dialogue_<personality>/lang/en_us.json` |
| `<personality>.dialogue.…` pt | `src/main/resources/assets/mca_dialogue_<personality>/lang/pt_br.json` |
| `mcaconversations.…` | `src/main/resources/assets/mcaconversations/lang/<locale>.json` |

The key is printed on the line above every `en`, in full, exactly as the JSON needs it.

---

## 3. The rules the build enforces

These are not style preferences. Each one is a test that fails the build, so a rewrite that breaks
one will not ship.

**Keys and locales**

1. `en_us` and `pt_br` must carry **the same keys**, and each key's **placeholders must match** —
   same set of `%N$s`, same indices. Changing `%1$s` to `%2$s` in one locale and not the other
   throws while formatting, in game, for that one line.
2. A `/N` variant run must have **no gaps**. `/1 /2 /4` silently shrinks the pool to two lines.
   If you delete a variant, renumber the rest.
3. Never write a bare `%s`. Always `%1$s`, `%2$s`, …
4. A personality overlay key **must** carry its personality prefix — `odd.dialogue.conversations…`,
   never `dialogue.conversations…`. Minecraft translation keys are global across asset namespaces:
   an unprefixed key in an overlay collides with the base pool, and whichever resource pack loads
   last silently becomes *every* villager's voice.
5. An overlay may only override a pool that already exists in the base `mca_dialogue` file. It
   cannot invent one.

**Voice**

6. **No catchphrases.** A word that is not an ordinary sentence opener may begin at most **2%** of
   the lines in a locale. ("I", "You", "That's", "It's", "The", "We" and friends are exempt — those
   are how sentences begin.) "Aye" once opened 3.7% of the corpus and had to be culled; do not
   rebuild it.
7. **No universal goodbye.** No exact sentence of two words or more may appear more than **12
   times** across the corpus. Write each farewell for its own moment.
8. A button label is **in-character words**, never a mechanic. "You could face it. I'd stand with
   you." — not "Persuade", not "+2 trust", never a number. This holds at every relationship level:
   below a gate the villager's guarded reply is the honest outcome, so the button can still be
   honest.
9. A button must make sense as a reply to **every** line that can open its node. Each node section
   lists those lines under "The lines that can open it" — check yours against all of them.

**Structure (only matters if you change more than wording)**

10. Actions run in JSON key order and `say` must come **after** `next`, or the line is overwritten
    and the player never reads it.
11. When every outcome of a button scores ≤ 0, MCA picks the **last** one. The final entry of a
    `results` array is the safety net; the templates mark it for you.
12. An `auto` question must have exactly one answer.
13. Every node must offer a graceful way out — the `exit` stance. The templates mark which button
    that is.

---

## 4. What the metadata on each block is telling you

**`SIZE — N lines in this pool.`** The client picks one at random each time. They must be
interchangeable: same information, same emotional result, same thing left open. If one variant
answers a question the others do not, the conversation stutters at random.

**`NOTE beat …`** The beat contract is the machine-readable promise this line makes:

- *npc_act* — what the villager is doing: `accept`, `qualify`, `explain`, `deflect`, `withhold`, …
- *subject* — what it is about. Later lines look this up.
- *polarity* — `positive` / `negative` / `mixed` / `neutral`.
- *openness* — `invites_followup` means the line must leave a door open; `permits_followup` means it
  may close; `closes` means it must not keep probing.
- *outcome* — what the game records happened: `accepted`, `qualified`, `rebuffed`, …
- *facts* — when a beat declares a fact, **this line is what establishes it**, and later lines read
  it back. A replacement that stops saying it breaks the exchange two screens later.

Keep all of that true and you can rewrite the sentence completely. Change what the line *does* and
the corresponding contract in `data/mcaconversations/conversation_beats/` has to change with it.

**`stance family` on a button.** The shared vocabulary for what kind of thing the player just said:
`empathy`, `curiosity`, `candor`, `encouragement`, `practical_help`, `humor`,
`respectful_disagreement`, `self_disclosure`, `restraint`, `challenge`, `flirtation`, `dismissal`,
`boundary_push`, `exit`. Rewrite the words, keep the stance.

**Chat-mode phrasings.** Where a button lists them, a player can *type* that reply instead of
clicking it. If you change the button's wording, change the phrasings in
`data/mcaconversations/chat_intents/` to match, or typing the new wording stops working.

**`Fires when:`** how MCA chooses between outcomes. Positive `chance` makes an outcome eligible and
weights it; a large negative (`-1000`, `-2000`) kills it outright. That is how a feature toggle or a
disposition gate is expressed. You do not need to touch these to rewrite text.

**`Does:`** what pressing the button changes — hearts, the six disposition axes, arc stages,
milestones, remembered flags. **Your replacement text has to be worth that.** A line that pays +6
hearts should read like a moment that earned them.

---

## 5. The disposition axes and dialogue checks, briefly

Hearts stay MCA's visible relationship number. Underneath, each (villager, player) pair carries six
hidden axes: `trust`, `respect`, `warmth`, `attraction` (romance-gated), `tension`, `familiarity`.
They never show as a number — they only decide which outcome opens and how a line is voiced.

A **checked** button rolls: the axis value, plus hearts/4, plus a mood adjustment, plus a seeded
−10..+10, against a difficulty — landing on `crit`, `success`, `partial` or `rebuff`. Each tier has
its own line. When you rewrite a tier set, keep the four distinct: a crit should read like something
that surprised the villager, a rebuff like a door closing gently rather than a failure message. The
roll is seeded, so a player cannot re-open the screen to reroll it; they can come back another day.

---

## 6. Regenerating these files

They are generated from the shipped data, so they go stale as soon as the data changes.
[`generate-templates.py`](generate-templates.py) reads `data/mcaconversations/**` and
`assets/mca_dialogue*/lang/**` and rewrites this whole directory. Run it from the repository root:

```bash
python docs/conversation-templates/generate-templates.py
```

> **It overwrites every `.md` file here.** Anything you have typed into a `>>` line is lost.
> Apply your batch of rewrites to the lang JSON first, *then* regenerate — the `en` and `pt`
> reference lines will come back showing your new text, with fresh blanks under them.
> (`README.md` and the script itself are not touched.)

The related build reports are worth knowing about too — `./gradlew test` writes
`build/reports/conversations/adjacency.md` (the same graph, node-first, without the fill-in blanks),
plus `scenes.md`, `threads.md` and the coverage reports.

---

## 7. A suggested order of work

The corpus is large, so the highest-value passes first:

1. **The six reply-tier lines every personality overrides** — the first reply in each emotional
   register. They are the most-read lines in the mod: `day.rough.empathize`,
   `checkin.rough.offer_help`, `work.followup.hear_burnout`, `fears.open.pledge`,
   `hopes.respond.listen`, `life.revisit`.
2. **The hub and category pages** ([00-hub-part1.md](00-hub-part1.md) onward) — every player reads
   these before anything else.
3. **The quick topics** — `day`, `food`, `checkin`, `weather`, `news`. Highest traffic.
4. **The deep topics** — `fears`, `dreams`, `feelings`, `secret`, `regrets`, `us`. Fewest readers,
   most weight per line.
5. **Work talk** (`work-*.md`) — 37 professions, the longest tail.
6. **The leftovers** — [98-loose-lines.md](98-loose-lines.md) and
   [99-slots-and-interface.md](99-slots-and-interface.md).
