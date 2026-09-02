# Topic: rumors

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `rumors` |
| Opened from | question `conversations.cat.village`, button `rumors` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.village` |
| Ages that can reach it | adult |
| Stance families it must offer | `curiosity`, `restraint`, `challenge`, `dismissal`, `exit` |
| Narrative arc | `rumors`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.village`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.village.rumors
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.village.rumors   [24 chars]
    en  Any rumors going around?
    >>  ............................................
    pt  Tem algum boato rolando?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.rumors.resume.followup`](#conversations-arc-rumors-resume-followup)
- [`conversations.arc.rumors.resume.respond`](#conversations-arc-rumors-resume-respond)
- [`conversations.scene.rumors.followup`](#conversations-scene-rumors-followup)
- [`conversations.scene.rumors.one_i_will_not_pass_on.respond`](#conversations-scene-rumors-one-i-will-not-pass-on-respond)
- [`conversations.scene.rumors.the_correction.respond`](#conversations-scene-rumors-the-correction-respond)
- [`conversations.topic.rumors.about_player`](#conversations-topic-rumors-about-player)
- [`conversations.topic.rumors.doubt`](#conversations-topic-rumors-doubt)
- [`conversations.topic.rumors.followup`](#conversations-topic-rumors-followup)
- [`conversations.topic.rumors.harmless`](#conversations-topic-rumors-harmless)
- [`conversations.topic.rumors.more`](#conversations-topic-rumors-more)
- [`conversations.topic.rumors.none.respond`](#conversations-topic-rumors-none-respond)
- [`conversations.topic.rumors.private.followup`](#conversations-topic-rumors-private-followup)
- [`conversations.topic.rumors.private.respond`](#conversations-topic-rumors-private-respond)
- [`conversations.topic.rumors.respond`](#conversations-topic-rumors-respond)
- [`conversations.topic.rumors.stale`](#conversations-topic-rumors-stale)

---

## `conversations.arc.rumors.resume.followup`

**Reached from 3 route(s):** `conversations.arc.rumors.resume.respond` / `believe_you`; `conversations.arc.rumors.resume.respond` / `who_then`; `conversations.arc.rumors.resume.respond` / `let_it_die`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.resume.believe_you` — e.g. "...Thank you. I'd worked out what I'd say if you didn't, and I'd rather not use it."
- `conversations.rumors.resume.let_it_die` — e.g. "It will, if neither of us feeds it. That's the whole of how these things end."
- `conversations.rumors.resume.who_then` — e.g. "I've a guess and I'll not say it, because a guess said aloud becomes a second rumour."


```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.rumors.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.rumors.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `rumors.resume.believe_you`, `rumors.resume.who_then`, `rumors.resume.let_it_die`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `discretion`(0.3), `keeping`(1.2), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `rumors.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.rumors.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.thank_you_for_telling`: the villager accepts. Subject `rumors.spreading`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.rumors.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.rumors.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `rumors.resume.believe_you`, `rumors.resume.who_then`, `rumors.resume.let_it_die`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `discretion`(0.3), `leave`(0.6), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.followup.leave_it_with_you   [23 chars]
    en  I'll leave it with you.
    >>  ............................................
    pt  Vou deixar isso com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.rumors.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.leave_it_with_you`: the villager accepts. Subject `rumors.spreading`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.rumors.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.rumors.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.resume.believe_you`, `rumors.resume.who_then`, `rumors.resume.let_it_die` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.rumors.resume.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.leave`: the villager accepts. Subject `rumors.spreading`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.rumors.resume.respond / leave
```

```text
  dialogue.conversations.rumors.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.rumors.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.rumors.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.rumors.resume.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `rumors`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.resume` — e.g. "That thing I said I'd keep. I've kept it, and it has been harder than I expected."


```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.rumors.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.rumors.resume.respond   [15 chars]
    en  So there it is.
    >>  ............................................
    pt  Então é isso.
    >>  ............................................
```


### Button `believe_you` — "I believe you."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `rumors.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.resume.believe_you` — accepted phrasings: "i believe you"; "i take your word for it"; "i do not doubt you"
  - the message must contain one of: `believe`
  - scored words: `believe`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.respond.believe_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.respond.believe_you   [14 chars]
    en  I believe you.
    >>  ............................................
    pt  Eu acredito em você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `rumors.resume.believed`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `rumors.resume.believe_you`)_
- Does: session `turn`
- Then opens: `conversations.arc.rumors.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.believe_you
WHO    VILLAGER — what the player reads after pressing "I believe you."
       spoken on: conversations.arc.rumors.resume.respond, button `believe_you`
       leaves the player on: conversations.arc.rumors.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.believe_you`: the villager accepts. Subject `rumors.spreading`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.resume.believe_you/1   [83 chars]
    en  ...Thank you. I'd worked out what I'd say if you didn't, and I'd rather not use it.
    >>  ............................................
    pt  ...Obrigado. Eu tinha pensado no que diria se você não acreditasse, e prefiro não usar.
    >>  ............................................
  dialogue.conversations.rumors.resume.believe_you/2   [58 chars]
    en  That's worth more than you'd think in a village this size.
    >>  ............................................
    pt  Isso vale mais do que você imagina num vilarejo deste tamanho.
    >>  ............................................
  dialogue.conversations.rumors.resume.believe_you/3   [67 chars]
    en  Good. Because the alternative was me proving a negative all winter.
    >>  ............................................
    pt  Bom. Porque a alternativa era eu provar uma negativa o inverno inteiro.
    >>  ............................................
```


### Button `who_then` — "Then who?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `rumors.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.resume.who_then` — accepted phrasings: "then who was it"; "who else could it have been"; "who do you think it was"
  - scored words: `then`(0.5), `who`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.respond.who_then
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.respond.who_then   [9 chars]
    en  Then who?
    >>  ............................................
    pt  Então quem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.rumors.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.who_then
WHO    VILLAGER — what the player reads after pressing "Then who?"
       spoken on: conversations.arc.rumors.resume.respond, button `who_then`
       leaves the player on: conversations.arc.rumors.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.who_then`: the villager deflects. Subject `rumors.spreading`, polarity `mixed`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.resume.who_then/1   [85 chars]
    en  I've a guess and I'll not say it, because a guess said aloud becomes a second rumour.
    >>  ............................................
    pt  Tenho um palpite e não vou dizer, porque palpite dito vira um segundo boato.
    >>  ............................................
  dialogue.conversations.rumors.resume.who_then/2   [75 chars]
    en  Three people knew. I'll leave the arithmetic to you and not do it out loud.
    >>  ............................................
    pt  Três pessoas sabiam. Deixo a conta pra você e não faço em voz alta.
    >>  ............................................
  dialogue.conversations.rumors.resume.who_then/3   [80 chars]
    en  That's the question that turns one problem into two. I've decided not to ask it.
    >>  ............................................
    pt  É a pergunta que transforma um problema em dois. Decidi não fazer.
    >>  ............................................
```


### Button `let_it_die` — "Let it die, then."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `rumors.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.resume.let_it_die` — accepted phrasings: "let it die then"; "leave it to fade"; "we say nothing more about it"
  - the message must contain one of: `die`
  - scored words: `die`(1.2), `let`(0.4)

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.respond.let_it_die
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.respond.let_it_die   [17 chars]
    en  Let it die, then.
    >>  ............................................
    pt  Então deixe morrer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.rumors.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.let_it_die
WHO    VILLAGER — what the player reads after pressing "Let it die, then."
       spoken on: conversations.arc.rumors.resume.respond, button `let_it_die`
       leaves the player on: conversations.arc.rumors.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.let_it_die`: the villager accepts. Subject `rumors.spreading`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.resume.let_it_die/1   [77 chars]
    en  It will, if neither of us feeds it. That's the whole of how these things end.
    >>  ............................................
    pt  Vai morrer, se nenhum de nós alimentar. É assim que essas coisas acabam.
    >>  ............................................
  dialogue.conversations.rumors.resume.let_it_die/2   [69 chars]
    en  Agreed. Three weeks of silence and it's a thing that used to be said.
    >>  ............................................
    pt  Combinado. Três semanas de silêncio e vira coisa que se dizia.
    >>  ............................................
  dialogue.conversations.rumors.resume.let_it_die/3   [54 chars]
    en  That's the plan. Ask me in a season whether it worked.
    >>  ............................................
    pt  É o plano. Me pergunte numa estação se funcionou.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.rumors.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.rumors.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.rumors.resume.respond.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.rumors.resume.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.leave`: the villager accepts. Subject `rumors.spreading`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.rumors.resume.followup / leave
```

> Written out in full under **`conversations.arc.rumors.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.rumors.followup`

**Reached from 4 route(s):** `conversations.scene.rumors.one_i_will_not_pass_on.respond` / `back_the_silence`; `conversations.scene.rumors.one_i_will_not_pass_on.respond` / `ask_if_it_matters`; `conversations.scene.rumors.the_correction.respond` / `ask_if_it_helped`; `conversations.scene.rumors.the_correction.respond` / `respect_the_admission`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.rumors.one_i_will_not_pass_on.explained` — e.g. "One household, badly, and for about two years, and the person telling it does not think of it as harm at all."
- `conversations.scene.rumors.one_i_will_not_pass_on.steadied` — e.g. "That is the plan and it is harder than it sounds, because holding one makes you look as though you are hiding something."
- `conversations.scene.rumors.the_correction.acknowledged` — e.g. "It is the only thing that has ever made me careful. Being told to be careful did nothing for twenty years."
- `conversations.scene.rumors.the_correction.answered` — e.g. "About a third as far and about four times as slowly. That is the arithmetic of it and it has never once been different."


```text
POOL   dialogue key: dialogue.conversations.scene.rumors.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.rumors.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.rumors.followup   [26 chars]
    en  Anything else going round?
    >>  ............................................
    pt  Mais alguma coisa circulando?
    >>  ............................................
```


### Button `leave` — "That's the talk, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:rumors.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.followup.leave   [22 chars]
    en  That's the talk, then.
    >>  ............................................
    pt  É essa a conversa, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.leaving
WHO    VILLAGER — what the player reads after pressing "That's the talk, then."
       spoken on: conversations.scene.rumors.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.scene.leaving`: the villager accepts. Subject `rumors.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.rumors.one_i_will_not_pass_on.respond / leave; conversations.scene.rumors.the_correction.respond / leave
```

```text
  dialogue.conversations.scene.rumors.leaving/1   [28 chars]
    en  You did not hear it from me.
    >>  ............................................
    pt  Você não ouviu de mim.
    >>  ............................................
  dialogue.conversations.scene.rumors.leaving/2   [33 chars]
    en  Right. That is as far as it goes.
    >>  ............................................
    pt  Certo. É até aí que vai.
    >>  ............................................
  dialogue.conversations.scene.rumors.leaving/3   [23 chars]
    en  Let it die where it is.
    >>  ............................................
    pt  Deixe morrer onde está.
    >>  ............................................
```

---


## `conversations.scene.rumors.one_i_will_not_pass_on.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `rumors`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.rumors.one_i_will_not_pass_on` — e.g. "There is one doing the rounds that I have heard four times and have not repeated once, and I intend to keep that record."


```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.rumors.one_i_will_not_pass_on.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond   [19 chars]
    en  What's going round.
    >>  ............................................
    pt  O que está circulando.
    >>  ............................................
```


### Button `back_the_silence` — "Then let it end with you."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `rumors.one_i_will_not_pass_on.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.rumors.one_i_will_not_pass_on.back_the_silence` — accepted phrasings: "then let it end with you"; "then let it end with you"; "let it end there rather than spread"
  - the message must contain one of: `end`, `spread`
  - scored words: `end`(1.8), `spread`(1.8), `let`(0.8), `rather`(0.8), `than`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond.back_the_silence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.one_i_will_not_pass_on.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond.back_the_silence   [25 chars]
    en  Then let it end with you.
    >>  ............................................
    pt  Então deixe acabar com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.rumors.stopped`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `rumors.withheld`)_
- Does: session `turn`
- Then opens: `conversations.scene.rumors.followup`
- …where the player's next choices will be: "That's the talk, then."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on.steadied
WHO    VILLAGER — what the player reads after pressing "Then let it end with you."
       spoken on: conversations.scene.rumors.one_i_will_not_pass_on.respond, button `back_the_silence`
       leaves the player on: conversations.scene.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.one_i_will_not_pass_on.open.steadied`: the villager accepts. Subject `rumors.withheld`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:rumors` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.steadied/1   [120 chars]
    en  That is the plan and it is harder than it sounds, because holding one makes you look as though you are hiding something.
    >>  ............................................
    pt  É o plano e é mais difícil do que parece, porque segurar um faz você parecer que está escondendo algo.
    >>  ............................................
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.steadied/2   [129 chars]
    en  Four people have tried to get it out of me this week by pretending they already knew. That trick works about a third of the time.
    >>  ............................................
    pt  Quatro pessoas tentaram arrancar de mim esta semana fingindo que já sabiam. Esse truque funciona em um terço das vezes.
    >>  ............................................
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.steadied/3   [107 chars]
    en  Yes. A rumour that reaches nine people is a fact by Thursday, and it does not need to be true to get there.
    >>  ............................................
    pt  Sim. Um boato que chega a nove pessoas vira fato até quinta, e não precisa ser verdade para chegar lá.
    >>  ............................................
```


### Button `ask_if_it_matters` — "Would it hurt anybody?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.one_i_will_not_pass_on.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.rumors.one_i_will_not_pass_on.ask_if_it_matters` — accepted phrasings: "would it hurt anybody"; "would it hurt anybody"; "does it do any harm"
  - the message must contain one of: `hurt`, `harm`
  - scored words: `hurt`(1.8), `harm`(1.8), `anybody`(0.8), `does`(0.8), `any`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond.ask_if_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.one_i_will_not_pass_on.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond.ask_if_it_matters   [22 chars]
    en  Would it hurt anybody?
    >>  ............................................
    pt  Isso machucaria alguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `rumors.withheld`)_
- Does: session `turn`
- Then opens: `conversations.scene.rumors.followup`
- …where the player's next choices will be: "That's the talk, then."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on.explained
WHO    VILLAGER — what the player reads after pressing "Would it hurt anybody?"
       spoken on: conversations.scene.rumors.one_i_will_not_pass_on.respond, button `ask_if_it_matters`
       leaves the player on: conversations.scene.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.one_i_will_not_pass_on.open.explained`: the villager explains. Subject `rumors.withheld`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:rumors` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.explained/1   [109 chars]
    en  One household, badly, and for about two years, and the person telling it does not think of it as harm at all.
    >>  ............................................
    pt  Uma casa, muito, e por uns dois anos, e quem conta não pensa nisso como dano nenhum.
    >>  ............................................
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.explained/2   [119 chars]
    en  It would end a thing that took four years to build. That is what a rumour costs and the cost is always somebody else's.
    >>  ............................................
    pt  Encerraria uma coisa que levou quatro anos para construir. É isso que um boato custa, e o custo é sempre de outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.explained/3   [93 chars]
    en  That is the only question worth asking about one, and almost nobody asks it before repeating.
    >>  ............................................
    pt  É a única pergunta que vale a pena fazer sobre um boato, e quase ninguém faz antes de repetir.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `rumors.one_i_will_not_pass_on.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.one_i_will_not_pass_on.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on.respond.leave   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.scene.rumors.one_i_will_not_pass_on.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.scene.leaving`: the villager accepts. Subject `rumors.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.rumors.followup / leave; conversations.scene.rumors.the_correction.respond / leave
```

> Written out in full under **`conversations.scene.rumors.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.rumors.the_correction.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `rumors`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.rumors.the_correction` — e.g. "A thing everybody believed last winter turned out to be nothing, and about four people have corrected themselves out loud."


```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.rumors.the_correction.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.rumors.the_correction.respond   [32 chars]
    en  Something that turned out wrong.
    >>  ............................................
    pt  Algo que se mostrou errado.
    >>  ............................................
```


### Button `ask_if_it_helped` — "Did the correction reach everyone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.the_correction.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.rumors.the_correction.ask_if_it_helped` — accepted phrasings: "did the correction reach everyone"; "did the correction reach everyone"; "does a correction travel as far"
  - the message must contain one of: `correction`, `travel`
  - scored words: `correction`(1.8), `travel`(1.8), `reach`(0.8), `everyone`(0.8), `does`(0.8), `far`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction.respond.ask_if_it_helped
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.the_correction.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.the_correction.respond.ask_if_it_helped   [34 chars]
    en  Did the correction reach everyone?
    >>  ............................................
    pt  A correção chegou a todos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `rumors.corrected`)_
- Does: session `turn`
- Then opens: `conversations.scene.rumors.followup`
- …where the player's next choices will be: "That's the talk, then."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction.answered
WHO    VILLAGER — what the player reads after pressing "Did the correction reach everyone?"
       spoken on: conversations.scene.rumors.the_correction.respond, button `ask_if_it_helped`
       leaves the player on: conversations.scene.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.the_correction.open.answered`: the villager explains. Subject `rumors.corrected`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:rumors` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.rumors.the_correction.answered/1   [119 chars]
    en  About a third as far and about four times as slowly. That is the arithmetic of it and it has never once been different.
    >>  ............................................
    pt  Cerca de um terço da distância e umas quatro vezes mais devagar. É essa a conta e nunca foi diferente.
    >>  ............................................
  dialogue.conversations.scene.rumors.the_correction.answered/2   [124 chars]
    en  The people who repeated it loudest were the quietest about the correction, which I have decided is human rather than wicked.
    >>  ............................................
    pt  Quem repetiu mais alto foi quem ficou mais calado na correção, o que eu decidi que é humano e não maldade.
    >>  ............................................
  dialogue.conversations.scene.rumors.the_correction.answered/3   [120 chars]
    en  It reached the household concerned, which is the only part that counts, and it took a neighbour walking there to say it.
    >>  ............................................
    pt  Chegou à casa envolvida, que é a única parte que conta, e precisou de um vizinho caminhar até lá para dizer.
    >>  ............................................
```


### Button `respect_the_admission` — "Admitting you repeated one is rare."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `rumors.the_correction.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.rumors.the_correction.respect_the_admission` — accepted phrasings: "admitting you repeated one is rare"; "admitting you repeated one is rare"; "few people admit their part in one"
  - the message must contain one of: `admitting`, `admit`, `rare`
  - scored words: `admitting`(1.8), `admit`(1.8), `rare`(1.8), `repeated`(0.8), `one`(0.8), `few`(0.8), `people`(0.8), `their`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction.respond.respect_the_admission
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.the_correction.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.the_correction.respond.respect_the_admission   [35 chars]
    en  Admitting you repeated one is rare.
    >>  ............................................
    pt  Admitir que repetiu um é raro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +2  _(recorded under topic `rumors.corrected`)_
- Does: session `turn`
- Then opens: `conversations.scene.rumors.followup`
- …where the player's next choices will be: "That's the talk, then."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction.acknowledged
WHO    VILLAGER — what the player reads after pressing "Admitting you repeated one is rare."
       spoken on: conversations.scene.rumors.the_correction.respond, button `respect_the_admission`
       leaves the player on: conversations.scene.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.the_correction.open.acknowledged`: the villager accepts. Subject `rumors.corrected`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:rumors` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.rumors.the_correction.acknowledged/1   [106 chars]
    en  It is the only thing that has ever made me careful. Being told to be careful did nothing for twenty years.
    >>  ............................................
    pt  É a única coisa que já me deixou cuidadosa. Me mandarem ser cuidadosa não fez nada por vinte anos.
    >>  ............................................
  dialogue.conversations.scene.rumors.the_correction.acknowledged/2   [93 chars]
    en  Thank you. I have said it twice and both times to somebody I wanted to be honest in front of.
    >>  ............................................
    pt  Obrigada. Já disse isso duas vezes, e as duas para alguém diante de quem eu queria ser honesta.
    >>  ............................................
  dialogue.conversations.scene.rumors.the_correction.acknowledged/3   [104 chars]
    en  The person it was about is dead now, which means it can never be put right, and that is the part I keep.
    >>  ............................................
    pt  A pessoa de quem falavam já morreu, o que significa que nunca poderá ser reparado, e é essa a parte que fica comigo.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `rumors.the_correction.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.rumors.the_correction.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.rumors.the_correction.respond.leave   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.scene.rumors.the_correction.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.scene.leaving`: the villager accepts. Subject `rumors.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.rumors.followup / leave; conversations.scene.rumors.one_i_will_not_pass_on.respond / leave
```

> Written out in full under **`conversations.scene.rumors.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.rumors.about_player`

**Reached from 1 route(s):** `conversations.topic.rumors.more` / `anything_about_me`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.more.anything_about_me` — e.g. "Some. Not unkind, exactly — people wonder where you go and they say so."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.about_player
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.about_player
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.about_player   [66 chars]
    en  That's what's said, and I'd not have told you if you hadn't asked.
    >>  ............................................
    pt  É o que se diz, e eu não teria contado se você não perguntasse.
    >>  ............................................
```


### Button `thank_you_for_saying` — "Thank you for telling me straight."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `rumors.more.anything_about_me`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.about_player.thanks` — accepted phrasings: "thank you for telling me straight"; "i would rather know"; "thanks for being straight about it"
  - the message must contain one of: `straight`
  - scored words: `straight`(1.2), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.about_player.thank_you_for_saying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.about_player
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.about_player.thank_you_for_saying   [34 chars]
    en  Thank you for telling me straight.
    >>  ............................................
    pt  Obrigado por me contar direto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `rumors.about_player.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `rumors.about_player.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.about_player.thanks
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me straight."
       spoken on: conversations.topic.rumors.about_player, button `thank_you_for_saying`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.about_player.thanks`: the villager accepts. Subject `rumors.about_player`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.about_player.thanks/1   [57 chars]
    en  You'd rather know than be the last to. I'd want the same.
    >>  ............................................
    pt  Você prefere saber a ser o último a saber. Eu ia querer o mesmo.
    >>  ............................................
  dialogue.conversations.rumors.about_player.thanks/2   [60 chars]
    en  It's easier to say it than to watch you not know it. Barely.
    >>  ............................................
    pt  É mais fácil dizer do que ver você sem saber. Por pouco.
    >>  ............................................
  dialogue.conversations.rumors.about_player.thanks/3   [63 chars]
    en  Somebody had to. I'd sooner it was me than someone enjoying it.
    >>  ............................................
    pt  Alguém tinha que contar. Prefiro que seja eu do que alguém se divertindo.
    >>  ............................................
```


### Button `set_it_right` — "Will you set it right if it comes up?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `rumors.more.anything_about_me`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.about_player.correct` — accepted phrasings: "will you set it right"; "will you correct it for me"; "would you speak up if it comes up"
  - the message must contain one of: `correct`
  - scored words: `right`(0.5), `correct`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.about_player.set_it_right
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.about_player
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.about_player.set_it_right   [37 chars]
    en  Will you set it right if it comes up?
    >>  ............................................
    pt  Você vai corrigir isso se aparecer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.about_player.correct`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2  _(recorded under topic `rumors.about_player.correct`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.about_player.correct
WHO    VILLAGER — what the player reads after pressing "Will you set it right if it comes up?"
       spoken on: conversations.topic.rumors.about_player, button `set_it_right`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.about_player.correct`: the villager accepts. Subject `rumors.about_player`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.about_player.correct/1   [78 chars]
    en  I will, and I'll be believed, which is worth more than you defending yourself.
    >>  ............................................
    pt  Vou, e vão acreditar em mim, o que vale mais do que você se defender.
    >>  ............................................
  dialogue.conversations.rumors.about_player.correct/2   [75 chars]
    en  I'll say what I've seen. That's all I can honestly do and it usually holds.
    >>  ............................................
    pt  Vou dizer o que vi. É tudo que posso fazer honestamente e costuma bastar.
    >>  ............................................
  dialogue.conversations.rumors.about_player.correct/3   [69 chars]
    en  It is. It's slower than you'd like and it works better than shouting.
    >>  ............................................
    pt  É sim. É mais lento do que você gostaria e funciona melhor que gritar.
    >>  ............................................
```


### Button `leave` — "I'll live with it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.more.anything_about_me` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.about_player.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.about_player
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.about_player.leave   [18 chars]
    en  I'll live with it.
    >>  ............................................
    pt  Vou conviver com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.about_player.leave
WHO    VILLAGER — what the player reads after pressing "I'll live with it."
       spoken on: conversations.topic.rumors.about_player, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.about_player.leave`: the villager accepts. Subject `rumors.about_player`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.about_player.leave/1   [37 chars]
    en  You will. Most of it dies on its own.
    >>  ............................................
    pt  Vai. Quase tudo morre sozinho.
    >>  ............................................
  dialogue.conversations.rumors.about_player.leave/2   [30 chars]
    en  So I've found. That's the way.
    >>  ............................................
    pt  Foi o que eu vi. É esse o jeito.
    >>  ............................................
  dialogue.conversations.rumors.about_player.leave/3   [15 chars]
    en  Sensible, %1$s.
    >>  ............................................
    pt  Sensato, %1$s.
    >>  ............................................
```

---


## `conversations.topic.rumors.doubt`

**Reached from 1 route(s):** `conversations.topic.rumors.more` / `do_you_believe_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.more.do_you_believe_it` — e.g. "Half. The shape of it, yes. The details have grown legs since I first heard it."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.doubt
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.doubt
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.doubt   [39 chars]
    en  That's how much of it I'd stand behind.
    >>  ............................................
    pt  É o quanto disso eu defenderia.
    >>  ............................................
```


### Button `then_dont_pass_it` — "Then perhaps don't pass it on."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `rumors.more.do_you_believe_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.doubt.hold` — accepted phrasings: "then perhaps do not pass it on"; "maybe keep that one to yourself"; "best not to spread it then"
  - the message must contain one of: `pass`
  - scored words: `pass`(1.2), `perhaps`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.doubt.then_dont_pass_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.doubt
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.doubt.then_dont_pass_it   [30 chars]
    en  Then perhaps don't pass it on.
    >>  ............................................
    pt  Então talvez não passe adiante.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.doubt.hold`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `rumors.doubt.hold`)_
- Does: arc `rumors` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.doubt.hold
WHO    VILLAGER — what the player reads after pressing "Then perhaps don't pass it on."
       spoken on: conversations.topic.rumors.doubt, button `then_dont_pass_it`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.doubt.hold`: the villager accepts. Subject `rumors.doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.doubt.hold/1   [78 chars]
    en  ...You're right, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  dialogue.conversations.rumors.doubt.hold/2   [66 chars]
    en  I'll hold it, then, and see whether it turns up from anybody else.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém.
    >>  ............................................
  dialogue.conversations.rumors.doubt.hold/3   [62 chars]
    en  That's the sensible line. It's also the one nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém aqui traça.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. I've told it all week and I've felt worse each time without knowing why.
    >>  ............................................
    pt  ...Você tem razão. Contei a semana toda e me senti pior a cada vez sem saber por quê.
    >>  ............................................
  anxious.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and I'd rather be the one who held it than the one who was right.
    >>  ............................................
    pt  Então eu seguro, e prefiro ser quem segurou a ser quem estava certo.
    >>  ............................................
  anxious.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody draws, and I'd like to be the exception.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém traça, e eu queria ser a exceção.
    >>  ............................................
  athletic.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. Forty years of not asking myself that is a long habit to notice.
    >>  ............................................
    pt  ...Você tem razão. Quarenta anos sem me perguntar isso é um hábito longo de notar.
    >>  ............................................
  athletic.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. If it's true it will find its own way here; they always do.
    >>  ............................................
    pt  Vou segurar. Se for verdade acha o caminho até aqui; sempre acham.
    >>  ............................................
  athletic.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. I've watched what happens to the people who never draw it.
    >>  ............................................
    pt  É a linha sensata. Já vi o que acontece com quem nunca a traça.
    >>  ............................................
  confident.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  confident.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém.
    >>  ............................................
  confident.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém aqui traça.
    >>  ............................................
  crabby.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  crabby.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém.
    >>  ............................................
  crabby.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém aqui traça.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, %1$s, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, %1$s, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then. If it's true it will reach me from somebody else.
    >>  ............................................
    pt  Então eu seguro. Se for verdade vai chegar a mim por outra pessoa.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line, and it's the one nobody here draws. I'll try to.
    >>  ............................................
    pt  É a linha sensata, e é a que ninguém aqui traça. Vou tentar.
    >>  ............................................
  flirty.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, %1$s, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, %1$s, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  flirty.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then. If it's true it will reach me from somebody else.
    >>  ............................................
    pt  Então eu seguro. Se for verdade vai chegar a mim por outra pessoa.
    >>  ............................................
  flirty.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line, and it's the one nobody here draws. I'll try to.
    >>  ............................................
    pt  É a linha sensata, e é a que ninguém aqui traça. Vou tentar.
    >>  ............................................
  friendly.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, %1$s, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, %1$s, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  friendly.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then. If it's true it will reach me from somebody else.
    >>  ............................................
    pt  Então eu seguro. Se for verdade vai chegar a mim por outra pessoa.
    >>  ............................................
  friendly.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line, and it's the one nobody here draws. I'll try to.
    >>  ............................................
    pt  É a linha sensata, e é a que ninguém aqui traça. Vou tentar.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. I've told it all week and I've felt worse each time without knowing why.
    >>  ............................................
    pt  ...Você tem razão. Contei a semana toda e me senti pior a cada vez sem saber por quê.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and I'd rather be the one who held it than the one who was right.
    >>  ............................................
    pt  Então eu seguro, e prefiro ser quem segurou a ser quem estava certo.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody draws, and I'd like to be the exception.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém traça, e eu queria ser a exceção.
    >>  ............................................
  greedy.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  greedy.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém.
    >>  ............................................
  greedy.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém aqui traça.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right, and I've been telling it all week without asking myself that.
    >>  ............................................
    pt  ...Você tem razão, e venho contando a semana toda sem me perguntar isso.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém aqui traça.
    >>  ............................................
  introverted.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. A week of telling it.
    >>  ............................................
    pt  ...Você tem razão. Uma semana contando.
    >>  ............................................
  introverted.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. See if it comes from elsewhere.
    >>  ............................................
    pt  Vou segurar. Ver se vem de outro lugar.
    >>  ............................................
  introverted.dialogue.conversations.rumors.doubt.hold/3
    en  The sensible line. Nobody draws it.
    >>  ............................................
    pt  A linha sensata. Ninguém traça.
    >>  ............................................
  lazy.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. Forty years of not asking myself that is a long habit to notice.
    >>  ............................................
    pt  ...Você tem razão. Quarenta anos sem me perguntar isso é um hábito longo de notar.
    >>  ............................................
  lazy.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. If it's true it will find its own way here; they always do.
    >>  ............................................
    pt  Vou segurar. Se for verdade acha o caminho até aqui; sempre acham.
    >>  ............................................
  lazy.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. I've watched what happens to the people who never draw it.
    >>  ............................................
    pt  É a linha sensata. Já vi o que acontece com quem nunca a traça.
    >>  ............................................
  odd.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. A week of telling it.
    >>  ............................................
    pt  ...Você tem razão. Uma semana contando.
    >>  ............................................
  odd.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. See if it comes from elsewhere.
    >>  ............................................
    pt  Vou segurar. Ver se vem de outro lugar.
    >>  ............................................
  odd.dialogue.conversations.rumors.doubt.hold/3
    en  The sensible line. Nobody draws it.
    >>  ............................................
    pt  A linha sensata. Ninguém traça.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. Forty years of not asking myself that is a long habit to notice.
    >>  ............................................
    pt  ...Você tem razão. Quarenta anos sem me perguntar isso é um hábito longo de notar.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. If it's true it will find its own way here; they always do.
    >>  ............................................
    pt  Vou segurar. Se for verdade acha o caminho até aqui; sempre acham.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. I've watched what happens to the people who never draw it.
    >>  ............................................
    pt  É a linha sensata. Já vi o que acontece com quem nunca a traça.
    >>  ............................................
  peppy.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right! And I've been telling it all week without once asking myself that.
    >>  ............................................
    pt  ...Você tem razão! E venho contando a semana toda sem me perguntar isso nenhuma vez.
    >>  ............................................
  peppy.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else. How novel.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém. Que novidade.
    >>  ............................................
  peppy.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one absolutely nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que absolutamente ninguém aqui traça.
    >>  ............................................
  playful.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right! And I've been telling it all week without once asking myself that.
    >>  ............................................
    pt  ...Você tem razão! E venho contando a semana toda sem me perguntar isso nenhuma vez.
    >>  ............................................
  playful.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else. How novel.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém. Que novidade.
    >>  ............................................
  playful.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one absolutely nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que absolutamente ninguém aqui traça.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. Forty years of not asking myself that is a long habit to notice.
    >>  ............................................
    pt  ...Você tem razão. Quarenta anos sem me perguntar isso é um hábito longo de notar.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. If it's true it will find its own way here; they always do.
    >>  ............................................
    pt  Vou segurar. Se for verdade acha o caminho até aqui; sempre acham.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. I've watched what happens to the people who never draw it.
    >>  ............................................
    pt  É a linha sensata. Já vi o que acontece com quem nunca a traça.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. I've told it all week and I've felt worse each time without knowing why.
    >>  ............................................
    pt  ...Você tem razão. Contei a semana toda e me senti pior a cada vez sem saber por quê.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and I'd rather be the one who held it than the one who was right.
    >>  ............................................
    pt  Então eu seguro, e prefiro ser quem segurou a ser quem estava certo.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one nobody draws, and I'd like to be the exception.
    >>  ............................................
    pt  É a linha sensata. Também é a que ninguém traça, e eu queria ser a exceção.
    >>  ............................................
  shy.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right. A week of telling it.
    >>  ............................................
    pt  ...Você tem razão. Uma semana contando.
    >>  ............................................
  shy.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it. See if it comes from elsewhere.
    >>  ............................................
    pt  Vou segurar. Ver se vem de outro lugar.
    >>  ............................................
  shy.dialogue.conversations.rumors.doubt.hold/3
    en  The sensible line. Nobody draws it.
    >>  ............................................
    pt  A linha sensata. Ninguém traça.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right! And I've been telling it all week without once asking myself that.
    >>  ............................................
    pt  ...Você tem razão! E venho contando a semana toda sem me perguntar isso nenhuma vez.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else. How novel.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém. Que novidade.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one absolutely nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que absolutamente ninguém aqui traça.
    >>  ............................................
  witty.dialogue.conversations.rumors.doubt.hold/1
    en  ...You're right! And I've been telling it all week without once asking myself that.
    >>  ............................................
    pt  ...Você tem razão! E venho contando a semana toda sem me perguntar isso nenhuma vez.
    >>  ............................................
  witty.dialogue.conversations.rumors.doubt.hold/2
    en  I'll hold it, then, and see whether it turns up from anybody else. How novel.
    >>  ............................................
    pt  Então eu seguro, e vejo se aparece de mais alguém. Que novidade.
    >>  ............................................
  witty.dialogue.conversations.rumors.doubt.hold/3
    en  That's the sensible line. It's also the one absolutely nobody here draws.
    >>  ............................................
    pt  É a linha sensata. Também é a que absolutamente ninguém aqui traça.
    >>  ............................................
```

</details>


### Button `who_would_know` — "Who would actually know?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.more.do_you_believe_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.doubt.who_would` — accepted phrasings: "who would actually know"; "who could say for certain"; "who has it first hand"
  - the message must contain one of: `actually`
  - scored words: `actually`(1.2), `sure`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.doubt.who_would_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.doubt
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.doubt.who_would_know   [24 chars]
    en  Who would actually know?
    >>  ............................................
    pt  Quem realmente saberia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.doubt.who_would
WHO    VILLAGER — what the player reads after pressing "Who would actually know?"
       spoken on: conversations.topic.rumors.doubt, button `who_would_know`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.doubt.who_would`: the villager explains. Subject `rumors.doubt`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.doubt.who_would/1   [70 chars]
    en  The people in it, and they've said nothing, which tells you something.
    >>  ............................................
    pt  As pessoas envolvidas, e elas não disseram nada, o que já diz algo.
    >>  ............................................
  dialogue.conversations.rumors.doubt.who_would/2   [63 chars]
    en  Nobody I'd trust twice. That's why I said it the way I said it.
    >>  ............................................
    pt  Ninguém em quem eu confiaria duas vezes. Por isso falei do jeito que falei.
    >>  ............................................
  dialogue.conversations.rumors.doubt.who_would/3   [58 chars]
    en  The trader, if it came in on the road. Ask before he goes.
    >>  ............................................
    pt  O mercador, se veio pela estrada. Pergunte antes que ele vá.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.more.do_you_believe_it` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.doubt.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.doubt
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.doubt.leave   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.doubt.leave
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.topic.rumors.doubt, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.doubt.leave`: the villager accepts. Subject `rumors.doubt`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.doubt.leave/1   [8 chars]
    en  Just so.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
  dialogue.conversations.rumors.doubt.leave/2   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  dialogue.conversations.rumors.doubt.leave/3   [10 chars]
    en  On you go.
    >>  ............................................
    pt  Siga em frente.
    >>  ............................................
```

---


## `conversations.topic.rumors.followup`

**Reached from 4 route(s):** `conversations.topic.rumors.respond` / `ask_source`; `conversations.topic.rumors.respond` / `challenge`; `conversations.topic.rumors.respond` / `challenge`; `conversations.topic.rumors.respond` / `listen`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.challenge.trader` — e.g. "Careful. Half the things I know, I know because nobody checked them either."
- `conversations.rumors.respond.ask_source` — e.g. "...Fair question. Second-hand, if I'm honest. Maybe third."
- `conversations.rumors.respond.challenge` — e.g. "...No. It doesn't, does it. I repeated it without thinking."
- `conversations.rumors.respond.listen` — e.g. "Right. Well. There's not much more, when I say it aloud."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.followup   [28 chars]
    en  So. What do we do with that.
    >>  ............................................
    pt  Então. O que fazemos com isso.
    >>  ............................................
```


### Button `promise_discretion` — "It stops with me."

*stance family `restraint` · tone `plain` · answers the beat(s) `rumors.challenge.trader.to.rumors`, `rumors.respond.ask_source.to.rumors`, `rumors.respond.challenge.to.rumors`, `rumors.respond.listen.to.rumors`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.followup.promise_discretion` — accepted phrasings: "it stops with me"; "i will not repeat it"; "your secret is safe"
  - the message must contain one of: `stops`, `repeat`, `secret`
  - scored words: `stops`(1.5), `repeat`(1.2), `secret`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.followup.promise_discretion
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.followup.promise_discretion   [17 chars]
    en  It stops with me.
    >>  ............................................
    pt  Isso morre comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `rumors.followup.promise_discretion`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +6  _(recorded under topic `rumors.followup.promise_discretion`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.followup.promise_discretion
WHO    VILLAGER — what the player reads after pressing "It stops with me."
       spoken on: conversations.topic.rumors.followup, button `promise_discretion`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.followup.promise_discretion.terminal`: the villager accepts. Subject `rumors.spreading`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.rumors.followup.promise_discretion/1   [46 chars]
    en  ...Thank you. That's not nothing, around here.
    >>  ............................................
    pt  ...Obrigado. Isso não é pouca coisa, por aqui.
    >>  ............................................
  dialogue.conversations.rumors.followup.promise_discretion/2   [59 chars]
    en  It stops with you. Good. I'd rather it had stopped with me.
    >>  ............................................
    pt  Morre com você. Bom. Eu preferia que tivesse morrido comigo.
    >>  ............................................
  dialogue.conversations.rumors.followup.promise_discretion/3   [56 chars]
    en  I'll hold you to that, %1$s. People here rarely mean it.
    >>  ............................................
    pt  Vou cobrar, %1$s. As pessoas aqui raramente falam sério.
    >>  ............................................
```


### Button `let_it_go` — "Best forgotten, that."

*stance family `restraint` · tone `plain` · answers the beat(s) `rumors.challenge.trader.to.rumors`, `rumors.respond.ask_source.to.rumors`, `rumors.respond.challenge.to.rumors`, `rumors.respond.listen.to.rumors`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.followup.let_it_go` — accepted phrasings: "best forgotten"; "let us forget it"; "we should drop it"
  - the message must contain one of: `forgotten`, `forget`, `drop`
  - scored words: `forgotten`(1.5), `forget`(1.2), `drop`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.followup.let_it_go
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.followup.let_it_go   [21 chars]
    en  Best forgotten, that.
    >>  ............................................
    pt  Melhor esquecer isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.followup.let_it_go`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +1  _(recorded under topic `rumors.followup.let_it_go`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.followup.let_it_go
WHO    VILLAGER — what the player reads after pressing "Best forgotten, that."
       spoken on: conversations.topic.rumors.followup, button `let_it_go`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.followup.let_it_go.terminal`: the villager accepts. Subject `rumors.spreading`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.rumors.followup.let_it_go/1   [38 chars]
    en  So it is. Best forgotten. I'll try to.
    >>  ............................................
    pt  É assim mesmo. Melhor esquecer. Vou tentar.
    >>  ............................................
  dialogue.conversations.rumors.followup.let_it_go/2   [41 chars]
    en  You're right. It doesn't deserve the air.
    >>  ............................................
    pt  Você tem razão. Não merece o ar.
    >>  ............................................
  dialogue.conversations.rumors.followup.let_it_go/3   [47 chars]
    en  Good. Let it die at this well and not the next.
    >>  ............................................
    pt  Bom. Que morra neste poço e não no próximo.
    >>  ............................................
```


### Button `encourage_spread` — "Who else knows?"

*stance family `encouragement` · tone `plain` · answers the beat(s) `rumors.challenge.trader.to.rumors`, `rumors.respond.ask_source.to.rumors`, `rumors.respond.challenge.to.rumors`, `rumors.respond.listen.to.rumors`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.followup.encourage_spread` — accepted phrasings: "who else knows"; "who else have you told"; "does anyone else know"
  - the message must contain one of: `knows`, `else`, `told`
  - scored words: `knows`(1.5), `else`(1.2), `told`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.followup.encourage_spread
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.followup.encourage_spread   [15 chars]
    en  Who else knows?
    >>  ............................................
    pt  Quem mais sabe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `rumors.followup.encourage_spread`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust -4, tension +3  _(recorded under topic `rumors.followup.encourage_spread`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.followup.encourage_spread
WHO    VILLAGER — what the player reads after pressing "Who else knows?"
       spoken on: conversations.topic.rumors.followup, button `encourage_spread`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.followup.encourage_spread.terminal`: the villager accepts. Subject `rumors.spreading`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.rumors.followup.encourage_spread/1   [45 chars]
    en  ...Who else. That's the wrong question, %1$s.
    >>  ............................................
    pt  ...Quem mais. Essa é a pergunta errada, %1$s.
    >>  ............................................
  dialogue.conversations.rumors.followup.encourage_spread/2   [58 chars]
    en  I'd rather not add to it. And now I wish I'd said nothing.
    >>  ............................................
    pt  Prefiro não aumentar isso. E agora queria não ter dito nada.
    >>  ............................................
  dialogue.conversations.rumors.followup.encourage_spread/3   [53 chars]
    en  You want the list. That tells me something about you.
    >>  ............................................
    pt  Você quer a lista. Isso me diz algo sobre você.
    >>  ............................................
```


### Button `ask_about_it` — "Before I go — what do you make of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.respond.ask_source.to.rumors`, `rumors.respond.challenge.to.rumors`, `rumors.challenge.trader.to.rumors`, `rumors.respond.listen.to.rumors`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.followup.ask_about_it` — accepted phrasings: "what do you make of it"; "what do you make of all that"; "before i go what do you make of it"
  - the message must contain one of: `make`
  - scored words: `make`(1.2), `of`(0.2), `it`(0.2)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.followup.ask_about_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.followup.ask_about_it   [37 chars]
    en  Before I go — what do you make of it?
    >>  ............................................
    pt  Antes de eu ir — o que você acha disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.rumors.more`
- …where the player's next choices will be: "Is it actually serious?" | "Do you believe it yourself?" | "Is any of it about me?" | "Is this old news?" | "That's enough of it."

```text
POOL   dialogue key: dialogue.conversations.rumors.more
WHO    VILLAGER — what the player reads after pressing "Before I go — what do you make of it?"
       spoken on: conversations.topic.rumors.followup, button `ask_about_it`
       leaves the player on: conversations.topic.rumors.more
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.more.open`: the villager invites. Subject `rumors.talk`, polarity `neutral`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.more/1   [81 chars]
    en  Ask me about it properly and you'll get a straighter answer than the telling was.
    >>  ............................................
    pt  Me pergunte direito e você terá uma resposta mais reta do que a contação foi.
    >>  ............................................
  dialogue.conversations.rumors.more/2   [79 chars]
    en  There's what's said and there's what I think. Ask, and you can have the second.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Pergunte, e você fica com o segundo.
    >>  ............................................
  dialogue.conversations.rumors.more/3   [59 chars]
    en  Go on. I'd rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado sobre isso a ser citado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.rumors.more/1
    en  Ask me properly. I'd feel better having been asked than having simply told you.
    >>  ............................................
    pt  Me pergunte direito. Eu me sentiria melhor tendo sido perguntado do que só tendo contado.
    >>  ............................................
  anxious.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and I've not liked keeping them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e não gostei de mantê-los separados.
    >>  ............................................
  anxious.dialogue.conversations.rumors.more/3
    en  Go on. Being questioned about it would settle something in me.
    >>  ............................................
    pt  Vá em frente. Ser questionado sobre isso resolveria algo em mim.
    >>  ............................................
  athletic.dialogue.conversations.rumors.more/1
    en  Ask me properly. I've heard enough of these to know which parts hold.
    >>  ............................................
    pt  Me pergunte direito. Já ouvi o bastante dessas pra saber quais partes se sustentam.
    >>  ............................................
  athletic.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Forty years teaches you to keep them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Quarenta anos ensinam a separar.
    >>  ............................................
  athletic.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it in ten years' time.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado daqui a dez anos.
    >>  ............................................
  confident.dialogue.conversations.rumors.more/1
    en  Ask me properly and you'll get a straighter answer than the telling was.
    >>  ............................................
    pt  Me pergunte direito e terá uma resposta mais reta do que a contação foi.
    >>  ............................................
  confident.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Ask, and you can have the second.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Pergunte, e fica com o segundo.
    >>  ............................................
  confident.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado.
    >>  ............................................
  crabby.dialogue.conversations.rumors.more/1
    en  Ask me properly and you'll get a straighter answer than the telling was.
    >>  ............................................
    pt  Me pergunte direito e terá uma resposta mais reta do que a contação foi.
    >>  ............................................
  crabby.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Ask, and you can have the second.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Pergunte, e fica com o segundo.
    >>  ............................................
  crabby.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.more/1
    en  Ask me properly, %1$s. You'll get a straighter answer than I gave the last person.
    >>  ............................................
    pt  Me pergunte direito, %1$s. Terá uma resposta mais reta que a da última pessoa.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. You can have the second one.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Você pode ficar com o segundo.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned by you than quoted by somebody else.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado por você a ser citado por outro.
    >>  ............................................
  flirty.dialogue.conversations.rumors.more/1
    en  Ask me properly, %1$s. You'll get a straighter answer than I gave the last person.
    >>  ............................................
    pt  Me pergunte direito, %1$s. Terá uma resposta mais reta que a da última pessoa.
    >>  ............................................
  flirty.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. You can have the second one.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Você pode ficar com o segundo.
    >>  ............................................
  flirty.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned by you than quoted by somebody else.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado por você a ser citado por outro.
    >>  ............................................
  friendly.dialogue.conversations.rumors.more/1
    en  Ask me properly, %1$s. You'll get a straighter answer than I gave the last person.
    >>  ............................................
    pt  Me pergunte direito, %1$s. Terá uma resposta mais reta que a da última pessoa.
    >>  ............................................
  friendly.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. You can have the second one.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Você pode ficar com o segundo.
    >>  ............................................
  friendly.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned by you than quoted by somebody else.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado por você a ser citado por outro.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.more/1
    en  Ask me properly. I'd feel better having been asked than having simply told you.
    >>  ............................................
    pt  Me pergunte direito. Eu me sentiria melhor tendo sido perguntado do que só tendo contado.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and I've not liked keeping them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e não gostei de mantê-los separados.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.more/3
    en  Go on. Being questioned about it would settle something in me.
    >>  ............................................
    pt  Vá em frente. Ser questionado sobre isso resolveria algo em mim.
    >>  ............................................
  greedy.dialogue.conversations.rumors.more/1
    en  Ask me properly and you'll get a straighter answer than the telling was.
    >>  ............................................
    pt  Me pergunte direito e terá uma resposta mais reta do que a contação foi.
    >>  ............................................
  greedy.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Ask, and you can have the second.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Pergunte, e fica com o segundo.
    >>  ............................................
  greedy.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.more/1
    en  Ask me properly and you'll get a straighter answer than the telling was.
    >>  ............................................
    pt  Me pergunte direito e terá uma resposta mais reta do que a contação foi.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Ask, and you can have the second.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Pergunte, e fica com o segundo.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado.
    >>  ............................................
  introverted.dialogue.conversations.rumors.more/1
    en  Ask me properly.
    >>  ............................................
    pt  Me pergunte direito.
    >>  ............................................
  introverted.dialogue.conversations.rumors.more/2
    en  There's what's said, and what I think.
    >>  ............................................
    pt  Tem o que se diz, e o que eu penso.
    >>  ............................................
  introverted.dialogue.conversations.rumors.more/3
    en  Go on. Better questioned than quoted.
    >>  ............................................
    pt  Vá em frente. Melhor questionado que citado.
    >>  ............................................
  lazy.dialogue.conversations.rumors.more/1
    en  Ask me properly. I've heard enough of these to know which parts hold.
    >>  ............................................
    pt  Me pergunte direito. Já ouvi o bastante dessas pra saber quais partes se sustentam.
    >>  ............................................
  lazy.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Forty years teaches you to keep them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Quarenta anos ensinam a separar.
    >>  ............................................
  lazy.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it in ten years' time.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado daqui a dez anos.
    >>  ............................................
  odd.dialogue.conversations.rumors.more/1
    en  Ask me properly.
    >>  ............................................
    pt  Me pergunte direito.
    >>  ............................................
  odd.dialogue.conversations.rumors.more/2
    en  There's what's said, and what I think.
    >>  ............................................
    pt  Tem o que se diz, e o que eu penso.
    >>  ............................................
  odd.dialogue.conversations.rumors.more/3
    en  Go on. Better questioned than quoted.
    >>  ............................................
    pt  Vá em frente. Melhor questionado que citado.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.more/1
    en  Ask me properly. I've heard enough of these to know which parts hold.
    >>  ............................................
    pt  Me pergunte direito. Já ouvi o bastante dessas pra saber quais partes se sustentam.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Forty years teaches you to keep them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Quarenta anos ensinam a separar.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it in ten years' time.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado daqui a dez anos.
    >>  ............................................
  peppy.dialogue.conversations.rumors.more/1
    en  Ask me properly! You'll get a far straighter answer than the telling was, I promise.
    >>  ............................................
    pt  Me pergunte direito! Terá uma resposta muito mais reta que a contação, prometo.
    >>  ............................................
  peppy.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and the second is much better company.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e o segundo é bem melhor companhia.
    >>  ............................................
  peppy.dialogue.conversations.rumors.more/3
    en  Go on, ask. I'd infinitely rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá, pergunte. Prefiro infinitamente ser questionado a ser citado.
    >>  ............................................
  playful.dialogue.conversations.rumors.more/1
    en  Ask me properly! You'll get a far straighter answer than the telling was, I promise.
    >>  ............................................
    pt  Me pergunte direito! Terá uma resposta muito mais reta que a contação, prometo.
    >>  ............................................
  playful.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and the second is much better company.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e o segundo é bem melhor companhia.
    >>  ............................................
  playful.dialogue.conversations.rumors.more/3
    en  Go on, ask. I'd infinitely rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá, pergunte. Prefiro infinitamente ser questionado a ser citado.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.more/1
    en  Ask me properly. I've heard enough of these to know which parts hold.
    >>  ............................................
    pt  Me pergunte direito. Já ouvi o bastante dessas pra saber quais partes se sustentam.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think. Forty years teaches you to keep them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso. Quarenta anos ensinam a separar.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.more/3
    en  Go on. I'd rather be questioned about it than quoted on it in ten years' time.
    >>  ............................................
    pt  Vá em frente. Prefiro ser questionado a ser citado daqui a dez anos.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.more/1
    en  Ask me properly. I'd feel better having been asked than having simply told you.
    >>  ............................................
    pt  Me pergunte direito. Eu me sentiria melhor tendo sido perguntado do que só tendo contado.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and I've not liked keeping them apart.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e não gostei de mantê-los separados.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.more/3
    en  Go on. Being questioned about it would settle something in me.
    >>  ............................................
    pt  Vá em frente. Ser questionado sobre isso resolveria algo em mim.
    >>  ............................................
  shy.dialogue.conversations.rumors.more/1
    en  Ask me properly.
    >>  ............................................
    pt  Me pergunte direito.
    >>  ............................................
  shy.dialogue.conversations.rumors.more/2
    en  There's what's said, and what I think.
    >>  ............................................
    pt  Tem o que se diz, e o que eu penso.
    >>  ............................................
  shy.dialogue.conversations.rumors.more/3
    en  Go on. Better questioned than quoted.
    >>  ............................................
    pt  Vá em frente. Melhor questionado que citado.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.more/1
    en  Ask me properly! You'll get a far straighter answer than the telling was, I promise.
    >>  ............................................
    pt  Me pergunte direito! Terá uma resposta muito mais reta que a contação, prometo.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and the second is much better company.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e o segundo é bem melhor companhia.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.more/3
    en  Go on, ask. I'd infinitely rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá, pergunte. Prefiro infinitamente ser questionado a ser citado.
    >>  ............................................
  witty.dialogue.conversations.rumors.more/1
    en  Ask me properly! You'll get a far straighter answer than the telling was, I promise.
    >>  ............................................
    pt  Me pergunte direito! Terá uma resposta muito mais reta que a contação, prometo.
    >>  ............................................
  witty.dialogue.conversations.rumors.more/2
    en  There's what's said and there's what I think, and the second is much better company.
    >>  ............................................
    pt  Tem o que se diz e tem o que eu penso, e o segundo é bem melhor companhia.
    >>  ............................................
  witty.dialogue.conversations.rumors.more/3
    en  Go on, ask. I'd infinitely rather be questioned about it than quoted on it.
    >>  ............................................
    pt  Vá, pergunte. Prefiro infinitamente ser questionado a ser citado.
    >>  ............................................
```

</details>


### Button `leave` — "I'd rather not know."

*stance family `exit` · tone `plain` · answers the beat(s) `rumors.challenge.trader.to.rumors`, `rumors.respond.ask_source.to.rumors`, `rumors.respond.challenge.to.rumors`, `rumors.respond.listen.to.rumors` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.followup.leave   [20 chars]
    en  I'd rather not know.
    >>  ............................................
    pt  Prefiro não saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'd rather not know."
       spoken on: conversations.topic.rumors.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.respond.leave.terminal`: the villager accepts. Subject `rumors.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.rumors.respond / leave
```

```text
  dialogue.conversations.rumors.respond.leave/1   [20 chars]
    en  Probably wise, %1$s.
    >>  ............................................
    pt  Provavelmente sábio, %1$s.
    >>  ............................................
  dialogue.conversations.rumors.respond.leave/2   [34 chars]
    en  Just so. I shouldn't have started.
    >>  ............................................
    pt  Pois é. Eu não devia ter começado.
    >>  ............................................
  dialogue.conversations.rumors.respond.leave/3   [29 chars]
    en  Fair. Forget I said anything.
    >>  ............................................
    pt  Justo. Esquece que eu falei.
    >>  ............................................
```

---


## `conversations.topic.rumors.harmless`

**Reached from 1 route(s):** `conversations.topic.rumors.more` / `is_it_serious`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.more.is_it_serious` — e.g. "No. It's the sort of thing we say when there's nothing to say."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.harmless
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.harmless
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.harmless   [33 chars]
    en  That's the weight of it, no more.
    >>  ............................................
    pt  É esse o peso disso, nada mais.
    >>  ............................................
```


### Button `glad_its_small` — "I'm glad it's nothing worse."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `rumors.more.is_it_serious`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.harmless.glad` — accepted phrasings: "i am glad it is nothing worse"; "at least it is nothing serious"; "good that it is only that"
  - the message must contain one of: `worse`
  - scored words: `worse`(1.2), `glad`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.harmless.glad_its_small
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.harmless
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.harmless.glad_its_small   [28 chars]
    en  I'm glad it's nothing worse.
    >>  ............................................
    pt  Ainda bem que não é nada pior.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1  _(recorded under topic `rumors.harmless.glad`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.harmless.glad
WHO    VILLAGER — what the player reads after pressing "I'm glad it's nothing worse."
       spoken on: conversations.topic.rumors.harmless, button `glad_its_small`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.harmless.glad`: the villager accepts. Subject `rumors.harmless`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.harmless.glad/1   [68 chars]
    en  So am I. We've had a year of worse and this makes a pleasant change.
    >>  ............................................
    pt  Eu também. Tivemos um ano de coisa pior e isto é uma mudança agradável.
    >>  ............................................
  dialogue.conversations.rumors.harmless.glad/2   [67 chars]
    en  True enough. Small news is the sign of a village that's doing well.
    >>  ............................................
    pt  Bem verdade. Notícia pequena é sinal de vilarejo indo bem.
    >>  ............................................
  dialogue.conversations.rumors.harmless.glad/3   [71 chars]
    en  Careful. Say that too loudly and something worse turns up out of spite.
    >>  ............................................
    pt  Cuidado. Diga isso alto demais e algo pior aparece de birra.
    >>  ............................................
```


### Button `why_repeat_it` — "Then why repeat it at all?"

*stance family `challenge` · tone `plain` · outcome `qualified` · answers the beat(s) `rumors.more.is_it_serious`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.harmless.why` — accepted phrasings: "then why repeat it at all"; "why pass it on then"; "why tell anyone then"
  - the message must contain one of: `repeat`
  - scored words: `repeat`(1.5), `why`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.harmless.why_repeat_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.harmless
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.harmless.why_repeat_it   [26 chars]
    en  Then why repeat it at all?
    >>  ............................................
    pt  Então por que repetir isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1, warmth -1  _(recorded under topic `rumors.harmless.why`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.harmless.why
WHO    VILLAGER — what the player reads after pressing "Then why repeat it at all?"
       spoken on: conversations.topic.rumors.harmless, button `why_repeat_it`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.harmless.why`: the villager qualifys. Subject `rumors.harmless`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.harmless.why/1   [71 chars]
    en  Because a village that only speaks about serious things stops speaking.
    >>  ............................................
    pt  Porque um vilarejo que só fala de coisas sérias para de falar.
    >>  ............................................
  dialogue.conversations.rumors.harmless.why/2   [73 chars]
    en  Fair. I'd say it's how we keep track of each other, but you're not wrong.
    >>  ............................................
    pt  Justo. Eu diria que é como nos acompanhamos, mas você não está errado.
    >>  ............................................
  dialogue.conversations.rumors.harmless.why/3   [71 chars]
    en  Habit, mostly. You've made me hear it, which I'll be cross about later.
    >>  ............................................
    pt  Hábito, principalmente. Você me fez ouvir isso, e vou ficar bravo depois.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.more.is_it_serious` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.harmless.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.harmless
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.harmless.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.harmless.leave
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.rumors.harmless, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.harmless.leave`: the villager accepts. Subject `rumors.harmless`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.harmless.leave/1   [6 chars]
    en  Quite.
    >>  ............................................
    pt  Exato.
    >>  ............................................
  dialogue.conversations.rumors.harmless.leave/2   [15 chars]
    en  Leave it there.
    >>  ............................................
    pt  Deixe por aí.
    >>  ............................................
  dialogue.conversations.rumors.harmless.leave/3   [25 chars]
    en  I'll see you about, %1$s.
    >>  ............................................
    pt  A gente se vê por aí, %1$s.
    >>  ............................................
```

---


## `conversations.topic.rumors.more`

**Reached from 1 route(s):** `conversations.topic.rumors.followup` / `ask_about_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.more` — e.g. "Ask me about it properly and you'll get a straighter answer than the telling was."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.more
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.more
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.more   [60 chars]
    en  Ask about it, then, and I'll tell you what I actually think.
    >>  ............................................
    pt  Então pergunte, e eu digo o que realmente penso.
    >>  ............................................
```


### Button `is_it_serious` — "Is it actually serious?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `rumors.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.more.is_it_serious` — accepted phrasings: "is it actually serious"; "is that a serious matter"; "does it really matter"
  - the message must contain one of: `serious`
  - scored words: `serious`(1.5), `actually`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.more.is_it_serious
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.more.is_it_serious   [23 chars]
    en  Is it actually serious?
    >>  ............................................
    pt  Isso é sério mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.rumors.harmless`
- …where the player's next choices will be: "I'm glad it's nothing worse." | "Then why repeat it at all?" | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.rumors.more.is_it_serious
WHO    VILLAGER — what the player reads after pressing "Is it actually serious?"
       spoken on: conversations.topic.rumors.more, button `is_it_serious`
       leaves the player on: conversations.topic.rumors.harmless
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.more.is_it_serious`: the villager qualifys. Subject `rumors.talk`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.more.is_it_serious/1   [62 chars]
    en  No. It's the sort of thing we say when there's nothing to say.
    >>  ............................................
    pt  Não. É o tipo de coisa que a gente diz quando não tem o que dizer.
    >>  ............................................
  dialogue.conversations.rumors.more.is_it_serious/2   [75 chars]
    en  Not remotely. Nobody's hurt by it, which is my whole test for these things.
    >>  ............................................
    pt  Nem de longe. Ninguém se machuca com isso, que é meu teste pra essas coisas.
    >>  ............................................
  dialogue.conversations.rumors.more.is_it_serious/3   [66 chars]
    en  Less than it sounded. I told it with more weight than it deserves.
    >>  ............................................
    pt  Menos do que soou. Contei com mais peso do que merece.
    >>  ............................................
```


### Button `do_you_believe_it` — "Do you believe it yourself?"

*stance family `challenge` · tone `plain` · outcome `qualified` · answers the beat(s) `rumors.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.more.do_you_believe_it` — accepted phrasings: "do you believe it yourself"; "do you think it is true"; "and do you credit it"
  - the message must contain one of: `believe`
  - scored words: `believe`(1.5), `yourself`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.more.do_you_believe_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.more.do_you_believe_it   [27 chars]
    en  Do you believe it yourself?
    >>  ............................................
    pt  Você mesmo acredita nisso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.rumors.doubt`
- …where the player's next choices will be: "Then perhaps don't pass it on." | "Who would actually know?" | "Understood."

```text
POOL   dialogue key: dialogue.conversations.rumors.more.do_you_believe_it
WHO    VILLAGER — what the player reads after pressing "Do you believe it yourself?"
       spoken on: conversations.topic.rumors.more, button `do_you_believe_it`
       leaves the player on: conversations.topic.rumors.doubt
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.more.do_you_believe_it`: the villager qualifys. Subject `rumors.talk`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.more.do_you_believe_it/1   [79 chars]
    en  Half. The shape of it, yes. The details have grown legs since I first heard it.
    >>  ............................................
    pt  Metade. A forma, sim. Os detalhes ganharam pernas desde que ouvi.
    >>  ............................................
  dialogue.conversations.rumors.more.do_you_believe_it/2   [65 chars]
    en  No. I've told you because you asked, not because I'd swear to it.
    >>  ............................................
    pt  Não. Contei porque você perguntou, não porque eu juraria.
    >>  ............................................
  dialogue.conversations.rumors.more.do_you_believe_it/3   [67 chars]
    en  I did until you asked me plainly. Now I find I'd rather not answer.
    >>  ............................................
    pt  Eu acreditava até você perguntar direto. Agora prefiro não responder.
    >>  ............................................
```


### Button `anything_about_me` — "Is any of it about me?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.more.anything_about_me` — accepted phrasings: "is any of it about me"; "do they say anything about me"; "am i in any of it"
  - scored words: `about`(0.4), `me`(0.4), `anything`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.more.anything_about_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.more.anything_about_me   [22 chars]
    en  Is any of it about me?
    >>  ............................................
    pt  Alguma parte é sobre mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.rumors.about_player`
- …where the player's next choices will be: "Thank you for telling me straight." | "Will you set it right if it comes up?" | "I'll live with it."

```text
POOL   dialogue key: dialogue.conversations.rumors.more.anything_about_me
WHO    VILLAGER — what the player reads after pressing "Is any of it about me?"
       spoken on: conversations.topic.rumors.more, button `anything_about_me`
       leaves the player on: conversations.topic.rumors.about_player
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.more.anything_about_me`: the villager reports. Subject `rumors.talk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.more.anything_about_me/1   [71 chars]
    en  Some. Not unkind, exactly — people wonder where you go and they say so.
    >>  ............................................
    pt  Um pouco. Não maldoso, exatamente — se perguntam aonde você vai e comentam.
    >>  ............................................
  dialogue.conversations.rumors.more.anything_about_me/2   [81 chars]
    en  There is. Somebody thinks you're too free with what you find, and said it loudly.
    >>  ............................................
    pt  Tem. Alguém acha que você é solto demais com o que acha, e disse alto.
    >>  ............................................
  dialogue.conversations.rumors.more.anything_about_me/3   [79 chars]
    en  None at all, and that's worth knowing. You're not interesting enough to invent.
    >>  ............................................
    pt  Nenhuma, e isso vale saber. Você não é interessante o bastante pra inventarem.
    >>  ............................................
```


### Button `is_this_old` — "Is this old news?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.more.is_this_old` — accepted phrasings: "is this old news"; "how long has this been going round"; "is that stale by now"
  - the message must contain one of: `old`
  - scored words: `old`(1.2), `news`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.more.is_this_old
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.more.is_this_old   [17 chars]
    en  Is this old news?
    >>  ............................................
    pt  Isso é notícia velha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.rumors.stale`
- …where the player's next choices will be: "Nobody thought to tell me." | "Then let it die out." | "Right."

```text
POOL   dialogue key: dialogue.conversations.rumors.more.is_this_old
WHO    VILLAGER — what the player reads after pressing "Is this old news?"
       spoken on: conversations.topic.rumors.more, button `is_this_old`
       leaves the player on: conversations.topic.rumors.stale
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.more.is_this_old`: the villager reports. Subject `rumors.talk`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.more.is_this_old/1   [62 chars]
    en  A season, near enough. You're the last person here to hear it.
    >>  ............................................
    pt  Uma estação, quase. Você é a última pessoa aqui a ouvir.
    >>  ............................................
  dialogue.conversations.rumors.more.is_this_old/2   [66 chars]
    en  New this week, which is why it's still being said with any energy.
    >>  ............................................
    pt  Nova desta semana, por isso ainda se fala com alguma energia.
    >>  ............................................
  dialogue.conversations.rumors.more.is_this_old/3   [70 chars]
    en  Old enough that I'd forgotten it and then someone started it up again.
    >>  ............................................
    pt  Velha o bastante pra eu ter esquecido, e aí alguém começou de novo.
    >>  ............................................
```


### Button `leave` — "That's enough of it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.more.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.more.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.more.leave   [20 chars]
    en  That's enough of it.
    >>  ............................................
    pt  Já chega disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.more.leave
WHO    VILLAGER — what the player reads after pressing "That's enough of it."
       spoken on: conversations.topic.rumors.more, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.more.leave`: the villager accepts. Subject `rumors.talk`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.more.leave/1   [16 chars]
    en  So it is, it is.
    >>  ............................................
    pt  É assim, chega.
    >>  ............................................
  dialogue.conversations.rumors.more.leave/2   [10 chars]
    en  Go safely.
    >>  ............................................
    pt  Vá com cuidado.
    >>  ............................................
  dialogue.conversations.rumors.more.leave/3   [28 chars]
    en  Take care of yourself, %1$s.
    >>  ............................................
    pt  Se cuide, %1$s.
    >>  ............................................
```

---


## `conversations.topic.rumors.none.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `rumors`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.none` — e.g. "Rumors? It's been dead quiet, %1$s. Not so much as a misplaced goat to talk about."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.none.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.none.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.none.respond   [24 chars]
    en  Nothing worth repeating.
    >>  ............................................
    pt  Nada que valha repetir.
    >>  ............................................
```


### Button `relieved` — "Good. A quiet week suits me."

*stance family `restraint` · tone `plain` · answers the beat(s) `rumors.none.to.rumors.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.none.relieved` — accepted phrasings: "a quiet week suits me"; "good, quiet is fine"; "quiet suits me"
  - the message must contain one of: `quiet`, `suits`
  - scored words: `quiet`(1.5), `suits`(1.2), `good`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.none.respond.relieved
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.none.respond.relieved   [28 chars]
    en  Good. A quiet week suits me.
    >>  ............................................
    pt  Bom. Uma semana calma me serve.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.none.relieved`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +1  _(recorded under topic `rumors.none.relieved`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.none.relieved
WHO    VILLAGER — what the player reads after pressing "Good. A quiet week suits me."
       spoken on: conversations.topic.rumors.none.respond, button `relieved`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.none.relieved.terminal`: the villager accepts. Subject `rumors.none`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.rumors.none.relieved/1   [43 chars]
    en  It does me too. Quiet weeks are underrated.
    >>  ............................................
    pt  A mim também. Semanas calmas são subestimadas.
    >>  ............................................
  dialogue.conversations.rumors.none.relieved/2   [58 chars]
    en  So I've found. Nothing to repeat means nothing went wrong.
    >>  ............................................
    pt  Foi o que eu vi. Nada para repetir significa que nada deu errado.
    >>  ............................................
  dialogue.conversations.rumors.none.relieved/3   [34 chars]
    en  Good. Long may it stay dull, %1$s.
    >>  ............................................
    pt  Bom. Que continue sem graça por muito tempo, %1$s.
    >>  ............................................
```


### Button `ask_anyway` — "Nothing at all?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `rumors.none.to.rumors.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.none.ask_anyway` — accepted phrasings: "nothing at all"; "really, nothing"; "nothing whatsoever"
  - the message must contain one of: `nothing`, `really`
  - scored words: `nothing`(1.5), `all`(0.6), `really`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.none.respond.ask_anyway
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.none.respond.ask_anyway   [15 chars]
    en  Nothing at all?
    >>  ............................................
    pt  Nada mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `rumors.none.ask_anyway`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.none.ask_anyway
WHO    VILLAGER — what the player reads after pressing "Nothing at all?"
       spoken on: conversations.topic.rumors.none.respond, button `ask_anyway`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.none.ask_anyway.terminal`: the villager accepts. Subject `rumors.none`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.rumors.none.ask_anyway/1   [56 chars]
    en  Nothing worth your time. And I'd tell you if there were.
    >>  ............................................
    pt  Nada que valha seu tempo. E eu te contaria se houvesse.
    >>  ............................................
  dialogue.conversations.rumors.none.ask_anyway/2   [42 chars]
    en  Not a whisper. Come back after market day.
    >>  ............................................
    pt  Nem um sussurro. Volte depois do dia de feira.
    >>  ............................................
  dialogue.conversations.rumors.none.ask_anyway/3   [38 chars]
    en  Truly nothing. It's almost unsettling.
    >>  ............................................
    pt  Realmente nada. É quase perturbador.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `rumors.none.to.rumors.none` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.none.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.none.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.rumors.none.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.none.leave.terminal`: the villager accepts. Subject `rumors.none`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.rumors.none.leave/1   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
  dialogue.conversations.rumors.none.leave/2   [22 chars]
    en  Until next time, %1$s.
    >>  ............................................
    pt  Até a próxima, %1$s.
    >>  ............................................
  dialogue.conversations.rumors.none.leave/3   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Outra hora.
    >>  ............................................
```

---


## `conversations.topic.rumors.private.followup`

**Reached from 1 route(s):** `conversations.topic.rumors.private.respond` / `ask_what_kind`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.private.ask_what_kind` — e.g. "It's the kind you'd hear anyway. I'd rather you heard it without the trimmings."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.private.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.private.followup   [41 chars]
    en  Right. That's where I'd like to leave it.
    >>  ............................................
    pt  Certo. É onde eu queria deixar.
    >>  ............................................
```


### Button `promise_discretion` — "It goes no further from me."

*stance family `candor` · tone `plain` · answers the beat(s) `rumors.private.ask_what_kind`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.private.promise_discretion` — accepted phrasings: "it goes no further from me"; "i will not pass it on"; "nobody will hear it from me"
  - the message must contain one of: `further`, `goes`, `nobody`
  - scored words: `further`(1.5), `goes`(0.8), `nobody`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.followup.promise_discretion
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.followup.promise_discretion   [27 chars]
    en  It goes no further from me.
    >>  ............................................
    pt  De mim não passa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.private.promise_discretion`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `rumors.private.promise_discretion`)_
- Does: arc `rumors` — advance
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.promise_discretion
WHO    VILLAGER — what the player reads after pressing "It goes no further from me."
       spoken on: conversations.topic.rumors.private.followup, button `promise_discretion`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.promise_discretion`: the villager accepts. Subject `rumors.private`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.private.promise_discretion/1   [59 chars]
    en  Good. That's the whole of what I wanted out of telling you.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar.
    >>  ............................................
  dialogue.conversations.rumors.private.promise_discretion/2   [73 chars]
    en  Then I was right about you, which I'd been about eighty per cent sure of.
    >>  ............................................
    pt  Então eu estava certo sobre você, do que eu tinha uns oitenta por cento de certeza.
    >>  ............................................
  dialogue.conversations.rumors.private.promise_discretion/3   [80 chars]
    en  Thank you. I'll know by Thursday whether that was true, and I expect it will be.
    >>  ............................................
    pt  Obrigado. Até quinta eu vou saber se era verdade, e eu espero que seja.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and I was frightened it wasn't going to come.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e eu temia que não viesse.
    >>  ............................................
  anxious.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. I've been wrong before and it cost me a friend.
    >>  ............................................
    pt  Então eu estava certo sobre você. Já errei antes e me custou um amigo.
    >>  ............................................
  anxious.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday, and I'd rather not have to know at all.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta, e preferia não ter que saber.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and at my age wanting little is a mercy.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e na minha idade querer pouco é uma bênção.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. Sixty years teaches you to be eighty per cent sure, no more.
    >>  ............................................
    pt  Então eu estava certo sobre você. Sessenta anos ensinam a ter oitenta por cento, não mais.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday. Things travel here on a schedule, and I know it.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta. As coisas viajam aqui com horário, e eu sei qual.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you, which I'd been about eighty per cent sure of.
    >>  ............................................
    pt  Então eu estava certo sobre você, do que eu tinha uns oitenta por cento de certeza.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e acho que era.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you, which I'd been about eighty per cent sure of.
    >>  ............................................
    pt  Então eu estava certo sobre você, do que eu tinha uns oitenta por cento de certeza.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e acho que era.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you, %1$s.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. I'd been eighty per cent sure and I'm glad of the twenty.
    >>  ............................................
    pt  Então eu estava certo sobre você. Tinha oitenta por cento e agradeço os vinte.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I've no doubt it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e não tenho dúvida.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you, %1$s.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. I'd been eighty per cent sure and I'm glad of the twenty.
    >>  ............................................
    pt  Então eu estava certo sobre você. Tinha oitenta por cento e agradeço os vinte.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I've no doubt it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e não tenho dúvida.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you, %1$s.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. I'd been eighty per cent sure and I'm glad of the twenty.
    >>  ............................................
    pt  Então eu estava certo sobre você. Tinha oitenta por cento e agradeço os vinte.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I've no doubt it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e não tenho dúvida.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and I was frightened it wasn't going to come.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e eu temia que não viesse.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. I've been wrong before and it cost me a friend.
    >>  ............................................
    pt  Então eu estava certo sobre você. Já errei antes e me custou um amigo.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday, and I'd rather not have to know at all.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta, e preferia não ter que saber.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you, which I'd been about eighty per cent sure of.
    >>  ............................................
    pt  Então eu estava certo sobre você, do que eu tinha uns oitenta por cento de certeza.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e acho que era.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted out of telling you.
    >>  ............................................
    pt  Bom. É tudo que eu queria ao te contar.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you, which I'd been about eighty per cent sure of.
    >>  ............................................
    pt  Então eu estava certo sobre você, do que eu tinha uns oitenta por cento de certeza.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e acho que era.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's what I wanted from telling you.
    >>  ............................................
    pt  Bom. É o que eu queria ao contar.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you.
    >>  ............................................
    pt  Então eu estava certo sobre você.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and at my age wanting little is a mercy.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e na minha idade querer pouco é uma bênção.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. Sixty years teaches you to be eighty per cent sure, no more.
    >>  ............................................
    pt  Então eu estava certo sobre você. Sessenta anos ensinam a ter oitenta por cento, não mais.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday. Things travel here on a schedule, and I know it.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta. As coisas viajam aqui com horário, e eu sei qual.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's what I wanted from telling you.
    >>  ............................................
    pt  Bom. É o que eu queria ao contar.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you.
    >>  ............................................
    pt  Então eu estava certo sobre você.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and at my age wanting little is a mercy.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e na minha idade querer pouco é uma bênção.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. Sixty years teaches you to be eighty per cent sure, no more.
    >>  ............................................
    pt  Então eu estava certo sobre você. Sessenta anos ensinam a ter oitenta por cento, não mais.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday. Things travel here on a schedule, and I know it.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta. As coisas viajam aqui com horário, e eu sei qual.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good! That's the whole of what I wanted out of telling you, and I feel better already.
    >>  ............................................
    pt  Bom! É tudo que eu queria ao te contar, e já me sinto melhor.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you — I'd been about eighty per cent sure, which is high for me.
    >>  ............................................
    pt  Então eu estava certo sobre você — tinha uns oitenta por cento, o que é alto pra mim.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I fully expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e espero muito que sim.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good! That's the whole of what I wanted out of telling you, and I feel better already.
    >>  ............................................
    pt  Bom! É tudo que eu queria ao te contar, e já me sinto melhor.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you — I'd been about eighty per cent sure, which is high for me.
    >>  ............................................
    pt  Então eu estava certo sobre você — tinha uns oitenta por cento, o que é alto pra mim.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I fully expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e espero muito que sim.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and at my age wanting little is a mercy.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e na minha idade querer pouco é uma bênção.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. Sixty years teaches you to be eighty per cent sure, no more.
    >>  ............................................
    pt  Então eu estava certo sobre você. Sessenta anos ensinam a ter oitenta por cento, não mais.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday. Things travel here on a schedule, and I know it.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta. As coisas viajam aqui com horário, e eu sei qual.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's the whole of what I wanted, and I was frightened it wasn't going to come.
    >>  ............................................
    pt  Bom. É tudo que eu queria, e eu temia que não viesse.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you. I've been wrong before and it cost me a friend.
    >>  ............................................
    pt  Então eu estava certo sobre você. Já errei antes e me custou um amigo.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday, and I'd rather not have to know at all.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta, e preferia não ter que saber.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good. That's what I wanted from telling you.
    >>  ............................................
    pt  Bom. É o que eu queria ao contar.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you.
    >>  ............................................
    pt  Então eu estava certo sobre você.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good! That's the whole of what I wanted out of telling you, and I feel better already.
    >>  ............................................
    pt  Bom! É tudo que eu queria ao te contar, e já me sinto melhor.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you — I'd been about eighty per cent sure, which is high for me.
    >>  ............................................
    pt  Então eu estava certo sobre você — tinha uns oitenta por cento, o que é alto pra mim.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I fully expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e espero muito que sim.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.promise_discretion/1
    en  Good! That's the whole of what I wanted out of telling you, and I feel better already.
    >>  ............................................
    pt  Bom! É tudo que eu queria ao te contar, e já me sinto melhor.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.promise_discretion/2
    en  Then I was right about you — I'd been about eighty per cent sure, which is high for me.
    >>  ............................................
    pt  Então eu estava certo sobre você — tinha uns oitenta por cento, o que é alto pra mim.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.promise_discretion/3
    en  Thank you. I'll know by Thursday whether that was true, and I fully expect it will be.
    >>  ............................................
    pt  Obrigado. Vou saber até quinta se era verdade, e espero muito que sim.
    >>  ............................................
```

</details>


### Button `ask_after_them` — "How are they, though?"

*stance family `empathy` · tone `plain` · answers the beat(s) `rumors.private.ask_what_kind`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.private.ask_after_them` — accepted phrasings: "how are they though"; "how are they doing"; "how is the family"
  - the message must contain one of: `how`, `them`, `they`
  - scored words: `how`(0.5), `them`(0.5), `they`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.followup.ask_after_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.followup.ask_after_them   [21 chars]
    en  How are they, though?
    >>  ............................................
    pt  Mas como eles estão?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.private.ask_after_them`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `rumors.private.ask_after_them`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.ask_after_them
WHO    VILLAGER — what the player reads after pressing "How are they, though?"
       spoken on: conversations.topic.rumors.private.followup, button `ask_after_them`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.ask_after_them`: the villager discloses. Subject `rumors.private`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.private.ask_after_them/1   [83 chars]
    en  ...Them. Not the story. No one has asked that, and I've told four people this week.
    >>  ............................................
    pt  ...Eles. Não a história. Ninguém fez essa pergunta, e eu contei a quatro pessoas esta semana.
    >>  ............................................
  dialogue.conversations.rumors.private.ask_after_them/2   [84 chars]
    en  Badly, and privately, and they'd not want it discussed. Thank you for asking anyway.
    >>  ............................................
    pt  Mal, e em particular, e não iam querer que se falasse. Mas obrigado por perguntar.
    >>  ............................................
  dialogue.conversations.rumors.private.ask_after_them/3   [80 chars]
    en  That's the right question. I'll tell them somebody asked it, without saying who.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou, sem dizer quem.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them. Not the story. No one has asked that, %1$s, and I've told four people this week.
    >>  ............................................
    pt  ...Deles. Não da história. Ninguém fez essa pergunta, %1$s, e eu contei a quatro pessoas.
    >>  ............................................
  anxious.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly. I'd not say that to anybody else and I'll not say more than the one word.
    >>  ............................................
    pt  Mal. Eu não diria isso a mais ninguém e não vou dizer mais que a palavra.
    >>  ............................................
  anxious.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. Give me a moment — I'd not braced for the right question.
    >>  ............................................
    pt  É a pergunta certa. Me dê um momento — eu não me preparei pra pergunta certa.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. It'll be a long while for them, and asking helps more than it seems.
    >>  ............................................
    pt  Deles. Não da história. Vai ser longo pra eles, e perguntar ajuda mais do que parece.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. It'll ease over a year or two, the way these things do.
    >>  ............................................
    pt  Mal, e em particular. Vai aliviar em um ano ou dois, como essas coisas aliviam.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll pass it on quietly, which is the only way to pass anything on.
    >>  ............................................
    pt  É a pergunta certa. Vou passar em silêncio, que é o único jeito de passar qualquer coisa.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. That's the question nobody asks.
    >>  ............................................
    pt  Deles. Não da história. É a pergunta que ninguém faz.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. They'd not want it discussed, so I'll leave it there.
    >>  ............................................
    pt  Mal, e em particular. Não iam querer que se falasse, então eu deixo aí.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it, without saying who.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou, sem dizer quem.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. That's the question nobody asks.
    >>  ............................................
    pt  Deles. Não da história. É a pergunta que ninguém faz.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. They'd not want it discussed, so I'll leave it there.
    >>  ............................................
    pt  Mal, e em particular. Não iam querer que se falasse, então eu deixo aí.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it, without saying who.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou, sem dizer quem.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them, %1$s. Not the story. I've told four people this week and you're the first.
    >>  ............................................
    pt  ...Deles, %1$s. Não da história. Contei a quatro pessoas esta semana e você é o primeiro.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately, and they'd not want it discussed. Thank you for asking anyway.
    >>  ............................................
    pt  Mal, e em particular, e não iam querer que se falasse. Mas obrigado por perguntar.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it — that will matter to them.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou — isso vai importar pra eles.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them, %1$s. Not the story. I've told four people this week and you're the first.
    >>  ............................................
    pt  ...Deles, %1$s. Não da história. Contei a quatro pessoas esta semana e você é o primeiro.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately, and they'd not want it discussed. Thank you for asking anyway.
    >>  ............................................
    pt  Mal, e em particular, e não iam querer que se falasse. Mas obrigado por perguntar.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it — that will matter to them.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou — isso vai importar pra eles.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them, %1$s. Not the story. I've told four people this week and you're the first.
    >>  ............................................
    pt  ...Deles, %1$s. Não da história. Contei a quatro pessoas esta semana e você é o primeiro.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately, and they'd not want it discussed. Thank you for asking anyway.
    >>  ............................................
    pt  Mal, e em particular, e não iam querer que se falasse. Mas obrigado por perguntar.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it — that will matter to them.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou — isso vai importar pra eles.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them. Not the story. No one has asked that, %1$s, and I've told four people this week.
    >>  ............................................
    pt  ...Deles. Não da história. Ninguém fez essa pergunta, %1$s, e eu contei a quatro pessoas.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly. I'd not say that to anybody else and I'll not say more than the one word.
    >>  ............................................
    pt  Mal. Eu não diria isso a mais ninguém e não vou dizer mais que a palavra.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. Give me a moment — I'd not braced for the right question.
    >>  ............................................
    pt  É a pergunta certa. Me dê um momento — eu não me preparei pra pergunta certa.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. That's the question nobody asks.
    >>  ............................................
    pt  Deles. Não da história. É a pergunta que ninguém faz.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. They'd not want it discussed, so I'll leave it there.
    >>  ............................................
    pt  Mal, e em particular. Não iam querer que se falasse, então eu deixo aí.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it, without saying who.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou, sem dizer quem.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. That's the question nobody asks.
    >>  ............................................
    pt  Deles. Não da história. É a pergunta que ninguém faz.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. They'd not want it discussed, so I'll leave it there.
    >>  ............................................
    pt  Mal, e em particular. Não iam querer que se falasse, então eu deixo aí.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked it, without saying who.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou, sem dizer quem.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them. Not the story.
    >>  ............................................
    pt  ...Deles. Não da história.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately.
    >>  ............................................
    pt  Mal, e em particular.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question.
    >>  ............................................
    pt  É a pergunta certa.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. It'll be a long while for them, and asking helps more than it seems.
    >>  ............................................
    pt  Deles. Não da história. Vai ser longo pra eles, e perguntar ajuda mais do que parece.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. It'll ease over a year or two, the way these things do.
    >>  ............................................
    pt  Mal, e em particular. Vai aliviar em um ano ou dois, como essas coisas aliviam.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll pass it on quietly, which is the only way to pass anything on.
    >>  ............................................
    pt  É a pergunta certa. Vou passar em silêncio, que é o único jeito de passar qualquer coisa.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them. Not the story.
    >>  ............................................
    pt  ...Deles. Não da história.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately.
    >>  ............................................
    pt  Mal, e em particular.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question.
    >>  ............................................
    pt  É a pergunta certa.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. It'll be a long while for them, and asking helps more than it seems.
    >>  ............................................
    pt  Deles. Não da história. Vai ser longo pra eles, e perguntar ajuda mais do que parece.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. It'll ease over a year or two, the way these things do.
    >>  ............................................
    pt  Mal, e em particular. Vai aliviar em um ano ou dois, como essas coisas aliviam.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll pass it on quietly, which is the only way to pass anything on.
    >>  ............................................
    pt  É a pergunta certa. Vou passar em silêncio, que é o único jeito de passar qualquer coisa.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them! Not the story. Do you know how rare that is? Four people this week and not one.
    >>  ............................................
    pt  Deles! Não da história. Sabe o quão raro é isso? Quatro pessoas esta semana e nenhuma.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. I'll not decorate it and I'll not make a joke of it either.
    >>  ............................................
    pt  Mal, e em particular. Não vou enfeitar e não vou fazer piada.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked. They'll want to know that.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou. Eles vão querer saber.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them! Not the story. Do you know how rare that is? Four people this week and not one.
    >>  ............................................
    pt  Deles! Não da história. Sabe o quão raro é isso? Quatro pessoas esta semana e nenhuma.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. I'll not decorate it and I'll not make a joke of it either.
    >>  ............................................
    pt  Mal, e em particular. Não vou enfeitar e não vou fazer piada.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked. They'll want to know that.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou. Eles vão querer saber.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them. Not the story. It'll be a long while for them, and asking helps more than it seems.
    >>  ............................................
    pt  Deles. Não da história. Vai ser longo pra eles, e perguntar ajuda mais do que parece.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. It'll ease over a year or two, the way these things do.
    >>  ............................................
    pt  Mal, e em particular. Vai aliviar em um ano ou dois, como essas coisas aliviam.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll pass it on quietly, which is the only way to pass anything on.
    >>  ............................................
    pt  É a pergunta certa. Vou passar em silêncio, que é o único jeito de passar qualquer coisa.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them. Not the story. No one has asked that, %1$s, and I've told four people this week.
    >>  ............................................
    pt  ...Deles. Não da história. Ninguém fez essa pergunta, %1$s, e eu contei a quatro pessoas.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly. I'd not say that to anybody else and I'll not say more than the one word.
    >>  ............................................
    pt  Mal. Eu não diria isso a mais ninguém e não vou dizer mais que a palavra.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. Give me a moment — I'd not braced for the right question.
    >>  ............................................
    pt  É a pergunta certa. Me dê um momento — eu não me preparei pra pergunta certa.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.ask_after_them/1
    en  ...Them. Not the story.
    >>  ............................................
    pt  ...Deles. Não da história.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately.
    >>  ............................................
    pt  Mal, e em particular.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question.
    >>  ............................................
    pt  É a pergunta certa.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them! Not the story. Do you know how rare that is? Four people this week and not one.
    >>  ............................................
    pt  Deles! Não da história. Sabe o quão raro é isso? Quatro pessoas esta semana e nenhuma.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. I'll not decorate it and I'll not make a joke of it either.
    >>  ............................................
    pt  Mal, e em particular. Não vou enfeitar e não vou fazer piada.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked. They'll want to know that.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou. Eles vão querer saber.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.ask_after_them/1
    en  Them! Not the story. Do you know how rare that is? Four people this week and not one.
    >>  ............................................
    pt  Deles! Não da história. Sabe o quão raro é isso? Quatro pessoas esta semana e nenhuma.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.ask_after_them/2
    en  Badly, and privately. I'll not decorate it and I'll not make a joke of it either.
    >>  ............................................
    pt  Mal, e em particular. Não vou enfeitar e não vou fazer piada.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.ask_after_them/3
    en  That's the right question. I'll tell them somebody asked. They'll want to know that.
    >>  ............................................
    pt  É a pergunta certa. Vou dizer a eles que alguém perguntou. Eles vão querer saber.
    >>  ............................................
```

</details>


### Button `leave` — "I'll say nothing about it."

*stance family `exit` · tone `plain` · answers the beat(s) `rumors.private.ask_what_kind` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.followup.leave   [26 chars]
    en  I'll say nothing about it.
    >>  ............................................
    pt  Eu não vou dizer nada sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.leave
WHO    VILLAGER — what the player reads after pressing "I'll say nothing about it."
       spoken on: conversations.topic.rumors.private.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.leave`: the villager accepts. Subject `rumors.private`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.rumors.private.respond / leave
```

```text
  dialogue.conversations.rumors.private.leave/1   [67 chars]
    en  Right. Then we're agreed and neither of us has to mention it again.
    >>  ............................................
    pt  Certo. Então estamos de acordo e nenhum de nós precisa mencionar de novo.
    >>  ............................................
  dialogue.conversations.rumors.private.leave/2   [32 chars]
    en  Good. Off you go, and thank you.
    >>  ............................................
    pt  Bom. Pode ir, e obrigado.
    >>  ............................................
  dialogue.conversations.rumors.private.leave/3   [55 chars]
    en  Fine. That's the end of it, which is where I wanted it.
    >>  ............................................
    pt  Tudo bem. É o fim disso, que é onde eu queria.
    >>  ............................................
```

---


## `conversations.topic.rumors.private.respond`

**Reached from 2 route(s):** `conversations.cat.village` / `rumors`; `conversations.cat.village` / `rumors`


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.private.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.private.respond   [45 chars]
    en  That's as much as I'm willing to carry about.
    >>  ............................................
    pt  É tudo que eu topo carregar por aí.
    >>  ............................................
```


### Button `ask_what_kind` — "Is it the kind of thing I should know?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.private.ask_what_kind` — accepted phrasings: "is it the kind of thing i should know"; "should i know about it"; "is it something i need to know"
  - the message must contain one of: `kind`, `know`, `should`
  - scored words: `kind`(1.2), `know`(0.6), `should`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.respond.ask_what_kind
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.respond.ask_what_kind   [38 chars]
    en  Is it the kind of thing I should know?
    >>  ............................................
    pt  É o tipo de coisa que eu deveria saber?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +2  _(recorded under topic `rumors.private.ask_what_kind`)_
- Then opens: `conversations.topic.rumors.private.followup`
- …where the player's next choices will be: "It goes no further from me." | "How are they, though?" | "I'll say nothing about it."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.ask_what_kind
WHO    VILLAGER — what the player reads after pressing "Is it the kind of thing I should know?"
       spoken on: conversations.topic.rumors.private.respond, button `ask_what_kind`
       leaves the player on: conversations.topic.rumors.private.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.ask_what_kind`: the villager qualifys. Subject `rumors.private`, polarity `mixed`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.private.ask_what_kind/1   [79 chars]
    en  It's the kind you'd hear anyway. I'd rather you heard it without the trimmings.
    >>  ............................................
    pt  É do tipo que você ouviria de todo jeito. Prefiro que ouça sem os enfeites.
    >>  ............................................
  dialogue.conversations.rumors.private.ask_what_kind/2   [83 chars]
    en  It's the kind that changes how you'd speak to somebody. That's why I said anything.
    >>  ............................................
    pt  É do tipo que muda como você falaria com alguém. Por isso eu disse algo.
    >>  ............................................
  dialogue.conversations.rumors.private.ask_what_kind/3   [71 chars]
    en  It's the kind people are careful about. Now you know to be careful too.
    >>  ............................................
    pt  É do tipo com que as pessoas têm cuidado. Agora você sabe ter cuidado também.
    >>  ............................................
```


### Button `let_it_be` — "Then I'll not ask further."

*stance family `restraint` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.private.let_it_be` — accepted phrasings: "then i will not ask further"; "that is private enough"; "i will be discreet"
  - the message must contain one of: `ask`, `discreet`, `private`
  - scored words: `ask`(0.4), `discreet`(1.5), `private`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.respond.let_it_be
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.respond.let_it_be   [26 chars]
    en  Then I'll not ask further.
    >>  ............................................
    pt  Então eu não pergunto mais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.private.let_it_be`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +4  _(recorded under topic `rumors.private.let_it_be`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.let_it_be
WHO    VILLAGER — what the player reads after pressing "Then I'll not ask further."
       spoken on: conversations.topic.rumors.private.respond, button `let_it_be`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.let_it_be`: the villager accepts. Subject `rumors.private`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.private.let_it_be/1   [70 chars]
    en  ...Thank you. Everyone wants the rest and I've had to say no all week.
    >>  ............................................
    pt  ...Obrigado. A maioria quer o resto e eu tive que dizer não a semana toda.
    >>  ............................................
  dialogue.conversations.rumors.private.let_it_be/2   [73 chars]
    en  You'll leave it. Right. That's the first time anybody has this fortnight.
    >>  ............................................
    pt  Você vai deixar. Certo. É a primeira vez que alguém deixa nesta quinzena.
    >>  ............................................
  dialogue.conversations.rumors.private.let_it_be/3   [59 chars]
    en  Then it stays where it is. I'll not forget that you let it.
    >>  ............................................
    pt  Então fica onde está. Não vou esquecer que você deixou.
    >>  ............................................
```


### Button `press` — "Come on. Who was it about?"

*stance family `boundary_push` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.private.press` — accepted phrasings: "come on who was it about"; "who was it about"; "tell me who"
  - the message must contain one of: `about`, `come`, `who`
  - scored words: `about`(0.4), `come`(0.8), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.respond.press   [26 chars]
    en  Come on. Who was it about?
    >>  ............................................
    pt  Vamos lá. Era sobre quem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `rumors.private.press`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust -4, tension +4, warmth -2  _(recorded under topic `rumors.private.press`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.press
WHO    VILLAGER — what the player reads after pressing "Come on. Who was it about?"
       spoken on: conversations.topic.rumors.private.respond, button `press`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.press`: the villager set_boundarys. Subject `rumors.private`, polarity `negative`, closes subject, outcome `boundary_closed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.private.press/1   [66 chars]
    en  No. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  dialogue.conversations.rumors.private.press/2   [61 chars]
    en  That's exactly why I didn't say. Ask the family or don't ask.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família ou não pergunte.
    >>  ............................................
  dialogue.conversations.rumors.private.press/3   [71 chars]
    en  I said as much as I'm willing to. That was the sentence and I meant it.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era essa a frase e eu falei sério.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, %1$s. Please don't make me the one who spread it.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, %1$s. Por favor não me faça ser quem espalhou.
    >>  ............................................
  anxious.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. If it gets about, they'll know who told, and they'd be right.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Se correr, eles vão saber quem contou, e com razão.
    >>  ............................................
  anxious.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. Asking again puts it on me, and I can't carry that.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Perguntar de novo põe em mim, e eu não consigo carregar.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family. It'll get about without my help, and slower.
    >>  ............................................
    pt  Não. É uma morte na família de alguém. Vai correr sem a minha ajuda, e mais devagar.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Give it a season and they'll tell it themselves.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Dê uma estação e eles mesmos contam.
    >>  ............................................
  athletic.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That's where I stop, and it's where I've always stopped.
    >>  ............................................
    pt  Eu disse tudo que eu topo. É onde eu paro, e é onde eu sempre parei.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Ask the family or don't ask.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família ou não pergunte.
    >>  ............................................
  confident.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the sentence and I meant it.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era essa a frase e eu falei sério.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Ask the family or don't ask.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família ou não pergunte.
    >>  ............................................
  crabby.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the sentence and I meant it.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era essa a frase e eu falei sério.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.press/1
    en  No, %1$s. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não, %1$s. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. I'd tell you most things. Not this one.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Eu te contaria quase tudo. Isso não.
    >>  ............................................
  extroverted.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. Don't make me say it twice to you of all people.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Não me faça dizer duas vezes a você, logo a você.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.press/1
    en  No, %1$s. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não, %1$s. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. I'd tell you most things. Not this one.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Eu te contaria quase tudo. Isso não.
    >>  ............................................
  flirty.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. Don't make me say it twice to you of all people.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Não me faça dizer duas vezes a você, logo a você.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.press/1
    en  No, %1$s. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não, %1$s. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. I'd tell you most things. Not this one.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Eu te contaria quase tudo. Isso não.
    >>  ............................................
  friendly.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. Don't make me say it twice to you of all people.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Não me faça dizer duas vezes a você, logo a você.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, %1$s. Please don't make me the one who spread it.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, %1$s. Por favor não me faça ser quem espalhou.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. If it gets about, they'll know who told, and they'd be right.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Se correr, eles vão saber quem contou, e com razão.
    >>  ............................................
  gloomy.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. Asking again puts it on me, and I can't carry that.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Perguntar de novo põe em mim, e eu não consigo carregar.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Ask the family or don't ask.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família ou não pergunte.
    >>  ............................................
  greedy.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the sentence and I meant it.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era essa a frase e eu falei sério.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, not a story for the square.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, não uma história pra praça.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Ask the family or don't ask.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família ou não pergunte.
    >>  ............................................
  grumpy.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the sentence and I meant it.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era essa a frase e eu falei sério.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.press/1
    en  No. It's somebody's family, not a story.
    >>  ............................................
    pt  Não. É a família de alguém, não uma história.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say.
    >>  ............................................
    pt  É exatamente por isso que eu não disse.
    >>  ............................................
  introverted.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to.
    >>  ............................................
    pt  Eu disse tudo que eu topo.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family. It'll get about without my help, and slower.
    >>  ............................................
    pt  Não. É uma morte na família de alguém. Vai correr sem a minha ajuda, e mais devagar.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Give it a season and they'll tell it themselves.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Dê uma estação e eles mesmos contam.
    >>  ............................................
  lazy.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That's where I stop, and it's where I've always stopped.
    >>  ............................................
    pt  Eu disse tudo que eu topo. É onde eu paro, e é onde eu sempre parei.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.press/1
    en  No. It's somebody's family, not a story.
    >>  ............................................
    pt  Não. É a família de alguém, não uma história.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say.
    >>  ............................................
    pt  É exatamente por isso que eu não disse.
    >>  ............................................
  odd.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to.
    >>  ............................................
    pt  Eu disse tudo que eu topo.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family. It'll get about without my help, and slower.
    >>  ............................................
    pt  Não. É uma morte na família de alguém. Vai correr sem a minha ajuda, e mais devagar.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Give it a season and they'll tell it themselves.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Dê uma estação e eles mesmos contam.
    >>  ............................................
  peaceful.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That's where I stop, and it's where I've always stopped.
    >>  ............................................
    pt  Eu disse tudo que eu topo. É onde eu paro, e é onde eu sempre parei.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.press/1
    en  No. There's no lighter way to hold this one and I'm not going to find one for you.
    >>  ............................................
    pt  Não. Não tem jeito mais leve de segurar isso e eu não vou achar um pra você.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say it. Ask the family. I'll not be the shortcut.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família. Não vou ser o atalho.
    >>  ............................................
  peppy.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the whole speech and it had an ending.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era o discurso inteiro e tinha um fim.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.press/1
    en  No. There's no lighter way to hold this one and I'm not going to find one for you.
    >>  ............................................
    pt  Não. Não tem jeito mais leve de segurar isso e eu não vou achar um pra você.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say it. Ask the family. I'll not be the shortcut.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família. Não vou ser o atalho.
    >>  ............................................
  playful.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the whole speech and it had an ending.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era o discurso inteiro e tinha um fim.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family. It'll get about without my help, and slower.
    >>  ............................................
    pt  Não. É uma morte na família de alguém. Vai correr sem a minha ajuda, e mais devagar.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. Give it a season and they'll tell it themselves.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Dê uma estação e eles mesmos contam.
    >>  ............................................
  relaxed.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That's where I stop, and it's where I've always stopped.
    >>  ............................................
    pt  Eu disse tudo que eu topo. É onde eu paro, e é onde eu sempre parei.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.press/1
    en  No. It's a death in somebody's family, %1$s. Please don't make me the one who spread it.
    >>  ............................................
    pt  Não. É uma morte na família de alguém, %1$s. Por favor não me faça ser quem espalhou.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say. If it gets about, they'll know who told, and they'd be right.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Se correr, eles vão saber quem contou, e com razão.
    >>  ............................................
  sensitive.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. Asking again puts it on me, and I can't carry that.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Perguntar de novo põe em mim, e eu não consigo carregar.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.press/1
    en  No. It's somebody's family, not a story.
    >>  ............................................
    pt  Não. É a família de alguém, não uma história.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say.
    >>  ............................................
    pt  É exatamente por isso que eu não disse.
    >>  ............................................
  shy.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to.
    >>  ............................................
    pt  Eu disse tudo que eu topo.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.press/1
    en  No. There's no lighter way to hold this one and I'm not going to find one for you.
    >>  ............................................
    pt  Não. Não tem jeito mais leve de segurar isso e eu não vou achar um pra você.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say it. Ask the family. I'll not be the shortcut.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família. Não vou ser o atalho.
    >>  ............................................
  upbeat.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the whole speech and it had an ending.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era o discurso inteiro e tinha um fim.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.press/1
    en  No. There's no lighter way to hold this one and I'm not going to find one for you.
    >>  ............................................
    pt  Não. Não tem jeito mais leve de segurar isso e eu não vou achar um pra você.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.press/2
    en  That's exactly why I didn't say it. Ask the family. I'll not be the shortcut.
    >>  ............................................
    pt  É exatamente por isso que eu não disse. Pergunte à família. Não vou ser o atalho.
    >>  ............................................
  witty.dialogue.conversations.rumors.private.press/3
    en  I said as much as I'm willing to. That was the whole speech and it had an ending.
    >>  ............................................
    pt  Eu disse tudo que eu topo. Era o discurso inteiro e tinha um fim.
    >>  ............................................
```

</details>


### Button `leave` — "I'll say nothing about it."

*stance family `exit` · tone `plain` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.private.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.private.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.private.respond.leave   [26 chars]
    en  I'll say nothing about it.
    >>  ............................................
    pt  Eu não vou dizer nada sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.private.leave
WHO    VILLAGER — what the player reads after pressing "I'll say nothing about it."
       spoken on: conversations.topic.rumors.private.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.private.leave`: the villager accepts. Subject `rumors.private`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.rumors.private.followup / leave
```

> Written out in full under **`conversations.topic.rumors.private.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.rumors.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `rumors`


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.respond   [26 chars]
    en  That's what's going round.
    >>  ............................................
    pt  É isso que está circulando.
    >>  ............................................
```


### Button `ask_source` — "Who told you that?"

*stance family `curiosity` · tone `plain` · outcome `accepted` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.respond.ask_source` — accepted phrasings: "who told you that"; "where did you hear it"; "who did you hear that from"
  - the message must contain one of: `told`, `who`, `heard`
  - scored words: `told`(1.5), `who`(1.0), `heard`(1.0), `from`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.respond.ask_source
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.respond.ask_source   [18 chars]
    en  Who told you that?
    >>  ............................................
    pt  Quem te contou isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.respond.ask_source`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, familiarity +1  _(recorded under topic `rumors.respond.ask_source`)_
- Then opens: `conversations.topic.rumors.followup`
- …where the player's next choices will be: "It stops with me." | "Best forgotten, that." | "Who else knows?" | "Before I go — what do you make of it?" | "I'd rather not know."

```text
POOL   dialogue key: dialogue.conversations.rumors.respond.ask_source
WHO    VILLAGER — what the player reads after pressing "Who told you that?"
       spoken on: conversations.topic.rumors.respond, button `ask_source`
       leaves the player on: conversations.topic.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.respond.ask_source.to.rumors`: the villager accepts. Subject `rumors.source`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.rumors.respond.ask_source/1   [58 chars]
    en  ...Fair question. Second-hand, if I'm honest. Maybe third.
    >>  ............................................
    pt  ...Pergunta justa. De segunda mão, para ser sincero. Talvez terceira.
    >>  ............................................
  dialogue.conversations.rumors.respond.ask_source/2   [63 chars]
    en  Nobody ever asks that. Which is rather how these things spread.
    >>  ............................................
    pt  Ninguém nunca pergunta isso. É mais ou menos assim que essas coisas se espalham.
    >>  ............................................
  dialogue.conversations.rumors.respond.ask_source/3   [61 chars]
    en  The well, where else. Which means it's half invented already.
    >>  ............................................
    pt  O poço, onde mais. O que significa que já está metade inventado.
    >>  ............................................
```


### Button `challenge` — "That doesn't sound reliable."

*stance family `challenge` · tone `plain` · outcome `accepted` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.respond.challenge` — accepted phrasings: "that does not sound reliable"; "i doubt that"; "is that even true"
  - the message must contain one of: `reliable`, `doubt`, `true`
  - scored words: `reliable`(1.5), `doubt`(1.5), `true`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.respond.challenge   [28 chars]
    en  That doesn't sound reliable.
    >>  ............................................
    pt  Isso não parece confiável.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `greedy`, `extroverted`, `flirty`
- Does: **hearts +1** — decision id `rumors.respond.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, tension +4  _(recorded under topic `rumors.respond.challenge`)_
- Then opens: `conversations.topic.rumors.followup`
- …where the player's next choices will be: "It stops with me." | "Best forgotten, that." | "Who else knows?" | "Before I go — what do you make of it?" | "I'd rather not know."

```text
POOL   dialogue key: dialogue.conversations.rumors.challenge.trader
WHO    VILLAGER — what the player reads after pressing "That doesn't sound reliable."
       spoken on: conversations.topic.rumors.respond, button `challenge`
       leaves the player on: conversations.topic.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.challenge.trader.to.rumors`: the villager accepts. Subject `rumors.source`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.rumors.challenge.trader/1   [75 chars]
    en  Careful. Half the things I know, I know because nobody checked them either.
    >>  ............................................
    pt  Cuidado. Metade do que eu sei, eu sei porque ninguém conferiu também.
    >>  ............................................
  dialogue.conversations.rumors.challenge.trader/2   [90 chars]
    en  You want it verified? That's not how any of this works, %1$s. It's a market, not a ledger.
    >>  ............................................
    pt  Você quer verificado? Não é assim que funciona, %1$s. Isso é feira, não livro-caixa.
    >>  ............................................
  dialogue.conversations.rumors.challenge.trader/3   [98 chars]
    en  Ask for proof and people stop telling you things. That's a worse trade than being wrong sometimes.
    >>  ............................................
    pt  Peça prova e as pessoas param de te contar coisas. É uma troca pior do que errar de vez em quando.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `greedy`, `extroverted`, `flirty`  _(chance -2000)_
- Does: **hearts +1** — decision id `rumors.respond.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `rumors.respond.challenge`)_
- Then opens: `conversations.topic.rumors.followup`
- …where the player's next choices will be: "It stops with me." | "Best forgotten, that." | "Who else knows?" | "Before I go — what do you make of it?" | "I'd rather not know."

```text
POOL   dialogue key: dialogue.conversations.rumors.respond.challenge
WHO    VILLAGER — what the player reads after pressing "That doesn't sound reliable."
       spoken on: conversations.topic.rumors.respond, button `challenge`
       leaves the player on: conversations.topic.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.respond.challenge.to.rumors`: the villager accepts. Subject `rumors.source`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.rumors.respond.challenge/1   [59 chars]
    en  ...No. It doesn't, does it. I repeated it without thinking.
    >>  ............................................
    pt  ...Não. Não parece mesmo, né. Repeti sem pensar.
    >>  ............................................
  dialogue.conversations.rumors.respond.challenge/2   [58 chars]
    en  You're right to doubt it. I doubted it and said it anyway.
    >>  ............................................
    pt  Você tem razão em duvidar. Eu duvidei e falei mesmo assim.
    >>  ............................................
  dialogue.conversations.rumors.respond.challenge/3   [58 chars]
    en  Reliable's a strong word for anything said near that well.
    >>  ............................................
    pt  Confiável é uma palavra forte para qualquer coisa dita perto daquele poço.
    >>  ............................................
```


### Button `listen` — "Go on."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.respond.listen` — accepted phrasings: "go on"; "tell me more"; "continue"
  - the message must contain one of: `go`, `more`, `continue`
  - scored words: `go`(0.6), `on`(0.4), `more`(1.0), `continue`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.respond.listen   [6 chars]
    en  Go on.
    >>  ............................................
    pt  Continue.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `rumors.respond.listen`)_
- Then opens: `conversations.topic.rumors.followup`
- …where the player's next choices will be: "It stops with me." | "Best forgotten, that." | "Who else knows?" | "Before I go — what do you make of it?" | "I'd rather not know."

```text
POOL   dialogue key: dialogue.conversations.rumors.respond.listen
WHO    VILLAGER — what the player reads after pressing "Go on."
       spoken on: conversations.topic.rumors.respond, button `listen`
       leaves the player on: conversations.topic.rumors.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.respond.listen.to.rumors`: the villager accepts. Subject `rumors.listen`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.rumors.respond.listen/1   [56 chars]
    en  Right. Well. There's not much more, when I say it aloud.
    >>  ............................................
    pt  Certo. Bom. Não tem muito mais, quando eu falo em voz alta.
    >>  ............................................
  dialogue.conversations.rumors.respond.listen/2   [48 chars]
    en  That's most of it. It sounded bigger in my head.
    >>  ............................................
    pt  É quase tudo. Parecia maior na minha cabeça.
    >>  ............................................
  dialogue.conversations.rumors.respond.listen/3   [57 chars]
    en  You're an easy person to tell things to. Dangerous, that.
    >>  ............................................
    pt  Você é uma pessoa fácil de contar coisas. Isso é perigoso.
    >>  ............................................
```


### Button `leave` — "I'd rather not know."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.respond.leave   [20 chars]
    en  I'd rather not know.
    >>  ............................................
    pt  Prefiro não saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'd rather not know."
       spoken on: conversations.topic.rumors.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.respond.leave.terminal`: the villager accepts. Subject `rumors.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.rumors.followup / leave
```

> Written out in full under **`conversations.topic.rumors.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.rumors.stale`

**Reached from 1 route(s):** `conversations.topic.rumors.more` / `is_this_old`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.rumors.more.is_this_old` — e.g. "A season, near enough. You're the last person here to hear it."


```text
POOL   dialogue key: dialogue.conversations.topic.rumors.stale
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.rumors.stale
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.rumors.stale   [38 chars]
    en  That's how long it's been going round.
    >>  ............................................
    pt  É há quanto tempo isso está circulando.
    >>  ............................................
```


### Button `nobody_told_me` — "Nobody thought to tell me."

*stance family `candor` · tone `plain` · outcome `engaged` · answers the beat(s) `rumors.more.is_this_old`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.stale.untold` — accepted phrasings: "nobody thought to tell me"; "no one told me any of this"; "why am i only hearing it now"
  - the message must contain one of: `nobody`
  - scored words: `nobody`(1.2), `told`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.stale.nobody_told_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.stale
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.stale.nobody_told_me   [26 chars]
    en  Nobody thought to tell me.
    >>  ............................................
    pt  Ninguém pensou em me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `rumors.stale.untold`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.stale.untold
WHO    VILLAGER — what the player reads after pressing "Nobody thought to tell me."
       spoken on: conversations.topic.rumors.stale, button `nobody_told_me`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.stale.untold`: the villager explains. Subject `rumors.stale`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.stale.untold/1   [75 chars]
    en  That's how you know where you stand here. It takes a year to be told first.
    >>  ............................................
    pt  É assim que você sabe onde está aqui. Leva um ano pra ser avisado primeiro.
    >>  ............................................
  dialogue.conversations.rumors.stale.untold/2   [79 chars]
    en  They will next time. Being told late is the last stage before being told early.
    >>  ............................................
    pt  Da próxima vez contam. Saber tarde é a última fase antes de saber cedo.
    >>  ............................................
  dialogue.conversations.rumors.stale.untold/3   [73 chars]
    en  I assumed somebody had. That's what everyone assumes, and so nobody does.
    >>  ............................................
    pt  Achei que alguém já tinha contado. Todos acham, e por isso ninguém conta.
    >>  ............................................
```


### Button `then_let_it_die` — "Then let it die out."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `rumors.more.is_this_old`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `rumors.stale.die` — accepted phrasings: "then let it die out"; "let it fade then"; "it should be left to die"
  - the message must contain one of: `die`
  - scored words: `die`(1.5), `out`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.stale.then_let_it_die
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.stale
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.stale.then_let_it_die   [20 chars]
    en  Then let it die out.
    >>  ............................................
    pt  Então deixe morrer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `rumors.stale.die`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2  _(recorded under topic `rumors.stale.die`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.stale.die
WHO    VILLAGER — what the player reads after pressing "Then let it die out."
       spoken on: conversations.topic.rumors.stale, button `then_let_it_die`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.stale.die`: the villager accepts. Subject `rumors.stale`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.stale.die/1   [70 chars]
    en  It would have, if people stopped feeding it. That includes me, I know.
    >>  ............................................
    pt  Já teria morrido, se parassem de alimentar. Inclusive eu, eu sei.
    >>  ............................................
  dialogue.conversations.rumors.stale.die/2   [59 chars]
    en  It's trying to. Every retelling gives it another fortnight.
    >>  ............................................
    pt  Está tentando. Cada recontagem dá mais quinze dias a isso.
    >>  ............................................
  dialogue.conversations.rumors.stale.die/3   [62 chars]
    en  So it is. I'll not be the one who carries it into next season.
    >>  ............................................
    pt  É assim mesmo. Não vou ser eu a carregar isso pra próxima estação.
    >>  ............................................
```


### Button `leave` — "Right."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `rumors.more.is_this_old` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.rumors.stale.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.rumors.stale
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.rumors.stale.leave   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.stale.leave
WHO    VILLAGER — what the player reads after pressing "Right."
       spoken on: conversations.topic.rumors.stale, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.stale.leave`: the villager accepts. Subject `rumors.stale`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.stale.leave/1   [12 chars]
    en  True enough.
    >>  ............................................
    pt  Bem verdade.
    >>  ............................................
  dialogue.conversations.rumors.stale.leave/2   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.rumors.stale.leave/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---

