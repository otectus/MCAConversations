# Work talk with a miner

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.miner.exhausted_seam.blocked.respond`](#conversations-scene-work-miner-exhausted-seam-blocked-respond)
- [`conversations.scene.work.miner.exhausted_seam.succeeded.respond`](#conversations-scene-work-miner-exhausted-seam-succeeded-respond)
- [`conversations.scene.work.miner.failing_props.blocked.respond`](#conversations-scene-work-miner-failing-props-blocked-respond)
- [`conversations.scene.work.miner.failing_props.succeeded.respond`](#conversations-scene-work-miner-failing-props-succeeded-respond)
- [`conversations.scene.work.miner.followup`](#conversations-scene-work-miner-followup)
- [`conversations.scene.work.miner.the_day_it_moved.succeeded.respond`](#conversations-scene-work-miner-the-day-it-moved-succeeded-respond)
- [`conversations.topic.work.miner.craft.respond`](#conversations-topic-work-miner-craft-respond)
- [`conversations.topic.work.miner.followup`](#conversations-topic-work-miner-followup)
- [`conversations.topic.work.miner.future.respond`](#conversations-topic-work-miner-future-respond)
- [`conversations.topic.work.miner.respond`](#conversations-topic-work-miner-respond)
- [`conversations.topic.work.miner.risk.respond`](#conversations-topic-work-miner-risk-respond)
- [`conversations.topic.work.miner.task.respond`](#conversations-topic-work-miner-task-respond)
- [`conversations.topic.work.miner.village.respond`](#conversations-topic-work-miner-village-respond)

---

## `conversations.scene.work.miner.exhausted_seam.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.miner.exhausted_seam.blocked` — e.g. "%2$s has given me %3$s, and I have been walking in every morning to look at it as if looking will change it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.miner.exhausted_seam.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond   [13 chars]
    en  The workings.
    >>  ............................................
    pt  As escavações.
    >>  ............................................
```


### Button `ask_how_she_knows` — "How do you know it's finished?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.exhausted_seam.blocked` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.exhausted_seam.blocked.ask_how_she_knows` — accepted phrasings: "how do you know its finished"; "how do you know it is finished"; "what tells you the seam is done"
  - the message must contain one of: `finished`, `seam`, `done`
  - scored words: `finished`(1.8), `seam`(1.8), `done`(1.8), `know`(0.8), `its`(0.8), `tells`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.ask_how_she_knows
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.exhausted_seam.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.ask_how_she_knows   [30 chars]
    en  How do you know it's finished?
    >>  ............................................
    pt  Como você sabe que acabou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.miner.the_seam`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.miner.exhausted_seam"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How do you know it's finished?"
       spoken on: conversations.scene.work.miner.exhausted_seam.blocked.respond, button `ask_how_she_knows`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.exhausted_seam.blocked.explained`: the villager explains. Subject `work.miner.the_seam`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.explained/1   [132 chars]
    en  The rock changes colour and then it changes sound. The sound goes first if you are listening, and I was not listening in the autumn.
    >>  ............................................
    pt  A rocha muda de cor e depois muda de som. O som vai primeiro, se você estiver ouvindo, e eu não estava ouvindo no outono.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.explained/2   [116 chars]
    en  You feel it in the pick. A live face bites and a dead one bounces, and your arm knows a week before your eyes agree.
    >>  ............................................
    pt  Você sente na picareta. Uma frente viva morde e uma morta rebate, e o braço sabe uma semana antes de os olhos concordarem.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.explained/3   [140 chars]
    en  I have been wrong about this twice and right about it nine times, and I have stopped announcing which one I think it is until the third day.
    >>  ............................................
    pt  Já errei nisso duas vezes e acertei nove, e parei de anunciar qual acho que é antes do terceiro dia.
    >>  ............................................
```


### Button `advise_new_ground` — "Then open new ground."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.exhausted_seam.blocked` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.exhausted_seam.blocked.advise_new_ground` — accepted phrasings: "then open new ground"; "then open new ground"; "start a fresh working somewhere else"
  - the message must contain one of: `ground`, `fresh`, `working`
  - scored words: `ground`(1.8), `fresh`(1.8), `working`(1.8), `open`(0.8), `new`(0.8), `start`(0.8), `somewhere`(0.8), `else`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.advise_new_ground
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.exhausted_seam.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.advise_new_ground   [21 chars]
    en  Then open new ground.
    >>  ............................................
    pt  Então abra terreno novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.miner.the_seam`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.miner.exhausted_seam"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Then open new ground."
       spoken on: conversations.scene.work.miner.exhausted_seam.blocked.respond, button `advise_new_ground`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.exhausted_seam.blocked.accepted`: the villager accepts. Subject `work.miner.the_seam`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.accepted/1   [136 chars]
    en  Six weeks of digging before anything comes out, and six weeks of the village asking me daily what I have got. That is the real obstacle.
    >>  ............................................
    pt  Seis semanas cavando antes de sair qualquer coisa, e seis semanas com a vila me perguntando todo dia o que eu consegui. É esse o obstáculo real.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.accepted/2   [130 chars]
    en  Yes. There is ground I have had my eye on for two years and have not touched because touching it means admitting the east is over.
    >>  ............................................
    pt  Sim. Tem um terreno que eu observo há dois anos e não toquei, porque tocar significa admitir que o leste acabou.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.accepted/3   [133 chars]
    en  I will start Monday. It is the right answer and it is also the frightening one, because a new working is a guess with a shovel in it.
    >>  ............................................
    pt  Começo segunda. É a resposta certa e também a assustadora, porque uma escavação nova é um palpite com uma pá dentro.
    >>  ............................................
```


### Button `acknowledge_the_loss` — "Eleven years is a long attachment."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.miner.exhausted_seam.blocked` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.exhausted_seam.blocked.acknowledge_the_loss` — accepted phrasings: "eleven years is a long attachment"; "eleven years is a long attachment"; "you were attached to that seam"
  - the message must contain one of: `attachment`, `attached`, `eleven`
  - scored words: `attachment`(1.8), `attached`(1.8), `eleven`(1.8), `years`(0.8), `long`(0.8), `were`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.acknowledge_the_loss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.exhausted_seam.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.acknowledge_the_loss   [34 chars]
    en  Eleven years is a long attachment.
    >>  ............................................
    pt  Onze anos é um apego longo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.miner.the_seam`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.miner.exhausted_seam"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Eleven years is a long attachment."
       spoken on: conversations.scene.work.miner.exhausted_seam.blocked.respond, button `acknowledge_the_loss`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.exhausted_seam.blocked.steadied`: the villager accepts. Subject `work.miner.the_seam`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.steadied/1   [114 chars]
    en  People laugh when I say it. It is a hole in the ground. I have spent more waking hours in it than in my own house.
    >>  ............................................
    pt  As pessoas riem quando eu digo. É um buraco no chão. Passei mais horas acordada lá dentro do que na minha própria casa.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.steadied/2   [140 chars]
    en  Thank you. There are corners down there I know better than the faces of my neighbours, and that is either sad or it is just what a trade is.
    >>  ............................................
    pt  Obrigada. Tem cantos lá embaixo que eu conheço melhor que o rosto dos meus vizinhos, e isso ou é triste ou é simplesmente o que um ofício é.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.steadied/3   [115 chars]
    en  I will go down once more with a lamp and no tools. Not for anything. Then I will board it and start somewhere else.
    >>  ............................................
    pt  Vou descer mais uma vez com uma lamparina e sem ferramenta. Sem motivo. Depois eu fecho com tábuas e começo em outro lugar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · answers the beat(s) `work.miner.exhausted_seam.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.exhausted_seam.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você voltar lá para baixo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.scene.work.miner.exhausted_seam.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.miner.leave/1   [52 chars]
    en  It's warmer down there than you'd think. Off you go.
    >>  ............................................
    pt  É mais quente lá embaixo do que você imagina. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.miner.leave/2   [33 chars]
    en  Aye. Mind the third ladder, %1$s.
    >>  ............................................
    pt  É. Cuidado com a terceira escada, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.miner.exhausted_seam.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.miner.exhausted_seam.succeeded` — e.g. "Seven weeks and then the pick bit. I sat down on the floor of the drift and laughed, alone, in the dark."


```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.miner.exhausted_seam.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.respond   [15 chars]
    en  The new ground.
    >>  ............................................
    pt  O terreno novo.
    >>  ............................................
```


### Button `ask_about_the_wait` — "How did you stand the seven weeks?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.exhausted_seam.succeeded` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.exhausted_seam.succeeded.ask_about_the_wait` — accepted phrasings: "how did you stand the seven weeks"; "how did you stand the seven weeks"; "what got you through the empty weeks"
  - the message must contain one of: `weeks`, `stand`, `through`
  - scored words: `weeks`(1.8), `stand`(1.8), `through`(1.8), `seven`(0.8), `got`(0.8), `empty`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.respond.ask_about_the_wait
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.exhausted_seam.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.respond.ask_about_the_wait   [34 chars]
    en  How did you stand the seven weeks?
    >>  ............................................
    pt  Como você aguentou as sete semanas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.the_seam`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.miner.exhausted_seam"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How did you stand the seven weeks?"
       spoken on: conversations.scene.work.miner.exhausted_seam.succeeded.respond, button `ask_about_the_wait`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.exhausted_seam.succeeded.answered`: the villager explains. Subject `work.miner.the_seam`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.answered/1   [117 chars]
    en  By measuring. Every day I wrote down how far in I was. Progress you can read is different from progress you can feel.
    >>  ............................................
    pt  Medindo. Todo dia eu anotava quanto tinha avançado. Progresso que se lê é diferente de progresso que se sente.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.answered/2   [140 chars]
    en  Badly, for the first three. Then somebody stopped asking me what I had got and started asking how far in I was, and that changed everything.
    >>  ............................................
    pt  Mal, nas três primeiras. Depois alguém parou de perguntar o que eu tinha conseguido e passou a perguntar quanto eu tinha avançado, e isso mudou tudo.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.answered/3   [151 chars]
    en  I told myself it was six weeks and prepared for ten. Seven was a relief instead of a disappointment, and that was entirely arithmetic rather than luck.
    >>  ............................................
    pt  Disse a mim mesma que eram seis semanas e me preparei para dez. Sete virou alívio em vez de decepção, e isso foi aritmética, não sorte.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · answers the beat(s) `work.miner.exhausted_seam.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.exhausted_seam.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você voltar lá para baixo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.scene.work.miner.exhausted_seam.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.miner.failing_props.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.miner.failing_props.blocked` — e.g. "There is %2$s at %3$s and I am still working under it, which I would tell anybody else to stop doing."


```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.miner.failing_props.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.respond   [10 chars]
    en  The props.
    >>  ............................................
    pt  As escoras.
    >>  ............................................
```


### Button `offer_timber` — "I'll bring you planks for props."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.miner.failing_props.blocked` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.failing_props.blocked.offer_timber` — accepted phrasings: "ill bring you planks for props"; "i can bring you planks for props"; "let me fetch timber for that"
  - the message must contain one of: `planks`, `timber`, `props`
  - scored words: `planks`(1.8), `timber`(1.8), `props`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.respond.offer_timber
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.failing_props.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.respond.offer_timber   [32 chars]
    en  I'll bring you planks for props.
    >>  ............................................
    pt  Vou trazer tábuas para as escoras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.miner.props.offer`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.miner.props`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.failing_props", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.miner.failing_props", "obligation": "commitment:work.miner.bring_timber"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.miner.bring_timber"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you planks for props."
       spoken on: conversations.scene.work.miner.failing_props.blocked.respond, button `offer_timber`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.failing_props.blocked.accepted`: the villager accepts. Subject `work.miner.props`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.accepted/1   [72 chars]
    en  Then %2$s is safe by Thursday and I stop lying to myself on the walk in.
    >>  ............................................
    pt  Então %2$s fica seguro até quinta e eu paro de mentir para mim mesma no caminho de descida.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked.accepted/2   [125 chars]
    en  Bring green if that is what there is. Green props creak before they go, and creaking is a warning, and dry ones simply break.
    >>  ............................................
    pt  Traga verde se for o que tiver. Escora verde range antes de ceder, e ranger é aviso, e a seca simplesmente quebra.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked.accepted/3   [101 chars]
    en  Yes. And I want you to come down and see them set, because you should know exactly what you paid for.
    >>  ............................................
    pt  Sim. E quero que você desça e veja elas assentadas, porque você deve saber exatamente o que pagou.
    >>  ............................................
```


### Button `urge_stopping` — "Stay out until it's propped."

*stance family `candor` · tone `blunt` · outcome `appreciated` · answers the beat(s) `work.miner.failing_props.blocked` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.failing_props.blocked.urge_stopping` — accepted phrasings: "stay out until its propped"; "stay out until it is propped"; "keep clear of that face until the timber is in"
  - the message must contain one of: `propped`, `clear`, `timber`
  - scored words: `propped`(1.8), `clear`(1.8), `timber`(1.8), `stay`(0.8), `out`(0.8), `until`(0.8), `its`(0.8), `keep`(0.8), `face`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.respond.urge_stopping
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.failing_props.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.respond.urge_stopping   [28 chars]
    en  Stay out until it's propped.
    >>  ............................................
    pt  Fique fora até escorar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.miner.props`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.miner.failing_props"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Stay out until it's propped."
       spoken on: conversations.scene.work.miner.failing_props.blocked.respond, button `urge_stopping`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.failing_props.blocked.conceded`: the villager accepts. Subject `work.miner.props`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.conceded/1   [101 chars]
    en  You are right and I have known it for nine days. Somebody had to say it in a voice that was not mine.
    >>  ............................................
    pt  Você tem razão e eu sei disso há nove dias. Alguém precisava dizer numa voz que não fosse a minha.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked.conceded/2   [106 chars]
    en  That costs the village a week of ore. It costs the village considerably more if the roof takes me with it.
    >>  ............................................
    pt  Isso custa à vila uma semana de minério. Custa consideravelmente mais se o teto me levar junto.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked.conceded/3   [118 chars]
    en  I will board the face this evening. Boarding is the part that makes it real — after that I cannot talk myself back in.
    >>  ............................................
    pt  Vou fechar a frente com tábuas hoje à noite. Fechar é a parte que torna real — depois disso eu não consigo me convencer a voltar.
    >>  ............................................
```


### Button `ask_the_risk` — "How much warning would you get?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.failing_props.blocked` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.failing_props.blocked.ask_the_risk` — accepted phrasings: "how much warning would you get"; "how much warning would you get"; "does a roof give any warning"
  - the message must contain one of: `warning`, `roof`
  - scored words: `warning`(1.8), `roof`(1.8), `much`(0.8), `get`(0.8), `does`(0.8), `give`(0.8), `any`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.respond.ask_the_risk
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.failing_props.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.respond.ask_the_risk   [31 chars]
    en  How much warning would you get?
    >>  ............................................
    pt  Quanto aviso você teria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.props`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.miner.failing_props"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How much warning would you get?"
       spoken on: conversations.scene.work.miner.failing_props.blocked.respond, button `ask_the_risk`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.failing_props.blocked.explained`: the villager explains. Subject `work.miner.props`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.explained/1   [122 chars]
    en  Sometimes a week of creaking. Sometimes a second and a half. There is no way of telling which sort you are standing under.
    >>  ............................................
    pt  Às vezes uma semana de rangido. Às vezes um segundo e meio. Não tem como saber embaixo de qual dos dois você está.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked.explained/2   [114 chars]
    en  Dust first. Fine dust off the roof, no sound at all, and if you see it you have about the time it takes to say so.
    >>  ............................................
    pt  Poeira primeiro. Poeira fina caindo do teto, sem som nenhum, e se você vir tem mais ou menos o tempo de dizer isso.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked.explained/3   [134 chars]
    en  That is the whole argument for props. You do not prop the roofs that are going to fall; you prop all of them, because you cannot tell.
    >>  ............................................
    pt  É esse o argumento inteiro a favor das escoras. Você não escora os tetos que vão cair; escora todos, porque não dá para saber.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · answers the beat(s) `work.miner.failing_props.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.failing_props.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você voltar lá para baixo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.scene.work.miner.failing_props.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.miner.failing_props.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.miner.failing_props.succeeded` — e.g. "%2$s is propped end to end. I set them myself and I have been back down twice just to look at them."


```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.miner.failing_props.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.miner.failing_props.succeeded.respond   [17 chars]
    en  The props, since.
    >>  ............................................
    pt  As escoras, depois disso.
    >>  ............................................
```


### Button `note_the_sleep` — "Sleeping through is worth nine days."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.miner.failing_props.succeeded` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.failing_props.succeeded.note_the_sleep` — accepted phrasings: "sleeping through is worth nine days"; "sleeping through is worth nine days"; "the sleep alone was worth it"
  - the message must contain one of: `sleeping`, `sleep`, `worth`
  - scored words: `sleeping`(1.8), `sleep`(1.8), `worth`(1.8), `through`(0.8), `nine`(0.8), `days`(0.8), `alone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.succeeded.respond.note_the_sleep
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.failing_props.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.failing_props.succeeded.respond.note_the_sleep   [36 chars]
    en  Sleeping through is worth nine days.
    >>  ............................................
    pt  Dormir a noite vale nove dias.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.miner.props`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.miner.failing_props"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Sleeping through is worth nine days."
       spoken on: conversations.scene.work.miner.failing_props.succeeded.respond, button `note_the_sleep`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.failing_props.succeeded.acknowledged`: the villager accepts. Subject `work.miner.props`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.failing_props.succeeded.acknowledged/1   [115 chars]
    en  I had not counted it as a cost, which is how it managed to run for nine days without me noticing what it was doing.
    >>  ............................................
    pt  Eu não tinha contabilizado como custo, que é como aquilo conseguiu correr nove dias sem eu perceber o que estava fazendo comigo.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.succeeded.acknowledged/2   [121 chars]
    en  Thank you. Nobody weighs the worrying. It does not go in the ledger and it takes about as much out of you as the digging.
    >>  ............................................
    pt  Obrigada. Ninguém pesa a preocupação. Não entra no livro-caixa e tira de você mais ou menos tanto quanto a cavação.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.succeeded.acknowledged/3   [136 chars]
    en  Nine days of ore and one winter of sleep. Written down like that, it was never a difficult decision, and I still took nine days over it.
    >>  ............................................
    pt  Nove dias de minério e um inverno de sono. Escrito assim, nunca foi decisão difícil, e ainda assim levei nove dias.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · answers the beat(s) `work.miner.failing_props.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.failing_props.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.failing_props.succeeded.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você voltar lá para baixo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.scene.work.miner.failing_props.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.miner.followup`

**Reached from 10 route(s):** `conversations.scene.work.miner.exhausted_seam.blocked.respond` / `ask_how_she_knows`; `conversations.scene.work.miner.exhausted_seam.blocked.respond` / `advise_new_ground`; `conversations.scene.work.miner.exhausted_seam.blocked.respond` / `acknowledge_the_loss`; `conversations.scene.work.miner.exhausted_seam.succeeded.respond` / `ask_about_the_wait`; `conversations.scene.work.miner.failing_props.blocked.respond` / `offer_timber`; `conversations.scene.work.miner.failing_props.blocked.respond` / `urge_stopping`; `conversations.scene.work.miner.failing_props.blocked.respond` / `ask_the_risk`; `conversations.scene.work.miner.failing_props.succeeded.respond` / `note_the_sleep`; `conversations.scene.work.miner.the_day_it_moved.succeeded.respond` / `ask_about_going_back`; `conversations.scene.work.miner.the_day_it_moved.succeeded.respond` / `say_that_sounds_hard`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.miner.exhausted_seam.blocked.accepted` — e.g. "Six weeks of digging before anything comes out, and six weeks of the village asking me daily what I have got. That is the real obstacle."
- `conversations.scene.work.miner.exhausted_seam.blocked.explained` — e.g. "The rock changes colour and then it changes sound. The sound goes first if you are listening, and I was not listening in the autumn."
- `conversations.scene.work.miner.exhausted_seam.blocked.steadied` — e.g. "People laugh when I say it. It is a hole in the ground. I have spent more waking hours in it than in my own house."
- `conversations.scene.work.miner.exhausted_seam.succeeded.answered` — e.g. "By measuring. Every day I wrote down how far in I was. Progress you can read is different from progress you can feel."
- `conversations.scene.work.miner.failing_props.blocked.accepted` — e.g. "Then %2$s is safe by Thursday and I stop lying to myself on the walk in."
- `conversations.scene.work.miner.failing_props.blocked.conceded` — e.g. "You are right and I have known it for nine days. Somebody had to say it in a voice that was not mine."
- `conversations.scene.work.miner.failing_props.blocked.explained` — e.g. "Sometimes a week of creaking. Sometimes a second and a half. There is no way of telling which sort you are standing under."
- `conversations.scene.work.miner.failing_props.succeeded.acknowledged` — e.g. "I had not counted it as a cost, which is how it managed to run for nine days without me noticing what it was doing."
- `conversations.scene.work.miner.the_day_it_moved.succeeded.answered` — e.g. "With somebody else. I have never gone down alone on a first day since, and I tell every new miner the same thing."
- `conversations.scene.work.miner.the_day_it_moved.succeeded.softened` — e.g. "It was, and I got told at the time that eleven days was a lot, by people who had never stood under a roof that sighed."


```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.miner.followup   [12 chars]
    en  More to ask?
    >>  ............................................
    pt  Mais perguntas?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a deep seam?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.miner.*` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.followup.ask_more` — accepted phrasings: "whats the hardest part of a deep seam"; "what is the hardest part of a deep seam"; "hardest thing about working a deep seam"
  - the message must contain one of: `hardest`, `seam`
  - scored words: `hardest`(1.8), `seam`(1.8), `whats`(0.8), `part`(0.8), `deep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.followup.ask_more   [39 chars]
    en  What's the hardest part of a deep seam?
    >>  ............................................
    pt  Qual é a parte mais difícil de um veio fundo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a deep seam?"
       spoken on: conversations.scene.work.miner.followup, button `ask_more`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.hard`: the villager explains. Subject `work.miner.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.miner.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.miner.hard/1   [76 chars]
    en  The timber. Wood talks before stone does, and stone doesn't warn you at all.
    >>  ............................................
    pt  A madeira. Madeira fala antes da pedra, e pedra não avisa nada.
    >>  ............................................
  dialogue.conversations.work.prof.miner.hard/2   [79 chars]
    en  Water, mostly. And the thing under the water noise that I've never named, %1$s.
    >>  ............................................
    pt  Água, principalmente. E aquilo debaixo do som da água que eu nunca nomeei, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the seam."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.miner.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.followup.leave   [27 chars]
    en  I'll leave you to the seam.
    >>  ............................................
    pt  Vou deixar você com o veio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the seam."
       spoken on: conversations.scene.work.miner.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.miner.the_day_it_moved.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.miner.the_day_it_moved.succeeded` — e.g. "Four years ago there was %2$s and I walked out and I did not go back down for eleven days."


```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.miner.the_day_it_moved.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond   [17 chars]
    en  The day it moved.
    >>  ............................................
    pt  O dia em que se mexeu.
    >>  ............................................
```


### Button `ask_about_going_back` — "How did you go back down?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.miner.the_day_it_moved.succeeded` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.the_day_it_moved.succeeded.ask_about_going_back` — accepted phrasings: "how did you go back down"; "how did you go back down"; "what got you underground again"
  - the message must contain one of: `back`, `underground`, `again`
  - scored words: `back`(1.8), `underground`(1.8), `again`(1.8), `down`(0.8), `got`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond.ask_about_going_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.the_day_it_moved.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond.ask_about_going_back   [25 chars]
    en  How did you go back down?
    >>  ............................................
    pt  Como você voltou a descer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.miner.the_day_it_moved`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.miner.the_day_it_moved"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How did you go back down?"
       spoken on: conversations.scene.work.miner.the_day_it_moved.succeeded.respond, button `ask_about_going_back`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.the_day_it_moved.succeeded.answered`: the villager explains. Subject `work.miner.the_day_it_moved`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.answered/1   [113 chars]
    en  With somebody else. I have never gone down alone on a first day since, and I tell every new miner the same thing.
    >>  ............................................
    pt  Com outra pessoa. Desde então nunca desci sozinha num primeiro dia, e digo a mesma coisa a todo mineiro novo.
    >>  ............................................
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.answered/2   [145 chars]
    en  Ten feet at a time. Day one I stood at the mouth. Day four I got to the first bend. It was eleven days and it was not bravery, it was arithmetic.
    >>  ............................................
    pt  Três metros por vez. No primeiro dia fiquei na boca. No quarto cheguei à primeira curva. Foram onze dias e não foi coragem, foi aritmética.
    >>  ............................................
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.answered/3   [129 chars]
    en  I went down and touched the face and came straight back up. That was the whole trip and it was the most useful hour of that year.
    >>  ............................................
    pt  Desci, toquei a frente e voltei direto. Foi a viagem inteira e foi a hora mais útil daquele ano.
    >>  ............................................
```


### Button `say_that_sounds_hard` — "Eleven days is a long climb back."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.miner.the_day_it_moved.succeeded` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.miner.the_day_it_moved.succeeded.say_that_sounds_hard` — accepted phrasings: "eleven days is a long climb back"; "eleven days is a long climb back"; "that was a long way back for you"
  - the message must contain one of: `eleven`, `climb`, `long`
  - scored words: `eleven`(1.8), `climb`(1.8), `long`(1.8), `days`(0.8), `way`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond.say_that_sounds_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.the_day_it_moved.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond.say_that_sounds_hard   [33 chars]
    en  Eleven days is a long climb back.
    >>  ............................................
    pt  Onze dias é uma volta longa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +2  _(recorded under topic `work.miner.the_day_it_moved`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.miner.the_day_it_moved"}
- Then opens: `conversations.scene.work.miner.followup`
- …where the player's next choices will be: "What's the hardest part of a deep seam?" | "I'll leave you to the seam."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.softened
WHO    VILLAGER — what the player reads after pressing "Eleven days is a long climb back."
       spoken on: conversations.scene.work.miner.the_day_it_moved.succeeded.respond, button `say_that_sounds_hard`
       leaves the player on: conversations.scene.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.the_day_it_moved.succeeded.softened`: the villager accepts. Subject `work.miner.the_day_it_moved`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.softened/1   [118 chars]
    en  It was, and I got told at the time that eleven days was a lot, by people who had never stood under a roof that sighed.
    >>  ............................................
    pt  Foi, e me disseram na época que onze dias era muito, gente que nunca esteve embaixo de um teto que suspirou.
    >>  ............................................
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.softened/2   [151 chars]
    en  Thank you. The trade does not talk about this part. Everybody has a day like it and nobody mentions theirs, so everybody thinks they were the slow one.
    >>  ............................................
    pt  Obrigada. O ofício não fala dessa parte. Todo mundo tem um dia assim e ninguém menciona o seu, então todo mundo acha que foi o lento.
    >>  ............................................
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.softened/3   [120 chars]
    en  I say it out loud now, to the young ones especially. Eleven days, and I still went back, and both halves of that matter.
    >>  ............................................
    pt  Eu digo em voz alta agora, principalmente para os novos. Onze dias, e eu voltei mesmo assim, e as duas metades importam.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · answers the beat(s) `work.miner.the_day_it_moved.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.miner.the_day_it_moved.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você voltar lá para baixo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.scene.work.miner.the_day_it_moved.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner.craft` — e.g. "Reading rock. Where it's sound, where it's lying to you, and where it changed its mind a long time ago."


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.craft.respond   [21 chars]
    en  That's the knowledge.
    >>  ............................................
    pt  É esse o conhecimento.
    >>  ............................................
```


### Button `ask_lying` — "How does rock lie to you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.craft` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.craft.ask_lying` — accepted phrasings: "how does rock lie to you"
  - the message must contain one of: `lying`, `rock`, `lie`
  - scored words: `lying`(1.5), `rock`(1.2), `lie`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.craft.respond.ask_lying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.craft.respond.ask_lying   [25 chars]
    en  How does rock lie to you?
    >>  ............................................
    pt  Como a rocha mente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.craft.ask_lying`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.craft.ask_lying
WHO    VILLAGER — what the player reads after pressing "How does rock lie to you?"
       spoken on: conversations.topic.work.miner.craft.respond, button `ask_lying`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.craft.ask_lying`: the villager explains. Subject `work.miner.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.craft.ask_lying/1   [103 chars]
    en  It looks solid and it's a lid. There's a word for the sound it makes and I'd rather you never heard it.
    >>  ............................................
    pt  Parece sólida e é uma tampa. Tem um nome pro som que ela faz e eu prefiro que você nunca ouça.
    >>  ............................................
  dialogue.conversations.work.prof.miner.craft.ask_lying/2   [84 chars]
    en  It holds beautifully for eleven months and then it rains hard, %1$s. That's the lie.
    >>  ............................................
    pt  Ela segura lindamente por onze meses e aí chove forte, %1$s. É essa a mentira.
    >>  ............................................
```


### Button `admire` — "Eleven is young to be underground."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.craft` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.craft.admire` — accepted phrasings: "eleven is young to be underground"
  - the message must contain one of: `eleven`, `young`, `underground`
  - scored words: `eleven`(1.5), `young`(1.5), `underground`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.craft.respond.admire   [34 chars]
    en  Eleven is young to be underground.
    >>  ............................................
    pt  Onze anos é novo pra estar debaixo da terra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.miner.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.miner.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.craft.admire
WHO    VILLAGER — what the player reads after pressing "Eleven is young to be underground."
       spoken on: conversations.topic.work.miner.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.craft.admire`: the villager accepts. Subject `work.miner.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.craft.admire/1   [95 chars]
    en  It was ordinary then. It isn't now, and I've made sure it isn't, and that's my one achievement.
    >>  ............................................
    pt  Era comum então. Não é agora, e eu garanti que não fosse, e é minha única conquista.
    >>  ............................................
  dialogue.conversations.work.prof.miner.craft.admire/2   [96 chars]
    en  It was, and I'd not let a child of mine, %1$s, and my father would have said the same about his.
    >>  ............................................
    pt  Era, e eu não deixaria um filho meu, %1$s, e meu pai teria dito o mesmo do dele.
    >>  ............................................
```


### Button `ask_words` — "How do you teach it without words?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.craft` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.craft.ask_words` — accepted phrasings: "how do you teach it without words"
  - the message must contain one of: `teach`, `words`
  - scored words: `teach`(1.5), `words`(1.2), `without`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.craft.respond.ask_words
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.craft.respond.ask_words   [34 chars]
    en  How do you teach it without words?
    >>  ............................................
    pt  Como se ensina sem palavras?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.craft.ask_words`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.craft.ask_words
WHO    VILLAGER — what the player reads after pressing "How do you teach it without words?"
       spoken on: conversations.topic.work.miner.craft.respond, button `ask_words`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.craft.ask_words`: the villager explains. Subject `work.miner.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.craft.ask_words/1   [95 chars]
    en  You take them down and you're quiet and they watch where you put your hand and where you don't.
    >>  ............................................
    pt  Você desce com eles e fica quieto e eles veem onde você põe a mão e onde não põe.
    >>  ............................................
  dialogue.conversations.work.prof.miner.craft.ask_words/2   [87 chars]
    en  The same way he taught me: badly, and it worked, %1$s, and I'd do it better if I could.
    >>  ............................................
    pt  Do mesmo jeito que ele me ensinou: mal, e funcionou, %1$s, e eu faria melhor se pudesse.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.craft.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você descer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.topic.work.miner.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.followup / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.followup`

**Reached from 20 route(s):** `conversations.scene.work.miner.followup` / `ask_more`; `conversations.topic.work.miner.craft.respond` / `ask_lying`; `conversations.topic.work.miner.craft.respond` / `admire`; `conversations.topic.work.miner.craft.respond` / `ask_words`; `conversations.topic.work.miner.future.respond` / `ask_saving`; `conversations.topic.work.miner.future.respond` / `encourage`; `conversations.topic.work.miner.future.respond` / `ask_inscription`; `conversations.topic.work.miner.respond` / `ask_hard`; `conversations.topic.work.miner.respond` / `value`; `conversations.topic.work.miner.respond` / `challenge`; `conversations.topic.work.miner.respond` / `challenge`; `conversations.topic.work.miner.risk.respond` / `ask_fall` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner.challenge.landed` — e.g. "I do. Knowing which rock and which dark is the difference between a seam and a grave."
- `conversations.work.prof.miner.challenge.stung` — e.g. "...I've held a roof up with my shoulder. Tell me again what I just do."
- `conversations.work.prof.miner.craft.admire` — e.g. "It was ordinary then. It isn't now, and I've made sure it isn't, and that's my one achievement."
- `conversations.work.prof.miner.craft.ask_lying` — e.g. "It looks solid and it's a lid. There's a word for the sound it makes and I'd rather you never heard it."
- `conversations.work.prof.miner.craft.ask_words` — e.g. "You take them down and you're quiet and they watch where you put your hand and where you don't."
- `conversations.work.prof.miner.future.ask_inscription` — e.g. "The mason cuts a name. That's what a fall is when there's one man down there."
- `conversations.work.prof.miner.future.ask_saving` — e.g. "For whoever comes after me. It's the only inheritance I've got and it's underground."
- `conversations.work.prof.miner.future.encourage` — e.g. "...First. Aye. I've been treating the seam as the goal and the hands as the luxury."
- `conversations.work.prof.miner.hard` — e.g. "The timber. Wood talks before stone does, and stone doesn't warn you at all."
- `conversations.work.prof.miner.risk.ask_fall` — e.g. "My father. I set the props that morning and there were three of them, and that is the whole account."
- `conversations.work.prof.miner.risk.ask_loan` — e.g. "The rock was there first and it will be there after. You're a guest with a lamp."
- `conversations.work.prof.miner.risk.sympathise` — e.g. "...Every day. You're the first to put those two facts next to each other out loud."
- `conversations.work.prof.miner.task.ask_dull` — e.g. "Anyone can find a seam. Getting it up a ladder for six months is where the money actually is."
- `conversations.work.prof.miner.task.ask_props` — e.g. "I don't. I know three isn't, because I've heard three not be, from the far side of a fall."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.followup   [23 chars]
    en  That's the seam and me.
    >>  ............................................
    pt  É o veio e eu.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.challenge.landed`, `work.miner.challenge.stung`, `work.miner.craft.admire`, `work.miner.craft.ask_lying`, `work.miner.craft.ask_words`, `work.miner.future.ask_inscription`, `work.miner.future.ask_saving`, `work.miner.future.encourage`, `work.miner.hard`, `work.miner.risk.ask_fall`, `work.miner.risk.ask_loan`, `work.miner.risk.sympathise`, `work.miner.task.ask_dull`, `work.miner.task.ask_props`, `work.miner.task.offer_hands`, `work.miner.value`, `work.miner.village.ask_ladder`, `work.miner.village.ask_smith`, `work.miner.village.say_thanks` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.miner.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `below`, `underground`
  - scored words: `thought`(1.2), `below`(1.5), `underground`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.miner.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.miner.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.miner.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.miner.thanks`: the villager accepts. Subject `work.miner.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.thanks/1   [70 chars]
    en  Nobody thinks about below. That's rather the shape of the whole trade.
    >>  ............................................
    pt  Ninguém pensa no que está embaixo. É mais ou menos o formato do ofício inteiro.
    >>  ............................................
  dialogue.conversations.work.prof.miner.thanks/2   [72 chars]
    en  Down there you get a lot of time to think and nobody to say it to, %1$s.
    >>  ............................................
    pt  Lá embaixo você tem muito tempo pra pensar e ninguém pra falar, %1$s.
    >>  ............................................
```


### Button `ask_more` — "Have you had a close call?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.challenge.landed`, `work.miner.challenge.stung`, `work.miner.craft.admire`, `work.miner.craft.ask_lying`, `work.miner.craft.ask_words`, `work.miner.future.ask_inscription`, `work.miner.future.ask_saving`, `work.miner.future.encourage`, `work.miner.hard`, `work.miner.risk.ask_fall`, `work.miner.risk.ask_loan`, `work.miner.risk.sympathise`, `work.miner.task.ask_dull`, `work.miner.task.ask_props`, `work.miner.task.offer_hands`, `work.miner.value`, `work.miner.village.ask_ladder`, `work.miner.village.ask_smith`, `work.miner.village.say_thanks` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.miner.more` — accepted phrasings: "have you had a close call"
  - the message must contain one of: `close`, `call`, `accident`
  - scored words: `close`(1.2), `call`(1.2), `accident`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.followup.ask_more   [26 chars]
    en  Have you had a close call?
    >>  ............................................
    pt  Você já se safou por pouco?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.miner.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.more
WHO    VILLAGER — what the player reads after pressing "Have you had a close call?"
       spoken on: conversations.topic.work.miner.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.miner.more`: the villager discloses. Subject `work.miner.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.more/1   [81 chars]
    en  One. Four hours behind a fall with two candles. I ration candles differently now.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desabamento com duas velas. Eu raciono vela diferente agora.
    >>  ............................................
  dialogue.conversations.work.prof.miner.more/2   [71 chars]
    en  Every miner has. Mine was quiet, which is the sort that stays with you.
    >>  ............................................
    pt  Todo mineiro já. A minha foi silenciosa, e é desse tipo que fica com a gente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall. My father was on the other side of it and he did not come out.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento. Meu pai estava do outro lado e não saiu.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. There's a name on the drift wall and I pass it going in every day.
    >>  ............................................
    pt  Um segundo par de mãos. Tem um nome na parede da galeria e eu passo por ele na entrada todo dia.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours in the dark. You learn a great deal about yourself and most of it keeps.
    >>  ............................................
    pt  Uma. Quatro horas no escuro. Você aprende muito sobre si e quase tudo se conserva.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands, in time. The seam east of the drift will wait; it has waited this long.
    >>  ............................................
    pt  Um segundo par de mãos, com o tempo. O veio a leste espera; já esperou até agora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall with two candles. I ration candles differently now.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento com duas velas. Eu raciono velas diferente agora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. Then a fall is a rescue instead of an inscription.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Aí um desmoronamento é resgate em vez de inscrição.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall with two candles. I ration candles differently now.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento com duas velas. Eu raciono velas diferente agora.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. Then a fall is a rescue instead of an inscription.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Aí um desmoronamento é resgate em vez de inscrição.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall. The smith sat at the shaft head the whole time and never said so.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento. O ferreiro ficou na boca do poço o tempo todo e nunca contou.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. I'd teach anyone. I'd be glad of the company more than the help.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Eu ensinaria qualquer um. A companhia me faria mais falta que a ajuda.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall. The smith sat at the shaft head the whole time and never said so.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento. O ferreiro ficou na boca do poço o tempo todo e nunca contou.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. I'd teach anyone. I'd be glad of the company more than the help.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Eu ensinaria qualquer um. A companhia me faria mais falta que a ajuda.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall. The smith sat at the shaft head the whole time and never said so.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento. O ferreiro ficou na boca do poço o tempo todo e nunca contou.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. I'd teach anyone. I'd be glad of the company more than the help.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Eu ensinaria qualquer um. A companhia me faria mais falta que a ajuda.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall. My father was on the other side of it and he did not come out.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento. Meu pai estava do outro lado e não saiu.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. There's a name on the drift wall and I pass it going in every day.
    >>  ............................................
    pt  Um segundo par de mãos. Tem um nome na parede da galeria e eu passo por ele na entrada todo dia.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall with two candles. I ration candles differently now.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento com duas velas. Eu raciono velas diferente agora.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. Then a fall is a rescue instead of an inscription.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Aí um desmoronamento é resgate em vez de inscrição.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall with two candles. I ration candles differently now.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento com duas velas. Eu raciono velas diferente agora.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands below. Then a fall is a rescue instead of an inscription.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo. Aí um desmoronamento é resgate em vez de inscrição.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours, two candles, and I did not use the second until the third hour.
    >>  ............................................
    pt  Uma. Quatro horas, duas velas, e eu não usei a segunda até a terceira hora.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. There's a seam east of the old drift I've never opened, and I'm saving it.
    >>  ............................................
    pt  Um segundo par de mãos. Tem um veio a leste da galeria velha que eu nunca abri, e estou guardando.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours in the dark. You learn a great deal about yourself and most of it keeps.
    >>  ............................................
    pt  Uma. Quatro horas no escuro. Você aprende muito sobre si e quase tudo se conserva.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands, in time. The seam east of the drift will wait; it has waited this long.
    >>  ............................................
    pt  Um segundo par de mãos, com o tempo. O veio a leste espera; já esperou até agora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours, two candles, and I did not use the second until the third hour.
    >>  ............................................
    pt  Uma. Quatro horas, duas velas, e eu não usei a segunda até a terceira hora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. There's a seam east of the old drift I've never opened, and I'm saving it.
    >>  ............................................
    pt  Um segundo par de mãos. Tem um veio a leste da galeria velha que eu nunca abri, e estou guardando.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours in the dark. You learn a great deal about yourself and most of it keeps.
    >>  ............................................
    pt  Uma. Quatro horas no escuro. Você aprende muito sobre si e quase tudo se conserva.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands, in time. The seam east of the drift will wait; it has waited this long.
    >>  ............................................
    pt  Um segundo par de mãos, com o tempo. O veio a leste espera; já esperou até agora.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.miner.more/1
    en  One! Four hours behind a fall with two candles. I have opinions about candles now.
    >>  ............................................
    pt  Uma! Quatro horas atrás de um desmoronamento com duas velas. Agora eu tenho opiniões sobre velas.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. Nobody wants to go below, and I've never actually asked anybody, which is different.
    >>  ............................................
    pt  Um segundo par de mãos. Ninguém quer descer, e eu nunca perguntei a ninguém, o que é diferente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.miner.more/1
    en  One! Four hours behind a fall with two candles. I have opinions about candles now.
    >>  ............................................
    pt  Uma! Quatro horas atrás de um desmoronamento com duas velas. Agora eu tenho opiniões sobre velas.
    >>  ............................................
  playful.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. Nobody wants to go below, and I've never actually asked anybody, which is different.
    >>  ............................................
    pt  Um segundo par de mãos. Ninguém quer descer, e eu nunca perguntei a ninguém, o que é diferente.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours in the dark. You learn a great deal about yourself and most of it keeps.
    >>  ............................................
    pt  Uma. Quatro horas no escuro. Você aprende muito sobre si e quase tudo se conserva.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands, in time. The seam east of the drift will wait; it has waited this long.
    >>  ............................................
    pt  Um segundo par de mãos, com o tempo. O veio a leste espera; já esperou até agora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours behind a fall. My father was on the other side of it and he did not come out.
    >>  ............................................
    pt  Uma. Quatro horas atrás de um desmoronamento. Meu pai estava do outro lado e não saiu.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. There's a name on the drift wall and I pass it going in every day.
    >>  ............................................
    pt  Um segundo par de mãos. Tem um nome na parede da galeria e eu passo por ele na entrada todo dia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.miner.more/1
    en  One. Four hours, two candles, and I did not use the second until the third hour.
    >>  ............................................
    pt  Uma. Quatro horas, duas velas, e eu não usei a segunda até a terceira hora.
    >>  ............................................
  shy.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. There's a seam east of the old drift I've never opened, and I'm saving it.
    >>  ............................................
    pt  Um segundo par de mãos. Tem um veio a leste da galeria velha que eu nunca abri, e estou guardando.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.miner.more/1
    en  One! Four hours behind a fall with two candles. I have opinions about candles now.
    >>  ............................................
    pt  Uma! Quatro horas atrás de um desmoronamento com duas velas. Agora eu tenho opiniões sobre velas.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. Nobody wants to go below, and I've never actually asked anybody, which is different.
    >>  ............................................
    pt  Um segundo par de mãos. Ninguém quer descer, e eu nunca perguntei a ninguém, o que é diferente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.miner.more/1
    en  One! Four hours behind a fall with two candles. I have opinions about candles now.
    >>  ............................................
    pt  Uma! Quatro horas atrás de um desmoronamento com duas velas. Agora eu tenho opiniões sobre velas.
    >>  ............................................
  witty.dialogue.conversations.work.prof.miner.more/2
    en  A second pair of hands. Nobody wants to go below, and I've never actually asked anybody, which is different.
    >>  ............................................
    pt  Um segundo par de mãos. Ninguém quer descer, e eu nunca perguntei a ninguém, o que é diferente.
    >>  ............................................
```

</details>


### Button `leave` — "Solid roof."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.challenge.landed`, `work.miner.challenge.stung`, `work.miner.craft.admire`, `work.miner.craft.ask_lying`, `work.miner.craft.ask_words`, `work.miner.future.ask_inscription`, `work.miner.future.ask_saving`, `work.miner.future.encourage`, `work.miner.hard`, `work.miner.risk.ask_fall`, `work.miner.risk.ask_loan`, `work.miner.risk.sympathise`, `work.miner.task.ask_dull`, `work.miner.task.ask_props`, `work.miner.task.offer_hands`, `work.miner.value`, `work.miner.village.ask_ladder`, `work.miner.village.ask_smith`, `work.miner.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.followup.leave   [11 chars]
    en  Solid roof.
    >>  ............................................
    pt  Teto firme.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "Solid roof."
       spoken on: conversations.topic.work.miner.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner.future` — e.g. "A second pair of hands below, so that a fall is a rescue instead of an inscription."


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.future.respond   [25 chars]
    en  That's what's left below.
    >>  ............................................
    pt  É o que resta lá embaixo.
    >>  ............................................
```


### Button `ask_saving` — "Saving it for what?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.future` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.future.ask_saving` — accepted phrasings: "saving it for what"
  - the message must contain one of: `saving`, `seam`, `east`
  - scored words: `saving`(1.5), `seam`(1.2), `east`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.future.respond.ask_saving
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.future.respond.ask_saving   [19 chars]
    en  Saving it for what?
    >>  ............................................
    pt  Guardando pra quê?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.future.ask_saving`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.future.ask_saving
WHO    VILLAGER — what the player reads after pressing "Saving it for what?"
       spoken on: conversations.topic.work.miner.future.respond, button `ask_saving`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.future.ask_saving`: the villager explains. Subject `work.miner.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.future.ask_saving/1   [84 chars]
    en  For whoever comes after me. It's the only inheritance I've got and it's underground.
    >>  ............................................
    pt  Pra quem vier depois de mim. É a única herança que eu tenho e está debaixo da terra.
    >>  ............................................
  dialogue.conversations.work.prof.miner.future.ask_saving/2   [73 chars]
    en  For a year I need it, %1$s. Every miner keeps one and none of us says so.
    >>  ............................................
    pt  Pra um ano em que eu precise, %1$s. Todo mineiro guarda um e nenhum de nós admite.
    >>  ............................................
```


### Button `encourage` — "Find the second pair of hands first."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.future` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.future.encourage` — accepted phrasings: "find the second pair of hands first"
  - the message must contain one of: `hands`, `second`
  - scored words: `hands`(1.5), `second`(1.2), `first`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.future.respond.encourage   [36 chars]
    en  Find the second pair of hands first.
    >>  ............................................
    pt  Ache o segundo par de mãos primeiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.miner.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.miner.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.future.encourage
WHO    VILLAGER — what the player reads after pressing "Find the second pair of hands first."
       spoken on: conversations.topic.work.miner.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.future.encourage`: the villager accepts. Subject `work.miner.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.future.encourage/1   [83 chars]
    en  ...First. Aye. I've been treating the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. Sim. Venho tratando o veio como meta e as mãos como luxo.
    >>  ............................................
  dialogue.conversations.work.prof.miner.future.encourage/2   [88 chars]
    en  Nobody wants to go below, %1$s. But I've not actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer, %1$s. Mas eu não perguntei a ninguém de fato, o que é diferente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. I've called the hands a luxury because asking for them felt like weakness.
    >>  ............................................
    pt  ...Primeiro. Chamei as mãos de luxo porque pedir parecia fraqueza.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've not asked, which means I've decided their answer for them.
    >>  ............................................
    pt  Ninguém quer descer. Eu não perguntei, o que é decidir a resposta por eles.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Twenty years of treating the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. Vinte anos tratando a veia como objetivo e as mãos como luxo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've buried two who went alone; that ought to have taught me.
    >>  ............................................
    pt  Ninguém quer descer. Enterrei dois que desceram sozinhos; isso devia ter me ensinado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Aye. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. É. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. But I've not actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer. Mas eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Aye. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. É. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. But I've not actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer. Mas eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First, %1$s. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro, %1$s. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. Except I've never asked, which you spotted before I did.
    >>  ............................................
    pt  Ninguém quer descer. Só que eu nunca perguntei, e você viu isso antes de mim.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First, %1$s. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro, %1$s. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. Except I've never asked, which you spotted before I did.
    >>  ............................................
    pt  Ninguém quer descer. Só que eu nunca perguntei, e você viu isso antes de mim.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First, %1$s. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro, %1$s. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. Except I've never asked, which you spotted before I did.
    >>  ............................................
    pt  Ninguém quer descer. Só que eu nunca perguntei, e você viu isso antes de mim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. I've called the hands a luxury because asking for them felt like weakness.
    >>  ............................................
    pt  ...Primeiro. Chamei as mãos de luxo porque pedir parecia fraqueza.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've not asked, which means I've decided their answer for them.
    >>  ............................................
    pt  Ninguém quer descer. Eu não perguntei, o que é decidir a resposta por eles.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Aye. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. É. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. But I've not actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer. Mas eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Aye. I've treated the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. É. Tratei a veia como objetivo e as mãos como luxo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. But I've not actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer. Mas eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. The hands, not the seam.
    >>  ............................................
    pt  ...Primeiro. As mãos, não a veia.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've not asked.
    >>  ............................................
    pt  Ninguém quer descer. Eu não perguntei.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Twenty years of treating the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. Vinte anos tratando a veia como objetivo e as mãos como luxo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've buried two who went alone; that ought to have taught me.
    >>  ............................................
    pt  Ninguém quer descer. Enterrei dois que desceram sozinhos; isso devia ter me ensinado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. The hands, not the seam.
    >>  ............................................
    pt  ...Primeiro. As mãos, não a veia.
    >>  ............................................
  odd.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've not asked.
    >>  ............................................
    pt  Ninguém quer descer. Eu não perguntei.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Twenty years of treating the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. Vinte anos tratando a veia como objetivo e as mãos como luxo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've buried two who went alone; that ought to have taught me.
    >>  ............................................
    pt  Ninguém quer descer. Enterrei dois que desceram sozinhos; isso devia ter me ensinado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First! Right. I've had the seam as the goal and the hands as some sort of luxury.
    >>  ............................................
    pt  ...Primeiro! Certo. Tratei a veia como objetivo e as mãos como algum tipo de luxo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below — except I haven't actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer — só que eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First! Right. I've had the seam as the goal and the hands as some sort of luxury.
    >>  ............................................
    pt  ...Primeiro! Certo. Tratei a veia como objetivo e as mãos como algum tipo de luxo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below — except I haven't actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer — só que eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. Twenty years of treating the seam as the goal and the hands as the luxury.
    >>  ............................................
    pt  ...Primeiro. Vinte anos tratando a veia como objetivo e as mãos como luxo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've buried two who went alone; that ought to have taught me.
    >>  ............................................
    pt  Ninguém quer descer. Enterrei dois que desceram sozinhos; isso devia ter me ensinado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. I've called the hands a luxury because asking for them felt like weakness.
    >>  ............................................
    pt  ...Primeiro. Chamei as mãos de luxo porque pedir parecia fraqueza.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've not asked, which means I've decided their answer for them.
    >>  ............................................
    pt  Ninguém quer descer. Eu não perguntei, o que é decidir a resposta por eles.
    >>  ............................................
  shy.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First. The hands, not the seam.
    >>  ............................................
    pt  ...Primeiro. As mãos, não a veia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below. I've not asked.
    >>  ............................................
    pt  Ninguém quer descer. Eu não perguntei.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First! Right. I've had the seam as the goal and the hands as some sort of luxury.
    >>  ............................................
    pt  ...Primeiro! Certo. Tratei a veia como objetivo e as mãos como algum tipo de luxo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below — except I haven't actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer — só que eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.miner.future.encourage/1
    en  ...First! Right. I've had the seam as the goal and the hands as some sort of luxury.
    >>  ............................................
    pt  ...Primeiro! Certo. Tratei a veia como objetivo e as mãos como algum tipo de luxo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.miner.future.encourage/2
    en  Nobody wants to go below — except I haven't actually asked anybody, which is different.
    >>  ............................................
    pt  Ninguém quer descer — só que eu não perguntei a ninguém, o que é diferente.
    >>  ............................................
```

</details>


### Button `ask_inscription` — "Inscription?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.future` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.future.ask_inscription` — accepted phrasings: "inscription"
  - the message must contain one of: `inscription`, `name`, `stone`
  - scored words: `inscription`(1.5), `name`(1.0), `stone`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.future.respond.ask_inscription
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.future.respond.ask_inscription   [12 chars]
    en  Inscription?
    >>  ............................................
    pt  Inscrição?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.future.ask_inscription`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.future.ask_inscription
WHO    VILLAGER — what the player reads after pressing "Inscription?"
       spoken on: conversations.topic.work.miner.future.respond, button `ask_inscription`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.future.ask_inscription`: the villager explains. Subject `work.miner.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.future.ask_inscription/1   [77 chars]
    en  The mason cuts a name. That's what a fall is when there's one man down there.
    >>  ............................................
    pt  O pedreiro corta um nome. É o que um desmoronamento é quando tem um homem só lá.
    >>  ............................................
  dialogue.conversations.work.prof.miner.future.ask_inscription/2   [95 chars]
    en  There's one already, on the drift wall, %1$s. I pass it going in and I don't pass it going out.
    >>  ............................................
    pt  Já tem uma, na parede da galeria, %1$s. Passo por ela na entrada e não passo na saída.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.future.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você descer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.topic.work.miner.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner` — e.g. "Down in the dark you learn what you're made of. Mostly I'm made of dust and stubbornness."


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.respond   [48 chars]
    en  That's the dark and my running score against it.
    >>  ............................................
    pt  É o escuro e o placar da minha disputa com ele.
    >>  ............................................
```


### Button `ask_hard` — "What do you listen for down there?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.identity` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.hard` — accepted phrasings: "what do you listen for down there"
  - the message must contain one of: `listen`, `sounds`, `timber`
  - scored words: `listen`(1.5), `sounds`(1.5), `timber`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.respond.ask_hard   [34 chars]
    en  What do you listen for down there?
    >>  ............................................
    pt  O que você escuta lá embaixo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.miner.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.hard
WHO    VILLAGER — what the player reads after pressing "What do you listen for down there?"
       spoken on: conversations.topic.work.miner.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.hard`: the villager explains. Subject `work.miner.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.followup / ask_more
```

> Written out in full under **`conversations.scene.work.miner.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Every smith here is waiting on you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.identity` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.value` — accepted phrasings: "every smith here is waiting on you"
  - the message must contain one of: `smith`, `ore`
  - scored words: `smith`(1.5), `ore`(1.5), `waiting`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.respond.value   [35 chars]
    en  Every smith here is waiting on you.
    >>  ............................................
    pt  Todo ferreiro daqui está esperando por você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.miner.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.miner.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.value
WHO    VILLAGER — what the player reads after pressing "Every smith here is waiting on you."
       spoken on: conversations.topic.work.miner.respond, button `value`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.value`: the villager accepts. Subject `work.miner.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.value/1   [75 chars]
    en  They are, and they'll grumble about the grade regardless. It's traditional.
    >>  ............................................
    pt  Estão, e vão reclamar do teor de qualquer jeito. É tradicional.
    >>  ............................................
  dialogue.conversations.work.prof.miner.value/2   [71 chars]
    en  Aye. Nothing above ground moves until something comes up from below it.
    >>  ............................................
    pt  É. Nada na superfície se move até algo subir de baixo dela.
    >>  ............................................
```


### Button `challenge` — "You just swing at rocks in the dark."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.miner.identity` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.challenge` — accepted phrasings: "you just swing at rocks in the dark"
  - the message must contain one of: `swing`, `rocks`
  - scored words: `swing`(1.5), `rocks`(1.2), `dark`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.respond.challenge   [36 chars]
    en  You just swing at rocks in the dark.
    >>  ............................................
    pt  Você só bate em pedra no escuro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.miner.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.miner.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You just swing at rocks in the dark."
       spoken on: conversations.topic.work.miner.respond, button `challenge`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.challenge.landed`: the villager resists. Subject `work.miner.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.challenge.landed/1   [85 chars]
    en  I do. Knowing which rock and which dark is the difference between a seam and a grave.
    >>  ............................................
    pt  Bato. Saber qual pedra e qual escuro é a diferença entre um veio e uma cova.
    >>  ............................................
  dialogue.conversations.work.prof.miner.challenge.landed/2   [64 chars]
    en  Swing at rocks. Aye. Come down and swing at the wrong one, %1$s.
    >>  ............................................
    pt  Bater em pedra. É. Desça e bata na errada, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.miner.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.miner.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You just swing at rocks in the dark."
       spoken on: conversations.topic.work.miner.respond, button `challenge`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.challenge.stung`: the villager resists. Subject `work.miner.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.challenge.stung/1   [70 chars]
    en  ...I've held a roof up with my shoulder. Tell me again what I just do.
    >>  ............................................
    pt  ...Já segurei um teto com o ombro. Repita o que é que eu só faço.
    >>  ............................................
  dialogue.conversations.work.prof.miner.challenge.stung/2   [58 chars]
    en  In the dark. Right. And you've never once thanked a torch.
    >>  ............................................
    pt  No escuro. Certo. E você nunca agradeceu uma tocha.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você descer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.topic.work.miner.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner.risk` — e.g. "There was a fall in the old drift nine years ago. Two of us went down and one of us walked out."


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.risk.respond   [29 chars]
    en  That's what it is down there.
    >>  ............................................
    pt  É isso que é lá embaixo.
    >>  ............................................
```


### Button `ask_fall` — "Who was the other one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.risk` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.risk.ask_fall` — accepted phrasings: "who was the other one"
  - the message must contain one of: `other`, `fall`
  - scored words: `other`(1.0), `fall`(1.5), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.risk.respond.ask_fall
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.risk.respond.ask_fall   [22 chars]
    en  Who was the other one?
    >>  ............................................
    pt  Quem era o outro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.risk.ask_fall`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.risk.ask_fall
WHO    VILLAGER — what the player reads after pressing "Who was the other one?"
       spoken on: conversations.topic.work.miner.risk.respond, button `ask_fall`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.risk.ask_fall`: the villager explains. Subject `work.miner.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.risk.ask_fall/1   [100 chars]
    en  My father. I set the props that morning and there were three of them, and that is the whole account.
    >>  ............................................
    pt  Meu pai. Eu pus as escoras naquela manhã e eram três, e é todo o relato.
    >>  ............................................
  dialogue.conversations.work.prof.miner.risk.ask_fall/2   [85 chars]
    en  A man I'd known for nineteen years. I'll not say his name standing in daylight, %1$s.
    >>  ............................................
    pt  Um homem que eu conhecia há dezenove anos. Não digo o nome dele à luz do dia, %1$s.
    >>  ............................................
```


### Button `sympathise` — "And you set four props every day since."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.risk` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.risk.sympathise` — accepted phrasings: "and you set four props every day since"
  - the message must contain one of: `props`, `since`
  - scored words: `props`(1.2), `since`(1.5), `every`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.risk.respond.sympathise   [39 chars]
    en  And you set four props every day since.
    >>  ............................................
    pt  E você põe quatro escoras todo dia desde então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.miner.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.miner.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "And you set four props every day since."
       spoken on: conversations.topic.work.miner.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.risk.sympathise`: the villager accepts. Subject `work.miner.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.risk.sympathise/1   [82 chars]
    en  ...Every day. You're the first to put those two facts next to each other out loud.
    >>  ............................................
    pt  ...Todo dia. Você é o primeiro a pôr esses dois fatos lado a lado em voz alta.
    >>  ............................................
  dialogue.conversations.work.prof.miner.risk.sympathise/2   [88 chars]
    en  Four, and a fifth when it rains. Nobody's asked why and I've never volunteered it, %1$s.
    >>  ............................................
    pt  Quatro, e uma quinta quando chove. Ninguém perguntou por quê e eu nunca contei, %1$s.
    >>  ............................................
```


### Button `ask_loan` — "What do you mean, on loan?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.risk` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.risk.ask_loan` — accepted phrasings: "what do you mean, on loan"
  - the message must contain one of: `loan`, `borrowed`
  - scored words: `loan`(1.5), `mean`(0.6), `borrowed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.risk.respond.ask_loan
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.risk.respond.ask_loan   [26 chars]
    en  What do you mean, on loan?
    >>  ............................................
    pt  Como assim, emprestado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.risk.ask_loan`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.risk.ask_loan
WHO    VILLAGER — what the player reads after pressing "What do you mean, on loan?"
       spoken on: conversations.topic.work.miner.risk.respond, button `ask_loan`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.risk.ask_loan`: the villager explains. Subject `work.miner.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.risk.ask_loan/1   [80 chars]
    en  The rock was there first and it will be there after. You're a guest with a lamp.
    >>  ............................................
    pt  A rocha estava lá antes e vai estar depois. Você é um hóspede com uma lamparina.
    >>  ............................................
  dialogue.conversations.work.prof.miner.risk.ask_loan/2   [99 chars]
    en  It means nothing down there is yours, %1$s, including the air, and the air is the one that matters.
    >>  ............................................
    pt  Significa que nada lá é seu, %1$s, incluindo o ar, e o ar é o que importa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.risk.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você descer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.topic.work.miner.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner.task` — e.g. "Timbering the new drift. Four props today, and I'd rather set four than explain why I set three."


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.task.respond   [19 chars]
    en  That's below today.
    >>  ............................................
    pt  É lá embaixo hoje.
    >>  ............................................
```


### Button `ask_props` — "How do you know four is enough?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.task` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.task.ask_props` — accepted phrasings: "how do you know four is enough"
  - the message must contain one of: `props`, `four`, `enough`
  - scored words: `props`(1.5), `four`(1.0), `enough`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.task.respond.ask_props
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.task.respond.ask_props   [31 chars]
    en  How do you know four is enough?
    >>  ............................................
    pt  Como você sabe que quatro bastam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.task.ask_props`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.task.ask_props
WHO    VILLAGER — what the player reads after pressing "How do you know four is enough?"
       spoken on: conversations.topic.work.miner.task.respond, button `ask_props`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.task.ask_props`: the villager explains. Subject `work.miner.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.task.ask_props/1   [90 chars]
    en  I don't. I know three isn't, because I've heard three not be, from the far side of a fall.
    >>  ............................................
    pt  Não sei. Sei que três não bastam, porque eu ouvi três não bastarem, do outro lado de um desmoronamento.
    >>  ............................................
  dialogue.conversations.work.prof.miner.task.ask_props/2   [84 chars]
    en  You listen to the roof for a week before you decide, %1$s. It talks if you're quiet.
    >>  ............................................
    pt  Você escuta o teto por uma semana antes de decidir, %1$s. Ele fala se você ficar quieto.
    >>  ............................................
```


### Button `offer_hands` — "I can haul for you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.miner.task` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.task.offer_hands` — accepted phrasings: "i can haul for you"
  - the message must contain one of: `haul`, `baskets`, `carry`
  - scored words: `haul`(1.5), `baskets`(1.2), `carry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.task.respond.offer_hands   [19 chars]
    en  I can haul for you.
    >>  ............................................
    pt  Eu posso puxar o material.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.miner.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.miner.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can haul for you."
       spoken on: conversations.topic.work.miner.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.task.offer_hands`: the villager accepts. Subject `work.miner.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.task.offer_hands/1   [82 chars]
    en  ...You can. Full baskets out, empty ones in, and never both at once on the ladder.
    >>  ............................................
    pt  ...Pode. Cestos cheios pra fora, vazios pra dentro, e nunca os dois na escada.
    >>  ............................................
  dialogue.conversations.work.prof.miner.task.offer_hands/2   [79 chars]
    en  Then you'll find out what my shoulders know, %1$s. Take the small basket first.
    >>  ............................................
    pt  Então você vai descobrir o que meus ombros sabem, %1$s. Pegue o cesto pequeno primeiro.
    >>  ............................................
```


### Button `ask_dull` — "Is the hauling really the deciding half?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.task` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.task.ask_dull` — accepted phrasings: "is the hauling really the deciding half"
  - the message must contain one of: `hauling`, `deciding`, `dull`
  - scored words: `hauling`(1.5), `deciding`(1.2), `dull`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.task.respond.ask_dull
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.task.respond.ask_dull   [40 chars]
    en  Is the hauling really the deciding half?
    >>  ............................................
    pt  Puxar é mesmo a metade decisiva?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.task.ask_dull`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.task.ask_dull
WHO    VILLAGER — what the player reads after pressing "Is the hauling really the deciding half?"
       spoken on: conversations.topic.work.miner.task.respond, button `ask_dull`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.task.ask_dull`: the villager explains. Subject `work.miner.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.task.ask_dull/1   [93 chars]
    en  Anyone can find a seam. Getting it up a ladder for six months is where the money actually is.
    >>  ............................................
    pt  Qualquer um acha um veio. Subir ele por seis meses é onde o dinheiro está de verdade.
    >>  ............................................
  dialogue.conversations.work.prof.miner.task.ask_dull/2   [91 chars]
    en  Two men here have found good seams and abandoned them, %1$s. Both times it was the hauling.
    >>  ............................................
    pt  Dois homens aqui acharam bons veios e abandonaram, %1$s. Nas duas vezes foi o transporte.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.task.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você descer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.topic.work.miner.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.miner.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.miner.village` — e.g. "Every nail in this place, every hinge, every pot. It all came up a ladder on somebody's back."


```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.miner.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.miner.village.respond   [36 chars]
    en  That's the whole of my contribution.
    >>  ............................................
    pt  É toda a minha contribuição.
    >>  ............................................
```


### Button `ask_smith` — "Would you like him to ask?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.village` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.village.ask_smith` — accepted phrasings: "would you like him to ask"
  - the message must contain one of: `smith`, `acknowledge`
  - scored words: `smith`(1.5), `ask`(0.8), `acknowledge`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.village.respond.ask_smith
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.village.respond.ask_smith   [26 chars]
    en  Would you like him to ask?
    >>  ............................................
    pt  Você gostaria que ele perguntasse?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.village.ask_smith`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.village.ask_smith
WHO    VILLAGER — what the player reads after pressing "Would you like him to ask?"
       spoken on: conversations.topic.work.miner.village.respond, button `ask_smith`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.village.ask_smith`: the villager explains. Subject `work.miner.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.village.ask_smith/1   [81 chars]
    en  ...I've said I don't mind and I've said it twice now, which probably answers you.
    >>  ............................................
    pt  ...Eu disse que não me importo e já disse duas vezes, o que provavelmente te responde.
    >>  ............................................
  dialogue.conversations.work.prof.miner.village.ask_smith/2   [94 chars]
    en  He knows. He brings me the first hot thing out of the forge every winter, %1$s. That's asking.
    >>  ............................................
    pt  Ele sabe. Ele me traz a primeira coisa quente da forja todo inverno, %1$s. Isso é perguntar.
    >>  ............................................
```


### Button `say_thanks` — "Somebody's back carried all of it. That's worth saying."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.miner.village` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.village.say_thanks` — accepted phrasings: "somebody's back carried all of it. that's worth saying"
  - the message must contain one of: `carried`, `back`, `underneath`
  - scored words: `carried`(1.5), `back`(1.0), `underneath`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.village.respond.say_thanks   [55 chars]
    en  Somebody's back carried all of it. That's worth saying.
    >>  ............................................
    pt  As costas de alguém carregaram tudo. Vale dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.miner.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.miner.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Somebody's back carried all of it. That's worth saying."
       spoken on: conversations.topic.work.miner.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.village.say_thanks`: the villager accepts. Subject `work.miner.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.village.say_thanks/1   [93 chars]
    en  ...It is. And it's mine, and my father's, and one other man's, and I'd rather name all three.
    >>  ............................................
    pt  ...Vale. E são minhas, e do meu pai, e de mais um homem, e eu prefiro nomear os três.
    >>  ............................................
  dialogue.conversations.work.prof.miner.village.say_thanks/2   [82 chars]
    en  Nobody says it because it's underneath everything, %1$s. Underneath is where I am.
    >>  ............................................
    pt  Ninguém diz porque está embaixo de tudo, %1$s. Embaixo é onde eu estou.
    >>  ............................................
```


### Button `ask_ladder` — "How much comes up in a year?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.miner.village` · offered only once the villager has actually said `work:miner`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.miner.village.ask_ladder` — accepted phrasings: "how much comes up in a year"
  - the message must contain one of: `baskets`
  - scored words: `baskets`(1.5), `year`(0.8), `much`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.village.respond.ask_ladder
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.village.respond.ask_ladder   [28 chars]
    en  How much comes up in a year?
    >>  ............................................
    pt  Quanto sobe num ano?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.miner.village.ask_ladder`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.miner.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you had a close call?" | "Solid roof."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.village.ask_ladder
WHO    VILLAGER — what the player reads after pressing "How much comes up in a year?"
       spoken on: conversations.topic.work.miner.village.respond, button `ask_ladder`
       leaves the player on: conversations.topic.work.miner.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.village.ask_ladder`: the villager explains. Subject `work.miner.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.miner.village.ask_ladder/1   [83 chars]
    en  Nine hundred baskets, near enough. I count them, because if I didn't, nobody would.
    >>  ............................................
    pt  Umas novecentas cestas. Eu conto, porque se eu não contasse, ninguém contaria.
    >>  ............................................
  dialogue.conversations.work.prof.miner.village.ask_ladder/2   [91 chars]
    en  Enough for a village and not enough for a market, %1$s. That's why it's just me down there.
    >>  ............................................
    pt  O bastante pra um vilarejo e pouco pra um mercado, %1$s. Por isso sou só eu lá embaixo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back down."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.miner.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.miner.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.miner.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.miner.village.respond.leave   [27 chars]
    en  I'll let you get back down.
    >>  ............................................
    pt  Vou deixar você descer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back down."
       spoken on: conversations.topic.work.miner.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.left`: the villager accepts. Subject `work.miner.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.miner.exhausted_seam.blocked.respond / leave; conversations.scene.work.miner.exhausted_seam.succeeded.respond / leave; conversations.scene.work.miner.failing_props.blocked.respond / leave; conversations.scene.work.miner.failing_props.succeeded.respond / leave; conversations.scene.work.miner.followup / leave; conversations.scene.work.miner.the_day_it_moved.succeeded.respond / leave; conversations.topic.work.miner.craft.respond / leave; conversations.topic.work.miner.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.miner.exhausted_seam.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

