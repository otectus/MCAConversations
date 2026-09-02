# Topic: shared_history

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `shared_history` |
| Opened from | question `conversations.cat.events`, button `shared_history` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.events` |
| Ages that can reach it | adult |
| Stance families it must offer | `curiosity`, `empathy`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.events`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.events.shared_history
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.events.shared_history   [26 chars]
    en  What have we been through?
    >>  ............................................
    pt  Pelo que já passamos?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.shared_history.followup`](#conversations-scene-shared-history-followup)
- [`conversations.scene.shared_history.the_date_nobody_marks.respond`](#conversations-scene-shared-history-the-date-nobody-marks-respond)
- [`conversations.scene.shared_history.the_thing_we_avoid.respond`](#conversations-scene-shared-history-the-thing-we-avoid-respond)
- [`conversations.topic.shared_history.more.respond`](#conversations-topic-shared-history-more-respond)
- [`conversations.topic.shared_history.open.respond`](#conversations-topic-shared-history-open-respond)

---

## `conversations.scene.shared_history.followup`

**Reached from 4 route(s):** `conversations.scene.shared_history.the_date_nobody_marks.respond` / `ask_the_count`; `conversations.scene.shared_history.the_date_nobody_marks.respond` / `say_it_deserves_marking`; `conversations.scene.shared_history.the_thing_we_avoid.respond` / `invite_them_to_say_it`; `conversations.scene.shared_history.the_thing_we_avoid.respond` / `let_it_stay_shut`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.shared_history.the_date_nobody_marks.counted` — e.g. "Longer than the last two people who said they were staying, and I stopped counting those on purpose."
- `conversations.scene.shared_history.the_date_nobody_marks.marked` — e.g. "Then we have marked it, just now, and that is more than most such days ever get."
- `conversations.scene.shared_history.the_thing_we_avoid.said` — e.g. "I was unfair to you that day and I let it stand because apologising would have meant explaining why."
- `conversations.scene.shared_history.the_thing_we_avoid.spared` — e.g. "Then we shall, and I shall stop circling it, and you will know that I was willing to."


```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.shared_history.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.shared_history.followup   [19 chars]
    en  More of the record?
    >>  ............................................
    pt  Mais do histórico?
    >>  ............................................
```


### Button `leave` — "The rest of it keeps."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:shared_history.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.followup.leave   [21 chars]
    en  The rest of it keeps.
    >>  ............................................
    pt  O resto pode esperar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.leaving
WHO    VILLAGER — what the player reads after pressing "The rest of it keeps."
       spoken on: conversations.scene.shared_history.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.scene.leaving`: the villager accepts. Subject `shared_history.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.shared_history.the_date_nobody_marks.respond / leave; conversations.scene.shared_history.the_thing_we_avoid.respond / leave; conversations.topic.shared_history.more.respond / leave; conversations.topic.shared_history.open.respond / leave
```

```text
  dialogue.conversations.scene.shared_history.leaving/1   [36 chars]
    en  It is a decent record, on the whole.
    >>  ............................................
    pt  É um histórico decente, no geral.
    >>  ............................................
  dialogue.conversations.scene.shared_history.leaving/2   [34 chars]
    en  That is what I have of it, anyway.
    >>  ............................................
    pt  É o que eu tenho disso, pelo menos.
    >>  ............................................
  dialogue.conversations.scene.shared_history.leaving/3   [34 chars]
    en  We will have more of it by winter.
    >>  ............................................
    pt  Vamos ter mais disso até o inverno.
    >>  ............................................
```

---


## `conversations.scene.shared_history.the_date_nobody_marks.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `shared_history`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.shared_history.the_date_nobody_marks` — e.g. "It has been long enough now that there is a date I quietly count from, and nobody else knows it exists."


```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.shared_history.the_date_nobody_marks.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond   [20 chars]
    en  A date nobody marks.
    >>  ............................................
    pt  Uma data que ninguém marca.
    >>  ............................................
```


### Button `ask_the_count` — "How long has it been?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `shared_history.the_date_nobody_marks.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.the_date_nobody_marks.ask_the_count` — accepted phrasings: "how long has it been"; "how long has it been"; "how many days do you count"
  - the message must contain one of: `long`, `days`
  - scored words: `long`(1.8), `days`(1.8), `been`(0.8), `many`(0.8), `count`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond.ask_the_count
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.the_date_nobody_marks.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond.ask_the_count   [21 chars]
    en  How long has it been?
    >>  ............................................
    pt  Há quanto tempo é?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `shared_history.unmarked_date`)_
- Does: session `turn`
- Then opens: `conversations.scene.shared_history.followup`
- …where the player's next choices will be: "The rest of it keeps."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks.counted
WHO    VILLAGER — what the player reads after pressing "How long has it been?"
       spoken on: conversations.scene.shared_history.the_date_nobody_marks.respond, button `ask_the_count`
       leaves the player on: conversations.scene.shared_history.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.the_date_nobody_marks.open.counted`: the villager explains. Subject `shared_history.unmarked_date`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.counted/1   [100 chars]
    en  Longer than the last two people who said they were staying, and I stopped counting those on purpose.
    >>  ............................................
    pt  Mais tempo que as duas últimas pessoas que disseram que iam ficar, e eu parei de contar essas de propósito.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.counted/2   [108 chars]
    en  Long enough that the children here do not remember the village without you in it, which is the real measure.
    >>  ............................................
    pt  Tempo o bastante para as crianças daqui não lembrarem da vila sem você, que é a medida de verdade.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.counted/3   [104 chars]
    en  I could give you the number and I would rather not, because saying it aloud makes it sound like a claim.
    >>  ............................................
    pt  Eu poderia te dar o número e prefiro não dar, porque dizer em voz alta soa como uma reivindicação.
    >>  ............................................
```


### Button `say_it_deserves_marking` — "That deserves marking."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `shared_history.the_date_nobody_marks.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.the_date_nobody_marks.say_it_deserves_marking` — accepted phrasings: "that deserves marking"; "that deserves marking"; "we should mark that day"
  - the message must contain one of: `deserves`, `mark`
  - scored words: `deserves`(1.8), `mark`(1.8), `marking`(0.8), `should`(0.8), `day`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond.say_it_deserves_marking
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.the_date_nobody_marks.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond.say_it_deserves_marking   [22 chars]
    en  That deserves marking.
    >>  ............................................
    pt  Isso merece ser marcado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.shared_history.date.marked`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +1  _(recorded under topic `shared_history.unmarked_date`)_
- Does: session `turn`
- Then opens: `conversations.scene.shared_history.followup`
- …where the player's next choices will be: "The rest of it keeps."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks.marked
WHO    VILLAGER — what the player reads after pressing "That deserves marking."
       spoken on: conversations.scene.shared_history.the_date_nobody_marks.respond, button `say_it_deserves_marking`
       leaves the player on: conversations.scene.shared_history.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.the_date_nobody_marks.open.marked`: the villager accepts. Subject `shared_history.unmarked_date`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.marked/1   [80 chars]
    en  Then we have marked it, just now, and that is more than most such days ever get.
    >>  ............................................
    pt  Então acabamos de marcar, agora, e é mais do que a maioria desses dias jamais recebe.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.marked/2   [85 chars]
    en  I was hoping you would say that and entirely prepared for you to laugh at it instead.
    >>  ............................................
    pt  Eu esperava que você dissesse isso e estava completamente preparada para você rir em vez disso.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.marked/3   [85 chars]
    en  Good. Next year I shall say the number out loud, and you may pretend to be surprised.
    >>  ............................................
    pt  Bom. Ano que vem eu digo o número em voz alta, e você pode fingir surpresa.
    >>  ............................................
```


### Button `leave` — "So we have."

*stance family `exit` · tone `plain` · answers the beat(s) `shared_history.the_date_nobody_marks.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.the_date_nobody_marks.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks.respond.leave   [11 chars]
    en  So we have.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.leaving
WHO    VILLAGER — what the player reads after pressing "So we have."
       spoken on: conversations.scene.shared_history.the_date_nobody_marks.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.scene.leaving`: the villager accepts. Subject `shared_history.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.shared_history.followup / leave; conversations.scene.shared_history.the_thing_we_avoid.respond / leave; conversations.topic.shared_history.more.respond / leave; conversations.topic.shared_history.open.respond / leave
```

> Written out in full under **`conversations.scene.shared_history.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.shared_history.the_thing_we_avoid.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `shared_history`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.shared_history.the_thing_we_avoid` — e.g. "There is one thing between us that we have both agreed to walk past, and neither of us ever agreed to it aloud."


```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.shared_history.the_thing_we_avoid.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond   [25 chars]
    en  The one we don't mention.
    >>  ............................................
    pt  A que a gente não menciona.
    >>  ............................................
```


### Button `invite_them_to_say_it` — "Say it. I'd rather hear it."

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `shared_history.the_thing_we_avoid.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.the_thing_we_avoid.invite_them_to_say_it` — accepted phrasings: "say it id rather hear it"; "say it i would rather hear it"; "go on and say the thing"
  - the message must contain one of: `hear`, `thing`
  - scored words: `hear`(1.8), `thing`(1.8), `say`(0.8), `rather`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond.invite_them_to_say_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.the_thing_we_avoid.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond.invite_them_to_say_it   [27 chars]
    en  Say it. I'd rather hear it.
    >>  ............................................
    pt  Diga. Prefiro ouvir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.shared_history.opened`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, familiarity +2  _(recorded under topic `shared_history.unmentioned`)_
- Does: session `turn`
- Then opens: `conversations.scene.shared_history.followup`
- …where the player's next choices will be: "The rest of it keeps."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid.said
WHO    VILLAGER — what the player reads after pressing "Say it. I'd rather hear it."
       spoken on: conversations.scene.shared_history.the_thing_we_avoid.respond, button `invite_them_to_say_it`
       leaves the player on: conversations.scene.shared_history.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.the_thing_we_avoid.open.said`: the villager explains. Subject `shared_history.unmentioned`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.said/1   [100 chars]
    en  I was unfair to you that day and I let it stand because apologising would have meant explaining why.
    >>  ............................................
    pt  Fui injusta com você naquele dia e deixei ficar porque pedir desculpa exigiria explicar o porquê.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.said/2   [104 chars]
    en  You saw me at my worst and you have been careful with me ever since, and the care is what I cannot bear.
    >>  ............................................
    pt  Você me viu no meu pior e tem tido cuidado comigo desde então, e é o cuidado que eu não suporto.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.said/3   [107 chars]
    en  I asked something of you that I had no right to ask, and you gave it, and we have both pretended otherwise.
    >>  ............................................
    pt  Pedi de você algo que eu não tinha direito de pedir, e você deu, e nós dois fingimos o contrário.
    >>  ............................................
```


### Button `let_it_stay_shut` — "We can leave that one buried."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `shared_history.the_thing_we_avoid.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.the_thing_we_avoid.let_it_stay_shut` — accepted phrasings: "we can leave that one buried"; "we can leave that one buried"; "some things are better left buried"
  - the message must contain one of: `leave`, `buried`
  - scored words: `leave`(1.8), `buried`(1.8), `one`(0.8), `some`(0.8), `things`(0.8), `better`(0.8), `left`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond.let_it_stay_shut
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.the_thing_we_avoid.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond.let_it_stay_shut   [29 chars]
    en  We can leave that one buried.
    >>  ............................................
    pt  Podemos deixar essa enterrada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, trust +1  _(recorded under topic `shared_history.unmentioned`)_
- Does: session `turn`
- Then opens: `conversations.scene.shared_history.followup`
- …where the player's next choices will be: "The rest of it keeps."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid.spared
WHO    VILLAGER — what the player reads after pressing "We can leave that one buried."
       spoken on: conversations.scene.shared_history.the_thing_we_avoid.respond, button `let_it_stay_shut`
       leaves the player on: conversations.scene.shared_history.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.the_thing_we_avoid.open.spared`: the villager accepts. Subject `shared_history.unmentioned`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.spared/1   [85 chars]
    en  Then we shall, and I shall stop circling it, and you will know that I was willing to.
    >>  ............................................
    pt  Então deixamos, e eu paro de rondar isso, e você vai saber que eu estava disposta.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.spared/2   [80 chars]
    en  That is generous and I half wanted you to make me say it. Another year, perhaps.
    >>  ............................................
    pt  É generoso e uma parte de mim queria que você me obrigasse a dizer. Outro ano, talvez.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.spared/3   [91 chars]
    en  Understood. It stays where it is. I am glad I offered and gladder that you did not take it.
    >>  ............................................
    pt  Entendido. Fica onde está. Fico contente de ter oferecido e mais contente de você não ter aceitado.
    >>  ............................................
```


### Button `leave` — "So we have."

*stance family `exit` · tone `plain` · answers the beat(s) `shared_history.the_thing_we_avoid.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.shared_history.the_thing_we_avoid.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid.respond.leave   [11 chars]
    en  So we have.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.leaving
WHO    VILLAGER — what the player reads after pressing "So we have."
       spoken on: conversations.scene.shared_history.the_thing_we_avoid.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.scene.leaving`: the villager accepts. Subject `shared_history.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.shared_history.followup / leave; conversations.scene.shared_history.the_date_nobody_marks.respond / leave; conversations.topic.shared_history.more.respond / leave; conversations.topic.shared_history.open.respond / leave
```

> Written out in full under **`conversations.scene.shared_history.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.shared_history.more.respond`

**Reached from 2 route(s):** `conversations.topic.shared_history.open.respond` / `ask_the_entries`; `conversations.topic.shared_history.open.respond` / `say_it_has_been_good`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.shared_history.open.agreed` — e.g. "It has. I did not expect to be able to say that about somebody who arrived four seasons ago."
- `conversations.shared_history.open.counted` — e.g. "The bad night, obviously. The day you turned up with the wrong thing and stayed anyway. About nine others."


```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.shared_history.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.shared_history.more.respond   [25 chars]
    en  Something I've forgotten.
    >>  ............................................
    pt  Algo que eu esqueci.
    >>  ............................................
```


### Button `ask_which_entry` — "Tell me which one."

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `shared_history.open.counted`, `shared_history.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.more.ask_which_entry` — accepted phrasings: "tell me which one"; "tell me which one"; "which day do you mean"
  - the message must contain one of: `which`, `day`
  - scored words: `which`(1.8), `day`(1.8), `tell`(0.8), `one`(0.8), `mean`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.more.respond.ask_which_entry
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.more.respond.ask_which_entry   [18 chars]
    en  Tell me which one.
    >>  ............................................
    pt  Me diga qual foi.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.shared_history.asked_after`, budget `standard`, replay policy `once`
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `shared_history.the_forgotten_entry`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.shared_history.more.told
WHO    VILLAGER — what the player reads after pressing "Tell me which one."
       spoken on: conversations.topic.shared_history.more.respond, button `ask_which_entry`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.more.told`: the villager explains. Subject `shared_history.the_forgotten_entry`, polarity `positive`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.more.told/1   [103 chars]
    en  You answered a question I asked badly, and you answered the question I meant instead of the one I said.
    >>  ............................................
    pt  Você respondeu a uma pergunta que eu fiz mal, e respondeu a que eu queria dizer em vez da que eu disse.
    >>  ............................................
  dialogue.conversations.shared_history.more.told/2   [93 chars]
    en  You noticed I was in a bad way and you did not say so in front of anybody. That is the entry.
    >>  ............................................
    pt  Você notou que eu estava mal e não comentou na frente de ninguém. É essa a entrada.
    >>  ............................................
  dialogue.conversations.shared_history.more.told/3   [97 chars]
    en  You waited. That is all. Everybody else that week was in a hurry and you were the one who waited.
    >>  ............................................
    pt  Você esperou. É só isso. Todo mundo naquela semana estava com pressa e você foi quem esperou.
    >>  ............................................
```


### Button `say_you_are_glad_it_helped` — "I'm glad it landed somewhere."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `shared_history.open.counted`, `shared_history.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.more.say_you_are_glad_it_helped` — accepted phrasings: "im glad it landed somewhere"; "i am glad it landed somewhere"; "glad it meant something to you"
  - the message must contain one of: `glad`, `landed`
  - scored words: `glad`(1.8), `landed`(1.8), `somewhere`(0.8), `meant`(0.8), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.more.respond.say_you_are_glad_it_helped
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.more.respond.say_you_are_glad_it_helped   [29 chars]
    en  I'm glad it landed somewhere.
    >>  ............................................
    pt  Fico feliz que tenha valido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.shared_history.received`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `shared_history.the_forgotten_entry`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.shared_history.more.met
WHO    VILLAGER — what the player reads after pressing "I'm glad it landed somewhere."
       spoken on: conversations.topic.shared_history.more.respond, button `say_you_are_glad_it_helped`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.more.met`: the villager accepts. Subject `shared_history.the_forgotten_entry`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.more.met/1   [109 chars]
    en  It landed. People almost never find out which of their small days turned out to be somebody else's large one.
    >>  ............................................
    pt  Valeu. As pessoas quase nunca descobrem qual dos seus dias pequenos foi o dia grande de outra pessoa.
    >>  ............................................
  dialogue.conversations.shared_history.more.met/2   [91 chars]
    en  Then I am glad I finally said it. I have rehearsed that sentence more than I care to admit.
    >>  ............................................
    pt  Então fico contente de ter finalmente dito. Ensaiei essa frase mais do que gostaria de admitir.
    >>  ............................................
  dialogue.conversations.shared_history.more.met/3   [105 chars]
    en  It did, and I have wanted you to know for about a year, and I kept deciding it would sound like too much.
    >>  ............................................
    pt  Valeu, e faz um ano que eu queria que você soubesse, e eu vivia decidindo que ia soar exagerado.
    >>  ............................................
```


### Button `wave_the_entry_away` — "It was a minor thing."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `shared_history.open.counted`, `shared_history.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.more.wave_the_entry_away` — accepted phrasings: "it was a minor thing"; "it was a minor thing"; "that was a small matter"
  - the message must contain one of: `minor`, `small`
  - scored words: `minor`(1.8), `small`(1.8), `thing`(0.8), `matter`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.more.respond.wave_the_entry_away
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.more.respond.wave_the_entry_away   [21 chars]
    en  It was a minor thing.
    >>  ............................................
    pt  Foi uma coisa menor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.shared_history.cut_short`, budget `standard`, replay policy `once`
- Does: disposition — warmth -1, tension +2  _(recorded under topic `shared_history.the_forgotten_entry`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.shared_history.more.closed
WHO    VILLAGER — what the player reads after pressing "It was a minor thing."
       spoken on: conversations.topic.shared_history.more.respond, button `wave_the_entry_away`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.more.closed`: the villager deflects. Subject `shared_history.the_forgotten_entry`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.more.closed/1   [87 chars]
    en  It was nothing to you. That is rather the point I was making, and I have made it badly.
    >>  ............................................
    pt  Não foi nada para você. É meio que o argumento que eu estava fazendo, e eu fiz mal.
    >>  ............................................
  dialogue.conversations.shared_history.more.closed/2   [77 chars]
    en  Perhaps. I have carried it for a year, so one of us has it in the wrong size.
    >>  ............................................
    pt  Talvez. Eu carreguei isso por um ano, então um de nós está com o tamanho errado.
    >>  ............................................
  dialogue.conversations.shared_history.more.closed/3   [49 chars]
    en  Right. I shall put it back where I keep it, then.
    >>  ............................................
    pt  Certo. Guardo de volta onde eu guardo, então.
    >>  ............................................
```


### Button `leave` — "So we have."

*stance family `exit` · tone `plain` · answers the beat(s) `shared_history.open.counted`, `shared_history.open.agreed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.more.respond.leave   [11 chars]
    en  So we have.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.leaving
WHO    VILLAGER — what the player reads after pressing "So we have."
       spoken on: conversations.topic.shared_history.more.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.scene.leaving`: the villager accepts. Subject `shared_history.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.shared_history.followup / leave; conversations.scene.shared_history.the_date_nobody_marks.respond / leave; conversations.scene.shared_history.the_thing_we_avoid.respond / leave; conversations.topic.shared_history.open.respond / leave
```

> Written out in full under **`conversations.scene.shared_history.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.shared_history.open.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `shared_history`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.shared_history.open` — e.g. "I keep a rough account of it in my head, and there are more entries on your side of it than on mine."


```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.shared_history.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.shared_history.open.respond   [24 chars]
    en  What we've been through.
    >>  ............................................
    pt  Pelo que já passamos.
    >>  ............................................
```


### Button `ask_the_entries` — "What's on the list?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `shared_history.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.open.ask_the_entries` — accepted phrasings: "whats on the list"; "what is on the list"; "what do you count on it"
  - the message must contain one of: `list`, `count`
  - scored words: `list`(1.8), `count`(1.8), `whats`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.open.respond.ask_the_entries
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.open.respond.ask_the_entries   [19 chars]
    en  What's on the list?
    >>  ............................................
    pt  O que está na lista?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `shared_history.the_ledger`)_
- Does: session `turn`
- Then opens: `conversations.topic.shared_history.more.respond`
- …where the player's next choices will be: "Tell me which one." | "I'm glad it landed somewhere." | "It was a minor thing." | "So we have."

```text
POOL   dialogue key: dialogue.conversations.shared_history.open.counted
WHO    VILLAGER — what the player reads after pressing "What's on the list?"
       spoken on: conversations.topic.shared_history.open.respond, button `ask_the_entries`
       leaves the player on: conversations.topic.shared_history.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.open.counted`: the villager reminisces. Subject `shared_history.the_ledger`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.open.counted/1   [106 chars]
    en  The bad night, obviously. The day you turned up with the wrong thing and stayed anyway. About nine others.
    >>  ............................................
    pt  A noite ruim, obviamente. O dia em que você chegou com a coisa errada e ficou mesmo assim. Umas nove outras.
    >>  ............................................
  dialogue.conversations.shared_history.open.counted/2   [110 chars]
    en  Mostly small entries. You carried something, you waited for somebody, you did not say the obvious cruel thing.
    >>  ............................................
    pt  Quase tudo entradas pequenas. Você carregou algo, esperou alguém, deixou de dizer a crueldade óbvia.
    >>  ............................................
  dialogue.conversations.shared_history.open.counted/3   [87 chars]
    en  Two I would tell anybody and one I would not, and I am not going to say which is which.
    >>  ............................................
    pt  Duas que eu contaria a qualquer um e uma que eu não contaria, e não vou dizer qual é qual.
    >>  ............................................
```


### Button `say_it_has_been_good` — "It's been good, on balance."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `shared_history.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.open.say_it_has_been_good` — accepted phrasings: "its been good on balance"; "it has been good on balance"; "the balance of it is good"
  - the message must contain one of: `balance`, `good`
  - scored words: `balance`(1.8), `good`(1.8), `its`(0.8), `been`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.open.respond.say_it_has_been_good
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.open.respond.say_it_has_been_good   [27 chars]
    en  It's been good, on balance.
    >>  ............................................
    pt  No balanço, tem sido bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `shared_history.the_ledger`)_
- Does: session `turn`
- Then opens: `conversations.topic.shared_history.more.respond`
- …where the player's next choices will be: "Tell me which one." | "I'm glad it landed somewhere." | "It was a minor thing." | "So we have."

```text
POOL   dialogue key: dialogue.conversations.shared_history.open.agreed
WHO    VILLAGER — what the player reads after pressing "It's been good, on balance."
       spoken on: conversations.topic.shared_history.open.respond, button `say_it_has_been_good`
       leaves the player on: conversations.topic.shared_history.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.open.agreed`: the villager accepts. Subject `shared_history.the_ledger`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.open.agreed/1   [92 chars]
    en  It has. I did not expect to be able to say that about somebody who arrived four seasons ago.
    >>  ............................................
    pt  Tem. Eu não esperava poder dizer isso de alguém que chegou quatro estações atrás.
    >>  ............................................
  dialogue.conversations.shared_history.open.agreed/2   [89 chars]
    en  On balance, yes, and I am aware that I have been the harder half of it to get along with.
    >>  ............................................
    pt  No balanço, sim, e eu tenho consciência de que fui a metade mais difícil de conviver.
    >>  ............................................
  dialogue.conversations.shared_history.open.agreed/3   [93 chars]
    en  Good. I would rather hear you say it than assume it, and I have been assuming it for a while.
    >>  ............................................
    pt  Bom. Prefiro te ouvir dizer a supor, e faz um tempo que eu venho supondo.
    >>  ............................................
```


### Button `shrug_off_the_ledger` — "Keeping score isn't for me."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `shared_history.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.shared_history.open.shrug_off_the_ledger` — accepted phrasings: "keeping score isnt for me"; "keeping score is not for me"; "let us skip the score"
  - the message must contain one of: `score`
  - scored words: `score`(1.8), `keeping`(0.8), `isnt`(0.8), `let`(0.8), `skip`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.open.respond.shrug_off_the_ledger
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.open.respond.shrug_off_the_ledger   [27 chars]
    en  Keeping score isn't for me.
    >>  ............................................
    pt  Fazer contagem não é para mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.shared_history.dismissed`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `shared_history.the_ledger`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.shared_history.open.closed
WHO    VILLAGER — what the player reads after pressing "Keeping score isn't for me."
       spoken on: conversations.topic.shared_history.open.respond, button `shrug_off_the_ledger`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.open.closed`: the villager qualifys. Subject `shared_history.the_ledger`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.open.closed/1   [97 chars]
    en  It is a record, rather than a score. The difference matters to me and I shall stop explaining it.
    >>  ............................................
    pt  É um registro, não uma contagem. A diferença importa para mim e eu paro de explicar.
    >>  ............................................
  dialogue.conversations.shared_history.open.closed/2   [98 chars]
    en  As you like. I keep one for everybody and it is how I know who to go to when something goes wrong.
    >>  ............................................
    pt  Como quiser. Eu mantenho um para todo mundo e é assim que sei a quem recorrer quando algo dá errado.
    >>  ............................................
  dialogue.conversations.shared_history.open.closed/3   [31 chars]
    en  Right. Consider it unmentioned.
    >>  ............................................
    pt  Certo. Considere não mencionado.
    >>  ............................................
```


### Button `leave` — "So we have."

*stance family `exit` · tone `plain` · answers the beat(s) `shared_history.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.shared_history.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.shared_history.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.shared_history.open.respond.leave   [11 chars]
    en  So we have.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.leaving
WHO    VILLAGER — what the player reads after pressing "So we have."
       spoken on: conversations.topic.shared_history.open.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.scene.leaving`: the villager accepts. Subject `shared_history.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.shared_history.followup / leave; conversations.scene.shared_history.the_date_nobody_marks.respond / leave; conversations.scene.shared_history.the_thing_we_avoid.respond / leave; conversations.topic.shared_history.more.respond / leave
```

> Written out in full under **`conversations.scene.shared_history.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

