# Work talk with a oceanographer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.oceanographer.broken_instrument.blocked.respond`](#conversations-scene-work-oceanographer-broken-instrument-blocked-respond)
- [`conversations.scene.work.oceanographer.broken_instrument.succeeded.respond`](#conversations-scene-work-oceanographer-broken-instrument-succeeded-respond)
- [`conversations.scene.work.oceanographer.contradictory_readings.blocked.respond`](#conversations-scene-work-oceanographer-contradictory-readings-blocked-respond)
- [`conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond`](#conversations-scene-work-oceanographer-contradictory-readings-succeeded-respond)
- [`conversations.scene.work.oceanographer.followup`](#conversations-scene-work-oceanographer-followup)
- [`conversations.scene.work.oceanographer.ignored_warning.failed.respond`](#conversations-scene-work-oceanographer-ignored-warning-failed-respond)
- [`conversations.scene.work.oceanographer.ignored_warning.remembered.respond`](#conversations-scene-work-oceanographer-ignored-warning-remembered-respond)
- [`conversations.topic.work.oceanographer.craft.respond`](#conversations-topic-work-oceanographer-craft-respond)
- [`conversations.topic.work.oceanographer.followup`](#conversations-topic-work-oceanographer-followup)
- [`conversations.topic.work.oceanographer.future.respond`](#conversations-topic-work-oceanographer-future-respond)
- [`conversations.topic.work.oceanographer.respond`](#conversations-topic-work-oceanographer-respond)
- [`conversations.topic.work.oceanographer.risk.respond`](#conversations-topic-work-oceanographer-risk-respond)
- [`conversations.topic.work.oceanographer.task.respond`](#conversations-topic-work-oceanographer-task-respond)
- [`conversations.topic.work.oceanographer.village.respond`](#conversations-topic-work-oceanographer-village-respond)

---

## `conversations.scene.work.oceanographer.broken_instrument.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.broken_instrument.blocked` — e.g. "There is %2$s and until it is fixed everything I write down is a story rather than a measurement."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.broken_instrument.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond   [15 chars]
    en  The instrument.
    >>  ............................................
    pt  O instrumento.
    >>  ............................................
```


### Button `offer_glass` — "I'll bring you glass for it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.oceanographer.broken_instrument.blocked` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.broken_instrument.blocked.offer_glass` — accepted phrasings: "ill bring you glass for it"; "i can bring you glass"; "let me fetch glass for that"
  - the message must contain one of: `glass`
  - scored words: `glass`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond.offer_glass
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.broken_instrument.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond.offer_glass   [28 chars]
    en  I'll bring you glass for it.
    >>  ............................................
    pt  Vou te trazer vidro para isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.oceanographer.instrument.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.oceanographer.instruments`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.broken_instrument", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.oceanographer.broken_instrument", "obligation": "commitment:work.oceanographer.bring_glass"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.oceanographer.bring_glass"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you glass for it."
       spoken on: conversations.scene.work.oceanographer.broken_instrument.blocked.respond, button `offer_glass`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.broken_instrument.blocked.accepted`: the villager accepts. Subject `work.oceanographer.instruments`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.accepted/1   [87 chars]
    en  Then the record starts again on Thursday and the gap is nine days rather than a season.
    >>  ............................................
    pt  Então o registro recomeça na quinta e a lacuna é de nove dias em vez de uma estação.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.accepted/2   [125 chars]
    en  Bring it flat and thick if you have a choice. I can grind and I cannot make glass, and grinding is the part I actually enjoy.
    >>  ............................................
    pt  Traga plano e grosso, se puder escolher. Eu sei polir e não sei fazer vidro, e polir é a parte de que eu de fato gosto.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.accepted/3   [129 chars]
    en  Yes. And I will write your name in the margin of the day it was repaired, which is how this book records everything that matters.
    >>  ............................................
    pt  Sim. E vou escrever seu nome na margem do dia do conserto, que é como este caderno registra tudo o que importa.
    >>  ............................................
```


### Button `ask_about_the_gap` — "What does a gap in the record cost?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.broken_instrument.blocked` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.broken_instrument.blocked.ask_about_the_gap` — accepted phrasings: "what does a gap in the record cost"; "what does a gap in the record cost"; "how bad is a break in the record"
  - the message must contain one of: `gap`, `record`, `break`
  - scored words: `gap`(1.8), `record`(1.8), `break`(1.8), `does`(0.8), `cost`(0.8), `bad`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond.ask_about_the_gap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.broken_instrument.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond.ask_about_the_gap   [35 chars]
    en  What does a gap in the record cost?
    >>  ............................................
    pt  O que uma lacuna no registro custa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.oceanographer.instruments`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.oceanographer.broken_instrument"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What does a gap in the record cost?"
       spoken on: conversations.scene.work.oceanographer.broken_instrument.blocked.respond, button `ask_about_the_gap`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.broken_instrument.blocked.explained`: the villager explains. Subject `work.oceanographer.instruments`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.explained/1   [130 chars]
    en  More than the days it covers. A record with a hole in it can be argued with, and once it can be argued with it stops being useful.
    >>  ............................................
    pt  Mais do que os dias que cobre. Um registro com buraco pode ser contestado, e assim que pode ser contestado deixa de ser útil.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.explained/2   [135 chars]
    en  Ten years from now somebody will want to know what happened this spring, and the honest answer will be a blank page with my name on it.
    >>  ............................................
    pt  Daqui a dez anos alguém vai querer saber o que aconteceu nesta primavera, e a resposta honesta vai ser uma página em branco com o meu nome.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.explained/3   [126 chars]
    en  It is not the missing numbers. It is that I will never again be certain the two halves of the record are the same measurement.
    >>  ............................................
    pt  Não são os números que faltam. É que eu nunca mais vou ter certeza de que as duas metades do registro são a mesma medição.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your readings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.oceanographer.broken_instrument.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.broken_instrument.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked.respond.leave   [39 chars]
    en  I'll let you get back to your readings.
    >>  ............................................
    pt  Vou deixar você voltar às medições.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your readings."
       spoken on: conversations.scene.work.oceanographer.broken_instrument.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.oceanographer.leave/1   [52 chars]
    en  The tide has opinions about my schedule. Off you go.
    >>  ............................................
    pt  A maré tem opinião sobre meu horário. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.leave/2   [34 chars]
    en  Aye. Don't step on the jars, %1$s.
    >>  ............................................
    pt  É. Não pise nos potes, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.oceanographer.broken_instrument.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.broken_instrument.succeeded` — e.g. "Ground and set and reading true. Nine days lost, and I have marked them as lost rather than filling them in from memory."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.respond   [23 chars]
    en  The instrument, mended.
    >>  ............................................
    pt  O instrumento, consertado.
    >>  ............................................
```


### Button `note_marking_the_gap` — "Marking the gap was right."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.oceanographer.broken_instrument.succeeded` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.broken_instrument.succeeded.note_marking_the_gap` — accepted phrasings: "marking the gap was right"; "marking the gap was right"; "leaving the days blank was correct"
  - the message must contain one of: `marking`, `gap`, `blank`
  - scored words: `marking`(1.8), `gap`(1.8), `blank`(1.8), `right`(0.8), `leaving`(0.8), `days`(0.8), `correct`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.respond.note_marking_the_gap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.respond.note_marking_the_gap   [26 chars]
    en  Marking the gap was right.
    >>  ............................................
    pt  Marcar a lacuna estava certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +1  _(recorded under topic `work.oceanographer.instruments`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.oceanographer.broken_instrument"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Marking the gap was right."
       spoken on: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond, button `note_marking_the_gap`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.broken_instrument.succeeded.acknowledged`: the villager accepts. Subject `work.oceanographer.instruments`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.acknowledged/1   [110 chars]
    en  It was tempting to fill them. I could have done it plausibly in an hour and nobody alive would have caught it.
    >>  ............................................
    pt  Foi tentador preencher. Eu teria feito de forma plausível em uma hora e ninguém vivo teria percebido.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.acknowledged/2   [140 chars]
    en  Thank you. A blank page is a claim too — it claims I was honest about the day I was not measuring, and that is worth more than nine numbers.
    >>  ............................................
    pt  Obrigada. Uma página em branco também é uma afirmação — afirma que eu fui honesta sobre o dia em que não medi, e isso vale mais que nove números.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.acknowledged/3   [124 chars]
    en  The next person to read this book will not know me. All they will have is whether the blanks are where the blanks should be.
    >>  ............................................
    pt  A próxima pessoa a ler este caderno não vai me conhecer. Tudo o que vai ter é se as lacunas estão onde deveriam estar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your readings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.oceanographer.broken_instrument.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded.respond.leave   [39 chars]
    en  I'll let you get back to your readings.
    >>  ............................................
    pt  Vou deixar você voltar às medições.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your readings."
       spoken on: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.oceanographer.contradictory_readings.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.contradictory_readings.blocked` — e.g. "I have %3$s for %2$s and I cannot tell you which of them is wrong, which means I cannot tell you anything."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond   [13 chars]
    en  The readings.
    >>  ............................................
    pt  As medições.
    >>  ............................................
```


### Button `ask_why_it_matters` — "What would the answer change?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.contradictory_readings.blocked` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.contradictory_readings.blocked.ask_why_it_matters` — accepted phrasings: "what would the answer change"; "what would the answer change"; "what depends on knowing that"
  - the message must contain one of: `answer`, `depends`
  - scored words: `answer`(1.8), `depends`(1.8), `change`(0.8), `knowing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.ask_why_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.ask_why_it_matters   [29 chars]
    en  What would the answer change?
    >>  ............................................
    pt  O que a resposta mudaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.oceanographer.readings`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.oceanographer.contradictory_readings"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What would the answer change?"
       spoken on: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond, button `ask_why_it_matters`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.contradictory_readings.blocked.explained`: the villager explains. Subject `work.oceanographer.readings`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.explained/1   [114 chars]
    en  Where the boats go in the spring. If %2$s is shifting, the safe channel is somewhere else and nobody knows it yet.
    >>  ............................................
    pt  Para onde os barcos vão na primavera. Se %2$s está mudando, o canal seguro está em outro lugar e ninguém sabe ainda.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.explained/2   [147 chars]
    en  Whether the river mouth silts up in ten years. That decides whether this village has a harbour or a marsh, and nobody here thinks in tens of years.
    >>  ............................................
    pt  Se a foz assoreia em dez anos. Isso decide se esta vila tem um porto ou um pântano, e ninguém aqui pensa em dezenas de anos.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.explained/3   [118 chars]
    en  Very little this week and a great deal in a decade, which is the worst possible shape for getting anybody's attention.
    >>  ............................................
    pt  Muito pouco esta semana e muitíssimo em uma década, que é o pior formato possível para conseguir a atenção de alguém.
    >>  ............................................
```


### Button `advise_more_readings` — "Then take another season of readings."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.contradictory_readings.blocked` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.contradictory_readings.blocked.advise_more_readings` — accepted phrasings: "then take another season of readings"; "then take another season of readings"; "keep measuring for another season"
  - the message must contain one of: `season`, `measuring`, `readings`
  - scored words: `season`(1.8), `measuring`(1.8), `readings`(1.8), `take`(0.8), `another`(0.8), `keep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.advise_more_readings
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.advise_more_readings   [37 chars]
    en  Then take another season of readings.
    >>  ............................................
    pt  Então meça mais uma estação.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.oceanographer.readings`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.oceanographer.contradictory_readings"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Then take another season of readings."
       spoken on: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond, button `advise_more_readings`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.contradictory_readings.blocked.accepted`: the villager accepts. Subject `work.oceanographer.readings`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.accepted/1   [110 chars]
    en  Another season and I will have eight months of contradictions instead of four, and that is genuinely progress.
    >>  ............................................
    pt  Mais uma estação e eu vou ter oito meses de contradições em vez de quatro, e isso é progresso de verdade.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.accepted/2   [125 chars]
    en  Yes. The alternative is to pick the reading I prefer and call it a finding, and I have watched a man do that and be believed.
    >>  ............................................
    pt  Sim. A alternativa é escolher a medição que eu prefiro e chamar de conclusão, e eu já vi um homem fazer isso e ser acreditado.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.accepted/3   [139 chars]
    en  It is the answer and it is unbearable, because it means saying I do not know for another four months in a village that finds that annoying.
    >>  ............................................
    pt  É a resposta e é insuportável, porque significa dizer que eu não sei por mais quatro meses numa vila que acha isso irritante.
    >>  ............................................
```


### Button `respect_the_caution` — "Your caution there is right."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.contradictory_readings.blocked` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.contradictory_readings.blocked.respect_the_caution` — accepted phrasings: "your caution there is right"; "holding back until you are sure is honest"; "your caution there is right"
  - the message must contain one of: `honest`, `caution`
  - scored words: `honest`(1.8), `caution`(1.8), `right`(0.8), `holding`(0.8), `back`(0.8), `until`(0.8), `sure`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.respect_the_caution
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.respect_the_caution   [28 chars]
    en  Your caution there is right.
    >>  ............................................
    pt  Sua cautela aí está certa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.oceanographer.readings.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `work.oceanographer.readings`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.oceanographer.contradictory_readings"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Your caution there is right."
       spoken on: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond, button `respect_the_caution`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.contradictory_readings.blocked.steadied`: the villager accepts. Subject `work.oceanographer.readings`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.steadied/1   [119 chars]
    en  It is the honest answer and it is also the one that gets a person ignored, and I have decided to be ignored accurately.
    >>  ............................................
    pt  É a resposta honesta e também a que faz uma pessoa ser ignorada, e eu decidi ser ignorada com exatidão.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.steadied/2   [122 chars]
    en  Thank you. Everybody wants a number. A wrong number is worse than no number and it takes about six years to find that out.
    >>  ............................................
    pt  Obrigada. Todo mundo quer um número. Um número errado é pior que nenhum número, e leva uns seis anos para descobrir isso.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.steadied/3   [142 chars]
    en  I keep a page at the front of the book of everything I have been wrong about. It is a short page and it is the reason anybody trusts the rest.
    >>  ............................................
    pt  Guardo uma página no começo do caderno com tudo em que eu errei. É uma página curta e é o motivo de alguém confiar no resto.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your readings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.oceanographer.contradictory_readings.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked.respond.leave   [39 chars]
    en  I'll let you get back to your readings.
    >>  ............................................
    pt  Vou deixar você voltar às medições.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your readings."
       spoken on: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.contradictory_readings.succeeded` — e.g. "Eight months settled it. %2$s is shifting east, about a boat's length a year, and now I can say so with a straight face."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond   [25 chars]
    en  The readings, in the end.
    >>  ............................................
    pt  As medições, no fim.
    >>  ............................................
```


### Button `ask_if_they_listened` — "Did anybody listen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.contradictory_readings.succeeded` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.contradictory_readings.succeeded.ask_if_they_listened` — accepted phrasings: "did anybody listen"; "did anybody listen to the finding"; "who listened in the end"
  - the message must contain one of: `listen`, `listened`, `finding`
  - scored words: `listen`(1.8), `listened`(1.8), `finding`(1.8), `anybody`(0.8), `who`(0.8), `end`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond.ask_if_they_listened
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond.ask_if_they_listened   [19 chars]
    en  Did anybody listen?
    >>  ............................................
    pt  Alguém escutou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.readings`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.oceanographer.contradictory_readings"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Did anybody listen?"
       spoken on: conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond, button `ask_if_they_listened`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.contradictory_readings.succeeded.answered`: the villager explains. Subject `work.oceanographer.readings`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.answered/1   [124 chars]
    en  The fishermen did, immediately, because they had known it in their hands for two years and wanted somebody to write it down.
    >>  ............................................
    pt  Os pescadores, na hora, porque já sabiam disso nas mãos há dois anos e queriam alguém para anotar.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.answered/2   [113 chars]
    en  Two boats moved their moorings. That is the entire visible consequence of eight months of work, and it is enough.
    >>  ............................................
    pt  Dois barcos mudaram de amarração. É a consequência visível inteira de oito meses de trabalho, e basta.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.answered/3   [123 chars]
    en  The headman said he would consider it. In this village that means yes in about four years, and four years is inside my ten.
    >>  ............................................
    pt  O chefe disse que ia considerar. Nesta vila isso quer dizer sim em uns quatro anos, e quatro anos cabem dentro dos meus dez.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your readings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.oceanographer.contradictory_readings.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond.leave   [39 chars]
    en  I'll let you get back to your readings.
    >>  ............................................
    pt  Vou deixar você voltar às medições.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your readings."
       spoken on: conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.oceanographer.followup`

**Reached from 10 route(s):** `conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / `offer_glass`; `conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / `ask_about_the_gap`; `conversations.scene.work.oceanographer.broken_instrument.succeeded.respond` / `note_marking_the_gap`; `conversations.scene.work.oceanographer.contradictory_readings.blocked.respond` / `ask_why_it_matters`; `conversations.scene.work.oceanographer.contradictory_readings.blocked.respond` / `advise_more_readings`; `conversations.scene.work.oceanographer.contradictory_readings.blocked.respond` / `respect_the_caution`; `conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond` / `ask_if_they_listened`; `conversations.scene.work.oceanographer.ignored_warning.failed.respond` / `ask_what_she_would_change`; `conversations.scene.work.oceanographer.ignored_warning.failed.respond` / `say_it_was_not_hers`; `conversations.scene.work.oceanographer.ignored_warning.remembered.respond` / `note_the_absence`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.broken_instrument.blocked.accepted` — e.g. "Then the record starts again on Thursday and the gap is nine days rather than a season."
- `conversations.scene.work.oceanographer.broken_instrument.blocked.explained` — e.g. "More than the days it covers. A record with a hole in it can be argued with, and once it can be argued with it stops being useful."
- `conversations.scene.work.oceanographer.broken_instrument.succeeded.acknowledged` — e.g. "It was tempting to fill them. I could have done it plausibly in an hour and nobody alive would have caught it."
- `conversations.scene.work.oceanographer.contradictory_readings.blocked.accepted` — e.g. "Another season and I will have eight months of contradictions instead of four, and that is genuinely progress."
- `conversations.scene.work.oceanographer.contradictory_readings.blocked.explained` — e.g. "Where the boats go in the spring. If %2$s is shifting, the safe channel is somewhere else and nobody knows it yet."
- `conversations.scene.work.oceanographer.contradictory_readings.blocked.steadied` — e.g. "It is the honest answer and it is also the one that gets a person ignored, and I have decided to be ignored accurately."
- `conversations.scene.work.oceanographer.contradictory_readings.succeeded.answered` — e.g. "The fishermen did, immediately, because they had known it in their hands for two years and wanted somebody to write it down."
- `conversations.scene.work.oceanographer.ignored_warning.failed.answered` — e.g. "Tell the boats first and the headman second. I did it the other way round because I thought that was the correct order, and correctness is not the same as effective."
- `conversations.scene.work.oceanographer.ignored_warning.failed.qualified` — e.g. "I did, and telling is not the job. The job is being understood, and I have decided that the difference is mine to close."
- `conversations.scene.work.oceanographer.ignored_warning.remembered.acknowledged` — e.g. "It is the only kind of result this trade produces, and it is invisible by definition, and I have made peace with that twice."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.followup   [25 chars]
    en  Anything else you needed?
    >>  ............................................
    pt  Precisava de mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a reading at sea?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.oceanographer.*` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.followup.ask_more` — accepted phrasings: "whats the hardest part of a reading at sea"; "what is the hardest part of a reading at sea"; "hardest thing about taking readings at sea"
  - the message must contain one of: `hardest`, `sea`
  - scored words: `hardest`(1.8), `sea`(1.8), `whats`(0.8), `part`(0.8), `reading`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.followup.ask_more   [44 chars]
    en  What's the hardest part of a reading at sea?
    >>  ............................................
    pt  Qual é a parte mais difícil de medir no mar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a reading at sea?"
       spoken on: conversations.scene.work.oceanographer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.hard`: the villager explains. Subject `work.oceanographer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.oceanographer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.oceanographer.hard/1   [84 chars]
    en  Long enough to be sensible and about forty heartbeats past it, which is the problem.
    >>  ............................................
    pt  Tempo bastante pra ser sensato e mais umas quarenta batidas depois disso, que é o problema.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.hard/2   [83 chars]
    en  Not as long as I'd like, %1$s. The sea is bigger than my lungs, which seems unfair.
    >>  ............................................
    pt  Não tanto quanto eu queria, %1$s. O mar é maior que meus pulmões, o que parece injusto.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the tide."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.oceanographer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.followup.leave   [27 chars]
    en  I'll leave you to the tide.
    >>  ............................................
    pt  Vou deixar você com a maré.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the tide."
       spoken on: conversations.scene.work.oceanographer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.oceanographer.ignored_warning.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.ignored_warning.failed` — e.g. "I told %2$s about the spring channel in good time and nothing happened, and then a boat grounded."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.ignored_warning.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond   [12 chars]
    en  The warning.
    >>  ............................................
    pt  O aviso.
    >>  ............................................
```


### Button `ask_what_she_would_change` — "What would you do differently?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.oceanographer.ignored_warning.failed` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.ignored_warning.failed.ask_what_she_would_change` — accepted phrasings: "what would you do differently"; "what would you do differently"; "what would you change about how you told them"
  - the message must contain one of: `differently`, `change`
  - scored words: `differently`(1.8), `change`(1.8), `told`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond.ask_what_she_would_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.ignored_warning.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond.ask_what_she_would_change   [30 chars]
    en  What would you do differently?
    >>  ............................................
    pt  O que você faria diferente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.what_the_fishermen_say`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.oceanographer.ignored_warning"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.answered
WHO    VILLAGER — what the player reads after pressing "What would you do differently?"
       spoken on: conversations.scene.work.oceanographer.ignored_warning.failed.respond, button `ask_what_she_would_change`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.ignored_warning.failed.answered`: the villager explains. Subject `work.oceanographer.what_the_fishermen_say`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.answered/1   [165 chars]
    en  Tell the boats first and the headman second. I did it the other way round because I thought that was the correct order, and correctness is not the same as effective.
    >>  ............................................
    pt  Avisar os barcos primeiro e o chefe depois. Fiz ao contrário porque achei que era a ordem correta, e correto não é o mesmo que eficaz.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.answered/2   [126 chars]
    en  Draw it. I gave them a number and they needed a picture, and I have spent four months since learning to draw badly on purpose.
    >>  ............................................
    pt  Desenhar. Dei um número e eles precisavam de uma figura, e passei quatro meses desde então aprendendo a desenhar mal de propósito.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.answered/3   [124 chars]
    en  Say it three times. A thing said once is a remark. I had been treating one clear statement as though it discharged the duty.
    >>  ............................................
    pt  Dizer três vezes. Uma coisa dita uma vez é um comentário. Eu vinha tratando uma frase clara como se cumprisse o dever.
    >>  ............................................
```


### Button `say_it_was_not_hers` — "You gave the warning in good time."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.oceanographer.ignored_warning.failed` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.ignored_warning.failed.say_it_was_not_hers` — accepted phrasings: "you gave the warning in good time"; "you gave the warning in good time"; "the warning was given"
  - the message must contain one of: `warning`, `gave`, `given`
  - scored words: `warning`(1.8), `gave`(1.8), `given`(1.8), `good`(0.8), `time`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond.say_it_was_not_hers
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.ignored_warning.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond.say_it_was_not_hers   [34 chars]
    en  You gave the warning in good time.
    >>  ............................................
    pt  Você deu o aviso em tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.oceanographer.what_the_fishermen_say`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.oceanographer.ignored_warning"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.qualified
WHO    VILLAGER — what the player reads after pressing "You gave the warning in good time."
       spoken on: conversations.scene.work.oceanographer.ignored_warning.failed.respond, button `say_it_was_not_hers`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.ignored_warning.failed.qualified`: the villager qualifys. Subject `work.oceanographer.what_the_fishermen_say`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.qualified/1   [120 chars]
    en  I did, and telling is not the job. The job is being understood, and I have decided that the difference is mine to close.
    >>  ............................................
    pt  Avisei, e avisar não é o trabalho. O trabalho é ser compreendida, e eu decidi que essa diferença é minha para fechar.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.qualified/2   [134 chars]
    en  Thank you. I will take half of that. The other half is that I know how this village listens and I spoke as if it listened differently.
    >>  ............................................
    pt  Obrigada. Aceito metade disso. A outra metade é que eu sei como esta vila escuta e falei como se ela escutasse de outro jeito.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.qualified/3   [125 chars]
    en  It is the comfortable version and I am wary of it, because a person who accepts it stops improving on the day they accept it.
    >>  ............................................
    pt  É a versão confortável e eu desconfio dela, porque quem aceita para de melhorar no dia em que aceita.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your readings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.oceanographer.ignored_warning.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.ignored_warning.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed.respond.leave   [39 chars]
    en  I'll let you get back to your readings.
    >>  ............................................
    pt  Vou deixar você voltar às medições.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your readings."
       spoken on: conversations.scene.work.oceanographer.ignored_warning.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.oceanographer.ignored_warning.remembered.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.oceanographer.ignored_warning.remembered` — e.g. "I drew it. Badly, on a board, at the quay, and eleven people stood and looked at it and two of them argued with me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.oceanographer.ignored_warning.remembered.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.respond   [19 chars]
    en  The channel, since.
    >>  ............................................
    pt  O canal, depois disso.
    >>  ............................................
```


### Button `note_the_absence` — "An absence is still your work."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.oceanographer.ignored_warning.remembered` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.oceanographer.ignored_warning.remembered.note_the_absence` — accepted phrasings: "an absence is still your work"; "an absence is still your work"; "the boats that did not ground are your result"
  - the message must contain one of: `absence`, `boats`, `result`
  - scored words: `absence`(1.8), `boats`(1.8), `result`(1.8), `still`(0.8), `work`(0.8), `ground`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.respond.note_the_absence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.ignored_warning.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.respond.note_the_absence   [30 chars]
    en  An absence is still your work.
    >>  ............................................
    pt  Uma ausência ainda é trabalho seu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.oceanographer.what_the_fishermen_say`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.oceanographer.ignored_warning"}
- Then opens: `conversations.scene.work.oceanographer.followup`
- …where the player's next choices will be: "What's the hardest part of a reading at sea?" | "I'll leave you to the tide."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.acknowledged
WHO    VILLAGER — what the player reads after pressing "An absence is still your work."
       spoken on: conversations.scene.work.oceanographer.ignored_warning.remembered.respond, button `note_the_absence`
       leaves the player on: conversations.scene.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.ignored_warning.remembered.acknowledged`: the villager accepts. Subject `work.oceanographer.what_the_fishermen_say`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.acknowledged/1   [124 chars]
    en  It is the only kind of result this trade produces, and it is invisible by definition, and I have made peace with that twice.
    >>  ............................................
    pt  É o único tipo de resultado que este ofício produz, é invisível por definição, e eu já fiz as pazes com isso duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.acknowledged/2   [128 chars]
    en  Thank you. I keep a private list of things that have not happened. It is the most satisfying and least shareable document I own.
    >>  ............................................
    pt  Obrigada. Guardo uma lista particular de coisas que não aconteceram. É o documento mais satisfatório e menos compartilhável que eu tenho.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.acknowledged/3   [147 chars]
    en  The board is still at the quay. Somebody has repainted the lines, which means somebody else has taken it on, and that is better than being thanked.
    >>  ............................................
    pt  A tábua continua no cais. Alguém repintou as linhas, o que quer dizer que outra pessoa assumiu aquilo, e isso é melhor do que ser agradecida.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your readings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.oceanographer.ignored_warning.remembered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.oceanographer.ignored_warning.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered.respond.leave   [39 chars]
    en  I'll let you get back to your readings.
    >>  ............................................
    pt  Vou deixar você voltar às medições.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your readings."
       spoken on: conversations.scene.work.oceanographer.ignored_warning.remembered.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer.craft` — e.g. "It's counting and waiting. Nineteen years of both, and the first eleven told me almost nothing."


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.craft.respond   [22 chars]
    en  That's how it was got.
    >>  ............................................
    pt  Foi assim que se obteve.
    >>  ............................................
```


### Button `ask_eleven` — "Eleven years of nothing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.craft` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.craft.ask_eleven` — accepted phrasings: "eleven years of nothing"
  - the message must contain one of: `eleven`, `nothing`
  - scored words: `eleven`(1.5), `nothing`(1.0), `years`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.craft.respond.ask_eleven
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.craft.respond.ask_eleven   [24 chars]
    en  Eleven years of nothing?
    >>  ............................................
    pt  Onze anos de nada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.craft.ask_eleven`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.craft.ask_eleven
WHO    VILLAGER — what the player reads after pressing "Eleven years of nothing?"
       spoken on: conversations.topic.work.oceanographer.craft.respond, button `ask_eleven`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.craft.ask_eleven`: the villager explains. Subject `work.oceanographer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.craft.ask_eleven/1   [89 chars]
    en  Eleven years of marks. In the twelfth they stopped being marks and started being a shape.
    >>  ............................................
    pt  Onze anos de marcas. No décimo segundo elas deixaram de ser marcas e viraram uma forma.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.craft.ask_eleven/2   [86 chars]
    en  You cannot see a tide from inside one year, %1$s. That's the whole difficulty of this.
    >>  ............................................
    pt  Não dá pra ver uma maré de dentro de um ano, %1$s. É toda a dificuldade disso.
    >>  ............................................
```


### Button `admire` — "Eleven years before the shape appeared is real patience."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.craft` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.craft.admire` — accepted phrasings: "eleven years before the shape appeared is real patience"
  - the message must contain one of: `patience`, `shape`, `appeared`
  - scored words: `patience`(1.5), `shape`(1.2), `appeared`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.craft.respond.admire   [56 chars]
    en  Eleven years before the shape appeared is real patience.
    >>  ............................................
    pt  Onze anos antes da forma aparecer é paciência real.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.oceanographer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.oceanographer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.craft.admire
WHO    VILLAGER — what the player reads after pressing "Eleven years before the shape appeared is real patience."
       spoken on: conversations.topic.work.oceanographer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.craft.admire`: the villager accepts. Subject `work.oceanographer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.craft.admire/1   [98 chars]
    en  It's not patience. It's that I'd already put eleven years in and couldn't afford it to be nothing.
    >>  ............................................
    pt  Não é paciência. É que eu já tinha posto onze anos e não podia deixar virar nada.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.craft.admire/2   [97 chars]
    en  It's the only thing about me anyone should be impressed by, %1$s, and it's the thing nobody sees.
    >>  ............................................
    pt  É a única coisa em mim que devia impressionar alguém, %1$s, e é a que ninguém vê.
    >>  ............................................
```


### Button `ask_books` — "What did the books get wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.craft` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.craft.ask_books` — accepted phrasings: "what did the books get wrong"
  - the message must contain one of: `books`, `wrong`, `read`
  - scored words: `books`(1.5), `wrong`(1.0), `read`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.craft.respond.ask_books
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.craft.respond.ask_books   [29 chars]
    en  What did the books get wrong?
    >>  ............................................
    pt  O que os livros erraram?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.craft.ask_books`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.craft.ask_books
WHO    VILLAGER — what the player reads after pressing "What did the books get wrong?"
       spoken on: conversations.topic.work.oceanographer.craft.respond, button `ask_books`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.craft.ask_books`: the villager explains. Subject `work.oceanographer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.craft.ask_books/1   [86 chars]
    en  They were written about a different coast. Everything in them was true somewhere else.
    >>  ............................................
    pt  Foram escritos sobre outra costa. Tudo neles era verdade em outro lugar.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.craft.ask_books/2   [79 chars]
    en  Nothing. I read them wrong, %1$s, which took me six years to be willing to say.
    >>  ............................................
    pt  Nada. Eu li errado, %1$s, e levei seis anos pra admitir.
    >>  ............................................
```


### Button `leave` — "I'll let you get to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.craft.respond.leave   [30 chars]
    en  I'll let you get to the water.
    >>  ............................................
    pt  Vou deixar você ir pra água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to the water."
       spoken on: conversations.topic.work.oceanographer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.followup`

**Reached from 20 route(s):** `conversations.scene.work.oceanographer.followup` / `ask_more`; `conversations.topic.work.oceanographer.craft.respond` / `ask_eleven`; `conversations.topic.work.oceanographer.craft.respond` / `admire`; `conversations.topic.work.oceanographer.craft.respond` / `ask_books`; `conversations.topic.work.oceanographer.future.respond` / `ask_second_post`; `conversations.topic.work.oceanographer.future.respond` / `encourage`; `conversations.topic.work.oceanographer.future.respond` / `ask_gap`; `conversations.topic.work.oceanographer.respond` / `ask_hard`; `conversations.topic.work.oceanographer.respond` / `value`; `conversations.topic.work.oceanographer.respond` / `challenge`; `conversations.topic.work.oceanographer.respond` / `challenge`; `conversations.topic.work.oceanographer.risk.respond` / `ask_houses` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer.challenge.landed` — e.g. "Statistically, yes. I have arranged for the notes to reach the cartographer."
- `conversations.work.prof.oceanographer.challenge.stung` — e.g. "...I have never once gone down without a line. Not once."
- `conversations.work.prof.oceanographer.craft.admire` — e.g. "It's not patience. It's that I'd already put eleven years in and couldn't afford it to be nothing."
- `conversations.work.prof.oceanographer.craft.ask_books` — e.g. "They were written about a different coast. Everything in them was true somewhere else."
- `conversations.work.prof.oceanographer.craft.ask_eleven` — e.g. "Eleven years of marks. In the twelfth they stopped being marks and started being a shape."
- `conversations.work.prof.oceanographer.future.ask_gap` — e.g. "Because you can't tell whether the thing you missed was the ordinary year or the one that mattered."
- `conversations.work.prof.oceanographer.future.ask_second_post` — e.g. "The far headland. Two days' walk and one afternoon's work, and then twenty years of walking there."
- `conversations.work.prof.oceanographer.future.encourage` — e.g. "...Now. Yes. Every year I wait is a year the comparison doesn't have, and I know that arithmetic."
- `conversations.work.prof.oceanographer.hard` — e.g. "Long enough to be sensible and about forty heartbeats past it, which is the problem."
- `conversations.work.prof.oceanographer.risk.ask_houses` — e.g. "Not on one month. On two I will, and I'll be the man who was wrong if the third month drops."
- `conversations.work.prof.oceanographer.risk.ask_warning` — e.g. "They give me a year's warning about a decade and no warning at all about a Tuesday."
- `conversations.work.prof.oceanographer.risk.sympathise` — e.g. "...And I have solved one of them and have no idea how to approach the other."
- `conversations.work.prof.oceanographer.task.ask_above` — e.g. "It's one month. Two months would be a pattern and a pattern would mean moving the lower houses."
- `conversations.work.prof.oceanographer.task.ask_census` — e.g. "Where the current has moved. Weed from the far headland means it's swung two points west."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.followup   [28 chars]
    en  That's the sea's half of it.
    >>  ............................................
    pt  É a metade do mar.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.challenge.landed`, `work.oceanographer.challenge.stung`, `work.oceanographer.craft.admire`, `work.oceanographer.craft.ask_books`, `work.oceanographer.craft.ask_eleven`, `work.oceanographer.future.ask_gap`, `work.oceanographer.future.ask_second_post`, `work.oceanographer.future.encourage`, `work.oceanographer.hard`, `work.oceanographer.risk.ask_houses`, `work.oceanographer.risk.ask_warning`, `work.oceanographer.risk.sympathise`, `work.oceanographer.task.ask_above`, `work.oceanographer.task.ask_census`, `work.oceanographer.task.offer_hands`, `work.oceanographer.value`, `work.oceanographer.village.ask_fishermen`, `work.oceanographer.village.ask_post`, `work.oceanographer.village.say_thanks` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.oceanographer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `surface`, `shore`
  - scored words: `thought`(1.2), `surface`(1.5), `shore`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.oceanographer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.oceanographer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.oceanographer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.oceanographer.thanks`: the villager accepts. Subject `work.oceanographer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.thanks/1   [63 chars]
    en  Few do. From the shore it looks like swimming with extra steps.
    >>  ............................................
    pt  Poucos pensam. Da praia parece nadar com passos extras.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.thanks/2   [75 chars]
    en  The surface is the only part anyone sees, %1$s. That's true of most things.
    >>  ............................................
    pt  A superfície é a única parte que se vê, %1$s. Vale pra quase tudo.
    >>  ............................................
```


### Button `ask_more` — "What have you brought up that mattered?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.challenge.landed`, `work.oceanographer.challenge.stung`, `work.oceanographer.craft.admire`, `work.oceanographer.craft.ask_books`, `work.oceanographer.craft.ask_eleven`, `work.oceanographer.future.ask_gap`, `work.oceanographer.future.ask_second_post`, `work.oceanographer.future.encourage`, `work.oceanographer.hard`, `work.oceanographer.risk.ask_houses`, `work.oceanographer.risk.ask_warning`, `work.oceanographer.risk.sympathise`, `work.oceanographer.task.ask_above`, `work.oceanographer.task.ask_census`, `work.oceanographer.task.offer_hands`, `work.oceanographer.value`, `work.oceanographer.village.ask_fishermen`, `work.oceanographer.village.ask_post`, `work.oceanographer.village.say_thanks` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.oceanographer.more` — accepted phrasings: "what have you brought up that mattered"
  - the message must contain one of: `brought`, `mattered`, `found`
  - scored words: `brought`(1.5), `mattered`(1.5), `found`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.followup.ask_more   [39 chars]
    en  What have you brought up that mattered?
    >>  ............................................
    pt  O que você trouxe que importou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.oceanographer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.more
WHO    VILLAGER — what the player reads after pressing "What have you brought up that mattered?"
       spoken on: conversations.topic.work.oceanographer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.oceanographer.more`: the villager discloses. Subject `work.oceanographer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.more/1   [89 chars]
    en  A tablet with our village's name on it, in a script older than the village. Explain that.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Explique isso.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.more/2   [91 chars]
    en  A ship's bell. It had a date. The date was wrong by two hundred years and I've told nobody.
    >>  ............................................
    pt  O sino de um navio. Tinha uma data. A data estava errada por duzentos anos e eu não contei a ninguém.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our name on it, older than us. I've not shown it to anyone and I don't know why.
    >>  ............................................
    pt  Uma tábua com o nosso nome, mais velha que nós. Não mostrei a ninguém e não sei por quê.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Every year I wait is a year the comparison doesn't have, and I know that arithmetic.
    >>  ............................................
    pt  Um segundo poste. Cada ano que eu espero é um ano que a comparação não tem, e eu sei essa conta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Older script than the village. It has waited a long while; it can wait for me to be sure.
    >>  ............................................
    pt  Uma tábua. Escrita mais velha que o vilarejo. Esperou muito; pode esperar eu ter certeza.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Twenty years of walking there is a reason to start now rather than a reason not to.
    >>  ............................................
    pt  Um segundo poste. Vinte anos indo lá é motivo pra começar agora e não motivo pra não começar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it, in a script older than the village. Explain that.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Explique isso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post on the far headland. Two coasts turn nineteen years of marks into an argument.
    >>  ............................................
    pt  Um segundo poste no promontório distante. Duas costas transformam dezenove anos de marcas num argumento.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it, in a script older than the village. Explain that.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Explique isso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post on the far headland. Two coasts turn nineteen years of marks into an argument.
    >>  ............................................
    pt  Um segundo poste no promontório distante. Duas costas transformam dezenove anos de marcas num argumento.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our name on it, older than the village. Come and look at it — nobody else has.
    >>  ............................................
    pt  Uma tábua com o nosso nome, mais velha que o vilarejo. Venha ver — mais ninguém veio.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Walk out to the headland with me some week and we'll drive it together.
    >>  ............................................
    pt  Um segundo poste. Ande até o promontório comigo numa semana e a gente crava junto.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our name on it, older than the village. Come and look at it — nobody else has.
    >>  ............................................
    pt  Uma tábua com o nosso nome, mais velha que o vilarejo. Venha ver — mais ninguém veio.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Walk out to the headland with me some week and we'll drive it together.
    >>  ............................................
    pt  Um segundo poste. Ande até o promontório comigo numa semana e a gente crava junto.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our name on it, older than the village. Come and look at it — nobody else has.
    >>  ............................................
    pt  Uma tábua com o nosso nome, mais velha que o vilarejo. Venha ver — mais ninguém veio.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Walk out to the headland with me some week and we'll drive it together.
    >>  ............................................
    pt  Um segundo poste. Ande até o promontório comigo numa semana e a gente crava junto.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our name on it, older than us. I've not shown it to anyone and I don't know why.
    >>  ............................................
    pt  Uma tábua com o nosso nome, mais velha que nós. Não mostrei a ninguém e não sei por quê.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Every year I wait is a year the comparison doesn't have, and I know that arithmetic.
    >>  ............................................
    pt  Um segundo poste. Cada ano que eu espero é um ano que a comparação não tem, e eu sei essa conta.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it, in a script older than the village. Explain that.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Explique isso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post on the far headland. Two coasts turn nineteen years of marks into an argument.
    >>  ............................................
    pt  Um segundo poste no promontório distante. Duas costas transformam dezenove anos de marcas num argumento.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it, in a script older than the village. Explain that.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Explique isso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post on the far headland. Two coasts turn nineteen years of marks into an argument.
    >>  ............................................
    pt  Um segundo poste no promontório distante. Duas costas transformam dezenove anos de marcas num argumento.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Our village's name, in a script older than the village. I've told nobody.
    >>  ............................................
    pt  Uma tábua. O nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Não contei a ninguém.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post, on the far headland. Anywhere that isn't here. That is the entire specification.
    >>  ............................................
    pt  Um segundo poste, no promontório distante. Qualquer lugar que não aqui. É toda a especificação.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Older script than the village. It has waited a long while; it can wait for me to be sure.
    >>  ............................................
    pt  Uma tábua. Escrita mais velha que o vilarejo. Esperou muito; pode esperar eu ter certeza.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Twenty years of walking there is a reason to start now rather than a reason not to.
    >>  ............................................
    pt  Um segundo poste. Vinte anos indo lá é motivo pra começar agora e não motivo pra não começar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Our village's name, in a script older than the village. I've told nobody.
    >>  ............................................
    pt  Uma tábua. O nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Não contei a ninguém.
    >>  ............................................
  odd.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post, on the far headland. Anywhere that isn't here. That is the entire specification.
    >>  ............................................
    pt  Um segundo poste, no promontório distante. Qualquer lugar que não aqui. É toda a especificação.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Older script than the village. It has waited a long while; it can wait for me to be sure.
    >>  ............................................
    pt  Uma tábua. Escrita mais velha que o vilarejo. Esperou muito; pode esperar eu ter certeza.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Twenty years of walking there is a reason to start now rather than a reason not to.
    >>  ............................................
    pt  Um segundo poste. Vinte anos indo lá é motivo pra começar agora e não motivo pra não começar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it! In older script than the village! Explain THAT.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo! Em escrita mais velha que o vilarejo! Explique ISSO.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Two days' walk, one afternoon's work, and twenty years of walking there. Bargain.
    >>  ............................................
    pt  Um segundo poste. Dois dias de caminhada, uma tarde de trabalho, e vinte anos indo lá. Pechincha.
    >>  ............................................
  playful.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it! In older script than the village! Explain THAT.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo! Em escrita mais velha que o vilarejo! Explique ISSO.
    >>  ............................................
  playful.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Two days' walk, one afternoon's work, and twenty years of walking there. Bargain.
    >>  ............................................
    pt  Um segundo poste. Dois dias de caminhada, uma tarde de trabalho, e vinte anos indo lá. Pechincha.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Older script than the village. It has waited a long while; it can wait for me to be sure.
    >>  ............................................
    pt  Uma tábua. Escrita mais velha que o vilarejo. Esperou muito; pode esperar eu ter certeza.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Twenty years of walking there is a reason to start now rather than a reason not to.
    >>  ............................................
    pt  Um segundo poste. Vinte anos indo lá é motivo pra começar agora e não motivo pra não começar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our name on it, older than us. I've not shown it to anyone and I don't know why.
    >>  ............................................
    pt  Uma tábua com o nosso nome, mais velha que nós. Não mostrei a ninguém e não sei por quê.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Every year I wait is a year the comparison doesn't have, and I know that arithmetic.
    >>  ............................................
    pt  Um segundo poste. Cada ano que eu espero é um ano que a comparação não tem, e eu sei essa conta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet. Our village's name, in a script older than the village. I've told nobody.
    >>  ............................................
    pt  Uma tábua. O nome do nosso vilarejo, numa escrita mais velha que o vilarejo. Não contei a ninguém.
    >>  ............................................
  shy.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post, on the far headland. Anywhere that isn't here. That is the entire specification.
    >>  ............................................
    pt  Um segundo poste, no promontório distante. Qualquer lugar que não aqui. É toda a especificação.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it! In older script than the village! Explain THAT.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo! Em escrita mais velha que o vilarejo! Explique ISSO.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Two days' walk, one afternoon's work, and twenty years of walking there. Bargain.
    >>  ............................................
    pt  Um segundo poste. Dois dias de caminhada, uma tarde de trabalho, e vinte anos indo lá. Pechincha.
    >>  ............................................
  witty.dialogue.conversations.work.prof.oceanographer.more/1
    en  A tablet with our village's name on it! In older script than the village! Explain THAT.
    >>  ............................................
    pt  Uma tábua com o nome do nosso vilarejo! Em escrita mais velha que o vilarejo! Explique ISSO.
    >>  ............................................
  witty.dialogue.conversations.work.prof.oceanographer.more/2
    en  A second post. Two days' walk, one afternoon's work, and twenty years of walking there. Bargain.
    >>  ............................................
    pt  Um segundo poste. Dois dias de caminhada, uma tarde de trabalho, e vinte anos indo lá. Pechincha.
    >>  ............................................
```

</details>


### Button `leave` — "Calm water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.challenge.landed`, `work.oceanographer.challenge.stung`, `work.oceanographer.craft.admire`, `work.oceanographer.craft.ask_books`, `work.oceanographer.craft.ask_eleven`, `work.oceanographer.future.ask_gap`, `work.oceanographer.future.ask_second_post`, `work.oceanographer.future.encourage`, `work.oceanographer.hard`, `work.oceanographer.risk.ask_houses`, `work.oceanographer.risk.ask_warning`, `work.oceanographer.risk.sympathise`, `work.oceanographer.task.ask_above`, `work.oceanographer.task.ask_census`, `work.oceanographer.task.offer_hands`, `work.oceanographer.value`, `work.oceanographer.village.ask_fishermen`, `work.oceanographer.village.ask_post`, `work.oceanographer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.followup.leave   [11 chars]
    en  Calm water.
    >>  ............................................
    pt  Águas calmas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "Calm water."
       spoken on: conversations.topic.work.oceanographer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer.future` — e.g. "Two posts, not one. A second coast to compare against turns nineteen years of marks into an argument."


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.future.respond   [26 chars]
    en  That's what has to happen.
    >>  ............................................
    pt  É o que tem que acontecer.
    >>  ............................................
```


### Button `ask_second_post` — "Where would the second post go?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.future` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.future.ask_second_post` — accepted phrasings: "where would the second post go"
  - the message must contain one of: `post`, `second`, `headland`
  - scored words: `post`(1.2), `second`(1.5), `headland`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.future.respond.ask_second_post
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.future.respond.ask_second_post   [31 chars]
    en  Where would the second post go?
    >>  ............................................
    pt  Onde iria o segundo poste?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.future.ask_second_post`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.future.ask_second_post
WHO    VILLAGER — what the player reads after pressing "Where would the second post go?"
       spoken on: conversations.topic.work.oceanographer.future.respond, button `ask_second_post`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.future.ask_second_post`: the villager explains. Subject `work.oceanographer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.future.ask_second_post/1   [98 chars]
    en  The far headland. Two days' walk and one afternoon's work, and then twenty years of walking there.
    >>  ............................................
    pt  No promontório distante. Dois dias de caminhada e uma tarde de trabalho, e depois vinte anos indo lá.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.future.ask_second_post/2   [96 chars]
    en  Anywhere that isn't here, %1$s. That's the entire specification and it's why nobody has done it.
    >>  ............................................
    pt  Em qualquer lugar que não aqui, %1$s. É toda a especificação e é por isso que ninguém fez.
    >>  ............................................
```


### Button `encourage` — "Twenty years of walking there is a reason to start now."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.future` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.future.encourage` — accepted phrasings: "twenty years of walking there is a reason to start now"
  - the message must contain one of: `start`, `now`, `twenty`
  - scored words: `start`(1.5), `now`(1.0), `twenty`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.future.respond.encourage   [55 chars]
    en  Twenty years of walking there is a reason to start now.
    >>  ............................................
    pt  Vinte anos indo lá é motivo pra começar agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.oceanographer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.oceanographer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Twenty years of walking there is a reason to start now."
       spoken on: conversations.topic.work.oceanographer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.future.encourage`: the villager accepts. Subject `work.oceanographer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.future.encourage/1   [97 chars]
    en  ...Now. Yes. Every year I wait is a year the comparison doesn't have, and I know that arithmetic.
    >>  ............................................
    pt  ...Agora. Sim. Cada ano que eu espero é um ano que a comparação não tem, e eu sei essa conta.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.future.encourage/2   [77 chars]
    en  I've been waiting for permission that nobody was ever going to give me, %1$s.
    >>  ............................................
    pt  Venho esperando uma permissão que ninguém ia me dar, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. I know the arithmetic and I've been hiding behind not knowing.
    >>  ............................................
    pt  ...Agora. Eu sei a conta e venho me escondendo atrás de não saber.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. It's easier to want a thing than to start it.
    >>  ............................................
    pt  Estive esperando permissão. É mais fácil querer algo do que começar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Nineteen years teaches you exactly what a year of waiting costs.
    >>  ............................................
    pt  ...Agora. Dezenove anos ensinam exatamente quanto custa um ano de espera.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. At my age you notice nobody is coming to give it.
    >>  ............................................
    pt  Estive esperando permissão. Na minha idade você nota que ninguém vem dar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Yes. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora. Sim. Cada ano que eu espero é um ano que a comparação não tem.
    >>  ............................................
  confident.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Yes. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora. Sim. Cada ano que eu espero é um ano que a comparação não tem.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now, %1$s. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora, %1$s. Cada ano que espero é um ano que a comparação não tem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission nobody was going to give. You've just given it.
    >>  ............................................
    pt  Estive esperando permissão que ninguém ia dar. Você acabou de dar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now, %1$s. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora, %1$s. Cada ano que espero é um ano que a comparação não tem.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission nobody was going to give. You've just given it.
    >>  ............................................
    pt  Estive esperando permissão que ninguém ia dar. Você acabou de dar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now, %1$s. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora, %1$s. Cada ano que espero é um ano que a comparação não tem.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission nobody was going to give. You've just given it.
    >>  ............................................
    pt  Estive esperando permissão que ninguém ia dar. Você acabou de dar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. I know the arithmetic and I've been hiding behind not knowing.
    >>  ............................................
    pt  ...Agora. Eu sei a conta e venho me escondendo atrás de não saber.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. It's easier to want a thing than to start it.
    >>  ............................................
    pt  Estive esperando permissão. É mais fácil querer algo do que começar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Yes. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora. Sim. Cada ano que eu espero é um ano que a comparação não tem.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Yes. Every year I wait is a year the comparison doesn't have.
    >>  ............................................
    pt  ...Agora. Sim. Cada ano que eu espero é um ano que a comparação não tem.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Every year waited is a year lost to it.
    >>  ............................................
    pt  ...Agora. Cada ano esperado é um ano perdido.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  Waiting for permission nobody would give.
    >>  ............................................
    pt  Esperando permissão que ninguém daria.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Nineteen years teaches you exactly what a year of waiting costs.
    >>  ............................................
    pt  ...Agora. Dezenove anos ensinam exatamente quanto custa um ano de espera.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. At my age you notice nobody is coming to give it.
    >>  ............................................
    pt  Estive esperando permissão. Na minha idade você nota que ninguém vem dar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Every year waited is a year lost to it.
    >>  ............................................
    pt  ...Agora. Cada ano esperado é um ano perdido.
    >>  ............................................
  odd.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  Waiting for permission nobody would give.
    >>  ............................................
    pt  Esperando permissão que ninguém daria.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Nineteen years teaches you exactly what a year of waiting costs.
    >>  ............................................
    pt  ...Agora. Dezenove anos ensinam exatamente quanto custa um ano de espera.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. At my age you notice nobody is coming to give it.
    >>  ............................................
    pt  Estive esperando permissão. Na minha idade você nota que ninguém vem dar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now! Yes. Every year I wait is a year the comparison simply doesn't have.
    >>  ............................................
    pt  ...Agora! Sim. Cada ano que espero é um ano que a comparação simplesmente não tem.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me. Ridiculous.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar. Ridículo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now! Yes. Every year I wait is a year the comparison simply doesn't have.
    >>  ............................................
    pt  ...Agora! Sim. Cada ano que espero é um ano que a comparação simplesmente não tem.
    >>  ............................................
  playful.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me. Ridiculous.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar. Ridículo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Nineteen years teaches you exactly what a year of waiting costs.
    >>  ............................................
    pt  ...Agora. Dezenove anos ensinam exatamente quanto custa um ano de espera.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. At my age you notice nobody is coming to give it.
    >>  ............................................
    pt  Estive esperando permissão. Na minha idade você nota que ninguém vem dar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. I know the arithmetic and I've been hiding behind not knowing.
    >>  ............................................
    pt  ...Agora. Eu sei a conta e venho me escondendo atrás de não saber.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission. It's easier to want a thing than to start it.
    >>  ............................................
    pt  Estive esperando permissão. É mais fácil querer algo do que começar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now. Every year waited is a year lost to it.
    >>  ............................................
    pt  ...Agora. Cada ano esperado é um ano perdido.
    >>  ............................................
  shy.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  Waiting for permission nobody would give.
    >>  ............................................
    pt  Esperando permissão que ninguém daria.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now! Yes. Every year I wait is a year the comparison simply doesn't have.
    >>  ............................................
    pt  ...Agora! Sim. Cada ano que espero é um ano que a comparação simplesmente não tem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me. Ridiculous.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar. Ridículo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.oceanographer.future.encourage/1
    en  ...Now! Yes. Every year I wait is a year the comparison simply doesn't have.
    >>  ............................................
    pt  ...Agora! Sim. Cada ano que espero é um ano que a comparação simplesmente não tem.
    >>  ............................................
  witty.dialogue.conversations.work.prof.oceanographer.future.encourage/2
    en  I've been waiting for permission that nobody was ever going to give me. Ridiculous.
    >>  ............................................
    pt  Estive esperando uma permissão que ninguém ia me dar. Ridículo.
    >>  ............................................
```

</details>


### Button `ask_gap` — "Why is a gap so bad?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.future` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.future.ask_gap` — accepted phrasings: "why is a gap so bad"
  - the message must contain one of: `gap`, `record`, `missing`
  - scored words: `gap`(1.5), `record`(1.0), `missing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.future.respond.ask_gap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.future.respond.ask_gap   [20 chars]
    en  Why is a gap so bad?
    >>  ............................................
    pt  Por que uma lacuna é tão ruim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.future.ask_gap`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.future.ask_gap
WHO    VILLAGER — what the player reads after pressing "Why is a gap so bad?"
       spoken on: conversations.topic.work.oceanographer.future.respond, button `ask_gap`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.future.ask_gap`: the villager explains. Subject `work.oceanographer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.future.ask_gap/1   [99 chars]
    en  Because you can't tell whether the thing you missed was the ordinary year or the one that mattered.
    >>  ............................................
    pt  Porque você não sabe se o que perdeu foi o ano comum ou o que importava.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.future.ask_gap/2   [90 chars]
    en  Because a tide is a shape, %1$s, and a shape with a hole in it is just two shorter shapes.
    >>  ............................................
    pt  Porque uma maré é uma forma, %1$s, e uma forma com buraco são só duas formas menores.
    >>  ............................................
```


### Button `leave` — "I'll let you get to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.future.respond.leave   [30 chars]
    en  I'll let you get to the water.
    >>  ............................................
    pt  Vou deixar você ir pra água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to the water."
       spoken on: conversations.topic.work.oceanographer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer` — e.g. "The sea keeps better secrets than any of us. I've surfaced with a few. Traded most for dry socks."


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.respond   [40 chars]
    en  That's the deep and the guardians in it.
    >>  ............................................
    pt  É o fundo e os guardiões nele.
    >>  ............................................
```


### Button `ask_hard` — "How long can you stay down?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.identity` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.hard` — accepted phrasings: "how long can you stay down"
  - the message must contain one of: `stay`, `breath`
  - scored words: `stay`(1.2), `breath`(1.5), `down`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.respond.ask_hard   [27 chars]
    en  How long can you stay down?
    >>  ............................................
    pt  Quanto tempo você aguenta lá embaixo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.oceanographer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.hard
WHO    VILLAGER — what the player reads after pressing "How long can you stay down?"
       spoken on: conversations.topic.work.oceanographer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.hard`: the villager explains. Subject `work.oceanographer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.oceanographer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You bring up things nobody else can reach."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.identity` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.value` — accepted phrasings: "you bring up things nobody else can reach"
  - the message must contain one of: `reach`, `bring`, `recover`
  - scored words: `reach`(1.5), `bring`(1.0), `recover`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.respond.value   [42 chars]
    en  You bring up things nobody else can reach.
    >>  ............................................
    pt  Você traz coisas que mais ninguém alcança.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.oceanographer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.oceanographer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.value
WHO    VILLAGER — what the player reads after pressing "You bring up things nobody else can reach."
       spoken on: conversations.topic.work.oceanographer.respond, button `value`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.value`: the villager accepts. Subject `work.oceanographer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.value/1   [78 chars]
    en  I do. Most of them are pottery. Two of them were not, and I think about those.
    >>  ............................................
    pt  Trago. A maioria é cerâmica. Duas não eram, e eu penso nessas.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.value/2   [73 chars]
    en  Aye. And I trade half of it for dry socks, which is a poor exchange rate.
    >>  ............................................
    pt  É. E troco metade por meias secas, o que é um câmbio ruim.
    >>  ............................................
```


### Button `challenge` — "You're going to drown one day."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.oceanographer.identity` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.challenge` — accepted phrasings: "you're going to drown one day"
  - the message must contain one of: `dangerous`, `die`, `drown`
  - scored words: `dangerous`(1.0), `die`(1.2), `drown`(2.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.respond.challenge   [30 chars]
    en  You're going to drown one day.
    >>  ............................................
    pt  Você vai se afogar um dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.oceanographer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.oceanographer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're going to drown one day."
       spoken on: conversations.topic.work.oceanographer.respond, button `challenge`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.challenge.landed`: the villager resists. Subject `work.oceanographer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.challenge.landed/1   [76 chars]
    en  Statistically, yes. I have arranged for the notes to reach the cartographer.
    >>  ............................................
    pt  Estatisticamente, sim. Já providenciei que as anotações cheguem ao cartógrafo.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.challenge.landed/2   [60 chars]
    en  Probably. It's a better ending than most trades offer, %1$s.
    >>  ............................................
    pt  Provavelmente. É um final melhor do que a maioria dos ofícios oferece, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.oceanographer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.oceanographer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're going to drown one day."
       spoken on: conversations.topic.work.oceanographer.respond, button `challenge`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.challenge.stung`: the villager resists. Subject `work.oceanographer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.challenge.stung/1   [56 chars]
    en  ...I have never once gone down without a line. Not once.
    >>  ............................................
    pt  ...Eu nunca desci sem uma corda. Nunca.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.challenge.stung/2   [65 chars]
    en  Drown. Yes. Thank you, that's very restful to hear before a dive.
    >>  ............................................
    pt  Afogar. Sim. Obrigado, muito reconfortante de se ouvir antes de um mergulho.
    >>  ............................................
```


### Button `leave` — "I'll let you get to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.respond.leave   [30 chars]
    en  I'll let you get to the water.
    >>  ............................................
    pt  Vou deixar você ir pra água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to the water."
       spoken on: conversations.topic.work.oceanographer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer.risk` — e.g. "If I'm right about the lower houses, I have to say so, and saying so moves four families."


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.risk.respond   [25 chars]
    en  That's what I'm carrying.
    >>  ............................................
    pt  É o que eu carrego.
    >>  ............................................
```


### Button `ask_houses` — "Are you going to say so?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.risk` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.risk.ask_houses` — accepted phrasings: "are you going to say so"
  - the message must contain one of: `houses`, `warn`
  - scored words: `houses`(1.5), `say`(0.6), `warn`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.risk.respond.ask_houses
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.risk.respond.ask_houses   [24 chars]
    en  Are you going to say so?
    >>  ............................................
    pt  Você vai dizer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.risk.ask_houses`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.risk.ask_houses
WHO    VILLAGER — what the player reads after pressing "Are you going to say so?"
       spoken on: conversations.topic.work.oceanographer.risk.respond, button `ask_houses`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.risk.ask_houses`: the villager explains. Subject `work.oceanographer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.risk.ask_houses/1   [92 chars]
    en  Not on one month. On two I will, and I'll be the man who was wrong if the third month drops.
    >>  ............................................
    pt  Com um mês, não. Com dois eu digo, e serei o homem que errou se o terceiro mês cair.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.risk.ask_houses/2   [88 chars]
    en  I've written it and not sent it, %1$s, and I've read what I wrote every night this week.
    >>  ............................................
    pt  Escrevi e não enviei, %1$s, e eu reli o que escrevi toda noite desta semana.
    >>  ............................................
```


### Button `sympathise` — "Being right and being believed are two separate problems."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.risk` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.risk.sympathise` — accepted phrasings: "being right and being believed are two separate problems"
  - the message must contain one of: `believed`, `right`, `separate`
  - scored words: `believed`(1.5), `right`(1.0), `separate`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.risk.respond.sympathise   [57 chars]
    en  Being right and being believed are two separate problems.
    >>  ............................................
    pt  Estar certo e ser acreditado são dois problemas separados.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.oceanographer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.oceanographer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being right and being believed are two separate problems."
       spoken on: conversations.topic.work.oceanographer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.risk.sympathise`: the villager accepts. Subject `work.oceanographer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.risk.sympathise/1   [76 chars]
    en  ...And I have solved one of them and have no idea how to approach the other.
    >>  ............................................
    pt  ...E eu resolvi um deles e não tenho ideia de como abordar o outro.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.risk.sympathise/2   [97 chars]
    en  Nineteen years on the first, %1$s, and not one afternoon spent on the second. That was a mistake.
    >>  ............................................
    pt  Dezenove anos no primeiro, %1$s, e nem uma tarde no segundo. Foi um erro.
    >>  ............................................
```


### Button `ask_warning` — "The marks give no warning at all?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.risk` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.risk.ask_warning` — accepted phrasings: "the marks give no warning at all"
  - the message must contain one of: `warning`, `marks`, `storm`
  - scored words: `warning`(1.5), `marks`(1.0), `storm`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.risk.respond.ask_warning
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.risk.respond.ask_warning   [33 chars]
    en  The marks give no warning at all?
    >>  ............................................
    pt  As marcas não dão aviso nenhum?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.risk.ask_warning`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.risk.ask_warning
WHO    VILLAGER — what the player reads after pressing "The marks give no warning at all?"
       spoken on: conversations.topic.work.oceanographer.risk.respond, button `ask_warning`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.risk.ask_warning`: the villager explains. Subject `work.oceanographer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.risk.ask_warning/1   [83 chars]
    en  They give me a year's warning about a decade and no warning at all about a Tuesday.
    >>  ............................................
    pt  Dão um ano de aviso sobre uma década e nenhum aviso sobre uma terça.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.risk.ask_warning/2   [88 chars]
    en  A storm is a storm, %1$s. What I measure is what the sea does when nothing is happening.
    >>  ............................................
    pt  Tempestade é tempestade, %1$s. O que eu meço é o que o mar faz quando nada acontece.
    >>  ............................................
```


### Button `leave` — "I'll let you get to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.risk.respond.leave   [30 chars]
    en  I'll let you get to the water.
    >>  ............................................
    pt  Vou deixar você ir pra água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to the water."
       spoken on: conversations.topic.work.oceanographer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer.task` — e.g. "Reading the tide board. Nineteen years of marks on one post, and this month sits above all of them."


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.task.respond   [23 chars]
    en  That's the shore today.
    >>  ............................................
    pt  É a costa hoje.
    >>  ............................................
```


### Button `ask_above` — "Above all of them — is that bad?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.task` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.task.ask_above` — accepted phrasings: "above all of them — is that bad"
  - the message must contain one of: `above`, `marks`, `tide`
  - scored words: `above`(1.5), `marks`(1.2), `tide`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.task.respond.ask_above
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.task.respond.ask_above   [32 chars]
    en  Above all of them — is that bad?
    >>  ............................................
    pt  Acima de todas — isso é ruim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.task.ask_above`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.task.ask_above
WHO    VILLAGER — what the player reads after pressing "Above all of them — is that bad?"
       spoken on: conversations.topic.work.oceanographer.task.respond, button `ask_above`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.task.ask_above`: the villager explains. Subject `work.oceanographer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.task.ask_above/1   [95 chars]
    en  It's one month. Two months would be a pattern and a pattern would mean moving the lower houses.
    >>  ............................................
    pt  É um mês. Dois meses seria padrão e um padrão significaria mudar as casas baixas.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.task.ask_above/2   [94 chars]
    en  It's not bad. It's the first time, %1$s, and first times are what I write down most carefully.
    >>  ............................................
    pt  Não é ruim. É a primeira vez, %1$s, e primeiras vezes são o que eu anoto com mais cuidado.
    >>  ............................................
```


### Button `offer_hands` — "I'll count the north shore."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.oceanographer.task` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.task.offer_hands` — accepted phrasings: "i'll count the north shore"
  - the message must contain one of: `shore`, `count`, `north`
  - scored words: `shore`(1.5), `count`(1.2), `north`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.task.respond.offer_hands   [27 chars]
    en  I'll count the north shore.
    >>  ............................................
    pt  Eu conto a costa norte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.oceanographer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.oceanographer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll count the north shore."
       spoken on: conversations.topic.work.oceanographer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.task.offer_hands`: the villager accepts. Subject `work.oceanographer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.task.offer_hands/1   [85 chars]
    en  ...Do. Weed, shell, timber, and anything that came off a boat. Keep those four apart.
    >>  ............................................
    pt  ...Conte. Alga, concha, madeira, e qualquer coisa de barco. Mantenha os quatro separados.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.task.offer_hands/2   [103 chars]
    en  Then start at the point and don't skip the ugly stretch, %1$s. The ugly stretch is the informative one.
    >>  ............................................
    pt  Então comece na ponta e não pule o trecho feio, %1$s. O trecho feio é o informativo.
    >>  ............................................
```


### Button `ask_census` — "What does the census tell you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.task` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.task.ask_census` — accepted phrasings: "what does the census tell you"
  - the message must contain one of: `census`, `tells`, `current`
  - scored words: `census`(1.5), `tells`(1.0), `current`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.task.respond.ask_census
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.task.respond.ask_census   [30 chars]
    en  What does the census tell you?
    >>  ............................................
    pt  O que o censo te diz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.task.ask_census`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.task.ask_census
WHO    VILLAGER — what the player reads after pressing "What does the census tell you?"
       spoken on: conversations.topic.work.oceanographer.task.respond, button `ask_census`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.task.ask_census`: the villager explains. Subject `work.oceanographer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.task.ask_census/1   [89 chars]
    en  Where the current has moved. Weed from the far headland means it's swung two points west.
    >>  ............................................
    pt  Pra onde a corrente foi. Alga do promontório distante significa que virou dois pontos a oeste.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.task.ask_census/2   [88 chars]
    en  What the fishermen will find in a fortnight, %1$s, though none of them has asked me yet.
    >>  ............................................
    pt  O que os pescadores vão achar em quinze dias, %1$s, embora nenhum tenha me perguntado ainda.
    >>  ............................................
```


### Button `leave` — "I'll let you get to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.task.respond.leave   [30 chars]
    en  I'll let you get to the water.
    >>  ............................................
    pt  Vou deixar você ir pra água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to the water."
       spoken on: conversations.topic.work.oceanographer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.oceanographer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.oceanographer.village` — e.g. "The fishermen go where the current is and the current is what I count. They don't know that and it's fine."


```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.oceanographer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.oceanographer.village.respond   [21 chars]
    en  That's what it's for.
    >>  ............................................
    pt  É pra isso que serve.
    >>  ............................................
```


### Button `ask_fishermen` — "Why not tell the fishermen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.village` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.village.ask_fishermen` — accepted phrasings: "why not tell the fishermen"
  - the message must contain one of: `fishermen`, `boats`
  - scored words: `fishermen`(1.5), `tell`(0.8), `boats`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.village.respond.ask_fishermen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.village.respond.ask_fishermen   [27 chars]
    en  Why not tell the fishermen?
    >>  ............................................
    pt  Por que não contar aos pescadores?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.village.ask_fishermen`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.village.ask_fishermen
WHO    VILLAGER — what the player reads after pressing "Why not tell the fishermen?"
       spoken on: conversations.topic.work.oceanographer.village.respond, button `ask_fishermen`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.village.ask_fishermen`: the villager explains. Subject `work.oceanographer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.village.ask_fishermen/1   [96 chars]
    en  I did, twice, and both times it was received as a man on the shore telling boats their business.
    >>  ............................................
    pt  Contei, duas vezes, e nas duas foi recebido como um homem da praia dizendo a barcos o que fazer.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.village.ask_fishermen/2   [94 chars]
    en  One of them listens. He doesn't say he does and he goes where I said, %1$s, and that's enough.
    >>  ............................................
    pt  Um deles escuta. Ele não diz que escuta e vai aonde eu disse, %1$s, e isso basta.
    >>  ............................................
```


### Button `say_thanks` — "The only record anywhere is worth more than a post."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.oceanographer.village` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.village.say_thanks` — accepted phrasings: "the only record anywhere is worth more than a post"
  - the message must contain one of: `record`, `only`, `anywhere`
  - scored words: `record`(1.5), `only`(1.0), `anywhere`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.village.respond.say_thanks   [51 chars]
    en  The only record anywhere is worth more than a post.
    >>  ............................................
    pt  O único registro em qualquer lugar vale mais que um poste.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.oceanographer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.oceanographer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "The only record anywhere is worth more than a post."
       spoken on: conversations.topic.work.oceanographer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.village.say_thanks`: the villager accepts. Subject `work.oceanographer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.village.say_thanks/1   [89 chars]
    en  ...It's worth exactly as much as the next person who reads it, which is currently nobody.
    >>  ............................................
    pt  ...Vale exatamente quanto a próxima pessoa que ler, que atualmente é ninguém.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.village.say_thanks/2   [94 chars]
    en  Then I should copy it onto something that isn't outdoors, %1$s. I've known that for six years.
    >>  ............................................
    pt  Então eu devia copiar pra algo que não fique ao relento, %1$s. Sei disso há seis anos.
    >>  ............................................
```


### Button `ask_post` — "What happens to the post in a storm?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.oceanographer.village` · offered only once the villager has actually said `work:oceanographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.oceanographer.village.ask_post` — accepted phrasings: "what happens to the post in a storm"
  - the message must contain one of: `post`, `storm`, `replaced`
  - scored words: `post`(1.5), `storm`(1.2), `replaced`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.village.respond.ask_post
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.village.respond.ask_post   [36 chars]
    en  What happens to the post in a storm?
    >>  ............................................
    pt  O que acontece com o poste numa tempestade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.oceanographer.village.ask_post`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.oceanographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you brought up that mattered?" | "Calm water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.village.ask_post
WHO    VILLAGER — what the player reads after pressing "What happens to the post in a storm?"
       spoken on: conversations.topic.work.oceanographer.village.respond, button `ask_post`
       leaves the player on: conversations.topic.work.oceanographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.village.ask_post`: the villager explains. Subject `work.oceanographer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.oceanographer.village.ask_post/1   [99 chars]
    en  It's been replaced twice and I transferred every mark by hand both times, in one night, both times.
    >>  ............................................
    pt  Já foi trocado duas vezes e eu transferi cada marca à mão nas duas, numa noite, nas duas.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.village.ask_post/2   [87 chars]
    en  It holds. It's the third post, %1$s, and each one has been driven deeper than the last.
    >>  ............................................
    pt  Ele aguenta. É o terceiro poste, %1$s, e cada um foi cravado mais fundo que o anterior.
    >>  ............................................
```


### Button `leave` — "I'll let you get to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.oceanographer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.oceanographer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.oceanographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.oceanographer.village.respond.leave   [30 chars]
    en  I'll let you get to the water.
    >>  ............................................
    pt  Vou deixar você ir pra água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to the water."
       spoken on: conversations.topic.work.oceanographer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.left`: the villager accepts. Subject `work.oceanographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.oceanographer.broken_instrument.blocked.respond / leave; conversations.scene.work.oceanographer.broken_instrument.succeeded.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.blocked.respond / leave; conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond / leave; conversations.scene.work.oceanographer.followup / leave; conversations.scene.work.oceanographer.ignored_warning.failed.respond / leave; conversations.scene.work.oceanographer.ignored_warning.remembered.respond / leave; conversations.topic.work.oceanographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.oceanographer.broken_instrument.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

