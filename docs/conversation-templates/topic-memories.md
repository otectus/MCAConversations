# Topic: memories

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `memories` |
| Opened from | question `conversations.family`, button `memories` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.family` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `humor`, `curiosity`, `self_disclosure`, `dismissal`, `exit` |
| Narrative arc | `family`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.family`, which is written out in **topic-family.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.family.memories
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.family
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-family*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.family.memories   [23 chars]
    en  Tell me a family story.
    >>  ............................................
    pt  Me conta uma história da família.
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.memories.followup`](#conversations-scene-memories-followup)
- [`conversations.scene.memories.the_house_as_it_was.respond`](#conversations-scene-memories-the-house-as-it-was-respond)
- [`conversations.scene.memories.the_one_who_is_not_here.respond`](#conversations-scene-memories-the-one-who-is-not-here-respond)
- [`conversations.topic.memories.disputed`](#conversations-topic-memories-disputed)
- [`conversations.topic.memories.followup`](#conversations-topic-memories-followup)
- [`conversations.topic.memories.holiday`](#conversations-topic-memories-holiday)
- [`conversations.topic.memories.respond`](#conversations-topic-memories-respond)

---

## `conversations.scene.memories.followup`

**Reached from 4 route(s):** `conversations.scene.memories.the_house_as_it_was.respond` / `ask_about_the_mornings`; `conversations.scene.memories.the_house_as_it_was.respond` / `say_it_sounds_full`; `conversations.scene.memories.the_one_who_is_not_here.respond` / `just_be_there`; `conversations.scene.memories.the_one_who_is_not_here.respond` / `ask_if_they_write`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.memories.the_house_as_it_was.explained` — e.g. "Four people and one kettle. Everything else about our household follows from that single fact."
- `conversations.scene.memories.the_house_as_it_was.pleased` — e.g. "It is, and I lived alone for four years before it and I remember exactly how quiet a kettle can be."
- `conversations.scene.memories.the_one_who_is_not_here.answered` — e.g. "Twice a year, and the letters are about weather, and I read them about nine times each."
- `conversations.scene.memories.the_one_who_is_not_here.steadied` — e.g. "It is, and nobody has said the number back to me before, and hearing it out loud is oddly steadying."


```text
POOL   dialogue key: dialogue.conversations.scene.memories.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.memories.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.memories.followup   [25 chars]
    en  Anything else about them?
    >>  ............................................
    pt  Mais alguma coisa sobre eles?
    >>  ............................................
```


### Button `leave` — "We'll leave them be."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:memories.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.memories.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.followup.leave   [20 chars]
    en  We'll leave them be.
    >>  ............................................
    pt  Vamos deixar eles em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave them be."
       spoken on: conversations.scene.memories.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.scene.leaving`: the villager accepts. Subject `memories.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.memories.the_house_as_it_was.respond / leave; conversations.scene.memories.the_one_who_is_not_here.respond / leave
```

```text
  dialogue.conversations.scene.memories.leaving/1   [22 chars]
    en  That is the household.
    >>  ............................................
    pt  É a casa.
    >>  ............................................
  dialogue.conversations.scene.memories.leaving/2   [22 chars]
    en  Right. They will keep.
    >>  ............................................
    pt  Certo. Eles ficam para depois.
    >>  ............................................
  dialogue.conversations.scene.memories.leaving/3   [18 chars]
    en  Enough about them.
    >>  ............................................
    pt  Chega de falar deles.
    >>  ............................................
```

---


## `conversations.scene.memories.the_house_as_it_was.respond`

**Reached from 1 route(s):** `conversations.family` / `memories`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.memories.the_house_as_it_was` — e.g. "It was loud in the morning and asleep by nine, and I complained about both and would take either back tomorrow."


```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.memories.the_house_as_it_was.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was.respond   [25 chars]
    en  The household, as it was.
    >>  ............................................
    pt  A casa, como era.
    >>  ............................................
```


### Button `ask_about_the_mornings` — "What are the mornings like?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `memories.the_house_as_it_was.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.memories.the_house_as_it_was.ask_about_the_mornings` — accepted phrasings: "what are the mornings like"; "what are the mornings like"; "describe a morning in that house"
  - the message must contain one of: `mornings`, `morning`
  - scored words: `mornings`(1.8), `morning`(1.8), `describe`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was.respond.ask_about_the_mornings
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.the_house_as_it_was.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was.respond.ask_about_the_mornings   [27 chars]
    en  What are the mornings like?
    >>  ............................................
    pt  Como são as manhãs?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `memories.household`)_
- Does: session `turn`
- Then opens: `conversations.scene.memories.followup`
- …where the player's next choices will be: "We'll leave them be."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was.explained
WHO    VILLAGER — what the player reads after pressing "What are the mornings like?"
       spoken on: conversations.scene.memories.the_house_as_it_was.respond, button `ask_about_the_mornings`
       leaves the player on: conversations.scene.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.the_house_as_it_was.open.explained`: the villager explains. Subject `memories.household`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:memories` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was.explained/1   [94 chars]
    en  Four people and one kettle. Everything else about our household follows from that single fact.
    >>  ............................................
    pt  Quatro pessoas e uma chaleira. Todo o resto da nossa casa decorre desse único fato.
    >>  ............................................
  dialogue.conversations.scene.memories.the_house_as_it_was.explained/2   [115 chars]
    en  Somebody is always looking for a thing that is exactly where they left it, and it is never the same somebody twice.
    >>  ............................................
    pt  Sempre tem alguém procurando uma coisa que está exatamente onde deixou, e nunca é a mesma pessoa duas vezes.
    >>  ............................................
  dialogue.conversations.scene.memories.the_house_as_it_was.explained/3   [119 chars]
    en  Very fast and then very empty. The house at half past seven is the best room I know and I get about four minutes of it.
    >>  ............................................
    pt  Muito rápida e depois muito vazia. A casa às sete e meia é o melhor cômodo que eu conheço e eu tenho uns quatro minutos disso.
    >>  ............................................
```


### Button `say_it_sounds_full` — "Sounds like a full house."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `memories.the_house_as_it_was.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.memories.the_house_as_it_was.say_it_sounds_full` — accepted phrasings: "sounds like a full house"; "sounds like a full house"; "that is a full household"
  - the message must contain one of: `full`, `house`, `household`
  - scored words: `full`(1.8), `house`(1.8), `household`(1.8), `sounds`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was.respond.say_it_sounds_full
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.the_house_as_it_was.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was.respond.say_it_sounds_full   [25 chars]
    en  Sounds like a full house.
    >>  ............................................
    pt  Parece uma casa cheia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `memories.household`)_
- Does: session `turn`
- Then opens: `conversations.scene.memories.followup`
- …where the player's next choices will be: "We'll leave them be."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was.pleased
WHO    VILLAGER — what the player reads after pressing "Sounds like a full house."
       spoken on: conversations.scene.memories.the_house_as_it_was.respond, button `say_it_sounds_full`
       leaves the player on: conversations.scene.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.the_house_as_it_was.open.pleased`: the villager accepts. Subject `memories.household`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:memories` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was.pleased/1   [99 chars]
    en  It is, and I lived alone for four years before it and I remember exactly how quiet a kettle can be.
    >>  ............................................
    pt  É, e eu morei sozinha por quatro anos antes disso e lembro exatamente o quanto uma chaleira pode ser silenciosa.
    >>  ............................................
  dialogue.conversations.scene.memories.the_house_as_it_was.pleased/2   [103 chars]
    en  Full and small, which is the combination people warn you about and then never explain the good half of.
    >>  ............................................
    pt  Cheia e pequena, que é a combinação sobre a qual as pessoas avisam e depois nunca explicam a metade boa.
    >>  ............................................
  dialogue.conversations.scene.memories.the_house_as_it_was.pleased/3   [103 chars]
    en  Come by some evening. There is always a chair and you will be handed something to hold within a minute.
    >>  ............................................
    pt  Apareça uma noite dessas. Sempre tem cadeira e você vai receber alguma coisa para segurar em menos de um minuto.
    >>  ............................................
```


### Button `leave` — "Thanks for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `memories.the_house_as_it_was.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.the_house_as_it_was.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was.respond.leave   [22 chars]
    en  Thanks for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for telling me."
       spoken on: conversations.scene.memories.the_house_as_it_was.respond, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.scene.leaving`: the villager accepts. Subject `memories.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.memories.followup / leave; conversations.scene.memories.the_one_who_is_not_here.respond / leave
```

> Written out in full under **`conversations.scene.memories.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.memories.the_one_who_is_not_here.respond`

**Reached from 1 route(s):** `conversations.family` / `memories`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.memories.the_one_who_is_not_here` — e.g. "There is a chair at our table that has been nobody's for six years and is still that chair."


```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.memories.the_one_who_is_not_here.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here.respond   [24 chars]
    en  Somebody who isn't here.
    >>  ............................................
    pt  Alguém que não está aqui.
    >>  ............................................
```


### Button `just_be_there` — "Six years is a long chair."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `memories.the_one_who_is_not_here.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.memories.the_one_who_is_not_here.just_be_there` — accepted phrasings: "six years is a long chair"; "six years is a long time for that"; "that chair has been empty a long while"
  - the message must contain one of: `years`, `chair`, `empty`
  - scored words: `years`(1.8), `chair`(1.8), `empty`(1.8), `six`(0.8), `long`(0.8), `time`(0.8), `been`(0.8), `while`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here.respond.just_be_there
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.the_one_who_is_not_here.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here.respond.just_be_there   [26 chars]
    en  Six years is a long chair.
    >>  ............................................
    pt  Seis anos é uma cadeira longa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.family.absent.held`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +4  _(recorded under topic `memories.absent`)_
- Does: session `turn`
- Then opens: `conversations.scene.memories.followup`
- …where the player's next choices will be: "We'll leave them be."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here.steadied
WHO    VILLAGER — what the player reads after pressing "Six years is a long chair."
       spoken on: conversations.scene.memories.the_one_who_is_not_here.respond, button `just_be_there`
       leaves the player on: conversations.scene.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.the_one_who_is_not_here.open.steadied`: the villager accepts. Subject `memories.absent`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:memories` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here.steadied/1   [100 chars]
    en  It is, and nobody has said the number back to me before, and hearing it out loud is oddly steadying.
    >>  ............................................
    pt  É, e ninguém tinha me devolvido o número antes, e ouvir em voz alta é estranhamente firmador.
    >>  ............................................
  dialogue.conversations.scene.memories.the_one_who_is_not_here.steadied/2   [93 chars]
    en  Thank you for not asking why they went. Everybody asks why and the why has never once helped.
    >>  ............................................
    pt  Obrigada por não perguntar por que foram embora. Todo mundo pergunta o porquê e o porquê nunca ajudou uma vez.
    >>  ............................................
  dialogue.conversations.scene.memories.the_one_who_is_not_here.steadied/3   [100 chars]
    en  We could move the chair. We have discussed moving the chair four times and the chair is still there.
    >>  ............................................
    pt  A gente podia tirar a cadeira. Já discutimos tirar a cadeira quatro vezes e a cadeira continua lá.
    >>  ............................................
```


### Button `ask_if_they_write` — "Do you hear from them?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `memories.the_one_who_is_not_here.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.memories.the_one_who_is_not_here.ask_if_they_write` — accepted phrasings: "do you hear from them"; "do you hear from them"; "is there any word from them"
  - the message must contain one of: `hear`, `word`
  - scored words: `hear`(1.8), `word`(1.8), `from`(0.8), `any`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here.respond.ask_if_they_write
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.the_one_who_is_not_here.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here.respond.ask_if_they_write   [22 chars]
    en  Do you hear from them?
    >>  ............................................
    pt  Você tem notícias deles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `memories.absent`)_
- Does: session `turn`
- Then opens: `conversations.scene.memories.followup`
- …where the player's next choices will be: "We'll leave them be."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here.answered
WHO    VILLAGER — what the player reads after pressing "Do you hear from them?"
       spoken on: conversations.scene.memories.the_one_who_is_not_here.respond, button `ask_if_they_write`
       leaves the player on: conversations.scene.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.the_one_who_is_not_here.open.answered`: the villager explains. Subject `memories.absent`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:memories` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here.answered/1   [87 chars]
    en  Twice a year, and the letters are about weather, and I read them about nine times each.
    >>  ............................................
    pt  Duas vezes por ano, e as cartas são sobre o tempo, e eu leio cada uma umas nove vezes.
    >>  ............................................
  dialogue.conversations.scene.memories.the_one_who_is_not_here.answered/2   [118 chars]
    en  Through somebody else, which is worse than nothing and better than nothing, and I could not tell you which more often.
    >>  ............................................
    pt  Por intermédio de outra pessoa, o que é pior que nada e melhor que nada, e eu não saberia dizer qual com mais frequência.
    >>  ............................................
  dialogue.conversations.scene.memories.the_one_who_is_not_here.answered/3   [103 chars]
    en  No. And I check anyway, every time a carter comes through, and I have stopped pretending that I do not.
    >>  ............................................
    pt  Não. E eu confiro mesmo assim, toda vez que passa um carroceiro, e parei de fingir que não confiro.
    >>  ............................................
```


### Button `leave` — "Thanks for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `memories.the_one_who_is_not_here.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.memories.the_one_who_is_not_here.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here.respond.leave   [22 chars]
    en  Thanks for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for telling me."
       spoken on: conversations.scene.memories.the_one_who_is_not_here.respond, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.scene.leaving`: the villager accepts. Subject `memories.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.memories.followup / leave; conversations.scene.memories.the_house_as_it_was.respond / leave
```

> Written out in full under **`conversations.scene.memories.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.memories.disputed`

**Reached from 1 route(s):** `conversations.topic.memories.respond` / `remember_it_differently`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.memories.disputed` — e.g. "Don't you. Go on, then — I've been telling it my way for years unopposed."


```text
POOL   dialogue key: dialogue.conversations.topic.memories.disputed
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.memories.disputed
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.memories.disputed   [28 chars]
    en  So which of us has it right?
    >>  ............................................
    pt  Então qual de nós está certo?
    >>  ............................................
```


### Button `both_true` — "Perhaps we both have a piece of it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `memories.disputed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.disputed.both` — accepted phrasings: "perhaps we both have a piece of it"; "maybe we are both right"; "we each remember part of it"
  - the message must contain one of: `both`, `piece`
  - scored words: `both`(1.2), `piece`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.disputed.both_true
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.disputed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.disputed.both_true   [35 chars]
    en  Perhaps we both have a piece of it.
    >>  ............................................
    pt  Talvez cada um de nós tenha um pedaço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `memories.disputed.both`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +2  _(recorded under topic `memories.disputed.both`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.memories.disputed.both
WHO    VILLAGER — what the player reads after pressing "Perhaps we both have a piece of it."
       spoken on: conversations.topic.memories.disputed, button `both_true`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.disputed.both`: the villager accepts. Subject `memories.disputed`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.memories.disputed.both/1   [59 chars]
    en  That's the kindest way anyone has ever told me I was wrong.
    >>  ............................................
    pt  É o jeito mais gentil que alguém já usou pra dizer que eu estava errado.
    >>  ............................................
  dialogue.conversations.memories.disputed.both/2   [68 chars]
    en  Likely. Two people never come out of a room with the same afternoon.
    >>  ............................................
    pt  Provável. Duas pessoas nunca saem de um cômodo com a mesma tarde.
    >>  ............................................
  dialogue.conversations.memories.disputed.both/3   [68 chars]
    en  Then we'll tell it together next time and confuse everybody equally.
    >>  ............................................
    pt  Então contamos juntos da próxima e confundimos todo mundo igualmente.
    >>  ............................................
```


### Button `tell_me_yours` — "Tell me your version properly."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `memories.disputed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.disputed.yours` — accepted phrasings: "tell me your version properly"; "give me your version of it"; "tell it your way then"
  - the message must contain one of: `version`
  - scored words: `version`(1.5), `yours`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.disputed.tell_me_yours
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.disputed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.disputed.tell_me_yours   [30 chars]
    en  Tell me your version properly.
    >>  ............................................
    pt  Me conte a sua versão direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `memories.disputed.yours`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.memories.disputed.yours
WHO    VILLAGER — what the player reads after pressing "Tell me your version properly."
       spoken on: conversations.topic.memories.disputed, button `tell_me_yours`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.disputed.yours`: the villager reminisces. Subject `memories.disputed`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.memories.disputed.yours/1   [69 chars]
    en  Right. It was raining, for a start, and everyone leaves the rain out.
    >>  ............................................
    pt  Certo. Estava chovendo, pra começar, e todo mundo esquece a chuva.
    >>  ............................................
  dialogue.conversations.memories.disputed.yours/2   [64 chars]
    en  Then sit down. It's longer than the version I give at the table.
    >>  ............................................
    pt  Então sente. É mais longa que a versão que eu conto à mesa.
    >>  ............................................
  dialogue.conversations.memories.disputed.yours/3   [68 chars]
    en  Nobody asks for the long one. You'll regret it by the second winter.
    >>  ............................................
    pt  Ninguém pede a longa. Você vai se arrepender lá pelo segundo inverno.
    >>  ............................................
```


### Button `leave` — "It doesn't matter much."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `memories.disputed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.memories.disputed.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.disputed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.disputed.leave   [23 chars]
    en  It doesn't matter much.
    >>  ............................................
    pt  Não faz muita diferença.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.memories.disputed.leave
WHO    VILLAGER — what the player reads after pressing "It doesn't matter much."
       spoken on: conversations.topic.memories.disputed, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.disputed.leave`: the villager accepts. Subject `memories.disputed`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.memories.disputed.leave/1   [30 chars]
    en  It does to me. But off you go.
    >>  ............................................
    pt  Pra mim faz. Mas pode ir.
    >>  ............................................
  dialogue.conversations.memories.disputed.leave/2   [10 chars]
    en  Hm. Right.
    >>  ............................................
    pt  Hm. Certo.
    >>  ............................................
  dialogue.conversations.memories.disputed.leave/3   [12 chars]
    en  As you like.
    >>  ............................................
    pt  Como quiser.
    >>  ............................................
```

---


## `conversations.topic.memories.followup`

**Reached from 4 route(s):** `conversations.topic.memories.respond` / `laugh`; `conversations.topic.memories.respond` / `laugh`; `conversations.topic.memories.respond` / `ask_more`; `conversations.topic.memories.respond` / `brush_off`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.memories.ask_more` — e.g. "More? Careful — I've a great many and no shame."
- `conversations.family.memories.brush_off` — e.g. "...That IS how it went."
- `conversations.family.memories.laugh` — e.g. "You'd forgotten! I've told that one at every feast for six years."
- `conversations.family.memories.laugh.again` — e.g. "We've built a stock of these, you and I. That's what a family is, in the end."


```text
POOL   dialogue key: dialogue.conversations.topic.memories.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.memories.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.memories.followup   [18 chars]
    en  Long time ago now.
    >>  ............................................
    pt  Faz tempo agora.
    >>  ............................................
```


### Button `add_own` — "Here's one you don't know."

*stance family `self_disclosure` · tone `playful` · answers the beat(s) `family.memories.ask_more.to.memories`, `family.memories.brush_off.to.memories`, `family.memories.laugh.again.to.memories`, `family.memories.laugh.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.followup.add_own` — accepted phrasings: "here is one you have not heard"; "let me add one of mine"; "i have an unheard one for you"
  - the message must contain one of: `add`, `unheard`
  - scored words: `add`(1.0), `mine`(0.8), `unheard`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.followup.add_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.followup.add_own   [26 chars]
    en  Here's one you don't know.
    >>  ............................................
    pt  Tenho uma que você não sabe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.memories.add_own`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — familiarity +5, warmth +2  _(recorded under topic `family.memories.add_own`)_
- Does: arc `family` — advance to stage 2
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.memories.add_own
WHO    VILLAGER — what the player reads after pressing "Here's one you don't know."
       spoken on: conversations.topic.memories.followup, button `add_own`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.add_own.to.family`: the villager accepts. Subject `family`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.add_own/1   [58 chars]
    en  You've one I don't know? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vai — não vou interromper. Muito.
    >>  ............................................
  dialogue.conversations.family.memories.add_own/2   [48 chars]
    en  Ha! Then we're trading, and I want the good one.
    >>  ............................................
    pt  Rá! Então estamos trocando, e eu quero a boa.
    >>  ............................................
  dialogue.conversations.family.memories.add_own/3   [49 chars]
    en  New to me. That's rare after all this time, %1$s.
    >>  ............................................
    pt  Nova para mim. Isso é raro depois de tanto tempo, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know, %1$s? Go on. I'll not interrupt, I promise.
    >>  ............................................
    pt  Você tem uma que eu não sei, %1$s? Vá em frente. Não vou interromper, prometo.
    >>  ............................................
  anxious.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours. Nobody offers one back. They take mine and go.
    >>  ............................................
    pt  Certo. A sua. Ninguém oferece uma de volta. Pegam a minha e vão.
    >>  ............................................
  anxious.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. That's — yes. Please. I'd like that very much.
    >>  ............................................
    pt  Uma que eu não ouvi. Isso é — sim. Por favor. Eu gostaria muito.
    >>  ............................................
  athletic.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt, and there's no hurry.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper, e sem pressa.
    >>  ............................................
  athletic.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Take as long as it takes.
    >>  ............................................
    pt  Certo. A sua, então. Leve o tempo que precisar.
    >>  ............................................
  athletic.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Good. We'll have the evening for it.
    >>  ............................................
    pt  Uma que eu não ouvi. Bom. A gente tem a noite pra isso.
    >>  ............................................
  confident.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  confident.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. I'll listen properly.
    >>  ............................................
    pt  Certo. A sua, então. Vou escutar direito.
    >>  ............................................
  confident.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  crabby.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  crabby.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. I'll listen properly.
    >>  ............................................
    pt  Certo. A sua, então. Vou escutar direito.
    >>  ............................................
  crabby.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  extroverted.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know, %1$s? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei, %1$s? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  extroverted.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Sit down properly; I'd like the whole of it.
    >>  ............................................
    pt  Certo. A sua, então. Sente-se direito; eu quero a coisa inteira.
    >>  ............................................
  extroverted.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Those are worth more than anything I could tell you.
    >>  ............................................
    pt  Uma que eu não ouvi. Essas valem mais que qualquer coisa que eu contasse.
    >>  ............................................
  flirty.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know, %1$s? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei, %1$s? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  flirty.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Sit down properly; I'd like the whole of it.
    >>  ............................................
    pt  Certo. A sua, então. Sente-se direito; eu quero a coisa inteira.
    >>  ............................................
  flirty.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Those are worth more than anything I could tell you.
    >>  ............................................
    pt  Uma que eu não ouvi. Essas valem mais que qualquer coisa que eu contasse.
    >>  ............................................
  friendly.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know, %1$s? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei, %1$s? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  friendly.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Sit down properly; I'd like the whole of it.
    >>  ............................................
    pt  Certo. A sua, então. Sente-se direito; eu quero a coisa inteira.
    >>  ............................................
  friendly.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Those are worth more than anything I could tell you.
    >>  ............................................
    pt  Uma que eu não ouvi. Essas valem mais que qualquer coisa que eu contasse.
    >>  ............................................
  gloomy.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know, %1$s? Go on. I'll not interrupt, I promise.
    >>  ............................................
    pt  Você tem uma que eu não sei, %1$s? Vá em frente. Não vou interromper, prometo.
    >>  ............................................
  gloomy.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours. Nobody offers one back. They take mine and go.
    >>  ............................................
    pt  Certo. A sua. Ninguém oferece uma de volta. Pegam a minha e vão.
    >>  ............................................
  gloomy.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. That's — yes. Please. I'd like that very much.
    >>  ............................................
    pt  Uma que eu não ouvi. Isso é — sim. Por favor. Eu gostaria muito.
    >>  ............................................
  greedy.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  greedy.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. I'll listen properly.
    >>  ............................................
    pt  Certo. A sua, então. Vou escutar direito.
    >>  ............................................
  greedy.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  grumpy.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  grumpy.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. I'll listen properly.
    >>  ............................................
    pt  Certo. A sua, então. Vou escutar direito.
    >>  ............................................
  grumpy.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  introverted.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  introverted.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then.
    >>  ............................................
    pt  Certo. A sua, então.
    >>  ............................................
  introverted.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  lazy.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt, and there's no hurry.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper, e sem pressa.
    >>  ............................................
  lazy.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Take as long as it takes.
    >>  ............................................
    pt  Certo. A sua, então. Leve o tempo que precisar.
    >>  ............................................
  lazy.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Good. We'll have the evening for it.
    >>  ............................................
    pt  Uma que eu não ouvi. Bom. A gente tem a noite pra isso.
    >>  ............................................
  odd.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  odd.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then.
    >>  ............................................
    pt  Certo. A sua, então.
    >>  ............................................
  odd.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  peaceful.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt, and there's no hurry.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper, e sem pressa.
    >>  ............................................
  peaceful.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Take as long as it takes.
    >>  ............................................
    pt  Certo. A sua, então. Leve o tempo que precisar.
    >>  ............................................
  peaceful.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Good. We'll have the evening for it.
    >>  ............................................
    pt  Uma que eu não ouvi. Bom. A gente tem a noite pra isso.
    >>  ............................................
  peppy.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  peppy.dialogue.conversations.family.memories.add_own/2
    en  Right, yours then! I'll listen properly and probably interrupt anyway.
    >>  ............................................
    pt  Certo, a sua então! Vou escutar direito e provavelmente interromper mesmo assim.
    >>  ............................................
  peppy.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard! Excellent. Sit down, this is my favourite kind of afternoon.
    >>  ............................................
    pt  Uma que eu não ouvi! Excelente. Sente-se, é meu tipo favorito de tarde.
    >>  ............................................
  playful.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  playful.dialogue.conversations.family.memories.add_own/2
    en  Right, yours then! I'll listen properly and probably interrupt anyway.
    >>  ............................................
    pt  Certo, a sua então! Vou escutar direito e provavelmente interromper mesmo assim.
    >>  ............................................
  playful.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard! Excellent. Sit down, this is my favourite kind of afternoon.
    >>  ............................................
    pt  Uma que eu não ouvi! Excelente. Sente-se, é meu tipo favorito de tarde.
    >>  ............................................
  relaxed.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt, and there's no hurry.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper, e sem pressa.
    >>  ............................................
  relaxed.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then. Take as long as it takes.
    >>  ............................................
    pt  Certo. A sua, então. Leve o tempo que precisar.
    >>  ............................................
  relaxed.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Good. We'll have the evening for it.
    >>  ............................................
    pt  Uma que eu não ouvi. Bom. A gente tem a noite pra isso.
    >>  ............................................
  sensitive.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know, %1$s? Go on. I'll not interrupt, I promise.
    >>  ............................................
    pt  Você tem uma que eu não sei, %1$s? Vá em frente. Não vou interromper, prometo.
    >>  ............................................
  sensitive.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours. Nobody offers one back. They take mine and go.
    >>  ............................................
    pt  Certo. A sua. Ninguém oferece uma de volta. Pegam a minha e vão.
    >>  ............................................
  sensitive.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. That's — yes. Please. I'd like that very much.
    >>  ............................................
    pt  Uma que eu não ouvi. Isso é — sim. Por favor. Eu gostaria muito.
    >>  ............................................
  shy.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on. I'll not interrupt.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente. Não vou interromper.
    >>  ............................................
  shy.dialogue.conversations.family.memories.add_own/2
    en  Right. Yours, then.
    >>  ............................................
    pt  Certo. A sua, então.
    >>  ............................................
  shy.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard. Say it.
    >>  ............................................
    pt  Uma que eu não ouvi. Diga.
    >>  ............................................
  upbeat.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  upbeat.dialogue.conversations.family.memories.add_own/2
    en  Right, yours then! I'll listen properly and probably interrupt anyway.
    >>  ............................................
    pt  Certo, a sua então! Vou escutar direito e provavelmente interromper mesmo assim.
    >>  ............................................
  upbeat.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard! Excellent. Sit down, this is my favourite kind of afternoon.
    >>  ............................................
    pt  Uma que eu não ouvi! Excelente. Sente-se, é meu tipo favorito de tarde.
    >>  ............................................
  witty.dialogue.conversations.family.memories.add_own/1
    en  You've one I don't know? Go on — I'll not interrupt. Much.
    >>  ............................................
    pt  Você tem uma que eu não sei? Vá em frente — não vou interromper. Muito.
    >>  ............................................
  witty.dialogue.conversations.family.memories.add_own/2
    en  Right, yours then! I'll listen properly and probably interrupt anyway.
    >>  ............................................
    pt  Certo, a sua então! Vou escutar direito e provavelmente interromper mesmo assim.
    >>  ............................................
  witty.dialogue.conversations.family.memories.add_own/3
    en  One I've not heard! Excellent. Sit down, this is my favourite kind of afternoon.
    >>  ............................................
    pt  Uma que eu não ouvi! Excelente. Sente-se, é meu tipo favorito de tarde.
    >>  ............................................
```

</details>


### Button `question_detail` — "Are you sure about that bit?"

*stance family `curiosity` · tone `playful` · answers the beat(s) `family.memories.ask_more.to.memories`, `family.memories.brush_off.to.memories`, `family.memories.laugh.again.to.memories`, `family.memories.laugh.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.followup.question_detail` — accepted phrasings: "are you sure about that bit"; "are you certain about that part"; "is that part right"
  - the message must contain one of: `sure`
  - scored words: `bit`(0.8), `sure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.followup.question_detail
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.followup.question_detail   [28 chars]
    en  Are you sure about that bit?
    >>  ............................................
    pt  Você tem certeza dessa parte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `family.memories.question_detail`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `family.memories.question_detail`)_
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.memories.question_detail
WHO    VILLAGER — what the player reads after pressing "Are you sure about that bit?"
       spoken on: conversations.topic.memories.followup, button `question_detail`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.question_detail.to.family`: the villager accepts. Subject `family`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.question_detail/1   [57 chars]
    en  ...It might have been the other summer. Doesn't spoil it.
    >>  ............................................
    pt  ...Pode ter sido no outro verão. Não estraga a história.
    >>  ............................................
  dialogue.conversations.family.memories.question_detail/2   [38 chars]
    en  You're checking my facts. In MY story.
    >>  ............................................
    pt  Você está checando meus fatos. Na MINHA história.
    >>  ............................................
  dialogue.conversations.family.memories.question_detail/3   [38 chars]
    en  Sure enough. Sure-ish. Let me have it.
    >>  ............................................
    pt  Tenho certeza. Quase certeza. Deixa eu contar.
    >>  ............................................
```


### Button `dismiss` — "You always tell that one."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `family.memories.ask_more.to.memories`, `family.memories.brush_off.to.memories`, `family.memories.laugh.again.to.memories`, `family.memories.laugh.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.followup.dismiss` — accepted phrasings: "you always tell that one"; "you tell that one every time"; "i have heard that one before"
  - the message must contain one of: `always`, `every`
  - scored words: `always`(1.5), `before`(0.8), `every`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.followup.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.followup.dismiss   [25 chars]
    en  You always tell that one.
    >>  ............................................
    pt  Você sempre conta essa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `family.memories.dismiss`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `family.memories.dismiss`)_
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.memories.dismiss
WHO    VILLAGER — what the player reads after pressing "You always tell that one."
       spoken on: conversations.topic.memories.followup, button `dismiss`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.dismiss.to.family`: the villager qualifys. Subject `family`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.dismiss/1   [46 chars]
    en  ...I do always tell that one. It's a good one.
    >>  ............................................
    pt  ...Eu sempre conto essa mesmo. É boa.
    >>  ............................................
  dialogue.conversations.family.memories.dismiss/2   [31 chars]
    en  Then I'll find a new one, %1$s.
    >>  ............................................
    pt  Então vou achar uma nova, %1$s.
    >>  ............................................
  dialogue.conversations.family.memories.dismiss/3   [31 chars]
    en  Noted. I'll spare you the rest.
    >>  ............................................
    pt  Anotado. Vou te poupar do resto.
    >>  ............................................
```


### Button `ask_holiday` — "What were the holidays like, back then?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `family.memories.ask_more.to.memories`, `family.memories.brush_off.to.memories`, `family.memories.laugh.again.to.memories`, `family.memories.laugh.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.holiday` — accepted phrasings: "what were the holidays like back then"; "what were the festivals like"; "how did you keep the holidays then"
  - the message must contain one of: `festivals`, `holidays`
  - scored words: `festivals`(1.2), `holidays`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.followup.ask_holiday
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.followup.ask_holiday   [39 chars]
    en  What were the holidays like, back then?
    >>  ............................................
    pt  Como eram as festas naquela época?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.memories.holiday`
- …where the player's next choices will be: "We could do it that way again." | "Who was there, in those days?" | "That's a good picture."

```text
POOL   dialogue key: dialogue.conversations.memories.holiday
WHO    VILLAGER — what the player reads after pressing "What were the holidays like, back then?"
       spoken on: conversations.topic.memories.followup, button `ask_holiday`
       leaves the player on: conversations.topic.memories.holiday
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.holiday`: the villager reminisces. Subject `memories.holiday`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, exit
```

```text
  dialogue.conversations.memories.holiday/1   [77 chars]
    en  Longer. Three days, and the third one was only for sitting about being tired.
    >>  ............................................
    pt  Mais longas. Três dias, e o terceiro era só pra ficar sentado, cansado.
    >>  ............................................
  dialogue.conversations.memories.holiday/2   [76 chars]
    en  Colder, and better for it. We ate outside because inside was full of people.
    >>  ............................................
    pt  Mais frias, e melhores por isso. Comíamos fora porque dentro estava cheio.
    >>  ............................................
  dialogue.conversations.memories.holiday/3   [86 chars]
    en  Smaller. Four families and one fiddle, and I'd take it over any of the big ones since.
    >>  ............................................
    pt  Menores. Quatro famílias e uma rabeca, e eu trocaria qualquer uma das grandes por aquela.
    >>  ............................................
```


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `family.memories.ask_more.to.memories`, `family.memories.brush_off.to.memories`, `family.memories.laugh.again.to.memories`, `family.memories.laugh.to.memories` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.memories.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.followup.leave   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.memories.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.topic.memories.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.memories.respond / leave
```

```text
  dialogue.conversations.family.memories.leave/1   [50 chars]
    en  Just so. Another time, and I'll have a better one.
    >>  ............................................
    pt  Pois é. Outra hora, e eu terei uma melhor.
    >>  ............................................
  dialogue.conversations.family.memories.leave/2   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
  dialogue.conversations.family.memories.leave/3   [9 chars]
    en  Quite so.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
```

---


## `conversations.topic.memories.holiday`

**Reached from 1 route(s):** `conversations.topic.memories.followup` / `ask_holiday`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.memories.holiday` — e.g. "Longer. Three days, and the third one was only for sitting about being tired."


```text
POOL   dialogue key: dialogue.conversations.topic.memories.holiday
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.memories.holiday
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.memories.holiday   [30 chars]
    en  That's what they were, anyway.
    >>  ............................................
    pt  Era assim que eram, de todo jeito.
    >>  ............................................
```


### Button `bring_it_back` — "We could do it that way again."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `memories.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.holiday.revive` — accepted phrasings: "we could do it that way again"; "we should bring that back"; "let us keep it the old way this year"
  - the message must contain one of: `revive`
  - scored words: `again`(0.5), `revive`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.holiday.bring_it_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.holiday
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.holiday.bring_it_back   [30 chars]
    en  We could do it that way again.
    >>  ............................................
    pt  A gente podia fazer assim de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `memories.holiday.revive`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `memories.holiday.revive`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.memories.holiday.revive
WHO    VILLAGER — what the player reads after pressing "We could do it that way again."
       spoken on: conversations.topic.memories.holiday, button `bring_it_back`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.holiday.revive`: the villager accepts. Subject `memories.holiday`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.memories.holiday.revive/1   [66 chars]
    en  Could we. I've thought it every year and never said it to anybody.
    >>  ............................................
    pt  Será? Penso nisso todo ano e nunca disse a ninguém.
    >>  ............................................
  dialogue.conversations.memories.holiday.revive/2   [66 chars]
    en  You'd need four families and a fiddle. I know where the fiddle is.
    >>  ............................................
    pt  Você precisaria de quatro famílias e uma rabeca. Sei onde está a rabeca.
    >>  ............................................
  dialogue.conversations.memories.holiday.revive/3   [68 chars]
    en  Careful. Say that where the others hear and you'll be organising it.
    >>  ............................................
    pt  Cuidado. Diga isso onde os outros ouçam e você acaba organizando.
    >>  ............................................
```


### Button `who_was_there` — "Who was there, in those days?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `memories.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.holiday.who` — accepted phrasings: "who was there in those days"; "who used to come"; "who else was there then"
  - scored words: `there`(0.5), `days`(0.6), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.holiday.who_was_there
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.holiday
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.holiday.who_was_there   [29 chars]
    en  Who was there, in those days?
    >>  ............................................
    pt  Quem estava lá, naquele tempo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `memories.holiday.who`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.memories.holiday.who
WHO    VILLAGER — what the player reads after pressing "Who was there, in those days?"
       spoken on: conversations.topic.memories.holiday, button `who_was_there`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.holiday.who`: the villager reminisces. Subject `memories.holiday`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.memories.holiday.who/1   [73 chars]
    en  Half of them are under the yew now. The other half moved and write badly.
    >>  ............................................
    pt  Metade está sob o teixo agora. A outra metade se mudou e escreve mal.
    >>  ............................................
  dialogue.conversations.memories.holiday.who/2   [70 chars]
    en  Everyone. That was the point of it — nobody had anywhere better to be.
    >>  ............................................
    pt  Todo mundo. Era esse o ponto — ninguém tinha lugar melhor pra estar.
    >>  ............................................
  dialogue.conversations.memories.holiday.who/3   [68 chars]
    en  My mother, mostly, doing all of it and telling no one she was tired.
    >>  ............................................
    pt  Minha mãe, principalmente, fazendo tudo e sem contar a ninguém que estava cansada.
    >>  ............................................
```


### Button `leave` — "That's a good picture."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `memories.holiday` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.memories.holiday.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.holiday
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.holiday.leave   [22 chars]
    en  That's a good picture.
    >>  ............................................
    pt  É uma boa imagem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.memories.holiday.leave
WHO    VILLAGER — what the player reads after pressing "That's a good picture."
       spoken on: conversations.topic.memories.holiday, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.holiday.leave`: the villager accepts. Subject `memories.holiday`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.memories.holiday.leave/1   [18 chars]
    en  It is. Off you go.
    >>  ............................................
    pt  É mesmo. Pode ir.
    >>  ............................................
  dialogue.conversations.memories.holiday.leave/2   [15 chars]
    en  It is. Keep it.
    >>  ............................................
    pt  É sim. Guarde.
    >>  ............................................
  dialogue.conversations.memories.holiday.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.memories.respond`

**Reached from 4 route(s):** `conversations.family` / `memories`; `conversations.family` / `memories`; `conversations.family` / `memories`; `conversations.family` / `memories`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.memories.again` — e.g. "I told you the good one already. The rest need supper and a fire to come out right."
- `conversations.family.memories.share` — e.g. "Did I ever tell you about the winter my uncle got snowed into the outhouse? No? Sit down."
- `conversations.family.memories.tell` — e.g. "There's one I always come back to. Sit down."
- `conversations.family.memories.toddler` — e.g. "'Member when we saw the sheep? That was the best day of my LIFE."


```text
POOL   dialogue key: dialogue.conversations.topic.memories.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.memories.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.memories.respond   [37 chars]
    en  That's the one I always come back to.
    >>  ............................................
    pt  É essa que eu sempre lembro.
    >>  ............................................
```


### Button `laugh` — "I'd forgotten that."

*stance family `humor` · tone `playful` · answers the beat(s) `family.memories.again.to.memories`, `family.memories.share.to.memories`, `family.memories.tell.to.memories`, `family.memories.toddler.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.laugh` — accepted phrasings: "i had forgotten that"; "i had completely forgotten that"; "that had gone right out of my head"
  - the message must contain one of: `forgotten`
  - scored words: `forgotten`(1.5), `ha`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.respond.laugh
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.respond.laugh   [19 chars]
    en  I'd forgotten that.
    >>  ............................................
    pt  Eu tinha esquecido disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when arc `family` is at stage 1..2
- Does: **hearts +2** — decision id `family.memories.laugh`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5, familiarity +3  _(recorded under topic `family.memories.laugh`)_
- Then opens: `conversations.topic.memories.followup`
- …where the player's next choices will be: "Here's one you don't know." | "Are you sure about that bit?" | "You always tell that one." | "What were the holidays like, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.laugh.again
WHO    VILLAGER — what the player reads after pressing "I'd forgotten that."
       spoken on: conversations.topic.memories.respond, button `laugh`
       leaves the player on: conversations.topic.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.laugh.again.to.memories`: the villager accepts. Subject `memories`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.laugh.again/1   [77 chars]
    en  We've built a stock of these, you and I. That's what a family is, in the end.
    >>  ............................................
    pt  A gente juntou um estoque dessas, você e eu. É isso que é uma família, no fim.
    >>  ............................................
  dialogue.conversations.family.memories.laugh.again/2   [49 chars]
    en  Another one for the pile. There'll be more, %1$s.
    >>  ............................................
    pt  Mais uma para a pilha. Vai ter mais, %1$s.
    >>  ............................................
  dialogue.conversations.family.memories.laugh.again/3   [80 chars]
    en  You've heard half of these twice and you still laugh. That counts for something.
    >>  ............................................
    pt  Você já ouviu metade dessas duas vezes e ainda ri. Isso conta.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when arc `family` is at stage 1..2  _(chance -2000)_
- Does: **hearts +2** — decision id `family.memories.laugh`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5, familiarity +2  _(recorded under topic `family.memories.laugh`)_
- Then opens: `conversations.topic.memories.followup`
- …where the player's next choices will be: "Here's one you don't know." | "Are you sure about that bit?" | "You always tell that one." | "What were the holidays like, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.laugh
WHO    VILLAGER — what the player reads after pressing "I'd forgotten that."
       spoken on: conversations.topic.memories.respond, button `laugh`
       leaves the player on: conversations.topic.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.laugh.to.memories`: the villager accepts. Subject `memories`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.laugh/1   [65 chars]
    en  You'd forgotten! I've told that one at every feast for six years.
    >>  ............................................
    pt  Você tinha esquecido! Já contei essa em toda festa por seis anos.
    >>  ............................................
  dialogue.conversations.family.memories.laugh/2   [34 chars]
    en  Ha! Then it's worth telling again.
    >>  ............................................
    pt  Rá! Então vale a pena contar de novo.
    >>  ............................................
  dialogue.conversations.family.memories.laugh/3   [51 chars]
    en  Good. It's better when somebody's hearing it fresh.
    >>  ............................................
    pt  Bom. É melhor quando alguém ouve pela primeira vez.
    >>  ............................................
```


### Button `ask_more` — "What else do you remember?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `family.memories.again.to.memories`, `family.memories.share.to.memories`, `family.memories.tell.to.memories`, `family.memories.toddler.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.ask_more` — accepted phrasings: "what else do you remember"; "what else comes back to you"; "is there more you remember"
  - the message must contain one of: `else`
  - scored words: `else`(1.0), `remember`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.respond.ask_more   [26 chars]
    en  What else do you remember?
    >>  ............................................
    pt  Do que mais você lembra?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `family.memories.ask_more`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — familiarity +4  _(recorded under topic `family.memories.ask_more`)_
- Then opens: `conversations.topic.memories.followup`
- …where the player's next choices will be: "Here's one you don't know." | "Are you sure about that bit?" | "You always tell that one." | "What were the holidays like, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.ask_more
WHO    VILLAGER — what the player reads after pressing "What else do you remember?"
       spoken on: conversations.topic.memories.respond, button `ask_more`
       leaves the player on: conversations.topic.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.ask_more.to.memories`: the villager explains. Subject `memories`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.ask_more/1   [47 chars]
    en  More? Careful — I've a great many and no shame.
    >>  ............................................
    pt  Mais? Cuidado — tenho muitas e nenhuma vergonha.
    >>  ............................................
  dialogue.conversations.family.memories.ask_more/2   [64 chars]
    en  Very well, there's the one about the goat. You'll regret asking.
    >>  ............................................
    pt  Muito bem, tem a do bode. Você vai se arrepender de perguntar.
    >>  ............................................
  dialogue.conversations.family.memories.ask_more/3   [47 chars]
    en  You're the first to ask for a second one, %1$s.
    >>  ............................................
    pt  Você é o primeiro a pedir uma segunda, %1$s.
    >>  ............................................
```


### Button `brush_off` — "That's not how it went."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `family.memories.again.to.memories`, `family.memories.share.to.memories`, `family.memories.tell.to.memories`, `family.memories.toddler.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.brush_off` — accepted phrasings: "you have that wrong"; "that is not what happened"; "you are misremembering it"
  - the message must contain one of: `happened`, `wrong`
  - scored words: `happened`(1.0), `wrong`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.respond.brush_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.respond.brush_off   [23 chars]
    en  That's not how it went.
    >>  ............................................
    pt  Não foi assim que aconteceu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `family.memories.brush_off`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +4  _(recorded under topic `family.memories.brush_off`)_
- Then opens: `conversations.topic.memories.followup`
- …where the player's next choices will be: "Here's one you don't know." | "Are you sure about that bit?" | "You always tell that one." | "What were the holidays like, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.brush_off
WHO    VILLAGER — what the player reads after pressing "That's not how it went."
       spoken on: conversations.topic.memories.respond, button `brush_off`
       leaves the player on: conversations.topic.memories.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.brush_off.to.memories`: the villager resists. Subject `memories`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.brush_off/1   [23 chars]
    en  ...That IS how it went.
    >>  ............................................
    pt  ...Foi ASSIM que aconteceu.
    >>  ............................................
  dialogue.conversations.family.memories.brush_off/2   [31 chars]
    en  You weren't there for that one.
    >>  ............................................
    pt  Você não estava lá nessa.
    >>  ............................................
  dialogue.conversations.family.memories.brush_off/3   [24 chars]
    en  Fine. You tell it, then.
    >>  ............................................
    pt  Tá. Conta você, então.
    >>  ............................................
```


### Button `remember_it_differently` — "That's not quite how I remember it."

*stance family `respectful_disagreement` · tone `gentle` · outcome `engaged` · answers the beat(s) `family.memories.again.to.memories`, `family.memories.share.to.memories`, `family.memories.tell.to.memories`, `family.memories.toddler.to.memories`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `memories.disputed` — accepted phrasings: "that is not how i remember it"; "i remember it differently"; "that is not quite how it went"
  - the message must contain one of: `differently`
  - scored words: `differently`(1.5), `remember`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.memories.respond.remember_it_differently
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.respond.remember_it_differently   [35 chars]
    en  That's not quite how I remember it.
    >>  ............................................
    pt  Não é bem assim que eu lembro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.memories.disputed`
- …where the player's next choices will be: "Perhaps we both have a piece of it." | "Tell me your version properly." | "It doesn't matter much."

```text
POOL   dialogue key: dialogue.conversations.memories.disputed
WHO    VILLAGER — what the player reads after pressing "That's not quite how I remember it."
       spoken on: conversations.topic.memories.respond, button `remember_it_differently`
       leaves the player on: conversations.topic.memories.disputed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.disputed`: the villager qualifys. Subject `memories.disputed`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, exit
```

```text
  dialogue.conversations.memories.disputed/1   [73 chars]
    en  Don't you. Go on, then — I've been telling it my way for years unopposed.
    >>  ............................................
    pt  Não lembra? Então conte — venho contando do meu jeito há anos sem oposição.
    >>  ............................................
  dialogue.conversations.memories.disputed/2   [76 chars]
    en  Maybe not. I've told it so often I'd not swear which parts I've sanded down.
    >>  ............................................
    pt  Talvez não. Contei tantas vezes que não juraria quais partes eu lixei.
    >>  ............................................
  dialogue.conversations.memories.disputed/3   [89 chars]
    en  It is how it went. But say yours and I'll listen, which is more than you'd get elsewhere.
    >>  ............................................
    pt  Foi assim que foi. Mas conte a sua e eu escuto, o que é mais do que teria em outro lugar.
    >>  ............................................
```


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `family.memories.again.to.memories`, `family.memories.share.to.memories`, `family.memories.tell.to.memories`, `family.memories.toddler.to.memories` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.memories.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.memories.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.memories.respond.leave   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.memories.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.topic.memories.respond, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.memories.followup / leave
```

> Written out in full under **`conversations.topic.memories.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

