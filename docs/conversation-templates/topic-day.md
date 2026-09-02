# Topic: day

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `day` |
| Opened from | question `conversations.cat.chitchat`, button `day` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.chitchat` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `practical_help`, `dismissal`, `humor`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.chitchat`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.day
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.chitchat.day   [30 chars]
    en  How's your day actually going?
    >>  ............................................
    pt  Como está sendo o seu dia, na real?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.day.before_the_light.respond`](#conversations-scene-day-before-the-light-respond)
- [`conversations.scene.day.end_of_it.respond`](#conversations-scene-day-end-of-it-respond)
- [`conversations.scene.day.followup`](#conversations-scene-day-followup)
- [`conversations.topic.day.again.respond`](#conversations-topic-day-again-respond)
- [`conversations.topic.day.good.deflated.followup`](#conversations-topic-day-good-deflated-followup)
- [`conversations.topic.day.good.followup`](#conversations-topic-day-good-followup)
- [`conversations.topic.day.good.respond`](#conversations-topic-day-good-respond)
- [`conversations.topic.day.ordinary.followup`](#conversations-topic-day-ordinary-followup)
- [`conversations.topic.day.ordinary.respond`](#conversations-topic-day-ordinary-respond)
- [`conversations.topic.day.rough.followup`](#conversations-topic-day-rough-followup)
- [`conversations.topic.day.rough.repair`](#conversations-topic-day-rough-repair)
- [`conversations.topic.day.rough.respond`](#conversations-topic-day-rough-respond)
- [`conversations.topic.day.toddler.respond`](#conversations-topic-day-toddler-respond)
- [`conversations.topic.day.young.followup`](#conversations-topic-day-young-followup)
- [`conversations.topic.day.young.respond`](#conversations-topic-day-young-respond)
- [`conversations.topic.day.young.snubbed.followup`](#conversations-topic-day-young-snubbed-followup)

---

## `conversations.scene.day.before_the_light.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.day.before_the_light` — e.g. "Started before it was properly light, which I say every morning and mean about half the time."


```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.day.before_the_light.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.day.before_the_light.respond   [13 chars]
    en  Your morning.
    >>  ............................................
    pt  Sua manhã.
    >>  ............................................
```


### Button `ask_the_first_hour` — "What do you do with the first hour?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `day.before_the_light.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.day.before_the_light.ask_the_first_hour` — accepted phrasings: "what do you do with the first hour"; "what do you do with the first hour"; "how do you spend the early hour"
  - the message must contain one of: `hour`, `early`
  - scored words: `hour`(1.8), `early`(1.8), `first`(0.8), `spend`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light.respond.ask_the_first_hour
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.before_the_light.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.before_the_light.respond.ask_the_first_hour   [35 chars]
    en  What do you do with the first hour?
    >>  ............................................
    pt  O que você faz na primeira hora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `day.early`)_
- Does: session `turn`
- Then opens: `conversations.scene.day.followup`
- …where the player's next choices will be: "I'll let you get on with yours."

```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light.explained
WHO    VILLAGER — what the player reads after pressing "What do you do with the first hour?"
       spoken on: conversations.scene.day.before_the_light.respond, button `ask_the_first_hour`
       leaves the player on: conversations.scene.day.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.before_the_light.open.explained`: the villager explains. Subject `day.early`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:day` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.day.before_the_light.explained/1   [111 chars]
    en  Nothing that needs doing. That is the point of it. The moment I start doing something needed, the hour is over.
    >>  ............................................
    pt  Nada que precise ser feito. É esse o ponto. No instante em que eu faço algo necessário, a hora acabou.
    >>  ............................................
  dialogue.conversations.scene.day.before_the_light.explained/2   [100 chars]
    en  I walk to the same place and stand there. It is not a beautiful place. It is just mine at that hour.
    >>  ............................................
    pt  Caminho até o mesmo lugar e fico parada. Não é um lugar bonito. É só meu naquela hora.
    >>  ............................................
  dialogue.conversations.scene.day.before_the_light.explained/3   [109 chars]
    en  Think about the day badly and then decide it is fine, which takes about forty minutes and never gets quicker.
    >>  ............................................
    pt  Penso mal no dia e depois decido que está tudo bem, o que leva uns quarenta minutos e nunca fica mais rápido.
    >>  ............................................
```


### Button `wish_them_well` — "Make it a good one."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `day.before_the_light.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.day.before_the_light.wish_them_well` — accepted phrasings: "make it a good one"; "make it a good one"; "hope the day treats you well"
  - the message must contain one of: `good`, `hope`, `day`
  - scored words: `good`(1.8), `hope`(1.8), `day`(1.8), `make`(0.8), `one`(0.8), `treats`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light.respond.wish_them_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.before_the_light.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.before_the_light.respond.wish_them_well   [19 chars]
    en  Make it a good one.
    >>  ............................................
    pt  Que seja um bom dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `day.early`)_
- Does: session `turn`
- Then opens: `conversations.scene.day.followup`
- …where the player's next choices will be: "I'll let you get on with yours."

```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light.thanked
WHO    VILLAGER — what the player reads after pressing "Make it a good one."
       spoken on: conversations.scene.day.before_the_light.respond, button `wish_them_well`
       leaves the player on: conversations.scene.day.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.before_the_light.open.thanked`: the villager accepts. Subject `day.early`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:day` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.day.before_the_light.thanked/1   [80 chars]
    en  I intend to. Most of it is decided by about ten, so I shall be brisk until then.
    >>  ............................................
    pt  Pretendo. Quase tudo se decide até as dez, então vou ser rápida até lá.
    >>  ............................................
  dialogue.conversations.scene.day.before_the_light.thanked/2   [98 chars]
    en  You too. And if it goes badly, come and say so, because a day that is complained about is shorter.
    >>  ............................................
    pt  Você também. E se for mal, venha reclamar, porque um dia do qual se reclama fica mais curto.
    >>  ............................................
  dialogue.conversations.scene.day.before_the_light.thanked/3   [90 chars]
    en  That is a kind thing to say at this hour, when nobody has done anything to deserve it yet.
    >>  ............................................
    pt  É uma coisa gentil de se dizer a esta hora, quando ninguém fez nada ainda para merecer.
    >>  ............................................
```


### Button `leave` — "Right you are."

*stance family `exit` · tone `plain` · answers the beat(s) `day.before_the_light.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.before_the_light.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.before_the_light.respond.leave   [14 chars]
    en  Right you are.
    >>  ............................................
    pt  Certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.day.leaving
WHO    VILLAGER — what the player reads after pressing "Right you are."
       spoken on: conversations.scene.day.before_the_light.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.scene.leaving`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.day.end_of_it.respond / leave; conversations.scene.day.followup / leave
```

```text
  dialogue.conversations.scene.day.leaving/1   [31 chars]
    en  Go on, then. Plenty of it left.
    >>  ............................................
    pt  Vá lá, então. Ainda tem bastante dia.
    >>  ............................................
  dialogue.conversations.scene.day.leaving/2   [38 chars]
    en  Right. I have things to be getting to.
    >>  ............................................
    pt  Certo. Tenho coisas a fazer.
    >>  ............................................
  dialogue.conversations.scene.day.leaving/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Vá com cuidado.
    >>  ............................................
```

---


## `conversations.scene.day.end_of_it.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.day.end_of_it` — e.g. "Done, and I could not tell you what happened in the middle of it, which is usually a sign it went well."


```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.day.end_of_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.day.end_of_it.respond   [19 chars]
    en  The day you've had.
    >>  ............................................
    pt  O dia que você teve.
    >>  ............................................
```


### Button `ask_the_best_part` — "What was the best part of it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `day.end_of_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.day.end_of_it.ask_the_best_part` — accepted phrasings: "what was the best part of it"; "what was the best part of it"; "which bit was worth having"
  - the message must contain one of: `best`, `worth`, `bit`
  - scored words: `best`(1.8), `worth`(1.8), `bit`(1.8), `part`(0.8), `which`(0.8), `having`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it.respond.ask_the_best_part
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.end_of_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.end_of_it.respond.ask_the_best_part   [29 chars]
    en  What was the best part of it?
    >>  ............................................
    pt  Qual foi a melhor parte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `day.late`)_
- Does: session `turn`
- Then opens: `conversations.scene.day.followup`
- …where the player's next choices will be: "I'll let you get on with yours."

```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it.answered
WHO    VILLAGER — what the player reads after pressing "What was the best part of it?"
       spoken on: conversations.scene.day.end_of_it.respond, button `ask_the_best_part`
       leaves the player on: conversations.scene.day.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.end_of_it.open.answered`: the villager reminisces. Subject `day.late`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:day` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.day.end_of_it.answered/1   [92 chars]
    en  Somebody laughed at something I said and did not have to. That is enough to carry a Tuesday.
    >>  ............................................
    pt  Alguém riu de algo que eu disse sem precisar rir. Já basta para segurar uma terça-feira.
    >>  ............................................
  dialogue.conversations.scene.day.end_of_it.answered/2   [92 chars]
    en  The walk back. It always is. Nothing happens on it and that is precisely the recommendation.
    >>  ............................................
    pt  A volta para casa. É sempre. Nada acontece nela, e é exatamente essa a recomendação.
    >>  ............................................
  dialogue.conversations.scene.day.end_of_it.answered/3   [127 chars]
    en  There was an hour in the afternoon when everything went right in a row, and I noticed it while it was happening, which is rare.
    >>  ............................................
    pt  Teve uma hora à tarde em que tudo deu certo em sequência, e eu percebi enquanto acontecia, o que é raro.
    >>  ............................................
```


### Button `tell_them_to_rest` — "Get some rest."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `day.end_of_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.day.end_of_it.tell_them_to_rest` — accepted phrasings: "get some rest"; "get some rest"; "you should sleep soon"
  - the message must contain one of: `rest`, `sleep`
  - scored words: `rest`(1.8), `sleep`(1.8), `get`(0.8), `some`(0.8), `should`(0.8), `soon`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it.respond.tell_them_to_rest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.end_of_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.end_of_it.respond.tell_them_to_rest   [14 chars]
    en  Get some rest.
    >>  ............................................
    pt  Vá descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `day.late`)_
- Does: session `turn`
- Then opens: `conversations.scene.day.followup`
- …where the player's next choices will be: "I'll let you get on with yours."

```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it.agreed
WHO    VILLAGER — what the player reads after pressing "Get some rest."
       spoken on: conversations.scene.day.end_of_it.respond, button `tell_them_to_rest`
       leaves the player on: conversations.scene.day.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.end_of_it.open.agreed`: the villager accepts. Subject `day.late`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:day` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.day.end_of_it.agreed/1   [98 chars]
    en  Shortly. I say shortly at this hour every night and it means something between one hour and three.
    >>  ............................................
    pt  Já já. Digo já já a esta hora toda noite e isso significa entre uma e três horas.
    >>  ............................................
  dialogue.conversations.scene.day.end_of_it.agreed/2   [114 chars]
    en  I will. The trouble with the end of a day is that it is the only quiet part, so I spend it being awake on purpose.
    >>  ............................................
    pt  Vou. O problema do fim do dia é que é a única parte tranquila, então eu passo essa parte acordada de propósito.
    >>  ............................................
  dialogue.conversations.scene.day.end_of_it.agreed/3   [94 chars]
    en  You as well. Whatever tomorrow is, it will be easier having slept through the space before it.
    >>  ............................................
    pt  Você também. Seja lá o que for amanhã, vai ser mais fácil tendo dormido no espaço antes dele.
    >>  ............................................
```


### Button `leave` — "Right you are."

*stance family `exit` · tone `plain` · answers the beat(s) `day.end_of_it.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.end_of_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.end_of_it.respond.leave   [14 chars]
    en  Right you are.
    >>  ............................................
    pt  Certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.day.leaving
WHO    VILLAGER — what the player reads after pressing "Right you are."
       spoken on: conversations.scene.day.end_of_it.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.scene.leaving`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.day.before_the_light.respond / leave; conversations.scene.day.followup / leave
```

> Written out in full under **`conversations.scene.day.before_the_light.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.day.followup`

**Reached from 4 route(s):** `conversations.scene.day.before_the_light.respond` / `ask_the_first_hour`; `conversations.scene.day.before_the_light.respond` / `wish_them_well`; `conversations.scene.day.end_of_it.respond` / `ask_the_best_part`; `conversations.scene.day.end_of_it.respond` / `tell_them_to_rest`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.day.before_the_light.explained` — e.g. "Nothing that needs doing. That is the point of it. The moment I start doing something needed, the hour is over."
- `conversations.scene.day.before_the_light.thanked` — e.g. "I intend to. Most of it is decided by about ten, so I shall be brisk until then."
- `conversations.scene.day.end_of_it.agreed` — e.g. "Shortly. I say shortly at this hour every night and it means something between one hour and three."
- `conversations.scene.day.end_of_it.answered` — e.g. "Somebody laughed at something I said and did not have to. That is enough to carry a Tuesday."


```text
POOL   dialogue key: dialogue.conversations.scene.day.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.day.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.day.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `leave` — "I'll let you get on with yours."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:day.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.day.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.day.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.day.followup.leave   [31 chars]
    en  I'll let you get on with yours.
    >>  ............................................
    pt  Vou deixar você seguir com o seu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.day.leaving
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with yours."
       spoken on: conversations.scene.day.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.scene.leaving`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.day.before_the_light.respond / leave; conversations.scene.day.end_of_it.respond / leave
```

> Written out in full under **`conversations.scene.day.before_the_light.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.day.again.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.again` — e.g. "Same day it was an hour ago, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.day.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.again.respond   [22 chars]
    en  You did ask, you know.
    >>  ............................................
    pt  Você já perguntou, sabia.
    >>  ............................................
```


### Button `apologize` — "Sorry — I forgot I'd asked."

*stance family `candor` · tone `gentle` · answers the beat(s) `day.again.to.day.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.again.apologize` — accepted phrasings: "sorry, i forgot i asked"; "i forgot"; "sorry, asked already"; "my mistake"
  - the message must contain one of: `sorry`, `forgot`, `already`
  - scored words: `sorry`(1.5), `forgot`(1.5), `asked`(0.8), `already`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.again.respond.apologize   [27 chars]
    en  Sorry — I forgot I'd asked.
    >>  ............................................
    pt  Desculpa — esqueci que já tinha perguntado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `day.again.apologize`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I forgot I'd asked."
       spoken on: conversations.topic.day.again.respond, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.again.apologize.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.again.apologize/1   [50 chars]
    en  Happens. My days blur together too, if I'm honest.
    >>  ............................................
    pt  Acontece. Meus dias se misturam também, para ser sincero.
    >>  ............................................
  dialogue.conversations.day.again.apologize/2   [63 chars]
    en  No harm done, %1$s. Ask me tomorrow and I'll have new material.
    >>  ............................................
    pt  Sem problema, %1$s. Pergunte amanhã e eu terei material novo.
    >>  ............................................
  dialogue.conversations.day.again.apologize/3   [45 chars]
    en  That's alright. Half of me forgot I answered.
    >>  ............................................
    pt  Tudo bem. Metade de mim esqueceu que respondeu.
    >>  ............................................
```


### Button `press` — "Humour me. How's your day?"

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `day.again.to.day.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.again.press` — accepted phrasings: "humour me"; "tell me anyway"; "go on, again"; "come on"
  - the message must contain one of: `humour`, `anyway`, `again`, `on`
  - scored words: `humour`(1.5), `anyway`(1.2), `again`(1.0), `come`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.day.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.again.respond.press   [26 chars]
    en  Humour me. How's your day?
    >>  ............................................
    pt  Me faz a vontade. Como está seu dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `day.again.press`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — tension +3, respect -1  _(recorded under topic `day.again.press`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.again.press
WHO    VILLAGER — what the player reads after pressing "Humour me. How's your day?"
       spoken on: conversations.topic.day.again.respond, button `press`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.again.press.terminal`: the villager resists. Subject `day.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.again.press/1   [60 chars]
    en  It's the same day, %1$s. It's been the same day for an hour.
    >>  ............................................
    pt  É o mesmo dia, %1$s. Faz uma hora que é o mesmo dia.
    >>  ............................................
  dialogue.conversations.day.again.press/2   [72 chars]
    en  Asking twice doesn't get you a better answer. It gets you a shorter one.
    >>  ............................................
    pt  Perguntar duas vezes não dá uma resposta melhor. Dá uma mais curta.
    >>  ............................................
  dialogue.conversations.day.again.press/3   [44 chars]
    en  ...Fine. It's fine. It was fine before, too.
    >>  ............................................
    pt  ...Tá. Está tudo bem. Já estava antes também.
    >>  ............................................
```


### Button `leave` — "Fair enough. Never mind."

*stance family `exit` · tone `plain` · answers the beat(s) `day.again.to.day.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.again.respond.leave   [24 chars]
    en  Fair enough. Never mind.
    >>  ............................................
    pt  Justo. Deixa para lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair enough. Never mind."
       spoken on: conversations.topic.day.again.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.again.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.again.leave/1   [23 chars]
    en  Aye. Catch me tomorrow.
    >>  ............................................
    pt  Tá. Me pega amanhã.
    >>  ............................................
  dialogue.conversations.day.again.leave/2   [42 chars]
    en  Go on. I'll have something new by evening.
    >>  ............................................
    pt  Pode ir. Terei algo novo até a noite.
    >>  ............................................
  dialogue.conversations.day.again.leave/3   [9 chars]
    en  Quite so.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
```

---


## `conversations.topic.day.good.deflated.followup`

**Reached from 1 route(s):** `conversations.topic.day.good.respond` / `deflate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.good.deflate` — e.g. "...Right. Well. Sorry for having an hour that didn't hurt."


```text
POOL   dialogue key: dialogue.conversations.topic.day.good.deflated.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.good.deflated.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.good.deflated.followup   [42 chars]
    en  Sorry for having an hour that didn't hurt.
    >>  ............................................
    pt  Desculpe por ter tido uma hora que não doeu.
    >>  ............................................
```


### Button `apologize` — "That was mean of me. Have your good day."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `day.good.deflated.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.deflated.apologize` — accepted phrasings: "that was mean of me. have your good day"
  - the message must contain one of: `mean`, `petty`
  - scored words: `mean`(1.5), `petty`(1.5), `have`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.deflated.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.deflated.followup.apologize   [40 chars]
    en  That was mean of me. Have your good day.
    >>  ............................................
    pt  Fui maldoso. Aproveite seu dia bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `day.good.deflated.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.deflated.apologize
WHO    VILLAGER — what the player reads after pressing "That was mean of me. Have your good day."
       spoken on: conversations.topic.day.good.deflated.followup, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.deflated.apologize`: the villager qualifys. Subject `day.good`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.day.good.deflated.apologize/1   [62 chars]
    en  ...I will, thank you. It was a small one and I'd like it back.
    >>  ............................................
    pt  ...Vou, obrigado. Foi pequeno e eu queria ele de volta.
    >>  ............................................
  dialogue.conversations.day.good.deflated.apologize/2   [70 chars]
    en  Mean, aye. But said and taken back inside a minute, %1$s. That's rare.
    >>  ............................................
    pt  Maldoso, sim. Mas dito e retirado em um minuto, %1$s. Isso é raro.
    >>  ............................................
  dialogue.conversations.day.good.deflated.apologize/3   [56 chars]
    en  Noted. Then it's still a good day and we'll say no more.
    >>  ............................................
    pt  Anotado. Então ainda é um dia bom e a gente não fala mais nisso.
    >>  ............................................
```


### Button `explain` — "It's been a worse day on my side of it."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `day.good.deflated.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.deflated.explain` — accepted phrasings: "it's been a worse day on my side of it"
  - the message must contain one of: `worse`, `side`
  - scored words: `worse`(1.5), `side`(1.2), `mine`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.deflated.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.deflated.followup.explain   [39 chars]
    en  It's been a worse day on my side of it.
    >>  ............................................
    pt  Foi um dia pior do meu lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `day.good.deflated.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.deflated.explain
WHO    VILLAGER — what the player reads after pressing "It's been a worse day on my side of it."
       spoken on: conversations.topic.day.good.deflated.followup, button `explain`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.deflated.explain`: the villager qualifys. Subject `day.good`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.day.good.deflated.explain/1   [62 chars]
    en  ...Ah. Then say that first next time and I'll ask about yours.
    >>  ............................................
    pt  ...Ah. Então da próxima diga isso primeiro e eu pergunto do seu.
    >>  ............................................
  dialogue.conversations.day.good.deflated.explain/2   [74 chars]
    en  That'll be it, then. Two people having different days at each other, %1$s.
    >>  ............................................
    pt  Deve ser isso. Duas pessoas tendo dias diferentes um contra o outro, %1$s.
    >>  ............................................
  dialogue.conversations.day.good.deflated.explain/3   [69 chars]
    en  Understood. Sit down and tell me about it, and I'll not mention mine.
    >>  ............................................
    pt  Entendido. Senta e me conta, e eu não menciono o meu.
    >>  ............................................
```


### Button `leave` — "I'll let you have it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `day.good.deflated.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.deflated.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.deflated.followup.leave   [21 chars]
    en  I'll let you have it.
    >>  ............................................
    pt  Vou deixar você aproveitar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.deflated.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you have it."
       spoken on: conversations.topic.day.good.deflated.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.deflated.leave`: the villager accepts. Subject `day.good`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.day.good.deflated.leave/1   [20 chars]
    en  Just so. Off you go.
    >>  ............................................
    pt  Pois é. Pode ir.
    >>  ............................................
  dialogue.conversations.day.good.deflated.leave/2   [18 chars]
    en  You have it, %1$s.
    >>  ............................................
    pt  Você acertou, %1$s.
    >>  ............................................
  dialogue.conversations.day.good.deflated.leave/3   [3 chars]
    en  Mm.
    >>  ............................................
    pt  Mm.
    >>  ............................................
```

---


## `conversations.topic.day.good.followup`

**Reached from 2 route(s):** `conversations.topic.day.good.respond` / `celebrate`; `conversations.topic.day.good.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.good.ask_more` — e.g. "Small things. Cold water, short queue, nobody needed anything urgently. That's the recipe."
- `conversations.day.good.celebrate` — e.g. "Earned it? I'll take that. Nobody says that around here."


```text
POOL   dialogue key: dialogue.conversations.topic.day.good.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.good.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.good.followup   [17 chars]
    en  So that's my day.
    >>  ............................................
    pt  Então é esse o meu dia.
    >>  ............................................
```


### Button `share_own` — "Mine's been decent too, actually."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `day.good.ask_more.to.day.good`, `day.good.celebrate.to.day.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.share_own` — accepted phrasings: "mine's been good too"; "me too"; "same here"; "mine as well"
  - the message must contain one of: `mine`, `too`, `same`, `also`
  - scored words: `mine`(1.5), `too`(1.0), `same`(1.0), `also`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.followup.share_own   [33 chars]
    en  Mine's been decent too, actually.
    >>  ............................................
    pt  O meu também foi bom, na verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.good.share_own`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `day.good.share_own`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.share_own
WHO    VILLAGER — what the player reads after pressing "Mine's been decent too, actually."
       spoken on: conversations.topic.day.good.followup, button `share_own`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.share_own.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.good.share_own/1   [75 chars]
    en  Two of us having a good day. The village won't know what to do with itself.
    >>  ............................................
    pt  Nós dois com um dia bom. A vila não vai saber o que fazer.
    >>  ............................................
  dialogue.conversations.day.good.share_own/2   [55 chars]
    en  Is that right? Good. It's better when it's going round.
    >>  ............................................
    pt  É mesmo? Que bom. É melhor quando está circulando.
    >>  ............................................
  dialogue.conversations.day.good.share_own/3   [44 chars]
    en  Then we should quit while we're ahead, %1$s.
    >>  ............................................
    pt  Então devíamos parar enquanto estamos ganhando, %1$s.
    >>  ............................................
```


### Button `tease` — "Careful — people will think you're happy."

*stance family `humor` · tone `playful` · answers the beat(s) `day.good.ask_more.to.day.good`, `day.good.celebrate.to.day.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.tease` — accepted phrasings: "careful, people will think you're happy"; "don't let it get out"; "you're smiling"; "mind your reputation"
  - the message must contain one of: `careful`, `happy`, `reputation`, `smiling`
  - scored words: `careful`(1.5), `happy`(1.0), `think`(0.6), `reputation`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.followup.tease
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.followup.tease   [41 chars]
    en  Careful — people will think you're happy.
    >>  ............................................
    pt  Cuidado — vão achar que você está feliz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`
- Does: **hearts +1** — decision id `day.good.tease`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `day.good.tease`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.tease.landed
WHO    VILLAGER — what the player reads after pressing "Careful — people will think you're happy."
       spoken on: conversations.topic.day.good.followup, button `tease`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.tease.landed.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.good.tease.landed/1   [50 chars]
    en  Let them think it! I'll deny it under questioning.
    >>  ............................................
    pt  Que pensem! Eu nego sob interrogatório.
    >>  ............................................
  dialogue.conversations.day.good.tease.landed/2   [63 chars]
    en  Happy? Slanderous. I'll have you know I'm merely not miserable.
    >>  ............................................
    pt  Feliz? Caluniador. Fique sabendo que estou apenas não infeliz.
    >>  ............................................
  dialogue.conversations.day.good.tease.landed/3   [41 chars]
    en  Ha! Don't tell anyone. I've a reputation.
    >>  ............................................
    pt  Ha! Não conta para ninguém. Tenho uma reputação.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`
- Does: **hearts -1** — decision id `day.good.tease`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `day.good.tease`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.tease.flat
WHO    VILLAGER — what the player reads after pressing "Careful — people will think you're happy."
       spoken on: conversations.topic.day.good.followup, button `tease`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.tease.flat.terminal`: the villager accepts. Subject `day.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.good.tease.flat/1   [55 chars]
    en  I am allowed one, you know. It doesn't need commentary.
    >>  ............................................
    pt  Eu tenho direito a um, sabia. Não precisa de comentário.
    >>  ............................................
  dialogue.conversations.day.good.tease.flat/2   [58 chars]
    en  Mm. And now I'm thinking about it, which rather spoils it.
    >>  ............................................
    pt  Hm. E agora estou pensando nisso, o que meio que estraga.
    >>  ............................................
  dialogue.conversations.day.good.tease.flat/3   [31 chars]
    en  Must you? It was going so well.
    >>  ............................................
    pt  Precisa mesmo? Estava indo tão bem.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`  _(chance -2000)_
- Does: disposition — warmth +1  _(recorded under topic `day.good.tease`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.tease.polite
WHO    VILLAGER — what the player reads after pressing "Careful — people will think you're happy."
       spoken on: conversations.topic.day.good.followup, button `tease`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.tease.polite.terminal`: the villager accepts. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.good.tease.polite/1   [41 chars]
    en  They'd be shocked, I'm sure. Let them be.
    >>  ............................................
    pt  Ficariam chocados, com certeza. Que fiquem.
    >>  ............................................
  dialogue.conversations.day.good.tease.polite/2   [41 chars]
    en  One day a season. That's the arrangement.
    >>  ............................................
    pt  Um dia por estação. É o combinado.
    >>  ............................................
  dialogue.conversations.day.good.tease.polite/3   [27 chars]
    en  Heh. I'll risk the rumours.
    >>  ............................................
    pt  Rá. Vou arriscar os boatos.
    >>  ............................................
```


### Button `wish_well` — "Hope it holds."

*stance family `encouragement` · tone `plain` · answers the beat(s) `day.good.ask_more.to.day.good`, `day.good.celebrate.to.day.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.wish_well` — accepted phrasings: "hope it holds"; "hope it lasts"; "long may it continue"; "keep it up"
  - the message must contain one of: `hope`, `holds`, `lasts`, `keeps`
  - scored words: `hope`(1.5), `holds`(1.2), `lasts`(1.2), `keep`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.followup.wish_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.followup.wish_well   [14 chars]
    en  Hope it holds.
    >>  ............................................
    pt  Tomara que dure.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.good.wish_well`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `day.good.wish_well`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.wish_well
WHO    VILLAGER — what the player reads after pressing "Hope it holds."
       spoken on: conversations.topic.day.good.followup, button `wish_well`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.wish_well.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.good.wish_well/1   [40 chars]
    en  So do I. Kind of you to say it out loud.
    >>  ............................................
    pt  Eu também. Gentil da sua parte dizer isso em voz alta.
    >>  ............................................
  dialogue.conversations.day.good.wish_well/2   [44 chars]
    en  From your mouth to the weather's ears, %1$s.
    >>  ............................................
    pt  Da sua boca aos ouvidos do tempo, %1$s.
    >>  ............................................
  dialogue.conversations.day.good.wish_well/3   [39 chars]
    en  It won't, but I appreciate the thought.
    >>  ............................................
    pt  Não vai durar, mas agradeço a intenção.
    >>  ............................................
```


### Button `leave` — "I'll let you enjoy it."

*stance family `exit` · tone `plain` · answers the beat(s) `day.good.ask_more.to.day.good`, `day.good.celebrate.to.day.good` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.followup.leave   [22 chars]
    en  I'll let you enjoy it.
    >>  ............................................
    pt  Vou deixar você aproveitar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you enjoy it."
       spoken on: conversations.topic.day.good.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.good.respond / leave
```

```text
  dialogue.conversations.day.good.leave/1   [44 chars]
    en  Aye, off you go. Enjoy yours if you get one.
    >>  ............................................
    pt  Tá, pode ir. Aproveite o seu, se tiver um.
    >>  ............................................
  dialogue.conversations.day.good.leave/2   [31 chars]
    en  Just so. Good day to you, %1$s.
    >>  ............................................
    pt  Exato. Bom dia para você, %1$s.
    >>  ............................................
  dialogue.conversations.day.good.leave/3   [56 chars]
    en  Go on then. Don't let me keep you from the good weather.
    >>  ............................................
    pt  Pode ir. Não deixe eu te prender longe do bom tempo.
    >>  ............................................
```

---


## `conversations.topic.day.good.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.good` — e.g. "Good one, actually. The well water was cold and the queue at the smith was short."


```text
POOL   dialogue key: dialogue.conversations.topic.day.good.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.good.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.good.respond   [21 chars]
    en  It's been a good one.
    >>  ............................................
    pt  Foi um dia bom.
    >>  ............................................
```


### Button `celebrate` — "You've earned a day like that."

*stance family `encouragement` · tone `playful` · answers the beat(s) `day.good.to.day.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.celebrate` — accepted phrasings: "you've earned it"; "you deserve that"; "glad to hear it"; "good for you"
  - the message must contain one of: `earned`, `deserve`, `glad`, `happy`
  - scored words: `earned`(1.5), `deserve`(1.5), `glad`(1.0), `good`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.respond.celebrate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.respond.celebrate   [30 chars]
    en  You've earned a day like that.
    >>  ............................................
    pt  Você merece um dia desses.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.good.celebrate`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `day.good.celebrate`)_
- Then opens: `conversations.topic.day.good.followup`
- …where the player's next choices will be: "Mine's been decent too, actually." | "Careful — people will think you're happy." | "Hope it holds." | "I'll let you enjoy it."

```text
POOL   dialogue key: dialogue.conversations.day.good.celebrate
WHO    VILLAGER — what the player reads after pressing "You've earned a day like that."
       spoken on: conversations.topic.day.good.respond, button `celebrate`
       leaves the player on: conversations.topic.day.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.celebrate.to.day.good`: the villager celebrates. Subject `day.good`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may take almost any stance (11 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.good.celebrate/1   [56 chars]
    en  Earned it? I'll take that. Nobody says that around here.
    >>  ............................................
    pt  Merecer? Vou aceitar essa. Ninguém fala isso por aqui.
    >>  ............................................
  dialogue.conversations.day.good.celebrate/2   [45 chars]
    en  Hah. Don't tell the others, they'll want one.
    >>  ............................................
    pt  Rá. Não conta para os outros, vão querer um também.
    >>  ............................................
  dialogue.conversations.day.good.celebrate/3   [49 chars]
    en  Maybe I have. Feels good to hear it said, anyway.
    >>  ............................................
    pt  Talvez eu mereça. De todo jeito, é bom ouvir isso.
    >>  ............................................
```


### Button `ask_more` — "What made it a good one?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `day.good.to.day.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.ask_more` — accepted phrasings: "what made it good"; "why's that"; "tell me more"; "what happened"
  - the message must contain one of: `made`, `what`, `why`, `more`
  - scored words: `what`(0.6), `made`(1.2), `why`(0.8), `more`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.respond.ask_more   [24 chars]
    en  What made it a good one?
    >>  ............................................
    pt  O que fez ele ser bom?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `day.good.ask_more`)_
- Then opens: `conversations.topic.day.good.followup`
- …where the player's next choices will be: "Mine's been decent too, actually." | "Careful — people will think you're happy." | "Hope it holds." | "I'll let you enjoy it."

```text
POOL   dialogue key: dialogue.conversations.day.good.ask_more
WHO    VILLAGER — what the player reads after pressing "What made it a good one?"
       spoken on: conversations.topic.day.good.respond, button `ask_more`
       leaves the player on: conversations.topic.day.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.ask_more.to.day.good`: the villager explains. Subject `day.good`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.good.ask_more/1   [90 chars]
    en  Small things. Cold water, short queue, nobody needed anything urgently. That's the recipe.
    >>  ............................................
    pt  Coisas pequenas. Água fria, fila curta, ninguém precisando de nada com urgência. É a receita.
    >>  ............................................
  dialogue.conversations.day.good.ask_more/2   [70 chars]
    en  Nothing went wrong. That's the whole of it. It's rarer than it sounds.
    >>  ............................................
    pt  Nada deu errado. É só isso. É mais raro do que parece.
    >>  ............................................
  dialogue.conversations.day.good.ask_more/3   [60 chars]
    en  The bread came out right. You can build a whole day on that.
    >>  ............................................
    pt  O pão saiu bom. Dá para construir um dia inteiro em cima disso.
    >>  ............................................
```


### Button `deflate` — "Must be nice. Some of us work."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `day.good.to.day.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.good.deflate` — accepted phrasings: "must be nice"; "some of us work"; "alright for some"; "lucky you"
  - the message must contain one of: `nice`, `work`, `alright`, `lucky`
  - scored words: `nice`(1.0), `work`(1.2), `some`(0.6), `alright`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.respond.deflate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.respond.deflate   [30 chars]
    en  Must be nice. Some of us work.
    >>  ............................................
    pt  Deve ser bom. Tem gente que trabalha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `day.good.deflate`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `day.good.deflate`)_
- Does: session `turn`
- Then opens: `conversations.topic.day.good.deflated.followup`
- …where the player's next choices will be: "That was mean of me. Have your good day." | "It's been a worse day on my side of it." | "I'll let you have it."

```text
POOL   dialogue key: dialogue.conversations.day.good.deflate
WHO    VILLAGER — what the player reads after pressing "Must be nice. Some of us work."
       spoken on: conversations.topic.day.good.respond, button `deflate`
       leaves the player on: conversations.topic.day.good.deflated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.deflated.open`: the villager hurts. Subject `day.good`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.day.good.deflate/1   [58 chars]
    en  ...Right. Well. Sorry for having an hour that didn't hurt.
    >>  ............................................
    pt  ...Certo. Bom. Desculpa por ter tido uma hora que não doeu.
    >>  ............................................
  dialogue.conversations.day.good.deflate/2   [57 chars]
    en  Everyone works, %1$s. Some of us also have days. Try one.
    >>  ............................................
    pt  Todo mundo trabalha, %1$s. Alguns de nós também têm dias. Experimente um.
    >>  ............................................
  dialogue.conversations.day.good.deflate/3   [43 chars]
    en  That's a way to take the shine off a thing.
    >>  ............................................
    pt  É um jeito de tirar o brilho da coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.day.good.deflate/1
    en  ...I'd been holding on to that one, %1$s.
    >>  ............................................
    pt  ...Eu estava me segurando naquela, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.day.good.deflate/2
    en  Sorry. I know it wasn't much of an hour.
    >>  ............................................
    pt  Desculpe. Eu sei que não era grande coisa como hora.
    >>  ............................................
  anxious.dialogue.conversations.day.good.deflate/3
    en  ...Right. Yes. I'll not go on about it.
    >>  ............................................
    pt  ...Certo. Sim. Não vou insistir.
    >>  ............................................
  athletic.dialogue.conversations.day.good.deflate/1
    en  ...Well. It was a good hour whatever we call it now.
    >>  ............................................
    pt  ...Bom. Foi uma boa hora, chamemos como chamarmos agora.
    >>  ............................................
  athletic.dialogue.conversations.day.good.deflate/2
    en  Aye. There'll be another. There usually is.
    >>  ............................................
    pt  É. Vai ter outra. Costuma ter.
    >>  ............................................
  athletic.dialogue.conversations.day.good.deflate/3
    en  ...Right you are. I'll let it go.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar pra lá.
    >>  ............................................
  confident.dialogue.conversations.day.good.deflate/1
    en  ...Right. Sorry for having an hour that didn't hurt.
    >>  ............................................
    pt  ...Certo. Desculpe por ter tido uma hora que não doeu.
    >>  ............................................
  confident.dialogue.conversations.day.good.deflate/2
    en  Noted. I'll keep the good ones to myself.
    >>  ............................................
    pt  Anotado. Vou guardar as boas pra mim.
    >>  ............................................
  confident.dialogue.conversations.day.good.deflate/3
    en  ...That's the hour finished, then.
    >>  ............................................
    pt  ...Então a hora acabou.
    >>  ............................................
  crabby.dialogue.conversations.day.good.deflate/1
    en  ...Right. Sorry for having an hour that didn't hurt.
    >>  ............................................
    pt  ...Certo. Desculpe por ter tido uma hora que não doeu.
    >>  ............................................
  crabby.dialogue.conversations.day.good.deflate/2
    en  Noted. I'll keep the good ones to myself.
    >>  ............................................
    pt  Anotado. Vou guardar as boas pra mim.
    >>  ............................................
  crabby.dialogue.conversations.day.good.deflate/3
    en  ...That's the hour finished, then.
    >>  ............................................
    pt  ...Então a hora acabou.
    >>  ............................................
  extroverted.dialogue.conversations.day.good.deflate/1
    en  ...Oh. I'd wanted to tell you about it, %1$s.
    >>  ............................................
    pt  ...Ah. Eu queria te contar sobre isso, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.day.good.deflate/2
    en  That's not what I'd hoped you'd say to that one.
    >>  ............................................
    pt  Não é o que eu esperava que você dissesse a essa.
    >>  ............................................
  extroverted.dialogue.conversations.day.good.deflate/3
    en  ...Right. I'll not share the next good hour, then.
    >>  ............................................
    pt  ...Certo. Não vou dividir a próxima hora boa, então.
    >>  ............................................
  flirty.dialogue.conversations.day.good.deflate/1
    en  ...Oh. I'd wanted to tell you about it, %1$s.
    >>  ............................................
    pt  ...Ah. Eu queria te contar sobre isso, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.day.good.deflate/2
    en  That's not what I'd hoped you'd say to that one.
    >>  ............................................
    pt  Não é o que eu esperava que você dissesse a essa.
    >>  ............................................
  flirty.dialogue.conversations.day.good.deflate/3
    en  ...Right. I'll not share the next good hour, then.
    >>  ............................................
    pt  ...Certo. Não vou dividir a próxima hora boa, então.
    >>  ............................................
  friendly.dialogue.conversations.day.good.deflate/1
    en  ...Oh. I'd wanted to tell you about it, %1$s.
    >>  ............................................
    pt  ...Ah. Eu queria te contar sobre isso, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.day.good.deflate/2
    en  That's not what I'd hoped you'd say to that one.
    >>  ............................................
    pt  Não é o que eu esperava que você dissesse a essa.
    >>  ............................................
  friendly.dialogue.conversations.day.good.deflate/3
    en  ...Right. I'll not share the next good hour, then.
    >>  ............................................
    pt  ...Certo. Não vou dividir a próxima hora boa, então.
    >>  ............................................
  gloomy.dialogue.conversations.day.good.deflate/1
    en  ...I'd been holding on to that one, %1$s.
    >>  ............................................
    pt  ...Eu estava me segurando naquela, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.day.good.deflate/2
    en  Sorry. I know it wasn't much of an hour.
    >>  ............................................
    pt  Desculpe. Eu sei que não era grande coisa como hora.
    >>  ............................................
  gloomy.dialogue.conversations.day.good.deflate/3
    en  ...Right. Yes. I'll not go on about it.
    >>  ............................................
    pt  ...Certo. Sim. Não vou insistir.
    >>  ............................................
  greedy.dialogue.conversations.day.good.deflate/1
    en  ...Right. Sorry for having an hour that didn't hurt.
    >>  ............................................
    pt  ...Certo. Desculpe por ter tido uma hora que não doeu.
    >>  ............................................
  greedy.dialogue.conversations.day.good.deflate/2
    en  Noted. I'll keep the good ones to myself.
    >>  ............................................
    pt  Anotado. Vou guardar as boas pra mim.
    >>  ............................................
  greedy.dialogue.conversations.day.good.deflate/3
    en  ...That's the hour finished, then.
    >>  ............................................
    pt  ...Então a hora acabou.
    >>  ............................................
  grumpy.dialogue.conversations.day.good.deflate/1
    en  ...Right. Sorry for having an hour that didn't hurt.
    >>  ............................................
    pt  ...Certo. Desculpe por ter tido uma hora que não doeu.
    >>  ............................................
  grumpy.dialogue.conversations.day.good.deflate/2
    en  Noted. I'll keep the good ones to myself.
    >>  ............................................
    pt  Anotado. Vou guardar as boas pra mim.
    >>  ............................................
  grumpy.dialogue.conversations.day.good.deflate/3
    en  ...That's the hour finished, then.
    >>  ............................................
    pt  ...Então a hora acabou.
    >>  ............................................
  introverted.dialogue.conversations.day.good.deflate/1
    en  ...Mm. All right.
    >>  ............................................
    pt  ...Hum. Está bem.
    >>  ............................................
  introverted.dialogue.conversations.day.good.deflate/2
    en  It was one hour. I'd not made more of it than that.
    >>  ............................................
    pt  Foi uma hora. Eu não estava fazendo mais que isso.
    >>  ............................................
  introverted.dialogue.conversations.day.good.deflate/3
    en  ...Right. Forget I mentioned it.
    >>  ............................................
    pt  ...Certo. Esqueça que eu mencionei.
    >>  ............................................
  lazy.dialogue.conversations.day.good.deflate/1
    en  ...Well. It was a good hour whatever we call it now.
    >>  ............................................
    pt  ...Bom. Foi uma boa hora, chamemos como chamarmos agora.
    >>  ............................................
  lazy.dialogue.conversations.day.good.deflate/2
    en  Aye. There'll be another. There usually is.
    >>  ............................................
    pt  É. Vai ter outra. Costuma ter.
    >>  ............................................
  lazy.dialogue.conversations.day.good.deflate/3
    en  ...Right you are. I'll let it go.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar pra lá.
    >>  ............................................
  odd.dialogue.conversations.day.good.deflate/1
    en  ...Mm. All right.
    >>  ............................................
    pt  ...Hum. Está bem.
    >>  ............................................
  odd.dialogue.conversations.day.good.deflate/2
    en  It was one hour. I'd not made more of it than that.
    >>  ............................................
    pt  Foi uma hora. Eu não estava fazendo mais que isso.
    >>  ............................................
  odd.dialogue.conversations.day.good.deflate/3
    en  ...Right. Forget I mentioned it.
    >>  ............................................
    pt  ...Certo. Esqueça que eu mencionei.
    >>  ............................................
  peaceful.dialogue.conversations.day.good.deflate/1
    en  ...Well. It was a good hour whatever we call it now.
    >>  ............................................
    pt  ...Bom. Foi uma boa hora, chamemos como chamarmos agora.
    >>  ............................................
  peaceful.dialogue.conversations.day.good.deflate/2
    en  Aye. There'll be another. There usually is.
    >>  ............................................
    pt  É. Vai ter outra. Costuma ter.
    >>  ............................................
  peaceful.dialogue.conversations.day.good.deflate/3
    en  ...Right you are. I'll let it go.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar pra lá.
    >>  ............................................
  peppy.dialogue.conversations.day.good.deflate/1
    en  ...And there it goes. Well, it was lovely while it lasted.
    >>  ............................................
    pt  ...E lá se foi. Bom, foi ótimo enquanto durou.
    >>  ............................................
  peppy.dialogue.conversations.day.good.deflate/2
    en  Right! One good hour, thoroughly extinguished. Efficient work.
    >>  ............................................
    pt  Certo! Uma boa hora, completamente extinta. Trabalho eficiente.
    >>  ............................................
  peppy.dialogue.conversations.day.good.deflate/3
    en  ...Ah. Yes. Back to normal, then.
    >>  ............................................
    pt  ...Ah. Sim. De volta ao normal, então.
    >>  ............................................
  playful.dialogue.conversations.day.good.deflate/1
    en  ...And there it goes. Well, it was lovely while it lasted.
    >>  ............................................
    pt  ...E lá se foi. Bom, foi ótimo enquanto durou.
    >>  ............................................
  playful.dialogue.conversations.day.good.deflate/2
    en  Right! One good hour, thoroughly extinguished. Efficient work.
    >>  ............................................
    pt  Certo! Uma boa hora, completamente extinta. Trabalho eficiente.
    >>  ............................................
  playful.dialogue.conversations.day.good.deflate/3
    en  ...Ah. Yes. Back to normal, then.
    >>  ............................................
    pt  ...Ah. Sim. De volta ao normal, então.
    >>  ............................................
  relaxed.dialogue.conversations.day.good.deflate/1
    en  ...Well. It was a good hour whatever we call it now.
    >>  ............................................
    pt  ...Bom. Foi uma boa hora, chamemos como chamarmos agora.
    >>  ............................................
  relaxed.dialogue.conversations.day.good.deflate/2
    en  Aye. There'll be another. There usually is.
    >>  ............................................
    pt  É. Vai ter outra. Costuma ter.
    >>  ............................................
  relaxed.dialogue.conversations.day.good.deflate/3
    en  ...Right you are. I'll let it go.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar pra lá.
    >>  ............................................
  sensitive.dialogue.conversations.day.good.deflate/1
    en  ...I'd been holding on to that one, %1$s.
    >>  ............................................
    pt  ...Eu estava me segurando naquela, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.day.good.deflate/2
    en  Sorry. I know it wasn't much of an hour.
    >>  ............................................
    pt  Desculpe. Eu sei que não era grande coisa como hora.
    >>  ............................................
  sensitive.dialogue.conversations.day.good.deflate/3
    en  ...Right. Yes. I'll not go on about it.
    >>  ............................................
    pt  ...Certo. Sim. Não vou insistir.
    >>  ............................................
  shy.dialogue.conversations.day.good.deflate/1
    en  ...Mm. All right.
    >>  ............................................
    pt  ...Hum. Está bem.
    >>  ............................................
  shy.dialogue.conversations.day.good.deflate/2
    en  It was one hour. I'd not made more of it than that.
    >>  ............................................
    pt  Foi uma hora. Eu não estava fazendo mais que isso.
    >>  ............................................
  shy.dialogue.conversations.day.good.deflate/3
    en  ...Right. Forget I mentioned it.
    >>  ............................................
    pt  ...Certo. Esqueça que eu mencionei.
    >>  ............................................
  upbeat.dialogue.conversations.day.good.deflate/1
    en  ...And there it goes. Well, it was lovely while it lasted.
    >>  ............................................
    pt  ...E lá se foi. Bom, foi ótimo enquanto durou.
    >>  ............................................
  upbeat.dialogue.conversations.day.good.deflate/2
    en  Right! One good hour, thoroughly extinguished. Efficient work.
    >>  ............................................
    pt  Certo! Uma boa hora, completamente extinta. Trabalho eficiente.
    >>  ............................................
  upbeat.dialogue.conversations.day.good.deflate/3
    en  ...Ah. Yes. Back to normal, then.
    >>  ............................................
    pt  ...Ah. Sim. De volta ao normal, então.
    >>  ............................................
  witty.dialogue.conversations.day.good.deflate/1
    en  ...And there it goes. Well, it was lovely while it lasted.
    >>  ............................................
    pt  ...E lá se foi. Bom, foi ótimo enquanto durou.
    >>  ............................................
  witty.dialogue.conversations.day.good.deflate/2
    en  Right! One good hour, thoroughly extinguished. Efficient work.
    >>  ............................................
    pt  Certo! Uma boa hora, completamente extinta. Trabalho eficiente.
    >>  ............................................
  witty.dialogue.conversations.day.good.deflate/3
    en  ...Ah. Yes. Back to normal, then.
    >>  ............................................
    pt  ...Ah. Sim. De volta ao normal, então.
    >>  ............................................
```

</details>


### Button `leave` — "Glad to hear it. I'll get on."

*stance family `exit` · tone `plain` · answers the beat(s) `day.good.to.day.good` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.good.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.good.respond.leave   [29 chars]
    en  Glad to hear it. I'll get on.
    >>  ............................................
    pt  Que bom. Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.good.leave
WHO    VILLAGER — what the player reads after pressing "Glad to hear it. I'll get on."
       spoken on: conversations.topic.day.good.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.good.followup / leave
```

> Written out in full under **`conversations.topic.day.good.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.day.ordinary.followup`

**Reached from 3 route(s):** `conversations.topic.day.ordinary.respond` / `ask_work`; `conversations.topic.day.ordinary.respond` / `share_own_day`; `conversations.topic.day.ordinary.respond` / `hurry`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.ordinary.ask_work` — e.g. "This? Nothing clever. But it wants doing properly, and that takes as long as it takes."
- `conversations.day.ordinary.hurry` — e.g. "...You asked. Suppose I'll get back to it, then."
- `conversations.day.ordinary.share_own_day` — e.g. "Then we're both having the same unremarkable day. Company, of a sort."


```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.ordinary.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.ordinary.followup   [27 chars]
    en  Anyway. It keeps me honest.
    >>  ............................................
    pt  Enfim. Me mantém ocupado.
    >>  ............................................
```


### Button `praise` — "You're good at that."

*stance family `encouragement` · tone `plain` · answers the beat(s) `day.ordinary.ask_work.to.day.ordinary`, `day.ordinary.hurry.to.day.ordinary`, `day.ordinary.share_own_day.to.day.ordinary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.ordinary.praise` — accepted phrasings: "you're good at that"; "that's impressive"; "you do that well"; "you know your craft"
  - the message must contain one of: `good`, `skilled`, `impressive`, `well`
  - scored words: `good`(1.0), `skilled`(1.5), `impressive`(1.5), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.followup.praise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.followup.praise   [20 chars]
    en  You're good at that.
    >>  ............................................
    pt  Você é bom nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.ordinary.praise`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +1  _(recorded under topic `day.ordinary.praise`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.praise
WHO    VILLAGER — what the player reads after pressing "You're good at that."
       spoken on: conversations.topic.day.ordinary.followup, button `praise`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.praise.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.ordinary.praise/1   [58 chars]
    en  ...Well. Not much call for saying so, but I am. Thank you.
    >>  ............................................
    pt  ...Bom. Ninguém costuma dizer isso, mas eu sou. Obrigado.
    >>  ............................................
  dialogue.conversations.day.ordinary.praise/2   [34 chars]
    en  Twenty years of it. You'd hope so.
    >>  ............................................
    pt  Vinte anos nisso. Seria de se esperar.
    >>  ............................................
  dialogue.conversations.day.ordinary.praise/3   [44 chars]
    en  That's landed better than you'd think, %1$s.
    >>  ............................................
    pt  Isso acertou mais fundo do que você imagina, %1$s.
    >>  ............................................
```


### Button `advise` — "You could do that faster, you know."

*stance family `respectful_disagreement` · tone `plain` · answers the beat(s) `day.ordinary.ask_work.to.day.ordinary`, `day.ordinary.hurry.to.day.ordinary`, `day.ordinary.share_own_day.to.day.ordinary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.ordinary.advise` — accepted phrasings: "you could do that faster"; "there's a quicker way"; "you should try"; "why not do it this way"
  - the message must contain one of: `faster`, `quicker`, `should`, `try`
  - scored words: `faster`(1.5), `quicker`(1.5), `should`(0.8), `try`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.followup.advise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.followup.advise   [35 chars]
    en  You could do that faster, you know.
    >>  ............................................
    pt  Dava para fazer isso mais rápido, sabia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `day.ordinary.advise`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `day.ordinary.advise`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.advise.welcome
WHO    VILLAGER — what the player reads after pressing "You could do that faster, you know."
       spoken on: conversations.topic.day.ordinary.followup, button `advise`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.advise.welcome.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.ordinary.advise.welcome/1   [54 chars]
    en  Go on then... huh. That would be faster. Right. Noted.
    >>  ............................................
    pt  Fala aí... hm. Seria mais rápido mesmo. Certo. Anotado.
    >>  ............................................
  dialogue.conversations.day.ordinary.advise.welcome/2   [69 chars]
    en  Say that again? ...No, you're right. I've been doing it the long way.
    >>  ............................................
    pt  Repete? ...Não, você tem razão. Estou fazendo pelo caminho longo.
    >>  ............................................
  dialogue.conversations.day.ordinary.advise.welcome/3   [62 chars]
    en  Blunt. Useful. I'll take blunt and useful over polite any day.
    >>  ............................................
    pt  Direto. Útil. Prefiro direto e útil a educado, qualquer dia.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `day.ordinary.advise`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect -2, tension +3  _(recorded under topic `day.ordinary.advise`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.advise.unwanted
WHO    VILLAGER — what the player reads after pressing "You could do that faster, you know."
       spoken on: conversations.topic.day.ordinary.followup, button `advise`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.advise.unwanted.terminal`: the villager accepts. Subject `day.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.ordinary.advise.unwanted/1   [83 chars]
    en  Could I. Thank you. I'll add that to the list of things I'm apparently doing wrong.
    >>  ............................................
    pt  Ah, dava. Obrigado. Vou somar à lista de coisas que aparentemente faço errado.
    >>  ............................................
  dialogue.conversations.day.ordinary.advise.unwanted/2   [53 chars]
    en  Mm. I'm sure it looks simple from where you're stood.
    >>  ............................................
    pt  Hm. Deve parecer simples daí de onde você está.
    >>  ............................................
  dialogue.conversations.day.ordinary.advise.unwanted/3   [31 chars]
    en  ...Right. I'll bear it in mind.
    >>  ............................................
    pt  ...Certo. Vou levar em consideração.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — respect +1  _(recorded under topic `day.ordinary.advise`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.advise.neutral
WHO    VILLAGER — what the player reads after pressing "You could do that faster, you know."
       spoken on: conversations.topic.day.ordinary.followup, button `advise`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.advise.neutral.terminal`: the villager accepts. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.ordinary.advise.neutral/1   [49 chars]
    en  Maybe. Maybe there's a reason it's done this way.
    >>  ............................................
    pt  Talvez. Talvez exista um motivo para ser feito assim.
    >>  ............................................
  dialogue.conversations.day.ordinary.advise.neutral/2   [63 chars]
    en  Everyone's got a faster way. Mine has the advantage of working.
    >>  ............................................
    pt  Todo mundo tem um jeito mais rápido. O meu tem a vantagem de funcionar.
    >>  ............................................
  dialogue.conversations.day.ordinary.advise.neutral/3   [17 chars]
    en  I'll think on it.
    >>  ............................................
    pt  Vou pensar nisso.
    >>  ............................................
```


### Button `let_them_work` — "I'll stop talking and let you finish."

*stance family `exit` · tone `gentle` · answers the beat(s) `day.ordinary.ask_work.to.day.ordinary`, `day.ordinary.hurry.to.day.ordinary`, `day.ordinary.share_own_day.to.day.ordinary` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.ordinary.let_them_work` — accepted phrasings: "i'll stop talking"; "let you finish"; "i'll be quiet"; "carry on, i'll watch"
  - the message must contain one of: `quiet`, `talking`, `finish`, `stop`
  - scored words: `quiet`(1.2), `stop`(1.0), `talking`(1.2), `finish`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.followup.let_them_work
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.followup.let_them_work   [37 chars]
    en  I'll stop talking and let you finish.
    >>  ............................................
    pt  Vou parar de falar e deixar você terminar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.ordinary.let_them_work`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +2, trust +1  _(recorded under topic `day.ordinary.let_them_work`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.let_them_work
WHO    VILLAGER — what the player reads after pressing "I'll stop talking and let you finish."
       spoken on: conversations.topic.day.ordinary.followup, button `let_them_work`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.let_them_work.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.ordinary.let_them_work/1   [62 chars]
    en  Appreciated. Stay if you like — quiet company is fine company.
    >>  ............................................
    pt  Agradeço. Fique se quiser — companhia calada é boa companhia.
    >>  ............................................
  dialogue.conversations.day.ordinary.let_them_work/2   [52 chars]
    en  That's the politest thing anyone's said to me today.
    >>  ............................................
    pt  É a coisa mais educada que me disseram hoje.
    >>  ............................................
  dialogue.conversations.day.ordinary.let_them_work/3   [55 chars]
    en  So it is. Talk's easier when the hands are busy anyway.
    >>  ............................................
    pt  É assim mesmo. Conversa fica mais fácil com as mãos ocupadas mesmo.
    >>  ............................................
```


### Button `leave` — "I'll get out of your way."

*stance family `exit` · tone `plain` · answers the beat(s) `day.ordinary.ask_work.to.day.ordinary`, `day.ordinary.hurry.to.day.ordinary`, `day.ordinary.share_own_day.to.day.ordinary` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.followup.leave   [25 chars]
    en  I'll get out of your way.
    >>  ............................................
    pt  Vou sair da sua frente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.leave
WHO    VILLAGER — what the player reads after pressing "I'll get out of your way."
       spoken on: conversations.topic.day.ordinary.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.ordinary.respond / leave
```

```text
  dialogue.conversations.day.ordinary.leave/1   [33 chars]
    en  Aye. Mind the puddle by the gate.
    >>  ............................................
    pt  Tá. Cuidado com a poça perto do portão.
    >>  ............................................
  dialogue.conversations.day.ordinary.leave/2   [36 chars]
    en  Off you go. It'll all still be here.
    >>  ............................................
    pt  Pode ir. Vai estar tudo aqui ainda.
    >>  ............................................
  dialogue.conversations.day.ordinary.leave/3   [17 chars]
    en  So you are, %1$s.
    >>  ............................................
    pt  Pois é, %1$s.
    >>  ............................................
```

---


## `conversations.topic.day.ordinary.respond`

**Reached from 2 route(s):** `conversations.cat.chitchat` / `day`; `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.plain` — e.g. "Ordinary. Nothing's broken and nothing's on fire. I'll take ordinary."
- `conversations.day.working` — e.g. "Busy hands today. Ask me again when I've put this down."


```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.ordinary.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.ordinary.respond   [28 chars]
    en  That's about the size of it.
    >>  ............................................
    pt  É basicamente isso.
    >>  ............................................
```


### Button `ask_work` — "What are you working on?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `day.plain.to.day.ordinary`, `day.working.to.day.ordinary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.ordinary.ask_work` — accepted phrasings: "what are you working on"; "what are you doing"; "what's that you're making"; "what are you up to"
  - the message must contain one of: `working`, `doing`, `making`, `busy`
  - scored words: `working`(1.5), `doing`(1.0), `making`(1.2), `what`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.respond.ask_work
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.respond.ask_work   [24 chars]
    en  What are you working on?
    >>  ............................................
    pt  No que você está trabalhando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, familiarity +1  _(recorded under topic `day.ordinary.ask_work`)_
- Then opens: `conversations.topic.day.ordinary.followup`
- …where the player's next choices will be: "You're good at that." | "You could do that faster, you know." | "I'll stop talking and let you finish." | "I'll get out of your way."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.ask_work
WHO    VILLAGER — what the player reads after pressing "What are you working on?"
       spoken on: conversations.topic.day.ordinary.respond, button `ask_work`
       leaves the player on: conversations.topic.day.ordinary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.ask_work.to.day.ordinary`: the villager accepts. Subject `day.ordinary`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.ordinary.ask_work/1   [86 chars]
    en  This? Nothing clever. But it wants doing properly, and that takes as long as it takes.
    >>  ............................................
    pt  Isso? Nada genial. Mas tem que ser bem feito, e isso leva o tempo que leva.
    >>  ............................................
  dialogue.conversations.day.ordinary.ask_work/2   [52 chars]
    en  Same as most days. Which is the point of it, really.
    >>  ............................................
    pt  Igual à maioria dos dias. Que é justamente a ideia.
    >>  ............................................
  dialogue.conversations.day.ordinary.ask_work/3   [54 chars]
    en  Ask me in an hour and I'll have something to show you.
    >>  ............................................
    pt  Me pergunte daqui a uma hora e eu terei algo para mostrar.
    >>  ............................................
```


### Button `share_own_day` — "Mine's been much the same."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `day.plain.to.day.ordinary`, `day.working.to.day.ordinary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.ordinary.share_own_day` — accepted phrasings: "mine's been the same"; "same here"; "much the same"; "mine too"
  - the message must contain one of: `mine`, `same`, `similar`, `too`
  - scored words: `mine`(1.5), `same`(1.2), `similar`(1.0), `too`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.respond.share_own_day
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.respond.share_own_day   [26 chars]
    en  Mine's been much the same.
    >>  ............................................
    pt  O meu foi bem parecido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.ordinary.share_own_day`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `day.ordinary.share_own_day`)_
- Then opens: `conversations.topic.day.ordinary.followup`
- …where the player's next choices will be: "You're good at that." | "You could do that faster, you know." | "I'll stop talking and let you finish." | "I'll get out of your way."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.share_own_day
WHO    VILLAGER — what the player reads after pressing "Mine's been much the same."
       spoken on: conversations.topic.day.ordinary.respond, button `share_own_day`
       leaves the player on: conversations.topic.day.ordinary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.share_own_day.to.day.ordinary`: the villager accepts. Subject `day.ordinary`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.ordinary.share_own_day/1   [69 chars]
    en  Then we're both having the same unremarkable day. Company, of a sort.
    >>  ............................................
    pt  Então nós dois estamos tendo o mesmo dia sem graça. Companhia, de certo modo.
    >>  ............................................
  dialogue.conversations.day.ordinary.share_own_day/2   [41 chars]
    en  Ha. Solidarity in nothing much happening.
    >>  ............................................
    pt  Ha. Solidariedade em não acontecer nada.
    >>  ............................................
  dialogue.conversations.day.ordinary.share_own_day/3   [42 chars]
    en  Good. Days like this are underrated, %1$s.
    >>  ............................................
    pt  Ótimo. Dias assim são subestimados, %1$s.
    >>  ............................................
```


### Button `hurry` — "Don't let me slow you down, then."

*stance family `exit` · tone `plain` · answers the beat(s) `day.plain.to.day.ordinary`, `day.working.to.day.ordinary` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.ordinary.hurry` — accepted phrasings: "don't let me slow you down"; "get on with it then"; "hurry up"; "you're busy, clearly"
  - the message must contain one of: `slow`, `busy`, `quick`, `hurry`
  - scored words: `slow`(1.2), `busy`(1.0), `keep`(0.8), `on`(0.3), `quick`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.respond.hurry
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.respond.hurry   [33 chars]
    en  Don't let me slow you down, then.
    >>  ............................................
    pt  Então não deixa eu te atrasar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `day.ordinary.hurry`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect -3, tension +3  _(recorded under topic `day.ordinary.hurry`)_
- Then opens: `conversations.topic.day.ordinary.followup`
- …where the player's next choices will be: "You're good at that." | "You could do that faster, you know." | "I'll stop talking and let you finish." | "I'll get out of your way."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.hurry
WHO    VILLAGER — what the player reads after pressing "Don't let me slow you down, then."
       spoken on: conversations.topic.day.ordinary.respond, button `hurry`
       leaves the player on: conversations.topic.day.ordinary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.hurry.to.day.ordinary`: the villager accepts. Subject `day.ordinary`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.ordinary.hurry/1   [48 chars]
    en  ...You asked. Suppose I'll get back to it, then.
    >>  ............................................
    pt  ...Você que perguntou. Vou voltar ao trabalho, então.
    >>  ............................................
  dialogue.conversations.day.ordinary.hurry/2   [58 chars]
    en  So be it. Wouldn't want to waste your time with an answer.
    >>  ............................................
    pt  Que seja. Não quero desperdiçar seu tempo com uma resposta.
    >>  ............................................
  dialogue.conversations.day.ordinary.hurry/3   [58 chars]
    en  Mm. Next time just walk past, it's quicker for both of us.
    >>  ............................................
    pt  Hm. Da próxima só passe direto, é mais rápido para nós dois.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · answers the beat(s) `day.plain.to.day.ordinary`, `day.working.to.day.ordinary` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.ordinary.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.ordinary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.ordinary.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.ordinary.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.day.ordinary.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.ordinary.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.ordinary.followup / leave
```

> Written out in full under **`conversations.topic.day.ordinary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.day.rough.followup`

**Reached from 3 route(s):** `conversations.topic.day.rough.respond` / `empathize`; `conversations.topic.day.rough.respond` / `empathize`; `conversations.topic.day.rough.respond` / `ask`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.rough.ask` — e.g. "The short version? Everything that could tip over, tipped over. In order."
- `conversations.day.rough.empathize` — e.g. "It is, a bit. Nice of you to notice instead of telling me it could be worse."
- `conversations.day.rough.tense` — e.g. "...Mm. Thank you. It lands a bit flat coming from you just now, but thank you."


```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.rough.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.rough.followup   [15 chars]
    en  So there it is.
    >>  ............................................
    pt  Então é isso.
    >>  ............................................
```


### Button `offer_help` — "Let me take something off your hands."

*stance family `practical_help` · tone `plain` · answers the beat(s) `day.rough.ask.to.day.rough`, `day.rough.empathize.to.day.rough`, `day.rough.tense.to.day.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.offer_help` — accepted phrasings: "let me help"; "give you a hand"; "can i help"; "let me take that"; "i'll help"
  - the message must contain one of: `help`, `hand`, `carry`, `take`
  - scored words: `help`(1.5), `hand`(1.2), `carry`(0.8), `do`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.followup.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.followup.offer_help   [37 chars]
    en  Let me take something off your hands.
    >>  ............................................
    pt  Deixa eu tirar alguma coisa das suas costas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when today's affection ledger, axis positive >= 8
- Does: disposition — warmth +2, respect +3  _(recorded under topic `day.rough.offer_help`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.offer_help.spent
WHO    VILLAGER — what the player reads after pressing "Let me take something off your hands."
       spoken on: conversations.topic.day.rough.followup, button `offer_help`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.offer_help.spent.terminal`: the villager accepts. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.offer_help.spent/1   [76 chars]
    en  You've done enough for one day. Sit down and let it be somebody else's turn.
    >>  ............................................
    pt  Você já fez bastante por hoje. Sente aí e deixe ser a vez de outra pessoa.
    >>  ............................................
  dialogue.conversations.day.rough.offer_help.spent/2   [73 chars]
    en  Another time. You've been at it since morning, %1$s — I've been watching.
    >>  ............................................
    pt  Outra hora. Você está nisso desde de manhã, %1$s — eu reparei.
    >>  ............................................
  dialogue.conversations.day.rough.offer_help.spent/3   [82 chars]
    en  No. Kind of you, but there's a limit to how much one person should carry in a day.
    >>  ............................................
    pt  Não. Gentil da sua parte, mas tem um limite para o quanto uma pessoa carrega num dia.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when today's affection ledger, axis positive >= 8  _(chance -2000)_
- Does: **hearts +1** — decision id `day.rough.offer_help`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +3  _(recorded under topic `day.rough.offer_help`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.offer_help
WHO    VILLAGER — what the player reads after pressing "Let me take something off your hands."
       spoken on: conversations.topic.day.rough.followup, button `offer_help`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.offer_help.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.offer_help/1   [79 chars]
    en  You mean that? ...Alright. That's the first useful thing that's happened today.
    >>  ............................................
    pt  Você está falando sério? ...Certo. É a primeira coisa útil que acontece hoje.
    >>  ............................................
  dialogue.conversations.day.rough.offer_help/2   [45 chars]
    en  Hands, not sympathy. You're a rare one, %1$s.
    >>  ............................................
    pt  Mão na massa, não pena. Você é raro, %1$s.
    >>  ............................................
  dialogue.conversations.day.rough.offer_help/3   [63 chars]
    en  I'll take you up on that. Don't offer twice unless you mean it.
    >>  ............................................
    pt  Vou aceitar. Não ofereça duas vezes se não for sério.
    >>  ............................................
```


### Button `lighten` — "It sounds like the day was against you personally."

*stance family `humor` · tone `playful` · answers the beat(s) `day.rough.ask.to.day.rough`, `day.rough.empathize.to.day.rough`, `day.rough.tense.to.day.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.lighten` — accepted phrasings: "the cat won"; "at least it's funny"; "you have to laugh"; "that's a bit funny"
  - the message must contain one of: `cat`, `won`, `funny`, `laugh`, `joke`
  - scored words: `cat`(1.5), `won`(1.2), `funny`(1.0), `laugh`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.followup.lighten
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.followup.lighten   [50 chars]
    en  It sounds like the day was against you personally.
    >>  ............................................
    pt  Parece que o dia tinha algo contra você, pessoalmente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `day.lighten` lands on tier **crit** (axis warmth, difficulty 25, stance humor)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `day.lighten.crit`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +6, tension -4  _(recorded under topic `day.lighten`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.lighten.crit
WHO    VILLAGER — what the player reads after pressing "It sounds like the day was against you personally."
       spoken on: conversations.topic.day.rough.followup, button `lighten`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.lighten.crit.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.lighten.crit/1   [133 chars]
    en  It WAS. Personally, deliberately, all day — and you're the first to say it out loud. Right. I'm laughing now and the day can go hang.
    >>  ............................................
    pt  TINHA mesmo. Pessoalmente, de propósito, o dia inteiro — e você foi a primeira pessoa a dizer isso em voz alta. Pronto. Agora estou rindo e o dia que se dane.
    >>  ............................................
  dialogue.conversations.day.lighten.crit/2   [111 chars]
    en  ...You've gone and fixed it. One sentence. I was going to carry that mood till Thursday, %1$s, and now I can't.
    >>  ............................................
    pt  ...Você foi lá e consertou. Uma frase. Eu ia carregar esse humor até quinta, %1$s, e agora não consigo.
    >>  ............................................
```


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `day.lighten` lands on tier **success** (axis warmth, difficulty 25, stance humor)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `day.lighten.success`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +4, tension -2  _(recorded under topic `day.lighten`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.lighten.landed
WHO    VILLAGER — what the player reads after pressing "It sounds like the day was against you personally."
       spoken on: conversations.topic.day.rough.followup, button `lighten`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.lighten.landed.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.lighten.landed/1   [85 chars]
    en  Ha! It WAS. All day, personally, deliberately. Right — I feel better and I resent it.
    >>  ............................................
    pt  Ha! TINHA mesmo. O dia inteiro, pessoalmente, de propósito. Pronto — melhorei e estou irritado com isso.
    >>  ............................................
  dialogue.conversations.day.rough.lighten.landed/2   [74 chars]
    en  You're terrible. ...That's the first time I've laughed since this morning.
    >>  ............................................
    pt  Você é terrível. ...É a primeira vez que rio desde cedo.
    >>  ............................................
  dialogue.conversations.day.rough.lighten.landed/3   [35 chars]
    en  Ha. Alright. Alright. Point to you.
    >>  ............................................
    pt  Ha. Tá bom. Tá bom. Ponto para você.
    >>  ............................................
```


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `day.lighten` lands on tier **partial** (axis warmth, difficulty 25, stance humor)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `day.lighten.partial`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +1  _(recorded under topic `day.lighten`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.lighten.polite
WHO    VILLAGER — what the player reads after pressing "It sounds like the day was against you personally."
       spoken on: conversations.topic.day.rough.followup, button `lighten`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.lighten.polite.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.lighten.polite/1   [47 chars]
    en  Heh. Personally. Aye, that's about how it felt.
    >>  ............................................
    pt  Rê. Pessoalmente. É, foi mais ou menos essa a sensação.
    >>  ............................................
  dialogue.conversations.day.rough.lighten.polite/2   [33 chars]
    en  That's about the size of it, yes.
    >>  ............................................
    pt  É mais ou menos isso, sim.
    >>  ............................................
  dialogue.conversations.day.rough.lighten.polite/3   [55 chars]
    en  Suppose you have to laugh. Or you'd do the other thing.
    >>  ............................................
    pt  Acho que a gente tem que rir. Senão faz a outra coisa.
    >>  ............................................
```


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `day.lighten` lands on tier **rebuff** (axis warmth, difficulty 25, stance humor)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -1** — decision id `day.lighten.rebuff`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +3  _(recorded under topic `day.lighten`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.lighten.flat
WHO    VILLAGER — what the player reads after pressing "It sounds like the day was against you personally."
       spoken on: conversations.topic.day.rough.followup, button `lighten`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.lighten.flat.terminal`: the villager accepts. Subject `day.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.lighten.flat/1   [48 chars]
    en  ...I'm sure that's funny somewhere. Not in here.
    >>  ............................................
    pt  ...Deve ser engraçado em algum lugar. Aqui dentro não é.
    >>  ............................................
  dialogue.conversations.day.rough.lighten.flat/2   [57 chars]
    en  Please don't. Not today. Today I'd rather it just be bad.
    >>  ............................................
    pt  Por favor, não. Hoje não. Hoje eu prefiro que seja só ruim mesmo.
    >>  ............................................
  dialogue.conversations.day.rough.lighten.flat/3   [58 chars]
    en  Mm. Very clever, %1$s. I'm going to go back to my day now.
    >>  ............................................
    pt  Hm. Muito espirituoso, %1$s. Vou voltar para o meu dia agora.
    >>  ............................................
```


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +1** — decision id `day.lighten`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `day.lighten`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.lighten.plain
WHO    VILLAGER — what the player reads after pressing "It sounds like the day was against you personally."
       spoken on: conversations.topic.day.rough.followup, button `lighten`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.lighten.plain.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.lighten.plain/1   [47 chars]
    en  Heh. It does rather feel like that, doesn't it.
    >>  ............................................
    pt  Rê. Parece bem isso mesmo, né.
    >>  ............................................
  dialogue.conversations.day.lighten.plain/2   [50 chars]
    en  Mm. Personally, aye. That's the word for it, %1$s.
    >>  ............................................
    pt  Hm. Pessoalmente, é. É essa a palavra, %1$s.
    >>  ............................................
  dialogue.conversations.day.lighten.plain/3   [55 chars]
    en  Suppose you have to laugh. Or you'd do the other thing.
    >>  ............................................
    pt  Acho que a gente tem que rir. Senão faz a outra coisa.
    >>  ............................................
```


### Button `let_be` — "You don't have to make it into anything."

*stance family `restraint` · tone `gentle` · answers the beat(s) `day.rough.ask.to.day.rough`, `day.rough.empathize.to.day.rough`, `day.rough.tense.to.day.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.let_be` — accepted phrasings: "it's okay to have a bad day"; "you don't have to explain"; "that's fine"; "you don't need to make it into anything"; "it can just be a bad day"
  - the message must contain one of: `fine`, `okay`, `allowed`, `have`, `need`
  - scored words: `have`(0.4), `fine`(1.0), `okay`(1.0), `allowed`(1.2), `just`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.followup.let_be
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.followup.let_be   [40 chars]
    en  You don't have to make it into anything.
    >>  ............................................
    pt  Você não precisa transformar isso em nada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.rough.let_be`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +1  _(recorded under topic `day.rough.let_be`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.let_be
WHO    VILLAGER — what the player reads after pressing "You don't have to make it into anything."
       spoken on: conversations.topic.day.rough.followup, button `let_be`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.let_be.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.let_be/1   [48 chars]
    en  ...No. I don't, do I. That's a relief, actually.
    >>  ............................................
    pt  ...Não. Não preciso mesmo, né. Isso é um alívio, na verdade.
    >>  ............................................
  dialogue.conversations.day.rough.let_be/2   [63 chars]
    en  Everyone wants to make it into a lesson. You didn't. Thank you.
    >>  ............................................
    pt  Todo mundo quer transformar em lição. Você não. Obrigado.
    >>  ............................................
  dialogue.conversations.day.rough.let_be/3   [59 chars]
    en  It can just be a bad day. Feels strange to be allowed that.
    >>  ............................................
    pt  Pode ser só um dia ruim. É estranho ter permissão para isso.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · answers the beat(s) `day.rough.ask.to.day.rough`, `day.rough.empathize.to.day.rough`, `day.rough.tense.to.day.rough` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.followup.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.day.rough.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.rough.repair / leave; conversations.topic.day.rough.respond / leave
```

```text
  dialogue.conversations.day.rough.leave/1   [29 chars]
    en  Go on, then. The day'll keep.
    >>  ............................................
    pt  Pode ir. O dia continua aqui.
    >>  ............................................
  dialogue.conversations.day.rough.leave/2   [27 chars]
    en  Aye. Mind how you go, %1$s.
    >>  ............................................
    pt  Tá bom. Se cuida, %1$s.
    >>  ............................................
  dialogue.conversations.day.rough.leave/3   [49 chars]
    en  Fair enough. I'll be here, being tired at things.
    >>  ............................................
    pt  Justo. Vou ficar por aqui, cansado das coisas.
    >>  ............................................
```

---


## `conversations.topic.day.rough.repair`

**Reached from 1 route(s):** `conversations.topic.day.rough.respond` / `brush_off`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.rough.brush_off` — e.g. "...Right. Everyone does. Thanks for that."


```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.repair
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.rough.repair
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.rough.repair   [13 chars]
    en  Well. Anyway.
    >>  ............................................
    pt  Bom. Enfim.
    >>  ............................................
```


### Button `apologize` — "That came out wrong. I'm sorry."

*stance family `candor` · tone `gentle` · answers the beat(s) `day.rough.brush_off.to.day.rough.repair`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.apologize` — accepted phrasings: "that came out wrong"; "i'm sorry"; "i didn't mean that"; "sorry, that was harsh"
  - the message must contain one of: `sorry`, `apologise`, `meant`, `wrong`
  - scored words: `sorry`(1.5), `apologise`(1.5), `wrong`(0.8), `meant`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.repair.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.repair
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.repair.apologize   [31 chars]
    en  That came out wrong. I'm sorry.
    >>  ............................................
    pt  Isso saiu errado. Me desculpa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4, respect +1  _(recorded under topic `day.rough.apologize`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.apologize
WHO    VILLAGER — what the player reads after pressing "That came out wrong. I'm sorry."
       spoken on: conversations.topic.day.rough.repair, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.apologize.terminal`: the villager accepts. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.apologize/1   [56 chars]
    en  ...Alright. It did come out wrong. We'll leave it there.
    >>  ............................................
    pt  ...Tudo bem. Saiu errado mesmo. Vamos deixar por isso.
    >>  ............................................
  dialogue.conversations.day.rough.apologize/2   [41 chars]
    en  Accepted. Don't make a habit of it, %1$s.
    >>  ............................................
    pt  Aceito. Não faça disso um hábito, %1$s.
    >>  ............................................
  dialogue.conversations.day.rough.apologize/3   [56 chars]
    en  Mm. Thank you for saying so. It's more than most manage.
    >>  ............................................
    pt  Hm. Obrigado por dizer. É mais do que a maioria consegue.
    >>  ............................................
```


### Button `double_down` — "I'm not going to pretend it's a tragedy."

*stance family `candor` · tone `blunt` · answers the beat(s) `day.rough.brush_off.to.day.rough.repair`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.double_down` — accepted phrasings: "it's not a tragedy"; "you're overreacting"; "stop being dramatic"; "i won't pretend it's a tragedy"
  - the message must contain one of: `tragedy`, `drama`, `overreacting`, `pretend`
  - scored words: `tragedy`(1.5), `drama`(1.2), `overreacting`(1.5), `pretend`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.repair.double_down
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.repair
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.repair.double_down   [40 chars]
    en  I'm not going to pretend it's a tragedy.
    >>  ............................................
    pt  Não vou fingir que é uma tragédia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `day.rough.double_down`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +6, trust -2  _(recorded under topic `day.rough.double_down`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.double_down
WHO    VILLAGER — what the player reads after pressing "I'm not going to pretend it's a tragedy."
       spoken on: conversations.topic.day.rough.repair, button `double_down`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.double_down.terminal`: the villager accepts. Subject `day.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.rough.double_down/1   [77 chars]
    en  No. It's not a tragedy. It's just mine, and you've made it heavier. Good day.
    >>  ............................................
    pt  Não. Não é uma tragédia. É só minha, e você a deixou mais pesada. Bom dia.
    >>  ............................................
  dialogue.conversations.day.rough.double_down/2   [70 chars]
    en  Then keep it to yourself next time. I've plenty to be getting on with.
    >>  ............................................
    pt  Então guarde para você da próxima. Tenho bastante o que fazer.
    >>  ............................................
  dialogue.conversations.day.rough.double_down/3   [40 chars]
    en  Good. I'll remember you said that, %1$s.
    >>  ............................................
    pt  Bom. Vou lembrar que você disse isso, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `day.rough.brush_off.to.day.rough.repair` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.repair.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.repair
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.repair.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Já vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.day.rough.repair, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.rough.followup / leave; conversations.topic.day.rough.respond / leave
```

> Written out in full under **`conversations.topic.day.rough.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.day.rough.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.rough` — e.g. "Long. My back aches and the cat knocked the stew off the table."


```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.rough.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.rough.respond   [42 chars]
    en  That's the shape of the day, more or less.
    >>  ............................................
    pt  É mais ou menos essa a cara do dia.
    >>  ............................................
```


### Button `empathize` — "That sounds like a lot to carry."

*stance family `empathy` · tone `gentle` · answers the beat(s) `day.rough.to.day.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.empathize` — accepted phrasings: "that sounds hard"; "sounds rough"; "i'm sorry"; "that's a lot"; "sounds heavy"; "that sounds awful"
  - the message must contain one of: `sorry`, `rough`, `heavy`, `hard`, `awful`, `rubbish`
  - scored words: `sorry`(1.2), `rough`(1.0), `heavy`(1.2), `hard`(1.0), `sounds`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.respond.empathize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.respond.empathize   [32 chars]
    en  That sounds like a lot to carry.
    >>  ............................................
    pt  Parece pesado de carregar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when disposition tension >= 30
- Fires when: RULED OUT when the `dispositions` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `day.rough.empathize`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `day.rough.empathize`)_
- Then opens: `conversations.topic.day.rough.followup`
- …where the player's next choices will be: "Let me take something off your hands." | "It sounds like the day was against you personally." | "You don't have to make it into anything." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.day.rough.tense
WHO    VILLAGER — what the player reads after pressing "That sounds like a lot to carry."
       spoken on: conversations.topic.day.rough.respond, button `empathize`
       leaves the player on: conversations.topic.day.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.tense.to.day.rough`: the villager accepts. Subject `day.rough`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.rough.tense/1   [78 chars]
    en  ...Mm. Thank you. It lands a bit flat coming from you just now, but thank you.
    >>  ............................................
    pt  ...Hm. Obrigado. Vindo de você agora soa um pouco vazio, mas obrigado.
    >>  ............................................
  dialogue.conversations.day.rough.tense/2   [81 chars]
    en  True enough. We're not quite square, you and I, but I'll take the sympathy, %1$s.
    >>  ............................................
    pt  Bem verdade. A gente ainda não está quite, você e eu, mas aceito a solidariedade, %1$s.
    >>  ............................................
  dialogue.conversations.day.rough.tense/3   [71 chars]
    en  That's kind. There's still a thing between us, mind. But that was kind.
    >>  ............................................
    pt  Isso é gentil. Ainda tem uma coisa entre nós, veja bem. Mas foi gentil.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when disposition tension >= 30  _(chance -2000)_
- Does: **hearts +1** — decision id `day.rough.empathize`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `day.rough.empathize`)_
- Then opens: `conversations.topic.day.rough.followup`
- …where the player's next choices will be: "Let me take something off your hands." | "It sounds like the day was against you personally." | "You don't have to make it into anything." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.day.rough.empathize
WHO    VILLAGER — what the player reads after pressing "That sounds like a lot to carry."
       spoken on: conversations.topic.day.rough.respond, button `empathize`
       leaves the player on: conversations.topic.day.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.empathize.to.day.rough`: the villager accepts. Subject `day.rough`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.rough.empathize/1   [76 chars]
    en  It is, a bit. Nice of you to notice instead of telling me it could be worse.
    >>  ............................................
    pt  É, um pouco. Bom você notar em vez de dizer que podia ser pior.
    >>  ............................................
  dialogue.conversations.day.rough.empathize/2   [77 chars]
    en  Some days are just heavy. Thank you for not trying to fix it in one sentence.
    >>  ............................................
    pt  Tem dia que é pesado e pronto. Obrigado por não tentar resolver numa frase.
    >>  ............................................
  dialogue.conversations.day.rough.empathize/3   [60 chars]
    en  That's all it needed, honestly. Somebody saying it out loud.
    >>  ............................................
    pt  Era só isso que faltava, sinceramente. Alguém dizendo em voz alta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.day.rough.empathize
    en  It is. I keep waiting for someone to tell me I'm making a fuss, and you didn't, so — thank you.
    >>  ............................................
    pt  É sim. Fico esperando alguém dizer que estou exagerando, e você não disse, então — obrigado.
    >>  ............................................
  athletic.dialogue.conversations.day.rough.empathize
    en  It is. Most people tell you to walk it off. You didn't, and that's the better instinct.
    >>  ............................................
    pt  É. A maioria manda andar que passa. Você não mandou, e esse é o instinto melhor.
    >>  ............................................
  confident.dialogue.conversations.day.rough.empathize
    en  It is. And you said so plainly instead of dressing it up, which I'll remember.
    >>  ............................................
    pt  É. E você disse na lata em vez de enfeitar, o que eu vou lembrar.
    >>  ............................................
  crabby.dialogue.conversations.day.rough.empathize
    en  It is. And no, I don't want it fixed, and yes, I noticed you didn't try.
    >>  ............................................
    pt  É. E não, eu não quero que resolvam, e sim, eu reparei que você não tentou.
    >>  ............................................
  extroverted.dialogue.conversations.day.rough.empathize
    en  It is! And now I've said it out loud to somebody it's already half the size. That's the trick.
    >>  ............................................
    pt  É! E agora que eu falei em voz alta para alguém já diminuiu pela metade. É esse o truque.
    >>  ............................................
  flirty.dialogue.conversations.day.rough.empathize
    en  It is. And you noticed, which is more than the rest of this village manages on a good day.
    >>  ............................................
    pt  É. E você reparou, o que é mais do que esta vila inteira consegue num dia bom.
    >>  ............................................
  friendly.dialogue.conversations.day.rough.empathize
    en  It is, a bit. It's kind of you to just let it be hard without trying to mend it.
    >>  ............................................
    pt  É, um pouco. É gentil da sua parte deixar ser difícil sem tentar consertar.
    >>  ............................................
  gloomy.dialogue.conversations.day.rough.empathize
    en  It is. It usually is. You not pretending otherwise is the only useful thing anyone's done today.
    >>  ............................................
    pt  É. Geralmente é. Você não fingir o contrário é a única coisa útil que aconteceu hoje.
    >>  ............................................
  greedy.dialogue.conversations.day.rough.empathize
    en  It is. And you didn't try to sell me a remedy for it, which puts you ahead of most.
    >>  ............................................
    pt  É. E você não tentou me vender um remédio para isso, o que já te põe à frente da maioria.
    >>  ............................................
  grumpy.dialogue.conversations.day.rough.empathize
    en  It is. Don't make a thing of it. ...But aye, you noticed, and that's more than most.
    >>  ............................................
    pt  É. Não faça disso um caso. ...Mas é, você reparou, e isso é mais do que a maioria.
    >>  ............................................
  introverted.dialogue.conversations.day.rough.empathize
    en  It is. And you let it sit there without needing me to explain it. That helped more than talking would.
    >>  ............................................
    pt  É. E você deixou ficar ali sem exigir que eu explicasse. Isso ajudou mais do que falar ajudaria.
    >>  ............................................
  lazy.dialogue.conversations.day.rough.empathize
    en  It is. And you're not suggesting I do something about it, which is the kindest possible response.
    >>  ............................................
    pt  É. E você não está sugerindo que eu faça algo a respeito, o que é a resposta mais gentil possível.
    >>  ............................................
  odd.dialogue.conversations.day.rough.empathize
    en  It is. Days have a texture and today's is wrong, and you're the first to accept that as a sentence.
    >>  ............................................
    pt  É. Dias têm textura e a de hoje está errada, e você é o primeiro a aceitar isso como frase.
    >>  ............................................
  peaceful.dialogue.conversations.day.rough.empathize
    en  It is. You've a way of letting a thing be difficult without needing to solve it. That's a gift.
    >>  ............................................
    pt  É. Você tem um jeito de deixar uma coisa ser difícil sem precisar resolver. Isso é um dom.
    >>  ............................................
  peppy.dialogue.conversations.day.rough.empathize
    en  It IS! And you said so! Everyone keeps trying to cheer me up and honestly this is much better.
    >>  ............................................
    pt  É SIM! E você falou! Todo mundo tenta me animar e sinceramente isso aqui é bem melhor.
    >>  ............................................
  playful.dialogue.conversations.day.rough.empathize
    en  It is. Though you've made it about twelve per cent funnier just by taking it seriously. Odd, that.
    >>  ............................................
    pt  É. Mas você deixou uns doze por cento mais engraçado só por levar a sério. Curioso, isso.
    >>  ............................................
  relaxed.dialogue.conversations.day.rough.empathize
    en  It is. No sense pretending otherwise. Nice of you to let it just be a bad day.
    >>  ............................................
    pt  É. Não adianta fingir o contrário. Bom você deixar ser só um dia ruim.
    >>  ............................................
  sensitive.dialogue.conversations.day.rough.empathize
    en  It is. And you didn't tell me it could be worse, which people always do, and it never helps.
    >>  ............................................
    pt  É. E você não disse que podia ser pior, coisa que todo mundo diz, e que nunca ajuda.
    >>  ............................................
  shy.dialogue.conversations.day.rough.empathize
    en  ...It is. Thank you for not making me explain it.
    >>  ............................................
    pt  ...É. Obrigado por não me fazer explicar.
    >>  ............................................
  upbeat.dialogue.conversations.day.rough.empathize
    en  It is! But it'll pass, and you letting it be bad for now is what makes that true.
    >>  ............................................
    pt  É! Mas vai passar, e você deixar ser ruim por agora é o que torna isso verdade.
    >>  ............................................
  witty.dialogue.conversations.day.rough.empathize
    en  It is. And you resisted 'could be worse', which shows unusual restraint and excellent instincts.
    >>  ............................................
    pt  É. E você resistiu ao 'podia ser pior', o que mostra contenção incomum e ótimos instintos.
    >>  ............................................
```

</details>


### Button `ask` — "What happened?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `day.rough.to.day.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.ask` — accepted phrasings: "what happened"; "what went wrong"; "what's wrong"; "tell me what happened"; "how come"
  - the message must contain one of: `happened`, `wrong`, `why`, `tell`
  - scored words: `happened`(1.5), `what`(0.6), `wrong`(1.2), `why`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.respond.ask   [14 chars]
    en  What happened?
    >>  ............................................
    pt  O que aconteceu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +2, familiarity +1  _(recorded under topic `day.rough.ask`)_
- Then opens: `conversations.topic.day.rough.followup`
- …where the player's next choices will be: "Let me take something off your hands." | "It sounds like the day was against you personally." | "You don't have to make it into anything." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.day.rough.ask
WHO    VILLAGER — what the player reads after pressing "What happened?"
       spoken on: conversations.topic.day.rough.respond, button `ask`
       leaves the player on: conversations.topic.day.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.ask.to.day.rough`: the villager asks. Subject `day.rough`, polarity `positive`, invites followup, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.rough.ask/1   [73 chars]
    en  The short version? Everything that could tip over, tipped over. In order.
    >>  ............................................
    pt  Versão curta? Tudo que podia tombar, tombou. Em ordem.
    >>  ............................................
  dialogue.conversations.day.rough.ask/2   [78 chars]
    en  Nothing dramatic. Just a hundred small things, all pulling the same direction.
    >>  ............................................
    pt  Nada dramático. Só cem coisinhas puxando todas para o mesmo lado.
    >>  ............................................
  dialogue.conversations.day.rough.ask/3   [72 chars]
    en  It started with one small thing going wrong and got creative from there.
    >>  ............................................
    pt  Começou com uma coisinha dando errado e foi ficando criativo.
    >>  ............................................
```


### Button `brush_off` — "Everyone has days like that."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `day.rough.to.day.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.rough.brush_off` — accepted phrasings: "everyone has bad days"; "could be worse"; "that is just life"; "that's life"; "everyone has days like that"
  - the message must contain one of: `everyone`, `worse`, `whatever`, `life`
  - scored words: `everyone`(1.5), `worse`(1.2), `whatever`(1.0), `life`(1.2), `always`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.respond.brush_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.respond.brush_off   [28 chars]
    en  Everyone has days like that.
    >>  ............................................
    pt  Todo mundo tem dias assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `day.rough.brush_off`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +4  _(recorded under topic `day.rough.brush_off`)_
- Then opens: `conversations.topic.day.rough.repair`
- …where the player's next choices will be: "That came out wrong. I'm sorry." | "I'm not going to pretend it's a tragedy." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.day.rough.brush_off
WHO    VILLAGER — what the player reads after pressing "Everyone has days like that."
       spoken on: conversations.topic.day.rough.respond, button `brush_off`
       leaves the player on: conversations.topic.day.rough.repair
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.brush_off.to.day.rough.repair`: the villager hurts. Subject `day.rough.repair`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, boundary_push, exit
```

```text
  dialogue.conversations.day.rough.brush_off/1   [41 chars]
    en  ...Right. Everyone does. Thanks for that.
    >>  ............................................
    pt  ...Certo. Todo mundo tem. Valeu por isso.
    >>  ............................................
  dialogue.conversations.day.rough.brush_off/2   [68 chars]
    en  Suppose they do. Doesn't make this one lighter, but suppose they do.
    >>  ............................................
    pt  Devem ter mesmo. Não deixa o meu mais leve, mas devem ter.
    >>  ............................................
  dialogue.conversations.day.rough.brush_off/3   [79 chars]
    en  Mm. That's the sort of thing people say when they'd rather not hear the answer.
    >>  ............................................
    pt  Hm. É o tipo de coisa que se diz quando não se quer ouvir a resposta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.day.rough.brush_off/1
    en  ...I know. It doesn't help, but I know.
    >>  ............................................
    pt  ...Eu sei. Não ajuda, mas eu sei.
    >>  ............................................
  anxious.dialogue.conversations.day.rough.brush_off/2
    en  Everyone does. That's never once made mine lighter, %1$s.
    >>  ............................................
    pt  Todo mundo tem. Isso nunca deixou o meu mais leve, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. Sorry to have brought it up.
    >>  ............................................
    pt  ...Certo. Desculpe por ter levantado.
    >>  ............................................
  athletic.dialogue.conversations.day.rough.brush_off/1
    en  ...Aye, everyone does. It's still today, though.
    >>  ............................................
    pt  ...É, todo mundo tem. Mas ainda é hoje.
    >>  ............................................
  athletic.dialogue.conversations.day.rough.brush_off/2
    en  True enough. It'll pass, same as the rest of them.
    >>  ............................................
    pt  Bem verdade. Vai passar, igual às outras.
    >>  ............................................
  athletic.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll get on with it.
    >>  ............................................
    pt  ...Certo. Vou tocando.
    >>  ............................................
  confident.dialogue.conversations.day.rough.brush_off/1
    en  ...Right. Everyone does. Thanks for that.
    >>  ............................................
    pt  ...Certo. Todo mundo tem. Obrigado por isso.
    >>  ............................................
  confident.dialogue.conversations.day.rough.brush_off/2
    en  Everyone has hard days. I didn't ask for the general case.
    >>  ............................................
    pt  Todo mundo tem dias difíceis. Eu não pedi o caso geral.
    >>  ............................................
  confident.dialogue.conversations.day.rough.brush_off/3
    en  ...Then I'll stop describing mine.
    >>  ............................................
    pt  ...Então eu paro de descrever o meu.
    >>  ............................................
  crabby.dialogue.conversations.day.rough.brush_off/1
    en  ...Right. Everyone does. Thanks for that.
    >>  ............................................
    pt  ...Certo. Todo mundo tem. Obrigado por isso.
    >>  ............................................
  crabby.dialogue.conversations.day.rough.brush_off/2
    en  Everyone has hard days. I didn't ask for the general case.
    >>  ............................................
    pt  Todo mundo tem dias difíceis. Eu não pedi o caso geral.
    >>  ............................................
  crabby.dialogue.conversations.day.rough.brush_off/3
    en  ...Then I'll stop describing mine.
    >>  ............................................
    pt  ...Então eu paro de descrever o meu.
    >>  ............................................
  extroverted.dialogue.conversations.day.rough.brush_off/1
    en  ...I know everyone does, %1$s. I told you because it was mine today.
    >>  ............................................
    pt  ...Eu sei que todo mundo tem, %1$s. Eu te contei porque hoje foi o meu.
    >>  ............................................
  extroverted.dialogue.conversations.day.rough.brush_off/2
    en  That's the answer for somebody you don't know. I'd hoped for the other one.
    >>  ............................................
    pt  É a resposta pra alguém que você não conhece. Eu esperava a outra.
    >>  ............................................
  extroverted.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll ask about your day instead, then.
    >>  ............................................
    pt  ...Certo. Então eu pergunto do seu dia.
    >>  ............................................
  flirty.dialogue.conversations.day.rough.brush_off/1
    en  ...I know everyone does, %1$s. I told you because it was mine today.
    >>  ............................................
    pt  ...Eu sei que todo mundo tem, %1$s. Eu te contei porque hoje foi o meu.
    >>  ............................................
  flirty.dialogue.conversations.day.rough.brush_off/2
    en  That's the answer for somebody you don't know. I'd hoped for the other one.
    >>  ............................................
    pt  É a resposta pra alguém que você não conhece. Eu esperava a outra.
    >>  ............................................
  flirty.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll ask about your day instead, then.
    >>  ............................................
    pt  ...Certo. Então eu pergunto do seu dia.
    >>  ............................................
  friendly.dialogue.conversations.day.rough.brush_off/1
    en  ...I know everyone does, %1$s. I told you because it was mine today.
    >>  ............................................
    pt  ...Eu sei que todo mundo tem, %1$s. Eu te contei porque hoje foi o meu.
    >>  ............................................
  friendly.dialogue.conversations.day.rough.brush_off/2
    en  That's the answer for somebody you don't know. I'd hoped for the other one.
    >>  ............................................
    pt  É a resposta pra alguém que você não conhece. Eu esperava a outra.
    >>  ............................................
  friendly.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll ask about your day instead, then.
    >>  ............................................
    pt  ...Certo. Então eu pergunto do seu dia.
    >>  ............................................
  gloomy.dialogue.conversations.day.rough.brush_off/1
    en  ...I know. It doesn't help, but I know.
    >>  ............................................
    pt  ...Eu sei. Não ajuda, mas eu sei.
    >>  ............................................
  gloomy.dialogue.conversations.day.rough.brush_off/2
    en  Everyone does. That's never once made mine lighter, %1$s.
    >>  ............................................
    pt  Todo mundo tem. Isso nunca deixou o meu mais leve, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. Sorry to have brought it up.
    >>  ............................................
    pt  ...Certo. Desculpe por ter levantado.
    >>  ............................................
  greedy.dialogue.conversations.day.rough.brush_off/1
    en  ...Right. Everyone does. Thanks for that.
    >>  ............................................
    pt  ...Certo. Todo mundo tem. Obrigado por isso.
    >>  ............................................
  greedy.dialogue.conversations.day.rough.brush_off/2
    en  Everyone has hard days. I didn't ask for the general case.
    >>  ............................................
    pt  Todo mundo tem dias difíceis. Eu não pedi o caso geral.
    >>  ............................................
  greedy.dialogue.conversations.day.rough.brush_off/3
    en  ...Then I'll stop describing mine.
    >>  ............................................
    pt  ...Então eu paro de descrever o meu.
    >>  ............................................
  grumpy.dialogue.conversations.day.rough.brush_off/1
    en  ...Right. Everyone does. Thanks for that.
    >>  ............................................
    pt  ...Certo. Todo mundo tem. Obrigado por isso.
    >>  ............................................
  grumpy.dialogue.conversations.day.rough.brush_off/2
    en  Everyone has hard days. I didn't ask for the general case.
    >>  ............................................
    pt  Todo mundo tem dias difíceis. Eu não pedi o caso geral.
    >>  ............................................
  grumpy.dialogue.conversations.day.rough.brush_off/3
    en  ...Then I'll stop describing mine.
    >>  ............................................
    pt  ...Então eu paro de descrever o meu.
    >>  ............................................
  introverted.dialogue.conversations.day.rough.brush_off/1
    en  ...Mm. They do.
    >>  ............................................
    pt  ...Hum. Têm mesmo.
    >>  ............................................
  introverted.dialogue.conversations.day.rough.brush_off/2
    en  Right. That's true.
    >>  ............................................
    pt  Certo. É verdade.
    >>  ............................................
  introverted.dialogue.conversations.day.rough.brush_off/3
    en  ...I'll leave it, then.
    >>  ............................................
    pt  ...Vou deixar, então.
    >>  ............................................
  lazy.dialogue.conversations.day.rough.brush_off/1
    en  ...Aye, everyone does. It's still today, though.
    >>  ............................................
    pt  ...É, todo mundo tem. Mas ainda é hoje.
    >>  ............................................
  lazy.dialogue.conversations.day.rough.brush_off/2
    en  True enough. It'll pass, same as the rest of them.
    >>  ............................................
    pt  Bem verdade. Vai passar, igual às outras.
    >>  ............................................
  lazy.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll get on with it.
    >>  ............................................
    pt  ...Certo. Vou tocando.
    >>  ............................................
  odd.dialogue.conversations.day.rough.brush_off/1
    en  ...Mm. They do.
    >>  ............................................
    pt  ...Hum. Têm mesmo.
    >>  ............................................
  odd.dialogue.conversations.day.rough.brush_off/2
    en  Right. That's true.
    >>  ............................................
    pt  Certo. É verdade.
    >>  ............................................
  odd.dialogue.conversations.day.rough.brush_off/3
    en  ...I'll leave it, then.
    >>  ............................................
    pt  ...Vou deixar, então.
    >>  ............................................
  peaceful.dialogue.conversations.day.rough.brush_off/1
    en  ...Aye, everyone does. It's still today, though.
    >>  ............................................
    pt  ...É, todo mundo tem. Mas ainda é hoje.
    >>  ............................................
  peaceful.dialogue.conversations.day.rough.brush_off/2
    en  True enough. It'll pass, same as the rest of them.
    >>  ............................................
    pt  Bem verdade. Vai passar, igual às outras.
    >>  ............................................
  peaceful.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll get on with it.
    >>  ............................................
    pt  ...Certo. Vou tocando.
    >>  ............................................
  peppy.dialogue.conversations.day.rough.brush_off/1
    en  ...Everyone! Yes. Marvellous. I feel enormously seen.
    >>  ............................................
    pt  ...Todo mundo! Sim. Maravilhoso. Me sinto imensamente visto.
    >>  ............................................
  peppy.dialogue.conversations.day.rough.brush_off/2
    en  Right, well. Universal experience. Very comforting, that.
    >>  ............................................
    pt  Certo, bom. Experiência universal. Muito reconfortante.
    >>  ............................................
  peppy.dialogue.conversations.day.rough.brush_off/3
    en  ...Ah, everyone does. Silly of me to have mentioned it.
    >>  ............................................
    pt  ...Ah, todo mundo tem. Que bobo eu ter mencionado.
    >>  ............................................
  playful.dialogue.conversations.day.rough.brush_off/1
    en  ...Everyone! Yes. Marvellous. I feel enormously seen.
    >>  ............................................
    pt  ...Todo mundo! Sim. Maravilhoso. Me sinto imensamente visto.
    >>  ............................................
  playful.dialogue.conversations.day.rough.brush_off/2
    en  Right, well. Universal experience. Very comforting, that.
    >>  ............................................
    pt  Certo, bom. Experiência universal. Muito reconfortante.
    >>  ............................................
  playful.dialogue.conversations.day.rough.brush_off/3
    en  ...Ah, everyone does. Silly of me to have mentioned it.
    >>  ............................................
    pt  ...Ah, todo mundo tem. Que bobo eu ter mencionado.
    >>  ............................................
  relaxed.dialogue.conversations.day.rough.brush_off/1
    en  ...Aye, everyone does. It's still today, though.
    >>  ............................................
    pt  ...É, todo mundo tem. Mas ainda é hoje.
    >>  ............................................
  relaxed.dialogue.conversations.day.rough.brush_off/2
    en  True enough. It'll pass, same as the rest of them.
    >>  ............................................
    pt  Bem verdade. Vai passar, igual às outras.
    >>  ............................................
  relaxed.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. I'll get on with it.
    >>  ............................................
    pt  ...Certo. Vou tocando.
    >>  ............................................
  sensitive.dialogue.conversations.day.rough.brush_off/1
    en  ...I know. It doesn't help, but I know.
    >>  ............................................
    pt  ...Eu sei. Não ajuda, mas eu sei.
    >>  ............................................
  sensitive.dialogue.conversations.day.rough.brush_off/2
    en  Everyone does. That's never once made mine lighter, %1$s.
    >>  ............................................
    pt  Todo mundo tem. Isso nunca deixou o meu mais leve, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.day.rough.brush_off/3
    en  ...Right. Sorry to have brought it up.
    >>  ............................................
    pt  ...Certo. Desculpe por ter levantado.
    >>  ............................................
  shy.dialogue.conversations.day.rough.brush_off/1
    en  ...Mm. They do.
    >>  ............................................
    pt  ...Hum. Têm mesmo.
    >>  ............................................
  shy.dialogue.conversations.day.rough.brush_off/2
    en  Right. That's true.
    >>  ............................................
    pt  Certo. É verdade.
    >>  ............................................
  shy.dialogue.conversations.day.rough.brush_off/3
    en  ...I'll leave it, then.
    >>  ............................................
    pt  ...Vou deixar, então.
    >>  ............................................
  upbeat.dialogue.conversations.day.rough.brush_off/1
    en  ...Everyone! Yes. Marvellous. I feel enormously seen.
    >>  ............................................
    pt  ...Todo mundo! Sim. Maravilhoso. Me sinto imensamente visto.
    >>  ............................................
  upbeat.dialogue.conversations.day.rough.brush_off/2
    en  Right, well. Universal experience. Very comforting, that.
    >>  ............................................
    pt  Certo, bom. Experiência universal. Muito reconfortante.
    >>  ............................................
  upbeat.dialogue.conversations.day.rough.brush_off/3
    en  ...Ah, everyone does. Silly of me to have mentioned it.
    >>  ............................................
    pt  ...Ah, todo mundo tem. Que bobo eu ter mencionado.
    >>  ............................................
  witty.dialogue.conversations.day.rough.brush_off/1
    en  ...Everyone! Yes. Marvellous. I feel enormously seen.
    >>  ............................................
    pt  ...Todo mundo! Sim. Maravilhoso. Me sinto imensamente visto.
    >>  ............................................
  witty.dialogue.conversations.day.rough.brush_off/2
    en  Right, well. Universal experience. Very comforting, that.
    >>  ............................................
    pt  Certo, bom. Experiência universal. Muito reconfortante.
    >>  ............................................
  witty.dialogue.conversations.day.rough.brush_off/3
    en  ...Ah, everyone does. Silly of me to have mentioned it.
    >>  ............................................
    pt  ...Ah, todo mundo tem. Que bobo eu ter mencionado.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `day.rough.to.day.rough` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.rough.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.rough.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.day.rough.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.rough.followup / leave; conversations.topic.day.rough.repair / leave
```

> Written out in full under **`conversations.topic.day.rough.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.day.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.toddler` — e.g. "I stacked mud! It was the best mud."


```text
POOL   dialogue key: dialogue.conversations.topic.day.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.toddler.respond   [14 chars]
    en  That's my day!
    >>  ............................................
    pt  Esse é o meu dia!
    >>  ............................................
```


### Button `delighted` — "That is the best news I've heard all day."

*stance family `encouragement` · tone `playful` · answers the beat(s) `day.toddler.to.day.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.toddler.delighted` — accepted phrasings: "that's the best news"; "wow"; "that's amazing"; "brilliant"
  - the message must contain one of: `best`, `amazing`, `wow`, `great`
  - scored words: `best`(1.5), `amazing`(1.5), `wow`(1.5), `news`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.toddler.respond.delighted
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.toddler.respond.delighted   [41 chars]
    en  That is the best news I've heard all day.
    >>  ............................................
    pt  Essa é a melhor notícia do meu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.toddler.delighted`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `day.toddler.delighted`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.toddler.delighted
WHO    VILLAGER — what the player reads after pressing "That is the best news I've heard all day."
       spoken on: conversations.topic.day.toddler.respond, button `delighted`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.toddler.delighted.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.toddler.delighted/1   [7 chars]
    en  I KNOW!
    >>  ............................................
    pt  EU SEI!
    >>  ............................................
  dialogue.conversations.day.toddler.delighted/2   [28 chars]
    en  It WAS the best! You get it!
    >>  ............................................
    pt  Foi o MELHOR! Você entendeu!
    >>  ............................................
  dialogue.conversations.day.toddler.delighted/3   [16 chars]
    en  Yes! Yes it was!
    >>  ............................................
    pt  Sim! Foi sim!
    >>  ............................................
```


### Button `ask` — "Tell me more about that."

*stance family `curiosity` · tone `plain` · answers the beat(s) `day.toddler.to.day.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.toddler.ask` — accepted phrasings: "tell me more"; "how much"; "how many"; "really, how much"
  - the message must contain one of: `more`, `tell`, `much`, `many`
  - scored words: `more`(1.2), `tell`(1.0), `much`(1.2), `many`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.day.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.toddler.respond.ask   [24 chars]
    en  Tell me more about that.
    >>  ............................................
    pt  Me conta mais sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `day.toddler.ask`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.toddler.ask
WHO    VILLAGER — what the player reads after pressing "Tell me more about that."
       spoken on: conversations.topic.day.toddler.respond, button `ask`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.toddler.ask.terminal`: the villager asks. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.toddler.ask/1   [32 chars]
    en  Lots. This many. No — THIS many.
    >>  ............................................
    pt  Muito. Deste tanto. Não — DESTE tanto.
    >>  ............................................
  dialogue.conversations.day.toddler.ask/2   [31 chars]
    en  It was big. Bigger than a duck.
    >>  ............................................
    pt  Era grande. Maior que um pato.
    >>  ............................................
  dialogue.conversations.day.toddler.ask/3   [33 chars]
    en  I don't remember but it was GOOD.
    >>  ............................................
    pt  Não lembro mas era BOM.
    >>  ............................................
```


### Button `leave` — "Off you go, then."

*stance family `exit` · tone `plain` · answers the beat(s) `day.toddler.to.day.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.toddler.respond.leave   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir brincar, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go, then."
       spoken on: conversations.topic.day.toddler.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.toddler.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.toddler.leave/1   [8 chars]
    en  Bye bye!
    >>  ............................................
    pt  Tchau tchau!
    >>  ............................................
  dialogue.conversations.day.toddler.leave/2   [38 chars]
    en  Okay bye! I'm going to go do it again!
    >>  ............................................
    pt  Tá, tchau! Vou fazer de novo!
    >>  ............................................
  dialogue.conversations.day.toddler.leave/3   [18 chars]
    en  Bye! ...Wait. Bye!
    >>  ............................................
    pt  Tchau! ...Espera. Tchau!
    >>  ............................................
```

---


## `conversations.topic.day.young.followup`

**Reached from 2 route(s):** `conversations.topic.day.young.respond` / `interested`; `conversations.topic.day.young.respond` / `encourage`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.young.encourage` — e.g. "It was, wasn't it! Nobody counts it as work but it IS."
- `conversations.day.young.interested` — e.g. "Really? Nobody ever wants the long version. Right — so —"


```text
POOL   dialogue key: dialogue.conversations.topic.day.young.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.young.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.young.followup   [24 chars]
    en  So yeah. That was today.
    >>  ............................................
    pt  Então é. Foi isso hoje.
    >>  ............................................
```


### Button `play_along` — "And then what happened?"

*stance family `encouragement` · tone `playful` · answers the beat(s) `day.young.encourage.to.day.young`, `day.young.interested.to.day.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.play_along` — accepted phrasings: "and then what"; "what happened next"; "and then"; "go on, what next"
  - the message must contain one of: `happened`, `next`, `then`
  - scored words: `then`(1.0), `happened`(1.5), `next`(1.2), `and`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.followup.play_along
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.followup.play_along   [23 chars]
    en  And then what happened?
    >>  ............................................
    pt  E aí o que aconteceu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.young.play_along`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `day.young.play_along`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.play_along
WHO    VILLAGER — what the player reads after pressing "And then what happened?"
       spoken on: conversations.topic.day.young.followup, button `play_along`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.play_along.terminal`: the villager accepts. Subject `day.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.young.play_along/1   [67 chars]
    en  AND THEN — right, so — you're the only one who ever lets me finish.
    >>  ............................................
    pt  E AÍ — então — você é o único que me deixa terminar.
    >>  ............................................
  dialogue.conversations.day.young.play_along/2   [35 chars]
    en  Then it got worse! In the good way!
    >>  ............................................
    pt  Aí piorou! Do jeito bom!
    >>  ............................................
  dialogue.conversations.day.young.play_along/3   [51 chars]
    en  You're going to regret asking. I've got three more.
    >>  ............................................
    pt  Você vai se arrepender de perguntar. Tenho mais três.
    >>  ............................................
```


### Button `ask_more` — "Is that how it usually goes?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `day.young.encourage.to.day.young`, `day.young.interested.to.day.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.ask_more` — accepted phrasings: "is it usually like that"; "is it always like this"; "does that happen a lot"; "is that normal"
  - the message must contain one of: `usually`, `always`, `normally`, `every`
  - scored words: `usually`(1.5), `always`(1.2), `normally`(1.5), `every`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.followup.ask_more   [28 chars]
    en  Is that how it usually goes?
    >>  ............................................
    pt  É sempre assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `day.young.ask_more`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.ask_more
WHO    VILLAGER — what the player reads after pressing "Is that how it usually goes?"
       spoken on: conversations.topic.day.young.followup, button `ask_more`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.ask_more.terminal`: the villager explains. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.day.young.ask_more/1   [57 chars]
    en  Mostly, yeah. Some days are better. Today was a good one.
    >>  ............................................
    pt  Na maioria das vezes, sim. Uns dias são melhores. Hoje foi bom.
    >>  ............................................
  dialogue.conversations.day.young.ask_more/2   [40 chars]
    en  Depends on the day. Depends who's about.
    >>  ............................................
    pt  Depende do dia. Depende de quem está por perto.
    >>  ............................................
  dialogue.conversations.day.young.ask_more/3   [40 chars]
    en  More or less. It's alright here, mostly.
    >>  ............................................
    pt  Mais ou menos. Aqui é de boa, na maior parte.
    >>  ............................................
```


### Button `leave` — "I should get on."

*stance family `exit` · tone `plain` · answers the beat(s) `day.young.encourage.to.day.young`, `day.young.interested.to.day.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.followup.leave   [16 chars]
    en  I should get on.
    >>  ............................................
    pt  Preciso ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.leave
WHO    VILLAGER — what the player reads after pressing "I should get on."
       spoken on: conversations.topic.day.young.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.young.respond / leave
```

```text
  dialogue.conversations.day.young.leave/1   [20 chars]
    en  See you round, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.day.young.leave/2   [9 chars]
    en  Bye then!
    >>  ............................................
    pt  Tchau então!
    >>  ............................................
  dialogue.conversations.day.young.leave/3   [45 chars]
    en  Alright. Come back when you've got more time.
    >>  ............................................
    pt  Tá bom. Volta quando tiver mais tempo.
    >>  ............................................
```

---


## `conversations.topic.day.young.respond`

**Reached from 2 route(s):** `conversations.cat.chitchat` / `day`; `conversations.cat.chitchat` / `day`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.child` — e.g. "BEST day. I found a rock shaped like a slightly different rock!"
- `conversations.day.teen` — e.g. "It's fine. It's a day. They keep happening whether I approve or not."


```text
POOL   dialogue key: dialogue.conversations.topic.day.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.young.respond   [22 chars]
    en  That's my day, anyway.
    >>  ............................................
    pt  Enfim, esse foi o meu dia.
    >>  ............................................
```


### Button `interested` — "Go on, tell me properly."

*stance family `curiosity` · tone `plain` · answers the beat(s) `day.child.to.day.young`, `day.teen.to.day.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.interested` — accepted phrasings: "tell me properly"; "go on then"; "i want to hear it"; "tell me more"
  - the message must contain one of: `tell`, `properly`, `more`, `hear`
  - scored words: `tell`(1.2), `properly`(1.5), `more`(1.0), `go`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.respond.interested
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.respond.interested   [24 chars]
    en  Go on, tell me properly.
    >>  ............................................
    pt  Vai, me conta direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.young.interested`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `day.young.interested`)_
- Then opens: `conversations.topic.day.young.followup`
- …where the player's next choices will be: "And then what happened?" | "Is that how it usually goes?" | "I should get on."

```text
POOL   dialogue key: dialogue.conversations.day.young.interested
WHO    VILLAGER — what the player reads after pressing "Go on, tell me properly."
       spoken on: conversations.topic.day.young.respond, button `interested`
       leaves the player on: conversations.topic.day.young.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.interested.to.day.young`: the villager accepts. Subject `day.young`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.young.interested/1   [56 chars]
    en  Really? Nobody ever wants the long version. Right — so —
    >>  ............................................
    pt  Sério? Ninguém nunca quer a versão longa. Então — olha só —
    >>  ............................................
  dialogue.conversations.day.young.interested/2   [38 chars]
    en  You actually want to hear it? Alright!
    >>  ............................................
    pt  Você quer mesmo ouvir? Beleza!
    >>  ............................................
  dialogue.conversations.day.young.interested/3   [41 chars]
    en  Hah. Fine. Sit down, this takes a minute.
    >>  ............................................
    pt  Rá. Tá bom. Senta, isso leva um minuto.
    >>  ............................................
```


### Button `encourage` — "That sounds like a good day's work."

*stance family `encouragement` · tone `plain` · answers the beat(s) `day.child.to.day.young`, `day.teen.to.day.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.encourage` — accepted phrasings: "that sounds like good work"; "that's a good day's work"; "well done"; "i'd be proud of that"
  - the message must contain one of: `work`, `proud`, `good`, `well`
  - scored words: `work`(1.2), `proud`(1.5), `good`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.respond.encourage   [35 chars]
    en  That sounds like a good day's work.
    >>  ............................................
    pt  Parece um bom dia de trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `day.young.encourage`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `day.young.encourage`)_
- Then opens: `conversations.topic.day.young.followup`
- …where the player's next choices will be: "And then what happened?" | "Is that how it usually goes?" | "I should get on."

```text
POOL   dialogue key: dialogue.conversations.day.young.encourage
WHO    VILLAGER — what the player reads after pressing "That sounds like a good day's work."
       spoken on: conversations.topic.day.young.respond, button `encourage`
       leaves the player on: conversations.topic.day.young.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.encourage.to.day.young`: the villager accepts. Subject `day.young`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.young.encourage/1   [54 chars]
    en  It was, wasn't it! Nobody counts it as work but it IS.
    >>  ............................................
    pt  Foi, né! Ninguém conta como trabalho mas É.
    >>  ............................................
  dialogue.conversations.day.young.encourage/2   [77 chars]
    en  ...Huh. Thanks. Usually it's just 'that's nice' and then a change of subject.
    >>  ............................................
    pt  ...Hm. Obrigado. Normalmente é só 'que bom' e aí muda de assunto.
    >>  ............................................
  dialogue.conversations.day.young.encourage/3   [31 chars]
    en  See, that's what I keep saying.
    >>  ............................................
    pt  Viu, é isso que eu fico falando.
    >>  ............................................
```


### Button `dismiss` — "That's not really news."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `day.child.to.day.young`, `day.teen.to.day.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.dismiss` — accepted phrasings: "that's not really news"; "that's boring"; "who cares"; "so what"
  - the message must contain one of: `news`, `boring`, `whatever`, `care`
  - scored words: `news`(1.5), `boring`(1.5), `whatever`(1.0), `really`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.respond.dismiss   [23 chars]
    en  That's not really news.
    >>  ............................................
    pt  Isso não é bem novidade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `day.young.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `day.young.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.day.young.snubbed.followup`
- …where the player's next choices will be: "No — I asked and then didn't listen. Sorry." | "Tell me again. I'm listening properly now." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.day.young.dismiss
WHO    VILLAGER — what the player reads after pressing "That's not really news."
       spoken on: conversations.topic.day.young.respond, button `dismiss`
       leaves the player on: conversations.topic.day.young.snubbed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.snubbed.open`: the villager hurts. Subject `day.young`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a child/teen
```

```text
  dialogue.conversations.day.young.dismiss/1   [32 chars]
    en  ...Fine. Forget I said anything.
    >>  ............................................
    pt  ...Tá. Esquece que eu falei.
    >>  ............................................
  dialogue.conversations.day.young.dismiss/2   [39 chars]
    en  Understood. Grown-up news only. Got it.
    >>  ............................................
    pt  Entendido. Só notícia de adulto. Entendi.
    >>  ............................................
  dialogue.conversations.day.young.dismiss/3   [46 chars]
    en  You asked, though. That's the bit I don't get.
    >>  ............................................
    pt  Mas você perguntou. Essa é a parte que eu não entendo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Sorry. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Desculpe. Esqueça que eu disse algo.
    >>  ............................................
  anxious.dialogue.conversations.day.young.dismiss/2
    en  I shouldn't have brought it up.
    >>  ............................................
    pt  Eu não devia ter levantado.
    >>  ............................................
  anxious.dialogue.conversations.day.young.dismiss/3
    en  ...Right. I'll keep the next one to myself.
    >>  ............................................
    pt  ...Certo. Vou guardar a próxima pra mim.
    >>  ............................................
  athletic.dialogue.conversations.day.young.dismiss/1
    en  ...All right. Another day, then.
    >>  ............................................
    pt  ...Está bem. Outro dia, então.
    >>  ............................................
  athletic.dialogue.conversations.day.young.dismiss/2
    en  Fine. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  athletic.dialogue.conversations.day.young.dismiss/3
    en  ...Right you are. Never mind.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  confident.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Esqueça que eu disse algo.
    >>  ............................................
  confident.dialogue.conversations.day.young.dismiss/2
    en  Right. I'll not tell you the next one.
    >>  ............................................
    pt  Certo. Não te conto a próxima.
    >>  ............................................
  confident.dialogue.conversations.day.young.dismiss/3
    en  ...Then I'll keep it.
    >>  ............................................
    pt  ...Então eu guardo.
    >>  ............................................
  crabby.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Esqueça que eu disse algo.
    >>  ............................................
  crabby.dialogue.conversations.day.young.dismiss/2
    en  Right. I'll not tell you the next one.
    >>  ............................................
    pt  Certo. Não te conto a próxima.
    >>  ............................................
  crabby.dialogue.conversations.day.young.dismiss/3
    en  ...Then I'll keep it.
    >>  ............................................
    pt  ...Então eu guardo.
    >>  ............................................
  extroverted.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. I thought you'd want to hear it, %1$s.
    >>  ............................................
    pt  ...Tudo bem. Achei que você fosse querer ouvir, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.day.young.dismiss/2
    en  Right. Forget it. I'd been saving it for you, mind.
    >>  ............................................
    pt  Certo. Esqueça. Mas eu estava guardando pra você.
    >>  ............................................
  extroverted.dialogue.conversations.day.young.dismiss/3
    en  ...I'll not bother you with the next one.
    >>  ............................................
    pt  ...Não vou te incomodar com a próxima.
    >>  ............................................
  flirty.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. I thought you'd want to hear it, %1$s.
    >>  ............................................
    pt  ...Tudo bem. Achei que você fosse querer ouvir, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.day.young.dismiss/2
    en  Right. Forget it. I'd been saving it for you, mind.
    >>  ............................................
    pt  Certo. Esqueça. Mas eu estava guardando pra você.
    >>  ............................................
  flirty.dialogue.conversations.day.young.dismiss/3
    en  ...I'll not bother you with the next one.
    >>  ............................................
    pt  ...Não vou te incomodar com a próxima.
    >>  ............................................
  friendly.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. I thought you'd want to hear it, %1$s.
    >>  ............................................
    pt  ...Tudo bem. Achei que você fosse querer ouvir, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.day.young.dismiss/2
    en  Right. Forget it. I'd been saving it for you, mind.
    >>  ............................................
    pt  Certo. Esqueça. Mas eu estava guardando pra você.
    >>  ............................................
  friendly.dialogue.conversations.day.young.dismiss/3
    en  ...I'll not bother you with the next one.
    >>  ............................................
    pt  ...Não vou te incomodar com a próxima.
    >>  ............................................
  gloomy.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Sorry. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Desculpe. Esqueça que eu disse algo.
    >>  ............................................
  gloomy.dialogue.conversations.day.young.dismiss/2
    en  I shouldn't have brought it up.
    >>  ............................................
    pt  Eu não devia ter levantado.
    >>  ............................................
  gloomy.dialogue.conversations.day.young.dismiss/3
    en  ...Right. I'll keep the next one to myself.
    >>  ............................................
    pt  ...Certo. Vou guardar a próxima pra mim.
    >>  ............................................
  greedy.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Esqueça que eu disse algo.
    >>  ............................................
  greedy.dialogue.conversations.day.young.dismiss/2
    en  Right. I'll not tell you the next one.
    >>  ............................................
    pt  Certo. Não te conto a próxima.
    >>  ............................................
  greedy.dialogue.conversations.day.young.dismiss/3
    en  ...Then I'll keep it.
    >>  ............................................
    pt  ...Então eu guardo.
    >>  ............................................
  grumpy.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Esqueça que eu disse algo.
    >>  ............................................
  grumpy.dialogue.conversations.day.young.dismiss/2
    en  Right. I'll not tell you the next one.
    >>  ............................................
    pt  Certo. Não te conto a próxima.
    >>  ............................................
  grumpy.dialogue.conversations.day.young.dismiss/3
    en  ...Then I'll keep it.
    >>  ............................................
    pt  ...Então eu guardo.
    >>  ............................................
  introverted.dialogue.conversations.day.young.dismiss/1
    en  ...Fine.
    >>  ............................................
    pt  ...Tudo bem.
    >>  ............................................
  introverted.dialogue.conversations.day.young.dismiss/2
    en  Forget it.
    >>  ............................................
    pt  Esqueça.
    >>  ............................................
  introverted.dialogue.conversations.day.young.dismiss/3
    en  ...Right. Nothing, then.
    >>  ............................................
    pt  ...Certo. Nada, então.
    >>  ............................................
  lazy.dialogue.conversations.day.young.dismiss/1
    en  ...All right. Another day, then.
    >>  ............................................
    pt  ...Está bem. Outro dia, então.
    >>  ............................................
  lazy.dialogue.conversations.day.young.dismiss/2
    en  Fine. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  lazy.dialogue.conversations.day.young.dismiss/3
    en  ...Right you are. Never mind.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  odd.dialogue.conversations.day.young.dismiss/1
    en  ...Fine.
    >>  ............................................
    pt  ...Tudo bem.
    >>  ............................................
  odd.dialogue.conversations.day.young.dismiss/2
    en  Forget it.
    >>  ............................................
    pt  Esqueça.
    >>  ............................................
  odd.dialogue.conversations.day.young.dismiss/3
    en  ...Right. Nothing, then.
    >>  ............................................
    pt  ...Certo. Nada, então.
    >>  ............................................
  peaceful.dialogue.conversations.day.young.dismiss/1
    en  ...All right. Another day, then.
    >>  ............................................
    pt  ...Está bem. Outro dia, então.
    >>  ............................................
  peaceful.dialogue.conversations.day.young.dismiss/2
    en  Fine. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  peaceful.dialogue.conversations.day.young.dismiss/3
    en  ...Right you are. Never mind.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  peppy.dialogue.conversations.day.young.dismiss/1
    en  ...Fine! Forget it. It was a good one, too.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. E era boa.
    >>  ............................................
  peppy.dialogue.conversations.day.young.dismiss/2
    en  Right, well. I'll tell somebody who wants it.
    >>  ............................................
    pt  Certo, bom. Vou contar pra quem queira.
    >>  ............................................
  peppy.dialogue.conversations.day.young.dismiss/3
    en  ...Never mind! It's gone now.
    >>  ............................................
    pt  ...Deixa pra lá! Já foi.
    >>  ............................................
  playful.dialogue.conversations.day.young.dismiss/1
    en  ...Fine! Forget it. It was a good one, too.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. E era boa.
    >>  ............................................
  playful.dialogue.conversations.day.young.dismiss/2
    en  Right, well. I'll tell somebody who wants it.
    >>  ............................................
    pt  Certo, bom. Vou contar pra quem queira.
    >>  ............................................
  playful.dialogue.conversations.day.young.dismiss/3
    en  ...Never mind! It's gone now.
    >>  ............................................
    pt  ...Deixa pra lá! Já foi.
    >>  ............................................
  relaxed.dialogue.conversations.day.young.dismiss/1
    en  ...All right. Another day, then.
    >>  ............................................
    pt  ...Está bem. Outro dia, então.
    >>  ............................................
  relaxed.dialogue.conversations.day.young.dismiss/2
    en  Fine. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  relaxed.dialogue.conversations.day.young.dismiss/3
    en  ...Right you are. Never mind.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  sensitive.dialogue.conversations.day.young.dismiss/1
    en  ...Fine. Sorry. Forget I said anything.
    >>  ............................................
    pt  ...Tudo bem. Desculpe. Esqueça que eu disse algo.
    >>  ............................................
  sensitive.dialogue.conversations.day.young.dismiss/2
    en  I shouldn't have brought it up.
    >>  ............................................
    pt  Eu não devia ter levantado.
    >>  ............................................
  sensitive.dialogue.conversations.day.young.dismiss/3
    en  ...Right. I'll keep the next one to myself.
    >>  ............................................
    pt  ...Certo. Vou guardar a próxima pra mim.
    >>  ............................................
  shy.dialogue.conversations.day.young.dismiss/1
    en  ...Fine.
    >>  ............................................
    pt  ...Tudo bem.
    >>  ............................................
  shy.dialogue.conversations.day.young.dismiss/2
    en  Forget it.
    >>  ............................................
    pt  Esqueça.
    >>  ............................................
  shy.dialogue.conversations.day.young.dismiss/3
    en  ...Right. Nothing, then.
    >>  ............................................
    pt  ...Certo. Nada, então.
    >>  ............................................
  upbeat.dialogue.conversations.day.young.dismiss/1
    en  ...Fine! Forget it. It was a good one, too.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. E era boa.
    >>  ............................................
  upbeat.dialogue.conversations.day.young.dismiss/2
    en  Right, well. I'll tell somebody who wants it.
    >>  ............................................
    pt  Certo, bom. Vou contar pra quem queira.
    >>  ............................................
  upbeat.dialogue.conversations.day.young.dismiss/3
    en  ...Never mind! It's gone now.
    >>  ............................................
    pt  ...Deixa pra lá! Já foi.
    >>  ............................................
  witty.dialogue.conversations.day.young.dismiss/1
    en  ...Fine! Forget it. It was a good one, too.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. E era boa.
    >>  ............................................
  witty.dialogue.conversations.day.young.dismiss/2
    en  Right, well. I'll tell somebody who wants it.
    >>  ............................................
    pt  Certo, bom. Vou contar pra quem queira.
    >>  ............................................
  witty.dialogue.conversations.day.young.dismiss/3
    en  ...Never mind! It's gone now.
    >>  ............................................
    pt  ...Deixa pra lá! Já foi.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `day.child.to.day.young`, `day.teen.to.day.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que estava fazendo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.day.young.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.leave.terminal`: the villager accepts. Subject `day.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.day.young.followup / leave
```

> Written out in full under **`conversations.topic.day.young.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.day.young.snubbed.followup`

**Reached from 1 route(s):** `conversations.topic.day.young.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.day.young.dismiss` — e.g. "...Fine. Forget I said anything."


```text
POOL   dialogue key: dialogue.conversations.topic.day.young.snubbed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.day.young.snubbed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.day.young.snubbed.followup   [23 chars]
    en  Forget I said anything.
    >>  ............................................
    pt  Esquece que eu falei.
    >>  ............................................
```


### Button `apologize` — "No — I asked and then didn't listen. Sorry."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `day.young.snubbed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.snubbed.apologize` — accepted phrasings: "no — i asked and then didn't listen. sorry"
  - the message must contain one of: `listen`, `asked`, `sorry`
  - scored words: `listen`(1.2), `asked`(1.2), `sorry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.snubbed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.snubbed.followup.apologize   [43 chars]
    en  No — I asked and then didn't listen. Sorry.
    >>  ............................................
    pt  Não — eu perguntei e não escutei. Desculpa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `day.young.snubbed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.snubbed.apologize
WHO    VILLAGER — what the player reads after pressing "No — I asked and then didn't listen. Sorry."
       spoken on: conversations.topic.day.young.snubbed.followup, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.snubbed.apologize`: the villager qualifys. Subject `day.young`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a child/teen
```

```text
  dialogue.conversations.day.young.snubbed.apologize/1   [47 chars]
    en  ...Okay. Do you want to hear it properly, then?
    >>  ............................................
    pt  ...Tá. Então você quer ouvir direito?
    >>  ............................................
  dialogue.conversations.day.young.snubbed.apologize/2   [46 chars]
    en  Grown-ups don't say sorry to me much. Alright.
    >>  ............................................
    pt  Adulto não me pede desculpa muito. Tudo bem.
    >>  ............................................
  dialogue.conversations.day.young.snubbed.apologize/3   [54 chars]
    en  Fine. But you have to actually listen this time, %1$s.
    >>  ............................................
    pt  Beleza. Mas dessa vez você escuta de verdade, %1$s.
    >>  ............................................
```


### Button `explain` — "Tell me again. I'm listening properly now."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `day.young.snubbed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `day.young.snubbed.explain` — accepted phrasings: "tell me again. i'm listening properly now"
  - the message must contain one of: `again`, `properly`
  - scored words: `again`(1.5), `properly`(1.5), `tell`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.snubbed.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.snubbed.followup.explain   [42 chars]
    en  Tell me again. I'm listening properly now.
    >>  ............................................
    pt  Me conta de novo. Agora eu estou escutando direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `day.young.snubbed.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.snubbed.explain
WHO    VILLAGER — what the player reads after pressing "Tell me again. I'm listening properly now."
       spoken on: conversations.topic.day.young.snubbed.followup, button `explain`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.snubbed.explain`: the villager qualifys. Subject `day.young`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a child/teen
```

```text
  dialogue.conversations.day.young.snubbed.explain/1   [66 chars]
    en  ...Right. So it started at the well, and it got better from there.
    >>  ............................................
    pt  ...Certo. Então começou no poço, e melhorou depois.
    >>  ............................................
  dialogue.conversations.day.young.snubbed.explain/2   [57 chars]
    en  You'd better be. It's a good one and I've been saving it.
    >>  ............................................
    pt  É bom mesmo. É boa e eu estava guardando.
    >>  ............................................
  dialogue.conversations.day.young.snubbed.explain/3   [60 chars]
    en  Okay. But it's shorter now because you ruined the beginning.
    >>  ............................................
    pt  Tá. Mas agora é mais curta porque você estragou o começo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `day.young.snubbed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.day.young.snubbed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.day.young.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.day.young.snubbed.followup.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.young.snubbed.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.day.young.snubbed.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.young.snubbed.leave`: the villager accepts. Subject `day.young`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a child/teen
```

```text
  dialogue.conversations.day.young.snubbed.leave/1   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
  dialogue.conversations.day.young.snubbed.leave/2   [15 chars]
    en  Bye then, %1$s.
    >>  ............................................
    pt  Tchau então, %1$s.
    >>  ............................................
  dialogue.conversations.day.young.snubbed.leave/3   [5 chars]
    en  Fine.
    >>  ............................................
    pt  Beleza.
    >>  ............................................
```

---

