# Topic: noticed

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `noticed` |
| Opened from | question `conversations.cat.events`, button `noticed` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.events` |
| Ages that can reach it | adult |
| Stance families it must offer | `empathy`, `restraint`, `practical_help`, `dismissal`, `candor`, `exit` |
| Narrative arc | `noticed`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.events`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.events.noticed
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.events.noticed   [31 chars]
    en  How have you been, in yourself?
    >>  ............................................
    pt  Como você tem estado, por dentro?
    >>  ............................................
```

---

**Parts of this conversation:** [part 1](topic-noticed-part1.md) · [part 2](topic-noticed-part2.md)


## Nodes in this file

- [`conversations.arc.noticed.resume.followup`](#conversations-arc-noticed-resume-followup)
- [`conversations.arc.noticed.resume.respond`](#conversations-arc-noticed-resume-respond)
- [`conversations.scene.noticed.followup`](#conversations-scene-noticed-followup)
- [`conversations.scene.noticed.something_about_the_room.respond`](#conversations-scene-noticed-something-about-the-room-respond)
- [`conversations.scene.noticed.you_look_tired.respond`](#conversations-scene-noticed-you-look-tired-respond)
- [`conversations.topic.noticed.afflicted.followup`](#conversations-topic-noticed-afflicted-followup)
- [`conversations.topic.noticed.afflicted.respond`](#conversations-topic-noticed-afflicted-respond)
- [`conversations.topic.noticed.annoyed.boundary.followup`](#conversations-topic-noticed-annoyed-boundary-followup)
- [`conversations.topic.noticed.annoyed.explain.followup`](#conversations-topic-noticed-annoyed-explain-followup)
- [`conversations.topic.noticed.annoyed.repair.followup`](#conversations-topic-noticed-annoyed-repair-followup)
- [`conversations.topic.noticed.annoyed.respond`](#conversations-topic-noticed-annoyed-respond)
- [`conversations.topic.noticed.elated.deflated.followup`](#conversations-topic-noticed-elated-deflated-followup)
- [`conversations.topic.noticed.elated.followup`](#conversations-topic-noticed-elated-followup)
- [`conversations.topic.noticed.elated.respond`](#conversations-topic-noticed-elated-respond)
- [`conversations.topic.noticed.expecting.followup`](#conversations-topic-noticed-expecting-followup)
- [`conversations.topic.noticed.expecting.respond`](#conversations-topic-noticed-expecting-respond)
- [`conversations.topic.noticed.fine.admitted.followup`](#conversations-topic-noticed-fine-admitted-followup)
- [`conversations.topic.noticed.fine.closed.followup`](#conversations-topic-noticed-fine-closed-followup)
- [`conversations.topic.noticed.fine.followup`](#conversations-topic-noticed-fine-followup)
- [`conversations.topic.noticed.fine.opened.followup`](#conversations-topic-noticed-fine-opened-followup)
- [`conversations.topic.noticed.fine.respond`](#conversations-topic-noticed-fine-respond)
- [`conversations.topic.noticed.grieving.hostile.followup`](#conversations-topic-noticed-grieving-hostile-followup)
- [`conversations.topic.noticed.grieving.quiet.followup`](#conversations-topic-noticed-grieving-quiet-followup)
- [`conversations.topic.noticed.grieving.respond`](#conversations-topic-noticed-grieving-respond)

---

## `conversations.arc.noticed.resume.followup`

**Reached from 3 route(s):** `conversations.arc.noticed.resume.respond` / `sorry_i_didnt`; `conversations.arc.noticed.resume.respond` / `how_is_it_now`; `conversations.arc.noticed.resume.respond` / `ill_come_regular`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.resume.how_is_it_now` — e.g. "Stiff in the morning and forgotten by noon. That's the shape of getting better."
- `conversations.noticed.resume.ill_come_regular` — e.g. "Then say a day and keep to it. A regular thing is only regular if it has a name."
- `conversations.noticed.resume.sorry_i_didnt` — e.g. "Then come now and we'll say no more about it. That's the whole of the apology I want."


```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.noticed.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.noticed.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.resume.sorry_i_didnt`, `noticed.resume.how_is_it_now`, `noticed.resume.ill_come_regular`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `keeping`(1.2), `mending`(0.3), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `noticed.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.noticed.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.thank_you_for_telling`: the villager accepts. Subject `noticed.injury`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.noticed.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.noticed.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.resume.sorry_i_didnt`, `noticed.resume.how_is_it_now`, `noticed.resume.ill_come_regular`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `leave`(0.6), `mending`(0.3), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.followup.leave_it_with_you   [23 chars]
    en  I'll leave it with you.
    >>  ............................................
    pt  Vou deixar isso com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.noticed.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.leave_it_with_you`: the villager accepts. Subject `noticed.injury`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.noticed.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.noticed.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.resume.sorry_i_didnt`, `noticed.resume.how_is_it_now`, `noticed.resume.ill_come_regular` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.noticed.resume.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.leave`: the villager accepts. Subject `noticed.injury`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.noticed.resume.respond / leave
```

```text
  dialogue.conversations.noticed.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.noticed.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.noticed.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.noticed.resume.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.resume` — e.g. "You came back. I'd bet against it, quietly, and I'm glad to have lost."


```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.noticed.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.noticed.resume.respond   [26 chars]
    en  That's where I am with it.
    >>  ............................................
    pt  É aí que estou com isso.
    >>  ............................................
```


### Button `sorry_i_didnt` — "I'm sorry I didn't come."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.resume.sorry_i_didnt` — accepted phrasings: "i am sorry i did not come"; "i should have come"; "i meant to come and did not"
  - scored words: `come`(0.5), `sorry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.respond.sorry_i_didnt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.respond.sorry_i_didnt   [24 chars]
    en  I'm sorry I didn't come.
    >>  ............................................
    pt  Desculpe por não ter ido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.resume.sorry`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1, warmth +2  _(recorded under topic `noticed.resume.sorry_i_didnt`)_
- Does: session `turn`
- Then opens: `conversations.arc.noticed.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.sorry_i_didnt
WHO    VILLAGER — what the player reads after pressing "I'm sorry I didn't come."
       spoken on: conversations.arc.noticed.resume.respond, button `sorry_i_didnt`
       leaves the player on: conversations.arc.noticed.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.sorry_i_didnt`: the villager accepts. Subject `noticed.injury`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.resume.sorry_i_didnt/1   [85 chars]
    en  Then come now and we'll say no more about it. That's the whole of the apology I want.
    >>  ............................................
    pt  Então venha agora e não se fala mais nisso. É toda a desculpa que eu quero.
    >>  ............................................
  dialogue.conversations.noticed.resume.sorry_i_didnt/2   [79 chars]
    en  People don't say that. They explain instead, and the explaining is what stings.
    >>  ............................................
    pt  As pessoas não dizem isso. Explicam, e é a explicação que dói.
    >>  ............................................
  dialogue.conversations.noticed.resume.sorry_i_didnt/3   [76 chars]
    en  Accepted, and I mean it. I've done the same to somebody and never gone back.
    >>  ............................................
    pt  Aceito, e falo sério. Já fiz o mesmo com alguém e nunca voltei.
    >>  ............................................
```


### Button `how_is_it_now` — "How is it now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.resume.how_is_it_now` — accepted phrasings: "how is it now"; "how is it healing"; "is it any better now"
  - the message must contain one of: `healing`
  - scored words: `healing`(1.0), `how`(0.3), `now`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.respond.how_is_it_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.respond.how_is_it_now   [14 chars]
    en  How is it now?
    >>  ............................................
    pt  Como está agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.noticed.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.how_is_it_now
WHO    VILLAGER — what the player reads after pressing "How is it now?"
       spoken on: conversations.arc.noticed.resume.respond, button `how_is_it_now`
       leaves the player on: conversations.arc.noticed.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.how_is_it_now`: the villager reports. Subject `noticed.injury`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.resume.how_is_it_now/1   [79 chars]
    en  Stiff in the morning and forgotten by noon. That's the shape of getting better.
    >>  ............................................
    pt  Duro de manhã e esquecido ao meio-dia. É esse o formato de melhorar.
    >>  ............................................
  dialogue.conversations.noticed.resume.how_is_it_now/2   [83 chars]
    en  The same, which after three weeks means worse. I've an appointment with the cleric.
    >>  ............................................
    pt  Igual, o que depois de três semanas significa pior. Tenho hora com o clérigo.
    >>  ............................................
  dialogue.conversations.noticed.resume.how_is_it_now/3   [71 chars]
    en  Better than it looks. It looks appalling, so that's a low bar to clear.
    >>  ............................................
    pt  Melhor do que parece. Parece horrível, então é uma régua baixa.
    >>  ............................................
```


### Button `ill_come_regular` — "I'll make it a regular thing."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.resume.ill_come_regular` — accepted phrasings: "i will make it a regular thing"; "i will look in every week"; "i will make a habit of it"
  - the message must contain one of: `regular`
  - scored words: `regular`(1.5), `thing`(0.3)

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.respond.ill_come_regular
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.respond.ill_come_regular   [29 chars]
    en  I'll make it a regular thing.
    >>  ............................................
    pt  Vou fazer disso uma coisa regular.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.resume.regular`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `noticed.resume.ill_come_regular`)_
- Does: session `turn`
- Then opens: `conversations.arc.noticed.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.ill_come_regular
WHO    VILLAGER — what the player reads after pressing "I'll make it a regular thing."
       spoken on: conversations.arc.noticed.resume.respond, button `ill_come_regular`
       leaves the player on: conversations.arc.noticed.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.ill_come_regular`: the villager accepts. Subject `noticed.injury`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.resume.ill_come_regular/1   [80 chars]
    en  Then say a day and keep to it. A regular thing is only regular if it has a name.
    >>  ............................................
    pt  Então diga um dia e cumpra. Uma coisa regular só é regular se tiver nome.
    >>  ............................................
  dialogue.conversations.noticed.resume.ill_come_regular/2   [79 chars]
    en  Careful — I'll come to depend on it, and depending on people is a habit I lost.
    >>  ............................................
    pt  Cuidado — vou passar a depender, e depender de gente é um hábito que perdi.
    >>  ............................................
  dialogue.conversations.noticed.resume.ill_come_regular/3   [67 chars]
    en  That's more than the cleric offered, and the cleric is paid for it.
    >>  ............................................
    pt  É mais do que o clérigo ofereceu, e o clérigo é pago pra isso.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.noticed.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.noticed.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.noticed.resume.respond.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.noticed.resume.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.leave`: the villager accepts. Subject `noticed.injury`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.noticed.resume.followup / leave
```

> Written out in full under **`conversations.arc.noticed.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.noticed.followup`

**Reached from 4 route(s):** `conversations.scene.noticed.something_about_the_room.respond` / `ask_what_it_means`; `conversations.scene.noticed.something_about_the_room.respond` / `say_you_hadnt_noticed`; `conversations.scene.noticed.you_look_tired.respond` / `admit_it`; `conversations.scene.noticed.you_look_tired.respond` / `deflect_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.noticed.something_about_the_room.acknowledged` — e.g. "I stand in one place for a great deal of the day. Attention is mostly just staying still where things happen."
- `conversations.scene.noticed.something_about_the_room.explained` — e.g. "Very little, on its own. Three small things in a row mean something. One is just a Tuesday."
- `conversations.scene.noticed.you_look_tired.accepted_the_boundary` — e.g. "Fair enough. I said it so that it had been said, not so that you had to answer it."
- `conversations.scene.noticed.you_look_tired.received` — e.g. "Then I will stop asking and start being about. That is the useful half and it is the half people forget to offer."


```text
POOL   dialogue key: dialogue.conversations.scene.noticed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.noticed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.noticed.followup   [29 chars]
    en  Anything else you've noticed?
    >>  ............................................
    pt  Mais alguma coisa que você reparou?
    >>  ............................................
```


### Button `leave` — "We'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:noticed.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.followup.leave   [21 chars]
    en  We'll leave it there.
    >>  ............................................
    pt  Vamos deixar assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave it there."
       spoken on: conversations.scene.noticed.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.scene.leaving`: the villager accepts. Subject `noticed.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.noticed.something_about_the_room.respond / leave; conversations.scene.noticed.you_look_tired.respond / leave
```

```text
  dialogue.conversations.scene.noticed.leaving/1   [22 chars]
    en  I will leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
  dialogue.conversations.scene.noticed.leaving/2   [28 chars]
    en  Right. I have said my piece.
    >>  ............................................
    pt  Certo. Já disse o que tinha.
    >>  ............................................
  dialogue.conversations.scene.noticed.leaving/3   [26 chars]
    en  That is all I meant by it.
    >>  ............................................
    pt  Era só isso que eu queria dizer.
    >>  ............................................
```

---


## `conversations.scene.noticed.something_about_the_room.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.noticed.something_about_the_room` — e.g. "The lane has been quieter since the harvest. Not unhappy. Quieter. There is a difference and it takes a season to hear."


```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.noticed.something_about_the_room.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room.respond   [25 chars]
    en  Something you've spotted.
    >>  ............................................
    pt  Algo que você notou.
    >>  ............................................
```


### Button `ask_what_it_means` — "What do you make of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.something_about_the_room.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.noticed.something_about_the_room.ask_what_it_means` — accepted phrasings: "what do you make of it"; "what do you make of it"; "what do you reckon that means"
  - the message must contain one of: `make`, `reckon`, `means`
  - scored words: `make`(1.8), `reckon`(1.8), `means`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room.respond.ask_what_it_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.something_about_the_room.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room.respond.ask_what_it_means   [23 chars]
    en  What do you make of it?
    >>  ............................................
    pt  O que você acha disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `noticed.the_village`)_
- Does: session `turn`
- Then opens: `conversations.scene.noticed.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room.explained
WHO    VILLAGER — what the player reads after pressing "What do you make of it?"
       spoken on: conversations.scene.noticed.something_about_the_room.respond, button `ask_what_it_means`
       leaves the player on: conversations.scene.noticed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.something_about_the_room.open.explained`: the villager explains. Subject `noticed.the_village`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:noticed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room.explained/1   [91 chars]
    en  Very little, on its own. Three small things in a row mean something. One is just a Tuesday.
    >>  ............................................
    pt  Muito pouco, sozinho. Três coisinhas em sequência significam algo. Uma é só uma terça-feira.
    >>  ............................................
  dialogue.conversations.scene.noticed.something_about_the_room.explained/2   [128 chars]
    en  I keep it in mind and I do not act on it. That has served me better than the alternative, which is being right loudly and early.
    >>  ............................................
    pt  Guardo na cabeça e não ajo. Isso me serviu melhor que a alternativa, que é estar certa em voz alta e cedo demais.
    >>  ............................................
  dialogue.conversations.scene.noticed.something_about_the_room.explained/3   [101 chars]
    en  Ask me in a month. If it is still true in a month it is a fact, and until then it is a habit of mine.
    >>  ............................................
    pt  Me pergunte daqui a um mês. Se ainda for verdade em um mês, é um fato, e até lá é um hábito meu.
    >>  ............................................
```


### Button `say_you_hadnt_noticed` — "You pay close attention."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.something_about_the_room.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.noticed.something_about_the_room.say_you_hadnt_noticed` — accepted phrasings: "you pay close attention"; "you pay close attention"; "you notice a great deal"
  - the message must contain one of: `attention`, `notice`
  - scored words: `attention`(1.8), `notice`(1.8), `pay`(0.8), `close`(0.8), `great`(0.8), `deal`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room.respond.say_you_hadnt_noticed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.something_about_the_room.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room.respond.say_you_hadnt_noticed   [24 chars]
    en  You pay close attention.
    >>  ............................................
    pt  Você presta muita atenção.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, warmth +1  _(recorded under topic `noticed.the_village`)_
- Does: session `turn`
- Then opens: `conversations.scene.noticed.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room.acknowledged
WHO    VILLAGER — what the player reads after pressing "You pay close attention."
       spoken on: conversations.scene.noticed.something_about_the_room.respond, button `say_you_hadnt_noticed`
       leaves the player on: conversations.scene.noticed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.something_about_the_room.open.acknowledged`: the villager accepts. Subject `noticed.the_village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:noticed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room.acknowledged/1   [109 chars]
    en  I stand in one place for a great deal of the day. Attention is mostly just staying still where things happen.
    >>  ............................................
    pt  Eu fico parada num lugar só boa parte do dia. Atenção é quase só ficar quieta onde as coisas acontecem.
    >>  ............................................
  dialogue.conversations.scene.noticed.something_about_the_room.acknowledged/2   [105 chars]
    en  It is the only skill I am sure of, and it is not much use unless somebody asks, which almost nobody does.
    >>  ............................................
    pt  É a única habilidade de que eu tenho certeza, e não serve para muita coisa a menos que alguém pergunte, o que quase ninguém faz.
    >>  ............................................
  dialogue.conversations.scene.noticed.something_about_the_room.acknowledged/3   [103 chars]
    en  Thank you. Half the village thinks it is nosiness and the other half has never thought about it at all.
    >>  ............................................
    pt  Obrigada. Metade da vila acha que é intrometimento e a outra metade nunca pensou no assunto.
    >>  ............................................
```


### Button `leave` — "Thanks for noticing."

*stance family `exit` · tone `plain` · answers the beat(s) `noticed.something_about_the_room.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.something_about_the_room.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room.respond.leave   [20 chars]
    en  Thanks for noticing.
    >>  ............................................
    pt  Obrigado por reparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for noticing."
       spoken on: conversations.scene.noticed.something_about_the_room.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.scene.leaving`: the villager accepts. Subject `noticed.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.noticed.followup / leave; conversations.scene.noticed.you_look_tired.respond / leave
```

> Written out in full under **`conversations.scene.noticed.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.noticed.you_look_tired.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.noticed.you_look_tired` — e.g. "You have been walking differently. I am not going to make a thing of it, and I did notice."


```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.noticed.you_look_tired.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.noticed.you_look_tired.respond   [25 chars]
    en  Something you've noticed.
    >>  ............................................
    pt  Algo que você reparou.
    >>  ............................................
```


### Button `admit_it` — "You read that correctly."

*stance family `self_disclosure` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.you_look_tired.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.noticed.you_look_tired.admit_it` — accepted phrasings: "you read that correctly"; "you have read that correctly"; "you are right about that"
  - the message must contain one of: `correctly`, `right`, `read`
  - scored words: `correctly`(1.8), `right`(1.8), `read`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired.respond.admit_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.you_look_tired.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.you_look_tired.respond.admit_it   [24 chars]
    en  You read that correctly.
    >>  ............................................
    pt  Você leu certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.noticed.admitted`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `noticed.the_player`)_
- Does: session `turn`
- Then opens: `conversations.scene.noticed.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired.received
WHO    VILLAGER — what the player reads after pressing "You read that correctly."
       spoken on: conversations.scene.noticed.you_look_tired.respond, button `admit_it`
       leaves the player on: conversations.scene.noticed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.you_look_tired.open.received`: the villager accepts. Subject `noticed.the_player`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:noticed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.noticed.you_look_tired.received/1   [113 chars]
    en  Then I will stop asking and start being about. That is the useful half and it is the half people forget to offer.
    >>  ............................................
    pt  Então eu paro de perguntar e passo a estar por perto. É a metade útil e é a metade que as pessoas esquecem de oferecer.
    >>  ............................................
  dialogue.conversations.scene.noticed.you_look_tired.received/2   [97 chars]
    en  Thank you for saying so plainly. I had a whole speech prepared for if you had said you were fine.
    >>  ............................................
    pt  Obrigada por dizer com franqueza. Eu tinha um discurso inteiro pronto caso você dissesse que estava bem.
    >>  ............................................
  dialogue.conversations.scene.noticed.you_look_tired.received/3   [96 chars]
    en  Right. I am not going to ask what it is. If you want to say, I am usually here at the same hour.
    >>  ............................................
    pt  Certo. Não vou perguntar o que é. Se você quiser contar, eu costumo estar aqui na mesma hora.
    >>  ............................................
```


### Button `deflect_it` — "I would rather leave that where it is."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `noticed.you_look_tired.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.noticed.you_look_tired.deflect_it` — accepted phrasings: "i would rather leave that where it is"; "i would rather leave that where it is"; "let us talk about something else"
  - the message must contain one of: `rather`, `something`, `else`
  - scored words: `rather`(1.8), `something`(1.8), `else`(1.8), `leave`(0.8), `where`(0.8), `let`(0.8), `talk`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired.respond.deflect_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.you_look_tired.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.you_look_tired.respond.deflect_it   [38 chars]
    en  I would rather leave that where it is.
    >>  ............................................
    pt  Prefiro deixar isso onde está.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `noticed.the_player`)_
- Does: session `turn`
- Then opens: `conversations.scene.noticed.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired.accepted_the_boundary
WHO    VILLAGER — what the player reads after pressing "I would rather leave that where it is."
       spoken on: conversations.scene.noticed.you_look_tired.respond, button `deflect_it`
       leaves the player on: conversations.scene.noticed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.you_look_tired.open.accepted_the_boundary`: the villager accepts. Subject `noticed.the_player`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `topic:noticed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.noticed.you_look_tired.accepted_the_boundary/1   [82 chars]
    en  Fair enough. I said it so that it had been said, not so that you had to answer it.
    >>  ............................................
    pt  Justo. Eu disse para que ficasse dito, não para que você tivesse que responder.
    >>  ............................................
  dialogue.conversations.scene.noticed.you_look_tired.accepted_the_boundary/2   [97 chars]
    en  Right. I will not raise it again. If it changes, you know where I am and you know I noticed once.
    >>  ............................................
    pt  Certo. Não vou levantar de novo. Se mudar, você sabe onde eu estou e sabe que eu reparei uma vez.
    >>  ............................................
  dialogue.conversations.scene.noticed.you_look_tired.accepted_the_boundary/3   [85 chars]
    en  That is your business and I meant it as an observation, not a door I was standing in.
    >>  ............................................
    pt  É assunto seu, e eu disse como observação, não como uma porta em que eu estivesse parada.
    >>  ............................................
```


### Button `leave` — "Thanks for noticing."

*stance family `exit` · tone `plain` · answers the beat(s) `noticed.you_look_tired.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.noticed.you_look_tired.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.noticed.you_look_tired.respond.leave   [20 chars]
    en  Thanks for noticing.
    >>  ............................................
    pt  Obrigado por reparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for noticing."
       spoken on: conversations.scene.noticed.you_look_tired.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.scene.leaving`: the villager accepts. Subject `noticed.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.noticed.followup / leave; conversations.scene.noticed.something_about_the_room.respond / leave
```

> Written out in full under **`conversations.scene.noticed.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.noticed.afflicted.followup`

**Reached from 3 route(s):** `conversations.topic.noticed.afflicted.respond` / `what_frightens`; `conversations.topic.noticed.afflicted.respond` / `how_long`; `conversations.topic.noticed.afflicted.respond` / `stay`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.afflicted.how_long` — e.g. "Long enough to have stopped counting, and not long enough to be used to it."
- `conversations.noticed.afflicted.stay` — e.g. "People say that. Then the season turns and the saying turns with it."
- `conversations.noticed.afflicted.what_frightens` — e.g. "Not the ending. The part before it, where I'm still here and not myself."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.afflicted.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.afflicted.followup   [29 chars]
    en  So that's where I am with it.
    >>  ............................................
    pt  É aí que eu estou com isso.
    >>  ............................................
```


### Button `find_a_cure` — "Then we find whoever can undo it."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.afflicted.what_frightens`, `noticed.afflicted.how_long`, `noticed.afflicted.stay`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.afflicted.followup.cure` — accepted phrasings: "then we find whoever can undo it"; "there must be a cure"; "we will find a way to undo it"
  - the message must contain one of: `undo`, `cure`
  - scored words: `undo`(1.5), `cure`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.followup.find_a_cure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.followup.find_a_cure   [33 chars]
    en  Then we find whoever can undo it.
    >>  ............................................
    pt  Então vamos achar quem possa desfazer isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.afflicted.followup.cure`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `noticed.afflicted.followup.cure`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.followup.cure
WHO    VILLAGER — what the player reads after pressing "Then we find whoever can undo it."
       spoken on: conversations.topic.noticed.afflicted.followup, button `find_a_cure`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.followup.cure`: the villager accepts. Subject `noticed.affliction`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.followup.cure/1   [62 chars]
    en  "We." I'd not let myself think in that word until you said it.
    >>  ............................................
    pt  "Vamos." Eu não tinha me deixado pensar nessa palavra até você dizer.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.followup.cure/2   [75 chars]
    en  Then you'll be the first person to say so out loud instead of looking away.
    >>  ............................................
    pt  Então você é a primeira pessoa a dizer isso em voz alta em vez de desviar o olhar.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.followup.cure/3   [76 chars]
    en  There may be nobody. But looking is better than the sitting I've been doing.
    >>  ............................................
    pt  Pode não haver ninguém. Mas procurar é melhor que o sentar que eu venho fazendo.
    >>  ............................................
```


### Button `no_different` — "You're no different to me for it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.afflicted.what_frightens`, `noticed.afflicted.how_long`, `noticed.afflicted.stay`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.afflicted.followup.same` — accepted phrasings: "you are no different to me"; "you are the same to me"; "it changes nothing between us"
  - the message must contain one of: `different`
  - scored words: `different`(1.5), `same`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.followup.no_different
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.followup.no_different   [33 chars]
    en  You're no different to me for it.
    >>  ............................................
    pt  Você não é diferente pra mim por causa disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.afflicted.followup.same`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2, warmth +3  _(recorded under topic `noticed.afflicted.followup.same`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.followup.same
WHO    VILLAGER — what the player reads after pressing "You're no different to me for it."
       spoken on: conversations.topic.noticed.afflicted.followup, button `no_different`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.followup.same`: the villager accepts. Subject `noticed.affliction`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.followup.same/1   [78 chars]
    en  ...Say that again in a month and I'll believe it. Say it now and I'll hold it.
    >>  ............................................
    pt  ...Diga de novo em um mês e eu acredito. Diga agora e eu seguro.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.followup.same/2   [76 chars]
    en  Two people have crossed the lane to avoid me this week. So that lands, %1$s.
    >>  ............................................
    pt  Duas pessoas atravessaram a rua pra me evitar esta semana. Então isso pega, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.followup.same/3   [77 chars]
    en  You may change your mind. If you do, tell me plainly. Don't just stop coming.
    >>  ............................................
    pt  Você pode mudar de ideia. Se mudar, me diga na cara. Não simplesmente pare de vir.
    >>  ............................................
```


### Button `leave` — "I'll leave you be for now."

*stance family `exit` · tone `gentle` · outcome `conversation_ended` · answers the beat(s) `noticed.afflicted.what_frightens`, `noticed.afflicted.how_long`, `noticed.afflicted.stay` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.followup.leave   [26 chars]
    en  I'll leave you be for now.
    >>  ............................................
    pt  Vou te deixar em paz por enquanto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you be for now."
       spoken on: conversations.topic.noticed.afflicted.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.followup.leave`: the villager accepts. Subject `noticed.affliction`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.followup.leave/1   [38 chars]
    en  So it is. Thank you for asking at all.
    >>  ............................................
    pt  É assim mesmo. Obrigado por ter perguntado.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.followup.leave/2   [25 chars]
    en  Right. Come back, though.
    >>  ............................................
    pt  Certo. Mas volte.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.followup.leave/3   [36 chars]
    en  Go on. And %1$s — don't stop asking.
    >>  ............................................
    pt  Vá lá. E %1$s — não pare de perguntar.
    >>  ............................................
```

---


## `conversations.topic.noticed.afflicted.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.afflicted` — e.g. "Frightened, if you want it plainly. There's something in me that wasn't there last month."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.afflicted.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.afflicted.respond   [56 chars]
    en  You asked at a bad time, and I'm going to answer anyway.
    >>  ............................................
    pt  Você perguntou numa hora ruim, e eu vou responder mesmo assim.
    >>  ............................................
```


### Button `what_frightens` — "What is it that frightens you most?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `noticed.afflicted.open` · offered only once the villager has actually said `noticed:afflicted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.afflicted.what_frightens` — accepted phrasings: "what is it that frightens you most"; "what are you most afraid of"; "what frightens you about it"
  - the message must contain one of: `frightens`, `afraid`
  - scored words: `frightens`(1.5), `afraid`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.respond.what_frightens
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.respond.what_frightens   [35 chars]
    en  What is it that frightens you most?
    >>  ............................................
    pt  O que mais te assusta nisso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.afflicted.followup`
- …where the player's next choices will be: "Then we find whoever can undo it." | "You're no different to me for it." | "I'll leave you be for now."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.what_frightens
WHO    VILLAGER — what the player reads after pressing "What is it that frightens you most?"
       spoken on: conversations.topic.noticed.afflicted.respond, button `what_frightens`
       leaves the player on: conversations.topic.noticed.afflicted.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.what_frightens`: the villager discloses. Subject `noticed.affliction`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.what_frightens/1   [72 chars]
    en  Not the ending. The part before it, where I'm still here and not myself.
    >>  ............................................
    pt  Não o fim. A parte antes dele, em que ainda estou aqui e não sou eu.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.what_frightens/2   [77 chars]
    en  That I'll do something I'd never do, and everyone will remember that instead.
    >>  ............................................
    pt  Que eu faça algo que eu nunca faria, e todos lembrem disso em vez do resto.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.what_frightens/3   [86 chars]
    en  Being handled gently. I've watched it happen to somebody else and I'd rather anything.
    >>  ............................................
    pt  Ser tratado com pena. Vi isso acontecer com outra pessoa e prefiro qualquer coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. Being here and not being me, and everyone watching it happen.
    >>  ............................................
    pt  Não o fim. Estar aqui e não ser eu, e todos vendo acontecer.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll hurt somebody. That's the whole of the fear and I've said it now.
    >>  ............................................
    pt  Que eu machuque alguém. É o medo inteiro e eu já disse.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. It would mean everyone had already decided how this goes.
    >>  ............................................
    pt  Ser tratado com pena. Significaria que já decidiram como isso termina.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. I've made my peace with endings. The part before, I haven't.
    >>  ............................................
    pt  Não o fim. Fiz as pazes com finais. Com a parte antes, não fiz.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, after a life of not doing those things.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, depois de uma vida sem fazer essas coisas.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've been useful here since before you were born.
    >>  ............................................
    pt  Ser tratado com pena. Fui útil aqui desde antes de você nascer.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before it, where I'm still here and not myself.
    >>  ............................................
    pt  Não o fim. A parte antes, em que ainda estou aqui e não sou eu.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, and that's what gets remembered.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, e que lembrem disso.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've watched it happen to somebody else.
    >>  ............................................
    pt  Ser tratado com pena. Vi isso acontecer com outra pessoa.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before it, where I'm still here and not myself.
    >>  ............................................
    pt  Não o fim. A parte antes, em que ainda estou aqui e não sou eu.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, and that's what gets remembered.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, e que lembrem disso.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've watched it happen to somebody else.
    >>  ............................................
    pt  Ser tratado com pena. Vi isso acontecer com outra pessoa.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part where I'm still here and you don't recognise me.
    >>  ............................................
    pt  Não o fim. A parte em que ainda estou aqui e você não me reconhece.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do — to you, most likely, since you're nearest.
    >>  ............................................
    pt  Que eu faça algo que nunca faria — com você, provavelmente, já que está mais perto.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Don't you start, %1$s. You're the last one who talks to me straight.
    >>  ............................................
    pt  Ser tratado com pena. Não comece, %1$s. Você é o último que fala reto comigo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part where I'm still here and you don't recognise me.
    >>  ............................................
    pt  Não o fim. A parte em que ainda estou aqui e você não me reconhece.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do — to you, most likely, since you're nearest.
    >>  ............................................
    pt  Que eu faça algo que nunca faria — com você, provavelmente, já que está mais perto.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Don't you start, %1$s. You're the last one who talks to me straight.
    >>  ............................................
    pt  Ser tratado com pena. Não comece, %1$s. Você é o último que fala reto comigo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part where I'm still here and you don't recognise me.
    >>  ............................................
    pt  Não o fim. A parte em que ainda estou aqui e você não me reconhece.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do — to you, most likely, since you're nearest.
    >>  ............................................
    pt  Que eu faça algo que nunca faria — com você, provavelmente, já que está mais perto.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Don't you start, %1$s. You're the last one who talks to me straight.
    >>  ............................................
    pt  Ser tratado com pena. Não comece, %1$s. Você é o último que fala reto comigo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. Being here and not being me, and everyone watching it happen.
    >>  ............................................
    pt  Não o fim. Estar aqui e não ser eu, e todos vendo acontecer.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll hurt somebody. That's the whole of the fear and I've said it now.
    >>  ............................................
    pt  Que eu machuque alguém. É o medo inteiro e eu já disse.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. It would mean everyone had already decided how this goes.
    >>  ............................................
    pt  Ser tratado com pena. Significaria que já decidiram como isso termina.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before it, where I'm still here and not myself.
    >>  ............................................
    pt  Não o fim. A parte antes, em que ainda estou aqui e não sou eu.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, and that's what gets remembered.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, e que lembrem disso.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've watched it happen to somebody else.
    >>  ............................................
    pt  Ser tratado com pena. Vi isso acontecer com outra pessoa.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before it, where I'm still here and not myself.
    >>  ............................................
    pt  Não o fim. A parte antes, em que ainda estou aqui e não sou eu.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, and that's what gets remembered.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, e que lembrem disso.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've watched it happen to somebody else.
    >>  ............................................
    pt  Ser tratado com pena. Vi isso acontecer com outra pessoa.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before.
    >>  ............................................
    pt  Não o fim. A parte antes.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  Doing something I'd never do.
    >>  ............................................
    pt  Fazer algo que eu nunca faria.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently.
    >>  ............................................
    pt  Ser tratado com pena.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. I've made my peace with endings. The part before, I haven't.
    >>  ............................................
    pt  Não o fim. Fiz as pazes com finais. Com a parte antes, não fiz.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, after a life of not doing those things.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, depois de uma vida sem fazer essas coisas.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've been useful here since before you were born.
    >>  ............................................
    pt  Ser tratado com pena. Fui útil aqui desde antes de você nascer.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before.
    >>  ............................................
    pt  Não o fim. A parte antes.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  Doing something I'd never do.
    >>  ............................................
    pt  Fazer algo que eu nunca faria.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently.
    >>  ............................................
    pt  Ser tratado com pena.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. I've made my peace with endings. The part before, I haven't.
    >>  ............................................
    pt  Não o fim. Fiz as pazes com finais. Com a parte antes, não fiz.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, after a life of not doing those things.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, depois de uma vida sem fazer essas coisas.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've been useful here since before you were born.
    >>  ............................................
    pt  Ser tratado com pena. Fui útil aqui desde antes de você nascer.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The middle, where I'm still talking and it isn't me talking.
    >>  ............................................
    pt  Não o fim. O meio, em que ainda estou falando e não sou eu falando.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something unforgivable and be remembered for the one week of it.
    >>  ............................................
    pt  Que eu faça algo imperdoável e seja lembrado só por aquela semana.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Everyone goes soft-voiced and I'd rather they shouted.
    >>  ............................................
    pt  Ser tratado com pena. Todos falam baixinho e eu preferia que gritassem.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The middle, where I'm still talking and it isn't me talking.
    >>  ............................................
    pt  Não o fim. O meio, em que ainda estou falando e não sou eu falando.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something unforgivable and be remembered for the one week of it.
    >>  ............................................
    pt  Que eu faça algo imperdoável e seja lembrado só por aquela semana.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Everyone goes soft-voiced and I'd rather they shouted.
    >>  ............................................
    pt  Ser tratado com pena. Todos falam baixinho e eu preferia que gritassem.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. I've made my peace with endings. The part before, I haven't.
    >>  ............................................
    pt  Não o fim. Fiz as pazes com finais. Com a parte antes, não fiz.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something I'd never do, after a life of not doing those things.
    >>  ............................................
    pt  Que eu faça algo que nunca faria, depois de uma vida sem fazer essas coisas.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. I've been useful here since before you were born.
    >>  ............................................
    pt  Ser tratado com pena. Fui útil aqui desde antes de você nascer.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. Being here and not being me, and everyone watching it happen.
    >>  ............................................
    pt  Não o fim. Estar aqui e não ser eu, e todos vendo acontecer.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll hurt somebody. That's the whole of the fear and I've said it now.
    >>  ............................................
    pt  Que eu machuque alguém. É o medo inteiro e eu já disse.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. It would mean everyone had already decided how this goes.
    >>  ............................................
    pt  Ser tratado com pena. Significaria que já decidiram como isso termina.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The part before.
    >>  ............................................
    pt  Não o fim. A parte antes.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  Doing something I'd never do.
    >>  ............................................
    pt  Fazer algo que eu nunca faria.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently.
    >>  ............................................
    pt  Ser tratado com pena.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The middle, where I'm still talking and it isn't me talking.
    >>  ............................................
    pt  Não o fim. O meio, em que ainda estou falando e não sou eu falando.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something unforgivable and be remembered for the one week of it.
    >>  ............................................
    pt  Que eu faça algo imperdoável e seja lembrado só por aquela semana.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Everyone goes soft-voiced and I'd rather they shouted.
    >>  ............................................
    pt  Ser tratado com pena. Todos falam baixinho e eu preferia que gritassem.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.what_frightens/1
    en  Not the ending. The middle, where I'm still talking and it isn't me talking.
    >>  ............................................
    pt  Não o fim. O meio, em que ainda estou falando e não sou eu falando.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.what_frightens/2
    en  That I'll do something unforgivable and be remembered for the one week of it.
    >>  ............................................
    pt  Que eu faça algo imperdoável e seja lembrado só por aquela semana.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.what_frightens/3
    en  Being handled gently. Everyone goes soft-voiced and I'd rather they shouted.
    >>  ............................................
    pt  Ser tratado com pena. Todos falam baixinho e eu preferia que gritassem.
    >>  ............................................
```

</details>


### Button `how_long` — "How long have you known?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.afflicted.open` · offered only once the villager has actually said `noticed:afflicted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.afflicted.how_long` — accepted phrasings: "how long have you known"; "when did you find out"; "how long has it been"
  - the message must contain one of: `known`
  - scored words: `known`(1.2), `when`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.respond.how_long
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.respond.how_long   [24 chars]
    en  How long have you known?
    >>  ............................................
    pt  Há quanto tempo você sabe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.afflicted.followup`
- …where the player's next choices will be: "Then we find whoever can undo it." | "You're no different to me for it." | "I'll leave you be for now."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.how_long
WHO    VILLAGER — what the player reads after pressing "How long have you known?"
       spoken on: conversations.topic.noticed.afflicted.respond, button `how_long`
       leaves the player on: conversations.topic.noticed.afflicted.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.how_long`: the villager discloses. Subject `noticed.affliction`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.how_long/1   [75 chars]
    en  Long enough to have stopped counting, and not long enough to be used to it.
    >>  ............................................
    pt  Tempo suficiente pra ter parado de contar, e não o bastante pra me acostumar.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.how_long/2   [65 chars]
    en  Since the frost. I told nobody until the day you happened to ask.
    >>  ............................................
    pt  Desde a geada. Não contei a ninguém até o dia em que você perguntou.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.how_long/3   [72 chars]
    en  A while. I kept deciding to say it tomorrow, and tomorrow kept arriving.
    >>  ............................................
    pt  Um tempo. Eu decidia contar amanhã, e o amanhã ficava chegando.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I count anyway, at night, and then I stop again.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Eu conto mesmo assim, à noite, e paro de novo.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. Telling you has taken more out of me than the months did.
    >>  ............................................
    pt  Desde a geada. Te contar me tirou mais do que os meses tiraram.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. Every day I decided tomorrow, because tomorrow I'd be braver.
    >>  ............................................
    pt  Um tempo. Todo dia eu decidia amanhã, porque amanhã eu seria mais corajoso.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I've learned that counting doesn't slow a thing down.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Aprendi que contar não desacelera nada.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've kept my own counsel about worse things than this.
    >>  ............................................
    pt  Desde a geada. Já guardei segredo de coisas piores que esta.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. At my age a while is a shorter word than it used to be.
    >>  ............................................
    pt  Um tempo. Na minha idade 'um tempo' é uma palavra mais curta do que era.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting, and not long enough to be used to it.
    >>  ............................................
    pt  Tempo suficiente pra ter parado de contar, e não o bastante pra me acostumar.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I told nobody until you asked.
    >>  ............................................
    pt  Desde a geada. Não contei a ninguém até você perguntar.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept deciding to say it tomorrow.
    >>  ............................................
    pt  Um tempo. Eu ficava decidindo contar amanhã.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting, and not long enough to be used to it.
    >>  ............................................
    pt  Tempo suficiente pra ter parado de contar, e não o bastante pra me acostumar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I told nobody until you asked.
    >>  ............................................
    pt  Desde a geada. Não contei a ninguém até você perguntar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept deciding to say it tomorrow.
    >>  ............................................
    pt  Um tempo. Eu ficava decidindo contar amanhã.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting. You're the first to ask, %1$s.
    >>  ............................................
    pt  Tempo o bastante pra ter parado de contar. Você é o primeiro a perguntar, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I nearly told you twice and lost my nerve both times.
    >>  ............................................
    pt  Desde a geada. Quase te contei duas vezes e perdi a coragem nas duas.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept meaning to say it to you and never found the right afternoon.
    >>  ............................................
    pt  Um tempo. Eu queria te dizer e nunca achei a tarde certa.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting. You're the first to ask, %1$s.
    >>  ............................................
    pt  Tempo o bastante pra ter parado de contar. Você é o primeiro a perguntar, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I nearly told you twice and lost my nerve both times.
    >>  ............................................
    pt  Desde a geada. Quase te contei duas vezes e perdi a coragem nas duas.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept meaning to say it to you and never found the right afternoon.
    >>  ............................................
    pt  Um tempo. Eu queria te dizer e nunca achei a tarde certa.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting. You're the first to ask, %1$s.
    >>  ............................................
    pt  Tempo o bastante pra ter parado de contar. Você é o primeiro a perguntar, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I nearly told you twice and lost my nerve both times.
    >>  ............................................
    pt  Desde a geada. Quase te contei duas vezes e perdi a coragem nas duas.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept meaning to say it to you and never found the right afternoon.
    >>  ............................................
    pt  Um tempo. Eu queria te dizer e nunca achei a tarde certa.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I count anyway, at night, and then I stop again.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Eu conto mesmo assim, à noite, e paro de novo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. Telling you has taken more out of me than the months did.
    >>  ............................................
    pt  Desde a geada. Te contar me tirou mais do que os meses tiraram.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. Every day I decided tomorrow, because tomorrow I'd be braver.
    >>  ............................................
    pt  Um tempo. Todo dia eu decidia amanhã, porque amanhã eu seria mais corajoso.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting, and not long enough to be used to it.
    >>  ............................................
    pt  Tempo suficiente pra ter parado de contar, e não o bastante pra me acostumar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I told nobody until you asked.
    >>  ............................................
    pt  Desde a geada. Não contei a ninguém até você perguntar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept deciding to say it tomorrow.
    >>  ............................................
    pt  Um tempo. Eu ficava decidindo contar amanhã.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to have stopped counting, and not long enough to be used to it.
    >>  ............................................
    pt  Tempo suficiente pra ter parado de contar, e não o bastante pra me acostumar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I told nobody until you asked.
    >>  ............................................
    pt  Desde a geada. Não contei a ninguém até você perguntar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. I kept deciding to say it tomorrow.
    >>  ............................................
    pt  Um tempo. Eu ficava decidindo contar amanhã.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough.
    >>  ............................................
    pt  Tempo o bastante.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost.
    >>  ............................................
    pt  Desde a geada.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while.
    >>  ............................................
    pt  Um tempo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I've learned that counting doesn't slow a thing down.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Aprendi que contar não desacelera nada.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've kept my own counsel about worse things than this.
    >>  ............................................
    pt  Desde a geada. Já guardei segredo de coisas piores que esta.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. At my age a while is a shorter word than it used to be.
    >>  ............................................
    pt  Um tempo. Na minha idade 'um tempo' é uma palavra mais curta do que era.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough.
    >>  ............................................
    pt  Tempo o bastante.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost.
    >>  ............................................
    pt  Desde a geada.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while.
    >>  ............................................
    pt  Um tempo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I've learned that counting doesn't slow a thing down.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Aprendi que contar não desacelera nada.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've kept my own counsel about worse things than this.
    >>  ............................................
    pt  Desde a geada. Já guardei segredo de coisas piores que esta.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. At my age a while is a shorter word than it used to be.
    >>  ............................................
    pt  Um tempo. Na minha idade 'um tempo' é uma palavra mais curta do que era.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. Which is a cheerful way of saying too long.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Que é um jeito alegre de dizer tempo demais.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've been very entertaining about it and told absolutely no one.
    >>  ............................................
    pt  Desde a geada. Fui muito divertido sobre isso e não contei absolutamente a ninguém.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while! Every day I decided to mention it tomorrow, and tomorrow kept turning up.
    >>  ............................................
    pt  Um tempo! Todo dia eu decidia mencionar amanhã, e o amanhã continuava chegando.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. Which is a cheerful way of saying too long.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Que é um jeito alegre de dizer tempo demais.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've been very entertaining about it and told absolutely no one.
    >>  ............................................
    pt  Desde a geada. Fui muito divertido sobre isso e não contei absolutamente a ninguém.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while! Every day I decided to mention it tomorrow, and tomorrow kept turning up.
    >>  ............................................
    pt  Um tempo! Todo dia eu decidia mencionar amanhã, e o amanhã continuava chegando.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I've learned that counting doesn't slow a thing down.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Aprendi que contar não desacelera nada.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've kept my own counsel about worse things than this.
    >>  ............................................
    pt  Desde a geada. Já guardei segredo de coisas piores que esta.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. At my age a while is a shorter word than it used to be.
    >>  ............................................
    pt  Um tempo. Na minha idade 'um tempo' é uma palavra mais curta do que era.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. I count anyway, at night, and then I stop again.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Eu conto mesmo assim, à noite, e paro de novo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. Telling you has taken more out of me than the months did.
    >>  ............................................
    pt  Desde a geada. Te contar me tirou mais do que os meses tiraram.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while. Every day I decided tomorrow, because tomorrow I'd be braver.
    >>  ............................................
    pt  Um tempo. Todo dia eu decidia amanhã, porque amanhã eu seria mais corajoso.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough.
    >>  ............................................
    pt  Tempo o bastante.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost.
    >>  ............................................
    pt  Desde a geada.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while.
    >>  ............................................
    pt  Um tempo.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. Which is a cheerful way of saying too long.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Que é um jeito alegre de dizer tempo demais.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've been very entertaining about it and told absolutely no one.
    >>  ............................................
    pt  Desde a geada. Fui muito divertido sobre isso e não contei absolutamente a ninguém.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while! Every day I decided to mention it tomorrow, and tomorrow kept turning up.
    >>  ............................................
    pt  Um tempo! Todo dia eu decidia mencionar amanhã, e o amanhã continuava chegando.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.how_long/1
    en  Long enough to stop counting. Which is a cheerful way of saying too long.
    >>  ............................................
    pt  Tempo o bastante pra parar de contar. Que é um jeito alegre de dizer tempo demais.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.how_long/2
    en  Since the frost. I've been very entertaining about it and told absolutely no one.
    >>  ............................................
    pt  Desde a geada. Fui muito divertido sobre isso e não contei absolutamente a ninguém.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.how_long/3
    en  A while! Every day I decided to mention it tomorrow, and tomorrow kept turning up.
    >>  ............................................
    pt  Um tempo! Todo dia eu decidia mencionar amanhã, e o amanhã continuava chegando.
    >>  ............................................
```

</details>


### Button `stay` — "I'm not going anywhere."

*stance family `candor` · tone `gentle` · outcome `qualified` · answers the beat(s) `noticed.afflicted.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.afflicted.stay` — accepted phrasings: "i am not going anywhere"; "i am staying right here"; "you will not be rid of me"
  - the message must contain one of: `anywhere`, `staying`
  - scored words: `anywhere`(1.2), `staying`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.respond.stay
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.respond.stay   [23 chars]
    en  I'm not going anywhere.
    >>  ............................................
    pt  Eu não vou a lugar nenhum.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.afflicted.stay`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `noticed.afflicted.stay`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.afflicted.followup`
- …where the player's next choices will be: "Then we find whoever can undo it." | "You're no different to me for it." | "I'll leave you be for now."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.stay
WHO    VILLAGER — what the player reads after pressing "I'm not going anywhere."
       spoken on: conversations.topic.noticed.afflicted.respond, button `stay`
       leaves the player on: conversations.topic.noticed.afflicted.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.stay`: the villager qualifys. Subject `noticed.affliction`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.stay/1   [68 chars]
    en  People say that. Then the season turns and the saying turns with it.
    >>  ............................................
    pt  As pessoas dizem isso. Aí a estação vira e o dito vira junto.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.stay/2   [73 chars]
    en  ...Alright. I'll not argue with you today. I haven't the strength for it.
    >>  ............................................
    pt  ...Está bem. Não vou discutir com você hoje. Não tenho força pra isso.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.stay/3   [84 chars]
    en  Then stand there and let me finish being frightened. It passes quicker with company.
    >>  ............................................
    pt  Então fique aí e me deixe acabar de ter medo. Passa mais rápido com companhia.
    >>  ............................................
```


### Button `dismiss` — "Everyone's got something wrong with them."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.afflicted.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.afflicted.dismissed` — accepted phrasings: "everyone has got something wrong with them"; "we all have our problems"; "that is simply how life goes"
  - the message must contain one of: `everyone`
  - scored words: `everyone`(1.2), `something`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.respond.dismiss   [41 chars]
    en  Everyone's got something wrong with them.
    >>  ............................................
    pt  Todo mundo tem alguma coisa errada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `noticed.afflicted.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -2, warmth -3  _(recorded under topic `noticed.afflicted.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.dismissed
WHO    VILLAGER — what the player reads after pressing "Everyone's got something wrong with them."
       spoken on: conversations.topic.noticed.afflicted.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.dismissed`: the villager hurts. Subject `noticed.affliction`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.dismissed/1   [61 chars]
    en  Not this. And you'd know that if you'd waited two more words.
    >>  ............................................
    pt  Isso não. E você saberia se tivesse esperado mais duas palavras.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.dismissed/2   [68 chars]
    en  Then go and be even-handed at somebody else. I've had my fill of it.
    >>  ............................................
    pt  Então vá ser imparcial com outra pessoa. Já tive o bastante disso.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.dismissed/3   [70 chars]
    en  That's the answer I was braced for. You still managed to make it land.
    >>  ............................................
    pt  É a resposta pra qual eu estava preparado. Você ainda conseguiu fazer doer.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.afflicted.dismissed/1
    en  ...Not this. Please. I've barely got the words out.
    >>  ............................................
    pt  ...Isso não. Por favor. Eu mal consegui tirar as palavras.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go, before I say something I'd have to apologise for later.
    >>  ............................................
    pt  Então vá, antes que eu diga algo pelo qual teria que pedir desculpa depois.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that all week and I still wasn't ready when it came.
    >>  ............................................
    pt  Eu me preparei pra isso a semana toda e ainda não estava pronto quando veio.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. I've heard every version of that sentence and none of them fit this.
    >>  ............................................
    pt  Isso não. Já ouvi todas as versões dessa frase e nenhuma serve pra isto.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed elsewhere. I've no patience left to spend on it.
    >>  ............................................
    pt  Então vá ser imparcial em outro lugar. Não me sobrou paciência pra gastar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for it. Sixty years and the braced ones still land.
    >>  ............................................
    pt  Eu estava preparado. Sessenta anos e as preparadas ainda doem.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. You'd know that if you'd waited two more words.
    >>  ............................................
    pt  Isso não. Você saberia se tivesse esperado mais duas palavras.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody else.
    >>  ............................................
    pt  Então vá ser imparcial com outra pessoa.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted.dismissed/3
    en  That's the answer I was braced for. It still landed.
    >>  ............................................
    pt  É a resposta pra qual eu estava preparado. Ainda assim doeu.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. You'd know that if you'd waited two more words.
    >>  ............................................
    pt  Isso não. Você saberia se tivesse esperado mais duas palavras.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody else.
    >>  ............................................
    pt  Então vá ser imparcial com outra pessoa.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted.dismissed/3
    en  That's the answer I was braced for. It still landed.
    >>  ............................................
    pt  É a resposta pra qual eu estava preparado. Ainda assim doeu.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this, %1$s. Of all the people to say that, I'd not have guessed you.
    >>  ............................................
    pt  Isso não, %1$s. De todas as pessoas pra dizer isso, eu não teria adivinhado você.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody who hasn't just trusted you.
    >>  ............................................
    pt  Então vá ser imparcial com alguém que não acabou de confiar em você.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for that from strangers. Not from you.
    >>  ............................................
    pt  Eu estava preparado pra isso de estranhos. De você, não.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this, %1$s. Of all the people to say that, I'd not have guessed you.
    >>  ............................................
    pt  Isso não, %1$s. De todas as pessoas pra dizer isso, eu não teria adivinhado você.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody who hasn't just trusted you.
    >>  ............................................
    pt  Então vá ser imparcial com alguém que não acabou de confiar em você.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for that from strangers. Not from you.
    >>  ............................................
    pt  Eu estava preparado pra isso de estranhos. De você, não.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this, %1$s. Of all the people to say that, I'd not have guessed you.
    >>  ............................................
    pt  Isso não, %1$s. De todas as pessoas pra dizer isso, eu não teria adivinhado você.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody who hasn't just trusted you.
    >>  ............................................
    pt  Então vá ser imparcial com alguém que não acabou de confiar em você.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for that from strangers. Not from you.
    >>  ............................................
    pt  Eu estava preparado pra isso de estranhos. De você, não.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.dismissed/1
    en  ...Not this. Please. I've barely got the words out.
    >>  ............................................
    pt  ...Isso não. Por favor. Eu mal consegui tirar as palavras.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go, before I say something I'd have to apologise for later.
    >>  ............................................
    pt  Então vá, antes que eu diga algo pelo qual teria que pedir desculpa depois.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that all week and I still wasn't ready when it came.
    >>  ............................................
    pt  Eu me preparei pra isso a semana toda e ainda não estava pronto quando veio.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. You'd know that if you'd waited two more words.
    >>  ............................................
    pt  Isso não. Você saberia se tivesse esperado mais duas palavras.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody else.
    >>  ............................................
    pt  Então vá ser imparcial com outra pessoa.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted.dismissed/3
    en  That's the answer I was braced for. It still landed.
    >>  ............................................
    pt  É a resposta pra qual eu estava preparado. Ainda assim doeu.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. You'd know that if you'd waited two more words.
    >>  ............................................
    pt  Isso não. Você saberia se tivesse esperado mais duas palavras.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed at somebody else.
    >>  ............................................
    pt  Então vá ser imparcial com outra pessoa.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted.dismissed/3
    en  That's the answer I was braced for. It still landed.
    >>  ............................................
    pt  É a resposta pra qual eu estava preparado. Ainda assim doeu.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this.
    >>  ............................................
    pt  Isso não.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go.
    >>  ............................................
    pt  Então vá.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for that. It landed anyway.
    >>  ............................................
    pt  Eu estava preparado. Doeu mesmo assim.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. I've heard every version of that sentence and none of them fit this.
    >>  ............................................
    pt  Isso não. Já ouvi todas as versões dessa frase e nenhuma serve pra isto.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed elsewhere. I've no patience left to spend on it.
    >>  ............................................
    pt  Então vá ser imparcial em outro lugar. Não me sobrou paciência pra gastar.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for it. Sixty years and the braced ones still land.
    >>  ............................................
    pt  Eu estava preparado. Sessenta anos e as preparadas ainda doem.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this.
    >>  ............................................
    pt  Isso não.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go.
    >>  ............................................
    pt  Então vá.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for that. It landed anyway.
    >>  ............................................
    pt  Eu estava preparado. Doeu mesmo assim.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. I've heard every version of that sentence and none of them fit this.
    >>  ............................................
    pt  Isso não. Já ouvi todas as versões dessa frase e nenhuma serve pra isto.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed elsewhere. I've no patience left to spend on it.
    >>  ............................................
    pt  Então vá ser imparcial em outro lugar. Não me sobrou paciência pra gastar.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for it. Sixty years and the braced ones still land.
    >>  ............................................
    pt  Eu estava preparado. Sessenta anos e as preparadas ainda doem.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this one. Two more words and you'd have had the whole picture.
    >>  ............................................
    pt  Esta não. Mais duas palavras e você teria o quadro inteiro.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and spread that fine even-handedness somewhere it can do less damage.
    >>  ............................................
    pt  Então leve essa bela imparcialidade pra onde ela cause menos estrago.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that and it still got through. Impressive, in its way.
    >>  ............................................
    pt  Eu me preparei pra isso e ainda passou. Impressionante, à sua maneira.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this one. Two more words and you'd have had the whole picture.
    >>  ............................................
    pt  Esta não. Mais duas palavras e você teria o quadro inteiro.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and spread that fine even-handedness somewhere it can do less damage.
    >>  ............................................
    pt  Então leve essa bela imparcialidade pra onde ela cause menos estrago.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that and it still got through. Impressive, in its way.
    >>  ............................................
    pt  Eu me preparei pra isso e ainda passou. Impressionante, à sua maneira.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this. I've heard every version of that sentence and none of them fit this.
    >>  ............................................
    pt  Isso não. Já ouvi todas as versões dessa frase e nenhuma serve pra isto.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and be even-handed elsewhere. I've no patience left to spend on it.
    >>  ............................................
    pt  Então vá ser imparcial em outro lugar. Não me sobrou paciência pra gastar.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for it. Sixty years and the braced ones still land.
    >>  ............................................
    pt  Eu estava preparado. Sessenta anos e as preparadas ainda doem.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.dismissed/1
    en  ...Not this. Please. I've barely got the words out.
    >>  ............................................
    pt  ...Isso não. Por favor. Eu mal consegui tirar as palavras.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go, before I say something I'd have to apologise for later.
    >>  ............................................
    pt  Então vá, antes que eu diga algo pelo qual teria que pedir desculpa depois.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that all week and I still wasn't ready when it came.
    >>  ............................................
    pt  Eu me preparei pra isso a semana toda e ainda não estava pronto quando veio.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this.
    >>  ............................................
    pt  Isso não.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go.
    >>  ............................................
    pt  Então vá.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I was braced for that. It landed anyway.
    >>  ............................................
    pt  Eu estava preparado. Doeu mesmo assim.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this one. Two more words and you'd have had the whole picture.
    >>  ............................................
    pt  Esta não. Mais duas palavras e você teria o quadro inteiro.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and spread that fine even-handedness somewhere it can do less damage.
    >>  ............................................
    pt  Então leve essa bela imparcialidade pra onde ela cause menos estrago.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that and it still got through. Impressive, in its way.
    >>  ............................................
    pt  Eu me preparei pra isso e ainda passou. Impressionante, à sua maneira.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.dismissed/1
    en  Not this one. Two more words and you'd have had the whole picture.
    >>  ............................................
    pt  Esta não. Mais duas palavras e você teria o quadro inteiro.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.dismissed/2
    en  Then go and spread that fine even-handedness somewhere it can do less damage.
    >>  ............................................
    pt  Então leve essa bela imparcialidade pra onde ela cause menos estrago.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted.dismissed/3
    en  I braced for that and it still got through. Impressive, in its way.
    >>  ............................................
    pt  Eu me preparei pra isso e ainda passou. Impressionante, à sua maneira.
    >>  ............................................
```

</details>


### Button `back` — "I'll go."

*stance family `exit` · tone `gentle` · outcome `conversation_ended` · answers the beat(s) `noticed.afflicted.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.afflicted.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.afflicted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.afflicted.respond.back   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou embora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted.back
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.noticed.afflicted.respond, button `back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.back`: the villager accepts. Subject `noticed.affliction`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted.back/1   [8 chars]
    en  Just so.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.back/2   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  dialogue.conversations.noticed.afflicted.back/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuide, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.annoyed.boundary.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.annoyed.respond` / `brush_off`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.annoyed.brush_off` — e.g. "Still on about it, yes. And I'll stay on about it a while yet."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.boundary.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.annoyed.boundary.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.annoyed.boundary.followup   [23 chars]
    en  That's where it stands.
    >>  ............................................
    pt  É assim que fica.
    >>  ............................................
```


### Button `apologize_properly` — "...Then I'm sorry. Properly, this time."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.annoyed.rebuffed` · offered only once the villager has actually said `cause:player`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.boundary.apologize_properly` — accepted phrasings: "i am sorry, properly this time"; "i truly am sorry"; "i mean it, i am sorry"
  - the message must contain one of: `properly`, `truly`, `mean`
  - scored words: `properly`(1.5), `truly`(1.2), `mean`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.boundary.followup.apologize_properly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.boundary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.boundary.followup.apologize_properly   [39 chars]
    en  ...Then I'm sorry. Properly, this time.
    >>  ............................................
    pt  ...Então me desculpe. De verdade, dessa vez.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.annoyed.boundary.apologize_properly`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -4  _(recorded under topic `noticed.annoyed.boundary.apologize_properly`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.boundary.apologize_properly
WHO    VILLAGER — what the player reads after pressing "...Then I'm sorry. Properly, this time."
       spoken on: conversations.topic.noticed.annoyed.boundary.followup, button `apologize_properly`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.boundary.apologize_properly`: the villager qualifys. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.boundary.apologize_properly/1   [42 chars]
    en  ...Hm. That one sounded like you meant it.
    >>  ............................................
    pt  ...Hm. Esse aí soou de verdade.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.apologize_properly/2   [59 chars]
    en  Better. I'll not pretend it's fixed, but it's better, %1$s.
    >>  ............................................
    pt  Melhor. Não vou fingir que está resolvido, mas está melhor, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.apologize_properly/3   [38 chars]
    en  Twice asked, once meant. Aye. Alright.
    >>  ............................................
    pt  Duas vezes pedido, uma vez sentido. É. Está bem.
    >>  ............................................
```


### Button `ask_what` — "Tell me what I actually did."

*stance family `curiosity` · tone `plain` · outcome `resisted` · answers the beat(s) `noticed.annoyed.rebuffed` · offered only once the villager has actually said `cause:player`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.boundary.ask_what` — accepted phrasings: "tell me what i actually did"; "what exactly did i do"; "what did i do"
  - the message must contain one of: `exactly`, `actually`, `did`
  - scored words: `exactly`(1.5), `actually`(1.2), `did`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.boundary.followup.ask_what
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.boundary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.boundary.followup.ask_what   [28 chars]
    en  Tell me what I actually did.
    >>  ............................................
    pt  Me diga o que eu fiz, exatamente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension +1  _(recorded under topic `noticed.annoyed.boundary.ask_what`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.boundary.ask_what
WHO    VILLAGER — what the player reads after pressing "Tell me what I actually did."
       spoken on: conversations.topic.noticed.annoyed.boundary.followup, button `ask_what`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.boundary.ask_what`: the villager deflects. Subject `noticed.player_conflict`, polarity `negative`, guarded, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.boundary.ask_what/1   [78 chars]
    en  You don't know. That's somehow worse. ...Ask me tomorrow and I might tell you.
    >>  ............................................
    pt  Você não sabe. De algum jeito isso é pior. ...Me pergunte amanhã e talvez eu conte.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.ask_what/2   [55 chars]
    en  If you have to be told, %1$s, telling you won't fix it.
    >>  ............................................
    pt  Se você precisa que eu diga, %1$s, dizer não vai resolver.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.ask_what/3   [47 chars]
    en  ...Fine. But not stood in the street like this.
    >>  ............................................
    pt  ...Tudo bem. Mas não em pé no meio da rua assim.
    >>  ............................................
```


### Button `respect_it` — "Understood. I'll not press it."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.annoyed.rebuffed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.boundary.respect_it` — accepted phrasings: "understood, i will not press it"; "i will not insist"; "i will not press"
  - the message must contain one of: `press`, `understood`, `insist`
  - scored words: `press`(1.5), `understood`(1.5), `insist`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.boundary.followup.respect_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.boundary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.boundary.followup.respect_it   [30 chars]
    en  Understood. I'll not press it.
    >>  ............................................
    pt  Entendido. Não vou insistir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, tension -2  _(recorded under topic `noticed.annoyed.boundary.respect_it`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.boundary.respect_it
WHO    VILLAGER — what the player reads after pressing "Understood. I'll not press it."
       spoken on: conversations.topic.noticed.annoyed.boundary.followup, button `respect_it`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.boundary.respect_it`: the villager accepts. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.boundary.respect_it/1   [48 chars]
    en  Good. That's the first useful thing you've said.
    >>  ............................................
    pt  Bom. Foi a primeira coisa útil que você disse.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.respect_it/2   [41 chars]
    en  Quite. Leave it a few days and we'll see.
    >>  ............................................
    pt  Exato. Deixa uns dias e a gente vê.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.respect_it/3   [38 chars]
    en  Right. Thank you for hearing it, %1$s.
    >>  ............................................
    pt  Certo. Obrigado por ouvir, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.annoyed.rebuffed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.boundary.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.boundary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.boundary.followup.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.boundary.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.noticed.annoyed.boundary.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.boundary.leave`: the villager accepts. Subject `noticed.player_conflict`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.boundary.leave/1   [3 chars]
    en  Do.
    >>  ............................................
    pt  Vá.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.leave/2   [24 chars]
    en  True enough. Off you go.
    >>  ............................................
    pt  Bem verdade. Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.boundary.leave/3   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
```

---


## `conversations.topic.noticed.annoyed.explain.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.annoyed.respond` / `explain`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.annoyed.explain` — e.g. "Go on then. I'll hear it, which is more than I meant to offer."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.explain.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.annoyed.explain.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.annoyed.explain.followup   [27 chars]
    en  Go on, then. I'm listening.
    >>  ............................................
    pt  Pode falar, então. Estou ouvindo.
    >>  ............................................
```


### Button `didnt_realise` — "I didn't realise it would land like that."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.annoyed.explain_invited` · offered only once the villager has actually said `villager:listening`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.explain.didnt_realise` — accepted phrasings: "i did not realise it would land like that"; "i did not realize"; "i had no idea it would land like that"
  - the message must contain one of: `realise`, `realize`, `land`
  - scored words: `realise`(1.5), `realize`(1.5), `land`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.explain.followup.didnt_realise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.explain.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.explain.followup.didnt_realise   [41 chars]
    en  I didn't realise it would land like that.
    >>  ............................................
    pt  Eu não imaginei que ia soar assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.annoyed.explain.didnt_realise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -4, trust +1  _(recorded under topic `noticed.annoyed.explain.didnt_realise`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.explain.didnt_realise
WHO    VILLAGER — what the player reads after pressing "I didn't realise it would land like that."
       spoken on: conversations.topic.noticed.annoyed.explain.followup, button `didnt_realise`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.explain.didnt_realise`: the villager qualifys. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.explain.didnt_realise/1   [51 chars]
    en  No. You wouldn't have. That's half of why it stung.
    >>  ............................................
    pt  Não. Você não imaginaria mesmo. É metade do motivo de ter doído.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.didnt_realise/2   [83 chars]
    en  That's the trouble with it, %1$s. Cost you nothing to say and me a whole afternoon.
    >>  ............................................
    pt  É esse o problema, %1$s. Não te custou nada dizer e me custou uma tarde inteira.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.didnt_realise/3   [62 chars]
    en  ...Alright. I'll believe that. I'd rather careless than cruel.
    >>  ............................................
    pt  ...Certo. Vou acreditar. Prefiro descuido a crueldade.
    >>  ............................................
```


### Button `own_it` — "There's no excuse. I got it wrong."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.annoyed.explain_invited`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.explain.own_it` — accepted phrasings: "there is no excuse"; "it was my fault"; "i have no defence, i got it wrong"
  - the message must contain one of: `excuse`, `fault`, `defence`
  - scored words: `excuse`(1.5), `fault`(1.5), `defence`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.explain.followup.own_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.explain.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.explain.followup.own_it   [34 chars]
    en  There's no excuse. I got it wrong.
    >>  ............................................
    pt  Não tem desculpa. Eu errei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.annoyed.explain.own_it`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, tension -5  _(recorded under topic `noticed.annoyed.explain.own_it`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.explain.own_it
WHO    VILLAGER — what the player reads after pressing "There's no excuse. I got it wrong."
       spoken on: conversations.topic.noticed.annoyed.explain.followup, button `own_it`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.explain.own_it`: the villager accepts. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.explain.own_it/1   [49 chars]
    en  ...Well. That's not what I braced for. Thank you.
    >>  ............................................
    pt  ...Bom. Não era pra isso que eu estava me preparando. Obrigado.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.own_it/2   [59 chars]
    en  No excuse offered. That's rarer than the apology, honestly.
    >>  ............................................
    pt  Sem desculpa nenhuma. Sinceramente, isso é mais raro que o pedido de desculpas.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.own_it/3   [53 chars]
    en  Right. Then it's done, %1$s. I'll not raise it again.
    >>  ............................................
    pt  Certo. Então está encerrado, %1$s. Não toco mais no assunto.
    >>  ............................................
```


### Button `push_back` — "You'd have done the same in my place."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `noticed.annoyed.explain_invited`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.explain.push_back` — accepted phrasings: "you would have done the same"; "in my place you would have done the same"; "anyone would have"
  - the message must contain one of: `same`, `place`, `anyone`
  - scored words: `same`(1.5), `place`(1.2), `anyone`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.explain.followup.push_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.explain.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.explain.followup.push_back   [37 chars]
    en  You'd have done the same in my place.
    >>  ............................................
    pt  No meu lugar você teria feito igual.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `noticed.annoyed.explain.push_back`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1, tension +4  _(recorded under topic `noticed.annoyed.explain.push_back`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.explain.push_back
WHO    VILLAGER — what the player reads after pressing "You'd have done the same in my place."
       spoken on: conversations.topic.noticed.annoyed.explain.followup, button `push_back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.explain.push_back`: the villager resists. Subject `noticed.player_conflict`, polarity `negative`, guarded, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.explain.push_back/1   [48 chars]
    en  Perhaps. That isn't the defence you think it is.
    >>  ............................................
    pt  Talvez. Só que isso não é a defesa que você acha que é.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.push_back/2   [46 chars]
    en  Maybe I would. I'd have owned it faster, mind.
    >>  ............................................
    pt  Talvez eu tivesse. Mas eu teria assumido mais rápido.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.push_back/3   [67 chars]
    en  So we're both the sort who'd do it. Wonderful. Anything else, %1$s?
    >>  ............................................
    pt  Então nós dois somos do tipo que faz isso. Maravilha. Mais alguma coisa, %1$s?
    >>  ............................................
```


### Button `leave` — "Nothing that'd sound better out loud."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.annoyed.explain_invited` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.explain.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.explain.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.explain.followup.leave   [37 chars]
    en  Nothing that'd sound better out loud.
    >>  ............................................
    pt  Nada que soe melhor dito em voz alta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.explain.leave
WHO    VILLAGER — what the player reads after pressing "Nothing that'd sound better out loud."
       spoken on: conversations.topic.noticed.annoyed.explain.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.explain.leave`: the villager accepts. Subject `noticed.player_conflict`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.explain.leave/1   [43 chars]
    en  Then don't say it. That's honest, at least.
    >>  ............................................
    pt  Então não diga. Pelo menos é honesto.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.leave/2   [16 chars]
    en  Hm. Fair enough.
    >>  ............................................
    pt  Hm. Tudo bem.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain.leave/3   [16 chars]
    en  Safe home, %1$s.
    >>  ............................................
    pt  Volte bem, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.annoyed.repair.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.annoyed.respond` / `apologize`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.annoyed.apologize` — e.g. "...Right. That's more than I expected. It helps."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.repair.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.annoyed.repair.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.annoyed.repair.followup   [35 chars]
    en  We're not square. But we're closer.
    >>  ............................................
    pt  A gente não está quite. Mas está mais perto.
    >>  ............................................
```


### Button `ask_repair` — "Is there anything that'd put it right?"

*stance family `curiosity` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.annoyed.apology_accepted` · offered only once the villager has actually said `apology:accepted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.repair.ask_repair` — accepted phrasings: "what would put it right"; "how can i make amends"; "anything that would mend it"
  - the message must contain one of: `right`, `mend`, `amends`
  - scored words: `right`(1.5), `mend`(1.5), `amends`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.repair.followup.ask_repair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.repair.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.repair.followup.ask_repair   [38 chars]
    en  Is there anything that'd put it right?
    >>  ............................................
    pt  Tem alguma coisa que resolveria isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.annoyed.repair.ask_repair`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, trust +2  _(recorded under topic `noticed.annoyed.repair.ask_repair`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.repair.ask_repair
WHO    VILLAGER — what the player reads after pressing "Is there anything that'd put it right?"
       spoken on: conversations.topic.noticed.annoyed.repair.followup, button `ask_repair`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.repair.ask_repair`: the villager explains. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.repair.ask_repair/1   [41 chars]
    en  Time, mostly. And you not doing it again.
    >>  ............................................
    pt  Tempo, principalmente. E você não repetir.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.ask_repair/2   [59 chars]
    en  Nothing you can carry, %1$s. Just don't make a habit of it.
    >>  ............................................
    pt  Nada que você possa carregar, %1$s. Só não vire hábito.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.ask_repair/3   [57 chars]
    en  You've done the hard part. The rest is mine to let go of.
    >>  ............................................
    pt  Você já fez a parte difícil. O resto é comigo, é eu soltar.
    >>  ............................................
```


### Button `promise` — "It won't happen again."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.annoyed.apology_accepted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.repair.promise` — accepted phrasings: "it will not happen again"; "i promise"; "it will not repeat"
  - the message must contain one of: `again`, `promise`, `repeat`
  - scored words: `again`(1.5), `promise`(1.5), `repeat`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.repair.followup.promise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.repair.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.repair.followup.promise   [22 chars]
    en  It won't happen again.
    >>  ............................................
    pt  Não vai acontecer de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.annoyed.repair.promise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, tension -2  _(recorded under topic `noticed.annoyed.repair.promise`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.repair.promise
WHO    VILLAGER — what the player reads after pressing "It won't happen again."
       spoken on: conversations.topic.noticed.annoyed.repair.followup, button `promise`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.repair.promise`: the villager accepts. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.repair.promise/1   [50 chars]
    en  Good. I'd rather have your word than your apology.
    >>  ............................................
    pt  Bom. Prefiro sua palavra ao seu pedido de desculpas.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.promise/2   [65 chars]
    en  See that it doesn't. I've a long memory and a short temper, %1$s.
    >>  ............................................
    pt  Trate de não acontecer. Tenho memória longa e pavio curto, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.promise/3   [51 chars]
    en  Said plainly. That's worth more than the sorry was.
    >>  ............................................
    pt  Dito assim, direto. Vale mais que o desculpa valeu.
    >>  ............................................
```


### Button `let_rest` — "I'll let it rest there."

*stance family `restraint` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.annoyed.apology_accepted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.repair.let_rest` — accepted phrasings: "i will let it rest"; "let us drop it"; "let it settle"
  - the message must contain one of: `rest`, `drop`, `settle`
  - scored words: `rest`(1.5), `drop`(1.2), `settle`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.repair.followup.let_rest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.repair.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.repair.followup.let_rest   [23 chars]
    en  I'll let it rest there.
    >>  ............................................
    pt  Vou deixar quieto por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension -1  _(recorded under topic `noticed.annoyed.repair.let_rest`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.repair.let_rest
WHO    VILLAGER — what the player reads after pressing "I'll let it rest there."
       spoken on: conversations.topic.noticed.annoyed.repair.followup, button `let_rest`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.repair.let_rest`: the villager accepts. Subject `noticed.player_conflict`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.repair.let_rest/1   [58 chars]
    en  Just so. Let it. It'll have gone off the boil by tomorrow.
    >>  ............................................
    pt  Pois é. Deixa. Amanhã já vai ter esfriado.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.let_rest/2   [61 chars]
    en  That's the sensible thing. Everyone else keeps picking at it.
    >>  ............................................
    pt  É o mais sensato. Todo mundo fica cutucando.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.let_rest/3   [52 chars]
    en  Good. I'd rather not turn it over again today, %1$s.
    >>  ............................................
    pt  Bom. Prefiro não remoer isso de novo hoje, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll get out of your way."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.annoyed.apology_accepted` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.repair.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.repair.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.repair.followup.leave   [25 chars]
    en  I'll get out of your way.
    >>  ............................................
    pt  Vou sair do seu caminho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.repair.leave
WHO    VILLAGER — what the player reads after pressing "I'll get out of your way."
       spoken on: conversations.topic.noticed.annoyed.repair.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.repair.leave`: the villager accepts. Subject `noticed.player_conflict`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.repair.leave/1   [21 chars]
    en  So I've found. Go on.
    >>  ............................................
    pt  Foi o que eu vi. Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.leave/2   [12 chars]
    en  Enough said.
    >>  ............................................
    pt  Já foi dito.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.repair.leave/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuida, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.annoyed.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.annoyed` — e.g. "You. Right. What is it you want, %1$s?"


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.annoyed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.annoyed.respond   [13 chars]
    en  You know why.
    >>  ............................................
    pt  Você sabe por quê.
    >>  ............................................
```


### Button `apologize` — "I'm sorry for what I did."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.annoyed.open` · offered only once the villager has actually said `cause:player`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.apologize` — accepted phrasings: "i am sorry for what i did"; "sorry, that was wrong of me"; "i am sorry"
  - the message must contain one of: `sorry`, `wrong`
  - scored words: `sorry`(1.5), `did`(0.5), `wrong`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.respond.apologize   [25 chars]
    en  I'm sorry for what I did.
    >>  ............................................
    pt  Desculpa pelo que eu fiz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.annoyed.apologize`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -6, respect +2  _(recorded under topic `noticed.annoyed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.annoyed.repair.followup`
- …where the player's next choices will be: "Is there anything that'd put it right?" | "It won't happen again." | "I'll let it rest there." | "I'll get out of your way."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.apologize
WHO    VILLAGER — what the player reads after pressing "I'm sorry for what I did."
       spoken on: conversations.topic.noticed.annoyed.respond, button `apologize`
       leaves the player on: conversations.topic.noticed.annoyed.repair.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.apology_accepted`: the villager accepts. Subject `noticed.player_conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `state:annoyed`, `cause:player`, `apology:accepted` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, restraint, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.apologize/1   [48 chars]
    en  ...Right. That's more than I expected. It helps.
    >>  ............................................
    pt  ...Certo. É mais do que eu esperava. Ajuda.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.apologize/2   [69 chars]
    en  An actual apology. Huh. Alright — we're not square, but we're closer.
    >>  ............................................
    pt  Um pedido de desculpa de verdade. Hm. Certo — não estamos quites, mas estamos mais perto.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.apologize/3   [47 chars]
    en  I'll take it, %1$s. Don't make me need another.
    >>  ............................................
    pt  Aceito, %1$s. Não me faça precisar de outro.
    >>  ............................................
```


### Button `explain` — "Let me explain myself."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `noticed.annoyed.open` · offered only once the villager has actually said `cause:player`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.explain` — accepted phrasings: "let me explain myself"; "let me explain"; "there was a reason"
  - the message must contain one of: `explain`, `myself`, `reason`
  - scored words: `explain`(1.5), `myself`(1.2), `reason`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.respond.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.respond.explain   [22 chars]
    en  Let me explain myself.
    >>  ............................................
    pt  Deixa eu me explicar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `noticed.annoyed.explain`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.annoyed.explain.followup`
- …where the player's next choices will be: "I didn't realise it would land like that." | "There's no excuse. I got it wrong." | "You'd have done the same in my place." | "Nothing that'd sound better out loud."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.explain
WHO    VILLAGER — what the player reads after pressing "Let me explain myself."
       spoken on: conversations.topic.noticed.annoyed.respond, button `explain`
       leaves the player on: conversations.topic.noticed.annoyed.explain.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.explain_invited`: the villager asks. Subject `noticed.player_conflict`, polarity `negative`, invites followup, outcome `qualified`.
NOTE   this is the line that establishes `state:annoyed`, `cause:player`, `villager:listening` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, challenge, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.explain/1   [62 chars]
    en  Go on then. I'll hear it, which is more than I meant to offer.
    >>  ............................................
    pt  Fala então. Vou ouvir, o que é mais do que eu pretendia oferecer.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain/2   [51 chars]
    en  An explanation isn't an apology. But I'm listening.
    >>  ............................................
    pt  Explicação não é desculpa. Mas estou ouvindo.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.explain/3   [24 chars]
    en  ...Fine. Say your piece.
    >>  ............................................
    pt  ...Tá. Diga o que tem a dizer.
    >>  ............................................
```


### Button `brush_off` — "You're still on about that?"

*stance family `dismissal` · tone `blunt` · outcome `rebuffed` · answers the beat(s) `noticed.annoyed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.annoyed.brush_off` — accepted phrasings: "you are still on about that"; "get over it"; "still holding a grudge"
  - the message must contain one of: `still`, `over`, `grudge`
  - scored words: `still`(1.5), `about`(0.4), `over`(1.2), `grudge`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.respond.brush_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.respond.brush_off   [27 chars]
    en  You're still on about that?
    >>  ............................................
    pt  Você ainda está com isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `noticed.annoyed.brush_off`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +6, warmth -3  _(recorded under topic `noticed.annoyed.brush_off`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.annoyed.boundary.followup`
- …where the player's next choices will be: "...Then I'm sorry. Properly, this time." | "Tell me what I actually did." | "Understood. I'll not press it." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.brush_off
WHO    VILLAGER — what the player reads after pressing "You're still on about that?"
       spoken on: conversations.topic.noticed.annoyed.respond, button `brush_off`
       leaves the player on: conversations.topic.noticed.annoyed.boundary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.rebuffed`: the villager refuses. Subject `noticed.player_conflict`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   this is the line that establishes `state:annoyed`, `cause:player`, `subject:closed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, restraint, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.brush_off/1   [62 chars]
    en  Still on about it, yes. And I'll stay on about it a while yet.
    >>  ............................................
    pt  Ainda nisso, sim. E vou continuar nisso mais um tempo.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.brush_off/2   [54 chars]
    en  You've a short memory for your own behaviour. I don't.
    >>  ............................................
    pt  Você tem memória curta para o próprio comportamento. Eu não.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.brush_off/3   [30 chars]
    en  Then we've nothing to discuss.
    >>  ............................................
    pt  Então não temos nada a discutir.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.annoyed.brush_off/1
    en  ...Still. I know it's tiresome, %1$s. It's tiresome for me too.
    >>  ............................................
    pt  ...Ainda. Eu sei que é cansativo, %1$s. Pra mim também é.
    >>  ............................................
  anxious.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right. I'd hoped saying it out loud would make it smaller. It hasn't.
    >>  ............................................
    pt  Certo. Eu esperava que dizer em voz alta deixasse menor. Não deixou.
    >>  ............................................
  anxious.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...I'll keep it in. That's what I usually do anyway.
    >>  ............................................
    pt  ...Vou guardar. É o que eu costumo fazer mesmo.
    >>  ............................................
  athletic.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, aye. It'll wear off in its own time.
    >>  ............................................
    pt  Ainda nisso, é. Vai passar no tempo dele.
    >>  ............................................
  athletic.dialogue.conversations.noticed.annoyed.brush_off/2
    en  ...It's not gone yet. These things don't go on a schedule.
    >>  ............................................
    pt  ...Ainda não passou. Essas coisas não seguem horário.
    >>  ............................................
  athletic.dialogue.conversations.noticed.annoyed.brush_off/3
    en  Right. I'll let it alone and it'll let me alone eventually.
    >>  ............................................
    pt  Certo. Vou deixar em paz e ele vai me deixar em paz uma hora.
    >>  ............................................
  confident.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, yes. And I'll stay on about it a while yet.
    >>  ............................................
    pt  Ainda nisso, sim. E vou ficar nisso mais um tempo.
    >>  ............................................
  confident.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't stopped being annoying since you last asked.
    >>  ............................................
    pt  Não parou de ser irritante desde a última vez que você perguntou.
    >>  ............................................
  confident.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll be annoyed to myself, then.
    >>  ............................................
    pt  ...Certo. Vou ficar irritado sozinho, então.
    >>  ............................................
  crabby.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, yes. And I'll stay on about it a while yet.
    >>  ............................................
    pt  Ainda nisso, sim. E vou ficar nisso mais um tempo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't stopped being annoying since you last asked.
    >>  ............................................
    pt  Não parou de ser irritante desde a última vez que você perguntou.
    >>  ............................................
  crabby.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll be annoyed to myself, then.
    >>  ............................................
    pt  ...Certo. Vou ficar irritado sozinho, então.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still, yes. I'd thought you might ask what actually happened, %1$s.
    >>  ............................................
    pt  Ainda, sim. Achei que você fosse perguntar o que aconteceu de verdade, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right. I'll not bore you with it again.
    >>  ............................................
    pt  Certo. Não te encho de novo com isso.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...It's not nothing, mind. But I'll drop it.
    >>  ............................................
    pt  ...Mas não é nada, veja bem. Mas eu largo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still, yes. I'd thought you might ask what actually happened, %1$s.
    >>  ............................................
    pt  Ainda, sim. Achei que você fosse perguntar o que aconteceu de verdade, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right. I'll not bore you with it again.
    >>  ............................................
    pt  Certo. Não te encho de novo com isso.
    >>  ............................................
  flirty.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...It's not nothing, mind. But I'll drop it.
    >>  ............................................
    pt  ...Mas não é nada, veja bem. Mas eu largo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still, yes. I'd thought you might ask what actually happened, %1$s.
    >>  ............................................
    pt  Ainda, sim. Achei que você fosse perguntar o que aconteceu de verdade, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right. I'll not bore you with it again.
    >>  ............................................
    pt  Certo. Não te encho de novo com isso.
    >>  ............................................
  friendly.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...It's not nothing, mind. But I'll drop it.
    >>  ............................................
    pt  ...Mas não é nada, veja bem. Mas eu largo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.annoyed.brush_off/1
    en  ...Still. I know it's tiresome, %1$s. It's tiresome for me too.
    >>  ............................................
    pt  ...Ainda. Eu sei que é cansativo, %1$s. Pra mim também é.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right. I'd hoped saying it out loud would make it smaller. It hasn't.
    >>  ............................................
    pt  Certo. Eu esperava que dizer em voz alta deixasse menor. Não deixou.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...I'll keep it in. That's what I usually do anyway.
    >>  ............................................
    pt  ...Vou guardar. É o que eu costumo fazer mesmo.
    >>  ............................................
  greedy.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, yes. And I'll stay on about it a while yet.
    >>  ............................................
    pt  Ainda nisso, sim. E vou ficar nisso mais um tempo.
    >>  ............................................
  greedy.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't stopped being annoying since you last asked.
    >>  ............................................
    pt  Não parou de ser irritante desde a última vez que você perguntou.
    >>  ............................................
  greedy.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll be annoyed to myself, then.
    >>  ............................................
    pt  ...Certo. Vou ficar irritado sozinho, então.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, yes. And I'll stay on about it a while yet.
    >>  ............................................
    pt  Ainda nisso, sim. E vou ficar nisso mais um tempo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't stopped being annoying since you last asked.
    >>  ............................................
    pt  Não parou de ser irritante desde a última vez que você perguntou.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll be annoyed to myself, then.
    >>  ............................................
    pt  ...Certo. Vou ficar irritado sozinho, então.
    >>  ............................................
  introverted.dialogue.conversations.noticed.annoyed.brush_off/1
    en  ...Still, yes.
    >>  ............................................
    pt  ...Ainda, sim.
    >>  ............................................
  introverted.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't changed.
    >>  ............................................
    pt  Não mudou.
    >>  ............................................
  introverted.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll say no more.
    >>  ............................................
    pt  ...Certo. Não digo mais nada.
    >>  ............................................
  lazy.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, aye. It'll wear off in its own time.
    >>  ............................................
    pt  Ainda nisso, é. Vai passar no tempo dele.
    >>  ............................................
  lazy.dialogue.conversations.noticed.annoyed.brush_off/2
    en  ...It's not gone yet. These things don't go on a schedule.
    >>  ............................................
    pt  ...Ainda não passou. Essas coisas não seguem horário.
    >>  ............................................
  lazy.dialogue.conversations.noticed.annoyed.brush_off/3
    en  Right. I'll let it alone and it'll let me alone eventually.
    >>  ............................................
    pt  Certo. Vou deixar em paz e ele vai me deixar em paz uma hora.
    >>  ............................................
  odd.dialogue.conversations.noticed.annoyed.brush_off/1
    en  ...Still, yes.
    >>  ............................................
    pt  ...Ainda, sim.
    >>  ............................................
  odd.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't changed.
    >>  ............................................
    pt  Não mudou.
    >>  ............................................
  odd.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll say no more.
    >>  ............................................
    pt  ...Certo. Não digo mais nada.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, aye. It'll wear off in its own time.
    >>  ............................................
    pt  Ainda nisso, é. Vai passar no tempo dele.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.annoyed.brush_off/2
    en  ...It's not gone yet. These things don't go on a schedule.
    >>  ............................................
    pt  ...Ainda não passou. Essas coisas não seguem horário.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.annoyed.brush_off/3
    en  Right. I'll let it alone and it'll let me alone eventually.
    >>  ............................................
    pt  Certo. Vou deixar em paz e ele vai me deixar em paz uma hora.
    >>  ............................................
  peppy.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it! Yes. It's the best material I've had all week.
    >>  ............................................
    pt  Ainda nisso! Sim. É o melhor material que eu tive a semana toda.
    >>  ............................................
  peppy.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right, well. I'll take my grievance somewhere it's appreciated.
    >>  ............................................
    pt  Certo, bom. Vou levar minha queixa pra onde seja apreciada.
    >>  ............................................
  peppy.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Ha. Give me another day and I'll be over it. Probably.
    >>  ............................................
    pt  ...Ha. Me dê mais um dia e eu supero. Provavelmente.
    >>  ............................................
  playful.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it! Yes. It's the best material I've had all week.
    >>  ............................................
    pt  Ainda nisso! Sim. É o melhor material que eu tive a semana toda.
    >>  ............................................
  playful.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right, well. I'll take my grievance somewhere it's appreciated.
    >>  ............................................
    pt  Certo, bom. Vou levar minha queixa pra onde seja apreciada.
    >>  ............................................
  playful.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Ha. Give me another day and I'll be over it. Probably.
    >>  ............................................
    pt  ...Ha. Me dê mais um dia e eu supero. Provavelmente.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it, aye. It'll wear off in its own time.
    >>  ............................................
    pt  Ainda nisso, é. Vai passar no tempo dele.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.annoyed.brush_off/2
    en  ...It's not gone yet. These things don't go on a schedule.
    >>  ............................................
    pt  ...Ainda não passou. Essas coisas não seguem horário.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.annoyed.brush_off/3
    en  Right. I'll let it alone and it'll let me alone eventually.
    >>  ............................................
    pt  Certo. Vou deixar em paz e ele vai me deixar em paz uma hora.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.annoyed.brush_off/1
    en  ...Still. I know it's tiresome, %1$s. It's tiresome for me too.
    >>  ............................................
    pt  ...Ainda. Eu sei que é cansativo, %1$s. Pra mim também é.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right. I'd hoped saying it out loud would make it smaller. It hasn't.
    >>  ............................................
    pt  Certo. Eu esperava que dizer em voz alta deixasse menor. Não deixou.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...I'll keep it in. That's what I usually do anyway.
    >>  ............................................
    pt  ...Vou guardar. É o que eu costumo fazer mesmo.
    >>  ............................................
  shy.dialogue.conversations.noticed.annoyed.brush_off/1
    en  ...Still, yes.
    >>  ............................................
    pt  ...Ainda, sim.
    >>  ............................................
  shy.dialogue.conversations.noticed.annoyed.brush_off/2
    en  It hasn't changed.
    >>  ............................................
    pt  Não mudou.
    >>  ............................................
  shy.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Right. I'll say no more.
    >>  ............................................
    pt  ...Certo. Não digo mais nada.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it! Yes. It's the best material I've had all week.
    >>  ............................................
    pt  Ainda nisso! Sim. É o melhor material que eu tive a semana toda.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right, well. I'll take my grievance somewhere it's appreciated.
    >>  ............................................
    pt  Certo, bom. Vou levar minha queixa pra onde seja apreciada.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Ha. Give me another day and I'll be over it. Probably.
    >>  ............................................
    pt  ...Ha. Me dê mais um dia e eu supero. Provavelmente.
    >>  ............................................
  witty.dialogue.conversations.noticed.annoyed.brush_off/1
    en  Still on about it! Yes. It's the best material I've had all week.
    >>  ............................................
    pt  Ainda nisso! Sim. É o melhor material que eu tive a semana toda.
    >>  ............................................
  witty.dialogue.conversations.noticed.annoyed.brush_off/2
    en  Right, well. I'll take my grievance somewhere it's appreciated.
    >>  ............................................
    pt  Certo, bom. Vou levar minha queixa pra onde seja apreciada.
    >>  ............................................
  witty.dialogue.conversations.noticed.annoyed.brush_off/3
    en  ...Ha. Give me another day and I'll be over it. Probably.
    >>  ............................................
    pt  ...Ha. Me dê mais um dia e eu supero. Provavelmente.
    >>  ............................................
```

</details>


### Button `leave` — "I'll give you room."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.annoyed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.annoyed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.annoyed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.annoyed.respond.leave   [19 chars]
    en  I'll give you room.
    >>  ............................................
    pt  Vou te dar espaço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed.leave
WHO    VILLAGER — what the player reads after pressing "I'll give you room."
       spoken on: conversations.topic.noticed.annoyed.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.left`: the villager accepts. Subject `noticed.player_conflict`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed.leave/1   [15 chars]
    en  It is. Do that.
    >>  ............................................
    pt  É sim. Faça isso.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.leave/2   [20 chars]
    en  Good. I'd like some.
    >>  ............................................
    pt  Bom. Eu queria um pouco.
    >>  ............................................
  dialogue.conversations.noticed.annoyed.leave/3   [18 chars]
    en  Right. Off you go.
    >>  ............................................
    pt  Certo. Pode ir.
    >>  ............................................
```

---


## `conversations.topic.noticed.elated.deflated.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.elated.respond` / `deflate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.elated.deflate` — e.g. "...I'll get carried away if I like, thank you."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.deflated.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.elated.deflated.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.elated.deflated.followup   [24 chars]
    en  ...Right. Where were we.
    >>  ............................................
    pt  ...Certo. Onde a gente estava.
    >>  ............................................
```


### Button `take_it_back` — "No — you've earned a good week."

*stance family `empathy` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.elated.deflated` · offered only once the villager has actually said `player:deflated_joy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.deflated.take_it_back` — accepted phrasings: "you have earned a good week"; "you deserve it"; "you are entitled to a good week"
  - the message must contain one of: `earned`, `deserve`, `entitled`
  - scored words: `earned`(1.5), `deserve`(1.5), `entitled`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.deflated.followup.take_it_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.deflated.followup.take_it_back   [31 chars]
    en  No — you've earned a good week.
    >>  ............................................
    pt  Não — você merece uma boa semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.elated.deflated.take_it_back`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -3, warmth +2  _(recorded under topic `noticed.elated.deflated.take_it_back`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.deflated.take_it_back
WHO    VILLAGER — what the player reads after pressing "No — you've earned a good week."
       spoken on: conversations.topic.noticed.elated.deflated.followup, button `take_it_back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.deflated.take_it_back`: the villager accepts. Subject `noticed.elation`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.deflated.take_it_back/1   [49 chars]
    en  ...Have I. Say that again and I might believe it.
    >>  ............................................
    pt  ...Será. Diz de novo e talvez eu acredite.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.take_it_back/2   [34 chars]
    en  Earned. Hm. I'll allow that, %1$s.
    >>  ............................................
    pt  Merecer. Hm. Isso eu aceito, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.take_it_back/3   [55 chars]
    en  That's kinder than the first thing you said. Thank you.
    >>  ............................................
    pt  Foi mais gentil que a primeira coisa que você disse. Obrigado.
    >>  ............................................
```


### Button `explain_meant` — "I meant it kindly. It came out wrong."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.elated.deflated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.deflated.explain_meant` — accepted phrasings: "i meant it kindly"; "it came out wrong"; "that came out wrong"
  - the message must contain one of: `kindly`, `meant`, `came`
  - scored words: `kindly`(1.5), `meant`(1.2), `came`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.deflated.followup.explain_meant
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.deflated.followup.explain_meant   [37 chars]
    en  I meant it kindly. It came out wrong.
    >>  ............................................
    pt  Falei com boa intenção. Saiu errado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.elated.deflated.explain_meant`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2  _(recorded under topic `noticed.elated.deflated.explain_meant`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.deflated.explain_meant
WHO    VILLAGER — what the player reads after pressing "I meant it kindly. It came out wrong."
       spoken on: conversations.topic.noticed.elated.deflated.followup, button `explain_meant`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.deflated.explain_meant`: the villager qualifys. Subject `noticed.elation`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.deflated.explain_meant/1   [58 chars]
    en  It did. But I'll take how you meant it over how it landed.
    >>  ............................................
    pt  Saiu mesmo. Mas fico com a intenção, não com o efeito.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.explain_meant/2   [42 chars]
    en  Kindly. Aye, alright. Consider it unheard.
    >>  ............................................
    pt  Boa intenção. É, tudo bem. Considere não dito.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.explain_meant/3   [43 chars]
    en  Next time say the kind version first, %1$s.
    >>  ............................................
    pt  Da próxima vez diga primeiro a versão gentil, %1$s.
    >>  ............................................
```


### Button `stand_by_it` — "Good weeks end. I'd rather you were ready."

*stance family `candor` · tone `blunt` · outcome `resisted` · answers the beat(s) `noticed.elated.deflated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.deflated.stand_by_it` — accepted phrasings: "good weeks end"; "i would rather you were ready"; "best to be prepared"
  - the message must contain one of: `end`, `ready`, `prepared`
  - scored words: `end`(1.5), `ready`(1.5), `prepared`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.deflated.followup.stand_by_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.deflated.followup.stand_by_it   [42 chars]
    en  Good weeks end. I'd rather you were ready.
    >>  ............................................
    pt  Boas semanas acabam. Prefiro você preparado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `noticed.elated.deflated.stand_by_it`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1, tension +3  _(recorded under topic `noticed.elated.deflated.stand_by_it`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.deflated.stand_by_it
WHO    VILLAGER — what the player reads after pressing "Good weeks end. I'd rather you were ready."
       spoken on: conversations.topic.noticed.elated.deflated.followup, button `stand_by_it`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.deflated.stand_by_it`: the villager resists. Subject `noticed.elation`, polarity `negative`, guarded, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.deflated.stand_by_it/1   [52 chars]
    en  I know they end. That's why I was enjoying this one.
    >>  ............................................
    pt  Eu sei que acabam. É por isso que eu estava aproveitando essa.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.stand_by_it/2   [69 chars]
    en  Ready. Right. And what does being ready get me that the ending won't?
    >>  ............................................
    pt  Preparado. Certo. E o que estar preparado me dá que o fim não dê?
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.stand_by_it/3   [60 chars]
    en  You may be right and still have been unkind, %1$s. Both fit.
    >>  ............................................
    pt  Você pode estar certo e ainda ter sido indelicado, %1$s. Cabem as duas.
    >>  ............................................
```


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.elated.deflated` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.deflated.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.deflated.followup.leave   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.deflated.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.noticed.elated.deflated.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.deflated.leave`: the villager accepts. Subject `noticed.elation`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.deflated.leave/1   [13 chars]
    en  So it is. Do.
    >>  ............................................
    pt  É assim mesmo. Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.leave/2   [15 chars]
    en  Leave it there.
    >>  ............................................
    pt  Deixe por aí.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflated.leave/3   [24 chars]
    en  We'll speak again, %1$s.
    >>  ............................................
    pt  A gente se fala, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.elated.followup`

**Reached from 2 route(s):** `conversations.topic.noticed.elated.respond` / `share_joy`; `conversations.topic.noticed.elated.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.elated.ask_more` — e.g. "The whole village, really. Good weeks are contagious here."
- `conversations.noticed.elated.share_joy` — e.g. "It's good to BE like this. It's been a while, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.elated.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.elated.followup   [40 chars]
    en  It's a good week. I'm letting it be one.
    >>  ............................................
    pt  É uma boa semana. Estou deixando ser.
    >>  ............................................
```


### Button `ask_best` — "What's the best of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.elated.joy_shared`, `noticed.elated.cause_told` · offered only once the villager has actually said `state:elated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.followup.ask_best` — accepted phrasings: "what is the best of it"; "what is the best part"; "what is your favourite part of it"
  - the message must contain one of: `best`, `part`, `favourite`
  - scored words: `best`(1.5), `part`(1.2), `favourite`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.followup.ask_best
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.followup.ask_best   [22 chars]
    en  What's the best of it?
    >>  ............................................
    pt  Qual é a melhor parte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `noticed.elated.followup.ask_best`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.followup.ask_best
WHO    VILLAGER — what the player reads after pressing "What's the best of it?"
       spoken on: conversations.topic.noticed.elated.followup, button `ask_best`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.followup.ask_best`: the villager discloses. Subject `noticed.elation`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.followup.ask_best/1   [69 chars]
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.ask_best/2   [56 chars]
    en  Everyone smiling at once. It doesn't happen often, %1$s.
    >>  ............................................
    pt  Todo mundo sorrindo ao mesmo tempo. Não acontece sempre, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.ask_best/3   [52 chars]
    en  Honestly? Nobody's needed anything from me all week.
    >>  ............................................
    pt  Sinceramente? Ninguém precisou de nada de mim a semana toda.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true and it hasn't been taken back.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade e não foi retirado.
    >>  ............................................
  anxious.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. I keep checking that it's still there, and it keeps being there.
    >>  ............................................
    pt  O anoitecer. Eu fico conferindo se ainda está lá, e continua estando.
    >>  ............................................
  anxious.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. I've not had many of those lately, %1$s, so it counts double.
    >>  ............................................
    pt  O fim do dia. Não tive muitos desses ultimamente, %1$s, então conta em dobro.
    >>  ............................................
  athletic.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  athletic.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. Good news settles at dusk, in my experience. It needs the day to be over first.
    >>  ............................................
    pt  O anoitecer. Boa notícia assenta ao anoitecer, na minha experiência. Precisa que o dia acabe.
    >>  ............................................
  athletic.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. It'll still be true tomorrow, and that's the best part of all.
    >>  ............................................
    pt  O fim do dia. Vai continuar verdade amanhã, e essa é a melhor parte.
    >>  ............................................
  confident.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  confident.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. Everything before that is noise.
    >>  ............................................
    pt  O fim do dia. Tudo antes disso é barulho.
    >>  ............................................
  confident.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The moment I remember it and it hasn't gone away. That one.
    >>  ............................................
    pt  O momento em que eu lembro e não foi embora. Esse.
    >>  ............................................
  crabby.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  crabby.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. Everything before that is noise.
    >>  ............................................
    pt  O fim do dia. Tudo antes disso é barulho.
    >>  ............................................
  crabby.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The moment I remember it and it hasn't gone away. That one.
    >>  ............................................
    pt  O momento em que eu lembro e não foi embora. Esse.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true. Telling you is close second.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade. Contar a você é quase igual.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day — and this, now, saying it to somebody who asked.
    >>  ............................................
    pt  O fim do dia — e isto, agora, dizer a alguém que perguntou.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering at dusk that it hasn't gone. And you asking about it, which nobody did.
    >>  ............................................
    pt  Lembrar ao anoitecer que não foi embora. E você perguntar, o que ninguém fez.
    >>  ............................................
  flirty.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true. Telling you is close second.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade. Contar a você é quase igual.
    >>  ............................................
  flirty.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day — and this, now, saying it to somebody who asked.
    >>  ............................................
    pt  O fim do dia — e isto, agora, dizer a alguém que perguntou.
    >>  ............................................
  flirty.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering at dusk that it hasn't gone. And you asking about it, which nobody did.
    >>  ............................................
    pt  Lembrar ao anoitecer que não foi embora. E você perguntar, o que ninguém fez.
    >>  ............................................
  friendly.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true. Telling you is close second.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade. Contar a você é quase igual.
    >>  ............................................
  friendly.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day — and this, now, saying it to somebody who asked.
    >>  ............................................
    pt  O fim do dia — e isto, agora, dizer a alguém que perguntou.
    >>  ............................................
  friendly.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering at dusk that it hasn't gone. And you asking about it, which nobody did.
    >>  ............................................
    pt  Lembrar ao anoitecer que não foi embora. E você perguntar, o que ninguém fez.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true and it hasn't been taken back.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade e não foi retirado.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. I keep checking that it's still there, and it keeps being there.
    >>  ............................................
    pt  O anoitecer. Eu fico conferindo se ainda está lá, e continua estando.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. I've not had many of those lately, %1$s, so it counts double.
    >>  ............................................
    pt  O fim do dia. Não tive muitos desses ultimamente, %1$s, então conta em dobro.
    >>  ............................................
  greedy.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  greedy.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. Everything before that is noise.
    >>  ............................................
    pt  O fim do dia. Tudo antes disso é barulho.
    >>  ............................................
  greedy.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The moment I remember it and it hasn't gone away. That one.
    >>  ............................................
    pt  O momento em que eu lembro e não foi embora. Esse.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. Everything before that is noise.
    >>  ............................................
    pt  O fim do dia. Tudo antes disso é barulho.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The moment I remember it and it hasn't gone away. That one.
    >>  ............................................
    pt  O momento em que eu lembro e não foi embora. Esse.
    >>  ............................................
  introverted.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  introverted.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. When I remember and it hasn't gone.
    >>  ............................................
    pt  O anoitecer. Quando eu lembro e não foi embora.
    >>  ............................................
  introverted.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. That's the part.
    >>  ............................................
    pt  O fim do dia. É essa a parte.
    >>  ............................................
  lazy.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  lazy.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. Good news settles at dusk, in my experience. It needs the day to be over first.
    >>  ............................................
    pt  O anoitecer. Boa notícia assenta ao anoitecer, na minha experiência. Precisa que o dia acabe.
    >>  ............................................
  lazy.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. It'll still be true tomorrow, and that's the best part of all.
    >>  ............................................
    pt  O fim do dia. Vai continuar verdade amanhã, e essa é a melhor parte.
    >>  ............................................
  odd.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  odd.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. When I remember and it hasn't gone.
    >>  ............................................
    pt  O anoitecer. Quando eu lembro e não foi embora.
    >>  ............................................
  odd.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. That's the part.
    >>  ............................................
    pt  O fim do dia. É essa a parte.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. Good news settles at dusk, in my experience. It needs the day to be over first.
    >>  ............................................
    pt  O anoitecer. Boa notícia assenta ao anoitecer, na minha experiência. Precisa que o dia acabe.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. It'll still be true tomorrow, and that's the best part of all.
    >>  ............................................
    pt  O fim do dia. Vai continuar verdade amanhã, e essa é a melhor parte.
    >>  ............................................
  peppy.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true! That's the best of it.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade! É o melhor.
    >>  ............................................
  peppy.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. All the shouting is fine, but the quiet bit is where it lands.
    >>  ............................................
    pt  O fim do dia. A gritaria é boa, mas é na parte quieta que assenta.
    >>  ............................................
  peppy.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering it at dusk and finding it hasn't gone. That's the part I'd keep.
    >>  ............................................
    pt  Lembrar disso ao anoitecer e ver que não foi embora. É a parte que eu guardaria.
    >>  ............................................
  playful.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true! That's the best of it.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade! É o melhor.
    >>  ............................................
  playful.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. All the shouting is fine, but the quiet bit is where it lands.
    >>  ............................................
    pt  O fim do dia. A gritaria é boa, mas é na parte quieta que assenta.
    >>  ............................................
  playful.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering it at dusk and finding it hasn't gone. That's the part I'd keep.
    >>  ............................................
    pt  Lembrar disso ao anoitecer e ver que não foi embora. É a parte que eu guardaria.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. Good news settles at dusk, in my experience. It needs the day to be over first.
    >>  ............................................
    pt  O anoitecer. Boa notícia assenta ao anoitecer, na minha experiência. Precisa que o dia acabe.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. It'll still be true tomorrow, and that's the best part of all.
    >>  ............................................
    pt  O fim do dia. Vai continuar verdade amanhã, e essa é a melhor parte.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true and it hasn't been taken back.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade e não foi retirado.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. I keep checking that it's still there, and it keeps being there.
    >>  ............................................
    pt  O anoitecer. Eu fico conferindo se ainda está lá, e continua estando.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. I've not had many of those lately, %1$s, so it counts double.
    >>  ............................................
    pt  O fim do dia. Não tive muitos desses ultimamente, %1$s, então conta em dobro.
    >>  ............................................
  shy.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade.
    >>  ............................................
  shy.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  Dusk. When I remember and it hasn't gone.
    >>  ............................................
    pt  O anoitecer. Quando eu lembro e não foi embora.
    >>  ............................................
  shy.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  The end of the day. That's the part.
    >>  ............................................
    pt  O fim do dia. É essa a parte.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true! That's the best of it.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade! É o melhor.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. All the shouting is fine, but the quiet bit is where it lands.
    >>  ............................................
    pt  O fim do dia. A gritaria é boa, mas é na parte quieta que assenta.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering it at dusk and finding it hasn't gone. That's the part I'd keep.
    >>  ............................................
    pt  Lembrar disso ao anoitecer e ver que não foi embora. É a parte que eu guardaria.
    >>  ............................................
  witty.dialogue.conversations.noticed.elated.followup.ask_best/1
    en  The quiet bit at the end of the day, when I remember it's still true! That's the best of it.
    >>  ............................................
    pt  A parte quieta no fim do dia, quando eu lembro que ainda é verdade! É o melhor.
    >>  ............................................
  witty.dialogue.conversations.noticed.elated.followup.ask_best/2
    en  The end of the day. All the shouting is fine, but the quiet bit is where it lands.
    >>  ............................................
    pt  O fim do dia. A gritaria é boa, mas é na parte quieta que assenta.
    >>  ............................................
  witty.dialogue.conversations.noticed.elated.followup.ask_best/3
    en  Remembering it at dusk and finding it hasn't gone. That's the part I'd keep.
    >>  ............................................
    pt  Lembrar disso ao anoitecer e ver que não foi embora. É a parte que eu guardaria.
    >>  ............................................
```

</details>


### Button `encourage` — "Make the most of it."

*stance family `encouragement` · tone `playful` · outcome `appreciated` · answers the beat(s) `noticed.elated.joy_shared`, `noticed.elated.cause_told`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.followup.encourage` — accepted phrasings: "make the most of it"; "enjoy it while it lasts"; "savour it"
  - the message must contain one of: `enjoy`, `savour`, `lasts`
  - scored words: `enjoy`(1.5), `savour`(1.5), `lasts`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.followup.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.followup.encourage   [20 chars]
    en  Make the most of it.
    >>  ............................................
    pt  Aproveita bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.elated.followup.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `noticed.elated.followup.encourage`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.followup.encourage
WHO    VILLAGER — what the player reads after pressing "Make the most of it."
       spoken on: conversations.topic.noticed.elated.followup, button `encourage`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.followup.encourage`: the villager celebrates. Subject `noticed.elation`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.followup.encourage/1   [56 chars]
    en  Oh, I intend to. There are plans, and they involve cake.
    >>  ............................................
    pt  Ah, pretendo. Tem planos, e eles envolvem bolo.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.encourage/2   [70 chars]
    en  That's the plan. Good weeks are owed to somebody, and this one's mine.
    >>  ............................................
    pt  É o plano. Boas semanas são devidas a alguém, e essa é minha.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.encourage/3   [45 chars]
    en  I will, %1$s. You should too — it's catching.
    >>  ............................................
    pt  Vou sim, %1$s. Você também devia — isso pega.
    >>  ............................................
```


### Button `share_own` — "It's been a good week for me too."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.elated.joy_shared`, `noticed.elated.cause_told`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.followup.share_own` — accepted phrasings: "it has been a good week for me too"; "mine has been good too"; "a good week for me as well"
  - the message must contain one of: `week`, `mine`, `too`
  - scored words: `week`(1.5), `mine`(1.2), `too`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.followup.share_own   [33 chars]
    en  It's been a good week for me too.
    >>  ............................................
    pt  Também foi uma boa semana pra mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +2  _(recorded under topic `noticed.elated.followup.share_own`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.followup.share_own
WHO    VILLAGER — what the player reads after pressing "It's been a good week for me too."
       spoken on: conversations.topic.noticed.elated.followup, button `share_own`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.followup.share_own`: the villager celebrates. Subject `noticed.elation`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.followup.share_own/1   [58 chars]
    en  Then that's two of us, and the week can't argue with both.
    >>  ............................................
    pt  Então somos dois, e a semana não discute com os dois.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.share_own/2   [65 chars]
    en  Good! Tell me one thing about it and I'll be smug on your behalf.
    >>  ............................................
    pt  Ótimo! Me conta uma coisa dela e eu me gabo por você.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.share_own/3   [44 chars]
    en  Ha. Look at us, %1$s. Practically fortunate.
    >>  ............................................
    pt  Ha. Olha nós dois, %1$s. Praticamente afortunados.
    >>  ............................................
```


### Button `leave` — "I'll leave you to enjoy it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.elated.joy_shared`, `noticed.elated.cause_told` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.followup.leave   [27 chars]
    en  I'll leave you to enjoy it.
    >>  ............................................
    pt  Vou deixar você aproveitar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to enjoy it."
       spoken on: conversations.topic.noticed.elated.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.followup.leave`: the villager accepts. Subject `noticed.elation`, polarity `positive`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.followup.leave/1   [37 chars]
    en  So it is, do. Thank you for stopping.
    >>  ............................................
    pt  É assim, pode ir. Obrigado por ter parado.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.leave/2   [39 chars]
    en  Off you go, %1$s. Mind you enjoy yours.
    >>  ............................................
    pt  Pode ir, %1$s. Trate de aproveitar a sua também.
    >>  ............................................
  dialogue.conversations.noticed.elated.followup.leave/3   [10 chars]
    en  Go safely.
    >>  ............................................
    pt  Vá com cuidado.
    >>  ............................................
```

---


## `conversations.topic.noticed.elated.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.elated` — e.g. "Oh, it's a good few days! There's been happy news in the village — you can feel it in the square."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.elated.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.elated.respond   [29 chars]
    en  Things have been good lately.
    >>  ............................................
    pt  As coisas têm sido boas ultimamente.
    >>  ............................................
```


### Button `share_joy` — "It's good to see you like this."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.elated.open` · offered only once the villager has actually said `state:elated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.share_joy` — accepted phrasings: "it is good to see you like this"; "good to see you happy"; "nice to see you like this"
  - the message must contain one of: `see`, `happy`, `good`
  - scored words: `see`(1.0), `like`(0.6), `happy`(1.2), `good`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.respond.share_joy
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.respond.share_joy   [31 chars]
    en  It's good to see you like this.
    >>  ............................................
    pt  É bom te ver assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.elated.share_joy`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4  _(recorded under topic `noticed.elated.share_joy`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.elated.followup`
- …where the player's next choices will be: "What's the best of it?" | "Make the most of it." | "It's been a good week for me too." | "I'll leave you to enjoy it."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.share_joy
WHO    VILLAGER — what the player reads after pressing "It's good to see you like this."
       spoken on: conversations.topic.noticed.elated.respond, button `share_joy`
       leaves the player on: conversations.topic.noticed.elated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.joy_shared`: the villager accepts. Subject `noticed.elation`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `state:elated` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, self_disclosure, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.share_joy/1   [51 chars]
    en  It's good to BE like this. It's been a while, %1$s.
    >>  ............................................
    pt  É bom ESTAR assim. Já fazia um tempo, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.elated.share_joy/2   [40 chars]
    en  Ha! Don't get used to it. But thank you.
    >>  ............................................
    pt  Rá! Não se acostume. Mas obrigado.
    >>  ............................................
  dialogue.conversations.noticed.elated.share_joy/3   [41 chars]
    en  Somebody noticing makes it better, oddly.
    >>  ............................................
    pt  Alguém notar torna melhor, estranhamente.
    >>  ............................................
```


### Button `ask_more` — "What's brought this on?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.elated.open` · offered only once the villager has actually said `state:elated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.ask_more` — accepted phrasings: "what has brought this on"; "what caused this"; "what brought that on"
  - the message must contain one of: `brought`, `caused`
  - scored words: `brought`(1.5), `caused`(1.2), `what`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.respond.ask_more   [23 chars]
    en  What's brought this on?
    >>  ............................................
    pt  O que causou isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `noticed.elated.ask_more`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.elated.followup`
- …where the player's next choices will be: "What's the best of it?" | "Make the most of it." | "It's been a good week for me too." | "I'll leave you to enjoy it."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.ask_more
WHO    VILLAGER — what the player reads after pressing "What's brought this on?"
       spoken on: conversations.topic.noticed.elated.respond, button `ask_more`
       leaves the player on: conversations.topic.noticed.elated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.cause_told`: the villager explains. Subject `noticed.elation`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `state:elated` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, self_disclosure, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.ask_more/1   [58 chars]
    en  The whole village, really. Good weeks are contagious here.
    >>  ............................................
    pt  A vila inteira, na real. Semanas boas são contagiosas aqui.
    >>  ............................................
  dialogue.conversations.noticed.elated.ask_more/2   [50 chars]
    en  A few things at once. I've stopped questioning it.
    >>  ............................................
    pt  Várias coisas de uma vez. Parei de questionar.
    >>  ............................................
  dialogue.conversations.noticed.elated.ask_more/3   [59 chars]
    en  You'll have heard. Everyone has. It's that sort of village.
    >>  ............................................
    pt  Você deve ter ouvido. Todo mundo ouviu. É esse tipo de vila.
    >>  ............................................
```


### Button `deflate` — "Don't get carried away."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.elated.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.elated.deflate` — accepted phrasings: "do not get carried away"; "do not get ahead of yourself"; "steady on"
  - the message must contain one of: `carried`, `away`, `ahead`
  - scored words: `carried`(1.5), `away`(1.0), `ahead`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.respond.deflate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.respond.deflate   [23 chars]
    en  Don't get carried away.
    >>  ............................................
    pt  Não se empolga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `noticed.elated.deflate`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `noticed.elated.deflate`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.elated.deflated.followup`
- …where the player's next choices will be: "No — you've earned a good week." | "I meant it kindly. It came out wrong." | "Good weeks end. I'd rather you were ready." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.deflate
WHO    VILLAGER — what the player reads after pressing "Don't get carried away."
       spoken on: conversations.topic.noticed.elated.respond, button `deflate`
       leaves the player on: conversations.topic.noticed.elated.deflated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.deflated`: the villager hurts. Subject `noticed.elation`, polarity `negative`, guarded, outcome `hurt`.
NOTE   this is the line that establishes `state:elated`, `player:deflated_joy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.deflate/1   [46 chars]
    en  ...I'll get carried away if I like, thank you.
    >>  ............................................
    pt  ...Vou me empolgar se eu quiser, obrigado.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflate/2   [53 chars]
    en  Must you. It's been a good week and you've dented it.
    >>  ............................................
    pt  Precisa mesmo. Foi uma boa semana e você amassou.
    >>  ............................................
  dialogue.conversations.noticed.elated.deflate/3   [57 chars]
    en  Noted. I'll be measured about my own happiness in future.
    >>  ............................................
    pt  Anotado. Vou ser comedido com a minha própria felicidade no futuro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'd only had it for an hour, %1$s. It was a good hour.
    >>  ............................................
    pt  ...Eu só tinha isso há uma hora, %1$s. Foi uma boa hora.
    >>  ............................................
  anxious.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Yes. I'll not make a fool of myself over it twice.
    >>  ............................................
    pt  Certo. Sim. Não vou me fazer de bobo duas vezes por isso.
    >>  ............................................
  anxious.dialogue.conversations.noticed.elated.deflate/3
    en  ...I know it's small. Everything good is small at the moment.
    >>  ............................................
    pt  ...Eu sei que é pequeno. Tudo de bom é pequeno no momento.
    >>  ............................................
  athletic.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like. It'll pass on its own soon enough.
    >>  ............................................
    pt  Eu me empolgo se eu quiser. Vai passar sozinho logo.
    >>  ............................................
  athletic.dialogue.conversations.noticed.elated.deflate/2
    en  ...Aye, probably. Let me have the rest of the afternoon with it, though.
    >>  ............................................
    pt  ...É, provavelmente. Mas me deixe ficar com o resto da tarde.
    >>  ............................................
  athletic.dialogue.conversations.noticed.elated.deflate/3
    en  Right. It was good while it was, and that counts.
    >>  ............................................
    pt  Certo. Foi bom enquanto foi, e isso conta.
    >>  ............................................
  confident.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like, thank you.
    >>  ............................................
    pt  Eu me empolgo se eu quiser, obrigado.
    >>  ............................................
  confident.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Noted. I'll keep the good ones at a sensible volume.
    >>  ............................................
    pt  Certo. Anotado. Vou manter as boas num volume sensato.
    >>  ............................................
  confident.dialogue.conversations.noticed.elated.deflate/3
    en  ...That's the wind out of it. Well done.
    >>  ............................................
    pt  ...Lá se foi o vento. Muito bem.
    >>  ............................................
  crabby.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like, thank you.
    >>  ............................................
    pt  Eu me empolgo se eu quiser, obrigado.
    >>  ............................................
  crabby.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Noted. I'll keep the good ones at a sensible volume.
    >>  ............................................
    pt  Certo. Anotado. Vou manter as boas num volume sensato.
    >>  ............................................
  crabby.dialogue.conversations.noticed.elated.deflate/3
    en  ...That's the wind out of it. Well done.
    >>  ............................................
    pt  ...Lá se foi o vento. Muito bem.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'll get carried away if I like, %1$s. I'd wanted you carried away with me.
    >>  ............................................
    pt  ...Eu me empolgo se eu quiser, %1$s. Eu queria você empolgado comigo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.elated.deflate/2
    en  That's a cold thing to do to somebody who was that pleased.
    >>  ............................................
    pt  É uma coisa fria de se fazer com alguém que estava tão contente.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.elated.deflate/3
    en  ...Right. I'll be pleased about it on my own.
    >>  ............................................
    pt  ...Certo. Vou ficar contente sozinho.
    >>  ............................................
  flirty.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'll get carried away if I like, %1$s. I'd wanted you carried away with me.
    >>  ............................................
    pt  ...Eu me empolgo se eu quiser, %1$s. Eu queria você empolgado comigo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.elated.deflate/2
    en  That's a cold thing to do to somebody who was that pleased.
    >>  ............................................
    pt  É uma coisa fria de se fazer com alguém que estava tão contente.
    >>  ............................................
  flirty.dialogue.conversations.noticed.elated.deflate/3
    en  ...Right. I'll be pleased about it on my own.
    >>  ............................................
    pt  ...Certo. Vou ficar contente sozinho.
    >>  ............................................
  friendly.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'll get carried away if I like, %1$s. I'd wanted you carried away with me.
    >>  ............................................
    pt  ...Eu me empolgo se eu quiser, %1$s. Eu queria você empolgado comigo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.elated.deflate/2
    en  That's a cold thing to do to somebody who was that pleased.
    >>  ............................................
    pt  É uma coisa fria de se fazer com alguém que estava tão contente.
    >>  ............................................
  friendly.dialogue.conversations.noticed.elated.deflate/3
    en  ...Right. I'll be pleased about it on my own.
    >>  ............................................
    pt  ...Certo. Vou ficar contente sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'd only had it for an hour, %1$s. It was a good hour.
    >>  ............................................
    pt  ...Eu só tinha isso há uma hora, %1$s. Foi uma boa hora.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Yes. I'll not make a fool of myself over it twice.
    >>  ............................................
    pt  Certo. Sim. Não vou me fazer de bobo duas vezes por isso.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.elated.deflate/3
    en  ...I know it's small. Everything good is small at the moment.
    >>  ............................................
    pt  ...Eu sei que é pequeno. Tudo de bom é pequeno no momento.
    >>  ............................................
  greedy.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like, thank you.
    >>  ............................................
    pt  Eu me empolgo se eu quiser, obrigado.
    >>  ............................................
  greedy.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Noted. I'll keep the good ones at a sensible volume.
    >>  ............................................
    pt  Certo. Anotado. Vou manter as boas num volume sensato.
    >>  ............................................
  greedy.dialogue.conversations.noticed.elated.deflate/3
    en  ...That's the wind out of it. Well done.
    >>  ............................................
    pt  ...Lá se foi o vento. Muito bem.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like, thank you.
    >>  ............................................
    pt  Eu me empolgo se eu quiser, obrigado.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Noted. I'll keep the good ones at a sensible volume.
    >>  ............................................
    pt  Certo. Anotado. Vou manter as boas num volume sensato.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.elated.deflate/3
    en  ...That's the wind out of it. Well done.
    >>  ............................................
    pt  ...Lá se foi o vento. Muito bem.
    >>  ............................................
  introverted.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'll get carried away if I like.
    >>  ............................................
    pt  ...Eu me empolgo se eu quiser.
    >>  ............................................
  introverted.dialogue.conversations.noticed.elated.deflate/2
    en  Right. That's that put back down.
    >>  ............................................
    pt  Certo. Isso foi posto de volta no chão.
    >>  ............................................
  introverted.dialogue.conversations.noticed.elated.deflate/3
    en  ...I'll not say more about it.
    >>  ............................................
    pt  ...Não falo mais disso.
    >>  ............................................
  lazy.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like. It'll pass on its own soon enough.
    >>  ............................................
    pt  Eu me empolgo se eu quiser. Vai passar sozinho logo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.elated.deflate/2
    en  ...Aye, probably. Let me have the rest of the afternoon with it, though.
    >>  ............................................
    pt  ...É, provavelmente. Mas me deixe ficar com o resto da tarde.
    >>  ............................................
  lazy.dialogue.conversations.noticed.elated.deflate/3
    en  Right. It was good while it was, and that counts.
    >>  ............................................
    pt  Certo. Foi bom enquanto foi, e isso conta.
    >>  ............................................
  odd.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'll get carried away if I like.
    >>  ............................................
    pt  ...Eu me empolgo se eu quiser.
    >>  ............................................
  odd.dialogue.conversations.noticed.elated.deflate/2
    en  Right. That's that put back down.
    >>  ............................................
    pt  Certo. Isso foi posto de volta no chão.
    >>  ............................................
  odd.dialogue.conversations.noticed.elated.deflate/3
    en  ...I'll not say more about it.
    >>  ............................................
    pt  ...Não falo mais disso.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like. It'll pass on its own soon enough.
    >>  ............................................
    pt  Eu me empolgo se eu quiser. Vai passar sozinho logo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.elated.deflate/2
    en  ...Aye, probably. Let me have the rest of the afternoon with it, though.
    >>  ............................................
    pt  ...É, provavelmente. Mas me deixe ficar com o resto da tarde.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.elated.deflate/3
    en  Right. It was good while it was, and that counts.
    >>  ............................................
    pt  Certo. Foi bom enquanto foi, e isso conta.
    >>  ............................................
  peppy.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like! It's my afternoon and I'm having it.
    >>  ............................................
    pt  Eu me empolgo se eu quiser! É a minha tarde e eu vou aproveitar.
    >>  ............................................
  peppy.dialogue.conversations.noticed.elated.deflate/2
    en  Right! Down we come. That was a very efficient landing, %1$s.
    >>  ............................................
    pt  Certo! Descemos. Foi um pouso muito eficiente, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.noticed.elated.deflate/3
    en  ...Ha. Fine. I'll be delighted quietly in a corner.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar encantado em silêncio num canto.
    >>  ............................................
  playful.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like! It's my afternoon and I'm having it.
    >>  ............................................
    pt  Eu me empolgo se eu quiser! É a minha tarde e eu vou aproveitar.
    >>  ............................................
  playful.dialogue.conversations.noticed.elated.deflate/2
    en  Right! Down we come. That was a very efficient landing, %1$s.
    >>  ............................................
    pt  Certo! Descemos. Foi um pouso muito eficiente, %1$s.
    >>  ............................................
  playful.dialogue.conversations.noticed.elated.deflate/3
    en  ...Ha. Fine. I'll be delighted quietly in a corner.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar encantado em silêncio num canto.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like. It'll pass on its own soon enough.
    >>  ............................................
    pt  Eu me empolgo se eu quiser. Vai passar sozinho logo.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.elated.deflate/2
    en  ...Aye, probably. Let me have the rest of the afternoon with it, though.
    >>  ............................................
    pt  ...É, provavelmente. Mas me deixe ficar com o resto da tarde.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.elated.deflate/3
    en  Right. It was good while it was, and that counts.
    >>  ............................................
    pt  Certo. Foi bom enquanto foi, e isso conta.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'd only had it for an hour, %1$s. It was a good hour.
    >>  ............................................
    pt  ...Eu só tinha isso há uma hora, %1$s. Foi uma boa hora.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.elated.deflate/2
    en  Right. Yes. I'll not make a fool of myself over it twice.
    >>  ............................................
    pt  Certo. Sim. Não vou me fazer de bobo duas vezes por isso.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.elated.deflate/3
    en  ...I know it's small. Everything good is small at the moment.
    >>  ............................................
    pt  ...Eu sei que é pequeno. Tudo de bom é pequeno no momento.
    >>  ............................................
  shy.dialogue.conversations.noticed.elated.deflate/1
    en  ...I'll get carried away if I like.
    >>  ............................................
    pt  ...Eu me empolgo se eu quiser.
    >>  ............................................
  shy.dialogue.conversations.noticed.elated.deflate/2
    en  Right. That's that put back down.
    >>  ............................................
    pt  Certo. Isso foi posto de volta no chão.
    >>  ............................................
  shy.dialogue.conversations.noticed.elated.deflate/3
    en  ...I'll not say more about it.
    >>  ............................................
    pt  ...Não falo mais disso.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like! It's my afternoon and I'm having it.
    >>  ............................................
    pt  Eu me empolgo se eu quiser! É a minha tarde e eu vou aproveitar.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.elated.deflate/2
    en  Right! Down we come. That was a very efficient landing, %1$s.
    >>  ............................................
    pt  Certo! Descemos. Foi um pouso muito eficiente, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.elated.deflate/3
    en  ...Ha. Fine. I'll be delighted quietly in a corner.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar encantado em silêncio num canto.
    >>  ............................................
  witty.dialogue.conversations.noticed.elated.deflate/1
    en  I'll get carried away if I like! It's my afternoon and I'm having it.
    >>  ............................................
    pt  Eu me empolgo se eu quiser! É a minha tarde e eu vou aproveitar.
    >>  ............................................
  witty.dialogue.conversations.noticed.elated.deflate/2
    en  Right! Down we come. That was a very efficient landing, %1$s.
    >>  ............................................
    pt  Certo! Descemos. Foi um pouso muito eficiente, %1$s.
    >>  ............................................
  witty.dialogue.conversations.noticed.elated.deflate/3
    en  ...Ha. Fine. I'll be delighted quietly in a corner.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar encantado em silêncio num canto.
    >>  ............................................
```

</details>


### Button `leave` — "Enjoy it. I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.elated.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.elated.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.elated.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.elated.respond.leave   [18 chars]
    en  Enjoy it. I'll go.
    >>  ............................................
    pt  Aproveita. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated.leave
WHO    VILLAGER — what the player reads after pressing "Enjoy it. I'll go."
       spoken on: conversations.topic.noticed.elated.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.left`: the villager accepts. Subject `noticed.elation`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated.leave/1   [20 chars]
    en  I will. Go on, %1$s.
    >>  ............................................
    pt  Vou sim. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.elated.leave/2   [16 chars]
    en  Aye! Off you go.
    >>  ............................................
    pt  É! Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.elated.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.topic.noticed.expecting.followup`

**Reached from 3 route(s):** `conversations.topic.noticed.expecting.respond` / `how_feeling`; `conversations.topic.noticed.expecting.respond` / `what_needed`; `conversations.topic.noticed.expecting.respond` / `ready`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.expecting.how_feeling` — e.g. "Frightened and pleased at the same hour, which nobody warns you about."
- `conversations.noticed.expecting.ready` — e.g. "No, and I'm told that's usual. It hasn't stopped me lying awake about it."
- `conversations.noticed.expecting.what_needed` — e.g. "Sleep, and nobody's yet worked out how to carry that up the lane."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.expecting.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.expecting.followup   [21 chars]
    en  That's how it stands.
    >>  ............................................
    pt  É assim que está.
    >>  ............................................
```


### Button `bring_it` — "I'll bring what you need. Just say the word."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.expecting.how_feeling`, `noticed.expecting.what_needed`, `noticed.expecting.ready`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.expecting.followup.bring` — accepted phrasings: "i will bring what you need"; "just tell me what you need"; "say the word and i will fetch it"
  - the message must contain one of: `bring`
  - scored words: `bring`(1.2), `need`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.followup.bring_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.followup.bring_it   [44 chars]
    en  I'll bring what you need. Just say the word.
    >>  ............................................
    pt  Eu trago o que você precisar. É só falar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.expecting.followup.bring`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1, warmth +3  _(recorded under topic `noticed.expecting.followup.bring`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.followup.bring
WHO    VILLAGER — what the player reads after pressing "I'll bring what you need. Just say the word."
       spoken on: conversations.topic.noticed.expecting.followup, button `bring_it`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.followup.bring`: the villager accepts. Subject `noticed.pregnancy`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.followup.bring/1   [62 chars]
    en  I'll hold you to that on a bad morning, and there will be one.
    >>  ............................................
    pt  Vou cobrar isso numa manhã ruim, e vai ter uma.
    >>  ............................................
  dialogue.conversations.noticed.expecting.followup.bring/2   [63 chars]
    en  Careful. I'm past the stage of politely declining things, %1$s.
    >>  ............................................
    pt  Cuidado. Já passei da fase de recusar as coisas educadamente, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.expecting.followup.bring/3   [64 chars]
    en  Then I'll ask, which is more than I've managed with anyone else.
    >>  ............................................
    pt  Então eu vou pedir, o que é mais do que consegui com qualquer outra pessoa.
    >>  ............................................
```


### Button `glad_for_you` — "I'm glad for you. Truly."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.expecting.how_feeling`, `noticed.expecting.what_needed`, `noticed.expecting.ready`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.expecting.followup.glad` — accepted phrasings: "i am glad for you"; "i am truly happy for you"; "i am pleased to hear it"
  - the message must contain one of: `glad`, `truly`
  - scored words: `glad`(1.2), `truly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.followup.glad_for_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.followup.glad_for_you   [24 chars]
    en  I'm glad for you. Truly.
    >>  ............................................
    pt  Estou feliz por você. De verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.expecting.followup.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +2  _(recorded under topic `noticed.expecting.followup.glad`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.followup.glad
WHO    VILLAGER — what the player reads after pressing "I'm glad for you. Truly."
       spoken on: conversations.topic.noticed.expecting.followup, button `glad_for_you`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.followup.glad`: the villager accepts. Subject `noticed.pregnancy`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.followup.glad/1   [68 chars]
    en  Thank you. Everyone says it; you're one of the few who paused first.
    >>  ............................................
    pt  Obrigada. Todos dizem; você é um dos poucos que pausou antes.
    >>  ............................................
  dialogue.conversations.noticed.expecting.followup.glad/2   [66 chars]
    en  So am I, on the good days, and I've decided to count those double.
    >>  ............................................
    pt  Eu também, nos dias bons, e decidi contar esses em dobro.
    >>  ............................................
  dialogue.conversations.noticed.expecting.followup.glad/3   [70 chars]
    en  That's kind. I'll take kind — I've had a great deal of advice instead.
    >>  ............................................
    pt  É gentil. Eu aceito gentil — tenho recebido muito conselho no lugar.
    >>  ............................................
```


### Button `leave` — "I'll let you sit down."

*stance family `exit` · tone `gentle` · outcome `conversation_ended` · answers the beat(s) `noticed.expecting.how_feeling`, `noticed.expecting.what_needed`, `noticed.expecting.ready` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.followup.leave   [22 chars]
    en  I'll let you sit down.
    >>  ............................................
    pt  Vou deixar você sentar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you sit down."
       spoken on: conversations.topic.noticed.expecting.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.followup.leave`: the villager accepts. Subject `noticed.pregnancy`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.followup.leave/1   [12 chars]
    en  I intend to.
    >>  ............................................
    pt  Pretendo.
    >>  ............................................
  dialogue.conversations.noticed.expecting.followup.leave/2   [27 chars]
    en  Finally, somebody sensible.
    >>  ............................................
    pt  Enfim, alguém sensato.
    >>  ............................................
  dialogue.conversations.noticed.expecting.followup.leave/3   [24 chars]
    en  It is. Off you go, %1$s.
    >>  ............................................
    pt  É sim. Pode ir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.expecting.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.expecting` — e.g. "Tired in a way I don't mind, which is new. Ask me again at the end of the week."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.expecting.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.expecting.respond   [40 chars]
    en  That's the honest state of me this week.
    >>  ............................................
    pt  É o estado honesto de mim esta semana.
    >>  ............................................
```


### Button `how_feeling` — "How are you feeling, really?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `noticed.expecting.open` · offered only once the villager has actually said `noticed:expecting`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.expecting.how_feeling` — accepted phrasings: "how are you feeling really"; "how is it treating you"; "and how has it been for you"
  - the message must contain one of: `feeling`
  - scored words: `feeling`(1.2), `really`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.respond.how_feeling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.respond.how_feeling   [28 chars]
    en  How are you feeling, really?
    >>  ............................................
    pt  Como você está se sentindo, de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.expecting.followup`
- …where the player's next choices will be: "I'll bring what you need. Just say the word." | "I'm glad for you. Truly." | "I'll let you sit down."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.how_feeling
WHO    VILLAGER — what the player reads after pressing "How are you feeling, really?"
       spoken on: conversations.topic.noticed.expecting.respond, button `how_feeling`
       leaves the player on: conversations.topic.noticed.expecting.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.how_feeling`: the villager discloses. Subject `noticed.pregnancy`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.how_feeling/1   [70 chars]
    en  Frightened and pleased at the same hour, which nobody warns you about.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e ninguém te avisa disso.
    >>  ............................................
  dialogue.conversations.noticed.expecting.how_feeling/2   [76 chars]
    en  Well enough. Better than my mother had it, and she never let me forget hers.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela nunca me deixou esquecer.
    >>  ............................................
  dialogue.conversations.noticed.expecting.how_feeling/3   [70 chars]
    en  Some mornings I can't lace my own boots. The rest of it I'd not trade.
    >>  ............................................
    pt  Em algumas manhãs não consigo amarrar minhas botas. O resto eu não trocaria.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased in the same hour, and I can't hold both, so I take turns.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e não seguro as duas, então revezo.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. I keep checking she's still moving, and then I feel silly for checking.
    >>  ............................................
    pt  Bem o bastante. Fico checando se ela ainda se mexe, e depois me sinto boba por checar.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my boots and I sit on the step and cry about a boot.
    >>  ............................................
    pt  Em algumas manhãs não amarro as botas e sento no degrau e choro por causa de uma bota.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once. It was the same the first time and I'd forgotten.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo. Foi igual na primeira vez e eu tinha esquecido.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she said the same about hers.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela dizia o mesmo da dela.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings the boots defeat me. I've been defeated by smaller things.
    >>  ............................................
    pt  Em algumas manhãs as botas me vencem. Já fui vencida por coisas menores.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at the same hour, which nobody warns you about.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e ninguém te avisa disso.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my own boots. The rest I'd not trade.
    >>  ............................................
    pt  Em algumas manhãs não amarro minhas botas. O resto eu não trocaria.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at the same hour, which nobody warns you about.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e ninguém te avisa disso.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my own boots. The rest I'd not trade.
    >>  ............................................
    pt  Em algumas manhãs não amarro minhas botas. O resto eu não trocaria.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once, %1$s. I've told nobody the frightened half.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo, %1$s. Não contei a ninguém a metade assustada.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and I think of her more than I did.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e penso nela mais do que antes.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my boots. You may laugh — I did.
    >>  ............................................
    pt  Em algumas manhãs não amarro as botas. Pode rir — eu ri.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once, %1$s. I've told nobody the frightened half.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo, %1$s. Não contei a ninguém a metade assustada.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and I think of her more than I did.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e penso nela mais do que antes.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my boots. You may laugh — I did.
    >>  ............................................
    pt  Em algumas manhãs não amarro as botas. Pode rir — eu ri.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once, %1$s. I've told nobody the frightened half.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo, %1$s. Não contei a ninguém a metade assustada.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and I think of her more than I did.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e penso nela mais do que antes.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my boots. You may laugh — I did.
    >>  ............................................
    pt  Em algumas manhãs não amarro as botas. Pode rir — eu ri.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased in the same hour, and I can't hold both, so I take turns.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e não seguro as duas, então revezo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. I keep checking she's still moving, and then I feel silly for checking.
    >>  ............................................
    pt  Bem o bastante. Fico checando se ela ainda se mexe, e depois me sinto boba por checar.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my boots and I sit on the step and cry about a boot.
    >>  ............................................
    pt  Em algumas manhãs não amarro as botas e sento no degrau e choro por causa de uma bota.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at the same hour, which nobody warns you about.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e ninguém te avisa disso.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my own boots. The rest I'd not trade.
    >>  ............................................
    pt  Em algumas manhãs não amarro minhas botas. O resto eu não trocaria.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at the same hour, which nobody warns you about.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e ninguém te avisa disso.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my own boots. The rest I'd not trade.
    >>  ............................................
    pt  Em algumas manhãs não amarro minhas botas. O resto eu não trocaria.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough.
    >>  ............................................
    pt  Bem o bastante.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings are harder than others.
    >>  ............................................
    pt  Algumas manhãs são mais difíceis que outras.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once. It was the same the first time and I'd forgotten.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo. Foi igual na primeira vez e eu tinha esquecido.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she said the same about hers.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela dizia o mesmo da dela.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings the boots defeat me. I've been defeated by smaller things.
    >>  ............................................
    pt  Em algumas manhãs as botas me vencem. Já fui vencida por coisas menores.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough.
    >>  ............................................
    pt  Bem o bastante.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings are harder than others.
    >>  ............................................
    pt  Algumas manhãs são mais difíceis que outras.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once. It was the same the first time and I'd forgotten.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo. Foi igual na primeira vez e eu tinha esquecido.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she said the same about hers.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela dizia o mesmo da dela.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings the boots defeat me. I've been defeated by smaller things.
    >>  ............................................
    pt  Em algumas manhãs as botas me vencem. Já fui vencida por coisas menores.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and delighted in the same hour. Nobody warns you about that bit!
    >>  ............................................
    pt  Assustada e encantada na mesma hora. Ninguém te avisa dessa parte!
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she reminded me of hers weekly.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela me lembrava da dela toda semana.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings my boots win. The rest of it I'd not trade for a quiet house.
    >>  ............................................
    pt  Em algumas manhãs as botas ganham. O resto eu não trocaria por uma casa quieta.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and delighted in the same hour. Nobody warns you about that bit!
    >>  ............................................
    pt  Assustada e encantada na mesma hora. Ninguém te avisa dessa parte!
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she reminded me of hers weekly.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela me lembrava da dela toda semana.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings my boots win. The rest of it I'd not trade for a quiet house.
    >>  ............................................
    pt  Em algumas manhãs as botas ganham. O resto eu não trocaria por uma casa quieta.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once. It was the same the first time and I'd forgotten.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo. Foi igual na primeira vez e eu tinha esquecido.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she said the same about hers.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela dizia o mesmo da dela.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings the boots defeat me. I've been defeated by smaller things.
    >>  ............................................
    pt  Em algumas manhãs as botas me vencem. Já fui vencida por coisas menores.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased in the same hour, and I can't hold both, so I take turns.
    >>  ............................................
    pt  Assustada e contente na mesma hora, e não seguro as duas, então revezo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. I keep checking she's still moving, and then I feel silly for checking.
    >>  ............................................
    pt  Bem o bastante. Fico checando se ela ainda se mexe, e depois me sinto boba por checar.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings I can't lace my boots and I sit on the step and cry about a boot.
    >>  ............................................
    pt  Em algumas manhãs não amarro as botas e sento no degrau e choro por causa de uma bota.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and pleased at once.
    >>  ............................................
    pt  Assustada e contente ao mesmo tempo.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough.
    >>  ............................................
    pt  Bem o bastante.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings are harder than others.
    >>  ............................................
    pt  Algumas manhãs são mais difíceis que outras.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and delighted in the same hour. Nobody warns you about that bit!
    >>  ............................................
    pt  Assustada e encantada na mesma hora. Ninguém te avisa dessa parte!
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she reminded me of hers weekly.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela me lembrava da dela toda semana.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings my boots win. The rest of it I'd not trade for a quiet house.
    >>  ............................................
    pt  Em algumas manhãs as botas ganham. O resto eu não trocaria por uma casa quieta.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.how_feeling/1
    en  Frightened and delighted in the same hour. Nobody warns you about that bit!
    >>  ............................................
    pt  Assustada e encantada na mesma hora. Ninguém te avisa dessa parte!
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.how_feeling/2
    en  Well enough. Better than my mother had it, and she reminded me of hers weekly.
    >>  ............................................
    pt  Bem o bastante. Melhor do que minha mãe teve, e ela me lembrava da dela toda semana.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.how_feeling/3
    en  Some mornings my boots win. The rest of it I'd not trade for a quiet house.
    >>  ............................................
    pt  Em algumas manhãs as botas ganham. O resto eu não trocaria por uma casa quieta.
    >>  ............................................
```

</details>


### Button `what_needed` — "Is there anything you're short of?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.expecting.open` · offered only once the villager has actually said `noticed:expecting`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.expecting.what_needed` — accepted phrasings: "is there anything you are short of"; "do you need anything"; "is there anything you are missing"
  - the message must contain one of: `short`
  - scored words: `short`(1.2), `anything`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.respond.what_needed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.respond.what_needed   [34 chars]
    en  Is there anything you're short of?
    >>  ............................................
    pt  Está faltando alguma coisa pra você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.expecting.followup`
- …where the player's next choices will be: "I'll bring what you need. Just say the word." | "I'm glad for you. Truly." | "I'll let you sit down."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.what_needed
WHO    VILLAGER — what the player reads after pressing "Is there anything you're short of?"
       spoken on: conversations.topic.noticed.expecting.respond, button `what_needed`
       leaves the player on: conversations.topic.noticed.expecting.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.what_needed`: the villager request_helps. Subject `noticed.pregnancy`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.what_needed/1   [65 chars]
    en  Sleep, and nobody's yet worked out how to carry that up the lane.
    >>  ............................................
    pt  Sono, e ninguém ainda descobriu como carregar isso rua acima.
    >>  ............................................
  dialogue.conversations.noticed.expecting.what_needed/2   [81 chars]
    en  Wool, if you're offering. Everything I've knitted so far has been the wrong size.
    >>  ............................................
    pt  Lã, se estiver oferecendo. Tudo que tricotei até agora saiu do tamanho errado.
    >>  ............................................
  dialogue.conversations.noticed.expecting.what_needed/3   [73 chars]
    en  Someone to take the heavy end of things without making a speech about it.
    >>  ............................................
    pt  Alguém pra pegar a ponta pesada das coisas sem fazer discurso sobre isso.
    >>  ............................................
```


### Button `ready` — "Do you feel ready?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.expecting.open` · offered only once the villager has actually said `noticed:expecting`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.expecting.ready` — accepted phrasings: "do you feel ready"; "are you ready for it"; "do you feel prepared"
  - the message must contain one of: `ready`, `prepared`
  - scored words: `ready`(1.5), `prepared`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.respond.ready
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.respond.ready   [18 chars]
    en  Do you feel ready?
    >>  ............................................
    pt  Você se sente pronta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.expecting.followup`
- …where the player's next choices will be: "I'll bring what you need. Just say the word." | "I'm glad for you. Truly." | "I'll let you sit down."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.ready
WHO    VILLAGER — what the player reads after pressing "Do you feel ready?"
       spoken on: conversations.topic.noticed.expecting.respond, button `ready`
       leaves the player on: conversations.topic.noticed.expecting.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.ready`: the villager discloses. Subject `noticed.pregnancy`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.ready/1   [73 chars]
    en  No, and I'm told that's usual. It hasn't stopped me lying awake about it.
    >>  ............................................
    pt  Não, e me dizem que é normal. Não me impediu de ficar acordada pensando.
    >>  ............................................
  dialogue.conversations.noticed.expecting.ready/2   [67 chars]
    en  Ready as a house half-built. It'll have to finish itself around us.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai ter que se terminar em volta da gente.
    >>  ............................................
  dialogue.conversations.noticed.expecting.ready/3   [80 chars]
    en  I was ready in spring. Now I'm just impatient, which I'm told is the next stage.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora só estou impaciente, que dizem ser a fase seguinte.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.expecting.ready/1
    en  No. I'm not, and saying so out loud is the most honest thing I've done this month.
    >>  ............................................
    pt  Não. Não estou, e dizer em voz alta é a coisa mais honesta que fiz este mês.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built, and I lie awake listening to the wind through it.
    >>  ............................................
    pt  Pronta como uma casa pela metade, e fico acordada ouvindo o vento passar por ela.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. I've had all summer to think and thinking hasn't helped.
    >>  ............................................
    pt  Eu estava pronta na primavera. Tive o verão inteiro pra pensar e pensar não ajudou.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.ready/1
    en  No, and nobody ever is. I wasn't the first time and that one turned out fine.
    >>  ............................................
    pt  Não, e ninguém nunca está. Não estava na primeira vez e aquela saiu bem.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. They all get finished eventually, one way or another.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Todas ficam prontas em algum momento.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Readiness comes and goes; the child comes regardless.
    >>  ............................................
    pt  Eu estava pronta na primavera. A prontidão vai e vem; a criança vem de qualquer jeito.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.ready/1
    en  No, and I'm told that's usual. It hasn't stopped me lying awake.
    >>  ............................................
    pt  Não, e me dizem que é normal. Não me impediu de ficar acordada.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll finish itself around us.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai se terminar em volta da gente.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, which is the next stage.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, que é a fase seguinte.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.ready/1
    en  No, and I'm told that's usual. It hasn't stopped me lying awake.
    >>  ............................................
    pt  Não, e me dizem que é normal. Não me impediu de ficar acordada.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll finish itself around us.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai se terminar em volta da gente.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, which is the next stage.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, que é a fase seguinte.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.ready/1
    en  No, %1$s, and I'd only admit that to you. Everyone else gets a brave face.
    >>  ............................................
    pt  Não, %1$s, e eu só admitiria isso pra você. Os outros ganham cara de corajosa.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. Come and see it when it's finished, whatever it is.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Venha ver quando estiver pronta, seja lá o que for.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Ask me again next week and you'll get a third answer.
    >>  ............................................
    pt  Eu estava pronta na primavera. Pergunte semana que vem e terá uma terceira resposta.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.ready/1
    en  No, %1$s, and I'd only admit that to you. Everyone else gets a brave face.
    >>  ............................................
    pt  Não, %1$s, e eu só admitiria isso pra você. Os outros ganham cara de corajosa.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. Come and see it when it's finished, whatever it is.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Venha ver quando estiver pronta, seja lá o que for.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Ask me again next week and you'll get a third answer.
    >>  ............................................
    pt  Eu estava pronta na primavera. Pergunte semana que vem e terá uma terceira resposta.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.ready/1
    en  No, %1$s, and I'd only admit that to you. Everyone else gets a brave face.
    >>  ............................................
    pt  Não, %1$s, e eu só admitiria isso pra você. Os outros ganham cara de corajosa.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. Come and see it when it's finished, whatever it is.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Venha ver quando estiver pronta, seja lá o que for.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Ask me again next week and you'll get a third answer.
    >>  ............................................
    pt  Eu estava pronta na primavera. Pergunte semana que vem e terá uma terceira resposta.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.ready/1
    en  No. I'm not, and saying so out loud is the most honest thing I've done this month.
    >>  ............................................
    pt  Não. Não estou, e dizer em voz alta é a coisa mais honesta que fiz este mês.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built, and I lie awake listening to the wind through it.
    >>  ............................................
    pt  Pronta como uma casa pela metade, e fico acordada ouvindo o vento passar por ela.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. I've had all summer to think and thinking hasn't helped.
    >>  ............................................
    pt  Eu estava pronta na primavera. Tive o verão inteiro pra pensar e pensar não ajudou.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.ready/1
    en  No, and I'm told that's usual. It hasn't stopped me lying awake.
    >>  ............................................
    pt  Não, e me dizem que é normal. Não me impediu de ficar acordada.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll finish itself around us.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai se terminar em volta da gente.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, which is the next stage.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, que é a fase seguinte.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.ready/1
    en  No, and I'm told that's usual. It hasn't stopped me lying awake.
    >>  ............................................
    pt  Não, e me dizem que é normal. Não me impediu de ficar acordada.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll finish itself around us.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai se terminar em volta da gente.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, which is the next stage.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, que é a fase seguinte.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.ready/1
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built.
    >>  ............................................
    pt  Pronta como uma casa pela metade.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring.
    >>  ............................................
    pt  Eu estava pronta na primavera.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.ready/1
    en  No, and nobody ever is. I wasn't the first time and that one turned out fine.
    >>  ............................................
    pt  Não, e ninguém nunca está. Não estava na primeira vez e aquela saiu bem.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. They all get finished eventually, one way or another.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Todas ficam prontas em algum momento.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Readiness comes and goes; the child comes regardless.
    >>  ............................................
    pt  Eu estava pronta na primavera. A prontidão vai e vem; a criança vem de qualquer jeito.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.ready/1
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built.
    >>  ............................................
    pt  Pronta como uma casa pela metade.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring.
    >>  ............................................
    pt  Eu estava pronta na primavera.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.ready/1
    en  No, and nobody ever is. I wasn't the first time and that one turned out fine.
    >>  ............................................
    pt  Não, e ninguém nunca está. Não estava na primeira vez e aquela saiu bem.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. They all get finished eventually, one way or another.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Todas ficam prontas em algum momento.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Readiness comes and goes; the child comes regardless.
    >>  ............................................
    pt  Eu estava pronta na primavera. A prontidão vai e vem; a criança vem de qualquer jeito.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.ready/1
    en  No! And apparently that's usual, which is the least comforting comfort I've had.
    >>  ............................................
    pt  Não! E aparentemente é normal, que é o consolo menos consolador que já recebi.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll have to finish itself around us, won't it.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai ter que se terminar em volta da gente.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, and impatient is much more fun.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, e impaciente é bem mais divertido.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.ready/1
    en  No! And apparently that's usual, which is the least comforting comfort I've had.
    >>  ............................................
    pt  Não! E aparentemente é normal, que é o consolo menos consolador que já recebi.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll have to finish itself around us, won't it.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai ter que se terminar em volta da gente.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, and impatient is much more fun.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, e impaciente é bem mais divertido.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.ready/1
    en  No, and nobody ever is. I wasn't the first time and that one turned out fine.
    >>  ............................................
    pt  Não, e ninguém nunca está. Não estava na primeira vez e aquela saiu bem.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. They all get finished eventually, one way or another.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Todas ficam prontas em algum momento.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Readiness comes and goes; the child comes regardless.
    >>  ............................................
    pt  Eu estava pronta na primavera. A prontidão vai e vem; a criança vem de qualquer jeito.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.ready/1
    en  No. I'm not, and saying so out loud is the most honest thing I've done this month.
    >>  ............................................
    pt  Não. Não estou, e dizer em voz alta é a coisa mais honesta que fiz este mês.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built, and I lie awake listening to the wind through it.
    >>  ............................................
    pt  Pronta como uma casa pela metade, e fico acordada ouvindo o vento passar por ela.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. I've had all summer to think and thinking hasn't helped.
    >>  ............................................
    pt  Eu estava pronta na primavera. Tive o verão inteiro pra pensar e pensar não ajudou.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.ready/1
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built.
    >>  ............................................
    pt  Pronta como uma casa pela metade.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring.
    >>  ............................................
    pt  Eu estava pronta na primavera.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.ready/1
    en  No! And apparently that's usual, which is the least comforting comfort I've had.
    >>  ............................................
    pt  Não! E aparentemente é normal, que é o consolo menos consolador que já recebi.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll have to finish itself around us, won't it.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai ter que se terminar em volta da gente.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, and impatient is much more fun.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, e impaciente é bem mais divertido.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.ready/1
    en  No! And apparently that's usual, which is the least comforting comfort I've had.
    >>  ............................................
    pt  Não! E aparentemente é normal, que é o consolo menos consolador que já recebi.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.ready/2
    en  Ready as a house half-built. It'll have to finish itself around us, won't it.
    >>  ............................................
    pt  Pronta como uma casa pela metade. Vai ter que se terminar em volta da gente.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.ready/3
    en  I was ready in spring. Now I'm impatient, and impatient is much more fun.
    >>  ............................................
    pt  Eu estava pronta na primavera. Agora estou impaciente, e impaciente é bem mais divertido.
    >>  ............................................
```

</details>


### Button `dismiss` — "Women manage it every day."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.expecting.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.expecting.dismissed` — accepted phrasings: "women manage it every day"; "people do this all the time"; "it is hardly unusual"
  - the message must contain one of: `manage`
  - scored words: `manage`(1.2), `every`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.respond.dismiss   [26 chars]
    en  Women manage it every day.
    >>  ............................................
    pt  Mulheres fazem isso todo dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `noticed.expecting.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -2, warmth -3  _(recorded under topic `noticed.expecting.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.dismissed
WHO    VILLAGER — what the player reads after pressing "Women manage it every day."
       spoken on: conversations.topic.noticed.expecting.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.dismissed`: the villager hurts. Subject `noticed.pregnancy`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.dismissed/1   [66 chars]
    en  They do. Every one of them alone, if that's the attitude on offer.
    >>  ............................................
    pt  Fazem. Cada uma delas sozinha, se é essa a atitude oferecida.
    >>  ............................................
  dialogue.conversations.noticed.expecting.dismissed/2   [64 chars]
    en  And every one of them remembers who said that to them. Good day.
    >>  ............................................
    pt  E cada uma lembra de quem disse isso pra ela. Bom dia.
    >>  ............................................
  dialogue.conversations.noticed.expecting.dismissed/3   [64 chars]
    en  Then I'll manage this conversation the same way, shall I. Alone.
    >>  ............................................
    pt  Então eu vou lidar com esta conversa do mesmo jeito, não é. Sozinha.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.expecting.dismissed/1
    en  ...They do. Alone, mostly. I'd hoped this once it might be different.
    >>  ............................................
    pt  ...Fazem. Sozinhas, principalmente. Eu esperava que desta vez fosse diferente.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one of them remembers who said it. I wish I could unhear you.
    >>  ............................................
    pt  Cada uma lembra quem disse. Queria poder desouvir você.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone. I only wanted somebody to say it sounded hard.
    >>  ............................................
    pt  Então me viro sozinha. Eu só queria que alguém dissesse que parece difícil.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. I've watched three generations of them do it alone, and it never got easier.
    >>  ............................................
    pt  Fazem. Vi três gerações fazerem sozinhas, e nunca ficou mais fácil.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one remembers who said it. I remember mine, and she's been dead twenty years.
    >>  ............................................
    pt  Cada uma lembra quem disse. Eu lembro da minha, e ela morreu há vinte anos.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, the way the women here always have. Good day to you.
    >>  ............................................
    pt  Então me viro sozinha, como as mulheres daqui sempre fizeram. Bom dia.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Every one of them alone, if that's the attitude on offer.
    >>  ............................................
    pt  Fazem. Cada uma sozinha, se é essa a atitude oferecida.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said that. Good day.
    >>  ............................................
    pt  E cada uma lembra quem disse isso. Bom dia.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage this conversation the same way. Alone.
    >>  ............................................
    pt  Então eu lido com esta conversa do mesmo jeito. Sozinha.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Every one of them alone, if that's the attitude on offer.
    >>  ............................................
    pt  Fazem. Cada uma sozinha, se é essa a atitude oferecida.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said that. Good day.
    >>  ............................................
    pt  E cada uma lembra quem disse isso. Bom dia.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage this conversation the same way. Alone.
    >>  ............................................
    pt  Então eu lido com esta conversa do mesmo jeito. Sozinha.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. From you, though — I'd expected better, and that's the part that stings.
    >>  ............................................
    pt  Fazem. De você, porém — eu esperava melhor, e é essa parte que dói.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one of them remembers who said that to them. I'll remember it was you.
    >>  ............................................
    pt  Cada uma lembra quem disse isso a ela. Eu vou lembrar que foi você.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, which is not what I wanted from this conversation.
    >>  ............................................
    pt  Então eu me viro sozinha, que não é o que eu queria desta conversa.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. From you, though — I'd expected better, and that's the part that stings.
    >>  ............................................
    pt  Fazem. De você, porém — eu esperava melhor, e é essa parte que dói.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one of them remembers who said that to them. I'll remember it was you.
    >>  ............................................
    pt  Cada uma lembra quem disse isso a ela. Eu vou lembrar que foi você.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, which is not what I wanted from this conversation.
    >>  ............................................
    pt  Então eu me viro sozinha, que não é o que eu queria desta conversa.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. From you, though — I'd expected better, and that's the part that stings.
    >>  ............................................
    pt  Fazem. De você, porém — eu esperava melhor, e é essa parte que dói.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one of them remembers who said that to them. I'll remember it was you.
    >>  ............................................
    pt  Cada uma lembra quem disse isso a ela. Eu vou lembrar que foi você.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, which is not what I wanted from this conversation.
    >>  ............................................
    pt  Então eu me viro sozinha, que não é o que eu queria desta conversa.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.dismissed/1
    en  ...They do. Alone, mostly. I'd hoped this once it might be different.
    >>  ............................................
    pt  ...Fazem. Sozinhas, principalmente. Eu esperava que desta vez fosse diferente.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one of them remembers who said it. I wish I could unhear you.
    >>  ............................................
    pt  Cada uma lembra quem disse. Queria poder desouvir você.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone. I only wanted somebody to say it sounded hard.
    >>  ............................................
    pt  Então me viro sozinha. Eu só queria que alguém dissesse que parece difícil.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Every one of them alone, if that's the attitude on offer.
    >>  ............................................
    pt  Fazem. Cada uma sozinha, se é essa a atitude oferecida.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said that. Good day.
    >>  ............................................
    pt  E cada uma lembra quem disse isso. Bom dia.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage this conversation the same way. Alone.
    >>  ............................................
    pt  Então eu lido com esta conversa do mesmo jeito. Sozinha.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Every one of them alone, if that's the attitude on offer.
    >>  ............................................
    pt  Fazem. Cada uma sozinha, se é essa a atitude oferecida.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said that. Good day.
    >>  ............................................
    pt  E cada uma lembra quem disse isso. Bom dia.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage this conversation the same way. Alone.
    >>  ............................................
    pt  Então eu lido com esta conversa do mesmo jeito. Sozinha.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Alone.
    >>  ............................................
    pt  Fazem. Sozinhas.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.dismissed/2
    en  And they remember who said it.
    >>  ............................................
    pt  E lembram quem disse.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone.
    >>  ............................................
    pt  Então me viro sozinha.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. I've watched three generations of them do it alone, and it never got easier.
    >>  ............................................
    pt  Fazem. Vi três gerações fazerem sozinhas, e nunca ficou mais fácil.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one remembers who said it. I remember mine, and she's been dead twenty years.
    >>  ............................................
    pt  Cada uma lembra quem disse. Eu lembro da minha, e ela morreu há vinte anos.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, the way the women here always have. Good day to you.
    >>  ............................................
    pt  Então me viro sozinha, como as mulheres daqui sempre fizeram. Bom dia.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Alone.
    >>  ............................................
    pt  Fazem. Sozinhas.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.dismissed/2
    en  And they remember who said it.
    >>  ............................................
    pt  E lembram quem disse.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone.
    >>  ............................................
    pt  Então me viro sozinha.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. I've watched three generations of them do it alone, and it never got easier.
    >>  ............................................
    pt  Fazem. Vi três gerações fazerem sozinhas, e nunca ficou mais fácil.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one remembers who said it. I remember mine, and she's been dead twenty years.
    >>  ............................................
    pt  Cada uma lembra quem disse. Eu lembro da minha, e ela morreu há vinte anos.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, the way the women here always have. Good day to you.
    >>  ............................................
    pt  Então me viro sozinha, como as mulheres daqui sempre fizeram. Bom dia.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do! Alone, mostly, and now I know exactly why. Thank you for the demonstration.
    >>  ............................................
    pt  Fazem! Sozinhas, principalmente, e agora sei exatamente por quê. Obrigada pela demonstração.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said it. You've joined a very short list.
    >>  ............................................
    pt  E cada uma lembra quem disse. Você entrou numa lista bem curta.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage the conversation alone as well. I'm getting practice.
    >>  ............................................
    pt  Então eu lido com a conversa sozinha também. Estou treinando.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do! Alone, mostly, and now I know exactly why. Thank you for the demonstration.
    >>  ............................................
    pt  Fazem! Sozinhas, principalmente, e agora sei exatamente por quê. Obrigada pela demonstração.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said it. You've joined a very short list.
    >>  ............................................
    pt  E cada uma lembra quem disse. Você entrou numa lista bem curta.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage the conversation alone as well. I'm getting practice.
    >>  ............................................
    pt  Então eu lido com a conversa sozinha também. Estou treinando.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. I've watched three generations of them do it alone, and it never got easier.
    >>  ............................................
    pt  Fazem. Vi três gerações fazerem sozinhas, e nunca ficou mais fácil.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one remembers who said it. I remember mine, and she's been dead twenty years.
    >>  ............................................
    pt  Cada uma lembra quem disse. Eu lembro da minha, e ela morreu há vinte anos.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone, the way the women here always have. Good day to you.
    >>  ............................................
    pt  Então me viro sozinha, como as mulheres daqui sempre fizeram. Bom dia.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.dismissed/1
    en  ...They do. Alone, mostly. I'd hoped this once it might be different.
    >>  ............................................
    pt  ...Fazem. Sozinhas, principalmente. Eu esperava que desta vez fosse diferente.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.dismissed/2
    en  Every one of them remembers who said it. I wish I could unhear you.
    >>  ............................................
    pt  Cada uma lembra quem disse. Queria poder desouvir você.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone. I only wanted somebody to say it sounded hard.
    >>  ............................................
    pt  Então me viro sozinha. Eu só queria que alguém dissesse que parece difícil.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do. Alone.
    >>  ............................................
    pt  Fazem. Sozinhas.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.dismissed/2
    en  And they remember who said it.
    >>  ............................................
    pt  E lembram quem disse.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage alone.
    >>  ............................................
    pt  Então me viro sozinha.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do! Alone, mostly, and now I know exactly why. Thank you for the demonstration.
    >>  ............................................
    pt  Fazem! Sozinhas, principalmente, e agora sei exatamente por quê. Obrigada pela demonstração.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said it. You've joined a very short list.
    >>  ............................................
    pt  E cada uma lembra quem disse. Você entrou numa lista bem curta.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage the conversation alone as well. I'm getting practice.
    >>  ............................................
    pt  Então eu lido com a conversa sozinha também. Estou treinando.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.dismissed/1
    en  They do! Alone, mostly, and now I know exactly why. Thank you for the demonstration.
    >>  ............................................
    pt  Fazem! Sozinhas, principalmente, e agora sei exatamente por quê. Obrigada pela demonstração.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.dismissed/2
    en  And every one of them remembers who said it. You've joined a very short list.
    >>  ............................................
    pt  E cada uma lembra quem disse. Você entrou numa lista bem curta.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting.dismissed/3
    en  Then I'll manage the conversation alone as well. I'm getting practice.
    >>  ............................................
    pt  Então eu lido com a conversa sozinha também. Estou treinando.
    >>  ............................................
```

</details>


### Button `back` — "I'll leave you in peace."

*stance family `exit` · tone `gentle` · outcome `conversation_ended` · answers the beat(s) `noticed.expecting.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.expecting.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.expecting.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.expecting.respond.back   [24 chars]
    en  I'll leave you in peace.
    >>  ............................................
    pt  Deixo você em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting.back
WHO    VILLAGER — what the player reads after pressing "I'll leave you in peace."
       spoken on: conversations.topic.noticed.expecting.respond, button `back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.back`: the villager accepts. Subject `noticed.pregnancy`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting.back/1   [12 chars]
    en  Kind of you.
    >>  ............................................
    pt  Gentil da sua parte.
    >>  ............................................
  dialogue.conversations.noticed.expecting.back/2   [10 chars]
    en  Quite, do.
    >>  ............................................
    pt  Exato, vá.
    >>  ............................................
  dialogue.conversations.noticed.expecting.back/3   [19 chars]
    en  Get on, then, %1$s.
    >>  ............................................
    pt  Então vá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.fine.admitted.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.fine.respond` / `blunt`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.fine.blunt` — e.g. "...No. I'm not. Nobody's ever just said it, so I've been getting away with 'fine' for weeks."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.admitted.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.fine.admitted.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.fine.admitted.followup   [23 chars]
    en  So. Not fine. What now?
    >>  ............................................
    pt  Então. Não estou bem. E agora?
    >>  ............................................
```


### Button `how_long` — "How long has it been like that?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.fine.not_fine` · offered only once the villager has actually said `admission:not_fine`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.admitted.how_long` — accepted phrasings: "how long has it been like that"; "since when"; "how long has this been going on"
  - the message must contain one of: `since`, `been`, `going`
  - scored words: `since`(1.5), `been`(1.0), `going`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.admitted.followup.how_long
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.admitted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.admitted.followup.how_long   [31 chars]
    en  How long has it been like that?
    >>  ............................................
    pt  Faz quanto tempo que está assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +2  _(recorded under topic `noticed.fine.admitted.how_long`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.admitted.how_long
WHO    VILLAGER — what the player reads after pressing "How long has it been like that?"
       spoken on: conversations.topic.noticed.fine.admitted.followup, button `how_long`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.admitted.how_long`: the villager discloses. Subject `noticed.wellbeing`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.admitted.how_long/1   [48 chars]
    en  Since about the spring, if I'm honest. It crept.
    >>  ............................................
    pt  Desde a primavera, se eu for honesto. Foi chegando devagar.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.how_long/2   [70 chars]
    en  Long enough that 'fine' stopped being a lie and started being a habit.
    >>  ............................................
    pt  Tempo bastante pra 'bem' deixar de ser mentira e virar hábito.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.how_long/3   [43 chars]
    en  Longer than I'd care to say stood up, %1$s.
    >>  ............................................
    pt  Mais tempo do que eu diria em pé, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept, %1$s, and I let it.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando, %1$s, e eu deixei.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. I've known the date for a while and I've been careful not to say it.
    >>  ............................................
    pt  Primavera. Sei a data faz um tempo e tomei cuidado pra não dizer.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Putting a number on it makes it real, which is why I hadn't.
    >>  ............................................
    pt  Seis meses. Pôr um número torna real, e por isso eu não tinha posto.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept, the way these things do.
    >>  ............................................
    pt  Desde a primavera. Foi chegando, do jeito que essas coisas chegam.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It came on slowly and it'll go the same way, most likely.
    >>  ............................................
    pt  Primavera. Veio devagar e vai embora do mesmo jeito, provavelmente.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Long enough to be a season rather than a bad week.
    >>  ............................................
    pt  Seis meses. Tempo o bastante pra ser uma estação e não uma semana ruim.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive; it accumulated.
    >>  ............................................
    pt  Primavera. Não chegou; acumulou.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. I've only just put a date on it.
    >>  ............................................
    pt  Uns seis meses. Só agora eu pus uma data.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive; it accumulated.
    >>  ............................................
    pt  Primavera. Não chegou; acumulou.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. I've only just put a date on it.
    >>  ............................................
    pt  Uns seis meses. Só agora eu pus uma data.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest, %1$s. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto, %1$s. Foi chegando.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. I've not said that to anybody, so you're the first to have the date.
    >>  ............................................
    pt  Primavera. Não disse isso a ninguém, então você é o primeiro a ter a data.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. Saying it out loud to you is the first time I've counted it.
    >>  ............................................
    pt  Uns seis meses. Dizer em voz alta pra você é a primeira vez que eu conto.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest, %1$s. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto, %1$s. Foi chegando.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. I've not said that to anybody, so you're the first to have the date.
    >>  ............................................
    pt  Primavera. Não disse isso a ninguém, então você é o primeiro a ter a data.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. Saying it out loud to you is the first time I've counted it.
    >>  ............................................
    pt  Uns seis meses. Dizer em voz alta pra você é a primeira vez que eu conto.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest, %1$s. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto, %1$s. Foi chegando.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. I've not said that to anybody, so you're the first to have the date.
    >>  ............................................
    pt  Primavera. Não disse isso a ninguém, então você é o primeiro a ter a data.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. Saying it out loud to you is the first time I've counted it.
    >>  ............................................
    pt  Uns seis meses. Dizer em voz alta pra você é a primeira vez que eu conto.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept, %1$s, and I let it.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando, %1$s, e eu deixei.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. I've known the date for a while and I've been careful not to say it.
    >>  ............................................
    pt  Primavera. Sei a data faz um tempo e tomei cuidado pra não dizer.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Putting a number on it makes it real, which is why I hadn't.
    >>  ............................................
    pt  Seis meses. Pôr um número torna real, e por isso eu não tinha posto.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive; it accumulated.
    >>  ............................................
    pt  Primavera. Não chegou; acumulou.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. I've only just put a date on it.
    >>  ............................................
    pt  Uns seis meses. Só agora eu pus uma data.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive; it accumulated.
    >>  ............................................
    pt  Primavera. Não chegou; acumulou.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  About six months. I've only just put a date on it.
    >>  ............................................
    pt  Uns seis meses. Só agora eu pus uma data.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept.
    >>  ............................................
    pt  Desde a primavera. Foi chegando.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It accumulated rather than arrived.
    >>  ............................................
    pt  Primavera. Acumulou em vez de chegar.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Thereabouts.
    >>  ............................................
    pt  Seis meses. Por aí.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept, the way these things do.
    >>  ............................................
    pt  Desde a primavera. Foi chegando, do jeito que essas coisas chegam.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It came on slowly and it'll go the same way, most likely.
    >>  ............................................
    pt  Primavera. Veio devagar e vai embora do mesmo jeito, provavelmente.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Long enough to be a season rather than a bad week.
    >>  ............................................
    pt  Seis meses. Tempo o bastante pra ser uma estação e não uma semana ruim.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept.
    >>  ............................................
    pt  Desde a primavera. Foi chegando.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It accumulated rather than arrived.
    >>  ............................................
    pt  Primavera. Acumulou em vez de chegar.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Thereabouts.
    >>  ............................................
    pt  Seis meses. Por aí.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept, the way these things do.
    >>  ............................................
    pt  Desde a primavera. Foi chegando, do jeito que essas coisas chegam.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It came on slowly and it'll go the same way, most likely.
    >>  ............................................
    pt  Primavera. Veio devagar e vai embora do mesmo jeito, provavelmente.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Long enough to be a season rather than a bad week.
    >>  ............................................
    pt  Seis meses. Tempo o bastante pra ser uma estação e não uma semana ruim.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest! It crept. Very underhand of it.
    >>  ............................................
    pt  Desde a primavera, se for honesto! Foi chegando. Muito sorrateiro da parte dela.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive, it accumulated, which is a much sneakier way to go about things.
    >>  ............................................
    pt  Primavera. Não chegou, acumulou, que é um jeito bem mais dissimulado de agir.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months, give or take. I've only just done the arithmetic and I'd rather not have.
    >>  ............................................
    pt  Seis meses, mais ou menos. Só agora eu fiz a conta e preferia não ter feito.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest! It crept. Very underhand of it.
    >>  ............................................
    pt  Desde a primavera, se for honesto! Foi chegando. Muito sorrateiro da parte dela.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive, it accumulated, which is a much sneakier way to go about things.
    >>  ............................................
    pt  Primavera. Não chegou, acumulou, que é um jeito bem mais dissimulado de agir.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months, give or take. I've only just done the arithmetic and I'd rather not have.
    >>  ............................................
    pt  Seis meses, mais ou menos. Só agora eu fiz a conta e preferia não ter feito.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept, the way these things do.
    >>  ............................................
    pt  Desde a primavera. Foi chegando, do jeito que essas coisas chegam.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It came on slowly and it'll go the same way, most likely.
    >>  ............................................
    pt  Primavera. Veio devagar e vai embora do mesmo jeito, provavelmente.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Long enough to be a season rather than a bad week.
    >>  ............................................
    pt  Seis meses. Tempo o bastante pra ser uma estação e não uma semana ruim.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest. It crept, %1$s, and I let it.
    >>  ............................................
    pt  Desde a primavera, se for honesto. Foi chegando, %1$s, e eu deixei.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. I've known the date for a while and I've been careful not to say it.
    >>  ............................................
    pt  Primavera. Sei a data faz um tempo e tomei cuidado pra não dizer.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Putting a number on it makes it real, which is why I hadn't.
    >>  ............................................
    pt  Seis meses. Pôr um número torna real, e por isso eu não tinha posto.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring. It crept.
    >>  ............................................
    pt  Desde a primavera. Foi chegando.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It accumulated rather than arrived.
    >>  ............................................
    pt  Primavera. Acumulou em vez de chegar.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months. Thereabouts.
    >>  ............................................
    pt  Seis meses. Por aí.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest! It crept. Very underhand of it.
    >>  ............................................
    pt  Desde a primavera, se for honesto! Foi chegando. Muito sorrateiro da parte dela.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive, it accumulated, which is a much sneakier way to go about things.
    >>  ............................................
    pt  Primavera. Não chegou, acumulou, que é um jeito bem mais dissimulado de agir.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months, give or take. I've only just done the arithmetic and I'd rather not have.
    >>  ............................................
    pt  Seis meses, mais ou menos. Só agora eu fiz a conta e preferia não ter feito.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.admitted.how_long/1
    en  Since about the spring, if I'm honest! It crept. Very underhand of it.
    >>  ............................................
    pt  Desde a primavera, se for honesto! Foi chegando. Muito sorrateiro da parte dela.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.admitted.how_long/2
    en  Spring. It didn't arrive, it accumulated, which is a much sneakier way to go about things.
    >>  ............................................
    pt  Primavera. Não chegou, acumulou, que é um jeito bem mais dissimulado de agir.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.admitted.how_long/3
    en  Six months, give or take. I've only just done the arithmetic and I'd rather not have.
    >>  ............................................
    pt  Seis meses, mais ou menos. Só agora eu fiz a conta e preferia não ter feito.
    >>  ............................................
```

</details>


### Button `offer_help` — "Tell me what would help."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.fine.not_fine` · offered only once the villager has actually said `admission:not_fine`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.admitted.offer_help` — accepted phrasings: "tell me what would help"; "what do you need"; "what would help"
  - the message must contain one of: `help`, `need`
  - scored words: `help`(1.5), `need`(1.0), `would`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.admitted.followup.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.admitted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.admitted.followup.offer_help   [24 chars]
    en  Tell me what would help.
    >>  ............................................
    pt  Me diga o que ajudaria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.fine.admitted.offer_help`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +3  _(recorded under topic `noticed.fine.admitted.offer_help`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.admitted.offer_help
WHO    VILLAGER — what the player reads after pressing "Tell me what would help."
       spoken on: conversations.topic.noticed.fine.admitted.followup, button `offer_help`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.admitted.offer_help`: the villager accepts. Subject `noticed.wellbeing`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.admitted.offer_help/1   [67 chars]
    en  ...Nobody asks it like that. Give me a day and I'll have an answer.
    >>  ............................................
    pt  ...Ninguém pergunta desse jeito. Me dê um dia e eu terei uma resposta.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.offer_help/2   [66 chars]
    en  Nothing dramatic. Somebody asking twice, mostly. You've done that.
    >>  ............................................
    pt  Nada dramático. Alguém perguntando duas vezes, principalmente. Você fez isso.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.offer_help/3   [58 chars]
    en  Company, and not being handled. You've managed both, %1$s.
    >>  ............................................
    pt  Companhia, e não ser tratado como problema. Você conseguiu os dois, %1$s.
    >>  ............................................
```


### Button `be_around` — "I'll be around."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.fine.not_fine`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.admitted.be_around` — accepted phrasings: "i will be around"; "i am here"; "i will be nearby"
  - the message must contain one of: `around`, `here`, `nearby`
  - scored words: `around`(1.5), `here`(1.0), `nearby`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.admitted.followup.be_around
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.admitted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.admitted.followup.be_around   [15 chars]
    en  I'll be around.
    >>  ............................................
    pt  Vou estar por perto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.admitted.be_around`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +3  _(recorded under topic `noticed.fine.admitted.be_around`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.admitted.be_around
WHO    VILLAGER — what the player reads after pressing "I'll be around."
       spoken on: conversations.topic.noticed.fine.admitted.followup, button `be_around`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.admitted.be_around`: the villager accepts. Subject `noticed.wellbeing`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.admitted.be_around/1   [45 chars]
    en  That's the useful kind of promise. Thank you.
    >>  ............................................
    pt  Esse é o tipo útil de promessa. Obrigado.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.be_around/2   [47 chars]
    en  Around. Aye. That'll do more than advice would.
    >>  ............................................
    pt  Por perto. É. Isso faz mais que conselho faria.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.be_around/3   [42 chars]
    en  Good. I'll hold you to being nearby, %1$s.
    >>  ............................................
    pt  Bom. Vou cobrar essa proximidade, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll not make a thing of it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.fine.not_fine` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.admitted.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.admitted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.admitted.followup.leave   [28 chars]
    en  I'll not make a thing of it.
    >>  ............................................
    pt  Não vou fazer disso um caso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.admitted.leave
WHO    VILLAGER — what the player reads after pressing "I'll not make a thing of it."
       spoken on: conversations.topic.noticed.fine.admitted.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.admitted.leave`: the villager accepts. Subject `noticed.wellbeing`, polarity `mixed`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.admitted.leave/1   [43 chars]
    en  Thank you. That's the right amount of fuss.
    >>  ............................................
    pt  Obrigado. É a quantidade certa de alarde.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.leave/2   [47 chars]
    en  So I've found. Don't. But don't forget, either.
    >>  ............................................
    pt  Foi o que eu vi. Não faça. Mas também não esqueça.
    >>  ............................................
  dialogue.conversations.noticed.fine.admitted.leave/3   [16 chars]
    en  Safe home, %1$s.
    >>  ............................................
    pt  Volte bem, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.fine.closed.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.fine.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.fine.dismiss` — e.g. "It's the honest one, though."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.closed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.fine.closed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.fine.closed.followup   [18 chars]
    en  Fine is an answer.
    >>  ............................................
    pt  Bem é uma resposta.
    >>  ............................................
```


### Button `concede` — "You're right. Poor question."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.fine.answer_defended`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.closed.concede` — accepted phrasings: "you are right, poor question"; "fair enough, poor question"; "that was a poor question"
  - the message must contain one of: `poor`, `question`, `fair`
  - scored words: `poor`(1.5), `question`(1.2), `fair`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.closed.followup.concede
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.closed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.closed.followup.concede   [28 chars]
    en  You're right. Poor question.
    >>  ............................................
    pt  Você tem razão. Pergunta ruim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension -2  _(recorded under topic `noticed.fine.closed.concede`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.closed.concede
WHO    VILLAGER — what the player reads after pressing "You're right. Poor question."
       spoken on: conversations.topic.noticed.fine.closed.followup, button `concede`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.closed.concede`: the villager accepts. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.closed.concede/1   [49 chars]
    en  It wasn't a bad question. It just had one answer.
    >>  ............................................
    pt  Não foi uma pergunta ruim. Só tinha uma resposta.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.concede/2   [61 chars]
    en  Ha. Now we're both being reasonable and it's very unsettling.
    >>  ............................................
    pt  Ha. Agora nós dois estamos sendo razoáveis e isso é bem perturbador.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.concede/3   [59 chars]
    en  ...Aye, well. Ask me a better one and I'll do better, %1$s.
    >>  ............................................
    pt  ...É, bom. Me faça uma melhor e eu respondo melhor, %1$s.
    >>  ............................................
```


### Button `explain_intent` — "I only meant I'd listen, if there were more."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.fine.answer_defended`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.closed.explain_intent` — accepted phrasings: "i only meant i would listen"; "i was only offering to listen"; "i just meant i would listen"
  - the message must contain one of: `listen`, `offering`, `only`
  - scored words: `listen`(1.5), `offering`(1.2), `only`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.closed.followup.explain_intent
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.closed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.closed.followup.explain_intent   [44 chars]
    en  I only meant I'd listen, if there were more.
    >>  ............................................
    pt  Eu só quis dizer que eu ouviria, se tivesse mais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.closed.explain_intent`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, trust +2  _(recorded under topic `noticed.fine.closed.explain_intent`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.closed.explain_intent
WHO    VILLAGER — what the player reads after pressing "I only meant I'd listen, if there were more."
       spoken on: conversations.topic.noticed.fine.closed.followup, button `explain_intent`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.closed.explain_intent`: the villager qualifys. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.closed.explain_intent/1   [37 chars]
    en  ...Oh. That's different, then. Noted.
    >>  ............................................
    pt  ...Ah. Então é diferente. Anotado.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.explain_intent/2   [56 chars]
    en  Then I've been short with you for no reason. It happens.
    >>  ............................................
    pt  Então fui seco com você sem motivo. Acontece.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.explain_intent/3   [52 chars]
    en  Listening's not nothing. I'll bear it in mind, %1$s.
    >>  ............................................
    pt  Ouvir não é pouca coisa. Vou ter isso em mente, %1$s.
    >>  ............................................
```


### Button `accept_it` — "Understood."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.fine.answer_defended`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.closed.accept_it` — accepted phrasings: "understood"; "noted"; "alright then"
  - the message must contain one of: `noted`, `understood`
  - scored words: `noted`(1.5), `understood`(1.2), `alright`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.closed.followup.accept_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.closed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.closed.followup.accept_it   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `noticed.fine.closed.accept_it`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.closed.accept_it
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.topic.noticed.fine.closed.followup, button `accept_it`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.closed.accept_it`: the villager accepts. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.closed.accept_it/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.accept_it/2   [26 chars]
    en  Right. No harm done, %1$s.
    >>  ............................................
    pt  Certo. Sem ressentimento, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.accept_it/3   [22 chars]
    en  Mm. That's that, then.
    >>  ............................................
    pt  Mm. Então está resolvido.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.fine.answer_defended` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.closed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.closed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.closed.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.closed.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.topic.noticed.fine.closed.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.closed.leave`: the villager accepts. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.closed.leave/1   [20 chars]
    en  Just so. Off you go.
    >>  ............................................
    pt  Pois é. Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.leave/2   [19 chars]
    en  I'll see you about.
    >>  ............................................
    pt  A gente se vê por aí.
    >>  ............................................
  dialogue.conversations.noticed.fine.closed.leave/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuida, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.fine.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.fine.respond` / `glad`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.fine.glad` — e.g. "It isn't, is it. Steady's the best most weeks manage."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.fine.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.fine.followup   [19 chars]
    en  Steady it is, then.
    >>  ............................................
    pt  Então é estável mesmo.
    >>  ............................................
```


### Button `ask_what_keeps` — "What keeps a week steady round here?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.fine.steady_valued` · offered only once the villager has actually said `state:steady`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.followup.ask_what_keeps` — accepted phrasings: "what keeps a week steady"; "what keeps it steady round here"; "how do the weeks stay ordinary"
  - the message must contain one of: `keeps`, `steady`, `ordinary`
  - scored words: `keeps`(1.5), `steady`(1.2), `ordinary`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.followup.ask_what_keeps
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.followup.ask_what_keeps   [36 chars]
    en  What keeps a week steady round here?
    >>  ............................................
    pt  O que mantém uma semana estável por aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `noticed.fine.followup.ask_what_keeps`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.followup.ask_what_keeps
WHO    VILLAGER — what the player reads after pressing "What keeps a week steady round here?"
       spoken on: conversations.topic.noticed.fine.followup, button `ask_what_keeps`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.followup.ask_what_keeps`: the villager explains. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.followup.ask_what_keeps/1   [56 chars]
    en  Bread on time and nobody dying. That's the whole recipe.
    >>  ............................................
    pt  Pão na hora e ninguém morrendo. É a receita inteira.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.ask_what_keeps/2   [66 chars]
    en  Routine, mostly. Say it out loud and it sounds grim, but it isn't.
    >>  ............................................
    pt  Rotina, principalmente. Dito em voz alta soa sombrio, mas não é.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.ask_what_keeps/3   [59 chars]
    en  Small things done properly, %1$s. Nothing you'd write down.
    >>  ............................................
    pt  Coisas pequenas feitas direito, %1$s. Nada que se anote.
    >>  ............................................
```


### Button `wish_it_holds` — "Long may it hold."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.fine.steady_valued`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.followup.wish_it_holds` — accepted phrasings: "long may it hold"; "i hope it holds"; "may it hold"
  - the message must contain one of: `hold`, `holds`, `long`
  - scored words: `hold`(1.5), `holds`(1.5), `long`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.followup.wish_it_holds
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.followup.wish_it_holds   [17 chars]
    en  Long may it hold.
    >>  ............................................
    pt  Que dure bastante.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.followup.wish_it_holds`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `noticed.fine.followup.wish_it_holds`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.followup.wish_it_holds
WHO    VILLAGER — what the player reads after pressing "Long may it hold."
       spoken on: conversations.topic.noticed.fine.followup, button `wish_it_holds`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.followup.wish_it_holds`: the villager accepts. Subject `noticed.wellbeing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.followup.wish_it_holds/1   [37 chars]
    en  From your mouth to the weather's ear.
    >>  ............................................
    pt  Que sua boca seja um santo — ou pelo menos o tempo.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.wish_it_holds/2   [38 chars]
    en  It won't. But it's good of you to say.
    >>  ............................................
    pt  Não vai durar. Mas é bom da sua parte dizer.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.wish_it_holds/3   [31 chars]
    en  True enough. Long may it, %1$s.
    >>  ............................................
    pt  Bem verdade. Que dure, %1$s.
    >>  ............................................
```


### Button `share_own` — "I could do with a steady week myself."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.fine.steady_valued`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.followup.share_own` — accepted phrasings: "i could do with a steady week myself"; "i could use a steady week"; "i want one of those myself"
  - the message must contain one of: `myself`, `use`, `could`
  - scored words: `myself`(1.5), `use`(1.2), `could`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.followup.share_own   [37 chars]
    en  I could do with a steady week myself.
    >>  ............................................
    pt  Eu bem que queria uma semana estável.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `noticed.fine.followup.share_own`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.followup.share_own
WHO    VILLAGER — what the player reads after pressing "I could do with a steady week myself."
       spoken on: conversations.topic.noticed.fine.followup, button `share_own`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.followup.share_own`: the villager invites. Subject `noticed.wellbeing`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.followup.share_own/1   [46 chars]
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Dá pra ver, se não se importa que eu diga.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.share_own/2   [51 chars]
    en  Then stand here a while. It's catching, apparently.
    >>  ............................................
    pt  Então fique aqui um pouco. Pelo visto pega.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.share_own/3   [52 chars]
    en  Ha. You've had the interesting kind, have you, %1$s?
    >>  ............................................
    pt  Ha. A sua tem sido do tipo interessante, é, %1$s?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying, %1$s. I'd not have mentioned it otherwise.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga, %1$s. Senão eu não teria mencionado.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. I know how it feels to be asked and I know how it feels not to be.
    >>  ............................................
    pt  Você parece. Eu sei como é ser perguntado e sei como é não ser.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Sit down. I'm not going anywhere.
    >>  ............................................
    pt  Você parece. Sente-se. Eu não vou a lugar nenhum.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying. No hurry to talk about it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga. Sem pressa de falar sobre isso.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. It'll pass, and until it does you're welcome to sit here.
    >>  ............................................
    pt  Você parece. Vai passar, e até lá você pode ficar sentado aqui.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Take a seat and say nothing at all if you'd rather.
    >>  ............................................
    pt  Você parece. Sente e não diga nada, se preferir.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, and I'll say so since we're being honest.
    >>  ............................................
    pt  Você parece, e eu digo já que estamos sendo honestos.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's not an insult; it's a fact and I'd want telling.
    >>  ............................................
    pt  Você parece. Não é insulto; é fato e eu ia querer ser avisado.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, and I'll say so since we're being honest.
    >>  ............................................
    pt  Você parece, e eu digo já que estamos sendo honestos.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's not an insult; it's a fact and I'd want telling.
    >>  ............................................
    pt  Você parece. Não é insulto; é fato e eu ia querer ser avisado.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying, %1$s. Sit down a minute.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga, %1$s. Sente um minuto.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. And I'd rather say it than let you carry it about all afternoon.
    >>  ............................................
    pt  Você parece. E prefiro dizer a te deixar carregar isso a tarde toda.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Come and sit — I'll not ask anything, I'll just be here.
    >>  ............................................
    pt  Você parece. Venha sentar — eu não vou perguntar nada, só vou estar aqui.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying, %1$s. Sit down a minute.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga, %1$s. Sente um minuto.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. And I'd rather say it than let you carry it about all afternoon.
    >>  ............................................
    pt  Você parece. E prefiro dizer a te deixar carregar isso a tarde toda.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Come and sit — I'll not ask anything, I'll just be here.
    >>  ............................................
    pt  Você parece. Venha sentar — eu não vou perguntar nada, só vou estar aqui.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying, %1$s. Sit down a minute.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga, %1$s. Sente um minuto.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. And I'd rather say it than let you carry it about all afternoon.
    >>  ............................................
    pt  Você parece. E prefiro dizer a te deixar carregar isso a tarde toda.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Come and sit — I'll not ask anything, I'll just be here.
    >>  ............................................
    pt  Você parece. Venha sentar — eu não vou perguntar nada, só vou estar aqui.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying, %1$s. I'd not have mentioned it otherwise.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga, %1$s. Senão eu não teria mencionado.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. I know how it feels to be asked and I know how it feels not to be.
    >>  ............................................
    pt  Você parece. Eu sei como é ser perguntado e sei como é não ser.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Sit down. I'm not going anywhere.
    >>  ............................................
    pt  Você parece. Sente-se. Eu não vou a lugar nenhum.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, and I'll say so since we're being honest.
    >>  ............................................
    pt  Você parece, e eu digo já que estamos sendo honestos.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's not an insult; it's a fact and I'd want telling.
    >>  ............................................
    pt  Você parece. Não é insulto; é fato e eu ia querer ser avisado.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, and I'll say so since we're being honest.
    >>  ............................................
    pt  Você parece, e eu digo já que estamos sendo honestos.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's not an insult; it's a fact and I'd want telling.
    >>  ............................................
    pt  Você parece. Não é insulto; é fato e eu ia querer ser avisado.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do.
    >>  ............................................
    pt  Você parece.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's all I'll say.
    >>  ............................................
    pt  Você parece. É tudo que eu digo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying. No hurry to talk about it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga. Sem pressa de falar sobre isso.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. It'll pass, and until it does you're welcome to sit here.
    >>  ............................................
    pt  Você parece. Vai passar, e até lá você pode ficar sentado aqui.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Take a seat and say nothing at all if you'd rather.
    >>  ............................................
    pt  Você parece. Sente e não diga nada, se preferir.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do.
    >>  ............................................
    pt  Você parece.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's all I'll say.
    >>  ............................................
    pt  Você parece. É tudo que eu digo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying. No hurry to talk about it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga. Sem pressa de falar sobre isso.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. It'll pass, and until it does you're welcome to sit here.
    >>  ............................................
    pt  Você parece. Vai passar, e até lá você pode ficar sentado aqui.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Take a seat and say nothing at all if you'd rather.
    >>  ............................................
    pt  Você parece. Sente e não diga nada, se preferir.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying! Which is a terrible thing to say and I've said it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga! O que é horrível de dizer e eu disse.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, rather. I'd not mention it if we weren't being honest with each other.
    >>  ............................................
    pt  Você parece, sim. Eu não mencionaria se a gente não estivesse sendo honesto.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. There. Now we've both been rude and we can get on.
    >>  ............................................
    pt  Você parece. Pronto. Agora nós dois fomos grosseiros e podemos seguir.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying! Which is a terrible thing to say and I've said it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga! O que é horrível de dizer e eu disse.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, rather. I'd not mention it if we weren't being honest with each other.
    >>  ............................................
    pt  Você parece, sim. Eu não mencionaria se a gente não estivesse sendo honesto.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. There. Now we've both been rude and we can get on.
    >>  ............................................
    pt  Você parece. Pronto. Agora nós dois fomos grosseiros e podemos seguir.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying. No hurry to talk about it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga. Sem pressa de falar sobre isso.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. It'll pass, and until it does you're welcome to sit here.
    >>  ............................................
    pt  Você parece. Vai passar, e até lá você pode ficar sentado aqui.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Take a seat and say nothing at all if you'd rather.
    >>  ............................................
    pt  Você parece. Sente e não diga nada, se preferir.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying, %1$s. I'd not have mentioned it otherwise.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga, %1$s. Senão eu não teria mencionado.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do. I know how it feels to be asked and I know how it feels not to be.
    >>  ............................................
    pt  Você parece. Eu sei como é ser perguntado e sei como é não ser.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. Sit down. I'm not going anywhere.
    >>  ............................................
    pt  Você parece. Sente-se. Eu não vou a lugar nenhum.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do.
    >>  ............................................
    pt  Você parece.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. That's all I'll say.
    >>  ............................................
    pt  Você parece. É tudo que eu digo.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying! Which is a terrible thing to say and I've said it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga! O que é horrível de dizer e eu disse.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, rather. I'd not mention it if we weren't being honest with each other.
    >>  ............................................
    pt  Você parece, sim. Eu não mencionaria se a gente não estivesse sendo honesto.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. There. Now we've both been rude and we can get on.
    >>  ............................................
    pt  Você parece. Pronto. Agora nós dois fomos grosseiros e podemos seguir.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.followup.share_own/1
    en  You look like it, if you don't mind me saying! Which is a terrible thing to say and I've said it.
    >>  ............................................
    pt  Você parece, se não se importa que eu diga! O que é horrível de dizer e eu disse.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.followup.share_own/2
    en  You do, rather. I'd not mention it if we weren't being honest with each other.
    >>  ............................................
    pt  Você parece, sim. Eu não mencionaria se a gente não estivesse sendo honesto.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.followup.share_own/3
    en  You look it. There. Now we've both been rude and we can get on.
    >>  ............................................
    pt  Você parece. Pronto. Agora nós dois fomos grosseiros e podemos seguir.
    >>  ............................................
```

</details>


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.fine.steady_valued` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.topic.noticed.fine.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.followup.leave`: the villager accepts. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.followup.leave/1   [21 chars]
    en  So it is. Off you go.
    >>  ............................................
    pt  É assim mesmo. Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.leave/2   [22 chars]
    en  Take care of yourself.
    >>  ............................................
    pt  Se cuide.
    >>  ............................................
  dialogue.conversations.noticed.fine.followup.leave/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuida, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.fine.opened.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.fine.respond` / `dig`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.fine.dig` — e.g. "...Mostly. There's a thing, but it's small and it'll keep."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.opened.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.fine.opened.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.fine.opened.followup   [28 chars]
    en  It'll keep. It usually does.
    >>  ............................................
    pt  Pode esperar. Geralmente pode.
    >>  ............................................
```


### Button `ask_now` — "Small things get heavy. What is it?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `noticed.fine.small_thing` · offered only once the villager has actually said `concern:unnamed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.opened.ask_now` — accepted phrasings: "small things get heavy"; "what is it"; "tell me what it is"
  - the message must contain one of: `heavy`, `small`
  - scored words: `heavy`(1.5), `small`(1.2), `what`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.opened.followup.ask_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.opened.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.opened.followup.ask_now   [35 chars]
    en  Small things get heavy. What is it?
    >>  ............................................
    pt  Coisa pequena vai pesando. O que é?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1, trust +2  _(recorded under topic `noticed.fine.opened.ask_now`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.opened.ask_now
WHO    VILLAGER — what the player reads after pressing "Small things get heavy. What is it?"
       spoken on: conversations.topic.noticed.fine.opened.followup, button `ask_now`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.opened.ask_now`: the villager deflects. Subject `noticed.wellbeing`, polarity `mixed`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.opened.ask_now/1   [52 chars]
    en  ...It's my brother. That's all you're getting today.
    >>  ............................................
    pt  ...É meu irmão. É só isso que você leva hoje.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.ask_now/2   [63 chars]
    en  Nothing I can put in a sentence yet, %1$s. That's why it keeps.
    >>  ............................................
    pt  Nada que eu consiga colocar numa frase ainda, %1$s. Por isso pode esperar.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.ask_now/3   [48 chars]
    en  You'll hear it eventually. Not stood up, though.
    >>  ............................................
    pt  Você vai ouvir uma hora. Mas não em pé.
    >>  ............................................
```


### Button `offer_later` — "Then tell me when it stops keeping."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.fine.small_thing` · offered only once the villager has actually said `concern:unnamed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.opened.offer_later` — accepted phrasings: "tell me when it stops keeping"; "tell me later then"; "tell me when it will not keep"
  - the message must contain one of: `stops`, `keeping`, `later`
  - scored words: `stops`(1.5), `keeping`(1.5), `later`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.opened.followup.offer_later
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.opened.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.opened.followup.offer_later   [35 chars]
    en  Then tell me when it stops keeping.
    >>  ............................................
    pt  Então me conte quando não puder mais esperar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.opened.offer_later`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, trust +3  _(recorded under topic `noticed.fine.opened.offer_later`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.opened.offer_later
WHO    VILLAGER — what the player reads after pressing "Then tell me when it stops keeping."
       spoken on: conversations.topic.noticed.fine.opened.followup, button `offer_later`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.opened.offer_later`: the villager accepts. Subject `noticed.wellbeing`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.opened.offer_later/1   [60 chars]
    en  ...That's the right way round. Nearly everyone wants it now.
    >>  ............................................
    pt  ...É a ordem certa. Quase todo mundo quer saber agora.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.offer_later/2   [51 chars]
    en  I might. That's more than I'd have said last month.
    >>  ............................................
    pt  Talvez eu conte. Isso já é mais do que eu diria mês passado.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.offer_later/3   [45 chars]
    en  Just so. And I'll remember you offered, %1$s.
    >>  ............................................
    pt  Pois é. E vou lembrar que você se ofereceu, %1$s.
    >>  ............................................
```


### Button `reassure` — "Whatever it is, it isn't nothing."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.fine.small_thing`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.opened.reassure` — accepted phrasings: "whatever it is, it is not nothing"; "it is not nothing"; "that is not trivial"
  - the message must contain one of: `nothing`, `whatever`, `trivial`
  - scored words: `nothing`(1.5), `whatever`(1.2), `trivial`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.opened.followup.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.opened.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.opened.followup.reassure   [33 chars]
    en  Whatever it is, it isn't nothing.
    >>  ............................................
    pt  Seja o que for, não é nada não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.opened.reassure`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +2  _(recorded under topic `noticed.fine.opened.reassure`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.opened.reassure
WHO    VILLAGER — what the player reads after pressing "Whatever it is, it isn't nothing."
       spoken on: conversations.topic.noticed.fine.opened.followup, button `reassure`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.opened.reassure`: the villager accepts. Subject `noticed.wellbeing`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.opened.reassure/1   [56 chars]
    en  ...No. It isn't. Thank you for not making me argue that.
    >>  ............................................
    pt  ...Não. Não é. Obrigado por não me fazer discutir isso.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.reassure/2   [74 chars]
    en  Everyone keeps calling it small. Including me. It helps to hear otherwise.
    >>  ............................................
    pt  Todo mundo insiste que é pequeno. Eu inclusive. Ajuda ouvir o contrário.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.reassure/3   [32 chars]
    en  Hm. Aye. It isn't nothing, %1$s.
    >>  ............................................
    pt  Hm. É. Não é nada não, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let it keep, then."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.fine.small_thing` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.opened.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.opened.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.opened.followup.leave   [23 chars]
    en  I'll let it keep, then.
    >>  ............................................
    pt  Então deixo esperar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.opened.leave
WHO    VILLAGER — what the player reads after pressing "I'll let it keep, then."
       spoken on: conversations.topic.noticed.fine.opened.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.opened.leave`: the villager accepts. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.opened.leave/1   [34 chars]
    en  So I've found. Thank you for that.
    >>  ............................................
    pt  Foi o que eu vi. Obrigado por isso.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.leave/2   [21 chars]
    en  That'll do for today.
    >>  ............................................
    pt  Por hoje está bom.
    >>  ............................................
  dialogue.conversations.noticed.fine.opened.leave/3   [18 chars]
    en  Enough said, %1$s.
    >>  ............................................
    pt  Já foi dito, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.fine.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.fine` — e.g. "Same as ever, thanks for asking. Ups and downs, mostly evens. That's village life."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.fine.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.fine.respond   [23 chars]
    en  Nothing much to report.
    >>  ............................................
    pt  Nada de mais para contar.
    >>  ............................................
```


### Button `glad` — "Steady's not nothing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.fine.open` · offered only once the villager has actually said `state:steady`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.glad` — accepted phrasings: "steady is not nothing"; "steady is something"; "steady will do"
  - the message must contain one of: `steady`, `something`
  - scored words: `steady`(1.5), `something`(1.0), `nothing`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.respond.glad   [21 chars]
    en  Steady's not nothing.
    >>  ............................................
    pt  Estável já é alguma coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `noticed.fine.glad`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.fine.followup`
- …where the player's next choices will be: "What keeps a week steady round here?" | "Long may it hold." | "I could do with a steady week myself." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.glad
WHO    VILLAGER — what the player reads after pressing "Steady's not nothing."
       spoken on: conversations.topic.noticed.fine.respond, button `glad`
       leaves the player on: conversations.topic.noticed.fine.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.steady_valued`: the villager accepts. Subject `noticed.wellbeing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `state:steady` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, self_disclosure, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.glad/1   [53 chars]
    en  It isn't, is it. Steady's the best most weeks manage.
    >>  ............................................
    pt  Não é mesmo, né. Estável é o melhor que a maioria das semanas consegue.
    >>  ............................................
  dialogue.conversations.noticed.fine.glad/2   [41 chars]
    en  Quite. I'll take steady over interesting.
    >>  ............................................
    pt  Exato. Prefiro estável a interessante.
    >>  ............................................
  dialogue.conversations.noticed.fine.glad/3   [37 chars]
    en  Kind of you to count it as something.
    >>  ............................................
    pt  Gentil da sua parte contar isso como algo.
    >>  ............................................
```


### Button `dig` — "Really, though?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `noticed.fine.open` · offered only once the villager has actually said `state:steady`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.dig` — accepted phrasings: "really though"; "are you sure"; "really, though?"
  - the message must contain one of: `really`, `though`, `sure`
  - scored words: `really`(1.5), `though`(1.2), `sure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.respond.dig
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.respond.dig   [15 chars]
    en  Really, though?
    >>  ............................................
    pt  Sério mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `noticed.fine.dig`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.fine.opened.followup`
- …where the player's next choices will be: "Small things get heavy. What is it?" | "Then tell me when it stops keeping." | "Whatever it is, it isn't nothing." | "I'll let it keep, then."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.dig
WHO    VILLAGER — what the player reads after pressing "Really, though?"
       spoken on: conversations.topic.noticed.fine.respond, button `dig`
       leaves the player on: conversations.topic.noticed.fine.opened.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.small_thing`: the villager disclose_problems. Subject `noticed.wellbeing`, polarity `mixed`, guarded, outcome `qualified`.
NOTE   this is the line that establishes `state:steady`, `concern:unnamed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, restraint, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.dig/1   [58 chars]
    en  ...Mostly. There's a thing, but it's small and it'll keep.
    >>  ............................................
    pt  ...Na maioria. Tem uma coisa, mas é pequena e pode esperar.
    >>  ............................................
  dialogue.conversations.noticed.fine.dig/2   [59 chars]
    en  You're not letting that pass, are you. ...No. Not entirely.
    >>  ............................................
    pt  Você não vai deixar isso passar, né. ...Não. Não totalmente.
    >>  ............................................
  dialogue.conversations.noticed.fine.dig/3   [64 chars]
    en  Really. Ask me again in a week and I might have a longer answer.
    >>  ............................................
    pt  Sério. Me pergunte de novo em uma semana e talvez eu tenha resposta mais longa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. It's kept a while already.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Já espera faz um tempo.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. There's something and I'm not sure I could get through saying it just now.
    >>  ............................................
    pt  Quase bem. Tem algo e eu não sei se eu conseguiria dizer agora.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask again another day and I might be braver about it, %1$s.
    >>  ............................................
    pt  Quase. Pergunte outro dia e talvez eu tenha mais coragem, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. Most things do.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Quase tudo pode.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't in any hurry, so neither am I.
    >>  ............................................
    pt  Quase bem. Uma coisa, e ela não tem pressa, então eu também não.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. It'll sort itself or it'll come round to being said. Either's fine.
    >>  ............................................
    pt  Quase. Vai se resolver ou vai chegar a hora de ser dita. Tanto faz.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't ready to be talked about.
    >>  ............................................
    pt  Quase bem. Uma coisa, e não está pronta pra ser falada.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask me again in a week and you'll get a different answer.
    >>  ............................................
    pt  Quase. Me pergunte de novo em uma semana e você vai ter outra resposta.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't ready to be talked about.
    >>  ............................................
    pt  Quase bem. Uma coisa, e não está pronta pra ser falada.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask me again in a week and you'll get a different answer.
    >>  ............................................
    pt  Quase. Me pergunte de novo em uma semana e você vai ter outra resposta.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep, %1$s.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. There is something — ask me again when we've more time.
    >>  ............................................
    pt  Quase bem. Tem algo — me pergunte de novo quando a gente tiver mais tempo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. I'd tell you the rest if you sat down, but you've somewhere to be.
    >>  ............................................
    pt  Quase. Eu te contaria o resto se você sentasse, mas você tem lugar pra ir.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep, %1$s.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. There is something — ask me again when we've more time.
    >>  ............................................
    pt  Quase bem. Tem algo — me pergunte de novo quando a gente tiver mais tempo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. I'd tell you the rest if you sat down, but you've somewhere to be.
    >>  ............................................
    pt  Quase. Eu te contaria o resto se você sentasse, mas você tem lugar pra ir.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep, %1$s.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. There is something — ask me again when we've more time.
    >>  ............................................
    pt  Quase bem. Tem algo — me pergunte de novo quando a gente tiver mais tempo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. I'd tell you the rest if you sat down, but you've somewhere to be.
    >>  ............................................
    pt  Quase. Eu te contaria o resto se você sentasse, mas você tem lugar pra ir.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. It's kept a while already.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Já espera faz um tempo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. There's something and I'm not sure I could get through saying it just now.
    >>  ............................................
    pt  Quase bem. Tem algo e eu não sei se eu conseguiria dizer agora.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask again another day and I might be braver about it, %1$s.
    >>  ............................................
    pt  Quase. Pergunte outro dia e talvez eu tenha mais coragem, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't ready to be talked about.
    >>  ............................................
    pt  Quase bem. Uma coisa, e não está pronta pra ser falada.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask me again in a week and you'll get a different answer.
    >>  ............................................
    pt  Quase. Me pergunte de novo em uma semana e você vai ter outra resposta.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't ready to be talked about.
    >>  ............................................
    pt  Quase bem. Uma coisa, e não está pronta pra ser falada.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask me again in a week and you'll get a different answer.
    >>  ............................................
    pt  Quase. Me pergunte de novo em uma semana e você vai ter outra resposta.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.dig/2
    en  Mostly. It'll keep.
    >>  ............................................
    pt  Quase. Pode esperar.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.dig/3
    en  Mostly fine. One thing. Not today.
    >>  ............................................
    pt  Quase bem. Uma coisa. Hoje não.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. Most things do.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Quase tudo pode.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't in any hurry, so neither am I.
    >>  ............................................
    pt  Quase bem. Uma coisa, e ela não tem pressa, então eu também não.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. It'll sort itself or it'll come round to being said. Either's fine.
    >>  ............................................
    pt  Quase. Vai se resolver ou vai chegar a hora de ser dita. Tanto faz.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.dig/2
    en  Mostly. It'll keep.
    >>  ............................................
    pt  Quase. Pode esperar.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.dig/3
    en  Mostly fine. One thing. Not today.
    >>  ............................................
    pt  Quase bem. Uma coisa. Hoje não.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. Most things do.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Quase tudo pode.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't in any hurry, so neither am I.
    >>  ............................................
    pt  Quase bem. Uma coisa, e ela não tem pressa, então eu também não.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. It'll sort itself or it'll come round to being said. Either's fine.
    >>  ............................................
    pt  Quase. Vai se resolver ou vai chegar a hora de ser dita. Tanto faz.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.dig/1
    en  Mostly! There's a thing, but it's small and it'll keep. Very small. Barely a thing.
    >>  ............................................
    pt  Quase! Tem uma coisa, mas é pequena e pode esperar. Bem pequena. Quase nada.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One item outstanding, and it's not urgent enough to spoil an afternoon.
    >>  ............................................
    pt  Quase bem. Um item pendente, e não é urgente o bastante pra estragar uma tarde.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. There's something, and it's the sort of something that improves by being ignored.
    >>  ............................................
    pt  Quase. Tem algo, e é o tipo de algo que melhora sendo ignorado.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.dig/1
    en  Mostly! There's a thing, but it's small and it'll keep. Very small. Barely a thing.
    >>  ............................................
    pt  Quase! Tem uma coisa, mas é pequena e pode esperar. Bem pequena. Quase nada.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One item outstanding, and it's not urgent enough to spoil an afternoon.
    >>  ............................................
    pt  Quase bem. Um item pendente, e não é urgente o bastante pra estragar uma tarde.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. There's something, and it's the sort of something that improves by being ignored.
    >>  ............................................
    pt  Quase. Tem algo, e é o tipo de algo que melhora sendo ignorado.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. Most things do.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Quase tudo pode.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One thing, and it isn't in any hurry, so neither am I.
    >>  ............................................
    pt  Quase bem. Uma coisa, e ela não tem pressa, então eu também não.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. It'll sort itself or it'll come round to being said. Either's fine.
    >>  ............................................
    pt  Quase. Vai se resolver ou vai chegar a hora de ser dita. Tanto faz.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small and it'll keep. It's kept a while already.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena e pode esperar. Já espera faz um tempo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. There's something and I'm not sure I could get through saying it just now.
    >>  ............................................
    pt  Quase bem. Tem algo e eu não sei se eu conseguiria dizer agora.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. Ask again another day and I might be braver about it, %1$s.
    >>  ............................................
    pt  Quase. Pergunte outro dia e talvez eu tenha mais coragem, %1$s.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.dig/1
    en  Mostly. There's a thing, but it's small.
    >>  ............................................
    pt  Quase. Tem uma coisa, mas é pequena.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.dig/2
    en  Mostly. It'll keep.
    >>  ............................................
    pt  Quase. Pode esperar.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.dig/3
    en  Mostly fine. One thing. Not today.
    >>  ............................................
    pt  Quase bem. Uma coisa. Hoje não.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.dig/1
    en  Mostly! There's a thing, but it's small and it'll keep. Very small. Barely a thing.
    >>  ............................................
    pt  Quase! Tem uma coisa, mas é pequena e pode esperar. Bem pequena. Quase nada.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One item outstanding, and it's not urgent enough to spoil an afternoon.
    >>  ............................................
    pt  Quase bem. Um item pendente, e não é urgente o bastante pra estragar uma tarde.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. There's something, and it's the sort of something that improves by being ignored.
    >>  ............................................
    pt  Quase. Tem algo, e é o tipo de algo que melhora sendo ignorado.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.dig/1
    en  Mostly! There's a thing, but it's small and it'll keep. Very small. Barely a thing.
    >>  ............................................
    pt  Quase! Tem uma coisa, mas é pequena e pode esperar. Bem pequena. Quase nada.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.dig/2
    en  Mostly fine. One item outstanding, and it's not urgent enough to spoil an afternoon.
    >>  ............................................
    pt  Quase bem. Um item pendente, e não é urgente o bastante pra estragar uma tarde.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.dig/3
    en  Mostly. There's something, and it's the sort of something that improves by being ignored.
    >>  ............................................
    pt  Quase. Tem algo, e é o tipo de algo que melhora sendo ignorado.
    >>  ............................................
```

</details>


### Button `dismiss` — "Not much of an answer."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `noticed.fine.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.dismiss` — accepted phrasings: "not much of an answer"; "that is hardly an answer"; "not much to go on"
  - the message must contain one of: `answer`, `much`
  - scored words: `answer`(1.5), `much`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.respond.dismiss   [22 chars]
    en  Not much of an answer.
    >>  ............................................
    pt  Não é bem uma resposta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `noticed.fine.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `noticed.fine.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.fine.closed.followup`
- …where the player's next choices will be: "You're right. Poor question." | "I only meant I'd listen, if there were more." | "Understood." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.dismiss
WHO    VILLAGER — what the player reads after pressing "Not much of an answer."
       spoken on: conversations.topic.noticed.fine.respond, button `dismiss`
       leaves the player on: conversations.topic.noticed.fine.closed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.answer_defended`: the villager resists. Subject `noticed.wellbeing`, polarity `neutral`, closes subject, outcome `resisted`.
NOTE   this is the line that establishes `state:steady`, `subject:closed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.dismiss/1   [28 chars]
    en  It's the honest one, though.
    >>  ............................................
    pt  Mas é a honesta.
    >>  ............................................
  dialogue.conversations.noticed.fine.dismiss/2   [40 chars]
    en  ...You asked how I was. That's how I am.
    >>  ............................................
    pt  ...Você perguntou como eu estava. É assim que estou.
    >>  ............................................
  dialogue.conversations.noticed.fine.dismiss/3   [41 chars]
    en  Not everything needs to be a story, %1$s.
    >>  ............................................
    pt  Nem tudo precisa ser uma história, %1$s.
    >>  ............................................
```


### Button `blunt` — "You're not fine and we both know it."

*stance family `candor` · tone `blunt` · outcome `accepted` · answers the beat(s) `noticed.fine.open` · offered only once the villager has actually said `state:steady`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.fine.blunt` — accepted phrasings: "you are not fine and we both know it"; "you are not fine"; "do not tell me you are fine"
  - scored words: `both`(0.8), `fine`(0.6), `know`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.respond.blunt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.respond.blunt   [36 chars]
    en  You're not fine and we both know it.
    >>  ............................................
    pt  Você não está bem e nós dois sabemos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.fine.blunt`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, tension +2  _(recorded under topic `noticed.fine.blunt`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.fine.admitted.followup`
- …where the player's next choices will be: "How long has it been like that?" | "Tell me what would help." | "I'll be around." | "I'll not make a thing of it."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.blunt
WHO    VILLAGER — what the player reads after pressing "You're not fine and we both know it."
       spoken on: conversations.topic.noticed.fine.respond, button `blunt`
       leaves the player on: conversations.topic.noticed.fine.admitted.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.not_fine`: the villager discloses. Subject `noticed.wellbeing`, polarity `mixed`, invites followup, outcome `accepted`.
NOTE   this is the line that establishes `state:steady`, `admission:not_fine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, practical_help, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.blunt/1   [92 chars]
    en  ...No. I'm not. Nobody's ever just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não estou. Ninguém nunca disse assim, direto, então venho me safando com 'estou bem' há semanas.
    >>  ............................................
  dialogue.conversations.noticed.fine.blunt/2   [69 chars]
    en  That's very direct. ...And correct, %1$s, which is the annoying part.
    >>  ............................................
    pt  Isso é bem direto. ...E correto, %1$s, que é a parte irritante.
    >>  ............................................
  dialogue.conversations.noticed.fine.blunt/3   [85 chars]
    en  Hm. Well. You've saved us both about four more rounds of me saying 'fine', I suppose.
    >>  ............................................
    pt  Hm. Bom. Você poupou uns quatro rounds de mim dizendo 'estou bem', imagino.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's ever just said it, %1$s, so I've been getting away with 'fine'.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém nunca disse assim, %1$s, então eu vinha escapando com 'bem'.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.blunt/2
    en  No. I'd been hoping somebody would ask and dreading it in equal measure.
    >>  ............................................
    pt  Não. Eu vinha esperando que alguém perguntasse e temendo na mesma medida.
    >>  ............................................
  anxious.dialogue.conversations.noticed.fine.blunt/3
    en  ...No, I'm not. Give me a moment. That was harder than it sounded.
    >>  ............................................
    pt  ...Não, não estou. Me dê um momento. Foi mais difícil do que pareceu.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody's just said it, so 'fine' has done the job for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então 'bem' deu conta por semanas.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.blunt/2
    en  No. It's been a long while and 'fine' is a very serviceable word.
    >>  ............................................
    pt  Não. Faz muito tempo e 'bem' é uma palavra bem útil.
    >>  ............................................
  athletic.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. It'll do me good to have said it, I expect.
    >>  ............................................
    pt  ...Não. Certo. Deve me fazer bem ter dito, imagino.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody has just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.blunt/2
    en  No. And you're the first to make it awkward enough that I couldn't dodge.
    >>  ............................................
    pt  Não. E você é o primeiro a deixar constrangedor o bastante pra eu não desviar.
    >>  ............................................
  confident.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. There. That's the first time that's been said in this village.
    >>  ............................................
    pt  Não, não estou. Pronto. É a primeira vez que isso é dito neste vilarejo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody has just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.blunt/2
    en  No. And you're the first to make it awkward enough that I couldn't dodge.
    >>  ............................................
    pt  Não. E você é o primeiro a deixar constrangedor o bastante pra eu não desviar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. There. That's the first time that's been said in this village.
    >>  ............................................
    pt  Não, não estou. Pronto. É a primeira vez que isso é dito neste vilarejo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not, %1$s. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou, %1$s. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.blunt/2
    en  No. And I'd rather you than anybody else be the one who noticed.
    >>  ............................................
    pt  Não. E eu prefiro que seja você a ter reparado.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. Sit down, then, since you've gone and asked properly.
    >>  ............................................
    pt  Não, não estou. Então sente-se, já que você foi e perguntou direito.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not, %1$s. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou, %1$s. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.blunt/2
    en  No. And I'd rather you than anybody else be the one who noticed.
    >>  ............................................
    pt  Não. E eu prefiro que seja você a ter reparado.
    >>  ............................................
  flirty.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. Sit down, then, since you've gone and asked properly.
    >>  ............................................
    pt  Não, não estou. Então sente-se, já que você foi e perguntou direito.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not, %1$s. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou, %1$s. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.blunt/2
    en  No. And I'd rather you than anybody else be the one who noticed.
    >>  ............................................
    pt  Não. E eu prefiro que seja você a ter reparado.
    >>  ............................................
  friendly.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. Sit down, then, since you've gone and asked properly.
    >>  ............................................
    pt  Não, não estou. Então sente-se, já que você foi e perguntou direito.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's ever just said it, %1$s, so I've been getting away with 'fine'.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém nunca disse assim, %1$s, então eu vinha escapando com 'bem'.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.blunt/2
    en  No. I'd been hoping somebody would ask and dreading it in equal measure.
    >>  ............................................
    pt  Não. Eu vinha esperando que alguém perguntasse e temendo na mesma medida.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.fine.blunt/3
    en  ...No, I'm not. Give me a moment. That was harder than it sounded.
    >>  ............................................
    pt  ...Não, não estou. Me dê um momento. Foi mais difícil do que pareceu.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody has just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.blunt/2
    en  No. And you're the first to make it awkward enough that I couldn't dodge.
    >>  ............................................
    pt  Não. E você é o primeiro a deixar constrangedor o bastante pra eu não desviar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. There. That's the first time that's been said in this village.
    >>  ............................................
    pt  Não, não estou. Pronto. É a primeira vez que isso é dito neste vilarejo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody has just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.blunt/2
    en  No. And you're the first to make it awkward enough that I couldn't dodge.
    >>  ............................................
    pt  Não. E você é o primeiro a deixar constrangedor o bastante pra eu não desviar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not. There. That's the first time that's been said in this village.
    >>  ............................................
    pt  Não, não estou. Pronto. É a primeira vez que isso é dito neste vilarejo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not.
    >>  ............................................
    pt  ...Não. Não estou.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.blunt/2
    en  No. Nobody's just said it before.
    >>  ............................................
    pt  Não. Ninguém tinha dito assim antes.
    >>  ............................................
  introverted.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. That's out, then.
    >>  ............................................
    pt  ...Não. Certo. Então está dito.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody's just said it, so 'fine' has done the job for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então 'bem' deu conta por semanas.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.blunt/2
    en  No. It's been a long while and 'fine' is a very serviceable word.
    >>  ............................................
    pt  Não. Faz muito tempo e 'bem' é uma palavra bem útil.
    >>  ............................................
  lazy.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. It'll do me good to have said it, I expect.
    >>  ............................................
    pt  ...Não. Certo. Deve me fazer bem ter dito, imagino.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not.
    >>  ............................................
    pt  ...Não. Não estou.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.blunt/2
    en  No. Nobody's just said it before.
    >>  ............................................
    pt  Não. Ninguém tinha dito assim antes.
    >>  ............................................
  odd.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. That's out, then.
    >>  ............................................
    pt  ...Não. Certo. Então está dito.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody's just said it, so 'fine' has done the job for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então 'bem' deu conta por semanas.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.blunt/2
    en  No. It's been a long while and 'fine' is a very serviceable word.
    >>  ............................................
    pt  Não. Faz muito tempo e 'bem' é uma palavra bem útil.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. It'll do me good to have said it, I expect.
    >>  ............................................
    pt  ...Não. Certo. Deve me fazer bem ter dito, imagino.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.blunt/2
    en  No! And that's the first time anybody's made me say it. Well done, I suppose.
    >>  ............................................
    pt  Não! E é a primeira vez que alguém me faz dizer. Parabéns, eu acho.
    >>  ............................................
  peppy.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not, and now I've said it out loud and there's no putting it back.
    >>  ............................................
    pt  Não, não estou, e agora eu disse em voz alta e não tem como voltar atrás.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.blunt/2
    en  No! And that's the first time anybody's made me say it. Well done, I suppose.
    >>  ............................................
    pt  Não! E é a primeira vez que alguém me faz dizer. Parabéns, eu acho.
    >>  ............................................
  playful.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not, and now I've said it out loud and there's no putting it back.
    >>  ............................................
    pt  Não, não estou, e agora eu disse em voz alta e não tem como voltar atrás.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.blunt/1
    en  No. I'm not. Nobody's just said it, so 'fine' has done the job for weeks.
    >>  ............................................
    pt  Não. Não estou. Ninguém tinha dito assim, então 'bem' deu conta por semanas.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.blunt/2
    en  No. It's been a long while and 'fine' is a very serviceable word.
    >>  ............................................
    pt  Não. Faz muito tempo e 'bem' é uma palavra bem útil.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. It'll do me good to have said it, I expect.
    >>  ............................................
    pt  ...Não. Certo. Deve me fazer bem ter dito, imagino.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's ever just said it, %1$s, so I've been getting away with 'fine'.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém nunca disse assim, %1$s, então eu vinha escapando com 'bem'.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.blunt/2
    en  No. I'd been hoping somebody would ask and dreading it in equal measure.
    >>  ............................................
    pt  Não. Eu vinha esperando que alguém perguntasse e temendo na mesma medida.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.fine.blunt/3
    en  ...No, I'm not. Give me a moment. That was harder than it sounded.
    >>  ............................................
    pt  ...Não, não estou. Me dê um momento. Foi mais difícil do que pareceu.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not.
    >>  ............................................
    pt  ...Não. Não estou.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.blunt/2
    en  No. Nobody's just said it before.
    >>  ............................................
    pt  Não. Ninguém tinha dito assim antes.
    >>  ............................................
  shy.dialogue.conversations.noticed.fine.blunt/3
    en  ...No. Right. That's out, then.
    >>  ............................................
    pt  ...Não. Certo. Então está dito.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.blunt/2
    en  No! And that's the first time anybody's made me say it. Well done, I suppose.
    >>  ............................................
    pt  Não! E é a primeira vez que alguém me faz dizer. Parabéns, eu acho.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not, and now I've said it out loud and there's no putting it back.
    >>  ............................................
    pt  Não, não estou, e agora eu disse em voz alta e não tem como voltar atrás.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.blunt/1
    en  ...No. I'm not. Nobody's just said it, so I've been getting away with 'fine' for weeks.
    >>  ............................................
    pt  ...Não. Não estou. Ninguém tinha dito assim, então eu vinha escapando com 'bem' há semanas.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.blunt/2
    en  No! And that's the first time anybody's made me say it. Well done, I suppose.
    >>  ............................................
    pt  Não! E é a primeira vez que alguém me faz dizer. Parabéns, eu acho.
    >>  ............................................
  witty.dialogue.conversations.noticed.fine.blunt/3
    en  No, I'm not, and now I've said it out loud and there's no putting it back.
    >>  ............................................
    pt  Não, não estou, e agora eu disse em voz alta e não tem como voltar atrás.
    >>  ............................................
```

</details>


### Button `leave` — "Fair. I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.fine.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.fine.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.fine.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.fine.respond.leave   [18 chars]
    en  Fair. I'll get on.
    >>  ............................................
    pt  Justo. Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine.leave
WHO    VILLAGER — what the player reads after pressing "Fair. I'll get on."
       spoken on: conversations.topic.noticed.fine.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.left`: the villager accepts. Subject `noticed.wellbeing`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine.leave/1   [18 chars]
    en  It is. Off you go.
    >>  ............................................
    pt  É sim. Pode ir.
    >>  ............................................
  dialogue.conversations.noticed.fine.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.noticed.fine.leave/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuida, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.grieving.hostile.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.grieving.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.grieving.dismiss` — e.g. "...Get away from me, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.hostile.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.grieving.hostile.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.grieving.hostile.followup   [17 chars]
    en  Well? Still here.
    >>  ............................................
    pt  E então? Ainda aqui.
    >>  ............................................
```


### Button `apologize` — "That was cruel of me. I'm sorry."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.grieving.dismissed` · offered only once the villager has actually said `player:dismissed_grief`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.hostile.apologize` — accepted phrasings: "that was cruel of me"; "i am sorry, that was cruel"; "that was unkind of me"
  - the message must contain one of: `cruel`, `unkind`, `sorry`
  - scored words: `cruel`(1.5), `unkind`(1.5), `sorry`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.hostile.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.hostile.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.hostile.followup.apologize   [32 chars]
    en  That was cruel of me. I'm sorry.
    >>  ............................................
    pt  Isso foi cruel da minha parte. Me desculpe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `noticed.grieving.hostile.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.hostile.apologize
WHO    VILLAGER — what the player reads after pressing "That was cruel of me. I'm sorry."
       spoken on: conversations.topic.noticed.grieving.hostile.followup, button `apologize`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.hostile.apologize`: the villager qualifys. Subject `noticed.grief`, polarity `acute`, guarded, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.hostile.apologize/1   [49 chars]
    en  ...It was. Go on, then. I'll not hold it forever.
    >>  ............................................
    pt  ...Foi. Pode ir, então. Não vou guardar isso pra sempre.
    >>  ............................................
  dialogue.conversations.noticed.grieving.hostile.apologize/2   [64 chars]
    en  True enough, it was. Say nothing else and we'll call it managed.
    >>  ............................................
    pt  Bem verdade, foi. Não diga mais nada e a gente considera resolvido.
    >>  ............................................
  dialogue.conversations.noticed.grieving.hostile.apologize/3   [33 chars]
    en  ...Thank you. Now leave it, %1$s.
    >>  ............................................
    pt  ...Obrigado. Agora deixe pra lá, %1$s.
    >>  ............................................
```


### Button `explain` — "I meant it as comfort. It wasn't."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `noticed.grieving.dismissed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.hostile.explain` — accepted phrasings: "i meant it as comfort"; "i was trying to comfort you"; "i meant that as comfort"
  - the message must contain one of: `comfort`, `meant`, `trying`
  - scored words: `comfort`(1.5), `meant`(1.2), `trying`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.hostile.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.hostile.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.hostile.followup.explain   [33 chars]
    en  I meant it as comfort. It wasn't.
    >>  ............................................
    pt  Eu quis dizer como conforto. Não foi.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `noticed.grieving.hostile.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.hostile.explain
WHO    VILLAGER — what the player reads after pressing "I meant it as comfort. It wasn't."
       spoken on: conversations.topic.noticed.grieving.hostile.followup, button `explain`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.hostile.explain`: the villager qualifys. Subject `noticed.grief`, polarity `acute`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.hostile.explain/1   [31 chars]
    en  Comfort. Is that what that was.
    >>  ............................................
    pt  Conforto. Então era isso.
    >>  ............................................
  dialogue.conversations.noticed.grieving.hostile.explain/2   [64 chars]
    en  Then say the comfort next time and leave the philosophy at home.
    >>  ............................................
    pt  Então da próxima vez diga o conforto e deixe a filosofia em casa.
    >>  ............................................
  dialogue.conversations.noticed.grieving.hostile.explain/3   [60 chars]
    en  ...I'll take that it wasn't meant to land like it did, %1$s.
    >>  ............................................
    pt  ...Aceito que não era pra soar como soou, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.grieving.dismissed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.hostile.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.hostile.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.hostile.followup.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.hostile.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.noticed.grieving.hostile.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.hostile.leave`: the villager accepts. Subject `noticed.grief`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.hostile.leave/1   [3 chars]
    en  Do.
    >>  ............................................
    pt  Vá.
    >>  ............................................
  dialogue.conversations.noticed.grieving.hostile.leave/2   [12 chars]
    en  True enough.
    >>  ............................................
    pt  Bem verdade.
    >>  ............................................
  dialogue.conversations.noticed.grieving.hostile.leave/3   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
```

---


## `conversations.topic.noticed.grieving.quiet.followup`

**Reached from 2 route(s):** `conversations.topic.noticed.grieving.respond` / `validate`; `conversations.topic.noticed.grieving.respond` / `give_space`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.grieving.give_space` — e.g. "Thank you. Everyone wants the story. You didn't ask for it."
- `conversations.noticed.grieving.validate.guarded` — e.g. "...Don't. If you're kind about it now I'll not get through the rest of the day. ...But aye. Thank you. Say nothing else."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.quiet.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.grieving.quiet.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.grieving.quiet.followup   [23 chars]
    en  Aye. Just this is fine.
    >>  ............................................
    pt  É. Assim já está bom.
    >>  ............................................
```


### Button `stay_silent` — "I'll stay, and say nothing."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.grieving.quiet_thanked`, `noticed.grieving.space_given` · offered only once the villager has actually said `villager:wants_quiet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.quiet.stay_silent` — accepted phrasings: "i will stay and say nothing"; "i will be quiet"; "i will stay silent"
  - the message must contain one of: `silent`, `quiet`
  - scored words: `silent`(1.5), `quiet`(1.5), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.quiet.followup.stay_silent
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.quiet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.quiet.followup.stay_silent   [27 chars]
    en  I'll stay, and say nothing.
    >>  ............................................
    pt  Eu fico, e não digo nada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.quiet.stay_silent`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `noticed.grieving.quiet.stay_silent`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.quiet.stay_silent
WHO    VILLAGER — what the player reads after pressing "I'll stay, and say nothing."
       spoken on: conversations.topic.noticed.grieving.quiet.followup, button `stay_silent`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.quiet.stay_silent`: the villager accepts. Subject `noticed.grief`, polarity `acute`, guarded, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.quiet.stay_silent/1   [8 chars]
    en  ...Good.
    >>  ............................................
    pt  ...Bom.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.stay_silent/2   [52 chars]
    en  Thank you. Stand where I can see you and don't talk.
    >>  ............................................
    pt  Obrigado. Fique onde eu possa te ver e não fale.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.stay_silent/3   [62 chars]
    en  That's the most useful thing anyone's offered this week, %1$s.
    >>  ............................................
    pt  É a coisa mais útil que alguém ofereceu esta semana, %1$s.
    >>  ............................................
```


### Button `come_find_me` — "Come and find me when you can."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.grieving.quiet_thanked`, `noticed.grieving.space_given`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.quiet.come_find_me` — accepted phrasings: "come and find me when you can"; "find me when you are ready"; "come find me"
  - the message must contain one of: `find`, `come`
  - scored words: `find`(1.5), `come`(1.2), `ready`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.quiet.followup.come_find_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.quiet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.quiet.followup.come_find_me   [30 chars]
    en  Come and find me when you can.
    >>  ............................................
    pt  Me procure quando você conseguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.quiet.come_find_me`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +3  _(recorded under topic `noticed.grieving.quiet.come_find_me`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.quiet.come_find_me
WHO    VILLAGER — what the player reads after pressing "Come and find me when you can."
       spoken on: conversations.topic.noticed.grieving.quiet.followup, button `come_find_me`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.quiet.come_find_me`: the villager accepts. Subject `noticed.grief`, polarity `acute`, guarded, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.quiet.come_find_me/1   [33 chars]
    en  I will. Don't come looking first.
    >>  ............................................
    pt  Vou procurar. Não venha me procurar antes.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.come_find_me/2   [33 chars]
    en  It is. It might be a while, %1$s.
    >>  ............................................
    pt  É sim. Pode demorar, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.come_find_me/3   [39 chars]
    en  ...I'll try. That's the honest version.
    >>  ............................................
    pt  ...Vou tentar. Essa é a versão honesta.
    >>  ............................................
```


### Button `offer_practical` — "I'll see to anything that needs doing."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.grieving.quiet_thanked`, `noticed.grieving.space_given` · offered only once the villager has actually said `villager:wants_quiet`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.quiet.offer_practical` — accepted phrasings: "i will see to anything that needs doing"; "i will handle what needs doing"; "i will take care of things"
  - the message must contain one of: `handle`, `care`, `see`
  - scored words: `handle`(1.5), `care`(1.2), `see`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.quiet.followup.offer_practical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.quiet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.quiet.followup.offer_practical   [38 chars]
    en  I'll see to anything that needs doing.
    >>  ............................................
    pt  Eu cuido do que precisar ser feito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.quiet.offer_practical`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `noticed.grieving.quiet.offer_practical`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.quiet.offer_practical
WHO    VILLAGER — what the player reads after pressing "I'll see to anything that needs doing."
       spoken on: conversations.topic.noticed.grieving.quiet.followup, button `offer_practical`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.quiet.offer_practical`: the villager accepts. Subject `noticed.grief`, polarity `acute`, guarded, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.quiet.offer_practical/1   [52 chars]
    en  The animals, then. And don't tell me you've done it.
    >>  ............................................
    pt  Os animais, então. E não me avise que fez.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.offer_practical/2   [63 chars]
    en  If you must do something, do that. It helps me not to be asked.
    >>  ............................................
    pt  Se você precisa fazer algo, faça isso. Ajuda não ter que ser perguntado.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.offer_practical/3   [23 chars]
    en  Just so. Quietly, mind.
    >>  ............................................
    pt  Pois é. Mas em silêncio.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.grieving.quiet_thanked`, `noticed.grieving.space_given` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.quiet.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.quiet.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.quiet.followup.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.quiet.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.noticed.grieving.quiet.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.quiet.leave`: the villager accepts. Subject `noticed.grief`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.quiet.leave/1   [25 chars]
    en  So I've found. Thank you.
    >>  ............................................
    pt  Foi o que eu vi. Obrigado.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.leave/2   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
  dialogue.conversations.noticed.grieving.quiet.leave/3   [24 chars]
    en  We'll speak again, %1$s.
    >>  ............................................
    pt  A gente se fala, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.grieving.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.grieving` — e.g. "Ah. You heard, then. We lost one of our own. The whole village feels the gap. ...Thank you for asking, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.grieving.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.grieving.respond   [32 chars]
    en  It's been a heavy few days here.
    >>  ............................................
    pt  Foram dias pesados por aqui.
    >>  ............................................
```


### Button `validate` — "You're allowed to feel that."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.grieving.open` · offered only once the villager has actually said `state:grieving`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.validate` — accepted phrasings: "you are allowed to feel that"; "you are allowed to grieve"; "it is alright to feel that"
  - the message must contain one of: `allowed`, `feel`, `grieve`
  - scored words: `allowed`(1.5), `feel`(1.0), `grieve`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.respond.validate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.respond.validate   [28 chars]
    en  You're allowed to feel that.
    >>  ............................................
    pt  Você tem o direito de sentir isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `crabby`, `grumpy`, `confident`
- Does: **hearts +2** — decision id `noticed.grieving.validate`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2, tension -2  _(recorded under topic `noticed.grieving.validate`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.grieving.quiet.followup`
- …where the player's next choices will be: "I'll stay, and say nothing." | "Come and find me when you can." | "I'll see to anything that needs doing." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.validate.guarded
WHO    VILLAGER — what the player reads after pressing "You're allowed to feel that."
       spoken on: conversations.topic.noticed.grieving.respond, button `validate`
       leaves the player on: conversations.topic.noticed.grieving.quiet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.quiet_thanked`: the villager accepts. Subject `noticed.grief`, polarity `acute`, guarded, outcome `appreciated`.
NOTE   this is the line that establishes `state:grieving`, `loss:recent`, `villager:wants_quiet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, restraint, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.validate.guarded/1   [120 chars]
    en  ...Don't. If you're kind about it now I'll not get through the rest of the day. ...But aye. Thank you. Say nothing else.
    >>  ............................................
    pt  ...Não. Se você for gentil comigo agora eu não atravesso o resto do dia. ...Mas é. Obrigado. Não diga mais nada.
    >>  ............................................
  dialogue.conversations.noticed.grieving.validate.guarded/2   [86 chars]
    en  I'm allowed. I know I'm allowed. Knowing and feeling are two different counties, %1$s.
    >>  ............................................
    pt  Eu tenho direito. Eu sei que tenho. Saber e sentir são dois países diferentes, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.grieving.validate.guarded/3   [97 chars]
    en  Right. Yes. Fine. ...You're the first who didn't tell me it gets easier, so — thank you for that.
    >>  ............................................
    pt  Certo. Sim. Tudo bem. ...Você foi o primeiro que não disse que melhora, então — obrigado por isso.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `crabby`, `grumpy`, `confident`  _(chance -2000)_
- Does: **hearts +2** — decision id `noticed.grieving.validate`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `noticed.grieving.validate`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.grieving.supported.followup`
- …where the player's next choices will be: "Tell me about them, if you want to." | "I'll sit here a while." | "Is there anything that needs doing?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.validate
WHO    VILLAGER — what the player reads after pressing "You're allowed to feel that."
       spoken on: conversations.topic.noticed.grieving.respond, button `validate`
       leaves the player on: conversations.topic.noticed.grieving.supported.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.validated`: the villager accepts. Subject `noticed.grief`, polarity `acute`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `state:grieving`, `loss:recent`, `grief:acknowledged` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, practical_help, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.validate/1   [51 chars]
    en  ...Aye. I am allowed, aren't I. Nobody's said that.
    >>  ............................................
    pt  ...É. Eu tenho o direito, né. Ninguém disse isso.
    >>  ............................................
  dialogue.conversations.noticed.grieving.validate/2   [58 chars]
    en  Everyone's telling me to be strong. You didn't. Thank you.
    >>  ............................................
    pt  Todo mundo me manda ser forte. Você não. Obrigado.
    >>  ............................................
  dialogue.conversations.noticed.grieving.validate/3   [57 chars]
    en  Allowed. That's the whole of what I needed to hear, %1$s.
    >>  ............................................
    pt  Ter direito. Era só isso que eu precisava ouvir, %1$s.
    >>  ............................................
```


### Button `give_space` — "I'll not make you talk about it."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.grieving.open` · offered only once the villager has actually said `state:grieving`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.give_space` — accepted phrasings: "i will not make you talk about it"; "i will not push"; "i will not force it"
  - the message must contain one of: `talk`, `push`, `force`, `make`
  - scored words: `talk`(1.0), `make`(0.8), `push`(1.5), `force`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.respond.give_space
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.respond.give_space   [32 chars]
    en  I'll not make you talk about it.
    >>  ............................................
    pt  Não vou te obrigar a falar disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**

