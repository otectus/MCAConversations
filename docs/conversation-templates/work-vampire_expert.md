# Work talk with a vampire expert

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.vampire_expert.better_tests.active.respond`](#conversations-scene-work-vampire-expert-better-tests-active-respond)
- [`conversations.scene.work.vampire_expert.better_tests.succeeded.respond`](#conversations-scene-work-vampire-expert-better-tests-succeeded-respond)
- [`conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond`](#conversations-scene-work-vampire-expert-demand-for-certainty-blocked-respond)
- [`conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond`](#conversations-scene-work-vampire-expert-demand-for-certainty-succeeded-respond)
- [`conversations.scene.work.vampire_expert.followup`](#conversations-scene-work-vampire-expert-followup)
- [`conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond`](#conversations-scene-work-vampire-expert-the-case-i-got-wrong-failed-respond)
- [`conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond`](#conversations-scene-work-vampire-expert-the-case-i-got-wrong-remembered-respond)
- [`conversations.topic.work.vampire_expert.craft.respond`](#conversations-topic-work-vampire-expert-craft-respond)
- [`conversations.topic.work.vampire_expert.followup`](#conversations-topic-work-vampire-expert-followup)
- [`conversations.topic.work.vampire_expert.future.respond`](#conversations-topic-work-vampire-expert-future-respond)
- [`conversations.topic.work.vampire_expert.respond`](#conversations-topic-work-vampire-expert-respond)
- [`conversations.topic.work.vampire_expert.risk.respond`](#conversations-topic-work-vampire-expert-risk-respond)
- [`conversations.topic.work.vampire_expert.task.respond`](#conversations-topic-work-vampire-expert-task-respond)
- [`conversations.topic.work.vampire_expert.village.respond`](#conversations-topic-work-vampire-expert-village-respond)

---

## `conversations.scene.work.vampire_expert.better_tests.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.better_tests.active` — e.g. "I am trying to shorten %2$s honestly rather than kindly, and honestly means four years of records before I change anything."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.better_tests.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond   [12 chars]
    en  Your method.
    >>  ............................................
    pt  Seu método.
    >>  ............................................
```


### Button `ask_how_she_would_know` — "How would you know it was safe?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.better_tests.active` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.better_tests.active.ask_how_she_would_know` — accepted phrasings: "how would you know it was safe"; "how would you know it was safe"; "what would prove the shorter period works"
  - the message must contain one of: `safe`, `prove`, `shorter`
  - scored words: `safe`(1.8), `prove`(1.8), `shorter`(1.8), `know`(0.8), `period`(0.8), `works`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond.ask_how_she_would_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.better_tests.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond.ask_how_she_would_know   [31 chars]
    en  How would you know it was safe?
    >>  ............................................
    pt  Como você saberia que é seguro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.vampire_expert.the_tests`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.vampire_expert.better_tests"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active.explained
WHO    VILLAGER — what the player reads after pressing "How would you know it was safe?"
       spoken on: conversations.scene.work.vampire_expert.better_tests.active.respond, button `ask_how_she_would_know`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.better_tests.active.explained`: the villager explains. Subject `work.vampire_expert.the_tests`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.explained/1   [152 chars]
    en  Forty cases where I recorded the four-week reading and then waited the full six anyway. If the four-week reading was right every time, I have something.
    >>  ............................................
    pt  Quarenta casos em que eu anotei a leitura de quatro semanas e esperei as seis completas mesmo assim. Se a leitura de quatro semanas estiver certa toda vez, eu tenho algo.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.explained/2   [138 chars]
    en  It has to be forty and it has to include the ones I expect to be dull, because a test that only looks at frightening cases learns nothing.
    >>  ............................................
    pt  Tem que ser quarenta e tem que incluir os que eu espero que sejam banais, porque um teste que só olha casos assustadores não aprende nada.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.explained/3   [107 chars]
    en  And if it is right thirty-nine times out of forty, the answer is still six weeks. One in forty is a family.
    >>  ............................................
    pt  E se estiver certa trinta e nove vezes em quarenta, a resposta continua sendo seis semanas. Um em quarenta é uma família.
    >>  ............................................
```


### Button `back_the_patience` — "Collect the forty first."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.better_tests.active` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.better_tests.active.back_the_patience` — accepted phrasings: "collect the forty first"; "collect the forty cases first"; "gather the records before changing anything"
  - the message must contain one of: `forty`, `records`, `gather`
  - scored words: `forty`(1.8), `records`(1.8), `gather`(1.8), `collect`(0.8), `first`(0.8), `cases`(0.8), `before`(0.8), `changing`(0.8), `anything`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond.back_the_patience
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.better_tests.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond.back_the_patience   [24 chars]
    en  Collect the forty first.
    >>  ............................................
    pt  Reúna os quarenta primeiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4  _(recorded under topic `work.vampire_expert.the_tests`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.vampire_expert.better_tests"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active.accepted
WHO    VILLAGER — what the player reads after pressing "Collect the forty first."
       spoken on: conversations.scene.work.vampire_expert.better_tests.active.respond, button `back_the_patience`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.better_tests.active.accepted`: the villager accepts. Subject `work.vampire_expert.the_tests`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.accepted/1   [112 chars]
    en  Four years at ten cases a year. I will be fifty-one when I know, and the alternative is guessing at forty-seven.
    >>  ............................................
    pt  Quatro anos a dez casos por ano. Vou ter cinquenta e um quando souber, e a alternativa é chutar aos quarenta e sete.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.accepted/2   [135 chars]
    en  Yes. And I will publish it either way, including if it says six weeks was right all along, which is the outcome I am quietly expecting.
    >>  ............................................
    pt  Sim. E vou publicar de qualquer jeito, inclusive se disser que seis semanas estava certo desde sempre, que é o resultado que eu discretamente espero.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.accepted/3   [145 chars]
    en  Everybody in this field shortens the period at some point on a feeling. I am trying to be the one who does it on a record, and it is much slower.
    >>  ............................................
    pt  Todo mundo nesta área encurta o período em algum momento por pressentimento. Eu tento ser quem faz isso com registro, e é muito mais lento.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `work.vampire_expert.better_tests.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.better_tests.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active.respond.leave   [38 chars]
    en  I'll let you get back to your records.
    >>  ............................................
    pt  Vou deixar você voltar aos seus registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your records."
       spoken on: conversations.scene.work.vampire_expert.better_tests.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.vampire_expert.leave/1   [41 chars]
    en  Please. And close the shutter behind you.
    >>  ............................................
    pt  Por favor. E feche a veneziana ao sair.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.leave/2   [33 chars]
    en  Aye. Not at noon next time, %1$s.
    >>  ............................................
    pt  É. Da próxima vez não ao meio-dia, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.vampire_expert.better_tests.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.better_tests.succeeded` — e.g. "Forty cases. %2$s stays at six weeks, because two of the forty would have been called wrong at four, and two is two families."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.better_tests.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.respond   [13 chars]
    en  Your records.
    >>  ............................................
    pt  Seus registros.
    >>  ............................................
```


### Button `note_the_negative_result` — "A result that changes nothing is still a result."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.better_tests.succeeded` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.better_tests.succeeded.note_the_negative_result` — accepted phrasings: "a result that changes nothing is still a result"; "a result that changes nothing is still a result"; "confirming the old method is worth knowing"
  - the message must contain one of: `result`, `confirming`, `method`
  - scored words: `result`(1.8), `confirming`(1.8), `method`(1.8), `changes`(0.8), `nothing`(0.8), `still`(0.8), `old`(0.8), `worth`(0.8), `knowing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.respond.note_the_negative_result
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.better_tests.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.respond.note_the_negative_result   [48 chars]
    en  A result that changes nothing is still a result.
    >>  ............................................
    pt  Um resultado que não muda nada ainda é resultado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.vampire_expert.the_tests`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.vampire_expert.better_tests"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "A result that changes nothing is still a result."
       spoken on: conversations.scene.work.vampire_expert.better_tests.succeeded.respond, button `note_the_negative_result`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.better_tests.succeeded.acknowledged`: the villager accepts. Subject `work.vampire_expert.the_tests`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.acknowledged/1   [139 chars]
    en  It is the result that never gets written down anywhere, which is why every generation shortens the period again and finds out the hard way.
    >>  ............................................
    pt  É o resultado que nunca é anotado em lugar nenhum, e por isso cada geração encurta o período de novo e descobre do jeito difícil.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.acknowledged/2   [131 chars]
    en  Thank you. Four years to prove that nothing should change is a difficult thing to be proud of and I have decided to be proud of it.
    >>  ............................................
    pt  Obrigada. Quatro anos para provar que nada deve mudar é uma coisa difícil de se ter orgulho, e eu decidi ter orgulho.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.acknowledged/3   [139 chars]
    en  The person who stopped using four weeks is the entire return on it. One colleague, one habit, and a number of families who will never know.
    >>  ............................................
    pt  A pessoa que parou de usar quatro semanas é o retorno inteiro. Uma colega, um hábito, e um número de famílias que nunca vão saber.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `work.vampire_expert.better_tests.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.better_tests.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded.respond.leave   [38 chars]
    en  I'll let you get back to your records.
    >>  ............................................
    pt  Vou deixar você voltar aos seus registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your records."
       spoken on: conversations.scene.work.vampire_expert.better_tests.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.demand_for_certainty.blocked` — e.g. "%2$s came to me about %3$s and what they want is a yes or a no, and what I have is four possibilities and a waiting period."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond   [9 chars]
    en  The case.
    >>  ............................................
    pt  O caso.
    >>  ............................................
```


### Button `ask_about_the_tests` — "What do the tests involve?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.demand_for_certainty.blocked` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.demand_for_certainty.blocked.ask_about_the_tests` — accepted phrasings: "what do the tests involve"; "what do the tests involve"; "how does the testing work"
  - the message must contain one of: `tests`, `testing`
  - scored words: `tests`(1.8), `testing`(1.8), `involve`(0.8), `does`(0.8), `work`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.ask_about_the_tests
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.ask_about_the_tests   [26 chars]
    en  What do the tests involve?
    >>  ............................................
    pt  O que os testes envolvem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.vampire_expert.families`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.vampire_expert.demand_for_certainty"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What do the tests involve?"
       spoken on: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond, button `ask_about_the_tests`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.demand_for_certainty.blocked.explained`: the villager explains. Subject `work.vampire_expert.families`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.explained/1   [139 chars]
    en  Time, mostly. %2$s that is one of the dull four resolves within six weeks, and one that is not does not, and that is the whole of the test.
    >>  ............................................
    pt  Tempo, principalmente. %2$s que seja uma das quatro banais se resolve em seis semanas, e a que não for, não se resolve, e é esse o teste inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.explained/2   [114 chars]
    en  Observation and elimination. I rule out the ordinary things one at a time, and each one takes as long as it takes.
    >>  ............................................
    pt  Observação e eliminação. Eu descarto as coisas comuns uma por vez, e cada uma leva o tempo que leva.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.explained/3   [161 chars]
    en  There is no single test that answers it in a morning. Everybody wants there to be, and the people who claim to have one are the reason this field has a bad name.
    >>  ............................................
    pt  Não existe um teste único que responda numa manhã. Todo mundo quer que exista, e quem alega ter um é o motivo de esta área ter má fama.
    >>  ............................................
```


### Button `offer_bottles` — "I'll bring you bottles for the samples."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.vampire_expert.demand_for_certainty.blocked` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.demand_for_certainty.blocked.offer_bottles` — accepted phrasings: "ill bring you bottles for the samples"; "i can bring you bottles for samples"; "let me fetch bottles for that"
  - the message must contain one of: `bottles`, `bottle`
  - scored words: `bottles`(1.8), `bottle`(1.8), `ill`(0.8), `bring`(0.8), `samples`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.offer_bottles
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.offer_bottles   [39 chars]
    en  I'll bring you bottles for the samples.
    >>  ............................................
    pt  Vou trazer frascos para as amostras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.vampire_expert.case.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.vampire_expert.families`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.demand_for_certainty", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.vampire_expert.demand_for_certainty", "obligation": "commitment:work.vampire_expert.bring_bottles"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.vampire_expert.bring_bottles"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you bottles for the samples."
       spoken on: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond, button `offer_bottles`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.demand_for_certainty.blocked.accepted`: the villager accepts. Subject `work.vampire_expert.families`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.accepted/1   [128 chars]
    en  Then I can run the eliminations properly instead of by memory, and %2$s gets an answer six weeks from Tuesday rather than never.
    >>  ............................................
    pt  Então eu posso fazer as eliminações direito em vez de de memória, e %2$s tem uma resposta seis semanas a partir de terça, em vez de nunca.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.accepted/2   [134 chars]
    en  It sounds like a small thing to bring. It is the difference between a record and a recollection, and only one of those can be checked.
    >>  ............................................
    pt  Parece pouca coisa de se trazer. É a diferença entre um registro e uma lembrança, e só uma das duas pode ser conferida.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.accepted/3   [115 chars]
    en  Yes. And I will label every one with a date, so that if I am wrong somebody can find out exactly where I was wrong.
    >>  ............................................
    pt  Sim. E vou etiquetar cada um com data, para que, se eu errar, alguém possa descobrir exatamente onde eu errei.
    >>  ............................................
```


### Button `back_the_waiting` — "Make them wait the six weeks."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.demand_for_certainty.blocked` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.demand_for_certainty.blocked.back_the_waiting` — accepted phrasings: "make them wait the six weeks"; "make them wait the six weeks"; "hold to the waiting period"
  - the message must contain one of: `weeks`, `waiting`
  - scored words: `weeks`(1.8), `waiting`(1.8), `make`(0.8), `wait`(0.8), `six`(0.8), `hold`(0.8), `period`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.back_the_waiting
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.back_the_waiting   [29 chars]
    en  Make them wait the six weeks.
    >>  ............................................
    pt  Faça-os esperar as seis semanas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.vampire_expert.families`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.vampire_expert.demand_for_certainty"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Make them wait the six weeks."
       spoken on: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond, button `back_the_waiting`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.demand_for_certainty.blocked.steadied`: the villager accepts. Subject `work.vampire_expert.families`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.steadied/1   [114 chars]
    en  Six weeks, and I will visit every week so that the waiting has a shape and is not just a hole they are sitting in.
    >>  ............................................
    pt  Seis semanas, e eu visito toda semana para que a espera tenha um formato e não seja só um buraco em que estão sentados.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.steadied/2   [115 chars]
    en  Yes. The one time I shortened it, to be kind, I was wrong, and being kind for four days cost a family eleven years.
    >>  ............................................
    pt  Sim. A única vez em que eu encurtei, por bondade, eu errei, e ser bondosa por quatro dias custou onze anos a uma família.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.steadied/3   [145 chars]
    en  Thank you. Holding a waiting period against a frightened family is the hardest thing this work asks and nobody who has not done it believes that.
    >>  ............................................
    pt  Obrigada. Sustentar um período de espera diante de uma família assustada é a coisa mais difícil que este trabalho pede, e quem nunca fez não acredita.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `work.vampire_expert.demand_for_certainty.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond.leave   [38 chars]
    en  I'll let you get back to your records.
    >>  ............................................
    pt  Vou deixar você voltar aos seus registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your records."
       spoken on: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.demand_for_certainty.succeeded` — e.g. "Six weeks and it was the dullest of the four. %2$s is well and I have never seen anybody so tired by good news."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond   [10 chars]
    en  That case.
    >>  ............................................
    pt  Aquele caso.
    >>  ............................................
```


### Button `ask_about_the_visits` — "Why visit every week?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.vampire_expert.demand_for_certainty.succeeded` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.demand_for_certainty.succeeded.ask_about_the_visits` — accepted phrasings: "why visit every week"; "why visit every week"; "what do the weekly visits do"
  - the message must contain one of: `visit`, `visits`, `weekly`
  - scored words: `visit`(1.8), `visits`(1.8), `weekly`(1.8), `why`(0.8), `every`(0.8), `week`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond.ask_about_the_visits
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond.ask_about_the_visits   [21 chars]
    en  Why visit every week?
    >>  ............................................
    pt  Por que visitar toda semana?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `work.vampire_expert.families`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.vampire_expert.demand_for_certainty"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Why visit every week?"
       spoken on: conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond, button `ask_about_the_visits`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.demand_for_certainty.succeeded.answered`: the villager explains. Subject `work.vampire_expert.families`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.answered/1   [134 chars]
    en  Because a waiting period without visits is abandonment with a technical name on it, and I have been on the receiving end of that once.
    >>  ............................................
    pt  Porque um período de espera sem visitas é abandono com nome técnico, e eu já estive do lado de quem recebe isso uma vez.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.answered/2   [119 chars]
    en  It also lets me see the change week by week instead of at the end, which is better diagnosis as well as better manners.
    >>  ............................................
    pt  Também me deixa ver a mudança semana a semana em vez de só no fim, o que é diagnóstico melhor além de educação melhor.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.answered/3   [113 chars]
    en  And it stops them going to somebody who will give them an answer in a morning. There is always somebody who will.
    >>  ............................................
    pt  E impede que procurem alguém que dê uma resposta numa manhã. Sempre tem alguém que dá.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `work.vampire_expert.demand_for_certainty.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond.leave   [38 chars]
    en  I'll let you get back to your records.
    >>  ............................................
    pt  Vou deixar você voltar aos seus registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your records."
       spoken on: conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.vampire_expert.followup`

**Reached from 10 route(s):** `conversations.scene.work.vampire_expert.better_tests.active.respond` / `ask_how_she_would_know`; `conversations.scene.work.vampire_expert.better_tests.active.respond` / `back_the_patience`; `conversations.scene.work.vampire_expert.better_tests.succeeded.respond` / `note_the_negative_result`; `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond` / `ask_about_the_tests`; `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond` / `offer_bottles`; `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond` / `back_the_waiting`; `conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond` / `ask_about_the_visits`; `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond` / `ask_what_changed`; `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond` / `acknowledge_the_weight`; `conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond` / `note_the_teaching`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.better_tests.active.accepted` — e.g. "Four years at ten cases a year. I will be fifty-one when I know, and the alternative is guessing at forty-seven."
- `conversations.scene.work.vampire_expert.better_tests.active.explained` — e.g. "Forty cases where I recorded the four-week reading and then waited the full six anyway. If the four-week reading was right every time, I have something."
- `conversations.scene.work.vampire_expert.better_tests.succeeded.acknowledged` — e.g. "It is the result that never gets written down anywhere, which is why every generation shortens the period again and finds out the hard way."
- `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.accepted` — e.g. "Then I can run the eliminations properly instead of by memory, and %2$s gets an answer six weeks from Tuesday rather than never."
- `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.explained` — e.g. "Time, mostly. %2$s that is one of the dull four resolves within six weeks, and one that is not does not, and that is the whole of the test."
- `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.steadied` — e.g. "Six weeks, and I will visit every week so that the waiting has a shape and is not just a hole they are sitting in."
- `conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.answered` — e.g. "Because a waiting period without visits is abandonment with a technical name on it, and I have been on the receiving end of that once."
- `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.answered` — e.g. "I say the number now. Not likely — four in five, or one in nine. A number can be argued with and a certainty cannot."
- `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.steadied` — e.g. "I intend to. Putting it down would mean the waiting period is negotiable again, and it is the only thing keeping it fixed."
- `conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.acknowledged` — e.g. "Two, that I know of. It is a poor exchange for one family and it is the only exchange that was ever on offer."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.followup   [17 chars]
    en  Another question?
    >>  ............................................
    pt  Outra pergunta?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a wrong diagnosis?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.vampire_expert.*` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.followup.ask_more` — accepted phrasings: "whats the hardest part of a wrong diagnosis"; "what is the hardest part of a wrong diagnosis"; "hardest thing about getting a diagnosis wrong"
  - the message must contain one of: `hardest`, `diagnosis`
  - scored words: `hardest`(1.8), `diagnosis`(1.8), `whats`(0.8), `part`(0.8), `wrong`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.followup.ask_more   [45 chars]
    en  What's the hardest part of a wrong diagnosis?
    >>  ............................................
    pt  Qual é a parte mais difícil de um diagnóstico errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a wrong diagnosis?"
       spoken on: conversations.scene.work.vampire_expert.followup, button `ask_more`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.hard`: the villager explains. Subject `work.vampire_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.vampire_expert.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.vampire_expert.hard/1   [93 chars]
    en  At anything that would make a client better at hurting somebody. That line is not negotiable.
    >>  ............................................
    pt  Em qualquer coisa que fizesse um cliente ficar melhor em machucar alguém. Essa linha não se negocia.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.hard/2   [77 chars]
    en  Sooner than clients want, %1$s. Refusing well is the skill nobody advertises.
    >>  ............................................
    pt  Antes do que os clientes querem, %1$s. Recusar bem é a habilidade que ninguém anuncia.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.vampire_expert.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.followup.leave   [31 chars]
    en  I'll leave you to your records.
    >>  ............................................
    pt  Vou deixar você com os registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your records."
       spoken on: conversations.scene.work.vampire_expert.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed` — e.g. "There was %2$s. I shortened the waiting period because a family was suffering, and I was wrong, and they left the village."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond   [22 chars]
    en  The one you got wrong.
    >>  ............................................
    pt  O que você errou.
    >>  ............................................
```


### Button `ask_what_changed` — "What changed in your practice?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.vampire_expert.the_case_i_got_wrong.failed` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.the_case_i_got_wrong.failed.ask_what_changed` — accepted phrasings: "what changed in your practice"; "what changed in your practice"; "what did you do differently afterwards"
  - the message must contain one of: `practice`, `differently`
  - scored words: `practice`(1.8), `differently`(1.8), `changed`(0.8), `afterwards`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond.ask_what_changed   [30 chars]
    en  What changed in your practice?
    >>  ............................................
    pt  O que mudou na sua prática?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.vampire_expert.misdiagnosis`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.vampire_expert.the_case_i_got_wrong"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.answered
WHO    VILLAGER — what the player reads after pressing "What changed in your practice?"
       spoken on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.the_case_i_got_wrong.failed.answered`: the villager explains. Subject `work.vampire_expert.misdiagnosis`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.answered/1   [116 chars]
    en  I say the number now. Not likely — four in five, or one in nine. A number can be argued with and a certainty cannot.
    >>  ............................................
    pt  Agora eu digo o número. Não provável — quatro em cinco, ou um em nove. Um número pode ser contestado e uma certeza não.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.answered/2   [119 chars]
    en  The waiting period stopped being negotiable, and I lost two clients over it in the first year and have lost none since.
    >>  ............................................
    pt  O período de espera deixou de ser negociável, e eu perdi dois clientes por isso no primeiro ano e nenhum desde então.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.answered/3   [120 chars]
    en  I wrote it in the front of the book, with their name and mine. Every apprentice reads it before they read anything else.
    >>  ............................................
    pt  Escrevi na frente do livro, com o nome deles e o meu. Toda aprendiz lê isso antes de ler qualquer outra coisa.
    >>  ............................................
```


### Button `acknowledge_the_weight` — "You've carried that a long time."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.the_case_i_got_wrong.failed` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.the_case_i_got_wrong.failed.acknowledge_the_weight` — accepted phrasings: "youve carried that a long time"; "you have carried that a long time"; "eleven years is a long time to carry it"
  - the message must contain one of: `carried`, `carry`, `years`
  - scored words: `carried`(1.8), `carry`(1.8), `years`(1.8), `youve`(0.8), `long`(0.8), `time`(0.8), `eleven`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond.acknowledge_the_weight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond.acknowledge_the_weight   [32 chars]
    en  You've carried that a long time.
    >>  ............................................
    pt  Você carrega isso há muito tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.vampire_expert.error.seen`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `work.vampire_expert.misdiagnosis`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.vampire_expert.the_case_i_got_wrong"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.steadied
WHO    VILLAGER — what the player reads after pressing "You've carried that a long time."
       spoken on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond, button `acknowledge_the_weight`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.the_case_i_got_wrong.failed.steadied`: the villager accepts. Subject `work.vampire_expert.misdiagnosis`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.steadied/1   [122 chars]
    en  I intend to. Putting it down would mean the waiting period is negotiable again, and it is the only thing keeping it fixed.
    >>  ............................................
    pt  Pretendo carregar. Largar significaria que o período de espera volta a ser negociável, e é a única coisa que o mantém firme.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.steadied/2   [137 chars]
    en  Thank you. People either tell me it was a long time ago or that anybody would have done the same, and neither of those is a thing I want.
    >>  ............................................
    pt  Obrigada. As pessoas me dizem que foi há muito tempo ou que qualquer um teria feito o mesmo, e eu não quero nenhuma das duas.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.steadied/3   [138 chars]
    en  I wrote to them once, four years after. There was no reply and there did not need to be. The letter was for the record, not for an answer.
    >>  ............................................
    pt  Escrevi a eles uma vez, quatro anos depois. Não houve resposta e não precisava haver. A carta era para o registro, não para uma resposta.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `work.vampire_expert.the_case_i_got_wrong.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond.leave   [38 chars]
    en  I'll let you get back to your records.
    >>  ............................................
    pt  Vou deixar você voltar aos seus registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your records."
       spoken on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered` — e.g. "Two people in this field now hold a fixed waiting period because of a story I tell about myself, and that is the only good that has come of it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond   [24 chars]
    en  That case, looking back.
    >>  ............................................
    pt  Aquele caso, olhando para trás.
    >>  ............................................
```


### Button `note_the_teaching` — "Two others hold the line because of you."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.the_case_i_got_wrong.remembered` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.vampire_expert.the_case_i_got_wrong.remembered.note_the_teaching` — accepted phrasings: "two others hold the line because of you"; "two others hold the line because of you"; "others practise differently because you told them"
  - the message must contain one of: `others`, `practise`, `line`
  - scored words: `others`(1.8), `practise`(1.8), `line`(1.8), `two`(0.8), `hold`(0.8), `because`(0.8), `differently`(0.8), `told`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond.note_the_teaching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond.note_the_teaching   [40 chars]
    en  Two others hold the line because of you.
    >>  ............................................
    pt  Duas outras pessoas mantêm isso por sua causa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.vampire_expert.misdiagnosis`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.vampire_expert.the_case_i_got_wrong"}
- Then opens: `conversations.scene.work.vampire_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a wrong diagnosis?" | "I'll leave you to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.acknowledged
WHO    VILLAGER — what the player reads after pressing "Two others hold the line because of you."
       spoken on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond, button `note_the_teaching`
       leaves the player on: conversations.scene.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.the_case_i_got_wrong.remembered.acknowledged`: the villager accepts. Subject `work.vampire_expert.misdiagnosis`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.acknowledged/1   [109 chars]
    en  Two, that I know of. It is a poor exchange for one family and it is the only exchange that was ever on offer.
    >>  ............................................
    pt  Duas, que eu saiba. É uma troca ruim por uma família e é a única troca que jamais esteve em oferta.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.acknowledged/2   [131 chars]
    en  Thank you. I tell it badly on purpose, without any lesson at the end, because a tidy story is easier to forget than an awkward one.
    >>  ............................................
    pt  Obrigada. Conto mal de propósito, sem lição no fim, porque uma história bem-arrumada é mais fácil de esquecer do que uma constrangedora.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.acknowledged/3   [138 chars]
    en  The useful part is the four days. Not the diagnosis, not the family leaving — the four days I took off the waiting because somebody cried.
    >>  ............................................
    pt  A parte útil são os quatro dias. Não o diagnóstico, não a família indo embora — os quatro dias que eu tirei da espera porque alguém chorou.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your records."

*stance family `exit` · tone `plain` · answers the beat(s) `work.vampire_expert.the_case_i_got_wrong.remembered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond.leave   [38 chars]
    en  I'll let you get back to your records.
    >>  ............................................
    pt  Vou deixar você voltar aos seus registros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your records."
       spoken on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert.craft` — e.g. "I know exactly what I am and exactly what I'm not, and almost everything I do is correcting the second part."


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.craft.respond   [24 chars]
    en  That's how it was built.
    >>  ............................................
    pt  Foi assim que se construiu.
    >>  ............................................
```


### Button `ask_not` — "What are you not?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.craft` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.craft.ask_not` — accepted phrasings: "what are you not"
  - the message must contain one of: `correct`, `myths`
  - scored words: `not`(0.6), `correct`(1.5), `myths`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.craft.respond.ask_not
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.craft.respond.ask_not   [17 chars]
    en  What are you not?
    >>  ............................................
    pt  O que você não é?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.craft.ask_not`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.craft.ask_not
WHO    VILLAGER — what the player reads after pressing "What are you not?"
       spoken on: conversations.topic.work.vampire_expert.craft.respond, button `ask_not`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.craft.ask_not`: the villager explains. Subject `work.vampire_expert.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.craft.ask_not/1   [94 chars]
    en  Most of it. Nine of the ten things a frightened person names, I am not, and the tenth is dull.
    >>  ............................................
    pt  Quase tudo. Nove das dez coisas que um assustado nomeia, eu não sou, e a décima é sem graça.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.craft.ask_not/2   [98 chars]
    en  Contagious, chiefly. That's the correction that matters and it's the one that takes longest, %1$s.
    >>  ............................................
    pt  Contagioso, principalmente. É a correção que importa e é a que leva mais tempo, %1$s.
    >>  ............................................
```


### Button `admire` — "Assembling it from letters is a real body of knowledge."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.craft` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.craft.admire` — accepted phrasings: "assembling it from letters is a real body of knowledge"
  - the message must contain one of: `assembling`, `letters`, `knowledge`
  - scored words: `assembling`(1.5), `letters`(1.0), `knowledge`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.craft.respond.admire   [55 chars]
    en  Assembling it from letters is a real body of knowledge.
    >>  ............................................
    pt  Montar isso a partir de cartas é um corpo de conhecimento real.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.vampire_expert.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.vampire_expert.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.craft.admire
WHO    VILLAGER — what the player reads after pressing "Assembling it from letters is a real body of knowledge."
       spoken on: conversations.topic.work.vampire_expert.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.craft.admire`: the villager accepts. Subject `work.vampire_expert.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.craft.admire/1   [107 chars]
    en  It's forty people's ordinary days, written badly, over nineteen years. And yes, it's the only one there is.
    >>  ............................................
    pt  São os dias comuns de quarenta pessoas, mal escritos, ao longo de dezenove anos. E sim, é o único que existe.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.craft.admire/2   [98 chars]
    en  The scribe called it that. He's the only person who's ever used the word knowledge about it, %1$s.
    >>  ............................................
    pt  O escriba chamou assim. É a única pessoa que já usou a palavra conhecimento pra isso, %1$s.
    >>  ............................................
```


### Button `ask_forty` — "Forty people write to you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.craft` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.craft.ask_forty` — accepted phrasings: "forty people write to you"
  - the message must contain one of: `forty`, `write`, `correspondents`
  - scored words: `forty`(1.5), `write`(1.2), `correspondents`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.craft.respond.ask_forty
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.craft.respond.ask_forty   [26 chars]
    en  Forty people write to you?
    >>  ............................................
    pt  Quarenta pessoas te escrevem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.craft.ask_forty`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.craft.ask_forty
WHO    VILLAGER — what the player reads after pressing "Forty people write to you?"
       spoken on: conversations.topic.work.vampire_expert.craft.respond, button `ask_forty`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.craft.ask_forty`: the villager explains. Subject `work.vampire_expert.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.craft.ask_forty/1   [104 chars]
    en  Over nineteen years. Eleven still do. The rest either settled somewhere or found it easier to be silent.
    >>  ............................................
    pt  Ao longo de dezenove anos. Onze ainda escrevem. O resto ou se estabeleceu ou achou mais fácil calar.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.craft.ask_forty/2   [105 chars]
    en  Forty who've written once. Four who write every season, %1$s, and those four are the whole of my company.
    >>  ............................................
    pt  Quarenta que escreveram uma vez. Quatro que escrevem toda estação, %1$s, e esses quatro são toda a minha companhia.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.craft.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.vampire_expert.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.followup`

**Reached from 20 route(s):** `conversations.scene.work.vampire_expert.followup` / `ask_more`; `conversations.topic.work.vampire_expert.craft.respond` / `ask_not`; `conversations.topic.work.vampire_expert.craft.respond` / `admire`; `conversations.topic.work.vampire_expert.craft.respond` / `ask_forty`; `conversations.topic.work.vampire_expert.future.respond` / `ask_four_letters`; `conversations.topic.work.vampire_expert.future.respond` / `encourage`; `conversations.topic.work.vampire_expert.future.respond` / `ask_bag_future`; `conversations.topic.work.vampire_expert.respond` / `ask_hard`; `conversations.topic.work.vampire_expert.respond` / `value`; `conversations.topic.work.vampire_expert.respond` / `challenge`; `conversations.topic.work.vampire_expert.respond` / `challenge`; `conversations.topic.work.vampire_expert.risk.respond` / `ask_bag` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert.challenge.landed` — e.g. "There isn't a side. There are people with a condition and people frightened of them."
- `conversations.work.prof.vampire_expert.challenge.stung` — e.g. "...Name one person harmed by my advice. I'll wait."
- `conversations.work.prof.vampire_expert.craft.admire` — e.g. "It's forty people's ordinary days, written badly, over nineteen years. And yes, it's the only one there is."
- `conversations.work.prof.vampire_expert.craft.ask_forty` — e.g. "Over nineteen years. Eleven still do. The rest either settled somewhere or found it easier to be silent."
- `conversations.work.prof.vampire_expert.craft.ask_not` — e.g. "Most of it. Nine of the ten things a frightened person names, I am not, and the tenth is dull."
- `conversations.work.prof.vampire_expert.future.ask_bag_future` — e.g. "A year in which I don't reach for it. I've had eleven months twice and I've never had the twelfth."
- `conversations.work.prof.vampire_expert.future.ask_four_letters` — e.g. "Because they're the only evidence that any of us lived ordinarily. Every other account was written by somebody else."
- `conversations.work.prof.vampire_expert.future.encourage` — e.g. "...With his. Sealed, unread, and four valleys from anybody who'd know a name in them. That could work."
- `conversations.work.prof.vampire_expert.hard` — e.g. "At anything that would make a client better at hurting somebody. That line is not negotiable."
- `conversations.work.prof.vampire_expert.risk.ask_bag` — e.g. "Twice. Both times the priest stood in a doorway and both times I found out afterwards, not during."
- `conversations.work.prof.vampire_expert.risk.ask_burn` — e.g. "Every one, the day I answer it. Nineteen years and there is nothing in this house that could ruin anybody."
- `conversations.work.prof.vampire_expert.risk.sympathise` — e.g. "...It is, and it's why I burn them, and burning them is why there's no record of any of this."
- `conversations.work.prof.vampire_expert.task.ask_before` — e.g. "It's the first line of every letter for nineteen years. I've stopped resenting it and I've never stopped noticing."
- `conversations.work.prof.vampire_expert.task.ask_letters` — e.g. "How to live next to somebody without either of them pretending. That's the only question worth four pages."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.followup   [32 chars]
    en  That's the practice, discreetly.
    >>  ............................................
    pt  É a prática, discretamente.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.challenge.landed`, `work.vampire_expert.challenge.stung`, `work.vampire_expert.craft.admire`, `work.vampire_expert.craft.ask_forty`, `work.vampire_expert.craft.ask_not`, `work.vampire_expert.future.ask_bag_future`, `work.vampire_expert.future.ask_four_letters`, `work.vampire_expert.future.encourage`, `work.vampire_expert.hard`, `work.vampire_expert.risk.ask_bag`, `work.vampire_expert.risk.ask_burn`, `work.vampire_expert.risk.sympathise`, `work.vampire_expert.task.ask_before`, `work.vampire_expert.task.ask_letters`, `work.vampire_expert.task.offer_hands`, `work.vampire_expert.value`, `work.vampire_expert.village.ask_furniture`, `work.vampire_expert.village.ask_prove`, `work.vampire_expert.village.say_thanks` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.vampire_expert.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `discreet`
  - scored words: `thought`(1.2), `discreet`(1.5), `trade`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.vampire_expert.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.vampire_expert.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.vampire_expert.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.vampire_expert.thanks`: the villager accepts. Subject `work.vampire_expert.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.thanks/1   [74 chars]
    en  Almost nobody has. It is a trade that survives on not being thought about.
    >>  ............................................
    pt  Quase ninguém pensou. É um ofício que sobrevive de não ser pensado.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.thanks/2   [71 chars]
    en  You asked without deciding first, %1$s. That is rarer than you'd think.
    >>  ............................................
    pt  Você perguntou sem já ter decidido, %1$s. Isso é mais raro do que parece.
    >>  ............................................
```


### Button `ask_more` — "What's the case you still think about?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.challenge.landed`, `work.vampire_expert.challenge.stung`, `work.vampire_expert.craft.admire`, `work.vampire_expert.craft.ask_forty`, `work.vampire_expert.craft.ask_not`, `work.vampire_expert.future.ask_bag_future`, `work.vampire_expert.future.ask_four_letters`, `work.vampire_expert.future.encourage`, `work.vampire_expert.hard`, `work.vampire_expert.risk.ask_bag`, `work.vampire_expert.risk.ask_burn`, `work.vampire_expert.risk.sympathise`, `work.vampire_expert.task.ask_before`, `work.vampire_expert.task.ask_letters`, `work.vampire_expert.task.offer_hands`, `work.vampire_expert.value`, `work.vampire_expert.village.ask_furniture`, `work.vampire_expert.village.ask_prove`, `work.vampire_expert.village.say_thanks` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.vampire_expert.more` — accepted phrasings: "what's the case you still think about"
  - the message must contain one of: `case`, `remember`, `client`
  - scored words: `case`(1.5), `remember`(1.2), `client`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.followup.ask_more   [38 chars]
    en  What's the case you still think about?
    >>  ............................................
    pt  Qual caso você ainda remoí?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.vampire_expert.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.more
WHO    VILLAGER — what the player reads after pressing "What's the case you still think about?"
       spoken on: conversations.topic.work.vampire_expert.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.vampire_expert.more`: the villager discloses. Subject `work.vampire_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.more/1   [81 chars]
    en  A young one who did everything right and was driven out anyway. I keep the notes.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as anotações.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.more/2   [78 chars]
    en  One I turned away. He found worse advice elsewhere, and I hear about it still.
    >>  ............................................
    pt  Um que eu recusei. Ele achou conselho pior em outro lugar, e eu ainda ouço falar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I read those notes when I'm tempted to hope.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu leio essas notas quando fico tentado a ter esperança.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. Burning them would make nineteen years into nothing, and I could not do it.
    >>  ............................................
    pt  Quatro cartas. Queimá-las tornaria dezenove anos em nada, e eu não consegui.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. It happened elsewhere and it took nine years to reach me.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Aconteceu em outro lugar e levou nove anos pra chegar a mim.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters, kept somewhere else. There's no hurry, only the certainty that there will be one.
    >>  ............................................
    pt  Quatro cartas, guardadas em outro lugar. Não há pressa, só a certeza de que vai haver.
    >>  ............................................
  confident.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas.
    >>  ............................................
  confident.dialogue.conversations.work.prof.vampire_expert.more/2
    en  The four letters kept somewhere they'd survive me and never be read by anybody here.
    >>  ............................................
    pt  As quatro cartas guardadas onde me sobrevivessem e nunca fossem lidas por ninguém daqui.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.vampire_expert.more/2
    en  The four letters kept somewhere they'd survive me and never be read by anybody here.
    >>  ............................................
    pt  As quatro cartas guardadas onde me sobrevivessem e nunca fossem lidas por ninguém daqui.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I've kept their letters.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardei as cartas dele.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. They're the only evidence any of us lived ordinarily, and I'd like them to outlast me.
    >>  ............................................
    pt  Quatro cartas. São a única prova de que algum de nós viveu comumente, e eu queria que me sobrevivessem.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I've kept their letters.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardei as cartas dele.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. They're the only evidence any of us lived ordinarily, and I'd like them to outlast me.
    >>  ............................................
    pt  Quatro cartas. São a única prova de que algum de nós viveu comumente, e eu queria que me sobrevivessem.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I've kept their letters.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardei as cartas dele.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. They're the only evidence any of us lived ordinarily, and I'd like them to outlast me.
    >>  ............................................
    pt  Quatro cartas. São a única prova de que algum de nós viveu comumente, e eu queria que me sobrevivessem.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I read those notes when I'm tempted to hope.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu leio essas notas quando fico tentado a ter esperança.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. Burning them would make nineteen years into nothing, and I could not do it.
    >>  ............................................
    pt  Quatro cartas. Queimá-las tornaria dezenove anos em nada, e eu não consegui.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.vampire_expert.more/2
    en  The four letters kept somewhere they'd survive me and never be read by anybody here.
    >>  ............................................
    pt  As quatro cartas guardadas onde me sobrevivessem e nunca fossem lidas por ninguém daqui.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.vampire_expert.more/2
    en  The four letters kept somewhere they'd survive me and never be read by anybody here.
    >>  ............................................
    pt  As quatro cartas guardadas onde me sobrevivessem e nunca fossem lidas por ninguém daqui.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. Driven out anyway. The notes are in the third drawer.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Expulso mesmo assim. As notas estão na terceira gaveta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. Sealed, unread, four valleys away. That is the whole of what I want.
    >>  ............................................
    pt  Quatro cartas. Lacradas, não lidas, a quatro vales. É tudo que eu quero.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. It happened elsewhere and it took nine years to reach me.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Aconteceu em outro lugar e levou nove anos pra chegar a mim.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters, kept somewhere else. There's no hurry, only the certainty that there will be one.
    >>  ............................................
    pt  Quatro cartas, guardadas em outro lugar. Não há pressa, só a certeza de que vai haver.
    >>  ............................................
  odd.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. Driven out anyway. The notes are in the third drawer.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Expulso mesmo assim. As notas estão na terceira gaveta.
    >>  ............................................
  odd.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. Sealed, unread, four valleys away. That is the whole of what I want.
    >>  ............................................
    pt  Quatro cartas. Lacradas, não lidas, a quatro vales. É tudo que eu quero.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. It happened elsewhere and it took nine years to reach me.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Aconteceu em outro lugar e levou nove anos pra chegar a mim.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters, kept somewhere else. There's no hurry, only the certainty that there will be one.
    >>  ............................................
    pt  Quatro cartas, guardadas em outro lugar. Não há pressa, só a certeza de que vai haver.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes. Cheerful trade, mine.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas. Ofício alegre, o meu.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters I'd like kept somewhere safe. Sealed. Unread. Preferably four valleys from anybody.
    >>  ............................................
    pt  Quatro cartas que eu queria guardadas em segurança. Lacradas. Não lidas. De preferência a quatro vales de todos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes. Cheerful trade, mine.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas. Ofício alegre, o meu.
    >>  ............................................
  playful.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters I'd like kept somewhere safe. Sealed. Unread. Preferably four valleys from anybody.
    >>  ............................................
    pt  Quatro cartas que eu queria guardadas em segurança. Lacradas. Não lidas. De preferência a quatro vales de todos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. It happened elsewhere and it took nine years to reach me.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Aconteceu em outro lugar e levou nove anos pra chegar a mim.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters, kept somewhere else. There's no hurry, only the certainty that there will be one.
    >>  ............................................
    pt  Quatro cartas, guardadas em outro lugar. Não há pressa, só a certeza de que vai haver.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I read those notes when I'm tempted to hope.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu leio essas notas quando fico tentado a ter esperança.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. Burning them would make nineteen years into nothing, and I could not do it.
    >>  ............................................
    pt  Quatro cartas. Queimá-las tornaria dezenove anos em nada, e eu não consegui.
    >>  ............................................
  shy.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right. Driven out anyway. The notes are in the third drawer.
    >>  ............................................
    pt  Um jovem que fez tudo certo. Expulso mesmo assim. As notas estão na terceira gaveta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters. Sealed, unread, four valleys away. That is the whole of what I want.
    >>  ............................................
    pt  Quatro cartas. Lacradas, não lidas, a quatro vales. É tudo que eu quero.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes. Cheerful trade, mine.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas. Ofício alegre, o meu.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters I'd like kept somewhere safe. Sealed. Unread. Preferably four valleys from anybody.
    >>  ............................................
    pt  Quatro cartas que eu queria guardadas em segurança. Lacradas. Não lidas. De preferência a quatro vales de todos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.vampire_expert.more/1
    en  A young one who did everything right and was driven out anyway. I keep the notes. Cheerful trade, mine.
    >>  ............................................
    pt  Um jovem que fez tudo certo e foi expulso mesmo assim. Eu guardo as notas. Ofício alegre, o meu.
    >>  ............................................
  witty.dialogue.conversations.work.prof.vampire_expert.more/2
    en  Four letters I'd like kept somewhere safe. Sealed. Unread. Preferably four valleys from anybody.
    >>  ............................................
    pt  Quatro cartas que eu queria guardadas em segurança. Lacradas. Não lidas. De preferência a quatro vales de todos.
    >>  ............................................
```

</details>


### Button `leave` — "Mind the daylight."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.challenge.landed`, `work.vampire_expert.challenge.stung`, `work.vampire_expert.craft.admire`, `work.vampire_expert.craft.ask_forty`, `work.vampire_expert.craft.ask_not`, `work.vampire_expert.future.ask_bag_future`, `work.vampire_expert.future.ask_four_letters`, `work.vampire_expert.future.encourage`, `work.vampire_expert.hard`, `work.vampire_expert.risk.ask_bag`, `work.vampire_expert.risk.ask_burn`, `work.vampire_expert.risk.sympathise`, `work.vampire_expert.task.ask_before`, `work.vampire_expert.task.ask_letters`, `work.vampire_expert.task.offer_hands`, `work.vampire_expert.value`, `work.vampire_expert.village.ask_furniture`, `work.vampire_expert.village.ask_prove`, `work.vampire_expert.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.followup.leave   [18 chars]
    en  Mind the daylight.
    >>  ............................................
    pt  Cuidado com a luz do dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "Mind the daylight."
       spoken on: conversations.topic.work.vampire_expert.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert.future` — e.g. "I'd like the four letters kept somewhere they'd survive me and never be read by anybody here."


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.future.respond   [29 chars]
    en  That's what's left to settle.
    >>  ............................................
    pt  É o que falta resolver.
    >>  ............................................
```


### Button `ask_four_letters` — "Why keep those four?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.future` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.future.ask_four_letters` — accepted phrasings: "why keep those four"
  - the message must contain one of: `four`, `letters`, `evidence`
  - scored words: `four`(1.2), `letters`(1.2), `evidence`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.future.respond.ask_four_letters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.future.respond.ask_four_letters   [20 chars]
    en  Why keep those four?
    >>  ............................................
    pt  Por que guardar essas quatro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.future.ask_four_letters`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.future.ask_four_letters
WHO    VILLAGER — what the player reads after pressing "Why keep those four?"
       spoken on: conversations.topic.work.vampire_expert.future.respond, button `ask_four_letters`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.future.ask_four_letters`: the villager explains. Subject `work.vampire_expert.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.future.ask_four_letters/1   [116 chars]
    en  Because they're the only evidence that any of us lived ordinarily. Every other account was written by somebody else.
    >>  ............................................
    pt  Porque são a única prova de que algum de nós viveu comumente. Todo outro relato foi escrito por outra pessoa.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.future.ask_four_letters/2   [89 chars]
    en  Because burning them would make nineteen years into nothing, %1$s, and I could not do it.
    >>  ............................................
    pt  Porque queimá-las tornaria dezenove anos em nada, %1$s, e eu não consegui.
    >>  ............................................
```


### Button `encourage` — "The scribe sends a copy four valleys away. Send them with it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.future` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.future.encourage` — accepted phrasings: "the scribe sends a copy four valleys away. send them with it"
  - the message must contain one of: `scribe`, `send`, `sealed`
  - scored words: `scribe`(1.5), `send`(1.2), `sealed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.future.respond.encourage   [61 chars]
    en  The scribe sends a copy four valleys away. Send them with it.
    >>  ............................................
    pt  O escriba manda uma cópia a quatro vales. Mande com ela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.vampire_expert.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.vampire_expert.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.future.encourage
WHO    VILLAGER — what the player reads after pressing "The scribe sends a copy four valleys away. Send them with it."
       spoken on: conversations.topic.work.vampire_expert.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.future.encourage`: the villager accepts. Subject `work.vampire_expert.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.future.encourage/1   [102 chars]
    en  ...With his. Sealed, unread, and four valleys from anybody who'd know a name in them. That could work.
    >>  ............................................
    pt  ...Com a dele. Lacradas, não lidas, e a quatro vales de quem reconheceria um nome. Poderia funcionar.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.future.encourage/2   [84 chars]
    en  You've solved nineteen years in one sentence, %1$s, and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase, %1$s, e eu preciso me sentar por causa disso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed and unread, which is the part I could never arrange alone.
    >>  ............................................
    pt  ...Com as dele. Lacradas e não lidas, que é a parte que eu nunca resolvia sozinho.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I don't quite know what to do with my hands.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu nem sei o que fazer com as mãos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, four valleys off. That is how a thing gets kept.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, a quatro vales. É assim que algo se guarda.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. That happens perhaps twice in a life.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Isso acontece talvez duas vezes na vida.
    >>  ............................................
  confident.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, e a quatro vales de quem reconheceria um nome.
    >>  ............................................
  confident.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence, and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase, e eu preciso sentar por causa disso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, e a quatro vales de quem reconheceria um nome.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence, and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase, e eu preciso sentar por causa disso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his, %1$s. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele, %1$s. Lacradas, não lidas, e a quatro vales de quem saberia um nome.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. Give me a moment, %1$s.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Me dê um momento, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his, %1$s. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele, %1$s. Lacradas, não lidas, e a quatro vales de quem saberia um nome.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. Give me a moment, %1$s.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Me dê um momento, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his, %1$s. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele, %1$s. Lacradas, não lidas, e a quatro vales de quem saberia um nome.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. Give me a moment, %1$s.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Me dê um momento, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed and unread, which is the part I could never arrange alone.
    >>  ............................................
    pt  ...Com as dele. Lacradas e não lidas, que é a parte que eu nunca resolvia sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I don't quite know what to do with my hands.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu nem sei o que fazer com as mãos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, e a quatro vales de quem reconheceria um nome.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence, and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase, e eu preciso sentar por causa disso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, and four valleys from anybody who'd know a name.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, e a quatro vales de quem reconheceria um nome.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence, and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase, e eu preciso sentar por causa disso.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed. Four valleys off.
    >>  ............................................
    pt  ...Com as dele. Lacradas. A quatro vales.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  Nineteen years, in one sentence. Give me a moment.
    >>  ............................................
    pt  Dezenove anos, numa frase. Me dê um momento.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, four valleys off. That is how a thing gets kept.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, a quatro vales. É assim que algo se guarda.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. That happens perhaps twice in a life.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Isso acontece talvez duas vezes na vida.
    >>  ............................................
  odd.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed. Four valleys off.
    >>  ............................................
    pt  ...Com as dele. Lacradas. A quatro vales.
    >>  ............................................
  odd.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  Nineteen years, in one sentence. Give me a moment.
    >>  ............................................
    pt  Dezenove anos, numa frase. Me dê um momento.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, four valleys off. That is how a thing gets kept.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, a quatro vales. É assim que algo se guarda.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. That happens perhaps twice in a life.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Isso acontece talvez duas vezes na vida.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his! Sealed, unread, four valleys from anybody who'd know a name in them.
    >>  ............................................
    pt  ...Com as dele! Lacradas, não lidas, a quatro vales de quem reconheceria um nome.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu preciso sentar por causa disso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his! Sealed, unread, four valleys from anybody who'd know a name in them.
    >>  ............................................
    pt  ...Com as dele! Lacradas, não lidas, a quatro vales de quem reconheceria um nome.
    >>  ............................................
  playful.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu preciso sentar por causa disso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed, unread, four valleys off. That is how a thing gets kept.
    >>  ............................................
    pt  ...Com as dele. Lacradas, não lidas, a quatro vales. É assim que algo se guarda.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence. That happens perhaps twice in a life.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase. Isso acontece talvez duas vezes na vida.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed and unread, which is the part I could never arrange alone.
    >>  ............................................
    pt  ...Com as dele. Lacradas e não lidas, que é a parte que eu nunca resolvia sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I don't quite know what to do with my hands.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu nem sei o que fazer com as mãos.
    >>  ............................................
  shy.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his. Sealed. Four valleys off.
    >>  ............................................
    pt  ...Com as dele. Lacradas. A quatro vales.
    >>  ............................................
  shy.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  Nineteen years, in one sentence. Give me a moment.
    >>  ............................................
    pt  Dezenove anos, numa frase. Me dê um momento.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his! Sealed, unread, four valleys from anybody who'd know a name in them.
    >>  ............................................
    pt  ...Com as dele! Lacradas, não lidas, a quatro vales de quem reconheceria um nome.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu preciso sentar por causa disso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.vampire_expert.future.encourage/1
    en  ...With his! Sealed, unread, four valleys from anybody who'd know a name in them.
    >>  ............................................
    pt  ...Com as dele! Lacradas, não lidas, a quatro vales de quem reconheceria um nome.
    >>  ............................................
  witty.dialogue.conversations.work.prof.vampire_expert.future.encourage/2
    en  You've solved nineteen years in one sentence and I need to sit down about it.
    >>  ............................................
    pt  Você resolveu dezenove anos numa frase e eu preciso sentar por causa disso.
    >>  ............................................
```

</details>


### Button `ask_bag_future` — "What would it take to put the bag away?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.future` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.future.ask_bag_future` — accepted phrasings: "what would it take to put the bag away"
  - the message must contain one of: `bag`, `away`, `settled`
  - scored words: `bag`(1.5), `away`(1.0), `settled`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.future.respond.ask_bag_future
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.future.respond.ask_bag_future   [39 chars]
    en  What would it take to put the bag away?
    >>  ............................................
    pt  O que seria preciso pra guardar a bolsa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.future.ask_bag_future`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.future.ask_bag_future
WHO    VILLAGER — what the player reads after pressing "What would it take to put the bag away?"
       spoken on: conversations.topic.work.vampire_expert.future.respond, button `ask_bag_future`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.future.ask_bag_future`: the villager explains. Subject `work.vampire_expert.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.future.ask_bag_future/1   [98 chars]
    en  A year in which I don't reach for it. I've had eleven months twice and I've never had the twelfth.
    >>  ............................................
    pt  Um ano em que eu não a alcance. Já tive onze meses duas vezes e nunca tive o décimo segundo.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.future.ask_bag_future/2   [91 chars]
    en  Somebody saying they'd notice if I went, %1$s. Nobody has, and I've never asked anybody to.
    >>  ............................................
    pt  Alguém dizer que notaria se eu fosse, %1$s. Ninguém disse, e eu nunca pedi a ninguém.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.future.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.vampire_expert.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert` — e.g. "I advise on... nocturnal affairs. Professionally. My clientele tips well and never comes by at noon."


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.respond   [35 chars]
    en  That's the practice, and its hours.
    >>  ............................................
    pt  É a prática, e os horários dela.
    >>  ............................................
```


### Button `ask_hard` — "Where does the advice stop?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.identity` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.hard` — accepted phrasings: "where does the advice stop"
  - the message must contain one of: `advice`, `stop`, `limit`
  - scored words: `advice`(1.5), `stop`(1.2), `limit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.respond.ask_hard   [27 chars]
    en  Where does the advice stop?
    >>  ............................................
    pt  Onde o conselho para?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.vampire_expert.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.hard
WHO    VILLAGER — what the player reads after pressing "Where does the advice stop?"
       spoken on: conversations.topic.work.vampire_expert.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.hard`: the villager explains. Subject `work.vampire_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.followup / ask_more
```

> Written out in full under **`conversations.scene.work.vampire_expert.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "People come to you instead of doing something worse."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.identity` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.value` — accepted phrasings: "people come to you instead of doing something worse"
  - the message must contain one of: `instead`, `worse`
  - scored words: `instead`(1.2), `worse`(1.5), `come`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.respond.value   [52 chars]
    en  People come to you instead of doing something worse.
    >>  ............................................
    pt  As pessoas te procuram em vez de fazer algo pior.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.vampire_expert.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.vampire_expert.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.value
WHO    VILLAGER — what the player reads after pressing "People come to you instead of doing something worse."
       spoken on: conversations.topic.work.vampire_expert.respond, button `value`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.value`: the villager accepts. Subject `work.vampire_expert.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.value/1   [86 chars]
    en  That is the whole justification, and I'm relieved to hear it from outside my own head.
    >>  ............................................
    pt  É a justificativa inteira, e é um alívio ouvir de fora da minha própria cabeça.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.value/2   [88 chars]
    en  They do. Every one of them arrived at my door having already considered the alternative.
    >>  ............................................
    pt  Procuram. Cada um deles chegou à minha porta já tendo considerado a alternativa.
    >>  ............................................
```


### Button `challenge` — "You're on the wrong side of this."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.vampire_expert.identity` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.challenge` — accepted phrasings: "you're on the wrong side of this"
  - the message must contain one of: `side`, `wrong`, `complicit`
  - scored words: `side`(1.5), `wrong`(1.0), `complicit`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.respond.challenge   [33 chars]
    en  You're on the wrong side of this.
    >>  ............................................
    pt  Você está do lado errado disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.vampire_expert.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.vampire_expert.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're on the wrong side of this."
       spoken on: conversations.topic.work.vampire_expert.respond, button `challenge`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.challenge.landed`: the villager resists. Subject `work.vampire_expert.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.challenge.landed/1   [84 chars]
    en  There isn't a side. There are people with a condition and people frightened of them.
    >>  ............................................
    pt  Não existe lado. Existem pessoas com uma condição e pessoas com medo delas.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.challenge.landed/2   [86 chars]
    en  I've been told that by both sides, %1$s, which suggests I'm roughly where I should be.
    >>  ............................................
    pt  Já ouvi isso dos dois lados, %1$s, o que sugere que estou mais ou menos onde devia.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.vampire_expert.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.vampire_expert.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're on the wrong side of this."
       spoken on: conversations.topic.work.vampire_expert.respond, button `challenge`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.challenge.stung`: the villager resists. Subject `work.vampire_expert.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.challenge.stung/1   [50 chars]
    en  ...Name one person harmed by my advice. I'll wait.
    >>  ............................................
    pt  ...Cite uma pessoa prejudicada pelo meu conselho. Eu espero.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.challenge.stung/2   [74 chars]
    en  The wrong side. Right. Say that to the family I kept together last spring.
    >>  ............................................
    pt  Lado errado. Certo. Diga isso à família que eu mantive unida na primavera passada.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.vampire_expert.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert.risk` — e.g. "Any bad month in this valley could end with me in it, and I've kept a bag by the door for nineteen years."


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.risk.respond   [26 chars]
    en  That's what I live beside.
    >>  ............................................
    pt  É ao lado do que eu vivo.
    >>  ............................................
```


### Button `ask_bag` — "Have you ever nearly taken the bag?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.risk` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.risk.ask_bag` — accepted phrasings: "have you ever nearly taken the bag"
  - the message must contain one of: `bag`, `nearly`, `leave`
  - scored words: `bag`(1.5), `nearly`(1.2), `leave`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.risk.respond.ask_bag
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.risk.respond.ask_bag   [35 chars]
    en  Have you ever nearly taken the bag?
    >>  ............................................
    pt  Você já quase pegou a bolsa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.risk.ask_bag`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.risk.ask_bag
WHO    VILLAGER — what the player reads after pressing "Have you ever nearly taken the bag?"
       spoken on: conversations.topic.work.vampire_expert.risk.respond, button `ask_bag`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.risk.ask_bag`: the villager explains. Subject `work.vampire_expert.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.risk.ask_bag/1   [98 chars]
    en  Twice. Both times the priest stood in a doorway and both times I found out afterwards, not during.
    >>  ............................................
    pt  Duas vezes. Nas duas o padre ficou numa porta e nas duas eu soube depois, não durante.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.risk.ask_bag/2   [107 chars]
    en  Once I got as far as the gate, %1$s, and the guard said good evening as though it were an ordinary evening.
    >>  ............................................
    pt  Uma vez eu cheguei ao portão, %1$s, e o guarda deu boa noite como se fosse uma noite comum.
    >>  ............................................
```


### Button `sympathise` — "The letters costing somebody else is the worse half."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.risk` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.risk.sympathise` — accepted phrasings: "the letters costing somebody else is the worse half"
  - the message must contain one of: `letters`, `cost`, `writer`
  - scored words: `letters`(1.2), `cost`(1.5), `writer`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.risk.respond.sympathise   [52 chars]
    en  The letters costing somebody else is the worse half.
    >>  ............................................
    pt  As cartas custarem a outra pessoa é a metade pior.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.vampire_expert.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.vampire_expert.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "The letters costing somebody else is the worse half."
       spoken on: conversations.topic.work.vampire_expert.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.risk.sympathise`: the villager accepts. Subject `work.vampire_expert.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.risk.sympathise/1   [93 chars]
    en  ...It is, and it's why I burn them, and burning them is why there's no record of any of this.
    >>  ............................................
    pt  ...É, e é por isso que eu as queimo, e queimar é por que não existe registro disso.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.risk.sympathise/2   [92 chars]
    en  Nineteen years of the most useful thing I own, %1$s, and I destroy it as fast as it arrives.
    >>  ............................................
    pt  Dezenove anos da coisa mais útil que eu tenho, %1$s, e eu destruo tão rápido quanto chega.
    >>  ............................................
```


### Button `ask_burn` — "You burn all of them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.risk` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.risk.ask_burn` — accepted phrasings: "you burn all of them"
  - the message must contain one of: `burn`, `destroy`, `letters`
  - scored words: `burn`(1.5), `destroy`(1.2), `letters`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.risk.respond.ask_burn
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.risk.respond.ask_burn   [21 chars]
    en  You burn all of them?
    >>  ............................................
    pt  Você queima todas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.risk.ask_burn`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.risk.ask_burn
WHO    VILLAGER — what the player reads after pressing "You burn all of them?"
       spoken on: conversations.topic.work.vampire_expert.risk.respond, button `ask_burn`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.risk.ask_burn`: the villager explains. Subject `work.vampire_expert.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.risk.ask_burn/1   [106 chars]
    en  Every one, the day I answer it. Nineteen years and there is nothing in this house that could ruin anybody.
    >>  ............................................
    pt  Todas, no dia em que respondo. Dezenove anos e não há nada nesta casa que arruíne alguém.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.risk.ask_burn/2   [96 chars]
    en  All but four, %1$s, which are in a place I'd not describe, and which I should probably burn too.
    >>  ............................................
    pt  Todas menos quatro, %1$s, que estão num lugar que eu não descreveria, e que eu provavelmente deveria queimar também.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.risk.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.vampire_expert.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert.task` — e.g. "Answering letters. Four of them, all from people asking me what I am before they ask me anything useful."


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.task.respond   [22 chars]
    en  That's the day I have.
    >>  ............................................
    pt  É o dia que eu tenho.
    >>  ............................................
```


### Button `ask_letters` — "What do the useful ones ask?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.task` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.task.ask_letters` — accepted phrasings: "what do the useful ones ask"
  - the message must contain one of: `letters`, `useful`
  - scored words: `letters`(1.5), `useful`(1.2), `ask`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.task.respond.ask_letters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.task.respond.ask_letters   [28 chars]
    en  What do the useful ones ask?
    >>  ............................................
    pt  O que as úteis perguntam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.task.ask_letters`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.task.ask_letters
WHO    VILLAGER — what the player reads after pressing "What do the useful ones ask?"
       spoken on: conversations.topic.work.vampire_expert.task.respond, button `ask_letters`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.task.ask_letters`: the villager explains. Subject `work.vampire_expert.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.task.ask_letters/1   [106 chars]
    en  How to live next to somebody without either of them pretending. That's the only question worth four pages.
    >>  ............................................
    pt  Como viver ao lado de alguém sem que nenhum dos dois finja. É a única pergunta que vale quatro páginas.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.task.ask_letters/2   [94 chars]
    en  Practical things. Shutters, hours, what to tell a landlord, %1$s. Those I answer the same day.
    >>  ............................................
    pt  Coisas práticas. Venezianas, horários, o que dizer a um senhorio, %1$s. Essas eu respondo no mesmo dia.
    >>  ............................................
```


### Button `offer_hands` — "I could take the letters to the road for you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.vampire_expert.task` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.task.offer_hands` — accepted phrasings: "i could take the letters to the road for you"
  - the message must contain one of: `letters`, `road`, `post`
  - scored words: `letters`(1.2), `road`(1.5), `post`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.task.respond.offer_hands   [45 chars]
    en  I could take the letters to the road for you.
    >>  ............................................
    pt  Eu podia levar as cartas à estrada por você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.vampire_expert.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.vampire_expert.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could take the letters to the road for you."
       spoken on: conversations.topic.work.vampire_expert.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.task.offer_hands`: the villager accepts. Subject `work.vampire_expert.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.task.offer_hands/1   [96 chars]
    en  ...You could, and it would save me an hour of daylight I'd rather spend badly on something else.
    >>  ............................................
    pt  ...Podia, e me pouparia uma hora de sol que eu prefiro gastar mal em outra coisa.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.task.offer_hands/2   [101 chars]
    en  The second inn, before noon, %1$s. And don't explain who they're from. Nobody asks and nobody should.
    >>  ............................................
    pt  A segunda estalagem, antes do meio-dia, %1$s. E não explique de quem são. Ninguém pergunta e ninguém deve.
    >>  ............................................
```


### Button `ask_before` — "Does the asking-what-you-are get tiring?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.task` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.task.ask_before` — accepted phrasings: "does the asking-what-you-are get tiring"
  - the message must contain one of: `asking`, `tiring`
  - scored words: `asking`(1.2), `tiring`(1.5), `what`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.task.respond.ask_before
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.task.respond.ask_before   [40 chars]
    en  Does the asking-what-you-are get tiring?
    >>  ............................................
    pt  Perguntarem o que você é cansa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.task.ask_before`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.task.ask_before
WHO    VILLAGER — what the player reads after pressing "Does the asking-what-you-are get tiring?"
       spoken on: conversations.topic.work.vampire_expert.task.respond, button `ask_before`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.task.ask_before`: the villager explains. Subject `work.vampire_expert.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.task.ask_before/1   [114 chars]
    en  It's the first line of every letter for nineteen years. I've stopped resenting it and I've never stopped noticing.
    >>  ............................................
    pt  É a primeira linha de toda carta há dezenove anos. Parei de me ofender e nunca parei de notar.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.task.ask_before/2   [95 chars]
    en  It's fair, %1$s. It's the question I'd ask. I'd just like it to be the second one occasionally.
    >>  ............................................
    pt  É justo, %1$s. É a pergunta que eu faria. Só queria que fosse a segunda de vez em quando.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.task.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.vampire_expert.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.vampire_expert.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.vampire_expert.village` — e.g. "Nineteen years and this valley has not had one panic. I'd like to think I'm part of why, and I can't prove it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.vampire_expert.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.vampire_expert.village.respond   [24 chars]
    en  That's my standing here.
    >>  ............................................
    pt  É a minha posição aqui.
    >>  ............................................
```


### Button `ask_furniture` — "Furniture is a strange thing to be grateful for."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.village` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.village.ask_furniture` — accepted phrasings: "furniture is a strange thing to be grateful for"
  - the message must contain one of: `furniture`, `grateful`, `ordinary`
  - scored words: `furniture`(1.5), `grateful`(1.2), `ordinary`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.village.respond.ask_furniture
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.village.respond.ask_furniture   [48 chars]
    en  Furniture is a strange thing to be grateful for.
    >>  ............................................
    pt  Mobília é uma coisa estranha de se agradecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.village.ask_furniture`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.village.ask_furniture
WHO    VILLAGER — what the player reads after pressing "Furniture is a strange thing to be grateful for."
       spoken on: conversations.topic.work.vampire_expert.village.respond, button `ask_furniture`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.village.ask_furniture`: the villager explains. Subject `work.vampire_expert.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.village.ask_furniture/1   [97 chars]
    en  It's the best I've had anywhere. Being unremarkable took nineteen years and it is an achievement.
    >>  ............................................
    pt  É o melhor que eu já tive em qualquer lugar. Ser banal levou dezenove anos e é uma conquista.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.village.ask_furniture/2   [82 chars]
    en  Four people, %1$s. In the last place there were none, and there were also torches.
    >>  ............................................
    pt  Quatro pessoas, %1$s. No último lugar não havia nenhuma, e havia tochas.
    >>  ............................................
```


### Button `say_thanks` — "No panic in nineteen years is partly you and you should say so."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.vampire_expert.village` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.village.say_thanks` — accepted phrasings: "no panic in nineteen years is partly you and you should say so"
  - the message must contain one of: `panic`, `partly`, `credit`
  - scored words: `panic`(1.5), `partly`(1.2), `credit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.village.respond.say_thanks   [63 chars]
    en  No panic in nineteen years is partly you and you should say so.
    >>  ............................................
    pt  Nenhum pânico em dezenove anos é em parte você e você devia dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.vampire_expert.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.vampire_expert.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "No panic in nineteen years is partly you and you should say so."
       spoken on: conversations.topic.work.vampire_expert.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.village.say_thanks`: the villager accepts. Subject `work.vampire_expert.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.village.say_thanks/1   [82 chars]
    en  ...Say so. To whom, and with what proof, and what would they do with the sentence.
    >>  ............................................
    pt  ...Dizer. A quem, com que prova, e o que fariam com a frase.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.village.say_thanks/2   [100 chars]
    en  That's an unusual thing to be told, %1$s, and I'm going to need a moment to know what to do with it.
    >>  ............................................
    pt  É uma coisa incomum de ouvir, %1$s, e eu vou precisar de um momento pra saber o que fazer com ela.
    >>  ............................................
```


### Button `ask_prove` — "Why does it need proving?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.vampire_expert.village` · offered only once the villager has actually said `work:vampire_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.vampire_expert.village.ask_prove` — accepted phrasings: "why does it need proving"
  - the message must contain one of: `prove`, `claim`
  - scored words: `prove`(1.5), `claim`(1.2), `why`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.village.respond.ask_prove
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.village.respond.ask_prove   [25 chars]
    en  Why does it need proving?
    >>  ............................................
    pt  Por que precisa de prova?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.vampire_expert.village.ask_prove`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.vampire_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the case you still think about?" | "Mind the daylight."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.village.ask_prove
WHO    VILLAGER — what the player reads after pressing "Why does it need proving?"
       spoken on: conversations.topic.work.vampire_expert.village.respond, button `ask_prove`
       leaves the player on: conversations.topic.work.vampire_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.village.ask_prove`: the villager explains. Subject `work.vampire_expert.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.village.ask_prove/1   [97 chars]
    en  Because in my position a claim without proof is the first line of the story they tell afterwards.
    >>  ............................................
    pt  Porque na minha posição uma afirmação sem prova é a primeira linha da história que contam depois.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.village.ask_prove/2   [92 chars]
    en  Because I'd rather be furniture than be interesting, %1$s. Interesting is how it goes wrong.
    >>  ............................................
    pt  Porque eu prefiro ser mobília a ser interessante, %1$s. Interessante é como dá errado.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.vampire_expert.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.vampire_expert.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.vampire_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.vampire_expert.village.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.vampire_expert.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.left`: the villager accepts. Subject `work.vampire_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.vampire_expert.better_tests.active.respond / leave; conversations.scene.work.vampire_expert.better_tests.succeeded.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond / leave; conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond / leave; conversations.scene.work.vampire_expert.followup / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond / leave; conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond / leave; conversations.topic.work.vampire_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.vampire_expert.better_tests.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

