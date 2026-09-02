# Work talk with a armorer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.armorer.bad_fit.active.respond`](#conversations-scene-work-armorer-bad-fit-active-respond)
- [`conversations.scene.work.armorer.bad_fit.succeeded.respond`](#conversations-scene-work-armorer-bad-fit-succeeded-respond)
- [`conversations.scene.work.armorer.burn.succeeded.respond`](#conversations-scene-work-armorer-burn-succeeded-respond)
- [`conversations.scene.work.armorer.followup`](#conversations-scene-work-armorer-followup)
- [`conversations.scene.work.armorer.stalled_commission.blocked.respond`](#conversations-scene-work-armorer-stalled-commission-blocked-respond)
- [`conversations.scene.work.armorer.stalled_commission.succeeded.respond`](#conversations-scene-work-armorer-stalled-commission-succeeded-respond)
- [`conversations.topic.work.armorer.craft.respond`](#conversations-topic-work-armorer-craft-respond)
- [`conversations.topic.work.armorer.followup`](#conversations-topic-work-armorer-followup)
- [`conversations.topic.work.armorer.future.respond`](#conversations-topic-work-armorer-future-respond)
- [`conversations.topic.work.armorer.respond`](#conversations-topic-work-armorer-respond)
- [`conversations.topic.work.armorer.risk.respond`](#conversations-topic-work-armorer-risk-respond)
- [`conversations.topic.work.armorer.task.respond`](#conversations-topic-work-armorer-task-respond)
- [`conversations.topic.work.armorer.village.respond`](#conversations-topic-work-armorer-village-respond)

---

## `conversations.scene.work.armorer.bad_fit.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.armorer.bad_fit.active` — e.g. "%2$s sits wrong on %3$s and they will not come back for a second fitting, and I cannot fix a body I cannot see."


```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.armorer.bad_fit.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active.respond   [12 chars]
    en  The fitting.
    >>  ............................................
    pt  A prova.
    >>  ............................................
```


### Button `ask_why_they_wont` — "What keeps them from returning?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.bad_fit.active` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.bad_fit.active.ask_why_they_wont` — accepted phrasings: "what keeps them from returning"; "what stops the second fitting"; "what keeps them from returning"
  - the message must contain one of: `returning`, `keeps`, `fitting`
  - scored words: `returning`(1.8), `keeps`(1.8), `fitting`(1.8), `from`(0.8), `stops`(0.8), `second`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active.respond.ask_why_they_wont
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.bad_fit.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active.respond.ask_why_they_wont   [31 chars]
    en  What keeps them from returning?
    >>  ............................................
    pt  O que os impede de voltar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.fitting`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.armorer.bad_fit"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active.explained
WHO    VILLAGER — what the player reads after pressing "What keeps them from returning?"
       spoken on: conversations.scene.work.armorer.bad_fit.active.respond, button `ask_why_they_wont`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.bad_fit.active.explained`: the villager explains. Subject `work.armorer.fitting`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active.explained/1   [138 chars]
    en  Because standing still while somebody measures you feels like being judged, and %2$s would rather wear a bad helm than be looked at twice.
    >>  ............................................
    pt  Porque ficar parado enquanto alguém te mede parece julgamento, e %2$s prefere usar um elmo ruim a ser olhado duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.active.explained/2   [115 chars]
    en  Time, they say. It is not time. A fitting is ten minutes. It is that being fitted makes a person feel like a thing.
    >>  ............................................
    pt  Falta de tempo, dizem. Não é tempo. Uma prova leva dez minutos. É que ser medido faz a pessoa se sentir um objeto.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.active.explained/3   [141 chars]
    en  %2$s thinks asking for a second fitting is admitting they were difficult the first time. I have stopped arguing and started measuring faster.
    >>  ............................................
    pt  %2$s acha que pedir uma segunda prova é admitir que foi difícil na primeira. Parei de discutir e passei a medir mais rápido.
    >>  ............................................
```


### Button `advise_go_to_them` — "Then take the piece to them."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.bad_fit.active` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.bad_fit.active.advise_go_to_them` — accepted phrasings: "then take the piece to them"; "then take the piece to them"; "carry it over to their house"
  - the message must contain one of: `take`, `carry`, `house`
  - scored words: `take`(1.8), `carry`(1.8), `house`(1.8), `piece`(0.8), `over`(0.8), `their`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active.respond.advise_go_to_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.bad_fit.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active.respond.advise_go_to_them   [28 chars]
    en  Then take the piece to them.
    >>  ............................................
    pt  Então leve a peça até eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.armorer.fitting`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.armorer.bad_fit"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active.accepted
WHO    VILLAGER — what the player reads after pressing "Then take the piece to them."
       spoken on: conversations.scene.work.armorer.bad_fit.active.respond, button `advise_go_to_them`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.bad_fit.active.accepted`: the villager accepts. Subject `work.armorer.fitting`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active.accepted/1   [105 chars]
    en  That had genuinely not occurred to me, which is embarrassing, because the forge is not where the body is.
    >>  ............................................
    pt  Isso genuinamente não tinha me ocorrido, o que é vergonhoso, porque a forja não é onde o corpo está.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.active.accepted/2   [123 chars]
    en  Yes. I will go at supper when they are sitting down anyway, and the whole thing becomes a visit rather than an appointment.
    >>  ............................................
    pt  Sim. Vou na hora do jantar, quando já estão sentados, e a coisa toda vira visita em vez de compromisso.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.active.accepted/3   [111 chars]
    en  It costs me an hour and saves them their pride, and I have been charging them pride for years without noticing.
    >>  ............................................
    pt  Me custa uma hora e poupa o orgulho deles, e faz anos que eu cobro esse orgulho sem perceber.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · answers the beat(s) `work.armorer.bad_fit.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.bad_fit.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.scene.work.armorer.bad_fit.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.armorer.leave/1   [49 chars]
    en  It's gone cold twice while we talked. Off you go.
    >>  ............................................
    pt  Já esfriou duas vezes enquanto a gente falava. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.leave/2   [58 chars]
    en  Mind the tongs, %1$s. They stay hot longer than they look.
    >>  ............................................
    pt  Cuidado com a tenaz, %1$s. Ela fica quente mais tempo do que parece.
    >>  ............................................
```

---


## `conversations.scene.work.armorer.bad_fit.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.armorer.bad_fit.succeeded` — e.g. "I took %2$s to their door and had it right in twenty minutes, standing in their kitchen."


```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.armorer.bad_fit.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded.respond   [13 chars]
    en  That fitting.
    >>  ............................................
    pt  Aquela prova.
    >>  ............................................
```


### Button `note_the_change` — "That's a better way of working."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.armorer.bad_fit.succeeded` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.bad_fit.succeeded.note_the_change` — accepted phrasings: "thats a better way of working"; "that is a better way of working"; "going to them works better"
  - the message must contain one of: `better`, `working`, `works`
  - scored words: `better`(1.8), `working`(1.8), `works`(1.8), `thats`(0.8), `way`(0.8), `going`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.succeeded.respond.note_the_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.bad_fit.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded.respond.note_the_change   [31 chars]
    en  That's a better way of working.
    >>  ............................................
    pt  É um jeito melhor de trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.armorer.fitting`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.armorer.bad_fit"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "That's a better way of working."
       spoken on: conversations.scene.work.armorer.bad_fit.succeeded.respond, button `note_the_change`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.bad_fit.succeeded.acknowledged`: the villager accepts. Subject `work.armorer.fitting`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded.acknowledged/1   [100 chars]
    en  It is, and it took somebody outside the trade to say it, which I will be thinking about for a while.
    >>  ............................................
    pt  É, e precisou de alguém de fora do ofício para dizer, o que vou ficar remoendo por um tempo.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded.acknowledged/2   [107 chars]
    en  Thank you. I had built the whole habit around my own convenience and then called it professional standards.
    >>  ............................................
    pt  Obrigada. Eu tinha construído o hábito inteiro em torno da minha conveniência e chamado isso de padrão profissional.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded.acknowledged/3   [95 chars]
    en  Better and slower. I have decided I can afford slower, now that I am not remaking things twice.
    >>  ............................................
    pt  Melhor e mais devagar. Decidi que posso pagar por mais devagar, agora que não refaço as coisas duas vezes.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · answers the beat(s) `work.armorer.bad_fit.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.bad_fit.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.scene.work.armorer.bad_fit.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.armorer.burn.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.armorer.burn.succeeded` — e.g. "%2$s. Six years ago, and it still tells me when the weather is turning, which is more use than most scars."


```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.burn.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.armorer.burn.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.armorer.burn.succeeded.respond   [9 chars]
    en  Your arm.
    >>  ............................................
    pt  Seu braço.
    >>  ............................................
```


### Button `ask_if_it_hurts` — "Does it still trouble you?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.armorer.burn.succeeded` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.burn.succeeded.ask_if_it_hurts` — accepted phrasings: "does it still trouble you"; "does it still trouble you"; "does the old burn still ache"
  - the message must contain one of: `trouble`, `ache`, `burn`
  - scored words: `trouble`(1.8), `ache`(1.8), `burn`(1.8), `does`(0.8), `still`(0.8), `old`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.burn.succeeded.respond.ask_if_it_hurts
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.burn.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.burn.succeeded.respond.ask_if_it_hurts   [26 chars]
    en  Does it still trouble you?
    >>  ............................................
    pt  Ainda te incomoda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, familiarity +1  _(recorded under topic `work.armorer.heat`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.armorer.burn"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.burn.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Does it still trouble you?"
       spoken on: conversations.scene.work.armorer.burn.succeeded.respond, button `ask_if_it_hurts`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.burn.succeeded.answered`: the villager explains. Subject `work.armorer.heat`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.burn.succeeded.answered/1   [101 chars]
    en  Not really. It pulls in the cold and it will not tan, and otherwise it is just a part of the arm now.
    >>  ............................................
    pt  Nem tanto. Repuxa no frio e não bronzeia, e de resto agora é só parte do braço.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.burn.succeeded.answered/2   [90 chars]
    en  Only when somebody asks. Then I remember the smell, and that is the part that never faded.
    >>  ............................................
    pt  Só quando alguém pergunta. Aí eu lembro do cheiro, e essa é a parte que nunca desbotou.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.burn.succeeded.answered/3   [138 chars]
    en  It taught me to let things fall, which has been worth more than the arm ever cost me. I would rather not have paid for the lesson, though.
    >>  ............................................
    pt  Me ensinou a deixar as coisas caírem, o que valeu mais do que o braço me custou. Ainda assim, preferia não ter pago pela lição.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · answers the beat(s) `work.armorer.burn.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.burn.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.burn.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.burn.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.scene.work.armorer.burn.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.armorer.followup`

**Reached from 8 route(s):** `conversations.scene.work.armorer.bad_fit.active.respond` / `ask_why_they_wont`; `conversations.scene.work.armorer.bad_fit.active.respond` / `advise_go_to_them`; `conversations.scene.work.armorer.bad_fit.succeeded.respond` / `note_the_change`; `conversations.scene.work.armorer.burn.succeeded.respond` / `ask_if_it_hurts`; `conversations.scene.work.armorer.stalled_commission.blocked.respond` / `ask_the_cost`; `conversations.scene.work.armorer.stalled_commission.blocked.respond` / `offer_iron`; `conversations.scene.work.armorer.stalled_commission.blocked.respond` / `advise_telling_them`; `conversations.scene.work.armorer.stalled_commission.succeeded.respond` / `ask_what_makes_it_good`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.armorer.bad_fit.active.accepted` — e.g. "That had genuinely not occurred to me, which is embarrassing, because the forge is not where the body is."
- `conversations.scene.work.armorer.bad_fit.active.explained` — e.g. "Because standing still while somebody measures you feels like being judged, and %2$s would rather wear a bad helm than be looked at twice."
- `conversations.scene.work.armorer.bad_fit.succeeded.acknowledged` — e.g. "It is, and it took somebody outside the trade to say it, which I will be thinking about for a while."
- `conversations.scene.work.armorer.burn.succeeded.answered` — e.g. "Not really. It pulls in the cold and it will not tan, and otherwise it is just a part of the arm now."
- `conversations.scene.work.armorer.stalled_commission.blocked.accepted` — e.g. "Then %2$s is finished by Thursday and I get to be the woman who keeps promises again."
- `conversations.scene.work.armorer.stalled_commission.blocked.conceded` — e.g. "I know. Early bad news is a courtesy and late bad news is an insult, and I have been sitting on it for three days."
- `conversations.scene.work.armorer.stalled_commission.blocked.explained` — e.g. "Two days of reheating to get %2$s back where it was, and a customer who has decided I am slow. The second one lasts longer."
- `conversations.scene.work.armorer.stalled_commission.succeeded.explained` — e.g. "They forget they are wearing it. That is the only test. If they keep adjusting it as they walk, I have failed and they are being polite."


```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.armorer.followup   [10 chars]
    en  What else?
    >>  ............................................
    pt  Que mais?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of fitting a helm?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.armorer.*` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.followup.ask_more` — accepted phrasings: "whats the hardest part of fitting a helm"; "what is the hardest part of fitting a helm"; "hardest thing about fitting a helm"
  - the message must contain one of: `hardest`, `helm`
  - scored words: `hardest`(1.8), `helm`(1.8), `whats`(0.8), `part`(0.8), `fitting`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.followup.ask_more   [42 chars]
    en  What's the hardest part of fitting a helm?
    >>  ............................................
    pt  Qual é a parte mais difícil de ajustar um elmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of fitting a helm?"
       spoken on: conversations.scene.work.armorer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.hard`: the villager explains. Subject `work.armorer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.armorer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.armorer.hard/1   [78 chars]
    en  A seam I'm not certain of. I've unpicked whole nights over one shoulder joint.
    >>  ............................................
    pt  Uma costura que eu não tenho certeza. Já desfiz noites inteiras por causa de um ombro.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.hard/2   [76 chars]
    en  The thought of a good pattern failing in the one place I never tested, %1$s.
    >>  ............................................
    pt  A ideia de um bom padrão falhar no único lugar que eu nunca testei, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the fire."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.armorer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.followup.leave   [27 chars]
    en  I'll leave you to the fire.
    >>  ............................................
    pt  Vou deixar você com o fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the fire."
       spoken on: conversations.scene.work.armorer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.armorer.stalled_commission.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.armorer.stalled_commission.blocked` — e.g. "%2$s is half finished on the bench and I have %3$s, so it will stay half finished."


```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.armorer.stalled_commission.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond   [15 chars]
    en  The commission.
    >>  ............................................
    pt  A encomenda.
    >>  ............................................
```


### Button `ask_the_cost` — "What does stopping cost you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.stalled_commission.blocked` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.stalled_commission.blocked.ask_the_cost` — accepted phrasings: "what does stopping cost you"; "what does stopping cost you"; "how much does the delay cost"
  - the message must contain one of: `cost`, `delay`
  - scored words: `cost`(1.8), `delay`(1.8), `does`(0.8), `stopping`(0.8), `much`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.ask_the_cost
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.stalled_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.ask_the_cost   [28 chars]
    en  What does stopping cost you?
    >>  ............................................
    pt  O que parar custa a você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.armorer.materials`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.armorer.stalled_commission"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What does stopping cost you?"
       spoken on: conversations.scene.work.armorer.stalled_commission.blocked.respond, button `ask_the_cost`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.stalled_commission.blocked.explained`: the villager explains. Subject `work.armorer.materials`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.explained/1   [123 chars]
    en  Two days of reheating to get %2$s back where it was, and a customer who has decided I am slow. The second one lasts longer.
    >>  ............................................
    pt  Dois dias reaquecendo para trazer %2$s de volta ao ponto, e um cliente que decidiu que eu sou lenta. O segundo dura mais.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.explained/2   [128 chars]
    en  Fuel, mostly. Every cold start burns charcoal for nothing, and I am buying charcoal to undo the last time I ran out of charcoal.
    >>  ............................................
    pt  Combustível, principalmente. Todo recomeço frio queima carvão à toa, e eu compro carvão para desfazer a última vez em que faltou carvão.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.explained/3   [148 chars]
    en  The honest cost is that I stop trusting my own timetable. Once you have missed one date you start padding all of them, and then you really are slow.
    >>  ............................................
    pt  O custo honesto é que eu paro de confiar no meu próprio prazo. Depois de furar uma data você começa a inflar todas, e aí você é lenta de verdade.
    >>  ............................................
```


### Button `offer_iron` — "I'll bring you iron."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.armorer.stalled_commission.blocked` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.stalled_commission.blocked.offer_iron` — accepted phrasings: "ill bring you iron"; "i can bring you iron"; "let me fetch iron for that"
  - the message must contain one of: `iron`
  - scored words: `iron`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.offer_iron
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.stalled_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.offer_iron   [20 chars]
    en  I'll bring you iron.
    >>  ............................................
    pt  Vou te trazer ferro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.armorer.commission.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.armorer.materials`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.stalled_commission", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.armorer.stalled_commission", "obligation": "commitment:work.armorer.bring_iron"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.armorer.bring_iron"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you iron."
       spoken on: conversations.scene.work.armorer.stalled_commission.blocked.respond, button `offer_iron`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.stalled_commission.blocked.accepted`: the villager accepts. Subject `work.armorer.materials`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.accepted/1   [85 chars]
    en  Then %2$s is finished by Thursday and I get to be the woman who keeps promises again.
    >>  ............................................
    pt  Então %2$s fica pronto até quinta e eu volto a ser a mulher que cumpre promessas.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.accepted/2   [101 chars]
    en  Bring it and I will light the hearth before you are back through the door. I am not proud about this.
    >>  ............................................
    pt  Traga e eu acendo a fornalha antes de você voltar pela porta. Não tenho orgulho nenhum nisso.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.accepted/3   [104 chars]
    en  Yes. And I will put your name on the offcuts, because whoever wears %2$s should know it took two people.
    >>  ............................................
    pt  Sim. E vou pôr seu nome nas sobras, porque quem vestir %2$s deve saber que foram duas pessoas.
    >>  ............................................
```


### Button `advise_telling_them` — "Tell the customer before market day."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.stalled_commission.blocked` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.stalled_commission.blocked.advise_telling_them` — accepted phrasings: "tell the customer before market day"; "tell the customer before market day"; "warn them ahead of the deadline"
  - the message must contain one of: `customer`, `warn`, `deadline`
  - scored words: `customer`(1.8), `warn`(1.8), `deadline`(1.8), `tell`(0.8), `before`(0.8), `market`(0.8), `day`(0.8), `ahead`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.advise_telling_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.stalled_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.advise_telling_them   [36 chars]
    en  Tell the customer before market day.
    >>  ............................................
    pt  Avise o cliente antes do dia de feira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.armorer.materials`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.armorer.stalled_commission"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Tell the customer before market day."
       spoken on: conversations.scene.work.armorer.stalled_commission.blocked.respond, button `advise_telling_them`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.stalled_commission.blocked.conceded`: the villager accepts. Subject `work.armorer.materials`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.conceded/1   [114 chars]
    en  I know. Early bad news is a courtesy and late bad news is an insult, and I have been sitting on it for three days.
    >>  ............................................
    pt  Eu sei. Má notícia cedo é cortesia e má notícia tarde é ofensa, e eu estou sentada em cima disso há três dias.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.conceded/2   [126 chars]
    en  You are right. I will go tonight, and I will offer to take something off the price, and he will refuse and think better of me.
    >>  ............................................
    pt  Você tem razão. Vou hoje à noite, vou oferecer um desconto, ele vai recusar e vai passar a me respeitar mais.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.conceded/3   [122 chars]
    en  That is the part of the trade nobody teaches. Hammering is easy. Walking across the village to say a thing is late is not.
    >>  ............................................
    pt  É a parte do ofício que ninguém ensina. Martelar é fácil. Atravessar a vila para dizer que algo atrasou, não.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · answers the beat(s) `work.armorer.stalled_commission.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.stalled_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.scene.work.armorer.stalled_commission.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.armorer.stalled_commission.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.armorer.stalled_commission.succeeded` — e.g. "%2$s went out on Thursday and it fits, and I watched him walk in it before I let him pay."


```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.armorer.stalled_commission.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.respond   [25 chars]
    en  The commission, finished.
    >>  ............................................
    pt  A encomenda, terminada.
    >>  ............................................
```


### Button `ask_what_makes_it_good` — "How do you know when it's right?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.stalled_commission.succeeded` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.armorer.stalled_commission.succeeded.ask_what_makes_it_good` — accepted phrasings: "how do you know when its right"; "how do you know when it is right"; "what tells you the fit is right"
  - the message must contain one of: `right`, `fit`, `know`
  - scored words: `right`(1.8), `fit`(1.8), `know`(1.8), `when`(0.8), `its`(0.8), `tells`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.respond.ask_what_makes_it_good
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.stalled_commission.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.respond.ask_what_makes_it_good   [32 chars]
    en  How do you know when it's right?
    >>  ............................................
    pt  Como você sabe quando está certo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.materials`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.armorer.stalled_commission"}
- Then opens: `conversations.scene.work.armorer.followup`
- …where the player's next choices will be: "What's the hardest part of fitting a helm?" | "I'll leave you to the fire."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "How do you know when it's right?"
       spoken on: conversations.scene.work.armorer.stalled_commission.succeeded.respond, button `ask_what_makes_it_good`
       leaves the player on: conversations.scene.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.stalled_commission.succeeded.explained`: the villager explains. Subject `work.armorer.materials`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.explained/1   [136 chars]
    en  They forget they are wearing it. That is the only test. If they keep adjusting it as they walk, I have failed and they are being polite.
    >>  ............................................
    pt  Esquecem que estão usando. É o único teste. Se ficam ajustando enquanto andam, eu falhei e eles estão sendo educados.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.explained/2   [121 chars]
    en  I watch the shoulders. Bad armour makes people hold their shoulders up, and they do not notice, and by evening they ache.
    >>  ............................................
    pt  Eu olho os ombros. Armadura ruim faz a pessoa levantar os ombros, e ela não percebe, e à noite dói.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.explained/3   [103 chars]
    en  It sounds right when they move. A good piece is quiet in the wrong places and honest in the right ones.
    >>  ............................................
    pt  Soa certo quando se movem. Uma peça boa é silenciosa nos lugares errados e franca nos certos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · answers the beat(s) `work.armorer.stalled_commission.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.armorer.stalled_commission.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.scene.work.armorer.stalled_commission.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer.craft` — e.g. "My father made plate and I make plate, and mine is better, and I have never told him so."


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.craft.respond   [25 chars]
    en  That's how it came to me.
    >>  ............................................
    pt  Foi assim que chegou a mim.
    >>  ............................................
```


### Button `ask_father` — "Why have you never told him?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.craft` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.craft.ask_father` — accepted phrasings: "why have you never told him"
  - the message must contain one of: `father`, `told`
  - scored words: `father`(1.5), `told`(1.2), `never`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.craft.respond.ask_father
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.craft.respond.ask_father   [28 chars]
    en  Why have you never told him?
    >>  ............................................
    pt  Por que você nunca disse a ele?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.craft.ask_father`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.craft.ask_father
WHO    VILLAGER — what the player reads after pressing "Why have you never told him?"
       spoken on: conversations.topic.work.armorer.craft.respond, button `ask_father`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.craft.ask_father`: the villager explains. Subject `work.armorer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.craft.ask_father/1   [76 chars]
    en  Because he'd agree, and then I'd have taken something off him for no reason.
    >>  ............................................
    pt  Porque ele concordaria, e aí eu teria tirado algo dele sem motivo.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.craft.ask_father/2   [83 chars]
    en  Because being better than your father is a thing to know, not a thing to say, %1$s.
    >>  ............................................
    pt  Porque ser melhor que o pai é coisa pra saber, não pra dizer, %1$s.
    >>  ............................................
```


### Button `admire` — "Restraint is the harder half."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.craft` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.craft.admire` — accepted phrasings: "restraint is the harder half"
  - the message must contain one of: `restraint`, `harder`, `holding`
  - scored words: `restraint`(1.5), `harder`(1.5), `holding`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.craft.respond.admire   [29 chars]
    en  Restraint is the harder half.
    >>  ............................................
    pt  A contenção é a metade difícil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.armorer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.armorer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.craft.admire
WHO    VILLAGER — what the player reads after pressing "Restraint is the harder half."
       spoken on: conversations.topic.work.armorer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.craft.admire`: the villager accepts. Subject `work.armorer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.craft.admire/1   [84 chars]
    en  It is, and there's no sign for it. The metal tells you and you have to be listening.
    >>  ............................................
    pt  É, e não tem sinal pra isso. O metal te diz e você precisa estar escutando.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.craft.admire/2   [63 chars]
    en  Everyone wants the hammer. Nobody asks about the not-hammering.
    >>  ............................................
    pt  Todo mundo quer o martelo. Ninguém pergunta do não-martelar.
    >>  ............................................
```


### Button `ask_fifteen` — "What happened at fifteen years?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.craft` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.craft.ask_fifteen` — accepted phrasings: "what happened at fifteen years"
  - the message must contain one of: `fifteen`, `learned`
  - scored words: `fifteen`(1.5), `years`(0.8), `learned`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.craft.respond.ask_fifteen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.craft.respond.ask_fifteen   [31 chars]
    en  What happened at fifteen years?
    >>  ............................................
    pt  O que aconteceu nos quinze anos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.craft.ask_fifteen`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.craft.ask_fifteen
WHO    VILLAGER — what the player reads after pressing "What happened at fifteen years?"
       spoken on: conversations.topic.work.armorer.craft.respond, button `ask_fifteen`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.craft.ask_fifteen`: the villager explains. Subject `work.armorer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.craft.ask_fifteen/1   [99 chars]
    en  A breastplate came back whole from something that should have gone through it. That was the lesson.
    >>  ............................................
    pt  Um peitoral voltou inteiro de algo que devia ter atravessado. Foi a lição.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.craft.ask_fifteen/2   [78 chars]
    en  Nothing dramatic. I simply stopped ruining pieces, and I noticed a year later.
    >>  ............................................
    pt  Nada dramático. Eu simplesmente parei de estragar peças, e reparei um ano depois.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the fire."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.craft.respond.leave   [34 chars]
    en  I'll let you get back to the fire.
    >>  ............................................
    pt  Vou deixar você voltar ao fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the fire."
       spoken on: conversations.topic.work.armorer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.followup / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.followup`

**Reached from 20 route(s):** `conversations.scene.work.armorer.followup` / `ask_more`; `conversations.topic.work.armorer.craft.respond` / `ask_father`; `conversations.topic.work.armorer.craft.respond` / `admire`; `conversations.topic.work.armorer.craft.respond` / `ask_fifteen`; `conversations.topic.work.armorer.future.respond` / `ask_suit`; `conversations.topic.work.armorer.future.respond` / `encourage`; `conversations.topic.work.armorer.future.respond` / `ask_successor`; `conversations.topic.work.armorer.respond` / `ask_hard`; `conversations.topic.work.armorer.respond` / `value`; `conversations.topic.work.armorer.respond` / `challenge`; `conversations.topic.work.armorer.respond` / `challenge`; `conversations.topic.work.armorer.risk.respond` / `ask_bet` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer.challenge.landed` — e.g. "Largely, yes. The skill is knowing when it fits and stopping."
- `conversations.work.prof.armorer.challenge.stung` — e.g. "...Wear one for a week and then tell me about bashing."
- `conversations.work.prof.armorer.craft.admire` — e.g. "It is, and there's no sign for it. The metal tells you and you have to be listening."
- `conversations.work.prof.armorer.craft.ask_father` — e.g. "Because he'd agree, and then I'd have taken something off him for no reason."
- `conversations.work.prof.armorer.craft.ask_fifteen` — e.g. "A breastplate came back whole from something that should have gone through it. That was the lesson."
- `conversations.work.prof.armorer.future.ask_successor` — e.g. "Two asked. Both wanted swords. I sent them to the weaponsmith and neither came back."
- `conversations.work.prof.armorer.future.ask_suit` — e.g. "Nobody in particular. Made properly, hung on a stand, and never dented. A thing to look at."
- `conversations.work.prof.armorer.future.encourage` — e.g. "...That's a better way round than mine. I'd been treating it as a thing to do when there's time."
- `conversations.work.prof.armorer.hard` — e.g. "A seam I'm not certain of. I've unpicked whole nights over one shoulder joint."
- `conversations.work.prof.armorer.risk.ask_bet` — e.g. "I count the ones that came back dented. That's the only proof there is and I keep it carefully."
- `conversations.work.prof.armorer.risk.ask_fuel` — e.g. "It could, by somebody with a cart and a fortnight. I've asked the mayor twice."
- `conversations.work.prof.armorer.risk.sympathise` — e.g. "...It is. And it isn't the sort of thing you raise at the inn on a Friday."
- `conversations.work.prof.armorer.task.ask_fitting` — e.g. "A finger's width wrong at the shoulder and he can't raise his arm past his ear. Yes."
- `conversations.work.prof.armorer.task.ask_third` — e.g. "Either my pattern is wrong or something out there has learned where to strike. I sleep badly on it."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.followup   [36 chars]
    en  That's the forge and its arithmetic.
    >>  ............................................
    pt  É a forja e a aritmética dela.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.challenge.landed`, `work.armorer.challenge.stung`, `work.armorer.craft.admire`, `work.armorer.craft.ask_father`, `work.armorer.craft.ask_fifteen`, `work.armorer.future.ask_successor`, `work.armorer.future.ask_suit`, `work.armorer.future.encourage`, `work.armorer.hard`, `work.armorer.risk.ask_bet`, `work.armorer.risk.ask_fuel`, `work.armorer.risk.sympathise`, `work.armorer.task.ask_fitting`, `work.armorer.task.ask_third`, `work.armorer.task.offer_hands`, `work.armorer.value`, `work.armorer.village.ask_price`, `work.armorer.village.ask_two`, `work.armorer.village.say_thanks` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.armorer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `funerals`, `absence`
  - scored words: `thought`(1.2), `funerals`(1.5), `absence`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.armorer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.armorer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.armorer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.armorer.thanks`: the villager accepts. Subject `work.armorer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.thanks/1   [71 chars]
    en  It's a strange way to count a life's work. Funerals that didn't happen.
    >>  ............................................
    pt  É um jeito estranho de contar o trabalho de uma vida. Funerais que não aconteceram.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.thanks/2   [63 chars]
    en  Nobody thanks you for an absence, %1$s. So I keep my own tally.
    >>  ............................................
    pt  Ninguém te agradece por uma ausência, %1$s. Então eu faço minha própria conta.
    >>  ............................................
```


### Button `ask_more` — "Do the dents tell you anything?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.challenge.landed`, `work.armorer.challenge.stung`, `work.armorer.craft.admire`, `work.armorer.craft.ask_father`, `work.armorer.craft.ask_fifteen`, `work.armorer.future.ask_successor`, `work.armorer.future.ask_suit`, `work.armorer.future.encourage`, `work.armorer.hard`, `work.armorer.risk.ask_bet`, `work.armorer.risk.ask_fuel`, `work.armorer.risk.sympathise`, `work.armorer.task.ask_fitting`, `work.armorer.task.ask_third`, `work.armorer.task.offer_hands`, `work.armorer.value`, `work.armorer.village.ask_price`, `work.armorer.village.ask_two`, `work.armorer.village.say_thanks` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.armorer.more` — accepted phrasings: "do the dents tell you anything"
  - the message must contain one of: `dents`, `damage`
  - scored words: `dents`(1.5), `damage`(1.2), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.followup.ask_more   [31 chars]
    en  Do the dents tell you anything?
    >>  ............................................
    pt  Os amassados te dizem alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.armorer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.more
WHO    VILLAGER — what the player reads after pressing "Do the dents tell you anything?"
       spoken on: conversations.topic.work.armorer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.armorer.more`: the villager discloses. Subject `work.armorer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.more/1   [82 chars]
    en  Everything. Where a thing struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde bateu, com que força, e se sabia o que estava fazendo.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.more/2   [77 chars]
    en  Three came back the same last month. That's not luck, %1$s, that's a pattern.
    >>  ............................................
    pt  Três voltaram iguais mês passado. Isso não é sorte, %1$s, é padrão.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, and I'd rather it told me less. Some dents are a bad evening somebody survived.
    >>  ............................................
    pt  Tudo, e eu preferia que dissesse menos. Alguns amassados são uma noite ruim que alguém sobreviveu.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.armorer.more/2
    en  Three in the same place. I sleep badly on that and I've told nobody why.
    >>  ............................................
    pt  Três no mesmo lugar. Durmo mal com isso e não contei a ninguém por quê.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, given time. A dent will tell you the whole story if you sit with it long enough.
    >>  ............................................
    pt  Tudo, com tempo. Um amassado conta a história inteira se você ficar tempo suficiente com ele.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.armorer.more/2
    en  Three, all in one place. I'll watch it another season before I say anything out loud.
    >>  ............................................
    pt  Três, todos no mesmo lugar. Vou observar mais uma estação antes de dizer algo em voz alta.
    >>  ............................................
  confident.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where a thing struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde algo bateu, com que força, e se sabia o que estava fazendo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders split in one season, all in the same place. Either my pattern is wrong or something has learned.
    >>  ............................................
    pt  Três ombros rachados numa estação, todos no mesmo lugar. Ou meu padrão está errado ou algo aprendeu.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where a thing struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde algo bateu, com que força, e se sabia o que estava fazendo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders split in one season, all in the same place. Either my pattern is wrong or something has learned.
    >>  ............................................
    pt  Três ombros rachados numa estação, todos no mesmo lugar. Ou meu padrão está errado ou algo aprendeu.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Bring me a dented piece some time and I'll read it to you like a letter.
    >>  ............................................
    pt  Tudo. Traga uma peça amassada um dia e eu leio pra você como uma carta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.armorer.more/2
    en  Three in one place this season. I've not told the guard yet — I'd rather be certain before I frighten him.
    >>  ............................................
    pt  Três no mesmo lugar nesta estação. Ainda não contei ao guarda — prefiro ter certeza antes de assustá-lo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Bring me a dented piece some time and I'll read it to you like a letter.
    >>  ............................................
    pt  Tudo. Traga uma peça amassada um dia e eu leio pra você como uma carta.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.armorer.more/2
    en  Three in one place this season. I've not told the guard yet — I'd rather be certain before I frighten him.
    >>  ............................................
    pt  Três no mesmo lugar nesta estação. Ainda não contei ao guarda — prefiro ter certeza antes de assustá-lo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Bring me a dented piece some time and I'll read it to you like a letter.
    >>  ............................................
    pt  Tudo. Traga uma peça amassada um dia e eu leio pra você como uma carta.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.armorer.more/2
    en  Three in one place this season. I've not told the guard yet — I'd rather be certain before I frighten him.
    >>  ............................................
    pt  Três no mesmo lugar nesta estação. Ainda não contei ao guarda — prefiro ter certeza antes de assustá-lo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, and I'd rather it told me less. Some dents are a bad evening somebody survived.
    >>  ............................................
    pt  Tudo, e eu preferia que dissesse menos. Alguns amassados são uma noite ruim que alguém sobreviveu.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.armorer.more/2
    en  Three in the same place. I sleep badly on that and I've told nobody why.
    >>  ............................................
    pt  Três no mesmo lugar. Durmo mal com isso e não contei a ninguém por quê.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where a thing struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde algo bateu, com que força, e se sabia o que estava fazendo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders split in one season, all in the same place. Either my pattern is wrong or something has learned.
    >>  ............................................
    pt  Três ombros rachados numa estação, todos no mesmo lugar. Ou meu padrão está errado ou algo aprendeu.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where a thing struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde algo bateu, com que força, e se sabia o que estava fazendo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders split in one season, all in the same place. Either my pattern is wrong or something has learned.
    >>  ............................................
    pt  Três ombros rachados numa estação, todos no mesmo lugar. Ou meu padrão está errado ou algo aprendeu.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where it struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde bateu, com que força, e se sabia o que fazia.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders, same place, one season. I've said nothing and I've changed the pattern.
    >>  ............................................
    pt  Três ombros, mesmo lugar, uma estação. Não disse nada e mudei o padrão.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, given time. A dent will tell you the whole story if you sit with it long enough.
    >>  ............................................
    pt  Tudo, com tempo. Um amassado conta a história inteira se você ficar tempo suficiente com ele.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.armorer.more/2
    en  Three, all in one place. I'll watch it another season before I say anything out loud.
    >>  ............................................
    pt  Três, todos no mesmo lugar. Vou observar mais uma estação antes de dizer algo em voz alta.
    >>  ............................................
  odd.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where it struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde bateu, com que força, e se sabia o que fazia.
    >>  ............................................
  odd.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders, same place, one season. I've said nothing and I've changed the pattern.
    >>  ............................................
    pt  Três ombros, mesmo lugar, uma estação. Não disse nada e mudei o padrão.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, given time. A dent will tell you the whole story if you sit with it long enough.
    >>  ............................................
    pt  Tudo, com tempo. Um amassado conta a história inteira se você ficar tempo suficiente com ele.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.armorer.more/2
    en  Three, all in one place. I'll watch it another season before I say anything out loud.
    >>  ............................................
    pt  Três, todos no mesmo lugar. Vou observar mais uma estação antes de dizer algo em voz alta.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.armorer.more/1
    en  Everything! A dent is a letter. Where it hit, how hard, and whether the thing was any good at it.
    >>  ............................................
    pt  Tudo! Um amassado é uma carta. Onde bateu, com que força, e se a coisa era boa nisso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.armorer.more/2
    en  Three split shoulders this season, same spot each time. I find that fascinating and deeply unwelcome.
    >>  ............................................
    pt  Três ombros rachados nesta estação, mesmo ponto. Acho isso fascinante e profundamente indesejável.
    >>  ............................................
  playful.dialogue.conversations.work.prof.armorer.more/1
    en  Everything! A dent is a letter. Where it hit, how hard, and whether the thing was any good at it.
    >>  ............................................
    pt  Tudo! Um amassado é uma carta. Onde bateu, com que força, e se a coisa era boa nisso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.armorer.more/2
    en  Three split shoulders this season, same spot each time. I find that fascinating and deeply unwelcome.
    >>  ............................................
    pt  Três ombros rachados nesta estação, mesmo ponto. Acho isso fascinante e profundamente indesejável.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, given time. A dent will tell you the whole story if you sit with it long enough.
    >>  ............................................
    pt  Tudo, com tempo. Um amassado conta a história inteira se você ficar tempo suficiente com ele.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.armorer.more/2
    en  Three, all in one place. I'll watch it another season before I say anything out loud.
    >>  ............................................
    pt  Três, todos no mesmo lugar. Vou observar mais uma estação antes de dizer algo em voz alta.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.armorer.more/1
    en  Everything, and I'd rather it told me less. Some dents are a bad evening somebody survived.
    >>  ............................................
    pt  Tudo, e eu preferia que dissesse menos. Alguns amassados são uma noite ruim que alguém sobreviveu.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.armorer.more/2
    en  Three in the same place. I sleep badly on that and I've told nobody why.
    >>  ............................................
    pt  Três no mesmo lugar. Durmo mal com isso e não contei a ninguém por quê.
    >>  ............................................
  shy.dialogue.conversations.work.prof.armorer.more/1
    en  Everything. Where it struck, how hard, and whether it knew what it was doing.
    >>  ............................................
    pt  Tudo. Onde bateu, com que força, e se sabia o que fazia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.armorer.more/2
    en  Three shoulders, same place, one season. I've said nothing and I've changed the pattern.
    >>  ............................................
    pt  Três ombros, mesmo lugar, uma estação. Não disse nada e mudei o padrão.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.armorer.more/1
    en  Everything! A dent is a letter. Where it hit, how hard, and whether the thing was any good at it.
    >>  ............................................
    pt  Tudo! Um amassado é uma carta. Onde bateu, com que força, e se a coisa era boa nisso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.armorer.more/2
    en  Three split shoulders this season, same spot each time. I find that fascinating and deeply unwelcome.
    >>  ............................................
    pt  Três ombros rachados nesta estação, mesmo ponto. Acho isso fascinante e profundamente indesejável.
    >>  ............................................
  witty.dialogue.conversations.work.prof.armorer.more/1
    en  Everything! A dent is a letter. Where it hit, how hard, and whether the thing was any good at it.
    >>  ............................................
    pt  Tudo! Um amassado é uma carta. Onde bateu, com que força, e se a coisa era boa nisso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.armorer.more/2
    en  Three split shoulders this season, same spot each time. I find that fascinating and deeply unwelcome.
    >>  ............................................
    pt  Três ombros rachados nesta estação, mesmo ponto. Acho isso fascinante e profundamente indesejável.
    >>  ............................................
```

</details>


### Button `leave` — "Steady hands."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.challenge.landed`, `work.armorer.challenge.stung`, `work.armorer.craft.admire`, `work.armorer.craft.ask_father`, `work.armorer.craft.ask_fifteen`, `work.armorer.future.ask_successor`, `work.armorer.future.ask_suit`, `work.armorer.future.encourage`, `work.armorer.hard`, `work.armorer.risk.ask_bet`, `work.armorer.risk.ask_fuel`, `work.armorer.risk.sympathise`, `work.armorer.task.ask_fitting`, `work.armorer.task.ask_third`, `work.armorer.task.offer_hands`, `work.armorer.value`, `work.armorer.village.ask_price`, `work.armorer.village.ask_two`, `work.armorer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.followup.leave   [13 chars]
    en  Steady hands.
    >>  ............................................
    pt  Mãos firmes.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "Steady hands."
       spoken on: conversations.topic.work.armorer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer.future` — e.g. "I want to make one suit that nobody ever needs. That's the whole ambition and it's a strange one."


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.future.respond   [31 chars]
    en  That's the far end of the fire.
    >>  ............................................
    pt  É o extremo do fogo.
    >>  ............................................
```


### Button `ask_suit` — "Who would it be for?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.future` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.future.ask_suit` — accepted phrasings: "who would it be for"
  - the message must contain one of: `suit`, `whom`
  - scored words: `suit`(1.5), `whom`(1.2), `for`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.future.respond.ask_suit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.future.respond.ask_suit   [20 chars]
    en  Who would it be for?
    >>  ............................................
    pt  Pra quem seria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.future.ask_suit`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.future.ask_suit
WHO    VILLAGER — what the player reads after pressing "Who would it be for?"
       spoken on: conversations.topic.work.armorer.future.respond, button `ask_suit`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.future.ask_suit`: the villager explains. Subject `work.armorer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.future.ask_suit/1   [91 chars]
    en  Nobody in particular. Made properly, hung on a stand, and never dented. A thing to look at.
    >>  ............................................
    pt  Pra ninguém em especial. Bem feita, num suporte, e nunca amassada. Uma coisa pra olhar.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.future.ask_suit/2   [91 chars]
    en  The next guard. And I'd like it to be the one that comes back with no story attached, %1$s.
    >>  ............................................
    pt  Pro próximo guarda. E eu queria que fosse a que volta sem história nenhuma, %1$s.
    >>  ............................................
```


### Button `encourage` — "Make it. The stand can wait for the wearer."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.future` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.future.encourage` — accepted phrasings: "make it. the stand can wait for the wearer"
  - the message must contain one of: `stand`, `wearer`
  - scored words: `make`(0.8), `stand`(1.2), `wearer`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.future.respond.encourage   [43 chars]
    en  Make it. The stand can wait for the wearer.
    >>  ............................................
    pt  Faça. O suporte pode esperar por quem vá usar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.armorer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.armorer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Make it. The stand can wait for the wearer."
       spoken on: conversations.topic.work.armorer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.future.encourage`: the villager accepts. Subject `work.armorer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.future.encourage/1   [96 chars]
    en  ...That's a better way round than mine. I'd been treating it as a thing to do when there's time.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha. Eu vinha tratando como coisa pra fazer quando sobrar tempo.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.future.encourage/2   [72 chars]
    en  There's never time. That's why it needs saying out loud, so — thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Por isso precisa ser dito em voz alta, então — obrigado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine, and I'm a little ashamed of mine.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha, e eu me envergonho um pouco da minha.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Saying it aloud makes it a thing I could fail at.
    >>  ............................................
    pt  Nunca sobra tempo. Dizer em voz alta faz virar algo em que eu posso falhar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Thirty years of 'when there's time' and there never was.
    >>  ............................................
    pt  ...Melhor que a minha. Trinta anos de 'quando sobrar tempo' e nunca sobrou.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. I could have told you that at twenty and I'd not have believed it.
    >>  ............................................
    pt  Nunca sobra tempo. Eu diria isso aos vinte e não teria acreditado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine. I'd made it a thing for when there's time.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha. Eu tratava como coisa pra quando sobrasse tempo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. That's why it needs saying out loud, so — thank you.
    >>  ............................................
    pt  Nunca sobra tempo. É por isso que precisa ser dito em voz alta, então — obrigado.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine. I'd made it a thing for when there's time.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha. Eu tratava como coisa pra quando sobrasse tempo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. That's why it needs saying out loud, so — thank you.
    >>  ............................................
    pt  Nunca sobra tempo. É por isso que precisa ser dito em voz alta, então — obrigado.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine, %1$s. I'd been saving it for a spare year.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha, %1$s. Eu guardava pra um ano de sobra.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. You knew that when you said it, and thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Você sabia disso quando disse, e obrigado.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine, %1$s. I'd been saving it for a spare year.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha, %1$s. Eu guardava pra um ano de sobra.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. You knew that when you said it, and thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Você sabia disso quando disse, e obrigado.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine, %1$s. I'd been saving it for a spare year.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha, %1$s. Eu guardava pra um ano de sobra.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. You knew that when you said it, and thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Você sabia disso quando disse, e obrigado.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine, and I'm a little ashamed of mine.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha, e eu me envergonho um pouco da minha.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Saying it aloud makes it a thing I could fail at.
    >>  ............................................
    pt  Nunca sobra tempo. Dizer em voz alta faz virar algo em que eu posso falhar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine. I'd made it a thing for when there's time.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha. Eu tratava como coisa pra quando sobrasse tempo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. That's why it needs saying out loud, so — thank you.
    >>  ............................................
    pt  Nunca sobra tempo. É por isso que precisa ser dito em voz alta, então — obrigado.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine. I'd made it a thing for when there's time.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha. Eu tratava como coisa pra quando sobrasse tempo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. That's why it needs saying out loud, so — thank you.
    >>  ............................................
    pt  Nunca sobra tempo. É por isso que precisa ser dito em voz alta, então — obrigado.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Mine was 'when there's time'.
    >>  ............................................
    pt  ...Melhor que a minha. A minha era 'quando sobrar tempo'.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Obrigado.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Thirty years of 'when there's time' and there never was.
    >>  ............................................
    pt  ...Melhor que a minha. Trinta anos de 'quando sobrar tempo' e nunca sobrou.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. I could have told you that at twenty and I'd not have believed it.
    >>  ............................................
    pt  Nunca sobra tempo. Eu diria isso aos vinte e não teria acreditado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Mine was 'when there's time'.
    >>  ............................................
    pt  ...Melhor que a minha. A minha era 'quando sobrar tempo'.
    >>  ............................................
  odd.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Obrigado.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Thirty years of 'when there's time' and there never was.
    >>  ............................................
    pt  ...Melhor que a minha. Trinta anos de 'quando sobrar tempo' e nunca sobrou.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. I could have told you that at twenty and I'd not have believed it.
    >>  ............................................
    pt  Nunca sobra tempo. Eu diria isso aos vinte e não teria acreditado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a much better way round than mine! Mine was 'someday', which is not a plan.
    >>  ............................................
    pt  ...É uma ordem bem melhor que a minha! A minha era 'um dia', o que não é plano.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Never! So it has to be said out loud — thank you for making me.
    >>  ............................................
    pt  Nunca sobra tempo. Nunca! Então tem que ser dito em voz alta — obrigado por me obrigar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a much better way round than mine! Mine was 'someday', which is not a plan.
    >>  ............................................
    pt  ...É uma ordem bem melhor que a minha! A minha era 'um dia', o que não é plano.
    >>  ............................................
  playful.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Never! So it has to be said out loud — thank you for making me.
    >>  ............................................
    pt  Nunca sobra tempo. Nunca! Então tem que ser dito em voz alta — obrigado por me obrigar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Thirty years of 'when there's time' and there never was.
    >>  ............................................
    pt  ...Melhor que a minha. Trinta anos de 'quando sobrar tempo' e nunca sobrou.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. I could have told you that at twenty and I'd not have believed it.
    >>  ............................................
    pt  Nunca sobra tempo. Eu diria isso aos vinte e não teria acreditado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a better way round than mine, and I'm a little ashamed of mine.
    >>  ............................................
    pt  ...É uma ordem melhor que a minha, e eu me envergonho um pouco da minha.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Saying it aloud makes it a thing I could fail at.
    >>  ............................................
    pt  Nunca sobra tempo. Dizer em voz alta faz virar algo em que eu posso falhar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...Better than mine. Mine was 'when there's time'.
    >>  ............................................
    pt  ...Melhor que a minha. A minha era 'quando sobrar tempo'.
    >>  ............................................
  shy.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Thank you.
    >>  ............................................
    pt  Nunca sobra tempo. Obrigado.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a much better way round than mine! Mine was 'someday', which is not a plan.
    >>  ............................................
    pt  ...É uma ordem bem melhor que a minha! A minha era 'um dia', o que não é plano.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Never! So it has to be said out loud — thank you for making me.
    >>  ............................................
    pt  Nunca sobra tempo. Nunca! Então tem que ser dito em voz alta — obrigado por me obrigar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.armorer.future.encourage/1
    en  ...That's a much better way round than mine! Mine was 'someday', which is not a plan.
    >>  ............................................
    pt  ...É uma ordem bem melhor que a minha! A minha era 'um dia', o que não é plano.
    >>  ............................................
  witty.dialogue.conversations.work.prof.armorer.future.encourage/2
    en  There's never time. Never! So it has to be said out loud — thank you for making me.
    >>  ............................................
    pt  Nunca sobra tempo. Nunca! Então tem que ser dito em voz alta — obrigado por me obrigar.
    >>  ............................................
```

</details>


### Button `ask_successor` — "Has nobody wanted the forge?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.future` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.future.ask_successor` — accepted phrasings: "has nobody wanted the forge"
  - the message must contain one of: `forge`, `successor`
  - scored words: `forge`(1.5), `successor`(1.5), `wanted`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.future.respond.ask_successor
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.future.respond.ask_successor   [28 chars]
    en  Has nobody wanted the forge?
    >>  ............................................
    pt  Ninguém quis a forja?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.future.ask_successor`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.future.ask_successor
WHO    VILLAGER — what the player reads after pressing "Has nobody wanted the forge?"
       spoken on: conversations.topic.work.armorer.future.respond, button `ask_successor`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.future.ask_successor`: the villager explains. Subject `work.armorer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.future.ask_successor/1   [84 chars]
    en  Two asked. Both wanted swords. I sent them to the weaponsmith and neither came back.
    >>  ............................................
    pt  Dois perguntaram. Os dois queriam espadas. Mandei ao armeiro de guerra e nenhum voltou.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.future.ask_successor/2   [84 chars]
    en  It's hot, loud and nobody thanks you. I understand the shortage of volunteers, %1$s.
    >>  ............................................
    pt  É quente, barulhento e ninguém agradece. Entendo a falta de voluntários, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the fire."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.future.respond.leave   [34 chars]
    en  I'll let you get back to the fire.
    >>  ............................................
    pt  Vou deixar você voltar ao fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the fire."
       spoken on: conversations.topic.work.armorer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer` — e.g. "I make the steel that stands between folk and their bad luck. Heavy work. Sleeps well, though."


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.respond   [36 chars]
    en  That's the steel, and what it's for.
    >>  ............................................
    pt  É o aço, e pra que ele serve.
    >>  ............................................
```


### Button `ask_hard` — "What keeps you up about it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.identity` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.hard` — accepted phrasings: "what keeps you up about it"
  - the message must contain one of: `keeps`, `seam`, `worry`
  - scored words: `keeps`(1.0), `seam`(1.5), `worry`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.respond.ask_hard   [27 chars]
    en  What keeps you up about it?
    >>  ............................................
    pt  O que te tira o sono nisso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.armorer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.hard
WHO    VILLAGER — what the player reads after pressing "What keeps you up about it?"
       spoken on: conversations.topic.work.armorer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.hard`: the villager explains. Subject `work.armorer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.armorer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Nobody's died in armour you made."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.identity` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.value` — accepted phrasings: "nobody's died in armour you made"
  - the message must contain one of: `died`, `armour`, `survived`
  - scored words: `died`(1.5), `armour`(1.2), `survived`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.respond.value   [33 chars]
    en  Nobody's died in armour you made.
    >>  ............................................
    pt  Ninguém morreu com armadura sua.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.armorer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.armorer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.value
WHO    VILLAGER — what the player reads after pressing "Nobody's died in armour you made."
       spoken on: conversations.topic.work.armorer.respond, button `value`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.value`: the villager accepts. Subject `work.armorer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.value/1   [63 chars]
    en  Not yet. I keep the count and I'd like to die with it unbroken.
    >>  ............................................
    pt  Ainda não. Eu conto, e queria morrer com essa conta intacta.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.value/2   [71 chars]
    en  That's the number I actually care about. Everything else is trade talk.
    >>  ............................................
    pt  É esse o número que me importa de verdade. O resto é conversa de ofício.
    >>  ............................................
```


### Button `challenge` — "It's bashing metal until it fits."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.armorer.identity` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.challenge` — accepted phrasings: "it's bashing metal until it fits"
  - the message must contain one of: `bashing`, `metal`, `hammer`
  - scored words: `bashing`(1.5), `metal`(1.2), `hammer`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.respond.challenge   [33 chars]
    en  It's bashing metal until it fits.
    >>  ............................................
    pt  É bater metal até encaixar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.armorer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.armorer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's bashing metal until it fits."
       spoken on: conversations.topic.work.armorer.respond, button `challenge`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.challenge.landed`: the villager resists. Subject `work.armorer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.challenge.landed/1   [61 chars]
    en  Largely, yes. The skill is knowing when it fits and stopping.
    >>  ............................................
    pt  Basicamente, sim. A habilidade é saber quando encaixou e parar.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.challenge.landed/2   [55 chars]
    en  Bashing metal. Ha. That's the loud quarter of it, %1$s.
    >>  ............................................
    pt  Bater metal. Ha. É o quarto barulhento da coisa, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.armorer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.armorer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's bashing metal until it fits."
       spoken on: conversations.topic.work.armorer.respond, button `challenge`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.challenge.stung`: the villager resists. Subject `work.armorer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.challenge.stung/1   [54 chars]
    en  ...Wear one for a week and then tell me about bashing.
    >>  ............................................
    pt  ...Use uma por uma semana e depois me fale sobre bater.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.challenge.stung/2   [68 chars]
    en  Bashing. Right. That's what the guard said, until the day it wasn't.
    >>  ............................................
    pt  Bater. Certo. Foi o que o guarda disse, até o dia em que não era.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the fire."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.respond.leave   [34 chars]
    en  I'll let you get back to the fire.
    >>  ............................................
    pt  Vou deixar você voltar ao fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the fire."
       spoken on: conversations.topic.work.armorer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer.risk` — e.g. "Everything I make is a bet that I got it right, and I never find out when I did."


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.risk.respond   [24 chars]
    en  That's what hangs on it.
    >>  ............................................
    pt  É o que depende disso.
    >>  ............................................
```


### Button `ask_bet` — "How do you live with not knowing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.risk` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.risk.ask_bet` — accepted phrasings: "how do you live with not knowing"
  - the message must contain one of: `knowing`, `live`, `proof`
  - scored words: `knowing`(1.5), `live`(1.0), `proof`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.risk.respond.ask_bet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.risk.respond.ask_bet   [33 chars]
    en  How do you live with not knowing?
    >>  ............................................
    pt  Como você vive sem saber?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.risk.ask_bet`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.risk.ask_bet
WHO    VILLAGER — what the player reads after pressing "How do you live with not knowing?"
       spoken on: conversations.topic.work.armorer.risk.respond, button `ask_bet`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.risk.ask_bet`: the villager explains. Subject `work.armorer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.risk.ask_bet/1   [95 chars]
    en  I count the ones that came back dented. That's the only proof there is and I keep it carefully.
    >>  ............................................
    pt  Conto as que voltaram amassadas. É a única prova que existe e eu guardo com cuidado.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.risk.ask_bet/2   [63 chars]
    en  Badly, at three in the morning. Well enough at the bench, %1$s.
    >>  ............................................
    pt  Mal, às três da manhã. Bem o bastante na bancada, %1$s.
    >>  ............................................
```


### Button `sympathise` — "That's a strange weight to carry alone."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.risk` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.risk.sympathise` — accepted phrasings: "that's a strange weight to carry alone"
  - the message must contain one of: `weight`, `alone`, `carry`
  - scored words: `weight`(1.5), `alone`(1.2), `carry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.risk.respond.sympathise   [39 chars]
    en  That's a strange weight to carry alone.
    >>  ............................................
    pt  É um peso estranho de carregar sozinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.armorer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.armorer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "That's a strange weight to carry alone."
       spoken on: conversations.topic.work.armorer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.risk.sympathise`: the villager accepts. Subject `work.armorer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.risk.sympathise/1   [74 chars]
    en  ...It is. And it isn't the sort of thing you raise at the inn on a Friday.
    >>  ............................................
    pt  ...É. E não é o tipo de coisa que se levanta na estalagem na sexta.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.risk.sympathise/2   [83 chars]
    en  Alone is the word. The guard doesn't think about it and I think about nothing else.
    >>  ............................................
    pt  Sozinho é a palavra. O guarda não pensa nisso e eu não penso em outra coisa.
    >>  ............................................
```


### Button `ask_fuel` — "Could the fuel be sorted before winter?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.armorer.risk` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.risk.ask_fuel` — accepted phrasings: "could the fuel be sorted before winter"
  - the message must contain one of: `fuel`, `winter`, `charcoal`
  - scored words: `fuel`(1.5), `winter`(1.2), `charcoal`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.risk.respond.ask_fuel
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.risk.respond.ask_fuel   [39 chars]
    en  Could the fuel be sorted before winter?
    >>  ............................................
    pt  Dá pra resolver o combustível antes do inverno?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.armorer.risk.ask_fuel`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.armorer.risk.ask_fuel`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.risk.ask_fuel
WHO    VILLAGER — what the player reads after pressing "Could the fuel be sorted before winter?"
       spoken on: conversations.topic.work.armorer.risk.respond, button `ask_fuel`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.risk.ask_fuel`: the villager accepts. Subject `work.armorer.risk`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.risk.ask_fuel/1   [78 chars]
    en  It could, by somebody with a cart and a fortnight. I've asked the mayor twice.
    >>  ............................................
    pt  Daria, com alguém com carroça e quinze dias. Já pedi ao prefeito duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.risk.ask_fuel/2   [76 chars]
    en  If the charcoal came in autumn instead of January, yes. It never does, %1$s.
    >>  ............................................
    pt  Se o carvão chegasse no outono e não em janeiro, sim. Nunca chega, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the fire."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.risk.respond.leave   [34 chars]
    en  I'll let you get back to the fire.
    >>  ............................................
    pt  Vou deixar você voltar ao fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the fire."
       spoken on: conversations.topic.work.armorer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer.task` — e.g. "Re-riveting a shoulder that came back split. Third one this season, same place."


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.task.respond   [22 chars]
    en  That's the fire today.
    >>  ............................................
    pt  É o fogo de hoje.
    >>  ............................................
```


### Button `ask_third` — "Three in one place. What does that tell you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.task` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.task.ask_third` — accepted phrasings: "three in one place. what does that tell you"
  - the message must contain one of: `three`, `pattern`, `split`
  - scored words: `three`(1.5), `pattern`(1.2), `split`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.task.respond.ask_third
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.task.respond.ask_third   [44 chars]
    en  Three in one place. What does that tell you?
    >>  ............................................
    pt  Três no mesmo lugar. O que isso te diz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.task.ask_third`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.task.ask_third
WHO    VILLAGER — what the player reads after pressing "Three in one place. What does that tell you?"
       spoken on: conversations.topic.work.armorer.task.respond, button `ask_third`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.task.ask_third`: the villager explains. Subject `work.armorer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.task.ask_third/1   [99 chars]
    en  Either my pattern is wrong or something out there has learned where to strike. I sleep badly on it.
    >>  ............................................
    pt  Ou meu padrão está errado ou algo lá fora aprendeu onde bater. Durmo mal com isso.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.task.ask_third/2   [87 chars]
    en  It tells me to change the pattern and say nothing to the guard until I'm certain, %1$s.
    >>  ............................................
    pt  Diz pra eu mudar o padrão e não dizer nada ao guarda até ter certeza, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I can hold the piece steady."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.armorer.task` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.task.offer_hands` — accepted phrasings: "i can hold the piece steady"
  - the message must contain one of: `hold`, `steady`
  - scored words: `hold`(1.5), `steady`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.task.respond.offer_hands   [28 chars]
    en  I can hold the piece steady.
    >>  ............................................
    pt  Eu posso segurar a peça firme.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.armorer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.armorer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can hold the piece steady."
       spoken on: conversations.topic.work.armorer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.task.offer_hands`: the villager accepts. Subject `work.armorer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.task.offer_hands/1   [72 chars]
    en  ...Aye. Both hands, and when I say still I mean still, not nearly still.
    >>  ............................................
    pt  ...Pode. Duas mãos, e quando eu digo parado é parado, não quase parado.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.task.offer_hands/2   [60 chars]
    en  Then take the tongs and don't be proud about the heat, %1$s.
    >>  ............................................
    pt  Então pegue a tenaz e não seja orgulhoso com o calor, %1$s.
    >>  ............................................
```


### Button `ask_fitting` — "Does a fitting really matter that much?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.task` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.task.ask_fitting` — accepted phrasings: "does a fitting really matter that much"
  - the message must contain one of: `fitting`, `matter`, `size`
  - scored words: `fitting`(1.5), `matter`(1.0), `size`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.task.respond.ask_fitting
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.task.respond.ask_fitting   [39 chars]
    en  Does a fitting really matter that much?
    >>  ............................................
    pt  Um ajuste importa tanto assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.task.ask_fitting`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.task.ask_fitting
WHO    VILLAGER — what the player reads after pressing "Does a fitting really matter that much?"
       spoken on: conversations.topic.work.armorer.task.respond, button `ask_fitting`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.task.ask_fitting`: the villager explains. Subject `work.armorer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.task.ask_fitting/1   [84 chars]
    en  A finger's width wrong at the shoulder and he can't raise his arm past his ear. Yes.
    >>  ............................................
    pt  Um dedo de erro no ombro e ele não levanta o braço acima da orelha. Sim.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.task.ask_fitting/2   [82 chars]
    en  It's the whole trade. Anyone can make plate. Making plate for one man is the work.
    >>  ............................................
    pt  É o ofício inteiro. Qualquer um faz placa. Fazer placa pra um homem é o trabalho.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the fire."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.task.respond.leave   [34 chars]
    en  I'll let you get back to the fire.
    >>  ............................................
    pt  Vou deixar você voltar ao fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the fire."
       spoken on: conversations.topic.work.armorer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.armorer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.armorer.village` — e.g. "The watch stands in what I made. If I am wrong, somebody's family finds out before I do."


```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.armorer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.armorer.village.respond   [29 chars]
    en  That's what stands behind it.
    >>  ............................................
    pt  É o que está por trás disso.
    >>  ............................................
```


### Button `ask_two` — "What happened to the two?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.village` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.village.ask_two` — accepted phrasings: "what happened to the two"
  - the message must contain one of: `happened`, `two`, `died`
  - scored words: `happened`(1.2), `two`(1.5), `died`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.village.respond.ask_two
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.village.respond.ask_two   [25 chars]
    en  What happened to the two?
    >>  ............................................
    pt  O que aconteceu com os dois?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.village.ask_two`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.village.ask_two
WHO    VILLAGER — what the player reads after pressing "What happened to the two?"
       spoken on: conversations.topic.work.armorer.village.respond, button `ask_two`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.village.ask_two`: the villager explains. Subject `work.armorer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.village.ask_two/1   [88 chars]
    en  Age, and a fall. Neither of them my business, and I have checked that thought carefully.
    >>  ............................................
    pt  Idade, e uma queda. Nenhum deles assunto meu, e eu conferi esse pensamento com cuidado.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.village.ask_two/2   [76 chars]
    en  One old, one careless. I still went over the pieces afterwards, %1$s. Twice.
    >>  ............................................
    pt  Um velho, um descuidado. Ainda assim revisei as peças depois, %1$s. Duas vezes.
    >>  ............................................
```


### Button `say_thanks` — "Nineteen years and the count holds."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.armorer.village` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.village.say_thanks` — accepted phrasings: "nineteen years and the count holds"
  - the message must contain one of: `nineteen`, `holds`, `count`
  - scored words: `nineteen`(1.5), `holds`(1.2), `count`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.village.respond.say_thanks   [35 chars]
    en  Nineteen years and the count holds.
    >>  ............................................
    pt  Dezenove anos e a conta se mantém.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.armorer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.armorer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Nineteen years and the count holds."
       spoken on: conversations.topic.work.armorer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.village.say_thanks`: the villager accepts. Subject `work.armorer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.village.say_thanks/1   [68 chars]
    en  ...It holds. I'd not have put it that way and now I can't unhear it.
    >>  ............................................
    pt  ...Se mantém. Eu não teria colocado assim e agora não consigo desouvir.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.village.say_thanks/2   [85 chars]
    en  It holds until it doesn't. But aye — it holds, and thank you for counting it with me.
    >>  ............................................
    pt  Se mantém até não se manter. Mas é — se mantém, e obrigado por contar comigo.
    >>  ............................................
```


### Button `ask_price` — "Does anyone pay for all that?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.armorer.village` · offered only once the villager has actually said `work:armorer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.armorer.village.ask_price` — accepted phrasings: "does anyone pay for all that"
  - the message must contain one of: `pay`, `iron`, `cost`
  - scored words: `pay`(1.5), `iron`(1.2), `cost`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.village.respond.ask_price
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.village.respond.ask_price   [29 chars]
    en  Does anyone pay for all that?
    >>  ............................................
    pt  Alguém paga por tudo isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.armorer.village.ask_price`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.armorer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do the dents tell you anything?" | "Steady hands."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.village.ask_price
WHO    VILLAGER — what the player reads after pressing "Does anyone pay for all that?"
       spoken on: conversations.topic.work.armorer.village.respond, button `ask_price`
       leaves the player on: conversations.topic.work.armorer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.village.ask_price`: the villager explains. Subject `work.armorer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.armorer.village.ask_price/1   [87 chars]
    en  It pays for the iron. The rest is a thing I do because the alternative is not doing it.
    >>  ............................................
    pt  Paga o ferro. O resto é algo que eu faço porque a alternativa é não fazer.
    >>  ............................................
  dialogue.conversations.work.prof.armorer.village.ask_price/2   [59 chars]
    en  In coin, poorly. In not having to lock my door, generously.
    >>  ............................................
    pt  Em moeda, mal. Em não precisar trancar minha porta, generosamente.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the fire."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.armorer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.armorer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.armorer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.armorer.village.respond.leave   [34 chars]
    en  I'll let you get back to the fire.
    >>  ............................................
    pt  Vou deixar você voltar ao fogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the fire."
       spoken on: conversations.topic.work.armorer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.left`: the villager accepts. Subject `work.armorer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.armorer.bad_fit.active.respond / leave; conversations.scene.work.armorer.bad_fit.succeeded.respond / leave; conversations.scene.work.armorer.burn.succeeded.respond / leave; conversations.scene.work.armorer.followup / leave; conversations.scene.work.armorer.stalled_commission.blocked.respond / leave; conversations.scene.work.armorer.stalled_commission.succeeded.respond / leave; conversations.topic.work.armorer.craft.respond / leave; conversations.topic.work.armorer.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.armorer.bad_fit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

