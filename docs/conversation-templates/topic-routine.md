# Topic: routine

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `routine` |
| Opened from | question `conversations.cat.chitchat`, button `routine` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.chitchat` |
| Ages that can reach it | teen, adult |
| Stance families it must offer | `curiosity`, `practical_help`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.chitchat`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.routine
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.chitchat.routine   [20 chars]
    en  How do your days go?
    >>  ............................................
    pt  Como são seus dias?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.routine.after_the_work.respond`](#conversations-scene-routine-after-the-work-respond)
- [`conversations.scene.routine.followup`](#conversations-scene-routine-followup)
- [`conversations.scene.routine.the_first_hour.respond`](#conversations-scene-routine-the-first-hour-respond)
- [`conversations.topic.routine.more.respond`](#conversations-topic-routine-more-respond)
- [`conversations.topic.routine.open.respond`](#conversations-topic-routine-open-respond)

---

## `conversations.scene.routine.after_the_work.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `routine`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.routine.after_the_work` — e.g. "The work is finished and I have not yet decided whether the day counts as a good one."


```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.routine.after_the_work.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.routine.after_the_work.respond   [14 chars]
    en  The end of it.
    >>  ............................................
    pt  O fim dele.
    >>  ............................................
```


### Button `ask_the_verdict` — "Was it a good one?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `routine.after_the_work.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.after_the_work.ask_the_verdict` — accepted phrasings: "was it a good one"; "was it a good one"; "how did the day turn out"
  - the message must contain one of: `good`, `turn`
  - scored words: `good`(1.8), `turn`(1.8), `one`(0.8), `day`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work.respond.ask_the_verdict
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.after_the_work.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.after_the_work.respond.ask_the_verdict   [18 chars]
    en  Was it a good one?
    >>  ............................................
    pt  Foi um dia bom?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `routine.evening_remainder`)_
- Does: session `turn`
- Then opens: `conversations.scene.routine.followup`
- …where the player's next choices will be: "Back to it, then."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work.judged
WHO    VILLAGER — what the player reads after pressing "Was it a good one?"
       spoken on: conversations.scene.routine.after_the_work.respond, button `ask_the_verdict`
       leaves the player on: conversations.scene.routine.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.after_the_work.open.judged`: the villager reports. Subject `routine.evening_remainder`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.routine.after_the_work.judged/1   [94 chars]
    en  Middling, and middling is most of them, and I have made peace with that in the last few years.
    >>  ............................................
    pt  Mediano, e mediano é a maioria deles, e eu fiz as pazes com isso nos últimos anos.
    >>  ............................................
  dialogue.conversations.scene.routine.after_the_work.judged/2   [86 chars]
    en  Better than I expected at noon, which is the only comparison that ever means anything.
    >>  ............................................
    pt  Melhor do que eu esperava ao meio-dia, que é a única comparação que significa alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.routine.after_the_work.judged/3   [88 chars]
    en  It was. Nothing happened, everything got done, and nobody needed anything from me twice.
    >>  ............................................
    pt  Foi. Nada aconteceu, tudo ficou pronto e ninguém precisou de mim duas vezes.
    >>  ............................................
```


### Button `tell_them_to_leave_the_tidying` — "The tidying will keep."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `routine.after_the_work.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.after_the_work.tell_them_to_leave_the_tidying` — accepted phrasings: "the tidying will keep"; "the tidying will keep"; "leave the rest for the morning"
  - the message must contain one of: `tidying`, `morning`
  - scored words: `tidying`(1.8), `morning`(1.8), `keep`(0.8), `leave`(0.8), `rest`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work.respond.tell_them_to_leave_the_tidying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.after_the_work.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.after_the_work.respond.tell_them_to_leave_the_tidying   [22 chars]
    en  The tidying will keep.
    >>  ............................................
    pt  A arrumação pode esperar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.routine.evening.eased`, budget `quick`, replay policy `once`
- Does: disposition — warmth +3  _(recorded under topic `routine.evening_remainder`)_
- Does: session `turn`
- Then opens: `conversations.scene.routine.followup`
- …where the player's next choices will be: "Back to it, then."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work.eased
WHO    VILLAGER — what the player reads after pressing "The tidying will keep."
       spoken on: conversations.scene.routine.after_the_work.respond, button `tell_them_to_leave_the_tidying`
       leaves the player on: conversations.scene.routine.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.after_the_work.open.eased`: the villager accepts. Subject `routine.evening_remainder`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.routine.after_the_work.eased/1   [79 chars]
    en  It will. It has kept since spring and shows every sign of keeping until autumn.
    >>  ............................................
    pt  Pode. Vem esperando desde a primavera e dá todos os sinais de esperar até o outono.
    >>  ............................................
  dialogue.conversations.scene.routine.after_the_work.eased/2   [90 chars]
    en  Permission accepted. I was going to leave it anyway and now I can do so without the guilt.
    >>  ............................................
    pt  Permissão aceita. Eu ia deixar de qualquer jeito e agora posso fazer isso sem a culpa.
    >>  ............................................
  dialogue.conversations.scene.routine.after_the_work.eased/3   [74 chars]
    en  You are the second person to say that and the first one was right as well.
    >>  ............................................
    pt  Você é a segunda pessoa a dizer isso e a primeira também tinha razão.
    >>  ............................................
```


### Button `leave` — "Sounds a full day."

*stance family `exit` · tone `plain` · answers the beat(s) `routine.after_the_work.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.after_the_work.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.after_the_work.respond.leave   [18 chars]
    en  Sounds a full day.
    >>  ............................................
    pt  Parece um dia cheio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.leaving
WHO    VILLAGER — what the player reads after pressing "Sounds a full day."
       spoken on: conversations.scene.routine.after_the_work.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.scene.leaving`: the villager accepts. Subject `routine.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.routine.followup / leave; conversations.scene.routine.the_first_hour.respond / leave; conversations.topic.routine.more.respond / leave; conversations.topic.routine.open.respond / leave
```

```text
  dialogue.conversations.scene.routine.leaving/1   [33 chars]
    en  Same again tomorrow, near enough.
    >>  ............................................
    pt  Amanhã quase igual.
    >>  ............................................
  dialogue.conversations.scene.routine.leaving/2   [24 chars]
    en  That is the shape of it.
    >>  ............................................
    pt  É esse o formato da coisa.
    >>  ............................................
  dialogue.conversations.scene.routine.leaving/3   [17 chars]
    en  Back to it, then.
    >>  ............................................
    pt  De volta ao trabalho, então.
    >>  ............................................
```

---


## `conversations.scene.routine.followup`

**Reached from 4 route(s):** `conversations.scene.routine.after_the_work.respond` / `ask_the_verdict`; `conversations.scene.routine.after_the_work.respond` / `tell_them_to_leave_the_tidying`; `conversations.scene.routine.the_first_hour.respond` / `ask_what_is_planned`; `conversations.scene.routine.the_first_hour.respond` / `wish_them_a_good_one`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.routine.after_the_work.eased` — e.g. "It will. It has kept since spring and shows every sign of keeping until autumn."
- `conversations.scene.routine.after_the_work.judged` — e.g. "Middling, and middling is most of them, and I have made peace with that in the last few years."
- `conversations.scene.routine.the_first_hour.listed` — e.g. "Three things I promised and one I want to. I shall get through the three and resent it politely."
- `conversations.scene.routine.the_first_hour.thanked` — e.g. "That is a decent thing to hear at this hour. It usually goes better when somebody has said it."


```text
POOL   dialogue key: dialogue.conversations.scene.routine.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.routine.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.routine.followup   [28 chars]
    en  Still curious about the day?
    >>  ............................................
    pt  Ainda curioso sobre o dia?
    >>  ............................................
```


### Button `leave` — "Back to it, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:routine.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.routine.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.followup.leave   [17 chars]
    en  Back to it, then.
    >>  ............................................
    pt  De volta ao trabalho, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.leaving
WHO    VILLAGER — what the player reads after pressing "Back to it, then."
       spoken on: conversations.scene.routine.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.scene.leaving`: the villager accepts. Subject `routine.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.routine.after_the_work.respond / leave; conversations.scene.routine.the_first_hour.respond / leave; conversations.topic.routine.more.respond / leave; conversations.topic.routine.open.respond / leave
```

> Written out in full under **`conversations.scene.routine.after_the_work.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.routine.the_first_hour.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `routine`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.routine.the_first_hour` — e.g. "You have caught the good hour. Everything I decide before the sun is properly up turns out better."


```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.routine.the_first_hour.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.routine.the_first_hour.respond   [16 chars]
    en  This hour of it.
    >>  ............................................
    pt  Esta hora dele.
    >>  ............................................
```


### Button `ask_what_is_planned` — "What's the plan for today?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `routine.the_first_hour.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.the_first_hour.ask_what_is_planned` — accepted phrasings: "whats the plan for today"; "what is the plan for today"; "what are you doing today"
  - the message must contain one of: `plan`, `today`
  - scored words: `plan`(1.8), `today`(1.8), `whats`(0.8), `doing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour.respond.ask_what_is_planned
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.the_first_hour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.the_first_hour.respond.ask_what_is_planned   [26 chars]
    en  What's the plan for today?
    >>  ............................................
    pt  Qual é o plano para hoje?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `routine.first_hour`)_
- Does: session `turn`
- Then opens: `conversations.scene.routine.followup`
- …where the player's next choices will be: "Back to it, then."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour.listed
WHO    VILLAGER — what the player reads after pressing "What's the plan for today?"
       spoken on: conversations.scene.routine.the_first_hour.respond, button `ask_what_is_planned`
       leaves the player on: conversations.scene.routine.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.the_first_hour.open.listed`: the villager explains. Subject `routine.first_hour`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.routine.the_first_hour.listed/1   [96 chars]
    en  Three things I promised and one I want to. I shall get through the three and resent it politely.
    >>  ............................................
    pt  Três coisas que prometi e uma que eu quero. Vou dar conta das três e me irritar educadamente.
    >>  ............................................
  dialogue.conversations.scene.routine.the_first_hour.listed/2   [67 chars]
    en  Nothing clever. The same order as yesterday, which is why it works.
    >>  ............................................
    pt  Nada esperto. A mesma ordem de ontem, e é por isso que funciona.
    >>  ............................................
  dialogue.conversations.scene.routine.the_first_hour.listed/3   [100 chars]
    en  One awkward errand at the far end of it, and the whole morning is me not thinking about that errand.
    >>  ............................................
    pt  Uma tarefa incômoda lá no fim, e a manhã inteira é eu evitando pensar nessa tarefa.
    >>  ............................................
```


### Button `wish_them_a_good_one` — "May it go easily."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `routine.the_first_hour.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.the_first_hour.wish_them_a_good_one` — accepted phrasings: "may it go easily"; "may it go easily"; "hope the day goes well for you"
  - the message must contain one of: `easily`, `hope`
  - scored words: `easily`(1.8), `hope`(1.8), `may`(0.8), `day`(0.8), `goes`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour.respond.wish_them_a_good_one
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.the_first_hour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.the_first_hour.respond.wish_them_a_good_one   [17 chars]
    en  May it go easily.
    >>  ............................................
    pt  Que corra tranquilo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.routine.first_hour.wished`, budget `quick`, replay policy `once`
- Does: disposition — warmth +2  _(recorded under topic `routine.first_hour`)_
- Does: session `turn`
- Then opens: `conversations.scene.routine.followup`
- …where the player's next choices will be: "Back to it, then."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour.thanked
WHO    VILLAGER — what the player reads after pressing "May it go easily."
       spoken on: conversations.scene.routine.the_first_hour.respond, button `wish_them_a_good_one`
       leaves the player on: conversations.scene.routine.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.the_first_hour.open.thanked`: the villager accepts. Subject `routine.first_hour`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.routine.the_first_hour.thanked/1   [94 chars]
    en  That is a decent thing to hear at this hour. It usually goes better when somebody has said it.
    >>  ............................................
    pt  É uma coisa boa de se ouvir a esta hora. Costuma correr melhor quando alguém diz isso.
    >>  ............................................
  dialogue.conversations.scene.routine.the_first_hour.thanked/2   [62 chars]
    en  Thank you. I shall hold you to it about four in the afternoon.
    >>  ............................................
    pt  Obrigada. Vou cobrar de você lá pelas quatro da tarde.
    >>  ............................................
  dialogue.conversations.scene.routine.the_first_hour.thanked/3   [76 chars]
    en  Kind of you. On most mornings the first voice I hear is my own, complaining.
    >>  ............................................
    pt  Gentileza sua. Na maioria das manhãs a primeira voz que eu ouço é a minha, reclamando.
    >>  ............................................
```


### Button `leave` — "Sounds a full day."

*stance family `exit` · tone `plain` · answers the beat(s) `routine.the_first_hour.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.routine.the_first_hour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.routine.the_first_hour.respond.leave   [18 chars]
    en  Sounds a full day.
    >>  ............................................
    pt  Parece um dia cheio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.leaving
WHO    VILLAGER — what the player reads after pressing "Sounds a full day."
       spoken on: conversations.scene.routine.the_first_hour.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.scene.leaving`: the villager accepts. Subject `routine.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.routine.after_the_work.respond / leave; conversations.scene.routine.followup / leave; conversations.topic.routine.more.respond / leave; conversations.topic.routine.open.respond / leave
```

> Written out in full under **`conversations.scene.routine.after_the_work.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.routine.more.respond`

**Reached from 2 route(s):** `conversations.topic.routine.open.respond` / `ask_the_hardest_part`; `conversations.topic.routine.open.respond` / `say_it_sounds_steady`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.routine.open.agreed` — e.g. "It is. I spent some years without one and I have stopped calling routine a dull word."
- `conversations.routine.open.named` — e.g. "The starting. Once I am moving the day carries me, and getting moving takes about an hour of arguing."


```text
POOL   dialogue key: dialogue.conversations.topic.routine.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.routine.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.routine.more.respond   [19 chars]
    en  The part you guard.
    >>  ............................................
    pt  A parte que você protege.
    >>  ............................................
```


### Button `ask_which_quarter` — "Which piece is yours?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `routine.open.named`, `routine.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.more.ask_which_quarter` — accepted phrasings: "which piece is yours"; "which piece is yours"; "what part do you protect"
  - the message must contain one of: `piece`, `protect`
  - scored words: `piece`(1.8), `protect`(1.8), `which`(0.8), `yours`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.routine.more.respond.ask_which_quarter
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.more.respond.ask_which_quarter   [21 chars]
    en  Which piece is yours?
    >>  ............................................
    pt  Qual pedaço é seu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.routine.asked_after`, budget `quick`, replay policy `once`
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `routine.the_part_i_guard`)_
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.routine.more.told
WHO    VILLAGER — what the player reads after pressing "Which piece is yours?"
       spoken on: conversations.topic.routine.more.respond, button `ask_which_quarter`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.more.told`: the villager explains. Subject `routine.the_part_i_guard`, polarity `positive`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.more.told/1   [83 chars]
    en  The first light, before anybody is up. I have not missed it four times in a decade.
    >>  ............................................
    pt  A primeira luz, antes de qualquer um acordar. Não perdi isso quatro vezes numa década.
    >>  ............................................
  dialogue.conversations.routine.more.told/2   [99 chars]
    en  The walk back. It is nine minutes long and it is where I decide what sort of mood the evening gets.
    >>  ............................................
    pt  A caminhada de volta. Dura nove minutos e é onde eu decido o humor que a noite vai ter.
    >>  ............................................
  dialogue.conversations.routine.more.told/3   [90 chars]
    en  The bit right after the work and right before the eating, when nothing is owed to anybody.
    >>  ............................................
    pt  O trecho logo depois do trabalho e logo antes de comer, quando nada é devido a ninguém.
    >>  ............................................
```


### Button `say_keep_it` — "Hold on to that piece."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `routine.open.named`, `routine.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.more.say_keep_it` — accepted phrasings: "hold on to that piece"; "hold on to that piece"; "keep that part for yourself"
  - the message must contain one of: `hold`, `keep`
  - scored words: `hold`(1.8), `keep`(1.8), `yourself`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.routine.more.respond.say_keep_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.more.respond.say_keep_it   [22 chars]
    en  Hold on to that piece.
    >>  ............................................
    pt  Segure firme esse pedaço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +1  _(recorded under topic `routine.the_part_i_guard`)_
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.routine.more.acknowledged
WHO    VILLAGER — what the player reads after pressing "Hold on to that piece."
       spoken on: conversations.topic.routine.more.respond, button `say_keep_it`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.more.acknowledged`: the villager accepts. Subject `routine.the_part_i_guard`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.more.acknowledged/1   [68 chars]
    en  I intend to. The years I lost it were the years I was worst company.
    >>  ............................................
    pt  Pretendo. Os anos em que eu perdi isso foram os anos em que fui a pior companhia.
    >>  ............................................
  dialogue.conversations.routine.more.acknowledged/2   [82 chars]
    en  Thank you. Nearly everybody hears that and starts explaining why I could spare it.
    >>  ............................................
    pt  Obrigada. Quase todo mundo ouve isso e começa a explicar por que eu poderia abrir mão.
    >>  ............................................
  dialogue.conversations.routine.more.acknowledged/3   [76 chars]
    en  I shall. It is the cheapest thing I own and it holds up the rest of the day.
    >>  ............................................
    pt  Vou segurar. É a coisa mais barata que eu tenho e sustenta o resto do dia.
    >>  ............................................
```


### Button `cut_it_off` — "A quarter hour is a luxury."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `routine.open.named`, `routine.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.more.cut_it_off` — accepted phrasings: "a quarter hour is a luxury"; "a quarter hour is a luxury"; "that sounds like a luxury"
  - the message must contain one of: `luxury`
  - scored words: `luxury`(1.8), `quarter`(0.8), `hour`(0.8), `sounds`(0.8), `like`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.routine.more.respond.cut_it_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.more.respond.cut_it_off   [27 chars]
    en  A quarter hour is a luxury.
    >>  ............................................
    pt  Um quarto de hora é luxo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.routine.cut_short`, budget `quick`, replay policy `once`
- Does: disposition — warmth -2, tension +1  _(recorded under topic `routine.the_part_i_guard`)_
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.routine.more.closed
WHO    VILLAGER — what the player reads after pressing "A quarter hour is a luxury."
       spoken on: conversations.topic.routine.more.respond, button `cut_it_off`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.more.closed`: the villager deflects. Subject `routine.the_part_i_guard`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.more.closed/1   [96 chars]
    en  It is the one luxury I have, and it is fifteen minutes, so I am fairly comfortable defending it.
    >>  ............................................
    pt  É o único luxo que eu tenho, e são quinze minutos, então me sinto bem confortável defendendo.
    >>  ............................................
  dialogue.conversations.routine.more.closed/2   [95 chars]
    en  Perhaps. I have tried the version without it and the version without it is worse for everybody.
    >>  ............................................
    pt  Talvez. Já testei a versão sem isso e a versão sem isso é pior para todo mundo.
    >>  ............................................
  dialogue.conversations.routine.more.closed/3   [59 chars]
    en  Right. We shall talk about something with more in it, then.
    >>  ............................................
    pt  Certo. Falamos de algo com mais substância, então.
    >>  ............................................
```


### Button `leave` — "Sounds a full day."

*stance family `exit` · tone `plain` · answers the beat(s) `routine.open.named`, `routine.open.agreed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.routine.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.more.respond.leave   [18 chars]
    en  Sounds a full day.
    >>  ............................................
    pt  Parece um dia cheio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.leaving
WHO    VILLAGER — what the player reads after pressing "Sounds a full day."
       spoken on: conversations.topic.routine.more.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.scene.leaving`: the villager accepts. Subject `routine.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.routine.after_the_work.respond / leave; conversations.scene.routine.followup / leave; conversations.scene.routine.the_first_hour.respond / leave; conversations.topic.routine.open.respond / leave
```

> Written out in full under **`conversations.scene.routine.after_the_work.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.routine.open.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `routine`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.routine.open` — e.g. "Three parts. The part that has to happen at dawn, the part that can slide, and the part I keep putting off."


```text
POOL   dialogue key: dialogue.conversations.topic.routine.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.routine.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.routine.open.respond   [17 chars]
    en  How the day runs.
    >>  ............................................
    pt  Como o dia corre.
    >>  ............................................
```


### Button `ask_the_hardest_part` — "Which part is hardest?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `routine.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.open.ask_the_hardest_part` — accepted phrasings: "which part is hardest"; "which part is hardest"; "what is the hardest part of it"
  - the message must contain one of: `hardest`, `part`
  - scored words: `hardest`(1.8), `part`(1.8), `which`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.routine.open.respond.ask_the_hardest_part
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.open.respond.ask_the_hardest_part   [22 chars]
    en  Which part is hardest?
    >>  ............................................
    pt  Qual parte é a mais difícil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `routine.shape_of_the_day`)_
- Does: session `turn`
- Then opens: `conversations.topic.routine.more.respond`
- …where the player's next choices will be: "Which piece is yours?" | "Hold on to that piece." | "A quarter hour is a luxury." | "Sounds a full day."

```text
POOL   dialogue key: dialogue.conversations.routine.open.named
WHO    VILLAGER — what the player reads after pressing "Which part is hardest?"
       spoken on: conversations.topic.routine.open.respond, button `ask_the_hardest_part`
       leaves the player on: conversations.topic.routine.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.open.named`: the villager explains. Subject `routine.shape_of_the_day`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.open.named/1   [101 chars]
    en  The starting. Once I am moving the day carries me, and getting moving takes about an hour of arguing.
    >>  ............................................
    pt  Começar. Assim que estou em movimento o dia me carrega, e entrar em movimento leva uma hora de discussão.
    >>  ............................................
  dialogue.conversations.routine.open.named/2   [103 chars]
    en  The middle, where it is too late to change the plan and too early to see whether the plan was any good.
    >>  ............................................
    pt  O meio, quando já é tarde para mudar o plano e cedo demais para ver se o plano prestava.
    >>  ............................................
  dialogue.conversations.routine.open.named/3   [97 chars]
    en  The tidying. It is fifteen minutes and I have been losing that fifteen-minute argument for years.
    >>  ............................................
    pt  A arrumação. São quinze minutos e eu venho perdendo essa discussão de quinze minutos há anos.
    >>  ............................................
```


### Button `say_it_sounds_steady` — "A steady day is worth having."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `routine.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.open.say_it_sounds_steady` — accepted phrasings: "a steady day is worth having"; "a steady day is worth having"; "steady days are the good ones"
  - the message must contain one of: `steady`
  - scored words: `steady`(1.8), `day`(0.8), `worth`(0.8), `having`(0.8), `days`(0.8), `good`(0.8), `ones`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.routine.open.respond.say_it_sounds_steady
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.open.respond.say_it_sounds_steady   [29 chars]
    en  A steady day is worth having.
    >>  ............................................
    pt  Um dia estável vale muito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `routine.shape_of_the_day`)_
- Does: session `turn`
- Then opens: `conversations.topic.routine.more.respond`
- …where the player's next choices will be: "Which piece is yours?" | "Hold on to that piece." | "A quarter hour is a luxury." | "Sounds a full day."

```text
POOL   dialogue key: dialogue.conversations.routine.open.agreed
WHO    VILLAGER — what the player reads after pressing "A steady day is worth having."
       spoken on: conversations.topic.routine.open.respond, button `say_it_sounds_steady`
       leaves the player on: conversations.topic.routine.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.open.agreed`: the villager accepts. Subject `routine.shape_of_the_day`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.open.agreed/1   [85 chars]
    en  It is. I spent some years without one and I have stopped calling routine a dull word.
    >>  ............................................
    pt  Vale. Passei alguns anos sem um e parei de chamar rotina de palavra chata.
    >>  ............................................
  dialogue.conversations.routine.open.agreed/2   [97 chars]
    en  That is the argument I make to anybody who calls it dull, and you got there without me making it.
    >>  ............................................
    pt  É o argumento que eu faço a quem chama isso de chato, e você chegou lá sem eu precisar fazer.
    >>  ............................................
  dialogue.conversations.routine.open.agreed/3   [71 chars]
    en  Nearly everybody wants an interesting day until they get four in a row.
    >>  ............................................
    pt  Quase todo mundo quer um dia interessante até ter quatro seguidos.
    >>  ............................................
```


### Button `wave_it_away` — "Sounds the same as everyone's."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `routine.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.routine.open.wave_it_away` — accepted phrasings: "sounds the same as everyones"; "sounds the same as everyone elses"; "that sounds much the same"
  - the message must contain one of: `same`, `sounds`
  - scored words: `same`(1.8), `sounds`(1.8), `everyones`(0.8), `everyone`(0.8), `elses`(0.8), `much`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.routine.open.respond.wave_it_away
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.open.respond.wave_it_away   [30 chars]
    en  Sounds the same as everyone's.
    >>  ............................................
    pt  Parece igual ao de todo mundo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.routine.dismissed`, budget `quick`, replay policy `once`
- Does: disposition — warmth -2, tension +1  _(recorded under topic `routine.shape_of_the_day`)_
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.routine.open.closed
WHO    VILLAGER — what the player reads after pressing "Sounds the same as everyone's."
       spoken on: conversations.topic.routine.open.respond, button `wave_it_away`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.open.closed`: the villager qualifys. Subject `routine.shape_of_the_day`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.open.closed/1   [104 chars]
    en  Very likely. The differences are all in the small hours and those take longer to describe than you want.
    >>  ............................................
    pt  Muito provável. As diferenças estão todas nas horas pequenas e demoram mais para descrever do que você quer.
    >>  ............................................
  dialogue.conversations.routine.open.closed/2   [53 chars]
    en  It is, mostly. That is rather the point of a village.
    >>  ............................................
    pt  É, quase todo. Esse é meio que o objetivo de uma vila.
    >>  ............................................
  dialogue.conversations.routine.open.closed/3   [46 chars]
    en  Right. I shall spare you the rest of it, then.
    >>  ............................................
    pt  Certo. Poupo você do resto, então.
    >>  ............................................
```


### Button `leave` — "Sounds a full day."

*stance family `exit` · tone `plain` · answers the beat(s) `routine.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.routine.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.routine.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.routine.open.respond.leave   [18 chars]
    en  Sounds a full day.
    >>  ............................................
    pt  Parece um dia cheio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.leaving
WHO    VILLAGER — what the player reads after pressing "Sounds a full day."
       spoken on: conversations.topic.routine.open.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.scene.leaving`: the villager accepts. Subject `routine.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.routine.after_the_work.respond / leave; conversations.scene.routine.followup / leave; conversations.scene.routine.the_first_hour.respond / leave; conversations.topic.routine.more.respond / leave
```

> Written out in full under **`conversations.scene.routine.after_the_work.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

