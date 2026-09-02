# Topic: future

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `future` |
| Opened from | question `conversations.us`, button `future` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.us` |
| Ages that can reach it | adult |
| Stance families it must offer | `encouragement`, `curiosity`, `respectful_disagreement`, `dismissal`, `exit` |
| Narrative arc | `us`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.us`, which is written out in **topic-us.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.us.future
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.us
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-us*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.us.future   [32 chars]
    en  What do you want for our future?
    >>  ............................................
    pt  O que você quer pro nosso futuro?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.future.followup`](#conversations-scene-future-followup)
- [`conversations.scene.future.next_year.respond`](#conversations-scene-future-next-year-respond)
- [`conversations.scene.future.the_decision_between_us.respond`](#conversations-scene-future-the-decision-between-us-respond)
- [`conversations.topic.future.followup`](#conversations-topic-future-followup)
- [`conversations.topic.future.home`](#conversations-topic-future-home)
- [`conversations.topic.future.respond`](#conversations-topic-future-respond)

---

## `conversations.scene.future.followup`

**Reached from 4 route(s):** `conversations.scene.future.next_year.respond` / `ask_the_one_thing`; `conversations.scene.future.next_year.respond` / `say_you_hope_to_be_here`; `conversations.scene.future.the_decision_between_us.respond` / `decide_it_together`; `conversations.scene.future.the_decision_between_us.respond` / `ask_where_they_lean`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.future.next_year.answered` — e.g. "Whether I take somebody on. It sounds like a small decision and it changes the shape of every day afterwards."
- `conversations.scene.future.next_year.pleased` — e.g. "Then that is one more thing in next year that I can count on, and I keep a short list of those."
- `conversations.scene.future.the_decision_between_us.answered` — e.g. "Toward yes, and about two thirds of the way, and the last third is not about the decision at all."
- `conversations.scene.future.the_decision_between_us.resolved` — e.g. "This week. Thursday evening, at the table, with nothing else on, because a decision made in a doorway gets unmade in one."


```text
POOL   dialogue key: dialogue.conversations.scene.future.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.future.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.future.followup   [23 chars]
    en  Anything else about it?
    >>  ............................................
    pt  Mais alguma coisa sobre isso?
    >>  ............................................
```


### Button `leave` — "We'll come back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:future.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.future.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.followup.leave   [22 chars]
    en  We'll come back to it.
    >>  ............................................
    pt  A gente volta nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.future.leaving
WHO    VILLAGER — what the player reads after pressing "We'll come back to it."
       spoken on: conversations.scene.future.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.scene.leaving`: the villager accepts. Subject `future.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.future.next_year.respond / leave; conversations.scene.future.the_decision_between_us.respond / leave
```

```text
  dialogue.conversations.scene.future.leaving/1   [26 chars]
    en  We shall see how it falls.
    >>  ............................................
    pt  Vamos ver como fica.
    >>  ............................................
  dialogue.conversations.scene.future.leaving/2   [22 chars]
    en  Right. Plenty of time.
    >>  ............................................
    pt  Certo. Tem tempo de sobra.
    >>  ............................................
  dialogue.conversations.scene.future.leaving/3   [31 chars]
    en  Ask me again when it is closer.
    >>  ............................................
    pt  Me pergunte quando estiver mais perto.
    >>  ............................................
```

---


## `conversations.scene.future.next_year.respond`

**Reached from 1 route(s):** `conversations.us` / `future`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.future.next_year` — e.g. "Much like this one, which I say as a plan rather than as resignation, and I mean it as a plan."


```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.future.next_year.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.future.next_year.respond   [10 chars]
    en  Next year.
    >>  ............................................
    pt  O ano que vem.
    >>  ............................................
```


### Button `ask_the_one_thing` — "What's the one thing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `future.next_year.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.future.next_year.ask_the_one_thing` — accepted phrasings: "whats the one thing"; "what is the one thing"; "which thing is undecided"
  - the message must contain one of: `thing`, `undecided`
  - scored words: `thing`(1.8), `undecided`(1.8), `whats`(0.8), `one`(0.8), `which`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year.respond.ask_the_one_thing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.next_year.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.next_year.respond.ask_the_one_thing   [21 chars]
    en  What's the one thing?
    >>  ............................................
    pt  Qual é essa coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `future.practical`)_
- Does: session `turn`
- Then opens: `conversations.scene.future.followup`
- …where the player's next choices will be: "We'll come back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year.answered
WHO    VILLAGER — what the player reads after pressing "What's the one thing?"
       spoken on: conversations.scene.future.next_year.respond, button `ask_the_one_thing`
       leaves the player on: conversations.scene.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.next_year.open.answered`: the villager explains. Subject `future.practical`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.future.next_year.answered/1   [109 chars]
    en  Whether I take somebody on. It sounds like a small decision and it changes the shape of every day afterwards.
    >>  ............................................
    pt  Se eu pego alguém para trabalhar comigo. Parece uma decisão pequena e muda o formato de todos os dias depois.
    >>  ............................................
  dialogue.conversations.scene.future.next_year.answered/2   [102 chars]
    en  Where I spend the winter. It has been the same answer for eleven years and this year it is a question.
    >>  ............................................
    pt  Onde eu passo o inverno. Foi a mesma resposta por onze anos e este ano é uma pergunta.
    >>  ............................................
  dialogue.conversations.scene.future.next_year.answered/3   [116 chars]
    en  I would rather not say until it is decided. Saying a thing while it is still open makes it harder to close honestly.
    >>  ............................................
    pt  Prefiro não dizer antes de decidir. Falar de uma coisa enquanto está aberta dificulta fechar com honestidade.
    >>  ............................................
```


### Button `say_you_hope_to_be_here` — "I hope to still be around for it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `future.next_year.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.future.next_year.say_you_hope_to_be_here` — accepted phrasings: "i hope to still be around for it"; "i hope to still be around for it"; "i expect to still be here"
  - the message must contain one of: `around`, `here`
  - scored words: `around`(1.8), `here`(1.8), `hope`(0.8), `still`(0.8), `expect`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year.respond.say_you_hope_to_be_here
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.next_year.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.next_year.respond.say_you_hope_to_be_here   [33 chars]
    en  I hope to still be around for it.
    >>  ............................................
    pt  Espero ainda estar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +1  _(recorded under topic `future.practical`)_
- Does: session `turn`
- Then opens: `conversations.scene.future.followup`
- …where the player's next choices will be: "We'll come back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year.pleased
WHO    VILLAGER — what the player reads after pressing "I hope to still be around for it."
       spoken on: conversations.scene.future.next_year.respond, button `say_you_hope_to_be_here`
       leaves the player on: conversations.scene.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.next_year.open.pleased`: the villager accepts. Subject `future.practical`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.future.next_year.pleased/1   [95 chars]
    en  Then that is one more thing in next year that I can count on, and I keep a short list of those.
    >>  ............................................
    pt  Então é mais uma coisa no ano que vem com que eu posso contar, e eu mantenho uma lista curta dessas.
    >>  ............................................
  dialogue.conversations.scene.future.next_year.pleased/2   [97 chars]
    en  Good. Half the people who say that are gone by the spring and the other half never say it at all.
    >>  ............................................
    pt  Ótimo. Metade de quem diz isso some até a primavera e a outra metade nunca diz.
    >>  ............................................
  dialogue.conversations.scene.future.next_year.pleased/3   [95 chars]
    en  I shall hold you to it in a mild way, which is the only way anybody should be held to anything.
    >>  ............................................
    pt  Vou cobrar de um jeito leve, que é o único jeito de se cobrar qualquer coisa de alguém.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `future.next_year.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.next_year.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.next_year.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.future.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.future.next_year.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.scene.leaving`: the villager accepts. Subject `future.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.future.followup / leave; conversations.scene.future.the_decision_between_us.respond / leave
```

> Written out in full under **`conversations.scene.future.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.future.the_decision_between_us.respond`

**Reached from 1 route(s):** `conversations.us` / `future`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.future.the_decision_between_us` — e.g. "There is a decision the two of us keep almost making, and I would rather make it badly than keep almost making it."


```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.future.the_decision_between_us.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.future.the_decision_between_us.respond   [28 chars]
    en  The thing we've not decided.
    >>  ............................................
    pt  A coisa que a gente não decidiu.
    >>  ............................................
```


### Button `decide_it_together` — "Then let's settle it this week."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `future.the_decision_between_us.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.future.the_decision_between_us.decide_it_together` — accepted phrasings: "then lets settle it this week"; "then let us settle it this week"; "we should decide it this week"
  - the message must contain one of: `settle`, `decide`, `week`
  - scored words: `settle`(1.8), `decide`(1.8), `week`(1.8), `lets`(0.8), `let`(0.8), `should`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us.respond.decide_it_together
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.the_decision_between_us.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.the_decision_between_us.respond.decide_it_together   [31 chars]
    en  Then let's settle it this week.
    >>  ............................................
    pt  Então vamos resolver esta semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.future.settled`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `future.shared`)_
- Does: session `turn`
- Then opens: `conversations.scene.future.followup`
- …where the player's next choices will be: "We'll come back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us.resolved
WHO    VILLAGER — what the player reads after pressing "Then let's settle it this week."
       spoken on: conversations.scene.future.the_decision_between_us.respond, button `decide_it_together`
       leaves the player on: conversations.scene.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.the_decision_between_us.open.resolved`: the villager accepts. Subject `future.shared`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `topic:future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.future.the_decision_between_us.resolved/1   [121 chars]
    en  This week. Thursday evening, at the table, with nothing else on, because a decision made in a doorway gets unmade in one.
    >>  ............................................
    pt  Esta semana. Quinta à noite, na mesa, sem mais nada acontecendo, porque decisão tomada num vão de porta é desfeita em outro.
    >>  ............................................
  dialogue.conversations.scene.future.the_decision_between_us.resolved/2   [125 chars]
    en  Right. And whichever way it falls we both hold it, and neither of us gets to say afterwards that it was the other one's idea.
    >>  ............................................
    pt  Certo. E para que lado cair, nós dois seguramos, e nenhum de nós pode dizer depois que a ideia foi do outro.
    >>  ............................................
  dialogue.conversations.scene.future.the_decision_between_us.resolved/3   [102 chars]
    en  Thank you. Four months of nearly, ended by somebody naming a day. That is usually all any of it needs.
    >>  ............................................
    pt  Obrigada. Quatro meses de quase, encerrados por alguém marcar um dia. Costuma ser tudo o que qualquer coisa precisa.
    >>  ............................................
```


### Button `ask_where_they_lean` — "Which way do you lean?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `future.the_decision_between_us.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.future.the_decision_between_us.ask_where_they_lean` — accepted phrasings: "which way do you lean"; "which way do you lean"; "where do you stand on it"
  - the message must contain one of: `lean`, `stand`
  - scored words: `lean`(1.8), `stand`(1.8), `which`(0.8), `way`(0.8), `where`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us.respond.ask_where_they_lean
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.the_decision_between_us.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.the_decision_between_us.respond.ask_where_they_lean   [22 chars]
    en  Which way do you lean?
    >>  ............................................
    pt  Para que lado você pende?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `future.shared`)_
- Does: session `turn`
- Then opens: `conversations.scene.future.followup`
- …where the player's next choices will be: "We'll come back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us.answered
WHO    VILLAGER — what the player reads after pressing "Which way do you lean?"
       spoken on: conversations.scene.future.the_decision_between_us.respond, button `ask_where_they_lean`
       leaves the player on: conversations.scene.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.the_decision_between_us.open.answered`: the villager explains. Subject `future.shared`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.future.the_decision_between_us.answered/1   [97 chars]
    en  Toward yes, and about two thirds of the way, and the last third is not about the decision at all.
    >>  ............................................
    pt  Para o sim, e uns dois terços do caminho, e o último terço não é sobre a decisão de jeito nenhum.
    >>  ............................................
  dialogue.conversations.scene.future.the_decision_between_us.answered/2   [100 chars]
    en  I will tell you on Thursday and not now, because saying it now makes your answer a response to mine.
    >>  ............................................
    pt  Eu te digo na quinta e não agora, porque dizer agora transforma a sua resposta numa reação à minha.
    >>  ............................................
  dialogue.conversations.scene.future.the_decision_between_us.answered/3   [126 chars]
    en  Toward waiting, which I know is the cowardly answer, and I would like to be argued out of it by somebody who is fair about it.
    >>  ............................................
    pt  Para esperar, e eu sei que é a resposta covarde, e eu gostaria de ser convencida do contrário por alguém que seja justo.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `future.the_decision_between_us.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.future.the_decision_between_us.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.future.the_decision_between_us.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.future.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.future.the_decision_between_us.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.scene.leaving`: the villager accepts. Subject `future.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.future.followup / leave; conversations.scene.future.next_year.respond / leave
```

> Written out in full under **`conversations.scene.future.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.future.followup`

**Reached from 3 route(s):** `conversations.topic.future.respond` / `align`; `conversations.topic.future.respond` / `ask_priorities`; `conversations.topic.future.respond` / `avoid`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.future.align` — e.g. "You do? ...Then that's the whole conversation, and a good one."
- `conversations.us.future.ask_priorities` — e.g. "What matters most. ...You. Then the rest, in some order."
- `conversations.us.future.avoid` — e.g. "...Not that far ahead. Alright. I'll stop bringing it up."


```text
POOL   dialogue key: dialogue.conversations.topic.future.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.future.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.future.followup   [26 chars]
    en  So what do we do about it?
    >>  ............................................
    pt  Então o que a gente faz?
    >>  ............................................
```


### Button `negotiate` — "We want different things. Let's work it out."

*stance family `candor` · tone `plain` · answers the beat(s) `us.future.align.to.future`, `us.future.ask_priorities.to.future`, `us.future.avoid.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.followup.negotiate` — accepted phrasings: "we want different things let us work it out"; "we can work out the difference"; "let us find a middle way"
  - the message must contain one of: `different`
  - scored words: `different`(1.2), `work`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.future.followup.negotiate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.followup.negotiate   [44 chars]
    en  We want different things. Let's work it out.
    >>  ............................................
    pt  Queremos coisas diferentes. Vamos resolver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.future.negotiate`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +5, trust +3  _(recorded under topic `us.future.negotiate`)_
- Does: arc `us` — advance to stage 2
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.future.negotiate
WHO    VILLAGER — what the player reads after pressing "We want different things. Let's work it out."
       spoken on: conversations.topic.future.followup, button `negotiate`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.negotiate.to.us`: the villager accepts. Subject `us`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.negotiate/1   [65 chars]
    en  ...Work it out. Aye. That's better than one of us just giving in.
    >>  ............................................
    pt  ...Resolver. É. É melhor que um de nós simplesmente ceder.
    >>  ............................................
  dialogue.conversations.us.future.negotiate/2   [65 chars]
    en  Different things and still here. That's most of a marriage, %1$s.
    >>  ............................................
    pt  Coisas diferentes e ainda juntos. É quase isso um casamento, %1$s.
    >>  ............................................
  dialogue.conversations.us.future.negotiate/3   [63 chars]
    en  Then we'll talk it through properly. Not tonight, but properly.
    >>  ............................................
    pt  Então vamos conversar direito. Hoje não, mas direito.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. Aye. That's better than one of us just giving in, %1$s, and I'd have given in.
    >>  ............................................
    pt  ...Resolver juntos. É. É melhor que um de nós ceder, %1$s, e eu teria cedido.
    >>  ............................................
  anxious.dialogue.conversations.us.future.negotiate/2
    en  Both of us. I'd braced for having to concede, and I'd have done it, and I'd have minded.
    >>  ............................................
    pt  Nós dois. Eu me preparei pra ter que ceder, e eu teria cedido, e teria me incomodado.
    >>  ............................................
  anxious.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. I didn't know that was on offer. Give me a moment with it.
    >>  ............................................
    pt  Nenhum se rende. Eu não sabia que isso estava disponível. Me dê um momento.
    >>  ............................................
  athletic.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us giving in, and it holds better over years.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós ceder, e se sustenta melhor ao longo dos anos.
    >>  ............................................
  athletic.dialogue.conversations.us.future.negotiate/2
    en  Both of us, slowly. There's no hurry; the answer will be the same in six months.
    >>  ............................................
    pt  Nós dois, devagar. Sem pressa; a resposta vai ser a mesma em seis meses.
    >>  ............................................
  athletic.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. It'll take longer that way and it'll still be standing later.
    >>  ............................................
    pt  Nenhum se rende. Vai demorar mais assim e vai continuar de pé depois.
    >>  ............................................
  confident.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us just giving in.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós simplesmente ceder.
    >>  ............................................
  confident.dialogue.conversations.us.future.negotiate/2
    en  Right. Both of us, then. I'd rather that than a winner.
    >>  ............................................
    pt  Certo. Nós dois, então. Prefiro isso a um vencedor.
    >>  ............................................
  confident.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Fine. That's harder and it lasts.
    >>  ............................................
    pt  Nenhum de nós se rende. Tudo bem. É mais difícil e dura.
    >>  ............................................
  crabby.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us just giving in.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós simplesmente ceder.
    >>  ............................................
  crabby.dialogue.conversations.us.future.negotiate/2
    en  Right. Both of us, then. I'd rather that than a winner.
    >>  ............................................
    pt  Certo. Nós dois, então. Prefiro isso a um vencedor.
    >>  ............................................
  crabby.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Fine. That's harder and it lasts.
    >>  ............................................
    pt  Nenhum de nós se rende. Tudo bem. É mais difícil e dura.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. Aye. That's better than one of us just giving in, %1$s.
    >>  ............................................
    pt  ...Resolver juntos. É. É melhor que um de nós ceder, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.negotiate/2
    en  Both of us, then. I'd rather spend a year on it than have you concede in an evening.
    >>  ............................................
    pt  Nós dois, então. Prefiro gastar um ano nisso a te ver ceder numa noite.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. That's the version I could live with for a long time.
    >>  ............................................
    pt  Nenhum se rende. É a versão com que eu conseguiria viver por muito tempo.
    >>  ............................................
  flirty.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. Aye. That's better than one of us just giving in, %1$s.
    >>  ............................................
    pt  ...Resolver juntos. É. É melhor que um de nós ceder, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.us.future.negotiate/2
    en  Both of us, then. I'd rather spend a year on it than have you concede in an evening.
    >>  ............................................
    pt  Nós dois, então. Prefiro gastar um ano nisso a te ver ceder numa noite.
    >>  ............................................
  flirty.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. That's the version I could live with for a long time.
    >>  ............................................
    pt  Nenhum se rende. É a versão com que eu conseguiria viver por muito tempo.
    >>  ............................................
  friendly.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. Aye. That's better than one of us just giving in, %1$s.
    >>  ............................................
    pt  ...Resolver juntos. É. É melhor que um de nós ceder, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.us.future.negotiate/2
    en  Both of us, then. I'd rather spend a year on it than have you concede in an evening.
    >>  ............................................
    pt  Nós dois, então. Prefiro gastar um ano nisso a te ver ceder numa noite.
    >>  ............................................
  friendly.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. That's the version I could live with for a long time.
    >>  ............................................
    pt  Nenhum se rende. É a versão com que eu conseguiria viver por muito tempo.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. Aye. That's better than one of us just giving in, %1$s, and I'd have given in.
    >>  ............................................
    pt  ...Resolver juntos. É. É melhor que um de nós ceder, %1$s, e eu teria cedido.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.negotiate/2
    en  Both of us. I'd braced for having to concede, and I'd have done it, and I'd have minded.
    >>  ............................................
    pt  Nós dois. Eu me preparei pra ter que ceder, e eu teria cedido, e teria me incomodado.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. I didn't know that was on offer. Give me a moment with it.
    >>  ............................................
    pt  Nenhum se rende. Eu não sabia que isso estava disponível. Me dê um momento.
    >>  ............................................
  greedy.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us just giving in.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós simplesmente ceder.
    >>  ............................................
  greedy.dialogue.conversations.us.future.negotiate/2
    en  Right. Both of us, then. I'd rather that than a winner.
    >>  ............................................
    pt  Certo. Nós dois, então. Prefiro isso a um vencedor.
    >>  ............................................
  greedy.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Fine. That's harder and it lasts.
    >>  ............................................
    pt  Nenhum de nós se rende. Tudo bem. É mais difícil e dura.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us just giving in.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós simplesmente ceder.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.negotiate/2
    en  Right. Both of us, then. I'd rather that than a winner.
    >>  ............................................
    pt  Certo. Nós dois, então. Prefiro isso a um vencedor.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Fine. That's harder and it lasts.
    >>  ............................................
    pt  Nenhum de nós se rende. Tudo bem. É mais difícil e dura.
    >>  ............................................
  introverted.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. That's better than one of us giving in.
    >>  ............................................
    pt  ...Resolver juntos. É melhor que um de nós ceder.
    >>  ............................................
  introverted.dialogue.conversations.us.future.negotiate/2
    en  Both of us, then.
    >>  ............................................
    pt  Nós dois, então.
    >>  ............................................
  introverted.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Right.
    >>  ............................................
    pt  Nenhum de nós se rende. Certo.
    >>  ............................................
  lazy.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us giving in, and it holds better over years.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós ceder, e se sustenta melhor ao longo dos anos.
    >>  ............................................
  lazy.dialogue.conversations.us.future.negotiate/2
    en  Both of us, slowly. There's no hurry; the answer will be the same in six months.
    >>  ............................................
    pt  Nós dois, devagar. Sem pressa; a resposta vai ser a mesma em seis meses.
    >>  ............................................
  lazy.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. It'll take longer that way and it'll still be standing later.
    >>  ............................................
    pt  Nenhum se rende. Vai demorar mais assim e vai continuar de pé depois.
    >>  ............................................
  odd.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. That's better than one of us giving in.
    >>  ............................................
    pt  ...Resolver juntos. É melhor que um de nós ceder.
    >>  ............................................
  odd.dialogue.conversations.us.future.negotiate/2
    en  Both of us, then.
    >>  ............................................
    pt  Nós dois, então.
    >>  ............................................
  odd.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Right.
    >>  ............................................
    pt  Nenhum de nós se rende. Certo.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us giving in, and it holds better over years.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós ceder, e se sustenta melhor ao longo dos anos.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.negotiate/2
    en  Both of us, slowly. There's no hurry; the answer will be the same in six months.
    >>  ............................................
    pt  Nós dois, devagar. Sem pressa; a resposta vai ser a mesma em seis meses.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. It'll take longer that way and it'll still be standing later.
    >>  ............................................
    pt  Nenhum se rende. Vai demorar mais assim e vai continuar de pé depois.
    >>  ............................................
  peppy.dialogue.conversations.us.future.negotiate/1
    en  Work it out! That's better than one of us just giving in, which is the usual method.
    >>  ............................................
    pt  Resolver juntos! É melhor que um de nós ceder, que é o método de sempre.
    >>  ............................................
  peppy.dialogue.conversations.us.future.negotiate/2
    en  Right — both of us. I'd rather that than a winner and a quiet resentment.
    >>  ............................................
    pt  Certo — nós dois. Prefiro isso a um vencedor e um ressentimento calado.
    >>  ............................................
  peppy.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders! Excellent. Harder, longer, and infinitely better.
    >>  ............................................
    pt  Nenhum de nós se rende! Excelente. Mais difícil, mais longo e infinitamente melhor.
    >>  ............................................
  playful.dialogue.conversations.us.future.negotiate/1
    en  Work it out! That's better than one of us just giving in, which is the usual method.
    >>  ............................................
    pt  Resolver juntos! É melhor que um de nós ceder, que é o método de sempre.
    >>  ............................................
  playful.dialogue.conversations.us.future.negotiate/2
    en  Right — both of us. I'd rather that than a winner and a quiet resentment.
    >>  ............................................
    pt  Certo — nós dois. Prefiro isso a um vencedor e um ressentimento calado.
    >>  ............................................
  playful.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders! Excellent. Harder, longer, and infinitely better.
    >>  ............................................
    pt  Nenhum de nós se rende! Excelente. Mais difícil, mais longo e infinitamente melhor.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.negotiate/1
    en  Work it out. That's better than one of us giving in, and it holds better over years.
    >>  ............................................
    pt  Resolver juntos. É melhor que um de nós ceder, e se sustenta melhor ao longo dos anos.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.negotiate/2
    en  Both of us, slowly. There's no hurry; the answer will be the same in six months.
    >>  ............................................
    pt  Nós dois, devagar. Sem pressa; a resposta vai ser a mesma em seis meses.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. It'll take longer that way and it'll still be standing later.
    >>  ............................................
    pt  Nenhum se rende. Vai demorar mais assim e vai continuar de pé depois.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. Aye. That's better than one of us just giving in, %1$s, and I'd have given in.
    >>  ............................................
    pt  ...Resolver juntos. É. É melhor que um de nós ceder, %1$s, e eu teria cedido.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.negotiate/2
    en  Both of us. I'd braced for having to concede, and I'd have done it, and I'd have minded.
    >>  ............................................
    pt  Nós dois. Eu me preparei pra ter que ceder, e eu teria cedido, e teria me incomodado.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.negotiate/3
    en  Neither surrenders. I didn't know that was on offer. Give me a moment with it.
    >>  ............................................
    pt  Nenhum se rende. Eu não sabia que isso estava disponível. Me dê um momento.
    >>  ............................................
  shy.dialogue.conversations.us.future.negotiate/1
    en  ...Work it out. That's better than one of us giving in.
    >>  ............................................
    pt  ...Resolver juntos. É melhor que um de nós ceder.
    >>  ............................................
  shy.dialogue.conversations.us.future.negotiate/2
    en  Both of us, then.
    >>  ............................................
    pt  Nós dois, então.
    >>  ............................................
  shy.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders. Right.
    >>  ............................................
    pt  Nenhum de nós se rende. Certo.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.negotiate/1
    en  Work it out! That's better than one of us just giving in, which is the usual method.
    >>  ............................................
    pt  Resolver juntos! É melhor que um de nós ceder, que é o método de sempre.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.negotiate/2
    en  Right — both of us. I'd rather that than a winner and a quiet resentment.
    >>  ............................................
    pt  Certo — nós dois. Prefiro isso a um vencedor e um ressentimento calado.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders! Excellent. Harder, longer, and infinitely better.
    >>  ............................................
    pt  Nenhum de nós se rende! Excelente. Mais difícil, mais longo e infinitamente melhor.
    >>  ............................................
  witty.dialogue.conversations.us.future.negotiate/1
    en  Work it out! That's better than one of us just giving in, which is the usual method.
    >>  ............................................
    pt  Resolver juntos! É melhor que um de nós ceder, que é o método de sempre.
    >>  ............................................
  witty.dialogue.conversations.us.future.negotiate/2
    en  Right — both of us. I'd rather that than a winner and a quiet resentment.
    >>  ............................................
    pt  Certo — nós dois. Prefiro isso a um vencedor e um ressentimento calado.
    >>  ............................................
  witty.dialogue.conversations.us.future.negotiate/3
    en  Neither of us surrenders! Excellent. Harder, longer, and infinitely better.
    >>  ............................................
    pt  Nenhum de nós se rende! Excelente. Mais difícil, mais longo e infinitamente melhor.
    >>  ............................................
```

</details>


### Button `promise_nothing` — "I won't promise what I can't give."

*stance family `candor` · tone `plain` · answers the beat(s) `us.future.align.to.future`, `us.future.ask_priorities.to.future`, `us.future.avoid.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.followup.promise_nothing` — accepted phrasings: "i will not promise what i cannot give"; "i will not make a promise i cannot keep"; "i would rather promise nothing"
  - the message must contain one of: `promise`
  - scored words: `give`(0.5), `promise`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.future.followup.promise_nothing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.followup.promise_nothing   [34 chars]
    en  I won't promise what I can't give.
    >>  ............................................
    pt  Não vou prometer o que não posso dar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.future.promise_nothing`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `us.future.promise_nothing`)_
- Does: arc `us` — advance to stage 1
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.future.promise_nothing
WHO    VILLAGER — what the player reads after pressing "I won't promise what I can't give."
       spoken on: conversations.topic.future.followup, button `promise_nothing`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.promise_nothing.to.us`: the villager accepts. Subject `us`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.promise_nothing/1   [57 chars]
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  dialogue.conversations.us.future.promise_nothing/2   [51 chars]
    en  No promises. Good. We've both seen what those cost.
    >>  ............................................
    pt  Sem promessas. Bom. Nós dois já vimos o que elas custam.
    >>  ............................................
  dialogue.conversations.us.future.promise_nothing/3   [42 chars]
    en  Honest. I'll take honest over comfortable.
    >>  ............................................
    pt  Honesto. Prefiro honesto a confortável.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent, %1$s. I've had one of those.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria, %1$s. Já tive uma dessas.
    >>  ............................................
  anxious.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. It stings and it's the right kind of sting.
    >>  ............................................
    pt  Certo. Nada devido. Dói e é o tipo certo de dor.
    >>  ............................................
  anxious.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. I'd have believed a promise and built on it. So — thank you.
    >>  ............................................
    pt  Sem promessa. Eu teria acreditado numa promessa e construído sobre ela. Então — obrigado.
    >>  ............................................
  athletic.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent in a year.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria em um ano.
    >>  ............................................
  athletic.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. Things left free tend to still be there later.
    >>  ............................................
    pt  Certo. Nada devido. O que é deixado livre costuma continuar lá depois.
    >>  ............................................
  athletic.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. We'll see where it stands in a season.
    >>  ............................................
    pt  Sem promessa. Bom. A gente vê como fica em uma estação.
    >>  ............................................
  confident.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  confident.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'll take the honest version.
    >>  ............................................
    pt  Certo. Nada devido. Eu fico com a versão honesta.
    >>  ............................................
  confident.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Promises made to be kind go bad quickly.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas feitas por gentileza estragam rápido.
    >>  ............................................
  crabby.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  crabby.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'll take the honest version.
    >>  ............................................
    pt  Certo. Nada devido. Eu fico com a versão honesta.
    >>  ............................................
  crabby.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Promises made to be kind go bad quickly.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas feitas por gentileza estragam rápido.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair, %1$s. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo, %1$s. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'd sooner have you here freely than here on a debt.
    >>  ............................................
    pt  Certo. Nada devido. Prefiro você aqui por vontade a você aqui por dívida.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Thank you for not making one you weren't sure of.
    >>  ............................................
    pt  Sem promessa. Obrigado por não fazer uma de que você não tinha certeza.
    >>  ............................................
  flirty.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair, %1$s. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo, %1$s. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  flirty.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'd sooner have you here freely than here on a debt.
    >>  ............................................
    pt  Certo. Nada devido. Prefiro você aqui por vontade a você aqui por dívida.
    >>  ............................................
  flirty.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Thank you for not making one you weren't sure of.
    >>  ............................................
    pt  Sem promessa. Obrigado por não fazer uma de que você não tinha certeza.
    >>  ............................................
  friendly.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair, %1$s. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo, %1$s. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  friendly.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'd sooner have you here freely than here on a debt.
    >>  ............................................
    pt  Certo. Nada devido. Prefiro você aqui por vontade a você aqui por dívida.
    >>  ............................................
  friendly.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Thank you for not making one you weren't sure of.
    >>  ............................................
    pt  Sem promessa. Obrigado por não fazer uma de que você não tinha certeza.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent, %1$s. I've had one of those.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria, %1$s. Já tive uma dessas.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. It stings and it's the right kind of sting.
    >>  ............................................
    pt  Certo. Nada devido. Dói e é o tipo certo de dor.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. I'd have believed a promise and built on it. So — thank you.
    >>  ............................................
    pt  Sem promessa. Eu teria acreditado numa promessa e construído sobre ela. Então — obrigado.
    >>  ............................................
  greedy.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  greedy.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'll take the honest version.
    >>  ............................................
    pt  Certo. Nada devido. Eu fico com a versão honesta.
    >>  ............................................
  greedy.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Promises made to be kind go bad quickly.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas feitas por gentileza estragam rápido.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. I'll take the honest version.
    >>  ............................................
    pt  Certo. Nada devido. Eu fico com a versão honesta.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Promises made to be kind go bad quickly.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas feitas por gentileza estragam rápido.
    >>  ............................................
  introverted.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  introverted.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed.
    >>  ............................................
    pt  Certo. Nada devido.
    >>  ............................................
  introverted.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good.
    >>  ............................................
    pt  Sem promessa. Bom.
    >>  ............................................
  lazy.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent in a year.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria em um ano.
    >>  ............................................
  lazy.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. Things left free tend to still be there later.
    >>  ............................................
    pt  Certo. Nada devido. O que é deixado livre costuma continuar lá depois.
    >>  ............................................
  lazy.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. We'll see where it stands in a season.
    >>  ............................................
    pt  Sem promessa. Bom. A gente vê como fica em uma estação.
    >>  ............................................
  odd.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  odd.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed.
    >>  ............................................
    pt  Certo. Nada devido.
    >>  ............................................
  odd.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good.
    >>  ............................................
    pt  Sem promessa. Bom.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent in a year.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria em um ano.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. Things left free tend to still be there later.
    >>  ............................................
    pt  Certo. Nada devido. O que é deixado livre costuma continuar lá depois.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. We'll see where it stands in a season.
    >>  ............................................
    pt  Sem promessa. Bom. A gente vê como fica em uma estação.
    >>  ............................................
  peppy.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair! I'd rather that than a promise you'd resent by spring.
    >>  ............................................
    pt  É justo! Prefiro isso a uma promessa que você lamentaria na primavera.
    >>  ............................................
  peppy.dialogue.conversations.us.future.promise_nothing/2
    en  Right — nothing owed. Marvellously uncomplicated.
    >>  ............................................
    pt  Certo — nada devido. Maravilhosamente descomplicado.
    >>  ............................................
  peppy.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Kind promises are the ones that rot fastest.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas gentis são as que apodrecem mais rápido.
    >>  ............................................
  playful.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair! I'd rather that than a promise you'd resent by spring.
    >>  ............................................
    pt  É justo! Prefiro isso a uma promessa que você lamentaria na primavera.
    >>  ............................................
  playful.dialogue.conversations.us.future.promise_nothing/2
    en  Right — nothing owed. Marvellously uncomplicated.
    >>  ............................................
    pt  Certo — nada devido. Maravilhosamente descomplicado.
    >>  ............................................
  playful.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Kind promises are the ones that rot fastest.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas gentis são as que apodrecem mais rápido.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent in a year.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria em um ano.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. Things left free tend to still be there later.
    >>  ............................................
    pt  Certo. Nada devido. O que é deixado livre costuma continuar lá depois.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. We'll see where it stands in a season.
    >>  ............................................
    pt  Sem promessa. Bom. A gente vê como fica em uma estação.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent, %1$s. I've had one of those.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria, %1$s. Já tive uma dessas.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed. It stings and it's the right kind of sting.
    >>  ............................................
    pt  Certo. Nada devido. Dói e é o tipo certo de dor.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. I'd have believed a promise and built on it. So — thank you.
    >>  ............................................
    pt  Sem promessa. Eu teria acreditado numa promessa e construído sobre ela. Então — obrigado.
    >>  ............................................
  shy.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair. I'd rather that than a promise you'd resent.
    >>  ............................................
    pt  É justo. Prefiro isso a uma promessa que você lamentaria.
    >>  ............................................
  shy.dialogue.conversations.us.future.promise_nothing/2
    en  Right. Nothing owed.
    >>  ............................................
    pt  Certo. Nada devido.
    >>  ............................................
  shy.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good.
    >>  ............................................
    pt  Sem promessa. Bom.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair! I'd rather that than a promise you'd resent by spring.
    >>  ............................................
    pt  É justo! Prefiro isso a uma promessa que você lamentaria na primavera.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.promise_nothing/2
    en  Right — nothing owed. Marvellously uncomplicated.
    >>  ............................................
    pt  Certo — nada devido. Maravilhosamente descomplicado.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Kind promises are the ones that rot fastest.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas gentis são as que apodrecem mais rápido.
    >>  ............................................
  witty.dialogue.conversations.us.future.promise_nothing/1
    en  That's fair! I'd rather that than a promise you'd resent by spring.
    >>  ............................................
    pt  É justo! Prefiro isso a uma promessa que você lamentaria na primavera.
    >>  ............................................
  witty.dialogue.conversations.us.future.promise_nothing/2
    en  Right — nothing owed. Marvellously uncomplicated.
    >>  ............................................
    pt  Certo — nada devido. Maravilhosamente descomplicado.
    >>  ............................................
  witty.dialogue.conversations.us.future.promise_nothing/3
    en  No promise. Good. Kind promises are the ones that rot fastest.
    >>  ............................................
    pt  Sem promessa. Bom. Promessas gentis são as que apodrecem mais rápido.
    >>  ............................................
```

</details>


### Button `reject` — "That's never going to happen."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `us.future.align.to.future`, `us.future.ask_priorities.to.future`, `us.future.avoid.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.followup.reject` — accepted phrasings: "that is never going to happen"; "that will never happen"; "it is not going to happen"
  - the message must contain one of: `never`
  - scored words: `happen`(0.8), `never`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.future.followup.reject
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.followup.reject   [29 chars]
    en  That's never going to happen.
    >>  ............................................
    pt  Isso nunca vai acontecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -3** — decision id `us.future.reject`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -6, tension +7, trust -3  _(recorded under topic `us.future.reject`)_
- Does: session `turn`
- Then opens: `conversations.topic.us.hurt.close`
- …where the player's next choices will be: "That came out harder than I meant it." | "You matter to me. That part is true." | "I'll give you the evening."

```text
POOL   dialogue key: dialogue.conversations.us.future.reject
WHO    VILLAGER — what the player reads after pressing "That's never going to happen."
       spoken on: conversations.topic.future.followup, button `reject`
       leaves the player on: conversations.topic.us.hurt.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.rejected`: the villager hurts. Subject `us.relationship`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `relationship:strained`, `player:refused_a_future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.future.reject/1   [42 chars]
    en  ...Never. Right. That's a hard word, %1$s.
    >>  ............................................
    pt  ...Nunca. Certo. É uma palavra dura, %1$s.
    >>  ............................................
  dialogue.conversations.us.future.reject/2   [46 chars]
    en  You could have said 'not yet'. You said never.
    >>  ............................................
    pt  Você podia ter dito 'ainda não'. Você disse nunca.
    >>  ............................................
  dialogue.conversations.us.future.reject/3   [50 chars]
    en  Then I'll stop hoping for it. Consider it stopped.
    >>  ............................................
    pt  Então vou parar de esperar por isso. Considere parado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.future.reject/1
    en  ...Never. That's a hard word, %1$s. Harder than I'd braced for.
    >>  ............................................
    pt  ...Nunca. É uma palavra dura, %1$s. Mais dura do que eu me preparei.
    >>  ............................................
  anxious.dialogue.conversations.us.future.reject/2
    en  Right. Yes. I'd rather you said it than let me keep hoping.
    >>  ............................................
    pt  Certo. Sim. Prefiro que você diga a me deixar continuar esperando.
    >>  ............................................
  anxious.dialogue.conversations.us.future.reject/3
    en  ...I'm glad I asked. I'll be less glad about it tonight.
    >>  ............................................
    pt  ...Estou contente de ter perguntado. Hoje à noite vou estar menos contente.
    >>  ............................................
  athletic.dialogue.conversations.us.future.reject/1
    en  Never. Right. Well, that's the question answered.
    >>  ............................................
    pt  Nunca. Certo. Bom, a pergunta foi respondida.
    >>  ............................................
  athletic.dialogue.conversations.us.future.reject/2
    en  ...It's a hard word. I'd sooner have it than a soft one that meant the same.
    >>  ............................................
    pt  ...É uma palavra dura. Prefiro ela a uma suave que dissesse o mesmo.
    >>  ............................................
  athletic.dialogue.conversations.us.future.reject/3
    en  Fair enough. I'll set it down and we'll get on.
    >>  ............................................
    pt  Tudo bem. Vou largar e a gente segue.
    >>  ............................................
  confident.dialogue.conversations.us.future.reject/1
    en  Never. Right. That's a hard word.
    >>  ............................................
    pt  Nunca. Certo. É uma palavra dura.
    >>  ............................................
  confident.dialogue.conversations.us.future.reject/2
    en  Then I'll stop planning around you.
    >>  ............................................
    pt  Então eu paro de planejar em volta de você.
    >>  ............................................
  confident.dialogue.conversations.us.future.reject/3
    en  ...Understood. I'll not raise it again.
    >>  ............................................
    pt  ...Entendido. Não levanto de novo.
    >>  ............................................
  crabby.dialogue.conversations.us.future.reject/1
    en  Never. Right. That's a hard word.
    >>  ............................................
    pt  Nunca. Certo. É uma palavra dura.
    >>  ............................................
  crabby.dialogue.conversations.us.future.reject/2
    en  Then I'll stop planning around you.
    >>  ............................................
    pt  Então eu paro de planejar em volta de você.
    >>  ............................................
  crabby.dialogue.conversations.us.future.reject/3
    en  ...Understood. I'll not raise it again.
    >>  ............................................
    pt  ...Entendido. Não levanto de novo.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.reject/1
    en  ...Never. That's a hard word from you, %1$s, of all people.
    >>  ............................................
    pt  ...Nunca. É uma palavra dura vinda de você, %1$s, logo de você.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.reject/2
    en  Right. I'd rather know than wonder. It still lands like a stone.
    >>  ............................................
    pt  Certo. Prefiro saber a ficar imaginando. Ainda cai como pedra.
    >>  ............................................
  extroverted.dialogue.conversations.us.future.reject/3
    en  ...I'll not ask again. We can still be what we are, I hope.
    >>  ............................................
    pt  ...Não pergunto de novo. Ainda podemos ser o que somos, espero.
    >>  ............................................
  flirty.dialogue.conversations.us.future.reject/1
    en  ...Never. That's a hard word from you, %1$s, of all people.
    >>  ............................................
    pt  ...Nunca. É uma palavra dura vinda de você, %1$s, logo de você.
    >>  ............................................
  flirty.dialogue.conversations.us.future.reject/2
    en  Right. I'd rather know than wonder. It still lands like a stone.
    >>  ............................................
    pt  Certo. Prefiro saber a ficar imaginando. Ainda cai como pedra.
    >>  ............................................
  flirty.dialogue.conversations.us.future.reject/3
    en  ...I'll not ask again. We can still be what we are, I hope.
    >>  ............................................
    pt  ...Não pergunto de novo. Ainda podemos ser o que somos, espero.
    >>  ............................................
  friendly.dialogue.conversations.us.future.reject/1
    en  ...Never. That's a hard word from you, %1$s, of all people.
    >>  ............................................
    pt  ...Nunca. É uma palavra dura vinda de você, %1$s, logo de você.
    >>  ............................................
  friendly.dialogue.conversations.us.future.reject/2
    en  Right. I'd rather know than wonder. It still lands like a stone.
    >>  ............................................
    pt  Certo. Prefiro saber a ficar imaginando. Ainda cai como pedra.
    >>  ............................................
  friendly.dialogue.conversations.us.future.reject/3
    en  ...I'll not ask again. We can still be what we are, I hope.
    >>  ............................................
    pt  ...Não pergunto de novo. Ainda podemos ser o que somos, espero.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.reject/1
    en  ...Never. That's a hard word, %1$s. Harder than I'd braced for.
    >>  ............................................
    pt  ...Nunca. É uma palavra dura, %1$s. Mais dura do que eu me preparei.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.reject/2
    en  Right. Yes. I'd rather you said it than let me keep hoping.
    >>  ............................................
    pt  Certo. Sim. Prefiro que você diga a me deixar continuar esperando.
    >>  ............................................
  gloomy.dialogue.conversations.us.future.reject/3
    en  ...I'm glad I asked. I'll be less glad about it tonight.
    >>  ............................................
    pt  ...Estou contente de ter perguntado. Hoje à noite vou estar menos contente.
    >>  ............................................
  greedy.dialogue.conversations.us.future.reject/1
    en  Never. Right. That's a hard word.
    >>  ............................................
    pt  Nunca. Certo. É uma palavra dura.
    >>  ............................................
  greedy.dialogue.conversations.us.future.reject/2
    en  Then I'll stop planning around you.
    >>  ............................................
    pt  Então eu paro de planejar em volta de você.
    >>  ............................................
  greedy.dialogue.conversations.us.future.reject/3
    en  ...Understood. I'll not raise it again.
    >>  ............................................
    pt  ...Entendido. Não levanto de novo.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.reject/1
    en  Never. Right. That's a hard word.
    >>  ............................................
    pt  Nunca. Certo. É uma palavra dura.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.reject/2
    en  Then I'll stop planning around you.
    >>  ............................................
    pt  Então eu paro de planejar em volta de você.
    >>  ............................................
  grumpy.dialogue.conversations.us.future.reject/3
    en  ...Understood. I'll not raise it again.
    >>  ............................................
    pt  ...Entendido. Não levanto de novo.
    >>  ............................................
  introverted.dialogue.conversations.us.future.reject/1
    en  ...Never. Right.
    >>  ............................................
    pt  ...Nunca. Certo.
    >>  ............................................
  introverted.dialogue.conversations.us.future.reject/2
    en  That's clear enough.
    >>  ............................................
    pt  É claro o bastante.
    >>  ............................................
  introverted.dialogue.conversations.us.future.reject/3
    en  ...I'll not mention it again.
    >>  ............................................
    pt  ...Não menciono de novo.
    >>  ............................................
  lazy.dialogue.conversations.us.future.reject/1
    en  Never. Right. Well, that's the question answered.
    >>  ............................................
    pt  Nunca. Certo. Bom, a pergunta foi respondida.
    >>  ............................................
  lazy.dialogue.conversations.us.future.reject/2
    en  ...It's a hard word. I'd sooner have it than a soft one that meant the same.
    >>  ............................................
    pt  ...É uma palavra dura. Prefiro ela a uma suave que dissesse o mesmo.
    >>  ............................................
  lazy.dialogue.conversations.us.future.reject/3
    en  Fair enough. I'll set it down and we'll get on.
    >>  ............................................
    pt  Tudo bem. Vou largar e a gente segue.
    >>  ............................................
  odd.dialogue.conversations.us.future.reject/1
    en  ...Never. Right.
    >>  ............................................
    pt  ...Nunca. Certo.
    >>  ............................................
  odd.dialogue.conversations.us.future.reject/2
    en  That's clear enough.
    >>  ............................................
    pt  É claro o bastante.
    >>  ............................................
  odd.dialogue.conversations.us.future.reject/3
    en  ...I'll not mention it again.
    >>  ............................................
    pt  ...Não menciono de novo.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.reject/1
    en  Never. Right. Well, that's the question answered.
    >>  ............................................
    pt  Nunca. Certo. Bom, a pergunta foi respondida.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.reject/2
    en  ...It's a hard word. I'd sooner have it than a soft one that meant the same.
    >>  ............................................
    pt  ...É uma palavra dura. Prefiro ela a uma suave que dissesse o mesmo.
    >>  ............................................
  peaceful.dialogue.conversations.us.future.reject/3
    en  Fair enough. I'll set it down and we'll get on.
    >>  ............................................
    pt  Tudo bem. Vou largar e a gente segue.
    >>  ............................................
  peppy.dialogue.conversations.us.future.reject/1
    en  ...Never! Well. That's admirably decisive of you, %1$s.
    >>  ............................................
    pt  ...Nunca! Bom. Admiravelmente decisivo da sua parte, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.us.future.reject/2
    en  Right. Never. I'll cross the whole page out, then.
    >>  ............................................
    pt  Certo. Nunca. Então eu risco a página inteira.
    >>  ............................................
  peppy.dialogue.conversations.us.future.reject/3
    en  ...Ha. Fine. I did ask, and I got a very clear answer.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu perguntei, e recebi uma resposta bem clara.
    >>  ............................................
  playful.dialogue.conversations.us.future.reject/1
    en  ...Never! Well. That's admirably decisive of you, %1$s.
    >>  ............................................
    pt  ...Nunca! Bom. Admiravelmente decisivo da sua parte, %1$s.
    >>  ............................................
  playful.dialogue.conversations.us.future.reject/2
    en  Right. Never. I'll cross the whole page out, then.
    >>  ............................................
    pt  Certo. Nunca. Então eu risco a página inteira.
    >>  ............................................
  playful.dialogue.conversations.us.future.reject/3
    en  ...Ha. Fine. I did ask, and I got a very clear answer.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu perguntei, e recebi uma resposta bem clara.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.reject/1
    en  Never. Right. Well, that's the question answered.
    >>  ............................................
    pt  Nunca. Certo. Bom, a pergunta foi respondida.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.reject/2
    en  ...It's a hard word. I'd sooner have it than a soft one that meant the same.
    >>  ............................................
    pt  ...É uma palavra dura. Prefiro ela a uma suave que dissesse o mesmo.
    >>  ............................................
  relaxed.dialogue.conversations.us.future.reject/3
    en  Fair enough. I'll set it down and we'll get on.
    >>  ............................................
    pt  Tudo bem. Vou largar e a gente segue.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.reject/1
    en  ...Never. That's a hard word, %1$s. Harder than I'd braced for.
    >>  ............................................
    pt  ...Nunca. É uma palavra dura, %1$s. Mais dura do que eu me preparei.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.reject/2
    en  Right. Yes. I'd rather you said it than let me keep hoping.
    >>  ............................................
    pt  Certo. Sim. Prefiro que você diga a me deixar continuar esperando.
    >>  ............................................
  sensitive.dialogue.conversations.us.future.reject/3
    en  ...I'm glad I asked. I'll be less glad about it tonight.
    >>  ............................................
    pt  ...Estou contente de ter perguntado. Hoje à noite vou estar menos contente.
    >>  ............................................
  shy.dialogue.conversations.us.future.reject/1
    en  ...Never. Right.
    >>  ............................................
    pt  ...Nunca. Certo.
    >>  ............................................
  shy.dialogue.conversations.us.future.reject/2
    en  That's clear enough.
    >>  ............................................
    pt  É claro o bastante.
    >>  ............................................
  shy.dialogue.conversations.us.future.reject/3
    en  ...I'll not mention it again.
    >>  ............................................
    pt  ...Não menciono de novo.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.reject/1
    en  ...Never! Well. That's admirably decisive of you, %1$s.
    >>  ............................................
    pt  ...Nunca! Bom. Admiravelmente decisivo da sua parte, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.reject/2
    en  Right. Never. I'll cross the whole page out, then.
    >>  ............................................
    pt  Certo. Nunca. Então eu risco a página inteira.
    >>  ............................................
  upbeat.dialogue.conversations.us.future.reject/3
    en  ...Ha. Fine. I did ask, and I got a very clear answer.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu perguntei, e recebi uma resposta bem clara.
    >>  ............................................
  witty.dialogue.conversations.us.future.reject/1
    en  ...Never! Well. That's admirably decisive of you, %1$s.
    >>  ............................................
    pt  ...Nunca! Bom. Admiravelmente decisivo da sua parte, %1$s.
    >>  ............................................
  witty.dialogue.conversations.us.future.reject/2
    en  Right. Never. I'll cross the whole page out, then.
    >>  ............................................
    pt  Certo. Nunca. Então eu risco a página inteira.
    >>  ............................................
  witty.dialogue.conversations.us.future.reject/3
    en  ...Ha. Fine. I did ask, and I got a very clear answer.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu perguntei, e recebi uma resposta bem clara.
    >>  ............................................
```

</details>


### Button `leave` — "We'll come back to this."

*stance family `exit` · tone `plain` · answers the beat(s) `us.future.align.to.future`, `us.future.ask_priorities.to.future`, `us.future.avoid.to.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.future.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.followup.leave   [24 chars]
    en  We'll come back to this.
    >>  ............................................
    pt  A gente volta a isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.future.leave
WHO    VILLAGER — what the player reads after pressing "We'll come back to this."
       spoken on: conversations.topic.future.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.future.respond / leave
```

```text
  dialogue.conversations.us.future.leave/1   [36 chars]
    en  Aye. It's not a one-evening subject.
    >>  ............................................
    pt  É. Não é assunto de uma noite só.
    >>  ............................................
  dialogue.conversations.us.future.leave/2   [35 chars]
    en  Go on. We'll come back to it, %1$s.
    >>  ............................................
    pt  Pode ir. A gente volta a isso, %1$s.
    >>  ............................................
  dialogue.conversations.us.future.leave/3   [13 chars]
    en  Right. Later.
    >>  ............................................
    pt  Certo. Depois.
    >>  ............................................
```

---


## `conversations.topic.future.home`

**Reached from 1 route(s):** `conversations.topic.future.respond` / `ask_home`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.future.home` — e.g. "Here. I've thought about it and every other answer was me being polite."


```text
POOL   dialogue key: dialogue.conversations.topic.future.home
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.future.home
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.future.home   [37 chars]
    en  That's where I've put us, in my head.
    >>  ............................................
    pt  É aí que eu nos coloquei, na minha cabeça.
    >>  ............................................
```


### Button `then_we_build_it` — "Then we'll build it."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `future.home`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.home.build` — accepted phrasings: "then we will build it"; "we can build that"; "let us build it together"
  - the message must contain one of: `build`
  - scored words: `build`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.future.home.then_we_build_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.home
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.home.then_we_build_it   [20 chars]
    en  Then we'll build it.
    >>  ............................................
    pt  Então a gente constrói.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `future.home.build`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `future.home.build`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.future.home.build
WHO    VILLAGER — what the player reads after pressing "Then we'll build it."
       spoken on: conversations.topic.future.home, button `then_we_build_it`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.home.build`: the villager accepts. Subject `future.home`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.future.home.build/1   [73 chars]
    en  You say that like it's a small thing. It isn't, and I'm glad you said it.
    >>  ............................................
    pt  Você diz isso como se fosse pouco. Não é, e ainda bem que você disse.
    >>  ............................................
  dialogue.conversations.future.home.build/2   [61 chars]
    en  Then I'll start counting timber instead of counting evenings.
    >>  ............................................
    pt  Então começo a contar madeira em vez de contar noites.
    >>  ............................................
  dialogue.conversations.future.home.build/3   [55 chars]
    en  Careful. I'll hold you to that and I'll begin tomorrow.
    >>  ............................................
    pt  Cuidado. Vou cobrar isso e vou começar amanhã.
    >>  ............................................
```


### Button `ask_when` — "When were you thinking?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `future.home`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.home.when` — accepted phrasings: "when were you thinking"; "how soon do you mean"; "what sort of timescale"
  - the message must contain one of: `thinking`
  - scored words: `thinking`(1.0), `when`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.future.home.ask_when
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.home
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.home.ask_when   [23 chars]
    en  When were you thinking?
    >>  ............................................
    pt  Você estava pensando em quando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `future.home.when`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.future.home.when
WHO    VILLAGER — what the player reads after pressing "When were you thinking?"
       spoken on: conversations.topic.future.home, button `ask_when`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.home.when`: the villager discloses. Subject `future.home`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.future.home.when/1   [73 chars]
    en  After the harvest, if the harvest is kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  dialogue.conversations.future.home.when/2   [75 chars]
    en  Sooner than I've been saying out loud. I've been rounding it up to be safe.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Venho arredondando pra cima por segurança.
    >>  ............................................
  dialogue.conversations.future.home.when/3   [63 chars]
    en  I've not let myself pick a season. Picking one makes it a plan.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Escolher uma torna isso um plano.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.future.home.when/1
    en  After the harvest. I say that every year and this is the first year I meant it.
    >>  ............................................
    pt  Depois da colheita. Digo isso todo ano e este é o primeiro em que falei sério.
    >>  ............................................
  anxious.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round it up because saying the true one out loud is hard.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo porque dizer o verdadeiro em voz alta é difícil.
    >>  ............................................
  anxious.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. If I pick one it becomes a thing that can be lost.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Se eu escolher, vira algo que pode se perder.
    >>  ............................................
  athletic.dialogue.conversations.future.home.when/1
    en  After the harvest, if it's kind. I've learned not to build in a thin year.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Aprendi a não construir em ano magro.
    >>  ............................................
  athletic.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round these things up out of long habit.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo essas coisas por hábito antigo.
    >>  ............................................
  athletic.dialogue.conversations.future.home.when/3
    en  I've not picked a season. Plans made in winter get unmade by spring; I've seen it.
    >>  ............................................
    pt  Não escolhi uma estação. Plano feito no inverno se desfaz na primavera; já vi.
    >>  ............................................
  confident.dialogue.conversations.future.home.when/1
    en  After the harvest, if the harvest is kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  confident.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying out loud. I've been rounding it up to be safe.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Venho arredondando pra cima por segurança.
    >>  ............................................
  confident.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. Picking one makes it a plan.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Escolher uma torna isso um plano.
    >>  ............................................
  crabby.dialogue.conversations.future.home.when/1
    en  After the harvest, if the harvest is kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  crabby.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying out loud. I've been rounding it up to be safe.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Venho arredondando pra cima por segurança.
    >>  ............................................
  crabby.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. Picking one makes it a plan.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Escolher uma torna isso um plano.
    >>  ............................................
  extroverted.dialogue.conversations.future.home.when/1
    en  After the harvest, %1$s, if it's kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, %1$s, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  extroverted.dialogue.conversations.future.home.when/2
    en  Sooner than I've said out loud. I've been rounding it up so you'd not feel hurried.
    >>  ............................................
    pt  Mais cedo do que eu disse. Venho arredondando pra você não se sentir apressado.
    >>  ............................................
  extroverted.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. You picking one with me would settle it.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Você escolher uma comigo resolveria.
    >>  ............................................
  flirty.dialogue.conversations.future.home.when/1
    en  After the harvest, %1$s, if it's kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, %1$s, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  flirty.dialogue.conversations.future.home.when/2
    en  Sooner than I've said out loud. I've been rounding it up so you'd not feel hurried.
    >>  ............................................
    pt  Mais cedo do que eu disse. Venho arredondando pra você não se sentir apressado.
    >>  ............................................
  flirty.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. You picking one with me would settle it.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Você escolher uma comigo resolveria.
    >>  ............................................
  friendly.dialogue.conversations.future.home.when/1
    en  After the harvest, %1$s, if it's kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, %1$s, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  friendly.dialogue.conversations.future.home.when/2
    en  Sooner than I've said out loud. I've been rounding it up so you'd not feel hurried.
    >>  ............................................
    pt  Mais cedo do que eu disse. Venho arredondando pra você não se sentir apressado.
    >>  ............................................
  friendly.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. You picking one with me would settle it.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Você escolher uma comigo resolveria.
    >>  ............................................
  gloomy.dialogue.conversations.future.home.when/1
    en  After the harvest. I say that every year and this is the first year I meant it.
    >>  ............................................
    pt  Depois da colheita. Digo isso todo ano e este é o primeiro em que falei sério.
    >>  ............................................
  gloomy.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round it up because saying the true one out loud is hard.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo porque dizer o verdadeiro em voz alta é difícil.
    >>  ............................................
  gloomy.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. If I pick one it becomes a thing that can be lost.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Se eu escolher, vira algo que pode se perder.
    >>  ............................................
  greedy.dialogue.conversations.future.home.when/1
    en  After the harvest, if the harvest is kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  greedy.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying out loud. I've been rounding it up to be safe.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Venho arredondando pra cima por segurança.
    >>  ............................................
  greedy.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. Picking one makes it a plan.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Escolher uma torna isso um plano.
    >>  ............................................
  grumpy.dialogue.conversations.future.home.when/1
    en  After the harvest, if the harvest is kind. Before it, if I lose my nerve.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Antes dela, se eu perder a coragem.
    >>  ............................................
  grumpy.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying out loud. I've been rounding it up to be safe.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Venho arredondando pra cima por segurança.
    >>  ............................................
  grumpy.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. Picking one makes it a plan.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Escolher uma torna isso um plano.
    >>  ............................................
  introverted.dialogue.conversations.future.home.when/1
    en  After the harvest.
    >>  ............................................
    pt  Depois da colheita.
    >>  ............................................
  introverted.dialogue.conversations.future.home.when/2
    en  Sooner than I've said.
    >>  ............................................
    pt  Mais cedo do que eu disse.
    >>  ............................................
  introverted.dialogue.conversations.future.home.when/3
    en  I've not picked a season.
    >>  ............................................
    pt  Não escolhi uma estação.
    >>  ............................................
  lazy.dialogue.conversations.future.home.when/1
    en  After the harvest, if it's kind. I've learned not to build in a thin year.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Aprendi a não construir em ano magro.
    >>  ............................................
  lazy.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round these things up out of long habit.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo essas coisas por hábito antigo.
    >>  ............................................
  lazy.dialogue.conversations.future.home.when/3
    en  I've not picked a season. Plans made in winter get unmade by spring; I've seen it.
    >>  ............................................
    pt  Não escolhi uma estação. Plano feito no inverno se desfaz na primavera; já vi.
    >>  ............................................
  odd.dialogue.conversations.future.home.when/1
    en  After the harvest.
    >>  ............................................
    pt  Depois da colheita.
    >>  ............................................
  odd.dialogue.conversations.future.home.when/2
    en  Sooner than I've said.
    >>  ............................................
    pt  Mais cedo do que eu disse.
    >>  ............................................
  odd.dialogue.conversations.future.home.when/3
    en  I've not picked a season.
    >>  ............................................
    pt  Não escolhi uma estação.
    >>  ............................................
  peaceful.dialogue.conversations.future.home.when/1
    en  After the harvest, if it's kind. I've learned not to build in a thin year.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Aprendi a não construir em ano magro.
    >>  ............................................
  peaceful.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round these things up out of long habit.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo essas coisas por hábito antigo.
    >>  ............................................
  peaceful.dialogue.conversations.future.home.when/3
    en  I've not picked a season. Plans made in winter get unmade by spring; I've seen it.
    >>  ............................................
    pt  Não escolhi uma estação. Plano feito no inverno se desfaz na primavera; já vi.
    >>  ............................................
  peppy.dialogue.conversations.future.home.when/1
    en  After the harvest — or before it, if I lose my nerve, which is entirely possible.
    >>  ............................................
    pt  Depois da colheita — ou antes, se eu perder a coragem, o que é bem possível.
    >>  ............................................
  peppy.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying! I've been rounding it up so as not to frighten anybody.
    >>  ............................................
    pt  Mais cedo do que venho dizendo! Venho arredondando pra não assustar ninguém.
    >>  ............................................
  peppy.dialogue.conversations.future.home.when/3
    en  I've refused to pick a season. Picking one makes it a plan, and plans are terrifying.
    >>  ............................................
    pt  Me recusei a escolher uma estação. Escolher torna isso um plano, e planos assustam.
    >>  ............................................
  playful.dialogue.conversations.future.home.when/1
    en  After the harvest — or before it, if I lose my nerve, which is entirely possible.
    >>  ............................................
    pt  Depois da colheita — ou antes, se eu perder a coragem, o que é bem possível.
    >>  ............................................
  playful.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying! I've been rounding it up so as not to frighten anybody.
    >>  ............................................
    pt  Mais cedo do que venho dizendo! Venho arredondando pra não assustar ninguém.
    >>  ............................................
  playful.dialogue.conversations.future.home.when/3
    en  I've refused to pick a season. Picking one makes it a plan, and plans are terrifying.
    >>  ............................................
    pt  Me recusei a escolher uma estação. Escolher torna isso um plano, e planos assustam.
    >>  ............................................
  relaxed.dialogue.conversations.future.home.when/1
    en  After the harvest, if it's kind. I've learned not to build in a thin year.
    >>  ............................................
    pt  Depois da colheita, se ela for boa. Aprendi a não construir em ano magro.
    >>  ............................................
  relaxed.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round these things up out of long habit.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo essas coisas por hábito antigo.
    >>  ............................................
  relaxed.dialogue.conversations.future.home.when/3
    en  I've not picked a season. Plans made in winter get unmade by spring; I've seen it.
    >>  ............................................
    pt  Não escolhi uma estação. Plano feito no inverno se desfaz na primavera; já vi.
    >>  ............................................
  sensitive.dialogue.conversations.future.home.when/1
    en  After the harvest. I say that every year and this is the first year I meant it.
    >>  ............................................
    pt  Depois da colheita. Digo isso todo ano e este é o primeiro em que falei sério.
    >>  ............................................
  sensitive.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying. I round it up because saying the true one out loud is hard.
    >>  ............................................
    pt  Mais cedo do que venho dizendo. Arredondo porque dizer o verdadeiro em voz alta é difícil.
    >>  ............................................
  sensitive.dialogue.conversations.future.home.when/3
    en  I've not let myself pick a season. If I pick one it becomes a thing that can be lost.
    >>  ............................................
    pt  Não me deixei escolher uma estação. Se eu escolher, vira algo que pode se perder.
    >>  ............................................
  shy.dialogue.conversations.future.home.when/1
    en  After the harvest.
    >>  ............................................
    pt  Depois da colheita.
    >>  ............................................
  shy.dialogue.conversations.future.home.when/2
    en  Sooner than I've said.
    >>  ............................................
    pt  Mais cedo do que eu disse.
    >>  ............................................
  shy.dialogue.conversations.future.home.when/3
    en  I've not picked a season.
    >>  ............................................
    pt  Não escolhi uma estação.
    >>  ............................................
  upbeat.dialogue.conversations.future.home.when/1
    en  After the harvest — or before it, if I lose my nerve, which is entirely possible.
    >>  ............................................
    pt  Depois da colheita — ou antes, se eu perder a coragem, o que é bem possível.
    >>  ............................................
  upbeat.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying! I've been rounding it up so as not to frighten anybody.
    >>  ............................................
    pt  Mais cedo do que venho dizendo! Venho arredondando pra não assustar ninguém.
    >>  ............................................
  upbeat.dialogue.conversations.future.home.when/3
    en  I've refused to pick a season. Picking one makes it a plan, and plans are terrifying.
    >>  ............................................
    pt  Me recusei a escolher uma estação. Escolher torna isso um plano, e planos assustam.
    >>  ............................................
  witty.dialogue.conversations.future.home.when/1
    en  After the harvest — or before it, if I lose my nerve, which is entirely possible.
    >>  ............................................
    pt  Depois da colheita — ou antes, se eu perder a coragem, o que é bem possível.
    >>  ............................................
  witty.dialogue.conversations.future.home.when/2
    en  Sooner than I've been saying! I've been rounding it up so as not to frighten anybody.
    >>  ............................................
    pt  Mais cedo do que venho dizendo! Venho arredondando pra não assustar ninguém.
    >>  ............................................
  witty.dialogue.conversations.future.home.when/3
    en  I've refused to pick a season. Picking one makes it a plan, and plans are terrifying.
    >>  ............................................
    pt  Me recusei a escolher uma estação. Escolher torna isso um plano, e planos assustam.
    >>  ............................................
```

</details>


### Button `leave` — "That's worth thinking about."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `future.home` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.future.home.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.home
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.home.leave   [28 chars]
    en  That's worth thinking about.
    >>  ............................................
    pt  Vale a pena pensar nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.future.home.leave
WHO    VILLAGER — what the player reads after pressing "That's worth thinking about."
       spoken on: conversations.topic.future.home, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.home.leave`: the villager accepts. Subject `future.home`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.future.home.leave/1   [12 chars]
    en  Think, then.
    >>  ............................................
    pt  Então pense.
    >>  ............................................
  dialogue.conversations.future.home.leave/2   [18 chars]
    en  So I've found. Do.
    >>  ............................................
    pt  Foi o que eu vi. Pense.
    >>  ............................................
  dialogue.conversations.future.home.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.future.respond`

**Reached from 4 route(s):** `conversations.us` / `future`; `conversations.us` / `future`; `conversations.us` / `future`; `conversations.us` / `future`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.future.again` — e.g. "We just planned the whole of it, love. Give the plan a day to settle."
- `conversations.us.future.baby` — e.g. "Right now? I mostly think about the little one coming. Terrified. Thrilled. Both."
- `conversations.us.future.kids` — e.g. "I want the kids to have it easier than we did. That's the whole plan, really."
- `conversations.us.future.together` — e.g. "More mornings like this one. Maybe a bigger table. People to sit at it."


```text
POOL   dialogue key: dialogue.conversations.topic.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.future.respond   [30 chars]
    en  That's what I picture, anyway.
    >>  ............................................
    pt  É isso que eu imagino, enfim.
    >>  ............................................
```


### Button `align` — "I want the same thing."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `us.future.again.to.future`, `us.future.baby.to.future`, `us.future.kids.to.future`, `us.future.together.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.align` — accepted phrasings: "i want the same thing"; "i want that too"; "that is what i want as well"
  - scored words: `same`(0.5), `thing`(0.4), `want`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.future.respond.align
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.respond.align   [22 chars]
    en  I want the same thing.
    >>  ............................................
    pt  Eu quero a mesma coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.future.align`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5, trust +2  _(recorded under topic `us.future.align`)_
- Then opens: `conversations.topic.future.followup`
- …where the player's next choices will be: "We want different things. Let's work it out." | "I won't promise what I can't give." | "That's never going to happen." | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.align
WHO    VILLAGER — what the player reads after pressing "I want the same thing."
       spoken on: conversations.topic.future.respond, button `align`
       leaves the player on: conversations.topic.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.align.to.future`: the villager accepts. Subject `future`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.align/1   [62 chars]
    en  You do? ...Then that's the whole conversation, and a good one.
    >>  ............................................
    pt  Quer? ...Então essa é a conversa inteira, e foi boa.
    >>  ............................................
  dialogue.conversations.us.future.align/2   [50 chars]
    en  Same thing. After all my worrying, the same thing.
    >>  ............................................
    pt  A mesma coisa. Depois de toda a minha preocupação, a mesma coisa.
    >>  ............................................
  dialogue.conversations.us.future.align/3   [40 chars]
    en  Good. Then we plan instead of wondering.
    >>  ............................................
    pt  Bom. Então a gente planeja em vez de ficar imaginando.
    >>  ............................................
```


### Button `ask_priorities` — "What matters most to you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `us.future.again.to.future`, `us.future.baby.to.future`, `us.future.kids.to.future`, `us.future.together.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.ask_priorities` — accepted phrasings: "what matters most to you"; "what matters to you most of all"; "which part matters most"
  - the message must contain one of: `matters`
  - scored words: `matters`(1.5), `most`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.future.respond.ask_priorities
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.respond.ask_priorities   [25 chars]
    en  What matters most to you?
    >>  ............................................
    pt  O que importa mais para você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.future.ask_priorities`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +4, respect +2  _(recorded under topic `us.future.ask_priorities`)_
- Then opens: `conversations.topic.future.followup`
- …where the player's next choices will be: "We want different things. Let's work it out." | "I won't promise what I can't give." | "That's never going to happen." | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.ask_priorities
WHO    VILLAGER — what the player reads after pressing "What matters most to you?"
       spoken on: conversations.topic.future.respond, button `ask_priorities`
       leaves the player on: conversations.topic.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.ask_priorities.to.future`: the villager accepts. Subject `future`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.ask_priorities/1   [56 chars]
    en  What matters most. ...You. Then the rest, in some order.
    >>  ............................................
    pt  O que importa mais. ...Você. Depois o resto, em alguma ordem.
    >>  ............................................
  dialogue.conversations.us.future.ask_priorities/2   [67 chars]
    en  Nobody asks it that way round. Give me a moment to answer honestly.
    >>  ............................................
    pt  Ninguém pergunta desse jeito. Me dá um instante para responder com honestidade.
    >>  ............................................
  dialogue.conversations.us.future.ask_priorities/3   [56 chars]
    en  A roof, quiet, and someone in it. That's the whole list.
    >>  ............................................
    pt  Um teto, silêncio, e alguém dentro. É a lista inteira.
    >>  ............................................
```


### Button `avoid` — "Let's not plan that far ahead."

*stance family `restraint` · tone `plain` · answers the beat(s) `us.future.again.to.future`, `us.future.baby.to.future`, `us.future.kids.to.future`, `us.future.together.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.avoid` — accepted phrasings: "let us not plan that far ahead"; "no need to plan that far ahead"; "i would rather not look that far ahead"
  - the message must contain one of: `ahead`
  - scored words: `ahead`(1.5), `plan`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.future.respond.avoid
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.respond.avoid   [30 chars]
    en  Let's not plan that far ahead.
    >>  ............................................
    pt  Vamos não planejar tão longe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `us.future.avoid`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — tension +4, trust -2  _(recorded under topic `us.future.avoid`)_
- Then opens: `conversations.topic.future.followup`
- …where the player's next choices will be: "We want different things. Let's work it out." | "I won't promise what I can't give." | "That's never going to happen." | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.avoid
WHO    VILLAGER — what the player reads after pressing "Let's not plan that far ahead."
       spoken on: conversations.topic.future.respond, button `avoid`
       leaves the player on: conversations.topic.future.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.avoid.to.future`: the villager accepts. Subject `future`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.avoid/1   [57 chars]
    en  ...Not that far ahead. Alright. I'll stop bringing it up.
    >>  ............................................
    pt  ...Não tão longe. Certo. Vou parar de tocar no assunto.
    >>  ............................................
  dialogue.conversations.us.future.avoid/2   [34 chars]
    en  We have to plan it sometime, %1$s.
    >>  ............................................
    pt  A gente tem que planejar em algum momento, %1$s.
    >>  ............................................
  dialogue.conversations.us.future.avoid/3   [33 chars]
    en  Mm. I notice you always say that.
    >>  ............................................
    pt  Hm. Reparo que você sempre diz isso.
    >>  ............................................
```


### Button `ask_home` — "Where would we live?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `us.future.again.to.future`, `us.future.baby.to.future`, `us.future.kids.to.future`, `us.future.together.to.future`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `future.home` — accepted phrasings: "where would we live"; "where would we settle"; "what would our house be"
  - the message must contain one of: `live`
  - scored words: `live`(1.2), `where`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.future.respond.ask_home
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.respond.ask_home   [20 chars]
    en  Where would we live?
    >>  ............................................
    pt  Onde a gente moraria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.future.home`
- …where the player's next choices will be: "Then we'll build it." | "When were you thinking?" | "That's worth thinking about."

```text
POOL   dialogue key: dialogue.conversations.future.home
WHO    VILLAGER — what the player reads after pressing "Where would we live?"
       spoken on: conversations.topic.future.respond, button `ask_home`
       leaves the player on: conversations.topic.future.home
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.home`: the villager reports. Subject `future.home`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, self_disclosure, exit
```

```text
  dialogue.conversations.future.home/1   [71 chars]
    en  Here. I've thought about it and every other answer was me being polite.
    >>  ............................................
    pt  Aqui. Já pensei nisso e toda outra resposta era eu sendo educado.
    >>  ............................................
  dialogue.conversations.future.home/2   [79 chars]
    en  Somewhere with the door on the sheltered side. I've been particular about this.
    >>  ............................................
    pt  Em algum lugar com a porta no lado abrigado. Sou exigente quanto a isso.
    >>  ............................................
  dialogue.conversations.future.home/3   [76 chars]
    en  Not here. I'd want a hill and a bad road and nobody dropping in unannounced.
    >>  ............................................
    pt  Aqui não. Eu ia querer um morro e uma estrada ruim e ninguém aparecendo sem avisar.
    >>  ............................................
```


### Button `leave` — "We'll come back to this."

*stance family `exit` · tone `plain` · answers the beat(s) `us.future.again.to.future`, `us.future.baby.to.future`, `us.future.kids.to.future`, `us.future.together.to.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.future.respond.leave   [24 chars]
    en  We'll come back to this.
    >>  ............................................
    pt  A gente volta a isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.future.leave
WHO    VILLAGER — what the player reads after pressing "We'll come back to this."
       spoken on: conversations.topic.future.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.future.followup / leave
```

> Written out in full under **`conversations.topic.future.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

