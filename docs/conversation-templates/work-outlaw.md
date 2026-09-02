# Work talk with a outlaw

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.outlaw.followup`](#conversations-scene-work-outlaw-followup)
- [`conversations.scene.work.outlaw.old_associate.blocked.respond`](#conversations-scene-work-outlaw-old-associate-blocked-respond)
- [`conversations.scene.work.outlaw.old_associate.succeeded.respond`](#conversations-scene-work-outlaw-old-associate-succeeded-respond)
- [`conversations.scene.work.outlaw.old_debt.blocked.respond`](#conversations-scene-work-outlaw-old-debt-blocked-respond)
- [`conversations.scene.work.outlaw.old_debt.succeeded.respond`](#conversations-scene-work-outlaw-old-debt-succeeded-respond)
- [`conversations.scene.work.outlaw.the_name.active.respond`](#conversations-scene-work-outlaw-the-name-active-respond)
- [`conversations.scene.work.outlaw.the_name.succeeded.respond`](#conversations-scene-work-outlaw-the-name-succeeded-respond)
- [`conversations.topic.work.outlaw.craft.respond`](#conversations-topic-work-outlaw-craft-respond)
- [`conversations.topic.work.outlaw.followup`](#conversations-topic-work-outlaw-followup)
- [`conversations.topic.work.outlaw.future.respond`](#conversations-topic-work-outlaw-future-respond)
- [`conversations.topic.work.outlaw.respond`](#conversations-topic-work-outlaw-respond)
- [`conversations.topic.work.outlaw.risk.respond`](#conversations-topic-work-outlaw-risk-respond)
- [`conversations.topic.work.outlaw.task.respond`](#conversations-topic-work-outlaw-task-respond)
- [`conversations.topic.work.outlaw.village.respond`](#conversations-topic-work-outlaw-village-respond)

---

## `conversations.scene.work.outlaw.followup`

**Reached from 11 route(s):** `conversations.scene.work.outlaw.old_associate.blocked.respond` / `ask_what_they_want`; `conversations.scene.work.outlaw.old_associate.blocked.respond` / `advise_telling_someone`; `conversations.scene.work.outlaw.old_associate.succeeded.respond` / `acknowledge_the_cost`; `conversations.scene.work.outlaw.old_debt.blocked.respond` / `ask_why_repay`; `conversations.scene.work.outlaw.old_debt.blocked.respond` / `offer_help_repaying`; `conversations.scene.work.outlaw.old_debt.blocked.respond` / `advise_going_in_person`; `conversations.scene.work.outlaw.old_debt.succeeded.respond` / `ask_what_they_said`; `conversations.scene.work.outlaw.the_name.active.respond` / `ask_if_it_can_change`; `conversations.scene.work.outlaw.the_name.active.respond` / `say_you_judge_by_now`; `conversations.scene.work.outlaw.the_name.active.respond` / `advise_patience`; `conversations.scene.work.outlaw.the_name.succeeded.respond` / `note_it_shifted`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.old_associate.blocked.accepted` — e.g. "That is the right answer and it costs me the last thing I have from before, and I am going to do it tomorrow."
- `conversations.scene.work.outlaw.old_associate.blocked.explained` — e.g. "A door left unlocked, most likely, and a night when I look the other way. That is what I was for, and %2$s has no reason to think it changed."
- `conversations.scene.work.outlaw.old_associate.succeeded.acknowledged` — e.g. "It cost the last person alive who knew me before. I am not asking for sympathy about that. I am saying it out loud once."
- `conversations.scene.work.outlaw.old_debt.blocked.accepted` — e.g. "I will take it, and I will keep a tally, and you will get it back whether you want it or not."
- `conversations.scene.work.outlaw.old_debt.blocked.explained` — e.g. "Because %2$s is a person and I am the reason their year went badly. That is the entire argument and it does not need help."
- `conversations.scene.work.outlaw.old_debt.blocked.resisted` — e.g. "That would be for me. They would have to stand in their own doorway and be gracious to the person who did it."
- `conversations.scene.work.outlaw.old_debt.succeeded.answered` — e.g. "Received in full. That is all. It is the correct amount of warmth and I have read it about forty times."
- `conversations.scene.work.outlaw.the_name.active.accepted` — e.g. "That is the plan and it is a poor one and it is the only one available, which is roughly how I feel about most of my plans."
- `conversations.scene.work.outlaw.the_name.active.explained` — e.g. "Not the knowing. The weight of it, maybe, in about fifteen years, if I am very boring for all fifteen."
- `conversations.scene.work.outlaw.the_name.active.steadied` — e.g. "That is a rarer position than you think, and I would advise you to hold it quietly rather than at the well."
- `conversations.scene.work.outlaw.the_name.succeeded.acknowledged` — e.g. "Four years and one lock. I would like to be more moved by it than I am, and I am aware that says something about me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.followup   [24 chars]
    en  Anything you still want?
    >>  ............................................
    pt  Ainda quer alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a name that follows you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.outlaw.*` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.followup.ask_more` — accepted phrasings: "whats the hardest part of a name that follows you"; "what is the hardest part of a name that follows you"; "hardest thing about a name you cannot lose"
  - the message must contain one of: `hardest`, `name`
  - scored words: `hardest`(1.8), `name`(1.8), `whats`(0.8), `part`(0.8), `follows`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.followup.ask_more   [51 chars]
    en  What's the hardest part of a name that follows you?
    >>  ............................................
    pt  Qual é a parte mais difícil de um nome que te segue?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a name that follows you?"
       spoken on: conversations.scene.work.outlaw.followup, button `ask_more`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.hard`: the villager explains. Subject `work.outlaw.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.outlaw.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.outlaw.hard/1   [70 chars]
    en  A name I can use. Everything else you can replace; that one you can't.
    >>  ............................................
    pt  Um nome que eu possa usar. Tudo mais dá pra repor; esse não.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.hard/2   [81 chars]
    en  Sleep. And a village I could walk into without checking the far door first, %1$s.
    >>  ............................................
    pt  Sono. E um vilarejo em que eu pudesse entrar sem conferir a porta dos fundos, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your own business."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.outlaw.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.followup.leave   [36 chars]
    en  I'll leave you to your own business.
    >>  ............................................
    pt  Vou deixar você com seus assuntos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your own business."
       spoken on: conversations.scene.work.outlaw.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.outlaw.leave/1   [27 chars]
    en  Wise. And you never saw me.
    >>  ............................................
    pt  Sábio. E você nunca me viu.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.leave/2   [50 chars]
    en  Off you go, %1$s. Different way to the one I take.
    >>  ............................................
    pt  Pode ir, %1$s. Por outro caminho, não o meu.
    >>  ............................................
```

---


## `conversations.scene.work.outlaw.old_associate.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.old_associate.blocked` — e.g. "%2$s came through on Tuesday and knew exactly which door was mine."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.old_associate.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond   [12 chars]
    en  The visitor.
    >>  ............................................
    pt  A visita.
    >>  ............................................
```


### Button `ask_what_they_want` — "What do they want from you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.old_associate.blocked` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_associate.blocked.ask_what_they_want` — accepted phrasings: "what do they want from you"; "what do they want from you"; "what are they after"
  - the message must contain one of: `want`, `after`
  - scored words: `want`(1.8), `after`(1.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond.ask_what_they_want
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_associate.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond.ask_what_they_want   [27 chars]
    en  What do they want from you?
    >>  ............................................
    pt  O que querem de você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.old_business`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.old_associate"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What do they want from you?"
       spoken on: conversations.scene.work.outlaw.old_associate.blocked.respond, button `ask_what_they_want`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_associate.blocked.explained`: the villager explains. Subject `work.outlaw.old_business`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.explained/1   [141 chars]
    en  A door left unlocked, most likely, and a night when I look the other way. That is what I was for, and %2$s has no reason to think it changed.
    >>  ............................................
    pt  Uma porta destrancada, provavelmente, e uma noite em que eu olhe para o outro lado. Era para isso que eu servia, e %2$s não tem motivo para achar que mudou.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.explained/2   [80 chars]
    en  Company, possibly. That is the version I would prefer and the one I trust least.
    >>  ............................................
    pt  Companhia, talvez. É a versão que eu preferiria e a de que menos desconfio bem.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.explained/3   [119 chars]
    en  %2$s has not asked yet. That is the part that frightens me — the asking is the easy bit, and %2$s is taking their time.
    >>  ............................................
    pt  %2$s ainda não pediu. É essa a parte que me assusta — pedir é o fácil, e %2$s está tomando seu tempo.
    >>  ............................................
```


### Button `advise_telling_someone` — "Tell the headman before they ask you."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.old_associate.blocked` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_associate.blocked.advise_telling_someone` — accepted phrasings: "tell the headman before they ask you"; "tell the headman before they ask you"; "warn somebody in the village first"
  - the message must contain one of: `headman`, `warn`, `village`
  - scored words: `headman`(1.8), `warn`(1.8), `village`(1.8), `tell`(0.8), `before`(0.8), `ask`(0.8), `somebody`(0.8), `first`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond.advise_telling_someone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_associate.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond.advise_telling_someone   [37 chars]
    en  Tell the headman before they ask you.
    >>  ............................................
    pt  Fale com o chefe antes de te pedirem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.outlaw.visitor.declared`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `work.outlaw.old_business`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.old_associate"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Tell the headman before they ask you."
       spoken on: conversations.scene.work.outlaw.old_associate.blocked.respond, button `advise_telling_someone`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_associate.blocked.accepted`: the villager accepts. Subject `work.outlaw.old_business`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.accepted/1   [109 chars]
    en  That is the right answer and it costs me the last thing I have from before, and I am going to do it tomorrow.
    >>  ............................................
    pt  É a resposta certa e me custa a última coisa que eu tenho de antes, e eu vou fazer amanhã.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.accepted/2   [103 chars]
    en  Yes. Told first, it is information. Found out later, it is a conspiracy, and there is no third version.
    >>  ............................................
    pt  Sim. Contado antes, é informação. Descoberto depois, é conspiração, e não existe terceira versão.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.accepted/3   [122 chars]
    en  It will confirm every suspicion for a fortnight and settle them for good afterwards. I have run the arithmetic four times.
    >>  ............................................
    pt  Vai confirmar todas as suspeitas por duas semanas e encerrá-las de vez depois. Já fiz essa conta quatro vezes.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.outlaw.old_associate.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_associate.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked.respond.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você seguir com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.outlaw.old_associate.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.outlaw.old_associate.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.old_associate.succeeded` — e.g. "I told the headman and %2$s left the district within two days, and I have not heard since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.old_associate.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded.respond   [13 chars]
    en  That visitor.
    >>  ............................................
    pt  Aquela visita.
    >>  ............................................
```


### Button `acknowledge_the_cost` — "That cost you something."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.outlaw.old_associate.succeeded` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_associate.succeeded.acknowledge_the_cost` — accepted phrasings: "that cost you something"; "that cost you something real"; "there was a price in that for you"
  - the message must contain one of: `cost`, `price`
  - scored words: `cost`(1.8), `price`(1.8), `something`(0.8), `real`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.succeeded.respond.acknowledge_the_cost
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_associate.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded.respond.acknowledge_the_cost   [24 chars]
    en  That cost you something.
    >>  ............................................
    pt  Isso te custou algo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +2  _(recorded under topic `work.outlaw.old_business`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.outlaw.old_associate"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "That cost you something."
       spoken on: conversations.scene.work.outlaw.old_associate.succeeded.respond, button `acknowledge_the_cost`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_associate.succeeded.acknowledged`: the villager accepts. Subject `work.outlaw.old_business`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded.acknowledged/1   [120 chars]
    en  It cost the last person alive who knew me before. I am not asking for sympathy about that. I am saying it out loud once.
    >>  ............................................
    pt  Custou a última pessoa viva que me conhecia de antes. Não estou pedindo compaixão por isso. Estou dizendo em voz alta uma vez.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded.acknowledged/2   [116 chars]
    en  Yes. And the cost is the reason it counts. A choice that costs nothing tells you nothing about the person making it.
    >>  ............................................
    pt  Sim. E o custo é o motivo de contar. Uma escolha que não custa nada não diz nada sobre quem escolhe.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded.acknowledged/3   [119 chars]
    en  Thank you for not calling it brave. It was not brave. It was a woman doing sums at her own table at two in the morning.
    >>  ............................................
    pt  Obrigada por não chamar de corajoso. Não foi corajoso. Foi uma mulher fazendo contas na própria mesa às duas da manhã.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.outlaw.old_associate.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_associate.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded.respond.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você seguir com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.outlaw.old_associate.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.outlaw.old_debt.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.old_debt.blocked` — e.g. "I owe %2$s %3$s and I have saved about a third of it, which after two years is not a good rate."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.old_debt.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond   [17 chars]
    en  The old business.
    >>  ............................................
    pt  Os assuntos antigos.
    >>  ............................................
```


### Button `ask_why_repay` — "Why pay it back at all?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.old_debt.blocked` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_debt.blocked.ask_why_repay` — accepted phrasings: "why pay it back at all"; "why pay it back at all"; "what makes you repay it"
  - the message must contain one of: `repay`, `pay`
  - scored words: `repay`(1.8), `pay`(1.8), `why`(0.8), `back`(0.8), `all`(0.8), `makes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.ask_why_repay
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_debt.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.ask_why_repay   [23 chars]
    en  Why pay it back at all?
    >>  ............................................
    pt  Por que pagar de volta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.people_owed`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.old_debt"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Why pay it back at all?"
       spoken on: conversations.scene.work.outlaw.old_debt.blocked.respond, button `ask_why_repay`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_debt.blocked.explained`: the villager explains. Subject `work.outlaw.people_owed`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.explained/1   [122 chars]
    en  Because %2$s is a person and I am the reason their year went badly. That is the entire argument and it does not need help.
    >>  ............................................
    pt  Porque %2$s é uma pessoa e eu sou o motivo de o ano dela ter sido ruim. É o argumento inteiro e não precisa de ajuda.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.explained/2   [109 chars]
    en  Not for absolution. Absolution is a thing you give yourself and I have no interest in it. This is arithmetic.
    >>  ............................................
    pt  Não por absolvição. Absolvição é coisa que a gente dá a si mesma e não me interessa. Isto é aritmética.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.explained/3   [80 chars]
    en  Because otherwise I am a person who says she has changed, and saying it is free.
    >>  ............................................
    pt  Porque senão eu sou uma pessoa que diz que mudou, e dizer é de graça.
    >>  ............................................
```


### Button `offer_help_repaying` — "I'll put emeralds toward it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.outlaw.old_debt.blocked` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_debt.blocked.offer_help_repaying` — accepted phrasings: "ill put emeralds toward it"; "i can put emeralds toward it"; "let me contribute emeralds"
  - the message must contain one of: `emeralds`, `contribute`
  - scored words: `emeralds`(1.8), `contribute`(1.8), `ill`(0.8), `put`(0.8), `toward`(0.8), `let`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.offer_help_repaying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_debt.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.offer_help_repaying   [28 chars]
    en  I'll put emeralds toward it.
    >>  ............................................
    pt  Vou entrar com esmeraldas nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.outlaw.debt.helped`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.outlaw.people_owed`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.old_debt", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.old_debt", "obligation": "commitment:work.outlaw.bring_repayment"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.outlaw.bring_repayment"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll put emeralds toward it."
       spoken on: conversations.scene.work.outlaw.old_debt.blocked.respond, button `offer_help_repaying`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_debt.blocked.accepted`: the villager accepts. Subject `work.outlaw.people_owed`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.accepted/1   [93 chars]
    en  I will take it, and I will keep a tally, and you will get it back whether you want it or not.
    >>  ............................................
    pt  Vou aceitar, e vou anotar, e você vai receber de volta querendo ou não.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.accepted/2   [114 chars]
    en  That shortens two years to one. I am not going to make a speech, but I want you to know I did the sum immediately.
    >>  ............................................
    pt  Isso encurta dois anos para um. Não vou fazer discurso, mas quero que saiba que eu fiz a conta na hora.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.accepted/3   [101 chars]
    en  Careful. Helping me is the sort of thing this village notices, and it notices in the wrong direction.
    >>  ............................................
    pt  Cuidado. Ajudar a mim é o tipo de coisa que esta vila repara, e repara na direção errada.
    >>  ............................................
```


### Button `advise_going_in_person` — "Go to them yourself."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.outlaw.old_debt.blocked` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_debt.blocked.advise_going_in_person` — accepted phrasings: "go to them yourself"; "go to them yourself"; "deliver it in person"
  - the message must contain one of: `yourself`, `person`
  - scored words: `yourself`(1.8), `person`(1.8), `deliver`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.advise_going_in_person
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_debt.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.advise_going_in_person   [20 chars]
    en  Go to them yourself.
    >>  ............................................
    pt  Vá até eles pessoalmente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `work.outlaw.people_owed`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.old_debt"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Go to them yourself."
       spoken on: conversations.scene.work.outlaw.old_debt.blocked.respond, button `advise_going_in_person`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_debt.blocked.resisted`: the villager resists. Subject `work.outlaw.people_owed`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.resisted/1   [109 chars]
    en  That would be for me. They would have to stand in their own doorway and be gracious to the person who did it.
    >>  ............................................
    pt  Isso seria para mim. Eles teriam que ficar na própria porta e ser gentis com a pessoa que fez aquilo.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.resisted/2   [126 chars]
    en  One day, if they ask. Not before. The choice about whether to see me again is theirs and I have taken enough of their choices.
    >>  ............................................
    pt  Um dia, se pedirem. Antes não. A escolha de me ver de novo é deles e eu já tomei escolhas demais deles.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.resisted/3   [139 chars]
    en  I have written. Twice. Both letters said the same four things and neither asked for an answer, and that is as close as I will go uninvited.
    >>  ............................................
    pt  Eu escrevi. Duas vezes. As duas cartas diziam as mesmas quatro coisas e nenhuma pedia resposta, e é o mais perto que eu chego sem convite.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.outlaw.old_debt.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_debt.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked.respond.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você seguir com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.outlaw.old_debt.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.outlaw.old_debt.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.old_debt.succeeded` — e.g. "Paid. All of it, through the same third party, and %2$s sent back three words and I have kept them."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.old_debt.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded.respond   [10 chars]
    en  That debt.
    >>  ............................................
    pt  Aquela dívida.
    >>  ............................................
```


### Button `ask_what_they_said` — "What did they send back?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.outlaw.old_debt.succeeded` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.old_debt.succeeded.ask_what_they_said` — accepted phrasings: "what did they send back"; "what did they send back"; "what were the words in the reply"
  - the message must contain one of: `send`, `reply`, `words`
  - scored words: `send`(1.8), `reply`(1.8), `words`(1.8), `back`(0.8), `were`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.succeeded.respond.ask_what_they_said
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_debt.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded.respond.ask_what_they_said   [24 chars]
    en  What did they send back?
    >>  ............................................
    pt  O que mandaram de volta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.outlaw.people_owed`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.outlaw.old_debt"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What did they send back?"
       spoken on: conversations.scene.work.outlaw.old_debt.succeeded.respond, button `ask_what_they_said`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_debt.succeeded.answered`: the villager explains. Subject `work.outlaw.people_owed`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded.answered/1   [103 chars]
    en  Received in full. That is all. It is the correct amount of warmth and I have read it about forty times.
    >>  ............................................
    pt  Recebido integralmente. Só isso. É a quantidade correta de calor humano e eu já li umas quarenta vezes.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded.answered/2   [85 chars]
    en  Nothing kind. Nothing cruel either, which I had prepared for and would have accepted.
    >>  ............................................
    pt  Nada gentil. Nada cruel também, coisa para a qual eu tinha me preparado e que teria aceitado.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded.answered/3   [136 chars]
    en  Three words, in a hand that had clearly been thought about. I am not going to repeat them. They are the only thing I own from all of it.
    >>  ............................................
    pt  Três palavras, numa letra que claramente foi pensada. Não vou repetir. São a única coisa que eu tenho de tudo aquilo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.outlaw.old_debt.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.old_debt.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded.respond.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você seguir com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.outlaw.old_debt.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.outlaw.the_name.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.the_name.active` — e.g. "%2$s knows what I was, and there is no version of the next ten years where %2$s forgets."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.the_name.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.respond   [9 chars]
    en  The name.
    >>  ............................................
    pt  O nome.
    >>  ............................................
```


### Button `ask_if_it_can_change` — "Can that ever change?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.the_name.active` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.the_name.active.ask_if_it_can_change` — accepted phrasings: "can that ever change"; "can that ever change"; "will the name ever shift"
  - the message must contain one of: `change`, `shift`, `name`
  - scored words: `change`(1.8), `shift`(1.8), `name`(1.8), `ever`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.respond.ask_if_it_can_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.the_name.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.respond.ask_if_it_can_change   [21 chars]
    en  Can that ever change?
    >>  ............................................
    pt  Isso pode mudar algum dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.the_name`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.the_name"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.explained
WHO    VILLAGER — what the player reads after pressing "Can that ever change?"
       spoken on: conversations.scene.work.outlaw.the_name.active.respond, button `ask_if_it_can_change`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.the_name.active.explained`: the villager explains. Subject `work.outlaw.the_name`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.explained/1   [102 chars]
    en  Not the knowing. The weight of it, maybe, in about fifteen years, if I am very boring for all fifteen.
    >>  ............................................
    pt  O saber, não. O peso, talvez, em uns quinze anos, se eu for muito entediante nos quinze.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active.explained/2   [112 chars]
    en  It changes one household at a time and it changes back the first time a barn burns anywhere within a day's ride.
    >>  ............................................
    pt  Muda uma casa por vez e desmuda na primeira vez que um celeiro pega fogo a um dia de viagem daqui.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active.explained/3   [139 chars]
    en  The children are the answer. They will grow up with me as the woman who mends carts, and their version will outlive their parents' version.
    >>  ............................................
    pt  As crianças são a resposta. Vão crescer comigo como a mulher que conserta carroças, e a versão delas vai sobreviver à versão dos pais.
    >>  ............................................
```


### Button `say_you_judge_by_now` — "I judge people by what they do now."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.the_name.active` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.the_name.active.say_you_judge_by_now` — accepted phrasings: "i judge people by what they do now"; "i judge people by what they do now"; "what you do now is what counts"
  - the message must contain one of: `judge`, `now`, `counts`
  - scored words: `judge`(1.8), `now`(1.8), `counts`(1.8), `people`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.respond.say_you_judge_by_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.the_name.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.respond.say_you_judge_by_now   [35 chars]
    en  I judge people by what they do now.
    >>  ............................................
    pt  Eu julgo pelo que a pessoa faz agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.outlaw.name.trusted`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.outlaw.the_name`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.the_name"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.steadied
WHO    VILLAGER — what the player reads after pressing "I judge people by what they do now."
       spoken on: conversations.scene.work.outlaw.the_name.active.respond, button `say_you_judge_by_now`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.the_name.active.steadied`: the villager accepts. Subject `work.outlaw.the_name`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.steadied/1   [107 chars]
    en  That is a rarer position than you think, and I would advise you to hold it quietly rather than at the well.
    >>  ............................................
    pt  Essa é uma posição mais rara do que você imagina, e eu aconselharia sustentar em silêncio, não no poço.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active.steadied/2   [111 chars]
    en  Thank you. I am not going to pretend that undoes anything. It does make today easier, and today is what I have.
    >>  ............................................
    pt  Obrigada. Não vou fingir que isso desfaz alguma coisa. Facilita o dia de hoje, e hoje é o que eu tenho.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active.steadied/3   [135 chars]
    en  Be careful with that. People who decide I am fine tend to decide it all at once, and then they are hurt when I turn out to be ordinary.
    >>  ............................................
    pt  Cuidado com isso. Quem decide que eu presto costuma decidir tudo de uma vez, e depois se magoa quando eu me revelo comum.
    >>  ............................................
```


### Button `advise_patience` — "Then be boring for fifteen years."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.the_name.active` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.the_name.active.advise_patience` — accepted phrasings: "then be boring for fifteen years"; "then be boring for fifteen years"; "outlast it by being ordinary"
  - the message must contain one of: `boring`, `ordinary`, `outlast`
  - scored words: `boring`(1.8), `ordinary`(1.8), `outlast`(1.8), `fifteen`(0.8), `years`(0.8), `being`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.respond.advise_patience
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.the_name.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.respond.advise_patience   [33 chars]
    en  Then be boring for fifteen years.
    >>  ............................................
    pt  Então seja entediante por quinze anos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.outlaw.the_name`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.outlaw.the_name"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.accepted
WHO    VILLAGER — what the player reads after pressing "Then be boring for fifteen years."
       spoken on: conversations.scene.work.outlaw.the_name.active.respond, button `advise_patience`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.the_name.active.accepted`: the villager accepts. Subject `work.outlaw.the_name`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.accepted/1   [123 chars]
    en  That is the plan and it is a poor one and it is the only one available, which is roughly how I feel about most of my plans.
    >>  ............................................
    pt  É o plano, é um plano ruim e é o único disponível, que é mais ou menos como me sinto sobre a maioria dos meus planos.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active.accepted/2   [112 chars]
    en  Fifteen years of being unremarkable. I have done harder things in a week, and none of them lasted fifteen years.
    >>  ............................................
    pt  Quinze anos sendo comum. Já fiz coisas mais difíceis em uma semana, e nenhuma durou quinze anos.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active.accepted/3   [118 chars]
    en  Yes. And the difficulty is that boring is not a thing you can be seen doing. It only exists in the absence of stories.
    >>  ............................................
    pt  Sim. E a dificuldade é que entediante não é algo que se possa ser visto fazendo. Só existe na ausência de histórias.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.outlaw.the_name.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.the_name.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active.respond.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você seguir com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.outlaw.the_name.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.outlaw.the_name.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.outlaw.the_name.succeeded` — e.g. "%2$s asked me to look at a lock last week. Asked. That is the first time in four years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.outlaw.the_name.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.succeeded.respond   [20 chars]
    en  The village, lately.
    >>  ............................................
    pt  A vila, ultimamente.
    >>  ............................................
```


### Button `note_it_shifted` — "Four years of boring did that."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.outlaw.the_name.succeeded` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.outlaw.the_name.succeeded.note_it_shifted` — accepted phrasings: "four years of boring did that"; "four years of boring did that"; "the ordinary years earned that"
  - the message must contain one of: `boring`, `ordinary`, `years`
  - scored words: `boring`(1.8), `ordinary`(1.8), `years`(1.8), `four`(0.8), `earned`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.succeeded.respond.note_it_shifted
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.the_name.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.succeeded.respond.note_it_shifted   [30 chars]
    en  Four years of boring did that.
    >>  ............................................
    pt  Quatro anos de tédio fizeram isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.outlaw.the_name`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.outlaw.the_name"}
- Then opens: `conversations.scene.work.outlaw.followup`
- …where the player's next choices will be: "What's the hardest part of a name that follows you?" | "I'll leave you to your own business."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Four years of boring did that."
       spoken on: conversations.scene.work.outlaw.the_name.succeeded.respond, button `note_it_shifted`
       leaves the player on: conversations.scene.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.the_name.succeeded.acknowledged`: the villager accepts. Subject `work.outlaw.the_name`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.succeeded.acknowledged/1   [116 chars]
    en  Four years and one lock. I would like to be more moved by it than I am, and I am aware that says something about me.
    >>  ............................................
    pt  Quatro anos e uma fechadura. Eu gostaria de me emocionar mais do que me emociono, e sei que isso diz algo a meu respeito.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.succeeded.acknowledged/2   [101 chars]
    en  It did. And it can be undone by one bad harvest and one missing purse, so I have not started resting.
    >>  ............................................
    pt  Fizeram. E dá para desfazer com uma colheita ruim e uma bolsa sumida, então eu não comecei a descansar.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.succeeded.acknowledged/3   [125 chars]
    en  Thank you. There is no ceremony for this. Nobody announces that you have been let back in; you just notice the door was open.
    >>  ............................................
    pt  Obrigada. Não existe cerimônia para isso. Ninguém anuncia que você foi readmitida; você só repara que a porta estava aberta.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.outlaw.the_name.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.outlaw.the_name.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.succeeded.respond.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você seguir com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.outlaw.the_name.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.topic.work.outlaw.craft.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw.craft` — e.g. "I'm good at reading a room in about four seconds. It's a useful skill and I learned it very badly."


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.craft.respond   [19 chars]
    en  That's what I have.
    >>  ............................................
    pt  É o que eu tenho.
    >>  ............................................
```


### Button `ask_room` — "What do you read in four seconds?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.craft` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.craft.ask_room` — accepted phrasings: "what do you read in four seconds"
  - the message must contain one of: `room`, `read`, `seconds`
  - scored words: `room`(1.5), `read`(1.2), `seconds`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.craft.respond.ask_room
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.craft.respond.ask_room   [33 chars]
    en  What do you read in four seconds?
    >>  ............................................
    pt  O que você lê em quatro segundos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.craft.ask_room`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.craft.ask_room
WHO    VILLAGER — what the player reads after pressing "What do you read in four seconds?"
       spoken on: conversations.topic.work.outlaw.craft.respond, button `ask_room`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.craft.ask_room`: the villager explains. Subject `work.outlaw.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.craft.ask_room/1   [86 chars]
    en  Who's watching the door, who's had too much, and who's about to be somebody's problem.
    >>  ............................................
    pt  Quem vigia a porta, quem bebeu demais, e quem vai virar problema de alguém.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.craft.ask_room/2   [83 chars]
    en  Which of them has decided about me already, %1$s. That one takes about two seconds.
    >>  ............................................
    pt  Qual deles já decidiu sobre mim, %1$s. Esse leva uns dois segundos.
    >>  ............................................
```


### Button `admire` — "That's a skill a guard would pay for."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.craft` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.craft.admire` — accepted phrasings: "that's a skill a guard would pay for"
  - the message must contain one of: `skill`, `pay`, `useful`
  - scored words: `skill`(1.5), `pay`(1.2), `useful`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.craft.respond.admire   [37 chars]
    en  That's a skill a guard would pay for.
    >>  ............................................
    pt  É uma habilidade que um guarda pagaria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.outlaw.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.outlaw.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.craft.admire
WHO    VILLAGER — what the player reads after pressing "That's a skill a guard would pay for."
       spoken on: conversations.topic.work.outlaw.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.craft.admire`: the villager accepts. Subject `work.outlaw.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.craft.admire/1   [89 chars]
    en  He would, and he'd never say so, and he'd never let me stand next to him while I used it.
    >>  ............................................
    pt  Pagaria, e nunca diria, e nunca me deixaria ficar do lado dele usando.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.craft.admire/2   [90 chars]
    en  I offered. Once. He was polite about it and it took me a year to try anything again, %1$s.
    >>  ............................................
    pt  Eu ofereci. Uma vez. Ele foi educado e eu levei um ano pra tentar qualquer coisa de novo, %1$s.
    >>  ............................................
```


### Button `ask_carpentry` — "Could you learn carpentry now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.craft` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.craft.ask_carpentry` — accepted phrasings: "could you learn carpentry now"
  - the message must contain one of: `carpentry`, `learn`
  - scored words: `carpentry`(1.5), `learn`(1.0), `trade`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.craft.respond.ask_carpentry
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.craft.respond.ask_carpentry   [30 chars]
    en  Could you learn carpentry now?
    >>  ............................................
    pt  Você poderia aprender carpintaria agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.craft.ask_carpentry`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.craft.ask_carpentry
WHO    VILLAGER — what the player reads after pressing "Could you learn carpentry now?"
       spoken on: conversations.topic.work.outlaw.craft.respond, button `ask_carpentry`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.craft.ask_carpentry`: the villager explains. Subject `work.outlaw.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.craft.ask_carpentry/1   [88 chars]
    en  Who'd teach me? That's not self-pity, it's a real question with a short list of answers.
    >>  ............................................
    pt  Quem me ensinaria? Não é autopiedade, é uma pergunta real com poucas respostas.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.craft.ask_carpentry/2   [95 chars]
    en  The woodworker let me hold a plane once, %1$s. I've thought about that afternoon for two years.
    >>  ............................................
    pt  O marceneiro me deixou segurar uma plaina uma vez, %1$s. Penso nessa tarde faz dois anos.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.craft.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.outlaw.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.followup`

**Reached from 20 route(s):** `conversations.scene.work.outlaw.followup` / `ask_more`; `conversations.topic.work.outlaw.craft.respond` / `ask_room`; `conversations.topic.work.outlaw.craft.respond` / `admire`; `conversations.topic.work.outlaw.craft.respond` / `ask_carpentry`; `conversations.topic.work.outlaw.future.respond` / `ask_teacher`; `conversations.topic.work.outlaw.future.respond` / `encourage`; `conversations.topic.work.outlaw.future.respond` / `ask_road`; `conversations.topic.work.outlaw.respond` / `ask_hard`; `conversations.topic.work.outlaw.respond` / `value`; `conversations.topic.work.outlaw.respond` / `challenge`; `conversations.topic.work.outlaw.respond` / `challenge`; `conversations.topic.work.outlaw.risk.respond` / `ask_missing` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw.challenge.landed` — e.g. "I could. Then I'd be a man with no trade and a very memorable face."
- `conversations.work.prof.outlaw.challenge.stung` — e.g. "...Say that to the magistrate. See if he says 'well, stop then'."
- `conversations.work.prof.outlaw.craft.admire` — e.g. "He would, and he'd never say so, and he'd never let me stand next to him while I used it."
- `conversations.work.prof.outlaw.craft.ask_carpentry` — e.g. "Who'd teach me? That's not self-pity, it's a real question with a short list of answers."
- `conversations.work.prof.outlaw.craft.ask_room` — e.g. "Who's watching the door, who's had too much, and who's about to be somebody's problem."
- `conversations.work.prof.outlaw.future.ask_road` — e.g. "No. It would be mine, though, and there's a difference between worse and chosen."
- `conversations.work.prof.outlaw.future.ask_teacher` — e.g. "The woodworker, on a good day, if nobody was watching. Which is not the same as willing."
- `conversations.work.prof.outlaw.future.encourage` — e.g. "...With me. That changes it from me asking to two people arriving, and that is not a small change."
- `conversations.work.prof.outlaw.hard` — e.g. "A name I can use. Everything else you can replace; that one you can't."
- `conversations.work.prof.outlaw.risk.ask_missing` — e.g. "Twice. Both times it turned up. Neither time did anybody come and say so to my face."
- `conversations.work.prof.outlaw.risk.ask_name` — e.g. "Then I find out what four years of splitting kindling is worth against one sentence."
- `conversations.work.prof.outlaw.risk.sympathise` — e.g. "...It does, and the wearing is the quiet kind that doesn't look like anything from outside."
- `conversations.work.prof.outlaw.task.ask_seen` — e.g. "Because the day I'm not where they can see me is the day something goes missing and it's mine."
- `conversations.work.prof.outlaw.task.ask_widow` — e.g. "She says she's too old to be frightened of anybody and too tired to split her own kindling."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.followup   [32 chars]
    en  That's the freelance side of it.
    >>  ............................................
    pt  É o lado autônomo da coisa.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.challenge.landed`, `work.outlaw.challenge.stung`, `work.outlaw.craft.admire`, `work.outlaw.craft.ask_carpentry`, `work.outlaw.craft.ask_room`, `work.outlaw.future.ask_road`, `work.outlaw.future.ask_teacher`, `work.outlaw.future.encourage`, `work.outlaw.hard`, `work.outlaw.risk.ask_missing`, `work.outlaw.risk.ask_name`, `work.outlaw.risk.sympathise`, `work.outlaw.task.ask_seen`, `work.outlaw.task.ask_widow`, `work.outlaw.task.offer_hands`, `work.outlaw.value`, `work.outlaw.village.ask_invited`, `work.outlaw.village.ask_stay`, `work.outlaw.village.say_thanks` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.outlaw.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `opinion`, `conversation`
  - scored words: `thought`(1.2), `opinion`(1.2), `conversation`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.outlaw.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.outlaw.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.outlaw.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.outlaw.thanks`: the villager accepts. Subject `work.outlaw.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.thanks/1   [64 chars]
    en  Nobody does. It's easier to have an opinion than a conversation.
    >>  ............................................
    pt  Ninguém pensa. É mais fácil ter opinião que ter conversa.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.thanks/2   [66 chars]
    en  You've talked to me for longer than the magistrate ever did, %1$s.
    >>  ............................................
    pt  Você falou comigo por mais tempo que o magistrado jamais falou, %1$s.
    >>  ............................................
```


### Button `ask_more` — "Is there a way back for you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.challenge.landed`, `work.outlaw.challenge.stung`, `work.outlaw.craft.admire`, `work.outlaw.craft.ask_carpentry`, `work.outlaw.craft.ask_room`, `work.outlaw.future.ask_road`, `work.outlaw.future.ask_teacher`, `work.outlaw.future.encourage`, `work.outlaw.hard`, `work.outlaw.risk.ask_missing`, `work.outlaw.risk.ask_name`, `work.outlaw.risk.sympathise`, `work.outlaw.task.ask_seen`, `work.outlaw.task.ask_widow`, `work.outlaw.task.offer_hands`, `work.outlaw.value`, `work.outlaw.village.ask_invited`, `work.outlaw.village.ask_stay`, `work.outlaw.village.say_thanks` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.outlaw.more` — accepted phrasings: "is there a way back for you"
  - the message must contain one of: `back`, `redemption`, `pardon`
  - scored words: `back`(1.2), `redemption`(1.5), `pardon`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.followup.ask_more   [28 chars]
    en  Is there a way back for you?
    >>  ............................................
    pt  Existe volta pra você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.outlaw.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.more
WHO    VILLAGER — what the player reads after pressing "Is there a way back for you?"
       spoken on: conversations.topic.work.outlaw.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.outlaw.more`: the villager discloses. Subject `work.outlaw.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.more/1   [85 chars]
    en  There's a road. It's long and it goes through people who remember, so — probably not.
    >>  ............................................
    pt  Existe um caminho. É longo e passa por gente que lembra, então — provavelmente não.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.more/2   [77 chars]
    en  Somebody would have to vouch for me. That's the whole of it, and I've nobody.
    >>  ............................................
    pt  Alguém teria que responder por mim. É só isso, e eu não tenho ninguém.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. I keep a bag packed for it and I hate that I do, %1$s.
    >>  ............................................
    pt  Tem uma estrada. Eu mantenho uma bolsa pronta pra ela e eu odeio que eu mantenha, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, with somebody willing to be seen teaching me. It's the being seen that's the hard part.
    >>  ............................................
    pt  Um ofício, com alguém disposto a ser visto me ensinando. É o ser visto que é a parte difícil.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It'll still be there next year, and so will I, most likely.
    >>  ............................................
    pt  Tem uma estrada. Vai continuar lá ano que vem, e eu também, provavelmente.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, eventually. Four quiet years is not nothing to build on.
    >>  ............................................
    pt  Um ofício, uma hora. Quatro anos calmos não é pouca base.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It's long and it goes through people who remember, so — probably not.
    >>  ............................................
    pt  Tem uma estrada. É longa e passa por gente que lembra, então — provavelmente não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Any trade, badly paid, with somebody willing to be seen teaching me.
    >>  ............................................
    pt  Um ofício. Qualquer um, mal pago, com alguém disposto a ser visto me ensinando.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It's long and it goes through people who remember, so — probably not.
    >>  ............................................
    pt  Tem uma estrada. É longa e passa por gente que lembra, então — provavelmente não.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Any trade, badly paid, with somebody willing to be seen teaching me.
    >>  ............................................
    pt  Um ofício. Qualquer um, mal pago, com alguém disposto a ser visto me ensinando.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road, and it goes through people who remember. I'd rather stay where somebody nods.
    >>  ............................................
    pt  Tem uma estrada, e passa por gente que lembra. Prefiro ficar onde alguém acena.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Come with me to the toolsmith's door and it stops being me asking and starts being two people arriving.
    >>  ............................................
    pt  Um ofício. Venha comigo à porta do ferramenteiro e deixa de ser eu pedindo e vira duas pessoas chegando.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road, and it goes through people who remember. I'd rather stay where somebody nods.
    >>  ............................................
    pt  Tem uma estrada, e passa por gente que lembra. Prefiro ficar onde alguém acena.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Come with me to the toolsmith's door and it stops being me asking and starts being two people arriving.
    >>  ............................................
    pt  Um ofício. Venha comigo à porta do ferramenteiro e deixa de ser eu pedindo e vira duas pessoas chegando.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road, and it goes through people who remember. I'd rather stay where somebody nods.
    >>  ............................................
    pt  Tem uma estrada, e passa por gente que lembra. Prefiro ficar onde alguém acena.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Come with me to the toolsmith's door and it stops being me asking and starts being two people arriving.
    >>  ............................................
    pt  Um ofício. Venha comigo à porta do ferramenteiro e deixa de ser eu pedindo e vira duas pessoas chegando.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. I keep a bag packed for it and I hate that I do, %1$s.
    >>  ............................................
    pt  Tem uma estrada. Eu mantenho uma bolsa pronta pra ela e eu odeio que eu mantenha, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, with somebody willing to be seen teaching me. It's the being seen that's the hard part.
    >>  ............................................
    pt  Um ofício, com alguém disposto a ser visto me ensinando. É o ser visto que é a parte difícil.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It's long and it goes through people who remember, so — probably not.
    >>  ............................................
    pt  Tem uma estrada. É longa e passa por gente que lembra, então — provavelmente não.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Any trade, badly paid, with somebody willing to be seen teaching me.
    >>  ............................................
    pt  Um ofício. Qualquer um, mal pago, com alguém disposto a ser visto me ensinando.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It's long and it goes through people who remember, so — probably not.
    >>  ............................................
    pt  Tem uma estrada. É longa e passa por gente que lembra, então — provavelmente não.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Any trade, badly paid, with somebody willing to be seen teaching me.
    >>  ............................................
    pt  Um ofício. Qualquer um, mal pago, com alguém disposto a ser visto me ensinando.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. Long, and it goes the wrong way through the wrong memories.
    >>  ............................................
    pt  Tem uma estrada. Longa, e vai pelo lado errado por memórias erradas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. I've walked past the toolsmith's door nine times and not gone in once.
    >>  ............................................
    pt  Um ofício. Passei pela porta do ferramenteiro nove vezes e não entrei uma.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It'll still be there next year, and so will I, most likely.
    >>  ............................................
    pt  Tem uma estrada. Vai continuar lá ano que vem, e eu também, provavelmente.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, eventually. Four quiet years is not nothing to build on.
    >>  ............................................
    pt  Um ofício, uma hora. Quatro anos calmos não é pouca base.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. Long, and it goes the wrong way through the wrong memories.
    >>  ............................................
    pt  Tem uma estrada. Longa, e vai pelo lado errado por memórias erradas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. I've walked past the toolsmith's door nine times and not gone in once.
    >>  ............................................
    pt  Um ofício. Passei pela porta do ferramenteiro nove vezes e não entrei uma.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It'll still be there next year, and so will I, most likely.
    >>  ............................................
    pt  Tem uma estrada. Vai continuar lá ano que vem, e eu também, provavelmente.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, eventually. Four quiet years is not nothing to build on.
    >>  ............................................
    pt  Um ofício, uma hora. Quatro anos calmos não é pouca base.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road! Long, and full of people who remember me. So: probably not.
    >>  ............................................
    pt  Tem uma estrada! Longa, e cheia de gente que lembra de mim. Então: provavelmente não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Badly paid, ideally. I'd take anything with a bench and a nameplate.
    >>  ............................................
    pt  Um ofício. Mal pago, de preferência. Aceitaria qualquer coisa com bancada e placa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road! Long, and full of people who remember me. So: probably not.
    >>  ............................................
    pt  Tem uma estrada! Longa, e cheia de gente que lembra de mim. Então: provavelmente não.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Badly paid, ideally. I'd take anything with a bench and a nameplate.
    >>  ............................................
    pt  Um ofício. Mal pago, de preferência. Aceitaria qualquer coisa com bancada e placa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. It'll still be there next year, and so will I, most likely.
    >>  ............................................
    pt  Tem uma estrada. Vai continuar lá ano que vem, e eu também, provavelmente.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, eventually. Four quiet years is not nothing to build on.
    >>  ............................................
    pt  Um ofício, uma hora. Quatro anos calmos não é pouca base.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. I keep a bag packed for it and I hate that I do, %1$s.
    >>  ............................................
    pt  Tem uma estrada. Eu mantenho uma bolsa pronta pra ela e eu odeio que eu mantenha, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade, with somebody willing to be seen teaching me. It's the being seen that's the hard part.
    >>  ............................................
    pt  Um ofício, com alguém disposto a ser visto me ensinando. É o ser visto que é a parte difícil.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road. Long, and it goes the wrong way through the wrong memories.
    >>  ............................................
    pt  Tem uma estrada. Longa, e vai pelo lado errado por memórias erradas.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. I've walked past the toolsmith's door nine times and not gone in once.
    >>  ............................................
    pt  Um ofício. Passei pela porta do ferramenteiro nove vezes e não entrei uma.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road! Long, and full of people who remember me. So: probably not.
    >>  ............................................
    pt  Tem uma estrada! Longa, e cheia de gente que lembra de mim. Então: provavelmente não.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Badly paid, ideally. I'd take anything with a bench and a nameplate.
    >>  ............................................
    pt  Um ofício. Mal pago, de preferência. Aceitaria qualquer coisa com bancada e placa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw.more/1
    en  There's a road! Long, and full of people who remember me. So: probably not.
    >>  ............................................
    pt  Tem uma estrada! Longa, e cheia de gente que lembra de mim. Então: provavelmente não.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw.more/2
    en  A trade. Badly paid, ideally. I'd take anything with a bench and a nameplate.
    >>  ............................................
    pt  Um ofício. Mal pago, de preferência. Aceitaria qualquer coisa com bancada e placa.
    >>  ............................................
```

</details>


### Button `leave` — "Watch yourself."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.challenge.landed`, `work.outlaw.challenge.stung`, `work.outlaw.craft.admire`, `work.outlaw.craft.ask_carpentry`, `work.outlaw.craft.ask_room`, `work.outlaw.future.ask_road`, `work.outlaw.future.ask_teacher`, `work.outlaw.future.encourage`, `work.outlaw.hard`, `work.outlaw.risk.ask_missing`, `work.outlaw.risk.ask_name`, `work.outlaw.risk.sympathise`, `work.outlaw.task.ask_seen`, `work.outlaw.task.ask_widow`, `work.outlaw.task.offer_hands`, `work.outlaw.value`, `work.outlaw.village.ask_invited`, `work.outlaw.village.ask_stay`, `work.outlaw.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.followup.leave   [15 chars]
    en  Watch yourself.
    >>  ............................................
    pt  Se cuida.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "Watch yourself."
       spoken on: conversations.topic.work.outlaw.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw.future` — e.g. "I want a trade. Any trade, badly paid, with somebody willing to be seen teaching me."


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.future.respond   [21 chars]
    en  That's the two roads.
    >>  ............................................
    pt  São as duas estradas.
    >>  ............................................
```


### Button `ask_teacher` — "Who might be willing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.future` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.future.ask_teacher` — accepted phrasings: "who might be willing"
  - the message must contain one of: `willing`, `teach`
  - scored words: `willing`(1.5), `teach`(1.2), `who`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.future.respond.ask_teacher
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.future.respond.ask_teacher   [21 chars]
    en  Who might be willing?
    >>  ............................................
    pt  Quem estaria disposto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.future.ask_teacher`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.future.ask_teacher
WHO    VILLAGER — what the player reads after pressing "Who might be willing?"
       spoken on: conversations.topic.work.outlaw.future.respond, button `ask_teacher`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.future.ask_teacher`: the villager explains. Subject `work.outlaw.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.future.ask_teacher/1   [88 chars]
    en  The woodworker, on a good day, if nobody was watching. Which is not the same as willing.
    >>  ............................................
    pt  O marceneiro, num dia bom, se ninguém estivesse olhando. O que não é o mesmo que disposto.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.future.ask_teacher/2   [89 chars]
    en  The toolsmith takes anyone with steady hands, %1$s. I've walked past his door nine times.
    >>  ............................................
    pt  O ferramenteiro aceita qualquer um de mão firme, %1$s. Já passei pela porta dele nove vezes.
    >>  ............................................
```


### Button `encourage` — "Walk in the tenth time. I'll come with you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.future` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.future.encourage` — accepted phrasings: "walk in the tenth time. i'll come with you"
  - the message must contain one of: `tenth`, `come`, `accompany`
  - scored words: `tenth`(1.5), `come`(1.0), `accompany`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.future.respond.encourage   [43 chars]
    en  Walk in the tenth time. I'll come with you.
    >>  ............................................
    pt  Entre na décima vez. Eu vou com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.outlaw.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.outlaw.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.future.encourage
WHO    VILLAGER — what the player reads after pressing "Walk in the tenth time. I'll come with you."
       spoken on: conversations.topic.work.outlaw.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.future.encourage`: the villager accepts. Subject `work.outlaw.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.future.encourage/1   [98 chars]
    en  ...With me. That changes it from me asking to two people arriving, and that is not a small change.
    >>  ............................................
    pt  ...Comigo. Isso muda de eu pedindo pra duas pessoas chegando, e não é uma mudança pequena.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.future.encourage/2   [72 chars]
    en  Then it's tomorrow, before I talk myself out of it, %1$s. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário, %1$s. Não se atrase.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. I couldn't walk in alone and I've never said that to anybody.
    >>  ............................................
    pt  ...Comigo. Eu não conseguiria entrar sozinho e nunca disse isso a ninguém.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. I will try to, and don't let me.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Vou tentar, e não deixe.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Three years of asking alone and it never once worked.
    >>  ............................................
    pt  ...Comigo. Três anos pedindo sozinho e nunca funcionou.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow. At my age you stop pretending there's a better week coming.
    >>  ............................................
    pt  Então é amanhã. Na minha idade você para de fingir que vem uma semana melhor.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo. Isso muda de eu pedindo para duas pessoas chegando.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo. Isso muda de eu pedindo para duas pessoas chegando.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me, %1$s. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo, %1$s. Isso muda de eu pedindo pra duas pessoas chegando.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late, %1$s.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me, %1$s. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo, %1$s. Isso muda de eu pedindo pra duas pessoas chegando.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late, %1$s.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me, %1$s. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo, %1$s. Isso muda de eu pedindo pra duas pessoas chegando.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late, %1$s.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. I couldn't walk in alone and I've never said that to anybody.
    >>  ............................................
    pt  ...Comigo. Eu não conseguiria entrar sozinho e nunca disse isso a ninguém.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. I will try to, and don't let me.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Vou tentar, e não deixe.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo. Isso muda de eu pedindo para duas pessoas chegando.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. That changes it from me asking to two people arriving.
    >>  ............................................
    pt  ...Comigo. Isso muda de eu pedindo para duas pessoas chegando.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Two arriving, not one asking.
    >>  ............................................
    pt  ...Comigo. Dois chegando, não um pedindo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Tomorrow, then. Don't be late.
    >>  ............................................
    pt  Amanhã, então. Não se atrase.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Three years of asking alone and it never once worked.
    >>  ............................................
    pt  ...Comigo. Três anos pedindo sozinho e nunca funcionou.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow. At my age you stop pretending there's a better week coming.
    >>  ............................................
    pt  Então é amanhã. Na minha idade você para de fingir que vem uma semana melhor.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Two arriving, not one asking.
    >>  ............................................
    pt  ...Comigo. Dois chegando, não um pedindo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Tomorrow, then. Don't be late.
    >>  ............................................
    pt  Amanhã, então. Não se atrase.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Three years of asking alone and it never once worked.
    >>  ............................................
    pt  ...Comigo. Três anos pedindo sozinho e nunca funcionou.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow. At my age you stop pretending there's a better week coming.
    >>  ............................................
    pt  Então é amanhã. Na minha idade você para de fingir que vem uma semana melhor.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me! That changes it from me asking to two people arriving, which is different.
    >>  ............................................
    pt  ...Comigo! Isso muda de eu pedindo pra duas pessoas chegando, que é diferente.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me! That changes it from me asking to two people arriving, which is different.
    >>  ............................................
    pt  ...Comigo! Isso muda de eu pedindo pra duas pessoas chegando, que é diferente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Three years of asking alone and it never once worked.
    >>  ............................................
    pt  ...Comigo. Três anos pedindo sozinho e nunca funcionou.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow. At my age you stop pretending there's a better week coming.
    >>  ............................................
    pt  Então é amanhã. Na minha idade você para de fingir que vem uma semana melhor.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. I couldn't walk in alone and I've never said that to anybody.
    >>  ............................................
    pt  ...Comigo. Eu não conseguiria entrar sozinho e nunca disse isso a ninguém.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. I will try to, and don't let me.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Vou tentar, e não deixe.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me. Two arriving, not one asking.
    >>  ............................................
    pt  ...Comigo. Dois chegando, não um pedindo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Tomorrow, then. Don't be late.
    >>  ............................................
    pt  Amanhã, então. Não se atrase.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me! That changes it from me asking to two people arriving, which is different.
    >>  ............................................
    pt  ...Comigo! Isso muda de eu pedindo pra duas pessoas chegando, que é diferente.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw.future.encourage/1
    en  ...With me! That changes it from me asking to two people arriving, which is different.
    >>  ............................................
    pt  ...Comigo! Isso muda de eu pedindo pra duas pessoas chegando, que é diferente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw.future.encourage/2
    en  Then it's tomorrow, before I talk myself out of it. Don't be late.
    >>  ............................................
    pt  Então é amanhã, antes que eu me convença do contrário. Não se atrase.
    >>  ............................................
```

</details>


### Button `ask_road` — "Would the road really be better?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.future` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.future.ask_road` — accepted phrasings: "would the road really be better"
  - the message must contain one of: `road`, `better`, `leaving`
  - scored words: `road`(1.5), `better`(1.0), `leaving`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.future.respond.ask_road
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.future.respond.ask_road   [32 chars]
    en  Would the road really be better?
    >>  ............................................
    pt  A estrada seria melhor mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.future.ask_road`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.future.ask_road
WHO    VILLAGER — what the player reads after pressing "Would the road really be better?"
       spoken on: conversations.topic.work.outlaw.future.respond, button `ask_road`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.future.ask_road`: the villager explains. Subject `work.outlaw.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.future.ask_road/1   [80 chars]
    en  No. It would be mine, though, and there's a difference between worse and chosen.
    >>  ............................................
    pt  Não. Mas seria minha, e tem diferença entre pior e escolhido.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.future.ask_road/2   [92 chars]
    en  It'd be four years wasted and I'd be the one who wasted them, %1$s. That's the appeal of it.
    >>  ............................................
    pt  Seriam quatro anos jogados fora e eu seria quem jogou, %1$s. É esse o apelo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.future.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.outlaw.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw` — e.g. "My line of work is... freelance redistribution. The mayor and I disagree on the terminology."


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.respond   [40 chars]
    en  That's the terminology dispute, in full.
    >>  ............................................
    pt  É a disputa de terminologia, completa.
    >>  ............................................
```


### Button `ask_hard` — "What's it actually cost you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.identity` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.hard` — accepted phrasings: "what's it actually cost you"
  - the message must contain one of: `cost`, `lost`, `price`
  - scored words: `cost`(1.5), `lost`(1.2), `price`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.respond.ask_hard   [28 chars]
    en  What's it actually cost you?
    >>  ............................................
    pt  O que isso te custou de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.outlaw.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.hard
WHO    VILLAGER — what the player reads after pressing "What's it actually cost you?"
       spoken on: conversations.topic.work.outlaw.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.hard`: the villager explains. Subject `work.outlaw.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / ask_more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You've not taken anything of mine."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.identity` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.value` — accepted phrasings: "you've not taken anything of mine"
  - the message must contain one of: `taken`, `mine`, `stolen`
  - scored words: `taken`(1.5), `mine`(1.2), `stolen`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.respond.value   [34 chars]
    en  You've not taken anything of mine.
    >>  ............................................
    pt  Você não pegou nada meu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.outlaw.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.outlaw.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.value
WHO    VILLAGER — what the player reads after pressing "You've not taken anything of mine."
       spoken on: conversations.topic.work.outlaw.respond, button `value`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.value`: the villager accepts. Subject `work.outlaw.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.value/1   [81 chars]
    en  No. And I won't. That's not sentiment, it's the only rule that keeps me anywhere.
    >>  ............................................
    pt  Não. E não vou. Não é sentimentalismo, é a única regra que me mantém em algum lugar.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.value/2   [67 chars]
    en  Not yet, is what a careful man would say. I'll say no, and mean it.
    >>  ............................................
    pt  Ainda não, é o que um homem cuidadoso diria. Eu digo não, e é sério.
    >>  ............................................
```


### Button `challenge` — "You could just stop."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.outlaw.identity` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.challenge` — accepted phrasings: "you could just stop"
  - the message must contain one of: `stop`, `quit`, `straight`
  - scored words: `stop`(1.5), `quit`(1.2), `straight`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.respond.challenge   [20 chars]
    en  You could just stop.
    >>  ............................................
    pt  Você podia simplesmente parar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.outlaw.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.outlaw.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You could just stop."
       spoken on: conversations.topic.work.outlaw.respond, button `challenge`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.challenge.landed`: the villager resists. Subject `work.outlaw.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.challenge.landed/1   [67 chars]
    en  I could. Then I'd be a man with no trade and a very memorable face.
    >>  ............................................
    pt  Podia. Aí eu seria um homem sem ofício e com um rosto muito memorável.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.challenge.landed/2   [79 chars]
    en  Stopping's easy. It's the ten years after stopping that nobody offers me, %1$s.
    >>  ............................................
    pt  Parar é fácil. São os dez anos depois de parar que ninguém me oferece, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.outlaw.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.outlaw.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You could just stop."
       spoken on: conversations.topic.work.outlaw.respond, button `challenge`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.challenge.stung`: the villager resists. Subject `work.outlaw.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.challenge.stung/1   [64 chars]
    en  ...Say that to the magistrate. See if he says 'well, stop then'.
    >>  ............................................
    pt  ...Diga isso ao magistrado. Veja se ele diz 'ah, então pare'.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.challenge.stung/2   [76 chars]
    en  Just stop. Right. I'll add that to the list of things people have suggested.
    >>  ............................................
    pt  Simplesmente parar. Certo. Vou pôr na lista de coisas que já me sugeriram.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.outlaw.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw.risk` — e.g. "Anything that goes missing here is mine until proven otherwise. That's the arrangement and it never expires."


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.risk.respond   [25 chars]
    en  That's what I live under.
    >>  ............................................
    pt  É sob o que eu vivo.
    >>  ............................................
```


### Button `ask_missing` — "Has something gone missing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.risk` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.risk.ask_missing` — accepted phrasings: "has something gone missing"
  - the message must contain one of: `missing`, `accused`
  - scored words: `missing`(1.5), `accused`(1.2), `something`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.risk.respond.ask_missing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.risk.respond.ask_missing   [27 chars]
    en  Has something gone missing?
    >>  ............................................
    pt  Alguma coisa já sumiu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.risk.ask_missing`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.risk.ask_missing
WHO    VILLAGER — what the player reads after pressing "Has something gone missing?"
       spoken on: conversations.topic.work.outlaw.risk.respond, button `ask_missing`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.risk.ask_missing`: the villager explains. Subject `work.outlaw.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.risk.ask_missing/1   [84 chars]
    en  Twice. Both times it turned up. Neither time did anybody come and say so to my face.
    >>  ............................................
    pt  Duas vezes. Nas duas apareceu. Nas duas ninguém veio me dizer na cara.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.risk.ask_missing/2   [98 chars]
    en  A saddle, last spring. Found in a hedge, %1$s. The apology is still owed and I'm not expecting it.
    >>  ............................................
    pt  Uma sela, primavera passada. Achada numa cerca viva, %1$s. A desculpa ainda é devida e eu não espero.
    >>  ............................................
```


### Button `sympathise` — "Being first suspected every time would wear anybody down."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.risk` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.risk.sympathise` — accepted phrasings: "being first suspected every time would wear anybody down"
  - the message must contain one of: `suspected`, `wear`
  - scored words: `suspected`(1.5), `wear`(1.2), `first`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.risk.respond.sympathise   [57 chars]
    en  Being first suspected every time would wear anybody down.
    >>  ............................................
    pt  Ser o primeiro suspeito toda vez desgasta qualquer um.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.outlaw.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.outlaw.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being first suspected every time would wear anybody down."
       spoken on: conversations.topic.work.outlaw.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.risk.sympathise`: the villager accepts. Subject `work.outlaw.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.risk.sympathise/1   [91 chars]
    en  ...It does, and the wearing is the quiet kind that doesn't look like anything from outside.
    >>  ............................................
    pt  ...Desgasta, e é o desgaste silencioso que não parece nada de fora.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.risk.sympathise/2   [82 chars]
    en  I get told it's the cost of what I did. You're the first to call it wearing, %1$s.
    >>  ............................................
    pt  A maioria me diz que é o custo do que eu fiz. Você é o primeiro a chamar de desgaste, %1$s.
    >>  ............................................
```


### Button `ask_name` — "What happens when somebody walks in who knows the name?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.risk` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.risk.ask_name` — accepted phrasings: "what happens when somebody walks in who knows the name"
  - the message must contain one of: `name`, `walks`, `recognised`
  - scored words: `name`(1.5), `walks`(1.2), `recognised`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.risk.respond.ask_name
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.risk.respond.ask_name   [55 chars]
    en  What happens when somebody walks in who knows the name?
    >>  ............................................
    pt  O que acontece quando alguém entra sabendo o nome?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.risk.ask_name`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.risk.ask_name
WHO    VILLAGER — what the player reads after pressing "What happens when somebody walks in who knows the name?"
       spoken on: conversations.topic.work.outlaw.risk.respond, button `ask_name`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.risk.ask_name`: the villager explains. Subject `work.outlaw.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.risk.ask_name/1   [84 chars]
    en  Then I find out what four years of splitting kindling is worth against one sentence.
    >>  ............................................
    pt  Aí eu descubro quanto valem quatro anos rachando lenha contra uma frase.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.risk.ask_name/2   [84 chars]
    en  I've a bag packed. I've had a bag packed for four years, %1$s, and I hate that I do.
    >>  ............................................
    pt  Tenho uma bolsa pronta. Tenho há quatro anos, %1$s, e eu odeio que tenha.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.risk.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.outlaw.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw.task` — e.g. "Sitting where I can see the road and be seen doing nothing. It's a full day's occupation, oddly."


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.task.respond   [15 chars]
    en  That's the day.
    >>  ............................................
    pt  É o dia.
    >>  ............................................
```


### Button `ask_seen` — "Why does being seen doing nothing take all day?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.task` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.task.ask_seen` — accepted phrasings: "why does being seen doing nothing take all day"
  - the message must contain one of: `seen`, `nothing`, `watched`
  - scored words: `seen`(1.5), `nothing`(1.0), `watched`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.task.respond.ask_seen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.task.respond.ask_seen   [47 chars]
    en  Why does being seen doing nothing take all day?
    >>  ............................................
    pt  Por que ser visto sem fazer nada leva o dia todo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.task.ask_seen`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.task.ask_seen
WHO    VILLAGER — what the player reads after pressing "Why does being seen doing nothing take all day?"
       spoken on: conversations.topic.work.outlaw.task.respond, button `ask_seen`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.task.ask_seen`: the villager explains. Subject `work.outlaw.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.task.ask_seen/1   [94 chars]
    en  Because the day I'm not where they can see me is the day something goes missing and it's mine.
    >>  ............................................
    pt  Porque o dia em que eu não estiver visível é o dia em que algo some e é meu.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.task.ask_seen/2   [67 chars]
    en  It's the cheapest alibi there is, %1$s, and I have needed it twice.
    >>  ............................................
    pt  É o álibi mais barato que existe, %1$s, e eu precisei dele duas vezes.
    >>  ............................................
```


### Button `offer_hands` — "I'll split the rest of that kindling."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.outlaw.task` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.task.offer_hands` — accepted phrasings: "i'll split the rest of that kindling"
  - the message must contain one of: `kindling`, `split`
  - scored words: `kindling`(1.5), `split`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.task.respond.offer_hands   [37 chars]
    en  I'll split the rest of that kindling.
    >>  ............................................
    pt  Eu racho o resto dessa lenha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.outlaw.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.outlaw.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll split the rest of that kindling."
       spoken on: conversations.topic.work.outlaw.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.task.offer_hands`: the villager accepts. Subject `work.outlaw.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.task.offer_hands/1   [85 chars]
    en  ...You'd be seen doing it. I'd think about that before you pick up the axe, honestly.
    >>  ............................................
    pt  ...Você seria visto fazendo. Eu pensaria nisso antes de pegar o machado, sinceramente.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.task.offer_hands/2   [77 chars]
    en  Then take the far side of the pile, %1$s, and let her tell people you did it.
    >>  ............................................
    pt  Então pegue o outro lado da pilha, %1$s, e deixe ela contar às pessoas que foi você.
    >>  ............................................
```


### Button `ask_widow` — "Why does the widow ask you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.task` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.task.ask_widow` — accepted phrasings: "why does the widow ask you"
  - the message must contain one of: `widow`, `asks`
  - scored words: `widow`(1.5), `asks`(1.2), `why`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.task.respond.ask_widow
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.task.respond.ask_widow   [27 chars]
    en  Why does the widow ask you?
    >>  ............................................
    pt  Por que a viúva pede a você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.task.ask_widow`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.task.ask_widow
WHO    VILLAGER — what the player reads after pressing "Why does the widow ask you?"
       spoken on: conversations.topic.work.outlaw.task.respond, button `ask_widow`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.task.ask_widow`: the villager explains. Subject `work.outlaw.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.task.ask_widow/1   [91 chars]
    en  She says she's too old to be frightened of anybody and too tired to split her own kindling.
    >>  ............................................
    pt  Ela diz que é velha demais pra ter medo de alguém e cansada demais pra rachar a própria lenha.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.task.ask_widow/2   [85 chars]
    en  Because her son was one too, %1$s, somewhere else, and nobody there did his kindling.
    >>  ............................................
    pt  Porque o filho dela também era, %1$s, em outro lugar, e ninguém lá rachou a lenha dele.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.task.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.outlaw.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.outlaw.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.outlaw.village` — e.g. "This place let me stay when it had no reason to. I've not forgotten that and I've not been told it counts."


```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.outlaw.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.outlaw.village.respond   [25 chars]
    en  That's how I sit with it.
    >>  ............................................
    pt  É assim que eu fico com isso.
    >>  ............................................
```


### Button `ask_stay` — "Who let you stay?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.village` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.village.ask_stay` — accepted phrasings: "who let you stay"
  - the message must contain one of: `stay`, `allowed`
  - scored words: `stay`(1.5), `let`(0.8), `allowed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.village.respond.ask_stay
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.village.respond.ask_stay   [17 chars]
    en  Who let you stay?
    >>  ............................................
    pt  Quem te deixou ficar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.village.ask_stay`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.village.ask_stay
WHO    VILLAGER — what the player reads after pressing "Who let you stay?"
       spoken on: conversations.topic.work.outlaw.village.respond, button `ask_stay`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.village.ask_stay`: the villager explains. Subject `work.outlaw.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.village.ask_stay/1   [101 chars]
    en  The cleric argued for it and the mayor allowed it and the guard said nothing. I know all three votes.
    >>  ............................................
    pt  A clériga defendeu, o prefeito permitiu e o guarda não disse nada. Sei os três votos.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.village.ask_stay/2   [97 chars]
    en  One person spoke and eleven didn't object, %1$s. That's the whole of my welcome and I'll take it.
    >>  ............................................
    pt  Uma pessoa falou e onze não objetaram, %1$s. É toda a minha acolhida e eu aceito.
    >>  ............................................
```


### Button `say_thanks` — "Four years of no trouble is you keeping your side."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.outlaw.village` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.village.say_thanks` — accepted phrasings: "four years of no trouble is you keeping your side"
  - the message must contain one of: `side`, `four`, `kept`
  - scored words: `side`(1.5), `four`(1.0), `kept`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.village.respond.say_thanks   [50 chars]
    en  Four years of no trouble is you keeping your side.
    >>  ............................................
    pt  Quatro anos sem problema é você cumprindo sua parte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.outlaw.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.outlaw.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Four years of no trouble is you keeping your side."
       spoken on: conversations.topic.work.outlaw.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.village.say_thanks`: the villager accepts. Subject `work.outlaw.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.village.say_thanks/1   [79 chars]
    en  ...My side. That's a word that implies there are two, and I'd not have used it.
    >>  ............................................
    pt  ...Minha parte. É uma palavra que implica que tem duas, e eu não teria usado.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.village.say_thanks/2   [79 chars]
    en  Nobody has framed it as a bargain I'm holding up, %1$s. Only as a leash I'm on.
    >>  ............................................
    pt  Ninguém colocou como um acordo que eu estou cumprindo, %1$s. Só como uma coleira.
    >>  ............................................
```


### Button `ask_invited` — "Nobody's invited you through a door?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.outlaw.village` · offered only once the villager has actually said `work:outlaw`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.outlaw.village.ask_invited` — accepted phrasings: "nobody's invited you through a door"
  - the message must contain one of: `invited`, `door`, `inside`
  - scored words: `invited`(1.5), `door`(1.0), `inside`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.village.respond.ask_invited
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.village.respond.ask_invited   [36 chars]
    en  Nobody's invited you through a door?
    >>  ............................................
    pt  Ninguém te convidou pra dentro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.outlaw.village.ask_invited`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.outlaw.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a way back for you?" | "Watch yourself."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.village.ask_invited
WHO    VILLAGER — what the player reads after pressing "Nobody's invited you through a door?"
       spoken on: conversations.topic.work.outlaw.village.respond, button `ask_invited`
       leaves the player on: conversations.topic.work.outlaw.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.village.ask_invited`: the villager explains. Subject `work.outlaw.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.outlaw.village.ask_invited/1   [88 chars]
    en  The widow, for tea, twice. I count that as two and I have counted it a great many times.
    >>  ............................................
    pt  A viúva, pra um chá, duas vezes. Eu conto como duas e eu já contei muitas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.village.ask_invited/2   [98 chars]
    en  Not once in four years, %1$s. I'd like you to know that I keep track without complaining about it.
    >>  ............................................
    pt  Nem uma vez em quatro anos, %1$s. Queria que você soubesse que eu conto sem reclamar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.outlaw.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.outlaw.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.outlaw.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.outlaw.village.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.outlaw.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.left`: the villager accepts. Subject `work.outlaw.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.outlaw.followup / leave; conversations.scene.work.outlaw.old_associate.blocked.respond / leave; conversations.scene.work.outlaw.old_associate.succeeded.respond / leave; conversations.scene.work.outlaw.old_debt.blocked.respond / leave; conversations.scene.work.outlaw.old_debt.succeeded.respond / leave; conversations.scene.work.outlaw.the_name.active.respond / leave; conversations.scene.work.outlaw.the_name.succeeded.respond / leave; conversations.topic.work.outlaw.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.outlaw.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

