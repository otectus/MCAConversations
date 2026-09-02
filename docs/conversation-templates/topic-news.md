# Topic: news

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `news` |
| Opened from | question `conversations.cat.events`, button `news` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.events` |
| Ages that can reach it | child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `restraint`, `dismissal`, `exit` |
| Narrative arc | `news`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.events`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.events.news
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.events.news   [35 chars]
    en  Anything happen around here lately?
    >>  ............................................
    pt  Aconteceu alguma coisa por aqui ultimamente?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.news.resume.followup`](#conversations-arc-news-resume-followup)
- [`conversations.arc.news.resume.respond`](#conversations-arc-news-resume-respond)
- [`conversations.scene.news.followup`](#conversations-scene-news-followup)
- [`conversations.scene.news.quiet_week.respond`](#conversations-scene-news-quiet-week-respond)
- [`conversations.scene.news.while_you_were_away.respond`](#conversations-scene-news-while-you-were-away-respond)
- [`conversations.topic.news.callous.followup`](#conversations-topic-news-callous-followup)
- [`conversations.topic.news.deflated.followup`](#conversations-topic-news-deflated-followup)
- [`conversations.topic.news.glad.followup`](#conversations-topic-news-glad-followup)
- [`conversations.topic.news.glad.respond`](#conversations-topic-news-glad-respond)
- [`conversations.topic.news.grave.followup`](#conversations-topic-news-grave-followup)
- [`conversations.topic.news.helped.respond`](#conversations-topic-news-helped-respond)
- [`conversations.topic.news.mixed.followup`](#conversations-topic-news-mixed-followup)
- [`conversations.topic.news.mixed.respond`](#conversations-topic-news-mixed-respond)
- [`conversations.topic.news.none.respond`](#conversations-topic-news-none-respond)
- [`conversations.topic.news.sad.respond`](#conversations-topic-news-sad-respond)
- [`conversations.topic.news.teen.respond`](#conversations-topic-news-teen-respond)
- [`conversations.topic.news.young.respond`](#conversations-topic-news-young-respond)

---

## `conversations.arc.news.resume.followup`

**Reached from 3 route(s):** `conversations.arc.news.resume.respond` / `ask_after_them_now`; `conversations.arc.news.resume.respond` / `anything_else`; `conversations.arc.news.resume.respond` / `glad_it_helped`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.resume.anything_else` — e.g. "Not things. They've had things from four households and no company from any of them."
- `conversations.news.resume.ask_after_them_now` — e.g. "Standing. Which at three weeks is the whole of what you can ask for."
- `conversations.news.resume.glad_it_helped` — e.g. "It did. Not enough, which is what help is, and it did."


```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.news.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.news.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `news.resume.ask_after_them_now`, `news.resume.anything_else`, `news.resume.glad_it_helped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `bereaved`(0.3), `keeping`(1.2), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `news.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.news.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.thank_you_for_telling`: the villager accepts. Subject `news.aftermath`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.news.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.news.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `news.resume.ask_after_them_now`, `news.resume.anything_else`, `news.resume.glad_it_helped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `bereaved`(0.3), `leave`(0.6), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.followup.leave_it_with_you   [23 chars]
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
POOL   dialogue key: dialogue.conversations.news.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.news.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.leave_it_with_you`: the villager accepts. Subject `news.aftermath`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.news.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.news.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.resume.ask_after_them_now`, `news.resume.anything_else`, `news.resume.glad_it_helped` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.followup.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.news.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.news.resume.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.leave`: the villager accepts. Subject `news.aftermath`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.news.resume.respond / leave
```

```text
  dialogue.conversations.news.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.news.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.news.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.news.resume.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.resume` — e.g. "The firewood arrived. I've not said who from and neither has anybody else."


```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.news.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.news.resume.respond   [37 chars]
    en  That's the end of that piece of news.
    >>  ............................................
    pt  É esse o fim dessa notícia.
    >>  ............................................
```


### Button `ask_after_them_now` — "And how are they now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `news.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.resume.ask_after_them_now` — accepted phrasings: "and how are they now"; "how are they getting on now"; "how have they been since"
  - scored words: `now`(0.6), `they`(0.3)

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.respond.ask_after_them_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.respond.ask_after_them_now   [21 chars]
    en  And how are they now?
    >>  ............................................
    pt  E como eles estão agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.news.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.news.resume.ask_after_them_now
WHO    VILLAGER — what the player reads after pressing "And how are they now?"
       spoken on: conversations.arc.news.resume.respond, button `ask_after_them_now`
       leaves the player on: conversations.arc.news.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.ask_after_them_now`: the villager discloses. Subject `news.aftermath`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.resume.ask_after_them_now/1   [68 chars]
    en  Standing. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  dialogue.conversations.news.resume.ask_after_them_now/2   [71 chars]
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  dialogue.conversations.news.resume.ask_after_them_now/3   [68 chars]
    en  They asked after you, actually. I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade. Eu disse que você vinha perguntando.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's the whole of it, and I'd hoped for more for them.
    >>  ............................................
    pt  De pé. Com três semanas é tudo, e eu esperava mais por eles.
    >>  ............................................
  anxious.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. I've stopped hoping for a line.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Parei de esperar uma linha reta.
    >>  ............................................
  anxious.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. I'd not expected them to have room for anybody else yet.
    >>  ............................................
    pt  Perguntaram por você. Não esperava que tivessem espaço pra mais alguém ainda.
    >>  ............................................
  athletic.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything; I've watched enough of these to know.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo; já vi o bastante disso pra saber.
    >>  ............................................
  athletic.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. Grief always goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. O luto vai sempre em ondas.
    >>  ............................................
  athletic.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. That's the first sign of a person coming back to themselves.
    >>  ............................................
    pt  Perguntaram por você. É o primeiro sinal de alguém voltando a si.
    >>  ............................................
  confident.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  confident.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  confident.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually. I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade. Eu disse que você vinha perguntando.
    >>  ............................................
  crabby.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  crabby.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  crabby.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually. I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade. Eu disse que você vinha perguntando.
    >>  ............................................
  extroverted.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing, %1$s. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé, %1$s. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  extroverted.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  extroverted.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. I told them you'd been asking after them, and they were glad.
    >>  ............................................
    pt  Perguntaram por você. Eu disse que você vinha perguntando, e ficaram contentes.
    >>  ............................................
  flirty.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing, %1$s. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé, %1$s. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  flirty.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  flirty.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. I told them you'd been asking after them, and they were glad.
    >>  ............................................
    pt  Perguntaram por você. Eu disse que você vinha perguntando, e ficaram contentes.
    >>  ............................................
  friendly.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing, %1$s. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé, %1$s. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  friendly.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  friendly.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. I told them you'd been asking after them, and they were glad.
    >>  ............................................
    pt  Perguntaram por você. Eu disse que você vinha perguntando, e ficaram contentes.
    >>  ............................................
  gloomy.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's the whole of it, and I'd hoped for more for them.
    >>  ............................................
    pt  De pé. Com três semanas é tudo, e eu esperava mais por eles.
    >>  ............................................
  gloomy.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. I've stopped hoping for a line.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Parei de esperar uma linha reta.
    >>  ............................................
  gloomy.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. I'd not expected them to have room for anybody else yet.
    >>  ............................................
    pt  Perguntaram por você. Não esperava que tivessem espaço pra mais alguém ainda.
    >>  ............................................
  greedy.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  greedy.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  greedy.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually. I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade. Eu disse que você vinha perguntando.
    >>  ............................................
  grumpy.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. Which at three weeks is the whole of what you can ask for.
    >>  ............................................
    pt  De pé. O que com três semanas já é tudo que se pode pedir.
    >>  ............................................
  grumpy.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas.
    >>  ............................................
  grumpy.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually. I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade. Eu disse que você vinha perguntando.
    >>  ............................................
  introverted.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo.
    >>  ............................................
  introverted.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week. Better than the week before.
    >>  ............................................
    pt  Pior que semana passada. Melhor que a retrasada.
    >>  ............................................
  introverted.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you.
    >>  ............................................
    pt  Perguntaram por você.
    >>  ............................................
  lazy.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything; I've watched enough of these to know.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo; já vi o bastante disso pra saber.
    >>  ............................................
  lazy.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. Grief always goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. O luto vai sempre em ondas.
    >>  ............................................
  lazy.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. That's the first sign of a person coming back to themselves.
    >>  ............................................
    pt  Perguntaram por você. É o primeiro sinal de alguém voltando a si.
    >>  ............................................
  odd.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo.
    >>  ............................................
  odd.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week. Better than the week before.
    >>  ............................................
    pt  Pior que semana passada. Melhor que a retrasada.
    >>  ............................................
  odd.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you.
    >>  ............................................
    pt  Perguntaram por você.
    >>  ............................................
  peaceful.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything; I've watched enough of these to know.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo; já vi o bastante disso pra saber.
    >>  ............................................
  peaceful.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. Grief always goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. O luto vai sempre em ondas.
    >>  ............................................
  peaceful.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. That's the first sign of a person coming back to themselves.
    >>  ............................................
    pt  Perguntaram por você. É o primeiro sinal de alguém voltando a si.
    >>  ............................................
  peppy.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing! Which at three weeks is genuinely the whole of what you can ask for.
    >>  ............................................
    pt  De pé! O que com três semanas é sinceramente tudo que se pode pedir.
    >>  ............................................
  peppy.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves, apparently.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas, ao que parece.
    >>  ............................................
  peppy.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually! I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade! Eu disse que você vinha perguntando.
    >>  ............................................
  playful.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing! Which at three weeks is genuinely the whole of what you can ask for.
    >>  ............................................
    pt  De pé! O que com três semanas é sinceramente tudo que se pode pedir.
    >>  ............................................
  playful.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves, apparently.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas, ao que parece.
    >>  ............................................
  playful.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually! I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade! Eu disse que você vinha perguntando.
    >>  ............................................
  relaxed.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything; I've watched enough of these to know.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo; já vi o bastante disso pra saber.
    >>  ............................................
  relaxed.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. Grief always goes in waves.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. O luto vai sempre em ondas.
    >>  ............................................
  relaxed.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. That's the first sign of a person coming back to themselves.
    >>  ............................................
    pt  Perguntaram por você. É o primeiro sinal de alguém voltando a si.
    >>  ............................................
  sensitive.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's the whole of it, and I'd hoped for more for them.
    >>  ............................................
    pt  De pé. Com três semanas é tudo, e eu esperava mais por eles.
    >>  ............................................
  sensitive.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. I've stopped hoping for a line.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Parei de esperar uma linha reta.
    >>  ............................................
  sensitive.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you. I'd not expected them to have room for anybody else yet.
    >>  ............................................
    pt  Perguntaram por você. Não esperava que tivessem espaço pra mais alguém ainda.
    >>  ............................................
  shy.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing. At three weeks that's everything.
    >>  ............................................
    pt  De pé. Com três semanas isso é tudo.
    >>  ............................................
  shy.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week. Better than the week before.
    >>  ............................................
    pt  Pior que semana passada. Melhor que a retrasada.
    >>  ............................................
  shy.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you.
    >>  ............................................
    pt  Perguntaram por você.
    >>  ............................................
  upbeat.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing! Which at three weeks is genuinely the whole of what you can ask for.
    >>  ............................................
    pt  De pé! O que com três semanas é sinceramente tudo que se pode pedir.
    >>  ............................................
  upbeat.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves, apparently.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas, ao que parece.
    >>  ............................................
  upbeat.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually! I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade! Eu disse que você vinha perguntando.
    >>  ............................................
  witty.dialogue.conversations.news.resume.ask_after_them_now/1
    en  Standing! Which at three weeks is genuinely the whole of what you can ask for.
    >>  ............................................
    pt  De pé! O que com três semanas é sinceramente tudo que se pode pedir.
    >>  ............................................
  witty.dialogue.conversations.news.resume.ask_after_them_now/2
    en  Worse than last week and better than the week before. It goes in waves, apparently.
    >>  ............................................
    pt  Pior que semana passada e melhor que a retrasada. Vai em ondas, ao que parece.
    >>  ............................................
  witty.dialogue.conversations.news.resume.ask_after_them_now/3
    en  They asked after you, actually! I said you'd been asking after them.
    >>  ............................................
    pt  Eles perguntaram por você, na verdade! Eu disse que você vinha perguntando.
    >>  ............................................
```

</details>


### Button `anything_else` — "Is there anything else they need?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `news.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.resume.anything_else` — accepted phrasings: "is there anything else they need"; "what else do they need"; "can i do anything more for them"
  - scored words: `anything`(0.5), `else`(0.8), `need`(0.5)

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.respond.anything_else
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.respond.anything_else   [33 chars]
    en  Is there anything else they need?
    >>  ............................................
    pt  Precisam de mais alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.news.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.news.resume.anything_else
WHO    VILLAGER — what the player reads after pressing "Is there anything else they need?"
       spoken on: conversations.arc.news.resume.respond, button `anything_else`
       leaves the player on: conversations.arc.news.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.anything_else`: the villager request_helps. Subject `news.aftermath`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.resume.anything_else/1   [84 chars]
    en  Not things. They've had things from four households and no company from any of them.
    >>  ............................................
    pt  Coisas não. Receberam coisas de quatro casas e companhia de nenhuma delas.
    >>  ............................................
  dialogue.conversations.news.resume.anything_else/2   [76 chars]
    en  Someone to sit for an hour and not mention it. That's the hard one to fetch.
    >>  ............................................
    pt  Alguém pra sentar uma hora e não mencionar. Essa é a difícil de buscar.
    >>  ............................................
  dialogue.conversations.news.resume.anything_else/3   [87 chars]
    en  Not yet. Ask again after the frost, when the households have moved on and they haven't.
    >>  ............................................
    pt  Ainda não. Pergunte depois da geada, quando as casas seguiram em frente e eles não.
    >>  ............................................
```


### Button `glad_it_helped` — "I'm glad it helped."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `news.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.resume.glad_it_helped` — accepted phrasings: "i am glad it helped"; "good that it helped"; "i am pleased it was some use"
  - the message must contain one of: `helped`
  - scored words: `glad`(0.5), `helped`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.respond.glad_it_helped
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.respond.glad_it_helped   [19 chars]
    en  I'm glad it helped.
    >>  ............................................
    pt  Fico feliz que ajudou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.resume.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `news.resume.glad_it_helped`)_
- Does: session `turn`
- Then opens: `conversations.arc.news.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.news.resume.glad_it_helped
WHO    VILLAGER — what the player reads after pressing "I'm glad it helped."
       spoken on: conversations.arc.news.resume.respond, button `glad_it_helped`
       leaves the player on: conversations.arc.news.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.glad_it_helped`: the villager accepts. Subject `news.aftermath`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.resume.glad_it_helped/1   [54 chars]
    en  It did. Not enough, which is what help is, and it did.
    >>  ............................................
    pt  Ajudou. Não o bastante, que é o que ajuda é, e ajudou.
    >>  ............................................
  dialogue.conversations.news.resume.glad_it_helped/2   [83 chars]
    en  So am I. I'd told them somebody would come and I'd said it hopefully, not honestly.
    >>  ............................................
    pt  Eu também. Eu tinha dito a eles que alguém viria, e disse com esperança, não com certeza.
    >>  ............................................
  dialogue.conversations.news.resume.glad_it_helped/3   [78 chars]
    en  It helped. Say that to yourself again in a month when it feels like it didn't.
    >>  ............................................
    pt  Ajudou. Repita isso pra si em um mês, quando parecer que não ajudou.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.news.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.news.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.news.resume.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.news.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.news.resume.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.leave`: the villager accepts. Subject `news.aftermath`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.news.resume.followup / leave
```

> Written out in full under **`conversations.arc.news.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.news.followup`

**Reached from 4 route(s):** `conversations.scene.news.quiet_week.respond` / `ask_about_the_small_things`; `conversations.scene.news.quiet_week.respond` / `appreciate_the_quiet`; `conversations.scene.news.while_you_were_away.respond` / `ask_the_two_things`; `conversations.scene.news.while_you_were_away.respond` / `say_you_missed_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.news.quiet_week.agreed` — e.g. "It is, and it takes about thirty years to believe that. Ask anybody under twenty and they will tell you it is a tragedy."
- `conversations.scene.news.quiet_week.obliged` — e.g. "The baker has moved his table nine inches to the left and is pretending nobody has noticed. Four people have noticed."
- `conversations.scene.news.while_you_were_away.softened` — e.g. "You have, and I am not going to make you feel worse about it, because you came back and that is the part that counts."
- `conversations.scene.news.while_you_were_away.told` — e.g. "The bridge footing got dug properly after an argument, and a house at the top of the lane has a new roof and a new debt."


```text
POOL   dialogue key: dialogue.conversations.scene.news.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.news.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.news.followup   [24 chars]
    en  Anything else happening?
    >>  ............................................
    pt  Mais alguma coisa acontecendo?
    >>  ............................................
```


### Button `leave` — "That is the news, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:news.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.news.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.followup.leave   [23 chars]
    en  That is the news, then.
    >>  ............................................
    pt  São essas as novidades, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.news.leaving
WHO    VILLAGER — what the player reads after pressing "That is the news, then."
       spoken on: conversations.scene.news.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.scene.leaving`: the villager accepts. Subject `news.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.news.quiet_week.respond / leave; conversations.scene.news.while_you_were_away.respond / leave
```

```text
  dialogue.conversations.scene.news.leaving/1   [19 chars]
    en  That is all I have.
    >>  ............................................
    pt  É tudo o que eu tenho.
    >>  ............................................
  dialogue.conversations.scene.news.leaving/2   [35 chars]
    en  You will hear the rest at the well.
    >>  ............................................
    pt  Você ouve o resto no poço.
    >>  ............................................
  dialogue.conversations.scene.news.leaving/3   [24 chars]
    en  Right. That is the week.
    >>  ............................................
    pt  Certo. É a semana.
    >>  ............................................
```

---


## `conversations.scene.news.quiet_week.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.news.quiet_week` — e.g. "Since yesterday? A cat got into the granary and lost the argument. That is the whole of it."


```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.news.quiet_week.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.news.quiet_week.respond   [13 chars]
    en  Anything new?
    >>  ............................................
    pt  Alguma novidade?
    >>  ............................................
```


### Button `ask_about_the_small_things` — "Tell me a small thing, then."

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `news.quiet_week.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.news.quiet_week.ask_about_the_small_things` — accepted phrasings: "tell me a small thing then"; "tell me a small thing then"; "give me something small"
  - the message must contain one of: `small`, `something`
  - scored words: `small`(1.8), `something`(1.8), `tell`(0.8), `thing`(0.8), `give`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week.respond.ask_about_the_small_things
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.quiet_week.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.quiet_week.respond.ask_about_the_small_things   [28 chars]
    en  Tell me a small thing, then.
    >>  ............................................
    pt  Então me conte uma coisinha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `news.nothing_much`)_
- Does: session `turn`
- Then opens: `conversations.scene.news.followup`
- …where the player's next choices will be: "That is the news, then."

```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week.obliged
WHO    VILLAGER — what the player reads after pressing "Tell me a small thing, then."
       spoken on: conversations.scene.news.quiet_week.respond, button `ask_about_the_small_things`
       leaves the player on: conversations.scene.news.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.quiet_week.open.obliged`: the villager reports. Subject `news.nothing_much`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.news.quiet_week.obliged/1   [117 chars]
    en  The baker has moved his table nine inches to the left and is pretending nobody has noticed. Four people have noticed.
    >>  ............................................
    pt  O padeiro moveu a mesa vinte centímetros para a esquerda e finge que ninguém reparou. Quatro pessoas repararam.
    >>  ............................................
  dialogue.conversations.scene.news.quiet_week.obliged/2   [131 chars]
    en  There is a bird nesting in the bell housing. Nobody will ring it now until the chicks are out, which is a policy decided by nobody.
    >>  ............................................
    pt  Tem um pássaro fazendo ninho no sino. Ninguém vai tocar até os filhotes saírem, uma política decidida por ninguém.
    >>  ............................................
  dialogue.conversations.scene.news.quiet_week.obliged/3   [115 chars]
    en  Somebody left an apple on the step of the house at the end. Third time this month. I have theories and no evidence.
    >>  ............................................
    pt  Alguém deixou uma maçã no degrau da casa do fim. Terceira vez este mês. Tenho teorias e nenhuma prova.
    >>  ............................................
```


### Button `appreciate_the_quiet` — "A dull week is a good week."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `news.quiet_week.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.news.quiet_week.appreciate_the_quiet` — accepted phrasings: "a dull week is a good week"; "a dull week is a good week"; "an uneventful week is worth having"
  - the message must contain one of: `dull`, `uneventful`, `week`
  - scored words: `dull`(1.8), `uneventful`(1.8), `week`(1.8), `good`(0.8), `worth`(0.8), `having`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week.respond.appreciate_the_quiet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.quiet_week.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.quiet_week.respond.appreciate_the_quiet   [27 chars]
    en  A dull week is a good week.
    >>  ............................................
    pt  Uma semana sem graça é uma boa semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `news.nothing_much`)_
- Does: session `turn`
- Then opens: `conversations.scene.news.followup`
- …where the player's next choices will be: "That is the news, then."

```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week.agreed
WHO    VILLAGER — what the player reads after pressing "A dull week is a good week."
       spoken on: conversations.scene.news.quiet_week.respond, button `appreciate_the_quiet`
       leaves the player on: conversations.scene.news.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.quiet_week.open.agreed`: the villager accepts. Subject `news.nothing_much`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.news.quiet_week.agreed/1   [120 chars]
    en  It is, and it takes about thirty years to believe that. Ask anybody under twenty and they will tell you it is a tragedy.
    >>  ............................................
    pt  É, e leva uns trinta anos para acreditar nisso. Pergunte a qualquer um com menos de vinte e vão dizer que é uma tragédia.
    >>  ............................................
  dialogue.conversations.scene.news.quiet_week.agreed/2   [95 chars]
    en  The years people talk about are the bad ones. Nobody sings about a decade where the roofs held.
    >>  ............................................
    pt  Os anos de que as pessoas falam são os ruins. Ninguém canta sobre uma década em que os telhados aguentaram.
    >>  ............................................
  dialogue.conversations.scene.news.quiet_week.agreed/3   [84 chars]
    en  I shall write it in the register as ordinary, which is the entry I most like making.
    >>  ............................................
    pt  Vou anotar no registro como comum, que é a entrada que eu mais gosto de fazer.
    >>  ............................................
```


### Button `leave` — "Thanks for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `news.quiet_week.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.quiet_week.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.quiet_week.respond.leave   [22 chars]
    en  Thanks for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.news.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for telling me."
       spoken on: conversations.scene.news.quiet_week.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.scene.leaving`: the villager accepts. Subject `news.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.news.followup / leave; conversations.scene.news.while_you_were_away.respond / leave
```

> Written out in full under **`conversations.scene.news.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.news.while_you_were_away.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.news.while_you_were_away` — e.g. "A fair bit, and none of it enormous, which is the best kind of week to have missed."


```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.news.while_you_were_away.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.news.while_you_were_away.respond   [17 chars]
    en  What I've missed.
    >>  ............................................
    pt  O que eu perdi.
    >>  ............................................
```


### Button `ask_the_two_things` — "Start with the two worth telling."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `news.while_you_were_away.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.news.while_you_were_away.ask_the_two_things` — accepted phrasings: "start with the two worth telling"; "start with the two worth telling"; "tell me the important ones"
  - the message must contain one of: `worth`, `important`, `two`
  - scored words: `worth`(1.8), `important`(1.8), `two`(1.8), `start`(0.8), `telling`(0.8), `tell`(0.8), `ones`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away.respond.ask_the_two_things
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.while_you_were_away.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.while_you_were_away.respond.ask_the_two_things   [33 chars]
    en  Start with the two worth telling.
    >>  ............................................
    pt  Comece pelas duas que valem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `news.absence`)_
- Does: session `turn`
- Then opens: `conversations.scene.news.followup`
- …where the player's next choices will be: "That is the news, then."

```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away.told
WHO    VILLAGER — what the player reads after pressing "Start with the two worth telling."
       spoken on: conversations.scene.news.while_you_were_away.respond, button `ask_the_two_things`
       leaves the player on: conversations.scene.news.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.while_you_were_away.open.told`: the villager reports. Subject `news.absence`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.news.while_you_were_away.told/1   [120 chars]
    en  The bridge footing got dug properly after an argument, and a house at the top of the lane has a new roof and a new debt.
    >>  ............................................
    pt  A fundação da ponte foi cavada direito depois de uma discussão, e uma casa no fim da viela tem telhado novo e dívida nova.
    >>  ............................................
  dialogue.conversations.scene.news.while_you_were_away.told/2   [110 chars]
    en  Somebody left and somebody came back, and it is the same family, and I will let them tell you which way round.
    >>  ............................................
    pt  Alguém foi embora e alguém voltou, e é a mesma família, e vou deixar que eles contem a você qual foi qual.
    >>  ............................................
  dialogue.conversations.scene.news.while_you_were_away.told/3   [119 chars]
    en  One good thing and one that will be a problem in the spring. I will give you the good one first because I am not cruel.
    >>  ............................................
    pt  Uma coisa boa e uma que vai virar problema na primavera. Vou te dar a boa primeiro porque eu não sou cruel.
    >>  ............................................
```


### Button `say_you_missed_it` — "I've been away too long."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `news.while_you_were_away.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.news.while_you_were_away.say_you_missed_it` — accepted phrasings: "ive been away too long"; "i have been away too long"; "it has been too long since i was here"
  - the message must contain one of: `away`, `long`
  - scored words: `away`(1.8), `long`(1.8), `ive`(0.8), `been`(0.8), `too`(0.8), `since`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away.respond.say_you_missed_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.while_you_were_away.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.while_you_were_away.respond.say_you_missed_it   [24 chars]
    en  I've been away too long.
    >>  ............................................
    pt  Fiquei fora tempo demais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, familiarity +2  _(recorded under topic `news.absence`)_
- Does: session `turn`
- Then opens: `conversations.scene.news.followup`
- …where the player's next choices will be: "That is the news, then."

```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away.softened
WHO    VILLAGER — what the player reads after pressing "I've been away too long."
       spoken on: conversations.scene.news.while_you_were_away.respond, button `say_you_missed_it`
       leaves the player on: conversations.scene.news.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.while_you_were_away.open.softened`: the villager accepts. Subject `news.absence`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.news.while_you_were_away.softened/1   [117 chars]
    en  You have, and I am not going to make you feel worse about it, because you came back and that is the part that counts.
    >>  ............................................
    pt  Ficou, e eu não vou fazer você se sentir pior, porque você voltou e é essa a parte que conta.
    >>  ............................................
  dialogue.conversations.scene.news.while_you_were_away.softened/2   [87 chars]
    en  A village keeps things for people who come back. That is most of what a village is for.
    >>  ............................................
    pt  Uma vila guarda coisas para quem volta. É quase tudo o que uma vila serve para fazer.
    >>  ............................................
  dialogue.conversations.scene.news.while_you_were_away.softened/3   [110 chars]
    en  It is easier to say than I expected. Two people asked after you, and I told them I did not know, and now I do.
    >>  ............................................
    pt  É mais fácil dizer do que eu esperava. Duas pessoas perguntaram por você, e eu disse que não sabia, e agora sei.
    >>  ............................................
```


### Button `leave` — "Thanks for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `news.while_you_were_away.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.news.while_you_were_away.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.news.while_you_were_away.respond.leave   [22 chars]
    en  Thanks for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.news.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for telling me."
       spoken on: conversations.scene.news.while_you_were_away.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.scene.leaving`: the villager accepts. Subject `news.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.news.followup / leave; conversations.scene.news.quiet_week.respond / leave
```

> Written out in full under **`conversations.scene.news.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.news.callous.followup`

**Reached from 1 route(s):** `conversations.topic.news.sad.respond` / `amused`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.sad.amused` — e.g. "...Get away from me."


```text
POOL   dialogue key: dialogue.conversations.topic.news.callous.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.callous.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.callous.followup   [28 chars]
    en  Don't make me hear it twice.
    >>  ............................................
    pt  Não me faça ouvir isso duas vezes.
    >>  ............................................
```


### Button `apologize` — "That was unforgivable. I'm sorry."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `news.grave.mocked` · offered only once the villager has actually said `player:laughed_at_death`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.callous.apologize` — accepted phrasings: "that was unforgivable, i am sorry"; "that was a cruel thing to say"; "i should never have said that"
  - the message must contain one of: `unforgivable`, `cruel`, `sorry`
  - scored words: `unforgivable`(1.5), `cruel`(1.2), `sorry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.callous.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.callous.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.callous.followup.apologize   [33 chars]
    en  That was unforgivable. I'm sorry.
    >>  ............................................
    pt  Isso foi imperdoável. Me desculpe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `news.callous.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.callous.apologize
WHO    VILLAGER — what the player reads after pressing "That was unforgivable. I'm sorry."
       spoken on: conversations.topic.news.callous.followup, button `apologize`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.callous.apologize`: the villager qualifys. Subject `news.repair`, polarity `acute`, guarded, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.callous.apologize/1   [66 chars]
    en  ...It was close to it. Go on. I'd rather not look at you just now.
    >>  ............................................
    pt  ...Foi quase isso. Pode ir. Prefiro não olhar pra você agora.
    >>  ............................................
  dialogue.conversations.news.callous.apologize/2   [67 chars]
    en  Say it to the family and I'll think better of you, %1$s. Not to me.
    >>  ............................................
    pt  Diga isso à família e eu penso melhor de você, %1$s. Não a mim.
    >>  ............................................
  dialogue.conversations.news.callous.apologize/3   [55 chars]
    en  Just so. Take it away with you and don't bring it back.
    >>  ............................................
    pt  Pois é. Leve isso embora e não traga de volta.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.grave.mocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.callous.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.callous.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.callous.followup.leave   [8 chars]
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
POOL   dialogue key: dialogue.conversations.news.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.news.callous.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.left`: the villager accepts. Subject `news.aftermath`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.deflated.followup / leave; conversations.topic.news.glad.followup / leave; conversations.topic.news.grave.followup / leave; conversations.topic.news.mixed.followup / leave
```

```text
  dialogue.conversations.news.followup.leave/1   [47 chars]
    en  True enough. Enough of other people's business.
    >>  ............................................
    pt  Bem verdade. Chega de assunto dos outros.
    >>  ............................................
  dialogue.conversations.news.followup.leave/2   [18 chars]
    en  We'll speak again.
    >>  ............................................
    pt  A gente se fala.
    >>  ............................................
  dialogue.conversations.news.followup.leave/3   [27 chars]
    en  That'll do for today, %1$s.
    >>  ............................................
    pt  Por hoje está bom, %1$s.
    >>  ............................................
```

---


## `conversations.topic.news.deflated.followup`

**Reached from 1 route(s):** `conversations.topic.news.glad.respond` / `sour`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.glad.sour` — e.g. "...Must you. Let it be good for a week."


```text
POOL   dialogue key: dialogue.conversations.topic.news.deflated.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.deflated.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.deflated.followup   [26 chars]
    en  Let it be good for a week.
    >>  ............................................
    pt  Deixe ser bom por uma semana.
    >>  ............................................
```


### Button `apologize` — "You're right. Let it be good."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `news.glad.soured` · offered only once the villager has actually said `player:soured_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.deflated.apologize` — accepted phrasings: "you are right, let it be good"; "i did not mean to spoil it"; "sorry, i will not spoil it"
  - the message must contain one of: `right`, `sorry`, `spoil`
  - scored words: `right`(1.0), `sorry`(1.2), `spoil`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.news.deflated.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.deflated.followup.apologize   [29 chars]
    en  You're right. Let it be good.
    >>  ............................................
    pt  Você tem razão. Deixa ser bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3, warmth +1  _(recorded under topic `news.deflated.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.deflated.apologize
WHO    VILLAGER — what the player reads after pressing "You're right. Let it be good."
       spoken on: conversations.topic.news.deflated.followup, button `apologize`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.deflated.apologize`: the villager qualifys. Subject `news.repair`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.deflated.apologize/1   [50 chars]
    en  Thank you. It'll be over soon enough without help.
    >>  ............................................
    pt  Obrigado. Vai acabar logo sem ajuda.
    >>  ............................................
  dialogue.conversations.news.deflated.apologize/2   [56 chars]
    en  So it is. There's little enough of it, %1$s. Let it run.
    >>  ............................................
    pt  É assim mesmo. Tem pouco disso, %1$s. Deixa correr.
    >>  ............................................
  dialogue.conversations.news.deflated.apologize/3   [63 chars]
    en  ...Good. Now say something cheerful and we'll both forget that.
    >>  ............................................
    pt  ...Bom. Agora diga algo alegre e a gente esquece isso.
    >>  ............................................
```


### Button `let_it_be` — "Understood. I'll say no more."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `news.glad.soured`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.deflated.let_it_be` — accepted phrasings: "understood, i will say no more"; "i will keep quiet about it"; "enough said"
  - the message must contain one of: `understood`, `quiet`, `enough`
  - scored words: `understood`(1.2), `quiet`(1.2), `enough`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.deflated.followup.let_it_be
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.deflated.followup.let_it_be   [29 chars]
    en  Understood. I'll say no more.
    >>  ............................................
    pt  Entendido. Não digo mais nada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `news.deflated.let_it_be`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.deflated.let_it_be
WHO    VILLAGER — what the player reads after pressing "Understood. I'll say no more."
       spoken on: conversations.topic.news.deflated.followup, button `let_it_be`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.deflated.let_it_be`: the villager accepts. Subject `news.repair`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.deflated.let_it_be/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.news.deflated.let_it_be/2   [44 chars]
    en  That'll do, %1$s. It's a small thing to ask.
    >>  ............................................
    pt  Já basta, %1$s. É pouca coisa pra pedir.
    >>  ............................................
  dialogue.conversations.news.deflated.let_it_be/3   [57 chars]
    en  Right. Ask me in a month and I'll be as sour as you like.
    >>  ............................................
    pt  Certo. Me pergunte daqui a um mês e eu fico tão azedo quanto você quiser.
    >>  ............................................
```


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.glad.soured` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.deflated.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.deflated.followup.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.news.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.news.deflated.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.left`: the villager accepts. Subject `news.aftermath`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.callous.followup / leave; conversations.topic.news.glad.followup / leave; conversations.topic.news.grave.followup / leave; conversations.topic.news.mixed.followup / leave
```

> Written out in full under **`conversations.topic.news.callous.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.news.glad.followup`

**Reached from 2 route(s):** `conversations.topic.news.glad.respond` / `celebrate`; `conversations.topic.news.glad.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.glad.ask_more` — e.g. "Gladly. Sit down, this is the good version."
- `conversations.news.glad.celebrate` — e.g. "Isn't it! The whole square's been insufferable about it, in the best way."


```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.glad.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.glad.followup   [31 chars]
    en  It's been a good week for once.
    >>  ............................................
    pt  Foi uma boa semana, pra variar.
    >>  ............................................
```


### Button `celebrate_too` — "Then it's a good week for all of us."

*stance family `encouragement` · tone `playful` · outcome `appreciated` · answers the beat(s) `news.glad.shared`, `news.glad.told` · offered only once the villager has actually said `news:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.glad.celebrate_too` — accepted phrasings: "then it is a good week for all of us"; "a good week for everyone"; "that is good news for all of us"
  - the message must contain one of: `week`
  - scored words: `good`(0.6), `week`(1.2), `us`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.followup.celebrate_too
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.followup.celebrate_too   [36 chars]
    en  Then it's a good week for all of us.
    >>  ............................................
    pt  Então é uma boa semana pra todos nós.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `news.glad.celebrate_too`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4  _(recorded under topic `news.glad.celebrate_too`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.glad.celebrate_too
WHO    VILLAGER — what the player reads after pressing "Then it's a good week for all of us."
       spoken on: conversations.topic.news.glad.followup, button `celebrate_too`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.glad.celebrate_too`: the villager celebrates. Subject `news.good`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.glad.celebrate_too/1   [53 chars]
    en  It is. We're owed one and I intend to spend it badly.
    >>  ............................................
    pt  É sim. A gente merecia uma e eu pretendo gastar mal.
    >>  ............................................
  dialogue.conversations.news.glad.celebrate_too/2   [75 chars]
    en  Aye! Come to the square this evening, %1$s. Somebody's bound to be singing.
    >>  ............................................
    pt  É! Venha à praça hoje à noite, %1$s. Alguém vai estar cantando.
    >>  ............................................
  dialogue.conversations.news.glad.celebrate_too/3   [61 chars]
    en  That's the spirit. Good weeks are shared or they don't count.
    >>  ............................................
    pt  É esse o espírito. Boa semana se divide ou não conta.
    >>  ............................................
```


### Button `pass_it_on` — "I'll pass it on, gladly."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `news.glad.shared`, `news.glad.told` · offered only once the villager has actually said `news:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.glad.pass_it_on` — accepted phrasings: "i will pass it on gladly"; "i will spread that one"; "i will tell everyone i see"
  - the message must contain one of: `pass`, `gladly`
  - scored words: `pass`(1.5), `gladly`(1.5), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.followup.pass_it_on
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.followup.pass_it_on   [24 chars]
    en  I'll pass it on, gladly.
    >>  ............................................
    pt  Vou espalhar isso, com prazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.glad.pass_it_on`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `news.glad.pass_it_on`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.glad.pass_it_on
WHO    VILLAGER — what the player reads after pressing "I'll pass it on, gladly."
       spoken on: conversations.topic.news.glad.followup, button `pass_it_on`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.glad.pass_it_on`: the villager invites. Subject `news.good`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.glad.pass_it_on/1   [62 chars]
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Espalhe. É o único tipo de notícia que melhora quando é contada.
    >>  ............................................
  dialogue.conversations.news.glad.pass_it_on/2   [72 chars]
    en  Please. Half the village will hear it twice and be delighted both times.
    >>  ............................................
    pt  Por favor. Metade do vilarejo vai ouvir duas vezes e gostar das duas.
    >>  ............................................
  dialogue.conversations.news.glad.pass_it_on/3   [79 chars]
    en  Go on then, %1$s. Start with the baker; she'll want to bake something about it.
    >>  ............................................
    pt  Vá, %1$s. Comece pela padeira; ela vai querer assar alguma coisa por causa disso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, %1$s.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. There's been little enough of this sort lately.
    >>  ............................................
    pt  Passe adiante. Tem tido pouca coisa desse tipo ultimamente.
    >>  ............................................
  anxious.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll do somebody good today and I'd like to think it does.
    >>  ............................................
    pt  Passe. Vai fazer bem a alguém hoje e eu queria pensar que faz.
    >>  ............................................
  athletic.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, and it'll keep improving.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, e vai continuar melhorando.
    >>  ............................................
  athletic.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on, in your own time. Good news doesn't spoil.
    >>  ............................................
    pt  Passe adiante, no seu tempo. Boa notícia não estraga.
    >>  ............................................
  athletic.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll get round the village by Thursday and that's exactly right.
    >>  ............................................
    pt  Passe. Vai correr o vilarejo até quinta e está exatamente certo.
    >>  ............................................
  confident.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  confident.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Good news is the only sort that's better second-hand.
    >>  ............................................
    pt  Passe adiante. Boa notícia é a única que fica melhor em segunda mão.
    >>  ............................................
  confident.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And say where you heard it, so they know it's sound.
    >>  ............................................
    pt  Passe. E diga onde ouviu, pra saberem que é firme.
    >>  ............................................
  crabby.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  crabby.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Good news is the only sort that's better second-hand.
    >>  ............................................
    pt  Passe adiante. Boa notícia é a única que fica melhor em segunda mão.
    >>  ............................................
  crabby.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And say where you heard it, so they know it's sound.
    >>  ............................................
    pt  Passe. E diga onde ouviu, pra saberem que é firme.
    >>  ............................................
  extroverted.dialogue.conversations.news.glad.pass_it_on/1
    en  Do, %1$s. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe, %1$s. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  extroverted.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Tell the ones who've had a hard month first — they need it most.
    >>  ............................................
    pt  Passe adiante. Conte primeiro a quem teve um mês duro — precisam mais.
    >>  ............................................
  extroverted.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And come back and tell me who was most pleased about it.
    >>  ............................................
    pt  Passe. E volte pra me contar quem ficou mais contente.
    >>  ............................................
  flirty.dialogue.conversations.news.glad.pass_it_on/1
    en  Do, %1$s. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe, %1$s. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  flirty.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Tell the ones who've had a hard month first — they need it most.
    >>  ............................................
    pt  Passe adiante. Conte primeiro a quem teve um mês duro — precisam mais.
    >>  ............................................
  flirty.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And come back and tell me who was most pleased about it.
    >>  ............................................
    pt  Passe. E volte pra me contar quem ficou mais contente.
    >>  ............................................
  friendly.dialogue.conversations.news.glad.pass_it_on/1
    en  Do, %1$s. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe, %1$s. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  friendly.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Tell the ones who've had a hard month first — they need it most.
    >>  ............................................
    pt  Passe adiante. Conte primeiro a quem teve um mês duro — precisam mais.
    >>  ............................................
  friendly.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And come back and tell me who was most pleased about it.
    >>  ............................................
    pt  Passe. E volte pra me contar quem ficou mais contente.
    >>  ............................................
  gloomy.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, %1$s.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. There's been little enough of this sort lately.
    >>  ............................................
    pt  Passe adiante. Tem tido pouca coisa desse tipo ultimamente.
    >>  ............................................
  gloomy.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll do somebody good today and I'd like to think it does.
    >>  ............................................
    pt  Passe. Vai fazer bem a alguém hoje e eu queria pensar que faz.
    >>  ............................................
  greedy.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  greedy.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Good news is the only sort that's better second-hand.
    >>  ............................................
    pt  Passe adiante. Boa notícia é a única que fica melhor em segunda mão.
    >>  ............................................
  greedy.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And say where you heard it, so they know it's sound.
    >>  ............................................
    pt  Passe. E diga onde ouviu, pra saberem que é firme.
    >>  ............................................
  grumpy.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  grumpy.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. Good news is the only sort that's better second-hand.
    >>  ............................................
    pt  Passe adiante. Boa notícia é a única que fica melhor em segunda mão.
    >>  ............................................
  grumpy.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. And say where you heard it, so they know it's sound.
    >>  ............................................
    pt  Passe. E diga onde ouviu, pra saberem que é firme.
    >>  ............................................
  introverted.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  introverted.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. It's good news; it can go where it likes.
    >>  ............................................
    pt  Passe adiante. É boa notícia; pode ir aonde quiser.
    >>  ............................................
  introverted.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Say where you had it, if they ask.
    >>  ............................................
    pt  Passe. Diga onde ouviu, se perguntarem.
    >>  ............................................
  lazy.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, and it'll keep improving.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, e vai continuar melhorando.
    >>  ............................................
  lazy.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on, in your own time. Good news doesn't spoil.
    >>  ............................................
    pt  Passe adiante, no seu tempo. Boa notícia não estraga.
    >>  ............................................
  lazy.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll get round the village by Thursday and that's exactly right.
    >>  ............................................
    pt  Passe. Vai correr o vilarejo até quinta e está exatamente certo.
    >>  ............................................
  odd.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  odd.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. It's good news; it can go where it likes.
    >>  ............................................
    pt  Passe adiante. É boa notícia; pode ir aonde quiser.
    >>  ............................................
  odd.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Say where you had it, if they ask.
    >>  ............................................
    pt  Passe. Diga onde ouviu, se perguntarem.
    >>  ............................................
  peaceful.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, and it'll keep improving.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, e vai continuar melhorando.
    >>  ............................................
  peaceful.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on, in your own time. Good news doesn't spoil.
    >>  ............................................
    pt  Passe adiante, no seu tempo. Boa notícia não estraga.
    >>  ............................................
  peaceful.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll get round the village by Thursday and that's exactly right.
    >>  ............................................
    pt  Passe. Vai correr o vilarejo até quinta e está exatamente certo.
    >>  ............................................
  peppy.dialogue.conversations.news.glad.pass_it_on/1
    en  Do! This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe! É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  peppy.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on! Good news is the only sort that gets better second-hand and I'd know.
    >>  ............................................
    pt  Passe adiante! Boa notícia é a única que fica melhor em segunda mão e eu saberia.
    >>  ............................................
  peppy.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Tell everyone. Tell the miller twice — he'll enjoy it.
    >>  ............................................
    pt  Passe. Conte a todos. Conte ao moleiro duas vezes — ele vai gostar.
    >>  ............................................
  playful.dialogue.conversations.news.glad.pass_it_on/1
    en  Do! This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe! É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  playful.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on! Good news is the only sort that gets better second-hand and I'd know.
    >>  ............................................
    pt  Passe adiante! Boa notícia é a única que fica melhor em segunda mão e eu saberia.
    >>  ............................................
  playful.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Tell everyone. Tell the miller twice — he'll enjoy it.
    >>  ............................................
    pt  Passe. Conte a todos. Conte ao moleiro duas vezes — ele vai gostar.
    >>  ............................................
  relaxed.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, and it'll keep improving.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, e vai continuar melhorando.
    >>  ............................................
  relaxed.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on, in your own time. Good news doesn't spoil.
    >>  ............................................
    pt  Passe adiante, no seu tempo. Boa notícia não estraga.
    >>  ............................................
  relaxed.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll get round the village by Thursday and that's exactly right.
    >>  ............................................
    pt  Passe. Vai correr o vilarejo até quinta e está exatamente certo.
    >>  ............................................
  sensitive.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling, %1$s.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. There's been little enough of this sort lately.
    >>  ............................................
    pt  Passe adiante. Tem tido pouca coisa desse tipo ultimamente.
    >>  ............................................
  sensitive.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. It'll do somebody good today and I'd like to think it does.
    >>  ............................................
    pt  Passe. Vai fazer bem a alguém hoje e eu queria pensar que faz.
    >>  ............................................
  shy.dialogue.conversations.news.glad.pass_it_on/1
    en  Do. This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe. É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  shy.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on. It's good news; it can go where it likes.
    >>  ............................................
    pt  Passe adiante. É boa notícia; pode ir aonde quiser.
    >>  ............................................
  shy.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Say where you had it, if they ask.
    >>  ............................................
    pt  Passe. Diga onde ouviu, se perguntarem.
    >>  ............................................
  upbeat.dialogue.conversations.news.glad.pass_it_on/1
    en  Do! This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe! É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  upbeat.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on! Good news is the only sort that gets better second-hand and I'd know.
    >>  ............................................
    pt  Passe adiante! Boa notícia é a única que fica melhor em segunda mão e eu saberia.
    >>  ............................................
  upbeat.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Tell everyone. Tell the miller twice — he'll enjoy it.
    >>  ............................................
    pt  Passe. Conte a todos. Conte ao moleiro duas vezes — ele vai gostar.
    >>  ............................................
  witty.dialogue.conversations.news.glad.pass_it_on/1
    en  Do! This is the one kind of news that improves in the telling.
    >>  ............................................
    pt  Passe! É o único tipo de notícia que melhora ao ser contada.
    >>  ............................................
  witty.dialogue.conversations.news.glad.pass_it_on/2
    en  Pass it on! Good news is the only sort that gets better second-hand and I'd know.
    >>  ............................................
    pt  Passe adiante! Boa notícia é a única que fica melhor em segunda mão e eu saberia.
    >>  ............................................
  witty.dialogue.conversations.news.glad.pass_it_on/3
    en  Do. Tell everyone. Tell the miller twice — he'll enjoy it.
    >>  ............................................
    pt  Passe. Conte a todos. Conte ao moleiro duas vezes — ele vai gostar.
    >>  ............................................
```

</details>


### Button `ask_how_they_are` — "And how are you, with all that?"

*stance family `curiosity` · tone `gentle` · outcome `appreciated` · answers the beat(s) `news.glad.shared`, `news.glad.told` · offered only once the villager has actually said `news:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.glad.ask_how` — accepted phrasings: "and how are you finding it yourself"; "are you pleased about it yourself"; "and you, are you glad of it"
  - the message must contain one of: `yourself`, `pleased`, `glad`
  - scored words: `yourself`(1.5), `pleased`(1.5), `glad`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.followup.ask_how_they_are
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.followup.ask_how_they_are   [31 chars]
    en  And how are you, with all that?
    >>  ............................................
    pt  E você, como está com tudo isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.glad.ask_how_they_are`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +1, warmth +3  _(recorded under topic `news.asked_after`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.followup.ask_how_they_are
WHO    VILLAGER — what the player reads after pressing "And how are you, with all that?"
       spoken on: conversations.topic.news.glad.followup, button `ask_how_they_are`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.asked_after`: the villager discloses. Subject `news.aftermath`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.grave.followup / ask_how_they_are
```

```text
  dialogue.conversations.news.followup.ask_how_they_are/1   [79 chars]
    en  ...Me? I'm — thank you. I'm alright. And you? You get to answer that too, %1$s.
    >>  ............................................
    pt  ...Eu? Eu estou — obrigado. Estou bem. E você? Você também tem que responder isso, %1$s.
    >>  ............................................
  dialogue.conversations.news.followup.ask_how_they_are/2   [50 chars]
    en  You asked about me, not the story. That's unusual.
    >>  ............................................
    pt  Você perguntou de mim, não da história. Isso é incomum.
    >>  ............................................
  dialogue.conversations.news.followup.ask_how_they_are/3   [53 chars]
    en  Tired of carrying other people's news, if I'm honest.
    >>  ............................................
    pt  Cansado de carregar notícia dos outros, se for honesto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright, %1$s. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem, %1$s. E você? Você também responde isso.
    >>  ............................................
  anxious.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well. Nobody's asked me in a while, so give me a moment before you answer yours.
    >>  ............................................
    pt  Estou bem. Faz tempo que ninguém pergunta, então me dê um momento antes de você responder.
    >>  ............................................
  anxious.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, I think. It's odd to be asked. And you?
    >>  ............................................
    pt  Bem, eu acho. É estranho ser perguntado. E você?
    >>  ............................................
  athletic.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? That question goes both ways and it usually doesn't.
    >>  ............................................
    pt  Eu? Estou bem. E você? Essa pergunta vai nos dois sentidos e normalmente não vai.
    >>  ............................................
  athletic.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough, thank you. And yourself?
    >>  ............................................
    pt  Bem o bastante, obrigado. E você?
    >>  ............................................
  athletic.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, in the way that most weeks are fine. And you?
    >>  ............................................
    pt  Bem, do jeito que quase toda semana é bem. E você?
    >>  ............................................
  confident.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou bem. E você? Você também responde isso.
    >>  ............................................
  confident.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well enough. Your turn — I'll not let that go unanswered.
    >>  ............................................
    pt  Estou bem o bastante. Sua vez — não vou deixar isso sem resposta.
    >>  ............................................
  confident.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you, since we're doing this properly.
    >>  ............................................
    pt  Bem. E você, já que a gente está fazendo direito.
    >>  ............................................
  crabby.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou bem. E você? Você também responde isso.
    >>  ............................................
  crabby.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well enough. Your turn — I'll not let that go unanswered.
    >>  ............................................
    pt  Estou bem o bastante. Sua vez — não vou deixar isso sem resposta.
    >>  ............................................
  crabby.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you, since we're doing this properly.
    >>  ............................................
    pt  Bem. E você, já que a gente está fazendo direito.
    >>  ............................................
  extroverted.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And you? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E você? Você também responde isso, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well, and it's kind of you to ask. Now tell me about you, properly.
    >>  ............................................
    pt  Estou bem, e é gentil você perguntar. Agora me conte de você, direito.
    >>  ............................................
  extroverted.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Better for being asked. And you? Don't make me ask twice.
    >>  ............................................
    pt  Melhor por ter sido perguntado. E você? Não me faça perguntar duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And you? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E você? Você também responde isso, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well, and it's kind of you to ask. Now tell me about you, properly.
    >>  ............................................
    pt  Estou bem, e é gentil você perguntar. Agora me conte de você, direito.
    >>  ............................................
  flirty.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Better for being asked. And you? Don't make me ask twice.
    >>  ............................................
    pt  Melhor por ter sido perguntado. E você? Não me faça perguntar duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And you? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E você? Você também responde isso, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well, and it's kind of you to ask. Now tell me about you, properly.
    >>  ............................................
    pt  Estou bem, e é gentil você perguntar. Agora me conte de você, direito.
    >>  ............................................
  friendly.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Better for being asked. And you? Don't make me ask twice.
    >>  ............................................
    pt  Melhor por ter sido perguntado. E você? Não me faça perguntar duas vezes.
    >>  ............................................
  gloomy.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright, %1$s. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem, %1$s. E você? Você também responde isso.
    >>  ............................................
  gloomy.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well. Nobody's asked me in a while, so give me a moment before you answer yours.
    >>  ............................................
    pt  Estou bem. Faz tempo que ninguém pergunta, então me dê um momento antes de você responder.
    >>  ............................................
  gloomy.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, I think. It's odd to be asked. And you?
    >>  ............................................
    pt  Bem, eu acho. É estranho ser perguntado. E você?
    >>  ............................................
  greedy.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou bem. E você? Você também responde isso.
    >>  ............................................
  greedy.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well enough. Your turn — I'll not let that go unanswered.
    >>  ............................................
    pt  Estou bem o bastante. Sua vez — não vou deixar isso sem resposta.
    >>  ............................................
  greedy.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you, since we're doing this properly.
    >>  ............................................
    pt  Bem. E você, já que a gente está fazendo direito.
    >>  ............................................
  grumpy.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou bem. E você? Você também responde isso.
    >>  ............................................
  grumpy.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well enough. Your turn — I'll not let that go unanswered.
    >>  ............................................
    pt  Estou bem o bastante. Sua vez — não vou deixar isso sem resposta.
    >>  ............................................
  grumpy.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you, since we're doing this properly.
    >>  ............................................
    pt  Bem. E você, já que a gente está fazendo direito.
    >>  ............................................
  introverted.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you?
    >>  ............................................
    pt  Eu? Estou bem. E você?
    >>  ............................................
  introverted.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough. Your turn.
    >>  ............................................
    pt  Bem o bastante. Sua vez.
    >>  ............................................
  introverted.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you — you get to answer that too.
    >>  ............................................
    pt  Bem. E você — você também responde isso.
    >>  ............................................
  lazy.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? That question goes both ways and it usually doesn't.
    >>  ............................................
    pt  Eu? Estou bem. E você? Essa pergunta vai nos dois sentidos e normalmente não vai.
    >>  ............................................
  lazy.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough, thank you. And yourself?
    >>  ............................................
    pt  Bem o bastante, obrigado. E você?
    >>  ............................................
  lazy.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, in the way that most weeks are fine. And you?
    >>  ............................................
    pt  Bem, do jeito que quase toda semana é bem. E você?
    >>  ............................................
  odd.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you?
    >>  ............................................
    pt  Eu? Estou bem. E você?
    >>  ............................................
  odd.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough. Your turn.
    >>  ............................................
    pt  Bem o bastante. Sua vez.
    >>  ............................................
  odd.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you — you get to answer that too.
    >>  ............................................
    pt  Bem. E você — você também responde isso.
    >>  ............................................
  peaceful.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? That question goes both ways and it usually doesn't.
    >>  ............................................
    pt  Eu? Estou bem. E você? Essa pergunta vai nos dois sentidos e normalmente não vai.
    >>  ............................................
  peaceful.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough, thank you. And yourself?
    >>  ............................................
    pt  Bem o bastante, obrigado. E você?
    >>  ............................................
  peaceful.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, in the way that most weeks are fine. And you?
    >>  ............................................
    pt  Bem, do jeito que quase toda semana é bem. E você?
    >>  ............................................
  peppy.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And YOU? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E VOCÊ? Você também responde isso, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well! Which nobody has asked in a fortnight. Now you. Go on.
    >>  ............................................
    pt  Estou bem! O que ninguém perguntou em quinze dias. Agora você. Vá em frente.
    >>  ............................................
  peppy.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, thank you. Your turn, and no wriggling out of it.
    >>  ............................................
    pt  Bem, obrigado. Sua vez, e sem escapar.
    >>  ............................................
  playful.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And YOU? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E VOCÊ? Você também responde isso, %1$s.
    >>  ............................................
  playful.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well! Which nobody has asked in a fortnight. Now you. Go on.
    >>  ............................................
    pt  Estou bem! O que ninguém perguntou em quinze dias. Agora você. Vá em frente.
    >>  ............................................
  playful.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, thank you. Your turn, and no wriggling out of it.
    >>  ............................................
    pt  Bem, obrigado. Sua vez, e sem escapar.
    >>  ............................................
  relaxed.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you? That question goes both ways and it usually doesn't.
    >>  ............................................
    pt  Eu? Estou bem. E você? Essa pergunta vai nos dois sentidos e normalmente não vai.
    >>  ............................................
  relaxed.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough, thank you. And yourself?
    >>  ............................................
    pt  Bem o bastante, obrigado. E você?
    >>  ............................................
  relaxed.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, in the way that most weeks are fine. And you?
    >>  ............................................
    pt  Bem, do jeito que quase toda semana é bem. E você?
    >>  ............................................
  sensitive.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright, %1$s. And you? You get to answer that too.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem, %1$s. E você? Você também responde isso.
    >>  ............................................
  sensitive.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well. Nobody's asked me in a while, so give me a moment before you answer yours.
    >>  ............................................
    pt  Estou bem. Faz tempo que ninguém pergunta, então me dê um momento antes de você responder.
    >>  ............................................
  sensitive.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, I think. It's odd to be asked. And you?
    >>  ............................................
    pt  Bem, eu acho. É estranho ser perguntado. E você?
    >>  ............................................
  shy.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm alright. And you?
    >>  ............................................
    pt  Eu? Estou bem. E você?
    >>  ............................................
  shy.dialogue.conversations.news.followup.ask_how_they_are/2
    en  Well enough. Your turn.
    >>  ............................................
    pt  Bem o bastante. Sua vez.
    >>  ............................................
  shy.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine. And you — you get to answer that too.
    >>  ............................................
    pt  Bem. E você — você também responde isso.
    >>  ............................................
  upbeat.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And YOU? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E VOCÊ? Você também responde isso, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well! Which nobody has asked in a fortnight. Now you. Go on.
    >>  ............................................
    pt  Estou bem! O que ninguém perguntou em quinze dias. Agora você. Vá em frente.
    >>  ............................................
  upbeat.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, thank you. Your turn, and no wriggling out of it.
    >>  ............................................
    pt  Bem, obrigado. Sua vez, e sem escapar.
    >>  ............................................
  witty.dialogue.conversations.news.followup.ask_how_they_are/1
    en  Me? I'm — thank you. I'm alright. And YOU? You get to answer that too, %1$s.
    >>  ............................................
    pt  Eu? Estou — obrigado. Estou bem. E VOCÊ? Você também responde isso, %1$s.
    >>  ............................................
  witty.dialogue.conversations.news.followup.ask_how_they_are/2
    en  I'm well! Which nobody has asked in a fortnight. Now you. Go on.
    >>  ............................................
    pt  Estou bem! O que ninguém perguntou em quinze dias. Agora você. Vá em frente.
    >>  ............................................
  witty.dialogue.conversations.news.followup.ask_how_they_are/3
    en  Fine, thank you. Your turn, and no wriggling out of it.
    >>  ............................................
    pt  Bem, obrigado. Sua vez, e sem escapar.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.glad.shared`, `news.glad.told` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.followup.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.news.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.news.glad.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.left`: the villager accepts. Subject `news.aftermath`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.callous.followup / leave; conversations.topic.news.deflated.followup / leave; conversations.topic.news.grave.followup / leave; conversations.topic.news.mixed.followup / leave
```

> Written out in full under **`conversations.topic.news.callous.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.news.glad.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`


```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.glad.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.glad.respond   [20 chars]
    en  Good news, for once.
    >>  ............................................
    pt  Boa notícia, para variar.
    >>  ............................................
```


### Button `celebrate` — "That's wonderful news."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.glad.celebrate` — accepted phrasings: "that is wonderful news"; "brilliant"; "that is great news"
  - the message must contain one of: `wonderful`, `brilliant`, `great`
  - scored words: `wonderful`(1.5), `brilliant`(1.5), `great`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.respond.celebrate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.respond.celebrate   [22 chars]
    en  That's wonderful news.
    >>  ............................................
    pt  Que notícia maravilhosa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `news.glad.celebrate`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4  _(recorded under topic `news.glad.celebrate`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.glad.followup`
- …where the player's next choices will be: "Then it's a good week for all of us." | "I'll pass it on, gladly." | "And how are you, with all that?" | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.glad.celebrate
WHO    VILLAGER — what the player reads after pressing "That's wonderful news."
       spoken on: conversations.topic.news.glad.respond, button `celebrate`
       leaves the player on: conversations.topic.news.glad.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.glad.shared`: the villager celebrates. Subject `news.good`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `news:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.glad.celebrate/1   [73 chars]
    en  Isn't it! The whole square's been insufferable about it, in the best way.
    >>  ............................................
    pt  Né! A praça inteira está insuportável por causa disso, do melhor jeito.
    >>  ............................................
  dialogue.conversations.news.glad.celebrate/2   [40 chars]
    en  It is. We needed one of those, honestly.
    >>  ............................................
    pt  É. A gente precisava de uma dessas, sinceramente.
    >>  ............................................
  dialogue.conversations.news.glad.celebrate/3   [57 chars]
    en  Good news travels twice as fast here, and it deserves to.
    >>  ............................................
    pt  Boa notícia viaja duas vezes mais rápido aqui, e merece.
    >>  ............................................
```


### Button `ask_more` — "Tell me the whole story."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.glad.ask_more` — accepted phrasings: "tell me the whole story"; "tell me all of it"; "i want the whole story"
  - the message must contain one of: `story`, `whole`
  - scored words: `story`(1.5), `whole`(1.2), `all`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.respond.ask_more   [24 chars]
    en  Tell me the whole story.
    >>  ............................................
    pt  Me conta a história toda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `news.glad.ask_more`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.glad.followup`
- …where the player's next choices will be: "Then it's a good week for all of us." | "I'll pass it on, gladly." | "And how are you, with all that?" | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.glad.ask_more
WHO    VILLAGER — what the player reads after pressing "Tell me the whole story."
       spoken on: conversations.topic.news.glad.respond, button `ask_more`
       leaves the player on: conversations.topic.news.glad.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.glad.told`: the villager explains. Subject `news.good`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `news:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.glad.ask_more/1   [43 chars]
    en  Gladly. Sit down, this is the good version.
    >>  ............................................
    pt  Com prazer. Senta, essa é a versão boa.
    >>  ............................................
  dialogue.conversations.news.glad.ask_more/2   [90 chars]
    en  Ha! You'll get the long telling, then. Stop me when you've had enough — people usually do.
    >>  ............................................
    pt  Ha! Então você leva a versão longa. Me interrompa quando cansar — costuma acontecer.
    >>  ............................................
  dialogue.conversations.news.glad.ask_more/3   [42 chars]
    en  Everyone's got a piece of it. Here's mine.
    >>  ............................................
    pt  Todo mundo tem um pedaço dela. Aqui está o meu.
    >>  ............................................
```


### Button `sour` — "Won't last, these things."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.glad.sour` — accepted phrasings: "it will not last"; "these things never last"; "that will not last"
  - the message must contain one of: `last`, `wont`, `never`
  - scored words: `last`(1.5), `wont`(1.0), `never`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.respond.sour
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.respond.sour   [25 chars]
    en  Won't last, these things.
    >>  ............................................
    pt  Não vai durar, essas coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `news.glad.sour`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `news.glad.sour`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.deflated.followup`
- …where the player's next choices will be: "You're right. Let it be good." | "Understood. I'll say no more." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.glad.sour
WHO    VILLAGER — what the player reads after pressing "Won't last, these things."
       spoken on: conversations.topic.news.glad.respond, button `sour`
       leaves the player on: conversations.topic.news.deflated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.glad.soured`: the villager hurts. Subject `news.good`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `news:good`, `player:soured_it` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.glad.sour/1   [39 chars]
    en  ...Must you. Let it be good for a week.
    >>  ............................................
    pt  ...Precisa mesmo. Deixa ser bom por uma semana.
    >>  ............................................
  dialogue.conversations.news.glad.sour/2   [37 chars]
    en  Maybe not. But it's good today, %1$s.
    >>  ............................................
    pt  Talvez não. Mas hoje está bom, %1$s.
    >>  ............................................
  dialogue.conversations.news.glad.sour/3   [37 chars]
    en  That's a bleak way to take good news.
    >>  ............................................
    pt  É um jeito sombrio de receber uma boa notícia.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.glad.sour/1
    en  ...Must you. It was the first good thing in a month, %1$s.
    >>  ............................................
    pt  ...Precisava? Foi a primeira coisa boa em um mês, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.news.glad.sour/2
    en  I'd been holding on to that. Now I'm holding the other half instead.
    >>  ............................................
    pt  Eu estava me segurando nisso. Agora estou segurando a outra metade.
    >>  ............................................
  anxious.dialogue.conversations.news.glad.sour/3
    en  ...Right. Yes. There's always a catch. I know.
    >>  ............................................
    pt  ...Certo. Sim. Sempre tem um porém. Eu sei.
    >>  ............................................
  athletic.dialogue.conversations.news.glad.sour/1
    en  Must you. It'll sour on its own soon enough without help.
    >>  ............................................
    pt  Precisava? Vai azedar sozinho logo, sem ajuda.
    >>  ............................................
  athletic.dialogue.conversations.news.glad.sour/2
    en  ...Aye, likely. It's good today, though, and today's what I've got.
    >>  ............................................
    pt  ...É, provavelmente. Mas hoje é bom, e hoje é o que eu tenho.
    >>  ............................................
  athletic.dialogue.conversations.news.glad.sour/3
    en  Right. I'll let it be good until it isn't.
    >>  ............................................
    pt  Certo. Vou deixar ser bom até não ser.
    >>  ............................................
  confident.dialogue.conversations.news.glad.sour/1
    en  Must you. Let it be good for a week.
    >>  ............................................
    pt  Precisava? Deixe ser bom por uma semana.
    >>  ............................................
  confident.dialogue.conversations.news.glad.sour/2
    en  Right. One piece of good news and you've found the hole in it.
    >>  ............................................
    pt  Certo. Uma boa notícia e você achou o buraco nela.
    >>  ............................................
  confident.dialogue.conversations.news.glad.sour/3
    en  ...I'll enjoy it anyway, if that's permitted.
    >>  ............................................
    pt  ...Vou aproveitar mesmo assim, se for permitido.
    >>  ............................................
  crabby.dialogue.conversations.news.glad.sour/1
    en  Must you. Let it be good for a week.
    >>  ............................................
    pt  Precisava? Deixe ser bom por uma semana.
    >>  ............................................
  crabby.dialogue.conversations.news.glad.sour/2
    en  Right. One piece of good news and you've found the hole in it.
    >>  ............................................
    pt  Certo. Uma boa notícia e você achou o buraco nela.
    >>  ............................................
  crabby.dialogue.conversations.news.glad.sour/3
    en  ...I'll enjoy it anyway, if that's permitted.
    >>  ............................................
    pt  ...Vou aproveitar mesmo assim, se for permitido.
    >>  ............................................
  extroverted.dialogue.conversations.news.glad.sour/1
    en  ...Must you, %1$s. I was so pleased to be telling somebody.
    >>  ............................................
    pt  ...Precisava, %1$s? Eu estava tão contente de estar contando a alguém.
    >>  ............................................
  extroverted.dialogue.conversations.news.glad.sour/2
    en  That's not what I'd hoped to hear back from you.
    >>  ............................................
    pt  Não é o que eu esperava ouvir de volta de você.
    >>  ............................................
  extroverted.dialogue.conversations.news.glad.sour/3
    en  ...Right. I'll take the good bit and leave the rest with you.
    >>  ............................................
    pt  ...Certo. Vou ficar com a parte boa e deixar o resto com você.
    >>  ............................................
  flirty.dialogue.conversations.news.glad.sour/1
    en  ...Must you, %1$s. I was so pleased to be telling somebody.
    >>  ............................................
    pt  ...Precisava, %1$s? Eu estava tão contente de estar contando a alguém.
    >>  ............................................
  flirty.dialogue.conversations.news.glad.sour/2
    en  That's not what I'd hoped to hear back from you.
    >>  ............................................
    pt  Não é o que eu esperava ouvir de volta de você.
    >>  ............................................
  flirty.dialogue.conversations.news.glad.sour/3
    en  ...Right. I'll take the good bit and leave the rest with you.
    >>  ............................................
    pt  ...Certo. Vou ficar com a parte boa e deixar o resto com você.
    >>  ............................................
  friendly.dialogue.conversations.news.glad.sour/1
    en  ...Must you, %1$s. I was so pleased to be telling somebody.
    >>  ............................................
    pt  ...Precisava, %1$s? Eu estava tão contente de estar contando a alguém.
    >>  ............................................
  friendly.dialogue.conversations.news.glad.sour/2
    en  That's not what I'd hoped to hear back from you.
    >>  ............................................
    pt  Não é o que eu esperava ouvir de volta de você.
    >>  ............................................
  friendly.dialogue.conversations.news.glad.sour/3
    en  ...Right. I'll take the good bit and leave the rest with you.
    >>  ............................................
    pt  ...Certo. Vou ficar com a parte boa e deixar o resto com você.
    >>  ............................................
  gloomy.dialogue.conversations.news.glad.sour/1
    en  ...Must you. It was the first good thing in a month, %1$s.
    >>  ............................................
    pt  ...Precisava? Foi a primeira coisa boa em um mês, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.news.glad.sour/2
    en  I'd been holding on to that. Now I'm holding the other half instead.
    >>  ............................................
    pt  Eu estava me segurando nisso. Agora estou segurando a outra metade.
    >>  ............................................
  gloomy.dialogue.conversations.news.glad.sour/3
    en  ...Right. Yes. There's always a catch. I know.
    >>  ............................................
    pt  ...Certo. Sim. Sempre tem um porém. Eu sei.
    >>  ............................................
  greedy.dialogue.conversations.news.glad.sour/1
    en  Must you. Let it be good for a week.
    >>  ............................................
    pt  Precisava? Deixe ser bom por uma semana.
    >>  ............................................
  greedy.dialogue.conversations.news.glad.sour/2
    en  Right. One piece of good news and you've found the hole in it.
    >>  ............................................
    pt  Certo. Uma boa notícia e você achou o buraco nela.
    >>  ............................................
  greedy.dialogue.conversations.news.glad.sour/3
    en  ...I'll enjoy it anyway, if that's permitted.
    >>  ............................................
    pt  ...Vou aproveitar mesmo assim, se for permitido.
    >>  ............................................
  grumpy.dialogue.conversations.news.glad.sour/1
    en  Must you. Let it be good for a week.
    >>  ............................................
    pt  Precisava? Deixe ser bom por uma semana.
    >>  ............................................
  grumpy.dialogue.conversations.news.glad.sour/2
    en  Right. One piece of good news and you've found the hole in it.
    >>  ............................................
    pt  Certo. Uma boa notícia e você achou o buraco nela.
    >>  ............................................
  grumpy.dialogue.conversations.news.glad.sour/3
    en  ...I'll enjoy it anyway, if that's permitted.
    >>  ............................................
    pt  ...Vou aproveitar mesmo assim, se for permitido.
    >>  ............................................
  introverted.dialogue.conversations.news.glad.sour/1
    en  ...Must you.
    >>  ............................................
    pt  ...Precisava?
    >>  ............................................
  introverted.dialogue.conversations.news.glad.sour/2
    en  Let it be good a while.
    >>  ............................................
    pt  Deixe ser bom um pouco.
    >>  ............................................
  introverted.dialogue.conversations.news.glad.sour/3
    en  ...Right. I'll say nothing next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu não digo nada.
    >>  ............................................
  lazy.dialogue.conversations.news.glad.sour/1
    en  Must you. It'll sour on its own soon enough without help.
    >>  ............................................
    pt  Precisava? Vai azedar sozinho logo, sem ajuda.
    >>  ............................................
  lazy.dialogue.conversations.news.glad.sour/2
    en  ...Aye, likely. It's good today, though, and today's what I've got.
    >>  ............................................
    pt  ...É, provavelmente. Mas hoje é bom, e hoje é o que eu tenho.
    >>  ............................................
  lazy.dialogue.conversations.news.glad.sour/3
    en  Right. I'll let it be good until it isn't.
    >>  ............................................
    pt  Certo. Vou deixar ser bom até não ser.
    >>  ............................................
  odd.dialogue.conversations.news.glad.sour/1
    en  ...Must you.
    >>  ............................................
    pt  ...Precisava?
    >>  ............................................
  odd.dialogue.conversations.news.glad.sour/2
    en  Let it be good a while.
    >>  ............................................
    pt  Deixe ser bom um pouco.
    >>  ............................................
  odd.dialogue.conversations.news.glad.sour/3
    en  ...Right. I'll say nothing next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu não digo nada.
    >>  ............................................
  peaceful.dialogue.conversations.news.glad.sour/1
    en  Must you. It'll sour on its own soon enough without help.
    >>  ............................................
    pt  Precisava? Vai azedar sozinho logo, sem ajuda.
    >>  ............................................
  peaceful.dialogue.conversations.news.glad.sour/2
    en  ...Aye, likely. It's good today, though, and today's what I've got.
    >>  ............................................
    pt  ...É, provavelmente. Mas hoje é bom, e hoje é o que eu tenho.
    >>  ............................................
  peaceful.dialogue.conversations.news.glad.sour/3
    en  Right. I'll let it be good until it isn't.
    >>  ............................................
    pt  Certo. Vou deixar ser bom até não ser.
    >>  ............................................
  peppy.dialogue.conversations.news.glad.sour/1
    en  ...Must you! It was a lovely week and it lasted almost a whole minute.
    >>  ............................................
    pt  ...Precisava! Foi uma semana ótima e durou quase um minuto inteiro.
    >>  ............................................
  peppy.dialogue.conversations.news.glad.sour/2
    en  Right! Cloud located. Thank you, %1$s. Very thorough.
    >>  ............................................
    pt  Certo! Nuvem localizada. Obrigado, %1$s. Muito minucioso.
    >>  ............................................
  peppy.dialogue.conversations.news.glad.sour/3
    en  ...Ha. Fine. I'll be pleased about it privately.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar contente em particular.
    >>  ............................................
  playful.dialogue.conversations.news.glad.sour/1
    en  ...Must you! It was a lovely week and it lasted almost a whole minute.
    >>  ............................................
    pt  ...Precisava! Foi uma semana ótima e durou quase um minuto inteiro.
    >>  ............................................
  playful.dialogue.conversations.news.glad.sour/2
    en  Right! Cloud located. Thank you, %1$s. Very thorough.
    >>  ............................................
    pt  Certo! Nuvem localizada. Obrigado, %1$s. Muito minucioso.
    >>  ............................................
  playful.dialogue.conversations.news.glad.sour/3
    en  ...Ha. Fine. I'll be pleased about it privately.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar contente em particular.
    >>  ............................................
  relaxed.dialogue.conversations.news.glad.sour/1
    en  Must you. It'll sour on its own soon enough without help.
    >>  ............................................
    pt  Precisava? Vai azedar sozinho logo, sem ajuda.
    >>  ............................................
  relaxed.dialogue.conversations.news.glad.sour/2
    en  ...Aye, likely. It's good today, though, and today's what I've got.
    >>  ............................................
    pt  ...É, provavelmente. Mas hoje é bom, e hoje é o que eu tenho.
    >>  ............................................
  relaxed.dialogue.conversations.news.glad.sour/3
    en  Right. I'll let it be good until it isn't.
    >>  ............................................
    pt  Certo. Vou deixar ser bom até não ser.
    >>  ............................................
  sensitive.dialogue.conversations.news.glad.sour/1
    en  ...Must you. It was the first good thing in a month, %1$s.
    >>  ............................................
    pt  ...Precisava? Foi a primeira coisa boa em um mês, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.news.glad.sour/2
    en  I'd been holding on to that. Now I'm holding the other half instead.
    >>  ............................................
    pt  Eu estava me segurando nisso. Agora estou segurando a outra metade.
    >>  ............................................
  sensitive.dialogue.conversations.news.glad.sour/3
    en  ...Right. Yes. There's always a catch. I know.
    >>  ............................................
    pt  ...Certo. Sim. Sempre tem um porém. Eu sei.
    >>  ............................................
  shy.dialogue.conversations.news.glad.sour/1
    en  ...Must you.
    >>  ............................................
    pt  ...Precisava?
    >>  ............................................
  shy.dialogue.conversations.news.glad.sour/2
    en  Let it be good a while.
    >>  ............................................
    pt  Deixe ser bom um pouco.
    >>  ............................................
  shy.dialogue.conversations.news.glad.sour/3
    en  ...Right. I'll say nothing next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu não digo nada.
    >>  ............................................
  upbeat.dialogue.conversations.news.glad.sour/1
    en  ...Must you! It was a lovely week and it lasted almost a whole minute.
    >>  ............................................
    pt  ...Precisava! Foi uma semana ótima e durou quase um minuto inteiro.
    >>  ............................................
  upbeat.dialogue.conversations.news.glad.sour/2
    en  Right! Cloud located. Thank you, %1$s. Very thorough.
    >>  ............................................
    pt  Certo! Nuvem localizada. Obrigado, %1$s. Muito minucioso.
    >>  ............................................
  upbeat.dialogue.conversations.news.glad.sour/3
    en  ...Ha. Fine. I'll be pleased about it privately.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar contente em particular.
    >>  ............................................
  witty.dialogue.conversations.news.glad.sour/1
    en  ...Must you! It was a lovely week and it lasted almost a whole minute.
    >>  ............................................
    pt  ...Precisava! Foi uma semana ótima e durou quase um minuto inteiro.
    >>  ............................................
  witty.dialogue.conversations.news.glad.sour/2
    en  Right! Cloud located. Thank you, %1$s. Very thorough.
    >>  ............................................
    pt  Certo! Nuvem localizada. Obrigado, %1$s. Muito minucioso.
    >>  ............................................
  witty.dialogue.conversations.news.glad.sour/3
    en  ...Ha. Fine. I'll be pleased about it privately.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ficar contente em particular.
    >>  ............................................
```

</details>


### Button `leave` — "Good to hear. I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.glad.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.glad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.glad.respond.leave   [26 chars]
    en  Good to hear. I'll get on.
    >>  ............................................
    pt  Que bom saber. Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.glad.leave
WHO    VILLAGER — what the player reads after pressing "Good to hear. I'll get on."
       spoken on: conversations.topic.news.glad.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.glad.leave.terminal`: the villager accepts. Subject `news.good`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.glad.leave/1   [48 chars]
    en  Aye! Go and hear the rest from someone livelier.
    >>  ............................................
    pt  É! Vá ouvir o resto de alguém mais animado.
    >>  ............................................
  dialogue.conversations.news.glad.leave/2   [31 chars]
    en  Right you are. Good day for it.
    >>  ............................................
    pt  Isso mesmo. Bom dia para isso.
    >>  ............................................
  dialogue.conversations.news.glad.leave/3   [18 chars]
    en  Away you go, %1$s.
    >>  ............................................
    pt  Pode seguir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.news.grave.followup`

**Reached from 2 route(s):** `conversations.topic.news.sad.respond` / `compassion`; `conversations.topic.news.sad.respond` / `ask_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.sad.ask_more` — e.g. "There's not much more to it, and what there is I'd rather not."
- `conversations.news.sad.compassion` — e.g. "...Not well. Nobody is. Thank you for asking after them and not the details."


```text
POOL   dialogue key: dialogue.conversations.topic.news.grave.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.grave.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.grave.followup   [32 chars]
    en  That's all anyone needs to know.
    >>  ............................................
    pt  É tudo que alguém precisa saber.
    >>  ............................................
```


### Button `keep_quiet` — "I'll not repeat any of it."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `news.grave.borne`, `news.grave.withheld` · offered only once the villager has actually said `news:death`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.grave.keep_quiet` — accepted phrasings: "i will not breathe a word of it"; "not a word from me"; "i will repeat none of it"
  - the message must contain one of: `breathe`, `word`, `repeat`
  - scored words: `breathe`(1.5), `word`(1.5), `repeat`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.grave.followup.keep_quiet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.grave.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.grave.followup.keep_quiet   [26 chars]
    en  I'll not repeat any of it.
    >>  ............................................
    pt  Não vou repetir nada disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `news.grave.keep_quiet`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +5  _(recorded under topic `news.kept_quiet`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.followup.keep_quiet
WHO    VILLAGER — what the player reads after pressing "I'll not repeat any of it."
       spoken on: conversations.topic.news.grave.followup, button `keep_quiet`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.kept_quiet`: the villager accepts. Subject `news.aftermath`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.mixed.followup / keep_quiet
```

```text
  dialogue.conversations.news.followup.keep_quiet/1   [67 chars]
    en  ...Thank you. That's worth more than you know in a place this size.
    >>  ............................................
    pt  ...Obrigado. Isso vale mais do que você imagina num lugar deste tamanho.
    >>  ............................................
  dialogue.conversations.news.followup.keep_quiet/2   [34 chars]
    en  Good. I told you, not the village.
    >>  ............................................
    pt  Bom. Eu contei pra você, não pro vilarejo.
    >>  ............................................
  dialogue.conversations.news.followup.keep_quiet/3   [57 chars]
    en  I'll remember you said that, %1$s. People here rarely do.
    >>  ............................................
    pt  Vou lembrar que você disse isso, %1$s. Aqui pouca gente diz.
    >>  ............................................
```


### Button `ask_how_they_are` — "And how are you, with all that?"

*stance family `curiosity` · tone `gentle` · outcome `appreciated` · answers the beat(s) `news.grave.borne`, `news.grave.withheld` · offered only once the villager has actually said `news:death`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.grave.ask_how` — accepted phrasings: "and how are you bearing up"; "how are you carrying all that"; "and how are you yourself, with all that"
  - the message must contain one of: `bearing`, `yourself`, `carrying`
  - scored words: `bearing`(1.5), `yourself`(1.2), `carrying`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.grave.followup.ask_how_they_are
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.grave.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.grave.followup.ask_how_they_are   [31 chars]
    en  And how are you, with all that?
    >>  ............................................
    pt  E você, como está com tudo isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.grave.ask_how_they_are`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +1, warmth +3  _(recorded under topic `news.asked_after`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.followup.ask_how_they_are
WHO    VILLAGER — what the player reads after pressing "And how are you, with all that?"
       spoken on: conversations.topic.news.grave.followup, button `ask_how_they_are`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.asked_after`: the villager discloses. Subject `news.aftermath`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.glad.followup / ask_how_they_are
```

> Written out in full under **`conversations.topic.news.glad.followup` / button `ask_how_they_are`** earlier in this file. Fill it in there, once.


### Button `offer_help` — "Is there anything the family needs?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `news.grave.borne`, `news.grave.withheld` · offered only once the villager has actually said `village:grieving`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.grave.offer_help` — accepted phrasings: "is there anything the family needs"; "what does the family need"; "can i do anything for them"
  - the message must contain one of: `family`, `needs`
  - scored words: `family`(1.5), `needs`(1.2), `anything`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.news.grave.followup.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.grave.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.grave.followup.offer_help   [35 chars]
    en  Is there anything the family needs?
    >>  ............................................
    pt  A família precisa de alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.grave.offer_help`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `news.grave.offer_help`)_
- Does: arc `news` — advance
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.grave.offer_help
WHO    VILLAGER — what the player reads after pressing "Is there anything the family needs?"
       spoken on: conversations.topic.news.grave.followup, button `offer_help`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.grave.offer_help`: the villager request_helps. Subject `news.grave`, polarity `acute`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.grave.offer_help/1   [62 chars]
    en  ...Firewood, and nobody to fetch it. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. Essa é a resposta honesta.
    >>  ............................................
  dialogue.conversations.news.grave.offer_help/2   [68 chars]
    en  Somebody to sit with them who isn't crying. You'd manage that, %1$s.
    >>  ............................................
    pt  Alguém pra ficar com eles que não esteja chorando. Você daria conta, %1$s.
    >>  ............................................
  dialogue.conversations.news.grave.offer_help/3   [75 chars]
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que eles pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's a small thing and it's the whole of it.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É uma coisa pequena e é tudo.
    >>  ............................................
  anxious.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. I've tried and I'm no use at it.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Tentei e não sirvo pra isso.
    >>  ............................................
  anxious.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, and asking is beyond them just now.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, e pedir está além deles agora.
    >>  ............................................
  athletic.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's always firewood; it has been for forty years.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É sempre lenha; há quarenta anos é.
    >>  ............................................
  athletic.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. That's the rarest thing in a grieving house.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. É o mais raro numa casa de luto.
    >>  ............................................
  athletic.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take. Grief makes beggars of nobody, sadly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam. O luto não faz mendigo de ninguém, infelizmente.
    >>  ............................................
  confident.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta.
    >>  ............................................
  confident.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta.
    >>  ............................................
  confident.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  crabby.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta.
    >>  ............................................
  crabby.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta.
    >>  ............................................
  crabby.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  extroverted.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it, %1$s. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar, %1$s. É a resposta honesta.
    >>  ............................................
  extroverted.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, and few would.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, e poucos dariam.
    >>  ............................................
  extroverted.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  flirty.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it, %1$s. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar, %1$s. É a resposta honesta.
    >>  ............................................
  flirty.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, and few would.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, e poucos dariam.
    >>  ............................................
  flirty.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  friendly.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it, %1$s. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar, %1$s. É a resposta honesta.
    >>  ............................................
  friendly.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, and few would.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, e poucos dariam.
    >>  ............................................
  friendly.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  gloomy.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's a small thing and it's the whole of it.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É uma coisa pequena e é tudo.
    >>  ............................................
  gloomy.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. I've tried and I'm no use at it.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Tentei e não sirvo pra isso.
    >>  ............................................
  gloomy.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, and asking is beyond them just now.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, e pedir está além deles agora.
    >>  ............................................
  greedy.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta.
    >>  ............................................
  greedy.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta.
    >>  ............................................
  greedy.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  grumpy.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta.
    >>  ............................................
  grumpy.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta.
    >>  ............................................
  grumpy.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio.
    >>  ............................................
  introverted.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood. Nobody to fetch it.
    >>  ............................................
    pt  ...Lenha. Ninguém pra buscar.
    >>  ............................................
  introverted.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando.
    >>  ............................................
  introverted.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam.
    >>  ............................................
  lazy.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's always firewood; it has been for forty years.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É sempre lenha; há quarenta anos é.
    >>  ............................................
  lazy.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. That's the rarest thing in a grieving house.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. É o mais raro numa casa de luto.
    >>  ............................................
  lazy.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take. Grief makes beggars of nobody, sadly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam. O luto não faz mendigo de ninguém, infelizmente.
    >>  ............................................
  odd.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood. Nobody to fetch it.
    >>  ............................................
    pt  ...Lenha. Ninguém pra buscar.
    >>  ............................................
  odd.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando.
    >>  ............................................
  odd.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam.
    >>  ............................................
  peaceful.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's always firewood; it has been for forty years.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É sempre lenha; há quarenta anos é.
    >>  ............................................
  peaceful.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. That's the rarest thing in a grieving house.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. É o mais raro numa casa de luto.
    >>  ............................................
  peaceful.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take. Grief makes beggars of nobody, sadly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam. O luto não faz mendigo de ninguém, infelizmente.
    >>  ............................................
  peppy.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer, unglamorous as it is.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta, por menos glamorosa que seja.
    >>  ............................................
  peppy.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, I think.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, acho.
    >>  ............................................
  peppy.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly enough.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio suficiente.
    >>  ............................................
  playful.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer, unglamorous as it is.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta, por menos glamorosa que seja.
    >>  ............................................
  playful.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, I think.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, acho.
    >>  ............................................
  playful.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly enough.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio suficiente.
    >>  ............................................
  relaxed.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's always firewood; it has been for forty years.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É sempre lenha; há quarenta anos é.
    >>  ............................................
  relaxed.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. That's the rarest thing in a grieving house.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. É o mais raro numa casa de luto.
    >>  ............................................
  relaxed.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take. Grief makes beggars of nobody, sadly.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam. O luto não faz mendigo de ninguém, infelizmente.
    >>  ............................................
  sensitive.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. It's a small thing and it's the whole of it.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É uma coisa pequena e é tudo.
    >>  ............................................
  sensitive.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. I've tried and I'm no use at it.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Tentei e não sirvo pra isso.
    >>  ............................................
  sensitive.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, and asking is beyond them just now.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, e pedir está além deles agora.
    >>  ............................................
  shy.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood. Nobody to fetch it.
    >>  ............................................
    pt  ...Lenha. Ninguém pra buscar.
    >>  ............................................
  shy.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando.
    >>  ............................................
  shy.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam.
    >>  ............................................
  upbeat.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer, unglamorous as it is.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta, por menos glamorosa que seja.
    >>  ............................................
  upbeat.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, I think.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, acho.
    >>  ............................................
  upbeat.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly enough.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio suficiente.
    >>  ............................................
  witty.dialogue.conversations.news.grave.offer_help/1
    en  ...Firewood, and nobody to fetch it. That's the honest answer, unglamorous as it is.
    >>  ............................................
    pt  ...Lenha, e ninguém pra buscar. É a resposta honesta, por menos glamorosa que seja.
    >>  ............................................
  witty.dialogue.conversations.news.grave.offer_help/2
    en  Somebody to sit with them who isn't crying. You'd manage that, I think.
    >>  ............................................
    pt  Alguém pra sentar com eles que não esteja chorando. Você daria conta, acho.
    >>  ............................................
  witty.dialogue.conversations.news.grave.offer_help/3
    en  Nothing they'd ask for. Everything they'd take, if it were offered quietly enough.
    >>  ............................................
    pt  Nada que pediriam. Tudo que aceitariam, se fosse oferecido em silêncio suficiente.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.grave.borne`, `news.grave.withheld` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.grave.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.grave.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.grave.followup.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.news.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.news.grave.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.left`: the villager accepts. Subject `news.aftermath`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.callous.followup / leave; conversations.topic.news.deflated.followup / leave; conversations.topic.news.glad.followup / leave; conversations.topic.news.mixed.followup / leave
```

> Written out in full under **`conversations.topic.news.callous.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.news.helped.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`


```text
POOL   dialogue key: dialogue.conversations.topic.news.helped.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.helped.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.helped.respond   [44 chars]
    en  Word gets round when somebody's been helped.
    >>  ............................................
    pt  A notícia corre quando alguém foi ajudado.
    >>  ............................................
```


### Button `ask_who` — "Who was it that helped?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.helped.ask_who` — accepted phrasings: "who was it that helped"; "who helped them"; "who lent a hand"
  - the message must contain one of: `helped`
  - scored words: `helped`(1.2), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.news.helped.respond.ask_who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.helped.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.helped.respond.ask_who   [23 chars]
    en  Who was it that helped?
    >>  ............................................
    pt  Quem foi que ajudou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `news.helped.ask_who`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.helped.ask_who
WHO    VILLAGER — what the player reads after pressing "Who was it that helped?"
       spoken on: conversations.topic.news.helped.respond, button `ask_who`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.helped.ask_who.terminal`: the villager accepts. Subject `news.helped`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.helped.ask_who/1   [77 chars]
    en  Nobody will say outright. That's how you know it was done properly — quietly.
    >>  ............................................
    pt  Ninguém diz abertamente. É assim que se sabe que foi bem feito — em silêncio.
    >>  ............................................
  dialogue.conversations.news.helped.ask_who/2   [82 chars]
    en  Someone passing through, by the sound of it. They didn't stay to be thanked, %1$s.
    >>  ............................................
    pt  Alguém de passagem, pelo que parece. Não ficou para ser agradecido, %1$s.
    >>  ............................................
  dialogue.conversations.news.helped.ask_who/3   [75 chars]
    en  I've a guess. I'll keep it, though. Guessing out loud is how rumours start.
    >>  ............................................
    pt  Tenho um palpite. Mas guardo. Palpite em voz alta é como começa boato.
    >>  ............................................
```


### Button `glad` — "Good. That's how a village should work."

*stance family `candor` · tone `gentle` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.helped.glad` — accepted phrasings: "that is how a village should work"; "that is how a village ought to work"; "that is what neighbours are for"
  - the message must contain one of: `neighbours`
  - scored words: `neighbours`(1.2), `should`(0.5), `village`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.news.helped.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.helped.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.helped.respond.glad   [39 chars]
    en  Good. That's how a village should work.
    >>  ............................................
    pt  Que bom. É assim que uma vila devia funcionar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.helped.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +1  _(recorded under topic `news.helped.glad`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.helped.glad
WHO    VILLAGER — what the player reads after pressing "Good. That's how a village should work."
       spoken on: conversations.topic.news.helped.respond, button `glad`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.helped.glad.terminal`: the villager accepts. Subject `news.helped`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.helped.glad/1   [90 chars]
    en  It is. We're not much of a village if we only notice each other when something goes wrong.
    >>  ............................................
    pt  É. Não somos grande coisa como vila se só reparamos uns nos outros quando dá errado.
    >>  ............................................
  dialogue.conversations.news.helped.glad/2   [87 chars]
    en  Quite. One less thing hanging over somebody. That's a good week by our standards, %1$s.
    >>  ............................................
    pt  Exato. Uma coisa a menos pesando sobre alguém. Pelos nossos padrões é uma boa semana, %1$s.
    >>  ............................................
  dialogue.conversations.news.helped.glad/3   [83 chars]
    en  That's the whole of it, isn't it. Everyone takes a turn being the one who needs it.
    >>  ............................................
    pt  É isso mesmo, né. Todo mundo tem a sua vez de ser quem precisa.
    >>  ............................................
```


### Button `take_credit` — "That was me, actually."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.helped.take_credit` — accepted phrasings: "that was me actually"; "that was my doing"; "i was the one who did that"
  - the message must contain one of: `actually`
  - scored words: `actually`(1.2), `me`(0.4), `was`(0.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.helped.respond.take_credit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.helped.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.helped.respond.take_credit   [22 chars]
    en  That was me, actually.
    >>  ............................................
    pt  Fui eu, na verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `news.helped.take_credit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -3, tension +3  _(recorded under topic `news.helped.take_credit`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.helped.take_credit
WHO    VILLAGER — what the player reads after pressing "That was me, actually."
       spoken on: conversations.topic.news.helped.respond, button `take_credit`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.helped.take_credit.terminal`: the villager accepts. Subject `news.helped`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.helped.take_credit/1   [82 chars]
    en  ...Was it. Well. It'd have been a better story if I'd heard it from somebody else.
    >>  ............................................
    pt  ...Foi? Bom. Teria sido uma história melhor se eu tivesse ouvido de outra pessoa.
    >>  ............................................
  dialogue.conversations.news.helped.take_credit/2   [61 chars]
    en  Hm. I'd got most of the way to admiring whoever did it, %1$s.
    >>  ............................................
    pt  Hm. Eu já estava quase admirando quem quer que tivesse feito, %1$s.
    >>  ............................................
  dialogue.conversations.news.helped.take_credit/3   [70 chars]
    en  Quite, I'd half worked that out. You didn't have to help me finish it.
    >>  ............................................
    pt  Exato, eu já tinha meio que deduzido. Não precisava me ajudar a terminar.
    >>  ............................................
```


### Button `leave` — "Glad to hear it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.helped.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.helped.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.helped.respond.leave   [16 chars]
    en  Glad to hear it.
    >>  ............................................
    pt  Fico contente de saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.helped.leave
WHO    VILLAGER — what the player reads after pressing "Glad to hear it."
       spoken on: conversations.topic.news.helped.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.helped.leave.terminal`: the villager accepts. Subject `news.helped`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.helped.leave/1   [41 chars]
    en  It is. Off you go, and mind who you tell.
    >>  ............................................
    pt  É sim. Pode ir, e cuidado com quem você conta.
    >>  ............................................
  dialogue.conversations.news.helped.leave/2   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
  dialogue.conversations.news.helped.leave/3   [68 chars]
    en  Go on, then. It'll be round the whole village by morning regardless.
    >>  ............................................
    pt  Vai lá. De qualquer jeito estará na vila inteira até de manhã.
    >>  ............................................
```

---


## `conversations.topic.news.mixed.followup`

**Reached from 5 route(s):** `conversations.topic.news.mixed.respond` / `curious`; `conversations.topic.news.mixed.respond` / `wish_well`; `conversations.topic.news.mixed.respond` / `skeptical`; `conversations.topic.news.mixed.respond` / `skeptical`; `conversations.topic.news.mixed.respond` / `skeptical`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.news.mixed.curious` — e.g. "Nobody's quite sure. That's half of why it's being talked about."
- `conversations.news.mixed.skeptical.flat` — e.g. "Maybe there is. It's not ours to dig at."
- `conversations.news.mixed.skeptical.landed` — e.g. "There is. There always is. You've a good nose, %1$s."
- `conversations.news.mixed.skeptical.polite` — e.g. "Probably. There usually is."
- `conversations.news.mixed.wish_well` — e.g. "So do I. It's a strange thing, watching people go."


```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.mixed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.mixed.followup   [28 chars]
    en  Nobody knows the half of it.
    >>  ............................................
    pt  Ninguém sabe nem metade.
    >>  ............................................
```


### Button `keep_quiet` — "I'll not repeat any of it."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `news.mixed.guessed`, `news.mixed.wished_well`, `news.mixed.suspicion_shared`, `news.mixed.suspicion_checked`, `news.mixed.suspicion_allowed` · offered only once the villager has actually said `news:unclear`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.mixed.keep_quiet` — accepted phrasings: "i will not repeat any of it"; "it stays quiet with me"; "nobody will hear it from me"
  - the message must contain one of: `repeat`, `quiet`, `nobody`
  - scored words: `repeat`(1.5), `quiet`(1.2), `nobody`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.followup.keep_quiet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.followup.keep_quiet   [26 chars]
    en  I'll not repeat any of it.
    >>  ............................................
    pt  Não vou repetir nada disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `news.mixed.keep_quiet`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +5  _(recorded under topic `news.kept_quiet`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.followup.keep_quiet
WHO    VILLAGER — what the player reads after pressing "I'll not repeat any of it."
       spoken on: conversations.topic.news.mixed.followup, button `keep_quiet`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.kept_quiet`: the villager accepts. Subject `news.aftermath`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.grave.followup / keep_quiet
```

> Written out in full under **`conversations.topic.news.grave.followup` / button `keep_quiet`** earlier in this file. Fill it in there, once.


### Button `let_them_be` — "Whatever it is, it's theirs."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `news.mixed.guessed`, `news.mixed.wished_well`, `news.mixed.suspicion_shared`, `news.mixed.suspicion_checked`, `news.mixed.suspicion_allowed` · offered only once the villager has actually said `news:unclear`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.mixed.let_them_be` — accepted phrasings: "whatever it is, it is theirs"; "it is their business"; "that is their own affair"
  - the message must contain one of: `theirs`, `business`, `whatever`
  - scored words: `theirs`(1.5), `business`(1.5), `whatever`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.followup.let_them_be
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.followup.let_them_be   [28 chars]
    en  Whatever it is, it's theirs.
    >>  ............................................
    pt  Seja o que for, é assunto deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.mixed.let_them_be`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `news.mixed.let_them_be`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.let_them_be
WHO    VILLAGER — what the player reads after pressing "Whatever it is, it's theirs."
       spoken on: conversations.topic.news.mixed.followup, button `let_them_be`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.let_them_be`: the villager accepts. Subject `news.mixed`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.mixed.let_them_be/1   [64 chars]
    en  ...It is. That's the sentence this village never manages to say.
    >>  ............................................
    pt  ...É mesmo. É a frase que este vilarejo nunca consegue dizer.
    >>  ............................................
  dialogue.conversations.news.mixed.let_them_be/2   [71 chars]
    en  So it is. I'll try to remember that when the well starts talking, %1$s.
    >>  ............................................
    pt  É assim mesmo. Vou tentar lembrar disso quando o poço começar a falar, %1$s.
    >>  ............................................
  dialogue.conversations.news.mixed.let_them_be/3   [46 chars]
    en  Theirs. Right. I'll stop guessing at it, then.
    >>  ............................................
    pt  Deles. Certo. Então eu paro de tentar adivinhar.
    >>  ............................................
```


### Button `spread` — "Everyone should hear about this."

*stance family `boundary_push` · tone `blunt` · outcome `rebuffed` · answers the beat(s) `news.mixed.guessed`, `news.mixed.wished_well`, `news.mixed.suspicion_shared`, `news.mixed.suspicion_checked`, `news.mixed.suspicion_allowed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.spread_refused` — accepted phrasings: "everyone should hear about this"; "i will spread that around"; "people should know about this"
  - the message must contain one of: `everyone`, `hear`, `spread`
  - scored words: `everyone`(1.2), `hear`(1.2), `spread`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.followup.spread
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.followup.spread   [32 chars]
    en  Everyone should hear about this.
    >>  ............................................
    pt  Todo mundo devia saber disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `news.mixed.spread`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3, trust -4  _(recorded under topic `news.spread_refused`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.followup.spread
WHO    VILLAGER — what the player reads after pressing "Everyone should hear about this."
       spoken on: conversations.topic.news.mixed.followup, button `spread`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.spread_refused`: the villager refuses. Subject `news.spreading`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.followup.spread/1   [50 chars]
    en  ...Should they. It isn't ours to hand round, %1$s.
    >>  ............................................
    pt  ...Deviam mesmo. Não é nosso pra ficar distribuindo, %1$s.
    >>  ............................................
  dialogue.conversations.news.followup.spread/2   [50 chars]
    en  That's how it becomes something worse than it was.
    >>  ............................................
    pt  É assim que vira algo pior do que era.
    >>  ............................................
  dialogue.conversations.news.followup.spread/3   [50 chars]
    en  I'd rather it stopped here. Clearly you would not.
    >>  ............................................
    pt  Eu preferia que parasse aqui. Claramente você não.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours to hand round. They'd be hurt and it would be my doing, %1$s.
    >>  ............................................
    pt  ...Não é nossa pra distribuir. Eles se magoariam e seria obra minha, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.news.followup.spread/2
    en  Please don't. I've already said more than I should have.
    >>  ............................................
    pt  Por favor, não. Eu já disse mais do que devia.
    >>  ............................................
  anxious.dialogue.conversations.news.followup.spread/3
    en  ...Right. That was my mistake, telling you at all.
    >>  ............................................
    pt  ...Certo. O erro foi meu, ter contado.
    >>  ............................................
  athletic.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours, and news keeps better than people think.
    >>  ............................................
    pt  Deviam? Não é nossa, e notícia se conserva melhor do que se pensa.
    >>  ............................................
  athletic.dialogue.conversations.news.followup.spread/2
    en  ...No. It'll get where it's going without our help.
    >>  ............................................
    pt  ...Não. Vai chegar aonde tem que chegar sem a nossa ajuda.
    >>  ............................................
  athletic.dialogue.conversations.news.followup.spread/3
    en  Right. Let it come from them, in their own time.
    >>  ............................................
    pt  Certo. Que venha deles, no tempo deles.
    >>  ............................................
  confident.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours to hand round.
    >>  ............................................
    pt  Deviam? Não é nossa pra distribuir.
    >>  ............................................
  confident.dialogue.conversations.news.followup.spread/2
    en  No. I told you; I didn't tell the square through you.
    >>  ............................................
    pt  Não. Eu contei a você; não contei à praça através de você.
    >>  ............................................
  confident.dialogue.conversations.news.followup.spread/3
    en  ...Then I'll not tell you the next one.
    >>  ............................................
    pt  ...Então não te conto a próxima.
    >>  ............................................
  crabby.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours to hand round.
    >>  ............................................
    pt  Deviam? Não é nossa pra distribuir.
    >>  ............................................
  crabby.dialogue.conversations.news.followup.spread/2
    en  No. I told you; I didn't tell the square through you.
    >>  ............................................
    pt  Não. Eu contei a você; não contei à praça através de você.
    >>  ............................................
  crabby.dialogue.conversations.news.followup.spread/3
    en  ...Then I'll not tell you the next one.
    >>  ............................................
    pt  ...Então não te conto a próxima.
    >>  ............................................
  extroverted.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours to hand round, %1$s. It's theirs.
    >>  ............................................
    pt  ...Não é nossa pra distribuir, %1$s. É deles.
    >>  ............................................
  extroverted.dialogue.conversations.news.followup.spread/2
    en  I told you because I trust you with it. That's the whole of why.
    >>  ............................................
    pt  Eu te contei porque confio em você com isso. É toda a razão.
    >>  ............................................
  extroverted.dialogue.conversations.news.followup.spread/3
    en  ...Please don't. They'd know it came from me.
    >>  ............................................
    pt  ...Por favor, não. Eles saberiam que veio de mim.
    >>  ............................................
  flirty.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours to hand round, %1$s. It's theirs.
    >>  ............................................
    pt  ...Não é nossa pra distribuir, %1$s. É deles.
    >>  ............................................
  flirty.dialogue.conversations.news.followup.spread/2
    en  I told you because I trust you with it. That's the whole of why.
    >>  ............................................
    pt  Eu te contei porque confio em você com isso. É toda a razão.
    >>  ............................................
  flirty.dialogue.conversations.news.followup.spread/3
    en  ...Please don't. They'd know it came from me.
    >>  ............................................
    pt  ...Por favor, não. Eles saberiam que veio de mim.
    >>  ............................................
  friendly.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours to hand round, %1$s. It's theirs.
    >>  ............................................
    pt  ...Não é nossa pra distribuir, %1$s. É deles.
    >>  ............................................
  friendly.dialogue.conversations.news.followup.spread/2
    en  I told you because I trust you with it. That's the whole of why.
    >>  ............................................
    pt  Eu te contei porque confio em você com isso. É toda a razão.
    >>  ............................................
  friendly.dialogue.conversations.news.followup.spread/3
    en  ...Please don't. They'd know it came from me.
    >>  ............................................
    pt  ...Por favor, não. Eles saberiam que veio de mim.
    >>  ............................................
  gloomy.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours to hand round. They'd be hurt and it would be my doing, %1$s.
    >>  ............................................
    pt  ...Não é nossa pra distribuir. Eles se magoariam e seria obra minha, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.news.followup.spread/2
    en  Please don't. I've already said more than I should have.
    >>  ............................................
    pt  Por favor, não. Eu já disse mais do que devia.
    >>  ............................................
  gloomy.dialogue.conversations.news.followup.spread/3
    en  ...Right. That was my mistake, telling you at all.
    >>  ............................................
    pt  ...Certo. O erro foi meu, ter contado.
    >>  ............................................
  greedy.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours to hand round.
    >>  ............................................
    pt  Deviam? Não é nossa pra distribuir.
    >>  ............................................
  greedy.dialogue.conversations.news.followup.spread/2
    en  No. I told you; I didn't tell the square through you.
    >>  ............................................
    pt  Não. Eu contei a você; não contei à praça através de você.
    >>  ............................................
  greedy.dialogue.conversations.news.followup.spread/3
    en  ...Then I'll not tell you the next one.
    >>  ............................................
    pt  ...Então não te conto a próxima.
    >>  ............................................
  grumpy.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours to hand round.
    >>  ............................................
    pt  Deviam? Não é nossa pra distribuir.
    >>  ............................................
  grumpy.dialogue.conversations.news.followup.spread/2
    en  No. I told you; I didn't tell the square through you.
    >>  ............................................
    pt  Não. Eu contei a você; não contei à praça através de você.
    >>  ............................................
  grumpy.dialogue.conversations.news.followup.spread/3
    en  ...Then I'll not tell you the next one.
    >>  ............................................
    pt  ...Então não te conto a próxima.
    >>  ............................................
  introverted.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours.
    >>  ............................................
    pt  ...Não é nossa.
    >>  ............................................
  introverted.dialogue.conversations.news.followup.spread/2
    en  No. Leave it with me.
    >>  ............................................
    pt  Não. Deixe comigo.
    >>  ............................................
  introverted.dialogue.conversations.news.followup.spread/3
    en  ...I'd rather it went no further.
    >>  ............................................
    pt  ...Eu preferia que não fosse adiante.
    >>  ............................................
  lazy.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours, and news keeps better than people think.
    >>  ............................................
    pt  Deviam? Não é nossa, e notícia se conserva melhor do que se pensa.
    >>  ............................................
  lazy.dialogue.conversations.news.followup.spread/2
    en  ...No. It'll get where it's going without our help.
    >>  ............................................
    pt  ...Não. Vai chegar aonde tem que chegar sem a nossa ajuda.
    >>  ............................................
  lazy.dialogue.conversations.news.followup.spread/3
    en  Right. Let it come from them, in their own time.
    >>  ............................................
    pt  Certo. Que venha deles, no tempo deles.
    >>  ............................................
  odd.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours.
    >>  ............................................
    pt  ...Não é nossa.
    >>  ............................................
  odd.dialogue.conversations.news.followup.spread/2
    en  No. Leave it with me.
    >>  ............................................
    pt  Não. Deixe comigo.
    >>  ............................................
  odd.dialogue.conversations.news.followup.spread/3
    en  ...I'd rather it went no further.
    >>  ............................................
    pt  ...Eu preferia que não fosse adiante.
    >>  ............................................
  peaceful.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours, and news keeps better than people think.
    >>  ............................................
    pt  Deviam? Não é nossa, e notícia se conserva melhor do que se pensa.
    >>  ............................................
  peaceful.dialogue.conversations.news.followup.spread/2
    en  ...No. It'll get where it's going without our help.
    >>  ............................................
    pt  ...Não. Vai chegar aonde tem que chegar sem a nossa ajuda.
    >>  ............................................
  peaceful.dialogue.conversations.news.followup.spread/3
    en  Right. Let it come from them, in their own time.
    >>  ............................................
    pt  Certo. Que venha deles, no tempo deles.
    >>  ............................................
  peppy.dialogue.conversations.news.followup.spread/1
    en  ...Should they! No. That's how a village eats itself, %1$s.
    >>  ............................................
    pt  ...Deviam! Não. É assim que um vilarejo se devora, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.news.followup.spread/2
    en  Right, well. Consider that the last thing I tell you first.
    >>  ............................................
    pt  Certo, bom. Considere a última coisa que eu te conto primeiro.
    >>  ............................................
  peppy.dialogue.conversations.news.followup.spread/3
    en  ...Ha. No. Some things get to stay small.
    >>  ............................................
    pt  ...Ha. Não. Algumas coisas podem ficar pequenas.
    >>  ............................................
  playful.dialogue.conversations.news.followup.spread/1
    en  ...Should they! No. That's how a village eats itself, %1$s.
    >>  ............................................
    pt  ...Deviam! Não. É assim que um vilarejo se devora, %1$s.
    >>  ............................................
  playful.dialogue.conversations.news.followup.spread/2
    en  Right, well. Consider that the last thing I tell you first.
    >>  ............................................
    pt  Certo, bom. Considere a última coisa que eu te conto primeiro.
    >>  ............................................
  playful.dialogue.conversations.news.followup.spread/3
    en  ...Ha. No. Some things get to stay small.
    >>  ............................................
    pt  ...Ha. Não. Algumas coisas podem ficar pequenas.
    >>  ............................................
  relaxed.dialogue.conversations.news.followup.spread/1
    en  Should they. It isn't ours, and news keeps better than people think.
    >>  ............................................
    pt  Deviam? Não é nossa, e notícia se conserva melhor do que se pensa.
    >>  ............................................
  relaxed.dialogue.conversations.news.followup.spread/2
    en  ...No. It'll get where it's going without our help.
    >>  ............................................
    pt  ...Não. Vai chegar aonde tem que chegar sem a nossa ajuda.
    >>  ............................................
  relaxed.dialogue.conversations.news.followup.spread/3
    en  Right. Let it come from them, in their own time.
    >>  ............................................
    pt  Certo. Que venha deles, no tempo deles.
    >>  ............................................
  sensitive.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours to hand round. They'd be hurt and it would be my doing, %1$s.
    >>  ............................................
    pt  ...Não é nossa pra distribuir. Eles se magoariam e seria obra minha, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.news.followup.spread/2
    en  Please don't. I've already said more than I should have.
    >>  ............................................
    pt  Por favor, não. Eu já disse mais do que devia.
    >>  ............................................
  sensitive.dialogue.conversations.news.followup.spread/3
    en  ...Right. That was my mistake, telling you at all.
    >>  ............................................
    pt  ...Certo. O erro foi meu, ter contado.
    >>  ............................................
  shy.dialogue.conversations.news.followup.spread/1
    en  ...It isn't ours.
    >>  ............................................
    pt  ...Não é nossa.
    >>  ............................................
  shy.dialogue.conversations.news.followup.spread/2
    en  No. Leave it with me.
    >>  ............................................
    pt  Não. Deixe comigo.
    >>  ............................................
  shy.dialogue.conversations.news.followup.spread/3
    en  ...I'd rather it went no further.
    >>  ............................................
    pt  ...Eu preferia que não fosse adiante.
    >>  ............................................
  upbeat.dialogue.conversations.news.followup.spread/1
    en  ...Should they! No. That's how a village eats itself, %1$s.
    >>  ............................................
    pt  ...Deviam! Não. É assim que um vilarejo se devora, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.news.followup.spread/2
    en  Right, well. Consider that the last thing I tell you first.
    >>  ............................................
    pt  Certo, bom. Considere a última coisa que eu te conto primeiro.
    >>  ............................................
  upbeat.dialogue.conversations.news.followup.spread/3
    en  ...Ha. No. Some things get to stay small.
    >>  ............................................
    pt  ...Ha. Não. Algumas coisas podem ficar pequenas.
    >>  ............................................
  witty.dialogue.conversations.news.followup.spread/1
    en  ...Should they! No. That's how a village eats itself, %1$s.
    >>  ............................................
    pt  ...Deviam! Não. É assim que um vilarejo se devora, %1$s.
    >>  ............................................
  witty.dialogue.conversations.news.followup.spread/2
    en  Right, well. Consider that the last thing I tell you first.
    >>  ............................................
    pt  Certo, bom. Considere a última coisa que eu te conto primeiro.
    >>  ............................................
  witty.dialogue.conversations.news.followup.spread/3
    en  ...Ha. No. Some things get to stay small.
    >>  ............................................
    pt  ...Ha. Não. Algumas coisas podem ficar pequenas.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `news.mixed.guessed`, `news.mixed.wished_well`, `news.mixed.suspicion_shared`, `news.mixed.suspicion_checked`, `news.mixed.suspicion_allowed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.followup.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.news.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.news.mixed.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.left`: the villager accepts. Subject `news.aftermath`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.news.callous.followup / leave; conversations.topic.news.deflated.followup / leave; conversations.topic.news.glad.followup / leave; conversations.topic.news.grave.followup / leave
```

> Written out in full under **`conversations.topic.news.callous.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.news.mixed.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`


```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.mixed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.mixed.respond   [33 chars]
    en  Things change round here, slowly.
    >>  ............................................
    pt  As coisas mudam por aqui, devagar.
    >>  ............................................
```


### Button `curious` — "What brought that on?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.mixed.curious` — accepted phrasings: "what brought that on"; "what caused it"; "why did that happen"
  - the message must contain one of: `brought`, `caused`, `why`
  - scored words: `brought`(1.5), `caused`(1.5), `why`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.respond.curious
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.respond.curious   [21 chars]
    en  What brought that on?
    >>  ............................................
    pt  O que causou isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.mixed.curious`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +3  _(recorded under topic `news.mixed.curious`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.mixed.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "Whatever it is, it's theirs." | "Everyone should hear about this." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.curious
WHO    VILLAGER — what the player reads after pressing "What brought that on?"
       spoken on: conversations.topic.news.mixed.respond, button `curious`
       leaves the player on: conversations.topic.news.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.guessed`: the villager reports. Subject `news.mixed`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `news:unclear` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, boundary_push, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.mixed.curious/1   [64 chars]
    en  Nobody's quite sure. That's half of why it's being talked about.
    >>  ............................................
    pt  Ninguém tem certeza. É metade do motivo de estarem falando disso.
    >>  ............................................
  dialogue.conversations.news.mixed.curious/2   [45 chars]
    en  Work, most likely. It usually is, round here.
    >>  ............................................
    pt  Trabalho, provavelmente. Geralmente é, por aqui.
    >>  ............................................
  dialogue.conversations.news.mixed.curious/3   [53 chars]
    en  You'd have to ask them. Nobody has, which is very us.
    >>  ............................................
    pt  Você teria que perguntar a eles. Ninguém perguntou, o que é bem a nossa cara.
    >>  ............................................
```


### Button `wish_well` — "I hope it works out for them."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.mixed.wish_well` — accepted phrasings: "i hope it works out for them"; "i hope they do well"; "hope it works out"
  - the message must contain one of: `hope`, `works`
  - scored words: `hope`(1.5), `works`(1.2), `them`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.respond.wish_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.respond.wish_well   [29 chars]
    en  I hope it works out for them.
    >>  ............................................
    pt  Espero que dê certo para eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.mixed.wish_well`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `news.mixed.wish_well`)_
- Does: arc `news` — advance
- Does: session `turn`
- Then opens: `conversations.topic.news.mixed.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "Whatever it is, it's theirs." | "Everyone should hear about this." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.wish_well
WHO    VILLAGER — what the player reads after pressing "I hope it works out for them."
       spoken on: conversations.topic.news.mixed.respond, button `wish_well`
       leaves the player on: conversations.topic.news.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.wished_well`: the villager accepts. Subject `news.mixed`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `news:unclear` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, boundary_push, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.mixed.wish_well/1   [50 chars]
    en  So do I. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também. É estranho, ver as pessoas irem.
    >>  ............................................
  dialogue.conversations.news.mixed.wish_well/2   [54 chars]
    en  Kind of you. Half the village is placing bets instead.
    >>  ............................................
    pt  Gentil da sua parte. Metade da vila está apostando em vez disso.
    >>  ............................................
  dialogue.conversations.news.mixed.wish_well/3   [49 chars]
    en  True enough. Whatever it is, I hope it works out.
    >>  ............................................
    pt  Bem verdade. Seja lá o que for, espero que dê certo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go, and I never get used to it.
    >>  ............................................
    pt  Eu também. É estranho ver as pessoas irem, e eu nunca me acostumo.
    >>  ............................................
  anxious.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets, and I'd rather be on your side of that.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta, e prefiro ficar do seu lado disso.
    >>  ............................................
  anxious.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. I've stopped assuming things do.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Parei de supor que dá.
    >>  ............................................
  athletic.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. I've watched a great many people go and it's strange every time.
    >>  ............................................
    pt  Eu também. Já vi muita gente ir e é estranho todas as vezes.
    >>  ............................................
  athletic.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets; they did the same when I arrived.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta; fizeram o mesmo quando eu cheguei.
    >>  ............................................
  athletic.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. Most of it does, eventually.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Quase tudo dá, com o tempo.
    >>  ............................................
  confident.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também. É estranho, ver as pessoas irem.
    >>  ............................................
  confident.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando.
    >>  ............................................
  confident.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo.
    >>  ............................................
  crabby.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também. É estranho, ver as pessoas irem.
    >>  ............................................
  crabby.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando.
    >>  ............................................
  crabby.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo.
    >>  ............................................
  extroverted.dialogue.conversations.news.mixed.wish_well/1
    en  So do I, %1$s. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também, %1$s. É estranho, ver as pessoas irem.
    >>  ............................................
  extroverted.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, and you're not.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, e você não.
    >>  ............................................
  extroverted.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out for them.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo pra eles.
    >>  ............................................
  flirty.dialogue.conversations.news.mixed.wish_well/1
    en  So do I, %1$s. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também, %1$s. É estranho, ver as pessoas irem.
    >>  ............................................
  flirty.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, and you're not.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, e você não.
    >>  ............................................
  flirty.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out for them.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo pra eles.
    >>  ............................................
  friendly.dialogue.conversations.news.mixed.wish_well/1
    en  So do I, %1$s. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também, %1$s. É estranho, ver as pessoas irem.
    >>  ............................................
  friendly.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, and you're not.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, e você não.
    >>  ............................................
  friendly.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out for them.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo pra eles.
    >>  ............................................
  gloomy.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go, and I never get used to it.
    >>  ............................................
    pt  Eu também. É estranho ver as pessoas irem, e eu nunca me acostumo.
    >>  ............................................
  gloomy.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets, and I'd rather be on your side of that.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta, e prefiro ficar do seu lado disso.
    >>  ............................................
  gloomy.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. I've stopped assuming things do.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Parei de supor que dá.
    >>  ............................................
  greedy.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também. É estranho, ver as pessoas irem.
    >>  ............................................
  greedy.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando.
    >>  ............................................
  greedy.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo.
    >>  ............................................
  grumpy.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go.
    >>  ............................................
    pt  Eu também. É estranho, ver as pessoas irem.
    >>  ............................................
  grumpy.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando.
    >>  ............................................
  grumpy.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo.
    >>  ............................................
  introverted.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. Strange, watching people go.
    >>  ............................................
    pt  Eu também. Estranho, ver as pessoas irem.
    >>  ............................................
  introverted.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Others are betting.
    >>  ............................................
    pt  Gentil da sua parte. Outros estão apostando.
    >>  ............................................
  introverted.dialogue.conversations.news.mixed.wish_well/3
    en  Whatever it is, I hope it works out.
    >>  ............................................
    pt  Seja o que for, espero que dê certo.
    >>  ............................................
  lazy.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. I've watched a great many people go and it's strange every time.
    >>  ............................................
    pt  Eu também. Já vi muita gente ir e é estranho todas as vezes.
    >>  ............................................
  lazy.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets; they did the same when I arrived.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta; fizeram o mesmo quando eu cheguei.
    >>  ............................................
  lazy.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. Most of it does, eventually.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Quase tudo dá, com o tempo.
    >>  ............................................
  odd.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. Strange, watching people go.
    >>  ............................................
    pt  Eu também. Estranho, ver as pessoas irem.
    >>  ............................................
  odd.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Others are betting.
    >>  ............................................
    pt  Gentil da sua parte. Outros estão apostando.
    >>  ............................................
  odd.dialogue.conversations.news.mixed.wish_well/3
    en  Whatever it is, I hope it works out.
    >>  ............................................
    pt  Seja o que for, espero que dê certo.
    >>  ............................................
  peaceful.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. I've watched a great many people go and it's strange every time.
    >>  ............................................
    pt  Eu também. Já vi muita gente ir e é estranho todas as vezes.
    >>  ............................................
  peaceful.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets; they did the same when I arrived.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta; fizeram o mesmo quando eu cheguei.
    >>  ............................................
  peaceful.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. Most of it does, eventually.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Quase tudo dá, com o tempo.
    >>  ............................................
  peppy.dialogue.conversations.news.mixed.wish_well/1
    en  So do I! It's a strange thing, watching people go, and this village does a lot of it.
    >>  ............................................
    pt  Eu também! É estranho ver as pessoas irem, e este vilarejo faz muito isso.
    >>  ............................................
  peppy.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, which tells you about the village.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, o que diz muito do vilarejo.
    >>  ............................................
  peppy.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out splendidly.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê esplendidamente certo.
    >>  ............................................
  playful.dialogue.conversations.news.mixed.wish_well/1
    en  So do I! It's a strange thing, watching people go, and this village does a lot of it.
    >>  ............................................
    pt  Eu também! É estranho ver as pessoas irem, e este vilarejo faz muito isso.
    >>  ............................................
  playful.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, which tells you about the village.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, o que diz muito do vilarejo.
    >>  ............................................
  playful.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out splendidly.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê esplendidamente certo.
    >>  ............................................
  relaxed.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. I've watched a great many people go and it's strange every time.
    >>  ............................................
    pt  Eu também. Já vi muita gente ir e é estranho todas as vezes.
    >>  ............................................
  relaxed.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets; they did the same when I arrived.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta; fizeram o mesmo quando eu cheguei.
    >>  ............................................
  relaxed.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. Most of it does, eventually.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Quase tudo dá, com o tempo.
    >>  ............................................
  sensitive.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. It's a strange thing, watching people go, and I never get used to it.
    >>  ............................................
    pt  Eu também. É estranho ver as pessoas irem, e eu nunca me acostumo.
    >>  ............................................
  sensitive.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets, and I'd rather be on your side of that.
    >>  ............................................
    pt  Gentil da sua parte. Metade aposta, e prefiro ficar do seu lado disso.
    >>  ............................................
  sensitive.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out. I've stopped assuming things do.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê certo. Parei de supor que dá.
    >>  ............................................
  shy.dialogue.conversations.news.mixed.wish_well/1
    en  So do I. Strange, watching people go.
    >>  ............................................
    pt  Eu também. Estranho, ver as pessoas irem.
    >>  ............................................
  shy.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Others are betting.
    >>  ............................................
    pt  Gentil da sua parte. Outros estão apostando.
    >>  ............................................
  shy.dialogue.conversations.news.mixed.wish_well/3
    en  Whatever it is, I hope it works out.
    >>  ............................................
    pt  Seja o que for, espero que dê certo.
    >>  ............................................
  upbeat.dialogue.conversations.news.mixed.wish_well/1
    en  So do I! It's a strange thing, watching people go, and this village does a lot of it.
    >>  ............................................
    pt  Eu também! É estranho ver as pessoas irem, e este vilarejo faz muito isso.
    >>  ............................................
  upbeat.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, which tells you about the village.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, o que diz muito do vilarejo.
    >>  ............................................
  upbeat.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out splendidly.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê esplendidamente certo.
    >>  ............................................
  witty.dialogue.conversations.news.mixed.wish_well/1
    en  So do I! It's a strange thing, watching people go, and this village does a lot of it.
    >>  ............................................
    pt  Eu também! É estranho ver as pessoas irem, e este vilarejo faz muito isso.
    >>  ............................................
  witty.dialogue.conversations.news.mixed.wish_well/2
    en  Kind of you. Half the village is placing bets instead, which tells you about the village.
    >>  ............................................
    pt  Gentil da sua parte. Metade do vilarejo está apostando, o que diz muito do vilarejo.
    >>  ............................................
  witty.dialogue.conversations.news.mixed.wish_well/3
    en  True enough. Whatever it is, I hope it works out splendidly.
    >>  ............................................
    pt  Bem verdade. Seja o que for, espero que dê esplendidamente certo.
    >>  ............................................
```

</details>


### Button `skeptical` — "There's more to that story."

*stance family `challenge` · tone `plain` · outcome `accepted`/`qualified`/`resisted` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.mixed.skeptical` — accepted phrasings: "there is more to that story"; "something behind that"; "there is more to it"
  - the message must contain one of: `more`, `story`, `behind`
  - scored words: `more`(1.2), `story`(1.2), `behind`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.respond.skeptical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.respond.skeptical   [27 chars]
    en  There's more to that story.
    >>  ............................................
    pt  Tem mais nessa história.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `news.mixed.skeptical`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `news.mixed.skeptical`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.mixed.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "Whatever it is, it's theirs." | "Everyone should hear about this." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.skeptical.landed
WHO    VILLAGER — what the player reads after pressing "There's more to that story."
       spoken on: conversations.topic.news.mixed.respond, button `skeptical`
       leaves the player on: conversations.topic.news.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.suspicion_shared`: the villager accepts. Subject `news.mixed`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `news:unclear` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, boundary_push, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.mixed.skeptical.landed/1   [52 chars]
    en  There is. There always is. You've a good nose, %1$s.
    >>  ............................................
    pt  Tem. Sempre tem. Você tem bom faro, %1$s.
    >>  ............................................
  dialogue.conversations.news.mixed.skeptical.landed/2   [64 chars]
    en  Aye — I thought the same and said nothing. Good to hear it said.
    >>  ............................................
    pt  É — pensei o mesmo e não disse nada. Bom ouvir alguém dizer.
    >>  ............................................
  dialogue.conversations.news.mixed.skeptical.landed/3   [67 chars]
    en  Ha. You've been here long enough to be suspicious. That's progress.
    >>  ............................................
    pt  Rá. Você já está aqui tempo suficiente para desconfiar. Isso é progresso.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `news.mixed.skeptical`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `news.mixed.skeptical`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.mixed.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "Whatever it is, it's theirs." | "Everyone should hear about this." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.skeptical.flat
WHO    VILLAGER — what the player reads after pressing "There's more to that story."
       spoken on: conversations.topic.news.mixed.respond, button `skeptical`
       leaves the player on: conversations.topic.news.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.suspicion_checked`: the villager resists. Subject `news.mixed`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `news:unclear` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, boundary_push, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.mixed.skeptical.flat/1   [40 chars]
    en  Maybe there is. It's not ours to dig at.
    >>  ............................................
    pt  Talvez tenha. Não cabe a nós cavar.
    >>  ............................................
  dialogue.conversations.news.mixed.skeptical.flat/2   [34 chars]
    en  ...Or people just do things, %1$s.
    >>  ............................................
    pt  ...Ou as pessoas simplesmente fazem coisas, %1$s.
    >>  ............................................
  dialogue.conversations.news.mixed.skeptical.flat/3   [46 chars]
    en  That's how half the unkind stories here start.
    >>  ............................................
    pt  É assim que metade das histórias maldosas daqui começa.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `news.mixed.skeptical`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.mixed.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "Whatever it is, it's theirs." | "Everyone should hear about this." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.skeptical.polite
WHO    VILLAGER — what the player reads after pressing "There's more to that story."
       spoken on: conversations.topic.news.mixed.respond, button `skeptical`
       leaves the player on: conversations.topic.news.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.suspicion_allowed`: the villager qualifys. Subject `news.mixed`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `news:unclear` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, boundary_push, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.mixed.skeptical.polite/1   [27 chars]
    en  Probably. There usually is.
    >>  ............................................
    pt  Provavelmente. Geralmente tem.
    >>  ............................................
  dialogue.conversations.news.mixed.skeptical.polite/2   [44 chars]
    en  Could be. I'd not want to guess at it aloud.
    >>  ............................................
    pt  Pode ser. Não gostaria de adivinhar em voz alta.
    >>  ............................................
  dialogue.conversations.news.mixed.skeptical.polite/3   [33 chars]
    en  Everyone thinks so. Nobody knows.
    >>  ............................................
    pt  Todo mundo acha. Ninguém sabe.
    >>  ............................................
```


### Button `leave` — "Interesting. I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.mixed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.mixed.respond.leave   [21 chars]
    en  Interesting. I'll go.
    >>  ............................................
    pt  Interessante. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.mixed.leave
WHO    VILLAGER — what the player reads after pressing "Interesting. I'll go."
       spoken on: conversations.topic.news.mixed.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.mixed.leave.terminal`: the villager accepts. Subject `news.mixed`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.mixed.leave/1   [38 chars]
    en  Just so. You'll hear more soon enough.
    >>  ............................................
    pt  Pois é. Você vai ouvir mais em breve.
    >>  ............................................
  dialogue.conversations.news.mixed.leave/2   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
  dialogue.conversations.news.mixed.leave/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.news.none.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.gossip.none` — e.g. "Quiet week, honestly. The most exciting thing was a chicken on the chapel roof."


```text
POOL   dialogue key: dialogue.conversations.topic.news.none.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.none.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.none.respond   [23 chars]
    en  Quiet week, truthfully.
    >>  ............................................
    pt  Semana calma, para dizer a verdade.
    >>  ............................................
```


### Button `chat_anyway` — "Quiet suits a village."

*stance family `encouragement` · tone `plain` · answers the beat(s) `gossip.none.to.news.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.none.chat_anyway` — accepted phrasings: "quiet suits a village"; "quiet is good"; "quiet suits this place"
  - the message must contain one of: `quiet`, `suits`
  - scored words: `quiet`(1.5), `suits`(1.2), `village`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.news.none.respond.chat_anyway
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.none.respond.chat_anyway   [22 chars]
    en  Quiet suits a village.
    >>  ............................................
    pt  Calmaria combina com uma vila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.none.chat_anyway`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `news.none.chat_anyway`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.none.chat_anyway
WHO    VILLAGER — what the player reads after pressing "Quiet suits a village."
       spoken on: conversations.topic.news.none.respond, button `chat_anyway`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.none.chat_anyway.terminal`: the villager accepts. Subject `news.none`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.none.chat_anyway/1   [55 chars]
    en  It does. A week with no news is a week nobody was hurt.
    >>  ............................................
    pt  Combina. Uma semana sem notícia é uma semana em que ninguém se machucou.
    >>  ............................................
  dialogue.conversations.news.none.chat_anyway/2   [50 chars]
    en  Quite. I'll take dull over interesting most years.
    >>  ............................................
    pt  Exato. Prefiro sem graça a interessante na maioria dos anos.
    >>  ............................................
  dialogue.conversations.news.none.chat_anyway/3   [45 chars]
    en  That's a kind way to put an empty week, %1$s.
    >>  ............................................
    pt  É um jeito gentil de descrever uma semana vazia, %1$s.
    >>  ............................................
```


### Button `share_own` — "I've had a week of it myself."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `gossip.none.to.news.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.none.share_own` — accepted phrasings: "i have had a week of it myself"; "same week for me"; "mine has been quiet too"
  - the message must contain one of: `week`, `myself`, `mine`
  - scored words: `week`(1.5), `myself`(1.2), `mine`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.none.respond.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.none.respond.share_own   [29 chars]
    en  I've had a week of it myself.
    >>  ............................................
    pt  Eu tive uma semana dessas também.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.none.share_own`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +3  _(recorded under topic `news.none.share_own`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.none.share_own
WHO    VILLAGER — what the player reads after pressing "I've had a week of it myself."
       spoken on: conversations.topic.news.none.respond, button `share_own`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.none.share_own.terminal`: the villager accepts. Subject `news.none`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.none.share_own/1   [49 chars]
    en  Have you? Go on then — you're the news this week.
    >>  ............................................
    pt  Teve? Vai lá então — você é a notícia desta semana.
    >>  ............................................
  dialogue.conversations.news.none.share_own/2   [51 chars]
    en  Then tell me yours. Somebody should have something.
    >>  ............................................
    pt  Então me conta a sua. Alguém tem que ter alguma.
    >>  ............................................
  dialogue.conversations.news.none.share_own/3   [51 chars]
    en  Ha. Between us we might manage one piece of gossip.
    >>  ............................................
    pt  Rá. Entre nós dois talvez a gente consiga uma fofoca.
    >>  ............................................
```


### Button `leave` — "Another time, then."

*stance family `exit` · tone `plain` · answers the beat(s) `gossip.none.to.news.none` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.none.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.none.respond.leave   [19 chars]
    en  Another time, then.
    >>  ............................................
    pt  Outra hora, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.none.leave
WHO    VILLAGER — what the player reads after pressing "Another time, then."
       spoken on: conversations.topic.news.none.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.none.leave.terminal`: the villager accepts. Subject `news.none`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.none.leave/1   [34 chars]
    en  It is. Come back after market day.
    >>  ............................................
    pt  É sim. Volte depois do dia de feira.
    >>  ............................................
  dialogue.conversations.news.none.leave/2   [13 chars]
    en  Get on, then.
    >>  ............................................
    pt  Então vá.
    >>  ............................................
  dialogue.conversations.news.none.leave/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.news.sad.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`


```text
POOL   dialogue key: dialogue.conversations.topic.news.sad.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.sad.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.sad.respond   [35 chars]
    en  That's the news, and it's not good.
    >>  ............................................
    pt  É essa a notícia, e não é boa.
    >>  ............................................
```


### Button `compassion` — "That's awful. How is everyone?"

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.sad.compassion` — accepted phrasings: "that is awful, how is everyone"; "that is terrible"; "how is everyone holding up"
  - the message must contain one of: `awful`, `terrible`, `everyone`
  - scored words: `awful`(1.5), `terrible`(1.5), `everyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.news.sad.respond.compassion
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.sad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.sad.respond.compassion   [30 chars]
    en  That's awful. How is everyone?
    >>  ............................................
    pt  Que terrível. Como está todo mundo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `news.sad.compassion`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `news.sad.compassion`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.grave.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "And how are you, with all that?" | "Is there anything the family needs?" | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.sad.compassion
WHO    VILLAGER — what the player reads after pressing "That's awful. How is everyone?"
       spoken on: conversations.topic.news.sad.respond, button `compassion`
       leaves the player on: conversations.topic.news.grave.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.grave.borne`: the villager discloses. Subject `news.grave`, polarity `acute`, guarded, outcome `appreciated`.
NOTE   this is the line that establishes `news:death`, `village:grieving` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.sad.compassion/1   [76 chars]
    en  ...Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  ...Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  dialogue.conversations.news.sad.compassion/2   [61 chars]
    en  Badly. But somebody asking helps more than you'd think, %1$s.
    >>  ............................................
    pt  Mal. Mas alguém perguntar ajuda mais do que você imagina, %1$s.
    >>  ............................................
  dialogue.conversations.news.sad.compassion/3   [75 chars]
    en  As you'd expect. We're carrying it between us, which is how it's done here.
    >>  ............................................
    pt  Como era de se esperar. Estamos carregando juntos, é assim que se faz aqui.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details, %1$s.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.news.sad.compassion/2
    en  Badly. I've been holding that in all day and you've asked it out of me.
    >>  ............................................
    pt  Mal. Eu segurei isso o dia todo e você tirou de mim.
    >>  ............................................
  anxious.dialogue.conversations.news.sad.compassion/3
    en  Not well. And thank you. You've no idea what the right question is worth today.
    >>  ............................................
    pt  Nada bem. E obrigado. Você não faz ideia do que vale a pergunta certa hoje.
    >>  ............................................
  athletic.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. It'll be a long while and everybody knows it.
    >>  ............................................
    pt  Nada bem. Ninguém está. Vai ser longo e todos sabem.
    >>  ............................................
  athletic.dialogue.conversations.news.sad.compassion/2
    en  Badly, and it'll be badly for some time. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e vai ser mal por um tempo. Obrigado por perguntar o certo.
    >>  ............................................
  athletic.dialogue.conversations.news.sad.compassion/3
    en  Not well. These things take the time they take, and this one will take a while.
    >>  ............................................
    pt  Nada bem. Essas coisas levam o tempo que levam, e essa vai levar um tempo.
    >>  ............................................
  confident.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  confident.dialogue.conversations.news.sad.compassion/2
    en  Badly. And thank you for not asking me what happened.
    >>  ............................................
    pt  Mal. E obrigado por não perguntar o que aconteceu.
    >>  ............................................
  confident.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked the right question, which nobody else has.
    >>  ............................................
    pt  Nada bem. Você fez a pergunta certa, que mais ninguém fez.
    >>  ............................................
  crabby.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  crabby.dialogue.conversations.news.sad.compassion/2
    en  Badly. And thank you for not asking me what happened.
    >>  ............................................
    pt  Mal. E obrigado por não perguntar o que aconteceu.
    >>  ............................................
  crabby.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked the right question, which nobody else has.
    >>  ............................................
    pt  Nada bem. Você fez a pergunta certa, que mais ninguém fez.
    >>  ............................................
  extroverted.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details, %1$s.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.news.sad.compassion/2
    en  Badly. Sit a moment. It's easier to say with somebody in the room.
    >>  ............................................
    pt  Mal. Sente um pouco. É mais fácil dizer com alguém na sala.
    >>  ............................................
  extroverted.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them, not about what happened, and I noticed that.
    >>  ............................................
    pt  Nada bem. Você perguntou deles, não do que aconteceu, e eu reparei.
    >>  ............................................
  flirty.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details, %1$s.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.news.sad.compassion/2
    en  Badly. Sit a moment. It's easier to say with somebody in the room.
    >>  ............................................
    pt  Mal. Sente um pouco. É mais fácil dizer com alguém na sala.
    >>  ............................................
  flirty.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them, not about what happened, and I noticed that.
    >>  ............................................
    pt  Nada bem. Você perguntou deles, não do que aconteceu, e eu reparei.
    >>  ............................................
  friendly.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details, %1$s.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.news.sad.compassion/2
    en  Badly. Sit a moment. It's easier to say with somebody in the room.
    >>  ............................................
    pt  Mal. Sente um pouco. É mais fácil dizer com alguém na sala.
    >>  ............................................
  friendly.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them, not about what happened, and I noticed that.
    >>  ............................................
    pt  Nada bem. Você perguntou deles, não do que aconteceu, e eu reparei.
    >>  ............................................
  gloomy.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details, %1$s.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.news.sad.compassion/2
    en  Badly. I've been holding that in all day and you've asked it out of me.
    >>  ............................................
    pt  Mal. Eu segurei isso o dia todo e você tirou de mim.
    >>  ............................................
  gloomy.dialogue.conversations.news.sad.compassion/3
    en  Not well. And thank you. You've no idea what the right question is worth today.
    >>  ............................................
    pt  Nada bem. E obrigado. Você não faz ideia do que vale a pergunta certa hoje.
    >>  ............................................
  greedy.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  greedy.dialogue.conversations.news.sad.compassion/2
    en  Badly. And thank you for not asking me what happened.
    >>  ............................................
    pt  Mal. E obrigado por não perguntar o que aconteceu.
    >>  ............................................
  greedy.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked the right question, which nobody else has.
    >>  ............................................
    pt  Nada bem. Você fez a pergunta certa, que mais ninguém fez.
    >>  ............................................
  grumpy.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  grumpy.dialogue.conversations.news.sad.compassion/2
    en  Badly. And thank you for not asking me what happened.
    >>  ............................................
    pt  Mal. E obrigado por não perguntar o que aconteceu.
    >>  ............................................
  grumpy.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked the right question, which nobody else has.
    >>  ............................................
    pt  Nada bem. Você fez a pergunta certa, que mais ninguém fez.
    >>  ............................................
  introverted.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is.
    >>  ............................................
    pt  Nada bem. Ninguém está.
    >>  ............................................
  introverted.dialogue.conversations.news.sad.compassion/2
    en  Badly. Thank you for not asking the rest.
    >>  ............................................
    pt  Mal. Obrigado por não perguntar o resto.
    >>  ............................................
  introverted.dialogue.conversations.news.sad.compassion/3
    en  Not well. That's all I'll say.
    >>  ............................................
    pt  Nada bem. É tudo que eu digo.
    >>  ............................................
  lazy.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. It'll be a long while and everybody knows it.
    >>  ............................................
    pt  Nada bem. Ninguém está. Vai ser longo e todos sabem.
    >>  ............................................
  lazy.dialogue.conversations.news.sad.compassion/2
    en  Badly, and it'll be badly for some time. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e vai ser mal por um tempo. Obrigado por perguntar o certo.
    >>  ............................................
  lazy.dialogue.conversations.news.sad.compassion/3
    en  Not well. These things take the time they take, and this one will take a while.
    >>  ............................................
    pt  Nada bem. Essas coisas levam o tempo que levam, e essa vai levar um tempo.
    >>  ............................................
  odd.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is.
    >>  ............................................
    pt  Nada bem. Ninguém está.
    >>  ............................................
  odd.dialogue.conversations.news.sad.compassion/2
    en  Badly. Thank you for not asking the rest.
    >>  ............................................
    pt  Mal. Obrigado por não perguntar o resto.
    >>  ............................................
  odd.dialogue.conversations.news.sad.compassion/3
    en  Not well. That's all I'll say.
    >>  ............................................
    pt  Nada bem. É tudo que eu digo.
    >>  ............................................
  peaceful.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. It'll be a long while and everybody knows it.
    >>  ............................................
    pt  Nada bem. Ninguém está. Vai ser longo e todos sabem.
    >>  ............................................
  peaceful.dialogue.conversations.news.sad.compassion/2
    en  Badly, and it'll be badly for some time. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e vai ser mal por um tempo. Obrigado por perguntar o certo.
    >>  ............................................
  peaceful.dialogue.conversations.news.sad.compassion/3
    en  Not well. These things take the time they take, and this one will take a while.
    >>  ............................................
    pt  Nada bem. Essas coisas levam o tempo que levam, e essa vai levar um tempo.
    >>  ............................................
  peppy.dialogue.conversations.news.sad.compassion/1
    en  ...Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  ...Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  peppy.dialogue.conversations.news.sad.compassion/2
    en  Badly, and I've no lightness for this one. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e eu não tenho leveza pra isso. Obrigado por perguntar o certo.
    >>  ............................................
  peppy.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them. Most people ask about the story.
    >>  ............................................
    pt  Nada bem. Você perguntou deles. A maioria pergunta da história.
    >>  ............................................
  playful.dialogue.conversations.news.sad.compassion/1
    en  ...Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  ...Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  playful.dialogue.conversations.news.sad.compassion/2
    en  Badly, and I've no lightness for this one. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e eu não tenho leveza pra isso. Obrigado por perguntar o certo.
    >>  ............................................
  playful.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them. Most people ask about the story.
    >>  ............................................
    pt  Nada bem. Você perguntou deles. A maioria pergunta da história.
    >>  ............................................
  relaxed.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. It'll be a long while and everybody knows it.
    >>  ............................................
    pt  Nada bem. Ninguém está. Vai ser longo e todos sabem.
    >>  ............................................
  relaxed.dialogue.conversations.news.sad.compassion/2
    en  Badly, and it'll be badly for some time. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e vai ser mal por um tempo. Obrigado por perguntar o certo.
    >>  ............................................
  relaxed.dialogue.conversations.news.sad.compassion/3
    en  Not well. These things take the time they take, and this one will take a while.
    >>  ............................................
    pt  Nada bem. Essas coisas levam o tempo que levam, e essa vai levar um tempo.
    >>  ............................................
  sensitive.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is. Thank you for asking after them and not the details, %1$s.
    >>  ............................................
    pt  Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.news.sad.compassion/2
    en  Badly. I've been holding that in all day and you've asked it out of me.
    >>  ............................................
    pt  Mal. Eu segurei isso o dia todo e você tirou de mim.
    >>  ............................................
  sensitive.dialogue.conversations.news.sad.compassion/3
    en  Not well. And thank you. You've no idea what the right question is worth today.
    >>  ............................................
    pt  Nada bem. E obrigado. Você não faz ideia do que vale a pergunta certa hoje.
    >>  ............................................
  shy.dialogue.conversations.news.sad.compassion/1
    en  Not well. Nobody is.
    >>  ............................................
    pt  Nada bem. Ninguém está.
    >>  ............................................
  shy.dialogue.conversations.news.sad.compassion/2
    en  Badly. Thank you for not asking the rest.
    >>  ............................................
    pt  Mal. Obrigado por não perguntar o resto.
    >>  ............................................
  shy.dialogue.conversations.news.sad.compassion/3
    en  Not well. That's all I'll say.
    >>  ............................................
    pt  Nada bem. É tudo que eu digo.
    >>  ............................................
  upbeat.dialogue.conversations.news.sad.compassion/1
    en  ...Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  ...Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  upbeat.dialogue.conversations.news.sad.compassion/2
    en  Badly, and I've no lightness for this one. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e eu não tenho leveza pra isso. Obrigado por perguntar o certo.
    >>  ............................................
  upbeat.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them. Most people ask about the story.
    >>  ............................................
    pt  Nada bem. Você perguntou deles. A maioria pergunta da história.
    >>  ............................................
  witty.dialogue.conversations.news.sad.compassion/1
    en  ...Not well. Nobody is. Thank you for asking after them and not the details.
    >>  ............................................
    pt  ...Nada bem. Ninguém está. Obrigado por perguntar deles e não dos detalhes.
    >>  ............................................
  witty.dialogue.conversations.news.sad.compassion/2
    en  Badly, and I've no lightness for this one. Thank you for asking the right thing.
    >>  ............................................
    pt  Mal, e eu não tenho leveza pra isso. Obrigado por perguntar o certo.
    >>  ............................................
  witty.dialogue.conversations.news.sad.compassion/3
    en  Not well. You asked about them. Most people ask about the story.
    >>  ............................................
    pt  Nada bem. Você perguntou deles. A maioria pergunta da história.
    >>  ............................................
```

</details>


### Button `ask_more` — "What happened, exactly?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.sad.ask_more` — accepted phrasings: "what happened exactly"; "what happened"; "how did it happen"
  - the message must contain one of: `happened`, `exactly`
  - scored words: `happened`(1.5), `exactly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.sad.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.sad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.sad.respond.ask_more   [23 chars]
    en  What happened, exactly?
    >>  ............................................
    pt  O que aconteceu, exatamente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `news.sad.ask_more`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.grave.followup`
- …where the player's next choices will be: "I'll not repeat any of it." | "And how are you, with all that?" | "Is there anything the family needs?" | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.news.sad.ask_more
WHO    VILLAGER — what the player reads after pressing "What happened, exactly?"
       spoken on: conversations.topic.news.sad.respond, button `ask_more`
       leaves the player on: conversations.topic.news.grave.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.grave.withheld`: the villager deflects. Subject `news.grave`, polarity `acute`, guarded, outcome `qualified`.
NOTE   this is the line that establishes `news:death`, `village:grieving` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.sad.ask_more/1   [62 chars]
    en  There's not much more to it, and what there is I'd rather not.
    >>  ............................................
    pt  Não tem muito mais, e o que tem eu prefiro não contar.
    >>  ............................................
  dialogue.conversations.news.sad.ask_more/2   [59 chars]
    en  You'll hear the rest from someone. I'd rather it not be me.
    >>  ............................................
    pt  Você vai ouvir o resto de alguém. Prefiro que não seja de mim.
    >>  ............................................
  dialogue.conversations.news.sad.ask_more/3   [55 chars]
    en  Quietly, in the night. That's all anyone needs to know.
    >>  ............................................
    pt  Em silêncio, à noite. É tudo que alguém precisa saber.
    >>  ............................................
```


### Button `amused` — "Ha — serves them right."

*stance family `dismissal` · tone `hostile` · outcome `hurt` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.sad.amused` — accepted phrasings: "serves them right"; "they deserved it"; "that is funny"
  - the message must contain one of: `serves`, `deserved`, `funny`
  - scored words: `serves`(1.5), `deserved`(1.5), `funny`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.sad.respond.amused
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.sad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.sad.respond.amused   [23 chars]
    en  Ha — serves them right.
    >>  ............................................
    pt  Rá — bem feito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -3** — decision id `news.sad.amused`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -6, tension +8, trust -3  _(recorded under topic `news.sad.amused`)_
- Does: session `turn`
- Then opens: `conversations.topic.news.callous.followup`
- …where the player's next choices will be: "That was unforgivable. I'm sorry." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.news.sad.amused
WHO    VILLAGER — what the player reads after pressing "Ha — serves them right."
       spoken on: conversations.topic.news.sad.respond, button `amused`
       leaves the player on: conversations.topic.news.callous.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.grave.mocked`: the villager hurts. Subject `news.grave`, polarity `acute`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `news:death`, `player:laughed_at_death` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.sad.amused/1   [20 chars]
    en  ...Get away from me.
    >>  ............................................
    pt  ...Sai de perto de mim.
    >>  ............................................
  dialogue.conversations.news.sad.amused/2   [42 chars]
    en  Somebody died, %1$s. Somebody we all knew.
    >>  ............................................
    pt  Alguém morreu, %1$s. Alguém que todos nós conhecíamos.
    >>  ............................................
  dialogue.conversations.news.sad.amused/3   [61 chars]
    en  I'll pretend I didn't hear that. Don't make me hear it twice.
    >>  ............................................
    pt  Vou fingir que não ouvi. Não me faça ouvir duas vezes.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me, %1$s. Please.
    >>  ............................................
    pt  ...Saia de perto de mim, %1$s. Por favor.
    >>  ............................................
  anxious.dialogue.conversations.news.sad.amused/2
    en  Don't. Not about that. Not while it's this new.
    >>  ............................................
    pt  Não. Sobre isso não. Não enquanto é tão recente.
    >>  ............................................
  anxious.dialogue.conversations.news.sad.amused/3
    en  ...I can't be near you just now. Go.
    >>  ............................................
    pt  ...Eu não consigo ficar perto de você agora. Vá.
    >>  ............................................
  athletic.dialogue.conversations.news.sad.amused/1
    en  ...No. Get away from me.
    >>  ............................................
    pt  ...Não. Saia de perto de mim.
    >>  ............................................
  athletic.dialogue.conversations.news.sad.amused/2
    en  That's not a thing to be amused at. Go and be amused elsewhere.
    >>  ............................................
    pt  Não é coisa pra achar graça. Vá achar graça em outro lugar.
    >>  ............................................
  athletic.dialogue.conversations.news.sad.amused/3
    en  ...I'll speak to you another day. Not today.
    >>  ............................................
    pt  ...Eu falo com você outro dia. Hoje não.
    >>  ............................................
  confident.dialogue.conversations.news.sad.amused/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  confident.dialogue.conversations.news.sad.amused/2
    en  No. Not that, not about this.
    >>  ............................................
    pt  Não. Isso não, sobre isso não.
    >>  ............................................
  confident.dialogue.conversations.news.sad.amused/3
    en  ...We're done talking.
    >>  ............................................
    pt  ...A conversa acabou.
    >>  ............................................
  crabby.dialogue.conversations.news.sad.amused/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  crabby.dialogue.conversations.news.sad.amused/2
    en  No. Not that, not about this.
    >>  ............................................
    pt  Não. Isso não, sobre isso não.
    >>  ............................................
  crabby.dialogue.conversations.news.sad.amused/3
    en  ...We're done talking.
    >>  ............................................
    pt  ...A conversa acabou.
    >>  ............................................
  extroverted.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me. I'd not have believed that of you.
    >>  ............................................
    pt  ...Saia de perto de mim. Eu não acreditaria isso de você.
    >>  ............................................
  extroverted.dialogue.conversations.news.sad.amused/2
    en  No. Not from you, %1$s. Not about this.
    >>  ............................................
    pt  Não. De você não, %1$s. Sobre isso não.
    >>  ............................................
  extroverted.dialogue.conversations.news.sad.amused/3
    en  ...Go. Please. Before I say something I'd have to take back.
    >>  ............................................
    pt  ...Vá. Por favor. Antes que eu diga algo que eu tenha que retirar.
    >>  ............................................
  flirty.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me. I'd not have believed that of you.
    >>  ............................................
    pt  ...Saia de perto de mim. Eu não acreditaria isso de você.
    >>  ............................................
  flirty.dialogue.conversations.news.sad.amused/2
    en  No. Not from you, %1$s. Not about this.
    >>  ............................................
    pt  Não. De você não, %1$s. Sobre isso não.
    >>  ............................................
  flirty.dialogue.conversations.news.sad.amused/3
    en  ...Go. Please. Before I say something I'd have to take back.
    >>  ............................................
    pt  ...Vá. Por favor. Antes que eu diga algo que eu tenha que retirar.
    >>  ............................................
  friendly.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me. I'd not have believed that of you.
    >>  ............................................
    pt  ...Saia de perto de mim. Eu não acreditaria isso de você.
    >>  ............................................
  friendly.dialogue.conversations.news.sad.amused/2
    en  No. Not from you, %1$s. Not about this.
    >>  ............................................
    pt  Não. De você não, %1$s. Sobre isso não.
    >>  ............................................
  friendly.dialogue.conversations.news.sad.amused/3
    en  ...Go. Please. Before I say something I'd have to take back.
    >>  ............................................
    pt  ...Vá. Por favor. Antes que eu diga algo que eu tenha que retirar.
    >>  ............................................
  gloomy.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me, %1$s. Please.
    >>  ............................................
    pt  ...Saia de perto de mim, %1$s. Por favor.
    >>  ............................................
  gloomy.dialogue.conversations.news.sad.amused/2
    en  Don't. Not about that. Not while it's this new.
    >>  ............................................
    pt  Não. Sobre isso não. Não enquanto é tão recente.
    >>  ............................................
  gloomy.dialogue.conversations.news.sad.amused/3
    en  ...I can't be near you just now. Go.
    >>  ............................................
    pt  ...Eu não consigo ficar perto de você agora. Vá.
    >>  ............................................
  greedy.dialogue.conversations.news.sad.amused/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  greedy.dialogue.conversations.news.sad.amused/2
    en  No. Not that, not about this.
    >>  ............................................
    pt  Não. Isso não, sobre isso não.
    >>  ............................................
  greedy.dialogue.conversations.news.sad.amused/3
    en  ...We're done talking.
    >>  ............................................
    pt  ...A conversa acabou.
    >>  ............................................
  grumpy.dialogue.conversations.news.sad.amused/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  grumpy.dialogue.conversations.news.sad.amused/2
    en  No. Not that, not about this.
    >>  ............................................
    pt  Não. Isso não, sobre isso não.
    >>  ............................................
  grumpy.dialogue.conversations.news.sad.amused/3
    en  ...We're done talking.
    >>  ............................................
    pt  ...A conversa acabou.
    >>  ............................................
  introverted.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me.
    >>  ............................................
    pt  ...Saia de perto de mim.
    >>  ............................................
  introverted.dialogue.conversations.news.sad.amused/2
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  introverted.dialogue.conversations.news.sad.amused/3
    en  ...Go.
    >>  ............................................
    pt  ...Vá.
    >>  ............................................
  lazy.dialogue.conversations.news.sad.amused/1
    en  ...No. Get away from me.
    >>  ............................................
    pt  ...Não. Saia de perto de mim.
    >>  ............................................
  lazy.dialogue.conversations.news.sad.amused/2
    en  That's not a thing to be amused at. Go and be amused elsewhere.
    >>  ............................................
    pt  Não é coisa pra achar graça. Vá achar graça em outro lugar.
    >>  ............................................
  lazy.dialogue.conversations.news.sad.amused/3
    en  ...I'll speak to you another day. Not today.
    >>  ............................................
    pt  ...Eu falo com você outro dia. Hoje não.
    >>  ............................................
  odd.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me.
    >>  ............................................
    pt  ...Saia de perto de mim.
    >>  ............................................
  odd.dialogue.conversations.news.sad.amused/2
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  odd.dialogue.conversations.news.sad.amused/3
    en  ...Go.
    >>  ............................................
    pt  ...Vá.
    >>  ............................................
  peaceful.dialogue.conversations.news.sad.amused/1
    en  ...No. Get away from me.
    >>  ............................................
    pt  ...Não. Saia de perto de mim.
    >>  ............................................
  peaceful.dialogue.conversations.news.sad.amused/2
    en  That's not a thing to be amused at. Go and be amused elsewhere.
    >>  ............................................
    pt  Não é coisa pra achar graça. Vá achar graça em outro lugar.
    >>  ............................................
  peaceful.dialogue.conversations.news.sad.amused/3
    en  ...I'll speak to you another day. Not today.
    >>  ............................................
    pt  ...Eu falo com você outro dia. Hoje não.
    >>  ............................................
  peppy.dialogue.conversations.news.sad.amused/1
    en  ...No. Not that one. I make the jokes and even I wouldn't.
    >>  ............................................
    pt  ...Não. Essa não. Eu é que faço as piadas e nem eu faria.
    >>  ............................................
  peppy.dialogue.conversations.news.sad.amused/2
    en  Get away from me. I mean it, %1$s.
    >>  ............................................
    pt  Saia de perto de mim. Estou falando sério, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.news.sad.amused/3
    en  ...That's the end of this conversation.
    >>  ............................................
    pt  ...Esta conversa acabou.
    >>  ............................................
  playful.dialogue.conversations.news.sad.amused/1
    en  ...No. Not that one. I make the jokes and even I wouldn't.
    >>  ............................................
    pt  ...Não. Essa não. Eu é que faço as piadas e nem eu faria.
    >>  ............................................
  playful.dialogue.conversations.news.sad.amused/2
    en  Get away from me. I mean it, %1$s.
    >>  ............................................
    pt  Saia de perto de mim. Estou falando sério, %1$s.
    >>  ............................................
  playful.dialogue.conversations.news.sad.amused/3
    en  ...That's the end of this conversation.
    >>  ............................................
    pt  ...Esta conversa acabou.
    >>  ............................................
  relaxed.dialogue.conversations.news.sad.amused/1
    en  ...No. Get away from me.
    >>  ............................................
    pt  ...Não. Saia de perto de mim.
    >>  ............................................
  relaxed.dialogue.conversations.news.sad.amused/2
    en  That's not a thing to be amused at. Go and be amused elsewhere.
    >>  ............................................
    pt  Não é coisa pra achar graça. Vá achar graça em outro lugar.
    >>  ............................................
  relaxed.dialogue.conversations.news.sad.amused/3
    en  ...I'll speak to you another day. Not today.
    >>  ............................................
    pt  ...Eu falo com você outro dia. Hoje não.
    >>  ............................................
  sensitive.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me, %1$s. Please.
    >>  ............................................
    pt  ...Saia de perto de mim, %1$s. Por favor.
    >>  ............................................
  sensitive.dialogue.conversations.news.sad.amused/2
    en  Don't. Not about that. Not while it's this new.
    >>  ............................................
    pt  Não. Sobre isso não. Não enquanto é tão recente.
    >>  ............................................
  sensitive.dialogue.conversations.news.sad.amused/3
    en  ...I can't be near you just now. Go.
    >>  ............................................
    pt  ...Eu não consigo ficar perto de você agora. Vá.
    >>  ............................................
  shy.dialogue.conversations.news.sad.amused/1
    en  ...Get away from me.
    >>  ............................................
    pt  ...Saia de perto de mim.
    >>  ............................................
  shy.dialogue.conversations.news.sad.amused/2
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  shy.dialogue.conversations.news.sad.amused/3
    en  ...Go.
    >>  ............................................
    pt  ...Vá.
    >>  ............................................
  upbeat.dialogue.conversations.news.sad.amused/1
    en  ...No. Not that one. I make the jokes and even I wouldn't.
    >>  ............................................
    pt  ...Não. Essa não. Eu é que faço as piadas e nem eu faria.
    >>  ............................................
  upbeat.dialogue.conversations.news.sad.amused/2
    en  Get away from me. I mean it, %1$s.
    >>  ............................................
    pt  Saia de perto de mim. Estou falando sério, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.news.sad.amused/3
    en  ...That's the end of this conversation.
    >>  ............................................
    pt  ...Esta conversa acabou.
    >>  ............................................
  witty.dialogue.conversations.news.sad.amused/1
    en  ...No. Not that one. I make the jokes and even I wouldn't.
    >>  ............................................
    pt  ...Não. Essa não. Eu é que faço as piadas e nem eu faria.
    >>  ............................................
  witty.dialogue.conversations.news.sad.amused/2
    en  Get away from me. I mean it, %1$s.
    >>  ............................................
    pt  Saia de perto de mim. Estou falando sério, %1$s.
    >>  ............................................
  witty.dialogue.conversations.news.sad.amused/3
    en  ...That's the end of this conversation.
    >>  ............................................
    pt  ...Esta conversa acabou.
    >>  ............................................
```

</details>


### Button `leave` — "I'm sorry. I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.sad.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.sad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.sad.respond.leave   [19 chars]
    en  I'm sorry. I'll go.
    >>  ............................................
    pt  Sinto muito. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.sad.leave
WHO    VILLAGER — what the player reads after pressing "I'm sorry. I'll go."
       spoken on: conversations.topic.news.sad.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.sad.leave.terminal`: the villager accepts. Subject `news.grave`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.sad.leave/1   [37 chars]
    en  So I've found. Not a day for talking.
    >>  ............................................
    pt  Foi o que eu vi. Não é dia de conversa.
    >>  ............................................
  dialogue.conversations.news.sad.leave/2   [30 chars]
    en  Go on. Thank you for stopping.
    >>  ............................................
    pt  Pode ir. Obrigado por parar.
    >>  ............................................
  dialogue.conversations.news.sad.leave/3   [29 chars]
    en  Right. Mind how you go, %1$s.
    >>  ............................................
    pt  Certo. Se cuida, %1$s.
    >>  ............................................
```

---


## `conversations.topic.news.teen.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`


```text
POOL   dialogue key: dialogue.conversations.topic.news.teen.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.teen.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.teen.respond   [49 chars]
    en  That's what I've heard. Make of it what you like.
    >>  ............................................
    pt  É o que eu ouvi. Faça o que quiser com isso.
    >>  ............................................
```


### Button `take_seriously` — "Thank you for telling me straight."

*stance family `encouragement` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.teen.take_seriously` — accepted phrasings: "thank you for telling me straight"; "you told me straight"; "i appreciate you being direct"
  - the message must contain one of: `straight`, `telling`, `thank`
  - scored words: `straight`(1.5), `telling`(1.2), `thank`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.news.teen.respond.take_seriously
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.teen.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.teen.respond.take_seriously   [34 chars]
    en  Thank you for telling me straight.
    >>  ............................................
    pt  Obrigado por me contar direto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.teen.take_seriously`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, respect +3  _(recorded under topic `news.teen.take_seriously`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.teen.take_seriously
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me straight."
       spoken on: conversations.topic.news.teen.respond, button `take_seriously`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.teen.take_seriously.terminal`: the villager accepts. Subject `news.teen`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen
```

```text
  dialogue.conversations.news.teen.take_seriously/1   [71 chars]
    en  ...Straight. Right. The rest hear my age before they hear the sentence.
    >>  ............................................
    pt  ...Direto. Certo. Os outros ouvem minha idade antes de ouvir a frase.
    >>  ............................................
  dialogue.conversations.news.teen.take_seriously/2   [68 chars]
    en  You didn't check it with somebody older first. I noticed that, %1$s.
    >>  ............................................
    pt  Você não conferiu com alguém mais velho antes. Eu reparei, %1$s.
    >>  ............................................
  dialogue.conversations.news.teen.take_seriously/3   [87 chars]
    en  That's — thank you. I'd got used to being the last one asked and the first one doubted.
    >>  ............................................
    pt  Isso é — obrigado. Eu tinha me acostumado a ser o último perguntado e o primeiro duvidado.
    >>  ............................................
```


### Button `ask_how_they_know` — "How much of that is certain?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.teen.ask_how_they_know` — accepted phrasings: "how much of that is certain"; "how sure are you"; "which parts do you know"
  - the message must contain one of: `certain`, `much`, `sure`
  - scored words: `certain`(1.5), `much`(0.6), `sure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.news.teen.respond.ask_how_they_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.teen.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.teen.respond.ask_how_they_know   [28 chars]
    en  How much of that is certain?
    >>  ............................................
    pt  Quanto disso é certeza?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, respect +2  _(recorded under topic `news.teen.ask_how_they_know`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.teen.ask_how_they_know
WHO    VILLAGER — what the player reads after pressing "How much of that is certain?"
       spoken on: conversations.topic.news.teen.respond, button `ask_how_they_know`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.teen.ask_how_they_know.terminal`: the villager qualifys. Subject `news.teen`, polarity `mixed`, ends conversation, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen
```

```text
  dialogue.conversations.news.teen.ask_how_they_know/1   [96 chars]
    en  The first half I saw. The rest is the square, and the square is wrong about a third of the time.
    >>  ............................................
    pt  A primeira metade eu vi. O resto é a praça, e a praça erra um terço das vezes.
    >>  ............................................
  dialogue.conversations.news.teen.ask_how_they_know/2   [93 chars]
    en  I'll tell you which parts I'd stand behind if you want the list. It's shorter than the story.
    >>  ............................................
    pt  Eu te digo quais partes eu defenderia, se quiser a lista. É menor que a história.
    >>  ............................................
  dialogue.conversations.news.teen.ask_how_they_know/3   [60 chars]
    en  Not all of it. That's why I said it the way I said it, %1$s.
    >>  ............................................
    pt  Nem tudo. Por isso eu disse do jeito que disse, %1$s.
    >>  ............................................
```


### Button `dismiss` — "You're too young to be repeating that."

*stance family `dismissal` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.teen.dismiss` — accepted phrasings: "you are too young to be repeating that"; "you should not repeat that"; "that is not yours to pass on"
  - the message must contain one of: `repeating`, `too`, `young`
  - scored words: `repeating`(1.2), `too`(0.5), `young`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.news.teen.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.teen.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.teen.respond.dismiss   [38 chars]
    en  You're too young to be repeating that.
    >>  ............................................
    pt  Você é novo demais pra repetir isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `news.teen.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -3, respect -3, tension +3  _(recorded under topic `news.teen.dismiss`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.teen.dismiss
WHO    VILLAGER — what the player reads after pressing "You're too young to be repeating that."
       spoken on: conversations.topic.news.teen.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.teen.dismiss.terminal`: the villager hurts. Subject `news.teen`, polarity `negative`, ends conversation, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen
```

```text
  dialogue.conversations.news.teen.dismiss/1   [88 chars]
    en  ...Too young. Right. I'll be too young for another two years and then I'll be too green.
    >>  ............................................
    pt  ...Novo demais. Certo. Vou ser novo demais por mais dois anos e depois vou ser verde demais.
    >>  ............................................
  dialogue.conversations.news.teen.dismiss/2   [68 chars]
    en  Everyone in the square repeats it. I'm the one who gets told not to.
    >>  ............................................
    pt  Todo mundo na praça repete. Sou eu que sou mandado a não repetir.
    >>  ............................................
  dialogue.conversations.news.teen.dismiss/3   [72 chars]
    en  Fine. Ask somebody taller next time and see how much they actually know.
    >>  ............................................
    pt  Tudo bem. Pergunte a alguém mais alto da próxima e veja o quanto sabem de verdade.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. I know what that means and it isn't about my age, %1$s.
    >>  ............................................
    pt  ...Novo demais. Eu sei o que isso significa e não é sobre a minha idade, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. It's only a problem when I do, and I've noticed that for years.
    >>  ............................................
    pt  Todo mundo repete. Só é problema quando eu repito, e eu reparo nisso há anos.
    >>  ............................................
  anxious.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll stop. I shouldn't have expected this one to go differently.
    >>  ............................................
    pt  Tudo bem. Eu paro. Não devia ter esperado que fosse diferente desta vez.
    >>  ............................................
  athletic.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be older in a year and we can have this again then.
    >>  ............................................
    pt  Novo demais. Certo. Vou estar mais velho em um ano e a gente repete essa conversa.
    >>  ............................................
  athletic.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'll leave it a while and see if that changes.
    >>  ............................................
    pt  Todo mundo na praça repete. Vou deixar um tempo e ver se muda.
    >>  ............................................
  athletic.dialogue.conversations.news.teen.dismiss/3
    en  Fine. It'll get about without me, same as it always does.
    >>  ............................................
    pt  Tudo bem. Vai se espalhar sem mim, como sempre.
    >>  ............................................
  confident.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be too young for two more years and then I'll be too green.
    >>  ............................................
    pt  Novo demais. Certo. Vou ser novo demais por mais dois anos e depois vou ser verde demais.
    >>  ............................................
  confident.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'm the one told not to.
    >>  ............................................
    pt  Todo mundo na praça repete. Sou eu que sou mandado a não repetir.
    >>  ............................................
  confident.dialogue.conversations.news.teen.dismiss/3
    en  Fine. Ask somebody taller and see how much they actually know.
    >>  ............................................
    pt  Tudo bem. Pergunte a alguém mais alto e veja o quanto sabem de verdade.
    >>  ............................................
  crabby.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be too young for two more years and then I'll be too green.
    >>  ............................................
    pt  Novo demais. Certo. Vou ser novo demais por mais dois anos e depois vou ser verde demais.
    >>  ............................................
  crabby.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'm the one told not to.
    >>  ............................................
    pt  Todo mundo na praça repete. Sou eu que sou mandado a não repetir.
    >>  ............................................
  crabby.dialogue.conversations.news.teen.dismiss/3
    en  Fine. Ask somebody taller and see how much they actually know.
    >>  ............................................
    pt  Tudo bem. Pergunte a alguém mais alto e veja o quanto sabem de verdade.
    >>  ............................................
  extroverted.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. From you, %1$s. That's the part I hadn't expected.
    >>  ............................................
    pt  ...Novo demais. De você, %1$s. É essa a parte que eu não esperava.
    >>  ............................................
  extroverted.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I told you because I thought you'd hear it differently.
    >>  ............................................
    pt  Todo mundo na praça repete. Eu contei a você porque achei que você ouviria diferente.
    >>  ............................................
  extroverted.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll not bring you the next one, and I'd rather that weren't how this went.
    >>  ............................................
    pt  Tudo bem. Não te trago a próxima, e eu preferia que não tivesse acabado assim.
    >>  ............................................
  flirty.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. From you, %1$s. That's the part I hadn't expected.
    >>  ............................................
    pt  ...Novo demais. De você, %1$s. É essa a parte que eu não esperava.
    >>  ............................................
  flirty.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I told you because I thought you'd hear it differently.
    >>  ............................................
    pt  Todo mundo na praça repete. Eu contei a você porque achei que você ouviria diferente.
    >>  ............................................
  flirty.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll not bring you the next one, and I'd rather that weren't how this went.
    >>  ............................................
    pt  Tudo bem. Não te trago a próxima, e eu preferia que não tivesse acabado assim.
    >>  ............................................
  friendly.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. From you, %1$s. That's the part I hadn't expected.
    >>  ............................................
    pt  ...Novo demais. De você, %1$s. É essa a parte que eu não esperava.
    >>  ............................................
  friendly.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I told you because I thought you'd hear it differently.
    >>  ............................................
    pt  Todo mundo na praça repete. Eu contei a você porque achei que você ouviria diferente.
    >>  ............................................
  friendly.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll not bring you the next one, and I'd rather that weren't how this went.
    >>  ............................................
    pt  Tudo bem. Não te trago a próxima, e eu preferia que não tivesse acabado assim.
    >>  ............................................
  gloomy.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. I know what that means and it isn't about my age, %1$s.
    >>  ............................................
    pt  ...Novo demais. Eu sei o que isso significa e não é sobre a minha idade, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. It's only a problem when I do, and I've noticed that for years.
    >>  ............................................
    pt  Todo mundo repete. Só é problema quando eu repito, e eu reparo nisso há anos.
    >>  ............................................
  gloomy.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll stop. I shouldn't have expected this one to go differently.
    >>  ............................................
    pt  Tudo bem. Eu paro. Não devia ter esperado que fosse diferente desta vez.
    >>  ............................................
  greedy.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be too young for two more years and then I'll be too green.
    >>  ............................................
    pt  Novo demais. Certo. Vou ser novo demais por mais dois anos e depois vou ser verde demais.
    >>  ............................................
  greedy.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'm the one told not to.
    >>  ............................................
    pt  Todo mundo na praça repete. Sou eu que sou mandado a não repetir.
    >>  ............................................
  greedy.dialogue.conversations.news.teen.dismiss/3
    en  Fine. Ask somebody taller and see how much they actually know.
    >>  ............................................
    pt  Tudo bem. Pergunte a alguém mais alto e veja o quanto sabem de verdade.
    >>  ............................................
  grumpy.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be too young for two more years and then I'll be too green.
    >>  ............................................
    pt  Novo demais. Certo. Vou ser novo demais por mais dois anos e depois vou ser verde demais.
    >>  ............................................
  grumpy.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'm the one told not to.
    >>  ............................................
    pt  Todo mundo na praça repete. Sou eu que sou mandado a não repetir.
    >>  ............................................
  grumpy.dialogue.conversations.news.teen.dismiss/3
    en  Fine. Ask somebody taller and see how much they actually know.
    >>  ............................................
    pt  Tudo bem. Pergunte a alguém mais alto e veja o quanto sabem de verdade.
    >>  ............................................
  introverted.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. Right.
    >>  ............................................
    pt  ...Novo demais. Certo.
    >>  ............................................
  introverted.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it.
    >>  ............................................
    pt  Todo mundo na praça repete.
    >>  ............................................
  introverted.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll keep the next one.
    >>  ............................................
    pt  Tudo bem. Vou guardar a próxima.
    >>  ............................................
  lazy.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be older in a year and we can have this again then.
    >>  ............................................
    pt  Novo demais. Certo. Vou estar mais velho em um ano e a gente repete essa conversa.
    >>  ............................................
  lazy.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'll leave it a while and see if that changes.
    >>  ............................................
    pt  Todo mundo na praça repete. Vou deixar um tempo e ver se muda.
    >>  ............................................
  lazy.dialogue.conversations.news.teen.dismiss/3
    en  Fine. It'll get about without me, same as it always does.
    >>  ............................................
    pt  Tudo bem. Vai se espalhar sem mim, como sempre.
    >>  ............................................
  odd.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. Right.
    >>  ............................................
    pt  ...Novo demais. Certo.
    >>  ............................................
  odd.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it.
    >>  ............................................
    pt  Todo mundo na praça repete.
    >>  ............................................
  odd.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll keep the next one.
    >>  ............................................
    pt  Tudo bem. Vou guardar a próxima.
    >>  ............................................
  peaceful.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be older in a year and we can have this again then.
    >>  ............................................
    pt  Novo demais. Certo. Vou estar mais velho em um ano e a gente repete essa conversa.
    >>  ............................................
  peaceful.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'll leave it a while and see if that changes.
    >>  ............................................
    pt  Todo mundo na praça repete. Vou deixar um tempo e ver se muda.
    >>  ............................................
  peaceful.dialogue.conversations.news.teen.dismiss/3
    en  Fine. It'll get about without me, same as it always does.
    >>  ............................................
    pt  Tudo bem. Vai se espalhar sem mim, como sempre.
    >>  ............................................
  peppy.dialogue.conversations.news.teen.dismiss/1
    en  Too young! Right. Two more years of that and then I'll be too inexperienced. I've a calendar.
    >>  ............................................
    pt  Novo demais! Certo. Mais dois anos disso e aí eu vou ser inexperiente demais. Eu tenho calendário.
    >>  ............................................
  peppy.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. Everyone. I'm simply the one who gets the lecture.
    >>  ............................................
    pt  Todo mundo repete. Todo mundo. Eu que ganho o sermão.
    >>  ............................................
  peppy.dialogue.conversations.news.teen.dismiss/3
    en  Fine! Go and ask somebody taller. I'll wait. It won't take long.
    >>  ............................................
    pt  Tudo bem! Vá perguntar a alguém mais alto. Eu espero. Não vai demorar.
    >>  ............................................
  playful.dialogue.conversations.news.teen.dismiss/1
    en  Too young! Right. Two more years of that and then I'll be too inexperienced. I've a calendar.
    >>  ............................................
    pt  Novo demais! Certo. Mais dois anos disso e aí eu vou ser inexperiente demais. Eu tenho calendário.
    >>  ............................................
  playful.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. Everyone. I'm simply the one who gets the lecture.
    >>  ............................................
    pt  Todo mundo repete. Todo mundo. Eu que ganho o sermão.
    >>  ............................................
  playful.dialogue.conversations.news.teen.dismiss/3
    en  Fine! Go and ask somebody taller. I'll wait. It won't take long.
    >>  ............................................
    pt  Tudo bem! Vá perguntar a alguém mais alto. Eu espero. Não vai demorar.
    >>  ............................................
  relaxed.dialogue.conversations.news.teen.dismiss/1
    en  Too young. Right. I'll be older in a year and we can have this again then.
    >>  ............................................
    pt  Novo demais. Certo. Vou estar mais velho em um ano e a gente repete essa conversa.
    >>  ............................................
  relaxed.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it. I'll leave it a while and see if that changes.
    >>  ............................................
    pt  Todo mundo na praça repete. Vou deixar um tempo e ver se muda.
    >>  ............................................
  relaxed.dialogue.conversations.news.teen.dismiss/3
    en  Fine. It'll get about without me, same as it always does.
    >>  ............................................
    pt  Tudo bem. Vai se espalhar sem mim, como sempre.
    >>  ............................................
  sensitive.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. I know what that means and it isn't about my age, %1$s.
    >>  ............................................
    pt  ...Novo demais. Eu sei o que isso significa e não é sobre a minha idade, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. It's only a problem when I do, and I've noticed that for years.
    >>  ............................................
    pt  Todo mundo repete. Só é problema quando eu repito, e eu reparo nisso há anos.
    >>  ............................................
  sensitive.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll stop. I shouldn't have expected this one to go differently.
    >>  ............................................
    pt  Tudo bem. Eu paro. Não devia ter esperado que fosse diferente desta vez.
    >>  ............................................
  shy.dialogue.conversations.news.teen.dismiss/1
    en  ...Too young. Right.
    >>  ............................................
    pt  ...Novo demais. Certo.
    >>  ............................................
  shy.dialogue.conversations.news.teen.dismiss/2
    en  Everyone in the square repeats it.
    >>  ............................................
    pt  Todo mundo na praça repete.
    >>  ............................................
  shy.dialogue.conversations.news.teen.dismiss/3
    en  Fine. I'll keep the next one.
    >>  ............................................
    pt  Tudo bem. Vou guardar a próxima.
    >>  ............................................
  upbeat.dialogue.conversations.news.teen.dismiss/1
    en  Too young! Right. Two more years of that and then I'll be too inexperienced. I've a calendar.
    >>  ............................................
    pt  Novo demais! Certo. Mais dois anos disso e aí eu vou ser inexperiente demais. Eu tenho calendário.
    >>  ............................................
  upbeat.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. Everyone. I'm simply the one who gets the lecture.
    >>  ............................................
    pt  Todo mundo repete. Todo mundo. Eu que ganho o sermão.
    >>  ............................................
  upbeat.dialogue.conversations.news.teen.dismiss/3
    en  Fine! Go and ask somebody taller. I'll wait. It won't take long.
    >>  ............................................
    pt  Tudo bem! Vá perguntar a alguém mais alto. Eu espero. Não vai demorar.
    >>  ............................................
  witty.dialogue.conversations.news.teen.dismiss/1
    en  Too young! Right. Two more years of that and then I'll be too inexperienced. I've a calendar.
    >>  ............................................
    pt  Novo demais! Certo. Mais dois anos disso e aí eu vou ser inexperiente demais. Eu tenho calendário.
    >>  ............................................
  witty.dialogue.conversations.news.teen.dismiss/2
    en  Everyone repeats it. Everyone. I'm simply the one who gets the lecture.
    >>  ............................................
    pt  Todo mundo repete. Todo mundo. Eu que ganho o sermão.
    >>  ............................................
  witty.dialogue.conversations.news.teen.dismiss/3
    en  Fine! Go and ask somebody taller. I'll wait. It won't take long.
    >>  ............................................
    pt  Tudo bem! Vá perguntar a alguém mais alto. Eu espero. Não vai demorar.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `*` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.teen.leave` — accepted phrasings: "i will let you get on"; "i should go"; "another time"
  - the message must contain one of: `get`, `leave`, `on`
  - scored words: `get`(0.5), `leave`(1.2), `on`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.news.teen.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.teen.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.teen.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.teen.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.news.teen.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.teen.leave.terminal`: the villager accepts. Subject `news.teen`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen
```

```text
  dialogue.conversations.news.teen.leave/1   [41 chars]
    en  Right. I'll be about if anything changes.
    >>  ............................................
    pt  Certo. Vou estar por aí se algo mudar.
    >>  ............................................
  dialogue.conversations.news.teen.leave/2   [32 chars]
    en  Fine. You know where I am, %1$s.
    >>  ............................................
    pt  Tudo bem. Você sabe onde eu estou, %1$s.
    >>  ............................................
  dialogue.conversations.news.teen.leave/3   [60 chars]
    en  See you. I'll have more by the end of the week; I always do.
    >>  ............................................
    pt  Até. Vou ter mais no fim da semana; sempre tenho.
    >>  ............................................
```

---


## `conversations.topic.news.young.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `news`


```text
POOL   dialogue key: dialogue.conversations.topic.news.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.news.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.news.young.respond   [28 chars]
    en  That's what I heard, anyway.
    >>  ............................................
    pt  Foi isso que eu ouvi, enfim.
    >>  ............................................
```


### Button `take_seriously` — "Thank you for telling me properly."

*stance family `candor` · tone `gentle` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.young.take_seriously` — accepted phrasings: "thank you for telling me properly"; "thank you for telling me that properly"; "you told me that properly"
  - the message must contain one of: `properly`, `telling`
  - scored words: `properly`(1.5), `telling`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.news.young.respond.take_seriously
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.young.respond.take_seriously   [34 chars]
    en  Thank you for telling me properly.
    >>  ............................................
    pt  Obrigado por me contar direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `news.young.take_seriously`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, respect +2  _(recorded under topic `news.young.take_seriously`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.young.take_seriously
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me properly."
       spoken on: conversations.topic.news.young.respond, button `take_seriously`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.young.take_seriously.terminal`: the villager accepts. Subject `news.young`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.young.take_seriously/1   [91 chars]
    en  ...Properly. You said properly. Grown-ups usually do the voice where they don't believe me.
    >>  ............................................
    pt  ...Direito. Você disse direito. Adulto normalmente faz aquela voz de quem não acredita em mim.
    >>  ............................................
  dialogue.conversations.news.young.take_seriously/2   [71 chars]
    en  You're the first one who didn't check with somebody taller first, %1$s.
    >>  ............................................
    pt  Você foi o primeiro que não foi conferir com alguém mais alto antes, %1$s.
    >>  ............................................
  dialogue.conversations.news.young.take_seriously/3   [73 chars]
    en  I TOLD it properly. I practised. Thank you for noticing that I practised.
    >>  ............................................
    pt  Eu CONTEI direito. Eu treinei. Obrigado por reparar que eu treinei.
    >>  ............................................
```


### Button `ask_how_they_know` — "How did you find that out?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.young.ask_how_they_know` — accepted phrasings: "how did you find that out"; "how do you know that"; "where did you hear that"
  - the message must contain one of: `find`, `know`
  - scored words: `find`(1.5), `know`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.young.respond.ask_how_they_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.young.respond.ask_how_they_know   [26 chars]
    en  How did you find that out?
    >>  ............................................
    pt  Como você descobriu isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `news.young.ask_how_they_know`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.young.ask_how_they_know
WHO    VILLAGER — what the player reads after pressing "How did you find that out?"
       spoken on: conversations.topic.news.young.respond, button `ask_how_they_know`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.young.ask_how_they_know.terminal`: the villager accepts. Subject `news.young`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.young.ask_how_they_know/1   [84 chars]
    en  I listen. Nobody thinks I'm listening because I'm playing, but I'm always listening.
    >>  ............................................
    pt  Eu escuto. Ninguém acha que eu escuto porque eu estou brincando, mas eu escuto sempre.
    >>  ............................................
  dialogue.conversations.news.young.ask_how_they_know/2   [79 chars]
    en  People say things over my head like I'm furniture. So I know nearly everything.
    >>  ............................................
    pt  As pessoas falam por cima da minha cabeça como se eu fosse móvel. Então eu sei quase tudo.
    >>  ............................................
  dialogue.conversations.news.young.ask_how_they_know/3   [70 chars]
    en  I was under the table. That's the best place, %1$s. You should try it.
    >>  ............................................
    pt  Eu estava embaixo da mesa. É o melhor lugar, %1$s. Você devia experimentar.
    >>  ............................................
```


### Button `dismiss` — "You shouldn't repeat things."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `news.young.dismiss` — accepted phrasings: "you should not repeat things"; "do not repeat things like that"; "that is just repeating gossip"
  - the message must contain one of: `repeat`
  - scored words: `repeat`(1.5), `should`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.news.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.young.respond.dismiss   [28 chars]
    en  You shouldn't repeat things.
    >>  ............................................
    pt  Você não devia ficar repetindo as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `news.young.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -3, respect -2, tension +3  _(recorded under topic `news.young.dismiss`)_
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.young.dismiss
WHO    VILLAGER — what the player reads after pressing "You shouldn't repeat things."
       spoken on: conversations.topic.news.young.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.young.dismiss.terminal`: the villager dismisss. Subject `news.young`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.young.dismiss/1   [97 chars]
    en  ...It's not repeating if it's TRUE. You asked me. You asked and now I'm in trouble for answering.
    >>  ............................................
    pt  ...Não é repetir se é VERDADE. Você me perguntou. Perguntou e agora eu estou de castigo por responder.
    >>  ............................................
  dialogue.conversations.news.young.dismiss/2   [92 chars]
    en  Everyone else repeats things. I've heard them. But when I do it, it's different, apparently.
    >>  ............................................
    pt  Todo mundo repete as coisas. Eu já ouvi. Mas quando eu faço, é diferente, pelo visto.
    >>  ............................................
  dialogue.conversations.news.young.dismiss/3   [41 chars]
    en  Fine. Then I won't tell you the next one.
    >>  ............................................
    pt  Tá bom. Então eu não conto a próxima.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.news.young.dismiss/1
    en  ...You asked me. I thought that made it all right to say, %1$s.
    >>  ............................................
    pt  ...Você me perguntou. Achei que isso deixava tudo bem, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.news.young.dismiss/2
    en  I didn't mean to get anyone in trouble. I'm sorry.
    >>  ............................................
    pt  Eu não queria arranjar problema pra ninguém. Desculpe.
    >>  ............................................
  anxious.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I won't answer next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu não respondo.
    >>  ............................................
  athletic.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, and you asked. That's usually enough for me.
    >>  ............................................
    pt  ...É verdade, e você perguntou. Pra mim costuma bastar.
    >>  ............................................
  athletic.dialogue.conversations.news.young.dismiss/2
    en  Aye, well. I'll keep the next one a while longer.
    >>  ............................................
    pt  É, bom. Vou guardar a próxima mais um pouco.
    >>  ............................................
  athletic.dialogue.conversations.news.young.dismiss/3
    en  ...Right you are. It'll get about without me.
    >>  ............................................
    pt  ...Você tem razão. Vai se espalhar sem mim.
    >>  ............................................
  confident.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE. You asked me and now I'm in trouble for answering.
    >>  ............................................
    pt  Não é repetir se é VERDADE. Você me perguntou e agora eu levo bronca por responder.
    >>  ............................................
  confident.dialogue.conversations.news.young.dismiss/2
    en  Right. Then don't ask me things.
    >>  ............................................
    pt  Certo. Então não me pergunte coisas.
    >>  ............................................
  confident.dialogue.conversations.news.young.dismiss/3
    en  ...I only said what happened.
    >>  ............................................
    pt  ...Eu só disse o que aconteceu.
    >>  ............................................
  crabby.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE. You asked me and now I'm in trouble for answering.
    >>  ............................................
    pt  Não é repetir se é VERDADE. Você me perguntou e agora eu levo bronca por responder.
    >>  ............................................
  crabby.dialogue.conversations.news.young.dismiss/2
    en  Right. Then don't ask me things.
    >>  ............................................
    pt  Certo. Então não me pergunte coisas.
    >>  ............................................
  crabby.dialogue.conversations.news.young.dismiss/3
    en  ...I only said what happened.
    >>  ............................................
    pt  ...Eu só disse o que aconteceu.
    >>  ............................................
  extroverted.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's true, %1$s. And you asked me.
    >>  ............................................
    pt  Não é repetir se é verdade, %1$s. E você me perguntou.
    >>  ............................................
  extroverted.dialogue.conversations.news.young.dismiss/2
    en  I'd not have said it to anyone else. Only to you.
    >>  ............................................
    pt  Eu não teria dito a mais ninguém. Só a você.
    >>  ............................................
  extroverted.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I'll say nothing next time, even to you.
    >>  ............................................
    pt  ...Certo. Da próxima eu não digo nada, nem a você.
    >>  ............................................
  flirty.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's true, %1$s. And you asked me.
    >>  ............................................
    pt  Não é repetir se é verdade, %1$s. E você me perguntou.
    >>  ............................................
  flirty.dialogue.conversations.news.young.dismiss/2
    en  I'd not have said it to anyone else. Only to you.
    >>  ............................................
    pt  Eu não teria dito a mais ninguém. Só a você.
    >>  ............................................
  flirty.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I'll say nothing next time, even to you.
    >>  ............................................
    pt  ...Certo. Da próxima eu não digo nada, nem a você.
    >>  ............................................
  friendly.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's true, %1$s. And you asked me.
    >>  ............................................
    pt  Não é repetir se é verdade, %1$s. E você me perguntou.
    >>  ............................................
  friendly.dialogue.conversations.news.young.dismiss/2
    en  I'd not have said it to anyone else. Only to you.
    >>  ............................................
    pt  Eu não teria dito a mais ninguém. Só a você.
    >>  ............................................
  friendly.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I'll say nothing next time, even to you.
    >>  ............................................
    pt  ...Certo. Da próxima eu não digo nada, nem a você.
    >>  ............................................
  gloomy.dialogue.conversations.news.young.dismiss/1
    en  ...You asked me. I thought that made it all right to say, %1$s.
    >>  ............................................
    pt  ...Você me perguntou. Achei que isso deixava tudo bem, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.news.young.dismiss/2
    en  I didn't mean to get anyone in trouble. I'm sorry.
    >>  ............................................
    pt  Eu não queria arranjar problema pra ninguém. Desculpe.
    >>  ............................................
  gloomy.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I won't answer next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu não respondo.
    >>  ............................................
  greedy.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE. You asked me and now I'm in trouble for answering.
    >>  ............................................
    pt  Não é repetir se é VERDADE. Você me perguntou e agora eu levo bronca por responder.
    >>  ............................................
  greedy.dialogue.conversations.news.young.dismiss/2
    en  Right. Then don't ask me things.
    >>  ............................................
    pt  Certo. Então não me pergunte coisas.
    >>  ............................................
  greedy.dialogue.conversations.news.young.dismiss/3
    en  ...I only said what happened.
    >>  ............................................
    pt  ...Eu só disse o que aconteceu.
    >>  ............................................
  grumpy.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE. You asked me and now I'm in trouble for answering.
    >>  ............................................
    pt  Não é repetir se é VERDADE. Você me perguntou e agora eu levo bronca por responder.
    >>  ............................................
  grumpy.dialogue.conversations.news.young.dismiss/2
    en  Right. Then don't ask me things.
    >>  ............................................
    pt  Certo. Então não me pergunte coisas.
    >>  ............................................
  grumpy.dialogue.conversations.news.young.dismiss/3
    en  ...I only said what happened.
    >>  ............................................
    pt  ...Eu só disse o que aconteceu.
    >>  ............................................
  introverted.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, though.
    >>  ............................................
    pt  ...Mas é verdade.
    >>  ............................................
  introverted.dialogue.conversations.news.young.dismiss/2
    en  You asked me.
    >>  ............................................
    pt  Você me perguntou.
    >>  ............................................
  introverted.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I'll stop.
    >>  ............................................
    pt  ...Certo. Eu paro.
    >>  ............................................
  lazy.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, and you asked. That's usually enough for me.
    >>  ............................................
    pt  ...É verdade, e você perguntou. Pra mim costuma bastar.
    >>  ............................................
  lazy.dialogue.conversations.news.young.dismiss/2
    en  Aye, well. I'll keep the next one a while longer.
    >>  ............................................
    pt  É, bom. Vou guardar a próxima mais um pouco.
    >>  ............................................
  lazy.dialogue.conversations.news.young.dismiss/3
    en  ...Right you are. It'll get about without me.
    >>  ............................................
    pt  ...Você tem razão. Vai se espalhar sem mim.
    >>  ............................................
  odd.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, though.
    >>  ............................................
    pt  ...Mas é verdade.
    >>  ............................................
  odd.dialogue.conversations.news.young.dismiss/2
    en  You asked me.
    >>  ............................................
    pt  Você me perguntou.
    >>  ............................................
  odd.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I'll stop.
    >>  ............................................
    pt  ...Certo. Eu paro.
    >>  ............................................
  peaceful.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, and you asked. That's usually enough for me.
    >>  ............................................
    pt  ...É verdade, e você perguntou. Pra mim costuma bastar.
    >>  ............................................
  peaceful.dialogue.conversations.news.young.dismiss/2
    en  Aye, well. I'll keep the next one a while longer.
    >>  ............................................
    pt  É, bom. Vou guardar a próxima mais um pouco.
    >>  ............................................
  peaceful.dialogue.conversations.news.young.dismiss/3
    en  ...Right you are. It'll get about without me.
    >>  ............................................
    pt  ...Você tem razão. Vai se espalhar sem mim.
    >>  ............................................
  peppy.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE! That's just news, and news is my favourite thing.
    >>  ............................................
    pt  Não é repetir se é VERDADE! Isso é notícia, e notícia é a minha coisa favorita.
    >>  ............................................
  peppy.dialogue.conversations.news.young.dismiss/2
    en  Right! I asked nobody and told everybody. That's not what happened, but fine.
    >>  ............................................
    pt  Certo! Eu não perguntei a ninguém e contei a todos. Não foi isso, mas tudo bem.
    >>  ............................................
  peppy.dialogue.conversations.news.young.dismiss/3
    en  ...Ha. You asked. You did. I've witnesses.
    >>  ............................................
    pt  ...Ha. Você perguntou. Perguntou sim. Eu tenho testemunhas.
    >>  ............................................
  playful.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE! That's just news, and news is my favourite thing.
    >>  ............................................
    pt  Não é repetir se é VERDADE! Isso é notícia, e notícia é a minha coisa favorita.
    >>  ............................................
  playful.dialogue.conversations.news.young.dismiss/2
    en  Right! I asked nobody and told everybody. That's not what happened, but fine.
    >>  ............................................
    pt  Certo! Eu não perguntei a ninguém e contei a todos. Não foi isso, mas tudo bem.
    >>  ............................................
  playful.dialogue.conversations.news.young.dismiss/3
    en  ...Ha. You asked. You did. I've witnesses.
    >>  ............................................
    pt  ...Ha. Você perguntou. Perguntou sim. Eu tenho testemunhas.
    >>  ............................................
  relaxed.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, and you asked. That's usually enough for me.
    >>  ............................................
    pt  ...É verdade, e você perguntou. Pra mim costuma bastar.
    >>  ............................................
  relaxed.dialogue.conversations.news.young.dismiss/2
    en  Aye, well. I'll keep the next one a while longer.
    >>  ............................................
    pt  É, bom. Vou guardar a próxima mais um pouco.
    >>  ............................................
  relaxed.dialogue.conversations.news.young.dismiss/3
    en  ...Right you are. It'll get about without me.
    >>  ............................................
    pt  ...Você tem razão. Vai se espalhar sem mim.
    >>  ............................................
  sensitive.dialogue.conversations.news.young.dismiss/1
    en  ...You asked me. I thought that made it all right to say, %1$s.
    >>  ............................................
    pt  ...Você me perguntou. Achei que isso deixava tudo bem, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.news.young.dismiss/2
    en  I didn't mean to get anyone in trouble. I'm sorry.
    >>  ............................................
    pt  Eu não queria arranjar problema pra ninguém. Desculpe.
    >>  ............................................
  sensitive.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I won't answer next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu não respondo.
    >>  ............................................
  shy.dialogue.conversations.news.young.dismiss/1
    en  ...It's true, though.
    >>  ............................................
    pt  ...Mas é verdade.
    >>  ............................................
  shy.dialogue.conversations.news.young.dismiss/2
    en  You asked me.
    >>  ............................................
    pt  Você me perguntou.
    >>  ............................................
  shy.dialogue.conversations.news.young.dismiss/3
    en  ...Right. I'll stop.
    >>  ............................................
    pt  ...Certo. Eu paro.
    >>  ............................................
  upbeat.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE! That's just news, and news is my favourite thing.
    >>  ............................................
    pt  Não é repetir se é VERDADE! Isso é notícia, e notícia é a minha coisa favorita.
    >>  ............................................
  upbeat.dialogue.conversations.news.young.dismiss/2
    en  Right! I asked nobody and told everybody. That's not what happened, but fine.
    >>  ............................................
    pt  Certo! Eu não perguntei a ninguém e contei a todos. Não foi isso, mas tudo bem.
    >>  ............................................
  upbeat.dialogue.conversations.news.young.dismiss/3
    en  ...Ha. You asked. You did. I've witnesses.
    >>  ............................................
    pt  ...Ha. Você perguntou. Perguntou sim. Eu tenho testemunhas.
    >>  ............................................
  witty.dialogue.conversations.news.young.dismiss/1
    en  It's not repeating if it's TRUE! That's just news, and news is my favourite thing.
    >>  ............................................
    pt  Não é repetir se é VERDADE! Isso é notícia, e notícia é a minha coisa favorita.
    >>  ............................................
  witty.dialogue.conversations.news.young.dismiss/2
    en  Right! I asked nobody and told everybody. That's not what happened, but fine.
    >>  ............................................
    pt  Certo! Eu não perguntei a ninguém e contei a todos. Não foi isso, mas tudo bem.
    >>  ............................................
  witty.dialogue.conversations.news.young.dismiss/3
    en  ...Ha. You asked. You did. I've witnesses.
    >>  ............................................
    pt  ...Ha. Você perguntou. Perguntou sim. Eu tenho testemunhas.
    >>  ............................................
```

</details>


### Button `leave` — "Off you go, then."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.news.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.news.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.news.young.respond.leave   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.news.young.leave
WHO    VILLAGER — what the player reads after pressing "Off you go, then."
       spoken on: conversations.topic.news.young.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.young.leave.terminal`: the villager accepts. Subject `news.young`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.news.young.leave/1   [59 chars]
    en  Okay bye! I'll come and find you if something else happens.
    >>  ............................................
    pt  Tá, tchau! Eu venho te achar se acontecer outra coisa.
    >>  ............................................
  dialogue.conversations.news.young.leave/2   [54 chars]
    en  Bye, %1$s! There'll be more tomorrow, there always is.
    >>  ............................................
    pt  Tchau, %1$s! Amanhã tem mais, sempre tem.
    >>  ............................................
  dialogue.conversations.news.young.leave/3   [48 chars]
    en  Right. I'm going to go and find out more things.
    >>  ............................................
    pt  Certo. Vou descobrir mais coisas.
    >>  ............................................
```

---

