# Topic: firstmet

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `firstmet` |
| Opened from | question `conversations.us`, button `firstmet` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.us` |
| Ages that can reach it | adult |
| Stance families it must offer | `self_disclosure`, `humor`, `curiosity`, `dismissal`, `exit` |
| Narrative arc | `us`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.us`, which is written out in **topic-us.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.us.firstmet
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.us
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-us*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.us.firstmet   [21 chars]
    en  Remember when we met?
    >>  ............................................
    pt  Lembra quando a gente se conheceu?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.firstmet.followup`](#conversations-scene-firstmet-followup)
- [`conversations.scene.firstmet.long_enough_to_be_a_story.respond`](#conversations-scene-firstmet-long-enough-to-be-a-story-respond)
- [`conversations.scene.firstmet.recent_and_plain.respond`](#conversations-scene-firstmet-recent-and-plain-respond)
- [`conversations.topic.firstmet.brushed.followup`](#conversations-topic-firstmet-brushed-followup)
- [`conversations.topic.firstmet.disagreement`](#conversations-topic-firstmet-disagreement)
- [`conversations.topic.firstmet.followup`](#conversations-topic-firstmet-followup)
- [`conversations.topic.firstmet.impression`](#conversations-topic-firstmet-impression)
- [`conversations.topic.firstmet.respond`](#conversations-topic-firstmet-respond)

---

## `conversations.scene.firstmet.followup`

**Reached from 4 route(s):** `conversations.scene.firstmet.long_enough_to_be_a_story.respond` / `correct_the_record`; `conversations.scene.firstmet.long_enough_to_be_a_story.respond` / `ask_for_the_good_line`; `conversations.scene.firstmet.recent_and_plain.respond` / `ask_what_they_thought`; `conversations.scene.firstmet.recent_and_plain.respond` / `say_you_remember_it_too`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.firstmet.long_enough_to_be_a_story.conceded` — e.g. "I expect it is not. Nine tellings is enough to sand the corners off anything, and I have enjoyed every one of them."
- `conversations.scene.firstmet.long_enough_to_be_a_story.delighted` — e.g. "It is better in the telling and I am not going to spoil it by delivering it flat in a doorway."
- `conversations.scene.firstmet.recent_and_plain.answered` — e.g. "That you were listening, which is rarer than being friendly and lasts about four times as long."
- `conversations.scene.firstmet.recent_and_plain.pleased` — e.g. "Good. In about a year we will both remember it differently and it will be nobody's fault."


```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.firstmet.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.firstmet.followup   [25 chars]
    en  Anything else about then?
    >>  ............................................
    pt  Mais alguma coisa sobre aquela época?
    >>  ............................................
```


### Button `leave` — "We'll leave the rest of it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:firstmet.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.followup.leave   [27 chars]
    en  We'll leave the rest of it.
    >>  ............................................
    pt  Deixamos o resto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave the rest of it."
       spoken on: conversations.scene.firstmet.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.scene.leaving`: the villager accepts. Subject `firstmet.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.firstmet.long_enough_to_be_a_story.respond / leave; conversations.scene.firstmet.recent_and_plain.respond / leave
```

```text
  dialogue.conversations.scene.firstmet.leaving/1   [33 chars]
    en  That is how it went, near enough.
    >>  ............................................
    pt  Foi mais ou menos assim.
    >>  ............................................
  dialogue.conversations.scene.firstmet.leaving/2   [27 chars]
    en  Right. A long time ago now.
    >>  ............................................
    pt  Certo. Faz tempo já.
    >>  ............................................
  dialogue.conversations.scene.firstmet.leaving/3   [21 chars]
    en  Anyway. You are here.
    >>  ............................................
    pt  Enfim. Você está aqui.
    >>  ............................................
```

---


## `conversations.scene.firstmet.long_enough_to_be_a_story.respond`

**Reached from 1 route(s):** `conversations.us` / `firstmet`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.firstmet.long_enough_to_be_a_story` — e.g. "I have told that story about nine times and I am fairly sure it has improved, which means it is no longer entirely true."


```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.firstmet.long_enough_to_be_a_story.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond   [15 chars]
    en  The first time.
    >>  ............................................
    pt  A primeira vez.
    >>  ............................................
```


### Button `correct_the_record` — "My memory of it differs."

*stance family `candor` · tone `playful` · outcome `engaged` · answers the beat(s) `firstmet.long_enough_to_be_a_story.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.firstmet.long_enough_to_be_a_story.correct_the_record` — accepted phrasings: "my memory of it differs"; "my memory of it differs"; "i recall it going another way"
  - the message must contain one of: `memory`, `recall`, `differs`
  - scored words: `memory`(1.8), `recall`(1.8), `differs`(1.8), `going`(0.8), `another`(0.8), `way`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond.correct_the_record
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.long_enough_to_be_a_story.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond.correct_the_record   [24 chars]
    en  My memory of it differs.
    >>  ............................................
    pt  Minha memória disso é outra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `firstmet.retold`)_
- Does: session `turn`
- Then opens: `conversations.scene.firstmet.followup`
- …where the player's next choices will be: "We'll leave the rest of it."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.conceded
WHO    VILLAGER — what the player reads after pressing "My memory of it differs."
       spoken on: conversations.scene.firstmet.long_enough_to_be_a_story.respond, button `correct_the_record`
       leaves the player on: conversations.scene.firstmet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.long_enough_to_be_a_story.open.conceded`: the villager accepts. Subject `firstmet.retold`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:firstmet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.conceded/1   [115 chars]
    en  I expect it is not. Nine tellings is enough to sand the corners off anything, and I have enjoyed every one of them.
    >>  ............................................
    pt  Imagino que não. Nove narrativas bastam para lixar os cantos de qualquer coisa, e eu gostei de todas as nove.
    >>  ............................................
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.conceded/2   [108 chars]
    en  Then tell me yours and I shall adopt whichever is better, which is exactly how the current version happened.
    >>  ............................................
    pt  Então me conte a sua e eu adoto a melhor, que é exatamente como a versão atual apareceu.
    >>  ............................................
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.conceded/3   [113 chars]
    en  Two people and one afternoon and two entirely different afternoons. That is the whole trouble with a good memory.
    >>  ............................................
    pt  Duas pessoas e uma tarde e duas tardes completamente diferentes. É esse o problema inteiro de uma boa memória.
    >>  ............................................
```


### Button `ask_for_the_good_line` — "Let's hear the good line."

*stance family `curiosity` · tone `playful` · outcome `appreciated` · answers the beat(s) `firstmet.long_enough_to_be_a_story.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.firstmet.long_enough_to_be_a_story.ask_for_the_good_line` — accepted phrasings: "lets hear the good line"; "let us hear the good line"; "give me the good line then"
  - the message must contain one of: `line`, `hear`
  - scored words: `line`(1.8), `hear`(1.8), `lets`(0.8), `good`(0.8), `let`(0.8), `give`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond.ask_for_the_good_line
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.long_enough_to_be_a_story.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond.ask_for_the_good_line   [25 chars]
    en  Let's hear the good line.
    >>  ............................................
    pt  Quero ouvir a boa frase.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `firstmet.retold`)_
- Does: session `turn`
- Then opens: `conversations.scene.firstmet.followup`
- …where the player's next choices will be: "We'll leave the rest of it."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.delighted
WHO    VILLAGER — what the player reads after pressing "Let's hear the good line."
       spoken on: conversations.scene.firstmet.long_enough_to_be_a_story.respond, button `ask_for_the_good_line`
       leaves the player on: conversations.scene.firstmet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.long_enough_to_be_a_story.open.delighted`: the villager celebrates. Subject `firstmet.retold`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:firstmet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.delighted/1   [94 chars]
    en  It is better in the telling and I am not going to spoil it by delivering it flat in a doorway.
    >>  ............................................
    pt  É melhor na narração e eu não vou estragar entregando sem graça num vão de porta.
    >>  ............................................
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.delighted/2   [101 chars]
    en  Come to the inn on a Saturday and you shall have the whole thing with the pauses in the right places.
    >>  ............................................
    pt  Apareça na estalagem num sábado e você recebe a coisa toda com as pausas nos lugares certos.
    >>  ............................................
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.delighted/3   [119 chars]
    en  I will give you the line and you must promise to look surprised, because four people already have and it is going well.
    >>  ............................................
    pt  Eu te dou a frase e você promete parecer surpreso, porque quatro pessoas já pareceram e está indo bem.
    >>  ............................................
```


### Button `leave` — "Good to remember it."

*stance family `exit` · tone `plain` · answers the beat(s) `firstmet.long_enough_to_be_a_story.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.long_enough_to_be_a_story.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story.respond.leave   [20 chars]
    en  Good to remember it.
    >>  ............................................
    pt  Bom lembrar disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.leaving
WHO    VILLAGER — what the player reads after pressing "Good to remember it."
       spoken on: conversations.scene.firstmet.long_enough_to_be_a_story.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.scene.leaving`: the villager accepts. Subject `firstmet.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.firstmet.followup / leave; conversations.scene.firstmet.recent_and_plain.respond / leave
```

> Written out in full under **`conversations.scene.firstmet.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.firstmet.recent_and_plain.respond`

**Reached from 1 route(s):** `conversations.us` / `firstmet`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.firstmet.recent_and_plain` — e.g. "You came through the gate and asked a sensible question, which put you ahead of about half the people who arrive."


```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.firstmet.recent_and_plain.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain.respond   [12 chars]
    en  When we met.
    >>  ............................................
    pt  Quando nos conhecemos.
    >>  ............................................
```


### Button `ask_what_they_thought` — "What did you make of me?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `firstmet.recent_and_plain.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.firstmet.recent_and_plain.ask_what_they_thought` — accepted phrasings: "what did you make of me"; "what did you make of me"; "what was your first impression"
  - the message must contain one of: `make`, `impression`
  - scored words: `make`(1.8), `impression`(1.8), `first`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain.respond.ask_what_they_thought
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.recent_and_plain.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain.respond.ask_what_they_thought   [24 chars]
    en  What did you make of me?
    >>  ............................................
    pt  O que você achou de mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `firstmet.recent`)_
- Does: session `turn`
- Then opens: `conversations.scene.firstmet.followup`
- …where the player's next choices will be: "We'll leave the rest of it."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain.answered
WHO    VILLAGER — what the player reads after pressing "What did you make of me?"
       spoken on: conversations.scene.firstmet.recent_and_plain.respond, button `ask_what_they_thought`
       leaves the player on: conversations.scene.firstmet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.recent_and_plain.open.answered`: the villager explains. Subject `firstmet.recent`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:firstmet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain.answered/1   [95 chars]
    en  That you were listening, which is rarer than being friendly and lasts about four times as long.
    >>  ............................................
    pt  Que você estava escutando, o que é mais raro que ser simpático e dura umas quatro vezes mais.
    >>  ............................................
  dialogue.conversations.scene.firstmet.recent_and_plain.answered/2   [109 chars]
    en  Nothing, honestly. I decide about people in the third week and I have told you that so you can hold me to it.
    >>  ............................................
    pt  Nada, sinceramente. Eu decido sobre as pessoas na terceira semana e te contei isso para você poder me cobrar.
    >>  ............................................
  dialogue.conversations.scene.firstmet.recent_and_plain.answered/3   [109 chars]
    en  That you would either be gone by winter or here for years, and I could not tell which, and I still could not.
    >>  ............................................
    pt  Que você ou ia embora até o inverno ou ia ficar anos, e eu não sabia qual, e continuo não sabendo.
    >>  ............................................
```


### Button `say_you_remember_it_too` — "I remember it too."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `firstmet.recent_and_plain.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.firstmet.recent_and_plain.say_you_remember_it_too` — accepted phrasings: "i remember it too"; "i remember it too"; "that stuck with me as well"
  - the message must contain one of: `remember`, `stuck`
  - scored words: `remember`(1.8), `stuck`(1.8), `too`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain.respond.say_you_remember_it_too
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.recent_and_plain.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain.respond.say_you_remember_it_too   [18 chars]
    en  I remember it too.
    >>  ............................................
    pt  Eu também lembro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `firstmet.recent`)_
- Does: session `turn`
- Then opens: `conversations.scene.firstmet.followup`
- …where the player's next choices will be: "We'll leave the rest of it."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain.pleased
WHO    VILLAGER — what the player reads after pressing "I remember it too."
       spoken on: conversations.scene.firstmet.recent_and_plain.respond, button `say_you_remember_it_too`
       leaves the player on: conversations.scene.firstmet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.recent_and_plain.open.pleased`: the villager accepts. Subject `firstmet.recent`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:firstmet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain.pleased/1   [89 chars]
    en  Good. In about a year we will both remember it differently and it will be nobody's fault.
    >>  ............................................
    pt  Ótimo. Daqui a um ano nós dois vamos lembrar diferente e não vai ser culpa de ninguém.
    >>  ............................................
  dialogue.conversations.scene.firstmet.recent_and_plain.pleased/2   [89 chars]
    en  Then it happened, which is more than can be said for most first meetings in this village.
    >>  ............................................
    pt  Então aconteceu, o que já é mais do que se pode dizer da maioria dos primeiros encontros nesta vila.
    >>  ............................................
  dialogue.conversations.scene.firstmet.recent_and_plain.pleased/3   [117 chars]
    en  That is worth something. I have arrived somewhere and been forgotten by the following week, and it is a poor feeling.
    >>  ............................................
    pt  Isso vale alguma coisa. Já cheguei em algum lugar e fui esquecida na semana seguinte, e é uma sensação ruim.
    >>  ............................................
```


### Button `leave` — "Good to remember it."

*stance family `exit` · tone `plain` · answers the beat(s) `firstmet.recent_and_plain.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.firstmet.recent_and_plain.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain.respond.leave   [20 chars]
    en  Good to remember it.
    >>  ............................................
    pt  Bom lembrar disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.leaving
WHO    VILLAGER — what the player reads after pressing "Good to remember it."
       spoken on: conversations.scene.firstmet.recent_and_plain.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.scene.leaving`: the villager accepts. Subject `firstmet.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.firstmet.followup / leave; conversations.scene.firstmet.long_enough_to_be_a_story.respond / leave
```

> Written out in full under **`conversations.scene.firstmet.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.firstmet.brushed.followup`

**Reached from 1 route(s):** `conversations.topic.firstmet.respond` / `brush_off`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.firstmet.brush_off` — e.g. "...You don't remember it. Right."


```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.brushed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.firstmet.brushed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.firstmet.brushed.followup   [11 chars]
    en  Well. I do.
    >>  ............................................
    pt  Bom. Eu lembro.
    >>  ............................................
```


### Button `apologize` — "I do remember. I brushed it aside and I shouldn't have."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `firstmet.brushed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.brushed.apologize` — accepted phrasings: "i do remember. i brushed it aside and i shouldn't have"
  - the message must contain one of: `remember`, `dismissive`, `aside`
  - scored words: `remember`(1.2), `dismissive`(1.5), `aside`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.brushed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.brushed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.brushed.followup.apologize   [55 chars]
    en  I do remember. I brushed it aside and I shouldn't have.
    >>  ............................................
    pt  Eu lembro sim. Eu ignorei e não devia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `firstmet.brushed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.brushed.apologize
WHO    VILLAGER — what the player reads after pressing "I do remember. I brushed it aside and I shouldn't have."
       spoken on: conversations.topic.firstmet.brushed.followup, button `apologize`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.brushed.apologize`: the villager qualifys. Subject `firstmet.memory`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.firstmet.brushed.apologize/1   [52 chars]
    en  ...Then say what you remember and I'll stop sulking.
    >>  ............................................
    pt  ...Então diga do que você lembra e eu paro de emburrar.
    >>  ............................................
  dialogue.conversations.firstmet.brushed.apologize/2   [60 chars]
    en  You brushed aside the day I count from, %1$s. But — alright.
    >>  ............................................
    pt  Você ignorou o dia que eu conto como marco, %1$s. Mas — está bem.
    >>  ............................................
  dialogue.conversations.firstmet.brushed.apologize/3   [53 chars]
    en  Good. I'd have been unbearable about that for a week.
    >>  ............................................
    pt  Bom. Eu ia ficar insuportável com isso por uma semana.
    >>  ............................................
```


### Button `explain` — "I remember it differently, that's all."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `firstmet.brushed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.brushed.explain` — accepted phrasings: "i remember it differently, that's all"
  - the message must contain one of: `differently`, `version`, `recall`
  - scored words: `differently`(1.5), `version`(1.2), `recall`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.brushed.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.brushed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.brushed.followup.explain   [38 chars]
    en  I remember it differently, that's all.
    >>  ............................................
    pt  Eu lembro diferente, só isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `firstmet.brushed.explain`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.brushed.explain
WHO    VILLAGER — what the player reads after pressing "I remember it differently, that's all."
       spoken on: conversations.topic.firstmet.brushed.followup, button `explain`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.brushed.explain`: the villager qualifys. Subject `firstmet.memory`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.firstmet.brushed.explain/1   [64 chars]
    en  ...Differently. Go on, then. I'd like to hear the other version.
    >>  ............................................
    pt  ...Diferente. Então conte. Eu quero ouvir a outra versão.
    >>  ............................................
  dialogue.conversations.firstmet.brushed.explain/2   [68 chars]
    en  Two people, one afternoon, two afternoons. That's how it goes, %1$s.
    >>  ............................................
    pt  Duas pessoas, uma tarde, duas tardes. É assim que funciona, %1$s.
    >>  ............................................
  dialogue.conversations.firstmet.brushed.explain/3   [56 chars]
    en  Then tell me yours and I'll decide which of us is lying.
    >>  ............................................
    pt  Então conte a sua e eu decido qual de nós está mentindo.
    >>  ............................................
```


### Button `leave` — "I'll let it lie."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `firstmet.brushed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.brushed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.brushed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.brushed.followup.leave   [16 chars]
    en  I'll let it lie.
    >>  ............................................
    pt  Vou deixar quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.brushed.leave
WHO    VILLAGER — what the player reads after pressing "I'll let it lie."
       spoken on: conversations.topic.firstmet.brushed.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.brushed.leave`: the villager accepts. Subject `firstmet.memory`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.firstmet.brushed.leave/1   [24 chars]
    en  True enough. Off you go.
    >>  ............................................
    pt  Bem verdade. Pode ir.
    >>  ............................................
  dialogue.conversations.firstmet.brushed.leave/2   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
  dialogue.conversations.firstmet.brushed.leave/3   [3 chars]
    en  Mm.
    >>  ............................................
    pt  Mm.
    >>  ............................................
```

---


## `conversations.topic.firstmet.disagreement`

**Reached from 1 route(s):** `conversations.topic.firstmet.followup` / `another_version`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.firstmet.disagreement` — e.g. "Then one of us has been carrying the wrong afternoon around for years."


```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.disagreement
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.firstmet.disagreement
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.firstmet.disagreement   [26 chars]
    en  So whose afternoon was it?
    >>  ............................................
    pt  Então de quem era a tarde?
    >>  ............................................
```


### Button `call_it_both` — "Let's keep both and not decide."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `firstmet.disagreement`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.disagreement.both` — accepted phrasings: "let us keep both"; "we can keep both versions"; "no need to decide whose it is"
  - the message must contain one of: `both`
  - scored words: `both`(1.2), `decide`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.disagreement.call_it_both
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.disagreement
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.disagreement.call_it_both   [31 chars]
    en  Let's keep both and not decide.
    >>  ............................................
    pt  Vamos ficar com as duas e não decidir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `firstmet.disagreement.both`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `firstmet.disagreement.both`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.disagreement.both
WHO    VILLAGER — what the player reads after pressing "Let's keep both and not decide."
       spoken on: conversations.topic.firstmet.disagreement, button `call_it_both`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.disagreement.both`: the villager accepts. Subject `firstmet.disagreement`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.firstmet.disagreement.both/1   [60 chars]
    en  Both, then. It'll be a better story with two endings anyway.
    >>  ............................................
    pt  As duas, então. Vai ser uma história melhor com dois finais.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement.both/2   [46 chars]
    en  That suits me. I'd not enjoy winning this one.
    >>  ............................................
    pt  Isso me serve. Eu não ia gostar de ganhar essa.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement.both/3   [73 chars]
    en  Agreed. We'll each tell ours and let the children decide in thirty years.
    >>  ............................................
    pt  Combinado. Cada um conta a sua e as crianças decidem em trinta anos.
    >>  ............................................
```


### Button `it_was_the_other_way` — "No — it was the other way round."

*stance family `self_disclosure` · tone `plain` · outcome `qualified` · answers the beat(s) `firstmet.disagreement`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.disagreement.corrected` — accepted phrasings: "it was the other way round"; "no you have it backwards"; "it happened the other way"
  - the message must contain one of: `round`
  - scored words: `other`(0.6), `round`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.disagreement.it_was_the_other_way
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.disagreement
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.disagreement.it_was_the_other_way   [32 chars]
    en  No — it was the other way round.
    >>  ............................................
    pt  Não — foi o contrário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1, respect +1  _(recorded under topic `firstmet.disagreement.corrected`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.disagreement.corrected
WHO    VILLAGER — what the player reads after pressing "No — it was the other way round."
       spoken on: conversations.topic.firstmet.disagreement, button `it_was_the_other_way`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.disagreement.corrected`: the villager qualifys. Subject `firstmet.disagreement`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.firstmet.disagreement.corrected/1   [76 chars]
    en  ...It was, wasn't it. I've had that backwards for years and told it proudly.
    >>  ............................................
    pt  ...Foi mesmo, não foi. Passei anos com isso invertido e contando com orgulho.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement.corrected/2   [66 chars]
    en  You're sure? Then I've been thanking myself for something you did.
    >>  ............................................
    pt  Você tem certeza? Então venho me agradecendo por algo que você fez.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement.corrected/3   [67 chars]
    en  Hm. I'll take that under consideration and go on telling it my way.
    >>  ............................................
    pt  Hm. Vou levar isso em consideração e continuar contando do meu jeito.
    >>  ............................................
```


### Button `leave` — "It hardly matters now."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `firstmet.disagreement` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.disagreement.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.disagreement
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.disagreement.leave   [22 chars]
    en  It hardly matters now.
    >>  ............................................
    pt  Agora mal importa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.disagreement.leave
WHO    VILLAGER — what the player reads after pressing "It hardly matters now."
       spoken on: conversations.topic.firstmet.disagreement, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.disagreement.leave`: the villager accepts. Subject `firstmet.disagreement`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.firstmet.disagreement.leave/1   [23 chars]
    en  It doesn't. Off you go.
    >>  ............................................
    pt  Não importa. Pode ir.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement.leave/2   [16 chars]
    en  No. Go on, then.
    >>  ............................................
    pt  Não. Então vá.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement.leave/3   [18 chars]
    en  True enough, %1$s.
    >>  ............................................
    pt  Bem verdade, %1$s.
    >>  ............................................
```

---


## `conversations.topic.firstmet.followup`

**Reached from 2 route(s):** `conversations.topic.firstmet.respond` / `share_own`; `conversations.topic.firstmet.respond` / `tease`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.firstmet.share_own` — e.g. "You remember it differently to me. I love that."
- `conversations.us.firstmet.tease` — e.g. "I WAS a state. You were no better, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.firstmet.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.firstmet.followup   [21 chars]
    en  Funny, thinking back.
    >>  ............................................
    pt  Engraçado, olhando para trás.
    >>  ............................................
```


### Button `ask_stood_out` — "What stood out to you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `us.firstmet.share_own.to.firstmet`, `us.firstmet.tease.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.followup.ask_stood_out` — accepted phrasings: "what stood out to you"; "what stood out about me"; "what did you notice first"
  - the message must contain one of: `stood`
  - scored words: `noticed`(0.6), `stood`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.followup.ask_stood_out
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.followup.ask_stood_out   [22 chars]
    en  What stood out to you?
    >>  ............................................
    pt  O que te chamou atenção?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.firstmet.ask_stood_out`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, familiarity +3  _(recorded under topic `us.firstmet.ask_stood_out`)_
- Does: arc `us` — advance to stage 1
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.ask_stood_out
WHO    VILLAGER — what the player reads after pressing "What stood out to you?"
       spoken on: conversations.topic.firstmet.followup, button `ask_stood_out`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.ask_stood_out.to.us`: the villager accepts. Subject `us`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.ask_stood_out/1   [56 chars]
    en  ...You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você. Não algo que você disse. Só que você ficou.
    >>  ............................................
  dialogue.conversations.us.firstmet.ask_stood_out/2   [74 chars]
    en  The way you spoke to the baker, actually. Kindness when nobody's counting.
    >>  ............................................
    pt  O jeito que você falou com a padeira, na verdade. Gentileza quando ninguém conta.
    >>  ............................................
  dialogue.conversations.us.firstmet.ask_stood_out/3   [57 chars]
    en  I'll not tell you. You'll only get insufferable about it.
    >>  ............................................
    pt  Não vou te contar. Você só vai ficar insuportável.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did, %1$s. Not anything you said. Just that you stayed, and nobody stays.
    >>  ............................................
    pt  ...Você marcou, %1$s. Não foi nada que você disse. Foi que você ficou, e ninguém fica.
    >>  ............................................
  anxious.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. I'd been counting on you leaving.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Eu contava que você fosse embora.
    >>  ............................................
  anxious.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. I'd not remember a sentence. I remember you still being there.
    >>  ............................................
    pt  Não foi algo que você disse. Eu não lembraria de uma frase. Lembro de você ainda ali.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed, and you've kept staying.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou, e continuou ficando.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. That's held up over the years since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Isso se manteve ao longo dos anos.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. Words go. The staying is what I've still got.
    >>  ............................................
    pt  Não foi algo que você disse. Palavras vão. O ficar é o que eu ainda tenho.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when the conversation stopped being easy. That was it.
    >>  ............................................
    pt  Você ficou quando a conversa parou de ser fácil. Foi isso.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you didn't leave.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você não foi embora.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when the conversation stopped being easy. That was it.
    >>  ............................................
    pt  Você ficou quando a conversa parou de ser fácil. Foi isso.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you didn't leave.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você não foi embora.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did, %1$s. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você marcou, %1$s. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. I've thought about that afternoon since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Penso naquela tarde desde então.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you were still there when I looked up.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você ainda estava lá quando eu olhei.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did, %1$s. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você marcou, %1$s. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. I've thought about that afternoon since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Penso naquela tarde desde então.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you were still there when I looked up.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você ainda estava lá quando eu olhei.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did, %1$s. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você marcou, %1$s. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. I've thought about that afternoon since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Penso naquela tarde desde então.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you were still there when I looked up.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você ainda estava lá quando eu olhei.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did, %1$s. Not anything you said. Just that you stayed, and nobody stays.
    >>  ............................................
    pt  ...Você marcou, %1$s. Não foi nada que você disse. Foi que você ficou, e ninguém fica.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. I'd been counting on you leaving.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Eu contava que você fosse embora.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. I'd not remember a sentence. I remember you still being there.
    >>  ............................................
    pt  Não foi algo que você disse. Eu não lembraria de uma frase. Lembro de você ainda ali.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when the conversation stopped being easy. That was it.
    >>  ............................................
    pt  Você ficou quando a conversa parou de ser fácil. Foi isso.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you didn't leave.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você não foi embora.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when the conversation stopped being easy. That was it.
    >>  ............................................
    pt  Você ficou quando a conversa parou de ser fácil. Foi isso.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. It was that you didn't leave.
    >>  ............................................
    pt  Não foi algo que você disse. Foi que você não foi embora.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed. That was it.
    >>  ............................................
    pt  Você ficou. Foi isso.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said.
    >>  ............................................
    pt  Não foi algo que você disse.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed, and you've kept staying.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou, e continuou ficando.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. That's held up over the years since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Isso se manteve ao longo dos anos.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. Words go. The staying is what I've still got.
    >>  ............................................
    pt  Não foi algo que você disse. Palavras vão. O ficar é o que eu ainda tenho.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed. That was it.
    >>  ............................................
    pt  Você ficou. Foi isso.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said.
    >>  ............................................
    pt  Não foi algo que você disse.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed, and you've kept staying.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou, e continuou ficando.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. That's held up over the years since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Isso se manteve ao longo dos anos.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. Words go. The staying is what I've still got.
    >>  ............................................
    pt  Não foi algo que você disse. Palavras vão. O ficar é o que eu ainda tenho.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did! Not anything you said. Just that you stayed, which nobody does.
    >>  ............................................
    pt  Você marcou! Não foi nada que você disse. Foi que você ficou, o que ninguém faz.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being an easy conversation. That's the whole of it.
    >>  ............................................
    pt  Você ficou quando parou de ser conversa fácil. É tudo.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said! It was the not-leaving. Very underrated skill.
    >>  ............................................
    pt  Não foi algo que você disse! Foi o não ir embora. Habilidade muito subestimada.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did! Not anything you said. Just that you stayed, which nobody does.
    >>  ............................................
    pt  Você marcou! Não foi nada que você disse. Foi que você ficou, o que ninguém faz.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being an easy conversation. That's the whole of it.
    >>  ............................................
    pt  Você ficou quando parou de ser conversa fácil. É tudo.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said! It was the not-leaving. Very underrated skill.
    >>  ............................................
    pt  Não foi algo que você disse! Foi o não ir embora. Habilidade muito subestimada.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did. Not anything you said. Just that you stayed, and you've kept staying.
    >>  ............................................
    pt  Você marcou. Não foi nada que você disse. Foi que você ficou, e continuou ficando.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. That's held up over the years since.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Isso se manteve ao longo dos anos.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. Words go. The staying is what I've still got.
    >>  ............................................
    pt  Não foi algo que você disse. Palavras vão. O ficar é o que eu ainda tenho.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did, %1$s. Not anything you said. Just that you stayed, and nobody stays.
    >>  ............................................
    pt  ...Você marcou, %1$s. Não foi nada que você disse. Foi que você ficou, e ninguém fica.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being easy. I'd been counting on you leaving.
    >>  ............................................
    pt  Você ficou quando parou de ser fácil. Eu contava que você fosse embora.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said. I'd not remember a sentence. I remember you still being there.
    >>  ............................................
    pt  Não foi algo que você disse. Eu não lembraria de uma frase. Lembro de você ainda ali.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  ...You did. Not anything you said. Just that you stayed.
    >>  ............................................
    pt  ...Você marcou. Não foi nada que você disse. Foi que você ficou.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed. That was it.
    >>  ............................................
    pt  Você ficou. Foi isso.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said.
    >>  ............................................
    pt  Não foi algo que você disse.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did! Not anything you said. Just that you stayed, which nobody does.
    >>  ............................................
    pt  Você marcou! Não foi nada que você disse. Foi que você ficou, o que ninguém faz.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being an easy conversation. That's the whole of it.
    >>  ............................................
    pt  Você ficou quando parou de ser conversa fácil. É tudo.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said! It was the not-leaving. Very underrated skill.
    >>  ............................................
    pt  Não foi algo que você disse! Foi o não ir embora. Habilidade muito subestimada.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.ask_stood_out/1
    en  You did! Not anything you said. Just that you stayed, which nobody does.
    >>  ............................................
    pt  Você marcou! Não foi nada que você disse. Foi que você ficou, o que ninguém faz.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.ask_stood_out/2
    en  You stayed when it stopped being an easy conversation. That's the whole of it.
    >>  ............................................
    pt  Você ficou quando parou de ser conversa fácil. É tudo.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.ask_stood_out/3
    en  It wasn't a thing you said! It was the not-leaving. Very underrated skill.
    >>  ............................................
    pt  Não foi algo que você disse! Foi o não ir embora. Habilidade muito subestimada.
    >>  ............................................
```

</details>


### Button `correct_gently` — "It wasn't quite like that."

*stance family `candor` · tone `gentle` · answers the beat(s) `us.firstmet.share_own.to.firstmet`, `us.firstmet.tease.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.followup.correct_gently` — accepted phrasings: "it was not quite like that"; "that is not quite right"; "it did not quite happen that way"
  - the message must contain one of: `quite`
  - scored words: `like`(0.3), `quite`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.followup.correct_gently
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.followup.correct_gently   [26 chars]
    en  It wasn't quite like that.
    >>  ............................................
    pt  Não foi bem assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.firstmet.correct_gently`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — familiarity +3  _(recorded under topic `us.firstmet.correct_gently`)_
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.correct_gently
WHO    VILLAGER — what the player reads after pressing "It wasn't quite like that."
       spoken on: conversations.topic.firstmet.followup, button `correct_gently`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.correct_gently.to.us`: the villager accepts. Subject `us`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.correct_gently/1   [55 chars]
    en  ...Was it not? Huh. I've told it wrong for years, then.
    >>  ............................................
    pt  ...Não foi? Hm. Então contei errado por anos.
    >>  ............................................
  dialogue.conversations.us.firstmet.correct_gently/2   [50 chars]
    en  You'd know better than me. You were the sober one.
    >>  ............................................
    pt  Você sabe melhor que eu. Você era o sóbrio.
    >>  ............................................
  dialogue.conversations.us.firstmet.correct_gently/3   [31 chars]
    en  Maybe. I like my version, mind.
    >>  ............................................
    pt  Talvez. Mas eu gosto da minha versão.
    >>  ............................................
```


### Button `dismiss` — "It was a long time ago."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `us.firstmet.share_own.to.firstmet`, `us.firstmet.tease.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.followup.dismiss` — accepted phrasings: "it was a long time ago"; "that was years ago now"; "all that was a long while back"
  - the message must contain one of: `ago`
  - scored words: `ago`(1.5), `long`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.followup.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.followup.dismiss   [23 chars]
    en  It was a long time ago.
    >>  ............................................
    pt  Foi há muito tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `us.firstmet.dismiss`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `us.firstmet.dismiss`)_
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.dismiss
WHO    VILLAGER — what the player reads after pressing "It was a long time ago."
       spoken on: conversations.topic.firstmet.followup, button `dismiss`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.dismiss.to.us`: the villager qualifys. Subject `us`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.dismiss/1   [46 chars]
    en  It was. It's still the day I count from, %1$s.
    >>  ............................................
    pt  Foi. Mas ainda é o dia de onde eu conto, %1$s.
    >>  ............................................
  dialogue.conversations.us.firstmet.dismiss/2   [32 chars]
    en  Long ago, aye. Not gone, though.
    >>  ............................................
    pt  Há muito tempo, é. Mas não passou.
    >>  ............................................
  dialogue.conversations.us.firstmet.dismiss/3   [21 chars]
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa para lá.
    >>  ............................................
```


### Button `another_version` — "That isn't the version I have."

*stance family `respectful_disagreement` · tone `gentle` · outcome `engaged` · answers the beat(s) `us.firstmet.share_own.to.firstmet`, `us.firstmet.tease.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.disagreement` — accepted phrasings: "that is not the version i have"; "my version is different"; "mine goes another way"
  - the message must contain one of: `version`
  - scored words: `mine`(0.6), `version`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.followup.another_version
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.followup.another_version   [30 chars]
    en  That isn't the version I have.
    >>  ............................................
    pt  Essa não é a versão que eu tenho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.firstmet.disagreement`
- …where the player's next choices will be: "Let's keep both and not decide." | "No — it was the other way round." | "It hardly matters now."

```text
POOL   dialogue key: dialogue.conversations.firstmet.disagreement
WHO    VILLAGER — what the player reads after pressing "That isn't the version I have."
       spoken on: conversations.topic.firstmet.followup, button `another_version`
       leaves the player on: conversations.topic.firstmet.disagreement
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.disagreement`: the villager qualifys. Subject `firstmet.disagreement`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, self_disclosure, exit
```

```text
  dialogue.conversations.firstmet.disagreement/1   [70 chars]
    en  Then one of us has been carrying the wrong afternoon around for years.
    >>  ............................................
    pt  Então um de nós carrega a tarde errada há anos.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement/2   [74 chars]
    en  It wouldn't be. I've told mine so often it's more telling than memory now.
    >>  ............................................
    pt  Não seria. Contei tanto a minha que agora é mais contação do que memória.
    >>  ............................................
  dialogue.conversations.firstmet.disagreement/3   [60 chars]
    en  Go on, then. I'd rather have yours than keep polishing mine.
    >>  ............................................
    pt  Então conte. Prefiro ter a sua a continuar polindo a minha.
    >>  ............................................
```


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `us.firstmet.share_own.to.firstmet`, `us.firstmet.tease.to.firstmet` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.followup.leave   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.topic.firstmet.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.firstmet.respond / leave
```

```text
  dialogue.conversations.us.firstmet.leave/1   [32 chars]
    en  Aye. Nice to think back, though.
    >>  ............................................
    pt  É. Mas é bom lembrar.
    >>  ............................................
  dialogue.conversations.us.firstmet.leave/2   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
  dialogue.conversations.us.firstmet.leave/3   [13 chars]
    en  Right. Later.
    >>  ............................................
    pt  Certo. Depois.
    >>  ............................................
```

---


## `conversations.topic.firstmet.impression`

**Reached from 1 route(s):** `conversations.topic.firstmet.respond` / `ask_impression`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.firstmet.impression` — e.g. "That you'd not last the winter. I've never been so pleased to be wrong."


```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.impression
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.firstmet.impression
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.firstmet.impression   [39 chars]
    en  That's what I had of you, at the start.
    >>  ............................................
    pt  Era isso que eu tinha de você, no começo.
    >>  ............................................
```


### Button `and_now` — "And now?"

*stance family `curiosity` · tone `gentle` · outcome `appreciated` · answers the beat(s) `firstmet.impression`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.impression.now` — accepted phrasings: "and now"; "and what about now"; "has that changed"
  - the message must contain one of: `now`
  - scored words: `now`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.impression.and_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.impression
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.impression.and_now   [8 chars]
    en  And now?
    >>  ............................................
    pt  E agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `firstmet.impression.now`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +2  _(recorded under topic `firstmet.impression.now`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.impression.now
WHO    VILLAGER — what the player reads after pressing "And now?"
       spoken on: conversations.topic.firstmet.impression, button `and_now`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.impression.now`: the villager discloses. Subject `firstmet.impression`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.firstmet.impression.now/1   [72 chars]
    en  Now I'd not want the version of this village that didn't have you in it.
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você nela.
    >>  ............................................
  dialogue.conversations.firstmet.impression.now/2   [72 chars]
    en  Now I know you're quiet because you're listening, which is worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é pior pra mim.
    >>  ............................................
  dialogue.conversations.firstmet.impression.now/3   [69 chars]
    en  Now? Now I've stopped forming an opinion and started just being glad.
    >>  ............................................
    pt  Agora? Agora parei de formar opinião e passei só a ficar contente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want a version of this village without you, and it frightens me to have said it.
    >>  ............................................
    pt  Agora eu não ia querer uma versão deste vilarejo sem você, e me assusta ter dito isso.
    >>  ............................................
  anxious.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. I've thought about that more than I should.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Pensei nisso mais do que deveria.
    >>  ............................................
  anxious.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion. It turns out being glad is simpler and harder.
    >>  ............................................
    pt  Agora parei de formar opinião. Acontece que ficar contente é mais simples e mais difícil.
    >>  ............................................
  athletic.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you. I've watched enough people arrive to know.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você. Já vi gente chegar o bastante pra saber.
    >>  ............................................
  athletic.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. It took me a season to work that out.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Levei uma estação pra descobrir.
    >>  ............................................
  athletic.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming opinions about people. At my age, being glad is the better use.
    >>  ............................................
    pt  Agora parei de formar opinião sobre as pessoas. Na minha idade, ficar contente rende mais.
    >>  ............................................
  confident.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want the version of this village that didn't have you in it.
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você nela.
    >>  ............................................
  confident.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é pior pra mim.
    >>  ............................................
  confident.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started just being glad.
    >>  ............................................
    pt  Agora parei de formar opinião e passei só a ficar contente.
    >>  ............................................
  crabby.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want the version of this village that didn't have you in it.
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você nela.
    >>  ............................................
  crabby.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é pior pra mim.
    >>  ............................................
  crabby.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started just being glad.
    >>  ............................................
    pt  Agora parei de formar opinião e passei só a ficar contente.
    >>  ............................................
  extroverted.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want a version of this village without you in it, %1$s. Plainly said.
    >>  ............................................
    pt  Agora eu não ia querer uma versão deste vilarejo sem você, %1$s. Dito sem rodeios.
    >>  ............................................
  extroverted.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. I've been careful with my words since.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Ando cuidadoso com as palavras desde então.
    >>  ............................................
  extroverted.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started being glad. That took me a year.
    >>  ............................................
    pt  Agora parei de formar opinião e passei a ficar contente. Levei um ano.
    >>  ............................................
  flirty.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want a version of this village without you in it, %1$s. Plainly said.
    >>  ............................................
    pt  Agora eu não ia querer uma versão deste vilarejo sem você, %1$s. Dito sem rodeios.
    >>  ............................................
  flirty.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. I've been careful with my words since.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Ando cuidadoso com as palavras desde então.
    >>  ............................................
  flirty.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started being glad. That took me a year.
    >>  ............................................
    pt  Agora parei de formar opinião e passei a ficar contente. Levei um ano.
    >>  ............................................
  friendly.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want a version of this village without you in it, %1$s. Plainly said.
    >>  ............................................
    pt  Agora eu não ia querer uma versão deste vilarejo sem você, %1$s. Dito sem rodeios.
    >>  ............................................
  friendly.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. I've been careful with my words since.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Ando cuidadoso com as palavras desde então.
    >>  ............................................
  friendly.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started being glad. That took me a year.
    >>  ............................................
    pt  Agora parei de formar opinião e passei a ficar contente. Levei um ano.
    >>  ............................................
  gloomy.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want a version of this village without you, and it frightens me to have said it.
    >>  ............................................
    pt  Agora eu não ia querer uma versão deste vilarejo sem você, e me assusta ter dito isso.
    >>  ............................................
  gloomy.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. I've thought about that more than I should.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Pensei nisso mais do que deveria.
    >>  ............................................
  gloomy.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion. It turns out being glad is simpler and harder.
    >>  ............................................
    pt  Agora parei de formar opinião. Acontece que ficar contente é mais simples e mais difícil.
    >>  ............................................
  greedy.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want the version of this village that didn't have you in it.
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você nela.
    >>  ............................................
  greedy.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é pior pra mim.
    >>  ............................................
  greedy.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started just being glad.
    >>  ............................................
    pt  Agora parei de formar opinião e passei só a ficar contente.
    >>  ............................................
  grumpy.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want the version of this village that didn't have you in it.
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você nela.
    >>  ............................................
  grumpy.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é pior pra mim.
    >>  ............................................
  grumpy.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion and started just being glad.
    >>  ............................................
    pt  Agora parei de formar opinião e passei só a ficar contente.
    >>  ............................................
  introverted.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you in it.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você.
    >>  ............................................
  introverted.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're listening.
    >>  ............................................
    pt  Agora sei que você escuta.
    >>  ............................................
  introverted.dialogue.conversations.firstmet.impression.now/3
    en  Now I'm just glad.
    >>  ............................................
    pt  Agora só fico contente.
    >>  ............................................
  lazy.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you. I've watched enough people arrive to know.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você. Já vi gente chegar o bastante pra saber.
    >>  ............................................
  lazy.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. It took me a season to work that out.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Levei uma estação pra descobrir.
    >>  ............................................
  lazy.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming opinions about people. At my age, being glad is the better use.
    >>  ............................................
    pt  Agora parei de formar opinião sobre as pessoas. Na minha idade, ficar contente rende mais.
    >>  ............................................
  odd.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you in it.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você.
    >>  ............................................
  odd.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're listening.
    >>  ............................................
    pt  Agora sei que você escuta.
    >>  ............................................
  odd.dialogue.conversations.firstmet.impression.now/3
    en  Now I'm just glad.
    >>  ............................................
    pt  Agora só fico contente.
    >>  ............................................
  peaceful.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you. I've watched enough people arrive to know.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você. Já vi gente chegar o bastante pra saber.
    >>  ............................................
  peaceful.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. It took me a season to work that out.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Levei uma estação pra descobrir.
    >>  ............................................
  peaceful.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming opinions about people. At my age, being glad is the better use.
    >>  ............................................
    pt  Agora parei de formar opinião sobre as pessoas. Na minha idade, ficar contente rende mais.
    >>  ............................................
  peppy.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not have the version of this village without you in it. Imagine the dullness!
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você. Imagine o tédio!
    >>  ............................................
  peppy.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is much worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é bem pior pra mim.
    >>  ............................................
  peppy.dialogue.conversations.firstmet.impression.now/3
    en  Now? I've given up having opinions about you and gone straight to being glad.
    >>  ............................................
    pt  Agora? Desisti de ter opinião sobre você e fui direto pra ficar contente.
    >>  ............................................
  playful.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not have the version of this village without you in it. Imagine the dullness!
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você. Imagine o tédio!
    >>  ............................................
  playful.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is much worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é bem pior pra mim.
    >>  ............................................
  playful.dialogue.conversations.firstmet.impression.now/3
    en  Now? I've given up having opinions about you and gone straight to being glad.
    >>  ............................................
    pt  Agora? Desisti de ter opinião sobre você e fui direto pra ficar contente.
    >>  ............................................
  relaxed.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you. I've watched enough people arrive to know.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você. Já vi gente chegar o bastante pra saber.
    >>  ............................................
  relaxed.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. It took me a season to work that out.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Levei uma estação pra descobrir.
    >>  ............................................
  relaxed.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming opinions about people. At my age, being glad is the better use.
    >>  ............................................
    pt  Agora parei de formar opinião sobre as pessoas. Na minha idade, ficar contente rende mais.
    >>  ............................................
  sensitive.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want a version of this village without you, and it frightens me to have said it.
    >>  ............................................
    pt  Agora eu não ia querer uma versão deste vilarejo sem você, e me assusta ter dito isso.
    >>  ............................................
  sensitive.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening. I've thought about that more than I should.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta. Pensei nisso mais do que deveria.
    >>  ............................................
  sensitive.dialogue.conversations.firstmet.impression.now/3
    en  Now I've stopped forming an opinion. It turns out being glad is simpler and harder.
    >>  ............................................
    pt  Agora parei de formar opinião. Acontece que ficar contente é mais simples e mais difícil.
    >>  ............................................
  shy.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not want this village without you in it.
    >>  ............................................
    pt  Agora eu não ia querer este vilarejo sem você.
    >>  ............................................
  shy.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're listening.
    >>  ............................................
    pt  Agora sei que você escuta.
    >>  ............................................
  shy.dialogue.conversations.firstmet.impression.now/3
    en  Now I'm just glad.
    >>  ............................................
    pt  Agora só fico contente.
    >>  ............................................
  upbeat.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not have the version of this village without you in it. Imagine the dullness!
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você. Imagine o tédio!
    >>  ............................................
  upbeat.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is much worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é bem pior pra mim.
    >>  ............................................
  upbeat.dialogue.conversations.firstmet.impression.now/3
    en  Now? I've given up having opinions about you and gone straight to being glad.
    >>  ............................................
    pt  Agora? Desisti de ter opinião sobre você e fui direto pra ficar contente.
    >>  ............................................
  witty.dialogue.conversations.firstmet.impression.now/1
    en  Now I'd not have the version of this village without you in it. Imagine the dullness!
    >>  ............................................
    pt  Agora eu não ia querer a versão deste vilarejo sem você. Imagine o tédio!
    >>  ............................................
  witty.dialogue.conversations.firstmet.impression.now/2
    en  Now I know you're quiet because you're listening, which is much worse for me.
    >>  ............................................
    pt  Agora sei que você é quieto porque escuta, o que é bem pior pra mim.
    >>  ............................................
  witty.dialogue.conversations.firstmet.impression.now/3
    en  Now? I've given up having opinions about you and gone straight to being glad.
    >>  ............................................
    pt  Agora? Desisti de ter opinião sobre você e fui direto pra ficar contente.
    >>  ............................................
```

</details>


### Button `fair_enough` — "That's fair. I was exactly that."

*stance family `candor` · tone `playful` · outcome `accepted` · answers the beat(s) `firstmet.impression`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.impression.fair` — accepted phrasings: "that is fair i was exactly that"; "you are not wrong about that"; "i was exactly like that"
  - the message must contain one of: `exactly`
  - scored words: `exactly`(1.2), `fair`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.impression.fair_enough
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.impression
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.impression.fair_enough   [32 chars]
    en  That's fair. I was exactly that.
    >>  ............................................
    pt  É justo. Eu era exatamente isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `firstmet.impression.fair`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.impression.fair
WHO    VILLAGER — what the player reads after pressing "That's fair. I was exactly that."
       spoken on: conversations.topic.firstmet.impression, button `fair_enough`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.impression.fair`: the villager accepts. Subject `firstmet.impression`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.firstmet.impression.fair/1   [60 chars]
    en  You were. I'd have said so at the time if I'd had the nerve.
    >>  ............................................
    pt  Você era. Eu teria dito na época se tivesse coragem.
    >>  ............................................
  dialogue.conversations.firstmet.impression.fair/2   [68 chars]
    en  You admit it. That's more than I'd manage about my first month here.
    >>  ............................................
    pt  Você admite. É mais do que eu conseguiria sobre meu primeiro mês aqui.
    >>  ............................................
  dialogue.conversations.firstmet.impression.fair/3   [75 chars]
    en  Quite, you were, and you've the decency to say so, which is the difference.
    >>  ............................................
    pt  Exato, você era, e tem a decência de admitir, que é a diferença.
    >>  ............................................
```


### Button `leave` — "That's a lot to take in."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `firstmet.impression` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.impression.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.impression
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.impression.leave   [24 chars]
    en  That's a lot to take in.
    >>  ............................................
    pt  É muita coisa pra absorver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.firstmet.impression.leave
WHO    VILLAGER — what the player reads after pressing "That's a lot to take in."
       spoken on: conversations.topic.firstmet.impression, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.impression.leave`: the villager accepts. Subject `firstmet.impression`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.firstmet.impression.leave/1   [17 chars]
    en  Take it in, then.
    >>  ............................................
    pt  Então absorva.
    >>  ............................................
  dialogue.conversations.firstmet.impression.leave/2   [13 chars]
    en  It is. Go on.
    >>  ............................................
    pt  É sim. Vá lá.
    >>  ............................................
  dialogue.conversations.firstmet.impression.leave/3   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.firstmet.respond`

**Reached from 3 route(s):** `conversations.us` / `firstmet`; `conversations.us` / `firstmet`; `conversations.us` / `firstmet`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.firstmet.again` — e.g. "We just walked down that road, love. But I never mind the view."
- `conversations.us.firstmet.memory` — e.g. "Of course. You looked completely lost, and I pretended I wasn't staring. Best day %2$s ever had."
- `conversations.us.firstmet.tell` — e.g. "You want the story of how we met? Go on, then. I'll tell it my way."


```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.firstmet.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.firstmet.respond   [25 chars]
    en  That's how I remember it.
    >>  ............................................
    pt  É assim que eu lembro.
    >>  ............................................
```


### Button `share_own` — "Here's what I remember."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `us.firstmet.again.to.firstmet`, `us.firstmet.memory.to.firstmet`, `us.firstmet.tell.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.share_own` — accepted phrasings: "here is what i remember"; "let me tell you what i remember"; "i remember it like this"
  - scored words: `here`(0.5), `mine`(0.8), `remember`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.respond.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.respond.share_own   [23 chars]
    en  Here's what I remember.
    >>  ............................................
    pt  Aqui está o que eu lembro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.firstmet.share_own`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — familiarity +5, warmth +2  _(recorded under topic `us.firstmet.share_own`)_
- Then opens: `conversations.topic.firstmet.followup`
- …where the player's next choices will be: "What stood out to you?" | "It wasn't quite like that." | "It was a long time ago." | "That isn't the version I have." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.share_own
WHO    VILLAGER — what the player reads after pressing "Here's what I remember."
       spoken on: conversations.topic.firstmet.respond, button `share_own`
       leaves the player on: conversations.topic.firstmet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.share_own.to.firstmet`: the villager accepts. Subject `firstmet`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.share_own/1   [47 chars]
    en  You remember it differently to me. I love that.
    >>  ............................................
    pt  Você lembra diferente de mim. Adoro isso.
    >>  ............................................
  dialogue.conversations.us.firstmet.share_own/2   [41 chars]
    en  Go on then — I want to hear your version.
    >>  ............................................
    pt  Vai lá então — quero ouvir a sua versão.
    >>  ............................................
  dialogue.conversations.us.firstmet.share_own/3   [36 chars]
    en  Ha! That's not how I tell it at all.
    >>  ............................................
    pt  Rá! Não é nada assim que eu conto.
    >>  ............................................
```


### Button `tease` — "You were a state, as I recall."

*stance family `humor` · tone `playful` · answers the beat(s) `us.firstmet.again.to.firstmet`, `us.firstmet.memory.to.firstmet`, `us.firstmet.tell.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.tease` — accepted phrasings: "you were a state as i recall"; "you were in a right state"; "you looked a fright"
  - the message must contain one of: `fright`, `recall`, `state`
  - scored words: `fright`(1.2), `recall`(1.0), `state`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.respond.tease
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.respond.tease   [30 chars]
    en  You were a state, as I recall.
    >>  ............................................
    pt  Você estava um caco, se bem me lembro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.firstmet.tease`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `us.firstmet.tease`)_
- Then opens: `conversations.topic.firstmet.followup`
- …where the player's next choices will be: "What stood out to you?" | "It wasn't quite like that." | "It was a long time ago." | "That isn't the version I have." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.tease
WHO    VILLAGER — what the player reads after pressing "You were a state, as I recall."
       spoken on: conversations.topic.firstmet.respond, button `tease`
       leaves the player on: conversations.topic.firstmet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.tease.to.firstmet`: the villager accepts. Subject `firstmet`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.tease/1   [40 chars]
    en  I WAS a state. You were no better, %1$s.
    >>  ............................................
    pt  EU estava um caco. Você não estava melhor, %1$s.
    >>  ............................................
  dialogue.conversations.us.firstmet.tease/2   [56 chars]
    en  Ha! Say that again and I'll tell everyone what YOU said.
    >>  ............................................
    pt  Rá! Diga de novo e eu conto para todo mundo o que VOCÊ disse.
    >>  ............................................
  dialogue.conversations.us.firstmet.tease/3   [27 chars]
    en  Cruel. Accurate, but cruel.
    >>  ............................................
    pt  Cruel. Preciso, mas cruel.
    >>  ............................................
```


### Button `brush_off` — "I don't really remember it."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `us.firstmet.again.to.firstmet`, `us.firstmet.memory.to.firstmet`, `us.firstmet.tell.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.brush_off` — accepted phrasings: "i do not really remember it"; "i barely remember it"; "it has gone from me entirely"
  - scored words: `really`(0.6), `remember`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.respond.brush_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.respond.brush_off   [27 chars]
    en  I don't really remember it.
    >>  ............................................
    pt  Não lembro direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `us.firstmet.brush_off`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +4  _(recorded under topic `us.firstmet.brush_off`)_
- Does: session `turn`
- Then opens: `conversations.topic.firstmet.brushed.followup`
- …where the player's next choices will be: "I do remember. I brushed it aside and I shouldn't have." | "I remember it differently, that's all." | "I'll let it lie."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.brush_off
WHO    VILLAGER — what the player reads after pressing "I don't really remember it."
       spoken on: conversations.topic.firstmet.respond, button `brush_off`
       leaves the player on: conversations.topic.firstmet.brushed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.brushed.open`: the villager hurts. Subject `firstmet.memory`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.firstmet.brush_off/1   [32 chars]
    en  ...You don't remember it. Right.
    >>  ............................................
    pt  ...Você não lembra. Certo.
    >>  ............................................
  dialogue.conversations.us.firstmet.brush_off/2   [54 chars]
    en  I've thought about that day more than I'd admit, %1$s.
    >>  ............................................
    pt  Pensei naquele dia mais do que admitiria, %1$s.
    >>  ............................................
  dialogue.conversations.us.firstmet.brush_off/3   [11 chars]
    en  Well. I do.
    >>  ............................................
    pt  Bom. Eu lembro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember. I've had that afternoon in my head for years, %1$s.
    >>  ............................................
    pt  ...Você não lembra. Eu tenho aquela tarde na cabeça faz anos, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.us.firstmet.brush_off/2
    en  Right. Yes. It was only a small thing. To you.
    >>  ............................................
    pt  Certo. Sim. Era só uma coisinha. Pra você.
    >>  ............................................
  anxious.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Sorry. I shouldn't have expected you to.
    >>  ............................................
    pt  ...Desculpe. Eu não devia ter esperado que você lembrasse.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it. Well. People keep different things.
    >>  ............................................
    pt  ...Você não lembra. Bom. As pessoas guardam coisas diferentes.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's no fault of yours and I'll not make it one.
    >>  ............................................
    pt  Eu lembro. Não é culpa sua e eu não vou fazer disso uma.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. It'll come back to you or it won't.
    >>  ............................................
    pt  ...Certo. Vai voltar pra você ou não.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.brush_off/1
    en  You don't remember it. Right.
    >>  ............................................
    pt  Você não lembra. Certo.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's the difference between us on this, apparently.
    >>  ............................................
    pt  Eu lembro. É essa a diferença entre nós nisso, aparentemente.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.brush_off/3
    en  ...We'll leave it, then.
    >>  ............................................
    pt  ...Vamos deixar, então.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.brush_off/1
    en  You don't remember it. Right.
    >>  ............................................
    pt  Você não lembra. Certo.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's the difference between us on this, apparently.
    >>  ............................................
    pt  Eu lembro. É essa a diferença entre nós nisso, aparentemente.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.brush_off/3
    en  ...We'll leave it, then.
    >>  ............................................
    pt  ...Vamos deixar, então.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it, %1$s. I've thought about that day rather a lot.
    >>  ............................................
    pt  ...Você não lembra, %1$s. Eu pensei bastante naquele dia.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.brush_off/2
    en  Right. Well. It mattered more to me than to you, then. That's all right.
    >>  ............................................
    pt  Certo. Bom. Importou mais pra mim que pra você, então. Tudo bem.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.brush_off/3
    en  ...I'll not bring it up again. It's a nice memory to keep on my own.
    >>  ............................................
    pt  ...Não levanto de novo. É uma boa lembrança pra guardar sozinho.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it, %1$s. I've thought about that day rather a lot.
    >>  ............................................
    pt  ...Você não lembra, %1$s. Eu pensei bastante naquele dia.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.brush_off/2
    en  Right. Well. It mattered more to me than to you, then. That's all right.
    >>  ............................................
    pt  Certo. Bom. Importou mais pra mim que pra você, então. Tudo bem.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.brush_off/3
    en  ...I'll not bring it up again. It's a nice memory to keep on my own.
    >>  ............................................
    pt  ...Não levanto de novo. É uma boa lembrança pra guardar sozinho.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it, %1$s. I've thought about that day rather a lot.
    >>  ............................................
    pt  ...Você não lembra, %1$s. Eu pensei bastante naquele dia.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.brush_off/2
    en  Right. Well. It mattered more to me than to you, then. That's all right.
    >>  ............................................
    pt  Certo. Bom. Importou mais pra mim que pra você, então. Tudo bem.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.brush_off/3
    en  ...I'll not bring it up again. It's a nice memory to keep on my own.
    >>  ............................................
    pt  ...Não levanto de novo. É uma boa lembrança pra guardar sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember. I've had that afternoon in my head for years, %1$s.
    >>  ............................................
    pt  ...Você não lembra. Eu tenho aquela tarde na cabeça faz anos, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.brush_off/2
    en  Right. Yes. It was only a small thing. To you.
    >>  ............................................
    pt  Certo. Sim. Era só uma coisinha. Pra você.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Sorry. I shouldn't have expected you to.
    >>  ............................................
    pt  ...Desculpe. Eu não devia ter esperado que você lembrasse.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.brush_off/1
    en  You don't remember it. Right.
    >>  ............................................
    pt  Você não lembra. Certo.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's the difference between us on this, apparently.
    >>  ............................................
    pt  Eu lembro. É essa a diferença entre nós nisso, aparentemente.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.brush_off/3
    en  ...We'll leave it, then.
    >>  ............................................
    pt  ...Vamos deixar, então.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.brush_off/1
    en  You don't remember it. Right.
    >>  ............................................
    pt  Você não lembra. Certo.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's the difference between us on this, apparently.
    >>  ............................................
    pt  Eu lembro. É essa a diferença entre nós nisso, aparentemente.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.brush_off/3
    en  ...We'll leave it, then.
    >>  ............................................
    pt  ...Vamos deixar, então.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it.
    >>  ............................................
    pt  ...Você não lembra.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.brush_off/2
    en  I do.
    >>  ............................................
    pt  Eu lembro.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it. Well. People keep different things.
    >>  ............................................
    pt  ...Você não lembra. Bom. As pessoas guardam coisas diferentes.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's no fault of yours and I'll not make it one.
    >>  ............................................
    pt  Eu lembro. Não é culpa sua e eu não vou fazer disso uma.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. It'll come back to you or it won't.
    >>  ............................................
    pt  ...Certo. Vai voltar pra você ou não.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it.
    >>  ............................................
    pt  ...Você não lembra.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.brush_off/2
    en  I do.
    >>  ............................................
    pt  Eu lembro.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it. Well. People keep different things.
    >>  ............................................
    pt  ...Você não lembra. Bom. As pessoas guardam coisas diferentes.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's no fault of yours and I'll not make it one.
    >>  ............................................
    pt  Eu lembro. Não é culpa sua e eu não vou fazer disso uma.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. It'll come back to you or it won't.
    >>  ............................................
    pt  ...Certo. Vai voltar pra você ou não.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it! Wonderful. It was a whole afternoon, %1$s.
    >>  ............................................
    pt  ...Você não lembra! Maravilhoso. Foi uma tarde inteira, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.brush_off/2
    en  Right, well. I'll keep the memory. It's a good one and it's mine now.
    >>  ............................................
    pt  Certo, bom. Eu fico com a lembrança. É boa e agora é minha.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Ha. Fine. I'll describe it to you sometime and watch you not recall a bit of it.
    >>  ............................................
    pt  ...Ha. Tudo bem. Um dia eu descrevo e vejo você não lembrar de nada.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it! Wonderful. It was a whole afternoon, %1$s.
    >>  ............................................
    pt  ...Você não lembra! Maravilhoso. Foi uma tarde inteira, %1$s.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.brush_off/2
    en  Right, well. I'll keep the memory. It's a good one and it's mine now.
    >>  ............................................
    pt  Certo, bom. Eu fico com a lembrança. É boa e agora é minha.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Ha. Fine. I'll describe it to you sometime and watch you not recall a bit of it.
    >>  ............................................
    pt  ...Ha. Tudo bem. Um dia eu descrevo e vejo você não lembrar de nada.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it. Well. People keep different things.
    >>  ............................................
    pt  ...Você não lembra. Bom. As pessoas guardam coisas diferentes.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.brush_off/2
    en  I do. That's no fault of yours and I'll not make it one.
    >>  ............................................
    pt  Eu lembro. Não é culpa sua e eu não vou fazer disso uma.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. It'll come back to you or it won't.
    >>  ............................................
    pt  ...Certo. Vai voltar pra você ou não.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember. I've had that afternoon in my head for years, %1$s.
    >>  ............................................
    pt  ...Você não lembra. Eu tenho aquela tarde na cabeça faz anos, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.brush_off/2
    en  Right. Yes. It was only a small thing. To you.
    >>  ............................................
    pt  Certo. Sim. Era só uma coisinha. Pra você.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Sorry. I shouldn't have expected you to.
    >>  ............................................
    pt  ...Desculpe. Eu não devia ter esperado que você lembrasse.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it.
    >>  ............................................
    pt  ...Você não lembra.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.brush_off/2
    en  I do.
    >>  ............................................
    pt  Eu lembro.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it! Wonderful. It was a whole afternoon, %1$s.
    >>  ............................................
    pt  ...Você não lembra! Maravilhoso. Foi uma tarde inteira, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.brush_off/2
    en  Right, well. I'll keep the memory. It's a good one and it's mine now.
    >>  ............................................
    pt  Certo, bom. Eu fico com a lembrança. É boa e agora é minha.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Ha. Fine. I'll describe it to you sometime and watch you not recall a bit of it.
    >>  ............................................
    pt  ...Ha. Tudo bem. Um dia eu descrevo e vejo você não lembrar de nada.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.brush_off/1
    en  ...You don't remember it! Wonderful. It was a whole afternoon, %1$s.
    >>  ............................................
    pt  ...Você não lembra! Maravilhoso. Foi uma tarde inteira, %1$s.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.brush_off/2
    en  Right, well. I'll keep the memory. It's a good one and it's mine now.
    >>  ............................................
    pt  Certo, bom. Eu fico com a lembrança. É boa e agora é minha.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.brush_off/3
    en  ...Ha. Fine. I'll describe it to you sometime and watch you not recall a bit of it.
    >>  ............................................
    pt  ...Ha. Tudo bem. Um dia eu descrevo e vejo você não lembrar de nada.
    >>  ............................................
```

</details>


### Button `ask_impression` — "What did you make of me, back then?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `us.firstmet.again.to.firstmet`, `us.firstmet.memory.to.firstmet`, `us.firstmet.tell.to.firstmet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `firstmet.impression` — accepted phrasings: "what did you make of me back then"; "what did you think of me at first"; "what was your first impression of me"
  - scored words: `back`(0.5), `make`(0.8), `then`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.respond.ask_impression
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.respond.ask_impression   [35 chars]
    en  What did you make of me, back then?
    >>  ............................................
    pt  O que você achou de mim, naquela época?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.firstmet.impression`
- …where the player's next choices will be: "And now?" | "That's fair. I was exactly that." | "That's a lot to take in."

```text
POOL   dialogue key: dialogue.conversations.firstmet.impression
WHO    VILLAGER — what the player reads after pressing "What did you make of me, back then?"
       spoken on: conversations.topic.firstmet.respond, button `ask_impression`
       leaves the player on: conversations.topic.firstmet.impression
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.impression`: the villager reminisces. Subject `firstmet.impression`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, self_disclosure, exit
```

```text
  dialogue.conversations.firstmet.impression/1   [71 chars]
    en  That you'd not last the winter. I've never been so pleased to be wrong.
    >>  ............................................
    pt  Que você não passaria do inverno. Nunca fiquei tão feliz de estar errado.
    >>  ............................................
  dialogue.conversations.firstmet.impression/2   [74 chars]
    en  Too quiet to read. I decided you were rude and spent a month being unfair.
    >>  ............................................
    pt  Quieto demais pra se ler. Decidi que era grosseria e passei um mês sendo injusto.
    >>  ............................................
  dialogue.conversations.firstmet.impression/3   [78 chars]
    en  That you looked at people properly. It's a small thing and I noticed it first.
    >>  ............................................
    pt  Que você olhava as pessoas de verdade. É pequeno e foi a primeira coisa que notei.
    >>  ............................................
```


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `us.firstmet.again.to.firstmet`, `us.firstmet.memory.to.firstmet`, `us.firstmet.tell.to.firstmet` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.firstmet.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.firstmet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.firstmet.respond.leave   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.topic.firstmet.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.firstmet.followup / leave
```

> Written out in full under **`conversations.topic.firstmet.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

