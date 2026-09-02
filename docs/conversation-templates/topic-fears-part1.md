# Topic: fears

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `fears` |
| Opened from | question `conversations.cat.personal`, button `fears` |
| Depth class (its heart budget) | `deep` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `challenge`, `restraint`, `boundary_push`, `exit` |
| Narrative arc | `fears`, max stage 3 |
| Milestones it can set | `fears.revelation`, `fears.scar` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.fears
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.fears   [23 chars]
    en  What are you afraid of?
    >>  ............................................
    pt  Do que você tem medo?
    >>  ............................................
```

---

**Parts of this conversation:** [part 1](topic-fears-part1.md) · [part 2](topic-fears-part2.md)


## Nodes in this file

- [`conversations.arc.fears.followthrough.followup`](#conversations-arc-fears-followthrough-followup)
- [`conversations.arc.fears.followthrough.respond`](#conversations-arc-fears-followthrough-respond)
- [`conversations.arc.fears.plan.followup`](#conversations-arc-fears-plan-followup)
- [`conversations.arc.fears.plan.respond`](#conversations-arc-fears-plan-respond)
- [`conversations.fears`](#conversations-fears)
- [`conversations.scene.fears.followup`](#conversations-scene-fears-followup)
- [`conversations.scene.fears.the_doorway_one.respond`](#conversations-scene-fears-the-doorway-one-respond)
- [`conversations.scene.fears.the_night_one.respond`](#conversations-scene-fears-the-night-one-respond)
- [`conversations.topic.fears.again.respond`](#conversations-topic-fears-again-respond)
- [`conversations.topic.fears.guarded.respond`](#conversations-topic-fears-guarded-respond)
- [`conversations.topic.fears.lapsed`](#conversations-topic-fears-lapsed)
- [`conversations.topic.fears.open.close`](#conversations-topic-fears-open-close)
- [`conversations.topic.fears.open.close.rebuffed`](#conversations-topic-fears-open-close-rebuffed)
- [`conversations.topic.fears.open.disclosed`](#conversations-topic-fears-open-disclosed)
- [`conversations.topic.fears.open.followup`](#conversations-topic-fears-open-followup)
- [`conversations.topic.fears.open.respond`](#conversations-topic-fears-open-respond)

---

## `conversations.arc.fears.followthrough.followup`

**Reached from 2 route(s):** `conversations.arc.fears.followthrough.respond` / `ask_how`; `conversations.arc.fears.followthrough.respond` / `celebrate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.followthrough.ask_how` — e.g. "Slowly. But slowly is a direction, which is more than I had."
- `conversations.fears.followthrough.celebrate` — e.g. "...It isn't nothing, is it. I keep telling myself it's nothing."


```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.fears.followthrough.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.fears.followthrough.followup   [29 chars]
    en  That's how it stands, anyway.
    >>  ............................................
    pt  É assim que está, enfim.
    >>  ............................................
```


### Button `proud` — "You've come a long way with it."

*stance family `encouragement` · tone `plain` · answers the beat(s) `fears.followthrough.ask_how.to.fears.followthrough`, `fears.followthrough.celebrate.to.fears.followthrough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.followthrough.followup.proud` — accepted phrasings: "you have come a long way with it"; "that is a long way to have come"; "you have come far with this"
  - the message must contain one of: `long`, `way`
  - scored words: `long`(1.5), `way`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.followup.proud
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.followup.proud   [31 chars]
    en  You've come a long way with it.
    >>  ............................................
    pt  Você andou bastante com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.followthrough.followup.proud`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `fears.followthrough.followup.proud`)_
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.followup.proud
WHO    VILLAGER — what the player reads after pressing "You've come a long way with it."
       spoken on: conversations.arc.fears.followthrough.followup, button `proud`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.followup.proud.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.followthrough.followup.proud/1   [97 chars]
    en  A long way. ...I hadn't measured it from the start. I'd only been measuring from here to the end.
    >>  ............................................
    pt  Bastante. ...Eu não tinha medido desde o começo. Só media daqui até o fim.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.proud/2   [89 chars]
    en  Have I? Turn round and look back, you said. I never do that. It's further than I thought.
    >>  ............................................
    pt  Andei? Olhe para trás, você diz. Eu nunca faço isso. É mais longe do que eu pensava.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.proud/3   [71 chars]
    en  That's a kind way to put it, %1$s. And an accurate one, which is rarer.
    >>  ............................................
    pt  É um jeito gentil de dizer, %1$s. E preciso, o que é mais raro.
    >>  ............................................
```


### Button `ask_next` — "What's the next hard bit?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `fears.followthrough.ask_how.to.fears.followthrough`, `fears.followthrough.celebrate.to.fears.followthrough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.followthrough.followup.ask_next` — accepted phrasings: "what is the next hard bit"; "what is the hard part now"; "which bit is hard next"
  - the message must contain one of: `hard`
  - scored words: `hard`(1.5), `next`(1.1)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.followup.ask_next
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.followup.ask_next   [25 chars]
    en  What's the next hard bit?
    >>  ............................................
    pt  Qual é a próxima parte difícil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `fears.followthrough.followup.ask_next`)_
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.followup.ask_next
WHO    VILLAGER — what the player reads after pressing "What's the next hard bit?"
       spoken on: conversations.arc.fears.followthrough.followup, button `ask_next`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.followup.ask_next.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.followthrough.followup.ask_next/1   [86 chars]
    en  The next hard bit is the one where nobody's watching. Those are always the worst ones.
    >>  ............................................
    pt  A próxima parte difícil é aquela em que ninguém está olhando. Essas são sempre as piores.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.ask_next/2   [78 chars]
    en  Doing it twice. Once was luck. Twice is a person changing, and that's heavier.
    >>  ............................................
    pt  Fazer duas vezes. Uma foi sorte. Duas é uma pessoa mudando, e isso pesa mais.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.ask_next/3   [98 chars]
    en  Telling someone else. ...Not yet. You were the practice run, and you don't know how hard that was.
    >>  ............................................
    pt  Contar para outra pessoa. ...Ainda não. Você foi o ensaio, e não sabe como foi difícil.
    >>  ............................................
```


### Button `steady` — "You don't have to be finished."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.followthrough.ask_how.to.fears.followthrough`, `fears.followthrough.celebrate.to.fears.followthrough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.followthrough.followup.steady` — accepted phrasings: "you do not have to be finished"; "you are not required to be finished"; "nobody says you have to be finished"
  - the message must contain one of: `finished`
  - scored words: `finished`(1.6), `have`(0.7)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.followup.steady
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.followup.steady   [30 chars]
    en  You don't have to be finished.
    >>  ............................................
    pt  Você não precisa terminar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.followthrough.followup.steady`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, tension -3  _(recorded under topic `fears.followthrough.followup.steady`)_
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.followup.steady
WHO    VILLAGER — what the player reads after pressing "You don't have to be finished."
       spoken on: conversations.arc.fears.followthrough.followup, button `steady`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.followup.steady.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.followthrough.followup.steady/1   [84 chars]
    en  Not finished. ...I'd been treating it like a job with an end to it. It isn't, is it.
    >>  ............................................
    pt  Não terminar. ...Eu estava tratando isso como um serviço com fim. Não é, né.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.steady/2   [79 chars]
    en  That takes the hurry out of it. I didn't know the hurry was most of the weight.
    >>  ............................................
    pt  Isso tira a pressa. Eu não sabia que a pressa era a maior parte do peso.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.steady/3   [49 chars]
    en  No deadline. Right. I'll breathe out, then, %1$s.
    >>  ............................................
    pt  Sem prazo. Certo. Então eu respiro, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll not make a thing of it."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.followthrough.ask_how.to.fears.followthrough`, `fears.followthrough.celebrate.to.fears.followthrough` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.followup.leave   [28 chars]
    en  I'll not make a thing of it.
    >>  ............................................
    pt  Não vou fazer alarde.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll not make a thing of it."
       spoken on: conversations.arc.fears.followthrough.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.followup.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.followthrough.followup.leave/1   [47 chars]
    en  So I've found. No fuss. That's how I'd want it.
    >>  ............................................
    pt  Foi o que eu vi. Sem alarde. É como eu iria querer.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.leave/2   [20 chars]
    en  Just so. Off you go.
    >>  ............................................
    pt  Exato. Pode ir.
    >>  ............................................
  dialogue.conversations.fears.followthrough.followup.leave/3   [42 chars]
    en  Go on, %1$s. And thank you for the asking.
    >>  ............................................
    pt  Vai lá, %1$s. E obrigado por perguntar.
    >>  ............................................
```

---


## `conversations.arc.fears.followthrough.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.resume.followthrough` — e.g. "I did the thing. Or started it. You'll want to hear."


```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.fears.followthrough.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.fears.followthrough.respond   [29 chars]
    en  You remember what I told you.
    >>  ............................................
    pt  Você lembra do que eu te contei.
    >>  ............................................
```


### Button `ask_how` — "How's it been going?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `fears.resume.followthrough.to.fears.followthrough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.followthrough.ask_how` — accepted phrasings: "how is it going"; "how have you been getting on"; "how are you doing with it"
  - the message must contain one of: `going`, `getting`, `how`, `doing`
  - scored words: `going`(1.5), `how`(0.8), `getting`(1.2), `doing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.respond.ask_how
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.respond.ask_how   [20 chars]
    en  How's it been going?
    >>  ............................................
    pt  Como tem sido?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.followthrough.ask_how`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, familiarity +2  _(recorded under topic `fears.followthrough.ask_how`)_
- Does: arc `fears` — advance to stage 3
- Then opens: `conversations.arc.fears.followthrough.followup`
- …where the player's next choices will be: "You've come a long way with it." | "What's the next hard bit?" | "You don't have to be finished." | "I'll not make a thing of it."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.ask_how
WHO    VILLAGER — what the player reads after pressing "How's it been going?"
       spoken on: conversations.arc.fears.followthrough.respond, button `ask_how`
       leaves the player on: conversations.arc.fears.followthrough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.ask_how.to.fears.followthrough`: the villager accepts. Subject `fears.followthrough`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.followthrough.ask_how/1   [60 chars]
    en  Slowly. But slowly is a direction, which is more than I had.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, o que já é mais do que eu tinha.
    >>  ............................................
  dialogue.conversations.fears.followthrough.ask_how/2   [55 chars]
    en  Two bad nights, one fine one. I'll take the arithmetic.
    >>  ............................................
    pt  Duas noites ruins, uma boa. Aceito essa conta.
    >>  ............................................
  dialogue.conversations.fears.followthrough.ask_how/3   [70 chars]
    en  Better than it was. Ask me again next week and we'll see if I'm lying.
    >>  ............................................
    pt  Melhor do que era. Me pergunte semana que vem e a gente vê se estou mentindo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, and I had no direction at all before, %1$s.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, e eu não tinha direção nenhuma antes, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some days I go backwards. I'd rather say that than give you the tidy version.
    >>  ............................................
    pt  Em alguns dias eu volto atrás. Prefiro dizer isso a te dar a versão arrumada.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Some weeks I can't tell, and then somebody asks and I can.
    >>  ............................................
    pt  Está andando. Em algumas semanas eu não noto, e aí alguém pergunta e eu noto.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. Slowly is a direction and it's the only one this sort of thing has.
    >>  ............................................
    pt  Devagar. Devagar é uma direção e é a única que esse tipo de coisa tem.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. It'll take the years it takes; I've stopped arguing with that.
    >>  ............................................
    pt  Algum. Vai levar os anos que levar; parei de discutir com isso.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Not this month, particularly. Over a year, yes.
    >>  ............................................
    pt  Está andando. Neste mês, nem tanto. Ao longo de um ano, sim.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, which is more than I had.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, que é mais do que eu tinha.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Not enough to celebrate and not nothing either.
    >>  ............................................
    pt  Algum progresso. Não o bastante pra comemorar e também não é nada.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's the whole of the report.
    >>  ............................................
    pt  Está andando. É todo o relatório.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, which is more than I had.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, que é mais do que eu tinha.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Not enough to celebrate and not nothing either.
    >>  ............................................
    pt  Algum progresso. Não o bastante pra comemorar e também não é nada.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's the whole of the report.
    >>  ............................................
    pt  Está andando. É todo o relatório.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, %1$s, which is more than I had before you asked.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, %1$s, que é mais do que eu tinha antes de você perguntar.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. And you asking is part of why there's any at all.
    >>  ............................................
    pt  Algum. E você perguntar é parte do porquê existe algum.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. I'd not have told anyone else that.
    >>  ............................................
    pt  Está andando. Eu não teria contado isso a mais ninguém.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, %1$s, which is more than I had before you asked.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, %1$s, que é mais do que eu tinha antes de você perguntar.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. And you asking is part of why there's any at all.
    >>  ............................................
    pt  Algum. E você perguntar é parte do porquê existe algum.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. I'd not have told anyone else that.
    >>  ............................................
    pt  Está andando. Eu não teria contado isso a mais ninguém.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, %1$s, which is more than I had before you asked.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, %1$s, que é mais do que eu tinha antes de você perguntar.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. And you asking is part of why there's any at all.
    >>  ............................................
    pt  Algum. E você perguntar é parte do porquê existe algum.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. I'd not have told anyone else that.
    >>  ............................................
    pt  Está andando. Eu não teria contado isso a mais ninguém.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, and I had no direction at all before, %1$s.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, e eu não tinha direção nenhuma antes, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some days I go backwards. I'd rather say that than give you the tidy version.
    >>  ............................................
    pt  Em alguns dias eu volto atrás. Prefiro dizer isso a te dar a versão arrumada.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Some weeks I can't tell, and then somebody asks and I can.
    >>  ............................................
    pt  Está andando. Em algumas semanas eu não noto, e aí alguém pergunta e eu noto.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, which is more than I had.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, que é mais do que eu tinha.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Not enough to celebrate and not nothing either.
    >>  ............................................
    pt  Algum progresso. Não o bastante pra comemorar e também não é nada.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's the whole of the report.
    >>  ............................................
    pt  Está andando. É todo o relatório.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, which is more than I had.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, que é mais do que eu tinha.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Not enough to celebrate and not nothing either.
    >>  ............................................
    pt  Algum progresso. Não o bastante pra comemorar e também não é nada.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's the whole of the report.
    >>  ............................................
    pt  Está andando. É todo o relatório.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. Not much.
    >>  ............................................
    pt  Algum. Não muito.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's all.
    >>  ............................................
    pt  Está andando. É tudo.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. Slowly is a direction and it's the only one this sort of thing has.
    >>  ............................................
    pt  Devagar. Devagar é uma direção e é a única que esse tipo de coisa tem.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. It'll take the years it takes; I've stopped arguing with that.
    >>  ............................................
    pt  Algum. Vai levar os anos que levar; parei de discutir com isso.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Not this month, particularly. Over a year, yes.
    >>  ............................................
    pt  Está andando. Neste mês, nem tanto. Ao longo de um ano, sim.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. Not much.
    >>  ............................................
    pt  Algum. Não muito.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's all.
    >>  ............................................
    pt  Está andando. É tudo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. Slowly is a direction and it's the only one this sort of thing has.
    >>  ............................................
    pt  Devagar. Devagar é uma direção e é a única que esse tipo de coisa tem.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. It'll take the years it takes; I've stopped arguing with that.
    >>  ............................................
    pt  Algum. Vai levar os anos que levar; parei de discutir com isso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Not this month, particularly. Over a year, yes.
    >>  ............................................
    pt  Está andando. Neste mês, nem tanto. Ao longo de um ano, sim.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly! But slowly is a direction, which is a great deal more than I had.
    >>  ............................................
    pt  Devagar! Mas devagar é uma direção, que é muito mais do que eu tinha.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Glacial, dignified progress. I'll take dignified.
    >>  ............................................
    pt  Algum progresso. Progresso glacial e digno. Eu fico com digno.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving! Not fast. Moving, though, and that's new.
    >>  ............................................
    pt  Está andando! Não rápido. Mas andando, e isso é novo.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly! But slowly is a direction, which is a great deal more than I had.
    >>  ............................................
    pt  Devagar! Mas devagar é uma direção, que é muito mais do que eu tinha.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Glacial, dignified progress. I'll take dignified.
    >>  ............................................
    pt  Algum progresso. Progresso glacial e digno. Eu fico com digno.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving! Not fast. Moving, though, and that's new.
    >>  ............................................
    pt  Está andando! Não rápido. Mas andando, e isso é novo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. Slowly is a direction and it's the only one this sort of thing has.
    >>  ............................................
    pt  Devagar. Devagar é uma direção e é a única que esse tipo de coisa tem.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. It'll take the years it takes; I've stopped arguing with that.
    >>  ............................................
    pt  Algum. Vai levar os anos que levar; parei de discutir com isso.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Not this month, particularly. Over a year, yes.
    >>  ............................................
    pt  Está andando. Neste mês, nem tanto. Ao longo de um ano, sim.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction, and I had no direction at all before, %1$s.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção, e eu não tinha direção nenhuma antes, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some days I go backwards. I'd rather say that than give you the tidy version.
    >>  ............................................
    pt  Em alguns dias eu volto atrás. Prefiro dizer isso a te dar a versão arrumada.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. Some weeks I can't tell, and then somebody asks and I can.
    >>  ............................................
    pt  Está andando. Em algumas semanas eu não noto, e aí alguém pergunta e eu noto.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly. But slowly is a direction.
    >>  ............................................
    pt  Devagar. Mas devagar é uma direção.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some. Not much.
    >>  ............................................
    pt  Algum. Não muito.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving. That's all.
    >>  ............................................
    pt  Está andando. É tudo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly! But slowly is a direction, which is a great deal more than I had.
    >>  ............................................
    pt  Devagar! Mas devagar é uma direção, que é muito mais do que eu tinha.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Glacial, dignified progress. I'll take dignified.
    >>  ............................................
    pt  Algum progresso. Progresso glacial e digno. Eu fico com digno.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving! Not fast. Moving, though, and that's new.
    >>  ............................................
    pt  Está andando! Não rápido. Mas andando, e isso é novo.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.ask_how/1
    en  Slowly! But slowly is a direction, which is a great deal more than I had.
    >>  ............................................
    pt  Devagar! Mas devagar é uma direção, que é muito mais do que eu tinha.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.ask_how/2
    en  Some progress. Glacial, dignified progress. I'll take dignified.
    >>  ............................................
    pt  Algum progresso. Progresso glacial e digno. Eu fico com digno.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.ask_how/3
    en  It's moving! Not fast. Moving, though, and that's new.
    >>  ............................................
    pt  Está andando! Não rápido. Mas andando, e isso é novo.
    >>  ............................................
```

</details>


### Button `celebrate` — "Look at you. That's not nothing."

*stance family `encouragement` · tone `playful` · answers the beat(s) `fears.resume.followthrough.to.fears.followthrough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.followthrough.celebrate` — accepted phrasings: "look at you"; "that is not nothing"; "i am proud of you"; "you have done well"
  - the message must contain one of: `proud`, `nothing`, `look`, `done`
  - scored words: `proud`(1.5), `look`(1.0), `nothing`(1.2), `well`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.respond.celebrate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.respond.celebrate   [32 chars]
    en  Look at you. That's not nothing.
    >>  ............................................
    pt  Olha só você. Isso não é pouca coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `fears.followthrough.celebrate`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, respect +2  _(recorded under topic `fears.followthrough.celebrate`)_
- Does: arc `fears` — advance to stage 3
- Then opens: `conversations.arc.fears.followthrough.followup`
- …where the player's next choices will be: "You've come a long way with it." | "What's the next hard bit?" | "You don't have to be finished." | "I'll not make a thing of it."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.celebrate
WHO    VILLAGER — what the player reads after pressing "Look at you. That's not nothing."
       spoken on: conversations.arc.fears.followthrough.respond, button `celebrate`
       leaves the player on: conversations.arc.fears.followthrough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.celebrate.to.fears.followthrough`: the villager celebrates. Subject `fears.followthrough`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may take almost any stance (11 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.followthrough.celebrate/1   [63 chars]
    en  ...It isn't nothing, is it. I keep telling myself it's nothing.
    >>  ............................................
    pt  ...Não é pouca coisa, né. Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  dialogue.conversations.fears.followthrough.celebrate/2   [49 chars]
    en  Don't make me proud of it, I'll get insufferable.
    >>  ............................................
    pt  Não me deixe orgulhoso disso, vou ficar insuportável.
    >>  ............................................
  dialogue.conversations.fears.followthrough.celebrate/3   [48 chars]
    en  Someone noticing is half of why it worked, %1$s.
    >>  ............................................
    pt  Alguém notar é metade do motivo de ter funcionado, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it. I keep telling myself it's nothing, %1$s, so that it can't be taken back.
    >>  ............................................
    pt  ...Não é nada, é? Eu fico dizendo que não é nada, %1$s, pra não poder ser retirado.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.celebrate/2
    en  If I let it count and then I slip, I'll have lost twice. That's why I've not let it count.
    >>  ............................................
    pt  Se eu deixar contar e aí eu recair, eu perco duas vezes. Por isso eu não deixei contar.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. Right. Give me a moment — I'm not used to being congratulated about this.
    >>  ............................................
    pt  Alguma coisa. Certo. Me dê um momento — não estou acostumado a ser parabenizado por isso.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing. It's small and it's real, and small real things are what last.
    >>  ............................................
    pt  Não é nada. É pequeno e é real, e coisas pequenas e reais são as que duram.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. There'll be more of them and there'll be worse weeks too.
    >>  ............................................
    pt  Certo. Conta. Vai ter mais delas e vai ter semanas piores também.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. I'll take it and not make a great deal of it either way.
    >>  ............................................
    pt  Alguma coisa. Eu aceito e não faço caso demais de nenhum lado.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it. I keep telling myself it's nothing.
    >>  ............................................
    pt  Não é nada, é? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. I'd not have counted it on my own.
    >>  ............................................
    pt  Certo. Conta. Eu não teria contado sozinho.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something, then. I'll allow that.
    >>  ............................................
    pt  Alguma coisa, então. Eu permito.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it. I keep telling myself it's nothing.
    >>  ............................................
    pt  Não é nada, é? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. I'd not have counted it on my own.
    >>  ............................................
    pt  Certo. Conta. Eu não teria contado sozinho.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something, then. I'll allow that.
    >>  ............................................
    pt  Alguma coisa, então. Eu permito.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it, %1$s. I keep telling myself it's nothing.
    >>  ............................................
    pt  ...Não é nada, é, %1$s? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.celebrate/2
    en  You'd call that something. Coming from you, I might believe it.
    >>  ............................................
    pt  Você chamaria isso de algo. Vindo de você, eu talvez acredite.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.celebrate/3
    en  Right. It counts. I needed somebody else to say so before it could.
    >>  ............................................
    pt  Certo. Conta. Eu precisava que outra pessoa dissesse antes que pudesse contar.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it, %1$s. I keep telling myself it's nothing.
    >>  ............................................
    pt  ...Não é nada, é, %1$s? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.celebrate/2
    en  You'd call that something. Coming from you, I might believe it.
    >>  ............................................
    pt  Você chamaria isso de algo. Vindo de você, eu talvez acredite.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.celebrate/3
    en  Right. It counts. I needed somebody else to say so before it could.
    >>  ............................................
    pt  Certo. Conta. Eu precisava que outra pessoa dissesse antes que pudesse contar.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it, %1$s. I keep telling myself it's nothing.
    >>  ............................................
    pt  ...Não é nada, é, %1$s? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.celebrate/2
    en  You'd call that something. Coming from you, I might believe it.
    >>  ............................................
    pt  Você chamaria isso de algo. Vindo de você, eu talvez acredite.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.celebrate/3
    en  Right. It counts. I needed somebody else to say so before it could.
    >>  ............................................
    pt  Certo. Conta. Eu precisava que outra pessoa dissesse antes que pudesse contar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it. I keep telling myself it's nothing, %1$s, so that it can't be taken back.
    >>  ............................................
    pt  ...Não é nada, é? Eu fico dizendo que não é nada, %1$s, pra não poder ser retirado.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.celebrate/2
    en  If I let it count and then I slip, I'll have lost twice. That's why I've not let it count.
    >>  ............................................
    pt  Se eu deixar contar e aí eu recair, eu perco duas vezes. Por isso eu não deixei contar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. Right. Give me a moment — I'm not used to being congratulated about this.
    >>  ............................................
    pt  Alguma coisa. Certo. Me dê um momento — não estou acostumado a ser parabenizado por isso.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it. I keep telling myself it's nothing.
    >>  ............................................
    pt  Não é nada, é? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. I'd not have counted it on my own.
    >>  ............................................
    pt  Certo. Conta. Eu não teria contado sozinho.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something, then. I'll allow that.
    >>  ............................................
    pt  Alguma coisa, então. Eu permito.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it. I keep telling myself it's nothing.
    >>  ............................................
    pt  Não é nada, é? Eu fico dizendo a mim mesmo que não é nada.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. I'd not have counted it on my own.
    >>  ............................................
    pt  Certo. Conta. Eu não teria contado sozinho.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something, then. I'll allow that.
    >>  ............................................
    pt  Alguma coisa, então. Eu permito.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it.
    >>  ............................................
    pt  ...Não é nada, é?
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts.
    >>  ............................................
    pt  Certo. Conta.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.celebrate/3
    en  ...Something, then.
    >>  ............................................
    pt  ...Alguma coisa, então.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing. It's small and it's real, and small real things are what last.
    >>  ............................................
    pt  Não é nada. É pequeno e é real, e coisas pequenas e reais são as que duram.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. There'll be more of them and there'll be worse weeks too.
    >>  ............................................
    pt  Certo. Conta. Vai ter mais delas e vai ter semanas piores também.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. I'll take it and not make a great deal of it either way.
    >>  ............................................
    pt  Alguma coisa. Eu aceito e não faço caso demais de nenhum lado.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it.
    >>  ............................................
    pt  ...Não é nada, é?
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts.
    >>  ............................................
    pt  Certo. Conta.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.celebrate/3
    en  ...Something, then.
    >>  ............................................
    pt  ...Alguma coisa, então.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing. It's small and it's real, and small real things are what last.
    >>  ............................................
    pt  Não é nada. É pequeno e é real, e coisas pequenas e reais são as que duram.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. There'll be more of them and there'll be worse weeks too.
    >>  ............................................
    pt  Certo. Conta. Vai ter mais delas e vai ter semanas piores também.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. I'll take it and not make a great deal of it either way.
    >>  ............................................
    pt  Alguma coisa. Eu aceito e não faço caso demais de nenhum lado.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it! I keep telling myself it's nothing. It's clearly not nothing.
    >>  ............................................
    pt  Não é nada, é! Eu fico dizendo a mim mesmo que não é nada. Claramente não é nada.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right! It counts! I've been refusing to let it count for about a month.
    >>  ............................................
    pt  Certo! Conta! Eu venho me recusando a deixar contar há um mês.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something! Ha. I'll be unbearably pleased about this later, in private.
    >>  ............................................
    pt  Alguma coisa! Ha. Vou ficar insuportavelmente contente depois, em particular.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it! I keep telling myself it's nothing. It's clearly not nothing.
    >>  ............................................
    pt  Não é nada, é! Eu fico dizendo a mim mesmo que não é nada. Claramente não é nada.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right! It counts! I've been refusing to let it count for about a month.
    >>  ............................................
    pt  Certo! Conta! Eu venho me recusando a deixar contar há um mês.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something! Ha. I'll be unbearably pleased about this later, in private.
    >>  ............................................
    pt  Alguma coisa! Ha. Vou ficar insuportavelmente contente depois, em particular.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing. It's small and it's real, and small real things are what last.
    >>  ............................................
    pt  Não é nada. É pequeno e é real, e coisas pequenas e reais são as que duram.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts. There'll be more of them and there'll be worse weeks too.
    >>  ............................................
    pt  Certo. Conta. Vai ter mais delas e vai ter semanas piores também.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. I'll take it and not make a great deal of it either way.
    >>  ............................................
    pt  Alguma coisa. Eu aceito e não faço caso demais de nenhum lado.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it. I keep telling myself it's nothing, %1$s, so that it can't be taken back.
    >>  ............................................
    pt  ...Não é nada, é? Eu fico dizendo que não é nada, %1$s, pra não poder ser retirado.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.celebrate/2
    en  If I let it count and then I slip, I'll have lost twice. That's why I've not let it count.
    >>  ............................................
    pt  Se eu deixar contar e aí eu recair, eu perco duas vezes. Por isso eu não deixei contar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something. Right. Give me a moment — I'm not used to being congratulated about this.
    >>  ............................................
    pt  Alguma coisa. Certo. Me dê um momento — não estou acostumado a ser parabenizado por isso.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.celebrate/1
    en  ...It isn't nothing, is it.
    >>  ............................................
    pt  ...Não é nada, é?
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right. It counts.
    >>  ............................................
    pt  Certo. Conta.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.celebrate/3
    en  ...Something, then.
    >>  ............................................
    pt  ...Alguma coisa, então.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it! I keep telling myself it's nothing. It's clearly not nothing.
    >>  ............................................
    pt  Não é nada, é! Eu fico dizendo a mim mesmo que não é nada. Claramente não é nada.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right! It counts! I've been refusing to let it count for about a month.
    >>  ............................................
    pt  Certo! Conta! Eu venho me recusando a deixar contar há um mês.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something! Ha. I'll be unbearably pleased about this later, in private.
    >>  ............................................
    pt  Alguma coisa! Ha. Vou ficar insuportavelmente contente depois, em particular.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.celebrate/1
    en  It isn't nothing, is it! I keep telling myself it's nothing. It's clearly not nothing.
    >>  ............................................
    pt  Não é nada, é! Eu fico dizendo a mim mesmo que não é nada. Claramente não é nada.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.celebrate/2
    en  Right! It counts! I've been refusing to let it count for about a month.
    >>  ............................................
    pt  Certo! Conta! Eu venho me recusando a deixar contar há um mês.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.celebrate/3
    en  Something! Ha. I'll be unbearably pleased about this later, in private.
    >>  ............................................
    pt  Alguma coisa! Ha. Vou ficar insuportavelmente contente depois, em particular.
    >>  ............................................
```

</details>


### Button `recall_promise` — "I meant what I said, back then."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `fears.resume.followthrough.to.fears.followthrough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.followthrough.recall_promise` — accepted phrasings: "i meant what i said"; "i meant it back then"; "i stand by what i said"
  - the message must contain one of: `meant`, `said`, `words`
  - scored words: `meant`(1.5), `said`(1.2), `then`(0.6), `back`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.respond.recall_promise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.respond.recall_promise   [31 chars]
    en  I meant what I said, back then.
    >>  ............................................
    pt  Eu falei sério naquele dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +100 when exclusive `fears.support` is `pledged`
- Fires when: RULED OUT when LACKS the memory `mcaconversations.pledge.fears` (this player only)  _(chance -2000)_
- Does: **hearts +1** — decision id `fears.followthrough.recall_promise`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `fears.followthrough.recall_promise`)_
- Does: arc `fears` — hold
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.recall.pledged
WHO    VILLAGER — what the player reads after pressing "I meant what I said, back then."
       spoken on: conversations.arc.fears.followthrough.respond, button `recall_promise`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.recall.pledged.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.followthrough.recall.pledged/1   [74 chars]
    en  You did say that. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse mesmo. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.pledged/2   [67 chars]
    en  I know. I've been keeping the receipt, %1$s. You're square with me.
    >>  ............................................
    pt  Eu sei. Guardei o recibo, %1$s. Estamos quites.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.pledged/3   [58 chars]
    en  Not alone, you said. It turns out that was the useful bit.
    >>  ............................................
    pt  Não sozinho, você disse. No fim das contas era essa a parte útil.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, %1$s, which is the half I'd stopped expecting.
    >>  ............................................
    pt  Você disse isso. E apareceu, %1$s, que é a metade que eu tinha parado de esperar.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  I remember the exact evening you said it. I'd not admit that to anybody else.
    >>  ............................................
    pt  Eu lembro da noite exata em que você disse. Não admitiria isso a mais ninguém.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Both halves. I'd made a small plan for what to do when only the first one arrived.
    >>  ............................................
    pt  As duas metades. Eu tinha um plano pequeno pro caso de só a primeira chegar.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, and you turned up. Both, in order, without hurrying either.
    >>  ............................................
    pt  Você disse isso, e apareceu. As duas, em ordem, sem apressar nenhuma.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said and done. That's how the things that hold get built.
    >>  ............................................
    pt  Dito e feito. É assim que se constrói o que se mantém.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. The rarer half. It'll be the reason this one lasts.
    >>  ............................................
    pt  Certo. A metade mais rara. Vai ser a razão de isto durar.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and then you did it. I've had the first half from a great many people.
    >>  ............................................
    pt  Você disse e depois fez. Já tive a primeira metade de muita gente.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. Said and done. That's the order it's supposed to go in.
    >>  ............................................
    pt  Certo. Dito e feito. É a ordem em que deveria ir.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and then you did it. I've had the first half from a great many people.
    >>  ............................................
    pt  Você disse e depois fez. Já tive a primeira metade de muita gente.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. Said and done. That's the order it's supposed to go in.
    >>  ............................................
    pt  Certo. Dito e feito. É a ordem em que deveria ir.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, %1$s. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso, %1$s. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and you meant it. I'd have forgiven you for only meaning it at the time.
    >>  ............................................
    pt  Você disse e falou sério. Eu teria perdoado se fosse sério só naquela hora.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Both halves. I'd like you to know that I noticed both.
    >>  ............................................
    pt  As duas metades. Queria que você soubesse que eu reparei nas duas.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, %1$s. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso, %1$s. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and you meant it. I'd have forgiven you for only meaning it at the time.
    >>  ............................................
    pt  Você disse e falou sério. Eu teria perdoado se fosse sério só naquela hora.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Both halves. I'd like you to know that I noticed both.
    >>  ............................................
    pt  As duas metades. Queria que você soubesse que eu reparei nas duas.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, %1$s. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso, %1$s. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and you meant it. I'd have forgiven you for only meaning it at the time.
    >>  ............................................
    pt  Você disse e falou sério. Eu teria perdoado se fosse sério só naquela hora.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Both halves. I'd like you to know that I noticed both.
    >>  ............................................
    pt  As duas metades. Queria que você soubesse que eu reparei nas duas.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, %1$s, which is the half I'd stopped expecting.
    >>  ............................................
    pt  Você disse isso. E apareceu, %1$s, que é a metade que eu tinha parado de esperar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  I remember the exact evening you said it. I'd not admit that to anybody else.
    >>  ............................................
    pt  Eu lembro da noite exata em que você disse. Não admitiria isso a mais ninguém.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Both halves. I'd made a small plan for what to do when only the first one arrived.
    >>  ............................................
    pt  As duas metades. Eu tinha um plano pequeno pro caso de só a primeira chegar.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and then you did it. I've had the first half from a great many people.
    >>  ............................................
    pt  Você disse e depois fez. Já tive a primeira metade de muita gente.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. Said and done. That's the order it's supposed to go in.
    >>  ............................................
    pt  Certo. Dito e feito. É a ordem em que deveria ir.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, which is the rarer half of a promise.
    >>  ............................................
    pt  Você disse isso. E apareceu, que é a metade mais rara de uma promessa.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  You said it and then you did it. I've had the first half from a great many people.
    >>  ............................................
    pt  Você disse e depois fez. Já tive a primeira metade de muita gente.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. Said and done. That's the order it's supposed to go in.
    >>  ............................................
    pt  Certo. Dito e feito. É a ordem em que deveria ir.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up.
    >>  ............................................
    pt  Você disse isso. E apareceu.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said, and then done. That's the rarer order.
    >>  ............................................
    pt  Dito, e depois feito. É a ordem mais rara.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. You came back.
    >>  ............................................
    pt  Certo. Você voltou.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, and you turned up. Both, in order, without hurrying either.
    >>  ............................................
    pt  Você disse isso, e apareceu. As duas, em ordem, sem apressar nenhuma.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said and done. That's how the things that hold get built.
    >>  ............................................
    pt  Dito e feito. É assim que se constrói o que se mantém.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. The rarer half. It'll be the reason this one lasts.
    >>  ............................................
    pt  Certo. A metade mais rara. Vai ser a razão de isto durar.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up.
    >>  ............................................
    pt  Você disse isso. E apareceu.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said, and then done. That's the rarer order.
    >>  ............................................
    pt  Dito, e depois feito. É a ordem mais rara.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. You came back.
    >>  ............................................
    pt  Certo. Você voltou.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, and you turned up. Both, in order, without hurrying either.
    >>  ............................................
    pt  Você disse isso, e apareceu. As duas, em ordem, sem apressar nenhuma.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said and done. That's how the things that hold get built.
    >>  ............................................
    pt  Dito e feito. É assim que se constrói o que se mantém.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. The rarer half. It'll be the reason this one lasts.
    >>  ............................................
    pt  Certo. A metade mais rara. Vai ser a razão de isto durar.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that! And you turned up. Both halves! Nobody manages both halves.
    >>  ............................................
    pt  Você disse isso! E apareceu. As duas metades! Ninguém consegue as duas.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said it AND did it. I'm going to tell people about this. Not the fear part. The you part.
    >>  ............................................
    pt  Disse E fez. Vou contar isso pras pessoas. Não a parte do medo. A parte de você.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right — the rarer half. Everyone does the promising. Almost nobody does the arriving.
    >>  ............................................
    pt  Certo — a metade mais rara. Todo mundo promete. Quase ninguém chega.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that! And you turned up. Both halves! Nobody manages both halves.
    >>  ............................................
    pt  Você disse isso! E apareceu. As duas metades! Ninguém consegue as duas.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said it AND did it. I'm going to tell people about this. Not the fear part. The you part.
    >>  ............................................
    pt  Disse E fez. Vou contar isso pras pessoas. Não a parte do medo. A parte de você.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right — the rarer half. Everyone does the promising. Almost nobody does the arriving.
    >>  ............................................
    pt  Certo — a metade mais rara. Todo mundo promete. Quase ninguém chega.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that, and you turned up. Both, in order, without hurrying either.
    >>  ............................................
    pt  Você disse isso, e apareceu. As duas, em ordem, sem apressar nenhuma.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said and done. That's how the things that hold get built.
    >>  ............................................
    pt  Dito e feito. É assim que se constrói o que se mantém.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. The rarer half. It'll be the reason this one lasts.
    >>  ............................................
    pt  Certo. A metade mais rara. Vai ser a razão de isto durar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up, %1$s, which is the half I'd stopped expecting.
    >>  ............................................
    pt  Você disse isso. E apareceu, %1$s, que é a metade que eu tinha parado de esperar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  I remember the exact evening you said it. I'd not admit that to anybody else.
    >>  ............................................
    pt  Eu lembro da noite exata em que você disse. Não admitiria isso a mais ninguém.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Both halves. I'd made a small plan for what to do when only the first one arrived.
    >>  ............................................
    pt  As duas metades. Eu tinha um plano pequeno pro caso de só a primeira chegar.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that. And you turned up.
    >>  ............................................
    pt  Você disse isso. E apareceu.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said, and then done. That's the rarer order.
    >>  ............................................
    pt  Dito, e depois feito. É a ordem mais rara.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right. You came back.
    >>  ............................................
    pt  Certo. Você voltou.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that! And you turned up. Both halves! Nobody manages both halves.
    >>  ............................................
    pt  Você disse isso! E apareceu. As duas metades! Ninguém consegue as duas.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said it AND did it. I'm going to tell people about this. Not the fear part. The you part.
    >>  ............................................
    pt  Disse E fez. Vou contar isso pras pessoas. Não a parte do medo. A parte de você.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right — the rarer half. Everyone does the promising. Almost nobody does the arriving.
    >>  ............................................
    pt  Certo — a metade mais rara. Todo mundo promete. Quase ninguém chega.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.pledged/1
    en  You did say that! And you turned up. Both halves! Nobody manages both halves.
    >>  ............................................
    pt  Você disse isso! E apareceu. As duas metades! Ninguém consegue as duas.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.pledged/2
    en  Said it AND did it. I'm going to tell people about this. Not the fear part. The you part.
    >>  ............................................
    pt  Disse E fez. Vou contar isso pras pessoas. Não a parte do medo. A parte de você.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.pledged/3
    en  Right — the rarer half. Everyone does the promising. Almost nobody does the arriving.
    >>  ............................................
    pt  Certo — a metade mais rara. Todo mundo promete. Quase ninguém chega.
    >>  ............................................
```

</details>


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +100 when exclusive `fears.support` is `pledged`
- Fires when: RULED OUT when has the memory `mcaconversations.pledge.fears` (this player only)  _(chance -2000)_
- Does: disposition — trust -3, tension +4  _(recorded under topic `fears.followthrough.recall_promise`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.lapsed`
- …where the player's next choices will be: "You're right. I wasn't there." | "Then let me be there now." | "I'll not make excuses."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.recall.lapsed
WHO    VILLAGER — what the player reads after pressing "I meant what I said, back then."
       spoken on: conversations.arc.fears.followthrough.respond, button `recall_promise`
       leaves the player on: conversations.topic.fears.lapsed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.recall.lapsed.to.fears.lapsed`: the villager accepts. Subject `fears.lapsed`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.followthrough.recall.lapsed/1   [92 chars]
    en  You did say that. ...It's been a while since, mind. I'd started filling in the blank myself.
    >>  ............................................
    pt  Você disse isso mesmo. ...Faz um tempo, veja bem. Eu já tinha começado a preencher o vazio sozinho.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.lapsed/2   [97 chars]
    en  I remember the promise, %1$s. I remember the days after it, too, and you weren't in many of them.
    >>  ............................................
    pt  Eu lembro da promessa, %1$s. Lembro dos dias seguintes também, e você não esteve em muitos deles.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.lapsed/3   [75 chars]
    en  Just so, you said it. People do say it. I'd stopped counting on it, is all.
    >>  ............................................
    pt  Pois é, você disse. As pessoas dizem. Eu só tinha parado de contar com isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, %1$s, and I counted the while.
    >>  ............................................
    pt  Você disse isso. Faz um tempo, %1$s, e eu contei o tempo.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  I'd started filling the gap myself. That's not a complaint, it's just what happened.
    >>  ............................................
    pt  Eu tinha começado a preencher a lacuna sozinho. Não é queixa, é só o que aconteceu.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  You said it. I believed it. Then I learned to manage without it, which took longer.
    >>  ............................................
    pt  Você disse. Eu acreditei. Depois aprendi a me virar sem, o que levou mais tempo.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while. Things drift; I'd not make much of it.
    >>  ............................................
    pt  Você disse isso. Faz um tempo. As coisas escorrem; eu não faria caso.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it once. Once is enough to be worth returning to, which is what you've done.
    >>  ............................................
    pt  Você disse uma vez. Uma vez basta pra valer voltar, e é o que você fez.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago. But here we are again, and that's the part that matters.
    >>  ............................................
    pt  Certo. Faz tempo. Mas aqui estamos de novo, e é essa a parte que importa.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso. Mas faz um tempo.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then the months went by and I stopped counting on it.
    >>  ............................................
    pt  Você disse. Aí os meses passaram e eu parei de contar com isso.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. You said it once. Saying it twice is what would change something.
    >>  ............................................
    pt  Certo. Você disse uma vez. Dizer duas é o que mudaria algo.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso. Mas faz um tempo.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then the months went by and I stopped counting on it.
    >>  ............................................
    pt  Você disse. Aí os meses passaram e eu parei de contar com isso.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. You said it once. Saying it twice is what would change something.
    >>  ............................................
    pt  Certo. Você disse uma vez. Dizer duas é o que mudaria algo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that, %1$s. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso, %1$s. Mas faz um tempo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it and then you were gone a while. I'm not holding it against you.
    >>  ............................................
    pt  Você disse e depois sumiu um tempo. Não estou guardando mágoa.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. I'd started filling the gap myself, which is what one does.
    >>  ............................................
    pt  Certo. Eu tinha começado a preencher a lacuna sozinho, que é o que se faz.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that, %1$s. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso, %1$s. Mas faz um tempo.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it and then you were gone a while. I'm not holding it against you.
    >>  ............................................
    pt  Você disse e depois sumiu um tempo. Não estou guardando mágoa.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. I'd started filling the gap myself, which is what one does.
    >>  ............................................
    pt  Certo. Eu tinha começado a preencher a lacuna sozinho, que é o que se faz.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that, %1$s. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso, %1$s. Mas faz um tempo.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it and then you were gone a while. I'm not holding it against you.
    >>  ............................................
    pt  Você disse e depois sumiu um tempo. Não estou guardando mágoa.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. I'd started filling the gap myself, which is what one does.
    >>  ............................................
    pt  Certo. Eu tinha começado a preencher a lacuna sozinho, que é o que se faz.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, %1$s, and I counted the while.
    >>  ............................................
    pt  Você disse isso. Faz um tempo, %1$s, e eu contei o tempo.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  I'd started filling the gap myself. That's not a complaint, it's just what happened.
    >>  ............................................
    pt  Eu tinha começado a preencher a lacuna sozinho. Não é queixa, é só o que aconteceu.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  You said it. I believed it. Then I learned to manage without it, which took longer.
    >>  ............................................
    pt  Você disse. Eu acreditei. Depois aprendi a me virar sem, o que levou mais tempo.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso. Mas faz um tempo.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then the months went by and I stopped counting on it.
    >>  ............................................
    pt  Você disse. Aí os meses passaram e eu parei de contar com isso.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. You said it once. Saying it twice is what would change something.
    >>  ............................................
    pt  Certo. Você disse uma vez. Dizer duas é o que mudaria algo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, mind.
    >>  ............................................
    pt  Você disse isso. Mas faz um tempo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then the months went by and I stopped counting on it.
    >>  ............................................
    pt  Você disse. Aí os meses passaram e eu parei de contar com isso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. You said it once. Saying it twice is what would change something.
    >>  ............................................
    pt  Certo. Você disse uma vez. Dizer duas é o que mudaria algo.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while.
    >>  ............................................
    pt  Você disse isso. Faz um tempo.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  I remember. I'd started filling the gap myself.
    >>  ............................................
    pt  Eu lembro. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago, though.
    >>  ............................................
    pt  Certo. Mas foi faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while. Things drift; I'd not make much of it.
    >>  ............................................
    pt  Você disse isso. Faz um tempo. As coisas escorrem; eu não faria caso.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it once. Once is enough to be worth returning to, which is what you've done.
    >>  ............................................
    pt  Você disse uma vez. Uma vez basta pra valer voltar, e é o que você fez.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago. But here we are again, and that's the part that matters.
    >>  ............................................
    pt  Certo. Faz tempo. Mas aqui estamos de novo, e é essa a parte que importa.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while.
    >>  ............................................
    pt  Você disse isso. Faz um tempo.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  I remember. I'd started filling the gap myself.
    >>  ............................................
    pt  Eu lembro. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago, though.
    >>  ............................................
    pt  Certo. Mas foi faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while. Things drift; I'd not make much of it.
    >>  ............................................
    pt  Você disse isso. Faz um tempo. As coisas escorrem; eu não faria caso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it once. Once is enough to be worth returning to, which is what you've done.
    >>  ............................................
    pt  Você disse uma vez. Uma vez basta pra valer voltar, e é o que você fez.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago. But here we are again, and that's the part that matters.
    >>  ............................................
    pt  Certo. Faz tempo. Mas aqui estamos de novo, e é essa a parte que importa.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that! It's been a while, mind. I'd started filling in the gap myself.
    >>  ............................................
    pt  Você disse isso! Mas faz um tempo. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then several months happened. Good months, busy months, absent months.
    >>  ............................................
    pt  Você disse. Aí vários meses aconteceram. Meses bons, meses ocupados, meses ausentes.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right — you did. I have an excellent memory and a very patient nature, apparently.
    >>  ............................................
    pt  Certo — você disse. Eu tenho memória excelente e natureza muito paciente, aparentemente.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that! It's been a while, mind. I'd started filling in the gap myself.
    >>  ............................................
    pt  Você disse isso! Mas faz um tempo. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then several months happened. Good months, busy months, absent months.
    >>  ............................................
    pt  Você disse. Aí vários meses aconteceram. Meses bons, meses ocupados, meses ausentes.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right — you did. I have an excellent memory and a very patient nature, apparently.
    >>  ............................................
    pt  Certo — você disse. Eu tenho memória excelente e natureza muito paciente, aparentemente.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while. Things drift; I'd not make much of it.
    >>  ............................................
    pt  Você disse isso. Faz um tempo. As coisas escorrem; eu não faria caso.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it once. Once is enough to be worth returning to, which is what you've done.
    >>  ............................................
    pt  Você disse uma vez. Uma vez basta pra valer voltar, e é o que você fez.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago. But here we are again, and that's the part that matters.
    >>  ............................................
    pt  Certo. Faz tempo. Mas aqui estamos de novo, e é essa a parte que importa.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while since, %1$s, and I counted the while.
    >>  ............................................
    pt  Você disse isso. Faz um tempo, %1$s, e eu contei o tempo.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  I'd started filling the gap myself. That's not a complaint, it's just what happened.
    >>  ............................................
    pt  Eu tinha começado a preencher a lacuna sozinho. Não é queixa, é só o que aconteceu.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  You said it. I believed it. Then I learned to manage without it, which took longer.
    >>  ............................................
    pt  Você disse. Eu acreditei. Depois aprendi a me virar sem, o que levou mais tempo.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that. It's been a while.
    >>  ............................................
    pt  Você disse isso. Faz um tempo.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  I remember. I'd started filling the gap myself.
    >>  ............................................
    pt  Eu lembro. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right. A while ago, though.
    >>  ............................................
    pt  Certo. Mas foi faz tempo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that! It's been a while, mind. I'd started filling in the gap myself.
    >>  ............................................
    pt  Você disse isso! Mas faz um tempo. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then several months happened. Good months, busy months, absent months.
    >>  ............................................
    pt  Você disse. Aí vários meses aconteceram. Meses bons, meses ocupados, meses ausentes.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right — you did. I have an excellent memory and a very patient nature, apparently.
    >>  ............................................
    pt  Certo — você disse. Eu tenho memória excelente e natureza muito paciente, aparentemente.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.lapsed/1
    en  You did say that! It's been a while, mind. I'd started filling in the gap myself.
    >>  ............................................
    pt  Você disse isso! Mas faz um tempo. Eu tinha começado a preencher a lacuna sozinho.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.lapsed/2
    en  You said it. Then several months happened. Good months, busy months, absent months.
    >>  ............................................
    pt  Você disse. Aí vários meses aconteceram. Meses bons, meses ocupados, meses ausentes.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.lapsed/3
    en  Right — you did. I have an excellent memory and a very patient nature, apparently.
    >>  ............................................
    pt  Certo — você disse. Eu tenho memória excelente e natureza muito paciente, aparentemente.
    >>  ............................................
```

</details>


**Outcome 3 of 4** — base weight `0`

- Fires when: weighted +100 when exclusive `fears.support` is `stepped_back`
- Does: disposition — respect +2  _(recorded under topic `fears.followthrough.recall_promise`)_
- Does: arc `fears` — hold
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.recall.stepped_back
WHO    VILLAGER — what the player reads after pressing "I meant what I said, back then."
       spoken on: conversations.arc.fears.followthrough.respond, button `recall_promise`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.recall.stepped_back.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.followthrough.recall.stepped_back/1   [78 chars]
    en  You didn't promise anything. I remember. That's why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. É por isso que acreditei no resto.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.stepped_back/2   [69 chars]
    en  You were honest instead of kind. It wore better than kind would have.
    >>  ............................................
    pt  Você foi honesto em vez de gentil. Envelheceu melhor do que gentil teria envelhecido.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.stepped_back/3   [60 chars]
    en  No promises from you, %1$s. And here you are anyway, asking.
    >>  ............................................
    pt  Nenhuma promessa da sua parte, %1$s. E mesmo assim aqui está, perguntando.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember, %1$s. That's why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro, %1$s. Por isso eu acreditei no resto.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  Stepping back was the kindest thing you could have done and I don't think you knew.
    >>  ............................................
    pt  Recuar foi a coisa mais gentil que você podia ter feito e eu acho que você não sabia.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  You could have said anything that night. You said the careful thing instead.
    >>  ............................................
    pt  Você podia ter dito qualquer coisa naquela noite. Disse a coisa cuidadosa em vez disso.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. Careful words wear better than large ones.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Palavras cuidadosas duram mais que grandes.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's held up over the months rather better than a promise would have.
    >>  ............................................
    pt  Você recuou. Isso se manteve ao longo dos meses melhor que uma promessa se manteria.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise, and no promise broken. That's a good year's worth of nothing going wrong.
    >>  ............................................
    pt  Certo. Sem promessa, e sem promessa quebrada. É um bom ano de nada dando errado.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back instead of overreaching. I've thought about that since.
    >>  ............................................
    pt  Você recuou em vez de se estender demais. Pensei nisso desde então.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. You said what you could do and not what you couldn't.
    >>  ............................................
    pt  Certo. Você disse o que podia fazer e não o que não podia.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back instead of overreaching. I've thought about that since.
    >>  ............................................
    pt  Você recuou em vez de se estender demais. Pensei nisso desde então.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. You said what you could do and not what you couldn't.
    >>  ............................................
    pt  Certo. Você disse o que podia fazer e não o que não podia.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything, %1$s. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada, %1$s. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back rather than say a thing you weren't sure of. I noticed that.
    >>  ............................................
    pt  Você recuou em vez de dizer algo de que não tinha certeza. Eu reparei.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. And it's the reason I've told you more since than I've told anybody.
    >>  ............................................
    pt  Certo. E é a razão de eu ter te contado mais desde então do que contei a qualquer um.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything, %1$s. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada, %1$s. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back rather than say a thing you weren't sure of. I noticed that.
    >>  ............................................
    pt  Você recuou em vez de dizer algo de que não tinha certeza. Eu reparei.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. And it's the reason I've told you more since than I've told anybody.
    >>  ............................................
    pt  Certo. E é a razão de eu ter te contado mais desde então do que contei a qualquer um.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything, %1$s. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada, %1$s. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back rather than say a thing you weren't sure of. I noticed that.
    >>  ............................................
    pt  Você recuou em vez de dizer algo de que não tinha certeza. Eu reparei.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. And it's the reason I've told you more since than I've told anybody.
    >>  ............................................
    pt  Certo. E é a razão de eu ter te contado mais desde então do que contei a qualquer um.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember, %1$s. That's why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro, %1$s. Por isso eu acreditei no resto.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  Stepping back was the kindest thing you could have done and I don't think you knew.
    >>  ............................................
    pt  Recuar foi a coisa mais gentil que você podia ter feito e eu acho que você não sabia.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  You could have said anything that night. You said the careful thing instead.
    >>  ............................................
    pt  Você podia ter dito qualquer coisa naquela noite. Disse a coisa cuidadosa em vez disso.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back instead of overreaching. I've thought about that since.
    >>  ............................................
    pt  Você recuou em vez de se estender demais. Pensei nisso desde então.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. You said what you could do and not what you couldn't.
    >>  ............................................
    pt  Certo. Você disse o que podia fazer e não o que não podia.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. That's why I believed the rest.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Por isso eu acreditei no resto.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back instead of overreaching. I've thought about that since.
    >>  ............................................
    pt  Você recuou em vez de se estender demais. Pensei nisso desde então.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. You said what you could do and not what you couldn't.
    >>  ............................................
    pt  Certo. Você disse o que podia fazer e não o que não podia.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's why I believed the rest.
    >>  ............................................
    pt  Você recuou. Por isso eu acreditei no resto.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise. Good.
    >>  ............................................
    pt  Certo. Sem promessa. Bom.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. Careful words wear better than large ones.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Palavras cuidadosas duram mais que grandes.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's held up over the months rather better than a promise would have.
    >>  ............................................
    pt  Você recuou. Isso se manteve ao longo dos meses melhor que uma promessa se manteria.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise, and no promise broken. That's a good year's worth of nothing going wrong.
    >>  ............................................
    pt  Certo. Sem promessa, e sem promessa quebrada. É um bom ano de nada dando errado.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's why I believed the rest.
    >>  ............................................
    pt  Você recuou. Por isso eu acreditei no resto.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise. Good.
    >>  ............................................
    pt  Certo. Sem promessa. Bom.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. Careful words wear better than large ones.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Palavras cuidadosas duram mais que grandes.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's held up over the months rather better than a promise would have.
    >>  ............................................
    pt  Você recuou. Isso se manteve ao longo dos meses melhor que uma promessa se manteria.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise, and no promise broken. That's a good year's worth of nothing going wrong.
    >>  ............................................
    pt  Certo. Sem promessa, e sem promessa quebrada. É um bom ano de nada dando errado.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything! I remember. That's exactly why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada! Eu lembro. É exatamente por isso que eu acreditei no resto.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. Sensibly! Everyone else would have promised the moon on a rope.
    >>  ............................................
    pt  Você recuou. Sensatamente! Todo mundo teria prometido a lua numa corda.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right — no promise. It's the most trustworthy thing anyone's ever not said to me.
    >>  ............................................
    pt  Certo — sem promessa. É a coisa mais confiável que alguém já não me disse.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything! I remember. That's exactly why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada! Eu lembro. É exatamente por isso que eu acreditei no resto.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. Sensibly! Everyone else would have promised the moon on a rope.
    >>  ............................................
    pt  Você recuou. Sensatamente! Todo mundo teria prometido a lua numa corda.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right — no promise. It's the most trustworthy thing anyone's ever not said to me.
    >>  ............................................
    pt  Certo — sem promessa. É a coisa mais confiável que alguém já não me disse.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember. Careful words wear better than large ones.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro. Palavras cuidadosas duram mais que grandes.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's held up over the months rather better than a promise would have.
    >>  ............................................
    pt  Você recuou. Isso se manteve ao longo dos meses melhor que uma promessa se manteria.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise, and no promise broken. That's a good year's worth of nothing going wrong.
    >>  ............................................
    pt  Certo. Sem promessa, e sem promessa quebrada. É um bom ano de nada dando errado.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember, %1$s. That's why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro, %1$s. Por isso eu acreditei no resto.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  Stepping back was the kindest thing you could have done and I don't think you knew.
    >>  ............................................
    pt  Recuar foi a coisa mais gentil que você podia ter feito e eu acho que você não sabia.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  You could have said anything that night. You said the careful thing instead.
    >>  ............................................
    pt  Você podia ter dito qualquer coisa naquela noite. Disse a coisa cuidadosa em vez disso.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything. I remember.
    >>  ............................................
    pt  Você não prometeu nada. Eu lembro.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. That's why I believed the rest.
    >>  ............................................
    pt  Você recuou. Por isso eu acreditei no resto.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right. No promise. Good.
    >>  ............................................
    pt  Certo. Sem promessa. Bom.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything! I remember. That's exactly why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada! Eu lembro. É exatamente por isso que eu acreditei no resto.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. Sensibly! Everyone else would have promised the moon on a rope.
    >>  ............................................
    pt  Você recuou. Sensatamente! Todo mundo teria prometido a lua numa corda.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right — no promise. It's the most trustworthy thing anyone's ever not said to me.
    >>  ............................................
    pt  Certo — sem promessa. É a coisa mais confiável que alguém já não me disse.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.stepped_back/1
    en  You didn't promise anything! I remember. That's exactly why I believed the rest of it.
    >>  ............................................
    pt  Você não prometeu nada! Eu lembro. É exatamente por isso que eu acreditei no resto.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.stepped_back/2
    en  You stepped back. Sensibly! Everyone else would have promised the moon on a rope.
    >>  ............................................
    pt  Você recuou. Sensatamente! Todo mundo teria prometido a lua numa corda.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.stepped_back/3
    en  Right — no promise. It's the most trustworthy thing anyone's ever not said to me.
    >>  ............................................
    pt  Certo — sem promessa. É a coisa mais confiável que alguém já não me disse.
    >>  ............................................
```

</details>


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when exclusive `fears.support` is `pledged`  _(chance -2000)_
- Fires when: RULED OUT when exclusive `fears.support` is `stepped_back`  _(chance -2000)_
- Does: disposition — familiarity +2  _(recorded under topic `fears.followthrough.recall_promise`)_
- Does: arc `fears` — hold
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.recall.plain
WHO    VILLAGER — what the player reads after pressing "I meant what I said, back then."
       spoken on: conversations.arc.fears.followthrough.respond, button `recall_promise`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.recall.plain.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.followthrough.recall.plain/1   [94 chars]
    en  There wasn't a promise, %1$s. You've just kept turning up, which I've decided counts for more.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo, e eu decidi que isso vale mais.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.plain/2   [95 chars]
    en  I don't recall you swearing to anything. You've simply been about. That's its own sort of word.
    >>  ............................................
    pt  Não me lembro de você jurar nada. Você simplesmente esteve por perto. É um tipo de palavra também.
    >>  ............................................
  dialogue.conversations.fears.followthrough.recall.plain/3   [83 chars]
    en  No oath between us. Only the asking, over and over. I'd not trade that for an oath.
    >>  ............................................
    pt  Nenhum juramento entre nós. Só o perguntar, de novo e de novo. Eu não trocaria isso por um juramento.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise, %1$s. You've just kept turning up, and I've counted every time.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo, e eu contei todas as vezes.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.plain/2
    en  Nobody promised anything. That's why I believed it — promises I've had.
    >>  ............................................
    pt  Ninguém prometeu nada. Por isso eu acreditei — promessas eu já tive.
    >>  ............................................
  anxious.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said you would. You just did, and I've not known what to do with that.
    >>  ............................................
    pt  Você nunca disse que viria. Só veio, e eu não soube o que fazer com isso.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up, which lasts better than a promise does.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo, o que dura mais que uma promessa.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Only a habit, and habits outlive intentions.
    >>  ............................................
    pt  Sem promessa. Só um hábito, e hábitos sobrevivem a intenções.
    >>  ............................................
  athletic.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You simply came, over and over, and that's the whole of it.
    >>  ............................................
    pt  Você nunca disse. Simplesmente veio, e de novo, e é isso.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise was made. You came back anyway, which is the same thing done quietly.
    >>  ............................................
    pt  Nenhuma promessa foi feita. Você voltou mesmo assim, que é a mesma coisa feita em silêncio.
    >>  ............................................
  confident.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You didn't say anything. You just kept appearing. I noticed.
    >>  ............................................
    pt  Você não disse nada. Só continuou aparecendo. Eu reparei.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise was made. You came back anyway, which is the same thing done quietly.
    >>  ............................................
    pt  Nenhuma promessa foi feita. Você voltou mesmo assim, que é a mesma coisa feita em silêncio.
    >>  ............................................
  crabby.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You didn't say anything. You just kept appearing. I noticed.
    >>  ............................................
    pt  Você não disse nada. Só continuou aparecendo. Eu reparei.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise, %1$s. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.plain/2
    en  You never said you would. You just did, and that's worth more than the saying.
    >>  ............................................
    pt  Você nunca disse que viria. Só veio, e isso vale mais que o dizer.
    >>  ............................................
  extroverted.dialogue.conversations.fears.followthrough.recall.plain/3
    en  No promise. Only the turning up, every time, without being asked.
    >>  ............................................
    pt  Sem promessa. Só o aparecer, toda vez, sem ser chamado.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise, %1$s. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.plain/2
    en  You never said you would. You just did, and that's worth more than the saying.
    >>  ............................................
    pt  Você nunca disse que viria. Só veio, e isso vale mais que o dizer.
    >>  ............................................
  flirty.dialogue.conversations.fears.followthrough.recall.plain/3
    en  No promise. Only the turning up, every time, without being asked.
    >>  ............................................
    pt  Sem promessa. Só o aparecer, toda vez, sem ser chamado.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise, %1$s. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.plain/2
    en  You never said you would. You just did, and that's worth more than the saying.
    >>  ............................................
    pt  Você nunca disse que viria. Só veio, e isso vale mais que o dizer.
    >>  ............................................
  friendly.dialogue.conversations.fears.followthrough.recall.plain/3
    en  No promise. Only the turning up, every time, without being asked.
    >>  ............................................
    pt  Sem promessa. Só o aparecer, toda vez, sem ser chamado.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise, %1$s. You've just kept turning up, and I've counted every time.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo, e eu contei todas as vezes.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.plain/2
    en  Nobody promised anything. That's why I believed it — promises I've had.
    >>  ............................................
    pt  Ninguém prometeu nada. Por isso eu acreditei — promessas eu já tive.
    >>  ............................................
  gloomy.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said you would. You just did, and I've not known what to do with that.
    >>  ............................................
    pt  Você nunca disse que viria. Só veio, e eu não soube o que fazer com isso.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise was made. You came back anyway, which is the same thing done quietly.
    >>  ............................................
    pt  Nenhuma promessa foi feita. Você voltou mesmo assim, que é a mesma coisa feita em silêncio.
    >>  ............................................
  greedy.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You didn't say anything. You just kept appearing. I noticed.
    >>  ............................................
    pt  Você não disse nada. Só continuou aparecendo. Eu reparei.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise was made. You came back anyway, which is the same thing done quietly.
    >>  ............................................
    pt  Nenhuma promessa foi feita. Você voltou mesmo assim, que é a mesma coisa feita em silêncio.
    >>  ............................................
  grumpy.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You didn't say anything. You just kept appearing. I noticed.
    >>  ............................................
    pt  Você não disse nada. Só continuou aparecendo. Eu reparei.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just the turning up.
    >>  ............................................
    pt  Sem promessa. Só o aparecer.
    >>  ............................................
  introverted.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You just came.
    >>  ............................................
    pt  Você nunca disse. Só veio.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up, which lasts better than a promise does.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo, o que dura mais que uma promessa.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Only a habit, and habits outlive intentions.
    >>  ............................................
    pt  Sem promessa. Só um hábito, e hábitos sobrevivem a intenções.
    >>  ............................................
  lazy.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You simply came, over and over, and that's the whole of it.
    >>  ............................................
    pt  Você nunca disse. Simplesmente veio, e de novo, e é isso.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just the turning up.
    >>  ............................................
    pt  Sem promessa. Só o aparecer.
    >>  ............................................
  odd.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You just came.
    >>  ............................................
    pt  Você nunca disse. Só veio.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up, which lasts better than a promise does.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo, o que dura mais que uma promessa.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Only a habit, and habits outlive intentions.
    >>  ............................................
    pt  Sem promessa. Só um hábito, e hábitos sobrevivem a intenções.
    >>  ............................................
  peaceful.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You simply came, over and over, and that's the whole of it.
    >>  ............................................
    pt  Você nunca disse. Simplesmente veio, e de novo, e é isso.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise! You've just kept turning up, which is a much better system.
    >>  ............................................
    pt  Não houve promessa! Você só continuou aparecendo, que é um sistema bem melhor.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just appearances. Reliable, unannounced appearances. I approve.
    >>  ............................................
    pt  Sem promessa. Só aparições. Aparições confiáveis e não anunciadas. Eu aprovo.
    >>  ............................................
  peppy.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said a word about it. You simply kept arriving. Devious.
    >>  ............................................
    pt  Você nunca disse uma palavra. Simplesmente continuou chegando. Astuto.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise! You've just kept turning up, which is a much better system.
    >>  ............................................
    pt  Não houve promessa! Você só continuou aparecendo, que é um sistema bem melhor.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just appearances. Reliable, unannounced appearances. I approve.
    >>  ............................................
    pt  Sem promessa. Só aparições. Aparições confiáveis e não anunciadas. Eu aprovo.
    >>  ............................................
  playful.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said a word about it. You simply kept arriving. Devious.
    >>  ............................................
    pt  Você nunca disse uma palavra. Simplesmente continuou chegando. Astuto.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up, which lasts better than a promise does.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo, o que dura mais que uma promessa.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Only a habit, and habits outlive intentions.
    >>  ............................................
    pt  Sem promessa. Só um hábito, e hábitos sobrevivem a intenções.
    >>  ............................................
  relaxed.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You simply came, over and over, and that's the whole of it.
    >>  ............................................
    pt  Você nunca disse. Simplesmente veio, e de novo, e é isso.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise, %1$s. You've just kept turning up, and I've counted every time.
    >>  ............................................
    pt  Não houve promessa, %1$s. Você só continuou aparecendo, e eu contei todas as vezes.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.plain/2
    en  Nobody promised anything. That's why I believed it — promises I've had.
    >>  ............................................
    pt  Ninguém prometeu nada. Por isso eu acreditei — promessas eu já tive.
    >>  ............................................
  sensitive.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said you would. You just did, and I've not known what to do with that.
    >>  ............................................
    pt  Você nunca disse que viria. Só veio, e eu não soube o que fazer com isso.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise. You've just kept turning up.
    >>  ............................................
    pt  Não houve promessa. Você só continuou aparecendo.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just the turning up.
    >>  ............................................
    pt  Sem promessa. Só o aparecer.
    >>  ............................................
  shy.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said. You just came.
    >>  ............................................
    pt  Você nunca disse. Só veio.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise! You've just kept turning up, which is a much better system.
    >>  ............................................
    pt  Não houve promessa! Você só continuou aparecendo, que é um sistema bem melhor.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just appearances. Reliable, unannounced appearances. I approve.
    >>  ............................................
    pt  Sem promessa. Só aparições. Aparições confiáveis e não anunciadas. Eu aprovo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said a word about it. You simply kept arriving. Devious.
    >>  ............................................
    pt  Você nunca disse uma palavra. Simplesmente continuou chegando. Astuto.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.plain/1
    en  There wasn't a promise! You've just kept turning up, which is a much better system.
    >>  ............................................
    pt  Não houve promessa! Você só continuou aparecendo, que é um sistema bem melhor.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.plain/2
    en  No promise. Just appearances. Reliable, unannounced appearances. I approve.
    >>  ............................................
    pt  Sem promessa. Só aparições. Aparições confiáveis e não anunciadas. Eu aprovo.
    >>  ............................................
  witty.dialogue.conversations.fears.followthrough.recall.plain/3
    en  You never said a word about it. You simply kept arriving. Devious.
    >>  ............................................
    pt  Você nunca disse uma palavra. Simplesmente continuou chegando. Astuto.
    >>  ............................................
```

</details>


### Button `leave` — "I'll not make a fuss of it."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.resume.followthrough.to.fears.followthrough` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.fears.followthrough.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.followthrough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.followthrough.respond.leave   [27 chars]
    en  I'll not make a fuss of it.
    >>  ............................................
    pt  Não vou fazer alarde disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.followthrough.leave
WHO    VILLAGER — what the player reads after pressing "I'll not make a fuss of it."
       spoken on: conversations.arc.fears.followthrough.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.followthrough.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.followthrough.leave/1   [38 chars]
    en  Aye. Best not to stare at it too long.
    >>  ............................................
    pt  Tá. Melhor não encarar isso por muito tempo.
    >>  ............................................
  dialogue.conversations.fears.followthrough.leave/2   [58 chars]
    en  Very well. Off you go. It's still there, but it's smaller.
    >>  ............................................
    pt  Muito bem. Pode ir. Ainda está lá, mas menor.
    >>  ............................................
  dialogue.conversations.fears.followthrough.leave/3   [39 chars]
    en  Go on. And thank you for keeping track.
    >>  ............................................
    pt  Pode ir. E obrigado por acompanhar.
    >>  ............................................
```

---


## `conversations.arc.fears.plan.followup`

**Reached from 4 route(s):** `conversations.arc.fears.plan.respond` / `ask_what_helps`; `conversations.arc.fears.plan.respond` / `ask_what_helps`; `conversations.arc.fears.plan.respond` / `offer_plan`; `conversations.arc.fears.plan.respond` / `just_listen`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.plan.ask_what_helps.plain` — e.g. "Honestly? I don't know. Huh. That's a worse answer than I expected to give. Right — next question."
- `conversations.fears.plan.ask_what_helps.remembered` — e.g. "You remember all of it, don't you. Even the part I nearly didn't say. ...Company. That's what helps."
- `conversations.fears.plan.just_listen` — e.g. "...No plan. Just this. That's better than a plan, actually."
- `conversations.fears.plan.offer_plan` — e.g. "Do something about it. You make it sound like a chore list. ...Maybe it is."


```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.fears.plan.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.fears.plan.followup   [29 chars]
    en  That's what I've got, anyway.
    >>  ............................................
    pt  É o que eu tenho, enfim.
    >>  ............................................
```


### Button `commit` — "Then I'll be there for that bit."

*stance family `encouragement` · tone `plain` · answers the beat(s) `fears.plan.ask_what_helps.plain.to.fears.plan`, `fears.plan.ask_what_helps.remembered.to.fears.plan`, `fears.plan.just_listen.to.fears.plan`, `fears.plan.offer_plan.to.fears.plan`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.plan.followup.commit` — accepted phrasings: "then i will be there for that bit"; "i will be there for that part"; "i will be there when it happens"
  - the message must contain one of: `there`
  - scored words: `there`(1.4), `bit`(1.1)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.followup.commit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.followup.commit   [32 chars]
    en  Then I'll be there for that bit.
    >>  ............................................
    pt  Então eu estarei lá nessa parte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.plan.followup.commit`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +2  _(recorded under topic `fears.plan.followup.commit`)_
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.followup.commit
WHO    VILLAGER — what the player reads after pressing "Then I'll be there for that bit."
       spoken on: conversations.arc.fears.plan.followup, button `commit`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.followup.commit.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.followup.commit/1   [80 chars]
    en  You'll be there. Then it's a plan and not a wish, which is the whole difference.
    >>  ............................................
    pt  Você estará lá. Então é um plano e não um desejo, que é a diferença inteira.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.commit/2   [91 chars]
    en  So be it. Then I can't quietly not do it, can I. That's the useful part of telling someone.
    >>  ............................................
    pt  Que seja. Aí eu não posso simplesmente não fazer, né. Essa é a parte útil de contar para alguém.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.commit/3   [57 chars]
    en  Good. I'll hold you to it, %1$s, and you hold me to mine.
    >>  ............................................
    pt  Bom. Eu cobro de você, %1$s, e você cobra de mim.
    >>  ............................................
```


### Button `refine` — "What would make that easier?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `fears.plan.ask_what_helps.plain.to.fears.plan`, `fears.plan.ask_what_helps.remembered.to.fears.plan`, `fears.plan.just_listen.to.fears.plan`, `fears.plan.offer_plan.to.fears.plan`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.plan.followup.refine` — accepted phrasings: "what would make that easier"; "how could that be easier"; "what makes it easier"
  - the message must contain one of: `easier`
  - scored words: `easier`(1.6), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.followup.refine
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.followup.refine   [28 chars]
    en  What would make that easier?
    >>  ............................................
    pt  O que tornaria isso mais fácil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, familiarity +2  _(recorded under topic `fears.plan.followup.refine`)_
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.followup.refine
WHO    VILLAGER — what the player reads after pressing "What would make that easier?"
       spoken on: conversations.arc.fears.plan.followup, button `refine`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.followup.refine.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.followup.refine/1   [82 chars]
    en  Easier. ...If it were smaller. If it were one step instead of the whole staircase.
    >>  ............................................
    pt  Mais fácil. ...Se fosse menor. Se fosse um degrau em vez da escada inteira.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.refine/2   [74 chars]
    en  Daylight would help. Everything's twice the size after dark, I've noticed.
    >>  ............................................
    pt  Luz do dia ajudaria. Tudo tem o dobro do tamanho depois de escurecer, eu reparei.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.refine/3   [81 chars]
    en  Somebody standing where I can see them. That's all. That's the entire list, %1$s.
    >>  ............................................
    pt  Alguém parado onde eu consiga ver. Só isso. É a lista inteira, %1$s.
    >>  ............................................
```


### Button `honest` — "I can't promise. But I'll try."

*stance family `candor` · tone `plain` · answers the beat(s) `fears.plan.ask_what_helps.plain.to.fears.plan`, `fears.plan.ask_what_helps.remembered.to.fears.plan`, `fears.plan.just_listen.to.fears.plan`, `fears.plan.offer_plan.to.fears.plan`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.plan.followup.honest` — accepted phrasings: "i cannot promise but i will try"; "no promises but i will try"; "i will try, i cannot promise"
  - the message must contain one of: `promise`, `try`
  - scored words: `promise`(1.5), `try`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.followup.honest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.followup.honest   [30 chars]
    en  I can't promise. But I'll try.
    >>  ............................................
    pt  Não posso prometer. Mas vou tentar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.plan.followup.honest`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +5, trust +2  _(recorded under topic `fears.plan.followup.honest`)_
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.followup.honest
WHO    VILLAGER — what the player reads after pressing "I can't promise. But I'll try."
       spoken on: conversations.arc.fears.plan.followup, button `honest`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.followup.honest.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.followup.honest/1   [95 chars]
    en  You can't promise. Good. I've had promises. I'd rather have somebody who counts the cost first.
    >>  ............................................
    pt  Você não pode prometer. Bom. Já tive promessas. Prefiro quem calcula o custo antes.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.honest/2   [82 chars]
    en  'I'll try' is a real thing. 'I promise' is usually a way of ending a conversation.
    >>  ............................................
    pt  'Vou tentar' é uma coisa real. 'Prometo' costuma ser um jeito de encerrar a conversa.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.honest/3   [42 chars]
    en  That'll do. That'll do very well, in fact.
    >>  ............................................
    pt  Serve. Serve muito bem, aliás.
    >>  ............................................
```


### Button `leave` — "Take your time with it."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.plan.ask_what_helps.plain.to.fears.plan`, `fears.plan.ask_what_helps.remembered.to.fears.plan`, `fears.plan.just_listen.to.fears.plan`, `fears.plan.offer_plan.to.fears.plan` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.followup.leave   [23 chars]
    en  Take your time with it.
    >>  ............................................
    pt  Vá no seu tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.followup.leave
WHO    VILLAGER — what the player reads after pressing "Take your time with it."
       spoken on: conversations.arc.fears.plan.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.followup.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.plan.followup.leave/1   [57 chars]
    en  It is. It's waited this long, it can wait for a good day.
    >>  ............................................
    pt  É sim. Esperou até aqui, pode esperar por um dia bom.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.leave/2   [24 chars]
    en  Right you are. No hurry.
    >>  ............................................
    pt  Isso mesmo. Sem pressa.
    >>  ............................................
  dialogue.conversations.fears.plan.followup.leave/3   [43 chars]
    en  Off you go, %1$s. I'll keep thinking on it.
    >>  ............................................
    pt  Pode ir, %1$s. Vou seguir pensando nisso.
    >>  ............................................
```

---


## `conversations.arc.fears.plan.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.resume.plan` — e.g. "That thing I told you about. It hasn't gone anywhere."


```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.fears.plan.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.fears.plan.respond   [29 chars]
    en  It's still there, that thing.
    >>  ............................................
    pt  Ainda está lá, aquela coisa.
    >>  ............................................
```


### Button `ask_what_helps` — "What would actually help?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `fears.resume.plan.to.fears.plan`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.plan.ask_what_helps` — accepted phrasings: "what would help"; "what do you need"; "what actually helps"; "how can i help"
  - the message must contain one of: `help`, `helps`, `need`
  - scored words: `help`(1.5), `helps`(1.5), `need`(1.2), `what`(0.4)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.respond.ask_what_helps
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.respond.ask_what_helps   [25 chars]
    en  What would actually help?
    >>  ............................................
    pt  O que realmente ajudaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when milestone `fears.revelation` is set
- Does: **hearts +2** — decision id `fears.plan.ask_what_helps`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `fears.plan.ask_what_helps`)_
- Does: arc `fears` — advance to stage 2
- Then opens: `conversations.arc.fears.plan.followup`
- …where the player's next choices will be: "Then I'll be there for that bit." | "What would make that easier?" | "I can't promise. But I'll try." | "Take your time with it."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.ask_what_helps.remembered
WHO    VILLAGER — what the player reads after pressing "What would actually help?"
       spoken on: conversations.arc.fears.plan.respond, button `ask_what_helps`
       leaves the player on: conversations.arc.fears.plan.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.ask_what_helps.remembered.to.fears.plan`: the villager accepts. Subject `fears.plan`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.ask_what_helps.remembered/1   [100 chars]
    en  You remember all of it, don't you. Even the part I nearly didn't say. ...Company. That's what helps.
    >>  ............................................
    pt  Você lembra de tudo, né. Até da parte que eu quase não disse. ...Companhia. É isso que ajuda.
    >>  ............................................
  dialogue.conversations.fears.plan.ask_what_helps.remembered/2   [90 chars]
    en  You remembered by morning. Nine in ten don't. Not being alone with it — that's the answer.
    >>  ............................................
    pt  Você lembrou de manhã. Nove em dez não lembram. Não estar sozinho com isso — é essa a resposta.
    >>  ............................................
  dialogue.conversations.fears.plan.ask_what_helps.remembered/3   [85 chars]
    en  Since you actually listened the first time: someone knowing. That's most of it, %1$s.
    >>  ............................................
    pt  Já que você ouviu de verdade da primeira vez: alguém saber. É quase tudo, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it, don't you. Even the part I nearly didn't say, %1$s.
    >>  ............................................
    pt  Você lembra de tudo, não é? Até da parte que eu quase não disse, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. I'd assumed it had gone in one ear. Being wrong about that is —  give me a moment.
    >>  ............................................
    pt  A coisa toda. Eu supunha que tinha entrado por um ouvido. Errar nisso é — me dê um momento.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I said quietly so it wouldn't count.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu disse baixo pra não contar.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say. Things keep, with the right person.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse. As coisas se conservam, com a pessoa certa.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it, months on. That's a better memory than mine and I was there.
    >>  ............................................
    pt  A coisa toda, meses depois. É uma memória melhor que a minha e eu estava lá.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Right. Then we can start further along than last time.
    >>  ............................................
    pt  Tudo. Certo. Então a gente começa mais adiante que da última vez.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  You kept the whole of it. Right. Then I'll not repeat myself.
    >>  ............................................
    pt  Você guardou tudo. Certo. Então eu não vou me repetir.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I mumbled. Noted.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu murmurei. Anotado.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  You kept the whole of it. Right. Then I'll not repeat myself.
    >>  ............................................
    pt  Você guardou tudo. Certo. Então eu não vou me repetir.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I mumbled. Noted.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu murmurei. Anotado.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it, don't you, %1$s. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo, não é, %1$s. Até da parte que eu quase não disse.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. Nobody keeps the whole of it. I'd not expected to be kept.
    >>  ............................................
    pt  A coisa toda. Ninguém guarda a coisa toda. Eu não esperava ser guardado.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. That's the part that makes this easier than last time.
    >>  ............................................
    pt  Tudo. É essa parte que torna isso mais fácil que da última vez.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it, don't you, %1$s. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo, não é, %1$s. Até da parte que eu quase não disse.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. Nobody keeps the whole of it. I'd not expected to be kept.
    >>  ............................................
    pt  A coisa toda. Ninguém guarda a coisa toda. Eu não esperava ser guardado.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. That's the part that makes this easier than last time.
    >>  ............................................
    pt  Tudo. É essa parte que torna isso mais fácil que da última vez.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it, don't you, %1$s. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo, não é, %1$s. Até da parte que eu quase não disse.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. Nobody keeps the whole of it. I'd not expected to be kept.
    >>  ............................................
    pt  A coisa toda. Ninguém guarda a coisa toda. Eu não esperava ser guardado.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. That's the part that makes this easier than last time.
    >>  ............................................
    pt  Tudo. É essa parte que torna isso mais fácil que da última vez.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it, don't you. Even the part I nearly didn't say, %1$s.
    >>  ............................................
    pt  Você lembra de tudo, não é? Até da parte que eu quase não disse, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. I'd assumed it had gone in one ear. Being wrong about that is —  give me a moment.
    >>  ............................................
    pt  A coisa toda. Eu supunha que tinha entrado por um ouvido. Errar nisso é — me dê um momento.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I said quietly so it wouldn't count.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu disse baixo pra não contar.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  You kept the whole of it. Right. Then I'll not repeat myself.
    >>  ............................................
    pt  Você guardou tudo. Certo. Então eu não vou me repetir.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I mumbled. Noted.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu murmurei. Anotado.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  You kept the whole of it. Right. Then I'll not repeat myself.
    >>  ............................................
    pt  Você guardou tudo. Certo. Então eu não vou me repetir.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I mumbled. Noted.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu murmurei. Anotado.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. Right.
    >>  ............................................
    pt  A coisa toda. Certo.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. I'd not expected that.
    >>  ............................................
    pt  Tudo. Eu não esperava isso.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say. Things keep, with the right person.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse. As coisas se conservam, com a pessoa certa.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it, months on. That's a better memory than mine and I was there.
    >>  ............................................
    pt  A coisa toda, meses depois. É uma memória melhor que a minha e eu estava lá.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Right. Then we can start further along than last time.
    >>  ............................................
    pt  Tudo. Certo. Então a gente começa mais adiante que da última vez.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. Right.
    >>  ............................................
    pt  A coisa toda. Certo.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. I'd not expected that.
    >>  ............................................
    pt  Tudo. Eu não esperava isso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say. Things keep, with the right person.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse. As coisas se conservam, com a pessoa certa.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it, months on. That's a better memory than mine and I was there.
    >>  ............................................
    pt  A coisa toda, meses depois. É uma memória melhor que a minha e eu estava lá.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Right. Then we can start further along than last time.
    >>  ............................................
    pt  Tudo. Certo. Então a gente começa mais adiante que da última vez.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it! Even the part I nearly didn't say. That's frankly alarming.
    >>  ............................................
    pt  Você lembra de tudo! Até da parte que eu quase não disse. É francamente alarmante.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole thing. Including the mumbled bit. I'd hoped that one had escaped.
    >>  ............................................
    pt  A coisa toda. Inclusive a parte murmurada. Eu esperava que aquela tivesse escapado.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it! Right. I'll be more careful about what I mumble in future.
    >>  ............................................
    pt  Tudo! Certo. Vou ter mais cuidado com o que eu murmuro daqui pra frente.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it! Even the part I nearly didn't say. That's frankly alarming.
    >>  ............................................
    pt  Você lembra de tudo! Até da parte que eu quase não disse. É francamente alarmante.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole thing. Including the mumbled bit. I'd hoped that one had escaped.
    >>  ............................................
    pt  A coisa toda. Inclusive a parte murmurada. Eu esperava que aquela tivesse escapado.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it! Right. I'll be more careful about what I mumble in future.
    >>  ............................................
    pt  Tudo! Certo. Vou ter mais cuidado com o que eu murmuro daqui pra frente.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say. Things keep, with the right person.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse. As coisas se conservam, com a pessoa certa.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it, months on. That's a better memory than mine and I was there.
    >>  ............................................
    pt  A coisa toda, meses depois. É uma memória melhor que a minha e eu estava lá.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Right. Then we can start further along than last time.
    >>  ............................................
    pt  Tudo. Certo. Então a gente começa mais adiante que da última vez.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it, don't you. Even the part I nearly didn't say, %1$s.
    >>  ............................................
    pt  Você lembra de tudo, não é? Até da parte que eu quase não disse, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. I'd assumed it had gone in one ear. Being wrong about that is —  give me a moment.
    >>  ............................................
    pt  A coisa toda. Eu supunha que tinha entrado por um ouvido. Errar nisso é — me dê um momento.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. Including the bit I said quietly so it wouldn't count.
    >>  ............................................
    pt  Tudo. Inclusive a parte que eu disse baixo pra não contar.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it. Even the part I nearly didn't say.
    >>  ............................................
    pt  Você lembra de tudo. Até da parte que eu quase não disse.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole of it. Right.
    >>  ............................................
    pt  A coisa toda. Certo.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it. I'd not expected that.
    >>  ............................................
    pt  Tudo. Eu não esperava isso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it! Even the part I nearly didn't say. That's frankly alarming.
    >>  ............................................
    pt  Você lembra de tudo! Até da parte que eu quase não disse. É francamente alarmante.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole thing. Including the mumbled bit. I'd hoped that one had escaped.
    >>  ............................................
    pt  A coisa toda. Inclusive a parte murmurada. Eu esperava que aquela tivesse escapado.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it! Right. I'll be more careful about what I mumble in future.
    >>  ............................................
    pt  Tudo! Certo. Vou ter mais cuidado com o que eu murmuro daqui pra frente.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.ask_what_helps.remembered/1
    en  You remember all of it! Even the part I nearly didn't say. That's frankly alarming.
    >>  ............................................
    pt  Você lembra de tudo! Até da parte que eu quase não disse. É francamente alarmante.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.ask_what_helps.remembered/2
    en  The whole thing. Including the mumbled bit. I'd hoped that one had escaped.
    >>  ............................................
    pt  A coisa toda. Inclusive a parte murmurada. Eu esperava que aquela tivesse escapado.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.ask_what_helps.remembered/3
    en  All of it! Right. I'll be more careful about what I mumble in future.
    >>  ............................................
    pt  Tudo! Certo. Vou ter mais cuidado com o que eu murmuro daqui pra frente.
    >>  ............................................
```

</details>


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when milestone `fears.revelation` is set  _(chance -2000)_
- Does: **hearts +1** — decision id `fears.plan.ask_what_helps`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3  _(recorded under topic `fears.plan.ask_what_helps`)_
- Does: arc `fears` — advance to stage 2
- Then opens: `conversations.arc.fears.plan.followup`
- …where the player's next choices will be: "Then I'll be there for that bit." | "What would make that easier?" | "I can't promise. But I'll try." | "Take your time with it."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.ask_what_helps.plain
WHO    VILLAGER — what the player reads after pressing "What would actually help?"
       spoken on: conversations.arc.fears.plan.respond, button `ask_what_helps`
       leaves the player on: conversations.arc.fears.plan.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.ask_what_helps.plain.to.fears.plan`: the villager accepts. Subject `fears.plan`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.ask_what_helps.plain/1   [98 chars]
    en  Honestly? I don't know. Huh. That's a worse answer than I expected to give. Right — next question.
    >>  ............................................
    pt  Sinceramente? Não sei. Hm. Essa resposta é pior do que eu esperava dar. Certo — próxima pergunta.
    >>  ............................................
  dialogue.conversations.fears.plan.ask_what_helps.plain/2   [53 chars]
    en  What would help. Huh. Give me a moment with that one.
    >>  ............................................
    pt  O que ajudaria. Hm. Me dá um instante com essa.
    >>  ............................................
  dialogue.conversations.fears.plan.ask_what_helps.plain/3   [40 chars]
    en  Not being talked out of it, for a start.
    >>  ............................................
    pt  Não ser convencido do contrário, para começar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know, %1$s. That's a worse answer than I meant to give.
    >>  ............................................
    pt  Sinceramente? Não sei, %1$s. É uma resposta pior do que eu queria dar.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. It hadn't occurred to me that anything could help, so I never looked.
    >>  ............................................
    pt  Nem ideia. Não me ocorreu que algo pudesse ajudar, então eu nunca procurei.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. I'm sorry. I'd like to have something better than that.
    >>  ............................................
    pt  Não sei. Desculpe. Eu gostaria de ter algo melhor que isso.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. I'll have thought about it by the next time you ask.
    >>  ............................................
    pt  Sinceramente? Não sei. Vou ter pensado até a próxima vez que você perguntar.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea yet. These answers arrive slowly and I've only just been asked the question.
    >>  ............................................
    pt  Nem ideia ainda. Essas respostas chegam devagar e eu acabei de ouvir a pergunta.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. That's not a closed door; it's just an empty one for now.
    >>  ............................................
    pt  Não sei. Não é uma porta fechada; é só uma vazia por enquanto.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  I've no idea. Nobody's asked, so I've never had to work it out.
    >>  ............................................
    pt  Não faço ideia. Ninguém perguntou, então eu nunca precisei descobrir.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me a week and I might.
    >>  ............................................
    pt  Não sei. Me dê uma semana e talvez eu saiba.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  I've no idea. Nobody's asked, so I've never had to work it out.
    >>  ............................................
    pt  Não faço ideia. Ninguém perguntou, então eu nunca precisei descobrir.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me a week and I might.
    >>  ............................................
    pt  Não sei. Me dê uma semana e talvez eu saiba.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know, %1$s. That's a worse answer than I expected to give you.
    >>  ............................................
    pt  Sinceramente? Não sei, %1$s. É uma resposta pior do que eu esperava te dar.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. Ask me again once I've thought — I'd like to have a proper answer for you.
    >>  ............................................
    pt  Nem ideia. Me pergunte de novo quando eu tiver pensado — eu queria ter resposta pra você.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Nobody's asked, so it's never had to become a question.
    >>  ............................................
    pt  Não sei. Ninguém perguntou, então nunca precisou virar pergunta.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know, %1$s. That's a worse answer than I expected to give you.
    >>  ............................................
    pt  Sinceramente? Não sei, %1$s. É uma resposta pior do que eu esperava te dar.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. Ask me again once I've thought — I'd like to have a proper answer for you.
    >>  ............................................
    pt  Nem ideia. Me pergunte de novo quando eu tiver pensado — eu queria ter resposta pra você.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Nobody's asked, so it's never had to become a question.
    >>  ............................................
    pt  Não sei. Ninguém perguntou, então nunca precisou virar pergunta.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know, %1$s. That's a worse answer than I expected to give you.
    >>  ............................................
    pt  Sinceramente? Não sei, %1$s. É uma resposta pior do que eu esperava te dar.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. Ask me again once I've thought — I'd like to have a proper answer for you.
    >>  ............................................
    pt  Nem ideia. Me pergunte de novo quando eu tiver pensado — eu queria ter resposta pra você.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Nobody's asked, so it's never had to become a question.
    >>  ............................................
    pt  Não sei. Ninguém perguntou, então nunca precisou virar pergunta.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know, %1$s. That's a worse answer than I meant to give.
    >>  ............................................
    pt  Sinceramente? Não sei, %1$s. É uma resposta pior do que eu queria dar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. It hadn't occurred to me that anything could help, so I never looked.
    >>  ............................................
    pt  Nem ideia. Não me ocorreu que algo pudesse ajudar, então eu nunca procurei.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. I'm sorry. I'd like to have something better than that.
    >>  ............................................
    pt  Não sei. Desculpe. Eu gostaria de ter algo melhor que isso.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  I've no idea. Nobody's asked, so I've never had to work it out.
    >>  ............................................
    pt  Não faço ideia. Ninguém perguntou, então eu nunca precisei descobrir.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me a week and I might.
    >>  ............................................
    pt  Não sei. Me dê uma semana e talvez eu saiba.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  I've no idea. Nobody's asked, so I've never had to work it out.
    >>  ............................................
    pt  Não faço ideia. Ninguém perguntou, então eu nunca precisei descobrir.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me a week and I might.
    >>  ............................................
    pt  Não sei. Me dê uma semana e talvez eu saiba.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know.
    >>  ............................................
    pt  Sinceramente? Não sei.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. Nobody's asked before.
    >>  ............................................
    pt  Nem ideia. Ninguém perguntou antes.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me time.
    >>  ............................................
    pt  Não sei. Me dê tempo.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. I'll have thought about it by the next time you ask.
    >>  ............................................
    pt  Sinceramente? Não sei. Vou ter pensado até a próxima vez que você perguntar.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea yet. These answers arrive slowly and I've only just been asked the question.
    >>  ............................................
    pt  Nem ideia ainda. Essas respostas chegam devagar e eu acabei de ouvir a pergunta.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. That's not a closed door; it's just an empty one for now.
    >>  ............................................
    pt  Não sei. Não é uma porta fechada; é só uma vazia por enquanto.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know.
    >>  ............................................
    pt  Sinceramente? Não sei.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. Nobody's asked before.
    >>  ............................................
    pt  Nem ideia. Ninguém perguntou antes.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me time.
    >>  ............................................
    pt  Não sei. Me dê tempo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. I'll have thought about it by the next time you ask.
    >>  ............................................
    pt  Sinceramente? Não sei. Vou ter pensado até a próxima vez que você perguntar.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea yet. These answers arrive slowly and I've only just been asked the question.
    >>  ............................................
    pt  Nem ideia ainda. Essas respostas chegam devagar e eu acabei de ouvir a pergunta.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. That's not a closed door; it's just an empty one for now.
    >>  ............................................
    pt  Não sei. Não é uma porta fechada; é só uma vazia por enquanto.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know! Huh. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei! Huh. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. None. Nobody's ever asked, so I've never had to have one ready.
    >>  ............................................
    pt  Nem ideia. Nenhuma. Ninguém nunca perguntou, então eu nunca precisei ter uma pronta.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know! Which is embarrassing and also quite interesting.
    >>  ............................................
    pt  Não sei! O que é constrangedor e também bem interessante.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know! Huh. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei! Huh. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. None. Nobody's ever asked, so I've never had to have one ready.
    >>  ............................................
    pt  Nem ideia. Nenhuma. Ninguém nunca perguntou, então eu nunca precisei ter uma pronta.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know! Which is embarrassing and also quite interesting.
    >>  ............................................
    pt  Não sei! O que é constrangedor e também bem interessante.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know. I'll have thought about it by the next time you ask.
    >>  ............................................
    pt  Sinceramente? Não sei. Vou ter pensado até a próxima vez que você perguntar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea yet. These answers arrive slowly and I've only just been asked the question.
    >>  ............................................
    pt  Nem ideia ainda. Essas respostas chegam devagar e eu acabei de ouvir a pergunta.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. That's not a closed door; it's just an empty one for now.
    >>  ............................................
    pt  Não sei. Não é uma porta fechada; é só uma vazia por enquanto.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know, %1$s. That's a worse answer than I meant to give.
    >>  ............................................
    pt  Sinceramente? Não sei, %1$s. É uma resposta pior do que eu queria dar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. It hadn't occurred to me that anything could help, so I never looked.
    >>  ............................................
    pt  Nem ideia. Não me ocorreu que algo pudesse ajudar, então eu nunca procurei.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. I'm sorry. I'd like to have something better than that.
    >>  ............................................
    pt  Não sei. Desculpe. Eu gostaria de ter algo melhor que isso.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know.
    >>  ............................................
    pt  Sinceramente? Não sei.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. Nobody's asked before.
    >>  ............................................
    pt  Nem ideia. Ninguém perguntou antes.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know. Give me time.
    >>  ............................................
    pt  Não sei. Me dê tempo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know! Huh. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei! Huh. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. None. Nobody's ever asked, so I've never had to have one ready.
    >>  ............................................
    pt  Nem ideia. Nenhuma. Ninguém nunca perguntou, então eu nunca precisei ter uma pronta.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know! Which is embarrassing and also quite interesting.
    >>  ............................................
    pt  Não sei! O que é constrangedor e também bem interessante.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.ask_what_helps.plain/1
    en  Honestly? I don't know! Huh. That's a worse answer than I expected to give.
    >>  ............................................
    pt  Sinceramente? Não sei! Huh. É uma resposta pior do que eu esperava dar.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.ask_what_helps.plain/2
    en  No idea. None. Nobody's ever asked, so I've never had to have one ready.
    >>  ............................................
    pt  Nem ideia. Nenhuma. Ninguém nunca perguntou, então eu nunca precisei ter uma pronta.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.ask_what_helps.plain/3
    en  I don't know! Which is embarrassing and also quite interesting.
    >>  ............................................
    pt  Não sei! O que é constrangedor e também bem interessante.
    >>  ............................................
```

</details>


### Button `offer_plan` — "Then let's do something about it."

*stance family `practical_help` · tone `plain` · answers the beat(s) `fears.resume.plan.to.fears.plan`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.plan.offer_plan` — accepted phrasings: "let us do something about it"; "we need a plan"; "let us try something"; "we should act"
  - the message must contain one of: `something`, `plan`, `try`, `act`
  - scored words: `do`(0.5), `something`(1.2), `plan`(1.5), `try`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.respond.offer_plan
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.respond.offer_plan   [33 chars]
    en  Then let's do something about it.
    >>  ............................................
    pt  Então vamos fazer alguma coisa a respeito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.plan.offer_plan`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +1  _(recorded under topic `fears.plan.offer_plan`)_
- Does: arc `fears` — advance to stage 2
- Then opens: `conversations.arc.fears.plan.followup`
- …where the player's next choices will be: "Then I'll be there for that bit." | "What would make that easier?" | "I can't promise. But I'll try." | "Take your time with it."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.offer_plan
WHO    VILLAGER — what the player reads after pressing "Then let's do something about it."
       spoken on: conversations.arc.fears.plan.respond, button `offer_plan`
       leaves the player on: conversations.arc.fears.plan.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.offer_plan.to.fears.plan`: the villager accepts. Subject `fears.plan`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.offer_plan/1   [75 chars]
    en  Do something about it. You make it sound like a chore list. ...Maybe it is.
    >>  ............................................
    pt  Fazer algo a respeito. Você fala como se fosse lista de tarefas. ...Talvez seja.
    >>  ............................................
  dialogue.conversations.fears.plan.offer_plan/2   [58 chars]
    en  Alright. Something small, then. I'm not promising heroics.
    >>  ............................................
    pt  Certo. Algo pequeno, então. Não estou prometendo heroísmo.
    >>  ............................................
  dialogue.conversations.fears.plan.offer_plan/3   [90 chars]
    en  A plan. Aye — written down, in order, with a first step small enough to actually do, %1$s.
    >>  ............................................
    pt  Um plano. É — escrito, em ordem, com um primeiro passo pequeno o bastante para eu fazer, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list, %1$s. It hasn't felt like one.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas, %1$s. Nunca pareceu uma.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. I've had plans. Plans are for things you can see the edges of.
    >>  ............................................
    pt  Um plano. Já tive planos. Planos são pra coisas cujas bordas você vê.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. I'd like it to be that. I'm not sure it is and I'd like it to be.
    >>  ............................................
    pt  Consertar. Eu queria que fosse isso. Não tenho certeza e queria que fosse.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list. Chore lists do get done, mind.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas. Mas listas se cumprem.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Slowly, then, and with a great many small items rather than one large one.
    >>  ............................................
    pt  Um plano. Devagar, então, e com muitos itens pequenos em vez de um grande.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Over years, perhaps. Not this month.
    >>  ............................................
    pt  Consertar. Ao longo de anos, talvez. Neste mês não.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. It's not a fence that needs mending, but go on.
    >>  ............................................
    pt  Um plano. Certo. Não é uma cerca que precisa de conserto, mas continue.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. That's how you'd put it. It isn't wrong, exactly.
    >>  ............................................
    pt  Consertar. É como você colocaria. Não está errado, exatamente.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. It's not a fence that needs mending, but go on.
    >>  ............................................
    pt  Um plano. Certo. Não é uma cerca que precisa de conserto, mas continue.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. That's how you'd put it. It isn't wrong, exactly.
    >>  ............................................
    pt  Consertar. É como você colocaria. Não está errado, exatamente.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list, %1$s. ...Maybe it is.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas, %1$s. ...Talvez seja.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. From you I'll hear it out, which I'd not do from most people.
    >>  ............................................
    pt  Um plano. De você eu escuto, o que eu não faria com quase ninguém.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Alright. Say the first step and I'll tell you honestly whether I could.
    >>  ............................................
    pt  Consertar. Está bem. Diga o primeiro passo e eu digo honestamente se eu conseguiria.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list, %1$s. ...Maybe it is.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas, %1$s. ...Talvez seja.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. From you I'll hear it out, which I'd not do from most people.
    >>  ............................................
    pt  Um plano. De você eu escuto, o que eu não faria com quase ninguém.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Alright. Say the first step and I'll tell you honestly whether I could.
    >>  ............................................
    pt  Consertar. Está bem. Diga o primeiro passo e eu digo honestamente se eu conseguiria.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list, %1$s. ...Maybe it is.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas, %1$s. ...Talvez seja.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. From you I'll hear it out, which I'd not do from most people.
    >>  ............................................
    pt  Um plano. De você eu escuto, o que eu não faria com quase ninguém.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Alright. Say the first step and I'll tell you honestly whether I could.
    >>  ............................................
    pt  Consertar. Está bem. Diga o primeiro passo e eu digo honestamente se eu conseguiria.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list, %1$s. It hasn't felt like one.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas, %1$s. Nunca pareceu uma.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. I've had plans. Plans are for things you can see the edges of.
    >>  ............................................
    pt  Um plano. Já tive planos. Planos são pra coisas cujas bordas você vê.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. I'd like it to be that. I'm not sure it is and I'd like it to be.
    >>  ............................................
    pt  Consertar. Eu queria que fosse isso. Não tenho certeza e queria que fosse.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. It's not a fence that needs mending, but go on.
    >>  ............................................
    pt  Um plano. Certo. Não é uma cerca que precisa de conserto, mas continue.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. That's how you'd put it. It isn't wrong, exactly.
    >>  ............................................
    pt  Consertar. É como você colocaria. Não está errado, exatamente.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. It's not a fence that needs mending, but go on.
    >>  ............................................
    pt  Um plano. Certo. Não é uma cerca que precisa de conserto, mas continue.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. That's how you'd put it. It isn't wrong, exactly.
    >>  ............................................
    pt  Consertar. É como você colocaria. Não está errado, exatamente.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. ...Maybe it is one.
    >>  ............................................
    pt  Um plano. ...Talvez seja uma.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Right.
    >>  ............................................
    pt  Consertar. Certo.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list. Chore lists do get done, mind.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas. Mas listas se cumprem.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Slowly, then, and with a great many small items rather than one large one.
    >>  ............................................
    pt  Um plano. Devagar, então, e com muitos itens pequenos em vez de um grande.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Over years, perhaps. Not this month.
    >>  ............................................
    pt  Consertar. Ao longo de anos, talvez. Neste mês não.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. ...Maybe it is one.
    >>  ............................................
    pt  Um plano. ...Talvez seja uma.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Right.
    >>  ............................................
    pt  Consertar. Certo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list. Chore lists do get done, mind.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas. Mas listas se cumprem.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Slowly, then, and with a great many small items rather than one large one.
    >>  ............................................
    pt  Um plano. Devagar, então, e com muitos itens pequenos em vez de um grande.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Over years, perhaps. Not this month.
    >>  ............................................
    pt  Consertar. Ao longo de anos, talvez. Neste mês não.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it! You make it sound like a chore list. ...Maybe it is one.
    >>  ............................................
    pt  Fazer algo a respeito! Você faz soar como lista de tarefas. ...Talvez seja uma.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. Nobody's ever tried treating it as a job of work. It might even help.
    >>  ............................................
    pt  Um plano. Certo. Ninguém nunca tentou tratar isso como serviço. Pode até ajudar.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it! Ha. Go on then, tell me the first item and I'll object to it.
    >>  ............................................
    pt  Consertar! Ha. Vá em frente, diga o primeiro item e eu vou objetar.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it! You make it sound like a chore list. ...Maybe it is one.
    >>  ............................................
    pt  Fazer algo a respeito! Você faz soar como lista de tarefas. ...Talvez seja uma.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. Nobody's ever tried treating it as a job of work. It might even help.
    >>  ............................................
    pt  Um plano. Certo. Ninguém nunca tentou tratar isso como serviço. Pode até ajudar.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it! Ha. Go on then, tell me the first item and I'll object to it.
    >>  ............................................
    pt  Consertar! Ha. Vá em frente, diga o primeiro item e eu vou objetar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list. Chore lists do get done, mind.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas. Mas listas se cumprem.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Slowly, then, and with a great many small items rather than one large one.
    >>  ............................................
    pt  Um plano. Devagar, então, e com muitos itens pequenos em vez de um grande.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Over years, perhaps. Not this month.
    >>  ............................................
    pt  Consertar. Ao longo de anos, talvez. Neste mês não.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list, %1$s. It hasn't felt like one.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas, %1$s. Nunca pareceu uma.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. I've had plans. Plans are for things you can see the edges of.
    >>  ............................................
    pt  Um plano. Já tive planos. Planos são pra coisas cujas bordas você vê.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. I'd like it to be that. I'm not sure it is and I'd like it to be.
    >>  ............................................
    pt  Consertar. Eu queria que fosse isso. Não tenho certeza e queria que fosse.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it. You make it sound like a chore list.
    >>  ............................................
    pt  Fazer algo a respeito. Você faz soar como lista de tarefas.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. ...Maybe it is one.
    >>  ............................................
    pt  Um plano. ...Talvez seja uma.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it. Right.
    >>  ............................................
    pt  Consertar. Certo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it! You make it sound like a chore list. ...Maybe it is one.
    >>  ............................................
    pt  Fazer algo a respeito! Você faz soar como lista de tarefas. ...Talvez seja uma.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. Nobody's ever tried treating it as a job of work. It might even help.
    >>  ............................................
    pt  Um plano. Certo. Ninguém nunca tentou tratar isso como serviço. Pode até ajudar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it! Ha. Go on then, tell me the first item and I'll object to it.
    >>  ............................................
    pt  Consertar! Ha. Vá em frente, diga o primeiro item e eu vou objetar.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.offer_plan/1
    en  Do something about it! You make it sound like a chore list. ...Maybe it is one.
    >>  ............................................
    pt  Fazer algo a respeito! Você faz soar como lista de tarefas. ...Talvez seja uma.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.offer_plan/2
    en  A plan. Right. Nobody's ever tried treating it as a job of work. It might even help.
    >>  ............................................
    pt  Um plano. Certo. Ninguém nunca tentou tratar isso como serviço. Pode até ajudar.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.offer_plan/3
    en  Fix it! Ha. Go on then, tell me the first item and I'll object to it.
    >>  ............................................
    pt  Consertar! Ha. Vá em frente, diga o primeiro item e eu vou objetar.
    >>  ............................................
```

</details>


### Button `just_listen` — "You don't need a plan. I'm here."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.resume.plan.to.fears.plan`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.plan.just_listen` — accepted phrasings: "you do not need a plan"; "i am here"; "i am just listening"; "i do not need to fix it"
  - the message must contain one of: `here`, `listen`, `fix`
  - scored words: `here`(1.2), `listen`(1.5), `fix`(1.2), `need`(0.4)

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.respond.just_listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.respond.just_listen   [32 chars]
    en  You don't need a plan. I'm here.
    >>  ............................................
    pt  Você não precisa de um plano. Estou aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +3, warmth +1  _(recorded under topic `fears.plan.just_listen`)_
- Does: arc `fears` — hold
- Then opens: `conversations.arc.fears.plan.followup`
- …where the player's next choices will be: "Then I'll be there for that bit." | "What would make that easier?" | "I can't promise. But I'll try." | "Take your time with it."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.just_listen
WHO    VILLAGER — what the player reads after pressing "You don't need a plan. I'm here."
       spoken on: conversations.arc.fears.plan.respond, button `just_listen`
       leaves the player on: conversations.arc.fears.plan.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.just_listen.to.fears.plan`: the villager accepts. Subject `fears.plan`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.plan.just_listen/1   [59 chars]
    en  ...No plan. Just this. That's better than a plan, actually.
    >>  ............................................
    pt  ...Sem plano. Só isso. Na verdade é melhor que um plano.
    >>  ............................................
  dialogue.conversations.fears.plan.just_listen/2   [58 chars]
    en  Everyone wants to fix it. You just stood there. Thank you.
    >>  ............................................
    pt  Todo mundo quer consertar. Você só ficou aí. Obrigado.
    >>  ............................................
  dialogue.conversations.fears.plan.just_listen/3   [41 chars]
    en  Being here is the whole of it, some days.
    >>  ............................................
    pt  Estar aqui é tudo, em alguns dias.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this. That's better than a plan, %1$s, and I didn't know I was allowed to want it.
    >>  ............................................
    pt  ...Sem plano. Só isso. É melhor que um plano, %1$s, e eu não sabia que podia querer isso.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.just_listen/2
    en  No fixing. Everyone fixes. Being not-fixed at is a thing I'd forgotten existed.
    >>  ............................................
    pt  Sem consertar. Todo mundo conserta. Não ser consertado é algo que eu esqueci que existia.
    >>  ............................................
  anxious.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. I'll probably cry, and I'd rather warn you than surprise you.
    >>  ............................................
    pt  Só escutar. Eu provavelmente vou chorar, e prefiro avisar a te surpreender.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. Plans have their place and this isn't it.
    >>  ............................................
    pt  Sem plano. Só isso. Planos têm lugar e não é aqui.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. Some things want time rather than solutions.
    >>  ............................................
    pt  Certo. Sem consertar. Algumas coisas querem tempo em vez de soluções.
    >>  ............................................
  athletic.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's the one that has ever actually helped.
    >>  ............................................
    pt  Só escutar. É o que sempre ajudou de verdade.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. That's better than a plan.
    >>  ............................................
    pt  Sem plano. Só isso. É melhor que um plano.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. I'd not known that was on offer.
    >>  ............................................
    pt  Certo. Sem consertar. Eu não sabia que isso estava disponível.
    >>  ............................................
  confident.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Fine. That's what I actually wanted.
    >>  ............................................
    pt  Só escutar. Tudo bem. É o que eu queria de verdade.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. That's better than a plan.
    >>  ............................................
    pt  Sem plano. Só isso. É melhor que um plano.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. I'd not known that was on offer.
    >>  ............................................
    pt  Certo. Sem consertar. Eu não sabia que isso estava disponível.
    >>  ............................................
  crabby.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Fine. That's what I actually wanted.
    >>  ............................................
    pt  Só escutar. Tudo bem. É o que eu queria de verdade.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this. That's better than a plan, %1$s.
    >>  ............................................
    pt  ...Sem plano. Só isso. É melhor que um plano, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.just_listen/2
    en  No fixing. Then sit down and I'll say it in whatever order it comes.
    >>  ............................................
    pt  Sem consertar. Então sente-se e eu digo na ordem que vier.
    >>  ............................................
  extroverted.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's what I'd have asked for if I'd known I could.
    >>  ............................................
    pt  Só escutar. É o que eu teria pedido se soubesse que podia.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this. That's better than a plan, %1$s.
    >>  ............................................
    pt  ...Sem plano. Só isso. É melhor que um plano, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.just_listen/2
    en  No fixing. Then sit down and I'll say it in whatever order it comes.
    >>  ............................................
    pt  Sem consertar. Então sente-se e eu digo na ordem que vier.
    >>  ............................................
  flirty.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's what I'd have asked for if I'd known I could.
    >>  ............................................
    pt  Só escutar. É o que eu teria pedido se soubesse que podia.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this. That's better than a plan, %1$s.
    >>  ............................................
    pt  ...Sem plano. Só isso. É melhor que um plano, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.just_listen/2
    en  No fixing. Then sit down and I'll say it in whatever order it comes.
    >>  ............................................
    pt  Sem consertar. Então sente-se e eu digo na ordem que vier.
    >>  ............................................
  friendly.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's what I'd have asked for if I'd known I could.
    >>  ............................................
    pt  Só escutar. É o que eu teria pedido se soubesse que podia.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this. That's better than a plan, %1$s, and I didn't know I was allowed to want it.
    >>  ............................................
    pt  ...Sem plano. Só isso. É melhor que um plano, %1$s, e eu não sabia que podia querer isso.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.just_listen/2
    en  No fixing. Everyone fixes. Being not-fixed at is a thing I'd forgotten existed.
    >>  ............................................
    pt  Sem consertar. Todo mundo conserta. Não ser consertado é algo que eu esqueci que existia.
    >>  ............................................
  gloomy.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. I'll probably cry, and I'd rather warn you than surprise you.
    >>  ............................................
    pt  Só escutar. Eu provavelmente vou chorar, e prefiro avisar a te surpreender.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. That's better than a plan.
    >>  ............................................
    pt  Sem plano. Só isso. É melhor que um plano.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. I'd not known that was on offer.
    >>  ............................................
    pt  Certo. Sem consertar. Eu não sabia que isso estava disponível.
    >>  ............................................
  greedy.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Fine. That's what I actually wanted.
    >>  ............................................
    pt  Só escutar. Tudo bem. É o que eu queria de verdade.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. That's better than a plan.
    >>  ............................................
    pt  Sem plano. Só isso. É melhor que um plano.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. I'd not known that was on offer.
    >>  ............................................
    pt  Certo. Sem consertar. Eu não sabia que isso estava disponível.
    >>  ............................................
  grumpy.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Fine. That's what I actually wanted.
    >>  ............................................
    pt  Só escutar. Tudo bem. É o que eu queria de verdade.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this.
    >>  ............................................
    pt  ...Sem plano. Só isso.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing.
    >>  ............................................
    pt  Certo. Sem consertar.
    >>  ............................................
  introverted.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Good.
    >>  ............................................
    pt  Só escutar. Bom.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. Plans have their place and this isn't it.
    >>  ............................................
    pt  Sem plano. Só isso. Planos têm lugar e não é aqui.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. Some things want time rather than solutions.
    >>  ............................................
    pt  Certo. Sem consertar. Algumas coisas querem tempo em vez de soluções.
    >>  ............................................
  lazy.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's the one that has ever actually helped.
    >>  ............................................
    pt  Só escutar. É o que sempre ajudou de verdade.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this.
    >>  ............................................
    pt  ...Sem plano. Só isso.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing.
    >>  ............................................
    pt  Certo. Sem consertar.
    >>  ............................................
  odd.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Good.
    >>  ............................................
    pt  Só escutar. Bom.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. Plans have their place and this isn't it.
    >>  ............................................
    pt  Sem plano. Só isso. Planos têm lugar e não é aqui.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. Some things want time rather than solutions.
    >>  ............................................
    pt  Certo. Sem consertar. Algumas coisas querem tempo em vez de soluções.
    >>  ............................................
  peaceful.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's the one that has ever actually helped.
    >>  ............................................
    pt  Só escutar. É o que sempre ajudou de verdade.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.just_listen/1
    en  No plan! Just this. That's better than a plan, actually, and I've had plans.
    >>  ............................................
    pt  Sem plano! Só isso. É melhor que um plano, na verdade, e eu já tive planos.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.just_listen/2
    en  Right — no fixing. What a relief. Everyone arrives with a list.
    >>  ............................................
    pt  Certo — sem consertar. Que alívio. Todo mundo chega com uma lista.
    >>  ............................................
  peppy.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening! Nobody offers that. Everybody offers advice.
    >>  ............................................
    pt  Só escutar! Ninguém oferece isso. Todo mundo oferece conselho.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.just_listen/1
    en  No plan! Just this. That's better than a plan, actually, and I've had plans.
    >>  ............................................
    pt  Sem plano! Só isso. É melhor que um plano, na verdade, e eu já tive planos.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.just_listen/2
    en  Right — no fixing. What a relief. Everyone arrives with a list.
    >>  ............................................
    pt  Certo — sem consertar. Que alívio. Todo mundo chega com uma lista.
    >>  ............................................
  playful.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening! Nobody offers that. Everybody offers advice.
    >>  ............................................
    pt  Só escutar! Ninguém oferece isso. Todo mundo oferece conselho.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.just_listen/1
    en  No plan. Just this. Plans have their place and this isn't it.
    >>  ............................................
    pt  Sem plano. Só isso. Planos têm lugar e não é aqui.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing. Some things want time rather than solutions.
    >>  ............................................
    pt  Certo. Sem consertar. Algumas coisas querem tempo em vez de soluções.
    >>  ............................................
  relaxed.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. That's the one that has ever actually helped.
    >>  ............................................
    pt  Só escutar. É o que sempre ajudou de verdade.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this. That's better than a plan, %1$s, and I didn't know I was allowed to want it.
    >>  ............................................
    pt  ...Sem plano. Só isso. É melhor que um plano, %1$s, e eu não sabia que podia querer isso.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.just_listen/2
    en  No fixing. Everyone fixes. Being not-fixed at is a thing I'd forgotten existed.
    >>  ............................................
    pt  Sem consertar. Todo mundo conserta. Não ser consertado é algo que eu esqueci que existia.
    >>  ............................................
  sensitive.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. I'll probably cry, and I'd rather warn you than surprise you.
    >>  ............................................
    pt  Só escutar. Eu provavelmente vou chorar, e prefiro avisar a te surpreender.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.just_listen/1
    en  ...No plan. Just this.
    >>  ............................................
    pt  ...Sem plano. Só isso.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.just_listen/2
    en  Right. No fixing.
    >>  ............................................
    pt  Certo. Sem consertar.
    >>  ............................................
  shy.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening. Good.
    >>  ............................................
    pt  Só escutar. Bom.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.just_listen/1
    en  No plan! Just this. That's better than a plan, actually, and I've had plans.
    >>  ............................................
    pt  Sem plano! Só isso. É melhor que um plano, na verdade, e eu já tive planos.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.just_listen/2
    en  Right — no fixing. What a relief. Everyone arrives with a list.
    >>  ............................................
    pt  Certo — sem consertar. Que alívio. Todo mundo chega com uma lista.
    >>  ............................................
  upbeat.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening! Nobody offers that. Everybody offers advice.
    >>  ............................................
    pt  Só escutar! Ninguém oferece isso. Todo mundo oferece conselho.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.just_listen/1
    en  No plan! Just this. That's better than a plan, actually, and I've had plans.
    >>  ............................................
    pt  Sem plano! Só isso. É melhor que um plano, na verdade, e eu já tive planos.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.just_listen/2
    en  Right — no fixing. What a relief. Everyone arrives with a list.
    >>  ............................................
    pt  Certo — sem consertar. Que alívio. Todo mundo chega com uma lista.
    >>  ............................................
  witty.dialogue.conversations.fears.plan.just_listen/3
    en  Just listening! Nobody offers that. Everybody offers advice.
    >>  ............................................
    pt  Só escutar! Ninguém oferece isso. Todo mundo oferece conselho.
    >>  ............................................
```

</details>


### Button `leave` — "Take your time."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.resume.plan.to.fears.plan` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.fears.plan.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.fears.plan.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.fears.plan.respond.leave   [15 chars]
    en  Take your time.
    >>  ............................................
    pt  Vá no seu tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.plan.leave
WHO    VILLAGER — what the player reads after pressing "Take your time."
       spoken on: conversations.arc.fears.plan.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.plan.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.plan.leave/1   [38 chars]
    en  Aye. It'll wait. It's good at waiting.
    >>  ............................................
    pt  Tá. Ela espera. É boa em esperar.
    >>  ............................................
  dialogue.conversations.fears.plan.leave/2   [24 chars]
    en  Go on. We'll pick it up.
    >>  ............................................
    pt  Pode ir. A gente retoma.
    >>  ............................................
  dialogue.conversations.fears.plan.leave/3   [46 chars]
    en  Right. Thank you for coming back to it at all.
    >>  ............................................
    pt  Certo. Obrigado por ter voltado ao assunto.
    >>  ............................................
```

---


## `conversations.fears`

**Reached from 4 route(s):** `conversations.cat.personal` / `fears`; `conversations.fears` / `challenge`; `conversations.fears` / `press`; `conversations.fears` / `press`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.challenge.guard` — e.g. "That's easy to say from where you're standing. We're not there yet, you and I."
- `conversations.fears.first` — e.g. "Honestly? Thunder. And the thing that scratched at my door two winters back. Mostly the thunder."
- `conversations.fears.press.guard` — e.g. "The rest isn't for sale, %1$s. It's not even for borrowing. Not yet."
- `conversations.fears.press.partial` — e.g. "Some of it, maybe. Not tonight. Tonight's too much like the night it happened."


```text
POOL   dialogue key: dialogue.conversations.fears
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.fears
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.fears   [23 chars]
    en  What are you afraid of?
    >>  ............................................
    pt  Do que você tem medo?
    >>  ............................................
```


### Button `comfort` — "That sounds hard to carry."

*stance family `empathy` · tone `gentle` · answers the beat(s) `fears.challenge.guard.to.fears`, `fears.first.to.fears`, `fears.press.guard.to.fears`, `fears.press.partial.to.fears`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.comfort` — accepted phrasings: "sounds hard"; "hard to carry"; "that sounds difficult"; "so sorry"
  - the message must contain one of: `hard`, `carry`, `difficult`, `sorry`
  - scored words: `hard`(1.2), `carry`(1.0), `difficult`(1.0), `sorry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.fears.comfort
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.fears
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.fears.comfort   [26 chars]
    en  That sounds hard to carry.
    >>  ............................................
    pt  Isso parece pesado de carregar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.comfort` lands on tier **crit** (axis warmth, difficulty 25)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 6
- Does: disposition — warmth +4, trust +2, familiarity +1  _(recorded under topic `fears.comfort`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.comfort.crit
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.fears, button `comfort`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.comfort.crit.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.comfort.crit/1   [142 chars]
    en  ...You mean that. You're not just being polite. Then here's the part I never say out loud: it's worse when nobody knows. It's smaller already.
    >>  ............................................
    pt  ...Você falou sério. Não está só sendo educado. Então aqui vai a parte que eu nunca digo em voz alta: é pior quando ninguém sabe. Já está menor.
    >>  ............................................
  dialogue.conversations.fears.comfort.crit/2   [83 chars]
    en  Careful, %1$s. Kindness like that makes a person start hoping. ...Too late. Hoping.
    >>  ............................................
    pt  Cuidado, %1$s. Gentileza dessas faz a pessoa começar a ter esperança. ...Tarde demais. Esperançoso.
    >>  ............................................
```


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.comfort` lands on tier **success** (axis warmth, difficulty 25)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 5
- Does: disposition — warmth +3, familiarity +1  _(recorded under topic `fears.comfort`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.comfort.thanks
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.fears, button `comfort`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.comfort.thanks.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.comfort.thanks/1   [45 chars]
    en  ...Thank you. Most people change the subject.
    >>  ............................................
    pt  ...Obrigado. A maioria das pessoas muda de assunto.
    >>  ............................................
  dialogue.conversations.fears.comfort.thanks/2   [35 chars]
    en  You're gentler than you look, %1$s.
    >>  ............................................
    pt  Você é mais gentil do que aparenta, %1$s.
    >>  ............................................
  dialogue.conversations.fears.comfort.thanks/3   [74 chars]
    en  Funny how a fear shrinks a little every time somebody doesn't laugh at it.
    >>  ............................................
    pt  Engraçado como um medo encolhe um pouco toda vez que alguém não ri dele.
    >>  ............................................
```


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.comfort` lands on tier **partial** (axis warmth, difficulty 25)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.comfort.brushoff
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.fears, button `comfort`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.comfort.brushoff.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.comfort.brushoff/1   [74 chars]
    en  I'm fine. Forget I said anything. The thunder and I have an understanding.
    >>  ............................................
    pt  Estou bem. Esquece o que eu disse. O trovão e eu temos um acordo.
    >>  ............................................
  dialogue.conversations.fears.comfort.brushoff/2   [61 chars]
    en  Yes, well. Enough about that. How's the weather treating you?
    >>  ............................................
    pt  Sim, bom. Chega disso. Como o tempo tem te tratado?
    >>  ............................................
  dialogue.conversations.fears.comfort.brushoff/3   [75 chars]
    en  Don't make it a thing. It's not a thing. It's a perfectly reasonable dread.
    >>  ............................................
    pt  Não faz disso um caso. Não é um caso. É um pavor perfeitamente razoável.
    >>  ............................................
```


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.comfort` lands on tier **rebuff** (axis warmth, difficulty 25)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: disposition — tension +3  _(recorded under topic `fears.comfort`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.comfort.rebuff
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.fears, button `comfort`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.comfort.rebuff.terminal`: the villager refuses. Subject `fears.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.comfort.rebuff/1   [62 chars]
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei isso pra você fazer carinho na cabeça.
    >>  ............................................
  dialogue.conversations.fears.comfort.rebuff/2   [53 chars]
    en  Soft words, %1$s? It isn't a soft thing. Leave it be.
    >>  ............................................
    pt  Palavras macias, %1$s? Não é uma coisa macia. Deixa quieto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I know you mean well and I can't take it kindly just now.
    >>  ............................................
    pt  Não. Eu sei que você quer bem e eu não consigo receber bem agora.
    >>  ............................................
  anxious.dialogue.conversations.fears.comfort.rebuff/2
    en  Please don't soften it, %1$s. Softening it makes me feel foolish for having said it.
    >>  ............................................
    pt  Por favor não suavize, %1$s. Suavizar me faz sentir bobo por ter dito.
    >>  ............................................
  athletic.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  athletic.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  confident.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  confident.dialogue.conversations.fears.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  crabby.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  crabby.dialogue.conversations.fears.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  extroverted.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't, %1$s. I didn't tell you so you'd make it smaller.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu não contei pra você diminuir.
    >>  ............................................
  extroverted.dialogue.conversations.fears.comfort.rebuff/2
    en  That's kindly meant and it lands wrong. I'd rather you just sat there.
    >>  ............................................
    pt  É bem-intencionado e cai errado. Eu preferia que você só ficasse aí.
    >>  ............................................
  flirty.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't, %1$s. I didn't tell you so you'd make it smaller.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu não contei pra você diminuir.
    >>  ............................................
  flirty.dialogue.conversations.fears.comfort.rebuff/2
    en  That's kindly meant and it lands wrong. I'd rather you just sat there.
    >>  ............................................
    pt  É bem-intencionado e cai errado. Eu preferia que você só ficasse aí.
    >>  ............................................
  friendly.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't, %1$s. I didn't tell you so you'd make it smaller.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu não contei pra você diminuir.
    >>  ............................................
  friendly.dialogue.conversations.fears.comfort.rebuff/2
    en  That's kindly meant and it lands wrong. I'd rather you just sat there.
    >>  ............................................
    pt  É bem-intencionado e cai errado. Eu preferia que você só ficasse aí.
    >>  ............................................
  gloomy.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I know you mean well and I can't take it kindly just now.
    >>  ............................................
    pt  Não. Eu sei que você quer bem e eu não consigo receber bem agora.
    >>  ............................................
  gloomy.dialogue.conversations.fears.comfort.rebuff/2
    en  Please don't soften it, %1$s. Softening it makes me feel foolish for having said it.
    >>  ............................................
    pt  Por favor não suavize, %1$s. Suavizar me faz sentir bobo por ter dito.
    >>  ............................................
  greedy.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  greedy.dialogue.conversations.fears.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  grumpy.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  grumpy.dialogue.conversations.fears.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  introverted.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. Please.
    >>  ............................................
    pt  Não. Por favor.
    >>  ............................................
  introverted.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Not that.
    >>  ............................................
    pt  Não. Isso não.
    >>  ............................................
  lazy.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  lazy.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  odd.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. Please.
    >>  ............................................
    pt  Não. Por favor.
    >>  ............................................
  odd.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Not that.
    >>  ............................................
    pt  Não. Isso não.
    >>  ............................................
  peaceful.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  peppy.dialogue.conversations.fears.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  peppy.dialogue.conversations.fears.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
  playful.dialogue.conversations.fears.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  playful.dialogue.conversations.fears.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. I know you mean well and I can't take it kindly just now.
    >>  ............................................
    pt  Não. Eu sei que você quer bem e eu não consigo receber bem agora.
    >>  ............................................
  sensitive.dialogue.conversations.fears.comfort.rebuff/2
    en  Please don't soften it, %1$s. Softening it makes me feel foolish for having said it.
    >>  ............................................
    pt  Por favor não suavize, %1$s. Suavizar me faz sentir bobo por ter dito.
    >>  ............................................
  shy.dialogue.conversations.fears.comfort.rebuff/1
    en  Don't. Please.
    >>  ............................................
    pt  Não. Por favor.
    >>  ............................................
  shy.dialogue.conversations.fears.comfort.rebuff/2
    en  No. Not that.
    >>  ............................................
    pt  Não. Isso não.
    >>  ............................................
  upbeat.dialogue.conversations.fears.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
  witty.dialogue.conversations.fears.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  witty.dialogue.conversations.fears.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `5`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 5
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.comfort.plain
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.fears, button `comfort`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.comfort.plain.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.comfort.plain/1   [78 chars]
    en  It is. It sits with me most days. ...Thank you for not making a face about it.
    >>  ............................................
    pt  É sim. Fica comigo quase todo dia. ...Obrigado por não fazer cara feia.
    >>  ............................................
  dialogue.conversations.fears.comfort.plain/2   [70 chars]
    en  That's kind. It doesn't fix anything, but it's kind, and I'll take it.
    >>  ............................................
    pt  Isso é gentil. Não resolve nada, mas é gentil, e eu aceito.
    >>  ............................................
  dialogue.conversations.fears.comfort.plain/3   [72 chars]
    en  Mm. Saying it to somebody who doesn't laugh takes a bit of the edge off.
    >>  ............................................
    pt  Hm. Falar isso para alguém que não ri tira um pouco do peso.
    >>  ............................................
```


### Button `challenge` — "You could face it. I'd stand with you."

*stance family `challenge` · tone `blunt` · answers the beat(s) `fears.challenge.guard.to.fears`, `fears.first.to.fears`, `fears.press.guard.to.fears`, `fears.press.partial.to.fears`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.challenge` — accepted phrasings: "you could face it"; "stand with you"; "face it"; "face your fear"
  - the message must contain one of: `face`, `stand`, `brave`, `overcome`
  - scored words: `face`(1.2), `stand`(1.2), `brave`(1.0), `overcome`(0.8)

```text
POOL   dialogue key: dialogue.conversations.fears.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.fears
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.fears.challenge   [38 chars]
    en  You could face it. I'd stand with you.
    >>  ............................................
    pt  Você poderia encarar isso. Eu ficaria do seu lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +100 when disposition trust <= 34
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.fears`
- …where the player's next choices will be: "That sounds hard to carry." | "You could face it. I'd stand with you." | "Tell me the rest of it." | "I'm scared of that too." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.fears.challenge.guard
WHO    VILLAGER — what the player reads after pressing "You could face it. I'd stand with you."
       spoken on: conversations.fears, button `challenge`
       leaves the player on: conversations.fears
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.challenge.guard.to.fears`: the villager accepts. Subject `fears`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.challenge.guard/1   [78 chars]
    en  That's easy to say from where you're standing. We're not there yet, you and I.
    >>  ............................................
    pt  É fácil falar daí de onde você está. A gente ainda não chegou nesse ponto, você e eu.
    >>  ............................................
  dialogue.conversations.fears.challenge.guard/2   [86 chars]
    en  Face it. Just like that. ...Know a person before you volunteer them for bravery, %1$s.
    >>  ............................................
    pt  Encarar. Assim, do nada. ...Conheça a pessoa antes de oferecer coragem por ela, %1$s.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.challenge` lands on tier **crit** (axis trust, difficulty 45)
- Fires when: RULED OUT when disposition trust <= 34  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 6
- Does: disposition — respect +6, trust +3, familiarity +1  _(recorded under topic `fears.challenge`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.challenge.crit
WHO    VILLAGER — what the player reads after pressing "You could face it. I'd stand with you."
       spoken on: conversations.fears, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.challenge.crit.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.challenge.crit/1   [133 chars]
    en  ...Nobody ever offered the 'with you' part before. That's the part that matters. Alright. Next time it comes, I don't run. Witnessed.
    >>  ............................................
    pt  ...Ninguém nunca ofereceu a parte do "do seu lado" antes. Essa é a parte que importa. Tudo bem. Da próxima vez que vier, eu não corro. Você é testemunha.
    >>  ............................................
  dialogue.conversations.fears.challenge.crit/2   [100 chars]
    en  You'd actually stand there with me. Huh. — Fine. I'm holding you to it, and you're holding me to it.
    >>  ............................................
    pt  Você ficaria mesmo ali comigo. Hm. — Tudo bem. Vou cobrar de você, e você cobra de mim.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.challenge` lands on tier **success** (axis trust, difficulty 45)
- Fires when: RULED OUT when disposition trust <= 34  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: disposition — respect +4, familiarity +1  _(recorded under topic `fears.challenge`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.challenge.success
WHO    VILLAGER — what the player reads after pressing "You could face it. I'd stand with you."
       spoken on: conversations.fears, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.challenge.success.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.challenge.success/1   [64 chars]
    en  Maybe. With a witness, maybe. Don't let me back out of it later.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixa voltar atrás depois.
    >>  ............................................
  dialogue.conversations.fears.challenge.success/2   [98 chars]
    en  You make it sound almost possible. Ask me again when it's close, and we'll learn what I'm made of.
    >>  ............................................
    pt  Você faz parecer quase possível. Me pergunte de novo quando estiver perto, e a gente descobre do que eu sou feito.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.challenge` lands on tier **partial** (axis trust, difficulty 45)
- Fires when: RULED OUT when disposition trust <= 34  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.challenge.partial
WHO    VILLAGER — what the player reads after pressing "You could face it. I'd stand with you."
       spoken on: conversations.fears, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.challenge.partial.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.challenge.partial/1   [106 chars]
    en  Easy for you — you've got somewhere else to be when it goes wrong. ...But it wasn't nothing, you offering.
    >>  ............................................
    pt  Fácil pra você — você tem pra onde ir quando der errado. ...Mas não foi pouca coisa, você oferecer.
    >>  ............................................
  dialogue.conversations.fears.challenge.partial/2   [63 chars]
    en  Hm. Half of me believes you. The other half keeps the accounts.
    >>  ............................................
    pt  Hm. Metade de mim acredita em você. A outra metade faz as contas.
    >>  ............................................
```


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.challenge` lands on tier **rebuff** (axis trust, difficulty 45)
- Fires when: RULED OUT when disposition trust <= 34  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: disposition — tension +4  _(recorded under topic `fears.challenge`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.challenge.rebuff
WHO    VILLAGER — what the player reads after pressing "You could face it. I'd stand with you."
       spoken on: conversations.fears, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.challenge.rebuff.terminal`: the villager refuses. Subject `fears.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.challenge.rebuff/1   [84 chars]
    en  Don't make my fear a test of your character, %1$s. It isn't yours to be brave about.
    >>  ............................................
    pt  Não transforma o meu medo em teste do seu caráter, %1$s. Não cabe a você ser corajoso com ele.
    >>  ............................................
  dialogue.conversations.fears.challenge.rebuff/2   [62 chars]
    en  Stand with me? You'll be gone by frost like everyone else. No.
    >>  ............................................
    pt  Ficar do meu lado? Você vai ter ido embora antes da geada, como todo mundo. Não.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.challenge.rebuff/1
    en  Please don't. It took a great deal to say it once, %1$s.
    >>  ............................................
    pt  Por favor, não. Custou muito dizer uma vez, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.challenge.rebuff/2
    en  Don't make it a test. I'd only just got the words out.
    >>  ............................................
    pt  Não faça disso um teste. Eu tinha acabado de conseguir dizer.
    >>  ............................................
  athletic.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  athletic.dialogue.conversations.fears.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  confident.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  confident.dialogue.conversations.fears.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  crabby.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  crabby.dialogue.conversations.fears.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  extroverted.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't, %1$s. I gave you that; I didn't hand you a challenge to win.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te dei aquilo; não te entreguei um desafio pra vencer.
    >>  ............................................
  extroverted.dialogue.conversations.fears.challenge.rebuff/2
    en  That's not what I was asking for, and I think you know it.
    >>  ............................................
    pt  Não é isso que eu estava pedindo, e eu acho que você sabe.
    >>  ............................................
  flirty.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't, %1$s. I gave you that; I didn't hand you a challenge to win.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te dei aquilo; não te entreguei um desafio pra vencer.
    >>  ............................................
  flirty.dialogue.conversations.fears.challenge.rebuff/2
    en  That's not what I was asking for, and I think you know it.
    >>  ............................................
    pt  Não é isso que eu estava pedindo, e eu acho que você sabe.
    >>  ............................................
  friendly.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't, %1$s. I gave you that; I didn't hand you a challenge to win.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te dei aquilo; não te entreguei um desafio pra vencer.
    >>  ............................................
  friendly.dialogue.conversations.fears.challenge.rebuff/2
    en  That's not what I was asking for, and I think you know it.
    >>  ............................................
    pt  Não é isso que eu estava pedindo, e eu acho que você sabe.
    >>  ............................................
  gloomy.dialogue.conversations.fears.challenge.rebuff/1
    en  Please don't. It took a great deal to say it once, %1$s.
    >>  ............................................
    pt  Por favor, não. Custou muito dizer uma vez, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.challenge.rebuff/2
    en  Don't make it a test. I'd only just got the words out.
    >>  ............................................
    pt  Não faça disso um teste. Eu tinha acabado de conseguir dizer.
    >>  ............................................
  greedy.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  greedy.dialogue.conversations.fears.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  grumpy.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  introverted.dialogue.conversations.fears.challenge.rebuff/1
    en  No. Not like that.
    >>  ............................................
    pt  Não. Assim não.
    >>  ............................................
  introverted.dialogue.conversations.fears.challenge.rebuff/2
    en  Don't. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça isso. Não é seu pra ser corajoso.
    >>  ............................................
  lazy.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  lazy.dialogue.conversations.fears.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  odd.dialogue.conversations.fears.challenge.rebuff/1
    en  No. Not like that.
    >>  ............................................
    pt  Não. Assim não.
    >>  ............................................
  odd.dialogue.conversations.fears.challenge.rebuff/2
    en  Don't. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça isso. Não é seu pra ser corajoso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  peppy.dialogue.conversations.fears.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  peppy.dialogue.conversations.fears.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
  playful.dialogue.conversations.fears.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  playful.dialogue.conversations.fears.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  relaxed.dialogue.conversations.fears.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  sensitive.dialogue.conversations.fears.challenge.rebuff/1
    en  Please don't. It took a great deal to say it once, %1$s.
    >>  ............................................
    pt  Por favor, não. Custou muito dizer uma vez, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.challenge.rebuff/2
    en  Don't make it a test. I'd only just got the words out.
    >>  ............................................
    pt  Não faça disso um teste. Eu tinha acabado de conseguir dizer.
    >>  ............................................
  shy.dialogue.conversations.fears.challenge.rebuff/1
    en  No. Not like that.
    >>  ............................................
    pt  Não. Assim não.
    >>  ............................................
  shy.dialogue.conversations.fears.challenge.rebuff/2
    en  Don't. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça isso. Não é seu pra ser corajoso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
  witty.dialogue.conversations.fears.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  witty.dialogue.conversations.fears.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
```

</details>


**Outcome 6 of 6** — base weight `3`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Fires when: RULED OUT when disposition trust <= 34  _(chance -1000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.challenge.plain
WHO    VILLAGER — what the player reads after pressing "You could face it. I'd stand with you."
       spoken on: conversations.fears, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.challenge.plain.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.challenge.plain/1   [65 chars]
    en  Face it. You say that like it's a door I could just walk through.
    >>  ............................................
    pt  Encarar. Você fala como se fosse uma porta que dá para simplesmente atravessar.
    >>  ............................................
  dialogue.conversations.fears.challenge.plain/2   [72 chars]
    en  Maybe. Ask me when it's actually in front of me and we'll both find out.
    >>  ............................................
    pt  Talvez. Me pergunte quando estiver na minha frente e nós dois descobrimos.
    >>  ............................................
  dialogue.conversations.fears.challenge.plain/3   [71 chars]
    en  I've been told that before. It lands differently coming from you, %1$s.
    >>  ............................................
    pt  Já me disseram isso antes. Vindo de você soa diferente, %1$s.
    >>  ............................................
```


### Button `press` — "Tell me the rest of it."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `fears.challenge.guard.to.fears`, `fears.first.to.fears`, `fears.press.guard.to.fears`, `fears.press.partial.to.fears`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.press` — accepted phrasings: "tell me the rest"; "the rest of it"; "go on"; "tell me more"
  - the message must contain one of: `rest`, `more`, `continue`
  - scored words: `rest`(1.2), `more`(1.0), `continue`(1.0), `go`(0.4)

```text
POOL   dialogue key: dialogue.conversations.fears.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.fears
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.fears.press   [23 chars]
    en  Tell me the rest of it.
    >>  ............................................
    pt  Me conta o resto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +100 when disposition trust <= 29
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.fears`
- …where the player's next choices will be: "That sounds hard to carry." | "You could face it. I'd stand with you." | "Tell me the rest of it." | "I'm scared of that too." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.fears.press.guard
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.fears, button `press`
       leaves the player on: conversations.fears
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.press.guard.to.fears`: the villager resists. Subject `fears`, polarity `neutral`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.press.guard/1   [68 chars]
    en  The rest isn't for sale, %1$s. It's not even for borrowing. Not yet.
    >>  ............................................
    pt  O resto não está à venda, %1$s. Nem emprestado. Ainda não.
    >>  ............................................
  dialogue.conversations.fears.press.guard/2   [78 chars]
    en  There's a door in that story I don't open for acquaintances. No offense meant.
    >>  ............................................
    pt  Tem uma porta nessa história que eu não abro pra conhecidos. Sem ofensa.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.press` lands on tier **crit** (axis trust, difficulty 55)
- Fires when: RULED OUT when disposition trust <= 29  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 5
- Does: disposition — trust +4, familiarity +2  _(recorded under topic `fears.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.press.crit
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.fears, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.press.crit.terminal`: the villager resists. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.press.crit/1   [146 chars]
    en  ...You don't flinch, do you. Alright. The rest — the true shape of it, the part I skip even alone at night. Sit down. I've never told anyone this.
    >>  ............................................
    pt  ...Você não recua, né. Tudo bem. O resto — o formato verdadeiro daquilo, a parte que eu pulo até sozinho de noite. Senta. Nunca contei isso pra ninguém.
    >>  ............................................
  dialogue.conversations.fears.press.crit/2   [111 chars]
    en  Persistent. And gentle about it, which is worse. ...Fine. The whole of it, then. But it stays between us, %1$s.
    >>  ............................................
    pt  Insistente. E gentil ao insistir, o que é pior. ...Tá bom. Então a coisa toda. Mas fica entre nós, %1$s.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.press` lands on tier **success** (axis trust, difficulty 55)
- Fires when: RULED OUT when disposition trust <= 29  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: disposition — trust +2, familiarity +1  _(recorded under topic `fears.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.press.success
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.fears, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.press.success.terminal`: the villager resists. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.press.success/1   [120 chars]
    en  There's more, yes. Give me a breath. ...There. Now two of us are carrying it, and it's strange how much lighter that is.
    >>  ............................................
    pt  Tem mais, sim. Me dá um fôlego. ...Pronto. Agora somos dois carregando, e é estranho como isso fica mais leve.
    >>  ............................................
  dialogue.conversations.fears.press.success/2   [91 chars]
    en  You pull gently, I'll give you that. All right — a little more of it. The rest another day.
    >>  ............................................
    pt  Você puxa com jeito, isso eu reconheço. Tudo bem — um pouco mais. O resto fica pra outro dia.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.press` lands on tier **partial** (axis trust, difficulty 55)
- Fires when: RULED OUT when disposition trust <= 29  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.fears`
- …where the player's next choices will be: "That sounds hard to carry." | "You could face it. I'd stand with you." | "Tell me the rest of it." | "I'm scared of that too." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.fears.press.partial
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.fears, button `press`
       leaves the player on: conversations.fears
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.press.partial.to.fears`: the villager resists. Subject `fears`, polarity `neutral`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.press.partial/1   [78 chars]
    en  Some of it, maybe. Not tonight. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte disso, talvez. Hoje não. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  dialogue.conversations.fears.press.partial/2   [85 chars]
    en  That thread's still attached to something, %1$s. Later. I mean it — later, not never.
    >>  ............................................
    pt  Esse fio ainda está preso em alguma coisa, %1$s. Depois. E eu digo mesmo — depois, não nunca.
    >>  ............................................
```


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.press` lands on tier **rebuff** (axis trust, difficulty 55)
- Fires when: RULED OUT when disposition trust <= 29  _(chance -1000)_
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: disposition — tension +5  _(recorded under topic `fears.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.press.rebuff
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.fears, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.press.rebuff.terminal`: the villager refuses. Subject `fears.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.press.rebuff/1   [47 chars]
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Falei o que falei. O resto é meu. Deixa quieto.
    >>  ............................................
  dialogue.conversations.fears.press.rebuff/2   [87 chars]
    en  No. Some doors you don't knock on twice in a day, %1$s. I'd have thought you knew that.
    >>  ............................................
    pt  Não. Tem porta em que você não bate duas vezes no mesmo dia, %1$s. Achei que você soubesse disso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.press.rebuff/1
    en  Please. I've given you the part I could give, %1$s.
    >>  ............................................
    pt  Por favor. Eu te dei a parte que eu conseguia dar, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.press.rebuff/2
    en  Don't ask again. I'll say yes and then I'll regret it all night.
    >>  ............................................
    pt  Não pergunte de novo. Eu vou dizer sim e depois vou me arrepender a noite toda.
    >>  ............................................
  athletic.dialogue.conversations.fears.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  athletic.dialogue.conversations.fears.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  confident.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  confident.dialogue.conversations.fears.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  crabby.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  crabby.dialogue.conversations.fears.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  extroverted.dialogue.conversations.fears.press.rebuff/1
    en  I've told you more than I've told anyone, %1$s. Let that be enough tonight.
    >>  ............................................
    pt  Eu te contei mais do que contei a qualquer um, %1$s. Que baste por hoje.
    >>  ............................................
  extroverted.dialogue.conversations.fears.press.rebuff/2
    en  That's as far as I go, even with you. Especially with you, maybe.
    >>  ............................................
    pt  É até onde eu vou, mesmo com você. Especialmente com você, talvez.
    >>  ............................................
  flirty.dialogue.conversations.fears.press.rebuff/1
    en  I've told you more than I've told anyone, %1$s. Let that be enough tonight.
    >>  ............................................
    pt  Eu te contei mais do que contei a qualquer um, %1$s. Que baste por hoje.
    >>  ............................................
  flirty.dialogue.conversations.fears.press.rebuff/2
    en  That's as far as I go, even with you. Especially with you, maybe.
    >>  ............................................
    pt  É até onde eu vou, mesmo com você. Especialmente com você, talvez.
    >>  ............................................
  friendly.dialogue.conversations.fears.press.rebuff/1
    en  I've told you more than I've told anyone, %1$s. Let that be enough tonight.
    >>  ............................................
    pt  Eu te contei mais do que contei a qualquer um, %1$s. Que baste por hoje.
    >>  ............................................
  friendly.dialogue.conversations.fears.press.rebuff/2
    en  That's as far as I go, even with you. Especially with you, maybe.
    >>  ............................................
    pt  É até onde eu vou, mesmo com você. Especialmente com você, talvez.
    >>  ............................................
  gloomy.dialogue.conversations.fears.press.rebuff/1
    en  Please. I've given you the part I could give, %1$s.
    >>  ............................................
    pt  Por favor. Eu te dei a parte que eu conseguia dar, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.press.rebuff/2
    en  Don't ask again. I'll say yes and then I'll regret it all night.
    >>  ............................................
    pt  Não pergunte de novo. Eu vou dizer sim e depois vou me arrepender a noite toda.
    >>  ............................................
  greedy.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  greedy.dialogue.conversations.fears.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  grumpy.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  grumpy.dialogue.conversations.fears.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  introverted.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said.
    >>  ............................................
    pt  Eu disse o que disse.
    >>  ............................................
  introverted.dialogue.conversations.fears.press.rebuff/2
    en  No. The rest stays with me.
    >>  ............................................
    pt  Não. O resto fica comigo.
    >>  ............................................
  lazy.dialogue.conversations.fears.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  lazy.dialogue.conversations.fears.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  odd.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said.
    >>  ............................................
    pt  Eu disse o que disse.
    >>  ............................................
  odd.dialogue.conversations.fears.press.rebuff/2
    en  No. The rest stays with me.
    >>  ............................................
    pt  Não. O resto fica comigo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  peaceful.dialogue.conversations.fears.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  peppy.dialogue.conversations.fears.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  peppy.dialogue.conversations.fears.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
  playful.dialogue.conversations.fears.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  playful.dialogue.conversations.fears.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  relaxed.dialogue.conversations.fears.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  sensitive.dialogue.conversations.fears.press.rebuff/1
    en  Please. I've given you the part I could give, %1$s.
    >>  ............................................
    pt  Por favor. Eu te dei a parte que eu conseguia dar, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.press.rebuff/2
    en  Don't ask again. I'll say yes and then I'll regret it all night.
    >>  ............................................
    pt  Não pergunte de novo. Eu vou dizer sim e depois vou me arrepender a noite toda.
    >>  ............................................
  shy.dialogue.conversations.fears.press.rebuff/1
    en  I said what I said.
    >>  ............................................
    pt  Eu disse o que disse.
    >>  ............................................
  shy.dialogue.conversations.fears.press.rebuff/2
    en  No. The rest stays with me.
    >>  ............................................
    pt  Não. O resto fica comigo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  upbeat.dialogue.conversations.fears.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
  witty.dialogue.conversations.fears.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  witty.dialogue.conversations.fears.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
```

</details>


**Outcome 6 of 6** — base weight `3`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Fires when: RULED OUT when disposition trust <= 29  _(chance -1000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.press.plain
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.fears, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.press.plain.terminal`: the villager resists. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.press.plain/1   [69 chars]
    en  There's more to it. Whether you get it today is a different question.
    >>  ............................................
    pt  Tem mais coisa. Se você vai ouvir hoje é outra questão.
    >>  ............................................
  dialogue.conversations.fears.press.plain/2   [69 chars]
    en  Bold of you. Give me a moment to decide how much of it you're having.
    >>  ............................................
    pt  Ousado. Me dê um instante para decidir quanto disso você leva.
    >>  ............................................
  dialogue.conversations.fears.press.plain/3   [46 chars]
    en  Mm. The rest isn't a story I tell standing up.
    >>  ............................................
    pt  Hm. O resto não é história que eu conte de pé.
    >>  ............................................
```


### Button `share` — "I'm scared of that too."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `fears.challenge.guard.to.fears`, `fears.first.to.fears`, `fears.press.guard.to.fears`, `fears.press.partial.to.fears`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.share` — accepted phrasings: "me too"; "that too"; "scared of that too"; "same here"
  - the message must contain one of: `too`, `also`, `same`, `myself`
  - scored words: `too`(1.0), `also`(1.0), `same`(1.0), `myself`(0.8)

```text
POOL   dialogue key: dialogue.conversations.fears.share
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.fears
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.fears.share   [23 chars]
    en  I'm scared of that too.
    >>  ............................................
    pt  Eu também tenho medo disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `5`

- Fires when: weighted +4 when the personality is `friendly`
- Fires when: weighted +4 when the personality is `sensitive`
- Does: **hearts (raw MCA `positive` field)** = 6
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.share.grateful
WHO    VILLAGER — what the player reads after pressing "I'm scared of that too."
       spoken on: conversations.fears, button `share`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.share.grateful.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.share.grateful/1   [55 chars]
    en  You too? Huh. It's less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Hm. Fica menos pesado quando duas pessoas carregam.
    >>  ............................................
  dialogue.conversations.fears.share.grateful/2   [55 chars]
    en  Strange comfort, that. Two cowards make one brave pair.
    >>  ............................................
    pt  Que consolo estranho. Dois covardes fazem uma dupla corajosa.
    >>  ............................................
  dialogue.conversations.fears.share.grateful/3   [74 chars]
    en  Then we watch each other's backs. That's how it's supposed to work anyway.
    >>  ............................................
    pt  Então a gente cuida um das costas do outro. É assim que deveria funcionar, afinal.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +5 when the personality is `upbeat`
- Does: **hearts (raw MCA `positive` field)** = 3
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.share.joke
WHO    VILLAGER — what the player reads after pressing "I'm scared of that too."
       spoken on: conversations.fears, button `share`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.share.joke.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.share.joke/1   [65 chars]
    en  Then we'll hide under the same table when it storms. Bring bread.
    >>  ............................................
    pt  Então a gente se esconde debaixo da mesma mesa quando cair tempestade. Traz pão.
    >>  ............................................
  dialogue.conversations.fears.share.joke/2   [57 chars]
    en  Excellent. You bring the bread, I'll bring the trembling.
    >>  ............................................
    pt  Excelente. Você traz o pão, eu trago o tremor.
    >>  ............................................
  dialogue.conversations.fears.share.joke/3   [62 chars]
    en  A matched set of cowards! The village should charge admission.
    >>  ............................................
    pt  Um par combinando de covardes! O vilarejo devia cobrar ingresso.
    >>  ............................................
```


### Button `back` — "Let's talk about something else."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.challenge.guard.to.fears`, `fears.first.to.fears`, `fears.press.guard.to.fears`, `fears.press.partial.to.fears` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.fears.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.fears
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.fears.back   [32 chars]
    en  Let's talk about something else.
    >>  ............................................
    pt  Vamos falar de outra coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.

---


## `conversations.scene.fears.followup`

**Reached from 4 route(s):** `conversations.scene.fears.the_doorway_one.respond` / `ask_why_that_one`; `conversations.scene.fears.the_doorway_one.respond` / `share_your_own`; `conversations.scene.fears.the_night_one.respond` / `stay_with_it`; `conversations.scene.fears.the_night_one.respond` / `ask_how_long`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.fears.the_doorway_one.delighted` — e.g. "Good. Everybody has one and pretending otherwise is how the whole village ends up standing on ladders it hates."
- `conversations.scene.fears.the_doorway_one.explained` — e.g. "A cousin, a pond and a summer afternoon that everybody else remembers as funny."
- `conversations.scene.fears.the_night_one.answered` — e.g. "Since a particular winter. I could give you the month. That is how you know it is not a mood."
- `conversations.scene.fears.the_night_one.steadied` — e.g. "Thank you for not solving it. Everybody solves it, and being solved at makes a thing lonelier rather than smaller."


```text
POOL   dialogue key: dialogue.conversations.scene.fears.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.fears.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.fears.followup   [22 chars]
    en  Anything else on that?
    >>  ............................................
    pt  Mais alguma coisa sobre isso?
    >>  ............................................
```


### Button `leave` — "We'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:fears.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.fears.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.followup.leave   [21 chars]
    en  We'll leave it there.
    >>  ............................................
    pt  Vamos deixar assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave it there."
       spoken on: conversations.scene.fears.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scene.leaving`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.fears.the_doorway_one.respond / leave; conversations.scene.fears.the_night_one.respond / leave
```

```text
  dialogue.conversations.scene.fears.leaving/1   [39 chars]
    en  That is enough of that for one evening.
    >>  ............................................
    pt  Já basta disso por uma noite.
    >>  ............................................
  dialogue.conversations.scene.fears.leaving/2   [39 chars]
    en  Right. It looks smaller now it is said.
    >>  ............................................
    pt  Certo. Parece menor agora que foi dito.
    >>  ............................................
  dialogue.conversations.scene.fears.leaving/3   [22 chars]
    en  I will leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

---


## `conversations.scene.fears.the_doorway_one.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.fears.the_doorway_one` — e.g. "Deep water and public speaking, in that order, and I have arranged my entire life around avoiding both."


```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.fears.the_doorway_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.fears.the_doorway_one.respond   [22 chars]
    en  Anything frighten you?
    >>  ............................................
    pt  Alguma coisa te assusta?
    >>  ............................................
```


### Button `ask_why_that_one` — "Where did that one come from?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `fears.the_doorway_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.fears.the_doorway_one.ask_why_that_one` — accepted phrasings: "where did that one come from"; "where did that one come from"; "what is behind that one"
  - the message must contain one of: `came`, `behind`, `come`
  - scored words: `came`(1.8), `behind`(1.8), `come`(1.8), `where`(0.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one.respond.ask_why_that_one
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.the_doorway_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.the_doorway_one.respond.ask_why_that_one   [29 chars]
    en  Where did that one come from?
    >>  ............................................
    pt  De onde veio essa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `fears.ordinary`)_
- Does: session `turn`
- Then opens: `conversations.scene.fears.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one.explained
WHO    VILLAGER — what the player reads after pressing "Where did that one come from?"
       spoken on: conversations.scene.fears.the_doorway_one.respond, button `ask_why_that_one`
       leaves the player on: conversations.scene.fears.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.the_doorway_one.open.explained`: the villager explains. Subject `fears.ordinary`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:fears` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.fears.the_doorway_one.explained/1   [79 chars]
    en  A cousin, a pond and a summer afternoon that everybody else remembers as funny.
    >>  ............................................
    pt  Um primo, um açude e uma tarde de verão que todo mundo lembra como engraçada.
    >>  ............................................
  dialogue.conversations.scene.fears.the_doorway_one.explained/2   [92 chars]
    en  No story at all, which people find unsatisfying. Some of them just arrive with you and stay.
    >>  ............................................
    pt  Nenhuma história, o que as pessoas acham insatisfatório. Algumas simplesmente chegam com você e ficam.
    >>  ............................................
  dialogue.conversations.scene.fears.the_doorway_one.explained/3   [101 chars]
    en  I fell off one at eleven. Six feet. Nothing broke and I have never been the same about ladders since.
    >>  ............................................
    pt  Caí de uma aos onze anos. Dois metros. Nada quebrou e eu nunca mais fui a mesma com escadas.
    >>  ............................................
```


### Button `share_your_own` — "I've got one just as daft."

*stance family `self_disclosure` · tone `playful` · outcome `appreciated` · answers the beat(s) `fears.the_doorway_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.fears.the_doorway_one.share_your_own` — accepted phrasings: "ive got one just as daft"; "i have one just as daft"; "mine is just as silly"
  - the message must contain one of: `daft`, `silly`, `mine`
  - scored words: `daft`(1.8), `silly`(1.8), `mine`(1.8), `ive`(0.8), `got`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one.respond.share_your_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.the_doorway_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.the_doorway_one.respond.share_your_own   [26 chars]
    en  I've got one just as daft.
    >>  ............................................
    pt  Eu tenho um tão bobo quanto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, familiarity +2  _(recorded under topic `fears.ordinary`)_
- Does: session `turn`
- Then opens: `conversations.scene.fears.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one.delighted
WHO    VILLAGER — what the player reads after pressing "I've got one just as daft."
       spoken on: conversations.scene.fears.the_doorway_one.respond, button `share_your_own`
       leaves the player on: conversations.scene.fears.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.the_doorway_one.open.delighted`: the villager celebrates. Subject `fears.ordinary`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:fears` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.fears.the_doorway_one.delighted/1   [111 chars]
    en  Good. Everybody has one and pretending otherwise is how the whole village ends up standing on ladders it hates.
    >>  ............................................
    pt  Ótimo. Todo mundo tem um, e fingir o contrário é como a vila inteira acaba em cima de escadas que detesta.
    >>  ............................................
  dialogue.conversations.scene.fears.the_doorway_one.delighted/2   [77 chars]
    en  Then we shall both go the long way round and say nothing about it to anybody.
    >>  ............................................
    pt  Então nós dois damos a volta maior e não contamos nada a ninguém.
    >>  ............................................
  dialogue.conversations.scene.fears.the_doorway_one.delighted/3   [102 chars]
    en  That is the correct response and almost nobody gives it. Nearly everybody gives advice about the pond.
    >>  ............................................
    pt  É a resposta certa e quase ninguém dá. Quase todos dão conselho sobre o açude.
    >>  ............................................
```


### Button `leave` — "Thank you for trusting me."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.the_doorway_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.the_doorway_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.the_doorway_one.respond.leave   [26 chars]
    en  Thank you for trusting me.
    >>  ............................................
    pt  Obrigado por confiar em mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for trusting me."
       spoken on: conversations.scene.fears.the_doorway_one.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scene.leaving`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.fears.followup / leave; conversations.scene.fears.the_night_one.respond / leave
```

> Written out in full under **`conversations.scene.fears.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.fears.the_night_one.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.fears.the_night_one` — e.g. "It is not a thing that could happen. It is a thing that already has, somewhere else, to somebody like me."


```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.fears.the_night_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.fears.the_night_one.respond   [18 chars]
    en  What keeps you up.
    >>  ............................................
    pt  O que te tira o sono.
    >>  ............................................
```


### Button `stay_with_it` — "That's a real thing to be afraid of."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `fears.the_night_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.fears.the_night_one.stay_with_it` — accepted phrasings: "thats a real thing to be afraid of"; "that is a real thing to be afraid of"; "that fear makes sense to me"
  - the message must contain one of: `afraid`, `fear`, `real`
  - scored words: `afraid`(1.8), `fear`(1.8), `real`(1.8), `thats`(0.8), `thing`(0.8), `makes`(0.8), `sense`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one.respond.stay_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.the_night_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.the_night_one.respond.stay_with_it   [36 chars]
    en  That's a real thing to be afraid of.
    >>  ............................................
    pt  É uma coisa real de se temer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.fears.held`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +4  _(recorded under topic `fears.the_real_one`)_
- Does: session `turn`
- Then opens: `conversations.scene.fears.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one.steadied
WHO    VILLAGER — what the player reads after pressing "That's a real thing to be afraid of."
       spoken on: conversations.scene.fears.the_night_one.respond, button `stay_with_it`
       leaves the player on: conversations.scene.fears.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.the_night_one.open.steadied`: the villager accepts. Subject `fears.the_real_one`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:fears` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.fears.the_night_one.steadied/1   [114 chars]
    en  Thank you for not solving it. Everybody solves it, and being solved at makes a thing lonelier rather than smaller.
    >>  ............................................
    pt  Obrigada por não resolver. Todo mundo resolve, e ser resolvida deixa uma coisa mais solitária em vez de menor.
    >>  ............................................
  dialogue.conversations.scene.fears.the_night_one.steadied/2   [87 chars]
    en  It is, and saying it to somebody who did not flinch has taken about a third of it away.
    >>  ............................................
    pt  É, e dizer isso a alguém que não se encolheu tirou uns dois terços... um terço do peso.
    >>  ............................................
  dialogue.conversations.scene.fears.the_night_one.steadied/3   [117 chars]
    en  I have carried that for two years and said it once. Twice, now. It gets lighter by roughly the same amount each time.
    >>  ............................................
    pt  Carreguei isso por dois anos e disse uma vez. Duas, agora. Fica mais leve mais ou menos na mesma medida a cada vez.
    >>  ............................................
```


### Button `ask_how_long` — "How long have you carried that?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `fears.the_night_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.fears.the_night_one.ask_how_long` — accepted phrasings: "how long have you carried that"; "how long have you carried that"; "how long has that been with you"
  - the message must contain one of: `carried`, `long`
  - scored words: `carried`(1.8), `long`(1.8), `been`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one.respond.ask_how_long
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.the_night_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.the_night_one.respond.ask_how_long   [31 chars]
    en  How long have you carried that?
    >>  ............................................
    pt  Há quanto tempo você carrega isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `fears.the_real_one`)_
- Does: session `turn`
- Then opens: `conversations.scene.fears.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one.answered
WHO    VILLAGER — what the player reads after pressing "How long have you carried that?"
       spoken on: conversations.scene.fears.the_night_one.respond, button `ask_how_long`
       leaves the player on: conversations.scene.fears.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.the_night_one.open.answered`: the villager explains. Subject `fears.the_real_one`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:fears` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.fears.the_night_one.answered/1   [93 chars]
    en  Since a particular winter. I could give you the month. That is how you know it is not a mood.
    >>  ............................................
    pt  Desde um inverno específico. Eu saberia dizer o mês. É assim que se sabe que não é humor.
    >>  ............................................
  dialogue.conversations.scene.fears.the_night_one.answered/2   [92 chars]
    en  Long enough that it has furniture. It has a chair it sits in and a time of night it prefers.
    >>  ............................................
    pt  Tempo suficiente para ter mobília. Tem uma cadeira em que senta e uma hora da noite que prefere.
    >>  ............................................
  dialogue.conversations.scene.fears.the_night_one.answered/3   [114 chars]
    en  It arrived all at once when I was about twenty-six, and it has never once been louder than it was that first week.
    >>  ............................................
    pt  Chegou de uma vez quando eu tinha uns vinte e seis, e nunca mais foi tão alto quanto naquela primeira semana.
    >>  ............................................
```


### Button `leave` — "Thank you for trusting me."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.the_night_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.fears.the_night_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.fears.the_night_one.respond.leave   [26 chars]
    en  Thank you for trusting me.
    >>  ............................................
    pt  Obrigado por confiar em mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for trusting me."
       spoken on: conversations.scene.fears.the_night_one.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scene.leaving`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.fears.followup / leave; conversations.scene.fears.the_doorway_one.respond / leave
```

> Written out in full under **`conversations.scene.fears.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.fears.again.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.again` — e.g. "I already showed you that scar, %1$s. Don't poke it twice in one week."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.again.respond   [20 chars]
    en  We did this already.
    >>  ............................................
    pt  A gente já fez isso.
    >>  ............................................
```


### Button `apologize` — "Sorry — I shouldn't have asked twice."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.again.to.fears.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.again.apologize` — accepted phrasings: "sorry, i asked twice"; "i should not have asked again"; "sorry, i forgot"; "my mistake"
  - the message must contain one of: `twice`, `sorry`, `forgot`, `shouldnt`
  - scored words: `twice`(1.5), `sorry`(1.2), `forgot`(1.2), `shouldnt`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.again.respond.apologize   [37 chars]
    en  Sorry — I shouldn't have asked twice.
    >>  ............................................
    pt  Desculpa — não devia ter perguntado duas vezes.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `fears.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I shouldn't have asked twice."
       spoken on: conversations.topic.fears.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.again.apologize.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.again.apologize/1   [72 chars]
    en  It's alright. It's not the sort of thing that gets easier by repetition.
    >>  ............................................
    pt  Tudo bem. Não é o tipo de coisa que melhora com repetição.
    >>  ............................................
  dialogue.conversations.fears.again.apologize/2   [60 chars]
    en  No harm. Ask me in a few days and I'll have found new words.
    >>  ............................................
    pt  Sem problema. Pergunte em uns dias e eu terei achado palavras novas.
    >>  ............................................
  dialogue.conversations.fears.again.apologize/3   [60 chars]
    en  Appreciated, %1$s. Twice in a day is a lot to ask of anyone.
    >>  ............................................
    pt  Agradeço, %1$s. Duas vezes no mesmo dia é muito para qualquer um.
    >>  ............................................
```


### Button `press` — "Tell me again anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `fears.again.to.fears.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.again.press` — accepted phrasings: "tell me again anyway"; "humour me"; "go on, again"
  - the message must contain one of: `anyway`, `again`, `humour`
  - scored words: `anyway`(1.5), `again`(1.2), `humour`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.again.respond.press   [21 chars]
    en  Tell me again anyway.
    >>  ............................................
    pt  Me conta de novo mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `fears.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +4  _(recorded under topic `fears.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.fears.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.again.press.terminal`: the villager resists. Subject `fears.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.again.press/1   [64 chars]
    en  I already showed you that scar. Don't poke it twice in one week.
    >>  ............................................
    pt  Já te mostrei essa cicatriz. Não cutuca duas vezes na mesma semana.
    >>  ............................................
  dialogue.conversations.fears.again.press/2   [34 chars]
    en  Twice? Buy me a drink first, %1$s.
    >>  ............................................
    pt  Duas vezes? Me paga uma bebida primeiro, %1$s.
    >>  ............................................
  dialogue.conversations.fears.again.press/3   [44 chars]
    en  Let it rest. It bites when it's poked fresh.
    >>  ............................................
    pt  Deixa descansar. Ela morde quando é cutucada fresca.
    >>  ............................................
```


### Button `leave` — "Fair. Another day."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.again.to.fears.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.again.respond.leave   [18 chars]
    en  Fair. Another day.
    >>  ............................................
    pt  Justo. Outro dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.fears.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.again.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.again.leave/1   [17 chars]
    en  Aye. Another day.
    >>  ............................................
    pt  Tá. Outro dia.
    >>  ............................................
  dialogue.conversations.fears.again.leave/2   [38 chars]
    en  Good. Catch me when the week's turned.
    >>  ............................................
    pt  Bom. Me procura quando a semana virar.
    >>  ............................................
  dialogue.conversations.fears.again.leave/3   [42 chars]
    en  Good. Some things want a gap between them.
    >>  ............................................
    pt  Bom. Algumas coisas precisam de um intervalo.
    >>  ............................................
```

---


## `conversations.topic.fears.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.personal` — e.g. "That's... a bit close to the bone for someone I barely know."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.guarded.respond   [29 chars]
    en  Some doors stay shut a while.
    >>  ............................................
    pt  Algumas portas ficam fechadas por um tempo.
    >>  ............................................
```


### Button `respect` — "Fair enough. It's yours to keep."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.personal.to.fears.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.guarded.respect` — accepted phrasings: "that is yours to keep"; "fair enough"; "keep it private"; "i understand"
  - the message must contain one of: `yours`, `keep`, `fair`, `private`
  - scored words: `yours`(1.5), `keep`(1.2), `fair`(1.2), `private`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.guarded.respond.respect   [32 chars]
    en  Fair enough. It's yours to keep.
    >>  ............................................
    pt  Justo. É seu para guardar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.guarded.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `fears.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.guarded.respect
WHO    VILLAGER — what the player reads after pressing "Fair enough. It's yours to keep."
       spoken on: conversations.topic.fears.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.guarded.respect.terminal`: the villager deflects. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.guarded.respect/1   [62 chars]
    en  ...Aye. It is mine. Thank you for hearing that the first time.
    >>  ............................................
    pt  ...É. É meu mesmo. Obrigado por entender de primeira.
    >>  ............................................
  dialogue.conversations.fears.guarded.respect/2   [60 chars]
    en  Most people push. You didn't. That buys you something, %1$s.
    >>  ............................................
    pt  A maioria insiste. Você não. Isso te rende algo, %1$s.
    >>  ............................................
  dialogue.conversations.fears.guarded.respect/3   [45 chars]
    en  Good. Ask me again in a season and we'll see.
    >>  ............................................
    pt  Bom. Me pergunte de novo daqui a uma estação e a gente vê.
    >>  ............................................
```


### Button `ask_safer` — "Then tell me something easier."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.personal.to.fears.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.guarded.ask_safer` — accepted phrasings: "tell me something easier"; "something lighter then"; "what else then"; "ask something else"
  - the message must contain one of: `easier`, `lighter`, `else`, `instead`
  - scored words: `easier`(1.5), `lighter`(1.5), `else`(1.0), `something`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.guarded.respond.ask_safer   [30 chars]
    en  Then tell me something easier.
    >>  ............................................
    pt  Então me conta algo mais leve.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `fears.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Then tell me something easier."
       spoken on: conversations.topic.fears.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.guarded.ask_safer.terminal`: the villager deflects. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.guarded.ask_safer/1   [67 chars]
    en  Now that I can do. Ask me about the crops, or the roof, or the cat.
    >>  ............................................
    pt  Isso eu posso. Me pergunte da plantação, ou do telhado, ou do gato.
    >>  ............................................
  dialogue.conversations.fears.guarded.ask_safer/2   [53 chars]
    en  Something easier. Aye. Let's start there and work up.
    >>  ............................................
    pt  Algo mais leve. É. Vamos começar por aí e ir subindo.
    >>  ............................................
  dialogue.conversations.fears.guarded.ask_safer/3   [56 chars]
    en  Sensible. Small talk first, %1$s. That's how it's built.
    >>  ............................................
    pt  Sensato. Conversa fiada primeiro, %1$s. É assim que se constrói.
    >>  ............................................
```


### Button `press` — "Come on. Out with it."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.personal.to.fears.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.guarded.press` — accepted phrasings: "come on"; "out with it"; "tell me anyway"; "just tell me"
  - the message must contain one of: `come`, `anyway`, `tell`, `out`
  - scored words: `come`(1.2), `out`(1.0), `tell`(1.0), `anyway`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.guarded.respond.press   [21 chars]
    en  Come on. Out with it.
    >>  ............................................
    pt  Vai. Desembucha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `fears.guarded.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `fears.guarded.press`)_
- Then opens: `conversations.topic.fears.pressed`
- …where the player's next choices will be: "You're right. Forget I asked." | "I want the rest. Now." | "Alright. I'll drop it."

```text
POOL   dialogue key: dialogue.conversations.fears.guarded.press
WHO    VILLAGER — what the player reads after pressing "Come on. Out with it."
       spoken on: conversations.topic.fears.guarded.respond, button `press`
       leaves the player on: conversations.topic.fears.pressed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.guarded.press.to.fears.pressed`: the villager resists. Subject `fears.pressed`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.guarded.press/1   [48 chars]
    en  I said no. Do you want to hear it a second time?
    >>  ............................................
    pt  Eu disse não. Quer ouvir uma segunda vez?
    >>  ............................................
  dialogue.conversations.fears.guarded.press/2   [51 chars]
    en  You'll be wanting my savings next. It's a no, %1$s.
    >>  ............................................
    pt  Daqui a pouco vai querer minhas economias. É não, %1$s.
    >>  ............................................
  dialogue.conversations.fears.guarded.press/3   [49 chars]
    en  Pushing at a locked door only tells me about you.
    >>  ............................................
    pt  Empurrar uma porta trancada só me diz coisas sobre você.
    >>  ............................................
```


### Button `leave` — "Another time, then."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.personal.to.fears.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.guarded.respond.leave   [19 chars]
    en  Another time, then.
    >>  ............................................
    pt  Outra hora, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.guarded.leave
WHO    VILLAGER — what the player reads after pressing "Another time, then."
       spoken on: conversations.topic.fears.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.guarded.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.guarded.leave/1   [25 chars]
    en  Aye. Another time, maybe.
    >>  ............................................
    pt  Tá. Outra hora, quem sabe.
    >>  ............................................
  dialogue.conversations.fears.guarded.leave/2   [29 chars]
    en  Off you go. No hard feelings.
    >>  ............................................
    pt  Pode ir. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.fears.guarded.leave/3   [40 chars]
    en  So you are. We'll get there or we won't.
    >>  ............................................
    pt  Pois é. A gente chega lá ou não.
    >>  ............................................
```

---


## `conversations.topic.fears.lapsed`

**Reached from 1 route(s):** `conversations.arc.fears.followthrough.respond` / `recall_promise`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.followthrough.recall.lapsed` — e.g. "You did say that. ...It's been a while since, mind. I'd started filling in the blank myself."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.lapsed
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.lapsed
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.lapsed   [35 chars]
    en  You did say it. That's not nothing.
    >>  ............................................
    pt  Você disse mesmo. Isso não é nada.
    >>  ............................................
```


### Button `apologize` — "You're right. I wasn't there."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.followthrough.recall.lapsed.to.fears.lapsed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.lapsed.apologize` — accepted phrasings: "you are right, i was not there"; "i was not there and i am sorry"; "you are right about that"
  - the message must contain one of: `right`, `sorry`, `there`
  - scored words: `right`(1.3), `there`(1.2), `sorry`(1.3)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.lapsed.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.lapsed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.lapsed.apologize   [29 chars]
    en  You're right. I wasn't there.
    >>  ............................................
    pt  Você tem razão. Eu não estive aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4, respect +2  _(recorded under topic `fears.lapsed.apologize`)_
- Does: remembers `mcaconversations.pledge.fears` (this player only) for 72000 ticks
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.lapsed.apologize
WHO    VILLAGER — what the player reads after pressing "You're right. I wasn't there."
       spoken on: conversations.topic.fears.lapsed, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.lapsed.apologize.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.lapsed.apologize/1   [89 chars]
    en  ...Well. That's more than most would say. It's not undone, but it's said, and that helps.
    >>  ............................................
    pt  ...Bom. É mais do que a maioria diria. Não desfaz, mas foi dito, e isso ajuda.
    >>  ............................................
  dialogue.conversations.fears.lapsed.apologize/2   [90 chars]
    en  You could have argued. You didn't. ...Alright, %1$s. We're not square, but we're speaking.
    >>  ............................................
    pt  Você podia ter discutido. Não discutiu. ...Certo, %1$s. Não estamos quites, mas estamos conversando.
    >>  ............................................
  dialogue.conversations.fears.lapsed.apologize/3   [53 chars]
    en  Mm. Owning it costs something. I noticed you paid it.
    >>  ............................................
    pt  Hm. Assumir custa alguma coisa. Reparei que você pagou.
    >>  ............................................
```


### Button `make_good` — "Then let me be there now."

*stance family `empathy` · tone `gentle` · answers the beat(s) `fears.followthrough.recall.lapsed.to.fears.lapsed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.lapsed.make_good` — accepted phrasings: "then let me be there now"; "let me make it good now"; "i am here now"
  - the message must contain one of: `now`, `here`, `make`
  - scored words: `now`(1.5), `here`(1.2), `make`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.lapsed.make_good
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.lapsed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.lapsed.make_good   [25 chars]
    en  Then let me be there now.
    >>  ............................................
    pt  Então deixe eu estar aqui agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.lapsed.make_good`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, tension -2  _(recorded under topic `fears.lapsed.make_good`)_
- Does: remembers `mcaconversations.pledge.fears` (this player only) for 72000 ticks
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.lapsed.make_good
WHO    VILLAGER — what the player reads after pressing "Then let me be there now."
       spoken on: conversations.topic.fears.lapsed, button `make_good`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.lapsed.make_good.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.lapsed.make_good/1   [84 chars]
    en  Now, you mean. ...Right. Now will do. Now is the only bit anyone can actually offer.
    >>  ............................................
    pt  Agora, você diz. ...Certo. Agora serve. Agora é a única parte que alguém consegue oferecer de verdade.
    >>  ............................................
  dialogue.conversations.fears.lapsed.make_good/2   [56 chars]
    en  Then be here, and we'll say no more about the gap, %1$s.
    >>  ............................................
    pt  Então esteja aqui, e não falamos mais do intervalo, %1$s.
    >>  ............................................
  dialogue.conversations.fears.lapsed.make_good/3   [59 chars]
    en  Alright. Second time of asking. I'd not give everyone that.
    >>  ............................................
    pt  Tudo bem. Segunda chance. Eu não daria isso a qualquer um.
    >>  ............................................
```


### Button `leave` — "I'll not make excuses."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.followthrough.recall.lapsed.to.fears.lapsed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.lapsed.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.lapsed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.lapsed.leave   [22 chars]
    en  I'll not make excuses.
    >>  ............................................
    pt  Não vou dar desculpas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.lapsed.leave
WHO    VILLAGER — what the player reads after pressing "I'll not make excuses."
       spoken on: conversations.topic.fears.lapsed, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.lapsed.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.lapsed.leave/1   [81 chars]
    en  No excuses. Aye — that's the right call, and I'd have thought less of a good one.
    >>  ............................................
    pt  Sem desculpas. É — essa é a escolha certa, e eu teria gostado menos de uma boa desculpa.
    >>  ............................................
  dialogue.conversations.fears.lapsed.leave/2   [86 chars]
    en  Off you go, then. It's not the end of anything, %1$s. It's just a thing that happened.
    >>  ............................................
    pt  Pode ir, então. Não é o fim de nada, %1$s. É só uma coisa que aconteceu.
    >>  ............................................
  dialogue.conversations.fears.lapsed.leave/3   [62 chars]
    en  So be it. We'll leave it where it is and see what you do next.
    >>  ............................................
    pt  Que seja. Deixamos como está e vemos o que você faz depois.
    >>  ............................................
```

---


## `conversations.topic.fears.open.close`

**Reached from 13 route(s):** `conversations.arc.fears.followthrough.followup` / `proud`; `conversations.arc.fears.followthrough.followup` / `ask_next`; `conversations.arc.fears.followthrough.followup` / `steady`; `conversations.arc.fears.plan.followup` / `commit`; `conversations.arc.fears.plan.followup` / `refine`; `conversations.arc.fears.plan.followup` / `honest`; `conversations.topic.fears.open.followup` / `pledge`; `conversations.topic.fears.open.followup` / `pledge`; `conversations.topic.fears.open.followup` / `step_back`; `conversations.topic.fears.open.followup` / `challenge`; `conversations.topic.fears.open.followup` / `challenge`; `conversations.topic.fears.open.followup` / `challenge` …and 1 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.followthrough.followup.ask_next` — e.g. "The next hard bit is the one where nobody's watching. Those are always the worst ones."
- `conversations.fears.followthrough.followup.proud` — e.g. "A long way. ...I hadn't measured it from the start. I'd only been measuring from here to the end."
- `conversations.fears.followthrough.followup.steady` — e.g. "Not finished. ...I'd been treating it like a job with an end to it. It isn't, is it."
- `conversations.fears.open.challenge.crit` — e.g. "...Nobody ever offered the 'with you' part before. That's the part that matters. Alright. Next storm, I'll stand in the doorway."
- `conversations.fears.open.challenge.partial` — e.g. "Easy for you — you've somewhere else to be when it goes wrong."
- `conversations.fears.open.challenge.plain` — e.g. "Maybe I could. Not today, but maybe."
- `conversations.fears.open.challenge.success` — e.g. "Maybe. With a witness, maybe. Don't let me back out of it later."
- `conversations.fears.open.pledge` — e.g. "...Nobody's said that before. I'm holding you to it, %1$s."
- `conversations.fears.open.pledge.trusted` — e.g. "...I know you will. That's the strange part — I said it and then realised I already believed it."
- `conversations.fears.open.step_back` — e.g. "...That's honest. I'd rather that than a promise you'd break."
- `conversations.fears.plan.followup.commit` — e.g. "You'll be there. Then it's a plan and not a wish, which is the whole difference."
- `conversations.fears.plan.followup.honest` — e.g. "You can't promise. Good. I've had promises. I'd rather have somebody who counts the cost first."
- `conversations.fears.plan.followup.refine` — e.g. "Easier. ...If it were smaller. If it were one step instead of the whole staircase."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.open.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.open.close   [18 chars]
    en  Anyway. It's said.
    >>  ............................................
    pt  Enfim. Está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for trusting me with that."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.followthrough.followup.ask_next.to.fears.open`, `fears.followthrough.followup.proud.to.fears.open`, `fears.followthrough.followup.steady.to.fears.open`, `fears.open.challenge.crit.to.fears.open`, `fears.open.challenge.partial.to.fears.open`, `fears.open.challenge.plain.to.fears.open`, `fears.open.challenge.success.to.fears.open`, `fears.open.pledge.to.fears.open`, `fears.open.pledge.trusted.to.fears.open`, `fears.open.step_back.to.fears.open`, `fears.plan.followup.commit.to.fears.open`, `fears.plan.followup.honest.to.fears.open`, `fears.plan.followup.refine.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.thank` — accepted phrasings: "thank you for telling me"; "thank you for trusting me"; "i am grateful"
  - the message must contain one of: `thank`, `trusting`, `grateful`, `trust`
  - scored words: `thank`(1.5), `trusting`(1.5), `grateful`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.close.thank   [36 chars]
    en  Thank you for trusting me with that.
    >>  ............................................
    pt  Obrigado por confiar isso a mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when today's affection ledger, axis positive >= 8
- Does: disposition — warmth +2, trust +1  _(recorded under topic `fears.open.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.thank.spent
WHO    VILLAGER — what the player reads after pressing "Thank you for trusting me with that."
       spoken on: conversations.topic.fears.open.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.thank.spent.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.thank.spent/1   [77 chars]
    en  We've talked it thin for one day. Come back and we'll pick it up where it is.
    >>  ............................................
    pt  Já falamos disso até gastar por hoje. Volte e a gente retoma de onde parou.
    >>  ............................................
  dialogue.conversations.fears.open.thank.spent/2   [101 chars]
    en  Enough for today. There's only so much of a thing you can say before it stops meaning anything, %1$s.
    >>  ............................................
    pt  Chega por hoje. Tem um limite de quanto se fala de uma coisa antes de parar de significar, %1$s.
    >>  ............................................
  dialogue.conversations.fears.open.thank.spent/3   [59 chars]
    en  Quite. Let it rest now. It'll still be here, and so will I.
    >>  ............................................
    pt  Exato. Deixa descansar agora. Vai continuar aqui, e eu também.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when today's affection ledger, axis positive >= 8  _(chance -2000)_
- Does: **hearts +1** — decision id `fears.open.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `fears.open.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for trusting me with that."
       spoken on: conversations.topic.fears.open.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.thank.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.thank/1   [73 chars]
    en  Don't thank me. I didn't do anything except stop pretending for a minute.
    >>  ............................................
    pt  Não me agradeça. Eu não fiz nada além de parar de fingir por um minuto.
    >>  ............................................
  dialogue.conversations.fears.open.thank/2   [67 chars]
    en  It's not a gift, %1$s. It's just true. Still — aye. You're welcome.
    >>  ............................................
    pt  Não é um presente, %1$s. É só verdade. Mesmo assim — é. De nada.
    >>  ............................................
  dialogue.conversations.fears.open.thank/3   [56 chars]
    en  Trust's a strange thing to be thanked for. I'll take it.
    >>  ............................................
    pt  Confiança é uma coisa estranha de se agradecer. Mas aceito.
    >>  ............................................
```


### Button `means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.followthrough.followup.ask_next.to.fears.open`, `fears.followthrough.followup.proud.to.fears.open`, `fears.followthrough.followup.steady.to.fears.open`, `fears.open.challenge.crit.to.fears.open`, `fears.open.challenge.partial.to.fears.open`, `fears.open.challenge.plain.to.fears.open`, `fears.open.challenge.success.to.fears.open`, `fears.open.pledge.to.fears.open`, `fears.open.pledge.trusted.to.fears.open`, `fears.open.step_back.to.fears.open`, `fears.plan.followup.commit.to.fears.open`, `fears.plan.followup.honest.to.fears.open`, `fears.plan.followup.refine.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.means` — accepted phrasings: "that took something to say"; "that was brave"; "that took courage"; "that cannot have been easy"
  - the message must contain one of: `took`, `brave`, `courage`, `easy`
  - scored words: `took`(1.5), `brave`(1.5), `courage`(1.5), `say`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.close.means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.open.means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `fears.open.means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.fears.open.close, button `means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.means.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.means/1   [53 chars]
    en  ...It did. More than I'd like to admit standing here.
    >>  ............................................
    pt  ...Exigiu. Mais do que eu gostaria de admitir aqui de pé.
    >>  ............................................
  dialogue.conversations.fears.open.means/2   [75 chars]
    en  Getting it past my own teeth was the hard part. You just had to not flinch.
    >>  ............................................
    pt  O difícil foi passar dos meus próprios dentes. Você só teve que não recuar.
    >>  ............................................
  dialogue.conversations.fears.open.means/3   [34 chars]
    en  Noticed that, did you. Most don't.
    >>  ............................................
    pt  Você notou, é? A maioria não nota.
    >>  ............................................
```


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.followthrough.followup.ask_next.to.fears.open`, `fears.followthrough.followup.proud.to.fears.open`, `fears.followthrough.followup.steady.to.fears.open`, `fears.open.challenge.crit.to.fears.open`, `fears.open.challenge.partial.to.fears.open`, `fears.open.challenge.plain.to.fears.open`, `fears.open.challenge.success.to.fears.open`, `fears.open.pledge.to.fears.open`, `fears.open.pledge.trusted.to.fears.open`, `fears.open.step_back.to.fears.open`, `fears.plan.followup.commit.to.fears.open`, `fears.plan.followup.honest.to.fears.open`, `fears.plan.followup.refine.to.fears.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.close.leave   [16 chars]
    en  I'll let you be.
    >>  ............................................
    pt  Vou te deixar em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.close_leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.fears.open.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.close_leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.close_leave/1   [33 chars]
    en  Aye. Go on. And %1$s — thank you.
    >>  ............................................
    pt  Tá. Pode ir. E %1$s — obrigado.
    >>  ............................................
  dialogue.conversations.fears.open.close_leave/2   [39 chars]
    en  Noted. Enough of that. Mind how you go.
    >>  ............................................
    pt  Anotado. Já chega disso. Se cuida.
    >>  ............................................
  dialogue.conversations.fears.open.close_leave/3   [55 chars]
    en  Off with you. We'll pretend this was about the weather.
    >>  ............................................
    pt  Vai lá. Vamos fingir que isso foi sobre o tempo.
    >>  ............................................
```

---


## `conversations.topic.fears.open.close.rebuffed`

**Reached from 2 route(s):** `conversations.topic.fears.open.followup` / `challenge`; `conversations.topic.fears.open.respond` / `comfort`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.open.challenge.rebuff` — e.g. "Don't make my fear a test of your character. It isn't yours to be brave about."
- `conversations.fears.open.comfort.rebuff` — e.g. "Don't. I didn't hand you that so you could pat it on the head."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.rebuffed
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.open.close.rebuffed
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.open.close.rebuffed   [24 chars]
    en  That landed wrong, then.
    >>  ............................................
    pt  Então isso soou mal.
    >>  ............................................
```


### Button `apologize` — "Fair. That came out wrong."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.open.challenge.rebuff.to.fears.open.close.rebuffed`, `fears.open.comfort.rebuff.to.fears.open.close.rebuffed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.rebuffed.apologize` — accepted phrasings: "that came out wrong"; "fair, that was the wrong thing to say"; "sorry, that came out badly"
  - the message must contain one of: `wrong`, `sorry`
  - scored words: `wrong`(1.5), `sorry`(1.3), `came`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.rebuffed.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.close.rebuffed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.close.rebuffed.apologize   [26 chars]
    en  Fair. That came out wrong.
    >>  ............................................
    pt  Justo. Saiu errado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -5, respect +2  _(recorded under topic `fears.open.rebuffed.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.rebuffed.apologize
WHO    VILLAGER — what the player reads after pressing "Fair. That came out wrong."
       spoken on: conversations.topic.fears.open.close.rebuffed, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.rebuffed.apologize.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.rebuffed.apologize/1   [80 chars]
    en  ...Aye. Well. I'm prickly about it, and you weren't to know. We'll call it even.
    >>  ............................................
    pt  ...É. Bom. Eu sou espinhoso com isso, e você não tinha como saber. Ficamos quites.
    >>  ............................................
  dialogue.conversations.fears.open.rebuffed.apologize/2   [85 chars]
    en  It did. But I've the shorter fuse where that's concerned. Let's leave it there, %1$s.
    >>  ............................................
    pt  Soou. Mas o pavio é mais curto quando é esse assunto. Vamos deixar por aqui, %1$s.
    >>  ............................................
  dialogue.conversations.fears.open.rebuffed.apologize/3   [57 chars]
    en  Mm. Thank you for not arguing the point. Most would have.
    >>  ............................................
    pt  Hm. Obrigado por não discutir. A maioria discutiria.
    >>  ............................................
```


### Button `accept` — "Understood. I'll shut up."

*stance family `restraint` · tone `plain` · answers the beat(s) `fears.open.challenge.rebuff.to.fears.open.close.rebuffed`, `fears.open.comfort.rebuff.to.fears.open.close.rebuffed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.rebuffed.accept` — accepted phrasings: "understood, i will shut up"; "i will be quiet then"; "understood"
  - the message must contain one of: `understood`, `quiet`, `shut`
  - scored words: `understood`(1.5), `quiet`(1.2), `shut`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.rebuffed.accept
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.close.rebuffed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.close.rebuffed.accept   [25 chars]
    en  Understood. I'll shut up.
    >>  ............................................
    pt  Entendi. Vou calar a boca.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, tension -2  _(recorded under topic `fears.open.rebuffed.accept`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.rebuffed.accept
WHO    VILLAGER — what the player reads after pressing "Understood. I'll shut up."
       spoken on: conversations.topic.fears.open.close.rebuffed, button `accept`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.rebuffed.accept.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.rebuffed.accept/1   [84 chars]
    en  ...That's the right answer, and I didn't expect it. Sit there a minute, if you like.
    >>  ............................................
    pt  ...Essa é a resposta certa, e eu não esperava. Fica aí um minuto, se quiser.
    >>  ............................................
  dialogue.conversations.fears.open.rebuffed.accept/2   [81 chars]
    en  Good. Not everything wants handling. Some of it just wants somebody stood nearby.
    >>  ............................................
    pt  Bom. Nem tudo quer ser resolvido. Às vezes só quer alguém parado por perto.
    >>  ............................................
  dialogue.conversations.fears.open.rebuffed.accept/3   [84 chars]
    en  Very well. You listen well when you stop trying to help, %1$s. That's not an insult.
    >>  ............................................
    pt  Muito bem. Você escuta bem quando para de tentar ajudar, %1$s. Não é um insulto.
    >>  ............................................
```


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.open.challenge.rebuff.to.fears.open.close.rebuffed`, `fears.open.comfort.rebuff.to.fears.open.close.rebuffed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.close.rebuffed.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.close.rebuffed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.close.rebuffed.leave   [16 chars]
    en  I'll let you be.
    >>  ............................................
    pt  Vou te deixar em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.rebuffed.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.fears.open.close.rebuffed, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.rebuffed.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.rebuffed.leave/1   [56 chars]
    en  True enough. Go on. I'll be less of a hedgehog tomorrow.
    >>  ............................................
    pt  Bem verdade. Pode ir. Amanhã eu estarei menos ouriço.
    >>  ............................................
  dialogue.conversations.fears.open.rebuffed.leave/2   [61 chars]
    en  Off you go. It's not you I'm cross with, for what it's worth.
    >>  ............................................
    pt  Pode ir. Não é com você que eu estou bravo, se serve de algo.
    >>  ............................................
  dialogue.conversations.fears.open.rebuffed.leave/3   [29 chars]
    en  Just so, %1$s. Mind the step.
    >>  ............................................
    pt  Exato, %1$s. Cuidado com o degrau.
    >>  ............................................
```

---


## `conversations.topic.fears.open.disclosed`

**Reached from 2 route(s):** `conversations.topic.fears.open.respond` / `comfort`; `conversations.topic.fears.open.respond` / `press`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.open.comfort.crit` — e.g. "...You mean that. You're not just being polite. Then here's the part I never say: it isn't the thing itself. It's that I'd be alone in it."
- `conversations.fears.open.press.crit` — e.g. "...You don't flinch, do you. Alright. The rest of it, the true shape — and it stays between us."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.disclosed
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.open.disclosed
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.open.disclosed   [63 chars]
    en  There. That's the whole of it, and I've never said it out loud.
    >>  ............................................
    pt  Pronto. É isso por inteiro, e eu nunca disse em voz alta.
    >>  ............................................
```


### Button `hold_it` — "I'll help you carry that."

*stance family `practical_help` · tone `plain` · answers the beat(s) `fears.open.comfort.crit.to.fears.open.disclosed`, `fears.open.press.crit.to.fears.open.disclosed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.disclosed.hold_it` — accepted phrasings: "i will help you carry that"; "let me carry some of that"; "we can share the weight of it"
  - the message must contain one of: `carry`, `share`
  - scored words: `carry`(1.5), `help`(1.0), `share`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.disclosed.hold_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.disclosed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.disclosed.hold_it   [25 chars]
    en  I'll help you carry that.
    >>  ............................................
    pt  Eu ajudo você a carregar isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +5, warmth +3  _(recorded under topic `fears.open.disclosed.hold_it`)_
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.disclosed.hold_it
WHO    VILLAGER — what the player reads after pressing "I'll help you carry that."
       spoken on: conversations.topic.fears.open.disclosed, button `hold_it`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.disclosed.hold_it.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.disclosed.hold_it/1   [113 chars]
    en  ...You say that like it's a sack of grain. Like it's a thing that divides up. Alright. Take a corner of it, then.
    >>  ............................................
    pt  ...Você fala como se fosse um saco de grão. Como se fosse coisa que dá para dividir. Tudo bem. Então pegue uma ponta.
    >>  ............................................
  dialogue.conversations.fears.open.disclosed.hold_it/2   [125 chars]
    en  It doesn't work like that. ...Except it does, apparently, because it's lighter and you've done nothing but stand there, %1$s.
    >>  ............................................
    pt  Não funciona assim. ...Só que funciona, pelo visto, porque está mais leve e você não fez nada além de ficar aí, %1$s.
    >>  ............................................
  dialogue.conversations.fears.open.disclosed.hold_it/3   [95 chars]
    en  Then it isn't mine alone any more. I don't know what to do with that. Something good, I expect.
    >>  ............................................
    pt  Então já não é só meu. Não sei o que fazer com isso. Alguma coisa boa, eu imagino.
    >>  ............................................
```


### Button `ask_when` — "How long have you had that?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `fears.open.comfort.crit.to.fears.open.disclosed`, `fears.open.press.crit.to.fears.open.disclosed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.disclosed.ask_when` — accepted phrasings: "how long have you had that"; "when did that start"; "how long has it been like that"
  - the message must contain one of: `long`, `when`
  - scored words: `long`(1.5), `when`(1.2), `had`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.disclosed.ask_when
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.disclosed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.disclosed.ask_when   [27 chars]
    en  How long have you had that?
    >>  ............................................
    pt  Há quanto tempo você tem isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +4, trust +2  _(recorded under topic `fears.open.disclosed.ask_when`)_
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.disclosed.ask_when
WHO    VILLAGER — what the player reads after pressing "How long have you had that?"
       spoken on: conversations.topic.fears.open.disclosed, button `ask_when`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.disclosed.ask_when.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.disclosed.ask_when/1   [112 chars]
    en  Longer than I've lived in this house. It came with me, like a piece of furniture I keep meaning to leave behind.
    >>  ............................................
    pt  Mais tempo do que eu moro nesta casa. Veio comigo, feito um móvel que eu sempre penso em deixar para trás.
    >>  ............................................
  dialogue.conversations.fears.open.disclosed.ask_when/2   [91 chars]
    en  Since I was small enough to fit under a table. I remember the table better than the reason.
    >>  ............................................
    pt  Desde que eu cabia embaixo de uma mesa. Lembro melhor da mesa do que do motivo.
    >>  ............................................
  dialogue.conversations.fears.open.disclosed.ask_when/3   [94 chars]
    en  Long enough that I'd stopped thinking of it as a thing that started. It's just weather, to me.
    >>  ............................................
    pt  Tempo bastante para eu ter parado de pensar nisso como algo que começou. Para mim é clima.
    >>  ............................................
```


### Button `leave` — "That's enough for one day."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.open.comfort.crit.to.fears.open.disclosed`, `fears.open.press.crit.to.fears.open.disclosed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.disclosed.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.disclosed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.disclosed.leave   [26 chars]
    en  That's enough for one day.
    >>  ............................................
    pt  Já chega por hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.disclosed.leave
WHO    VILLAGER — what the player reads after pressing "That's enough for one day."
       spoken on: conversations.topic.fears.open.disclosed, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.disclosed.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.open.disclosed.leave/1   [72 chars]
    en  So it is. That's plenty. ...Thank you for hearing the whole of it, %1$s.
    >>  ............................................
    pt  É assim mesmo. Já basta. ...Obrigado por ouvir até o fim, %1$s.
    >>  ............................................
  dialogue.conversations.fears.open.disclosed.leave/2   [66 chars]
    en  Enough. Go on. I've said more today than in the last year of days.
    >>  ............................................
    pt  Chega. Pode ir. Falei mais hoje do que no último ano inteiro.
    >>  ............................................
  dialogue.conversations.fears.open.disclosed.leave/3   [65 chars]
    en  Good. Off with you, before I say a third thing I can't take back.
    >>  ............................................
    pt  Bom. Some daqui, antes que eu diga uma terceira coisa irreversível.
    >>  ............................................
```

---


## `conversations.topic.fears.open.followup`

**Reached from 8 route(s):** `conversations.topic.fears.open.disclosed` / `hold_it`; `conversations.topic.fears.open.disclosed` / `ask_when`; `conversations.topic.fears.open.respond` / `comfort`; `conversations.topic.fears.open.respond` / `comfort`; `conversations.topic.fears.open.respond` / `comfort`; `conversations.topic.fears.open.respond` / `press`; `conversations.topic.fears.open.respond` / `share`; `conversations.topic.fears.open.respond` / `no_words`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.no_words` — e.g. "...Nor did I, for years. That's rather the whole trouble with it. We'll sit here not knowing together."
- `conversations.fears.open.comfort.partial` — e.g. "Mm. It is. Not much to be done about it, though."
- `conversations.fears.open.comfort.plain` — e.g. "That's... yes. It is hard to carry. Thank you for saying so."
- `conversations.fears.open.comfort.success` — e.g. "...That helps. I've no idea why saying it aloud to someone helps, but it does."
- `conversations.fears.open.disclosed.ask_when` — e.g. "Longer than I've lived in this house. It came with me, like a piece of furniture I keep meaning to leave behind."
- `conversations.fears.open.disclosed.hold_it` — e.g. "...You say that like it's a sack of grain. Like it's a thing that divides up. Alright. Take a corner of it, then."
- `conversations.fears.open.press.success` — e.g. "There's more, yes. Give me a breath. ...There. Two of us carrying it now."
- `conversations.fears.open.share` — e.g. "You too? Huh. It's less heavy when two people carry it."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.open.followup   [16 chars]
    en  So now you know.
    >>  ............................................
    pt  Então agora você sabe.
    >>  ............................................
```


### Button `pledge` — "You won't face it alone."

*stance family `practical_help` · tone `plain` · answers the beat(s) `fears.no_words.to.fears.open`, `fears.open.comfort.partial.to.fears.open`, `fears.open.comfort.plain.to.fears.open`, `fears.open.comfort.success.to.fears.open`, `fears.open.disclosed.ask_when.to.fears.open`, `fears.open.disclosed.hold_it.to.fears.open`, `fears.open.press.success.to.fears.open`, `fears.open.share.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.pledge` — accepted phrasings: "you will not face it alone"; "not alone"; "we face it together"; "i promise"
  - the message must contain one of: `alone`, `together`, `promise`, `beside`
  - scored words: `alone`(1.5), `together`(1.5), `with`(0.5), `promise`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.followup.pledge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.followup.pledge   [24 chars]
    en  You won't face it alone.
    >>  ............................................
    pt  Você não vai enfrentar isso sozinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when disposition trust >= 55
- Fires when: RULED OUT when the `dispositions` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `fears.open.pledge`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `fears.open.pledge`)_
- Does: exclusive `fears.support` -> `pledged` (locks the other side out for good)
- Does: remembers `mcaconversations.pledge.fears` (this player only) for 72000 ticks
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.pledge.trusted
WHO    VILLAGER — what the player reads after pressing "You won't face it alone."
       spoken on: conversations.topic.fears.open.followup, button `pledge`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.pledge.trusted.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.pledge.trusted/1   [96 chars]
    en  ...I know you will. That's the strange part — I said it and then realised I already believed it.
    >>  ............................................
    pt  ...Eu sei que vai. Essa é a parte estranha — eu disse e percebi que já acreditava.
    >>  ............................................
  dialogue.conversations.fears.open.pledge.trusted/2   [87 chars]
    en  I'd not have told you this a year ago, %1$s. I'd not have told anyone. Hold on to that.
    >>  ............................................
    pt  Eu não teria te contado isso um ano atrás, %1$s. Não teria contado a ninguém. Guarde isso.
    >>  ............................................
  dialogue.conversations.fears.open.pledge.trusted/3   [85 chars]
    en  You don't have to promise. You've been doing it for months without the word attached.
    >>  ............................................
    pt  Você não precisa prometer. Vem fazendo isso há meses sem a palavra junto.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when disposition trust >= 55  _(chance -2000)_
- Does: **hearts +2** — decision id `fears.open.pledge`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `fears.open.pledge`)_
- Does: exclusive `fears.support` -> `pledged` (locks the other side out for good)
- Does: remembers `mcaconversations.pledge.fears` (this player only) for 72000 ticks
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.pledge
WHO    VILLAGER — what the player reads after pressing "You won't face it alone."
       spoken on: conversations.topic.fears.open.followup, button `pledge`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.pledge.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.pledge/1   [58 chars]
    en  ...Nobody's said that before. I'm holding you to it, %1$s.
    >>  ............................................
    pt  ...Ninguém nunca disse isso. Vou cobrar de você, %1$s.
    >>  ............................................
  dialogue.conversations.fears.open.pledge/2   [43 chars]
    en  Not alone. Right. I'll try to believe that.
    >>  ............................................
    pt  Não sozinho. Certo. Vou tentar acreditar nisso.
    >>  ............................................
  dialogue.conversations.fears.open.pledge/3   [46 chars]
    en  Careful what you promise. I remember promises.
    >>  ............................................
    pt  Cuidado com o que promete. Eu lembro de promessas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. ...Say it once more so I can put it somewhere I'll find it later.
    >>  ............................................
    pt  Você vai ficar comigo. ...Fala mais uma vez para eu guardar num lugar em que eu ache depois.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.pledge
    en  You'll be there. Right — then I train for it. That's how I do everything else.
    >>  ............................................
    pt  Você vai estar lá. Certo — então eu treino para isso. É como eu faço todo o resto.
    >>  ............................................
  confident.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Good. I'd have done it alone, but I'd rather not, and that's new.
    >>  ............................................
    pt  Você vai ficar comigo. Bom. Eu faria sozinho, mas prefiro não fazer, e isso é novo.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. I'll believe it Tuesday. ...But I've written it down.
    >>  ............................................
    pt  Você vai ficar comigo. Acredito na terça. ...Mas eu anotei.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Right — then I'm telling everyone I've got backup, and I'm not sorry.
    >>  ............................................
    pt  Você vai ficar comigo. Certo — então eu vou contar para todo mundo que tenho reforço, sem vergonha.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Careful — say things like that and I'll start expecting them.
    >>  ............................................
    pt  Você vai ficar comigo. Cuidado — fale coisas assim e eu começo a esperar por elas.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. That's — thank you. I'll not forget you said it, whatever comes.
    >>  ............................................
    pt  Você vai ficar comigo. Isso é — obrigado. Não vou esquecer que você disse, venha o que vier.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. ...People say that. You might even mean it. I'll try to let you.
    >>  ............................................
    pt  Você vai ficar comigo. ...As pessoas dizem isso. Você pode até estar falando sério. Vou tentar deixar.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. That's not a small thing to offer and I know exactly what it's worth.
    >>  ............................................
    pt  Você vai ficar comigo. Não é coisa pequena de se oferecer e eu sei exatamente quanto vale.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Hmph. We'll see. ...I've not said no, mind. Note that.
    >>  ............................................
    pt  Você vai ficar comigo. Hunf. A gente vê. ...Mas eu não disse não. Anote isso.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. ...I don't know what to do with that yet. I'll let you know.
    >>  ............................................
    pt  Você vai ficar comigo. ...Ainda não sei o que fazer com isso. Eu te aviso.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Then I'll get round to facing it. Eventually. But I will.
    >>  ............................................
    pt  Você vai ficar comigo. Então eu encaro isso. Uma hora. Mas eu encaro.
    >>  ............................................
  odd.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Right. I'm putting that in the same drawer as the other important things.
    >>  ............................................
    pt  Você vai ficar comigo. Certo. Vou guardar isso na mesma gaveta das outras coisas importantes.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Then I'm less afraid of it already, which seems too easy but there it is.
    >>  ............................................
    pt  Você vai ficar comigo. Então já tenho menos medo, o que parece fácil demais, mas é assim.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.pledge
    en  You'll stand with me?! Right. RIGHT. I'm going to be insufferable about this, fair warning.
    >>  ............................................
    pt  Você vai ficar comigo?! Certo. CERTO. Eu vou ser insuportável com isso, aviso desde já.
    >>  ............................................
  playful.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Right, well, now I have to actually do it. Look what you've done.
    >>  ............................................
    pt  Você vai ficar comigo. Certo, bom, agora eu tenho que fazer de verdade. Olha o que você fez.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. That's decent of you. I'll hold you to it without any pressure attached.
    >>  ............................................
    pt  Você vai ficar comigo. Decente da sua parte. Eu cobro sem nenhuma pressão junto.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. ...Give me a moment. That landed harder than I was ready for.
    >>  ............................................
    pt  Você vai ficar comigo. ...Me dá um instante. Isso bateu mais forte do que eu esperava.
    >>  ............................................
  shy.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. ...I'd like that. Sorry, that was hard to say.
    >>  ............................................
    pt  Você vai ficar comigo. ...Eu ia gostar disso. Desculpa, foi difícil dizer.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Then we're halfway there already, and I mean that.
    >>  ............................................
    pt  Você vai ficar comigo. Então já estamos na metade do caminho, e eu falo sério.
    >>  ............................................
  witty.dialogue.conversations.fears.open.pledge
    en  You'll stand with me. Now I've a witness, which is either support or terrible tactics on your part.
    >>  ............................................
    pt  Você vai ficar comigo. Agora tenho testemunha, o que é apoio ou uma péssima tática sua.
    >>  ............................................
```

</details>


### Button `step_back` — "I can't promise that. But I'm listening."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.no_words.to.fears.open`, `fears.open.comfort.partial.to.fears.open`, `fears.open.comfort.plain.to.fears.open`, `fears.open.comfort.success.to.fears.open`, `fears.open.disclosed.ask_when.to.fears.open`, `fears.open.disclosed.hold_it.to.fears.open`, `fears.open.press.success.to.fears.open`, `fears.open.share.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.step_back` — accepted phrasings: "i cannot promise that"; "i will not promise"; "but i am listening"; "i would rather be honest"
  - the message must contain one of: `cannot`, `listening`, `honest`, `wont`
  - scored words: `cannot`(1.5), `listening`(1.5), `honest`(1.2), `wont`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.followup.step_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.followup.step_back   [40 chars]
    en  I can't promise that. But I'm listening.
    >>  ............................................
    pt  Não posso prometer isso. Mas estou ouvindo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.open.step_back`, budget `deep`, replay policy `once`
- Does: disposition — respect +4, trust +1  _(recorded under topic `fears.open.step_back`)_
- Does: exclusive `fears.support` -> `stepped_back` (locks the other side out for good)
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.step_back
WHO    VILLAGER — what the player reads after pressing "I can't promise that. But I'm listening."
       spoken on: conversations.topic.fears.open.followup, button `step_back`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.step_back.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.step_back/1   [61 chars]
    en  ...That's honest. I'd rather that than a promise you'd break.
    >>  ............................................
    pt  ...Isso é honesto. Prefiro isso a uma promessa que você quebraria.
    >>  ............................................
  dialogue.conversations.fears.open.step_back/2   [65 chars]
    en  Good. People promise too easily around here. Listening is enough.
    >>  ............................................
    pt  Bom. As pessoas prometem fácil demais por aqui. Ouvir já basta.
    >>  ............................................
  dialogue.conversations.fears.open.step_back/3   [50 chars]
    en  No promises. Understood. You still stayed, though.
    >>  ............................................
    pt  Sem promessas. Entendido. Mas você ficou mesmo assim.
    >>  ............................................
```


### Button `challenge` — "You could face it, you know."

*stance family `challenge` · tone `blunt` · answers the beat(s) `fears.no_words.to.fears.open`, `fears.open.comfort.partial.to.fears.open`, `fears.open.comfort.plain.to.fears.open`, `fears.open.comfort.success.to.fears.open`, `fears.open.disclosed.ask_when.to.fears.open`, `fears.open.disclosed.hold_it.to.fears.open`, `fears.open.press.success.to.fears.open`, `fears.open.share.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.challenge` — accepted phrasings: "you could face it"; "you could beat this"; "stand up to it"; "you can overcome it"
  - the message must contain one of: `face`, `stand`, `beat`, `overcome`
  - scored words: `face`(1.5), `stand`(1.2), `beat`(1.2), `could`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.followup.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.followup.challenge   [28 chars]
    en  You could face it, you know.
    >>  ............................................
    pt  Você poderia encarar isso, sabia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.challenge` lands on tier **crit** (axis respect, difficulty 45, stance challenge, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +3** — decision id `fears.open.challenge.crit`, budget `deep`, replay policy `once`
- Does: disposition — respect +6, trust +2  _(recorded under topic `fears.open.challenge`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.challenge.crit
WHO    VILLAGER — what the player reads after pressing "You could face it, you know."
       spoken on: conversations.topic.fears.open.followup, button `challenge`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.challenge.crit.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.challenge.crit/1   [128 chars]
    en  ...Nobody ever offered the 'with you' part before. That's the part that matters. Alright. Next storm, I'll stand in the doorway.
    >>  ............................................
    pt  ...Ninguém nunca ofereceu a parte do 'com você'. É essa parte que importa. Certo. Na próxima tempestade, eu fico na porta.
    >>  ............................................
  dialogue.conversations.fears.open.challenge.crit/2   [98 chars]
    en  You'd actually stand there with me. Huh. Fine. I'm holding you to it, and you're holding me to it.
    >>  ............................................
    pt  Você ficaria lá comigo de verdade. Hm. Tudo bem. Eu te cobro, e você me cobra.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before, %1$s. I'd stopped hearing the offer at all.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes, %1$s. Eu tinha parado de ouvir a oferta.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Give me a moment. That's undone something I'd built rather carefully.
    >>  ............................................
    pt  Com você. Me dê um momento. Isso desfez algo que eu tinha construído com cuidado.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. It's the part that makes it possible, in time.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É a parte que torna possível, com o tempo.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Then it stops being a thing I have to do this week and becomes a thing we'll get to.
    >>  ............................................
    pt  Com você. Aí deixa de ser algo que eu tenho que fazer esta semana e vira algo a que a gente chega.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. That's the part that changes it.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É essa parte que muda tudo.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. That's a different sentence from the one people usually say.
    >>  ............................................
    pt  Com você. Certo. É uma frase diferente da que as pessoas costumam dizer.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. That's the part that changes it.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É essa parte que muda tudo.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. That's a different sentence from the one people usually say.
    >>  ............................................
    pt  Com você. Certo. É uma frase diferente da que as pessoas costumam dizer.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before, %1$s. That's the part that changes it.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes, %1$s. É essa parte que muda tudo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. I could do it with you. I could not do it alone and I've proved that twice.
    >>  ............................................
    pt  Com você. Com você eu conseguiria. Sozinho eu não consigo e já provei duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before, %1$s. That's the part that changes it.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes, %1$s. É essa parte que muda tudo.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. I could do it with you. I could not do it alone and I've proved that twice.
    >>  ............................................
    pt  Com você. Com você eu conseguiria. Sozinho eu não consigo e já provei duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before, %1$s. That's the part that changes it.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes, %1$s. É essa parte que muda tudo.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. I could do it with you. I could not do it alone and I've proved that twice.
    >>  ............................................
    pt  Com você. Com você eu conseguiria. Sozinho eu não consigo e já provei duas vezes.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before, %1$s. I'd stopped hearing the offer at all.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes, %1$s. Eu tinha parado de ouvir a oferta.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Give me a moment. That's undone something I'd built rather carefully.
    >>  ............................................
    pt  Com você. Me dê um momento. Isso desfez algo que eu tinha construído com cuidado.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. That's the part that changes it.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É essa parte que muda tudo.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. That's a different sentence from the one people usually say.
    >>  ............................................
    pt  Com você. Certo. É uma frase diferente da que as pessoas costumam dizer.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. That's the part that changes it.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É essa parte que muda tudo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. That's a different sentence from the one people usually say.
    >>  ............................................
    pt  Com você. Certo. É uma frase diferente da que as pessoas costumam dizer.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. That's different.
    >>  ............................................
    pt  Com você. Isso é diferente.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. It's the part that makes it possible, in time.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É a parte que torna possível, com o tempo.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Then it stops being a thing I have to do this week and becomes a thing we'll get to.
    >>  ............................................
    pt  Com você. Aí deixa de ser algo que eu tenho que fazer esta semana e vira algo a que a gente chega.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. That's different.
    >>  ............................................
    pt  Com você. Isso é diferente.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. It's the part that makes it possible, in time.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É a parte que torna possível, com o tempo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Then it stops being a thing I have to do this week and becomes a thing we'll get to.
    >>  ............................................
    pt  Com você. Aí deixa de ser algo que eu tenho que fazer esta semana e vira algo a que a gente chega.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody's ever offered the 'with you' part! That's the whole trick and you found it.
    >>  ............................................
    pt  Ninguém nunca ofereceu a parte do 'com você'! É todo o truque e você achou.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. Suddenly it's an outing rather than an ordeal.
    >>  ............................................
    pt  Com você. Certo. De repente virou um passeio em vez de uma provação.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody's ever offered the 'with you' part! That's the whole trick and you found it.
    >>  ............................................
    pt  Ninguém nunca ofereceu a parte do 'com você'! É todo o truque e você achou.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. Suddenly it's an outing rather than an ordeal.
    >>  ............................................
    pt  Com você. Certo. De repente virou um passeio em vez de uma provação.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody offered the 'with you' part before. It's the part that makes it possible, in time.
    >>  ............................................
    pt  Ninguém ofereceu a parte do 'com você' antes. É a parte que torna possível, com o tempo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Then it stops being a thing I have to do this week and becomes a thing we'll get to.
    >>  ............................................
    pt  Com você. Aí deixa de ser algo que eu tenho que fazer esta semana e vira algo a que a gente chega.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before, %1$s. I'd stopped hearing the offer at all.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes, %1$s. Eu tinha parado de ouvir a oferta.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Give me a moment. That's undone something I'd built rather carefully.
    >>  ............................................
    pt  Com você. Me dê um momento. Isso desfez algo que eu tinha construído com cuidado.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.crit/1
    en  ...Nobody offered the 'with you' part before.
    >>  ............................................
    pt  ...Ninguém ofereceu a parte do 'com você' antes.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. That's different.
    >>  ............................................
    pt  Com você. Isso é diferente.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody's ever offered the 'with you' part! That's the whole trick and you found it.
    >>  ............................................
    pt  Ninguém nunca ofereceu a parte do 'com você'! É todo o truque e você achou.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. Suddenly it's an outing rather than an ordeal.
    >>  ............................................
    pt  Com você. Certo. De repente virou um passeio em vez de uma provação.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.crit/1
    en  Nobody's ever offered the 'with you' part! That's the whole trick and you found it.
    >>  ............................................
    pt  Ninguém nunca ofereceu a parte do 'com você'! É todo o truque e você achou.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.crit/2
    en  With you. Right. Suddenly it's an outing rather than an ordeal.
    >>  ............................................
    pt  Com você. Certo. De repente virou um passeio em vez de uma provação.
    >>  ............................................
```

</details>


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.challenge` lands on tier **success** (axis respect, difficulty 45, stance challenge, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `fears.open.challenge`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `fears.open.challenge`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.challenge.success
WHO    VILLAGER — what the player reads after pressing "You could face it, you know."
       spoken on: conversations.topic.fears.open.followup, button `challenge`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.challenge.success.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.challenge.success/1   [64 chars]
    en  Maybe. With a witness, maybe. Don't let me back out of it later.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe desistir depois.
    >>  ............................................
  dialogue.conversations.fears.open.challenge.success/2   [64 chars]
    en  You make it sound almost possible. Ask me again when it's close.
    >>  ............................................
    pt  Você faz parecer quase possível. Me pergunte de novo quando estiver perto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later, %1$s — I will try to.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois, %1$s — eu vou tentar.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there. I'll be frightened the whole time and I'd still rather have said yes.
    >>  ............................................
    pt  Com alguém lá. Vou ficar com medo o tempo todo e ainda prefiro ter dito sim.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. There's no hurry — only don't let me quietly drop it.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Sem pressa — só não me deixe largar em silêncio.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, in time. I'd want to pick the week myself.
    >>  ............................................
    pt  Com alguém lá, com o tempo. Eu ia querer escolher a semana.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, yes. Hold me to that when I try to forget I said it.
    >>  ............................................
    pt  Com alguém lá, sim. Me cobre isso quando eu tentar esquecer que eu disse.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, yes. Hold me to that when I try to forget I said it.
    >>  ............................................
    pt  Com alguém lá, sim. Me cobre isso quando eu tentar esquecer que eu disse.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later, %1$s.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.success/2
    en  With you there, yes. That's the only version of this I could actually say yes to.
    >>  ............................................
    pt  Com você lá, sim. É a única versão disso a que eu conseguiria dizer sim.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later, %1$s.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.success/2
    en  With you there, yes. That's the only version of this I could actually say yes to.
    >>  ............................................
    pt  Com você lá, sim. É a única versão disso a que eu conseguiria dizer sim.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later, %1$s.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.success/2
    en  With you there, yes. That's the only version of this I could actually say yes to.
    >>  ............................................
    pt  Com você lá, sim. É a única versão disso a que eu conseguiria dizer sim.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later, %1$s — I will try to.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois, %1$s — eu vou tentar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there. I'll be frightened the whole time and I'd still rather have said yes.
    >>  ............................................
    pt  Com alguém lá. Vou ficar com medo o tempo todo e ainda prefiro ter dito sim.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, yes. Hold me to that when I try to forget I said it.
    >>  ............................................
    pt  Com alguém lá, sim. Me cobre isso quando eu tentar esquecer que eu disse.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, yes. Hold me to that when I try to forget I said it.
    >>  ............................................
    pt  Com alguém lá, sim. Me cobre isso quando eu tentar esquecer que eu disse.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. Don't let me back out later.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Não me deixe recuar depois.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there. Yes.
    >>  ............................................
    pt  Com alguém lá. Sim.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. There's no hurry — only don't let me quietly drop it.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Sem pressa — só não me deixe largar em silêncio.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, in time. I'd want to pick the week myself.
    >>  ............................................
    pt  Com alguém lá, com o tempo. Eu ia querer escolher a semana.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. Don't let me back out later.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Não me deixe recuar depois.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there. Yes.
    >>  ............................................
    pt  Com alguém lá. Sim.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. There's no hurry — only don't let me quietly drop it.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Sem pressa — só não me deixe largar em silêncio.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, in time. I'd want to pick the week myself.
    >>  ............................................
    pt  Com alguém lá, com o tempo. Eu ia querer escolher a semana.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe! With a witness, maybe. Don't let me back out of it later — I'm extremely good at that.
    >>  ............................................
    pt  Talvez! Com uma testemunha, talvez. Não me deixe recuar depois — eu sou ótimo nisso.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody watching, yes. Terrible plan. Let's do it.
    >>  ............................................
    pt  Com alguém olhando, sim. Plano terrível. Vamos fazer.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe! With a witness, maybe. Don't let me back out of it later — I'm extremely good at that.
    >>  ............................................
    pt  Talvez! Com uma testemunha, talvez. Não me deixe recuar depois — eu sou ótimo nisso.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody watching, yes. Terrible plan. Let's do it.
    >>  ............................................
    pt  Com alguém olhando, sim. Plano terrível. Vamos fazer.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. There's no hurry — only don't let me quietly drop it.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Sem pressa — só não me deixe largar em silêncio.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there, in time. I'd want to pick the week myself.
    >>  ............................................
    pt  Com alguém lá, com o tempo. Eu ia querer escolher a semana.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness, maybe. Don't let me back out of it later, %1$s — I will try to.
    >>  ............................................
    pt  Talvez. Com uma testemunha, talvez. Não me deixe recuar depois, %1$s — eu vou tentar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there. I'll be frightened the whole time and I'd still rather have said yes.
    >>  ............................................
    pt  Com alguém lá. Vou ficar com medo o tempo todo e ainda prefiro ter dito sim.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe. With a witness. Don't let me back out later.
    >>  ............................................
    pt  Talvez. Com uma testemunha. Não me deixe recuar depois.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody there. Yes.
    >>  ............................................
    pt  Com alguém lá. Sim.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe! With a witness, maybe. Don't let me back out of it later — I'm extremely good at that.
    >>  ............................................
    pt  Talvez! Com uma testemunha, talvez. Não me deixe recuar depois — eu sou ótimo nisso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody watching, yes. Terrible plan. Let's do it.
    >>  ............................................
    pt  Com alguém olhando, sim. Plano terrível. Vamos fazer.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.success/1
    en  Maybe! With a witness, maybe. Don't let me back out of it later — I'm extremely good at that.
    >>  ............................................
    pt  Talvez! Com uma testemunha, talvez. Não me deixe recuar depois — eu sou ótimo nisso.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.success/2
    en  With somebody watching, yes. Terrible plan. Let's do it.
    >>  ............................................
    pt  Com alguém olhando, sim. Plano terrível. Vamos fazer.
    >>  ............................................
```

</details>


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.challenge` lands on tier **partial** (axis respect, difficulty 45, stance challenge, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: disposition — respect +1, tension +1  _(recorded under topic `fears.open.challenge`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.challenge.partial
WHO    VILLAGER — what the player reads after pressing "You could face it, you know."
       spoken on: conversations.topic.fears.open.followup, button `challenge`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.challenge.partial.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.challenge.partial/1   [62 chars]
    en  Easy for you — you've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil para você — você tem outro lugar para estar quando der errado.
    >>  ............................................
  dialogue.conversations.fears.open.challenge.partial/2   [63 chars]
    en  Hm. Half of me believes you. The other half keeps the accounts.
    >>  ............................................
    pt  Hm. Metade de mim acredita em você. A outra metade faz as contas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong, %1$s. I haven't.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado, %1$s. Eu não tenho.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.challenge.partial/2
    en  That's the sentence everyone says. It has never once made it smaller.
    >>  ............................................
    pt  É a frase que todo mundo diz. Nunca deixou menor uma única vez.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You'd be somewhere else by the time it mattered.
    >>  ............................................
    pt  Fácil pra você. Você estaria em outro lugar quando importasse.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.partial/2
    en  It's an easier thing to recommend than to live beside for twenty years.
    >>  ............................................
    pt  É mais fácil de recomendar do que de conviver por vinte anos.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.partial/2
    en  You'd manage it. You'd also leave afterwards, which is the difference.
    >>  ............................................
    pt  Você daria conta. E também iria embora depois, que é a diferença.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.partial/2
    en  You'd manage it. You'd also leave afterwards, which is the difference.
    >>  ............................................
    pt  Você daria conta. E também iria embora depois, que é a diferença.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you, %1$s. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você, %1$s. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.partial/2
    en  I know you mean it kindly. It still comes from somebody who can leave.
    >>  ............................................
    pt  Eu sei que é bem-intencionado. Ainda vem de alguém que pode ir embora.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you, %1$s. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você, %1$s. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.partial/2
    en  I know you mean it kindly. It still comes from somebody who can leave.
    >>  ............................................
    pt  Eu sei que é bem-intencionado. Ainda vem de alguém que pode ir embora.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you, %1$s. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você, %1$s. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.partial/2
    en  I know you mean it kindly. It still comes from somebody who can leave.
    >>  ............................................
    pt  Eu sei que é bem-intencionado. Ainda vem de alguém que pode ir embora.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong, %1$s. I haven't.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado, %1$s. Eu não tenho.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.partial/2
    en  That's the sentence everyone says. It has never once made it smaller.
    >>  ............................................
    pt  É a frase que todo mundo diz. Nunca deixou menor uma única vez.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.partial/2
    en  You'd manage it. You'd also leave afterwards, which is the difference.
    >>  ............................................
    pt  Você daria conta. E também iria embora depois, que é a diferença.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.partial/2
    en  You'd manage it. You'd also leave afterwards, which is the difference.
    >>  ............................................
    pt  Você daria conta. E também iria embora depois, que é a diferença.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.partial/2
    en  You don't have to live here afterwards.
    >>  ............................................
    pt  Você não tem que morar aqui depois.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You'd be somewhere else by the time it mattered.
    >>  ............................................
    pt  Fácil pra você. Você estaria em outro lugar quando importasse.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.partial/2
    en  It's an easier thing to recommend than to live beside for twenty years.
    >>  ............................................
    pt  É mais fácil de recomendar do que de conviver por vinte anos.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.partial/2
    en  You don't have to live here afterwards.
    >>  ............................................
    pt  Você não tem que morar aqui depois.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You'd be somewhere else by the time it mattered.
    >>  ............................................
    pt  Fácil pra você. Você estaria em outro lugar quando importasse.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.partial/2
    en  It's an easier thing to recommend than to live beside for twenty years.
    >>  ............................................
    pt  É mais fácil de recomendar do que de conviver por vinte anos.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you! You've somewhere else to be when it goes wrong, which I do not.
    >>  ............................................
    pt  Fácil pra você! Você tem outro lugar pra estar quando der errado, e eu não.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.partial/2
    en  Ha. You'd be fine. You'd be fine and elsewhere, which is a marvellous combination.
    >>  ............................................
    pt  Ha. Você ficaria bem. Bem e em outro lugar, uma combinação maravilhosa.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you! You've somewhere else to be when it goes wrong, which I do not.
    >>  ............................................
    pt  Fácil pra você! Você tem outro lugar pra estar quando der errado, e eu não.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.partial/2
    en  Ha. You'd be fine. You'd be fine and elsewhere, which is a marvellous combination.
    >>  ............................................
    pt  Ha. Você ficaria bem. Bem e em outro lugar, uma combinação maravilhosa.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You'd be somewhere else by the time it mattered.
    >>  ............................................
    pt  Fácil pra você. Você estaria em outro lugar quando importasse.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.partial/2
    en  It's an easier thing to recommend than to live beside for twenty years.
    >>  ............................................
    pt  É mais fácil de recomendar do que de conviver por vinte anos.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be when it goes wrong, %1$s. I haven't.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar quando der errado, %1$s. Eu não tenho.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.partial/2
    en  That's the sentence everyone says. It has never once made it smaller.
    >>  ............................................
    pt  É a frase que todo mundo diz. Nunca deixou menor uma única vez.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you. You've somewhere else to be.
    >>  ............................................
    pt  Fácil pra você. Você tem outro lugar pra estar.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.partial/2
    en  You don't have to live here afterwards.
    >>  ............................................
    pt  Você não tem que morar aqui depois.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you! You've somewhere else to be when it goes wrong, which I do not.
    >>  ............................................
    pt  Fácil pra você! Você tem outro lugar pra estar quando der errado, e eu não.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.partial/2
    en  Ha. You'd be fine. You'd be fine and elsewhere, which is a marvellous combination.
    >>  ............................................
    pt  Ha. Você ficaria bem. Bem e em outro lugar, uma combinação maravilhosa.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.partial/1
    en  Easy for you! You've somewhere else to be when it goes wrong, which I do not.
    >>  ............................................
    pt  Fácil pra você! Você tem outro lugar pra estar quando der errado, e eu não.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.partial/2
    en  Ha. You'd be fine. You'd be fine and elsewhere, which is a marvellous combination.
    >>  ............................................
    pt  Ha. Você ficaria bem. Bem e em outro lugar, uma combinação maravilhosa.
    >>  ............................................
```

</details>


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.challenge` lands on tier **rebuff** (axis respect, difficulty 45, stance challenge, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -2** — decision id `fears.open.challenge.rebuff`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5, respect -2  _(recorded under topic `fears.open.challenge`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.close.rebuffed`
- …where the player's next choices will be: "Fair. That came out wrong." | "Understood. I'll shut up." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.challenge.rebuff
WHO    VILLAGER — what the player reads after pressing "You could face it, you know."
       spoken on: conversations.topic.fears.open.followup, button `challenge`
       leaves the player on: conversations.topic.fears.open.close.rebuffed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.challenge.rebuff.to.fears.open.close.rebuffed`: the villager refuses. Subject `fears.open.close.rebuffed`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, boundary_push, exit
```

```text
  dialogue.conversations.fears.open.challenge.rebuff/1   [78 chars]
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não transforme meu medo num teste do seu caráter. Não é sobre a sua coragem.
    >>  ............................................
  dialogue.conversations.fears.open.challenge.rebuff/2   [61 chars]
    en  Face it, you say. You'll be gone by frost like everyone else.
    >>  ............................................
    pt  Encarar, você diz. Você vai embora antes da geada como todo mundo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Please don't. It took a great deal to say it once, %1$s.
    >>  ............................................
    pt  Por favor, não. Custou muito dizer uma vez, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Don't make it a test. I'd only just got the words out.
    >>  ............................................
    pt  Não faça disso um teste. Eu tinha acabado de conseguir dizer.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't, %1$s. I gave you that; I didn't hand you a challenge to win.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te dei aquilo; não te entreguei um desafio pra vencer.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.rebuff/2
    en  That's not what I was asking for, and I think you know it.
    >>  ............................................
    pt  Não é isso que eu estava pedindo, e eu acho que você sabe.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't, %1$s. I gave you that; I didn't hand you a challenge to win.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te dei aquilo; não te entreguei um desafio pra vencer.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.rebuff/2
    en  That's not what I was asking for, and I think you know it.
    >>  ............................................
    pt  Não é isso que eu estava pedindo, e eu acho que você sabe.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't, %1$s. I gave you that; I didn't hand you a challenge to win.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te dei aquilo; não te entreguei um desafio pra vencer.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.rebuff/2
    en  That's not what I was asking for, and I think you know it.
    >>  ............................................
    pt  Não é isso que eu estava pedindo, e eu acho que você sabe.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Please don't. It took a great deal to say it once, %1$s.
    >>  ............................................
    pt  Por favor, não. Custou muito dizer uma vez, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Don't make it a test. I'd only just got the words out.
    >>  ............................................
    pt  Não faça disso um teste. Eu tinha acabado de conseguir dizer.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't make my fear a test of your character. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça do meu medo um teste do seu caráter. Não é seu pra ser corajoso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. You don't get to be bold with the thing that frightens me.
    >>  ............................................
    pt  Não. Você não tem o direito de ser ousado com o que me assusta.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.rebuff/1
    en  No. Not like that.
    >>  ............................................
    pt  Não. Assim não.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Don't. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça isso. Não é seu pra ser corajoso.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.rebuff/1
    en  No. Not like that.
    >>  ............................................
    pt  Não. Assim não.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Don't. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça isso. Não é seu pra ser corajoso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Don't. It's mine, and it's not a thing to be brave at.
    >>  ............................................
    pt  Não. É meu, e não é coisa pra se ser corajoso.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.rebuff/2
    en  No. Leave it be. Courage isn't what it wants from either of us.
    >>  ............................................
    pt  Não. Deixe estar. Coragem não é o que isso quer de nenhum de nós.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Please don't. It took a great deal to say it once, %1$s.
    >>  ............................................
    pt  Por favor, não. Custou muito dizer uma vez, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Don't make it a test. I'd only just got the words out.
    >>  ............................................
    pt  Não faça disso um teste. Eu tinha acabado de conseguir dizer.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.rebuff/1
    en  No. Not like that.
    >>  ............................................
    pt  Não. Assim não.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Don't. It isn't yours to be brave about.
    >>  ............................................
    pt  Não faça isso. Não é seu pra ser corajoso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.rebuff/1
    en  Ah — no. That's not a dare and I'm not taking it up.
    >>  ............................................
    pt  Ah — não. Isso não é um desafio e eu não vou aceitar.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.rebuff/2
    en  Right, stop there. You've turned my fear into a game and I've stopped playing.
    >>  ............................................
    pt  Certo, pare aí. Você virou meu medo num jogo e eu parei de jogar.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +1** — decision id `fears.open.challenge`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +2  _(recorded under topic `fears.open.challenge`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.close`
- …where the player's next choices will be: "Thank you for trusting me with that." | "That took something to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.challenge.plain
WHO    VILLAGER — what the player reads after pressing "You could face it, you know."
       spoken on: conversations.topic.fears.open.followup, button `challenge`
       leaves the player on: conversations.topic.fears.open.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.challenge.plain.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.challenge.plain/1   [36 chars]
    en  Maybe I could. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, mas talvez.
    >>  ............................................
  dialogue.conversations.fears.open.challenge.plain/2   [62 chars]
    en  That's a thought I'll be carrying around now. Thanks for that.
    >>  ............................................
    pt  Agora vou ficar carregando esse pensamento por aí. Obrigado por isso.
    >>  ............................................
  dialogue.conversations.fears.open.challenge.plain/3   [46 chars]
    en  You say it like it's simple. It might even be.
    >>  ............................................
    pt  Você fala como se fosse simples. Talvez até seja.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, %1$s, but maybe, and maybe is more than I had this morning.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, %1$s, mas talvez, e talvez é mais do que eu tinha de manhã.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd rather say possibly than yes and then have to take it back.
    >>  ............................................
    pt  Possivelmente. Prefiro dizer possivelmente a dizer sim e ter que voltar atrás.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I've frightened myself just by saying the word.
    >>  ............................................
    pt  Talvez. Eu me assustei só de dizer a palavra.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today. There's no calendar on it.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não. Não tem calendário nisso.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. In a year I might say it differently, and that's fine.
    >>  ............................................
    pt  Possivelmente. Em um ano eu posso dizer diferente, e tudo bem.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. These things arrive when they arrive.
    >>  ............................................
    pt  Talvez. Essas coisas chegam quando chegam.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, mas talvez.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want a better day than this one to try it on.
    >>  ............................................
    pt  Possivelmente. Eu ia querer um dia melhor que este pra tentar.
    >>  ............................................
  confident.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I'll not say more than perhaps.
    >>  ............................................
    pt  Talvez. Não vou dizer mais que talvez.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, mas talvez.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want a better day than this one to try it on.
    >>  ............................................
    pt  Possivelmente. Eu ia querer um dia melhor que este pra tentar.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I'll not say more than perhaps.
    >>  ............................................
    pt  Talvez. Não vou dizer mais que talvez.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could, %1$s. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse, %1$s. Hoje não, mas talvez.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want you there, if it came to it, and I've not said that before.
    >>  ............................................
    pt  Possivelmente. Eu ia querer você lá, se chegasse a isso, e eu nunca disse isso antes.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. Ask me again some other week and we'll see what I say.
    >>  ............................................
    pt  Talvez. Me pergunte em outra semana e a gente vê o que eu digo.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could, %1$s. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse, %1$s. Hoje não, mas talvez.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want you there, if it came to it, and I've not said that before.
    >>  ............................................
    pt  Possivelmente. Eu ia querer você lá, se chegasse a isso, e eu nunca disse isso antes.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. Ask me again some other week and we'll see what I say.
    >>  ............................................
    pt  Talvez. Me pergunte em outra semana e a gente vê o que eu digo.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could, %1$s. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse, %1$s. Hoje não, mas talvez.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want you there, if it came to it, and I've not said that before.
    >>  ............................................
    pt  Possivelmente. Eu ia querer você lá, se chegasse a isso, e eu nunca disse isso antes.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. Ask me again some other week and we'll see what I say.
    >>  ............................................
    pt  Talvez. Me pergunte em outra semana e a gente vê o que eu digo.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, %1$s, but maybe, and maybe is more than I had this morning.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, %1$s, mas talvez, e talvez é mais do que eu tinha de manhã.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd rather say possibly than yes and then have to take it back.
    >>  ............................................
    pt  Possivelmente. Prefiro dizer possivelmente a dizer sim e ter que voltar atrás.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I've frightened myself just by saying the word.
    >>  ............................................
    pt  Talvez. Eu me assustei só de dizer a palavra.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, mas talvez.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want a better day than this one to try it on.
    >>  ............................................
    pt  Possivelmente. Eu ia querer um dia melhor que este pra tentar.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I'll not say more than perhaps.
    >>  ............................................
    pt  Talvez. Não vou dizer mais que talvez.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, but maybe.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, mas talvez.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd want a better day than this one to try it on.
    >>  ............................................
    pt  Possivelmente. Eu ia querer um dia melhor que este pra tentar.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I'll not say more than perhaps.
    >>  ............................................
    pt  Talvez. Não vou dizer mais que talvez.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Not now.
    >>  ............................................
    pt  Possivelmente. Agora não.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps.
    >>  ............................................
    pt  Talvez.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today. There's no calendar on it.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não. Não tem calendário nisso.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. In a year I might say it differently, and that's fine.
    >>  ............................................
    pt  Possivelmente. Em um ano eu posso dizer diferente, e tudo bem.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. These things arrive when they arrive.
    >>  ............................................
    pt  Talvez. Essas coisas chegam quando chegam.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Not now.
    >>  ............................................
    pt  Possivelmente. Agora não.
    >>  ............................................
  odd.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps.
    >>  ............................................
    pt  Talvez.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today. There's no calendar on it.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não. Não tem calendário nisso.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. In a year I might say it differently, and that's fine.
    >>  ............................................
    pt  Possivelmente. Em um ano eu posso dizer diferente, e tudo bem.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. These things arrive when they arrive.
    >>  ............................................
    pt  Talvez. Essas coisas chegam quando chegam.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could! Not today. But maybe, and 'maybe' is new, so I'll take it.
    >>  ............................................
    pt  Talvez eu conseguisse! Hoje não. Mas talvez, e 'talvez' é novo, então eu aceito.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Ask me on a Tuesday with the sun out and I might say yes.
    >>  ............................................
    pt  Possivelmente. Me pergunte numa terça de sol e eu talvez diga sim.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps! There. I've said perhaps out loud and nothing terrible happened.
    >>  ............................................
    pt  Talvez! Pronto. Disse talvez em voz alta e nada terrível aconteceu.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could! Not today. But maybe, and 'maybe' is new, so I'll take it.
    >>  ............................................
    pt  Talvez eu conseguisse! Hoje não. Mas talvez, e 'talvez' é novo, então eu aceito.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Ask me on a Tuesday with the sun out and I might say yes.
    >>  ............................................
    pt  Possivelmente. Me pergunte numa terça de sol e eu talvez diga sim.
    >>  ............................................
  playful.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps! There. I've said perhaps out loud and nothing terrible happened.
    >>  ............................................
    pt  Talvez! Pronto. Disse talvez em voz alta e nada terrível aconteceu.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today. There's no calendar on it.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não. Não tem calendário nisso.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. In a year I might say it differently, and that's fine.
    >>  ............................................
    pt  Possivelmente. Em um ano eu posso dizer diferente, e tudo bem.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. These things arrive when they arrive.
    >>  ............................................
    pt  Talvez. Essas coisas chegam quando chegam.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today, %1$s, but maybe, and maybe is more than I had this morning.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não, %1$s, mas talvez, e talvez é mais do que eu tinha de manhã.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. I'd rather say possibly than yes and then have to take it back.
    >>  ............................................
    pt  Possivelmente. Prefiro dizer possivelmente a dizer sim e ter que voltar atrás.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps. I've frightened myself just by saying the word.
    >>  ............................................
    pt  Talvez. Eu me assustei só de dizer a palavra.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could. Not today.
    >>  ............................................
    pt  Talvez eu conseguisse. Hoje não.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Not now.
    >>  ............................................
    pt  Possivelmente. Agora não.
    >>  ............................................
  shy.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps.
    >>  ............................................
    pt  Talvez.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could! Not today. But maybe, and 'maybe' is new, so I'll take it.
    >>  ............................................
    pt  Talvez eu conseguisse! Hoje não. Mas talvez, e 'talvez' é novo, então eu aceito.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Ask me on a Tuesday with the sun out and I might say yes.
    >>  ............................................
    pt  Possivelmente. Me pergunte numa terça de sol e eu talvez diga sim.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps! There. I've said perhaps out loud and nothing terrible happened.
    >>  ............................................
    pt  Talvez! Pronto. Disse talvez em voz alta e nada terrível aconteceu.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.plain/1
    en  Maybe I could! Not today. But maybe, and 'maybe' is new, so I'll take it.
    >>  ............................................
    pt  Talvez eu conseguisse! Hoje não. Mas talvez, e 'talvez' é novo, então eu aceito.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.plain/2
    en  Possibly. Ask me on a Tuesday with the sun out and I might say yes.
    >>  ............................................
    pt  Possivelmente. Me pergunte numa terça de sol e eu talvez diga sim.
    >>  ............................................
  witty.dialogue.conversations.fears.open.challenge.plain/3
    en  Perhaps! There. I've said perhaps out loud and nothing terrible happened.
    >>  ............................................
    pt  Talvez! Pronto. Disse talvez em voz alta e nada terrível aconteceu.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.no_words.to.fears.open`, `fears.open.comfort.partial.to.fears.open`, `fears.open.comfort.plain.to.fears.open`, `fears.open.comfort.success.to.fears.open`, `fears.open.disclosed.ask_when.to.fears.open`, `fears.open.disclosed.hold_it.to.fears.open`, `fears.open.press.success.to.fears.open`, `fears.open.share.to.fears.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.followup.leave   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.fears.open.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.fears.open.respond / leave
```

```text
  dialogue.conversations.fears.open.leave/1   [44 chars]
    en  Aye. Thank you for not making a thing of it.
    >>  ............................................
    pt  Tá. Obrigado por não fazer disso um caso.
    >>  ............................................
  dialogue.conversations.fears.open.leave/2   [40 chars]
    en  Go on. It'll keep until next time, %1$s.
    >>  ............................................
    pt  Pode ir. Isso espera até a próxima, %1$s.
    >>  ............................................
  dialogue.conversations.fears.open.leave/3   [58 chars]
    en  Understood. Well. That's enough honesty for one afternoon.
    >>  ............................................
    pt  Entendido. Bom. Já chega de sinceridade por uma tarde.
    >>  ............................................
```

---


## `conversations.topic.fears.open.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `fears`; `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.first` — e.g. "Honestly? Thunder. And the thing that scratched at my door two winters back. Mostly the thunder."
- `conversations.fears.revisit` — e.g. "I've been chewing on what you asked me — about being afraid. It's lighter now that it's said."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.open.respond   [28 chars]
    en  That's it. That's the thing.
    >>  ............................................
    pt  É isso. É essa a coisa.
    >>  ............................................
```


### Button `comfort` — "That sounds hard to carry."

*stance family `empathy` · tone `gentle` · answers the beat(s) `fears.first.to.fears.open`, `fears.revisit.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.comfort` — accepted phrasings: "that sounds hard to carry"; "that is heavy"; "i am sorry"; "that sounds hard"
  - the message must contain one of: `carry`, `heavy`, `sorry`, `hard`
  - scored words: `carry`(1.5), `heavy`(1.2), `sorry`(1.0), `hard`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.respond.comfort
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.respond.comfort   [26 chars]
    en  That sounds hard to carry.
    >>  ............................................
    pt  Parece pesado de carregar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**

