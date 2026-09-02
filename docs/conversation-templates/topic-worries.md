# Topic: worries

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `worries` |
| Opened from | question `conversations.us`, button `worries` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.us` |
| Ages that can reach it | adult |
| Stance families it must offer | `empathy`, `curiosity`, `practical_help`, `dismissal`, `exit` |
| Narrative arc | `us`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.us`, which is written out in **topic-us.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.us.worries
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.us
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-us*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.us.worries   [28 chars]
    en  Is anything weighing on you?
    >>  ............................................
    pt  Tem alguma coisa te pesando?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.worries.followup`](#conversations-scene-worries-followup)
- [`conversations.scene.worries.the_late_one.respond`](#conversations-scene-worries-the-late-one-respond)
- [`conversations.scene.worries.the_practical_one.respond`](#conversations-scene-worries-the-practical-one-respond)
- [`conversations.topic.worries.dismissed.followup`](#conversations-topic-worries-dismissed-followup)
- [`conversations.topic.worries.followup`](#conversations-topic-worries-followup)
- [`conversations.topic.worries.heard`](#conversations-topic-worries-heard)
- [`conversations.topic.worries.information`](#conversations-topic-worries-information)
- [`conversations.topic.worries.respond`](#conversations-topic-worries-respond)

---

## `conversations.scene.worries.followup`

**Reached from 4 route(s):** `conversations.scene.worries.the_late_one.respond` / `hold_it_with_them`; `conversations.scene.worries.the_late_one.respond` / `ask_if_saying_would_help`; `conversations.scene.worries.the_practical_one.respond` / `offer_something_useful`; `conversations.scene.worries.the_practical_one.respond` / `ask_what_the_worst_is`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.worries.the_late_one.considered` — e.g. "It would help me and cost them, and that trade is the exact reason I have not made it in four years."
- `conversations.scene.worries.the_late_one.steadied` — e.g. "It is, and it is mine to carry, and there is a difference between help and company and I only ever wanted the second."
- `conversations.scene.worries.the_practical_one.answered` — e.g. "Ask me about it again on Thursday. That is genuinely the whole of it and it is the thing nobody thinks to offer."
- `conversations.scene.worries.the_practical_one.explained` — e.g. "A hard winter and asking for help, and the asking is the part I have been treating as the disaster."


```text
POOL   dialogue key: dialogue.conversations.scene.worries.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.worries.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.worries.followup   [27 chars]
    en  Anything else on your mind?
    >>  ............................................
    pt  Mais alguma coisa te preocupando?
    >>  ............................................
```


### Button `leave` — "We'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:worries.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.worries.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.followup.leave   [21 chars]
    en  We'll leave it there.
    >>  ............................................
    pt  Vamos deixar assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave it there."
       spoken on: conversations.scene.worries.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.scene.leaving`: the villager accepts. Subject `worries.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.worries.the_late_one.respond / leave; conversations.scene.worries.the_practical_one.respond / leave
```

```text
  dialogue.conversations.scene.worries.leaving/1   [23 chars]
    en  It is probably nothing.
    >>  ............................................
    pt  Provavelmente não é nada.
    >>  ............................................
  dialogue.conversations.scene.worries.leaving/2   [34 chars]
    en  Right. It will keep until morning.
    >>  ............................................
    pt  Certo. Isso espera até de manhã.
    >>  ............................................
  dialogue.conversations.scene.worries.leaving/3   [15 chars]
    en  Enough of that.
    >>  ............................................
    pt  Chega disso.
    >>  ............................................
```

---


## `conversations.scene.worries.the_late_one.respond`

**Reached from 1 route(s):** `conversations.us` / `worries`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.worries.the_late_one` — e.g. "I worry about somebody who is fine, constantly, and I have never told them and I am not going to."


```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.worries.the_late_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.worries.the_late_one.respond   [34 chars]
    en  The one you don't say in daylight.
    >>  ............................................
    pt  A que você não diz de dia.
    >>  ............................................
```


### Button `hold_it_with_them` — "That is a lot to carry by yourself."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `worries.the_late_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.worries.the_late_one.hold_it_with_them` — accepted phrasings: "that is a lot to carry by yourself"; "that is a lot to carry alone"; "carrying that by yourself is hard"
  - the message must contain one of: `carry`, `carrying`, `yourself`
  - scored words: `carry`(1.8), `carrying`(1.8), `yourself`(1.8), `lot`(0.8), `alone`(0.8), `hard`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one.respond.hold_it_with_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.the_late_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.the_late_one.respond.hold_it_with_them   [35 chars]
    en  That is a lot to carry by yourself.
    >>  ............................................
    pt  É muita coisa para carregar por conta própria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.worries.held`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +4  _(recorded under topic `worries.private`)_
- Does: session `turn`
- Then opens: `conversations.scene.worries.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one.steadied
WHO    VILLAGER — what the player reads after pressing "That is a lot to carry by yourself."
       spoken on: conversations.scene.worries.the_late_one.respond, button `hold_it_with_them`
       leaves the player on: conversations.scene.worries.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.the_late_one.open.steadied`: the villager accepts. Subject `worries.private`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:worries` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.worries.the_late_one.steadied/1   [117 chars]
    en  It is, and it is mine to carry, and there is a difference between help and company and I only ever wanted the second.
    >>  ............................................
    pt  É, e é minha para carregar, e existe diferença entre ajuda e companhia, e eu só quis a segunda.
    >>  ............................................
  dialogue.conversations.scene.worries.the_late_one.steadied/2   [131 chars]
    en  Thank you. Now two people know it exists, and the person it is about still gets to be untroubled by it, which was the whole design.
    >>  ............................................
    pt  Obrigada. Agora duas pessoas sabem que isso existe, e a pessoa de quem se trata continua sem ser incomodada, que era o desenho inteiro.
    >>  ............................................
  dialogue.conversations.scene.worries.the_late_one.steadied/3   [120 chars]
    en  I have said it once before and the person told the person. I would like it noted that I said it anyway, to you, tonight.
    >>  ............................................
    pt  Já disse uma vez antes e a pessoa contou à pessoa. Gostaria que ficasse registrado que eu disse mesmo assim, a você, hoje.
    >>  ............................................
```


### Button `ask_if_saying_would_help` — "Would telling them help?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `worries.the_late_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.worries.the_late_one.ask_if_saying_would_help` — accepted phrasings: "would telling them help"; "would telling them help"; "should they know about it"
  - the message must contain one of: `telling`, `know`
  - scored words: `telling`(1.8), `know`(1.8), `help`(0.8), `should`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one.respond.ask_if_saying_would_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.the_late_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.the_late_one.respond.ask_if_saying_would_help   [24 chars]
    en  Would telling them help?
    >>  ............................................
    pt  Contar a eles ajudaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `worries.private`)_
- Does: session `turn`
- Then opens: `conversations.scene.worries.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one.considered
WHO    VILLAGER — what the player reads after pressing "Would telling them help?"
       spoken on: conversations.scene.worries.the_late_one.respond, button `ask_if_saying_would_help`
       leaves the player on: conversations.scene.worries.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.the_late_one.open.considered`: the villager explains. Subject `worries.private`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:worries` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.worries.the_late_one.considered/1   [100 chars]
    en  It would help me and cost them, and that trade is the exact reason I have not made it in four years.
    >>  ............................................
    pt  Ajudaria a mim e custaria a eles, e é exatamente essa troca que eu não fiz em quatro anos.
    >>  ............................................
  dialogue.conversations.scene.worries.the_late_one.considered/2   [120 chars]
    en  They would be careful with me afterwards. I have watched that happen to somebody else and I would rather have the worry.
    >>  ............................................
    pt  Iam passar a ter cuidado comigo depois. Já vi isso acontecer com outra pessoa e eu prefiro ficar com a preocupação.
    >>  ............................................
  dialogue.conversations.scene.worries.the_late_one.considered/3   [108 chars]
    en  Perhaps. I have gone round that eleven times and landed in the same place, and I keep going round it anyway.
    >>  ............................................
    pt  Talvez. Já dei essa volta onze vezes e cheguei no mesmo lugar, e continuo dando a volta assim mesmo.
    >>  ............................................
```


### Button `leave` — "Thanks for saying."

*stance family `exit` · tone `plain` · answers the beat(s) `worries.the_late_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.the_late_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.the_late_one.respond.leave   [18 chars]
    en  Thanks for saying.
    >>  ............................................
    pt  Obrigado por dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for saying."
       spoken on: conversations.scene.worries.the_late_one.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.scene.leaving`: the villager accepts. Subject `worries.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.worries.followup / leave; conversations.scene.worries.the_practical_one.respond / leave
```

> Written out in full under **`conversations.scene.worries.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.worries.the_practical_one.respond`

**Reached from 1 route(s):** `conversations.us` / `worries`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.worries.the_practical_one` — e.g. "A thing that is probably fine and that I have checked four times, which tells you it is not about the thing."


```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.worries.the_practical_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.worries.the_practical_one.respond   [20 chars]
    en  What's on your mind?
    >>  ............................................
    pt  O que está te preocupando?
    >>  ............................................
```


### Button `offer_something_useful` — "Tell me what would actually help."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `worries.the_practical_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.worries.the_practical_one.offer_something_useful` — accepted phrasings: "tell me what would actually help"; "tell me what would actually help"; "what would be useful to you"
  - the message must contain one of: `help`, `useful`
  - scored words: `help`(1.8), `useful`(1.8), `tell`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one.respond.offer_something_useful
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.the_practical_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.the_practical_one.respond.offer_something_useful   [33 chars]
    en  Tell me what would actually help.
    >>  ............................................
    pt  Diga o que de fato ajudaria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.worries.offered`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, warmth +3  _(recorded under topic `worries.practical`)_
- Does: session `turn`
- Then opens: `conversations.scene.worries.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one.answered
WHO    VILLAGER — what the player reads after pressing "Tell me what would actually help."
       spoken on: conversations.scene.worries.the_practical_one.respond, button `offer_something_useful`
       leaves the player on: conversations.scene.worries.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.the_practical_one.open.answered`: the villager explains. Subject `worries.practical`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `topic:worries` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.worries.the_practical_one.answered/1   [112 chars]
    en  Ask me about it again on Thursday. That is genuinely the whole of it and it is the thing nobody thinks to offer.
    >>  ............................................
    pt  Me pergunte de novo na quinta. É genuinamente tudo, e é a coisa que ninguém pensa em oferecer.
    >>  ............................................
  dialogue.conversations.scene.worries.the_practical_one.answered/2   [99 chars]
    en  Two hours of somebody else's hands on the Saturday. Nothing else. I have costed this out carefully.
    >>  ............................................
    pt  Duas horas das mãos de outra pessoa no sábado. Nada mais. Já fiz essa conta com cuidado.
    >>  ............................................
  dialogue.conversations.scene.worries.the_practical_one.answered/3   [110 chars]
    en  You already did it by asking that instead of telling me it will be fine. It might not be fine and I know that.
    >>  ............................................
    pt  Você já ajudou perguntando isso em vez de dizer que vai ficar tudo bem. Pode não ficar, e eu sei disso.
    >>  ............................................
```


### Button `ask_what_the_worst_is` — "What's the worst case?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `worries.the_practical_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.worries.the_practical_one.ask_what_the_worst_is` — accepted phrasings: "whats the worst case"; "what is the worst case"; "how bad could it get"
  - the message must contain one of: `worst`, `bad`
  - scored words: `worst`(1.8), `bad`(1.8), `whats`(0.8), `case`(0.8), `get`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one.respond.ask_what_the_worst_is
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.the_practical_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.the_practical_one.respond.ask_what_the_worst_is   [22 chars]
    en  What's the worst case?
    >>  ............................................
    pt  Qual é o pior caso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `worries.practical`)_
- Does: session `turn`
- Then opens: `conversations.scene.worries.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one.explained
WHO    VILLAGER — what the player reads after pressing "What's the worst case?"
       spoken on: conversations.scene.worries.the_practical_one.respond, button `ask_what_the_worst_is`
       leaves the player on: conversations.scene.worries.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.the_practical_one.open.explained`: the villager explains. Subject `worries.practical`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:worries` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.worries.the_practical_one.explained/1   [99 chars]
    en  A hard winter and asking for help, and the asking is the part I have been treating as the disaster.
    >>  ............................................
    pt  Um inverno duro e pedir ajuda, e o pedir é a parte que eu venho tratando como o desastre.
    >>  ............................................
  dialogue.conversations.scene.worries.the_practical_one.explained/2   [112 chars]
    en  Said out loud it is smaller than it is at three in the morning. That happens every time and I forget every time.
    >>  ............................................
    pt  Dito em voz alta é menor do que é às três da manhã. Acontece toda vez e eu esqueço toda vez.
    >>  ............................................
  dialogue.conversations.scene.worries.the_practical_one.explained/3   [105 chars]
    en  I do not know, which is exactly why it has been running around my head instead of sitting down on a list.
    >>  ............................................
    pt  Eu não sei, que é exatamente por que isso vem correndo pela minha cabeça em vez de sentar numa lista.
    >>  ............................................
```


### Button `leave` — "Thanks for saying."

*stance family `exit` · tone `plain` · answers the beat(s) `worries.the_practical_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.worries.the_practical_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.worries.the_practical_one.respond.leave   [18 chars]
    en  Thanks for saying.
    >>  ............................................
    pt  Obrigado por dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for saying."
       spoken on: conversations.scene.worries.the_practical_one.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.scene.leaving`: the villager accepts. Subject `worries.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.worries.followup / leave; conversations.scene.worries.the_late_one.respond / leave
```

> Written out in full under **`conversations.scene.worries.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.worries.dismissed.followup`

**Reached from 1 route(s):** `conversations.topic.worries.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.worries.dismiss` — e.g. "...Nothing to fret over. Right. I'll fret quietly, then."


```text
POOL   dialogue key: dialogue.conversations.topic.worries.dismissed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.worries.dismissed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.worries.dismissed.followup   [24 chars]
    en  I'll fret quietly, then.
    >>  ............................................
    pt  Então eu me preocupo em silêncio.
    >>  ............................................
```


### Button `apologize` — "Don't. It was worth saying and I brushed it off."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `worries.dismissed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.dismissed.apologize` — accepted phrasings: "don't. it was worth saying and i brushed it off"
  - the message must contain one of: `brushed`, `worth`
  - scored words: `brushed`(1.5), `worth`(1.2), `saying`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.dismissed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.dismissed.followup.apologize   [48 chars]
    en  Don't. It was worth saying and I brushed it off.
    >>  ............................................
    pt  Não faça isso. Valia dizer e eu ignorei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `worries.dismissed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.dismissed.apologize
WHO    VILLAGER — what the player reads after pressing "Don't. It was worth saying and I brushed it off."
       spoken on: conversations.topic.worries.dismissed.followup, button `apologize`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.dismissed.apologize`: the villager qualifys. Subject `worries.burden`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.worries.dismissed.apologize/1   [56 chars]
    en  ...Then I'll say it again, and you'll not do that twice.
    >>  ............................................
    pt  ...Então eu digo de novo, e você não faz isso duas vezes.
    >>  ............................................
  dialogue.conversations.worries.dismissed.apologize/2   [66 chars]
    en  Brushed off. That's the word for it, %1$s. Thank you for using it.
    >>  ............................................
    pt  Ignorei. É a palavra certa, %1$s. Obrigado por usar.
    >>  ............................................
  dialogue.conversations.worries.dismissed.apologize/3   [69 chars]
    en  Alright. Quietly is no way to fret. It doubles the size of the thing.
    >>  ............................................
    pt  Está bem. Em silêncio não é jeito de se preocupar. Dobra o tamanho da coisa.
    >>  ............................................
```


### Button `explain` — "I meant it isn't as bad as it feels."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `worries.dismissed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.dismissed.explain` — accepted phrasings: "i meant it isn't as bad as it feels"
  - the message must contain one of: `bad`, `feels`, `meant`
  - scored words: `bad`(1.0), `feels`(1.5), `meant`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.dismissed.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.dismissed.followup.explain   [36 chars]
    en  I meant it isn't as bad as it feels.
    >>  ............................................
    pt  Eu quis dizer que não é tão ruim quanto parece.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `worries.dismissed.explain`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.dismissed.explain
WHO    VILLAGER — what the player reads after pressing "I meant it isn't as bad as it feels."
       spoken on: conversations.topic.worries.dismissed.followup, button `explain`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.dismissed.explain`: the villager qualifys. Subject `worries.burden`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.worries.dismissed.explain/1   [79 chars]
    en  ...Perhaps not. It feels how it feels regardless, and that's the bit I live in.
    >>  ............................................
    pt  ...Talvez não. Mas parece o que parece, e é nessa parte que eu moro.
    >>  ............................................
  dialogue.conversations.worries.dismissed.explain/2   [75 chars]
    en  That may even be true. It's a poor thing to say to somebody mid-fret, %1$s.
    >>  ............................................
    pt  Pode até ser verdade. É uma coisa ruim de se dizer a alguém no meio da aflição, %1$s.
    >>  ............................................
  dialogue.conversations.worries.dismissed.explain/3   [67 chars]
    en  Then sit with me while it feels bad, and be right about it quietly.
    >>  ............................................
    pt  Então fique comigo enquanto parece ruim, e esteja certo em silêncio.
    >>  ............................................
```


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `worries.dismissed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.worries.dismissed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.dismissed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.dismissed.followup.leave   [16 chars]
    en  I'll let you be.
    >>  ............................................
    pt  Vou te deixar em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.dismissed.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.worries.dismissed.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.dismissed.leave`: the villager accepts. Subject `worries.burden`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.worries.dismissed.leave/1   [4 chars]
    en  Aye.
    >>  ............................................
    pt  É.
    >>  ............................................
  dialogue.conversations.worries.dismissed.leave/2   [20 chars]
    en  Mind the road, %1$s.
    >>  ............................................
    pt  Cuidado na estrada, %1$s.
    >>  ............................................
  dialogue.conversations.worries.dismissed.leave/3   [18 chars]
    en  Mm. Quietly it is.
    >>  ............................................
    pt  Mm. Em silêncio, então.
    >>  ............................................
```

---


## `conversations.topic.worries.followup`

**Reached from 2 route(s):** `conversations.topic.worries.respond` / `validate`; `conversations.topic.worries.respond` / `ask_detail`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.worries.ask_detail` — e.g. "The rest of it. Alright — but it gets less reasonable from here."
- `conversations.us.worries.validate` — e.g. "...It is, isn't it. I'd half convinced myself it was silly."


```text
POOL   dialogue key: dialogue.conversations.topic.worries.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.worries.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.worries.followup   [30 chars]
    en  So there it is, said out loud.
    >>  ............................................
    pt  Então é isso, dito em voz alta.
    >>  ............................................
```


### Button `help_solve` — "Let's sort it together."

*stance family `practical_help` · tone `plain` · answers the beat(s) `us.worries.ask_detail.to.worries`, `us.worries.validate.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.followup.help_solve` — accepted phrasings: "let us sort it together"; "we can sort it out together"; "we will sort it out between us"
  - the message must contain one of: `sort`, `together`
  - scored words: `sort`(1.2), `together`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.followup.help_solve
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.followup.help_solve   [23 chars]
    en  Let's sort it together.
    >>  ............................................
    pt  Vamos resolver isso juntos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.worries.help_solve`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +5, trust +2  _(recorded under topic `us.worries.help_solve`)_
- Does: arc `us` — advance to stage 2
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.worries.help_solve
WHO    VILLAGER — what the player reads after pressing "Let's sort it together."
       spoken on: conversations.topic.worries.followup, button `help_solve`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.help_solve.to.us`: the villager accepts. Subject `us`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.help_solve/1   [58 chars]
    en  Together. ...That's the part I couldn't do alone. Alright.
    >>  ............................................
    pt  Juntos. ...É essa a parte que eu não conseguia sozinho. Certo.
    >>  ............................................
  dialogue.conversations.us.worries.help_solve/2   [82 chars]
    en  You'd take half of it. Then take the half with the letter in it, that one's worst.
    >>  ............................................
    pt  Você levaria metade. Então leve a metade com a carta dentro, essa é a pior.
    >>  ............................................
  dialogue.conversations.us.worries.help_solve/3   [55 chars]
    en  Right. Then it's a job, not a worry. That I can manage.
    >>  ............................................
    pt  Certo. Então é uma tarefa, não uma preocupação. Isso eu consigo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.worries.help_solve/1
    en  ...Together. That's the part I couldn't do alone, %1$s, and I'd stopped trying.
    >>  ............................................
    pt  ...Juntos. É a parte que eu não conseguia sozinho, %1$s, e eu tinha parado de tentar.
    >>  ............................................
  anxious.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us. I'd built a whole plan around having to manage by myself.
    >>  ............................................
    pt  Certo. Nós dois. Eu tinha um plano inteiro em volta de ter que me virar sozinho.
    >>  ............................................
  anxious.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. Give me a moment — I'd not expected to be offered a second pair of hands.
    >>  ............................................
    pt  Com você, então. Me dê um momento — eu não esperava um segundo par de mãos.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone, and alone was taking years.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho, e sozinho estava levando anos.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us, slowly. It'll get done and there's no hurry about when.
    >>  ............................................
    pt  Certo. Nós dois, devagar. Vai ser feito e não há pressa sobre quando.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. We'll start when we start. That's more than I had this morning.
    >>  ............................................
    pt  Com você, então. A gente começa quando começar. É mais do que eu tinha de manhã.
    >>  ............................................
  confident.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  confident.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it. That changes the size of it.
    >>  ............................................
    pt  Certo. Nós dois nisso. Isso muda o tamanho da coisa.
    >>  ............................................
  confident.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. I'd got stuck at the doing-it-alone part.
    >>  ............................................
    pt  Com você, então. Eu tinha travado na parte de fazer sozinho.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it. That changes the size of it.
    >>  ............................................
    pt  Certo. Nós dois nisso. Isso muda o tamanho da coisa.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. I'd got stuck at the doing-it-alone part.
    >>  ............................................
    pt  Com você, então. Eu tinha travado na parte de fazer sozinho.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.help_solve/1
    en  ...Together, %1$s. That's the part I couldn't do alone.
    >>  ............................................
    pt  ...Juntos, %1$s. É a parte que eu não conseguia sozinho.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us. I'd not have asked and I'm very glad you offered.
    >>  ............................................
    pt  Certo. Nós dois. Eu não teria pedido e fico muito contente que você tenha oferecido.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. Let's find the first bit and I'll not carry it by myself this time.
    >>  ............................................
    pt  Com você, então. Vamos achar a primeira parte e eu não carrego sozinho desta vez.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.help_solve/1
    en  ...Together, %1$s. That's the part I couldn't do alone.
    >>  ............................................
    pt  ...Juntos, %1$s. É a parte que eu não conseguia sozinho.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us. I'd not have asked and I'm very glad you offered.
    >>  ............................................
    pt  Certo. Nós dois. Eu não teria pedido e fico muito contente que você tenha oferecido.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. Let's find the first bit and I'll not carry it by myself this time.
    >>  ............................................
    pt  Com você, então. Vamos achar a primeira parte e eu não carrego sozinho desta vez.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.help_solve/1
    en  ...Together, %1$s. That's the part I couldn't do alone.
    >>  ............................................
    pt  ...Juntos, %1$s. É a parte que eu não conseguia sozinho.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us. I'd not have asked and I'm very glad you offered.
    >>  ............................................
    pt  Certo. Nós dois. Eu não teria pedido e fico muito contente que você tenha oferecido.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. Let's find the first bit and I'll not carry it by myself this time.
    >>  ............................................
    pt  Com você, então. Vamos achar a primeira parte e eu não carrego sozinho desta vez.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.help_solve/1
    en  ...Together. That's the part I couldn't do alone, %1$s, and I'd stopped trying.
    >>  ............................................
    pt  ...Juntos. É a parte que eu não conseguia sozinho, %1$s, e eu tinha parado de tentar.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us. I'd built a whole plan around having to manage by myself.
    >>  ............................................
    pt  Certo. Nós dois. Eu tinha um plano inteiro em volta de ter que me virar sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. Give me a moment — I'd not expected to be offered a second pair of hands.
    >>  ............................................
    pt  Com você, então. Me dê um momento — eu não esperava um segundo par de mãos.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it. That changes the size of it.
    >>  ............................................
    pt  Certo. Nós dois nisso. Isso muda o tamanho da coisa.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. I'd got stuck at the doing-it-alone part.
    >>  ............................................
    pt  Com você, então. Eu tinha travado na parte de fazer sozinho.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it. That changes the size of it.
    >>  ............................................
    pt  Certo. Nós dois nisso. Isso muda o tamanho da coisa.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. I'd got stuck at the doing-it-alone part.
    >>  ............................................
    pt  Com você, então. Eu tinha travado na parte de fazer sozinho.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.help_solve/1
    en  ...Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  ...Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it.
    >>  ............................................
    pt  Certo. Nós dois nisso.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.help_solve/3
    en  With you, then.
    >>  ............................................
    pt  Com você, então.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone, and alone was taking years.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho, e sozinho estava levando anos.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us, slowly. It'll get done and there's no hurry about when.
    >>  ............................................
    pt  Certo. Nós dois, devagar. Vai ser feito e não há pressa sobre quando.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. We'll start when we start. That's more than I had this morning.
    >>  ............................................
    pt  Com você, então. A gente começa quando começar. É mais do que eu tinha de manhã.
    >>  ............................................
  odd.dialogue.conversations.us.worries.help_solve/1
    en  ...Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  ...Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  odd.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it.
    >>  ............................................
    pt  Certo. Nós dois nisso.
    >>  ............................................
  odd.dialogue.conversations.us.worries.help_solve/3
    en  With you, then.
    >>  ............................................
    pt  Com você, então.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone, and alone was taking years.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho, e sozinho estava levando anos.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us, slowly. It'll get done and there's no hurry about when.
    >>  ............................................
    pt  Certo. Nós dois, devagar. Vai ser feito e não há pressa sobre quando.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. We'll start when we start. That's more than I had this morning.
    >>  ............................................
    pt  Com você, então. A gente começa quando começar. É mais do que eu tinha de manhã.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.help_solve/1
    en  Together! That's the part I couldn't do alone. Which is embarrassing and true.
    >>  ............................................
    pt  Juntos! É a parte que eu não conseguia sozinho. O que é constrangedor e verdade.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.help_solve/2
    en  Right — both of us on it. Suddenly it's a manageable size.
    >>  ............................................
    pt  Certo — nós dois nisso. De repente é de um tamanho administrável.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.help_solve/3
    en  With you, then! I'd been stuck at the doing-it-alone part for about a month.
    >>  ............................................
    pt  Com você, então! Eu estava travado na parte de fazer sozinho há um mês.
    >>  ............................................
  playful.dialogue.conversations.us.worries.help_solve/1
    en  Together! That's the part I couldn't do alone. Which is embarrassing and true.
    >>  ............................................
    pt  Juntos! É a parte que eu não conseguia sozinho. O que é constrangedor e verdade.
    >>  ............................................
  playful.dialogue.conversations.us.worries.help_solve/2
    en  Right — both of us on it. Suddenly it's a manageable size.
    >>  ............................................
    pt  Certo — nós dois nisso. De repente é de um tamanho administrável.
    >>  ............................................
  playful.dialogue.conversations.us.worries.help_solve/3
    en  With you, then! I'd been stuck at the doing-it-alone part for about a month.
    >>  ............................................
    pt  Com você, então! Eu estava travado na parte de fazer sozinho há um mês.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.help_solve/1
    en  Together. That's the part I couldn't do alone, and alone was taking years.
    >>  ............................................
    pt  Juntos. É a parte que eu não conseguia sozinho, e sozinho estava levando anos.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us, slowly. It'll get done and there's no hurry about when.
    >>  ............................................
    pt  Certo. Nós dois, devagar. Vai ser feito e não há pressa sobre quando.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. We'll start when we start. That's more than I had this morning.
    >>  ............................................
    pt  Com você, então. A gente começa quando começar. É mais do que eu tinha de manhã.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.help_solve/1
    en  ...Together. That's the part I couldn't do alone, %1$s, and I'd stopped trying.
    >>  ............................................
    pt  ...Juntos. É a parte que eu não conseguia sozinho, %1$s, e eu tinha parado de tentar.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us. I'd built a whole plan around having to manage by myself.
    >>  ............................................
    pt  Certo. Nós dois. Eu tinha um plano inteiro em volta de ter que me virar sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.help_solve/3
    en  With you, then. Give me a moment — I'd not expected to be offered a second pair of hands.
    >>  ............................................
    pt  Com você, então. Me dê um momento — eu não esperava um segundo par de mãos.
    >>  ............................................
  shy.dialogue.conversations.us.worries.help_solve/1
    en  ...Together. That's the part I couldn't do alone.
    >>  ............................................
    pt  ...Juntos. É a parte que eu não conseguia sozinho.
    >>  ............................................
  shy.dialogue.conversations.us.worries.help_solve/2
    en  Right. Both of us on it.
    >>  ............................................
    pt  Certo. Nós dois nisso.
    >>  ............................................
  shy.dialogue.conversations.us.worries.help_solve/3
    en  With you, then.
    >>  ............................................
    pt  Com você, então.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.help_solve/1
    en  Together! That's the part I couldn't do alone. Which is embarrassing and true.
    >>  ............................................
    pt  Juntos! É a parte que eu não conseguia sozinho. O que é constrangedor e verdade.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.help_solve/2
    en  Right — both of us on it. Suddenly it's a manageable size.
    >>  ............................................
    pt  Certo — nós dois nisso. De repente é de um tamanho administrável.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.help_solve/3
    en  With you, then! I'd been stuck at the doing-it-alone part for about a month.
    >>  ............................................
    pt  Com você, então! Eu estava travado na parte de fazer sozinho há um mês.
    >>  ............................................
  witty.dialogue.conversations.us.worries.help_solve/1
    en  Together! That's the part I couldn't do alone. Which is embarrassing and true.
    >>  ............................................
    pt  Juntos! É a parte que eu não conseguia sozinho. O que é constrangedor e verdade.
    >>  ............................................
  witty.dialogue.conversations.us.worries.help_solve/2
    en  Right — both of us on it. Suddenly it's a manageable size.
    >>  ............................................
    pt  Certo — nós dois nisso. De repente é de um tamanho administrável.
    >>  ............................................
  witty.dialogue.conversations.us.worries.help_solve/3
    en  With you, then! I'd been stuck at the doing-it-alone part for about a month.
    >>  ............................................
    pt  Com você, então! Eu estava travado na parte de fazer sozinho há um mês.
    >>  ............................................
```

</details>


### Button `give_space` — "You don't have to solve it today."

*stance family `restraint` · tone `gentle` · answers the beat(s) `us.worries.ask_detail.to.worries`, `us.worries.validate.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.followup.give_space` — accepted phrasings: "you do not have to solve it today"; "it does not have to be solved today"; "you can leave it for now"
  - the message must contain one of: `solve`, `today`
  - scored words: `solve`(1.0), `today`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.followup.give_space
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.followup.give_space   [33 chars]
    en  You don't have to solve it today.
    >>  ............................................
    pt  Você não precisa resolver hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.worries.give_space`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `us.worries.give_space`)_
- Does: arc `us` — advance to stage 1
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.worries.give_space
WHO    VILLAGER — what the player reads after pressing "You don't have to solve it today."
       spoken on: conversations.topic.worries.followup, button `give_space`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.give_space.to.us`: the villager accepts. Subject `us`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.give_space/1   [55 chars]
    en  ...Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que precisava.
    >>  ............................................
  dialogue.conversations.us.worries.give_space/2   [58 chars]
    en  You're right. It'll still be there tomorrow and so will I.
    >>  ............................................
    pt  Você tem razão. Vai continuar lá amanhã e eu também.
    >>  ............................................
  dialogue.conversations.us.worries.give_space/3   [46 chars]
    en  Thank you. Everyone wants it solved by supper.
    >>  ............................................
    pt  Obrigado. Todo mundo quer resolvido até o jantar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed, %1$s. Thank you.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava, %1$s. Obrigado.
    >>  ............................................
  anxious.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. I'd been bracing to have to explain it all evening.
    >>  ............................................
    pt  Certo. Então hoje não. Eu me preparava pra ter que explicar a noite toda.
    >>  ............................................
  anxious.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'd not have known how to ask for that.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu não saberia como pedir isso.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed. It'll still be there next week.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava. Vai continuar lá semana que vem.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Some things want a season before they want words.
    >>  ............................................
    pt  Certo. Então hoje não. Algumas coisas querem uma estação antes de querer palavras.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'll talk about it eventually. Not tonight.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu vou falar uma hora. Hoje não.
    >>  ............................................
  confident.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  confident.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for saying it first.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por dizer primeiro.
    >>  ............................................
  confident.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Nobody does that.
    >>  ............................................
    pt  Você me deixou não falar disso. Ninguém faz isso.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for saying it first.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por dizer primeiro.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Nobody does that.
    >>  ............................................
    pt  Você me deixou não falar disso. Ninguém faz isso.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed, %1$s.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for offering it rather than making me ask.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por oferecer em vez de me fazer pedir.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Sit with me anyway, if you've the time.
    >>  ............................................
    pt  Você me deixou não falar disso. Mas sente comigo, se você tiver tempo.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed, %1$s.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for offering it rather than making me ask.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por oferecer em vez de me fazer pedir.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Sit with me anyway, if you've the time.
    >>  ............................................
    pt  Você me deixou não falar disso. Mas sente comigo, se você tiver tempo.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed, %1$s.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for offering it rather than making me ask.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por oferecer em vez de me fazer pedir.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Sit with me anyway, if you've the time.
    >>  ............................................
    pt  Você me deixou não falar disso. Mas sente comigo, se você tiver tempo.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed, %1$s. Thank you.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava, %1$s. Obrigado.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. I'd been bracing to have to explain it all evening.
    >>  ............................................
    pt  Certo. Então hoje não. Eu me preparava pra ter que explicar a noite toda.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'd not have known how to ask for that.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu não saberia como pedir isso.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for saying it first.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por dizer primeiro.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Nobody does that.
    >>  ............................................
    pt  Você me deixou não falar disso. Ninguém faz isso.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Thank you for saying it first.
    >>  ............................................
    pt  Certo. Então hoje não. Obrigado por dizer primeiro.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. Nobody does that.
    >>  ............................................
    pt  Você me deixou não falar disso. Ninguém faz isso.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today.
    >>  ............................................
    pt  Certo. Então hoje não.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it.
    >>  ............................................
    pt  Você me deixou não falar disso.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed. It'll still be there next week.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava. Vai continuar lá semana que vem.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Some things want a season before they want words.
    >>  ............................................
    pt  Certo. Então hoje não. Algumas coisas querem uma estação antes de querer palavras.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'll talk about it eventually. Not tonight.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu vou falar uma hora. Hoje não.
    >>  ............................................
  odd.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  odd.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today.
    >>  ............................................
    pt  Certo. Então hoje não.
    >>  ............................................
  odd.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it.
    >>  ............................................
    pt  Você me deixou não falar disso.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed. It'll still be there next week.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava. Vai continuar lá semana que vem.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Some things want a season before they want words.
    >>  ............................................
    pt  Certo. Então hoje não. Algumas coisas querem uma estação antes de querer palavras.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'll talk about it eventually. Not tonight.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu vou falar uma hora. Hoje não.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.give_space/1
    en  Not today! That's permission I didn't know I needed. How useful of you.
    >>  ............................................
    pt  Hoje não! É uma permissão que eu não sabia que eu precisava. Que útil da sua parte.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.give_space/2
    en  Right — then not today. Thank you for saying it before I had to.
    >>  ............................................
    pt  Certo — então hoje não. Obrigado por dizer antes de eu ter que dizer.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it! Nobody does that. Everyone wants the details.
    >>  ............................................
    pt  Você me deixou não falar disso! Ninguém faz isso. Todos querem os detalhes.
    >>  ............................................
  playful.dialogue.conversations.us.worries.give_space/1
    en  Not today! That's permission I didn't know I needed. How useful of you.
    >>  ............................................
    pt  Hoje não! É uma permissão que eu não sabia que eu precisava. Que útil da sua parte.
    >>  ............................................
  playful.dialogue.conversations.us.worries.give_space/2
    en  Right — then not today. Thank you for saying it before I had to.
    >>  ............................................
    pt  Certo — então hoje não. Obrigado por dizer antes de eu ter que dizer.
    >>  ............................................
  playful.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it! Nobody does that. Everyone wants the details.
    >>  ............................................
    pt  Você me deixou não falar disso! Ninguém faz isso. Todos querem os detalhes.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.give_space/1
    en  Not today. That's permission I didn't know I needed. It'll still be there next week.
    >>  ............................................
    pt  Hoje não. É uma permissão que eu não sabia que eu precisava. Vai continuar lá semana que vem.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. Some things want a season before they want words.
    >>  ............................................
    pt  Certo. Então hoje não. Algumas coisas querem uma estação antes de querer palavras.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'll talk about it eventually. Not tonight.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu vou falar uma hora. Hoje não.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed, %1$s. Thank you.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava, %1$s. Obrigado.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today. I'd been bracing to have to explain it all evening.
    >>  ............................................
    pt  Certo. Então hoje não. Eu me preparava pra ter que explicar a noite toda.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it. I'd not have known how to ask for that.
    >>  ............................................
    pt  Você me deixou não falar disso. Eu não saberia como pedir isso.
    >>  ............................................
  shy.dialogue.conversations.us.worries.give_space/1
    en  ...Not today. That's permission I didn't know I needed.
    >>  ............................................
    pt  ...Hoje não. É uma permissão que eu não sabia que eu precisava.
    >>  ............................................
  shy.dialogue.conversations.us.worries.give_space/2
    en  Right. Then not today.
    >>  ............................................
    pt  Certo. Então hoje não.
    >>  ............................................
  shy.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it.
    >>  ............................................
    pt  Você me deixou não falar disso.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.give_space/1
    en  Not today! That's permission I didn't know I needed. How useful of you.
    >>  ............................................
    pt  Hoje não! É uma permissão que eu não sabia que eu precisava. Que útil da sua parte.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.give_space/2
    en  Right — then not today. Thank you for saying it before I had to.
    >>  ............................................
    pt  Certo — então hoje não. Obrigado por dizer antes de eu ter que dizer.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it! Nobody does that. Everyone wants the details.
    >>  ............................................
    pt  Você me deixou não falar disso! Ninguém faz isso. Todos querem os detalhes.
    >>  ............................................
  witty.dialogue.conversations.us.worries.give_space/1
    en  Not today! That's permission I didn't know I needed. How useful of you.
    >>  ............................................
    pt  Hoje não! É uma permissão que eu não sabia que eu precisava. Que útil da sua parte.
    >>  ............................................
  witty.dialogue.conversations.us.worries.give_space/2
    en  Right — then not today. Thank you for saying it before I had to.
    >>  ............................................
    pt  Certo — então hoje não. Obrigado por dizer antes de eu ter que dizer.
    >>  ............................................
  witty.dialogue.conversations.us.worries.give_space/3
    en  You've let me not talk about it! Nobody does that. Everyone wants the details.
    >>  ............................................
    pt  Você me deixou não falar disso! Ninguém faz isso. Todos querem os detalhes.
    >>  ............................................
```

</details>


### Button `change_subject` — "Let's think about something else."

*stance family `exit` · tone `plain` · answers the beat(s) `us.worries.ask_detail.to.worries`, `us.worries.validate.to.worries` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.worries.followup.change_subject
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.followup.change_subject   [33 chars]
    en  Let's think about something else.
    >>  ............................................
    pt  Vamos pensar em outra coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.worries.change_subject
WHO    VILLAGER — what the player reads after pressing "Let's think about something else."
       spoken on: conversations.topic.worries.followup, button `change_subject`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.change_subject.to.us`: the villager accepts. Subject `us`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.change_subject/1   [55 chars]
    en  Aye, let's. I've turned it over enough for one evening.
    >>  ............................................
    pt  É, vamos. Já remoí o bastante por uma noite.
    >>  ............................................
  dialogue.conversations.us.worries.change_subject/2   [44 chars]
    en  Good idea. Tell me something ordinary, %1$s.
    >>  ............................................
    pt  Boa ideia. Me conta algo comum, %1$s.
    >>  ............................................
  dialogue.conversations.us.worries.change_subject/3   [37 chars]
    en  Right. Something else. Anything else.
    >>  ............................................
    pt  Certo. Outra coisa. Qualquer outra coisa.
    >>  ............................................
```


### Button `ask_what_would_help` — "Would knowing something help?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `us.worries.ask_detail.to.worries`, `us.worries.validate.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.information` — accepted phrasings: "would knowing something help"; "would it help to know something"; "is there something you need to know"
  - the message must contain one of: `knowing`
  - scored words: `know`(0.4), `knowing`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.followup.ask_what_would_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.followup.ask_what_would_help   [29 chars]
    en  Would knowing something help?
    >>  ............................................
    pt  Saber de alguma coisa ajudaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.worries.information`
- …where the player's next choices will be: "I'll find out for you." | "Here's what I know already." | "I'll see what I can do."

```text
POOL   dialogue key: dialogue.conversations.worries.information
WHO    VILLAGER — what the player reads after pressing "Would knowing something help?"
       spoken on: conversations.topic.worries.followup, button `ask_what_would_help`
       leaves the player on: conversations.topic.worries.information
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.information`: the villager request_helps. Subject `worries.information`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, self_disclosure, exit
```

```text
  dialogue.conversations.worries.information/1   [81 chars]
    en  It would. I don't know whether the road south is passable and I can't ask openly.
    >>  ............................................
    pt  Ajudaria. Não sei se a estrada do sul está passável e não posso perguntar abertamente.
    >>  ............................................
  dialogue.conversations.worries.information/2   [81 chars]
    en  Yes. Whether it's happened to anybody else here, and whether they came out of it.
    >>  ............................................
    pt  Sim. Se aconteceu com mais alguém aqui, e se essa pessoa saiu disso.
    >>  ............................................
  dialogue.conversations.worries.information/3   [81 chars]
    en  One thing. Whether the thing I'm afraid of is even possible, or I've invented it.
    >>  ............................................
    pt  Uma coisa. Se aquilo que eu temo é sequer possível, ou se eu inventei.
    >>  ............................................
```


### Button `leave` — "I'll let you think."

*stance family `exit` · tone `plain` · answers the beat(s) `us.worries.ask_detail.to.worries`, `us.worries.validate.to.worries` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.worries.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.followup.leave   [19 chars]
    en  I'll let you think.
    >>  ............................................
    pt  Vou deixar você pensar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.worries.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you think."
       spoken on: conversations.topic.worries.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.worries.respond / leave
```

```text
  dialogue.conversations.us.worries.leave/1   [33 chars]
    en  Aye. Thank you for asking at all.
    >>  ............................................
    pt  É. Obrigado por ter perguntado.
    >>  ............................................
  dialogue.conversations.us.worries.leave/2   [24 chars]
    en  We'll speak again, %1$s.
    >>  ............................................
    pt  A gente se fala, %1$s.
    >>  ............................................
  dialogue.conversations.us.worries.leave/3   [20 chars]
    en  Right. I'll be here.
    >>  ............................................
    pt  Certo. Vou estar aqui.
    >>  ............................................
```

---


## `conversations.topic.worries.heard`

**Reached from 1 route(s):** `conversations.topic.worries.respond` / `just_listen`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.worries.heard` — e.g. "Yes. Everyone reaches for a solution and I've had four this week."


```text
POOL   dialogue key: dialogue.conversations.topic.worries.heard
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.worries.heard
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.worries.heard   [58 chars]
    en  So that's what I'd want, if I'm allowed to want something.
    >>  ............................................
    pt  Então é isso que eu ia querer, se eu tiver direito de querer algo.
    >>  ............................................
```


### Button `then_ill_listen` — "Then I'll listen. Take as long as you like."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `worries.heard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.heard.listened` — accepted phrasings: "then i will listen"; "take as long as you like"; "i am listening go on"
  - the message must contain one of: `listen`
  - scored words: `listen`(1.2), `long`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.heard.then_ill_listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.heard
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.heard.then_ill_listen   [43 chars]
    en  Then I'll listen. Take as long as you like.
    >>  ............................................
    pt  Então eu escuto. Leve o tempo que quiser.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `worries.heard.listened`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +3  _(recorded under topic `worries.heard.listened`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.heard.listened
WHO    VILLAGER — what the player reads after pressing "Then I'll listen. Take as long as you like."
       spoken on: conversations.topic.worries.heard, button `then_ill_listen`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.heard.listened`: the villager accepts. Subject `worries.heard`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.worries.heard.listened/1   [63 chars]
    en  ...Right. Then I'll start at the part I've not said to anybody.
    >>  ............................................
    pt  ...Certo. Então começo pela parte que não contei a ninguém.
    >>  ............................................
  dialogue.conversations.worries.heard.listened/2   [82 chars]
    en  You'll be here a while. I've been rehearsing this on the walk to work for a month.
    >>  ............................................
    pt  Você vai ficar um tempo. Venho ensaiando isso no caminho do trabalho há um mês.
    >>  ............................................
  dialogue.conversations.worries.heard.listened/3   [69 chars]
    en  Thank you. I'll not need long — I mostly needed somebody in the room.
    >>  ............................................
    pt  Obrigado. Não vou precisar de muito — eu precisava de alguém na sala.
    >>  ............................................
```


### Button `when_youre_ready` — "And I'll be here when you want more than that."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `worries.heard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.heard.later` — accepted phrasings: "i will be here when you want more"; "i am here if you want more than that"; "come to me when you want help"
  - scored words: `here`(0.5), `want`(0.4), `more`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.heard.when_youre_ready
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.heard
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.heard.when_youre_ready   [46 chars]
    en  And I'll be here when you want more than that.
    >>  ............................................
    pt  E eu estarei aqui quando você quiser mais que isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `worries.heard.later`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `worries.heard.later`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.heard.later
WHO    VILLAGER — what the player reads after pressing "And I'll be here when you want more than that."
       spoken on: conversations.topic.worries.heard, button `when_youre_ready`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.heard.later`: the villager accepts. Subject `worries.heard`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.worries.heard.later/1   [77 chars]
    en  That's the useful half of a promise. Everyone else only makes the other half.
    >>  ............................................
    pt  É a metade útil de uma promessa. Todos os outros só fazem a outra metade.
    >>  ............................................
  dialogue.conversations.worries.heard.later/2   [70 chars]
    en  I'll want more than that eventually. It's good to know where it'll be.
    >>  ............................................
    pt  Vou querer mais que isso um dia. É bom saber onde vai estar.
    >>  ............................................
  dialogue.conversations.worries.heard.later/3   [70 chars]
    en  Then I'll ask, and I'm bad at asking, so expect it to arrive sideways.
    >>  ............................................
    pt  Então eu peço, e sou ruim em pedir, então espere que chegue de lado.
    >>  ............................................
```


### Button `leave` — "I'll not crowd you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `worries.heard` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.worries.heard.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.heard
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.heard.leave   [19 chars]
    en  I'll not crowd you.
    >>  ............................................
    pt  Não vou te sufocar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.heard.leave
WHO    VILLAGER — what the player reads after pressing "I'll not crowd you."
       spoken on: conversations.topic.worries.heard, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.heard.leave`: the villager accepts. Subject `worries.heard`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.worries.heard.leave/1   [12 chars]
    en  Kind of you.
    >>  ............................................
    pt  Gentil da sua parte.
    >>  ............................................
  dialogue.conversations.worries.heard.leave/2   [15 chars]
    en  Aye. Thank you.
    >>  ............................................
    pt  É. Obrigado.
    >>  ............................................
  dialogue.conversations.worries.heard.leave/3   [19 chars]
    en  Get on, then, %1$s.
    >>  ............................................
    pt  Então vá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.worries.information`

**Reached from 1 route(s):** `conversations.topic.worries.followup` / `ask_what_would_help`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.worries.information` — e.g. "It would. I don't know whether the road south is passable and I can't ask openly."


```text
POOL   dialogue key: dialogue.conversations.topic.worries.information
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.worries.information
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.worries.information   [54 chars]
    en  That's what I'd want to know, if anyone could tell me.
    >>  ............................................
    pt  É isso que eu ia querer saber, se alguém pudesse me dizer.
    >>  ............................................
```


### Button `ill_find_out` — "I'll find out for you."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `worries.information`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.information.find` — accepted phrasings: "i will find out for you"; "i can find that out"; "leave it with me i will ask"
  - the message must contain one of: `find`
  - scored words: `find`(1.2), `out`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.information.ill_find_out
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.information
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.information.ill_find_out   [22 chars]
    en  I'll find out for you.
    >>  ............................................
    pt  Eu descubro pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `worries.information.find`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `worries.information.find`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.information.find
WHO    VILLAGER — what the player reads after pressing "I'll find out for you."
       spoken on: conversations.topic.worries.information, button `ill_find_out`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.information.find`: the villager accepts. Subject `worries.information`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.worries.information.find/1   [72 chars]
    en  Would you. Then I'll stop turning it over every night until you're back.
    >>  ............................................
    pt  Você faria isso? Então paro de revirar isso toda noite até você voltar.
    >>  ............................................
  dialogue.conversations.worries.information.find/2   [81 chars]
    en  Quietly, if you can. I'd rather the question didn't get about with my name on it.
    >>  ............................................
    pt  Em silêncio, se puder. Prefiro que a pergunta não corra com meu nome.
    >>  ............................................
  dialogue.conversations.worries.information.find/3   [80 chars]
    en  Then that's the first useful thing anyone's offered, and I've asked four people.
    >>  ............................................
    pt  Então é a primeira coisa útil que alguém ofereceu, e eu perguntei a quatro.
    >>  ............................................
```


### Button `what_i_know` — "Here's what I know already."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `worries.information`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.information.told` — accepted phrasings: "here is what i know already"; "i can tell you what i know"; "let me tell you what i have heard"
  - the message must contain one of: `already`
  - scored words: `already`(1.2), `here`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.information.what_i_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.information
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.information.what_i_know   [27 chars]
    en  Here's what I know already.
    >>  ............................................
    pt  Olha o que eu já sei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `worries.information.told`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, respect +2  _(recorded under topic `worries.information.told`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.information.told
WHO    VILLAGER — what the player reads after pressing "Here's what I know already."
       spoken on: conversations.topic.worries.information, button `what_i_know`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.information.told`: the villager accepts. Subject `worries.information`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.worries.information.told/1   [72 chars]
    en  ...That's better than I'd feared and worse than I'd hoped. I'll take it.
    >>  ............................................
    pt  ...É melhor do que eu temia e pior do que eu esperava. Eu aceito.
    >>  ............................................
  dialogue.conversations.worries.information.told/2   [76 chars]
    en  Say that again slowly. I've been guessing for a fortnight on less than that.
    >>  ............................................
    pt  Repita devagar. Passei quinze dias adivinhando com menos que isso.
    >>  ............................................
  dialogue.conversations.worries.information.told/3   [66 chars]
    en  Then I've been frightened of the wrong half of it this whole time.
    >>  ............................................
    pt  Então eu estava com medo da metade errada esse tempo todo.
    >>  ............................................
```


### Button `leave` — "I'll see what I can do."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `worries.information` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.worries.information.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.information
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.information.leave   [23 chars]
    en  I'll see what I can do.
    >>  ............................................
    pt  Vou ver o que consigo fazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.worries.information.leave
WHO    VILLAGER — what the player reads after pressing "I'll see what I can do."
       spoken on: conversations.topic.worries.information, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.information.leave`: the villager accepts. Subject `worries.information`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.worries.information.leave/1   [19 chars]
    en  That's all I asked.
    >>  ............................................
    pt  É tudo que eu pedi.
    >>  ............................................
  dialogue.conversations.worries.information.leave/2   [15 chars]
    en  Aye. Thank you.
    >>  ............................................
    pt  É. Obrigado.
    >>  ............................................
  dialogue.conversations.worries.information.leave/3   [18 chars]
    en  Enough said, %1$s.
    >>  ............................................
    pt  Já foi dito, %1$s.
    >>  ............................................
```

---


## `conversations.topic.worries.respond`

**Reached from 3 route(s):** `conversations.us` / `worries`; `conversations.us` / `worries`; `conversations.us` / `worries`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.worries.again` — e.g. "You checked on me already, love. I promise I'd tell you."
- `conversations.us.worries.fine` — e.g. "Nothing worth your frown. The usual aches. Having you ask is half the cure."
- `conversations.us.worries.open` — e.g. "Since you ask... yes. I didn't want to burden you, but yes. Sit with me a moment."


```text
POOL   dialogue key: dialogue.conversations.topic.worries.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.worries.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.worries.respond   [28 chars]
    en  That's what's sitting on me.
    >>  ............................................
    pt  É isso que está me pesando.
    >>  ............................................
```


### Button `validate` — "That's worth worrying about."

*stance family `empathy` · tone `gentle` · answers the beat(s) `us.worries.again.to.worries`, `us.worries.fine.to.worries`, `us.worries.open.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.validate` — accepted phrasings: "that is worth worrying about"; "that is a fair thing to worry about"; "you are right to be worried"
  - the message must contain one of: `worth`
  - scored words: `worrying`(0.8), `worth`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.respond.validate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.respond.validate   [28 chars]
    en  That's worth worrying about.
    >>  ............................................
    pt  Vale a pena se preocupar com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.worries.validate`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `us.worries.validate`)_
- Then opens: `conversations.topic.worries.followup`
- …where the player's next choices will be: "Let's sort it together." | "You don't have to solve it today." | "Let's think about something else." | "Would knowing something help?" | "I'll let you think."

```text
POOL   dialogue key: dialogue.conversations.us.worries.validate
WHO    VILLAGER — what the player reads after pressing "That's worth worrying about."
       spoken on: conversations.topic.worries.respond, button `validate`
       leaves the player on: conversations.topic.worries.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.validate.to.worries`: the villager accepts. Subject `worries`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.validate/1   [59 chars]
    en  ...It is, isn't it. I'd half convinced myself it was silly.
    >>  ............................................
    pt  ...É mesmo, né. Eu tinha quase me convencido de que era bobagem.
    >>  ............................................
  dialogue.conversations.us.worries.validate/2   [52 chars]
    en  You didn't tell me not to worry. Thank you for that.
    >>  ............................................
    pt  Você não me mandou não me preocupar. Obrigado por isso.
    >>  ............................................
  dialogue.conversations.us.worries.validate/3   [45 chars]
    en  Worth worrying about. Aye. That helps, oddly.
    >>  ............................................
    pt  Vale se preocupar. É. Isso ajuda, estranhamente.
    >>  ............................................
```


### Button `ask_detail` — "Tell me the rest of it."

*stance family `curiosity` · tone `plain` · answers the beat(s) `us.worries.again.to.worries`, `us.worries.fine.to.worries`, `us.worries.open.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.ask_detail` — accepted phrasings: "tell me the rest of it"; "go on tell me the rest"; "let me hear the rest"
  - the message must contain one of: `rest`
  - scored words: `rest`(1.2), `tell`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.respond.ask_detail
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.respond.ask_detail   [23 chars]
    en  Tell me the rest of it.
    >>  ............................................
    pt  Me conta o resto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.worries.ask_detail`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +2  _(recorded under topic `us.worries.ask_detail`)_
- Then opens: `conversations.topic.worries.followup`
- …where the player's next choices will be: "Let's sort it together." | "You don't have to solve it today." | "Let's think about something else." | "Would knowing something help?" | "I'll let you think."

```text
POOL   dialogue key: dialogue.conversations.us.worries.ask_detail
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.topic.worries.respond, button `ask_detail`
       leaves the player on: conversations.topic.worries.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.ask_detail.to.worries`: the villager accepts. Subject `worries`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.ask_detail/1   [64 chars]
    en  The rest of it. Alright — but it gets less reasonable from here.
    >>  ............................................
    pt  O resto. Certo — mas fica menos razoável daqui em diante.
    >>  ............................................
  dialogue.conversations.us.worries.ask_detail/2   [49 chars]
    en  You want all of it. Nobody wants all of it, %1$s.
    >>  ............................................
    pt  Você quer tudo. Ninguém quer tudo, %1$s.
    >>  ............................................
  dialogue.conversations.us.worries.ask_detail/3   [42 chars]
    en  Right. Sit down. This is the long version.
    >>  ............................................
    pt  Certo. Senta. Essa é a versão longa.
    >>  ............................................
```


### Button `dismiss` — "That's nothing to fret over."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `us.worries.again.to.worries`, `us.worries.fine.to.worries`, `us.worries.open.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.dismiss` — accepted phrasings: "that is nothing to fret over"; "that is nothing to worry about"; "there is nothing in that to fret about"
  - the message must contain one of: `fret`
  - scored words: `fret`(1.5), `nothing`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.respond.dismiss   [28 chars]
    en  That's nothing to fret over.
    >>  ............................................
    pt  Não é nada para se preocupar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `us.worries.dismiss`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +6  _(recorded under topic `us.worries.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.worries.dismissed.followup`
- …where the player's next choices will be: "Don't. It was worth saying and I brushed it off." | "I meant it isn't as bad as it feels." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.us.worries.dismiss
WHO    VILLAGER — what the player reads after pressing "That's nothing to fret over."
       spoken on: conversations.topic.worries.respond, button `dismiss`
       leaves the player on: conversations.topic.worries.dismissed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.dismissed.open`: the villager hurts. Subject `worries.burden`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.worries.dismiss/1   [56 chars]
    en  ...Nothing to fret over. Right. I'll fret quietly, then.
    >>  ............................................
    pt  ...Nada para me preocupar. Certo. Vou me preocupar em silêncio, então.
    >>  ............................................
  dialogue.conversations.us.worries.dismiss/2   [27 chars]
    en  It's something to me, %1$s.
    >>  ............................................
    pt  Para mim é algo, %1$s.
    >>  ............................................
  dialogue.conversations.us.worries.dismiss/3   [35 chars]
    en  Then I'll not mention the next one.
    >>  ............................................
    pt  Então não vou mencionar a próxima.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. I've been fretting for a month, %1$s.
    >>  ............................................
    pt  ...Nada com que se preocupar. Eu venho me preocupando há um mês, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.us.worries.dismiss/2
    en  Right. Yes. Silly of me. I'd worked that out and said it anyway.
    >>  ............................................
    pt  Certo. Sim. Bobagem minha. Eu já sabia e disse mesmo assim.
    >>  ............................................
  anxious.dialogue.conversations.us.worries.dismiss/3
    en  ...Sorry. I'll keep the next one in.
    >>  ............................................
    pt  ...Desculpe. Vou guardar a próxima.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Well, we'll see. Most things do come out all right.
    >>  ............................................
    pt  Nada com que se preocupar. Bom, vamos ver. Quase tudo acaba bem.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.dismiss/2
    en  ...Aye, likely. I'll keep half an eye on it regardless.
    >>  ............................................
    pt  ...É, provavelmente. Vou ficar de olho de qualquer jeito.
    >>  ............................................
  athletic.dialogue.conversations.us.worries.dismiss/3
    en  Right you are. It'll sort itself, or it'll ask again.
    >>  ............................................
    pt  Você tem razão. Vai se resolver, ou vai pedir de novo.
    >>  ............................................
  confident.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Right. I'll fret quietly, then.
    >>  ............................................
    pt  Nada com que se preocupar. Certo. Vou me preocupar em silêncio, então.
    >>  ............................................
  confident.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing. I'd not have raised it if it were.
    >>  ............................................
    pt  Não é nada. Eu não teria levantado se fosse.
    >>  ............................................
  confident.dialogue.conversations.us.worries.dismiss/3
    en  ...Fine. Consider it unmentioned.
    >>  ............................................
    pt  ...Tudo bem. Considere não mencionado.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Right. I'll fret quietly, then.
    >>  ............................................
    pt  Nada com que se preocupar. Certo. Vou me preocupar em silêncio, então.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing. I'd not have raised it if it were.
    >>  ............................................
    pt  Não é nada. Eu não teria levantado se fosse.
    >>  ............................................
  crabby.dialogue.conversations.us.worries.dismiss/3
    en  ...Fine. Consider it unmentioned.
    >>  ............................................
    pt  ...Tudo bem. Considere não mencionado.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. I brought it to you because it was about us, %1$s.
    >>  ............................................
    pt  ...Nada com que se preocupar. Eu trouxe a você porque era sobre nós, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.dismiss/2
    en  Right. I'll not bring you the next one, then.
    >>  ............................................
    pt  Certo. Então não te trago a próxima.
    >>  ............................................
  extroverted.dialogue.conversations.us.worries.dismiss/3
    en  ...I'd rather have been told I was wrong than told it was nothing.
    >>  ............................................
    pt  ...Eu preferia ouvir que estava errado a ouvir que não era nada.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. I brought it to you because it was about us, %1$s.
    >>  ............................................
    pt  ...Nada com que se preocupar. Eu trouxe a você porque era sobre nós, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.dismiss/2
    en  Right. I'll not bring you the next one, then.
    >>  ............................................
    pt  Certo. Então não te trago a próxima.
    >>  ............................................
  flirty.dialogue.conversations.us.worries.dismiss/3
    en  ...I'd rather have been told I was wrong than told it was nothing.
    >>  ............................................
    pt  ...Eu preferia ouvir que estava errado a ouvir que não era nada.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. I brought it to you because it was about us, %1$s.
    >>  ............................................
    pt  ...Nada com que se preocupar. Eu trouxe a você porque era sobre nós, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.dismiss/2
    en  Right. I'll not bring you the next one, then.
    >>  ............................................
    pt  Certo. Então não te trago a próxima.
    >>  ............................................
  friendly.dialogue.conversations.us.worries.dismiss/3
    en  ...I'd rather have been told I was wrong than told it was nothing.
    >>  ............................................
    pt  ...Eu preferia ouvir que estava errado a ouvir que não era nada.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. I've been fretting for a month, %1$s.
    >>  ............................................
    pt  ...Nada com que se preocupar. Eu venho me preocupando há um mês, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.dismiss/2
    en  Right. Yes. Silly of me. I'd worked that out and said it anyway.
    >>  ............................................
    pt  Certo. Sim. Bobagem minha. Eu já sabia e disse mesmo assim.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.dismiss/3
    en  ...Sorry. I'll keep the next one in.
    >>  ............................................
    pt  ...Desculpe. Vou guardar a próxima.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Right. I'll fret quietly, then.
    >>  ............................................
    pt  Nada com que se preocupar. Certo. Vou me preocupar em silêncio, então.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing. I'd not have raised it if it were.
    >>  ............................................
    pt  Não é nada. Eu não teria levantado se fosse.
    >>  ............................................
  greedy.dialogue.conversations.us.worries.dismiss/3
    en  ...Fine. Consider it unmentioned.
    >>  ............................................
    pt  ...Tudo bem. Considere não mencionado.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Right. I'll fret quietly, then.
    >>  ............................................
    pt  Nada com que se preocupar. Certo. Vou me preocupar em silêncio, então.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing. I'd not have raised it if it were.
    >>  ............................................
    pt  Não é nada. Eu não teria levantado se fosse.
    >>  ............................................
  grumpy.dialogue.conversations.us.worries.dismiss/3
    en  ...Fine. Consider it unmentioned.
    >>  ............................................
    pt  ...Tudo bem. Considere não mencionado.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. Right.
    >>  ............................................
    pt  ...Nada com que se preocupar. Certo.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing.
    >>  ............................................
    pt  Não é nada.
    >>  ............................................
  introverted.dialogue.conversations.us.worries.dismiss/3
    en  ...I'll fret quietly.
    >>  ............................................
    pt  ...Vou me preocupar em silêncio.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Well, we'll see. Most things do come out all right.
    >>  ............................................
    pt  Nada com que se preocupar. Bom, vamos ver. Quase tudo acaba bem.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.dismiss/2
    en  ...Aye, likely. I'll keep half an eye on it regardless.
    >>  ............................................
    pt  ...É, provavelmente. Vou ficar de olho de qualquer jeito.
    >>  ............................................
  lazy.dialogue.conversations.us.worries.dismiss/3
    en  Right you are. It'll sort itself, or it'll ask again.
    >>  ............................................
    pt  Você tem razão. Vai se resolver, ou vai pedir de novo.
    >>  ............................................
  odd.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. Right.
    >>  ............................................
    pt  ...Nada com que se preocupar. Certo.
    >>  ............................................
  odd.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing.
    >>  ............................................
    pt  Não é nada.
    >>  ............................................
  odd.dialogue.conversations.us.worries.dismiss/3
    en  ...I'll fret quietly.
    >>  ............................................
    pt  ...Vou me preocupar em silêncio.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Well, we'll see. Most things do come out all right.
    >>  ............................................
    pt  Nada com que se preocupar. Bom, vamos ver. Quase tudo acaba bem.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.dismiss/2
    en  ...Aye, likely. I'll keep half an eye on it regardless.
    >>  ............................................
    pt  ...É, provavelmente. Vou ficar de olho de qualquer jeito.
    >>  ............................................
  peaceful.dialogue.conversations.us.worries.dismiss/3
    en  Right you are. It'll sort itself, or it'll ask again.
    >>  ............................................
    pt  Você tem razão. Vai se resolver, ou vai pedir de novo.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over! Marvellous. I'll cancel the fretting immediately.
    >>  ............................................
    pt  Nada com que se preocupar! Maravilhoso. Cancelo a preocupação imediatamente.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.dismiss/2
    en  Right, well. I'll worry about it on my own time, %1$s.
    >>  ............................................
    pt  Certo, bom. Vou me preocupar no meu tempo livre, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.us.worries.dismiss/3
    en  ...Ha. Fine. Consider me reassured. Utterly.
    >>  ............................................
    pt  ...Ha. Tudo bem. Considere-me tranquilizado. Completamente.
    >>  ............................................
  playful.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over! Marvellous. I'll cancel the fretting immediately.
    >>  ............................................
    pt  Nada com que se preocupar! Maravilhoso. Cancelo a preocupação imediatamente.
    >>  ............................................
  playful.dialogue.conversations.us.worries.dismiss/2
    en  Right, well. I'll worry about it on my own time, %1$s.
    >>  ............................................
    pt  Certo, bom. Vou me preocupar no meu tempo livre, %1$s.
    >>  ............................................
  playful.dialogue.conversations.us.worries.dismiss/3
    en  ...Ha. Fine. Consider me reassured. Utterly.
    >>  ............................................
    pt  ...Ha. Tudo bem. Considere-me tranquilizado. Completamente.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over. Well, we'll see. Most things do come out all right.
    >>  ............................................
    pt  Nada com que se preocupar. Bom, vamos ver. Quase tudo acaba bem.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.dismiss/2
    en  ...Aye, likely. I'll keep half an eye on it regardless.
    >>  ............................................
    pt  ...É, provavelmente. Vou ficar de olho de qualquer jeito.
    >>  ............................................
  relaxed.dialogue.conversations.us.worries.dismiss/3
    en  Right you are. It'll sort itself, or it'll ask again.
    >>  ............................................
    pt  Você tem razão. Vai se resolver, ou vai pedir de novo.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. I've been fretting for a month, %1$s.
    >>  ............................................
    pt  ...Nada com que se preocupar. Eu venho me preocupando há um mês, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.dismiss/2
    en  Right. Yes. Silly of me. I'd worked that out and said it anyway.
    >>  ............................................
    pt  Certo. Sim. Bobagem minha. Eu já sabia e disse mesmo assim.
    >>  ............................................
  sensitive.dialogue.conversations.us.worries.dismiss/3
    en  ...Sorry. I'll keep the next one in.
    >>  ............................................
    pt  ...Desculpe. Vou guardar a próxima.
    >>  ............................................
  shy.dialogue.conversations.us.worries.dismiss/1
    en  ...Nothing to fret over. Right.
    >>  ............................................
    pt  ...Nada com que se preocupar. Certo.
    >>  ............................................
  shy.dialogue.conversations.us.worries.dismiss/2
    en  It isn't nothing.
    >>  ............................................
    pt  Não é nada.
    >>  ............................................
  shy.dialogue.conversations.us.worries.dismiss/3
    en  ...I'll fret quietly.
    >>  ............................................
    pt  ...Vou me preocupar em silêncio.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over! Marvellous. I'll cancel the fretting immediately.
    >>  ............................................
    pt  Nada com que se preocupar! Maravilhoso. Cancelo a preocupação imediatamente.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.dismiss/2
    en  Right, well. I'll worry about it on my own time, %1$s.
    >>  ............................................
    pt  Certo, bom. Vou me preocupar no meu tempo livre, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.us.worries.dismiss/3
    en  ...Ha. Fine. Consider me reassured. Utterly.
    >>  ............................................
    pt  ...Ha. Tudo bem. Considere-me tranquilizado. Completamente.
    >>  ............................................
  witty.dialogue.conversations.us.worries.dismiss/1
    en  Nothing to fret over! Marvellous. I'll cancel the fretting immediately.
    >>  ............................................
    pt  Nada com que se preocupar! Maravilhoso. Cancelo a preocupação imediatamente.
    >>  ............................................
  witty.dialogue.conversations.us.worries.dismiss/2
    en  Right, well. I'll worry about it on my own time, %1$s.
    >>  ............................................
    pt  Certo, bom. Vou me preocupar no meu tempo livre, %1$s.
    >>  ............................................
  witty.dialogue.conversations.us.worries.dismiss/3
    en  ...Ha. Fine. Consider me reassured. Utterly.
    >>  ............................................
    pt  ...Ha. Tudo bem. Considere-me tranquilizado. Completamente.
    >>  ............................................
```

</details>


### Button `just_listen` — "Would you rather I just listened?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `us.worries.again.to.worries`, `us.worries.fine.to.worries`, `us.worries.open.to.worries`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `worries.heard` — accepted phrasings: "would you rather i just listened"; "do you want me to listen"; "shall i just listen"
  - the message must contain one of: `listened`
  - scored words: `listened`(1.5), `rather`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.worries.respond.just_listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.respond.just_listen   [33 chars]
    en  Would you rather I just listened?
    >>  ............................................
    pt  Você prefere que eu só escute?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.worries.heard`
- …where the player's next choices will be: "Then I'll listen. Take as long as you like." | "And I'll be here when you want more than that." | "I'll not crowd you."

```text
POOL   dialogue key: dialogue.conversations.worries.heard
WHO    VILLAGER — what the player reads after pressing "Would you rather I just listened?"
       spoken on: conversations.topic.worries.respond, button `just_listen`
       leaves the player on: conversations.topic.worries.heard
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.heard`: the villager qualifys. Subject `worries.heard`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (8 families), so it must not close the subject down
```

```text
  dialogue.conversations.worries.heard/1   [65 chars]
    en  Yes. Everyone reaches for a solution and I've had four this week.
    >>  ............................................
    pt  Sim. Todos correm pra uma solução e eu tive quatro esta semana.
    >>  ............................................
  dialogue.conversations.worries.heard/2   [78 chars]
    en  I would. There's nothing to be done and being told that again would finish me.
    >>  ............................................
    pt  Eu preferia. Não há o que fazer e ouvir isso de novo me acabaria.
    >>  ............................................
  dialogue.conversations.worries.heard/3   [63 chars]
    en  ...That's the first time anybody has asked instead of assuming.
    >>  ............................................
    pt  ...É a primeira vez que alguém pergunta em vez de presumir.
    >>  ............................................
```


### Button `leave` — "I'll let you think."

*stance family `exit` · tone `plain` · answers the beat(s) `us.worries.again.to.worries`, `us.worries.fine.to.worries`, `us.worries.open.to.worries` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.worries.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.worries.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.worries.respond.leave   [19 chars]
    en  I'll let you think.
    >>  ............................................
    pt  Vou deixar você pensar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.worries.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you think."
       spoken on: conversations.topic.worries.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.worries.followup / leave
```

> Written out in full under **`conversations.topic.worries.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

