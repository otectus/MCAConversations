# Work talk with a delightcook

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.delightcook.borrowed_recipe.active.respond`](#conversations-scene-work-delightcook-borrowed-recipe-active-respond)
- [`conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond`](#conversations-scene-work-delightcook-borrowed-recipe-succeeded-respond)
- [`conversations.scene.work.delightcook.bruised_deliveries.blocked.respond`](#conversations-scene-work-delightcook-bruised-deliveries-blocked-respond)
- [`conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond`](#conversations-scene-work-delightcook-bruised-deliveries-succeeded-respond)
- [`conversations.scene.work.delightcook.followup`](#conversations-scene-work-delightcook-followup)
- [`conversations.scene.work.delightcook.long_evenings.blocked.respond`](#conversations-scene-work-delightcook-long-evenings-blocked-respond)
- [`conversations.scene.work.delightcook.long_evenings.succeeded.respond`](#conversations-scene-work-delightcook-long-evenings-succeeded-respond)
- [`conversations.topic.work.delightcook.craft.respond`](#conversations-topic-work-delightcook-craft-respond)
- [`conversations.topic.work.delightcook.followup`](#conversations-topic-work-delightcook-followup)
- [`conversations.topic.work.delightcook.future.respond`](#conversations-topic-work-delightcook-future-respond)
- [`conversations.topic.work.delightcook.respond`](#conversations-topic-work-delightcook-respond)
- [`conversations.topic.work.delightcook.risk.respond`](#conversations-topic-work-delightcook-risk-respond)
- [`conversations.topic.work.delightcook.task.respond`](#conversations-topic-work-delightcook-task-respond)
- [`conversations.topic.work.delightcook.village.respond`](#conversations-topic-work-delightcook-village-respond)

---

## `conversations.scene.work.delightcook.borrowed_recipe.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.borrowed_recipe.active` — e.g. "A woman gave me %2$s before she died and I have been cooking it and I have never once put a name to it on the board."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.borrowed_recipe.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond   [12 chars]
    en  That recipe.
    >>  ............................................
    pt  Aquela receita.
    >>  ............................................
```


### Button `ask_what_stops_her` — "What holds you back from naming it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.delightcook.borrowed_recipe.active` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.borrowed_recipe.active.ask_what_stops_her` — accepted phrasings: "what holds you back from naming it"; "what holds you back from naming it"; "why leave the name off the board"
  - the message must contain one of: `naming`, `name`, `board`
  - scored words: `naming`(1.8), `name`(1.8), `board`(1.8), `holds`(0.8), `back`(0.8), `from`(0.8), `why`(0.8), `leave`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond.ask_what_stops_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.borrowed_recipe.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond.ask_what_stops_her   [35 chars]
    en  What holds you back from naming it?
    >>  ............................................
    pt  O que te impede de dar o nome?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.delightcook.a_recipe_not_mine`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.borrowed_recipe"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.explained
WHO    VILLAGER — what the player reads after pressing "What holds you back from naming it?"
       spoken on: conversations.scene.work.delightcook.borrowed_recipe.active.respond, button `ask_what_stops_her`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.borrowed_recipe.active.explained`: the villager explains. Subject `work.delightcook.a_recipe_not_mine`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.explained/1   [123 chars]
    en  Her family are still here. Putting her name up makes it a memorial, and nobody asked me to make her a memorial out of soup.
    >>  ............................................
    pt  A família dela ainda está aqui. Pôr o nome dela transforma aquilo em memorial, e ninguém me pediu para fazer um memorial de sopa.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.explained/2   [118 chars]
    en  Putting mine up would be a lie, and putting neither up is what I have been doing, and that is a lie of a quieter sort.
    >>  ............................................
    pt  Pôr o meu seria mentira, e não pôr nenhum é o que eu venho fazendo, e isso é uma mentira mais silenciosa.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.explained/3   [118 chars]
    en  I do not know what she would have wanted. I could ask her daughter. I have been not-asking her daughter for two years.
    >>  ............................................
    pt  Eu não sei o que ela ia querer. Eu poderia perguntar à filha dela. Faz dois anos que eu venho não-perguntando à filha dela.
    >>  ............................................
```


### Button `advise_asking_the_family` — "Ask her daughter what she'd want."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightcook.borrowed_recipe.active` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.borrowed_recipe.active.advise_asking_the_family` — accepted phrasings: "ask her daughter what shed want"; "ask her daughter what she would want"; "let the family decide the name"
  - the message must contain one of: `daughter`, `family`
  - scored words: `daughter`(1.8), `family`(1.8), `ask`(0.8), `her`(0.8), `shed`(0.8), `want`(0.8), `she`(0.8), `let`(0.8), `decide`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond.advise_asking_the_family
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.borrowed_recipe.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond.advise_asking_the_family   [33 chars]
    en  Ask her daughter what she'd want.
    >>  ............................................
    pt  Pergunte à filha o que ela quereria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightcook.recipe.asked`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +3  _(recorded under topic `work.delightcook.a_recipe_not_mine`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.borrowed_recipe"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.accepted
WHO    VILLAGER — what the player reads after pressing "Ask her daughter what she'd want."
       spoken on: conversations.scene.work.delightcook.borrowed_recipe.active.respond, button `advise_asking_the_family`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.borrowed_recipe.active.accepted`: the villager accepts. Subject `work.delightcook.a_recipe_not_mine`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.accepted/1   [125 chars]
    en  It is the obvious thing and it is two years overdue, and the reason it is overdue is that I might be told to stop cooking it.
    >>  ............................................
    pt  É o óbvio e está dois anos atrasado, e o motivo do atraso é que podem me mandar parar de fazer.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.accepted/2   [123 chars]
    en  Yes. And I will take her a bowl of it, which is either the right way to open that conversation or an act of enormous cheek.
    >>  ............................................
    pt  Sim. E vou levar uma tigela para ela, o que ou é o jeito certo de abrir essa conversa ou é um atrevimento enorme.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.accepted/3   [135 chars]
    en  You are right. It is her decision and I have been keeping it from her in order to keep making it, and that is worse than either answer.
    >>  ............................................
    pt  Você tem razão. A decisão é dela e eu vinha escondendo isso para poder continuar fazendo, e isso é pior que qualquer uma das respostas.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightcook.borrowed_recipe.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.borrowed_recipe.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active.respond.leave   [34 chars]
    en  I'll let you get back to the pass.
    >>  ............................................
    pt  Vou deixar você voltar ao balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pass."
       spoken on: conversations.scene.work.delightcook.borrowed_recipe.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.delightcook.leave/1   [40 chars]
    en  It catches if you look away. Off you go.
    >>  ............................................
    pt  Ela pega no fundo se você desviar o olhar. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.leave/2   [42 chars]
    en  Take a bowl on the way, %1$s. Don't argue.
    >>  ............................................
    pt  Leve uma tigela na saída, %1$s. Não discuta.
    >>  ............................................
```

---


## `conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.borrowed_recipe.succeeded` — e.g. "Her daughter cried and then laughed and then told me a detail about %2$s that I had been getting wrong for two years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond   [19 chars]
    en  That recipe, since.
    >>  ............................................
    pt  Aquela receita, depois disso.
    >>  ............................................
```


### Button `note_the_asking` — "Asking gave her something too."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightcook.borrowed_recipe.succeeded` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.borrowed_recipe.succeeded.note_the_asking` — accepted phrasings: "asking gave her something too"; "asking gave her something too"; "the question was a gift to her"
  - the message must contain one of: `asking`, `question`, `gift`
  - scored words: `asking`(1.8), `question`(1.8), `gift`(1.8), `gave`(0.8), `her`(0.8), `something`(0.8), `too`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond.note_the_asking
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond.note_the_asking   [30 chars]
    en  Asking gave her something too.
    >>  ............................................
    pt  Perguntar deu algo a ela também.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, respect +2  _(recorded under topic `work.delightcook.a_recipe_not_mine`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightcook.borrowed_recipe"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Asking gave her something too."
       spoken on: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond, button `note_the_asking`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.borrowed_recipe.succeeded.acknowledged`: the villager accepts. Subject `work.delightcook.a_recipe_not_mine`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.acknowledged/1   [112 chars]
    en  She said nobody had asked her about her mother in a year. I had spent two years protecting her from being asked.
    >>  ............................................
    pt  Ela disse que ninguém perguntava sobre a mãe dela havia um ano. Eu passei dois anos protegendo ela de ser perguntada.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.acknowledged/2   [127 chars]
    en  Thank you. I had built the whole delay out of kindness and it was made entirely of my own nerve, which I am still sitting with.
    >>  ............................................
    pt  Obrigada. Eu tinha construído o adiamento inteiro em nome da gentileza e ele era feito só do meu próprio medo, e ainda estou digerindo isso.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.acknowledged/3   [123 chars]
    en  She comes in on Thursdays now and tells me one thing each time. There are apparently four more steps I have never heard of.
    >>  ............................................
    pt  Ela vem às quintas agora e me conta uma coisa por vez. Aparentemente existem mais quatro passos de que eu nunca ouvi falar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightcook.borrowed_recipe.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the pass.
    >>  ............................................
    pt  Vou deixar você voltar ao balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pass."
       spoken on: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightcook.bruised_deliveries.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.bruised_deliveries.blocked` — e.g. "%2$s, every week for a month, and I trim off a third of it before it reaches the pot."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond   [15 chars]
    en  The deliveries.
    >>  ............................................
    pt  As entregas.
    >>  ............................................
```


### Button `offer_produce` — "I'll bring you potatoes this week."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.delightcook.bruised_deliveries.blocked` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.bruised_deliveries.blocked.offer_produce` — accepted phrasings: "ill bring you potatoes this week"; "i can bring you potatoes this week"; "let me fetch potatoes for the kitchen"
  - the message must contain one of: `potatoes`, `potato`
  - scored words: `potatoes`(1.8), `potato`(1.8), `ill`(0.8), `bring`(0.8), `week`(0.8), `let`(0.8), `fetch`(0.8), `kitchen`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond.offer_produce
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond.offer_produce   [34 chars]
    en  I'll bring you potatoes this week.
    >>  ............................................
    pt  Vou trazer batatas esta semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightcook.stores.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.delightcook.stores`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.bruised_deliveries", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.bruised_deliveries", "obligation": "commitment:work.delightcook.bring_potatoes"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.delightcook.bring_potatoes"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you potatoes this week."
       spoken on: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond, button `offer_produce`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.bruised_deliveries.blocked.accepted`: the villager accepts. Subject `work.delightcook.stores`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.accepted/1   [113 chars]
    en  Then I can refuse next week's delivery outright, which I could not do while it was the only food in the building.
    >>  ............................................
    pt  Então eu posso recusar a entrega da semana que vem de vez, o que eu não podia enquanto era a única comida do prédio.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.accepted/2   [117 chars]
    en  That is the difference between a complaint and a position. Thank you, and I mean the second part more than the first.
    >>  ............................................
    pt  É a diferença entre uma reclamação e uma posição. Obrigada, e eu falo mais sério na segunda parte do que na primeira.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.accepted/3   [109 chars]
    en  Yes. And you will eat here free until they run out, and I will not be argued with while I am holding a knife.
    >>  ............................................
    pt  Sim. E você come aqui de graça até acabarem, e não se discute comigo enquanto eu estou com uma faca na mão.
    >>  ............................................
```


### Button `advise_refusing` — "Refuse the next delivery."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.bruised_deliveries.blocked` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.bruised_deliveries.blocked.advise_refusing` — accepted phrasings: "refuse the next delivery"; "refuse the next delivery"; "send the next load back to the grower"
  - the message must contain one of: `delivery`, `grower`, `load`
  - scored words: `delivery`(1.8), `grower`(1.8), `load`(1.8), `refuse`(0.8), `next`(0.8), `send`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond.advise_refusing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond.advise_refusing   [25 chars]
    en  Refuse the next delivery.
    >>  ............................................
    pt  Recuse a próxima entrega.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.delightcook.stores`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.bruised_deliveries"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.resolved
WHO    VILLAGER — what the player reads after pressing "Refuse the next delivery."
       spoken on: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond, button `advise_refusing`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.bruised_deliveries.blocked.resolved`: the villager accepts. Subject `work.delightcook.stores`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.resolved/1   [136 chars]
    en  In front of the cart, at the door, where the whole lane can see. That is the part that makes it stick and the part I have been avoiding.
    >>  ............................................
    pt  Diante da carroça, na porta, onde a viela inteira possa ver. É a parte que faz aquilo pegar e a parte que eu venho evitando.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.resolved/2   [106 chars]
    en  Yes. He will be furious and then he will send better, because I am four of his eleven customers by weight.
    >>  ............................................
    pt  Sim. Ele vai ficar furioso e depois vai mandar melhor, porque eu sou quatro dos onze clientes dele em peso.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.resolved/3   [125 chars]
    en  I have to have food in the building first. Once I do, refusing is easy, and everything before that is why I have not done it.
    >>  ............................................
    pt  Preciso ter comida no prédio primeiro. Depois disso, recusar é fácil, e tudo antes disso é o motivo de eu não ter feito.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightcook.bruised_deliveries.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the pass.
    >>  ............................................
    pt  Vou deixar você voltar ao balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pass."
       spoken on: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.bruised_deliveries.succeeded` — e.g. "I refused a load at the door and the next one was the best I have had from him in four years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond   [22 chars]
    en  The deliveries, since.
    >>  ............................................
    pt  As entregas, depois disso.
    >>  ............................................
```


### Button `note_the_spread` — "Two other kitchens followed you."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightcook.bruised_deliveries.succeeded` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.bruised_deliveries.succeeded.note_the_spread` — accepted phrasings: "two other kitchens followed you"; "two other kitchens followed you"; "the other kitchens followed your lead"
  - the message must contain one of: `kitchens`, `followed`
  - scored words: `kitchens`(1.8), `followed`(1.8), `two`(0.8), `other`(0.8), `lead`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond.note_the_spread
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond.note_the_spread   [32 chars]
    en  Two other kitchens followed you.
    >>  ............................................
    pt  Outras duas cozinhas te seguiram.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.delightcook.stores`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightcook.bruised_deliveries"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Two other kitchens followed you."
       spoken on: conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond, button `note_the_spread`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.bruised_deliveries.succeeded.acknowledged`: the villager accepts. Subject `work.delightcook.stores`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.acknowledged/1   [116 chars]
    en  One of them had been trimming a third off for six years and had never said so to me, and I had never said so to her.
    >>  ............................................
    pt  Uma delas vinha descartando um terço fazia seis anos e nunca tinha me contado, e eu nunca tinha contado a ela.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.acknowledged/2   [121 chars]
    en  Thank you. Doing it at the door mattered. If I had done it politely in a letter, nobody would have known it was possible.
    >>  ............................................
    pt  Obrigada. Fazer na porta importou. Se eu tivesse feito educadamente por carta, ninguém saberia que era possível.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.acknowledged/3   [129 chars]
    en  We have started talking, the three of us, on the quiet afternoon. It has been eleven years and we had never once compared prices.
    >>  ............................................
    pt  Começamos a conversar, nós três, na tarde parada. São onze anos e a gente nunca tinha comparado preços.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightcook.bruised_deliveries.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the pass.
    >>  ............................................
    pt  Vou deixar você voltar ao balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pass."
       spoken on: conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightcook.followup`

**Reached from 10 route(s):** `conversations.scene.work.delightcook.borrowed_recipe.active.respond` / `ask_what_stops_her`; `conversations.scene.work.delightcook.borrowed_recipe.active.respond` / `advise_asking_the_family`; `conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond` / `note_the_asking`; `conversations.scene.work.delightcook.bruised_deliveries.blocked.respond` / `offer_produce`; `conversations.scene.work.delightcook.bruised_deliveries.blocked.respond` / `advise_refusing`; `conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond` / `note_the_spread`; `conversations.scene.work.delightcook.long_evenings.blocked.respond` / `ask_what_would_fix_it`; `conversations.scene.work.delightcook.long_evenings.blocked.respond` / `advise_fewer_dishes`; `conversations.scene.work.delightcook.long_evenings.blocked.respond` / `notice_the_tiredness`; `conversations.scene.work.delightcook.long_evenings.succeeded.respond` / `ask_about_tasting`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.borrowed_recipe.active.accepted` — e.g. "It is the obvious thing and it is two years overdue, and the reason it is overdue is that I might be told to stop cooking it."
- `conversations.scene.work.delightcook.borrowed_recipe.active.explained` — e.g. "Her family are still here. Putting her name up makes it a memorial, and nobody asked me to make her a memorial out of soup."
- `conversations.scene.work.delightcook.borrowed_recipe.succeeded.acknowledged` — e.g. "She said nobody had asked her about her mother in a year. I had spent two years protecting her from being asked."
- `conversations.scene.work.delightcook.bruised_deliveries.blocked.accepted` — e.g. "Then I can refuse next week's delivery outright, which I could not do while it was the only food in the building."
- `conversations.scene.work.delightcook.bruised_deliveries.blocked.resolved` — e.g. "In front of the cart, at the door, where the whole lane can see. That is the part that makes it stick and the part I have been avoiding."
- `conversations.scene.work.delightcook.bruised_deliveries.succeeded.acknowledged` — e.g. "One of them had been trimming a third off for six years and had never said so to me, and I had never said so to her."
- `conversations.scene.work.delightcook.long_evenings.blocked.conceded` — e.g. "Four done properly beats seven done at speed, and I have said that to other cooks and never once to myself."
- `conversations.scene.work.delightcook.long_evenings.blocked.explained` — e.g. "One more pair of hands for two hours an evening. That is the whole answer and it costs less than the food I am spoiling by being tired."
- `conversations.scene.work.delightcook.long_evenings.blocked.steadied` — e.g. "I am, and you are the first person to say it to my face rather than to somebody else in the doorway."
- `conversations.scene.work.delightcook.long_evenings.succeeded.explained` — e.g. "About a year ago, gradually, the way everything important stops. There was no day I decided it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.followup   [26 chars]
    en  Anything else at the pass?
    >>  ............................................
    pt  Mais alguma coisa no balcão?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a kitchen at dusk?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.delightcook.*` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.followup.ask_more` — accepted phrasings: "whats the hardest part of a kitchen at dusk"; "what is the hardest part of a kitchen at dusk"; "hardest thing about the kitchen at dusk"
  - the message must contain one of: `hardest`, `kitchen`
  - scored words: `hardest`(1.8), `kitchen`(1.8), `whats`(0.8), `part`(0.8), `dusk`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.followup.ask_more   [45 chars]
    en  What's the hardest part of a kitchen at dusk?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma cozinha ao anoitecer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a kitchen at dusk?"
       spoken on: conversations.scene.work.delightcook.followup, button `ask_more`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.hard`: the villager explains. Subject `work.delightcook.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.delightcook.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.delightcook.hard/1   [87 chars]
    en  Late winter. The good stores are gone and the new ones aren't in, and people still eat.
    >>  ............................................
    pt  Fim do inverno. Os bons estoques acabaram e os novos não chegaram, e as pessoas comem mesmo assim.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.hard/2   [74 chars]
    en  Any week the farmer's short, %1$s. I hide vegetables; I can't invent them.
    >>  ............................................
    pt  Qualquer semana em que o fazendeiro falta, %1$s. Eu escondo verdura; não consigo inventar.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.delightcook.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.followup.leave   [27 chars]
    en  I'll leave you to the pass.
    >>  ............................................
    pt  Vou deixar você com o balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the pass."
       spoken on: conversations.scene.work.delightcook.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightcook.long_evenings.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.long_evenings.blocked` — e.g. "%2$s, and %3$s is now the only part of the day where I am any good, and it is getting shorter."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.long_evenings.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond   [12 chars]
    en  The kitchen.
    >>  ............................................
    pt  A cozinha.
    >>  ............................................
```


### Button `ask_what_would_fix_it` — "What would actually fix it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.long_evenings.blocked` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.long_evenings.blocked.ask_what_would_fix_it` — accepted phrasings: "what would actually fix it"; "what would actually fix it"; "what is the real solution here"
  - the message must contain one of: `fix`, `solution`
  - scored words: `fix`(1.8), `solution`(1.8), `actually`(0.8), `real`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.ask_what_would_fix_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.long_evenings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.ask_what_would_fix_it   [27 chars]
    en  What would actually fix it?
    >>  ............................................
    pt  O que resolveria de fato?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.the_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.long_evenings"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What would actually fix it?"
       spoken on: conversations.scene.work.delightcook.long_evenings.blocked.respond, button `ask_what_would_fix_it`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.long_evenings.blocked.explained`: the villager explains. Subject `work.delightcook.the_hours`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.explained/1   [135 chars]
    en  One more pair of hands for two hours an evening. That is the whole answer and it costs less than the food I am spoiling by being tired.
    >>  ............................................
    pt  Mais um par de mãos por duas horas à noite. É a resposta inteira e custa menos que a comida que eu estrago de cansaço.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.explained/2   [118 chars]
    en  Closing one night a week. Everybody says they would understand and nobody would, and I have run that experiment twice.
    >>  ............................................
    pt  Fechar uma noite por semana. Todo mundo diz que entenderia e ninguém entenderia, e eu já rodei esse experimento duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.explained/3   [125 chars]
    en  Doing less. Four dishes instead of seven. It is obviously right and it feels like giving up, which is why I have not done it.
    >>  ............................................
    pt  Fazer menos. Quatro pratos em vez de sete. É obviamente certo e parece desistência, e é por isso que eu não fiz.
    >>  ............................................
```


### Button `advise_fewer_dishes` — "Cook four dishes instead of seven."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.long_evenings.blocked` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.long_evenings.blocked.advise_fewer_dishes` — accepted phrasings: "cook four dishes instead of seven"; "cook four dishes instead of seven"; "cut the number of dishes down"
  - the message must contain one of: `dishes`, `four`, `cut`
  - scored words: `dishes`(1.8), `four`(1.8), `cut`(1.8), `cook`(0.8), `instead`(0.8), `seven`(0.8), `number`(0.8), `down`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.advise_fewer_dishes
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.long_evenings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.advise_fewer_dishes   [34 chars]
    en  Cook four dishes instead of seven.
    >>  ............................................
    pt  Faça quatro pratos em vez de sete.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +1  _(recorded under topic `work.delightcook.the_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.long_evenings"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Cook four dishes instead of seven."
       spoken on: conversations.scene.work.delightcook.long_evenings.blocked.respond, button `advise_fewer_dishes`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.long_evenings.blocked.conceded`: the villager accepts. Subject `work.delightcook.the_hours`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.conceded/1   [107 chars]
    en  Four done properly beats seven done at speed, and I have said that to other cooks and never once to myself.
    >>  ............................................
    pt  Quatro bem feitos vencem sete feitos na correria, e eu já disse isso a outros cozinheiros e nunca uma vez a mim mesma.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.conceded/2   [109 chars]
    en  Yes. Three people will complain for a fortnight and then order one of the four and forget they ever objected.
    >>  ............................................
    pt  Sim. Três pessoas vão reclamar por duas semanas e depois pedir um dos quatro e esquecer que reclamaram.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.conceded/3   [118 chars]
    en  It is a smaller kitchen than the one in my head. I have been cooking for the kitchen in my head for about three years.
    >>  ............................................
    pt  É uma cozinha menor do que a que existe na minha cabeça. Faz uns três anos que eu cozinho para a cozinha da minha cabeça.
    >>  ............................................
```


### Button `notice_the_tiredness` — "You look worn out."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightcook.long_evenings.blocked` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.long_evenings.blocked.notice_the_tiredness` — accepted phrasings: "you look worn out"; "you look worn out"; "you seem exhausted lately"
  - the message must contain one of: `worn`, `exhausted`
  - scored words: `worn`(1.8), `exhausted`(1.8), `look`(0.8), `out`(0.8), `seem`(0.8), `lately`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.notice_the_tiredness
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.long_evenings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.notice_the_tiredness   [18 chars]
    en  You look worn out.
    >>  ............................................
    pt  Você parece exausta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, trust +1  _(recorded under topic `work.delightcook.the_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightcook.long_evenings"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "You look worn out."
       spoken on: conversations.scene.work.delightcook.long_evenings.blocked.respond, button `notice_the_tiredness`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.long_evenings.blocked.steadied`: the villager accepts. Subject `work.delightcook.the_hours`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.steadied/1   [100 chars]
    en  I am, and you are the first person to say it to my face rather than to somebody else in the doorway.
    >>  ............................................
    pt  Estou, e você é a primeira pessoa a dizer isso na minha cara em vez de a outra pessoa na porta.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.steadied/2   [130 chars]
    en  Four nights. It is not the four nights. It is that I cannot see the end of the four nights, and that is a different kind of tired.
    >>  ............................................
    pt  Quatro noites. Não são as quatro noites. É que eu não vejo o fim das quatro noites, e isso é outro tipo de cansaço.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.steadied/3   [114 chars]
    en  Thank you. I will be all right. I would like the record to show that I said that too quickly, because I always do.
    >>  ............................................
    pt  Obrigada. Vou ficar bem. Quero que fique registrado que eu disse isso rápido demais, porque eu sempre digo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightcook.long_evenings.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.long_evenings.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the pass.
    >>  ............................................
    pt  Vou deixar você voltar ao balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pass."
       spoken on: conversations.scene.work.delightcook.long_evenings.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightcook.long_evenings.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightcook.long_evenings.succeeded` — e.g. "Four dishes. %2$s is mine again and I have started tasting things before they go out, which I had stopped doing without noticing."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightcook.long_evenings.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.respond   [19 chars]
    en  The kitchen, since.
    >>  ............................................
    pt  A cozinha, depois disso.
    >>  ............................................
```


### Button `ask_about_tasting` — "You'd stopped tasting?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.long_evenings.succeeded` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightcook.long_evenings.succeeded.ask_about_tasting` — accepted phrasings: "youd stopped tasting"; "you had stopped tasting the food"; "when did the tasting stop"
  - the message must contain one of: `tasting`, `taste`
  - scored words: `tasting`(1.8), `taste`(1.8), `youd`(0.8), `stopped`(0.8), `had`(0.8), `food`(0.8), `when`(0.8), `stop`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.respond.ask_about_tasting
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.long_evenings.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.respond.ask_about_tasting   [22 chars]
    en  You'd stopped tasting?
    >>  ............................................
    pt  Você tinha parado de provar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.delightcook.the_hours`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightcook.long_evenings"}
- Then opens: `conversations.scene.work.delightcook.followup`
- …where the player's next choices will be: "What's the hardest part of a kitchen at dusk?" | "I'll leave you to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "You'd stopped tasting?"
       spoken on: conversations.scene.work.delightcook.long_evenings.succeeded.respond, button `ask_about_tasting`
       leaves the player on: conversations.scene.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.long_evenings.succeeded.explained`: the villager explains. Subject `work.delightcook.the_hours`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.explained/1   [95 chars]
    en  About a year ago, gradually, the way everything important stops. There was no day I decided it.
    >>  ............................................
    pt  Faz mais ou menos um ano, aos poucos, do jeito que tudo o que é importante para. Não houve um dia em que eu decidi.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.explained/2   [126 chars]
    en  You taste to check, and checking takes four seconds, and when you are behind by an hour four seconds is the first thing to go.
    >>  ............................................
    pt  A gente prova para conferir, e conferir leva quatro segundos, e quando você está uma hora atrasada quatro segundos é a primeira coisa a ir embora.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.explained/3   [120 chars]
    en  That is how I know the tiredness had reached the food. Not that anything was bad. That I had stopped being able to tell.
    >>  ............................................
    pt  É assim que eu sei que o cansaço tinha chegado na comida. Não que algo estivesse ruim. Que eu tinha perdido a capacidade de saber.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pass."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightcook.long_evenings.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightcook.long_evenings.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the pass.
    >>  ............................................
    pt  Vou deixar você voltar ao balcão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pass."
       spoken on: conversations.scene.work.delightcook.long_evenings.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.topic.work.delightcook.craft.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook.craft` — e.g. "Making a great deal out of very little. That's not humility, it's the actual skill and it took years."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.craft.respond   [29 chars]
    en  That's what it comes down to.
    >>  ............................................
    pt  É nisso que dá.
    >>  ............................................
```


### Button `ask_proportions` — "What were her proportions?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.craft` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.craft.ask_proportions` — accepted phrasings: "what were her proportions"
  - the message must contain one of: `proportions`, `grandmother`, `recipe`
  - scored words: `proportions`(1.5), `grandmother`(1.2), `recipe`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.craft.respond.ask_proportions
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.craft.respond.ask_proportions   [26 chars]
    en  What were her proportions?
    >>  ............................................
    pt  Quais eram as proporções dela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.craft.ask_proportions`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.craft.ask_proportions
WHO    VILLAGER — what the player reads after pressing "What were her proportions?"
       spoken on: conversations.topic.work.delightcook.craft.respond, button `ask_proportions`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.craft.ask_proportions`: the villager explains. Subject `work.delightcook.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.craft.ask_proportions/1   [95 chars]
    en  Three of the cheap thing to one of the dear thing, and salt like you mean it. That's all of it.
    >>  ............................................
    pt  Três do barato pra um do caro, e sal como quem quer dizer algo. É tudo.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.craft.ask_proportions/2   [86 chars]
    en  In her head, and then in mine, %1$s, and now they're nowhere else and that worries me.
    >>  ............................................
    pt  Na cabeça dela, e depois na minha, %1$s, e agora não estão em mais lugar nenhum e isso me preocupa.
    >>  ............................................
```


### Button `admire` — "Feeding twelve on nothing is a harder skill than a feast."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.craft` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.craft.admire` — accepted phrasings: "feeding twelve on nothing is a harder skill than a feast"
  - the message must contain one of: `nothing`, `harder`, `feast`
  - scored words: `nothing`(1.2), `harder`(1.5), `feast`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.craft.respond.admire   [57 chars]
    en  Feeding twelve on nothing is a harder skill than a feast.
    >>  ............................................
    pt  Alimentar doze com nada é habilidade mais difícil que um banquete.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightcook.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightcook.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.craft.admire
WHO    VILLAGER — what the player reads after pressing "Feeding twelve on nothing is a harder skill than a feast."
       spoken on: conversations.topic.work.delightcook.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.craft.admire`: the villager accepts. Subject `work.delightcook.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.craft.admire/1   [74 chars]
    en  It is, and nobody writes it down, because a feast is what gets remembered.
    >>  ............................................
    pt  É, e ninguém anota, porque é o banquete que fica lembrado.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.craft.admire/2   [71 chars]
    en  The chef would agree with you and the chef would be the only one, %1$s.
    >>  ............................................
    pt  O cozinheiro-chefe concordaria com você e seria o único, %1$s.
    >>  ............................................
```


### Button `ask_matched` — "Why haven't you matched her?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.craft` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.craft.ask_matched` — accepted phrasings: "why haven't you matched her"
  - the message must contain one of: `matched`, `grandmother`
  - scored words: `matched`(1.5), `grandmother`(1.0), `her`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.craft.respond.ask_matched
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.craft.respond.ask_matched   [28 chars]
    en  Why haven't you matched her?
    >>  ............................................
    pt  Por que você não a igualou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.craft.ask_matched`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.craft.ask_matched
WHO    VILLAGER — what the player reads after pressing "Why haven't you matched her?"
       spoken on: conversations.topic.work.delightcook.craft.respond, button `ask_matched`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.craft.ask_matched`: the villager explains. Subject `work.delightcook.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.craft.ask_matched/1   [91 chars]
    en  Because she had less than I do and she never once served a thin pot. I've served thin pots.
    >>  ............................................
    pt  Porque ela tinha menos que eu e nunca serviu um caldo ralo. Eu já servi.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.craft.ask_matched/2   [82 chars]
    en  Because I've never had to, %1$s. She was hungry and I have only ever been careful.
    >>  ............................................
    pt  Porque eu nunca precisei, %1$s. Ela passava fome e eu só fui cuidadoso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.craft.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar pra panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.topic.work.delightcook.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.followup`

**Reached from 20 route(s):** `conversations.scene.work.delightcook.followup` / `ask_more`; `conversations.topic.work.delightcook.craft.respond` / `ask_proportions`; `conversations.topic.work.delightcook.craft.respond` / `admire`; `conversations.topic.work.delightcook.craft.respond` / `ask_matched`; `conversations.topic.work.delightcook.future.respond` / `ask_funded`; `conversations.topic.work.delightcook.future.respond` / `encourage`; `conversations.topic.work.delightcook.future.respond` / `ask_head`; `conversations.topic.work.delightcook.respond` / `ask_hard`; `conversations.topic.work.delightcook.respond` / `value`; `conversations.topic.work.delightcook.respond` / `challenge`; `conversations.topic.work.delightcook.respond` / `challenge`; `conversations.topic.work.delightcook.risk.respond` / `ask_thin` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook.challenge.landed` — e.g. "The chef does the interesting cooking. I do the cooking that happens every day."
- `conversations.work.prof.delightcook.challenge.stung` — e.g. "...The chef has never once fed forty people on what was left."
- `conversations.work.prof.delightcook.craft.admire` — e.g. "It is, and nobody writes it down, because a feast is what gets remembered."
- `conversations.work.prof.delightcook.craft.ask_matched` — e.g. "Because she had less than I do and she never once served a thin pot. I've served thin pots."
- `conversations.work.prof.delightcook.craft.ask_proportions` — e.g. "Three of the cheap thing to one of the dear thing, and salt like you mean it. That's all of it."
- `conversations.work.prof.delightcook.future.ask_funded` — e.g. "A sack of barley a month from the common store. I've asked for it as a favour and been given a favour."
- `conversations.work.prof.delightcook.future.ask_head` — e.g. "Anybody's. That's the honest answer and it's why I say them out loud while I cook, to an empty room."
- `conversations.work.prof.delightcook.future.encourage` — e.g. "...As a line. A favour can be withdrawn quietly and a line has to be argued away. That's the difference."
- `conversations.work.prof.delightcook.hard` — e.g. "Late winter. The good stores are gone and the new ones aren't in, and people still eat."
- `conversations.work.prof.delightcook.risk.ask_fund` — e.g. "He'd want the four names. That's the whole of why I've never asked and never will."
- `conversations.work.prof.delightcook.risk.ask_thin` — e.g. "Two winters ago it was water with an opinion in it. I served it and I said nothing and neither did they."
- `conversations.work.prof.delightcook.risk.sympathise` — e.g. "...I'd rather it stayed quiet. Said out loud it starts to sound like I want something for it."
- `conversations.work.prof.delightcook.task.ask_four` — e.g. "I'll not say. They'd have to be grateful in public and that's a thing I've spared them."
- `conversations.work.prof.delightcook.task.ask_pot` — e.g. "Barley, the end of the bacon, and whatever the farmer had too much of. It's always that shape."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.followup   [23 chars]
    en  That's the line and me.
    >>  ............................................
    pt  É a fila e eu.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.challenge.landed`, `work.delightcook.challenge.stung`, `work.delightcook.craft.admire`, `work.delightcook.craft.ask_matched`, `work.delightcook.craft.ask_proportions`, `work.delightcook.future.ask_funded`, `work.delightcook.future.ask_head`, `work.delightcook.future.encourage`, `work.delightcook.hard`, `work.delightcook.risk.ask_fund`, `work.delightcook.risk.ask_thin`, `work.delightcook.risk.sympathise`, `work.delightcook.task.ask_four`, `work.delightcook.task.ask_pot`, `work.delightcook.task.offer_hands`, `work.delightcook.value`, `work.delightcook.village.ask_nineteen`, `work.delightcook.village.ask_second_bowl`, `work.delightcook.village.say_thanks` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.delightcook.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `everyday`, `invisible`
  - scored words: `thought`(1.2), `everyday`(1.5), `invisible`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.delightcook.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.delightcook.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.delightcook.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.delightcook.thanks`: the villager accepts. Subject `work.delightcook.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.thanks/1   [64 chars]
    en  Few do. Everyday food is invisible until a day comes without it.
    >>  ............................................
    pt  Poucos pensam. Comida do dia a dia é invisível até vir um dia sem ela.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.thanks/2   [76 chars]
    en  I'd rather be thought of once properly than praised weekly, %1$s. Thank you.
    >>  ............................................
    pt  Prefiro ser pensado uma vez direito a ser elogiado toda semana, %1$s. Obrigado.
    >>  ............................................
```


### Button `ask_more` — "What do you make when there's nothing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.challenge.landed`, `work.delightcook.challenge.stung`, `work.delightcook.craft.admire`, `work.delightcook.craft.ask_matched`, `work.delightcook.craft.ask_proportions`, `work.delightcook.future.ask_funded`, `work.delightcook.future.ask_head`, `work.delightcook.future.encourage`, `work.delightcook.hard`, `work.delightcook.risk.ask_fund`, `work.delightcook.risk.ask_thin`, `work.delightcook.risk.sympathise`, `work.delightcook.task.ask_four`, `work.delightcook.task.ask_pot`, `work.delightcook.task.offer_hands`, `work.delightcook.value`, `work.delightcook.village.ask_nineteen`, `work.delightcook.village.ask_second_bowl`, `work.delightcook.village.say_thanks` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.delightcook.more` — accepted phrasings: "what do you make when there's nothing"
  - the message must contain one of: `nothing`, `empty`, `stew`
  - scored words: `nothing`(1.5), `empty`(1.2), `stew`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.followup.ask_more   [38 chars]
    en  What do you make when there's nothing?
    >>  ............................................
    pt  O que você faz quando não tem nada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.delightcook.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.more
WHO    VILLAGER — what the player reads after pressing "What do you make when there's nothing?"
       spoken on: conversations.topic.work.delightcook.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.delightcook.more`: the villager discloses. Subject `work.delightcook.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.more/1   [80 chars]
    en  Stew. There is always stew. Stew is what a kitchen says instead of 'we managed'.
    >>  ............................................
    pt  Ensopado. Sempre tem ensopado. Ensopado é o que uma cozinha diz em vez de 'a gente se virou'.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.more/2   [76 chars]
    en  Bread and an apology. I've done both more often than I'd like to write down.
    >>  ............................................
    pt  Pão e um pedido de desculpas. Já fiz os dois mais vezes do que gostaria de anotar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. Two winters ago it was water with an opinion in it and I served it and said nothing.
    >>  ............................................
    pt  Ensopado. Dois invernos atrás era água com uma opinião dentro e eu servi e não disse nada.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then I'd stop being the one who decides which nineteen people eat.
    >>  ............................................
    pt  O caldeirão financiado. Aí eu pararia de ser quem decide quais dezenove pessoas comem.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, the same as it's been for years. There is always stew and there always has been.
    >>  ............................................
    pt  Ensopado, o mesmo de sempre. Sempre tem ensopado e sempre teve.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightcook.more/2
    en  A regular sack of barley. It'll come or it won't; the pot has managed without it so far.
    >>  ............................................
    pt  Um saco de cevada regular. Vem ou não vem; o caldeirão se virou sem até agora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. There is always stew. Stew is what a kitchen says instead of 'we managed'.
    >>  ............................................
    pt  Ensopado. Sempre tem ensopado. Ensopado é o que uma cozinha diz em vez de 'a gente se virou'.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then thinning it is a decision about barley and not about people.
    >>  ............................................
    pt  O caldeirão financiado. Aí ralar é decisão sobre cevada e não sobre pessoas.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. There is always stew. Stew is what a kitchen says instead of 'we managed'.
    >>  ............................................
    pt  Ensopado. Sempre tem ensopado. Ensopado é o que uma cozinha diz em vez de 'a gente se virou'.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then thinning it is a decision about barley and not about people.
    >>  ............................................
    pt  O caldeirão financiado. Aí ralar é decisão sobre cevada e não sobre pessoas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, always. Bring a bowl — there's more in that pot than there has any right to be.
    >>  ............................................
    pt  Ensopado, sempre. Traga uma tigela — tem mais naquele caldeirão do que deveria.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot on the books. Then nobody would have to be quietly grateful to me, which is the part I'd fix.
    >>  ............................................
    pt  O caldeirão no orçamento. Aí ninguém precisaria me agradecer em silêncio, que é a parte que eu consertaria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, always. Bring a bowl — there's more in that pot than there has any right to be.
    >>  ............................................
    pt  Ensopado, sempre. Traga uma tigela — tem mais naquele caldeirão do que deveria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot on the books. Then nobody would have to be quietly grateful to me, which is the part I'd fix.
    >>  ............................................
    pt  O caldeirão no orçamento. Aí ninguém precisaria me agradecer em silêncio, que é a parte que eu consertaria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, always. Bring a bowl — there's more in that pot than there has any right to be.
    >>  ............................................
    pt  Ensopado, sempre. Traga uma tigela — tem mais naquele caldeirão do que deveria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot on the books. Then nobody would have to be quietly grateful to me, which is the part I'd fix.
    >>  ............................................
    pt  O caldeirão no orçamento. Aí ninguém precisaria me agradecer em silêncio, que é a parte que eu consertaria.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. Two winters ago it was water with an opinion in it and I served it and said nothing.
    >>  ............................................
    pt  Ensopado. Dois invernos atrás era água com uma opinião dentro e eu servi e não disse nada.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then I'd stop being the one who decides which nineteen people eat.
    >>  ............................................
    pt  O caldeirão financiado. Aí eu pararia de ser quem decide quais dezenove pessoas comem.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. There is always stew. Stew is what a kitchen says instead of 'we managed'.
    >>  ............................................
    pt  Ensopado. Sempre tem ensopado. Ensopado é o que uma cozinha diz em vez de 'a gente se virou'.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then thinning it is a decision about barley and not about people.
    >>  ............................................
    pt  O caldeirão financiado. Aí ralar é decisão sobre cevada e não sobre pessoas.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. There is always stew. Stew is what a kitchen says instead of 'we managed'.
    >>  ............................................
    pt  Ensopado. Sempre tem ensopado. Ensopado é o que uma cozinha diz em vez de 'a gente se virou'.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then thinning it is a decision about barley and not about people.
    >>  ............................................
    pt  O caldeirão financiado. Aí ralar é decisão sobre cevada e não sobre pessoas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. Barley, the end of the bacon, and whatever the farmer had too much of.
    >>  ............................................
    pt  Ensopado. Cevada, o fim do toucinho, e o que o fazendeiro tinha demais.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month from the common store. I've asked for it as a favour and been given a favour.
    >>  ............................................
    pt  Um saco de cevada por mês do celeiro comum. Pedi como favor e recebi um favor.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, the same as it's been for years. There is always stew and there always has been.
    >>  ............................................
    pt  Ensopado, o mesmo de sempre. Sempre tem ensopado e sempre teve.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightcook.more/2
    en  A regular sack of barley. It'll come or it won't; the pot has managed without it so far.
    >>  ............................................
    pt  Um saco de cevada regular. Vem ou não vem; o caldeirão se virou sem até agora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. Barley, the end of the bacon, and whatever the farmer had too much of.
    >>  ............................................
    pt  Ensopado. Cevada, o fim do toucinho, e o que o fazendeiro tinha demais.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month from the common store. I've asked for it as a favour and been given a favour.
    >>  ............................................
    pt  Um saco de cevada por mês do celeiro comum. Pedi como favor e recebi um favor.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, the same as it's been for years. There is always stew and there always has been.
    >>  ............................................
    pt  Ensopado, o mesmo de sempre. Sempre tem ensopado e sempre teve.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightcook.more/2
    en  A regular sack of barley. It'll come or it won't; the pot has managed without it so far.
    >>  ............................................
    pt  Um saco de cevada regular. Vem ou não vem; o caldeirão se virou sem até agora.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew! There is always stew. Stew is a kitchen's way of saying 'we managed', and we did.
    >>  ............................................
    pt  Ensopado! Sempre tem ensopado. Ensopado é o jeito da cozinha dizer 'a gente se virou', e a gente se virou.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month. That's the whole ambition. It's not a grand one and I want it enormously.
    >>  ............................................
    pt  Um saco de cevada por mês. É toda a ambição. Não é grandiosa e eu quero enormemente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew! There is always stew. Stew is a kitchen's way of saying 'we managed', and we did.
    >>  ............................................
    pt  Ensopado! Sempre tem ensopado. Ensopado é o jeito da cozinha dizer 'a gente se virou', e a gente se virou.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month. That's the whole ambition. It's not a grand one and I want it enormously.
    >>  ............................................
    pt  Um saco de cevada por mês. É toda a ambição. Não é grandiosa e eu quero enormemente.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew, the same as it's been for years. There is always stew and there always has been.
    >>  ............................................
    pt  Ensopado, o mesmo de sempre. Sempre tem ensopado e sempre teve.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightcook.more/2
    en  A regular sack of barley. It'll come or it won't; the pot has managed without it so far.
    >>  ............................................
    pt  Um saco de cevada regular. Vem ou não vem; o caldeirão se virou sem até agora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. Two winters ago it was water with an opinion in it and I served it and said nothing.
    >>  ............................................
    pt  Ensopado. Dois invernos atrás era água com uma opinião dentro e eu servi e não disse nada.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightcook.more/2
    en  The pot funded. Then I'd stop being the one who decides which nineteen people eat.
    >>  ............................................
    pt  O caldeirão financiado. Aí eu pararia de ser quem decide quais dezenove pessoas comem.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew. Barley, the end of the bacon, and whatever the farmer had too much of.
    >>  ............................................
    pt  Ensopado. Cevada, o fim do toucinho, e o que o fazendeiro tinha demais.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month from the common store. I've asked for it as a favour and been given a favour.
    >>  ............................................
    pt  Um saco de cevada por mês do celeiro comum. Pedi como favor e recebi um favor.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew! There is always stew. Stew is a kitchen's way of saying 'we managed', and we did.
    >>  ............................................
    pt  Ensopado! Sempre tem ensopado. Ensopado é o jeito da cozinha dizer 'a gente se virou', e a gente se virou.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month. That's the whole ambition. It's not a grand one and I want it enormously.
    >>  ............................................
    pt  Um saco de cevada por mês. É toda a ambição. Não é grandiosa e eu quero enormemente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightcook.more/1
    en  Stew! There is always stew. Stew is a kitchen's way of saying 'we managed', and we did.
    >>  ............................................
    pt  Ensopado! Sempre tem ensopado. Ensopado é o jeito da cozinha dizer 'a gente se virou', e a gente se virou.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightcook.more/2
    en  A sack of barley a month. That's the whole ambition. It's not a grand one and I want it enormously.
    >>  ............................................
    pt  Um saco de cevada por mês. É toda a ambição. Não é grandiosa e eu quero enormemente.
    >>  ............................................
```

</details>


### Button `leave` — "Eat something."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.challenge.landed`, `work.delightcook.challenge.stung`, `work.delightcook.craft.admire`, `work.delightcook.craft.ask_matched`, `work.delightcook.craft.ask_proportions`, `work.delightcook.future.ask_funded`, `work.delightcook.future.ask_head`, `work.delightcook.future.encourage`, `work.delightcook.hard`, `work.delightcook.risk.ask_fund`, `work.delightcook.risk.ask_thin`, `work.delightcook.risk.sympathise`, `work.delightcook.task.ask_four`, `work.delightcook.task.ask_pot`, `work.delightcook.task.offer_hands`, `work.delightcook.value`, `work.delightcook.village.ask_nineteen`, `work.delightcook.village.ask_second_bowl`, `work.delightcook.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.followup.leave   [14 chars]
    en  Eat something.
    >>  ............................................
    pt  Coma alguma coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "Eat something."
       spoken on: conversations.topic.work.delightcook.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook.future` — e.g. "I want the pot funded, so that thinning it is a decision about barley and not about people."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.future.respond   [22 chars]
    en  That's what I'm after.
    >>  ............................................
    pt  É o que eu quero.
    >>  ............................................
```


### Button `ask_funded` — "What would funding the pot take?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.future` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.future.ask_funded` — accepted phrasings: "what would funding the pot take"
  - the message must contain one of: `funded`, `barley`, `sack`
  - scored words: `funded`(1.5), `barley`(1.2), `sack`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.future.respond.ask_funded
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.future.respond.ask_funded   [32 chars]
    en  What would funding the pot take?
    >>  ............................................
    pt  O que exigiria financiar o caldeirão?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.future.ask_funded`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.future.ask_funded
WHO    VILLAGER — what the player reads after pressing "What would funding the pot take?"
       spoken on: conversations.topic.work.delightcook.future.respond, button `ask_funded`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.future.ask_funded`: the villager explains. Subject `work.delightcook.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.future.ask_funded/1   [102 chars]
    en  A sack of barley a month from the common store. I've asked for it as a favour and been given a favour.
    >>  ............................................
    pt  Um saco de cevada por mês do celeiro comum. Pedi como favor e recebi um favor.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.future.ask_funded/2   [98 chars]
    en  It's the smallest sum anybody in that hall discusses, %1$s, and it's the one they discuss longest.
    >>  ............................................
    pt  É a menor soma que se discute naquele salão, %1$s, e é a que discutem por mais tempo.
    >>  ............................................
```


### Button `encourage` — "Ask for it as a line, not a favour."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.future` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.future.encourage` — accepted phrasings: "ask for it as a line, not a favour"
  - the message must contain one of: `line`, `favour`
  - scored words: `line`(1.5), `favour`(1.2), `ask`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.future.respond.encourage   [35 chars]
    en  Ask for it as a line, not a favour.
    >>  ............................................
    pt  Peça como uma rubrica, não como favor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightcook.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightcook.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.future.encourage
WHO    VILLAGER — what the player reads after pressing "Ask for it as a line, not a favour."
       spoken on: conversations.topic.work.delightcook.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.future.encourage`: the villager accepts. Subject `work.delightcook.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.future.encourage/1   [104 chars]
    en  ...As a line. A favour can be withdrawn quietly and a line has to be argued away. That's the difference.
    >>  ............................................
    pt  ...Como rubrica. Um favor se retira em silêncio e uma rubrica precisa ser discutida. É a diferença.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.future.encourage/2   [97 chars]
    en  The difference has never been put to me in those words, %1$s, and I've asked wrong for six years.
    >>  ............................................
    pt  A diferença nunca me foi posta nessas palavras, %1$s, e eu pedi errado por seis anos.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've been asking for a favour because a line felt like too much to want.
    >>  ............................................
    pt  ...Como uma linha. Pedi favor porque linha parecia querer demais.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. I'd rather have been told sooner and I'm glad it was you.
    >>  ............................................
    pt  Seis anos pedindo errado. Preferia ter ouvido antes e ainda bem que foi você.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've watched favours withdrawn quietly for thirty years.
    >>  ............................................
    pt  ...Como uma linha. Vi favores serem retirados em silêncio por trinta anos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. That's not the longest mistake I've made here.
    >>  ............................................
    pt  Seis anos pedindo errado. Não é o erro mais longo que cometi aqui.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. A favour is withdrawn quietly and a line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha. Um favor se retira em silêncio e uma linha tem que ser discutida.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me in those words, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença nessas palavras, e pedi errado por seis anos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. A favour is withdrawn quietly and a line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha. Um favor se retira em silêncio e uma linha tem que ser discutida.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me in those words, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença nessas palavras, e pedi errado por seis anos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line, %1$s. A favour goes quietly. A line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha, %1$s. Um favor some quieto. Uma linha tem que ser discutida.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me like that, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença assim, e pedi errado por seis anos.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line, %1$s. A favour goes quietly. A line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha, %1$s. Um favor some quieto. Uma linha tem que ser discutida.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me like that, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença assim, e pedi errado por seis anos.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line, %1$s. A favour goes quietly. A line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha, %1$s. Um favor some quieto. Uma linha tem que ser discutida.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me like that, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença assim, e pedi errado por seis anos.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've been asking for a favour because a line felt like too much to want.
    >>  ............................................
    pt  ...Como uma linha. Pedi favor porque linha parecia querer demais.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. I'd rather have been told sooner and I'm glad it was you.
    >>  ............................................
    pt  Seis anos pedindo errado. Preferia ter ouvido antes e ainda bem que foi você.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. A favour is withdrawn quietly and a line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha. Um favor se retira em silêncio e uma linha tem que ser discutida.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me in those words, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença nessas palavras, e pedi errado por seis anos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. A favour is withdrawn quietly and a line has to be argued away.
    >>  ............................................
    pt  ...Como uma linha. Um favor se retira em silêncio e uma linha tem que ser discutida.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put the difference to me in those words, and I've asked wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs a diferença nessas palavras, e pedi errado por seis anos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. Favours go quietly.
    >>  ............................................
    pt  ...Como uma linha. Favores somem quietos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong, then.
    >>  ............................................
    pt  Seis anos pedindo errado, então.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've watched favours withdrawn quietly for thirty years.
    >>  ............................................
    pt  ...Como uma linha. Vi favores serem retirados em silêncio por trinta anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. That's not the longest mistake I've made here.
    >>  ............................................
    pt  Seis anos pedindo errado. Não é o erro mais longo que cometi aqui.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. Favours go quietly.
    >>  ............................................
    pt  ...Como uma linha. Favores somem quietos.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong, then.
    >>  ............................................
    pt  Seis anos pedindo errado, então.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've watched favours withdrawn quietly for thirty years.
    >>  ............................................
    pt  ...Como uma linha. Vi favores serem retirados em silêncio por trinta anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. That's not the longest mistake I've made here.
    >>  ............................................
    pt  Seis anos pedindo errado. Não é o erro mais longo que cometi aqui.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line! A favour vanishes quietly; a line has to be argued away in front of people.
    >>  ............................................
    pt  ...Como uma linha! Um favor some em silêncio; uma linha tem que ser discutida na frente de todos.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put it to me in those words, and I've been asking wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs nessas palavras, e venho pedindo errado há seis anos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line! A favour vanishes quietly; a line has to be argued away in front of people.
    >>  ............................................
    pt  ...Como uma linha! Um favor some em silêncio; uma linha tem que ser discutida na frente de todos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put it to me in those words, and I've been asking wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs nessas palavras, e venho pedindo errado há seis anos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've watched favours withdrawn quietly for thirty years.
    >>  ............................................
    pt  ...Como uma linha. Vi favores serem retirados em silêncio por trinta anos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. That's not the longest mistake I've made here.
    >>  ............................................
    pt  Seis anos pedindo errado. Não é o erro mais longo que cometi aqui.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. I've been asking for a favour because a line felt like too much to want.
    >>  ............................................
    pt  ...Como uma linha. Pedi favor porque linha parecia querer demais.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong. I'd rather have been told sooner and I'm glad it was you.
    >>  ............................................
    pt  Seis anos pedindo errado. Preferia ter ouvido antes e ainda bem que foi você.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line. Favours go quietly.
    >>  ............................................
    pt  ...Como uma linha. Favores somem quietos.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Six years of asking wrong, then.
    >>  ............................................
    pt  Seis anos pedindo errado, então.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line! A favour vanishes quietly; a line has to be argued away in front of people.
    >>  ............................................
    pt  ...Como uma linha! Um favor some em silêncio; uma linha tem que ser discutida na frente de todos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put it to me in those words, and I've been asking wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs nessas palavras, e venho pedindo errado há seis anos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightcook.future.encourage/1
    en  ...As a line! A favour vanishes quietly; a line has to be argued away in front of people.
    >>  ............................................
    pt  ...Como uma linha! Um favor some em silêncio; uma linha tem que ser discutida na frente de todos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightcook.future.encourage/2
    en  Nobody has put it to me in those words, and I've been asking wrong for six years.
    >>  ............................................
    pt  Ninguém me pôs nessas palavras, e venho pedindo errado há seis anos.
    >>  ............................................
```

</details>


### Button `ask_head` — "Whose head should have the proportions?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.future` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.future.ask_head` — accepted phrasings: "whose head should have the proportions"
  - the message must contain one of: `proportions`, `head`, `successor`
  - scored words: `proportions`(1.2), `head`(1.2), `successor`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.future.respond.ask_head
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.future.respond.ask_head   [39 chars]
    en  Whose head should have the proportions?
    >>  ............................................
    pt  Na cabeça de quem as proporções deveriam estar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.future.ask_head`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.future.ask_head
WHO    VILLAGER — what the player reads after pressing "Whose head should have the proportions?"
       spoken on: conversations.topic.work.delightcook.future.respond, button `ask_head`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.future.ask_head`: the villager explains. Subject `work.delightcook.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.future.ask_head/1   [100 chars]
    en  Anybody's. That's the honest answer and it's why I say them out loud while I cook, to an empty room.
    >>  ............................................
    pt  De qualquer um. É a resposta honesta e por isso eu digo em voz alta enquanto cozinho, pra uma sala vazia.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.future.ask_head/2   [102 chars]
    en  A child, ideally. They keep proportions the way I did, %1$s, without knowing they're keeping anything.
    >>  ............................................
    pt  De uma criança, de preferência. Elas guardam proporções como eu guardei, %1$s, sem saber que guardam.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.future.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar pra panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.topic.work.delightcook.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook` — e.g. "I feed the village the food it needs, not the food it deserves. There are vegetables hidden in everything."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.respond   [34 chars]
    en  That's the pot line and its rules.
    >>  ............................................
    pt  É a fila da panela e as regras dela.
    >>  ............................................
```


### Button `ask_hard` — "What's the hardest week?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.identity` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.hard` — accepted phrasings: "what's the hardest week"
  - the message must contain one of: `hardest`, `week`, `shortage`
  - scored words: `hardest`(1.2), `week`(1.2), `shortage`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.respond.ask_hard   [24 chars]
    en  What's the hardest week?
    >>  ............................................
    pt  Qual é a semana mais difícil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.delightcook.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest week?"
       spoken on: conversations.topic.work.delightcook.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.hard`: the villager explains. Subject `work.delightcook.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.followup / ask_more
```

> Written out in full under **`conversations.scene.work.delightcook.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "The children eat properly because of you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.identity` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.value` — accepted phrasings: "the children eat properly because of you"
  - the message must contain one of: `children`, `properly`, `fed`
  - scored words: `children`(1.5), `properly`(1.2), `fed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.respond.value   [41 chars]
    en  The children eat properly because of you.
    >>  ............................................
    pt  As crianças comem direito por sua causa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightcook.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.delightcook.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.value
WHO    VILLAGER — what the player reads after pressing "The children eat properly because of you."
       spoken on: conversations.topic.work.delightcook.respond, button `value`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.value`: the villager accepts. Subject `work.delightcook.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.value/1   [70 chars]
    en  They do. Not one of them knows it and that is exactly as it should be.
    >>  ............................................
    pt  Comem. Nenhuma delas sabe e é exatamente como tem que ser.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.value/2   [92 chars]
    en  That's the job. Not the chef's job — mine. There's a difference and I've made peace with it.
    >>  ............................................
    pt  É o trabalho. Não o do chef — o meu. Tem diferença e eu já fiz as pazes com ela.
    >>  ............................................
```


### Button `challenge` — "The chef does the real cooking."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.delightcook.identity` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.challenge` — accepted phrasings: "the chef does the real cooking"
  - the message must contain one of: `chef`, `real`, `proper`
  - scored words: `chef`(1.5), `real`(1.0), `proper`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.respond.challenge   [31 chars]
    en  The chef does the real cooking.
    >>  ............................................
    pt  O chef é que cozinha de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.delightcook.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.delightcook.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.challenge.landed
WHO    VILLAGER — what the player reads after pressing "The chef does the real cooking."
       spoken on: conversations.topic.work.delightcook.respond, button `challenge`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.challenge.landed`: the villager resists. Subject `work.delightcook.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.challenge.landed/1   [79 chars]
    en  The chef does the interesting cooking. I do the cooking that happens every day.
    >>  ............................................
    pt  O chef faz a comida interessante. Eu faço a comida que acontece todo dia.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.challenge.landed/2   [78 chars]
    en  That's what everyone thinks, %1$s, and I'd rather they thought about the food.
    >>  ............................................
    pt  É o que todo mundo pensa, %1$s, e eu prefiro que pensem na comida.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.delightcook.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.delightcook.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.challenge.stung
WHO    VILLAGER — what the player reads after pressing "The chef does the real cooking."
       spoken on: conversations.topic.work.delightcook.respond, button `challenge`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.challenge.stung`: the villager resists. Subject `work.delightcook.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.challenge.stung/1   [61 chars]
    en  ...The chef has never once fed forty people on what was left.
    >>  ............................................
    pt  ...O chef nunca alimentou quarenta pessoas com o que sobrou.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.challenge.stung/2   [65 chars]
    en  Real cooking. Right. Eat at my line for a month and then rank us.
    >>  ............................................
    pt  Cozinhar de verdade. Certo. Coma na minha fila por um mês e depois nos classifique.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar pra panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.topic.work.delightcook.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook.risk` — e.g. "When the stores are short I'm the one who thins the pot, and thinning it is a decision about people."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.risk.respond   [28 chars]
    en  That's what's under the lid.
    >>  ............................................
    pt  É o que está sob a tampa.
    >>  ............................................
```


### Button `ask_thin` — "How thin has it had to get?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.risk` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.risk.ask_thin` — accepted phrasings: "how thin has it had to get"
  - the message must contain one of: `thin`, `short`, `winter`
  - scored words: `thin`(1.5), `short`(1.2), `winter`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.risk.respond.ask_thin
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.risk.respond.ask_thin   [27 chars]
    en  How thin has it had to get?
    >>  ............................................
    pt  Quão ralo já ficou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.risk.ask_thin`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.risk.ask_thin
WHO    VILLAGER — what the player reads after pressing "How thin has it had to get?"
       spoken on: conversations.topic.work.delightcook.risk.respond, button `ask_thin`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.risk.ask_thin`: the villager explains. Subject `work.delightcook.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.risk.ask_thin/1   [104 chars]
    en  Two winters ago it was water with an opinion in it. I served it and I said nothing and neither did they.
    >>  ............................................
    pt  Dois invernos atrás era água com uma opinião dentro. Servi e não disse nada e eles também não.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.risk.ask_thin/2   [89 chars]
    en  Thin enough that I stopped eating from it myself, %1$s, so that the count came out right.
    >>  ............................................
    pt  Ralo o bastante pra eu parar de comer dele, %1$s, pra conta fechar.
    >>  ............................................
```


### Button `sympathise` — "Your own flour, unasked and unfunded, is a quiet kind of giving."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.risk` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.risk.sympathise` — accepted phrasings: "your own flour, unasked and unfunded, is a quiet kind of giving"
  - the message must contain one of: `flour`, `unasked`, `giving`
  - scored words: `flour`(1.5), `unasked`(1.2), `giving`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.risk.respond.sympathise   [64 chars]
    en  Your own flour, unasked and unfunded, is a quiet kind of giving.
    >>  ............................................
    pt  Sua própria farinha, sem pedirem e sem financiar, é um dar silencioso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.delightcook.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightcook.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Your own flour, unasked and unfunded, is a quiet kind of giving."
       spoken on: conversations.topic.work.delightcook.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.risk.sympathise`: the villager accepts. Subject `work.delightcook.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.risk.sympathise/1   [93 chars]
    en  ...I'd rather it stayed quiet. Said out loud it starts to sound like I want something for it.
    >>  ............................................
    pt  ...Eu preferia que ficasse silencioso. Dito alto começa a soar como se eu quisesse algo.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.risk.sympathise/2   [87 chars]
    en  It's four loaves. Said the way you said it, %1$s, it sounds like more than four loaves.
    >>  ............................................
    pt  São quatro pães. Dito do jeito que você disse, %1$s, soa como mais que quatro pães.
    >>  ............................................
```


### Button `ask_fund` — "Would the mayor really refuse to fund it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.risk` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.risk.ask_fund` — accepted phrasings: "would the mayor really refuse to fund it"
  - the message must contain one of: `fund`, `mayor`, `refuse`
  - scored words: `fund`(1.5), `mayor`(1.2), `refuse`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.risk.respond.ask_fund
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.risk.respond.ask_fund   [41 chars]
    en  Would the mayor really refuse to fund it?
    >>  ............................................
    pt  O prefeito recusaria mesmo financiar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.risk.ask_fund`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.risk.ask_fund
WHO    VILLAGER — what the player reads after pressing "Would the mayor really refuse to fund it?"
       spoken on: conversations.topic.work.delightcook.risk.respond, button `ask_fund`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.risk.ask_fund`: the villager explains. Subject `work.delightcook.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.risk.ask_fund/1   [82 chars]
    en  He'd want the four names. That's the whole of why I've never asked and never will.
    >>  ............................................
    pt  Ele ia querer os quatro nomes. É toda a razão de eu nunca ter pedido e nunca pedir.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.risk.ask_fund/2   [91 chars]
    en  He'd fund it and then it would be a list, %1$s, and a list is a different thing from bread.
    >>  ............................................
    pt  Ele financiaria e aí viraria uma lista, %1$s, e uma lista é outra coisa que não pão.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.risk.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar pra panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.topic.work.delightcook.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook.task` — e.g. "The pot. It goes on before dawn and it comes off after dark and something is always in it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.task.respond   [28 chars]
    en  That's the pot and the oven.
    >>  ............................................
    pt  É o caldeirão e o forno.
    >>  ............................................
```


### Button `ask_four` — "Which four houses?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.task` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.task.ask_four` — accepted phrasings: "which four houses"
  - the message must contain one of: `houses`, `four`
  - scored words: `houses`(1.5), `four`(1.2), `which`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.task.respond.ask_four
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.task.respond.ask_four   [18 chars]
    en  Which four houses?
    >>  ............................................
    pt  Quais quatro casas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.task.ask_four`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.task.ask_four
WHO    VILLAGER — what the player reads after pressing "Which four houses?"
       spoken on: conversations.topic.work.delightcook.task.respond, button `ask_four`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.task.ask_four`: the villager explains. Subject `work.delightcook.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.task.ask_four/1   [87 chars]
    en  I'll not say. They'd have to be grateful in public and that's a thing I've spared them.
    >>  ............................................
    pt  Não vou dizer. Teriam que agradecer em público e isso eu poupei delas.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.task.ask_four/2   [78 chars]
    en  The ones where nobody comes to the door quickly, %1$s. You learn to read that.
    >>  ............................................
    pt  As casas onde ninguém vem à porta rápido, %1$s. Você aprende a ler isso.
    >>  ............................................
```


### Button `offer_hands` — "I can carry the four loaves round."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.delightcook.task` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.task.offer_hands` — accepted phrasings: "i can carry the four loaves round"
  - the message must contain one of: `loaves`, `carry`, `deliver`
  - scored words: `loaves`(1.5), `carry`(1.2), `deliver`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.task.respond.offer_hands   [34 chars]
    en  I can carry the four loaves round.
    >>  ............................................
    pt  Eu posso levar os quatro pães.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.delightcook.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightcook.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can carry the four loaves round."
       spoken on: conversations.topic.work.delightcook.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.task.offer_hands`: the villager accepts. Subject `work.delightcook.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.task.offer_hands/1   [94 chars]
    en  ...Not you. Not because of you — because a stranger at that door changes what the bread means.
    >>  ............................................
    pt  ...Você não. Não por sua causa — porque um estranho naquela porta muda o que o pão significa.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.task.offer_hands/2   [76 chars]
    en  Then leave them on the step and don't knock, %1$s. That's the entire method.
    >>  ............................................
    pt  Então deixe no degrau e não bata, %1$s. É todo o método.
    >>  ............................................
```


### Button `ask_pot` — "What's in the pot today?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.task` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.task.ask_pot` — accepted phrasings: "what's in the pot today"
  - the message must contain one of: `pot`, `soup`
  - scored words: `pot`(1.5), `today`(0.8), `soup`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.task.respond.ask_pot
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.task.respond.ask_pot   [24 chars]
    en  What's in the pot today?
    >>  ............................................
    pt  O que tem no caldeirão hoje?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.task.ask_pot`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.task.ask_pot
WHO    VILLAGER — what the player reads after pressing "What's in the pot today?"
       spoken on: conversations.topic.work.delightcook.task.respond, button `ask_pot`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.task.ask_pot`: the villager explains. Subject `work.delightcook.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.task.ask_pot/1   [94 chars]
    en  Barley, the end of the bacon, and whatever the farmer had too much of. It's always that shape.
    >>  ............................................
    pt  Cevada, o fim do toucinho, e o que o fazendeiro tinha demais. É sempre esse formato.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.task.ask_pot/2   [91 chars]
    en  Nothing you'd write down, %1$s, and it'll feed nineteen people and nobody will remember it.
    >>  ............................................
    pt  Nada que você anotaria, %1$s, e vai alimentar dezenove pessoas e ninguém vai lembrar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.task.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar pra panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.topic.work.delightcook.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightcook.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightcook.village` — e.g. "Nineteen people eat from that pot most days. It's not a feast and it's the reason they get to the feast."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightcook.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightcook.village.respond   [22 chars]
    en  That's my share of it.
    >>  ............................................
    pt  É a minha parte.
    >>  ............................................
```


### Button `ask_second_bowl` — "Do you say anything to the ones who come back?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.village` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.village.ask_second_bowl` — accepted phrasings: "do you say anything to the ones who come back"
  - the message must contain one of: `bowl`, `second`
  - scored words: `bowl`(1.5), `second`(1.2), `say`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.village.respond.ask_second_bowl
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.village.respond.ask_second_bowl   [46 chars]
    en  Do you say anything to the ones who come back?
    >>  ............................................
    pt  Você diz algo pros que voltam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.village.ask_second_bowl`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.village.ask_second_bowl
WHO    VILLAGER — what the player reads after pressing "Do you say anything to the ones who come back?"
       spoken on: conversations.topic.work.delightcook.village.respond, button `ask_second_bowl`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.village.ask_second_bowl`: the villager explains. Subject `work.delightcook.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.village.ask_second_bowl/1   [88 chars]
    en  Never. I fill it higher the first time the next day and I say nothing about that either.
    >>  ............................................
    pt  Nunca. Encho mais na primeira vez no dia seguinte e também não digo nada sobre isso.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.village.ask_second_bowl/2   [77 chars]
    en  I ask about their roof, or their knee, or anything that isn't the bowl, %1$s.
    >>  ............................................
    pt  Pergunto do telhado, ou do joelho, ou de qualquer coisa que não a tigela, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Knowing before the mayor does is worth more than the pot."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightcook.village` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.village.say_thanks` — accepted phrasings: "knowing before the mayor does is worth more than the pot"
  - the message must contain one of: `knowing`, `mayor`
  - scored words: `knowing`(1.5), `mayor`(1.0), `before`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.village.respond.say_thanks   [57 chars]
    en  Knowing before the mayor does is worth more than the pot.
    >>  ............................................
    pt  Saber antes do prefeito vale mais que o caldeirão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightcook.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightcook.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Knowing before the mayor does is worth more than the pot."
       spoken on: conversations.topic.work.delightcook.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.village.say_thanks`: the villager accepts. Subject `work.delightcook.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.village.say_thanks/1   [72 chars]
    en  ...It might be. The knowing has never been counted as part of what I do.
    >>  ............................................
    pt  ...Talvez valha. O saber nunca foi contado como parte do que eu faço.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.village.say_thanks/2   [70 chars]
    en  Then somebody ought to ask me, %1$s, and in nineteen years nobody has.
    >>  ............................................
    pt  Então alguém devia me perguntar, %1$s, e em dezenove anos ninguém perguntou.
    >>  ............................................
```


### Button `ask_nineteen` — "Who are the nineteen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightcook.village` · offered only once the villager has actually said `work:delightcook`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightcook.village.ask_nineteen` — accepted phrasings: "who are the nineteen"
  - the message must contain one of: `nineteen`, `regulars`
  - scored words: `nineteen`(1.5), `who`(0.5), `regulars`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.village.respond.ask_nineteen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.village.respond.ask_nineteen   [21 chars]
    en  Who are the nineteen?
    >>  ............................................
    pt  Quem são os dezenove?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightcook.village.ask_nineteen`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightcook.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you make when there's nothing?" | "Eat something."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.village.ask_nineteen
WHO    VILLAGER — what the player reads after pressing "Who are the nineteen?"
       spoken on: conversations.topic.work.delightcook.village.respond, button `ask_nineteen`
       leaves the player on: conversations.topic.work.delightcook.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.village.ask_nineteen`: the villager explains. Subject `work.delightcook.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightcook.village.ask_nineteen/1   [97 chars]
    en  Whoever comes. That's the rule and it's the reason it works and the reason I can't budget for it.
    >>  ............................................
    pt  Quem vier. É a regra e é por isso que funciona e por isso que eu não consigo orçar.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.village.ask_nineteen/2   [76 chars]
    en  Six regulars and thirteen who vary, %1$s. The thirteen are the ones I watch.
    >>  ............................................
    pt  Seis fixos e treze que variam, %1$s. Os treze são os que eu observo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightcook.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightcook.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightcook.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightcook.village.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar pra panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.topic.work.delightcook.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.left`: the villager accepts. Subject `work.delightcook.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightcook.borrowed_recipe.active.respond / leave; conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.blocked.respond / leave; conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond / leave; conversations.scene.work.delightcook.followup / leave; conversations.scene.work.delightcook.long_evenings.blocked.respond / leave; conversations.scene.work.delightcook.long_evenings.succeeded.respond / leave; conversations.topic.work.delightcook.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightcook.borrowed_recipe.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

