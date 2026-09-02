# Work talk with a cleric

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.cleric.closed_door.blocked.respond`](#conversations-scene-work-cleric-closed-door-blocked-respond)
- [`conversations.scene.work.cleric.closed_door.succeeded.respond`](#conversations-scene-work-cleric-closed-door-succeeded-respond)
- [`conversations.scene.work.cleric.followup`](#conversations-scene-work-cleric-followup)
- [`conversations.scene.work.cleric.no_answer.active.respond`](#conversations-scene-work-cleric-no-answer-active-respond)
- [`conversations.scene.work.cleric.no_answer.succeeded.respond`](#conversations-scene-work-cleric-no-answer-succeeded-respond)
- [`conversations.scene.work.cleric.sitting_up.active.respond`](#conversations-scene-work-cleric-sitting-up-active-respond)
- [`conversations.scene.work.cleric.sitting_up.succeeded.respond`](#conversations-scene-work-cleric-sitting-up-succeeded-respond)
- [`conversations.topic.work.cleric.craft.respond`](#conversations-topic-work-cleric-craft-respond)
- [`conversations.topic.work.cleric.followup`](#conversations-topic-work-cleric-followup)
- [`conversations.topic.work.cleric.future.respond`](#conversations-topic-work-cleric-future-respond)
- [`conversations.topic.work.cleric.respond`](#conversations-topic-work-cleric-respond)
- [`conversations.topic.work.cleric.risk.respond`](#conversations-topic-work-cleric-risk-respond)
- [`conversations.topic.work.cleric.task.respond`](#conversations-topic-work-cleric-task-respond)
- [`conversations.topic.work.cleric.village.respond`](#conversations-topic-work-cleric-village-respond)

---

## `conversations.scene.work.cleric.closed_door.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.closed_door.blocked` — e.g. "%2$s needs somebody and I get %3$s every time, and I have started to wonder whether I am the wrong somebody."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.closed_door.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.respond   [12 chars]
    en  Your rounds.
    >>  ............................................
    pt  Suas visitas.
    >>  ............................................
```


### Button `ask_what_they_need` — "What do they actually need?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.closed_door.blocked` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.closed_door.blocked.ask_what_they_need` — accepted phrasings: "what do they actually need"; "what do they actually need"; "what would help that household"
  - the message must contain one of: `need`, `household`, `help`
  - scored words: `need`(1.8), `household`(1.8), `help`(1.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.ask_what_they_need
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.closed_door.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.ask_what_they_need   [27 chars]
    en  What do they actually need?
    >>  ............................................
    pt  Do que eles realmente precisam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.small_kindnesses`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.closed_door"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What do they actually need?"
       spoken on: conversations.scene.work.cleric.closed_door.blocked.respond, button `ask_what_they_need`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.closed_door.blocked.explained`: the villager explains. Subject `work.cleric.small_kindnesses`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.explained/1   [118 chars]
    en  Firewood and somebody to sit with the youngest for two hours. Nothing spiritual whatsoever, which is usually the case.
    >>  ............................................
    pt  Lenha e alguém para ficar com o caçula por duas horas. Nada espiritual, o que costuma ser o caso.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked.explained/2   [119 chars]
    en  To be asked about something other than their trouble. %2$s has been the object of concern for a year and it is wearing.
    >>  ............................................
    pt  Ser perguntada sobre outra coisa que não a própria desgraça. %2$s é objeto de preocupação há um ano, e isso cansa.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked.explained/3   [119 chars]
    en  Honestly? For me to stop coming for a month and then come back. I have not decided whether that is wisdom or giving up.
    >>  ............................................
    pt  Sinceramente? Que eu pare de ir por um mês e depois volte. Ainda não decidi se isso é sabedoria ou desistência.
    >>  ............................................
```


### Button `advise_asking_them` — "Ask them what they want instead."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cleric.closed_door.blocked` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.closed_door.blocked.advise_asking_them` — accepted phrasings: "ask them what they want instead"; "ask them what they want instead"; "let them say what they want"
  - the message must contain one of: `want`, `ask`
  - scored words: `want`(1.8), `ask`(1.8), `instead`(0.8), `let`(0.8), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.advise_asking_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.closed_door.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.advise_asking_them   [32 chars]
    en  Ask them what they want instead.
    >>  ............................................
    pt  Pergunte a eles o que querem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.cleric.small_kindnesses`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.closed_door"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Ask them what they want instead."
       spoken on: conversations.scene.work.cleric.closed_door.blocked.respond, button `advise_asking_them`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.closed_door.blocked.accepted`: the villager accepts. Subject `work.cleric.small_kindnesses`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.accepted/1   [118 chars]
    en  I have been arriving with an answer instead of a question for about eleven years. You are the first person to name it.
    >>  ............................................
    pt  Faz uns onze anos que eu chego com uma resposta em vez de uma pergunta. Você é a primeira pessoa a nomear isso.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked.accepted/2   [114 chars]
    en  Yes. And I will ask from the doorway rather than from a chair, because sitting down turns a question into a visit.
    >>  ............................................
    pt  Sim. E vou perguntar da porta, não da cadeira, porque sentar transforma uma pergunta em visita.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked.accepted/3   [113 chars]
    en  That is right and it frightens me slightly, because they may say nothing, and then I will have to accept nothing.
    >>  ............................................
    pt  É o certo e me assusta um pouco, porque podem não dizer nada, e aí eu vou ter que aceitar nada.
    >>  ............................................
```


### Button `say_showing_up_counts` — "Turning up counts for something."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cleric.closed_door.blocked` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.closed_door.blocked.say_showing_up_counts` — accepted phrasings: "turning up counts for something"; "turning up counts for something"; "just showing up matters"
  - the message must contain one of: `turning`, `showing`, `counts`
  - scored words: `turning`(1.8), `showing`(1.8), `counts`(1.8), `something`(0.8), `matters`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.say_showing_up_counts
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.closed_door.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.say_showing_up_counts   [32 chars]
    en  Turning up counts for something.
    >>  ............................................
    pt  Aparecer já conta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +1  _(recorded under topic `work.cleric.small_kindnesses`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.closed_door"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Turning up counts for something."
       spoken on: conversations.scene.work.cleric.closed_door.blocked.respond, button `say_showing_up_counts`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.closed_door.blocked.steadied`: the villager accepts. Subject `work.cleric.small_kindnesses`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.steadied/1   [136 chars]
    en  It is most of what I have. Some weeks I would like it to be more, and then I remember who taught me the trade and what she actually did.
    >>  ............................................
    pt  É quase tudo o que eu tenho. Em algumas semanas eu queria que fosse mais, e aí lembro de quem me ensinou o ofício e do que ela de fato fazia.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked.steadied/2   [107 chars]
    en  Thank you. It is a small claim and it is one I can keep, which is more than can be said for the large ones.
    >>  ............................................
    pt  Obrigada. É uma promessa pequena e é uma que eu consigo cumprir, o que já é mais do que se pode dizer das grandes.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked.steadied/3   [103 chars]
    en  I hope so. Nobody ever writes down the visits where nothing happened, and those are almost all of them.
    >>  ............................................
    pt  Espero que sim. Ninguém anota as visitas em que nada aconteceu, e são quase todas.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cleric.closed_door.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.closed_door.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked.respond.leave   [37 chars]
    en  I'll let you get on with your rounds.
    >>  ............................................
    pt  Vou deixar você seguir suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your rounds."
       spoken on: conversations.scene.work.cleric.closed_door.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.cleric.leave/1   [52 chars]
    en  There's always a bottle wanting turning. Off you go.
    >>  ............................................
    pt  Sempre tem um frasco pra virar. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.leave/2   [16 chars]
    en  Go gently, %1$s.
    >>  ............................................
    pt  Vá com calma, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.cleric.closed_door.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.closed_door.succeeded` — e.g. "%2$s asked me in. Not for anything. They wanted somebody to eat with, and I have thought about it all week."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.closed_door.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.succeeded.respond   [15 chars]
    en  That household.
    >>  ............................................
    pt  Aquela casa.
    >>  ............................................
```


### Button `ask_what_worked` — "What made the difference?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.cleric.closed_door.succeeded` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.closed_door.succeeded.ask_what_worked` — accepted phrasings: "what made the difference"; "what made the difference in the end"; "which part actually worked"
  - the message must contain one of: `difference`, `worked`
  - scored words: `difference`(1.8), `worked`(1.8), `made`(0.8), `end`(0.8), `which`(0.8), `part`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.succeeded.respond.ask_what_worked
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.closed_door.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.succeeded.respond.ask_what_worked   [25 chars]
    en  What made the difference?
    >>  ............................................
    pt  O que fez diferença?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.small_kindnesses`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cleric.closed_door"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "What made the difference?"
       spoken on: conversations.scene.work.cleric.closed_door.succeeded.respond, button `ask_what_worked`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.closed_door.succeeded.explained`: the villager explains. Subject `work.cleric.small_kindnesses`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.succeeded.explained/1   [105 chars]
    en  Asking from the doorway. It let them refuse cheaply, and once refusing was cheap they stopped needing to.
    >>  ............................................
    pt  Perguntar da porta. Isso deixou a recusa barata, e quando recusar ficou barato eles pararam de precisar recusar.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.succeeded.explained/2   [116 chars]
    en  I brought nothing. Eleven years of arriving with bread, and the week I arrive empty-handed is the week I get let in.
    >>  ............................................
    pt  Não levei nada. Onze anos chegando com pão, e a semana em que chego de mãos vazias é a semana em que me deixam entrar.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.succeeded.explained/3   [109 chars]
    en  Time, mostly. I would love to claim a clever sentence. It was ninety visits and one of them happened to land.
    >>  ............................................
    pt  Tempo, principalmente. Eu adoraria alegar uma frase inteligente. Foram noventa visitas e uma delas por acaso acertou.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cleric.closed_door.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.closed_door.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.succeeded.respond.leave   [37 chars]
    en  I'll let you get on with your rounds.
    >>  ............................................
    pt  Vou deixar você seguir suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your rounds."
       spoken on: conversations.scene.work.cleric.closed_door.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cleric.followup`

**Reached from 11 route(s):** `conversations.scene.work.cleric.closed_door.blocked.respond` / `ask_what_they_need`; `conversations.scene.work.cleric.closed_door.blocked.respond` / `advise_asking_them`; `conversations.scene.work.cleric.closed_door.blocked.respond` / `say_showing_up_counts`; `conversations.scene.work.cleric.closed_door.succeeded.respond` / `ask_what_worked`; `conversations.scene.work.cleric.no_answer.active.respond` / `ask_what_she_says`; `conversations.scene.work.cleric.no_answer.active.respond` / `back_the_honesty`; `conversations.scene.work.cleric.no_answer.succeeded.respond` / `note_it_held`; `conversations.scene.work.cleric.sitting_up.active.respond` / `ask_what_it_is_like`; `conversations.scene.work.cleric.sitting_up.active.respond` / `offer_food`; `conversations.scene.work.cleric.sitting_up.active.respond` / `say_rest_matters`; `conversations.scene.work.cleric.sitting_up.succeeded.respond` / `honour_the_hours`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.closed_door.blocked.accepted` — e.g. "I have been arriving with an answer instead of a question for about eleven years. You are the first person to name it."
- `conversations.scene.work.cleric.closed_door.blocked.explained` — e.g. "Firewood and somebody to sit with the youngest for two hours. Nothing spiritual whatsoever, which is usually the case."
- `conversations.scene.work.cleric.closed_door.blocked.steadied` — e.g. "It is most of what I have. Some weeks I would like it to be more, and then I remember who taught me the trade and what she actually did."
- `conversations.scene.work.cleric.closed_door.succeeded.explained` — e.g. "Asking from the doorway. It let them refuse cheaply, and once refusing was cheap they stopped needing to."
- `conversations.scene.work.cleric.no_answer.active.explained` — e.g. "That I do not know, and that I will come back on Thursday. The second half is the part that does the work."
- `conversations.scene.work.cleric.no_answer.active.steadied` — e.g. "I will. It costs me something every time and I have never regretted it a year later, which is the only test I trust."
- `conversations.scene.work.cleric.no_answer.succeeded.acknowledged` — e.g. "It held. I want to be clear that for eight months it looked exactly like failure, and I had no way of knowing which it was."
- `conversations.scene.work.cleric.sitting_up.active.accepted` — e.g. "Yes, and leave it at the door rather than handing it over. A family in that state cannot manage being thanked at."
- `conversations.scene.work.cleric.sitting_up.active.explained` — e.g. "Very little. I keep the fire going and I do not fill the silence, because filling it is for me and not for them."
- `conversations.scene.work.cleric.sitting_up.active.qualified` — e.g. "I do, and I will, and not this week. I have said that sentence to other people and been unconvinced by it then as well."
- `conversations.scene.work.cleric.sitting_up.succeeded.acknowledged` — e.g. "They count to four people and that is the correct number. This is not work that scales, and I have made my peace with that."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.followup   [17 chars]
    en  Anything further?
    >>  ............................................
    pt  Mais alguma coisa além disso?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of being asked for comfort?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.cleric.*` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.followup.ask_more` — accepted phrasings: "whats the hardest part of being asked for comfort"; "what is the hardest part of being asked for comfort"; "hardest thing about giving comfort"
  - the message must contain one of: `hardest`, `comfort`
  - scored words: `hardest`(1.8), `comfort`(1.8), `whats`(0.8), `part`(0.8), `being`(0.8), `asked`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.followup.ask_more   [51 chars]
    en  What's the hardest part of being asked for comfort?
    >>  ............................................
    pt  Qual é a parte mais difícil de ser procurada por consolo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of being asked for comfort?"
       spoken on: conversations.scene.work.cleric.followup, button `ask_more`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.hard`: the villager explains. Subject `work.cleric.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.cleric.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.cleric.hard/1   [89 chars]
    en  Sit with them. That's the part nobody trains you for and it's most of the job some weeks.
    >>  ............................................
    pt  Sento com a pessoa. É a parte pra qual ninguém te treina, e em algumas semanas é quase o trabalho todo.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.hard/2   [78 chars]
    en  I stop pretending, %1$s. Half of what harms people is being told a lie kindly.
    >>  ............................................
    pt  Eu paro de fingir, %1$s. Metade do que machuca as pessoas é uma mentira dita com gentileza.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.cleric.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.followup.leave   [30 chars]
    en  I'll leave you to your rounds.
    >>  ............................................
    pt  Vou deixar você com suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your rounds."
       spoken on: conversations.scene.work.cleric.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cleric.no_answer.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.no_answer.active` — e.g. "The family of %2$s asked me why, and I said I did not know, and I watched that land badly."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.no_answer.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active.respond   [27 chars]
    en  The question you get asked.
    >>  ............................................
    pt  A pergunta que te fazem.
    >>  ............................................
```


### Button `ask_what_she_says` — "So what do you say to them?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.cleric.no_answer.active` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.no_answer.active.ask_what_she_says` — accepted phrasings: "so what do you say to them"; "so what do you say to them"; "what words do you use then"
  - the message must contain one of: `say`, `words`
  - scored words: `say`(1.8), `words`(1.8), `use`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active.respond.ask_what_she_says
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.no_answer.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active.respond.ask_what_she_says   [27 chars]
    en  So what do you say to them?
    >>  ............................................
    pt  Então o que você diz a eles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.cleric.doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.no_answer"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active.explained
WHO    VILLAGER — what the player reads after pressing "So what do you say to them?"
       spoken on: conversations.scene.work.cleric.no_answer.active.respond, button `ask_what_she_says`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.no_answer.active.explained`: the villager explains. Subject `work.cleric.doubt`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active.explained/1   [106 chars]
    en  That I do not know, and that I will come back on Thursday. The second half is the part that does the work.
    >>  ............................................
    pt  Que eu não sei, e que volto na quinta. A segunda metade é a parte que faz o trabalho.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.active.explained/2   [109 chars]
    en  I say their person's name and something true about them. It is not an answer. It is the nearest honest thing.
    >>  ............................................
    pt  Digo o nome da pessoa deles e algo verdadeiro sobre ela. Não é resposta. É a coisa honesta mais próxima.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.active.explained/3   [128 chars]
    en  Nothing, sometimes. I have learned that a silence held steadily is heard as company, and a filled silence is heard as a lecture.
    >>  ............................................
    pt  Nada, às vezes. Aprendi que um silêncio sustentado com firmeza é ouvido como companhia, e um silêncio preenchido é ouvido como sermão.
    >>  ............................................
```


### Button `back_the_honesty` — "Stay honest about the limits."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.no_answer.active` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.no_answer.active.back_the_honesty` — accepted phrasings: "stay honest about the limits"; "keep telling them the truth"; "stay honest about the limits"
  - the message must contain one of: `truth`, `honest`, `limits`
  - scored words: `truth`(1.8), `honest`(1.8), `limits`(1.8), `stay`(0.8), `keep`(0.8), `telling`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active.respond.back_the_honesty
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.no_answer.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active.respond.back_the_honesty   [29 chars]
    en  Stay honest about the limits.
    >>  ............................................
    pt  Seja honesta sobre os limites.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cleric.doubt.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `work.cleric.doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.no_answer"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active.steadied
WHO    VILLAGER — what the player reads after pressing "Stay honest about the limits."
       spoken on: conversations.scene.work.cleric.no_answer.active.respond, button `back_the_honesty`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.no_answer.active.steadied`: the villager accepts. Subject `work.cleric.doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active.steadied/1   [116 chars]
    en  I will. It costs me something every time and I have never regretted it a year later, which is the only test I trust.
    >>  ............................................
    pt  Vou. Custa algo toda vez e eu nunca me arrependi um ano depois, que é o único teste em que confio.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.active.steadied/2   [127 chars]
    en  Thank you. There are people in this trade who found the comfortable sentence, and I have watched what it does to them by fifty.
    >>  ............................................
    pt  Obrigada. Existem pessoas neste ofício que acharam a frase confortável, e eu vi o que isso faz com elas até os cinquenta.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.active.steadied/3   [142 chars]
    en  The strange part is that families remember the honesty warmly. Not at the time. Years later, when they are checking whether they were lied to.
    >>  ............................................
    pt  O curioso é que as famílias lembram da honestidade com carinho. Não na hora. Anos depois, quando estão conferindo se mentiram para elas.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cleric.no_answer.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.no_answer.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active.respond.leave   [37 chars]
    en  I'll let you get on with your rounds.
    >>  ............................................
    pt  Vou deixar você seguir suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your rounds."
       spoken on: conversations.scene.work.cleric.no_answer.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cleric.no_answer.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.no_answer.succeeded` — e.g. "They came to find me last week. Not for comfort. To tell me that not being lied to had mattered."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.no_answer.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.succeeded.respond   [12 chars]
    en  That family.
    >>  ............................................
    pt  Aquela família.
    >>  ............................................
```


### Button `note_it_held` — "The honesty held up."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cleric.no_answer.succeeded` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.no_answer.succeeded.note_it_held` — accepted phrasings: "the honesty held up"; "the honesty held up in the end"; "being truthful held up"
  - the message must contain one of: `honesty`, `truthful`, `held`
  - scored words: `honesty`(1.8), `truthful`(1.8), `held`(1.8), `end`(0.8), `being`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.succeeded.respond.note_it_held
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.no_answer.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.succeeded.respond.note_it_held   [20 chars]
    en  The honesty held up.
    >>  ............................................
    pt  A honestidade se sustentou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.cleric.doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cleric.no_answer"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "The honesty held up."
       spoken on: conversations.scene.work.cleric.no_answer.succeeded.respond, button `note_it_held`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.no_answer.succeeded.acknowledged`: the villager accepts. Subject `work.cleric.doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.succeeded.acknowledged/1   [123 chars]
    en  It held. I want to be clear that for eight months it looked exactly like failure, and I had no way of knowing which it was.
    >>  ............................................
    pt  Se sustentou. Quero deixar claro que por oito meses aquilo parecia exatamente fracasso, e eu não tinha como saber qual era.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.succeeded.acknowledged/2   [101 chars]
    en  Thank you. I will remember this the next time somebody asks me why, which will probably be this week.
    >>  ............................................
    pt  Obrigada. Vou lembrar disso na próxima vez em que alguém me perguntar por quê, o que provavelmente será esta semana.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.succeeded.acknowledged/3   [86 chars]
    en  It held because I kept coming back. Honesty on its own is just a cold thing said once.
    >>  ............................................
    pt  Se sustentou porque eu continuei voltando. Honestidade sozinha é só uma coisa fria dita uma vez.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cleric.no_answer.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.no_answer.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.succeeded.respond.leave   [37 chars]
    en  I'll let you get on with your rounds.
    >>  ............................................
    pt  Vou deixar você seguir suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your rounds."
       spoken on: conversations.scene.work.cleric.no_answer.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cleric.sitting_up.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.sitting_up.active` — e.g. "I sat up with %2$s through %3$s. There is nothing to report. That is what sitting up is."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.sitting_up.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.respond   [11 chars]
    en  Last night.
    >>  ............................................
    pt  Ontem à noite.
    >>  ............................................
```


### Button `ask_what_it_is_like` — "What do you do through those hours?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.cleric.sitting_up.active` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.sitting_up.active.ask_what_it_is_like` — accepted phrasings: "what do you do through those hours"; "what do you do through those hours"; "how do the hours pass for you"
  - the message must contain one of: `hours`, `pass`
  - scored words: `hours`(1.8), `pass`(1.8), `through`(0.8), `those`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.respond.ask_what_it_is_like
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.sitting_up.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.respond.ask_what_it_is_like   [35 chars]
    en  What do you do through those hours?
    >>  ............................................
    pt  O que você faz nessas horas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.cleric.unseen_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.sitting_up"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.explained
WHO    VILLAGER — what the player reads after pressing "What do you do through those hours?"
       spoken on: conversations.scene.work.cleric.sitting_up.active.respond, button `ask_what_it_is_like`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.sitting_up.active.explained`: the villager explains. Subject `work.cleric.unseen_hours`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.explained/1   [112 chars]
    en  Very little. I keep the fire going and I do not fill the silence, because filling it is for me and not for them.
    >>  ............................................
    pt  Muito pouco. Mantenho o fogo e não preencho o silêncio, porque preencher é para mim e não para eles.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active.explained/2   [86 chars]
    en  I count breaths, which sounds grim and is actually the calmest thing I know how to do.
    >>  ............................................
    pt  Conto respirações, o que soa sombrio e é, na verdade, a coisa mais calma que eu sei fazer.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active.explained/3   [132 chars]
    en  I let them talk if they want to and I do not answer the large questions, because I have no answers and a made-up one would be theft.
    >>  ............................................
    pt  Deixo falarem se quiserem e não respondo às perguntas grandes, porque não tenho respostas e uma inventada seria roubo.
    >>  ............................................
```


### Button `offer_food` — "I'll bring bread for the house."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.cleric.sitting_up.active` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.sitting_up.active.offer_food` — accepted phrasings: "ill bring bread for the house"; "i can bring bread for the house"; "let me bring the household some bread"
  - the message must contain one of: `bread`
  - scored words: `bread`(1.8), `ill`(0.8), `bring`(0.8), `house`(0.8), `let`(0.8), `household`(0.8), `some`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.respond.offer_food
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.sitting_up.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.respond.offer_food   [31 chars]
    en  I'll bring bread for the house.
    >>  ............................................
    pt  Vou levar pão para a casa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cleric.vigil.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.cleric.unseen_hours`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.sitting_up", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.sitting_up", "obligation": "commitment:work.cleric.bring_food"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.cleric.bring_food"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring bread for the house."
       spoken on: conversations.scene.work.cleric.sitting_up.active.respond, button `offer_food`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.sitting_up.active.accepted`: the villager accepts. Subject `work.cleric.unseen_hours`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.accepted/1   [113 chars]
    en  Yes, and leave it at the door rather than handing it over. A family in that state cannot manage being thanked at.
    >>  ............................................
    pt  Sim, e deixe na porta em vez de entregar na mão. Uma família nesse estado não dá conta de ser agradecida.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active.accepted/2   [108 chars]
    en  That is exactly the right size of help. Nobody in that house has cooked in four days and nobody will say so.
    >>  ............................................
    pt  É exatamente o tamanho certo de ajuda. Ninguém naquela casa cozinhou em quatro dias e ninguém vai dizer isso.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active.accepted/3   [133 chars]
    en  Thank you. I will tell them it came from you, because being helped by a neighbour is easier to carry than being helped by the church.
    >>  ............................................
    pt  Obrigada. Vou dizer que veio de você, porque ser ajudado por um vizinho é mais fácil de carregar do que ser ajudado pela igreja.
    >>  ............................................
```


### Button `say_rest_matters` — "You need sleep as well."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cleric.sitting_up.active` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.sitting_up.active.say_rest_matters` — accepted phrasings: "you need sleep as well"; "you need sleep as well"; "you should rest too"
  - the message must contain one of: `sleep`, `rest`
  - scored words: `sleep`(1.8), `rest`(1.8), `need`(0.8), `well`(0.8), `should`(0.8), `too`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.respond.say_rest_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.sitting_up.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.respond.say_rest_matters   [23 chars]
    en  You need sleep as well.
    >>  ............................................
    pt  Você também precisa dormir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4  _(recorded under topic `work.cleric.unseen_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cleric.sitting_up"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.qualified
WHO    VILLAGER — what the player reads after pressing "You need sleep as well."
       spoken on: conversations.scene.work.cleric.sitting_up.active.respond, button `say_rest_matters`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.sitting_up.active.qualified`: the villager qualifys. Subject `work.cleric.unseen_hours`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.qualified/1   [119 chars]
    en  I do, and I will, and not this week. I have said that sentence to other people and been unconvinced by it then as well.
    >>  ............................................
    pt  Preciso, e vou, e não esta semana. Já disse essa frase a outras pessoas e também não me convenci na época.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active.qualified/2   [112 chars]
    en  You are right. There is a version of this work that eats the person doing it and I have watched it happen twice.
    >>  ............................................
    pt  Você tem razão. Existe uma versão deste trabalho que devora quem o faz, e eu já vi acontecer duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active.qualified/3   [121 chars]
    en  I sleep in the afternoons. It is not the same and it is enough for a fortnight, and this will be over inside a fortnight.
    >>  ............................................
    pt  Durmo à tarde. Não é a mesma coisa e dá para duas semanas, e isso vai acabar dentro de duas semanas.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cleric.sitting_up.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.sitting_up.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active.respond.leave   [37 chars]
    en  I'll let you get on with your rounds.
    >>  ............................................
    pt  Vou deixar você seguir suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your rounds."
       spoken on: conversations.scene.work.cleric.sitting_up.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cleric.sitting_up.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cleric.sitting_up.succeeded` — e.g. "%2$s is through it. Sitting up did not cure anything. It meant nobody was alone at four in the morning."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cleric.sitting_up.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded.respond   [13 chars]
    en  Those nights.
    >>  ............................................
    pt  Aquelas noites.
    >>  ............................................
```


### Button `honour_the_hours` — "Nobody sees those hours but they count."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cleric.sitting_up.succeeded` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cleric.sitting_up.succeeded.honour_the_hours` — accepted phrasings: "nobody sees those hours but they count"; "nobody sees those hours but they count"; "those unseen hours count"
  - the message must contain one of: `hours`, `count`
  - scored words: `hours`(1.8), `count`(1.8), `nobody`(0.8), `sees`(0.8), `those`(0.8), `but`(0.8), `unseen`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.succeeded.respond.honour_the_hours
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.sitting_up.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded.respond.honour_the_hours   [39 chars]
    en  Nobody sees those hours but they count.
    >>  ............................................
    pt  Ninguém vê essas horas, mas elas contam.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, respect +3  _(recorded under topic `work.cleric.unseen_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cleric.sitting_up"}
- Then opens: `conversations.scene.work.cleric.followup`
- …where the player's next choices will be: "What's the hardest part of being asked for comfort?" | "I'll leave you to your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Nobody sees those hours but they count."
       spoken on: conversations.scene.work.cleric.sitting_up.succeeded.respond, button `honour_the_hours`
       leaves the player on: conversations.scene.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.sitting_up.succeeded.acknowledged`: the villager accepts. Subject `work.cleric.unseen_hours`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded.acknowledged/1   [123 chars]
    en  They count to four people and that is the correct number. This is not work that scales, and I have made my peace with that.
    >>  ............................................
    pt  Contam para quatro pessoas, e esse é o número correto. Este trabalho não cresce em escala, e eu fiz as pazes com isso.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded.acknowledged/2   [117 chars]
    en  Thank you. I have never once been asked about a night, only ever about the day after, and the night is the whole job.
    >>  ............................................
    pt  Obrigada. Nunca me perguntaram sobre uma noite, só sobre o dia seguinte, e a noite é o trabalho inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded.acknowledged/3   [128 chars]
    en  I keep no record of them, deliberately. The moment I start counting I will start wanting credit, and then I will be worse at it.
    >>  ............................................
    pt  Não guardo registro delas, de propósito. No instante em que eu começar a contar, vou começar a querer crédito, e aí vou fazer pior.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your rounds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cleric.sitting_up.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cleric.sitting_up.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded.respond.leave   [37 chars]
    en  I'll let you get on with your rounds.
    >>  ............................................
    pt  Vou deixar você seguir suas visitas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your rounds."
       spoken on: conversations.scene.work.cleric.sitting_up.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.topic.work.cleric.craft.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric.craft` — e.g. "Brewing I learned from a book and a great many ruined bottles. The listening I learned from failing at it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.craft.respond   [33 chars]
    en  That's the shape of the learning.
    >>  ............................................
    pt  É esse o formato do aprendizado.
    >>  ............................................
```


### Button `ask_failing` — "How does listening go wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.craft` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.craft.ask_failing` — accepted phrasings: "how does listening go wrong"
  - the message must contain one of: `listening`, `wrong`, `failing`
  - scored words: `listening`(1.5), `wrong`(1.0), `failing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.craft.respond.ask_failing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.craft.respond.ask_failing   [28 chars]
    en  How does listening go wrong?
    >>  ............................................
    pt  Como escutar dá errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.craft.ask_failing`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.craft.ask_failing
WHO    VILLAGER — what the player reads after pressing "How does listening go wrong?"
       spoken on: conversations.topic.work.cleric.craft.respond, button `ask_failing`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.craft.ask_failing`: the villager explains. Subject `work.cleric.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.craft.ask_failing/1   [88 chars]
    en  You answer. That's it. Somebody says a hard thing and you answer, and they stop talking.
    >>  ............................................
    pt  Você responde. É isso. Alguém diz algo difícil e você responde, e a pessoa se cala.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.craft.ask_failing/2   [86 chars]
    en  I once told a widow it would pass. She was polite about it. She never came back, %1$s.
    >>  ............................................
    pt  Uma vez eu disse a uma viúva que ia passar. Ela foi educada. Nunca mais voltou, %1$s.
    >>  ............................................
```


### Button `admire` — "Knowing which is wanted is rarer than the brewing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.craft` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.craft.admire` — accepted phrasings: "knowing which is wanted is rarer than the brewing"
  - the message must contain one of: `wanted`, `rarer`, `knowing`
  - scored words: `wanted`(1.2), `rarer`(1.5), `knowing`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.craft.respond.admire   [50 chars]
    en  Knowing which is wanted is rarer than the brewing.
    >>  ............................................
    pt  Saber qual é o desejado é mais raro que o preparo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cleric.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cleric.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.craft.admire
WHO    VILLAGER — what the player reads after pressing "Knowing which is wanted is rarer than the brewing."
       spoken on: conversations.topic.work.cleric.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.craft.admire`: the villager accepts. Subject `work.cleric.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.craft.admire/1   [67 chars]
    en  It is, and it cannot be bottled, which is why nobody trades for it.
    >>  ............................................
    pt  É, e não dá pra engarrafar, e por isso ninguém troca por isso.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.craft.admire/2   [73 chars]
    en  Say that to the apprentice who wanted the recipes and not the afternoons.
    >>  ............................................
    pt  Diga isso ao aprendiz que queria as receitas e não as tardes.
    >>  ............................................
```


### Button `ask_book` — "Have you added to the book?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.craft` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.craft.ask_book` — accepted phrasings: "have you added to the book"
  - the message must contain one of: `book`, `added`, `written`
  - scored words: `book`(1.5), `added`(1.2), `written`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.craft.respond.ask_book
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.craft.respond.ask_book   [27 chars]
    en  Have you added to the book?
    >>  ............................................
    pt  Você acrescentou algo ao livro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.craft.ask_book`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.craft.ask_book
WHO    VILLAGER — what the player reads after pressing "Have you added to the book?"
       spoken on: conversations.topic.work.cleric.craft.respond, button `ask_book`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.craft.ask_book`: the villager explains. Subject `work.cleric.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.craft.ask_book/1   [81 chars]
    en  Forty pages. Half of them say 'this does not work' and those are the useful half.
    >>  ............................................
    pt  Quarenta páginas. Metade diz 'isto não funciona' e essa é a metade útil.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.craft.ask_book/2   [78 chars]
    en  I write in the margins. It is not my book and it will not be my book after me.
    >>  ............................................
    pt  Escrevo nas margens. Não é meu livro e não vai ser meu depois de mim.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.craft.respond.leave   [28 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.work.cleric.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.followup`

**Reached from 20 route(s):** `conversations.scene.work.cleric.followup` / `ask_more`; `conversations.topic.work.cleric.craft.respond` / `ask_failing`; `conversations.topic.work.cleric.craft.respond` / `admire`; `conversations.topic.work.cleric.craft.respond` / `ask_book`; `conversations.topic.work.cleric.future.respond` / `ask_afternoons`; `conversations.topic.work.cleric.future.respond` / `encourage`; `conversations.topic.work.cleric.future.respond` / `ask_lost`; `conversations.topic.work.cleric.respond` / `ask_hard`; `conversations.topic.work.cleric.respond` / `value`; `conversations.topic.work.cleric.respond` / `challenge`; `conversations.topic.work.cleric.respond` / `challenge`; `conversations.topic.work.cleric.risk.respond` / `ask_saying` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric.challenge.landed` — e.g. "A fair share of it is. Comfort mends more than you'd credit."
- `conversations.work.prof.cleric.challenge.stung` — e.g. "...Come and say that at a sickbed. I'll hold the lamp for you."
- `conversations.work.prof.cleric.craft.admire` — e.g. "It is, and it cannot be bottled, which is why nobody trades for it."
- `conversations.work.prof.cleric.craft.ask_book` — e.g. "Forty pages. Half of them say 'this does not work' and those are the useful half."
- `conversations.work.prof.cleric.craft.ask_failing` — e.g. "You answer. That's it. Somebody says a hard thing and you answer, and they stop talking."
- `conversations.work.prof.cleric.future.ask_afternoons` — e.g. "Having needed one. Every good cleric I've met was somebody's patient first."
- `conversations.work.prof.cleric.future.ask_lost` — e.g. "A way of setting a bone that nobody else here can do. I've watched it once and it wasn't enough."
- `conversations.work.prof.cleric.future.encourage` — e.g. "...I might. I've been looking for the wrong thing, which is a useful thing to be told."
- `conversations.work.prof.cleric.hard` — e.g. "Sit with them. That's the part nobody trains you for and it's most of the job some weeks."
- `conversations.work.prof.cleric.risk.ask_confidence` — e.g. "No. It isn't mine to set down. That's rather the definition of the thing."
- `conversations.work.prof.cleric.risk.ask_saying` — e.g. "Slowly, sitting down, and without the word 'unfortunately'. Then you stay in the room."
- `conversations.work.prof.cleric.risk.sympathise` — e.g. "...Nobody. That is a question I have been carefully not asking myself for some years."
- `conversations.work.prof.cleric.task.ask_cough` — e.g. "Not yet. That's a sentence I say carefully and check on twice a week."
- `conversations.work.prof.cleric.task.ask_sitting` — e.g. "That I won't say. You'll notice I've told you about the cough and not about this."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.followup   [39 chars]
    en  That's the candles and the confessions.
    >>  ............................................
    pt  São as velas e as confissões.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.challenge.landed`, `work.cleric.challenge.stung`, `work.cleric.craft.admire`, `work.cleric.craft.ask_book`, `work.cleric.craft.ask_failing`, `work.cleric.future.ask_afternoons`, `work.cleric.future.ask_lost`, `work.cleric.future.encourage`, `work.cleric.hard`, `work.cleric.risk.ask_confidence`, `work.cleric.risk.ask_saying`, `work.cleric.risk.sympathise`, `work.cleric.task.ask_cough`, `work.cleric.task.ask_sitting`, `work.cleric.task.offer_hands`, `work.cleric.value`, `work.cleric.village.ask_help`, `work.cleric.village.ask_list`, `work.cleric.village.say_thanks` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.cleric.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `listening`
  - scored words: `thought`(1.2), `listening`(1.5), `half`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cleric.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.cleric.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.cleric.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cleric.thanks`: the villager accepts. Subject `work.cleric.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.thanks/1   [65 chars]
    en  Few do. They think of the brewing. The brewing is the short half.
    >>  ............................................
    pt  Poucos pensam. Pensam no preparo. O preparo é a metade curta.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.thanks/2   [62 chars]
    en  It's the part that doesn't smell of herbs, %1$s. Easy to miss.
    >>  ............................................
    pt  É a parte que não cheira a erva, %1$s. Fácil de não ver.
    >>  ............................................
```


### Button `ask_more` — "Who looks after you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.challenge.landed`, `work.cleric.challenge.stung`, `work.cleric.craft.admire`, `work.cleric.craft.ask_book`, `work.cleric.craft.ask_failing`, `work.cleric.future.ask_afternoons`, `work.cleric.future.ask_lost`, `work.cleric.future.encourage`, `work.cleric.hard`, `work.cleric.risk.ask_confidence`, `work.cleric.risk.ask_saying`, `work.cleric.risk.sympathise`, `work.cleric.task.ask_cough`, `work.cleric.task.ask_sitting`, `work.cleric.task.offer_hands`, `work.cleric.value`, `work.cleric.village.ask_help`, `work.cleric.village.ask_list`, `work.cleric.village.say_thanks` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.cleric.more` — accepted phrasings: "who looks after you"
  - the message must contain one of: `looks`, `after`
  - scored words: `looks`(1.0), `after`(1.0), `you`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.followup.ask_more   [20 chars]
    en  Who looks after you?
    >>  ............................................
    pt  Quem cuida de você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.cleric.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.more
WHO    VILLAGER — what the player reads after pressing "Who looks after you?"
       spoken on: conversations.topic.work.cleric.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cleric.more`: the villager discloses. Subject `work.cleric.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.more/1   [61 chars]
    en  ...That's a question nobody asks. I'll need a moment with it.
    >>  ............................................
    pt  ...Essa é uma pergunta que ninguém faz. Vou precisar de um momento com ela.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.more/2   [66 chars]
    en  The work does, mostly. It's not a good answer and I know it, %1$s.
    >>  ............................................
    pt  O trabalho, principalmente. Não é uma boa resposta e eu sei disso, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cleric.more/1
    en  ...Nobody asks me that. I'd stopped expecting anyone to, %1$s.
    >>  ............................................
    pt  ...Ninguém me pergunta isso. Eu tinha parado de esperar que alguém perguntasse, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits with me afterwards. I say it plainly because you asked plainly.
    >>  ............................................
    pt  Ninguém senta comigo depois. Digo sem rodeio porque você perguntou sem rodeio.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. I'll sit with it a moment before I spoil it with an answer.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Vou ficar um momento com ela antes de estragar com uma resposta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, and it has been nobody a long time. It's not a complaint; it's just the shape of the trade.
    >>  ............................................
    pt  Ninguém, e é ninguém faz tempo. Não é queixa; é só o formato do ofício.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cleric.more/1
    en  That's a question nobody asks. I'll need a moment with it.
    >>  ............................................
    pt  É uma pergunta que ninguém faz. Vou precisar de um momento com ela.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits in the room with me afterwards. I've been carefully not asking myself that for years.
    >>  ............................................
    pt  Ninguém senta no quarto comigo depois. Venho cuidadosamente não me perguntando isso há anos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cleric.more/1
    en  That's a question nobody asks. I'll need a moment with it.
    >>  ............................................
    pt  É uma pergunta que ninguém faz. Vou precisar de um momento com ela.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits in the room with me afterwards. I've been carefully not asking myself that for years.
    >>  ............................................
    pt  Ninguém senta no quarto comigo depois. Venho cuidadosamente não me perguntando isso há anos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks, %1$s. I'll need a moment, and then I'll answer it properly.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz, %1$s. Vou precisar de um momento, e depois respondo direito.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, mostly. Which is why somebody asking is a larger thing than you meant it to be.
    >>  ............................................
    pt  Ninguém, na maior parte. Por isso alguém perguntar é maior do que você pretendia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks, %1$s. I'll need a moment, and then I'll answer it properly.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz, %1$s. Vou precisar de um momento, e depois respondo direito.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, mostly. Which is why somebody asking is a larger thing than you meant it to be.
    >>  ............................................
    pt  Ninguém, na maior parte. Por isso alguém perguntar é maior do que você pretendia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks, %1$s. I'll need a moment, and then I'll answer it properly.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz, %1$s. Vou precisar de um momento, e depois respondo direito.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, mostly. Which is why somebody asking is a larger thing than you meant it to be.
    >>  ............................................
    pt  Ninguém, na maior parte. Por isso alguém perguntar é maior do que você pretendia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cleric.more/1
    en  ...Nobody asks me that. I'd stopped expecting anyone to, %1$s.
    >>  ............................................
    pt  ...Ninguém me pergunta isso. Eu tinha parado de esperar que alguém perguntasse, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits with me afterwards. I say it plainly because you asked plainly.
    >>  ............................................
    pt  Ninguém senta comigo depois. Digo sem rodeio porque você perguntou sem rodeio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cleric.more/1
    en  That's a question nobody asks. I'll need a moment with it.
    >>  ............................................
    pt  É uma pergunta que ninguém faz. Vou precisar de um momento com ela.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits in the room with me afterwards. I've been carefully not asking myself that for years.
    >>  ............................................
    pt  Ninguém senta no quarto comigo depois. Venho cuidadosamente não me perguntando isso há anos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cleric.more/1
    en  That's a question nobody asks. I'll need a moment with it.
    >>  ............................................
    pt  É uma pergunta que ninguém faz. Vou precisar de um momento com ela.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits in the room with me afterwards. I've been carefully not asking myself that for years.
    >>  ............................................
    pt  Ninguém senta no quarto comigo depois. Venho cuidadosamente não me perguntando isso há anos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cleric.more/1
    en  ...Nobody asks that.
    >>  ............................................
    pt  ...Ninguém pergunta isso.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody. That's the honest answer and it took me a moment to find it.
    >>  ............................................
    pt  Ninguém. É a resposta honesta e eu levei um momento pra achar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. I'll sit with it a moment before I spoil it with an answer.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Vou ficar um momento com ela antes de estragar com uma resposta.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, and it has been nobody a long time. It's not a complaint; it's just the shape of the trade.
    >>  ............................................
    pt  Ninguém, e é ninguém faz tempo. Não é queixa; é só o formato do ofício.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cleric.more/1
    en  ...Nobody asks that.
    >>  ............................................
    pt  ...Ninguém pergunta isso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody. That's the honest answer and it took me a moment to find it.
    >>  ............................................
    pt  Ninguém. É a resposta honesta e eu levei um momento pra achar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. I'll sit with it a moment before I spoil it with an answer.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Vou ficar um momento com ela antes de estragar com uma resposta.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, and it has been nobody a long time. It's not a complaint; it's just the shape of the trade.
    >>  ............................................
    pt  Ninguém, e é ninguém faz tempo. Não é queixa; é só o formato do ofício.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. Give me a moment; I've no joke ready for it.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Me dê um momento; não tenho piada pronta.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cleric.more/2
    en  Who looks after me? The bottles. Which is a joke, and it is also the answer.
    >>  ............................................
    pt  Quem cuida de mim? Os frascos. É piada, e também é a resposta.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. Give me a moment; I've no joke ready for it.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Me dê um momento; não tenho piada pronta.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cleric.more/2
    en  Who looks after me? The bottles. Which is a joke, and it is also the answer.
    >>  ............................................
    pt  Quem cuida de mim? Os frascos. É piada, e também é a resposta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. I'll sit with it a moment before I spoil it with an answer.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Vou ficar um momento com ela antes de estragar com uma resposta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody, and it has been nobody a long time. It's not a complaint; it's just the shape of the trade.
    >>  ............................................
    pt  Ninguém, e é ninguém faz tempo. Não é queixa; é só o formato do ofício.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cleric.more/1
    en  ...Nobody asks me that. I'd stopped expecting anyone to, %1$s.
    >>  ............................................
    pt  ...Ninguém me pergunta isso. Eu tinha parado de esperar que alguém perguntasse, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody sits with me afterwards. I say it plainly because you asked plainly.
    >>  ............................................
    pt  Ninguém senta comigo depois. Digo sem rodeio porque você perguntou sem rodeio.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cleric.more/1
    en  ...Nobody asks that.
    >>  ............................................
    pt  ...Ninguém pergunta isso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cleric.more/2
    en  Nobody. That's the honest answer and it took me a moment to find it.
    >>  ............................................
    pt  Ninguém. É a resposta honesta e eu levei um momento pra achar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. Give me a moment; I've no joke ready for it.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Me dê um momento; não tenho piada pronta.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cleric.more/2
    en  Who looks after me? The bottles. Which is a joke, and it is also the answer.
    >>  ............................................
    pt  Quem cuida de mim? Os frascos. É piada, e também é a resposta.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cleric.more/1
    en  ...That's a question nobody asks. Give me a moment; I've no joke ready for it.
    >>  ............................................
    pt  ...É uma pergunta que ninguém faz. Me dê um momento; não tenho piada pronta.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cleric.more/2
    en  Who looks after me? The bottles. Which is a joke, and it is also the answer.
    >>  ............................................
    pt  Quem cuida de mim? Os frascos. É piada, e também é a resposta.
    >>  ............................................
```

</details>


### Button `leave` — "Keep well."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.challenge.landed`, `work.cleric.challenge.stung`, `work.cleric.craft.admire`, `work.cleric.craft.ask_book`, `work.cleric.craft.ask_failing`, `work.cleric.future.ask_afternoons`, `work.cleric.future.ask_lost`, `work.cleric.future.encourage`, `work.cleric.hard`, `work.cleric.risk.ask_confidence`, `work.cleric.risk.ask_saying`, `work.cleric.risk.sympathise`, `work.cleric.task.ask_cough`, `work.cleric.task.ask_sitting`, `work.cleric.task.offer_hands`, `work.cleric.value`, `work.cleric.village.ask_help`, `work.cleric.village.ask_list`, `work.cleric.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.followup.leave   [10 chars]
    en  Keep well.
    >>  ............................................
    pt  Fique bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "Keep well."
       spoken on: conversations.topic.work.cleric.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric.future` — e.g. "I'd like an apprentice who wants the afternoons and not the recipes. I've had three of the other sort."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.future.respond   [26 chars]
    en  That's what's ahead of me.
    >>  ............................................
    pt  É o que está à minha frente.
    >>  ............................................
```


### Button `ask_afternoons` — "What makes someone want the afternoons?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.future` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.future.ask_afternoons` — accepted phrasings: "what makes someone want the afternoons"
  - the message must contain one of: `afternoons`, `apprentice`
  - scored words: `afternoons`(1.5), `apprentice`(1.2), `want`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.future.respond.ask_afternoons
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.future.respond.ask_afternoons   [39 chars]
    en  What makes someone want the afternoons?
    >>  ............................................
    pt  O que faz alguém querer as tardes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.future.ask_afternoons`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.future.ask_afternoons
WHO    VILLAGER — what the player reads after pressing "What makes someone want the afternoons?"
       spoken on: conversations.topic.work.cleric.future.respond, button `ask_afternoons`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.future.ask_afternoons`: the villager explains. Subject `work.cleric.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.future.ask_afternoons/1   [75 chars]
    en  Having needed one. Every good cleric I've met was somebody's patient first.
    >>  ............................................
    pt  Ter precisado de uma. Todo bom clérigo que conheci foi paciente de alguém primeiro.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.future.ask_afternoons/2   [55 chars]
    en  I don't know. If I knew I would go and find them, %1$s.
    >>  ............................................
    pt  Não sei. Se eu soubesse eu iria procurar, %1$s.
    >>  ............................................
```


### Button `encourage` — "You'd know one if you met them."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.future` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.future.encourage` — accepted phrasings: "you'd know one if you met them"
  - the message must contain one of: `meet`, `recognise`
  - scored words: `know`(0.6), `meet`(1.2), `recognise`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.future.respond.encourage   [31 chars]
    en  You'd know one if you met them.
    >>  ............................................
    pt  Você reconheceria se encontrasse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cleric.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cleric.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.future.encourage
WHO    VILLAGER — what the player reads after pressing "You'd know one if you met them."
       spoken on: conversations.topic.work.cleric.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.future.encourage`: the villager accepts. Subject `work.cleric.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.future.encourage/1   [86 chars]
    en  ...I might. I've been looking for the wrong thing, which is a useful thing to be told.
    >>  ............................................
    pt  ...Talvez. Venho procurando a coisa errada, o que é útil de ouvir.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.future.encourage/2   [61 chars]
    en  I hope so. I've a few years yet, but not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto eu queria.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing and I think I knew that.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada e acho que eu sabia.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. There aren't as many years as I let myself pretend.
    >>  ............................................
    pt  Espero que sim. Não há tantos anos quanto eu finjo pra mim mesmo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Forty years of looking for the wrong thing, put plainly.
    >>  ............................................
    pt  ...Talvez eu ache. Quarenta anos procurando a coisa errada, dito sem rodeios.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, and I've learned exactly how few that is.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, e aprendi exatamente quão poucos são.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing, which is useful to be told.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada, o que é útil ouvir.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, but not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing, which is useful to be told.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada, o que é útil ouvir.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, but not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might, %1$s. I've been looking for the wrong thing and needed telling.
    >>  ............................................
    pt  ...Talvez eu ache, %1$s. Venho procurando a coisa errada e precisava ouvir isso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, though not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might, %1$s. I've been looking for the wrong thing and needed telling.
    >>  ............................................
    pt  ...Talvez eu ache, %1$s. Venho procurando a coisa errada e precisava ouvir isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, though not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might, %1$s. I've been looking for the wrong thing and needed telling.
    >>  ............................................
    pt  ...Talvez eu ache, %1$s. Venho procurando a coisa errada e precisava ouvir isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, though not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing and I think I knew that.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada e acho que eu sabia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. There aren't as many years as I let myself pretend.
    >>  ............................................
    pt  Espero que sim. Não há tantos anos quanto eu finjo pra mim mesmo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing, which is useful to be told.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada, o que é útil ouvir.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, but not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing, which is useful to be told.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada, o que é útil ouvir.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, but not as many as I'd like.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, mas não tantos quanto queria.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Wrong thing, all this time.
    >>  ............................................
    pt  ...Talvez eu ache. Coisa errada, esse tempo todo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. Not many years left for it.
    >>  ............................................
    pt  Espero que sim. Não sobram muitos anos pra isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Forty years of looking for the wrong thing, put plainly.
    >>  ............................................
    pt  ...Talvez eu ache. Quarenta anos procurando a coisa errada, dito sem rodeios.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, and I've learned exactly how few that is.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, e aprendi exatamente quão poucos são.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Wrong thing, all this time.
    >>  ............................................
    pt  ...Talvez eu ache. Coisa errada, esse tempo todo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. Not many years left for it.
    >>  ............................................
    pt  Espero que sim. Não sobram muitos anos pra isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Forty years of looking for the wrong thing, put plainly.
    >>  ............................................
    pt  ...Talvez eu ache. Quarenta anos procurando a coisa errada, dito sem rodeios.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, and I've learned exactly how few that is.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, e aprendi exatamente quão poucos são.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might! I've been looking for entirely the wrong thing. How cheering.
    >>  ............................................
    pt  ...Talvez eu ache! Venho procurando a coisa completamente errada. Que animador.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet — fewer than I'd like, but a few.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda — menos do que queria, mas alguns.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might! I've been looking for entirely the wrong thing. How cheering.
    >>  ............................................
    pt  ...Talvez eu ache! Venho procurando a coisa completamente errada. Que animador.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet — fewer than I'd like, but a few.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda — menos do que queria, mas alguns.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Forty years of looking for the wrong thing, put plainly.
    >>  ............................................
    pt  ...Talvez eu ache. Quarenta anos procurando a coisa errada, dito sem rodeios.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet, and I've learned exactly how few that is.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda, e aprendi exatamente quão poucos são.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. I've been looking for the wrong thing and I think I knew that.
    >>  ............................................
    pt  ...Talvez eu ache. Venho procurando a coisa errada e acho que eu sabia.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. There aren't as many years as I let myself pretend.
    >>  ............................................
    pt  Espero que sim. Não há tantos anos quanto eu finjo pra mim mesmo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might. Wrong thing, all this time.
    >>  ............................................
    pt  ...Talvez eu ache. Coisa errada, esse tempo todo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. Not many years left for it.
    >>  ............................................
    pt  Espero que sim. Não sobram muitos anos pra isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might! I've been looking for entirely the wrong thing. How cheering.
    >>  ............................................
    pt  ...Talvez eu ache! Venho procurando a coisa completamente errada. Que animador.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet — fewer than I'd like, but a few.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda — menos do que queria, mas alguns.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cleric.future.encourage/1
    en  ...I might! I've been looking for entirely the wrong thing. How cheering.
    >>  ............................................
    pt  ...Talvez eu ache! Venho procurando a coisa completamente errada. Que animador.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cleric.future.encourage/2
    en  I hope so. I've a few years yet — fewer than I'd like, but a few.
    >>  ............................................
    pt  Espero que sim. Tenho alguns anos ainda — menos do que queria, mas alguns.
    >>  ............................................
```

</details>


### Button `ask_lost` — "What did they know that's lost?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.future` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.future.ask_lost` — accepted phrasings: "what did they know that's lost"
  - the message must contain one of: `lost`, `knew`, `died`
  - scored words: `lost`(1.5), `knew`(1.2), `died`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.future.respond.ask_lost
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.future.respond.ask_lost   [31 chars]
    en  What did they know that's lost?
    >>  ............................................
    pt  O que essa pessoa sabia que se perdeu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.future.ask_lost`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.future.ask_lost
WHO    VILLAGER — what the player reads after pressing "What did they know that's lost?"
       spoken on: conversations.topic.work.cleric.future.respond, button `ask_lost`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.future.ask_lost`: the villager explains. Subject `work.cleric.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.future.ask_lost/1   [96 chars]
    en  A way of setting a bone that nobody else here can do. I've watched it once and it wasn't enough.
    >>  ............................................
    pt  Um jeito de imobilizar osso que mais ninguém aqui sabe. Vi uma vez e não bastou.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.future.ask_lost/2   [84 chars]
    en  Something about fever. She showed me and I did not write it down, %1$s. I was young.
    >>  ............................................
    pt  Algo sobre febre. Ela me mostrou e eu não anotei, %1$s. Eu era jovem.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.future.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.cleric.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric` — e.g. "I tend souls and brew what mends them. Half my job is listening. You'd be surprised who talks."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.respond   [46 chars]
    en  That's the work, and the listening half of it.
    >>  ............................................
    pt  É o trabalho, e a metade dele que é ouvir.
    >>  ............................................
```


### Button `ask_hard` — "And when it cannot be mended?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.identity` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.hard` — accepted phrasings: "and when it cannot be mended"
  - the message must contain one of: `mend`, `cannot`, `dying`
  - scored words: `mend`(1.5), `cannot`(1.0), `dying`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.respond.ask_hard   [29 chars]
    en  And when it cannot be mended?
    >>  ............................................
    pt  E quando não dá pra consertar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.cleric.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.hard
WHO    VILLAGER — what the player reads after pressing "And when it cannot be mended?"
       spoken on: conversations.topic.work.cleric.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.hard`: the villager explains. Subject `work.cleric.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.followup / ask_more
```

> Written out in full under **`conversations.scene.work.cleric.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Half the folk here have cried in front of you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.identity` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.value` — accepted phrasings: "half the folk here have cried in front of you"
  - the message must contain one of: `cried`, `confide`
  - scored words: `cried`(1.5), `confide`(1.5), `listen`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.respond.value   [46 chars]
    en  Half the folk here have cried in front of you.
    >>  ............................................
    pt  Metade do povo daqui já chorou na sua frente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cleric.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.cleric.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.value
WHO    VILLAGER — what the player reads after pressing "Half the folk here have cried in front of you."
       spoken on: conversations.topic.work.cleric.respond, button `value`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.value`: the villager accepts. Subject `work.cleric.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.value/1   [88 chars]
    en  More than half. And not one of them mentions it in the square, which is as it should be.
    >>  ............................................
    pt  Mais da metade. E nenhum deles comenta na praça, como tem que ser.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.value/2   [81 chars]
    en  They have. I keep all of it, and I say none of it. That's the whole of the trust.
    >>  ............................................
    pt  Já choraram. Eu guardo tudo e não digo nada. É essa a confiança inteira.
    >>  ............................................
```


### Button `challenge` — "Most of what you brew is comfort, not cure."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.cleric.identity` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.challenge` — accepted phrasings: "most of what you brew is comfort, not cure"
  - the message must contain one of: `comfort`, `cure`
  - scored words: `comfort`(1.5), `cure`(1.5), `brew`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.respond.challenge   [43 chars]
    en  Most of what you brew is comfort, not cure.
    >>  ............................................
    pt  Boa parte do que você prepara é conforto, não cura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.cleric.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.cleric.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Most of what you brew is comfort, not cure."
       spoken on: conversations.topic.work.cleric.respond, button `challenge`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.challenge.landed`: the villager resists. Subject `work.cleric.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.challenge.landed/1   [60 chars]
    en  A fair share of it is. Comfort mends more than you'd credit.
    >>  ............................................
    pt  Boa parte é. Conforto cura mais do que você imagina.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.challenge.landed/2   [81 chars]
    en  You're not wrong. I'd rather a bottle that soothes than a shelf of nothing, %1$s.
    >>  ............................................
    pt  Você não está errado. Prefiro um frasco que acalma a uma prateleira vazia, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.cleric.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.cleric.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Most of what you brew is comfort, not cure."
       spoken on: conversations.topic.work.cleric.respond, button `challenge`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.challenge.stung`: the villager resists. Subject `work.cleric.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.challenge.stung/1   [62 chars]
    en  ...Come and say that at a sickbed. I'll hold the lamp for you.
    >>  ............................................
    pt  ...Venha dizer isso à beira de um leito. Eu seguro a lamparina pra você.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.challenge.stung/2   [86 chars]
    en  Comfort, is it. Tell that to the ones who slept through the night because of it, %1$s.
    >>  ............................................
    pt  Conforto, é. Diga isso a quem dormiu a noite inteira por causa dele, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.cleric.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric.risk` — e.g. "There are things I cannot mend and I have to say so out loud, to a face, in a small room."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.risk.respond   [21 chars]
    en  That's what it costs.
    >>  ............................................
    pt  É o que custa.
    >>  ............................................
```


### Button `ask_saying` — "How do you say a thing like that?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.risk` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.risk.ask_saying` — accepted phrasings: "how do you say a thing like that"
  - the message must contain one of: `telling`, `news`
  - scored words: `say`(0.6), `telling`(1.5), `news`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.risk.respond.ask_saying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.risk.respond.ask_saying   [33 chars]
    en  How do you say a thing like that?
    >>  ............................................
    pt  Como se diz uma coisa dessas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.risk.ask_saying`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.risk.ask_saying
WHO    VILLAGER — what the player reads after pressing "How do you say a thing like that?"
       spoken on: conversations.topic.work.cleric.risk.respond, button `ask_saying`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.risk.ask_saying`: the villager explains. Subject `work.cleric.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.risk.ask_saying/1   [86 chars]
    en  Slowly, sitting down, and without the word 'unfortunately'. Then you stay in the room.
    >>  ............................................
    pt  Devagar, sentado, e sem a palavra 'infelizmente'. Aí você fica no quarto.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.risk.ask_saying/2   [61 chars]
    en  Plainly. Every soft version I have tried made it worse, %1$s.
    >>  ............................................
    pt  Sem rodeio. Toda versão suave que eu tentei piorou, %1$s.
    >>  ............................................
```


### Button `sympathise` — "And who sits in the room with you afterwards?"

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.risk` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.risk.sympathise` — accepted phrasings: "and who sits in the room with you afterwards"
  - the message must contain one of: `afterwards`, `room`, `sits`
  - scored words: `afterwards`(1.5), `room`(1.0), `sits`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.risk.respond.sympathise   [45 chars]
    en  And who sits in the room with you afterwards?
    >>  ............................................
    pt  E quem senta no quarto com você depois?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cleric.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cleric.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "And who sits in the room with you afterwards?"
       spoken on: conversations.topic.work.cleric.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.risk.sympathise`: the villager accepts. Subject `work.cleric.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.risk.sympathise/1   [85 chars]
    en  ...Nobody. That is a question I have been carefully not asking myself for some years.
    >>  ............................................
    pt  ...Ninguém. É uma pergunta que eu venho cuidadosamente não me fazendo há anos.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.risk.sympathise/2   [62 chars]
    en  The bottles. Which is a joke, and it is also the answer, %1$s.
    >>  ............................................
    pt  Os frascos. É piada, e também é a resposta, %1$s.
    >>  ............................................
```


### Button `ask_confidence` — "Can you put that confidence down?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.risk` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.risk.ask_confidence` — accepted phrasings: "can you put that confidence down"
  - the message must contain one of: `confidence`
  - scored words: `confidence`(1.5), `down`(0.8), `put`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.risk.respond.ask_confidence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.risk.respond.ask_confidence   [33 chars]
    en  Can you put that confidence down?
    >>  ............................................
    pt  Dá pra largar essa confidência?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.risk.ask_confidence`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.risk.ask_confidence
WHO    VILLAGER — what the player reads after pressing "Can you put that confidence down?"
       spoken on: conversations.topic.work.cleric.risk.respond, button `ask_confidence`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.risk.ask_confidence`: the villager explains. Subject `work.cleric.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.risk.ask_confidence/1   [73 chars]
    en  No. It isn't mine to set down. That's rather the definition of the thing.
    >>  ............................................
    pt  Não. Não é minha pra largar. É meio que a definição da coisa.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.risk.ask_confidence/2   [79 chars]
    en  I've thought about it every week for four years and the answer has not changed.
    >>  ............................................
    pt  Penso nisso toda semana faz quatro anos e a resposta não mudou.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.risk.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.cleric.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric.task` — e.g. "Three bottles for the miller's cough and a fourth I'm making because I do not trust the third."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.task.respond   [22 chars]
    en  That's the day's list.
    >>  ............................................
    pt  É a lista do dia.
    >>  ............................................
```


### Button `ask_cough` — "Is the cough serious?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.task` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.task.ask_cough` — accepted phrasings: "is the cough serious"
  - the message must contain one of: `cough`, `serious`, `miller`
  - scored words: `cough`(1.5), `serious`(1.2), `miller`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.task.respond.ask_cough
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.task.respond.ask_cough   [21 chars]
    en  Is the cough serious?
    >>  ............................................
    pt  A tosse é séria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.task.ask_cough`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.task.ask_cough
WHO    VILLAGER — what the player reads after pressing "Is the cough serious?"
       spoken on: conversations.topic.work.cleric.task.respond, button `ask_cough`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.task.ask_cough`: the villager explains. Subject `work.cleric.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.task.ask_cough/1   [69 chars]
    en  Not yet. That's a sentence I say carefully and check on twice a week.
    >>  ............................................
    pt  Ainda não. É uma frase que eu digo com cuidado e confiro duas vezes por semana.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.task.ask_cough/2   [60 chars]
    en  It's the fourth week of it. I'd not call that nothing, %1$s.
    >>  ............................................
    pt  É a quarta semana. Eu não chamaria isso de nada, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I could carry the bottles round."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.cleric.task` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.task.offer_hands` — accepted phrasings: "i could carry the bottles round"
  - the message must contain one of: `carry`, `bottles`, `deliver`
  - scored words: `carry`(1.2), `bottles`(1.5), `deliver`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.task.respond.offer_hands   [32 chars]
    en  I could carry the bottles round.
    >>  ............................................
    pt  Eu podia levar os frascos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cleric.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cleric.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could carry the bottles round."
       spoken on: conversations.topic.work.cleric.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.task.offer_hands`: the villager accepts. Subject `work.cleric.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.task.offer_hands/1   [81 chars]
    en  ...You could. Say nothing about what's in them and nothing about who they're for.
    >>  ............................................
    pt  ...Podia. Não diga nada sobre o conteúdo nem sobre pra quem são.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.task.offer_hands/2   [74 chars]
    en  The miller's first. And don't let him tell you he's fine, because he will.
    >>  ............................................
    pt  Primeiro o moleiro. E não deixe ele dizer que está bem, porque ele vai dizer.
    >>  ............................................
```


### Button `ask_sitting` — "Who are you sitting with?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.task` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.task.ask_sitting` — accepted phrasings: "who are you sitting with"
  - the message must contain one of: `sitting`, `appointment`
  - scored words: `sitting`(1.5), `who`(0.5), `appointment`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.task.respond.ask_sitting
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.task.respond.ask_sitting   [25 chars]
    en  Who are you sitting with?
    >>  ............................................
    pt  Com quem você vai sentar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.task.ask_sitting`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.task.ask_sitting
WHO    VILLAGER — what the player reads after pressing "Who are you sitting with?"
       spoken on: conversations.topic.work.cleric.task.respond, button `ask_sitting`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.task.ask_sitting`: the villager explains. Subject `work.cleric.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.task.ask_sitting/1   [81 chars]
    en  That I won't say. You'll notice I've told you about the cough and not about this.
    >>  ............................................
    pt  Isso eu não digo. Repare que eu falei da tosse e não disso.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.task.ask_sitting/2   [66 chars]
    en  Somebody who asked me to, and that's as much as anyone gets, %1$s.
    >>  ............................................
    pt  Alguém que me pediu, e é tudo que qualquer um recebe, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.task.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.cleric.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cleric.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cleric.village` — e.g. "Nobody in this village has died alone in eleven years. That's the only number I keep."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cleric.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cleric.village.respond   [25 chars]
    en  That's the account of it.
    >>  ............................................
    pt  É esse o balanço.
    >>  ............................................
```


### Button `ask_list` — "How do you check on them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cleric.village` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.village.ask_list` — accepted phrasings: "how do you check on them"
  - the message must contain one of: `check`, `list`, `visit`
  - scored words: `check`(1.5), `list`(1.2), `visit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.village.respond.ask_list
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.village.respond.ask_list   [25 chars]
    en  How do you check on them?
    >>  ............................................
    pt  Como você confere?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cleric.village.ask_list`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.village.ask_list
WHO    VILLAGER — what the player reads after pressing "How do you check on them?"
       spoken on: conversations.topic.work.cleric.village.respond, button `ask_list`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.village.ask_list`: the villager explains. Subject `work.cleric.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.village.ask_list/1   [81 chars]
    en  I find a reason to walk past. Everybody in this village has a fence I can admire.
    >>  ............................................
    pt  Arranjo um motivo pra passar por perto. Todo mundo aqui tem uma cerca que eu posso elogiar.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.village.ask_list/2   [70 chars]
    en  Badly, and I know it. Two of them would not open the door to me, %1$s.
    >>  ............................................
    pt  Mal, e eu sei. Dois deles não abririam a porta pra mim, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Eleven years is a number worth keeping."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cleric.village` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.village.say_thanks` — accepted phrasings: "eleven years is a number worth keeping"
  - the message must contain one of: `eleven`, `number`
  - scored words: `eleven`(1.5), `years`(0.8), `number`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.village.respond.say_thanks   [39 chars]
    en  Eleven years is a number worth keeping.
    >>  ............................................
    pt  Onze anos é um número que vale guardar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cleric.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cleric.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Eleven years is a number worth keeping."
       spoken on: conversations.topic.work.cleric.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.village.say_thanks`: the villager accepts. Subject `work.cleric.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.village.say_thanks/1   [81 chars]
    en  ...It is. I have never said it aloud, and now I have, and it sounds like a boast.
    >>  ............................................
    pt  ...É. Nunca disse em voz alta, e agora disse, e soa como vaidade.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.village.say_thanks/2   [67 chars]
    en  It is the only one. Everything else in this trade resists counting.
    >>  ............................................
    pt  É o único. Todo o resto neste ofício resiste a ser contado.
    >>  ............................................
```


### Button `ask_help` — "Could I walk past a door or two?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.cleric.village` · offered only once the villager has actually said `work:cleric`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cleric.village.ask_help` — accepted phrasings: "could i walk past a door or two"
  - the message must contain one of: `walk`, `door`
  - scored words: `walk`(1.2), `door`(1.5), `check`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.village.respond.ask_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.village.respond.ask_help   [32 chars]
    en  Could I walk past a door or two?
    >>  ............................................
    pt  Eu podia passar por uma porta ou outra?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cleric.village.ask_help`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cleric.village.ask_help`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cleric.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Who looks after you?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.village.ask_help
WHO    VILLAGER — what the player reads after pressing "Could I walk past a door or two?"
       spoken on: conversations.topic.work.cleric.village.respond, button `ask_help`
       leaves the player on: conversations.topic.work.cleric.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.village.ask_help`: the villager accepts. Subject `work.cleric.village`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cleric.village.ask_help/1   [88 chars]
    en  ...You could. The one at the end of the lane. Admire the fence and mention nothing else.
    >>  ............................................
    pt  ...Podia. A do fim da viela. Elogie a cerca e não mencione mais nada.
    >>  ............................................
  dialogue.conversations.work.prof.cleric.village.ask_help/2   [84 chars]
    en  Then take the far side of the village. And %1$s — tell me only if something's wrong.
    >>  ............................................
    pt  Então pegue o outro lado do vilarejo. E %1$s — só me diga se algo estiver errado.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cleric.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cleric.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cleric.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cleric.village.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.cleric.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.cleric.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.left`: the villager accepts. Subject `work.cleric.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cleric.closed_door.blocked.respond / leave; conversations.scene.work.cleric.closed_door.succeeded.respond / leave; conversations.scene.work.cleric.followup / leave; conversations.scene.work.cleric.no_answer.active.respond / leave; conversations.scene.work.cleric.no_answer.succeeded.respond / leave; conversations.scene.work.cleric.sitting_up.active.respond / leave; conversations.scene.work.cleric.sitting_up.succeeded.respond / leave; conversations.topic.work.cleric.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cleric.closed_door.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

