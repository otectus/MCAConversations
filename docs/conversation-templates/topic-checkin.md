# Topic: checkin

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `checkin` |
| Opened from | question `greet`, button `checkin` |
| Depth class (its heart budget) | `quick` |
| Returns to | `main` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `encouragement`, `dismissal`, `humor`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `greet`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.greet.checkin
WHO    PLAYER — the button that opens this whole conversation
       on the node: greet
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.greet.checkin   [26 chars]
    en  How have you been, really?
    >>  ............................................
    pt  Como você tem passado, de verdade?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.topic.checkin.again.respond`](#conversations-topic-checkin-again-respond)
- [`conversations.topic.checkin.deflated.followup`](#conversations-topic-checkin-deflated-followup)
- [`conversations.topic.checkin.good.followup`](#conversations-topic-checkin-good-followup)
- [`conversations.topic.checkin.good.respond`](#conversations-topic-checkin-good-respond)
- [`conversations.topic.checkin.holiday.followup`](#conversations-topic-checkin-holiday-followup)
- [`conversations.topic.checkin.hurt.followup`](#conversations-topic-checkin-hurt-followup)
- [`conversations.topic.checkin.late.followup`](#conversations-topic-checkin-late-followup)
- [`conversations.topic.checkin.late.respond`](#conversations-topic-checkin-late-respond)
- [`conversations.topic.checkin.rough.dismissed.followup`](#conversations-topic-checkin-rough-dismissed-followup)
- [`conversations.topic.checkin.rough.followup`](#conversations-topic-checkin-rough-followup)
- [`conversations.topic.checkin.rough.respond`](#conversations-topic-checkin-rough-respond)
- [`conversations.topic.checkin.smitten.followup`](#conversations-topic-checkin-smitten-followup)
- [`conversations.topic.checkin.toddler.respond`](#conversations-topic-checkin-toddler-respond)
- [`conversations.topic.checkin.young.respond`](#conversations-topic-checkin-young-respond)

---

## `conversations.topic.checkin.again.respond`

**Reached from 1 route(s):** `greet` / `checkin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.again` — e.g. "You asked me that this morning, %1$s. Still the same answer."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.again.respond   [23 chars]
    en  You did ask me already.
    >>  ............................................
    pt  Você já me perguntou.
    >>  ............................................
```


### Button `apologize` — "Sorry — I've asked you that already."

*stance family `candor` · tone `gentle` · answers the beat(s) `checkin.again.to.checkin.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.again.apologize` — accepted phrasings: "sorry, i have asked already"; "i already asked"; "sorry, my mistake"
  - the message must contain one of: `already`, `sorry`, `asked`
  - scored words: `already`(1.5), `sorry`(1.2), `asked`(0.8), `twice`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.again.respond.apologize   [36 chars]
    en  Sorry — I've asked you that already.
    >>  ............................................
    pt  Desculpa — já te perguntei isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `checkin.again.apologize`)_
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked you that already."
       spoken on: conversations.topic.checkin.again.respond, button `apologize`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.again.apologize.terminal`: the villager accepts. Subject `checkin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.again.apologize/1   [47 chars]
    en  It's alright. Nice to be asked twice, honestly.
    >>  ............................................
    pt  Tudo bem. É bom ser perguntado duas vezes, sinceramente.
    >>  ............................................
  dialogue.conversations.checkin.again.apologize/2   [37 chars]
    en  No harm in it, %1$s. My answer keeps.
    >>  ............................................
    pt  Sem problema, %1$s. Minha resposta se conserva.
    >>  ............................................
  dialogue.conversations.checkin.again.apologize/3   [42 chars]
    en  Happens. I forget who I've told what, too.
    >>  ............................................
    pt  Acontece. Eu também esqueço o que contei para quem.
    >>  ............................................
```


### Button `press` — "Humour me. Really, how are you?"

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `checkin.again.to.checkin.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.again.press` — accepted phrasings: "humour me"; "really, how are you"; "tell me anyway"
  - the message must contain one of: `humour`, `really`, `anyway`
  - scored words: `humour`(1.5), `really`(1.2), `anyway`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.again.respond.press   [31 chars]
    en  Humour me. Really, how are you?
    >>  ............................................
    pt  Me faz a vontade. Sério, como você está?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `checkin.again.press`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — tension +3, respect -1  _(recorded under topic `checkin.again.press`)_
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.again.press
WHO    VILLAGER — what the player reads after pressing "Humour me. Really, how are you?"
       spoken on: conversations.topic.checkin.again.respond, button `press`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.again.press.terminal`: the villager resists. Subject `checkin.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.again.press/1   [56 chars]
    en  The same as an hour ago. I'd tell you if it had changed.
    >>  ............................................
    pt  Do mesmo jeito que há uma hora. Eu te avisaria se tivesse mudado.
    >>  ............................................
  dialogue.conversations.checkin.again.press/2   [75 chars]
    en  Asking twice doesn't get you a different life, %1$s. Just a shorter answer.
    >>  ............................................
    pt  Perguntar duas vezes não te dá uma vida diferente, %1$s. Só uma resposta mais curta.
    >>  ............................................
  dialogue.conversations.checkin.again.press/3   [44 chars]
    en  ...Fine. Still fine. That's the whole of it.
    >>  ............................................
    pt  ...Bem. Ainda bem. É isso.
    >>  ............................................
```


### Button `leave` — "Fair. Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `checkin.again.to.checkin.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.again.respond.leave   [19 chars]
    en  Fair. Another time.
    >>  ............................................
    pt  Justo. Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another time."
       spoken on: conversations.topic.checkin.again.respond, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.again.leave.terminal`: the villager accepts. Subject `checkin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.again.leave/1   [23 chars]
    en  Aye. Catch me tomorrow.
    >>  ............................................
    pt  Tá. Me pega amanhã.
    >>  ............................................
  dialogue.conversations.checkin.again.leave/2   [9 chars]
    en  Quite so.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
  dialogue.conversations.checkin.again.leave/3   [50 chars]
    en  Go on. I'll have changed my answer by then, maybe.
    >>  ............................................
    pt  Pode ir. Talvez eu tenha mudado a resposta até lá.
    >>  ............................................
```

---


## `conversations.topic.checkin.deflated.followup`

**Reached from 1 route(s):** `conversations.topic.checkin.good.respond` / `deflate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.good.deflate` — e.g. "...Aye, probably. Thanks for the reminder, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.deflated.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.deflated.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.deflated.followup   [10 chars]
    en  ...Anyway.
    >>  ............................................
    pt  ...Enfim.
    >>  ............................................
```


### Button `apologize` — "That was a poor thing to say."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `checkin.good.deflated` · offered only once the villager has actually said `player:deflated_good_news`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.deflated.apologize` — accepted phrasings: "that was a poor thing to say"; "that was unkind of me"; "i should not have said that"
  - the message must contain one of: `poor`, `unkind`
  - scored words: `poor`(1.5), `unkind`(1.5), `say`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.deflated.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.deflated.followup.apologize   [29 chars]
    en  That was a poor thing to say.
    >>  ............................................
    pt  Foi uma coisa ruim de se dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.deflated.apologize`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -3, warmth +1  _(recorded under topic `checkin.deflated.apologize`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.deflated.apologize
WHO    VILLAGER — what the player reads after pressing "That was a poor thing to say."
       spoken on: conversations.topic.checkin.deflated.followup, button `apologize`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.deflated.apologize`: the villager qualifys. Subject `checkin.wellbeing`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.deflated.apologize/1   [56 chars]
    en  It was. And now it's said and unsaid, so we'll leave it.
    >>  ............................................
    pt  Foi. E agora foi dito e desdito, então deixamos pra lá.
    >>  ............................................
  dialogue.conversations.checkin.deflated.apologize/2   [58 chars]
    en  So it is. Thank you for noticing without being told, %1$s.
    >>  ............................................
    pt  É assim mesmo. Obrigado por perceber sem eu ter que falar, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.deflated.apologize/3   [46 chars]
    en  ...Right. Well. The week's still good. Mostly.
    >>  ............................................
    pt  ...Certo. Bom. A semana ainda está boa. Quase toda.
    >>  ............................................
```


### Button `restore` — "You've earned the good week. Take it."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `checkin.good.deflated` · offered only once the villager has actually said `state:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.deflated.restore` — accepted phrasings: "you have earned the good week"; "you deserve it, take it"; "take the good week"
  - the message must contain one of: `earned`, `deserve`
  - scored words: `earned`(1.5), `deserve`(1.5), `take`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.deflated.followup.restore
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.deflated.followup.restore   [37 chars]
    en  You've earned the good week. Take it.
    >>  ............................................
    pt  Você merece a boa semana. Aproveite.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.deflated.restore`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, warmth +3  _(recorded under topic `checkin.deflated.restore`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.deflated.restore
WHO    VILLAGER — what the player reads after pressing "You've earned the good week. Take it."
       spoken on: conversations.topic.checkin.deflated.followup, button `restore`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.deflated.restore`: the villager accepts. Subject `checkin.wellbeing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.deflated.restore/1   [54 chars]
    en  ...Earned. I'll take that word and keep it, thank you.
    >>  ............................................
    pt  ...Merecer. Vou ficar com essa palavra, obrigado.
    >>  ............................................
  dialogue.conversations.checkin.deflated.restore/2   [68 chars]
    en  You've a talent for putting a thing back the way you found it, %1$s.
    >>  ............................................
    pt  Você tem talento pra recolocar as coisas como encontrou, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.deflated.restore/3   [34 chars]
    en  Good. Taking it. Watch me take it.
    >>  ............................................
    pt  Bom. Estou aproveitando. Olha só eu aproveitando.
    >>  ............................................
```


### Button `explain` — "I meant it lightly."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `checkin.good.deflated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.deflated.explain` — accepted phrasings: "i meant it lightly"; "it was a joke"; "i did not mean anything by it"
  - the message must contain one of: `lightly`, `joke`, `meant`
  - scored words: `lightly`(1.5), `joke`(1.2), `meant`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.deflated.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.deflated.followup.explain   [19 chars]
    en  I meant it lightly.
    >>  ............................................
    pt  Eu falei sem peso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `checkin.deflated.explain`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.deflated.explain
WHO    VILLAGER — what the player reads after pressing "I meant it lightly."
       spoken on: conversations.topic.checkin.deflated.followup, button `explain`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.deflated.explain`: the villager qualifys. Subject `checkin.wellbeing`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.deflated.explain/1   [47 chars]
    en  Most things that land badly were meant lightly.
    >>  ............................................
    pt  A maioria das coisas que caem mal foram ditas sem peso.
    >>  ............................................
  dialogue.conversations.checkin.deflated.explain/2   [54 chars]
    en  I'll take that. Lightly said still has a weight, %1$s.
    >>  ............................................
    pt  Aceito. Mas o que é dito sem peso ainda pesa, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.deflated.explain/3   [68 chars]
    en  Then say the light thing lighter next time and we'll both be spared.
    >>  ............................................
    pt  Então da próxima diga a coisa leve mais leve e a gente se poupa.
    >>  ............................................
```


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.good.deflated` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.deflated.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.deflated.followup.leave   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.deflated.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.checkin.deflated.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.deflated.leave`: the villager accepts. Subject `checkin.wellbeing`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.deflated.leave/1   [16 chars]
    en  True enough. Do.
    >>  ............................................
    pt  Bem verdade. Pode ir.
    >>  ............................................
  dialogue.conversations.checkin.deflated.leave/2   [17 chars]
    en  So you are, %1$s.
    >>  ............................................
    pt  Pois é, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.deflated.leave/3   [3 chars]
    en  Mm.
    >>  ............................................
    pt  Mm.
    >>  ............................................
```

---


## `conversations.topic.checkin.good.followup`

**Reached from 3 route(s):** `conversations.topic.checkin.good.respond` / `glad`; `conversations.topic.checkin.good.respond` / `glad`; `conversations.topic.checkin.good.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.glad.grateful` — e.g. "Better than I was — that %2$s you brought me is still doing its work."
- `conversations.checkin.good.ask_more` — e.g. "Small things stacking the right way for once. That's all it takes."
- `conversations.checkin.good.glad` — e.g. "Thank you. People check in when it's bad and vanish when it isn't."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.good.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.good.followup   [25 chars]
    en  Anyway. Long may it last.
    >>  ............................................
    pt  Enfim. Que dure.
    >>  ............................................
```


### Button `keep_it_up` — "Whatever you're doing, keep at it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `checkin.good.contentment`, `checkin.good.cause_told`, `checkin.good.gift_remembered` · offered only once the villager has actually said `state:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.good.keep_it_up` — accepted phrasings: "keep at it"; "keep it up"; "carry on with that"; "continue doing that"
  - the message must contain one of: `keep`, `carry`, `continue`
  - scored words: `keep`(1.5), `carry`(1.0), `doing`(0.8), `continue`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.followup.keep_it_up
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.followup.keep_it_up   [34 chars]
    en  Whatever you're doing, keep at it.
    >>  ............................................
    pt  Seja lá o que estiver fazendo, continue.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.good.keep_it_up`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +1  _(recorded under topic `checkin.good.keep_it_up`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.keep_it_up
WHO    VILLAGER — what the player reads after pressing "Whatever you're doing, keep at it."
       spoken on: conversations.topic.checkin.good.followup, button `keep_it_up`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.keep_it_up`: the villager accepts. Subject `checkin.wellbeing`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.keep_it_up/1   [64 chars]
    en  I intend to. Ask me again in a month and we'll see if I managed.
    >>  ............................................
    pt  Pretendo. Me pergunte de novo em um mês e vemos se consegui.
    >>  ............................................
  dialogue.conversations.checkin.good.keep_it_up/2   [50 chars]
    en  That's the plan. Plans and I have a history, mind.
    >>  ............................................
    pt  Esse é o plano. Eu e planos temos um histórico, veja bem.
    >>  ............................................
  dialogue.conversations.checkin.good.keep_it_up/3   [62 chars]
    en  Quite. Nice to be told to keep going rather than to slow down.
    >>  ............................................
    pt  Exato. Bom ouvir para continuar em vez de para diminuir o ritmo.
    >>  ............................................
```


### Button `tease` — "Careful, you'll ruin your reputation."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `checkin.good.contentment`, `checkin.good.cause_told`, `checkin.good.gift_remembered` · offered only once the villager has actually said `state:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.good.tease` — accepted phrasings: "careful, you will ruin your reputation"; "mind your reputation"; "you will ruin it"
  - the message must contain one of: `reputation`, `careful`, `ruin`
  - scored words: `reputation`(1.5), `careful`(1.2), `ruin`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.followup.tease
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.followup.tease   [37 chars]
    en  Careful, you'll ruin your reputation.
    >>  ............................................
    pt  Cuidado, vai estragar sua reputação.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`
- Does: **hearts +1** — decision id `checkin.good.tease`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `checkin.good.tease`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.tease.landed
WHO    VILLAGER — what the player reads after pressing "Careful, you'll ruin your reputation."
       spoken on: conversations.topic.checkin.good.followup, button `tease`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.tease.landed`: the villager accepts. Subject `checkin.wellbeing`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.tease.landed/1   [52 chars]
    en  Ruined already. Word'll be round the well by supper.
    >>  ............................................
    pt  Já estragada. A notícia chega ao poço antes do jantar.
    >>  ............................................
  dialogue.conversations.checkin.good.tease.landed/2   [59 chars]
    en  Hah! Don't you dare tell the baker. They'd never let it go.
    >>  ............................................
    pt  Rá! Não ouse contar lá na padaria. Nunca mais me deixariam esquecer.
    >>  ............................................
  dialogue.conversations.checkin.good.tease.landed/3   [47 chars]
    en  A reputation for cheerfulness. The shame of it.
    >>  ............................................
    pt  Uma reputação de alegria. Que vergonha.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`
- Does: **hearts -1** — decision id `checkin.good.tease`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `checkin.good.tease`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.tease.flat
WHO    VILLAGER — what the player reads after pressing "Careful, you'll ruin your reputation."
       spoken on: conversations.topic.checkin.good.followup, button `tease`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.tease.flat`: the villager hurts. Subject `checkin.wellbeing`, polarity `negative`, ends conversation, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.tease.flat/1   [31 chars]
    en  Must you? It was a nice moment.
    >>  ............................................
    pt  Precisa mesmo? Era um momento bom.
    >>  ............................................
  dialogue.conversations.checkin.good.tease.flat/2   [49 chars]
    en  I'm allowed a good week without commentary, %1$s.
    >>  ............................................
    pt  Tenho direito a uma semana boa sem comentários, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.good.tease.flat/3   [51 chars]
    en  Mm. And now I'm self-conscious about it. Well done.
    >>  ............................................
    pt  Hm. E agora fiquei constrangido. Parabéns.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.good.tease.flat/1
    en  ...That stung more than you meant it to, I think.
    >>  ............................................
    pt  ...Isso doeu mais do que você queria, eu acho.
    >>  ............................................
  anxious.dialogue.conversations.checkin.good.tease.flat/2
    en  Please don't. It was a good moment and it was quite a small one.
    >>  ............................................
    pt  Por favor, não. Era um momento bom e era bem pequeno.
    >>  ............................................
  anxious.dialogue.conversations.checkin.good.tease.flat/3
    en  ...I'd only just started feeling all right about it, %1$s.
    >>  ............................................
    pt  ...Eu tinha acabado de começar a me sentir bem com isso, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. Let it be a nice moment a while longer.
    >>  ............................................
    pt  Precisava? Deixe ser um momento bom mais um pouco.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Not that one. There's no hurry to spoil it.
    >>  ............................................
    pt  ...Essa não. Não tem pressa de estragar.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good.tease.flat/3
    en  Mm. I'd have left that where it was.
    >>  ............................................
    pt  Hum. Eu teria deixado isso onde estava.
    >>  ............................................
  confident.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. It was a nice moment and now it isn't.
    >>  ............................................
    pt  Precisava? Era um momento bom e agora não é.
    >>  ............................................
  confident.dialogue.conversations.checkin.good.tease.flat/2
    en  That was unnecessary. I'll not pretend otherwise to spare you.
    >>  ............................................
    pt  Isso foi desnecessário. Não vou fingir o contrário pra te poupar.
    >>  ............................................
  confident.dialogue.conversations.checkin.good.tease.flat/3
    en  No. Not that, not today.
    >>  ............................................
    pt  Não. Isso não, hoje não.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. It was a nice moment and now it isn't.
    >>  ............................................
    pt  Precisava? Era um momento bom e agora não é.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good.tease.flat/2
    en  That was unnecessary. I'll not pretend otherwise to spare you.
    >>  ............................................
    pt  Isso foi desnecessário. Não vou fingir o contrário pra te poupar.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good.tease.flat/3
    en  No. Not that, not today.
    >>  ............................................
    pt  Não. Isso não, hoje não.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good.tease.flat/1
    en  ...Not that. I'd been enjoying this and you know I had.
    >>  ............................................
    pt  ...Isso não. Eu estava gostando disso e você sabe que estava.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good.tease.flat/2
    en  That's the one thing I'd have asked you not to say just now.
    >>  ............................................
    pt  Era a única coisa que eu teria pedido pra você não dizer agora.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good.tease.flat/3
    en  ...Oh, don't, %1$s. Not while it was going well.
    >>  ............................................
    pt  ...Ah, não faça isso, %1$s. Não enquanto ia bem.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good.tease.flat/1
    en  ...Not that. I'd been enjoying this and you know I had.
    >>  ............................................
    pt  ...Isso não. Eu estava gostando disso e você sabe que estava.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good.tease.flat/2
    en  That's the one thing I'd have asked you not to say just now.
    >>  ............................................
    pt  Era a única coisa que eu teria pedido pra você não dizer agora.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good.tease.flat/3
    en  ...Oh, don't, %1$s. Not while it was going well.
    >>  ............................................
    pt  ...Ah, não faça isso, %1$s. Não enquanto ia bem.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good.tease.flat/1
    en  ...Not that. I'd been enjoying this and you know I had.
    >>  ............................................
    pt  ...Isso não. Eu estava gostando disso e você sabe que estava.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good.tease.flat/2
    en  That's the one thing I'd have asked you not to say just now.
    >>  ............................................
    pt  Era a única coisa que eu teria pedido pra você não dizer agora.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good.tease.flat/3
    en  ...Oh, don't, %1$s. Not while it was going well.
    >>  ............................................
    pt  ...Ah, não faça isso, %1$s. Não enquanto ia bem.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good.tease.flat/1
    en  ...That stung more than you meant it to, I think.
    >>  ............................................
    pt  ...Isso doeu mais do que você queria, eu acho.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good.tease.flat/2
    en  Please don't. It was a good moment and it was quite a small one.
    >>  ............................................
    pt  Por favor, não. Era um momento bom e era bem pequeno.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good.tease.flat/3
    en  ...I'd only just started feeling all right about it, %1$s.
    >>  ............................................
    pt  ...Eu tinha acabado de começar a me sentir bem com isso, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. It was a nice moment and now it isn't.
    >>  ............................................
    pt  Precisava? Era um momento bom e agora não é.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good.tease.flat/2
    en  That was unnecessary. I'll not pretend otherwise to spare you.
    >>  ............................................
    pt  Isso foi desnecessário. Não vou fingir o contrário pra te poupar.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good.tease.flat/3
    en  No. Not that, not today.
    >>  ............................................
    pt  Não. Isso não, hoje não.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. It was a nice moment and now it isn't.
    >>  ............................................
    pt  Precisava? Era um momento bom e agora não é.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good.tease.flat/2
    en  That was unnecessary. I'll not pretend otherwise to spare you.
    >>  ............................................
    pt  Isso foi desnecessário. Não vou fingir o contrário pra te poupar.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good.tease.flat/3
    en  No. Not that, not today.
    >>  ............................................
    pt  Não. Isso não, hoje não.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good.tease.flat/1
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good.tease.flat/2
    en  That wasn't wanted. I'll leave it at that.
    >>  ............................................
    pt  Isso não era desejado. Vou deixar assim.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good.tease.flat/3
    en  ...I'd rather you hadn't.
    >>  ............................................
    pt  ...Eu preferia que você não tivesse dito.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. Let it be a nice moment a while longer.
    >>  ............................................
    pt  Precisava? Deixe ser um momento bom mais um pouco.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Not that one. There's no hurry to spoil it.
    >>  ............................................
    pt  ...Essa não. Não tem pressa de estragar.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good.tease.flat/3
    en  Mm. I'd have left that where it was.
    >>  ............................................
    pt  Hum. Eu teria deixado isso onde estava.
    >>  ............................................
  odd.dialogue.conversations.checkin.good.tease.flat/1
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  odd.dialogue.conversations.checkin.good.tease.flat/2
    en  That wasn't wanted. I'll leave it at that.
    >>  ............................................
    pt  Isso não era desejado. Vou deixar assim.
    >>  ............................................
  odd.dialogue.conversations.checkin.good.tease.flat/3
    en  ...I'd rather you hadn't.
    >>  ............................................
    pt  ...Eu preferia que você não tivesse dito.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. Let it be a nice moment a while longer.
    >>  ............................................
    pt  Precisava? Deixe ser um momento bom mais um pouco.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Not that one. There's no hurry to spoil it.
    >>  ............................................
    pt  ...Essa não. Não tem pressa de estragar.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good.tease.flat/3
    en  Mm. I'd have left that where it was.
    >>  ............................................
    pt  Hum. Eu teria deixado isso onde estava.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good.tease.flat/1
    en  Oof. That one didn't land where you meant it to, %1$s.
    >>  ............................................
    pt  Uf. Essa não caiu onde você queria, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Right, that's the joke over. Even I have a floor.
    >>  ............................................
    pt  ...Certo, a piada acabou. Até eu tenho um limite.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good.tease.flat/3
    en  Wrong moment for that one. I'd know — I make most of them.
    >>  ............................................
    pt  Momento errado pra essa. Eu saberia — eu faço quase todas.
    >>  ............................................
  playful.dialogue.conversations.checkin.good.tease.flat/1
    en  Oof. That one didn't land where you meant it to, %1$s.
    >>  ............................................
    pt  Uf. Essa não caiu onde você queria, %1$s.
    >>  ............................................
  playful.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Right, that's the joke over. Even I have a floor.
    >>  ............................................
    pt  ...Certo, a piada acabou. Até eu tenho um limite.
    >>  ............................................
  playful.dialogue.conversations.checkin.good.tease.flat/3
    en  Wrong moment for that one. I'd know — I make most of them.
    >>  ............................................
    pt  Momento errado pra essa. Eu saberia — eu faço quase todas.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good.tease.flat/1
    en  Must you. Let it be a nice moment a while longer.
    >>  ............................................
    pt  Precisava? Deixe ser um momento bom mais um pouco.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Not that one. There's no hurry to spoil it.
    >>  ............................................
    pt  ...Essa não. Não tem pressa de estragar.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good.tease.flat/3
    en  Mm. I'd have left that where it was.
    >>  ............................................
    pt  Hum. Eu teria deixado isso onde estava.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good.tease.flat/1
    en  ...That stung more than you meant it to, I think.
    >>  ............................................
    pt  ...Isso doeu mais do que você queria, eu acho.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good.tease.flat/2
    en  Please don't. It was a good moment and it was quite a small one.
    >>  ............................................
    pt  Por favor, não. Era um momento bom e era bem pequeno.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good.tease.flat/3
    en  ...I'd only just started feeling all right about it, %1$s.
    >>  ............................................
    pt  ...Eu tinha acabado de começar a me sentir bem com isso, %1$s.
    >>  ............................................
  shy.dialogue.conversations.checkin.good.tease.flat/1
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  shy.dialogue.conversations.checkin.good.tease.flat/2
    en  That wasn't wanted. I'll leave it at that.
    >>  ............................................
    pt  Isso não era desejado. Vou deixar assim.
    >>  ............................................
  shy.dialogue.conversations.checkin.good.tease.flat/3
    en  ...I'd rather you hadn't.
    >>  ............................................
    pt  ...Eu preferia que você não tivesse dito.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good.tease.flat/1
    en  Oof. That one didn't land where you meant it to, %1$s.
    >>  ............................................
    pt  Uf. Essa não caiu onde você queria, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Right, that's the joke over. Even I have a floor.
    >>  ............................................
    pt  ...Certo, a piada acabou. Até eu tenho um limite.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good.tease.flat/3
    en  Wrong moment for that one. I'd know — I make most of them.
    >>  ............................................
    pt  Momento errado pra essa. Eu saberia — eu faço quase todas.
    >>  ............................................
  witty.dialogue.conversations.checkin.good.tease.flat/1
    en  Oof. That one didn't land where you meant it to, %1$s.
    >>  ............................................
    pt  Uf. Essa não caiu onde você queria, %1$s.
    >>  ............................................
  witty.dialogue.conversations.checkin.good.tease.flat/2
    en  ...Right, that's the joke over. Even I have a floor.
    >>  ............................................
    pt  ...Certo, a piada acabou. Até eu tenho um limite.
    >>  ............................................
  witty.dialogue.conversations.checkin.good.tease.flat/3
    en  Wrong moment for that one. I'd know — I make most of them.
    >>  ............................................
    pt  Momento errado pra essa. Eu saberia — eu faço quase todas.
    >>  ............................................
```

</details>


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`  _(chance -2000)_
- Does: disposition — warmth +1  _(recorded under topic `checkin.good.tease`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.tease.polite
WHO    VILLAGER — what the player reads after pressing "Careful, you'll ruin your reputation."
       spoken on: conversations.topic.checkin.good.followup, button `tease`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.tease.polite`: the villager accepts. Subject `checkin.wellbeing`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.tease.polite/1   [38 chars]
    en  Let them talk. I've had worse rumours.
    >>  ............................................
    pt  Que falem. Já tive boatos piores.
    >>  ............................................
  dialogue.conversations.checkin.good.tease.polite/2   [43 chars]
    en  It'll pass before anyone notices, I expect.
    >>  ............................................
    pt  Vai passar antes de alguém notar, imagino.
    >>  ............................................
  dialogue.conversations.checkin.good.tease.polite/3   [22 chars]
    en  Heh. A risk I'll take.
    >>  ............................................
    pt  Rá. Um risco que eu corro.
    >>  ............................................
```


### Button `share_own` — "Things have been alright with me too."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `checkin.good.contentment`, `checkin.good.cause_told`, `checkin.good.gift_remembered`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.good.share_own` — accepted phrasings: "things have been alright with me too"; "same with me"; "mine has been alright too"
  - the message must contain one of: `too`, `alright`, `mine`
  - scored words: `me`(0.4), `too`(1.2), `alright`(1.2), `mine`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.followup.share_own   [37 chars]
    en  Things have been alright with me too.
    >>  ............................................
    pt  As coisas também têm ido bem comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.good.share_own`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `checkin.good.share_own`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.share_own
WHO    VILLAGER — what the player reads after pressing "Things have been alright with me too."
       spoken on: conversations.topic.checkin.good.followup, button `share_own`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.share_own`: the villager celebrates. Subject `checkin.wellbeing`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.share_own/1   [62 chars]
    en  Two of us doing alright at once. The village won't survive it.
    >>  ............................................
    pt  Nós dois bem ao mesmo tempo. A vila não vai sobreviver.
    >>  ............................................
  dialogue.conversations.checkin.good.share_own/2   [73 chars]
    en  Good. It's better when it's going round rather than pooling in one house.
    >>  ............................................
    pt  Bom. É melhor quando circula em vez de se acumular numa casa só.
    >>  ............................................
  dialogue.conversations.checkin.good.share_own/3   [62 chars]
    en  Then we're both ahead. Let's not examine it too closely, %1$s.
    >>  ............................................
    pt  Então nós dois estamos ganhando. Vamos não analisar muito, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you enjoy it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.good.contentment`, `checkin.good.cause_told`, `checkin.good.gift_remembered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.followup.leave   [22 chars]
    en  I'll let you enjoy it.
    >>  ............................................
    pt  Vou deixar você aproveitar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you enjoy it."
       spoken on: conversations.topic.checkin.good.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.leave`: the villager accepts. Subject `checkin.wellbeing`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.checkin.good.respond / leave
```

```text
  dialogue.conversations.checkin.good.leave/1   [29 chars]
    en  Aye, off you go. Enjoy yours.
    >>  ............................................
    pt  Tá, pode ir. Aproveite o seu.
    >>  ............................................
  dialogue.conversations.checkin.good.leave/2   [24 chars]
    en  Just so. Good day, %1$s.
    >>  ............................................
    pt  Exato. Bom dia, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.good.leave/3   [47 chars]
    en  Go on. I'll be here being suspiciously content.
    >>  ............................................
    pt  Pode ir. Vou ficar aqui suspeitosamente contente.
    >>  ............................................
```

---


## `conversations.topic.checkin.good.respond`

**Reached from 1 route(s):** `greet` / `checkin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.good` — e.g. "Honestly? Pretty good. The bread turned out right and nobody's yelled at me yet."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.good.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.good.respond   [30 chars]
    en  That's the shape of me lately.
    >>  ............................................
    pt  É essa a minha forma ultimamente.
    >>  ............................................
```


### Button `glad` — "I'm glad. Genuinely."

*stance family `encouragement` · tone `plain` · answers the beat(s) `checkin.good.to.checkin.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.good.glad` — accepted phrasings: "i am glad"; "genuinely glad"; "that pleases me"; "glad to hear it"
  - the message must contain one of: `glad`, `genuinely`, `pleased`
  - scored words: `glad`(1.5), `genuinely`(1.5), `pleased`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.respond.glad   [20 chars]
    en  I'm glad. Genuinely.
    >>  ............................................
    pt  Que bom. De verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.grateful` (this player only)
- Fires when: RULED OUT when the `states` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `checkin.good.glad`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `checkin.good.glad`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.good.followup`
- …where the player's next choices will be: "Whatever you're doing, keep at it." | "Careful, you'll ruin your reputation." | "Things have been alright with me too." | "I'll let you enjoy it."

```text
POOL   dialogue key: dialogue.conversations.checkin.glad.grateful
WHO    VILLAGER — what the player reads after pressing "I'm glad. Genuinely."
       spoken on: conversations.topic.checkin.good.respond, button `glad`
       leaves the player on: conversations.topic.checkin.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = last_gift_item
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.gift_remembered`: the villager accepts. Subject `checkin.wellbeing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `state:good`, `gift:remembered` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, humor, self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.glad.grateful/1   [69 chars]
    en  Better than I was — that %2$s you brought me is still doing its work.
    >>  ............................................
    pt  Melhor do que eu estava — aquele %2$s que você me trouxe ainda está fazendo efeito.
    >>  ............................................
  dialogue.conversations.checkin.glad.grateful/2   [71 chars]
    en  Well enough. I've the %2$s you gave me on the shelf where I can see it.
    >>  ............................................
    pt  Bem o bastante. Tenho o %2$s que você me deu na prateleira, onde eu consigo ver.
    >>  ............................................
  dialogue.conversations.checkin.glad.grateful/3   [57 chars]
    en  Good, and partly because of the %2$s. I've not forgotten.
    >>  ............................................
    pt  Bem, e em parte por causa do %2$s. Não esqueci.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: RULED OUT when has the memory `mcaconversations.state.grateful` (this player only)  _(chance -2000)_
- Fires when: weighted +100 when `min_health` = 1
- Fires when: RULED OUT when `min_health` = 10  _(chance -2000)_
- Does: **hearts +1** — decision id `checkin.good.glad`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `checkin.good.glad`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.hurt.followup`
- …where the player's next choices will be: "It looks worse than it is." | "...Alright. Just for a moment." | "Have you anything for it?" | "I've had worse. Leave it." | "I'll manage."

```text
POOL   dialogue key: dialogue.conversations.checkin.glad.hurt
WHO    VILLAGER — what the player reads after pressing "I'm glad. Genuinely."
       spoken on: conversations.topic.checkin.good.respond, button `glad`
       leaves the player on: conversations.topic.checkin.hurt.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.player_hurt`: the villager asks. Subject `checkin.player_health`, polarity `negative`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `player:injured` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, candor, practical_help, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.glad.hurt/1   [70 chars]
    en  ...You're bleeding. Sit down before you tell me how well you're doing.
    >>  ............................................
    pt  ...Você está sangrando. Senta antes de me contar como está indo bem.
    >>  ............................................
  dialogue.conversations.checkin.glad.hurt/2   [77 chars]
    en  Glad, are you. You look like you lost an argument with something large, %1$s.
    >>  ............................................
    pt  Bem, é? Você parece ter perdido uma discussão com algo grande, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.glad.hurt/3   [50 chars]
    en  Don't 'glad' me. Something's had a go at you. Sit.
    >>  ............................................
    pt  Não me venha com 'bem'. Alguma coisa te pegou. Senta.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when `min_health` = 1  _(chance -2000)_
- Does: **hearts +1** — decision id `checkin.good.glad`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `checkin.good.glad`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.good.followup`
- …where the player's next choices will be: "Whatever you're doing, keep at it." | "Careful, you'll ruin your reputation." | "Things have been alright with me too." | "I'll let you enjoy it."

```text
POOL   dialogue key: dialogue.conversations.checkin.good.glad
WHO    VILLAGER — what the player reads after pressing "I'm glad. Genuinely."
       spoken on: conversations.topic.checkin.good.respond, button `glad`
       leaves the player on: conversations.topic.checkin.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.contentment`: the villager accepts. Subject `checkin.wellbeing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `state:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, humor, self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.glad/1   [66 chars]
    en  Thank you. People check in when it's bad and vanish when it isn't.
    >>  ............................................
    pt  Obrigado. As pessoas perguntam quando está ruim e somem quando não está.
    >>  ............................................
  dialogue.conversations.checkin.good.glad/2   [40 chars]
    en  Genuinely, you say. I'll take genuinely.
    >>  ............................................
    pt  De verdade, você diz. Aceito de verdade.
    >>  ............................................
  dialogue.conversations.checkin.good.glad/3   [37 chars]
    en  So am I, honestly. It's been a while.
    >>  ............................................
    pt  Eu também, sinceramente. Já fazia um tempo.
    >>  ............................................
```


### Button `ask_more` — "What's been going right?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `checkin.good.to.checkin.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.good.ask_more` — accepted phrasings: "what has been going right"; "what is going well"; "what has gone right"
  - the message must contain one of: `going`, `right`, `well`
  - scored words: `going`(1.2), `right`(1.2), `what`(0.5), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.respond.ask_more   [24 chars]
    en  What's been going right?
    >>  ............................................
    pt  O que tem dado certo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.smitten` (this player only)
- Fires when: RULED OUT when the `states` feature is OFF  _(chance -2000)_
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `checkin.good.ask_more`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.smitten.followup`
- …where the player's next choices will be: "Go on, then. The long version." | "I'd listen to you for an hour." | "Let's keep to the weather for now." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.checkin.ask_more.smitten
WHO    VILLAGER — what the player reads after pressing "What's been going right?"
       spoken on: conversations.topic.checkin.good.respond, button `ask_more`
       leaves the player on: conversations.topic.checkin.smitten.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.smitten.offered`: the villager invites. Subject `checkin.affection`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `state:good`, `villager:smitten` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, flirtation, self_disclosure, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.ask_more.smitten/1   [96 chars]
    en  ...More. Right. I've been trying to think of a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  ...Mais. Certo. Eu estava tentando pensar num motivo para te segurar aqui e você me deu um.
    >>  ............................................
  dialogue.conversations.checkin.ask_more.smitten/2   [78 chars]
    en  You want the long version? From you, %1$s, you can have the very long version.
    >>  ............................................
    pt  Você quer a versão longa? Vinda de você, %1$s, pode ter a versão longuíssima.
    >>  ............................................
  dialogue.conversations.checkin.ask_more.smitten/3   [77 chars]
    en  Ask me anything. Honestly. Ask me about the weather and I'd talk for an hour.
    >>  ............................................
    pt  Me pergunte qualquer coisa. Sério. Me pergunte do tempo que eu falo por uma hora.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.ask_more.smitten/1
    en  ...More. Right. I've been trying to find a reason to keep you here, %1$s, and you've handed me one.
    >>  ............................................
    pt  ...Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui, %1$s, e você me deu um.
    >>  ............................................
  anxious.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. I'd been about to stop, in case I was going on too long.
    >>  ............................................
    pt  Você quer mais. Eu ia parar, com medo de estar me alongando.
    >>  ............................................
  anxious.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. Give me a moment — I'd not expected to be asked.
    >>  ............................................
    pt  Mais, então. Me dê um momento — eu não esperava ser pedido.
    >>  ............................................
  athletic.dialogue.conversations.checkin.ask_more.smitten/1
    en  More, then. There's no hurry on either of us, so I'll take my time with it.
    >>  ............................................
    pt  Mais, então. Nenhum de nós tem pressa, então eu vou com calma.
    >>  ............................................
  athletic.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. Sit down; it's a long evening and there's plenty of it.
    >>  ............................................
    pt  Você quer mais. Bom. Sente-se; a noite é longa e tem bastante.
    >>  ............................................
  athletic.dialogue.conversations.checkin.ask_more.smitten/3
    en  More. Right. I'd been enjoying this and I'm glad it isn't over.
    >>  ............................................
    pt  Mais. Certo. Eu estava gostando disso e fico contente que não acabou.
    >>  ............................................
  confident.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to find a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  confident.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. I'd been looking for a way to make this last longer.
    >>  ............................................
    pt  Você quer mais. Bom. Eu vinha procurando um jeito de fazer isso durar mais.
    >>  ............................................
  confident.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll not pretend I'm sorry about that.
    >>  ............................................
    pt  Mais, então. Não vou fingir que eu lamento.
    >>  ............................................
  crabby.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to find a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  crabby.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. I'd been looking for a way to make this last longer.
    >>  ............................................
    pt  Você quer mais. Bom. Eu vinha procurando um jeito de fazer isso durar mais.
    >>  ............................................
  crabby.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll not pretend I'm sorry about that.
    >>  ............................................
    pt  Mais, então. Não vou fingir que eu lamento.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to think of a reason to keep you here, %1$s, and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando pensar num motivo pra te segurar aqui, %1$s, e você me deu um.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Then sit down properly, because I've a great deal of it.
    >>  ............................................
    pt  Você quer mais. Então sente direito, porque eu tenho muito.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. That's the best thing anybody's said to me this week.
    >>  ............................................
    pt  Mais, então. É a melhor coisa que alguém me disse esta semana.
    >>  ............................................
  flirty.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to think of a reason to keep you here, %1$s, and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando pensar num motivo pra te segurar aqui, %1$s, e você me deu um.
    >>  ............................................
  flirty.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Then sit down properly, because I've a great deal of it.
    >>  ............................................
    pt  Você quer mais. Então sente direito, porque eu tenho muito.
    >>  ............................................
  flirty.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. That's the best thing anybody's said to me this week.
    >>  ............................................
    pt  Mais, então. É a melhor coisa que alguém me disse esta semana.
    >>  ............................................
  friendly.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to think of a reason to keep you here, %1$s, and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando pensar num motivo pra te segurar aqui, %1$s, e você me deu um.
    >>  ............................................
  friendly.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Then sit down properly, because I've a great deal of it.
    >>  ............................................
    pt  Você quer mais. Então sente direito, porque eu tenho muito.
    >>  ............................................
  friendly.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. That's the best thing anybody's said to me this week.
    >>  ............................................
    pt  Mais, então. É a melhor coisa que alguém me disse esta semana.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.ask_more.smitten/1
    en  ...More. Right. I've been trying to find a reason to keep you here, %1$s, and you've handed me one.
    >>  ............................................
    pt  ...Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui, %1$s, e você me deu um.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. I'd been about to stop, in case I was going on too long.
    >>  ............................................
    pt  Você quer mais. Eu ia parar, com medo de estar me alongando.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. Give me a moment — I'd not expected to be asked.
    >>  ............................................
    pt  Mais, então. Me dê um momento — eu não esperava ser pedido.
    >>  ............................................
  greedy.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to find a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  greedy.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. I'd been looking for a way to make this last longer.
    >>  ............................................
    pt  Você quer mais. Bom. Eu vinha procurando um jeito de fazer isso durar mais.
    >>  ............................................
  greedy.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll not pretend I'm sorry about that.
    >>  ............................................
    pt  Mais, então. Não vou fingir que eu lamento.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.ask_more.smitten/1
    en  More. Right. I've been trying to find a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. I'd been looking for a way to make this last longer.
    >>  ............................................
    pt  Você quer mais. Bom. Eu vinha procurando um jeito de fazer isso durar mais.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll not pretend I'm sorry about that.
    >>  ............................................
    pt  Mais, então. Não vou fingir que eu lamento.
    >>  ............................................
  introverted.dialogue.conversations.checkin.ask_more.smitten/1
    en  ...More. Right.
    >>  ............................................
    pt  ...Mais. Certo.
    >>  ............................................
  introverted.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Alright.
    >>  ............................................
    pt  Você quer mais. Está bem.
    >>  ............................................
  introverted.dialogue.conversations.checkin.ask_more.smitten/3
    en  ...Then I'll go on.
    >>  ............................................
    pt  ...Então eu continuo.
    >>  ............................................
  lazy.dialogue.conversations.checkin.ask_more.smitten/1
    en  More, then. There's no hurry on either of us, so I'll take my time with it.
    >>  ............................................
    pt  Mais, então. Nenhum de nós tem pressa, então eu vou com calma.
    >>  ............................................
  lazy.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. Sit down; it's a long evening and there's plenty of it.
    >>  ............................................
    pt  Você quer mais. Bom. Sente-se; a noite é longa e tem bastante.
    >>  ............................................
  lazy.dialogue.conversations.checkin.ask_more.smitten/3
    en  More. Right. I'd been enjoying this and I'm glad it isn't over.
    >>  ............................................
    pt  Mais. Certo. Eu estava gostando disso e fico contente que não acabou.
    >>  ............................................
  odd.dialogue.conversations.checkin.ask_more.smitten/1
    en  ...More. Right.
    >>  ............................................
    pt  ...Mais. Certo.
    >>  ............................................
  odd.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Alright.
    >>  ............................................
    pt  Você quer mais. Está bem.
    >>  ............................................
  odd.dialogue.conversations.checkin.ask_more.smitten/3
    en  ...Then I'll go on.
    >>  ............................................
    pt  ...Então eu continuo.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.ask_more.smitten/1
    en  More, then. There's no hurry on either of us, so I'll take my time with it.
    >>  ............................................
    pt  Mais, então. Nenhum de nós tem pressa, então eu vou com calma.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. Sit down; it's a long evening and there's plenty of it.
    >>  ............................................
    pt  Você quer mais. Bom. Sente-se; a noite é longa e tem bastante.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.ask_more.smitten/3
    en  More. Right. I'd been enjoying this and I'm glad it isn't over.
    >>  ............................................
    pt  Mais. Certo. Eu estava gostando disso e fico contente que não acabou.
    >>  ............................................
  peppy.dialogue.conversations.checkin.ask_more.smitten/1
    en  More! Right. I've been trying to think of a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais! Certo. Eu vinha tentando pensar num motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  peppy.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more! Excellent. I had about four more things and no excuse to say them.
    >>  ............................................
    pt  Você quer mais! Excelente. Eu tinha umas quatro coisas e nenhuma desculpa pra dizer.
    >>  ............................................
  peppy.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll take that and I'll be unbearable about it later.
    >>  ............................................
    pt  Mais, então. Eu aceito e vou ser insuportável sobre isso depois.
    >>  ............................................
  playful.dialogue.conversations.checkin.ask_more.smitten/1
    en  More! Right. I've been trying to think of a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais! Certo. Eu vinha tentando pensar num motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  playful.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more! Excellent. I had about four more things and no excuse to say them.
    >>  ............................................
    pt  Você quer mais! Excelente. Eu tinha umas quatro coisas e nenhuma desculpa pra dizer.
    >>  ............................................
  playful.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll take that and I'll be unbearable about it later.
    >>  ............................................
    pt  Mais, então. Eu aceito e vou ser insuportável sobre isso depois.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.ask_more.smitten/1
    en  More, then. There's no hurry on either of us, so I'll take my time with it.
    >>  ............................................
    pt  Mais, então. Nenhum de nós tem pressa, então eu vou com calma.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Good. Sit down; it's a long evening and there's plenty of it.
    >>  ............................................
    pt  Você quer mais. Bom. Sente-se; a noite é longa e tem bastante.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.ask_more.smitten/3
    en  More. Right. I'd been enjoying this and I'm glad it isn't over.
    >>  ............................................
    pt  Mais. Certo. Eu estava gostando disso e fico contente que não acabou.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.ask_more.smitten/1
    en  ...More. Right. I've been trying to find a reason to keep you here, %1$s, and you've handed me one.
    >>  ............................................
    pt  ...Mais. Certo. Eu vinha tentando achar um motivo pra te segurar aqui, %1$s, e você me deu um.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. I'd been about to stop, in case I was going on too long.
    >>  ............................................
    pt  Você quer mais. Eu ia parar, com medo de estar me alongando.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. Give me a moment — I'd not expected to be asked.
    >>  ............................................
    pt  Mais, então. Me dê um momento — eu não esperava ser pedido.
    >>  ............................................
  shy.dialogue.conversations.checkin.ask_more.smitten/1
    en  ...More. Right.
    >>  ............................................
    pt  ...Mais. Certo.
    >>  ............................................
  shy.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more. Alright.
    >>  ............................................
    pt  Você quer mais. Está bem.
    >>  ............................................
  shy.dialogue.conversations.checkin.ask_more.smitten/3
    en  ...Then I'll go on.
    >>  ............................................
    pt  ...Então eu continuo.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.ask_more.smitten/1
    en  More! Right. I've been trying to think of a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais! Certo. Eu vinha tentando pensar num motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more! Excellent. I had about four more things and no excuse to say them.
    >>  ............................................
    pt  Você quer mais! Excelente. Eu tinha umas quatro coisas e nenhuma desculpa pra dizer.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll take that and I'll be unbearable about it later.
    >>  ............................................
    pt  Mais, então. Eu aceito e vou ser insuportável sobre isso depois.
    >>  ............................................
  witty.dialogue.conversations.checkin.ask_more.smitten/1
    en  More! Right. I've been trying to think of a reason to keep you here and you've handed me one.
    >>  ............................................
    pt  Mais! Certo. Eu vinha tentando pensar num motivo pra te segurar aqui e você me deu um.
    >>  ............................................
  witty.dialogue.conversations.checkin.ask_more.smitten/2
    en  You want more! Excellent. I had about four more things and no excuse to say them.
    >>  ............................................
    pt  Você quer mais! Excelente. Eu tinha umas quatro coisas e nenhuma desculpa pra dizer.
    >>  ............................................
  witty.dialogue.conversations.checkin.ask_more.smitten/3
    en  More, then. I'll take that and I'll be unbearable about it later.
    >>  ............................................
    pt  Mais, então. Eu aceito e vou ser insuportável sobre isso depois.
    >>  ............................................
```

</details>


**Outcome 2 of 3** — base weight `0`

- Fires when: RULED OUT when has the memory `mcaconversations.state.smitten` (this player only)  _(chance -2000)_
- Fires when: weighted +100 when the festival is `harvest_festival`
- Fires when: RULED OUT when the `holidays` feature is OFF  _(chance -2000)_
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `checkin.good.ask_more`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.holiday.followup`
- …where the player's next choices will be: "What are the traditions?" | "Enjoy every hour of it." | "I've been eating since dawn myself." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.checkin.ask_more.holiday
WHO    VILLAGER — what the player reads after pressing "What's been going right?"
       spoken on: conversations.topic.checkin.good.respond, button `ask_more`
       leaves the player on: conversations.topic.checkin.holiday.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = holiday
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.holiday.told`: the villager celebrates. Subject `checkin.holiday`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `state:good`, `occasion:holiday` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.ask_more.holiday/1   [70 chars]
    en  More? It's %2$s. I've been eating since dawn and I intend to continue.
    >>  ............................................
    pt  Mais? É %2$s. Estou comendo desde o amanhecer e pretendo continuar.
    >>  ............................................
  dialogue.conversations.checkin.ask_more.holiday/2   [72 chars]
    en  On a day like today, %1$s, the answer to 'how are you' is always 'full'.
    >>  ............................................
    pt  Num dia como hoje, %1$s, a resposta para 'como você está' é sempre 'cheio'.
    >>  ............................................
  dialogue.conversations.checkin.ask_more.holiday/3   [71 chars]
    en  Everything's better today and I'm not going to examine why too closely.
    >>  ............................................
    pt  Tudo está melhor hoje e eu não vou examinar muito o porquê.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the festival is `harvest_festival`  _(chance -2000)_
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `checkin.good.ask_more`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.good.followup`
- …where the player's next choices will be: "Whatever you're doing, keep at it." | "Careful, you'll ruin your reputation." | "Things have been alright with me too." | "I'll let you enjoy it."

```text
POOL   dialogue key: dialogue.conversations.checkin.good.ask_more
WHO    VILLAGER — what the player reads after pressing "What's been going right?"
       spoken on: conversations.topic.checkin.good.respond, button `ask_more`
       leaves the player on: conversations.topic.checkin.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.cause_told`: the villager explains. Subject `checkin.wellbeing`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `state:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, humor, self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.ask_more/1   [66 chars]
    en  Small things stacking the right way for once. That's all it takes.
    >>  ............................................
    pt  Coisinhas se empilhando do jeito certo, para variar. É só isso que precisa.
    >>  ............................................
  dialogue.conversations.checkin.good.ask_more/2   [52 chars]
    en  Sleep, mostly. And nobody needing anything urgently.
    >>  ............................................
    pt  Sono, principalmente. E ninguém precisando de nada com urgência.
    >>  ............................................
  dialogue.conversations.checkin.good.ask_more/3   [65 chars]
    en  The work's been going well, and that spills into everything else.
    >>  ............................................
    pt  O trabalho tem ido bem, e isso transborda para o resto.
    >>  ............................................
```


### Button `deflate` — "Give it a week."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `checkin.good.to.checkin.good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.good.deflate` — accepted phrasings: "give it a week"; "wait a week"; "it will not last"
  - the message must contain one of: `week`, `wait`, `last`
  - scored words: `week`(1.5), `wait`(1.2), `last`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.respond.deflate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.respond.deflate   [15 chars]
    en  Give it a week.
    >>  ............................................
    pt  Espera uma semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `checkin.good.deflate`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `checkin.good.deflate`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.deflated.followup`
- …where the player's next choices will be: "That was a poor thing to say." | "You've earned the good week. Take it." | "I meant it lightly." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.checkin.good.deflate
WHO    VILLAGER — what the player reads after pressing "Give it a week."
       spoken on: conversations.topic.checkin.good.respond, button `deflate`
       leaves the player on: conversations.topic.checkin.deflated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.deflated`: the villager hurts. Subject `checkin.wellbeing`, polarity `negative`, guarded, outcome `hurt`.
NOTE   this is the line that establishes `state:good`, `player:deflated_good_news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.good.deflate/1   [48 chars]
    en  ...Aye, probably. Thanks for the reminder, %1$s.
    >>  ............................................
    pt  ...É, provavelmente. Obrigado pelo lembrete, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.good.deflate/2   [36 chars]
    en  You could have let me have the week.
    >>  ............................................
    pt  Você podia ter me deixado ter a semana.
    >>  ............................................
  dialogue.conversations.checkin.good.deflate/3   [69 chars]
    en  Mm. There's a way of saying that which isn't a curse. That wasn't it.
    >>  ............................................
    pt  Hm. Tem um jeito de dizer isso que não é uma maldição. Não foi esse.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.good.deflate/1
    en  ...I know. I'd only had it for a day, %1$s.
    >>  ............................................
    pt  ...Eu sei. Eu só tinha isso há um dia, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.good.deflate/2
    en  ...You're right. I'd let myself forget for an hour and now I haven't.
    >>  ............................................
    pt  ...Você tem razão. Eu tinha me deixado esquecer por uma hora e agora não esqueci mais.
    >>  ............................................
  anxious.dialogue.conversations.checkin.good.deflate/3
    en  ...Yes. Sorry. I'll not make more of it than it was.
    >>  ............................................
    pt  ...Sim. Desculpe. Não vou fazer disso mais do que foi.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good.deflate/1
    en  ...Aye, probably. It was pleasant while it lasted.
    >>  ............................................
    pt  ...É, provavelmente. Foi agradável enquanto durou.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good.deflate/2
    en  ...Likely so. I'll take the week as it came and not argue with it.
    >>  ............................................
    pt  ...Deve ser. Vou pegar a semana como veio e não discutir com ela.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good.deflate/3
    en  ...True enough. It'll come round again, whichever way you say it.
    >>  ............................................
    pt  ...Bem verdade. Vai voltar, do jeito que você disser.
    >>  ............................................
  confident.dialogue.conversations.checkin.good.deflate/1
    en  ...Right. Noted. I'll be sure not to enjoy the week too loudly.
    >>  ............................................
    pt  ...Certo. Anotado. Vou tomar cuidado pra não curtir a semana alto demais.
    >>  ............................................
  confident.dialogue.conversations.checkin.good.deflate/2
    en  ...You needn't have. I was managing the reminder on my own.
    >>  ............................................
    pt  ...Não precisava. Eu estava dando conta do lembrete sozinho.
    >>  ............................................
  confident.dialogue.conversations.checkin.good.deflate/3
    en  ...Fine. It was a good week. It's less of one now.
    >>  ............................................
    pt  ...Tudo bem. Foi uma boa semana. Agora é menos.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good.deflate/1
    en  ...Right. Noted. I'll be sure not to enjoy the week too loudly.
    >>  ............................................
    pt  ...Certo. Anotado. Vou tomar cuidado pra não curtir a semana alto demais.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good.deflate/2
    en  ...You needn't have. I was managing the reminder on my own.
    >>  ............................................
    pt  ...Não precisava. Eu estava dando conta do lembrete sozinho.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good.deflate/3
    en  ...Fine. It was a good week. It's less of one now.
    >>  ............................................
    pt  ...Tudo bem. Foi uma boa semana. Agora é menos.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good.deflate/1
    en  ...Oh. I'd been about to tell you the rest of it, %1$s.
    >>  ............................................
    pt  ...Ah. Eu ia te contar o resto, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good.deflate/2
    en  ...That's a colder answer than I expected from you. I'll leave it there.
    >>  ............................................
    pt  ...É uma resposta mais fria do que eu esperava de você. Vou deixar aí.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good.deflate/3
    en  ...Right. Well. I'll not go on about it, then.
    >>  ............................................
    pt  ...Certo. Bom. Não vou insistir, então.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good.deflate/1
    en  ...Oh. I'd been about to tell you the rest of it, %1$s.
    >>  ............................................
    pt  ...Ah. Eu ia te contar o resto, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good.deflate/2
    en  ...That's a colder answer than I expected from you. I'll leave it there.
    >>  ............................................
    pt  ...É uma resposta mais fria do que eu esperava de você. Vou deixar aí.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good.deflate/3
    en  ...Right. Well. I'll not go on about it, then.
    >>  ............................................
    pt  ...Certo. Bom. Não vou insistir, então.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good.deflate/1
    en  ...Oh. I'd been about to tell you the rest of it, %1$s.
    >>  ............................................
    pt  ...Ah. Eu ia te contar o resto, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good.deflate/2
    en  ...That's a colder answer than I expected from you. I'll leave it there.
    >>  ............................................
    pt  ...É uma resposta mais fria do que eu esperava de você. Vou deixar aí.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good.deflate/3
    en  ...Right. Well. I'll not go on about it, then.
    >>  ............................................
    pt  ...Certo. Bom. Não vou insistir, então.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good.deflate/1
    en  ...I know. I'd only had it for a day, %1$s.
    >>  ............................................
    pt  ...Eu sei. Eu só tinha isso há um dia, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good.deflate/2
    en  ...You're right. I'd let myself forget for an hour and now I haven't.
    >>  ............................................
    pt  ...Você tem razão. Eu tinha me deixado esquecer por uma hora e agora não esqueci mais.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good.deflate/3
    en  ...Yes. Sorry. I'll not make more of it than it was.
    >>  ............................................
    pt  ...Sim. Desculpe. Não vou fazer disso mais do que foi.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good.deflate/1
    en  ...Right. Noted. I'll be sure not to enjoy the week too loudly.
    >>  ............................................
    pt  ...Certo. Anotado. Vou tomar cuidado pra não curtir a semana alto demais.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good.deflate/2
    en  ...You needn't have. I was managing the reminder on my own.
    >>  ............................................
    pt  ...Não precisava. Eu estava dando conta do lembrete sozinho.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good.deflate/3
    en  ...Fine. It was a good week. It's less of one now.
    >>  ............................................
    pt  ...Tudo bem. Foi uma boa semana. Agora é menos.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good.deflate/1
    en  ...Right. Noted. I'll be sure not to enjoy the week too loudly.
    >>  ............................................
    pt  ...Certo. Anotado. Vou tomar cuidado pra não curtir a semana alto demais.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good.deflate/2
    en  ...You needn't have. I was managing the reminder on my own.
    >>  ............................................
    pt  ...Não precisava. Eu estava dando conta do lembrete sozinho.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good.deflate/3
    en  ...Fine. It was a good week. It's less of one now.
    >>  ............................................
    pt  ...Tudo bem. Foi uma boa semana. Agora é menos.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good.deflate/1
    en  ...Mm. Yes. I'll stop.
    >>  ............................................
    pt  ...Hum. Sim. Eu paro.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good.deflate/2
    en  ...I'd noticed the same thing myself. I was choosing not to say it.
    >>  ............................................
    pt  ...Eu tinha reparado a mesma coisa. Estava escolhendo não dizer.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good.deflate/3
    en  ...Right. That's the week put back in its place.
    >>  ............................................
    pt  ...Certo. A semana voltou pro lugar dela.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good.deflate/1
    en  ...Aye, probably. It was pleasant while it lasted.
    >>  ............................................
    pt  ...É, provavelmente. Foi agradável enquanto durou.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good.deflate/2
    en  ...Likely so. I'll take the week as it came and not argue with it.
    >>  ............................................
    pt  ...Deve ser. Vou pegar a semana como veio e não discutir com ela.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good.deflate/3
    en  ...True enough. It'll come round again, whichever way you say it.
    >>  ............................................
    pt  ...Bem verdade. Vai voltar, do jeito que você disser.
    >>  ............................................
  odd.dialogue.conversations.checkin.good.deflate/1
    en  ...Mm. Yes. I'll stop.
    >>  ............................................
    pt  ...Hum. Sim. Eu paro.
    >>  ............................................
  odd.dialogue.conversations.checkin.good.deflate/2
    en  ...I'd noticed the same thing myself. I was choosing not to say it.
    >>  ............................................
    pt  ...Eu tinha reparado a mesma coisa. Estava escolhendo não dizer.
    >>  ............................................
  odd.dialogue.conversations.checkin.good.deflate/3
    en  ...Right. That's the week put back in its place.
    >>  ............................................
    pt  ...Certo. A semana voltou pro lugar dela.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good.deflate/1
    en  ...Aye, probably. It was pleasant while it lasted.
    >>  ............................................
    pt  ...É, provavelmente. Foi agradável enquanto durou.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good.deflate/2
    en  ...Likely so. I'll take the week as it came and not argue with it.
    >>  ............................................
    pt  ...Deve ser. Vou pegar a semana como veio e não discutir com ela.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good.deflate/3
    en  ...True enough. It'll come round again, whichever way you say it.
    >>  ............................................
    pt  ...Bem verdade. Vai voltar, do jeito que você disser.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good.deflate/1
    en  ...Well. That was a cloud arriving at speed. Thank you for that.
    >>  ............................................
    pt  ...Bom. Foi uma nuvem chegando em alta velocidade. Obrigado por isso.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good.deflate/2
    en  ...Right, yes. Back down to earth. I was quite enjoying the other thing.
    >>  ............................................
    pt  ...Certo, sim. De volta ao chão. Eu estava gostando da outra coisa.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good.deflate/3
    en  ...Ha. Yes. Very good. I'll put the week away, then.
    >>  ............................................
    pt  ...Ha. Sim. Muito bom. Vou guardar a semana, então.
    >>  ............................................
  playful.dialogue.conversations.checkin.good.deflate/1
    en  ...Well. That was a cloud arriving at speed. Thank you for that.
    >>  ............................................
    pt  ...Bom. Foi uma nuvem chegando em alta velocidade. Obrigado por isso.
    >>  ............................................
  playful.dialogue.conversations.checkin.good.deflate/2
    en  ...Right, yes. Back down to earth. I was quite enjoying the other thing.
    >>  ............................................
    pt  ...Certo, sim. De volta ao chão. Eu estava gostando da outra coisa.
    >>  ............................................
  playful.dialogue.conversations.checkin.good.deflate/3
    en  ...Ha. Yes. Very good. I'll put the week away, then.
    >>  ............................................
    pt  ...Ha. Sim. Muito bom. Vou guardar a semana, então.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good.deflate/1
    en  ...Aye, probably. It was pleasant while it lasted.
    >>  ............................................
    pt  ...É, provavelmente. Foi agradável enquanto durou.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good.deflate/2
    en  ...Likely so. I'll take the week as it came and not argue with it.
    >>  ............................................
    pt  ...Deve ser. Vou pegar a semana como veio e não discutir com ela.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good.deflate/3
    en  ...True enough. It'll come round again, whichever way you say it.
    >>  ............................................
    pt  ...Bem verdade. Vai voltar, do jeito que você disser.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good.deflate/1
    en  ...I know. I'd only had it for a day, %1$s.
    >>  ............................................
    pt  ...Eu sei. Eu só tinha isso há um dia, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good.deflate/2
    en  ...You're right. I'd let myself forget for an hour and now I haven't.
    >>  ............................................
    pt  ...Você tem razão. Eu tinha me deixado esquecer por uma hora e agora não esqueci mais.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good.deflate/3
    en  ...Yes. Sorry. I'll not make more of it than it was.
    >>  ............................................
    pt  ...Sim. Desculpe. Não vou fazer disso mais do que foi.
    >>  ............................................
  shy.dialogue.conversations.checkin.good.deflate/1
    en  ...Mm. Yes. I'll stop.
    >>  ............................................
    pt  ...Hum. Sim. Eu paro.
    >>  ............................................
  shy.dialogue.conversations.checkin.good.deflate/2
    en  ...I'd noticed the same thing myself. I was choosing not to say it.
    >>  ............................................
    pt  ...Eu tinha reparado a mesma coisa. Estava escolhendo não dizer.
    >>  ............................................
  shy.dialogue.conversations.checkin.good.deflate/3
    en  ...Right. That's the week put back in its place.
    >>  ............................................
    pt  ...Certo. A semana voltou pro lugar dela.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good.deflate/1
    en  ...Well. That was a cloud arriving at speed. Thank you for that.
    >>  ............................................
    pt  ...Bom. Foi uma nuvem chegando em alta velocidade. Obrigado por isso.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good.deflate/2
    en  ...Right, yes. Back down to earth. I was quite enjoying the other thing.
    >>  ............................................
    pt  ...Certo, sim. De volta ao chão. Eu estava gostando da outra coisa.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good.deflate/3
    en  ...Ha. Yes. Very good. I'll put the week away, then.
    >>  ............................................
    pt  ...Ha. Sim. Muito bom. Vou guardar a semana, então.
    >>  ............................................
  witty.dialogue.conversations.checkin.good.deflate/1
    en  ...Well. That was a cloud arriving at speed. Thank you for that.
    >>  ............................................
    pt  ...Bom. Foi uma nuvem chegando em alta velocidade. Obrigado por isso.
    >>  ............................................
  witty.dialogue.conversations.checkin.good.deflate/2
    en  ...Right, yes. Back down to earth. I was quite enjoying the other thing.
    >>  ............................................
    pt  ...Certo, sim. De volta ao chão. Eu estava gostando da outra coisa.
    >>  ............................................
  witty.dialogue.conversations.checkin.good.deflate/3
    en  ...Ha. Yes. Very good. I'll put the week away, then.
    >>  ............................................
    pt  ...Ha. Sim. Muito bom. Vou guardar a semana, então.
    >>  ............................................
```

</details>


### Button `leave` — "Good to hear. I'll get on."

*stance family `exit` · tone `plain` · answers the beat(s) `checkin.good.to.checkin.good` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.good.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.good.respond.leave   [26 chars]
    en  Good to hear. I'll get on.
    >>  ............................................
    pt  Que bom ouvir. Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good.leave
WHO    VILLAGER — what the player reads after pressing "Good to hear. I'll get on."
       spoken on: conversations.topic.checkin.good.respond, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.leave`: the villager accepts. Subject `checkin.wellbeing`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.checkin.good.followup / leave
```

> Written out in full under **`conversations.topic.checkin.good.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.checkin.holiday.followup`

**Reached from 1 route(s):** `conversations.topic.checkin.good.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.ask_more.holiday` — e.g. "More? It's %2$s. I've been eating since dawn and I intend to continue."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.holiday.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.holiday.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.holiday.followup   [36 chars]
    en  It's the one day nobody keeps count.
    >>  ............................................
    pt  É o único dia em que ninguém fica contando.
    >>  ............................................
```


### Button `ask_tradition` — "What are the traditions?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `checkin.holiday.told` · offered only once the villager has actually said `occasion:holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.holiday.tradition` — accepted phrasings: "what are the traditions"; "any traditions for it"; "what is the custom here"
  - the message must contain one of: `tradition`, `celebrate`, `custom`
  - scored words: `tradition`(1.5), `celebrate`(1.2), `custom`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.holiday.followup.ask_tradition
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.holiday.followup.ask_tradition   [24 chars]
    en  What are the traditions?
    >>  ............................................
    pt  Quais são as tradições?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `checkin.holiday.tradition`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.holiday.tradition
WHO    VILLAGER — what the player reads after pressing "What are the traditions?"
       spoken on: conversations.topic.checkin.holiday.followup, button `ask_tradition`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.holiday.tradition`: the villager explains. Subject `checkin.holiday`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.holiday.tradition/1   [64 chars]
    en  Eat. Sit. Eat again. My family have refined it over generations.
    >>  ............................................
    pt  Comer. Sentar. Comer de novo. Minha família refinou isso por gerações.
    >>  ............................................
  dialogue.conversations.checkin.holiday.tradition/2   [76 chars]
    en  There's a thing we do with the first loaf that I'll not explain sober, %1$s.
    >>  ............................................
    pt  Tem uma coisa que a gente faz com o primeiro pão que eu não explico sóbrio, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.holiday.tradition/3   [75 chars]
    en  Nothing clever. That's the point of it — one day with nothing clever in it.
    >>  ............................................
    pt  Nada de especial. É esse o ponto — um dia sem nada de especial.
    >>  ............................................
```


### Button `wish_well` — "Enjoy every hour of it."

*stance family `encouragement` · tone `playful` · outcome `appreciated` · answers the beat(s) `checkin.holiday.told`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.holiday.wish_well` — accepted phrasings: "enjoy every hour of it"; "make the most of the festival"; "enjoy the day"
  - the message must contain one of: `enjoy`, `hour`, `festival`
  - scored words: `enjoy`(1.5), `hour`(1.2), `festival`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.holiday.followup.wish_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.holiday.followup.wish_well   [23 chars]
    en  Enjoy every hour of it.
    >>  ............................................
    pt  Aproveite cada hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.holiday.wish_well`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `checkin.holiday.wish_well`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.holiday.wish_well
WHO    VILLAGER — what the player reads after pressing "Enjoy every hour of it."
       spoken on: conversations.topic.checkin.holiday.followup, button `wish_well`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.holiday.wish_well`: the villager celebrates. Subject `checkin.holiday`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.holiday.wish_well/1   [53 chars]
    en  Every hour and two of tomorrow's, if I can manage it.
    >>  ............................................
    pt  Cada hora e mais duas do dia seguinte, se eu conseguir.
    >>  ............................................
  dialogue.conversations.checkin.holiday.wish_well/2   [54 chars]
    en  I will. You should too — nobody's working today, %1$s.
    >>  ............................................
    pt  Vou aproveitar. Você também devia — ninguém está trabalhando hoje, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.holiday.wish_well/3   [68 chars]
    en  That's the correct thing to say to someone at a festival. Well done.
    >>  ............................................
    pt  É a coisa certa a se dizer para alguém numa festa. Muito bem.
    >>  ............................................
```


### Button `join_in` — "I've been eating since dawn myself."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `checkin.holiday.told` · offered only once the villager has actually said `occasion:holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.holiday.join_in` — accepted phrasings: "i have been eating since dawn myself"; "i started eating at dawn"; "i have been at it since dawn"
  - the message must contain one of: `dawn`, `eating`, `myself`
  - scored words: `dawn`(1.5), `eating`(1.2), `myself`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.holiday.followup.join_in
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.holiday.followup.join_in   [35 chars]
    en  I've been eating since dawn myself.
    >>  ............................................
    pt  Eu também estou comendo desde o amanhecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `checkin.holiday.join_in`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.holiday.join_in
WHO    VILLAGER — what the player reads after pressing "I've been eating since dawn myself."
       spoken on: conversations.topic.checkin.holiday.followup, button `join_in`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.holiday.join_in`: the villager invites. Subject `checkin.holiday`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.holiday.join_in/1   [58 chars]
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entendeu o dia direito. A maioria dos visitantes não entende.
    >>  ............................................
  dialogue.conversations.checkin.holiday.join_in/2   [62 chars]
    en  Ha! A fellow professional. Come and find me at the long table.
    >>  ............................................
    pt  Ha! Um colega profissional. Me procure na mesa comprida.
    >>  ............................................
  dialogue.conversations.checkin.holiday.join_in/3   [62 chars]
    en  Good. Anyone who eats before noon on this day is family, %1$s.
    >>  ............................................
    pt  Bom. Quem come antes do meio-dia nesse dia é da família, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly, %1$s. Most visitors don't, and it shows.
    >>  ............................................
    pt  Então você entende o dia direito, %1$s. A maioria dos visitantes não, e dá pra ver.
    >>  ............................................
  anxious.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. I'd been hoping you would, and I'd not have asked you to.
    >>  ............................................
    pt  Bom. Eu esperava que você entendesse, e eu não teria pedido.
    >>  ............................................
  anxious.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. That means more to me than it probably sounds.
    >>  ............................................
    pt  Certo. Você serve. Isso significa mais pra mim do que provavelmente soa.
    >>  ............................................
  athletic.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. It takes most people a few years to get there.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria leva alguns anos pra chegar lá.
    >>  ............................................
  athletic.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. There's no rush about it; the day goes on until it doesn't.
    >>  ............................................
    pt  Bom. Sem pressa; o dia vai até acabar.
    >>  ............................................
  athletic.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. Come along and take it at the pace it takes.
    >>  ............................................
    pt  Certo. Você serve. Venha e leve no ritmo que for.
    >>  ............................................
  confident.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não entende.
    >>  ............................................
  confident.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Most people watch it. Joining in is the whole point.
    >>  ............................................
    pt  Bom. A maioria assiste. Participar é toda a questão.
    >>  ............................................
  confident.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do, then.
    >>  ............................................
    pt  Certo. Então você serve.
    >>  ............................................
  crabby.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não entende.
    >>  ............................................
  crabby.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Most people watch it. Joining in is the whole point.
    >>  ............................................
    pt  Bom. A maioria assiste. Participar é toda a questão.
    >>  ............................................
  crabby.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do, then.
    >>  ............................................
    pt  Certo. Então você serve.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly, %1$s. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito, %1$s. A maioria dos visitantes não.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Come and stand with us — you'll be somebody's cousin by the end of the evening.
    >>  ............................................
    pt  Bom. Venha ficar com a gente — no fim da noite você vai ser primo de alguém.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. I'd hoped you'd say that, if I'm honest.
    >>  ............................................
    pt  Certo. Você serve. Se for honesto, eu esperava que você dissesse isso.
    >>  ............................................
  flirty.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly, %1$s. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito, %1$s. A maioria dos visitantes não.
    >>  ............................................
  flirty.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Come and stand with us — you'll be somebody's cousin by the end of the evening.
    >>  ............................................
    pt  Bom. Venha ficar com a gente — no fim da noite você vai ser primo de alguém.
    >>  ............................................
  flirty.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. I'd hoped you'd say that, if I'm honest.
    >>  ............................................
    pt  Certo. Você serve. Se for honesto, eu esperava que você dissesse isso.
    >>  ............................................
  friendly.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly, %1$s. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito, %1$s. A maioria dos visitantes não.
    >>  ............................................
  friendly.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Come and stand with us — you'll be somebody's cousin by the end of the evening.
    >>  ............................................
    pt  Bom. Venha ficar com a gente — no fim da noite você vai ser primo de alguém.
    >>  ............................................
  friendly.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. I'd hoped you'd say that, if I'm honest.
    >>  ............................................
    pt  Certo. Você serve. Se for honesto, eu esperava que você dissesse isso.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly, %1$s. Most visitors don't, and it shows.
    >>  ............................................
    pt  Então você entende o dia direito, %1$s. A maioria dos visitantes não, e dá pra ver.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. I'd been hoping you would, and I'd not have asked you to.
    >>  ............................................
    pt  Bom. Eu esperava que você entendesse, e eu não teria pedido.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. That means more to me than it probably sounds.
    >>  ............................................
    pt  Certo. Você serve. Isso significa mais pra mim do que provavelmente soa.
    >>  ............................................
  greedy.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não entende.
    >>  ............................................
  greedy.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Most people watch it. Joining in is the whole point.
    >>  ............................................
    pt  Bom. A maioria assiste. Participar é toda a questão.
    >>  ............................................
  greedy.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do, then.
    >>  ............................................
    pt  Certo. Então você serve.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não entende.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. Most people watch it. Joining in is the whole point.
    >>  ............................................
    pt  Bom. A maioria assiste. Participar é toda a questão.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do, then.
    >>  ............................................
    pt  Certo. Então você serve.
    >>  ............................................
  introverted.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não.
    >>  ............................................
  introverted.dialogue.conversations.checkin.holiday.join_in/2
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  introverted.dialogue.conversations.checkin.holiday.join_in/3
    en  ...Right. You'll do.
    >>  ............................................
    pt  ...Certo. Você serve.
    >>  ............................................
  lazy.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. It takes most people a few years to get there.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria leva alguns anos pra chegar lá.
    >>  ............................................
  lazy.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. There's no rush about it; the day goes on until it doesn't.
    >>  ............................................
    pt  Bom. Sem pressa; o dia vai até acabar.
    >>  ............................................
  lazy.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. Come along and take it at the pace it takes.
    >>  ............................................
    pt  Certo. Você serve. Venha e leve no ritmo que for.
    >>  ............................................
  odd.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não.
    >>  ............................................
  odd.dialogue.conversations.checkin.holiday.join_in/2
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  odd.dialogue.conversations.checkin.holiday.join_in/3
    en  ...Right. You'll do.
    >>  ............................................
    pt  ...Certo. Você serve.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. It takes most people a few years to get there.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria leva alguns anos pra chegar lá.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. There's no rush about it; the day goes on until it doesn't.
    >>  ............................................
    pt  Bom. Sem pressa; o dia vai até acabar.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. Come along and take it at the pace it takes.
    >>  ............................................
    pt  Certo. Você serve. Venha e leve no ritmo que for.
    >>  ............................................
  peppy.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly! Most visitors don't. Most visitors stand at the edge looking polite.
    >>  ............................................
    pt  Então você entende o dia direito! A maioria dos visitantes não. A maioria fica na beirada parecendo educada.
    >>  ............................................
  peppy.dialogue.conversations.checkin.holiday.join_in/2
    en  Good! Joining in is the whole point and about four people manage it.
    >>  ............................................
    pt  Bom! Participar é toda a questão e umas quatro pessoas conseguem.
    >>  ............................................
  peppy.dialogue.conversations.checkin.holiday.join_in/3
    en  Right — you'll do. Come on, then, before the good part starts without us.
    >>  ............................................
    pt  Certo — você serve. Vamos, então, antes que a parte boa comece sem nós.
    >>  ............................................
  playful.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly! Most visitors don't. Most visitors stand at the edge looking polite.
    >>  ............................................
    pt  Então você entende o dia direito! A maioria dos visitantes não. A maioria fica na beirada parecendo educada.
    >>  ............................................
  playful.dialogue.conversations.checkin.holiday.join_in/2
    en  Good! Joining in is the whole point and about four people manage it.
    >>  ............................................
    pt  Bom! Participar é toda a questão e umas quatro pessoas conseguem.
    >>  ............................................
  playful.dialogue.conversations.checkin.holiday.join_in/3
    en  Right — you'll do. Come on, then, before the good part starts without us.
    >>  ............................................
    pt  Certo — você serve. Vamos, então, antes que a parte boa comece sem nós.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. It takes most people a few years to get there.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria leva alguns anos pra chegar lá.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. There's no rush about it; the day goes on until it doesn't.
    >>  ............................................
    pt  Bom. Sem pressa; o dia vai até acabar.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. Come along and take it at the pace it takes.
    >>  ............................................
    pt  Certo. Você serve. Venha e leve no ritmo que for.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly, %1$s. Most visitors don't, and it shows.
    >>  ............................................
    pt  Então você entende o dia direito, %1$s. A maioria dos visitantes não, e dá pra ver.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.holiday.join_in/2
    en  Good. I'd been hoping you would, and I'd not have asked you to.
    >>  ............................................
    pt  Bom. Eu esperava que você entendesse, e eu não teria pedido.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.holiday.join_in/3
    en  Right. You'll do. That means more to me than it probably sounds.
    >>  ............................................
    pt  Certo. Você serve. Isso significa mais pra mim do que provavelmente soa.
    >>  ............................................
  shy.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly. Most visitors don't.
    >>  ............................................
    pt  Então você entende o dia direito. A maioria dos visitantes não.
    >>  ............................................
  shy.dialogue.conversations.checkin.holiday.join_in/2
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  shy.dialogue.conversations.checkin.holiday.join_in/3
    en  ...Right. You'll do.
    >>  ............................................
    pt  ...Certo. Você serve.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly! Most visitors don't. Most visitors stand at the edge looking polite.
    >>  ............................................
    pt  Então você entende o dia direito! A maioria dos visitantes não. A maioria fica na beirada parecendo educada.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.holiday.join_in/2
    en  Good! Joining in is the whole point and about four people manage it.
    >>  ............................................
    pt  Bom! Participar é toda a questão e umas quatro pessoas conseguem.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.holiday.join_in/3
    en  Right — you'll do. Come on, then, before the good part starts without us.
    >>  ............................................
    pt  Certo — você serve. Vamos, então, antes que a parte boa comece sem nós.
    >>  ............................................
  witty.dialogue.conversations.checkin.holiday.join_in/1
    en  Then you understand the day properly! Most visitors don't. Most visitors stand at the edge looking polite.
    >>  ............................................
    pt  Então você entende o dia direito! A maioria dos visitantes não. A maioria fica na beirada parecendo educada.
    >>  ............................................
  witty.dialogue.conversations.checkin.holiday.join_in/2
    en  Good! Joining in is the whole point and about four people manage it.
    >>  ............................................
    pt  Bom! Participar é toda a questão e umas quatro pessoas conseguem.
    >>  ............................................
  witty.dialogue.conversations.checkin.holiday.join_in/3
    en  Right — you'll do. Come on, then, before the good part starts without us.
    >>  ............................................
    pt  Certo — você serve. Vamos, então, antes que a parte boa comece sem nós.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.holiday.told` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.holiday.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.holiday.followup.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar pra isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.holiday.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.topic.checkin.holiday.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.holiday.leave`: the villager accepts. Subject `checkin.holiday`, polarity `positive`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.holiday.leave/1   [37 chars]
    en  Do. There's a pie with my name on it.
    >>  ............................................
    pt  Vá. Tem uma torta com meu nome nela.
    >>  ............................................
  dialogue.conversations.checkin.holiday.leave/2   [27 chars]
    en  It is. Find me later, %1$s.
    >>  ............................................
    pt  É sim. Me procure mais tarde, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.holiday.leave/3   [9 chars]
    en  Quite so.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
```

---


## `conversations.topic.checkin.hurt.followup`

**Reached from 1 route(s):** `conversations.topic.checkin.good.respond` / `glad`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.glad.hurt` — e.g. "...You're bleeding. Sit down before you tell me how well you're doing."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.hurt.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.hurt.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.hurt.followup   [34 chars]
    en  Sit down before you argue with me.
    >>  ............................................
    pt  Senta antes de discutir comigo.
    >>  ............................................
```


### Button `explain` — "It looks worse than it is."

*stance family `self_disclosure` · tone `plain` · outcome `qualified` · answers the beat(s) `checkin.player_hurt` · offered only once the villager has actually said `player:injured`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.hurt.explain` — accepted phrasings: "it looks worse than it is"; "it is just a scratch"; "it is not as bad as it looks"
  - the message must contain one of: `worse`, `looks`, `scratch`
  - scored words: `worse`(1.5), `looks`(1.2), `scratch`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.hurt.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.hurt.followup.explain   [26 chars]
    en  It looks worse than it is.
    >>  ............................................
    pt  Parece pior do que é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `checkin.hurt.explain`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.hurt.explain
WHO    VILLAGER — what the player reads after pressing "It looks worse than it is."
       spoken on: conversations.topic.checkin.hurt.followup, button `explain`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.hurt.explain`: the villager qualifys. Subject `checkin.player_health`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.hurt.explain/1   [50 chars]
    en  It always does, to the one wearing it. Sit anyway.
    >>  ............................................
    pt  Sempre parece, pra quem está usando. Senta mesmo assim.
    >>  ............................................
  dialogue.conversations.checkin.hurt.explain/2   [57 chars]
    en  That's what everyone says on the way to the cleric, %1$s.
    >>  ............................................
    pt  É o que todo mundo diz a caminho do clérigo, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.hurt.explain/3   [56 chars]
    en  Then it looks very bad indeed, and I'd like the details.
    >>  ............................................
    pt  Então parece muito ruim mesmo, e eu quero os detalhes.
    >>  ............................................
```


### Button `sit` — "...Alright. Just for a moment."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `checkin.player_hurt`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.hurt.sit` — accepted phrasings: "alright, just for a moment"; "fine, i will sit"; "just for a moment then"
  - the message must contain one of: `alright`, `moment`, `sit`
  - scored words: `alright`(1.2), `moment`(1.5), `sit`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.hurt.followup.sit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.hurt.followup.sit   [30 chars]
    en  ...Alright. Just for a moment.
    >>  ............................................
    pt  ...Está bem. Só um momento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.hurt.sit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +3  _(recorded under topic `checkin.hurt.sit`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.hurt.sit
WHO    VILLAGER — what the player reads after pressing "...Alright. Just for a moment."
       spoken on: conversations.topic.checkin.hurt.followup, button `sit`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.hurt.sit`: the villager accepts. Subject `checkin.player_health`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.hurt.sit/1   [56 chars]
    en  Good. Now don't talk for a bit and let it stop bleeding.
    >>  ............................................
    pt  Bom. Agora fica quieto um pouco e deixa parar de sangrar.
    >>  ............................................
  dialogue.conversations.checkin.hurt.sit/2   [44 chars]
    en  A moment's all I asked for. Thank you, %1$s.
    >>  ............................................
    pt  Um momento foi tudo que eu pedi. Obrigado, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.hurt.sit/3   [60 chars]
    en  There. The village will keep without you for a quarter hour.
    >>  ............................................
    pt  Pronto. O vilarejo aguenta sem você por um quarto de hora.
    >>  ............................................
```


### Button `ask_help` — "Have you anything for it?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `checkin.player_hurt` · offered only once the villager has actually said `player:injured`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.hurt.ask_help` — accepted phrasings: "have you anything for it"; "do you have a bandage"; "have you a remedy"
  - the message must contain one of: `anything`, `bandage`, `remedy`
  - scored words: `anything`(1.2), `bandage`(1.5), `remedy`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.hurt.followup.ask_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.hurt.followup.ask_help   [25 chars]
    en  Have you anything for it?
    >>  ............................................
    pt  Você tem alguma coisa pra isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1, trust +2  _(recorded under topic `checkin.hurt.ask_help`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.hurt.ask_help
WHO    VILLAGER — what the player reads after pressing "Have you anything for it?"
       spoken on: conversations.topic.checkin.hurt.followup, button `ask_help`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.hurt.ask_help`: the villager accepts. Subject `checkin.player_health`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.hurt.ask_help/1   [70 chars]
    en  Clean water and a strip of linen. It isn't magic, but it's what works.
    >>  ............................................
    pt  Água limpa e uma tira de linho. Não é mágica, mas funciona.
    >>  ............................................
  dialogue.conversations.checkin.hurt.ask_help/2   [76 chars]
    en  The cleric has the good stuff. I have the stuff that stops it getting worse.
    >>  ............................................
    pt  O clérigo tem o bom. Eu tenho o que impede de piorar.
    >>  ............................................
  dialogue.conversations.checkin.hurt.ask_help/3   [55 chars]
    en  Aye. Hold still, %1$s, and stop telling me you're fine.
    >>  ............................................
    pt  Tenho. Fica parado, %1$s, e para de dizer que está bem.
    >>  ............................................
```


### Button `wave_off` — "I've had worse. Leave it."

*stance family `dismissal` · tone `blunt` · outcome `rebuffed` · answers the beat(s) `checkin.player_hurt`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.hurt.wave_off` — accepted phrasings: "i have had worse, leave it"; "do not fuss"; "leave it, i am fine"
  - the message must contain one of: `worse`, `leave`, `fuss`
  - scored words: `worse`(1.2), `leave`(1.5), `fuss`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.hurt.followup.wave_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.hurt.followup.wave_off   [25 chars]
    en  I've had worse. Leave it.
    >>  ............................................
    pt  Já tive coisa pior. Deixa pra lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `checkin.hurt.wave_off`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +2, warmth -2  _(recorded under topic `checkin.hurt.wave_off`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.hurt.wave_off
WHO    VILLAGER — what the player reads after pressing "I've had worse. Leave it."
       spoken on: conversations.topic.checkin.hurt.followup, button `wave_off`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.hurt.wave_off`: the villager refuses. Subject `checkin.player_health`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.hurt.wave_off/1   [57 chars]
    en  I don't doubt it. That's not the comfort you think it is.
    >>  ............................................
    pt  Não duvido. Isso não é o consolo que você acha que é.
    >>  ............................................
  dialogue.conversations.checkin.hurt.wave_off/2   [56 chars]
    en  Then you've had worse and learned nothing from it, %1$s.
    >>  ............................................
    pt  Então você já teve pior e não aprendeu nada com isso, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.hurt.wave_off/3   [46 chars]
    en  As you like. Bleed on someone else's doorstep.
    >>  ............................................
    pt  Como quiser. Sangre na porta de outra pessoa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...I know it's common. Knowing that has never once made it smaller.
    >>  ............................................
    pt  ...Eu sei que é comum. Saber disso nunca deixou menor.
    >>  ............................................
  anxious.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's the thing people say instead of the thing I needed, %1$s.
    >>  ............................................
    pt  É o que as pessoas dizem em vez do que eu precisava, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.hurt.wave_off/3
    en  ...Right. Yes. Everyone. I'll manage.
    >>  ............................................
    pt  ...Certo. Sim. Todo mundo. Eu me viro.
    >>  ............................................
  athletic.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. It's still mine to carry and it'll take the time it takes.
    >>  ............................................
    pt  Não duvido. Continua sendo meu de carregar e vai levar o tempo que levar.
    >>  ............................................
  athletic.dialogue.conversations.checkin.hurt.wave_off/2
    en  Aye, everyone does. That never has been much use to the one it's happening to.
    >>  ............................................
    pt  É, acontece com todos. Isso nunca serviu de muito pra quem está passando.
    >>  ............................................
  athletic.dialogue.conversations.checkin.hurt.wave_off/3
    en  Common enough, true. I'd still rather sit with it than be told the size of it.
    >>  ............................................
    pt  Comum o bastante, é verdade. Ainda prefiro ficar com isso a ouvir o tamanho.
    >>  ............................................
  confident.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. That is not the comfort you think it is.
    >>  ............................................
    pt  Não duvido. Isso não é o consolo que você acha que é.
    >>  ............................................
  confident.dialogue.conversations.checkin.hurt.wave_off/2
    en  I know it happens to everyone. I asked about me.
    >>  ............................................
    pt  Eu sei que acontece com todo mundo. Eu perguntei de mim.
    >>  ............................................
  confident.dialogue.conversations.checkin.hurt.wave_off/3
    en  That's an answer for somebody else's trouble, not mine.
    >>  ............................................
    pt  Essa é uma resposta pro problema de outra pessoa, não pro meu.
    >>  ............................................
  crabby.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. That is not the comfort you think it is.
    >>  ............................................
    pt  Não duvido. Isso não é o consolo que você acha que é.
    >>  ............................................
  crabby.dialogue.conversations.checkin.hurt.wave_off/2
    en  I know it happens to everyone. I asked about me.
    >>  ............................................
    pt  Eu sei que acontece com todo mundo. Eu perguntei de mim.
    >>  ............................................
  crabby.dialogue.conversations.checkin.hurt.wave_off/3
    en  That's an answer for somebody else's trouble, not mine.
    >>  ............................................
    pt  Essa é uma resposta pro problema de outra pessoa, não pro meu.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...I know it's common, %1$s. I told you because it was mine.
    >>  ............................................
    pt  ...Eu sei que é comum, %1$s. Eu te contei porque era meu.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's what you'd say to a stranger. I'd thought we were past that.
    >>  ............................................
    pt  É o que você diria a um estranho. Eu achei que a gente tinha passado disso.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.hurt.wave_off/3
    en  ...Everyone. Right. I'd hoped for the smaller answer.
    >>  ............................................
    pt  ...Todo mundo. Certo. Eu esperava a resposta menor.
    >>  ............................................
  flirty.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...I know it's common, %1$s. I told you because it was mine.
    >>  ............................................
    pt  ...Eu sei que é comum, %1$s. Eu te contei porque era meu.
    >>  ............................................
  flirty.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's what you'd say to a stranger. I'd thought we were past that.
    >>  ............................................
    pt  É o que você diria a um estranho. Eu achei que a gente tinha passado disso.
    >>  ............................................
  flirty.dialogue.conversations.checkin.hurt.wave_off/3
    en  ...Everyone. Right. I'd hoped for the smaller answer.
    >>  ............................................
    pt  ...Todo mundo. Certo. Eu esperava a resposta menor.
    >>  ............................................
  friendly.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...I know it's common, %1$s. I told you because it was mine.
    >>  ............................................
    pt  ...Eu sei que é comum, %1$s. Eu te contei porque era meu.
    >>  ............................................
  friendly.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's what you'd say to a stranger. I'd thought we were past that.
    >>  ............................................
    pt  É o que você diria a um estranho. Eu achei que a gente tinha passado disso.
    >>  ............................................
  friendly.dialogue.conversations.checkin.hurt.wave_off/3
    en  ...Everyone. Right. I'd hoped for the smaller answer.
    >>  ............................................
    pt  ...Todo mundo. Certo. Eu esperava a resposta menor.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...I know it's common. Knowing that has never once made it smaller.
    >>  ............................................
    pt  ...Eu sei que é comum. Saber disso nunca deixou menor.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's the thing people say instead of the thing I needed, %1$s.
    >>  ............................................
    pt  É o que as pessoas dizem em vez do que eu precisava, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.hurt.wave_off/3
    en  ...Right. Yes. Everyone. I'll manage.
    >>  ............................................
    pt  ...Certo. Sim. Todo mundo. Eu me viro.
    >>  ............................................
  greedy.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. That is not the comfort you think it is.
    >>  ............................................
    pt  Não duvido. Isso não é o consolo que você acha que é.
    >>  ............................................
  greedy.dialogue.conversations.checkin.hurt.wave_off/2
    en  I know it happens to everyone. I asked about me.
    >>  ............................................
    pt  Eu sei que acontece com todo mundo. Eu perguntei de mim.
    >>  ............................................
  greedy.dialogue.conversations.checkin.hurt.wave_off/3
    en  That's an answer for somebody else's trouble, not mine.
    >>  ............................................
    pt  Essa é uma resposta pro problema de outra pessoa, não pro meu.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. That is not the comfort you think it is.
    >>  ............................................
    pt  Não duvido. Isso não é o consolo que você acha que é.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.hurt.wave_off/2
    en  I know it happens to everyone. I asked about me.
    >>  ............................................
    pt  Eu sei que acontece com todo mundo. Eu perguntei de mim.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.hurt.wave_off/3
    en  That's an answer for somebody else's trouble, not mine.
    >>  ............................................
    pt  Essa é uma resposta pro problema de outra pessoa, não pro meu.
    >>  ............................................
  introverted.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...Mm. Everyone. Right.
    >>  ............................................
    pt  ...Hum. Todo mundo. Certo.
    >>  ............................................
  introverted.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's true and it isn't an answer.
    >>  ............................................
    pt  Isso é verdade e não é uma resposta.
    >>  ............................................
  introverted.dialogue.conversations.checkin.hurt.wave_off/3
    en  I know. I'd not have mentioned it if that were the point.
    >>  ............................................
    pt  Eu sei. Eu não teria mencionado se fosse essa a questão.
    >>  ............................................
  lazy.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. It's still mine to carry and it'll take the time it takes.
    >>  ............................................
    pt  Não duvido. Continua sendo meu de carregar e vai levar o tempo que levar.
    >>  ............................................
  lazy.dialogue.conversations.checkin.hurt.wave_off/2
    en  Aye, everyone does. That never has been much use to the one it's happening to.
    >>  ............................................
    pt  É, acontece com todos. Isso nunca serviu de muito pra quem está passando.
    >>  ............................................
  lazy.dialogue.conversations.checkin.hurt.wave_off/3
    en  Common enough, true. I'd still rather sit with it than be told the size of it.
    >>  ............................................
    pt  Comum o bastante, é verdade. Ainda prefiro ficar com isso a ouvir o tamanho.
    >>  ............................................
  odd.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...Mm. Everyone. Right.
    >>  ............................................
    pt  ...Hum. Todo mundo. Certo.
    >>  ............................................
  odd.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's true and it isn't an answer.
    >>  ............................................
    pt  Isso é verdade e não é uma resposta.
    >>  ............................................
  odd.dialogue.conversations.checkin.hurt.wave_off/3
    en  I know. I'd not have mentioned it if that were the point.
    >>  ............................................
    pt  Eu sei. Eu não teria mencionado se fosse essa a questão.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. It's still mine to carry and it'll take the time it takes.
    >>  ............................................
    pt  Não duvido. Continua sendo meu de carregar e vai levar o tempo que levar.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.hurt.wave_off/2
    en  Aye, everyone does. That never has been much use to the one it's happening to.
    >>  ............................................
    pt  É, acontece com todos. Isso nunca serviu de muito pra quem está passando.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.hurt.wave_off/3
    en  Common enough, true. I'd still rather sit with it than be told the size of it.
    >>  ............................................
    pt  Comum o bastante, é verdade. Ainda prefiro ficar com isso a ouvir o tamanho.
    >>  ............................................
  peppy.dialogue.conversations.checkin.hurt.wave_off/1
    en  Right, yes. Everyone. That's the bit that helps, is it.
    >>  ............................................
    pt  Certo, sim. Todo mundo. É essa a parte que ajuda, é?
    >>  ............................................
  peppy.dialogue.conversations.checkin.hurt.wave_off/2
    en  ...Common, is it. Marvellous. I feel enormously better.
    >>  ............................................
    pt  ...Comum, é? Maravilhoso. Estou me sentindo imensamente melhor.
    >>  ............................................
  peppy.dialogue.conversations.checkin.hurt.wave_off/3
    en  Everyone gets it. Everyone. Very warming, that.
    >>  ............................................
    pt  Acontece com todo mundo. Todo mundo. Muito reconfortante.
    >>  ............................................
  playful.dialogue.conversations.checkin.hurt.wave_off/1
    en  Right, yes. Everyone. That's the bit that helps, is it.
    >>  ............................................
    pt  Certo, sim. Todo mundo. É essa a parte que ajuda, é?
    >>  ............................................
  playful.dialogue.conversations.checkin.hurt.wave_off/2
    en  ...Common, is it. Marvellous. I feel enormously better.
    >>  ............................................
    pt  ...Comum, é? Maravilhoso. Estou me sentindo imensamente melhor.
    >>  ............................................
  playful.dialogue.conversations.checkin.hurt.wave_off/3
    en  Everyone gets it. Everyone. Very warming, that.
    >>  ............................................
    pt  Acontece com todo mundo. Todo mundo. Muito reconfortante.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.hurt.wave_off/1
    en  I don't doubt it. It's still mine to carry and it'll take the time it takes.
    >>  ............................................
    pt  Não duvido. Continua sendo meu de carregar e vai levar o tempo que levar.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.hurt.wave_off/2
    en  Aye, everyone does. That never has been much use to the one it's happening to.
    >>  ............................................
    pt  É, acontece com todos. Isso nunca serviu de muito pra quem está passando.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.hurt.wave_off/3
    en  Common enough, true. I'd still rather sit with it than be told the size of it.
    >>  ............................................
    pt  Comum o bastante, é verdade. Ainda prefiro ficar com isso a ouvir o tamanho.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...I know it's common. Knowing that has never once made it smaller.
    >>  ............................................
    pt  ...Eu sei que é comum. Saber disso nunca deixou menor.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's the thing people say instead of the thing I needed, %1$s.
    >>  ............................................
    pt  É o que as pessoas dizem em vez do que eu precisava, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.hurt.wave_off/3
    en  ...Right. Yes. Everyone. I'll manage.
    >>  ............................................
    pt  ...Certo. Sim. Todo mundo. Eu me viro.
    >>  ............................................
  shy.dialogue.conversations.checkin.hurt.wave_off/1
    en  ...Mm. Everyone. Right.
    >>  ............................................
    pt  ...Hum. Todo mundo. Certo.
    >>  ............................................
  shy.dialogue.conversations.checkin.hurt.wave_off/2
    en  That's true and it isn't an answer.
    >>  ............................................
    pt  Isso é verdade e não é uma resposta.
    >>  ............................................
  shy.dialogue.conversations.checkin.hurt.wave_off/3
    en  I know. I'd not have mentioned it if that were the point.
    >>  ............................................
    pt  Eu sei. Eu não teria mencionado se fosse essa a questão.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.hurt.wave_off/1
    en  Right, yes. Everyone. That's the bit that helps, is it.
    >>  ............................................
    pt  Certo, sim. Todo mundo. É essa a parte que ajuda, é?
    >>  ............................................
  upbeat.dialogue.conversations.checkin.hurt.wave_off/2
    en  ...Common, is it. Marvellous. I feel enormously better.
    >>  ............................................
    pt  ...Comum, é? Maravilhoso. Estou me sentindo imensamente melhor.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.hurt.wave_off/3
    en  Everyone gets it. Everyone. Very warming, that.
    >>  ............................................
    pt  Acontece com todo mundo. Todo mundo. Muito reconfortante.
    >>  ............................................
  witty.dialogue.conversations.checkin.hurt.wave_off/1
    en  Right, yes. Everyone. That's the bit that helps, is it.
    >>  ............................................
    pt  Certo, sim. Todo mundo. É essa a parte que ajuda, é?
    >>  ............................................
  witty.dialogue.conversations.checkin.hurt.wave_off/2
    en  ...Common, is it. Marvellous. I feel enormously better.
    >>  ............................................
    pt  ...Comum, é? Maravilhoso. Estou me sentindo imensamente melhor.
    >>  ............................................
  witty.dialogue.conversations.checkin.hurt.wave_off/3
    en  Everyone gets it. Everyone. Very warming, that.
    >>  ............................................
    pt  Acontece com todo mundo. Todo mundo. Muito reconfortante.
    >>  ............................................
```

</details>


### Button `leave` — "I'll manage."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.player_hurt` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.hurt.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.hurt.followup.leave   [12 chars]
    en  I'll manage.
    >>  ............................................
    pt  Eu me viro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.hurt.leave
WHO    VILLAGER — what the player reads after pressing "I'll manage."
       spoken on: conversations.topic.checkin.hurt.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.hurt.leave`: the villager accepts. Subject `checkin.player_health`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.hurt.leave/1   [38 chars]
    en  You will. That was never the question.
    >>  ............................................
    pt  Você vai. Nunca foi essa a questão.
    >>  ............................................
  dialogue.conversations.checkin.hurt.leave/2   [19 chars]
    en  Go carefully, %1$s.
    >>  ............................................
    pt  Vá com cuidado, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.hurt.leave/3   [20 chars]
    en  Mm. Mind how you go.
    >>  ............................................
    pt  Mm. Se cuida.
    >>  ............................................
```

---


## `conversations.topic.checkin.late.followup`

**Reached from 1 route(s):** `conversations.topic.checkin.late.respond` / `ask_why_up`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.late.ask_why_up` — e.g. "Nothing I could name. That's the trouble — a named thing you can put down."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.late.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.late.followup   [32 chars]
    en  That's the hour talking, mostly.
    >>  ............................................
    pt  É a hora falando, principalmente.
    >>  ............................................
```


### Button `awake_too` — "I'm no better. I never sleep either."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `checkin.late.told` · offered only once the villager has actually said `time:night`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.late.awake_too` — accepted phrasings: "i never sleep either"; "i am awake at this hour too"; "i am no better at sleeping"
  - the message must contain one of: `sleep`, `either`, `awake`
  - scored words: `sleep`(1.5), `either`(1.2), `awake`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.followup.awake_too
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.followup.awake_too   [36 chars]
    en  I'm no better. I never sleep either.
    >>  ............................................
    pt  Eu não sou melhor. Também nunca durmo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `checkin.late.awake_too`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.late.awake_too
WHO    VILLAGER — what the player reads after pressing "I'm no better. I never sleep either."
       spoken on: conversations.topic.checkin.late.followup, button `awake_too`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.awake_too`: the villager accepts. Subject `checkin.sleeplessness`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.late.awake_too/1   [69 chars]
    en  Then the village has two of us and neither will admit it in daylight.
    >>  ............................................
    pt  Então o vilarejo tem dois de nós e nenhum admite isso de dia.
    >>  ............................................
  dialogue.conversations.checkin.late.awake_too/2   [67 chars]
    en  Ha. We should form a guild, %1$s. Meetings at three in the morning.
    >>  ............................................
    pt  Ha. A gente devia formar uma guilda, %1$s. Reuniões às três da manhã.
    >>  ............................................
  dialogue.conversations.checkin.late.awake_too/3   [61 chars]
    en  That's oddly steadying to hear. Misery does like the company.
    >>  ............................................
    pt  É estranhamente reconfortante ouvir isso. Sofrimento gosta de companhia.
    >>  ............................................
```


### Button `name_it` — "Can you name any of it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `checkin.late.told` · offered only once the villager has actually said `villager:sleepless`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.late.name_it` — accepted phrasings: "can you name any of it"; "what is on the list"; "can you put a name to it"
  - the message must contain one of: `name`, `list`
  - scored words: `name`(1.5), `list`(1.2), `any`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.followup.name_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.followup.name_it   [23 chars]
    en  Can you name any of it?
    >>  ............................................
    pt  Você consegue nomear alguma coisa disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1, trust +3  _(recorded under topic `checkin.late.name_it`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.late.name_it
WHO    VILLAGER — what the player reads after pressing "Can you name any of it?"
       spoken on: conversations.topic.checkin.late.followup, button `name_it`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.name_it`: the villager discloses. Subject `checkin.sleeplessness`, polarity `mixed`, guarded, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.late.name_it/1   [70 chars]
    en  ...One of them, maybe. It's the roof. It's been the roof since spring.
    >>  ............................................
    pt  ...Uma delas, talvez. É o telhado. É o telhado desde a primavera.
    >>  ............................................
  dialogue.conversations.checkin.late.name_it/2   [61 chars]
    en  If I could name them they'd be jobs, %1$s, and jobs I can do.
    >>  ............................................
    pt  Se eu conseguisse nomear seriam tarefas, %1$s, e tarefa eu dou conta.
    >>  ............................................
  dialogue.conversations.checkin.late.name_it/3   [67 chars]
    en  Naming them at this hour makes them bigger. I'll do it in daylight.
    >>  ............................................
    pt  Nomear a essa hora só aumenta. Faço isso de dia.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof, and it's been the roof since spring, %1$s.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado, e é o telhado desde a primavera, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.late.name_it/2
    en  The roof. Saying it makes it real, which is why I've been careful not to.
    >>  ............................................
    pt  O telhado. Dizer torna real, e é por isso que eu tinha cuidado de não dizer.
    >>  ............................................
  anxious.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. And what the roof means about next winter. Mostly that.
    >>  ............................................
    pt  É o telhado. E o que o telhado significa pro inverno que vem. Principalmente isso.
    >>  ............................................
  athletic.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. Roofs are patient about being fixed.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. Telhados são pacientes quanto a serem consertados.
    >>  ............................................
  athletic.dialogue.conversations.checkin.late.name_it/2
    en  The roof, since spring. It'll get done. Things do, eventually.
    >>  ............................................
    pt  O telhado, desde a primavera. Vai ser feito. As coisas são, uma hora.
    >>  ............................................
  athletic.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Not urgent, just always there, which is its own sort of weight.
    >>  ............................................
    pt  É o telhado. Não é urgente, só está sempre lá, que é um peso próprio.
    >>  ............................................
  confident.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. É o telhado desde a primavera.
    >>  ............................................
  confident.dialogue.conversations.checkin.late.name_it/2
    en  The roof. There. That's it named, and it hasn't got any smaller for being said.
    >>  ............................................
    pt  O telhado. Pronto. Está nomeado, e não ficou menor por ter sido dito.
    >>  ............................................
  confident.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof, and the cost of the roof, and the winter after the roof.
    >>  ............................................
    pt  É o telhado, e o custo do telhado, e o inverno depois do telhado.
    >>  ............................................
  crabby.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. É o telhado desde a primavera.
    >>  ............................................
  crabby.dialogue.conversations.checkin.late.name_it/2
    en  The roof. There. That's it named, and it hasn't got any smaller for being said.
    >>  ............................................
    pt  O telhado. Pronto. Está nomeado, e não ficou menor por ter sido dito.
    >>  ............................................
  crabby.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof, and the cost of the roof, and the winter after the roof.
    >>  ............................................
    pt  É o telhado, e o custo do telhado, e o inverno depois do telhado.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof, %1$s. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado, %1$s. É o telhado desde a primavera.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.late.name_it/2
    en  The roof. I've not said that out loud to anybody, so — there it is.
    >>  ............................................
    pt  O telhado. Eu não disse isso em voz alta pra ninguém, então — pronto.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Thank you for staying long enough for me to get to it.
    >>  ............................................
    pt  É o telhado. Obrigado por ficar tempo o bastante pra eu chegar nisso.
    >>  ............................................
  flirty.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof, %1$s. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado, %1$s. É o telhado desde a primavera.
    >>  ............................................
  flirty.dialogue.conversations.checkin.late.name_it/2
    en  The roof. I've not said that out loud to anybody, so — there it is.
    >>  ............................................
    pt  O telhado. Eu não disse isso em voz alta pra ninguém, então — pronto.
    >>  ............................................
  flirty.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Thank you for staying long enough for me to get to it.
    >>  ............................................
    pt  É o telhado. Obrigado por ficar tempo o bastante pra eu chegar nisso.
    >>  ............................................
  friendly.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof, %1$s. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado, %1$s. É o telhado desde a primavera.
    >>  ............................................
  friendly.dialogue.conversations.checkin.late.name_it/2
    en  The roof. I've not said that out loud to anybody, so — there it is.
    >>  ............................................
    pt  O telhado. Eu não disse isso em voz alta pra ninguém, então — pronto.
    >>  ............................................
  friendly.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Thank you for staying long enough for me to get to it.
    >>  ............................................
    pt  É o telhado. Obrigado por ficar tempo o bastante pra eu chegar nisso.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof, and it's been the roof since spring, %1$s.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado, e é o telhado desde a primavera, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.late.name_it/2
    en  The roof. Saying it makes it real, which is why I've been careful not to.
    >>  ............................................
    pt  O telhado. Dizer torna real, e é por isso que eu tinha cuidado de não dizer.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. And what the roof means about next winter. Mostly that.
    >>  ............................................
    pt  É o telhado. E o que o telhado significa pro inverno que vem. Principalmente isso.
    >>  ............................................
  greedy.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. É o telhado desde a primavera.
    >>  ............................................
  greedy.dialogue.conversations.checkin.late.name_it/2
    en  The roof. There. That's it named, and it hasn't got any smaller for being said.
    >>  ............................................
    pt  O telhado. Pronto. Está nomeado, e não ficou menor por ter sido dito.
    >>  ............................................
  greedy.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof, and the cost of the roof, and the winter after the roof.
    >>  ............................................
    pt  É o telhado, e o custo do telhado, e o inverno depois do telhado.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. It's been the roof since spring.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. É o telhado desde a primavera.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.late.name_it/2
    en  The roof. There. That's it named, and it hasn't got any smaller for being said.
    >>  ............................................
    pt  O telhado. Pronto. Está nomeado, e não ficou menor por ter sido dito.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof, and the cost of the roof, and the winter after the roof.
    >>  ............................................
    pt  É o telhado, e o custo do telhado, e o inverno depois do telhado.
    >>  ............................................
  introverted.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado.
    >>  ............................................
  introverted.dialogue.conversations.checkin.late.name_it/2
    en  The roof. Since spring.
    >>  ............................................
    pt  O telhado. Desde a primavera.
    >>  ............................................
  introverted.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. That's as much as I'll say about it tonight.
    >>  ............................................
    pt  É o telhado. É tudo que eu digo sobre isso hoje.
    >>  ............................................
  lazy.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. Roofs are patient about being fixed.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. Telhados são pacientes quanto a serem consertados.
    >>  ............................................
  lazy.dialogue.conversations.checkin.late.name_it/2
    en  The roof, since spring. It'll get done. Things do, eventually.
    >>  ............................................
    pt  O telhado, desde a primavera. Vai ser feito. As coisas são, uma hora.
    >>  ............................................
  lazy.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Not urgent, just always there, which is its own sort of weight.
    >>  ............................................
    pt  É o telhado. Não é urgente, só está sempre lá, que é um peso próprio.
    >>  ............................................
  odd.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado.
    >>  ............................................
  odd.dialogue.conversations.checkin.late.name_it/2
    en  The roof. Since spring.
    >>  ............................................
    pt  O telhado. Desde a primavera.
    >>  ............................................
  odd.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. That's as much as I'll say about it tonight.
    >>  ............................................
    pt  É o telhado. É tudo que eu digo sobre isso hoje.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. Roofs are patient about being fixed.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. Telhados são pacientes quanto a serem consertados.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.late.name_it/2
    en  The roof, since spring. It'll get done. Things do, eventually.
    >>  ............................................
    pt  O telhado, desde a primavera. Vai ser feito. As coisas são, uma hora.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Not urgent, just always there, which is its own sort of weight.
    >>  ............................................
    pt  É o telhado. Não é urgente, só está sempre lá, que é um peso próprio.
    >>  ............................................
  peppy.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe! It's the roof. It's been the roof since spring and I'd hoped for something grander.
    >>  ............................................
    pt  Uma delas, talvez! É o telhado. É o telhado desde a primavera e eu esperava algo mais grandioso.
    >>  ............................................
  peppy.dialogue.conversations.checkin.late.name_it/2
    en  The roof. That's it. Not a tragedy, just a leak with excellent stamina.
    >>  ............................................
    pt  O telhado. É isso. Não é tragédia, só uma goteira com excelente resistência.
    >>  ............................................
  peppy.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Naming it hasn't fixed it, which I feel is a design flaw.
    >>  ............................................
    pt  É o telhado. Nomear não consertou, o que eu considero uma falha de projeto.
    >>  ............................................
  playful.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe! It's the roof. It's been the roof since spring and I'd hoped for something grander.
    >>  ............................................
    pt  Uma delas, talvez! É o telhado. É o telhado desde a primavera e eu esperava algo mais grandioso.
    >>  ............................................
  playful.dialogue.conversations.checkin.late.name_it/2
    en  The roof. That's it. Not a tragedy, just a leak with excellent stamina.
    >>  ............................................
    pt  O telhado. É isso. Não é tragédia, só uma goteira com excelente resistência.
    >>  ............................................
  playful.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Naming it hasn't fixed it, which I feel is a design flaw.
    >>  ............................................
    pt  É o telhado. Nomear não consertou, o que eu considero uma falha de projeto.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof. Roofs are patient about being fixed.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado. Telhados são pacientes quanto a serem consertados.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.late.name_it/2
    en  The roof, since spring. It'll get done. Things do, eventually.
    >>  ............................................
    pt  O telhado, desde a primavera. Vai ser feito. As coisas são, uma hora.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Not urgent, just always there, which is its own sort of weight.
    >>  ............................................
    pt  É o telhado. Não é urgente, só está sempre lá, que é um peso próprio.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof, and it's been the roof since spring, %1$s.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado, e é o telhado desde a primavera, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.late.name_it/2
    en  The roof. Saying it makes it real, which is why I've been careful not to.
    >>  ............................................
    pt  O telhado. Dizer torna real, e é por isso que eu tinha cuidado de não dizer.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. And what the roof means about next winter. Mostly that.
    >>  ............................................
    pt  É o telhado. E o que o telhado significa pro inverno que vem. Principalmente isso.
    >>  ............................................
  shy.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe. It's the roof.
    >>  ............................................
    pt  Uma delas, talvez. É o telhado.
    >>  ............................................
  shy.dialogue.conversations.checkin.late.name_it/2
    en  The roof. Since spring.
    >>  ............................................
    pt  O telhado. Desde a primavera.
    >>  ............................................
  shy.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. That's as much as I'll say about it tonight.
    >>  ............................................
    pt  É o telhado. É tudo que eu digo sobre isso hoje.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe! It's the roof. It's been the roof since spring and I'd hoped for something grander.
    >>  ............................................
    pt  Uma delas, talvez! É o telhado. É o telhado desde a primavera e eu esperava algo mais grandioso.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.late.name_it/2
    en  The roof. That's it. Not a tragedy, just a leak with excellent stamina.
    >>  ............................................
    pt  O telhado. É isso. Não é tragédia, só uma goteira com excelente resistência.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Naming it hasn't fixed it, which I feel is a design flaw.
    >>  ............................................
    pt  É o telhado. Nomear não consertou, o que eu considero uma falha de projeto.
    >>  ............................................
  witty.dialogue.conversations.checkin.late.name_it/1
    en  One of them, maybe! It's the roof. It's been the roof since spring and I'd hoped for something grander.
    >>  ............................................
    pt  Uma delas, talvez! É o telhado. É o telhado desde a primavera e eu esperava algo mais grandioso.
    >>  ............................................
  witty.dialogue.conversations.checkin.late.name_it/2
    en  The roof. That's it. Not a tragedy, just a leak with excellent stamina.
    >>  ............................................
    pt  O telhado. É isso. Não é tragédia, só uma goteira com excelente resistência.
    >>  ............................................
  witty.dialogue.conversations.checkin.late.name_it/3
    en  It's the roof. Naming it hasn't fixed it, which I feel is a design flaw.
    >>  ............................................
    pt  É o telhado. Nomear não consertou, o que eu considero uma falha de projeto.
    >>  ............................................
```

</details>


### Button `sit_with` — "Then we'll be awake together a while."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `checkin.late.told`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.late.sit_with` — accepted phrasings: "then we will be awake together a while"; "i will stay up with you"; "we can be awake together"
  - the message must contain one of: `together`, `awake`, `stay`
  - scored words: `together`(1.5), `awake`(1.2), `stay`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.followup.sit_with
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.followup.sit_with   [37 chars]
    en  Then we'll be awake together a while.
    >>  ............................................
    pt  Então a gente fica acordado junto um pouco.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.late.sit_with`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +3  _(recorded under topic `checkin.late.sit_with`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.late.sit_with
WHO    VILLAGER — what the player reads after pressing "Then we'll be awake together a while."
       spoken on: conversations.topic.checkin.late.followup, button `sit_with`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.sit_with`: the villager accepts. Subject `checkin.sleeplessness`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.late.sit_with/1   [59 chars]
    en  ...That's a kind way to spend an hour neither of us wanted.
    >>  ............................................
    pt  ...É um jeito gentil de passar uma hora que nenhum dos dois queria.
    >>  ............................................
  dialogue.conversations.checkin.late.sit_with/2   [56 chars]
    en  So it is. Sit. The fire's still got an hour in it, %1$s.
    >>  ............................................
    pt  É assim mesmo. Senta. O fogo ainda tem uma hora, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.late.sit_with/3   [73 chars]
    en  Together. That's the whole difference between this hour and the last one.
    >>  ............................................
    pt  Junto. É toda a diferença entre essa hora e a anterior.
    >>  ............................................
```


### Button `leave` — "Try to sleep."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.late.told` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.followup.leave   [13 chars]
    en  Try to sleep.
    >>  ............................................
    pt  Tenta dormir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.late.leave
WHO    VILLAGER — what the player reads after pressing "Try to sleep."
       spoken on: conversations.topic.checkin.late.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.leave`: the villager accepts. Subject `checkin.sleeplessness`, polarity `mixed`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.checkin.late.respond / leave
```

```text
  dialogue.conversations.checkin.late.leave/1   [51 chars]
    en  I'll try. Trying is most of what I do at this hour.
    >>  ............................................
    pt  Vou tentar. Tentar é quase tudo que eu faço a essa hora.
    >>  ............................................
  dialogue.conversations.checkin.late.leave/2   [29 chars]
    en  So I've found. You too, %1$s.
    >>  ............................................
    pt  Foi o que eu vi. Você também, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.late.leave/3   [28 chars]
    en  Mind how you go in the dark.
    >>  ............................................
    pt  Cuidado no escuro.
    >>  ............................................
```

---


## `conversations.topic.checkin.late.respond`

**Reached from 1 route(s):** `greet` / `checkin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.late` — e.g. "Late to be about. I'm no better — I've been up since the fire went low."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.late.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.late.respond   [32 chars]
    en  It's a strange hour for company.
    >>  ............................................
    pt  É uma hora estranha para ter companhia.
    >>  ............................................
```


### Button `ask_why_up` — "What's keeping you up?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `checkin.late.to.checkin.late`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.late.ask_why_up` — accepted phrasings: "what is keeping you up"; "why are you still awake"; "what is keeping you awake"
  - the message must contain one of: `awake`, `keeping`
  - scored words: `awake`(1.0), `keeping`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.respond.ask_why_up
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.respond.ask_why_up   [22 chars]
    en  What's keeping you up?
    >>  ............................................
    pt  O que está te mantendo acordado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `checkin.late.ask_why_up`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.late.followup`
- …where the player's next choices will be: "I'm no better. I never sleep either." | "Can you name any of it?" | "Then we'll be awake together a while." | "Try to sleep."

```text
POOL   dialogue key: dialogue.conversations.checkin.late.ask_why_up
WHO    VILLAGER — what the player reads after pressing "What's keeping you up?"
       spoken on: conversations.topic.checkin.late.respond, button `ask_why_up`
       leaves the player on: conversations.topic.checkin.late.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.told`: the villager discloses. Subject `checkin.sleeplessness`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `time:night`, `villager:sleepless` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, self_disclosure, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.late.ask_why_up/1   [74 chars]
    en  Nothing I could name. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear. É esse o problema — coisa com nome a gente consegue largar.
    >>  ............................................
  dialogue.conversations.checkin.late.ask_why_up/2   [53 chars]
    en  Tomorrow, mostly. It's very close at this hour, %1$s.
    >>  ............................................
    pt  O amanhã, principalmente. A esta hora ele fica bem perto, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.late.ask_why_up/3   [77 chars]
    en  The list. Everyone's got a list and mine reads itself aloud around this time.
    >>  ............................................
    pt  A lista. Todo mundo tem uma lista e a minha se lê em voz alta por volta desta hora.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. A named thing you can put down, %1$s. This one has no handle.
    >>  ............................................
    pt  Nada que eu saiba nomear. Uma coisa com nome você consegue largar, %1$s. Esta não tem alça.
    >>  ............................................
  anxious.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been trying to find the word for it since about the spring. There isn't one.
    >>  ............................................
    pt  Venho tentando achar a palavra desde a primavera. Não existe uma.
    >>  ............................................
  anxious.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing. Which is worse than something, and I know how that sounds.
    >>  ............................................
    pt  Nada. O que é pior que alguma coisa, e eu sei como isso soa.
    >>  ............................................
  athletic.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. It'll name itself eventually. They usually do.
    >>  ............................................
    pt  Nada que eu saiba nomear. Vai se nomear uma hora. Costumam se nomear.
    >>  ............................................
  athletic.dialogue.conversations.checkin.late.ask_why_up/2
    en  No reason in particular. Some nights go long and there's no arguing with them.
    >>  ............................................
    pt  Nenhum motivo em especial. Algumas noites se alongam e não adianta discutir.
    >>  ............................................
  athletic.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing on any list. I've stopped looking for it and I'm still up.
    >>  ............................................
    pt  Nada em lista nenhuma. Parei de procurar e continuo acordado.
    >>  ............................................
  confident.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  confident.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been through the list twice. There's nothing on it, and I'm still up.
    >>  ............................................
    pt  Já passei pela lista duas vezes. Não tem nada nela, e eu continuo acordado.
    >>  ............................................
  confident.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the honest answer and it is not a useful one.
    >>  ............................................
    pt  Sem motivo. É a resposta honesta e não é útil.
    >>  ............................................
  crabby.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  crabby.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been through the list twice. There's nothing on it, and I'm still up.
    >>  ............................................
    pt  Já passei pela lista duas vezes. Não tem nada nela, e eu continuo acordado.
    >>  ............................................
  crabby.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the honest answer and it is not a useful one.
    >>  ............................................
    pt  Sem motivo. É a resposta honesta e não é útil.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name, %1$s. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear, %1$s. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.late.ask_why_up/2
    en  I don't know. I'd tell you if I did; that's rather why I'm still standing here.
    >>  ............................................
    pt  Não sei. Eu te contaria se soubesse; é meio que por isso que eu ainda estou aqui de pé.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing in particular. It helps that somebody asked, though.
    >>  ............................................
    pt  Nada em especial. Mas ajuda que alguém tenha perguntado.
    >>  ............................................
  flirty.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name, %1$s. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear, %1$s. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  flirty.dialogue.conversations.checkin.late.ask_why_up/2
    en  I don't know. I'd tell you if I did; that's rather why I'm still standing here.
    >>  ............................................
    pt  Não sei. Eu te contaria se soubesse; é meio que por isso que eu ainda estou aqui de pé.
    >>  ............................................
  flirty.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing in particular. It helps that somebody asked, though.
    >>  ............................................
    pt  Nada em especial. Mas ajuda que alguém tenha perguntado.
    >>  ............................................
  friendly.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name, %1$s. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear, %1$s. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  friendly.dialogue.conversations.checkin.late.ask_why_up/2
    en  I don't know. I'd tell you if I did; that's rather why I'm still standing here.
    >>  ............................................
    pt  Não sei. Eu te contaria se soubesse; é meio que por isso que eu ainda estou aqui de pé.
    >>  ............................................
  friendly.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing in particular. It helps that somebody asked, though.
    >>  ............................................
    pt  Nada em especial. Mas ajuda que alguém tenha perguntado.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. A named thing you can put down, %1$s. This one has no handle.
    >>  ............................................
    pt  Nada que eu saiba nomear. Uma coisa com nome você consegue largar, %1$s. Esta não tem alça.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been trying to find the word for it since about the spring. There isn't one.
    >>  ............................................
    pt  Venho tentando achar a palavra desde a primavera. Não existe uma.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing. Which is worse than something, and I know how that sounds.
    >>  ............................................
    pt  Nada. O que é pior que alguma coisa, e eu sei como isso soa.
    >>  ............................................
  greedy.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  greedy.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been through the list twice. There's nothing on it, and I'm still up.
    >>  ............................................
    pt  Já passei pela lista duas vezes. Não tem nada nela, e eu continuo acordado.
    >>  ............................................
  greedy.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the honest answer and it is not a useful one.
    >>  ............................................
    pt  Sem motivo. É a resposta honesta e não é útil.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. That's the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear. É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been through the list twice. There's nothing on it, and I'm still up.
    >>  ............................................
    pt  Já passei pela lista duas vezes. Não tem nada nela, e eu continuo acordado.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the honest answer and it is not a useful one.
    >>  ............................................
    pt  Sem motivo. É a resposta honesta e não é útil.
    >>  ............................................
  introverted.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name.
    >>  ............................................
    pt  Nada que eu saiba nomear.
    >>  ............................................
  introverted.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've looked. There's nothing on the list and I'm still awake.
    >>  ............................................
    pt  Eu procurei. Não tem nada na lista e eu continuo acordado.
    >>  ............................................
  introverted.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the whole of it.
    >>  ............................................
    pt  Sem motivo. É tudo.
    >>  ............................................
  lazy.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. It'll name itself eventually. They usually do.
    >>  ............................................
    pt  Nada que eu saiba nomear. Vai se nomear uma hora. Costumam se nomear.
    >>  ............................................
  lazy.dialogue.conversations.checkin.late.ask_why_up/2
    en  No reason in particular. Some nights go long and there's no arguing with them.
    >>  ............................................
    pt  Nenhum motivo em especial. Algumas noites se alongam e não adianta discutir.
    >>  ............................................
  lazy.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing on any list. I've stopped looking for it and I'm still up.
    >>  ............................................
    pt  Nada em lista nenhuma. Parei de procurar e continuo acordado.
    >>  ............................................
  odd.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name.
    >>  ............................................
    pt  Nada que eu saiba nomear.
    >>  ............................................
  odd.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've looked. There's nothing on the list and I'm still awake.
    >>  ............................................
    pt  Eu procurei. Não tem nada na lista e eu continuo acordado.
    >>  ............................................
  odd.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the whole of it.
    >>  ............................................
    pt  Sem motivo. É tudo.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. It'll name itself eventually. They usually do.
    >>  ............................................
    pt  Nada que eu saiba nomear. Vai se nomear uma hora. Costumam se nomear.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.late.ask_why_up/2
    en  No reason in particular. Some nights go long and there's no arguing with them.
    >>  ............................................
    pt  Nenhum motivo em especial. Algumas noites se alongam e não adianta discutir.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing on any list. I've stopped looking for it and I'm still up.
    >>  ............................................
    pt  Nada em lista nenhuma. Parei de procurar e continuo acordado.
    >>  ............................................
  peppy.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name! Which is the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear! É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  peppy.dialogue.conversations.checkin.late.ask_why_up/2
    en  No idea. If I could name it I'd have dealt with it and gone to bed like a sensible person.
    >>  ............................................
    pt  Nem ideia. Se eu soubesse nomear eu teria resolvido e ido dormir como gente sensata.
    >>  ............................................
  peppy.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing at all. It's the not-knowing that keeps me vertical at this hour.
    >>  ............................................
    pt  Nada. É o não saber que me mantém de pé a esta hora.
    >>  ............................................
  playful.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name! Which is the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear! É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  playful.dialogue.conversations.checkin.late.ask_why_up/2
    en  No idea. If I could name it I'd have dealt with it and gone to bed like a sensible person.
    >>  ............................................
    pt  Nem ideia. Se eu soubesse nomear eu teria resolvido e ido dormir como gente sensata.
    >>  ............................................
  playful.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing at all. It's the not-knowing that keeps me vertical at this hour.
    >>  ............................................
    pt  Nada. É o não saber que me mantém de pé a esta hora.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. It'll name itself eventually. They usually do.
    >>  ............................................
    pt  Nada que eu saiba nomear. Vai se nomear uma hora. Costumam se nomear.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.late.ask_why_up/2
    en  No reason in particular. Some nights go long and there's no arguing with them.
    >>  ............................................
    pt  Nenhum motivo em especial. Algumas noites se alongam e não adianta discutir.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing on any list. I've stopped looking for it and I'm still up.
    >>  ............................................
    pt  Nada em lista nenhuma. Parei de procurar e continuo acordado.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name. A named thing you can put down, %1$s. This one has no handle.
    >>  ............................................
    pt  Nada que eu saiba nomear. Uma coisa com nome você consegue largar, %1$s. Esta não tem alça.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've been trying to find the word for it since about the spring. There isn't one.
    >>  ............................................
    pt  Venho tentando achar a palavra desde a primavera. Não existe uma.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing. Which is worse than something, and I know how that sounds.
    >>  ............................................
    pt  Nada. O que é pior que alguma coisa, e eu sei como isso soa.
    >>  ............................................
  shy.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name.
    >>  ............................................
    pt  Nada que eu saiba nomear.
    >>  ............................................
  shy.dialogue.conversations.checkin.late.ask_why_up/2
    en  I've looked. There's nothing on the list and I'm still awake.
    >>  ............................................
    pt  Eu procurei. Não tem nada na lista e eu continuo acordado.
    >>  ............................................
  shy.dialogue.conversations.checkin.late.ask_why_up/3
    en  No reason. That's the whole of it.
    >>  ............................................
    pt  Sem motivo. É tudo.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name! Which is the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear! É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.late.ask_why_up/2
    en  No idea. If I could name it I'd have dealt with it and gone to bed like a sensible person.
    >>  ............................................
    pt  Nem ideia. Se eu soubesse nomear eu teria resolvido e ido dormir como gente sensata.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing at all. It's the not-knowing that keeps me vertical at this hour.
    >>  ............................................
    pt  Nada. É o não saber que me mantém de pé a esta hora.
    >>  ............................................
  witty.dialogue.conversations.checkin.late.ask_why_up/1
    en  Nothing I could name! Which is the trouble — a named thing you can put down.
    >>  ............................................
    pt  Nada que eu saiba nomear! É esse o problema — uma coisa com nome você consegue largar.
    >>  ............................................
  witty.dialogue.conversations.checkin.late.ask_why_up/2
    en  No idea. If I could name it I'd have dealt with it and gone to bed like a sensible person.
    >>  ............................................
    pt  Nem ideia. Se eu soubesse nomear eu teria resolvido e ido dormir como gente sensata.
    >>  ............................................
  witty.dialogue.conversations.checkin.late.ask_why_up/3
    en  Nothing at all. It's the not-knowing that keeps me vertical at this hour.
    >>  ............................................
    pt  Nada. É o não saber que me mantém de pé a esta hora.
    >>  ............................................
```

</details>


### Button `send_to_bed` — "Go and sleep. It'll keep."

*stance family `encouragement` · tone `gentle` · answers the beat(s) `checkin.late.to.checkin.late`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.late.send_to_bed` — accepted phrasings: "go and sleep it will keep"; "you should go to bed"; "get some sleep it can wait"
  - the message must contain one of: `bed`, `sleep`
  - scored words: `bed`(1.2), `sleep`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.respond.send_to_bed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.respond.send_to_bed   [25 chars]
    en  Go and sleep. It'll keep.
    >>  ............................................
    pt  Vá dormir. Isso espera.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.late.send_to_bed`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +4  _(recorded under topic `checkin.late.send_to_bed`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.checkin.late.send_to_bed
WHO    VILLAGER — what the player reads after pressing "Go and sleep. It'll keep."
       spoken on: conversations.topic.checkin.late.respond, button `send_to_bed`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.send_to_bed.terminal`: the villager accepts. Subject `checkin.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.late.send_to_bed/1   [93 chars]
    en  ...You're right. It will keep. It always keeps and I never believe it until somebody says so.
    >>  ............................................
    pt  ...Você tem razão. Vai esperar. Sempre espera e eu nunca acredito até alguém dizer.
    >>  ............................................
  dialogue.conversations.checkin.late.send_to_bed/2   [44 chars]
    en  Just so. Bed. Thank you for the shove, %1$s.
    >>  ............................................
    pt  Pois é. Cama. Obrigado pelo empurrão, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.late.send_to_bed/3   [67 chars]
    en  Fair. Nothing at this hour ever looks the same size in the morning.
    >>  ............................................
    pt  Justo. Nada a esta hora tem o mesmo tamanho de manhã.
    >>  ............................................
```


### Button `keep_watch` — "I'll sit up with you a while."

*stance family `restraint` · tone `gentle` · answers the beat(s) `checkin.late.to.checkin.late`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.late.keep_watch` — accepted phrasings: "i will sit up with you a while"; "i will stay up with you"; "i will sit with you until you sleep"
  - the message must contain one of: `sit`
  - scored words: `sit`(1.2), `up`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.respond.keep_watch
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.respond.keep_watch   [29 chars]
    en  I'll sit up with you a while.
    >>  ............................................
    pt  Eu fico acordado com você um pouco.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.late.keep_watch`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — trust +4, tension -2  _(recorded under topic `checkin.late.keep_watch`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.checkin.late.keep_watch
WHO    VILLAGER — what the player reads after pressing "I'll sit up with you a while."
       spoken on: conversations.topic.checkin.late.respond, button `keep_watch`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.keep_watch.terminal`: the villager accepts. Subject `checkin.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.late.keep_watch/1   [86 chars]
    en  ...You'd sit up. With me. For no reason at all. Well. Pull the other stool over, then.
    >>  ............................................
    pt  ...Você ficaria acordado. Comigo. Sem motivo nenhum. Bom. Então puxe o outro banco.
    >>  ............................................
  dialogue.conversations.checkin.late.keep_watch/2   [54 chars]
    en  A while. Aye. It's better company than the list, %1$s.
    >>  ............................................
    pt  Um pouco. É. É companhia melhor que a lista, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.late.keep_watch/3   [55 chars]
    en  Don't talk, mind. Just be here. That's the useful part.
    >>  ............................................
    pt  Mas não fale. Só fique. Essa é a parte útil.
    >>  ............................................
```


### Button `leave` — "Goodnight, then."

*stance family `exit` · tone `plain` · answers the beat(s) `checkin.late.to.checkin.late` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.late.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.late.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.late.respond.leave   [16 chars]
    en  Goodnight, then.
    >>  ............................................
    pt  Boa noite, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.checkin.late.leave
WHO    VILLAGER — what the player reads after pressing "Goodnight, then."
       spoken on: conversations.topic.checkin.late.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.leave.terminal`: the villager accepts. Subject `checkin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.checkin.late.followup / leave
```

> Written out in full under **`conversations.topic.checkin.late.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.checkin.rough.dismissed.followup`

**Reached from 1 route(s):** `conversations.topic.checkin.rough.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.rough.dismiss` — e.g. "...I will, yes. Thank you for the vote of confidence."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.dismissed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.rough.dismissed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.rough.dismissed.followup   [15 chars]
    en  So that's that.
    >>  ............................................
    pt  Então é isso.
    >>  ............................................
```


### Button `apologize` — "That was thin of me. Sorry."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `checkin.rough.brushed_off` · offered only once the villager has actually said `player:brushed_off`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.dismissed.apologize` — accepted phrasings: "that was thin of me, sorry"; "sorry, i brushed that off"; "i am sorry, that was careless"
  - the message must contain one of: `thin`, `sorry`, `brushed`
  - scored words: `thin`(1.5), `sorry`(1.2), `brushed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.dismissed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.dismissed.followup.apologize   [27 chars]
    en  That was thin of me. Sorry.
    >>  ............................................
    pt  Foi pouco da minha parte. Desculpe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.rough.dismissed.apologize`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -3  _(recorded under topic `checkin.rough.dismissed.apologize`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.dismissed.apologize
WHO    VILLAGER — what the player reads after pressing "That was thin of me. Sorry."
       spoken on: conversations.topic.checkin.rough.dismissed.followup, button `apologize`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.dismissed.apologize`: the villager qualifys. Subject `checkin.hard_stretch`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.dismissed.apologize/1   [58 chars]
    en  ...It was. But you came back to it, which counts for more.
    >>  ............................................
    pt  ...Foi. Mas você voltou nisso, o que conta mais.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.apologize/2   [64 chars]
    en  Thin, aye. Half the village does it without ever noticing, %1$s.
    >>  ............................................
    pt  Pouco, é. Meio vilarejo faz isso sem nunca perceber, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.apologize/3   [63 chars]
    en  Accepted. Ask again tomorrow and I'll give you the real answer.
    >>  ............................................
    pt  Aceito. Pergunte de novo amanhã e eu dou a resposta de verdade.
    >>  ............................................
```


### Button `ask_properly` — "Let me ask again, properly."

*stance family `curiosity` · tone `plain` · outcome `accepted` · answers the beat(s) `checkin.rough.brushed_off` · offered only once the villager has actually said `state:rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.dismissed.ask_properly` — accepted phrasings: "let me ask again properly"; "let me try that again"; "i will ask again, properly this time"
  - the message must contain one of: `properly`, `again`
  - scored words: `properly`(1.5), `again`(1.2), `ask`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.dismissed.followup.ask_properly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.dismissed.followup.ask_properly   [27 chars]
    en  Let me ask again, properly.
    >>  ............................................
    pt  Deixa eu perguntar de novo, direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1, trust +2  _(recorded under topic `checkin.rough.dismissed.ask_properly`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.dismissed.ask_properly
WHO    VILLAGER — what the player reads after pressing "Let me ask again, properly."
       spoken on: conversations.topic.checkin.rough.dismissed.followup, button `ask_properly`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.dismissed.ask_properly`: the villager accepts. Subject `checkin.hard_stretch`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.dismissed.ask_properly/1   [65 chars]
    en  ...Go on, then. Slowly, mind. I'm out of practice at being asked.
    >>  ............................................
    pt  ...Então pergunte. Devagar, hein. Estou sem prática em ser perguntado.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.ask_properly/2   [66 chars]
    en  Properly. All right, %1$s. Sit down first — properly takes longer.
    >>  ............................................
    pt  Direito. Está bem, %1$s. Senta primeiro — direito demora mais.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.ask_properly/3   [44 chars]
    en  You may. I'll not make it easy, but you may.
    >>  ............................................
    pt  Pode. Não vou facilitar, mas pode.
    >>  ............................................
```


### Button `respect` — "Fair. I'll not push."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `checkin.rough.brushed_off`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.dismissed.respect` — accepted phrasings: "fair, i will not push"; "i will not pry"; "fair enough, i will not press you"
  - the message must contain one of: `push`, `pry`, `fair`
  - scored words: `push`(1.5), `pry`(1.5), `fair`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.dismissed.followup.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.dismissed.followup.respect   [20 chars]
    en  Fair. I'll not push.
    >>  ............................................
    pt  Justo. Não vou insistir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `checkin.rough.dismissed.respect`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.dismissed.respect
WHO    VILLAGER — what the player reads after pressing "Fair. I'll not push."
       spoken on: conversations.topic.checkin.rough.dismissed.followup, button `respect`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.dismissed.respect`: the villager accepts. Subject `checkin.hard_stretch`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.dismissed.respect/1   [57 chars]
    en  Good. Pushing was never going to get it out of me anyway.
    >>  ............................................
    pt  Bom. Insistir nunca ia arrancar isso de mim mesmo.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.respect/2   [48 chars]
    en  Quite. It'll keep, %1$s. Most of it always does.
    >>  ............................................
    pt  Exato. Pode esperar, %1$s. Quase tudo sempre pode.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.respect/3   [39 chars]
    en  So be it. Thank you for hearing the no.
    >>  ............................................
    pt  Que seja. Obrigado por ouvir o não.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.rough.brushed_off` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.dismissed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.dismissed.followup.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.dismissed.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.checkin.rough.dismissed.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.dismissed.leave`: the villager accepts. Subject `checkin.hard_stretch`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.dismissed.leave/1   [12 chars]
    en  True enough.
    >>  ............................................
    pt  Bem verdade.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismissed.leave/3   [3 chars]
    en  Mm.
    >>  ............................................
    pt  Mm.
    >>  ............................................
```

---


## `conversations.topic.checkin.rough.followup`

**Reached from 3 route(s):** `conversations.topic.checkin.rough.respond` / `listen`; `conversations.topic.checkin.rough.respond` / `listen`; `conversations.topic.checkin.rough.respond` / `ask`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.rough.ask` — e.g. "Nothing you could put on a list. It's the weight of the ordinary, mostly."
- `conversations.checkin.rough.listen` — e.g. "...Alright. It's the hours, mostly. And nobody noticing them until now."
- `conversations.checkin.rough.tense` — e.g. "...You're asking after me. After the other week. Alright — I'll answer, and we'll see how it goes."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.rough.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.rough.followup   [15 chars]
    en  So there it is.
    >>  ............................................
    pt  Então é isso.
    >>  ............................................
```


### Button `offer_help` — "Tell me one thing I can take off you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `checkin.rough.unnamed`, `checkin.rough.told`, `checkin.rough.wary` · offered only once the villager has actually said `state:rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.offer_help` — accepted phrasings: "tell me one thing i can take off you"; "let me take something"; "what can i help with"; "name one thing"
  - the message must contain one of: `take`, `help`, `thing`
  - scored words: `take`(1.2), `off`(0.5), `help`(1.2), `one`(0.6), `thing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.followup.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.followup.offer_help   [37 chars]
    en  Tell me one thing I can take off you.
    >>  ............................................
    pt  Me diz uma coisa que eu posso tirar das suas costas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when today's affection ledger, axis positive >= 8
- Does: disposition — warmth +2, respect +3  _(recorded under topic `checkin.rough.offer_help`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.offer_help.spent
WHO    VILLAGER — what the player reads after pressing "Tell me one thing I can take off you."
       spoken on: conversations.topic.checkin.rough.followup, button `offer_help`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.offer_help.spent`: the villager deflects. Subject `checkin.hard_stretch`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.offer_help.spent/1   [65 chars]
    en  You've given me plenty today. Save some of yourself for tomorrow.
    >>  ............................................
    pt  Você já me deu bastante hoje. Guarde um pouco de si para amanhã.
    >>  ............................................
  dialogue.conversations.checkin.rough.offer_help.spent/2   [73 chars]
    en  Enough, %1$s. Truly. Come back tomorrow and offer again and I'll say yes.
    >>  ............................................
    pt  Chega, %1$s. Sério. Volte amanhã e ofereça de novo que eu aceito.
    >>  ............................................
  dialogue.conversations.checkin.rough.offer_help.spent/3   [85 chars]
    en  That's twice today. I'll not have it a third time — go and do something for yourself.
    >>  ............................................
    pt  Já é a segunda vez hoje. Não vai ter terceira — vá fazer algo por você.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when today's affection ledger, axis positive >= 8  _(chance -2000)_
- Does: **hearts +1** — decision id `checkin.rough.offer_help`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +3  _(recorded under topic `checkin.rough.offer_help`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.offer_help
WHO    VILLAGER — what the player reads after pressing "Tell me one thing I can take off you."
       spoken on: conversations.topic.checkin.rough.followup, button `offer_help`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.offer_help`: the villager accepts. Subject `checkin.hard_stretch`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.offer_help/1   [68 chars]
    en  ...The wood, then. If you meant it. Tuesday, and bring the good axe.
    >>  ............................................
    pt  ...A lenha, então. Se você falou sério. Terça, e traga o machado bom.
    >>  ............................................
  dialogue.conversations.checkin.rough.offer_help/2   [59 chars]
    en  One thing. You're the first to make it that easy to answer.
    >>  ............................................
    pt  Uma coisa. Você é o primeiro a tornar isso fácil de responder.
    >>  ............................................
  dialogue.conversations.checkin.rough.offer_help/3   [63 chars]
    en  Hah. Alright. I'll think of something and hold you to it, %1$s.
    >>  ............................................
    pt  Rá. Certo. Vou pensar em algo e vou cobrar, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Yes. Tuesday. I'll be up half of Monday worrying you'll forget, but yes.
    >>  ............................................
    pt  A lenha. Sim. Terça. Vou passar metade da segunda preocupado que você esqueça, mas sim.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Good. Tuesday, dawn, and don't eat first — you'll regret it either way.
    >>  ............................................
    pt  A lenha. Bom. Terça, ao amanhecer, e não coma antes — você vai se arrepender de qualquer jeito.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. I don't ask twice, so I'm glad you answered the first time.
    >>  ............................................
    pt  A lenha, então. Terça. Eu não peço duas vezes, então ainda bem que você respondeu na primeira.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Tuesday. Bring the good axe and don't talk while we work.
    >>  ............................................
    pt  A lenha. Terça. Traga o machado bom e não fale enquanto a gente trabalha.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.offer_help
    en  The wood — brilliant. Tuesday. I'll get the others, we'll make an afternoon of it.
    >>  ............................................
    pt  A lenha — ótimo. Terça. Eu chamo os outros, a gente faz disso uma tarde.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. Wear something you don't mind ruining.
    >>  ............................................
    pt  A lenha, então. Terça. Vista algo que você não se importe de estragar.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then! Tuesday. Come early and I'll feed you first, that's the deal.
    >>  ............................................
    pt  A lenha, então! Terça. Venha cedo que eu te alimento primeiro, é esse o trato.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Tuesday. It won't fix anything, but it's one thing less, and that's the most I hope for.
    >>  ............................................
    pt  A lenha. Terça. Não conserta nada, mas é uma coisa a menos, e é o máximo que eu espero.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. Bring the good axe and I'll owe you — and I do pay debts.
    >>  ............................................
    pt  A lenha, então. Terça. Traga o machado bom e eu fico te devendo — e eu pago o que devo.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Tuesday. Bring the good axe and no conversation before noon.
    >>  ............................................
    pt  A lenha. Terça. Traga o machado bom e nada de conversa antes do meio-dia.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. We can do it without much talking, if that suits you.
    >>  ............................................
    pt  A lenha, então. Terça. Dá para fazer sem muita conversa, se te servir.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Tuesday. ...Wednesday. Let's say Wednesday and both feel better about it.
    >>  ............................................
    pt  A lenha. Terça. ...Quarta. Vamos dizer quarta e nós dois nos sentimos melhor.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. I'll have thought of a system by then. I always think of a system.
    >>  ............................................
    pt  A lenha, então. Terça. Até lá eu terei pensado num sistema. Eu sempre penso num sistema.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. There's no hurry in it; we'll go at whatever pace the day allows.
    >>  ............................................
    pt  A lenha, então. Terça. Não tem pressa nisso; vamos no ritmo que o dia permitir.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.offer_help
    en  The wood — YES. Tuesday. I'll bring snacks. Obviously I'll bring snacks.
    >>  ............................................
    pt  A lenha — ISSO. Terça. Eu levo lanche. Óbvio que eu levo lanche.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then! Tuesday. Loser stacks. I'll not say who's losing yet.
    >>  ............................................
    pt  A lenha, então! Terça. Quem perder empilha. Ainda não vou dizer quem está perdendo.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. Bring the good axe and we'll take our time over it.
    >>  ............................................
    pt  A lenha, então. Terça. Traga o machado bom e a gente vai com calma.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Tuesday. ...You meant it. I checked your face. You meant it.
    >>  ............................................
    pt  A lenha. Terça. ...Você falou sério. Eu conferi o seu rosto. Você falou sério.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.offer_help
    en  The wood. Tuesday. ...You don't have to. But — yes. Thank you.
    >>  ............................................
    pt  A lenha. Terça. ...Você não precisa. Mas — sim. Obrigado.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.offer_help
    en  The wood — grand! Tuesday. It'll be the best afternoon either of us has had all week.
    >>  ............................................
    pt  A lenha — ótimo! Terça. Vai ser a melhor tarde que qualquer um de nós teve na semana.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.offer_help
    en  The wood, then. Tuesday. Bring the good axe and a low opinion of my stacking.
    >>  ............................................
    pt  A lenha, então. Terça. Traga o machado bom e uma opinião baixa sobre o meu empilhamento.
    >>  ............................................
```

</details>


### Button `give_space` — "I'll stop asking. I'm around, though."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `checkin.rough.unnamed`, `checkin.rough.told`, `checkin.rough.wary` · offered only once the villager has actually said `state:rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.give_space` — accepted phrasings: "i will stop asking"; "i am around though"; "i will give you space"; "i will not push"
  - the message must contain one of: `stop`, `around`, `space`, `asking`
  - scored words: `stop`(1.2), `asking`(1.2), `around`(1.2), `space`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.followup.give_space
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.followup.give_space   [37 chars]
    en  I'll stop asking. I'm around, though.
    >>  ............................................
    pt  Vou parar de perguntar. Mas estou por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.rough.give_space`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +1  _(recorded under topic `checkin.rough.give_space`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.give_space
WHO    VILLAGER — what the player reads after pressing "I'll stop asking. I'm around, though."
       spoken on: conversations.topic.checkin.rough.followup, button `give_space`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.give_space`: the villager accepts. Subject `checkin.hard_stretch`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.give_space/1   [58 chars]
    en  That's the kindest version of leaving me alone I've heard.
    >>  ............................................
    pt  Essa é a versão mais gentil de me deixar em paz que já ouvi.
    >>  ............................................
  dialogue.conversations.checkin.rough.give_space/2   [54 chars]
    en  Around, but not hovering. Aye. That's the useful kind.
    >>  ............................................
    pt  Por perto, mas sem ficar em cima. É. Esse é o tipo útil.
    >>  ............................................
  dialogue.conversations.checkin.rough.give_space/3   [53 chars]
    en  Thank you. I'll come find you when I've words for it.
    >>  ............................................
    pt  Obrigado. Vou te procurar quando tiver palavras para isso.
    >>  ............................................
```


### Button `share_own` — "I've had a stretch like that myself."

*stance family `self_disclosure` · tone `plain` · outcome `accepted` · answers the beat(s) `checkin.rough.unnamed`, `checkin.rough.told`, `checkin.rough.wary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.share_own` — accepted phrasings: "i have had a stretch like that"; "i went through that myself"; "been through that myself"
  - the message must contain one of: `stretch`, `myself`, `through`
  - scored words: `stretch`(1.5), `myself`(1.2), `been`(0.4), `through`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.followup.share_own   [36 chars]
    en  I've had a stretch like that myself.
    >>  ............................................
    pt  Eu também já passei por uma fase assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts +1** — decision id `checkin.rough.share_own`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `checkin.rough.share_own`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.share_own.landed
WHO    VILLAGER — what the player reads after pressing "I've had a stretch like that myself."
       spoken on: conversations.topic.checkin.rough.followup, button `share_own`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.share_own.landed`: the villager accepts. Subject `checkin.hard_stretch`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.share_own.landed/1   [67 chars]
    en  You have? ...Then you know it isn't the sort of thing advice fixes.
    >>  ............................................
    pt  Você já? ...Então você sabe que não é o tipo de coisa que conselho resolve.
    >>  ............................................
  dialogue.conversations.checkin.rough.share_own.landed/2   [54 chars]
    en  Somebody else in the same boat. Strangely, that helps.
    >>  ............................................
    pt  Mais alguém no mesmo barco. Estranhamente, isso ajuda.
    >>  ............................................
  dialogue.conversations.checkin.rough.share_own.landed/3   [52 chars]
    en  Good. Not good for you — you know what I mean, %1$s.
    >>  ............................................
    pt  Que bom. Não bom para você — você entendeu, %1$s.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts -1** — decision id `checkin.rough.share_own`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect -2  _(recorded under topic `checkin.rough.share_own`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.share_own.flat
WHO    VILLAGER — what the player reads after pressing "I've had a stretch like that myself."
       spoken on: conversations.topic.checkin.rough.followup, button `share_own`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.share_own.flat`: the villager resists. Subject `checkin.hard_stretch`, polarity `negative`, ends conversation, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.share_own.flat/1   [45 chars]
    en  Everyone's had a stretch. I asked about mine.
    >>  ............................................
    pt  Todo mundo já passou por uma fase. Eu falei da minha.
    >>  ............................................
  dialogue.conversations.checkin.rough.share_own.flat/2   [45 chars]
    en  Mm. And here I thought we were discussing me.
    >>  ............................................
    pt  Hm. E eu achando que a gente estava falando de mim.
    >>  ............................................
  dialogue.conversations.checkin.rough.share_own.flat/3   [39 chars]
    en  Good. Well. Glad you got through yours.
    >>  ............................................
    pt  Bom. Bom. Que bom que você superou a sua.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Does: disposition — familiarity +2  _(recorded under topic `checkin.rough.share_own`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.share_own.polite
WHO    VILLAGER — what the player reads after pressing "I've had a stretch like that myself."
       spoken on: conversations.topic.checkin.rough.followup, button `share_own`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.share_own.polite`: the villager qualifys. Subject `checkin.hard_stretch`, polarity `neutral`, ends conversation, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.share_own.polite/1   [67 chars]
    en  Everyone has, I suppose. Doesn't make it lighter, but it's company.
    >>  ............................................
    pt  Todo mundo teve, imagino. Não deixa mais leve, mas é companhia.
    >>  ............................................
  dialogue.conversations.checkin.rough.share_own.polite/2   [33 chars]
    en  It is. It goes round, doesn't it.
    >>  ............................................
    pt  É sim. Isso circula, né.
    >>  ............................................
  dialogue.conversations.checkin.rough.share_own.polite/3   [56 chars]
    en  Then you'll know I'll be alright eventually. Eventually.
    >>  ............................................
    pt  Então você sabe que eu vou ficar bem em algum momento. Em algum momento.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.rough.unnamed`, `checkin.rough.told`, `checkin.rough.wary` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.followup.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.checkin.rough.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.leave`: the villager accepts. Subject `checkin.hard_stretch`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.checkin.rough.respond / leave
```

```text
  dialogue.conversations.checkin.rough.leave/1   [21 chars]
    en  Aye. Mind how you go.
    >>  ............................................
    pt  Tá. Se cuida.
    >>  ............................................
  dialogue.conversations.checkin.rough.leave/2   [32 chars]
    en  Off you go. Thanks for stopping.
    >>  ............................................
    pt  Pode ir. Obrigado por parar.
    >>  ............................................
  dialogue.conversations.checkin.rough.leave/3   [20 chars]
    en  Noted. I'll be here.
    >>  ............................................
    pt  Anotado. Vou estar por aqui.
    >>  ............................................
```

---


## `conversations.topic.checkin.rough.respond`

**Reached from 1 route(s):** `greet` / `checkin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.rough` — e.g. "You want the honest answer? It's been a heavy few days. Thanks for asking, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.rough.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.rough.respond   [31 chars]
    en  That's where I've been, anyway.
    >>  ............................................
    pt  É aí que eu ando, enfim.
    >>  ............................................
```


### Button `listen` — "I'm listening, if you want to say more."

*stance family `restraint` · tone `gentle` · answers the beat(s) `checkin.rough.to.checkin.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.listen` — accepted phrasings: "i am listening"; "say more if you want"; "i will listen"; "tell me more if you want"
  - the message must contain one of: `listening`, `listen`, `more`
  - scored words: `listening`(1.5), `listen`(1.5), `more`(0.6), `say`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.respond.listen   [39 chars]
    en  I'm listening, if you want to say more.
    >>  ............................................
    pt  Estou ouvindo, se quiser falar mais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when disposition tension >= 30
- Fires when: RULED OUT when the `dispositions` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `checkin.rough.listen`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `checkin.rough.listen`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.rough.followup`
- …where the player's next choices will be: "Tell me one thing I can take off you." | "I'll stop asking. I'm around, though." | "I've had a stretch like that myself." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.tense
WHO    VILLAGER — what the player reads after pressing "I'm listening, if you want to say more."
       spoken on: conversations.topic.checkin.rough.respond, button `listen`
       leaves the player on: conversations.topic.checkin.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.wary`: the villager discloses. Subject `checkin.hard_stretch`, polarity `negative`, guarded, outcome `engaged`.
NOTE   this is the line that establishes `state:rough`, `relationship:tense` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, restraint, self_disclosure, empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.tense/1   [98 chars]
    en  ...You're asking after me. After the other week. Alright — I'll answer, and we'll see how it goes.
    >>  ............................................
    pt  ...Você está perguntando de mim. Depois daquela semana. Tudo bem — eu respondo, e a gente vê.
    >>  ............................................
  dialogue.conversations.checkin.rough.tense/2   [72 chars]
    en  Hm. Asking is a start. It's not the whole of it, %1$s, but it's a start.
    >>  ............................................
    pt  Hm. Perguntar é um começo. Não é tudo, %1$s, mas é um começo.
    >>  ............................................
  dialogue.conversations.checkin.rough.tense/3   [64 chars]
    en  I'll tell you, but I'll be watching how you take it. Old habits.
    >>  ............................................
    pt  Eu conto, mas vou reparar em como você recebe. Costume antigo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. I'd not been sure you would, %1$s.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Eu não tinha certeza se perguntaria, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough.tense/2
    en  You came back. I'd been telling myself you wouldn't, so I'll need a moment.
    >>  ............................................
    pt  Você voltou. Eu vinha dizendo a mim que você não voltaria, então vou precisar de um momento.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough.tense/3
    en  ...Alright. I'll answer. I'm glad it's you asking and I'm not sure how to say that.
    >>  ............................................
    pt  ...Está bem. Eu respondo. Fico contente que seja você perguntando e não sei bem como dizer isso.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me, after the other week. Alright. These things pass and this one has.
    >>  ............................................
    pt  Você está perguntando de mim, depois da outra semana. Está bem. Essas coisas passam e essa passou.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.tense/2
    en  You've come back. Good. I'd have waited longer if it took longer.
    >>  ............................................
    pt  Você voltou. Bom. Eu teria esperado mais se levasse mais.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.tense/3
    en  Right. The other week was the other week. Ask away.
    >>  ............................................
    pt  Certo. A outra semana foi a outra semana. Pergunte.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. Alright — I'll answer, and we'll see how it goes.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Está bem — eu respondo, e a gente vê no que dá.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.tense/2
    en  After what was said, you're asking. Fine. I'll take it at face value for now.
    >>  ............................................
    pt  Depois do que foi dito, você está perguntando. Tudo bem. Vou levar a sério por ora.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.tense/3
    en  Right. You've come back. I'll answer and I'll not pretend the other week didn't happen.
    >>  ............................................
    pt  Certo. Você voltou. Eu respondo e não vou fingir que a outra semana não aconteceu.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. Alright — I'll answer, and we'll see how it goes.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Está bem — eu respondo, e a gente vê no que dá.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.tense/2
    en  After what was said, you're asking. Fine. I'll take it at face value for now.
    >>  ............................................
    pt  Depois do que foi dito, você está perguntando. Tudo bem. Vou levar a sério por ora.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.tense/3
    en  Right. You've come back. I'll answer and I'll not pretend the other week didn't happen.
    >>  ............................................
    pt  Certo. Você voltou. Eu respondo e não vou fingir que a outra semana não aconteceu.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. That means more than you probably intended, %1$s.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Isso significa mais do que você pretendia, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.tense/2
    en  You came back. Right. I'll answer, and I'd rather do this than not.
    >>  ............................................
    pt  Você voltou. Certo. Eu respondo, e eu prefiro isso a não fazer.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.tense/3
    en  After the other week, and you've still asked. Alright. Sit down.
    >>  ............................................
    pt  Depois da outra semana, e você perguntou mesmo assim. Está bem. Sente-se.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. That means more than you probably intended, %1$s.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Isso significa mais do que você pretendia, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.tense/2
    en  You came back. Right. I'll answer, and I'd rather do this than not.
    >>  ............................................
    pt  Você voltou. Certo. Eu respondo, e eu prefiro isso a não fazer.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.tense/3
    en  After the other week, and you've still asked. Alright. Sit down.
    >>  ............................................
    pt  Depois da outra semana, e você perguntou mesmo assim. Está bem. Sente-se.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. That means more than you probably intended, %1$s.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Isso significa mais do que você pretendia, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.tense/2
    en  You came back. Right. I'll answer, and I'd rather do this than not.
    >>  ............................................
    pt  Você voltou. Certo. Eu respondo, e eu prefiro isso a não fazer.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.tense/3
    en  After the other week, and you've still asked. Alright. Sit down.
    >>  ............................................
    pt  Depois da outra semana, e você perguntou mesmo assim. Está bem. Sente-se.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. I'd not been sure you would, %1$s.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Eu não tinha certeza se perguntaria, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.tense/2
    en  You came back. I'd been telling myself you wouldn't, so I'll need a moment.
    >>  ............................................
    pt  Você voltou. Eu vinha dizendo a mim que você não voltaria, então vou precisar de um momento.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.tense/3
    en  ...Alright. I'll answer. I'm glad it's you asking and I'm not sure how to say that.
    >>  ............................................
    pt  ...Está bem. Eu respondo. Fico contente que seja você perguntando e não sei bem como dizer isso.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. Alright — I'll answer, and we'll see how it goes.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Está bem — eu respondo, e a gente vê no que dá.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.tense/2
    en  After what was said, you're asking. Fine. I'll take it at face value for now.
    >>  ............................................
    pt  Depois do que foi dito, você está perguntando. Tudo bem. Vou levar a sério por ora.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.tense/3
    en  Right. You've come back. I'll answer and I'll not pretend the other week didn't happen.
    >>  ............................................
    pt  Certo. Você voltou. Eu respondo e não vou fingir que a outra semana não aconteceu.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. Alright — I'll answer, and we'll see how it goes.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Está bem — eu respondo, e a gente vê no que dá.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.tense/2
    en  After what was said, you're asking. Fine. I'll take it at face value for now.
    >>  ............................................
    pt  Depois do que foi dito, você está perguntando. Tudo bem. Vou levar a sério por ora.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.tense/3
    en  Right. You've come back. I'll answer and I'll not pretend the other week didn't happen.
    >>  ............................................
    pt  Certo. Você voltou. Eu respondo e não vou fingir que a outra semana não aconteceu.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.tense/1
    en  You're asking. After the other week.
    >>  ............................................
    pt  Você está perguntando. Depois da outra semana.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.tense/2
    en  Right. I'll answer.
    >>  ............................................
    pt  Certo. Eu respondo.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.tense/3
    en  ...Alright. Go on, then.
    >>  ............................................
    pt  ...Está bem. Vá em frente.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me, after the other week. Alright. These things pass and this one has.
    >>  ............................................
    pt  Você está perguntando de mim, depois da outra semana. Está bem. Essas coisas passam e essa passou.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.tense/2
    en  You've come back. Good. I'd have waited longer if it took longer.
    >>  ............................................
    pt  Você voltou. Bom. Eu teria esperado mais se levasse mais.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.tense/3
    en  Right. The other week was the other week. Ask away.
    >>  ............................................
    pt  Certo. A outra semana foi a outra semana. Pergunte.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.tense/1
    en  You're asking. After the other week.
    >>  ............................................
    pt  Você está perguntando. Depois da outra semana.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.tense/2
    en  Right. I'll answer.
    >>  ............................................
    pt  Certo. Eu respondo.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.tense/3
    en  ...Alright. Go on, then.
    >>  ............................................
    pt  ...Está bem. Vá em frente.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me, after the other week. Alright. These things pass and this one has.
    >>  ............................................
    pt  Você está perguntando de mim, depois da outra semana. Está bem. Essas coisas passam e essa passou.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.tense/2
    en  You've come back. Good. I'd have waited longer if it took longer.
    >>  ............................................
    pt  Você voltou. Bom. Eu teria esperado mais se levasse mais.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.tense/3
    en  Right. The other week was the other week. Ask away.
    >>  ............................................
    pt  Certo. A outra semana foi a outra semana. Pergunte.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me! After the other week. Bold. Right — go on, then.
    >>  ............................................
    pt  Você está perguntando de mim! Depois da outra semana. Ousado. Certo — vá em frente.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.tense/2
    en  After all that, you've come back to ask. I'll give you points for nerve, %1$s.
    >>  ............................................
    pt  Depois de tudo aquilo, você voltou pra perguntar. Ponto pra coragem, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.tense/3
    en  Right, well. You're here. I'll answer and we'll both pretend that's normal.
    >>  ............................................
    pt  Certo, bom. Você está aqui. Eu respondo e nós dois fingimos que é normal.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me! After the other week. Bold. Right — go on, then.
    >>  ............................................
    pt  Você está perguntando de mim! Depois da outra semana. Ousado. Certo — vá em frente.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.tense/2
    en  After all that, you've come back to ask. I'll give you points for nerve, %1$s.
    >>  ............................................
    pt  Depois de tudo aquilo, você voltou pra perguntar. Ponto pra coragem, %1$s.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.tense/3
    en  Right, well. You're here. I'll answer and we'll both pretend that's normal.
    >>  ............................................
    pt  Certo, bom. Você está aqui. Eu respondo e nós dois fingimos que é normal.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me, after the other week. Alright. These things pass and this one has.
    >>  ............................................
    pt  Você está perguntando de mim, depois da outra semana. Está bem. Essas coisas passam e essa passou.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.tense/2
    en  You've come back. Good. I'd have waited longer if it took longer.
    >>  ............................................
    pt  Você voltou. Bom. Eu teria esperado mais se levasse mais.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.tense/3
    en  Right. The other week was the other week. Ask away.
    >>  ............................................
    pt  Certo. A outra semana foi a outra semana. Pergunte.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me. After the other week. I'd not been sure you would, %1$s.
    >>  ............................................
    pt  Você está perguntando de mim. Depois da outra semana. Eu não tinha certeza se perguntaria, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.tense/2
    en  You came back. I'd been telling myself you wouldn't, so I'll need a moment.
    >>  ............................................
    pt  Você voltou. Eu vinha dizendo a mim que você não voltaria, então vou precisar de um momento.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.tense/3
    en  ...Alright. I'll answer. I'm glad it's you asking and I'm not sure how to say that.
    >>  ............................................
    pt  ...Está bem. Eu respondo. Fico contente que seja você perguntando e não sei bem como dizer isso.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.tense/1
    en  You're asking. After the other week.
    >>  ............................................
    pt  Você está perguntando. Depois da outra semana.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.tense/2
    en  Right. I'll answer.
    >>  ............................................
    pt  Certo. Eu respondo.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.tense/3
    en  ...Alright. Go on, then.
    >>  ............................................
    pt  ...Está bem. Vá em frente.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me! After the other week. Bold. Right — go on, then.
    >>  ............................................
    pt  Você está perguntando de mim! Depois da outra semana. Ousado. Certo — vá em frente.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.tense/2
    en  After all that, you've come back to ask. I'll give you points for nerve, %1$s.
    >>  ............................................
    pt  Depois de tudo aquilo, você voltou pra perguntar. Ponto pra coragem, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.tense/3
    en  Right, well. You're here. I'll answer and we'll both pretend that's normal.
    >>  ............................................
    pt  Certo, bom. Você está aqui. Eu respondo e nós dois fingimos que é normal.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.tense/1
    en  You're asking after me! After the other week. Bold. Right — go on, then.
    >>  ............................................
    pt  Você está perguntando de mim! Depois da outra semana. Ousado. Certo — vá em frente.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.tense/2
    en  After all that, you've come back to ask. I'll give you points for nerve, %1$s.
    >>  ............................................
    pt  Depois de tudo aquilo, você voltou pra perguntar. Ponto pra coragem, %1$s.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.tense/3
    en  Right, well. You're here. I'll answer and we'll both pretend that's normal.
    >>  ............................................
    pt  Certo, bom. Você está aqui. Eu respondo e nós dois fingimos que é normal.
    >>  ............................................
```

</details>


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when disposition tension >= 30  _(chance -2000)_
- Does: **hearts +1** — decision id `checkin.rough.listen`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `checkin.rough.listen`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.rough.followup`
- …where the player's next choices will be: "Tell me one thing I can take off you." | "I'll stop asking. I'm around, though." | "I've had a stretch like that myself." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.listen
WHO    VILLAGER — what the player reads after pressing "I'm listening, if you want to say more."
       spoken on: conversations.topic.checkin.rough.respond, button `listen`
       leaves the player on: conversations.topic.checkin.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.told`: the villager discloses. Subject `checkin.hard_stretch`, polarity `negative`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `state:rough`, `villager:tired` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, restraint, self_disclosure, empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.listen/1   [71 chars]
    en  ...Alright. It's the hours, mostly. And nobody noticing them until now.
    >>  ............................................
    pt  ...Tá bom. São as horas, principalmente. E ninguém notar até agora.
    >>  ............................................
  dialogue.conversations.checkin.rough.listen/2   [67 chars]
    en  You'll actually wait for the answer. That's rarer than you'd think.
    >>  ............................................
    pt  Você vai mesmo esperar a resposta. Isso é mais raro do que parece.
    >>  ............................................
  dialogue.conversations.checkin.rough.listen/3   [55 chars]
    en  Then I'll say it: I'm tired in a way sleep doesn't fix.
    >>  ............................................
    pt  Então vou dizer: estou cansado de um jeito que dormir não resolve.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours. And nobody noticing them until now, which I'd not meant to say aloud.
    >>  ............................................
    pt  São as horas. E ninguém reparar nelas até agora, o que eu não pretendia dizer em voz alta.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough.listen/2
    en  The hours. I'd got used to them. Getting used to a thing isn't the same as it being all right.
    >>  ............................................
    pt  As horas. Eu tinha me acostumado. Acostumar não é o mesmo que estar tudo bem.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, %1$s. And I'd stopped expecting anybody to ask about them.
    >>  ............................................
    pt  São as horas, %1$s. E eu tinha parado de esperar que alguém perguntasse.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. They've been the hours a long while.
    >>  ............................................
    pt  São as horas, principalmente. São as horas faz tempo.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.listen/2
    en  The hours. They'll ease when the season turns. They generally do.
    >>  ............................................
    pt  As horas. Vão aliviar quando a estação virar. Geralmente aliviam.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, and I'll get used to them again. I always have.
    >>  ............................................
    pt  São as horas, e eu vou me acostumar de novo. Sempre me acostumei.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same hours as always, and they've started costing more.
    >>  ............................................
    pt  As horas. As mesmas de sempre, e começaram a custar mais.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. That's the whole of it and you're the first to ask.
    >>  ............................................
    pt  São as horas. É tudo, e você é o primeiro a perguntar.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same hours as always, and they've started costing more.
    >>  ............................................
    pt  As horas. As mesmas de sempre, e começaram a custar mais.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. That's the whole of it and you're the first to ask.
    >>  ............................................
    pt  São as horas. É tudo, e você é o primeiro a perguntar.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now, %1$s.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.listen/2
    en  The hours. I'd not have said so if you hadn't sat down for it.
    >>  ............................................
    pt  As horas. Eu não teria dito se você não tivesse sentado pra ouvir.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours — and the fact that you asked twice. That's the part I'll remember.
    >>  ............................................
    pt  São as horas — e o fato de você ter perguntado duas vezes. É a parte que eu vou lembrar.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now, %1$s.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.listen/2
    en  The hours. I'd not have said so if you hadn't sat down for it.
    >>  ............................................
    pt  As horas. Eu não teria dito se você não tivesse sentado pra ouvir.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours — and the fact that you asked twice. That's the part I'll remember.
    >>  ............................................
    pt  São as horas — e o fato de você ter perguntado duas vezes. É a parte que eu vou lembrar.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now, %1$s.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.listen/2
    en  The hours. I'd not have said so if you hadn't sat down for it.
    >>  ............................................
    pt  As horas. Eu não teria dito se você não tivesse sentado pra ouvir.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours — and the fact that you asked twice. That's the part I'll remember.
    >>  ............................................
    pt  São as horas — e o fato de você ter perguntado duas vezes. É a parte que eu vou lembrar.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours. And nobody noticing them until now, which I'd not meant to say aloud.
    >>  ............................................
    pt  São as horas. E ninguém reparar nelas até agora, o que eu não pretendia dizer em voz alta.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.listen/2
    en  The hours. I'd got used to them. Getting used to a thing isn't the same as it being all right.
    >>  ............................................
    pt  As horas. Eu tinha me acostumado. Acostumar não é o mesmo que estar tudo bem.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, %1$s. And I'd stopped expecting anybody to ask about them.
    >>  ............................................
    pt  São as horas, %1$s. E eu tinha parado de esperar que alguém perguntasse.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same hours as always, and they've started costing more.
    >>  ............................................
    pt  As horas. As mesmas de sempre, e começaram a custar mais.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. That's the whole of it and you're the first to ask.
    >>  ............................................
    pt  São as horas. É tudo, e você é o primeiro a perguntar.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. And nobody noticing them until now.
    >>  ............................................
    pt  São as horas, principalmente. E ninguém reparar nelas até agora.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same hours as always, and they've started costing more.
    >>  ............................................
    pt  As horas. As mesmas de sempre, e começaram a custar mais.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. That's the whole of it and you're the first to ask.
    >>  ............................................
    pt  São as horas. É tudo, e você é o primeiro a perguntar.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly.
    >>  ............................................
    pt  São as horas, principalmente.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.listen/2
    en  The hours. And nobody noticing them.
    >>  ............................................
    pt  As horas. E ninguém reparar nelas.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.listen/3
    en  The hours. That's as much as I've worked out.
    >>  ............................................
    pt  As horas. É tudo que eu consegui entender.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. They've been the hours a long while.
    >>  ............................................
    pt  São as horas, principalmente. São as horas faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.listen/2
    en  The hours. They'll ease when the season turns. They generally do.
    >>  ............................................
    pt  As horas. Vão aliviar quando a estação virar. Geralmente aliviam.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, and I'll get used to them again. I always have.
    >>  ............................................
    pt  São as horas, e eu vou me acostumar de novo. Sempre me acostumei.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly.
    >>  ............................................
    pt  São as horas, principalmente.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.listen/2
    en  The hours. And nobody noticing them.
    >>  ............................................
    pt  As horas. E ninguém reparar nelas.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.listen/3
    en  The hours. That's as much as I've worked out.
    >>  ............................................
    pt  As horas. É tudo que eu consegui entender.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. They've been the hours a long while.
    >>  ............................................
    pt  São as horas, principalmente. São as horas faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.listen/2
    en  The hours. They'll ease when the season turns. They generally do.
    >>  ............................................
    pt  As horas. Vão aliviar quando a estação virar. Geralmente aliviam.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, and I'll get used to them again. I always have.
    >>  ............................................
    pt  São as horas, e eu vou me acostumar de novo. Sempre me acostumei.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly! And nobody noticing them until you did, which I'll admit landed.
    >>  ............................................
    pt  São as horas, principalmente! E ninguém reparar nelas até você, o que eu admito que pegou.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same as always. They've simply started charging interest.
    >>  ............................................
    pt  As horas. As mesmas de sempre. Só começaram a cobrar juros.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. Not thrilling, is it. But you asked, and there it is.
    >>  ............................................
    pt  São as horas. Não é empolgante, né. Mas você perguntou, e pronto.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly! And nobody noticing them until you did, which I'll admit landed.
    >>  ............................................
    pt  São as horas, principalmente! E ninguém reparar nelas até você, o que eu admito que pegou.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same as always. They've simply started charging interest.
    >>  ............................................
    pt  As horas. As mesmas de sempre. Só começaram a cobrar juros.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. Not thrilling, is it. But you asked, and there it is.
    >>  ............................................
    pt  São as horas. Não é empolgante, né. Mas você perguntou, e pronto.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly. They've been the hours a long while.
    >>  ............................................
    pt  São as horas, principalmente. São as horas faz tempo.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.listen/2
    en  The hours. They'll ease when the season turns. They generally do.
    >>  ............................................
    pt  As horas. Vão aliviar quando a estação virar. Geralmente aliviam.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, and I'll get used to them again. I always have.
    >>  ............................................
    pt  São as horas, e eu vou me acostumar de novo. Sempre me acostumei.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours. And nobody noticing them until now, which I'd not meant to say aloud.
    >>  ............................................
    pt  São as horas. E ninguém reparar nelas até agora, o que eu não pretendia dizer em voz alta.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.listen/2
    en  The hours. I'd got used to them. Getting used to a thing isn't the same as it being all right.
    >>  ............................................
    pt  As horas. Eu tinha me acostumado. Acostumar não é o mesmo que estar tudo bem.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours, %1$s. And I'd stopped expecting anybody to ask about them.
    >>  ............................................
    pt  São as horas, %1$s. E eu tinha parado de esperar que alguém perguntasse.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly.
    >>  ............................................
    pt  São as horas, principalmente.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.listen/2
    en  The hours. And nobody noticing them.
    >>  ............................................
    pt  As horas. E ninguém reparar nelas.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.listen/3
    en  The hours. That's as much as I've worked out.
    >>  ............................................
    pt  As horas. É tudo que eu consegui entender.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly! And nobody noticing them until you did, which I'll admit landed.
    >>  ............................................
    pt  São as horas, principalmente! E ninguém reparar nelas até você, o que eu admito que pegou.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same as always. They've simply started charging interest.
    >>  ............................................
    pt  As horas. As mesmas de sempre. Só começaram a cobrar juros.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. Not thrilling, is it. But you asked, and there it is.
    >>  ............................................
    pt  São as horas. Não é empolgante, né. Mas você perguntou, e pronto.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.listen/1
    en  It's the hours, mostly! And nobody noticing them until you did, which I'll admit landed.
    >>  ............................................
    pt  São as horas, principalmente! E ninguém reparar nelas até você, o que eu admito que pegou.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.listen/2
    en  The hours. Same as always. They've simply started charging interest.
    >>  ............................................
    pt  As horas. As mesmas de sempre. Só começaram a cobrar juros.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.listen/3
    en  It's the hours. Not thrilling, is it. But you asked, and there it is.
    >>  ............................................
    pt  São as horas. Não é empolgante, né. Mas você perguntou, e pronto.
    >>  ............................................
```

</details>


### Button `ask` — "What's been weighing on you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `checkin.rough.to.checkin.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.ask` — accepted phrasings: "what is weighing on you"; "what is bothering you"; "what is wrong"; "what is troubling you"
  - the message must contain one of: `weighing`, `bothering`, `wrong`, `troubling`
  - scored words: `weighing`(1.5), `bothering`(1.5), `wrong`(1.0), `troubling`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.respond.ask   [28 chars]
    en  What's been weighing on you?
    >>  ............................................
    pt  O que tem pesado em você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +2, familiarity +1  _(recorded under topic `checkin.rough.ask`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.rough.followup`
- …where the player's next choices will be: "Tell me one thing I can take off you." | "I'll stop asking. I'm around, though." | "I've had a stretch like that myself." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.ask
WHO    VILLAGER — what the player reads after pressing "What's been weighing on you?"
       spoken on: conversations.topic.checkin.rough.respond, button `ask`
       leaves the player on: conversations.topic.checkin.rough.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.unnamed`: the villager disclose_problems. Subject `checkin.hard_stretch`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `state:rough` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, restraint, self_disclosure, empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.ask/1   [73 chars]
    en  Nothing you could put on a list. It's the weight of the ordinary, mostly.
    >>  ............................................
    pt  Nada que dê para colocar numa lista. É o peso do comum, na maior parte.
    >>  ............................................
  dialogue.conversations.checkin.rough.ask/2   [65 chars]
    en  A few things. None of them the sort you can hand to someone else.
    >>  ............................................
    pt  Algumas coisas. Nenhuma delas do tipo que dá para entregar a outra pessoa.
    >>  ............................................
  dialogue.conversations.checkin.rough.ask/3   [70 chars]
    en  Ask me on a better day and I'd say nothing at all. Today — a fair bit.
    >>  ............................................
    pt  Me pergunte num dia melhor e eu diria que nada. Hoje — bastante coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. That's what makes it hard to say out loud, %1$s.
    >>  ............................................
    pt  Nada que caiba numa lista. É isso que dificulta dizer em voz alta, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. If there were one thing I could carry it. It's the forty.
    >>  ............................................
    pt  Nenhuma coisa só. Se fosse uma coisa eu carregaria. São as quarenta.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. And the fact that ordinary is supposed to be the easy part.
    >>  ............................................
    pt  O comum. E o fato de que o comum devia ser a parte fácil.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. The weight of the ordinary. It shifts eventually.
    >>  ............................................
    pt  Nada que caiba numa lista. O peso do comum. Muda uma hora.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. It builds slowly and it goes slowly, and it always has.
    >>  ............................................
    pt  Nenhuma coisa só. Acumula devagar e passa devagar, e sempre foi assim.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It'll ease. It has every other time.
    >>  ............................................
    pt  O comum. Vai aliviar. Aliviou todas as outras vezes.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. It's the weight of the ordinary, mostly.
    >>  ............................................
    pt  Nada que caiba numa lista. É o peso do comum, principalmente.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones, and forty is heavier than one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas, e quarenta pesa mais que uma.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It adds up and nobody sees it adding.
    >>  ............................................
    pt  O comum. Vai somando e ninguém vê somar.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. It's the weight of the ordinary, mostly.
    >>  ............................................
    pt  Nada que caiba numa lista. É o peso do comum, principalmente.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones, and forty is heavier than one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas, e quarenta pesa mais que uma.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It adds up and nobody sees it adding.
    >>  ............................................
    pt  O comum. Vai somando e ninguém vê somar.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list, %1$s. It's the weight of the ordinary.
    >>  ............................................
    pt  Nada que caiba numa lista, %1$s. É o peso do comum.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. That's why I've not mentioned it — there's nothing to point at.
    >>  ............................................
    pt  Nenhuma coisa só. Por isso eu não mencionei — não tem pra onde apontar.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It helps to be asked, even when there's no answer to give.
    >>  ............................................
    pt  O comum. Ajuda ser perguntado, mesmo sem resposta pra dar.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list, %1$s. It's the weight of the ordinary.
    >>  ............................................
    pt  Nada que caiba numa lista, %1$s. É o peso do comum.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. That's why I've not mentioned it — there's nothing to point at.
    >>  ............................................
    pt  Nenhuma coisa só. Por isso eu não mencionei — não tem pra onde apontar.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It helps to be asked, even when there's no answer to give.
    >>  ............................................
    pt  O comum. Ajuda ser perguntado, mesmo sem resposta pra dar.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list, %1$s. It's the weight of the ordinary.
    >>  ............................................
    pt  Nada que caiba numa lista, %1$s. É o peso do comum.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. That's why I've not mentioned it — there's nothing to point at.
    >>  ............................................
    pt  Nenhuma coisa só. Por isso eu não mencionei — não tem pra onde apontar.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It helps to be asked, even when there's no answer to give.
    >>  ............................................
    pt  O comum. Ajuda ser perguntado, mesmo sem resposta pra dar.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. That's what makes it hard to say out loud, %1$s.
    >>  ............................................
    pt  Nada que caiba numa lista. É isso que dificulta dizer em voz alta, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. If there were one thing I could carry it. It's the forty.
    >>  ............................................
    pt  Nenhuma coisa só. Se fosse uma coisa eu carregaria. São as quarenta.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. And the fact that ordinary is supposed to be the easy part.
    >>  ............................................
    pt  O comum. E o fato de que o comum devia ser a parte fácil.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. It's the weight of the ordinary, mostly.
    >>  ............................................
    pt  Nada que caiba numa lista. É o peso do comum, principalmente.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones, and forty is heavier than one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas, e quarenta pesa mais que uma.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It adds up and nobody sees it adding.
    >>  ............................................
    pt  O comum. Vai somando e ninguém vê somar.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. It's the weight of the ordinary, mostly.
    >>  ............................................
    pt  Nada que caiba numa lista. É o peso do comum, principalmente.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones, and forty is heavier than one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas, e quarenta pesa mais que uma.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It adds up and nobody sees it adding.
    >>  ............................................
    pt  O comum. Vai somando e ninguém vê somar.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list.
    >>  ............................................
    pt  Nada que caiba numa lista.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. That's all.
    >>  ............................................
    pt  O comum. Só isso.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. The weight of the ordinary. It shifts eventually.
    >>  ............................................
    pt  Nada que caiba numa lista. O peso do comum. Muda uma hora.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. It builds slowly and it goes slowly, and it always has.
    >>  ............................................
    pt  Nenhuma coisa só. Acumula devagar e passa devagar, e sempre foi assim.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It'll ease. It has every other time.
    >>  ............................................
    pt  O comum. Vai aliviar. Aliviou todas as outras vezes.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list.
    >>  ............................................
    pt  Nada que caiba numa lista.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. That's all.
    >>  ............................................
    pt  O comum. Só isso.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. The weight of the ordinary. It shifts eventually.
    >>  ............................................
    pt  Nada que caiba numa lista. O peso do comum. Muda uma hora.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. It builds slowly and it goes slowly, and it always has.
    >>  ............................................
    pt  Nenhuma coisa só. Acumula devagar e passa devagar, e sempre foi assim.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It'll ease. It has every other time.
    >>  ............................................
    pt  O comum. Vai aliviar. Aliviou todas as outras vezes.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list! It's the weight of the ordinary, which is a dreadful phrase and accurate.
    >>  ............................................
    pt  Nada que caiba numa lista! É o peso do comum, uma frase horrível e exata.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones. Forty small ones is somehow worse than one large one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas. Quarenta pequenas é pior que uma grande.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary, mostly. Very undramatic. I'd prefer a dragon, frankly.
    >>  ............................................
    pt  O comum, principalmente. Nada dramático. Francamente, eu preferia um dragão.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list! It's the weight of the ordinary, which is a dreadful phrase and accurate.
    >>  ............................................
    pt  Nada que caiba numa lista! É o peso do comum, uma frase horrível e exata.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones. Forty small ones is somehow worse than one large one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas. Quarenta pequenas é pior que uma grande.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary, mostly. Very undramatic. I'd prefer a dragon, frankly.
    >>  ............................................
    pt  O comum, principalmente. Nada dramático. Francamente, eu preferia um dragão.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. The weight of the ordinary. It shifts eventually.
    >>  ............................................
    pt  Nada que caiba numa lista. O peso do comum. Muda uma hora.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. It builds slowly and it goes slowly, and it always has.
    >>  ............................................
    pt  Nenhuma coisa só. Acumula devagar e passa devagar, e sempre foi assim.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. It'll ease. It has every other time.
    >>  ............................................
    pt  O comum. Vai aliviar. Aliviou todas as outras vezes.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list. That's what makes it hard to say out loud, %1$s.
    >>  ............................................
    pt  Nada que caiba numa lista. É isso que dificulta dizer em voz alta, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. If there were one thing I could carry it. It's the forty.
    >>  ............................................
    pt  Nenhuma coisa só. Se fosse uma coisa eu carregaria. São as quarenta.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. And the fact that ordinary is supposed to be the easy part.
    >>  ............................................
    pt  O comum. E o fato de que o comum devia ser a parte fácil.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list.
    >>  ............................................
    pt  Nada que caiba numa lista.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary. That's all.
    >>  ............................................
    pt  O comum. Só isso.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list! It's the weight of the ordinary, which is a dreadful phrase and accurate.
    >>  ............................................
    pt  Nada que caiba numa lista! É o peso do comum, uma frase horrível e exata.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones. Forty small ones is somehow worse than one large one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas. Quarenta pequenas é pior que uma grande.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary, mostly. Very undramatic. I'd prefer a dragon, frankly.
    >>  ............................................
    pt  O comum, principalmente. Nada dramático. Francamente, eu preferia um dragão.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.ask/1
    en  Nothing you could put on a list! It's the weight of the ordinary, which is a dreadful phrase and accurate.
    >>  ............................................
    pt  Nada que caiba numa lista! É o peso do comum, uma frase horrível e exata.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.ask/2
    en  No one thing. Forty small ones. Forty small ones is somehow worse than one large one.
    >>  ............................................
    pt  Nenhuma coisa só. Quarenta pequenas. Quarenta pequenas é pior que uma grande.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough.ask/3
    en  The ordinary, mostly. Very undramatic. I'd prefer a dragon, frankly.
    >>  ............................................
    pt  O comum, principalmente. Nada dramático. Francamente, eu preferia um dragão.
    >>  ............................................
```

</details>


### Button `dismiss` — "You'll be fine. You always are."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `checkin.rough.to.checkin.rough`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.rough.dismiss` — accepted phrasings: "you will be fine"; "you always are"; "you will live"; "you will survive"
  - the message must contain one of: `fine`, `always`, `survive`, `live`
  - scored words: `fine`(1.2), `always`(1.2), `survive`(1.5), `live`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.respond.dismiss   [31 chars]
    en  You'll be fine. You always are.
    >>  ............................................
    pt  Você vai ficar bem. Sempre fica.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `checkin.rough.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +4  _(recorded under topic `checkin.rough.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin.rough.dismissed.followup`
- …where the player's next choices will be: "That was thin of me. Sorry." | "Let me ask again, properly." | "Fair. I'll not push." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.dismiss
WHO    VILLAGER — what the player reads after pressing "You'll be fine. You always are."
       spoken on: conversations.topic.checkin.rough.respond, button `dismiss`
       leaves the player on: conversations.topic.checkin.rough.dismissed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.brushed_off`: the villager resists. Subject `checkin.hard_stretch`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   this is the line that establishes `state:rough`, `player:brushed_off` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.rough.dismiss/1   [53 chars]
    en  ...I will, yes. Thank you for the vote of confidence.
    >>  ............................................
    pt  ...Vou ficar, sim. Obrigado pelo voto de confiança.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismiss/2   [43 chars]
    en  Always am. That's rather the problem, %1$s.
    >>  ............................................
    pt  Sempre fico. É justamente esse o problema, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.rough.dismiss/3   [58 chars]
    en  Mm. Suppose asking was the polite part and you've done it.
    >>  ............................................
    pt  Hm. Acho que perguntar era a parte educada e você já fez.
    >>  ............................................
```


### Button `leave` — "I'll not keep you."

*stance family `exit` · tone `plain` · answers the beat(s) `checkin.rough.to.checkin.rough` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.rough.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.rough.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.rough.respond.leave   [18 chars]
    en  I'll not keep you.
    >>  ............................................
    pt  Não vou te prender.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.rough.leave
WHO    VILLAGER — what the player reads after pressing "I'll not keep you."
       spoken on: conversations.topic.checkin.rough.respond, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.leave`: the villager accepts. Subject `checkin.hard_stretch`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.checkin.rough.followup / leave
```

> Written out in full under **`conversations.topic.checkin.rough.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.checkin.smitten.followup`

**Reached from 1 route(s):** `conversations.topic.checkin.good.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.ask_more.smitten` — e.g. "...More. Right. I've been trying to think of a reason to keep you here and you've handed me one."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.smitten.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.smitten.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.smitten.followup   [25 chars]
    en  ...Ask me anything, then.
    >>  ............................................
    pt  ...Então pode me perguntar qualquer coisa.
    >>  ............................................
```


### Button `take_it_up` — "Go on, then. The long version."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `checkin.smitten.offered` · offered only once the villager has actually said `villager:smitten`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.smitten.long_version` — accepted phrasings: "go on then the long version"; "give me the long version"; "i want the long version"
  - the message must contain one of: `version`, `long`
  - scored words: `version`(1.5), `long`(1.2), `go`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.smitten.followup.take_it_up
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.smitten.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.smitten.followup.take_it_up   [30 chars]
    en  Go on, then. The long version.
    >>  ............................................
    pt  Então vai. A versão longa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `checkin.smitten.long_version`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.smitten.long_version
WHO    VILLAGER — what the player reads after pressing "Go on, then. The long version."
       spoken on: conversations.topic.checkin.smitten.followup, button `take_it_up`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.smitten.long_version`: the villager discloses. Subject `checkin.affection`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.smitten.long_version/1   [72 chars]
    en  Very well. It began badly, improved without permission, and here we are.
    >>  ............................................
    pt  Muito bem. Começou mal, melhorou sem pedir licença, e aqui estamos.
    >>  ............................................
  dialogue.conversations.checkin.smitten.long_version/2   [53 chars]
    en  You'll regret asking. I've been saving this up, %1$s.
    >>  ............................................
    pt  Você vai se arrepender de perguntar. Eu estava guardando isso, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.smitten.long_version/3   [62 chars]
    en  The long version needs sitting down for. Are you sitting down?
    >>  ............................................
    pt  A versão longa precisa que você sente. Você está sentado?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are. I've said that carefully, %1$s.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos. Eu disse isso com cuidado, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you and I'd been rather attached to being right.
    >>  ............................................
    pt  Eu errei sobre você e eu era bem apegado a estar certo.
    >>  ............................................
  anxious.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. I have thought about the exact afternoon it stopped being nothing.
    >>  ............................................
    pt  Começou como nada. Eu pensei na tarde exata em que deixou de ser nada.
    >>  ............................................
  athletic.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly and improved slowly, which is how most good things go.
    >>  ............................................
    pt  Começou mal e melhorou devagar, que é como quase tudo de bom vai.
    >>  ............................................
  athletic.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. It took a couple of years to find that out and I'm in no hurry to finish it.
    >>  ............................................
    pt  Eu errei sobre você. Levou uns anos pra descobrir e eu não tenho pressa de terminar.
    >>  ............................................
  athletic.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. These things take the time they take and this one has taken a while.
    >>  ............................................
    pt  Começou como nada. Essas coisas levam o tempo que levam e essa levou um tempo.
    >>  ............................................
  confident.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos.
    >>  ............................................
  confident.dialogue.conversations.checkin.smitten.long_version/2
    en  Short version: I was wrong about you twice and I've stopped keeping score.
    >>  ............................................
    pt  Versão curta: eu errei sobre você duas vezes e parei de contar.
    >>  ............................................
  confident.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it has not stayed nothing. That's the whole account.
    >>  ............................................
    pt  Começou como nada e não continuou nada. É todo o relato.
    >>  ............................................
  crabby.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos.
    >>  ............................................
  crabby.dialogue.conversations.checkin.smitten.long_version/2
    en  Short version: I was wrong about you twice and I've stopped keeping score.
    >>  ............................................
    pt  Versão curta: eu errei sobre você duas vezes e parei de contar.
    >>  ............................................
  crabby.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it has not stayed nothing. That's the whole account.
    >>  ............................................
    pt  Começou como nada e não continuou nada. É todo o relato.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are, %1$s.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.smitten.long_version/2
    en  I'll tell it properly if you sit down. It's a better story than either of us behaved in it.
    >>  ............................................
    pt  Eu conto direito se você sentar. É uma história melhor do que nós dois nos comportamos nela.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and I'd rather it hadn't stayed nothing, and it hasn't.
    >>  ............................................
    pt  Começou como nada e eu preferia que não continuasse nada, e não continuou.
    >>  ............................................
  flirty.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are, %1$s.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.checkin.smitten.long_version/2
    en  I'll tell it properly if you sit down. It's a better story than either of us behaved in it.
    >>  ............................................
    pt  Eu conto direito se você sentar. É uma história melhor do que nós dois nos comportamos nela.
    >>  ............................................
  flirty.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and I'd rather it hadn't stayed nothing, and it hasn't.
    >>  ............................................
    pt  Começou como nada e eu preferia que não continuasse nada, e não continuou.
    >>  ............................................
  friendly.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are, %1$s.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.checkin.smitten.long_version/2
    en  I'll tell it properly if you sit down. It's a better story than either of us behaved in it.
    >>  ............................................
    pt  Eu conto direito se você sentar. É uma história melhor do que nós dois nos comportamos nela.
    >>  ............................................
  friendly.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and I'd rather it hadn't stayed nothing, and it hasn't.
    >>  ............................................
    pt  Começou como nada e eu preferia que não continuasse nada, e não continuou.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are. I've said that carefully, %1$s.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos. Eu disse isso com cuidado, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you and I'd been rather attached to being right.
    >>  ............................................
    pt  Eu errei sobre você e eu era bem apegado a estar certo.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. I have thought about the exact afternoon it stopped being nothing.
    >>  ............................................
    pt  Começou como nada. Eu pensei na tarde exata em que deixou de ser nada.
    >>  ............................................
  greedy.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos.
    >>  ............................................
  greedy.dialogue.conversations.checkin.smitten.long_version/2
    en  Short version: I was wrong about you twice and I've stopped keeping score.
    >>  ............................................
    pt  Versão curta: eu errei sobre você duas vezes e parei de contar.
    >>  ............................................
  greedy.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it has not stayed nothing. That's the whole account.
    >>  ............................................
    pt  Começou como nada e não continuou nada. É todo o relato.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.smitten.long_version/2
    en  Short version: I was wrong about you twice and I've stopped keeping score.
    >>  ............................................
    pt  Versão curta: eu errei sobre você duas vezes e parei de contar.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it has not stayed nothing. That's the whole account.
    >>  ............................................
    pt  Começou como nada e não continuou nada. É todo o relato.
    >>  ............................................
  introverted.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly. It improved. Here we are.
    >>  ............................................
    pt  Começou mal. Melhorou. Aqui estamos.
    >>  ............................................
  introverted.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. Twice. That's the story.
    >>  ............................................
    pt  Eu errei sobre você. Duas vezes. É essa a história.
    >>  ............................................
  introverted.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it isn't nothing now.
    >>  ............................................
    pt  Começou como nada e agora não é nada.
    >>  ............................................
  lazy.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly and improved slowly, which is how most good things go.
    >>  ............................................
    pt  Começou mal e melhorou devagar, que é como quase tudo de bom vai.
    >>  ............................................
  lazy.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. It took a couple of years to find that out and I'm in no hurry to finish it.
    >>  ............................................
    pt  Eu errei sobre você. Levou uns anos pra descobrir e eu não tenho pressa de terminar.
    >>  ............................................
  lazy.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. These things take the time they take and this one has taken a while.
    >>  ............................................
    pt  Começou como nada. Essas coisas levam o tempo que levam e essa levou um tempo.
    >>  ............................................
  odd.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly. It improved. Here we are.
    >>  ............................................
    pt  Começou mal. Melhorou. Aqui estamos.
    >>  ............................................
  odd.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. Twice. That's the story.
    >>  ............................................
    pt  Eu errei sobre você. Duas vezes. É essa a história.
    >>  ............................................
  odd.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it isn't nothing now.
    >>  ............................................
    pt  Começou como nada e agora não é nada.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly and improved slowly, which is how most good things go.
    >>  ............................................
    pt  Começou mal e melhorou devagar, que é como quase tudo de bom vai.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. It took a couple of years to find that out and I'm in no hurry to finish it.
    >>  ............................................
    pt  Eu errei sobre você. Levou uns anos pra descobrir e eu não tenho pressa de terminar.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. These things take the time they take and this one has taken a while.
    >>  ............................................
    pt  Começou como nada. Essas coisas levam o tempo que levam e essa levou um tempo.
    >>  ............................................
  peppy.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are! Nobody consulted me at any point.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos! Ninguém me consultou em momento nenhum.
    >>  ............................................
  peppy.dialogue.conversations.checkin.smitten.long_version/2
    en  I was entirely wrong about you and I've decided to enjoy being wrong about it.
    >>  ............................................
    pt  Eu errei completamente sobre você e decidi curtir estar errado.
    >>  ............................................
  peppy.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. Nothing has been extremely persistent since.
    >>  ............................................
    pt  Começou como nada. Nada tem sido extremamente persistente desde então.
    >>  ............................................
  playful.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are! Nobody consulted me at any point.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos! Ninguém me consultou em momento nenhum.
    >>  ............................................
  playful.dialogue.conversations.checkin.smitten.long_version/2
    en  I was entirely wrong about you and I've decided to enjoy being wrong about it.
    >>  ............................................
    pt  Eu errei completamente sobre você e decidi curtir estar errado.
    >>  ............................................
  playful.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. Nothing has been extremely persistent since.
    >>  ............................................
    pt  Começou como nada. Nada tem sido extremamente persistente desde então.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly and improved slowly, which is how most good things go.
    >>  ............................................
    pt  Começou mal e melhorou devagar, que é como quase tudo de bom vai.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. It took a couple of years to find that out and I'm in no hurry to finish it.
    >>  ............................................
    pt  Eu errei sobre você. Levou uns anos pra descobrir e eu não tenho pressa de terminar.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. These things take the time they take and this one has taken a while.
    >>  ............................................
    pt  Começou como nada. Essas coisas levam o tempo que levam e essa levou um tempo.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are. I've said that carefully, %1$s.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos. Eu disse isso com cuidado, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you and I'd been rather attached to being right.
    >>  ............................................
    pt  Eu errei sobre você e eu era bem apegado a estar certo.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. I have thought about the exact afternoon it stopped being nothing.
    >>  ............................................
    pt  Começou como nada. Eu pensei na tarde exata em que deixou de ser nada.
    >>  ............................................
  shy.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly. It improved. Here we are.
    >>  ............................................
    pt  Começou mal. Melhorou. Aqui estamos.
    >>  ............................................
  shy.dialogue.conversations.checkin.smitten.long_version/2
    en  I was wrong about you. Twice. That's the story.
    >>  ............................................
    pt  Eu errei sobre você. Duas vezes. É essa a história.
    >>  ............................................
  shy.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing and it isn't nothing now.
    >>  ............................................
    pt  Começou como nada e agora não é nada.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are! Nobody consulted me at any point.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos! Ninguém me consultou em momento nenhum.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.smitten.long_version/2
    en  I was entirely wrong about you and I've decided to enjoy being wrong about it.
    >>  ............................................
    pt  Eu errei completamente sobre você e decidi curtir estar errado.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. Nothing has been extremely persistent since.
    >>  ............................................
    pt  Começou como nada. Nada tem sido extremamente persistente desde então.
    >>  ............................................
  witty.dialogue.conversations.checkin.smitten.long_version/1
    en  It began badly, improved without permission, and here we are! Nobody consulted me at any point.
    >>  ............................................
    pt  Começou mal, melhorou sem permissão, e aqui estamos! Ninguém me consultou em momento nenhum.
    >>  ............................................
  witty.dialogue.conversations.checkin.smitten.long_version/2
    en  I was entirely wrong about you and I've decided to enjoy being wrong about it.
    >>  ............................................
    pt  Eu errei completamente sobre você e decidi curtir estar errado.
    >>  ............................................
  witty.dialogue.conversations.checkin.smitten.long_version/3
    en  It started as nothing. Nothing has been extremely persistent since.
    >>  ............................................
    pt  Começou como nada. Nada tem sido extremamente persistente desde então.
    >>  ............................................
```

</details>


### Button `return_warmth` — "I'd listen to you for an hour."

*stance family `flirtation` · tone `intimate` · outcome `appreciated` · answers the beat(s) `checkin.smitten.offered` · offered only once the villager has actually said `villager:smitten`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.smitten.return_warmth` — accepted phrasings: "i would listen to you for an hour"; "i would gladly listen"; "i could listen to you all day"
  - the message must contain one of: `listen`, `hour`, `gladly`
  - scored words: `listen`(1.5), `hour`(1.2), `gladly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.smitten.followup.return_warmth
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.smitten.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.smitten.followup.return_warmth   [30 chars]
    en  I'd listen to you for an hour.
    >>  ............................................
    pt  Eu te ouviria por uma hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.smitten.return_warmth`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — attraction +4, warmth +2  _(recorded under topic `checkin.smitten.return_warmth`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.smitten.return_warmth
WHO    VILLAGER — what the player reads after pressing "I'd listen to you for an hour."
       spoken on: conversations.topic.checkin.smitten.followup, button `return_warmth`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.smitten.return_warmth`: the villager accepts. Subject `checkin.affection`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.smitten.return_warmth/1   [67 chars]
    en  An hour. ...Right. I'm going to need a moment before I start, then.
    >>  ............................................
    pt  Uma hora. ...Certo. Então vou precisar de um momento antes de começar.
    >>  ............................................
  dialogue.conversations.checkin.smitten.return_warmth/2   [41 chars]
    en  Careful, %1$s. I'll hold you to the hour.
    >>  ............................................
    pt  Cuidado, %1$s. Vou cobrar a hora inteira.
    >>  ............................................
  dialogue.conversations.checkin.smitten.return_warmth/3   [73 chars]
    en  An hour of being listened to. I'll spend it badly and enjoy every minute.
    >>  ............................................
    pt  Uma hora sendo ouvido. Vou gastar mal e adorar cada minuto.
    >>  ............................................
```


### Button `stay_light` — "Let's keep to the weather for now."

*stance family `restraint` · tone `gentle` · outcome `accepted` · answers the beat(s) `checkin.smitten.offered`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.smitten.stay_light` — accepted phrasings: "let us keep to the weather for now"; "let us keep it lighter"; "something lighter, maybe"
  - the message must contain one of: `weather`, `lighter`
  - scored words: `weather`(1.5), `lighter`(1.2), `keep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.smitten.followup.stay_light
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.smitten.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.smitten.followup.stay_light   [34 chars]
    en  Let's keep to the weather for now.
    >>  ............................................
    pt  Vamos ficar no tempo por enquanto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — attraction -1, respect +3  _(recorded under topic `checkin.smitten.stay_light`)_
- Does: session `turn`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.smitten.stay_light
WHO    VILLAGER — what the player reads after pressing "Let's keep to the weather for now."
       spoken on: conversations.topic.checkin.smitten.followup, button `stay_light`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.smitten.stay_light`: the villager accepts. Subject `checkin.affection`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.smitten.stay_light/1   [48 chars]
    en  ...The weather. Right. It's fine, since you ask.
    >>  ............................................
    pt  ...O tempo. Certo. Está bom, já que você pergunta.
    >>  ............................................
  dialogue.conversations.checkin.smitten.stay_light/2   [43 chars]
    en  Understood. I'll not make it awkward, %1$s.
    >>  ............................................
    pt  Entendido. Não vou deixar estranho, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.smitten.stay_light/3   [58 chars]
    en  Fair. The offer keeps better than the moment does, anyway.
    >>  ............................................
    pt  Justo. A oferta se guarda melhor que o momento, de qualquer forma.
    >>  ............................................
```


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin.smitten.offered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.smitten.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.smitten.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.smitten.followup.leave   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Fica pra outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.smitten.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.topic.checkin.smitten.followup, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.smitten.leave`: the villager accepts. Subject `checkin.affection`, polarity `mixed`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin.smitten.leave/1   [21 chars]
    en  ...Aye. Another time.
    >>  ............................................
    pt  ...É. Fica pra outra.
    >>  ............................................
  dialogue.conversations.checkin.smitten.leave/2   [28 chars]
    en  I'll hold you to that, %1$s.
    >>  ............................................
    pt  Vou cobrar isso, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.smitten.leave/3   [11 chars]
    en  So you are.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
```

---


## `conversations.topic.checkin.toddler.respond`

**Reached from 1 route(s):** `greet` / `checkin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.toddler` — e.g. "I'm good! I found a bug this morning. It wiggled."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.toddler.respond   [16 chars]
    en  That's how I am.
    >>  ............................................
    pt  É assim que eu estou.
    >>  ............................................
```


### Button `delight` — "That is a very good report."

*stance family `encouragement` · tone `playful` · answers the beat(s) `checkin.toddler.to.checkin.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.toddler.delight` — accepted phrasings: "that is a very good report"; "a very good report"; "good report"
  - the message must contain one of: `report`, `good`
  - scored words: `report`(1.5), `good`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.toddler.respond.delight   [27 chars]
    en  That is a very good report.
    >>  ............................................
    pt  Esse é um relatório muito bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.toddler.delight`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `checkin.toddler.delight`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.checkin.toddler.delight
WHO    VILLAGER — what the player reads after pressing "That is a very good report."
       spoken on: conversations.topic.checkin.toddler.respond, button `delight`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.toddler.delight.terminal`: the villager celebrates. Subject `checkin.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.toddler.delight/1   [38 chars]
    en  It IS a good report. I did a good one.
    >>  ............................................
    pt  É um relatório bom MESMO. Eu fiz um bom.
    >>  ............................................
  dialogue.conversations.checkin.toddler.delight/2   [67 chars]
    en  A REPORT. Like a proper one. I'm going to tell Mama I did a report.
    >>  ............................................
    pt  Um RELATÓRIO. Tipo de verdade. Vou contar pra Mamãe que eu fiz um relatório.
    >>  ............................................
  dialogue.conversations.checkin.toddler.delight/3   [59 chars]
    en  Good report! Thank you, %1$s. I'll do another one tomorrow.
    >>  ............................................
    pt  Relatório bom! Obrigado, %1$s. Amanhã eu faço outro.
    >>  ............................................
```


### Button `ask` — "Tell me the best bit."

*stance family `curiosity` · tone `plain` · answers the beat(s) `checkin.toddler.to.checkin.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.toddler.ask` — accepted phrasings: "tell me the best bit"; "what was the best bit"; "the best bit please"
  - the message must contain one of: `best`, `bit`
  - scored words: `best`(1.5), `bit`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.toddler.respond.ask   [21 chars]
    en  Tell me the best bit.
    >>  ............................................
    pt  Me conta a melhor parte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `checkin.toddler.ask`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.checkin.toddler.ask
WHO    VILLAGER — what the player reads after pressing "Tell me the best bit."
       spoken on: conversations.topic.checkin.toddler.respond, button `ask`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.toddler.ask.terminal`: the villager asks. Subject `checkin.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.toddler.ask/1   [62 chars]
    en  The best bit was the bit at the start. Before everything else.
    >>  ............................................
    pt  A melhor parte foi a do começo. Antes de tudo o resto.
    >>  ............................................
  dialogue.conversations.checkin.toddler.ask/2   [57 chars]
    en  There were LOTS of best bits. That's why it's a good day.
    >>  ............................................
    pt  Teve MUITAS melhores partes. Por isso é um dia bom.
    >>  ............................................
  dialogue.conversations.checkin.toddler.ask/3   [66 chars]
    en  The bit nobody saw. That one's mine. ...Okay, I'll tell you later.
    >>  ............................................
    pt  A parte que ninguém viu. Essa é minha. ...Tá, eu te conto depois.
    >>  ............................................
```


### Button `leave` — "Off you go, then."

*stance family `exit` · tone `plain` · answers the beat(s) `checkin.toddler.to.checkin.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.toddler.respond.leave   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.checkin.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go, then."
       spoken on: conversations.topic.checkin.toddler.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.toddler.leave.terminal`: the villager accepts. Subject `checkin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.toddler.leave/1   [21 chars]
    en  Bye! I've got things.
    >>  ............................................
    pt  Tchau! Tenho coisas.
    >>  ............................................
  dialogue.conversations.checkin.toddler.leave/2   [15 chars]
    en  Okay bye, %1$s!
    >>  ............................................
    pt  Tá, tchau, %1$s!
    >>  ............................................
  dialogue.conversations.checkin.toddler.leave/3   [49 chars]
    en  Bye bye. Ask me again tomorrow, there'll be more.
    >>  ............................................
    pt  Tchau tchau. Pergunta de novo amanhã, vai ter mais.
    >>  ............................................
```

---


## `conversations.topic.checkin.young.respond`

**Reached from 2 route(s):** `greet` / `checkin`; `greet` / `checkin`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.child` — e.g. "Good! Mostly good. The goat and I have a truce now."
- `conversations.checkin.teen` — e.g. "Fine, I guess. Same as yesterday."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin.young.respond   [21 chars]
    en  That's how I've been!
    >>  ............................................
    pt  É assim que eu tenho estado!
    >>  ............................................
```


### Button `interested` — "Go on, tell me the whole thing."

*stance family `curiosity` · tone `plain` · answers the beat(s) `checkin.child.to.checkin.young`, `checkin.teen.to.checkin.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.young.interested` — accepted phrasings: "tell me the whole thing"; "tell me everything"; "go on then tell me"
  - the message must contain one of: `whole`, `everything`, `tell`
  - scored words: `whole`(1.5), `everything`(1.2), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.young.respond.interested
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.young.respond.interested   [31 chars]
    en  Go on, tell me the whole thing.
    >>  ............................................
    pt  Vai, me conta tudo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.young.interested`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `checkin.young.interested`)_
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.young.interested
WHO    VILLAGER — what the player reads after pressing "Go on, tell me the whole thing."
       spoken on: conversations.topic.checkin.young.respond, button `interested`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.young.interested.terminal`: the villager accepts. Subject `checkin.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.young.interested/1   [33 chars]
    en  The WHOLE thing? Right. Sit down.
    >>  ............................................
    pt  TUDO? Certo. Senta.
    >>  ............................................
  dialogue.conversations.checkin.young.interested/2   [48 chars]
    en  Nobody ever asks for the whole thing! Okay, so —
    >>  ............................................
    pt  Ninguém nunca pede tudo! Tá, então —
    >>  ............................................
  dialogue.conversations.checkin.young.interested/3   [45 chars]
    en  You're going to regret that. It's a long one.
    >>  ............................................
    pt  Você vai se arrepender. É longa.
    >>  ............................................
```


### Button `encourage` — "You're doing alright, you know."

*stance family `encouragement` · tone `plain` · answers the beat(s) `checkin.child.to.checkin.young`, `checkin.teen.to.checkin.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.young.encourage` — accepted phrasings: "you are doing alright"; "i am proud of you"; "you are doing well"
  - the message must contain one of: `alright`, `proud`, `well`, `doing`
  - scored words: `doing`(1.0), `alright`(1.2), `proud`(1.5), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.young.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.young.respond.encourage   [31 chars]
    en  You're doing alright, you know.
    >>  ............................................
    pt  Você está indo bem, sabia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin.young.encourage`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `checkin.young.encourage`)_
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.young.encourage
WHO    VILLAGER — what the player reads after pressing "You're doing alright, you know."
       spoken on: conversations.topic.checkin.young.respond, button `encourage`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.young.encourage.terminal`: the villager accepts. Subject `checkin.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.young.encourage/1   [75 chars]
    en  ...Am I? Don't say it in front of the others. ...But say it again sometime.
    >>  ............................................
    pt  ...Sou? Não fala isso na frente dos outros. ...Mas fala de novo qualquer hora.
    >>  ............................................
  dialogue.conversations.checkin.young.encourage/2   [34 chars]
    en  I am, aren't I! Mostly. Some days.
    >>  ............................................
    pt  Estou, né! Na maioria das vezes. Em alguns dias.
    >>  ............................................
  dialogue.conversations.checkin.young.encourage/3   [42 chars]
    en  That's a good thing to hear before chores.
    >>  ............................................
    pt  É bom ouvir isso antes das tarefas.
    >>  ............................................
```


### Button `dismiss` — "That's not much of an answer."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `checkin.child.to.checkin.young`, `checkin.teen.to.checkin.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin.young.dismiss` — accepted phrasings: "that is not much of an answer"; "hardly an answer"; "that is not much"
  - the message must contain one of: `answer`, `hardly`, `much`
  - scored words: `answer`(1.5), `much`(1.0), `hardly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.young.respond.dismiss   [29 chars]
    en  That's not much of an answer.
    >>  ............................................
    pt  Isso não é bem uma resposta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `checkin.young.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `checkin.young.dismiss`)_
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.young.dismiss
WHO    VILLAGER — what the player reads after pressing "That's not much of an answer."
       spoken on: conversations.topic.checkin.young.respond, button `dismiss`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.young.dismiss.terminal`: the villager dismisss. Subject `checkin.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.young.dismiss/1   [21 chars]
    en  ...You asked, though.
    >>  ............................................
    pt  Mas você perguntou.
    >>  ............................................
  dialogue.conversations.checkin.young.dismiss/2   [21 chars]
    en  Fine. Forget it then.
    >>  ............................................
    pt  Tá. Esquece então.
    >>  ............................................
  dialogue.conversations.checkin.young.dismiss/3   [28 chars]
    en  What did you want, a speech?
    >>  ............................................
    pt  O que você queria, um discurso?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though. I thought that meant it was all right to say.
    >>  ............................................
    pt  ...Mas você perguntou. Achei que significava que dava pra falar.
    >>  ............................................
  anxious.dialogue.conversations.checkin.young.dismiss/2
    en  I only told you because you asked. I won't again.
    >>  ............................................
    pt  Eu só contei porque você perguntou. Não conto de novo.
    >>  ............................................
  anxious.dialogue.conversations.checkin.young.dismiss/3
    en  ...Sorry. I thought you wanted to hear it.
    >>  ............................................
    pt  ...Desculpe. Achei que você quisesse ouvir.
    >>  ............................................
  athletic.dialogue.conversations.checkin.young.dismiss/1
    en  ...You did ask, mind.
    >>  ............................................
    pt  ...Mas você perguntou, veja bem.
    >>  ............................................
  athletic.dialogue.conversations.checkin.young.dismiss/2
    en  All right. Ask me a different day, then.
    >>  ............................................
    pt  Está bem. Me pergunte outro dia, então.
    >>  ............................................
  athletic.dialogue.conversations.checkin.young.dismiss/3
    en  ...Fair enough. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  confident.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though.
    >>  ............................................
    pt  ...Mas você perguntou.
    >>  ............................................
  confident.dialogue.conversations.checkin.young.dismiss/2
    en  You asked me and now it's silly. All right.
    >>  ............................................
    pt  Você me perguntou e agora é bobo. Está bem.
    >>  ............................................
  confident.dialogue.conversations.checkin.young.dismiss/3
    en  ...Then don't ask next time.
    >>  ............................................
    pt  ...Então não pergunte da próxima.
    >>  ............................................
  crabby.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though.
    >>  ............................................
    pt  ...Mas você perguntou.
    >>  ............................................
  crabby.dialogue.conversations.checkin.young.dismiss/2
    en  You asked me and now it's silly. All right.
    >>  ............................................
    pt  Você me perguntou e agora é bobo. Está bem.
    >>  ............................................
  crabby.dialogue.conversations.checkin.young.dismiss/3
    en  ...Then don't ask next time.
    >>  ............................................
    pt  ...Então não pergunte da próxima.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.young.dismiss/1
    en  ...But you asked me, %1$s. I thought you wanted to know.
    >>  ............................................
    pt  ...Mas você me perguntou, %1$s. Achei que quisesse saber.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.young.dismiss/2
    en  You asked. I'd have kept it to myself otherwise, honestly.
    >>  ............................................
    pt  Você perguntou. Senão eu teria guardado pra mim, sinceramente.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.young.dismiss/3
    en  ...I only said it because it was you who asked.
    >>  ............................................
    pt  ...Eu só falei porque foi você que perguntou.
    >>  ............................................
  flirty.dialogue.conversations.checkin.young.dismiss/1
    en  ...But you asked me, %1$s. I thought you wanted to know.
    >>  ............................................
    pt  ...Mas você me perguntou, %1$s. Achei que quisesse saber.
    >>  ............................................
  flirty.dialogue.conversations.checkin.young.dismiss/2
    en  You asked. I'd have kept it to myself otherwise, honestly.
    >>  ............................................
    pt  Você perguntou. Senão eu teria guardado pra mim, sinceramente.
    >>  ............................................
  flirty.dialogue.conversations.checkin.young.dismiss/3
    en  ...I only said it because it was you who asked.
    >>  ............................................
    pt  ...Eu só falei porque foi você que perguntou.
    >>  ............................................
  friendly.dialogue.conversations.checkin.young.dismiss/1
    en  ...But you asked me, %1$s. I thought you wanted to know.
    >>  ............................................
    pt  ...Mas você me perguntou, %1$s. Achei que quisesse saber.
    >>  ............................................
  friendly.dialogue.conversations.checkin.young.dismiss/2
    en  You asked. I'd have kept it to myself otherwise, honestly.
    >>  ............................................
    pt  Você perguntou. Senão eu teria guardado pra mim, sinceramente.
    >>  ............................................
  friendly.dialogue.conversations.checkin.young.dismiss/3
    en  ...I only said it because it was you who asked.
    >>  ............................................
    pt  ...Eu só falei porque foi você que perguntou.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though. I thought that meant it was all right to say.
    >>  ............................................
    pt  ...Mas você perguntou. Achei que significava que dava pra falar.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.young.dismiss/2
    en  I only told you because you asked. I won't again.
    >>  ............................................
    pt  Eu só contei porque você perguntou. Não conto de novo.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.young.dismiss/3
    en  ...Sorry. I thought you wanted to hear it.
    >>  ............................................
    pt  ...Desculpe. Achei que você quisesse ouvir.
    >>  ............................................
  greedy.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though.
    >>  ............................................
    pt  ...Mas você perguntou.
    >>  ............................................
  greedy.dialogue.conversations.checkin.young.dismiss/2
    en  You asked me and now it's silly. All right.
    >>  ............................................
    pt  Você me perguntou e agora é bobo. Está bem.
    >>  ............................................
  greedy.dialogue.conversations.checkin.young.dismiss/3
    en  ...Then don't ask next time.
    >>  ............................................
    pt  ...Então não pergunte da próxima.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though.
    >>  ............................................
    pt  ...Mas você perguntou.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.young.dismiss/2
    en  You asked me and now it's silly. All right.
    >>  ............................................
    pt  Você me perguntou e agora é bobo. Está bem.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.young.dismiss/3
    en  ...Then don't ask next time.
    >>  ............................................
    pt  ...Então não pergunte da próxima.
    >>  ............................................
  introverted.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked.
    >>  ............................................
    pt  ...Você perguntou.
    >>  ............................................
  introverted.dialogue.conversations.checkin.young.dismiss/2
    en  I wouldn't have said it otherwise.
    >>  ............................................
    pt  Eu não teria dito de outro jeito.
    >>  ............................................
  introverted.dialogue.conversations.checkin.young.dismiss/3
    en  ...Right. I'll keep the next one.
    >>  ............................................
    pt  ...Certo. Vou guardar a próxima.
    >>  ............................................
  lazy.dialogue.conversations.checkin.young.dismiss/1
    en  ...You did ask, mind.
    >>  ............................................
    pt  ...Mas você perguntou, veja bem.
    >>  ............................................
  lazy.dialogue.conversations.checkin.young.dismiss/2
    en  All right. Ask me a different day, then.
    >>  ............................................
    pt  Está bem. Me pergunte outro dia, então.
    >>  ............................................
  lazy.dialogue.conversations.checkin.young.dismiss/3
    en  ...Fair enough. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  odd.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked.
    >>  ............................................
    pt  ...Você perguntou.
    >>  ............................................
  odd.dialogue.conversations.checkin.young.dismiss/2
    en  I wouldn't have said it otherwise.
    >>  ............................................
    pt  Eu não teria dito de outro jeito.
    >>  ............................................
  odd.dialogue.conversations.checkin.young.dismiss/3
    en  ...Right. I'll keep the next one.
    >>  ............................................
    pt  ...Certo. Vou guardar a próxima.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.young.dismiss/1
    en  ...You did ask, mind.
    >>  ............................................
    pt  ...Mas você perguntou, veja bem.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.young.dismiss/2
    en  All right. Ask me a different day, then.
    >>  ............................................
    pt  Está bem. Me pergunte outro dia, então.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.young.dismiss/3
    en  ...Fair enough. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  peppy.dialogue.conversations.checkin.young.dismiss/1
    en  ...You DID ask. That's the bit I'd like on the record.
    >>  ............................................
    pt  ...Você PERGUNTOU. É a parte que eu queria registrada.
    >>  ............................................
  peppy.dialogue.conversations.checkin.young.dismiss/2
    en  Right! Well. I won't tell you the good part, then.
    >>  ............................................
    pt  Certo! Bom. Então eu não vou te contar a parte boa.
    >>  ............................................
  peppy.dialogue.conversations.checkin.young.dismiss/3
    en  ...You asked! And now I'm the silly one. Excellent.
    >>  ............................................
    pt  ...Você perguntou! E agora eu sou o bobo. Excelente.
    >>  ............................................
  playful.dialogue.conversations.checkin.young.dismiss/1
    en  ...You DID ask. That's the bit I'd like on the record.
    >>  ............................................
    pt  ...Você PERGUNTOU. É a parte que eu queria registrada.
    >>  ............................................
  playful.dialogue.conversations.checkin.young.dismiss/2
    en  Right! Well. I won't tell you the good part, then.
    >>  ............................................
    pt  Certo! Bom. Então eu não vou te contar a parte boa.
    >>  ............................................
  playful.dialogue.conversations.checkin.young.dismiss/3
    en  ...You asked! And now I'm the silly one. Excellent.
    >>  ............................................
    pt  ...Você perguntou! E agora eu sou o bobo. Excelente.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.young.dismiss/1
    en  ...You did ask, mind.
    >>  ............................................
    pt  ...Mas você perguntou, veja bem.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.young.dismiss/2
    en  All right. Ask me a different day, then.
    >>  ............................................
    pt  Está bem. Me pergunte outro dia, então.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.young.dismiss/3
    en  ...Fair enough. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked, though. I thought that meant it was all right to say.
    >>  ............................................
    pt  ...Mas você perguntou. Achei que significava que dava pra falar.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.young.dismiss/2
    en  I only told you because you asked. I won't again.
    >>  ............................................
    pt  Eu só contei porque você perguntou. Não conto de novo.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.young.dismiss/3
    en  ...Sorry. I thought you wanted to hear it.
    >>  ............................................
    pt  ...Desculpe. Achei que você quisesse ouvir.
    >>  ............................................
  shy.dialogue.conversations.checkin.young.dismiss/1
    en  ...You asked.
    >>  ............................................
    pt  ...Você perguntou.
    >>  ............................................
  shy.dialogue.conversations.checkin.young.dismiss/2
    en  I wouldn't have said it otherwise.
    >>  ............................................
    pt  Eu não teria dito de outro jeito.
    >>  ............................................
  shy.dialogue.conversations.checkin.young.dismiss/3
    en  ...Right. I'll keep the next one.
    >>  ............................................
    pt  ...Certo. Vou guardar a próxima.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.young.dismiss/1
    en  ...You DID ask. That's the bit I'd like on the record.
    >>  ............................................
    pt  ...Você PERGUNTOU. É a parte que eu queria registrada.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.young.dismiss/2
    en  Right! Well. I won't tell you the good part, then.
    >>  ............................................
    pt  Certo! Bom. Então eu não vou te contar a parte boa.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.young.dismiss/3
    en  ...You asked! And now I'm the silly one. Excellent.
    >>  ............................................
    pt  ...Você perguntou! E agora eu sou o bobo. Excelente.
    >>  ............................................
  witty.dialogue.conversations.checkin.young.dismiss/1
    en  ...You DID ask. That's the bit I'd like on the record.
    >>  ............................................
    pt  ...Você PERGUNTOU. É a parte que eu queria registrada.
    >>  ............................................
  witty.dialogue.conversations.checkin.young.dismiss/2
    en  Right! Well. I won't tell you the good part, then.
    >>  ............................................
    pt  Certo! Bom. Então eu não vou te contar a parte boa.
    >>  ............................................
  witty.dialogue.conversations.checkin.young.dismiss/3
    en  ...You asked! And now I'm the silly one. Excellent.
    >>  ............................................
    pt  ...Você perguntou! E agora eu sou o bobo. Excelente.
    >>  ............................................
```

</details>


### Button `leave` — "See you around."

*stance family `exit` · tone `plain` · answers the beat(s) `checkin.child.to.checkin.young`, `checkin.teen.to.checkin.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin.young.respond.leave   [15 chars]
    en  See you around.
    >>  ............................................
    pt  Até mais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.young.leave
WHO    VILLAGER — what the player reads after pressing "See you around."
       spoken on: conversations.topic.checkin.young.respond, button `leave`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.young.leave.terminal`: the villager accepts. Subject `checkin.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin.young.leave/1   [9 chars]
    en  Bye then!
    >>  ............................................
    pt  Tchau então!
    >>  ............................................
  dialogue.conversations.checkin.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.young.leave/3   [38 chars]
    en  Okay. Come back later, I'll have news.
    >>  ............................................
    pt  Tá. Volta depois, vou ter novidade.
    >>  ............................................
```

---

