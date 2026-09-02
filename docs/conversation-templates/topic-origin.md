# Topic: origin

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `origin` |
| Opened from | question `conversations.cat.personal`, button `origin` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | teen, adult |
| Stance families it must offer | `curiosity`, `empathy`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.origin
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.origin   [19 chars]
    en  Where are you from?
    >>  ............................................
    pt  De onde você é?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.origin.followup`](#conversations-scene-origin-followup)
- [`conversations.scene.origin.the_season_that_smells_like_it.respond`](#conversations-scene-origin-the-season-that-smells-like-it-respond)
- [`conversations.scene.origin.whether_this_is_home.respond`](#conversations-scene-origin-whether-this-is-home-respond)
- [`conversations.topic.origin.more.respond`](#conversations-topic-origin-more-respond)
- [`conversations.topic.origin.open.respond`](#conversations-topic-origin-open-respond)

---

## `conversations.scene.origin.followup`

**Reached from 4 route(s):** `conversations.scene.origin.the_season_that_smells_like_it.respond` / `ask_what_it_was_like`; `conversations.scene.origin.the_season_that_smells_like_it.respond` / `sit_with_it`; `conversations.scene.origin.whether_this_is_home.respond` / `ask_the_answer`; `conversations.scene.origin.whether_this_is_home.respond` / `say_it_can_be_both`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.origin.the_season_that_smells_like_it.described` — e.g. "Louder. Everybody worked outdoors until the dark and then all of it happened in one room until sleep."
- `conversations.scene.origin.the_season_that_smells_like_it.eased` — e.g. "They do. I used to fight it and now I let the four days happen and get on with the rest of the year."
- `conversations.scene.origin.whether_this_is_home.answered` — e.g. "I said here, and I meant it, and I went quiet for the rest of the evening about it."
- `conversations.scene.origin.whether_this_is_home.settled` — e.g. "That is the first version of that sentence I have been able to hear without wanting to argue."


```text
POOL   dialogue key: dialogue.conversations.scene.origin.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.origin.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.origin.followup   [13 chars]
    en  Further back?
    >>  ............................................
    pt  Mais para trás?
    >>  ............................................
```


### Button `leave` — "That is far enough back for one day."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:origin.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.origin.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.followup.leave   [36 chars]
    en  That is far enough back for one day.
    >>  ............................................
    pt  É longe o bastante por hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.leaving
WHO    VILLAGER — what the player reads after pressing "That is far enough back for one day."
       spoken on: conversations.scene.origin.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.scene.leaving`: the villager accepts. Subject `origin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.origin.the_season_that_smells_like_it.respond / leave; conversations.scene.origin.whether_this_is_home.respond / leave; conversations.topic.origin.more.respond / leave; conversations.topic.origin.open.respond / leave
```

```text
  dialogue.conversations.scene.origin.leaving/1   [42 chars]
    en  That is the whole of the map, near enough.
    >>  ............................................
    pt  É o mapa inteiro, quase.
    >>  ............................................
  dialogue.conversations.scene.origin.leaving/2   [27 chars]
    en  It was a long time ago now.
    >>  ............................................
    pt  Já faz muito tempo agora.
    >>  ............................................
  dialogue.conversations.scene.origin.leaving/3   [33 chars]
    en  Anyway. Here is where I ended up.
    >>  ............................................
    pt  Enfim. Foi aqui que eu vim parar.
    >>  ............................................
```

---


## `conversations.scene.origin.the_season_that_smells_like_it.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `origin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.origin.the_season_that_smells_like_it` — e.g. "This is the season that smells like where I grew up, and it ambushes me every single year."


```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.origin.the_season_that_smells_like_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond   [18 chars]
    en  This time of year.
    >>  ............................................
    pt  Esta época do ano.
    >>  ............................................
```


### Button `ask_what_it_was_like` — "What was it like there, this time of year?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `origin.the_season_that_smells_like_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.the_season_that_smells_like_it.ask_what_it_was_like` — accepted phrasings: "what was it like there this time of year"; "what was it like there this time of year"; "how was the season there"
  - the message must contain one of: `season`, `there`
  - scored words: `season`(1.8), `there`(1.8), `like`(0.8), `time`(0.8), `year`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond.ask_what_it_was_like
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.the_season_that_smells_like_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond.ask_what_it_was_like   [42 chars]
    en  What was it like there, this time of year?
    >>  ............................................
    pt  Como era lá, nesta época?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `origin.season_memory`)_
- Does: session `turn`
- Then opens: `conversations.scene.origin.followup`
- …where the player's next choices will be: "That is far enough back for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it.described
WHO    VILLAGER — what the player reads after pressing "What was it like there, this time of year?"
       spoken on: conversations.scene.origin.the_season_that_smells_like_it.respond, button `ask_what_it_was_like`
       leaves the player on: conversations.scene.origin.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.the_season_that_smells_like_it.open.described`: the villager reminisces. Subject `origin.season_memory`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.described/1   [101 chars]
    en  Louder. Everybody worked outdoors until the dark and then all of it happened in one room until sleep.
    >>  ............................................
    pt  Mais barulhento. Todo mundo trabalhava fora até escurecer e aí tudo acontecia num cômodo só até dormir.
    >>  ............................................
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.described/2   [85 chars]
    en  Wetter, and the whole village smelled of woodsmoke and wet wool for two solid months.
    >>  ............................................
    pt  Mais úmido, e a vila inteira cheirava a fumaça de lenha e lã molhada por dois meses inteiros.
    >>  ............................................
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.described/3   [111 chars]
    en  We had a thing we did on the shortest day that nobody here has ever heard of, and I have stopped explaining it.
    >>  ............................................
    pt  A gente fazia uma coisa no dia mais curto que ninguém aqui nunca ouviu falar, e eu parei de explicar.
    >>  ............................................
```


### Button `sit_with_it` — "Some seasons carry more than weather."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `origin.the_season_that_smells_like_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.the_season_that_smells_like_it.sit_with_it` — accepted phrasings: "some seasons carry more than weather"; "some seasons carry more than weather"; "the season brings it back"
  - the message must contain one of: `seasons`, `brings`
  - scored words: `seasons`(1.8), `brings`(1.8), `some`(0.8), `carry`(0.8), `more`(0.8), `than`(0.8), `weather`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.the_season_that_smells_like_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond.sit_with_it   [37 chars]
    en  Some seasons carry more than weather.
    >>  ............................................
    pt  Algumas estações carregam mais que clima.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.origin.season.met`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3  _(recorded under topic `origin.season_memory`)_
- Does: session `turn`
- Then opens: `conversations.scene.origin.followup`
- …where the player's next choices will be: "That is far enough back for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it.eased
WHO    VILLAGER — what the player reads after pressing "Some seasons carry more than weather."
       spoken on: conversations.scene.origin.the_season_that_smells_like_it.respond, button `sit_with_it`
       leaves the player on: conversations.scene.origin.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.the_season_that_smells_like_it.open.eased`: the villager accepts. Subject `origin.season_memory`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.eased/1   [100 chars]
    en  They do. I used to fight it and now I let the four days happen and get on with the rest of the year.
    >>  ............................................
    pt  Carregam. Eu costumava lutar contra e agora deixo os quatro dias acontecerem e sigo com o resto do ano.
    >>  ............................................
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.eased/2   [98 chars]
    en  That is a kind way to put it. I am usually told it is only the cold and that I ought to wear more.
    >>  ............................................
    pt  É um jeito gentil de dizer. Quase todo mundo me diz que é só o frio e que eu devia me agasalhar mais.
    >>  ............................................
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.eased/3   [99 chars]
    en  Thank you. It helps to say it aloud once a year to somebody who does not immediately try to fix it.
    >>  ............................................
    pt  Obrigada. Ajuda dizer em voz alta uma vez por ano para alguém que não tenta consertar na hora.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `origin.the_season_that_smells_like_it.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.the_season_that_smells_like_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it.respond.leave   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.scene.origin.the_season_that_smells_like_it.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.scene.leaving`: the villager accepts. Subject `origin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.origin.followup / leave; conversations.scene.origin.whether_this_is_home.respond / leave; conversations.topic.origin.more.respond / leave; conversations.topic.origin.open.respond / leave
```

> Written out in full under **`conversations.scene.origin.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.origin.whether_this_is_home.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `origin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.origin.whether_this_is_home` — e.g. "I have been here longer than I was ever there, and I still say 'back home' about the other place."


```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.origin.whether_this_is_home.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home.respond   [21 chars]
    en  Whether here is home.
    >>  ............................................
    pt  Se aqui é casa.
    >>  ............................................
```


### Button `ask_the_answer` — "What did you answer?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `origin.whether_this_is_home.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.whether_this_is_home.ask_the_answer` — accepted phrasings: "what did you answer"; "what did you answer"; "which one did you say"
  - the message must contain one of: `answer`, `say`
  - scored words: `answer`(1.8), `say`(1.8), `which`(0.8), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home.respond.ask_the_answer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.whether_this_is_home.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home.respond.ask_the_answer   [20 chars]
    en  What did you answer?
    >>  ............................................
    pt  O que você respondeu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `origin.is_this_home`)_
- Does: session `turn`
- Then opens: `conversations.scene.origin.followup`
- …where the player's next choices will be: "That is far enough back for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home.answered
WHO    VILLAGER — what the player reads after pressing "What did you answer?"
       spoken on: conversations.scene.origin.whether_this_is_home.respond, button `ask_the_answer`
       leaves the player on: conversations.scene.origin.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.whether_this_is_home.open.answered`: the villager explains. Subject `origin.is_this_home`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home.answered/1   [83 chars]
    en  I said here, and I meant it, and I went quiet for the rest of the evening about it.
    >>  ............................................
    pt  Eu disse aqui, e falei sério, e fiquei calada o resto da noite por causa disso.
    >>  ............................................
  dialogue.conversations.scene.origin.whether_this_is_home.answered/2   [110 chars]
    en  I said neither, which was true and which I would not say again, because it upset somebody who deserved better.
    >>  ............................................
    pt  Eu disse nenhum dos dois, o que era verdade e o que eu não repetiria, porque magoou alguém que merecia melhor.
    >>  ............................................
  dialogue.conversations.scene.origin.whether_this_is_home.answered/3   [95 chars]
    en  I said the old place, out of loyalty, and I have known ever since that it was the wrong answer.
    >>  ............................................
    pt  Eu disse o lugar antigo, por lealdade, e desde então eu sei que era a resposta errada.
    >>  ............................................
```


### Button `say_it_can_be_both` — "A person can hold two homes."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `origin.whether_this_is_home.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.whether_this_is_home.say_it_can_be_both` — accepted phrasings: "a person can hold two homes"; "a person can hold two homes"; "it can be both places"
  - the message must contain one of: `homes`, `both`
  - scored words: `homes`(1.8), `both`(1.8), `person`(0.8), `hold`(0.8), `two`(0.8), `places`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home.respond.say_it_can_be_both
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.whether_this_is_home.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home.respond.say_it_can_be_both   [28 chars]
    en  A person can hold two homes.
    >>  ............................................
    pt  Uma pessoa pode ter duas casas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.origin.home.met`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `origin.is_this_home`)_
- Does: session `turn`
- Then opens: `conversations.scene.origin.followup`
- …where the player's next choices will be: "That is far enough back for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home.settled
WHO    VILLAGER — what the player reads after pressing "A person can hold two homes."
       spoken on: conversations.scene.origin.whether_this_is_home.respond, button `say_it_can_be_both`
       leaves the player on: conversations.scene.origin.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.whether_this_is_home.open.settled`: the villager accepts. Subject `origin.is_this_home`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home.settled/1   [93 chars]
    en  That is the first version of that sentence I have been able to hear without wanting to argue.
    >>  ............................................
    pt  É a primeira versão dessa frase que eu consegui ouvir sem querer discutir.
    >>  ............................................
  dialogue.conversations.scene.origin.whether_this_is_home.settled/2   [88 chars]
    en  I would like that to be true. Some days I manage to believe it and today is one of them.
    >>  ............................................
    pt  Eu gostaria que fosse verdade. Em alguns dias eu consigo acreditar e hoje é um deles.
    >>  ............................................
  dialogue.conversations.scene.origin.whether_this_is_home.settled/3   [88 chars]
    en  Then I shall try holding both, and stop treating one of them as a betrayal of the other.
    >>  ............................................
    pt  Então vou tentar ter as duas, e parar de tratar uma delas como traição à outra.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `origin.whether_this_is_home.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.origin.whether_this_is_home.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home.respond.leave   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.scene.origin.whether_this_is_home.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.scene.leaving`: the villager accepts. Subject `origin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.origin.followup / leave; conversations.scene.origin.the_season_that_smells_like_it.respond / leave; conversations.topic.origin.more.respond / leave; conversations.topic.origin.open.respond / leave
```

> Written out in full under **`conversations.scene.origin.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.origin.more.respond`

**Reached from 2 route(s):** `conversations.topic.origin.open.respond` / `ask_why_they_left`; `conversations.topic.origin.open.respond` / `say_that_is_a_long_road`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.origin.open.acknowledged` — e.g. "It was, and the walking was the easy half. The arriving somewhere that already has all its people is the hard half."
- `conversations.origin.open.explained` — e.g. "Work, officially. Really it was that everybody there had already decided who I was going to be."


```text
POOL   dialogue key: dialogue.conversations.topic.origin.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.origin.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.origin.more.respond   [26 chars]
    en  What you brought with you.
    >>  ............................................
    pt  O que você trouxe com você.
    >>  ............................................
```


### Button `ask_to_see_it` — "What was the one object?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `origin.open.explained`, `origin.open.acknowledged`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.more.ask_to_see_it` — accepted phrasings: "what was the one object"; "what was the one object"; "what did you bring with you"
  - the message must contain one of: `object`, `bring`
  - scored words: `object`(1.8), `bring`(1.8), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.origin.more.respond.ask_to_see_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.more.respond.ask_to_see_it   [24 chars]
    en  What was the one object?
    >>  ............................................
    pt  Qual era o objeto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.origin.asked_after`, budget `standard`, replay policy `once`
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `origin.what_i_carried`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.origin.more.shown
WHO    VILLAGER — what the player reads after pressing "What was the one object?"
       spoken on: conversations.topic.origin.more.respond, button `ask_to_see_it`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.more.shown`: the villager explains. Subject `origin.what_i_carried`, polarity `positive`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.more.shown/1   [95 chars]
    en  A spoon, if you can believe it. Worth nothing, and I would go back into a burning house for it.
    >>  ............................................
    pt  Uma colher, se der para acreditar. Não vale nada, e eu voltaria a uma casa em chamas por ela.
    >>  ............................................
  dialogue.conversations.origin.more.shown/2   [99 chars]
    en  A bit of cloth off a coat. The coat is long gone and the cloth has moved house with me three times.
    >>  ............................................
    pt  Um pedaço de tecido de um casaco. O casaco sumiu faz tempo e o tecido já mudou de casa comigo três vezes.
    >>  ............................................
  dialogue.conversations.origin.more.shown/3   [109 chars]
    en  A small carved thing my grandmother made badly. Everyone who sees it assumes a child made it, and I let them.
    >>  ............................................
    pt  Uma coisinha entalhada que minha avó fez mal feita. Quem vê acha que uma criança fez, e eu deixo achar.
    >>  ............................................
```


### Button `say_it_is_worth_keeping` — "Worth carrying that far."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `origin.open.explained`, `origin.open.acknowledged`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.more.say_it_is_worth_keeping` — accepted phrasings: "worth carrying that far"; "worth carrying that far"; "that was worth keeping"
  - the message must contain one of: `carrying`, `keeping`
  - scored words: `carrying`(1.8), `keeping`(1.8), `worth`(0.8), `far`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.origin.more.respond.say_it_is_worth_keeping
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.more.respond.say_it_is_worth_keeping   [24 chars]
    en  Worth carrying that far.
    >>  ............................................
    pt  Valeu carregar tão longe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `origin.what_i_carried`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.origin.more.acknowledged
WHO    VILLAGER — what the player reads after pressing "Worth carrying that far."
       spoken on: conversations.topic.origin.more.respond, button `say_it_is_worth_keeping`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.more.acknowledged`: the villager accepts. Subject `origin.what_i_carried`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.more.acknowledged/1   [93 chars]
    en  I have never been sure. Some years it is a comfort and some years it is a stone in my pocket.
    >>  ............................................
    pt  Nunca tive certeza. Em alguns anos é um consolo e em outros é uma pedra no bolso.
    >>  ............................................
  dialogue.conversations.origin.more.acknowledged/2   [92 chars]
    en  Thank you. It is the sort of thing you cannot explain the value of without sounding foolish.
    >>  ............................................
    pt  Obrigada. É o tipo de coisa cujo valor não dá para explicar sem soar boba.
    >>  ............................................
  dialogue.conversations.origin.more.acknowledged/3   [88 chars]
    en  It weighs almost nothing and it is the heaviest thing I own. Make of that what you like.
    >>  ............................................
    pt  Não pesa quase nada e é a coisa mais pesada que eu tenho. Faça o que quiser com isso.
    >>  ............................................
```


### Button `wave_off_the_keepsake` — "Old things weigh people down."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `origin.open.explained`, `origin.open.acknowledged`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.more.wave_off_the_keepsake` — accepted phrasings: "old things weigh people down"; "old things weigh people down"; "keepsakes are a weight to carry"
  - the message must contain one of: `weigh`, `keepsakes`
  - scored words: `weigh`(1.8), `keepsakes`(1.8), `old`(0.8), `things`(0.8), `people`(0.8), `down`(0.8), `weight`(0.8), `carry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.origin.more.respond.wave_off_the_keepsake
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.more.respond.wave_off_the_keepsake   [29 chars]
    en  Old things weigh people down.
    >>  ............................................
    pt  Coisas velhas pesam nas pessoas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.origin.cut_short`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `origin.what_i_carried`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.origin.more.closed
WHO    VILLAGER — what the player reads after pressing "Old things weigh people down."
       spoken on: conversations.topic.origin.more.respond, button `wave_off_the_keepsake`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.more.closed`: the villager deflects. Subject `origin.what_i_carried`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.more.closed/1   [93 chars]
    en  They do. It is one object and it weighs less than a loaf, so I judge myself to be bearing up.
    >>  ............................................
    pt  Pesam. É um objeto e pesa menos que um pão, então eu me julgo aguentando bem.
    >>  ............................................
  dialogue.conversations.origin.more.closed/2   [93 chars]
    en  Perhaps. The people I know with nothing from before are not noticeably lighter on their feet.
    >>  ............................................
    pt  Talvez. As pessoas que eu conheço sem nada de antes não andam visivelmente mais leves.
    >>  ............................................
  dialogue.conversations.origin.more.closed/3   [51 chars]
    en  Right. We shall talk about something present, then.
    >>  ............................................
    pt  Certo. Falamos de algo do presente, então.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `origin.open.explained`, `origin.open.acknowledged` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.origin.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.more.respond.leave   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.origin.more.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.scene.leaving`: the villager accepts. Subject `origin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.origin.followup / leave; conversations.scene.origin.the_season_that_smells_like_it.respond / leave; conversations.scene.origin.whether_this_is_home.respond / leave; conversations.topic.origin.open.respond / leave
```

> Written out in full under **`conversations.scene.origin.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.origin.open.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `origin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.origin.open` — e.g. "Not from here, which everybody worked out in my first fortnight and nobody has ever asked about since."


```text
POOL   dialogue key: dialogue.conversations.topic.origin.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.origin.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.origin.open.respond   [20 chars]
    en  Where you came from.
    >>  ............................................
    pt  De onde você veio.
    >>  ............................................
```


### Button `ask_why_they_left` — "What brought you away?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `origin.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.open.ask_why_they_left` — accepted phrasings: "what brought you away"; "what brought you away"; "why did you leave there"
  - the message must contain one of: `brought`, `leave`
  - scored words: `brought`(1.8), `leave`(1.8), `away`(0.8), `why`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.origin.open.respond.ask_why_they_left
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.open.respond.ask_why_they_left   [22 chars]
    en  What brought you away?
    >>  ............................................
    pt  O que te trouxe para longe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `origin.where_from`)_
- Does: session `turn`
- Then opens: `conversations.topic.origin.more.respond`
- …where the player's next choices will be: "What was the one object?" | "Worth carrying that far." | "Old things weigh people down." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.origin.open.explained
WHO    VILLAGER — what the player reads after pressing "What brought you away?"
       spoken on: conversations.topic.origin.open.respond, button `ask_why_they_left`
       leaves the player on: conversations.topic.origin.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.open.explained`: the villager explains. Subject `origin.where_from`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.open.explained/1   [95 chars]
    en  Work, officially. Really it was that everybody there had already decided who I was going to be.
    >>  ............................................
    pt  Trabalho, oficialmente. Na verdade era que todo mundo lá já tinha decidido quem eu ia ser.
    >>  ............................................
  dialogue.conversations.origin.open.explained/2   [102 chars]
    en  The place stopped feeding people. It happened over about four years and everybody pretended otherwise.
    >>  ............................................
    pt  O lugar parou de alimentar as pessoas. Levou uns quatro anos e todo mundo fingiu o contrário.
    >>  ............................................
  dialogue.conversations.origin.open.explained/3   [97 chars]
    en  I followed somebody. They went home again after two seasons and I found that I had not wanted to.
    >>  ............................................
    pt  Segui alguém. Essa pessoa voltou para casa depois de duas estações e eu descobri que não queria voltar.
    >>  ............................................
```


### Button `say_that_is_a_long_road` — "That's a long road to walk."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `origin.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.open.say_that_is_a_long_road` — accepted phrasings: "thats a long road to walk"; "that is a long road to walk"; "a long way to have come"
  - the message must contain one of: `road`, `way`
  - scored words: `road`(1.8), `way`(1.8), `thats`(0.8), `long`(0.8), `walk`(0.8), `come`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.origin.open.respond.say_that_is_a_long_road
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.open.respond.say_that_is_a_long_road   [27 chars]
    en  That's a long road to walk.
    >>  ............................................
    pt  É uma estrada longa de percorrer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `origin.where_from`)_
- Does: session `turn`
- Then opens: `conversations.topic.origin.more.respond`
- …where the player's next choices will be: "What was the one object?" | "Worth carrying that far." | "Old things weigh people down." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.origin.open.acknowledged
WHO    VILLAGER — what the player reads after pressing "That's a long road to walk."
       spoken on: conversations.topic.origin.open.respond, button `say_that_is_a_long_road`
       leaves the player on: conversations.topic.origin.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.open.acknowledged`: the villager accepts. Subject `origin.where_from`, polarity `mixed`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.open.acknowledged/1   [115 chars]
    en  It was, and the walking was the easy half. The arriving somewhere that already has all its people is the hard half.
    >>  ............................................
    pt  Foi, e caminhar era a metade fácil. Chegar num lugar que já tem toda a sua gente é a metade difícil.
    >>  ............................................
  dialogue.conversations.origin.open.acknowledged/2   [73 chars]
    en  Long enough that I stopped counting the days about a third of the way in.
    >>  ............................................
    pt  Longa o bastante para eu parar de contar os dias lá pelo primeiro terço.
    >>  ............................................
  dialogue.conversations.origin.open.acknowledged/3   [107 chars]
    en  Thank you. Half the people here have never been further than the next valley and it does not occur to them.
    >>  ............................................
    pt  Obrigada. Quase todo mundo aqui nunca passou do vale seguinte e nem lhes ocorre pensar nisso.
    >>  ............................................
```


### Button `drop_the_subject` — "Everyone came from somewhere."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `origin.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.origin.open.drop_the_subject` — accepted phrasings: "everyone came from somewhere"; "everyone came from somewhere"; "everybody is from somewhere else"
  - the message must contain one of: `everyone`, `everybody`
  - scored words: `everyone`(1.8), `everybody`(1.8), `came`(0.8), `from`(0.8), `somewhere`(0.8), `else`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.origin.open.respond.drop_the_subject
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.open.respond.drop_the_subject   [29 chars]
    en  Everyone came from somewhere.
    >>  ............................................
    pt  Todo mundo veio de algum lugar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.origin.dismissed`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `origin.where_from`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.origin.open.closed
WHO    VILLAGER — what the player reads after pressing "Everyone came from somewhere."
       spoken on: conversations.topic.origin.open.respond, button `drop_the_subject`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.open.closed`: the villager qualifys. Subject `origin.where_from`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.open.closed/1   [103 chars]
    en  They did. Some of us came from further, and that turns out to matter rather more than I expected it to.
    >>  ............................................
    pt  Vieram. Alguns de nós vieram de mais longe, e isso importa bem mais do que eu esperava.
    >>  ............................................
  dialogue.conversations.origin.open.closed/2   [111 chars]
    en  True. It is also the only part of me that nobody in this village knows, so I mention it perhaps twice a decade.
    >>  ............................................
    pt  Verdade. Também é a única parte de mim que ninguém nesta vila conhece, então eu menciono umas duas vezes por década.
    >>  ............................................
  dialogue.conversations.origin.open.closed/3   [44 chars]
    en  Right. I shall keep the map to myself, then.
    >>  ............................................
    pt  Certo. Guardo o mapa para mim, então.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `origin.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.origin.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.origin.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.origin.open.respond.leave   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.origin.open.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.scene.leaving`: the villager accepts. Subject `origin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.origin.followup / leave; conversations.scene.origin.the_season_that_smells_like_it.respond / leave; conversations.scene.origin.whether_this_is_home.respond / leave; conversations.topic.origin.more.respond / leave
```

> Written out in full under **`conversations.scene.origin.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

