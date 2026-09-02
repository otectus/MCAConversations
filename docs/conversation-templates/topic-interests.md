# Topic: interests

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `interests` |
| Opened from | question `conversations.cat.personal`, button `interests` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | teen, adult |
| Stance families it must offer | `curiosity`, `encouragement`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.interests
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.interests   [18 chars]
    en  What do you enjoy?
    >>  ............................................
    pt  Do que você gosta?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.interests.followup`](#conversations-scene-interests-followup)
- [`conversations.scene.interests.the_hour_for_it.respond`](#conversations-scene-interests-the-hour-for-it-respond)
- [`conversations.scene.interests.the_one_who_shares_it.respond`](#conversations-scene-interests-the-one-who-shares-it-respond)
- [`conversations.topic.interests.more.respond`](#conversations-topic-interests-more-respond)
- [`conversations.topic.interests.open.respond`](#conversations-topic-interests-open-respond)

---

## `conversations.scene.interests.followup`

**Reached from 4 route(s):** `conversations.scene.interests.the_hour_for_it.respond` / `ask_the_plan`; `conversations.scene.interests.the_hour_for_it.respond` / `wish_them_the_hour`; `conversations.scene.interests.the_one_who_shares_it.respond` / `ask_how_they_found_out`; `conversations.scene.interests.the_one_who_shares_it.respond` / `say_that_is_lucky`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.interests.the_hour_for_it.answered` — e.g. "An hour of the thing I am bad at, on purpose, where nobody can hear me being bad at it."
- `conversations.scene.interests.the_hour_for_it.thanked` — e.g. "I intend to, and I shall be back to being useful by morning, which is the bargain I have made with this village."
- `conversations.scene.interests.the_one_who_shares_it.delighted` — e.g. "It is, and I have started wondering how many more of us there are being quiet about it in separate houses."
- `conversations.scene.interests.the_one_who_shares_it.told` — e.g. "They left something out on a windowsill and I recognised what it was from four doors down."


```text
POOL   dialogue key: dialogue.conversations.scene.interests.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.interests.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.interests.followup   [13 chars]
    en  More of that?
    >>  ............................................
    pt  Mais disso?
    >>  ............................................
```


### Button `leave` — "Another time, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:interests.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.interests.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.followup.leave   [19 chars]
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
POOL   dialogue key: dialogue.conversations.scene.interests.leaving
WHO    VILLAGER — what the player reads after pressing "Another time, then."
       spoken on: conversations.scene.interests.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.scene.leaving`: the villager accepts. Subject `interests.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.interests.the_hour_for_it.respond / leave; conversations.scene.interests.the_one_who_shares_it.respond / leave; conversations.topic.interests.more.respond / leave; conversations.topic.interests.open.respond / leave
```

```text
  dialogue.conversations.scene.interests.leaving/1   [35 chars]
    en  Anyway. It keeps me out of trouble.
    >>  ............................................
    pt  Enfim. Me mantém fora de encrenca.
    >>  ............................................
  dialogue.conversations.scene.interests.leaving/2   [25 chars]
    en  Right. Enough about that.
    >>  ............................................
    pt  Certo. Chega disso.
    >>  ............................................
  dialogue.conversations.scene.interests.leaving/3   [28 chars]
    en  You will be sorry you asked.
    >>  ............................................
    pt  Você vai se arrepender de ter perguntado.
    >>  ............................................
```

---


## `conversations.scene.interests.the_hour_for_it.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `interests`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.interests.the_hour_for_it` — e.g. "I have an hour after this and I know exactly what I am doing with it, which does not happen most days."


```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.interests.the_hour_for_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it.respond   [24 chars]
    en  What you're up to later.
    >>  ............................................
    pt  O que você vai fazer depois.
    >>  ............................................
```


### Button `ask_the_plan` — "What are the plans?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `interests.the_hour_for_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.the_hour_for_it.ask_the_plan` — accepted phrasings: "what are the plans"; "what are the plans"; "what will you do with the hour"
  - the message must contain one of: `plans`, `hour`
  - scored words: `plans`(1.8), `hour`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it.respond.ask_the_plan
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.the_hour_for_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it.respond.ask_the_plan   [19 chars]
    en  What are the plans?
    >>  ............................................
    pt  Quais são os planos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `interests.tonight`)_
- Does: session `turn`
- Then opens: `conversations.scene.interests.followup`
- …where the player's next choices will be: "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it.answered
WHO    VILLAGER — what the player reads after pressing "What are the plans?"
       spoken on: conversations.scene.interests.the_hour_for_it.respond, button `ask_the_plan`
       leaves the player on: conversations.scene.interests.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.the_hour_for_it.open.answered`: the villager explains. Subject `interests.tonight`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it.answered/1   [87 chars]
    en  An hour of the thing I am bad at, on purpose, where nobody can hear me being bad at it.
    >>  ............................................
    pt  Uma hora da coisa em que eu sou ruim, de propósito, onde ninguém pode me ouvir sendo ruim.
    >>  ............................................
  dialogue.conversations.scene.interests.the_hour_for_it.answered/2   [110 chars]
    en  Sitting somewhere specific and doing something nobody would call an activity. It is the best hour of the week.
    >>  ............................................
    pt  Sentar num lugar específico e fazer algo que ninguém chamaria de atividade. É a melhor hora da semana.
    >>  ............................................
  dialogue.conversations.scene.interests.the_hour_for_it.answered/3   [124 chars]
    en  I am going to finish a thing I started in the spring. It will take twenty minutes and I have had six months of intending to.
    >>  ............................................
    pt  Vou terminar uma coisa que comecei na primavera. Leva vinte minutos e eu tive seis meses de intenção.
    >>  ............................................
```


### Button `wish_them_the_hour` — "Enjoy the hour."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `interests.the_hour_for_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.the_hour_for_it.wish_them_the_hour` — accepted phrasings: "enjoy the hour"; "enjoy the hour"; "make the most of it"
  - the message must contain one of: `enjoy`, `most`
  - scored words: `enjoy`(1.8), `most`(1.8), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it.respond.wish_them_the_hour
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.the_hour_for_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it.respond.wish_them_the_hour   [15 chars]
    en  Enjoy the hour.
    >>  ............................................
    pt  Aproveite a hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `interests.tonight`)_
- Does: session `turn`
- Then opens: `conversations.scene.interests.followup`
- …where the player's next choices will be: "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it.thanked
WHO    VILLAGER — what the player reads after pressing "Enjoy the hour."
       spoken on: conversations.scene.interests.the_hour_for_it.respond, button `wish_them_the_hour`
       leaves the player on: conversations.scene.interests.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.the_hour_for_it.open.thanked`: the villager accepts. Subject `interests.tonight`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it.thanked/1   [112 chars]
    en  I intend to, and I shall be back to being useful by morning, which is the bargain I have made with this village.
    >>  ............................................
    pt  Pretendo, e volto a ser útil pela manhã, que é o acordo que eu fiz com esta vila.
    >>  ............................................
  dialogue.conversations.scene.interests.the_hour_for_it.thanked/2   [97 chars]
    en  Thank you. Almost nobody says that. They ask what I am doing and then look slightly disappointed.
    >>  ............................................
    pt  Obrigada. Quase ninguém diz isso. Perguntam o que eu vou fazer e depois parecem levemente decepcionados.
    >>  ............................................
  dialogue.conversations.scene.interests.the_hour_for_it.thanked/3   [97 chars]
    en  One hour. It sounds like very little and it is the difference between a good week and a long one.
    >>  ............................................
    pt  Uma hora. Parece pouquíssimo e é a diferença entre uma boa semana e uma longa.
    >>  ............................................
```


### Button `leave` — "Good to know."

*stance family `exit` · tone `plain` · answers the beat(s) `interests.the_hour_for_it.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.the_hour_for_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it.respond.leave   [13 chars]
    en  Good to know.
    >>  ............................................
    pt  Bom saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.leaving
WHO    VILLAGER — what the player reads after pressing "Good to know."
       spoken on: conversations.scene.interests.the_hour_for_it.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.scene.leaving`: the villager accepts. Subject `interests.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.interests.followup / leave; conversations.scene.interests.the_one_who_shares_it.respond / leave; conversations.topic.interests.more.respond / leave; conversations.topic.interests.open.respond / leave
```

> Written out in full under **`conversations.scene.interests.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.interests.the_one_who_shares_it.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `interests`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.interests.the_one_who_shares_it` — e.g. "I found out last month that somebody else here does the same thing, and neither of us had said a word in six years."


```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.interests.the_one_who_shares_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it.respond   [23 chars]
    en  Somebody who shares it.
    >>  ............................................
    pt  Alguém que compartilha isso.
    >>  ............................................
```


### Button `ask_how_they_found_out` — "How did you find out?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `interests.the_one_who_shares_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.the_one_who_shares_it.ask_how_they_found_out` — accepted phrasings: "how did you find out"; "how did you find out"; "how did that come to light"
  - the message must contain one of: `find`, `light`
  - scored words: `find`(1.8), `light`(1.8), `out`(0.8), `come`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it.respond.ask_how_they_found_out
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.the_one_who_shares_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it.respond.ask_how_they_found_out   [21 chars]
    en  How did you find out?
    >>  ............................................
    pt  Como você descobriu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `interests.shared`)_
- Does: session `turn`
- Then opens: `conversations.scene.interests.followup`
- …where the player's next choices will be: "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it.told
WHO    VILLAGER — what the player reads after pressing "How did you find out?"
       spoken on: conversations.scene.interests.the_one_who_shares_it.respond, button `ask_how_they_found_out`
       leaves the player on: conversations.scene.interests.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.the_one_who_shares_it.open.told`: the villager reminisces. Subject `interests.shared`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it.told/1   [90 chars]
    en  They left something out on a windowsill and I recognised what it was from four doors down.
    >>  ............................................
    pt  Deixaram uma coisa no peitoril e eu reconheci o que era de quatro portas de distância.
    >>  ............................................
  dialogue.conversations.scene.interests.the_one_who_shares_it.told/2   [135 chars]
    en  A child told me, entirely by accident, while asking me an unrelated question. Children are the whole intelligence service of a village.
    >>  ............................................
    pt  Uma criança me contou, completamente por acaso, enquanto me perguntava outra coisa. Criança é o serviço de inteligência inteiro de uma vila.
    >>  ............................................
  dialogue.conversations.scene.interests.the_one_who_shares_it.told/3   [124 chars]
    en  We both stopped in the same place on the same evening to look at the same thing, and stood there being awkward for a minute.
    >>  ............................................
    pt  Nós duas paramos no mesmo lugar na mesma noite para olhar a mesma coisa, e ficamos ali sem jeito por um minuto.
    >>  ............................................
```


### Button `say_that_is_lucky` — "Two is lucky in a place this size."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `interests.the_one_who_shares_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.the_one_who_shares_it.say_that_is_lucky` — accepted phrasings: "two is lucky in a place this size"; "two is lucky in a place this size"; "that is a lucky find here"
  - the message must contain one of: `lucky`, `find`
  - scored words: `lucky`(1.8), `find`(1.8), `two`(0.8), `place`(0.8), `size`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it.respond.say_that_is_lucky
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.the_one_who_shares_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it.respond.say_that_is_lucky   [34 chars]
    en  Two is lucky in a place this size.
    >>  ............................................
    pt  Duas é sorte num lugar desse tamanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `interests.shared`)_
- Does: session `turn`
- Then opens: `conversations.scene.interests.followup`
- …where the player's next choices will be: "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it.delighted
WHO    VILLAGER — what the player reads after pressing "Two is lucky in a place this size."
       spoken on: conversations.scene.interests.the_one_who_shares_it.respond, button `say_that_is_lucky`
       leaves the player on: conversations.scene.interests.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.the_one_who_shares_it.open.delighted`: the villager celebrates. Subject `interests.shared`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it.delighted/1   [106 chars]
    en  It is, and I have started wondering how many more of us there are being quiet about it in separate houses.
    >>  ............................................
    pt  É, e comecei a me perguntar quantas mais de nós existem, caladas a respeito em casas separadas.
    >>  ............................................
  dialogue.conversations.scene.interests.the_one_who_shares_it.delighted/2   [115 chars]
    en  Six years of both of us assuming we were the only one. I keep thinking about the six years rather than the finding.
    >>  ............................................
    pt  Seis anos com nós duas achando que éramos a única. Eu fico pensando nos seis anos, não na descoberta.
    >>  ............................................
  dialogue.conversations.scene.interests.the_one_who_shares_it.delighted/3   [77 chars]
    en  Lucky and slightly absurd. We live four doors apart and it took a windowsill.
    >>  ............................................
    pt  Sorte e um pouco absurdo. Moramos a quatro portas e precisou de um peitoril.
    >>  ............................................
```


### Button `leave` — "Good to know."

*stance family `exit` · tone `plain` · answers the beat(s) `interests.the_one_who_shares_it.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.interests.the_one_who_shares_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it.respond.leave   [13 chars]
    en  Good to know.
    >>  ............................................
    pt  Bom saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.leaving
WHO    VILLAGER — what the player reads after pressing "Good to know."
       spoken on: conversations.scene.interests.the_one_who_shares_it.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.scene.leaving`: the villager accepts. Subject `interests.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.interests.followup / leave; conversations.scene.interests.the_hour_for_it.respond / leave; conversations.topic.interests.more.respond / leave; conversations.topic.interests.open.respond / leave
```

> Written out in full under **`conversations.scene.interests.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.interests.more.respond`

**Reached from 2 route(s):** `conversations.topic.interests.open.respond` / `ask_what_it_is`; `conversations.topic.interests.open.respond` / `say_it_sounds_good`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.interests.open.named` — e.g. "Watching where water goes. It sounds like nothing and I have four years of notes about this village's ditches."
- `conversations.interests.open.warmed` — e.g. "It is, and it took me until about thirty to stop apologising for it, which is longer than it should have."


```text
POOL   dialogue key: dialogue.conversations.topic.interests.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.interests.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.interests.more.respond   [15 chars]
    en  How it started.
    >>  ............................................
    pt  Como começou.
    >>  ............................................
```


### Button `ask_to_be_shown` — "Would you show me sometime?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `interests.open.named`, `interests.open.warmed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.more.ask_to_be_shown` — accepted phrasings: "would you show me sometime"; "would you show me sometime"; "i would like to be shown"
  - the message must contain one of: `show`, `shown`
  - scored words: `show`(1.8), `shown`(1.8), `sometime`(0.8), `like`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.interests.more.respond.ask_to_be_shown
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.more.respond.ask_to_be_shown   [27 chars]
    en  Would you show me sometime?
    >>  ............................................
    pt  Você me mostraria algum dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.interests.asked_to_see`, budget `standard`, replay policy `once`
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `interests.how_it_started`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.interests.more.pleased
WHO    VILLAGER — what the player reads after pressing "Would you show me sometime?"
       spoken on: conversations.topic.interests.more.respond, button `ask_to_be_shown`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.more.pleased`: the villager accepts. Subject `interests.how_it_started`, polarity `positive`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.more.pleased/1   [98 chars]
    en  Yes. Come on a dry evening and expect to be bored for the first ten minutes, because everybody is.
    >>  ............................................
    pt  Sim. Venha numa noite seca e espere ficar entediado nos dez primeiros minutos, porque todo mundo fica.
    >>  ............................................
  dialogue.conversations.interests.more.pleased/2   [113 chars]
    en  That question does not get asked. I have thought about how I would answer it and I still managed to be surprised.
    >>  ............................................
    pt  Ninguém tinha perguntado isso antes. Já pensei em como responderia e ainda assim me surpreendi.
    >>  ............................................
  dialogue.conversations.interests.more.pleased/3   [90 chars]
    en  You may, and I shall be insufferable about it, and you will have brought that on yourself.
    >>  ............................................
    pt  Pode, e eu vou ficar insuportável a respeito, e você terá causado isso a si mesmo.
    >>  ............................................
```


### Button `say_it_suits_them` — "That suits you."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `interests.open.named`, `interests.open.warmed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.more.say_it_suits_them` — accepted phrasings: "that suits you"; "that suits you"; "it fits you well"
  - the message must contain one of: `suits`, `fits`
  - scored words: `suits`(1.8), `fits`(1.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.interests.more.respond.say_it_suits_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.more.respond.say_it_suits_them   [15 chars]
    en  That suits you.
    >>  ............................................
    pt  Isso combina com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `interests.how_it_started`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.interests.more.acknowledged
WHO    VILLAGER — what the player reads after pressing "That suits you."
       spoken on: conversations.topic.interests.more.respond, button `say_it_suits_them`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.more.acknowledged`: the villager accepts. Subject `interests.how_it_started`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.more.acknowledged/1   [110 chars]
    en  It does, and being told so by somebody who has known me a while is worth more than the doing of it some weeks.
    >>  ............................................
    pt  Combina, e ouvir isso de quem me conhece já faz um tempo vale mais que a coisa em si em algumas semanas.
    >>  ............................................
  dialogue.conversations.interests.more.acknowledged/2   [95 chars]
    en  Thank you. That is a small sentence and I shall be thinking about it in the middle of Thursday.
    >>  ............................................
    pt  Obrigada. É uma frase pequena e eu vou ficar pensando nela no meio da quinta-feira.
    >>  ............................................
  dialogue.conversations.interests.more.acknowledged/3   [110 chars]
    en  I think it made me rather than the other way round, which is a strange thing to say about a hobby and is true.
    >>  ............................................
    pt  Acho que ela me fez, e não o contrário, o que é estranho de se dizer de um passatempo e é verdade.
    >>  ............................................
```


### Button `cut_it_short` — "That is more than I needed."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `interests.open.named`, `interests.open.warmed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.more.cut_it_short` — accepted phrasings: "that is more than i needed"; "i have heard plenty about it"; "that is more than i needed"
  - the message must contain one of: `plenty`, `needed`
  - scored words: `plenty`(1.8), `needed`(1.8), `more`(0.8), `than`(0.8), `heard`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.interests.more.respond.cut_it_short
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.more.respond.cut_it_short   [27 chars]
    en  That is more than I needed.
    >>  ............................................
    pt  Isso é mais do que eu precisava.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.interests.cut_short`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `interests.how_it_started`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.interests.more.closed
WHO    VILLAGER — what the player reads after pressing "That is more than I needed."
       spoken on: conversations.topic.interests.more.respond, button `cut_it_short`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.more.closed`: the villager deflects. Subject `interests.how_it_started`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.more.closed/1   [79 chars]
    en  Right. I did warn you, in fairness, and you asked anyway, and here we both are.
    >>  ............................................
    pt  Certo. Eu avisei, para ser justa, e você perguntou assim mesmo, e aqui estamos nós dois.
    >>  ............................................
  dialogue.conversations.interests.more.closed/2   [68 chars]
    en  Understood. I shall keep the rest of it to the people who ask twice.
    >>  ............................................
    pt  Entendido. Guardo o resto para quem pergunta duas vezes.
    >>  ............................................
  dialogue.conversations.interests.more.closed/3   [107 chars]
    en  That is fine. It is the sort of thing that only interests the person doing it and I have always known that.
    >>  ............................................
    pt  Tudo bem. É o tipo de coisa que só interessa a quem faz e eu sempre soube disso.
    >>  ............................................
```


### Button `leave` — "Good to know."

*stance family `exit` · tone `plain` · answers the beat(s) `interests.open.named`, `interests.open.warmed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.interests.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.more.respond.leave   [13 chars]
    en  Good to know.
    >>  ............................................
    pt  Bom saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.leaving
WHO    VILLAGER — what the player reads after pressing "Good to know."
       spoken on: conversations.topic.interests.more.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.scene.leaving`: the villager accepts. Subject `interests.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.interests.followup / leave; conversations.scene.interests.the_hour_for_it.respond / leave; conversations.scene.interests.the_one_who_shares_it.respond / leave; conversations.topic.interests.open.respond / leave
```

> Written out in full under **`conversations.scene.interests.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.interests.open.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `interests`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.interests.open` — e.g. "There is one thing I would do even if nobody paid me for anything, and it is not my trade."


```text
POOL   dialogue key: dialogue.conversations.topic.interests.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.interests.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.interests.open.respond   [15 chars]
    en  What you enjoy.
    >>  ............................................
    pt  Do que você gosta.
    >>  ............................................
```


### Button `ask_what_it_is` — "Tell me what the thing is."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `interests.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.open.ask_what_it_is` — accepted phrasings: "tell me what the thing is"; "what is the thing"; "tell me what it is"
  - the message must contain one of: `thing`, `tell`
  - scored words: `thing`(1.8), `tell`(1.8)

```text
POOL   dialogue key: dialogue.conversations.topic.interests.open.respond.ask_what_it_is
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.open.respond.ask_what_it_is   [26 chars]
    en  Tell me what the thing is.
    >>  ............................................
    pt  Me conte que coisa é essa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `interests.what_i_like`)_
- Does: session `turn`
- Then opens: `conversations.topic.interests.more.respond`
- …where the player's next choices will be: "Would you show me sometime?" | "That suits you." | "That is more than I needed." | "Good to know."

```text
POOL   dialogue key: dialogue.conversations.interests.open.named
WHO    VILLAGER — what the player reads after pressing "Tell me what the thing is."
       spoken on: conversations.topic.interests.open.respond, button `ask_what_it_is`
       leaves the player on: conversations.topic.interests.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.open.named`: the villager explains. Subject `interests.what_i_like`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.open.named/1   [110 chars]
    en  Watching where water goes. It sounds like nothing and I have four years of notes about this village's ditches.
    >>  ............................................
    pt  Observar para onde a água vai. Parece nada e eu tenho quatro anos de anotações sobre as valas desta vila.
    >>  ............................................
  dialogue.conversations.interests.open.named/2   [99 chars]
    en  Whistling. Properly, in parts. I can do two and I am working towards a third and it is going badly.
    >>  ............................................
    pt  Assobiar. Direito, em vozes. Consigo duas e estou trabalhando na terceira e vai mal.
    >>  ............................................
  dialogue.conversations.interests.open.named/3   [122 chars]
    en  Old walls. Who built them and in what order. Somebody laid every stone in this place and almost all of them are forgotten.
    >>  ............................................
    pt  Muros velhos. Quem construiu e em que ordem. Alguém assentou cada pedra deste lugar e quase todos foram esquecidos.
    >>  ............................................
```


### Button `say_it_sounds_good` — "It's good to have something of your own."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `interests.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.open.say_it_sounds_good` — accepted phrasings: "its good to have something of your own"; "it is good to have something of your own"; "everyone should have something of their own"
  - the message must contain one of: `own`, `something`
  - scored words: `own`(1.8), `something`(1.8), `its`(0.8), `good`(0.8), `everyone`(0.8), `should`(0.8), `their`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.interests.open.respond.say_it_sounds_good
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.open.respond.say_it_sounds_good   [40 chars]
    en  It's good to have something of your own.
    >>  ............................................
    pt  É bom ter algo só seu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `interests.what_i_like`)_
- Does: session `turn`
- Then opens: `conversations.topic.interests.more.respond`
- …where the player's next choices will be: "Would you show me sometime?" | "That suits you." | "That is more than I needed." | "Good to know."

```text
POOL   dialogue key: dialogue.conversations.interests.open.warmed
WHO    VILLAGER — what the player reads after pressing "It's good to have something of your own."
       spoken on: conversations.topic.interests.open.respond, button `say_it_sounds_good`
       leaves the player on: conversations.topic.interests.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.open.warmed`: the villager accepts. Subject `interests.what_i_like`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.open.warmed/1   [105 chars]
    en  It is, and it took me until about thirty to stop apologising for it, which is longer than it should have.
    >>  ............................................
    pt  É, e eu levei até uns trinta anos para parar de me desculpar por isso, o que é mais tempo do que devia.
    >>  ............................................
  dialogue.conversations.interests.open.warmed/2   [104 chars]
    en  That is a kinder answer than the usual one, which is somebody explaining why my thing is really a waste.
    >>  ............................................
    pt  É uma resposta mais gentil do que a de sempre, que é alguém explicando por que a minha coisa é desperdício.
    >>  ............................................
  dialogue.conversations.interests.open.warmed/3   [100 chars]
    en  Everybody here has one. Half of them will deny it and all of them go quiet about theirs on a Sunday.
    >>  ............................................
    pt  Todo mundo aqui tem uma. Metade vai negar e todos ficam quietos sobre a sua num domingo.
    >>  ............................................
```


### Button `brush_it_aside` — "Sounds like a waste of an evening."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `interests.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.interests.open.brush_it_aside` — accepted phrasings: "sounds like a waste of an evening"; "sounds like a waste of an evening"; "that seems a waste of time"
  - the message must contain one of: `waste`
  - scored words: `waste`(1.8), `sounds`(0.8), `like`(0.8), `evening`(0.8), `seems`(0.8), `time`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.interests.open.respond.brush_it_aside
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.open.respond.brush_it_aside   [34 chars]
    en  Sounds like a waste of an evening.
    >>  ............................................
    pt  Parece desperdício de uma noite.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.interests.dismissed`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `interests.what_i_like`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.interests.open.closed
WHO    VILLAGER — what the player reads after pressing "Sounds like a waste of an evening."
       spoken on: conversations.topic.interests.open.respond, button `brush_it_aside`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.open.closed`: the villager deflects. Subject `interests.what_i_like`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.open.closed/1   [94 chars]
    en  Probably. It is my evening, so I shall go on wasting it, and I will stop describing it to you.
    >>  ............................................
    pt  Provavelmente. É a minha noite, então vou continuar desperdiçando, e paro de descrever para você.
    >>  ............................................
  dialogue.conversations.interests.open.closed/2   [97 chars]
    en  Right. That is the answer I get about four times a year and I have stopped being surprised by it.
    >>  ............................................
    pt  Certo. É a resposta que eu recebo umas quatro vezes por ano e parei de me surpreender.
    >>  ............................................
  dialogue.conversations.interests.open.closed/3   [84 chars]
    en  Fair enough. Ask me about the weather instead and we shall both have a nicer minute.
    >>  ............................................
    pt  Tudo bem. Me pergunte do tempo e nós dois teremos um minuto melhor.
    >>  ............................................
```


### Button `leave` — "Good to know."

*stance family `exit` · tone `plain` · answers the beat(s) `interests.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.interests.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.interests.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.interests.open.respond.leave   [13 chars]
    en  Good to know.
    >>  ............................................
    pt  Bom saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.leaving
WHO    VILLAGER — what the player reads after pressing "Good to know."
       spoken on: conversations.topic.interests.open.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.scene.leaving`: the villager accepts. Subject `interests.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.interests.followup / leave; conversations.scene.interests.the_hour_for_it.respond / leave; conversations.scene.interests.the_one_who_shares_it.respond / leave; conversations.topic.interests.more.respond / leave
```

> Written out in full under **`conversations.scene.interests.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

