# Work talk with a butcher

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.butcher.followup`](#conversations-scene-work-butcher-followup)
- [`conversations.scene.work.butcher.hard_slaughter.blocked.respond`](#conversations-scene-work-butcher-hard-slaughter-blocked-respond)
- [`conversations.scene.work.butcher.hard_slaughter.succeeded.respond`](#conversations-scene-work-butcher-hard-slaughter-succeeded-respond)
- [`conversations.scene.work.butcher.spoiling_store.blocked.respond`](#conversations-scene-work-butcher-spoiling-store-blocked-respond)
- [`conversations.scene.work.butcher.spoiling_store.failed.respond`](#conversations-scene-work-butcher-spoiling-store-failed-respond)
- [`conversations.scene.work.butcher.the_hands.active.respond`](#conversations-scene-work-butcher-the-hands-active-respond)
- [`conversations.scene.work.butcher.the_hands.succeeded.respond`](#conversations-scene-work-butcher-the-hands-succeeded-respond)
- [`conversations.topic.work.butcher.craft.respond`](#conversations-topic-work-butcher-craft-respond)
- [`conversations.topic.work.butcher.followup`](#conversations-topic-work-butcher-followup)
- [`conversations.topic.work.butcher.future.respond`](#conversations-topic-work-butcher-future-respond)
- [`conversations.topic.work.butcher.respond`](#conversations-topic-work-butcher-respond)
- [`conversations.topic.work.butcher.risk.respond`](#conversations-topic-work-butcher-risk-respond)
- [`conversations.topic.work.butcher.task.respond`](#conversations-topic-work-butcher-task-respond)
- [`conversations.topic.work.butcher.village.respond`](#conversations-topic-work-butcher-village-respond)

---

## `conversations.scene.work.butcher.followup`

**Reached from 12 route(s):** `conversations.scene.work.butcher.hard_slaughter.blocked.respond` / `ask_how_she_does_it`; `conversations.scene.work.butcher.hard_slaughter.blocked.respond` / `acknowledge_the_weight`; `conversations.scene.work.butcher.hard_slaughter.blocked.respond` / `urge_getting_on_with_it`; `conversations.scene.work.butcher.hard_slaughter.succeeded.respond` / `sit_with_it`; `conversations.scene.work.butcher.spoiling_store.blocked.respond` / `ask_the_arithmetic`; `conversations.scene.work.butcher.spoiling_store.blocked.respond` / `offer_salt`; `conversations.scene.work.butcher.spoiling_store.blocked.respond` / `suggest_selling_cheap`; `conversations.scene.work.butcher.spoiling_store.failed.respond` / `ask_what_now`; `conversations.scene.work.butcher.spoiling_store.failed.respond` / `say_it_happens`; `conversations.scene.work.butcher.the_hands.active.respond` / `ask_if_it_wears`; `conversations.scene.work.butcher.the_hands.active.respond` / `say_it_is_honest_work`; `conversations.scene.work.butcher.the_hands.succeeded.respond` / `note_the_shift`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.hard_slaughter.blocked.accepted` — e.g. "Yes. Tomorrow, at first light. Saying it to somebody makes it a thing I have to do rather than a thing I keep meaning to."
- `conversations.scene.work.butcher.hard_slaughter.blocked.explained` — e.g. "Early, alone, and quickly. Anything else is for my benefit rather than hers, and I am not the one it is happening to."
- `conversations.scene.work.butcher.hard_slaughter.blocked.steadied` — e.g. "It is, and hardly anybody says so, because saying so means thinking about it and nobody wants to think about it before dinner."
- `conversations.scene.work.butcher.hard_slaughter.succeeded.acknowledged` — e.g. "I hope so. That is the whole of what I have to offer them and it has to be enough, because there is no version where they get to stay."
- `conversations.scene.work.butcher.spoiling_store.blocked.accepted` — e.g. "Then %2$s keeps until spring and four households eat, and I will remember which of those things you made happen."
- `conversations.scene.work.butcher.spoiling_store.blocked.explained` — e.g. "Six weeks of somebody's winter. That is how I count it. Not coins — weeks of a family eating properly."
- `conversations.scene.work.butcher.spoiling_store.blocked.resisted` — e.g. "And teach the whole village that my prices are a suggestion. They would remember the cheap week for four years."
- `conversations.scene.work.butcher.spoiling_store.failed.answered` — e.g. "I pay him in full out of my own store and I tell him exactly what happened, in that order, because the order matters."
- `conversations.scene.work.butcher.spoiling_store.failed.qualified` — e.g. "Half true, and I would rather hold the half that is mine, because that is the half I can do something about next year."
- `conversations.scene.work.butcher.the_hands.active.acknowledged` — e.g. "And well is the part that matters. A careless butcher and a careful one look the same at the market and are not the same at all."
- `conversations.scene.work.butcher.the_hands.active.answered` — e.g. "Less than it did. What still lands is being thanked in a lowered voice, as if I had done them a favour they would rather not name."
- `conversations.scene.work.butcher.the_hands.succeeded.acknowledged` — e.g. "By being unembarrassed for a year. It is a very slow argument and it is the only one that has ever worked on a village."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.followup   [25 chars]
    en  Anything more you wanted?
    >>  ............................................
    pt  Queria mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of the killing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.butcher.*` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.followup.ask_more` — accepted phrasings: "whats the hardest part of the killing"; "what is the hardest part of the killing"; "hardest thing about the killing"
  - the message must contain one of: `hardest`, `killing`
  - scored words: `hardest`(1.8), `killing`(1.8), `whats`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.followup.ask_more   [39 chars]
    en  What's the hardest part of the killing?
    >>  ............................................
    pt  Qual é a parte mais difícil do abate?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of the killing?"
       spoken on: conversations.scene.work.butcher.followup, button `ask_more`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard`: the villager explains. Subject `work.butcher.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.butcher.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.butcher.hard/1   [76 chars]
    en  The first cut of the day. After that it's work. Before that it's a decision.
    >>  ............................................
    pt  O primeiro corte do dia. Depois disso é trabalho. Antes disso é decisão.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.hard/2   [68 chars]
    en  None of it, most days. Some days all of it, %1$s. It comes and goes.
    >>  ............................................
    pt  Nenhuma, quase todo dia. Alguns dias, todas, %1$s. Vai e vem.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.butcher.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.followup.leave   [28 chars]
    en  I'll leave you to the block.
    >>  ............................................
    pt  Vou deixar você com o cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the block."
       spoken on: conversations.scene.work.butcher.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.butcher.leave/1   [32 chars]
    en  Aye. Mind the floor, it's slick.
    >>  ............................................
    pt  É. Cuidado com o chão, está escorregadio.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.leave/2   [35 chars]
    en  Off you go, %1$s. Come back Friday.
    >>  ............................................
    pt  Pode ir, %1$s. Volte na sexta.
    >>  ............................................
```

---


## `conversations.scene.work.butcher.hard_slaughter.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.hard_slaughter.blocked` — e.g. "%2$s has to go this week and I have put it off twice, which helps nobody and least of all her."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.hard_slaughter.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond   [9 chars]
    en  The yard.
    >>  ............................................
    pt  O curral.
    >>  ............................................
```


### Button `ask_how_she_does_it` — "How do you get yourself to do it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.hard_slaughter.blocked` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.hard_slaughter.blocked.ask_how_she_does_it` — accepted phrasings: "how do you get yourself to do it"; "how do you get yourself to do it"; "how do you manage that part"
  - the message must contain one of: `manage`, `yourself`
  - scored words: `manage`(1.8), `yourself`(1.8), `get`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.ask_how_she_does_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.hard_slaughter.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.ask_how_she_does_it   [33 chars]
    en  How do you get yourself to do it?
    >>  ............................................
    pt  Como você consegue fazer isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.butcher.animals`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.hard_slaughter"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How do you get yourself to do it?"
       spoken on: conversations.scene.work.butcher.hard_slaughter.blocked.respond, button `ask_how_she_does_it`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard_slaughter.blocked.explained`: the villager explains. Subject `work.butcher.animals`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.explained/1   [117 chars]
    en  Early, alone, and quickly. Anything else is for my benefit rather than hers, and I am not the one it is happening to.
    >>  ............................................
    pt  Cedo, sozinha e rápido. Qualquer outra coisa é para meu benefício, não o dela, e não sou eu que estou passando por isso.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.explained/2   [104 chars]
    en  I do it well. That is the only thing I actually control, so I have made it the only thing I think about.
    >>  ............................................
    pt  Eu faço bem feito. É a única coisa que eu de fato controlo, então fiz dela a única coisa em que penso.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.explained/3   [125 chars]
    en  I say her name first. It sounds soft and it is not — it stops me pretending %2$s is a task, which is when hands get careless.
    >>  ............................................
    pt  Digo o nome dela primeiro. Parece sentimental e não é — impede que eu finja que %2$s é uma tarefa, e é aí que a mão fica descuidada.
    >>  ............................................
```


### Button `acknowledge_the_weight` — "That's a heavy thing to carry."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.butcher.hard_slaughter.blocked` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.hard_slaughter.blocked.acknowledge_the_weight` — accepted phrasings: "thats a heavy thing to carry"; "that is a heavy thing to carry"; "it must weigh on you"
  - the message must contain one of: `heavy`, `weigh`, `carry`
  - scored words: `heavy`(1.8), `weigh`(1.8), `carry`(1.8), `thats`(0.8), `thing`(0.8), `must`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.acknowledge_the_weight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.hard_slaughter.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.acknowledge_the_weight   [30 chars]
    en  That's a heavy thing to carry.
    >>  ............................................
    pt  É uma coisa pesada de carregar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.butcher.slaughter.seen`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `work.butcher.animals`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.hard_slaughter"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "That's a heavy thing to carry."
       spoken on: conversations.scene.work.butcher.hard_slaughter.blocked.respond, button `acknowledge_the_weight`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard_slaughter.blocked.steadied`: the villager accepts. Subject `work.butcher.animals`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.steadied/1   [126 chars]
    en  It is, and hardly anybody says so, because saying so means thinking about it and nobody wants to think about it before dinner.
    >>  ............................................
    pt  É, e quase ninguém diz isso, porque dizer significa pensar nisso, e ninguém quer pensar nisso antes do jantar.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.steadied/2   [113 chars]
    en  Thank you. I would rather carry it than have somebody careless carry it, so I am not asking to be relieved of it.
    >>  ............................................
    pt  Obrigada. Prefiro carregar eu a ver alguém descuidado carregando, então não estou pedindo para me livrar disso.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.steadied/3   [93 chars]
    en  Some weeks it is nothing and some weeks it is the whole week. This is one of the second kind.
    >>  ............................................
    pt  Tem semanas em que não é nada e semanas em que é a semana inteira. Esta é do segundo tipo.
    >>  ............................................
```


### Button `urge_getting_on_with_it` — "Waiting is worse for her."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.hard_slaughter.blocked` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.hard_slaughter.blocked.urge_getting_on_with_it` — accepted phrasings: "waiting is worse for her"; "waiting is worse for her"; "the delay only prolongs it"
  - the message must contain one of: `waiting`, `delay`, `worse`
  - scored words: `waiting`(1.8), `delay`(1.8), `worse`(1.8), `her`(0.8), `only`(0.8), `prolongs`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.urge_getting_on_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.hard_slaughter.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.urge_getting_on_with_it   [25 chars]
    en  Waiting is worse for her.
    >>  ............................................
    pt  Esperar é pior para ela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.butcher.animals`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.hard_slaughter"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Waiting is worse for her."
       spoken on: conversations.scene.work.butcher.hard_slaughter.blocked.respond, button `urge_getting_on_with_it`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard_slaughter.blocked.accepted`: the villager accepts. Subject `work.butcher.animals`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.accepted/1   [121 chars]
    en  Yes. Tomorrow, at first light. Saying it to somebody makes it a thing I have to do rather than a thing I keep meaning to.
    >>  ............................................
    pt  Sim. Amanhã, ao primeiro raio de luz. Dizer isso a alguém transforma em coisa que eu tenho que fazer, não em coisa que eu pretendo fazer.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.accepted/2   [122 chars]
    en  I have said that exact sentence to farmers about their own animals. It is less convincing from the outside of my own head.
    >>  ............................................
    pt  Já disse essa frase exata a fazendeiros sobre os animais deles. Convence menos vinda de fora da minha própria cabeça.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.accepted/3   [104 chars]
    en  You are right and I needed it said plainly. Kindness that costs the animal another week is not kindness.
    >>  ............................................
    pt  Você tem razão e eu precisava ouvir com todas as letras. Bondade que custa mais uma semana ao animal não é bondade.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `work.butcher.hard_slaughter.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.hard_slaughter.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the block.
    >>  ............................................
    pt  Vou deixar você voltar ao cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the block."
       spoken on: conversations.scene.work.butcher.hard_slaughter.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.butcher.hard_slaughter.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.hard_slaughter.succeeded` — e.g. "It is done. %2$s went easily, first light, and I sat on the wall afterwards for longer than I needed to."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.hard_slaughter.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.respond   [16 chars]
    en  The yard, after.
    >>  ............................................
    pt  O curral, depois.
    >>  ............................................
```


### Button `sit_with_it` — "You did right by her."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.butcher.hard_slaughter.succeeded` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.hard_slaughter.succeeded.sit_with_it` — accepted phrasings: "you did right by her"; "you did right by her"; "you treated her properly"
  - the message must contain one of: `right`, `properly`, `treated`
  - scored words: `right`(1.8), `properly`(1.8), `treated`(1.8), `her`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.hard_slaughter.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.respond.sit_with_it   [21 chars]
    en  You did right by her.
    >>  ............................................
    pt  Você fez o certo por ela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.butcher.animals`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.butcher.hard_slaughter"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You did right by her."
       spoken on: conversations.scene.work.butcher.hard_slaughter.succeeded.respond, button `sit_with_it`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard_slaughter.succeeded.acknowledged`: the villager accepts. Subject `work.butcher.animals`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.acknowledged/1   [134 chars]
    en  I hope so. That is the whole of what I have to offer them and it has to be enough, because there is no version where they get to stay.
    >>  ............................................
    pt  Espero que sim. É tudo o que tenho a oferecer a eles e tem que bastar, porque não existe versão em que eles fiquem.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.acknowledged/2   [121 chars]
    en  Thank you. I have been told I am hard about this and I think I am only being accurate, and accuracy is what she was owed.
    >>  ............................................
    pt  Obrigada. Já me disseram que sou dura com isso, e acho que sou apenas exata, e exatidão era o que ela merecia.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.acknowledged/3   [123 chars]
    en  I will take that. It is a strange thing to be praised for and it is also the only praise in this trade that means anything.
    >>  ............................................
    pt  Vou aceitar. É uma coisa estranha de se receber elogio, e é também o único elogio deste ofício que significa algo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `work.butcher.hard_slaughter.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.hard_slaughter.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the block.
    >>  ............................................
    pt  Vou deixar você voltar ao cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the block."
       spoken on: conversations.scene.work.butcher.hard_slaughter.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.butcher.spoiling_store.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.spoiling_store.blocked` — e.g. "There is %3$s hanging and I have %2$s, and I am watching good food turn into an apology."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.spoiling_store.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond   [11 chars]
    en  The cellar.
    >>  ............................................
    pt  O porão.
    >>  ............................................
```


### Button `ask_the_arithmetic` — "How much would be lost?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.spoiling_store.blocked` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.spoiling_store.blocked.ask_the_arithmetic` — accepted phrasings: "how much would be lost"; "how much would be lost"; "what is the loss if it spoils"
  - the message must contain one of: `lost`, `loss`, `spoils`
  - scored words: `lost`(1.8), `loss`(1.8), `spoils`(1.8), `much`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.ask_the_arithmetic
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.ask_the_arithmetic   [23 chars]
    en  How much would be lost?
    >>  ............................................
    pt  Quanto se perderia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.winter_store`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.spoiling_store"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How much would be lost?"
       spoken on: conversations.scene.work.butcher.spoiling_store.blocked.respond, button `ask_the_arithmetic`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.blocked.explained`: the villager explains. Subject `work.butcher.winter_store`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.explained/1   [102 chars]
    en  Six weeks of somebody's winter. That is how I count it. Not coins — weeks of a family eating properly.
    >>  ............................................
    pt  Seis semanas do inverno de alguém. É assim que eu conto. Não em moedas — em semanas de uma família comendo direito.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.explained/2   [122 chars]
    en  The animal, and the year it took to raise, and my word to the farmer that it would not be wasted. The last one costs most.
    >>  ............................................
    pt  O animal, e o ano que levou para criá-lo, e a minha palavra ao fazendeiro de que não seria desperdiçado. Essa última custa mais.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.explained/3   [133 chars]
    en  Enough that I would take the loss myself rather than sell it doubtful. Doubtful meat is how a butcher ends a career in one afternoon.
    >>  ............................................
    pt  O bastante para eu assumir o prejuízo em vez de vender duvidoso. Carne duvidosa é como um açougueiro encerra a carreira numa tarde.
    >>  ............................................
```


### Button `offer_salt` — "I'll bring you something to cure it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.butcher.spoiling_store.blocked` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.spoiling_store.blocked.offer_salt` — accepted phrasings: "ill bring you something to cure it"; "i can bring you curing supplies"; "let me fetch what you need to cure it"
  - the message must contain one of: `curing`, `cure`
  - scored words: `curing`(1.8), `cure`(1.8), `ill`(0.8), `bring`(0.8), `something`(0.8), `supplies`(0.8), `let`(0.8), `fetch`(0.8), `need`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.offer_salt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.offer_salt   [36 chars]
    en  I'll bring you something to cure it.
    >>  ............................................
    pt  Vou trazer algo para curar isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.butcher.store.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.butcher.winter_store`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.spoiling_store", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.spoiling_store", "obligation": "commitment:work.butcher.bring_salt"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.butcher.bring_salt"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you something to cure it."
       spoken on: conversations.scene.work.butcher.spoiling_store.blocked.respond, button `offer_salt`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.blocked.accepted`: the villager accepts. Subject `work.butcher.winter_store`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.accepted/1   [112 chars]
    en  Then %2$s keeps until spring and four households eat, and I will remember which of those things you made happen.
    >>  ............................................
    pt  Então %2$s dura até a primavera e quatro casas comem, e eu vou lembrar qual dessas coisas você tornou possível.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.accepted/2   [97 chars]
    en  Bring it and I will work through the night. I have done it before and it is a good sort of tired.
    >>  ............................................
    pt  Traga e eu trabalho a noite inteira. Já fiz isso antes e é um cansaço do tipo bom.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.accepted/3   [104 chars]
    en  Yes. And take a joint for yourself when it is done, and do not argue with me about it in my own doorway.
    >>  ............................................
    pt  Sim. E leve uma peça para você quando terminar, e não discuta comigo sobre isso na minha própria porta.
    >>  ............................................
```


### Button `suggest_selling_cheap` — "Sell it cheap today, then."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.butcher.spoiling_store.blocked` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.spoiling_store.blocked.suggest_selling_cheap` — accepted phrasings: "sell it cheap today then"; "sell it cheap today then"; "drop the price and move it fast"
  - the message must contain one of: `cheap`, `price`, `sell`
  - scored words: `cheap`(1.8), `price`(1.8), `sell`(1.8), `today`(0.8), `drop`(0.8), `move`(0.8), `fast`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.suggest_selling_cheap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.suggest_selling_cheap   [26 chars]
    en  Sell it cheap today, then.
    >>  ............................................
    pt  Então venda barato hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `work.butcher.winter_store`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.spoiling_store"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Sell it cheap today, then."
       spoken on: conversations.scene.work.butcher.spoiling_store.blocked.respond, button `suggest_selling_cheap`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.blocked.resisted`: the villager resists. Subject `work.butcher.winter_store`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.resisted/1   [111 chars]
    en  And teach the whole village that my prices are a suggestion. They would remember the cheap week for four years.
    >>  ............................................
    pt  E ensinar a vila inteira que meus preços são sugestão. Eles lembrariam da semana barata por quatro anos.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.resisted/2   [126 chars]
    en  That is what a person does once. Then everybody waits for the cheap day, and I have destroyed my own trade to save one animal.
    >>  ............................................
    pt  É o que se faz uma vez. Depois todo mundo espera pelo dia barato, e eu destruí meu próprio ofício para salvar um animal.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.resisted/3   [125 chars]
    en  Half of it, maybe, to people who I know will eat it this week. The other half I would rather salt badly than sell in a panic.
    >>  ............................................
    pt  Metade, talvez, para gente que eu sei que vai comer esta semana. A outra metade eu prefiro salgar mal a vender no desespero.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `work.butcher.spoiling_store.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the block.
    >>  ............................................
    pt  Vou deixar você voltar ao cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the block."
       spoken on: conversations.scene.work.butcher.spoiling_store.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.butcher.spoiling_store.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.spoiling_store.failed` — e.g. "I lost %2$s. Buried it myself so that nobody would be tempted, and I have not told the farmer the whole of it yet."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.spoiling_store.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond   [18 chars]
    en  The cellar, after.
    >>  ............................................
    pt  O porão, depois.
    >>  ............................................
```


### Button `ask_what_now` — "What happens with the farmer?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.butcher.spoiling_store.failed` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.spoiling_store.failed.ask_what_now` — accepted phrasings: "what happens with the farmer"; "what happens with the farmer now"; "how will you settle it with the farmer"
  - the message must contain one of: `farmer`, `settle`
  - scored words: `farmer`(1.8), `settle`(1.8), `happens`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond.ask_what_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond.ask_what_now   [29 chars]
    en  What happens with the farmer?
    >>  ............................................
    pt  E com o fazendeiro, como fica?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.winter_store`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.butcher.spoiling_store"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed.answered
WHO    VILLAGER — what the player reads after pressing "What happens with the farmer?"
       spoken on: conversations.scene.work.butcher.spoiling_store.failed.respond, button `ask_what_now`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.failed.answered`: the villager explains. Subject `work.butcher.winter_store`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.answered/1   [117 chars]
    en  I pay him in full out of my own store and I tell him exactly what happened, in that order, because the order matters.
    >>  ............................................
    pt  Pago a ele integralmente do meu próprio estoque e conto exatamente o que aconteceu, nessa ordem, porque a ordem importa.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.answered/2   [110 chars]
    en  He will be decent about it, which is worse. I would find a shouting match easier than being forgiven promptly.
    >>  ............................................
    pt  Ele vai ser decente a respeito, o que é pior. Eu acharia uma briga mais fácil do que ser perdoada na hora.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.answered/3   [112 chars]
    en  I will offer him the next two beasts at no charge for the work. It is more than the loss and that is deliberate.
    >>  ............................................
    pt  Vou oferecer os próximos dois animais sem cobrar o trabalho. É mais do que a perda, e isso é de propósito.
    >>  ............................................
```


### Button `say_it_happens` — "A warm week isn't your fault."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.butcher.spoiling_store.failed` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.spoiling_store.failed.say_it_happens` — accepted phrasings: "a warm week isnt your fault"; "a warm week is not your fault"; "the weather did that not you"
  - the message must contain one of: `weather`, `fault`, `warm`
  - scored words: `weather`(1.8), `fault`(1.8), `warm`(1.8), `week`(0.8), `isnt`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond.say_it_happens
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond.say_it_happens   [29 chars]
    en  A warm week isn't your fault.
    >>  ............................................
    pt  Uma semana quente não é culpa sua.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.butcher.winter_store`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.butcher.spoiling_store"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed.qualified
WHO    VILLAGER — what the player reads after pressing "A warm week isn't your fault."
       spoken on: conversations.scene.work.butcher.spoiling_store.failed.respond, button `say_it_happens`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.failed.qualified`: the villager qualifys. Subject `work.butcher.winter_store`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.qualified/1   [118 chars]
    en  Half true, and I would rather hold the half that is mine, because that is the half I can do something about next year.
    >>  ............................................
    pt  Meia verdade, e prefiro segurar a metade que é minha, porque é a metade sobre a qual posso fazer algo no ano que vem.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.qualified/2   [98 chars]
    en  Thank you. I will accept it in about a week. Today I am still going over the Monday I did nothing.
    >>  ............................................
    pt  Obrigada. Vou aceitar isso daqui a uma semana. Hoje ainda estou remoendo a segunda-feira em que não fiz nada.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.qualified/3   [100 chars]
    en  The weather is not my fault. Waiting until Thursday to admit I was in trouble was entirely my doing.
    >>  ............................................
    pt  O tempo não é culpa minha. Esperar até quinta para admitir que eu estava em apuros foi inteiramente obra minha.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `work.butcher.spoiling_store.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.spoiling_store.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed.respond.leave   [35 chars]
    en  I'll let you get back to the block.
    >>  ............................................
    pt  Vou deixar você voltar ao cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the block."
       spoken on: conversations.scene.work.butcher.spoiling_store.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.butcher.the_hands.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.the_hands.active` — e.g. "People buy from me at %2$s and look at my hands the whole time, and then ask for it wrapped so they cannot see it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.the_hands.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active.respond   [20 chars]
    en  How people take you.
    >>  ............................................
    pt  Como te veem.
    >>  ............................................
```


### Button `ask_if_it_wears` — "Does that wear on you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.the_hands.active` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.the_hands.active.ask_if_it_wears` — accepted phrasings: "does that wear on you"; "does that wear on you"; "how much does that grind you down"
  - the message must contain one of: `wear`, `grind`
  - scored words: `wear`(1.8), `grind`(1.8), `does`(0.8), `much`(0.8), `down`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active.respond.ask_if_it_wears
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.the_hands.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active.respond.ask_if_it_wears   [22 chars]
    en  Does that wear on you?
    >>  ............................................
    pt  Isso te desgasta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.squeamish_customers`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.the_hands"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active.answered
WHO    VILLAGER — what the player reads after pressing "Does that wear on you?"
       spoken on: conversations.scene.work.butcher.the_hands.active.respond, button `ask_if_it_wears`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.the_hands.active.answered`: the villager explains. Subject `work.butcher.squeamish_customers`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active.answered/1   [130 chars]
    en  Less than it did. What still lands is being thanked in a lowered voice, as if I had done them a favour they would rather not name.
    >>  ............................................
    pt  Menos que antes. O que ainda dói é ser agradecida em voz baixa, como se eu tivesse feito um favor que preferem não nomear.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.active.answered/2   [149 chars]
    en  Not the looking. The pretending. A person who says plainly that they do not like it is easy company; the ones who perform not minding are exhausting.
    >>  ............................................
    pt  O olhar, não. A fingida. Quem diz claramente que não gosta é companhia fácil; quem encena não se importar cansa.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.active.answered/3   [124 chars]
    en  Some days I want to hand somebody the knife and say go on, then. I never will. But I have thought it at %2$s more than once.
    >>  ............................................
    pt  Tem dias em que eu quero entregar a faca para alguém e dizer: então vá. Nunca vou fazer. Mas já pensei isso em %2$s mais de uma vez.
    >>  ............................................
```


### Button `say_it_is_honest_work` — "Somebody has to do it, and well."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.the_hands.active` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.the_hands.active.say_it_is_honest_work` — accepted phrasings: "somebody has to do it and well"; "somebody has to do it and do it well"; "it is honest work done properly"
  - the message must contain one of: `honest`, `somebody`, `properly`
  - scored words: `honest`(1.8), `somebody`(1.8), `properly`(1.8), `well`(0.8), `work`(0.8), `done`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active.respond.say_it_is_honest_work
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.the_hands.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active.respond.say_it_is_honest_work   [32 chars]
    en  Somebody has to do it, and well.
    >>  ............................................
    pt  Alguém tem que fazer, e bem feito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.butcher.squeamish_customers`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.butcher.the_hands"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active.acknowledged
WHO    VILLAGER — what the player reads after pressing "Somebody has to do it, and well."
       spoken on: conversations.scene.work.butcher.the_hands.active.respond, button `say_it_is_honest_work`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.the_hands.active.acknowledged`: the villager accepts. Subject `work.butcher.squeamish_customers`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active.acknowledged/1   [128 chars]
    en  And well is the part that matters. A careless butcher and a careful one look the same at the market and are not the same at all.
    >>  ............................................
    pt  E o bem feito é a parte que importa. Um açougueiro descuidado e um cuidadoso parecem iguais na feira e não são nada iguais.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.active.acknowledged/2   [110 chars]
    en  Thank you. That is the sentence I would like carved over the door, and I would like it said in a normal voice.
    >>  ............................................
    pt  Obrigada. É a frase que eu gostaria de ver entalhada sobre a porta, e gostaria que fosse dita em voz normal.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.active.acknowledged/3   [127 chars]
    en  It is honest. I have never once had to explain away anything I sold, and there are trades in this village that cannot say that.
    >>  ............................................
    pt  É honesto. Nunca precisei justificar nada do que vendi, e há ofícios nesta vila que não podem dizer o mesmo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `work.butcher.the_hands.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.the_hands.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active.respond.leave   [35 chars]
    en  I'll let you get back to the block.
    >>  ............................................
    pt  Vou deixar você voltar ao cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the block."
       spoken on: conversations.scene.work.butcher.the_hands.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.butcher.the_hands.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.butcher.the_hands.succeeded` — e.g. "A child asked me how it works last week, properly and without flinching, and her mother let her."


```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.butcher.the_hands.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.succeeded.respond   [19 chars]
    en  The market, lately.
    >>  ............................................
    pt  A feira, ultimamente.
    >>  ............................................
```


### Button `note_the_shift` — "You changed how they see it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.butcher.the_hands.succeeded` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.butcher.the_hands.succeeded.note_the_shift` — accepted phrasings: "you changed how they see it"; "you changed how they see it"; "you shifted the way people see the trade"
  - the message must contain one of: `changed`, `shifted`, `see`
  - scored words: `changed`(1.8), `shifted`(1.8), `see`(1.8), `way`(0.8), `people`(0.8), `trade`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.succeeded.respond.note_the_shift
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.the_hands.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.succeeded.respond.note_the_shift   [28 chars]
    en  You changed how they see it.
    >>  ............................................
    pt  Você mudou como eles enxergam.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.butcher.squeamish_customers`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.butcher.the_hands"}
- Then opens: `conversations.scene.work.butcher.followup`
- …where the player's next choices will be: "What's the hardest part of the killing?" | "I'll leave you to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You changed how they see it."
       spoken on: conversations.scene.work.butcher.the_hands.succeeded.respond, button `note_the_shift`
       leaves the player on: conversations.scene.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.the_hands.succeeded.acknowledged`: the villager accepts. Subject `work.butcher.squeamish_customers`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.succeeded.acknowledged/1   [119 chars]
    en  By being unembarrassed for a year. It is a very slow argument and it is the only one that has ever worked on a village.
    >>  ............................................
    pt  Por ficar sem constrangimento durante um ano. É um argumento muito lento e é o único que já funcionou com uma vila.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.succeeded.acknowledged/2   [114 chars]
    en  The child did more than I did. One honest question in public and four adults had to stand there and be reasonable.
    >>  ............................................
    pt  A criança fez mais do que eu. Uma pergunta honesta em público e quatro adultos tiveram que ficar ali e ser razoáveis.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.succeeded.acknowledged/3   [122 chars]
    en  I would like to say I planned it. I got tired, and stopped performing, and it turned out tiredness looked like confidence.
    >>  ............................................
    pt  Eu gostaria de dizer que planejei. Eu me cansei, parei de encenar, e acontece que cansaço parecia confiança.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the block."

*stance family `exit` · tone `plain` · answers the beat(s) `work.butcher.the_hands.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.butcher.the_hands.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the block.
    >>  ............................................
    pt  Vou deixar você voltar ao cepo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the block."
       spoken on: conversations.scene.work.butcher.the_hands.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.topic.work.butcher.craft.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher.craft` — e.g. "A good cut follows the animal, not the knife. Fighting it is how you get gristle and waste."


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.craft.respond   [25 chars]
    en  That's the knowing of it.
    >>  ............................................
    pt  É esse o saber.
    >>  ............................................
```


### Button `ask_frightened` — "How can you tell it was frightened?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.craft` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.craft.ask_frightened` — accepted phrasings: "how can you tell it was frightened"
  - the message must contain one of: `frightened`, `afraid`, `meat`
  - scored words: `frightened`(1.5), `afraid`(1.2), `meat`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.craft.respond.ask_frightened
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.craft.respond.ask_frightened   [35 chars]
    en  How can you tell it was frightened?
    >>  ............................................
    pt  Como você sabe que estava assustado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.craft.ask_frightened`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.craft.ask_frightened
WHO    VILLAGER — what the player reads after pressing "How can you tell it was frightened?"
       spoken on: conversations.topic.work.butcher.craft.respond, button `ask_frightened`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.craft.ask_frightened`: the villager explains. Subject `work.butcher.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.craft.ask_frightened/1   [78 chars]
    en  The meat is wrong. Tight and dark. It's the animal's last minutes still in it.
    >>  ............................................
    pt  A carne fica errada. Tensa e escura. São os últimos minutos do animal ainda ali.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.craft.ask_frightened/2   [84 chars]
    en  You learn it and then you can't unlearn it, %1$s, and it changes how you keep a pen.
    >>  ............................................
    pt  Você aprende e depois não desaprende, %1$s, e isso muda como você cuida do curral.
    >>  ............................................
```


### Button `admire` — "Following the animal rather than the knife is well put."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.craft` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.craft.admire` — accepted phrasings: "following the animal rather than the knife is well put"
  - the message must contain one of: `following`, `knife`, `phrase`
  - scored words: `following`(1.5), `knife`(1.0), `phrase`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.craft.respond.admire   [55 chars]
    en  Following the animal rather than the knife is well put.
    >>  ............................................
    pt  Seguir o animal em vez da faca é bem dito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.butcher.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.butcher.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.craft.admire
WHO    VILLAGER — what the player reads after pressing "Following the animal rather than the knife is well put."
       spoken on: conversations.topic.work.butcher.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.craft.admire`: the villager accepts. Subject `work.butcher.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.craft.admire/1   [79 chars]
    en  My mother said it first and I've never improved on it. Fifteen years of trying.
    >>  ............................................
    pt  Minha mãe disse primeiro e eu nunca melhorei. Quinze anos tentando.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.craft.admire/2   [88 chars]
    en  It's the only sentence of mine worth repeating, so repeat it where the apprentice hears.
    >>  ............................................
    pt  É a única frase minha que vale repetir, então repita onde o aprendiz ouça.
    >>  ............................................
```


### Button `ask_waste` — "How little goes to waste?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.craft` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.craft.ask_waste` — accepted phrasings: "how little goes to waste"
  - the message must contain one of: `waste`, `discarded`, `use`
  - scored words: `waste`(1.5), `discarded`(1.2), `use`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.craft.respond.ask_waste
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.craft.respond.ask_waste   [25 chars]
    en  How little goes to waste?
    >>  ............................................
    pt  Quão pouco se perde?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.craft.ask_waste`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.craft.ask_waste
WHO    VILLAGER — what the player reads after pressing "How little goes to waste?"
       spoken on: conversations.topic.work.butcher.craft.respond, button `ask_waste`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.craft.ask_waste`: the villager explains. Subject `work.butcher.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.craft.ask_waste/1   [103 chars]
    en  Bone to the broth, fat to the candles, hide to the leatherworker. Almost nothing, and that's the point.
    >>  ............................................
    pt  Osso pro caldo, gordura pras velas, couro pro curtidor. Quase nada, e é essa a questão.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.craft.ask_waste/2   [99 chars]
    en  Less than a handful off a whole beast. Anything more and I'd be insulting the farmer who raised it.
    >>  ............................................
    pt  Menos que um punhado de um bicho inteiro. Mais que isso seria insultar quem criou.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.craft.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.butcher.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.followup`

**Reached from 20 route(s):** `conversations.scene.work.butcher.followup` / `ask_more`; `conversations.topic.work.butcher.craft.respond` / `ask_frightened`; `conversations.topic.work.butcher.craft.respond` / `admire`; `conversations.topic.work.butcher.craft.respond` / `ask_waste`; `conversations.topic.work.butcher.future.respond` / `ask_pens`; `conversations.topic.work.butcher.future.respond` / `encourage`; `conversations.topic.work.butcher.future.respond` / `ask_after`; `conversations.topic.work.butcher.respond` / `ask_hard`; `conversations.topic.work.butcher.respond` / `value`; `conversations.topic.work.butcher.respond` / `challenge`; `conversations.topic.work.butcher.respond` / `challenge`; `conversations.topic.work.butcher.risk.respond` / `ask_barrel` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher.challenge.landed` — e.g. "It's an honest one. Cruel would be letting the winter do it slowly instead."
- `conversations.work.prof.butcher.challenge.stung` — e.g. "...Cruel. Right. Eat bread and turnips all winter and then say it to me."
- `conversations.work.prof.butcher.craft.admire` — e.g. "My mother said it first and I've never improved on it. Fifteen years of trying."
- `conversations.work.prof.butcher.craft.ask_frightened` — e.g. "The meat is wrong. Tight and dark. It's the animal's last minutes still in it."
- `conversations.work.prof.butcher.craft.ask_waste` — e.g. "Bone to the broth, fat to the candles, hide to the leatherworker. Almost nothing, and that's the point."
- `conversations.work.prof.butcher.future.ask_after` — e.g. "Nobody yet. It's an honest trade that everybody would rather somebody else did."
- `conversations.work.prof.butcher.future.ask_pens` — e.g. "Land at the north edge and two weeks of fencing. I've asked. It gets called a preference."
- `conversations.work.prof.butcher.future.encourage` — e.g. "...That's a difference of one word and it might be the difference. I'll try it."
- `conversations.work.prof.butcher.hard` — e.g. "The first cut of the day. After that it's work. Before that it's a decision."
- `conversations.work.prof.butcher.risk.ask_barrel` — e.g. "Smell, at the top, weekly, all winter. It has never once been convenient."
- `conversations.work.prof.butcher.risk.ask_broke` — e.g. "A calf that followed me about. I gave her a name in March and I could not do it in October."
- `conversations.work.prof.butcher.risk.sympathise` — e.g. "...It does. And the year I broke it was the worst year I've had at this trade."
- `conversations.work.prof.butcher.task.ask_february` — e.g. "Some years it's a fortnight of margin. Nobody notices, because I'm the one counting."
- `conversations.work.prof.butcher.task.ask_race` — e.g. "Then it's dog meat and an apology to the family that raised it. I've done that twice."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.followup   [27 chars]
    en  That's the block, all told.
    >>  ............................................
    pt  É o cepo, no fim das contas.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.challenge.landed`, `work.butcher.challenge.stung`, `work.butcher.craft.admire`, `work.butcher.craft.ask_frightened`, `work.butcher.craft.ask_waste`, `work.butcher.future.ask_after`, `work.butcher.future.ask_pens`, `work.butcher.future.encourage`, `work.butcher.hard`, `work.butcher.risk.ask_barrel`, `work.butcher.risk.ask_broke`, `work.butcher.risk.sympathise`, `work.butcher.task.ask_february`, `work.butcher.task.ask_race`, `work.butcher.task.offer_hands`, `work.butcher.value`, `work.butcher.village.ask_decide`, `work.butcher.village.ask_last`, `work.butcher.village.say_thanks` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.butcher.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `stomach`, `honest`
  - scored words: `thought`(1.2), `stomach`(1.2), `honest`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.butcher.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.butcher.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.butcher.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.butcher.thanks`: the villager accepts. Subject `work.butcher.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.thanks/1   [51 chars]
    en  Most folk prefer not to. I don't blame them for it.
    >>  ............................................
    pt  O povo prefere não pensar. Não os culpo.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.thanks/2   [70 chars]
    en  It's a trade you can only think about clearly on a full stomach, %1$s.
    >>  ............................................
    pt  É um ofício que só dá pra pensar com clareza de barriga cheia, %1$s.
    >>  ............................................
```


### Button `ask_more` — "How do you get through the winter?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.challenge.landed`, `work.butcher.challenge.stung`, `work.butcher.craft.admire`, `work.butcher.craft.ask_frightened`, `work.butcher.craft.ask_waste`, `work.butcher.future.ask_after`, `work.butcher.future.ask_pens`, `work.butcher.future.encourage`, `work.butcher.hard`, `work.butcher.risk.ask_barrel`, `work.butcher.risk.ask_broke`, `work.butcher.risk.sympathise`, `work.butcher.task.ask_february`, `work.butcher.task.ask_race`, `work.butcher.task.offer_hands`, `work.butcher.value`, `work.butcher.village.ask_decide`, `work.butcher.village.ask_last`, `work.butcher.village.say_thanks` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.butcher.more` — accepted phrasings: "how do you get through the winter"
  - the message must contain one of: `winter`, `preserve`, `salt`
  - scored words: `winter`(1.5), `preserve`(1.5), `salt`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.followup.ask_more   [34 chars]
    en  How do you get through the winter?
    >>  ............................................
    pt  Como você atravessa o inverno?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.butcher.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.more
WHO    VILLAGER — what the player reads after pressing "How do you get through the winter?"
       spoken on: conversations.topic.work.butcher.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.butcher.more`: the villager discloses. Subject `work.butcher.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.more/1   [80 chars]
    en  Salt, smoke, and starting in autumn. If you begin in winter you've already lost.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Se você começa no inverno, já perdeu.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.more/2   [76 chars]
    en  Carefully, and with a ledger. I know to the week when the village runs thin.
    >>  ............................................
    pt  Com cuidado, e com um livro-caixa. Sei até a semana em que o vilarejo aperta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Get it wrong and forty people are ill and none will know which meal.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Erre e quarenta pessoas ficam doentes e nenhuma vai saber qual refeição.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.butcher.more/2
    en  Two winters ago we were four barrels short and I have never told anybody how close that was.
    >>  ............................................
    pt  Dois invernos atrás faltaram quatro barris e eu nunca contei a ninguém o quão perto foi.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and the season. Autumn does the deciding and I only do as I'm told.
    >>  ............................................
    pt  Sal, defumação, e a estação. O outono decide e eu só obedeço.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels a year, same as always. It has come out right most years, and the rest we managed.
    >>  ............................................
    pt  Quarenta barris por ano, como sempre. Deu certo quase todo ano, e o resto a gente se virou.
    >>  ............................................
  confident.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Begin in winter and you have already lost.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Comece no inverno e você já perdeu.
    >>  ............................................
  confident.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels and a fortnight of margin. Nobody notices, because I am the one counting.
    >>  ............................................
    pt  Quarenta barris e uma quinzena de margem. Ninguém repara, porque eu é que conto.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Begin in winter and you have already lost.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Comece no inverno e você já perdeu.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels and a fortnight of margin. Nobody notices, because I am the one counting.
    >>  ............................................
    pt  Quarenta barris e uma quinzena de margem. Ninguém repara, porque eu é que conto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting early. Come at the autumn packing and I'll show you — it's better company than you'd think.
    >>  ............................................
    pt  Sal, defumação, e começar cedo. Venha na salga do outono e eu mostro — é companhia melhor do que se imagina.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels, and I know which families will need which of them. That's the part I'd talk about all day.
    >>  ............................................
    pt  Quarenta barris, e eu sei quais famílias vão precisar de quais. É a parte de que eu falaria o dia todo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting early. Come at the autumn packing and I'll show you — it's better company than you'd think.
    >>  ............................................
    pt  Sal, defumação, e começar cedo. Venha na salga do outono e eu mostro — é companhia melhor do que se imagina.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels, and I know which families will need which of them. That's the part I'd talk about all day.
    >>  ............................................
    pt  Quarenta barris, e eu sei quais famílias vão precisar de quais. É a parte de que eu falaria o dia todo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting early. Come at the autumn packing and I'll show you — it's better company than you'd think.
    >>  ............................................
    pt  Sal, defumação, e começar cedo. Venha na salga do outono e eu mostro — é companhia melhor do que se imagina.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels, and I know which families will need which of them. That's the part I'd talk about all day.
    >>  ............................................
    pt  Quarenta barris, e eu sei quais famílias vão precisar de quais. É a parte de que eu falaria o dia todo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Get it wrong and forty people are ill and none will know which meal.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Erre e quarenta pessoas ficam doentes e nenhuma vai saber qual refeição.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.butcher.more/2
    en  Two winters ago we were four barrels short and I have never told anybody how close that was.
    >>  ............................................
    pt  Dois invernos atrás faltaram quatro barris e eu nunca contei a ninguém o quão perto foi.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Begin in winter and you have already lost.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Comece no inverno e você já perdeu.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels and a fortnight of margin. Nobody notices, because I am the one counting.
    >>  ............................................
    pt  Quarenta barris e uma quinzena de margem. Ninguém repara, porque eu é que conto.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Begin in winter and you have already lost.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Comece no inverno e você já perdeu.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels and a fortnight of margin. Nobody notices, because I am the one counting.
    >>  ............................................
    pt  Quarenta barris e uma quinzena de margem. Ninguém repara, porque eu é que conto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and autumn. Layer, salt, press, and never be shy with the salt.
    >>  ............................................
    pt  Sal, defumação, e outono. Camada, sal, prensa, e nunca ser tímido com o sal.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.butcher.more/2
    en  I open every barrel myself before it's sold. Nobody asked me to and nobody knows.
    >>  ............................................
    pt  Abro cada barril eu mesmo antes de vender. Ninguém pediu e ninguém sabe.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and the season. Autumn does the deciding and I only do as I'm told.
    >>  ............................................
    pt  Sal, defumação, e a estação. O outono decide e eu só obedeço.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels a year, same as always. It has come out right most years, and the rest we managed.
    >>  ............................................
    pt  Quarenta barris por ano, como sempre. Deu certo quase todo ano, e o resto a gente se virou.
    >>  ............................................
  odd.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and autumn. Layer, salt, press, and never be shy with the salt.
    >>  ............................................
    pt  Sal, defumação, e outono. Camada, sal, prensa, e nunca ser tímido com o sal.
    >>  ............................................
  odd.dialogue.conversations.work.prof.butcher.more/2
    en  I open every barrel myself before it's sold. Nobody asked me to and nobody knows.
    >>  ............................................
    pt  Abro cada barril eu mesmo antes de vender. Ninguém pediu e ninguém sabe.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and the season. Autumn does the deciding and I only do as I'm told.
    >>  ............................................
    pt  Sal, defumação, e a estação. O outono decide e eu só obedeço.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels a year, same as always. It has come out right most years, and the rest we managed.
    >>  ............................................
    pt  Quarenta barris por ano, como sempre. Deu certo quase todo ano, e o resto a gente se virou.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn! Start in winter and you have already lost, and nobody believes me.
    >>  ............................................
    pt  Sal, defumação, e começar no outono! Comece no inverno e já perdeu, e ninguém acredita em mim.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels. Forty! And a fortnight of margin, which is the least comfortable fortnight of my year.
    >>  ............................................
    pt  Quarenta barris. Quarenta! E uma quinzena de margem, a quinzena menos confortável do meu ano.
    >>  ............................................
  playful.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn! Start in winter and you have already lost, and nobody believes me.
    >>  ............................................
    pt  Sal, defumação, e começar no outono! Comece no inverno e já perdeu, e ninguém acredita em mim.
    >>  ............................................
  playful.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels. Forty! And a fortnight of margin, which is the least comfortable fortnight of my year.
    >>  ............................................
    pt  Quarenta barris. Quarenta! E uma quinzena de margem, a quinzena menos confortável do meu ano.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and the season. Autumn does the deciding and I only do as I'm told.
    >>  ............................................
    pt  Sal, defumação, e a estação. O outono decide e eu só obedeço.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels a year, same as always. It has come out right most years, and the rest we managed.
    >>  ............................................
    pt  Quarenta barris por ano, como sempre. Deu certo quase todo ano, e o resto a gente se virou.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn. Get it wrong and forty people are ill and none will know which meal.
    >>  ............................................
    pt  Sal, defumação, e começar no outono. Erre e quarenta pessoas ficam doentes e nenhuma vai saber qual refeição.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.butcher.more/2
    en  Two winters ago we were four barrels short and I have never told anybody how close that was.
    >>  ............................................
    pt  Dois invernos atrás faltaram quatro barris e eu nunca contei a ninguém o quão perto foi.
    >>  ............................................
  shy.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and autumn. Layer, salt, press, and never be shy with the salt.
    >>  ............................................
    pt  Sal, defumação, e outono. Camada, sal, prensa, e nunca ser tímido com o sal.
    >>  ............................................
  shy.dialogue.conversations.work.prof.butcher.more/2
    en  I open every barrel myself before it's sold. Nobody asked me to and nobody knows.
    >>  ............................................
    pt  Abro cada barril eu mesmo antes de vender. Ninguém pediu e ninguém sabe.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn! Start in winter and you have already lost, and nobody believes me.
    >>  ............................................
    pt  Sal, defumação, e começar no outono! Comece no inverno e já perdeu, e ninguém acredita em mim.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels. Forty! And a fortnight of margin, which is the least comfortable fortnight of my year.
    >>  ............................................
    pt  Quarenta barris. Quarenta! E uma quinzena de margem, a quinzena menos confortável do meu ano.
    >>  ............................................
  witty.dialogue.conversations.work.prof.butcher.more/1
    en  Salt, smoke, and starting in autumn! Start in winter and you have already lost, and nobody believes me.
    >>  ............................................
    pt  Sal, defumação, e começar no outono! Comece no inverno e já perdeu, e ninguém acredita em mim.
    >>  ............................................
  witty.dialogue.conversations.work.prof.butcher.more/2
    en  Forty barrels. Forty! And a fortnight of margin, which is the least comfortable fortnight of my year.
    >>  ............................................
    pt  Quarenta barris. Quarenta! E uma quinzena de margem, a quinzena menos confortável do meu ano.
    >>  ............................................
```

</details>


### Button `leave` — "Keep well fed."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.challenge.landed`, `work.butcher.challenge.stung`, `work.butcher.craft.admire`, `work.butcher.craft.ask_frightened`, `work.butcher.craft.ask_waste`, `work.butcher.future.ask_after`, `work.butcher.future.ask_pens`, `work.butcher.future.encourage`, `work.butcher.hard`, `work.butcher.risk.ask_barrel`, `work.butcher.risk.ask_broke`, `work.butcher.risk.sympathise`, `work.butcher.task.ask_february`, `work.butcher.task.ask_race`, `work.butcher.task.offer_hands`, `work.butcher.value`, `work.butcher.village.ask_decide`, `work.butcher.village.ask_last`, `work.butcher.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.followup.leave   [14 chars]
    en  Keep well fed.
    >>  ............................................
    pt  Coma bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "Keep well fed."
       spoken on: conversations.topic.work.butcher.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher.future` — e.g. "I'd like the pens further from the houses. Children shouldn't grow up learning that sound."


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.future.respond   [26 chars]
    en  That's the far side of it.
    >>  ............................................
    pt  É o outro lado disso.
    >>  ............................................
```


### Button `ask_pens` — "What would moving the pens take?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.future` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.future.ask_pens` — accepted phrasings: "what would moving the pens take"
  - the message must contain one of: `pens`, `moving`, `fencing`
  - scored words: `pens`(1.5), `moving`(1.2), `fencing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.future.respond.ask_pens
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.future.respond.ask_pens   [32 chars]
    en  What would moving the pens take?
    >>  ............................................
    pt  O que exigiria mudar os currais?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.future.ask_pens`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.future.ask_pens
WHO    VILLAGER — what the player reads after pressing "What would moving the pens take?"
       spoken on: conversations.topic.work.butcher.future.respond, button `ask_pens`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.future.ask_pens`: the villager explains. Subject `work.butcher.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.future.ask_pens/1   [89 chars]
    en  Land at the north edge and two weeks of fencing. I've asked. It gets called a preference.
    >>  ............................................
    pt  Terra na borda norte e duas semanas de cerca. Já pedi. Chamam de preferência.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.future.ask_pens/2   [66 chars]
    en  Nothing anyone would notice except me and fourteen children, %1$s.
    >>  ............................................
    pt  Nada que alguém repare, exceto eu e catorze crianças, %1$s.
    >>  ............................................
```


### Button `encourage` — "Call it a plan, not a preference, and ask again."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.future` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.future.encourage` — accepted phrasings: "call it a plan, not a preference, and ask again"
  - the message must contain one of: `plan`, `proposal`, `again`
  - scored words: `plan`(1.5), `proposal`(1.2), `again`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.future.respond.encourage   [48 chars]
    en  Call it a plan, not a preference, and ask again.
    >>  ............................................
    pt  Chame de plano, não de preferência, e peça de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.butcher.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.butcher.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.future.encourage
WHO    VILLAGER — what the player reads after pressing "Call it a plan, not a preference, and ask again."
       spoken on: conversations.topic.work.butcher.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.future.encourage`: the villager accepts. Subject `work.butcher.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.future.encourage/1   [79 chars]
    en  ...That's a difference of one word and it might be the difference. I'll try it.
    >>  ............................................
    pt  ...É uma diferença de uma palavra e pode ser a diferença. Vou tentar.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.future.encourage/2   [72 chars]
    en  Said that way the mayor has to argue with a proposal instead of with me.
    >>  ............................................
    pt  Dito assim o prefeito tem que discutir com uma proposta em vez de comigo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word's difference. I've been the wrong word for four years without knowing.
    >>  ............................................
    pt  ...Uma palavra de diferença. Fui a palavra errada por quatro anos sem saber.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. Not with me. That would be a relief.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. Não comigo. Seria um alívio.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. I've watched proposals win and men lose for less than that.
    >>  ............................................
    pt  ...Uma palavra. Já vi propostas vencerem e homens perderem por menos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. That's how anything gets built here.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. É assim que se constrói algo aqui.
    >>  ............................................
  confident.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...That's a difference of one word and it might be the whole difference. I'll try it.
    >>  ............................................
    pt  ...É uma diferença de uma palavra e pode ser a diferença toda. Vou tentar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de discutir comigo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...That's a difference of one word and it might be the whole difference. I'll try it.
    >>  ............................................
    pt  ...É uma diferença de uma palavra e pode ser a diferença toda. Vou tentar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de discutir comigo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word's difference, %1$s, and it might be the whole of it. I'll try it.
    >>  ............................................
    pt  ...Uma palavra de diferença, %1$s, e pode ser tudo. Vou tentar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said your way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito do seu jeito o prefeito discute com uma proposta, não comigo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word's difference, %1$s, and it might be the whole of it. I'll try it.
    >>  ............................................
    pt  ...Uma palavra de diferença, %1$s, e pode ser tudo. Vou tentar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said your way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito do seu jeito o prefeito discute com uma proposta, não comigo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word's difference, %1$s, and it might be the whole of it. I'll try it.
    >>  ............................................
    pt  ...Uma palavra de diferença, %1$s, e pode ser tudo. Vou tentar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said your way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito do seu jeito o prefeito discute com uma proposta, não comigo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word's difference. I've been the wrong word for four years without knowing.
    >>  ............................................
    pt  ...Uma palavra de diferença. Fui a palavra errada por quatro anos sem saber.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. Not with me. That would be a relief.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. Não comigo. Seria um alívio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...That's a difference of one word and it might be the whole difference. I'll try it.
    >>  ............................................
    pt  ...É uma diferença de uma palavra e pode ser a diferença toda. Vou tentar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de discutir comigo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...That's a difference of one word and it might be the whole difference. I'll try it.
    >>  ............................................
    pt  ...É uma diferença de uma palavra e pode ser a diferença toda. Vou tentar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor argues with a proposal instead of with me.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de discutir comigo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. Might be the whole difference.
    >>  ............................................
    pt  ...Uma palavra. Pode ser a diferença toda.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Then he argues with a proposal, not with me.
    >>  ............................................
    pt  Aí ele discute com uma proposta, não comigo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. I've watched proposals win and men lose for less than that.
    >>  ............................................
    pt  ...Uma palavra. Já vi propostas vencerem e homens perderem por menos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. That's how anything gets built here.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. É assim que se constrói algo aqui.
    >>  ............................................
  odd.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. Might be the whole difference.
    >>  ............................................
    pt  ...Uma palavra. Pode ser a diferença toda.
    >>  ............................................
  odd.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Then he argues with a proposal, not with me.
    >>  ............................................
    pt  Aí ele discute com uma proposta, não comigo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. I've watched proposals win and men lose for less than that.
    >>  ............................................
    pt  ...Uma palavra. Já vi propostas vencerem e homens perderem por menos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. That's how anything gets built here.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. É assim que se constrói algo aqui.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word! One. And it might be the whole difference. Right, I'm trying that.
    >>  ............................................
    pt  ...Uma palavra! Uma. E pode ser a diferença toda. Certo, vou tentar isso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor has to argue with a proposal instead of with me. Marvellous.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de comigo. Maravilhoso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word! One. And it might be the whole difference. Right, I'm trying that.
    >>  ............................................
    pt  ...Uma palavra! Uma. E pode ser a diferença toda. Certo, vou tentar isso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor has to argue with a proposal instead of with me. Marvellous.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de comigo. Maravilhoso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. I've watched proposals win and men lose for less than that.
    >>  ............................................
    pt  ...Uma palavra. Já vi propostas vencerem e homens perderem por menos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. That's how anything gets built here.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. É assim que se constrói algo aqui.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word's difference. I've been the wrong word for four years without knowing.
    >>  ............................................
    pt  ...Uma palavra de diferença. Fui a palavra errada por quatro anos sem saber.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way he argues with a proposal. Not with me. That would be a relief.
    >>  ............................................
    pt  Dito assim ele discute com uma proposta. Não comigo. Seria um alívio.
    >>  ............................................
  shy.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word. Might be the whole difference.
    >>  ............................................
    pt  ...Uma palavra. Pode ser a diferença toda.
    >>  ............................................
  shy.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Then he argues with a proposal, not with me.
    >>  ............................................
    pt  Aí ele discute com uma proposta, não comigo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word! One. And it might be the whole difference. Right, I'm trying that.
    >>  ............................................
    pt  ...Uma palavra! Uma. E pode ser a diferença toda. Certo, vou tentar isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor has to argue with a proposal instead of with me. Marvellous.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de comigo. Maravilhoso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.butcher.future.encourage/1
    en  ...One word! One. And it might be the whole difference. Right, I'm trying that.
    >>  ............................................
    pt  ...Uma palavra! Uma. E pode ser a diferença toda. Certo, vou tentar isso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.butcher.future.encourage/2
    en  Said that way the mayor has to argue with a proposal instead of with me. Marvellous.
    >>  ............................................
    pt  Dito assim o prefeito discute com uma proposta em vez de comigo. Maravilhoso.
    >>  ............................................
```

</details>


### Button `ask_after` — "Who takes the block after you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.future` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.future.ask_after` — accepted phrasings: "who takes the block after you"
  - the message must contain one of: `block`, `after`, `successor`
  - scored words: `block`(1.2), `after`(1.2), `successor`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.future.respond.ask_after
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.future.respond.ask_after   [30 chars]
    en  Who takes the block after you?
    >>  ............................................
    pt  Quem pega o cepo depois de você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.future.ask_after`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.future.ask_after
WHO    VILLAGER — what the player reads after pressing "Who takes the block after you?"
       spoken on: conversations.topic.work.butcher.future.respond, button `ask_after`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.future.ask_after`: the villager explains. Subject `work.butcher.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.future.ask_after/1   [79 chars]
    en  Nobody yet. It's an honest trade that everybody would rather somebody else did.
    >>  ............................................
    pt  Ninguém ainda. É um ofício honesto que todo mundo prefere que outro faça.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.future.ask_after/2   [81 chars]
    en  I'd take anyone who could keep the rule about names. That's the whole test, %1$s.
    >>  ............................................
    pt  Aceitaria qualquer um que guardasse a regra dos nomes. É todo o teste, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.future.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.butcher.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher` — e.g. "It's blunt work, but nobody in this village goes hungry on my watch. That's the whole ethics of it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.respond   [35 chars]
    en  That's the block and what it's for.
    >>  ............................................
    pt  É o cepo e pra que ele serve.
    >>  ............................................
```


### Button `ask_hard` — "What's the part you don't like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.identity` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.hard` — accepted phrasings: "what's the part you don't like"
  - the message must contain one of: `part`, `unpleasant`
  - scored words: `like`(0.6), `part`(1.0), `unpleasant`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.respond.ask_hard   [31 chars]
    en  What's the part you don't like?
    >>  ............................................
    pt  Qual é a parte que você não gosta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.butcher.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.hard
WHO    VILLAGER — what the player reads after pressing "What's the part you don't like?"
       spoken on: conversations.topic.work.butcher.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard`: the villager explains. Subject `work.butcher.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / ask_more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Nobody in this village goes hungry."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.identity` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.value` — accepted phrasings: "nobody in this village goes hungry"
  - the message must contain one of: `hungry`, `feed`
  - scored words: `hungry`(1.5), `feed`(1.2), `village`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.respond.value   [35 chars]
    en  Nobody in this village goes hungry.
    >>  ............................................
    pt  Ninguém neste vilarejo passa fome.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.butcher.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.butcher.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.value
WHO    VILLAGER — what the player reads after pressing "Nobody in this village goes hungry."
       spoken on: conversations.topic.work.butcher.respond, button `value`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.value`: the villager accepts. Subject `work.butcher.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.value/1   [62 chars]
    en  Not on my watch. That's the only sentence I'd want on a stone.
    >>  ............................................
    pt  Não no meu turno. É a única frase que eu queria numa lápide.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.value/2   [50 chars]
    en  That's the whole of it, said better than I say it.
    >>  ............................................
    pt  É isso mesmo, dito melhor do que eu digo.
    >>  ............................................
```


### Button `challenge` — "It's a cruel way to make a living."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.butcher.identity` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.challenge` — accepted phrasings: "it's a cruel way to make a living"
  - the message must contain one of: `cruel`, `killing`
  - scored words: `cruel`(1.5), `killing`(1.5), `living`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.respond.challenge   [34 chars]
    en  It's a cruel way to make a living.
    >>  ............................................
    pt  É um jeito cruel de ganhar a vida.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.butcher.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.butcher.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's a cruel way to make a living."
       spoken on: conversations.topic.work.butcher.respond, button `challenge`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.challenge.landed`: the villager resists. Subject `work.butcher.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.challenge.landed/1   [75 chars]
    en  It's an honest one. Cruel would be letting the winter do it slowly instead.
    >>  ............................................
    pt  É um jeito honesto. Cruel seria deixar o inverno fazer devagar.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.challenge.landed/2   [77 chars]
    en  I've thought that at four in the morning too, %1$s. Then people need feeding.
    >>  ............................................
    pt  Já pensei isso às quatro da manhã também, %1$s. Aí as pessoas precisam comer.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.butcher.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.butcher.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's a cruel way to make a living."
       spoken on: conversations.topic.work.butcher.respond, button `challenge`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.challenge.stung`: the villager resists. Subject `work.butcher.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.challenge.stung/1   [72 chars]
    en  ...Cruel. Right. Eat bread and turnips all winter and then say it to me.
    >>  ............................................
    pt  ...Cruel. Certo. Coma pão e nabo o inverno inteiro e aí me diga isso.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.challenge.stung/2   [59 chars]
    en  You'll be at my counter by Friday like everyone else, %1$s.
    >>  ............................................
    pt  Você vai estar no meu balcão até sexta, como todo mundo, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.butcher.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher.risk` — e.g. "One bad barrel and forty people are ill and none of them will know which meal did it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.risk.respond   [26 chars]
    en  That's what sits under it.
    >>  ............................................
    pt  É o que está por baixo.
    >>  ............................................
```


### Button `ask_barrel` — "How do you catch a bad barrel?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.risk` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.risk.ask_barrel` — accepted phrasings: "how do you catch a bad barrel"
  - the message must contain one of: `barrel`, `catch`, `spoiled`
  - scored words: `barrel`(1.5), `catch`(1.2), `spoiled`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.risk.respond.ask_barrel
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.risk.respond.ask_barrel   [30 chars]
    en  How do you catch a bad barrel?
    >>  ............................................
    pt  Como você pega um barril ruim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.risk.ask_barrel`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.risk.ask_barrel
WHO    VILLAGER — what the player reads after pressing "How do you catch a bad barrel?"
       spoken on: conversations.topic.work.butcher.risk.respond, button `ask_barrel`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.risk.ask_barrel`: the villager explains. Subject `work.butcher.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.risk.ask_barrel/1   [73 chars]
    en  Smell, at the top, weekly, all winter. It has never once been convenient.
    >>  ............................................
    pt  Cheiro, no topo, toda semana, o inverno todo. Nunca foi conveniente uma única vez.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.risk.ask_barrel/2   [78 chars]
    en  I open every one myself before it's sold. No one asked me to and no one knows.
    >>  ............................................
    pt  Abro cada um eu mesmo antes de vender. Nenhuma alma me pediu e nenhuma alma sabe.
    >>  ............................................
```


### Button `sympathise` — "The rule about names sounds like it costs you."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.risk` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.risk.sympathise` — accepted phrasings: "the rule about names sounds like it costs you"
  - the message must contain one of: `names`, `rule`, `costs`
  - scored words: `names`(1.5), `rule`(1.2), `costs`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.risk.respond.sympathise   [46 chars]
    en  The rule about names sounds like it costs you.
    >>  ............................................
    pt  A regra sobre nomes parece te custar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.butcher.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.butcher.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "The rule about names sounds like it costs you."
       spoken on: conversations.topic.work.butcher.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.risk.sympathise`: the villager accepts. Subject `work.butcher.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.risk.sympathise/1   [78 chars]
    en  ...It does. And the year I broke it was the worst year I've had at this trade.
    >>  ............................................
    pt  ...Custa. E o ano em que eu quebrei foi o pior que eu tive neste ofício.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.risk.sympathise/2   [69 chars]
    en  It costs the same amount every day, %1$s, which is how I can bear it.
    >>  ............................................
    pt  Custa a mesma quantidade todo dia, %1$s, e é assim que eu suporto.
    >>  ............................................
```


### Button `ask_broke` — "What happened the year you broke it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.risk` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.risk.ask_broke` — accepted phrasings: "what happened the year you broke it"
  - the message must contain one of: `broke`, `calf`, `exception`
  - scored words: `broke`(1.5), `calf`(1.2), `exception`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.risk.respond.ask_broke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.risk.respond.ask_broke   [36 chars]
    en  What happened the year you broke it?
    >>  ............................................
    pt  O que aconteceu no ano em que você quebrou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.risk.ask_broke`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.risk.ask_broke
WHO    VILLAGER — what the player reads after pressing "What happened the year you broke it?"
       spoken on: conversations.topic.work.butcher.risk.respond, button `ask_broke`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.risk.ask_broke`: the villager explains. Subject `work.butcher.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.risk.ask_broke/1   [91 chars]
    en  A calf that followed me about. I gave her a name in March and I could not do it in October.
    >>  ............................................
    pt  Uma bezerra que me seguia. Dei um nome em março e não consegui fazer em outubro.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.risk.ask_broke/2   [90 chars]
    en  I sold her on to another village at a loss and told the mayor it was a bookkeeping matter.
    >>  ............................................
    pt  Vendi pra outro vilarejo com prejuízo e disse ao prefeito que era questão de contabilidade.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.risk.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.butcher.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher.task` — e.g. "Breaking down two carcasses before the heat gets into the afternoon. It's a race I usually win."


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.task.respond   [23 chars]
    en  That's the block today.
    >>  ............................................
    pt  É o cepo hoje.
    >>  ............................................
```


### Button `ask_race` — "What happens if the heat wins?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.task` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.task.ask_race` — accepted phrasings: "what happens if the heat wins"
  - the message must contain one of: `heat`, `spoil`, `race`
  - scored words: `heat`(1.5), `spoil`(1.5), `race`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.task.respond.ask_race
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.task.respond.ask_race   [30 chars]
    en  What happens if the heat wins?
    >>  ............................................
    pt  O que acontece se o calor ganhar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.task.ask_race`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.task.ask_race
WHO    VILLAGER — what the player reads after pressing "What happens if the heat wins?"
       spoken on: conversations.topic.work.butcher.task.respond, button `ask_race`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.task.ask_race`: the villager explains. Subject `work.butcher.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.task.ask_race/1   [85 chars]
    en  Then it's dog meat and an apology to the family that raised it. I've done that twice.
    >>  ............................................
    pt  Aí vira comida de cachorro e um pedido de desculpa à família que criou. Já fiz isso duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.task.ask_race/2   [68 chars]
    en  Nothing you'd want in your mouth by Thursday, %1$s. So I don't lose.
    >>  ............................................
    pt  Nada que você queira na boca até quinta, %1$s. Então eu não perco.
    >>  ............................................
```


### Button `offer_hands` — "I can pack barrels."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.butcher.task` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.task.offer_hands` — accepted phrasings: "i can pack barrels"
  - the message must contain one of: `barrels`, `pack`, `salt`
  - scored words: `barrels`(1.5), `pack`(1.2), `salt`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.task.respond.offer_hands   [19 chars]
    en  I can pack barrels.
    >>  ............................................
    pt  Eu posso encher barris.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.butcher.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.butcher.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can pack barrels."
       spoken on: conversations.topic.work.butcher.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.task.offer_hands`: the villager accepts. Subject `work.butcher.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.task.offer_hands/1   [78 chars]
    en  ...You can. Layer, salt, press, and don't be shy with the salt. Shy salt rots.
    >>  ............................................
    pt  ...Pode. Camada, sal, prensa, e não seja tímido com o sal. Sal tímido apodrece.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.task.offer_hands/2   [79 chars]
    en  Then start at the end of the row. Hands clean first, %1$s, and I do mean first.
    >>  ............................................
    pt  Então comece no fim da fileira. Mãos limpas antes, %1$s, e eu digo antes.
    >>  ............................................
```


### Button `ask_february` — "Is February really that close a thing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.task` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.task.ask_february` — accepted phrasings: "is february really that close a thing"
  - the message must contain one of: `february`, `winter`, `close`
  - scored words: `february`(1.5), `winter`(1.2), `close`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.task.respond.ask_february
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.task.respond.ask_february   [38 chars]
    en  Is February really that close a thing?
    >>  ............................................
    pt  Fevereiro é tão apertado assim mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.task.ask_february`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.task.ask_february
WHO    VILLAGER — what the player reads after pressing "Is February really that close a thing?"
       spoken on: conversations.topic.work.butcher.task.respond, button `ask_february`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.task.ask_february`: the villager explains. Subject `work.butcher.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.task.ask_february/1   [84 chars]
    en  Some years it's a fortnight of margin. Nobody notices, because I'm the one counting.
    >>  ............................................
    pt  Em alguns anos é uma quinzena de margem. Ninguém repara, porque eu é que conto.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.task.ask_february/2   [87 chars]
    en  Two winters ago we were four barrels short and I've never told anyone how close it was.
    >>  ............................................
    pt  Dois invernos atrás faltaram quatro barris e eu nunca contei a ninguém o quão perto foi.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.task.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.butcher.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.butcher.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.butcher.village` — e.g. "Every table with meat on it in this place has had my hands on that meat first."


```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.butcher.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.butcher.village.respond   [22 chars]
    en  That's where it lands.
    >>  ............................................
    pt  É onde isso cai.
    >>  ............................................
```


### Button `ask_decide` — "How do you decide?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.village` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.village.ask_decide` — accepted phrasings: "how do you decide"
  - the message must contain one of: `decide`, `order`, `hungry`
  - scored words: `decide`(1.5), `order`(1.2), `hungry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.village.respond.ask_decide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.village.respond.ask_decide   [18 chars]
    en  How do you decide?
    >>  ............................................
    pt  Como você decide?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.village.ask_decide`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.village.ask_decide
WHO    VILLAGER — what the player reads after pressing "How do you decide?"
       spoken on: conversations.topic.work.butcher.village.respond, button `ask_decide`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.village.ask_decide`: the villager explains. Subject `work.butcher.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.village.ask_decide/1   [77 chars]
    en  Children, the ill, the ones working outside. Then everyone else, and then me.
    >>  ............................................
    pt  Crianças, doentes, quem trabalha fora. Depois todo o resto, e depois eu.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.village.ask_decide/2   [84 chars]
    en  Quietly, and without telling anybody the order, %1$s. The order would start a fight.
    >>  ............................................
    pt  Em silêncio, e sem contar a ordem a ninguém, %1$s. A ordem começaria uma briga.
    >>  ............................................
```


### Button `say_thanks` — "Somebody has to hold that, and you do."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.butcher.village` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.village.say_thanks` — accepted phrasings: "somebody has to hold that, and you do"
  - the message must contain one of: `holding`, `hold`
  - scored words: `holding`(1.5), `somebody`(0.8), `hold`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.village.respond.say_thanks   [38 chars]
    en  Somebody has to hold that, and you do.
    >>  ............................................
    pt  Alguém tem que segurar isso, e você segura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.butcher.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.butcher.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Somebody has to hold that, and you do."
       spoken on: conversations.topic.work.butcher.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.village.say_thanks`: the villager accepts. Subject `work.butcher.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.village.say_thanks/1   [84 chars]
    en  ...Aye. Well. That's the first time anyone's put it as holding rather than as trade.
    >>  ............................................
    pt  ...É. Bom. É a primeira vez que alguém chama de segurar em vez de negócio.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.village.say_thanks/2   [70 chars]
    en  I'd rather nobody knew I was holding it at all. But — thank you, %1$s.
    >>  ............................................
    pt  Eu preferia que ninguém soubesse que eu seguro. Mas — obrigado, %1$s.
    >>  ............................................
```


### Button `ask_last` — "You put yourself last?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.butcher.village` · offered only once the villager has actually said `work:butcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.butcher.village.ask_last` — accepted phrasings: "you put yourself last"
  - the message must contain one of: `last`, `yourself`
  - scored words: `last`(1.5), `yourself`(1.2), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.village.respond.ask_last
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.village.respond.ask_last   [22 chars]
    en  You put yourself last?
    >>  ............................................
    pt  Você se põe por último?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.butcher.village.ask_last`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.butcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "How do you get through the winter?" | "Keep well fed."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.village.ask_last
WHO    VILLAGER — what the player reads after pressing "You put yourself last?"
       spoken on: conversations.topic.work.butcher.village.respond, button `ask_last`
       leaves the player on: conversations.topic.work.butcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.village.ask_last`: the villager explains. Subject `work.butcher.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.butcher.village.ask_last/1   [69 chars]
    en  It's the only part of the arrangement I'm entitled to decide, so yes.
    >>  ............................................
    pt  É a única parte do arranjo que eu tenho direito de decidir, então sim.
    >>  ............................................
  dialogue.conversations.work.prof.butcher.village.ask_last/2   [67 chars]
    en  It's not noble. It's that I'd have to look at them in spring, %1$s.
    >>  ............................................
    pt  Não é nobreza. É que eu teria que olhar pra eles na primavera, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.butcher.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.butcher.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.butcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.butcher.village.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.butcher.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.left`: the villager accepts. Subject `work.butcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.butcher.followup / leave; conversations.scene.work.butcher.hard_slaughter.blocked.respond / leave; conversations.scene.work.butcher.hard_slaughter.succeeded.respond / leave; conversations.scene.work.butcher.spoiling_store.blocked.respond / leave; conversations.scene.work.butcher.spoiling_store.failed.respond / leave; conversations.scene.work.butcher.the_hands.active.respond / leave; conversations.scene.work.butcher.the_hands.succeeded.respond / leave; conversations.topic.work.butcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.butcher.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

